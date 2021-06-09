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

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.LocalTextStyle
import org.pushingpixels.aurora.Sides
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.LabelProjection
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * Panel composable that hosts command buttons. Provides support for button groups,
 * same icon state / dimension and column-fill / row-fill layout. Under [RowFill]
 * layout mode, the buttons are laid out in rows, never exceeding the available
 * horizontal space. A vertical scroll bar will kick in once there is not enough
 * vertical space to show all the buttons. The schematic below shows a row-fill command
 * button panel:
 * </p>
 *
 * <pre>
 * +-----------------------------+-+
 * |                             |o|
 * | +----+ +----+ +----+ +----+ |o|
 * | | 01 | | 02 | | 03 | | 04 | |o|
 * | +----+ +----+ +----+ +----+ |o|
 * |                             | |
 * | +----+ +----+ +----+ +----+ | |
 * | | 05 | | 06 | | 07 | | 07 | | |
 * | +----+ +----+ +----+ +----+ | |
 * |                             | |
 * | +----+ +----+ +----+ +----+ | |
 * | | 09 | | 10 | | 11 | | 12 | | |
 * | +----+ +----+ +----+ +----+ | |
 * |                             | |
 * | +----+ +----+ +----+ +----+ | |
 * | | 13 | | 14 | | 15 | | 16 | | |
 * +-----------------------------+-+
 * </pre>
 *
 * <p>
 * Each row hosts four buttons, and the vertical scroll bar allows scrolling the
 * content up and down.
 * </p>
 *
 * <p>
 * Under the [ColumnFill] layout mode, the buttons are laid
 * out in columns, never exceeding the available vertical space. A horizontal scroll
 * bar will kick in once there is not enough horizontal space to show all the
 * buttons. The schematic below shows a column-fill command button panel:
 * </p>
 *
 * <pre>
 * +---------------------------------+
 * |                                 |
 * | +----+ +----+ +----+ +----+ +---|
 * | | 01 | | 04 | | 07 | | 10 | | 13|
 * | +----+ +----+ +----+ +----+ +---|
 * |                                 |
 * | +----+ +----+ +----+ +----+ +---|
 * | | 02 | | 05 | | 08 | | 11 | | 14|
 * | +----+ +----+ +----+ +----+ +---|
 * |                                 |
 * | +----+ +----+ +----+ +----+ +---|
 * | | 03 | | 06 | | 09 | | 12 | | 15|
 * | +----+ +----+ +----+ +----+ +---|
 * |                                 |
 * +---------------------------------+
 * |oooo                             |
 * +---------------------------------+
 * </pre>
 *
 * <p>
 * Each column hosts three buttons, and the horizontal scroll bar allows
 * scrolling the content left and right.
 * </p>
 */
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

    Box {
        val stateVertical = rememberScrollState(0)
        val stateHorizontal = rememberScrollState(0)

        val preferredSizes = mutableMapOf<Command, Size>()

        val extraEndPadding = if (presentationModel.layoutFillMode == PanelLayoutFillMode.RowFill)
            ScrollBarSizingConstants.DefaultScrollBarSize else 0.dp
        val extraBottomPadding =
            if (presentationModel.layoutFillMode == PanelLayoutFillMode.ColumnFill)
                ScrollBarSizingConstants.DefaultScrollBarSize else 0.dp
        val contentStartPadding =
            presentationModel.contentPadding.calculateStartPadding(layoutDirection)
        val contentEndPadding =
            presentationModel.contentPadding.calculateEndPadding(layoutDirection)
        val contentTopPadding = presentationModel.contentPadding.calculateTopPadding()
        val contentBottomPadding = presentationModel.contentPadding.calculateBottomPadding()
        var topLevelModifier = modifier.padding(
            start = contentStartPadding,
            end = contentEndPadding + extraEndPadding,
            top = contentTopPadding,
            bottom = contentBottomPadding + extraBottomPadding
        )
        topLevelModifier = when (presentationModel.layoutFillMode) {
            PanelLayoutFillMode.RowFill -> topLevelModifier.verticalScroll(stateVertical)
            PanelLayoutFillMode.ColumnFill -> topLevelModifier.horizontalScroll(stateHorizontal)
        }
        Layout(
            modifier = topLevelModifier,
            content = {
                val commandPreviewListener = contentModel.commandActionPreview
                for (groupModel in contentModel.commandGroups) {
                    if (presentationModel.showGroupLabels && (groupModel.title != null)) {
                        // The title of the current command group
                        // TODO - add horizontal separators and background fill
                        LabelProjection(
                            contentModel = LabelContentModel(text = groupModel.title),
                            presentationModel = LabelPresentationModel(
                                horizontalAlignment = HorizontalAlignment.Leading
                            )
                        ).project()
                    }

                    // TODO - add even-odd background fill
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

                        val preLayoutInfo =
                            layoutManager.getPreLayoutInfo(command, commandPresentation)

                        // Cache preferred size
                        preferredSizes[command] = layoutManager.getPreferredSize(
                            command = command,
                            presentationModel = commandPresentation,
                            preLayoutInfo = preLayoutInfo
                        )
                    }
                }
            },
            measurePolicy = when (presentationModel.layoutFillMode) {
                PanelLayoutFillMode.RowFill ->
                    getRowFillMeasurePolicy(contentModel, presentationModel, preferredSizes)
                PanelLayoutFillMode.ColumnFill ->
                    getColumnFillMeasurePolicy(contentModel, presentationModel, preferredSizes)
            }
        )
        if (presentationModel.layoutFillMode == PanelLayoutFillMode.RowFill) {
            AuroraVerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().padding(
                    start = 0.dp,
                    end = contentEndPadding + ScrollBarSizingConstants.DefaultScrollBarMargin,
                    top = contentTopPadding,
                    bottom = contentBottomPadding
                ),
                adapter = rememberScrollbarAdapter(stateVertical)
            )
        } else {
            AuroraHorizontalScrollbar(
                modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                    .padding(
                        start = contentStartPadding,
                        end = contentEndPadding,
                        top = 0.dp,
                        bottom = contentBottomPadding + ScrollBarSizingConstants.DefaultScrollBarMargin
                    ),
                adapter = rememberScrollbarAdapter(stateHorizontal)
            )
        }
    }
}

