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
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.colorscheme.composite
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateFractionBasedBorderPainter
import org.pushingpixels.aurora.painter.border.FractionBasedBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


/**
 * Applies the specified highlight schemes on the relevant parts of the
 * specified scheme bundle.
 *
 * @param schemeBundle    Scheme bundle.
 * @param highlightScheme Highlight scheme.
 */
private fun applyHighlightColorScheme(
    schemeBundle: AuroraColorSchemeBundle,
    highlightScheme: AuroraColorScheme
) {
    // specify custom alpha values for the highlights
    schemeBundle.registerHighlightAlpha(0.85f, ComponentState.ROLLOVER_UNSELECTED)
    schemeBundle.registerHighlightAlpha(0.9f, ComponentState.SELECTED)
    schemeBundle.registerHighlightColorScheme(
        highlightScheme, ComponentState.ROLLOVER_UNSELECTED,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )
}

private fun applyHighlightAsFill(
    schemeBundle: AuroraColorSchemeBundle,
    highlightScheme: AuroraColorScheme, highlightBorderScheme: AuroraColorScheme
) {
    // use for borders on rollover controls
    schemeBundle.registerColorScheme(
        highlightBorderScheme, ColorSchemeAssociationKind.BORDER,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED
    )

    // use for fill of selected controls
    schemeBundle.registerColorScheme(
        highlightScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    // use for borders of highlights
    schemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.HIGHLIGHT_BORDER, *ComponentState.activeStates
    )

    // use for text highlight
    schemeBundle.registerColorScheme(
        highlightScheme, ColorSchemeAssociationKind.HIGHLIGHT_TEXT,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )
}

