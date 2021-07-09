/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon

class ColorSolidIcon(var _width: Dp, var _height: Dp, val color: Color) : AuroraIcon {
    override fun getHeight(): Dp {
        return _height
    }

    override fun getWidth(): Dp {
        return _width
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        this._width = width
        this._height = height
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        // no-op
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            drawRect(
                color = color,
                topLeft = Offset.Zero,
                size = Size(_width.toPx(), _height.toPx()),
                style = Fill
            )
        }
    }

    companion object {
        fun factory(color: Color): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return ColorSolidIcon(16.dp, 16.dp, color)
                }
            }
        }
    }
}

class ColorGradientIcon(
    var _width: Dp,
    var _height: Dp,
    val colorTop: Color,
    val colorBottom: Color
) : AuroraIcon {
    override fun getHeight(): Dp {
        return _height
    }

    override fun getWidth(): Dp {
        return _width
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        this._width = width
        this._height = height
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        // no-op
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            drawRect(
                brush = Brush.verticalGradient(
                    0.0f to colorTop,
                    1.0f to colorBottom,
                    startY = 0.0f,
                    endY = size.height,
                    tileMode = TileMode.Clamp
                ),
                topLeft = Offset.Zero,
                size = Size(_width.toPx(), _height.toPx()),
                style = Fill
            )
        }
    }

    companion object {
        fun factory(colorTop: Color, colorBottom: Color): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return ColorGradientIcon(16.dp, 16.dp, colorTop, colorBottom)
                }
            }
        }
    }
}
