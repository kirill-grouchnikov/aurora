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

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.ComponentState
import org.pushingpixels.aurora.ComponentStateFacet
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.utils.getBaseOutline
import kotlin.math.min

@Composable
fun AuroraCircularProgress(
    modifier: Modifier = Modifier,
    contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    presentationModel: ProgressCircularPresentationModel = ProgressCircularPresentationModel()
) {
    val transition = rememberInfiniteTransition()
    val arcSpan by transition.animateFloat(
        initialValue = 30f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val arcStart = remember { mutableStateOf(0.0f) }
    val arcEnd = remember { mutableStateOf(0.0f) }
    // TODO - not ideal, but will do for now
    val prevArcSpan = remember { mutableStateOf(arcSpan) }

    val color = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = if (contentModel.enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    ).foregroundColor
    val alpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = if (contentModel.enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    )

    Canvas(
        modifier
            .progressSemantics()
            .size(presentationModel.size)
    ) {
        val isArcGrowing = (arcSpan > prevArcSpan.value)
        if (isArcGrowing) {
            arcStart.value = arcStart.value - 8.0f
            arcEnd.value = arcStart.value - arcSpan
        } else {
            arcEnd.value = arcEnd.value - 8.0f
            arcStart.value = arcEnd.value + arcSpan
        }

        arcStart.value = arcStart.value % 360.0f
        arcEnd.value = arcEnd.value % 360.0f

        prevArcSpan.value = arcSpan

        val diameter = min(size.width, size.height) - 2.0f
        drawArc(
            color = color,
            startAngle = arcStart.value,
            sweepAngle = arcSpan,
            useCenter = false,
            topLeft = Offset.Zero,
            size = Size(2.0f * diameter, 2.0f * diameter),
            style = Stroke(width = 1.2f.dp.toPx(), cap = StrokeCap.Butt, join = StrokeJoin.Round),
            alpha = alpha
        )
    }
}

private val DETERMINATE_SELECTED = ComponentState(
    "determinate enabled", arrayOf(
        ComponentStateFacet.ENABLE,
        ComponentStateFacet.DETERMINATE, ComponentStateFacet.SELECTION
    ),
    null
)

private val DETERMINATE_SELECTED_DISABLED = ComponentState(
    "determinate disabled", arrayOf(
        ComponentStateFacet.DETERMINATE,
        ComponentStateFacet.SELECTION
    ), arrayOf(ComponentStateFacet.ENABLE)
)

private val INDETERMINATE_SELECTED = ComponentState(
    "indeterminate enabled",
    arrayOf(ComponentStateFacet.ENABLE, ComponentStateFacet.SELECTION),
    arrayOf(ComponentStateFacet.DETERMINATE)
)

private val INDETERMINATE_SELECTED_DISABLED = ComponentState(
    "indeterminate disabled", null, arrayOf(
        ComponentStateFacet.DETERMINATE, ComponentStateFacet.ENABLE,
        ComponentStateFacet.SELECTION
    )
)

private val progressFillPainter = FractionBasedFillPainter(
    0.0f to { it.extraLightColor },
    0.5f to { it.lightColor },
    1.0f to { it.midColor },
    displayName = "Progress fill (internal)"
)

@Composable
fun AuroraIndeterminateLinearProgress(
    modifier: Modifier = Modifier,
    contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) {
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    val progressState = if (contentModel.enabled) INDETERMINATE_SELECTED else INDETERMINATE_SELECTED_DISABLED
    val borderState = if (contentModel.enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED

    // install state-aware alpha channel (support for skins
    // that use translucency on disabled states).
    val stateAlpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = progressState
    )
    val colorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = progressState
    )
    val borderColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = borderState
    )

    Canvas(
        modifier
            .progressSemantics()
            .size(
                width = presentationModel.primarySize,
                height = presentationModel.secondarySize
            )
    ) {
        val valComplete = progress * (2 * size.height + 1)
        val radius = 1.5f.dp.toPx()

        withTransform({
            clipPath(Path().also {
                it.addRoundRect(
                    RoundRect(
                        left = 0.0f,
                        top = 0.0f,
                        right = size.width,
                        bottom = size.height,
                        cornerRadius = CornerRadius(radius, radius)
                    )
                )
            })
        }) {
            drawOutline(
                outline = Outline.Rectangle(Rect(offset = Offset.Zero, size = size)),
                style = Fill,
                brush = Brush.verticalGradient(
                    0.0f to colorScheme.darkColor,
                    0.2f to colorScheme.lightColor,
                    0.5f to colorScheme.midColor,
                    0.8f to colorScheme.lightColor,
                    1.0f to colorScheme.darkColor,
                    startY = 0.0f,
                    endY = size.height,
                    tileMode = TileMode.Clamp
                ),
                alpha = stateAlpha
            )

            val stripeCount = (size.width / size.height).toInt()
            val stripeOffset = valComplete % (2 * size.height).toInt()
            val stripeWidth = 1.8f * size.height
            for (stripe in -2..stripeCount step 2) {
                val stripePos = stripe * size.height + stripeOffset

                drawPath(
                    path = Path().also {
                        it.moveTo(stripePos, 0.0f)
                        it.lineTo(stripePos + stripeWidth - 1.0f - size.height, 0.0f)
                        it.lineTo(stripePos + stripeWidth - 1.0f, size.height)
                        it.lineTo(stripePos + size.height, size.height)
                        it.close()
                    },
                    color = colorScheme.ultraLightColor,
                    alpha = stateAlpha
                )
            }
        }
        val outline = getBaseOutline(
            width = size.width,
            height = size.height,
            radius = radius,
            straightSides = emptySet(),
            insets = 0.5f
        )
        drawOutline(
            outline = outline,
            style = Stroke(width = 1.0f),
            color = borderColorScheme.darkColor,
            alpha = stateAlpha
        )
    }
}

