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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skia.*
import java.nio.ByteOrder

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(300.dp, 300.dp)
    )

    val sksl = """
            uniform shader input;
            
            half4 main(vec2 fragcoord) { 
                return input.eval(fragcoord).bgra;
            }
        """

    val runtimeEffect = RuntimeEffect.makeForShader(sksl)
    val shaderPaint = remember { Paint() }
    val byteBuffer = remember { ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN) }
    var timeUniform by remember { mutableStateOf(0.0f) }

    Window(
        title = "Compose / Skia shader demo",
        state = state,
        onCloseRequest = ::exitApplication,
    ) {
        val timeBits = byteBuffer.clear().putFloat(timeUniform).array()
        val shader = runtimeEffect.makeShader(
            uniforms = Data.makeFromBytes(timeBits),
            children = null,
            localMatrix = null,
            isOpaque = false
        )

        shaderPaint.setShader(shader)

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.size(400.dp).paint(painter = object : Painter() {
                override val intrinsicSize: Size
                    get() = Size.Unspecified

                override fun DrawScope.onDraw() {
                    this.drawIntoCanvas {
                        val nativeCanvas = it.nativeCanvas
                        nativeCanvas.translate(100f, 65f)
                        nativeCanvas.clipRect(Rect.makeWH(400f, 400f))
                        nativeCanvas.drawPaint(shaderPaint)
                    }
                }
            }))
        }

        LaunchedEffect(null) {
            while (true) {
                withFrameNanos {
                    timeUniform -= 0.0005f

                    val timeBits = byteBuffer.clear().putFloat(timeUniform).array()
                    val shader = runtimeEffect.makeShader(
                        uniforms = Data.makeFromBytes(timeBits),
                        children = null,
                        localMatrix = null,
                        isOpaque = false
                    )
                    shaderPaint.setShader(shader)
                }
            }
        }
    }
}

