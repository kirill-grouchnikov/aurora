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

import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.decorator.AuroraDecorators
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.decoration.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.decoration.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.surface.FlatSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.GlassSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicComponentShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun moderateSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val steelBlueDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF6CA9CEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDDE2E5u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEFF5FBu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val steelBlueHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFFF1D59Au.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())

    steelBlueDefaultBundle.registerActiveContainerTokens(
        colorTokens = steelBlueHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(steelBlueDefaultBundle, DecorationAreaType.None)

    val steelBlueHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF6D9BBAu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF82ABC7u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF6D9BBAu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        isSystemDark = false)

    val steelBlueHeaderHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF6EA7CAu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    steelBlueHeaderBundle.registerActiveContainerTokens(
        colorTokens = steelBlueHeaderHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    steelBlueHeaderBundle.registerActiveContainerTokens(
        colorTokens = steelBlueHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(steelBlueHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFD1D3D7u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.ControlPane)

    return result
}

fun moderateSkin(): AuroraSkinDefinition {
    val decorationPainter = MatteDecorationPainter()
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    decorationPainter.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
    // add an overlay painter to paint separator lines along the bottom
    // edges of menu bars
    decorationPainter.addOverlayPainter(
        BottomLineOverlayPainter(colorTokensQuery = { it.markerOnContainer.withAlpha(0.5f) }),
        DecorationAreaType.Header
    )

    val painters = AuroraPainters(
        decorationPainter = decorationPainter,
        surfacePainter = SpecularRectangularSurfacePainter(base = GlassSurfacePainter(), baseAlpha = 0.5f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = FlatSurfacePainter("Moderate",
            { if (it.isDark) it.containerSurfaceHighest else it.containerSurfaceLowest }),
        highlightOutlinePainter = FlatOutlinePainter()
    )
    return AuroraSkinDefinition(
        displayName = "Moderate",
        colors = moderateSkinColors(),
        painters = painters,
        componentShapers = AuroraComponentShapers.withDefaults(ClassicComponentShaper()),
        decorators = AuroraDecorators.buildDefault(),
    )
}

