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
package org.pushingpixels.mosaic.colorscheme

import androidx.compose.ui.graphics.Color

open class BaseColorScheme(
    val displayName: String,
    val _isDark: Boolean,
    val ultraLight: Color = Color.White,
    val extraLight: Color = Color.White,
    val light: Color = Color.White,
    val mid: Color = Color.White,
    val dark: Color = Color.White,
    val ultraDark: Color = Color.White,
    val foreground: Color
) : MosaicColorScheme {
    override val ultraLightColor: Color
        get() = ultraLight
    override val extraLightColor: Color
        get() = extraLight
    override val lightColor: Color
        get() = light
    override val midColor: Color
        get() = mid
    override val darkColor: Color
        get() = dark
    override val ultraDarkColor: Color
        get() = ultraDark
    override val foregroundColor: Color
        get() = foreground

    override fun displayName(): String {
        return displayName
    }

    override fun isDark(): Boolean {
        return _isDark
    }

    override fun shift(
        backgroundShiftColor: Color,
        backgroundShiftFactor: Float,
        foregroundShiftColor: Color,
        foregroundShiftFactor: Float
    ): MosaicColorScheme {
        return ShiftColorScheme(
            this, backgroundShiftColor,
            backgroundShiftFactor, foregroundShiftColor,
            foregroundShiftFactor, true
        )
    }

    override fun shade(shadeFactor: Float): MosaicColorScheme {
        return ShadeColorScheme(this, shadeFactor)
    }

    override fun tint(tintFactor: Float): MosaicColorScheme {
        return TintColorScheme(this, tintFactor)
    }

    override fun tone(toneFactor: Float): MosaicColorScheme {
        return ToneColorScheme(this, toneFactor)
    }

    override fun negate(): MosaicColorScheme {
        return NegatedColorScheme(this)
    }

    override fun invert(): MosaicColorScheme {
        return InvertedColorScheme(this)
    }

    override fun saturate(saturateFactor: Float): MosaicColorScheme {
        return SaturatedColorScheme(this, saturateFactor)
    }

    override fun hueShift(hueShiftFactor: Float): MosaicColorScheme {
        return HueShiftColorScheme(this, hueShiftFactor)
    }

    override fun blendWith(otherScheme: MosaicColorScheme, likenessToThisScheme: Float): MosaicColorScheme {
        return BlendBiColorScheme(this, otherScheme, likenessToThisScheme)
    }
}