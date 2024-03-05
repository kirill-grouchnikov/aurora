/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.painter.border

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.painter.ColorQueryStop
import org.pushingpixels.aurora.theming.painter.FractionBasedPainter

/**
 * Border painter with fraction-based stops and a color query associated with
 * each stop. This class allows creating multi-gradient borders with exact
 * control over which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */
open class FractionBasedBorderPainter(
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
            if ((fractionLow > 0.5f) || (fractionHigh < 0.5f)) {
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
