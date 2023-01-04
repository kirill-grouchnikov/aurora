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
package org.pushingpixels.aurora.component.layout

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.model.*
import java.util.*
import kotlin.math.max

internal open class CommandButtonLayoutManagerBig(
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
        return DpSize(32.dp, 32.dp)
    }

    protected open fun getCurrentIconWidth(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): Dp {
        return if ((command.icon != null) || presentationModel.forceAllocateSpaceForIcon)
            getPreferredIconSize(command, presentationModel).width else 0.dp
    }

    protected open fun getCurrentIconHeight(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): Dp {
        return if ((command.icon != null) || presentationModel.forceAllocateSpaceForIcon)
            getPreferredIconSize(command, presentationModel).height else 0.dp
    }

    private fun getTitleStrings(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): Pair<String, String> {
        // Break the title in two parts (the second part may be empty),
        // finding the "inflection" point. The inflection point is a space
        // character that breaks the title in two parts, such that the maximal
        // length of the first part and the second part + action label icon
        // is minimal between all possible space characters
        val text = command.text
        val tokenizer = StringTokenizer(text, " _-", true)
        if (tokenizer.countTokens() <= 1) {
            return Pair(text, "")
        } else {
            val singleLineParagraph = Paragraph(
                text = text, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )

            var bestPart1 = text
            var bestPart2 = ""

            val actionIconWidth = if (command.secondaryContentModel == null) 0.0f else
                (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                        presentationModel.horizontalGapScaleFactor).toPx() * 2 +
                        singleLineParagraph.height / 2.0f
            var currMaxLength = singleLineParagraph.maxIntrinsicWidth + actionIconWidth.toInt()
            val currLeading = StringBuilder()
            while (tokenizer.hasMoreTokens()) {
                currLeading.append(tokenizer.nextToken())
                val part1 = currLeading.toString()
                var part2 = text.substring(currLeading.length)
                if (part2.startsWith(" ")) {
                    part2 = part2.substring(1)
                }
                val len1 = Paragraph(
                    text = part1, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
                ).maxIntrinsicWidth
                val len2 = Paragraph(
                    text = part2, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
                ).maxIntrinsicWidth + actionIconWidth.toInt()
                val len = max(len1, len2)
                if (currMaxLength > len) {
                    currMaxLength = len
                    bestPart1 = part1
                    bestPart2 = part2
                }
            }
            return Pair(bestPart1, bestPart2)
        }
    }

    override fun getPreferredSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): Size {
        val paddingValues = presentationModel.contentPadding
        val bx = presentationModel.horizontalGapScaleFactor *
                (paddingValues.calculateStartPadding(layoutDirection) +
                        paddingValues.calculateEndPadding(layoutDirection)).toPx()
        val buttonText = command.text
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val layoutVGap = (CommandButtonSizingConstants.DefaultVerticalContentLayoutGap *
                presentationModel.verticalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasText = buttonText.isNotEmpty()
        val hasPopupIcon = (command.secondaryContentModel != null)

        val title1Line = Paragraph(
            text = preLayoutInfo.texts[0], style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
            density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
        )
        val title2Line = Paragraph(
            text = preLayoutInfo.texts[1], style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
            density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
        )

        val title1Width = title1Line.maxIntrinsicWidth
        val title2Width = title2Line.maxIntrinsicWidth
        val titleWidth = max(
            title1Width,
            title2Width + (if (hasPopupIcon) 4 * layoutHGap +
                    CommandButtonSizingConstants.PopupIconWidth.toPx() else 0).toInt()
        )
        val width = max(getCurrentIconWidth(command, presentationModel).toPx(), titleWidth)

        // start height with the top inset
        var height = presentationModel.verticalGapScaleFactor *
                paddingValues.calculateTopPadding().toPx()
        // icon?
        if (hasIcon) {
            // padding above the icon
            height += layoutVGap
            // icon height
            height += getCurrentIconHeight(command, presentationModel).toPx()
            // padding below the icon
            height += layoutVGap
        }
        // text?
        if (hasText) {
            // padding above the text
            height += layoutVGap
            // text height - two lines. Since second line might be empty for short texts,
            // double the height of the first line
            height += 2 * title1Line.height
            // padding below the text
            height += layoutVGap
        }
        // popup icon (no text)?
        if (!hasText && hasPopupIcon) {
            // padding above the popup icon
            height += layoutVGap
            // popup icon height - one line of text
            height += CommandButtonSizingConstants.PopupIconHeight.toPx()
            // padding below the popup icon
            height += layoutVGap
        }

        // always account for a separator for consistent visuals across
        // BIG buttons with and without two areas
        height += SeparatorSizingConstants.Thickness.toPx()

        // bottom insets
        height += presentationModel.verticalGapScaleFactor *
                paddingValues.calculateBottomPadding().toPx()

        // and remove the padding above the first and below the last elements
        height -= 2 * layoutVGap
        return Size(bx + width, height)
    }

    override fun getPreLayoutInfo(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
        val hasAction = (command.action != null)
        val hasPopup = (command.secondaryContentModel != null)

        val commandButtonKind = if (hasAction && hasPopup) {
            if (presentationModel.textClick == TextClick.Action)
                CommandButtonKind.ActionAndPopupMainAction else
                CommandButtonKind.ActionAndPopupMainPopup
        } else if (hasPopup) {
            CommandButtonKind.PopupOnly
        } else {
            CommandButtonKind.ActionOnly
        }

        val texts = getTitleStrings(command, presentationModel)

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            showIcon = true,
            texts = listOf(texts.first, texts.second),
            extraTexts = emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.Action),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Horizontal,
            showPopupIcon = commandButtonKind.hasPopup
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
        val startInset = presentationModel.horizontalGapScaleFactor *
                paddingValues.calculateStartPadding(layoutDirection).toPx()
        val endInset = presentationModel.horizontalGapScaleFactor *
                paddingValues.calculateEndPadding(layoutDirection).toPx()
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val layoutVGap = (CommandButtonSizingConstants.DefaultVerticalContentLayoutGap *
                presentationModel.verticalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasPopupIcon = (command.secondaryContentModel != null)

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var iconRect = Rect.Zero
        var separatorArea = Rect.Zero
        var popupActionRect = Rect.Zero
        val textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()
        val extraTextLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()
        val horizontalSeparatorHeight = SeparatorSizingConstants.Thickness.toPx()

        var shiftY = 0.0f
        var finalWidth = preferredSize.width
        var finalHeight = preferredSize.height
        if (constraints.hasFixedHeight && (constraints.maxHeight > 0)) {
            finalHeight = constraints.maxHeight.toFloat()
            if (finalHeight > preferredSize.height) {
                // We have more vertical space than needed to display the content.
                shiftY = (finalHeight - preferredSize.height) / 2
            }
        }
        if (constraints.hasFixedWidth && (constraints.maxWidth > 0)) {
            finalWidth = constraints.maxWidth.toFloat()
        }
        var shiftX = 0.0f
        if (finalWidth < presentationModel.minWidth.toPx()) {
            shiftX += (presentationModel.minWidth.toPx() - finalWidth) / 2.0f
            finalWidth = presentationModel.minWidth.toPx()
        }

        var y = presentationModel.verticalGapScaleFactor *
                paddingValues.calculateTopPadding().toPx() + shiftY - layoutVGap

        // icon
        if (hasIcon) {
            y += layoutVGap

            val iconWidth = getCurrentIconWidth(command, presentationModel).toPx()
            val iconHeight = getCurrentIconHeight(command, presentationModel).toPx()
            iconRect = Rect(
                left = (finalWidth - iconWidth) / 2,
                right = (finalWidth - iconWidth) / 2 + iconWidth,
                top = y,
                bottom = y + iconHeight
            )

            y += (iconHeight + layoutVGap)
        }

        // always account for a separator for consistent visuals across
        // BIG buttons with and without two areas
        if (hasIcon && preLayoutInfo.commandButtonKind.hasAction &&
            preLayoutInfo.commandButtonKind.hasPopup
        ) {
            separatorArea = Rect(
                left = 0.0f,
                right = finalWidth,
                top = y,
                bottom = y + horizontalSeparatorHeight
            )
        }
        y += horizontalSeparatorHeight

        y += layoutVGap

        val title1Line = Paragraph(
            text = preLayoutInfo.texts[0], style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
            density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
        )

        var lastTextLineWidth = title1Line.maxIntrinsicWidth
        val line1LayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
            text = preLayoutInfo.texts[0],
            textRect = Rect(
                left = (startInset + (finalWidth - lastTextLineWidth - startInset - endInset) / 2),
                right = (startInset + (finalWidth - lastTextLineWidth - startInset - endInset) / 2) + lastTextLineWidth,
                top = y,
                bottom = y + title1Line.height
            )
        )
        y += title1Line.height

        val title2Line = Paragraph(
            text = preLayoutInfo.texts[1], style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
            density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
        )

        val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
        val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()

        lastTextLineWidth = title2Line.maxIntrinsicWidth

        val extraWidth = if (hasPopupIcon) 4 * layoutHGap + popupIconWidth else 0

        val line2x = if (ltr)
            (startInset + (finalWidth - lastTextLineWidth - extraWidth.toFloat() - startInset - endInset) / 2)
        else
            (finalWidth - startInset - lastTextLineWidth
                    - (finalWidth - lastTextLineWidth - extraWidth.toFloat() - startInset - endInset) / 2)
        val line2LayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
            text = preLayoutInfo.texts[1],
            textRect = Rect(
                left = line2x,
                right = line2x + lastTextLineWidth,
                top = y,
                bottom = y + title2Line.height
            )
        )
        textLayoutInfoList.add(line1LayoutInfo)
        textLayoutInfoList.add(line2LayoutInfo)

        if (hasPopupIcon) {
            val popupActionX = if (line2LayoutInfo.textRect.width > 0) {
                if (ltr)
                    line2LayoutInfo.textRect.right + 4 * layoutHGap
                else
                    line2LayoutInfo.textRect.left - 4 * layoutHGap - popupIconWidth
            } else (finalWidth - popupIconWidth) / 2
            popupActionRect = Rect(
                left = popupActionX,
                right = popupActionX + popupIconWidth,
                top = y + (title1Line.height - popupIconHeight) / 2,
                bottom = y + (title1Line.height - popupIconHeight) / 2 + popupIconHeight
            )
        }

        var actionClickArea = Rect.Zero
        var popupClickArea = Rect.Zero

        when (preLayoutInfo.commandButtonKind) {
            CommandButtonKind.ActionOnly -> {
                actionClickArea = Rect(
                    left = 0.0f,
                    top = 0.0f,
                    right = finalWidth,
                    bottom = finalHeight
                )
            }
            CommandButtonKind.PopupOnly -> {
                popupClickArea = Rect(
                    left = 0.0f,
                    top = 0.0f,
                    right = finalWidth,
                    bottom = finalHeight
                )
            }
            CommandButtonKind.ActionAndPopupMainAction,
            CommandButtonKind.ActionAndPopupMainPopup -> {
                // no break (all popup) if button has no icon
                if (hasIcon) {
                    val yBorderBetweenActionAndPopupAreas = iconRect.bottom + layoutVGap

                    actionClickArea = Rect(
                        left = 0.0f,
                        right = finalWidth,
                        top = 0.0f,
                        bottom = yBorderBetweenActionAndPopupAreas
                    )
                    popupClickArea = Rect(
                        left = 0.0f,
                        right = finalWidth,
                        top = yBorderBetweenActionAndPopupAreas,
                        bottom = finalHeight
                    )
                } else {
                    popupClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )
                }
            }
        }
        return CommandButtonLayoutManager.CommandButtonLayoutInfo(
            fullSize = Size(finalWidth, finalHeight),
            actionClickArea = actionClickArea,
            popupClickArea = popupClickArea,
            separatorArea = separatorArea,
            iconRect = iconRect,
            textLayoutInfoList = textLayoutInfoList,
            extraTextLayoutInfoList = extraTextLayoutInfoList,
            popupActionRect = popupActionRect
        )
    }
}

internal class CommandButtonLayoutManagerBigFitToIcon(
    layoutDirection: LayoutDirection,
    _density: Density,
    textStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver
) : CommandButtonLayoutManagerBig(layoutDirection, _density, textStyle, fontFamilyResolver) {
    override fun getPreferredIconSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): DpSize {
        return presentationModel.iconDimension ?: super.getPreferredIconSize(
            command,
            presentationModel
        )
    }
}
