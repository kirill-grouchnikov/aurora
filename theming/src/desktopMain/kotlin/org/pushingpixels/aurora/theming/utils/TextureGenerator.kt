/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.graphics.Color
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.Shader
import java.nio.ByteBuffer
import java.nio.ByteOrder

fun getNoiseShader(
    colorLight: Color,
    colorDark: Color,
    alpha: Float = 1.0f,
    baseFrequency: Float = 0.45f
): Shader {
    // Fractal noise shader
    val noiseShader = Shader.makeFractalNoise(
        baseFrequencyX = baseFrequency,
        baseFrequencyY = baseFrequency,
        numOctaves = 1,
        seed = 0.0f
    )

    // Duotone shader
    val duotoneDesc = """
            uniform shader shaderInput;
            uniform vec4 colorLight;
            uniform vec4 colorDark;
            uniform float alpha;
            
            half4 main(vec2 fragcoord) { 
                vec4 inputColor = shaderInput.eval(fragcoord);
                float luma = dot(inputColor.rgb, vec3(0.299, 0.587, 0.114));
                vec4 duotone = mix(colorLight, colorDark, luma);
                return vec4(duotone.r * alpha, duotone.g * alpha, duotone.b * alpha, alpha);
            }
        """

    val duotoneDataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
    // RGBA colorLight
    duotoneDataBuffer.putFloat(0, colorLight.red)
    duotoneDataBuffer.putFloat(4, colorLight.green)
    duotoneDataBuffer.putFloat(8, colorLight.blue)
    duotoneDataBuffer.putFloat(12, colorLight.alpha)
    // RGBA colorDark
    duotoneDataBuffer.putFloat(16, colorDark.red)
    duotoneDataBuffer.putFloat(20, colorDark.green)
    duotoneDataBuffer.putFloat(24, colorDark.blue)
    duotoneDataBuffer.putFloat(28, colorDark.alpha)
    // Alpha
    duotoneDataBuffer.putFloat(32, alpha)

    val duotoneEffect = RuntimeEffect.makeForShader(duotoneDesc)
    val duotoneShader = duotoneEffect.makeShader(
        uniforms = Data.makeFromBytes(duotoneDataBuffer.array()),
        children = arrayOf(noiseShader),
        localMatrix = null,
        isOpaque = false
    )

    return duotoneShader
}

fun getBrushedMetalShader(colorLight: Color, colorDark: Color, alpha: Float = 1.0f): Shader {
    // Fractal noise shader
    val noiseShader = Shader.makeFractalNoise(
        baseFrequencyX = 0.45f,
        baseFrequencyY = 0.45f,
        numOctaves = 4,
        seed = 0.0f
    )

    // Brushed metal shader
    val brushedMetalDesc = """
            uniform shader shaderInput;

            half4 main(vec2 fragcoord) { 
              vec4 inputColor = shaderInput.eval(vec2(0, fragcoord.y));
              // Compute the luma at the first pixel in this row
              float luma = dot(inputColor.rgb, vec3(0.299, 0.587, 0.114));
              // Apply modulation to stretch and shift the texture for the brushed metal look 
              float modulated = abs(cos((0.004 + 0.02 * luma) * (fragcoord.x + 200) + 0.26 * luma) 
                  * sin((0.06 - 0.25 * luma) * (fragcoord.x + 85) + 0.75 * luma));
              // Map 0.0-1.0 range to inverse 0.15-0.3
              float modulated2 = 0.3 - modulated / 6.5;
              half4 result = half4(modulated2, modulated2, modulated2, 1.0);
              return result;
            }
    """
    val brushedMetalEffect = RuntimeEffect.makeForShader(brushedMetalDesc)
    val brushedMetalShader = brushedMetalEffect.makeShader(
        uniforms = null,
        children = arrayOf(noiseShader),
        localMatrix = null,
        isOpaque = false
    )

    // Duotone shader
    val duotoneDesc = """
            uniform shader shaderInput;
            uniform vec4 colorLight;
            uniform vec4 colorDark;
            uniform float alpha;
            
            half4 main(vec2 fragcoord) { 
                vec4 inputColor = shaderInput.eval(fragcoord);
                float luma = dot(inputColor.rgb, vec3(0.299, 0.587, 0.114));
                vec4 duotone = mix(colorLight, colorDark, luma);
                return vec4(duotone.r * alpha, duotone.g * alpha, duotone.b * alpha, alpha);
            }
        """

    val duotoneDataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
    // RGBA colorLight
    duotoneDataBuffer.putFloat(0, colorLight.red)
    duotoneDataBuffer.putFloat(4, colorLight.green)
    duotoneDataBuffer.putFloat(8, colorLight.blue)
    duotoneDataBuffer.putFloat(12, colorLight.alpha)
    // RGBA colorDark
    duotoneDataBuffer.putFloat(16, colorDark.red)
    duotoneDataBuffer.putFloat(20, colorDark.green)
    duotoneDataBuffer.putFloat(24, colorDark.blue)
    duotoneDataBuffer.putFloat(28, colorDark.alpha)
    // Alpha
    duotoneDataBuffer.putFloat(32, alpha)

    val duotoneEffect = RuntimeEffect.makeForShader(duotoneDesc)
    val duotoneShader = duotoneEffect.makeShader(
        uniforms = Data.makeFromBytes(duotoneDataBuffer.array()),
        children = arrayOf(brushedMetalShader),
        localMatrix = null,
        isOpaque = false
    )

    return duotoneShader
}