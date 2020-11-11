/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
import kotlin.math.*

/**
 * Interpolates color.
 *
 * @param color1         The first color
 * @param color2         The second color
 * @param color1Likeness The closer this value is to 0.0, the closer the resulting
 * color will be to `color2`.
 * @return Interpolated RGB value.
 */
fun getInterpolatedColor(color1: Color, color2: Color, color1Likeness: Float): Color {
    if (color1Likeness < 0.0 || color1Likeness > 1.0) {
        throw IllegalArgumentException(
            "Color likeness should be in 0.0-1.0 range [is "
                    + color1Likeness + "]"
        )
    }
    val alpha1: Float = color1.alpha
    val alpha2: Float = color2.alpha
    val r = getInterpolatedChannelValue(color1.red, color2.red, color1Likeness)
    val g = getInterpolatedChannelValue(color1.green, color2.green, color1Likeness)
    val b = getInterpolatedChannelValue(color1.blue, color2.blue, color1Likeness)
    val a = if (alpha1 == alpha2) alpha1 else
        round(color1Likeness * alpha1 + (1.0f - color1Likeness) * alpha2)
    return Color(r, g, b, a, color1.colorSpace)
}

private fun getInterpolatedChannelValue(value1: Float, value2: Float, value1Likeness: Float): Float {
    if (value1 == value2) {
        return value1
    }
    if (value1Likeness == 1.0f) {
        return value1
    }
    if (value1Likeness == 0.0f) {
        return value2
    }

    // Step 1 - convert channel from electro to optical
    val optical1 = EOCF_sRGB(value1)
    val optical2 = EOCF_sRGB(value2)

    // Step 2 - interpolate
    val interpolatedOptical = value1Likeness * optical1 +
            (1.0f - value1Likeness) * optical2

    // Step 3 - convert interpolated from optical to electro
    val interpolatedElectro = OECF_sRGB(interpolatedOptical)

    // Step 4 - convert to 0..1 range
    // using some interpolation values (such as 0.29 from issue 401)
    // results in an incorrect final value without Math.round.
    var result = interpolatedElectro
    if (result < 0.0f) {
        result = 0.0f
    }
    if (result > 1.0f) {
        result = 1.0f
    }
    return result
}

// Opto-electronic conversion function for the sRGB color space
// Takes a gamma-encoded sRGB value and converts it to a linear sRGB value
private fun OECF_sRGB(linear: Float): Float {
    // IEC 61966-2-1:1999
    return if (linear <= 0.0031308f) linear * 12.92f else
        linear.pow(1.0f / 2.4f) * 1.055f - 0.055f
}

// Electro-optical conversion function for the sRGB color space
// Takes a linear sRGB value and converts it to a gamma-encoded sRGB value
private fun EOCF_sRGB(srgb: Float): Float {
    // IEC 61966-2-1:1999
    return if (srgb <= 0.04045f) srgb / 12.92f else ((srgb + 0.055f) / 1.055f).pow(2.4f)
}

internal fun RGBtoHSB(from: Color): FloatArray {
    val r = from.red
    val g = from.green
    val b = from.blue
    val cmax = max(max(r, g), b)
    val cmin = min(min(r, g), b)
    val delta = cmax - cmin

    val result = FloatArray(3)
    // brightness
    result[2] = cmax
    // saturation
    result[1] = if (cmax == 0.0f) 0.0f else delta / cmax
    // hue
    if (delta == 0.0f) {
        result[0] = 0.0f
    } else {
        if (cmax == r) {
            result[0] = (g - b) / delta
        } else if (cmax == g) {
            result[0] = 1.0f / 3.0f + (b - r) / delta
        } else {
            result[0] = 2f / 3 + (r - g) / delta
        }
        if (result[0] < 0.0f) {
            result[0] = 0.0f
        }
    }
    return result
}

