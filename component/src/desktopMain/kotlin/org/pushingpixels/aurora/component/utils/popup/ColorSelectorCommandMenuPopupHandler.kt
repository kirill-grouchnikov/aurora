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
package org.pushingpixels.aurora.component.utils.popup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.utils.CommandMenuHandler
import org.pushingpixels.aurora.component.utils.CommandMenuPopupLayoutInfo

internal data class ColorSelectorPopupContentLayoutInfo(
    override val popupSize: IntSize,
) : CommandMenuPopupLayoutInfo

internal class ColorSelectorCommandMenuPopupHandler : CommandMenuHandler<
        ColorSelectorMenuContentModel, ColorSelectorCommandPopupMenuPresentationModel,
        ColorSelectorPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver
    ): ColorSelectorPopupContentLayoutInfo {

        return ColorSelectorPopupContentLayoutInfo(
            popupSize = IntSize(400, 600),
        )
    }

    @Composable
    override fun generatePopupContent(
        popupMenu: AuroraSwingPopupMenu,
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        toDismissPopupsOnActivation: Boolean,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: ColorSelectorPopupContentLayoutInfo
    ) {
        Box(modifier = Modifier.fillMaxSize())
    }
}
