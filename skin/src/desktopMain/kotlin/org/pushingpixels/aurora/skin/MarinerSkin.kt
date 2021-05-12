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

    defaultSchemeBundle.registerAlpha(0.8f, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerAlpha(0.8f, ComponentState.DISABLED_UNSELECTED)
    defaultSchemeBundle.registerColorScheme(
        disabledSelectedScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )

    // borders
    val activeBorderScheme = schemes["Mariner Active Border"]
    val enabledBorderScheme = schemes["Mariner Enabled Border"]
    val disabledSelectedBorderScheme = schemes["Mariner Disabled Selected Border"]
    defaultSchemeBundle.registerColorScheme(
        activeBorderScheme,
        ColorSchemeAssociationKind.BORDER, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        disabledSelectedBorderScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        enabledBorderScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.ENABLED
    )

    // marks
    val activeMarkScheme = schemes["Mariner Active Mark"]
    val enabledMarkScheme = schemes["Mariner Enabled Mark"]
    defaultSchemeBundle.registerColorScheme(
        activeMarkScheme, ColorSchemeAssociationKind.MARK,
        *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        enabledMarkScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.ENABLED
    )

    val uneditableState =
        ComponentState(
            "uneditable", arrayOf(ComponentStateFacet.ENABLE),
            arrayOf(ComponentStateFacet.EDITABLE)
        )
    val uneditableControls = schemes["Mariner Uneditable"]
    defaultSchemeBundle.registerColorScheme(
        uneditableControls, ColorSchemeAssociationKind.FILL,
        uneditableState
    )

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.NONE)

    // header color scheme bundle
    val headerColorScheme = schemes["Mariner Header"]
    val headerBorderColorScheme = schemes["Mariner Header Border"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        headerColorScheme, headerColorScheme, headerColorScheme
    )
    headerSchemeBundle.registerAlpha(0.4f, ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED)
    headerSchemeBundle.registerColorScheme(
        headerColorScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED, ComponentState.DISABLED_UNSELECTED
    )
    headerSchemeBundle.registerColorScheme(
        activeScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED, ComponentState.ROLLOVER_SELECTED
    )
    headerSchemeBundle.registerColorScheme(headerColorScheme, ColorSchemeAssociationKind.MARK)
    headerSchemeBundle.registerColorScheme(
        headerBorderColorScheme,
        ColorSchemeAssociationKind.BORDER
    )
    headerSchemeBundle.registerColorScheme(
        enabledMarkScheme.shade(0.8f), ColorSchemeAssociationKind.MARK,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED,
        ComponentState.PRESSED_SELECTED
    )
    headerSchemeBundle.registerColorScheme(
        enabledMarkScheme.shade(0.7f), ColorSchemeAssociationKind.MARK,
        ComponentState.ROLLOVER_UNSELECTED
    )
    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerColorScheme,
        DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
    )

    // footer color scheme bundle
    val enabledFooterScheme = schemes["Mariner Footer Enabled"]
    val disabledFooterScheme = schemes["Mariner Footer Disabled"]

    val footerSchemeBundle = AuroraColorSchemeBundle(
        activeScheme,
        enabledFooterScheme, disabledFooterScheme
    )

    footerSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_SELECTED)
    footerSchemeBundle.registerAlpha(0.8f, ComponentState.DISABLED_UNSELECTED)
    footerSchemeBundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    footerSchemeBundle.registerColorScheme(
        disabledFooterScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )

    // borders
    val footerEnabledBorderScheme = schemes["Mariner Footer Enabled Border"]
    footerSchemeBundle.registerColorScheme(
        activeBorderScheme,
        ColorSchemeAssociationKind.BORDER, *ComponentState.activeStates
    )
    footerSchemeBundle.registerColorScheme(
        activeBorderScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.DISABLED_SELECTED
    )
    footerSchemeBundle.registerColorScheme(
        footerEnabledBorderScheme,
        ColorSchemeAssociationKind.BORDER, ComponentState.ENABLED
    )

    // marks
    val footerEnabledMarkScheme = schemes["Mariner Footer Enabled Mark"]
    footerSchemeBundle.registerColorScheme(
        activeMarkScheme, ColorSchemeAssociationKind.MARK,
        *ComponentState.activeStates
    )
    footerSchemeBundle.registerColorScheme(
        footerEnabledMarkScheme,
        ColorSchemeAssociationKind.MARK, ComponentState.ENABLED
    )

    // separators
    val footerSeparatorScheme = schemes["Mariner Footer Separator"]
    footerSchemeBundle.registerColorScheme(
        footerSeparatorScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )

    val footerBackgroundColorScheme = schemes["Mariner Footer Background"]
    result.registerDecorationAreaSchemeBundle(
        footerSchemeBundle, footerBackgroundColorScheme,
        DecorationAreaType.FOOTER, DecorationAreaType.TOOLBAR, DecorationAreaType.CONTROL_PANE
    )
    return result
}

fun marinerSkin(): AuroraSkinDefinition {
    val painters = Painters(
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
        DecorationAreaType.FOOTER
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(0.5f))
        ),
        DecorationAreaType.HEADER
    )

    // add overlay painter to paint drop shadows along the bottom
    // edges of toolbars
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.TOOLBAR
    )

    // add overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.ultraDarkColor }),
        DecorationAreaType.TOOLBAR
    )

    return AuroraSkinDefinition(
        displayName = "Mariner",
        colors = marinerSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
