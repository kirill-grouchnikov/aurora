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

import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.DecorationAreaType

/**
 * Overlay painter interface.
 *
 * @author Kirill Grouchnikov
 */
interface AuroraOverlayPainter : AuroraTrait {
    /**
     * Paints the overlay.
     *
     * @param drawScope Draw scope.
     * @param decorationAreaType Decoration area type.
     * @param width Width.
     * @param height Height.
     * @param colors Colors for painting the overlay.
     */
    fun paintOverlay(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        width: Float,
        height: Float,
        colors: AuroraSkinColors
    )
}