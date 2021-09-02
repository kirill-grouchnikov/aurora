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
import org.pushingpixels.aurora.common.byAlpha
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withBrightness

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
    origScheme: AuroraColorScheme, backgroundShiftColor: Color,
    backgroundShiftFactor: Float, foregroundShiftColor: Color,
    foregroundShiftFactor: Float, shiftByBrightness: Boolean
) : BaseColorScheme(
    displayName = "Shift " + origScheme.displayName + " to backgr [" + backgroundShiftColor.toString() + "] "
            + (100 * backgroundShiftFactor).toInt() + "%, foregr [" + foregroundShiftColor.toString() + "]"
            + (100 * foregroundShiftFactor).toInt() + "%",
    isDark = origScheme.isDark,
    ultraLight = (if (shiftByBrightness)
        backgroundShiftColor.withBrightness(origScheme.ultraLightColor) else backgroundShiftColor).interpolateTowards(
        origScheme.ultraLightColor, backgroundShiftFactor
    ).byAlpha(origScheme.ultraLightColor.alpha),
    extraLight = (if (shiftByBrightness)
        backgroundShiftColor.withBrightness(origScheme.extraLightColor) else backgroundShiftColor).interpolateTowards(
        origScheme.extraLightColor, backgroundShiftFactor
    ).byAlpha(origScheme.extraLightColor.alpha),
    light = (if (shiftByBrightness)
        backgroundShiftColor.withBrightness(origScheme.lightColor) else backgroundShiftColor).interpolateTowards(
        origScheme.lightColor, backgroundShiftFactor
    ).byAlpha(origScheme.lightColor.alpha),
    mid = (if (shiftByBrightness)
        backgroundShiftColor.withBrightness(origScheme.midColor) else backgroundShiftColor).interpolateTowards(
        origScheme.midColor, backgroundShiftFactor
    ).byAlpha(origScheme.midColor.alpha),
    dark = (if (shiftByBrightness)
        backgroundShiftColor.withBrightness(origScheme.darkColor) else backgroundShiftColor).interpolateTowards(
        origScheme.darkColor, backgroundShiftFactor
    ).byAlpha(origScheme.darkColor.alpha),
    ultraDark = (if (shiftByBrightness)
        backgroundShiftColor.withBrightness(origScheme.ultraDarkColor) else backgroundShiftColor).interpolateTowards(
        origScheme.ultraDarkColor, backgroundShiftFactor
    ).byAlpha(origScheme.ultraDarkColor.alpha),
    foreground = (if (shiftByBrightness)
        foregroundShiftColor.withBrightness(origScheme.foregroundColor) else foregroundShiftColor).interpolateTowards(
        origScheme.foregroundColor, foregroundShiftFactor
    ).byAlpha(origScheme.foregroundColor.alpha),
) {
    constructor(origScheme: AuroraColorScheme, shiftColor: Color, shiftFactor: Float) :
            this(origScheme, shiftColor, shiftFactor, shiftColor, shiftFactor / 2.0f, false)
}

/**
 * Shaded color scheme. A shaded color scheme is a color scheme that is shifted
 * towards black color.
 */
class ShadeColorScheme(origColorScheme: AuroraColorScheme, shadeFactor: Float) :
    ShiftColorScheme(origColorScheme, Color.Black, shadeFactor)

/**
 * Tinted color scheme. A tinted color scheme is a color scheme that is shifted
 * towards white color.
 */
class TintColorScheme(origColorScheme: AuroraColorScheme, shadeFactor: Float) :
    ShiftColorScheme(origColorScheme, Color.White, shadeFactor)

/**
 * Toned color scheme. A toned color scheme is a color scheme that is shifted
 * towards gray color.
 */
class ToneColorScheme(origColorScheme: AuroraColorScheme, shadeFactor: Float) :
    ShiftColorScheme(origColorScheme, Color.Gray, shadeFactor)

