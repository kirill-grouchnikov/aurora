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
package org.pushingpixels.aurora.theming.colorscheme

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.theming.AuroraTrait

interface AuroraColorScheme : SchemeBaseColors, SchemeDerivedColors, AuroraTrait {
    /**
     * Returns indication whether this color scheme uses dark colors. Note that
     * this method may be removed in the future. It is highly recommended to use
     * one of the colors from the parent [SchemeBaseColors] and [SchemeDerivedColors]
     * interfaces instead.
     *
     * @return `true` if this color scheme uses dark colors,
     * `false` otherwise.
     */
    val isDark: Boolean

    /**
     * Creates a shift version of `this` scheme.
     *
     * @param backgroundShiftColor  Shift color for background colors. Should have full opacity.
     * @param backgroundShiftFactor Value in 0.0...1.0 range. Larger values shift more towards the
     * specified color.
     * @param foregroundShiftColor  Shift color for foreground colors. Should have full opacity.
     * @param foregroundShiftFactor Value in 0.0...1.0 range. Larger values shift more towards the
     * specified color.
     * @return Shift version of `this` scheme.
     */
    fun shift(
        backgroundShiftColor: Color,
        backgroundShiftFactor: Float, foregroundShiftColor: Color,
        foregroundShiftFactor: Float
    ): AuroraColorScheme

    /**
     * Creates a tinted (shifted towards white) version of `this`
     * color scheme.
     *
     * @param tintFactor Value in 0.0...1.0 range. Larger values shift more towards
     * white color.
     * @return Tinted version of `this` scheme.
     */
    fun tint(tintFactor: Float): AuroraColorScheme

    /**
     * Creates a toned (shifted towards gray) version of `this` color
     * scheme.
     *
     * @param toneFactor Value in 0.0...1.0 range. Larger values shift more towards
     * gray color.
     * @return Toned version of `this` scheme.
     */
    fun tone(toneFactor: Float): AuroraColorScheme

    /**
     * Creates a shaded (shifted towards black) version of `this`
     * color scheme.
     *
     * @param shadeFactor Value in 0.0...1.0 range. Larger values shift more towards
     * black color.
     * @return Shaded version of `this` scheme.
     */
    fun shade(shadeFactor: Float): AuroraColorScheme

    /**
     * Creates a saturated or desaturated version of `this` scheme.
     * The value and brightness stay the same.
     *
     * @param saturateFactor Value in -1.0...1.0 range. Positive values create more
     * saturated colors. Negative values create more desaturated
     * colors.
     * @return Saturated version of `this` scheme.
     */
    fun saturate(saturateFactor: Float): AuroraColorScheme

    /**
     * Creates an inverted version of `this` scheme.
     *
     * @return Inverted version of `this` scheme.
     */
    fun invert(): AuroraColorScheme

    /**
     * Creates a negated version of `this` scheme.
     *
     * @return Negated version of `this` scheme.
     */
    fun negate(): AuroraColorScheme

    /**
     * Creates a hue-shifted (in HSB space) version of `this` color
     * scheme.
     *
     * @param hueShiftFactor Value in -1.0...1.0 range.
     * @return Hue-shifted version of `this` scheme.
     */
    fun hueShift(hueShiftFactor: Float): AuroraColorScheme

    /**
     * Creates a blended version of `this` color scheme based on another
     * color scheme.
     *
     * @param otherScheme          The other color scheme for blending colors.
     * @param likenessToThisScheme Defines how close the colors of the resulting
     * color scheme are to this scheme. Value of 1.0 returns a color
     * scheme with the exact colors of this color scheme. Value of 0.0
     * returns a color scheme with the exact colors of the other color
     * scheme.
     * @return Blended color scheme.
     */
    fun blendWith(otherScheme: AuroraColorScheme, likenessToThisScheme: Float): AuroraColorScheme
}

fun composite(
    base: (AuroraColorScheme) -> Color,
    vararg transforms: (Color) -> Color
): (AuroraColorScheme) -> Color {
    return { scheme ->
        var result: Color = base.invoke(scheme)
        for (transform in transforms) {
            result = transform.invoke(result)
        }
        result
    }
}
