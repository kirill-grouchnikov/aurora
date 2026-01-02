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
package org.pushingpixels.aurora.theming.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.DecorationAreaType

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
        colorTokens: ContainerColorTokens
    ) {
        if (decorationAreaType === DecorationAreaType.TitlePane) {
            drawScope.paintTitleBackground(outline, colorTokens)
        } else {
            drawScope.paintExtraBackground(outline, rootSize, offsetFromRoot, colorTokens)
        }
    }

    private fun DrawScope.paintTitleBackground(
        outline: Outline,
        colorTokens: ContainerColorTokens
    ) {
        val boundingRect = outline.bounds

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

            val edgeColor = colorTokens.containerSurface
            val centerColor = if (colorTokens.isDark)
                colorTokens.containerSurfaceHigh
            else
                colorTokens.containerSurfaceLowest

            val topGradient = Brush.horizontalGradient(
                0.0f to edgeColor,
                0.5f to centerColor,
                1.0f to edgeColor,
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

            val edgeBottomColor = if (colorTokens.isDark)
                colorTokens.containerSurfaceLowest
            else
                colorTokens.containerSurfaceHighest
            val centerBottomColor = colorTokens.containerSurface

            val bottomGradient = Brush.horizontalGradient(
                0.0f to edgeBottomColor,
                0.5f to centerBottomColor,
                1.0f to edgeBottomColor,
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

    private fun DrawScope.paintExtraBackground(
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    ) {
        val edgeBottomColor = if (colorTokens.isDark)
            colorTokens.containerSurfaceLowest
        else
            colorTokens.containerSurfaceHighest
        val centerBottomColor = colorTokens.containerSurface

        val gradientBottom = Brush.horizontalGradient(
            0.0f to edgeBottomColor,
            0.5f to centerBottomColor,
            1.0f to edgeBottomColor,
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
