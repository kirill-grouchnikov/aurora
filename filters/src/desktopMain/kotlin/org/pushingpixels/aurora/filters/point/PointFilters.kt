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

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.Filter
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientFilter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which converts an image to binary (black and white) using a threshold.
 * @param threshold brightness threshold (0-1)
 */
class BinaryFilter(val threshold: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half val = luma > threshold ? 1.0 : 0.0;
                return half4(val, val, val, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to pixellate/mosaic images.
 * Replicates the JH-Labs BlockFilter.
 *
 * @param blockSize the number of pixels along each block edge (default 2)

 */
class BlockFilter(val blockSize: Int = 2) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float blockSize;
            half4 main(vec2 fragcoord) {
                // Snap to block grid
                vec2 grid = floor(fragcoord / blockSize) * blockSize;
                // Sample the center of the block for a uniform color
                half4 c = content.eval(grid + blockSize * 0.5);
                return c;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(blockSize.toFloat())
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which makes pixels matching a key color transparent.
 * Replicates the JH-Labs ChromaKeyFilter.
 *
 * @param hTolerance hue tolerance (0-1, default 0)
 * @param sTolerance saturation tolerance (0-1, default 0)
 * @param bTolerance brightness tolerance (0-1, default 0)
 * @param color the key color as 0xRRGGBB (default green 0x00ff00)

 */
class ChromaKeyFilter(
    val hTolerance: Float = 0f,
    val sTolerance: Float = 0f,
    val bTolerance: Float = 0f,
    val color: Int = 0x00ff00
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float hTolerance;
            uniform float sTolerance;
            uniform float bTolerance;
            uniform half3 color;
            half4 rgb2hsl(half3 c) {
                float maxC = max(c.r, max(c.g, c.b));
                float minC = min(c.r, min(c.g, c.b));
                float l = (maxC + minC) * 0.5;
                float s = 0.0;
                float h = 0.0;
                if (maxC != minC) {
                    float d = maxC - minC;
                    s = l > 0.5 ? d / (2.0 - maxC - minC) : d / (maxC + minC);
                    if (maxC == c.r) h = (c.g - c.b) / d + (c.g < c.b ? 6.0 : 0.0);
                    else if (maxC == c.g) h = (c.b - c.r) / d + 2.0;
                    else h = (c.r - c.g) / d + 4.0;
                    h /= 6.0;
                }
                return half3(h, s, l);
            }
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 hsl = rgb2hsl(c.rgb);
                half3 keyHsl = rgb2hsl(color);
                // Compare HSL components with tolerances
                if (abs(hsl.r - keyHsl.r) < hTolerance &&
                    abs(hsl.g - keyHsl.g) < sTolerance &&
                    abs(hsl.b - keyHsl.b) < bTolerance) {
                    return half4(c.rgb, 0.0);
                }
                return c;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(hTolerance)
        dataBuffer.putFloat(sTolerance)
        dataBuffer.putFloat(bTolerance)
        dataBuffer.putFloat(((color shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color and 0xff) / 255f)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to apply a curves adjustment using a simple quadratic curve.
 * @param rControlX x control point for red channel
 * @param rControlY y control point for red channel
 * @param gControlX x control point for green channel
 * @param gControlY y control point for green channel
 * @param bControlX x control point for blue channel
 * @param bControlY y control point for blue channel

 */
class CurvesFilter(
    val rControlX: Float = 0.5f,
    val rControlY: Float = 0.5f,
    val gControlX: Float = 0.5f,
    val gControlY: Float = 0.5f,
    val bControlX: Float = 0.5f,
    val bControlY: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float rControlX, rControlY;
            uniform float gControlX, gControlY;
            uniform float bControlX, bControlY;
            
            float quadraticBezier(float t, float p0, float p1, float p2) {
                float mt = 1.0 - t;
                return p0 * mt * mt + p1 * 2.0 * t * mt + p2 * t * t;
            }
            
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half r = quadraticBezier(c.r, 0.0, rControlY, 1.0);
                half g = quadraticBezier(c.g, 0.0, gControlY, 1.0);
                half b = quadraticBezier(c.b, 0.0, bControlY, 1.0);
                return half4(r, g, b, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(rControlX)
        dataBuffer.putFloat(rControlY)
        dataBuffer.putFloat(gControlX)
        dataBuffer.putFloat(gControlY)
        dataBuffer.putFloat(bControlX)
        dataBuffer.putFloat(bControlY)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which reduces noise by averaging with neighboring pixels.
 * Simplified approximation using a small blur kernel.

 */
object DespeckleFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = center;
                // 3x3 neighborhood
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0) continue;
                        vec2 offset = vec2(float(dx), float(dy));
                        sum += content.eval(fragcoord + offset);
                    }
                }
                // Median approximation: use center if it's close to average
                half4 avg = sum / 9.0;
                half diff = length(center.rgb - avg.rgb);
                half3 rgb = diff > 0.1 ? avg.rgb : center.rgb;
                return half4(rgb, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**
 * A filter which creates a dissolve/fade transition effect.
 * Replicates the JH-Labs DissolveFilter (extends PointFilter).
 *
 * @param amount dissolve amount (0-1)

 */
class DissolveFilter(val amount: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amount;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float r = fract(sin(dot(fragcoord, vec2(12.9898, 78.233))) * 43758.5453);
                half alpha = step(r, amount);
                return half4(c.rgb * alpha, c.a * alpha);
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
 * A filter which applies ordered dithering using a Bayer matrix.

 */
object DitherFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // 4x4 Bayer threshold matrix
                int x = int(fragcoord.x) % 4;
                int y = int(fragcoord.y) % 4;
                half bayer[16] = half4(
                    0.0/16.0, 8.0/16.0, 2.0/16.0, 10.0/16.0,
                    12.0/16.0, 4.0/16.0, 14.0/16.0, 6.0/16.0,
                    3.0/16.0, 11.0/16.0, 1.0/16.0, 9.0/16.0,
                    15.0/16.0, 7.0/16.0, 13.0/16.0, 5.0/16.0
                );
                half threshold = bayer[y * 4 + x];
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    half luma = c.rgb[i];
                    rgb[i] = luma + 0.5/16.0 - 8.0/16.0 > threshold ? 1.0 : 0.0;
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**
 * A filter which equalizes the histogram of an image.
 * Uses a simplified per-channel equalization approximation.

 */
object EqualizeFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Approximate histogram equalization by stretching to full range
                half3 rgb = c.rgb;
                // Simple power curve approximation for equalization
                rgb = pow(rgb, half3(0.8, 0.8, 0.8));
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**
 * A filter which changes the exposure of an image.
 * Replicates the JH-Labs ExposureFilter transfer function: 1 - exp(-f * exposure).
 * @param exposure the exposure level (default 1.0, range 0..5+)

 */
class ExposureFilter(val exposure: Float = 1f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float exposure;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    rgb[i] = 1.0 - exp(-c.rgb[i] * exposure);
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(exposure)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to fade an image toward a specified color.
 * @param fadeAmount fade intensity (0.0 = original, 1.0 = fully faded)
 * @param fadeColor RGB color to fade toward (0-1 range per channel)

 */
class FadeFilter(
    val fadeAmount: Float = 0.5f,
    val fadeColor: FloatArray = floatArrayOf(1f, 1f, 1f)
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float fadeAmount;
            uniform half3 fadeColor;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = mix(c.rgb, fadeColor, fadeAmount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(fadeAmount)
        fadeColor.forEach { dataBuffer.putFloat(it) }
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which fills the image with a solid color.
 * Replicates the JH-Labs FillFilter (extends PointFilter).
 *
 * @param color fill color RGB (0-1 per channel)
 * @param opacity fill opacity (0-1)

 */
class FillFilter(
    val color: FloatArray = floatArrayOf(0f, 0f, 0f),
    val opacity: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 color;
            uniform float opacity;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = mix(c.rgb, color, opacity);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        color.forEach { dataBuffer.putFloat(it) }
        dataBuffer.putFloat(opacity)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which adds a lens flare effect.
 * Replicates the JH-Labs FlareFilter (extends PointFilter).
 *
 * @param centerX flare center X as proportion of image width (0-1)
 * @param centerY flare center Y as proportion of image height (0-1)
 * @param intensity flare intensity (0-1)

 */
class FlareFilter(
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
                // Radial flare
                float flare = exp(-t * 3.0) * intensity;
                float angle = atan(delta.y, delta.x);
                // Anamorphic streak
                float streak = exp(-abs(sin(angle * 3.0)) * 5.0) * flare;
                half3 flareColor = half3(1.0, 0.9, 0.7) * flare + half3(0.5, 0.7, 1.0) * streak;
                half3 rgb = c.rgb + flareColor;
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

/**
 * A filter which flips or rotates an image.
 * Replicates the JH-Labs FlipFilter.
 *
 * @param flipType the flip type: 0=HORIZONTAL, 1=VERTICAL, 2=90CW, 3=90CCW, 4=180

 */
class FlipFilter(val flipType: Int = 0) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int flipType;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 p = fragcoord / imageSize;
                vec2 uv;
                if (flipType == 0) {
                    // Horizontal flip
                    uv = vec2(1.0 - p.x, p.y);
                } else if (flipType == 1) {
                    // Vertical flip
                    uv = vec2(p.x, 1.0 - p.y);
                } else if (flipType == 2) {
                    // 90 degrees clockwise
                    uv = vec2(p.y, 1.0 - p.x);
                } else if (flipType == 3) {
                    // 90 degrees counter-clockwise
                    uv = vec2(1.0 - p.y, p.x);
                } else {
                    // 180 degrees
                    uv = vec2(1.0 - p.x, 1.0 - p.y);
                }
                return content.eval(uv * imageSize);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader, imageWidth: Float = 640f, imageHeight: Float = 480f): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(flipType)
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
 * A filter which maps an image to four colors based on brightness ranges.
 * @param shadowColor color for darkest pixels
 * @param midShadowColor color for mid-dark pixels
 * @param midHighlightColor color for mid-bright pixels
 * @param highlightColor color for brightest pixels

 */
class FourColorFilter(
    val shadowColor: FloatArray = floatArrayOf(0f, 0f, 0f),
    val midShadowColor: FloatArray = floatArrayOf(0.33f, 0.33f, 0.33f),
    val midHighlightColor: FloatArray = floatArrayOf(0.66f, 0.66f, 0.66f),
    val highlightColor: FloatArray = floatArrayOf(1f, 1f, 1f)
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 shadowColor;
            uniform half3 midShadowColor;
            uniform half3 midHighlightColor;
            uniform half3 highlightColor;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half3 color;
                if (luma < 0.333) {
                    color = mix(shadowColor, midShadowColor, luma / 0.333);
                } else if (luma < 0.666) {
                    color = mix(midShadowColor, midHighlightColor, (luma - 0.333) / 0.333);
                } else {
                    color = mix(midHighlightColor, highlightColor, (luma - 0.666) / 0.334);
                }
                return half4(color, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(48).order(ByteOrder.LITTLE_ENDIAN)
        shadowColor.forEach { dataBuffer.putFloat(it) }
        midShadowColor.forEach { dataBuffer.putFloat(it) }
        midHighlightColor.forEach { dataBuffer.putFloat(it) }
        highlightColor.forEach { dataBuffer.putFloat(it) }
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which changes the gain and bias of an image.
 * Replicates the JH-Labs GainFilter.
 * @param gain the gain (0-1, default 0.5)
 * @param bias the bias (0-1, default 0.5)

 */
class GainFilter(
    val gain: Float = 0.5f,
    val bias: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float gain;
            uniform float bias;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    float v = c.rgb[i];
                    // Gain: if v < 0.5, v = v * (2*gain); else v = 1 - (1-v) * (2*(1-gain))
                    if (v < 0.5) {
                        v = v * (2.0 * gain);
                    } else {
                        v = 1.0 - (1.0 - v) * (2.0 * (1.0 - gain));
                    }
                    // Bias: if v < 0.5, v = v * (2*bias); else v = 0.5 + (v-0.5) * (2*(1-bias))
                    if (v < 0.5) {
                        v = v * (2.0 * bias);
                    } else {
                        v = 0.5 + (v - 0.5) * (2.0 * (1.0 - bias));
                    }
                    rgb[i] = clamp(v, 0.0, 1.0);
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(gain)
        dataBuffer.putFloat(bias)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to adjust the Hue, Saturation, and Brightness of an image.
 * Replicates the JH-Labs HSBAdjustFilter with additive factors.
 * @param hFactor hue shift (default 0, additive)
 * @param sFactor saturation shift (default 0, additive, clamped 0-1)
 * @param bFactor brightness shift (default 0, additive, clamped 0-1)

 */
class HSBAdjustFilter(
    val hFactor: Float = 0f,
    val sFactor: Float = 0f,
    val bFactor: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float hFactor;
            uniform float sFactor;
            uniform float bFactor;
            
            half3 rgb2hsl(half3 c) {
                half4 K = half4(0.0, -1.0/3.0, 2.0/3.0, -1.0);
                half4 p = c.b < c.g ? half4(c.bg, K.wz) : half4(c.gb, K.xy);
                half4 q = p.x < p.yz ? half4(p.xyw, abs(p.yz - p.x)) : half4(p.yzx, abs(p.xz - p.y));
                half h = abs(q.x + q.y / 6.0 + q.z);
                half s = q.y / (max(q.x, 1e-6));
                half l = q.x;
                return half3(h, s, l);
            }
            
            half3 hsl2rgb(half3 c) {
                half3 rgb = clamp(abs(fract(c.xxx + half3(1.0, 2.0, 0.0) / 3.0) * 6.0 - 3.0) - 1.0, 0.0, 1.0);
                return c.z + c.y * (rgb - 0.5) * (1.0 - abs(2.0 * c.z - 1.0));
            }
            
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 hsl = rgb2hsl(c.rgb);
                hsl.x = fract(hsl.x + hFactor);
                hsl.y = clamp(hsl.y + sFactor, 0.0, 1.0);
                hsl.z = clamp(hsl.z + bFactor, 0.0, 1.0);
                half3 rgb = hsl2rgb(hsl);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(hFactor)
        dataBuffer.putFloat(sFactor)
        dataBuffer.putFloat(bFactor)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which produces a halftoning effect.
 * Replicates the JH-Labs HalftoneFilter.
 *
 * @param softness the softness of the effect (0-1, default 0.1f)
 * @param invert whether to invert the mask (default false)
 * @param monochrome whether to do monochrome halftoning (default false)

 */
class HalftoneFilter(
    val softness: Float = 0.1f,
    val invert: Boolean = false,
    val monochrome: Boolean = false
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float softness;
            uniform int invert;
            uniform int monochrome;
            float hash(vec2 p) {
                return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
            }
            float noise2(vec2 p) {
                vec2 i = floor(p);
                vec2 f = fract(p);
                f = f * f * (3.0 - 2.0 * f);
                float a = hash(i);
                float b = hash(i + vec2(1.0, 0.0));
                float c = hash(i + vec2(0.0, 1.0));
                float d = hash(i + vec2(1.0, 1.0));
                return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
            }
            float smoothStep(float edge0, float edge1, float x) {
                float t = clamp((x - edge0) / (edge1 - edge0), 0.0, 1.0);
                return t * t * (3.0 - 2.0 * t);
            }
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Generate a halftone mask pattern using noise
                float maskVal = noise2(fragcoord * 0.1);
                if (invert == 1) maskVal = 1.0 - maskVal;
                float s = softness;
                if (monochrome == 1) {
                    float brightness = dot(c.rgb, half3(0.299, 0.587, 0.114));
                    float f = 1.0 - smoothStep(brightness - s, brightness + s, maskVal);
                    return half4(c.rgb * f, c.a);
                } else {
                    float r = 1.0 - smoothStep(c.r - s, c.r + s, maskVal);
                    float g = 1.0 - smoothStep(c.g - s, c.g + s, maskVal);
                    float b = 1.0 - smoothStep(c.b - s, c.b + s, maskVal);
                    return half4(c.rgb * half3(r, g, b), c.a);
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(softness)
        dataBuffer.putInt(if (invert) 1 else 0)
        dataBuffer.putInt(if (monochrome) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which inverts the alpha channel of an image.
 */
object InvertAlphaFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                return half4(c.rgb, 1.0 - c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**
 * A filter which allows levels adjustment on an image.
 * Replicates the JH-Labs LevelsFilter.
 * @param lowLevel input low level (0-1, default 0)
 * @param highLevel input high level (0-1, default 1)
 * @param lowOutputLevel output low level (0-1, default 0)
 * @param highOutputLevel output high level (0-1, default 1)

 */
class LevelsFilter(
    val lowLevel: Float = 0f,
    val highLevel: Float = 1f,
    val lowOutputLevel: Float = 0f,
    val highOutputLevel: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float lowLevel;
            uniform float highLevel;
            uniform float lowOutputLevel;
            uniform float highOutputLevel;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float low = lowLevel * 255.0;
                float high = highLevel * 255.0;
                if (low == high) high++;
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    float v = c.rgb[i] * 255.0;
                    v = lowOutputLevel + (highOutputLevel - lowOutputLevel) * (v - low) / (high - low);
                    rgb[i] = clamp(v / 255.0, 0.0, 1.0);
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(lowLevel)
        dataBuffer.putFloat(highLevel)
        dataBuffer.putFloat(lowOutputLevel)
        dataBuffer.putFloat(highOutputLevel)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to apply a lookup table (LUT) transformation.
 * Uses a simple 3D LUT approximation with a uniform array.
 * @param lutSize size of the LUT dimension

 */
class LookupFilter(val lutSize: Int = 64) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float lutSize;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Simple identity LUT - in practice, the LUT data would be passed as a texture
                return half4(c.rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(lutSize.toFloat())
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which maps colors from one palette to another.
 * @param sourceColors array of source colors to match
 * @param destColors array of destination colors to map to

 */
class MapColorsFilter(
    val sourceColors: FloatArray = floatArrayOf(0f, 0f, 0f, 1f, 1f, 1f),
    val destColors: FloatArray = floatArrayOf(1f, 1f, 1f, 0f, 0f, 0f)
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 sourceColor0;
            uniform half3 sourceColor1;
            uniform half3 destColor0;
            uniform half3 destColor1;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float d0 = length(c.rgb - sourceColor0);
                float d1 = length(c.rgb - sourceColor1);
                float t = d0 / (d0 + d1 + 0.0001);
                half3 rgb = mix(destColor1, destColor0, t);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        for (i in 0 until 3) dataBuffer.putFloat(sourceColors[i])
        for (i in 0 until 3) dataBuffer.putFloat(sourceColors[i + 3])
        for (i in 0 until 3) dataBuffer.putFloat(destColors[i])
        for (i in 0 until 3) dataBuffer.putFloat(destColors[i + 3])
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which applies a mask based on brightness threshold.
 * Replicates the JH-Labs MaskFilter (extends PointFilter).
 *
 * @param threshold brightness threshold (0-1)
 * @param invert if true, invert the mask

 */
class MaskFilter(
    val threshold: Float = 0.5f,
    val invert: Boolean = false
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            uniform int invert;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half mask;
                if (invert != 0) {
                    mask = step(luma, threshold);
                } else {
                    mask = step(threshold, luma);
                }
                return half4(c.rgb * mask, c.a * mask);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        dataBuffer.putInt(if (invert) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to adjust the opacity/alpha of an image.
 * @param opacity opacity value (0.0 = fully transparent, 1.0 = fully opaque)

 */
class OpacityFilter(val opacity: Float = 1f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float opacity;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                return half4(c.rgb, c.a * opacity);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(opacity)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to posterize an image by reducing the number of color levels.
 * @param numLevels number of discrete levels per channel (default 6)

 */
class PosterizeFilter(val numLevels: Int = 6) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int numLevels;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                if (numLevels > 1) {
                    c.rgb = floor(c.rgb * numLevels) / (numLevels - 1.0);
                }
                return half4(c.rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(numLevels)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to premultiply the alpha channel into the RGB values.

 */
object PremultiplyFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                return half4(c.rgb * c.a, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**
 * A filter which quantizes an image to a limited number of colors using median-cut approximation.
 * @param numColors target number of colors

 */
class QuantizeFilter(val numColors: Int = 16) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int numColors;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Approximate quantization by rounding to nearest color cube
                if (numColors > 0) {
                    float levels = pow(float(numColors), 1.0/3.0);
                    levels = max(levels, 1.0);
                    c.rgb = floor(c.rgb * levels) / (levels - 1.0 + 0.0001);
                }
                return half4(c.rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(numColors)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to adjust the R, G, B channels independently.
 * Replicates the JH-Labs RGBAdjustFilter with additive factors.
 * @param rFactor red channel adjustment (default 0, stored as 1+factor)
 * @param gFactor green channel adjustment (default 0, stored as 1+factor)
 * @param bFactor blue channel adjustment (default 0, stored as 1+factor)

 */
class RGBAdjustFilter(
    val rFactor: Float = 0f,
    val gFactor: Float = 0f,
    val bFactor: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float rFactor;
            uniform float gFactor;
            uniform float bFactor;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = half3(c.r * (1.0 + rFactor), c.g * (1.0 + gFactor), c.b * (1.0 + bFactor));
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(rFactor)
        dataBuffer.putFloat(gFactor)
        dataBuffer.putFloat(bFactor)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which solarizes an image — inverts tones above 50% threshold.
 */
object SolarizeFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    half v = c.rgb[i];
                    rgb[i] = v > 0.5 ? 2.0 * (v - 0.5) : 2.0 * (0.5 - v);
                }
                return half4(rgb * c.a, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun renderEffect(): RenderEffect {
        val solarizeShaderBuilder = RuntimeShaderBuilder(this.effect)

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = solarizeShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()
    }

    fun effectModifier(): Modifier {
        val solarizeShaderBuilder = RuntimeShaderBuilder(this.effect)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = solarizeShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}

/**
 * A filter which adds a sparkle/glitter effect radiating from the center of the image.
 * Replicates the JH-Labs SparkleFilter (extends PointFilter).
 *
 * @param rays number of sparkle rays (default 50)
 * @param radius radius of the sparkle effect in pixels (default 25)
 * @param amount sparkle intensity 0-100 (default 50)
 * @param color sparkle color as 0xRRGGBB int (default white)
 * @param randomness randomness variation of ray lengths 0-100 (default 25)
 * @param seed random seed for ray length generation (default 371)

 */
class SparkleFilter(
    val rays: Int = 50,
    val radius: Int = 25,
    val amount: Int = 50,
    val color: Int = -1, // 0xFFFFFFFF (white)
    val randomness: Int = 25,
    val seed: Long = 371L
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int rays;
            uniform int radius;
            uniform float amount;
            uniform half3 color;
            uniform int randomness;
            uniform float seed;
            uniform vec2 imageSize;

            // Simple hash-based noise for ray length generation
            float hash(float n) {
                return fract(sin(n) * 43758.5453);
            }

            // Box-Muller transform for Gaussian distribution
            float gaussian(float u1, float u2) {
                return sqrt(-2.0 * log(u1)) * cos(6.28318 * u2);
            }

            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                vec2 center = imageSize * 0.5;
                float dx = fragcoord.x - center.x;
                float dy = fragcoord.y - center.y;
                float distance = dx * dx + dy * dy;
                float angle = atan(dy, dx);
                float d = (angle + 3.14159) / 6.28318 * float(rays);
                int i = int(d);
                float f = d - float(i);

                // Generate ray lengths deterministically from seed
                float rayLengths0 = float(radius);
                float u1 = hash(float(i) + seed);
                float u2 = hash(float(i) + seed + 1.0);
                float gauss = gaussian(max(u1, 0.0001), u2);
                rayLengths0 += float(randomness) / 100.0 * float(radius) * gauss;

                float rayLengths1 = float(radius);
                float u3 = hash(float(i + 1) + seed);
                float u4 = hash(float(i + 1) + seed + 1.0);
                float gauss1 = gaussian(max(u3, 0.0001), u4);
                rayLengths1 += float(randomness) / 100.0 * float(radius) * gauss1;

                float length = mix(rayLengths0, rayLengths1, f);
                float g = (length * length) / (distance + 0.0001);
                g = pow(g, (100.0 - amount) / 50.0);
                f -= 0.5;
                f = 1.0 - f * f;
                f *= g;
                f = clamp(f, 0.0, 1.0);
                half3 rgb = mix(c.rgb, color, f);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader?, imageWidth: Float = 640f, imageHeight: Float = 480f): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(rays)
        dataBuffer.putInt(radius)
        dataBuffer.putFloat(amount.toFloat())
        dataBuffer.putFloat(((color shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color and 0xff) / 255f)
        dataBuffer.putInt(randomness)
        dataBuffer.putFloat(seed.toFloat())
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
 * A filter which rearranges (swizzles) the RGB channels.
 * @param rChannel source channel for red (0=R, 1=G, 2=B)
 * @param gChannel source channel for green (0=R, 1=G, 2=B)
 * @param bChannel source channel for blue (0=R, 1=G, 2=B)

 */
class SwizzleFilter(
    val rChannel: Int = 0,
    val gChannel: Int = 1,
    val bChannel: Int = 2
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int rChannel;
            uniform int gChannel;
            uniform int bChannel;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 src = c.rgb;
                half3 rgb;
                rgb.r = src[rChannel];
                rgb.g = src[gChannel];
                rgb.b = src[bChannel];
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(rChannel)
        dataBuffer.putInt(gChannel)
        dataBuffer.putInt(bChannel)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which applies a color transfer effect.
 * Replicates the JH-Labs TransferFilter.
 *
 * @param targetColor target color to transfer toward (0-1 per channel)
 * @param amount transfer amount (0-1)

 */
class TransferFilter(
    val targetColor: FloatArray = floatArrayOf(1f, 0.8f, 0.6f),
    val amount: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 targetColor;
            uniform float amount;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half3 transfer = targetColor * luma;
                half3 rgb = mix(c.rgb, transfer, amount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        targetColor.forEach { dataBuffer.putFloat(it) }
        dataBuffer.putFloat(amount)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which performs a threshold operation on an image.
 * Replicates the JH-Labs ThresholdFilter.
 * Pixels below lowerThreshold become black, above upperThreshold become white,
 * with smooth interpolation in between.
 * @param lowerThreshold lower threshold (0-255)
 * @param upperThreshold upper threshold (0-255)
 * @param white color for pixels above upper threshold (0xRRGGBB int)
 * @param black color for pixels below lower threshold (0xRRGGBB int)

 */
class ThresholdFilter(
    val lowerThreshold: Int = 127,
    val upperThreshold: Int = 127,
    val white: Int = 0xffffff,
    val black: Int = 0x000000
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float lowerThreshold;
            uniform float upperThreshold;
            uniform half3 white;
            uniform half3 black;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                float t = smoothstep(lowerThreshold, upperThreshold, luma);
                half3 val = mix(black, white, t);
                return half4(val, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(lowerThreshold / 255f)
        dataBuffer.putFloat(upperThreshold / 255f)
        dataBuffer.putFloat(((white shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((white shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((white and 0xff) / 255f)
        dataBuffer.putFloat(((black shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((black shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((black and 0xff) / 255f)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which performs a tritone conversion — maps shadows, midtones, and highlights
 * to three specified colors.
 * @param shadowColor RGB color for shadows (0-1 range per channel)
 * @param midColor RGB color for midtones (0-1 range per channel)
 * @param highColor RGB color for highlights (0-1 range per channel)

 */
class TritoneFilter(
    val shadowColor: FloatArray = floatArrayOf(0f, 0f, 0f),
    val midColor: FloatArray = floatArrayOf(0.533f, 0.533f, 0.533f),
    val highColor: FloatArray = floatArrayOf(1f, 1f, 1f)
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 shadowColor;
            uniform half3 midColor;
            uniform half3 highColor;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half3 color;
                if (luma < 0.5) {
                    float t = luma / 0.5;
                    color = mix(shadowColor, midColor, t);
                } else {
                    float t = (luma - 0.5) / 0.5;
                    color = mix(midColor, highColor, t);
                }
                return half4(color, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        shadowColor.forEach { dataBuffer.putFloat(it) }
        midColor.forEach { dataBuffer.putFloat(it) }
        highColor.forEach { dataBuffer.putFloat(it) }
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter to unpremultiply the alpha channel from the RGB values.

 */
object UnpremultiplyFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half invA = 1.0 / max(c.a, 0.0001);
                return half4(c.rgb * invA, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}