private fun getRowFillMeasurePolicy(
    contentModel: CommandPanelContentModel,
    presentationModel: CommandPanelPresentationModel,
    preferredSizes: Map<Command, Size>
): MeasurePolicy {
    return MeasurePolicy { measurables, constraints ->
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

        var actualColumnCount = min(
            (panelWidth + gap) / (maxButtonWidth + gap),
            presentationModel.maxColumns
        )
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

            // Measure all the buttons
            for (command in groupModel.commands) {
                val commandButtonPlaceable = measurables[currMeasurableIndex++].measure(
                    Constraints.fixed(
                        width = actualButtonWidth, height = maxButtonHeight
                    )
                )
                placeables.add(commandButtonPlaceable)
            }
        }

        // And account for gaps between groups
        panelHeight += (contentModel.commandGroups.size - 1) * gap

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
                // And place all the buttons
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
                currY += gap
            }
        }
    }
}

private fun getColumnFillMeasurePolicy(
    contentModel: CommandPanelContentModel,
    presentationModel: CommandPanelPresentationModel,
    preferredSizes: Map<Command, Size>
): MeasurePolicy {
    return MeasurePolicy { measurables, constraints ->
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
        val panelHeight = if (constraints.hasFixedHeight || constraints.hasBoundedHeight) {
            constraints.maxHeight
        } else {
            maxButtonHeight * presentationModel.maxRows +
                    gap * (presentationModel.maxRows - 1)
        }

        var actualRowCount = min(
            (panelHeight + gap) / (maxButtonHeight + gap),
            presentationModel.maxRows
        )
        if (actualRowCount == 0) {
            actualRowCount = 1
        }

        val actualButtonHeight = (panelHeight - gap * (actualRowCount - 1)) / actualRowCount

        var panelWidth = 0
        val placeables = arrayListOf<Placeable>()
        // Go over all the placeables and combine their heights
        var currMeasurableIndex = 0
        for (groupModel in contentModel.commandGroups) {
            // How many button columns does this command group need?
            val buttonColumns =
                ceil((groupModel.commands.size.toFloat()) / actualRowCount).toInt()
            // Add to overall panel width, including gaps between the columns
            panelWidth += buttonColumns * maxButtonWidth + (buttonColumns - 1) * gap

            // Measure all the buttons
            for (command in groupModel.commands) {
                val commandButtonPlaceable = measurables[currMeasurableIndex++].measure(
                    Constraints.fixed(
                        width = maxButtonWidth, height = actualButtonHeight
                    )
                )
                placeables.add(commandButtonPlaceable)
            }
        }
        // And account for gaps between groups
        panelWidth += (contentModel.commandGroups.size - 1) * gap

        layout(width = panelWidth, height = panelHeight) {
            var currPlaceableIndex = 0
            var currX = 0
            var currY = 0
            // TODO - support RTL
            for (groupModel in contentModel.commandGroups) {
                currY = 0
                // And place all the buttons
                for ((index, _) in groupModel.commands.withIndex()) {
                    val commandButtonPlaceable = placeables[currPlaceableIndex++]
                    commandButtonPlaceable.place(currX, currY)
                    currY += (actualButtonHeight + gap)
                    if (currY >= panelHeight) {
                        // No more vertical space in this column
                        currY = 0
                        currX += maxButtonWidth
                        if (index < (groupModel.commands.size - 1)) {
                            // This is not the last column
                            currX += gap
                        }
                    } else {
                        if (index == (groupModel.commands.size - 1)) {
                            // Partially filled last column
                            currX += (maxButtonWidth + gap)
                        }
                    }
                }
            }
        }
    }
}

