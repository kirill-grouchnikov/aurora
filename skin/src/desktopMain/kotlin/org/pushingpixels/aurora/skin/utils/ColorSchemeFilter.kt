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
package org.pushingpixels.aurora.skin.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toComposeColorFilter
import org.pushingpixels.aurora.common.colorBrightness
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withBrightness
import org.pushingpixels.aurora.skin.colorscheme.AuroraColorScheme
import kotlin.math.roundToInt

private val interpolations: MutableMap<AuroraColorScheme, Array<Color?>> = hashMapOf()
private const val MapSteps = 256

private fun getInterpolatedColors(scheme: AuroraColorScheme): Array<Color?> {
    if ((scheme !is MutableColorScheme) && interpolations.containsKey(scheme)) {
        return interpolations[scheme]!!
    }

    val result = arrayOfNulls<Color>(MapSteps)

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
    for (i in 0 until MapSteps) {
        val brightness = (256.0 * i / MapSteps).toInt()
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
    if (scheme !is MutableColorScheme) {
        interpolations[scheme] = result
    }
    return result
}

fun getColorSchemeFilterSkia(scheme: AuroraColorScheme): org.jetbrains.skia.ColorFilter {
    val filtering = getInterpolatedColors(scheme)
    val reds = ByteArray(256)
    val greens = ByteArray(256)
    val blues = ByteArray(256)

    for ((index, filteredColor) in filtering.withIndex()) {
        reds[index] = (255 * filteredColor!!.red).roundToInt().toByte()
        greens[index] = (255 * filteredColor.green).roundToInt().toByte()
        blues[index] = (255 * filteredColor.blue).roundToInt().toByte()
    }

    // Pass null for alphas so that when the filter is applied, it respects the alpha
    // channel of the source image
    return org.jetbrains.skia.ColorFilter.makeTableARGB(null, reds, greens, blues)
}

fun getColorSchemeFilter(scheme: AuroraColorScheme): ColorFilter {
    return getColorSchemeFilterSkia(scheme).toComposeColorFilter()
}
