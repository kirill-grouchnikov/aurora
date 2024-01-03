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

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme

open class StandardBorderPainter : AuroraBorderPainter {
    override val displayName: String
        get() = "Standard"
    override val isPaintingInnerOutline: Boolean
        get() = false

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
                brush = Brush.verticalGradient(
                    0.0f to getTopBorderColor(borderScheme),
                    0.5f to getMidBorderColor(borderScheme),
                    1.0f to getBottomBorderColor(borderScheme),
                    startY = 0.0f,
                    endY = size.height,
                    tileMode = TileMode.Repeated
                ),
                alpha = alpha
            )
        }
    }

    /**
     * Computes the color of the top portion of the border. Override to provide different visual.
     *
     * @param borderScheme The border color scheme.
     * @return The color of the top portion of the border.
     */
    open fun getTopBorderColor(borderScheme: AuroraColorScheme): Color {
        return borderScheme.ultraDarkColor
    }

    /**
     * Computes the color of the middle portion of the border. Override to provide different visual.
     *
     * @param borderScheme The border color scheme.
     * @return The color of the middle portion of the border.
     */
    open fun getMidBorderColor(borderScheme: AuroraColorScheme): Color {
        return borderScheme.darkColor
    }

    /**
     * Computes the color of the bottom portion of the border. Override to provide different visual.
     *
     * @param borderScheme The border color scheme.
     * @return The color of the bottom portion of the border.
     */
    open fun getBottomBorderColor(borderScheme: AuroraColorScheme): Color {
        return borderScheme.darkColor.interpolateTowards(borderScheme.midColor, 0.5f)
    }

    override fun getRepresentativeColor(borderScheme: AuroraColorScheme): Color {
        return this.getMidBorderColor(borderScheme)
    }
}
