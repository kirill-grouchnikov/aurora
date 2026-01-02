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
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import org.pushingpixels.aurora.theming.utils.getClassicCornerRadius

/**
 * Button shaper that returns rectangular buttons with slightly rounded corners.
 *
 * @author Kirill Grouchnikov
 */
class ClassicButtonShaper : AuroraButtonShaper, RectangularButtonShaper {
    override val displayName: String
        get() = "Classic"

    override fun getButtonOutline(
        width: Float,
        height: Float,
        insets: Float,
        sides: Sides,
        radiusAdjustment: Float,
        outlineKind: OutlineKind,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radius = (getCornerRadius(width, height, insets, density) - radiusAdjustment).coerceAtLeast(0.0f)

        return getBaseOutline(layoutDirection, width, height, radius, sides, insets, outlineKind)
    }

    override fun getCornerRadius(width: Float, height: Float, insets: Float, density: Density): Float {
        return density.getClassicCornerRadius()
    }

    override fun getExtraContentPadding(uiPreferredSize: Size, layoutDirection: LayoutDirection, density: Density): PaddingValues {
        return PaddingValues.Zero
    }

    companion object {
        /**
         * Reusable instance of this shaper.
         */
        val Instance = ClassicButtonShaper()
    }
}