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
import androidx.compose.runtime.remember
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
import org.pushingpixels.aurora.filters.generative.CausticsFilter
import org.pushingpixels.aurora.filters.generative.PlasmaFilter
import org.pushingpixels.aurora.filters.generative.SkyFilter
import org.pushingpixels.aurora.filters.point.SolarizeFilter

private object ResourceLoader

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(1200.dp, 740.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Aurora Filters Demo"
    ) {
        val chromeFilter = remember { ChromeFilter() }
        val causticsFilter = remember { CausticsFilter() }
        val plasmaFilter = remember { PlasmaFilter() }
        val colorHalftoneFilter = remember { ColorHalftoneFilter() }
        val skyFilter = remember { SkyFilter() }
        val solarizeFilter = SolarizeFilter

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
                    effectModifier = chromeFilter.effectModifier(
                        amount = 0.75f,
                        exposure = 0.8f,
                        bumpShape = BumpShape.NONE
                    ),
                    renderEffectTitle = "Chrome"
                )
            }

            item {
                FilterBox(
                    effectModifier = colorHalftoneFilter.effectModifier(dotRadius = 3.0f),
                    renderEffectTitle = "Color halftone")
            }

            item {
                FilterBox(
                    effectModifier = solarizeFilter.effectModifier(),
                    renderEffectTitle = "Solarize")
            }

            item {
                FilterBox(
                    effectModifier = plasmaFilter.effectModifier(
                        turbulence = 0.3f,
                        scaling = 10.0f,
                        seed = 2089),
                    renderEffectTitle = "Plasma without colormap")
            }

            item {
                FilterBox(
                    effectModifier = plasmaFilter.effectModifier(
                        turbulence = 0.3f,
                        scaling = 2.0f,
                        seed = 2089,
                        colors = listOf(Color(0xFF0000FF),
                            Color(0xFF00FF00),
                            Color(0xFFFF0000),
                            Color(0xFFFFFF00))),
                    renderEffectTitle = "Plasma with colormap")
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


