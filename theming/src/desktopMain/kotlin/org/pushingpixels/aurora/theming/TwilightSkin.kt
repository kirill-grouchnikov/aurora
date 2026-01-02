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

import androidx.compose.ui.graphics.toArgb
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun twilightSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    // For muted containers (enabled controls), use higher alpha values for disabled
    // controls for better contrast.
    val mutedResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerSurfaceDisabledAlpha = { 0.5f },
            onContainerDisabledAlpha = { 0.6f },
            containerOutlineDisabledAlpha = { 0.55f },
        )
    )
    val twilightDefaultMutedTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF3B3A32u.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ true,
            /* contrastLevel */ -0.1),
        colorResolver = mutedResolver)

    // For active containers, use higher alpha values for disabled
    // controls for better contrast. Also use muted outlines for border consistency
    // with enabled controls.
    val resolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { twilightDefaultMutedTokens.containerOutline.toArgb() },
            containerOutlineVariant = { twilightDefaultMutedTokens.containerOutlineVariant.toArgb() },
            containerSurfaceDisabledAlpha = { 0.4f },
            onContainerDisabledAlpha = { 0.6f },
            containerOutlineDisabledAlpha = { 0.55f },
        )
    )
    val twilightDefaultActiveTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF8F8B7Au.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.2),
        colorResolver = resolver)

    // For neutral containers, use the text / icon colors from the muted containers
    // for better visual consistency
    val neutralResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { twilightDefaultMutedTokens.containerOutline.toArgb() },
            containerOutlineVariant = { twilightDefaultMutedTokens.containerOutlineVariant.toArgb() },
        )
    )
    val twilightDefaultNeutralTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF48443Bu.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ true,
            /* contrastLevel */ -0.1),
        colorResolver = neutralResolver)

    val twilightPaletteContainerColorResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { it.onContainer },
            containerOutlineVariant = { it.onContainerVariant },
        )
    )

    val twilightSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF91865Du.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ -0.1),
            colorResolver = twilightPaletteContainerColorResolver)
    val twilightSelectedHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF8F8B7Au.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = twilightPaletteContainerColorResolver)

    val twilightDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = twilightDefaultActiveTokens,
        mutedContainerTokens = twilightDefaultMutedTokens,
        neutralContainerTokens = twilightDefaultNeutralTokens,
        isSystemDark = true
    )
    // More saturated seed for controls in selected state
    twilightDefaultBundle.registerActiveContainerTokens(
        colorTokens = twilightSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // And less saturated seed for selected highlights
    twilightDefaultBundle.registerActiveContainerTokens(
        colorTokens = twilightSelectedHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    // Selected tabs with active (not muted) outlines
    twilightDefaultBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF91865Du.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ -0.1)),
        associationKind = ContainerColorTokensAssociationKind.Tab,
        ComponentState.Selected)
    twilightDefaultBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF8F8B7Au.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ -0.1)),
        associationKind = ContainerColorTokensAssociationKind.Tab,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)
    result.registerDecorationAreaTokensBundle(twilightDefaultBundle, DecorationAreaType.None)

    // Toolbars, footers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF45433Au.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        DecorationAreaType.Footer, DecorationAreaType.Toolbar)

    // Control panes
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF504E45u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        DecorationAreaType.ControlPane)

    // Headers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF0E0E0Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)
    
    return result
}

fun twilightSkin(): AuroraSkinDefinition {
    val outlinePainter = InlayOutlinePainter(
        displayName = "Twilight",
        outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
        inner = OutlineSpec(
            ColorStop(fraction = 0.0f, alpha = 0.125f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 0.5f, alpha = 0.09375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 1.0f, alpha = 0.09375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
        )
    )
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                it.containerSurfaceHigh.interpolateTowards(it.containerSurface, 0.4f)
            }),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
            displayName = "Twilight"
        ),
        outlinePainter = outlinePainter,
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = outlinePainter,
    )

    // Add overlay painters to paint drop shadows along the bottom
    // edges of toolbars and footers
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Footer)

    // add an overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter( { it.containerOutlineVariant } ),
        DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a light line along the top
    // edge of toolbars
    painters.addOverlayPainter(
        TopLineOverlayPainter( { it.inverseContainerOutline.withAlpha(0.125f) } ),
        DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorTokensQueryTop = { it.containerOutlineVariant },
            colorTokensQueryBottom = { it.inverseContainerOutline.withAlpha(0.28125f) }
        ), DecorationAreaType.Footer
    )

    return AuroraSkinDefinition(
        displayName = "Twilight",
        colors = twilightSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
