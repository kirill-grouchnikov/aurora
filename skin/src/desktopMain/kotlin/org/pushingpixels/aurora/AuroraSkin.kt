/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableContract
import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.colorscheme.ColorSchemes
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.common.withBrightness
import org.pushingpixels.aurora.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

object AuroraSkin {
    @ComposableContract(readonly = true)
    val decorationAreaType: DecorationAreaType
        @Composable
        get() = AmbientDecorationAreaType.current

    @ComposableContract(readonly = true)
    val colors: AuroraSkinColors
        @Composable
        get() = AmbientSkinColors.current

    @ComposableContract(readonly = true)
    val buttonShaper: AuroraButtonShaper
        @Composable
        get() = AmbientButtonShaper.current

    @ComposableContract(readonly = true)
    val painters: Painters
        @Composable
        get() = AmbientPainters.current

    @ComposableContract(readonly = true)
    val animationConfig: AnimationConfig
        @Composable
        get() = AmbientAnimationConfig.current
}

data class AuroraSkinDefinition(
    override val displayName: String,
    val colors: AuroraSkinColors,
    val buttonShaper: AuroraButtonShaper,
    val painters: Painters
) : AuroraTrait

class AccentBuilder {
    internal var windowChromeAccent: AuroraColorScheme? = null
    internal var enabledControlsAccent: AuroraColorScheme? = null
    internal var activeControlsAccent: AuroraColorScheme? = null
    internal var highlightsAccent: AuroraColorScheme? = null
    internal var backgroundAccent: AuroraColorScheme? = null
    private var accentColorSchemes: ColorSchemes? = null

    fun withAccentResource(colorSchemeResourceName: String): AccentBuilder {
        accentColorSchemes = getColorSchemes(
            AuroraSkin::class.java.getResourceAsStream(colorSchemeResourceName)
        )
        require(accentColorSchemes != null) {
            "Could not load accent color schemes from $colorSchemeResourceName"
        }
        return this
    }

    fun withWindowChromeAccent(windowChromeAccentName: String): AccentBuilder {
        checkNotNull(accentColorSchemes) { "Builder not configured with accent resource file" }
        windowChromeAccent = accentColorSchemes!![windowChromeAccentName]
        return this
    }

    fun withWindowChromeAccent(windowChromeAccent: AuroraColorScheme): AccentBuilder {
        this.windowChromeAccent = windowChromeAccent
        return this
    }

    fun withActiveControlsAccent(activeControlsAccentName: String): AccentBuilder {
        checkNotNull(accentColorSchemes) { "Builder not configured with accent resource file" }
        activeControlsAccent = this.accentColorSchemes!![activeControlsAccentName]
        return this
    }

    fun withActiveControlsAccent(activeControlsAccent: AuroraColorScheme): AccentBuilder {
        this.activeControlsAccent = activeControlsAccent
        return this
    }

    fun withEnabledControlsAccent(enabledControlsAccentName: String): AccentBuilder {
        checkNotNull(this.accentColorSchemes) { "Builder not configured with accent resource file" }
        this.enabledControlsAccent = this.accentColorSchemes!![enabledControlsAccentName]
        return this
    }

    fun withEnabledControlsAccent(enabledControlsAccent: AuroraColorScheme): AccentBuilder {
        this.enabledControlsAccent = enabledControlsAccent
        return this
    }

    fun withHighlightsAccent(highlightsAccentName: String): AccentBuilder {
        checkNotNull(this.accentColorSchemes) { "Builder not configured with accent resource file" }
        this.highlightsAccent = this.accentColorSchemes!![highlightsAccentName]
        return this
    }

    fun withHighlightsAccent(highlightsAccent: AuroraColorScheme): AccentBuilder {
        this.highlightsAccent = highlightsAccent
        return this
    }

    fun withBackgroundAccent(backgroundAccentName: String): AccentBuilder {
        checkNotNull(this.accentColorSchemes) { "Builder not configured with accent resource file" }
        this.backgroundAccent = this.accentColorSchemes!![backgroundAccentName]
        return this
    }

    fun withBackgroundAccent(backgroundAccent: AuroraColorScheme): AccentBuilder {
        this.backgroundAccent = backgroundAccent
        return this
    }
}

object ColorTransforms {
    fun alpha(alpha: Float): (Color) -> Color {
        return { it.withAlpha(alpha) }
    }

    fun brightness(brightnessFactor: Float): (Color) -> Color {
        return { it.withBrightness(brightnessFactor) }
    }
}