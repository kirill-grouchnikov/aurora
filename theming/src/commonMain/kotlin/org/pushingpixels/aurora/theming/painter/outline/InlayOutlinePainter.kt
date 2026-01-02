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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.InsetKind
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.painter.ColorStop

/**
 * Inlay outline painter that paints a double outline, with the inner one following the shape
 * of the outer one with no gaps between them.
 *
 * @author Kirill Grouchnikov
 */
class InlayOutlinePainter(
    override val displayName: String,
    val outer: OutlineSpec,
    val inner: OutlineSpec,
    val strokeWidth: Float = 1.0f,
) : AuroraOutlinePainter, AuroraTrait {
    override fun paintOutline(
        drawScope: DrawScope,
        size: Size,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with(drawScope) {
            translate(left = strokeWidth, top = strokeWidth) {
                this.paintSingleOutline(
                    size = Size(
                        width = size.width - 2.0f * strokeWidth,
                        height = size.height - 2.0f * strokeWidth
                    ),
                    radiusAdjustment = strokeWidth / 2.0f,
                    outlineSupplier = outlineSupplier,
                    colorTokens = colorTokens,
                    outlineSpec = inner,
                    alpha = alpha
                )
            }
            this.paintSingleOutline(
                size = size,
                radiusAdjustment = 0.0f,
                outlineSupplier = outlineSupplier,
                colorTokens = colorTokens,
                outlineSpec = outer,
                alpha = alpha
            )
        }
    }

    private fun DrawScope.paintSingleOutline(
        size: Size,
        radiusAdjustment: Float,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        outlineSpec: OutlineSpec,
        alpha: Float
    ) {
        val colorsNoAlpha = outlineSpec.colorQueries.map { it.invoke(colorTokens) }
        val colors = colorsNoAlpha.zip(outlineSpec.alphas) { color, alpha -> color.withAlpha(alpha) }

        val outline = outlineSupplier.getOutline(
            layoutDirection = this.layoutDirection,
            density = this,
            size = size,
            insets = strokeWidth - 0.5f,
            radiusAdjustment = radiusAdjustment,
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
                    colorStops = outlineSpec.fractions,
                    tileMode = TileMode.Repeated
                )
            ),
            alpha = alpha
        )
    }

    override fun getOutlineInset(insetKind: InsetKind): Float {
        return when (insetKind) {
            // Ignore the inner outline, and treat surface to extend halfway into the outer outline
            InsetKind.Surface -> this.strokeWidth / 2.0f
            InsetKind.Content -> {
                // For content, both outlines should be considered. However, to preserve the layout
                // alignment between single outlines (from {@link FractionBasedOutlinePainter) and double
                // outlines from this painter - at default hairline stroke width - make a special case where
                // only the outer outline is considered for the content insets.
                if (this.strokeWidth <= 1.0f) {
                    return this.strokeWidth
                }
                return 2.0f * this.strokeWidth
            }
        }
    }
}

class OutlineSpec(vararg colorQueryStops: ColorStop) {
    val fractions: List<Float> = colorQueryStops.map { it.fraction }
    val alphas: List<Float> = colorQueryStops.map { it.alpha }
    val colorQueries: List<(ContainerColorTokens) -> Color> = colorQueryStops.map { it.colorQuery }

    constructor(colorQuery: (ContainerColorTokens) -> Color) :
            this(
                ColorStop(fraction = 0.0f, colorQuery = colorQuery),
                ColorStop(fraction = 1.0f, colorQuery = colorQuery)
            )
}