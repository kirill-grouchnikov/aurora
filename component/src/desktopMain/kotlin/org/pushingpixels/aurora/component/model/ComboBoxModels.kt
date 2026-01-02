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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.utils.ArrowSizingConstants
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

data class ComboBoxContentModel<E>(
    val items: List<E>,
    val selectedItem: E,
    val richTooltip: RichTooltip? = null,
    val enabled: Boolean = true,
    val onTriggerItemSelectedChange: (E) -> Unit
) : ContentModel

object ComboBoxSizingConstants {
    val DefaultComboBoxArrowWidth = ArrowSizingConstants.DefaultSingleArrowWidth
    val DefaultComboBoxArrowHeight = ArrowSizingConstants.DefaultSingleArrowHeight
    val DefaultComboBoxContentArrowGap = 6.dp
    val DefaultComboBoxContentPadding =
        PaddingValues(start = 6.dp, top = 3.dp, end = 6.dp, bottom = 4.dp)
    val DefaultComboBoxIconTextLayoutGap = 4.dp
    val DefaultComboBoxContentWidth = 60.dp
    val DefaultComboBoxContentHeight = 16.dp
}

data class ComboBoxPresentationModel<E>(
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val displayConverter: (E) -> String,
    val displayIconConverter: ((E) -> Painter)? = null,
    val displayIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val displayIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val displayIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val displayPrototype: ((List<E>) -> E)? = null,
    val contentPadding: PaddingValues = ComboBoxSizingConstants.DefaultComboBoxContentPadding,
    val defaultMinSize: DpSize = DpSize(
        width = ComboBoxSizingConstants.DefaultComboBoxContentWidth,
        height = ComboBoxSizingConstants.DefaultComboBoxContentHeight
    ),
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val horizontalGapScaleFactor: Float = 1.0f,
    val textStyle: TextStyle? = null,
    val textOverflow: TextOverflow = TextOverflow.Clip,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    val popupMaxVisibleItems: Int = 8,
    val popupDisplayPrototype: ((List<E>) -> E)? = null,
    val richTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel()
) : PresentationModel
