/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme

/**
 * Implementation of [AuroraDecorationPainter] that uses "arc" painting on title panes and
 * lighter gradient near the horizontal center of the application frame.
 *
 * @author Kirill Grouchnikov
 */
class ArcDecorationPainter : AuroraDecorationPainter {
    override val displayName = "Arc"

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        if (decorationAreaType === DecorationAreaType.TitlePane) {
            paintTitleBackground(drawScope, outline, colorScheme)
        } else {
            paintExtraBackground(drawScope, outline, rootSize, offsetFromRoot, colorScheme)
        }
    }

    private fun paintTitleBackground(
        drawScope: DrawScope,
        outline: Outline,
        colorScheme: AuroraColorScheme
    ) {
        val boundingRect = outline.bounds

        with(drawScope) {
            withTransform({
                clipPath(path = Path().also { it.addOutline(outline) })
                translate(left = boundingRect.left, top = boundingRect.top)
            }) {
                // Top part
                val topPath = Path()
                topPath.moveTo(0.0f, 0.0f)
                topPath.lineTo(boundingRect.width, 0.0f)
                topPath.lineTo(boundingRect.width, boundingRect.height / 2.0f)
                topPath.quadraticTo(
                    boundingRect.width / 2, boundingRect.height / 4.0f,
                    0.0f, boundingRect.height / 2.0f
                )
                topPath.close()

                val topGradient = Brush.horizontalGradient(
                    0.0f to colorScheme.lightColor,
                    0.5f to colorScheme.ultraLightColor,
                    1.0f to colorScheme.lightColor,
                    startX = 0.0f,
                    endX = boundingRect.width,
                    tileMode = TileMode.Repeated
                )

                drawPath(
                    path = topPath,
                    style = Fill,
                    brush = topGradient
                )

                // Bottom part
                val bottomPath = Path()
                bottomPath.moveTo(0.0f, boundingRect.height)
                bottomPath.lineTo(boundingRect.width, boundingRect.height)
                bottomPath.lineTo(boundingRect.width, boundingRect.height / 2.0f)
                bottomPath.quadraticTo(
                    boundingRect.width / 2, boundingRect.height / 4.0f,
                    0.0f, boundingRect.height / 2.0f
                )
                bottomPath.close()

                val bottomGradient = Brush.horizontalGradient(
                    0.0f to colorScheme.midColor,
                    0.5f to colorScheme.lightColor,
                    1.0f to colorScheme.midColor,
                    startX = 0.0f,
                    endX = boundingRect.width,
                    tileMode = TileMode.Repeated
                )

                drawPath(
                    path = bottomPath,
                    style = Fill,
                    brush = bottomGradient
                )

                // Middle part (connector between the two arc parts)
                val middlePath = Path()
                middlePath.moveTo(boundingRect.width, boundingRect.height / 2.0f)
                middlePath.quadraticTo(
                    boundingRect.width / 2, boundingRect.height / 4.0f,
                    0.0f, boundingRect.height / 2.0f
                )
                middlePath.close()

                drawPath(
                    path = middlePath,
                    style = Stroke(width = 1.0f),
                    brush = bottomGradient
                )
            }
        }
    }

    private fun paintExtraBackground(
        drawScope: DrawScope,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        with(drawScope) {
            val gradientBottom = Brush.horizontalGradient(
                0.0f to colorScheme.midColor,
                0.5f to colorScheme.lightColor,
                1.0f to colorScheme.midColor,
                startX = -offsetFromRoot.x,
                endX = -offsetFromRoot.x + rootSize.width,
                tileMode = TileMode.Repeated
            )

            drawOutline(
                outline = outline,
                style = Fill,
                brush = gradientBottom
            )
        }
    }
}
