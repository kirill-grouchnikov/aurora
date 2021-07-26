/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposeWindow
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.utils.MutableColorScheme
import java.awt.Rectangle
import java.awt.Window
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Immutable
private class ComboBoxDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

private class ComboBoxLocator(val topLeftOffset: AuroraOffset, val size: AuroraSize) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.width = coordinates.size.width
        size.height = coordinates.size.height
    }
}

@Composable
private fun Modifier.comboBoxLocator(topLeftOffset: AuroraOffset, size: AuroraSize) = this.then(
    ComboBoxLocator(topLeftOffset, size)
)

@Composable
internal fun <E> AuroraComboBox(
    modifier: Modifier = Modifier,
    contentModel: ComboBoxContentModel<E>,
    presentationModel: ComboBoxPresentationModel<E>
) {
    val interactionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { ComboBoxDrawingCache() }
    var rollover by remember { mutableStateOf(false) }
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
    val buttonShaper = AuroraSkin.buttonShaper
    val painters = AuroraSkin.painters

    val comboBoxTopLeftOffset = AuroraOffset(0.0f, 0.0f)
    val comboBoxSize = AuroraSize(0, 0)
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current

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

    val parentComposition = rememberCompositionContext()

    val commandMenuContentModel = CommandMenuContentModel(
        group = CommandGroup(
            commands = contentModel.items.map {
                Command(
                    text = presentationModel.displayConverter.invoke(it),
                    isActionEnabled = true,
                    action = { contentModel.onTriggerItemSelectedChange.invoke(it) }
                )
            }
        )
    )
    val contentModelState = rememberUpdatedState(commandMenuContentModel)

    Box(
        modifier = modifier
            .pointerMoveFilter(
                onEnter = {
                    rollover = true
                    false
                },
                onExit = {
                    rollover = false
                    false
                },
                onMove = {
                    false
                })
            .clickable(
                enabled = contentModel.enabled,
                onClick = {
                    displayPopupContent(
                        parentWindow = null,
                        layoutDirection = layoutDirection,
                        density = density,
                        textStyle = textStyle,
                        resourceLoader = resourceLoader,
                        parentComposition = parentComposition,
                        anchorBoundsInWindow = Rect(
                            offset = comboBoxTopLeftOffset.asOffset(density),
                            size = comboBoxSize.asSize(density)
                        ),
                        contentModel = contentModelState,
                        presentationModel = CommandPopupMenuPresentationModel(
                            menuPresentationState = CommandButtonPresentationState.Medium,
                            maxVisibleMenuCommands =  presentationModel.popupMaxVisibleItems,
                            popupPlacementStrategy = presentationModel.popupPlacementStrategy
                        ),
                        toDismissPopupsOnActivation = true,
                        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                        overlays = emptyMap()
                    )
                },
                interactionSource = interactionSource,
                indication = null
            )
            .comboBoxLocator(comboBoxTopLeftOffset, comboBoxSize),
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

            val alpha: Float
            if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                // For flat buttons, compute the combined contribution of all
                // non-disabled states - ignoring ComponentState.ENABLED
                alpha = modelStateInfo.stateContributionMap
                    .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                    .values.sumOf { it.contribution.toDouble() }.toFloat()
            } else {
                alpha = if (currentState.value.isDisabled)
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
                    val outline = buttonShaper.getButtonOutline(
                        width = width,
                        height = height,
                        extraInsets = 0.5f,
                        isInner = false,
                        sides = Sides(),
                        drawScope = this
                    )

                    val outlineBoundingRect = outline.bounds
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
                        this, this.size, outline, drawingCache.colorScheme, alpha
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

                    val innerOutline = if (borderPainter.isPaintingInnerOutline)
                        buttonShaper.getButtonOutline(
                            width = width,
                            height = height,
                            extraInsets = 1.0f,
                            isInner = true,
                            sides = Sides(),
                            drawScope = this
                        ) else null

                    borderPainter.paintBorder(
                        this, this.size, outline, innerOutline, drawingCache.colorScheme, alpha
                    )

                    val arrowWidth = if (presentationModel.popupPlacementStrategy.isHorizontal)
                        ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx() else
                        ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx()
                    val arrowHeight =
                        if (presentationModel.popupPlacementStrategy.isHorizontal)
                            ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx() else
                            ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx()
                    // TODO - support RTL
                    translate(
                        left = width - ComboBoxSizingConstants.DefaultComboBoxContentPadding.calculateRightPadding(
                            layoutDirection
                        ).toPx() - arrowWidth,
                        top = (height - arrowHeight) / 2.0f
                    ) {
                        drawArrow(
                            drawScope = this,
                            width = arrowWidth,
                            height = arrowHeight,
                            strokeWidth = 2.0.dp.toPx(),
                            direction = presentationModel.popupPlacementStrategy,
                            layoutDirection = layoutDirection,
                            color = arrowColor
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
                // TODO - revisit this maybe
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
                    // TODO - add RTL support
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

@Composable
private fun ComboBoxPopupContent(
    contentProjections: List<CommandButtonProjection>,
    popupColumnSize: Size,
    showingVerticalPopupContentScrollBar: Boolean
) {
    val borderScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = DecorationAreaType.None,
        associationKind = ColorSchemeAssociationKind.Border,
        componentState = ComponentState.Enabled
    )
    val popupBorderColor = AuroraSkin.painters.borderPainter.getRepresentativeColor(borderScheme)
    val stateVertical = rememberScrollState(0)

    ComboBoxPopupLayout(
        modifier = Modifier.auroraBackground(),
        popupColumnSize = popupColumnSize,
        showingVerticalPopupContentScrollBar = showingVerticalPopupContentScrollBar
    ) {
        Canvas(modifier = Modifier) {
            if ((size.width > 0.0f) && (size.height > 0.0f)) {
                val outline = Outline.Rectangle(
                    rect = Rect(
                        left = 0.5f, top = 0.5f,
                        right = size.width - 0.5f, bottom = size.height - 0.5f
                    )
                )
                drawOutline(
                    outline = outline,
                    color = popupBorderColor,
                    style = Stroke(width = 1.0f)
                )
            }
        }
        ComboBoxPopupColumn(
            modifier = Modifier.verticalScroll(stateVertical),
            popupColumnSize = popupColumnSize
        ) {
            val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
                decorationAreaType = AuroraSkin.decorationAreaType
            )
            val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
            val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
            for ((projectionIndex, projection) in contentProjections.withIndex()) {
                projection.project(
                    modifier = Modifier.background(
                        color = if ((projectionIndex % 2) == 0) backgroundEvenRows else backgroundOddRows
                    )
                )
            }
        }
        if (showingVerticalPopupContentScrollBar) {
            AuroraVerticalScrollbar(
                adapter = remember(stateVertical) {
                    ScrollbarAdapter(stateVertical)
                }
            )
        }
    }
}

@Composable
private fun ComboBoxPopupLayout(
    modifier: Modifier,
    popupColumnSize: Size,
    showingVerticalPopupContentScrollBar: Boolean,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val canvasMeasurable = measurables[0]
        val popupColumnMeasurable = measurables[1]
        val verticalScrollBarMeasurable =
            if (showingVerticalPopupContentScrollBar) measurables[2] else null

        val popupColumnPlaceable = popupColumnMeasurable.measure(
            Constraints.fixed(
                width = popupColumnSize.width.roundToInt(),
                height = popupColumnSize.height.roundToInt()
            )
        )

        var verticalScrollBarPlaceable: Placeable? = null
        val scrollBarMarginPx = ScrollBarSizingConstants.DefaultScrollBarMargin.roundToPx()
        var fullWidth = popupColumnSize.width.roundToInt()
        if (verticalScrollBarMeasurable != null) {
            // account for top and bottom margins for height
            verticalScrollBarPlaceable =
                verticalScrollBarMeasurable.measure(
                    Constraints.fixed(
                        width = ScrollBarSizingConstants.DefaultScrollBarThickness.roundToPx(),
                        height = popupColumnSize.height.roundToInt() - 2 * scrollBarMarginPx
                    )
                )
            fullWidth += (verticalScrollBarPlaceable.measuredWidth + 2 * scrollBarMarginPx)
        }

        val canvasPlaceable = canvasMeasurable.measure(
            Constraints.fixed(
                width = fullWidth + 2,
                height = popupColumnSize.height.roundToInt() + 2
            )
        )

        layout(width = fullWidth + 2, height = popupColumnSize.height.roundToInt() + 2) {
            canvasPlaceable.place(x = 0, y = 0)
            // TODO - support RTL
            // Offset everything else by 1,1 for border insets
            popupColumnPlaceable.place(x = 1, y = 1)
            verticalScrollBarPlaceable?.place(
                x = popupColumnSize.width.roundToInt() + scrollBarMarginPx + 1,
                y = scrollBarMarginPx + 1
            )
        }
    }
}

@Composable
private fun ComboBoxPopupColumn(
    modifier: Modifier,
    popupColumnSize: Size,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, _ ->
        val placeables = measurables.map { measurable ->
            // Measure each child with fixed (widest) width
            measurable.measure(Constraints.fixedWidth(popupColumnSize.width.roundToInt()))
        }

        // The children are laid out in a column
        val contentMaxHeight = placeables.sumOf { it.height }

        layout(width = popupColumnSize.width.roundToInt(), height = contentMaxHeight) {
            var yPosition = 0

            // TODO - support RTL
            placeables.forEach { placeable ->
                placeable.placeRelative(
                    x = 0,
                    y = yPosition
                )
                yPosition += placeable.height
            }
        }
    }
}

