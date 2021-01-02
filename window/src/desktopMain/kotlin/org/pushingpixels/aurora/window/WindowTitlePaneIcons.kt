/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.window

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import org.pushingpixels.aurora.bitmapfilter.ColorBitmapFilter
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.colorBrightness
import org.pushingpixels.aurora.common.colorStrength
import kotlin.math.abs

private fun overlayEcho(image: ImageBitmap, echoAlpha: Float, echoColor: Color, density: Float): ImageBitmap {
    val offsetX = 0.0f
    val offsetY = 0.0f

    val echo = ColorBitmapFilter.getColorFilter(color = echoColor, alpha = 1.0f).filter(image)
    val result = ImageBitmap(image.width, image.height)
    val canvas = Canvas(result)

    val paint20 = Paint().also { it.alpha = 0.2f * echoAlpha }
    canvas.drawImage(
        image = echo,
        topLeftOffset = Offset(x = offsetX - density, y = offsetY - density),
        paint = paint20
    )
    canvas.drawImage(
        image = echo,
        topLeftOffset = Offset(x = offsetX + density, y = offsetY - density),
        paint = paint20
    )
    canvas.drawImage(
        image = echo,
        topLeftOffset = Offset(x = offsetX - density, y = offsetY + density),
        paint = paint20
    )
    canvas.drawImage(
        image = echo,
        topLeftOffset = Offset(x = offsetX + density, y = offsetY + density),
        paint = paint20
    )

    val paint70 = Paint().also { it.alpha = 0.7f * echoAlpha }
    canvas.drawImage(image = echo, topLeftOffset = Offset(x = offsetX, y = offsetY - density), paint = paint70)
    canvas.drawImage(image = echo, topLeftOffset = Offset(x = offsetX, y = offsetY - density), paint = paint70)
    canvas.drawImage(image = echo, topLeftOffset = Offset(x = offsetX - density, y = offsetY), paint = paint70)
    canvas.drawImage(image = echo, topLeftOffset = Offset(x = offsetX + density, y = offsetY), paint = paint70)

    canvas.drawImage(image = image, topLeftOffset = Offset(x = offsetX, y = offsetY), paint = Paint())

    return result
}

internal fun getCloseIcon(iconSize: Int, scheme: AuroraColorScheme, density: Float): ImageBitmap {
    val image = ImageBitmap(iconSize, iconSize)
    val start = iconSize / 4.0f
    val end = iconSize - start

    val color = scheme.markColor
    val paint = Paint().also {
        it.color = color
        it.style = PaintingStyle.Stroke
        it.strokeWidth = 1.5f * density
        it.strokeCap = StrokeCap.Round
        it.strokeJoin = StrokeJoin.Round
    }

    val canvas = Canvas(image)
    canvas.drawLine(p1 = Offset(start, start), p2 = Offset(end, end), paint = paint)
    canvas.drawLine(p1 = Offset(start, end), p2 = Offset(end, start), paint = paint)

    val echoColor = scheme.echoColor
    val fgStrength = color.colorBrightness
    val echoStrength = echoColor.colorBrightness
    val noEcho = abs(fgStrength - echoStrength) < 0.1875f
    return overlayEcho(
        image = image,
        echoAlpha = if (noEcho) 0.0f else color.colorStrength,
        echoColor = echoColor,
        density = density
    )
}

fun getMinimizeIcon(iconSize: Int, scheme: AuroraColorScheme, density: Float): ImageBitmap {
    val image = ImageBitmap(iconSize, iconSize)

    val start = iconSize * 0.25f
    val end = iconSize * 0.75f

    val color = scheme.markColor
    val paint = Paint().also {
        it.color = color
        it.style = PaintingStyle.Fill
    }
    val canvas = Canvas(image)

    canvas.drawRect(
        rect = Rect(
            left = start,
            top = end - 1.5f * density,
            right = end,
            bottom = end + density
        ), paint = paint
    )

    val echoColor = scheme.echoColor
    val fgStrength = color.colorBrightness
    val echoStrength = echoColor.colorBrightness
    val noEcho = abs(fgStrength - echoStrength) < 0.1875f
    return overlayEcho(
        image = image,
        echoAlpha = if (noEcho) 0.0f else color.colorStrength,
        echoColor = echoColor,
        density = density
    )
}

