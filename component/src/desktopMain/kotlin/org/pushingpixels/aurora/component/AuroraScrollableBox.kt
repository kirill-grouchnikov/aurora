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

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.utils.ArrowSizingConstants
import org.pushingpixels.aurora.component.utils.TransitionAwarePainter
import org.pushingpixels.aurora.component.utils.TransitionAwarePainterDelegate
import org.pushingpixels.aurora.component.utils.drawDoubleArrow
import org.pushingpixels.aurora.theming.*
import kotlin.math.roundToInt

object ScrollableBoxConstants {
    val DefaultScrollAmount = 12.dp
}

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraHorizontallyScrollableBox(
    modifier: Modifier,
    height: Dp,
    contentWidth: () -> Int,
    horizontalScrollState: ScrollState,
    scrollAmount: Dp = ScrollableBoxConstants.DefaultScrollAmount,
    content: @Composable () -> Unit
) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    // Presentation model for the scroller buttons. Note usage of Original icon filter strategy
    // since we're using TransitionAwarePainterDelegate to draw the double arrows, and
    // OnRollover action fire trigger for interacting with the scroller buttons.
    val scrollerPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Small,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        iconActiveFilterStrategy = IconFilterStrategy.Original,
        iconEnabledFilterStrategy = IconFilterStrategy.Original,
        iconDisabledFilterStrategy = IconFilterStrategy.Original,
        contentPadding = PaddingValues(0.dp),
        actionFireTrigger = ActionFireTrigger.OnRollover,
        autoRepeatAction = true,
        autoRepeatInitialInterval = 200L,
        autoRepeatSubsequentInterval = 50L
    )
    val scrollerLayoutManager = scrollerPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val scope = rememberCoroutineScope()
    val scrollAmountPx = scrollAmount.value * density.density

    val leftScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowDoubleWidth =
                                ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                        ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                            val arrowDoubleHeight =
                                ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                            val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawDoubleArrow(
                                    drawScope = this,
                                    width = arrowDoubleWidth,
                                    height = arrowDoubleHeight,
                                    gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Startward.VAlignTop,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (horizontalScrollState.value > 0),
        action = {
            scope.launch {
                horizontalScrollState.scrollTo((horizontalScrollState.value - scrollAmountPx.toInt()).coerceAtLeast(0))
            }
        })
    val rightScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowDoubleWidth =
                                ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                        ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                            val arrowDoubleHeight =
                                ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                            val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawDoubleArrow(
                                    drawScope = this,
                                    width = arrowDoubleWidth,
                                    height = arrowDoubleHeight,
                                    gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (horizontalScrollState.value < horizontalScrollState.maxValue),
        action = {
            scope.launch {
                horizontalScrollState.scrollTo(
                    (horizontalScrollState.value + scrollAmountPx.toInt()).coerceAtMost(horizontalScrollState.maxValue)
                )
            }
        })

    Layout(modifier = modifier.fillMaxWidth().height(height = height),
        content = {
            // Leftwards scroller
            CommandButtonProjection(
                contentModel = leftScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = CommandButtonPresentationModel.Overlay(
                        sides = Sides(straightSides = hashSetOf(Side.Trailing))
                    )
                )
            ).project()

            Box(modifier = Modifier.horizontalScroll(horizontalScrollState)) {
                Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                    content()
                }
            }

            // Rightwards scroller
            CommandButtonProjection(
                contentModel = rightScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = CommandButtonPresentationModel.Overlay(
                        sides = Sides(straightSides = hashSetOf(Side.Leading))
                    )
                )
            ).project()

        },
        measurePolicy = { measurables, constraints ->
            val leftScrollerMeasurable = measurables[0]
            val contentMeasurable = measurables[1]
            val rightScrollerMeasurable = measurables[2]

            // How big is the left scroller?
            val leftScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    leftScrollerCommand,
                    scrollerPresentationModel
                )
            val leftScrollerSize = scrollerLayoutManager.getPreferredSize(
                leftScrollerCommand, scrollerPresentationModel, leftScrollerPreLayoutInfo
            )

            // How big is the right scroller?
            val rightScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    rightScrollerCommand,
                    scrollerPresentationModel
                )
            val rightScrollerSize = scrollerLayoutManager.getPreferredSize(
                rightScrollerCommand, scrollerPresentationModel, rightScrollerPreLayoutInfo
            )

            // How much space does the scrollable content need?
            val contentRequiredWidth = contentWidth.invoke()

            // Do we need to show the scrollers? If available width from constraints is enough
            // to fully display all scrollable content, we don't need to show the scrollers.
            val needScrollers = (contentRequiredWidth > constraints.maxWidth)
            val contentWidth = if (needScrollers) constraints.maxWidth -
                    leftScrollerSize.width - rightScrollerSize.width
            else constraints.maxWidth

            val contentHeightPx = height.toPx().roundToInt()
            val leftScrollerPlaceable = leftScrollerMeasurable.measure(
                Constraints.fixed(
                    width = if (needScrollers) leftScrollerSize.width.toInt() else 0,
                    height = contentHeightPx
                )
            )
            val rightScrollerPlaceable = rightScrollerMeasurable.measure(
                Constraints.fixed(
                    width = if (needScrollers) rightScrollerSize.width.toInt() else 0,
                    height = contentHeightPx
                )
            )
            val contentPlaceable = contentMeasurable.measure(
                Constraints.fixed(contentWidth.toInt(), contentHeightPx)
            )

            layout(width = constraints.maxWidth, height = contentHeightPx) {
                if (leftScrollerPlaceable.width > 0) {
                    leftScrollerPlaceable.placeRelative(0, 0)
                }
                if (rightScrollerPlaceable.width > 0) {
                    rightScrollerPlaceable.placeRelative(
                        constraints.maxWidth - rightScrollerPlaceable.width,
                        0
                    )
                }
                contentPlaceable.placeRelative(leftScrollerPlaceable.width, 0)
            }
        })
}

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraVerticallyScrollableBox(
    modifier: Modifier,
    width: Dp,
    contentHeight: () -> Int,
    verticalScrollState: ScrollState,
    scrollAmount: Dp = ScrollableBoxConstants.DefaultScrollAmount,
    content: @Composable () -> Unit
) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    // Presentation model for the scroller buttons. Note usage of Original icon filter strategy
    // since we're using TransitionAwarePainterDelegate to draw the double arrows, and
    // OnRollover action fire trigger for interacting with the scroller buttons.
    val scrollerPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Small,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        iconActiveFilterStrategy = IconFilterStrategy.Original,
        iconEnabledFilterStrategy = IconFilterStrategy.Original,
        iconDisabledFilterStrategy = IconFilterStrategy.Original,
        contentPadding = PaddingValues(0.dp),
        actionFireTrigger = ActionFireTrigger.OnRollover,
        autoRepeatAction = true,
        autoRepeatInitialInterval = 200L,
        autoRepeatSubsequentInterval = 50L
    )
    val scrollerLayoutManager = scrollerPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val scope = rememberCoroutineScope()
    val scrollAmountPx = scrollAmount.value * density.density

    val topScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowDoubleWidth =
                                ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                        ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                            val arrowDoubleHeight =
                                ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                            val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawDoubleArrow(
                                    drawScope = this,
                                    width = arrowDoubleWidth,
                                    height = arrowDoubleHeight,
                                    gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (verticalScrollState.value > 0),
        action = {
            scope.launch {
                verticalScrollState.scrollTo((verticalScrollState.value - scrollAmountPx.toInt()).coerceAtLeast(0))
            }
        })
    val bottomScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowDoubleWidth =
                                ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                        ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                            val arrowDoubleHeight =
                                ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                            val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawDoubleArrow(
                                    drawScope = this,
                                    width = arrowDoubleWidth,
                                    height = arrowDoubleHeight,
                                    gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (verticalScrollState.value < verticalScrollState.maxValue),
        action = {
            scope.launch {
                verticalScrollState.scrollTo(
                    (verticalScrollState.value + scrollAmountPx.toInt()).coerceAtMost(verticalScrollState.maxValue)
                )
            }
        })

    Layout(modifier = modifier.fillMaxHeight().width(width = width),
        content = {
            // Upwards scroller
            CommandButtonProjection(
                contentModel = topScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = CommandButtonPresentationModel.Overlay(
                        sides = Sides(straightSides = hashSetOf(Side.Bottom))
                    )
                )
            ).project()

            Box(modifier = Modifier.verticalScroll(verticalScrollState)) {
                Column(modifier = Modifier.fillMaxHeight().wrapContentWidth()) {
                    content()
                }
            }

            // Downwards scroller
            CommandButtonProjection(
                contentModel = bottomScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = CommandButtonPresentationModel.Overlay(
                        sides = Sides(straightSides = hashSetOf(Side.Top))
                    )
                )
            ).project()

        },
        measurePolicy = { measurables, constraints ->
            val topScrollerMeasurable = measurables[0]
            val contentMeasurable = measurables[1]
            val bottomScrollerMeasurable = measurables[2]

            // How big is the top scroller?
            val topScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    topScrollerCommand,
                    scrollerPresentationModel
                )
            val topScrollerSize = scrollerLayoutManager.getPreferredSize(
                topScrollerCommand, scrollerPresentationModel, topScrollerPreLayoutInfo
            )

            // How big is the bottom scroller?
            val bottomScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    bottomScrollerCommand,
                    scrollerPresentationModel
                )
            val bottomScrollerSize = scrollerLayoutManager.getPreferredSize(
                bottomScrollerCommand, scrollerPresentationModel, bottomScrollerPreLayoutInfo
            )

            // How much space does the scrollable content need?
            val contentRequiredHeight = contentHeight.invoke()

            // Do we need to show the scrollers? If available height from constraints is enough
            // to fully display all scrollable content, we don't need to show the scrollers.
            val needScrollers = (contentRequiredHeight > constraints.maxHeight)
            val contentHeight = if (needScrollers) constraints.maxHeight -
                    topScrollerSize.height - bottomScrollerSize.height
            else constraints.maxWidth

            val contentWidthPx = width.toPx().roundToInt()
            val topScrollerPlaceable = topScrollerMeasurable.measure(
                Constraints.fixed(
                    width = contentWidthPx,
                    height = if (needScrollers) topScrollerSize.height.toInt() else 0
                )
            )
            val bottomScrollerPlaceable = bottomScrollerMeasurable.measure(
                Constraints.fixed(
                    width = contentWidthPx,
                    height = if (needScrollers) bottomScrollerSize.height.toInt() else 0
                )
            )
            val contentPlaceable = contentMeasurable.measure(
                Constraints.fixed(contentWidthPx, contentHeight.toInt())
            )

            layout(width = contentWidthPx, height = constraints.maxHeight) {
                if (topScrollerPlaceable.height > 0) {
                    topScrollerPlaceable.placeRelative(0, 0)
                }
                if (bottomScrollerPlaceable.height > 0) {
                    bottomScrollerPlaceable.placeRelative(
                        0,
                        constraints.maxHeight - bottomScrollerPlaceable.height
                    )
                }
                contentPlaceable.placeRelative(0, topScrollerPlaceable.height)
            }
        })
}
