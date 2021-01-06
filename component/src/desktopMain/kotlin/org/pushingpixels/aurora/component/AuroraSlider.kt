/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.component

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.utils.getBaseOutline
import kotlin.math.roundToInt

// This will be initialized on first usage using the getSelectedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var SelectedTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getRolloverTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var RolloverTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getPressedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var PressedTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getEnabledTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var EnabledTransitionDefinition: TransitionDefinition<Boolean>

object SliderConstants {
    val DefaultSliderContentPadding = PaddingValues(start = 0.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)
    val DefaultWidth = 240.dp
    val ThumbFullSize = 18.dp
    val TrackHeight = 6.dp
    val TrackTickGap = 4.dp
    val TickHeight = 8.dp
}

@Composable
fun AuroraSlider(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChange: (Float) -> Unit = {},
    onValueChangeEnd: () -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tickSteps: Int = 0, // Zero means continuous slider value range
    snapToTicks: Boolean = false,
    drawTicks: Boolean = false
) {
    require((value >= valueRange.start) && (value <= valueRange.endInclusive)) {
        "Value $value not in range ${valueRange.start}..${valueRange.endInclusive}"
    }
    require(tickSteps >= 0) {
        "Cannot have negative tick steps"
    }

    AuroraSlider(
        value = value,
        valueRange = valueRange,
        onValueChange = onValueChange,
        onValueChangeEnd = onValueChangeEnd,
        modifier = modifier,
        enabled = enabled,
        tickSteps = tickSteps,
        snapToTicks = snapToTicks,
        drawTicks = drawTicks,
        interactionState = remember { InteractionState() },
        stateTransitionFloat = AnimatedFloat(0.0f, AmbientAnimationClock.current.asDisposableClock()),
    )
}

@Immutable
private class SliderDrawingCache(
    val trackRect: AuroraRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
    val thumbRect: AuroraRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false,
        ultraLight = Color.White,
        extraLight = Color.White,
        light = Color.White,
        mid = Color.White,
        dark = Color.White,
        ultraDark = Color.White,
        foreground = Color.Black
    )
)

