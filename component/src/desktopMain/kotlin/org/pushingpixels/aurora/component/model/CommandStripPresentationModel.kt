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

import androidx.compose.ui.unit.DpSize
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

enum class StripOrientation {
    /**
     * Horizontal strip orientation.
     */
    Horizontal,

    /**
     * Vertical strip orientation.
     */
    Vertical
}

object CommandStripSizingConstants {
    const val DefaultGapScaleFactorPrimaryAxis = 0.75f
    const val DefaultGapScaleFactorSecondaryAxis = 1.0f
}

data class CommandStripPresentationModel(
    val orientation: StripOrientation = StripOrientation.Horizontal,
    val commandPresentationState: CommandButtonPresentationState = CommandButtonPresentationState.Small,
    val horizontalGapScaleFactor: Float = if (orientation == StripOrientation.Horizontal)
        CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
    else CommandStripSizingConstants.DefaultGapScaleFactorSecondaryAxis,
    val verticalGapScaleFactor: Float = if (orientation == StripOrientation.Vertical)
        CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
        else CommandStripSizingConstants.DefaultGapScaleFactorSecondaryAxis,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val iconDimension: DpSize? = null,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    val textClick: TextClick = TextClick.Action,
    val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed,
    val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize
): PresentationModel