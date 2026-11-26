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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import org.pushingpixels.aurora.filters.point.*
import org.pushingpixels.aurora.filters.convolution.DiffusionFilter
import org.pushingpixels.aurora.filters.generative.BrushedMetalFilter
import org.pushingpixels.aurora.filters.generative.CausticsFilter
import org.pushingpixels.aurora.filters.generative.MarbleTexFilter
import org.pushingpixels.aurora.filters.generative.WoodFilter

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

            item(
                span = { GridItemSpan(maxLineSpan) },
                contentType = "header"
            ) {
                BasicText(
                    text = "Color Adjustment Filters",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }

            item {
                FilterBox(
                    effectModifier = ChannelMixFilter.effectModifier(
                        blueGreen = 0.0f,
                        redBlue = 1.0f,
                        greenRed = 0.0f,
                        intoR = 1f,
                        intoG = 0f,
                        intoB = 1f
                    ),
                    renderEffectTitle = "Channel mix"
                )
            }

            item {
                FilterBox(
                    effectModifier = ContrastFilter.effectModifier(
                        brightness = 1.1f,
                        contrast = 1.5f
                    ),
                    renderEffectTitle = "Contrast"
                )
            }

            item {
                FilterBox(
                    effectModifier = CurvesFilter.effectModifier(
                        rControlX = 0.5f, rControlY = 0.7f,
                        gControlX = 0.5f, gControlY = 0.5f,
                        bControlX = 0.2f, bControlY = 0.7f
                    ),
                    renderEffectTitle = "Curves"
                )
            }

            item {
                FilterBox(
                    effectModifier = DiffusionFilter.effectModifier(levels = 6),
                    renderEffectTitle = "Diffusion"
                )
            }

            item {
                FilterBox(
                    effectModifier = DitherFilter.effectModifier(),
                    renderEffectTitle = "Dither"
                )
            }

            item {
                FilterBox(
                    effectModifier = ExposureFilter.effectModifier(exposure = 1.5f),
                    renderEffectTitle = "Exposure"
                )
            }

            item {
                FilterBox(
                    effectModifier = GainFilter.effectModifier(gain = 0.7f, bias = 0.6f),
                    renderEffectTitle = "Gain"
                )
            }

            item {
                FilterBox(
                    effectModifier = GammaFilter.effectModifier(
                        rGamma = 1.5f,
                        gGamma = 1.0f,
                        bGamma = 0.7f
                    ),
                    renderEffectTitle = "Gamma"
                )
            }

            item {
                FilterBox(
                    effectModifier = GrayFilter.effectModifier(),
                    renderEffectTitle = "Gray"
                )
            }

            item {
                FilterBox(
                    effectModifier = GrayscaleFilter.effectModifier(),
                    renderEffectTitle = "Grayscale"
                )
            }

            item {
                FilterBox(
                    effectModifier = HSBAdjustFilter.effectModifier(
                        hFactor = 0.1f,
                        sFactor = 0.2f,
                        bFactor = 0.1f
                    ),
                    renderEffectTitle = "HSB Adjust"
                )
            }

//            item {
//                FilterBox(
//                    effectModifier = InvertAlphaFilter.effectModifier(),
//                    renderEffectTitle = "Invert alpha"
//                )
//            }

            item {
                FilterBox(
                    effectModifier = InvertFilter.effectModifier(),
                    renderEffectTitle = "Invert"
                )
            }

            item {
                FilterBox(
                    effectModifier = LevelsFilter.effectModifier(
                        lowLevel = 0.1f,
                        highLevel = 0.9f,
                        lowOutputLevel = 0.5f,
                        highOutputLevel = 0.8f
                    ),
                    renderEffectTitle = "Levels"
                )
            }

            item {
                FilterBox(
                    effectModifier = LookupFilter.effectModifier(
                        colors = listOf(
                            Color(0xFFFF0000),
                            Color(0xFF0000FF))),
                    renderEffectTitle = "Lookup"
                )
            }

            item {
                FilterBox(
                    effectModifier = MaskFilter.effectModifier(mask = 0xFF00FFFF.toInt()),
                    renderEffectTitle = "Mask"
                )
            }

            item {
                FilterBox(
                    effectModifier = PosterizeFilter.effectModifier(numLevels = 4),
                    renderEffectTitle = "Posterize"
                )
            }

            item {
                FilterBox(
                    effectModifier = RescaleFilter.effectModifier(scale = 2.0f),
                    renderEffectTitle = "Rescale"
                )
            }

            item {
                FilterBox(
                    effectModifier = RGBAdjustFilter.effectModifier(
                        rFactor = 0.7f,
                        gFactor = 0f,
                        bFactor = -0.7f
                    ),
                    renderEffectTitle = "RGB adjust"
                )
            }

            item {
                FilterBox(
                    effectModifier = SolarizeFilter.effectModifier(),
                    renderEffectTitle = "Solarize")
            }

            item {
                FilterBox(
                    effectModifier = ThresholdFilter.effectModifier(
                        lowerThreshold = 70,
                        upperThreshold = 190
                    ),
                    renderEffectTitle = "Threshold"
                )
            }

            item {
                FilterBox(
                    effectModifier = TritoneFilter.effectModifier(
                        shadowColor = Color(0xFF000020),
                        midColor = Color(0xFF204080),
                        highColor = Color(0xFF80A0FF)
                    ),
                    renderEffectTitle = "Tritone"
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
                contentType = "header"
            ) {
                BasicText(
                    text = "Effects Filters",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                    effectModifier = EmbossFilter.effectModifier(),
                    renderEffectTitle = "Emboss"
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
                    effectModifier = StampFilter.effectModifier(
                        radius = 3f, softness = 0.35f),
                    renderEffectTitle = "Stamp"
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
                contentType = "header"
            ) {
                BasicText(
                    text = "Distortion and Warping Filters",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
                contentType = "header"
            ) {
                BasicText(
                    text = "Texturing Filters",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }

            item {
                TexturedBox(
                    effectModifier = BrushedMetalFilter.effectModifier(
                        radius = 8, amount = 1.0f, color = Color(0xFF800000)
                    ),
                    renderEffectTitle = "Brushed Metal"
                )
            }

            item {
                TexturedBox(
                    effectModifier = CausticsFilter.effectModifier(),
                    renderEffectTitle = "Caustics"
                )
            }

            item {
                FilterBox(
                    effectModifier = ChromeFilter.effectModifier(),
                    renderEffectTitle = "Chrome")
            }

            item {
                TexturedBox(
                    effectModifier = FlareFilter.effectModifier(
                        color = Color(0xFFE0FFFF), radius = 100.0f, ringAmount = 0.2f, rayAmount = 0.15f),
                    renderEffectTitle = "Flare"
                )
            }

            item {
                TexturedBox(
                    effectModifier = MarbleTexFilter.effectModifier(
                        colors = listOf(Color(0xFFB76E79), Color(0xFFFFC0CB))
                    ),
                    renderEffectTitle = "Marble"
                )
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
                TexturedBox(
                    effectModifier = SparkleFilter.effectModifier(color = Color(0xFF208070)),
                    renderEffectTitle = "Sparkle"
                )
            }

            item {
                TexturedBox(
                    effectModifier = WoodFilter.effectModifier(
                        scale = 100.0f,
                        rings = 0.4f, fibres = 0.3f, gain = 0.7f
                    ),
                    renderEffectTitle = "Wood"
                )
            }

            item {
                TexturedBox(
                    effectModifier = WoodFilter.effectModifier(
                        scale = 80.0f, stretch = 15.0f, gain = 1.9f,
                        angle = 1.5f * Math.PI.toFloat(),
                        colors = listOf(Color(0xFFCD4A4A), Color(0xFF682523), Color(0xFF482029))
                    ),
                    renderEffectTitle = "Wood with colormap"
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
                contentType = "header"
            ) {
                BasicText(
                    text = "Blurring and Sharpening Filters",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                    effectModifier = HighPassFilter.effectModifier(radius = 2f),
                    renderEffectTitle = "High pass"
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

@Composable
fun TexturedBox(modifier: Modifier = Modifier, effectModifier: Modifier, renderEffectTitle: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.DarkGray).then(effectModifier))
        BasicText(text = renderEffectTitle, modifier = Modifier.padding(all = 8.dp))
    }
}


