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

