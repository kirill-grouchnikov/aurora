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

sealed class RowFillSpec {
    class Fixed(val count: Int) : RowFillSpec()
    class Adaptive(val minWidth: Dp) : RowFillSpec()

    override fun hashCode() = if (this is Fixed) {
        31 + count
    } else {
        require(this is Adaptive)
        62 + minWidth.hashCode()
    }

    override fun equals(other: Any?) =
        (this is Fixed && other is Fixed && this.count == other.count) ||
                (this is Adaptive && other is Adaptive && this.minWidth == other.minWidth)
}

sealed class ColumnFillSpec {
    class Fixed(val count: Int) : ColumnFillSpec()
    class Adaptive(val minHeight: Dp) : ColumnFillSpec()

    override fun hashCode() = if (this is Fixed) {
        31 + count
    } else {
        require(this is Adaptive)
        62 + minHeight.hashCode()
    }

    override fun equals(other: Any?) =
        (this is Fixed && other is Fixed && this.count == other.count) ||
                (this is Adaptive && other is Adaptive && this.minHeight == other.minHeight)
}

sealed class LayoutSpec {
    class RowFill(val rowFillSpec: RowFillSpec): LayoutSpec()
    class ColumnFill(val columnFillSpec: ColumnFillSpec): LayoutSpec()
}

data class CommandPanelPresentationModel(
    val contentPadding: PaddingValues = CommandPanelSizingConstants.DefaultContentPadding,
    val layoutSpec:  LayoutSpec = LayoutSpec.RowFill(RowFillSpec.Adaptive(48.dp)),
    val maxColumns: Int = -1,  // only relevant when layoutFillMode is RowFill
    val maxRows: Int = -1,     // only relevant when layoutFillMode is ColumnFill
    val showGroupLabels: Boolean = true,
    val commandPresentationState: CommandButtonPresentationState,
    val commandIconDimension: Dp = 0.dp,
    val commandContentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    val commandTextStyle: TextStyle? = null,
    val commandTextOverflow: TextOverflow = TextOverflow.Clip,
    val commandHorizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val commandHorizontalGapScaleFactor: Float = 1.0f,
    val commandVerticalGapScaleFactor: Float = 1.0f,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward,
    val isMenu: Boolean = false
) : PresentationModel
