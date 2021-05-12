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

import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.PopupPlacementStrategy

enum class StripOrientation {
    /**
     * Horizontal strip orientation.
     */
    HORIZONTAL,

    /**
     * Vertical strip orientation.
     */
    VERTICAL
}

data class CommandStripPresentationModel(
    val orientation: StripOrientation = StripOrientation.HORIZONTAL,
    val commandPresentationState: CommandButtonPresentationState = CommandButtonPresentationState.SMALL,
    val horizontalGapScaleFactor: Float =
        if (orientation == StripOrientation.HORIZONTAL) DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
        else DEFAULT_GAP_SCALE_FACTOR_SECONDARY_AXIS,
    val verticalGapScaleFactor: Float =
        if (orientation == StripOrientation.VERTICAL) DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
        else DEFAULT_GAP_SCALE_FACTOR_SECONDARY_AXIS,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.CENTER,
    val iconDimension: Dp? = null,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
    val textClick: TextClick = TextClick.ACTION,
    val isMenu: Boolean = false
) {
    companion object {
        const val DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS = 0.75f
        const val DEFAULT_GAP_SCALE_FACTOR_SECONDARY_AXIS = 1.0f
    }
}