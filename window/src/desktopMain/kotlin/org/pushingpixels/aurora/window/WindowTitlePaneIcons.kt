/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.window

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme

internal fun drawCloseIcon(
    drawScope: DrawScope,
    iconSize: Dp,
    scheme: AuroraColorScheme
) {
    with(drawScope) {
        val start = iconSize.toPx() / 4.0f
        val end = iconSize.toPx() * 0.75f

        drawLine(
            color = scheme.markColor,
            start = Offset(start, start),
            end = Offset(end, end),
            strokeWidth = 1.5f * density,
            cap = StrokeCap.Round
        )
        drawLine(
            color = scheme.markColor,
            start = Offset(start, end),
            end = Offset(end, start),
            strokeWidth = 1.5f * density,
            cap = StrokeCap.Round
        )
    }
}

internal fun drawMinimizeIcon(drawScope: DrawScope, iconSize: Dp, scheme: AuroraColorScheme) {
    with(drawScope) {
        val start = iconSize.toPx() * 0.25f
        val end = iconSize.toPx() * 0.75f
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(start, end - 1.5f * density),
            size = Size(end - start, 2.5f * density),
            style = Fill
        )
    }
}

internal fun drawRestoreIcon(
    drawScope: DrawScope,
    iconSize: Dp,
    scheme: AuroraColorScheme
) {
    with(drawScope) {
        val start = iconSize.toPx() / 4.0f - density
        val end = iconSize.toPx() - start
        val smallSquareSize = end - start - 3.0f * density

        // "Main" rectangle
        val mainStartX = start
        val mainStartY = end - smallSquareSize

        // top (thicker)
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(mainStartX, mainStartY),
            size = Size(smallSquareSize, 2.0f * density),
            style = Fill
        )
        // left
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(mainStartX, mainStartY),
            size = Size(density, smallSquareSize),
            style = Fill
        )
        // right
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(mainStartX + smallSquareSize - density, mainStartY),
            size = Size(density, smallSquareSize),
            style = Fill
        )
        // bottom
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(mainStartX, mainStartY + smallSquareSize - density),
            size = Size(smallSquareSize, density),
            style = Fill
        )

        // "Secondary rectangle"
        val secondaryStartX = mainStartX + 3.0f * density
        val secondaryStartY = mainStartY - 3.0f * density
        // top (thicker)
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(secondaryStartX, secondaryStartY),
            size = Size(smallSquareSize, 2.0f * density),
            style = Fill
        )
        // right
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(secondaryStartX + smallSquareSize - density, secondaryStartY),
            size = Size(density, smallSquareSize),
            style = Fill
        )
        // bottom (partial)
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(
                mainStartX + smallSquareSize + density,
                secondaryStartY + smallSquareSize - density
            ),
            size = Size(2.0f * density, density),
            style = Fill
        )
    }
}

internal fun drawMaximizeIcon(
    drawScope: DrawScope,
    iconSize: Dp,
    scheme: AuroraColorScheme
) {
    with(drawScope) {

        val start = iconSize.toPx() / 4.0f - density
        val end = iconSize.toPx() - start

        // top (thicker)
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(start, start),
            size = Size(end - start, 2.0f * density),
            style = Fill
        )
        // left
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(start, start),
            size = Size(density, end - start),
            style = Fill
        )
        // right
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(end - density, start),
            size = Size(density, end - start),
            style = Fill
        )
        // bottom
        drawRect(
            color = scheme.markColor,
            topLeft = Offset(start, end - density),
            size = Size(end - start, density),
            style = Fill
        )
    }
}






