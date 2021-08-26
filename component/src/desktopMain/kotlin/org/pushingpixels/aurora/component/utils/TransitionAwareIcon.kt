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

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.ModelStateInfoSnapshot
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.MutableColorScheme

/**
 * Icon with transition-aware capabilities. Has a delegate that does the actual
 * painting based on the transition color schemes.
 */
class TransitionAwareIcon(
    val iconSize: Dp,
    val decorationAreaType: DecorationAreaType,
    val skinColors: AuroraSkinColors,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val paintDelegate: (drawScope: DrawScope, iconSize: Dp, colorScheme: AuroraColorScheme) -> Unit,
    val density: Density
) : AuroraIcon() {

    abstract class TransitionAwareIconFactory : AuroraIcon.Factory {
        override fun createNewIcon(): AuroraIcon {
            throw UnsupportedOperationException()
        }

        abstract fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): AuroraIcon
    }

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

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        // No op
    }
}