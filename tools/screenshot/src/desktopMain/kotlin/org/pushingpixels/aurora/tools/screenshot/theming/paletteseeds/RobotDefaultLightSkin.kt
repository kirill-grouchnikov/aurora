/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.tools.screenshot.theming.paletteseeds

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private val MutedLightSeed: Hct = Hct.fromInt(java.awt.Color(208, 212, 216).rgb)
private val NeutralLightSeed: Hct = Hct.fromInt(java.awt.Color(252, 249, 248).rgb)

fun robotDefaultLightSkin(accentColor: Color, name: String) : AuroraSkinDefinition {
    val buttonShaper = ClassicButtonShaper()
    val painters = AuroraPainters(
        decorationPainter = MarbleNoiseDecorationPainter(
            colorQuery1 = { it.containerSurface },
            colorQuery2 = { it.containerSurfaceHighest },
            textureAlpha = 0.6f,
            baseDecorationPainter = ArcDecorationPainter()
        ),
        surfacePainter = SpecularRectangularSurfacePainter(ClassicSurfacePainter(), 1.0f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter()
    )
    painters.addOverlayPainter(BottomLineOverlayPainter(colorTokensQuery = { it.containerOutline }),
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header)

    val skinColors = AuroraSkinColors()

    val activeContainerColorTokens = getContainerTokens(
        seed = Hct.fromInt(accentColor.toArgb()),
        containerConfiguration = ContainerConfiguration.defaultLight()
    )
    val defaultTokensBundle = ContainerColorTokensBundle(
        activeContainerTokens = activeContainerColorTokens,
        mutedContainerTokens = getContainerTokens(
            seed = MutedLightSeed,
            containerConfiguration = ContainerConfiguration.defaultLight()
        ),
        neutralContainerTokens = getContainerTokens(
            seed = NeutralLightSeed,
            containerConfiguration = ContainerConfiguration.defaultLight()
        ),
        isSystemDark = false
    )
    skinColors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    skinColors.registerAsDecorationArea(activeContainerColorTokens,
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return AuroraSkinDefinition(
        displayName = name,
        colors = skinColors,
        painters = painters,
        buttonShaper = buttonShaper,
    )
}
