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

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import org.jetbrains.skija.ColorAlphaType
import org.jetbrains.skija.ColorInfo
import org.jetbrains.skija.ColorType
import org.jetbrains.skija.ImageInfo

abstract class BaseBitmapFilter {
    abstract fun filter(width: Int, height: Int, source: ByteArray): ByteArray

    fun filter(source: ImageBitmap): ImageBitmap {
        val width = source.width
        val height = source.height
        val originalBitmap = source.asDesktopBitmap()
        val originalPixels = originalBitmap.readPixels(
            ImageInfo(ColorInfo(ColorType.BGRA_8888, originalBitmap.alphaType, null), width, height),
            width.toLong() * originalBitmap.bytesPerPixel, 0, 0
        )

        require(originalPixels != null) { "Cannot read pixels from the source" }

        // Filter the pixels
        val filteredPixels = filter(width, height, originalPixels)

        // And convert them to an ImageBitmap in the same BGRA_8888 format
        val result = ImageBitmap(width = width, height = height)
        val skijaBitmap = result.asDesktopBitmap()
        skijaBitmap.installPixels(
            ImageInfo(ColorInfo(ColorType.BGRA_8888, ColorAlphaType.UNPREMUL, null), width, height),
            filteredPixels, 4L * width
        )

        return result
    }
}