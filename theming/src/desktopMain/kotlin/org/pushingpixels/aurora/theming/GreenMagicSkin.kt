/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.theming.colorscheme.composite
import org.pushingpixels.aurora.theming.painter.border.ClassicBorderPainter
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomShadowOverlayPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes

private fun greenMagicSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()
    val schemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/theming/greenmagic.colorschemes"
        )
    )

    val activeScheme = schemes["Green Magic Active"]
    val enabledScheme = schemes["Green Magic Enabled"]
    val disabledScheme = schemes["Green Magic Disabled"]

    val defaultSchemeBundle = AuroraColorSchemeBundle(
        activeScheme, enabledScheme, disabledScheme
    )
    result.registerDecorationAreaSchemeBundle(
        defaultSchemeBundle,
        DecorationAreaType.None
    )

    // mark title panes and headers as decoration areas
    result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
    )

    val footerFillScheme = schemes["Green Magic Footer Fill"]
    result.registerAsDecorationArea(footerFillScheme, DecorationAreaType.Footer)

    return result
}

fun greenMagicSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.ultraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.lightColor },
            displayName = "Green Magic"
        ),
        borderPainter = ClassicBorderPainter(),
        decorationPainter = ArcDecorationPainter(),
        highlightFillPainter = ClassicFillPainter()
    )

    // Add overlay painters to paint drop shadow and a dark line along the bottom
    // edges of headers
    painters.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Header)
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.darkColor }, ColorTransforms.alpha(0.5f))
        ),
        DecorationAreaType.Header
    )

    return AuroraSkinDefinition(
        displayName = "Green Magic",
        colors = greenMagicSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
