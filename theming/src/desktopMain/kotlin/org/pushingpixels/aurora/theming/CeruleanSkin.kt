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

import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.GlassSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun ceruleanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val ceruleanDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD2E0EDu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFECECEDu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFBFCFCu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val ceruleanSelectedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFC0DBEEu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())
    val ceruleanSelectedHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFFFBDCA1u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val ceruleanRolloverHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFFF7E5C4u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val ceruleanTextHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFFFEDB7Cu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val ceruleanDeterminateContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFCFEAFEu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())

    // More saturated blue seed for controls in selected state
    ceruleanDefaultBundle.registerActiveContainerTokens(
        colorTokens = ceruleanSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // Yellow saturated seed for selected highlights
    ceruleanDefaultBundle.registerActiveContainerTokens(
        colorTokens = ceruleanSelectedHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    // Lighter yellow seed for rollover highlights
    ceruleanDefaultBundle.registerActiveContainerTokens(
        colorTokens = ceruleanRolloverHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected, ComponentState.RolloverSelected, ComponentState.RolloverMixed)
    // Text highlights
    ceruleanDefaultBundle.registerActiveContainerTokens(
        colorTokens = ceruleanTextHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        activeStates = ComponentState.activeStates)
    // Progress bars
    ceruleanDefaultBundle.registerActiveContainerTokens(
        colorTokens = ceruleanDeterminateContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Determinate, ComponentState.Indeterminate)
    result.registerDecorationAreaTokensBundle(ceruleanDefaultBundle, DecorationAreaType.None)

    val ceruleanHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC0DBEEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3B7BA8u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3B7BA8u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        isSystemDark = true);
    ceruleanHeaderBundle.registerActiveContainerTokens(
        colorTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFAEDCFFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    // Text highlights
    ceruleanHeaderBundle.registerActiveContainerTokens(
        colorTokens = ceruleanTextHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(ceruleanHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFCBD1D7u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.Footer, DecorationAreaType.ControlPane)

    return result
}

fun ceruleanSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = ArcDecorationPainter(),
        surfacePainter = SpecularRectangularSurfacePainter(base = ClassicSurfacePainter(), baseAlpha = 0.5f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = GlassSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter()
    )

    // Add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)

    return AuroraSkinDefinition(
        displayName = "Cerulean",
        colors = ceruleanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
