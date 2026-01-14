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

package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType

internal object ArrowSizingConstants {
    val DefaultArrowStroke = 2.0.dp
    val DefaultSingleArrowWidth = 10.dp
    val DefaultSingleArrowHeight = 7.dp
    val DefaultDoubleArrowWidth = 8.dp
    val DefaultDoubleArrowHeight = 5.dp
    val DefaultDoubleArrowStroke = 1.5.dp
    val DefaultDoubleArrowGap = 3.0.dp
}

private enum class ArrowDirection {
    Upward, Downward, Leftward, Rightward, UpAndDown
}

private fun PopupPlacementStrategy.toArrowDirection(layoutDirection: LayoutDirection): ArrowDirection {
    return when (this) {
        is PopupPlacementStrategy.CenteredVertically.HAlignStart,
        is PopupPlacementStrategy.CenteredVertically.HAlignEnd -> ArrowDirection.UpAndDown

        is PopupPlacementStrategy.Upward.HAlignStart,
        is PopupPlacementStrategy.Upward.HAlignEnd -> ArrowDirection.Upward

        is PopupPlacementStrategy.Downward.HAlignStart,
        is PopupPlacementStrategy.Downward.HAlignEnd -> ArrowDirection.Downward

        is PopupPlacementStrategy.Startward.VAlignTop,
        is PopupPlacementStrategy.Startward.VAlignBottom ->
            if (layoutDirection == LayoutDirection.Ltr) ArrowDirection.Leftward
            else ArrowDirection.Rightward

        is PopupPlacementStrategy.Endward.VAlignTop,
        is PopupPlacementStrategy.Endward.VAlignBottom ->
            if (layoutDirection == LayoutDirection.Ltr) ArrowDirection.Rightward
            else ArrowDirection.Leftward
    }
}

