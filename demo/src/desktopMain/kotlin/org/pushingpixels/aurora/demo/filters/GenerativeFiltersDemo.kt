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
        size = DpSize(600.dp, 600.dp)
    )

    androidx.compose.ui.window.Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Aurora Filters Demo"
    ) {
        val woodFilter = remember {
            WoodFilter(
                scale = 100.0f, angle = PI.toFloat() / 2.0f, stretch = 10.0f, turbulence = 0.0f,
                rings = 0.4f, fibres = 0.3f, gain = 0.7f)
        }
        val brushedMetalFilter = remember {
            BrushedMetalFilter(amount = 40.0f)
        }
        val chromeFilter = remember {
            ChromeFilter()
        }
        val weaveFilter = remember {
            WeaveFilter()
        }
        val marbleTexFilter = remember {
            MarbleTexFilter(color1 = 0xB76E79, color2 = 0xFFC0CB)
        }
        val causticsFilter = remember {
            CausticsFilter()
        }
        val plasmaFilter = remember {
            PlasmaFilter()
        }

        val skyFilter = remember { SkyFilter() }
        val sparkleFilter = remember { SparkleFilter(color = 0x208070, amount = 75, rays = 35) }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 200.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item { Box(modifier = Modifier.size(200.dp).woodFilterBackground(woodFilter)) }
            item { Box(modifier = Modifier.size(200.dp).brushedMetalBackground(brushedMetalFilter)) }
            //item { Box(modifier = Modifier.size(200.dp).plasmaBackground(plasmaFilter)) }
            item { Box(modifier = Modifier.size(200.dp).marbleTexBackground(marbleTexFilter)) }
            item { Box(modifier = Modifier.size(200.dp).sparkleBackground(sparkleFilter)) }
            //item { Box(modifier = Modifier.size(200.dp).skyBackground(skyFilter)) }
            //item { Box(modifier = Modifier.size(200.dp).chromeBackground(chromeFilter)) }
//            item { Box(modifier = Modifier.size(200.dp).causticsBackground(causticsFilter)) }
        }

//        Column(modifier = Modifier.fillMaxSize()) {
//            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f)
//                        .fillMaxHeight()
//                        .woodFilterBackground(woodFilter)
//                )
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth(1.0f)
//                        .fillMaxHeight()
//                        .brushedMetalBackground(brushedMetalFilter)
//                )
//            }
//            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(1.0f)) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f)
//                        .fillMaxHeight()
//                        .plasmaBackground(plasmaFilter)
//                )
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth(1.0f)
//                        .fillMaxHeight()
//                        .marbleBackground(marbleFilter)
//                )
////                Box(
////                    modifier = Modifier
////                        .fillMaxWidth(0.5f)
////                        .fillMaxHeight()
////                        .chromeBackground(chromeFilter)
////                )
////                Box(
////                    modifier = Modifier
////                        .fillMaxWidth(1.0f)
////                        .fillMaxHeight()
////                        .brushedMetalBackground(brushedMetalFilter)
////                )
//            }
       // }
    }
}

private fun Modifier.woodFilterBackground(filter: WoodFilter) = this.then(
    Modifier.drawBehind {
        // Create a solid color base shader (warm brown background) using a linear gradient
        // with identical colors at both stops
        val baseColor = Color(0xFF8B6914)
        val color4f = Color4f(
            baseColor.red / 255f,
            baseColor.green / 255f,
            baseColor.blue / 255f,
            baseColor.alpha / 255f
        )
        val baseShader: Shader = Shader.makeLinearGradient(
            x0 = 0f, y0 = 0f, x1 = 0f, y1 = 0f,
            gradient = Gradient(
                colors = Gradient.Colors(
                    colors = arrayOf(color4f, color4f),
                    positions = floatArrayOf(0f, 1f),
                    tileMode = FilterTileMode.CLAMP
                )
            ),
            localMatrix = null
        )

        // Apply the wood filter to the base shader
        val filteredShader = filter.makeShader(null)

        // Draw the filtered result
        drawRect(
            brush = ShaderBrush(filteredShader.asComposeShader())
        )
    }
)

private fun Modifier.brushedMetalBackground(filter: BrushedMetalFilter) = this.then(
    Modifier.drawBehind {
        // Apply the brushed metal shader filter to the base shader
        val filteredShader = filter.makeShader(null)

        // Draw the filtered result
        drawRect(
            brush = ShaderBrush(filteredShader.asComposeShader())
        )
    }
)

//private fun Modifier.chromeBackground(filter: ChromeFilter) = this.then(
//    Modifier.drawBehind {
//        // Apply the chrome shader filter with image dimensions
//        val filteredShader = filter.makeShader(null, size.width, size.height)
//
//        // Draw the filtered result
//        drawRect(
//            brush = ShaderBrush(filteredShader.asComposeShader())
//        )
//    }
//)

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

private fun Modifier.weaveBackground(filter: WeaveFilter) = this.then(
    Modifier.drawBehind {
        // Apply the brushed metal shader filter to the base shader
        val filteredShader = filter.makeShader(null)

        // Draw the filtered result
        drawRect(
            brush = ShaderBrush(filteredShader.asComposeShader())
        )
    }
)

private fun Modifier.causticsBackground(filter: CausticsFilter) = this.then(
    Modifier.drawBehind {
        // Apply the brushed metal shader filter to the base shader
        val filteredShader = filter.makeShader(null)

        // Draw the filtered result
        drawRect(
            brush = ShaderBrush(filteredShader.asComposeShader())
        )
    }
)

private fun Modifier.skyBackground(filter: SkyFilter) = this.then(
    Modifier.drawBehind {
        // Apply the brushed metal shader filter to the base shader
        val filteredShader = filter.makeShader(null, size.width, size.height)

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
