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
        DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
    )

    return result
}

private fun nebulaBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
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
    )

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.darkColor }, ColorTransforms.alpha(0.625f))
        ),
        DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
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
            painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.TOOLBAR)
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

