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
package org.pushingpixels.aurora.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.ComponentState
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.painter.fill.ClassicFillPainter
import org.pushingpixels.aurora.utils.getBaseOutline

object SliderConstants {
    val DefaultWidth = 240.dp
    val DefaultHeight = 20.dp
    val TrackHeight = 6.dp
}

@Composable
fun AuroraSlider(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val trackFillState = if (enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    val trackSelectedState = if (enabled) ComponentState.SELECTED else ComponentState.DISABLED_SELECTED

    // install state-aware alpha channel (support for skins
    // that use translucency on disabled states).
    val stateAlpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackFillState
    )
    val fillScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackFillState
    )
    val selectionColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = trackSelectedState
    )
    val borderFillColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = trackFillState
    )
    val borderSelectionColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = trackSelectedState
    )
    val fillPainter = ClassicFillPainter.INSTANCE

    Canvas(
        modifier
            .preferredSize(
                width = SliderConstants.DefaultWidth,
                height = SliderConstants.DefaultHeight
            )
    ) {
        val radius = 1.5f.dp.toPx()

        val trackHeight = SliderConstants.TrackHeight.toPx()
        val trackY = (size.height - trackHeight) / 2.0f

        // Fill track
        fillPainter.paintContourBackground(
            drawScope = this,
            size = this.size,
            outline = Outline.Rounded(
                RoundRect(
                    left = 0.0f,
                    top = trackY,
                    right = size.width,
                    bottom = trackY + trackHeight,
                    cornerRadius = CornerRadius(radius, radius)
                )
            ),
            fillScheme = fillScheme,
            alpha = stateAlpha
        )

        // Border track
        withTransform({ translate(left = 0.0f, top = trackY) }) {
            val trackOutline = getBaseOutline(
                width = size.width,
                height = trackHeight,
                radius = radius,
                straightSides = emptySet(),
                insets = 0.5f
            )
            drawOutline(
                outline = trackOutline,
                style = Stroke(width = 1.0f),
                color = borderFillColorScheme.darkColor,
                alpha = stateAlpha
            )
        }

        val selectionWidth = size.width * value / (valueRange.endInclusive - valueRange.start)
        if (selectionWidth > 0.0f) {
            // Fill selection

            // TODO - support RTL (corners) if necessary after we add the thumb
            fillPainter.paintContourBackground(
                drawScope = this,
                size = this.size,
                outline = Outline.Rounded(
                    RoundRect(
                        left = 0.0f,
                        top = trackY,
                        right = selectionWidth,
                        bottom = trackY + trackHeight,
                        cornerRadius = CornerRadius(radius, radius)
                    )
                ),
                fillScheme = selectionColorScheme,
                alpha = stateAlpha
            )

            // Border selection
            withTransform({ translate(left = 0.0f, top = trackY) }) {
                val selectionOutline = getBaseOutline(
                    width = selectionWidth,
                    height = trackHeight,
                    radius = radius,
                    straightSides = setOf(Side.END),
                    insets = 0.5f
                )
                drawOutline(
                    outline = selectionOutline,
                    style = Stroke(width = 1.0f),
                    color = borderSelectionColorScheme.darkColor,
                    alpha = stateAlpha
                )
            }
        }
    }
}
