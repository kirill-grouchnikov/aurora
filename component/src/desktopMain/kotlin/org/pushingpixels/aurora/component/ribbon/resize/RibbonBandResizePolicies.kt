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
package org.pushingpixels.aurora.component.ribbon.resize

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.LocalButtonShaper
import org.pushingpixels.aurora.theming.LocalTextStyle
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * Defines the resize policies for the [RibbonBand]s and [FlowRibbonBand]s.
 *
 * The resize policy defines a single visual state of the given ribbon band. For
 * every control in the specific ribbon band (command button, gallery etc.), the
 * resize policy defines what is its presentation state.
 *
 * The resize policies are passed to the ribbon band constructor. The order
 * of the resize policies in this list is important. The first entry in the list
 * must be the most permissive policy that returns the largest value from its
 * [RibbonBandResizePolicy.getPreferredWidth]. Each successive entry in the list must
 * return the value smaller than its predecessors.
 *
 * As the ribbon horizontal size is changed (by the user resizing the
 * application window), the ribbon task resize sequencing policy set on
 * [RibbonTask] determines the order of ribbon bands to shrink / expand. See more details in
 * the documentation of the [RibbonBandResizeSequencingPolicy].
 *
 * The [CoreRibbonResizePolicies] provides a number of resize
 * policies that respect the application element priorities passed to ribbon commands
 * and galleries on [RibbonBand]s. There are three types of resize policies provided by the core
 * Aurora implementation:
 *
 * 1. Resize policies for the [FlowRibbonBand]s. The [CoreRibbonResizePolicies.FlowTwoRows]
 * and [CoreRibbonResizePolicies.FlowThreeRows] allow placing the flow ribbon band content in two
 * and three rows respectively.
 * 2. Resize policies for the [RibbonBand]s. The
 * [CoreRibbonResizePolicies.BaseCoreRibbonResizePolicy] is the base class for these policies.
 * These policies respect the presentation priority associated with the command buttons and ribbon
 * galleries. While content of [FlowRibbonBand] stays at fixed size (and flows between 1 to 3 rows),
 * ta resize policy on [RibbonBand] can also change the presentation state of the
 * command buttons and the number of visible buttons in the ribbon galleries.
 * 3. The collapsed / icon policy that replaces the entire content of the ribbon band
 * with a single popup button. This is done when there is not enough horizontal
 * space to show the content of the ribbon band under its most restrictive
 * resize policy. Activating the popup button will show the original content
 * under the most permissive resize policy in a popup. This policy is
 * implemented in the [CoreRibbonResizePolicies.Icon].
 *
 * In addition to the specific resize policies, the
 * [CoreRibbonResizePolicies] provides three core resize policies lists
 * for [RibbonBand]s:
 *
 * 1. [CoreRibbonResizePolicies.getCorePoliciesPermissive]
 * returns a list that starts with a resize policy that shows all command
 * buttons in the [CommandButtonPresentationState.Big] and ribbon galleries
 * with the largest number of visible buttons, fully utilizing the available
 * screen space.
 * 2. [CoreRibbonResizePolicies.getCorePoliciesRestrictive]
 * returns a list that starts with a resize policy that respects the associated
 * ribbon element priority set on the specific components.
 * 3. [CoreRibbonResizePolicies.getCorePoliciesNone] returns
 * a list that only has a [CoreRibbonResizePolicies.Mirror] and
 * [CoreRibbonResizePolicies.Icon] resize policies.
 *
 * Note that as mentioned above, all the three lists above have the
 * [CoreRibbonResizePolicies.Icon] policy as their last element.
 *
 * In addition, the
 * [CoreRibbonResizePolicies.getCoreFlowPoliciesRestrictive]
 * returns a restrictive resize policy for [FlowRibbonBand]s. The list
 * starts with the two-row policy, goes to the three-row policy and then finally
 * to the [CoreRibbonResizePolicies.Icon] policy.
 *
 * @author Kirill Grouchnikov
 */
