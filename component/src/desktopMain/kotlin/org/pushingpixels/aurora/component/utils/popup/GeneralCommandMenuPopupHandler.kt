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
package org.pushingpixels.aurora.component.utils.popup

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.getPreferredCommandPopupMenuPanelSize
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCascadingCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.CascadingCommandMenuHandler
import org.pushingpixels.aurora.component.popup.auroraPopupMenuRowBackground
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@AuroraInternalApi
data class GeneralPopupContentLayoutInfo(
    override val popupSize: Size,
    val fullSize: Size,
    val buttonPanelSize: Size,
    val separatorSize: Size,
    val generalContentSize: Size,
    val generalContentItemHeights: FloatArray,
    val generalVerticalScrollbarSize: Size,
    val gutterWidth: Float
) : BaseCascadingCommandMenuPopupLayoutInfo

@AuroraInternalApi
object GeneralCommandMenuPopupHandler : CascadingCommandMenuHandler<
        CommandMenuContentModel, CommandPopupMenuPresentationModel, GeneralPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): GeneralPopupContentLayoutInfo {
        val hasButtonPanel = (menuContentModel.panelContentModel != null)
        val panelButtonLayoutManager =
            if (hasButtonPanel) menuPresentationModel.panelPresentationModel!!.commandPresentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            ) else null

        // Compute the size of the popup content, accounting for the panel
        val panelPreferredSize =
            if (hasButtonPanel) getPreferredCommandPopupMenuPanelSize(
                contentModel = menuContentModel.panelContentModel,
                presentationModel = menuPresentationModel.panelPresentationModel!!,
                buttonLayoutManager = panelButtonLayoutManager!!,
                buttonShaper = buttonShaper,
                layoutDirection = layoutDirection,
                density = density
            ) else Size(0.0f, 0.0f)

        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val regularButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.itemPresentationState,
            iconActiveFilterStrategy = menuPresentationModel.itemIconActiveFilterStrategy,
            iconEnabledFilterStrategy = menuPresentationModel.itemIconEnabledFilterStrategy,
            iconDisabledFilterStrategy = menuPresentationModel.itemIconDisabledFilterStrategy,
            popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
            popupFireTrigger = menuPresentationModel.itemPopupFireTrigger,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = menuPresentationModel.itemHorizontalAlignment,
            contentPadding = menuPresentationModel.itemContentPadding,
            selectedStateHighlight = menuPresentationModel.itemSelectedStateHighlight
        )
        val regularButtonLayoutManager =
            menuPresentationModel.itemPresentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        var gutterWidth = 0.0f
        var atLeastOneRegularButtonHasIcon = false
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                if ((secondaryCommand.icon != null) || (secondaryCommand.isActionToggle)) {
                    atLeastOneRegularButtonHasIcon = true
                    if (gutterWidth == 0.0f) {
                        gutterWidth =
                            (menuPresentationModel.itemContentPadding.calculateStartPadding(layoutDirection) +
                                    regularButtonLayoutManager.getPreferredIconSize(
                                        command = secondaryCommand,
                                        presentationModel = regularButtonPresentationModel
                                    ).width +
                                    regularButtonLayoutManager.getIconTextGap(regularButtonPresentationModel) / 2.0f).value * density.density
                    }
                }
            }
        }

        val regularButtonPresentationModelOverlay =
            BaseCommandButtonPresentationModel.Overlay(
                forceAllocateSpaceForIcon = atLeastOneRegularButtonHasIcon,
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
        val regularButtonPresentationModelWithOverlay =
            regularButtonPresentationModel.overlayWith(
                regularButtonPresentationModelOverlay
            )

        // Account for possible presence of vertical scroll bar
        var showingVerticalRegularContentScrollBar = false

        var regularButtonColumnWidth = 0.0f
        var regularButtonCount = 0
        var regularButtonHeight = 0.0f
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                val preferredSize = regularButtonLayoutManager.getPreferredSize(
                    command = secondaryCommand,
                    presentationModel = regularButtonPresentationModelWithOverlay,
                    preLayoutInfo = regularButtonLayoutManager.getPreLayoutInfo(
                        command = secondaryCommand,
                        presentationModel = regularButtonPresentationModelWithOverlay
                    ),
                    buttonShaper = buttonShaper
                )
                regularButtonColumnWidth = max(regularButtonColumnWidth, preferredSize.width)
                regularButtonHeight = max(regularButtonHeight, preferredSize.height)

                regularButtonCount++
                if (menuPresentationModel.maxVisibleItems == regularButtonCount) {
                    // This is the maximum number of menu commands that we should show
                    showingVerticalRegularContentScrollBar = true
                }
            }
        }

        // Have display prototype?
        if (displayPrototypeCommand != null) {
            val displayPrototypePreferredSize = regularButtonLayoutManager.getPreferredSize(
                command = displayPrototypeCommand,
                presentationModel = regularButtonPresentationModelWithOverlay,
                preLayoutInfo = regularButtonLayoutManager.getPreLayoutInfo(
                    command = displayPrototypeCommand,
                    presentationModel = regularButtonPresentationModelWithOverlay
                ),
                buttonShaper = buttonShaper
            )
            regularButtonColumnWidth = max(regularButtonColumnWidth, displayPrototypePreferredSize.width)
        }

        val separatorHeight = SeparatorSizingConstants.Thickness.value * density.density
        var index = 0
        val itemHeights = FloatArray(regularButtonCount + menuContentModel.groups.size - 1)
        for ((groupIndex, commandGroup) in menuContentModel.groups.withIndex()) {
            for (secondaryCommand in commandGroup.commands) {
                itemHeights[index++] = regularButtonHeight
            }
            if (groupIndex < (menuContentModel.groups.size - 1)) {
                itemHeights[index++] = separatorHeight
            }
        }

        val visibleCommands = if (menuPresentationModel.maxVisibleItems == 0) regularButtonCount
        else min(menuPresentationModel.maxVisibleItems, regularButtonCount)
        val regularButtonColumnHeight = visibleCommands * regularButtonHeight +
                (menuContentModel.groups.size - 1) * separatorHeight

        val generalVerticalScrollbarWidth = if (showingVerticalRegularContentScrollBar) {
            ScrollBarSizingConstants.DefaultScrollBarThickness.value * density.density +
                    2 * ScrollBarSizingConstants.DefaultScrollBarMargin.value * density.density
        } else {
            0.0f
        }

        // If we're displaying the button panel, we need to tweak either the button panel width
        // or the general content width so that they match
        val fullContentWidth = max(
            panelPreferredSize.width,
            regularButtonColumnWidth + generalVerticalScrollbarWidth
        )
        val fullContentHeight = panelPreferredSize.height +
                (if (hasButtonPanel) SeparatorSizingConstants.Thickness.value * density.density else 0.0f) +
                regularButtonColumnHeight
        val finalGeneralContentWidth = fullContentWidth - generalVerticalScrollbarWidth

        val offset = ceil(density.density).toInt()

        return GeneralPopupContentLayoutInfo(
            popupSize = Size(fullContentWidth, fullContentHeight),
            fullSize = Size(
                width = fullContentWidth + 2 * offset,
                height = fullContentHeight + 2 * offset
            ),
            buttonPanelSize = Size(width = fullContentWidth, height = panelPreferredSize.height),
            separatorSize = Size(
                width = fullContentWidth,
                height = if (hasButtonPanel) SeparatorSizingConstants.Thickness.value * density.density else 0.0f
            ),
            generalContentSize = Size(
                width = finalGeneralContentWidth,
                height = regularButtonColumnHeight
            ),
            generalContentItemHeights = itemHeights,
            generalVerticalScrollbarSize = Size(
                width = generalVerticalScrollbarWidth,
                height = regularButtonColumnHeight
            ),
            gutterWidth = gutterWidth
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: GeneralPopupContentLayoutInfo
    ) {
        TopLevelPopupContent(
            menuContentModel = menuContentModel,
            menuPresentationModel = menuPresentationModel,
            overlays = overlays,
            contentLayoutInfo = popupContentLayoutInfo
        )
    }

    @Composable
    private fun TopLevelPopupContent(
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        contentLayoutInfo: GeneralPopupContentLayoutInfo
    ) {
        val stateVertical = rememberScrollState(0)

        val hasPanel = (menuContentModel.panelContentModel != null)
        PopupContentLayout(
            modifier = Modifier.auroraBackground(),
            hasPanel = hasPanel,
            contentLayoutInfo = contentLayoutInfo
        ) {
            if (hasPanel) {
                CommandButtonPanelProjection(
                    contentModel = menuContentModel.panelContentModel,
                    presentationModel = menuPresentationModel.panelPresentationModel!!.toCommandPanelPresentationModel(),
                    overlays = overlays
                ).project()
                HorizontalSeparatorProjection(
                    presentationModel = SeparatorPresentationModel(
                        startGradientAmount = 0.dp,
                        endGradientAmount = 0.dp
                    )
                ).project()
            }

            Layout(modifier = Modifier.verticalScroll(stateVertical), content = {
                PopupGeneralContent(
                    menuContentModel = menuContentModel,
                    menuPresentationModel = menuPresentationModel,
                    gutterWidth = contentLayoutInfo.gutterWidth,
                    overlays = overlays
                )
            }) { measurables, _ ->
                val placeables = measurables.mapIndexed { index, measurable ->
                    // Measure each child with fixed (widest) width and matching height (button
                    // height or separator height)
                    measurable.measure(
                        Constraints.fixed(
                            width = contentLayoutInfo.generalContentSize.width.roundToInt(),
                            height = contentLayoutInfo.generalContentItemHeights[index].toInt()
                        )
                    )
                }

                // The children are laid out in a column
                val contentMaxHeight = placeables.sumOf { it.height }

                layout(
                    width = contentLayoutInfo.generalContentSize.width.roundToInt(),
                    height = contentMaxHeight
                ) {
                    var yPosition = 0

                    placeables.forEach { placeable ->
                        placeable.placeRelative(
                            x = 0,
                            y = yPosition
                        )
                        yPosition += placeable.height
                    }
                }
            }
            if (contentLayoutInfo.generalVerticalScrollbarSize.width > 0.0f) {
                AuroraVerticalScrollbar(
                    adapter = rememberScrollbarAdapter(scrollState = stateVertical)
                )
            }
        }
    }

    @Composable
    private fun PopupGeneralContent(
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        gutterWidth: Float,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>
    ) {
        // If at least one secondary command in this popup menu has icon factory
        // we force all command buttons to allocate space for the icon (for overall
        // alignment of content across the entire popup menu)
        var atLeastOneButtonHasIcon = false
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                if (secondaryCommand.icon != null) {
                    atLeastOneButtonHasIcon = true
                }
                if (secondaryCommand.isActionToggle) {
                    atLeastOneButtonHasIcon = true
                }
            }
        }

        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val itemButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.itemPresentationState,
            iconActiveFilterStrategy = menuPresentationModel.itemIconActiveFilterStrategy,
            iconEnabledFilterStrategy = menuPresentationModel.itemIconEnabledFilterStrategy,
            iconDisabledFilterStrategy = menuPresentationModel.itemIconDisabledFilterStrategy,
            forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
            popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
            popupFireTrigger = menuPresentationModel.itemPopupFireTrigger,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = menuPresentationModel.itemHorizontalAlignment,
            contentPadding = menuPresentationModel.itemContentPadding,
            selectedStateHighlight = menuPresentationModel.itemSelectedStateHighlight,
            sides = menuPresentationModel.itemSides
        )

        var runningCommandIndex = 0
        for ((commandGroupIndex, commandGroup) in menuContentModel.groups.withIndex()) {
            for (secondaryCommand in commandGroup.commands) {
                // Check if we have a presentation overlay for this secondary command
                val hasOverlay = overlays.containsKey(secondaryCommand)
                var currSecondaryPresentationModel = if (hasOverlay)
                    itemButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
                else itemButtonPresentationModel
                if (secondaryCommand == menuContentModel.highlightedCommand) {
                    // If our secondary content model has a highlighted command, pass bold
                    // font weight to the text style of the matching command button.
                    currSecondaryPresentationModel =
                        currSecondaryPresentationModel.overlayWith(
                            BaseCommandButtonPresentationModel.Overlay(
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        )
                }

                // Project a command button for each secondary command, passing the same
                // overlays into it.
                CommandButtonProjection(
                    contentModel = secondaryCommand,
                    presentationModel = currSecondaryPresentationModel,
                    secondaryOverlays = overlays
                ).project(
                    modifier = Modifier.auroraPopupMenuRowBackground(
                        backgroundFillColorQuery = menuPresentationModel.backgroundFillColorQuery,
                        iconGutterFillColorQuery = menuPresentationModel.iconGutterFillColorQuery,
                        rowIndex = runningCommandIndex,
                        gutterWidth = gutterWidth
                    ),
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() }
                )
                runningCommandIndex++
            }
            if (commandGroupIndex < (menuContentModel.groups.size - 1)) {
                HorizontalSeparatorProjection(
                    presentationModel = SeparatorPresentationModel(
                        startGradientAmount = 0.dp,
                        endGradientAmount = 0.dp
                    )
                ).project()
            }
        }
    }

    @Composable
    private fun PopupContentLayout(
        hasPanel: Boolean,
        modifier: Modifier = Modifier,
        contentLayoutInfo: GeneralPopupContentLayoutInfo,
        content: @Composable () -> Unit
    ) {
        val offset = ceil(LocalDensity.current.density).toInt()
        Layout(modifier = modifier, content = content) { measurables, _ ->
            var panelPlaceable: Placeable? = null
            var panelSeparatorPlaceable: Placeable? = null
            if (hasPanel) {
                // The column width is determined by the panel
                panelPlaceable = measurables[0].measure(
                    Constraints.fixed(
                        width = contentLayoutInfo.buttonPanelSize.width.toInt(),
                        height = contentLayoutInfo.buttonPanelSize.height.toInt()
                    )
                )
                panelSeparatorPlaceable = measurables[1].measure(
                    Constraints.fixed(
                        width = contentLayoutInfo.separatorSize.width.toInt(),
                        height = contentLayoutInfo.separatorSize.height.toInt()
                    )
                )
            }

            val generalContentPlaceable = measurables[if (hasPanel) 2 else 0].measure(
                Constraints.fixed(
                    width = contentLayoutInfo.generalContentSize.width.toInt(),
                    height = contentLayoutInfo.generalContentSize.height.toInt()
                )
            )
            var verticalScrollBarPlaceable: Placeable? = null
            val scrollBarMarginPx = ScrollBarSizingConstants.DefaultScrollBarMargin.roundToPx()
            val scrollBarThicknessPx = ScrollBarSizingConstants.DefaultScrollBarThickness.roundToPx()
            if (contentLayoutInfo.generalVerticalScrollbarSize.width > 0.0f) {
                verticalScrollBarPlaceable = measurables[if (hasPanel) 3 else 1].measure(
                    Constraints.fixed(
                        width = scrollBarThicknessPx,
                        height = contentLayoutInfo.generalVerticalScrollbarSize.height.toInt() - 2 * scrollBarMarginPx
                    )
                )
            }

            layout(
                width = contentLayoutInfo.fullSize.width.toInt(),
                height = contentLayoutInfo.fullSize.height.toInt()
            ) {
                // Offset everything by [offset,offset] for border insets
                var yPosition = offset
                if (panelPlaceable != null) {
                    panelPlaceable.placeRelative(offset, offset)
                    yPosition += panelPlaceable.height
                    panelSeparatorPlaceable!!.placeRelative(offset, yPosition)
                    yPosition += panelSeparatorPlaceable.height
                }

                generalContentPlaceable.placeRelative(x = offset, y = yPosition)
                verticalScrollBarPlaceable?.placeRelative(
                    x = offset + generalContentPlaceable.width + scrollBarMarginPx + offset,
                    y = yPosition + scrollBarMarginPx
                )
            }
        }
    }
}
