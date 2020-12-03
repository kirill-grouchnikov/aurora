/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.utils.ColorSchemeBitmapFilter

/**
 * Implementation of [AuroraDecorationPainter] that uses an image source to paint on
 * decoration areas.
 *
 * @author Kirill Grouchnikov
 */
abstract class ImageWrapperDecorationPainter(
    val originalTile: ImageBitmap,
    val textureAlpha: Float = 0.2f
) : AuroraDecorationPainter {
    /**
     * The base decoration painter - the colorized image tiles are painted over the painting of this
     * painter. Can be `null`.
     */
    private var baseDecorationPainter: AuroraDecorationPainter? = null

    /**
     * Map of colorized tiles.
     */
    private var colorizedTileMap = LinkedHashMap<String, ImageBitmap>(10)

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        with(drawScope) {
            if (baseDecorationPainter != null) {
                baseDecorationPainter!!.paintDecorationArea(
                    drawScope = this,
                    decorationAreaType = decorationAreaType,
                    componentSize = componentSize,
                    outline = outline,
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
     *
     * @param g
     * Graphic context.
     * @param comp
     * Component.
     * @param tileScheme
     * Scheme for the tile colorization.
     * @param offsetTextureX
     * X offset for the tiling.
     * @param offsetTextureY
     * Y offset for the tiling.
     * @param width
     * Width of the tiling region.
     * @param height
     * Height of the tiling region.
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
            val colorizedTile = getColorizedTile(tileScheme)
            val tileWidth = colorizedTile.width
            val tileHeight = colorizedTile.height
            offsetTextureX %= tileWidth
            offsetTextureY %= tileHeight
            var currTileTop = -offsetTextureY
            do {
                var currTileLeft = -offsetTextureX
                do {
                    drawImage(
                        image = colorizedTile,
                        alpha = textureAlpha,
                        topLeft = Offset(currTileLeft, currTileTop)
                    )
                    currTileLeft += tileWidth
                } while (currTileLeft < componentSize.width)
                currTileTop += tileHeight
            } while (currTileTop < componentSize.height)
        }
    }

    /**
     * Returns a colorized image tile.
     *
     * @param scheme Color scheme for the colorization.
     * @return Colorized tile.
     */
    private fun getColorizedTile(scheme: AuroraColorScheme): ImageBitmap {
        var result = colorizedTileMap[scheme.displayName]
        if (result == null) {
            result = ColorSchemeBitmapFilter(scheme = scheme, originalBrightnessFactor = 0.0f, alpha = 1.0f)
                .filter(source = originalTile)

            // Cache the bitmap
            colorizedTileMap[scheme.displayName] = result
        }
        return result
    }
}