private fun geminiSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/gemini.colorschemes"
        )
    )

    val grayScheme = schemes["Gemini Gray"]
    val disabledScheme = schemes["Gemini Disabled"]

    // use the same color scheme for active and enabled controls
    val defaultSchemeBundle = AuroraColorSchemeBundle(
        grayScheme,
        grayScheme, disabledScheme
    )

    // highlight fill scheme + custom alpha for rollover unselected state
    val highlightScheme = schemes["Gemini Highlight"]
    val highlightBorderScheme = schemes["Gemini Highlight Border"]
    applyHighlightColorScheme(defaultSchemeBundle, highlightScheme)
    applyHighlightAsFill(defaultSchemeBundle, highlightScheme, highlightBorderScheme)

    // borders, separators, marks
    val grayBorderScheme = schemes["Gemini Gray Border"]
    val lightGrayBorderScheme = schemes["Gemini Light Gray Border"]
    val darkGraySeparatorScheme = schemes["Gemini Dark Gray Separator"]
    defaultSchemeBundle.registerColorScheme(
        grayBorderScheme,
        ColorSchemeAssociationKind.BORDER
    )
    defaultSchemeBundle.registerColorScheme(
        lightGrayBorderScheme,
        ColorSchemeAssociationKind.BORDER,
        ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        grayScheme,
        ColorSchemeAssociationKind.FILL, ComponentState.ROLLOVER_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        darkGraySeparatorScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )
    defaultSchemeBundle.registerColorScheme(grayScheme, ColorSchemeAssociationKind.MARK)

    defaultSchemeBundle.registerAlpha(
        0.6f, ComponentState.DISABLED_UNSELECTED,
        ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        highlightScheme.tone(0.2f), ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )

    val whiteBackgroundScheme = schemes["Gemini White Background"]

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, whiteBackgroundScheme,
        DecorationAreaType.NONE
    )

    // control pane color scheme bundle
    val controlPaneSchemeBundle = AuroraColorSchemeBundle(
        grayScheme,
        grayScheme, disabledScheme
    )
    controlPaneSchemeBundle.registerColorScheme(
        grayScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )
    controlPaneSchemeBundle.registerColorScheme(grayScheme, ColorSchemeAssociationKind.MARK)
    controlPaneSchemeBundle.registerColorScheme(grayBorderScheme, ColorSchemeAssociationKind.BORDER)
    controlPaneSchemeBundle.registerAlpha(
        0.6f, ComponentState.DISABLED_UNSELECTED,
        ComponentState.DISABLED_SELECTED
    )
    applyHighlightColorScheme(controlPaneSchemeBundle, highlightScheme)
    applyHighlightAsFill(controlPaneSchemeBundle, highlightScheme, highlightBorderScheme)
    result.registerDecorationAreaSchemeBundle(
        controlPaneSchemeBundle, grayScheme,
        DecorationAreaType.CONTROL_PANE, DecorationAreaType.FOOTER
    )

    // header color scheme bundle
    val blackColorScheme = schemes["Gemini Black"]
    val activeHeaderScheme = schemes["Gemini Black Active Header"]
    val disabledHeaderScheme = schemes["Gemini Black Disabled Header"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeHeaderScheme, blackColorScheme, blackColorScheme
    )
    headerSchemeBundle.registerAlpha(
        0.5f, ComponentState.DISABLED_UNSELECTED,
        ComponentState.DISABLED_SELECTED
    )
    headerSchemeBundle.registerColorScheme(
        disabledHeaderScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED
    )
    headerSchemeBundle.registerColorScheme(
        blackColorScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )
    headerSchemeBundle.registerColorScheme(blackColorScheme, ColorSchemeAssociationKind.MARK)
    // TODO - this is different from the original
    headerSchemeBundle.registerColorScheme(
        grayScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )
    headerSchemeBundle.registerColorScheme(
        blackColorScheme.shade(0.9f),
        ColorSchemeAssociationKind.BORDER
    )
    applyHighlightColorScheme(headerSchemeBundle, highlightScheme)
    // TODO - this is different from the original
    applyHighlightAsFill(headerSchemeBundle, highlightScheme, highlightBorderScheme)

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, blackColorScheme,
        DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
    )

    // toolbar color scheme bundle
    val darkBlueColorScheme = schemes["Gemini Dark Blue"]
    val darkBlueBackgroundColorScheme = schemes["Gemini Dark Blue Background"]
    val darkBlueSeparatorColorScheme = schemes["Gemini Dark Blue Separator"]
    val toolbarSchemeBundle = AuroraColorSchemeBundle(
        blackColorScheme, darkBlueColorScheme, darkBlueColorScheme
    )
    toolbarSchemeBundle.registerAlpha(
        0.5f, ComponentState.DISABLED_UNSELECTED,
        ComponentState.DISABLED_SELECTED
    )
    toolbarSchemeBundle.registerColorScheme(
        blackColorScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    toolbarSchemeBundle.registerColorScheme(
        darkBlueColorScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    toolbarSchemeBundle.registerColorScheme(
        blackColorScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )
    toolbarSchemeBundle.registerColorScheme(darkBlueColorScheme, ColorSchemeAssociationKind.MARK)
    toolbarSchemeBundle.registerColorScheme(darkBlueColorScheme, ColorSchemeAssociationKind.BORDER)
    toolbarSchemeBundle.registerColorScheme(
        darkBlueSeparatorColorScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )
    toolbarSchemeBundle.registerColorScheme(
        highlightScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED,
        ComponentState.PRESSED_SELECTED
    )
    applyHighlightColorScheme(toolbarSchemeBundle, highlightScheme)
    applyHighlightAsFill(toolbarSchemeBundle, highlightScheme, darkBlueColorScheme)
    result.registerDecorationAreaSchemeBundle(
        toolbarSchemeBundle, darkBlueBackgroundColorScheme,
        DecorationAreaType.TOOLBAR
    )

    return result
}

fun geminiSkin(): AuroraSkinDefinition {
    val outerBorderPainter = FractionBasedBorderPainter(
        0.0f to { it.ultraDarkColor },
        0.5f to { it.ultraDarkColor },
        1.0f to { it.ultraDarkColor },
        displayName = "Gemini Outer"
    )

    val painters = Painters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.extraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.midColor },
            displayName = "Gemini"
        ),
        borderPainter = CompositeBorderPainter(
            displayName = "Gemini",
            outer = outerBorderPainter,
            inner = DelegateFractionBasedBorderPainter(
                displayName = "Gemini Inner",
                delegate = outerBorderPainter,
                masks = intArrayOf(0x60FFFFFF, 0x40FFFFFF, 0x20FFFFFF),
                transform = { it.tint(0.7f) }
            )),
        decorationPainter = MatteDecorationPainter()
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorSchemeQueryTop = { it.darkColor },
            colorSchemeQueryBottom = { it.ultraLightColor }
        ),
        DecorationAreaType.FOOTER
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.5f))
        ),
        DecorationAreaType.HEADER
    )
    painters.addOverlayPainter(
        TopLineOverlayPainter(
            composite({ it.foregroundColor }, ColorTransforms.alpha(0.125f))
        ),
        DecorationAreaType.TOOLBAR
    )

    // add overlay painter to paint drop shadows along the bottom
    // edges of toolbars
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.TOOLBAR
    )

    // add overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.ultraDarkColor }),
        DecorationAreaType.TOOLBAR
    )

    return AuroraSkinDefinition(
        displayName = "Gemini",
        colors = geminiSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
