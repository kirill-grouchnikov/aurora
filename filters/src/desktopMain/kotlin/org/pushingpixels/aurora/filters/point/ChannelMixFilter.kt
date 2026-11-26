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

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.Filter
import org.pushingpixels.aurora.filters.composite.FloatPair

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter that mixes RGB channels.
 *
 * Parameters are in the range [0, 1]:
 * @param blueGreen amount to mix blue into green (0 = no mix, 1 = full mix)
 * @param redBlue amount to mix red into blue (0 = no mix, 1 = full mix)
 * @param greenRed amount to mix green into red (0 = no mix, 1 = full mix)
 * @param intoR how much of the mixed value to blend into the red channel (0 = none, 1 = full)
 * @param intoG how much of the mixed value to blend into the green channel (0 = none, 1 = full)
 * @param intoB how much of the mixed value to blend into the blue channel (0 = none, 1 = full)
 */
object ChannelMixFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float blueGreen;
            uniform float redBlue;
            uniform float greenRed;
            uniform float intoR;
            uniform float intoG;
            uniform float intoB;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half r = c.r;
                half g = c.g;
                half b = c.b;
                half nr = clamp(intoR * (blueGreen * g + (1.0 - blueGreen) * b) + (1.0 - intoR) * r, 0.0, 1.0);
                half ng = clamp(intoG * (redBlue * b + (1.0 - redBlue) * r) + (1.0 - intoG) * g, 0.0, 1.0);
                half nb = clamp(intoB * (greenRed * r + (1.0 - greenRed) * g) + (1.0 - intoB) * b, 0.0, 1.0);
                return half4(nr, ng, nb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun renderEffect(
        blueGreen: Float = 0f,
        redBlue: Float = 0f,
        greenRed: Float = 0f,
        intoR: Float = 0f,
        intoG: Float = 0f,
        intoB: Float = 0f
    ): RenderEffect {
        val channelMixShaderBuilder = RuntimeShaderBuilder(this.effect)
        channelMixShaderBuilder.uniform("blueGreen", blueGreen)
        channelMixShaderBuilder.uniform("redBlue", redBlue)
        channelMixShaderBuilder.uniform("greenRed", greenRed)
        channelMixShaderBuilder.uniform("intoR", intoR)
        channelMixShaderBuilder.uniform("intoG", intoG)
        channelMixShaderBuilder.uniform("intoB", intoB)

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = channelMixShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()
    }

    fun imageFilter(
        size: FloatPair,
        blueGreen: Float = 0f,
        redBlue: Float = 0f,
        greenRed: Float = 0f,
        intoR: Float = 0f,
        intoG: Float = 0f,
        intoB: Float = 0f
    ): ImageFilter {
        val channelMixShaderBuilder = RuntimeShaderBuilder(this.effect)
        channelMixShaderBuilder.uniform("blueGreen", blueGreen)
        channelMixShaderBuilder.uniform("redBlue", redBlue)
        channelMixShaderBuilder.uniform("greenRed", greenRed)
        channelMixShaderBuilder.uniform("intoR", intoR)
        channelMixShaderBuilder.uniform("intoG", intoG)
        channelMixShaderBuilder.uniform("intoB", intoB)
        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = channelMixShaderBuilder,
            shaderName = "content",
            input = null
        )
    }

    fun effectModifier(
        blueGreen: Float = 0f,
        redBlue: Float = 0f,
        greenRed: Float = 0f,
        intoR: Float = 0f,
        intoG: Float = 0f,
        intoB: Float = 0f
    ): Modifier {
        val channelMixShaderBuilder = RuntimeShaderBuilder(this.effect)
        channelMixShaderBuilder.uniform("blueGreen", blueGreen)
        channelMixShaderBuilder.uniform("redBlue", redBlue)
        channelMixShaderBuilder.uniform("greenRed", greenRed)
        channelMixShaderBuilder.uniform("intoR", intoR)
        channelMixShaderBuilder.uniform("intoG", intoG)
        channelMixShaderBuilder.uniform("intoB", intoB)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = channelMixShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}

/**
 * A filter which multiplies the RGB values by a scaling factor.
 * @param scale the scale factor
 */
object RescaleFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                float3 rgb = clamp(float3(c.rgb) * scale, 0.0, float(c.a));
                return half4(half3(rgb), c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun imageFilter(
        size: FloatPair,
        scale: Float = 1f
    ): ImageFilter {
        val rescaleShaderBuilder = RuntimeShaderBuilder(this.effect)
        rescaleShaderBuilder.uniform("scale", scale)
        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = rescaleShaderBuilder,
            shaderName = "content",
            input = null
        )
    }

    fun effectModifier(scale: Float = 1f): Modifier {
        val rescaleShaderBuilder = RuntimeShaderBuilder(this.effect)
        rescaleShaderBuilder.uniform("scale", scale)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = rescaleShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}
