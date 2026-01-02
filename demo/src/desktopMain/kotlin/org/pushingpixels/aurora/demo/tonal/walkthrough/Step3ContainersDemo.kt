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

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.HorizontalAlignment
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.tonal.ContainerPalettePreview
import org.pushingpixels.aurora.demo.tonal.ContainerPreview
import org.pushingpixels.aurora.demo.tonal.TonalPalettePreview
import org.pushingpixels.aurora.demo.tonal.Variant
import org.pushingpixels.aurora.theming.ContainerColorTokensBundle
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.businessSkin
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct
import org.pushingpixels.ephemeral.chroma.palettes.TonalPalette

fun main() = auroraApplication {
    val state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(860.dp, 540.dp)
    )
    AuroraWindow(
            skin = businessSkin(),
            title = "Chroma Palette",
            state = state,
            windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
            icon = radiance_menu(),
            iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
            onCloseRequest = ::exitApplication,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 12.dp)) {
            val activePalette = TonalPalette.fromHct(Hct.from(300.0, 40.0, 40.0))
            val mutedPalette = TonalPalette.fromHct(Hct.from(300.0, 18.0, 40.0))
            val neutralPalette = TonalPalette.fromHct(Hct.from(300.0, 8.0, 40.0))

            val labelPresentationModel = LabelPresentationModel(
                contentPadding = PaddingValues(0.dp),
                horizontalAlignment = HorizontalAlignment.Trailing,
                textMaxLines = 1,
                singleLineDisplayPrototype = "Neutral container"
            )
            val labelModifier = Modifier.padding(start = 20.dp, end = 10.dp, bottom = 4.dp)
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Active palette"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                TonalPalettePreview(tonalPalette = activePalette, showLegend = true)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Muted palette"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                TonalPalettePreview(tonalPalette = mutedPalette, showLegend = false)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Neutral palette"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                TonalPalettePreview(tonalPalette = neutralPalette, showLegend = false)
            }

            val lightBundle = ContainerColorTokensBundle(
                activeContainerTokens = getContainerTokens(
                    seed = activePalette.getHct(80.0),  
                    containerConfiguration = ContainerConfiguration.defaultLight()
                ),  
                mutedContainerTokens = getContainerTokens( 
                    seed = mutedPalette.getHct(85.0),  
                    containerConfiguration = ContainerConfiguration.defaultLight()
                ),  
                neutralContainerTokens = getContainerTokens( 
                    seed = neutralPalette.getHct(95.0),  
                    containerConfiguration = ContainerConfiguration.defaultLight()
                ),
                isSystemDark = false
            )

            val darkBundle = ContainerColorTokensBundle( 
                activeContainerTokens = getContainerTokens( 
                    seed = activePalette.getHct(40.0),  
                    containerConfiguration = ContainerConfiguration.defaultDark()
                ),  
                mutedContainerTokens = getContainerTokens( 
                    seed = mutedPalette.getHct(32.0),  
                    containerConfiguration = ContainerConfiguration.defaultDark()
                ),  
                neutralContainerTokens = getContainerTokens( 
                    seed = neutralPalette.getHct(26.0),  
                    containerConfiguration = ContainerConfiguration.defaultDark()
                ), 
                isSystemDark = true
            )

            Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 8.dp)) {
                LabelProjection(
                    contentModel = LabelContentModel(text = ""),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                LabelProjection(
                    contentModel = LabelContentModel(text = "LIGHT"),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        horizontalAlignment = HorizontalAlignment.Center,
                        textMaxLines = 1,
                    )
                ).project(modifier = Modifier.width(340.dp))

                LabelProjection(
                    contentModel = LabelContentModel(text = "DARK"),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        horizontalAlignment = HorizontalAlignment.Center,
                        textMaxLines = 1,
                    )
                ).project(modifier = Modifier.width(340.dp))
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Active container"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                ContainerPalettePreview(colorTokens = lightBundle.getActiveContainerTokens(), showLegend = true)
                Spacer(modifier = Modifier.width(8.dp))
                ContainerPalettePreview(colorTokens = darkBundle.getActiveContainerTokens(), showLegend = true)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Muted container"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                ContainerPalettePreview(colorTokens = lightBundle.getMutedContainerTokens(), showLegend = false)
                Spacer(modifier = Modifier.width(8.dp))
                ContainerPalettePreview(colorTokens = darkBundle.getMutedContainerTokens(), showLegend = false)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Neutral container"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                ContainerPalettePreview(colorTokens = lightBundle.getNeutralContainerTokens(), showLegend = false)
                Spacer(modifier = Modifier.width(8.dp))
                ContainerPalettePreview(colorTokens = darkBundle.getNeutralContainerTokens(), showLegend = false)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Active container"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                ContainerPreview(colorTokens = lightBundle.getActiveContainerTokens(), text = "Active", variant = Variant.Wide)
                Spacer(modifier = Modifier.width(8.dp))
                ContainerPreview(colorTokens = darkBundle.getActiveContainerTokens(), text = "Active", variant = Variant.Wide)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Muted container"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                ContainerPreview(colorTokens = lightBundle.getMutedContainerTokens(), text = "Muted", variant = Variant.Wide)
                Spacer(modifier = Modifier.width(8.dp))
                ContainerPreview(colorTokens = darkBundle.getMutedContainerTokens(), text = "Muted", variant = Variant.Wide)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "Neutral container"),
                    presentationModel = labelPresentationModel
                ).project(modifier = labelModifier)

                ContainerPreview(colorTokens = lightBundle.getNeutralContainerTokens(), text = "Neutral", variant = Variant.Wide)
                Spacer(modifier = Modifier.width(8.dp))
                ContainerPreview(colorTokens = darkBundle.getNeutralContainerTokens(), text = "Neutral", variant = Variant.Wide)
            }
        }
    }
}
