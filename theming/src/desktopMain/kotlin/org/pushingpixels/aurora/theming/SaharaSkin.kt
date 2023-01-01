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
package org.pushingpixels.aurora.theming

import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colorscheme.DesertSandColorScheme
import org.pushingpixels.aurora.theming.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.SpecularRectangularFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun saharaSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/kitchen-sink.colorschemes"
        )
    )
    val activeScheme = DesertSandColorScheme()
    val enabledScheme = MetallicColorScheme()
    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, kitchenSinkSchemes["Gray Disabled"]
    )
    defaultSchemeBundle.registerHighlightColorScheme(kitchenSinkSchemes["Sahara Highlight"])
    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.None
    )

    result.registerAsDecorationArea(
        activeScheme,
        DecorationAreaType.TitlePane, DecorationAreaType.Header
    )

    return result
}

fun saharaSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = SpecularRectangularFillPainter(ClassicFillPainter(), 0.6f),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = MatteDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
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
        displayName = "Sahara",
        colors = saharaSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

