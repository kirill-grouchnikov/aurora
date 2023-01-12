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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManagerMedium
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

interface BaseCommandPopupMenuPresentationModel {
    val menuPresentationState: CommandButtonPresentationState
}

data class CommandPopupMenuPresentationModel(
    override val menuPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val panelPresentationModel: CommandPopupMenuPanelPresentationModel? = null,
    val menuIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val menuIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val menuIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val menuContentPadding: PaddingValues =
        CommandButtonSizingConstants.CompactButtonContentPadding,
    val maxVisibleMenuCommands: Int = 0,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
    val toDismissOnCommandActivation: Boolean = true
): BaseCommandPopupMenuPresentationModel

private class PopupMenuCommandButtonLayoutManager(
    layoutDirection: LayoutDirection,
    density: Density,
    textStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver
) : CommandButtonLayoutManagerMedium(layoutDirection, density, textStyle, fontFamilyResolver) {
    override fun getIconTextGap(presentationModel: BaseCommandButtonPresentationModel): Dp {
        return super.getIconTextGap(presentationModel) * 2.0f
    }

    companion object {
        fun getLayoutManager(
            layoutDirection: LayoutDirection,
            density: Density,
            textStyle: TextStyle,
            fontFamilyResolver: FontFamily.Resolver
        ): CommandButtonLayoutManagerMedium {
            return PopupMenuCommandButtonLayoutManager(
                layoutDirection, density, textStyle, fontFamilyResolver
            )
        }
    }
}

val DefaultCommandPopupMenuPresentationState: CommandButtonPresentationState =
    object : CommandButtonPresentationState("Popup menu") {
        override fun createLayoutManager(
            layoutDirection: LayoutDirection,
            density: Density,
            textStyle: TextStyle,
            fontFamilyResolver: FontFamily.Resolver
        ): CommandButtonLayoutManager {
            return PopupMenuCommandButtonLayoutManager.getLayoutManager(
                layoutDirection,
                density,
                textStyle,
                fontFamilyResolver
            )
        }
    }