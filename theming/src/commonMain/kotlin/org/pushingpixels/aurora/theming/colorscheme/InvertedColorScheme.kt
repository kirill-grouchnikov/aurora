/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
 * Implementation of inverted color scheme. Inverted color scheme is based on
 * some original color scheme, switching the dark colors by light colors and
 * inverting the foreground color.
 *
 * @author Kirill Grouchnikov
 */
class InvertedColorScheme(origScheme: AuroraColorScheme) :
    BaseColorScheme(
        displayName = "Negated ${origScheme.displayName}",
        isDark = origScheme.isDark,
        ultraLight = origScheme.ultraDarkColor.inverted(),
        extraLight = origScheme.darkColor.inverted(),
        light = origScheme.midColor.inverted(),
        mid = origScheme.lightColor.inverted(),
        dark = origScheme.extraLightColor.inverted(),
        ultraDark = origScheme.ultraLightColor.inverted(),
        foreground = origScheme.foregroundColor.inverted()
    )