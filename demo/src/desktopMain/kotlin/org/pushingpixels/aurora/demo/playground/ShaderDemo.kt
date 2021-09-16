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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skia.*
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.window.AuroraWindow
import java.nio.ByteOrder

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(800.dp, 600.dp)
    )
    val skin = mutableStateOf(businessSkin())

    val sksl = """
            uniform float time;
            
            float f(vec3 p) {
                p.z -= 10. + time;
                float a = p.z * .1;
                p.xy *= mat2(cos(a), sin(a), -sin(a), cos(a));
                return .1 - length(cos(p.xy) + sin(p.yz));
            }
            
            half4 main(vec2 fragcoord) { 
                vec3 d = .5 - fragcoord.xy1 / 400;
                vec3 p=vec3(0);
                for (int i = 0; i < 32; i++) p += f(p) * d;
                return ((sin(p) + vec3(2, 5, 9)) / length(p)).xyz1;
            }
        """

    val runtimeEffect = RuntimeEffect.makeForShader(sksl)
    val shaderTile = remember {  ImageBitmap(width = 400, height = 400) }
    val shaderCanvas = remember {  Canvas(shaderTile.asDesktopBitmap()) }
    var clicks by remember { mutableStateOf(0.0f) }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        val timeBits = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(clicks).array()
        val shader = runtimeEffect.makeShader(
            uniforms = Data.makeFromBytes(timeBits),
            children = null,
            localMatrix = null,
            isOpaque = false
        )

        val shaderCanvas = Canvas(shaderTile.asDesktopBitmap())
        val shaderPaint = Paint()
        shaderPaint.setShader(shader)
        shaderCanvas.drawPaint(shaderPaint)

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.size(400.dp).paint(painter = object : Painter() {
                override val intrinsicSize: Size
                    get() = Size.Unspecified

                override fun DrawScope.onDraw() {
                    drawImage(shaderTile, topLeft = Offset(100f, 100f))
                }
            }))
            Box(modifier = Modifier.size(100.dp).background(Color.Blue).clickable(
                onClick = {
                    clicks += 0.1f

                    val time1 = System.nanoTime()
                    val timeBits = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(clicks).array()
                    val shader = runtimeEffect.makeShader(
                        uniforms = Data.makeFromBytes(timeBits),
                        children = null,
                        localMatrix = null,
                        isOpaque = false
                    )
                    val time2 = System.nanoTime()
                    val shaderPaint = Paint()
                    shaderPaint.setShader(shader)
                    val time3 = System.nanoTime()
                    shaderCanvas.drawPaint(shaderPaint)
                    val time4 = System.nanoTime()

                    println("" + clicks + " in " + (time2-time1) + "ns and " + (time3 - time2) + " ns and " + (time4 - time3) + " ns")
                }
            ))
        }
    }
}

