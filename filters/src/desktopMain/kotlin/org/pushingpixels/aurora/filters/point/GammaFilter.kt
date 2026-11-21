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
 * A filter for changing the gamma of an image.
 */
object GammaFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float rGamma;
            uniform float gGamma;
            uniform float bGamma;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = c.rgb;
                rgb.r = pow(max(rgb.r, 0.0001), 1.0 / rGamma);
                rgb.g = pow(max(rgb.g, 0.0001), 1.0 / gGamma);
                rgb.b = pow(max(rgb.b, 0.0001), 1.0 / bGamma);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun renderEffect(
        rGamma: Float = 1f,
        gGamma: Float = 1f,
        bGamma: Float = 1f
    ): RenderEffect {
        val gammaShaderBuilder = RuntimeShaderBuilder(this.effect)
        gammaShaderBuilder.uniform("rGamma", rGamma)
        gammaShaderBuilder.uniform("gGamma", gGamma)
        gammaShaderBuilder.uniform("bGamma", bGamma)

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = gammaShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()
    }

    fun imageFilter(
        size: FloatPair,
        rGamma: Float = 1f,
        gGamma: Float = 1f,
        bGamma: Float = 1f
    ): ImageFilter {
        val gammaShaderBuilder = RuntimeShaderBuilder(this.effect)
        gammaShaderBuilder.uniform("rGamma", rGamma)
        gammaShaderBuilder.uniform("gGamma", gGamma)
        gammaShaderBuilder.uniform("bGamma", bGamma)
        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = gammaShaderBuilder,
            shaderName = "content",
            input = null
        )
    }

    fun effectModifier(
        rGamma: Float = 1f,
        gGamma: Float = 1f,
        bGamma: Float = 1f
    ): Modifier {
        val gammaShaderBuilder = RuntimeShaderBuilder(this.effect)
        gammaShaderBuilder.uniform("rGamma", rGamma)
        gammaShaderBuilder.uniform("gGamma", gGamma)
        gammaShaderBuilder.uniform("bGamma", bGamma)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = gammaShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}
