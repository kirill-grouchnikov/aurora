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
package org.pushingpixels.aurora.skin.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import org.jetbrains.skia.Paint
import org.jetbrains.skia.Rect
import org.pushingpixels.aurora.skin.DecorationAreaType
import org.pushingpixels.aurora.skin.colorscheme.AuroraColorScheme

/**
 * Implementation of [AuroraDecorationPainter] that uses an image source to paint on
 * decoration areas.
 *
 * @author Kirill Grouchnikov
 */
abstract class ImageWrapperDecorationPainter(
    val paintGenerator: (AuroraColorScheme) -> Paint,
    val baseDecorationPainter: AuroraDecorationPainter? = null
) : AuroraDecorationPainter {
    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        with(drawScope) {
            if (baseDecorationPainter != null) {
                baseDecorationPainter.paintDecorationArea(
                    drawScope = this,
                    decorationAreaType = decorationAreaType,
                    componentSize = componentSize,
                    outline = outline,
                    rootSize = rootSize,
                    offsetFromRoot = offsetFromRoot,
                    colorScheme = colorScheme
                )
            } else {
                drawOutline(
                    outline = outline,
                    style = Fill,
                    color = colorScheme.midColor
                )
            }

            val clipPath = Path()
            clipPath.addOutline(outline)
            clipPath(path = clipPath) {
                val colorizedPaint = paintGenerator.invoke(colorScheme)
                this.drawIntoCanvas {
                    val nativeCanvas = it.nativeCanvas
                    val tileRect = Rect.makeLTRB(
                        l = -offsetFromRoot.x, t = -offsetFromRoot.y,
                        r = componentSize.width, b = componentSize.height
                    )
                    nativeCanvas.clipRect(tileRect)
                    nativeCanvas.drawRect(r = tileRect, paint = colorizedPaint)
                }
            }
        }
    }
}