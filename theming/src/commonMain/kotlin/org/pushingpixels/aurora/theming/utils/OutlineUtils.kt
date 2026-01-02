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
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides

/**
 * Returns a base outline for the specified parameters. The base outline is
 * a rectangle with rounded corners. Some corners may not be rounded based on
 * the passed `sides`. If the `outlineKind` is `OutlineKind.Border` and the
 * `sides` has open side(s), the returned outline will have a non-continuous path
 * with "jumps" that correspond to the open side(s).
 */
fun getBaseOutline(
    layoutDirection: LayoutDirection,
    width: Float, height: Float,
    radius: Float, sides: Sides? = null,
    insets: Float = 0.0f, outlineKind: OutlineKind = OutlineKind.Outline
): Outline {
    val leftSide = if (layoutDirection == LayoutDirection.Ltr) Side.Leading else Side.Trailing
    val rightSide = if (layoutDirection == LayoutDirection.Ltr) Side.Trailing else Side.Leading

    val straightSides = sides?.straightSides
    val isTopLeftCorner = (straightSides != null
            && (straightSides.contains(leftSide) || straightSides.contains(Side.Top)))
    val isTopRightCorner = (straightSides != null
            && (straightSides.contains(rightSide) || straightSides.contains(Side.Top)))
    val isBottomRightCorner = (straightSides != null
            && (straightSides.contains(rightSide) || straightSides.contains(Side.Bottom)))
    val isBottomLeftCorner = (straightSides != null
            && (straightSides.contains(leftSide) || straightSides.contains(Side.Bottom)))

    val openSides = sides?.openSides
    val hasOpenSides = !openSides.isNullOrEmpty()

    if (!hasOpenSides) {
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
    // Open sides are handled differently for fill and border outline kinds. For fill we need
    // to return a fully continuous and closed path so that it can be filled. For border, we
    // respect the open sides, using moveTo instead of lineTo for the relevant side(s).
    val isTopOpen = openSides.contains(Side.Top) && (outlineKind == OutlineKind.Outline)
    val isBottomOpen = openSides.contains(Side.Bottom) && (outlineKind == OutlineKind.Outline)
    val isLeftOpen = openSides.contains(leftSide) && (outlineKind == OutlineKind.Outline)
    val isRightOpen = openSides.contains(rightSide) && (outlineKind == OutlineKind.Outline)

    val path = Path()
    // Start in top left
    if (isTopLeftCorner) {
        path.moveTo(x = insets, y = insets)
    } else {
        path.moveTo(x = insets + radius, y = insets)
    }
    // To top right
    if (isTopOpen) {
        if (isTopRightCorner) {
            path.moveTo(x = width - insets, y = insets)
        } else {
            path.moveTo(x = width - insets - radius, y = insets)
        }
    } else {
        if (isTopRightCorner) {
            path.lineTo(x = width - insets, y = insets)
        } else {
            path.lineTo(x = width - insets - radius, y = insets)
        }
    }
    // Rounded top right corner
    if (!isTopRightCorner) {
        path.arcTo(
            rect = Rect(
                left = width - insets - 2 * radius,
                top = insets,
                right = width - insets,
                bottom = insets + 2 * radius
            ), startAngleDegrees = 270.0f, sweepAngleDegrees = 90.0f, forceMoveTo = false
        )
    }
    // To bottom right
    if (isRightOpen) {
        if (isBottomRightCorner) {
            path.moveTo(x = width - insets, y = height - insets)
        } else {
            path.moveTo(x = width - insets, y = height - insets - radius)
        }
    } else {
        if (isBottomRightCorner) {
            path.lineTo(x = width - insets, y = height - insets)
        } else {
            path.lineTo(x = width - insets, y = height - insets - radius)
        }
    }
    // Rounded bottom right corner
    if (!isBottomRightCorner) {
        path.arcTo(
            rect = Rect(
                left = width - insets - 2 * radius,
                top = height - insets - 2 * radius,
                right = width - insets,
                bottom = height - insets
            ), startAngleDegrees = 0.0f, sweepAngleDegrees = 90.0f, forceMoveTo = false
        )
    }
    // To bottom left
    if (isBottomOpen) {
        if (isBottomLeftCorner) {
            path.moveTo(x = insets, y = height - insets)
        } else {
            path.moveTo(x = insets + radius, y = height - insets)
        }
    } else {
        if (isBottomLeftCorner) {
            path.lineTo(x = insets, y = height - insets)
        } else {
            path.lineTo(x = insets + radius, y = height - insets)
        }
    }
    // Rounded bottom left corner
    if (!isBottomLeftCorner) {
        path.arcTo(
            rect = Rect(
                left = insets,
                top = height - insets - 2 * radius,
                right = insets + 2 * radius,
                bottom = height - insets
            ), startAngleDegrees = 90.0f, sweepAngleDegrees = 90.0f, forceMoveTo = false
        )
    }
    // To top left
    if (isLeftOpen) {
        if (isTopLeftCorner) {
            path.moveTo(x = insets, y = insets)
        } else {
            path.moveTo(x = insets, y = insets + radius)
        }
    } else {
        if (isTopLeftCorner) {
            path.lineTo(x = insets, y = insets)
        } else {
            path.lineTo(x = insets, y = insets + radius)
        }
    }
    // Rounded top left corner
    if (!isTopLeftCorner) {
        path.arcTo(
            rect = Rect(
                left = insets,
                top = insets,
                right = insets + 2 * radius,
                bottom = insets + 2 * radius
            ), startAngleDegrees = 180.0f, sweepAngleDegrees = 90.0f, forceMoveTo = false
        )
    }

    return Outline.Generic(path = path)
}

fun Density.getClassicCornerRadius(): Float = 3.0.dp.toPx()
