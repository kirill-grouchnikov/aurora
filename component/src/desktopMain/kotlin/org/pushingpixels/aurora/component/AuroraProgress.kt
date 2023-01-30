/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.ProgressCircularPresentationModel
import org.pushingpixels.aurora.component.model.ProgressDeterminateContentModel
import org.pushingpixels.aurora.component.model.ProgressIndeterminateContentModel
import org.pushingpixels.aurora.component.model.ProgressLinearPresentationModel
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.ColorSchemeAssociationKind
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import kotlin.math.min

@Composable
internal fun AuroraCircularProgress(
    modifier: Modifier,
    contentModel: ProgressIndeterminateContentModel,
    presentationModel: ProgressCircularPresentationModel
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
        componentState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    ).foregroundColor
    val alpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
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
            style = Stroke(
                width = presentationModel.strokeWidth.toPx(),
                cap = StrokeCap.Butt,
                join = StrokeJoin.Round
            ),
            alpha = alpha
        )
    }
}


private val progressFillPainter = FractionBasedFillPainter(
    0.0f to { it.extraLightColor },
    0.5f to { it.lightColor },
    1.0f to { it.midColor },
    displayName = "Progress fill (internal)"
)

@Composable
internal fun AuroraIndeterminateLinearProgress(
    modifier: Modifier,
    contentModel: ProgressIndeterminateContentModel,
    presentationModel: ProgressLinearPresentationModel
) {
    val layoutDirection = LocalLayoutDirection.current

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

    val progressState =
        if (contentModel.enabled) ComponentState.Indeterminate else ComponentState.DisabledIndeterminate
    val borderState =
        if (contentModel.enabled) ComponentState.Determinate else ComponentState.DisabledDeterminate

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
        associationKind = ColorSchemeAssociationKind.Border,
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
        if ((size.width <= radius) || (size.height <= radius)) {
            // Size too small to do any meaningful painting
            return@Canvas
        }

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
                var stripePos = stripe * size.height + stripeOffset
                if (layoutDirection == LayoutDirection.Rtl) {
                    stripePos = size.width - stripePos
                }

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
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = radius,
            sides = null,
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
internal fun AuroraDeterminateLinearProgress(
    modifier: Modifier,
    contentModel: ProgressDeterminateContentModel,
    presentationModel: ProgressLinearPresentationModel
) {
    val layoutDirection = LocalLayoutDirection.current

    val progressState =
        if (contentModel.enabled) ComponentState.Determinate else ComponentState.DisabledDeterminate
    val fillState =
        if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val borderState =
        if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected

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
        associationKind = ColorSchemeAssociationKind.Border,
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
        if ((size.width <= radius) || (size.height <= radius)) {
            // Size too small to do any meaningful painting
            return@Canvas
        }

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
                progressFillPainter.paintContourBackground(
                    drawScope = this,
                    size = this.size,
                    outline = Outline.Rectangle(
                        Rect(
                            offset = if (layoutDirection == LayoutDirection.Ltr) Offset.Zero else
                                Offset(x = size.width - progressWidth, 0.0f),
                            size = Size(progressWidth, size.height)
                        )
                    ),
                    fillScheme = progressColorScheme,
                    alpha = stateAlpha
                )
            }
        }
        val outline = getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = radius,
            sides = null,
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
