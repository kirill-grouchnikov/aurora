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
package org.pushingpixels.aurora.skin.painter.overlay

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.common.withBrightness
import org.pushingpixels.aurora.skin.DecorationAreaType
import org.pushingpixels.aurora.skin.colorscheme.AuroraSkinColors

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
        val shadowColor =
            colors.getBackgroundColorScheme(decorationAreaType).backgroundFillColor
                .withBrightness(brightnessFactor = -0.4f)

        with(drawScope) {
            val shadowHeight = 4.0.dp.toPx()
            translate(top = size.height - shadowHeight) {
                drawRect(
                    topLeft = Offset.Zero,
                    size = Size(width, shadowHeight),
                    style = Fill,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            shadowColor.withAlpha(0.0625f),
                            shadowColor.withAlpha(endAlpha)
                        ),
                        startY = 0.0f,
                        endY = shadowHeight,
                        tileMode = TileMode.Clamp
                    )
                )
            }
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
