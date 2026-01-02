/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolver
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper

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
    val painterOverlays: AuroraPainterOverlays?
        @Composable
        @ReadOnlyComposable
        get() = LocalPainterOverlays.current

    @OptIn(AuroraInternalApi::class)
    val animationConfig: AnimationConfig
        @Composable
        @ReadOnlyComposable
        get() = LocalAnimationConfig.current
}

data class AuroraSkinDefinition(
    override val displayName: String,
    val colors: AuroraSkinColors,
    val buttonShaper: AuroraButtonShaper,
    val painters: AuroraPainters
) : AuroraTrait

data class AccentContainerColorTokens(
    internal val defaultAreaPaletteColorResolver: TokenPaletteColorResolver = DefaultPaletteColorResolver,
    internal val defaultAreaActiveTokens: ContainerColorTokens? = null,
    internal val defaultAreaMutedTokens: ContainerColorTokens? = null,
    internal val defaultAreaNeutralTokens: ContainerColorTokens? = null,
    internal val defaultAreaHighlightTokens: ContainerColorTokens? = null,
    internal val defaultAreaSelectedTokens: ContainerColorTokens? = null,
    internal val headerAreaActiveTokens: ContainerColorTokens? = null,
    internal val headerAreaMutedTokens: ContainerColorTokens? = null,
    internal val headerAreaNeutralTokens: ContainerColorTokens? = null,
    internal val headerAreaHighlightTokens: ContainerColorTokens? = null)
