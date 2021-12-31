/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo.playground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.Shader
import org.pushingpixels.aurora.theming.businessSkin
import org.pushingpixels.aurora.theming.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.theming.colorscheme.OrangeColorScheme
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.nio.ByteBuffer
import java.nio.ByteOrder

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 600.dp)
    )
    val skin = mutableStateOf(businessSkin())

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        val metallic = MetallicColorScheme()
        val orange = OrangeColorScheme()

        val noiseMetallicShader = getNoiseShader(metallic.extraLightColor, metallic.darkColor)
        val noiseOrangeShader = getNoiseShader(orange.midColor, orange.ultraDarkColor, 1.0f, 0.25f)
        val noiseOrangeShaderAlpha = getNoiseShader(orange.midColor, Color.Black, 0.5f, 0.05f)

        val brushedMetalShader = getBrushedMetalShader(
            metallic.lightColor, metallic.ultraDarkColor, 1.0f
        )
        val brushedMetalOrangeShader = getBrushedMetalShader(
            orange.lightColor, orange.ultraDarkColor, 1.0f
        )
        val brushedMetalOrangeShaderAlpha = getBrushedMetalShader(
            orange.lightColor, orange.ultraDarkColor, 0.5f
        )

        Box(modifier = Modifier.size(500.dp).paint(painter = object : Painter() {
            override val intrinsicSize: Size
                get() = Size.Unspecified

            override fun DrawScope.onDraw() {
                drawRect(
                    brush = ShaderBrush(noiseMetallicShader),
                    topLeft = Offset(20f, 20f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(noiseOrangeShader),
                    topLeft = Offset(440f, 20f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(noiseOrangeShaderAlpha),
                    topLeft = Offset(860f, 20f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(brushedMetalShader),
                    topLeft = Offset(20f, 440f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(brushedMetalOrangeShader),
                    topLeft = Offset(440f, 440f),
                    size = Size(400f, 400f)
                )

                drawRect(
                    brush = ShaderBrush(brushedMetalOrangeShaderAlpha),
                    topLeft = Offset(860f, 440f),
                    size = Size(400f, 400f)
                )
            }
        }))
    }
}

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


