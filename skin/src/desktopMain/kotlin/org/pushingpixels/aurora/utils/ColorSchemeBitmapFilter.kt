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

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.bitmapfilter.BaseBitmapFilter
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.*

fun getInterpolatedColors(scheme: AuroraColorScheme): Array<Color?> {
    val result = arrayOfNulls<Color>(ColorSchemeBitmapFilter.MAPSTEPS)

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
    for (i in 0 until ColorSchemeBitmapFilter.MAPSTEPS) {
        val brightness = (256.0 * i / ColorSchemeBitmapFilter.MAPSTEPS).toInt()
        if (schemeBrightness.contains(brightness)) {
            result[i] = stretchedColorMapping[brightness]
        } else {
            if (hasSameBrightness) {
                result[i] = stretchedColorMapping[lowestSchemeBrightness]
            } else {
                var currIndex = 0
                while (true) {
                    val currStopValue = schemeBrightness[currIndex]
                    val nextStopValue = schemeBrightness[currIndex + 1]
                    if (brightness > currStopValue && brightness < nextStopValue) {
                        // interpolate
                        val currStopColor = stretchedColorMapping[currStopValue]!!
                        val nextStopColor = stretchedColorMapping[nextStopValue]!!
                        result[i] = currStopColor.interpolateTowards(
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
    return result
}

class ColorSchemeBitmapFilter(
    val scheme: AuroraColorScheme,
    private val originalBrightnessFactor: Float,
    val alpha: Float
) : BaseBitmapFilter() {
    private val interpolated = getInterpolatedColors(scheme)

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

            val pixelColor = interpolated[(brightness * MAPSTEPS - 0.5f).toInt()]!!
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