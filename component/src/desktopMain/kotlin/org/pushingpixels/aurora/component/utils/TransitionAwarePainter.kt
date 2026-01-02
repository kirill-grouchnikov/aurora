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
package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.MutableContainerColorTokens

abstract class TransitionAwarePainterDelegate: Painter() {
    abstract fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter

    override val intrinsicSize: Size = Size.Unspecified

    override fun DrawScope.onDraw() {
        // Do nothing, this only pretends to be a painter
    }
}

/**
 * Painter with transition-aware capabilities. Has a delegate that does the actual
 * painting based on the transition color tokens.
 */
@OptIn(AuroraInternalApi::class)
class TransitionAwarePainter(
    val iconSize: Dp,
    val decorationAreaType: DecorationAreaType,
    val skinColors: AuroraSkinColors,
    val tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    val inactiveContainerType: ContainerType,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val paintDelegate: (drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) -> Unit,
    val density: Density
) : Painter() {

    private val mutableColorTokens = MutableContainerColorTokens()

    override val intrinsicSize: Size
        get() = Size(iconSize.value * density.density, iconSize.value * density.density)

    override fun DrawScope.onDraw() {
        populateColorTokens(
            colorTokens = mutableColorTokens,
            colors = skinColors,
            tokensOverlayProvider = tokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            modelStateInfoSnapshot = modelStateInfoSnapshot,
            associationKind = ContainerColorTokensAssociationKind.Mark,
            backgroundAppearanceStrategy = backgroundAppearanceStrategy,
            treatEnabledAsActive = false,
            skipFlatCheck = false,
            inactiveContainerType = inactiveContainerType)

        this.withTransform({
            clipRect(
                left = 0.0f,
                top = 0.0f,
                right = intrinsicSize.width,
                bottom = intrinsicSize.height,
                clipOp = ClipOp.Intersect
            )
        }) {
            paintDelegate.invoke(this, iconSize, mutableColorTokens)
        }
    }
}