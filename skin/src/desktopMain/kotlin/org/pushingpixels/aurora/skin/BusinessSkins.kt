/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.skin

import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
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

private fun businessBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
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

