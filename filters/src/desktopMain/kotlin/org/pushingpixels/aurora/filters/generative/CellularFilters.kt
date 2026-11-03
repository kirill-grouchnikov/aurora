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

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asComposeShader
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientShader
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
 * A filter which generates a cellular/Worley noise texture overlay.
 * Replicates the JH-Labs CellularFilter (extends TextureFilter).
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param gradientCoefficient the gradient coefficient (default 0.0f)
 * @param randomness the randomness amount (default 0.0f)
 * @param amount the overlay intensity (default 1.0f)
 * @param colors optional colormap colors; when provided, the cellular value is mapped through the gradient
 */
class CellularFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float gradientCoefficient;
            uniform float randomness;
            uniform float amount;
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
                // Normalize to 0..1
                minDist = clamp(minDist, 0.0, 1.0);
                half3 rgb;
                if (useColormap == 1) {
                    half4 colormapEval = colormap.eval(vec2(minDist * 256.0, 0.0));
                    rgb = colormapEval.rgb;
                    if (colormapEval.a != 0.0) {
                        rgb = rgb / colormapEval.a;
                    }
                    rgb = mix(c.rgb, rgb, amount);
                } else {
                    half3 cellular = half3(minDist);
                    rgb = mix(c.rgb, cellular, amount);
                }
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader? = null,
        scale: Float = 32f,
        stretch: Float = 1f,
        angle: Float = 0f,
        gradientCoefficient: Float = 0f,
        randomness: Float = 0f,
        amount: Float = 1f,
        colors: List<Color>? = null
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(28).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(gradientCoefficient)
        dataBuffer.putFloat(randomness)
        dataBuffer.putFloat(amount)
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

    fun effectModifier(
        scale: Float = 32f,
        stretch: Float = 1f,
        angle: Float = 0f,
        gradientCoefficient: Float = 0f,
        randomness: Float = 0f,
        amount: Float = 1f,
        colors: List<Color>? = null
    ): Modifier {
        return Modifier.drawWithCache {
            val shader = makeShader(inputShader = null, scale = scale, stretch = stretch, angle = angle, gradientCoefficient = gradientCoefficient, randomness = randomness, amount = amount, colors = colors)
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
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
class CrystallizeFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float size;
            uniform float edgeThickness;
            uniform int fadeEdges;
            uniform half3 edgeColor;
            uniform float width;
            uniform float height;

            // Simple hash for deterministic point placement
            float hash(float n) {
                return fract(sin(n) * 43758.5453123);
            }

            half4 main(vec2 fragcoord) {
                // Scale coordinates, offset by 1000 to reduce artifacts around 0,0 (matching original)
                vec2 p = fragcoord / size + 1000.0;

                // Grid cell containing this point
                vec2 grid = floor(p);
                vec2 frac = p - grid;

                // Track the two nearest points
                float d1 = 1e10; // nearest distance
                float d2 = 1e10; // second nearest distance
                vec2 p1 = vec2(0.0); // nearest point location (in grid coords)
                vec2 p2 = vec2(0.0); // second nearest point location

                // Check 3x3 neighborhood of cells
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int cubeX = int(grid.x) + i;
                        int cubeY = int(grid.y) + j;

                        // Hexagonal point placement: one point per cell
                        // Even cubeX: point at (0.75, 0)
                        // Odd cubeX: point at (0.75, 0.5)
                        float px, py;
                        if (mod(float(cubeX), 2.0) == 0.0) {
                            px = 0.75;
                            py = 0.0;
                        } else {
                            px = 0.75;
                            py = 0.5;
                        }

                        // Absolute point position in grid space
                        vec2 pointPos = vec2(float(cubeX) + px, float(cubeY) + py);

                        // Distance from fragment to this point
                        vec2 diff = p - pointPos;
                        float dist = length(diff);

                        // Insertion sort: keep track of two nearest
                        if (dist < d1) {
                            d2 = d1;
                            p2 = p1;
                            d1 = dist;
                            p1 = pointPos;
                        } else if (dist < d2) {
                            d2 = dist;
                            p2 = pointPos;
                        }
                    }
                }

                // Sample the input image at the nearest point's source location
                // Map from grid space back to fragment coordinates: (p1 - 1000) * size
                vec2 srcCoord = (p1 - 1000.0) * size;
                half4 cellColor = content.eval(srcCoord);

                // Edge detection: (f2 - f1) / edgeThickness, then smoothstep
                float f = (d2 - d1) / edgeThickness;
                float edge = smoothstep(0.0, edgeThickness, f);

                if (fadeEdges == 1) {
                    // Blend between nearest cell color and second-nearest cell color
                    vec2 srcCoord2 = (p2 - 1000.0) * size;
                    half4 cellColor2 = content.eval(srcCoord2);
                    half3 blended = mix(cellColor2.rgb, cellColor.rgb, 0.5);
                    half3 rgb = mix(blended, cellColor.rgb, edge);
                    return half4(rgb, cellColor.a);
                } else {
                    half3 rgb = mix(edgeColor, cellColor.rgb, edge);
                    return half4(rgb, cellColor.a);
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader? = null,
        size: Float = 10f,
        edgeThickness: Float = 0.4f,
        fadeEdges: Boolean = false,
        edgeColor: Color = Color.Black,
        width: Int = 1,
        height: Int = 1
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(size)
        dataBuffer.putFloat(edgeThickness)
        dataBuffer.putInt(if (fadeEdges) 1 else 0)
        dataBuffer.putFloat(edgeColor.red)
        dataBuffer.putFloat(edgeColor.green)
        dataBuffer.putFloat(edgeColor.blue)
        dataBuffer.putFloat(width.toFloat())
        dataBuffer.putFloat(height.toFloat())
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }

    fun effectModifier(
        size: Float = 10f,
        edgeThickness: Float = 0.4f,
        fadeEdges: Boolean = false,
        edgeColor: Color = Color.Black
    ): Modifier {
        return Modifier.drawWithCache {
            val shader = makeShader(inputShader = null, size = size, edgeThickness = edgeThickness, fadeEdges = fadeEdges, edgeColor = edgeColor, width = this.size.width.toInt(), height = this.size.height.toInt())
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
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
class PointillizeFilter : Filter {
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

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader? = null,
        size: Float = 5f,
        edgeThickness: Float = 0.4f,
        fadeEdges: Boolean = false,
        edgeColor: Color = Color.Black,
        fuzziness: Float = 0.1f
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(28).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(size)
        dataBuffer.putFloat(edgeThickness)
        dataBuffer.putInt(if (fadeEdges) 1 else 0)
        dataBuffer.putFloat(edgeColor.red)
        dataBuffer.putFloat(edgeColor.green)
        dataBuffer.putFloat(edgeColor.blue)
        dataBuffer.putFloat(fuzziness)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }

    fun effectModifier(
        size: Float = 5f,
        edgeThickness: Float = 0.4f,
        fadeEdges: Boolean = false,
        edgeColor: Color = Color.Black,
        fuzziness: Float = 0.1f
    ): Modifier {
        return Modifier.drawWithCache {
            val shader = makeShader(inputShader = null, size = size, edgeThickness = edgeThickness, fadeEdges = fadeEdges, edgeColor = edgeColor, fuzziness = fuzziness)
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}
