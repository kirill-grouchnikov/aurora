/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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

