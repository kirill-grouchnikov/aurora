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
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colortokens.ContainerColorTokens
import org.pushingpixels.aurora.theming.colortokens.ContainerColorTokensBundle
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateFractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.border.FractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun magellanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/magellan.colorschemes"
        )
    )

    val blueControlsActive = schemes["Magellan Blue Controls Active"]
    val blueControlsEnabled = schemes["Magellan Blue Controls Enabled"]

    val defaultColorSchemeBundle = AuroraColorSchemeBundle(
        blueControlsActive, blueControlsEnabled, blueControlsEnabled
    )
    defaultColorSchemeBundle.registerAlpha(
        0.5f, ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsEnabled,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    // color schemes for the active states
    val blueControlsActiveBorder = schemes["Magellan Blue Controls Active Border"]
    val blueControlsEnabledBorder = schemes["Magellan Blue Controls Enabled Border"]
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Border,
        ComponentState.DisabledSelected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsEnabledBorder,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled,
        ComponentState.DisabledUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Mark,
        ComponentState.Selected
    )
    defaultColorSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Mark, ComponentState.DisabledSelected
    )

    // color schemes for the pressed states
    val blueControlsPressed = schemes["Magellan Blue Controls Pressed"]
    val blueControlsPressedBorder = schemes["Magellan Blue Controls Pressed Border"]
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsPressed,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsPressedBorder,
        ColorSchemeAssociationKind.Border,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )

    // color schemes for the rollover / armed states
    val greenControls = schemes["Magellan Green Controls"]
    val greenControlsRollover = schemes["Magellan Green Controls Rollover"]
    val blueActiveControlsPressed = schemes["Magellan Blue Active Controls Pressed"]
    val greenControlsMark = schemes["Magellan Green Controls Mark"]
    val activeControlsBorder = schemes["Magellan Green Controls Border"]
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsRollover,
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueActiveControlsPressed,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsMark,
        ColorSchemeAssociationKind.Mark,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected,
        ComponentState.PressedUnselected,
        ComponentState.PressedSelected,
        ComponentState.Selected
    )
    defaultColorSchemeBundle.registerColorScheme(
        activeControlsBorder,
        ColorSchemeAssociationKind.Border,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )

    // Also use active colors for selected checkboxes and radio buttons
    defaultColorSchemeBundle.registerColorScheme(
        greenControls,
        ColorSchemeAssociationKind.MarkBox,
        ComponentState.Selected
    )
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsRollover,
        ColorSchemeAssociationKind.MarkBox,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueActiveControlsPressed,
        ColorSchemeAssociationKind.MarkBox,
        ComponentState.PressedUnselected,
        ComponentState.PressedSelected
    )

    // color scheme for the selected state - preventing fallback to the
    // rollover selected state
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive,
        ColorSchemeAssociationKind.Fill, ComponentState.Selected
    )
    // But continue using green for selected highlight text
    defaultColorSchemeBundle.registerColorScheme(
        greenControls,
        ColorSchemeAssociationKind.HighlightText
    )

    // highlight alphas
    defaultColorSchemeBundle.registerHighlightAlpha(0.75f, ComponentState.RolloverUnselected)
    defaultColorSchemeBundle.registerHighlightAlpha(0.85f, ComponentState.Selected)
    defaultColorSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.RolloverSelected)
    defaultColorSchemeBundle.registerHighlightColorScheme(
        greenControls, ComponentState.RolloverUnselected,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive.tint(0.2f),
        ColorSchemeAssociationKind.Tab,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    val lightBlueBackground = schemes["Magellan Light Blue Background"]

    result.registerDecorationAreaSchemeBundle(
        defaultColorSchemeBundle,
        lightBlueBackground, DecorationAreaType.None
    )

    val mediumBlueBackground = schemes["Magellan Medium Blue Background"]
    val darkBlueBackground = schemes["Magellan Dark Blue Background"]
    result.registerAsDecorationArea(
        mediumBlueBackground,
        DecorationAreaType.ControlPane, DecorationAreaType.Toolbar
    )
    result.registerAsDecorationArea(
        darkBlueBackground,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
    )

    val lightBlueControlsActive = schemes["Magellan Light Blue Controls Active"]
    val lightBlueControlsEnabled = schemes["Magellan Light Blue Controls Enabled"]
    val lightBlueBordersEnabled = schemes["Magellan Light Blue Borders Enabled"]
    val footerColorSchemeBundle = AuroraColorSchemeBundle(
        lightBlueControlsActive, lightBlueControlsEnabled,
        lightBlueControlsEnabled
    )
    footerColorSchemeBundle.registerAlpha(
        0.5f, ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueControlsEnabled,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueControlsActive,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueBordersEnabled,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    val lightBlueSeparator = schemes["Magellan Light Blue Separator"]
    footerColorSchemeBundle.registerColorScheme(
        lightBlueSeparator,
        ColorSchemeAssociationKind.Separator
    )

    // And use light-on-blue for text highlights in the footer area
    footerColorSchemeBundle.registerColorScheme(
        blueControlsEnabled,
        ColorSchemeAssociationKind.HighlightText
    )

    val ultraLightBlueBackground = schemes["Magellan Ultralight Blue Background"]
    result.registerDecorationAreaSchemeBundle(
        footerColorSchemeBundle,
        ultraLightBlueBackground, DecorationAreaType.Footer
    )

    // TODO - remove everything above this comment
    val magellanDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = 
            getContainerTokens(
                seed = Hct.fromInt(0xFF0070DFu.toInt()),
                containerConfiguration = ContainerConfiguration( 
                    /* isDark */ true,  
                    /* contrastLevel */ -0.1),  
                colorResolver = DefaultPaletteColorResolver.overlayWith(
                    TokenPaletteColorResolverOverlay(
                        onContainer = { it.onContainer and 0xE0FFFFFFu.toInt() },
                        onContainerVariant = { it.onContainerVariant and 0xE0FFFFFFu.toInt() },
                    )
                )
            ),
        mutedContainerTokens = 
            getContainerTokens(
                seed = Hct.fromInt(0xFF004C92u.toInt()),  
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ true,
                    /* contrastLevel */ 0.1)
            ),
        neutralContainerTokens =
            getContainerTokens(
                seed = Hct.fromInt(0xFF005CB7u.toInt()), 
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ true,  
                    /* contrastLevel */ -0.2),
                colorResolver = DefaultPaletteColorResolver.overlayWith(
                    TokenPaletteColorResolverOverlay(
                        onContainer = { it.onContainer and 0xE0FFFFFFu.toInt() },
                        onContainerVariant = { it.onContainerVariant and 0xE0FFFFFFu.toInt() },
                    )
                )
            ),
        isSystemDark = true
    )

    val magellanSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF006FDBu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    onContainer = { it.onContainer and 0xE0FFFFFFu.toInt() },
                    onContainerVariant = { it.onContainerVariant and 0xE0FFFFFFu.toInt() },
                )
            )
        )

    val magellanPressedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00AEB8.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())

    val magellanGreenContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF1EBF00.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val magellanGreenRolloverContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00B933.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val magellanGreenHighlightSelectedContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00B000.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())
    val magellanGreenHighlightRolloverContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFF00A422.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight())

    // More saturated seed for controls in selected state
    magellanDefaultBundle.registerActiveContainerTokens(magellanSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    // Less saturated seed for controls in pressed states
    magellanDefaultBundle.registerActiveContainerTokens(magellanPressedContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.PressedSelected, ComponentState.PressedUnselected)
    // Greens for rollovers
    magellanDefaultBundle.registerActiveContainerTokens(magellanGreenContainerTokens,
        ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)

    // Marks
    magellanDefaultBundle.registerActiveContainerTokens(magellanGreenContainerTokens,
        ContainerColorTokensAssociationKind.Mark,
        ComponentState.Selected)
    magellanDefaultBundle.registerActiveContainerTokens(magellanGreenRolloverContainerTokens,
        ContainerColorTokensAssociationKind.Mark,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)
    magellanDefaultBundle.registerActiveContainerTokens(magellanPressedContainerTokens,
        ContainerColorTokensAssociationKind.Mark,
        ComponentState.PressedUnselected, ComponentState.PressedSelected)

    // Blues for active tabs
    magellanDefaultBundle.registerActiveContainerTokens(magellanSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Tab,
        *ComponentState.activeStates)
    // Greens for highlights
    magellanDefaultBundle.registerActiveContainerTokens(
        magellanGreenHighlightSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    magellanDefaultBundle.registerActiveContainerTokens(
        magellanGreenHighlightRolloverContainerTokens,
        ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverSelected, ComponentState.RolloverUnselected)

    result.registerDecorationAreaTokensBundle(magellanDefaultBundle, DecorationAreaType.None)

    // Toolbars, control panes
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF004D99.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        DecorationAreaType.Toolbar, DecorationAreaType.ControlPane)

    val magellanFooterBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF006FDB.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFA0D8F7.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF9DD2FF.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    result.registerDecorationAreaTokensBundle(magellanFooterBundle,
        DecorationAreaType.Footer)

    // Headers
    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFF003367.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

fun magellanSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.extraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.midColor },
            displayName = "Magellan"
        ),
        borderPainter = CompositeBorderPainter(
            displayName = "Magellan",
            outer = FractionBasedBorderPainter(
                0.0f to { it.ultraDarkColor },
                0.5f to { it.darkColor },
                1.0f to { it.darkColor },
                displayName = "Magellan Outer"
            ),
            inner = DelegateFractionBasedBorderPainter(
                displayName = "Magellan Inner",
                delegate = ClassicBorderPainter(),
                masks = longArrayOf(0xA0FFFFFF, 0x60FFFFFF, 0x40FFFFFF),
                transform = { it.tint(0.5f) })),
        decorationPainter = MatteDecorationPainter(),
        highlightFillPainter = ClassicFillPainter(),
        surfacePainter = FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = {
                if (it.isDark) {
                    it.containerSurfaceHighest.interpolateTowards(it.containerSurfaceHigh, 0.6f)
                } else {
                    it.containerSurfaceLowest.interpolateTowards(it.containerSurfaceLow, 0.6f)
                }
            }),
            ColorStop(fraction = 0.3f, colorQuery = {
                if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow
            }),
            ColorStop(fraction = 0.6f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = {
                if (it.isDark) it.containerSurfaceLowest else it.containerSurfaceHighest
            }),
            displayName = "Magellan"
        ),
        outlinePainter = InlayOutlinePainter(
            displayName = "Magellan",
            outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
            inner = OutlineSpec(
                ColorStop(fraction = 0.0f, alpha = 0.4375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 0.5f, alpha = 0.3125f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 1.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            )
        ),
        highlightSurfacePainter = ClassicSurfacePainter()
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
        BottomLineOverlayPainter(colorTokensQuery = { it.containerOutlineVariant }),
        DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a light line along the top
    // edge of toolbars
    painters.addOverlayPainter(
        TopLineOverlayPainter(colorTokensQuery = {
            it.inverseContainerOutline.withAlpha(0.375f)
        }), DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Footer)

    return AuroraSkinDefinition(
        displayName = "Magellan",
        colors = magellanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
