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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.*
import java.util.*

sealed interface ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuCommand(val command: Command) : ColorSelectorPopupMenuEntry
data class ColorSelectorPopupMenuSection(
    val title: String,
    val colors: List<Color>
) : ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuSectionWithDerived(
    val title: String,
    val colors: List<Color>,
    val derivedCount: Int
) : ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuRecentsSection(val title: String) : ColorSelectorPopupMenuEntry

/**
 * Listener for tracking color preview events.
 */
interface ColorPreviewListener {
    /**
     * Invoked when the preview of a color in any of the color sections of this model is
     * activated.
     *
     * @param color Color for which the preview has been activated.
     */
    fun onColorPreviewActivated(color: Color)

    /**
     * Invoked when the color preview has been canceled.
     */
    fun onColorPreviewCanceled(color: Color)
}

object RecentlyUsedColors {
    private val recentlyUsedList = LinkedList<Color>()

    @Synchronized
    fun getRecentlyUsed(): List<Color> {
        return Collections.unmodifiableList(recentlyUsedList)
    }

    @Synchronized
    fun addToRecentlyUsed(color: Color) {
        // Is it already in?
        if (recentlyUsedList.contains(color)) {
            // Bump up to the top of the most recent
            recentlyUsedList.remove(color)
            recentlyUsedList.addFirst(color)
            return
        }
        if (recentlyUsedList.size == 100) {
            // Too many in history, bump out the least recently used or added
            recentlyUsedList.removeLast()
        }
        recentlyUsedList.addFirst(color)
    }
}

object ColorSelectorCommandButtonSizingConstants {
    val DefaultColorCellSize = 12.dp
    val DefaultColorCellGap = 4.dp
    val DefaultSectionContentPadding = PaddingValues(all = 4.dp)
}

data class ColorSelectorCommandPopupMenuPresentationModel(
    val itemPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val itemPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnRollover,
    val itemSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.IconOnly,
    val itemSides: Sides = Sides.ClosedRectangle,
    val colorColumns: Int,
    val colorCellSize: Dp = ColorSelectorCommandButtonSizingConstants.DefaultColorCellSize,
    val colorCellGap: Dp = ColorSelectorCommandButtonSizingConstants.DefaultColorCellGap,
    val sectionTitleTextStyle: TextStyle? = null,
    val sectionContentPadding: PaddingValues = ColorSelectorCommandButtonSizingConstants.DefaultSectionContentPadding
) : BaseCommandPopupMenuPresentationModel

data class ColorSelectorCommand(
    override val text: String,
    override val extraText: String? = null,
    override val icon: Painter? = null,
    override val secondaryContentModel: ColorSelectorMenuContentModel,
    override val isSecondaryEnabled: Boolean = true,
    override val secondaryRichTooltip: RichTooltip? = null,
    override val tag: Any? = null,
) : BaseCommand {
    override val action = null
    override val actionPreview = null
    override val isActionEnabled = false
    override val isActionToggle = false
    override val isActionToggleSelected = false
    override val actionRichTooltip = null
    override val onTriggerActionToggleSelectedChange = null
}

data class ColorSelectorMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val entries: List<ColorSelectorPopupMenuEntry>,
    val onColorPreviewActivated: ColorPreviewListener,
    val onColorActivated: (Color) -> Unit
) : BaseCommandMenuContentModel

data class ColorSelectorCommandButtonPresentationModel(
    override val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium,
    override val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    override val iconDimension: DpSize? = null,
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val forceAllocateSpaceForIcon: Boolean = false,
    override val textStyle: TextStyle? = null,
    override val textOverflow: TextOverflow = TextOverflow.Clip,
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    override val popupAnchorBoundsProvider: (() -> Rect)? = null,
    override val toDismissPopupsOnActivation: Boolean = true,
    override val showPopupIcon: Boolean = true,
    override val popupKeyTip: String? = null,
    override val popupMenuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel =
        ColorSelectorCommandPopupMenuPresentationModel(colorColumns = 10),
    override val popupRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    override val horizontalGapScaleFactor: Float = 1.0f,
    override val verticalGapScaleFactor: Float = 1.0f,
    override val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed,
    override val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize,
    override val minWidth: Dp = 0.dp,
    override val sides: Sides = Sides()
) : BaseCommandButtonPresentationModel {
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval = CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval = CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()

    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): ColorSelectorCommandButtonPresentationModel {
        return ColorSelectorCommandButtonPresentationModel(
            presentationState = overlay.presentationState ?: this.presentationState,
            colorTokensOverlayProvider = overlay.colorTokensOverlayProvider ?: this.colorTokensOverlayProvider,
            backgroundAppearanceStrategy = overlay.backgroundAppearanceStrategy
                ?: this.backgroundAppearanceStrategy,
            horizontalAlignment = overlay.horizontalAlignment ?: this.horizontalAlignment,
            iconDimension = overlay.iconDimension ?: this.iconDimension,
            iconDisabledFilterStrategy = overlay.iconDisabledFilterStrategy ?: this.iconDisabledFilterStrategy,
            iconEnabledFilterStrategy = overlay.iconEnabledFilterStrategy ?: this.iconEnabledFilterStrategy,
            iconActiveFilterStrategy = overlay.iconActiveFilterStrategy ?: this.iconActiveFilterStrategy,
            forceAllocateSpaceForIcon = overlay.forceAllocateSpaceForIcon ?: this.forceAllocateSpaceForIcon,
            textStyle = overlay.textStyle ?: this.textStyle,
            textOverflow = overlay.textOverflow ?: this.textOverflow,
            popupPlacementStrategy = overlay.popupPlacementStrategy ?: this.popupPlacementStrategy,
            popupAnchorBoundsProvider = overlay.popupAnchorBoundsProvider ?: this.popupAnchorBoundsProvider,
            toDismissPopupsOnActivation = overlay.toDismissPopupsOnActivation ?: this.toDismissPopupsOnActivation,
            showPopupIcon = overlay.showPopupIcon ?: this.showPopupIcon,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            popupFireTrigger = overlay.popupFireTrigger ?: this.popupFireTrigger,
            popupMenuPresentationModel = (overlay.popupMenuPresentationModel as? ColorSelectorCommandPopupMenuPresentationModel)
                ?: this.popupMenuPresentationModel,
            popupRichTooltipPresentationModel = overlay.popupRichTooltipPresentationModel
                ?: this.popupRichTooltipPresentationModel,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
            horizontalGapScaleFactor = overlay.horizontalGapScaleFactor ?: this.horizontalGapScaleFactor,
            verticalGapScaleFactor = overlay.verticalGapScaleFactor ?: this.verticalGapScaleFactor,
            selectedStateHighlight = overlay.selectedStateHighlight ?: this.selectedStateHighlight,
            minWidth = overlay.minWidth ?: this.minWidth,
            sides = overlay.sides ?: this.sides
        )
    }
}
