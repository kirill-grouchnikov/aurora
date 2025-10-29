/*
 * Copyright 2020-2025 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colortokens.ContainerColorTokens
import org.pushingpixels.aurora.theming.colortokens.ContainerColorTokensBundle
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateFractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun autumnSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/autumn.colorschemes"
        )
    )

    val activeScheme = schemes["Autumn Active"]
    val enabledScheme = schemes["Autumn Enabled"]
    val disabledScheme = enabledScheme

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    defaultSchemeBundle.registerAlpha(0.6f, ComponentState.DisabledUnselected, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.None
    )

    val titlePaneSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    titlePaneSchemeBundle.registerAlpha(0.6f, ComponentState.DisabledUnselected, ComponentState.DisabledSelected)
    titlePaneSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    titlePaneSchemeBundle.registerColorScheme(
        activeScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    val borderScheme = enabledScheme.saturate(0.2f)
    titlePaneSchemeBundle.registerColorScheme(
        borderScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    result.registerDecorationAreaSchemeBundle(
        bundle = titlePaneSchemeBundle,
        backgroundColorScheme = activeScheme,
        DecorationAreaType.TitlePane
    )

    val backgroundScheme = schemes["Autumn Background"]

    result.registerAsDecorationArea(
        activeScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
    )

    result.registerAsDecorationArea(
        backgroundScheme,
        DecorationAreaType.ControlPane, DecorationAreaType.Footer,
        DecorationAreaType.Toolbar
    )

    // TODO - remove everything above this comment

    // For active containers, use softer text / icon colors.
    // Also use higher alpha values for disabled controls for better contrast.
    val resolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            onContainer = { it.containerOutline },
            onContainerVariant = { it.containerOutlineVariant and 0xC0FFFFFFu.toInt() },
            containerSurfaceDisabledAlpha = { 0.4f },
            containerOutlineDisabledAlpha = { 0.55f }
        )
    )

    val autumnDefaultActiveTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFCB90u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = resolver
    )

    // For muted containers (enabled controls), use tonal on container and container outline
    // values for consistency with active controls. Also use higher alpha values for disabled
    // controls for better contrast.
    val mutedResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            containerOutlineVariant = { autumnDefaultActiveTokens.containerOutlineVariant.toArgb() },
            complementaryContainerOutline = { autumnDefaultActiveTokens.complementaryContainerOutline.toArgb() },
            onContainer = { autumnDefaultActiveTokens.onContainer.toArgb() },
            onContainerVariant = { autumnDefaultActiveTokens.onContainerVariant.toArgb() },
            containerSurfaceDisabledAlpha = { 0.5f },
            onContainerDisabledAlpha = { 0.6f },
            containerOutlineDisabledAlpha = { 0.55f },
        )
    )

    val autumnDefaultMutedTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFEDCB6u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = mutedResolver
    )

    // For neutral containers, use active on container and container outline
    // values for consistency with active controls.
    val neutralResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { autumnDefaultActiveTokens.containerOutline.toArgb() },
            containerOutlineVariant = { autumnDefaultActiveTokens.containerOutlineVariant.toArgb() },
            complementaryContainerOutline = { autumnDefaultActiveTokens.complementaryContainerOutline.toArgb() },
            onContainer = { autumnDefaultActiveTokens.onContainer.toArgb() },
            onContainerVariant = { autumnDefaultActiveTokens.onContainerVariant.toArgb() },
        )
    )

    val autumnDefaultNeutralTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFE2C1u.toInt()),
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
    // 1. Deeper container surfaces (more saturated seed in fidelity mode)
    // 2. Softer on container, mapped to container outline (used for texts and icons)
    // 3. Higher alpha values for disabled controls for better contrast
    autumnDefaultBundle.registerActiveContainerTokens(
        getContainerTokens(
            seed = Hct.fromInt(0xFFFDBD72u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = resolver
        ),
        ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected
    )
    autumnDefaultBundle.registerActiveContainerTokens(
        getContainerTokens(
            seed = Hct.fromInt(0xFFFCEF9Fu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.2),
            colorResolver = resolver
        ),
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
                    onContainer = { it.onContainerVariant }
                )
            )
        ),
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    val autumnControlPaneActiveTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFDBD72u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = resolver
    )
    val autumnControlPaneMutedTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFEDCB6u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = mutedResolver
    )
    val autumnControlPaneNeutralTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFED8B2u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()
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
    val painters = AuroraPainters(
        fillPainter = SpecularRectangularFillPainter(MatteFillPainter(), 0.2f),
        borderPainter = CompositeBorderPainter(
            displayName = "Autumn",
            outer = DelegateFractionBasedBorderPainter(
                displayName = "Autumn Outer",
                delegate = ClassicBorderPainter(),
                masks = longArrayOf(0xFFFFFFFFL, 0xFFFFFFFFL, 0xFFFFFFFFL),
                transform = { it.shade(0.1f) }),
            inner = DelegateFractionBasedBorderPainter(
                displayName = "Autumn Inner",
                delegate = ClassicBorderPainter(),
                masks = longArrayOf(0xFFFFFFFFL, 0xFFFFFFFFL, 0xFFFFFFFFL),
                transform = { it.tint(0.8f) })),
        highlightFillPainter = ClassicFillPainter(),
        decorationPainter = MarbleNoiseDecorationPainter(textureAlpha = 1.0f),
        surfacePainter = SpecularRectangularSurfacePainter(
            base = MatteSurfacePainter(),
            colorTokensQuery = { it.containerSurfaceLow },
            baseAlpha = 0.7f),
        outlinePainter = InlayOutlinePainter(
            displayName = "Autumn",
            outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutlineVariant),
            inner = OutlineSpec(
                ColorStop(fraction = 0.0f, alpha = 0.9375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 1.0f, alpha = 0.9375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            )
        ),
        highlightSurfacePainter = ClassicSurfacePainter()
    )
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(50), DecorationAreaType.Toolbar)
    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorTokensQuery = { it.containerOutlineVariant }),
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    return AuroraSkinDefinition(
        displayName = "Autumn",
        colors = autumnSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

