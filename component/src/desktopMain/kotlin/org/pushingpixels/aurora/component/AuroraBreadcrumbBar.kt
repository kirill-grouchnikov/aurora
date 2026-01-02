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
package org.pushingpixels.aurora.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.LocalButtonShaper
import org.pushingpixels.aurora.theming.LocalTextStyle
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

private fun getCommandButtonPresentationModel(presentationModel: BreadcrumbBarPresentationModel):
        CommandButtonPresentationModel {
    return CommandButtonPresentationModel(
        presentationState = presentationModel.presentationState,
        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
        iconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
        iconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
        popupMenuPresentationModel = CommandPopupMenuPresentationModel(
            itemIconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
            itemIconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
            itemIconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
            maxVisibleItems = presentationModel.maxVisibleChoiceCommands
        )
    )
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun breadcrumbBarIntrinsicSize(
    contentModel: BreadcrumbBarContentModel,
    presentationModel: BreadcrumbBarPresentationModel
): Size {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }
    val buttonShaper = LocalButtonShaper.current

    val contentPresentationModel = getCommandButtonPresentationModel(presentationModel)
    val contentLayoutManager = contentPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val forSizing = Command(text = "sample", action = {})
    val boxHeight = contentLayoutManager.getPreferredSize(
        command = forSizing,
        presentationModel = contentPresentationModel,
        preLayoutInfo = contentLayoutManager.getPreLayoutInfo(
            forSizing,
            contentPresentationModel
        ),
        buttonShaper = buttonShaper
    ).height
    val width = contentModel.commands.sumOf { command ->
        val commandPreLayoutInfo =
            contentLayoutManager.getPreLayoutInfo(
                command,
                contentPresentationModel
            )
        val commandSize = contentLayoutManager.getPreferredSize(
            command, contentPresentationModel, commandPreLayoutInfo, buttonShaper
        )
        commandSize.width.toDouble()
    }
    return Size(width.toFloat(), boxHeight)
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraBreadcrumbBar(
    contentModel: BreadcrumbBarContentModel,
    presentationModel: BreadcrumbBarPresentationModel = BreadcrumbBarPresentationModel(),
    horizontalScrollState: ScrollState = rememberScrollState(0),
    modifier: Modifier
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val buttonShaper = LocalButtonShaper.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    // Presentation model for the content - copy fields from the breadcrumb bar presentation model
    val contentPresentationModel = getCommandButtonPresentationModel(presentationModel)
    val contentLayoutManager = contentPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val forSizing = Command(text = "sample", action = {})
    val boxHeight = contentLayoutManager.getPreferredSize(
        command = forSizing,
        presentationModel = contentPresentationModel,
        preLayoutInfo = contentLayoutManager.getPreLayoutInfo(
            forSizing,
            contentPresentationModel
        ),
        buttonShaper = buttonShaper
    ).height.toInt()

    AuroraHorizontallyScrollableBox(
        modifier = modifier,
        height = (boxHeight / density.density).dp,
        contentWidth = {
            contentModel.commands.sumOf { command ->
                val commandPreLayoutInfo =
                    contentLayoutManager.getPreLayoutInfo(
                        command,
                        contentPresentationModel
                    )
                val commandSize = contentLayoutManager.getPreferredSize(
                    command, contentPresentationModel, commandPreLayoutInfo, buttonShaper
                )
                commandSize.width.toDouble()
            }.toInt()
        },
        horizontalScrollState = horizontalScrollState,
        scrollAmount = 12.dp,
        content = {
            for (command in contentModel.commands) {
                val popupInteractionSource = remember(command) { MutableInteractionSource() }
                val isPopupRollover by popupInteractionSource.collectIsHoveredAsState()

                // Project a command button for each command
                CommandButtonProjection(
                    contentModel = command,
                    presentationModel = contentPresentationModel.overlayWith(
                        BaseCommandButtonPresentationModel.Overlay(
                            popupPlacementStrategy = if (isPopupRollover) PopupPlacementStrategy.Downward.HAlignStart
                            else PopupPlacementStrategy.Endward.VAlignTop
                        )
                    ),
                    secondaryOverlays = mapOf()
                ).project(
                    modifier = Modifier.fillMaxWidth(),
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = popupInteractionSource
                )
            }
        }
    )
}