@Composable
fun AuroraDeterminateLinearProgress(
    modifier: Modifier = Modifier,
    contentModel: ProgressDeterminateContentModel,
    presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) {
    val progressState = if (contentModel.enabled) DETERMINATE_SELECTED else DETERMINATE_SELECTED_DISABLED
    val fillState = if (contentModel.enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    val borderState = if (contentModel.enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED

    // install state-aware alpha channel (support for skins
    // that use translucency on disabled states).
    val stateAlpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = fillState
    )
    val fillScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = fillState
    )
    val progressColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = progressState
    )
    val borderColorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = borderState
    )
    val fillPainter = AuroraSkin.painters.fillPainter

    Canvas(
        modifier
            .progressSemantics()
            .size(
                width = presentationModel.primarySize,
                height = presentationModel.secondarySize
            )
    ) {
        val radius = 1.5f.dp.toPx()

        withTransform({
            clipPath(Path().also {
                it.addRoundRect(
                    RoundRect(
                        left = 0.0f,
                        top = 0.0f,
                        right = size.width,
                        bottom = size.height,
                        cornerRadius = CornerRadius(radius, radius)
                    )
                )
            })
        }) {
            fillPainter.paintContourBackground(
                drawScope = this,
                size = this.size,
                outline = Outline.Rectangle(Rect(offset = Offset.Zero, size = size)),
                fillScheme = fillScheme,
                alpha = stateAlpha
            )

            val progressWidth = size.width * contentModel.progress
            if (progressWidth > 0.0f) {
                // TODO - support RTL
                progressFillPainter.paintContourBackground(
                    drawScope = this,
                    size = this.size,
                    outline = Outline.Rectangle(Rect(offset = Offset.Zero, size = Size(progressWidth, size.height))),
                    fillScheme = progressColorScheme,
                    alpha = stateAlpha
                )
            }
        }
        val outline = getBaseOutline(
            width = size.width,
            height = size.height,
            radius = radius,
            straightSides = emptySet(),
            insets = 0.5f
        )
        drawOutline(
            outline = outline,
            style = Stroke(width = 1.0f),
            color = borderColorScheme.darkColor,
            alpha = stateAlpha
        )
    }
}
