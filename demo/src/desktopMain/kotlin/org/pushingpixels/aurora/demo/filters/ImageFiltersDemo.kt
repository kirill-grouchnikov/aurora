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
package org.pushingpixels.aurora.demo.filters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skia.Image
import org.pushingpixels.aurora.filters.composite.BumpShape
import org.pushingpixels.aurora.filters.composite.ChromeFilter
import org.pushingpixels.aurora.filters.composite.ColorHalftoneFilter
import org.pushingpixels.aurora.filters.composite.GlowFilter
import org.pushingpixels.aurora.filters.convolution.EmbossFilter
import org.pushingpixels.aurora.filters.convolution.HighPassFilter
import org.pushingpixels.aurora.filters.convolution.UnsharpFilter
import org.pushingpixels.aurora.filters.convolution.LensBlurFilter
import org.pushingpixels.aurora.filters.convolution.MotionBlurFilter
import org.pushingpixels.aurora.filters.generative.CrystallizeFilter
import org.pushingpixels.aurora.filters.generative.GridType
import org.pushingpixels.aurora.filters.generative.PlasmaFilter
import org.pushingpixels.aurora.filters.generative.PointillizeFilter
import org.pushingpixels.aurora.filters.generative.StampFilter
import org.pushingpixels.aurora.filters.point.BlockFilter
import org.pushingpixels.aurora.filters.point.SolarizeFilter

private object ResourceLoader

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(1200.dp, 900.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Aurora Filters Demo"
    ) {
        // All filters are now objects — no need to remember or instantiate

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item {
                OriginalBox()
            }

            item {
                FilterBox(
                    effectModifier = ChromeFilter.effectModifier(
                        amount = 0.75f,
                        exposure = 0.8f,
                        bumpShape = BumpShape.NONE
                    ),
                    renderEffectTitle = "Chrome"
                )
            }

            item {
                FilterBox(
                    effectModifier = ColorHalftoneFilter.effectModifier(dotRadius = 3.0f),
                    renderEffectTitle = "Color halftone")
            }

            item {
                FilterBox(
                    effectModifier = SolarizeFilter.effectModifier(),
                    renderEffectTitle = "Solarize")
            }

            item {
                FilterBox(
                    effectModifier = PlasmaFilter.effectModifier(
                        turbulence = 0.3f,
                        scaling = 10.0f,
                        seed = 2089),
                    renderEffectTitle = "Plasma without colormap")
            }

            item {
                FilterBox(
                    effectModifier = PlasmaFilter.effectModifier(
                        turbulence = 0.3f,
                        scaling = 2.0f,
                        seed = 2089,
                        colors = listOf(Color(0xFF0000FF),
                            Color(0xFF00FF00),
                            Color(0xFFFF0000),
                            Color(0xFFFFFF00))),
                    renderEffectTitle = "Plasma with colormap")
            }

            item {
                FilterBox(
                    effectModifier = CrystallizeFilter.effectModifier(
                        scale = 20.0f, edgeThickness = 0.4f, fadeEdges = false
                    ),
                    renderEffectTitle = "Crystallize hexagons"
                )
            }

            item {
                FilterBox(
                    effectModifier = CrystallizeFilter.effectModifier(
                        scale = 30.0f, edgeThickness = 0.4f, fadeEdges = false,
                        gridType = GridType.Octagonal),
                    renderEffectTitle = "Crystallize octagons"
                )
            }

            item {
                FilterBox(
                    effectModifier = PointillizeFilter.effectModifier(
                        scale = 10.0f, edgeThickness = 0.4f, fadeEdges = false,
                        fuzziness = 0.1f
                    ),
                    renderEffectTitle = "Pointillize"
                )
            }

            item {
                FilterBox(
                    effectModifier = BlockFilter.effectModifier(blockSize = 10),
                    renderEffectTitle = "Block"
                )
            }

            item {
                FilterBox(
                    effectModifier = StampFilter.effectModifier(
                        radius = 3f, softness = 0.35f),
                    renderEffectTitle = "Stamp"
                )
            }

            item {
                FilterBox(
                    effectModifier = EmbossFilter.effectModifier(),
                    renderEffectTitle = "Emboss"
                )
            }

            item {
                FilterBox(
                    effectModifier = LensBlurFilter.effectModifier(),
                    renderEffectTitle = "Lens blur"
                )
            }

            item {
                FilterBox(
                    effectModifier = MotionBlurFilter.effectModifier(
                        angle = 0.785f,
                        distanceAmount = 40f
                    ),
                    renderEffectTitle = "Motion blur"
                )
            }

            item {
                FilterBox(
                    effectModifier = MotionBlurFilter.effectModifier(
                        angle = 0.785f,
                        distanceAmount = 10f,
                        zoom = 0.2f
                    ),
                    renderEffectTitle = "Motion blur with zoom"
                )
            }

            item {
                FilterBox(
                    effectModifier = HighPassFilter.effectModifier(radius = 2f),
                    renderEffectTitle = "High pass"
                )
            }

            item {
                FilterBox(
                    effectModifier = GlowFilter.effectModifier(radius = 5f, amount = 0.2f),
                    renderEffectTitle = "Glow"
                )
            }

            item {
                FilterBox(
                    effectModifier = UnsharpFilter.effectModifier(radius = 2f, amount = 0.5f),
                    renderEffectTitle = "Unsharp mask"
                )
            }
        }
    }
}

@Composable
fun OriginalBox(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageBox(modifier = Modifier)
        BasicText(text = "Original", modifier = Modifier.padding(all = 8.dp))
    }
}

@Composable
fun FilterBox(modifier: Modifier = Modifier, effectModifier: Modifier, renderEffectTitle: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageBox(modifier = effectModifier)
        BasicText(text = renderEffectTitle, modifier = Modifier.padding(all = 8.dp))
    }
}

@Composable
fun ImageBox(modifier: Modifier) {
    val tooltipStream = ResourceLoader.javaClass.classLoader.getResourceAsStream(
        "org/pushingpixels/aurora/demo/filters/radiance-shells.jpg"
    )
    val bitmap = Image.makeFromEncoded(tooltipStream.readAllBytes()).toComposeImageBitmap()
    val image = BitmapPainter(bitmap)

    Box(modifier) {
        androidx.compose.foundation.Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}


