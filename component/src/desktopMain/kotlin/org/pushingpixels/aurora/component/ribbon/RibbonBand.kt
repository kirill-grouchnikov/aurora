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

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizePolicies
import org.pushingpixels.aurora.component.ribbon.resize.RibbonBandResizePolicy

sealed interface AbstractRibbonBand {
    val title: String
    val icon: Painter?
    val expandCommand: Command?
    val expandCommandKeyTip: String?
    val collapsedStateKeyTip: String?
    val resizePolicies: List<RibbonBandResizePolicy>
}

infix fun RibbonGalleryProjection.at(that: PresentationPriority):
        Pair<RibbonGalleryProjection, PresentationPriority> = Pair(this, that)

infix fun <C: ContentModel, P: PresentationModel> Projection<C, P>.with(that: RibbonComponentPresentationModel):
        Pair<Projection<C, P>, RibbonComponentPresentationModel> = Pair(this, that)

data class RibbonBandGroup(
    val title: String? = null,
    val commandProjections: List<Pair<BaseCommandButtonProjection<*, *>, PresentationPriority>> = emptyList(),
    val componentProjections: List<Pair<Projection<ContentModel, PresentationModel>, RibbonComponentPresentationModel>> = emptyList(),
    val galleryProjections: List<Pair<RibbonGalleryProjection, PresentationPriority>> = emptyList(),
)

data class RibbonBand(
    override val title: String,
    override val icon: Painter? = null,
    override val expandCommand: Command? = null,
    override val expandCommandKeyTip: String? = null,
    override val collapsedStateKeyTip: String? = null,
    override val resizePolicies: List<RibbonBandResizePolicy> =
        CoreRibbonResizePolicies.getCorePoliciesPermissive(),
    val groups: List<RibbonBandGroup> = emptyList()
) : AbstractRibbonBand

data class FlowRibbonBand(
    override val title: String,
    override val icon: Painter? = null,
    override val expandCommand: Command? = null,
    override val expandCommandKeyTip: String? = null,
    override val collapsedStateKeyTip: String? = null,
    override val resizePolicies: List<RibbonBandResizePolicy> =
        CoreRibbonResizePolicies.getCoreFlowPoliciesRestrictive(3),
    val flowComponentProjections: List<Pair<Projection<ContentModel, PresentationModel>, RibbonComponentPresentationModel>> = emptyList()
) : AbstractRibbonBand

object RibbonBandCommandButtonPresentationStates {
    val BigFixed: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Big Fixed") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                throw UnsupportedOperationException()
            }
        }

    val BigFixedLandscape: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Big Fixed Landscape") {
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