internal fun getPreferredCommandButtonPanelSize(
    contentModel: CommandPanelContentModel,
    presentationModel: CommandPanelPresentationModel,
    buttonLayoutManager: CommandButtonLayoutManager,
    layoutDirection: LayoutDirection,
    density: Density
): Size {
    // Our grid is uniform. The buttons will have the same width and height. Start
    // by computing the max preferred width / height across all the buttons.
    var maxButtonWidth = 0
    var maxButtonHeight = 0

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

    for (groupModel in contentModel.commandGroups) {
        for (command in groupModel.commands) {
            val preLayoutInfo =
                buttonLayoutManager.getPreLayoutInfo(command, baseCommandButtonPresentationModel)

            // Compute the preferred size of this button
            val preferredSize = buttonLayoutManager.getPreferredSize(
                command = command,
                presentationModel = baseCommandButtonPresentationModel,
                preLayoutInfo = preLayoutInfo
            )
            maxButtonWidth = max(maxButtonWidth, preferredSize.width.toInt())
            maxButtonHeight = max(maxButtonHeight, preferredSize.height.toInt())
        }
    }

    val gap = (CommandPanelSizingConstants.DefaultGap.value * density.density)
    var panelWidth = maxButtonWidth * presentationModel.maxColumns +
            gap * (presentationModel.maxColumns - 1)
    var panelHeight = maxButtonHeight * presentationModel.maxRows +
            gap * (presentationModel.maxColumns - 1)

    // Account for content padding
    panelWidth += (presentationModel.contentPadding.calculateStartPadding(layoutDirection) +
            presentationModel.contentPadding.calculateEndPadding(layoutDirection)).value * density.density
    panelHeight += (presentationModel.contentPadding.calculateTopPadding() +
            presentationModel.contentPadding.calculateBottomPadding()).value * density.density

    // Account for scroll bar. For now the assumption is that it's always showing
    if (presentationModel.layoutFillMode == PanelLayoutFillMode.RowFill) {
        panelWidth += ScrollBarSizingConstants.DefaultScrollBarSize.value * density.density
    } else {
        panelHeight += ScrollBarSizingConstants.DefaultScrollBarSize.value * density.density
    }

    return Size(panelWidth, panelHeight)
}
