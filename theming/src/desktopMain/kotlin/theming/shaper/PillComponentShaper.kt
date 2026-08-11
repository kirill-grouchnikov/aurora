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
package org.pushingpixels.aurora.theming.shaper

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.utils.getBaseOutline

/**
 * Component shaper that returns buttons with completely rounded corners.
 *
 * @author Kirill Grouchnikov
 */
class PillComponentShaper : ClassicComponentShaper() {
    override val displayName: String
        get() = "Pill"

    override fun getButtonExtraContentPadding(uiPreferredSize: Size, layoutDirection: LayoutDirection, density: Density): PaddingValues {
        // Account for additional horizontal space needed for the pill shape -
        // half the height on the left and half the height on the right
        val horizontal = if (uiPreferredSize.width > uiPreferredSize.height) {
            uiPreferredSize.height / 2
        } else {
            0.0f
        }
        return PaddingValues(horizontal = (horizontal / density.density).dp, vertical = 0.dp)
    }

    private fun getCornerRadius(width: Float, height: Float, insets: Float, density: Density): Float {
        return if (width > height) {
            (height - 2 * insets) / 2.0f
        } else {
            (width - 2 * insets) / 2.0f
        }
    }

    override fun getButtonOutlineSupplier(buttonSides: Sides): OutlineSupplier {
        return object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val radius = (getCornerRadius(size.width, size.height, insets, density) - radiusAdjustment).coerceAtLeast(0.0f)

                return getBaseOutline(layoutDirection, size.width, size.height, radius, buttonSides, insets, outlineKind)
            }
        }
    }
}
