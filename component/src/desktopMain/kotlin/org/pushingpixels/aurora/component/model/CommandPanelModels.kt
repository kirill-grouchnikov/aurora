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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.PopupPlacementStrategy

data class CommandPanelContentModel(
    val commandGroups: List<CommandGroup>,
    val commandActionPreview: CommandActionPreview? = null
) : ContentModel


enum class PanelLayoutFillMode {
    /** The buttons are laid out in rows respecting the available width. */
    RowFill,

    /** The buttons are laid out in columns respecting the available height. */
    ColumnFill
}

object CommandPanelSizingConstants {
    val DefaultContentPadding = PaddingValues(6.dp)
    val DefaultGap = 4.dp
}

data class CommandPanelPresentationModel(
    val contentPadding: PaddingValues = CommandPanelSizingConstants.DefaultContentPadding,
    val layoutFillMode: PanelLayoutFillMode = PanelLayoutFillMode.RowFill,
    val maxColumns: Int = -1,  // only relevant when layoutFillMode is RowFill
    val maxRows: Int = -1,     // only relevant when layoutFillMode is ColumnFill
    val showGroupLabels: Boolean = true,
    val commandPresentationState: CommandButtonPresentationState,
    val commandIconSize: Dp = 0.dp,
    val commandHorizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward,
    val isMenu: Boolean = false
) : PresentationModel
