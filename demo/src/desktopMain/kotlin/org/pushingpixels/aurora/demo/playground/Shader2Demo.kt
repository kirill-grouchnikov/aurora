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
            uniform float time;

            float noise(in vec2 position) {
                return fract(sin(dot(position.xy,
                                     vec2(12.9898,78.233)))*
                    43758.5453123);
            }
            
            // Value Noise courtesy of Book of Shaders
            // https://thebookofshaders.com/11/
            float noise2d(vec2 uv) {
                
                vec2 pos = floor(uv);
                vec2 fractional = fract(uv);
                
                // four corners
                float a = noise(pos);					// bottom left
                float b = noise(pos + vec2(1., 0.));	// bottom right
                float c = noise(pos + vec2(1., 1.));	// top right
                float d = noise(pos + vec2(0., 1.));	// top left
                
                vec2 intermix = smoothstep(0., 1., fractional);
                
                float value = mix(a, b, intermix.x);
                value += (d - a) * intermix.y * (1.0 - intermix.x);
                value += (c - b) * intermix.x * intermix.y;
                
                return value;
            }
            
            // Fractal noise courtesy of iq
            // https://www.shadertoy.com/view/XdXGW8
            float fractalNoise2d(vec2 uv) {
                uv *= 3.0;
                uv.x -= time / 5.0;
                uv.y += sin(time / 5.0) * 2.0;
                
                mat2 rotate = mat2(1.6 - sin(time / 100.0) / 10.0, 1.2, -1.2, 1.6);
                
                float value = 0.5 * noise2d(uv);
                uv *= rotate;
                value += 0.25 * noise2d(uv);
                uv *= rotate;
                value += 0.125 * noise2d(uv);
                uv *= rotate;
                value += 0.0625 * noise2d(uv);
                
                return value;
            }
            
            vec2 rotate2D(vec2 _st, float _angle){
                _st -= 0.5;
                _st =  mat2(cos(_angle),-sin(_angle),
                            sin(_angle),cos(_angle)) * _st;
                _st += 0.5;
                return _st;
            }
            
            half4 main(vec2 fragcoord) { 
                vec2 uv = fragcoord.xy/400.0;
                
                float noise = fractalNoise2d(uv);
                uv = rotate2D(uv, noise*300.);
                
                uv = .5*uv;
                float plasma = noise*(sin(uv.x)+cos(uv.y))*5.;
                
                vec3 col = vec3(sin((-plasma)+time),
                                sin((-plasma+.4)+time),
                                sin((-plasma+.5)+time));
            
                return vec4(col,1.0);
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

        Box(modifier = Modifier.fillMaxSize().paint(painter = object : Painter() {
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

        LaunchedEffect(null) {
            while (true) {
                withFrameNanos {
                    timeUniform -= 0.0001f
                }
            }
        }
    }
}

