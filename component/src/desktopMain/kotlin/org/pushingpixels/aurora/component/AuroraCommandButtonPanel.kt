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

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.utils.TitleLabel
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import kotlin.math.max

private fun LazyListScope.rowOfItems(
    backgroundColor: Color,
    gap: Dp,
    commandGroup: CommandGroup,
    indexRowStart: Int,
    indexRowEnd: Int,
    itemWidth: Dp,
    baseCommandButtonPresentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>
) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = gap, vertical = gap / 2.0f)
        ) {
            for (index in indexRowStart until indexRowEnd) {
                val command = commandGroup.commands[index]
                // Apply overlay if we have one registered for the current command
                val commandPresentation = if (overlays.containsKey(command))
                    baseCommandButtonPresentationModel.overlayWith(overlays[command]!!)
                else
                    baseCommandButtonPresentationModel

                // Propagate command overlays so that key tips are properly displayed
                // on secondary content of the current command's projection
                CommandButtonProjection(
                    contentModel = command,
                    presentationModel = commandPresentation,
                    secondaryOverlays = overlays
                ).project(
                    modifier = Modifier.width(itemWidth),
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() }
                )

                if (index != (indexRowEnd - 1)) {
                    Spacer(modifier = Modifier.width(gap))
                }
            }
        }
    }
}

private fun LazyListScope.columnOfItems(
    backgroundColor: Color,
    gap: Dp,
    commandGroup: CommandGroup,
    indexColumnStart: Int,
    indexColumnEnd: Int,
    itemHeight: Dp,
    baseCommandButtonPresentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>
) {
    item {
        Column(
            modifier = Modifier.fillMaxHeight()
                .background(backgroundColor)
                .padding(horizontal = gap / 2.0f, vertical = gap)
        ) {
            for (index in indexColumnStart until indexColumnEnd) {
                val command = commandGroup.commands[index]
                // Apply overlay if we have one registered for the current command
                val commandPresentation = if (overlays.containsKey(command))
                    baseCommandButtonPresentationModel.overlayWith(overlays[command]!!)
                else
                    baseCommandButtonPresentationModel

                // Propagate command overlays so that key tips are properly displayed
                // on secondary content of the current command's projection
                CommandButtonProjection(
                    contentModel = command,
                    presentationModel = commandPresentation,
                    secondaryOverlays = overlays
                ).project(
                    modifier = Modifier.height(itemHeight),
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() }
                )

                if (index != (indexColumnEnd - 1)) {
                    Spacer(modifier = Modifier.height(gap))
                }
            }
        }
    }
}

