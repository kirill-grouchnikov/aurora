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

// Shared shader functions reused by both LightFilter and ChromeFilter.

internal val applyBumpShapeFn = """
            // Apply bump shape transformation to a height value.
            // Replicates the JH-Labs ImageMath functions used in LightFilter bumpShape.
            float applyBumpShape(float v) {
                if (bumpShape == 1) {
                    // SMOOTH_STEP: v *= smoothStep(0.45, 0.55, v)
                    float t = clamp((v - 0.45) / (0.55 - 0.45), 0.0, 1.0);
                    t = t * t * (3.0 - 2.0 * t);
                    v *= t;
                } else if (bumpShape == 2) {
                    // CLAMP_MIN: v = v < 0.5 ? 0.5 : v
                    if (v < 0.5) v = 0.5;
                } else if (bumpShape == 3) {
                    // TRIANGLE: repeating triangle wave, wavelength 1.0
                    v = mod(v, 1.0);
                    v = abs(v - 0.5) * 2.0;
                } else if (bumpShape == 4) {
                    // CIRCLE_DOWN: concave bevel shape, sqrt(1 - (1-v)^2)
                    v = sqrt(max(0.0, 1.0 - (1.0 - v) * (1.0 - v)));
                } else if (bumpShape == 5) {
                    // GAIN: gamma-like contrast adjustment with gain 0.75
                    // gain(a, b) where b=0.75: raises darks, lowers lights toward center
                    float g = 0.75;
                    if (v <= g * g) {
                        v = v * v / (2.0 * g * g - 2.0 * g * v + 1.0);
                    } else {
                        v = 1.0 - (1.0 - v) * (1.0 - v) / (2.0 * (1.0 - g) * (1.0 - g) - 2.0 * (1.0 - g) * (1.0 - v) + 1.0);
                    }
                }
                return v;
            }
""".trimIndent()

internal val sampleBumpHeightFn = """
            // Sample bump height at a pixel, optionally applying Gaussian blur (bumpSoftness).
            // Replicates the JH-Labs LightFilter bumpSoftness preprocessing.
            float sampleBumpHeight(vec2 fragcoord) {
                if (bumpSoftness < 0.5) {
                    // No softness - direct sample
                    float h = length(content.eval(fragcoord).rgb);
                    return applyBumpShape(h);
                }
                // Gaussian-weighted neighborhood sample for softness
                int r = int(ceil(bumpSoftness));
                float sigma = bumpSoftness / 3.0;
                if (sigma < 0.01) sigma = 0.01;
                float sigma22 = 2.0 * sigma * sigma;
                float total = 0.0;
                float sum = 0.0;
                for (int dy = -10; dy <= 10; dy++) {
                    if ((dy < -r) || (dy > r)) {
                        continue;
                    } 
                    for (int dx = -10; dx <= 10; dx++) {
                        if ((dx < -r) || (dx > r)) {
                            continue;
                        } 
                        float dist2 = float(dx * dx + dy * dy);
                        float weight = exp(-dist2 / sigma22);
                        float h = length(content.eval(fragcoord + vec2(dx, dy)).rgb);
                        sum += h * weight;
                        total += weight;
                    }
                }
                float h = sum / total;
                return applyBumpShape(h);
            }
""".trimIndent()

