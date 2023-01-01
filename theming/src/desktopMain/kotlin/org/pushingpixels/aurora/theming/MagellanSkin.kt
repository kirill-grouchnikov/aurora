/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.theming.colorscheme.composite
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.theming.painter.border.FractionBasedBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun magellanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/magellan.colorschemes"
        )
    )

    val blueControlsActive = schemes["Magellan Blue Controls Active"]
    val blueControlsEnabled = schemes["Magellan Blue Controls Enabled"]

    val defaultColorSchemeBundle = AuroraColorSchemeBundle(
        blueControlsActive, blueControlsEnabled, blueControlsEnabled
    )
    defaultColorSchemeBundle.registerAlpha(
        0.5f, ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsEnabled,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    // color schemes for the active states
    val blueControlsActiveBorder = schemes["Magellan Blue Controls Active Border"]
    val blueControlsEnabledBorder = schemes["Magellan Blue Controls Enabled Border"]
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Border,
        ComponentState.DisabledSelected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsEnabledBorder,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled,
        ComponentState.DisabledUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Mark,
        ComponentState.Selected
    )
    defaultColorSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.Mark, ComponentState.DisabledSelected
    )

    // color schemes for the pressed states
    val blueControlsPressed = schemes["Magellan Blue Controls Pressed"]
    val blueControlsPressedBorder = schemes["Magellan Blue Controls Pressed Border"]
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsPressed,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsPressedBorder,
        ColorSchemeAssociationKind.Border,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )

    // color schemes for the rollover / armed states
    val greenControls = schemes["Magellan Green Controls"]
    val greenControlsRollover = schemes["Magellan Green Controls Rollover"]
    val blueActiveControlsPressed = schemes["Magellan Blue Active Controls Pressed"]
    val greenControlsMark = schemes["Magellan Green Controls Mark"]
    val activeControlsBorder = schemes["Magellan Green Controls Border"]
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsRollover,
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueActiveControlsPressed,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsMark,
        ColorSchemeAssociationKind.Mark,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected,
        ComponentState.PressedUnselected,
        ComponentState.PressedSelected,
        ComponentState.Selected
    )
    defaultColorSchemeBundle.registerColorScheme(
        activeControlsBorder,
        ColorSchemeAssociationKind.Border,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )

    // Also use active colors for selected checkboxes and radio buttons
    defaultColorSchemeBundle.registerColorScheme(
        greenControls,
        ColorSchemeAssociationKind.MarkBox,
        ComponentState.Selected
    )
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsRollover,
        ColorSchemeAssociationKind.MarkBox,
        ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueActiveControlsPressed,
        ColorSchemeAssociationKind.MarkBox,
        ComponentState.PressedUnselected,
        ComponentState.PressedSelected
    )

    // color scheme for the selected state - preventing fallback to the
    // rollover selected state
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive,
        ColorSchemeAssociationKind.Fill, ComponentState.Selected
    )
    // But continue using green for selected highlight text
    defaultColorSchemeBundle.registerColorScheme(
        greenControls,
        ColorSchemeAssociationKind.HighlightText
    )

    // highlight alphas
    defaultColorSchemeBundle.registerHighlightAlpha(0.75f, ComponentState.RolloverUnselected)
    defaultColorSchemeBundle.registerHighlightAlpha(0.85f, ComponentState.Selected)
    defaultColorSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.RolloverSelected)
    defaultColorSchemeBundle.registerHighlightColorScheme(
        greenControls, ComponentState.RolloverUnselected,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive.tint(0.2f),
        ColorSchemeAssociationKind.Tab,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    val lightBlueBackground = schemes["Magellan Light Blue Background"]

    result.registerDecorationAreaSchemeBundle(
        defaultColorSchemeBundle,
        lightBlueBackground, DecorationAreaType.None
    )

    val mediumBlueBackground = schemes["Magellan Medium Blue Background"]
    val darkBlueBackground = schemes["Magellan Dark Blue Background"]
    result.registerAsDecorationArea(
        mediumBlueBackground,
        DecorationAreaType.ControlPane, DecorationAreaType.Toolbar
    )
    result.registerAsDecorationArea(
        darkBlueBackground,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
    )

    val lightBlueControlsActive = schemes["Magellan Light Blue Controls Active"]
    val lightBlueControlsEnabled = schemes["Magellan Light Blue Controls Enabled"]
    val lightBlueBordersEnabled = schemes["Magellan Light Blue Borders Enabled"]
    val footerColorSchemeBundle = AuroraColorSchemeBundle(
        lightBlueControlsActive, lightBlueControlsEnabled,
        lightBlueControlsEnabled
    )
    footerColorSchemeBundle.registerAlpha(
        0.5f, ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueControlsEnabled,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueControlsActive,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueBordersEnabled,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    val lightBlueSeparator = schemes["Magellan Light Blue Separator"]
    footerColorSchemeBundle.registerColorScheme(
        lightBlueSeparator,
        ColorSchemeAssociationKind.Separator
    )

    // And use light-on-blue for text highlights in the footer area
    footerColorSchemeBundle.registerColorScheme(
        blueControlsEnabled,
        ColorSchemeAssociationKind.HighlightText
    )

    val ultraLightBlueBackground = schemes["Magellan Ultralight Blue Background"]
    result.registerDecorationAreaSchemeBundle(
        footerColorSchemeBundle,
        ultraLightBlueBackground, DecorationAreaType.Footer
    )

    return result
}

fun magellanSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.extraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.midColor },
            displayName = "Magellan"
        ),
        borderPainter = CompositeBorderPainter(
            displayName = "Magellan",
            outer = FractionBasedBorderPainter(
                0.0f to { it.ultraDarkColor },
                0.5f to { it.darkColor },
                1.0f to { it.darkColor },
                displayName = "Magellan Outer"
            ),
            inner = DelegateBorderPainter(
                displayName = "Magellan Inner", delegate = ClassicBorderPainter(),
                topMask = 0xA0FFFFFF,
                midMask = 0x60FFFFFF,
                bottomMask = 0x40FFFFFF
            ) { it.tint(0.5f) }),
        decorationPainter = MatteDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
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

    // add an overlay painter to paint a light line along the top
    // edge of toolbars
    painters.addOverlayPainter(
        TopLineOverlayPainter(
            composite(
                { it.foregroundColor },
                ColorTransforms.alpha(0.15625f)
            )
        ), DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Footer)

    return AuroraSkinDefinition(
        displayName = "Magellan",
        colors = magellanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
