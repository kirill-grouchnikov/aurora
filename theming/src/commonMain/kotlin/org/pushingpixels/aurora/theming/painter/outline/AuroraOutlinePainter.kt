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
package org.pushingpixels.aurora.theming.painter.outline

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.InsetKind
import org.pushingpixels.aurora.theming.OutlineKind

interface OutlineSupplier {
    fun getOutline(
        layoutDirection: LayoutDirection, density: Density,
        size: Size, insets: Float, radiusAdjustment: Float, outlineKind: OutlineKind
    ): Outline
}

interface AuroraOutlinePainter : AuroraTrait {
    fun paintOutline(
        drawScope: DrawScope,
        size: Size,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        alpha: Float
    )

    fun getOutlineInset(insetKind: InsetKind): Float

    fun interface Overlay {
        fun paintOutlineOverlay(
            drawScope: DrawScope,
            size: Size,
            outlineSupplier: OutlineSupplier,
            colorTokens: ContainerColorTokens,
            alpha: Float
        )
    }

    class CompositeOverlay(vararg val overlays: Overlay): Overlay {
        override fun paintOutlineOverlay(
            drawScope: DrawScope,
            size: Size,
            outlineSupplier: OutlineSupplier,
            colorTokens: ContainerColorTokens,
            alpha: Float
        ) {
            for (overlay in overlays) {
                overlay.paintOutlineOverlay(drawScope, size, outlineSupplier, colorTokens, alpha)
            }
        }
    }
}
