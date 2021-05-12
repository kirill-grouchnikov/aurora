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
package org.pushingpixels.aurora.colorscheme

import org.pushingpixels.aurora.common.withSaturation

/**
 * Saturated color scheme. A saturated color scheme is a color scheme that is
 * saturated / desaturated (using HSV).
 *
 * @author Kirill Grouchnikov
 * @see ShiftColorScheme
 */
class SaturatedColorScheme(origScheme: AuroraColorScheme, saturationFactor: Float) : BaseColorScheme(
    displayName = "Saturated (" + (100 * saturationFactor).toInt() + "%) "
            + origScheme.displayName,
    isDark = origScheme.isDark,
    ultraLight = origScheme.ultraLightColor.withSaturation(saturationFactor),
    extraLight = origScheme.extraLightColor.withSaturation(saturationFactor),
    light = origScheme.lightColor.withSaturation(saturationFactor),
    mid = origScheme.midColor.withSaturation(saturationFactor),
    dark = origScheme.darkColor.withSaturation(saturationFactor),
    ultraDark = origScheme.ultraDarkColor.withSaturation(saturationFactor),
    foreground = origScheme.foregroundColor
)
