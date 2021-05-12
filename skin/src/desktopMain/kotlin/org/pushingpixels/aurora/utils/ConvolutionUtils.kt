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

import kotlin.math.max
import kotlin.math.min

data class ConvolutionKernel(
    val width: Int, val height: Int,
    val matrix: FloatArray
) {
    val verticalHalfSpan = (height - 1) / 2
    val horizontalHalfSpan = (width - 1) / 2
}

internal fun convolve(width: Int, height: Int, src: IntArray, kernel: ConvolutionKernel): IntArray {
    require(src.size == width * height) { "Source spec inconsistent" }
    require((kernel.width % 2 == 1) && (kernel.height % 2 == 1)) { "Only support odd-sized kernel matrix" }
    require(kernel.matrix.size == kernel.width * kernel.height) { "Kernel matrix spec inconsistent" }

    // TODO - can this be made more efficient without allocating an extra array?
    val temp = FloatArray(4 * width * height)

    // Compute combined channel-based contributions
    var srcPosition = 0
    for (srcRow in 0 until height) {
        for (srcColumn in 0 until width) {
            // Get the pixel at the source
            val srcPixel = src[srcPosition]
            val srcAlpha = srcPixel ushr 24 and 0xFF
            val srcRed = srcPixel ushr 16 and 0xFF
            val srcGreen = srcPixel ushr 8 and 0xFF
            val srcBlue = srcPixel and 0xFF

            // Update all the pixels that this pixel contributes to in the destination
            for (d in 0 until kernel.width * kernel.height) {
                var dstRow = srcRow + (d / kernel.width - kernel.verticalHalfSpan)
                // Clamp destination row to be in [0..height) range so that pixels
                // on edges get consistent contribution and we don't end up with
                // tiling artifacts
                dstRow = max(0, min(height - 1, dstRow))

                var dstColumn = srcColumn + (d % kernel.width - kernel.horizontalHalfSpan)
                // Clamp destination column to be in [0..width) range so that pixels
                // on edges get consistent contribution and we don't end up with
                // tiling artifacts
                dstColumn = max(0, min(width - 1, dstColumn))

                val dstPosition = dstRow * width + dstColumn

                // Alpha
                temp[4 * dstPosition] += kernel.matrix[d] * srcAlpha
                // Red
                temp[4 * dstPosition + 1] += kernel.matrix[d] * srcRed
                // Green
                temp[4 * dstPosition + 2] += kernel.matrix[d] * srcGreen
                // Blue
                temp[4 * dstPosition + 3] += kernel.matrix[d] * srcBlue
            }

            srcPosition++
        }
    }

    // Pack combined contributions into the result
    val result = IntArray(width * height)
    for (dstPosition in 0 until width * height) {
        val alpha = max(0, min(255, (temp[4 * dstPosition] + 0.5f).toInt()))
        val red = max(0, min(255, (temp[4 * dstPosition + 1] + 0.5f).toInt()))
        val green = max(0, min(255, (temp[4 * dstPosition + 2] + 0.5f).toInt()))
        val blue = max(0, min(255, (temp[4 * dstPosition + 3] + 0.5f).toInt()))

        // Pack the channels into a single Int
        result[dstPosition] = (alpha shl 24) or (red shl 16) or (green shl 8) or blue
    }

    return result
}