internal val computeNormalFn = """
            // Compute the surface normal from the bump map (image brightness).
            // Replicates the JH-Labs LightFilter algorithm:
            // samples a 3x3 height grid, computes cross products of gradient vectors
            // for up to 4 corner normals, and averages them.
            half3 computeNormal(vec2 fragcoord, float width, float height) {
                // Scale factor for height differences, matching original: |6.0 * bumpHeight|
                float width45 = abs(6.0 * bumpHeight);
                bool invertBumps = bumpHeight < 0.0;

                // Sample the 3x3 height grid (heightWindow equivalent), with optional softness and shape
                // h[y+1][x-1], h[y+1][x], h[y+1][x+1]
                // h[y  ][x-1], h[y  ][x], h[y  ][x+1]
                // h[y-1][x-1], h[y-1][x], h[y-1][x+1]
                float h00 = sampleBumpHeight(fragcoord);
                float hL  = sampleBumpHeight(fragcoord + vec2(-1.0, 0.0));
                float hR  = sampleBumpHeight(fragcoord + vec2(1.0, 0.0));
                float hU  = sampleBumpHeight(fragcoord + vec2(0.0, -1.0));
                float hD  = sampleBumpHeight(fragcoord + vec2(0.0, 1.0));
                float hUL = sampleBumpHeight(fragcoord + vec2(-1.0, -1.0));
                float hUR = sampleBumpHeight(fragcoord + vec2(1.0, -1.0));
                float hDL = sampleBumpHeight(fragcoord + vec2(-1.0, 1.0));
                float hDR = sampleBumpHeight(fragcoord + vec2(1.0, 1.0));

                // Scale heights by width45, matching original
                float m0 = width45 * h00;
                float m1 = width45 * hL;   // left
                float m2 = width45 * hU;   // up
                float m3 = width45 * hR;   // right
                float m4 = width45 * hD;   // down
                float mUL = width45 * hUL; // up-left
                float mUR = width45 * hUR; // up-right
                float mDL = width45 * hDL; // down-left
                float mDR = width45 * hDR; // down-right

                // Check boundary conditions
                bool x0 = fragcoord.x > 0.0;
                bool x1 = fragcoord.x < width - 1.0;
                bool y0 = fragcoord.y > 0.0;
                bool y1 = fragcoord.y < height - 1.0;

                // Calculate four normals using cross products of gradient vectors
                // matching the original LightFilter algorithm
                half3 normal = half3(0.0, 0.0, 0.0);
                int count = 0;

                // Bottom-left corner: vectors to left and down
                if (x0 && y1) {
                    half3 v1 = half3(-1.0, 0.0, m1 - m0);
                    half3 v2 = half3(0.0, 1.0, m4 - m0);
                    half3 n = cross(v1, v2);
                    n = normalize(n);
                    if (n.z < 0.0) n.z = -n.z;
                    normal += n;
                    count++;
                }

                // Top-left corner: vectors to left and up
                if (x0 && y0) {
                    half3 v1 = half3(-1.0, 0.0, m1 - m0);
                    half3 v2 = half3(0.0, -1.0, m2 - m0);
                    half3 n = cross(v1, v2);
                    n = normalize(n);
                    if (n.z < 0.0) n.z = -n.z;
                    normal += n;
                    count++;
                }

                // Top-right corner: vectors to up and right
                if (y0 && x1) {
                    half3 v1 = half3(0.0, -1.0, m2 - m0);
                    half3 v2 = half3(1.0, 0.0, m3 - m0);
                    half3 n = cross(v1, v2);
                    n = normalize(n);
                    if (n.z < 0.0) n.z = -n.z;
                    normal += n;
                    count++;
                }

                // Bottom-right corner: vectors to right and down
                if (x1 && y1) {
                    half3 v1 = half3(1.0, 0.0, m3 - m0);
                    half3 v2 = half3(0.0, 1.0, m4 - m0);
                    half3 n = cross(v1, v2);
                    n = normalize(n);
                    if (n.z < 0.0) n.z = -n.z;
                    normal += n;
                    count++;
                }

                // Average the normals
                if (count > 0) {
                    normal /= float(count);
                } else {
                    // Fallback for edge pixels with no neighbors
                    normal = half3(0.0, 0.0, 1.0);
                }

                // Handle inverted bumps
                if (invertBumps) {
                    normal.x = -normal.x;
                    normal.y = -normal.y;
                }

                return normal;
            }
""".trimIndent()

