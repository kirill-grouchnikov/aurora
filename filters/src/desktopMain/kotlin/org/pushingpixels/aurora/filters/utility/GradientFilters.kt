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
package org.pushingpixels.aurora.filters.utility

import androidx.compose.ui.graphics.Color
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder

/**
 * Creates an [ImageFilter] that produces a horizontal linear gradient
 * transitioning from [color1] on the left to [color2] on the right.
 *
 * The gradient spans the full width of the painted region, interpolating
 * between the two colors along the X axis.
 *
 * @param color1 the starting color on the left side of the gradient
 * @param color2 the ending color on the right side of the gradient
 * @return an [ImageFilter] containing the horizontal linear gradient shader
 */
fun makeHorizontalGradientFilter(color1: Color, color2: Color, width: Int): ImageFilter {
    @Language("GLSL")
    val shaderSource = """
        uniform shader content;
        uniform half4 color1;
        uniform half4 color2;
        uniform int width;
        half4 main(vec2 fragCoord) {
            float t = fragCoord.x / float(width);
            t = clamp(t, 0.0, 1.0);
            half4 gradient = mix(color1, color2, t);
            return gradient;
        }
    """
    val effect = RuntimeEffect.makeForShader(shaderSource)

    val builder = RuntimeShaderBuilder(effect)
    builder.uniform("color1", floatArrayOf(color1.red, color1.green, color1.blue, color1.alpha))
    builder.uniform("color2", floatArrayOf(color2.red, color2.green, color2.blue, color2.alpha))
    builder.uniform("width", width)

    return ImageFilter.makeRuntimeShader(
        runtimeShaderBuilder = builder,
        shaderName = "content",
        input = null
    )
}

/**
 * Creates an [ImageFilter] that produces a horizontal linear gradient
 * with multiple color [stops].
 *
 * The gradient spans the full width of the painted region, interpolating
 * between consecutive color stops along the X axis. Stops are distributed
 * evenly across the gradient from left to right.
 *
 * @param stops the list of color stops (must contain at least two colors)
 * @param width the width of the painted region in pixels
 * @return an [ImageFilter] containing the multi-stop horizontal linear gradient shader
 * @throws IllegalArgumentException if [stops] contains fewer than two colors
 */
fun makeMultiStopHorizontalGradientFilter(stops: List<Color>, width: Int): ImageFilter {
    require(stops.size >= 2) { "At least two color stops are required" }

    val stopCount = stops.size
    val maxStops = 16
    @Language("GLSL")
    val shaderSource = """
        uniform shader content;
        uniform int numStops;
        uniform half4 stops[${maxStops}];
        uniform int width;

        half4 main(vec2 fragCoord) {
            float t = fragCoord.x / float(width);
            t = clamp(t, 0.0, 1.0);
            float segPos = t * float(numStops - 1);
            half4 c0 = stops[0];
            half4 c1 = stops[0];
            float segStart = 0.0;
            for (int i = 0; i < $maxStops - 1; i++) {
                if (i >= numStops) {
                    break;
                }
                float nextSeg = float(i + 1);
                if (segPos <= nextSeg) {
                    c0 = stops[i];
                    c1 = stops[i + 1];
                    segStart = float(i);
                    break;
                }
            }
            float localT = segPos - segStart;
            half4 gradient = mix(c0, c1, localT);
            return gradient;
        }
    """

    val effect = RuntimeEffect.makeForShader(shaderSource)

    val builder = RuntimeShaderBuilder(effect)
    builder.uniform("numStops", stopCount)
    val slStops = FloatArray(maxStops * 4) { 0.0f }
    var index = 0
    for (i in stops.indices) {
        slStops[index++] = stops[i].red
        slStops[index++] = stops[i].green
        slStops[index++] = stops[i].blue
        slStops[index++] = stops[i].alpha
    }
    builder.uniform("stops", slStops)
    builder.uniform("width", width)

    return ImageFilter.makeRuntimeShader(
        runtimeShaderBuilder = builder,
        shaderName = "content",
        input = null
    )
}
