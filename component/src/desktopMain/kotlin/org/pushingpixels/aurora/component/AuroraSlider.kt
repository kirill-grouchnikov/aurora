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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.ComponentState
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.utils.getBaseOutline

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
    val DefaultWidth = 240.dp
    val DefaultHeight = 20.dp
    val ThumbFullSize = 18.dp
    val TrackHeight = 6.dp
}

private class SliderLocator(val size: AuroraSize) : OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        size.width = coordinates.size.width
        size.height = coordinates.size.height
    }
}

@Composable
private fun Modifier.sliderLocator(size: AuroraSize) = this.then(
    SliderLocator(size)
)

@Composable
fun AuroraSlider(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    AuroraSlider(
        value = value,
        valueRange = valueRange,
        modifier = modifier,
        enabled = enabled,
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
    modifier: Modifier,
    enabled: Boolean,
    interactionState: InteractionState,
    stateTransitionFloat: AnimatedFloat,
) {
    val drawingCache = remember { SliderDrawingCache() }

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
        enabled, Interaction.Pressed in interactionState,
        AuroraSkin.animationConfig.regular
    )

    val trackFillState = if (enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    val trackSelectedState = if (enabled) ComponentState.SELECTED else ComponentState.DISABLED_SELECTED

    // install state-aware alpha channel (support for skins
    // that use translucency on disabled states).
    val stateAlpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackFillState
    )
    val fillScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackFillState
    )
    val selectionColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackSelectedState
    )
    val borderFillColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = trackFillState
    )
    val borderSelectionColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = trackSelectedState
    )
    val fillPainter = ClassicFillPainter.INSTANCE

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val painters = AuroraSkin.painters

    val auroraSize = AuroraSize(0, 0)

    Box(
        modifier = Modifier.pointerMoveFilter(
            onEnter = {
                false
            },
            onExit = {
                stateTransitionTracker.rolloverState.value = false
                false
            },
            onMove = { position ->
                // Rollover is only "active" in the thumb rectangle
                stateTransitionTracker.rolloverState.value =
                    drawingCache.thumbRect.contains(position.x, position.y)
                false
            })
            .sliderLocator(auroraSize)
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

        Canvas(
            modifier
                .preferredSize(
                    width = SliderConstants.DefaultWidth,
                    height = SliderConstants.DefaultHeight
                )
        ) {
            val radius = 1.5f.dp.toPx()

            // Calculate the track rectangle
            drawingCache.trackRect.x = SliderConstants.ThumbFullSize.toPx() / 2.0f
            drawingCache.trackRect.y = (size.height - SliderConstants.TrackHeight.toPx()) / 2.0f
            drawingCache.trackRect.width = size.width - SliderConstants.ThumbFullSize.toPx() / 2.0f
            drawingCache.trackRect.height = SliderConstants.TrackHeight.toPx()

            // Calculate the thumb rectangle
            // TODO - support RTL
            val thumbSize = SliderConstants.ThumbFullSize.toPx() *
                    (2.0f + stateTransitionTracker.modelStateInfo.activeStrength) / 3.0f;
            val selectionCenterX = size.width * value / (valueRange.endInclusive - valueRange.start)
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
                        left = 0.0f,
                        top = drawingCache.trackRect.y,
                        right = size.width,
                        bottom = drawingCache.trackRect.y + drawingCache.trackRect.height,
                        cornerRadius = CornerRadius(radius, radius)
                    )
                ),
                fillScheme = fillScheme,
                alpha = stateAlpha
            )

            // Border track
            withTransform({ translate(left = 0.0f, top = drawingCache.trackRect.y) }) {
                val trackOutline = getBaseOutline(
                    width = size.width,
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
                    size = this.size,
                    outline = Outline.Rounded(
                        RoundRect(
                            left = 0.0f,
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
                withTransform({ translate(left = 0.0f, top = drawingCache.trackRect.y) }) {
                    val selectionOutline = getBaseOutline(
                        width = selectionCenterX,
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
