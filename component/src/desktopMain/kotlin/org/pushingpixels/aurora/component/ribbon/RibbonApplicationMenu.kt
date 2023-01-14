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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.popup.BaseCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.*

data class RibbonApplicationMenuCommand(
    override val text: String,
    override val secondaryContentModel: RibbonApplicationMenuContentModel,
    override val secondaryRichTooltip: RichTooltip? = null
) : BaseCommand {
    override val extraText = null
    override val icon = null
    override val action = null
    override val actionPreview = null
    override val isActionEnabled = false
    override val isActionToggle = false
    override val isActionToggleSelected = false
    override val actionRichTooltip = null
    override val onTriggerActionToggleSelectedChange = null
    override val isSecondaryEnabled = true
}

object RibbonApplicationMenuButtonPresentationStates {
    private class CommandButtonLayoutManagerRibbonApplicationMenuButton(
        override val layoutDirection: LayoutDirection,
        private val _density: Density,
        private val textStyle: TextStyle,
        private val fontFamilyResolver: FontFamily.Resolver
    ) : CommandButtonLayoutManager {
        override val density = _density.density
        override val fontScale = _density.fontScale

        override fun getPreferredIconSize(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel
        ): DpSize {
            return DpSize.Zero
        }

        override fun getPreferredSize(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
        ): Size {
            val paddingValues = presentationModel.contentPadding
            val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
            val bx = presentationModel.horizontalGapScaleFactor * paddingValues.horizontalPaddings.toPx()

            val paragraph = Paragraph(
                text = command.text, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )

            return Size(
                width = bx + paragraph.maxIntrinsicWidth,
                height = by + paragraph.height
            )
        }

        override fun getPreLayoutInfo(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel
        ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
            return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
                commandButtonKind = CommandButtonKind.PopupOnly,
                showIcon = false,
                texts = listOf(command.text),
                extraTexts = emptyList(),
                isTextInActionArea = true,
                separatorOrientation = null,
                showPopupIcon = false
            )
        }

        override fun getLayoutInfo(
            constraints: Constraints,
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
        ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
            val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo)

            val paddingValues = presentationModel.contentPadding
            val top = presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
            val left = presentationModel.horizontalGapScaleFactor *
                    paddingValues.calculateLeftPadding(layoutDirection).toPx()

            val paragraph = Paragraph(
                text = command.text, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )

            return CommandButtonLayoutManager.CommandButtonLayoutInfo(
                fullSize = preferredSize,
                actionClickArea = Rect.Zero,
                popupClickArea = Rect(
                    left = 0.0f,
                    right = preferredSize.width,
                    top = 0.0f,
                    bottom = preferredSize.height
                ),
                separatorArea = Rect.Zero,
                iconRect = Rect.Zero,
                textLayoutInfoList = listOf(
                    CommandButtonLayoutManager.TextLayoutInfo(
                        text = command.text,
                        textRect = Rect(
                            left = left, right = left + paragraph.maxIntrinsicWidth,
                            top = top, bottom = top + paragraph.height
                        )
                    )
                ),
                extraTextLayoutInfoList = emptyList(),
                popupActionRect = Rect.Zero
            )
        }
    }

    val AppMenuButtonState: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Ribbon Application Menu Button") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                return CommandButtonLayoutManagerRibbonApplicationMenuButton(
                    layoutDirection, density, textStyle, fontFamilyResolver
                )
            }
        }

    val RibbonAppMenuSecondaryLevel: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Ribbon application menu tile level 2") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                throw UnsupportedOperationException()
            }
        }
}

data class RibbonApplicationMenuCommandButtonPresentationModel(
    override val textStyle: TextStyle? = null,
    override val textOverflow: TextOverflow = TextOverflow.Clip,
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    override val popupKeyTip: String? = null,
    override val popupRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.WideButtonContentPadding,
    override val sides: Sides = Sides()
) : BaseCommandButtonPresentationModel {
    override val presentationState: CommandButtonPresentationState = RibbonApplicationMenuButtonPresentationStates.AppMenuButtonState
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center
    override val iconDimension: DpSize? = null
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val forceAllocateSpaceForIcon: Boolean = false
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval = CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val toDismissPopupsOnActivation: Boolean = true
    override val popupMenuPresentationModel: CommandPopupMenuPresentationModel =
        CommandPopupMenuPresentationModel(menuPresentationState = CommandButtonPresentationState.Tile)
    override val horizontalGapScaleFactor: Float = 1.0f
    override val verticalGapScaleFactor: Float = 1.0f
    override val minWidth: Dp = 0.dp
    override val isMenu: Boolean = false
}

