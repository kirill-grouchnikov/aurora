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
package org.pushingpixels.aurora.demo.titlepane.mail

import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colorscheme.SunGlareColorScheme
import org.pushingpixels.aurora.theming.colorscheme.TerracottaColorScheme
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.BrushedMetalDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun visorSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/demo/visor.colorschemes"
        )
    )

    val activeScheme = schemes["Visor Active"]
    val enabledScheme = schemes["Visor Enabled"]
    val disabledScheme = schemes["Visor Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    defaultSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    defaultSchemeBundle.registerColorScheme(
        activeScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.None)

    val activeDestinationsScheme = schemes["Visor Active Destinations"]
    val enabledDestinationsScheme = schemes["Visor Enabled Destinations"]
    val destinationsSchemeBundle = AuroraColorSchemeBundle(
        activeDestinationsScheme, enabledDestinationsScheme, disabledScheme
    )
    destinationsSchemeBundle.registerAlpha(0.7f, ComponentState.DisabledUnselected)
    destinationsSchemeBundle.registerColorScheme(
        enabledDestinationsScheme,
        ColorSchemeAssociationKind.Fill, ComponentState.DisabledUnselected
    )

    // use SunGlare for destinations highlights
    val destinationsHighlight = SunGlareColorScheme()
    destinationsSchemeBundle.registerAlpha(0.75f, ComponentState.RolloverUnselected)
    destinationsSchemeBundle.registerHighlightColorScheme(
        destinationsHighlight,
        ComponentState.RolloverUnselected
    )
    destinationsSchemeBundle.registerAlpha(0.9f, ComponentState.Selected)
    destinationsSchemeBundle.registerHighlightColorScheme(
        destinationsHighlight,
        ComponentState.Selected
    )
    destinationsSchemeBundle.registerAlpha(1.0f, ComponentState.RolloverSelected)
    destinationsSchemeBundle.registerHighlightColorScheme(
        destinationsHighlight,
        ComponentState.RolloverSelected
    )

    // use Terracotta for borders of destinations highlights
    destinationsSchemeBundle.registerColorScheme(
        TerracottaColorScheme(),
        ColorSchemeAssociationKind.HighlightBorder,
        *ComponentState.activeStates
    )

    result.registerDecorationAreaSchemeBundle(destinationsSchemeBundle, VisorDecorations.Destinations)

    val threadsSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )

    threadsSchemeBundle.registerAlpha(0.5f, ComponentState.DisabledSelected)
    threadsSchemeBundle.registerColorScheme(
        activeScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    // Configure white-on-dark-blue highlights for the threads view
    val threadsHighlight = schemes["Visor Threads Highlight"]
    val threadsHighlightLight = schemes["Visor Threads Highlight Light"]
    threadsSchemeBundle.registerAlpha(1.0f, ComponentState.RolloverUnselected)
    threadsSchemeBundle.registerHighlightColorScheme(
        threadsHighlightLight,
        ComponentState.RolloverUnselected
    )
    threadsSchemeBundle.registerAlpha(0.9f, ComponentState.Selected)
    threadsSchemeBundle.registerHighlightColorScheme(
        threadsHighlight,
        ComponentState.Selected
    )
    threadsSchemeBundle.registerAlpha(1.0f, ComponentState.RolloverSelected)
    threadsSchemeBundle.registerHighlightColorScheme(
        threadsHighlight,
        ComponentState.RolloverSelected
    )

    result.registerDecorationAreaSchemeBundle(threadsSchemeBundle, VisorDecorations.Threads)

    return result
}

fun visorSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = ClassicFillPainter(),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = BrushedMetalDecorationPainter(),
        highlightFillPainter = FractionBasedFillPainter(
            0.0f to { it.extraLightColor },
            1.0f to { it.extraLightColor },
            displayName = "Visor Highlight"
        )
    )

    return AuroraSkinDefinition(
        displayName = "Visor",
        colors = visorSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
