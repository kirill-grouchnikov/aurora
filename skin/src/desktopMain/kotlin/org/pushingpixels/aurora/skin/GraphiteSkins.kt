/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.skin

import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.colorscheme.SunsetColorScheme
import org.pushingpixels.aurora.colorscheme.composite
import org.pushingpixels.aurora.painter.border.AuroraBorderPainter
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.decoration.FlatDecorationPainter
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun graphiteBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/graphite.colorschemes"
        )
    )

    val activeScheme = schemes.get("Graphite Active")
    val selectedDisabledScheme = schemes.get("Graphite Selected Disabled")
    val selectedScheme = schemes.get("Graphite Selected")
    val disabledScheme = schemes.get("Graphite Disabled")

    val enabledScheme = schemes.get("Graphite Enabled")
    val backgroundScheme = schemes.get("Graphite Background")

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme,
        disabledScheme
    )

    // border scheme
    val borderScheme = schemes.get("Graphite Border")
    val separatorScheme = schemes.get("Graphite Separator")
    defaultSchemeBundle.registerColorScheme(borderScheme, ColorSchemeAssociationKind.BORDER)
    defaultSchemeBundle.registerColorScheme(separatorScheme, ColorSchemeAssociationKind.SEPARATOR)

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_UNSELECTED)
    defaultSchemeBundle.registerAlpha(0.65f, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        selectedDisabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        selectedScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED
    )

    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle, backgroundScheme,
        DecorationAreaType.NONE
    )

    // highlight fill scheme + custom alpha for rollover unselected state
    defaultSchemeBundle.registerHighlightAlpha(0.9f, ComponentState.SELECTED)
    defaultSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.ROLLOVER_UNSELECTED)
    defaultSchemeBundle.registerHighlightAlpha(1.0f, ComponentState.ROLLOVER_SELECTED)
    defaultSchemeBundle.registerHighlightColorScheme(
        accentBuilder.highlightsAccent!!,
        ComponentState.ROLLOVER_UNSELECTED,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!.shade(0.2f).saturate(0.2f),
        ColorSchemeAssociationKind.FILL,
        ComponentState.PRESSED_SELECTED, ComponentState.PRESSED_UNSELECTED
    )
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.TAB,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        borderScheme,
        ColorSchemeAssociationKind.HIGHLIGHT_BORDER, *ComponentState.activeStates
    )
    defaultSchemeBundle.registerColorScheme(
        borderScheme,
        ColorSchemeAssociationKind.BORDER, *ComponentState.activeStates
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.MARK,
        ComponentState.SELECTED
    )
    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DISABLED_SELECTED)
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.MARK,
        ComponentState.DISABLED_SELECTED
    )

    // text highlight scheme

    // text highlight scheme
    defaultSchemeBundle.registerColorScheme(
        accentBuilder.highlightsAccent!!,
        ColorSchemeAssociationKind.HIGHLIGHT_TEXT,
        ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED
    )

    defaultSchemeBundle.registerColorScheme(
        accentBuilder.activeControlsAccent!!,
        ColorSchemeAssociationKind.FILL,
        ComponentState.ROLLOVER_UNSELECTED
    )

    return result
}

private fun graphiteBasePainters(borderPainter: AuroraBorderPainter? = null): Painters {
    return Painters(
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
            "/org/pushingpixels/aurora/skins/graphite.colorschemes"
        )
    )

    bundle.registerAlpha(0.65f, ComponentState.DISABLED_SELECTED)
    val highlightMarkScheme = schemes["Graphite Highlight Mark"]
    bundle.registerColorScheme(
        highlightMarkScheme,
        ColorSchemeAssociationKind.HIGHLIGHT_MARK, *ComponentState.activeStates
    )
    bundle.registerColorScheme(
        highlightMarkScheme,
        ColorSchemeAssociationKind.MARK, ComponentState.ROLLOVER_SELECTED,
        ComponentState.ROLLOVER_UNSELECTED
    )

    val selectedScheme = schemes["Graphite Selected"]
    val borderScheme = schemes["Graphite Border"]
    bundle.registerColorScheme(
        selectedScheme, ColorSchemeAssociationKind.FILL,
        ComponentState.SELECTED
    )
    bundle.registerColorScheme(
        borderScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.SELECTED
    )

    val selectedDisabledScheme = schemes["Graphite Selected Disabled"]
    val disabledScheme = schemes["Graphite Disabled"]
    bundle.registerColorScheme(
        disabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_UNSELECTED
    )
    bundle.registerColorScheme(
        selectedDisabledScheme,
        ColorSchemeAssociationKind.FILL,
        ComponentState.DISABLED_SELECTED
    )
    bundle.registerColorScheme(
        disabledScheme, ColorSchemeAssociationKind.MARK,
        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
    )
}

