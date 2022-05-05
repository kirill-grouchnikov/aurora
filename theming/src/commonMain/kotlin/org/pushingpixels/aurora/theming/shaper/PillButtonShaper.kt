/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.utils.getBaseOutline

/**
 * Button shaper that returns buttons with completely rounded corners.
 *
 * @author Kirill Grouchnikov
 */
class PillButtonShaper : AuroraButtonShaper, RectangularButtonShaper {
    override val displayName: String
        get() = "Pill"

    override fun getButtonOutline(
        width: Float,
        height: Float,
        extraInsets: Float,
        isInner: Boolean,
        sides: Sides,
        outlineKind: OutlineKind,
        density: Density
    ): Outline {
        var radius = getCornerRadius(width, height, extraInsets, density)
        if (isInner) {
            radius -= 1.0f
            if (radius < 0.0f) radius = 0.0f
        }

        return getBaseOutline(width, height, radius, sides, extraInsets, outlineKind)
    }

    override fun getCornerRadius(width: Float, height: Float, insets: Float, density: Density): Float {
        return if (width > height) {
            (height - 2 * insets) / 2.0f
        } else {
            (width - 2 * insets) / 2.0f
        }
    }

    override fun getPreferredSize(uiPreferredWidth: Float, uiPreferredHeight: Float): Size {
        // Account for additional horizontal space needed for the pill shape -
        // half the height on the left and half the height on the right
        return Size(uiPreferredWidth + uiPreferredHeight, uiPreferredHeight)
    }
}
