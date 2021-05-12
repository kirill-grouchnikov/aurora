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
import org.pushingpixels.aurora.colorscheme.DarkMetallicColorScheme
import org.pushingpixels.aurora.colorscheme.EbonyColorScheme
import org.pushingpixels.aurora.painter.border.GlassBorderPainter
import org.pushingpixels.aurora.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.painter.fill.GlassFillPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

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
    defaultSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.ROLLOVER_UNSELECTED)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.SELECTED)
    defaultSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.ROLLOVER_SELECTED)
    defaultSchemeBundle.registerHighlightColorScheme(
        highlightScheme, ComponentState.ROLLOVER_UNSELECTED,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    // highlight border scheme
    defaultSchemeBundle.registerColorScheme(
        EbonyColorScheme(),
        ColorSchemeAssociationKind.HIGHLIGHT_BORDER, *ComponentState.activeStates
    )

    // text highlight scheme
    val textHighlightScheme = schemes["Graphite Text Highlight"]
    defaultSchemeBundle.registerColorScheme(
        textHighlightScheme,
        ColorSchemeAssociationKind.HIGHLIGHT_TEXT,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )

    val highlightMarkScheme = schemes["Raven Highlight Mark"]
    defaultSchemeBundle.registerColorScheme(
        highlightMarkScheme,
        ColorSchemeAssociationKind.HIGHLIGHT_MARK, *ComponentState.activeStates
    )

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )

    val tabHighlightScheme = schemes["Graphite Tab Highlight"]
    defaultSchemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        tabHighlightScheme,
        ColorSchemeAssociationKind.TAB, ComponentState.SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.SELECTED,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED
    )

    val selectedMarkScheme = schemes["Raven Selected Mark"]
    defaultSchemeBundle.registerColorScheme(
        selectedMarkScheme,
        ColorSchemeAssociationKind.MARK, ComponentState.SELECTED,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        selectedMarkScheme,
        ColorSchemeAssociationKind.MARK,
        ComponentState.ROLLOVER_UNSELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.BORDER,
        ComponentState.DISABLED_SELECTED
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        schemes["Graphite Background"].shade(0.4f), DecorationAreaType.NONE
    )

    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER, DecorationAreaType.FOOTER,
        DecorationAreaType.CONTROL_PANE, DecorationAreaType.TOOLBAR
    )

    return result
}

fun ravenSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Raven",
        colors = ravenSkinColors(),
        painters = Painters(
            fillPainter = GlassFillPainter(),
            borderPainter = GlassBorderPainter(),
            decorationPainter = ArcDecorationPainter()
        ),
        buttonShaper = ClassicButtonShaper()
    )
}

