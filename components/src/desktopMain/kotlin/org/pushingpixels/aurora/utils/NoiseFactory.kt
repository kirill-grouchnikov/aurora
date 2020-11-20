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
package org.pushingpixels.aurora.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import org.jetbrains.skija.ColorAlphaType
import org.jetbrains.skija.ColorInfo
import org.jetbrains.skija.ColorType
import org.jetbrains.skija.ImageInfo
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Factory for creating noise images.
 *
 * @author Kirill Grouchnikov.
 */
internal object NoiseFactory {
    /**
     * Returns a noise image.
     *
     * @param scheme The color scheme to use for rendering the noise image.
     * @param width Noise image width.
     * @param height  Noise image height.
     * @param xFactor X stretch factor.
     * @param yFactor Y stretch factor.
     * @param hasConstantZ Indication whether the Z is constant.
     * @return Noise image.
     */
    fun getNoiseImage(
        scheme: AuroraColorScheme, width: Int,
        height: Int, xFactor: Double, yFactor: Double, hasConstantZ: Boolean
    ): ImageBitmap {
        val c1: Color = scheme.darkColor
        val c3: Color = scheme.lightColor

        // Step 1 - create a noise pixel map
        val noiseBuffer = IntArray(width * height)
        val m2 = xFactor * width * xFactor * width + (yFactor * height
                * yFactor * height)
        var pos = 0
        for (row in 0 until height) {
            val tweakedRow = yFactor * row
            for (column in 0 until width) {
                val tweakedColumn = xFactor * column
                val z = if (hasConstantZ) 1.0 else
                    sqrt(m2 - tweakedColumn * tweakedColumn - tweakedRow * tweakedRow)
                val noise = 0.5f + 0.5f * PerlinNoiseGenerator.noise(tweakedColumn, tweakedRow, z)
                val likeness = max(0.0f, min(1.0f, 2.0f * noise.toFloat()))

                noiseBuffer[pos] = getInterpolatedRGB(c3, c1, likeness)

                // Go to the next pixel
                pos++
            }
        }

        // Step 2 - convolve to soften the noise
        val convolved = convolve(
            width = width,
            height = height,
            src = noiseBuffer,
            kernel = ConvolutionKernel(
                width = 3,
                height = 3,
                matrix = floatArrayOf(.08f, .08f, .08f, .08f, .38f, .08f, .08f, .08f, .08f)
            )
        )

        // Step 3 - convert an array of integers (each integer is 8888 of ARGB) into an
        // array of bytes (in BGRA order) that Skija expects
        val byteDstBuffer = ByteArray(4 * width * height)
        for (pos in 0 until width * height) {
            val rgb = convolved[pos]
            // The order of the bytes corresponds to BGRA_8888
            // Blue
            byteDstBuffer[4 * pos] = (rgb ushr 0 and 0xFF).toByte()
            // Green
            byteDstBuffer[4 * pos + 1] = (rgb ushr 8 and 0xFF).toByte()
            // Red
            byteDstBuffer[4 * pos + 2] = (rgb ushr 16 and 0xFF).toByte()
            // Alpha
            byteDstBuffer[4 * pos + 3] = 255.toByte()
        }

        // Step 4 - create a Skija bitmap
        val result = ImageBitmap(width = width, height = height)
        val skijaBitmap = result.asDesktopBitmap()
        skijaBitmap.installPixels(
            ImageInfo(ColorInfo(ColorType.BGRA_8888, ColorAlphaType.UNPREMUL, null), width, height),
            byteDstBuffer, 4L * width
        )
        return result
    }
}