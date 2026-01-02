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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import org.pushingpixels.aurora.component.ribbon.impl.ribbonGalleryIntrinsicWidth
import kotlin.math.min
import kotlin.properties.Delegates

enum class PresentationPriority {
    /** Top priority */
    Top,

    /** Medium priority */
    Medium,

    /** Low priority */
    Low
}

infix fun <C : BaseCommand, P : BaseCommandButtonPresentationModel, CBP : BaseCommandButtonProjection<C, P, CBP>> BaseCommandButtonProjection<C, P, CBP>.at(
    that: PresentationPriority
): Pair<BaseCommandButtonProjection<C, P, CBP>, PresentationPriority> = Pair(this, that)

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
    val commandButtonPresentationState: CommandButtonPresentationState,
    val commandButtonTextOverflow: TextOverflow = TextOverflow.Clip,
    val commandPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnRollover,
    val commandSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize,
    val contentPadding: PaddingValues = RibbonGallerySizingConstants.DefaultContentPadding,
    val layoutGap: Dp = RibbonGallerySizingConstants.DefaultContentLayoutGap,
    val expandKeyTip: String? = null,
    val popupLayoutSpec: MenuPopupPanelLayoutSpec,
    val collapsedVisibleCountLow: Int,
    val collapsedVisibleCountMedium: Int,
    val collapsedVisibleCountTop: Int,
) : PresentationModel

class RibbonGalleryInlineState(
    val contentModel: RibbonGalleryContentModel,
    val presentationModel: RibbonGalleryPresentationModel,
) {
    private val fullCount: Int
        get() = contentModel.commandGroups.sumOf { it.commands.size }
    var visibleCount by Delegates.notNull<Int>()
    lateinit var presentationPriority: PresentationPriority
    var firstVisibleIndex by mutableStateOf(0)
    val lastVisibleIndex: Int
        get() = min(firstVisibleIndex + visibleCount - 1, fullCount - 1)
    private var shouldRevealSelected: Boolean = false

    fun revealSelected() {
        shouldRevealSelected = true
    }

    fun getAndClearRevealSelected(): Boolean {
        val result = shouldRevealSelected
        shouldRevealSelected = false
        return result
    }

    fun revealAt(index: Int) {
        if ((index < 0) || (index >= fullCount)) {
            // out of bounds
            return
        }
        if ((index >= firstVisibleIndex) && (index <= lastVisibleIndex)) {
            // already shown
            return
        }
        if (index > lastVisibleIndex) {
            while (index > lastVisibleIndex) {
                firstVisibleIndex += visibleCount
            }
            return
        }
        if (index < firstVisibleIndex) {
            while (index < firstVisibleIndex) {
                firstVisibleIndex -= visibleCount
            }
        }
    }
}

class RibbonGalleryProjection(
    override val contentModel: RibbonGalleryContentModel,
    override val presentationModel: RibbonGalleryPresentationModel,
    val secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null,
    val inlineState: RibbonGalleryInlineState
) : Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>() {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        require(
            (presentationModel.commandButtonPresentationState == CommandButtonPresentationState.Small) ||
                    (presentationModel.commandButtonPresentationState == RibbonBandCommandButtonPresentationStates.BigFixed) ||
                    (presentationModel.commandButtonPresentationState == RibbonBandCommandButtonPresentationStates.BigFixedLandscape)
        ) {
            "Unsupported command button presentation state ${presentationModel.commandButtonPresentationState}"
        }

        RibbonGallery(
            modifier = modifier,
            originalProjection = this,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            inlineState = inlineState
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        error("Reprojecting a ribbon gallery is not supported")
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return ribbonGalleryIntrinsicWidth(
            this.contentModel, this.presentationModel, this.inlineState.visibleCount, height
        )
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        error("In-ribbon gallery height is fixed by its band container")
    }
}

infix fun RibbonGalleryProjection.at(that: PresentationPriority):
        Pair<RibbonGalleryProjection, PresentationPriority> = Pair(this, that)

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
        commandProjection: BaseCommandButtonProjection<*, *, *>
    ): CommandMenuContentModel

    fun getContextualMenuContentModel(ribbon: Ribbon): CommandMenuContentModel
}

data class Ribbon(
    val tasks: List<RibbonTask>,
    val contextualTaskGroups: List<RibbonContextualTaskGroup> = emptyList(),
    val anchoredCommands: List<CommandButtonProjection> = emptyList(),
    val taskbarElements: MutableList<RibbonTaskbarElement> = arrayListOf(),
    val taskbarKeyTipPolicy: RibbonTaskbarKeyTipPolicy,
    val applicationMenuCommandButtonProjection: RibbonApplicationMenuCommandButtonProjection? = null,
    val isMinimized: Boolean = false,
    val onShowContextualMenuListener: OnShowContextualMenuListener? = null
)
