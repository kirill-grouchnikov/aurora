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
import org.pushingpixels.mosaic.utils.deriveByBrightness
import org.pushingpixels.mosaic.utils.getInterpolatedColor

/**
 * Base class for shifted color schemes. A shifted color scheme is based on some
 * original color scheme, a shift color and a shift factor. All colors of the
 * original color scheme are shifted towards the shift color based on the shift
 * factor. The closer the shift factor value is to 1.0, the closer the colors of
 * the shifted color scheme will be to the shift color.
 *
 * @author Kirill Grouchnikov
 */
open class ShiftColorScheme(
    origScheme: MosaicColorScheme, backgroundShiftColor: Color,
    backgroundShiftFactor: Float, foregroundShiftColor: Color,
    foregroundShiftFactor: Float, shiftByBrightness: Boolean
) : BaseColorScheme(
    displayName = "Shift " + origScheme.displayName() + " to backgr [" + backgroundShiftColor.toString() + "] "
            + (100 * backgroundShiftFactor).toInt() + "%, foregr [" + foregroundShiftColor.toString() + "]"
            + (100 * foregroundShiftFactor).toInt() + "%",
    backgroundStart = getInterpolatedColor(
        color1 = if (shiftByBrightness) deriveByBrightness(
            backgroundShiftColor,
            origScheme.backgroundColorStart
        ) else backgroundShiftColor,
        color2 = origScheme.backgroundColorStart,
        color1Likeness = backgroundShiftFactor
    ),
    backgroundEnd = getInterpolatedColor(
        color1 = if (shiftByBrightness) deriveByBrightness(
            backgroundShiftColor,
            origScheme.backgroundColorEnd
        ) else backgroundShiftColor,
        color2 = origScheme.backgroundColorEnd,
        color1Likeness = backgroundShiftFactor
    ),
    foreground = getInterpolatedColor(
        color1 = if (shiftByBrightness) deriveByBrightness(
            foregroundShiftColor,
            origScheme.foregroundColor
        ) else foregroundShiftColor,
        color2 = origScheme.foregroundColor,
        color1Likeness = foregroundShiftFactor
    ),
) {
    constructor (origScheme: MosaicColorScheme, shiftColor: Color, shiftFactor: Float) :
            this(origScheme, shiftColor, shiftFactor, shiftColor, shiftFactor / 2.0f, false)
}

/**
 * Shaded color scheme. A shaded color scheme is a color scheme that is shifted
 * towards black color.
 */
class ShadeColorScheme(origColorScheme: MosaicColorScheme, shadeFactor: Float) :
    ShiftColorScheme(origColorScheme, Color.Black, shadeFactor)

/**
 * Tinted color scheme. A tinted color scheme is a color scheme that is shifted
 * towards white color.
 */
class TintColorScheme(origColorScheme: MosaicColorScheme, shadeFactor: Float) :
    ShiftColorScheme(origColorScheme, Color.White, shadeFactor)

/**
 * Toned color scheme. A toned color scheme is a color scheme that is shifted
 * towards gray color.
 */
class ToneColorScheme(origColorScheme: MosaicColorScheme, shadeFactor: Float) :
    ShiftColorScheme(origColorScheme, Color.Gray, shadeFactor)

