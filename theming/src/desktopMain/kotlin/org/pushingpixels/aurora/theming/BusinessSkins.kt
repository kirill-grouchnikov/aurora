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

import org.pushingpixels.aurora.theming.painter.decoration.BrushedMetalDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun businessBaseSkinColors(
    accentContainerColorTokens: AccentContainerColorTokens,
    isHeaderDark: Boolean
): AuroraSkinColors {
    val result = AuroraSkinColors()

    val businessDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = accentContainerColorTokens.defaultAreaActiveTokens!!,
        mutedContainerTokens = accentContainerColorTokens.defaultAreaMutedTokens!!,
        neutralContainerTokens = accentContainerColorTokens.defaultAreaNeutralTokens!!,
        isSystemDark = false)

    businessDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    businessDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaActiveTokens,
        associationKind = ContainerColorTokensAssociationKind.Tab,
        ComponentState.Selected, ComponentState.RolloverSelected)
    result.registerDecorationAreaTokensBundle(businessDefaultBundle, DecorationAreaType.None)

    val businessDefaultHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = accentContainerColorTokens.headerAreaActiveTokens!!,
        mutedContainerTokens = accentContainerColorTokens.headerAreaMutedTokens!!,
        neutralContainerTokens = accentContainerColorTokens.headerAreaNeutralTokens!!,
        isSystemDark = isHeaderDark)
    if (accentContainerColorTokens.headerAreaHighlightTokens != null) {
        businessDefaultHeaderBundle.registerActiveContainerTokens(
            colorTokens = accentContainerColorTokens.headerAreaHighlightTokens,
            associationKind = ContainerColorTokensAssociationKind.Highlight,
            activeStates = ComponentState.activeStates)
    }
    result.registerDecorationAreaTokensBundle(businessDefaultHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

private fun businessBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
        decorationPainter = BrushedMetalDecorationPainter(
            colorQuery1 = { if (it.isDark) it.containerSurfaceDim else it.containerSurfaceBright },
            colorQuery2 = { if (it.isDark) it.containerSurfaceBright else it.containerSurfaceDim },
        ),
        surfacePainter = SpecularRectangularSurfacePainter(base = ClassicSurfacePainter(), baseAlpha = 0.8f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter()
    )

    // add an overlay painter to paint a drop shadow along the top edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(80), DecorationAreaType.Toolbar)

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorTokensQuery = { it.containerOutline }),
        DecorationAreaType.Header
    )

    return painters
}

fun businessSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEAEDF3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFC4C8CCu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE4EAF0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBD296u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEAEDF3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDEDDDFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFBDC8D3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBD296u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    )
    return AuroraSkinDefinition(
        displayName = "Business",
        colors = businessBaseSkinColors(
            accentContainerColorTokens = accentContainerColorTokens,
            isHeaderDark = false
        ).also {
            val businessControlPaneBundle = ContainerColorTokensBundle(
                activeContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFEAEDF3u.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                 mutedContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFC4C8CCu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                 neutralContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFD4D9DFu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                isSystemDark = false
            )
            businessControlPaneBundle.registerActiveContainerTokens(
                colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens!!,
                associationKind = ContainerColorTokensAssociationKind.Highlight,
                activeStates = ComponentState.activeStates)
            it.registerDecorationAreaTokensBundle(businessControlPaneBundle, DecorationAreaType.ControlPane)

            val businessFooterBundle = ContainerColorTokensBundle(
                activeContainerTokens = accentContainerColorTokens.headerAreaActiveTokens!!,
                mutedContainerTokens = accentContainerColorTokens.headerAreaMutedTokens!!,
                neutralContainerTokens = accentContainerColorTokens.headerAreaNeutralTokens!!,
                isSystemDark = false
            )
            businessFooterBundle.registerActiveContainerTokens(
                accentContainerColorTokens.defaultAreaHighlightTokens,
                associationKind = ContainerColorTokensAssociationKind.Highlight,
                activeStates = ComponentState.activeStates)
            it.registerDecorationAreaTokensBundle(businessFooterBundle, DecorationAreaType.Footer)
        },
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun businessBlackSteelSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF98B7CCu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFC4C8CCu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE4EAF0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFA1BCCFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF404040u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        headerAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF606060u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        headerAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF555555u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        headerAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF85A3B5u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    )


    return AuroraSkinDefinition(
        displayName = "Business Blue Steel",
        colors = businessBaseSkinColors(
            accentContainerColorTokens = accentContainerColorTokens,
            isHeaderDark = true
        ).also {
            val businessBlueSteelControlBundle = ContainerColorTokensBundle(
                activeContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFF94B9D3u.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                mutedContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFBFCFDBu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                neutralContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFBFCFDBu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                isSystemDark = false
            )
            businessBlueSteelControlBundle.registerActiveContainerTokens(
                colorTokens = accentContainerColorTokens.defaultAreaActiveTokens!!,
                associationKind = ContainerColorTokensAssociationKind.Highlight,
                activeStates = ComponentState.activeStates)
            it.registerDecorationAreaTokensBundle(businessBlueSteelControlBundle,
                DecorationAreaType.ControlPane, DecorationAreaType.Footer)
        },
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun businessBlueSteelSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF98B7CCu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFC4C8CCu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE4EAF0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBD296u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF91B6CBu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9BBACDu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFA1BCD0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF83AFCEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    )

    return AuroraSkinDefinition(
        displayName = "Business Blue Steel",
        colors = businessBaseSkinColors(
            accentContainerColorTokens = accentContainerColorTokens,
            isHeaderDark = false
        ).also {
            val businessBlackSteelControlBundle = ContainerColorTokensBundle(
                activeContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFAFBEC7u.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                mutedContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFD5DBDFu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                neutralContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFD5DBDFu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                isSystemDark = false
            )
            businessBlackSteelControlBundle.registerActiveContainerTokens(
                colorTokens = accentContainerColorTokens.defaultAreaActiveTokens!!,
                associationKind = ContainerColorTokensAssociationKind.Highlight,
                activeStates = ComponentState.activeStates)
            it.registerDecorationAreaTokensBundle(businessBlackSteelControlBundle,
                DecorationAreaType.ControlPane, DecorationAreaType.Footer)
        },
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

