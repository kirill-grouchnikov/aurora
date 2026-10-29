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

/**
 * A filter which adds a glow effect to bright areas of the image.
 * @param radius glow radius in pixels
 * @param intensity glow intensity (0-1)
 */
class GlowFilter(
    val radius: Float = 5f,
    val intensity: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform float intensity;
            half4 main(vec2 fragcoord) {
                half4 center = content.eval(fragcoord);
                half4 sum = half4(0);
                int r = int(ceil(radius));
                if (r < 1) r = 1;
                int count = 0;
                for (int dy = -r; dy <= r; dy++) {
                    for (int dx = -r; dx <= r; dx++) {
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        sum += c;
                        count++;
                    }
                }
                half4 blur = sum / float(count);
                // Glow: add blurred bright areas
                half3 glow = blur.rgb * intensity * step(0.5, blur.r);
                half3 rgb = center.rgb + glow;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, center.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(radius)
        dataBuffer.putFloat(intensity)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
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
 * A filter which simulates directional lighting.
 * @param azimuth light azimuth angle in radians
 * @param elevation light elevation angle in radians
 * @param direction light direction factor
 * @param brightness brightness factor
 */
class LightFilter(
    val azimuth: Float = 1.178f,
    val elevation: Float = 0.524f,
    val direction: Float = 1f,
    val brightness: Float = 1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float azimuth;
            uniform float elevation;
            uniform float direction;
            uniform float brightness;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float h = length(c.rgb);
                float hL = length(content.eval(fragcoord + vec2(-1.0, 0.0)).rgb);
                float hR = length(content.eval(fragcoord + vec2(1.0, 0.0)).rgb);
                float hU = length(content.eval(fragcoord + vec2(0.0, -1.0)).rgb);
                float hD = length(content.eval(fragcoord + vec2(0.0, 1.0)).rgb);
                
                float Nx = (hL - hR) * direction;
                float Ny = (hU - hD) * direction;
                float Nz = 1.0;
                
                float Lx = cos(azimuth) * cos(elevation);
                float Ly = sin(azimuth) * cos(elevation);
                float Lz = sin(elevation);
                
                float NdotL = (Nx * Lx + Ny * Ly + Nz * Lz) / (sqrt(Nx*Nx + Ny*Ny + Nz*Nz) * sqrt(Lx*Lx + Ly*Ly + Lz*Lz));
                float shade = max(NdotL, 0.0) * brightness + (1.0 - brightness);
                half3 rgb = c.rgb * shade;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(azimuth)
        dataBuffer.putFloat(elevation)
        dataBuffer.putFloat(direction)
        dataBuffer.putFloat(brightness)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * Material properties for lighting calculations in [ChromeFilter].
 * Replicates the JH-Labs LightFilter.Material.
 *
 * @param diffuseColor diffuse color as ARGB int (default 0xff888888)
 * @param specularColor specular color as ARGB int (default 0xffffffff)
 * @param ambientIntensity ambient light intensity (0-1, default 0.5)
 * @param diffuseReflectivity diffuse reflectivity (0-1, default 1.0)
 * @param specularReflectivity specular reflectivity (0-1, default 1.0)
 * @param highlight specular highlight exponent (default 3.0)
 * @param reflectivity environment reflectivity (0-1, default 0.0)
 * @param opacity material opacity (0-1, default 1.0)
 */
data class Material(
    val diffuseColor: Int = 0xff888888.toInt(),
    val specularColor: Int = 0xffffffff.toInt(),
    val ambientIntensity: Float = 0.5f,
    val diffuseReflectivity: Float = 1.0f,
    val specularReflectivity: Float = 1.0f,
    val highlight: Float = 3.0f,
    val reflectivity: Float = 0.0f,
    val opacity: Float = 1.0f
) {
    /** Extract the RGB components (0-1) from the ARGB int. */
    val diffuseRGB: FloatArray
        get() = floatArrayOf(
            ((diffuseColor shr 16) and 0xff) / 255f,
            ((diffuseColor shr 8) and 0xff) / 255f,
            (diffuseColor and 0xff) / 255f
        )

    /** Extract the RGB components (0-1) from the specular ARGB int. */
    val specularRGB: FloatArray
        get() = floatArrayOf(
            ((specularColor shr 16) and 0xff) / 255f,
            ((specularColor shr 8) and 0xff) / 255f,
            (specularColor and 0xff) / 255f
        )
}

/**
 * Light type for [Light].
 */
enum class LightType {
    AMBIENT, DISTANT, POINT, SPOT
}

/**
 * Bump shape transformation for [ChromeFilter].
 * Replicates the JH-Labs LightFilter bumpShape options.
 */
enum class BumpShape {
    /** No transformation (default). */
    NONE,
    /** Smooth step: raises values above 0.45-0.55 range, suppresses below. */
    SMOOTH_STEP,
    /** Clamp minimum: values below 0.5 are raised to 0.5. */
    CLAMP_MIN,
    /** Triangle wave: creates a repeating triangle pattern from the height values. */
    TRIANGLE,
    /** Circle down: creates a concave bevel shape. */
    CIRCLE_DOWN,
    /** Gain: applies a gamma-like contrast adjustment (gain 0.75). */
    GAIN
}

/**
 * A light source for [ChromeFilter] Phong shading.
 * Replicates the JH-Labs LightFilter.Light.
 *
 * @param type light type (ambient, distant, point, spot)
 * @param color light color as ARGB int (default white)
 * @param intensity light intensity (default 1.0)
 * @param azimuth azimuth angle in radians (default ~270°)
 * @param elevation elevation angle in radians (default ~30°)
 * @param focus spotlight focus 0-1, only for SPOT type (default 0.5)
 * @param centreX light center X as proportion of image width (default 0.5)
 * @param centreY light center Y as proportion of image height (default 0.5)
 * @param coneAngle spotlight cone angle in radians, only for SPOT type (default 30°)
 * @param distance light distance from surface, for POINT/SPOT types (default 100.0)
 */
data class Light(
    val type: LightType = LightType.DISTANT,
    val color: Int = 0xffffffff.toInt(),
    val intensity: Float = 1.0f,
    val azimuth: Float = 270f * 3.14159265f / 180f,
    val elevation: Float = 0.5235987755982988f,
    val focus: Float = 0.5f,
    val centreX: Float = 0.5f,
    val centreY: Float = 0.5f,
    val coneAngle: Float = 3.14159265f / 6f,
    val distance: Float = 100.0f
) {
    /** Extract the RGB components (0-1) from the ARGB int, scaled by intensity. */
    val colorRGB: FloatArray
        get() {
            val r = ((color shr 16) and 0xff) / 255f
            val g = ((color shr 8) and 0xff) / 255f
            val b = (color and 0xff) / 255f
            return floatArrayOf(r * intensity, g * intensity, b * intensity)
        }

    /** Precomputed cosine of the cone angle for spotlight. */
    val cosConeAngle: Float
        get() = Math.cos(coneAngle.toDouble()).toFloat()

    /** Precomputed light direction vector (normalized). */
    val direction: FloatArray
        get() {
            val lx = Math.cos(azimuth.toDouble()) * Math.cos(elevation.toDouble())
            val ly = Math.sin(azimuth.toDouble()) * Math.cos(elevation.toDouble())
            val lz = Math.sin(elevation.toDouble())
            val len = Math.sqrt(lx * lx + ly * ly + lz * lz).toFloat()
            return floatArrayOf(lx.toFloat() / len, ly.toFloat() / len, lz.toFloat() / len)
        }

    /** Precomputed light position for POINT/SPOT lights, given image dimensions. */
    fun position(width: Float, height: Float): FloatArray {
        val d = direction
        return when (type) {
            LightType.POINT, LightType.SPOT -> {
                floatArrayOf(
                    d[0] * distance + width * centreX,
                    d[1] * distance + height * centreY,
                    d[2] * distance
                )
            }
            else -> floatArrayOf(0f, 0f, 0f)
        }
    }
}

/**
 * A filter which simulates chrome/metallic effect with full Phong lighting.
 * Replicates the JH-Labs ChromeFilter which extends LightFilter.
 *
 * The filter applies Phong shading with configurable material properties and multiple
 * light sources, then applies the chrome transfer function to produce a metallic look.
 *
 * @param amount amount of chrome effect (0-1, default 0.5)
 * @param exposure exposure of the chrome effect (default 1.0)
 * @param material material properties for Phong shading
 * @param lights list of light sources (default: one distant light)
 * @param bumpHeight bump map height scale (default 1.0)
 * @param bumpSoftness radius for Gaussian blur preprocessing of the bump map (default 0.0, disabled)
 * @param bumpShape bump height transformation shape (default NONE)
 * @param viewDistance viewer distance from surface for specular calculations (default 10000.0)
 * @param useImageColors if true, use image colors as diffuse; if false, use material diffuseColor
 */
class ChromeFilter(
    val amount: Float = 0.5f,
    val exposure: Float = 1f,
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
            uniform float amount;
            uniform float exposure;
            uniform float materialDiffuseRGB[3];
            uniform float materialSpecularRGB[3];
            uniform float ambientIntensity;
            uniform float diffuseReflectivity;
            uniform float specularReflectivity;
            uniform float highlight;
            uniform float reflectivity;
            uniform float opacity;
            uniform int lightCount;
            uniform int lightTypes[4];
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

            // Phong shade a single pixel with all lights
            half3 phongShade(vec2 fragcoord, half3 normal, half3 viewpoint, half3 diffuseColor, half3 specularColor) {
                // Ambient component
                half3 shadedColor = diffuseColor * ambientIntensity;

                for (int i = 0; i < 4; i++) {
                    if (i >= lightCount) {
                        break;
                    }
                    int lightType = lightTypes[i];
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

            // Chrome transfer function
            half3 chromeTransfer(half3 c) {
                half3 rgb;
                for (int i = 0; i < 3; i++) {
                    float v = c[i];
                    v += amount * sin(v * 6.28318);
                    v = 1.0 - exp(-v * exposure);
                    rgb[i] = clamp(v, 0.0, 1.0);
                }
                return rgb;
            }

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
                half3 shaded = phongShade(fragcoord, normal, viewpoint, diffuseColor, specularColor);

                // Apply chrome transfer function
                half3 result = chromeTransfer(shaded);
                
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
        // amount(4) + exposure(4) + materialDiffuseRGB(12) + materialSpecularRGB(12) +
        // ambientIntensity(4) + diffuseReflectivity(4) + specularReflectivity(4) + highlight(4) +
        // reflectivity(4) + opacity(4) + lightCount(4) +
        // lightTypes(16) + lightColorRGB(48) + lightDirections(48) + lightPositions(48) +
        // lightCosConeAngles(16) + lightFocus(16) +
        // bumpHeight(4) + bumpSoftness(4) + bumpShape(4) + viewDistance(4) + useImageColors(4) + imageSize(8) = 300 bytes
        val bufferSize = 300
        val dataBuffer = ByteBuffer.allocate(bufferSize).order(ByteOrder.LITTLE_ENDIAN)

        // Chrome parameters
        dataBuffer.putFloat(amount)
        dataBuffer.putFloat(exposure)

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

/**
 * A filter which creates a smear/smudge effect.
 * @param distance smear distance in pixels
 * @param angle smear angle in degrees
 */
class SmearFilter(
    val distance: Float = 10f,
    val angle: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float distance;
            uniform float angle;
            half4 main(vec2 fragcoord) {
                float a = angle * 3.14159 / 180.0;
                vec2 dir = vec2(cos(a), sin(a)) * distance;
                half4 c = content.eval(fragcoord);
                half4 smear = content.eval(fragcoord + dir);
                half3 rgb = mix(c.rgb, smear.rgb, 0.5);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(distance)
        dataBuffer.putFloat(angle)
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

/**
 * A filter which applies a color transfer effect.
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
 * A filter which adds glint highlights to bright areas.
 * Replicates the JH-Labs GlintFilter.
 *
 * @param threshold brightness threshold for glint (default 1.0f)
 * @param length glint streak length (default 5)
 * @param blur glint blur amount (default 0.0f)
 * @param amount glint intensity (default 0.1f)
 * @param glintOnly true to show only glint, false to overlay (default false)
 */
class GlintFilter(
    val threshold: Float = 1.0f,
    val length: Int = 5,
    val blur: Float = 0.0f,
    val amount: Float = 0.1f,
    val glintOnly: Boolean = false
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float threshold;
            uniform int length;
            uniform float blur;
            uniform float amount;
            uniform int glintOnly;
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
                if (glintOnly == 1) {
                    rgb = glint.rgb * amount;
                } else {
                    rgb = c.rgb + glint.rgb * amount;
                }
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(threshold)
        dataBuffer.putInt(length)
        dataBuffer.putFloat(blur)
        dataBuffer.putFloat(amount)
        dataBuffer.putInt(if (glintOnly) 1 else 0)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}

/**
 * A filter which produces a color halftone effect with CMYK-style screen angles.
 * Replicates the JH-Labs ColorHalftoneFilter.
 *
 * @param dotRadius the radius of halftone dots (default 2)
 * @param cyanScreenAngle cyan screen angle in radians (default 108°)
 * @param magentaScreenAngle magenta screen angle in radians (default 162°)
 * @param yellowScreenAngle yellow screen angle in radians (default 90°)
 */
class ColorHalftoneFilter(
    val dotRadius: Float = 2f,
    val cyanScreenAngle: Float = 108f * 3.14159265f / 180f,
    val magentaScreenAngle: Float = 162f * 3.14159265f / 180f,
    val yellowScreenAngle: Float = 90f * 3.14159265f / 180f
) : Filter {
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

    fun makeShader(inputShader: org.jetbrains.skia.Shader): org.jetbrains.skia.Shader {
        val dataBuffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)
        dataBuffer.putFloat(dotRadius)
        dataBuffer.putFloat(cyanScreenAngle)
        dataBuffer.putFloat(magentaScreenAngle)
        dataBuffer.putFloat(yellowScreenAngle)
        return effect.makeShader(
            uniforms = Data.makeFromBytes(dataBuffer.array()),
            children = arrayOf(inputShader),
            localMatrix = null
        )
    }
}
