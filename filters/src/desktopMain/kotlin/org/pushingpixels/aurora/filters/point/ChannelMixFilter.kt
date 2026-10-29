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
package org.pushingpixels.aurora.filters.point

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter that mixes RGB channels using a 3x3 matrix.
 * @param matrix 3x3 matrix (row-major) for channel mixing
 */
class ChannelMixFilter(
    val matrix: FloatArray = floatArrayOf(
        1f, 0f, 0f,
        0f, 1f, 0f,
        0f, 0f, 1f
    )
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform mat3 matrix;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = matrix * c.rgb;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        matrix.forEach { dataBuffer.putFloat(it) }
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter that rescales the RGB values to a new range.
 * @param minOutput minimum output value (0-1)
 * @param maxOutput maximum output value (0-1)
 */
class RescaleFilter(
    val minOutput: Float = 0f,
    val maxOutput: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float minOutput;
            uniform float maxOutput;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = mix(minOutput, maxOutput, c.rgb);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(minOutput)
        dataBuffer.putFloat(maxOutput)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
