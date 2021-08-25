/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme

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
                topPath.quadraticBezierTo(
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
                bottomPath.quadraticBezierTo(
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
                middlePath.quadraticBezierTo(
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
