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
import androidx.compose.ui.graphics.toArgb
import org.pushingpixels.ephemeral.chroma.blend.Blend
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
    return Color(Blend.harmonizeAll(this.toArgb(), other.toArgb(), (1.0f - thisLikeness).toDouble()))
}

fun Color.interpolateTowardsAsRGB(other: Color, thisLikeness: Float): Int {
    require((thisLikeness >= 0.0f) && (thisLikeness <= 1.0f)) {
        "Color likeness should be in 0.0-1.0 range [is $thisLikeness]"
    }
    return Blend.harmonizeAll(this.toArgb(), other.toArgb(), (1.0f - thisLikeness).toDouble())
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



