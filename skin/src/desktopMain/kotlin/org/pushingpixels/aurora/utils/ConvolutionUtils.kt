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
