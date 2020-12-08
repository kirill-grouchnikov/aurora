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
import org.pushingpixels.aurora.colorscheme.*
import org.pushingpixels.aurora.painter.border.FlatBorderPainter
import org.pushingpixels.aurora.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.painter.fill.SubduedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun nebulaBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/nebula.colorschemes"
        )
    )

    val activeScheme = schemes["Nebula Active"]
    val enabledScheme = schemes["Nebula Enabled"]
    val rolloverUnselectedScheme = schemes["Nebula Rollover Unselected"]
    val pressedScheme = schemes["Nebula Pressed"]
    val rolloverSelectedScheme = schemes["Nebula Rollover Selected"]
    val disabledScheme = schemes["Nebula Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    defaultSchemeBundle.registerColorScheme(
        rolloverUnselectedScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        rolloverSelectedScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        pressedScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.PRESSED_SELECTED, ComponentState.PRESSED_UNSELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        rolloverUnselectedScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.SELECTED
    )

    defaultSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.ROLLOVER_UNSELECTED)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.SELECTED)
    defaultSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.ROLLOVER_SELECTED)
    defaultSchemeBundle.registerHighlightColorScheme(
        pressedScheme, ComponentState.ROLLOVER_UNSELECTED,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    // for progress bars
    val determinateState = ComponentState(
        "determinate", arrayOf(
            ComponentStateFacet.ENABLE,
            ComponentStateFacet.DETERMINATE
        ), null
    )
    val indeterminateState =
        ComponentState("indeterminate", arrayOf(ComponentStateFacet.ENABLE), arrayOf(ComponentStateFacet.DETERMINATE))
    val determinateScheme = schemes["Nebula Determinate"]
    val determinateBorderScheme = schemes["Nebula Determinate Border"]
    defaultSchemeBundle.registerColorScheme(
        determinateScheme,
        ColorSchemeAssociationKind.FILL,
        determinateState, indeterminateState
    )
    defaultSchemeBundle.registerColorScheme(
        determinateBorderScheme,
        ColorSchemeAssociationKind.BORDER, determinateState,
        indeterminateState
    )

    val determinateDisabledState = ComponentState(
        "determinate disabled", arrayOf(ComponentStateFacet.DETERMINATE), arrayOf(ComponentStateFacet.ENABLE)
    )
    val indeterminateDisabledState = ComponentState(
        "indeterminate disabled", null, arrayOf(
            ComponentStateFacet.ENABLE,
            ComponentStateFacet.DETERMINATE
        )
    )
    val determinateDisabledScheme = schemes["Nebula Determinate Disabled"]
    val determinateDisabledBorderScheme = schemes["Nebula Determinate Disabled Border"]
    defaultSchemeBundle.registerColorScheme(
        determinateDisabledScheme,
        ColorSchemeAssociationKind.FILL,
        determinateDisabledState, indeterminateDisabledState
    )
    defaultSchemeBundle.registerColorScheme(
        determinateDisabledBorderScheme,
        ColorSchemeAssociationKind.BORDER, determinateDisabledState,
        indeterminateDisabledState
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, DecorationAreaType.NONE
    )

    result.registerAsDecorationArea(
        backgroundColorScheme = schemes["Nebula Decorations"],
        noneTransformationOverlay = { bundle ->
            bundle.registerColorScheme(
                schemes["Nebula Decorations Separator"],
                ColorSchemeAssociationKind.SEPARATOR
            )
        },
        areaTypes = arrayOf(DecorationAreaType.FOOTER, DecorationAreaType.CONTROL_PANE)
    )

    result.registerAsDecorationArea(
        accentBuilder.windowChromeAccent!!,
        DecorationAreaType.PRIMARY_TITLE_PANE,
        DecorationAreaType.SECONDARY_TITLE_PANE,
        DecorationAreaType.HEADER
    );


    return result
}

private fun nebulaBasePainters(): Painters {
    val painters = Painters(
        fillPainter = SubduedFillPainter(),
        borderPainter = FlatBorderPainter(),
        decorationPainter = MarbleNoiseDecorationPainter(
            textureAlpha = 0.3f,
            baseDecorationPainter = ArcDecorationPainter()
        )
    )

    // add an overlay painter to paint a drop shadow along the top edge of toolbars
    painters.addOverlayPainter(
        TopShadowOverlayPainter.getInstance(60),
        DecorationAreaType.TOOLBAR
    );

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.darkColor }, ColorTransforms.alpha(0.625f))
        ),
        DecorationAreaType.PRIMARY_TITLE_PANE,
        DecorationAreaType.SECONDARY_TITLE_PANE,
        DecorationAreaType.HEADER
    )

    return painters
}

fun nebulaSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Nebula",
        colors = nebulaBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/nebula.colorschemes")
                .withWindowChromeAccent("Nebula Decorations")
        ),
        painters = nebulaBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun nebulaAmethystSkin(): AuroraSkinDefinition {
    val accentBuilder = AccentBuilder().withWindowChromeAccent(PurpleColorScheme())

    return AuroraSkinDefinition(
        displayName = "Nebula Amethyst",
        colors = nebulaBaseSkinColors(
            AccentBuilder().withWindowChromeAccent(PurpleColorScheme())
        ).also {
            // Use the window chrome accent color scheme on toolbars
            it.registerAsDecorationArea(
                accentBuilder.windowChromeAccent!!,
                DecorationAreaType.TOOLBAR
            )
        },
        painters = nebulaBasePainters().also { painters ->
            // Clear the top shadow painter on the toolbars and add combined
            // separator + drop shadow along the toolbar bottom
            painters.clearOverlayPainters(DecorationAreaType.TOOLBAR)
            painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.TOOLBAR);
            painters.addOverlayPainter(
                BottomLineOverlayPainter(
                    composite({ it.darkColor }, ColorTransforms.alpha(0.625f))
                ), DecorationAreaType.TOOLBAR
            )

        },
        buttonShaper = ClassicButtonShaper()
    )
}

fun nebulaBrickWallSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Nebula Brick Wall",
        colors = nebulaBaseSkinColors(
            AccentBuilder().withWindowChromeAccent(OrangeColorScheme())
        ),
        painters = nebulaBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

