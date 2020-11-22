/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.painter.fill

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.utils.getInterpolatedColor

/**
 * Gradient painter that returns images with subtle 3D gradient appearance. This class is part of
 * officially supported API.
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
                brush = LinearGradient(
                    0.0f to getTopFillColor(fillScheme),
                    0.4999999f to getMidFillColorTop(fillScheme),
                    0.5f to getMidFillColorBottom(fillScheme),
                    1.0f to getBottomFillColor(fillScheme),
                    startX = 0.0f,
                    startY = 0.0f,
                    endX = 0.0f,
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
        return getInterpolatedColor(fillScheme.darkColor, fillScheme.midColor, 0.4f)
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