fun graphiteSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Highlight")
                .withHighlightsAccent("Graphite Highlight")
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.NONE),
                noneTransformationOverlay = { bundle ->
                    graphiteSkinColorsBaseExtensions(bundle)
                },
                areaTypes = arrayOf(DecorationAreaType.NONE)
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
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Aqua")
                .withHighlightsAccent("Graphite Aqua")
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.NONE),
                noneTransformationOverlay = { bundle ->
                    // Use disabled color scheme for marks of disabled selected checkboxes and radio buttons
                    // for better contrast
                    val schemes = getColorSchemes(
                        AuroraSkin::class.java.getResourceAsStream(
                            "/org/pushingpixels/aurora/skins/graphite.colorschemes"
                        )
                    )
                    bundle.registerColorScheme(
                        schemes["Graphite Disabled"],
                        ColorSchemeAssociationKind.MARK, ComponentState.DISABLED_SELECTED
                    )
                },
                areaTypes = arrayOf(DecorationAreaType.NONE)
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
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Highlight")
                .withHighlightsAccent("Graphite Highlight")
        ).also {
            it.registerAsDecorationArea(
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.NONE),
                noneTransformationOverlay = { bundle ->
                    graphiteSkinColorsBaseExtensions(bundle)

                    val schemes = getColorSchemes(
                        AuroraSkin::class.java.getResourceAsStream(
                            "/org/pushingpixels/aurora/skins/graphite.colorschemes"
                        )
                    )
                    val chalkScheme = schemes["Chalk"]
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.TAB_BORDER,
                        *ComponentState.activeStates
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.BORDER,
                        ComponentState.ENABLED
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.BORDER,
                        *ComponentState.activeStates
                    )
                    bundle.registerAlpha(
                        0.5f,
                        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.BORDER,
                        ComponentState.DISABLED_UNSELECTED, ComponentState.DISABLED_SELECTED
                    )
                    bundle.registerColorScheme(
                        chalkScheme,
                        ColorSchemeAssociationKind.HIGHLIGHT_BORDER,
                        *ComponentState.activeStates
                    )

                    val markScheme = schemes["Graphite Mark"]
                    bundle.registerColorScheme(markScheme, ColorSchemeAssociationKind.MARK)

                    val separatorScheme = schemes["Chalk Separator"]
                    bundle.registerColorScheme(
                        separatorScheme,
                        ColorSchemeAssociationKind.SEPARATOR, ComponentState.ENABLED
                    )
                },
                areaTypes = arrayOf(DecorationAreaType.NONE)
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
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
                .withActiveControlsAccent("Graphite Highlight")
                .withHighlightsAccent("Graphite Highlight")
        ).also {
            val schemes = getColorSchemes(
                AuroraSkin::class.java.getResourceAsStream(
                    "/org/pushingpixels/aurora/skins/graphite.colorschemes"
                )
            )
            val backgroundScheme = schemes["Graphite Background"]
            it.registerAsDecorationArea(
                backgroundScheme,
                DecorationAreaType.TITLE_PANE, DecorationAreaType.HEADER
            )
        },
        painters = graphiteBasePainters(borderPainter = ClassicBorderPainter()).also {
            // add two overlay painters to create a bezel line between
            // menu bar and toolbars
            it.addOverlayPainter(
                BottomLineOverlayPainter(colorSchemeQuery = { scheme -> scheme.midColor }),
                DecorationAreaType.HEADER)
            it.addOverlayPainter(TopLineOverlayPainter(
                composite(
                    { scheme -> scheme.foregroundColor },
                    ColorTransforms.alpha(0.125f)
                )
            ), DecorationAreaType.TOOLBAR)

        },
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteElectricSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Graphite Electric",
        colors = graphiteBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
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
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
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
                .withAccentResource("/org/pushingpixels/aurora/skins/graphite.colorschemes")
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
                backgroundColorScheme = it.getBackgroundColorScheme(DecorationAreaType.NONE),
                noneTransformationOverlay = { bundle ->
                    // Sunset needs tweaks for the enabled / disabled visuals of checkbox and radio button marks
                    // for better contrast

                    // Sunset needs tweaks for the enabled / disabled visuals of checkbox and radio button marks
                    // for better contrast
                    bundle.registerColorScheme(
                        accentScheme,
                        ColorSchemeAssociationKind.MARK,
                        ComponentState.SELECTED
                    )
                    bundle.registerAlpha(0.7f, ComponentState.DISABLED_SELECTED)
                    bundle.registerColorScheme(
                        accentScheme.shade(0.4f),
                        ColorSchemeAssociationKind.MARK,
                        ComponentState.DISABLED_SELECTED
                    )
                },
                areaTypes = arrayOf(DecorationAreaType.NONE)
            )
        },
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

