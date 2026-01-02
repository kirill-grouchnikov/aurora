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

import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun sentinelSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val sentinelDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFEB79Eu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE8C3A6u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFFD8B6u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val sentinelSelectedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFF9E7Bu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())
    val sentinelSelectedHighlightContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFC0A5u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())

    // More saturated seed for controls in selected state
    sentinelDefaultBundle.registerActiveContainerTokens(
        colorTokens = sentinelSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // Less saturated seed for selected highlights
    sentinelDefaultBundle.registerActiveContainerTokens(
        colorTokens = sentinelSelectedHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    result.registerDecorationAreaTokensBundle(sentinelDefaultBundle, DecorationAreaType.None)

    // Headers
    val sentinelHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFEB79Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.8)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF4A2C25u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.8)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF2A0C05u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.6)),
        isSystemDark = true)

    sentinelHeaderBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDE9D87u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    // Lighter outlines for checkboxes and radio button menu items
    sentinelHeaderBundle.registerMutedContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF4A2C25u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerOutline = { it.onContainer and 0xC0FFFFFFu.toInt() },
                    containerOutlineVariant = { it.containerOutlineVariant and 0xC0FFFFFFu.toInt() },
                )
            )
        ),
        associationKind = ContainerColorTokensAssociationKind.Mark)
    sentinelHeaderBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF2A0C05u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.2),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerOutline = { it.onContainer and 0xC0FFFFFFu.toInt() },
                    containerOutlineVariant = { it.containerOutlineVariant and 0xC0FFFFFFu.toInt() },
                )
            )
        ),
        associationKind = ContainerColorTokensAssociationKind.Mark,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(sentinelHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    // Control panes
    val sentinelControlPaneBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFEB79Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ -0.6)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF8F543Bu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ -0.7)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF754133u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ -0.7)),
        isSystemDark = true)

    result.registerDecorationAreaTokensBundle(sentinelControlPaneBundle, DecorationAreaType.ControlPane)

    // Toolbars and footers
    val sentinelBarsBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFEB79Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.1)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF703723u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.1)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF53281Au.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.1)),
        isSystemDark = true)
    result.registerDecorationAreaTokensBundle(sentinelBarsBundle,
        DecorationAreaType.Toolbar, DecorationAreaType.Footer)

    return result
}

fun sentinelSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = MatteSurfacePainter(),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = MatteSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    // Add overlay painters to paint drop shadow and a dark line along the bottom
    // edges of toolbars
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.Toolbar
    )
    painters.addOverlayPainter(
        BottomLineOverlayPainter( { it.containerOutline } ),
        DecorationAreaType.Toolbar
    )

    // Add overlay painters to paint drop shadow and a dark line along the top
    // edges of footers
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(15), DecorationAreaType.Footer)
    painters.addOverlayPainter(
        TopLineOverlayPainter( { it.containerOutline } ),
        DecorationAreaType.Footer
    )

    return AuroraSkinDefinition(
        displayName = "Sentinel",
        colors = sentinelSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
