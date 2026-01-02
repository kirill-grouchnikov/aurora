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

import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun marinerSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val marinerDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF6DD9Du.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD9D8D5u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFECF0F3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val marinerSelectedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFF5D47Au.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())
    val marinerSelectedHighlightContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFF7D997u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())

    // More saturated seed for controls in selected state
    marinerDefaultBundle.registerActiveContainerTokens(
        colorTokens = marinerSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // And less saturated seed for selected highlights
    marinerDefaultBundle.registerActiveContainerTokens(
        colorTokens = marinerSelectedHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    result.registerDecorationAreaTokensBundle(marinerDefaultBundle, DecorationAreaType.None)

    val marinerHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF5D47Au.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.8)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF281D1Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.8)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF261D1Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 1.0),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerOutline = { it.containerOutlineVariant }
                )
            )
        ),
        isSystemDark = true)

    // More saturated seed for controls in selected state
    marinerHeaderBundle.registerActiveContainerTokens(
        colorTokens = marinerSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        activeStates = ComponentState.activeStates)
    // More saturated highlights
    marinerHeaderBundle.registerActiveContainerTokens(
        colorTokens = marinerSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    // More muted separators
    marinerHeaderBundle.registerNeutralContainerTokens(
        getContainerTokens(
            seed = Hct.fromInt(0xFF261D1Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.7)),
        ContainerColorTokensAssociationKind.Separator)
    result.registerDecorationAreaTokensBundle(marinerHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    val marinerFooterBundle = ContainerColorTokensBundle (
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF6DD9Du.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC5C4C2u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFB9B7B9u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false);

    result.registerDecorationAreaTokensBundle(marinerFooterBundle,
        DecorationAreaType.Footer, DecorationAreaType.Toolbar, DecorationAreaType.ControlPane)

    return result
}

fun marinerSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLowest
            }),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceLow else it.containerSurfaceHigh
            }),
            displayName = "Mariner"
        ),
        highlightSurfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceLow else it.containerSurfaceHigh
            }),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow
            }),
            displayName = "Mariner Highlight"
        ),
        outlinePainter = InlayOutlinePainter(
            displayName = "Mariner",
            outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
            inner = OutlineSpec(
                ColorStop(fraction = 0.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 1.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            )
        ),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorTokensQueryTop = { it.containerOutline.withAlpha(0.3125f) },
            colorTokensQueryBottom = { it.inverseContainerOutline.withAlpha(0.1875f) }
        ),
        DecorationAreaType.Footer
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter( { it.containerSurfaceHighest } ),
        DecorationAreaType.Header
    )

    // add overlay painter to paint drop shadows along the bottom
    // edges of toolbars
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.Toolbar
    )

    // add overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorTokensQuery = { it.containerOutline.withAlpha(0.5f) }),
        DecorationAreaType.Toolbar
    )

    return AuroraSkinDefinition(
        displayName = "Mariner",
        colors = marinerSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
