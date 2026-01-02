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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCascadingCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.CascadingCommandMenuHandler
import org.pushingpixels.aurora.component.ribbon.RibbonTask
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper

internal data class RibbonTaskCollapsedMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val ribbonTask: RibbonTask
) : BaseCommandMenuContentModel

internal data class RibbonTaskCollapsedCommandPopupMenuPresentationModel(
    val taskWidth: Int,
    val taskHeight: Int,
) : BaseCommandPopupMenuPresentationModel

internal data class RibbonTaskCollapsedPopupContentLayoutInfo(
    override val popupSize: Size
) : BaseCascadingCommandMenuPopupLayoutInfo

internal object RibbonTaskCollapsedCommandMenuPopupHandler : CascadingCommandMenuHandler<
        RibbonTaskCollapsedMenuContentModel, RibbonTaskCollapsedCommandPopupMenuPresentationModel,
        RibbonTaskCollapsedPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: RibbonTaskCollapsedMenuContentModel,
        menuPresentationModel: RibbonTaskCollapsedCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): RibbonTaskCollapsedPopupContentLayoutInfo {
        return RibbonTaskCollapsedPopupContentLayoutInfo(
            popupSize = Size(
                width = menuPresentationModel.taskWidth.toFloat(),
                height = menuPresentationModel.taskHeight.toFloat()
            )
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: RibbonTaskCollapsedMenuContentModel,
        menuPresentationModel: RibbonTaskCollapsedCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: RibbonTaskCollapsedPopupContentLayoutInfo
    ) {
        val neutralColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Box(
            modifier = Modifier.fillMaxSize().background(color = neutralColorTokens.containerSurface)
                .padding(all = 1.0.dp)
        ) {
            RibbonBands(ribbonTask = menuContentModel.ribbonTask)
        }
    }
}
