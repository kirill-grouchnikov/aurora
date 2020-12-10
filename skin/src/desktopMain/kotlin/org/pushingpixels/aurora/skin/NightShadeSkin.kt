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
import org.pushingpixels.aurora.colorscheme.composite
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun nightShadeSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/nightshade.colorschemes"
        )
    )

    val activeScheme = schemes["Night Shade Active"]
    val enabledScheme = schemes["Night Shade Enabled"]
    val disabledScheme = schemes["Night Shade Disabled"]
    val disabledSelectedScheme = schemes["Night Shade Disabled Selected"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    defaultSchemeBundle.registerAlpha(0.6f, ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerColorScheme(disabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED)
    defaultSchemeBundle.registerColorScheme(disabledSelectedScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED)

    // borders
    val borderScheme = schemes["Night Shade Border"]
    defaultSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.BORDER)

    // marks
    val markActiveScheme = schemes["Night Shade Mark Active"]
    defaultSchemeBundle.registerColorScheme(
        markActiveScheme, ColorSchemeAssociationKind.MARK,
        *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        markActiveScheme,
        ColorSchemeAssociationKind.MARK, ComponentState.DISABLED_SELECTED,
        ComponentState.DISABLED_UNSELECTED
    )

    // separators
    val separatorScheme = schemes["Night Shade Separator"]
    defaultSchemeBundle.registerColorScheme(separatorScheme, ColorSchemeAssociationKind.SEPARATOR)

    // tab borders
    defaultSchemeBundle.registerColorScheme(
        schemes["Night Shade Tab Border"],
        ColorSchemeAssociationKind.TAB_BORDER, *ComponentState.activeStates
    )

    val backgroundScheme = schemes["Night Shade Background"]

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, backgroundScheme, DecorationAreaType.NONE)

    val decorationsSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    decorationsSchemeBundle.registerAlpha(0.4f, ComponentState.DISABLED_UNSELECTED)
    decorationsSchemeBundle.registerColorScheme(enabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED)

    // borders
    decorationsSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.BORDER)

    // marks
    decorationsSchemeBundle.registerColorScheme(
        markActiveScheme,
        ColorSchemeAssociationKind.MARK, *ComponentState.activeStates
    )

    // separators
    val separatorDecorationsScheme = schemes["Night Shade Decorations Separator"]
    decorationsSchemeBundle.registerColorScheme(
        separatorDecorationsScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )

    val decorationsBackgroundScheme = schemes["Night Shade Decorations Background"]
    result.registerDecorationAreaSchemeBundle(
        decorationsSchemeBundle, decorationsBackgroundScheme,
        DecorationAreaType.TOOLBAR, DecorationAreaType.FOOTER
    )

    val controlPaneBackgroundScheme = schemes["Night Shade Control Pane Background"]
    result.registerDecorationAreaSchemeBundle(
        decorationsSchemeBundle, controlPaneBackgroundScheme,
        DecorationAreaType.CONTROL_PANE
    )

    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeScheme,
        enabledScheme, disabledScheme
    )
    headerSchemeBundle.registerAlpha(
        0.6f, ComponentState.DISABLED_UNSELECTED,
        ComponentState.DISABLED_SELECTED
    )
    headerSchemeBundle.registerColorScheme(disabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED)
    headerSchemeBundle.registerColorScheme(disabledSelectedScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED)

    // borders
    val headerBorderScheme = schemes["Night Shade Header Border"]
    headerSchemeBundle.registerColorScheme(headerBorderScheme, ColorSchemeAssociationKind.BORDER)
    // marks
    headerSchemeBundle.registerColorScheme(
        markActiveScheme, ColorSchemeAssociationKind.MARK,
        *ComponentState.activeStates
    )
    headerSchemeBundle.registerColorScheme(
        markActiveScheme,
        ColorSchemeAssociationKind.MARK, ComponentState.DISABLED_SELECTED,
        ComponentState.DISABLED_UNSELECTED
    )
    headerSchemeBundle.registerColorScheme(
        separatorDecorationsScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )

    headerSchemeBundle.registerHighlightAlpha(0.7f, ComponentState.ROLLOVER_UNSELECTED)
    headerSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.SELECTED)
    headerSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.ROLLOVER_SELECTED)
    headerSchemeBundle.registerHighlightColorScheme(
        activeScheme,
        ComponentState.ROLLOVER_UNSELECTED, ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    val headerBackgroundScheme = schemes["Night Shade Header Background"]

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerBackgroundScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER
    )

    return result
}

fun nightShadeSkin(): AuroraSkinDefinition {
    val painters = Painters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.ultraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.lightColor },
            displayName = "Night Shade"
        ),
        borderPainter = CompositeBorderPainter(
            displayName = "Night Shade",
            outer = ClassicBorderPainter(),
            inner = DelegateBorderPainter(
                displayName = "Night Shade Inner", 
                delegate = ClassicBorderPainter(),
                topMask = 0x40FFFFFF,
                midMask = 0x20FFFFFF, 
                bottomMask = 0x00FFFFFF,
            ) { it.tint(0.2f) }),
        decorationPainter = MatteDecorationPainter()
    )

    // Add overlay painters to paint drop shadows along the bottom
    // edges of toolbars and footers
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.TOOLBAR, DecorationAreaType.FOOTER
    )

    // add an overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(BottomLineOverlayPainter(
        composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.5f))
    ), DecorationAreaType.TOOLBAR)

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopBezelOverlayPainter(
        colorSchemeQueryTop = composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.5f)),
        colorSchemeQueryBottom = composite({ it.foregroundColor }, ColorTransforms.alpha(0.125f))
    ), DecorationAreaType.FOOTER)

    return AuroraSkinDefinition(
        displayName = "Night Shade",
        colors = nightShadeSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
