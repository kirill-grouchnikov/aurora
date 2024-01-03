/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopBezelOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun nightShadeSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/nightshade.colorschemes"
        )
    )

    val activeScheme = schemes["Night Shade Active"]
    val enabledScheme = schemes["Night Shade Enabled"]
    val disabledScheme = schemes["Night Shade Disabled"]
    val disabledSelectedScheme = schemes["Night Shade Disabled Selected"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    defaultSchemeBundle.registerAlpha(0.6f, ComponentState.DisabledUnselected, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(disabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected)
    defaultSchemeBundle.registerColorScheme(disabledSelectedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected)

    // borders
    val borderScheme = schemes["Night Shade Border"]
    defaultSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.Border)

    // marks
    val markActiveScheme = schemes["Night Shade Mark Active"]
    defaultSchemeBundle.registerColorScheme(
        markActiveScheme, ColorSchemeAssociationKind.Mark,
        *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        markActiveScheme,
        ColorSchemeAssociationKind.Mark, ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )

    // separators
    val separatorScheme = schemes["Night Shade Separator"]
    defaultSchemeBundle.registerColorScheme(separatorScheme, ColorSchemeAssociationKind.Separator)

    // tab borders
    defaultSchemeBundle.registerColorScheme(
        schemes["Night Shade Tab Border"],
        ColorSchemeAssociationKind.TabBorder, *ComponentState.activeStates
    )

    val backgroundScheme = schemes["Night Shade Background"]

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, backgroundScheme, DecorationAreaType.None)

    val decorationsSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    decorationsSchemeBundle.registerAlpha(0.4f, ComponentState.DisabledUnselected)
    decorationsSchemeBundle.registerColorScheme(enabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected)

    // borders
    decorationsSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.Border)

    // marks
    decorationsSchemeBundle.registerColorScheme(
        markActiveScheme,
        ColorSchemeAssociationKind.Mark, *ComponentState.activeStates
    )

    // separators
    val separatorDecorationsScheme = schemes["Night Shade Decorations Separator"]
    decorationsSchemeBundle.registerColorScheme(
        separatorDecorationsScheme,
        ColorSchemeAssociationKind.Separator
    )

    val decorationsBackgroundScheme = schemes["Night Shade Decorations Background"]
    result.registerDecorationAreaSchemeBundle(
        decorationsSchemeBundle, decorationsBackgroundScheme,
        DecorationAreaType.Toolbar, DecorationAreaType.Footer
    )

    val controlPaneBackgroundScheme = schemes["Night Shade Control Pane Background"]
    result.registerDecorationAreaSchemeBundle(
        decorationsSchemeBundle, controlPaneBackgroundScheme,
        DecorationAreaType.ControlPane
    )

    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeScheme,
        enabledScheme, disabledScheme
    )
    headerSchemeBundle.registerAlpha(
        0.6f, ComponentState.DisabledUnselected,
        ComponentState.DisabledSelected
    )
    headerSchemeBundle.registerColorScheme(disabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected)
    headerSchemeBundle.registerColorScheme(disabledSelectedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected)

    // borders
    val headerBorderScheme = schemes["Night Shade Header Border"]
    headerSchemeBundle.registerColorScheme(headerBorderScheme, ColorSchemeAssociationKind.Border)
    // marks
    headerSchemeBundle.registerColorScheme(
        markActiveScheme, ColorSchemeAssociationKind.Mark,
        *ComponentState.activeStates
    )
    headerSchemeBundle.registerColorScheme(
        markActiveScheme,
        ColorSchemeAssociationKind.Mark, ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )
    headerSchemeBundle.registerColorScheme(
        separatorDecorationsScheme,
        ColorSchemeAssociationKind.Separator
    )

    headerSchemeBundle.registerHighlightAlpha(0.7f, ComponentState.RolloverUnselected)
    headerSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.Selected)
    headerSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.RolloverSelected)
    headerSchemeBundle.registerHighlightColorScheme(
        activeScheme,
        ComponentState.RolloverUnselected, ComponentState.Selected, ComponentState.RolloverSelected
    )

    val headerBackgroundScheme = schemes["Night Shade Header Background"]

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerBackgroundScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
    )

    return result
}

fun nightShadeSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
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
        decorationPainter = MatteDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
    )

    // Add overlay painters to paint drop shadows along the bottom
    // edges of toolbars and footers
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.Toolbar, DecorationAreaType.Footer
    )

    // add an overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(BottomLineOverlayPainter(
        composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.5f))
    ), DecorationAreaType.Toolbar)

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(TopBezelOverlayPainter(
        colorSchemeQueryTop = composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.5f)),
        colorSchemeQueryBottom = composite({ it.foregroundColor }, ColorTransforms.alpha(0.125f))
    ), DecorationAreaType.Footer)

    return AuroraSkinDefinition(
        displayName = "Night Shade",
        colors = nightShadeSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
