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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.*
import java.nio.ByteBuffer
import java.nio.ByteOrder

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

/**
 * Creates an [org.jetbrains.skia.Shader] that renders a horizontal linear gradient
 * with the given color [stops].
 *
 * Instead of using a GLSL shader with a fixed stop limit, this function bakes the
 * gradient into 1-pixel tall [ImageBitmap] and returns a shader that samples that bitmap.
 * This supports an arbitrary number of color stops.
 *
 * @param stops the list of color stops (must contain at least two colors)
 * @return an [org.jetbrains.skia.Shader] that renders the multi-stop horizontal gradient
 * @throws IllegalArgumentException if [stops] contains fewer than two colors
 */
fun makeMultiStopHorizontalGradientShader(stops: List<Color>, width: Int = 512): org.jetbrains.skia.Shader {
    require(stops.size >= 2) { "At least two color stops are required" }
    val argb = IntArray(width)

    for (x in 0 until width) {
        val t = x / (width - 1).toFloat()
        val scaledT = t * (stops.size - 1)
        val segIndex = scaledT.toInt().coerceIn(0, stops.size - 2)
        val localT = scaledT - segIndex.toFloat()

        val c0 = stops[segIndex]
        val c1 = stops[segIndex + 1]

        val r = ((c0.red + (c1.red - c0.red) * localT).coerceIn(0f, 1f) * 255).toInt() and 0xFF
        val g = ((c0.green + (c1.green - c0.green) * localT).coerceIn(0f, 1f) * 255).toInt() and 0xFF
        val b = ((c0.blue + (c1.blue - c0.blue) * localT).coerceIn(0f, 1f) * 255).toInt() and 0xFF
        val a = ((c0.alpha + (c1.alpha - c0.alpha) * localT).coerceIn(0f, 1f) * 255).toInt() and 0xFF

        argb[x] = (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    val bitmap = createImageBitmapFromArgb(argb, width, 1)
    val skiaImage = Image.makeFromBitmap(bitmap.asSkiaBitmap())
    return skiaImage.makeShader(
        tmx = FilterTileMode.CLAMP,
        tmy = FilterTileMode.CLAMP,
        sampling = SamplingMode.DEFAULT
    )
}

fun createImageBitmapFromArgb(
    argb: IntArray,
    width: Int,
    height: Int
): ImageBitmap {
    // Convert IntArray (ARGB) to ByteArray (BGRA for Skia N32/BGRA_8888)
    val bytes = ByteArray(argb.size * 4)
    for (i in argb.indices) {
        val c = argb[i]
        val base = i * 4
        bytes[base] = (c and 0xFF).toByte()         // B
        bytes[base + 1] = ((c shr 8) and 0xFF).toByte()  // G
        bytes[base + 2] = ((c shr 16) and 0xFF).toByte() // R
        bytes[base + 3] = ((c shr 24) and 0xFF).toByte() // A
    }

    val info = ImageInfo(
        width = width,
        height = height,
        colorType = ColorType.BGRA_8888,
        alphaType = ColorAlphaType.UNPREMUL
    )
    return Image.makeRaster(info, bytes, width * 4).toComposeImageBitmap()
}

fun makePassthroughShader(): Shader {
    @Language("GLSL")
    val shaderSource = """
        uniform shader content;

        half4 main(vec2 fragCoord) {
            return content.eval(fragCoord);
        }
    """

    val effect = RuntimeEffect.makeForShader(shaderSource)

    val dataBuffer = ByteBuffer.allocate(0).order(ByteOrder.LITTLE_ENDIAN)
    return effect.makeShader(
        uniforms = Data.makeFromBytes(dataBuffer.array()),
        children = arrayOf(null),
        localMatrix = null
    )
}