/**
 * Panel composable that hosts command buttons. Provides support for button groups,
 * same icon state / dimension and column-fill / row-fill layout. Under
 * [PanelLayoutSpec.RowFill] layout mode, the buttons are laid out in rows, never
 * exceeding the available horizontal space. A vertical scroll bar will kick in once
 * there is not enough vertical space to show all the buttons. The schematic below
 * shows a row-fill command button panel:
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
 * Under the [PanelLayoutSpec.ColumnFill] layout mode, the buttons are laid
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
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay> = mapOf()
) {
    val layoutDirection = LocalLayoutDirection.current

    val baseCommandButtonPresentationModel =
        CommandButtonPresentationModel(
            contentPadding = presentationModel.commandContentPadding,
            presentationState = presentationModel.commandPresentationState,
            iconDimension = presentationModel.commandIconDimension,
            backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
            textStyle = presentationModel.commandTextStyle,
            textOverflow = presentationModel.commandTextOverflow,
            horizontalAlignment = presentationModel.commandHorizontalAlignment,
            horizontalGapScaleFactor = presentationModel.commandHorizontalGapScaleFactor,
            verticalGapScaleFactor = presentationModel.commandVerticalGapScaleFactor,
            popupFireTrigger = presentationModel.commandPopupFireTrigger,
            selectedStateHighlight = presentationModel.commandSelectedStateHighlight,
            popupPlacementStrategy = presentationModel.popupPlacementStrategy,
            iconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
            iconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
            iconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
        )

    val extraEndPadding = if (presentationModel.layoutSpec is PanelLayoutSpec.RowFill)
        ScrollBarSizingConstants.DefaultScrollBarThickness + ScrollBarSizingConstants.DefaultScrollBarMargin else 0.dp
    val extraBottomPadding =
        if (presentationModel.layoutSpec is PanelLayoutSpec.ColumnFill)
            ScrollBarSizingConstants.DefaultScrollBarThickness + ScrollBarSizingConstants.DefaultScrollBarMargin else 0.dp
    val contentStartPadding =
        presentationModel.contentPadding.calculateStartPadding(layoutDirection)
    val contentEndPadding =
        presentationModel.contentPadding.calculateEndPadding(layoutDirection)
    val contentTopPadding = presentationModel.contentPadding.calculateTopPadding()
    val contentBottomPadding = presentationModel.contentPadding.calculateBottomPadding()
    val neutralColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
    val backgroundEvenGroups = neutralColorTokens.containerSurface
    val backgroundOddGroups = if (neutralColorTokens.isDark) {
        neutralColorTokens.containerSurfaceLow
    } else {
        neutralColorTokens.containerSurfaceHigh
    }

    SubcomposeLayout(modifier = modifier.fillMaxSize()) { constraints ->
        val gap = presentationModel.contentGap
        val gapPx = gap.roundToPx()

        val panelPlaceable: Placeable
        when (presentationModel.layoutSpec) {
            is PanelLayoutSpec.RowFill -> {
                val columnCount: Int = when (presentationModel.layoutSpec.rowFillSpec) {
                    is PanelRowFillSpec.Fixed -> presentationModel.layoutSpec.rowFillSpec.columnCount
                    is PanelRowFillSpec.Adaptive ->
                        (constraints.maxWidth - contentStartPadding.roundToPx()
                                - contentEndPadding.roundToPx() - gapPx) /
                                (presentationModel.layoutSpec.rowFillSpec.minColumnWidth.roundToPx() + gapPx)
                }
                val itemWidth = (constraints.maxWidth - contentStartPadding.roundToPx()
                        - contentEndPadding.roundToPx() - gapPx * (columnCount + 1)) / columnCount

                panelPlaceable = subcompose(0) {
                    Box(modifier = modifier.fillMaxSize()) {
                        val stateVertical = rememberLazyListState()
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                                .padding(
                                    start = contentStartPadding,
                                    end = contentEndPadding + extraEndPadding,
                                    top = contentTopPadding,
                                    bottom = contentBottomPadding + extraBottomPadding
                                ),
                            state = stateVertical
                        ) {
                            for ((groupIndex, commandGroup) in contentModel.commandGroups.withIndex()) {
                                if (presentationModel.showGroupLabels && (commandGroup.title != null)) {
                                    if (presentationModel.useStickyGroupLabels) {
                                        stickyHeader { CommandButtonGroupTitle(commandGroup) }
                                    } else {
                                        item { CommandButtonGroupTitle(commandGroup) }
                                    }
                                }

                                var indexRowStart = 0
                                while (true) {
                                    val indexRowEnd =
                                        (indexRowStart + columnCount).coerceAtMost(commandGroup.commands.size)
                                    rowOfItems(
                                        backgroundColor = if (groupIndex % 2 == 0) backgroundEvenGroups else backgroundOddGroups,
                                        gap = gap,
                                        commandGroup = commandGroup,
                                        indexRowStart = indexRowStart,
                                        indexRowEnd = indexRowEnd,
                                        itemWidth = itemWidth.toDp(),
                                        baseCommandButtonPresentationModel = baseCommandButtonPresentationModel,
                                        overlays = overlays
                                    )
                                    indexRowStart += columnCount
                                    if (indexRowStart >= commandGroup.commands.size) {
                                        break
                                    }
                                }
                            }
                        }
                        AuroraVerticalScrollbar(
                            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().padding(
                                start = 0.dp,
                                end = contentEndPadding + ScrollBarSizingConstants.DefaultScrollBarMargin,
                                top = contentTopPadding + ScrollBarSizingConstants.DefaultScrollBarMargin,
                                bottom = contentBottomPadding + ScrollBarSizingConstants.DefaultScrollBarMargin
                            ),
                            adapter = rememberScrollbarAdapter(scrollState = stateVertical)
                        )
                    }
                }.first().measure(constraints)
            }

            is PanelLayoutSpec.ColumnFill -> {
                val rowCount: Int = when (presentationModel.layoutSpec.columnFillSpec) {
                    is PanelColumnFillSpec.Fixed -> presentationModel.layoutSpec.columnFillSpec.rowCount
                    is PanelColumnFillSpec.Adaptive ->
                        (constraints.maxHeight - contentTopPadding.roundToPx() -
                                contentBottomPadding.roundToPx() - gapPx) /
                                (presentationModel.layoutSpec.columnFillSpec.minRowHeight.roundToPx() + gapPx)
                }
                val itemHeight = (constraints.maxHeight - contentTopPadding.roundToPx()
                        - contentBottomPadding.roundToPx() - gapPx * (rowCount + 1)) / rowCount

                panelPlaceable = subcompose(0) {
                    Box(modifier = modifier.fillMaxSize()) {
                        val stateHorizontal = rememberLazyListState()
                        LazyRow(
                            modifier = Modifier.fillMaxSize()
                                .padding(
                                    start = contentStartPadding,
                                    end = contentEndPadding + extraEndPadding,
                                    top = contentTopPadding,
                                    bottom = contentBottomPadding + extraBottomPadding
                                ),
                            state = stateHorizontal
                        ) {
                            for ((groupIndex, commandGroup) in contentModel.commandGroups.withIndex()) {
                                var indexColumnStart = 0
                                while (true) {
                                    val indexColumnEnd =
                                        (indexColumnStart + rowCount).coerceAtMost(commandGroup.commands.size)
                                    columnOfItems(
                                        backgroundColor = if (groupIndex % 2 == 0) backgroundEvenGroups else backgroundOddGroups,
                                        gap = gap,
                                        commandGroup = commandGroup,
                                        indexColumnStart = indexColumnStart,
                                        indexColumnEnd = indexColumnEnd,
                                        itemHeight = itemHeight.toDp(),
                                        baseCommandButtonPresentationModel = baseCommandButtonPresentationModel,
                                        overlays = overlays
                                    )
                                    indexColumnStart += rowCount
                                    if (indexColumnStart >= commandGroup.commands.size) {
                                        break
                                    }
                                }
                            }
                        }
                        AuroraHorizontalScrollbar(
                            modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(
                                start = contentStartPadding + ScrollBarSizingConstants.DefaultScrollBarMargin,
                                end = contentEndPadding + ScrollBarSizingConstants.DefaultScrollBarMargin,
                                top = 0.dp,
                                bottom = contentBottomPadding + ScrollBarSizingConstants.DefaultScrollBarMargin
                            ),
                            adapter = rememberScrollbarAdapter(scrollState = stateHorizontal)
                        )
                    }
                }.first().measure(constraints)
            }
        }

        layout(panelPlaceable.width, panelPlaceable.height) {
            panelPlaceable.place(0, 0)
        }
    }
}

internal fun getPreferredCommandPopupMenuPanelSize(
    contentModel: CommandPanelContentModel,
    presentationModel: CommandPopupMenuPanelPresentationModel,
    buttonLayoutManager: CommandButtonLayoutManager,
    buttonShaper: AuroraButtonShaper,
    layoutDirection: LayoutDirection,
    density: Density
): Size {
    // Our grid is uniform. The buttons will have the same width and height. Start
    // by computing the max preferred width / height across all the buttons.
    var maxButtonWidth = 0
    var maxButtonHeight = 0

    val baseCommandButtonPresentationModel =
        CommandButtonPresentationModel(
            contentPadding = presentationModel.commandContentPadding,
            presentationState = presentationModel.commandPresentationState,
            iconDimension = presentationModel.commandIconDimension,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            textStyle = presentationModel.commandTextStyle,
            textOverflow = presentationModel.commandTextOverflow,
            horizontalAlignment = presentationModel.commandHorizontalAlignment,
            horizontalGapScaleFactor = presentationModel.commandHorizontalGapScaleFactor,
            verticalGapScaleFactor = presentationModel.commandVerticalGapScaleFactor,
            popupFireTrigger = presentationModel.commandPopupFireTrigger,
            selectedStateHighlight = presentationModel.commandSelectedStateHighlight,
            popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
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
                preLayoutInfo = preLayoutInfo,
                buttonShaper = buttonShaper
            )
            maxButtonWidth = max(maxButtonWidth, preferredSize.width.toInt())
            maxButtonHeight = max(maxButtonHeight, preferredSize.height.toInt())
        }
    }

    val gap = (presentationModel.contentGap.value * density.density)
    var panelWidth = maxButtonWidth * presentationModel.layoutSpec.columnCount +
            gap * (presentationModel.layoutSpec.columnCount + 1)
    var panelHeight = maxButtonHeight * presentationModel.layoutSpec.visibleRowCount +
            gap * (presentationModel.layoutSpec.visibleRowCount + 1)

    // Account for content padding
    panelWidth += (presentationModel.contentPadding.calculateStartPadding(layoutDirection) +
            presentationModel.contentPadding.calculateEndPadding(layoutDirection)).value * density.density
    panelHeight += (presentationModel.contentPadding.calculateTopPadding() +
            presentationModel.contentPadding.calculateBottomPadding()).value * density.density

    // Account for scroll bar. For now the assumption is that it's always showing
    val extraSpaceForScrollBar = ScrollBarSizingConstants.DefaultScrollBarSize.value * density.density
    panelWidth += extraSpaceForScrollBar

    return Size(panelWidth, panelHeight)
}

@Composable
private fun CommandButtonGroupTitle(groupModel: CommandGroup) {
    require(groupModel.title != null) {
        "This composable should not be called on a command group with no title"
    }

    TitleLabel(
        modifier = Modifier.fillMaxWidth(),
        title = groupModel.title,
        presentationModel = LabelPresentationModel(
            horizontalAlignment = HorizontalAlignment.Leading
        )
    )
}
