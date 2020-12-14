/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.colorscheme

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.*

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