/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.skija.ColorAlphaType
import org.jetbrains.skija.ColorInfo
import org.jetbrains.skija.ColorType
import org.jetbrains.skija.ImageInfo
import org.pushingpixels.aurora.AmbientTextColor
import org.pushingpixels.aurora.bitmapfilter.ColorBitmapFilter
import java.awt.image.BufferedImage

// Ideally every transcoded SVG image that has raster content would have this
// conversion function in that class (so that it doesn't need to bring the
// dependency), but for now make it an extension on the [BufferedImage] class.
fun BufferedImage.toComposeBitmap(): ImageBitmap {
    val width = this.width
    val height = this.height
    val origPixels = IntArray(width * height)

    // Step 1 - get the pixels from the input
    if ((this.type == BufferedImage.TYPE_INT_ARGB) || (this.type == BufferedImage.TYPE_INT_RGB)) {
        this.raster.getDataElements(0, 0, width, height, origPixels)
    } else {
        this.getRGB(0, 0, width, height, origPixels, 0, width)
    }

    // Step 2 - convert an array of integers (each integer is 8888 of ARGB) into an
    // array of bytes (in BGRA order) that Skija expects
    val byteDstBuffer = ByteArray(4 * width * height)
    for (pos in 0 until width * height) {
        val rgb = origPixels[pos]
        // The order of the bytes corresponds to BGRA_8888
        // Blue
        byteDstBuffer[4 * pos] = (rgb ushr 0 and 0xFF).toByte()
        // Green
        byteDstBuffer[4 * pos + 1] = (rgb ushr 8 and 0xFF).toByte()
        // Red
        byteDstBuffer[4 * pos + 2] = (rgb ushr 16 and 0xFF).toByte()
        // Alpha
        byteDstBuffer[4 * pos + 3] = 255.toByte()
    }

    // Step 3 - create a Skija bitmap
    val result = ImageBitmap(width = width, height = height)
    val skijaBitmap = result.asDesktopBitmap()
    skijaBitmap.installPixels(
        ImageInfo(ColorInfo(ColorType.BGRA_8888, ColorAlphaType.UNPREMUL, null), width, height),
        byteDstBuffer, 4L * width
    )
    return result
}

fun AuroraIcon.toBitmap(density: Float): ImageBitmap {
    val result = ImageBitmap((this.getWidth() * density).toInt(), (this.getHeight() * density).toInt())
    val canvas = Canvas(result)
    CanvasDrawScope().draw(
        density = Density(density),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = Size(width = result.width.toFloat(), height = result.height.toFloat())
    ) {
        paintIcon(this)
    }
    return result
}

private fun Modifier.auroraThemedIconPaint(icon: ImageBitmap, textColor: Color) =
    this.then(AuroraThemedIconModifier(icon = icon, textColor = textColor))

private class AuroraThemedIconModifier(
    val icon: ImageBitmap, val textColor: Color
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        val filtered = ColorBitmapFilter.getColorFilter(color = textColor).filter(icon)
        drawImage(filtered)
    }
}

@Composable
fun AuroraThemedIcon(icon: AuroraIcon, modifier: Modifier = Modifier) {
    val textColor = AmbientTextColor.current
    val density = AmbientDensity.current.density
    val bitmap = remember { icon.toBitmap(density) }
    // TODO - is it worth caching filtered bitmap by color (for performance reasons)?
    Box(
        modifier.preferredSize(
            width = icon.getWidth().dp,
            height = icon.getHeight().dp
        ).auroraThemedIconPaint(bitmap, textColor)
    )
}


