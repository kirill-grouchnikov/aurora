/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.skin.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.skin.PopupPlacementStrategy

data class ComboBoxContentModel<E>(
    val items: List<E>,
    val selectedItem: E,
    val enabled: Boolean = true,
    val onTriggerItemSelectedChange: (E) -> Unit
): ContentModel

object ComboBoxSizingConstants {
    val DefaultComboBoxArrowWidth = 10.dp
    val DefaultComboBoxArrowHeight = 7.dp
    val DefaultComboBoxContentArrowGap = 6.dp
    val DefaultComboBoxContentPadding =
        PaddingValues(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
    val DefaultComboBoxContentWidth = 60.dp
    val DefaultComboBoxContentHeight = 16.dp
}

data class ComboBoxPresentationModel<E>(
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val displayConverter: (E) -> String,
    val contentPadding: PaddingValues = ComboBoxSizingConstants.DefaultComboBoxContentPadding,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val horizontalGapScaleFactor: Float = 1.0f,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward,
    val popupMaxVisibleItems: Int = 8
): PresentationModel