internal fun HSBtoRGB(from: FloatArray): Color {
    val hue = from[0]
    val saturation = from[1]
    val brightness = from[2]

    if (saturation == 0.0f) {
        return Color(brightness, brightness, brightness)
    }

    val hue360 = hue * 360.0f
    val hue360sharp = hue360 / 60.0f

    val chroma = saturation * brightness
    val x = chroma * (1 - abs(hue360sharp % 2 - 1))
    val m = brightness - chroma

    if ((hue360sharp >= 0.0f) && (hue360sharp <= 1.0f)) {
        return Color(chroma + m, x + m, m)
    }
    if (hue360sharp <= 2.0f) {
        return Color(x + m, chroma + m, m)
    }
    if (hue360sharp <= 3.0f) {
        return Color(m, chroma + m, x + m)
    }
    if (hue360sharp <= 4.0f) {
        return Color(m, x + m, chroma + m)
    }
    if (hue360sharp <= 5.0f) {
        return Color(x + m, m, chroma + m)
    }
    return Color(chroma + m, m, x + m)
}

fun deriveByBrightness(original: Color, brightnessSource: Color): Color {
    val hsbvalsOrig = RGBtoHSB(original)
    val hsbvalsBrightnessSrc = RGBtoHSB(brightnessSource)
    return HSBtoRGB(
        floatArrayOf(
            hsbvalsOrig[0], hsbvalsOrig[1],
            (hsbvalsBrightnessSrc[2] + hsbvalsOrig[2]) / 2.0f
        )
    )
}

fun deriveByBrightness(original: Color, brightnessFactor: Float): Color {
    val hsbvalsOrig = RGBtoHSB(original)

    // Brightness factor is in -1.0...1.0 range. Negative values are treated as darkening
    // and positive values are treated as brightening - leaving the hue and saturation intact
    val newBrightness =
        if (brightnessFactor > 0.0f) hsbvalsOrig[2] + (1.0f - hsbvalsOrig[2]) * brightnessFactor else hsbvalsOrig[2] + hsbvalsOrig[2] * brightnessFactor
    return HSBtoRGB(
        floatArrayOf(hsbvalsOrig[0], hsbvalsOrig[1], newBrightness)
    )
}

/**
 * Inverts the specified color.
 *
 * @param color The original color.
 * @return The inverted color.
 */
fun invertColor(color: Color): Color {
    return Color(1.0f - color.red, 1.0f - color.green, 1.0f - color.blue, color.alpha)
}

/**
 * Returns a translucent of the specified color.
 *
 * @param color Color.
 * @param alpha Alpha channel value.
 * @return Translucent of the specified color that matches the requested
 * alpha channel value.
 */
fun getAlphaColor(color: Color, alpha: Float): Color {
    return Color(color.red, color.green, color.blue, alpha)
}

/**
 * Returns saturated version of the specified color.
 *
 * @param color  Color.
 * @param factor Saturation factor.
 * @return Saturated color.
 */
fun getSaturatedColor(color: Color, factor: Float): Color {
    val red = color.red
    val green = color.green
    val blue = color.blue
    if (red == green || green == blue) {
        // monochrome
        return color
    }
    val hsbvals = RGBtoHSB(color)
    var saturation = hsbvals[1]
    saturation = if (factor > 0.0) {
        saturation + factor * (1.0f - saturation)
    } else {
        saturation + factor * saturation
    }
    return HSBtoRGB(floatArrayOf(hsbvals[0], saturation, hsbvals[2]))
}

/**
 * Returns hue-shifted (in HSV space) version of the specified color.
 *
 * @param color    Color.
 * @param hueShift hue shift factor.
 * @return Hue-shifted (in HSV space) color.
 */
fun getHueShiftedColor(color: Color, hueShift: Float): Color {
    val hsbvals = RGBtoHSB(color)
    var hue = hsbvals[0]
    hue += hueShift.toFloat()
    if (hue < 0.0) {
        hue += 1.0f
    }
    if (hue > 1.0) {
        hue -= 1.0f
    }
    return HSBtoRGB(floatArrayOf(hue, hsbvals[1], hsbvals[2]))
}


