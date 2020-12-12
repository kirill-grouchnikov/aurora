/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.utils

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.boundingRect
import androidx.compose.ui.graphics.Outline
import org.pushingpixels.aurora.Side

fun Outline.boundingRect(): Rect {
    return when (this) {
        is Outline.Rectangle -> this.rect
        is Outline.Rounded -> this.roundRect.boundingRect
        is Outline.Generic -> this.path.getBounds()
    }
}

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
    // TODO - add RTL support
    val isTopLeftCorner = (straightSides != null
            && (straightSides.contains(Side.START) || straightSides.contains(Side.TOP)))
    val isTopRightCorner = (straightSides != null
            && (straightSides.contains(Side.END) || straightSides.contains(Side.TOP)))
    val isBottomRightCorner = (straightSides != null
            && (straightSides.contains(Side.END) || straightSides.contains(Side.BOTTOM)))
    val isBottomLeftCorner = (straightSides != null
            && (straightSides.contains(Side.START) || straightSides.contains(Side.BOTTOM)))

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
