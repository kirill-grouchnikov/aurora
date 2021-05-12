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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.PopupPlacementStrategy

internal fun drawArrow(
    drawScope: DrawScope,
    width: Float, height: Float, strokeWidth: Float,
    direction: PopupPlacementStrategy, layoutDirection: LayoutDirection,
    color: Color
) {
    if (direction == PopupPlacementStrategy.CENTERED_VERTICALLY) {
        val smallHeight = height - strokeWidth / 2.0f

        drawScope.translate(left = 0.0f, top = -strokeWidth - 1.0f) {
            drawArrow(
                drawScope = this,
                width = width,
                height = smallHeight,
                strokeWidth = strokeWidth,
                direction = PopupPlacementStrategy.UPWARD,
                layoutDirection = layoutDirection,
                color = color
            )
        }

        drawScope.translate(left = 0.0f, top = height / 2.0f + strokeWidth - 1.0f) {
            drawArrow(
                drawScope = this,
                width = width,
                height = smallHeight,
                strokeWidth = strokeWidth,
                direction = PopupPlacementStrategy.DOWNWARD,
                layoutDirection = layoutDirection,
                color = color
            )
        }
        return
    }

    val cushion = strokeWidth / 2.0f
    val gp = Path()

    if (direction == PopupPlacementStrategy.DOWNWARD) {
        gp.moveTo(cushion, cushion)
        gp.lineTo(0.5f * width, height - cushion - 1)
        gp.lineTo(width - cushion, cushion)
    } else if (direction == PopupPlacementStrategy.UPWARD) {
        gp.moveTo(cushion, height - cushion - 1)
        gp.lineTo(0.5f * width, cushion)
        gp.lineTo(width - cushion, height - cushion - 1)
    } else {
        val leftward =
            ((direction == PopupPlacementStrategy.STARTWARD) && (layoutDirection == LayoutDirection.Ltr)) ||
                    ((direction == PopupPlacementStrategy.ENDWARD) && (layoutDirection == LayoutDirection.Rtl))
        if (leftward) {
            gp.moveTo(width - 1 - cushion, cushion)
            gp.lineTo(cushion, 0.5f * height)
            gp.lineTo(width - 1 - cushion, height - cushion)
        } else {
            gp.moveTo(cushion, cushion)
            gp.lineTo(width - 1 - cushion, 0.5f * height)
            gp.lineTo(cushion, height - cushion)
        }
    }

    with(drawScope) {
        drawPath(
            path = gp,
            color = color,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Miter)
        )
    }
}

