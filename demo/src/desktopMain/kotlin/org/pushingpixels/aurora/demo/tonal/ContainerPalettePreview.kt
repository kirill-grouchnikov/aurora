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
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.HorizontalAlignment
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.ContainerColorTokens

private val SmallGap = 3.dp
private val MediumGap = 8.dp
private val SquareSize = 24.dp

@Composable
fun ContainerPalettePreview(colorTokens: ContainerColorTokens, showLegend: Boolean = true) {
    Row(modifier = Modifier.width(340.dp).padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(MediumGap)) {
        Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (showLegend) {
                Box {
                    LabelProjection(
                        contentModel = LabelContentModel(text = "surface"),
                        presentationModel = LabelPresentationModel(
                            contentPadding = PaddingValues(0.dp),
                            horizontalAlignment = HorizontalAlignment.Center,
                            textMaxLines = 1,
                        )
                    ).project(Modifier.padding(vertical = 2.dp))
                }
            }
            Row {
                ColorTokenSquare(colorTokens.containerSurfaceLowest)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.containerSurfaceLow)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.containerSurface)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.containerSurfaceHigh)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.containerSurfaceHighest)

                Spacer(modifier = Modifier.width(MediumGap))

                ColorTokenSquare(colorTokens.containerSurfaceDim)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.containerSurfaceBright)
            }
        }
        Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (showLegend) {
                Box {
                    LabelProjection(
                        contentModel = LabelContentModel(text = "on"),
                        presentationModel = LabelPresentationModel(
                            contentPadding = PaddingValues(0.dp),
                            horizontalAlignment = HorizontalAlignment.Center,
                            textMaxLines = 1,
                        )
                    ).project()
                }
            }
            Row {
                ColorTokenSquare(colorTokens.onContainer)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.onContainerVariant)
            }
        }
        Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (showLegend) {
                Box {
                    LabelProjection(
                        contentModel = LabelContentModel(text = "outline"),
                        presentationModel = LabelPresentationModel(
                            contentPadding = PaddingValues(0.dp),
                            horizontalAlignment = HorizontalAlignment.Center,
                            textMaxLines = 1,
                        )
                    ).project()
                }
            }
            Row {
                ColorTokenSquare(colorTokens.containerOutline)
                Spacer(modifier = Modifier.width(SmallGap))
                ColorTokenSquare(colorTokens.containerOutlineVariant)
            }
        }
    }
}

@Composable
private fun ColorTokenSquare(color: Color) {
    Box(
        modifier = Modifier.size(SquareSize)
            .border(width = 0.dp, color = Color.Black)
            .background(color = color)
    )
}