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
package org.pushingpixels.aurora.theming.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.DecorationAreaType

/**
 * Implementation of [AuroraDecorationPainter] that uses matte painting
 * on decoration areas.
 *
 * @author Kirill Grouchnikov
 */
class MatteDecorationPainter : AuroraDecorationPainter {
    override val displayName: String
        get() = "Matte"

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    ) {
        with(drawScope) {
            val startColor = if (colorTokens.isDark) {
                colorTokens.containerSurfaceHigh
            } else {
                colorTokens.containerSurfaceLowest
            }
            val endColor = colorTokens.containerSurface
            val flexPoint = FlexPoint.toPx()
            val gradientHeight = kotlin.math.max(
                flexPoint,
                componentSize.height - offsetFromRoot.y
            )
            // 0 - flex : light -> medium
            // flex - : medium fill
            val paint = if (gradientHeight == flexPoint)
                Brush.verticalGradient(
                    0.0f to startColor,
                    1.0f to endColor,
                    startY = -offsetFromRoot.y,
                    endY = gradientHeight - offsetFromRoot.y,
                    tileMode = TileMode.Clamp
                )
            else Brush.verticalGradient(
                0.0f to startColor,
                flexPoint / gradientHeight to endColor,
                1.0f to endColor,
                startY = -offsetFromRoot.y,
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
        private val FlexPoint = 50.dp
    }
}
