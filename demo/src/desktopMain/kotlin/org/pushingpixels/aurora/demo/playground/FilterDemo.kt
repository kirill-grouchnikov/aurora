/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo.playground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.skin.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.skin.colorscheme.OrangeColorScheme
import org.pushingpixels.aurora.skin.utils.getBrushedMetalShader
import org.pushingpixels.aurora.skin.utils.getNoiseShader
import org.pushingpixels.aurora.window.AuroraWindow

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(800.dp, 600.dp)
    )
    val skin = mutableStateOf(businessSkin())

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        val metallic = MetallicColorScheme()
        val orange = OrangeColorScheme()

        val noiseMetallicPaint = getNoiseShader(metallic.extraLightColor, metallic.darkColor)
        val noiseOrangePaint = getNoiseShader(orange.midColor, orange.ultraDarkColor, 1.0f, 0.25f)
        val noiseOrangePaintAlpha = getNoiseShader(orange.midColor, Color.Black, 0.5f, 0.05f)

        val brushedMetalPaint = getBrushedMetalShader(
            metallic.lightColor, metallic.ultraDarkColor, 1.0f
        )
        val brushedMetalOrangePaint = getBrushedMetalShader(
            orange.lightColor, orange.ultraDarkColor, 1.0f
        )
        val brushedMetalOrangePaintAlpha = getBrushedMetalShader(
            orange.lightColor, orange.ultraDarkColor, 0.5f
        )

        Box(modifier = Modifier.size(500.dp).paint(painter = object : Painter() {
            override val intrinsicSize: Size
                get() = Size.Unspecified

            override fun DrawScope.onDraw() {
                drawRect(
                    brush = ShaderBrush(noiseMetallicPaint),
                    topLeft = Offset(20f, 20f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(noiseOrangePaint),
                    topLeft = Offset(440f, 20f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(noiseOrangePaintAlpha),
                    topLeft = Offset(860f, 20f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(brushedMetalPaint),
                    topLeft = Offset(20f, 440f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(brushedMetalOrangePaint),
                    topLeft = Offset(440f, 440f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(brushedMetalOrangePaintAlpha),
                    topLeft = Offset(860f, 440f),
                    size = Size(400f, 400f)
                )
            }
        }))
    }
}

