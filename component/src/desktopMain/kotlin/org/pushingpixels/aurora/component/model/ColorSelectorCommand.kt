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

import androidx.compose.ui.graphics.Color
import java.util.*

data class ColorSectionModel(
    val title: String,
    val colors: List<Color>
)

sealed interface ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuCommand(val command: Command) : ColorSelectorPopupMenuEntry
data class ColorSelectorPopupMenuSection(val colorSectionModel: ColorSectionModel) : ColorSelectorPopupMenuEntry
data class ColorSelectorPopupMenuSectionWithDerived(val colorSectionModel: ColorSectionModel) :
    ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuRecentsSection(val colorSectionModel: ColorSectionModel) : ColorSelectorPopupMenuEntry

data class ColorSelectorPopupMenuGroupModel(val content: List<ColorSelectorPopupMenuEntry>)

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

data class ColorSelectorPopupMenuContentModel(
    val menuGroups: List<ColorSelectorPopupMenuGroupModel>,
    val onColorPreviewActivated: ColorPreviewListener,
    val onColorActivated: (Color) -> Unit
) : BaseCommandMenuContentModel

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
