/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.theming

import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.painter.border.GlassBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun ceruleanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/cerulean.colorschemes"
        )
    )

    val activeScheme = schemes["Cerulean Active"]
    val enabledScheme = schemes["Cerulean Enabled"]
    val disabledScheme = schemes["Cerulean Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Pressed"],
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected, ComponentState.PressedUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Disabled Selected"],
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Selected"],
        ColorSchemeAssociationKind.Fill,
        ComponentState.Selected
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Rollover Selected"],
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverSelected
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Rollover Unselected"],
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )

    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Mark"],
        ColorSchemeAssociationKind.Mark, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        schemes["Cerulean Border"],
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )

    // for progress bars
    val determinateState = ComponentState(
        "determinate enabled", arrayOf(
            ComponentStateFacet.Enable,
            ComponentStateFacet.Determinate, ComponentStateFacet.Selection
        ),
        null
    )
    val determinateDisabledState = ComponentState(
        "determinate disabled", arrayOf(
            ComponentStateFacet.Determinate,
            ComponentStateFacet.Selection
        ), arrayOf(ComponentStateFacet.Enable)
    )
    val indeterminateState = ComponentState(
        "indeterminate enabled", arrayOf(
            ComponentStateFacet.Enable,
            ComponentStateFacet.Selection
        ), arrayOf(ComponentStateFacet.Determinate)
    )
    val indeterminateDisabledState = ComponentState(
        "indeterminate disabled",
        null, arrayOf(
            ComponentStateFacet.Determinate,
            ComponentStateFacet.Enable, ComponentStateFacet.Selection
        )
    )
    val rolloverSelectedScheme = schemes["Cerulean Rollover Selected"]
    defaultSchemeBundle.registerColorScheme(
        rolloverSelectedScheme,
        ColorSchemeAssociationKind.Fill,
        determinateState, indeterminateState
    )
    defaultSchemeBundle.registerColorScheme(
        rolloverSelectedScheme,
        ColorSchemeAssociationKind.Border, determinateState, indeterminateState
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.Fill,
        determinateDisabledState, indeterminateDisabledState
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.Border,
        determinateDisabledState, indeterminateDisabledState
    )

    // for text highlight
    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/kitchen-sink.colorschemes"
        )
    )

    val highlightColorScheme = kitchenSinkSchemes["Moderate Highlight"]
    defaultSchemeBundle.registerHighlightColorScheme(highlightColorScheme)

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.None)

    val activeHeaderScheme = schemes["Cerulean Active Header"]
    val headerScheme = schemes["Cerulean Header"]
    val disabledHeaderScheme = schemes["Cerulean Header Disabled"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeHeaderScheme, headerScheme, disabledHeaderScheme
    )
    headerSchemeBundle.registerAlpha(
        0.6f, ComponentState.DisabledUnselected,
        ComponentState.DisabledSelected
    )
    headerSchemeBundle.registerColorScheme(
        activeHeaderScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected, ComponentState.DisabledUnselected
    )
    headerSchemeBundle.registerColorScheme(
        activeHeaderScheme,
        ColorSchemeAssociationKind.Mark,
        ComponentState.DisabledSelected, ComponentState.DisabledUnselected
    )
    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerScheme,
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    result.registerAsDecorationArea(
        backgroundColorScheme = schemes["Cerulean Footer"],
        noneTransformationOverlay = {
            it.registerColorScheme(
                schemes["Cerulean Footer Separator"],
                ColorSchemeAssociationKind.Separator
            )
        },
        areaTypes = arrayOf(DecorationAreaType.Footer, DecorationAreaType.ControlPane)
    )

    return result
}

fun ceruleanSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = SpecularRectangularFillPainter(ClassicFillPainter(), 0.5f),
        borderPainter = GlassBorderPainter(),
        decorationPainter = ArcDecorationPainter()
    )

    // Add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)

    return AuroraSkinDefinition(
        displayName = "Cerulean",
        colors = ceruleanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
