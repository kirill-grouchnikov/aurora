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
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import org.pushingpixels.aurora.theming.Side

/**
 * Returns base outline for the specified parameters. The base outline is
 * a rectangle with rounded corners. Some corners may not be rounded based
 * on the contents of `straightSides` parameter.
 *
 * @param width Width of some UI component.
 * @param height Height of some UI component.
 * @param radius Corner radius.
 * @param straightSides Contains all sides which are straight.
 * @return The base outline for the specified parameters.
 */
fun getBaseOutline(
    width: Float, height: Float,
    radius: Float, straightSides: Set<Side>? = null,
    insets: Float = 0.0f
): Outline {
    val isTopLeftCorner = (straightSides != null
            && (straightSides.contains(Side.Left) || straightSides.contains(Side.Top)))
    val isTopRightCorner = (straightSides != null
            && (straightSides.contains(Side.Right) || straightSides.contains(Side.Top)))
    val isBottomRightCorner = (straightSides != null
            && (straightSides.contains(Side.Right) || straightSides.contains(Side.Bottom)))
    val isBottomLeftCorner = (straightSides != null
            && (straightSides.contains(Side.Left) || straightSides.contains(Side.Bottom)))

    // If all the sides are straight, the result is a simple rectangle
    if (isTopLeftCorner && isTopRightCorner && isBottomRightCorner && isBottomLeftCorner) {
        // Rectangle
        return Outline.Rectangle(
            rect = Rect(
                left = insets, top = insets,
                right = width - insets, bottom = height - insets
            )
        )
    }

    // Otherwise we have a rounded rectangle with potentially different corner radii
    // based on which sides are straight
    return Outline.Rounded(
        roundRect = RoundRect(
            left = insets, top = insets,
            right = width - insets, bottom = height - insets,
            topLeftCornerRadius = if (isTopLeftCorner) CornerRadius.Zero else CornerRadius(radius, radius),
            topRightCornerRadius = if (isTopRightCorner) CornerRadius.Zero else CornerRadius(radius, radius),
            bottomRightCornerRadius = if (isBottomRightCorner) CornerRadius.Zero else CornerRadius(radius, radius),
            bottomLeftCornerRadius = if (isBottomLeftCorner) CornerRadius.Zero else CornerRadius(radius, radius)
        )
    )
}
