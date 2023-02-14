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
package org.pushingpixels.aurora.component.utils.appmenu

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
import org.pushingpixels.aurora.component.model.BaseCommand
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonKind

internal class CommandButtonLayoutManagerRibbonApplicationMenuButton(
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
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
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
        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = CommandButtonKind.PopupOnly,
            showIcon = false,
            texts = listOf(command.text),
            extraTexts = emptyList(),
            isTextInActionArea = true,
            separatorOrientation = null,
            showPopupIcon = false
        )
    }

    override fun getLayoutInfo(
        constraints: Constraints,
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo)

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
            actionClickArea = Rect.Zero,
            popupClickArea = Rect(
                left = 0.0f,
                right = preferredSize.width,
                top = 0.0f,
                bottom = preferredSize.height
            ),
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
}
