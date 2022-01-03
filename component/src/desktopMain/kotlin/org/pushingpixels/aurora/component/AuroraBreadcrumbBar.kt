/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
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
import org.pushingpixels.aurora.component.utils.TransitionAwarePainter
import org.pushingpixels.aurora.component.utils.TransitionAwarePainterDelegate
import org.pushingpixels.aurora.component.utils.drawArrow
import org.pushingpixels.aurora.theming.*

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraBreadcrumbBar(commands: List<Command>, modifier: Modifier) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val scrollerPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Small,
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
        resourceLoader = resourceLoader
    )

    val contentPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Medium,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
    )
    val contentLayoutManager = contentPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        resourceLoader = resourceLoader
    )

    val stateHorizontal = rememberScrollState(0)
    val scope = rememberCoroutineScope()
    val scrollAmount = 12.dp.value * density.density

    val leftScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ComboBoxSizingConstants.DefaultComboBoxArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowWidth =
                                ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx()
                            val arrowHeight =
                                ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowWidth) / 2
                            val dy = (iconSize.toPx() - arrowHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(decorationAreaType, modelStateInfoSnapshot.currModelState) else 1.0f
                            translate(left = dx, top = dy) {
                                drawArrow(
                                    drawScope = this,
                                    width = arrowWidth,
                                    height = arrowHeight,
                                    strokeWidth = 2.0.dp.toPx(),
                                    direction = PopupPlacementStrategy.Startward,
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
        isActionEnabled = (stateHorizontal.value > 0),
        action = {
            scope.launch {
                stateHorizontal.scrollTo(
                    (stateHorizontal.value - scrollAmount.toInt()).coerceAtLeast(0)
                )
            }
        })
    val rightScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ComboBoxSizingConstants.DefaultComboBoxArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowWidth =
                                ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx()
                            val arrowHeight =
                                ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowWidth) / 2
                            val dy = (iconSize.toPx() - arrowHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(decorationAreaType, modelStateInfoSnapshot.currModelState) else 1.0f
                            translate(left = dx, top = dy) {
                                drawArrow(
                                    drawScope = this,
                                    width = arrowWidth,
                                    height = arrowHeight,
                                    strokeWidth = 2.0.dp.toPx(),
                                    direction = PopupPlacementStrategy.Endward,
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
        isActionEnabled = (stateHorizontal.value < stateHorizontal.maxValue),
        action = {
            scope.launch {
                stateHorizontal.scrollTo(
                    (stateHorizontal.value + scrollAmount.toInt()).coerceAtMost(
                        stateHorizontal.maxValue
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
                        sides = Sides(
                            straightSides = hashSetOf(
                                if (layoutDirection == LayoutDirection.Ltr) Side.Right else Side.Left
                            )
                        )
                    )
                )
            ).project()

            Box(modifier = Modifier.horizontalScroll(stateHorizontal)) {
                Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                    for (command in commands) {
                        CommandButtonProjection(
                            contentModel = command,
                            presentationModel = contentPresentationModel
                        ).project()
                    }
                }
            }

            // Rightwards scroller
            CommandButtonProjection(
                contentModel = rightScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = CommandButtonPresentationModel.Overlay(
                        sides = Sides(
                            straightSides = hashSetOf(
                                if (layoutDirection == LayoutDirection.Ltr) Side.Left else Side.Right
                            )
                        )
                    )
                )
            ).project()

        },
        measurePolicy = { measurables, constraints ->
            val leftScrollerMeasurable = measurables[0]
            val contentMeasurable = measurables[1]
            val rightScrollerMeasurable = measurables[2]

            val leftScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    leftScrollerCommand,
                    scrollerPresentationModel
                )
            val leftScrollerSize = scrollerLayoutManager.getPreferredSize(
                leftScrollerCommand, scrollerPresentationModel, leftScrollerPreLayoutInfo
            )

            val rightScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    rightScrollerCommand,
                    scrollerPresentationModel
                )
            val rightScrollerSize = scrollerLayoutManager.getPreferredSize(
                rightScrollerCommand, scrollerPresentationModel, rightScrollerPreLayoutInfo
            )

            var boxRequiredWidth = 0.0f
            var boxHeight = 0
            for (command in commands) {
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
