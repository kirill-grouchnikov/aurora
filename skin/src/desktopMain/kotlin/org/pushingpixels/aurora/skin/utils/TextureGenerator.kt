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
package org.pushingpixels.aurora.skin.utils

import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import org.jetbrains.skia.*
import org.pushingpixels.aurora.common.interpolateTowards
import java.nio.ByteOrder
import kotlin.math.roundToInt

fun getGradientColorFilter(color1: Color, color2: Color): ColorFilter {
    val reds = ByteArray(256)
    val greens = ByteArray(256)
    val blues = ByteArray(256)

    for (index in 0..255) {
        val color = color1.interpolateTowards(color2, index / 255.0f)
        reds[index] = (255 * color.red).roundToInt().toByte()
        greens[index] = (255 * color.green).roundToInt().toByte()
        blues[index] = (255 * color.blue).roundToInt().toByte()
    }

    // Pass null for alphas so that when the filter is applied, it respects the alpha
    // channel of the source image
    return ColorFilter.makeTableARGB(null, reds, greens, blues)
}

fun getBrushedMetalPaint(colorFilter: ColorFilter, hOffset: Float): Paint {
    val paint = Paint()
    // Fractal noise shader
    paint.setShader(
        Shader.makeFractalNoise(
            baseFrequencyX = 0.45f,
            baseFrequencyY = 0.45f,
            numOctaves = 4,
            seed = 0.0f,
            tiles = emptyArray()
        )
    )
    // Composed color filter. Inner filter applies greyscale, and outer filter applies the colors of
    // the specified Aurora color scheme
    paint.setColorFilter(
        ColorFilter.makeComposed(
            outer = colorFilter,
            inner = ColorFilter.makeMatrix(
                ColorMatrix(
                    0.21f, 0.72f, 0.07f, 0.0f, 0.0f,
                    0.21f, 0.72f, 0.07f, 0.0f, 0.0f,
                    0.21f, 0.72f, 0.07f, 0.0f, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f, 0.0f
                )
            )
        )
    )
    // Image filter to apply horizontal blur
    paint.imageFilter =
        ImageFilter.makeBlur(hOffset, 0.0f, FilterTileMode.REPEAT, null, null)

    return paint
}

fun getNoisePaint(colorLight: Color, colorDark: Color, alpha: Float = 1.0f, baseFrequency: Float = 0.45f): Paint {
    val paint = Paint()

    // Fractal noise shader
    val noiseShader = Shader.makeFractalNoise(
        baseFrequencyX = baseFrequency,
        baseFrequencyY = baseFrequency,
        numOctaves = 1,
        seed = 0.0f,
        tiles = emptyArray()
    )

    val duotoneDesc = """
            uniform shader input;
            uniform vec4 colorLight;
            uniform vec4 colorDark;
            uniform float alpha;
            
            vec4 lerp(float f, vec4 a, vec4 b) {
                return a + f * (b - a);
            }

            half4 main(vec2 fragcoord) { 
                vec4 inputColor = sample(input, fragcoord);
                float luma = 0.2126 * inputColor.r + 0.7152 * inputColor.g + 0.0722 * inputColor.b;
                //float tweakedLuma = 1.0 - (1.0 - luma) / 1.2;
                vec4 duotone = lerp(luma, colorLight, colorDark);
                return vec4(duotone.r * alpha, duotone.g * alpha, duotone.b * alpha, alpha);
            }
        """

    val byteBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
    // RGBA colorLight
    byteBuffer.putFloat(0, colorLight.red)
    byteBuffer.putFloat(4, colorLight.green)
    byteBuffer.putFloat(8, colorLight.blue)
    byteBuffer.putFloat(12, colorLight.alpha)
    // RGBA colorDark
    byteBuffer.putFloat(16, colorDark.red)
    byteBuffer.putFloat(20, colorDark.green)
    byteBuffer.putFloat(24, colorDark.blue)
    byteBuffer.putFloat(28, colorDark.alpha)
    // Alpha
    byteBuffer.putFloat(32, alpha)

    val duotoneEffect = RuntimeEffect.makeForShader(duotoneDesc)
    val duotoneShader = duotoneEffect.makeShader(
        uniforms = Data.makeFromBytes(byteBuffer.array()),
        children = arrayOf(noiseShader),
        localMatrix = null,
        isOpaque = false
    )

    paint.setShader(duotoneShader);

    return paint
}

fun getBrushedMetalPaintAlt(hOffset: Float): Paint {
    val paint = Paint()

    // Fractal noise shader
    val noiseShader = Shader.makeFractalNoise(
        baseFrequencyX = 0.45f,
        baseFrequencyY = 0.45f,
        numOctaves = 4,
        seed = 0.0f,
        tiles = emptyArray()
    )

    val duotoneDesc = """
            uniform shader input;
            
            vec4 lerp(float f, vec4 a, vec4 b) {
                return a + f * (b - a);
            }

            vec4 red = vec4(255.0 / 255.0, 0.0 / 255.0, 0.0 / 255.0, 1.0);
            vec4 blue = vec4(0.0 / 255.0, 0.0 / 255.0, 255.0 / 255.0, 1.0);

            half4 main(vec2 fragcoord) { 
                vec4 inputColor = sample(input, fragcoord);
                float luma = 0.2126 * inputColor.r + 0.7152 * inputColor.g + 0.0722 * inputColor.b;
                float tweakedLuma = 1.0 - (1.0 - luma) / 1.2;
                vec4 duotone = lerp(tweakedLuma, red, blue);
                return duotone;
            }
        """

    val duotoneEffect = RuntimeEffect.makeForShader(duotoneDesc)
    val duotoneShader = duotoneEffect.makeShader(
        uniforms = null,
        children = arrayOf(noiseShader),
        localMatrix = null,
        isOpaque = false
    )

    paint.setShader(duotoneShader);

    // Image filter to apply horizontal blur
    paint.imageFilter =
        ImageFilter.makeBlur(hOffset, 0.0f, FilterTileMode.REPEAT, null, null)

    return paint
}