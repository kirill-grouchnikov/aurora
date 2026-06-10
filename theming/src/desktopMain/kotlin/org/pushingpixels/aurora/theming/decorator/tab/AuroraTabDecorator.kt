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
package org.pushingpixels.aurora.theming.decorator.tab

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.theming.shaper.OutlineSupplier

interface AuroraTabDecorator {
    fun getTabExtraPadding(): PaddingValues

    @Composable
    fun getTabContentColor(currState: ComponentState,
        tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
        backgroundAppearanceStrategy: BackgroundAppearanceStrategy) : Color

    @Composable
    fun getDecoratedTabContentColor(currState: ComponentState,
        activeStates: Map<ComponentState, Float>,
        parentDecorationAreaType: DecorationAreaType,
        tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
        backgroundAppearanceStrategy: BackgroundAppearanceStrategy) : Color

    @Composable
    fun getTabOutlineColor(currState: ComponentState,
        tokensOverlayProvider: ContainerColorTokensOverlay.Provider?) : Color

    fun shouldDrawUnbrokenContentEdge(): Boolean

    fun paintTabSurface(
        drawScope: DrawScope,
        skinColors: AuroraSkinColors,
        decorationAreaType: DecorationAreaType,
        decorationPainter: AuroraDecorationPainter,
        outlineFill: Outline,
        density: Density,
        rootSize: Size,
        offsetFromRoot: Offset,
        size: Size,
        surfaceColorTokens: ContainerColorTokens,
        alpha: Float)

    fun paintTabSurfaceHighlight(
        drawScope: DrawScope, outlineSupplier: OutlineSupplier,
        density: Density, size: Size,
        surfaceHighlightColorTokens: ContainerColorTokens, alpha: Float)

    fun paintTabOutline(
        drawScope: DrawScope, outlineSupplier: OutlineSupplier,
        density: Density, size: Size,
        outlineColor: Color, alpha: Float)
}