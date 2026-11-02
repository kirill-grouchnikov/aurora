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
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientShader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.PI

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which produces a color halftone effect with CMYK-style screen angles.
 * Replicates the JH-Labs ColorHalftoneFilter.
 */
class ColorHalftoneFilter() : Filter {
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

//    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
//        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
//        dataBuffer.putFloat(dotRadius)
//        dataBuffer.putFloat(cyanScreenAngle)
//        dataBuffer.putFloat(magentaScreenAngle)
//        dataBuffer.putFloat(yellowScreenAngle)
//        return effect.makeShader(
//            uniforms = Data.makeFromBytes(dataBuffer.array()),
//            children = arrayOf(inputShader),
//            localMatrix = null
//        )
//    }

    fun renderEffect(
        dotRadius: Float = 2.0f,
        cyanAngle: Float = 108.0f * PI.toFloat() / 180.0f,
        magentaAngle: Float = 162.0f * PI.toFloat() / 180.0f,
        yellowAngle: Float = 90.0f * PI.toFloat() / 180.0f
    ): RenderEffect {
        val colorHalftoneShaderBuilder = RuntimeShaderBuilder(this.effect)
        colorHalftoneShaderBuilder.uniform("dotRadius", dotRadius)
        colorHalftoneShaderBuilder.uniform("cyanAngle", cyanAngle)
        colorHalftoneShaderBuilder.uniform("magentaAngle", magentaAngle)
        colorHalftoneShaderBuilder.uniform("yellowAngle", yellowAngle)

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = colorHalftoneShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()
    }

