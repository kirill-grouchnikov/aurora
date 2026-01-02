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

import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun cremeBaseSkinColors(accentContainerColorTokens: AccentContainerColorTokens): AuroraSkinColors {
    val result = AuroraSkinColors()

    val cremeDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = accentContainerColorTokens.defaultAreaActiveTokens!!,
        mutedContainerTokens = accentContainerColorTokens.defaultAreaMutedTokens!!,
        neutralContainerTokens = accentContainerColorTokens.defaultAreaNeutralTokens!!,
        isSystemDark = false
    )
    cremeDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaSelectedTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    cremeDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected, ComponentState.Selected,
        ComponentState.RolloverSelected)
    cremeDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected)
    result.registerDecorationAreaTokensBundle(cremeDefaultBundle, DecorationAreaType.None)

    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFEBECE5u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.8)
        ),
        DecorationAreaType.TitlePane, DecorationAreaType.Header, DecorationAreaType.Toolbar,
        DecorationAreaType.ControlPane, DecorationAreaType.Footer)

    return result
}

private fun cremeBasePainters(): AuroraPainters {
    val outlinePainter = InlayOutlinePainter(
        displayName = "Creme",
        outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
        inner = OutlineSpec(
            ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
        )
    )
    val painters = AuroraPainters(
        decorationPainter = ArcDecorationPainter(),
        surfacePainter = SpecularRectangularSurfacePainter(MatteSurfacePainter(), 0.5f),
        outlinePainter = outlinePainter,
        highlightSurfacePainter = MatteSurfacePainter(),
        highlightOutlinePainter = outlinePainter
    )

    // Add overlay painters to paint drop shadows along the bottom edges of toolbars
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(40), DecorationAreaType.Toolbar)

    // add an overlay painter to paint a dark line along the bottom edge of toolbars
    painters.addOverlayPainter(BottomLineOverlayPainter(colorTokensQuery = { it.containerOutline }),
        DecorationAreaType.Toolbar)

    return painters
}

fun cremeSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC8E8F9u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFF0F1EBu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEEF3E5u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9DD9F9u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerOutline = { it.containerSurfaceHighest },
                    containerOutlineVariant = { it.containerSurfaceHigh }
                )
            )
        ),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFACDDF4u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.3),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerOutline = { it.containerSurfaceHighest },
                    containerOutlineVariant = { it.containerSurfaceHigh }
                )
            )
        ),
    )

    return AuroraSkinDefinition(
        displayName = "Creme",
        colors = cremeBaseSkinColors(accentContainerColorTokens),
        painters = cremeBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun cremeCoffeeSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDEC59Du.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFF0F1EBu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEEF3E5u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDFBF7Fu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE1C591u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.1)),
    )
    return AuroraSkinDefinition(
        displayName = "Creme Coffee",
        colors = cremeBaseSkinColors(accentContainerColorTokens),
        painters = cremeBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}
