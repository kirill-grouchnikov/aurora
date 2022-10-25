/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.SliderContentModel
import org.pushingpixels.aurora.component.model.SliderPresentationModel
import org.pushingpixels.aurora.component.model.SliderSizingConstants
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import kotlin.math.roundToInt

@Immutable
private class SliderDrawingCache(
    val trackRect: AuroraRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
    val thumbRect: AuroraRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

@OptIn(ExperimentalComposeUiApi::class, AuroraInternalApi::class)
@Composable
internal fun AuroraSlider(
    modifier: Modifier,
    contentModel: SliderContentModel,
    presentationModel: SliderPresentationModel
) {
    require(
        (contentModel.value >= contentModel.valueRange.start) and
                (contentModel.value <= contentModel.valueRange.endInclusive)
    ) {
        "Value ${contentModel.value} not in range ${contentModel.valueRange.start}..${contentModel.valueRange.endInclusive}"
    }
    require(presentationModel.tickSteps >= 0) {
        "Cannot have negative tick steps"
    }

    val ltr = (LocalLayoutDirection.current == LayoutDirection.Ltr)

    val interactionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { SliderDrawingCache() }
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

    val trackFillState =
        if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val trackSelectedState =
        if (contentModel.enabled) ComponentState.Selected else ComponentState.DisabledSelected

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
        associationKind = ColorSchemeAssociationKind.Border,
        componentState = trackFillState
    )
    val borderSelectionColorScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Border,
        componentState = trackSelectedState
    )
    val tickScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Separator,
        componentState = trackFillState
    )
    val fillPainter = ClassicFillPainter.Instance

    val dragStartX = remember { mutableStateOf(0.0f) }
    val cumulativeDragAmount = remember { mutableStateOf(0.0f) }

    val press = remember { mutableStateOf<PressInteraction.Press?>(null) }
    val drag = Modifier.draggable(
        state = rememberDraggableState {
            // Update the cumulative drag amount
            cumulativeDragAmount.value += it

            // Convert from pixels to value range
            var newValue = if (ltr) {
                contentModel.valueRange.start +
                        (dragStartX.value + cumulativeDragAmount.value - drawingCache.trackRect.x) *
                        (contentModel.valueRange.endInclusive - contentModel.valueRange.start) / drawingCache.trackRect.width
            } else {
                contentModel.valueRange.start +
                        (drawingCache.trackRect.x + drawingCache.trackRect.width -
                                dragStartX.value - cumulativeDragAmount.value) *
                        (contentModel.valueRange.endInclusive - contentModel.valueRange.start) / drawingCache.trackRect.width

            }
            newValue = newValue.coerceIn(
                contentModel.valueRange.start,
                contentModel.valueRange.endInclusive
            )

            // Snap to the closest tick if needed
            if ((presentationModel.tickSteps > 0) && presentationModel.snapToTicks) {
                val tickRange =
                    (contentModel.valueRange.endInclusive - contentModel.valueRange.start) / (presentationModel.tickSteps + 1)
                val tick = ((newValue - contentModel.valueRange.start) / tickRange).roundToInt()
                newValue = tick * tickRange
            }

            // Update value change lambda
            contentModel.onTriggerValueChange.invoke(newValue)
        },
        orientation = Orientation.Horizontal,
        reverseDirection = false,
        interactionSource = interactionSource,
        startDragImmediately = true,
        onDragStarted = { pos ->
            // Reset the drag start position and cumulative drag amount
            dragStartX.value = pos.x
            cumulativeDragAmount.value = 0.0f

            // Convert from pixels to value range
            var newValue = if (ltr) {
                contentModel.valueRange.start +
                        (pos.x - drawingCache.trackRect.x) *
                        (contentModel.valueRange.endInclusive - contentModel.valueRange.start) / drawingCache.trackRect.width
            } else {
                contentModel.valueRange.start +
                        (drawingCache.trackRect.x + drawingCache.trackRect.width - pos.x) *
                        (contentModel.valueRange.endInclusive - contentModel.valueRange.start) / drawingCache.trackRect.width
            }

            // Snap to the closest tick if needed
            if ((presentationModel.tickSteps > 0) && presentationModel.snapToTicks) {
                val tickRange =
                    (contentModel.valueRange.endInclusive - contentModel.valueRange.start) / (presentationModel.tickSteps + 1)
                val tick = ((newValue - contentModel.valueRange.start) / tickRange).roundToInt()
                newValue = tick * tickRange
            }

            // Update value change lambda
            contentModel.onTriggerValueChange.invoke(newValue)

            // And add pressed state to the interaction
            press.value = PressInteraction.Press(pos)
            interactionSource.emit(press.value!!)
        },
        onDragStopped = {
            // Update value change end lambda
            contentModel.onValueChangeEnd.invoke()

            // And remove pressed state to the interaction
            interactionSource.emit(PressInteraction.Release(press.value!!))
        }
    )

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

    Box(
        modifier = modifier.onPointerEvent(PointerEventType.Exit) {
            if (contentModel.enabled) {
                // Reset rollover when mouse exits the component bounds
                rollover = false
            }
        }.onPointerEvent(PointerEventType.Move) {
            if (contentModel.enabled) {
                // Rollover is only "active" in the thumb rectangle
                rollover = drawingCache.thumbRect.contains(
                    it.changes.first().position.x, it.changes.first().position.y)
            }
        }.then(drag)
    ) {
        // Populate the cached color scheme for filling the thumb
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.Fill
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
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.Border
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

        val alpha = if (currentState.value.isDisabled)
            skinColors.getAlpha(decorationAreaType, currentState.value) else 1.0f

        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
            isTextInFilledArea = true
        )

        var prefHeight = SliderSizingConstants.DefaultSliderContentPadding.calculateTopPadding()
        prefHeight += SliderSizingConstants.TrackHeight
        if ((presentationModel.tickSteps >= 0) && presentationModel.drawTicks) {
            prefHeight += SliderSizingConstants.TrackTickGap
            prefHeight += SliderSizingConstants.TickHeight
        }
        prefHeight += SliderSizingConstants.DefaultSliderContentPadding.calculateBottomPadding()

        Canvas(
            Modifier.size(width = SliderSizingConstants.DefaultWidth, height = prefHeight)
        ) {
            val radius = 1.5f.dp.toPx()

            // Calculate the track rectangle
            drawingCache.trackRect.x = SliderSizingConstants.ThumbFullSize.toPx() / 2.0f
            drawingCache.trackRect.y =
                SliderSizingConstants.DefaultSliderContentPadding.calculateTopPadding().toPx()
            drawingCache.trackRect.width = size.width - SliderSizingConstants.ThumbFullSize.toPx()
            drawingCache.trackRect.height = SliderSizingConstants.TrackHeight.toPx()

            // Calculate the thumb rectangle
            val thumbSize = SliderSizingConstants.ThumbFullSize.toPx() *
                    (2.0f + modelStateInfo.activeStrength) / 3.0f
            val selectionCenterX = if (ltr) {
                drawingCache.trackRect.x +
                        drawingCache.trackRect.width * contentModel.value / (contentModel.valueRange.endInclusive - contentModel.valueRange.start)
            } else {
                drawingCache.trackRect.x + drawingCache.trackRect.width -
                        drawingCache.trackRect.width * contentModel.value / (contentModel.valueRange.endInclusive - contentModel.valueRange.start)
            }
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
            withTransform({
                translate(
                    left = drawingCache.trackRect.x,
                    top = drawingCache.trackRect.y
                )
            }) {
                val trackOutline = getBaseOutline(
                    width = drawingCache.trackRect.width,
                    height = drawingCache.trackRect.height,
                    radius = radius,
                    sides = null,
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
                if (ltr) {
                    fillPainter.paintContourBackground(
                        drawScope = this,
                        size = Size(
                            selectionCenterX - drawingCache.trackRect.x,
                            drawingCache.trackRect.height
                        ),
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
                } else {
                    fillPainter.paintContourBackground(
                        drawScope = this,
                        size = Size(
                            drawingCache.trackRect.x + drawingCache.trackRect.width - selectionCenterX,
                            drawingCache.trackRect.height
                        ),
                        outline = Outline.Rounded(
                            RoundRect(
                                left = selectionCenterX,
                                top = drawingCache.trackRect.y,
                                right = drawingCache.trackRect.x + drawingCache.trackRect.width,
                                bottom = drawingCache.trackRect.y + drawingCache.trackRect.height,
                                cornerRadius = CornerRadius(radius, radius)
                            )
                        ),
                        fillScheme = selectionColorScheme,
                        alpha = stateAlpha
                    )
                }

                // Border selection
                if (ltr) {
                    withTransform({
                        translate(
                            left = drawingCache.trackRect.x,
                            top = drawingCache.trackRect.y
                        )
                    }) {
                        val selectionOutline = getBaseOutline(
                            width = selectionCenterX - drawingCache.trackRect.x,
                            height = drawingCache.trackRect.height,
                            radius = radius,
                            sides = Sides(straightSides = setOf(Side.Right)),
                            insets = 0.5f
                        )
                        drawOutline(
                            outline = selectionOutline,
                            style = Stroke(width = 1.0f),
                            color = borderSelectionColorScheme.darkColor,
                            alpha = stateAlpha
                        )
                    }
                } else {
                    withTransform({
                        translate(
                            left = selectionCenterX,
                            top = drawingCache.trackRect.y
                        )
                    }) {
                        val selectionOutline = getBaseOutline(
                            width = drawingCache.trackRect.x + drawingCache.trackRect.width - selectionCenterX,
                            height = drawingCache.trackRect.height,
                            radius = radius,
                            sides = Sides(straightSides = setOf(Side.Right)),
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
            }

            // Draw the ticks
            if ((presentationModel.tickSteps > 0) && presentationModel.drawTicks) {
                val tickHeight = SliderSizingConstants.TickHeight.toPx()
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
                        SliderSizingConstants.TrackTickGap.toPx()
                withTransform({
                    translate(left = 0.0f, top = tickTop)
                }) {
                    for (tick in 0 until presentationModel.tickSteps) {
                        val tickX = (drawingCache.trackRect.x +
                                drawingCache.trackRect.width * (tick + 1) / (presentationModel.tickSteps + 1)).toInt()

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
                    this,
                    this.size,
                    thumbOutline,
                    innerThumbOutline,
                    drawingCache.colorScheme,
                    alpha
                )
            }
        }
    }
}
