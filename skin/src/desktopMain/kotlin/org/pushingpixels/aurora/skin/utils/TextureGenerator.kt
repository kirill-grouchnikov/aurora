/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.skin.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import org.jetbrains.skia.*
import org.pushingpixels.aurora.skin.colorscheme.AuroraColorScheme

fun getNoiseTile(scheme: AuroraColorScheme, width: Int, height: Int): ImageBitmap {
    val tileNoise = Bitmap()
    tileNoise.setImageInfo(ImageInfo(width, height, ColorType.BGRA_8888, ColorAlphaType.PREMUL))
    tileNoise.allocPixels()
    val noiseCanvas = Canvas(tileNoise)

    val paintNoise = Paint()
    // Fractal noise shader
    paintNoise.setShader(
        Shader.makeFractalNoise(
            baseFrequencyX = 0.45f,
            baseFrequencyY = 0.45f,
            numOctaves = 4,
            seed = 0.0f,
            tiles = arrayOf(ISize.make(width, height))
        )
    )
    // Composed color filter. Inner filter applies greyscale, and outer filter applies the colors of
    // the specified Aurora color scheme
    paintNoise.setColorFilter(
        ColorFilter.makeComposed(
            outer = getColorSchemeFilterSkia(scheme = scheme),
            inner = ColorFilter.makeMatrix(
                ColorMatrix(
                    0.21f, 0.72f, 0.07f, 0.0f, 0.0f,
                    0.21f, 0.72f, 0.07f, 0.0f, 0.0f,
                    0.21f, 0.72f, 0.07f, 0.0f, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f, 0.0f
                )
            )
        )
    )
    // Image filter to apply softening (to make the noise less sharp)
    paintNoise.imageFilter = ImageFilter.makeMatrixConvolution(
        3, 3,
        floatArrayOf(.08f, .08f, .08f, .08f, .38f, .08f, .08f, .08f, .08f),
        1f / 1.02f, 1f / 1.02f, 0, 0, FilterTileMode.REPEAT, true, null, null
    )
    noiseCanvas.drawRect(Rect.makeWH(width.toFloat(), height.toFloat()), paintNoise)
    val textureNoise = ImageBitmap(width = width, height = height)
    // Copy over the pixels from the noise bitmap
    textureNoise.asDesktopBitmap().installPixels(tileNoise.readPixels())
    return textureNoise
}

fun getBrushedMetalTile(scheme: AuroraColorScheme, width: Int, height: Int): ImageBitmap {
    val hOffset = 15
    // Create a slightly larger noise tile
    val tile = getNoiseTile(scheme = scheme, width = width + 8 * hOffset, height = height)
    // A Skia blur filter
    val blur = ImageFilter.makeBlur(hOffset.toFloat(), 0.0f, FilterTileMode.REPEAT, null, null)
    val paint = Paint()
    paint.imageFilter = blur
    // A bitmap to paint the noise tile + blur filter into
    val bitmap = Bitmap()
    bitmap.setImageInfo(ImageInfo(width, height, ColorType.BGRA_8888, ColorAlphaType.PREMUL))
    bitmap.allocPixels()
    // A canvas to wrap the bitmap
    val canvas = Canvas(bitmap)
    // Draw the noise tile with blur filter into the canvas
    canvas.drawImage(Image.makeFromBitmap(tile.asDesktopBitmap()), -4.0f * hOffset, 0.0f, paint)
    // A Compose image bitmap
    val texture = ImageBitmap(width = width, height = height)
    // Copy over the pixels from the noise tile + blur filter bitmap
    texture.asDesktopBitmap().installPixels(bitmap.readPixels())
    return texture
}