fun getRestoreIcon(iconSize: Int, scheme: AuroraColorScheme, density: Float): ImageBitmap {
    val image = ImageBitmap(iconSize, iconSize)

    val start = iconSize / 4.0f - density
    val end = iconSize - start
    val smallSquareSize = end - start - 3.0f * density

    val color = scheme.markColor
    val paint = Paint().also {
        it.color = color
        it.style = PaintingStyle.Fill
    }
    val canvas = Canvas(image)

    // "Main" rectangle
    val mainStartX = start
    val mainStartY = end - smallSquareSize

    // top (thicker)
    canvas.drawRect(
        rect = Rect(
            left = mainStartX,
            top = mainStartY,
            right = mainStartX + smallSquareSize,
            bottom = mainStartY + 2.0f * density
        ), paint = paint
    )
    // left
    canvas.drawRect(
        rect = Rect(
            left = mainStartX,
            top = mainStartY,
            right = mainStartX + density,
            bottom = mainStartY + smallSquareSize
        ), paint = paint
    )
    // right
    canvas.drawRect(
        rect = Rect(
            left = mainStartX + smallSquareSize - density,
            top = mainStartY,
            right = mainStartX + smallSquareSize,
            bottom = mainStartY + smallSquareSize
        ), paint = paint
    )
    // bottom
    canvas.drawRect(
        rect = Rect(
            left = mainStartX,
            top = mainStartY + smallSquareSize - density,
            right = mainStartX + smallSquareSize,
            bottom = mainStartY + smallSquareSize
        ), paint = paint
    )

    // "Secondary rectangle"
    val secondaryStartX = mainStartX + 3.0f * density
    val secondaryStartY = mainStartY - 3.0f * density
    // top (thicker)
    canvas.drawRect(
        rect = Rect(
            left = secondaryStartX,
            top = secondaryStartY,
            right = secondaryStartX + smallSquareSize,
            bottom = secondaryStartY + 2.0f * density
        ), paint = paint
    )
    // right
    canvas.drawRect(
        rect = Rect(
            left = secondaryStartX + smallSquareSize - density,
            top = secondaryStartY,
            right = secondaryStartX + smallSquareSize,
            bottom = secondaryStartY + smallSquareSize
        ), paint = paint
    )
    // bottom (partial)
    canvas.drawRect(
        rect = Rect(
            left = mainStartX + smallSquareSize + density,
            top = secondaryStartY + smallSquareSize - density,
            right = mainStartX + smallSquareSize + 3.0f*density,
            bottom = secondaryStartY + smallSquareSize
        ), paint = paint
    )

    val echoColor = scheme.echoColor
    val fgStrength = color.colorBrightness
    val echoStrength = echoColor.colorBrightness
    val noEcho = abs(fgStrength - echoStrength) < 0.1875f
    return overlayEcho(
        image = image,
        echoAlpha = if (noEcho) 0.0f else color.colorStrength,
        echoColor = echoColor,
        density = density
    )
}

fun getMaximizeIcon(iconSize: Int, scheme: AuroraColorScheme, density: Float): ImageBitmap {
    val image = ImageBitmap(iconSize, iconSize)

    val start = iconSize / 4.0f - density
    val end = iconSize - start

    val color = scheme.markColor
    val paint = Paint().also {
        it.color = color
        it.style = PaintingStyle.Fill
    }
    val canvas = Canvas(image)

    // top (thicker)
    canvas.drawRect(
        rect = Rect(
            left = start,
            top = start,
            right = end,
            bottom = start + 2.0f * density
        ), paint = paint
    )
    // left
    canvas.drawRect(
        rect = Rect(
            left = start,
            top = start,
            right = start + density,
            bottom = end
        ), paint = paint
    )
    // right
    canvas.drawRect(
        rect = Rect(
            left = end - density,
            top = start,
            right = end,
            bottom = end
        ), paint = paint
    )
    // bottom
    canvas.drawRect(
        rect = Rect(
            left = start,
            top = end - density,
            right = end,
            bottom = end
        ), paint = paint
    )

    val echoColor = scheme.echoColor
    val fgStrength = color.colorBrightness
    val echoStrength = echoColor.colorBrightness
    val noEcho = abs(fgStrength - echoStrength) < 0.1875f
    return overlayEcho(
        image = image,
        echoAlpha = if (noEcho) 0.0f else color.colorStrength,
        echoColor = echoColor,
        density = density
    )
}






