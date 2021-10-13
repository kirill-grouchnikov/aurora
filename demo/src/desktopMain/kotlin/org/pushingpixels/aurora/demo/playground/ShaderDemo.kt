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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skia.ByteBuffer
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import java.nio.ByteOrder

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(300.dp, 300.dp)
    )

    val sksl = """
            uniform float time;
            
            float f(vec3 p) {
                p.z -= 10. + time;
                float a = p.z * .1;
                p.xy *= mat2(cos(a), sin(a), -sin(a), cos(a));
                return .1 - length(cos(p.xy) + sin(p.yz));
            }
            
            half4 main(vec2 fragcoord) { 
                vec3 d = .5 - fragcoord.xy1 / 500;
                vec3 p=vec3(0);
                for (int i = 0; i < 32; i++) p += f(p) * d;
                return ((sin(p) + vec3(2, 5, 9)) / length(p)).xyz1;
            }
        """

    val runtimeEffect = RuntimeEffect.makeForShader(sksl)
    val byteBuffer = remember { ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN) }
    var timeUniform by remember { mutableStateOf(0.0f) }
    var previousNanos by remember { mutableStateOf(0L) }

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
        val brush = ShaderBrush(shader)

        Box(modifier = Modifier.fillMaxSize().drawBehind {
            drawRect(
                brush = brush, topLeft = Offset(100f, 65f), size = Size(400f, 400f)
            )
        })

        LaunchedEffect(null) {
            while (true) {
                withFrameNanos { frameTimeNanos ->
                    val nanosPassed = frameTimeNanos - previousNanos
                    val delta = nanosPassed / 100000000f
                    if (previousNanos > 0.0f) {
                        timeUniform -= delta
                    }
                    previousNanos = frameTimeNanos
                }
            }
        }
    }
}

