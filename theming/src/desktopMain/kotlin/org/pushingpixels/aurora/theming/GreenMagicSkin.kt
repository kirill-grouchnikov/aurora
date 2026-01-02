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

import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.getBimodalContainerTokens
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.dynamiccolor.DynamicBimodalPalette
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun greenMagicSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val greenMagicDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF00C5A9u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF8CDFB5u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFA3ECB9u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        isSystemDark = false)

    val greenMagicSelectedContainerTokens = getBimodalContainerTokens(
        seedOne = Hct.fromInt(0xFF00C6A8u.toInt()),
        seedTwo = Hct.fromInt(0xFF00E68Au.toInt()),
        transitionRange = DynamicBimodalPalette.TransitionRange.TONAL_CONTAINER_SURFACES,
        fidelityTone = 75.0,  // lighter tone for selected and rollover states
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.6),
        colorResolver = DefaultPaletteColorResolver)
    val greenMagicPressedContainerTokens = getBimodalContainerTokens(
        seedOne = Hct.fromInt(0xFF00BF7Fu.toInt()),
        seedTwo = Hct.fromInt(0xFF00B39Au.toInt()),
        transitionRange = DynamicBimodalPalette.TransitionRange.TONAL_CONTAINER_SURFACES,
        fidelityTone = 65.0,  // darker tone for pressed states
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.6),
        colorResolver = DefaultPaletteColorResolver)

    greenMagicDefaultBundle.registerActiveContainerTokens(
        colorTokens = greenMagicSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected, ComponentState.RolloverUnselected, ComponentState.RolloverSelected)
    greenMagicDefaultBundle.registerActiveContainerTokens(
        colorTokens = greenMagicSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected, ComponentState.RolloverUnselected, ComponentState.RolloverSelected)
    greenMagicDefaultBundle.registerActiveContainerTokens(
        colorTokens = greenMagicPressedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.PressedSelected, ComponentState.PressedUnselected)
    greenMagicDefaultBundle.registerActiveContainerTokens(
        colorTokens = greenMagicPressedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.PressedSelected, ComponentState.PressedUnselected)
    result.registerDecorationAreaTokensBundle(greenMagicDefaultBundle, DecorationAreaType.None)

    // Headers
    result.registerAsDecorationArea(getBimodalContainerTokens(
        seedOne = Hct.fromInt(0xFF4ECDAAu.toInt()),
        seedTwo = Hct.fromInt(0xFFA3ECB9u.toInt()),
        transitionRange = DynamicBimodalPalette.TransitionRange.TONAL_CONTAINER_SURFACES,
        fidelityTone = 85.0,
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.6),
        colorResolver = DefaultPaletteColorResolver),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    // Footers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF8ADFB5u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.Footer)

    return result
}

fun greenMagicSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = ArcDecorationPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurfaceLowest),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
            displayName = "Green Magic"
        ),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    // Add overlay painters to paint drop shadow and a dark line along the bottom
    // edges of headers
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Header)
    painters.addOverlayPainter(
        BottomLineOverlayPainter( { it.containerOutlineVariant } ),
        DecorationAreaType.Header
    )

    return AuroraSkinDefinition(
        displayName = "Green Magic",
        colors = greenMagicSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
