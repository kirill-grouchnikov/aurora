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
package org.pushingpixels.mosaic.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import org.jetbrains.skija.ColorAlphaType
import org.jetbrains.skija.ImageInfo
import org.pushingpixels.mosaic.DecorationAreaType
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme
import org.pushingpixels.mosaic.utils.colorize

/**
 * Implementation of [MosaicDecorationPainter] that uses an image source to paint on
 * decoration areas.
 *
 * @author Kirill Grouchnikov
 */
abstract class ImageWrapperDecorationPainter(
    val originalTile: ImageBitmap,
    val textureAlpha: Float = 0.2f
) : MosaicDecorationPainter {
    /**
     * The base decoration painter - the colorized image tiles are painted over the painting of this
     * painter. Can be `null`.
     */
    protected var baseDecorationPainter: MosaicDecorationPainter? = null

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
        colorScheme: MosaicColorScheme
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
        tileScheme: MosaicColorScheme
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
     * @param scheme
     * Color scheme for the colorization.
     * @return Colorized tile.
     */
    protected fun getColorizedTile(scheme: MosaicColorScheme): ImageBitmap {
        var result = colorizedTileMap[scheme.displayName]
        if (result == null) {
            val tileWidth = originalTile.width
            val tileHeight = originalTile.height

            // Get pixels from the original tile
            val originalBitmap = originalTile.asDesktopBitmap()
            val originalPixels = originalBitmap.readPixels(
                ImageInfo.makeN32(tileWidth, tileHeight, originalBitmap.alphaType),
                tileWidth.toLong() * originalBitmap.bytesPerPixel, 0, 0
            )

            // Colorize the pixels
            val colorizedPixels = colorize(
                width = tileWidth,
                height = tileHeight,
                src = originalPixels!!,
                scheme = scheme,
                originalBrightnessFactor = 0.0f,
                alpha = 1.0f
            )

            // And convert them to an ImageBitmap
            val result = ImageBitmap(width = tileWidth, height = tileHeight)
            val skijaBitmap = result.asDesktopBitmap()
            skijaBitmap.installPixels(
                ImageInfo.makeN32(tileWidth, tileHeight, ColorAlphaType.UNPREMUL),
                colorizedPixels, 4L * tileWidth
            )

            // Cache the bitmap
            colorizedTileMap[scheme.displayName] = result
//            val tileBi: BufferedImage = SubstanceCoreUtilities.getBlankImage(
//                (tileWidth / scaleFactor).toInt(),
//                (tileHeight / scaleFactor).toInt()
//            )
//            val tile2D = tileBi.createGraphics()
//            tile2D.drawImage(
//                originalTile, 0, 0, (tileWidth / scaleFactor).toInt(),
//                (tileHeight / scaleFactor).toInt(), null
//            )
//            tile2D.dispose()
//            result = SubstanceImageCreator.getColorSchemeImage(tileBi, scheme, 0.0f, 1.0f)
//            colorizedTileMap[scheme.getDisplayName()] = result
        }
        return colorizedTileMap[scheme.displayName]!!
    }
}