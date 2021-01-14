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
import org.pushingpixels.aurora.colorscheme.AquaColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.shaper.PillButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun mistBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/mist.colorschemes"
        )
    )

    val enabledScheme = schemes["Mist Enabled"]
    val disabledScheme = schemes["Mist Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        accentBuilder.activeControlsAccent!!, enabledScheme, disabledScheme
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.highlightsAccent!!,
        ColorSchemeAssociationKind.HIGHLIGHT_TEXT,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.highlightsAccent!!,
        ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        enabledScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.SELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.TAB,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.NONE
    )

    val controlPaneBackgroundScheme = schemes["Mist Control Pane Background"]
    result.registerDecorationAreaSchemeBundle(
        AuroraColorSchemeBundle(
            accentBuilder.activeControlsAccent!!, enabledScheme, disabledScheme
        ),
        controlPaneBackgroundScheme,
        DecorationAreaType.CONTROL_PANE
    )

    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER, DecorationAreaType.FOOTER,
        DecorationAreaType.TOOLBAR
    )

    return result
}

private fun mistBasePainters(): Painters {
    return Painters(
        fillPainter = MatteFillPainter(),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = MatteDecorationPainter()
    )
}

fun mistSilverSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Mist Silver",
        colors = mistBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/mist.colorschemes")
                .withActiveControlsAccent("Mist Silver Light Blue")
                .withHighlightsAccent("Mist Silver Light Blue")
        ),
        painters = mistBasePainters(),
        buttonShaper = PillButtonShaper()
    )
}

fun mistAquaSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Mist Aqua",
        colors = mistBaseSkinColors(
            AccentBuilder()
                .withActiveControlsAccent(AquaColorScheme())
                .withHighlightsAccent(AquaColorScheme())
        ),
        painters = mistBasePainters(),
        buttonShaper = PillButtonShaper()
    )
}

