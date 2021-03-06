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
    val textureAlpha: Float = 0.2f,
    val baseDecorationPainter: AuroraDecorationPainter? = null
) : AuroraDecorationPainter {
    /** Map of colorized tiles. */
    private var colorizedTileMap = LinkedHashMap<String, ImageBitmap>(10)

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