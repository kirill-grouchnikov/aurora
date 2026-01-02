/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asComposeColorFilter
import org.jetbrains.skia.ColorMatrix
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.colorBrightness
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.ContainerColorTokens
import kotlin.math.roundToInt

private val interpolations: MutableMap<ContainerColorTokens, Array<Color?>> = hashMapOf()
private const val MapSteps = 256

@OptIn(AuroraInternalApi::class)
private fun getInterpolatedColors(colorTokens: ContainerColorTokens): Array<Color?> {
    if ((colorTokens !is MutableContainerColorTokens) && interpolations.containsKey(colorTokens)) {
        return interpolations[colorTokens]!!
    }

    val result = arrayOfNulls<Color>(MapSteps)

    // collect the brightness factors of the color tokens
    val tokenColorMapping = hashMapOf<Int, Color>()
    val containerLowest = colorTokens.containerSurfaceLowest
    val containerLow = colorTokens.containerSurfaceLow
    val container = colorTokens.containerSurface
    val containerHigh = colorTokens.containerSurfaceHigh
    val containerHighest = colorTokens.containerSurfaceHighest
    val containerOutlineVariant = colorTokens.containerOutlineVariant
    val containerOutline = colorTokens.containerOutline

    // Step 1 - map the color tokens colors based on their brightness
    tokenColorMapping[(containerLowest.colorBrightness * 255.0f).toInt()] = containerLowest
    tokenColorMapping[(containerLow.colorBrightness * 255.0f).toInt()] = containerLow
    tokenColorMapping[(container.colorBrightness * 255.0f).toInt()] = container
    tokenColorMapping[(containerHigh.colorBrightness * 255.0f).toInt()] = containerHigh
    tokenColorMapping[(containerHighest.colorBrightness * 255.0f).toInt()] = containerHighest
    tokenColorMapping[(containerOutlineVariant.colorBrightness * 255.0f).toInt()] = containerOutlineVariant
    tokenColorMapping[(containerOutline.colorBrightness * 255.0f).toInt()] = containerOutline

    var colorTokensBrightness: List<Int> = ArrayList(tokenColorMapping.keys).sorted()

    // Step 2 - create a "stretched" brightness mapping where the lowest brightness
    // is mapped to 0 and the highest to 255
    val lowestColorTokensBrightness = colorTokensBrightness[0]
    val highestColorTokensBrightness = colorTokensBrightness[colorTokensBrightness.size - 1]
    val hasSameBrightness = highestColorTokensBrightness == lowestColorTokensBrightness

    val stretchedColorMapping: MutableMap<Int, Color> = hashMapOf()
    for ((brightness, value) in tokenColorMapping) {
        val stretched = if (hasSameBrightness) brightness
        else 255 - 255 * (highestColorTokensBrightness - brightness) /
                (highestColorTokensBrightness - lowestColorTokensBrightness)
        stretchedColorMapping[stretched] = value
    }
    colorTokensBrightness = ArrayList(stretchedColorMapping.keys).sorted()

    // Step 3 - create the full brightness mapping that assigns colors to
    // all intermediate brightness values. The intermediate brightness values
    // are in discrete range
    for (i in 0 until MapSteps) {
        val brightness = (256.0 * i / MapSteps).toInt()
        if (colorTokensBrightness.contains(brightness)) {
            result[i] = stretchedColorMapping[brightness]
        } else {
            if (hasSameBrightness) {
                result[i] = stretchedColorMapping[lowestColorTokensBrightness]
            } else {
                var currIndex = 0
                while (true) {
                    val currStopValue = colorTokensBrightness[currIndex]
                    val nextStopValue = colorTokensBrightness[currIndex + 1]
                    if ((brightness > currStopValue) && (brightness < nextStopValue)) {
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
    if (colorTokens !is MutableContainerColorTokens) {
        interpolations[colorTokens] = result
    }
    return result
}

fun getContainerColorTokensFilter(colorTokens: ContainerColorTokens): ColorFilter {
    val filtering = getInterpolatedColors(colorTokens)
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
    val outer = org.jetbrains.skia.ColorFilter.makeTableARGB(null, reds, greens, blues)

    // But first, we need to apply a grayscale color filter to remove all hue from the
    // original paint
    val inner = org.jetbrains.skia.ColorFilter.makeMatrix(ColorMatrix(
        0.2126f, 0.7152f, 0.0722f, 0.0f, 0.0f,
        0.2126f, 0.7152f, 0.0722f, 0.0f, 0.0f,
        0.2126f, 0.7152f, 0.0722f, 0.0f, 0.0f,
        0.0f,  0.0f,  0.0f,  1.0f, 0.0f
    ))
    return org.jetbrains.skia.ColorFilter.makeComposed(outer, inner).asComposeColorFilter()
}
