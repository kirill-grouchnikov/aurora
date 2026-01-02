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
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun nightShadeSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val nightShadeDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF4E5562u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.5)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF373B45u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.5)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF292A32u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.5)),
        /* isSystemDark */ true)

    val nightShadeSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF3D4B63u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark())
    val nightShadeSelectedHighlightContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF414752u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark())

    // More saturated seed for controls in selected state
    nightShadeDefaultBundle.registerActiveContainerTokens(
        colorTokens = nightShadeSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // And less saturated seed for selected highlights
    nightShadeDefaultBundle.registerActiveContainerTokens(
        colorTokens = nightShadeSelectedHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    result.registerDecorationAreaTokensBundle(nightShadeDefaultBundle, DecorationAreaType.None)

    // Toolbars, footers, control panes
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF22252Au.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        DecorationAreaType.Footer, DecorationAreaType.Toolbar, DecorationAreaType.ControlPane)

    // Headers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF22252Au.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.6)),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

fun nightShadeSkin(): AuroraSkinDefinition {
    val outlinePainter = InlayOutlinePainter(
        displayName = "Night Shade",
        outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
        inner = OutlineSpec(
            ColorStop(fraction = 0.0f, alpha = 0.125f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 1.0f, alpha = 0.046875f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
        )
    )
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurfaceLow),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
            displayName = "Night Shade"
        ),
        outlinePainter = outlinePainter,
        highlightSurfacePainter = MatteSurfacePainter(),
        highlightOutlinePainter = outlinePainter,
    )

    // Add overlay painters to paint drop shadows along the bottom
    // edges of toolbars and footers
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.Toolbar, DecorationAreaType.Footer
    )

    // add an overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(BottomLineOverlayPainter( { it.containerOutlineVariant } ),
        DecorationAreaType.Toolbar)

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopBezelOverlayPainter(
        colorTokensQueryTop = { it.containerOutlineVariant },
        colorTokensQueryBottom = { it.inverseContainerOutline.withAlpha(0.28125f) }
    ), DecorationAreaType.Footer)

    return AuroraSkinDefinition(
        displayName = "Night Shade",
        colors = nightShadeSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
