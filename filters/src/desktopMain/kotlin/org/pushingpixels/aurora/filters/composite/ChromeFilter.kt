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
import androidx.compose.ui.layout.onSizeChanged
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.Filter
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientFilter
import java.nio.ByteBuffer
import java.nio.ByteOrder

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

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
class ChromeFilter : Filter {
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
            uniform float lightTypes[4]; // should be int, but easier to pass floats
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
            uniform float2 contentSize;

            $applyBumpShapeFn

            $sampleBumpHeightFn

            $computeNormalFn

            $phongShadeFn

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
                half3 normal = computeNormal(fragcoord, contentSize.x, contentSize.y);

                // Viewpoint for specular calculations
                half3 viewpoint = half3(
                    contentSize.x * 0.5 - fragcoord.x,
                    contentSize.y * 0.5 - fragcoord.y,
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

//    fun makeShader(
//        inputShader: org.jetbrains.skia.Shader?,
//        imageWidth: Float,
//        imageHeight: Float
//    ): org.jetbrains.skia.Shader {
//        // Maximum 4 lights supported in the shader
//        val lightCount = lights.size.coerceAtMost(4)
//
//        // Calculate buffer size:
//        // amount(4) + exposure(4) + materialDiffuseRGB(12) + materialSpecularRGB(12) +
//        // ambientIntensity(4) + diffuseReflectivity(4) + specularReflectivity(4) + highlight(4) +
//        // reflectivity(4) + opacity(4) + lightCount(4) +
//        // lightTypes(16) + lightColorRGB(48) + lightDirections(48) + lightPositions(48) +
//        // lightCosConeAngles(16) + lightFocus(16) +
//        // bumpHeight(4) + bumpSoftness(4) + bumpShape(4) + viewDistance(4) + useImageColors(4) + imageSize(8) = 300 bytes
//        val bufferSize = 300
//        val dataBuffer = ByteBuffer.allocate(bufferSize).order(ByteOrder.LITTLE_ENDIAN)
//
//        // Chrome parameters
//        dataBuffer.putFloat(amount)
//        dataBuffer.putFloat(exposure)
//
//        // Material properties
//        val diffuseRGB = material.diffuseRGB
//        dataBuffer.putFloat(diffuseRGB[0])
//        dataBuffer.putFloat(diffuseRGB[1])
//        dataBuffer.putFloat(diffuseRGB[2])
//
//        val specularRGB = material.specularRGB
//        dataBuffer.putFloat(specularRGB[0])
//        dataBuffer.putFloat(specularRGB[1])
//        dataBuffer.putFloat(specularRGB[2])
//
//        dataBuffer.putFloat(material.ambientIntensity)
//        dataBuffer.putFloat(material.diffuseReflectivity)
//        dataBuffer.putFloat(material.specularReflectivity)
//        dataBuffer.putFloat(material.highlight)
//        dataBuffer.putFloat(material.reflectivity)
//        dataBuffer.putFloat(material.opacity)
//
//        // Light count
//        dataBuffer.putInt(lightCount)
//
//        // Light arrays (pad to 4)
//        for (i in 0 until 4) {
//            if (i < lightCount) {
//                val light = lights[i]
//                dataBuffer.putInt(light.type.ordinal)
//                val c = light.colorRGB
//                dataBuffer.putFloat(c[0])
//                dataBuffer.putFloat(c[1])
//                dataBuffer.putFloat(c[2])
//                val d = light.direction
//                dataBuffer.putFloat(d[0])
//                dataBuffer.putFloat(d[1])
//                dataBuffer.putFloat(d[2])
//                val p = light.position(imageWidth, imageHeight)
//                dataBuffer.putFloat(p[0])
//                dataBuffer.putFloat(p[1])
//                dataBuffer.putFloat(p[2])
//                dataBuffer.putFloat(light.cosConeAngle)
//                dataBuffer.putFloat(light.focus)
//            } else {
//                dataBuffer.putInt(0)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//                dataBuffer.putFloat(0f)
//            }
//        }
//
//        // Bump and view parameters
//        dataBuffer.putFloat(bumpHeight)
//        dataBuffer.putFloat(bumpSoftness)
//        dataBuffer.putInt(bumpShape.ordinal)
//        dataBuffer.putFloat(viewDistance)
//        dataBuffer.putInt(if (useImageColors) 1 else 0)
//        dataBuffer.putFloat(imageWidth)
//        dataBuffer.putFloat(imageHeight)
//
//        return effect.makeShader(
//            uniforms = Data.makeFromBytes(dataBuffer.array()),
//            children = arrayOf(inputShader),
//            localMatrix = null
//        )
//    }

    fun effectModifier(
        amount: Float = 0.5f,
        exposure: Float = 1f,
        chromeMaterial: Material = Material(),
        lights: List<Light> = listOf(Light(type = LightType.DISTANT)),
        bumpHeight: Float = 1.0f,
        bumpSoftness: Float = 0f,
        bumpShape: BumpShape = BumpShape.NONE,
        viewDistance: Float = 10000.0f,
        useImageColors: Boolean = false
    ): Modifier {
        val chromeShaderBuilder = RuntimeShaderBuilder(this.effect)

        val maxLights = 4

        val sizeChangedModifier = Modifier.onSizeChanged { newSize ->
            chromeShaderBuilder.uniform(
                "contentSize",
                newSize.width.toFloat(),
                newSize.height.toFloat()
            )

            val lightPositions = FloatArray(maxLights * 3) { 0.0f }
            var lIndex = 0
            for (light in lights) {
                val lightPosition = light.position(newSize.width.toFloat(), newSize.height.toFloat())
                lightPositions[lIndex++] = lightPosition[0]
                lightPositions[lIndex++] = lightPosition[1]
                lightPositions[lIndex++] = lightPosition[2]
            }
            chromeShaderBuilder.uniform("lightPositions", lightPositions)
        }

        chromeShaderBuilder.uniform("amount", amount)
        chromeShaderBuilder.uniform("exposure", exposure)
        chromeShaderBuilder.uniform(
            "materialDiffuseRGB",
            chromeMaterial.diffuseRGB[0], chromeMaterial.diffuseRGB[1], chromeMaterial.diffuseRGB[2]
        )
        chromeShaderBuilder.uniform(
            "materialSpecularRGB",
            chromeMaterial.specularRGB[0], chromeMaterial.specularRGB[1], chromeMaterial.specularRGB[2]
        )

        chromeShaderBuilder.uniform("ambientIntensity", chromeMaterial.ambientIntensity)
        chromeShaderBuilder.uniform("diffuseReflectivity", chromeMaterial.diffuseReflectivity)
        chromeShaderBuilder.uniform("specularReflectivity", chromeMaterial.specularReflectivity)
        chromeShaderBuilder.uniform("highlight", chromeMaterial.highlight)
        chromeShaderBuilder.uniform("reflectivity", chromeMaterial.reflectivity)
        chromeShaderBuilder.uniform("opacity", chromeMaterial.opacity)

        chromeShaderBuilder.uniform("lightCount", lights.size)

        val lightTypes = FloatArray(maxLights) { 0.0f }
        val lightColorRGB = FloatArray(maxLights * 3) { 0.0f }
        val lightDirections = FloatArray(maxLights * 3) { 0.0f }
        val lightCosConeAngles = FloatArray(maxLights) { 0.0f }
        val lightFocus = FloatArray(maxLights) { 0.0f }

        var lIndex = 0
        for (light in lights) {
            lightTypes[lIndex++] = light.type.ordinal.toFloat()
        }

        lIndex = 0
        for (light in lights) {
            lightColorRGB[lIndex++] = light.colorRGB[0]
            lightColorRGB[lIndex++] = light.colorRGB[1]
            lightColorRGB[lIndex++] = light.colorRGB[2]
        }

        lIndex = 0
        for (light in lights) {
            lightDirections[lIndex++] = light.direction[0]
            lightDirections[lIndex++] = light.direction[1]
            lightDirections[lIndex++] = light.direction[2]
        }

        lIndex = 0
        for (light in lights) {
            lightCosConeAngles[lIndex++] = light.cosConeAngle
        }

        lIndex = 0
        for (light in lights) {
            lightFocus[lIndex++] = light.focus
        }

        chromeShaderBuilder.uniform("lightTypes", lightTypes)
        chromeShaderBuilder.uniform("lightColorRGB", lightColorRGB)
        chromeShaderBuilder.uniform("lightDirections", lightDirections)
        chromeShaderBuilder.uniform("lightCosConeAngles", lightCosConeAngles)
        chromeShaderBuilder.uniform("lightFocus", lightFocus)

        chromeShaderBuilder.uniform("bumpHeight", bumpHeight)
        chromeShaderBuilder.uniform("bumpSoftness", bumpSoftness)
        chromeShaderBuilder.uniform("bumpShape", bumpShape.ordinal)
        chromeShaderBuilder.uniform("viewDistance", viewDistance)
        chromeShaderBuilder.uniform("useImageColors", if (useImageColors) 1 else 0)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = chromeShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return sizeChangedModifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}
