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

import org.pushingpixels.aurora.common.interpolateTowards

/**
 * Blended color scheme.
 *
 * @author Kirill Grouchnikov
 */
class BlendBiColorScheme(
    firstScheme: AuroraColorScheme,
    secondScheme: AuroraColorScheme, firstSchemeLikeness: Float
) : BaseColorScheme(
    displayName =
    "Blended " + firstScheme.displayName + " & "
            + secondScheme.displayName + " " + firstSchemeLikeness,
    isDark = firstScheme.isDark,
    ultraLight = firstScheme.ultraLightColor.interpolateTowards(
        secondScheme.ultraLightColor, firstSchemeLikeness
    ),
    extraLight = firstScheme.extraLightColor.interpolateTowards(
        secondScheme.extraLightColor, firstSchemeLikeness
    ),
    light = firstScheme.lightColor.interpolateTowards(
        secondScheme.lightColor, firstSchemeLikeness
    ),
    mid = firstScheme.midColor.interpolateTowards(
        secondScheme.midColor, firstSchemeLikeness
    ),
    dark = firstScheme.darkColor.interpolateTowards(
        secondScheme.darkColor, firstSchemeLikeness
    ),
    ultraDark = firstScheme.ultraDarkColor.interpolateTowards(
        secondScheme.ultraDarkColor, firstSchemeLikeness
    ),
    foreground = firstScheme.foregroundColor.interpolateTowards(
        secondScheme.foregroundColor, firstSchemeLikeness
    )
)