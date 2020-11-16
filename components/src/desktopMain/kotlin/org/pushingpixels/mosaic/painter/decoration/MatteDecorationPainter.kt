/*
 * Copyright (c) 2005-2020 Radiance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.DecorationAreaType
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme
import org.pushingpixels.mosaic.utils.getInterpolatedColor
import java.lang.Float.max

/**
 * Implementation of [SubstanceDecorationPainter] that uses matte painting
 * on decoration areas.
 *
 * @author Kirill Grouchnikov
 */
class MatteDecorationPainter : MosaicDecorationPainter {
    override val displayName: String
        get() = "Matte"

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        offsetFromRoot: Offset,
        colorScheme: MosaicColorScheme
    ) {
        with(drawScope) {
            val startColor = colorScheme.lightColor
            val endColor = getInterpolatedColor(startColor, colorScheme.midColor, 0.4f)
            val flexPoint = FLEX_POINT.dp.toPx()
            val gradientHeight = max(
                flexPoint,
                componentSize.height + offsetFromRoot.y
            )
            // 0 - flex : light -> medium
            // flex - : medium fill
            val paint = if (gradientHeight == flexPoint)
                LinearGradient(
                    0.0f to startColor,
                    1.0f to endColor,
                    startX = 0.0f,
                    startY = -offsetFromRoot.y,
                    endX = 0.0f,
                    endY = gradientHeight - offsetFromRoot.y,
                    tileMode = TileMode.Clamp
                )
            else LinearGradient(
                0.0f to startColor,
                flexPoint / gradientHeight to endColor,
                1.0f to endColor,
                startX = 0.0f,
                startY = -offsetFromRoot.y,
                endX = 0.0f,
                endY = componentSize.height - offsetFromRoot.y,
                tileMode = TileMode.Clamp
            )
            drawOutline(
                outline = outline,
                style = Fill,
                brush = paint
            )
        }
    }

    companion object {
        private const val FLEX_POINT = 50
    }
}
