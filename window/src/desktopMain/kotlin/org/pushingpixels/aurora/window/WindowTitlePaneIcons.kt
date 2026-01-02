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
package org.pushingpixels.aurora.window

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.theming.ContainerColorTokens
import kotlin.math.roundToInt

internal fun drawCloseIcon(
    drawScope: DrawScope,
    iconSize: Dp,
    colorTokens: ContainerColorTokens
) {
    with(drawScope) {
        val start = iconSize.toPx() / 4.0f
        val end = iconSize.toPx() * 0.75f

        drawLine(
            color = colorTokens.complementaryOnContainer,
            start = Offset(start, start),
            end = Offset(end, end),
            strokeWidth = 3.75f * density,
            cap = StrokeCap.Round,
            alpha = 0.4f,
        )
        drawLine(
            color = colorTokens.complementaryOnContainer,
            start = Offset(start, end),
            end = Offset(end, start),
            strokeWidth = 3.75f * density,
            cap = StrokeCap.Round,
            alpha = 0.4f,
        )

        drawLine(
            color = colorTokens.onContainer,
            start = Offset(start, start),
            end = Offset(end, end),
            strokeWidth = 1.5f * density,
            cap = StrokeCap.Round
        )
        drawLine(
            color = colorTokens.onContainer,
            start = Offset(start, end),
            end = Offset(end, start),
            strokeWidth = 1.5f * density,
            cap = StrokeCap.Round
        )
    }
}

internal fun drawMinimizeIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
    with(drawScope) {
        val start = (iconSize.toPx() * 0.25f).roundToInt().toFloat()
        val end = (iconSize.toPx() * 0.75f).roundToInt().toFloat()
        val extra = density
        drawRect(
            color = colorTokens.complementaryOnContainer,
            topLeft = Offset(start - extra, (end - 1.5f * density).toInt().toFloat() - extra),
            size = Size(end - start + 2 * extra, (2.5f * density).toInt().toFloat() + 2 * extra),
            style = Fill,
            alpha = 0.4f,
        )
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(start, (end - 1.5f * density).toInt().toFloat()),
            size = Size(end - start, (2.5f * density).toInt().toFloat()),
            style = Fill
        )
    }
}

internal fun drawRestoreIcon(
    drawScope: DrawScope,
    iconSize: Dp,
    colorTokens: ContainerColorTokens
) {
    with(drawScope) {
        val start = (iconSize.toPx() / 4.0f - density).roundToInt().toFloat()
        val end = (iconSize.toPx() - start).roundToInt().toFloat()
        val smallSquareSize = (end - start - 3.0f * density).roundToInt().toFloat()

        val mainStartX = start
        val mainStartY = end - smallSquareSize
        val mainEndX = mainStartX + smallSquareSize
        val mainEndY = mainStartY + smallSquareSize
        val secondaryStartX = (mainStartX + 3.0f * density).toInt().toFloat()
        val secondaryEndX = secondaryStartX + smallSquareSize
        val secondaryStartY = (mainStartY - 3.0f * density).toInt().toFloat()
        val secondaryEndY = secondaryStartY + smallSquareSize

        val secondary = Path()
        secondary.moveTo(mainStartX, mainStartY)
        // top first
        secondary.lineTo(mainEndX, mainStartY)
        secondary.lineTo(mainEndX, mainStartY + density)
        // top second (for a thicker overall top line)
        secondary.lineTo(mainStartX, mainStartY + density)
        // left
        secondary.lineTo(mainStartX, mainEndY)
        // bottom
        secondary.lineTo(mainEndX, mainEndY)
        // right
        secondary.lineTo(mainEndX, mainStartY + density)

        // top (thicker)
        secondary.moveTo(secondaryEndX, secondaryStartY)
        secondary.lineTo(secondaryStartX, secondaryStartY)
        secondary.lineTo(secondaryStartX, secondaryStartY + density)
        secondary.lineTo(secondaryEndX, secondaryStartY + density)
        // right
        secondary.lineTo(secondaryEndX, secondaryEndY)
        // bottom (partial)
        secondary.lineTo(secondaryEndX - 2 * density, secondaryEndY)

        drawPath(
            path = secondary,
            color = colorTokens.complementaryOnContainer,
            style = Stroke(
                width = 3.0f * density,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            ),
            alpha = 0.4f
        )

        // "Main" rectangle
        // top (thicker)
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(mainStartX, mainStartY),
            size = Size(smallSquareSize, (2.0f * density).toInt().toFloat()),
            style = Fill
        )
        // left
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(mainStartX, mainStartY),
            size = Size(density, smallSquareSize),
            style = Fill
        )
        // right
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset((mainStartX + smallSquareSize - density).toInt().toFloat(), mainStartY),
            size = Size(density, smallSquareSize),
            style = Fill
        )
        // bottom
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(mainStartX, (mainStartY + smallSquareSize - density).toInt().toFloat()),
            size = Size(smallSquareSize, density.toInt().toFloat()),
            style = Fill
        )

        // "Secondary rectangle"
        // top (thicker)
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(secondaryStartX, secondaryStartY),
            size = Size(smallSquareSize, (2.0f * density).toInt().toFloat()),
            style = Fill
        )
        // right
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset((secondaryStartX + smallSquareSize - density).toInt().toFloat(), secondaryStartY),
            size = Size(density, smallSquareSize),
            style = Fill
        )
        // bottom (partial)
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(
                (mainStartX + smallSquareSize + density).toInt().toFloat(),
                (secondaryStartY + smallSquareSize - density).toInt().toFloat()
            ),
            size = Size((2.0f * density).toInt().toFloat(), density.toInt().toFloat()),
            style = Fill
        )
    }
}

internal fun drawMaximizeIcon(
    drawScope: DrawScope,
    iconSize: Dp,
    colorTokens: ContainerColorTokens
) {
    with(drawScope) {

        val start = (iconSize.toPx() / 4.0f - density).roundToInt().toFloat()
        val end = (iconSize.toPx() - start).roundToInt().toFloat()

        val secondary = Path()
        secondary.moveTo(start, start)
        // top first
        secondary.lineTo(end, start)
        secondary.lineTo(end, start + density)
        // top second (for a thicker overall top line)
        secondary.lineTo(start, start + density)
        // left
        secondary.lineTo(start, end)
        // bottom
        secondary.lineTo(end, end)
        // right
        secondary.lineTo(end, start + density)

        drawPath(
            path = secondary,
            color = colorTokens.complementaryOnContainer,
            style = Stroke(
                width = 3.0f * density,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            ),
            alpha = 0.4f
        )

        // top (thicker)
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(start, start),
            size = Size(end - start, 2.0f * density),
            style = Fill
        )
        // left
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(start, start),
            size = Size(density, end - start),
            style = Fill
        )
        // right
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(end - density, start),
            size = Size(density, end - start),
            style = Fill
        )
        // bottom
        drawRect(
            color = colorTokens.onContainer,
            topLeft = Offset(start, end - density),
            size = Size(end - start, density),
            style = Fill
        )
    }
}






