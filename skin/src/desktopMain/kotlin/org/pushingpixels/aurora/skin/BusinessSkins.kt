/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.skin

import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.*
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.decoration.BrushedMetalDecorationPainter
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun businessBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val businessSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/business.colorschemes"
        )
    )

    val enabledScheme = businessSchemes["Business Enabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        accentBuilder.activeControlsAccent!!, enabledScheme, enabledScheme
    )

    defaultSchemeBundle.registerHighlightColorScheme(accentBuilder.highlightsAccent!!)

    defaultSchemeBundle.registerAlpha(
        0.5f,
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        enabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED, ComponentState.SELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.TAB, ComponentState.SELECTED,
        ComponentState.ROLLOVER_SELECTED
    )

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.NONE)

    result.registerAsDecorationArea(
        accentBuilder.windowChromeAccent!!,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER, DecorationAreaType.FOOTER
    )

    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes"
        )
    )

    result.registerAsDecorationArea(
        kitchenSinkSchemes["LightGray Control Pane Background"],
        DecorationAreaType.CONTROL_PANE
    )

    return result
}

private fun businessBasePainters(): Painters {
    val painters = Painters(
        fillPainter = ClassicFillPainter(),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = BrushedMetalDecorationPainter()
    )

    // add an overlay painter to paint a drop shadow along the top edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(80), DecorationAreaType.TOOLBAR)

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.midColor }),
        DecorationAreaType.HEADER
    )

    return painters
}

fun businessSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Business",
        colors = businessBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/business.colorschemes")
                .withWindowChromeAccent("Business Enabled")
                .withActiveControlsAccent("Business Active")
                .withHighlightsAccent("Business Highlight")
        ),
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun businessBlackSteelSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Business Black Steel",
        colors = businessBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/business.colorschemes")
                .withWindowChromeAccent("Business Black Steel Active Header")
                .withActiveControlsAccent("Business Black Steel Active")
                .withHighlightsAccent("Business Black Steel Active")
        ).also {
            val businessSchemes = getColorSchemes(
                AuroraSkin::class.java.getResourceAsStream(
                    "/org/pushingpixels/aurora/skins/business.colorschemes"
                )
            )

            val activeScheme = businessSchemes["Business Black Steel Active"]
            val disabledScheme = businessSchemes["Business Black Steel Disabled"]

            // color scheme bundle for title panes
            val activeHeaderScheme = businessSchemes["Business Black Steel Active Header"]
            val enabledHeaderScheme = businessSchemes["Business Black Steel Enabled Header"]
            val headerSchemeBundle = AuroraColorSchemeBundle(
                activeHeaderScheme, enabledHeaderScheme, disabledScheme
            )
            headerSchemeBundle.registerAlpha(
                0.5f,
                ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
            )
            headerSchemeBundle.registerColorScheme(
                enabledHeaderScheme,
                ColorSchemeAssociationKind.FILL,
                ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
            )
            headerSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.ROLLOVER_UNSELECTED)
            headerSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.SELECTED)
            headerSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.ROLLOVER_SELECTED)
            headerSchemeBundle.registerHighlightColorScheme(
                activeScheme, ComponentState.ROLLOVER_UNSELECTED,
                ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
            )

            it.registerDecorationAreaSchemeBundle(
                headerSchemeBundle, activeHeaderScheme,
                DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
            )

            // color scheme bundle for control pane areas
            val activeControlPaneScheme =
                businessSchemes["Business Black Steel Active Control Pane"]
            val enabledControlPaneScheme =
                businessSchemes["Business Black Steel Enabled Control Pane"]
            val controlPaneSchemeBundle = AuroraColorSchemeBundle(
                activeControlPaneScheme, enabledControlPaneScheme, disabledScheme
            )
            controlPaneSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_UNSELECTED)
            controlPaneSchemeBundle.registerColorScheme(
                disabledScheme,
                ColorSchemeAssociationKind.FILL,
                ComponentState.DISABLED_UNSELECTED
            )
            it.registerDecorationAreaSchemeBundle(
                controlPaneSchemeBundle, DecorationAreaType.FOOTER,
                DecorationAreaType.CONTROL_PANE
            )
        },
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun businessBlueSteelSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Business Blue Steel",
        colors = businessBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/business.colorschemes")
                .withWindowChromeAccent("Business Blue Steel Active Header")
                .withActiveControlsAccent("Business Blue Steel Active")
                .withHighlightsAccent("Business Blue Steel Highlight")
        ).also {
            val businessSchemes = getColorSchemes(
                AuroraSkin::class.java.getResourceAsStream(
                    "/org/pushingpixels/aurora/skins/business.colorschemes"
                )
            )

            val disabledScheme = businessSchemes["Business Blue Steel Disabled"]

            val activeHeaderScheme = businessSchemes["Business Blue Steel Active Header"]
            val enabledHeaderScheme = businessSchemes["Business Blue Steel Enabled Header"]
            val headerSchemeBundle = AuroraColorSchemeBundle(
                activeHeaderScheme, enabledHeaderScheme, enabledHeaderScheme
            )
            headerSchemeBundle.registerAlpha(0.5f,
                ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED)
            headerSchemeBundle.registerColorScheme(
                enabledHeaderScheme,
                ColorSchemeAssociationKind.FILL,
                ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
            )
            it.registerDecorationAreaSchemeBundle(
                headerSchemeBundle,
                DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
            )

            val activeControlPaneScheme =
                businessSchemes["Business Blue Steel Active Control Pane"]
            val enabledControlPaneScheme =
                businessSchemes["Business Blue Steel Enabled Control Pane"]
            val controlPaneSchemeBundle = AuroraColorSchemeBundle(
                activeControlPaneScheme, enabledControlPaneScheme, disabledScheme
            )
            controlPaneSchemeBundle.registerAlpha(0.7f, ComponentState.DISABLED_UNSELECTED)
            controlPaneSchemeBundle.registerColorScheme(
                enabledControlPaneScheme,
                ColorSchemeAssociationKind.FILL,
                ComponentState.DISABLED_UNSELECTED
            )
            it.registerDecorationAreaSchemeBundle(
                controlPaneSchemeBundle, DecorationAreaType.FOOTER,
                DecorationAreaType.CONTROL_PANE
            )

        },
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

