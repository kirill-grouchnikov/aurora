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