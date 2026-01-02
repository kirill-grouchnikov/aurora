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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.LocalButtonShaper
import org.pushingpixels.aurora.theming.LocalTextStyle
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides
import kotlin.math.max

@Composable
private fun CommandButtonStripContent(
    commandGroup: CommandGroup,
    presentationModel: CommandStripPresentationModel,
    commandButtonPresentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay> = mapOf()
) {
    val commandCount = commandGroup.commands.size
    val isHorizontal = (presentationModel.orientation == StripOrientation.Horizontal)
    val leadingSide = if (isHorizontal) Side.Leading else Side.Top
    val trailingSide = if (isHorizontal) Side.Trailing else Side.Bottom
    for ((index, command) in commandGroup.commands.withIndex()) {
        val straightSides = when {
            (commandCount <= 1) -> emptySet()
            (index == 0) -> setOf(trailingSide)
            (index == (commandCount - 1)) -> setOf(leadingSide)
            else -> setOf(leadingSide, trailingSide)
        }
        val openSides = when {
            (commandCount <= 1) -> emptySet()
            (index == 0) -> emptySet()
            else -> setOf(leadingSide)
        }
        var currentPresentationModel = commandButtonPresentationModel.overlayWith(
            overlay = BaseCommandButtonPresentationModel.Overlay(
                sides = Sides(openSides = openSides, straightSides = straightSides)
            )
        )
        if (overlays.containsKey(command)) {
            currentPresentationModel = currentPresentationModel.overlayWith(overlay = overlays[command]!!)
        }

        CommandButtonProjection(
            contentModel = command,
            presentationModel = currentPresentationModel,
            secondaryOverlays = overlays
        ).project(
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = remember { MutableInteractionSource() }
        )
    }
}

private fun getCommandButtonPresentationModel(presentationModel: CommandStripPresentationModel):
        CommandButtonPresentationModel {
    return CommandButtonPresentationModel(
        presentationState = presentationModel.commandPresentationState,
        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
        horizontalAlignment = presentationModel.horizontalAlignment,
        iconDimension = presentationModel.iconDimension,
        iconDisabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
        iconActiveFilterStrategy = presentationModel.iconActiveFilterStrategy,
        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
        horizontalGapScaleFactor = presentationModel.horizontalGapScaleFactor,
        verticalGapScaleFactor = presentationModel.verticalGapScaleFactor,
        popupFireTrigger = presentationModel.popupFireTrigger,
        selectedStateHighlight = presentationModel.selectedStateHighlight
    )
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun commandButtonStripIntrinsicSize(
    commandGroup: CommandGroup,
    presentationModel: CommandStripPresentationModel
): Size {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val buttonShaper = LocalButtonShaper.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val commandButtonPresentationModel = getCommandButtonPresentationModel(presentationModel)
    val layoutManager = commandButtonPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    var width = 0.0f
    var height = 0.0f
    for (command in commandGroup.commands) {
        val preLayoutInfo =
            layoutManager.getPreLayoutInfo(
                command,
                commandButtonPresentationModel
            )
        val preferredSize = layoutManager.getPreferredSize(
            command, commandButtonPresentationModel, preLayoutInfo, buttonShaper
        )
        if (presentationModel.orientation == StripOrientation.Vertical) {
            width = max(width, preferredSize.width)
            height += preferredSize.height
        } else {
            width += preferredSize.width
            height = max(height, preferredSize.height)
        }
    }
    return Size(width, height)
}

@Composable
internal fun AuroraCommandButtonStrip(
    modifier: Modifier = Modifier,
    commandGroup: CommandGroup,
    presentationModel: CommandStripPresentationModel = CommandStripPresentationModel(),
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay> = mapOf()
) {
    val commandButtonPresentationModel = getCommandButtonPresentationModel(presentationModel)
    if (presentationModel.orientation == StripOrientation.Horizontal) {
        Row(modifier = modifier) {
            CommandButtonStripContent(
                commandGroup, presentationModel,
                commandButtonPresentationModel, overlays
            )
        }
    } else {
        Column(modifier = modifier) {
            CommandButtonStripContent(
                commandGroup, presentationModel,
                commandButtonPresentationModel, overlays
            )
        }
    }
}

