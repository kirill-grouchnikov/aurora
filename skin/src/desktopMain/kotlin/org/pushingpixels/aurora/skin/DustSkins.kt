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
import org.pushingpixels.aurora.colorscheme.composite
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun dustBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/dust.colorschemes"
        )
    )

    val activeControlsAccent = accentBuilder.activeControlsAccent!!
    val enabledControlsAccent = accentBuilder.enabledControlsAccent!!

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeControlsAccent, enabledControlsAccent, enabledControlsAccent
    )
    defaultSchemeBundle.registerAlpha(
        0.5f,
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        enabledControlsAccent,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        activeControlsAccent,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )

    // borders and marks
    val borderEnabledScheme = schemes["Dust Border Enabled"]
    val borderActiveScheme = schemes["Dust Border Active"]
    val markEnabledScheme = schemes["Dust Mark Enabled"]

    defaultSchemeBundle.registerColorScheme(
        borderEnabledScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.ENABLED,
        ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        borderActiveScheme,
        ColorSchemeAssociationKind.BORDER, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        markEnabledScheme,
        ColorSchemeAssociationKind.MARK
    )

    // text highlight
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.highlightsAccent!!,
        ColorSchemeAssociationKind.HIGHLIGHT_TEXT,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    // custom highlight alphas
    defaultSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.ROLLOVER_UNSELECTED)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.SELECTED)
    defaultSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.ROLLOVER_SELECTED)
    defaultSchemeBundle.registerHighlightColorScheme(
        accentBuilder.highlightsAccent!!,
        ComponentState.ROLLOVER_UNSELECTED, ComponentState.SELECTED,
        ComponentState.ROLLOVER_SELECTED
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, accentBuilder.backgroundAccent!!,
        DecorationAreaType.NONE
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
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )
    headerSchemeBundle.registerColorScheme(
        headerDisabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )

    headerSchemeBundle.registerColorScheme(
        headerBorderScheme,
        ColorSchemeAssociationKind.BORDER
    )
    headerSchemeBundle.registerColorScheme(
        headerSeparatorScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )

    headerSchemeBundle.registerHighlightAlpha(1.0f)
    headerSchemeBundle.registerHighlightColorScheme(headerActiveScheme)
    // the next line is to have consistent coloring during the rollover menu animations
    // the next line is to have consistent coloring during the rollover menu animations
    headerSchemeBundle.registerHighlightAlpha(0.0f, ComponentState.ENABLED)

    result.registerDecorationAreaSchemeBundle(headerSchemeBundle, DecorationAreaType.TOOLBAR)

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerBackgroundScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER, DecorationAreaType.FOOTER
    )

    return result
}

private fun dustBasePainters(): Painters {
    val painters = Painters(
        fillPainter = MatteFillPainter(),
        borderPainter = CompositeBorderPainter(
            displayName = "Dust",
            outer = ClassicBorderPainter(),
            inner = DelegateBorderPainter(
                displayName = "Dust Inner",
                delegate = ClassicBorderPainter(),
                topMask = 0x60FFFFFF,
                midMask = 0x40FFFFFF,
                bottomMask = 0x20FFFFFF,
                transform = {
                    it.shift(
                        backgroundShiftColor = it.ultraLightColor, backgroundShiftFactor = 0.8f,
                        foregroundShiftColor = it.foregroundColor, foregroundShiftFactor = 0.0f
                    ).tint(0.6f)
                        .saturate(0.2f)
                }
            )),
        decorationPainter = MatteDecorationPainter()
    )

    // add two overlay painters to create a bezel line between menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite(
                { it.ultraDarkColor },
                ColorTransforms.brightness(-0.5f)
            )
        ), DecorationAreaType.HEADER
    )
    painters.addOverlayPainter(TopLineOverlayPainter(
        composite(
            { it.foregroundColor },
            ColorTransforms.alpha(0.125f)
        )
    ), DecorationAreaType.TOOLBAR)

    return painters
}

fun dustSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Dust",
        colors = dustBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/dust.colorschemes")
                .withActiveControlsAccent("Dust Active")
                .withEnabledControlsAccent("Dust Enabled")
                .withBackgroundAccent("Dust Enabled")
                .withHighlightsAccent("Dust Highlight")
        ),
        painters = dustBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun dustCoffeeSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Dust Coffee",
        colors = dustBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/dust.colorschemes")
                .withActiveControlsAccent("Dust Coffee Active")
                .withEnabledControlsAccent("Dust Coffee Enabled")
                .withBackgroundAccent("Dust Coffee Background")
                .withHighlightsAccent("Dust Coffee Text Highlight")
        ),
        painters = dustBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

