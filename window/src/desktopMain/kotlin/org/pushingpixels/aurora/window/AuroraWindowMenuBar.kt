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

package org.pushingpixels.aurora.window

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper

private object WindowMenuBarLayout {
    private class CommandButtonLayoutManagerMenuBar(
        override val layoutDirection: LayoutDirection,
        private val _density: Density,
        private val textStyle: TextStyle,
        private val fontFamilyResolver: FontFamily.Resolver
    ) : CommandButtonLayoutManager {
        override val density = _density.density
        override val fontScale = _density.fontScale

        override fun getPreferredIconSize(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel
        ): DpSize {
            return DpSize.Zero
        }

        override fun getPreferredSize(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
            buttonShaper: AuroraButtonShaper
        ): Size {
            val paddingValues = presentationModel.contentPadding
            val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
            val bx = presentationModel.horizontalGapScaleFactor * paddingValues.horizontalPaddings.toPx()

            val paragraph = Paragraph(
                text = command.text, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )

            return Size(
                width = bx + paragraph.maxIntrinsicWidth,
                height = by + paragraph.height
            )
        }

        override fun getPreLayoutInfo(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel
        ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
            val hasAction = (command.action != null)
            val hasPopup = (command.secondaryContentModel != null)

            val commandButtonKind = if (!(hasAction xor hasPopup)) {
                throw IllegalArgumentException("Menu command ${command.text} needs to have either action or secondary content")
            } else if (hasPopup) {
                CommandButtonLayoutManager.CommandButtonKind.PopupOnly
            } else {
                CommandButtonLayoutManager.CommandButtonKind.ActionOnly
            }

            return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
                commandButtonKind = commandButtonKind,
                showIcon = false,
                texts = listOf(command.text),
                extraTexts = emptyList(),
                isTextInActionArea = (hasAction or command.isActionToggle) &&
                        (presentationModel.textClick == TextClick.Action),
                separatorOrientation = null,
                showPopupIcon = false
            )
        }

        override fun getLayoutInfo(
            constraints: Constraints,
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
            buttonShaper: AuroraButtonShaper
        ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
            val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo, buttonShaper)

            val paddingValues = presentationModel.contentPadding
            val top = presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
            val left = presentationModel.horizontalGapScaleFactor *
                    paddingValues.calculateLeftPadding(layoutDirection).toPx()

            val paragraph = Paragraph(
                text = command.text, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
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
                extraTextLayoutInfoList = emptyList(),
                popupActionRect = Rect.Zero
            )
        }

        override fun getActionKeyTipAnchorCenterPoint(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
        ): Offset {
            // horizontally centered at the bottom edge of the action click area
            return Offset(
                x = layoutInfo.actionClickArea.left + layoutInfo.actionClickArea.width / 2,
                y = layoutInfo.actionClickArea.top + layoutInfo.actionClickArea.height
            )
        }

        override fun getPopupKeyTipAnchorCenterPoint(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
        ): Offset {
            // horizontally centered at the bottom edge of the popup click area
            return Offset(
                x = layoutInfo.popupClickArea.left + layoutInfo.popupClickArea.width / 2,
                y = layoutInfo.popupClickArea.top + layoutInfo.popupClickArea.height
            )
        }
    }

    val MenuBar: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Menu Bar") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                return CommandButtonLayoutManagerMenuBar(
                    layoutDirection,
                    density,
                    textStyle,
                    fontFamilyResolver
                )
            }
        }
}

@Composable
internal fun AuroraWindowScope.AuroraWindowMenuBar(menuCommands: CommandGroup) {
    for (menuCommand in menuCommands.commands) {
        // Needs a non-empty text
        if (menuCommand.text.isEmpty()) {
            throw IllegalArgumentException("Top-level menu command text cannot be empty")
        }
        if (menuCommand.icon != null) {
            throw IllegalArgumentException("Icons in top-level menu commands not supported")
        }
        val hasAction = menuCommand.isActionEnabled && (menuCommand.action != null)
        val hasPopup = menuCommand.isSecondaryEnabled && (menuCommand.secondaryContentModel != null)
        if (!(hasAction xor hasPopup)) {
            throw IllegalArgumentException("Top-level menu command ${menuCommand.text} needs to have either action or secondary content")
        }
        if (menuCommand.isActionToggle) {
            throw IllegalArgumentException("Toggle top-level menu commands not supported")
        }
    }

    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().auroraBackground()
        ) {
            for (menuCommand in menuCommands.commands) {
                CommandButtonProjection(
                    contentModel = menuCommand,
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = WindowMenuBarLayout.MenuBar,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        contentPadding = CommandButtonSizingConstants.WideButtonContentPadding,
                        sides = Sides.ClosedRectangle,
                        selectedStateHighlight = SelectedStateHighlight.IconOnly
                    )
                ).project()
            }
        }
    }
}

