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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.filters.Filter

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * Maximum radius for the GlowFilter shader loop.
 * SkSL doesn't support dynamic loop bounds, so this is hardcoded.
 */
private const val GLOW_MAX_R = 25

/**
 * A filter which adds Gaussian blur to an image, producing a glowing effect.
 * Replicates the JH-Labs GlowFilter.
 */
object GlowFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform float amount;
            half4 main(vec2 fragcoord) {
                half4 original = content.eval(fragcoord);
                int r = int(ceil(radius));
                if (r < 1) r = 1;
                half4 sum = half4(0);
                int count = 0;
                for (int dy = -$GLOW_MAX_R; dy <= $GLOW_MAX_R; dy++) {
                    for (int dx = -$GLOW_MAX_R; dx <= $GLOW_MAX_R; dx++) {
                        if (abs(float(dx)) > radius || abs(float(dy)) > radius) continue;
                        sum += content.eval(fragcoord + vec2(float(dx), float(dy)));
                        count++;
                    }
                }
                half4 blurred = sum / float(count);
                // Glow: original + a * blurred, where a = 4 * amount
                float a = 4.0 * amount;
                half3 rgb = clamp(original.rgb + a * blurred.rgb, 0.0, 1.0);
                return half4(rgb, original.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    /**
     * Returns a Compose Modifier that applies the glow filter effect.
     *
     * @param radius blur radius in pixels (default 2.0f)
     * @param amount glow amount 0-1 (default 0.5f)
     */
    @Composable
    fun effectModifier(
        radius: Float = 2f,
        amount: Float = 0.5f
    ): Modifier {
        val glowShaderBuilder = RuntimeShaderBuilder(this.effect)
        glowShaderBuilder.uniform("radius", radius)
        glowShaderBuilder.uniform("amount", amount)

        return Modifier.graphicsLayer {
            renderEffect = ImageFilter.makeRuntimeShader(
                runtimeShaderBuilder = glowShaderBuilder,
                shaderName = "content",
                input = null
            ).asComposeRenderEffect()
            clip = true
        }
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

}


