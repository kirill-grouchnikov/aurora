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

package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.AmbientLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.PopupPlacementStrategy
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme

internal fun getArrow(
    width: Float, height: Float, strokeWidth: Float,
    direction: PopupPlacementStrategy, layoutDirection: LayoutDirection,
    color: Color
): ImageBitmap {
    val isSideways = (direction == PopupPlacementStrategy.STARTWARD) ||
            (direction == PopupPlacementStrategy.ENDWARD)

    val arrowImage = ImageBitmap(width = if (isSideways) height.toInt() else width.toInt(),
        height = if (isSideways) width.toInt() else height.toInt())

    val canvas = Canvas(arrowImage)

    if (direction == PopupPlacementStrategy.CENTERED) {
        val smallHeight = (height - strokeWidth) / 2
        val top = getArrow(
            width, smallHeight, strokeWidth, PopupPlacementStrategy.UPWARD,
            layoutDirection, color
        )
        val bottom = getArrow(
            width, smallHeight, strokeWidth, PopupPlacementStrategy.DOWNWARD,
            layoutDirection, color
        )
        canvas.drawImage(image = top, topLeftOffset = Offset(0.0f, 0.0f), paint = Paint())
        canvas.drawImage(image = bottom, topLeftOffset = Offset(0.0f, height / 2.0f), paint = Paint())

        return arrowImage
    }

    val cushion = strokeWidth / 2.0f
    val gp = Path()

    if (direction == PopupPlacementStrategy.DOWNWARD) {
        gp.moveTo(cushion, cushion)
        gp.lineTo(0.5f * width, height - cushion - 1)
        gp.lineTo(width - cushion, cushion)
    } else if (direction == PopupPlacementStrategy.UPWARD) {
        gp.moveTo(cushion, height - cushion - 1)
        gp.lineTo(0.5f * width, cushion)
        gp.lineTo(width - cushion, height - cushion - 1)
    } else {
        val leftward =
            ((direction == PopupPlacementStrategy.STARTWARD) && (layoutDirection == LayoutDirection.Ltr)) ||
                    ((direction == PopupPlacementStrategy.ENDWARD) && (layoutDirection == LayoutDirection.Rtl))
        if (leftward) {
            gp.moveTo(width - 1 - cushion, cushion)
            gp.lineTo(cushion, 0.5f * height)
            gp.lineTo(width - 1 - cushion, height - cushion)
        } else {
            gp.moveTo(cushion, cushion)
            gp.lineTo(width - 1 - cushion, 0.5f * height)
            gp.lineTo(cushion, height - cushion)
        }
    }

    val paint = Paint().also {
        it.color = color
        it.style = PaintingStyle.Stroke
        it.strokeWidth = strokeWidth
        it.strokeCap = StrokeCap.Round
        it.strokeJoin = StrokeJoin.Miter
    }
    canvas.drawPath(gp, paint)

    return arrowImage
}

