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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.CircularProgressPresentationModel
import org.pushingpixels.aurora.component.model.DeterminateProgressContentModel
import org.pushingpixels.aurora.component.model.IndeterminateProgressContentModel
import org.pushingpixels.aurora.component.model.LinearProgressPresentationModel
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.*
import kotlin.math.min

@Composable
internal fun circularProgressIntrinsicSize(
    presentationModel: CircularProgressPresentationModel
): Size {
    val density = LocalDensity.current
    val side = presentationModel.radius.value * density.density
    return Size(side, side)
}

@Composable
@OptIn(AuroraInternalApi::class)
internal fun AuroraIndeterminateCircularProgress(
    modifier: Modifier,
    contentModel: IndeterminateProgressContentModel,
    presentationModel: CircularProgressPresentationModel
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

    val componentState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val colorTokens = getContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = componentState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
        inactiveContainerType = ContainerType.Neutral
    )
    val color = colorTokens.onContainer
    val alpha = if (contentModel.enabled) 1.0f else colorTokens.onContainerDisabledAlpha

    Canvas(
        modifier
            .progressSemantics()
            .size(presentationModel.radius)
    ) {
        val isArcGrowing = (arcSpan > prevArcSpan.value)
        if (isArcGrowing) {
            arcStart.value -= 8.0f
            arcEnd.value = arcStart.value - arcSpan
        } else {
            arcEnd.value -= 8.0f
            arcStart.value = arcEnd.value + arcSpan
        }

        arcStart.value %= 360.0f
        arcEnd.value %= 360.0f

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
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            ),
            alpha = alpha
        )
    }
}

@Composable
@OptIn(AuroraInternalApi::class)
internal fun AuroraDeterminateCircularProgress(
    modifier: Modifier,
    contentModel: DeterminateProgressContentModel,
    presentationModel: CircularProgressPresentationModel
) {

    val componentState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val colorTokens = getContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = componentState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
        inactiveContainerType = ContainerType.Neutral
    )
    val color = colorTokens.onContainer
    val alpha = if (contentModel.enabled) 1.0f else colorTokens.onContainerDisabledAlpha

    Canvas(
        modifier
            .progressSemantics()
            .size(presentationModel.radius * 2)
    ) {
        val strokeWidthPx = presentationModel.strokeWidth.toPx()
        val diameter = 2.0f * presentationModel.radius.toPx() - strokeWidthPx
        drawArc(
            color = color,
            startAngle = 270.0f,
            sweepAngle = 360.0f * contentModel.progress,
            useCenter = false,
            topLeft = Offset(strokeWidthPx / 2.0f, strokeWidthPx / 2.0f),
            size = Size(diameter, diameter),
            style = Stroke(
                width = strokeWidthPx,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            ),
            alpha = alpha
        )
    }
}

private object LinearProgressOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = density.getClassicCornerRadius()
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = cornerRadius - radiusAdjustment,
            sides = Sides(),
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

@Composable
internal fun linearProgressIntrinsicSize(
    presentationModel: LinearProgressPresentationModel
): Size {
    val density = LocalDensity.current
    return Size(width = presentationModel.primarySize.value * density.density,
        height = presentationModel.secondarySize.value * density.density)
}

@Composable
@OptIn(AuroraInternalApi::class)
internal fun AuroraIndeterminateLinearProgress(
    modifier: Modifier,
    contentModel: IndeterminateProgressContentModel,
    presentationModel: LinearProgressPresentationModel
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

    val componentState =
        if (contentModel.enabled) ComponentState.Indeterminate else ComponentState.DisabledIndeterminate
    val fillColorTokens = getContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = componentState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Muted
    )
    val progressColorTokens = getActiveContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = componentState,
    )

    val outlinePainter = AuroraSkin.painters.outlinePainter
    val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

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
            val containerSurfaceAlpha =
                if (componentState.isDisabled) progressColorTokens.containerSurfaceDisabledAlpha else 1.0f
            drawOutline(
                outline = Outline.Rectangle(Rect(offset = Offset.Zero, size = size)),
                style = Fill,
                brush = Brush.verticalGradient(
                    0.0f to progressColorTokens.containerSurfaceHighest,
                    0.2f to progressColorTokens.containerSurface,
                    0.5f to progressColorTokens.containerSurfaceHigh,
                    0.8f to progressColorTokens.containerSurface,
                    1.0f to progressColorTokens.containerSurfaceHighest,
                    startY = 0.0f,
                    endY = size.height,
                    tileMode = TileMode.Clamp
                ),
                alpha = containerSurfaceAlpha
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
                    color = progressColorTokens.containerSurfaceLow,
                    alpha = containerSurfaceAlpha
                )
            }
        }
        paintOutline(
            drawScope = this,
            componentState = componentState,
            outlinePainter = outlinePainter,
            outlinePainterOverlay = outlinePainterOverlay,
            size = this.size,
            alpha = 1.0f,
            outlineSupplier = LinearProgressOutlineSuppler,
            colorTokens = fillColorTokens)
    }
}

@Composable
@OptIn(AuroraInternalApi::class)
internal fun AuroraDeterminateLinearProgress(
    modifier: Modifier,
    contentModel: DeterminateProgressContentModel,
    presentationModel: LinearProgressPresentationModel
) {
    val layoutDirection = LocalLayoutDirection.current

    val progressState =
        if (contentModel.enabled) ComponentState.Determinate else ComponentState.DisabledDeterminate
    val fillState =
        if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected

    val fillColorTokens = getContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = fillState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Muted
    )
    val progressColorTokens = getActiveContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = progressState,
    )

    val surfacePainter = AuroraSkin.painters.surfacePainter
    val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
    val outlinePainter = AuroraSkin.painters.outlinePainter
    val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

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
            paintSurface(
                drawScope = this,
                componentState = fillState,
                surfacePainter = surfacePainter,
                surfacePainterOverlay = surfacePainterOverlay,
                size = this.size,
                alpha = 1.0f,
                outline = Outline.Rectangle(Rect(offset = Offset.Zero, size = size)),
                colorTokens = fillColorTokens
            )

            val progressWidth = size.width * contentModel.progress
            if (progressWidth > 0.0f) {
                paintSurface(
                    drawScope = this,
                    componentState = progressState,
                    surfacePainter = surfacePainter,
                    surfacePainterOverlay = surfacePainterOverlay,
                    size = this.size,
                    alpha = 1.0f,
                    outline = Outline.Rectangle(
                        Rect(
                            offset = if (layoutDirection == LayoutDirection.Ltr) Offset.Zero else
                                Offset(x = size.width - progressWidth, 0.0f),
                            size = Size(progressWidth, size.height)
                        )
                    ),
                    colorTokens = progressColorTokens
                )
            }
        }

        paintOutline(
            drawScope = this,
            componentState = fillState,
            outlinePainter = outlinePainter,
            outlinePainterOverlay = outlinePainterOverlay,
            size = this.size,
            alpha = 1.0f,
            outlineSupplier = LinearProgressOutlineSuppler,
            colorTokens = fillColorTokens)
    }
}
