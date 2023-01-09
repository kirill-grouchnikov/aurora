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
package org.pushingpixels.aurora.component.utils.popup

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.AuroraCommandButton
import org.pushingpixels.aurora.component.AuroraCommandButtonPanel
import org.pushingpixels.aurora.component.getPreferredCommandPopupMenuPanelSize
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.popup.BaseCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.theming.*
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

internal data class GeneralPopupContentLayoutInfo(
    override val popupSize: Size,
    val fullSize: Size,
    val buttonPanelSize: Size,
    val separatorSize: Size,
    val generalContentSize: Size,
    val generalContentItemHeights: FloatArray,
    val generalVerticalScrollbarSize: Size
) : BaseCommandMenuPopupLayoutInfo

internal object GeneralCommandMenuPopupHandler : BaseCommandMenuHandler<
        CommandMenuContentModel, CommandPopupMenuPresentationModel, GeneralPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver
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
                contentModel = menuContentModel.panelContentModel!!,
                presentationModel = menuPresentationModel.panelPresentationModel!!,
                buttonLayoutManager = panelButtonLayoutManager!!,
                layoutDirection = layoutDirection,
                density = density
            ) else Size(0.0f, 0.0f)

        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val regularButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.menuPresentationState,
            iconActiveFilterStrategy = menuPresentationModel.menuIconActiveFilterStrategy,
            iconEnabledFilterStrategy = menuPresentationModel.menuIconEnabledFilterStrategy,
            iconDisabledFilterStrategy = menuPresentationModel.menuIconDisabledFilterStrategy,
            popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Leading,
            contentPadding = menuPresentationModel.menuContentPadding,
            isMenu = true
        )
        val regularButtonLayoutManager =
            menuPresentationModel.menuPresentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        var atLeastOneRegularButtonHasIcon = false
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                if (secondaryCommand.icon != null) {
                    atLeastOneRegularButtonHasIcon = true
                }
                if (secondaryCommand.isActionToggle) {
                    atLeastOneRegularButtonHasIcon = true
                }
            }
        }

        val regularButtonPresentationModelOverlay =
            CommandButtonPresentationModel.Overlay(
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
                    )
                )
                regularButtonColumnWidth = max(regularButtonColumnWidth, preferredSize.width)
                regularButtonHeight = max(regularButtonHeight, preferredSize.height)

                regularButtonCount++
                if (menuPresentationModel.maxVisibleMenuCommands == regularButtonCount) {
                    // This is the maximum number of menu commands that we should show
                    showingVerticalRegularContentScrollBar = true
                }
            }
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

        val visibleCommands = if (menuPresentationModel.maxVisibleMenuCommands == 0) regularButtonCount
        else min(menuPresentationModel.maxVisibleMenuCommands, regularButtonCount)
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
            )
        )
    }

    @Composable
    override fun generatePopupContent(
        popupMenu: AuroraSwingPopupMenu,
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        toDismissPopupsOnActivation: Boolean,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: GeneralPopupContentLayoutInfo
    ) {
        TopLevelPopupContent(
            popupMenu = popupMenu,
            menuContentModel = menuContentModel,
            menuPresentationModel = menuPresentationModel,
            toDismissPopupsOnActivation = toDismissPopupsOnActivation,
            toUseBackgroundStriping = toUseBackgroundStriping,
            overlays = overlays,
            contentLayoutInfo = popupContentLayoutInfo
        )
    }

    @Composable
    private fun TopLevelPopupContent(
        popupMenu: AuroraSwingPopupMenu,
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        toDismissPopupsOnActivation: Boolean,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
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
                AuroraCommandButtonPanel(
                    contentModel = menuContentModel.panelContentModel!!,
                    presentationModel = menuPresentationModel.panelPresentationModel!!.toCommandPanelPresentationModel(),
                    extraAction = {
                        if (toDismissPopupsOnActivation) {
                            AuroraPopupManager.hidePopups(null)
                        }
                    },
                    overlays = overlays
                )
                HorizontalSeparatorProjection(
                    presentationModel = SeparatorPresentationModel(
                        startGradientAmount = 0.dp,
                        endGradientAmount = 0.dp
                    )
                ).project()
            }

            Layout(modifier = Modifier.verticalScroll(stateVertical), content = {
                PopupGeneralContent(
                    popupMenu = popupMenu,
                    menuContentModel = menuContentModel,
                    menuPresentationModel = menuPresentationModel,
                    toDismissPopupsOnActivation = toDismissPopupsOnActivation,
                    toUseBackgroundStriping = toUseBackgroundStriping,
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
                    adapter = remember(stateVertical) {
                        ScrollbarAdapter(stateVertical)
                    }
                )
            }
        }
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    private fun PopupGeneralContent(
        popupMenu: AuroraSwingPopupMenu,
        menuContentModel: CommandMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        toDismissPopupsOnActivation: Boolean,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>
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
        val menuButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.menuPresentationState,
            iconActiveFilterStrategy = menuPresentationModel.menuIconActiveFilterStrategy,
            iconEnabledFilterStrategy = menuPresentationModel.menuIconEnabledFilterStrategy,
            iconDisabledFilterStrategy = menuPresentationModel.menuIconDisabledFilterStrategy,
            forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
            popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Leading,
            contentPadding = menuPresentationModel.menuContentPadding,
            isMenu = true,
            sides = Sides(straightSides = Side.values().toSet())
        )

        var runningCommandIndex = 0
        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
        val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
        for ((commandGroupIndex, commandGroup) in menuContentModel.groups.withIndex()) {
            for (secondaryCommand in commandGroup.commands) {
                // Check if we have a presentation overlay for this secondary command
                val hasOverlay = overlays.containsKey(secondaryCommand)
                var currSecondaryPresentationModel = if (hasOverlay)
                    menuButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
                else menuButtonPresentationModel
                if (secondaryCommand == menuContentModel.highlightedCommand) {
                    // If our secondary content model has a highlighted command, pass bold
                    // font weight to the text style of the matching command button.
                    currSecondaryPresentationModel =
                        currSecondaryPresentationModel.overlayWith(
                            CommandButtonPresentationModel.Overlay(
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        )
                }

                // Create a command button for each secondary command, passing the same
                // overlays into it.
                AuroraCommandButton(
                    modifier = if (toUseBackgroundStriping)
                        Modifier.background(
                            color = if ((runningCommandIndex % 2) == 0) backgroundEvenRows else backgroundOddRows
                        ) else Modifier,
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() },
                    command = secondaryCommand,
                    parentPopupMenu = popupMenu,
                    extraAction = {
                        if (toDismissPopupsOnActivation and
                            currSecondaryPresentationModel.toDismissPopupsOnActivation
                        ) {
                            AuroraPopupManager.hidePopups(null)
                        }
                    },
                    popupPlacementStrategyProvider = null,
                    presentationModel = currSecondaryPresentationModel,
                    popupHandler = this,
                    overlays = overlays
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
