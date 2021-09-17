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
    val tileGenerator: (AuroraColorScheme) -> Paint,
    val tileSize: Size,
    val baseDecorationPainter: AuroraDecorationPainter? = null
) : AuroraDecorationPainter {
    private val tiles = hashMapOf<String, Paint>()

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
                tileArea(
                    drawScope = this,
                    componentSize = componentSize,
                    offsetTexture = offsetFromRoot,
                    tileScheme = colorScheme
                )
            }
        }
    }

    /**
     * Tiles the specified area with colorized version of the image tile. This is called after the
     * [.baseDecorationPainter] has painted the area. This method should respect the current
     * [.textureAlpha] value.
     */
    private fun tileArea(
        drawScope: DrawScope,
        componentSize: Size,
        offsetTexture: Offset,
        tileScheme: AuroraColorScheme
    ) {
        var offsetTextureX = offsetTexture.x
        var offsetTextureY = offsetTexture.y

        with(drawScope) {
            var colorizedPaint = tiles[tileScheme.displayName]
            if (colorizedPaint == null) {
                colorizedPaint = tileGenerator.invoke(tileScheme)
                tiles[tileScheme.displayName] = colorizedPaint
            }

            val tileWidth = tileSize.width
            val tileHeight = tileSize.height
            offsetTextureX %= tileWidth
            offsetTextureY %= tileHeight
            var currTileTop = -offsetTextureY
            this.drawIntoCanvas {
                val nativeCanvas = it.nativeCanvas
                do {
                    var currTileLeft = -offsetTextureX
                    do {
                        nativeCanvas.save()
                        val tileRect = Rect.makeLTRB(
                            l = currTileLeft, t = currTileTop,
                            r = currTileLeft + tileWidth, b = currTileTop + tileHeight
                        )
                        nativeCanvas.clipRect(tileRect)
                        nativeCanvas.drawRect(r = tileRect, paint = colorizedPaint)
                        nativeCanvas.restore()

                        currTileLeft += tileWidth
                    } while (currTileLeft < componentSize.width)
                    currTileTop += tileHeight
                } while (currTileTop < componentSize.height)
            }
        }
    }
}