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
package org.pushingpixels.aurora.filters.utility

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which applies a JavaLNF-style bevel effect.
 * @param raised if true, raised bevel; if false, etched bevel
 * @param depth bevel depth (0-1)
 */
class JavaLnFFilter(
    val raised: Boolean = true,
    val depth: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int raised;
            uniform float depth;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float x = fragcoord.x / imageSize.x;
                float y = fragcoord.y / imageSize.y;
                float edgeX = min(x, 1.0 - x) * imageSize.x;
                float edgeY = min(y, 1.0 - y) * imageSize.y;
                float edge = min(edgeX, edgeY);
                float bevel = 0.0;
                if (edge < 3.0) {
                    if (raised != 0) {
                        bevel = (edge < 1.0) ? depth : -depth * 0.5;
                    } else {
                        bevel = (edge < 1.0) ? -depth : depth * 0.5;
                    }
                }
                half3 rgb = c.rgb + bevel;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader,
        imageWidth: Float,
        imageHeight: Float
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        dataBuffer.putInt(if (raised) 1 else 0)
        dataBuffer.putFloat(depth)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which simulates Conway's Game of Life overlay.
 * @param scale cell size in pixels
 * @param amount overlay intensity (0-1)
 */
class LifeFilter(
    val scale: Float = 8f,
    val amount: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float amount;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                vec2 p = floor(fragcoord / scale);
                // Deterministic "life-like" pattern
                float v = 0.0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        vec2 n = p + vec2(float(dx), float(dy));
                        float h = fract(sin(dot(n, vec2(127.1, 311.7))) * 43758.5453);
                        v += step(0.5, h);
                    }
                }
                // Life-like rule approximation
                float cell = fract(sin(dot(p, vec2(269.5, 183.3))) * 43758.5453);
                cell = step(0.5, cell);
                float life = (v > 2.0 && v < 5.0) ? 1.0 : 0.0;
                half3 lifeColor = half3(life);
                half3 rgb = mix(c.rgb, lifeColor, amount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(amount)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which applies a shade/gradient overlay.
 * @param angle shade angle in degrees
 * @param amount shade intensity (0-1)
 */
class ShadeFilter(
    val angle: Float = 45f,
    val amount: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float angle;
            uniform float amount;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float a = angle * 3.14159 / 180.0;
                vec2 dir = normalize(vec2(cos(a), sin(a)));
                float proj = dot(fragcoord, dir) / dot(imageSize, dir);
                proj = clamp(proj, 0.0, 1.0);
                float shade = 1.0 - amount * proj;
                half3 rgb = c.rgb * shade;
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader,
        imageWidth: Float,
        imageHeight: Float
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(amount)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
