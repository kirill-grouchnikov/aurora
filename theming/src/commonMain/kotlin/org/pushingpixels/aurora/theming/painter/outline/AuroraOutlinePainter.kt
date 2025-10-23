/*
 * Copyright 2020-2025 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.theming.colortokens.ContainerColorTokens

interface OutlineSupplier {
    fun getOutline(
        layoutDirection: LayoutDirection, density: Density,
        size: Size, insets: Float, radiusAdjustment: Float
    ): Outline
}

// TODO - merge this with AuroraSlices.OutlineKind?
enum class InsetKind {
    Surface, Content
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
}