private data class RibbonApplicationMenuPopupContentLayoutInfo(
    override val popupSize: Size,
    val menuButtonPresentationModel: CommandButtonPresentationModel,
) : BaseCommandMenuPopupLayoutInfo

private object RibbonApplicationMenuPopupHandler : BaseCommandMenuHandler<
        RibbonApplicationMenuContentModel, CommandPopupMenuPresentationModel,
        RibbonApplicationMenuPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: RibbonApplicationMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver
    ): RibbonApplicationMenuPopupContentLayoutInfo {

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
            iconActiveFilterStrategy = IconFilterStrategy.Original,
            iconEnabledFilterStrategy = IconFilterStrategy.Original,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
            forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
            popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Leading,
            contentPadding = CommandButtonSizingConstants.CompactButtonContentPadding,
            isMenu = true,
            sides = Sides.ClosedRectangle
        )

        val layoutManager: CommandButtonLayoutManager =
            menuButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        var maxWidth = 0.0f
        var combinedHeight = 0.0f
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                val preferredSize = layoutManager.getPreferredSize(
                    command = secondaryCommand,
                    presentationModel = menuButtonPresentationModel,
                    preLayoutInfo = layoutManager.getPreLayoutInfo(
                        command = secondaryCommand,
                        presentationModel = menuButtonPresentationModel
                    )
                )
                maxWidth = kotlin.math.max(maxWidth, preferredSize.width)
                combinedHeight += preferredSize.height
            }
        }

        return RibbonApplicationMenuPopupContentLayoutInfo(
            popupSize = Size(
                width = maxWidth,
                height = combinedHeight
            ),
            menuButtonPresentationModel = menuButtonPresentationModel
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: RibbonApplicationMenuContentModel,
        menuPresentationModel: CommandPopupMenuPresentationModel,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: RibbonApplicationMenuPopupContentLayoutInfo
    ) {
        val menuButtonPresentationModel = popupContentLayoutInfo.menuButtonPresentationModel

        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        Column(
            modifier = Modifier.fillMaxSize().background(color = backgroundColorScheme.backgroundFillColor)
                .padding(all = 1.0.dp)
        ) {
            for (commandGroup in menuContentModel.groups) {
                for (secondaryCommand in commandGroup.commands) {
                    // Check if we have a presentation overlay for this secondary command
                    val hasOverlay = overlays.containsKey(secondaryCommand)
                    val currSecondaryPresentationModel = if (hasOverlay)
                        menuButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
                    else menuButtonPresentationModel
                    // Project a command button for each secondary command, passing the same
                    // overlays into it.
                    CommandButtonProjection(
                        contentModel = secondaryCommand,
                        presentationModel = currSecondaryPresentationModel,
                        overlays = overlays
                    ).project(
                        modifier = Modifier.fillMaxWidth(),
                        actionInteractionSource = remember { MutableInteractionSource() },
                        popupInteractionSource = remember { MutableInteractionSource() }
                    )
                }
            }
        }
    }
}

class RibbonApplicationMenuCommandButtonProjection(
    contentModel: RibbonApplicationMenuCommand,
    presentationModel: RibbonApplicationMenuCommandButtonPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null,
    secondaryStates: Map<Command, CommandButtonPresentationState>? = null
) : BaseCommandButtonProjection<RibbonApplicationMenuCommand, RibbonApplicationMenuCommandButtonPresentationModel>(
    contentModel, presentationModel, overlays
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        super.project(
            modifier = modifier,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = RibbonApplicationMenuPopupHandler,
        )
    }
}

