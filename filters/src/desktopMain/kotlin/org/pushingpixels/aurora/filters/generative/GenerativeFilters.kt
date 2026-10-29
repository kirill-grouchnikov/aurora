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

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

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
class BrushedMetalFilter(
    val radius: Int = 10,
    val amount: Float = 0.1f,
    val color: FloatArray = floatArrayOf(136f / 255f, 136f / 255f, 136f / 255f, 1f),
    val shine: Float = 0.1f,
    val monochrome: Boolean = true
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            uniform float amount;
            uniform float color[4];
            uniform float shine;
            uniform int monochrome;
            uniform float width;
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
                    float f = shine * sin(fragcoord.x / width * 3.14159265);
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
                        half4 c = content.eval(fragcoord + offset);
                        half3 blurred = c.rgb;
                        float n2 = noise2(fragcoord + offset) * 2.0 - 1.0;
                        if (monochrome == 1) {
                            blurred += n2 * amount;
                        } else {
                            blurred.r += (noise2(fragcoord + offset + vec2(17.0, 0.0)) * 2.0 - 1.0) * amount;
                            blurred.g += (noise2(fragcoord + offset + vec2(0.0, 17.0)) * 2.0 - 1.0) * amount;
                            blurred.b += (noise2(fragcoord + offset + vec2(17.0, 17.0)) * 2.0 - 1.0) * amount;
                        }
                        if (shine > 0.0) {
                            float f2 = shine * sin((fragcoord.x + float(i)) / width * 3.14159265);
                            blurred += half3(f2, f2, f2);
                        }
                        blurred = clamp(blurred, 0.0, 1.0);
                        sum.rgb += blurred;
                    }
                    return half4(sum.rgb / float(r2), 1.0);
                }
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader?, imageWidth: Float = 640f): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        dataBuffer.putFloat(amount)
        for (c in color) dataBuffer.putFloat(c)
        dataBuffer.putFloat(shine)
        dataBuffer.putInt(if (monochrome) 1 else 0)
        dataBuffer.putFloat(imageWidth)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
 * @param bgColor the background color as 0xRRGGBB (default 0xff799fff)
 */
