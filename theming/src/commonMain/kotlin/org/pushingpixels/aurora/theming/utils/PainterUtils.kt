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
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.painter.outline.AuroraOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.painter.surface.AuroraSurfacePainter

@AuroraInternalApi
fun paintSurface(
    drawScope: DrawScope,
    componentState: ComponentState,
    surfacePainter: AuroraSurfacePainter,
    surfacePainterOverlay: AuroraSurfacePainter.Overlay?,
    size: Size,
    alpha: Float,
    outline: Outline,
    colorTokens: ContainerColorTokens) {

    // If we're in a disabled state, apply the matching alpha
    val containerSurfaceAlpha = alpha *
            (if (componentState.isDisabled) colorTokens.containerSurfaceDisabledAlpha else 1.0f)
    surfacePainter.paintSurface(
        drawScope = drawScope,
        size = size,
        outline = outline,
        colorTokens = colorTokens,
        alpha = containerSurfaceAlpha
    )

    surfacePainterOverlay?.paintSurfaceOverlay(
        drawScope = drawScope,
        size = size,
        outline = outline,
        colorTokens = colorTokens,
        alpha = containerSurfaceAlpha
    )
}

@AuroraInternalApi
fun paintOutline(
    drawScope: DrawScope,
    componentState: ComponentState,
    outlinePainter: AuroraOutlinePainter,
    outlinePainterOverlay: AuroraOutlinePainter.Overlay?,
    size: Size,
    alpha: Float,
    outlineSupplier: OutlineSupplier,
    colorTokens: ContainerColorTokens) {

    // If we're in a disabled state, apply the matching alpha
    val containerOutlineAlpha = alpha *
            (if (componentState.isDisabled) colorTokens.containerOutlineDisabledAlpha else 1.0f)
    outlinePainter.paintOutline(
        drawScope = drawScope,
        size = size,
        colorTokens = colorTokens,
        alpha = containerOutlineAlpha,
        outlineSupplier = outlineSupplier,
    )

    outlinePainterOverlay?.paintOutlineOverlay(
        drawScope = drawScope,
        size = size,
        colorTokens = colorTokens,
        alpha = containerOutlineAlpha,
        outlineSupplier = outlineSupplier,
    )
}