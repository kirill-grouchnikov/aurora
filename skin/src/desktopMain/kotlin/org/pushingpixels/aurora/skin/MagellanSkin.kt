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
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.border.FractionBasedBorderPainter
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes


private fun magellanSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/magellan.colorschemes"
        )
    )

    val blueControlsActive = schemes["Magellan Blue Controls Active"]
    val blueControlsEnabled = schemes["Magellan Blue Controls Enabled"]

    val defaultColorSchemeBundle = AuroraColorSchemeBundle(
        blueControlsActive, blueControlsEnabled, blueControlsEnabled
    )
    defaultColorSchemeBundle.registerAlpha(
        0.5f, ComponentState.DISABLED_SELECTED,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsEnabled,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )

    // color schemes for the active states
    val blueControlsActiveBorder = schemes["Magellan Blue Controls Active Border"]
    val blueControlsEnabledBorder = schemes["Magellan Blue Controls Enabled Border"]
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.BORDER, *ComponentState.activeStates
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.BORDER,
        ComponentState.DISABLED_SELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsEnabledBorder,
        ColorSchemeAssociationKind.BORDER, ComponentState.ENABLED,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.MARK,
        ComponentState.SELECTED
    )
    defaultColorSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_SELECTED)
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActiveBorder,
        ColorSchemeAssociationKind.MARK, ComponentState.DISABLED_SELECTED
    )

    // color schemes for the pressed states
    val blueControlsPressed = schemes["Magellan Blue Controls Pressed"]
    val blueControlsPressedBorder = schemes["Magellan Blue Controls Pressed Border"]
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsPressed,
        ColorSchemeAssociationKind.FILL,
        ComponentState.PRESSED_SELECTED,
        ComponentState.PRESSED_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsPressedBorder,
        ColorSchemeAssociationKind.BORDER,
        ComponentState.PRESSED_SELECTED,
        ComponentState.PRESSED_UNSELECTED
    )

    // color schemes for the rollover / armed states
    val greenControls = schemes["Magellan Green Controls"]
    val greenControlsRollover = schemes["Magellan Green Controls Rollover"]
    val blueActiveControlsPressed = schemes["Magellan Blue Active Controls Pressed"]
    val greenControlsMark = schemes["Magellan Green Controls Mark"]
    val activeControlsBorder = schemes["Magellan Green Controls Border"]
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsRollover,
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueActiveControlsPressed,
        ColorSchemeAssociationKind.FILL,
        ComponentState.PRESSED_SELECTED,
        ComponentState.PRESSED_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsMark,
        ColorSchemeAssociationKind.MARK,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED,
        ComponentState.PRESSED_UNSELECTED,
        ComponentState.PRESSED_SELECTED,
        ComponentState.SELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        activeControlsBorder,
        ColorSchemeAssociationKind.BORDER,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED
    )

    // Also use active colors for selected checkboxes and radio buttons
    defaultColorSchemeBundle.registerColorScheme(
        greenControls,
        ColorSchemeAssociationKind.MARK_BOX,
        ComponentState.SELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        greenControlsRollover,
        ColorSchemeAssociationKind.MARK_BOX,
        ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED
    )
    defaultColorSchemeBundle.registerColorScheme(
        blueActiveControlsPressed,
        ColorSchemeAssociationKind.MARK_BOX,
        ComponentState.PRESSED_UNSELECTED,
        ComponentState.PRESSED_SELECTED
    )

    // color scheme for the uneditable text components
    val uneditable = ComponentState(
        "uneditable",
        arrayOf(ComponentStateFacet.ENABLE), arrayOf(ComponentStateFacet.EDITABLE)
    )
    val uneditableControls = schemes["Magellan Uneditable Controls"]
    defaultColorSchemeBundle.registerColorScheme(
        uneditableControls,
        ColorSchemeAssociationKind.FILL, uneditable
    )

    // color scheme for the selected state - preventing fallback to the
    // rollover selected state
    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive,
        ColorSchemeAssociationKind.FILL, ComponentState.SELECTED
    )
    // But continue using green for selected highlight text
    defaultColorSchemeBundle.registerColorScheme(
        greenControls,
        ColorSchemeAssociationKind.HIGHLIGHT_TEXT
    )

    // highlight alphas
    defaultColorSchemeBundle.registerHighlightAlpha(0.75f, ComponentState.ROLLOVER_UNSELECTED)
    defaultColorSchemeBundle.registerHighlightAlpha(0.85f, ComponentState.SELECTED)
    defaultColorSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.ROLLOVER_SELECTED)
    defaultColorSchemeBundle.registerHighlightColorScheme(
        greenControls, ComponentState.ROLLOVER_UNSELECTED,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    defaultColorSchemeBundle.registerColorScheme(
        blueControlsActive.tint(0.2f),
        ColorSchemeAssociationKind.TAB,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    val lightBlueBackground = schemes["Magellan Light Blue Background"]

    result.registerDecorationAreaSchemeBundle(
        defaultColorSchemeBundle,
        lightBlueBackground, DecorationAreaType.NONE
    )

    val mediumBlueBackground = schemes["Magellan Medium Blue Background"]
    val darkBlueBackground = schemes["Magellan Dark Blue Background"]
    result.registerAsDecorationArea(
        mediumBlueBackground,
        DecorationAreaType.CONTROL_PANE, DecorationAreaType.TOOLBAR
    )
    result.registerAsDecorationArea(
        darkBlueBackground,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER
    )

    val lightBlueControlsActive = schemes["Magellan Light Blue Controls Active"]
    val lightBlueControlsEnabled = schemes["Magellan Light Blue Controls Enabled"]
    val lightBlueBordersEnabled = schemes["Magellan Light Blue Borders Enabled"]
    val footerColorSchemeBundle = AuroraColorSchemeBundle(
        lightBlueControlsActive, lightBlueControlsEnabled,
        lightBlueControlsEnabled
    )
    footerColorSchemeBundle.registerAlpha(
        0.5f, ComponentState.DISABLED_SELECTED,
        ComponentState.DISABLED_UNSELECTED
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueControlsEnabled,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueControlsActive,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    footerColorSchemeBundle.registerColorScheme(
        lightBlueBordersEnabled,
        ColorSchemeAssociationKind.BORDER, ComponentState.ENABLED
    )

    val lightBlueSeparator = schemes["Magellan Light Blue Separator"]
    footerColorSchemeBundle.registerColorScheme(
        lightBlueSeparator,
        ColorSchemeAssociationKind.SEPARATOR
    )

    // And use light-on-blue for text highlights in the footer area
    footerColorSchemeBundle.registerColorScheme(
        blueControlsEnabled,
        ColorSchemeAssociationKind.HIGHLIGHT_TEXT
    )

    val ultraLightBlueBackground = schemes["Magellan Ultralight Blue Background"]
    result.registerDecorationAreaSchemeBundle(
        footerColorSchemeBundle,
        ultraLightBlueBackground, DecorationAreaType.FOOTER
    )

    return result
}

fun magellanSkin(): AuroraSkinDefinition {
    val painters = Painters(
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
        decorationPainter = MatteDecorationPainter()
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

    // add an overlay painter to paint a light line along the top
    // edge of toolbars
    painters.addOverlayPainter(
        TopLineOverlayPainter(
            composite(
                { it.foregroundColor },
                ColorTransforms.alpha(0.15625f)
            )
        ), DecorationAreaType.TOOLBAR
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.FOOTER)

    return AuroraSkinDefinition(
        displayName = "Magellan",
        colors = magellanSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
