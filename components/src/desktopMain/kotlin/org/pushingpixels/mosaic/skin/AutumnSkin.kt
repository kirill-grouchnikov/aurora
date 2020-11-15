/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.skin

import org.pushingpixels.mosaic.*
import org.pushingpixels.mosaic.colorscheme.MosaicColorSchemeBundle
import org.pushingpixels.mosaic.colorscheme.MosaicSkinColors
import org.pushingpixels.mosaic.painter.border.ClassicBorderPainter
import org.pushingpixels.mosaic.painter.border.CompositeBorderPainter
import org.pushingpixels.mosaic.painter.border.DelegateBorderPainter
import org.pushingpixels.mosaic.painter.fill.MatteFillPainter
import org.pushingpixels.mosaic.shaper.ClassicButtonShaper
import org.pushingpixels.mosaic.utils.getColorSchemes

private fun autumnSkinColors(): MosaicSkinColors {
    val result = MosaicSkinColors()
    val schemes = getColorSchemes(
        MosaicSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/mosaic/skins/autumn.colorschemes"
        )
    )

    val activeScheme = schemes["Autumn Active"]
    val enabledScheme = schemes["Autumn Enabled"]
    val disabledScheme = enabledScheme

    val defaultSchemeBundle = MosaicColorSchemeBundle(
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

    val titlePaneSchemeBundle = MosaicColorSchemeBundle(
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
        titlePaneSchemeBundle,
        activeScheme, DecorationAreaType.PRIMARY_TITLE_PANE,
        DecorationAreaType.SECONDARY_TITLE_PANE
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

fun autumnSkin(): MosaicSkinDefinition {
    return MosaicSkinDefinition(
        displayName = "Autumn",
        colors = autumnSkinColors(),
        painters = Painters(
            fillPainter = MatteFillPainter(),
            borderPainter = CompositeBorderPainter("Autumn",
                DelegateBorderPainter(
                    "Autumn Outer", ClassicBorderPainter()
                ) { it.shade(0.1f) },
                DelegateBorderPainter(
                    "Autumn Inner", ClassicBorderPainter()
                ) { it.tint(0.8f) })
        ),
        buttonShaper = ClassicButtonShaper()
    )
}

