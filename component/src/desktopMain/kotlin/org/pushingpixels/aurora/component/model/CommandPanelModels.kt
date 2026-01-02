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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

data class CommandPanelContentModel(val commandGroups: List<CommandGroup>) : ContentModel

object CommandPanelSizingConstants {
    val DefaultContentPadding = PaddingValues(6.dp)
    val DefaultGap = 4.dp
}

sealed class PanelRowFillSpec {
    class Fixed(val columnCount: Int) : PanelRowFillSpec()
    class Adaptive(val minColumnWidth: Dp) : PanelRowFillSpec()

    override fun hashCode() =
        when (this) {
            is Fixed -> 31 + columnCount
            is Adaptive -> 62 + minColumnWidth.hashCode()
        }

    override fun equals(other: Any?) =
        (this is Fixed && other is Fixed && this.columnCount == other.columnCount) ||
                (this is Adaptive && other is Adaptive && this.minColumnWidth == other.minColumnWidth)
}

sealed class PanelColumnFillSpec {
    class Fixed(val rowCount: Int) : PanelColumnFillSpec()
    class Adaptive(val minRowHeight: Dp) : PanelColumnFillSpec()

    override fun hashCode() =
        when (this) {
            is Fixed -> 31 + rowCount
            is Adaptive -> 62 + minRowHeight.hashCode()
        }

    override fun equals(other: Any?) =
        (this is Fixed && other is Fixed && this.rowCount == other.rowCount) ||
                (this is Adaptive && other is Adaptive && this.minRowHeight == other.minRowHeight)
}

sealed class PanelLayoutSpec {
    class RowFill(val rowFillSpec: PanelRowFillSpec) : PanelLayoutSpec()
    class ColumnFill(val columnFillSpec: PanelColumnFillSpec) : PanelLayoutSpec()
}

data class CommandPanelPresentationModel(
    val layoutSpec: PanelLayoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Adaptive(48.dp)),
    val contentPadding: PaddingValues = CommandPanelSizingConstants.DefaultContentPadding,
    val contentGap: Dp = CommandPanelSizingConstants.DefaultGap,
    val showGroupLabels: Boolean = true,
    val useStickyGroupLabels: Boolean = false,
    val commandPresentationState: CommandButtonPresentationState,
    val commandIconDimension: DpSize = DpSize.Zero,
    val commandContentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    val commandTextStyle: TextStyle? = null,
    val commandTextOverflow: TextOverflow = TextOverflow.Clip,
    val commandHorizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val commandHorizontalGapScaleFactor: Float = 1.0f,
    val commandVerticalGapScaleFactor: Float = 1.0f,
    val commandPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed,
    val commandSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
) : PresentationModel

data class MenuPopupPanelLayoutSpec(val columnCount: Int, val visibleRowCount: Int)

data class CommandPopupMenuPanelPresentationModel(
    val layoutSpec: MenuPopupPanelLayoutSpec,
    val contentPadding: PaddingValues = CommandPanelSizingConstants.DefaultContentPadding,
    val contentGap: Dp = CommandPanelSizingConstants.DefaultGap,
    val showGroupLabels: Boolean = true,
    val useStickyGroupLabels: Boolean = false,
    val commandPresentationState: CommandButtonPresentationState,
    val commandIconDimension: DpSize = DpSize.Zero,
    val commandContentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    val commandTextStyle: TextStyle? = null,
    val commandTextOverflow: TextOverflow = TextOverflow.Clip,
    val commandHorizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val commandHorizontalGapScaleFactor: Float = 1.0f,
    val commandVerticalGapScaleFactor: Float = 1.0f,
    val commandPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed,
    val commandSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
) : PresentationModel {
    fun toCommandPanelPresentationModel(): CommandPanelPresentationModel {
        return CommandPanelPresentationModel(
            layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Fixed(this.layoutSpec.columnCount)),
            contentPadding = this.contentPadding,
            contentGap = this.contentGap,
            showGroupLabels = this.showGroupLabels,
            useStickyGroupLabels = this.useStickyGroupLabels,
            commandPresentationState = this.commandPresentationState,
            commandIconDimension = this.commandIconDimension,
            commandContentPadding = this.commandContentPadding,
            commandTextStyle = this.commandTextStyle,
            commandTextOverflow = this.commandTextOverflow,
            commandHorizontalAlignment = this.commandHorizontalAlignment,
            commandHorizontalGapScaleFactor = this.commandHorizontalGapScaleFactor,
            commandVerticalGapScaleFactor = this.commandVerticalGapScaleFactor,
            commandPopupFireTrigger = this.commandPopupFireTrigger,
            commandSelectedStateHighlight = this.commandSelectedStateHighlight,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            iconDisabledFilterStrategy = this.iconDisabledFilterStrategy,
            iconEnabledFilterStrategy = this.iconEnabledFilterStrategy,
            iconActiveFilterStrategy = this.iconActiveFilterStrategy
        )
    }
}
