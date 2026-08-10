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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.pushingpixels.aurora.common.interpolateTowardsAsRGB
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.decorator.AuroraDecorators
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.decoration.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.decoration.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.surface.FlatSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.*
import org.pushingpixels.aurora.theming.shaper.ClassicComponentShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun autumnSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    // For active containers, use softer outlines and shadows.
    // Also use higher alpha values for disabled controls for better contrast.
    val resolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { Color(it.containerOutline).interpolateTowardsAsRGB(Color(it.containerOutlineLow), 0.3f) },
            markerOnContainer = { it.containerOutline },
            complementaryMarkerOnContainer = { it.complementaryMarkerOnContainer and 0xC0FFFFFFu.toInt() },
            containerShadow = { it.containerOutline } ,
            containerSurfaceDisabledAlpha = { 0.4f },
            onContainerDisabledAlpha = { 0.6f },
            containerOutlineDisabledAlpha = { 0.55f }
        )
    )

    val autumnDefaultActiveTokens = getDuotoneContainerTokens(
        seedContainer = Hct.fromInt(0xFFFFCB90u.toInt()),
        seedOnContainer = Hct.fromInt(0xFF996F3Cu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = resolver
    )

    // For muted containers (enabled controls), use outlines and shadows consistent with active controls.
    // Also use higher alpha values for disabled controls for better contrast.
    val mutedResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            containerOutlineLow = { autumnDefaultActiveTokens.containerOutlineLow.toArgb() },
            containerOutlineHigh = { autumnDefaultActiveTokens.containerOutlineHigh.toArgb() },
            complementaryContainerOutline = { autumnDefaultActiveTokens.complementaryContainerOutline.toArgb() },
            containerShadow = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            markerOnContainer = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            complementaryMarkerOnContainer = { it.complementaryMarkerOnContainer and 0xA0FFFFFFu.toInt() },
            containerSurfaceDisabledAlpha = { 0.5f },
            onContainerDisabledAlpha = { 0.6f },
            containerOutlineDisabledAlpha = { 0.55f },
        )
    )

    val autumnDefaultMutedTokens = getDuotoneContainerTokens(
        seedContainer = Hct.fromInt(0xFFFEDCB6u.toInt()),
        seedOnContainer = Hct.fromInt(0xFF996F3Cu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = mutedResolver
    )

    // For neutral containers, use outlines and shadows consistent with active controls.
    val neutralResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            containerOutlineLow = { autumnDefaultActiveTokens.containerOutlineLow.toArgb() },
            containerOutlineHigh = { autumnDefaultActiveTokens.containerOutlineHigh.toArgb() },
            containerShadow = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            complementaryContainerOutline = { autumnDefaultActiveTokens.complementaryContainerOutline.toArgb() },
            markerOnContainer = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            complementaryMarkerOnContainer = { it.complementaryMarkerOnContainer and 0xA0FFFFFFu.toInt() },
        )
    )

    val autumnDefaultNeutralTokens = getDuotoneContainerTokens(
        seedContainer = Hct.fromInt(0xFFFFE2C1u.toInt()),
        seedOnContainer = Hct.fromInt(0xFF996F3Cu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = neutralResolver
    )

    val autumnDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = autumnDefaultActiveTokens,
        mutedContainerTokens = autumnDefaultMutedTokens,
        neutralContainerTokens = autumnDefaultNeutralTokens,
        isSystemDark = false
    )

    // Custom visuals for controls in selected state:
    // 1. Deeper container surfaces (more saturated seed)
    // 2. Softer on container
    // 3. Higher alpha values for disabled controls for better contrast
    autumnDefaultBundle.registerActiveContainerTokens(
        getDuotoneContainerTokens(
            seedContainer = Hct.fromInt(0xFFFDBD72u.toInt()),
            seedOnContainer = Hct.fromInt(0xFFA06C29u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = resolver),
        ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected
    )
    autumnDefaultBundle.registerActiveContainerTokens(
        getDuotoneContainerTokens(
            seedContainer = Hct.fromInt(0xFFFCEF9Fu.toInt()),
            seedOnContainer = Hct.fromInt(0xFF776E2Cu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.2),
            colorResolver = resolver),
        ContainerColorTokensAssociationKind.HighlightText,
        *ComponentState.activeStates
    )
    result.registerDecorationAreaTokensBundle(autumnDefaultBundle, DecorationAreaType.None)

    // Deeper container surfaces in title / header decoration areas, along with slightly
    // softer texts / icons (on container overlaid to be on container variant).
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFFEC983u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    onContainer = { it.onContainerLow },
                    markerOnContainer = { autumnDefaultActiveTokens.containerOutline.toArgb() },
                    complementaryMarkerOnContainer = { autumnDefaultActiveTokens.complementaryContainerOutline.toArgb() }
                )
            )
        ),
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    val autumnControlPaneActiveTokens = getDuotoneContainerTokens(
        seedContainer = Hct.fromInt(0xFFFDBD72u.toInt()),
        seedOnContainer = Hct.fromInt(0xFFA06C29u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = resolver
    )
    val autumnControlPaneMutedTokens = getDuotoneContainerTokens(
        seedContainer = Hct.fromInt(0xFFFEDCB6u.toInt()),
        seedOnContainer = Hct.fromInt(0xFF996F3Cu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = mutedResolver
    )
    val autumnControlPaneNeutralTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFED8B2u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = mutedResolver
    )

    val autumnControlPaneBundle = ContainerColorTokensBundle(
        activeContainerTokens = autumnControlPaneActiveTokens,
        mutedContainerTokens = autumnControlPaneMutedTokens,
        neutralContainerTokens = autumnControlPaneNeutralTokens,
        isSystemDark = false
    )
    autumnControlPaneBundle.registerActiveContainerTokens(
        getContainerTokens(
            seed = Hct.fromInt(0xFFFCEF9Fu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.2
            )
        ),
        ContainerColorTokensAssociationKind.HighlightText,
        *ComponentState.activeStates
    )
    result.registerDecorationAreaTokensBundle(
        autumnControlPaneBundle,
        DecorationAreaType.ControlPane
    )

    return result
}

