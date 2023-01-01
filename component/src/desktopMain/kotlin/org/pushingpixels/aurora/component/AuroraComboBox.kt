/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import kotlin.math.max

@Immutable
private class ComboBoxDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

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

@Composable
private fun Modifier.comboBoxLocator(topLeftOffset: AuroraOffset, size: MutableState<IntSize>) = this.then(
    ComboBoxLocator(topLeftOffset, size)
)

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
                    )) {
                    // We're showing a popup that originates from this combo. Hide it.
                    AuroraPopupManager.hidePopups(originator = popupOriginator)
                } else {
                    // Display our popup content.
                    showPopupContent(
                        popupOriginator = popupOriginator,
                        layoutDirection = layoutDirection,
                        density = density,
                        textStyle = resolvedTextStyle,
                        fontFamilyResolver = fontFamilyResolver,
                        skinColors = skinColors,
                        skinPainters = painters,
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
                            menuPresentationState = DefaultCommandPopupMenuPresentationState,
                            maxVisibleMenuCommands = presentationModel.popupMaxVisibleItems,
                            popupPlacementStrategy = presentationModel.popupPlacementStrategy
                        ),
                        toDismissPopupsOnActivation = true,
                        toUseBackgroundStriping = true,
                        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                        overlays = emptyMap()
                    )
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
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
            isTextInFilledArea = true
        )
        // And the arrow color
        val arrowColor = getStateAwareColor(
            modelStateInfo, currentState.value,
            decorationAreaType, ColorSchemeAssociationKind.Mark
        ) { it.markColor }

        if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
            // Populate the cached color scheme for filling the combobox
            // based on the current model state info
            populateColorScheme(
                drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
                ColorSchemeAssociationKind.Fill
            )
            // And retrieve the container fill colors
            val fillUltraLight = drawingCache.colorScheme.ultraLightColor
            val fillExtraLight = drawingCache.colorScheme.extraLightColor
            val fillLight = drawingCache.colorScheme.lightColor
            val fillMid = drawingCache.colorScheme.midColor
            val fillDark = drawingCache.colorScheme.darkColor
            val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
            val fillIsDark = drawingCache.colorScheme.isDark

            // Populate the cached color scheme for drawing the border
            // based on the current model state info
            populateColorScheme(
                drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
                ColorSchemeAssociationKind.Border
            )
            // And retrieve the border colors
            val borderUltraLight = drawingCache.colorScheme.ultraLightColor
            val borderExtraLight = drawingCache.colorScheme.extraLightColor
            val borderLight = drawingCache.colorScheme.lightColor
            val borderMid = drawingCache.colorScheme.midColor
            val borderDark = drawingCache.colorScheme.darkColor
            val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
            val borderIsDark = drawingCache.colorScheme.isDark

            val fillPainter = AuroraSkin.painters.fillPainter
            val borderPainter = AuroraSkin.painters.borderPainter

            val alpha =
                if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                    if (currentState.value == ComponentState.DisabledSelected) {
                        // Respect the alpha in disabled+selected state
                        skinColors.getAlpha(decorationAreaType, currentState.value)
                    } else {
                        // For flat comboboxes, compute the combined contribution of all
                        // non-disabled states - ignoring ComponentState.ENABLED
                        modelStateInfo.stateContributionMap
                            .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                            .values.sumOf { it.contribution.toDouble() }.toFloat()
                    }
                } else {
                    if (currentState.value.isDisabled)
                        AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f
                }

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
                    val fillOutline = buttonShaper.getButtonOutline(
                        width = width,
                        height = height,
                        extraInsets = 0.5f,
                        isInner = false,
                        sides = Sides(),
                        outlineKind = OutlineKind.Fill,
                        density = this
                    )

                    val outlineBoundingRect = fillOutline.bounds
                    if (outlineBoundingRect.isEmpty) {
                        return@withTransform
                    }

                    // Populate the cached color scheme for filling the combobox
                    drawingCache.colorScheme.ultraLight = fillUltraLight
                    drawingCache.colorScheme.extraLight = fillExtraLight
                    drawingCache.colorScheme.light = fillLight
                    drawingCache.colorScheme.mid = fillMid
                    drawingCache.colorScheme.dark = fillDark
                    drawingCache.colorScheme.ultraDark = fillUltraDark
                    drawingCache.colorScheme.isDark = fillIsDark
                    drawingCache.colorScheme.foreground = textColor
                    fillPainter.paintContourBackground(
                        this, this.size, fillOutline, drawingCache.colorScheme, alpha
                    )

                    // Populate the cached color scheme for drawing the border
                    drawingCache.colorScheme.ultraLight = borderUltraLight
                    drawingCache.colorScheme.extraLight = borderExtraLight
                    drawingCache.colorScheme.light = borderLight
                    drawingCache.colorScheme.mid = borderMid
                    drawingCache.colorScheme.dark = borderDark
                    drawingCache.colorScheme.ultraDark = borderUltraDark
                    drawingCache.colorScheme.isDark = borderIsDark
                    drawingCache.colorScheme.foreground = textColor

                    val borderOutline = buttonShaper.getButtonOutline(
                        width = width,
                        height = height,
                        extraInsets = 0.5f,
                        isInner = false,
                        sides = Sides(),
                        outlineKind = OutlineKind.Border,
                        density = this
                    )

                    val innerBorderOutline = if (borderPainter.isPaintingInnerOutline)
                        buttonShaper.getButtonOutline(
                            width = width,
                            height = height,
                            extraInsets = 1.0f,
                            isInner = true,
                            sides = Sides(),
                            outlineKind = OutlineKind.Border,
                            density = this
                        ) else null

                    borderPainter.paintBorder(
                        this, this.size, borderOutline, innerBorderOutline, drawingCache.colorScheme, alpha
                    )

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

        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            Layout(
                modifier = Modifier.padding(
                    PaddingValues(
                        start = ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateStartPadding(
                            layoutDirection
                        ),
                        end = ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateEndPadding(
                            layoutDirection
                        ) + ComboBoxSizingConstants.DefaultComboBoxContentArrowGap
                                + ComboBoxSizingConstants.DefaultComboBoxArrowWidth,
                        top = ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateTopPadding(),
                        bottom = ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateBottomPadding()
                    )
                ),
                content = {
                    AuroraText(presentationModel.displayConverter.invoke(contentModel.selectedItem))
                }
            ) { measurables, constraints ->
                // Measure each child so that we know how much space they need
                val placeables = measurables.map { measurable ->
                    // Measure each child
                    measurable.measure(constraints)
                }

                // The children are laid out in a row
                val contentTotalWidth = placeables.sumOf { it.width }
                // And the height of the row is determined by the height of the tallest child
                val contentMaxHeight = placeables.maxOf { it.height }

                // Get the preferred size
                var uiPreferredWidth = contentTotalWidth
                var uiPreferredHeight = contentMaxHeight

                // Bump up to default minimums if necessary
                uiPreferredWidth = max(
                    uiPreferredWidth,
                    ComboBoxSizingConstants.DefaultComboBoxContentWidth.roundToPx()
                )
                uiPreferredHeight = max(
                    uiPreferredHeight,
                    ComboBoxSizingConstants.DefaultComboBoxContentHeight.roundToPx()
                )

                // And ask the button shaper for the final sizing
                val finalSize = buttonShaper.getPreferredSize(
                    uiPreferredWidth.toFloat(), uiPreferredHeight.toFloat()
                )

                // Center children vertically within the vertical space
                layout(width = finalSize.width.toInt(), height = finalSize.height.toInt()) {
                    var xPosition = 0

                    placeables.forEach { placeable ->
                        placeable.placeRelative(
                            x = xPosition,
                            y = (finalSize.height.toInt() - placeable.height) / 2
                        )
                        xPosition += placeable.width
                    }
                }
            }
        }
    }
}
