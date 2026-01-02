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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraRect
import org.pushingpixels.aurora.common.contains
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.SliderContentModel
import org.pushingpixels.aurora.component.model.SliderPresentationModel
import org.pushingpixels.aurora.component.model.SliderSizingConstants
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.utils.*
import kotlin.math.roundToInt

@OptIn(AuroraInternalApi::class)
@Immutable
private class SliderDrawingCache(
    val trackRect: AuroraRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
    val thumbRect: AuroraRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

@Composable
internal fun sliderIntrinsicSize(
    contentModel: SliderContentModel,
    presentationModel: SliderPresentationModel
): Size {
    val density = LocalDensity.current

    var height = SliderSizingConstants.DefaultSliderContentPadding.calculateTopPadding()
    height += SliderSizingConstants.TrackHeight
    if ((presentationModel.tickSteps >= 0) && presentationModel.drawTicks) {
        height += SliderSizingConstants.TrackTickGap
        height += SliderSizingConstants.TickHeight
    }
    height += SliderSizingConstants.DefaultSliderContentPadding.calculateBottomPadding()

    return Size(
        SliderSizingConstants.DefaultWidth.value * density.density,
        height.value * density.density
    )
}

private object SliderTrackOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = density.getClassicCornerRadius() / 2.0f
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

private object SliderThumbOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        return Outline.Rounded(
            roundRect = RoundRect(
                left = 0.5f + insets,
                top = 0.5f + insets,
                right = size.width - 0.5f - insets,
                bottom = size.height - 0.5f - insets,
                radiusX = (size.width - 1.0f) / 2.0f - insets,
                radiusY = (size.height - 1.0f) / 2.0f - insets
            )
        )
    }
}

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

    val density = LocalDensity.current

    val trackFillState =
        if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val trackSelectedState =
        if (contentModel.enabled) ComponentState.Selected else ComponentState.DisabledSelected

    val decorationAreaType = AuroraSkin.decorationAreaType

    val selectionColorTokens = getActiveContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackSelectedState,
    )
    val tickColorTokens = getContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Separator,
        componentState = trackFillState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
        inactiveContainerType = ContainerType.Neutral
    )

    val surfacePainter = AuroraSkin.painters.surfacePainter
    val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
    val trackSurfacePainter = MatteSurfacePainter()
    val outlinePainter = AuroraSkin.painters.outlinePainter
    val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

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
                    it.changes.first().position.x, it.changes.first().position.y
                )
            }
        }.then(drag)
    ) {
        // Populate the cached color tokens for filling the thumb
        // based on the current model state info
        populateColorTokens(
            colorTokens = drawingCache.colorTokens,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            treatEnabledAsActive = false,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted)

        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Neutral,
            isTextInFilledArea = false
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
            //val radius = 1.5f.dp.toPx()

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
            val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
            val trackSize = Size(drawingCache.trackRect.width, drawingCache.trackRect.height)

            val outlineFill = SliderTrackOutlineSuppler.getOutline(
                layoutDirection = layoutDirection,
                density = density,
                size = trackSize,
                insets = outlineInset,
                radiusAdjustment = 0.0f,
                outlineKind = OutlineKind.Surface)

            translate(left = drawingCache.trackRect.x, top = drawingCache.trackRect.y) {
                paintSurface(
                    drawScope = this,
                    componentState = currentState.value,
                    surfacePainter = trackSurfacePainter,
                    surfacePainterOverlay = surfacePainterOverlay,
                    size = trackSize,
                    alpha = 1.0f,
                    outline = outlineFill,
                    colorTokens = drawingCache.colorTokens)

                paintOutline(
                    drawScope = this,
                    componentState = currentState.value,
                    outlinePainter = outlinePainter,
                    outlinePainterOverlay = outlinePainterOverlay,
                    size = trackSize,
                    alpha = 1.0f,
                    outlineSupplier = SliderTrackOutlineSuppler,
                    colorTokens = drawingCache.colorTokens)
            }

            if (selectionCenterX > 0.0f) {
                val selectionSize = Size(
                    width = if (ltr) {
                        selectionCenterX - drawingCache.trackRect.x
                    } else {
                        drawingCache.trackRect.x + drawingCache.trackRect.width - selectionCenterX
                    },
                    height = drawingCache.trackRect.height
                )

                if (ltr) {
                    val selectionSize = Size(
                        width = selectionCenterX - drawingCache.trackRect.x,
                        height = drawingCache.trackRect.height
                    )
                    translate(left = drawingCache.trackRect.x, top = drawingCache.trackRect.y) {
                        val selectedFill = SliderTrackOutlineSuppler.getOutline(
                            layoutDirection = layoutDirection,
                            density = density,
                            size = selectionSize,
                            insets = outlineInset,
                            radiusAdjustment = 0.0f,
                            outlineKind = OutlineKind.Surface)

                        paintSurface(
                            drawScope = this,
                            componentState = currentState.value,
                            surfacePainter = trackSurfacePainter,
                            surfacePainterOverlay = surfacePainterOverlay,
                            size = selectionSize,
                            alpha = 1.0f,
                            outline = selectedFill,
                            colorTokens = selectionColorTokens)

                        paintOutline(
                            drawScope = this,
                            componentState = currentState.value,
                            outlinePainter = outlinePainter,
                            outlinePainterOverlay = outlinePainterOverlay,
                            size = selectionSize,
                            alpha = 1.0f,
                            outlineSupplier = SliderTrackOutlineSuppler,
                            colorTokens = selectionColorTokens)

                    }
                } else {
                    val selectionSize = Size(
                        width = drawingCache.trackRect.x + drawingCache.trackRect.width - selectionCenterX,
                        height = drawingCache.trackRect.height
                    )
                    translate(left = selectionCenterX, top = drawingCache.trackRect.y) {
                        val selectedFill = SliderTrackOutlineSuppler.getOutline(
                            layoutDirection = layoutDirection,
                            density = density,
                            size = selectionSize,
                            insets = outlineInset,
                            radiusAdjustment = 0.0f,
                            outlineKind = OutlineKind.Surface)

                        paintSurface(
                            drawScope = this,
                            componentState = currentState.value,
                            surfacePainter = trackSurfacePainter,
                            surfacePainterOverlay = surfacePainterOverlay,
                            size = selectionSize,
                            alpha = 1.0f,
                            outline = selectedFill,
                            colorTokens = selectionColorTokens)

                        paintOutline(
                            drawScope = this,
                            componentState = currentState.value,
                            outlinePainter = outlinePainter,
                            outlinePainterOverlay = outlinePainterOverlay,
                            size = selectionSize,
                            alpha = 1.0f,
                            outlineSupplier = SliderTrackOutlineSuppler,
                            colorTokens = selectionColorTokens)

                    }
                }
            }

            // Draw the ticks
            if ((presentationModel.tickSteps > 0) && presentationModel.drawTicks) {
                val tickHeight = SliderSizingConstants.TickHeight.toPx()
                val tickPrimaryColor = if (tickColorTokens.isDark) {
                    tickColorTokens.complementaryContainerOutline.withAlpha(0.28125f)
                } else {
                    tickColorTokens.containerOutline.withAlpha(0.375f)
                }
                val tickPrimaryBrush = Brush.verticalGradient(
                    0.0f to tickPrimaryColor,
                    0.75f to tickPrimaryColor,
                    1.0f to tickPrimaryColor.withAlpha(0.0f),
                    startY = 0.0f,
                    endY = tickHeight,
                    tileMode = TileMode.Repeated
                )
                val tickSecondaryColor = if (tickColorTokens.isDark) {
                    tickColorTokens.containerOutline.withAlpha(0.75f)
                } else {
                    tickColorTokens.complementaryContainerOutline.withAlpha(0.9375f)
                }
                val tickSecondaryBrush = Brush.verticalGradient(
                    0.0f to tickSecondaryColor,
                    0.75f to tickSecondaryColor,
                    1.0f to tickSecondaryColor.withAlpha(0.0f),
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
            withTransform({
                translate(left = drawingCache.thumbRect.x, top = drawingCache.thumbRect.y)
            }) {
                val thumbOutlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                val thumbOutlineFill = SliderThumbOutlineSuppler.getOutline(
                    layoutDirection = layoutDirection,
                    density = density,
                    size = Size(thumbSize, thumbSize),
                    insets = thumbOutlineInset,
                    radiusAdjustment = 0.0f,
                    outlineKind = OutlineKind.Surface)

                paintSurface(
                    drawScope = this,
                    componentState = currentState.value,
                    surfacePainter = surfacePainter,
                    surfacePainterOverlay = surfacePainterOverlay,
                    size = Size(thumbSize, thumbSize),
                    alpha = 1.0f,
                    outline = thumbOutlineFill,
                    colorTokens = drawingCache.colorTokens)

                paintOutline(
                    drawScope = this,
                    componentState = currentState.value,
                    outlinePainter = outlinePainter,
                    outlinePainterOverlay = outlinePainterOverlay,
                    size = Size(thumbSize, thumbSize),
                    alpha = 1.0f,
                    outlineSupplier = SliderThumbOutlineSuppler,
                    colorTokens = drawingCache.colorTokens)
            }
        }
    }
}
