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
package org.pushingpixels.aurora.common

import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * Interpolates this color towards the `other` color. The closer `thisLikeness` is to 0.0,
 * the closer the resulting color will be to the `other` color.
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
    val a = (if (alpha1 == alpha2) alpha1 else
        thisLikeness * alpha1 + (1.0f - thisLikeness) * alpha2).coerceIn(0.0f, 1.0f)
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
    val a = (if (alpha1 == alpha2) alpha1 else
        thisLikeness * alpha1 + (1.0f - thisLikeness) * alpha2).coerceIn(0.0f, 1.0f)

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
    ).byAlpha(this.alpha)
}

fun Color.withBrightness(brightnessFactor: Float): Color {
    val hsbvalsOrig = RGBtoHSB(this)

    // Brightness factor is in -1.0...1.0 range. Negative values are treated as darkening
    // and positive values are treated as brightening - leaving the hue and saturation intact
    val newBrightness =
        if (brightnessFactor > 0.0f) hsbvalsOrig[2] + (1.0f - hsbvalsOrig[2]) * brightnessFactor else hsbvalsOrig[2] + hsbvalsOrig[2] * brightnessFactor
    return HSBtoRGB(
        floatArrayOf(hsbvalsOrig[0], hsbvalsOrig[1], newBrightness)
    ).byAlpha(this.alpha)
}

/** Returns the inverted version of this color. */
fun Color.inverted(): Color {
    return Color(1.0f - this.red, 1.0f - this.green, 1.0f - this.blue, this.alpha)
}

/** Returns the version of this color based on the specified alpha. */
fun Color.withAlpha(alpha: Float): Color {
    if (alpha == 1.0f) {
        return this
    }
    return Color(this.red, this.green, this.blue, alpha, this.colorSpace)
}

/** Returns the version of this color based on the specified alpha. */
fun Color.byAlpha(alpha: Float): Color {
    return Color(this.red, this.green, this.blue, this.alpha * alpha, this.colorSpace)
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
    return HSBtoRGB(floatArrayOf(hsbvals[0], saturation, hsbvals[2])).byAlpha(this.alpha)
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
    return HSBtoRGB(floatArrayOf(hue, hsbvals[1], hsbvals[2])).byAlpha(this.alpha)
}

/** Returns a lighter version of this color. */
fun Color.lighter(diff: Float): Color {
    return interpolateTowards(Color.White, 1.0f - diff)
}

/** Returns a darker version of this color. */
fun Color.darker(diff: Float): Color {
    return interpolateTowards(Color.Black, 1.0f - diff)
}


fun Color.overlayWith(overlay: Color): Color {
    val baseAlpha = this.alpha
    val overlayAlpha = overlay.alpha
    val finalAlpha = overlayAlpha + baseAlpha * (1.0f - overlayAlpha)

    if (finalAlpha == 0.0f) {
        return Color(0, 0, 0, 0)
    }

    val baseR = this.red
    val overlayR = overlay.red
    val finalR = (overlayR * overlayAlpha +
            (baseR * baseAlpha) * (1.0f - overlayAlpha)) / finalAlpha

    val baseG = this.green
    val overlayG = overlay.green
    val finalG = (overlayG * overlayAlpha +
            (baseG * baseAlpha) * (1.0f - overlayAlpha)) / finalAlpha

    val baseB = this.blue
    val overlayB = overlay.blue
    val finalB = (overlayB * overlayAlpha +
            (baseB * baseAlpha) * (1.0f - overlayAlpha)) / finalAlpha

    return Color(finalR, finalG, finalB, finalAlpha, this.colorSpace)
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



