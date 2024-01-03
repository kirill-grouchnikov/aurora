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

import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colorscheme.composite
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateFractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.border.FractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

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
    schemeBundle.registerHighlightAlpha(0.85f, ComponentState.RolloverUnselected)
    schemeBundle.registerHighlightAlpha(0.9f, ComponentState.Selected)
    schemeBundle.registerHighlightColorScheme(
        highlightScheme, ComponentState.RolloverUnselected,
        ComponentState.Selected, ComponentState.RolloverSelected
    )
}

private fun applyHighlightAsFill(
    schemeBundle: AuroraColorSchemeBundle,
    highlightScheme: AuroraColorScheme, highlightBorderScheme: AuroraColorScheme
) {
    // use for borders on rollover controls
    schemeBundle.registerColorScheme(
        highlightBorderScheme, ColorSchemeAssociationKind.Border,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )

    // use for fill of selected controls
    schemeBundle.registerColorScheme(
        highlightScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    // use for borders of highlights
    schemeBundle.registerColorScheme(
        highlightScheme,
        ColorSchemeAssociationKind.HighlightBorder, *ComponentState.activeStates
    )

    // use for text highlight
    schemeBundle.registerColorScheme(
        highlightScheme, ColorSchemeAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected
    )
}

private fun geminiSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/gemini.colorschemes"
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
        ColorSchemeAssociationKind.Border
    )
    defaultSchemeBundle.registerColorScheme(
        lightGrayBorderScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.DisabledSelected, ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        grayScheme,
        ColorSchemeAssociationKind.Fill, ComponentState.RolloverUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        darkGraySeparatorScheme,
        ColorSchemeAssociationKind.Separator
    )
    defaultSchemeBundle.registerColorScheme(grayScheme, ColorSchemeAssociationKind.Mark)

    defaultSchemeBundle.registerAlpha(
        0.6f, ComponentState.DisabledUnselected,
        ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        highlightScheme.tone(0.2f), ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    val whiteBackgroundScheme = schemes["Gemini White Background"]

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, whiteBackgroundScheme,
        DecorationAreaType.None
    )

    // control pane color scheme bundle
    val controlPaneSchemeBundle = AuroraColorSchemeBundle(
        grayScheme,
        grayScheme, disabledScheme
    )
    controlPaneSchemeBundle.registerColorScheme(
        grayScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )
    controlPaneSchemeBundle.registerColorScheme(grayScheme, ColorSchemeAssociationKind.Mark)
    controlPaneSchemeBundle.registerColorScheme(grayBorderScheme, ColorSchemeAssociationKind.Border)
    controlPaneSchemeBundle.registerAlpha(
        0.6f, ComponentState.DisabledUnselected,
        ComponentState.DisabledSelected
    )
    applyHighlightColorScheme(controlPaneSchemeBundle, highlightScheme)
    applyHighlightAsFill(controlPaneSchemeBundle, highlightScheme, highlightBorderScheme)
    result.registerDecorationAreaSchemeBundle(
        controlPaneSchemeBundle, grayScheme,
        DecorationAreaType.ControlPane, DecorationAreaType.Footer
    )

    // header color scheme bundle
    val blackColorScheme = schemes["Gemini Black"]
    val activeHeaderScheme = schemes["Gemini Black Active Header"]
    val disabledHeaderScheme = schemes["Gemini Black Disabled Header"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeHeaderScheme, blackColorScheme, blackColorScheme
    )
    headerSchemeBundle.registerAlpha(
        0.5f, ComponentState.DisabledUnselected,
        ComponentState.DisabledSelected
    )
    headerSchemeBundle.registerColorScheme(
        disabledHeaderScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected, ComponentState.DisabledUnselected
    )
    headerSchemeBundle.registerColorScheme(
        blackColorScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )
    headerSchemeBundle.registerColorScheme(blackColorScheme, ColorSchemeAssociationKind.Mark)
    headerSchemeBundle.registerColorScheme(
        grayScheme, ColorSchemeAssociationKind.Mark,
        ComponentState.Selected, ComponentState.RolloverSelected
    )
    headerSchemeBundle.registerColorScheme(
        blackColorScheme.shade(0.9f),
        ColorSchemeAssociationKind.Border
    )
    applyHighlightColorScheme(headerSchemeBundle, highlightScheme)
    applyHighlightAsFill(headerSchemeBundle, highlightScheme, highlightBorderScheme)

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, blackColorScheme,
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    // toolbar color scheme bundle
    val darkBlueColorScheme = schemes["Gemini Dark Blue"]
    val darkBlueBackgroundColorScheme = schemes["Gemini Dark Blue Background"]
    val darkBlueSeparatorColorScheme = schemes["Gemini Dark Blue Separator"]
    val toolbarSchemeBundle = AuroraColorSchemeBundle(
        blackColorScheme, darkBlueColorScheme, darkBlueColorScheme
    )
    toolbarSchemeBundle.registerAlpha(
        0.5f, ComponentState.DisabledUnselected,
        ComponentState.DisabledSelected
    )
    toolbarSchemeBundle.registerColorScheme(
        blackColorScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    toolbarSchemeBundle.registerColorScheme(
        darkBlueColorScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    toolbarSchemeBundle.registerColorScheme(
        blackColorScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )
    toolbarSchemeBundle.registerColorScheme(darkBlueColorScheme, ColorSchemeAssociationKind.Mark)
    toolbarSchemeBundle.registerColorScheme(darkBlueColorScheme, ColorSchemeAssociationKind.Border)
    toolbarSchemeBundle.registerColorScheme(
        darkBlueSeparatorColorScheme,
        ColorSchemeAssociationKind.Separator
    )
    toolbarSchemeBundle.registerColorScheme(
        highlightScheme, ColorSchemeAssociationKind.Mark,
        ComponentState.Selected, ComponentState.RolloverSelected,
        ComponentState.PressedSelected
    )
    applyHighlightColorScheme(toolbarSchemeBundle, highlightScheme)
    applyHighlightAsFill(toolbarSchemeBundle, highlightScheme, darkBlueColorScheme)
    result.registerDecorationAreaSchemeBundle(
        toolbarSchemeBundle, darkBlueBackgroundColorScheme,
        DecorationAreaType.Toolbar
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

    val painters = AuroraPainters(
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
                masks = longArrayOf(0x60FFFFFFL, 0x40FFFFFFL, 0x20FFFFFFL),
                transform = { it.tint(0.7f) }
            )),
        decorationPainter = MatteDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorSchemeQueryTop = { it.darkColor },
            colorSchemeQueryBottom = { it.ultraLightColor }
        ),
        DecorationAreaType.Footer
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.5f))
        ),
        DecorationAreaType.Header
    )
    painters.addOverlayPainter(
        TopLineOverlayPainter(
            composite({ it.foregroundColor }, ColorTransforms.alpha(0.125f))
        ),
        DecorationAreaType.Toolbar
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
        BottomLineOverlayPainter(colorSchemeQuery = { it.ultraDarkColor }),
        DecorationAreaType.Toolbar
    )

    return AuroraSkinDefinition(
        displayName = "Gemini",
        colors = geminiSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
