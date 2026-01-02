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
package org.pushingpixels.aurora.demo

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.painter.Painter

class ColorSolidIcon(val color: Color) : Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = size,
            style = Fill
        )
    }
}

class ColorGradientIcon(
    val colorTop: Color,
    val colorBottom: Color
) : Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        drawRect(
            brush = Brush.verticalGradient(
                0.0f to colorTop,
                1.0f to colorBottom,
                startY = 0.0f,
                endY = size.height,
                tileMode = TileMode.Clamp
            ),
            topLeft = Offset.Zero,
            size = size,
            style = Fill
        )
    }
}

class DecoratedIcon(val main: Painter, val decoration: Painter) : Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        with (main) {
            draw(size, 1.0f, null)
        }
        with (decoration) {
            draw(size, 1.0f, null)
        }
    }
}

class EmptyIcon: Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
    }
}

