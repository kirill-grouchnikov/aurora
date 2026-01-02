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
package org.pushingpixels.aurora.theming.painter.outline

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.InsetKind
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.FractionBasedPainter

/**
 * Outline painter with fraction-based stops and a color query associated with
 * each stop. This class allows creating multi-gradient borders with exact
 * control over which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */
open class FractionBasedOutlinePainter(
    vararg colorQueryStops: ColorStop,
    displayName: String,
    val strokeWidth: Float = 1.0f,
) : FractionBasedPainter(displayName, *colorQueryStops), AuroraOutlinePainter {
    override fun paintOutline(
        drawScope: DrawScope,
        size: Size,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with(drawScope) {
            val colorsNoAlpha = colorQueries.map { it.invoke(colorTokens) }
            val colors = colorsNoAlpha.zip(alphas) { color, alpha -> color.withAlpha(alpha) }

            val outline = outlineSupplier.getOutline(
                layoutDirection = this.layoutDirection,
                density = this,
                size = size,
                insets = strokeWidth - 0.5f,
                radiusAdjustment = 0.0f,
                outlineKind = OutlineKind.Outline
            )
            drawOutline(
                outline = outline,
                style = Stroke(width = strokeWidth),
                brush = ShaderBrush(
                    LinearGradientShader(
                        from = Offset.Zero,
                        to = Offset(0.0f, size.height),
                        colors = colors,
                        colorStops = fractions,
                        tileMode = TileMode.Repeated
                    )
                ),
                alpha = alpha
            )
        }
    }

    override fun getOutlineInset(insetKind: InsetKind): Float {
        return when (insetKind) {
            InsetKind.Surface -> this.strokeWidth / 2.0f
            InsetKind.Content -> this.strokeWidth
        }
    }
}
