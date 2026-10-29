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
package org.pushingpixels.aurora.filters.scale

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which applies bicubic scaling interpolation.
 * @param scaleX horizontal scale factor
 * @param scaleY vertical scale factor
 */
class BicubicScaleFilter(
    val scaleX: Float = 1f,
    val scaleY: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 scale;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 src = fragcoord * scale;
                vec2 uv = src / imageSize;
                vec2 i0 = floor(uv * imageSize - 0.5);
                vec2 f = uv * imageSize - i0 - 0.5;
                vec2 f2 = f * f;
                vec2 f3 = f2 * f;
                vec2 w0 = f3 - 2.5 * f2 + 2.0;
                vec2 w1 = -3.0 * f3 + 4.0 * f2 + 0.5;
                vec2 w2 = 3.0 * f3 - 2.0 * f2;
                vec2 w3 = f3 - 0.5 * f2;
                half4 sum = half4(0);
                for (int vy = -1; vy <= 2; vy++) {
                    for (int vx = -1; vx <= 2; vx++) {
                        vec2 idx = vec2(float(vx), float(vy));
                        vec2 pos = i0 + idx + 0.5;
                        half4 c = content.eval(pos);
                        sum += c * w0[vx+1].x * w0[vy+1].y;
                    }
                }
                return sum;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader,
        imageWidth: Float = 640f,
        imageHeight: Float = 480f
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scaleX)
        dataBuffer.putFloat(scaleY)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which deinterlaces an image by blending alternating rows.
 * @param field field selection (0 = top field, 1 = bottom field)
 */
class DeinterlaceFilter(val field: Int = 0) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int field;
            half4 main(vec2 fragcoord) {
                int y = int(fragcoord.y);
                int row = y % 2;
                if (row == field) {
                    return content.eval(fragcoord);
                } else {
                    half4 above = content.eval(fragcoord + vec2(0.0, -1.0));
                    half4 below = content.eval(fragcoord + vec2(0.0, 1.0));
                    half4 avg = (above + below) / 2.0;
                    return avg;
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(field)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which interpolates between pixels for smooth scaling.
 * @param amount interpolation amount (0-1)
 */
class InterpolateFilter(val amount: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amount;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half4 t = content.eval(fragcoord + vec2(1.0, 0.0));
                half4 r = content.eval(fragcoord + vec2(0.0, 1.0));
                half4 tr = content.eval(fragcoord + vec2(1.0, 1.0));
                half4 avg = (c + t + r + tr) / 4.0;
                half3 rgb = mix(c.rgb, avg.rgb, amount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(amount)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which rescales the image to a new size.
 * @param scaleX horizontal scale factor
 * @param scaleY vertical scale factor
 */
class RescaleFilter(
    val scaleX: Float = 1f,
    val scaleY: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 scale;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 src = fragcoord * scale;
                return content.eval(src);
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
        dataBuffer.putFloat(scaleX)
        dataBuffer.putFloat(scaleY)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
