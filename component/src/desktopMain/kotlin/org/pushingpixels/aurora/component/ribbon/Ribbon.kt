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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

enum class PresentationPriority {
    /** Top priority */
    Top,

    /** Medium priority */
    Medium,

    /** Low priority */
    Low
}

sealed interface AbstractRibbonBand {
    val title: String
    val icon: Painter?
}

data class RibbonCommandButtonPresentationModel(
    val presentationPriority: PresentationPriority,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    val textClick: TextClick = TextClick.Action,
    val actionRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    val popupRichTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel(),
    val actionKeyTip: String? = null,
    val popupKeyTip: String? = null
) : PresentationModel

data class RibbonComponentPresentationModel(
    val basePresentationModel: PresentationModel,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val keyTip: String? = null,
    val isResizingAware: Boolean = false,
): PresentationModel

fun PresentationModel.inRibbon(
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    keyTip: String? = null,
    isResizingAware: Boolean = false
): RibbonComponentPresentationModel =
    RibbonComponentPresentationModel(
        basePresentationModel = this,
        horizontalAlignment = horizontalAlignment,
        keyTip = keyTip,
        isResizingAware = isResizingAware
    )

data class RibbonGalleryContentModel(
    val icon: Painter? = null,
    val commandGroups: List<CommandGroup>,
    val extraPopupGroups: List<CommandGroup>,
    val commandAction: (() -> Unit)? = null,
    val commandActionPreview: CommandActionPreview? = null,
): ContentModel

data class RibbonGalleryPresentationModel(
    val popupLayoutSpec: MenuPopupPanelLayoutSpec,
    val expandKeyTip: String? = null,
    val policies: Map<PresentationPriority, Int>
): PresentationModel

class RibbonGalleryProjection(
    val contentModel: RibbonGalleryContentModel,
    val presentationModel: RibbonGalleryPresentationModel
): Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>()

class RibbonCommandButtonProjection(
    val contentModel: Command,
    val presentationModel: RibbonCommandButtonPresentationModel
): Projection<Command, RibbonCommandButtonPresentationModel>()

class RibbonComponentProjection(
    val contentModel: Command,
    val presentationModel: RibbonComponentPresentationModel
): Projection<Command, RibbonComponentPresentationModel>()

data class RibbonBand(
    override val title: String,
    override val icon: Painter? = null,
    val commandProjections: List<RibbonCommandButtonProjection> = emptyList(),
    val componentProjections: List<RibbonComponentProjection> = emptyList(),
    val galleryProjections: List<RibbonGalleryProjection> = emptyList()
) : AbstractRibbonBand

data class FlowRibbonBand(
    override val title: String,
    override val icon: Painter? = null,
    val flowComponentProjections: List<RibbonComponentProjection> = emptyList()
) : AbstractRibbonBand

interface RibbonBandResizeSequencingPolicy {
    /**
     * Resets this policy. Note that this method is for internal use only and
     * should not be called by the application code.
     */
    fun reset()

    /**
     * Returns the next ribbon band for collapse.
     *
     * @return The next ribbon band for collapse.
     */
    operator fun next(): AbstractRibbonBand
}

data class RibbonTask(
    val title: String,
    val bands: List<AbstractRibbonBand>,
    val resizeSequencingPolicy: RibbonBandResizeSequencingPolicy,
    val keyTip: String? = null
)

data class RibbonContextualTaskGroup(
    val title: String,
    val hueColor: Color,
    val tasks: List<RibbonTask>
)

data class RibbonTaskbarCommandButtonPresentationModel(
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupMenuPresentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel()
): PresentationModel

interface RibbonTaskbarKeyTipPolicy {
    /**
     * Returns the keytip for the task bar content (command, component, gallery, menu link)
     * at the specified index.
     *
     * @param contentIndex Index of the task bar content. Content index starts at 1.
     * @return Keytip for the specified content.
     */
    fun getContentKeyTip(contentIndex: Int): String

    /**
     * Returns the keytip for the overflow button of the task bar.
     *
     * @return Keytip for the overflow button of the task bar.
     */
    val overflowButtonKeyTip: String
}

interface OnShowContextualMenuListener {
    fun getContextualMenuContentModel(
        ribbon: Ribbon,
        galleryProjection: RibbonGalleryProjection
    ): CommandMenuContentModel

    fun <C: ContentModel, P: PresentationModel> getContextualMenuContentModel(
        ribbon: Ribbon,
        componentProjection: Projection<C, P>
    ): CommandMenuContentModel

    fun getContextualMenuContentModel(
        ribbon: Ribbon,
        commandProjection: CommandButtonProjection
    ): CommandMenuContentModel

    fun getContextualMenuContentModel(ribbon: Ribbon): CommandMenuContentModel
}

class RibbonTaskbarCommandButtonProjection(
    val contentModel: Command,
    val presentationModel: RibbonTaskbarCommandButtonPresentationModel
): Projection<Command, RibbonTaskbarCommandButtonPresentationModel>()

data class Ribbon(
    val tasks: List<RibbonTask>,
    val selectedTask: RibbonTask,
    val onTaskClick: () -> Unit,
    val contextualTaskGroups: List<RibbonContextualTaskGroup>?,
    val anchoredCommands: List<CommandButtonProjection>?,
    val taskbarCommandProjections: List<RibbonTaskbarCommandButtonProjection> = emptyList(),
    val taskbarComponentProjections: List<RibbonComponentProjection> = emptyList(),
    val taskbarGalleryProjections: List<RibbonGalleryProjection> = emptyList(),
    val taskbarKeyTipPolicy: RibbonTaskbarKeyTipPolicy,
    val isMinimized: Boolean = false,
    val onShowContextualMenuListener: OnShowContextualMenuListener?
)