internal val phongShadeFn = """
            // Phong shade a single pixel with all lights
            half3 phongShade(vec2 fragcoord, half3 normal, half3 viewpoint, half3 diffuseColor, half3 specularColor) {
                // Ambient component
                half3 shadedColor = diffuseColor * ambientIntensity;

                for (int i = 0; i < 4; i++) {
                    if (i >= lightCount) {
                        break;
                    }
                    int lightType = int(lightTypes[i]);
                    half3 lightColor = half3(lightColorRGB[i*3], lightColorRGB[i*3+1], lightColorRGB[i*3+2]);
                    half3 lightDir = half3(lightDirections[i*3], lightDirections[i*3+1], lightDirections[i*3+2]);
                    half3 lightPos = half3(lightPositions[i*3], lightPositions[i*3+1], lightPositions[i*3+2]);
                    float cosConeAngle = lightCosConeAngles[i];
                    float spotFocus = lightFocus[i];

                    // Compute light direction to this point
                    half3 L;
                    if (lightType == 1) { // DISTANT
                        L = lightDir;
                    } else {
                        // POINT or SPOT: direction from fragment to light position
                        L = normalize(half3(lightPos.x - fragcoord.x, lightPos.y - fragcoord.y, lightPos.z));
                    }

                    float NdotL = dot(normal, L);
                    if (NdotL < 0.0) continue;

                    // Spotlight cone check
                    if (lightType == 3) { // SPOT
                        float dDotL = dot(lightDir, -L);
                        if (dDotL < cosConeAngle) continue;
                    }

                    // Specular: reflect light around normal
                    half3 R = normalize(2.0 * NdotL * normal - L);
                    half3 V = normalize(viewpoint);
                    float RdotV = dot(R, V);

                    float specular = 0.0;
                    if (RdotV > 0.0) {
                        // Fast approximation to pow(RdotV, highlight)
                        specular = RdotV / (highlight - highlight * RdotV + RdotV);
                    }

                    // Spotlight falloff
                    if (lightType == 3) { // SPOT
                        float dDotL = dot(lightDir, -L);
                        float e = dDotL / cosConeAngle;
                        e = e * e * e * e * e;
                        float spotFactor = pow(dDotL / cosConeAngle, spotFocus * 10.0) * (1.0 - e);
                        specular *= spotFactor;
                        NdotL *= spotFactor;
                    }

                    // Accumulate diffuse and specular
                    half3 diffuseContrib = diffuseColor * diffuseReflectivity * lightColor * NdotL;
                    half3 specularContrib = specularColor * specularReflectivity * lightColor * specular;
                    shadedColor += diffuseContrib + specularContrib;
                }

                return clamp(shadedColor, 0.0, 1.0);
            }
""".trimIndent()

/**
 * A filter which simulates realistic lighting with full Phong shading.
 * Replicates the JH-Labs LightFilter which is the base class of ChromeFilter.
 *
 * The filter applies Phong shading with configurable material properties and multiple
 * light sources, supporting bump mapping, bump softness, and bump shape transformations.
 *
 * @param material material properties for Phong shading
 * @param lights list of light sources (default: one distant light)
 * @param bumpHeight bump map height scale (default 1.0)
 * @param bumpSoftness radius for Gaussian blur preprocessing of the bump map (default 0.0, disabled)
 * @param bumpShape bump height transformation shape (default NONE)
 * @param viewDistance viewer distance from surface for specular calculations (default 10000.0)
 * @param useImageColors if true, use image colors as diffuse; if false, use material diffuseColor
 */
