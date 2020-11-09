/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.components

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.AmbientTextColor
import org.pushingpixels.mosaic.MosaicSkin
import org.pushingpixels.mosaic.colorscheme.BaseColorScheme
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme

enum class ButtonState {
    IDLE, SELECTED
}

private val ColorTransitionFraction = FloatPropKey()

interface ButtonColors {
    @Composable
    fun fillColorScheme(selected: Boolean): MosaicColorScheme

    @Composable
    fun borderColorScheme(selected: Boolean): MosaicColorScheme

    @Composable
    fun textColorScheme(selected: Boolean): MosaicColorScheme
}

@Composable
fun defaultButtonColors(
    fillColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedFillColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
    borderColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedBorderColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
    textColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedTextColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
): ButtonColors {
    return DefaultButtonColors(
        fillColorScheme = fillColorScheme,
        selectedFillColorScheme = selectedFillColorScheme,
        borderColorScheme = borderColorScheme,
        selectedBorderColorScheme = selectedBorderColorScheme,
        textColorScheme = textColorScheme,
        selectedTextColorScheme = selectedTextColorScheme
    )
}

private class DefaultButtonColors(
    private val fillColorScheme: MosaicColorScheme,
    private val selectedFillColorScheme: MosaicColorScheme,
    private val borderColorScheme: MosaicColorScheme,
    private val selectedBorderColorScheme: MosaicColorScheme,
    private val textColorScheme: MosaicColorScheme,
    private val selectedTextColorScheme: MosaicColorScheme
) : ButtonColors {

    @Composable
    override fun fillColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedFillColorScheme else fillColorScheme
    }

    @Composable
    override fun borderColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedBorderColorScheme else borderColorScheme
    }

    @Composable
    override fun textColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedTextColorScheme else textColorScheme
    }
}

@Composable
private fun getColorTransitionDefinition(duration: Int): TransitionDefinition<ButtonState> {
    return transitionDefinition {
        state(ButtonState.IDLE) {
            this[ColorTransitionFraction] = 0.0f
        }

        state(ButtonState.SELECTED) {
            this[ColorTransitionFraction] = 1.0f
        }

        transition(ButtonState.IDLE to ButtonState.SELECTED) {
            ColorTransitionFraction using tween(durationMillis = duration)
        }

        transition(ButtonState.SELECTED to ButtonState.IDLE) {
            ColorTransitionFraction using tween(durationMillis = duration)
        }
    }
}

// This will be initialized on first usage using the getColorTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var ColorTransitionDefinition: TransitionDefinition<ButtonState>

@Composable
fun MosaicToggleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    shape: Shape = MosaicSkin.shapes.regular,
    colorsSchemes: ButtonColors = defaultButtonColors(),
    content: @Composable RowScope.() -> Unit
) {
    val state = remember { InteractionState() }

    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

    // Transition for animating the colors of the button
    if (!::ColorTransitionDefinition.isInitialized) {
        ColorTransitionDefinition =
            getColorTransitionDefinition(MosaicSkin.animationConfig.regular)
    }
    val colorTransitionState = transition(
        definition = ColorTransitionDefinition,
        initState = buttonState.value,
        toState = buttonState.value
    )

    Box(
        modifier = modifier.clickable(onClick = {
            buttonState.value = if (buttonState.value == ButtonState.IDLE) {
                ButtonState.SELECTED
            } else {
                ButtonState.IDLE
            }
            onClick.invoke()
        }, interactionState = state, indication = null),
        alignment = Alignment.TopStart
    ) {
        val colorTransitionPosition = colorTransitionState[ColorTransitionFraction]

        val fillColorStart = androidx.compose.ui.graphics.lerp(
            colorsSchemes.fillColorScheme(false).backgroundColorStart,
            colorsSchemes.fillColorScheme(true).backgroundColorStart,
            colorTransitionPosition
        )
        val fillColorEnd = androidx.compose.ui.graphics.lerp(
            colorsSchemes.fillColorScheme(false).backgroundColorEnd,
            colorsSchemes.fillColorScheme(true).backgroundColorEnd,
            colorTransitionPosition
        )
        val borderColorStart = androidx.compose.ui.graphics.lerp(
            colorsSchemes.borderColorScheme(false).foregroundColor,
            colorsSchemes.borderColorScheme(true).foregroundColor,
            colorTransitionPosition
        )
        val borderColorEnd = androidx.compose.ui.graphics.lerp(
            colorsSchemes.borderColorScheme(false).foregroundColor,
            colorsSchemes.borderColorScheme(true).foregroundColor,
            colorTransitionPosition
        )
        val textColor = androidx.compose.ui.graphics.lerp(
            colorsSchemes.textColorScheme(false).foregroundColor,
            colorsSchemes.textColorScheme(true).foregroundColor,
            colorTransitionPosition
        )

        // TODO: figure out how to not create a new color scheme object on every
        // redraw
        val currBackgroundColorScheme = BaseColorScheme(
            displayName = "dummy",
            backgroundStart = fillColorStart,
            backgroundEnd = fillColorEnd,
            foreground = textColor
        )
        val currBorderColorScheme = BaseColorScheme(
            displayName = "dummy",
            backgroundStart = borderColorStart,
            backgroundEnd = borderColorEnd,
            foreground = textColor
        )
        val fillPainter = MosaicSkin.painters.fillPainter
        val borderPainter = MosaicSkin.painters.borderPainter

        Canvas(modifier.matchParentSize().padding(2.dp)) {
            val width = this.size.width
            val height = this.size.height

            val outline = shape.createOutline(Size(width, height), this)

            fillPainter.paintContourBackground(
                this, this.size, outline, currBackgroundColorScheme
            )

            borderPainter.paintBorder(
                this, this.size, outline, null, currBorderColorScheme
            )
        }
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
                children = content
            )
        }
    }
}
