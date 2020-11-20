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
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.utils.deriveByBrightness
import org.pushingpixels.aurora.utils.getAlphaColor

/**
 * Overlay painter that paints a few pixel-high drop shadow at the bottom edge
 * of the relevant decoration area. The constructor is private to enforce that
 * [.getInstance] is the only way an application can get an instance of this class.
 *
 * @author Kirill Grouchnikov
 */
class BottomShadowOverlayPainter private constructor(private val endAlpha: Float) : AuroraOverlayPainter {
    override val displayName = "Bottom Shadow"

    override fun paintOverlay(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        width: Float,
        height: Float,
        colors: AuroraSkinColors
    ) {
        val shadowColor = deriveByBrightness(
            original = colors.getBackgroundColorScheme(decorationAreaType).backgroundFillColor,
            brightnessFactor = -0.4f
        )

        with(drawScope) {
            val shadowHeight = 4.0.dp.toPx()
            drawRect(
                topLeft = Offset(0.0f, 0.0f),
                size = Size(width, shadowHeight),
                style = Fill,
                brush = LinearGradient(
                    colors = listOf(
                        getAlphaColor(shadowColor, 16.0f / 255.0f),
                        getAlphaColor(shadowColor, endAlpha)
                    ),
                    startX = 0.0f,
                    startY = size.height - shadowHeight,
                    endX = 0.0f,
                    endY = size.height,
                    tileMode = TileMode.Clamp
                )
            )
        }
    }

    companion object {
        private val MAP: MutableMap<Int, BottomShadowOverlayPainter> = HashMap()
        private const val DEFAULT_SHADOW_END_ALPHA = 128.0f / 255.0f
        private const val MIN_SHADOW_END_ALPHA = 32.0f / 255.0f

        /**
         * Returns an instance of bottom shadow overlay painter with the requested strength.
         *
         * @param strength Drop shadow strength. Must be in [0..100] range.
         * @return Bottom shadow overlay painter with the requested strength.
         */
        @Synchronized
        fun getInstance(strength: Int): BottomShadowOverlayPainter {
            require(!(strength < 0 || strength > 100)) { "Strength must be in [0..100] range" }
            var result = MAP[strength]
            if (result == null) {
                val endAlpha = MIN_SHADOW_END_ALPHA +
                        (DEFAULT_SHADOW_END_ALPHA - MIN_SHADOW_END_ALPHA) * strength / 100
                result = BottomShadowOverlayPainter(endAlpha = endAlpha)
                MAP[strength] = result
            }
            return result
        }
    }
}