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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.AmbientTextColor
import org.pushingpixels.mosaic.MosaicSkin

private val CheckboxSize = 14.dp
private val CheckMarkDrawFraction = FloatPropKey()

interface CheckBoxColors {
    @Composable
    fun backgroundColor(selected: Boolean): Color

    @Composable
    fun borderColor(selected: Boolean): Color

    @Composable
    fun markColor(selected: Boolean): Color

    @Composable
    fun textColor(): Color
}

@Composable
fun defaultCheckBoxColors(
    backgroundColor: Color = MosaicSkin.colors.enabledBackground,
    selectedBackgroundColor: Color = MosaicSkin.colors.selectedBackground,
    borderColor: Color = MosaicSkin.colors.enabledForeground,
    selectedBorderColor: Color = MosaicSkin.colors.selectedForeground,
    markColor: Color = MosaicSkin.colors.enabledForeground,
    selectedMarkColor: Color = MosaicSkin.colors.selectedForeground,
    textColor: Color = MosaicSkin.colors.enabledForeground
): CheckBoxColors {
    val clock = AnimationClockAmbient.current.asDisposableClock()
    return remember(
        backgroundColor, selectedBackgroundColor,
        borderColor, selectedBorderColor,
        markColor, selectedMarkColor,
        textColor,
        clock
    ) {
        DefaultCheckBoxColors(
            backgroundColor = backgroundColor,
            selectedBackgroundColor = selectedBackgroundColor,
            borderColor = borderColor,
            selectedBorderColor = selectedBorderColor,
            markColor = markColor,
            selectedMarkColor = selectedMarkColor,
            textColor = textColor,
            clock = clock
        )
    }
}

private class DefaultCheckBoxColors(
    private val backgroundColor: Color,
    private val selectedBackgroundColor: Color,
    private val borderColor: Color,
    private val selectedBorderColor: Color,
    private val markColor: Color,
    private val selectedMarkColor: Color,
    private val textColor: Color,
    private val clock: AnimationClockObservable
) : CheckBoxColors {

    private val animatedBackgroundColorTracker = LazyAnimatedValue<Color, AnimationVector4D> { target ->
        AnimatedValueModel(target, (Color.VectorConverter)(target.colorSpace), clock)
    }

    private val animatedMarkColorTracker = LazyAnimatedValue<Color, AnimationVector4D> { target ->
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
            animatedBackgroundColor.animateTo(
                target,
                tween(durationMillis = MosaicSkin.animationConfig.regular)
            )
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
            animatedBorderColor.animateTo(
                target,
                tween(durationMillis = MosaicSkin.animationConfig.regular)
            )
        }

        return animatedBorderColor.value
    }

    @Composable
    override fun markColor(selected: Boolean): Color {
        return selectedMarkColor
    }

    @Composable
    override fun textColor(): Color {
        return textColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultCheckBoxColors

        if (backgroundColor != other.backgroundColor) return false
        if (selectedBackgroundColor != other.selectedBackgroundColor) return false
        if (borderColor != other.borderColor) return false
        if (selectedBorderColor != other.selectedBorderColor) return false
        if (markColor != other.markColor) return false
        if (selectedMarkColor != other.selectedMarkColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + selectedBackgroundColor.hashCode()
        result = 31 * result + borderColor.hashCode()
        result = 31 * result + selectedBorderColor.hashCode()
        result = 31 * result + markColor.hashCode()
        result = 31 * result + selectedMarkColor.hashCode()
        return result
    }
}

@Composable
fun CheckMarkTransitionDefinition(duration: Int) : TransitionDefinition<Boolean> {
    return transitionDefinition<Boolean> {
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

@Composable
fun MosaicCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = MosaicSkin.shapes.small,
    colors: CheckBoxColors = defaultCheckBoxColors(),
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val checkedState = remember { mutableStateOf(checked) }

    // Transition for animating the alpha channel of the checkbox mark
    val transitionState = transition(
        definition = CheckMarkTransitionDefinition(MosaicSkin.animationConfig.short),
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
        val fillColor = colors.backgroundColor(checkedState.value)
        val borderColor = colors.borderColor(checkedState.value)
        val markColor = colors.markColor(checkedState.value)

        Canvas(modifier.wrapContentSize(Alignment.Center).size(CheckboxSize)) {
            val width = this.size.width
            val height = this.size.height
            val oneDp = 1.dp.toPx()
            val outerStroke = 1.5.dp.toPx()

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

            // Draw the checkbox mark with the alpha that corresponds to the current
            // selection and potential transition
            val path = Path()
            path.moveTo(0.22f * width, 0.45f * height)
            path.lineTo(0.45f * width, 0.7f * height)
            path.lineTo(0.73f * width, 0.25f * height)

            drawPath(
                path = path,
                color = markColor.copy(alpha = transitionState[CheckMarkDrawFraction]),
                style = Stroke(width = outerStroke)
            )
        }
        // Unlike buttons, the rest of the content should ignore (at least for now)
        // the selected state of the checkbox for drawing the text
        Providers(AmbientTextColor provides colors.textColor()) {
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
