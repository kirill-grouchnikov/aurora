/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import org.pushingpixels.mosaic.Side

/**
 * Returns basic outline for the specified parameters. The basic outline is
 * a rectangle with rounded corners. Some corners may not be rounded based
 * on the contents of `straightSide` parameter.
 *
 * @param width
 * Width of some UI component.
 * @param height
 * Height of some UI component.
 * @param radius
 * Corner radius.
 * @param straightSides
 * Contains all sides which are straight.
 * @return The basic outline for the specified parameters.
 */
internal fun getBaseOutline(
    width: Float, height: Float,
    radius: Float, straightSides: Set<Side>? = null,
    insets: Float = 0.0f
): Outline {
    var width = width
    var height = height
    val isTopLeftCorner = (straightSides != null
            && (straightSides.contains(Side.LEFT) || straightSides
        .contains(Side.TOP)))
    val isTopRightCorner = (straightSides != null
            && (straightSides.contains(Side.RIGHT) || straightSides
        .contains(Side.TOP)))
    val isBottomRightCorner = (straightSides != null
            && (straightSides.contains(Side.RIGHT) || straightSides
        .contains(Side.BOTTOM)))
    val isBottomLeftCorner = (straightSides != null
            && (straightSides.contains(Side.LEFT) || straightSides
        .contains(Side.BOTTOM)))
    width -= 2 * insets
    height -= 2 * insets
    val result = Path()
    if (isTopLeftCorner || radius <= 0.0f) {
        result.moveTo(insets, insets)
    } else {
        result.moveTo(insets + radius, insets)
    }
    if (isTopRightCorner || radius <= 0.0f) {
        result.lineTo(insets + width, insets)
    } else {
        if (isTopLeftCorner || insets + width - radius >= radius) {
            result.lineTo(insets + width - radius, insets)
        }
        result.arcTo(
            rect = Rect(
                left = insets + width - 2 * radius,
                top = insets,
                right = insets + width,
                bottom = insets + 2 * radius
            ),
            startAngleDegrees = 270.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
    }
    if (isBottomRightCorner || radius <= 0.0f) {
        result.lineTo(insets + width, insets + height)
    } else {
        if (isTopRightCorner || insets + height - radius >= radius) {
            result.lineTo(insets + width, insets + height - radius)
        }
        result.arcTo(
            rect = Rect(
                left = insets + width - 2 * radius,
                top = insets + height - 2 * radius,
                right = insets + width,
                bottom = insets + height
            ),
            startAngleDegrees = 0.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
    }
    if (isBottomLeftCorner || radius <= 0.0f) {
        result.lineTo(insets, insets + height)
    } else {
        if (isBottomRightCorner || insets + width - radius >= radius) {
            result.lineTo(insets + radius, insets + height)
        }
        result.arcTo(
            rect = Rect(
                left = insets,
                top = insets + height - 2 * radius,
                right = insets + 2 * radius,
                bottom = insets + height
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
    }
    if (isTopLeftCorner || radius == 0.0f) {
        result.lineTo(insets, insets)
    } else {
        if (isBottomLeftCorner || insets + height - radius >= radius) {
            result.lineTo(insets, insets + radius)
        }
        result.arcTo(
            rect = Rect(
                left = insets,
                top = insets,
                right = insets + 2 * radius,
                bottom = insets + 2 * radius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
    }
    result.close()
    return Outline.Generic(result)
}
