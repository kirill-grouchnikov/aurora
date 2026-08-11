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
package org.pushingpixels.aurora.theming.painter.surface

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.FractionBasedPainter

/**
 * Surface painter with fraction-based stops and a color query associated with each
 * stop. This class allows creating multi-gradient fills with exact control over
 * which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */
open class FractionBasedSurfacePainter(
    vararg colorQueryStops: ColorStop,
    displayName: String
) : FractionBasedPainter(displayName, *colorQueryStops), AuroraSurfacePainter {
    override fun paintSurface(
        drawScope: DrawScope,
        size: Size,
        outline: Outline,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with(drawScope) {
            val colorsNoAlpha = colorQueries.map { it.invoke(colorTokens) }
            val colors = colorsNoAlpha.zip(alphas) { color, alpha -> color.withAlpha(alpha) }
            drawOutline(
                outline = outline,
                style = Fill,
                brush = ShaderBrush(
                    LinearGradientShader(
                        from = Offset.Zero,
                        to = Offset(0.0f, size.height),
                        colors = colors,
                        colorStops = fractions,
                        tileMode = TileMode.Clamp
                    )
                ),
                alpha = alpha
            )
        }
    }
}