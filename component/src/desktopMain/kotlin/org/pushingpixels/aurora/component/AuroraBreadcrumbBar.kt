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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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
fun <T> AuroraBreadcrumbBar(
    contentProvider: BreadcrumbBarContentProvider<T>,
    presentationModel: BreadcrumbBarPresentationModel = BreadcrumbBarPresentationModel(),
    modifier: Modifier
) {
    val initialized = remember { mutableStateOf(false) }

    // The currently shown path of breadcrumb items.
    val shownPath: MutableList<BreadcrumbItem<T>> = remember { mutableStateListOf() }
    // For each item in "shownPath" this has the list of path choices - to be displayed
    // in the popup for that particular item. The first item in this list has path choices
    // for the root.
    val shownPathChoices: MutableList<List<BreadcrumbItem<T>>> = remember { mutableStateListOf() }

    val contentModel = remember { BreadcrumbBarContentModel(shownPath) }
    if (!initialized.value) {
        LaunchedEffect(null) {
            coroutineScope {
                val rootPathChoices = contentProvider.getPathChoices(emptyList())
                shownPathChoices.add(rootPathChoices)
            }
            initialized.value = true
        }
    }

    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current
    val window = LocalWindow.current

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
        resourceLoader = resourceLoader
    )

    // Presentation model for the content - copy fields from the breadcrumb bar presentation model
    val contentPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.presentationState,
        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
        iconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
        iconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
        popupMenuPresentationModel = CommandPopupMenuPresentationModel(
            menuIconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
            menuIconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
            menuIconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
            maxVisibleMenuCommands = presentationModel.maxVisibleChoiceCommands
        )
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

    val commands = derivedStateOf {
        val rootChoices = if (shownPathChoices.isNotEmpty()) shownPathChoices[0] else null
        val rootSecondaryContentModel = rootChoices?.let {
            if (it.isEmpty()) null else
                CommandMenuContentModel(
                    group = CommandGroup(
                        title = "",
                        commands = it.map { rootChoice ->
                            Command(text = rootChoice.displayName,
                                icon = rootChoice.icon,
                                action = {
                                    shownPath.clear()
                                    shownPath.add(rootChoice)
                                    scope.launch {
                                        while (shownPathChoices.size > 1) {
                                            shownPathChoices.removeLast()
                                        }
                                        val newPathChoices =
                                            contentProvider.getPathChoices(shownPath)
                                        shownPathChoices.add(newPathChoices)
                                    }
                                })
                        }
                    )
                )
        }

        listOf(
            Command(
                text = "",
                icon = null,
                action = {
                    // Clear all entries in the shown path
                    shownPath.clear()
                },
                secondaryContentModel = rootSecondaryContentModel
            )
        ) + shownPath.mapIndexed { index, shownPathEntry ->
            // First entry in "shownPathChoices" is always root path choices
            val indexInShownPathChoices = index + 1
            val shownPathEntryChoices =
                if (indexInShownPathChoices <= (shownPathChoices.size - 1))
                    shownPathChoices[indexInShownPathChoices] else null
            val shownPathEntryMenuContentModel = shownPathEntryChoices?.let {
                if (it.isEmpty()) null else
                    CommandMenuContentModel(
                        group = CommandGroup(
                            title = "",
                            commands = it.map { entryChoice ->
                                Command(text = entryChoice.displayName,
                                    icon = entryChoice.icon,
                                    action = {
                                        // Clear all entries in shown path after the one that
                                        // corresponds to the command with these choices
                                        while (shownPath.size > indexInShownPathChoices) {
                                            shownPath.removeLast()
                                        }
                                        shownPath.add(entryChoice)

                                        // And load choices for this entry
                                        scope.launch {
                                            while (shownPathChoices.size > (indexInShownPathChoices + 1)) {
                                                shownPathChoices.removeLast()
                                            }
                                            val newPathChoices =
                                                contentProvider.getPathChoices(shownPath)
                                            shownPathChoices.add(newPathChoices)
                                            // Scroll to reveal the newly added content
                                            delay(150)
                                            stateHorizontal.animateScrollTo(stateHorizontal.maxValue)
                                        }
                                    })
                            }
                        )
                    )
            }
            Command(
                text = shownPathEntry.displayName,
                icon = shownPathEntry.icon,
                action = {
                    // Clear all entries in shown path after the one that
                    // corresponds to this command
                    while (shownPath.size > indexInShownPathChoices) {
                        shownPath.removeLast()
                    }
                },
                secondaryContentModel = shownPathEntryMenuContentModel
            )
        }
    }

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
                    for (command in commands.value) {
                        AuroraCommandButton(
                            modifier = Modifier,
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() },
                            command = command,
                            parentWindow = window,
                            extraAction = null,
                            popupPlacementStrategyProvider = { modelStateInfo ->
                                if (modelStateInfo.activeStrength > 0.0f) PopupPlacementStrategy.Downward
                                else PopupPlacementStrategy.Endward
                            },
                            presentationModel = contentPresentationModel,
                            overlays = mapOf()
                        )
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
            if (commands.value.isNotEmpty()) {
                for (command in commands.value) {
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
