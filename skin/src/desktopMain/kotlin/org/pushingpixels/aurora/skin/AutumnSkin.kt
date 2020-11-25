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
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun autumnSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/autumn.colorschemes"
        )
    )

    val activeScheme = schemes["Autumn Active"]
    val enabledScheme = schemes["Autumn Enabled"]
    val disabledScheme = enabledScheme

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    defaultSchemeBundle.registerAlpha(0.6f, ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.NONE
    )

    val titlePaneSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    titlePaneSchemeBundle.registerAlpha(0.6f, ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED)
    titlePaneSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    titlePaneSchemeBundle.registerColorScheme(
        activeScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )

    val borderScheme = enabledScheme.saturate(0.2f)
    titlePaneSchemeBundle.registerColorScheme(
        borderScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.ENABLED
    )

    result.registerDecorationAreaSchemeBundle(
        bundle = titlePaneSchemeBundle,
        backgroundColorScheme = activeScheme,
        DecorationAreaType.PRIMARY_TITLE_PANE, DecorationAreaType.SECONDARY_TITLE_PANE
    )

    val backgroundScheme = schemes["Autumn Background"]

    result.registerAsDecorationArea(
        activeScheme,
        DecorationAreaType.PRIMARY_TITLE_PANE,
        DecorationAreaType.SECONDARY_TITLE_PANE,
        DecorationAreaType.HEADER
    )

    result.registerAsDecorationArea(
        backgroundScheme,
        DecorationAreaType.CONTROL_PANE, DecorationAreaType.FOOTER,
        DecorationAreaType.TOOLBAR
    )

    return result
}

fun autumnSkin(): AuroraSkinDefinition {
    val painters = Painters(
        fillPainter = MatteFillPainter(),
        borderPainter = CompositeBorderPainter("Autumn",
            DelegateBorderPainter(
                "Autumn Outer", ClassicBorderPainter()
            ) { it.shade(0.1f) },
            DelegateBorderPainter(
                "Autumn Inner", ClassicBorderPainter()
            ) { it.tint(0.8f) }),
        decorationPainter = MarbleNoiseDecorationPainter()
    )
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(50), DecorationAreaType.TOOLBAR)
    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.darkColor }),
        DecorationAreaType.PRIMARY_TITLE_PANE,
        DecorationAreaType.SECONDARY_TITLE_PANE,
        DecorationAreaType.HEADER
    )

    return AuroraSkinDefinition(
        displayName = "Autumn",
        colors = autumnSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

