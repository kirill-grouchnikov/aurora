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

/**
 * Grid type for cellular noise in [CrystallizeFilter].
 * Determines the arrangement of sample points within each grid cell.
 */
enum class GridType {
    /** Fully random point placement (Poisson-like distribution). */
    Random,
    /** Single point at the center of each square cell. */
    Square,
    /** Single point in a hexagonal lattice pattern. */
    Hexagonal,
    /** Two points per cell forming an octagonal pattern. */
    Octagonal,
    /** Two points per cell forming a triangular pattern. */
    Triangular
}

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
 * A filter which produces an image with a cellular/Worley noise texture.
 * Replicates the JH-Labs CellularFilter (extends WholeImageFilter).
 *
 * Uses the same cellular noise evaluation pipeline as [CrystallizeFilter] and [PointillizeFilter],
 * with support for grid types, distance metrics, turbulence, and colormap-based coloring.
 *
 * @param scale the scale of the texture (default 32.0f)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param amount the effect intensity (default 1.0f)
 * @param turbulence turbulence frequency for fractal noise; 1.0 means single evaluation (default 1.0f)
 * @param gain gain applied to the cellular value (default 0.5f)
 * @param bias bias added to the cellular value (default 0.5f)
 * @param distancePower the distance metric power: 1=Manhattan, 2=Euclidean (default 2.0f)
 * @param gridType the grid type for cell point arrangement (default [GridType.Hexagonal])
 * @param randomness the randomness amount perturbing cell points (default 0.0f)
 * @param f1 coefficient for nearest cell distance (default 1.0f)
 * @param f2 coefficient for second-nearest cell distance (default 0.0f)
 * @param f3 coefficient for third-nearest cell distance (default 0.0f)
 * @param f4 coefficient for fourth-nearest cell distance (default 0.0f)
 * @param angleCoefficient angle coefficient for angular variation (default 0.0f)
 * @param gradientCoefficient gradient noise coefficient (default 0.0f)
 * @param useColor when true, samples source image color at nearest cell position (default false)
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
            uniform float amount;
            uniform float turbulence;
            uniform float gain;
            uniform float bias;
            uniform float distancePower;
            uniform int gridType;
            uniform float randomness;
            uniform float f1;
            uniform float f2;
            uniform float f3;
            uniform float f4;
            uniform float angleCoefficient;
            uniform float gradientCoefficient;
            uniform int useColor;
            uniform int useColormap;
            $NOISE_FUNC
            $CELL_HASH_FUNC
            $COMPUTE_DISTANCE_FUNC
            $CHECK_CUBE_FUNC
            $EVALUATE_FUNC

            half4 main(vec2 fragcoord) {
                // Rotation matrix from angle
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                nx /= scale * stretch;
                ny /= scale;
                nx += 1000.0;
                ny += 1000.0;

                // Evaluate cellular noise (with turbulence if > 1.0)
                float f;
                if (turbulence <= 1.0) {
                    f = evaluate(nx, ny, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)[0][0];
                } else {
                    f = 0.0;
                    float freq = 1.0;
                    while (freq <= turbulence) {
                        f += evaluate(freq * nx, freq * ny, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)[0][0] / freq;
                        freq *= 2.0;
                    }
                }

                // Apply gain, bias, and amount
                f = f * gain + bias;
                f *= amount;
                f = clamp(f, 0.0, 1.0);

                half4 c = content.eval(fragcoord);
                half3 rgb;

                if (useColor == 1) {
                    // Sample source image color at nearest cell position
                    mat3 result = evaluate(nx, ny, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                    float r0x = result[0][1];
                    float r0y = result[1][1];
                    float srcX = (r0x - 1000.0) * scale * stretch;
                    float srcY = (r0y - 1000.0) * scale;
                    half4 cellColor = content.eval(vec2(srcX, srcY));
                    rgb = cellColor.rgb;
                } else if (useColormap == 1) {
                    half4 colormapEval = colormap.eval(vec2(f * 256.0, 0.0));
                    rgb = colormapEval.rgb;
                    if (colormapEval.a != 0.0) {
                        rgb = rgb / colormapEval.a;
                    }
                } else {
                    rgb = half3(f);
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
        amount: Float = 1f,
        turbulence: Float = 1f,
        gain: Float = 0.5f,
        bias: Float = 0.5f,
        distancePower: Float = 2f,
        gridType: GridType = GridType.Hexagonal,
        randomness: Float = 0f,
        f1: Float = 1f,
        f2: Float = 0f,
        f3: Float = 0f,
        f4: Float = 0f,
        angleCoefficient: Float = 0f,
        gradientCoefficient: Float = 0f,
        useColor: Boolean = false,
        colors: List<Color>? = null
    ): org.jetbrains.skia.Shader {
        // 16 floats + 3 ints = 76 bytes
        val dataBuffer = ByteBuffer.allocate(76).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(turbulence)
        dataBuffer.putFloat(gain)
        dataBuffer.putFloat(bias)
        dataBuffer.putFloat(distancePower)
        dataBuffer.putInt(gridType.ordinal)
        dataBuffer.putFloat(randomness)
        dataBuffer.putFloat(f1)
        dataBuffer.putFloat(f2)
        dataBuffer.putFloat(f3)
        dataBuffer.putFloat(f4)
        dataBuffer.putFloat(angleCoefficient)
        dataBuffer.putFloat(gradientCoefficient)
        dataBuffer.putInt(if (useColor) 1 else 0)
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
        amount: Float = 1f,
        turbulence: Float = 1f,
        gain: Float = 0.5f,
        bias: Float = 0.5f,
        distancePower: Float = 2f,
        gridType: GridType = GridType.Hexagonal,
        randomness: Float = 0f,
        f1: Float = 1f,
        f2: Float = 0f,
        f3: Float = 0f,
        f4: Float = 0f,
        angleCoefficient: Float = 0f,
        gradientCoefficient: Float = 0f,
        useColor: Boolean = false,
        colors: List<Color>? = null
    ): Modifier {
        return Modifier.drawWithCache {
            val shader = makeShader(
                inputShader = null,
                scale = scale,
                stretch = stretch,
                angle = angle,
                amount = amount,
                turbulence = turbulence,
                gain = gain,
                bias = bias,
                distancePower = distancePower,
                gridType = gridType,
                randomness = randomness,
                f1 = f1,
                f2 = f2,
                f3 = f3,
                f4 = f4,
                angleCoefficient = angleCoefficient,
                gradientCoefficient = gradientCoefficient,
                useColor = useColor,
                colors = colors
            )
            val shaderBrush = ShaderBrush(shader.asComposeShader())
            onDrawBehind {
                drawRect(brush = shaderBrush)
            }
        }
    }
}

// Reusable GLSL shader fragments for cellular noise filters (shared by CrystallizeFilter and PointillizeFilter)

/** Hash function for deterministic point placement per cell. */
private val CELL_HASH_FUNC = """
    float cellHash(int cubeX, int cubeY) {
        return hash(vec2(float(cubeX) * 127.1 + float(cubeY) * 311.7, float(cubeX) * 269.5 + float(cubeY) * 183.3));
    }
"""

/** Compute distance using configurable power metric (matching original Java). */
private val COMPUTE_DISTANCE_FUNC = """
    float computeDistance(float dx, float dy) {
        if (distancePower == 1.0)
            return dx + dy;
        else if (distancePower == 2.0)
            return sqrt(dx * dx + dy * dy);
        else
            return pow(pow(dx, distancePower) + pow(dy, distancePower), 1.0 / distancePower);
    }
"""

/** Check a single cell and update the three nearest results (matching original checkCube). */
private val CHECK_CUBE_FUNC = """
    float checkCube(float fx, float fy, int cubeX, int cubeY,
                   inout float r0d, inout float r0x, inout float r0y,
                   inout float r1d, inout float r1x, inout float r1y,
                   inout float r2d, inout float r2x, inout float r2y) {
        int numPoints;
        if (gridType == 0 || gridType > 4) { // RANDOM
            numPoints = 3;
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
"""

/** Evaluate cellular noise at (nx, ny) — returns mat3 packing 9 values. */
private val EVALUATE_FUNC = """
    mat3 evaluate(float nx, float ny, float evalScale,
                 float r0x, float r0y,
                 float r1x, float r1y,
                 float r2x, float r2y) {
        float r0d = 1e10; float r1d = 1e10; float r2d = 1e10;

        vec2 p = vec2(nx, ny);
        vec2 grid = floor(p);
        float fx = p.x - grid.x;
        float fy = p.y - grid.y;

        float d = checkCube(fx, fy, int(grid.x), int(grid.y),
                           r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);

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
            checkCube(fx - 1.0, fy, int(grid.x) + 1, int(grid.y),
                     r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
            if (d > fy)
                d = checkCube(fx - 1.0, fy + 1.0, int(grid.x) + 1, int(grid.y) - 1,
                             r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
            if (d > 1.0 - fy)
                d = checkCube(fx - 1.0, fy - 1.0, int(grid.x) + 1, int(grid.y) + 1,
                             r0d, r0x, r0y, r1d, r1x, r1y, r2d, r2x, r2y);
        }

        float t = f1 * r0d + f2 * r1d + f3 * r2d;

        if (angleCoefficient != 0.0) {
            float ang = atan(ny - r0y, nx - r0x);
            if (ang < 0.0) ang += 2.0 * 3.14159265358979323846;
            ang /= 4.0 * 3.14159265358979323846;
            t += angleCoefficient * ang;
        }

        if (gradientCoefficient != 0.0) {
            float a = 1.0 / (abs(r0y - ny) + abs(r0x - nx) + 0.001);
            t += gradientCoefficient * a;
        }

        return mat3(t, r0x, r1y,
                   r0d, r0y, r2x,
                   r1d, r1x, r2y);
    }
"""

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
 * @param gridType the grid type for cell point arrangement (default [GridType.Hexagonal])
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
            $CELL_HASH_FUNC
            $COMPUTE_DISTANCE_FUNC
            $CHECK_CUBE_FUNC
            $EVALUATE_FUNC

            half4 main(vec2 fragcoord) {
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                nx /= scale;
                ny /= scale * stretch;
                nx += 1000.0;
                ny += 1000.0;

                mat3 result = evaluate(nx, ny, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                float r0d = result[1][0];
                float r1d = result[2][0];
                float r0x = result[0][1];
                float r0y = result[1][1];
                float r1x = result[2][1];
                float r1y = result[0][2];
                float r2x = result[1][2];
                float r2y = result[2][2];

                float srcX = (r0x - 1000.0) * scale;
                float srcY = (r0y - 1000.0) * scale;
                vec2 srcCoord = clamp(vec2(srcX, srcY), vec2(1.0), contentSize - 1.0);
                half4 cellColor = content.eval(srcCoord);
                
                float edgeF = (r1d - r0d) / edgeThickness;
                float edge = smoothstep(0.0, edgeThickness, edgeF);
                
                if (fadeEdges == 1) {
                    float srcX2 = (r1x - 1000.0) * scale;
                    float srcY2 = (r1y - 1000.0) * scale;
                    vec2 srcCoord2 = clamp(vec2(srcX2, srcY2), vec2(1.0), contentSize - 1.0);
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
        scale: Float = 16f,
        stretch: Float = 1f,
        angle: Float = 0f,
        randomness: Float = 0f,
        gridType: GridType = GridType.Hexagonal,
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
        dataBuffer.putInt(gridType.ordinal)
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
        gridType: GridType = GridType.Hexagonal,
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
        crystallizeShaderBuilder.uniform("gridType", gridType.ordinal)
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
 * Replicates the JH-Labs PointillizeFilter (extends CellularFilter).
 *
 * Inherits CellularFilter parameters: scale, stretch, angle, randomness, gridType,
 * distancePower, gradientCoefficient, angleCoefficient, coefficients (F1-F4), turbulence, amount.
 *
 * @param scale the scale of the cells (default 16, matching original PointillizeFilter constructor)
 * @param stretch the stretch factor (default 1.0f)
 * @param angle the rotation angle in radians (default 0.0f)
 * @param randomness the randomness amount perturbing cell points (default 0.0f, matching original constructor)
 * @param gridType the grid type for cell point arrangement (default [GridType.Hexagonal])
 * @param distancePower the distance metric power: 1=Manhattan, 2=Euclidean (default 2.0f)
 * @param gradientCoefficient gradient noise coefficient (default 0.0f)
 * @param angleCoefficient angle coefficient for angular variation (default 0.0f)
 * @param f1 coefficient for nearest cell distance (default 1.0f)
 * @param f2 coefficient for second-nearest cell distance (default 0.0f)
 * @param f3 coefficient for third-nearest cell distance (default 0.0f)
 * @param f4 coefficient for fourth-nearest cell distance (default 0.0f)
 * @param turbulence turbulence frequency for fractal noise (default 1.0f)
 * @param amount the effect intensity (default 1.0f)
 * @param edgeThickness the edge line thickness 0-1 (default 0.4f)
 * @param fadeEdges true to fade edges by blending adjacent cells (default false)
 * @param edgeColor the edge color (default black)
 * @param fuzziness the fuzziness amount 0-1 (default 0.1f)
 */
class PointillizeFilter : Filter {
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
            uniform float fuzziness;
            uniform vec2 contentSize;

            $NOISE_FUNC
            $CELL_HASH_FUNC
            $COMPUTE_DISTANCE_FUNC
            $CHECK_CUBE_FUNC
            $EVALUATE_FUNC

            half4 main(vec2 fragcoord) {
                float cosA = cos(angle);
                float sinA = sin(angle);
                float nx = cosA * fragcoord.x + sinA * fragcoord.y;
                float ny = -sinA * fragcoord.x + cosA * fragcoord.y;
                nx /= scale;
                ny /= scale * stretch;
                nx += 1000.0;
                ny += 1000.0;

                mat3 result = evaluate(nx, ny, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                float r0d = result[1][0];
                float r1d = result[2][0];
                float r0x = result[0][1];
                float r0y = result[1][1];
                float r1x = result[2][1];
                float r1y = result[0][2];

                float srcX = (r0x - 1000.0) * scale;
                float srcY = (r0y - 1000.0) * scale;
                vec2 srcCoord = clamp(vec2(srcX, srcY), vec2(1.0), contentSize - 1.0);
                half4 cellColor = content.eval(srcCoord);

                if (fadeEdges == 1) {
                    float srcX2 = (r1x - 1000.0) * scale;
                    float srcY2 = (r1y - 1000.0) * scale;
                    vec2 srcCoord2 = clamp(vec2(srcX2, srcY2), vec2(1.0), contentSize - 1.0);
                    half4 cellColor2 = content.eval(srcCoord2);
                    // Original: v = mixColors(0.5f*f1/f2, v, v2)
                    float blend = 0.5 * r0d / r1d;
                    half3 rgb = mix(cellColor2.rgb, cellColor.rgb, blend);
                    return half4(rgb, cellColor.a);
                } else {
                    // Original: f = 1 - smoothStep(edgeThickness, edgeThickness+fuzziness, f1)
                    // v = mixColors(f, edgeColor, v)
                    float edge = 1.0 - smoothstep(edgeThickness, edgeThickness + fuzziness, r0d);
                    half3 rgb = mix(edgeColor, cellColor.rgb, edge);
                    return half4(rgb, cellColor.a);
                }
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
        gridType: GridType = GridType.Hexagonal,
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
        fuzziness: Float = 0.1f,
        contentSize: FloatArray
    ): org.jetbrains.skia.Shader {
        // 20 floats + 2 ints + 3 floats (edgeColor) + 2 floats (contentSize) = 92 bytes
        val dataBuffer = ByteBuffer.allocate(92).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(scale)
        dataBuffer.putFloat(stretch)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(randomness)
        dataBuffer.putInt(gridType.ordinal)
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
        dataBuffer.putFloat(fuzziness)
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
        gridType: GridType = GridType.Hexagonal,
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
        fuzziness: Float = 0.1f
    ): Modifier {
        val pointillizeShaderBuilder = RuntimeShaderBuilder(this.effect)

        pointillizeShaderBuilder.uniform("scale", scale)
        pointillizeShaderBuilder.uniform("stretch", stretch)
        pointillizeShaderBuilder.uniform("angle", angle)
        pointillizeShaderBuilder.uniform("randomness", randomness)
        pointillizeShaderBuilder.uniform("gridType", gridType.ordinal)
        pointillizeShaderBuilder.uniform("distancePower", distancePower)
        pointillizeShaderBuilder.uniform("gradientCoefficient", gradientCoefficient)
        pointillizeShaderBuilder.uniform("angleCoefficient", angleCoefficient)
        pointillizeShaderBuilder.uniform("f1", f1)
        pointillizeShaderBuilder.uniform("f2", f2)
        pointillizeShaderBuilder.uniform("f3", f3)
        pointillizeShaderBuilder.uniform("f4", f4)
        pointillizeShaderBuilder.uniform("turbulence", turbulence)
        pointillizeShaderBuilder.uniform("amount", amount)
        pointillizeShaderBuilder.uniform("edgeThickness", edgeThickness)
        pointillizeShaderBuilder.uniform("fadeEdges", if (fadeEdges) 1 else 0)
        pointillizeShaderBuilder.uniform(
            "edgeColor", edgeColor.red, edgeColor.green, edgeColor.blue
        )
        pointillizeShaderBuilder.uniform("fuzziness", fuzziness)
        pointillizeShaderBuilder.uniform(
            "contentSize", 560.0f, 385.0f
        )

        val sizeChangedModifier = Modifier.onSizeChanged { newSize ->
//            pointillizeShaderBuilder.uniform(
//                "contentSize", newSize.width.toFloat(), newSize.height.toFloat()
//            )
        }

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = pointillizeShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return sizeChangedModifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}
