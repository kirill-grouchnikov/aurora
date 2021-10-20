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
package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.theming.ColorSchemeAssociationKind
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.ModelStateInfoSnapshot
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.utils.MutableColorScheme

abstract class TransitionAwarePainterDelegate: Painter() {
    abstract fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter

    override val intrinsicSize: Size = Size.Unspecified

    override fun DrawScope.onDraw() {
        // Do nothing, this only pretends to be a painter
    }
}

/**
 * Painter with transition-aware capabilities. Has a delegate that does the actual
 * painting based on the transition color schemes.
 */
class TransitionAwarePainter(
    val iconSize: Dp,
    val decorationAreaType: DecorationAreaType,
    val skinColors: AuroraSkinColors,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val paintDelegate: (drawScope: DrawScope, iconSize: Dp, colorScheme: AuroraColorScheme) -> Unit,
    val density: Density
) : Painter() {

    private val mutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        populateColorScheme(
            colorScheme = mutableColorScheme,
            modelStateInfo = modelStateInfoSnapshot,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            associationKind = ColorSchemeAssociationKind.Mark
        )
        paintDelegate.invoke(this, iconSize, mutableColorScheme)
    }
}