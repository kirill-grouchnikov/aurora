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
package org.pushingpixels.aurora.demo.titlepane.mail

import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.BrushedMetalDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun visorSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val visorDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9FC5E8u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDEDDDFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEFF8FFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)
    result.registerDecorationAreaTokensBundle(visorDefaultBundle, DecorationAreaType.None)

    // Custom palette resolver for the highlights in the threads decoration area
    // to set outline colors to be identical to surface colors (effectively removing the
    // visuals of the outlines)
    val threadsHighlightsPaletteResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { it.containerSurface },
            containerOutlineVariant = { it.containerSurfaceHigh },
        )
    )
    val visorThreadsBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9CBDD3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC9D5DEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE1EFF7u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)
    visorThreadsBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF5B91F8u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = threadsHighlightsPaletteResolver),
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected, ComponentState.RolloverSelected)
    visorThreadsBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF80B6CBu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = threadsHighlightsPaletteResolver),
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected)
    result.registerDecorationAreaTokensBundle(visorThreadsBundle, VisorDecorations.Threads)

    // Custom palette resolver for the highlights in the threads decoration area
    // to set outline colors to be identical to surface colors (effectively removing the
    // visuals of the outlines)
    val destinationsHighlightsPaletteResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { it.containerOutlineVariant },
            containerOutlineVariant = { it.containerOutlineVariant },
        )
    )
    val visorDestinationsBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9CBDD3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC9D5DEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD3E2EFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)
    visorDestinationsBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE8EDAFu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6),
            /* colorResolver */ destinationsHighlightsPaletteResolver),
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected, ComponentState.RolloverSelected)
    visorDestinationsBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD7E1C2u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6),
            /* colorResolver */ destinationsHighlightsPaletteResolver),
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected)
    result.registerDecorationAreaTokensBundle(visorDestinationsBundle, VisorDecorations.Destinations)

    // For the overall frame decoration border
    result.registerAsDecorationArea(getContainerTokens(
        seed = Hct.fromInt(0xFFC9D6DFu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.TitlePane)

    return result
}

fun visorSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = BrushedMetalDecorationPainter(
            colorQuery1 = { it.containerSurfaceBright },
            colorQuery2 = { it.containerSurfaceDim }),
        surfacePainter = MatteSurfacePainter(),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
            displayName = "Visor Highlight"
        ),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    return AuroraSkinDefinition(
        displayName = "Visor",
        colors = visorSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
