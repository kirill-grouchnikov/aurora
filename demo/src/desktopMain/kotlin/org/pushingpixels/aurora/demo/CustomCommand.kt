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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCascadingCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.CascadingCommandMenuHandler
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import kotlin.math.max

data class CustomCommand(
    override val icon: Painter,
    override val secondaryContentModel: CustomMenuContentModel,
) : BaseCommand {
    override val text = ""
    override val extraText = null
    override val action = null
    override val actionPreview = null
    override val isSecondaryEnabled = true
    override val secondaryRichTooltip = null
    override val isActionEnabled = false
    override val isActionToggle = false
    override val isActionToggleSelected = false
    override val actionRichTooltip = null
    override val onTriggerActionToggleSelectedChange = null
    override val tag = null
}

data class CustomMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val entries: List<Command>
) : BaseCommandMenuContentModel

data class CustomCommandPopupMenuPresentationModel(
    val itemPresentationState: CommandButtonPresentationState = DefaultCommandPopupMenuPresentationState,
    val itemPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnRollover,
    val itemSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.IconOnly,
) : BaseCommandPopupMenuPresentationModel

data class CustomCommandButtonPresentationModel(
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    override val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    override val iconDimension: DpSize? = null,
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val textStyle: TextStyle? = null,
    override val textOverflow: TextOverflow = TextOverflow.Clip,
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    override val toDismissPopupsOnActivation: Boolean = true,
    override val popupKeyTip: String? = null,
    override val popupMenuPresentationModel: CustomCommandPopupMenuPresentationModel = CustomCommandPopupMenuPresentationModel(),
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    override val minWidth: Dp = 0.dp,
    override val sides: Sides = Sides()
) : BaseCommandButtonPresentationModel {
    override val presentationState = CommandButtonPresentationState.Small
    override val forceAllocateSpaceForIcon = false
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupAnchorBoundsProvider: (() -> Rect)? = null
    override val horizontalGapScaleFactor = 1.0f
    override val verticalGapScaleFactor = 1.0f
    override val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize
    override val showPopupIcon: Boolean = false

    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): CustomCommandButtonPresentationModel {
        return CustomCommandButtonPresentationModel(
            colorTokensOverlayProvider = overlay.colorTokensOverlayProvider ?: this.colorTokensOverlayProvider,
            backgroundAppearanceStrategy = overlay.backgroundAppearanceStrategy
                ?: this.backgroundAppearanceStrategy,
            horizontalAlignment = overlay.horizontalAlignment ?: this.horizontalAlignment,
            iconDimension = overlay.iconDimension ?: this.iconDimension,
            iconDisabledFilterStrategy = overlay.iconDisabledFilterStrategy ?: this.iconDisabledFilterStrategy,
            iconEnabledFilterStrategy = overlay.iconEnabledFilterStrategy ?: this.iconEnabledFilterStrategy,
            iconActiveFilterStrategy = overlay.iconActiveFilterStrategy ?: this.iconActiveFilterStrategy,
            textStyle = overlay.textStyle ?: this.textStyle,
            textOverflow = overlay.textOverflow ?: this.textOverflow,
            popupPlacementStrategy = overlay.popupPlacementStrategy ?: this.popupPlacementStrategy,
            toDismissPopupsOnActivation = overlay.toDismissPopupsOnActivation ?: this.toDismissPopupsOnActivation,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            popupMenuPresentationModel = (overlay.popupMenuPresentationModel as? CustomCommandPopupMenuPresentationModel)
                ?: this.popupMenuPresentationModel,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
            minWidth = overlay.minWidth ?: this.minWidth,
            sides = overlay.sides ?: this.sides
        )
    }
}

data class CustomPopupContentLayoutInfo(
    override val popupSize: Size,
    val itemButtonPresentationModel: CommandButtonPresentationModel,
) : BaseCascadingCommandMenuPopupLayoutInfo

