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
import org.pushingpixels.aurora.skin.colorscheme.*
import org.pushingpixels.aurora.skin.painter.border.FlatBorderPainter
import org.pushingpixels.aurora.skin.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.skin.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.skin.painter.fill.SubduedFillPainter
import org.pushingpixels.aurora.skin.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.skin.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.skin.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.skin.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.skin.utils.getColorSchemes

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
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        rolloverSelectedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverSelected
    )
    defaultSchemeBundle.registerColorScheme(
        pressedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected, ComponentState.PressedUnselected
    )

    defaultSchemeBundle.registerColorScheme(
        rolloverUnselectedScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Selected
    )

    defaultSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.RolloverUnselected)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.Selected)
    defaultSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.RolloverSelected)
    defaultSchemeBundle.registerHighlightColorScheme(
        pressedScheme, ComponentState.RolloverUnselected,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    // for progress bars
    val determinateState = ComponentState(
        "determinate", arrayOf(
            ComponentStateFacet.Enable,
            ComponentStateFacet.Determinate
        ), null
    )
    val indeterminateState =
        ComponentState("indeterminate", arrayOf(ComponentStateFacet.Enable), arrayOf(ComponentStateFacet.Determinate))
    val determinateScheme = schemes["Nebula Determinate"]
    val determinateBorderScheme = schemes["Nebula Determinate Border"]
    defaultSchemeBundle.registerColorScheme(
        determinateScheme,
        ColorSchemeAssociationKind.Fill,
        determinateState, indeterminateState
    )
    defaultSchemeBundle.registerColorScheme(
        determinateBorderScheme,
        ColorSchemeAssociationKind.Border, determinateState,
        indeterminateState
    )

    val determinateDisabledState = ComponentState(
        "determinate disabled", arrayOf(ComponentStateFacet.Determinate), arrayOf(ComponentStateFacet.Enable)
    )
    val indeterminateDisabledState = ComponentState(
        "indeterminate disabled", null, arrayOf(
            ComponentStateFacet.Enable,
            ComponentStateFacet.Determinate
        )
    )
    val determinateDisabledScheme = schemes["Nebula Determinate Disabled"]
    val determinateDisabledBorderScheme = schemes["Nebula Determinate Disabled Border"]
    defaultSchemeBundle.registerColorScheme(
        determinateDisabledScheme,
        ColorSchemeAssociationKind.Fill,
        determinateDisabledState, indeterminateDisabledState
    )
    defaultSchemeBundle.registerColorScheme(
        determinateDisabledBorderScheme,
        ColorSchemeAssociationKind.Border, determinateDisabledState,
        indeterminateDisabledState
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, DecorationAreaType.None
    )

    result.registerAsDecorationArea(
        backgroundColorScheme = schemes["Nebula Decorations"],
        noneTransformationOverlay = { bundle ->
            bundle.registerColorScheme(
                schemes["Nebula Decorations Separator"],
                ColorSchemeAssociationKind.Separator
            )
        },
        areaTypes = arrayOf(DecorationAreaType.Footer, DecorationAreaType.ControlPane)
    )

    result.registerAsDecorationArea(
        accentBuilder.windowChromeAccent!!,
        DecorationAreaType.TitlePane, DecorationAreaType.Header
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
        DecorationAreaType.Toolbar
    )

    // add an overlay painter to paint separator lines along the bottom
    // edges of title panes and menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.darkColor }, ColorTransforms.alpha(0.625f))
        ),
        DecorationAreaType.TitlePane, DecorationAreaType.Header
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
                DecorationAreaType.Toolbar
            )
        },
        painters = nebulaBasePainters().also { painters ->
            // Clear the top shadow painter on the toolbars and add combined
            // separator + drop shadow along the toolbar bottom
            painters.clearOverlayPainters(DecorationAreaType.Toolbar)
            painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
            painters.addOverlayPainter(
                BottomLineOverlayPainter(
                    composite({ it.darkColor }, ColorTransforms.alpha(0.625f))
                ), DecorationAreaType.Toolbar
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