@Composable
private fun AuroraSlider(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    onValueChangeEnd: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    tickSteps: Int,
    snapToTicks: Boolean,
    drawTicks: Boolean,
    interactionState: InteractionState,
    stateTransitionFloat: AnimatedFloat,
) {
    val drawingCache = remember { SliderDrawingCache() }
    val current = remember { mutableStateOf(value) }

    val stateTransitionTracker =
        remember { StateTransitionTracker(enabled, false, stateTransitionFloat) }

    // Transition for the selection state
    if (!::SelectedTransitionDefinition.isInitialized) {
        SelectedTransitionDefinition =
            getSelectedTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val selectionTransitionState = transition(
        definition = SelectedTransitionDefinition,
        initState = stateTransitionTracker.selectedState.value,
        toState = stateTransitionTracker.selectedState.value
    )
    // Transition for the rollover state
    if (!::RolloverTransitionDefinition.isInitialized) {
        RolloverTransitionDefinition =
            getRolloverTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val rolloverTransitionState = transition(
        definition = RolloverTransitionDefinition,
        initState = stateTransitionTracker.rolloverState.value,
        toState = stateTransitionTracker.rolloverState.value
    )
    // Transition for the pressed state
    if (!::PressedTransitionDefinition.isInitialized) {
        PressedTransitionDefinition =
            getPressedTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val pressedTransitionState = transition(
        definition = PressedTransitionDefinition,
        initState = Interaction.Pressed in interactionState,
        toState = Interaction.Pressed in interactionState
    )
    // Transition for the enabled state
    if (!::EnabledTransitionDefinition.isInitialized) {
        EnabledTransitionDefinition =
            getEnabledTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val enabledTransitionState = transition(
        definition = EnabledTransitionDefinition,
        initState = enabled,
        toState = enabled
    )

    // TODO - how to trigger the state transition animation without these transitions
    //  that track the changes in different states?
    selectionTransitionState[SelectionTransitionFraction]
    rolloverTransitionState[RolloverTransitionFraction]
    pressedTransitionState[PressedTransitionFraction]
    enabledTransitionState[EnabledTransitionFraction]

    stateTransitionTracker.update(
        isEnabled = enabled,
        isPressed = Interaction.Pressed in interactionState,
        isSelected = false,
        duration = AuroraSkin.animationConfig.regular
    )

    val trackFillState = if (enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    val trackSelectedState = if (enabled) ComponentState.SELECTED else ComponentState.DISABLED_SELECTED

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val painters = AuroraSkin.painters

    // install state-aware alpha channel (support for skins
    // that use translucency on disabled states).
    val stateAlpha = skinColors.getAlpha(
        decorationAreaType = decorationAreaType,
        componentState = trackFillState
    )
    val fillScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        componentState = trackFillState
    )
    val selectionColorScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        componentState = trackSelectedState
    )
    val borderFillColorScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = trackFillState
    )
    val borderSelectionColorScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = trackSelectedState
    )
    val tickScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.SEPARATOR,
        componentState = trackFillState
    )
    val fillPainter = ClassicFillPainter.INSTANCE

    val dragStartX = remember { mutableStateOf(0.0f) }
    val cumulativeDragAmount = remember { mutableStateOf(0.0f) }

    val drag = Modifier.draggable(
        orientation = Orientation.Horizontal,
        reverseDirection = false,
        interactionState = interactionState,
        startDragImmediately = true,
        onDragStarted = { pos ->
            // Reset the drag start position and cumulative drag amount
            dragStartX.value = pos.x
            cumulativeDragAmount.value = 0.0f

            // Convert from pixels to value range
            val newValue = valueRange.start +
                    (pos.x - drawingCache.trackRect.x) * (valueRange.endInclusive - valueRange.start) / drawingCache.trackRect.width
            current.value = newValue.coerceIn(valueRange.start, valueRange.endInclusive)

            // Snap to the closest tick if needed
            if ((tickSteps > 0) && snapToTicks) {
                val tickRange = (valueRange.endInclusive - valueRange.start) / (tickSteps + 1)
                val tick = ((current.value - valueRange.start) / tickRange).roundToInt()
                current.value = tick * tickRange
            }

            // Update value change lambda
            onValueChange.invoke(current.value)

            // And add pressed state to the interaction
            interactionState.addInteraction(Interaction.Pressed, pos)
        },
        onDrag = {
            // Update the cumulative drag amount
            cumulativeDragAmount.value += it

            // Convert from pixels to value range
            val newValue = valueRange.start +
                    (dragStartX.value + cumulativeDragAmount.value - drawingCache.trackRect.x) * (valueRange.endInclusive - valueRange.start) / drawingCache.trackRect.width
            current.value = newValue.coerceIn(valueRange.start, valueRange.endInclusive)

            // Snap to the closest tick if needed
            if ((tickSteps > 0) && snapToTicks) {
                val tickRange = (valueRange.endInclusive - valueRange.start) / (tickSteps + 1)
                val tick = ((current.value - valueRange.start) / tickRange).roundToInt()
                current.value = tick * tickRange
            }

            // Update value change lambda
            onValueChange.invoke(current.value)
        },
        onDragStopped = {
            // Update value change end lambda
            onValueChangeEnd.invoke()

            // And remove pressed state to the interaction
            interactionState.removeInteraction(Interaction.Pressed)
        }
    )

    Box(
        modifier = Modifier.pointerMoveFilter(
            onEnter = {
                false
            },
            onExit = {
                // Reset rollover when mouse exits the component bounds
                stateTransitionTracker.rolloverState.value = false
                false
            },
            onMove = { position ->
                // Rollover is only "active" in the thumb rectangle
                stateTransitionTracker.rolloverState.value =
                    drawingCache.thumbRect.contains(position.x, position.y)
                false
            }).then(drag)
    ) {
        // Populate the cached color scheme for filling the thumb
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, stateTransitionTracker.modelStateInfo, decorationAreaType,
            ColorSchemeAssociationKind.FILL
        )

        // And retrieve the thumb fill colors
        val thumbFillUltraLight = drawingCache.colorScheme.ultraLightColor
        val thumbFillExtraLight = drawingCache.colorScheme.extraLightColor
        val thumbFillLight = drawingCache.colorScheme.lightColor
        val thumbFillMid = drawingCache.colorScheme.midColor
        val thumbFillDark = drawingCache.colorScheme.darkColor
        val thumbFillUltraDark = drawingCache.colorScheme.ultraDarkColor
        val thumbFillIsDark = drawingCache.colorScheme.isDark

        // Populate the cached color scheme for drawing the thumb border
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, stateTransitionTracker.modelStateInfo, decorationAreaType,
            ColorSchemeAssociationKind.BORDER
        )
        // And retrieve the border colors
        val thumbBorderUltraLight = drawingCache.colorScheme.ultraLightColor
        val thumbBorderExtraLight = drawingCache.colorScheme.extraLightColor
        val thumbBorderLight = drawingCache.colorScheme.lightColor
        val thumbBorderMid = drawingCache.colorScheme.midColor
        val thumbBorderDark = drawingCache.colorScheme.darkColor
        val thumbBorderUltraDark = drawingCache.colorScheme.ultraDarkColor
        val thumbBorderIsDark = drawingCache.colorScheme.isDark

        val thumbFillPainter = painters.fillPainter
        val thumbBorderPainter = painters.borderPainter

        val alpha = if (stateTransitionTracker.currentState.isDisabled)
            skinColors.getAlpha(decorationAreaType, stateTransitionTracker.currentState) else 1.0f

        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = stateTransitionTracker.modelStateInfo,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            isTextInFilledArea = true
        )

        var prefHeight = SliderConstants.DefaultSliderContentPadding.top
        prefHeight += SliderConstants.TrackHeight
        if ((tickSteps >= 0) && drawTicks) {
            prefHeight += SliderConstants.TrackTickGap
            prefHeight += SliderConstants.TickHeight
        }
        prefHeight += SliderConstants.DefaultSliderContentPadding.bottom

        Canvas(
            modifier
                .preferredSize(
                    width = SliderConstants.DefaultWidth,
                    height = prefHeight
                )
        ) {
            val radius = 1.5f.dp.toPx()

            // Calculate the track rectangle
            drawingCache.trackRect.x = SliderConstants.ThumbFullSize.toPx() / 2.0f
            drawingCache.trackRect.y = SliderConstants.DefaultSliderContentPadding.top.toPx()
            drawingCache.trackRect.width = size.width - SliderConstants.ThumbFullSize.toPx()
            drawingCache.trackRect.height = SliderConstants.TrackHeight.toPx()

            // Calculate the thumb rectangle
            // TODO - support RTL
            val thumbSize = SliderConstants.ThumbFullSize.toPx() *
                    (2.0f + stateTransitionTracker.modelStateInfo.activeStrength) / 3.0f;
            val selectionCenterX = drawingCache.trackRect.x +
                    drawingCache.trackRect.width * current.value / (valueRange.endInclusive - valueRange.start)
            drawingCache.thumbRect.x = selectionCenterX - thumbSize / 2.0f
            drawingCache.thumbRect.y =
                drawingCache.trackRect.y + drawingCache.trackRect.height / 2.0f - thumbSize / 2.0f
            drawingCache.thumbRect.width = thumbSize
            drawingCache.thumbRect.height = thumbSize

            // Fill track
            fillPainter.paintContourBackground(
                drawScope = this,
                size = this.size,
                outline = Outline.Rounded(
                    RoundRect(
                        left = drawingCache.trackRect.x,
                        top = drawingCache.trackRect.y,
                        right = drawingCache.trackRect.x + drawingCache.trackRect.width,
                        bottom = drawingCache.trackRect.y + drawingCache.trackRect.height,
                        cornerRadius = CornerRadius(radius, radius)
                    )
                ),
                fillScheme = fillScheme,
                alpha = stateAlpha
            )

            // Border track
            withTransform({ translate(left = drawingCache.trackRect.x, top = drawingCache.trackRect.y) }) {
                val trackOutline = getBaseOutline(
                    width = drawingCache.trackRect.width,
                    height = drawingCache.trackRect.height,
                    radius = radius,
                    straightSides = emptySet(),
                    insets = 0.5f
                )
                drawOutline(
                    outline = trackOutline,
                    style = Stroke(width = 1.0f),
                    color = borderFillColorScheme.darkColor,
                    alpha = stateAlpha
                )
            }

            if (selectionCenterX > 0.0f) {
                // Fill selection
                fillPainter.paintContourBackground(
                    drawScope = this,
                    size = Size(selectionCenterX - drawingCache.trackRect.x, drawingCache.trackRect.height),
                    outline = Outline.Rounded(
                        RoundRect(
                            left = drawingCache.trackRect.x,
                            top = drawingCache.trackRect.y,
                            right = selectionCenterX,
                            bottom = drawingCache.trackRect.y + drawingCache.trackRect.height,
                            cornerRadius = CornerRadius(radius, radius)
                        )
                    ),
                    fillScheme = selectionColorScheme,
                    alpha = stateAlpha
                )

                // Border selection
                withTransform({ translate(left = drawingCache.trackRect.x, top = drawingCache.trackRect.y) }) {
                    val selectionOutline = getBaseOutline(
                        width = selectionCenterX - drawingCache.trackRect.x,
                        height = drawingCache.trackRect.height,
                        radius = radius,
                        straightSides = setOf(Side.END),
                        insets = 0.5f
                    )
                    drawOutline(
                        outline = selectionOutline,
                        style = Stroke(width = 1.0f),
                        color = borderSelectionColorScheme.darkColor,
                        alpha = stateAlpha
                    )
                }
            }

            // Draw the ticks
            if ((tickSteps > 0) && drawTicks) {
                val tickHeight = SliderConstants.TickHeight.toPx()
                val tickPrimaryBrush = Brush.verticalGradient(
                    0.0f to tickScheme.separatorPrimaryColor,
                    0.75f to tickScheme.separatorPrimaryColor,
                    1.0f to tickScheme.separatorPrimaryColor.withAlpha(0.0f),
                    startY = 0.0f,
                    endY = tickHeight,
                    tileMode = TileMode.Repeated
                )
                val tickSecondaryBrush = Brush.verticalGradient(
                    0.0f to tickScheme.separatorSecondaryColor,
                    0.75f to tickScheme.separatorSecondaryColor,
                    1.0f to tickScheme.separatorSecondaryColor.withAlpha(0.0f),
                    startY = 0.0f,
                    endY = tickHeight,
                    tileMode = TileMode.Repeated
                )

                val tickTop = drawingCache.trackRect.x + drawingCache.trackRect.height +
                        SliderConstants.TrackTickGap.toPx()
                withTransform({
                    translate(left = 0.0f, top = tickTop)
                }) {
                    for (tick in 0 until tickSteps) {
                        val tickX = (drawingCache.trackRect.x +
                                drawingCache.trackRect.width * (tick + 1) / (tickSteps + 1)).toInt()

                        drawLine(
                            brush = tickPrimaryBrush,
                            start = Offset(tickX - 0.5f, 0.0f),
                            end = Offset(tickX - 0.5f, tickHeight),
                            strokeWidth = 1.0f
                        )
                        drawLine(
                            brush = tickSecondaryBrush,
                            start = Offset(tickX + 0.5f, 0.0f),
                            end = Offset(tickX + 0.5f, tickHeight),
                            strokeWidth = 1.0f
                        )
                    }
                }
            }

            // Draw the thumb
            val thumbOutline =
                Outline.Rounded(
                    roundRect = RoundRect(
                        left = 0.5f, top = 0.5f,
                        right = thumbSize - 0.5f,
                        bottom = thumbSize - 0.5f,
                        radiusX = (thumbSize - 1.0f) / 2.0f,
                        radiusY = (thumbSize - 1.0f) / 2.0f
                    )
                )

            withTransform({
                translate(left = drawingCache.thumbRect.x, top = drawingCache.thumbRect.y)
            }) {
                // Populate the cached color scheme for filling the thumb
                drawingCache.colorScheme.ultraLight = thumbFillUltraLight
                drawingCache.colorScheme.extraLight = thumbFillExtraLight
                drawingCache.colorScheme.light = thumbFillLight
                drawingCache.colorScheme.mid = thumbFillMid
                drawingCache.colorScheme.dark = thumbFillDark
                drawingCache.colorScheme.ultraDark = thumbFillUltraDark
                drawingCache.colorScheme.isDark = thumbFillIsDark
                drawingCache.colorScheme.foreground = textColor

                thumbFillPainter.paintContourBackground(
                    this, this.size, thumbOutline, drawingCache.colorScheme, alpha
                )

                // Populate the cached color scheme for drawing the thumb border
                drawingCache.colorScheme.ultraLight = thumbBorderUltraLight
                drawingCache.colorScheme.extraLight = thumbBorderExtraLight
                drawingCache.colorScheme.light = thumbBorderLight
                drawingCache.colorScheme.mid = thumbBorderMid
                drawingCache.colorScheme.dark = thumbBorderDark
                drawingCache.colorScheme.ultraDark = thumbBorderUltraDark
                drawingCache.colorScheme.isDark = thumbBorderIsDark
                drawingCache.colorScheme.foreground = textColor

                val innerThumbOutline = if (thumbBorderPainter.isPaintingInnerOutline)
                    Outline.Rounded(
                        roundRect = RoundRect(
                            left = 1.0f, top = 1.0f,
                            right = thumbSize - 1.0f,
                            bottom = thumbSize - 1.0f,
                            radiusX = (thumbSize - 2.0f) / 2.0f,
                            radiusY = (thumbSize - 2.0f) / 2.0f
                        )
                    ) else null

                thumbBorderPainter.paintBorder(
                    this, this.size, thumbOutline, innerThumbOutline, drawingCache.colorScheme, alpha
                )
            }
        }
    }
}
