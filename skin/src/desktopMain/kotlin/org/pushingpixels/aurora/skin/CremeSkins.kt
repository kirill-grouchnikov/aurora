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
import org.pushingpixels.aurora.colorscheme.CremeColorScheme
import org.pushingpixels.aurora.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.painter.border.CompositeBorderPainter
import org.pushingpixels.aurora.painter.border.DelegateBorderPainter
import org.pushingpixels.aurora.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.painter.fill.MatteFillPainter
import org.pushingpixels.aurora.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.utils.getColorSchemes

private fun cremeBaseSkinColors(accentBuilder: AccentBuilder): AuroraSkinColors {
    val result = AuroraSkinColors()
    val kitchenSinkSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes"
        )
    )

    val enabledScheme = CremeColorScheme()
    val disabledScheme = kitchenSinkSchemes["Creme Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        accentBuilder.activeControlsAccent!!, enabledScheme, disabledScheme
    )
    defaultSchemeBundle.registerHighlightColorScheme(accentBuilder.highlightsAccent!!)
    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.None
    )

    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header, DecorationAreaType.Footer,
        DecorationAreaType.ControlPane, DecorationAreaType.Toolbar
    )

    return result
}

private fun cremeBasePainters(): AuroraPainters {
    val painters = AuroraPainters(
        fillPainter = MatteFillPainter(),
        borderPainter = CompositeBorderPainter(
            displayName = "Creme",
            outer = ClassicBorderPainter(),
            inner = DelegateBorderPainter(
                displayName = "Creme Inner", delegate = ClassicBorderPainter()
            ) { it.tint(0.9f) }),
        decorationPainter = ArcDecorationPainter()
    )

    // Add overlay painters to paint drop shadows along the bottom edges of toolbars
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(40), DecorationAreaType.Toolbar)

    // add an overlay painter to paint a dark line along the bottom edge of toolbars
    painters.addOverlayPainter(BottomLineOverlayPainter(colorSchemeQuery = { it.midColor }),
        DecorationAreaType.Toolbar)

    return painters
}

fun cremeSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Creme",
        colors = cremeBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes")
                .withActiveControlsAccent("Creme Active")
                .withHighlightsAccent("Creme Highlights")
        ),
        painters = cremeBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun cremeCoffeeSkin(): AuroraSkinDefinition {
    return AuroraSkinDefinition(
        displayName = "Creme Coffee",
        colors = cremeBaseSkinColors(
            AccentBuilder()
                .withAccentResource("/org/pushingpixels/aurora/skins/kitchen-sink.colorschemes")
                .withActiveControlsAccent("Coffee Active")
                .withHighlightsAccent("Coffee Highlights")
        ),
        painters = cremeBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}
