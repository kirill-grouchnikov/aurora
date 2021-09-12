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
    val result = ImageBitmap(width = width, height = height)
    val tile = result.asDesktopBitmap()
    val canvas = Canvas(tile)

    val paint = Paint()
    // Fractal noise shader
    paint.setShader(
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
    paint.setColorFilter(
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
    paint.imageFilter = ImageFilter.makeMatrixConvolution(
        3, 3,
        floatArrayOf(.08f, .08f, .08f, .08f, .38f, .08f, .08f, .08f, .08f),
        1f / 1.02f, 1f / 1.02f, 0, 0, FilterTileMode.REPEAT, true, null, null
    )
    canvas.drawRect(Rect.makeWH(width.toFloat(), height.toFloat()), paint)

    return result
}

fun getBrushedMetalTile(scheme: AuroraColorScheme, width: Int, height: Int): ImageBitmap {
    val hOffset = 15

    val result = ImageBitmap(width = width, height = height)
    val tile = result.asDesktopBitmap()
    val canvas = Canvas(tile)

    val paint = Paint()
    // Fractal noise shader
    paint.setShader(
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
    paint.setColorFilter(
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
    // Image filter to apply horizontal blur
    paint.imageFilter = ImageFilter.makeBlur(hOffset.toFloat(), 0.0f, FilterTileMode.REPEAT, null, null)
    // Apply horizontal offset to "cut off" the parts of the image that have partial translucency
    // (along left and right edges) due to application of horizontal blur
    canvas.drawRect(Rect.makeLTRB(-4.0f * hOffset, 0.0f, width + 8.0f * hOffset, height.toFloat()), paint)

    return result
}