interface RibbonBandResizePolicy {
    /**
     * Returns the preferred width of the associated ribbon band under the
     * specified dimensions.
     *
     * @param availableHeight The height available for the associated ribbon band.
     * @param gap The inter-component gap.
     * @return The preferred width of the associated ribbon band under the
     * specified dimensions.
     */
    @Composable
    fun getPreferredWidth(ribbonBand: AbstractRibbonBand, availableHeight: Int, gap: Int): Int
}

sealed interface FlowRibbonBandResizePolicy : RibbonBandResizePolicy {
    val rowCount: Int
}

abstract class CoreRibbonResizePolicy(val mapping: (PresentationPriority) -> PresentationPriority) :
    RibbonBandResizePolicy

object CoreRibbonResizePolicies {
    fun getCorePoliciesNone(): List<RibbonBandResizePolicy> {
        return listOf(Mirror, Icon)
    }

    fun getCorePoliciesPermissive(): List<RibbonBandResizePolicy> {
        return listOf(None, Low2Mid, Mid2Mid, Mirror, Mid2Low, High2Mid, High2Low, Icon)
    }

    fun getCorePoliciesRestrictive(): List<RibbonBandResizePolicy> {
        return listOf(Mirror, Mid2Low, High2Mid, High2Low, Icon)
    }

    fun getCoreFlowPoliciesRestrictive(stepsToRepeat: Int): List<RibbonBandResizePolicy> {
        return List(stepsToRepeat) { FlowTwoRows } + List(stepsToRepeat) {FlowThreeRows} + Icon
    }

    @Composable
    private fun getCommandGroupWidth(
        group: RibbonBandCommandGroup,
        mapping: (PresentationPriority) -> PresentationPriority,
        bandContentHeight: Int,
        gap: Int
    ): Int {
        val rowHeight = ((bandContentHeight - 4 * gap) / 3.0f).toInt()
        var result = 0

        // Start with galleries
        for (galleryProjections in group.galleries) {
            val collapsedVisibleCount = when (mapping.invoke(galleryProjections.second)) {
                PresentationPriority.Low -> galleryProjections.first.presentationModel.collapsedVisibleCountLow
                PresentationPriority.Medium -> galleryProjections.first.presentationModel.collapsedVisibleCountMedium
                PresentationPriority.Top -> galleryProjections.first.presentationModel.collapsedVisibleCountTop
            }
            result += RibbonGalleryProjection(
                contentModel = galleryProjections.first.contentModel,
                presentationModel = galleryProjections.first.presentationModel,
                inlineState = galleryProjections.first.inlineState.apply {
                    visibleCount = collapsedVisibleCount
                }
            ).intrinsicWidth(bandContentHeight - 2 * gap)
        }
        if (group.galleries.isNotEmpty()) {
            result += gap * (group.galleries.size - 1)
        }

        // And then buttons
        // TODO - this will be a combination of presentation priority, available horizontal space
        // and ribbon band resize policies
        val buttonsBig: MutableList<BaseCommandButtonProjection<*, *, *>> = arrayListOf()
        val buttonsMedium: MutableList<BaseCommandButtonProjection<*, *, *>> = arrayListOf()
        val buttonsSmall: MutableList<BaseCommandButtonProjection<*, *, *>> = arrayListOf()
        for (commandProjection in group.commandProjections) {
            when (mapping.invoke(commandProjection.second)) {
                PresentationPriority.Top -> buttonsBig.add(commandProjection.first)
                PresentationPriority.Medium -> buttonsMedium.add(commandProjection.first)
                PresentationPriority.Low -> buttonsSmall.add(commandProjection.first)
            }
        }

        for (big in buttonsBig) {
            result += big.copy(
                primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                    presentationState = CommandButtonPresentationState.Big,
                )
            ).intrinsicWidth(bandContentHeight)
        }
        if (buttonsBig.isNotEmpty()) {
            result += gap * (buttonsBig.size - 1)
        }

