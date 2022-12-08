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

sealed class AbstractRibbonBand(
    val title: String,
    val icon: Painter? = null
)

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
)

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

public class RibbonBand(
    title: String,
    icon: Painter? = null
) : AbstractRibbonBand(title, icon) {
    public fun addRibbonCommand(command: Command, presentationModel: RibbonCommandButtonPresentationModel) {}
    public fun addRibbonComponent(contentModel: ContentModel, presentationModel: RibbonComponentPresentationModel) {}
    public fun addRibbonGallery(contentModel: RibbonGalleryContentModel, presentationModel: RibbonGalleryPresentationModel) {}
}

public class FlowRibbonBand(
    title: String,
    icon: Painter? = null
) : AbstractRibbonBand(title, icon) {
    public fun addFlowComponent(contentModel: ContentModel, presentationModel: RibbonComponentPresentationModel) {}
}

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

public class RibbonTask(
    title: String,
    bands: List<AbstractRibbonBand>,
    resizeSequencingPolicy: RibbonBandResizeSequencingPolicy,
    keyTip: String? = null
)

public class RibbonContextualTaskGroup(
    title: String,
    hueColor: Color,
    tasks: List<RibbonTask>
)

data class RibbonTaskbarCommandButtonPresentationModel(
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupMenuPresentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel()
)

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
        ribbon: Ribbon, galleryProjection: RibbonGalleryProjection
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

public class Ribbon {
    public fun addTask(task: RibbonTask) {}
    public fun addContextualTaskGroup(contextualTaskGroup: RibbonContextualTaskGroup) {}
    public fun setSelectedTask(task: RibbonTask) {}

    public fun addAnchoredCommand(commandButtonProjection: CommandButtonProjection) {}
    public fun removeAnchoredCommand(commandButtonProjection: CommandButtonProjection) {}

    public fun addTaskbarCommand(command: Command, presentationModel: RibbonTaskbarCommandButtonPresentationModel) {}
    public fun <C: ContentModel, P: PresentationModel> addTaskbarComponent(projection: Projection<C, P>) {}
    public fun addTaskbarGalleryDropdown(contentModel: RibbonGalleryContentModel, presentationModel: RibbonGalleryPresentationModel) {}
    public fun removeTaskbarCommand(command: Command) {}
    public fun removeTaskbarComponent(contentModel: ContentModel) {}
    public fun removeTaskbarGallery(contentModel: RibbonGalleryContentModel) {}
    public fun isShowingInTaskbar(command: Command): Boolean = false
    public fun isShowingInTaskbar(contentModel: ContentModel): Boolean = false
    public fun isShowingInTaskbar(contentModel: RibbonGalleryContentModel): Boolean = false

    public fun setTaskbarKeyTipPolicy(policy: RibbonTaskbarKeyTipPolicy) {}

    public fun setMinimized(isMinimized: Boolean) {}
    public fun setOnShowContextualMenuListener(onShowContextualMenuListener: OnShowContextualMenuListener) {}
}