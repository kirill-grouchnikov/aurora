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

import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun magellanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val magellanDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = 
            getContainerTokens(
                seed = Hct.fromInt(0xFF0070DFu.toInt()),
                containerConfiguration = ContainerConfiguration( 
                    /* isDark */ true,  
                    /* contrastLevel */ -0.1),  
                colorResolver = DefaultPaletteColorResolver.overlayWith(
                    TokenPaletteColorResolverOverlay(
                        onContainer = { it.onContainer and 0xE0FFFFFFu.toInt() },
                        onContainerVariant = { it.onContainerVariant and 0xE0FFFFFFu.toInt() },
                    )
                )
            ),
        mutedContainerTokens = 
            getContainerTokens(
                seed = Hct.fromInt(0xFF004C92u.toInt()),  
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ true,
                    /* contrastLevel */ 0.1)
            ),
        neutralContainerTokens =
            getContainerTokens(
                seed = Hct.fromInt(0xFF005CB7u.toInt()), 
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ true,  
                    /* contrastLevel */ -0.2),
                colorResolver = DefaultPaletteColorResolver.overlayWith(
                    TokenPaletteColorResolverOverlay(
                        onContainer = { it.onContainer and 0xE0FFFFFFu.toInt() },
                        onContainerVariant = { it.onContainerVariant and 0xE0FFFFFFu.toInt() },
                    )
                )
            ),
        isSystemDark = true
    )

    val magellanSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF006FDBu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    onContainer = { it.onContainer and 0xE0FFFFFFu.toInt() },
                    onContainerVariant = { it.onContainerVariant and 0xE0FFFFFFu.toInt() },
                )
            )
        )

    val magellanPressedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00AEB8.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())

    val magellanGreenContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF1EBF00.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val magellanGreenRolloverContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00B933.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val magellanGreenHighlightSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00B000.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val magellanGreenHighlightRolloverContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00A422.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())

    // More saturated seed for controls in selected state
    magellanDefaultBundle.registerActiveContainerTokens(magellanSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // Less saturated seed for controls in pressed states
    magellanDefaultBundle.registerActiveContainerTokens(magellanPressedContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.PressedSelected, ComponentState.PressedUnselected)
    // Greens for rollovers
    magellanDefaultBundle.registerActiveContainerTokens(magellanGreenContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)

    // Marks
    magellanDefaultBundle.registerActiveContainerTokens(magellanGreenContainerTokens,
        ContainerColorTokensAssociationKind.Mark,
        ComponentState.Selected)
    magellanDefaultBundle.registerActiveContainerTokens(magellanGreenRolloverContainerTokens,
        ContainerColorTokensAssociationKind.Mark,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)
    magellanDefaultBundle.registerActiveContainerTokens(magellanPressedContainerTokens,
        ContainerColorTokensAssociationKind.Mark,
        ComponentState.PressedUnselected, ComponentState.PressedSelected)

    // Blues for active tabs
    magellanDefaultBundle.registerActiveContainerTokens(magellanSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Tab,
        *ComponentState.activeStates)
    // Greens for highlights
    magellanDefaultBundle.registerActiveContainerTokens(
        magellanGreenHighlightSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    magellanDefaultBundle.registerActiveContainerTokens(
        magellanGreenHighlightRolloverContainerTokens,
        ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)

    result.registerDecorationAreaTokensBundle(magellanDefaultBundle, DecorationAreaType.None)

    // Toolbars, control panes
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF004D99.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        DecorationAreaType.Toolbar, DecorationAreaType.ControlPane)

    val magellanFooterBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF006FDB.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFA0D8F7.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9DD2FF.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    result.registerDecorationAreaTokensBundle(magellanFooterBundle,
        DecorationAreaType.Footer)

    // Headers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF003367.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

fun magellanSkin(): AuroraSkinDefinition {
    val outlinePainter = InlayOutlinePainter(
        displayName = "Magellan",
        outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
        inner = OutlineSpec(
            ColorStop(fraction = 0.0f, alpha = 0.4375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 0.5f, alpha = 0.3125f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 1.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
        )
    )
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                if (it.isDark) {
                    it.containerSurfaceHighest.interpolateTowards(it.containerSurfaceHigh, 0.6f)
                } else {
                    it.containerSurfaceLowest.interpolateTowards(it.containerSurfaceLow, 0.6f)
                }
            }),
            ColorStop(fraction = 0.3f, colorQuery = {
                if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow
            }),
            ColorStop(fraction = 0.6f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceLowest else it.containerSurfaceHighest
            }),
            displayName = "Magellan"
        ),
        outlinePainter = outlinePainter,
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = outlinePainter
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
        BottomLineOverlayPainter(colorTokensQuery = { it.containerOutlineVariant }),
        DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a light line along the top
    // edge of toolbars
    painters.addOverlayPainter(
        TopLineOverlayPainter(colorTokensQuery = {
            it.inverseContainerOutline.withAlpha(0.375f)
        }), DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Footer)

    return AuroraSkinDefinition(
        displayName = "Magellan",
        colors = magellanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