fun autumnSkin(): AuroraSkinDefinition {
    val outlinePainter = InlayOutlinePainter(
        displayName = "Autumn",
        outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutlineLow),
        inner = OutlineSpec(
            ColorStop(fraction = 0.0f, alpha = 0.9375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 1.0f, alpha = 0.9375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
        )
    )

    val decorationPainter = MarbleNoiseDecorationPainter(
        colorQuery1 = { it.containerSurface },
        colorQuery2 = { it.containerSurfaceHighest },
        textureAlpha = 0.2f)
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    decorationPainter.addOverlayPainter(TopShadowOverlayPainter.getInstance(70), DecorationAreaType.Toolbar)
    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    decorationPainter.addOverlayPainter(
        BottomLineOverlayPainter(colorTokensQuery = { it.markerOnContainer.withAlpha(0.25f) }),
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    val painters = AuroraPainters(
        decorationPainter = decorationPainter,
        surfacePainter = SpecularRectangularSurfacePainter(
            base = MatteSurfacePainter(),
            topQuery = { it.containerSurfaceLow },
            bottomQuery = { it.containerSurfaceLow },
            baseAlpha = 0.7f),
        outlinePainter = outlinePainter,
        highlightSurfacePainter = FlatSurfacePainter("Autumn", { it.containerSurfaceLow }),
        highlightOutlinePainter = outlinePainter
    )

    return AuroraSkinDefinition(
        displayName = "Autumn",
        colors = autumnSkinColors(),
        painters = painters,
        componentShapers = AuroraComponentShapers.withDefaults(ClassicComponentShaper()),
        decorators = AuroraDecorators.buildDefault(),
    )
}

