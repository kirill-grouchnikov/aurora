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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.*

object CommandButtonSizingConstants {
    val WideButtonContentPadding = PaddingValues(start = 10.dp, top = 3.dp, end = 10.dp, bottom = 4.dp)
    val CompactButtonContentPadding = PaddingValues(start = 6.dp, top = 3.dp, end = 6.dp, bottom = 4.dp)

    val PopupIconWidth = 6.0.dp
    val PopupIconHeight = 4.0.dp
    val DefaultHorizontalContentLayoutGap = 2.0.dp
    val DefaultVerticalContentLayoutGap = 2.0.dp
    val DefaultHorizontalIconTextLayoutGap = 4.0.dp
    val DefaultVerticalIconTextLayoutGap = 4.0.dp
}

object CommandButtonInteractionConstants {
    const val DefaultAutoRepeatInitialIntervalMillis = 500L
    const val DefaultAutoRepeatSubsequentIntervalMillis = 100L
}

enum class ActionFireTrigger {
    /** Fire action on rollover */
    OnRollover,

    /** Fire action on press. */
    OnPressed,

    /** Fire action on press release. */
    OnPressReleased
}

enum class PopupFireTrigger {
    /** Activate popup on rollover */
    OnRollover,

    /** Activate popup on press. */
    OnPressed
}

enum class SelectedStateHighlight {
    /** Selected state highlight is displayed around the button icon */
    IconOnly,

    /** Selected state highlight is displayed over the full button area */
    FullSize
}

interface BaseCommandButtonPresentationModel : PresentationModel {
    val presentationState: CommandButtonPresentationState
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider?
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy
    val horizontalAlignment: HorizontalAlignment
    val iconDimension: DpSize?
    val iconDisabledFilterStrategy: IconFilterStrategy
    val iconEnabledFilterStrategy: IconFilterStrategy
    val iconActiveFilterStrategy: IconFilterStrategy
    val forceAllocateSpaceForIcon: Boolean
    val textStyle: TextStyle?
    val textOverflow: TextOverflow
    val popupPlacementStrategy: PopupPlacementStrategy
    val toDismissPopupsOnActivation: Boolean
    val showPopupIcon: Boolean
    val actionKeyTip: String?
    val popupKeyTip: String?
    val autoRepeatAction: Boolean
    val autoRepeatInitialInterval: Long
    val autoRepeatSubsequentInterval: Long
    val actionFireTrigger: ActionFireTrigger
    val popupFireTrigger: PopupFireTrigger
    val popupMenuPresentationModel: BaseCommandPopupMenuPresentationModel
    val textClick: TextClick
    val actionRichTooltipPresentationModel: RichTooltipPresentationModel
    val popupRichTooltipPresentationModel: RichTooltipPresentationModel
    val popupAnchorBoundsProvider: (() -> Rect)?
    val contentPadding: PaddingValues
    val horizontalGapScaleFactor: Float
    val verticalGapScaleFactor: Float
    val selectedStateHighlight: SelectedStateHighlight
    val minWidth: Dp
    val sides: Sides

    data class Overlay(
        val presentationState: CommandButtonPresentationState? = null,
        val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
        val backgroundAppearanceStrategy: BackgroundAppearanceStrategy? = null,
        val horizontalAlignment: HorizontalAlignment? = null,
        val iconDimension: DpSize? = null,
        val iconDisabledFilterStrategy: IconFilterStrategy? = null,
        val iconEnabledFilterStrategy: IconFilterStrategy? = null,
        val iconActiveFilterStrategy: IconFilterStrategy? = null,
        val forceAllocateSpaceForIcon: Boolean? = null,
        val textStyle: TextStyle? = null,
        val textOverflow: TextOverflow? = null,
        val popupPlacementStrategy: PopupPlacementStrategy? = null,
        val popupAnchorBoundsProvider: (() -> Rect)? = null,
        val toDismissPopupsOnActivation: Boolean? = null,
        val showPopupIcon: Boolean? = null,
        val actionKeyTip: String? = null,
        val popupKeyTip: String? = null,
        val autoRepeatAction: Boolean? = null,
        val autoRepeatInitialInterval: Long? = null,
        val autoRepeatSubsequentInterval: Long? = null,
        val actionFireTrigger: ActionFireTrigger? = null,
        val popupFireTrigger: PopupFireTrigger? = null,
        val popupMenuPresentationModel: BaseCommandPopupMenuPresentationModel? = null,
        val textClick: TextClick? = null,
        val actionRichTooltipPresentationModel: RichTooltipPresentationModel? = null,
        val popupRichTooltipPresentationModel: RichTooltipPresentationModel? = null,
        val contentPadding: PaddingValues? = null,
        val horizontalGapScaleFactor: Float? = null,
        val verticalGapScaleFactor: Float? = null,
        val selectedStateHighlight: SelectedStateHighlight? = null,
        val minWidth: Dp? = null,
        val sides: Sides? = null
    )

    fun overlayWith(overlay: Overlay): BaseCommandButtonPresentationModel
}

data class CommandButtonPresentationModel(
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
    override val actionKeyTip: String? = null,
    override val popupKeyTip: String? = null,
    override val autoRepeatAction: Boolean = false,
    override val autoRepeatInitialInterval: Long = CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis,
    override val autoRepeatSubsequentInterval: Long = CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis,
    override val actionFireTrigger: ActionFireTrigger = ActionFireTrigger.OnPressReleased,
    override val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed,
    override val popupMenuPresentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel(),
    override val textClick: TextClick = TextClick.Action,
    override val actionRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    override val popupRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    override val horizontalGapScaleFactor: Float = 1.0f,
    override val verticalGapScaleFactor: Float = 1.0f,
    override val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize,
    override val minWidth: Dp = 0.dp,
    override val sides: Sides = Sides()
) : BaseCommandButtonPresentationModel {
    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): CommandButtonPresentationModel {
        return CommandButtonPresentationModel(
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
            actionKeyTip = overlay.actionKeyTip ?: this.actionKeyTip,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            autoRepeatAction = overlay.autoRepeatAction ?: this.autoRepeatAction,
            autoRepeatInitialInterval = overlay.autoRepeatInitialInterval ?: this.autoRepeatInitialInterval,
            autoRepeatSubsequentInterval = overlay.autoRepeatSubsequentInterval ?: this.autoRepeatSubsequentInterval,
            actionFireTrigger = overlay.actionFireTrigger ?: this.actionFireTrigger,
            popupFireTrigger = overlay.popupFireTrigger ?: this.popupFireTrigger,
            popupMenuPresentationModel = (overlay.popupMenuPresentationModel as? CommandPopupMenuPresentationModel)
                ?: this.popupMenuPresentationModel,
            textClick = overlay.textClick ?: this.textClick,
            actionRichTooltipPresentationModel = overlay.actionRichTooltipPresentationModel
                ?: this.actionRichTooltipPresentationModel,
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

