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

import org.pushingpixels.aurora.skin.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.skin.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.skin.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.skin.colorscheme.SteelBlueColorScheme
import org.pushingpixels.aurora.skin.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.skin.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.skin.painter.fill.GlassFillPainter
import org.pushingpixels.aurora.skin.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.skin.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.skin.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.skin.utils.getColorSchemes

private fun moderateSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes"
        )
    )
    val activeScheme = SteelBlueColorScheme()
    val enabledScheme = MetallicColorScheme()
    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, kitchenSinkSchemes["Gray Disabled"]
    )
    val highlightColorScheme = kitchenSinkSchemes["Moderate Highlight"]
    defaultSchemeBundle.registerHighlightColorScheme(highlightColorScheme)

    result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.None)

    val headerSchemeBundle = AuroraColorSchemeBundle(
        activeScheme.saturate(0.2f), activeScheme, kitchenSinkSchemes["Gray Disabled"]
    )
    result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    result.registerAsDecorationArea(
        kitchenSinkSchemes["LightGray Control Pane Background"],
        DecorationAreaType.ControlPane
    )

    return result
}

fun moderateSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = GlassFillPainter(),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = MatteDecorationPainter()
    )
    // add an overlay painter to paint a drop shadow along the top
    // edge of toolbars
    painters.addOverlayPainter(TopShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
    // add an overlay painter to paint separator lines along the bottom
    // edges of menu bars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.midColor }),
        DecorationAreaType.Header
    )

    return AuroraSkinDefinition(
        displayName = "Moderate",
        colors = moderateSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

