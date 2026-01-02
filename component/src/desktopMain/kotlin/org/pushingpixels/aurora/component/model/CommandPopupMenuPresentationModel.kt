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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManagerMedium
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.theming.Sides

interface BaseCommandPopupMenuPresentationModel

data class CommandPopupMenuPresentationModel(
    val itemPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val itemPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnRollover,
    val itemSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.IconOnly,
    val panelPresentationModel: CommandPopupMenuPanelPresentationModel? = null,
    val itemIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val itemIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val itemIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val itemContentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    val itemSides: Sides = Sides.ClosedRectangle,
    val itemHorizontalAlignment: HorizontalAlignment = HorizontalAlignment.Fill,
    val maxVisibleItems: Int = 0,
    val iconGutterFillColorQuery: ((ContainerColorTokens) -> Color)? = null,
    val backgroundFillColorQuery: ((Int, ContainerColorTokens) -> Color) = { _, colorTokens -> colorTokens.containerSurface },
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
    val toDismissOnCommandActivation: Boolean = true,
): BaseCommandPopupMenuPresentationModel

@OptIn(AuroraInternalApi::class)
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

@OptIn(AuroraInternalApi::class)
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