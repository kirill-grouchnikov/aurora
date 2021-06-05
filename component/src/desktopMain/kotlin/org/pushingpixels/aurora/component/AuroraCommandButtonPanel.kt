/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import org.pushingpixels.aurora.LocalTextStyle
import org.pushingpixels.aurora.Sides
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.LabelProjection
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

@Composable
internal fun AuroraCommandButtonPanel(
    modifier: Modifier = Modifier,
    contentModel: CommandPanelContentModel,
    presentationModel: CommandPanelPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay> = mapOf()
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current

    val layoutManager = presentationModel.commandPresentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = textStyle,
        resourceLoader = resourceLoader
    )

    val baseCommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = presentationModel.commandPresentationState,
            iconDimension = presentationModel.commandIconSize,
            isMenu = presentationModel.isMenu,
            horizontalAlignment = presentationModel.commandHorizontalAlignment,
            popupPlacementStrategy = presentationModel.popupPlacementStrategy,
            iconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
            iconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
            iconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy
        )

    // Compute pre-layout info for each command button
    val commandPresentationModels =
        mutableMapOf<Command, CommandButtonPresentationModel>()
    val preLayoutInfos =
        mutableMapOf<Command, CommandButtonLayoutManager.CommandButtonPreLayoutInfo>()
    val preferredSizes = mutableMapOf<Command, Size>()
    Layout(content = {
        val commandPreviewListener = contentModel.commandActionPreview
        for (groupModel in contentModel.commandGroups) {
            if (presentationModel.showGroupLabels && (groupModel.title != null)) {
                // The title of the current command group
                LabelProjection(contentModel = LabelContentModel(text = groupModel.title)).project()
            }

            for (command in groupModel.commands) {
                // Apply overlay if we have one registered for the current command
                val commandPresentation = if (overlays.containsKey(command))
                    baseCommandButtonPresentationModel.overlayWith(overlays[command]!!)
                else
                    baseCommandButtonPresentationModel

                // Propagate command overlays so that key tips are properly displayed
                // on secondary content of the current command's projection
                AuroraCommandButton(
                    command = command,
                    parentWindow = null,
                    extraActionPreview = commandPreviewListener,
                    presentationModel = commandPresentation,
                    overlays = overlays,
                    buttonSides = Sides()
                )

                // Cache the combined presentation model
                commandPresentationModels[command] = commandPresentation

                // Cache pre-layout info
                preLayoutInfos[command] =
                    layoutManager.getPreLayoutInfo(command, commandPresentation)

                // Cache preferred size
                preferredSizes[command] = layoutManager.getPreferredSize(
                    command,
                    commandPresentation,
                    preLayoutInfos[command]!!
                )
            }
        }
    }) { measurables, constraints ->
        // Our grid is uniform. The buttons will have the same width and height. Start
        // by computing the max preferred width / height across all the buttons.
        var maxButtonWidth = 0
        var maxButtonHeight = 0
        for (groupModel in contentModel.commandGroups) {
            for (command in groupModel.commands) {
                val preferredSize = preferredSizes[command]!!
                maxButtonWidth = max(maxButtonWidth, preferredSize.width.toInt())
                maxButtonHeight = max(maxButtonHeight, preferredSize.height.toInt())
            }
        }

        val gap = CommandPanelSizingConstants.DefaultGap.roundToPx()
        val panelWidth = if (constraints.hasFixedWidth || constraints.hasBoundedWidth) {
            constraints.maxWidth
        } else {
            maxButtonWidth * presentationModel.maxColumns +
                    gap * (presentationModel.maxColumns - 1)
        }

        var actualColumnCount = min((panelWidth + gap) / (maxButtonWidth + gap),
            presentationModel.maxColumns)
        if (actualColumnCount == 0) {
            actualColumnCount = 1
        }

        val actualButtonWidth = (panelWidth - gap * (actualColumnCount - 1)) / actualColumnCount

        var panelHeight = 0
        val placeables = arrayListOf<Placeable>()
        // Go over all the placeables, measure the titles and combine their heights
        var currMeasurableIndex = 0
        for (groupModel in contentModel.commandGroups) {
            if (presentationModel.showGroupLabels && (groupModel.title != null)) {
                // Measure the title of the current command group
                val titlePlaceable =
                    measurables[currMeasurableIndex++].measure(Constraints.fixedWidth(panelWidth))
                // Add to overall panel height
                panelHeight += titlePlaceable.height
                placeables.add(titlePlaceable)
            }
            // How many button rows does this command group need?
            val buttonRows =
                ceil((groupModel.commands.size.toFloat()) / actualColumnCount).toInt()
            // Add to overall panel height, including gaps between the rows
            panelHeight += buttonRows * maxButtonHeight + (buttonRows - 1) * gap
            // And also measure all the buttons
            for (command in groupModel.commands) {
                val commandButtonPlaceable = measurables[currMeasurableIndex++].measure(
                    Constraints.fixed(
                        width = actualButtonWidth, height = maxButtonHeight
                    )
                )
                placeables.add(commandButtonPlaceable)
            }
        }

        if (presentationModel.layoutFill == PanelLayoutFill.RowFill) {
            layout(width = panelWidth, height = panelHeight) {
                var currPlaceableIndex = 0
                var currX = 0
                var currY = 0
                // TODO - support RTL
                for (groupModel in contentModel.commandGroups) {
                    currX = 0
                    if (presentationModel.showGroupLabels && (groupModel.title != null)) {
                        // The current command group has a title
                        val currTitlePlaceable = placeables[currPlaceableIndex++]
                        currTitlePlaceable.place(currX, currY)
                        currY += currTitlePlaceable.height
                    }
                    // And also measure all the buttons
                    for ((index, _) in groupModel.commands.withIndex()) {
                        val commandButtonPlaceable = placeables[currPlaceableIndex++]
                        commandButtonPlaceable.place(currX, currY)
                        currX += (actualButtonWidth + gap)
                        if (currX >= panelWidth) {
                            // No more horizontal space in this row
                            currX = 0
                            currY += maxButtonHeight
                            if (index < (groupModel.commands.size - 1)) {
                                // This is not the last row
                                currY += gap
                            }
                        } else {
                            if (index == (groupModel.commands.size - 1)) {
                                // Partially filled last row
                                currY += maxButtonHeight
                            }
                        }
                    }
                }
            }
        } else {
            layout(width = 200, height = 200) {

            }
        }
    }
}
