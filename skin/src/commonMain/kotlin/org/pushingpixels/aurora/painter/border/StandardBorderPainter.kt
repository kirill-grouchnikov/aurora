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

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.interpolateTowards

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
