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
package org.pushingpixels.aurora.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import org.jetbrains.skija.ColorAlphaType
import org.jetbrains.skija.ColorInfo
import org.jetbrains.skija.ColorType
import org.jetbrains.skija.ImageInfo
import java.awt.image.BufferedImage

// Ideally every transcoded SVG image that has raster content would have this
// conversion function in that class (so that it doesn't need to bring the
// dependency), but for now make it an extension on the [BufferedImage] class.
fun BufferedImage.toComposeBitmap(): ImageBitmap {
    val width = this.width
    val height = this.height
    val origPixels = IntArray(width * height)

    // Step 1 - get the pixels from the input
    if ((this.type == BufferedImage.TYPE_INT_ARGB) || (this.type == BufferedImage.TYPE_INT_RGB)) {
        this.raster.getDataElements(0, 0, width, height, origPixels)
    } else {
        this.getRGB(0, 0, width, height, origPixels, 0, width)
    }

    // Step 2 - convert an array of integers (each integer is 8888 of ARGB) into an
    // array of bytes (in BGRA order) that Skija expects
    val byteDstBuffer = ByteArray(4 * width * height)
    for (pos in 0 until width * height) {
        val rgb = origPixels[pos]
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

    // Step 3 - create a Skija bitmap
    val result = ImageBitmap(width = width, height = height)
    val skijaBitmap = result.asDesktopBitmap()
    skijaBitmap.installPixels(
        ImageInfo(ColorInfo(ColorType.BGRA_8888, ColorAlphaType.UNPREMUL, null), width, height),
        byteDstBuffer, 4L * width
    )
    return result
}


