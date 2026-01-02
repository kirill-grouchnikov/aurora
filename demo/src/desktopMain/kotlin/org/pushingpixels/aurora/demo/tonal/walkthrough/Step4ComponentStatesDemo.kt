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
package org.pushingpixels.aurora.demo.tonal.walkthrough;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.decoration.ArcDecorationPainter
import org.pushingpixels.aurora.theming.painter.decoration.MarbleNoiseDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.GlassSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct
import org.pushingpixels.ephemeral.chroma.palettes.TonalPalette

private fun sampleLightSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val activePalette = TonalPalette.fromHct(Hct.from(300.0, 40.0, 40.0))
    val mutedPalette = TonalPalette.fromHct(Hct.from(300.0, 18.0, 40.0))
    val neutralPalette = TonalPalette.fromHct(Hct.from(300.0, 8.0, 40.0))

    val sampleLightDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            /* seed */ activePalette.getHct(80.0),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            /* seed */ mutedPalette.getHct(85.0),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            /* seed */ neutralPalette.getHct(95.0),
            /* containerConfiguration */ ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    result.registerDecorationAreaTokensBundle(sampleLightDefaultBundle, DecorationAreaType.None)

    result.registerAsDecorationArea(
        neutralContainerTokens = sampleLightDefaultBundle.getActiveContainerTokens(),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return result
}

fun sampleLightSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        decorationPainter = MarbleNoiseDecorationPainter(
            colorQuery1 = { it.containerSurface },
            colorQuery2 = { it.containerSurfaceHighest },
            textureAlpha = 0.3f,
            baseDecorationPainter = ArcDecorationPainter()),
        surfacePainter = SpecularRectangularSurfacePainter(GlassSurfacePainter(), 1.0f),
        outlinePainter = FlatOutlinePainter(),
        highlightSurfacePainter = ClassicSurfacePainter(),
        highlightOutlinePainter = FlatOutlinePainter()
    )

    return AuroraSkinDefinition(
        displayName = "Sample Light",
        colors = sampleLightSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(230.dp, 76.dp)
    )
    AuroraWindow(
        skin = sampleLightSkin(),
        title = "Chroma Palette",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(all = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            CommandButtonProjection(
                contentModel = Command(
                    text = "selected",
                    isActionToggle = true,
                    isActionToggleSelected = true,
                    action = {}
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                )
            ).project()

            CommandButtonProjection(
                contentModel = Command(
                    text = "enabled",
                    action = {}
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                )
            ).project()

            CommandButtonProjection(
                contentModel = Command(
                    text = "disabled",
                    isActionEnabled = false,
                    action = {}
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                )
            ).project()
        }
    }
}
