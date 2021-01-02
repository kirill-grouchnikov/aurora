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
package org.pushingpixels.aurora.utils

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.bitmapfilter.BaseBitmapFilter
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.*

class ColorSchemeBitmapFilter(
    val scheme: AuroraColorScheme,
    private val originalBrightnessFactor: Float,
    val alpha: Float
) : BaseBitmapFilter() {
    private val interpolated = arrayOfNulls<Color>(MAPSTEPS)

    init {
        // collect the brightness factors of the color scheme
        val schemeColorMapping = hashMapOf<Int, Color>()
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
            val lighter = light.withBrightness(0.2f)
            val darker = light.withBrightness(-0.2f)
            schemeColorMapping[(lighter.colorBrightness * 255.0f).toInt()] = lighter
            schemeColorMapping[(light.colorBrightness * 255.0f).toInt()] = light
            schemeColorMapping[(darker.colorBrightness * 255.0f).toInt()] = darker
        } else {
            schemeColorMapping[(ultraLight.colorBrightness * 255.0f).toInt()] = ultraLight
            schemeColorMapping[(extraLight.colorBrightness * 255.0f).toInt()] = extraLight
            schemeColorMapping[(light.colorBrightness * 255.0f).toInt()] = light
            schemeColorMapping[(mid.colorBrightness * 255.0f).toInt()] = mid
            schemeColorMapping[(dark.colorBrightness * 255.0f).toInt()] = dark
            schemeColorMapping[(ultraDark.colorBrightness * 255.0f).toInt()] = ultraDark
        }

        var schemeBrightness: List<Int> = ArrayList(schemeColorMapping.keys).sorted()

        // Step 2 - create a "stretched" brightness mapping where the lowest brightness
        // is mapped to 0 and the highest to 255
        val lowestSchemeBrightness = schemeBrightness[0]
        val highestSchemeBrightness = schemeBrightness[schemeBrightness.size - 1]
        val hasSameBrightness = highestSchemeBrightness == lowestSchemeBrightness

        val stretchedColorMapping: MutableMap<Int, Color> = hashMapOf()
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
                            interpolated[i] = currStopColor.interpolateTowards(
                                nextStopColor,
                                1.0f - (brightness - currStopValue).toFloat() / (nextStopValue - currStopValue).toFloat()
                            )
                            break
                        }
                        currIndex++
                    }
                }
            }
        }
    }

    override fun filter(width: Int, height: Int, source: ByteArray): ByteArray {
        // Use the brightness mapping to colorize each original pixel "into"
        // our target color scheme. The hue and the value of each original pixel are preserved.
        val size = source.size / 4
        val result = ByteArray(source.size)
        for (pos in 0 until size) {
            // Get the bytes from the Skija source
            val blueByte = source[4 * pos].toInt()
            val greenByte = source[4 * pos + 1].toInt()
            val redByte = source[4 * pos + 2].toInt()
            val alphaByte = source[4 * pos + 3].toInt()

            // Convert them to the 0.0-1.0 range that Compose operates in
            val b = (if (blueByte < 0) 256 + blueByte else blueByte) / 255.0f
            val g = (if (greenByte < 0) 256 + greenByte else greenByte) / 255.0f
            val r = (if (redByte < 0) 256 + redByte else redByte) / 255.0f
            val a = (if (alphaByte < 0) 256 + alphaByte else alphaByte) / 255.0f

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

    companion object {
        const val MAPSTEPS = 512
    }
}