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

import org.pushingpixels.aurora.common.inverted

/**
 * Implementation of negated color scheme. Negated color scheme is based on some
 * original color scheme, negating all the colors.
 *
 * @author Kirill Grouchnikov
 */
class NegatedColorScheme(origScheme: AuroraColorScheme) :
    BaseColorScheme(
        displayName = "Negated " + origScheme.displayName,
        isDark = origScheme.isDark,
        ultraLight = origScheme.ultraLightColor.inverted(),
        extraLight = origScheme.extraLightColor.inverted(),
        light = origScheme.lightColor.inverted(),
        mid = origScheme.midColor.inverted(),
        dark = origScheme.darkColor.inverted(),
        ultraDark = origScheme.ultraDarkColor.inverted(),
        foreground = origScheme.foregroundColor.inverted()
    )
