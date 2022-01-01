/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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

import org.pushingpixels.aurora.common.withHueShift

/**
 * Hue-shifted color scheme. A hue-shifted color scheme is a color scheme that
 * is hue-shifted in HSB space.
 *
 * @author Kirill Grouchnikov
 */
class HueShiftColorScheme(
    origScheme: AuroraColorScheme,
    hueShiftFactor: Float
) : BaseColorScheme(
    displayName =
    "Hue-shift " + origScheme.displayName + " "
            + (100 * hueShiftFactor).toInt() + "%",
    isDark = origScheme.isDark,
    ultraLight = origScheme.ultraLightColor.withHueShift(hueShiftFactor),
    extraLight = origScheme.extraLightColor.withHueShift(hueShiftFactor),
    light = origScheme.lightColor.withHueShift(hueShiftFactor),
    mid = origScheme.midColor.withHueShift(hueShiftFactor),
    dark = origScheme.darkColor.withHueShift(hueShiftFactor),
    ultraDark = origScheme.ultraDarkColor.withHueShift(hueShiftFactor),
    foreground = origScheme.foregroundColor.withHueShift(hueShiftFactor / 2.0f)
)