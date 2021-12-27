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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.businessSkin
import org.pushingpixels.aurora.theming.resolveAuroraDefaults
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.nio.ByteBuffer
import java.nio.ByteOrder

@OptIn(ExperimentalUnitApi::class)
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(900.dp, 800.dp)
    )
    val skin = mutableStateOf(businessSkin())

    AuroraWindow(
        skin = skin,
        title = "Gradients Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        val gradients = remember { mutableStateOf(Gradients.CyanRed) }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.weight(1.0f)) {
                GradientSection(
                    modifier = Modifier.weight(1.0f, fill = true),
                    gradients = Gradients.CyanRed
                )
                GradientSection(
                    modifier = Modifier.weight(1.0f, fill = true),
                    gradients = Gradients.GreenMagenta
                )
            }
            Row(modifier = Modifier.weight(1.0f)) {
                GradientSection(
                    modifier = Modifier.weight(1.0f, fill = true),
                    gradients = Gradients.WhiteBlue
                )
                GradientSection(
                    modifier = Modifier.weight(1.0f, fill = true),
                    gradients = Gradients.PeachTeal
                )
            }
        }
    }
}

data class GradientColors(val start: Color, val end: Color)

enum class Gradients(val desc: String, val colors: GradientColors) {
    CyanRed("Cyan-Red", GradientColors(Color.Cyan, Color.Red)),
    GreenMagenta("Green-Magenta", GradientColors(Color.Green, Color.Magenta)),
    WhiteBlue("White-Blue", GradientColors(Color.White, Color.Blue)),
    PeachTeal("Peach-Teal", GradientColors(Color(0xFFFFE5B4), Color(0xFF008080)))
}

