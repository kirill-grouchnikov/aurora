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

import androidx.compose.animation.AnimatedValueModel
import androidx.compose.animation.VectorConverter
import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.AmbientTextColor
import org.pushingpixels.mosaic.MosaicSkin

enum class ButtonState {
    IDLE, SELECTED
}

interface ButtonColors {
    @Composable
    fun backgroundColor(selected: Boolean): Color

    @Composable
    fun borderColor(selected: Boolean): Color

    @Composable
    fun textColor(selected: Boolean): Color
}

@Composable
fun defaultButtonColors(
    backgroundColor: Color = MosaicSkin.colors.enabledBackground,
    selectedBackgroundColor: Color = MosaicSkin.colors.selectedBackground,
    borderColor: Color = MosaicSkin.colors.enabledForeground,
    selectedBorderColor: Color = MosaicSkin.colors.selectedForeground,
    textColor: Color = MosaicSkin.colors.enabledForeground,
    selectedTextColor: Color = MosaicSkin.colors.selectedForeground,
): ButtonColors {
    val clock = AnimationClockAmbient.current.asDisposableClock()
    return remember(
        backgroundColor, selectedBackgroundColor,
        borderColor, selectedBorderColor,
        textColor, selectedTextColor,
        clock
    ) {
        DefaultButtonColors(
            backgroundColor = backgroundColor,
            selectedBackgroundColor = selectedBackgroundColor,
            borderColor = borderColor,
            selectedBorderColor = selectedBorderColor,
            textColor = textColor,
            selectedTextColor = selectedTextColor,
            clock = clock
        )
    }
}

internal class LazyAnimatedValue<T, V : AnimationVector>(
    private val factory: (target: T) -> AnimatedValue<T, V>
) {
    private var animatedValue: AnimatedValue<T, V>? = null

    fun animatedValueForTarget(targetValue: T): AnimatedValue<T, V> {
        return animatedValue ?: factory(targetValue).also { animatedValue = it }
    }
}

private class DefaultButtonColors(
    private val backgroundColor: Color,
    private val selectedBackgroundColor: Color,
    private val borderColor: Color,
    private val selectedBorderColor: Color,
    private val textColor: Color,
    private val selectedTextColor: Color,
    private val clock: AnimationClockObservable
) : ButtonColors {

    private val animatedBackgroundColorTracker = LazyAnimatedValue<Color, AnimationVector4D> { target ->
        AnimatedValueModel(target, (Color.VectorConverter)(target.colorSpace), clock)
    }

    private val animatedTextColorTracker = LazyAnimatedValue<Color, AnimationVector4D> { target ->
        AnimatedValueModel(target, (Color.VectorConverter)(target.colorSpace), clock)
    }

    private val animatedBorderColorTracker = LazyAnimatedValue<Color, AnimationVector4D> { target ->
        AnimatedValueModel(target, (Color.VectorConverter)(target.colorSpace), clock)
    }

    @Composable
    override fun backgroundColor(selected: Boolean): Color {
        val target = if (selected) {
            selectedBackgroundColor
        } else {
            backgroundColor
        }

        val animatedBackgroundColor = animatedBackgroundColorTracker.animatedValueForTarget(target)

        if (animatedBackgroundColor.targetValue != target) {
            animatedBackgroundColor.animateTo(target,
                tween(durationMillis = MosaicSkin.animationConfig.regular))
        }

        return animatedBackgroundColor.value
    }

    @Composable
    override fun borderColor(selected: Boolean): Color {
        val target = if (selected) {
            selectedBorderColor
        } else {
            borderColor
        }

        val animatedBorderColor = animatedBorderColorTracker.animatedValueForTarget(target)

        if (animatedBorderColor.targetValue != target) {
            animatedBorderColor.animateTo(target,
                tween(durationMillis = MosaicSkin.animationConfig.regular))
        }

        return animatedBorderColor.value
    }

    @Composable
    override fun textColor(selected: Boolean): Color {
        val target = if (selected) {
            selectedTextColor
        } else {
            textColor
        }

        val animatedTextColor = animatedTextColorTracker.animatedValueForTarget(target)

        if (animatedTextColor.targetValue != target) {
            animatedTextColor.animateTo(target,
                tween(durationMillis = MosaicSkin.animationConfig.regular))
        }

        return animatedTextColor.value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultButtonColors

        if (backgroundColor != other.backgroundColor) return false
        if (selectedBackgroundColor != other.selectedBackgroundColor) return false
        if (borderColor != other.borderColor) return false
        if (selectedBorderColor != other.selectedBorderColor) return false
        if (textColor != other.textColor) return false
        if (selectedTextColor != other.selectedTextColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + selectedBackgroundColor.hashCode()
        result = 31 * result + borderColor.hashCode()
        result = 31 * result + selectedBorderColor.hashCode()
        result = 31 * result + textColor.hashCode()
        result = 31 * result + selectedTextColor.hashCode()
        return result
    }
}

@Composable
fun MosaicToggleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    shape: Shape = MosaicSkin.shapes.regular,
    colors: ButtonColors = defaultButtonColors(),
    content: @Composable RowScope.() -> Unit
) {
    val state = remember { InteractionState() }

    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

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
        val fillColor = colors.backgroundColor(buttonState.value == ButtonState.SELECTED)
        val borderColor = colors.borderColor(buttonState.value == ButtonState.SELECTED)
        val textColor = colors.textColor(buttonState.value == ButtonState.SELECTED)

        Canvas(modifier.matchParentSize().padding(2.dp)) {
            val width = this.size.width
            val height = this.size.height
            val oneDp = 1.dp.toPx()
            val outerStroke = 2.dp.toPx()

            drawRect(
                fillColor,
                topLeft = Offset(oneDp, oneDp),
                size = Size(width - 2 * oneDp, height - 2 * oneDp)
            )

            drawOutline(
                outline = shape.createOutline(Size(width, height), this),
                style = Stroke(width = outerStroke),
                color = borderColor
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
