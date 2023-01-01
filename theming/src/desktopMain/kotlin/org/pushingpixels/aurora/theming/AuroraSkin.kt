/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.common.withBrightness
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colorscheme.ColorSchemes
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

object AuroraSkin {
    @OptIn(AuroraInternalApi::class)
    val displayName: String
        @Composable
        @ReadOnlyComposable
        get() = LocalDisplayName.current

    @OptIn(AuroraInternalApi::class)
    val decorationAreaType: DecorationAreaType
        @Composable
        @ReadOnlyComposable
        get() = LocalDecorationAreaType.current

    @OptIn(AuroraInternalApi::class)
    val colors: AuroraSkinColors
        @Composable
        @ReadOnlyComposable
        get() = LocalSkinColors.current

    @OptIn(AuroraInternalApi::class)
    val buttonShaper: AuroraButtonShaper
        @Composable
        @ReadOnlyComposable
        get() = LocalButtonShaper.current

    @OptIn(AuroraInternalApi::class)
    val painters: AuroraPainters
        @Composable
        @ReadOnlyComposable
        get() = LocalPainters.current

    @OptIn(AuroraInternalApi::class)
    val animationConfig: AnimationConfig
        @Composable
        @ReadOnlyComposable
        get() = LocalAnimationConfig.current
}

@OptIn(AuroraInternalApi::class)
@Composable
fun resolveAuroraDefaults() =
    resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)

data class AuroraSkinDefinition(
    override val displayName: String,
    val colors: AuroraSkinColors,
    val buttonShaper: AuroraButtonShaper,
    val painters: AuroraPainters
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

    fun getColorScheme(colorSchemeName: String): AuroraColorScheme? {
        return this.accentColorSchemes?.get(colorSchemeName)
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