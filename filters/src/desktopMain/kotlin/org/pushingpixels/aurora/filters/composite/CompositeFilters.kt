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
package org.pushingpixels.aurora.filters.composite

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which adds a glow effect to bright areas of the image.
 * @param radius glow radius in pixels
 * @param intensity glow intensity (0-1)
 */
class GlowFilter(
    val radius: Float = 5f,
    val intensity: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform float intensity;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = half4(0);
                int r = int(ceil(radius));
                if (r < 1) r = 1;
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        sum += c;
                        count++;
                    }
                }
                half4 blur = sum / float(count);
                // Glow: add blurred bright areas
                half3 glow = blur.rgb * intensity * step(0.5, blur.r);
                half3 rgb = center.rgb + glow;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(radius)
        dataBuffer.putFloat(intensity)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}


/**
 * A filter which creates a smear/smudge effect.
 * @param distance smear distance in pixels
 * @param angle smear angle in degrees
 */
class SmearFilter(
    val distance: Float = 10f,
    val angle: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float distance;
            uniform float angle;
            half4 main(vec2 fragcoord) {
                float a = angle * 3.14159 / 180.0;
                vec2 dir = vec2(cos(a), sin(a)) * distance;
                half4 c = content.eval(fragcoord);
                half4 smear = content.eval(fragcoord + dir);
                half3 rgb = mix(c.rgb, smear.rgb, 0.5);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(distance)
        dataBuffer.putFloat(angle)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}


