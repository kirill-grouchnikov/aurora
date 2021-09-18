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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skia.BlendMode
import org.jetbrains.skia.ColorFilter
import org.jetbrains.skia.Rect
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.skin.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.skin.colorscheme.OrangeColorScheme
import org.pushingpixels.aurora.skin.utils.*
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

        val noiseMetallicPaint = getNoisePaint(metallic.extraLightColor, metallic.darkColor)
        val noiseOrangePaint = getNoisePaint(orange.midColor, orange.ultraDarkColor, 1.0f, 0.25f)
        val noiseOrangePaintAlpha = getNoisePaint(orange.midColor, Color.Black, 0.5f, 0.05f)

        val brushedMetalPaint = getBrushedMetalPaint(
            colorFilter = getGradientColorFilter(metallic.midColor, metallic.darkColor),
            hOffset = 15f
        )
        val brushedMetalOrangePaint = getBrushedMetalPaint(
            colorFilter = ColorFilter.makeBlend(
                orange.lightColor.withAlpha(0.6f).toArgb(),
                BlendMode.MODULATE
            ),
            hOffset = 15f
        )
        val brushedMetalDuotonePaint = getBrushedMetalPaintAlt(
            hOffset = 15f
        )

        Box(modifier = Modifier.size(500.dp).paint(painter = object : Painter() {
            override val intrinsicSize: Size
                get() = Size.Unspecified

            override fun DrawScope.onDraw() {
                drawIntoCanvas {
                    val nativeCanvas = it.nativeCanvas

                    nativeCanvas.save()
                    nativeCanvas.clipRect(Rect.makeLTRB(l = 20f, t = 20f, r = 420f, b = 420f))
                    nativeCanvas.drawRect(
                        r = Rect.makeLTRB(l = 20f, t = 20f, r = 420f, b = 420f),
                        paint = noiseMetallicPaint
                    )
                    nativeCanvas.restore()

                    nativeCanvas.save()
                    nativeCanvas.clipRect(Rect.makeLTRB(l = 440f, t = 20f, r = 840f, b = 420f))
                    nativeCanvas.drawRect(
                        r = Rect.makeLTRB(l = 440f, t = 20f, r = 840f, b = 420f),
                        paint = noiseOrangePaint
                    )
                    nativeCanvas.restore()

                    nativeCanvas.save()
                    nativeCanvas.clipRect(Rect.makeLTRB(l = 860f, t = 20f, r = 1260f, b = 420f))
                    nativeCanvas.drawRect(
                        r = Rect.makeLTRB(l = 860f, t = 20f, r = 1260f, b = 420f),
                        paint = noiseOrangePaintAlpha
                    )
                    nativeCanvas.restore()

                    nativeCanvas.save()
                    nativeCanvas.clipRect(Rect.makeLTRB(l = 20f, t = 440f, r = 420f, b = 840f))
                    nativeCanvas.drawRect(
                        r = Rect.makeLTRB(l = 20f, t = 440f, r = 420f, b = 840f),
                        paint = brushedMetalPaint
                    )
                    nativeCanvas.restore()

                    nativeCanvas.save()
                    nativeCanvas.clipRect(Rect.makeLTRB(l = 440f, t = 440f, r = 840f, b = 840f))
                    nativeCanvas.drawRect(
                        r = Rect.makeLTRB(l = 440f, t = 440f, r = 840f, b = 840f),
                        paint = brushedMetalOrangePaint
                    )
                    nativeCanvas.restore()

                    nativeCanvas.save()
                    nativeCanvas.clipRect(Rect.makeLTRB(l = 860f, t = 440f, r = 1260f, b = 840f))
                    nativeCanvas.drawRect(
                        r = Rect.makeLTRB(l = 860f, t = 440f, r = 1260f, b = 840f),
                        paint = brushedMetalDuotonePaint
                    )
                    nativeCanvas.restore()
                }
            }
        }))
    }
}

