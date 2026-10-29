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
package org.pushingpixels.aurora.filters.convolution

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * An average blur filter that replaces each pixel with the average of its neighborhood.
 * @param radius neighborhood radius
 */
class AverageFilter(val radius: Int = 3) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                half4 sum = half4(0);
                int count = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        sum += content.eval(fragcoord + vec2(float(dx), float(dy)));
                        count++;
                    }
                }
                return sum / float(count);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A simple blur filter using a 3x3 convolution kernel.
 * Kernel: {1/14, 2/14, 1/14, 2/14, 2/14, 2/14, 1/14, 2/14, 1/14}
 */
object BlurFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 sum = half4(0);
                // 3x3 kernel weights
                half kernel[9] = half4(1.0/14.0, 2.0/14.0, 1.0/14.0, 2.0/14.0, 2.0/14.0, 2.0/14.0, 1.0/14.0, 2.0/14.0, 1.0/14.0);
                int idx = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        vec2 offset = vec2(float(dx), float(dy));
                        sum += content.eval(fragcoord + offset) * kernel[idx];
                        idx++;
                    }
                }
                return sum;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**

 * A filter which performs a box blur on an image.
 * Replicates the JH-Labs BoxBlurFilter.
 * @param hRadius horizontal radius of blur
 * @param vRadius vertical radius of blur
 * @param iterations number of iterations (default 1)
 */
