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
package org.pushingpixels.aurora.painter.border

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.painter.ColorQueryStop
import org.pushingpixels.aurora.painter.FractionBasedPainter

/**
 * Border painter with fraction-based stops and a color query associated with
 * each stop. This class allows creating multi-gradient borders with exact
 * control over which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */
class FractionBasedBorderPainter(
    vararg colorQueryStops: ColorQueryStop,
    displayName: String
) : FractionBasedPainter(displayName, *colorQueryStops), AuroraBorderPainter {
    override fun paintBorder(
        drawScope: DrawScope,
        size: Size,
        outline: Outline,
        outlineInner: Outline?,
        borderScheme: AuroraColorScheme,
        alpha: Float
    ) {
        with(drawScope) {
            drawOutline(
                outline = outline,
                style = Stroke(width = 1.0f),
                brush = ShaderBrush(
                    LinearGradientShader(
                        from = Offset.Zero,
                        to = Offset(0.0f, size.height),
                        colors = getColorQueries().map { it.invoke(borderScheme) },
                        colorStops = getFractions().toList(),
                        tileMode = TileMode.Repeated
                    )
                ),
                alpha = alpha
            )
        }
    }

    override val isPaintingInnerOutline: Boolean
        get() = false

    override fun getRepresentativeColor(borderScheme: AuroraColorScheme): Color {
        val fractions = getFractions()
        val colorQueries = getColorQueries()
        for (i in 0 until colorQueries.size - 1) {
            val fractionLow = fractions[i]
            val fractionHigh = fractions[i + 1]
            if (fractionLow == 0.5f) {
                return colorQueries[i].invoke(borderScheme)
            }
            if (fractionHigh == 0.5f) {
                return colorQueries[i + 1].invoke(borderScheme)
            }
            if (fractionLow < 0.5f || fractionHigh > 0.5f) {
                continue
            }
            // current range contains 0.5f
            val colorLow: Color = colorQueries[i].invoke(borderScheme)
            val colorHigh: Color = colorQueries[i + 1].invoke(borderScheme)
            val colorLowLikeness = (0.5f - fractionLow) / (fractionHigh - fractionLow)
            return colorLow.interpolateTowards(colorHigh, colorLowLikeness)
        }
        throw IllegalStateException("Could not find representative color")
    }
}
