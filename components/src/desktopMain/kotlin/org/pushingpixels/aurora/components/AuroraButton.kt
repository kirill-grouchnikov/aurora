/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.components

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Interaction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.utils.*


// This will be initialized on first usage using the getSelectedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var SelectedTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getRolloverTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var RolloverTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getPressedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var PressedTransitionDefinition: TransitionDefinition<Boolean>

@Immutable
private class AuroraDrawingCache(
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
fun AuroraToggleButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    sides: ButtonSides = ButtonSides(),
    content: @Composable RowScope.() -> Unit
) {
    AuroraToggleButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        sides = sides,
        stateTransitionFloat = AnimatedFloat(0.0f, AmbientAnimationClock.current.asDisposableClock()),
        content = content
    )
}

@Composable
private fun AuroraToggleButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    sides: ButtonSides,
    stateTransitionFloat: AnimatedFloat,
    content: @Composable RowScope.() -> Unit
) {
    val drawingCache = remember { AuroraDrawingCache() }

    val stateTransitionTracker =
        remember { StateTransitionTracker(enabled, stateTransitionFloat) }


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
        initState = Interaction.Pressed in stateTransitionTracker.interactionState,
        toState = Interaction.Pressed in stateTransitionTracker.interactionState
    )

    // TODO - how to trigger the state transition animation without these transitions
    //  that track the changes in different states?
    selectionTransitionState[SelectionTransitionFraction]
    rolloverTransitionState[RolloverTransitionFraction]
    pressedTransitionState[PressedTransitionFraction]

    stateTransitionTracker.update(enabled)

    val decorationAreaType = AuroraSkin.decorationArea.type
    Box(
        modifier = modifier
            .pointerMoveFilter(
                onEnter = {
                    stateTransitionTracker.rolloverState.value = true
                    false
                },
                onExit = {
                    stateTransitionTracker.rolloverState.value = false
                    false
                },
                onMove = {
                    false
                })
            .clickable(
                onClick = {
                    stateTransitionTracker.selectedState.value = !stateTransitionTracker.selectedState.value
                    onClick.invoke()
                },
                enabled = enabled,
                interactionState = stateTransitionTracker.interactionState,
                indication = null
            ),
        contentAlignment = Alignment.TopStart
    ) {
        // Populate the cached color scheme for filling the button container
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, stateTransitionTracker.modelStateInfo, decorationAreaType,
            ColorSchemeAssociationKind.FILL
        )
        // And retrieve the container fill colors
        val fillUltraLight = drawingCache.colorScheme.ultraLightColor
        val fillExtraLight = drawingCache.colorScheme.extraLightColor
        val fillLight = drawingCache.colorScheme.lightColor
        val fillMid = drawingCache.colorScheme.midColor
        val fillDark = drawingCache.colorScheme.darkColor
        val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
        val fillIsDark = drawingCache.colorScheme.isDark

        val textColor = getTextColor(
            modelStateInfo = stateTransitionTracker.modelStateInfo,
            skinColors = AuroraSkin.colors,
            decorationAreaType = AuroraSkin.decorationArea.type,
            isTextInFilledArea = true
        )

        // Populate the cached color scheme for drawing the button border
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, stateTransitionTracker.modelStateInfo, decorationAreaType,
            ColorSchemeAssociationKind.BORDER
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
        val buttonShaper = AuroraSkin.buttonShaper

        val alpha = if (stateTransitionTracker.currentState.isDisabled)
            AuroraSkin.colors.getAlpha(decorationAreaType, stateTransitionTracker.currentState) else 1.0f

        Canvas(modifier.matchParentSize().padding(2.dp)) {
            val width = this.size.width
            val height = this.size.height

            val openDelta = 3
            val deltaLeft = if (sides.openSides.contains(Side.LEFT)) openDelta else 0
            val deltaRight = if (sides.openSides.contains(Side.RIGHT)) openDelta else 0
            val deltaTop = if (sides.openSides.contains(Side.TOP)) openDelta else 0
            val deltaBottom = if (sides.openSides.contains(Side.BOTTOM)) openDelta else 0

            val outline = buttonShaper.getButtonOutline(
                width = width + deltaLeft + deltaRight,
                height = height + deltaTop + deltaBottom,
                extraInsets = 0.5f,
                isInner = false,
                sides = sides,
                drawScope = this
            )

            // Populate the cached color scheme for filling the button container
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

            // Populate the cached color scheme for drawing the button border
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
                    width = width + deltaLeft + deltaRight,
                    height = height + deltaTop + deltaBottom,
                    extraInsets = 1.0f,
                    isInner = true,
                    sides = sides,
                    drawScope = this
                ) else null

            borderPainter.paintBorder(
                this, this.size, outline, innerOutline, drawingCache.colorScheme, alpha
            )
        }

        // Pass our text color to the children
        Providers(AmbientTextColor provides textColor) {
            Row(
                Modifier
                    .defaultMinSizeConstraints(
                        minWidth = 64.dp,
                        minHeight = 36.dp
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}
