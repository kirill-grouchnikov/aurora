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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skia.BlendMode
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.composite.*
import org.pushingpixels.aurora.filters.generative.CausticsFilter
import org.pushingpixels.aurora.filters.generative.PlasmaFilter
import org.pushingpixels.aurora.filters.generative.SkyFilter
import org.pushingpixels.aurora.filters.point.SolarizeFilter

private object ResourceLoader

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 740.dp)
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
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item { ImageBox(modifier = Modifier.size(200.dp)) }

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
            chromeShaderBuilder.uniform("lightTypes", light.type.ordinal, 0, 0, 0)
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

             item { ImageBox(modifier = Modifier.size(200.dp)
                 .onSizeChanged { newSize ->
                     chromeShaderBuilder.uniform("imageSize", newSize.width.toFloat(), newSize.height.toFloat())
                 }
                 .graphicsLayer(renderEffect = ImageFilter.makeRuntimeShader(
                     runtimeShaderBuilder = chromeShaderBuilder,
                     shaderName = "content",
                     input = null
                 ).asComposeRenderEffect(), clip = true)
             )
             }

            item {
                ImageBox(modifier = Modifier.size(200.dp)
                    .graphicsLayer(
                        renderEffect = colorHalftoneFilter.renderEffect(dotRadius = 3.0f),
                        clip = true))
            }

            item {
                ImageBox(modifier = Modifier.size(200.dp)
                    .graphicsLayer(
                        renderEffect = solarizeFilter.renderEffect(),
                        clip = true))
            }

            item {
                ImageBox(modifier = Modifier.size(200.dp)
                    .graphicsLayer(
                        renderEffect = plasmaFilter.renderEffect(
                            turbulence = 0.3f,
                            scaling = 10.0f,
                            seed = 2089,
                        ),
                        clip = true))
            }

            item {
                ImageBox(modifier = Modifier.size(200.dp)
                    .graphicsLayer(
                        renderEffect = plasmaFilter.renderEffect(
                            turbulence = 0.3f,
                            scaling = 2.0f,
                            seed = 2089,
                            colors = listOf(Color(0xFF0000FF),
                                Color(0xFF00FF00),
                                Color(0xFFFF0000),
                                Color(0xFFFFFF00))
                        ),
                        clip = true))
            }
        }
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
            contentScale = ContentScale.Fit)
    }
}

