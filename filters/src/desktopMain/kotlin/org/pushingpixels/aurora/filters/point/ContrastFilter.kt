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
 * A filter to change the brightness and contrast of an image.
 */
object ContrastFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float brightness;
            uniform float contrast;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half3 rgb = c.rgb * brightness;
                rgb = (rgb - 0.5) * contrast + 0.5;
                rgb = clamp(rgb, 0.0, 1.0);
                return half4(rgb, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    fun renderEffect(
        brightness: Float = 1f,
        contrast: Float = 1f
    ): RenderEffect {
        val contrastShaderBuilder = RuntimeShaderBuilder(this.effect)
        contrastShaderBuilder.uniform("brightness", brightness)
        contrastShaderBuilder.uniform("contrast", contrast)

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = contrastShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()
    }

    fun imageFilter(
        size: FloatPair,
        brightness: Float = 1f,
        contrast: Float = 1f
    ): ImageFilter {
        val contrastShaderBuilder = RuntimeShaderBuilder(this.effect)
        contrastShaderBuilder.uniform("brightness", brightness)
        contrastShaderBuilder.uniform("contrast", contrast)
        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = contrastShaderBuilder,
            shaderName = "content",
            input = null
        )
    }

    fun effectModifier(
        brightness: Float = 1f,
        contrast: Float = 1f
    ): Modifier {
        val contrastShaderBuilder = RuntimeShaderBuilder(this.effect)
        contrastShaderBuilder.uniform("brightness", brightness)
        contrastShaderBuilder.uniform("contrast", contrast)

        val renderEffect: RenderEffect = ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = contrastShaderBuilder,
            shaderName = "content",
            input = null
        ).asComposeRenderEffect()

        return Modifier.graphicsLayer(renderEffect = renderEffect, clip = true)
    }
}
