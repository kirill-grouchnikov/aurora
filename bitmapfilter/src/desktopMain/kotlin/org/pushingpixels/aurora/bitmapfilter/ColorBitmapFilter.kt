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
package org.pushingpixels.aurora.bitmapfilter

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.HashMapKey

class ColorBitmapFilter private constructor(val color: Color, val alpha: Float = 1.0f) : BaseBitmapFilter() {
    override fun filter(width: Int, height: Int, source: ByteArray): ByteArray {
        val colorAlpha = color.alpha
        val colorRed = (color.red * 255.0f + 0.5f).toInt().toByte()
        val colorGreen = (color.green * 255.0f + 0.5f).toInt().toByte()
        val colorBlue = (color.blue * 255.0f + 0.5f).toInt().toByte()

        val size = source.size / 4
        val result = ByteArray(source.size)
        for (pos in 0 until size) {
            // Get the alpha byte from the Skija source
            val alphaByte = source[4 * pos + 3].toInt()
            // Convert it to the 0.0-1.0 range that compose operates in
            val sourceAlpha = (if (alphaByte < 0) 256 + alphaByte else alphaByte) / 255.0f

            // We have three alphas that are combined into the final alpha for the current pixel:
            // 1. The alpha of the current pixel in the source image
            // 2. The alpha of the color used in our filter
            // 3. The alpha of the filter itself
            val finalAlpha = (alpha * colorAlpha * sourceAlpha * 255.0f + 0.5f).toInt().toByte()

            // Put the byte values in BGRA order
            result[4 * pos] = colorBlue
            result[4 * pos + 1] = colorGreen
            result[4 * pos + 2] = colorRed
            result[4 * pos + 3] = finalAlpha
        }

        return result
    }

    companion object {
        private val filters = hashMapOf<HashMapKey, ColorBitmapFilter>()

        fun getColorFilter(color: Color, alpha: Float = 1.0f): ColorBitmapFilter {
            val key = HashMapKey(color, alpha)
            var result = filters[key]
            if (result == null) {
                result = ColorBitmapFilter(color, alpha)
                filters[key] = result
            }
            return result
        }
    }
}