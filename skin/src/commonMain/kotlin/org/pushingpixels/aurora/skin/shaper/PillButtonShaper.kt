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
package org.pushingpixels.aurora.skin.shaper

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.skin.Sides
import org.pushingpixels.aurora.skin.utils.getBaseOutline

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
        drawScope: DrawScope
    ): Outline {
        var radius = getCornerRadius(width, height, extraInsets, drawScope)
        if (isInner) {
            radius -= 1.0f
            if (radius < 0.0f) radius = 0.0f
        }

        return getBaseOutline(
            width, height, radius, sides.straightSides,
            extraInsets
        )
    }

    override fun getCornerRadius(width: Float, height: Float, insets: Float, drawScope: DrawScope): Float {
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
