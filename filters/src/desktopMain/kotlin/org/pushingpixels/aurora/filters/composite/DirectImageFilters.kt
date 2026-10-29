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
 * A filter which produces a color halftone effect with CMYK-style screen angles.
 * Replicates the JH-Labs ColorHalftoneFilter.
 *
 * @param dotRadius the radius of halftone dots (default 2)
 * @param cyanScreenAngle cyan screen angle in radians (default 108°)
 * @param magentaScreenAngle magenta screen angle in radians (default 162°)
 * @param yellowScreenAngle yellow screen angle in radians (default 90°)
 */
class ColorHalftoneFilter(
    val dotRadius: Float = 2f,
    val cyanScreenAngle: Float = 108f * 3.14159265f / 180f,
    val magentaScreenAngle: Float = 162f * 3.14159265f / 180f,
    val yellowScreenAngle: Float = 90f * 3.14159265f / 180f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float dotRadius;
            uniform float cyanAngle;
            uniform float magentaAngle;
            uniform float yellowAngle;
            float hash(vec2 p) {
                return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
            }
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float gridSize = dotRadius * 2.0 * 1.414;
                float halfGrid = gridSize * 0.5;
                float angles[3];
                angles[0] = cyanAngle;
                angles[1] = magentaAngle;
                angles[2] = yellowAngle;
                half3 result = half3(1.0);
                for (int channel = 0; channel < 3; channel++) {
                    float angle = angles[channel];
                    float cos = cos(angle);
                    float sin = sin(angle);
                    // Transform to halftone screen space
                    float tx = fragcoord.x * cos + fragcoord.y * sin;
                    float ty = -fragcoord.x * sin + fragcoord.y * cos;
                    // Find nearest grid point
                    tx = tx - mod(tx - halfGrid, gridSize) + halfGrid;
                    ty = ty - mod(ty - halfGrid, gridSize) + halfGrid;
                    // Transform back to image space
                    float nx = tx * cos - ty * sin;
                    float ny = tx * sin + ty * cos;
                    float dx = fragcoord.x - nx;
                    float dy = fragcoord.y - ny;
                    float dist = sqrt(dx * dx + dy * dy);
                    // Get channel value
                    float val = c.rgb[channel];
                    float l = (1.0 - val * val) * halfGrid * 1.414;
                    float f = 1.0 - smoothstep(dist, dist + 1.0, l);
                    result[channel] = f;
                }
                return half4(result, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(dotRadius)
        dataBuffer.putFloat(cyanScreenAngle)
        dataBuffer.putFloat(magentaScreenAngle)
        dataBuffer.putFloat(yellowScreenAngle)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which adds glint highlights to bright areas.
 * Replicates the JH-Labs GlintFilter.
 *
 * @param threshold brightness threshold for glint (default 1.0f)
 * @param length glint streak length (default 5)
 * @param blur glint blur amount (default 0.0f)
 * @param amount glint intensity (default 0.1f)
 * @param glintOnly true to show only glint, false to overlay (default false)
 */
class GlintFilter(
    val threshold: Float = 1.0f,
    val length: Int = 5,
    val blur: Float = 0.0f,
    val amount: Float = 0.1f,
    val glintOnly: Boolean = false
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            uniform int length;
            uniform float blur;
            uniform float amount;
            uniform int glintOnly;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float brightness = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half4 glint = half4(0);
                // Sample along a diagonal for glint streak
                for (int i = 0; i < length; i++) {
                    float fi = float(i);
                    vec2 offset = vec2(fi, -fi) * 2.0;
                    half4 s = content.eval(fragcoord + offset);
                    float b = dot(s.rgb, half3(0.299, 0.587, 0.114));
                    if (b > threshold) {
                        glint += s * (b - threshold);
                    }
                }
                glint /= float(length);
                half3 rgb;
                if (glintOnly == 1) {
                    rgb = glint.rgb * amount;
                } else {
                    rgb = c.rgb + glint.rgb * amount;
                }
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        dataBuffer.putInt(length)
        dataBuffer.putFloat(blur)
        dataBuffer.putFloat(amount)
        dataBuffer.putInt(if (glintOnly) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which adds a drop shadow effect.
 * @param offsetX shadow offset X in pixels
 * @param offsetY shadow offset Y in pixels
 * @param blur shadow blur radius
 * @param opacity shadow opacity (0-1)
 * @param color shadow color
 */
class ShadowFilter(
    val offsetX: Float = 5f,
    val offsetY: Float = 5f,
    val blur: Float = 3f,
    val opacity: Float = 0.5f,
    val color: FloatArray = floatArrayOf(0f, 0f, 0f)
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 offset;
            uniform float blur;
            uniform float opacity;
            uniform half3 color;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Sample shadow area
                half4 shadow = half4(0);
                int r = int(ceil(blur));
                if (r < 1) r = 1;
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        vec2 pos = fragcoord + offset + vec2(float(dx), float(dy));
                        shadow += content.eval(pos);
                        count++;
                    }
                }
                shadow /= float(count);
                // Invert alpha for shadow mask
                half shadowAlpha = (1.0 - shadow.a) * opacity;
                half3 rgb = mix(c.rgb, color * shadowAlpha, shadowAlpha);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(offsetX)
        dataBuffer.putFloat(offsetY)
        dataBuffer.putFloat(blur)
        dataBuffer.putFloat(opacity)
        color.forEach { dataBuffer.putFloat(it) }
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which adds a shine/gloss effect.
 * @param centerX shine center X as proportion of image width (0-1)
 * @param centerY shine center Y as proportion of image height (0-1)
 * @param intensity shine intensity (0-1)
 */
class ShineFilter(
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f,
    val intensity: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 center;
            uniform float intensity;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                vec2 icenter = imageSize * center;
                vec2 delta = fragcoord - icenter;
                float dist = length(delta);
                float maxDist = length(imageSize * 0.5);
                float t = dist / maxDist;
                float shine = exp(-t * 4.0) * intensity;
                half3 rgb = c.rgb + shine;
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
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        dataBuffer.putFloat(centerX)
        dataBuffer.putFloat(centerY)
        dataBuffer.putFloat(intensity)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
