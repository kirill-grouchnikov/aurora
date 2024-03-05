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
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateFractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

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
        decorationPainter = MarbleNoiseDecorationPainter(textureAlpha = 0.7f)
    )
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(50), DecorationAreaType.Toolbar)
    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.darkColor }),
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
    )

    return AuroraSkinDefinition(
        displayName = "Autumn",
        colors = autumnSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

