/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.theming.Sides

object CommandButtonSizingConstants {
    val WideButtonContentPadding = PaddingValues(start = 10.dp, top = 3.dp, end = 10.dp, bottom = 4.dp)
    val CompactButtonContentPadding = PaddingValues(start = 6.dp, top = 3.dp, end = 6.dp, bottom = 4.dp)
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

data class CommandButtonPresentationModel(
    val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val iconDimension: Dp? = null,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val forceAllocateSpaceForIcon: Boolean = false,
    val textStyle: TextStyle? = null,
    val textOverflow: TextOverflow = TextOverflow.Clip,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    val toDismissPopupsOnActivation: Boolean = true,
    val actionKeyTip: String? = null,
    val popupKeyTip: String? = null,
    val autoRepeatAction: Boolean = false,
    val autoRepeatInitialInterval: Long = CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis,
    val autoRepeatSubsequentInterval: Long = CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis,
    val actionFireTrigger: ActionFireTrigger = ActionFireTrigger.OnPressReleased,
    val popupMenuPresentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel(),
    val textClick: TextClick = TextClick.Action,
    val actionRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    val popupRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    val horizontalGapScaleFactor: Float = 1.0f,
    val verticalGapScaleFactor: Float = 1.0f,
    val minWidth: Dp = 0.dp,
    val isMenu: Boolean = false,
    val sides : Sides = Sides()
): PresentationModel {
    data class Overlay(
        val presentationState: CommandButtonPresentationState? = null,
        val backgroundAppearanceStrategy: BackgroundAppearanceStrategy? = null,
        val horizontalAlignment: HorizontalAlignment? = null,
        val iconDimension: Dp? = null,
        val iconDisabledFilterStrategy: IconFilterStrategy? = null,
        val iconEnabledFilterStrategy: IconFilterStrategy? = null,
        val iconActiveFilterStrategy: IconFilterStrategy? = null,
        val forceAllocateSpaceForIcon: Boolean? = null,
        val textStyle: TextStyle? = null,
        val textOverflow: TextOverflow? = null,
        val popupPlacementStrategy: PopupPlacementStrategy? = null,
        val toDismissPopupsOnActivation: Boolean? = null,
        val actionKeyTip: String? = null,
        val popupKeyTip: String? = null,
        val autoRepeatAction: Boolean? = null,
        val autoRepeatInitialInterval: Long? = null,
        val autoRepeatSubsequentInterval: Long? = null,
        val actionFireTrigger: ActionFireTrigger? = null,
        val popupMenuPresentationModel: CommandPopupMenuPresentationModel? = null,
        val textClick: TextClick? = null,
        val actionRichTooltipPresentationModel: RichTooltipPresentationModel? = null,
        val popupRichTooltipPresentationModel: RichTooltipPresentationModel? = null,
        val contentPadding: PaddingValues? = null,
        val horizontalGapScaleFactor: Float? = null,
        val verticalGapScaleFactor: Float? = null,
        val minWidth: Dp? = null,
        val isMenu: Boolean? = null,
        val sides : Sides? = null
    )

    fun overlayWith(overlay: Overlay): CommandButtonPresentationModel {
        return CommandButtonPresentationModel(
            presentationState = overlay.presentationState ?: this.presentationState,
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
            toDismissPopupsOnActivation = overlay.toDismissPopupsOnActivation ?: this.toDismissPopupsOnActivation,
            actionKeyTip = overlay.actionKeyTip ?: this.actionKeyTip,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            autoRepeatAction = overlay.autoRepeatAction ?: this.autoRepeatAction,
            autoRepeatInitialInterval = overlay.autoRepeatInitialInterval ?: this.autoRepeatInitialInterval,
            autoRepeatSubsequentInterval = overlay.autoRepeatSubsequentInterval ?: this.autoRepeatSubsequentInterval,
            actionFireTrigger = overlay.actionFireTrigger ?: this.actionFireTrigger,
            popupMenuPresentationModel = overlay.popupMenuPresentationModel ?: this.popupMenuPresentationModel,
            textClick = overlay.textClick ?: this.textClick,
            actionRichTooltipPresentationModel = overlay.actionRichTooltipPresentationModel ?: this.actionRichTooltipPresentationModel,
            popupRichTooltipPresentationModel = overlay.popupRichTooltipPresentationModel ?: this.popupRichTooltipPresentationModel,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
            horizontalGapScaleFactor = overlay.horizontalGapScaleFactor ?: this.horizontalGapScaleFactor,
            verticalGapScaleFactor = overlay.verticalGapScaleFactor ?: this.verticalGapScaleFactor,
            minWidth = overlay.minWidth ?: this.minWidth,
            isMenu = overlay.isMenu ?: this.isMenu,
            sides = overlay.sides ?: this.sides
        )
    }
}