internal fun drawArrow(
    drawScope: DrawScope,
    width: Float, height: Float, strokeWidth: Float,
    popupPlacementStrategy: PopupPlacementStrategy,
    layoutDirection: LayoutDirection,
    color: Color
) {
    val direction = popupPlacementStrategy.toArrowDirection(layoutDirection)
    if (direction == ArrowDirection.UpAndDown) {
        val smallHeight = height - strokeWidth / 2.0f

        drawScope.translate(left = 0.0f, top = -strokeWidth - 1.0f) {
            drawArrow(
                drawScope = this,
                width = width,
                height = smallHeight,
                strokeWidth = strokeWidth,
                popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
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
                popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd,
                layoutDirection = layoutDirection,
                color = color
            )
        }
        return
    }

    val cushion = strokeWidth / 2.0f
    val gp = Path()

    when (direction) {
        ArrowDirection.Downward -> {
            gp.moveTo(cushion, cushion)
            gp.lineTo(0.5f * width, height - cushion - 1)
            gp.lineTo(width - cushion, cushion)
        }

        ArrowDirection.Upward -> {
            gp.moveTo(cushion, height - cushion - 1)
            gp.lineTo(0.5f * width, cushion)
            gp.lineTo(width - cushion, height - cushion - 1)
        }

        ArrowDirection.Leftward -> {
            gp.moveTo(width - 1 - cushion, cushion)
            gp.lineTo(cushion, 0.5f * height)
            gp.lineTo(width - 1 - cushion, height - cushion)
        }

        ArrowDirection.Rightward -> {
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

internal fun drawDoubleArrow(
    drawScope: DrawScope,
    width: Float, height: Float, gap: Float, strokeWidth: Float,
    popupPlacementStrategy: PopupPlacementStrategy, layoutDirection: LayoutDirection,
    color: Color
) {
    val direction = popupPlacementStrategy.toArrowDirection(layoutDirection)
    require(direction != ArrowDirection.UpAndDown) {
        "CenteredVertically not supported for double arrows"
    }

    val cushion = strokeWidth / 2.0f
    val gp = Path()

    when (direction) {
        ArrowDirection.Downward -> {
            // top part
            gp.moveTo(cushion, cushion)
            gp.lineTo(0.5f * width, height - gap - cushion - 1)
            gp.lineTo(width - cushion, cushion)
            // bottom part
            gp.moveTo(cushion, gap + cushion)
            gp.lineTo(0.5f * width, height - cushion - 1)
            gp.lineTo(width - cushion, gap + cushion)
        }

        ArrowDirection.Upward -> {
            // top part
            gp.moveTo(cushion, height - gap - cushion - 1)
            gp.lineTo(0.5f * width, cushion)
            gp.lineTo(width - cushion, height - gap - cushion - 1)
            // bottom part
            gp.moveTo(cushion, height - cushion - 1)
            gp.lineTo(0.5f * width, gap + cushion)
            gp.lineTo(width - cushion, height - cushion - 1)
        }

        ArrowDirection.Leftward -> {
            // left part
            gp.moveTo(width - gap - 1 - cushion, cushion)
            gp.lineTo(cushion, 0.5f * height)
            gp.lineTo(width - gap - 1 - cushion, height - cushion)
            // right part
            gp.moveTo(width - 1 - cushion, cushion)
            gp.lineTo(gap + cushion, 0.5f * height)
            gp.lineTo(width - 1 - cushion, height - cushion)
        }

        ArrowDirection.Rightward -> {
            // left part
            gp.moveTo(cushion, cushion)
            gp.lineTo(width - gap - 1 - cushion, 0.5f * height)
            gp.lineTo(cushion, height - cushion)
            // right part
            gp.moveTo(gap + cushion, cushion)
            gp.lineTo(width - 1 - cushion, 0.5f * height)
            gp.lineTo(gap + cushion, height - cushion)
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

@OptIn(AuroraInternalApi::class)
internal fun getStartwardDoubleArrowIcon(
    decorationAreaType: DecorationAreaType,
    skinColors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    inactiveContainerType: ContainerType,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    density: Density
): Painter {
    return object : TransitionAwarePainterDelegate() {
        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
            return TransitionAwarePainter(
                iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                decorationAreaType = decorationAreaType,
                skinColors = skinColors,
                tokensOverlayProvider = tokensOverlayProvider,
                inactiveContainerType = inactiveContainerType,
                backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                modelStateInfoSnapshot = modelStateInfoSnapshot,
                paintDelegate = { drawScope, iconSize, colorTokens ->
                    with(drawScope) {
                        val arrowDoubleWidth =
                            ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                    ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                        val arrowDoubleHeight =
                            ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                        val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                        val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                        val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                            colorTokens.onContainerDisabledAlpha else 1.0f
                        translate(left = dx, top = dy) {
                            drawDoubleArrow(
                                drawScope = this,
                                width = arrowDoubleWidth,
                                height = arrowDoubleHeight,
                                gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                popupPlacementStrategy = PopupPlacementStrategy.Startward.VAlignTop,
                                layoutDirection = layoutDirection,
                                color = colorTokens.onContainer.withAlpha(alpha)
                            )
                        }
                    }
                },
                density = density
            )
        }
    }
}

@AuroraInternalApi
fun getEndwardDoubleArrowIcon(
    decorationAreaType: DecorationAreaType,
    skinColors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    inactiveContainerType: ContainerType,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    density: Density
): Painter {
    return object : TransitionAwarePainterDelegate() {
        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
            return TransitionAwarePainter(
                iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                decorationAreaType = decorationAreaType,
                skinColors = skinColors,
                tokensOverlayProvider = tokensOverlayProvider,
                inactiveContainerType = inactiveContainerType,
                backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                modelStateInfoSnapshot = modelStateInfoSnapshot,
                paintDelegate = { drawScope, iconSize, colorTokens ->
                    with(drawScope) {
                        val arrowDoubleWidth =
                            ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                    ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                        val arrowDoubleHeight =
                            ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                        val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                        val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                        val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                            colorTokens.onContainerDisabledAlpha else 1.0f
                        translate(left = dx, top = dy) {
                            drawDoubleArrow(
                                drawScope = this,
                                width = arrowDoubleWidth,
                                height = arrowDoubleHeight,
                                gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                popupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
                                layoutDirection = layoutDirection,
                                color = colorTokens.onContainer.withAlpha(alpha)
                            )
                        }
                    }
                },
                density = density
            )
        }
    }
}
