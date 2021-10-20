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
package org.pushingpixels.aurora.theming

import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colorscheme.SunsetColorScheme
import org.pushingpixels.aurora.theming.colorscheme.composite
import org.pushingpixels.aurora.theming.painter.border.AuroraBorderPainter
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.theming.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.FlatDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun graphiteBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/graphite.colorschemes"
        )
    )

    val activeScheme = schemes["Graphite Active"]
    val selectedDisabledScheme = schemes["Graphite Selected Disabled"]
    val selectedScheme = schemes["Graphite Selected"]
    val disabledScheme = schemes["Graphite Disabled"]

    val enabledScheme = schemes["Graphite Enabled"]
    val backgroundScheme = schemes["Graphite Background"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme,
        disabledScheme
    )

    // border scheme
    val borderScheme = schemes["Graphite Border"]
    val separatorScheme = schemes["Graphite Separator"]
    defaultSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.Border)
    defaultSchemeBundle.registerColorScheme(separatorScheme, ColorSchemeAssociationKind.Separator)

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledUnselected)
    defaultSchemeBundle.registerAlpha(0.65f, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        selectedDisabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.Mark,
        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )

    defaultSchemeBundle.registerColorScheme(
        selectedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.Selected
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, backgroundScheme,
        DecorationAreaType.None
    )

    // highlight fill scheme + custom alpha for rollover unselected state
    defaultSchemeBundle.registerHighlightAlpha(0.9f, ComponentState.Selected)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.RolloverUnselected)
    defaultSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.RolloverSelected)
    defaultSchemeBundle.registerHighlightColorScheme(
        accentBuilder.highlightsAccent!!,
        ComponentState.RolloverUnselected,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Fill,
        ComponentState.Selected, ComponentState.RolloverSelected
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!.shade(0.2f).saturate(0.2f),
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected, ComponentState.PressedUnselected
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Tab,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    defaultSchemeBundle.registerColorScheme(
        borderScheme,
        ColorSchemeAssociationKind.HighlightBorder, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        borderScheme,
        ColorSchemeAssociationKind.Border, *ComponentState.activeStates
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Mark,
        ComponentState.Selected, ComponentState.PressedSelected,
        ComponentState.PressedUnselected, ComponentState.RolloverUnselected,
        ComponentState.RolloverSelected
    )
    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Mark,
        ComponentState.DisabledSelected
    )

    // text highlight scheme

    // text highlight scheme
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.highlightsAccent!!,
        ColorSchemeAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )

    return result
}

private fun graphiteBasePainters(borderPainter: AuroraBorderPainter? = null): AuroraPainters {
    return AuroraPainters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.ultraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.lightColor },
            displayName = "Graphite"
        ),
        borderPainter = borderPainter ?: CompositeBorderPainter(
            displayName = "Graphite",
            outer = DelegateBorderPainter(
                displayName = "Graphite Outer",
                delegate = ClassicBorderPainter(),
                topMask = 0xFFFFFFFF,
                midMask = 0xFFFFFFFF,
                bottomMask = 0xFFFFFFFF
            ) { it.shade(0.4f) },
            inner = DelegateBorderPainter(
                displayName = "Graphite Inner",
                delegate = ClassicBorderPainter(),
                topMask = 0xA0FFFFFF,
                midMask = 0x90FFFFFF,
                bottomMask = 0xA0FFFFFF
            ) { it.tint(0.25f) }),
        decorationPainter = FlatDecorationPainter()
    )
}

private fun graphiteSkinColorsBaseExtensions(bundle: AuroraColorSchemeBundle) {
    // Unlike other accented Graphite skins that use the same highlight appearance on
    // checkboxes and radio buttons as on active renderers, this skin uses a more muted
    // appearance for checkboxes and radio buttons.
    // The following sections remove the accent from those controls and use darker, less
    // vibrant appearance.
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/graphite.colorschemes"
        )
    )

    bundle.registerAlpha(0.65f, ComponentState.DisabledSelected)
    val highlightMarkScheme = schemes["Graphite Highlight Mark"]
    bundle.registerColorScheme(
        highlightMarkScheme,
        ColorSchemeAssociationKind.HighlightMark, *ComponentState.activeStates
    )
    bundle.registerColorScheme(
        highlightMarkScheme,
        ColorSchemeAssociationKind.Mark, ComponentState.RolloverSelected,
        ComponentState.RolloverUnselected
    )

    val selectedScheme = schemes["Graphite Selected"]
    val borderScheme = schemes["Graphite Border"]
    bundle.registerColorScheme(
        selectedScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.Selected
    )
    bundle.registerColorScheme(
        borderScheme, ColorSchemeAssociationKind.Mark,
        ComponentState.Selected
    )

    val selectedDisabledScheme = schemes["Graphite Selected Disabled"]
    val disabledScheme = schemes["Graphite Disabled"]
    bundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    bundle.registerColorScheme(
        selectedDisabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    bundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.Mark,
        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )

    val pressedSelectedScheme = schemes["Graphite Pressed Selected"]
    val pressedUnselectedScheme = schemes["Graphite Pressed Unselected"]
    bundle.registerColorScheme(
        pressedSelectedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected
    )
    bundle.registerColorScheme(
        pressedSelectedScheme,
        ColorSchemeAssociationKind.Mark,
        ComponentState.PressedSelected
    )
    bundle.registerColorScheme(
        pressedUnselectedScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.PressedUnselected
    )
    bundle.registerColorScheme(
        pressedUnselectedScheme,
        ColorSchemeAssociationKind.Mark,
        ComponentState.PressedUnselected
    )
}

