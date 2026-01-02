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
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay

data class SelectorContentModel(
    val text: String,
    val richTooltip: RichTooltip? = null,
    val enabled: Boolean = true,
    val selected: Boolean = false,
    val onClick: () -> Unit
): ContentModel

data class TriStateSelectorContentModel(
    val text: String,
    val richTooltip: RichTooltip? = null,
    val enabled: Boolean = true,
    val state: ToggleableState = ToggleableState.Off,
    val onClick: () -> Unit
): ContentModel

data class SwitchContentModel(
    val richTooltip: RichTooltip? = null,
    val enabled: Boolean = true,
    val selected: Boolean = false,
    val onClick: () -> Unit
): ContentModel

object SelectorSizingConstants {
    val SelectorMarkSize = 14.dp
    val SelectorMarkTextGap = 4.dp
    val DefaultSelectorContentPadding = PaddingValues(start = 4.dp, top = 6.dp, end = 4.dp, bottom = 4.dp)
    val SwitchTrackWidth = 36.dp
    val SwitchTrackHeight = 18.dp
    val SwitchThumbSizeOn = 12.dp
    val SwitchThumbSizeOff = 10.dp
}

data class SelectorPresentationModel(
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val contentPadding: PaddingValues = SelectorSizingConstants.DefaultSelectorContentPadding,
    val markSize: Dp = SelectorSizingConstants.SelectorMarkSize,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val horizontalGapScaleFactor: Float = 1.0f,
    val richTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel()
): PresentationModel

data class SwitchPresentationModel(
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val contentPadding: PaddingValues = SelectorSizingConstants.DefaultSelectorContentPadding,
    val trackSize: DpSize = DpSize(width = SelectorSizingConstants.SwitchTrackWidth,
        height = SelectorSizingConstants.SwitchTrackHeight),
    val thumbSizeOn: Dp = SelectorSizingConstants.SwitchThumbSizeOn,
    val thumbSizeOff: Dp = SelectorSizingConstants.SwitchThumbSizeOff,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val richTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel()
): PresentationModel
