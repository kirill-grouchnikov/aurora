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
package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarCommandProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarComponentProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarElement
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarGalleryProjection
import org.pushingpixels.aurora.component.utils.getEndwardDoubleArrowIcon
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.Sides

@Composable
fun RibbonTaskbar(
    modifier: Modifier,
    maxWidth: Dp,
    elements: List<RibbonTaskbarElement>
) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current
    val expandCommand = Command(
        text = "",
        icon = getEndwardDoubleArrowIcon(
            decorationAreaType = decorationAreaType,
            skinColors = colors,
            colorSchemeBundle = null,
            density = density
        ),
        secondaryContentModel = CommandMenuContentModel(
            groups = emptyList()
        ),
        isSecondaryEnabled = true
    )

    Layout(modifier = modifier,
        content = {
            for (element in elements) {
                when (element) {
                    is RibbonTaskbarCommandProjection -> {
                        element.commandProjection.reproject(
                            modifier = Modifier,
                            primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                                presentationState = CommandButtonPresentationState.Small,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                            ),
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() }
                        )
                    }

                    is RibbonTaskbarGalleryProjection -> {
                        val galleryContentModel = element.galleryProjection.contentModel
                        val galleryPresentationModel = element.galleryProjection.presentationModel

                        val galleryCommand = Command(
                            text = "",
                            icon = galleryContentModel.icon,
                            secondaryContentModel = CommandMenuContentModel(
                                onDeactivatePopup = {
                                    // Mark the inline state to have the latest selected command button to be revealed
                                    element.galleryInlineState.revealSelected()
                                },
                                panelContentModel = CommandPanelContentModel(
                                    commandGroups = galleryContentModel.commandGroups
                                ),
                                groups = galleryContentModel.extraPopupGroups
                            ),
                            isSecondaryEnabled = true,
                        )
                        CommandButtonProjection(
                            contentModel = galleryCommand,
                            presentationModel = CommandButtonPresentationModel(
                                presentationState = CommandButtonPresentationState.Small,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                                    panelPresentationModel = CommandPopupMenuPanelPresentationModel(
                                        layoutSpec = galleryPresentationModel.popupLayoutSpec,
                                        contentPadding = PaddingValues(0.dp),
                                        showGroupLabels = galleryContentModel.commandGroups.all { !it.title.isNullOrEmpty() },
                                        commandPresentationState = galleryPresentationModel.commandButtonPresentationState,
                                        commandPopupFireTrigger = galleryPresentationModel.commandPopupFireTrigger,
                                        commandSelectedStateHighlight = galleryPresentationModel.commandSelectedStateHighlight
                                    )
                                )
                            ),
                            secondaryOverlays = element.galleryProjection.secondaryOverlays
                        ).project()
                    }

                    is RibbonTaskbarComponentProjection -> {
                        element.componentProjection.reproject(modifier = Modifier)
                    }
                }
            }

            CommandButtonProjection(
                contentModel = expandCommand,
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Small,
                    sides = Sides.ClosedRectangle,
                    contentPadding = PaddingValues(all = 2.dp),
                    showPopupIcon = false,
                    popupMenuPresentationModel = CommandPopupMenuPresentationModel(),
                )
            ).project()
        },
        measurePolicy = { measurables, constraints ->
            val maxWidthPx = maxWidth.toPx().toInt()
            val height = constraints.maxHeight
            val gap = TaskBarLayoutGap.toPx().toInt()

            val contentMeasurables = measurables.subList(0, elements.size)
            val expandButtonMeasurable = measurables.last()

            // Measure the max intrinsic width of every single child (including the expand button)
            val measuredWidths = measurables.map { it.maxIntrinsicWidth(height) }
            // How much width is needed to show all the content children with gaps between them,
            // but excluding the expand button?
            val fullWidthNeeded = measuredWidths.subList(0, measuredWidths.size - 1).sum() +
                    gap * (elements.size - 1)
            // Can we show all the content?
            val canFitAllContent = (fullWidthNeeded <= maxWidthPx)
            // What is the width available to display content? If all content does not fit, we account
            // for the expand button and the gap before it
            val maxAvailableWidthPx = if (canFitAllContent) maxWidthPx
            else maxWidthPx - measuredWidths.last() - gap

            val visibleContentPlaceables = mutableListOf<Placeable>()
            var currentlyTakenWidth = 0
            var isOverflowing = false
            // Measure all content children that fit
            for ((index, measurable) in contentMeasurables.withIndex()) {
                val neededWidth = measuredWidths[index]
                val needsGapAfter = (index < contentMeasurables.size - 1)
                val takenWidthWithThisChild = currentlyTakenWidth + neededWidth +
                        if (needsGapAfter) gap else 0
                if (!isOverflowing && (takenWidthWithThisChild <= maxAvailableWidthPx)) {
                    visibleContentPlaceables.add(
                        measurable.measure(
                            Constraints.fixed(
                                width = neededWidth,
                                height = measurable.maxIntrinsicHeight(neededWidth)
                            )
                        )
                    )
                    currentlyTakenWidth += (neededWidth + gap)
                } else {
                    isOverflowing = true
                }
            }

            if (!isOverflowing && (measurables.size > 1)) {
                // Remove the "trailing" gap
                currentlyTakenWidth -= gap
            }

            // And finally measure the expand button if needed
            var expandButtonPlaceable: Placeable? = null
            if (!canFitAllContent) {
                val expandButtonWidth = measuredWidths.last()
                expandButtonPlaceable = expandButtonMeasurable.measure(
                    Constraints.fixed(
                        width = expandButtonWidth,
                        height = expandButtonMeasurable.maxIntrinsicHeight(expandButtonWidth)
                    )
                )
                currentlyTakenWidth += expandButtonWidth
            }

            layout(width = currentlyTakenWidth, height = height) {
                var x = 0
                for (placeable in visibleContentPlaceables) {
                    val currWidth = placeable.measuredWidth
                    val currHeight = placeable.measuredHeight
                    placeable.placeRelative(x, (height - currHeight) / 2)
                    x += (currWidth + gap)
                }

                expandButtonPlaceable?.placeRelative(x, (height - expandButtonPlaceable.height) / 2)
            }
        })
}

private val TaskBarLayoutGap = 4.dp