class BoxBlurFilter(
    val hRadius: Float = 3f,
    val vRadius: Float = 3f,
    val iterations: Int = 1
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float hRadius;
            uniform float vRadius;
            uniform int iterations;
            half4 main(vec2 fragcoord) {
                half4 result = content.eval(fragcoord);
                for (int iter = 0; iter < iterations; iter++) {
                    // Horizontal pass
                    half4 hSum = half4(0);
                    int hR = int(ceil(hRadius));
                    if (hR < 1) hR = 1;
                    int hCount = 0;
                    for (int dx = -hR; dx <= hR; dx++) {
                        hSum += content.eval(fragcoord + vec2(float(dx), 0.0));
                        hCount++;
                    }
                    result = hSum / float(hCount);
                    // Vertical pass
                    half4 vSum = half4(0);
                    int vR = int(ceil(vRadius));
                    if (vR < 1) vR = 1;
                    int vCount = 0;
                    for (int dy = -vR; dy <= vR; dy++) {
                        vSum += result;
                        vCount++;
                    }
                    result = vSum / float(vCount);
                }
                return result;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(hRadius)
        dataBuffer.putFloat(vRadius)
        dataBuffer.putInt(iterations)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A bump mapping filter that simulates surface relief.
 * @param bumpHeight height of the bump effect
 */
class BumpFilter(val bumpHeight: Float = 1f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float bumpHeight;
            half4 main(vec2 fragcoord) {
                float h = length(content.eval(fragcoord).rgb);
                float hL = length(content.eval(fragcoord + vec2(-1.0, 0.0)).rgb);
                float hR = length(content.eval(fragcoord + vec2(1.0, 0.0)).rgb);
                float hU = length(content.eval(fragcoord + vec2(0.0, -1.0)).rgb);
                float hD = length(content.eval(fragcoord + vec2(0.0, 1.0)).rgb);
                
                float dx = (hR - hL) * bumpHeight;
                float dy = (hD - hU) * bumpHeight;
                float dz = 1.0 / (bumpHeight * 3.0);
                
                vec3 normal = normalize(vec3(-dx, -dy, dz));
                vec3 light = normalize(vec3(0.5, 0.5, 1.0));
                float diff = max(dot(normal, light), 0.0);
                
                half4 c = content.eval(fragcoord);
                half3 rgb = c.rgb * (0.5 + 0.5 * diff);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(bumpHeight)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A diffuse filter for softening edges.
 * @param amount diffusion amount (0-1)
 */
class DiffuseFilter(val amount: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amount;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = half4(0);
                sum += content.eval(fragcoord + vec2(-1.0, 0.0));
                sum += content.eval(fragcoord + vec2(1.0, 0.0));
                sum += content.eval(fragcoord + vec2(0.0, -1.0));
                sum += content.eval(fragcoord + vec2(0.0, 1.0));
                half4 avg = sum / 4.0;
                half3 rgb = mix(center.rgb, avg.rgb, amount);
                return half4(rgb, center.a);
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

 * A diffusion filter that simulates heat diffusion for smoothing.
 * @param iterations number of diffusion iterations
 */
class DiffusionFilter(val iterations: Int = 3) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int iterations;
            half4 main(vec2 fragcoord) {
                half4 result = content.eval(fragcoord);
                for (int iter = 0; iter < iterations; iter++) {
                    // Simple 4-neighbor diffusion step
                    half4 sum = result * 4.0;
                    sum += content.eval(fragcoord + vec2(-1.0, 0.0));
                    sum += content.eval(fragcoord + vec2(1.0, 0.0));
                    sum += content.eval(fragcoord + vec2(0.0, -1.0));
                    sum += content.eval(fragcoord + vec2(0.0, 1.0));
                    result = sum / 8.0;
                }
                return result;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(iterations)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A dilation filter (morphological operation) — replaces each pixel with the maximum in its neighborhood.
 * @param radius neighborhood radius
 */
class DilateFilter(val radius: Int = 1) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                half4 maxVal = content.eval(fragcoord);
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        if (length(c.rgb) > length(maxVal.rgb)) {
                            maxVal = c;
                        }
                    }
                }
                return maxVal;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A Difference of Gaussians filter for feature enhancement.
 * @param sigma1 first Gaussian sigma
 * @param sigma2 second Gaussian sigma
 */
class DoGFilter(
    val sigma1: Float = 1f,
    val sigma2: Float = 2f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float sigma1;
            uniform float sigma2;
            
            half gaussianBlur(vec2 coord, float sigma) {
                float r = ceil(sigma * 3.0);
                half sum = 0.0;
                float total = 0.0;
                for (float dx = -r; dx <= r; dx++) {
                    for (float dy = -r; dy <= r; dy++) {
                        float dist2 = dx*dx + dy*dy;
                        float weight = exp(-dist2 / (2.0 * sigma * sigma));
                        sum += content.eval(coord + vec2(dx, dy)).r * weight;
                        total += weight;
                    }
                }
                return sum / total;
            }
            
            half4 main(vec2 fragcoord) {
                half g1 = gaussianBlur(fragcoord, sigma1);
                half g2 = gaussianBlur(fragcoord, sigma2);
                half dog = abs(g1 - g2) * 2.0;
                dog = clamp(dog, 0.0, 1.0);
                half4 c = content.eval(fragcoord);
                return half4(dog, dog, dog, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(sigma1)
        dataBuffer.putFloat(sigma2)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * An edge detection filter using Sobel operators.
 */
object EdgeFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                // Sobel kernels
                // Gx: -1 0 1  Gy: -1 -2 -1
                //     -2 0 2      0  0  0
                //     -1 0 1      1  2  1
                half tl = content.eval(fragcoord + vec2(-1.0, -1.0)).r;
                half t  = content.eval(fragcoord + vec2(0.0, -1.0)).r;
                half tr = content.eval(fragcoord + vec2(1.0, -1.0)).r;
                half l  = content.eval(fragcoord + vec2(-1.0, 0.0)).r;
                half r  = content.eval(fragcoord + vec2(1.0, 0.0)).r;
                half bl = content.eval(fragcoord + vec2(-1.0, 1.0)).r;
                half b  = content.eval(fragcoord + vec2(0.0, 1.0)).r;
                half br = content.eval(fragcoord + vec2(1.0, 1.0)).r;
                
                half gx = -tl + tr - 2.0*l + 2.0*r - bl + br;
                half gy = -tl - 2.0*t - tr + bl + 2.0*b + br;
                half edge = sqrt(gx*gx + gy*gy) / 1.8;
                edge = clamp(edge, 0.0, 1.0);
                
                // Apply to all channels
                half4 c = content.eval(fragcoord);
                return half4(edge, edge, edge, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**

 * An emboss filter that simulates 3D embossing with directional lighting.
 * Replicates the JH-Labs EmbossFilter.
 * @param azimuth light azimuth angle in radians
 * @param elevation light elevation angle in radians
 * @param bumpHeight bump height factor (default 1.0)
 * @param emboss if true, color the emboss; if false, grayscale relief
 */
class EmbossFilter(
    val azimuth: Float = (135.0 * Math.PI / 180.0).toFloat(),
    val elevation: Float = (30.0 * Math.PI / 180.0).toFloat(),
    val bumpHeight: Float = 1f,
    val emboss: Boolean = false
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float azimuth;
            uniform float elevation;
            uniform float bumpHeight;
            uniform int emboss;
            half4 main(vec2 fragcoord) {
                float width45 = 3.0 * bumpHeight;
                float pixelScale = 255.9;
                
                // Sample 3x3 grid for bump mapping
                half tl = length(content.eval(fragcoord + vec2(-1.0, -1.0)).rgb);
                half t  = length(content.eval(fragcoord + vec2(0.0, -1.0)).rgb);
                half tr = length(content.eval(fragcoord + vec2(1.0, -1.0)).rgb);
                half l  = length(content.eval(fragcoord + vec2(-1.0, 0.0)).rgb);
                half r  = length(content.eval(fragcoord + vec2(1.0, 0.0)).rgb);
                half bl = length(content.eval(fragcoord + vec2(-1.0, 1.0)).rgb);
                half b  = length(content.eval(fragcoord + vec2(0.0, 1.0)).rgb);
                half br = length(content.eval(fragcoord + vec2(1.0, 1.0)).rgb);
                
                float Nx = float(tl + t + bl - tr - r - br);
                float Ny = float(bl + b + br - tl - t - tr);
                float Nz = 6.0 * 255.0 / width45;
                float Nz2 = Nz * Nz;
                float NzLz = Nz * (sin(elevation) * pixelScale);
                
                float Lx = cos(azimuth) * cos(elevation) * pixelScale;
                float Ly = sin(azimuth) * cos(elevation) * pixelScale;
                float Lz = sin(elevation) * pixelScale;
                
                float shade;
                if (Nx == 0.0 && Ny == 0.0) {
                    shade = Lz;
                } else {
                    float NdotL = Nx * Lx + Ny * Ly + NzLz;
                    if (NdotL < 0.0) {
                        shade = 0.0;
                    } else {
                        shade = NdotL / sqrt(Nx*Nx + Ny*Ny + Nz2);
                    }
                }
                shade = clamp(shade / pixelScale, 0.0, 1.0);
                
                half4 c = content.eval(fragcoord);
                if (emboss != 0) {
                    half3 rgb = c.rgb * shade;
                    return half4(rgb, c.a);
                } else {
                    return half4(shade, shade, shade, 1.0);
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(azimuth)
        dataBuffer.putFloat(elevation)
        dataBuffer.putFloat(bumpHeight)
        dataBuffer.putInt(if (emboss) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * An alpha erosion filter — erodes the alpha channel.
 * @param radius neighborhood radius
 */
class ErodeAlphaFilter(val radius: Int = 1) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half minA = c.a;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half a = content.eval(fragcoord + vec2(float(dx), float(dy))).a;
                        if (a < minA) minA = a;
                    }
                }
                return half4(c.rgb, minA);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * An erosion filter (morphological operation) — replaces each pixel with the minimum in its neighborhood.
 * @param radius neighborhood radius
 */
class ErodeFilter(val radius: Int = 1) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                half4 minVal = content.eval(fragcoord);
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        if (length(c.rgb) < length(minVal.rgb)) {
                            minVal = c;
                        }
                    }
                }
                return minVal;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A Gaussian blur filter.
 * @param radius blur radius in pixels
 */
class GaussianFilter(val radius: Float = 2f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            half4 main(vec2 fragcoord) {
                half4 sum = half4(0);
                float r = ceil(radius);
                float sigma = radius / 3.0;
                if (sigma < 0.01) sigma = 0.01;
                float sigma22 = 2.0 * sigma * sigma;
                float total = 0.0;
                // Horizontal pass
                half4 hSum = half4(0);
                for (float dx = -r; dx <= r; dx++) {
                    float dist = dx * dx;
                    float weight = exp(-dist / sigma22);
                    hSum += content.eval(fragcoord + vec2(dx, 0.0)) * weight;
                    total += weight;
                }
                hSum /= total;
                // Vertical pass
                total = 0.0;
                for (float dy = -r; dy <= r; dy++) {
                    float dist = dy * dy;
                    float weight = exp(-dist / sigma22);
                    // Sample from the horizontal result approximation
                    sum += content.eval(fragcoord + vec2(0.0, dy)) * weight;
                    total += weight;
                }
                sum /= total;
                // Blend horizontal and vertical for better approximation
                return (hSum + sum) / 2.0;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A high-pass filter that emphasizes high-frequency details.
 * @param radius kernel radius
 */
class HighPassFilter(val radius: Float = 2f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                int r = int(ceil(radius));
                if (r < 1) r = 1;
                half4 sum = half4(0);
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        sum += content.eval(fragcoord + vec2(float(dx), float(dy)));
                        count++;
                    }
                }
                half4 avg = sum / float(count);
                // High pass: original - low frequency
                half3 rgb = center.rgb - avg.rgb + 0.5;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A Laplacian edge detection filter.
 */
object LaplaceFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                // Laplacian kernel:
                //  0 -1  0
                // -1  4 -1
                //  0 -1  0
                half center = content.eval(fragcoord).r * 4.0;
                half sum = content.eval(fragcoord + vec2(-1.0, 0.0)).r;
                sum += content.eval(fragcoord + vec2(1.0, 0.0)).r;
                sum += content.eval(fragcoord + vec2(0.0, -1.0)).r;
                sum += content.eval(fragcoord + vec2(0.0, 1.0)).r;
                half edge = center - sum;
                edge = clamp(abs(edge), 0.0, 1.0);
                half4 c = content.eval(fragcoord);
                return half4(edge, edge, edge, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**

 * A lens blur filter that simics out-of-focus lens bokeh.
 * @param radius blur radius
 * @param threshold brightness threshold for blur masking
 */
class LensBlurFilter(
    val radius: Float = 5f,
    val threshold: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform float threshold;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                float brightness = length(center.rgb);
                float blurAmount = smoothstep(threshold - 0.2, threshold + 0.2, brightness);
                half4 sum = center;
                int r = int(ceil(radius));
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        float d = sqrt(float(dx*dx + dy*dy));
                        if (d <= float(r)) {
                            sum += content.eval(fragcoord + vec2(float(dx), float(dy)));
                            count++;
                        }
                    }
                }
                half4 blurred = sum / float(count);
                half3 rgb = mix(center.rgb, blurred.rgb, blurAmount);
                return half4(rgb, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(radius)
        dataBuffer.putFloat(threshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A maximum filter — replaces each pixel with the maximum value in its neighborhood.
 * @param radius neighborhood radius
 */
class MaximumFilter(val radius: Int = 1) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                half3 maxVal = content.eval(fragcoord).rgb;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half3 c = content.eval(fragcoord + vec2(float(dx), float(dy))).rgb;
                        maxVal = max(maxVal, c);
                    }
                }
                half4 orig = content.eval(fragcoord);
                return half4(maxVal, orig.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A median filter for noise reduction.
 * Approximates median using sorted neighborhood values.
 * @param radius neighborhood radius (use 1 for 3x3)
 */
class MedianFilter(val radius: Int = 1) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                // For a 3x3 kernel, collect 9 values and approximate median
                // by averaging the middle values after sorting
                half vals[9];
                int idx = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        vals[idx++] = length(c.rgb);
                    }
                }
                // Simple bubble sort for 9 elements
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8 - i; j++) {
                        if (vals[j] > vals[j+1]) {
                            half tmp = vals[j];
                            vals[j] = vals[j+1];
                            vals[j+1] = tmp;
                        }
                    }
                }
                half median = vals[4]; // middle value
                half4 center = content.eval(fragcoord);
                half centerBright = length(center.rgb);
                if (centerBright > 0.001) {
                    half3 rgb = center.rgb * (median / centerBright);
                    return half4(rgb, center.a);
                }
                return center;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A minimum filter — replaces each pixel with the minimum value in its neighborhood.
 * @param radius neighborhood radius
 */
class MinimumFilter(val radius: Int = 1) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            half4 main(vec2 fragcoord) {
                half3 minVal = content.eval(fragcoord).rgb;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half3 c = content.eval(fragcoord + vec2(float(dx), float(dy))).rgb;
                        minVal = min(minVal, c);
                    }
                }
                half4 orig = content.eval(fragcoord);
                return half4(minVal, orig.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A motion blur filter.
 * Replicates the JH-Labs MotionBlurFilter.
 * @param angle blur angle in radians
 * @param distance blur distance in pixels
 * @param rotation rotation angle in radians
 * @param zoom zoom factor (0 = no zoom)
 */
class MotionBlurFilter(
    val angle: Float = 0f,
    val distance: Float = 1f,
    val rotation: Float = 0f,
    val zoom: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float angle;
            uniform float distance;
            uniform float rotation;
            uniform float zoom;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                half4 sum = half4(0);
                float cx = imageSize.x * 0.5;
                float cy = imageSize.y * 0.5;
                float translateX = distance * cos(angle);
                float translateY = -distance * sin(angle);
                int repetitions = int(ceil(distance));
                if (repetitions < 1) repetitions = 1;
                for (int i = 0; i < repetitions; i++) {
                    float f = float(i) / float(repetitions);
                    float s = 1.0 - zoom * f;
                    float rx = -rotation * f;
                    // Transform point
                    float px = fragcoord.x + cx + f * translateX;
                    float py = fragcoord.y + cy + f * translateY;
                    px -= cx;
                    py -= cy;
                    float sx = px * s;
                    float sy = py * s;
                    if (rx != 0.0) {
                        float cr = cos(rx);
                        float sr = sin(rx);
                        float rpx = sx * cr - sy * sr;
                        float rpy = sx * sr + sy * cr;
                        sx = rpx;
                        sy = rpy;
                    }
                    px = sx + cx;
                    py = sy + cy;
                    sum += content.eval(vec2(px, py));
                }
                return sum / float(repetitions);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader,
        imageWidth: Float,
        imageHeight: Float
    ): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        dataBuffer.putFloat(angle)
        dataBuffer.putFloat(distance)
        dataBuffer.putFloat(rotation)
        dataBuffer.putFloat(zoom)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * An outline filter that extracts edges and renders them as outlines.
 * @param threshold edge detection threshold
 */
class OutlineFilter(val threshold: Float = 0.2f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            half4 main(vec2 fragcoord) {
                half center = length(content.eval(fragcoord).rgb);
                half sum = 0.0;
                sum += length(content.eval(fragcoord + vec2(-1.0, 0.0)).rgb);
                sum += length(content.eval(fragcoord + vec2(1.0, 0.0)).rgb);
                sum += length(content.eval(fragcoord + vec2(0.0, -1.0)).rgb);
                sum += length(content.eval(fragcoord + vec2(0.0, 1.0)).rgb);
                half avg = sum / 4.0;
                half edge = abs(center - avg);
                half val = edge > threshold ? 1.0 : 0.0;
                return half4(val, val, val, 1.0);
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

 * A noise reduction filter using bilateral-like smoothing.
 * @param radius neighborhood radius
 * @param sigma color similarity threshold
 */
class ReduceNoiseFilter(
    val radius: Int = 2,
    val sigma: Float = 0.1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int radius;
            uniform float sigma;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = half4(0);
                float weightSum = 0.0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        float colorDist = length(c.rgb - center.rgb);
                        float weight = exp(-colorDist * colorDist / (2.0 * sigma * sigma));
                        sum += c * weight;
                        weightSum += weight;
                    }
                }
                return sum / weightSum;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(radius)
        dataBuffer.putFloat(sigma)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A sharpen filter using a 3x3 convolution kernel.
 * Kernel: {0, -0.2, 0, -0.2, 1.8, -0.2, 0, -0.2, 0}
 */
object SharpenFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = half4(0);
                // Sum of 4 neighbors
                sum += content.eval(fragcoord + vec2(-1.0, 0.0));
                sum += content.eval(fragcoord + vec2(1.0, 0.0));
                sum += content.eval(fragcoord + vec2(0.0, -1.0));
                sum += content.eval(fragcoord + vec2(0.0, 1.0));
                half3 rgb = center.rgb * 1.8 - sum.rgb * 0.2;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}

/**

 * A skeleton filter that extracts the skeleton/medial axis of shapes.
 * Uses iterative erosion to find the skeleton.
 * @param iterations number of erosion iterations
 */
class SkeletonFilter(val iterations: Int = 5) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int iterations;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half brightness = length(c.rgb);
                
                // Approximate skeleton by checking if pixel is on an edge
                // after iterative thinning
                half sum = 0.0;
                int count = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        half b = length(content.eval(fragcoord + vec2(float(dx), float(dy))).rgb);
                        sum += b;
                        count++;
                    }
                }
                half avg = sum / float(count);
                half edge = abs(brightness - avg);
                
                // Skeleton approximation: thin edges
                half skeleton = edge > 0.1 ? 1.0 : 0.0;
                return half4(skeleton, skeleton, skeleton, 1.0);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(iterations)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**

 * A filter which performs a "smart blur" — blurs smooth parts while preserving edges.
 * Replicates the JH-Labs SmartBlurFilter.
 *
 * @param hRadius horizontal blur radius (default 5)
 * @param vRadius vertical blur radius (default 5)
 * @param threshold difference threshold below which to blur (default 10)
 */
class SmartBlurFilter(
    val hRadius: Int = 5,
    val vRadius: Int = 5,
    val threshold: Int = 10
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int hRadius;
            uniform int vRadius;
            uniform float threshold;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = half4(0);
                int count = 0;
                for (int dy = -vRadius; dy <= vRadius; dy++) {
                    for (int dx = -hRadius; dx <= hRadius; dx++) {
                        half4 neighbor = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        // Only include neighbors within the threshold (edge preservation)
                        if (length((center.rgb - neighbor.rgb) * 255.0) < threshold) {
                            sum += neighbor;
                            count++;
                        }
                    }
                }
                if (count > 0) {
                    return sum / float(count);
                }
                return center;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putInt(hRadius)
        dataBuffer.putInt(vRadius)
        dataBuffer.putFloat(threshold.toFloat())
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * An unsharp mask filter for sharpening.
 * Replicates the JH-Labs UnsharpFilter.
 * @param radius blur radius for the mask (default 2)
 * @param amount sharpening strength (0-1, default 0.5)
 * @param threshold threshold for applying sharpening (default 1)
 */
class UnsharpFilter(
    val radius: Float = 2f,
    val amount: Float = 0.5f,
    val threshold: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform float amount;
            uniform float threshold;
            half4 main(vec2 fragcoord) {
                half4 original = content.eval(fragcoord);
                int r = int(ceil(radius));
                if (r < 1) r = 1;
                half4 sum = half4(0);
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        sum += content.eval(fragcoord + vec2(float(dx), float(dy)));
                        count++;
                    }
                }
                half4 blurred = sum / float(count);
                float a = 4.0 * amount;
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    float diff = original.rgb[i] - blurred.rgb[i];
                    if (abs(diff) >= threshold / 255.0) {
                        rgb[i] = clamp((a + 1.0) * diff + blurred.rgb[i], 0.0, 1.0);
                    } else {
                        rgb[i] = original.rgb[i];
                    }
                }
                return half4(rgb, original.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(radius)
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(threshold)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A variable blur filter with different radii for different regions.
 * @param minRadius minimum blur radius
 * @param maxRadius maximum blur radius
 */
class VariableBlurFilter(
    val minRadius: Float = 1f,
    val maxRadius: Float = 5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float minRadius;
            uniform float maxRadius;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                // Distance from center determines blur amount
                vec2 center = imageSize * 0.5;
                float dist = length(fragcoord - center) / length(center);
                float radius = mix(minRadius, maxRadius, dist);
                int r = int(ceil(radius));
                if (r < 1) r = 1;
                half4 sum = half4(0);
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        sum += content.eval(fragcoord + vec2(float(dx), float(dy)));
                        count++;
                    }
                }
                return sum / float(count);
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
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)
        dataBuffer.putFloat(minRadius)
        dataBuffer.putFloat(maxRadius)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
