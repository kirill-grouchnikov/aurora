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

import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.AuroraSkinDefinition
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.Painters
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.colorscheme.DesertSandColorScheme
import org.pushingpixels.aurora.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun saharaSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes"
        )
    )
    val activeScheme = DesertSandColorScheme()
    val enabledScheme = MetallicColorScheme()
    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, kitchenSinkSchemes["Gray Disabled"]
    )
    defaultSchemeBundle.registerHighlightColorScheme(kitchenSinkSchemes["Sahara Highlight"])
    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.NONE
    )

    result.registerAsDecorationArea(
        activeScheme,
        DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
    )

    return result
}

fun saharaSkin(): AuroraSkinDefinition {
    val painters = Painters(
        fillPainter = ClassicFillPainter(),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = MatteDecorationPainter()
    )
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.TOOLBAR)
    // add an overlay painter to paint separator lines along the bottom
    // edges of menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.midColor }),
        DecorationAreaType.HEADER
    )

    return AuroraSkinDefinition(
        displayName = "Sahara",
        colors = saharaSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

