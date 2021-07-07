/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.skin

import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.colorscheme.composite
import org.pushingpixels.aurora.painter.border.FractionBasedBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun marinerSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/mariner.colorschemes"
        )
    )

    val activeScheme = schemes["Mariner Active"]
    val enabledScheme = schemes["Mariner Enabled"]
    val disabledScheme = schemes["Mariner Disabled"]

    val disabledSelectedScheme = schemes["Mariner Disabled Selected"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    defaultSchemeBundle.registerAlpha(0.8f, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerAlpha(0.8f, ComponentState.DisabledUnselected)
    defaultSchemeBundle.registerColorScheme(
        disabledSelectedScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )

    // borders
    val activeBorderScheme = schemes["Mariner Active Border"]
    val enabledBorderScheme = schemes["Mariner Enabled Border"]
    val disabledSelectedBorderScheme = schemes["Mariner Disabled Selected Border"]
    defaultSchemeBundle.registerColorScheme(
        activeBorderScheme,
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        disabledSelectedBorderScheme,
        ColorSchemeAssociationKind.Border, ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        enabledBorderScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    // marks
    val activeMarkScheme = schemes["Mariner Active Mark"]
    val enabledMarkScheme = schemes["Mariner Enabled Mark"]
    defaultSchemeBundle.registerColorScheme(
        activeMarkScheme, ColorSchemeAssociationKind.Mark,
        *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        enabledMarkScheme, ColorSchemeAssociationKind.Mark,
        ComponentState.Enabled
    )

    val uneditableState =
        ComponentState(
            "uneditable", arrayOf(ComponentStateFacet.Enable),
            arrayOf(ComponentStateFacet.Editable)
        )
    val uneditableControls = schemes["Mariner Uneditable"]
    defaultSchemeBundle.registerColorScheme(
        uneditableControls, ColorSchemeAssociationKind.Fill,
        uneditableState
    )

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.None)

    // header color scheme bundle
    val headerColorScheme = schemes["Mariner Header"]
    val headerBorderColorScheme = schemes["Mariner Header Border"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        headerColorScheme, headerColorScheme, headerColorScheme
    )
    headerSchemeBundle.registerAlpha(0.4f, ComponentState.DisabledSelected, ComponentState.DisabledUnselected)
    headerSchemeBundle.registerColorScheme(
        headerColorScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected, ComponentState.DisabledUnselected
    )
    headerSchemeBundle.registerColorScheme(
        activeScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected, ComponentState.RolloverSelected
    )
    headerSchemeBundle.registerColorScheme(headerColorScheme, ColorSchemeAssociationKind.Mark)
    headerSchemeBundle.registerColorScheme(
        headerBorderColorScheme,
        ColorSchemeAssociationKind.Border
    )
    headerSchemeBundle.registerColorScheme(
        enabledMarkScheme.shade(0.8f), ColorSchemeAssociationKind.Mark,
        ComponentState.Selected, ComponentState.RolloverSelected,
        ComponentState.PressedSelected
    )
    headerSchemeBundle.registerColorScheme(
        enabledMarkScheme.shade(0.7f), ColorSchemeAssociationKind.Mark,
        ComponentState.RolloverUnselected
    )

    headerSchemeBundle.registerHighlightAlpha(1.0f)
    headerSchemeBundle.registerHighlightColorScheme(activeScheme)
    // the next line is to have consistent coloring during the rollover menu animations
    headerSchemeBundle.registerHighlightAlpha(0.0f, ComponentState.Enabled)

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerColorScheme,
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    // footer color scheme bundle
    val enabledFooterScheme = schemes["Mariner Footer Enabled"]
    val disabledFooterScheme = schemes["Mariner Footer Disabled"]

    val footerSchemeBundle = AuroraColorSchemeBundle(
        activeScheme,
        enabledFooterScheme, disabledFooterScheme
    )

    footerSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    footerSchemeBundle.registerAlpha(0.8f, ComponentState.DisabledUnselected)
    footerSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    footerSchemeBundle.registerColorScheme(
        disabledFooterScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )

    // borders
    val footerEnabledBorderScheme = schemes["Mariner Footer Enabled Border"]
    footerSchemeBundle.registerColorScheme(
        activeBorderScheme,
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )
    footerSchemeBundle.registerColorScheme(
        activeBorderScheme,
        ColorSchemeAssociationKind.Border, ComponentState.DisabledSelected
    )
    footerSchemeBundle.registerColorScheme(
        footerEnabledBorderScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    // marks
    val footerEnabledMarkScheme = schemes["Mariner Footer Enabled Mark"]
    footerSchemeBundle.registerColorScheme(
        activeMarkScheme, ColorSchemeAssociationKind.Mark,
        *ComponentState.activeStates
    )
    footerSchemeBundle.registerColorScheme(
        footerEnabledMarkScheme,
        ColorSchemeAssociationKind.Mark, ComponentState.Enabled
    )

    // separators
    val footerSeparatorScheme = schemes["Mariner Footer Separator"]
    footerSchemeBundle.registerColorScheme(
        footerSeparatorScheme,
        ColorSchemeAssociationKind.Separator
    )

    val footerBackgroundColorScheme = schemes["Mariner Footer Background"]
    result.registerDecorationAreaSchemeBundle(
        footerSchemeBundle, footerBackgroundColorScheme,
        DecorationAreaType.Footer, DecorationAreaType.Toolbar, DecorationAreaType.ControlPane
    )
    return result
}

fun marinerSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.extraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.midColor },
            displayName = "Mariner"
        ),
        borderPainter = FractionBasedBorderPainter(
            0.0f to { it.ultraDarkColor },
            0.5f to { it.darkColor },
            1.0f to { it.midColor },
            displayName = "Mariner"
        ),
        decorationPainter = MatteDecorationPainter()
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorSchemeQueryTop = { it.ultraDarkColor },
            colorSchemeQueryBottom = { it.lightColor }
        ),
        DecorationAreaType.Footer
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(0.5f))
        ),
        DecorationAreaType.Header
    )

    // add overlay painter to paint drop shadows along the bottom
    // edges of toolbars
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.Toolbar
    )

    // add overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.ultraDarkColor }),
        DecorationAreaType.Toolbar
    )

    return AuroraSkinDefinition(
        displayName = "Mariner",
        colors = marinerSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
