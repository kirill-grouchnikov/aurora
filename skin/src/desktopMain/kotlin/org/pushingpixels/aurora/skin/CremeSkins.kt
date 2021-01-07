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
import org.pushingpixels.aurora.colorscheme.CremeColorScheme
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun cremeBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes"
        )
    )

    val enabledScheme = CremeColorScheme()
    val disabledScheme = kitchenSinkSchemes["Creme Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        accentBuilder.activeControlsAccent!!, enabledScheme, disabledScheme
    )
    defaultSchemeBundle.registerHighlightColorScheme(accentBuilder.highlightsAccent!!)
    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.NONE
    )

    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER, DecorationAreaType.FOOTER,
        DecorationAreaType.CONTROL_PANE, DecorationAreaType.TOOLBAR
    )

    return result
}

private fun cremeBasePainters(): Painters {
    val painters = Painters(
        fillPainter = MatteFillPainter(),
        borderPainter = CompositeBorderPainter(
            displayName = "Creme",
            outer = ClassicBorderPainter(),
            inner = DelegateBorderPainter(
                displayName = "Creme Inner", delegate = ClassicBorderPainter()
            ) { it.tint(0.9f) }),
        decorationPainter = ArcDecorationPainter()
    )

    // Add overlay painters to paint drop shadows along the bottom edges of toolbars
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(40), DecorationAreaType.TOOLBAR)

    // add an overlay painter to paint a dark line along the bottom edge of toolbars
    painters.addOverlayPainter(BottomLineOverlayPainter(colorSchemeQuery = { it.midColor }),
        DecorationAreaType.TOOLBAR)

    return painters
}

fun cremeSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Creme",
        colors = cremeBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes")
                .withActiveControlsAccent("Creme Active")
                .withHighlightsAccent("Creme Highlights")
        ),
        painters = cremeBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun cremeCoffeeSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Creme Coffee",
        colors = cremeBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes")
                .withActiveControlsAccent("Coffee Active")
                .withHighlightsAccent("Coffee Highlights")
        ),
        painters = cremeBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}
