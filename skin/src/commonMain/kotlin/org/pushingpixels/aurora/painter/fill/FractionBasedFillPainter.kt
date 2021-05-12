/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.painter.fill

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.painter.ColorQueryStop
import org.pushingpixels.aurora.painter.FractionBasedPainter

/**
 * Fill painter with fraction-based stops and a color query associated with each
 * stop. This class allows creating multi-gradient fills with exact control over
 * which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */
class FractionBasedFillPainter(
    vararg colorQueryStops: ColorQueryStop,
    displayName: String
) : FractionBasedPainter(displayName, *colorQueryStops), AuroraFillPainter {
    override fun paintContourBackground(
        drawScope: DrawScope,
        size: Size,
        outline: Outline,
        fillScheme: AuroraColorScheme,
        alpha: Float
    ) {
        with(drawScope) {
            drawOutline(
                outline = outline,
                style = Fill,
                brush = ShaderBrush(
                    LinearGradientShader(
                        from = Offset.Zero,
                        to = Offset(0.0f, size.height),
                        colors = getColorQueries().map { it.invoke(fillScheme) },
                        colorStops = getFractions().toList(),
                        tileMode = TileMode.Repeated
                    )
                ),
                alpha = alpha
            )
        }
    }
}