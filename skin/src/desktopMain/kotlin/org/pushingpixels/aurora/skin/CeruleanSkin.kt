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
import org.pushingpixels.aurora.painter.border.GlassBorderPainter
import org.pushingpixels.aurora.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun ceruleanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/cerulean.colorschemes"
        )
    )

    val activeScheme = schemes["Cerulean Active"]
    val enabledScheme = schemes["Cerulean Enabled"]
    val rolloverSelectedScheme = schemes["Cerulean Rollover Selected"]
    val disabledScheme = schemes["Cerulean Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Pressed"],
        ColorSchemeAssociationKind.FILL,
        ComponentState.PRESSED_SELECTED, ComponentState.PRESSED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Disabled Selected"],
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Selected"],
        ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Rollover Selected"],
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Rollover Unselected"],
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Mark"],
        ColorSchemeAssociationKind.MARK, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Border"],
        ColorSchemeAssociationKind.BORDER, *ComponentState.activeStates
    )

    // for progress bars
    val determinateState = ComponentState(
        "determinate enabled", arrayOf(
            ComponentStateFacet.ENABLE,
            ComponentStateFacet.DETERMINATE, ComponentStateFacet.SELECTION
        ),
        null
    )
    val determinateDisabledState = ComponentState(
        "determinate disabled", arrayOf(
            ComponentStateFacet.DETERMINATE,
            ComponentStateFacet.SELECTION
        ), arrayOf(ComponentStateFacet.ENABLE)
    )
    val indeterminateState = ComponentState(
        "indeterminate enabled", arrayOf(
            ComponentStateFacet.ENABLE,
            ComponentStateFacet.SELECTION
        ), arrayOf(ComponentStateFacet.DETERMINATE)
    )
    val indeterminateDisabledState = ComponentState(
        "indeterminate disabled",
        null, arrayOf(
            ComponentStateFacet.DETERMINATE,
            ComponentStateFacet.ENABLE, ComponentStateFacet.SELECTION
        )
    )
    defaultSchemeBundle.registerColorScheme(
        rolloverSelectedScheme,
        ColorSchemeAssociationKind.FILL,
        determinateState, indeterminateState
    )
    defaultSchemeBundle.registerColorScheme(
        rolloverSelectedScheme,
        ColorSchemeAssociationKind.BORDER, determinateState, indeterminateState
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.FILL,
        determinateDisabledState, indeterminateDisabledState
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.BORDER,
        determinateDisabledState, indeterminateDisabledState
    )

    // for uneditable fields
    val editable = ComponentState(
        "editable", arrayOf(
            ComponentStateFacet.ENABLE, ComponentStateFacet.EDITABLE
        ), null
    )
    val uneditable = ComponentState(
        "uneditable",
        editable,
        arrayOf(ComponentStateFacet.ENABLE),
        arrayOf(ComponentStateFacet.EDITABLE)
    )
    defaultSchemeBundle.registerColorScheme(
        defaultSchemeBundle.getColorScheme(editable),
        ColorSchemeAssociationKind.FILL, uneditable
    )

    // for text highlight
    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes"
        )
    )

    val highlightColorScheme = kitchenSinkSchemes["Moderate Highlight"]
    defaultSchemeBundle.registerHighlightColorScheme(highlightColorScheme)

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.NONE)

    val activeHeaderScheme = schemes["Cerulean Active Header"]
    val headerScheme = schemes["Cerulean Header"]
    val disabledHeaderScheme = schemes["Cerulean Header Disabled"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeHeaderScheme, headerScheme, disabledHeaderScheme
    )
    headerSchemeBundle.registerAlpha(
        0.6f, ComponentState.DISABLED_UNSELECTED,
        ComponentState.DISABLED_SELECTED
    )
    headerSchemeBundle.registerColorScheme(
        activeHeaderScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED
    )
    headerSchemeBundle.registerColorScheme(
        activeHeaderScheme,
        ColorSchemeAssociationKind.MARK,
        ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED
    )
    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerScheme,
        DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
    )

    result.registerAsDecorationArea(
        backgroundColorScheme = schemes["Cerulean Footer"],
        noneTransformationOverlay = {
            it.registerColorScheme(
                schemes["Cerulean Footer Separator"],
                ColorSchemeAssociationKind.SEPARATOR
            )
        },
        areaTypes = arrayOf(DecorationAreaType.FOOTER, DecorationAreaType.CONTROL_PANE)
    )

    return result
}

fun ceruleanSkin(): AuroraSkinDefinition {
    val painters = Painters(
        fillPainter = ClassicFillPainter(),
        borderPainter = GlassBorderPainter(),
        decorationPainter = ArcDecorationPainter()
    )

    // Add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.TOOLBAR);

    return AuroraSkinDefinition(
        displayName = "Green Magic",
        colors = ceruleanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
