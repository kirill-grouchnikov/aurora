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

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.darker
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.common.withBrightness

/**
 * Resolver of derived colors for dark color schemes. This class is not
 * accessible outside the package and is for internal use only.
 *
 * @author Kirill Grouchnikov
 */
internal class DerivedColorsResolverDark(scheme: AuroraColorScheme) : SchemeDerivedColors {
    /**
     * The original color scheme.
     */
    private val scheme: AuroraColorScheme
    override fun toString(): String {
        return "Resolver for " + scheme.displayName
    }

    override val lineColor: Color
        get() = scheme.midColor
    override val selectionForegroundColor: Color
        get() = scheme.foregroundColor
    override val selectionBackgroundColor: Color
        get() = scheme.ultraLightColor.withBrightness(0.2f)
    override  val backgroundFillColor: Color
        get() = scheme.midColor
    override val accentedBackgroundFillColor: Color
        get() = scheme.midColor.darker(0.08f)
    override val focusRingColor: Color
        get() = scheme.foregroundColor.withAlpha(0.75f)
    override val textBackgroundFillColor: Color
        get() = scheme.midColor.interpolateTowards(scheme.lightColor, 0.4f)
    override val separatorPrimaryColor: Color
        get() = scheme.extraLightColor
    override val separatorSecondaryColor: Color
        get() = scheme.darkColor
    override val markColor: Color
        get() = scheme.foregroundColor.interpolateTowards(scheme.ultraLightColor, 0.9f)
    override val echoColor: Color
        get() = scheme.ultraDarkColor

    /**
     * Creates the resolver of derived colors for the specified dark color scheme.
     */
    init {
        require(scheme.isDark) { "The scheme must be dark: " + scheme.displayName }
        this.scheme = scheme
    }
}