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

import androidx.compose.ui.graphics.Color
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which converts an image to binary (black and white) using a threshold.
 * @param threshold brightness threshold (0-1)
 */
class BinaryFilter(val threshold: Float = 0.5f, val newColor: Color = Color.Black) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            uniform half3 newColor;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half val = luma > threshold ? 1.0 : 0.0;
                return half4(newColor * val, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        dataBuffer.putFloat(newColor.red)
        dataBuffer.putFloat(newColor.green)
        dataBuffer.putFloat(newColor.blue)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A dilation filter (morphological operation) — sets a pixel to newColor if it has enough black neighbors.
 * Based on JH-Labs DilateFilter extending BinaryFilter.
 *
 * @param threshold minimum number of black neighbors to set pixel to newColor (default 2)
 * @param newColor the color to set dilated pixels to (default black, 0xff000000)
 * @param pixelBlackLumaThreshold luma threshold below which a pixel is considered black (default 0.0)
 */
class DilateFilter(val threshold: Int = 2, val newColor: Color = Color.Black, val pixelBlackLumaThreshold: Float = 0f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int threshold;
            uniform half3 newColor;
            uniform float pixelBlackLumaThreshold;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half centerLuma = dot(center.rgb, half3(0.299, 0.587, 0.114));
                // Only process non-black pixels
                if (centerLuma > pixelBlackLumaThreshold) {
                    int blackNeighbors = 0;
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dx == 0 && dy == 0) continue;
                            half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                            half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                            if (luma <= pixelBlackLumaThreshold) {
                                blackNeighbors++;
                            }
                        }
                    }
                    if (blackNeighbors >= threshold) {
                        return half4(newColor, center.a);
                    }
                }
                return center;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(threshold)
        dataBuffer.putFloat(newColor.red)
        dataBuffer.putFloat(newColor.green)
        dataBuffer.putFloat(newColor.blue)
        dataBuffer.putFloat(pixelBlackLumaThreshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * An erosion filter (morphological operation) — sets a pixel to newColor if it has enough non-black neighbors.
 * Based on JH-Labs ErodeFilter extending BinaryFilter.
 *
 * @param threshold minimum number of non-black neighbors to set pixel to newColor (default 2)
 * @param newColor the color to set eroded pixels to (default white, 0xffffffff)
 * @param pixelBlackLumaThreshold luma threshold below which a pixel is considered black (default 0.0)
 */
class ErodeFilter(val threshold: Int = 2, val newColor: Color = Color.White, val pixelBlackLumaThreshold: Float = 0f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int threshold;
            uniform half3 newColor;
            uniform float pixelBlackLumaThreshold;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half centerLuma = dot(center.rgb, half3(0.299, 0.587, 0.114));
                // Only process black pixels
                if (centerLuma <= pixelBlackLumaThreshold) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dx == 0 && dy == 0) continue;
                            half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                            half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                            if (luma > pixelBlackLumaThreshold) {
                                nonBlackNeighbors++;
                            }
                        }
                    }
                    if (nonBlackNeighbors >= threshold) {
                        return half4(newColor, center.a);
                    }
                }
                return center;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(threshold)
        dataBuffer.putFloat(newColor.red)
        dataBuffer.putFloat(newColor.green)
        dataBuffer.putFloat(newColor.blue)
        dataBuffer.putFloat(pixelBlackLumaThreshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * An outline filter that converts binary images to outlines by replacing interior black pixels with newColor.
 * Based on JH-Labs OutlineFilter extending BinaryFilter.
 *
 * @param newColor the color to set interior pixels to (default white, 0xffffffff)
 * @param pixelBlackLumaThreshold luma threshold below which a pixel is considered black (default 0.0)
 */
class OutlineFilter(val newColor: Color = Color.White, val pixelBlackLumaThreshold: Float = 0f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 newColor;
            uniform float pixelBlackLumaThreshold;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half centerLuma = dot(center.rgb, half3(0.299, 0.587, 0.114));
                // Only process black pixels
                if (centerLuma <= pixelBlackLumaThreshold) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dx == 0 && dy == 0) continue;
                            half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                            half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                            if (luma <= pixelBlackLumaThreshold) {
                                blackNeighbors++;
                            }
                        }
                    }
                    if (blackNeighbors == 8) {
                        return half4(newColor, center.a);
                    }
                }
                return center;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(newColor.red)
        dataBuffer.putFloat(newColor.green)
        dataBuffer.putFloat(newColor.blue)
        dataBuffer.putFloat(pixelBlackLumaThreshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A skeleton filter that reduces binary images to skeletons using the Zhang-Suen thinning algorithm.
 * Based on JH-Labs SkeletonFilter extending BinaryFilter.
 *
 * @param newColor the color of the skeleton lines (default white, 0xffffffff)
 * @param pixelBlackLumaThreshold luma threshold below which a pixel is considered black (default 0.0)
 */
class SkeletonFilter(val newColor: Color = Color.White, val pixelBlackLumaThreshold: Float = 0f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform half3 newColor;
            uniform float pixelBlackLumaThreshold;

            // Zhang-Suen skeleton lookup table (256 entries)
            // Each entry: 0=keep black, 1=remove in pass1, 2=remove in pass2, 3=remove in both
            // Table index built from 8 neighbors: TL=1, T=2, TR=4, R=8, BR=16, B=32, BL=64, L=128
            const int skeletonTable[256] = int[256](
                0, 0, 0, 1, 0, 0, 1, 3, 0, 0, 3, 1, 1, 0, 1, 3,
                0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 3, 0, 3, 3,
                0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0,
                2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 3, 0, 2, 2,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0,
                3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 2, 0,
                0, 1, 3, 1, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                2, 3, 1, 3, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                2, 3, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                3, 3, 0, 1, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0
            );

            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half centerLuma = dot(center.rgb, half3(0.299, 0.587, 0.114));

                // If background (non-black), keep it
                if (centerLuma > pixelBlackLumaThreshold) {
                    return center;
                }

                // Build table index from 8 neighbors (black=1, white=0)
                // TL=1, T=2, TR=4, R=8, BR=16, B=32, BL=64, L=128
                int tableIndex = 0;
                if (dot(content.eval(fragcoord + vec2(-1.0, -1.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 1;
                if (dot(content.eval(fragcoord + vec2(0.0, -1.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 2;
                if (dot(content.eval(fragcoord + vec2(1.0, -1.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 4;
                if (dot(content.eval(fragcoord + vec2(1.0, 0.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 8;
                if (dot(content.eval(fragcoord + vec2(1.0, 1.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 16;
                if (dot(content.eval(fragcoord + vec2(0.0, 1.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 32;
                if (dot(content.eval(fragcoord + vec2(-1.0, 1.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 64;
                if (dot(content.eval(fragcoord + vec2(-1.0, 0.0)).rgb, half3(0.299, 0.587, 0.114)) <= pixelBlackLumaThreshold)
                    tableIndex |= 128;

                int code = skeletonTable[tableIndex];

                // Single-pass approximation: remove if code indicates removal in either pass
                // code 1 = remove in pass1, code 2 = remove in pass2, code 3 = remove in both
                if (code != 0) {
                    // This pixel would be removed by thinning — keep as background (black)
                    return half4(0.0, 0.0, 0.0, center.a);
                }
                // Keep as skeleton — set to newColor
                return half4(newColor, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(newColor.red)
        dataBuffer.putFloat(newColor.green)
        dataBuffer.putFloat(newColor.blue)
        dataBuffer.putFloat(pixelBlackLumaThreshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter implementing one round of Conway's Game of Life on the image.
 * Based on JH-Labs LifeFilter extending BinaryFilter.
 *
 * The filter treats dark pixels (luma below threshold) as alive and bright pixels as dead,
 * then applies standard Game of Life rules: a dead cell with exactly 3 alive
 * neighbors becomes alive; an alive cell with 2 or 3 alive neighbors survives.
 * Surviving cells keep their original color; dead cells become white; newly born cells become black.
 *
 * @param pixelBlackLumaThreshold luma threshold below which a pixel is considered black/alive (default 0.0)
 */
class LifeFilter(val pixelBlackLumaThreshold: Float = 0f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float pixelBlackLumaThreshold;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half centerLuma = dot(center.rgb, half3(0.299, 0.587, 0.114));
                int alive = centerLuma <= pixelBlackLumaThreshold ? 1 : 0;

                // Count alive (black) neighbors in 3x3 neighborhood, excluding center
                int aliveNeighbors = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0) continue;
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                        if (luma <= pixelBlackLumaThreshold) {
                            aliveNeighbors++;
                        }
                    }
                }

                // Conway's Game of Life rules — preserve original pixel colors
                if (alive == 1) {
                    // Alive cell survives with 2 or 3 neighbors, otherwise dies (white)
                    if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                        return center;
                    } else {
                        return half4(1.0, 1.0, 1.0, center.a);
                    }
                } else {
                    // Dead cell becomes alive with exactly 3 neighbors (black), otherwise stays
                    if (aliveNeighbors == 3) {
                        return half4(0.0, 0.0, 0.0, center.a);
                    } else {
                        return center;
                    }
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(pixelBlackLumaThreshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
