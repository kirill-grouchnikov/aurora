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
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.skin.Sides
import org.pushingpixels.aurora.skin.utils.getBaseOutline

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
        with(drawScope) {
            return 3.0f.dp.toPx()
        }
    }

    override fun getPreferredSize(uiPreferredWidth: Float, uiPreferredHeight: Float): Size {
        return Size(uiPreferredWidth, uiPreferredHeight)
    }

    companion object {
        /**
         * Reusable instance of this shaper.
         */
        val INSTANCE = ClassicButtonShaper()
    }
}