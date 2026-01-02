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

import androidx.compose.ui.graphics.toArgb
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun geminiSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    // Same seed for active and muted
    val geminiDefaultActiveTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFB0BBB8u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()
    )
    val geminiDefaultMutedTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFB0BBB8u.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.2
        )
    )
    val geminiDefaultNeutralTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFD1E1E0u.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.6
        )
    )

    val geminiHighlightContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFDC02u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()
    )

    val geminiHighlightOutlineContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFDC02u.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.3
        )
    )

    // Use muted visuals for the container surface roles, and highlight (yellow) tokens
    // for outline roles
    val geminiHighlightRolloverContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFB0BBB8u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = DefaultPaletteColorResolver.overlayWith(
            TokenPaletteColorResolverOverlay(
                containerOutline = { geminiHighlightOutlineContainerTokens.containerOutlineVariant.toArgb() },
                containerOutlineVariant = { geminiHighlightOutlineContainerTokens.containerOutlineVariant.toArgb() },
            )
        )
    )

    val geminiDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = geminiDefaultActiveTokens,
        mutedContainerTokens = geminiDefaultMutedTokens,
        neutralContainerTokens = geminiDefaultNeutralTokens,
        isSystemDark = false
    )

    // Highlight tokens for controls in selected states
    geminiDefaultBundle.registerActiveContainerTokens(
        geminiHighlightContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    // Highlight rollover for controls in rollover state
    geminiDefaultBundle.registerActiveContainerTokens(
        geminiHighlightRolloverContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverUnselected
    )

    // Highlights
    geminiDefaultBundle.registerActiveContainerTokens(
        geminiHighlightContainerTokens,
        ContainerColorTokensAssociationKind.Highlight,
        *ComponentState.activeStates
    )

    result.registerDecorationAreaTokensBundle(geminiDefaultBundle, DecorationAreaType.None)

    // Control panes, footers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFA9B4B1u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()
        ),
        DecorationAreaType.ControlPane,
        DecorationAreaType.Footer
    )

    // Toolbars
    val geminiToolbarBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFFDC02u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()
        ),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF142429u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 1.0
            )
        ),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF203042u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4
            )
        ),
        isSystemDark = true
    )
    result.registerDecorationAreaTokensBundle(geminiToolbarBundle, DecorationAreaType.Toolbar)

    // Headers
    val geminiHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFFDC02u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.8
            )
        ),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF1C282Du.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 1.0
            )
        ),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF142429u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.9
            )
        ),
        isSystemDark = true
    )
    result.registerDecorationAreaTokensBundle(geminiHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

fun geminiSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow
            }),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceLow else it.containerSurfaceHigh
            }),
            displayName = "Gemini"
        ),
        highlightSurfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow
            }),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
            displayName = "Gemini Highlight"
        ),
        outlinePainter = InlayOutlinePainter(
            displayName = "Gemini",
            outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
            inner = OutlineSpec(
                ColorStop(fraction = 0.0f, alpha = 0.375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 0.5f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 1.0f, alpha = 0.125f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            )
        ),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorTokensQueryTop = ContainerColorTokens::containerOutlineVariant,
            colorTokensQueryBottom = { it.inverseContainerOutline.withAlpha(0.28125f) },
        ),
        DecorationAreaType.Footer
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(ContainerColorTokens::containerOutline),
        DecorationAreaType.Header
    )
    painters.addOverlayPainter(
        TopLineOverlayPainter( { it.complementaryContainerOutline.withAlpha(0.1875f) }),
        DecorationAreaType.Toolbar
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
        BottomLineOverlayPainter(colorTokensQuery = ContainerColorTokens::containerOutline),
        DecorationAreaType.Toolbar
    )

    return AuroraSkinDefinition(
        displayName = "Gemini",
        colors = geminiSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
