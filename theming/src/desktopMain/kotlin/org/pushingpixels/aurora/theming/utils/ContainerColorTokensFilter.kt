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
import org.pushingpixels.aurora.common.HashMapKey
import org.pushingpixels.aurora.common.colorBrightness
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.ContainerColorTokens
import kotlin.math.roundToInt

@OptIn(AuroraInternalApi::class)
private val interpolations: MutableMap<HashMapKey, Array<Color?>> = hashMapOf()
private const val MapSteps = 256

enum class FilterRange {
    FullSpan, TonalContainerSurfaces
}

private fun getColor(content: Color, contentAlpha: Float, surface: Color): Color {
    val finalContentAlpha = contentAlpha * content.alpha
    if (finalContentAlpha == 1.0f) {
        return content
    }

    val contentR = content.red
    val contentG = content.green
    val contentB = content.blue

    val surfaceR = surface.red
    val surfaceG = surface.green
    val surfaceB = surface.blue

    val resultR = surfaceR + contentAlpha * (contentR - surfaceR)
    val resultG = surfaceG + contentAlpha * (contentG - surfaceG)
    val resultB = surfaceB + contentAlpha * (contentB - surfaceB)

    return Color(resultR, resultG, resultB)
}

@OptIn(AuroraInternalApi::class)
private fun getInterpolatedColors(colorTokens: ContainerColorTokens, filterRange: FilterRange): Array<Color?> {
    val key = HashMapKey(colorTokens, filterRange)
    if ((colorTokens !is MutableContainerColorTokens) && interpolations.containsKey(key)) {
        return interpolations[key]!!
    }

    val result = arrayOfNulls<Color>(MapSteps)

    // collect the brightness factors of the color tokens
    var tokenColorMapping = hashMapOf<Int, Color>()
    val containerLowest = colorTokens.containerSurfaceLowest
    val containerLow = colorTokens.containerSurfaceLow
    val container = colorTokens.containerSurface
    val containerHigh = colorTokens.containerSurfaceHigh
    val containerHighest = colorTokens.containerSurfaceHighest
    val containerDim = colorTokens.containerSurfaceDim
    val containerBright = colorTokens.containerSurfaceBright

    // Step 1A - map the color tokens colors based on their brightness
    tokenColorMapping[(containerLowest.colorBrightness * 255.0f).toInt()] = containerLowest
    tokenColorMapping[(containerLow.colorBrightness * 255.0f).toInt()] = containerLow
    tokenColorMapping[(container.colorBrightness * 255.0f).toInt()] = container
    tokenColorMapping[(containerHigh.colorBrightness * 255.0f).toInt()] = containerHigh
    tokenColorMapping[(containerHighest.colorBrightness * 255.0f).toInt()] = containerHighest
    tokenColorMapping[(containerDim.colorBrightness * 255.0f).toInt()] = containerDim
    tokenColorMapping[(containerBright.colorBrightness * 255.0f).toInt()] = containerBright

    if (filterRange == FilterRange.FullSpan) {
        // Step 1B - more color tokens
        val containerOutlineLow = getColor(colorTokens.containerOutlineLow,
            colorTokens.containerOutlineEnabledAlpha, container)
        val containerOutline = getColor(colorTokens.containerOutline,
            colorTokens.containerOutlineEnabledAlpha, container)
        val containerOutlineHigh = getColor(colorTokens.containerOutlineHigh,
            colorTokens.containerOutlineEnabledAlpha, container)
        val onContainerLow = getColor(colorTokens.onContainerLow,
            colorTokens.onContainerEnabledAlpha, container)
        val onContainer = getColor(colorTokens.onContainer,
            colorTokens.onContainerEnabledAlpha, container)
        val onContainerHigh = getColor(colorTokens.onContainerHigh,
            colorTokens.onContainerEnabledAlpha, container)
        val inverseContainerSurface = colorTokens.inverseContainerSurface
        val inverseOnContainer = getColor(colorTokens.inverseOnContainer,
            colorTokens.onContainerEnabledAlpha, inverseContainerSurface)
        val inverseContainerOutline = getColor(colorTokens.inverseContainerOutline,
            colorTokens.containerOutlineEnabledAlpha, inverseContainerSurface)
        val complementaryOnContainer = getColor(colorTokens.complementaryOnContainer,
            colorTokens.onContainerEnabledAlpha, container)
        val complementaryContainerOutline = getColor(colorTokens.complementaryContainerOutline,
            colorTokens.containerOutlineEnabledAlpha, container)

        tokenColorMapping[(containerOutlineLow.colorBrightness * 255.0f).toInt()] = containerOutlineLow
        tokenColorMapping[(containerOutline.colorBrightness * 255.0f).toInt()] = containerOutline
        tokenColorMapping[(containerOutlineHigh.colorBrightness * 255.0f).toInt()] = containerOutlineHigh
        tokenColorMapping[(onContainerLow.colorBrightness * 255.0f).toInt()] = onContainerLow
        tokenColorMapping[(onContainer.colorBrightness * 255.0f).toInt()] = onContainer
        tokenColorMapping[(onContainerHigh.colorBrightness * 255.0f).toInt()] = onContainerHigh
        tokenColorMapping[(inverseContainerSurface.colorBrightness * 255.0f).toInt()] = inverseContainerSurface
        tokenColorMapping[(inverseOnContainer.colorBrightness * 255.0f).toInt()] = inverseOnContainer
        tokenColorMapping[(inverseContainerOutline.colorBrightness * 255.0f).toInt()] = inverseContainerOutline
        tokenColorMapping[(complementaryOnContainer.colorBrightness * 255.0f).toInt()] = complementaryOnContainer
        tokenColorMapping[(complementaryContainerOutline.colorBrightness * 255.0f).toInt()] = complementaryContainerOutline
    }

    var colorTokensBrightness: MutableList<Int> = ArrayList(tokenColorMapping.keys).sorted().toMutableList()
    val lowestTokensBrightness = colorTokensBrightness.first()
    val highestTokensBrightness = colorTokensBrightness.last()
    val hasSameBrightness = (highestTokensBrightness == lowestTokensBrightness)

    when (filterRange) {
        FilterRange.FullSpan -> {
            // Step 2A - put full black and full white as bookends if needed
            if (lowestTokensBrightness > 0) {
                colorTokensBrightness.add(0, 0)
                tokenColorMapping[0] = Color.Black
            }

            if (highestTokensBrightness < 255) {
                colorTokensBrightness.add(255)
                tokenColorMapping[255] = Color.White
            }
        }
        FilterRange.TonalContainerSurfaces -> {
            // Step 2B - create a "stretched" brightness mapping where the lowest brightness
            // is mapped to 0 and the highest to 255
            val lowestColorTokensBrightness = colorTokensBrightness[0]
            val highestColorTokensBrightness = colorTokensBrightness[colorTokensBrightness.size - 1]
            val hasSameBrightness = highestColorTokensBrightness == lowestColorTokensBrightness

            val stretchedColorMapping = hashMapOf<Int, Color>()
            for ((brightness, value) in tokenColorMapping) {
                val stretched = if (hasSameBrightness) brightness
                else 255 - 255 * (highestColorTokensBrightness - brightness) /
                    (highestColorTokensBrightness - lowestColorTokensBrightness)
                stretchedColorMapping[stretched] = value
            }
            colorTokensBrightness = ArrayList(stretchedColorMapping.keys).sorted().toMutableList()
            tokenColorMapping = stretchedColorMapping
        }
    }

    // Step 3 - create the full brightness mapping that assigns colors to
    // all intermediate brightness values. The intermediate brightness values
    // are in discrete range
    for (i in 0 until MapSteps) {
        val brightness = (256.0 * i / MapSteps).toInt()
        if (colorTokensBrightness.contains(brightness)) {
            result[i] = tokenColorMapping[brightness]
        } else {
            if (hasSameBrightness) {
                result[i] = tokenColorMapping[brightness]
            } else {
                var currIndex = 0
                while (true) {
                    val currStopValue = colorTokensBrightness[currIndex]
                    val nextStopValue = colorTokensBrightness[currIndex + 1]
                    if ((brightness > currStopValue) && (brightness < nextStopValue)) {
                        // interpolate
                        val currStopColor = tokenColorMapping[currStopValue]!!
                        val nextStopColor = tokenColorMapping[nextStopValue]!!
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
        interpolations[key] = result
    }
    return result
}

fun getContainerColorTokensFilter(colorTokens: ContainerColorTokens, filterRange: FilterRange): ColorFilter {
    val filtering = getInterpolatedColors(colorTokens, filterRange)
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