object CustomCommandMenuPopupHandler : CascadingCommandMenuHandler<
        CustomMenuContentModel, CustomCommandPopupMenuPresentationModel,
        CustomPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: CustomMenuContentModel,
        menuPresentationModel: CustomCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): CustomPopupContentLayoutInfo {

        // If at least one secondary command in this popup menu has icon factory
        // we force all command buttons to allocate space for the icon (for overall
        // alignment of content across the entire popup menu)
        var atLeastOneButtonHasIcon = false
        for (entry in menuContentModel.entries) {
            if (entry.icon != null) {
                atLeastOneButtonHasIcon = true
            }
            if (entry.isActionToggle) {
                atLeastOneButtonHasIcon = true
            }
        }

        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val itemButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.itemPresentationState,
            iconActiveFilterStrategy = IconFilterStrategy.Original,
            iconEnabledFilterStrategy = IconFilterStrategy.Original,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
            forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
            popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
            popupFireTrigger = menuPresentationModel.itemPopupFireTrigger,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Leading,
            contentPadding = CommandButtonSizingConstants.CompactButtonContentPadding,
            selectedStateHighlight = menuPresentationModel.itemSelectedStateHighlight,
            sides = Sides.ClosedRectangle
        )

        val layoutManager: CommandButtonLayoutManager =
            itemButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        var maxWidth = 0.0f
        var combinedHeight = 0.0f
        for (entry in menuContentModel.entries) {
            val preferredSize = layoutManager.getPreferredSize(
                command = entry,
                presentationModel = itemButtonPresentationModel,
                preLayoutInfo = layoutManager.getPreLayoutInfo(
                    command = entry,
                    presentationModel = itemButtonPresentationModel
                ),
                buttonShaper = buttonShaper
            )
            maxWidth = max(maxWidth, preferredSize.width)
            combinedHeight += preferredSize.height
        }

        return CustomPopupContentLayoutInfo(
            popupSize = Size(
                width = maxWidth,
                height = combinedHeight
            ),
            itemButtonPresentationModel = itemButtonPresentationModel
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: CustomMenuContentModel,
        menuPresentationModel: CustomCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: CustomPopupContentLayoutInfo
    ) {
        val itemButtonPresentationModel = popupContentLayoutInfo.itemButtonPresentationModel

        val neutralColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Column(
            modifier = Modifier.fillMaxSize().background(color = neutralColorTokens.containerSurface)
                .padding(all = 1.0.dp)
        ) {
            for (entry in menuContentModel.entries) {
                // Check if we have a presentation overlay for this secondary command
                val hasOverlay = overlays.containsKey(entry)
                val currSecondaryPresentationModel = if (hasOverlay)
                    itemButtonPresentationModel.overlayWith(overlays[entry]!!)
                else itemButtonPresentationModel
                // Project a command button for each secondary command, passing the same
                // overlays into it.
                CommandButtonProjection(
                    contentModel = entry,
                    presentationModel = currSecondaryPresentationModel,
                    secondaryOverlays = overlays
                ).project(
                    modifier = Modifier.fillMaxWidth(),
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() }
                )
            }
        }
    }
}

class CustomCommandButtonProjection(
    contentModel: CustomCommand,
    presentationModel: CustomCommandButtonPresentationModel = CustomCommandButtonPresentationModel(),
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<CustomCommand, CustomCommandButtonPresentationModel, CustomCommandButtonProjection>(
    contentModel, presentationModel, secondaryOverlays
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = CustomCommandMenuPopupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): CustomCommandButtonProjection {
        return CustomCommandButtonProjection(
            contentModel = this.contentModel,
            presentationModel = this.presentationModel.overlayWith(primaryOverlay),
            secondaryOverlays = this.secondaryOverlays
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = remember { MutableInteractionSource() },
            popupHandler = CustomCommandMenuPopupHandler,
        )
    }

    @Composable
    override fun reproject(
        modifier: Modifier,
        primaryOverlay: BaseCommandButtonPresentationModel.Overlay,
        actionInteractionSource: MutableInteractionSource,
        popupInteractionSource: MutableInteractionSource
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = primaryOverlay,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = CustomCommandMenuPopupHandler,
        )
    }
}
