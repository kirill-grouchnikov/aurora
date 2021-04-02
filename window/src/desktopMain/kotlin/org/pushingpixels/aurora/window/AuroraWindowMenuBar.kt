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

package org.pushingpixels.aurora.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.auroraBackground
import org.pushingpixels.aurora.component.AuroraCommandButton
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*

private object WindowMenuBarLayout {
    private class CommandButtonLayoutManagerMenuBar(
        override val layoutDirection: LayoutDirection,
        private val _density: Density,
        private val textStyle: TextStyle,
        private val resourceLoader: Font.ResourceLoader
    ) : CommandButtonLayoutManager {
        override val density = _density.density
        override val fontScale = _density.fontScale

        override fun getPreferredIconSize(
            command: Command,
            presentationModel: CommandButtonPresentationModel
        ): Dp {
            return 0.dp
        }

        private fun getPreferredSize(
            command: Command,
            presentationModel: CommandButtonPresentationModel,
            paddingValues: PaddingValues
        ): Size {
            val by = presentationModel.verticalGapScaleFactor *
                    (paddingValues.calculateTopPadding() + paddingValues.calculateBottomPadding()).toPx()
            val bx = presentationModel.horizontalGapScaleFactor *
                    (paddingValues.calculateStartPadding(layoutDirection) +
                            paddingValues.calculateEndPadding(layoutDirection)).toPx()

            val paragraph = Paragraph(
                text = command.text, style = textStyle, width = Float.POSITIVE_INFINITY,
                density = _density, maxLines = 1, resourceLoader = resourceLoader
            )

            return Size(
                width = bx + paragraph.maxIntrinsicWidth,
                height = by + paragraph.height
            )
        }

        override fun getPreLayoutInfo(
            command: Command,
            presentationModel: CommandButtonPresentationModel
        ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
            val hasAction = (command.action != null)
            val hasPopup = (command.secondaryContentModel != null)

            val commandButtonKind = if (!(hasAction xor hasPopup)) {
                throw IllegalArgumentException("Menu command ${command.text} needs to have either action or secondary content")
            } else if (hasPopup) {
                CommandButtonKind.POPUP_ONLY
            } else {
                CommandButtonKind.ACTION_ONLY
            }

            return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
                commandButtonKind = commandButtonKind,
                texts = listOf(command.text),
                extraTexts = emptyList(),
                isTextInActionArea = (hasAction or command.isActionToggle) &&
                        (presentationModel.textClick == TextClick.ACTION),
                separatorOrientation = null,
                showPopupIcon = false
            )
        }

        override fun getLayoutInfo(
            constraints: Constraints,
            command: Command,
            presentationModel: CommandButtonPresentationModel,
            preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
            paddingValues: PaddingValues
        ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
            val preferredSize = getPreferredSize(command, presentationModel, paddingValues)

            val top = presentationModel.verticalGapScaleFactor *
                    paddingValues.calculateTopPadding().toPx()
            val left = presentationModel.horizontalGapScaleFactor *
                    paddingValues.calculateLeftPadding(layoutDirection).toPx()

            val paragraph = Paragraph(
                text = command.text, style = textStyle, width = Float.POSITIVE_INFINITY,
                density = _density, maxLines = 1, resourceLoader = resourceLoader
            )

            return CommandButtonLayoutManager.CommandButtonLayoutInfo(
                fullSize = preferredSize,
                actionClickArea = if (command.action != null) Rect(
                    left = 0.0f,
                    right = preferredSize.width,
                    top = 0.0f,
                    bottom = preferredSize.height
                ) else Rect.Zero,
                popupClickArea = if (command.action == null) Rect(
                    left = 0.0f,
                    right = preferredSize.width,
                    top = 0.0f,
                    bottom = preferredSize.height
                ) else Rect.Zero,
                separatorArea = Rect.Zero,
                iconRect = Rect.Zero,
                textLayoutInfoList = listOf(
                    CommandButtonLayoutManager.TextLayoutInfo(
                        text = command.text,
                        textRect = Rect(
                            left = left, right = left + paragraph.maxIntrinsicWidth,
                            top = top, bottom = top + paragraph.height
                        )
                    )
                ),
                extraTextLayoutInfoList = null,
                popupActionRect = Rect.Zero
            )
        }
    }

    val MENU_BAR: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Menu Bar") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                resourceLoader: Font.ResourceLoader
            ): CommandButtonLayoutManager {
                return CommandButtonLayoutManagerMenuBar(
                    layoutDirection,
                    density,
                    textStyle,
                    resourceLoader
                )
            }
        }
}

@Composable
internal fun AuroraWindowMenuBar(menuCommands: CommandGroup) {
    for (menuCommand in menuCommands.commands) {
        // Needs a non-empty text
        if (menuCommand.text.isEmpty()) {
            throw IllegalArgumentException("Menu command text cannot be empty")
        }
        val hasAction = menuCommand.isActionEnabled && (menuCommand.action != null)
        val hasPopup = menuCommand.isSecondaryEnabled && (menuCommand.secondaryContentModel != null)
        if (!(hasAction xor hasPopup)) {
            throw IllegalArgumentException("Menu command ${menuCommand.text} needs to have either action or secondary content")
        }
        if (menuCommand.isActionToggle) {
            throw IllegalArgumentException("Toggle menu commands not supported")
        }
    }

    AuroraDecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().auroraBackground()
        ) {
            for (menuCommand in menuCommands.commands) {
                AuroraCommandButton(
                    command = menuCommand,
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = WindowMenuBarLayout.MENU_BAR,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                        isMenu = true
                    )
                )
            }
        }
    }
}

