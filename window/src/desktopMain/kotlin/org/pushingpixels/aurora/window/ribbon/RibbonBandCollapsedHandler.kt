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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCascadingCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.CascadingCommandMenuHandler
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.AbstractRibbonBand
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper

internal data class RibbonBandCollapsedCommand(
    override val text: String,
    override val icon: Painter?,
    override val secondaryContentModel: RibbonBandCollapsedMenuContentModel,
) : BaseCommand {
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

internal data class RibbonBandCollapsedMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val ribbonBand: AbstractRibbonBand
) : BaseCommandMenuContentModel

internal data class RibbonBandCollapsedCommandPopupMenuPresentationModel(
    val bandWidth: Int,
    val bandContentHeight: Int,
    val bandFullHeight: Int,
    val gap: Int
) : BaseCommandPopupMenuPresentationModel

internal data class RibbonBandCollapsedCommandButtonPresentationModel(
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val popupKeyTip: String? = null,
    override val popupMenuPresentationModel: RibbonBandCollapsedCommandPopupMenuPresentationModel,
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
) : BaseCommandButtonPresentationModel {
    override val presentationState = CommandButtonPresentationState.Big
    override val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center
    override val iconDimension: DpSize? = null
    override val forceAllocateSpaceForIcon = false
    override val textStyle: TextStyle? = null
    override val textOverflow: TextOverflow = TextOverflow.Clip
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
    override val toDismissPopupsOnActivation: Boolean = true
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
    override val showPopupIcon: Boolean = true
    override val minWidth: Dp = 0.dp
    override val sides: Sides = Sides()

    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): RibbonBandCollapsedCommandButtonPresentationModel {
        return RibbonBandCollapsedCommandButtonPresentationModel(
            backgroundAppearanceStrategy = overlay.backgroundAppearanceStrategy
                ?: this.backgroundAppearanceStrategy,
            iconDisabledFilterStrategy = overlay.iconDisabledFilterStrategy ?: this.iconDisabledFilterStrategy,
            iconEnabledFilterStrategy = overlay.iconEnabledFilterStrategy ?: this.iconEnabledFilterStrategy,
            iconActiveFilterStrategy = overlay.iconActiveFilterStrategy ?: this.iconActiveFilterStrategy,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            popupMenuPresentationModel = (overlay.popupMenuPresentationModel as? RibbonBandCollapsedCommandPopupMenuPresentationModel)
                ?: this.popupMenuPresentationModel,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
        )
    }
}

internal data class RibbonBandCollapsedPopupContentLayoutInfo(
    override val popupSize: Size
) : BaseCascadingCommandMenuPopupLayoutInfo

internal object RibbonBandCollapsedCommandMenuPopupHandler : CascadingCommandMenuHandler<
        RibbonBandCollapsedMenuContentModel, RibbonBandCollapsedCommandPopupMenuPresentationModel,
        RibbonBandCollapsedPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: RibbonBandCollapsedMenuContentModel,
        menuPresentationModel: RibbonBandCollapsedCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): RibbonBandCollapsedPopupContentLayoutInfo {
        return RibbonBandCollapsedPopupContentLayoutInfo(
            popupSize = Size(
                width = menuPresentationModel.bandWidth.toFloat(),
                height = menuPresentationModel.bandFullHeight.toFloat()
            )
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: RibbonBandCollapsedMenuContentModel,
        menuPresentationModel: RibbonBandCollapsedCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: RibbonBandCollapsedPopupContentLayoutInfo
    ) {
        val neutralColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Box(
            modifier = Modifier.fillMaxSize().background(color = neutralColorTokens.containerSurface)
                .padding(all = 1.0.dp)
        ) {
            RibbonBand(
                band = menuContentModel.ribbonBand,
                bandResizePolicy = menuContentModel.ribbonBand.resizePolicies[0],
                bandContentHeight = menuPresentationModel.bandContentHeight,
                bandFullHeight = menuPresentationModel.bandFullHeight
            )
        }
    }
}

internal class RibbonBandCollapsedCommandButtonProjection(
    contentModel: RibbonBandCollapsedCommand,
    presentationModel: RibbonBandCollapsedCommandButtonPresentationModel,
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<RibbonBandCollapsedCommand, RibbonBandCollapsedCommandButtonPresentationModel, RibbonBandCollapsedCommandButtonProjection>(
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
            popupHandler = RibbonBandCollapsedCommandMenuPopupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): RibbonBandCollapsedCommandButtonProjection {
        return RibbonBandCollapsedCommandButtonProjection(
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
            popupHandler = RibbonBandCollapsedCommandMenuPopupHandler,
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
            popupHandler = RibbonBandCollapsedCommandMenuPopupHandler,
        )
    }
}
