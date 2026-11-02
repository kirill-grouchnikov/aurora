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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asComposeShader
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skia.Color4f
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.Gradient
import org.jetbrains.skia.Shader
import org.pushingpixels.aurora.filters.composite.ChromeFilter
import org.pushingpixels.aurora.filters.point.SparkleFilter
import org.pushingpixels.aurora.filters.generative.BrushedMetalFilter
import org.pushingpixels.aurora.filters.generative.CausticsFilter
import org.pushingpixels.aurora.filters.generative.MarbleTexFilter
import org.pushingpixels.aurora.filters.generative.PlasmaFilter
import org.pushingpixels.aurora.filters.generative.SkyFilter
import org.pushingpixels.aurora.filters.generative.WeaveFilter
import org.pushingpixels.aurora.filters.generative.WoodFilter
import kotlin.math.PI

fun main() = androidx.compose.ui.window.application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(1200.dp, 900.dp)
    )

    androidx.compose.ui.window.Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Aurora Filters Demo"
    ) {
        val woodFilter = remember { WoodFilter() }
        val brushedMetalFilter = remember { BrushedMetalFilter() }
        val marbleTexFilter = remember {
            MarbleTexFilter(color1 = 0xB76E79, color2 = 0xFFC0CB)
        }
        val sparkleFilter = remember { SparkleFilter(color = 0x208070, amount = 75, rays = 35) }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item {
                Box(
                    modifier = Modifier.size(200.dp).then(
                        woodFilter.effectModifier(
                            scale = 100.0f,
                            rings = 0.4f, fibres = 0.3f, gain = 0.7f
                        )
                    )
                )
            }
            item {
                Box(
                    modifier = Modifier.size(200.dp).then(
                        woodFilter.effectModifier(
                            scale = 80.0f, stretch = 15.0f, gain = 1.9f, angle = 1.5f * Math.PI.toFloat(),
                            colors = listOf(Color(0xFFCD4A4A), Color(0xFF682523), Color(0xFF482029))
                        )
                    )
                )
            }
            item { Box(modifier = Modifier.size(200.dp).
                then(brushedMetalFilter.effectModifier(
                    radius = 8, amount = 1.0f, color = Color(0xFF800000)))) }
            item { Box(modifier = Modifier.size(200.dp).marbleTexBackground(marbleTexFilter)) }
            item { Box(modifier = Modifier.size(200.dp).sparkleBackground(sparkleFilter)) }
        }
    }
}

private fun Modifier.marbleTexBackground(filter: MarbleTexFilter) = this.then(
    Modifier.drawBehind {
        // Apply the brushed metal shader filter to the base shader
        val filteredShader = filter.makeShader(null)

        // Draw the filtered result
        drawRect(
            brush = ShaderBrush(filteredShader.asComposeShader())
        )
    }
)

private fun Modifier.sparkleBackground(filter: SparkleFilter) = this.background(Color.DarkGray).then(
    Modifier.drawBehind {
        // Apply the brushed metal shader filter to the base shader
        val filteredShader = filter.makeShader(null, size.width, size.height)

        // Draw the filtered result
        drawRect(
            brush = ShaderBrush(filteredShader.asComposeShader())
        )
    }
)
