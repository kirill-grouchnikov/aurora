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

interface MosaicColorScheme : SchemeBaseColors {
    fun displayName(): String

    /**
     * Returns indication whether this color scheme uses dark colors. Note that
     * this method may be removed in the future. It is highly recommended to use
     * one of the colors from the parent [SchemeBaseColors] and [SchemeDerivedColors]
     * interfaces instead.
     *
     * @return `true` if this color scheme uses dark colors,
     * `false` otherwise.
     */
    fun isDark(): Boolean

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
    ): MosaicColorScheme

    /**
     * Creates a tinted (shifted towards white) version of `this`
     * color scheme.
     *
     * @param tintFactor Value in 0.0...1.0 range. Larger values shift more towards
     * white color.
     * @return Tinted version of `this` scheme.
     */
    fun tint(tintFactor: Float): MosaicColorScheme

    /**
     * Creates a toned (shifted towards gray) version of `this` color
     * scheme.
     *
     * @param toneFactor Value in 0.0...1.0 range. Larger values shift more towards
     * gray color.
     * @return Toned version of `this` scheme.
     */
    fun tone(toneFactor: Float): MosaicColorScheme

    /**
     * Creates a shaded (shifted towards black) version of `this`
     * color scheme.
     *
     * @param shadeFactor Value in 0.0...1.0 range. Larger values shift more towards
     * black color.
     * @return Shaded version of `this` scheme.
     */
    fun shade(shadeFactor: Float): MosaicColorScheme

    /**
     * Creates a saturated or desaturated version of `this` scheme.
     * The value and brightness stay the same.
     *
     * @param saturateFactor Value in -1.0...1.0 range. Positive values create more
     * saturated colors. Negative values create more desaturated
     * colors.
     * @return Saturated version of `this` scheme.
     */
    fun saturate(saturateFactor: Float): MosaicColorScheme

    /**
     * Creates an inverted version of `this` scheme.
     *
     * @return Inverted version of `this` scheme.
     */
    fun invert(): MosaicColorScheme

    /**
     * Creates a negated version of `this` scheme.
     *
     * @return Negated version of `this` scheme.
     */
    fun negate(): MosaicColorScheme

    /**
     * Creates a hue-shifted (in HSB space) version of `this` color
     * scheme.
     *
     * @param hueShiftFactor Value in -1.0...1.0 range.
     * @return Hue-shifted version of `this` scheme.
     */
    fun hueShift(hueShiftFactor: Float): MosaicColorScheme

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
    fun blendWith(otherScheme: MosaicColorScheme, likenessToThisScheme: Float): MosaicColorScheme
}