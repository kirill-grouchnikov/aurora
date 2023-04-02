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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.AuroraHorizontallyScrollableBox
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import kotlin.math.max

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonPrimaryBar(ribbon: Ribbon) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val layoutGap = (TaskbarPrimaryBarGap.value * density.density).toInt()

    // Primary bar content is:
    //
    // AppMenuButton | Scrollable row of task toggle buttons | Anchored commands
    //
    // If the entire content fits withing the available horizontal space, anchored commands are projected
    // with their original presentation states. Otherwise, all anchored commands are projected with the
    // small presentation state, and horizontal space left after app menu button and anchored commands
    // is given to the task toggle buttons.
    //
    // We use SubcomposeLayout to implement the presentation overlay logic of anchored commands during
    // composition / projection.

    // Pre-compute preferred sizes of all elements

    // Start with the application menu button (optional)
    var applicationMenuButtonPreferredSize: Size? = null
    if (ribbon.applicationMenuCommandButtonProjection != null) {
        val applicationMenuCommandButtonLayoutManager =
            ribbon.applicationMenuCommandButtonProjection!!.presentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                fontFamilyResolver = fontFamilyResolver
            )
        applicationMenuButtonPreferredSize = applicationMenuCommandButtonLayoutManager.getPreferredSize(
            command = ribbon.applicationMenuCommandButtonProjection!!.contentModel,
            presentationModel = ribbon.applicationMenuCommandButtonProjection!!.presentationModel,
            preLayoutInfo = applicationMenuCommandButtonLayoutManager.getPreLayoutInfo(
                ribbon.applicationMenuCommandButtonProjection!!.contentModel,
                ribbon.applicationMenuCommandButtonProjection!!.presentationModel
            )
        )
    }

    // Next is toggle buttons for all the ribbon tasks
    val ribbonTaskCommands = ribbon.tasks.map { task ->
        Command(text = task.title,
            icon = null,
            isActionToggle = true,
            isActionToggleSelected = (task == ribbon.selectedTask),
            onTriggerActionToggleSelectedChange = {
                ribbon.onTaskClick(task)
            }
        )
    }

    var combinedTaskButtonsWidth = 0
    var maxTaskButtonHeight = 0

    val taskButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Medium,
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
        sides = Sides(openSides = hashSetOf(Side.Bottom)),
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
    )
    val taskButtonLayoutManager = taskButtonPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    for (ribbonTaskCommand in ribbonTaskCommands) {
        val currTaskButtonPreferredSize = taskButtonLayoutManager.getPreferredSize(
            command = ribbonTaskCommand,
            presentationModel = taskButtonPresentationModel,
            preLayoutInfo = taskButtonLayoutManager.getPreLayoutInfo(
                ribbonTaskCommand,
                taskButtonPresentationModel
            )
        )
        combinedTaskButtonsWidth += currTaskButtonPreferredSize.width.toInt()
        maxTaskButtonHeight = max(maxTaskButtonHeight, currTaskButtonPreferredSize.height.toInt())
    }
    if (ribbonTaskCommands.isNotEmpty()) {
        combinedTaskButtonsWidth += ((TaskbarPrimaryBarTaskButtonsGap.value * density.density) *
                (ribbonTaskCommands.size - 1)).toInt()
    }

    // And finally the anchored commands. We pre-compute their combined size twice, once at their original
    // presentation state, and once at the small state
    var combinedAnchoredCommandsOriginalWidth = 0
    var combinedAnchoredCommandsSmallWidth = 0
    var maxAnchoredCommandHeight = 0

    val anchoredCommandsOverlayOriginal = BaseCommandButtonPresentationModel.Overlay(
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd
    )
    val anchoredCommandsOverlaySmall = BaseCommandButtonPresentationModel.Overlay(
        presentationState = CommandButtonPresentationState.Small,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd
    )
    for (anchoredCommandProjection in ribbon.anchoredCommands) {
        val presentationModelWithOriginal =
            anchoredCommandProjection.presentationModel.overlayWith(anchoredCommandsOverlayOriginal)
        val presentationModelWithSmall =
            anchoredCommandProjection.presentationModel.overlayWith(anchoredCommandsOverlaySmall)
        val layoutManagerWithOriginal = presentationModelWithOriginal.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
        )
        val layoutManagerWithSmall = presentationModelWithSmall.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
        )
        val currAnchoredCommandOriginalPreferredSize = layoutManagerWithOriginal.getPreferredSize(
            command = anchoredCommandProjection.contentModel,
            presentationModel = presentationModelWithOriginal,
            preLayoutInfo = layoutManagerWithOriginal.getPreLayoutInfo(
                anchoredCommandProjection.contentModel,
                presentationModelWithOriginal
            )
        )
        val currAnchoredCommandSmallPreferredSize = layoutManagerWithSmall.getPreferredSize(
            command = anchoredCommandProjection.contentModel,
            presentationModel = presentationModelWithSmall,
            preLayoutInfo = layoutManagerWithSmall.getPreLayoutInfo(
                anchoredCommandProjection.contentModel,
                presentationModelWithSmall
            )
        )

        combinedAnchoredCommandsOriginalWidth += currAnchoredCommandOriginalPreferredSize.width.toInt()
        maxAnchoredCommandHeight =
            max(maxAnchoredCommandHeight, currAnchoredCommandOriginalPreferredSize.height.toInt())
        combinedAnchoredCommandsSmallWidth += currAnchoredCommandSmallPreferredSize.width.toInt()
        maxAnchoredCommandHeight = max(maxAnchoredCommandHeight, currAnchoredCommandSmallPreferredSize.height.toInt())
    }
    if (ribbon.anchoredCommands.isNotEmpty()) {
        val anchoredLayoutGap = (TaskbarPrimaryBarAnchoredCommandsGap.value * density.density).toInt()
        combinedAnchoredCommandsOriginalWidth += anchoredLayoutGap * (ribbon.anchoredCommands.size - 1)
        combinedAnchoredCommandsSmallWidth += anchoredLayoutGap * (ribbon.anchoredCommands.size - 1)
    }

    // Our height is the max of all three part heights
    var finalHeight = max(maxTaskButtonHeight, maxAnchoredCommandHeight)
    if (applicationMenuButtonPreferredSize != null) {
        finalHeight = max(finalHeight, applicationMenuButtonPreferredSize.height.toInt())
    }

    var fullWidthNeeded = 0
    if (applicationMenuButtonPreferredSize != null) {
        fullWidthNeeded = applicationMenuButtonPreferredSize.width.toInt()
    }
    if ((fullWidthNeeded > 0) && (combinedTaskButtonsWidth > 0)) {
        fullWidthNeeded += layoutGap
    }
    fullWidthNeeded += combinedTaskButtonsWidth
    if ((fullWidthNeeded > 0) && (combinedAnchoredCommandsOriginalWidth > 0)) {
        fullWidthNeeded += layoutGap
    }
    fullWidthNeeded += combinedAnchoredCommandsOriginalWidth

    val taskButtonRowScrollState: ScrollState = rememberScrollState(0)
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
        SubcomposeLayout(
            modifier = Modifier.auroraBackground()
                .fillMaxWidth()
                .padding(all = 4.dp)
                .height((finalHeight / density.density).dp)
        ) { constraints ->
            val widthAvailable = constraints.maxWidth
            val heightAvailable = constraints.maxHeight
            val everythingFits = (fullWidthNeeded <= widthAvailable)

            // Application menu command button (optional)
            val applicationMenuCommandButtonPlaceable =
                if (applicationMenuButtonPreferredSize != null) {
                    subcompose(0) {
                        ribbon.applicationMenuCommandButtonProjection?.project()
                    }.first().measure(
                        Constraints.fixed(
                            applicationMenuButtonPreferredSize.width.toInt(),
                            applicationMenuButtonPreferredSize.height.toInt()
                        )
                    )
                } else {
                    null
                }

            // Anchored commands
            val anchoredCommandsPlaceable = subcompose(1) {
                Row(horizontalArrangement = Arrangement.spacedBy(TaskbarPrimaryBarAnchoredCommandsGap)) {
                    for (anchored in ribbon.anchoredCommands) {
                        anchored.reproject(modifier = Modifier,
                            primaryOverlay = if (everythingFits) anchoredCommandsOverlayOriginal else anchoredCommandsOverlaySmall,
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() })
                    }
                }
            }.first().measure(
                Constraints.fixed(
                    if (everythingFits) combinedAnchoredCommandsOriginalWidth else combinedAnchoredCommandsSmallWidth,
                    maxAnchoredCommandHeight
                )
            )

            // Task toggle buttons
            val widthAvailableForTaskButtons = if (everythingFits) {
                combinedTaskButtonsWidth
            } else {
                var widthLeft = widthAvailable
                if (applicationMenuButtonPreferredSize != null) {
                    widthLeft -= (applicationMenuButtonPreferredSize.width.toInt() + layoutGap)
                }
                if (combinedAnchoredCommandsSmallWidth > 0) {
                    widthLeft -= (combinedAnchoredCommandsSmallWidth + layoutGap)
                }
                widthLeft
            }

            val taskButtonsPlaceable = subcompose(2) {
                AuroraHorizontallyScrollableBox(
                    modifier = Modifier.width(width = (widthAvailableForTaskButtons / density.density).dp),
                    height = (finalHeight / density.density).dp,
                    contentWidth = { combinedTaskButtonsWidth },
                    horizontalScrollState = taskButtonRowScrollState,
                    scrollAmount = 12.dp,
                    content = {
                        for ((index, taskCommand) in ribbonTaskCommands.withIndex()) {
                            if (index > 0) {
                                Spacer(modifier = Modifier.width(TaskbarPrimaryBarTaskButtonsGap))
                            }
                            CommandButtonProjection(
                                contentModel = taskCommand,
                                presentationModel = taskButtonPresentationModel
                            ).project()
                        }
                    }
                )
            }.first().measure(Constraints.fixed(
                widthAvailableForTaskButtons,
                maxTaskButtonHeight
            ))

            layout(widthAvailable, heightAvailable) {
                applicationMenuCommandButtonPlaceable?.placeRelative(
                    x = 0,
                    y = (heightAvailable - applicationMenuCommandButtonPlaceable.measuredHeight) / 2
                )

                val xForTaskButtons = if (applicationMenuCommandButtonPlaceable != null) {
                    applicationMenuCommandButtonPlaceable.measuredWidth + layoutGap
                } else {
                    0
                }
                taskButtonsPlaceable.placeRelative(
                    x = xForTaskButtons,
                    y = (heightAvailable - taskButtonsPlaceable.measuredHeight) / 2
                )

                val xForAnchoredCommands = widthAvailable - anchoredCommandsPlaceable.measuredWidth
                anchoredCommandsPlaceable.placeRelative(
                    x = xForAnchoredCommands,
                    y = (heightAvailable - anchoredCommandsPlaceable.measuredHeight) / 2
                )
            }
        }
    }
}

private val TaskbarPrimaryBarGap = 8.dp
private val TaskbarPrimaryBarTaskButtonsGap = 6.dp
private val TaskbarPrimaryBarAnchoredCommandsGap = 4.dp

