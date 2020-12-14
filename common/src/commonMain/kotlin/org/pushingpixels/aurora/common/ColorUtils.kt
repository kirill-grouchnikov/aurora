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
package org.pushingpixels.aurora.common

import androidx.compose.ui.graphics.Color
import kotlin.math.*

/**
 * Interpolates this color towards the `other` color. The closer `thisLikenss` is to 0.0,
 * the closer the resulting color will be to `other` color.
 */
fun Color.interpolateTowards(other: Color, thisLikeness: Float): Color {
    require((thisLikeness >= 0.0f) && (thisLikeness <= 1.0f)) {
        "Color likeness should be in 0.0-1.0 range [is $thisLikeness]"
    }
    val alpha1: Float = this.alpha
    val alpha2: Float = other.alpha
    val r = getInterpolatedChannelValue(this.red, other.red, thisLikeness)
    val g = getInterpolatedChannelValue(this.green, other.green, thisLikeness)
    val b = getInterpolatedChannelValue(this.blue, other.blue, thisLikeness)
    val a = if (alpha1 == alpha2) alpha1 else
        round(thisLikeness * alpha1 + (1.0f - thisLikeness) * alpha2)
    return Color(r, g, b, a, this.colorSpace)
}

fun Color.interpolateTowardsAsRGB(other: Color, thisLikeness: Float): Int {
    require((thisLikeness >= 0.0f) && (thisLikeness <= 1.0f)) {
        "Color likeness should be in 0.0-1.0 range [is $thisLikeness]"
    }
    val alpha1: Float = this.alpha
    val alpha2: Float = other.alpha
    val r = getInterpolatedChannelValue(this.red, other.red, thisLikeness)
    val g = getInterpolatedChannelValue(this.green, other.green, thisLikeness)
    val b = getInterpolatedChannelValue(this.blue, other.blue, thisLikeness)
    val a = if (alpha1 == alpha2) alpha1 else
        round(thisLikeness * alpha1 + (1.0f - thisLikeness) * alpha2)

    return ((a * 255.0f + 0.5f).toInt() shl 24) or
            ((r * 255.0f + 0.5f).toInt() shl 16) or
            ((g * 255.0f + 0.5f).toInt() shl 8) or
            (b * 255.0f + 0.5f).toInt()
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

fun RGBtoHSB(fromArgb: Int): FloatArray {
    val r = fromArgb ushr 16 and 0xFF
    val g = fromArgb ushr 8 and 0xFF
    val b = fromArgb ushr 0 and 0xFF

    return RGBtoHSB(r = r / 255.0f, g = g / 255.0f, b = b / 255.0f)
}

fun RGBtoHSB(from: Color): FloatArray {
    return RGBtoHSB(from.red, from.green, from.blue)
}

// See https://en.wikipedia.org/wiki/HSL_and_HSV#From_RGB
fun RGBtoHSB(r: Float, g: Float, b: Float): FloatArray {
    val result = FloatArray(3)

    val xmax = max(max(r, g), b)
    val xmin = min(min(r, g), b)
    val chroma = xmax - xmin

    // brightness
    result[2] = xmax
    // saturation
    result[1] = if (result[2] == 0.0f) 0.0f else chroma / result[2]
    // hue
    if (chroma == 0.0f) {
        result[0] = 0.0f
    } else {
        if (xmax == r) {
            result[0] = (1.0f / 6.0f) * ((g - b) / chroma)
        } else if (xmax == g) {
            result[0] = (1.0f / 6.0f) * (2 + (b - r) / chroma)
        } else {
            result[0] = (1.0f / 6.0f) * (4 + (r - g) / chroma)
        }
        if (result[0] < 0.0f) {
            result[0] = 0.0f
        }
    }
    return result
}

// See https://en.wikipedia.org/wiki/HSL_and_HSV#HSV_to_RGB
fun HSBtoRGB(from: FloatArray): Color {
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

fun Color.withBrightness(brightnessSource: Color): Color {
    val hsbvalsOrig = RGBtoHSB(this)
    val hsbvalsBrightnessSrc = RGBtoHSB(brightnessSource)
    return HSBtoRGB(
        floatArrayOf(
            hsbvalsOrig[0], hsbvalsOrig[1],
            (hsbvalsBrightnessSrc[2] + hsbvalsOrig[2]) / 2.0f
        )
    )
}

fun Color.withBrightness(brightnessFactor: Float): Color {
    val hsbvalsOrig = RGBtoHSB(this)

    // Brightness factor is in -1.0...1.0 range. Negative values are treated as darkening
    // and positive values are treated as brightening - leaving the hue and saturation intact
    val newBrightness =
        if (brightnessFactor > 0.0f) hsbvalsOrig[2] + (1.0f - hsbvalsOrig[2]) * brightnessFactor else hsbvalsOrig[2] + hsbvalsOrig[2] * brightnessFactor
    return HSBtoRGB(
        floatArrayOf(hsbvalsOrig[0], hsbvalsOrig[1], newBrightness)
    )
}

/** Returns the inverted version of this color. */
fun Color.inverted(): Color {
    return Color(1.0f - this.red, 1.0f - this.green, 1.0f - this.blue, this.alpha)
}

/** Returns the version of this color based on the specified alpha. */
fun Color.withAlpha(alpha: Float): Color {
    return Color(this.red, this.green, this.blue, alpha)
}

/** Returns saturated version of this color. */
fun Color.withSaturation(factor: Float): Color {
    val red = this.red
    val green = this.green
    val blue = this.blue
    if (red == green || green == blue) {
        // monochrome
        return this
    }
    val hsbvals = RGBtoHSB(this)
    var saturation = hsbvals[1]
    saturation = if (factor > 0.0) {
        saturation + factor * (1.0f - saturation)
    } else {
        saturation + factor * saturation
    }
    return HSBtoRGB(floatArrayOf(hsbvals[0], saturation, hsbvals[2]))
}

/** Returns hue-shifted (in HSB space) version of this color. */
fun Color.withHueShift(hueShift: Float): Color {
    val hsbvals = RGBtoHSB(this)
    var hue = hsbvals[0]
    hue += hueShift
    if (hue < 0.0) {
        hue += 1.0f
    }
    if (hue > 1.0) {
        hue -= 1.0f
    }
    return HSBtoRGB(floatArrayOf(hue, hsbvals[1], hsbvals[2]))
}

/** Returns a darker version of this color. */
fun Color.darker(diff: Float): Color {
    return interpolateTowards(Color.Black, 1.0f - diff)
}

/** Returns the brightness of this color in [0.0-1.0] range ignoring the alpha. */
val Color.colorBrightness: Float
    get() = getColorBrightness(this.red, this.green, this.blue)

val Color.colorStrength: Float
    get() = max(this.colorBrightness, this.inverted().colorBrightness)

/** Returns the brightness of the specified color values in [0.0-1.0] range. */
fun getColorBrightness(r: Float, g: Float, b: Float): Float {
    // See https://en.wikipedia.org/wiki/Relative_luminance
    return (2126.0f * r + 7152.0f * g + 722.0f * b) / 10000.0f
}

private fun encodeChannel(number: Float): String {
    require(!(number < 0 || number > 1.0f)) { "" + number }
    val hex = "0123456789ABCDEF"
    val asInt = (255.0f * number + 0.5f).toInt()
    val hexaDigit1 = hex[asInt / 16]
    val hexaDigit2 = hex[asInt % 16]
    return hexaDigit1.toString() + "" + hexaDigit2
}

val Color.hexadecimal: String
    get() = ("#" + encodeChannel(this.alpha) + encodeChannel(this.red) + encodeChannel(this.green) + encodeChannel(this.blue))



