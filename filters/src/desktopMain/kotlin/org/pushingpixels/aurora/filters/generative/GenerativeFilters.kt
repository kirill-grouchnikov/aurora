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
package org.pushingpixels.aurora.filters.generative

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.asComposeShader
import androidx.compose.ui.graphics.asSkiaColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.Filter
import org.pushingpixels.aurora.filters.composite.FloatPair
import org.pushingpixels.aurora.filters.utility.makeHorizontalGradientFilter
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientFilter
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientShader
import kotlin.math.PI

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

// Simple hash-based noise functions for GLSL
private val NOISE_FUNC = """
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
"""

/**
 * A filter which produces an image simulating brushed metal.
 * Replicates the JH-Labs BrushedMetalFilter.
 *
 * @param radius the blur radius (default 10)
 * @param amount the amount of texture (default 0.1f)
 * @param color the metal color as 0xRRGGBB (default 0x888888)
 * @param shine the shine amount (default 0.1f)
 * @param monochrome true for monochrome texture (default true)
 */
class BrushedMetalFilter: Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            uniform float amount;
            uniform float color[4];
            uniform float shine;
            uniform int monochrome;
            uniform float contentWidth;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                float r = color[0];
                float g = color[1];
                float b = color[2];
                float tr = r;
                float tg = g;
                float tb = b;
                // Shine: sine wave based on x position
                if (shine > 0.0) {
                    float f = shine * sin(fragcoord.x / contentWidth * 3.14159265);
                    tr += f;
                    tg += f;
                    tb += f;
                }
                // Random noise texture
                float n = noise2(fragcoord) * 2.0 - 1.0;
                if (monochrome == 1) {
                    float noiseVal = n * amount;
                    tr += noiseVal;
                    tg += noiseVal;
                    tb += noiseVal;
                } else {
                    tr += (noise2(fragcoord + vec2(17.0, 0.0)) * 2.0 - 1.0) * amount;
                    tg += (noise2(fragcoord + vec2(0.0, 17.0)) * 2.0 - 1.0) * amount;
                    tb += (noise2(fragcoord + vec2(17.0, 17.0)) * 2.0 - 1.0) * amount;
                }
                half3 rgb = clamp(half3(tr, tg, tb), 0.0, 1.0);
                // Horizontal box blur
                if (radius > 0) {
                    half4 sum = half4(rgb, 1.0);
                    int r2 = radius * 2 + 1;
                    for (int i = 0; i <= 10; i++) {
                        if (i < -radius) { 
                            break;
                        }
                        if (i > radius) {
                            break;
                        }
                        vec2 offset = vec2(float(i), 0.0);
                        vec2 sampleCoord = fragcoord + offset;
                        float sr = color[0];
                        float sg = color[1];
                        float sb = color[2];
                        float str = sr;
                        float stg = sg;
                        float stb = sb;
                        if (shine > 0.0) {
                            float f2 = shine * sin(sampleCoord.x / contentWidth * 3.14159265);
                            str += f2;
                            stg += f2;
                            stb += f2;
                        }
                        float n2 = noise2(sampleCoord) * 2.0 - 1.0;
                        if (monochrome == 1) {
                            str += n2 * amount;
                            stg += n2 * amount;
                            stb += n2 * amount;
                        } else {
                            str += (noise2(sampleCoord + vec2(17.0, 0.0)) * 2.0 - 1.0) * amount;
                            stg += (noise2(sampleCoord + vec2(0.0, 17.0)) * 2.0 - 1.0) * amount;
                            stb += (noise2(sampleCoord + vec2(17.0, 17.0)) * 2.0 - 1.0) * amount;
                        }
                        half3 blurred = clamp(half3(str, stg, stb), 0.0, 1.0);
                        sum.rgb += blurred;
                    }
                    return half4(sum.rgb / float(r2), 1.0);
                }
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        radius: Int = 10,
        amount: Float = 0.1f,
        color: Color = Color(0xFF888888),
        shine: Float = 0.1f,
        monochrome: Boolean = true
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("radius", radius)
            builder.uniform("amount", amount)
            builder.uniform("color", color.red, color.green, color.blue, 1f)
            builder.uniform("shine", shine)
            builder.uniform("monochrome", if (monochrome) 1 else 0)
            builder.uniform("contentWidth", size.width)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates caustic light patterns.
 * Replicates the JH-Labs CausticsFilter.
 *
 * @param scale the scale of the caustic pattern (default 32.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param brightness the brightness multiplier (default 10)
 * @param amount the overlay intensity (default 1.0f)
 * @param turbulence the turbulence amount (default 1.0f)
 * @param dispersion the dispersion amount (default 0.0f)
 * @param time the time offset for animation (default 0.0f)
 * @param samples the number of samples (default 2)
 * @param bgColor the background color (default light blue)
 */
class CausticsFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float angle;
            uniform float brightness;
            uniform float amount;
            uniform float turbulence;
            uniform float dispersion;
            uniform float time;
            uniform int samples;
            uniform float bgColor[3];
            $NOISE_FUNC
            // Perlin noise3 matching JH-Labs Noise.noise3
            vec3 perm3(int i) {
                float n = float(i) + 1.0;
                float x = fract(sin(n * 127.1 + 311.7) * 43758.5453) * 2.0 - 1.0;
                float y = fract(sin(n * 269.5 + 183.3) * 43758.5453) * 2.0 - 1.0;
                float z = fract(sin(n * 419.2 + 73.7) * 43758.5453) * 2.0 - 1.0;
                float len = sqrt(x*x + y*y + z*z);
                return vec3(x/len, y/len, z/len);
            }
            int permute(int i) {
                return int(mod(float(i) + mod(float(i * 13), 113.0), 256.0));
            }
            float noise3(vec3 p) {
                vec3 t = p + 4096.0;
                ivec3 bx = ivec3(floor(t));
                vec3 rx = fract(t);
                vec3 sx = rx * rx * (3.0 - 2.0 * rx);
                int bx0 = int(mod(float(bx.x), 256.0));
                int bx1 = int(mod(float(bx0 + 1), 256.0));
                int by0 = int(mod(float(bx.y), 256.0));
                int by1 = int(mod(float(by0 + 1), 256.0));
                int bz0 = int(mod(float(bx.z), 256.0));
                int bz1 = int(mod(float(bz0 + 1), 256.0));
                int b00 = permute(permute(bx0) + by0);
                int b10 = permute(permute(bx1) + by0);
                int b01 = permute(permute(bx0) + by1);
                int b11 = permute(permute(bx1) + by1);
                vec3 q = perm3(b00 + bz0);
                float u = rx.x * q.x + rx.y * q.y + rx.z * q.z;
                q = perm3(b10 + bz0);
                float v = (rx.x - 1.0) * q.x + rx.y * q.y + rx.z * q.z;
                float a = u + sx.x * (v - u);
                q = perm3(b01 + bz0);
                u = rx.x * q.x + (rx.y - 1.0) * q.y + rx.z * q.z;
                q = perm3(b11 + bz0);
                v = (rx.x - 1.0) * q.x + (rx.y - 1.0) * q.y + rx.z * q.z;
                float b = u + sx.x * (v - u);
                float c = a + sx.y * (b - a);
                q = perm3(b00 + bz1);
                u = rx.x * q.x + rx.y * q.y + (rx.z - 1.0) * q.z;
                q = perm3(b10 + bz1);
                v = (rx.x - 1.0) * q.x + rx.y * q.y + (rx.z - 1.0) * q.z;
                a = u + sx.x * (v - u);
                q = perm3(b01 + bz1);
                u = rx.x * q.x + (rx.y - 1.0) * q.y + (rx.z - 1.0) * q.z;
                q = perm3(b11 + bz1);
                v = (rx.x - 1.0) * q.x + (rx.y - 1.0) * q.y + (rx.z - 1.0) * q.z;
                b = u + sx.x * (v - u);
                float d = a + sx.y * (b - a);
                return 1.5 * (c + sx.z * (d - c));
            }
            float turbulence2(float x, float y, float t, float octaves) {
                float value = 0.0;
                float lacunarity = 2.0;
                float f = 1.0;
                x += 371.0;
                y += 529.0;
                int intOctaves = int(octaves);
                for (int i = 0; i < 8; i++) {
                    if (i >= intOctaves) break;
                    value += noise3(vec3(x, y, t)) / f;
                    x *= lacunarity;
                    y *= lacunarity;
                    f *= 2.0;
                }
                float remainder = octaves - float(intOctaves);
                if (remainder > 0.0) {
                    value += remainder * noise3(vec3(x, y, t)) / f;
                }
                return value;
            }
            float evaluate(float x, float y, float t) {
                float s = sin(0.1);
                float c = cos(0.1);
                float xt = s * x + c * t;
                float tt = c * x - c * t;
                if (turbulence <= 0.001) {
                    return noise3(vec3(xt, y, tt));
                }
                return turbulence2(xt, y, tt, turbulence);
            }
            half4 main(vec2 fragcoord) {
                // Original JH-Labs CausticsFilter algorithm:
                // 1. Fill output buffer with bgColor
                // 2. For each source pixel (x,y), for each sample with random jitter,
                //    compute noise gradient displacement, add brightness at displaced destination
                // 3. Pixels where many displacements converge become bright (caustic lines).
                //
                // Single-pass approximation:
                // Each output pixel checks nearby source pixels to see if their displacement
                // would scatter brightness to this pixel. Accumulate brightness for matches.
                //
                // Dispersion: when dispersion > 0, each color channel scatters at a different
                // displacement scale, creating chromatic aberration.

                float rs = 1.0 / scale;
                float dd = 0.95;
                float focus = 0.1 + amount;
                float v = brightness / max(float(samples), 1.0);
                if (v < 0.0) v = 0.0;

                // This output pixel coordinates
                float ox = fragcoord.x;
                float oy = fragcoord.y;

                // Search radius: displacement magnitude is roughly scale*focus*noiseGradient
                // noise gradient is at most ~2 (difference of two noise values in [-1,1])
                float searchRadius = scale * focus * 4.0;

                // Accumulate brightness for source pixels that scatter to this output pixel
                float brightnessR = 0.0;
                float brightnessG = 0.0;
                float brightnessB = 0.0;

                // Outer loop: samples (matching original's random jitter per sample)
                for (int s = 0; s < 8; s++) {
                    if (s >= samples) break;
                    float fs = float(s);

                    // Deterministic jitter per sample, matching original's random.nextFloat()
                    float jitterX = fract(sin(fs * 12.9898 + ox * 0.01) * 43758.5453);
                    float jitterY = fract(sin(fs * 78.233 + oy * 0.01) * 43758.5453);

                    // Grid search around this output pixel for source pixels
                    // The grid covers the search radius where displaced destinations could land here
                    int grid = 6;
                    float step = searchRadius / float(grid);

                    for (int gy = 0; gy < 6; gy++) {
                        for (int gx = 0; gx < 6; gx++) {
                            // Source pixel position (offset from output pixel + jitter)
                            float sx = ox + jitterX + (float(gx) - float(grid) * 0.5) * step;
                            float sy = oy + jitterY + (float(gy) - float(grid) * 0.5) * step;

                            // Noise coordinates for this source pixel
                            float snx = sx * rs;
                            float sny = sy * rs;

                            // Compute displacement at this source pixel (noise gradient)
                            float xDisp = evaluate(snx - dd, sny, time) - evaluate(snx + dd, sny, time);
                            float yDisp = evaluate(snx, sny + dd, time) - evaluate(snx, sny - dd, time);

                            if (dispersion > 0.0) {
                                // Per-channel dispersion: each channel scatters at a different position
                                // Original: c=0 (blue) uses base displacement, c=1 (green) uses 1+dispersion,
                                // c=2 (red) uses 1+2*dispersion
                                // Blue channel (c=0, ca=1.0)
                                {
                                    float ca = 1.0;
                                    float destX = sx + scale * focus * xDisp * ca;
                                    float destY = sy + scale * focus * yDisp * ca;
                                    float dx = destX - ox;
                                    float dy = destY - oy;
                                    float dist = sqrt(dx * dx + dy * dy);
                                    // Smooth falloff: contribute more when closer
                                    if (dist < step) {
                                        float contrib = 1.0 - dist / step;
                                        brightnessB += contrib * v;
                                    }
                                }
                                // Green channel (c=1, ca=1+dispersion)
                                {
                                    float ca = 1.0 + dispersion;
                                    float destX = sx + scale * focus * xDisp * ca;
                                    float destY = sy + scale * focus * yDisp * ca;
                                    float dx = destX - ox;
                                    float dy = destY - oy;
                                    float dist = sqrt(dx * dx + dy * dy);
                                    if (dist < step) {
                                        float contrib = 1.0 - dist / step;
                                        brightnessG += contrib * v;
                                    }
                                }
                                // Red channel (c=2, ca=1+2*dispersion)
                                {
                                    float ca = 1.0 + 2.0 * dispersion;
                                    float destX = sx + scale * focus * xDisp * ca;
                                    float destY = sy + scale * focus * yDisp * ca;
                                    float dx = destX - ox;
                                    float dy = destY - oy;
                                    float dist = sqrt(dx * dx + dy * dy);
                                    if (dist < step) {
                                        float contrib = 1.0 - dist / step;
                                        brightnessR += contrib * v;
                                    }
                                }
                            } else {
                                // No dispersion: all channels scatter at the same position
                                float destX = sx + scale * focus * xDisp;
                                float destY = sy + scale * focus * yDisp;

                                // Distance from displaced destination to this output pixel
                                float dx = destX - ox;
                                float dy = destY - oy;
                                float dist = sqrt(dx * dx + dy * dy);

                                // Smooth falloff: contribute more when closer
                                if (dist < step) {
                                    float contrib = 1.0 - dist / step;
                                    brightnessR += contrib * v;
                                    brightnessG += contrib * v;
                                    brightnessB += contrib * v;
                                }
                            }
                        }
                    }
                }

                // Normalize brightness per channel to [0, 1] range
                // The original accumulates up to brightness per sample, clamped at 255
                brightnessR = clamp(brightnessR, 0.0, 255.0) / 255.0;
                brightnessG = clamp(brightnessG, 0.0, 255.0) / 255.0;
                brightnessB = clamp(brightnessB, 0.0, 255.0) / 255.0;

                // Blend with background color
                half3 rgb = half3(bgColor[0], bgColor[1], bgColor[2]);
                rgb.r += brightnessR * amount;
                rgb.g += brightnessG * amount;
                rgb.b += brightnessB * amount;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        scale: Float = 32f,
        angle: Float = 0f,
        brightness: Int = 10,
        amount: Float = 1f,
        turbulence: Float = 1f,
        dispersion: Float = 0f,
        time: Float = 0f,
        samples: Int = 2,
        bgColor: Color = Color(0xFF799FFF)
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("scale", scale)
            builder.uniform("angle", angle)
            builder.uniform("brightness", brightness.toFloat())
            builder.uniform("amount", amount)
            builder.uniform("turbulence", turbulence)
            builder.uniform("dispersion", dispersion)
            builder.uniform("time", time)
            builder.uniform("samples", samples)
            builder.uniform("bgColor", bgColor.red, bgColor.green, bgColor.blue)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates a checkered pattern overlay.
 * Replicates the JH-Labs CheckFilter.
 *
 * @param xScale the x scale of checks (default 8)
 * @param yScale the y scale of checks (default 8)
 * @param foreground the foreground check color as 0xRRGGBB (default white)
 * @param background the background check color as 0xRRGGBB (default black)
 * @param fuzziness the edge fuzziness (default 0)
 * @param angle the rotation angle in radians (default 0.0f)
 */
class CheckFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float xScale;
            uniform float yScale;
            uniform half3 foreground;
            uniform half3 background;
            uniform float fuzziness;
            uniform float angle;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;

                int x = int(nx / xScale);
                int y = int(ny / yScale);
                half3 check = (x + y) % 2 == 0 ? foreground : background;

                // Fuzziness: add noise at edges
                if (fuzziness > 0.0) {
                    float fx = fract(nx / xScale);
                    float fy = fract(ny / yScale);
                    float edgeX = min(fx, 1.0 - fx);
                    float edgeY = min(fy, 1.0 - fy);
                    float edge = min(edgeX, edgeY);
                    if (edge < fuzziness / xScale) {
                        float n = noise2(fragcoord * 0.1) * 2.0 - 1.0;
                        check = mix(check, mix(foreground, background, n), edge * xScale / fuzziness);
                    }
                }
                return half4(check, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        xScale: Int = 8,
        yScale: Int = 8,
        foreground: Color = Color.White,
        background: Color = Color.Black,
        fuzziness: Int = 0,
        angle: Float = 0f
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("xScale", xScale.toFloat())
            builder.uniform("yScale", yScale.toFloat())
            builder.uniform("foreground", foreground.red, foreground.green, foreground.blue)
            builder.uniform("background", background.red, background.green, background.blue)
            builder.uniform("fuzziness", fuzziness.toFloat())
            builder.uniform("angle", angle)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates an FBM (Fractal Brownian Motion) texture overlay.
 * Replicates the JH-Labs FBMFilter.
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param amount the overlay intensity (default 1.0f)
 * @param H the H parameter for FBM (default 1.0f)
 * @param octaves the number of FBM octaves (default 4.0f)
 * @param lacunarity the FBM lacunarity (default 2.0f)
 * @param gain the FBM gain (default 0.5f)
 * @param bias the FBM bias (default 0.5f)
 * @param operation the blend operation: 0=ADD, 1=SUBTRACT, 2=MULTIPLY (default 0)
 * @param ridged true for ridged FBM (default false)
 * @param colors optional colormap colors; when provided, the FBM value is mapped through the gradient
 */
class FBMFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float amount;
            uniform float H;
            uniform float octaves;
            uniform float lacunarity;
            uniform float gain;
            uniform float bias;
            uniform int operation;
            uniform int ridged;
            uniform int useColormap;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                nx /= scale * stretch;
                ny /= scale;

                float value = 0.0;
                float amplitude = bias;
                int intOctaves = int(octaves);
                for (int i = 0; i < 8; i++) {
                    if (i >= intOctaves) break;
                    float n = noise2(vec2(nx * pow(lacunarity, float(i)), ny * pow(lacunarity, float(i))));
                    if (ridged == 1) {
                        n = 1.0 - abs(n * 2.0 - 1.0);
                    }
                    n *= pow(amplitude, H);
                    if (operation == 0) {
                        value += n;
                    } else if (operation == 1) {
                        value -= n;
                    } else {
                        value *= n;
                    }
                    amplitude *= gain;
                }
                // Normalize to 0..1
                value = value * 0.5 + 0.5;
                value = clamp(value, 0.0, 1.0);
                half3 rgb;
                if (useColormap == 1) {
                    half4 colormapEval = colormap.eval(vec2(value * 256.0, 0.0));
                    rgb = colormapEval.rgb;
                    if (colormapEval.a != 0.0) {
                        rgb = rgb / colormapEval.a;
                    }
                    rgb = mix(c.rgb, rgb, amount);
                } else {
                    half3 fbm = half3(value);
                    if (operation == 0) {
                        rgb = mix(c.rgb, fbm, amount);
                    } else if (operation == 1) {
                        rgb = c.rgb - fbm * amount;
                    } else {
                        rgb = c.rgb * fbm * amount;
                    }
                }
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        scale: Float = 32f,
        stretch: Float = 1f,
        angle: Float = 0f,
        amount: Float = 1f,
        H: Float = 1f,
        octaves: Float = 4f,
        lacunarity: Float = 2f,
        gain: Float = 0.5f,
        bias: Float = 0.5f,
        operation: Int = 0,
        ridged: Boolean = false,
        colors: List<Color>
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("scale", scale)
            builder.uniform("stretch", stretch)
            builder.uniform("angle", angle)
            builder.uniform("amount", amount)
            builder.uniform("H", H)
            builder.uniform("octaves", octaves)
            builder.uniform("lacunarity", lacunarity)
            builder.uniform("gain", gain)
            builder.uniform("bias", bias)
            builder.uniform("operation", operation)
            builder.uniform("ridged", if (ridged) 1 else 0)
            builder.uniform("useColormap", if (colors != null) 1 else 0)
            builder.child("colormap", makeMultiStopHorizontalGradientShader(colors, 256f))
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates a gradient fill.
 * Replicates the JH-Labs GradientFilter.
 *
 * @param angle the angle of the gradient in radians (default 0)
 * @param colors the colormap colors (default black to white)
 * @param repeat whether to repeat the gradient (default false)
 * @param type the gradient type: 0=LINEAR, 1=RADIAL, 2=SQUARE, 3=CONICAL (default 0)
 * @param interpolation the interpolation mode: 0=LINEAR, 1=GAUSSIAN (default 0)
 * @param paintMode the paint mode: 0=NORMAL, 1=ADD, 2=SUBTRACT (default 0)
 */
class GradientFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float angle;
            uniform int repeat;
            uniform int type;
            uniform int interpolation;
            uniform int paintMode;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 p = fragcoord / imageSize;
                float t;
                if (type == 0) {
                    // Linear gradient
                    float dx = cos(angle);
                    float dy = sin(angle);
                    t = p.x * dx + p.y * dy;
                } else if (type == 1) {
                    // Radial gradient
                    vec2 center = vec2(0.5, 0.5);
                    t = length(p - center) * 1.414;
                } else if (type == 2) {
                    // Square gradient
                    vec2 center = vec2(0.5, 0.5);
                    t = length(abs(p - center)) * 2.828;
                } else {
                    // Conical gradient
                    vec2 center = vec2(0.5, 0.5);
                    t = atan(p.y - center.y, p.x - center.x) / 6.28318 + 0.5;
                }
                if (repeat == 1) {
                    t = fract(t);
                } else {
                    t = clamp(t, 0.0, 1.0);
                }
                // Gaussian interpolation
                if (interpolation == 1) {
                    t = exp(-0.5 * (t - 0.5) * (t - 0.5) / 0.04);
                }
                t = clamp(t, 0.0, 1.0);
                half4 gradient = colormap.eval(vec2(t * 256.0, 0.0));
                half3 rgb = gradient.rgb;
                // Paint mode
                if (paintMode != 0) {
                    half4 c = content.eval(fragcoord);
                    if (paintMode == 1) {
                        rgb += c.rgb;
                    } else {
                        rgb -= c.rgb;
                    }
                }
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        angle: Float = 0f,
        colors: List<Color> = listOf(Color(0xFF000000), Color(0xFFFFFFFF)),
        repeat: Boolean = false,
        type: Int = 0,
        interpolation: Int = 0,
        paintMode: Int = 0,
        imageWidth: Float = 640f,
        imageHeight: Float = 480f
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("angle", angle)
            builder.uniform("repeat", if (repeat) 1 else 0)
            builder.uniform("type", type)
            builder.uniform("interpolation", interpolation)
            builder.uniform("paintMode", paintMode)
            builder.uniform("imageSize", size.width, size.height)
            builder.child("colormap", makeMultiStopHorizontalGradientShader(colors, 256f))
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates a marble texture overlay.
 * Replicates the JH-Labs MarbleFilter.
 *
 * @param xScale the x scale factor (default 4.0f)
 * @param yScale the y scale factor (default 4.0f)
 * @param amount the overlay intensity (default 1.0f)
 * @param turbulence the turbulence amount (default 1.0f)
 */
class MarbleFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float xScale;
            uniform float yScale;
            uniform float amount;
            uniform float turbulence;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float x = fragcoord.x * xScale;
                float y = fragcoord.y * yScale;
                // Marble displacement using noise
                float displacement = noise2(vec2(y * turbulence, x)) * turbulence;
                float marble = sin(x * 5.0 + displacement * 5.0);
                marble = marble * 0.5 + 0.5;
                half3 tex = half3(marble, marble * 0.9, marble * 0.8);
                half3 rgb = mix(c.rgb, tex, amount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        xScale: Float = 4f,
        yScale: Float = 4f,
        amount: Float = 1f,
        turbulence: Float = 1f
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("xScale", xScale.toFloat())
            builder.uniform("yScale", yScale.toFloat())
            builder.uniform("amount", amount)
            builder.uniform("turbulence", turbulence)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates a marble texture (texture-only version).
 * Replicates the JH-Labs MarbleTexFilter.
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param turbulence the turbulence amount (default 1.0f)
 * @param turbulenceFactor the turbulence factor (default 0.5f)
 */
class MarbleTexFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader colormap;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float turbulence;
            uniform float turbulenceFactor;
            $NOISE_FUNC
            float noise2t(vec2 p, float t) {
                // Turbulence noise with time offset
                vec2 i = floor(p + t);
                vec2 f = fract(p + t);
                f = f * f * (3.0 - 2.0 * f);
                float a = hash(i);
                float b = hash(i + vec2(1.0, 0.0));
                float c = hash(i + vec2(0.0, 1.0));
                float d = hash(i + vec2(1.0, 1.0));
                return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
            }
            half4 main(vec2 fragcoord) {
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                nx /= scale * stretch;
                ny /= scale;

                // Turbulence-based chaos
                float chaos = turbulenceFactor * noise2t(vec2(nx, ny), turbulence);
                float f = sin(sin(8.0 * chaos + 7.0 * nx + 3.0 * ny));
                float t = abs(f);

                float greenLayer = t;
                float brownLayer = t;

                float perturb = abs(sin(40.0 * chaos));
                float brownPerturb = 0.6 * perturb + 0.3;
                float greenPerturb = 0.2 * perturb + 0.8;
                float grnPerturb = 0.15 * perturb + 0.85;
                float grn = 0.5 * pow(abs(brownLayer), 0.3);
                brownLayer = pow(0.5 * (brownLayer + 1.0), 0.6) * brownPerturb;
                greenLayer = pow(0.5 * (greenLayer + 1.0), 0.6) * greenPerturb;

                float red = (0.5 * brownLayer + 0.35 * greenLayer) * 2.0 * grn;
                float blu = (0.25 * brownLayer + 0.35 * greenLayer) * 2.0 * grn;
                grn *= max(brownLayer, greenLayer) * grnPerturb;

                // Blend with colormap colors
                half3 rgb = colormap.eval(vec2(grn * 256, 0)).rgb;
                rgb.r *= red;
                rgb.g *= grn;
                rgb.b *= blu;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        scale: Float = 32f,
        stretch: Float = 1f,
        angle: Float = 0f,
        turbulence: Float = 1f,
        turbulenceFactor: Float = 0.5f,
        colors: List<Color> = listOf(Color(0xFF8B4513), Color(0xFF228B22))
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("scale", scale)
            builder.uniform("stretch", stretch)
            builder.uniform("angle", angle)
            builder.uniform("turbulence", turbulence)
            builder.uniform("turbulenceFactor", turbulenceFactor)
            builder.child("colormap", makeMultiStopHorizontalGradientShader(colors, 256f))
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates random noise.
 * Replicates the JH-Labs NoiseFilter.
 *
 * @param amount the noise amount 0-255 (default 25)
 * @param distribution the noise distribution: 0=UNIFORM, 1=GAUSSIAN (default 0)
 * @param monochrome true for grayscale noise (default false)
 * @param density the noise density 0-1 (default 1.0)
 */
class NoiseFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amount;
            uniform int distribution;
            uniform int monochrome;
            uniform float density;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float n = noise2(fragcoord * density) * 2.0 - 1.0;
                // Gaussian distribution approximation
                if (distribution == 1) {
                    float n2 = noise2(fragcoord * density + vec2(17.0, 17.0)) * 2.0 - 1.0;
                    float n3 = noise2(fragcoord * density + vec2(31.0, 37.0)) * 2.0 - 1.0;
                    n = (n + n2 + n3) / 3.0;
                }
                float f = amount / 255.0;
                half3 rgb;
                if (monochrome == 1) {
                    float v = c.r * 0.3 + c.g * 0.59 + c.b * 0.11;
                    v += n * f;
                    v = clamp(v, 0.0, 1.0);
                    rgb = half3(v, v, v);
                } else {
                    rgb = c.rgb + n * f;
                    rgb = clamp(rgb, 0.0, 1.0);
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        amount: Int = 25,
        distribution: Int = 0,
        monochrome: Boolean = false,
        density: Float = 1f
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("amount", amount)
            builder.uniform("distribution", distribution)
            builder.uniform("monochrome", if (monochrome) 1 else 0)
            builder.uniform("density", density)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which creates an oil painting effect.
 * Replicates the JH-Labs OilFilter.
 *
 * @param range the neighborhood range (default 3)
 * @param levels the number of color levels (default 256)
 */
class OilFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int range;
            uniform int levels;
            half4 main(vec2 fragcoord) {
                int bins = min(levels, 20);
                if (bins < 1) bins = 1;
                half counts[20];
                half3 sums[20];
                for (int i = 0; i < 20; i++) {
                    counts[i] = 0.0;
                    sums[i] = half3(0.0);
                }
                int count = 0;
                for (int dy = -range; dy <= range; dy++) {
                    for (int dx = -range; dx <= range; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        int bin = int(c.r * float(bins));
                        if (bin >= 20) bin = 19;
                        counts[bin] += 1.0;
                        sums[bin] += c.rgb;
                        count++;
                    }
                }
                int maxBin = 0;
                half maxCount = 0.0;
                for (int i = 0; i < 20; i++) {
                    if (counts[i] > maxCount) {
                        maxCount = counts[i];
                        maxBin = i;
                    }
                }
                half3 rgb = sums[maxBin] / max(maxCount, 0.001);
                half4 orig = content.eval(fragcoord);
                return half4(rgb, orig.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        range: Int = 3,
        levels: Int = 256
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("range", range)
            builder.uniform("levels", levels)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates a plasma texture overlay.
 * Replicates the JH-Labs PlasmaFilter.
 */
class PlasmaFilter() : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float scaling;
            uniform float turbulence;
            uniform int seed;
            uniform int useColormap;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float x = fragcoord.x;
                float y = fragcoord.y;
                // Plasma value from sine waves
                float v = (sin(x * 0.05) + sin(y * 0.05) + sin((x + y) * 0.05) + sin(sqrt(x * x + y * y) * 0.05)) * 0.25;
                // Add turbulence
                if (turbulence > 0.0) {
                    v += turbulence * (noise2(fragcoord * 0.01 + vec2(float(seed), float(seed))) * 2.0 - 1.0);
                }
                // Apply scaling displacement
                if (scaling > 0.0) {
                    v += scaling * (noise2(fragcoord * 0.02) * 2.0 - 1.0);
                }
                // Normalize to 0..1 range
                v = v * 0.5 + 0.5;
                v = clamp(v, 0.0, 1.0);
                half3 plasma;
                if (useColormap == 0) {
                    // Displace image colors
                    float dx = v * scaling;
                    float dy = (noise2(fragcoord * 0.02 + vec2(float(seed), float(seed * 2))) * 2.0 - 1.0) * scaling;
                    plasma = content.eval(fragcoord + vec2(dx, dy)).rgb;
                } else {
                    half4 colormapEval = colormap.eval(vec2(v * 256, 0));
                    plasma = half3(colormapEval.r, colormapEval.g, colormapEval.b);
                    // TODO: figure out proper handling for alpha premultiplication
                    if (colormapEval.a != 0.0) {
                        plasma = plasma / colormapEval.a;
                    }
                }
                return half4(plasma, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

//    fun makeShader(inputShader: org.jetbrains.skia.Shader?): org.jetbrains.skia.Shader {
//        val dataBuffer = ByteBuffer.allocate(68).order(ByteOrder.LITTLE_ENDIAN)
//        dataBuffer.putFloat(scaling)
//        dataBuffer.putFloat(turbulence)
//        dataBuffer.putInt(seed)
//        dataBuffer.putInt(if (useColormap) 1 else 0)
//        dataBuffer.putInt(if (useImageColors) 1 else 0)
//        dataBuffer.putFloat(((color1 shr 16) and 0xff) / 255f)
//        dataBuffer.putFloat(((color1 shr 8) and 0xff) / 255f)
//        dataBuffer.putFloat((color1 and 0xff) / 255f)
//        dataBuffer.putFloat(((color2 shr 16) and 0xff) / 255f)
//        dataBuffer.putFloat(((color2 shr 8) and 0xff) / 255f)
//        dataBuffer.putFloat((color2 and 0xff) / 255f)
//        dataBuffer.putFloat(((color3 shr 16) and 0xff) / 255f)
//        dataBuffer.putFloat(((color3 shr 8) and 0xff) / 255f)
//        dataBuffer.putFloat((color3 and 0xff) / 255f)
//        dataBuffer.putFloat(((color4 shr 16) and 0xff) / 255f)
//        dataBuffer.putFloat(((color4 shr 8) and 0xff) / 255f)
//        dataBuffer.putFloat((color4 and 0xff) / 255f)
//        return effect.makeShader(
//            uniforms = Data.makeFromBytes(dataBuffer.array()),
//            children = arrayOf(inputShader),
//            localMatrix = null
//        )
//    }

    fun renderEffect(
        turbulence: Float = 1.0f,
        scaling: Float = 0.0f,
        seed: Int = 567,
        colors: List<Color>? = null
    ): RenderEffect {
        val plasmaShaderBuilder = RuntimeShaderBuilder(this.effect)
        plasmaShaderBuilder.uniform("turbulence", turbulence)
        plasmaShaderBuilder.uniform("scaling", scaling)
        plasmaShaderBuilder.uniform("seed", seed)
        plasmaShaderBuilder.uniform("useColormap", if (colors != null) 1 else 0)

        val gradientFilter: ImageFilter? = colors?.let {
            makeMultiStopHorizontalGradientFilter(
                stops = it,
                width = 256)
        }

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = plasmaShaderBuilder,
            shaderNames = arrayOf("content", "colormap"),
            inputs = arrayOf(null, gradientFilter)
        ).asComposeRenderEffect()
    }

    fun imageFilter(
        size: FloatPair,
        turbulence: Float = 1.0f,
        scaling: Float = 0.0f,
        seed: Int = 567,
        colors: List<Color>? = null
    ): ImageFilter {
        val plasmaShaderBuilder = RuntimeShaderBuilder(this.effect)
        plasmaShaderBuilder.uniform("turbulence", turbulence)
        plasmaShaderBuilder.uniform("scaling", scaling)
        plasmaShaderBuilder.uniform("seed", seed)
        plasmaShaderBuilder.uniform("useColormap", if (colors != null) 1 else 0)

        val gradientFilter: ImageFilter? = colors?.let {
            makeMultiStopHorizontalGradientFilter(
                stops = it,
                width = 256)
        }

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = plasmaShaderBuilder,
            shaderNames = arrayOf("content", "colormap"),
            inputs = arrayOf(null, gradientFilter)
        )
    }

    fun effectModifier(
        turbulence: Float = 1.0f,
        scaling: Float = 0.0f,
        seed: Int = 567,
        colors: List<Color>? = null
    ): Modifier {
        val plasmaShaderBuilder = RuntimeShaderBuilder(this.effect)
        plasmaShaderBuilder.uniform("turbulence", turbulence)
        plasmaShaderBuilder.uniform("scaling", scaling)
        plasmaShaderBuilder.uniform("seed", seed)
        plasmaShaderBuilder.uniform("useColormap", if (colors != null) 1 else 0)

        val gradientFilter: ImageFilter? = colors?.let {
            makeMultiStopHorizontalGradientFilter(
                stops = it,
                width = 256)
        }

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = plasmaShaderBuilder,
            shaderNames = arrayOf("content", "colormap"),
            inputs = arrayOf(null, gradientFilter)
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}

/**
 * A filter which generates a Barnsley fern fractal texture.
 * Replicates the JH-Labs QuiltFilter.
 *
 * @param seed the random seed (default 567)
 * @param iterations the number of iterations (default 25000)
 * @param a the IFS coefficient a (default -0.59f)
 * @param b the IFS coefficient b (default 0.2f)
 * @param c the IFS coefficient c (default 0.1f)
 * @param d the IFS coefficient d (default 0.0f)
 * @param k the IFS coefficient k (default 0)
 * @param colors the colormap colors (default green to dark green)
 */
class QuiltFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader colormap;
            uniform int seed;
            uniform int iterations;
            uniform float a;
            uniform float b;
            uniform float c;
            uniform float d;
            uniform int k;
            uniform vec2 imageSize;
            float hash(int n) {
                return fract(sin(float(n)) * 43758.5453);
            }
            half4 main(vec2 fragcoord) {
                // Create a density map using IFS (Iterated Function System)
                float px = fragcoord.x / imageSize.x;
                float py = fragcoord.y / imageSize.y;

                // Initialize density grid
                float density = 0.0;
                float x = 0.0;
                float y = 0.0;
                int intSeed = seed;

                for (int i = 0; i < 100; i++) {
                    if (i >= iterations) break;
                    float r = hash(intSeed + i);
                    // Barnsley fern IFS transformation
                    float newX, newY;
                    if (r < 0.01) {
                        newX = 0.0;
                        newY = 0.0;
                    } else if (r < 0.86) {
                        newX = a * x;
                        newY = b * y;
                    } else if (r < 0.93) {
                        newX = c * x + d * y;
                        newY = -d * x + c * y;
                    } else {
                        newX = d * x + c * y;
                        newY = -b * x + a * y;
                    }
                    x = newX;
                    y = newY;

                    // Check if point is near current pixel
                    float sx = (x + 1.0) * 0.5;
                    float sy = y * 0.5 + 0.1;
                    float dist = length(vec2(sx, sy) - vec2(px, py));
                    if (dist < 0.005) {
                        density += 1.0;
                    }
                }

                // Normalize density
                density /= 100.0;
                density = clamp(density, 0.0, 1.0);

                return colormap.eval(vec2(density * 256.0, 0.0));
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        seed: Int = 567,
        iterations: Int = 25000,
        a: Float = -0.59f,
        b: Float = 0.2f,
        c: Float = 0.1f,
        d: Float = 0f,
        k: Int = 0,
        colors: List<Color> = listOf(Color(0xFF00FF00), Color(0xFF006400)),
        imageWidth: Float = 640f,
        imageHeight: Float = 480f
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("seed", seed)
            builder.uniform("iterations", iterations)
            builder.uniform("a", a)
            builder.uniform("b", b)
            builder.uniform("c", c)
            builder.uniform("d", d)
            builder.uniform("k", k)
            builder.uniform("imageSize", size.width, size.height)
            builder.child("colormap", makeMultiStopHorizontalGradientShader(colors, 256f))
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates random scratch lines overlay.
 * Replicates the JH-Labs ScratchFilter.
 *
 * @param density the density of scratches (default 0.1f)
 * @param angle the base angle of scratches in radians (default 0)
 * @param angleVariation the variation of angle (default 1.0f)
 * @param width the line width (default 0.5f)
 * @param length the scratch length factor (default 0.5f)
 * @param color the scratch color as 0xRRGGBB (default white)
 * @param seed the random seed (default 0)
 */
class ScratchFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float density;
            uniform float angle;
            uniform float angleVariation;
            uniform float width;
            uniform float length;
            uniform half3 color;
            uniform int seed;
            uniform vec2 imageSize;
            float hash(vec2 p, int s) {
                return fract(sin(dot(p, vec2(127.1 + s, 311.7 + s * 2))) * 43758.5453 + s);
            }
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float w = imageSize.x;
                float h = imageSize.y;
                float numScratches = density * w * h / 100.0;
                float l = length * w;
                float alpha = 0.0;
                // Check nearby scratch lines
                for (int i = 0; i < 20; i++) {
                    float fi = float(i) + seed;
                    float sx = hash(vec2(fi, fi * 1.1), seed) * w;
                    float sy = hash(vec2(fi * 1.3, fi * 1.7), seed) * h;
                    float a = angle + 6.2831853 * angleVariation * (hash(vec2(fi * 2.0, fi * 3.0), seed) - 0.5);
                    float s_sin = sin(a) * l;
                    float s_cos = cos(a) * l;
                    float x1 = sx - s_cos;
                    float y1 = sy - s_sin;
                    float x2 = sx + s_cos;
                    float y2 = sy + s_sin;
                    // Distance from point to line segment
                    float dx = x2 - x1;
                    float dy = y2 - y1;
                    float len2 = dx * dx + dy * dy;
                    float t = clamp(((fragcoord.x - x1) * dx + (fragcoord.y - y1) * dy) / len2, 0.0, 1.0);
                    float px = x1 + t * dx;
                    float py = y1 + t * dy;
                    float dist = length(fragcoord - vec2(px, py));
                    if (dist < width) {
                        alpha = max(alpha, 1.0 - dist / width);
                    }
                }
                half3 rgb = mix(c.rgb, color, alpha);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        density: Float = 0.1f,
        angle: Float = 0f,
        angleVariation: Float = 1.0f,
        width: Float = 0.5f,
        length: Float = 0.5f,
        color: Color = Color(0xFFFFFF),
        seed: Int = 0
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("density", density)
            builder.uniform("angle", angle)
            builder.uniform("angleVariation", angleVariation)
            builder.uniform("width", width)
            builder.uniform("length", length)
            builder.uniform("color", color.red, color.green, color.blue)
            builder.uniform("seed", seed)
            builder.uniform("imageSize", size.width, size.height)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which generates a sky with clouds, sun glow, and atmospheric haze.
 * Replicates the JH-Labs SkyFilter.
 *
 * @param scale the scale of the cloud pattern (default 0.1f)
 * @param stretch the vertical stretch of the cloud pattern (default 1.0f)
 * @param angle the rotation angle of the cloud pattern (default 0.0f)
 * @param amount the cloud overlay intensity (default 1.0f)
 * @param H the H parameter for FBM (default 1.0f)
 * @param octaves the number of FBM octaves (default 8.0f)
 * @param lacunarity the FBM lacunarity (default 2.0f)
 * @param gain the exposure gain (default 1.0f)
 * @param bias the FBM bias (default 0.6f)
 * @param cloudCover the cloud cover threshold (default 0.5f)
 * @param cloudSharpness the cloud edge sharpness (default 0.5f)
 * @param time the time offset for animation (default 0.3f)
 * @param glow the sun glow intensity (default 0.5f)
 * @param glowFalloff the sun glow falloff exponent (default 0.5f)
 * @param haziness the atmospheric haziness (default 0.96f)
 * @param t the time offset for noise animation (default 0.0f)
 * @param sunColor the sun color as 0xRRGGBB (default 0xFFFFFFFF white)
 * @param sunAzimuth the sun horizontal position 0-1 (default 0.5f)
 * @param sunElevation the sun vertical position 0-1 (default 0.5f)
 * @param windSpeed the wind speed affecting cloud movement (default 0.0f)
 * @param cameraAzimuth the camera azimuth angle (default 0.0f)
 * @param cameraElevation the camera elevation angle (default 0.0f)
 * @param fov the field of view (default 1.0f)
 */
class SkyFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float amount;
            uniform float H;
            uniform float octaves;
            uniform float lacunarity;
            uniform float gain;
            uniform float bias;
            uniform float cloudCover;
            uniform float cloudSharpness;
            uniform float time;
            uniform float glow;
            uniform float glowFalloff;
            uniform float haziness;
            uniform float t;
            uniform half3 sunColor;
            uniform float sunAzimuth;
            uniform float sunElevation;
            uniform float windSpeed;
            uniform float cameraAzimuth;
            uniform float cameraElevation;
            uniform float fov;
            uniform vec2 imageSize;
            $NOISE_FUNC
            float noise3(vec3 p) {
                // Simple 3D value noise using hash
                vec3 i = floor(p);
                vec3 f = fract(p);
                f = f * f * (3.0 - 2.0 * f);
                float n = i.x + i.y * 157.0 + 113.0 * i.z;
                float a = fract(sin(n) * 43758.5453);
                float b = fract(sin(n + 1.0) * 43758.5453);
                float c = fract(sin(n + 157.0) * 43758.5453);
                float d = fract(sin(n + 158.0) * 43758.5453);
                float e = fract(sin(n + 113.0) * 43758.5453);
                float f2 = fract(sin(n + 114.0) * 43758.5453);
                float g = fract(sin(n + 270.0) * 43758.5453);
                float h = fract(sin(n + 271.0) * 43758.5453);
                float v1 = mix(mix(mix(a, b, f.x), mix(c, d, f.x), f.y), mix(mix(e, f2, f.x), mix(g, h, f.x), f.y), f.z);
                return v1;
            }
            float evaluate(float x, float y, float timeT) {
                float value = 0.0;
                // to prevent cascading effects
                x += 371.0;
                y += 529.0;
                int intOctaves = int(octaves);
                float frequency = 1.0;
                for (int i = 0; i <= 10; i++) {
                    if (i > intOctaves) {
                        break;
                    }
                    float exponent = pow(2.0, -float(i));
                    value += noise3(vec3(x, y, timeT)) * exponent;
                    x *= lacunarity;
                    y *= lacunarity;
                    frequency *= lacunarity;
                }
                float remainder = octaves - float(intOctaves);
                if (remainder > 0.0) {
                    int idx = intOctaves;
                    float exponent = pow(2.0, -float(idx));
                    value += remainder * noise3(vec3(x, y, timeT)) * exponent;
                }
                return value;
            }
            half4 main(vec2 fragcoord) {
                float width = imageSize.x;
                float height = imageSize.y;
                float x = fragcoord.x;
                float y = fragcoord.y;

                // Sky gradient background (approximation of SkyColors.png lookup)
                float fy = y / height;
                // Time-based sky color index
                int tIdx = int(63.0 * time);
                // Approximate sky gradient based on time of day
                half3 skyTop, skyBottom;
                if (tIdx < 16) {
                    // Night to dawn
                    skyTop = half3(0.02, 0.02, 0.08);
                    skyBottom = half3(0.1, 0.05, 0.15);
                } else if (tIdx < 32) {
                    // Dawn to day
                    skyTop = half3(0.2, 0.4, 0.8);
                    skyBottom = half3(0.6, 0.7, 0.9);
                } else if (tIdx < 48) {
                    // Day to dusk
                    skyTop = half3(0.15, 0.3, 0.6);
                    skyBottom = half3(0.8, 0.4, 0.2);
                } else {
                    // Dusk to night
                    skyTop = half3(0.05, 0.05, 0.15);
                    skyBottom = half3(0.15, 0.08, 0.1);
                }
                half3 sky = mix(skyBottom, skyTop, fy);
                half r = sky.r;
                half g = sky.g;
                half b = sky.b;

                // FOV distortion
                float fx = x / width;
                float ny = tan(fov * fy * 3.14159265 * 0.5);
                float nx = (fx - 0.5) * (1.0 + ny);
                ny += t * windSpeed;

                // Scale and stretch
                nx /= scale;
                ny /= scale * stretch;

                // FBM noise for clouds
                float f = evaluate(nx, ny, t);
                // Normalize to 0..1
                f = (f + 1.23) / 2.46;

                // Cloud cover
                float c = f - cloudCover;
                if (c < 0.0) c = 0.0;
                float cloudAlpha = 1.0 - pow(cloudSharpness, c);

                // Sun glow
                float centreX = width * sunAzimuth;
                float centreY = height * sunElevation;
                float dx = x - centreX;
                float dy = y - centreY;
                float distance2 = dx * dx + dy * dy;
                distance2 = pow(distance2, glowFalloff);
                float sun = 10.0 * exp(-distance2 * glow * 0.1);

                // Sun glow onto sky
                r += sun * sunColor.r;
                g += sun * sunColor.g;
                b += sun * sunColor.b;

                // Clouds get darker as they get thicker
                float ca = (1.0 - cloudAlpha * cloudAlpha * cloudAlpha * cloudAlpha) * amount;
                float cloudR = sunColor.r * ca;
                float cloudG = sunColor.g * ca;
                float cloudB = sunColor.b * ca;

                // Apply haziness
                float haze = pow(haziness, 100.0 * fy * fy);
                cloudAlpha *= haze;

                // Composite clouds on sky
                float iCloudAlpha = 1.0 - cloudAlpha;
                r = iCloudAlpha * r + cloudAlpha * cloudR;
                g = iCloudAlpha * g + cloudAlpha * cloudG;
                b = iCloudAlpha * b + cloudAlpha * cloudB;

                // Exposure
                float exposure = gain;
                r = 1.0 - exp(-r * exposure);
                g = 1.0 - exp(-g * exposure);
                b = 1.0 - exp(-b * exposure);

                return half4(clamp(r, 0.0, 1.0), clamp(g, 0.0, 1.0), clamp(b, 0.0, 1.0), 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        scale: Float = 0.1f,
        stretch: Float = 1.0f,
        angle: Float = 0.0f,
        amount: Float = 1.0f,
        H: Float = 1.0f,
        octaves: Float = 8.0f,
        lacunarity: Float = 2.0f,
        gain: Float = 1.0f,
        bias: Float = 0.6f,
        cloudCover: Float = 0.5f,
        cloudSharpness: Float = 0.5f,
        time: Float = 0.3f,
        glow: Float = 0.5f,
        glowFalloff: Float = 0.5f,
        haziness: Float = 0.96f,
        t: Float = 0.0f,
        sunColor: Int = -1,
        sunAzimuth: Float = 0.5f,
        sunElevation: Float = 0.5f,
        windSpeed: Float = 0.0f,
        cameraAzimuth: Float = 0.0f,
        cameraElevation: Float = 0.0f,
        fov: Float = 1.0f,
        imageWidth: Float = 640f,
        imageHeight: Float = 480f
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("scale", scale)
            builder.uniform("stretch", stretch)
            builder.uniform("angle", angle)
            builder.uniform("amount", amount)
            builder.uniform("H", H)
            builder.uniform("octaves", octaves)
            builder.uniform("lacunarity", lacunarity)
            builder.uniform("gain", gain)
            builder.uniform("bias", bias)
            builder.uniform("cloudCover", cloudCover)
            builder.uniform("cloudSharpness", cloudSharpness)
            builder.uniform("time", time)
            builder.uniform("glow", glow)
            builder.uniform("glowFalloff", glowFalloff)
            builder.uniform("haziness", haziness)
            builder.uniform("t", t)
            builder.uniform("sunColor", ((sunColor shr 16) and 0xFF) / 255f, ((sunColor shr 8) and 0xFF) / 255f, (sunColor and 0xFF) / 255f)
            builder.uniform("sunAzimuth", sunAzimuth)
            builder.uniform("sunElevation", sunElevation)
            builder.uniform("windSpeed", windSpeed)
            builder.uniform("cameraAzimuth", cameraAzimuth)
            builder.uniform("cameraElevation", cameraElevation)
            builder.uniform("fov", fov)
            builder.uniform("imageSize", size.width, size.height)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which creates a rubber-stamp type of effect by performing a thresholded Gaussian blur.
 * Replicates the JH-Labs StampFilter.
 *
 * The algorithm first blurs the source image with a Gaussian kernel whose radius is controlled
 * by the [radius] parameter, then thresholds each pixel's brightness (R+G+B sum) to produce
 * a high-contrast stamp effect. The [threshold] controls the cutoff point and [softness]
 * controls the transition width via smoothstep.
 *
 * @param threshold the brightness threshold in the range 0..1 (default 0.5f)
 * @param softness the edge softness in the range 0..1 (default 0f)
 * @param radius the Gaussian blur radius in pixels (default 5)
 * @param white the color for pixels above the upper threshold (default white)
 * @param black the color for pixels below the lower threshold (default black)
 */
class StampFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader blurred;
            uniform float threshold;
            uniform float softness;
            uniform half3 white;
            uniform half3 black;
            half4 main(vec2 fragcoord) {
                vec4 blurredContent = blurred.eval(fragcoord);
                float blurR = blurredContent.r;
                float blurG = blurredContent.g;
                float blurB = blurredContent.b;
                
                // Threshold the blurred brightness, matching JH-Labs logic
                half l = 0.2126 * blurR + 0.7152 * blurG + 0.0722 * blurB;
                half lowerThreshold = (threshold - softness * 0.5);
                half upperThreshold = (threshold + softness * 0.5);
                float f = smoothstep(lowerThreshold, upperThreshold, l);
                
                half3 rgb = mix(black, white, f);
                half4 orig = content.eval(fragcoord);
                return half4(rgb, orig.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun imageFilter(
        size: FloatPair,
        threshold: Float = 0.5f,
        softness: Float = 0f,
        radius: Float = 5f,
        white: Color = Color.White,
        black: Color = Color.Black
    ): ImageFilter {
        val stampShaderBuilder = RuntimeShaderBuilder(this@StampFilter.effect)
        stampShaderBuilder.uniform("threshold", threshold)
        stampShaderBuilder.uniform("softness", softness)
        stampShaderBuilder.uniform("radius", radius)
        stampShaderBuilder.uniform("white", white.red, white.green, white.blue)
        stampShaderBuilder.uniform("black", black.red, black.green, black.blue)

        val blurImageFilter = ImageFilter.makeBlur(
            sigmaX = radius, sigmaY = radius, mode = FilterTileMode.DECAL)

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = stampShaderBuilder,
            shaderNames = arrayOf("content", "blurred"),
            inputs = arrayOf(null, blurImageFilter)
        )
    }

    @Composable
    fun effectModifier(
        threshold: Float = 0.5f,
        softness: Float = 0f,
        radius: Float = 5f,
        white: Color = Color.White,
        black: Color = Color.Black
    ): Modifier {
        return Modifier
            .graphicsLayer {
                val stampShaderBuilder = RuntimeShaderBuilder(this@StampFilter.effect)
                stampShaderBuilder.uniform("threshold", threshold)
                stampShaderBuilder.uniform("softness", softness)
                stampShaderBuilder.uniform("radius", radius)
                stampShaderBuilder.uniform("white", white.red, white.green, white.blue)
                stampShaderBuilder.uniform("black", black.red, black.green, black.blue)

                val blurImageFilter = ImageFilter.makeBlur(
                    sigmaX = radius, sigmaY = radius, mode = FilterTileMode.DECAL)

                renderEffect = ImageFilter.makeRuntimeShader(
                    runtimeShaderBuilder = stampShaderBuilder,
                    shaderNames = arrayOf("content", "blurred"),
                    inputs = arrayOf(null, blurImageFilter)
                ).asComposeRenderEffect()
                clip = true
            }
    }
}

/**
 * A filter which generates a generic procedural texture overlay.
 * Replicates the JH-Labs TextureFilter.
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param amount the overlay intensity (default 1.0f)
 * @param turbulence the turbulence amount (default 0.0f)
 * @param colors optional colormap colors; when provided, the texture value is mapped through the gradient
 */
class TextureFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float amount;
            uniform float turbulence;
            uniform int useColormap;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                nx /= scale * stretch;
                ny /= scale;

                float n = noise2(vec2(nx, ny));
                // Add turbulence
                if (turbulence > 0.0) {
                    n += turbulence * (noise2(vec2(nx * 5.0, ny * 5.0)) * 2.0 - 1.0);
                }
                n = n * 0.5 + 0.5;
                n = clamp(n, 0.0, 1.0);
                half3 rgb;
                if (useColormap == 1) {
                    half4 colormapEval = colormap.eval(vec2(n * 256.0, 0.0));
                    rgb = colormapEval.rgb;
                    if (colormapEval.a != 0.0) {
                        rgb = rgb / colormapEval.a;
                    }
                    rgb = mix(c.rgb, rgb, amount);
                } else {
                    half3 tex = half3(n);
                    rgb = mix(c.rgb, tex, amount);
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        scale: Float = 32f,
        stretch: Float = 1f,
        angle: Float = 0f,
        amount: Float = 1f,
        turbulence: Float = 0f,
        colors: List<Color>
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("scale", scale)
            builder.uniform("stretch", stretch)
            builder.uniform("angle", angle)
            builder.uniform("amount", amount)
            builder.uniform("turbulence", turbulence)
            builder.uniform("useColormap", if (colors != null) 1 else 0)
            builder.child("colormap", makeMultiStopHorizontalGradientShader(colors, 256f))
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which creates a weave/interlaced effect.
 * Replicates the JH-Labs WeaveFilter.
 *
 * @param xWidth the horizontal thread width (default 16)
 * @param yWidth the vertical thread width (default 16)
 * @param xGap the horizontal gap between threads (default 6)
 * @param yGap the vertical gap between threads (default 6)
 * @param rows the number of rows (default 4)
 * @param cols the number of columns (default 4)
 * @param rgbX the horizontal thread color as 0xRRGGBB (default 0xffff8080)
 * @param rgbY the vertical thread color as 0xRRGGBB (default 0xff8080ff)
 * @param useImageColors true to use image colors (default true)
 * @param roundThreads true for rounded threads (default false)
 * @param shadeCrossings true to shade thread crossings (default true)
 */
class WeaveFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float xWidth;
            uniform float yWidth;
            uniform float xGap;
            uniform float yGap;
            uniform int rows;
            uniform int cols;
            uniform half3 rgbX;
            uniform half3 rgbY;
            uniform int useImageColors;
            uniform int roundThreads;
            uniform int shadeCrossings;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                // Horizontal threads
                float xPhase = fract(fragcoord.x / (xWidth + xGap));
                float yPhase = fract(fragcoord.y / (yWidth + yGap));
                int isXThread = (xPhase < xWidth / (xWidth + xGap)) ? 1 : 0;
                int isYThread = (yPhase < yWidth / (yWidth + yGap)) ? 1 : 0;

                // Determine weave pattern
                int cellX = int(fragcoord.x / (xWidth + xGap));
                int cellY = int(fragcoord.y / (yWidth + yGap));
                int over = ((cellX + cellY) % 2 == 0) ? 1 : 0;

                half3 rgb = c.rgb;
                if (isXThread == 1 && isYThread == 1) {
                    // Crossing point
                    if (shadeCrossings == 1) {
                        rgb *= 0.8;
                    }
                } else if (isXThread == 1) {
                    if (useImageColors == 0) {
                        rgb = mix(rgb, rgbX, 0.3);
                    }
                } else if (isYThread == 1) {
                    if (useImageColors == 0) {
                        rgb = mix(rgb, rgbY, 0.3);
                    }
                }

                // Round thread edges
                if (roundThreads == 1) {
                    float edgeX = min(xPhase, 1.0 - xPhase) * (xWidth + xGap);
                    float edgeY = min(yPhase, 1.0 - yPhase) * (yWidth + yGap);
                    float roundFactor = smoothstep(0.0, 0.5, min(edgeX, edgeY));
                    rgb = mix(c.rgb, rgb, roundFactor);
                }

                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        xWidth: Float = 16f,
        yWidth: Float = 16f,
        xGap: Float = 6f,
        yGap: Float = 6f,
        rows: Int = 4,
        cols: Int = 4,
        rgbX: Color = Color(0xFFFF8080),
        rgbY: Color = Color(0xFF8080FF),
        useImageColors: Boolean = true,
        roundThreads: Boolean = false,
        shadeCrossings: Boolean = true
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("xWidth", xWidth)
            builder.uniform("yWidth", yWidth)
            builder.uniform("xGap", xGap)
            builder.uniform("yGap", yGap)
            builder.uniform("rows", rows)
            builder.uniform("cols", cols)
            builder.uniform("rgbX", rgbX.red, rgbX.green, rgbX.blue)
            builder.uniform("rgbY", rgbY.red, rgbY.green, rgbY.blue)
            builder.uniform("useImageColors", if (useImageColors) 1 else 0)
            builder.uniform("roundThreads", if (roundThreads) 1 else 0)
            builder.uniform("shadeCrossings", if (shadeCrossings) 1 else 0)
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

/**
 * A filter which produces a simulated wood texture.
 * This replicates the JH-Labs Java Image Filters WoodFilter algorithm.
 *
 * @param scale the scale of the texture (default 200.0, range 1..300+)
 * @param stretch the stretch factor of the texture (default 10.0, range 1..50+)
 * @param angle the angle of the texture in radians (default PI/2)
 * @param rings the rings value (default 0.5, range 0..1)
 * @param turbulence the turbulence of the texture (default 0.0, range 0..1)
 * @param fibres the amount of fibres in the texture (default 0.5, range 0..1)
 * @param gain the gain of the texture (default 0.8, range 0..1)
 * @param color1 the first colormap color as RGBA float4 (default light tan)
 * @param color2 the second colormap color as RGBA float4 (default dark brown)
 */
class WoodFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader colormap;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float rings;
            uniform float turbulence;
            uniform float fibres;
            uniform float gain;
            $NOISE_FUNC
            float smoothStep(float a, float b, float x) {
                if (x < a) return 0.0;
                if (x >= b) return 1.0;
                x = (x - a) / (b - a);
                return x * x * (3.0 - 2.0 * x);
            }
            half4 main(vec2 fragcoord) {
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;

                // Scale coordinates
                nx /= scale;
                ny /= scale * stretch;

                // Base noise
                float f = noise2(vec2(nx, ny));

                // Turbulence overlay
                f += 0.1 * turbulence * noise2(vec2(nx * 0.05, ny * 20.0));

                // Normalize to 0..1
                f = f * 0.5 + 0.5;

                // Ring pattern - fractional part
                f *= rings * 50.0;
                f = f - floor(f);

                // Smooth step to create ring edges
                f *= 1.0 - smoothStep(gain, 1.0, f);

                // Fiber noise overlay
                f += fibres * noise2(vec2(nx * scale, ny * 50.0));

                // Clamp f to [0, 1] for colormap lookup
                f = clamp(f, 0.0, 1.0);

                // Linear colormap interpolation
                return colormap.eval(vec2(f * 256, 0));
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun effectModifier(
        scale: Float = 200f,
        stretch: Float = 10.0f,
        angle: Float = (Math.PI / 2.0).toFloat(),
        rings: Float = 0.5f,
        turbulence: Float = 0.0f,
        fibres: Float = 0.5f,
        gain: Float = 0.8f,
        colors: List<Color> = listOf(Color(0xFFE5C494), Color(0xFF987B51))
    ): Modifier {
        return Modifier.drawWithCache {
            val builder = RuntimeShaderBuilder(effect)
            builder.uniform("scale", scale)
            builder.uniform("stretch", stretch)
            builder.uniform("angle", angle)
            builder.uniform("rings", rings)
            builder.uniform("turbulence", turbulence)
            builder.uniform("fibres", fibres)
            builder.uniform("gain", gain)
            builder.child("colormap", makeMultiStopHorizontalGradientShader(colors, 256f))
            val shader = builder.makeShader()
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}