fun graphiteSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Highlight")
                .withHighlightsAccent("Graphite Highlight")
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.None),
                noneTransformationOverlay = { bundle ->
                    graphiteSkinColorsBaseExtensions(bundle)
                },
                areaTypes = arrayOf(DecorationAreaType.None)
            )
        },
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteAquaSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Aqua",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Aqua")
                .withHighlightsAccent("Graphite Aqua")
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.None),
                noneTransformationOverlay = { bundle ->
                    // Use disabled color scheme for marks of disabled selected checkboxes and radio buttons
                    // for better contrast
                    val schemes = getColorSchemes(
                        AuroraSkin::class.java.getResourceAsStream(
                            "/org/pushingpixels/aurora/theming/graphite.colorschemes"
                        )
                    )
                    bundle.registerColorScheme(
                        schemes["Graphite Disabled"],
                        ColorSchemeAssociationKind.Mark, ComponentState.DisabledSelected
                    )
                },
                areaTypes = arrayOf(DecorationAreaType.None)
            )
        },
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteChalkSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Chalk",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Highlight")
                .withHighlightsAccent("Graphite Highlight")
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.None),
                noneTransformationOverlay = { bundle ->
                    graphiteSkinColorsBaseExtensions(bundle)

                    val schemes = getColorSchemes(
                        AuroraSkin::class.java.getResourceAsStream(
                            "/org/pushingpixels/aurora/theming/graphite.colorschemes"
                        )
                    )
                    val chalkScheme = schemes["Chalk"]
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.TabBorder,
                        *ComponentState.activeStates
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.Border,
                        ComponentState.Enabled
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.Border,
                        *ComponentState.activeStates
                    )
                    bundle.registerAlpha(
                        0.5f,
                        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.Border,
                        ComponentState.DisabledUnselected, ComponentState.DisabledSelected
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.HighlightBorder,
                        *ComponentState.activeStates
                    )

                    val markScheme = schemes["Graphite Mark"]
                    bundle.registerColorScheme(markScheme, ColorSchemeAssociationKind.Mark)

                    val separatorScheme = schemes["Chalk Separator"]
                    bundle.registerColorScheme(
                        separatorScheme,
                        ColorSchemeAssociationKind.Separator, ComponentState.Enabled
                    )
                },
                areaTypes = arrayOf(DecorationAreaType.None)
            )
        },
        painters = graphiteBasePainters(borderPainter = ClassicBorderPainter()),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteGlassSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Glass",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Highlight")
                .withHighlightsAccent("Graphite Highlight")
        ).also {
            val schemes = getColorSchemes(
                AuroraSkin::class.java.getResourceAsStream(
                    "/org/pushingpixels/aurora/theming/graphite.colorschemes"
                )
            )
            val backgroundScheme = schemes["Graphite Background"]
            it.registerAsDecorationArea(
                backgroundScheme,
                DecorationAreaType.TitlePane, DecorationAreaType.Header
            )
        },
        painters = graphiteBasePainters(borderPainter = ClassicBorderPainter()).also {
            // add two overlay painters to create a bezel line between
            // menu bar and toolbars
            it.addOverlayPainter(
                BottomLineOverlayPainter(colorSchemeQuery = { scheme -> scheme.midColor }),
                DecorationAreaType.Header)
            it.addOverlayPainter(TopLineOverlayPainter(
                composite(
                    { scheme -> scheme.foregroundColor },
                    ColorTransforms.alpha(0.125f)
                )
            ), DecorationAreaType.Toolbar)

        },
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteElectricSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Electric",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Electric")
                .withHighlightsAccent("Graphite Electric")
        ),
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteGoldSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Gold",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Gold")
                .withHighlightsAccent("Graphite Gold")
        ),
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteSiennaSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Sienna",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/theming/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Sienna")
                .withHighlightsAccent("Graphite Sienna")
        ),
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteSunsetSkin(): AuroraSkinDefinition {
    val accentScheme = SunsetColorScheme()

    return AuroraSkinDefinition(
        displayName = "Graphite Sunset",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withActiveControlsAccent(accentScheme)
                .withHighlightsAccent(accentScheme)
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.None),
                noneTransformationOverlay = { bundle ->
                    // Sunset needs tweaks for the enabled / disabled visuals of checkbox and radio button marks
                    // for better contrast

                    // Sunset needs tweaks for the enabled / disabled visuals of checkbox and radio button marks
                    // for better contrast
                    bundle.registerColorScheme(
                        accentScheme,
                        ColorSchemeAssociationKind.Mark,
                        ComponentState.Selected
                    )
                    bundle.registerAlpha(0.7f, ComponentState.DisabledSelected)
                    bundle.registerColorScheme(
                        accentScheme.shade(0.4f),
                        ColorSchemeAssociationKind.Mark,
                        ComponentState.DisabledSelected
                    )
                },
                areaTypes = arrayOf(DecorationAreaType.None)
            )
        },
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

