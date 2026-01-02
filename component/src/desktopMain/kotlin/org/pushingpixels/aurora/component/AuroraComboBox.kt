/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.component.utils.popup.GeneralCommandMenuPopupHandler
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.*

@Immutable
@OptIn(AuroraInternalApi::class)
private class ComboBoxDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

private object ComboBoxOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = density.getClassicCornerRadius()
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = cornerRadius - radiusAdjustment,
            sides = Sides(),
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

@OptIn(AuroraInternalApi::class)
private class ComboBoxLocator(val topLeftOffset: AuroraOffset, val size: MutableState<IntSize>) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.value = coordinates.size
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun Modifier.comboBoxLocator(topLeftOffset: AuroraOffset, size: MutableState<IntSize>) = this.then(
    ComboBoxLocator(topLeftOffset, size)
)

@OptIn(AuroraInternalApi::class)
@Composable
internal fun <E> comboBoxIntrinsicSize(
    contentModel: ComboBoxContentModel<E>,
    presentationModel: ComboBoxPresentationModel<E>
): Size {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val prototypeDisplayFullWidth = getPrototypeDisplayFullWidth(
        contentModel, presentationModel
    )

    var contentWidth: Dp = 0.dp
    val icon = presentationModel.displayIconConverter?.invoke(contentModel.selectedItem)
    if (icon != null) {
        contentWidth += 16.dp
        contentWidth += ComboBoxSizingConstants.DefaultComboBoxIconTextLayoutGap * presentationModel.horizontalGapScaleFactor
    }
    contentWidth += (getLabelPreferredSingleLineWidth(
        contentModel = LabelContentModel(text = presentationModel.displayConverter.invoke(contentModel.selectedItem)),
        presentationModel = LabelPresentationModel(
            contentPadding = PaddingValues(0.dp),
            textStyle = presentationModel.textStyle ?: LocalTextStyle.current,
            textMaxLines = 1,
            textOverflow = presentationModel.textOverflow
        ),
        resolvedTextStyle = resolvedTextStyle,
        layoutDirection = layoutDirection,
        density = density,
        fontFamilyResolver = fontFamilyResolver
    ) / density.density).dp

    contentWidth = max(contentWidth, prototypeDisplayFullWidth)

    var width = presentationModel.contentPadding.calculateStartPadding(layoutDirection) +
            contentWidth + ComboBoxSizingConstants.DefaultComboBoxContentArrowGap +
            ComboBoxSizingConstants.DefaultComboBoxArrowWidth +
            presentationModel.contentPadding.calculateEndPadding(layoutDirection)
    width = max(width, presentationModel.defaultMinSize.width)

    var contentHeight: Dp = 0.dp
    if (icon != null) {
        contentHeight = 16.dp
    }
    contentHeight = max(
        contentHeight,
        (getLabelPreferredHeight(
            contentModel = LabelContentModel(text = presentationModel.displayConverter.invoke(contentModel.selectedItem)),
            presentationModel = LabelPresentationModel(
                contentPadding = PaddingValues(0.dp),
                textStyle = presentationModel.textStyle ?: LocalTextStyle.current,
                textMaxLines = 1,
                textOverflow = presentationModel.textOverflow
            ),
            resolvedTextStyle = resolvedTextStyle,
            layoutDirection = layoutDirection,
            density = density,
            fontFamilyResolver = fontFamilyResolver,
            availableWidth = Float.MAX_VALUE
        ) / density.density).dp,
    )
    val height = presentationModel.contentPadding.calculateTopPadding() +
            contentHeight + presentationModel.contentPadding.calculateBottomPadding()

    return Size(
        width.value * density.density,
        height.value * density.density
    )
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun <E> getPrototypeDisplayFullWidth(
    contentModel: ComboBoxContentModel<E>,
    presentationModel: ComboBoxPresentationModel<E>
): Dp {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }
    val textMeasurer = rememberTextMeasurer(cacheSize = 10)

    val displayPrototype = presentationModel.displayPrototype?.invoke(contentModel.items) ?:
        contentModel.items.maxByOrNull {
            textMeasurer.measure(
                text = presentationModel.displayConverter.invoke(it),
                style = presentationModel.textStyle ?: resolvedTextStyle,
                overflow = presentationModel.textOverflow,
                maxLines = 1
            ).multiParagraph.width
        }

    val prototypeDisplayLabelWidth = getLabelPreferredSingleLineWidth(
        contentModel = LabelContentModel(text = presentationModel.displayConverter.invoke(displayPrototype!!)),
        presentationModel = LabelPresentationModel(
            contentPadding = PaddingValues(0.dp),
            textStyle = presentationModel.textStyle ?: resolvedTextStyle,
            textMaxLines = 1,
            textOverflow = presentationModel.textOverflow
        ),
        resolvedTextStyle = resolvedTextStyle,
        layoutDirection = layoutDirection,
        density = density,
        fontFamilyResolver = fontFamilyResolver
    )

    val prototypeIcon = presentationModel.displayIconConverter?.invoke(displayPrototype)

    // Full prototype display width - icon + gap if icon is present, text
    var prototypeDisplayFullWidth: Dp = 0.0.dp
    if (prototypeIcon != null) {
        prototypeDisplayFullWidth += (16.dp + ComboBoxSizingConstants.DefaultComboBoxIconTextLayoutGap * presentationModel.horizontalGapScaleFactor)
    }
    prototypeDisplayFullWidth += (prototypeDisplayLabelWidth / density.density).dp

    return prototypeDisplayFullWidth
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun <E> AuroraComboBox(
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    contentModel: ComboBoxContentModel<E>,
    presentationModel: ComboBoxPresentationModel<E>
) {
    val drawingCache = remember { ComboBoxDrawingCache() }
    val rollover by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = contentModel.enabled,
                isRollover = rollover,
                isSelected = false,
                isPressed = isPressed
            )
        )
    }

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters
    val buttonShaper = AuroraSkin.buttonShaper
    val popupOriginator = LocalPopupMenu.current ?: LocalWindow.current.rootPane

    val comboBoxTopLeftOffset = AuroraOffset(0.0f, 0.0f)
    val comboBoxSize = remember { mutableStateOf(IntSize(0, 0)) }
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    // Transition for the selection state
    val selectionTransition = updateTransition(false)
    val selectedFraction by selectionTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the rollover state
    val rolloverTransition = updateTransition(rollover)
    val rolloverFraction by rolloverTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the pressed state
    val pressedTransition = updateTransition(isPressed)
    val pressedFraction by pressedTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the enabled state
    val enabledTransition = updateTransition(contentModel.enabled)
    val enabledFraction by enabledTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // TODO - figure out why the animations are not running without looking
    //  at the result (and how it looks like in the new animation APIs)
    @Suppress("UNUSED_VARIABLE")
    val totalFraction = selectedFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = contentModel.enabled,
        selected = false,
        rollover = rollover,
        pressed = isPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (transitionInfo.value != null) {
        LaunchedEffect(currentState.value) {
            val transitionFloat = Animatable(transitionInfo.value!!.from)
            val result = transitionFloat.animateTo(
                targetValue = transitionInfo.value!!.to,
                animationSpec = tween(durationMillis = transitionInfo.value!!.duration)
            ) {
                modelStateInfo.updateActiveStates(value)
            }

            if (result.endReason == AnimationEndReason.Finished) {
                modelStateInfo.updateActiveStates(1.0f)
                modelStateInfo.clear(currentState.value)
            }
        }
    }

    val commandMenuContentModel = CommandMenuContentModel(
        group = CommandGroup(
            commands = contentModel.items.map {
                Command(
                    text = presentationModel.displayConverter.invoke(it),
                    icon = presentationModel.displayIconConverter?.invoke(it),
                    isActionEnabled = true,
                    action = { contentModel.onTriggerItemSelectedChange.invoke(it) }
                )
            }
        )
    )
    val contentModelState = rememberUpdatedState(commandMenuContentModel)
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)

    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = modifier.auroraRichTooltip(
            richTooltip = contentModel.richTooltip,
            presentationModel = presentationModel.richTooltipPresentationModel
        ).clickable(
            enabled = contentModel.enabled,
            onClick = {
                if (AuroraPopupManager.isShowingPopupFrom(
                        originator = popupOriginator,
                        pointInOriginator = AuroraOffset(
                            x = comboBoxTopLeftOffset.x + comboBoxSize.value.width / 2.0f,
                            y = comboBoxTopLeftOffset.y + comboBoxSize.value.height / 2.0f
                        ).asOffset(density)
                    )
                ) {
                    // We're showing a popup that originates from this combo. Hide it.
                    AuroraPopupManager.hidePopups(originator = popupOriginator)
                } else {
                    // Display our popup content.
                    val displayPrototypeCommand: Command? = presentationModel.popupDisplayPrototype?.let {
                        val displayPrototype = it.invoke(contentModel.items)
                        Command(
                            text = presentationModel.displayConverter.invoke(displayPrototype),
                            icon = presentationModel.displayIconConverter?.invoke(displayPrototype),
                            isActionEnabled = true,
                            action = { }
                        )
                    }
                    val popupWindow = GeneralCommandMenuPopupHandler.showPopupContent(
                        popupOriginator = popupOriginator,
                        layoutDirection = layoutDirection,
                        density = density,
                        textStyle = resolvedTextStyle,
                        fontFamilyResolver = fontFamilyResolver,
                        skinColors = skinColors,
                        skinPainters = painters,
                        buttonShaper = buttonShaper,
                        decorationAreaType = decorationAreaType,
                        compositionLocalContext = compositionLocalContext,
                        anchorBoundsInWindow = Rect(
                            offset = comboBoxTopLeftOffset.asOffset(density),
                            size = comboBoxSize.value.asSize(density)
                        ),
                        popupTriggerAreaInWindow = Rect(
                            offset = comboBoxTopLeftOffset.asOffset(density),
                            size = comboBoxSize.value.asSize(density)
                        ),
                        contentModel = contentModelState,
                        presentationModel = CommandPopupMenuPresentationModel(
                            itemPresentationState = CommandButtonPresentationState.Medium,
                            maxVisibleItems = presentationModel.popupMaxVisibleItems,
                            popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                            backgroundFillColorQuery = { rowIndex, colorTokens ->
                                if ((rowIndex % 2) == 0) {
                                    colorTokens.containerSurface
                                } else {
                                    if (colorTokens.isDark) {
                                        colorTokens.containerSurfaceHigh
                                    } else {
                                        colorTokens.containerSurfaceLow
                                    }
                                }
                            },
                        ),
                        displayPrototypeCommand = displayPrototypeCommand,
                        toDismissPopupsOnActivation = true,
                        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                        popupAnchorBoundsProvider = null,
                        popupOriginatorKeyTip = null,
                        overlays = emptyMap(),
                        popupKind = AuroraPopupManager.PopupKind.Popup
                    )
                    coroutineScope.launch {
                        popupWindow?.opacity = 1.0f
                    }
                }
            },
            interactionSource = interactionSource,
            indication = null
        ).comboBoxLocator(comboBoxTopLeftOffset, comboBoxSize),
        contentAlignment = Alignment.TopStart
    ) {
        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            colors = skinColors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted,
            isTextInFilledArea = true
        )
        // And the arrow color
        val arrowColor = getStateAwareColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted,
        ) { it.onContainer }

        if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
            // Populate the cached color tokens for filling the combobox
            // based on the current model state info
            populateColorTokens(
                colorTokens = drawingCache.colorTokens,
                colors = AuroraSkin.colors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                modelStateInfo = modelStateInfo,
                currState = currentState.value,
                associationKind = ContainerColorTokensAssociationKind.Default,
                backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                treatEnabledAsActive = false,
                skipFlatCheck = false,
                inactiveContainerType = ContainerType.Muted)

            val surfacePainter = AuroraSkin.painters.surfacePainter
            val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
            val outlinePainter = AuroraSkin.painters.outlinePainter
            val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

            // Handle flat comboboxes
            val alpha =
                if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                    // For flat comboboxes, compute the combined contribution of all
                    // non-disabled states - ignoring ComponentState.ENABLED
                    modelStateInfo.stateContributionMap
                        .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                        .values.sumOf { it.contribution.toDouble() }.toFloat()
                } else 1.0f

            Canvas(Modifier.matchParentSize()) {
                val width = this.size.width
                val height = this.size.height

                withTransform({
                    clipRect(
                        left = 0.0f,
                        top = 0.0f,
                        right = width,
                        bottom = height,
                        clipOp = ClipOp.Intersect
                    )
                }) {
                    val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                    val outlineFill = ComboBoxOutlineSuppler.getOutline(
                        layoutDirection = layoutDirection,
                        density = density,
                        size = this.size,
                        insets = outlineInset,
                        radiusAdjustment = 0.0f,
                        outlineKind = OutlineKind.Surface)
                    val outlineBoundingRect = outlineFill.bounds
                    if (outlineBoundingRect.isEmpty) {
                        return@withTransform
                    }

                    paintSurface(
                        drawScope = this,
                        componentState = currentState.value,
                        surfacePainter = surfacePainter,
                        surfacePainterOverlay = surfacePainterOverlay,
                        size = this.size,
                        alpha = alpha,
                        outline = outlineFill,
                        colorTokens = drawingCache.colorTokens)

                    paintOutline(
                        drawScope = this,
                        componentState = currentState.value,
                        outlinePainter = outlinePainter,
                        outlinePainterOverlay = outlinePainterOverlay,
                        size = this.size,
                        alpha = alpha,
                        outlineSupplier = ComboBoxOutlineSuppler,
                        colorTokens = drawingCache.colorTokens)

                    val arrowWidth = if (presentationModel.popupPlacementStrategy.isHorizontal)
                        ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx() else
                        ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx()
                    val arrowHeight =
                        if (presentationModel.popupPlacementStrategy.isHorizontal)
                            ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx() else
                            ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx()

                    val arrowOffsetX = if (layoutDirection == LayoutDirection.Ltr)
                        width - ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateRightPadding(
                            layoutDirection
                        ).toPx() - arrowWidth
                    else
                        ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateLeftPadding(
                            layoutDirection
                        ).toPx()
                    val arrowOffsetY = (height - arrowHeight) / 2.0f
                    translate(
                        left = arrowOffsetX,
                        top = arrowOffsetY
                    ) {
                        drawArrow(
                            drawScope = this,
                            width = arrowWidth,
                            height = arrowHeight,
                            strokeWidth = ArrowSizingConstants.DefaultArrowStroke.toPx(),
                            popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                            layoutDirection = layoutDirection,
                            color = arrowColor.withAlpha(alpha)
                        )
                    }
                }
            }
        }

        val prototypeDisplayFullWidth = getPrototypeDisplayFullWidth(
            contentModel, presentationModel
        )

        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value),
            LocalColorTokensOverlayProvider provides presentationModel.colorTokensOverlayProvider
        ) {
            Row(
                modifier = Modifier.defaultMinSize(
                    minWidth = presentationModel.defaultMinSize.width,
                    minHeight = presentationModel.defaultMinSize.height
                ).padding(
                    PaddingValues(
                        start = presentationModel.contentPadding.calculateStartPadding(layoutDirection),
                        end = presentationModel.contentPadding.calculateEndPadding(layoutDirection)
                                + ComboBoxSizingConstants.DefaultComboBoxContentArrowGap
                                + ComboBoxSizingConstants.DefaultComboBoxArrowWidth,
                        top = presentationModel.contentPadding.calculateTopPadding(),
                        bottom = presentationModel.contentPadding.calculateBottomPadding()
                    )
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val icon = presentationModel.displayIconConverter?.invoke(contentModel.selectedItem)
                if (icon != null) {
                    AuroraThemedIcon(
                        icon = icon,
                        size = DpSize(16.dp, 16.dp),
                        disabledFilterStrategy = presentationModel.displayIconDisabledFilterStrategy,
                        enabledFilterStrategy = presentationModel.displayIconEnabledFilterStrategy,
                        activeFilterStrategy = presentationModel.displayIconActiveFilterStrategy
                    )

                    Spacer(
                        modifier = Modifier.width(
                            ComboBoxSizingConstants.DefaultComboBoxIconTextLayoutGap * presentationModel.horizontalGapScaleFactor
                        )
                    )
                }

                AuroraText(
                    modifier = Modifier.defaultMinSize(
                        minWidth = prototypeDisplayFullWidth,
                        minHeight = 0.dp
                    ),
                    text = presentationModel.displayConverter.invoke(contentModel.selectedItem),
                    style = presentationModel.textStyle ?: LocalTextStyle.current,
                    overflow = presentationModel.textOverflow,
                    maxLines = 1
                )
            }
        }
    }
}