        val mediumColumnCount = ceil(buttonsMedium.size / 3.0f).toInt()
        if (mediumColumnCount > 0) {
            var mediumButtonIndex = 0
            for (column in 1..mediumColumnCount) {
                var columnWidth = 0
                for (row in 1..3) {
                    if (mediumButtonIndex < buttonsMedium.size) {
                        val buttonIntrinsicWidth = buttonsMedium[mediumButtonIndex].copy(
                            primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                                presentationState = CommandButtonPresentationState.Medium,
                            )
                        ).intrinsicWidth(rowHeight)
                        columnWidth = max(columnWidth, buttonIntrinsicWidth)
                        mediumButtonIndex++
                    }
                }
                result += columnWidth
            }
            result += gap * (mediumColumnCount - 1)
        }

        val smallColumnCount = ceil(buttonsSmall.size / 3.0f).toInt()
        if (smallColumnCount > 0) {
            var smallButtonIndex = 0
            for (column in 1..smallColumnCount) {
                var columnWidth = 0
                for (row in 1..3) {
                    if (smallButtonIndex < buttonsSmall.size) {
                        val buttonIntrinsicWidth = buttonsSmall[smallButtonIndex].copy(
                            primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                                presentationState = CommandButtonPresentationState.Small,
                            )
                        ).intrinsicWidth(rowHeight)
                        columnWidth = max(columnWidth, buttonIntrinsicWidth)
                        smallButtonIndex++
                    }
                }
                result += columnWidth
            }
            result += gap * (smallColumnCount - 1)
        }

        val buttonGroupsBySize = ((if (buttonsBig.isNotEmpty()) 1 else 0) +
                (if (buttonsMedium.isNotEmpty()) 1 else 0) +
                (if (buttonsSmall.isNotEmpty()) 1 else 0))
        if (buttonGroupsBySize > 0) {
            result += gap * (buttonGroupsBySize - 1)
        }

        return result + 2 * gap
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    private fun getComponentGroupWidth(
        group: RibbonBandComponentGroup,
        bandContentHeight: Int,
        gap: Int
    ): Int {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

        val rowHeight = ((bandContentHeight - 4 * gap) / 3.0f).toInt()

        val hasTitle = (group.title != null)
        val contentRows = if (hasTitle) 2 else 3

        val titleLabelWidth = if (hasTitle)
            getLabelPreferredSingleLineWidth(
                contentModel = LabelContentModel(text = group.title),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(0.dp),
                    textMaxLines = 1
                ),
                resolvedTextStyle = resolvedTextStyle,
                layoutDirection = layoutDirection,
                density = density,
                fontFamilyResolver = LocalFontFamilyResolver.current
            ).toInt() else 0

        val columnWidths: MutableList<Int> = arrayListOf()

        var contentWidth = 0
        var currentColumnWidth = 0
        var currentIndexInColumn = 0
        for (projection in group.componentProjections) {
            val widthNeeded = projection.intrinsicWidth(height = rowHeight)
            currentColumnWidth = max(currentColumnWidth, widthNeeded)

            currentIndexInColumn++
            if (currentIndexInColumn == contentRows) {
                columnWidths.add(currentColumnWidth)

                // Start a new column
                contentWidth += currentColumnWidth
                currentIndexInColumn = 0
                currentColumnWidth = 0
            }
        }
        if (currentIndexInColumn < contentRows) {
            // Incomplete last column
            contentWidth += currentColumnWidth
            columnWidths.add(currentColumnWidth)
        }

        // Account for gaps between columns
        val contentColumnCount = ceil(group.componentProjections.size.toFloat() / contentRows.toFloat()).toInt()
        contentWidth += (contentColumnCount - 1) * gap

        val fullWidth = max(titleLabelWidth, contentWidth) + 2 * gap
        return fullWidth
    }

    abstract class BaseCoreRibbonResizePolicy(mapping: (PresentationPriority) -> PresentationPriority) :
        CoreRibbonResizePolicy(mapping) {
        @Composable
        override fun getPreferredWidth(ribbonBand: AbstractRibbonBand, availableHeight: Int, gap: Int): Int {
            require(ribbonBand is RibbonBand) {
                "This policy only supports flow ribbon bands"
            }
            var result = 0
            for (bandGroup in ribbonBand.groups) {
                result += when (bandGroup) {
                    is RibbonBandCommandGroup ->
                        getCommandGroupWidth(bandGroup, this.mapping, availableHeight, gap)

                    is RibbonBandComponentGroup ->
                        getComponentGroupWidth(bandGroup, availableHeight, gap)
                }
            }

            if (ribbonBand.groups.size > 1) {
                result += (ribbonBand.groups.size - 1) * (SeparatorSizingConstants.Thickness.value * LocalDensity.current.density).toInt()
            }

            return result
        }
    }

    object None : BaseCoreRibbonResizePolicy({ PresentationPriority.Top })

    object High2Mid : BaseCoreRibbonResizePolicy({
        when (it) {
            PresentationPriority.Top -> PresentationPriority.Medium
            PresentationPriority.Medium -> PresentationPriority.Low
            PresentationPriority.Low -> PresentationPriority.Low
        }
    })

    object High2Low : BaseCoreRibbonResizePolicy({ PresentationPriority.Low })

    object Mid2Mid : BaseCoreRibbonResizePolicy({
        when (it) {
            PresentationPriority.Top -> PresentationPriority.Top
            PresentationPriority.Medium -> PresentationPriority.Medium
            PresentationPriority.Low -> PresentationPriority.Medium
        }
    })

    object Mid2Low : BaseCoreRibbonResizePolicy({
        when (it) {
            PresentationPriority.Top -> PresentationPriority.Top
            PresentationPriority.Medium -> PresentationPriority.Low
            PresentationPriority.Low -> PresentationPriority.Low
        }
    })

    object Low2Mid : BaseCoreRibbonResizePolicy({
        when (it) {
            PresentationPriority.Top -> PresentationPriority.Top
            PresentationPriority.Medium -> PresentationPriority.Top
            PresentationPriority.Low -> PresentationPriority.Medium
        }
    })

    object Mirror : BaseCoreRibbonResizePolicy({ it })

    data object FlowOneRow : FlowRibbonBandResizePolicy {
        override val rowCount: Int = 1

        @Composable
        override fun getPreferredWidth(ribbonBand: AbstractRibbonBand, availableHeight: Int, gap: Int): Int {
            require(ribbonBand is FlowRibbonBand) {
                "This policy only supports flow ribbon bands"
            }
            val compCount = ribbonBand.flowComponentProjections.size
            val widths = IntArray(compCount)
            var currBestResult = 0
            val rowHeight = ((availableHeight - 4 * gap) / 3.0f).toInt()
            for ((index, flowCompProjection) in ribbonBand.flowComponentProjections.withIndex()) {
                widths[index] = flowCompProjection.intrinsicWidth(height = rowHeight)
                currBestResult += widths[index]
            }
            if (ribbonBand.flowComponentProjections.size > 1) {
                currBestResult += (ribbonBand.flowComponentProjections.size - 1) * gap
            }
            return currBestResult + 2 * gap
        }
    }

    data object FlowTwoRows : FlowRibbonBandResizePolicy {
        override val rowCount: Int = 2

        @Composable
        override fun getPreferredWidth(ribbonBand: AbstractRibbonBand, availableHeight: Int, gap: Int): Int {
            require(ribbonBand is FlowRibbonBand) {
                "This policy only supports flow ribbon bands"
            }
            val compCount = ribbonBand.flowComponentProjections.size
            val widths = IntArray(compCount)
            var currBestResult = 0
            val rowHeight = ((availableHeight - 4 * gap) / 3.0f).toInt()
            for ((index, flowCompProjection) in ribbonBand.flowComponentProjections.withIndex()) {
                widths[index] = flowCompProjection.intrinsicWidth(height = rowHeight)
                currBestResult += widths[index]
            }
            if (ribbonBand.flowComponentProjections.size > 1) {
                currBestResult += (ribbonBand.flowComponentProjections.size - 1) * gap
            }

            // need to find the inflection point that results in
            // the lowest value for max length of two sub-sequences
            for (inflectionIndex in 0 until compCount - 1) {
                var w1 = 0
                for (index1 in 0..inflectionIndex) {
                    w1 += widths[index1] + gap
                }
                var w2 = 0
                for (index2 in inflectionIndex + 1 until compCount) {
                    w2 += widths[index2] + gap
                }
                val width = max(w1.toDouble(), w2.toDouble()).toInt()
                if (width < currBestResult) {
                    currBestResult = width
                }
            }

            return currBestResult + 2 * gap
        }
    }

    data object FlowThreeRows : FlowRibbonBandResizePolicy {
        override val rowCount: Int = 3

        @Composable
        override fun getPreferredWidth(ribbonBand: AbstractRibbonBand, availableHeight: Int, gap: Int): Int {
            require(ribbonBand is FlowRibbonBand) {
                "This policy only supports flow ribbon bands"
            }
            val compCount = ribbonBand.flowComponentProjections.size
            val widths = IntArray(compCount)
            var currBestResult = 0
            val rowHeight = ((availableHeight - 4 * gap) / 3.0f).toInt()
            for ((index, flowCompProjection) in ribbonBand.flowComponentProjections.withIndex()) {
                widths[index] = flowCompProjection.intrinsicWidth(height = rowHeight)
                currBestResult += widths[index]
            }
            if (ribbonBand.flowComponentProjections.size > 1) {
                currBestResult += (ribbonBand.flowComponentProjections.size - 1) * gap
            }

            // Special case for two components. With zero or one components, the total width
            // is also the "best" / smallest width achievable. With 3+ components, we should
            // look at all possible options that go to 3 rows. But with 2 components, we take the
            // width of the widest one
            if (compCount == 2) {
                return max(widths[0], widths[1]) + gap
            }

            // need to find the inflection points that result in
            // the lowest value for max length of three sub-sequences
            for (inflectionIndex1 in 0 until (compCount - 2)) {
                for (inflectionIndex2 in inflectionIndex1 + 1 until compCount - 1) {
                    var w1 = 0
                    for (index1 in 0..inflectionIndex1) {
                        w1 += widths[index1] + gap
                    }
                    var w2 = 0
                    for (index2 in inflectionIndex1 + 1..inflectionIndex2) {
                        w2 += widths[index2] + gap
                    }
                    var w3 = 0
                    for (index3 in inflectionIndex2 + 1 until compCount) {
                        w3 += widths[index3] + gap
                    }
                    val width = max(max(w1, w2), w3)
                    if (width < currBestResult) {
                        currBestResult = width
                    }
                }
            }

            return currBestResult + 2 * gap
        }
    }

    object Icon : RibbonBandResizePolicy {
        @OptIn(AuroraInternalApi::class)
        @Composable
        override fun getPreferredWidth(ribbonBand: AbstractRibbonBand, availableHeight: Int, gap: Int): Int {
            val density = LocalDensity.current
            val layoutDirection = LocalLayoutDirection.current
            val textStyle = LocalTextStyle.current
            val fontFamilyResolver = LocalFontFamilyResolver.current
            val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }
            val buttonShaper = LocalButtonShaper.current

            val iconCommand = Command(
                text = ribbonBand.title,
                icon = ribbonBand.icon,
                secondaryContentModel = CommandMenuContentModel(group = CommandGroup(commands = listOf()))
            )
            val iconPresentationModel =
                CommandButtonPresentationModel(presentationState = CommandButtonPresentationState.Big)
            val layoutManager = iconPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                fontFamilyResolver = fontFamilyResolver
            )
            val preLayoutInfo =
                layoutManager.getPreLayoutInfo(
                    iconCommand,
                    iconPresentationModel
                )
            return min(
                layoutManager.getPreferredSize(
                    iconCommand, iconPresentationModel, preLayoutInfo, buttonShaper
                ).width.toInt(), availableHeight * 5 / 4
            )
        }
    }
}