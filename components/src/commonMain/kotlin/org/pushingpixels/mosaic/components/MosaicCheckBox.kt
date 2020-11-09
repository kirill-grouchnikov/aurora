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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.AmbientTextColor
import org.pushingpixels.mosaic.MosaicSkin
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme

private val CheckboxSize = 14.dp
private val CheckMarkDrawFraction = FloatPropKey()
private val ColorTransitionFraction = FloatPropKey()

interface CheckBoxColors {
    @Composable
    fun backgroundColorScheme(selected: Boolean): MosaicColorScheme

    @Composable
    fun borderColorScheme(selected: Boolean): MosaicColorScheme

    @Composable
    fun markColor(selected: Boolean): Color

    @Composable
    fun textColor(): Color
}

@Composable
fun defaultCheckBoxColors(
    backgroundColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedBackgroundColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
    borderColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedBorderColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
    markColor: Color = MosaicSkin.colorSchemes.enabled.foregroundColor,
    selectedMarkColor: Color = MosaicSkin.colorSchemes.selected.foregroundColor,
    textColor: Color = MosaicSkin.colorSchemes.enabled.foregroundColor
): CheckBoxColors {
    return DefaultCheckBoxColors(
        backgroundColorScheme = backgroundColorScheme,
        selectedBackgroundColorScheme = selectedBackgroundColorScheme,
        borderColorScheme = borderColorScheme,
        selectedBorderColorScheme = selectedBorderColorScheme,
        markColor = markColor,
        selectedMarkColor = selectedMarkColor,
        textColor = textColor
    )
}

private class DefaultCheckBoxColors(
    private val backgroundColorScheme: MosaicColorScheme,
    private val selectedBackgroundColorScheme: MosaicColorScheme,
    private val borderColorScheme: MosaicColorScheme,
    private val selectedBorderColorScheme: MosaicColorScheme,
    private val markColor: Color,
    private val selectedMarkColor: Color,
    private val textColor: Color
) : CheckBoxColors {
    @Composable
    override fun backgroundColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedBackgroundColorScheme else backgroundColorScheme
    }

    @Composable
    override fun borderColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedBorderColorScheme else borderColorScheme
    }

    @Composable
    override fun markColor(selected: Boolean): Color {
        return selectedMarkColor
    }

    @Composable
    override fun textColor(): Color {
        return textColor
    }
}

@Composable
private fun getCheckMarkTransitionDefinition(duration: Int): TransitionDefinition<Boolean> {
    return transitionDefinition {
        state(false) {
            this[CheckMarkDrawFraction] = 0.0f
        }

        state(true) {
            this[CheckMarkDrawFraction] = 1.0f
        }

        transition(false to true) {
            CheckMarkDrawFraction using tween(durationMillis = duration)
        }

        transition(true to false) {
            CheckMarkDrawFraction using tween(durationMillis = duration)
        }
    }
}

// This will be initialized on first usage using the getCheckMarkTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var CheckMarkTransitionDefinition: TransitionDefinition<Boolean>

@Composable
private fun getColorTransitionDefinition(duration: Int): TransitionDefinition<Boolean> {
    return transitionDefinition {
        state(false) {
            this[ColorTransitionFraction] = 0.0f
        }

        state(true) {
            this[ColorTransitionFraction] = 1.0f
        }

        transition(false to true) {
            ColorTransitionFraction using tween(durationMillis = duration)
        }

        transition(true to false) {
            ColorTransitionFraction using tween(durationMillis = duration)
        }
    }
}

// This will be initialized on first usage using the getColorTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var ColorTransitionDefinition: TransitionDefinition<Boolean>

@Composable
fun MosaicCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = MosaicSkin.shapes.small,
    colorsSchemes: CheckBoxColors = defaultCheckBoxColors(),
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val checkedState = remember { mutableStateOf(checked) }

    // Transition for animating the alpha channel of the checkbox mark
    if (!::CheckMarkTransitionDefinition.isInitialized) {
        CheckMarkTransitionDefinition =
            getCheckMarkTransitionDefinition(MosaicSkin.animationConfig.short)
    }
    val markAlphaTransitionState = transition(
        definition = CheckMarkTransitionDefinition,
        initState = checkedState.value,
        toState = checkedState.value
    )

    // Transition for animating the colors of the checkbox
    if (!::ColorTransitionDefinition.isInitialized) {
        ColorTransitionDefinition =
            getColorTransitionDefinition(MosaicSkin.animationConfig.regular)
    }
    val colorTransitionState = transition(
        definition = ColorTransitionDefinition,
        initState = checkedState.value,
        toState = checkedState.value
    )

    // The toggleable modifier is set on the checkbox mark, as well as on the
    // content so that the whole thing is clickable to toggle the control.
    Row(
        modifier = modifier.toggleable(
            value = checkedState.value,
            onValueChange = {
                checkedState.value = it
                onCheckedChange.invoke(checkedState.value)
            },
            indication = null
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val colorTransitionPosition = colorTransitionState[ColorTransitionFraction]

        val fillColor = androidx.compose.ui.graphics.lerp(
            colorsSchemes.backgroundColorScheme(false).backgroundColorStart,
            colorsSchemes.backgroundColorScheme(true).backgroundColorStart,
            colorTransitionPosition
        )
        val borderColor = androidx.compose.ui.graphics.lerp(
            colorsSchemes.borderColorScheme(false).foregroundColor,
            colorsSchemes.borderColorScheme(true).foregroundColor,
            colorTransitionPosition
        )
        val markColor = colorsSchemes.markColor(true)

        Canvas(modifier.wrapContentSize(Alignment.Center).size(CheckboxSize)) {
            val width = this.size.width
            val height = this.size.height
            val outerStroke = 1.5.dp.toPx()

            val toDraw = shape.createOutline(Size(width, height), this)

            drawOutline(
                outline = toDraw,
                style = Fill,
                color = fillColor
            )

            drawOutline(
                outline = toDraw,
                style = Stroke(width = outerStroke),
                color = borderColor
            )

            // Draw the checkbox mark with the alpha that corresponds to the current
            // selection and potential transition
            val path = Path()
            path.moveTo(0.22f * width, 0.45f * height)
            path.lineTo(0.45f * width, 0.7f * height)
            path.lineTo(0.73f * width, 0.25f * height)

            drawPath(
                path = path,
                color = markColor.copy(alpha = markAlphaTransitionState[CheckMarkDrawFraction]),
                style = Stroke(width = outerStroke)
            )
        }
        // Unlike buttons, the rest of the content should ignore (at least for now)
        // the selected state of the checkbox for drawing the text
        Providers(AmbientTextColor provides colorsSchemes.textColor()) {
            Row(
                Modifier
                    .defaultMinSizeConstraints(
                        minWidth = 0.dp,
                        minHeight = CheckboxSize
                    )
                    .padding(4.dp, 10.dp, 4.dp, 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                children = content
            )
        }
    }
}
