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
import org.pushingpixels.aurora.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun sentinelSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/sentinel.colorschemes"
        )
    )

    val activeScheme = schemes["Sentinel Active"]
    val enabledScheme = schemes["Sentinel Enabled"]
    val disabledScheme = schemes["Sentinel Disabled"]
    val disabledSelectedScheme = schemes["Sentinel Disabled Selected"]

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
    val borderScheme = schemes["Sentinel Border"]
    defaultSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.BORDER)

    // marks
    val markScheme = schemes["Sentinel Mark"]
    defaultSchemeBundle.registerColorScheme(markScheme, ColorSchemeAssociationKind.MARK)

    // separators
    val separatorScheme = schemes["Sentinel Separator"]
    defaultSchemeBundle.registerColorScheme(separatorScheme, ColorSchemeAssociationKind.SEPARATOR)

    // tab borders
    defaultSchemeBundle.registerColorScheme(
        schemes["Sentinel Tab Border"],
        ColorSchemeAssociationKind.TAB_BORDER, *ComponentState.activeStates
    )

    // highlights
    val highlightScheme = schemes["Sentinel Highlight"]
    defaultSchemeBundle.registerHighlightColorScheme(highlightScheme)

    val backgroundScheme = schemes["Sentinel Background"]

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, backgroundScheme, DecorationAreaType.NONE)

    val activeDecorationsScheme = schemes["Sentinel Decorations Active"]
    val enabledDecorationsScheme = schemes["Sentinel Decorations Enabled"]
    val decorationsSchemeBundle = AuroraColorSchemeBundle(
        activeDecorationsScheme, enabledDecorationsScheme, enabledDecorationsScheme
    )
    decorationsSchemeBundle.registerAlpha(0.4f, ComponentState.DISABLED_UNSELECTED)
    decorationsSchemeBundle.registerColorScheme(enabledDecorationsScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED)

    // borders
    val borderDecorationsScheme = schemes["Sentinel Decorations Border"]
    decorationsSchemeBundle.registerColorScheme(borderDecorationsScheme, ColorSchemeAssociationKind.BORDER)

    // marks
    val markDecorationsScheme = schemes["Sentinel Decorations Mark"]
    decorationsSchemeBundle.registerColorScheme(markDecorationsScheme, ColorSchemeAssociationKind.MARK)

    // separators
    val separatorDecorationsScheme = schemes["Sentinel Decorations Separator"]
    decorationsSchemeBundle.registerColorScheme(
        separatorDecorationsScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )

    val decorationsBackgroundScheme = schemes["Sentinel Decorations Background"]
    result.registerDecorationAreaSchemeBundle(
        decorationsSchemeBundle, decorationsBackgroundScheme,
        DecorationAreaType.TOOLBAR, DecorationAreaType.FOOTER
    )

    val activeControlPaneScheme = schemes["Sentinel Control Pane Active"]
    val enabledControlPaneScheme = schemes["Sentinel Control Pane Enabled"]
    val controlPaneSchemeBundle = AuroraColorSchemeBundle(
        activeControlPaneScheme, enabledControlPaneScheme, enabledControlPaneScheme
    )
    controlPaneSchemeBundle.registerAlpha(0.4f, ComponentState.DISABLED_UNSELECTED)
    controlPaneSchemeBundle.registerColorScheme(
        enabledControlPaneScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )

    // borders
    val borderControlPaneScheme = schemes["Sentinel Control Pane Border"]
    controlPaneSchemeBundle.registerColorScheme(
        borderControlPaneScheme,
        ColorSchemeAssociationKind.BORDER
    )

    // marks
    val markControlPaneScheme = schemes["Sentinel Control Pane Mark"]
    controlPaneSchemeBundle.registerColorScheme(
        markControlPaneScheme,
        ColorSchemeAssociationKind.MARK, *ComponentState.activeStates
    )

    // separators
    val separatorControlPaneScheme = schemes["Sentinel Control Pane Separator"]
    controlPaneSchemeBundle.registerColorScheme(
        separatorControlPaneScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )
    val backgroundControlPaneScheme = schemes["Sentinel Control Pane Background"]
    result.registerDecorationAreaSchemeBundle(
        controlPaneSchemeBundle, backgroundControlPaneScheme,
        DecorationAreaType.CONTROL_PANE
    )

    val activeHeaderScheme = schemes["Sentinel Header Active"]
    val enabledHeaderScheme = schemes["Sentinel Header Enabled"]
    val disabledHeaderScheme = schemes["Sentinel Header Disabled"]
    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeHeaderScheme,
        enabledHeaderScheme, disabledHeaderScheme
    )
    headerSchemeBundle.registerAlpha(0.95f, ComponentState.DISABLED_UNSELECTED)
    headerSchemeBundle.registerColorScheme(disabledHeaderScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED)

    // borders
    val headerBorderScheme = schemes["Sentinel Header Border"]
    headerSchemeBundle.registerColorScheme(headerBorderScheme, ColorSchemeAssociationKind.BORDER)
    // marks
    val headerMarkScheme = schemes["Sentinel Header Mark"]
    headerSchemeBundle.registerColorScheme(headerMarkScheme, ColorSchemeAssociationKind.MARK)
    headerSchemeBundle.registerColorScheme(
        disabledHeaderScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )
    // separators
    val separatorHeaderScheme = schemes["Sentinel Header Separator"]
    headerSchemeBundle.registerColorScheme(
        separatorHeaderScheme,
        ColorSchemeAssociationKind.SEPARATOR
    )

    headerSchemeBundle.registerHighlightAlpha(0.85f, ComponentState.ROLLOVER_UNSELECTED)
    headerSchemeBundle.registerHighlightAlpha(0.9f, ComponentState.SELECTED)
    headerSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.ROLLOVER_SELECTED)
    headerSchemeBundle.registerHighlightColorScheme(highlightScheme)

    val headerBackgroundScheme = schemes["Sentinel Header Background"]

    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerBackgroundScheme,
        DecorationAreaType.TITLE_PANE,
        DecorationAreaType.HEADER
    )

    return result
}

fun sentinelSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = ClassicFillPainter(),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = MatteDecorationPainter()
    )

    // Add overlay painters to paint drop shadow and a dark line along the bottom
    // edges of toolbars
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.TOOLBAR)
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.1f))
        ),
        DecorationAreaType.TOOLBAR
    )

    // Add overlay painters to paint drop shadow and a dark line along the top
    // edges of footers
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(15), DecorationAreaType.FOOTER)
    painters.addOverlayPainter(
        TopLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(-0.1f))
        ),
        DecorationAreaType.FOOTER
    )

    return AuroraSkinDefinition(
        displayName = "Sentinel",
        colors = sentinelSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
