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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.filters.point.SparkleFilter
import org.pushingpixels.aurora.filters.generative.BrushedMetalFilter
import org.pushingpixels.aurora.filters.generative.CausticsFilter
import org.pushingpixels.aurora.filters.generative.MarbleTexFilter
import org.pushingpixels.aurora.filters.generative.WoodFilter
import org.pushingpixels.aurora.filters.point.FlareFilter

fun main() = androidx.compose.ui.window.application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(1200.dp, 900.dp)
    )

    androidx.compose.ui.window.Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Aurora Generative Filters Demo"
    ) {
        val woodFilter = remember { WoodFilter() }
        val brushedMetalFilter = remember { BrushedMetalFilter() }
        val marbleTexFilter = remember { MarbleTexFilter() }
        val sparkleFilter = remember { SparkleFilter() }
        val flareFilter = remember { FlareFilter() }
        val causticsFilter = remember { CausticsFilter() }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item {
                TexturedBox(
                    effectModifier = woodFilter.effectModifier(
                        scale = 100.0f,
                        rings = 0.4f, fibres = 0.3f, gain = 0.7f
                    ),
                    renderEffectTitle = "Wood"
                )
            }

            item {
                TexturedBox(
                    effectModifier = woodFilter.effectModifier(
                        scale = 80.0f, stretch = 15.0f, gain = 1.9f,
                        angle = 1.5f * Math.PI.toFloat(),
                        colors = listOf(Color(0xFFCD4A4A), Color(0xFF682523), Color(0xFF482029))
                    ),
                    renderEffectTitle = "Wood with colormap"
                )
            }

            item {
                TexturedBox(
                    effectModifier = brushedMetalFilter.effectModifier(
                        radius = 8, amount = 1.0f, color = Color(0xFF800000)
                    ),
                    renderEffectTitle = "Brushed Metal"
                )
            }

            item {
                TexturedBox(
                    effectModifier = marbleTexFilter.effectModifier(
                        colors = listOf(Color(0xFFB76E79), Color(0xFFFFC0CB))
                    ),
                    renderEffectTitle = "Marble"
                )
            }

            item {
                TexturedBox(
                    effectModifier = sparkleFilter.effectModifier(color = Color(0xFF208070)),
                    renderEffectTitle = "Sparkle"
                )
            }

            item {
                TexturedBox(
                    effectModifier = flareFilter.effectModifier(
                        color = Color(0xFFE0FFFF), radius = 100.0f, ringAmount = 0.2f, rayAmount = 0.15f),
                    renderEffectTitle = "Flare"
                )
            }

            item {
                TexturedBox(
                    effectModifier = causticsFilter.effectModifier(),
                    renderEffectTitle = "Caustics"
                )
            }
        }
    }
}

@Composable
fun TexturedBox(modifier: Modifier = Modifier, effectModifier: Modifier, renderEffectTitle: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.DarkGray).then(effectModifier))
        BasicText(text = renderEffectTitle, modifier = Modifier.padding(all = 8.dp))
    }
}
