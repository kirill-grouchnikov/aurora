/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

sealed interface ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuCommand(val command: Command) : ColorSelectorPopupMenuEntry
data class ColorSelectorPopupMenuSection(
    val title: String,
    val colors: List<Color>
) : ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuSectionWithDerived(
    val title: String,
    val colors: List<Color>,
    val derivedCount: Int
) : ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuRecentsSection(val title: String) : ColorSelectorPopupMenuEntry

/**
 * Listener for tracking color preview events.
 */
interface ColorPreviewListener {
    /**
     * Invoked when the preview of a color in any of the color sections of this model is
     * activated.
     *
     * @param color Color for which the preview has been activated.
     */
    fun onColorPreviewActivated(color: Color)

    /**
     * Invoked when the color preview has been canceled.
     */
    fun onColorPreviewCanceled()
}

object RecentlyUsed {
    private val recentlySelected = LinkedList<Color>()

    @Synchronized
    fun getRecentlyUsedColors(): List<Color> {
        return Collections.unmodifiableList(recentlySelected)
    }

    @Synchronized
    fun addColorToRecentlyUsed(color: Color) {
        // Is it already in?
        if (recentlySelected.contains(color)) {
            // Bump up to the top of the most recent
            recentlySelected.remove(color)
            recentlySelected.addLast(color)
            return
        }
        if (recentlySelected.size == 100) {
            // Too many in history, bump out the least recently used or added
            recentlySelected.removeFirst()
        }
        recentlySelected.addLast(color)
    }
}

object ColorSelectorCommandButtonSizingConstants {
    val DefaultColorCellSize = 12.dp
    val DefaultColorCellGap = 4.dp
}

data class ColorSelectorCommandPopupMenuPresentationModel(
    override val menuPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val colorColumns: Int,
    val colorCellSize: Dp = ColorSelectorCommandButtonSizingConstants.DefaultColorCellSize,
    val colorCellGap: Dp = ColorSelectorCommandButtonSizingConstants.DefaultColorCellGap,
    val sectionTitleTextStyle: TextStyle? = null
): BaseCommandPopupMenuPresentationModel

data class ColorSelectorCommand(
    override val text: String,
    override val extraText: String? = null,
    override val icon: Painter? = null,
    override val secondaryContentModel: ColorSelectorMenuContentModel,
    override val isSecondaryEnabled: Boolean = true,
    override val secondaryRichTooltip: RichTooltip? = null
) : BaseCommand {
    override val action: (() -> Unit)? = null
    override val actionPreview: CommandActionPreview? = null
    override val isActionEnabled: Boolean = false
    override val isActionToggle: Boolean = false
    override val isActionToggleSelected: Boolean = false
    override val actionRichTooltip: RichTooltip? = null
    override val onTriggerActionToggleSelectedChange: ((Boolean) -> Unit)? = null
}

data class ColorSelectorMenuContentModel(
    val entries: List<ColorSelectorPopupMenuEntry>,
    val onColorPreviewActivated: ColorPreviewListener,
    val onColorActivated: (Color) -> Unit
) : BaseCommandMenuContentModel