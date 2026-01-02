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
import org.pushingpixels.aurora.demo.tonal.TonalPalettePreview
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.businessSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import org.pushingpixels.ephemeral.chroma.hct.Hct
import org.pushingpixels.ephemeral.chroma.palettes.TonalPalette

fun main() = auroraApplication {
    val state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(650.dp, 600.dp)
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
            for (i in 0..<18) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    val hue = i * 20.0
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Hue=$hue"),
                        presentationModel = LabelPresentationModel(
                            contentPadding = PaddingValues(0.dp),
                            horizontalAlignment = HorizontalAlignment.Trailing,
                            textMaxLines = 1,
                            singleLineDisplayPrototype = "Hue=340.0"
                        )
                    ).project(modifier = Modifier.padding(start = 20.dp, end = 10.dp, bottom = 4.dp))
                    TonalPalettePreview(
                        tonalPalette = TonalPalette.fromHct(Hct.from(hue, 80.0, 40.0)),
                        showLegend = (i == 0)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