    fun effectModifier(
        dotRadius: Float = 2.0f,
        cyanAngle: Float = 108.0f * PI.toFloat() / 180.0f,
        magentaAngle: Float = 162.0f * PI.toFloat() / 180.0f,
        yellowAngle: Float = 90.0f * PI.toFloat() / 180.0f
    ): Modifier {
        val colorHalftoneShaderBuilder = RuntimeShaderBuilder(this.effect)
        colorHalftoneShaderBuilder.uniform("dotRadius", dotRadius)
        colorHalftoneShaderBuilder.uniform("cyanAngle", cyanAngle)
        colorHalftoneShaderBuilder.uniform("magentaAngle", magentaAngle)
        colorHalftoneShaderBuilder.uniform("yellowAngle", yellowAngle)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = colorHalftoneShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
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
 * @param colors optional colormap colors for glint gradient (default white to black)
 */
class GlintFilter(
    val threshold: Float = 1.0f,
    val length: Int = 5,
    val blur: Float = 0.0f,
    val amount: Float = 0.1f,
    val glintOnly: Boolean = false,
    val colors: List<Color>? = null
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float threshold;
            uniform int length;
            uniform float blur;
            uniform float amount;
            uniform int glintOnly;
            uniform int useColormap;
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
                if (useColormap == 1) {
                    // Use colormap for glint color based on position
                    float t = fragcoord.x / 256.0;
                    t = clamp(t, 0.0, 1.0);
                    half4 colormapEval = colormap.eval(vec2(t * 256.0, 0.0));
                    half3 glintColor = colormapEval.rgb;
                    if (colormapEval.a != 0.0) {
                        glintColor = glintColor / colormapEval.a;
                    }
                    if (glintOnly == 1) {
                        rgb = glintColor * amount;
                    } else {
                        rgb = c.rgb + glintColor * amount;
                    }
                } else {
                    if (glintOnly == 1) {
                        rgb = glint.rgb * amount;
                    } else {
                        rgb = c.rgb + glint.rgb * amount;
                    }
                }
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        dataBuffer.putInt(length)
        dataBuffer.putFloat(blur)
        dataBuffer.putFloat(amount)
        dataBuffer.putInt(if (glintOnly) 1 else 0)
        dataBuffer.putInt(if (colors != null) 1 else 0)

        val gradientShader = colors?.let {
            makeMultiStopHorizontalGradientShader(
                stops = it,
                width = 256.0f
            )
        }

        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader, gradientShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which produces the effect of light rays shining out of an image.
 * Replicates the JH-Labs RaysFilter and its MotionBlurOp superclass.
 *
 * @param centreX center of the effect in X as proportion of image width (default 0.5)
 * @param centreY center of the effect in Y as proportion of image height (default 0.5)
 * @param distance the distance of blur (default 10.0f)
 * @param angle the angle of blur in radians (default 0.0f)
 * @param rotation the rotation amount (default 0.0f)
 * @param zoom the zoom factor (default 0.0f)
 * @param opacity the opacity of the rays overlay (default 1.0f)
 * @param threshold brightness threshold for ray detection (default 0.0f)
 * @param strength the strength/intensity of the rays (default 0.5f)
 * @param raysOnly true to render only rays, false to overlay on source (default false)
 * @param colors optional colormap colors; when provided, the ray brightness maps through the gradient
 */
class RaysFilter(
    val centreX: Float = 0.5f,
    val centreY: Float = 0.5f,
    val distance: Float = 10.0f,
    val angle: Float = 0.0f,
    val rotation: Float = 0.0f,
    val zoom: Float = 0.0f,
    val opacity: Float = 1.0f,
    val threshold: Float = 0.0f,
    val strength: Float = 0.5f,
    val raysOnly: Boolean = false,
    val colors: List<Color>? = null
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform vec2 center;
            uniform float distance;
            uniform float blurAngle;
            uniform float rotation;
            uniform float zoom;
            uniform float opacity;
            uniform float threshold;
            uniform float strength;
            uniform int raysOnly;
            uniform int useColormap;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                vec2 icenter = imageSize * center;
                float cx = icenter.x;
                float cy = icenter.y;
                float imageRadius = length(icenter);

                // Compute maxDistance for multi-step blur
                float maxDistance = distance + abs(rotation * imageRadius) + abs(zoom * imageRadius);
                if (maxDistance < 1.0) maxDistance = 1.0;

                // Multi-step motion blur to create ray effect
                // Each step halves the transform and accumulates
                float translateX = (distance / maxDistance) * cos(blurAngle);
                float translateY = (distance / maxDistance) * (-sin(blurAngle));
                float scale = zoom / maxDistance;
                float rotate = rotation / maxDistance;

                // Number of steps (log2 of maxDistance, clamped)
                int steps = 0;
                float m = 1.0;
                while (m < maxDistance) {
                    m *= 2.0;
                    steps++;
                }
                if (steps < 1) steps = 1;
                if (steps > 16) steps = 16;

                // Accumulate blurred samples along radial direction from center
                half4 rays = half4(0);
                float totalWeight = 0.0;

                for (int i = 0; i < steps; i++) {
                    float fi = float(i);
                    // Compute cumulative transform for this step
                    float stepTranslateX = translateX * pow(2.0, fi);
                    float stepTranslateY = translateY * pow(2.0, fi);
                    float stepScale = 1.0 + scale * pow(2.0, fi);
                    float stepRotate = rotate * pow(2.0, fi);

                    // Radial offset from center
                    vec2 delta = fragcoord - icenter;
                    float dist = length(delta);
                    float radialAngle = atan(delta.y, delta.x);

                    // Apply rotation
                    float cosR = cos(stepRotate);
                    float sinR = sin(stepRotate);
                    vec2 rotatedDelta = vec2(
                        delta.x * cosR - delta.y * sinR,
                        delta.x * sinR + delta.y * cosR
                    );

                    // Apply scale
                    vec2 scaledDelta = rotatedDelta * stepScale;

                    // Apply translate offset along the blur angle
                    vec2 offset = vec2(stepTranslateX, stepTranslateY) * dist;

                    // Sample position
                    vec2 samplePos = icenter + scaledDelta + offset;

                    // Threshold and grayscale the sampled pixel
                    half4 s = content.eval(samplePos);
                    float l = s.r + s.g + s.b;
                    float thresh3 = threshold * 3.0 * 255.0 / 255.0;
                    if (l < thresh3) {
                        s = half4(0, 0, 0, 1);
                    } else {
                        float gray = l / 3.0;
                        s = half4(gray, gray, gray, s.a);
                    }

                    rays += s;
                    totalWeight += 1.0;
                }
                rays /= totalWeight;

                // Apply strength
                half3 rgb;
                if (useColormap == 1) {
                    float l = rays.r + rays.g + rays.b;
                    float t = l * strength / 3.0;
                    t = clamp(t, 0.0, 1.0);
                    half4 colormapEval = colormap.eval(vec2(t * 256.0, 0.0));
                    rgb = colormapEval.rgb;
                    if (colormapEval.a != 0.0) {
                        rgb = rgb / colormapEval.a;
                    }
                } else {
                    rgb = rays.rgb * strength;
                }
                rgb = clamp(rgb, 0.0, 1.0);

                // Composite: ADD rays over source with opacity
                if (raysOnly == 1) {
                    return half4(rgb, opacity);
                } else {
                    half3 finalRgb = c.rgb + rgb * opacity;
                    finalRgb = clamp(finalRgb, 0.0, 1.0);
                    return half4(finalRgb, c.a);
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader,
        imageWidth: Float = 640f,
        imageHeight: Float = 480f
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(60).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(centreX)
        dataBuffer.putFloat(centreY)
        dataBuffer.putFloat(distance)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(rotation)
        dataBuffer.putFloat(zoom)
        dataBuffer.putFloat(opacity)
        dataBuffer.putFloat(threshold)
        dataBuffer.putFloat(strength)
        dataBuffer.putInt(if (raysOnly) 1 else 0)
        dataBuffer.putInt(if (colors != null) 1 else 0)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)

        val gradientShader = colors?.let {
            makeMultiStopHorizontalGradientShader(
                stops = it,
                width = 256.0f
            )
        }

        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader, gradientShader),
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
