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

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.interpolateTowards

/**
 * Fill painter that paints a subtle gradient appearance.
 *
 * @author Kirill Grouchnikov
 */
open class StandardFillPainter : AuroraFillPainter {
    override val displayName: String
        get() = "Standard"

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
                brush = Brush.verticalGradient(
                    0.0f to getTopFillColor(fillScheme),
                    0.4999999f to getMidFillColorTop(fillScheme),
                    0.5f to getMidFillColorBottom(fillScheme),
                    1.0f to getBottomFillColor(fillScheme),
                    startY = 0.0f,
                    endY = size.height,
                    tileMode = TileMode.Clamp
                ),
                alpha = alpha
            )
        }
    }

    /**
     * Computes the color of the top portion of the fill. Override to provide different visual.
     *
     * @param fillScheme
     * The fill scheme.
     * @return The color of the top portion of the fill.
     */
    open fun getTopFillColor(fillScheme: AuroraColorScheme): Color {
        return fillScheme.darkColor.interpolateTowards(fillScheme.midColor, 0.4f)
    }

    /**
     * Computes the color of the middle portion of the fill from the top. Override to provide
     * different visual.
     *
     * @param fillScheme
     * The fill scheme.
     * @return The color of the middle portion of the fill from the top.
     */
    open fun getMidFillColorTop(fillScheme: AuroraColorScheme): Color {
        return fillScheme.midColor
    }

    /**
     * Computes the color of the middle portion of the fill from the bottom. Override to provide
     * different visual.
     *
     * @param fillScheme
     * The fill scheme.
     * @return The color of the middle portion of the fill from the bottom.
     */
    open fun getMidFillColorBottom(fillScheme: AuroraColorScheme): Color {
        return getMidFillColorTop(fillScheme)
    }

    /**
     * Computes the color of the bottom portion of the fill. Override to provide different visual.
     *
     * @param fillScheme
     * The fill scheme.
     * @return The color of the bottom portion of the fill.
     */
    open fun getBottomFillColor(fillScheme: AuroraColorScheme): Color {
        return fillScheme.ultraLightColor
    }
}