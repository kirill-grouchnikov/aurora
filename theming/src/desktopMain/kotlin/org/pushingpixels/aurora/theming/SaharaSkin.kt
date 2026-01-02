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
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun saharaSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val desertSandDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            /* seed */ Hct.fromInt(0xFFA5BB59u.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            /* seed */ Hct.fromInt(0xFFD5D9DE.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            /* seed */ Hct.fromInt(0xFFEFF5FB.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val desertHighlightContainerTokens = getContainerTokens(
        /* seed */ Hct.fromInt(0xFFCAD0BE.toInt()),
        /* containerConfiguration */ ContainerConfiguration.defaultLight())
    desertSandDefaultBundle.registerActiveContainerTokens(
        colorTokens = desertHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(desertSandDefaultBundle, DecorationAreaType.None)

    val desertSandHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            /* seed */ Hct.fromInt(0xFFA2B851.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            /* seed */ Hct.fromInt(0xFF99A764.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            /* seed */ Hct.fromInt(0xFF99A764.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        isSystemDark = false)
    val desertHeaderHighlightContainerTokens = getContainerTokens(
        /* seed */ Hct.fromInt(0xFFB2BC91.toInt()),
        /* containerConfiguration */ ContainerConfiguration.defaultLight())
    desertSandHeaderBundle.registerActiveContainerTokens(
        colorTokens = desertHeaderHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    result.registerDecorationAreaTokensBundle(desertSandHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

fun saharaSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = SpecularRectangularSurfacePainter(ClassicSurfacePainter(), 0.6f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter()
    )
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
    // add an overlay painter to paint separator lines along the bottom
    // edges of menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorTokensQuery = { it.containerOutline }),
        DecorationAreaType.Header
    )

    return AuroraSkinDefinition(
        displayName = "Sahara",
        colors = saharaSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

