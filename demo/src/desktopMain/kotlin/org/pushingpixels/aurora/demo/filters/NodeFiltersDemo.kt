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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.composite.BumpShape
import org.pushingpixels.aurora.filters.composite.ChromeFilter
import org.pushingpixels.aurora.filters.composite.Light
import org.pushingpixels.aurora.filters.composite.LightType
import org.pushingpixels.aurora.filters.composite.Material
import org.pushingpixels.aurora.filters.generative.CausticsFilter
import org.pushingpixels.aurora.filters.generative.PlasmaFilter
import org.pushingpixels.aurora.filters.generative.SkyFilter
import org.pushingpixels.aurora.filters.point.SolarizeFilter

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 600.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Aurora Filters Demo"
    ) {
        val chromeFilter = remember { ChromeFilter() }
        val causticsFilter = remember { CausticsFilter() }
        val plasmaFilter = remember { PlasmaFilter() }
        val skyFilter = remember { SkyFilter() }
        val solarizeFilter = SolarizeFilter

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item { CheckeredBox(modifier = Modifier.size(200.dp)) }

//             Chrome filter with Phong lighting - uses makeShader for full uniform support
//             Note: RuntimeShaderBuilder does not support array uniforms, so we use the filter's makeShader method
//             in GenerativeFiltersDemo instead

            val chromeWidth = 400f
            val chromeHeight = 400f

            val chromeShaderBuilder = RuntimeShaderBuilder(chromeFilter.effect)
            chromeShaderBuilder.uniform("amount", 0.5f)
            chromeShaderBuilder.uniform("exposure", 1.0f)
            val chromeMaterial = Material()
            chromeShaderBuilder.uniform("materialDiffuseRGB",
                chromeMaterial.diffuseRGB[0], chromeMaterial.diffuseRGB[1], chromeMaterial.diffuseRGB[2])
            chromeShaderBuilder.uniform("materialSpecularRGB",
                chromeMaterial.specularRGB[0], chromeMaterial.specularRGB[1], chromeMaterial.specularRGB[2])

            chromeShaderBuilder.uniform("ambientIntensity", chromeMaterial.ambientIntensity)
            chromeShaderBuilder.uniform("diffuseReflectivity", chromeMaterial.diffuseReflectivity)
            chromeShaderBuilder.uniform("specularReflectivity", chromeMaterial.specularReflectivity)
            chromeShaderBuilder.uniform("highlight", chromeMaterial.highlight)
            chromeShaderBuilder.uniform("reflectivity", chromeMaterial.reflectivity)
            chromeShaderBuilder.uniform("opacity", chromeMaterial.opacity)

            val light = Light(type = LightType.DISTANT)
            chromeShaderBuilder.uniform("lightCount", 1)
            chromeShaderBuilder.uniform("lightTypes", floatArrayOf(
                light.type.ordinal.toFloat(), 0.0f, 0.0f, 0.0f
            ))
            chromeShaderBuilder.uniform("lightColorRGB", floatArrayOf(
                light.colorRGB[0], light.colorRGB[1], light.colorRGB[2],
                0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f
            ))
            chromeShaderBuilder.uniform("lightDirections", floatArrayOf(
                light.direction[0], light.direction[1], light.direction[2],
                0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f
            ))
            chromeShaderBuilder.uniform("lightPositions", floatArrayOf(
                light.position(chromeWidth, chromeHeight)[0],
                light.position(chromeWidth, chromeHeight)[1],
                light.position(chromeWidth, chromeHeight)[2],
                0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f
            ))
            chromeShaderBuilder.uniform("lightCosConeAngles",
                light.cosConeAngle, 0f, 0f, 0f)
            chromeShaderBuilder.uniform("lightFocus",
                light.focus, 0f, 0f, 0f)

            chromeShaderBuilder.uniform("bumpHeight", 1.0f)
            chromeShaderBuilder.uniform("bumpSoftness", 5.0f)
            chromeShaderBuilder.uniform("bumpShape", BumpShape.NONE.ordinal)
            chromeShaderBuilder.uniform("viewDistance", 10000.0f)
            chromeShaderBuilder.uniform("useImageColors", 0)

             item { CheckeredBox(modifier = Modifier.size(200.dp)
                 .onSizeChanged { newSize ->
                     chromeShaderBuilder.uniform("contentSize", newSize.width.toFloat(), newSize.height.toFloat())
                 }
                 .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
                     runtimeShaderBuilder = chromeShaderBuilder,
                     shaderName = "content",
                     input = null
                 ).asComposeRenderEffect(), clip = true)
             )
             }

            val plasmaShaderBuilder = RuntimeShaderBuilder(plasmaFilter.effect)
            plasmaShaderBuilder.uniform("scaling", 2.0f)
            plasmaShaderBuilder.uniform("turbulence", 0.3f)
            plasmaShaderBuilder.uniform("seed", 2089)
            plasmaShaderBuilder.uniform("useColormap", 1)
            plasmaShaderBuilder.uniform("useImageColors", 0)
            plasmaShaderBuilder.uniform("color1", 0.0f, 0.0f, 255.0f)
            plasmaShaderBuilder.uniform("color2", 0.0f, 255.0f, 0.0f)
            plasmaShaderBuilder.uniform("color3", 255.0f, 0.0f, 0.0f)
            plasmaShaderBuilder.uniform("color4", 255.0f, 255.0f, 0.0f)
            item { CheckeredBox(modifier = Modifier.size(200.dp)
                .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
                    runtimeShaderBuilder = plasmaShaderBuilder,
                    shaderName = "content",
                    input = null
                ).asComposeRenderEffect(), clip = true))
            }

            val solarizeShaderBuilder = RuntimeShaderBuilder(solarizeFilter.effect)
            item { CheckeredBox(modifier = Modifier.size(200.dp)
                .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
                    runtimeShaderBuilder = solarizeShaderBuilder,
                    shaderName = "content",
                    input = null
                ).asComposeRenderEffect(), clip = true))
            }

            //            item { Box(modifier = Modifier.size(200.dp).checkeredBackground()
