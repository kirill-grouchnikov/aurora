/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.GlassSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun ravenSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val ravenDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF424242u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                // For active containers, use higher alpha values for
                // disabled controls for better contrast.
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.4f },
                    onContainerDisabledAlpha = { 0.3f },
                    containerOutlineDisabledAlpha = { 0.55f }
                )
            )
        ),        
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF504842u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                // For muted containers (enabled controls), use higher alpha values for
                // disabled controls for better contrast.
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.5f },
                    onContainerDisabledAlpha = { 0.3f },
                    containerOutlineDisabledAlpha = { 0.55f }
                )
            )
        ),        
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF333333u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        isSystemDark = true)

    val ravenHighlightContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFC4C3C5u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())

    val ravenSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFFCDD0D5u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.3),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.4f },
                    onContainerDisabledAlpha = { 1.0f },
                    containerOutlineDisabledAlpha = { 0.55f }
                )
            )
        )

    // Highlight tokens for controls in selected states
    ravenDefaultBundle.registerActiveContainerTokens(
        colorTokens = ravenSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected, ComponentState.RolloverSelected)
    // Highlight rollover for controls in rollover state
    ravenDefaultBundle.registerActiveContainerTokens(
        colorTokens = ravenHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverUnselected)
    // Highlights
    ravenDefaultBundle.registerActiveContainerTokens(
        colorTokens = ravenHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)

    result.registerDecorationAreaTokensBundle(ravenDefaultBundle, DecorationAreaType.None)

    // Decoration areas
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF4E463Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.6)),
        DecorationAreaType.TitlePane, DecorationAreaType.Header, DecorationAreaType.Toolbar,
        DecorationAreaType.ControlPane, DecorationAreaType.Footer)

    return result
}

fun ravenSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Raven",
        colors = ravenSkinColors(),
        painters = AuroraPainters(
            decorationPainter = ArcDecorationPainter(),
            surfacePainter = SpecularRectangularSurfacePainter(GlassSurfacePainter(), 0.5f),
            outlinePainter = FlatOutlinePainter(),
            highlightSurfacePainter = ClassicSurfacePainter(),
            highlightOutlinePainter = FlatOutlinePainter(),
        ),
        buttonShaper = ClassicButtonShaper()
    )
}

