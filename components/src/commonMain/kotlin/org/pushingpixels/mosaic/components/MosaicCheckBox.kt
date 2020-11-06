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
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.AmbientTextColor
import org.pushingpixels.mosaic.MosaicSkin

private val CheckboxSize = 14.dp

interface CheckBoxColors {
    fun backgroundColor(selected: Boolean): Color

    fun borderColor(selected: Boolean): Color

    fun markColor(selected: Boolean): Color
}

@Composable
fun defaultCheckBoxColors(
    backgroundColor: Color = MosaicSkin.colors.enabledBackground,
    selectedBackgroundColor: Color = MosaicSkin.colors.selectedBackground,
    borderColor: Color = MosaicSkin.colors.enabledForeground,
    selectedBorderColor: Color = MosaicSkin.colors.selectedForeground,
    markColor: Color = MosaicSkin.colors.enabledForeground,
    selectedMarkColor: Color = MosaicSkin.colors.selectedForeground
): CheckBoxColors = DefaultCheckBoxColors(
    backgroundColor, selectedBackgroundColor,
    borderColor, selectedBorderColor,
    markColor, selectedMarkColor
)

private class DefaultCheckBoxColors(
    private val backgroundColor: Color,
    private val selectedBackgroundColor: Color,
    private val borderColor: Color,
    private val selectedBorderColor: Color,
    private val markColor: Color,
    private val selectedMarkColor: Color
) : CheckBoxColors {
    override fun backgroundColor(selected: Boolean): Color {
        return if (selected) selectedBackgroundColor else backgroundColor
    }

    override fun borderColor(selected: Boolean): Color {
        return if (selected) selectedBorderColor else borderColor
    }

    override fun markColor(selected: Boolean): Color {
        return if (selected) selectedMarkColor else markColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultCheckBoxColors

        if (backgroundColor != other.backgroundColor) return false
        if (selectedBackgroundColor != other.selectedBorderColor) return false
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
fun MosaicCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = MosaicSkin.shapes.small,
    colors: CheckBoxColors = defaultCheckBoxColors(),
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val checkedState = remember { mutableStateOf(checked) }

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
        Canvas(modifier.wrapContentSize(Alignment.Center).size(CheckboxSize)) {
            val fillColor = colors.backgroundColor(checkedState.value)
            val borderColor = colors.borderColor(checkedState.value)
            val markColor = colors.markColor(checkedState.value)

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

            if (checkedState.value) {
                val path = Path()
                path.moveTo(0.22f * width, 0.45f * height)
                path.lineTo(0.45f * width, 0.7f * height)
                path.lineTo(0.73f * width, 0.25f * height)

                drawPath(
                    path = path,
                    color = markColor,
                    style = Stroke(width = outerStroke)
                )
            }
        }
        // Unlike buttons, the rest of the content should ignore (at least for now)
        // the selected state of the checkbox for drawing the text
        Providers(AmbientTextColor provides colors.borderColor(false)) {
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