////                .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
////                    runtimeShaderBuilder = RuntimeShaderBuilder(skyFilter.effect),
////                    shaderName = "shaderInput",
////                    input = null
////                ).asComposeRenderEffect())
//                )
//            }
//            item { Box(modifier = Modifier.size(200.dp).checkeredBackground()
////                .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
////                    runtimeShaderBuilder = RuntimeShaderBuilder(plasmaFilter.effect),
////                    shaderName = "shaderInput",
////                    input = null
////                ).asComposeRenderEffect())
//                )
//            }
//            item { Box(modifier = Modifier.size(200.dp).checkeredBackground()
////                .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
////                    runtimeShaderBuilder = RuntimeShaderBuilder(causticsFilter.effect),
////                    shaderName = "shaderInput",
////                    input = null
////                ).asComposeRenderEffect())
//                )
//            }
        }
    }
}



@Composable
fun CheckeredBox(modifier: Modifier) {
    Box(modifier) {
        Canvas(Modifier.fillMaxSize().clipToBounds()) {
            println("My size is $size")
            val squareSize = 32.dp.toPx()
            val lightColor1 = Color.LightGray
            val darkColor1 = Color.Blue
            val lightColor2 = Color(0xFF505050)
            val darkColor2 = Color(0xFF80FF80)

            val cols = (size.width / squareSize).toInt() + 1
            val rows = (size.height / squareSize).toInt() + 1

            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    val x = col * squareSize
                    val y = row * squareSize
                    val isLight = (row + col) % 2 == 0

                    drawRect(
                        brush = Brush.horizontalGradient(colors =
                            if (isLight) {
                                listOf(lightColor1, lightColor2)
                            } else {
                                listOf(darkColor1, darkColor2)
                            }
                        ),
                        topLeft = androidx.compose.ui.geometry.Offset(x, y),
                        size = androidx.compose.ui.geometry.Size(squareSize, squareSize)
                    )
                }
            }
        }
    }
}

//private fun Modifier.checkeredBackground() = this.then(
//    Modifier.drawBehind {
//        val squareSize = 32.dp.toPx()
//        val lightColor1 = Color.LightGray
//        val darkColor1 = Color.Blue
//        val lightColor2 = Color(0xFFBBBBBB)
//        val darkColor2 = Color(0xFF80FF80)
//
//        val cols = (size.width / squareSize).toInt() + 1
//        val rows = (size.height / squareSize).toInt() + 1
//
//        for (row in 0 until rows) {
//            for (col in 0 until cols) {
//                val x = col * squareSize
//                val y = row * squareSize
//                val isLight = (row + col) % 2 == 0
//
//                drawRect(
//                    brush = Brush.horizontalGradient(colors =
//                        if (isLight) {
//                            listOf(lightColor1, lightColor2)
//                        } else {
//                            listOf(darkColor1, darkColor2)
//                        }
//                    ),
//                    topLeft = androidx.compose.ui.geometry.Offset(x, y),
//                    size = androidx.compose.ui.geometry.Size(squareSize, squareSize)
//                )
//            }
//        }
//    }
//)
