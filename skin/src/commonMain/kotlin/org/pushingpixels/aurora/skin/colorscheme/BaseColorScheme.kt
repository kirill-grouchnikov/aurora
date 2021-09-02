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
package org.pushingpixels.aurora.skin.colorscheme

import androidx.compose.ui.graphics.Color

open class BaseColorScheme(
    override val displayName: String,
    override val isDark: Boolean,
    val ultraLight: Color = Color.White,
    val extraLight: Color = Color.White,
    val light: Color = Color.White,
    val mid: Color = Color.White,
    val dark: Color = Color.White,
    val ultraDark: Color = Color.White,
    val foreground: Color = Color.Black
) : AuroraColorScheme {
    /**
     * Resolver for the derived colors.
     */
    private val derivedColorsResolver: SchemeDerivedColors =
        if (isDark) DerivedColorsResolverDark(this) else DerivedColorsResolverLight(this)

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

    override val backgroundFillColor: Color
        get() = derivedColorsResolver.backgroundFillColor
    override val accentedBackgroundFillColor: Color
        get() = derivedColorsResolver.accentedBackgroundFillColor
    override val focusRingColor: Color
        get() = derivedColorsResolver.focusRingColor
    override val lineColor: Color
        get() = derivedColorsResolver.lineColor
    override val selectionForegroundColor: Color
        get() = derivedColorsResolver.selectionForegroundColor
    override val selectionBackgroundColor: Color
        get() = derivedColorsResolver.selectionBackgroundColor
    override val textBackgroundFillColor: Color
        get() = derivedColorsResolver.textBackgroundFillColor
    override val separatorPrimaryColor: Color
        get() = derivedColorsResolver.separatorPrimaryColor
    override val separatorSecondaryColor: Color
        get() = derivedColorsResolver.separatorSecondaryColor
    override val markColor: Color
        get() = derivedColorsResolver.markColor
    override val echoColor: Color
        get() = derivedColorsResolver.echoColor

    override fun shift(
        backgroundShiftColor: Color,
        backgroundShiftFactor: Float,
        foregroundShiftColor: Color,
        foregroundShiftFactor: Float
    ): AuroraColorScheme {
        return ShiftColorScheme(
            this, backgroundShiftColor,
            backgroundShiftFactor, foregroundShiftColor,
            foregroundShiftFactor, true
        )
    }

    override fun shade(shadeFactor: Float): AuroraColorScheme {
        return ShadeColorScheme(this, shadeFactor)
    }

    override fun tint(tintFactor: Float): AuroraColorScheme {
        return TintColorScheme(this, tintFactor)
    }

    override fun tone(toneFactor: Float): AuroraColorScheme {
        return ToneColorScheme(this, toneFactor)
    }

    override fun negate(): AuroraColorScheme {
        return NegatedColorScheme(this)
    }

    override fun invert(): AuroraColorScheme {
        return InvertedColorScheme(this)
    }

    override fun saturate(saturateFactor: Float): AuroraColorScheme {
        return SaturatedColorScheme(this, saturateFactor)
    }

    override fun hueShift(hueShiftFactor: Float): AuroraColorScheme {
        return HueShiftColorScheme(this, hueShiftFactor)
    }

    override fun blendWith(otherScheme: AuroraColorScheme, likenessToThisScheme: Float): AuroraColorScheme {
        return BlendBiColorScheme(this, otherScheme, likenessToThisScheme)
    }
}