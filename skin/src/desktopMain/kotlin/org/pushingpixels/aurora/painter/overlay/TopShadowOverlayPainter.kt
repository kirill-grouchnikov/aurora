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
package org.pushingpixels.aurora.painter.overlay

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.common.withBrightness

/**
 * Overlay painter that paints a few pixel-high drop shadow at the top edge of
 * the relevant decoration area. The constructor is private to enforce that
 * [.getInstance] is the only way an application can get an instance of this class.
 *
 * @author Kirill Grouchnikov
 */
class TopShadowOverlayPainter private constructor(private val startAlpha: Float) : AuroraOverlayPainter {
    override val displayName = "Top Shadow"

    override fun paintOverlay(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        width: Float,
        height: Float,
        colors: AuroraSkinColors
    ) {
        val shadowColor =
            colors.getBackgroundColorScheme(decorationAreaType).backgroundFillColor
                .withBrightness(brightnessFactor = -0.4f)

        with(drawScope) {
            val shadowHeight = 4.0.dp.toPx()
            drawRect(
                topLeft = Offset(0.0f, 0.0f),
                size = Size(width, shadowHeight),
                style = Fill,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        shadowColor.withAlpha(startAlpha),
                        shadowColor.withAlpha(0.0625f)
                    ),
                    startY = 0.0f,
                    endY = shadowHeight,
                    tileMode = TileMode.Clamp
                )
            )
        }
    }

    companion object {
        private val MAP: MutableMap<Int, TopShadowOverlayPainter> = HashMap()
        private const val DEFAULT_SHADOW_START_ALPHA = 160.0f / 255.0f
        private const val MIN_SHADOW_START_ALPHA = 32.0f / 255.0f

        /**
         * Returns an instance of top shadow overlay painter with the requested strength.
         *
         * @param strength Drop shadow strength. Must be in [0..100] range.
         * @return Top shadow overlay painter with the requested strength.
         */
        @Synchronized
        fun getInstance(strength: Int): TopShadowOverlayPainter {
            require(!(strength < 0 || strength > 100)) { "Strength must be in [0..100] range" }
            var result = MAP[strength]
            if (result == null) {
                val startAlpha = MIN_SHADOW_START_ALPHA +
                        (DEFAULT_SHADOW_START_ALPHA - MIN_SHADOW_START_ALPHA) * strength / 100
                result = TopShadowOverlayPainter(startAlpha = startAlpha)
                MAP[strength] = result
            }
            return result
        }
    }
}