class CausticsFilter(
    val scale: Float = 32f,
    val angle: Float = 0f,
    val brightness: Int = 10,
    val amount: Float = 1f,
    val turbulence: Float = 1f,
    val dispersion: Float = 0f,
    val time: Float = 0f,
    val samples: Int = 2,
    val bgColor: Int = 0xff799fff.toInt()
) : Filter {
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
            uniform half3 bgColor;
            $NOISE_FUNC
            float noise3(vec3 p) {
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
            float turbulence2(float x, float y, float t, float octaves) {
                float f = 0.0;
                float c = cos(t);
                float s = sin(t);
                float xt = x * c - t;
                float yt = y * s - t;
                float tt = c * x - c * t;
                f = noise3(vec3(xt, y, tt));
                return f;
            }
            half4 main(vec2 fragcoord) {
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;

                float x = nx / scale;
                float y = ny / scale;

                // Evaluate caustics with samples
                float sum = 0.0;
                for (int i = 0; i < 8; i++) {
                    if (i >= samples) break;
                    float fi = float(i);
                    float dx = dispersion * (noise2(vec2(fi, 0.0)) * 2.0 - 1.0);
                    float dy = dispersion * (noise2(vec2(0.0, fi)) * 2.0 - 1.0);
                    float f = turbulence2(x + dx, y + dy, time + fi, turbulence);
                    sum += f;
                }
                float v = sum / max(float(samples), 1.0);
                // Normalize to 0..1
                v = v * 0.5 + 0.5;
                // Apply brightness
                v *= brightness / 10.0;
                v = clamp(v, 0.0, 1.0);

                // Background + caustics overlay
                half3 rgb = bgColor * (1.0 - amount) + half3(v, v, v) * amount;
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader?): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(brightness / 10f)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(turbulence)
        dataBuffer.putFloat(dispersion)
        dataBuffer.putFloat(time)
        dataBuffer.putInt(samples)
        dataBuffer.putFloat(((bgColor shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((bgColor shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((bgColor and 0xff) / 255f)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which generates a cellular/Worley noise texture overlay.
 * Replicates the JH-Labs CellularFilter (extends TextureFilter).
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param gradientCoefficient the gradient coefficient (default 0.0f)
 * @param randomness the randomness amount (default 0.0f)
 * @param amount the overlay intensity (default 1.0f)
 */
class CellularFilter(
    val scale: Float = 32f,
    val stretch: Float = 1f,
    val angle: Float = 0f,
    val gradientCoefficient: Float = 0f,
    val randomness: Float = 0f,
    val amount: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float gradientCoefficient;
            uniform float randomness;
            uniform float amount;
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

                vec2 p = vec2(nx, ny);
                vec2 i = floor(p);
                vec2 f = fract(p);
                float minDist = 1.0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        vec2 neighbor = vec2(float(dx), float(dy));
                        vec2 point = neighbor + hash(i + neighbor);
                        // Add randomness
                        if (randomness > 0.0) {
                            point += randomness * (vec2(hash(point * 17.0), hash(point * 31.0)) - 0.5);
                        }
                        float dist = length(f - point);
                        if (dist < minDist) minDist = dist;
                    }
                }
                // Apply gradient coefficient
                if (gradientCoefficient > 0.0) {
                    minDist *= gradientCoefficient * noise2(fragcoord / scale);
                }
                half3 cellular = half3(minDist);
                half3 rgb = mix(c.rgb, cellular, amount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(gradientCoefficient)
        dataBuffer.putFloat(randomness)
        dataBuffer.putFloat(amount)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
class CheckFilter(
    val xScale: Int = 8,
    val yScale: Int = 8,
    val foreground: Int = 0xffffffff.toInt(),
    val background: Int = 0xff000000.toInt(),
    val fuzziness: Int = 0,
    val angle: Float = 0f
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader? = null): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(xScale.toFloat())
        dataBuffer.putFloat(yScale.toFloat())
        dataBuffer.putFloat(((foreground shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((foreground shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((foreground and 0xff) / 255f)
        dataBuffer.putFloat(((background shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((background shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((background and 0xff) / 255f)
        dataBuffer.putFloat(fuzziness.toFloat())
        dataBuffer.putFloat(angle)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which creates a crystallize/faceted effect.
 * Replicates the JH-Labs CrystallizeFilter.
 *
 * @param size the crystal size in pixels (default 10)
 * @param edgeThickness the edge line thickness 0-1 (default 0.4f)
 * @param fadeEdges true to fade edges (default false)
 * @param edgeColor the edge color as 0xRRGGBB (default black)
 */
class CrystallizeFilter(
    val size: Float = 10f,
    val edgeThickness: Float = 0.4f,
    val fadeEdges: Boolean = false,
    val edgeColor: Int = 0xff000000.toInt()
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float size;
            uniform float edgeThickness;
            uniform int fadeEdges;
            uniform half3 edgeColor;
            half4 main(vec2 fragcoord) {
                // Hexagonal grid approximation
                vec2 p = fragcoord / size;
                vec2 a = floor(p);
                vec2 f = fract(p);
                vec2 b = fract(a * size + vec2(0.0, mod(a.x, 2.0) * 0.5) * size) - 0.5;
                vec2 c = f - b;
                float d = length(c);
                vec2 center = a * size + b * size;
                half4 cellColor = content.eval(center);

                // Edge detection
                float edge = 1.0 - smoothstep(edgeThickness * size * 0.5, size * 0.5, d);
                if (fadeEdges == 1) {
                    edge *= (d / (size * 0.5));
                }
                half3 rgb = mix(cellColor.rgb, edgeColor, edge);
                return half4(rgb, cellColor.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(size)
        dataBuffer.putFloat(edgeThickness)
        dataBuffer.putInt(if (fadeEdges) 1 else 0)
        dataBuffer.putFloat(((edgeColor shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((edgeColor shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((edgeColor and 0xff) / 255f)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
 */
class FBMFilter(
    val scale: Float = 32f,
    val stretch: Float = 1f,
    val angle: Float = 0f,
    val amount: Float = 1f,
    val H: Float = 1f,
    val octaves: Float = 4f,
    val lacunarity: Float = 2f,
    val gain: Float = 0.5f,
    val bias: Float = 0.5f,
    val operation: Int = 0,
    val ridged: Boolean = false
) : Filter {
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
            uniform int operation;
            uniform int ridged;
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
                half3 fbm = half3(value);
                half3 rgb;
                if (operation == 0) {
                    rgb = mix(c.rgb, fbm, amount);
                } else if (operation == 1) {
                    rgb = c.rgb - fbm * amount;
                } else {
                    rgb = c.rgb * fbm * amount;
                }
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(H)
        dataBuffer.putFloat(octaves)
        dataBuffer.putFloat(lacunarity)
        dataBuffer.putFloat(gain)
        dataBuffer.putFloat(bias)
        dataBuffer.putInt(operation)
        dataBuffer.putInt(if (ridged) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which generates a gradient fill.
 * Replicates the JH-Labs GradientFilter.
 *
 * @param angle the angle of the gradient in radians (default 0)
 * @param color1 the first color as 0xRRGGBB (default black)
 * @param color2 the second color as 0xRRGGBB (default white)
 * @param repeat whether to repeat the gradient (default false)
 * @param type the gradient type: 0=LINEAR, 1=RADIAL, 2=SQUARE, 3=CONICAL (default 0)
 * @param interpolation the interpolation mode: 0=LINEAR, 1=GAUSSIAN (default 0)
 * @param paintMode the paint mode: 0=NORMAL, 1=ADD, 2=SUBTRACT (default 0)
 */
class GradientFilter(
    val angle: Float = 0f,
    val color1: Int = 0xff000000.toInt(),
    val color2: Int = 0xffffffff.toInt(),
    val repeat: Boolean = false,
    val type: Int = 0,
    val interpolation: Int = 0,
    val paintMode: Int = 0
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float angle;
            uniform half3 color1;
            uniform half3 color2;
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
                half3 rgb = mix(color1, color2, t);
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader? = null, imageWidth: Float = 640f, imageHeight: Float = 480f): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(52).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(((color1 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color1 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color1 and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color2 and 0xff) / 255f)
        dataBuffer.putInt(if (repeat) 1 else 0)
        dataBuffer.putInt(type)
        dataBuffer.putInt(interpolation)
        dataBuffer.putInt(paintMode)
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
 * A filter which generates a marble texture overlay.
 * Replicates the JH-Labs MarbleFilter.
 *
 * @param xScale the x scale factor (default 4.0f)
 * @param yScale the y scale factor (default 4.0f)
 * @param amount the overlay intensity (default 1.0f)
 * @param turbulence the turbulence amount (default 1.0f)
 */
class MarbleFilter(
    val xScale: Float = 4f,
    val yScale: Float = 4f,
    val amount: Float = 1f,
    val turbulence: Float = 1f
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader?): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(xScale)
        dataBuffer.putFloat(yScale)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(turbulence)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
 * @param color1 the first colormap color as 0xRRGGBB (default brown)
 * @param color2 the second colormap color as 0xRRGGBB (default green)
 */
class MarbleTexFilter(
    val scale: Float = 32f,
    val stretch: Float = 1f,
    val angle: Float = 0f,
    val turbulence: Float = 1f,
    val turbulenceFactor: Float = 0.5f,
    val color1: Int = 0xff8B4513.toInt(),
    val color2: Int = 0xff228B22.toInt()
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float turbulence;
            uniform float turbulenceFactor;
            uniform half3 color1;
            uniform half3 color2;
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
                half3 rgb = mix(color1, color2, grn);
                rgb.r *= red;
                rgb.g *= grn;
                rgb.b *= blu;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader? = null): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(turbulence)
        dataBuffer.putFloat(turbulenceFactor)
        dataBuffer.putFloat(((color1 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color1 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color1 and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color2 and 0xff) / 255f)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
class NoiseFilter(
    val amount: Int = 25,
    val distribution: Int = 0,
    val monochrome: Boolean = false,
    val density: Float = 1f
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(amount / 255f)
        dataBuffer.putInt(distribution)
        dataBuffer.putInt(if (monochrome) 1 else 0)
        dataBuffer.putFloat(density)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which creates an oil painting effect.
 * Replicates the JH-Labs OilFilter.
 *
 * @param range the neighborhood range (default 3)
 * @param levels the number of color levels (default 256)
 */
class OilFilter(
    val range: Int = 3,
    val levels: Int = 256
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(range)
        dataBuffer.putInt(levels)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which generates a plasma texture overlay.
 * Replicates the JH-Labs PlasmaFilter.
 *
 * @param scaling the scaling factor (default 0.0f)
 * @param turbulence the turbulence amount (default 0.0f)
 * @param seed the random seed (default 567)
 * @param useColormap true to use colormap colors (default false)
 * @param useImageColors true to use image colors (default false)
 * @param color1 the first colormap color as 0xRRGGBB (default blue)
 * @param color2 the second colormap color as 0xRRGGBB (default green)
 * @param color3 the third colormap color as 0xRRGGBB (default red)
 * @param color4 the fourth colormap color as 0xRRGGBB (default yellow)
 */
class PlasmaFilter(
    val scaling: Float = 0f,
    val turbulence: Float = 0f,
    val seed: Int = 567,
    val useColormap: Boolean = false,
    val useImageColors: Boolean = false,
    val color1: Int = 0xff0000ff.toInt(),
    val color2: Int = 0xff00ff00.toInt(),
    val color3: Int = 0xffff0000.toInt(),
    val color4: Int = 0xffffff00.toInt()
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scaling;
            uniform float turbulence;
            uniform int seed;
            uniform int useColormap;
            uniform int useImageColors;
            uniform half3 color1;
            uniform half3 color2;
            uniform half3 color3;
            uniform half3 color4;
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
                half3 plasma;
                if (useColormap == 1) {
                    // Interpolate through 4-color colormap
                    if (v < 0.333334) {
                        plasma = mix(color1, color2, v * 3.0);
                    } else if (v < 0.666667) {
                        plasma = mix(color2, color3, (v - 0.333333) * 3.0);
                    } else {
                        plasma = mix(color3, color4, (v - 0.666666) * 3.0);
                    }
                } else if (useImageColors == 1) {
                    // Displace image colors
                    float dx = v * scaling;
                    float dy = (noise2(fragcoord * 0.02 + vec2(float(seed), float(seed * 2))) * 2.0 - 1.0) * scaling;
                    plasma = content.eval(fragcoord + vec2(dx, dy)).rgb;
                } else {
                    plasma = mix(color1, color2, v);
                }
                return half4(plasma, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader?): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(68).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scaling)
        dataBuffer.putFloat(turbulence)
        dataBuffer.putInt(seed)
        dataBuffer.putInt(if (useColormap) 1 else 0)
        dataBuffer.putInt(if (useImageColors) 1 else 0)
        dataBuffer.putFloat(((color1 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color1 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color1 and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color2 and 0xff) / 255f)
        dataBuffer.putFloat(((color3 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color3 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color3 and 0xff) / 255f)
        dataBuffer.putFloat(((color4 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color4 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color4 and 0xff) / 255f)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which creates a pointillism effect.
 * Replicates the JH-Labs PointillizeFilter.
 *
 * @param size the dot size in pixels (default 5)
 * @param edgeThickness the edge line thickness 0-1 (default 0.4f)
 * @param fadeEdges true to fade edges (default false)
 * @param edgeColor the edge color as 0xRRGGBB (default black)
 * @param fuzziness the fuzziness amount 0-1 (default 0.1f)
 */
class PointillizeFilter(
    val size: Float = 5f,
    val edgeThickness: Float = 0.4f,
    val fadeEdges: Boolean = false,
    val edgeColor: Int = 0xff000000.toInt(),
    val fuzziness: Float = 0.1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float size;
            uniform float edgeThickness;
            uniform int fadeEdges;
            uniform half3 edgeColor;
            uniform float fuzziness;
            $NOISE_FUNC
            half4 main(vec2 fragcoord) {
                vec2 cell = floor(fragcoord / size) * size;
                half4 avg = content.eval(cell + size * 0.5);
                vec2 local = fragcoord - cell;
                float dist = length(local - size * 0.5);
                // Add fuzziness to dot edges
                float fuzz = fuzziness * (noise2(fragcoord * 0.1) * 2.0 - 1.0);
                float circle = 1.0 - smoothstep(size * 0.3 + fuzz, size * 0.5 + fuzz, dist);
                half4 c = content.eval(fragcoord);
                half3 rgb = mix(c.rgb, avg.rgb, circle);

                // Edge lines
                float edge = 1.0 - smoothstep(edgeThickness * size * 0.5, size * 0.5, dist);
                if (fadeEdges == 1) {
                    edge *= (dist / (size * 0.5));
                }
                rgb = mix(rgb, edgeColor, edge * (1.0 - circle));
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(28).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(size)
        dataBuffer.putFloat(edgeThickness)
        dataBuffer.putInt(if (fadeEdges) 1 else 0)
        dataBuffer.putFloat(((edgeColor shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((edgeColor shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((edgeColor and 0xff) / 255f)
        dataBuffer.putFloat(fuzziness)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
 * @param color1 the first colormap color as 0xRRGGBB (default green)
 * @param color2 the second colormap color as 0xRRGGBB (default dark green)
 */
class QuiltFilter(
    val seed: Int = 567,
    val iterations: Int = 25000,
    val a: Float = -0.59f,
    val b: Float = 0.2f,
    val c: Float = 0.1f,
    val d: Float = 0f,
    val k: Int = 0,
    val color1: Int = 0xff00ff00.toInt(),
    val color2: Int = 0xff006400.toInt()
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int seed;
            uniform int iterations;
            uniform float a;
            uniform float b;
            uniform float c;
            uniform float d;
            uniform int k;
            uniform half3 color1;
            uniform half3 color2;
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

                half3 rgb = mix(color1, color2, density);
                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader? = null, imageWidth: Float = 640f, imageHeight: Float = 480f): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(60).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(seed)
        dataBuffer.putInt(iterations)
        dataBuffer.putFloat(a)
        dataBuffer.putFloat(b)
        dataBuffer.putFloat(c)
        dataBuffer.putFloat(d)
        dataBuffer.putInt(k)
        dataBuffer.putFloat(((color1 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color1 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color1 and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color2 shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color2 and 0xff) / 255f)
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
class ScratchFilter(
    val density: Float = 0.1f,
    val angle: Float = 0f,
    val angleVariation: Float = 1.0f,
    val width: Float = 0.5f,
    val length: Float = 0.5f,
    val color: Int = 0xffffff,
    val seed: Int = 0
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(density)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(angleVariation)
        dataBuffer.putFloat(width)
        dataBuffer.putFloat(length)
        dataBuffer.putFloat(((color shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((color shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((color and 0xff) / 255f)
        dataBuffer.putInt(seed)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
class SkyFilter(
    val scale: Float = 0.1f,
    val stretch: Float = 1.0f,
    val angle: Float = 0.0f,
    val amount: Float = 1.0f,
    val H: Float = 1.0f,
    val octaves: Float = 8.0f,
    val lacunarity: Float = 2.0f,
    val gain: Float = 1.0f,
    val bias: Float = 0.6f,
    val cloudCover: Float = 0.5f,
    val cloudSharpness: Float = 0.5f,
    val time: Float = 0.3f,
    val glow: Float = 0.5f,
    val glowFalloff: Float = 0.5f,
    val haziness: Float = 0.96f,
    val t: Float = 0.0f,
    val sunColor: Int = -1,
    val sunAzimuth: Float = 0.5f,
    val sunElevation: Float = 0.5f,
    val windSpeed: Float = 0.0f,
    val cameraAzimuth: Float = 0.0f,
    val cameraElevation: Float = 0.0f,
    val fov: Float = 1.0f
) : Filter {
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

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader?,
        imageWidth: Float,
        imageHeight: Float
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(108).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(H)
        dataBuffer.putFloat(octaves)
        dataBuffer.putFloat(lacunarity)
        dataBuffer.putFloat(gain)
        dataBuffer.putFloat(bias)
        dataBuffer.putFloat(cloudCover)
        dataBuffer.putFloat(cloudSharpness)
        dataBuffer.putFloat(time)
        dataBuffer.putFloat(glow)
        dataBuffer.putFloat(glowFalloff)
        dataBuffer.putFloat(haziness)
        dataBuffer.putFloat(t)
        // sunColor as half3 (RR/255, GG/255, BB/255)
        val sunR = ((sunColor shr 16) and 0xFF) / 255.0f
        val sunG = ((sunColor shr 8) and 0xFF) / 255.0f
        val sunB = (sunColor and 0xFF) / 255.0f
        dataBuffer.putFloat(sunR)
        dataBuffer.putFloat(sunG)
        dataBuffer.putFloat(sunB)
        dataBuffer.putFloat(sunAzimuth)
        dataBuffer.putFloat(sunElevation)
        dataBuffer.putFloat(windSpeed)
        dataBuffer.putFloat(cameraAzimuth)
        dataBuffer.putFloat(cameraElevation)
        dataBuffer.putFloat(fov)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which creates a stamp/embossed effect.
 * Replicates the JH-Labs StampFilter.
 *
 * @param threshold the stamp threshold (default 0.0f)
 * @param softness the edge softness (default 0.0f)
 * @param radius the stamp radius (default 5)
 * @param white the white color as 0xRRGGBB (default white)
 * @param black the black color as 0xRRGGBB (default black)
 */
class StampFilter(
    val threshold: Float = 0f,
    val softness: Float = 0f,
    val radius: Float = 5f,
    val white: Int = 0xffffffff.toInt(),
    val black: Int = 0xff000000.toInt()
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            uniform float softness;
            uniform float radius;
            uniform half3 white;
            uniform half3 black;
            half4 main(vec2 fragcoord) {
                half center = length(content.eval(fragcoord).rgb);
                half top = length(content.eval(fragcoord + vec2(0.0, -radius)).rgb);
                half left = length(content.eval(fragcoord + vec2(-radius, 0.0)).rgb);
                half shadow = (center - top + center - left) + threshold;
                // Apply softness
                if (softness > 0.0) {
                    shadow = smoothstep(0.5 - softness, 0.5 + softness, shadow);
                } else {
                    shadow = shadow > 0.5 ? 1.0 : 0.0;
                }
                half3 rgb = mix(black, white, shadow);
                half4 orig = content.eval(fragcoord);
                return half4(rgb, orig.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        dataBuffer.putFloat(softness)
        dataBuffer.putFloat(radius)
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
 * A filter which generates a generic procedural texture overlay.
 * Replicates the JH-Labs TextureFilter.
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param amount the overlay intensity (default 1.0f)
 * @param turbulence the turbulence amount (default 0.0f)
 */
class TextureFilter(
    val scale: Float = 32f,
    val stretch: Float = 1f,
    val angle: Float = 0f,
    val amount: Float = 1f,
    val turbulence: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float amount;
            uniform float turbulence;
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
                half3 tex = half3(n);
                half3 rgb = mix(c.rgb, tex, amount);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(turbulence)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
class WeaveFilter(
    val xWidth: Float = 16f,
    val yWidth: Float = 16f,
    val xGap: Float = 6f,
    val yGap: Float = 6f,
    val rows: Int = 4,
    val cols: Int = 4,
    val rgbX: Int = 0xffff8080.toInt(),
    val rgbY: Int = 0xff8080ff.toInt(),
    val useImageColors: Boolean = true,
    val roundThreads: Boolean = false,
    val shadeCrossings: Boolean = true
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader?): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(60).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(xWidth)
        dataBuffer.putFloat(yWidth)
        dataBuffer.putFloat(xGap)
        dataBuffer.putFloat(yGap)
        dataBuffer.putInt(rows)
        dataBuffer.putInt(cols)
        dataBuffer.putFloat(((rgbX shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((rgbX shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((rgbX and 0xff) / 255f)
        dataBuffer.putFloat(((rgbY shr 16) and 0xff) / 255f)
        dataBuffer.putFloat(((rgbY shr 8) and 0xff) / 255f)
        dataBuffer.putFloat((rgbY and 0xff) / 255f)
        dataBuffer.putInt(if (useImageColors) 1 else 0)
        dataBuffer.putInt(if (roundThreads) 1 else 0)
        dataBuffer.putInt(if (shadeCrossings) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
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
class WoodFilter(
    val scale: Float = 200f,
    val stretch: Float = 10.0f,
    val angle: Float = (Math.PI / 2.0).toFloat(),
    val rings: Float = 0.5f,
    val turbulence: Float = 0.0f,
    val fibres: Float = 0.5f,
    val gain: Float = 0.8f,
    val color1: FloatArray = floatArrayOf(229f / 255f, 196f / 255f, 148f / 255f, 1f),
    val color2: FloatArray = floatArrayOf(152f / 255f, 123f / 255f, 81f / 255f, 1f)
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float rings;
            uniform float turbulence;
            uniform float fibres;
            uniform float gain;
            uniform float color1[4];
            uniform float color2[4];
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
                half3 rgb = mix(half3(color1[0], color1[1], color1[2]),
                                half3(color2[0], color2[1], color2[2]), f);

                return half4(rgb, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader?): org.jetbrains.skia.Shader {
        // 7 floats + 2x4 floats for colors = 15 floats = 60 bytes
        val dataBuffer = ByteBuffer.allocate(60).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(rings)
        dataBuffer.putFloat(turbulence)
        dataBuffer.putFloat(fibres)
        dataBuffer.putFloat(gain)
        for (c in color1) dataBuffer.putFloat(c)
        for (c in color2) dataBuffer.putFloat(c)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
