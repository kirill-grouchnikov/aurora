/*
 * Copyright (c) 2020 Mosaic Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.utils

import androidx.compose.ui.graphics.Color
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme
import java.util.*
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
    for (j in 0 until height) {
        for (i in 0 until width) {
            // Get the pixel at the source
            val srcPixel = src[srcPosition]
            val srcAlpha = srcPixel ushr 24 and 0xFF
            val srcRed = srcPixel ushr 16 and 0xFF
            val srcGreen = srcPixel ushr 8 and 0xFF
            val srcBlue = srcPixel and 0xFF

            // Update all the pixels that this pixel contributes to in the destination
            for (d in 0 until kernel.width * kernel.height) {
                var dstRow = j + (d / kernel.width - kernel.verticalHalfSpan)
                // Clamp destination row to be in [0..height) range so that pixels
                // on edges get consistent contribution and we don't end up with
                // tiling artifacts
                dstRow = max(0, min(height - 1, dstRow))

                var dstColumn = i + (d % kernel.width - kernel.horizontalHalfSpan)
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

    var dstPosition = 0
    for (j in 0 until height) {
        for (i in 0 until width) {
            val alpha = max(0, min(255, (temp[4 * dstPosition] + 0.5f).toInt()))
            val red = max(0, min(255, (temp[4 * dstPosition + 1] + 0.5f).toInt()))
            val green = max(0, min(255, (temp[4 * dstPosition + 2] + 0.5f).toInt()))
            val blue = max(0, min(255, (temp[4 * dstPosition + 3] + 0.5f).toInt()))

            // Pack the channels into a single Int
            result[dstPosition] = (alpha shl 24) or (red shl 16) or (green shl 8) or blue

            dstPosition++
        }
    }

    return result
}

internal fun colorizeBgra8888(
    width: Int, height: Int, src: ByteArray, scheme: MosaicColorScheme,
    originalBrightnessFactor: Float,
    alpha: Float
): ByteArray {
    // TODO - when this is extracted into its own filter class, move
    //  this constant out of the function
    val MAPSTEPS = 512

    // collect the brightness factors of the color scheme
    val schemeColorMapping = TreeMap<Int, Color>()
    val ultraLight = scheme.ultraLightColor
    val extraLight = scheme.extraLightColor
    val light = scheme.lightColor
    val mid = scheme.midColor
    val dark = scheme.darkColor
    val ultraDark = scheme.ultraDarkColor

    // Step 1 - map the color scheme colors based on their brightness
    if (ultraLight == extraLight && ultraLight == light && ultraLight == mid &&
        ultraLight == dark && ultraLight == ultraDark
    ) {
        // If the background colors are identical, create a lighter and a darker
        // version for brightness mapping
        val lighter = deriveByBrightness(light, 0.2f)
        val darker = deriveByBrightness(light, -0.2f)
        schemeColorMapping[(getColorBrightness(lighter) * 255.0f).toInt()] = lighter
        schemeColorMapping[(getColorBrightness(light) * 255.0f).toInt()] = light
        schemeColorMapping[(getColorBrightness(darker) * 255.0f).toInt()] = darker
    } else {
        schemeColorMapping[(getColorBrightness(ultraLight) * 255.0f).toInt()] = ultraLight
        schemeColorMapping[(getColorBrightness(extraLight) * 255.0f).toInt()] = extraLight
        schemeColorMapping[(getColorBrightness(light) * 255.0f).toInt()] = light
        schemeColorMapping[(getColorBrightness(mid) * 255.0f).toInt()] = mid
        schemeColorMapping[(getColorBrightness(dark) * 255.0f).toInt()] = dark
        schemeColorMapping[(getColorBrightness(ultraDark) * 255.0f).toInt()] = ultraDark
    }

    var schemeBrightness: List<Int> = ArrayList(schemeColorMapping.keys).sorted()

    // Step 2 - create a "stretched" brightness mapping where the lowest brightness
    // is mapped to 0 and the highest to 255
    val lowestSchemeBrightness = schemeBrightness[0]
    val highestSchemeBrightness = schemeBrightness[schemeBrightness.size - 1]
    val hasSameBrightness = highestSchemeBrightness == lowestSchemeBrightness

    val stretchedColorMapping: MutableMap<Int, Color> = TreeMap<Int, Color>()
    for ((brightness, value) in schemeColorMapping) {
        val stretched = if (hasSameBrightness) brightness
        else 255 - 255 * (highestSchemeBrightness - brightness) /
                (highestSchemeBrightness - lowestSchemeBrightness)
        stretchedColorMapping[stretched] = value
    }
    schemeBrightness = ArrayList(stretchedColorMapping.keys).sorted()

    // Step 3 - create the full brightness mapping that assigns colors to
    // all intermediate brightness values. The intermediate brightness values
    // are in discrete range
    val interpolated = arrayOfNulls<Color>(MAPSTEPS)
    for (i in 0 until MAPSTEPS) {
        val brightness = (256.0 * i / MAPSTEPS).toInt()
        if (schemeBrightness.contains(brightness)) {
            interpolated[i] = stretchedColorMapping[brightness]
        } else {
            if (hasSameBrightness) {
                interpolated[i] = stretchedColorMapping[lowestSchemeBrightness]
            } else {
                var currIndex = 0
                while (true) {
                    val currStopValue = schemeBrightness[currIndex]
                    val nextStopValue = schemeBrightness[currIndex + 1]
                    if (brightness > currStopValue && brightness < nextStopValue) {
                        // interpolate
                        val currStopColor = stretchedColorMapping[currStopValue]!!
                        val nextStopColor = stretchedColorMapping[nextStopValue]!!
                        interpolated[i] = getInterpolatedColor(
                            currStopColor, nextStopColor,
                            1.0f - (brightness - currStopValue).toFloat() / (nextStopValue - currStopValue).toFloat()
                        )
                        break
                    }
                    currIndex++
                }
            }
        }
    }

    // Step 4 - use the brightness mapping to colorize each original pixel "into"
    // our target color scheme. The hue and the value of each original pixel are preserved.
    val size = src.size / 4
    val result = ByteArray(src.size)
    for (pos in 0 until size) {
        // Convert byte channel values to [0.0-1.0] floats for manipulation
        val b = (256 + src[4 * pos].toInt()) / 255.0f
        val g = (256 + src[4 * pos + 1].toInt()) / 255.0f
        val r = (256 + src[4 * pos + 2].toInt()) / 255.0f
        val a = (256 + src[4 * pos + 3].toInt()) / 255.0f

        val brightness = getColorBrightness(r, g, b)
        val hsb = RGBtoHSB(r, g, b)

        val pixelColor = interpolated[(brightness * MAPSTEPS).toInt()]!!
        val hsbInterpolated = RGBtoHSB(pixelColor)

        // Preserve hue and value
        hsb[0] = hsbInterpolated[0]
        hsb[1] = hsbInterpolated[1]
        // And remap the brightness
        if (originalBrightnessFactor >= 0.0f) {
            hsb[2] = (originalBrightnessFactor * hsb[2]
                    + (1.0f - originalBrightnessFactor) * hsbInterpolated[2])
        } else {
            hsb[2] = hsb[2] * hsbInterpolated[2] * (1.0f + originalBrightnessFactor)
        }

        // Convert the remapped HSB back to RGB
        val finalPixel = HSBtoRGB(floatArrayOf(hsb[0], hsb[1], hsb[2]))
        // And compute the final channel values as bytes
        val finalAlpha = (a * alpha * 255.0f + 0.5f).toInt().toByte()
        val finalRed = (finalPixel.red * 255.0f + 0.5f).toInt().toByte()
        val finalGreen = (finalPixel.green * 255.0f + 0.5f).toInt().toByte()
        val finalBlue = (finalPixel.blue * 255.0f + 0.5f).toInt().toByte()

        // Put the byte values in BGRA order
        result[4 * pos] = finalBlue
        result[4 * pos + 1] = finalGreen
        result[4 * pos + 2] = finalRed
        result[4 * pos + 3] = finalAlpha
    }

    return result
}
