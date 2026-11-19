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
package org.pushingpixels.aurora.filters.lut

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import org.pushingpixels.aurora.filters.utility.createImageBitmapFromArgb
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Parses an Adobe .cube 3D LUT string and bakes it into a 512x512 Hald CLUT ImageBitmap.
 * Runs off the main thread on Dispatchers.Default.
 */
suspend fun cubeTextToHaldImageBitmap(cubeText: String): ImageBitmap = withContext(Dispatchers.Default) {
    val parsed = parseCubeLut(cubeText)
    val pixels = bakeCubeTo512HaldPixels(parsed)
    createImageBitmapFromArgb(pixels, 512, 512)
}

private data class CubeLutData(
    val size: Int,
    val domainMin: FloatArray,
    val domainMax: FloatArray,
    // Flat RGB float array of length size * size * size * 3, ordered with R fastest, then G, then B
    val table: FloatArray
)

// LUT .cube file spec is at https://web.archive.org/web/20220220033515/https://wwwimages2.adobe.com/content/dam/acom/en/products/speedgrade/cc/pdfs/cube-lut-specification-1.0.pdf
// or https://kono.phpage.fr/images/a/a1/Adobe-cube-lut-specification-1.0.pdf
private fun parseCubeLut(cubeText: String): CubeLutData {
    var size = 0
    val domainMin = floatArrayOf(0f, 0f, 0f)
    val domainMax = floatArrayOf(1f, 1f, 1f)
    var table: FloatArray? = null
    var writeIdx = 0

    for (rawLine in cubeText.lineSequence()) {
        val line = rawLine.substringBefore('#').trim()
        if (line.isEmpty()) continue

        when {
            line.startsWith("TITLE", ignoreCase = true) -> Unit
            line.startsWith("LUT_1D_SIZE", ignoreCase = true) -> {
                throw IllegalArgumentException("1D .cube LUTs are not supported; expected LUT_3D_SIZE.")
            }
            line.startsWith("LUT_3D_SIZE", ignoreCase = true) -> {
                size = line.split(Regex("\\s+"))[1].toInt()
                table = FloatArray(size * size * size * 3)
            }
            line.startsWith("DOMAIN_MIN", ignoreCase = true) -> {
                val parts = line.split(Regex("\\s+"))
                domainMin[0] = parts[1].toFloat()
                domainMin[1] = parts[2].toFloat()
                domainMin[2] = parts[3].toFloat()
            }
            line.startsWith("DOMAIN_MAX", ignoreCase = true) -> {
                val parts = line.split(Regex("\\s+"))
                domainMax[0] = parts[1].toFloat()
                domainMax[1] = parts[2].toFloat()
                domainMax[2] = parts[3].toFloat()
            }
            line.startsWith("LUT_3D_INPUT_RANGE", ignoreCase = true) -> {
                val parts = line.split(Regex("\\s+"))
                val minVal = parts[1].toFloat()
                val maxVal = parts[2].toFloat()
                domainMin[0] = minVal
                domainMin[1] = minVal
                domainMin[2] = minVal
                domainMax[0] = maxVal
                domainMax[1] = maxVal
                domainMax[2] = maxVal
            }
            else -> {
                val buf = table ?: throw IllegalStateException("Data rows appeared before LUT_3D_SIZE.")
                val parts = line.split(Regex("\\s+"))
                if (parts.size >= 3 && writeIdx + 2 < buf.size) {
                    buf[writeIdx++] = parts[0].toFloat()
                    buf[writeIdx++] = parts[1].toFloat()
                    buf[writeIdx++] = parts[2].toFloat()
                }
            }
        }
    }

    require(size > 1 && table != null && writeIdx == table.size) {
        "Invalid .cube LUT: expected ${size * size * size} RGB entries, got ${writeIdx / 3}."
    }

    return CubeLutData(size, domainMin, domainMax, table)
}

private fun bakeCubeTo512HaldPixels(lut: CubeLutData): IntArray {
    val srcN = lut.size
    val table = lut.table
    val dstPixels = IntArray(512 * 512)

    fun readFloat(r: Int, g: Int, b: Int, channel: Int): Float {
        // Standard .cube order: Red varies fastest, then Green, then Blue
        val idx = ((b * srcN + g) * srcN + r) * 3 + channel
        return table[idx]
    }

    fun lerp(a: Float, b: Float, t: Float) = a + (b - a) * t

    fun sampleChannel(rNorm: Float, gNorm: Float, bNorm: Float, channel: Int): Int {
        // Map [0..1] into the .cube domain, clamped to [0..1]
        val rScaled = ((rNorm - lut.domainMin[0]) / (lut.domainMax[0] - lut.domainMin[0])).coerceIn(0f, 1f)
        val gScaled = ((gNorm - lut.domainMin[1]) / (lut.domainMax[1] - lut.domainMin[1])).coerceIn(0f, 1f)
        val bScaled = ((bNorm - lut.domainMin[2]) / (lut.domainMax[2] - lut.domainMin[2])).coerceIn(0f, 1f)

        val x = rScaled * (srcN - 1)
        val y = gScaled * (srcN - 1)
        val z = bScaled * (srcN - 1)

        val x0 = floor(x).toInt(); val x1 = min(x0 + 1, srcN - 1); val xd = x - x0
        val y0 = floor(y).toInt(); val y1 = min(y0 + 1, srcN - 1); val yd = y - y0
        val z0 = floor(z).toInt(); val z1 = min(z0 + 1, srcN - 1); val zd = z - z0

        val c00 = lerp(readFloat(x0, y0, z0, channel), readFloat(x1, y0, z0, channel), xd)
        val c01 = lerp(readFloat(x0, y0, z1, channel), readFloat(x1, y0, z1, channel), xd)
        val c10 = lerp(readFloat(x0, y1, z0, channel), readFloat(x1, y1, z0, channel), xd)
        val c11 = lerp(readFloat(x0, y1, z1, channel), readFloat(x1, y1, z1, channel), xd)

        val c0 = lerp(c00, c10, yd)
        val c1 = lerp(c01, c11, yd)

        return (lerp(c0, c1, zd) * 255f).roundToInt().coerceIn(0, 255)
    }

    // 512x512 Hald CLUT = 8x8 grid of 64x64 tiles (64x64x64 total colors)
    for (b in 0 until 64) {
        val tileRow = b / 8
        val tileCol = b % 8
        val bNorm = b / 63f

        for (g in 0 until 64) {
            val gNorm = g / 63f
            val py = tileRow * 64 + g

            for (r in 0 until 64) {
                val rNorm = r / 63f
                val px = tileCol * 64 + r

                val outR = sampleChannel(rNorm, gNorm, bNorm, 0)
                val outG = sampleChannel(rNorm, gNorm, bNorm, 1)
                val outB = sampleChannel(rNorm, gNorm, bNorm, 2)

                dstPixels[py * 512 + px] = (0xFF shl 24) or (outR shl 16) or (outG shl 8) or outB
            }
        }
    }

    return dstPixels
}
