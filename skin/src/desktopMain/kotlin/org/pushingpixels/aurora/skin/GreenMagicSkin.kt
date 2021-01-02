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
import org.pushingpixels.aurora.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun greenMagicSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/greenmagic.colorschemes"
        )
    )

    val activeScheme = schemes["Green Magic Active"]
    val enabledScheme = schemes["Green Magic Enabled"]
    val disabledScheme = schemes["Green Magic Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.NONE
    )

    // mark title panes and headers as decoration areas
    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER
    )

    val footerFillScheme = schemes["Green Magic Footer Fill"]
    result.registerAsDecorationArea(footerFillScheme, DecorationAreaType.FOOTER)

    return result
}

fun greenMagicSkin(): AuroraSkinDefinition {
    val painters = Painters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.ultraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.lightColor },
            displayName = "Green Magic"
        ),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = ArcDecorationPainter()
    )

    // Add overlay painters to paint drop shadow and a dark line along the bottom
    // edges of headers
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.HEADER);
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.darkColor }, ColorTransforms.alpha(0.5f))
        ),
        DecorationAreaType.HEADER
    )

    return AuroraSkinDefinition(
        displayName = "Green Magic",
        colors = greenMagicSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
