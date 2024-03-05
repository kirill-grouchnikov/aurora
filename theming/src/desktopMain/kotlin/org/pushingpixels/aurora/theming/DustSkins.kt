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
import org.pushingpixels.aurora.theming.colorscheme.composite
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateFractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun dustBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/dust.colorschemes"
        )
    )

    val activeControlsAccent = accentBuilder.activeControlsAccent!!
    val enabledControlsAccent = accentBuilder.enabledControlsAccent!!

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeControlsAccent, enabledControlsAccent, enabledControlsAccent
    )
    defaultSchemeBundle.registerAlpha(
        0.5f,
        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        enabledControlsAccent,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        activeControlsAccent,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    // borders and marks
    val borderEnabledScheme = schemes["Dust Border Enabled"]
    val borderActiveScheme = schemes["Dust Border Active"]
    val markEnabledScheme = schemes["Dust Mark Enabled"]

    defaultSchemeBundle.registerColorScheme(
        borderEnabledScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled,
        ComponentState.DisabledSelected, ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        borderActiveScheme,
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        markEnabledScheme,
        ColorSchemeAssociationKind.Mark
    )

    // text highlight
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.highlightsAccent!!,
        ColorSchemeAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    // custom highlight alphas
    defaultSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.RolloverUnselected)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.Selected)
    defaultSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.RolloverSelected)
    defaultSchemeBundle.registerHighlightColorScheme(
        accentBuilder.highlightsAccent!!,
        ComponentState.RolloverUnselected, ComponentState.Selected,
        ComponentState.RolloverSelected
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, accentBuilder.backgroundAccent!!,
        DecorationAreaType.None
    )

    // header color scheme bundle
    val headerActiveScheme = schemes["Dust Header Active"]
    val headerEnabledScheme = schemes["Dust Header Enabled"]
    val headerDisabledScheme = schemes["Dust Header Disabled"]

    val headerBackgroundScheme = schemes["Dust Header Background"]
    val headerSeparatorScheme = schemes["Dust Header Separator"]
    val headerBorderScheme = schemes["Dust Header Border"]

    val headerSchemeBundle = AuroraColorSchemeBundle(
        headerActiveScheme, headerEnabledScheme, headerDisabledScheme
    )
    headerSchemeBundle.registerAlpha(
        0.7f,
        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )
    headerSchemeBundle.registerColorScheme(
        headerDisabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )

    headerSchemeBundle.registerColorScheme(
        headerBorderScheme,
        ColorSchemeAssociationKind.Border
    )
    headerSchemeBundle.registerColorScheme(
        headerSeparatorScheme,
        ColorSchemeAssociationKind.Separator
    )

    headerSchemeBundle.registerHighlightAlpha(1.0f)
    headerSchemeBundle.registerHighlightColorScheme(headerActiveScheme)
    // the next line is to have consistent coloring during the rollover menu animations
    headerSchemeBundle.registerHighlightAlpha(0.0f, ComponentState.Enabled)

    result.registerDecorationAreaSchemeBundle(headerSchemeBundle, DecorationAreaType.Toolbar)

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerBackgroundScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header, DecorationAreaType.Footer
    )

    return result
}

private fun dustBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
        fillPainter = SpecularRectangularFillPainter(MatteFillPainter(), 0.8f),
        borderPainter = CompositeBorderPainter(
            displayName = "Dust",
            outer = ClassicBorderPainter(),
            inner = DelegateFractionBasedBorderPainter(
                displayName = "Dust Inner",
                delegate = ClassicBorderPainter(),
                masks = longArrayOf(0x60FFFFFF, 0x40FFFFFF, 0x20FFFFFF),
                transform = {
                    it.shift(
                        backgroundShiftColor = it.ultraLightColor, backgroundShiftFactor = 0.8f,
                        foregroundShiftColor = it.foregroundColor, foregroundShiftFactor = 0.0f
                    ).tint(0.6f)
                        .saturate(0.2f)
                }
            )),
        decorationPainter = MatteDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
    )

    // add two overlay painters to create a bezel line between menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite(
                { it.ultraDarkColor },
                ColorTransforms.brightness(-0.5f)
            )
        ), DecorationAreaType.Header
    )
    painters.addOverlayPainter(TopLineOverlayPainter(
        composite(
            { it.foregroundColor },
            ColorTransforms.alpha(0.125f)
        )
    ), DecorationAreaType.Toolbar)

    return painters
}

fun dustSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Dust",
        colors = dustBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/dust.colorschemes")
                .withActiveControlsAccent("Dust Active")
                .withEnabledControlsAccent("Dust Enabled")
                .withBackgroundAccent("Dust Enabled")
                .withHighlightsAccent("Dust Highlight")
        ),
        painters = dustBasePainters(),
        buttonShaper = ClassicButtonShaper(),
    )
}

fun dustCoffeeSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Dust Coffee",
        colors = dustBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/dust.colorschemes")
                .withActiveControlsAccent("Dust Coffee Active")
                .withEnabledControlsAccent("Dust Coffee Enabled")
                .withBackgroundAccent("Dust Coffee Background")
                .withHighlightsAccent("Dust Coffee Text Highlight")
        ),
        painters = dustBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

