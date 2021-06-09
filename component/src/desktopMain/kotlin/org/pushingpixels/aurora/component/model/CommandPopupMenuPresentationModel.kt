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

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.PopupPlacementStrategy
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManagerMedium

data class CommandPopupMenuPresentationModel(
    val panelPresentationModel: CommandPanelPresentationModel? = null,
    val menuPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val maxVisibleMenuCommands: Int = 0,
    val toDismissOnCommandActivation: Boolean = false,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Endward
)

private class PopupMenuCommandButtonLayoutManager(
    layoutDirection: LayoutDirection,
    density: Density,
    textStyle: TextStyle,
    resourceLoader: Font.ResourceLoader
) : CommandButtonLayoutManagerMedium(layoutDirection, density, textStyle, resourceLoader) {
    override val iconTextGapFactor: Float = 2.0f
}

val DefaultCommandPopupMenuPresentationState: CommandButtonPresentationState =
    object : CommandButtonPresentationState("Popup menu") {
        override fun createLayoutManager(
            layoutDirection: LayoutDirection,
            density: Density,
            textStyle: TextStyle,
            resourceLoader: Font.ResourceLoader
        ): CommandButtonLayoutManager {
            return PopupMenuCommandButtonLayoutManager(
                layoutDirection,
                density,
                textStyle,
                resourceLoader
            )
        }
    }