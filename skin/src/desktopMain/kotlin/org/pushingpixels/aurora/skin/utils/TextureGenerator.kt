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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import org.jetbrains.skija.*
import org.pushingpixels.aurora.common.interpolateTowardsAsRGB
import org.pushingpixels.aurora.skin.colorscheme.AuroraColorScheme
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

fun getNoiseTile(scheme: AuroraColorScheme, width: Int, height: Int): ImageBitmap {
    val c1: Color = scheme.darkColor
    val c3: Color = scheme.lightColor
    val xFactor = 0.8
    val yFactor = 0.8
    val hasConstantZ = false

    // Create a noise pixel map
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

            noiseBuffer[pos] = c3.interpolateTowardsAsRGB(c1, likeness)

            // Go to the next pixel
            pos++
        }
    }
    // Convert an array of integers (each integer is 8888 of ARGB) into an
    // array of bytes (in BGRA order) that Skija expects
    val byteDstBuffer = ByteArray(4 * width * height)
    for (bytePos in 0 until width * height) {
        val rgb = noiseBuffer[bytePos]
        // The order of the bytes corresponds to BGRA_8888
        // Blue
        byteDstBuffer[4 * bytePos] = (rgb ushr 0 and 0xFF).toByte()
        // Green
        byteDstBuffer[4 * bytePos + 1] = (rgb ushr 8 and 0xFF).toByte()
        // Red
        byteDstBuffer[4 * bytePos + 2] = (rgb ushr 16 and 0xFF).toByte()
        // Alpha
        byteDstBuffer[4 * bytePos + 3] = 255.toByte()
    }
    // Create Skija bitmap from the noise buffer
    val noiseBitmap = Bitmap()
    noiseBitmap.imageInfo = ImageInfo(width, height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
    noiseBitmap.allocPixels()
    noiseBitmap.installPixels(byteDstBuffer)

    // Create another Skija bitmap that will have the softened noise (from applying a
    // convolution matrix)
    val softenedBitmap = Bitmap()
    softenedBitmap.imageInfo = ImageInfo(width, height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
    softenedBitmap.allocPixels()
    val softenedCanvas = Canvas(softenedBitmap)
    // Draw the noise tile with blur filter into the canvas
    val paint = Paint()
    paint.imageFilter = ImageFilter.makeMatrixConvolution(
        3, 3,
        floatArrayOf(.08f, .08f, .08f, .08f, .38f, .08f, .08f, .08f, .08f),
        1f / 1.02f, 1f / 1.02f, 0, 0, FilterTileMode.REPEAT, true, null, null
    )
    softenedCanvas.drawImage(Image.makeFromBitmap(noiseBitmap), 0.0f, 0.0f, paint)

    // A Compose image bitmap
    val texture = ImageBitmap(width = width, height = height)
    // Copy over the pixels from the noise tile + blur filter bitmap
    texture.asDesktopBitmap().installPixels(softenedBitmap.readPixels())
    return texture
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
    bitmap.imageInfo = ImageInfo(width, height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
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