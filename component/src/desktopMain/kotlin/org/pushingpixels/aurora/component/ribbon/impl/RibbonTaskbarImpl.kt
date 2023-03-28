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
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarCommandProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarComponentProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarElement
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarGalleryProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy

@Composable
fun RibbonTaskbar(
    modifier: Modifier,
    maxWidth: Dp,
    elements: List<RibbonTaskbarElement>
) {
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
        },
        measurePolicy = { measurables, constraints ->
            val maxWidthPx = maxWidth.toPx().toInt()
            val height = constraints.maxHeight
            val gap = TaskBarLayoutGap.toPx().toInt()

            val visiblePlaceables = mutableListOf<Placeable>()
            var currentlyTakenWidth = 0
            var isOverflowing = false
            for (measurable in measurables) {
                val neededWidth = measurable.maxIntrinsicWidth(height)
                if (!isOverflowing && (currentlyTakenWidth + gap + neededWidth) <= maxWidthPx) {
                    visiblePlaceables.add(
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

            layout(width = currentlyTakenWidth, height = height) {
                var x = 0
                for (placeable in visiblePlaceables) {
                    val currWidth = placeable.measuredWidth
                    val currHeight = placeable.measuredHeight
                    placeable.placeRelative(x, (height - currHeight) / 2)
                    x += (currWidth + gap)
                }
            }
        })
}

private val TaskBarLayoutGap = 4.dp