class LightFilter(
    val material: Material = Material(),
    val lights: List<Light> = listOf(Light(type = LightType.DISTANT)),
    val bumpHeight: Float = 1.0f,
    val bumpSoftness: Float = 0f,
    val bumpShape: BumpShape = BumpShape.NONE,
    val viewDistance: Float = 10000.0f,
    val useImageColors: Boolean = false
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float materialDiffuseRGB[3];
            uniform float materialSpecularRGB[3];
            uniform float ambientIntensity;
            uniform float diffuseReflectivity;
            uniform float specularReflectivity;
            uniform float highlight;
            uniform float reflectivity;
            uniform float opacity;
            uniform int lightCount;
            uniform float lightTypes[4];  // easier to pass floats in
            uniform float lightColorRGB[4*3];
            uniform float lightDirections[4*3];
            uniform float lightPositions[4*3];
            uniform float lightCosConeAngles[4];
            uniform float lightFocus[4];
            uniform float bumpHeight;
            uniform float bumpSoftness;
            uniform int bumpShape;
            uniform float viewDistance;
            uniform int useImageColors;
            uniform float2 imageSize;

            $applyBumpShapeFn

            $sampleBumpHeightFn

            $computeNormalFn

            $phongShadeFn

            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);

                // Compute surface normal from bump map using the original LightFilter algorithm
                half3 normal = computeNormal(fragcoord, imageSize.x, imageSize.y);

                // Viewpoint for specular calculations
                half3 viewpoint = half3(
                    imageSize.x * 0.5 - fragcoord.x,
                    imageSize.y * 0.5 - fragcoord.y,
                    viewDistance
                );

                // Get diffuse color - from image or from material
                half3 diffuseColor;
                if (useImageColors != 0) {
                    diffuseColor = c.rgb;
                } else {
                    diffuseColor = half3(materialDiffuseRGB[0], materialDiffuseRGB[1], materialDiffuseRGB[2]);
                }

                // Specular color from material
                half3 specularColor = half3(materialSpecularRGB[0], materialSpecularRGB[1], materialSpecularRGB[2]);

                // Apply environment reflectivity (simple approximation)
                if (reflectivity > 0.0) {
                    half3 envColor = c.rgb;
                    diffuseColor = mix(diffuseColor, envColor, reflectivity);
                }

                // Phong shading
                half3 result = phongShade(fragcoord, normal, viewpoint, diffuseColor, specularColor);

                return half4(result, c.a * opacity);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(
        inputShader: org.jetbrains.skia.Shader?,
        imageWidth: Float,
        imageHeight: Float
    ): org.jetbrains.skia.Shader {
        // Maximum 4 lights supported in the shader
        val lightCount = lights.size.coerceAtMost(4)

        // Calculate buffer size:
        // materialDiffuseRGB(12) + materialSpecularRGB(12) +
        // ambientIntensity(4) + diffuseReflectivity(4) + specularReflectivity(4) + highlight(4) +
        // reflectivity(4) + opacity(4) + lightCount(4) +
        // lightTypes(16) + lightColorRGB(48) + lightDirections(48) + lightPositions(48) +
        // lightCosConeAngles(16) + lightFocus(16) +
        // bumpHeight(4) + bumpSoftness(4) + bumpShape(4) + viewDistance(4) + useImageColors(4) + imageSize(8) = 292 bytes
        val bufferSize = 292
        val dataBuffer = ByteBuffer.allocate(bufferSize).order(ByteOrder.LITTLE_ENDIAN)

        // Material properties
        val diffuseRGB = material.diffuseRGB
        dataBuffer.putFloat(diffuseRGB[0])
        dataBuffer.putFloat(diffuseRGB[1])
        dataBuffer.putFloat(diffuseRGB[2])

        val specularRGB = material.specularRGB
        dataBuffer.putFloat(specularRGB[0])
        dataBuffer.putFloat(specularRGB[1])
        dataBuffer.putFloat(specularRGB[2])

        dataBuffer.putFloat(material.ambientIntensity)
        dataBuffer.putFloat(material.diffuseReflectivity)
        dataBuffer.putFloat(material.specularReflectivity)
        dataBuffer.putFloat(material.highlight)
        dataBuffer.putFloat(material.reflectivity)
        dataBuffer.putFloat(material.opacity)

        // Light count
        dataBuffer.putInt(lightCount)

        // Light arrays (pad to 4)
        for (i in 0 until 4) {
            if (i < lightCount) {
                val light = lights[i]
                dataBuffer.putInt(light.type.ordinal)
                val c = light.colorRGB
                dataBuffer.putFloat(c[0])
                dataBuffer.putFloat(c[1])
                dataBuffer.putFloat(c[2])
                val d = light.direction
                dataBuffer.putFloat(d[0])
                dataBuffer.putFloat(d[1])
                dataBuffer.putFloat(d[2])
                val p = light.position(imageWidth, imageHeight)
                dataBuffer.putFloat(p[0])
                dataBuffer.putFloat(p[1])
                dataBuffer.putFloat(p[2])
                dataBuffer.putFloat(light.cosConeAngle)
                dataBuffer.putFloat(light.focus)
            } else {
                dataBuffer.putInt(0)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
                dataBuffer.putFloat(0f)
            }
        }

        // Bump and view parameters
        dataBuffer.putFloat(bumpHeight)
        dataBuffer.putFloat(bumpSoftness)
        dataBuffer.putInt(bumpShape.ordinal)
        dataBuffer.putFloat(viewDistance)
        dataBuffer.putInt(if (useImageColors) 1 else 0)
        dataBuffer.putFloat(imageWidth)
        dataBuffer.putFloat(imageHeight)

        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
