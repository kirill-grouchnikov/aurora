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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
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

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraBreadcrumbBar(
    contentModel: BreadcrumbBarContentModel,
    presentationModel: BreadcrumbBarPresentationModel = BreadcrumbBarPresentationModel(),
    horizontalScrollState: ScrollState = rememberScrollState(0),
    modifier: Modifier
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
        autoRepeatInitialInterval = 250L,
        autoRepeatSubsequentInterval = 100L
    )
    val scrollerLayoutManager = scrollerPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    // Presentation model for the content - copy fields from the breadcrumb bar presentation model
    val contentPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.presentationState,
        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
        iconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
        iconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
        popupMenuPresentationModel = CommandPopupMenuPresentationModel(
            itemIconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
            itemIconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
            itemIconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
            maxVisibleItems = presentationModel.maxVisibleChoiceCommands
        )
    )
    val contentLayoutManager = contentPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val scope = rememberCoroutineScope()
    val scrollAmount = 12.dp.value * density.density

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
                horizontalScrollState.scrollTo(
                    (horizontalScrollState.value - scrollAmount.toInt()).coerceAtLeast(0)
                )
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
                    (horizontalScrollState.value + scrollAmount.toInt()).coerceAtMost(
                        horizontalScrollState.maxValue
                    )
                )
            }
        })

    Layout(modifier = modifier.fillMaxWidth(),
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
                    for (command in contentModel.commands) {
                        val popupInteractionSource = remember(command) { MutableInteractionSource() }
                        val isPopupRollover by popupInteractionSource.collectIsHoveredAsState()

                        // Project a command button for each command
                        CommandButtonProjection(
                            contentModel = command,
                            presentationModel = contentPresentationModel.overlayWith(
                                CommandButtonPresentationModel.Overlay(
                                    popupPlacementStrategy = if (isPopupRollover) PopupPlacementStrategy.Downward.HAlignStart
                                    else PopupPlacementStrategy.Endward.VAlignTop
                                )
                            ),
                            overlays = mapOf()
                        ).project(
                            modifier = Modifier.fillMaxWidth(),
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = popupInteractionSource
                        )
                    }
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
            var boxRequiredWidth = 0.0f
            var boxHeight = 0
            if (contentModel.commands.isNotEmpty()) {
                for (command in contentModel.commands) {
                    val commandPreLayoutInfo =
                        contentLayoutManager.getPreLayoutInfo(
                            command,
                            contentPresentationModel
                        )
                    val commandSize = contentLayoutManager.getPreferredSize(
                        command, contentPresentationModel, commandPreLayoutInfo
                    )
                    boxRequiredWidth += commandSize.width
                    boxHeight = commandSize.height.toInt()
                }
            } else {
                val forSizing = Command(text = "sample", action = {})
                val commandPreLayoutInfo =
                    contentLayoutManager.getPreLayoutInfo(
                        forSizing,
                        contentPresentationModel
                    )
                boxHeight = contentLayoutManager.getPreferredSize(
                    forSizing, contentPresentationModel, commandPreLayoutInfo
                ).height.toInt()
            }

            // Do we need to show the scrollers? If available width from constraints is enough
            // to fully display all scrollable content, we don't need to show the scrollers.
            val needScrollers = (boxRequiredWidth > constraints.maxWidth)
            val contentWidth = if (needScrollers) constraints.maxWidth -
                    leftScrollerSize.width - rightScrollerSize.width
            else constraints.maxWidth

            val leftScrollerPlaceable = leftScrollerMeasurable.measure(
                Constraints.fixed(
                    width = if (needScrollers) leftScrollerSize.width.toInt() else 0,
                    height = boxHeight
                )
            )
            val rightScrollerPlaceable = rightScrollerMeasurable.measure(
                Constraints.fixed(
                    width = if (needScrollers) rightScrollerSize.width.toInt() else 0,
                    height = boxHeight
                )
            )
            val contentPlaceable = contentMeasurable.measure(
                Constraints.fixed(contentWidth.toInt(), boxHeight)
            )

            layout(width = constraints.maxWidth, height = boxHeight) {
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
