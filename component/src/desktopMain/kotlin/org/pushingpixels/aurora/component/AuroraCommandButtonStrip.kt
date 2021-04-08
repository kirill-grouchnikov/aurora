/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.ButtonSides
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.component.model.*

@Composable
private fun CommandButtonStripContent(
    commandGroup: CommandGroup,
    presentationModel: CommandStripPresentationModel,
    commandButtonPresentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay> = mapOf()
) {
    val commandCount = commandGroup.commands.size
    val isHorizontal = (presentationModel.orientation == StripOrientation.HORIZONTAL)
    val leadingSide = if (isHorizontal) Side.START else Side.TOP
    val trailingSide = if (isHorizontal) Side.END else Side.BOTTOM
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
        AuroraCommandButton(
            command = command,
            parentWindow = null,
            extraAction = null,
            presentationModel = if (overlays.containsKey(command))
                commandButtonPresentationModel.overlayWith(overlay = overlays[command]!!)
            else commandButtonPresentationModel,
            buttonSides = ButtonSides(openSides = openSides, straightSides = straightSides)
        )
    }
}

@Composable
fun AuroraCommandButtonStrip(
    modifier: Modifier = Modifier,
    commandGroup: CommandGroup,
    presentationModel: CommandStripPresentationModel = CommandStripPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay> = mapOf()
) {
    val commandButtonPresentationModel = CommandButtonPresentationModel(
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
        isMenu = presentationModel.isMenu
    )
    if (presentationModel.orientation == StripOrientation.HORIZONTAL) {
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

