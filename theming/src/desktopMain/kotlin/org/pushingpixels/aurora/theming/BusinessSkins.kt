/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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

import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.BrushedMetalDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun businessBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val businessSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/business.colorschemes"
        )
    )

    val enabledScheme = businessSchemes["Business Enabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        accentBuilder.activeControlsAccent!!, enabledScheme, enabledScheme
    )

    defaultSchemeBundle.registerHighlightColorScheme(accentBuilder.highlightsAccent!!)

    defaultSchemeBundle.registerAlpha(
        0.5f,
        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        enabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected, ComponentState.Selected
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Tab, ComponentState.Selected,
        ComponentState.RolloverSelected
    )

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.None)

    result.registerAsDecorationArea(
        accentBuilder.windowChromeAccent!!,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header, DecorationAreaType.Footer
    )

    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/kitchen-sink.colorschemes"
        )
    )

    result.registerAsDecorationArea(
        kitchenSinkSchemes["LightGray Control Pane Background"],
        DecorationAreaType.ControlPane
    )

    return result
}

private fun businessBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
        fillPainter = SpecularRectangularFillPainter(ClassicFillPainter(), 0.5f),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = BrushedMetalDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
    )

    // add an overlay painter to paint a drop shadow along the top edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(80), DecorationAreaType.Toolbar)

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.midColor }),
        DecorationAreaType.Header
    )

    return painters
}

fun businessSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Business",
        colors = businessBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/business.colorschemes")
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
                .withAccentResource("/org/pushingpixels/aurora/theming/business.colorschemes")
                .withWindowChromeAccent("Business Black Steel Active Header")
                .withActiveControlsAccent("Business Black Steel Active")
                .withHighlightsAccent("Business Black Steel Active")
        ).also {
            val businessSchemes = getColorSchemes(
                AuroraSkin::class.java.getResourceAsStream(
                    "/org/pushingpixels/aurora/theming/business.colorschemes"
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
                ComponentState.DisabledUnselected, ComponentState.DisabledSelected
            )
            headerSchemeBundle.registerColorScheme(
                enabledHeaderScheme,
                ColorSchemeAssociationKind.Fill,
                ComponentState.DisabledUnselected, ComponentState.DisabledSelected
            )
            headerSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.RolloverUnselected)
            headerSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.Selected)
            headerSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.RolloverSelected)
            headerSchemeBundle.registerHighlightColorScheme(
                activeScheme, ComponentState.RolloverUnselected,
                ComponentState.Selected, ComponentState.RolloverSelected
            )

            it.registerDecorationAreaSchemeBundle(
                headerSchemeBundle, activeHeaderScheme,
                DecorationAreaType.TitlePane, DecorationAreaType.Header
            )

            // color scheme bundle for control pane areas
            val activeControlPaneScheme =
                businessSchemes["Business Black Steel Active Control Pane"]
            val enabledControlPaneScheme =
                businessSchemes["Business Black Steel Enabled Control Pane"]
            val controlPaneSchemeBundle = AuroraColorSchemeBundle(
                activeControlPaneScheme, enabledControlPaneScheme, disabledScheme
            )
            controlPaneSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledUnselected)
            controlPaneSchemeBundle.registerColorScheme(
                disabledScheme,
                ColorSchemeAssociationKind.Fill,
                ComponentState.DisabledUnselected
            )
            it.registerDecorationAreaSchemeBundle(
                controlPaneSchemeBundle, DecorationAreaType.Footer,
                DecorationAreaType.ControlPane
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
                .withAccentResource("/org/pushingpixels/aurora/theming/business.colorschemes")
                .withWindowChromeAccent("Business Blue Steel Active Header")
                .withActiveControlsAccent("Business Blue Steel Active")
                .withHighlightsAccent("Business Blue Steel Highlight")
        ).also {
            val businessSchemes = getColorSchemes(
                AuroraSkin::class.java.getResourceAsStream(
                    "/org/pushingpixels/aurora/theming/business.colorschemes"
                )
            )

            val disabledScheme = businessSchemes["Business Blue Steel Disabled"]

            val activeHeaderScheme = businessSchemes["Business Blue Steel Active Header"]
            val enabledHeaderScheme = businessSchemes["Business Blue Steel Enabled Header"]
            val headerSchemeBundle = AuroraColorSchemeBundle(
                activeHeaderScheme, enabledHeaderScheme, enabledHeaderScheme
            )
            headerSchemeBundle.registerAlpha(0.5f,
                ComponentState.DisabledUnselected, ComponentState.DisabledSelected)
            headerSchemeBundle.registerColorScheme(
                enabledHeaderScheme,
                ColorSchemeAssociationKind.Fill,
                ComponentState.DisabledUnselected, ComponentState.DisabledSelected
            )
            it.registerDecorationAreaSchemeBundle(
                headerSchemeBundle,
                DecorationAreaType.TitlePane, DecorationAreaType.Header
            )

            val activeControlPaneScheme =
                businessSchemes["Business Blue Steel Active Control Pane"]
            val enabledControlPaneScheme =
                businessSchemes["Business Blue Steel Enabled Control Pane"]
            val controlPaneSchemeBundle = AuroraColorSchemeBundle(
                activeControlPaneScheme, enabledControlPaneScheme, disabledScheme
            )
            controlPaneSchemeBundle.registerAlpha(0.7f, ComponentState.DisabledUnselected)
            controlPaneSchemeBundle.registerColorScheme(
                enabledControlPaneScheme,
                ColorSchemeAssociationKind.Fill,
                ComponentState.DisabledUnselected
            )
            it.registerDecorationAreaSchemeBundle(
                controlPaneSchemeBundle, DecorationAreaType.Footer,
                DecorationAreaType.ControlPane
            )

        },
        painters = businessBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

