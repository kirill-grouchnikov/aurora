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
import org.pushingpixels.aurora.skin.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.skin.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.skin.colorscheme.DarkMetallicColorScheme
import org.pushingpixels.aurora.skin.colorscheme.EbonyColorScheme
import org.pushingpixels.aurora.skin.painter.border.GlassBorderPainter
import org.pushingpixels.aurora.skin.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.skin.painter.fill.GlassFillPainter
import org.pushingpixels.aurora.skin.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.skin.utils.getColorSchemes

private fun ravenSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/graphite.colorschemes"
        )
    )
    val activeScheme = EbonyColorScheme()
    val enabledScheme = DarkMetallicColorScheme()
    val disabledScheme = schemes["Raven Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    // highlight fill scheme + custom alpha for rollover unselected state
    val highlightScheme = schemes["Graphite Highlight"]
    defaultSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.RolloverUnselected)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.Selected)
    defaultSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.RolloverSelected)
    defaultSchemeBundle.registerHighlightColorScheme(
        highlightScheme, ComponentState.RolloverUnselected,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    // highlight border scheme
    defaultSchemeBundle.registerColorScheme(
        EbonyColorScheme(),
        ColorSchemeAssociationKind.HighlightBorder, *ComponentState.activeStates
    )

    // text highlight scheme
    val textHighlightScheme = schemes["Graphite Text Highlight"]
    defaultSchemeBundle.registerColorScheme(
        textHighlightScheme,
        ColorSchemeAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    defaultSchemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )

    val highlightMarkScheme = schemes["Raven Highlight Mark"]
    defaultSchemeBundle.registerColorScheme(
        highlightMarkScheme,
        ColorSchemeAssociationKind.HighlightMark, *ComponentState.activeStates
    )

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledUnselected, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    val tabHighlightScheme = schemes["Graphite Tab Highlight"]
    defaultSchemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.Selected
    )
    defaultSchemeBundle.registerColorScheme(
        tabHighlightScheme,
        ColorSchemeAssociationKind.Tab, ComponentState.Selected
    )
    defaultSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Selected,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )

    val selectedMarkScheme = schemes["Raven Selected Mark"]
    defaultSchemeBundle.registerColorScheme(
        selectedMarkScheme,
        ColorSchemeAssociationKind.Mark, ComponentState.Selected,
        ComponentState.RolloverSelected,
        ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        selectedMarkScheme,
        ColorSchemeAssociationKind.Mark,
        ComponentState.RolloverUnselected
    )

    defaultSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.DisabledSelected
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        schemes["Graphite Background"].shade(0.4f), DecorationAreaType.None
    )

    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header, DecorationAreaType.Footer,
        DecorationAreaType.ControlPane, DecorationAreaType.Toolbar
    )

    return result
}

fun ravenSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Raven",
        colors = ravenSkinColors(),
        painters = AuroraPainters(
            fillPainter = GlassFillPainter(),
            borderPainter = GlassBorderPainter(),
            decorationPainter = ArcDecorationPainter()
        ),
        buttonShaper = ClassicButtonShaper()
    )
}

