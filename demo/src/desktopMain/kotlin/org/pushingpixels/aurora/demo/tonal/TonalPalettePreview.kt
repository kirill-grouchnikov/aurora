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
package org.pushingpixels.aurora.demo.tonal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.model.HorizontalAlignment
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.resolveAuroraDefaults
import org.pushingpixels.ephemeral.chroma.palettes.BaseTonalPalette

@Composable
fun TonalPalettePreview(tonalPalette: BaseTonalPalette, showLegend: Boolean = true) {
    val textStyle = resolveAuroraDefaults()
    val fontSize = textStyle.fontSize
    // Compute a smaller font size
    val smallerFontSize = TextUnit(fontSize.value - 1.5f, fontSize.type)

    Column(modifier = Modifier.width(560.dp).padding(horizontal = 10.dp)) {
        if (showLegend) {
            Row(modifier = Modifier.fillMaxWidth(1.0f)) {
                for (tone in 0..100 step 5) {
                    Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                        LabelProjection(
                            contentModel = LabelContentModel(text = tone.toString()),
                            presentationModel = LabelPresentationModel(
                                contentPadding = PaddingValues(0.dp),
                                horizontalAlignment = HorizontalAlignment.Center,
                                textStyle = textStyle.copy(fontSize = smallerFontSize, letterSpacing = (-0.4).sp),
                                textMaxLines = 1,
                                textOverflow = TextOverflow.Visible
                            )
                        ).project(modifier = Modifier.width(24.dp))
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(1.0f)) {
            for (tone in 0..100 step 5) {
                val toneRgb = tonalPalette.tone(tone)
                Box(
                    modifier = Modifier.size(24.dp)
                        .border(width = 0.dp, color = Color.Black)
                        .background(color = Color(toneRgb))
                )
            }
        }
    }
}