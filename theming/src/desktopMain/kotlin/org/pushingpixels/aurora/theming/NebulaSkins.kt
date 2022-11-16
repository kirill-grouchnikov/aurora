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

import org.pushingpixels.aurora.theming.colorscheme.*
import org.pushingpixels.aurora.theming.painter.border.FlatBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SubduedFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun nebulaBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/nebula.colorschemes"
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
    val determinateScheme = schemes["Nebula Determinate"]
    val determinateBorderScheme = schemes["Nebula Determinate Border"]
    defaultSchemeBundle.registerColorScheme(
        determinateScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.Determinate, ComponentState.Indeterminate
    )
    defaultSchemeBundle.registerColorScheme(
        determinateBorderScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.Determinate, ComponentState.Indeterminate
    )

    val determinateDisabledScheme = schemes["Nebula Determinate Disabled"]
    val determinateDisabledBorderScheme = schemes["Nebula Determinate Disabled Border"]
    defaultSchemeBundle.registerColorScheme(
        determinateDisabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledDeterminate, ComponentState.DisabledIndeterminate
    )
    defaultSchemeBundle.registerColorScheme(
        determinateDisabledBorderScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.DisabledDeterminate, ComponentState.DisabledIndeterminate
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
        fillPainter = SpecularRectangularFillPainter(SubduedFillPainter(), 0.3f),
        borderPainter = FlatBorderPainter(),
        decorationPainter = MarbleNoiseDecorationPainter(
            textureAlpha = 0.2f,
            baseDecorationPainter = ArcDecorationPainter()
        ),
        highlightFillPainter = ClassicFillPainter()
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
                .withAccentResource("/org/pushingpixels/aurora/theming/nebula.colorschemes")
                .withWindowChromeAccent("Nebula Decorations")
        ),
        painters = nebulaBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun nebulaAmethystSkin(): AuroraSkinDefinition {
    val accentBuilder = AccentBuilder()
        .withAccentResource("/org/pushingpixels/aurora/theming/nebula.colorschemes")
        .withWindowChromeAccent(PurpleColorScheme())

    return AuroraSkinDefinition(
        displayName = "Nebula Amethyst",
        colors = nebulaBaseSkinColors(
            AccentBuilder().withWindowChromeAccent(PurpleColorScheme())
        ).also {
            val windowChromeAccent = accentBuilder.windowChromeAccent!!
            val windowChromeDisabled =
                accentBuilder.getColorScheme("Nebula Amethyst Title Disabled")!!
            val toolbarBundle = AuroraColorSchemeBundle(
                windowChromeAccent.saturate(0.1f), windowChromeAccent, windowChromeDisabled
            )
            toolbarBundle.registerAlpha(
                0.8f, ComponentState.DisabledSelected,
                ComponentState.DisabledUnselected
            )
            toolbarBundle.registerColorScheme(
                windowChromeAccent.saturate(0.08f),
                ColorSchemeAssociationKind.Separator
            )
            it.registerDecorationAreaSchemeBundle(toolbarBundle, DecorationAreaType.Toolbar)
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