@ExperimentalUnitApi
@Composable
fun GradientSection(gradients: Gradients, modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        LabelProjection(
            contentModel = LabelContentModel(
                text = gradients.desc
            ), presentationModel = LabelPresentationModel(
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
        ).project()

        Spacer(modifier = Modifier.height(4.dp))
        SingleGradientSection("default", gradients.colors) { width, colors ->
            Brush.horizontalGradient(
                0.0f to colors.start,
                1.0f to colors.end,
                startX = 0.0f,
                endX = width
            )
        }

        SingleGradientSection("linear srgb", gradients.colors) { width, colors ->
            val sksl = """
                    // https://bottosson.github.io/posts/colorwrong/#what-can-we-do%3F
                    vec3 linearSrgbToSrgb(vec3 x) {
                        vec3 xlo = 12.92*x;
                        vec3 xhi = 1.055 * pow(x, vec3(1.0/2.4)) - 0.055;
                        return mix(xlo, xhi, step(vec3(0.0031308), x));
                    
                    }
                    
                    vec3 srgbToLinearSrgb(vec3 x) {
                        vec3 xlo = x / 12.92;
                        vec3 xhi = pow((x + 0.055)/(1.055), vec3(2.4));
                        return mix(xlo, xhi, step(vec3(0.04045), x));
                    }
                    
                    uniform vec4 start;
                    uniform vec4 end;
                    uniform float width;

                    half4 main(vec2 fragcoord) {
                       // Implicit assumption in here that colors are full opacity
                       float fraction = fragcoord.x / width;
                       // Convert start and end colors to linear SRGB
                       vec3 linearStart = srgbToLinearSrgb(start.xyz);
                       vec3 linearEnd = srgbToLinearSrgb(end.xyz);
                       // Interpolate in linear SRGB space
                       vec3 linearInterpolated = mix(linearStart, linearEnd, fraction);
                       // And convert back to SRGB
                       return vec4(linearSrgbToSrgb(linearInterpolated), 1.0);
                    }
                """

            val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
            // RGBA colorLight
            dataBuffer.putFloat(0, colors.start.red)
            dataBuffer.putFloat(4, colors.start.green)
            dataBuffer.putFloat(8, colors.start.blue)
            dataBuffer.putFloat(12, colors.start.alpha)
            // RGBA colorDark
            dataBuffer.putFloat(16, colors.end.red)
            dataBuffer.putFloat(20, colors.end.green)
            dataBuffer.putFloat(24, colors.end.blue)
            dataBuffer.putFloat(28, colors.end.alpha)
            // Width
            dataBuffer.putFloat(32, width)

            val effect = RuntimeEffect.makeForShader(sksl)
            val shader = effect.makeShader(
                uniforms = Data.makeFromBytes(dataBuffer.array()),
                children = null,
                localMatrix = null,
                isOpaque = false
            )

            ShaderBrush(shader)
        }

        SingleGradientSection("oklab", gradients.colors) { width, colors ->
            val sksl = """
                    // https://bottosson.github.io/posts/colorwrong/#what-can-we-do%3F
                    vec3 linearSrgbToSrgb(vec3 x) {
                        vec3 xlo = 12.92*x;
                        vec3 xhi = 1.055 * pow(x, vec3(1.0/2.4)) - 0.055;
                        return mix(xlo, xhi, step(vec3(0.0031308), x));
                    
                    }
                    
                    vec3 srgbToLinearSrgb(vec3 x) {
                        vec3 xlo = x / 12.92;
                        vec3 xhi = pow((x + 0.055)/(1.055), vec3(2.4));
                        return mix(xlo, xhi, step(vec3(0.04045), x));
                    }
                    
                    // https://bottosson.github.io/posts/oklab/#converting-from-linear-srgb-to-oklab
                    const mat3 fromOkStep1 = mat3(
                       1.0, 1.0, 1.0,
                       0.3963377774, -0.1055613458, -0.0894841775,
                       0.2158037573, -0.0638541728, -1.2914855480);
                                       
                    const mat3 fromOkStep2 = mat3(
                       4.0767416621, -1.2684380046, -0.0041960863,
                       -3.3077115913, 2.6097574011, -0.7034186147,
                       0.2309699292, -0.3413193965,  1.7076147010);
                    
                    const mat3 toOkStep1 = mat3(
                       0.4122214708, 0.2119034982, 0.0883024619,
                       0.5363325363, 0.6806995451, 0.2817188376,
                       0.0514459929, 0.1073969566, 0.6299787005);
                                       
                    const mat3 toOkStep2 = mat3(
                       0.2104542553, 1.9779984951, 0.0259040371,
                       0.7936177850, -2.4285922050, 0.7827717662,
                       -0.0040720468, 0.4505937099, -0.8086757660);

                    vec3 linearSrgbToOklab(vec3 x) {
                        vec3 lms = toOkStep1 * x;
                        return toOkStep2 * (sign(lms)*pow(abs(lms), vec3(1.0/3.0)));
                    }
                    
                    vec3 oklabToLinearSrgb(vec3 x) {
                        vec3 lms = fromOkStep1 * x;
                        return fromOkStep2 * (lms * lms * lms);
                    }
                    
                    uniform vec4 start;
                    uniform vec4 end;
                    uniform float width;

                    half4 main(vec2 fragcoord) {
                       // Implicit assumption in here that colors are full opacity
                       float fraction = fragcoord.x / width;
                       // Convert start and end colors to Oklab
                       vec3 oklabStart = linearSrgbToOklab(srgbToLinearSrgb(start.xyz));
                       vec3 oklabEnd = linearSrgbToOklab(srgbToLinearSrgb(end.xyz));
                       // Interpolate in Oklab space
                       vec3 oklabInterpolated = mix(oklabStart, oklabEnd, fraction);
                       // And convert back to SRGB
                       return vec4(linearSrgbToSrgb(oklabToLinearSrgb(oklabInterpolated)), 1.0);
                    }
                """

            val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
            // RGBA colorLight
            dataBuffer.putFloat(0, colors.start.red)
            dataBuffer.putFloat(4, colors.start.green)
            dataBuffer.putFloat(8, colors.start.blue)
            dataBuffer.putFloat(12, colors.start.alpha)
            // RGBA colorDark
            dataBuffer.putFloat(16, colors.end.red)
            dataBuffer.putFloat(20, colors.end.green)
            dataBuffer.putFloat(24, colors.end.blue)
            dataBuffer.putFloat(28, colors.end.alpha)
            // Width
            dataBuffer.putFloat(32, width)

            val effect = RuntimeEffect.makeForShader(sksl)
            val shader = effect.makeShader(
                uniforms = Data.makeFromBytes(dataBuffer.array()),
                children = null,
                localMatrix = null,
                isOpaque = false
            )

            ShaderBrush(shader)
        }


        SingleGradientSection(
            "oklab, bezier (non-uniform) interpolation",
            gradients.colors
        ) { width, colors ->
            val sksl = """
                    // https://bottosson.github.io/posts/colorwrong/#what-can-we-do%3F
                    vec3 linearSrgbToSrgb(vec3 x) {
                        vec3 xlo = 12.92*x;
                        vec3 xhi = 1.055 * pow(x, vec3(1.0/2.4)) - 0.055;
                        return mix(xlo, xhi, step(vec3(0.0031308), x));
                    
                    }
                    
                    vec3 srgbToLinearSrgb(vec3 x) {
                        vec3 xlo = x / 12.92;
                        vec3 xhi = pow((x + 0.055)/(1.055), vec3(2.4));
                        return mix(xlo, xhi, step(vec3(0.04045), x));
                    }
                    
                    // https://bottosson.github.io/posts/oklab/#converting-from-linear-srgb-to-oklab
                    const mat3 fromOkStep1 = mat3(
                       1.0, 1.0, 1.0,
                       0.3963377774, -0.1055613458, -0.0894841775,
                       0.2158037573, -0.0638541728, -1.2914855480);
                                       
                    const mat3 fromOkStep2 = mat3(
                       4.0767416621, -1.2684380046, -0.0041960863,
                       -3.3077115913, 2.6097574011, -0.7034186147,
                       0.2309699292, -0.3413193965,  1.7076147010);
                    
                    const mat3 toOkStep1 = mat3(
                       0.4122214708, 0.2119034982, 0.0883024619,
                       0.5363325363, 0.6806995451, 0.2817188376,
                       0.0514459929, 0.1073969566, 0.6299787005);
                                       
                    const mat3 toOkStep2 = mat3(
                       0.2104542553, 1.9779984951, 0.0259040371,
                       0.7936177850, -2.4285922050, 0.7827717662,
                       -0.0040720468, 0.4505937099, -0.8086757660);

                    vec3 linearSrgbToOklab(vec3 x) {
                        vec3 lms = toOkStep1 * x;
                        return toOkStep2 * (sign(lms)*pow(abs(lms), vec3(1.0/3.0)));
                    }
                    
                    vec3 oklabToLinearSrgb(vec3 x) {
                        vec3 lms = fromOkStep1 * x;
                        return fromOkStep2 * (lms * lms * lms);
                    }
                    
                    // https://en.wikipedia.org/wiki/B%C3%A9zier_curve
                    vec2 spline(vec2 start, vec2 control1, vec2 control2, vec2 end, float t) {
                        float invT = 1.0 - t;
                        return start * invT * invT * invT + control1 * 3.0 * t * invT * invT + control2 * 3.0 * t * t * invT + end * t * t * t;
                    }
        
                    uniform vec4 start;
                    uniform vec4 end;
                    uniform float width;

                    // Bezier curve points. Note the the first control point is intentionally
                    // outside the 0.0-1.0 x range to further "favor" the curve towards the start
                    vec2 bstart = vec2(0.0, 0.0);
                    vec2 bcontrol1 = vec2(1.3, 0.0);
                    vec2 bcontrol2 = vec2(0.9, 0.1);
                    vec2 bend = vec2(1.0, 1.0);

                    half4 main(vec2 fragcoord) {
                       // Implicit assumption in here that colors are full opacity
                       float fraction = spline(bstart, bcontrol1, bcontrol2, bend, fragcoord.x / width).y;
                       // Convert start and end colors to Oklab
                       vec3 oklabStart = linearSrgbToOklab(srgbToLinearSrgb(start.xyz));
                       vec3 oklabEnd = linearSrgbToOklab(srgbToLinearSrgb(end.xyz));
                       // Interpolate in Oklab space
                       vec3 oklabInterpolated = mix(oklabStart, oklabEnd, fraction);
                       // And convert back to SRGB
                       return vec4(linearSrgbToSrgb(oklabToLinearSrgb(oklabInterpolated)), 1.0);
                    }
                """

            val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
            // RGBA colorLight
            dataBuffer.putFloat(0, colors.start.red)
            dataBuffer.putFloat(4, colors.start.green)
            dataBuffer.putFloat(8, colors.start.blue)
            dataBuffer.putFloat(12, colors.start.alpha)
            // RGBA colorDark
            dataBuffer.putFloat(16, colors.end.red)
            dataBuffer.putFloat(20, colors.end.green)
            dataBuffer.putFloat(24, colors.end.blue)
            dataBuffer.putFloat(28, colors.end.alpha)
            // Width
            dataBuffer.putFloat(32, width)

            val effect = RuntimeEffect.makeForShader(sksl)
            val shader = effect.makeShader(
                uniforms = Data.makeFromBytes(dataBuffer.array()),
                children = null,
                localMatrix = null,
                isOpaque = false
            )

            ShaderBrush(shader)
        }
    }
}


@ExperimentalUnitApi
@Composable
fun SingleGradientSection(
    text: String,
    colors: GradientColors,
    brushCreator: (width: Float, colors: GradientColors) -> Brush
) {
    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveAuroraDefaults()
    val fontSize = resolvedTextStyle.fontSize
    // Compute a smaller font size
    val smallerFontSize = TextUnit(fontSize.value - 4.0f, fontSize.type)
    // And create our own text style with smaller font size and bold weight
    val textStyle = TextStyle(
        fontSize = smallerFontSize,
        fontWeight = FontWeight.Bold
    )
    val density = LocalDensity.current.density
    val brush = brushCreator.invoke(400.dp.value * density, colors)

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        LabelProjection(
            contentModel = LabelContentModel(
                text = text.uppercase()
            ),
            presentationModel = LabelPresentationModel(
                textStyle = textStyle
            )
        ).project()

        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.size(400.dp, 40.dp).drawBehind {
            drawRect(brush, topLeft = Offset.Zero, size = size)
        })
    }
}

