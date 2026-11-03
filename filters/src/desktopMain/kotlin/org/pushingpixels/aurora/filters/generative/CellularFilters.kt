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
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.asComposeShader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
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
 * Replicates the JH-Labs CrystallizeFilter (extends CellularFilter).
 *
 * Inherits CellularFilter parameters: scale, stretch, angle, randomness, gridType,
 * distancePower, gradientCoefficient, angleCoefficient, coefficients (F1-F4), turbulence, amount.
 *
 * @param scale the scale of the cells (default 16, matching original CrystallizeFilter constructor)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param randomness the randomness amount perturbing cell points (default 0.0f)
 * @param gridType the grid type: 0=RANDOM, 1=SQUARE, 2=HEXAGONAL, 3=OCTAGONAL, 4=TRIANGULAR (default 2=HEXAGONAL)
 * @param distancePower the distance metric power: 1=Manhattan, 2=Euclidean (default 2.0f)
 * @param gradientCoefficient gradient noise coefficient (default 0.0f)
 * @param angleCoefficient angle coefficient for angular variation (default 0.0f)
 * @param f1 coefficient for nearest cell distance (default 1.0f)
 * @param f2 coefficient for second-nearest cell distance (default 0.0f)
 * @param f3 coefficient for third-nearest cell distance (default 0.0f)
 * @param f4 coefficient for fourth-nearest cell distance (default 0.0f)
 * @param turbulence turbulence frequency for fractal noise (default 1.0f)
 * @param amount the effect intensity (default 1.0f)
 * @param edgeThickness the edge line thickness (default 0.4f)
 * @param fadeEdges true to fade edges by blending adjacent cells (default false)
 * @param edgeColor the edge color (default black)
 */
class CrystallizeFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform float stretch;
            uniform float angle;
            uniform float randomness;
            uniform int gridType;
            uniform float distancePower;
            uniform float gradientCoefficient;
            uniform float angleCoefficient;
            uniform float f1;
            uniform float f2;
            uniform float f3;
            uniform float f4;
            uniform float turbulence;
            uniform float amount;
            uniform float edgeThickness;
            uniform int fadeEdges;
            uniform half3 edgeColor;
            uniform vec2 contentSize;

            $NOISE_FUNC

            // Hash function for deterministic point placement per cell
            float cellHash(int cubeX, int cubeY) {
                return hash(vec2(float(cubeX) * 127.1 + float(cubeY) * 311.7, float(cubeX) * 269.5 + float(cubeY) * 183.3));
            }

            // Compute distance using configurable power metric (matching original Java)
            float computeDistance(float dx, float dy) {
                if (distancePower == 1.0)
                    return dx + dy;
                else if (distancePower == 2.0)
                    return sqrt(dx * dx + dy * dy);
                else
                    return pow(pow(dx, distancePower) + pow(dy, distancePower), 1.0 / distancePower);
            }

            // Check a single cell and update the three nearest results (matching original checkCube)
            // Returns the third-nearest distance for early termination
            float checkCube(float fx, float fy, int cubeX, int cubeY,
                           inout float r0d, inout float r0x, inout float r0y,
                           inout float r1d, inout float r1x, inout float r1y,
                           inout float r2d, inout float r2x, inout float r2y) {
                int numPoints;
                if (gridType == 0 || gridType > 4) { // RANDOM
                    // Simplified Poisson distribution lookup
                    numPoints = 3; // Average for Poisson(lambda=2.5)
                } else if (gridType == 1) { // SQUARE
                    numPoints = 1;
                } else if (gridType == 2) { // HEXAGONAL
                    numPoints = 1;
                } else if (gridType == 3) { // OCTAGONAL
                    numPoints = 2;
                } else { // TRIANGULAR
                    numPoints = 2;
                }

                for (int pt = 0; pt < 2; pt++) {
                    if (pt >= numPoints) break;
                    float px = 0.0, py = 0.0;
                    float weight = 1.0;

                    if (gridType == 0 || gridType > 4) { // RANDOM
                        px = cellHash(cubeX, cubeY);
                        py = hash(vec2(float(cubeX) * 173.1 + float(cubeY) * 293.7, float(cubeX) * 137.3 + float(cubeY) * 211.1));
                    } else if (gridType == 1) { // SQUARE
                        px = 0.5;
                        py = 0.5;
                        if (randomness > 0.0) {
                            px += randomness * (hash(vec2(float(cubeX) * 17.0, float(cubeY) * 31.0)) - 0.5);
                            py += randomness * (hash(vec2(float(cubeX) * 37.0, float(cubeY) * 53.0)) - 0.5);
                        }
                    } else if (gridType == 2) { // HEXAGONAL
                        if (mod(float(cubeX), 2.0) == 0.0) {
                            px = 0.75;
                            py = 0.0;
                        } else {
                            px = 0.75;
                            py = 0.5;
                        }
                        if (randomness > 0.0) {
                            px += randomness * noise2(vec2(271.0 * (float(cubeX) + px), 271.0 * (float(cubeY) + py)));
                            py += randomness * noise2(vec2(271.0 * (float(cubeX) + px) + 89.0, 271.0 * (float(cubeY) + py) + 137.0));
                        }
                    } else if (gridType == 3) { // OCTAGONAL
                        if (pt == 0) { px = 0.207; py = 0.207; }
                        else { px = 0.707; py = 0.707; weight = 1.6; }
                        if (randomness > 0.0) {
                            px += randomness * noise2(vec2(271.0 * (float(cubeX) + px), 271.0 * (float(cubeY) + py)));
                            py += randomness * noise2(vec2(271.0 * (float(cubeX) + px) + 89.0, 271.0 * (float(cubeY) + py) + 137.0));
                        }
                    } else { // TRIANGULAR
                        if (mod(float(cubeY), 2.0) == 0.0) {
                            if (pt == 0) { px = 0.25; py = 0.35; }
                            else { px = 0.75; py = 0.65; }
                        } else {
                            if (pt == 0) { px = 0.75; py = 0.35; }
                            else { px = 0.25; py = 0.65; }
                        }
                        if (randomness > 0.0) {
                            px += randomness * noise2(vec2(271.0 * (float(cubeX) + px), 271.0 * (float(cubeY) + py)));
                            py += randomness * noise2(vec2(271.0 * (float(cubeX) + px) + 89.0, 271.0 * (float(cubeY) + py) + 137.0));
                        }
                    }

                    float dx = abs(fx - px);
                    float dy = abs(fy - py);
                    dx *= weight;
                    dy *= weight;

                    float d = computeDistance(dx, dy);

                    // Insertion sort into top 3 results (matching original Java)
                    if (d < r0d) {
                        r2d = r1d; r2x = r1x; r2y = r1y;
                        r1d = r0d; r1x = r0x; r1y = r0y;
                        r0d = d; r0x = float(cubeX) + px; r0y = float(cubeY) + py;
                    } else if (d < r1d) {
                        r2d = r1d; r2x = r1x; r2y = r1y;
                        r1d = d; r1x = float(cubeX) + px; r1y = float(cubeY) + py;
                    } else if (d < r2d) {
                        r2d = d; r2x = float(cubeX) + px; r2y = float(cubeY) + py;
                    }
                }
                return r2d;
            }

            // Evaluate cellular noise at (nx, ny) with given scale (matching original evaluate method)
            float evaluate(float nx, float ny, float evalScale,
                          inout float r0d, inout float r0x, inout float r0y,
                          inout float r1d, inout float r1x, inout float r1y,
                          inout float r2d, inout float r2x, inout float r2y) {
                // Reset results
                r0d = 1e10; r1d = 1e10; r2d = 1e10;

                vec2 p = vec2(nx, ny);
                vec2 grid = floor(p);
                float fx = p.x - grid.x;
                float fy = p.y - grid.y;

                // Check center cell first
                float d = checkCube(fx, fy, int(grid.x), int(grid.y),
                                   r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);

                // Early termination checks (matching original Java evaluate method)
                if (d > fy)
                    d = checkCube(fx, fy + 1.0, int(grid.x), int(grid.y) - 1,
                                 r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                if (d > 1.0 - fy)
                    d = checkCube(fx, fy - 1.0, int(grid.x), int(grid.y) + 1,
                                 r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                if (d > fx) {
                    checkCube(fx + 1.0, fy, int(grid.x) - 1, int(grid.y),
                             r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                    if (d > fy)
                        d = checkCube(fx + 1.0, fy + 1.0, int(grid.x) - 1, int(grid.y) - 1,
                                     r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                    if (d > 1.0 - fy)
                        d = checkCube(fx + 1.0, fy - 1.0, int(grid.x) - 1, int(grid.y) + 1,
                                     r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                }
                if (d > 1.0 - fx) {
                    d = checkCube(fx - 1.0, fy, int(grid.x) + 1, int(grid.y),
                                 r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                    if (d > fy)
                        d = checkCube(fx - 1.0, fy + 1.0, int(grid.x) + 1, int(grid.y) - 1,
                                     r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                    if (d > 1.0 - fy)
                        d = checkCube(fx - 1.0, fy - 1.0, int(grid.x) + 1, int(grid.y) + 1,
                                     r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
                }

                // Combine distances with coefficients (matching original Java)
                float t = f1 * r0d + f2 * r1d + f3 * r2d;

                // Angle coefficient (matching original Java)
                if (angleCoefficient != 0.0) {
                    float ang = atan(ny - r0y, nx - r0x);
                    if (ang < 0.0) ang += 2.0 * 3.14159265358979323846;
                    ang /= 4.0 * 3.14159265358979323846;
                    t += angleCoefficient * ang;
                }

                // Gradient coefficient (matching original Java: 1/(dy+dx))
                if (gradientCoefficient != 0.0) {
                    float a = 1.0 / (abs(r0y - ny) + abs(r0x - nx) + 0.001);
                    t += gradientCoefficient * a;
                }

                return t;
            }

            half4 main(vec2 fragcoord) {
                // Rotation matrix from angle (matching original CellularFilter.setAngle)
                float cosA = cos(angle);
                float sinA = sin(angle);
                // Apply rotation: nx = m00*x + m01*y, ny = m10*x + m11*y
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                // Scale (matching original getPixel: nx /= scale; ny /= scale * stretch)
                nx /= scale;
                ny /= scale * stretch;
                nx += 1000.0;
                ny += 1000.0; // Reduce artifacts around 0,0 (matching original)

                float r0d, r0x, r0y;
                float r1d, r1x, r1y;
                float r2d, r2x, r2y;

                // Evaluate cellular noise once (matching original CrystallizeFilter.getPixel)
                // The original does NOT use turbulence in CrystallizeFilter
                evaluate(nx, ny, 1.0,
                         r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);

                // Sample source image at nearest cell point location
                // Original Java: srcx = clamp((int)((results[0].x - 1000) * scale), 0, width-1)
                float srcX = (r0x - 1000.0) * scale;
                float srcY = (r0y - 1000.0) * scale;
                vec2 srcCoord = clamp(vec2(srcX, srcY), vec2(0.0), contentSize - 1.0);
                half4 cellColor = content.eval(srcCoord);
                
                return cellColor;

//                // Edge detection (matching original CrystallizeFilter.getPixel)
//                // Original: f = (f2 - f1) / edgeThickness; f = ImageMath.smoothStep(0, edgeThickness, f)
//                float edgeF = (r1d - r0d) / edgeThickness;
//                float edge = smoothstep(0.0, edgeThickness, edgeF);
//
//                if (fadeEdges == 1) {
//                    // Blend between nearest and second-nearest cell colors
//                    // Original: v2 = mixColors(0.5f, v2, v); v = mixColors(f, v2, v)
//                    float srcX2 = (r1x - 1000.0) * scale;
//                    float srcY2 = (r1y - 1000.0) * scale;
//                    vec2 srcCoord2 = clamp(vec2(srcX2, srcY2), vec2(0.0), contentSize - 1.0);
//                    half4 cellColor2 = content.eval(srcCoord2);
//                    half3 blended = mix(cellColor2.rgb, cellColor.rgb, 0.5);
//                    half3 rgb = mix(blended, cellColor.rgb, edge);
//                    return half4(rgb, cellColor.a);
//                } else {
//                    // Original: v = mixColors(f, edgeColor, v)
//                    half3 rgb = mix(edgeColor, cellColor.rgb, edge);
//                    return half4(rgb, cellColor.a);
//                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader? = null,
        scale: Float = 16f,
        stretch: Float = 1f,
        angle: Float = 0f,
        randomness: Float = 0f,
        gridType: Int = 2, // HEXAGONAL
        distancePower: Float = 2f,
        gradientCoefficient: Float = 0f,
        angleCoefficient: Float = 0f,
        f1: Float = 1f,
        f2: Float = 0f,
        f3: Float = 0f,
        f4: Float = 0f,
        turbulence: Float = 1f,
        amount: Float = 1f,
        edgeThickness: Float = 0.4f,
        fadeEdges: Boolean = false,
        edgeColor: Color = Color.Black,
        contentSize: FloatArray
    ): org.jetbrains.skia.Shader {
        // 19 floats + 2 ints = 84 bytes
        val dataBuffer = ByteBuffer.allocate(84).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(randomness)
        dataBuffer.putInt(gridType)
        dataBuffer.putFloat(distancePower)
        dataBuffer.putFloat(gradientCoefficient)
        dataBuffer.putFloat(angleCoefficient)
        dataBuffer.putFloat(f1)
        dataBuffer.putFloat(f2)
        dataBuffer.putFloat(f3)
        dataBuffer.putFloat(f4)
        dataBuffer.putFloat(turbulence)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(edgeThickness)
        dataBuffer.putInt(if (fadeEdges) 1 else 0)
        dataBuffer.putFloat(edgeColor.red)
        dataBuffer.putFloat(edgeColor.green)
        dataBuffer.putFloat(edgeColor.blue)
        dataBuffer.putFloat(contentSize[0])
        dataBuffer.putFloat(contentSize[1])
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }

    fun effectModifier(
        scale: Float = 16f,
        stretch: Float = 1f,
        angle: Float = 0f,
        randomness: Float = 0f,
        gridType: Int = 2,
        distancePower: Float = 2f,
        gradientCoefficient: Float = 0f,
        angleCoefficient: Float = 0f,
        f1: Float = 1f,
        f2: Float = 0f,
        f3: Float = 0f,
        f4: Float = 0f,
        turbulence: Float = 1f,
        amount: Float = 1f,
        edgeThickness: Float = 0.4f,
        fadeEdges: Boolean = false,
        edgeColor: Color = Color.Black
    ): Modifier {
        val crystallizeShaderBuilder = RuntimeShaderBuilder(this.effect)

        crystallizeShaderBuilder.uniform("scale", scale)
        crystallizeShaderBuilder.uniform("stretch", stretch)
        crystallizeShaderBuilder.uniform("angle", angle)
        crystallizeShaderBuilder.uniform("randomness", randomness)
        crystallizeShaderBuilder.uniform("gridType", gridType)
        crystallizeShaderBuilder.uniform("distancePower", distancePower)
        crystallizeShaderBuilder.uniform("gradientCoefficient", gradientCoefficient)
        crystallizeShaderBuilder.uniform("angleCoefficient", angleCoefficient)
        crystallizeShaderBuilder.uniform("f1", f1)
        crystallizeShaderBuilder.uniform("f2", f2)
        crystallizeShaderBuilder.uniform("f3", f3)
        crystallizeShaderBuilder.uniform("f4", f4)
        crystallizeShaderBuilder.uniform("turbulence", turbulence)
        crystallizeShaderBuilder.uniform("amount", amount)
        crystallizeShaderBuilder.uniform("edgeThickness", edgeThickness)
        crystallizeShaderBuilder.uniform("fadeEdges", if (fadeEdges) 1 else 0)
        crystallizeShaderBuilder.uniform(
            "edgeColor", edgeColor.red, edgeColor.green, edgeColor.blue
        )
        crystallizeShaderBuilder.uniform(
            "contentSize", 560.0f, 385.0f
        )

        val sizeChangedModifier = Modifier.onSizeChanged { newSize ->
//            crystallizeShaderBuilder.uniform(
//                "contentSize", newSize.width.toFloat(), newSize.height.toFloat()
//            )
        }

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = crystallizeShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return sizeChangedModifier.graphicsLayer(renderEffect = renderEffect, clip = true)
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
