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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.utils.appmenu.CommandButtonLayoutManagerAppMenuLevel2
import org.pushingpixels.aurora.component.utils.popup.RibbonApplicationMenuPopupHandler
import org.pushingpixels.aurora.theming.*

data class RibbonApplicationMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val groups: List<CommandGroup>,
    val footerCommands: CommandGroup
) : BaseCommandMenuContentModel

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
    override val tag = null
}

object RibbonApplicationMenuButtonPresentationStates {
    val RibbonAppMenuSecondaryLevel: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Ribbon application menu tile level 2") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                return CommandButtonLayoutManagerAppMenuLevel2(
                    layoutDirection,
                    density,
                    textStyle,
                    fontFamilyResolver
                )
            }
        }
}

object RibbonApplicationMenuSizingConstants {
    val DefaultFooterContentPadding = PaddingValues(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 6.dp)
    val DefaultLevel2PanelWidth = 320.dp
    val DefaultLevel1ItemContentPadding = PaddingValues(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)
    val DefaultLevel2ItemContentPadding = PaddingValues(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)
}

data class RibbonApplicationMenuCommandPopupMenuPresentationModel(
    val itemPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val itemPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnRollover,
    val itemSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize,
    val itemIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val itemIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val itemIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val itemSides: Sides = Sides.ClosedRectangle,
    val itemHorizontalAlignment: HorizontalAlignment = HorizontalAlignment.Fill,
    val footerContentPadding: PaddingValues = RibbonApplicationMenuSizingConstants.DefaultFooterContentPadding,
    val level2PanelWidth: Dp = RibbonApplicationMenuSizingConstants.DefaultLevel2PanelWidth,
    val level1ItemContentPadding: PaddingValues = RibbonApplicationMenuSizingConstants.DefaultLevel1ItemContentPadding,
    val level2ItemContentPadding: PaddingValues = RibbonApplicationMenuSizingConstants.DefaultLevel2ItemContentPadding
) : BaseCommandPopupMenuPresentationModel

data class RibbonApplicationMenuCommandButtonPresentationModel(
    override val textStyle: TextStyle? = null,
    override val textOverflow: TextOverflow = TextOverflow.Clip,
    override val popupMenuPresentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel =
        RibbonApplicationMenuCommandPopupMenuPresentationModel(
            itemPresentationState = CommandButtonPresentationState.Tile,
            level1ItemContentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp)
        ),
    override val popupKeyTip: String? = null,
    override val popupRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    override val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed,
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.WideButtonContentPadding,
    override val sides: Sides = Sides()
) : BaseCommandButtonPresentationModel {
    override val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium
    override val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center
    override val iconDimension: DpSize? = null
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val forceAllocateSpaceForIcon: Boolean = false
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
    override val popupAnchorBoundsProvider: (() -> Rect)? = null
    override val showPopupIcon: Boolean = false
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval = CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val toDismissPopupsOnActivation: Boolean = true
    override val horizontalGapScaleFactor: Float = 1.0f
    override val verticalGapScaleFactor: Float = 1.0f
    override val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize
    override val minWidth: Dp = 0.dp

    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): RibbonApplicationMenuCommandButtonPresentationModel {
        return RibbonApplicationMenuCommandButtonPresentationModel(
            textStyle = overlay.textStyle ?: this.textStyle,
            textOverflow = overlay.textOverflow ?: this.textOverflow,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            popupFireTrigger = overlay.popupFireTrigger ?: this.popupFireTrigger,
            popupMenuPresentationModel = (overlay.popupMenuPresentationModel as? RibbonApplicationMenuCommandPopupMenuPresentationModel)
                ?: this.popupMenuPresentationModel,
            popupRichTooltipPresentationModel = overlay.popupRichTooltipPresentationModel
                ?: this.popupRichTooltipPresentationModel,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
            sides = overlay.sides ?: this.sides
        )
    }
}

class RibbonApplicationMenuCommandButtonProjection(
    contentModel: RibbonApplicationMenuCommand,
    presentationModel: RibbonApplicationMenuCommandButtonPresentationModel,
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null,
    val secondaryStates: Map<Command, CommandButtonPresentationState>? = null
) : BaseCommandButtonProjection<RibbonApplicationMenuCommand, RibbonApplicationMenuCommandButtonPresentationModel, RibbonApplicationMenuCommandButtonProjection>(
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
            popupHandler = RibbonApplicationMenuPopupHandler(secondaryStates = secondaryStates),
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): RibbonApplicationMenuCommandButtonProjection {
        return RibbonApplicationMenuCommandButtonProjection(
            contentModel = this.contentModel,
            presentationModel = this.presentationModel.overlayWith(primaryOverlay),
            secondaryOverlays = this.secondaryOverlays
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
            popupHandler = RibbonApplicationMenuPopupHandler(secondaryStates = secondaryStates),
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = remember { MutableInteractionSource() },
            popupHandler = RibbonApplicationMenuPopupHandler(secondaryStates = secondaryStates),
        )
    }
}
