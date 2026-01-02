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
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun nebulaBaseSkinColors(accentContainerColorTokens: AccentContainerColorTokens): AuroraSkinColors {
    val result = AuroraSkinColors()

    val nebulaDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFBAD2E3u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD7DBE1u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF3F7FDu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val nebulaRolloverHighlightContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF6B92AFu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultDark())
    val nebulaPressedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF276792u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultDark())
    val nebulaSelectedHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF5B85A6u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark())
    val nebulaDeterminateContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFD2852Fu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultDark())

    nebulaDefaultBundle.registerActiveContainerTokens(
        colorTokens = nebulaPressedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.PressedSelected, ComponentState.PressedUnselected)

    // Highlights
    nebulaDefaultBundle.registerActiveContainerTokens(
        colorTokens = nebulaRolloverHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)
    nebulaDefaultBundle.registerActiveContainerTokens(
        colorTokens = nebulaSelectedHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)

    // Progress bars
    nebulaDefaultBundle.registerActiveContainerTokens(
        colorTokens = nebulaDeterminateContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Determinate, ComponentState.Indeterminate)

    result.registerDecorationAreaTokensBundle(nebulaDefaultBundle, DecorationAreaType.None)

    val nebulaDecorationsColorTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFC2D1DAu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())
    result.registerAsDecorationArea(nebulaDecorationsColorTokens,
        DecorationAreaType.ControlPane, DecorationAreaType.Footer)

    val nebulaHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = accentContainerColorTokens.headerAreaActiveTokens!!,
        mutedContainerTokens = accentContainerColorTokens.headerAreaMutedTokens!!,
        neutralContainerTokens = accentContainerColorTokens.headerAreaNeutralTokens!!,
        isSystemDark = false)
    nebulaHeaderBundle.registerActiveContainerTokens(
        colorTokens = nebulaRolloverHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(nebulaHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

private fun nebulaBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
        decorationPainter = MarbleNoiseDecorationPainter(
            colorQuery1 = { it.containerSurface },
            colorQuery2 = { it.containerSurfaceHighest },
            textureAlpha = 0.2f,
            baseDecorationPainter = ArcDecorationPainter()
        ),
        surfacePainter = SpecularRectangularSurfacePainter(ClassicSurfacePainter(), 1.0f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = MatteSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    // add an overlay painter to paint a drop shadow along the top edge of toolbars
    painters.addOverlayPainter(
        TopShadowOverlayPainter.getInstance(60),
        DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter( { it.containerOutline } ),
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    return painters
}

fun nebulaSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        headerAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD9E8EDu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD6E3EEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD6E3EEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )
    return AuroraSkinDefinition(
        displayName = "Nebula",
        colors = nebulaBaseSkinColors(accentContainerColorTokens),
        painters = nebulaBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun nebulaAmethystSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        headerAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC2A9EFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD1A9F1u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD1A9F1u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    return AuroraSkinDefinition(
        displayName = "Nebula Amethyst",
        colors = nebulaBaseSkinColors(accentContainerColorTokens).also {
            // Also extend the window chrome accent color to the TOOLBAR area
            val nebulaAmethystToolbarBundle = ContainerColorTokensBundle(
                activeContainerTokens = getContainerTokens(
                    seed = Hct.fromInt(0xFFD264EBu.toInt()),
                    containerConfiguration = ContainerConfiguration.defaultLight()),
                 mutedContainerTokens = accentContainerColorTokens.headerAreaMutedTokens!!,
                 neutralContainerTokens = accentContainerColorTokens.headerAreaNeutralTokens!!,
                 isSystemDark = false)
            nebulaAmethystToolbarBundle.registerNeutralContainerTokens(getContainerTokens(
                seed = Hct.fromInt(0xFFD1A9F1u.toInt()),
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ -1.0)),
                ContainerColorTokensAssociationKind.Separator)
            it.registerDecorationAreaTokensBundle(nebulaAmethystToolbarBundle,
                DecorationAreaType.Toolbar)
        },
        painters = nebulaBasePainters().also { painters ->
            // Clear the top shadow painter on the toolbars and add combined
            // separator + drop shadow along the toolbar bottom
            painters.clearOverlayPainters(DecorationAreaType.Toolbar)
            painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
            painters.addOverlayPainter(
                BottomLineOverlayPainter({ it.containerOutline }), DecorationAreaType.Toolbar
            )

        },
        buttonShaper = ClassicButtonShaper()
    )
}

fun nebulaBrickWallSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        headerAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFBAC23u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF6C272u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        headerAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF6C272u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )
    return AuroraSkinDefinition(
        displayName = "Nebula Brick Wall",
        colors = nebulaBaseSkinColors(accentContainerColorTokens),
        painters = nebulaBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

