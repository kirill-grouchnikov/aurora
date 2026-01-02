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
package org.pushingpixels.aurora.theming.painter.overlay

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.DecorationAreaType

/**
 * Overlay painter that paints a single line at the top edge of the relevant
 * decoration area.
 *
 * @author Kirill Grouchnikov
 */
class TopLineOverlayPainter(private val colorTokensQuery: (ContainerColorTokens) -> Color) : AuroraOverlayPainter {
    override val displayName = "Top Line"

    override fun paintOverlay(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        width: Float,
        height: Float,
        colors: AuroraSkinColors
    ) {
        val neutralColorTokens = colors.getNeutralContainerTokens(decorationAreaType)
        with(drawScope) {
            drawLine(
                color = colorTokensQuery.invoke(neutralColorTokens),
                start = Offset.Zero,
                end = Offset(width, 0.0f)
            )
        }
    }
}