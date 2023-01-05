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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.component.AuroraCommandButton
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.utils.CommandMenuHandler
import org.pushingpixels.aurora.component.utils.CommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.theming.*
import kotlin.math.max

internal data class ColorSelectorPopupContentLayoutInfo(
    override val popupSize: DpSize,
    val menuButtonPresentationModel: CommandButtonPresentationModel
) : CommandMenuPopupLayoutInfo

internal object ColorSelectorCommandMenuPopupHandler : CommandMenuHandler<
        ColorSelectorMenuContentModel, ColorSelectorCommandPopupMenuPresentationModel,
        ColorSelectorPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver
    ): ColorSelectorPopupContentLayoutInfo {

        // If at least one secondary command in this popup menu has icon factory
        // we force all command buttons to allocate space for the icon (for overall
        // alignment of content across the entire popup menu)
        var atLeastOneButtonHasIcon = false
        for (entry in menuContentModel.entries) {
            if (entry is ColorSelectorPopupMenuCommand) {
                if (entry.command.icon != null) {
                    atLeastOneButtonHasIcon = true
                }
                if (entry.command.isActionToggle) {
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
            sides = Sides(straightSides = Side.values().toSet())
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
        for (entry in menuContentModel.entries) {
            when (entry) {
                is ColorSelectorPopupMenuCommand -> {
                    val preferredSize = layoutManager.getPreferredSize(
                        command = entry.command,
                        presentationModel = menuButtonPresentationModel,
                        preLayoutInfo = layoutManager.getPreLayoutInfo(
                            command = entry.command,
                            presentationModel = menuButtonPresentationModel
                        )
                    )
                    maxWidth = max(maxWidth, preferredSize.width)
                    combinedHeight += preferredSize.height
                }

                else -> {}
            }
        }


        return ColorSelectorPopupContentLayoutInfo(
            popupSize = DpSize(
                width = (maxWidth / density.density).dp,
                height = (combinedHeight / density.density).dp
            ),
            menuButtonPresentationModel = menuButtonPresentationModel
        )
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun generatePopupContent(
        popupMenu: AuroraSwingPopupMenu,
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        toDismissPopupsOnActivation: Boolean,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: ColorSelectorPopupContentLayoutInfo
    ) {
        // If at least one secondary command in this popup menu has icon factory
        // we force all command buttons to allocate space for the icon (for overall
        // alignment of content across the entire popup menu)
        var atLeastOneButtonHasIcon = false
        for (entry in menuContentModel.entries) {
            if (entry is ColorSelectorPopupMenuCommand) {
                if (entry.command.icon != null) {
                    atLeastOneButtonHasIcon = true
                }
                if (entry.command.isActionToggle) {
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
            sides = Sides(straightSides = Side.values().toSet())
        )

        Column(modifier = Modifier.fillMaxSize()) {
            for (entry in menuContentModel.entries) {
                when (entry) {
                    is ColorSelectorPopupMenuCommand -> {
                        // Check if we have a presentation overlay for this secondary command
                        val hasOverlay = overlays.containsKey(entry.command)
                        val currSecondaryPresentationModel = if (hasOverlay)
                            menuButtonPresentationModel.overlayWith(overlays[entry.command]!!)
                        else menuButtonPresentationModel
                        // Create a command button for each secondary command, passing the same
                        // overlays into it.
                        AuroraCommandButton(
                            modifier = Modifier.fillMaxWidth(),
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() },
                            command = entry.command,
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
                            popupHandler = this@ColorSelectorCommandMenuPopupHandler,
                            overlays = overlays
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}
