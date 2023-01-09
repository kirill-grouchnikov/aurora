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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.projection.RibbonApplicationMenuCommandButtonProjection

enum class PresentationPriority {
    /** Top priority */
    Top,

    /** Medium priority */
    Medium,

    /** Low priority */
    Low
}

infix fun <C : BaseCommand, P: BaseCommandButtonPresentationModel> BaseCommandButtonProjection<C, P>.at(
    that: PresentationPriority): Pair<BaseCommandButtonProjection<C, P>, PresentationPriority> = Pair(this, that)

data class RibbonComponentPresentationModel(
    val caption: String? = null,
    val icon: Painter? = null,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val keyTip: String? = null,
    val isResizingAware: Boolean = false,
) : PresentationModel

data class RibbonGalleryContentModel(
    val icon: Painter? = null,
    val commandGroups: List<CommandGroup>,
    val selectedCommand: Command? = null,
    val extraPopupGroups: List<CommandGroup>,
    val commandAction: ((Command?) -> Unit)? = null,
    val commandActionPreview: CommandActionPreview? = null,
) : ContentModel

data class RibbonGalleryPresentationModel(
    val popupLayoutSpec: MenuPopupPanelLayoutSpec,
    val preferredVisibleCommandCounts: Map<PresentationPriority, Int> = emptyMap(),
    val commandButtonPresentationState: CommandButtonPresentationState,
    val expandKeyTip: String? = null,
) : PresentationModel

class RibbonGalleryProjection(
    val contentModel: RibbonGalleryContentModel,
    val presentationModel: RibbonGalleryPresentationModel
) : Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>()

class RibbonComponentProjection<out C: ContentModel, out P: PresentationModel>(
    val projection: Projection<C, P>,
    val ribbonComponentPresentationModel: RibbonComponentPresentationModel = RibbonComponentPresentationModel()
)

interface OnShowContextualMenuListener {
    fun getContextualMenuContentModel(
        ribbon: Ribbon,
        galleryProjection: RibbonGalleryProjection
    ): CommandMenuContentModel

    fun <C : ContentModel, P : PresentationModel> getContextualMenuContentModel(
        ribbon: Ribbon,
        componentProjection: Projection<C, P>
    ): CommandMenuContentModel

    fun getContextualMenuContentModel(
        ribbon: Ribbon,
        commandProjection: CommandButtonProjection
    ): CommandMenuContentModel

    fun getContextualMenuContentModel(ribbon: Ribbon): CommandMenuContentModel
}

object RibbonApplicationMenuButtonPresentationStates {
    val RibbonAppMenuSecondaryLevel: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Ribbon application menu tile level 2") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                throw UnsupportedOperationException()
            }
        }
}

data class Ribbon(
    val tasks: List<RibbonTask>,
    val selectedTask: RibbonTask,
    val onTaskClick: (RibbonTask) -> Unit,
    val contextualTaskGroups: List<RibbonContextualTaskGroup> = emptyList(),
    val anchoredCommands: List<CommandButtonProjection> = emptyList(),
    val taskbarElements: List<RibbonTaskbarElement> = emptyList(),
    val taskbarKeyTipPolicy: RibbonTaskbarKeyTipPolicy,
    val applicationMenuCommandButtonProjection: RibbonApplicationMenuCommandButtonProjection? = null,
    val isMinimized: Boolean = false,
    val onShowContextualMenuListener: OnShowContextualMenuListener? = null
)
