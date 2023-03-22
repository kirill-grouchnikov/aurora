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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.impl.RibbonGallery

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
    val extraPopupGroups: List<CommandGroup>,
) : ContentModel

object RibbonGallerySizingConstants {
    val DefaultContentPadding = PaddingValues(all = 4.dp)
    val DefaultContentLayoutGap: Dp = 4.dp
}

data class RibbonGalleryPresentationModel(
    val popupLayoutSpec: MenuPopupPanelLayoutSpec,
    val preferredVisibleCommandCounts: Map<PresentationPriority, Int> = emptyMap(),
    val commandButtonPresentationState: CommandButtonPresentationState,
    val commandButtonTextOverflow: TextOverflow = TextOverflow.Clip,
    val contentPadding: PaddingValues = RibbonGallerySizingConstants.DefaultContentPadding,
    val layoutGap: Dp = RibbonGallerySizingConstants.DefaultContentLayoutGap,
    val expandKeyTip: String? = null,
) : PresentationModel

class RibbonGalleryProjection(
    val contentModel: RibbonGalleryContentModel,
    val presentationModel: RibbonGalleryPresentationModel
) : Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>() {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        presentationPriority: PresentationPriority
    ) {
        require((presentationModel.commandButtonPresentationState == CommandButtonPresentationState.Small) ||
                (presentationModel.commandButtonPresentationState == RibbonBandCommandButtonPresentationStates.BigFixed) ||
                (presentationModel.commandButtonPresentationState == RibbonBandCommandButtonPresentationStates.BigFixedLandscape)) {
            "Unsupported command button presentation state ${presentationModel.commandButtonPresentationState}"
        }

        RibbonGallery(
            modifier = modifier,
            presentationPriority = presentationPriority,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

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
