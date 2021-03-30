/*
 * Copyright (c) 2005-2021 Radiance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.component.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.SeparatorSizingConstants
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonKind
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.TextClick
import java.util.*
import kotlin.math.max

class CommandButtonLayoutManagerBig(
    override val layoutDirection: LayoutDirection,
    private val _density: Density,
    private val textStyle: TextStyle,
    private val resourceLoader: Font.ResourceLoader
) : CommandButtonLayoutManager {
    override val density = _density.density
    override val fontScale = _density.fontScale

    override fun getPreferredIconSize(): Dp {
        return 32.dp
    }

    private fun getTitleStrings(
        command: Command,
        presentationModel: CommandButtonPresentationModel
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
                text = text, style = textStyle, width = Float.POSITIVE_INFINITY,
                density = _density, maxLines = 1, resourceLoader = resourceLoader
            )

            var bestPart1 = text
            var bestPart2 = ""

            var currMaxLength = singleLineParagraph.maxIntrinsicWidth
            val actionIconWidth = if (command.secondaryContentModel == null) 0.0f else
                (2.dp * presentationModel.horizontalGapScaleFactor).toPx() * 2 + singleLineParagraph.height / 2.0f
            val currLeading = StringBuilder()
            while (tokenizer.hasMoreTokens()) {
                currLeading.append(tokenizer.nextToken())
                val part1 = currLeading.toString()
                val part2 = text.substring(currLeading.length)
                val len1 = Paragraph(
                    text = part1, style = textStyle, width = Float.POSITIVE_INFINITY,
                    density = _density, maxLines = 1, resourceLoader = resourceLoader
                ).maxIntrinsicWidth
                val len2 = Paragraph(
                    text = part2, style = textStyle, width = Float.POSITIVE_INFINITY,
                    density = _density, maxLines = 1, resourceLoader = resourceLoader
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

    private fun getPreferredSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
        paddingValues: PaddingValues
    ): Size {
        val bx =
            (paddingValues.calculateStartPadding(layoutDirection) +
                    paddingValues.calculateEndPadding(layoutDirection)).toPx()
        val buttonText = command.text
        val layoutHGap = (2.dp * presentationModel.horizontalGapScaleFactor).toPx()
        val layoutVGap = (2.dp * presentationModel.verticalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null)
        val hasPopupIcon = (command.secondaryContentModel != null)
        val prefIconSize = getPreferredIconSize().toPx()

        val title1Line = Paragraph(
            text = preLayoutInfo.texts[0], style = textStyle, width = Float.POSITIVE_INFINITY,
            density = _density, maxLines = 1, resourceLoader = resourceLoader
        )
        val title2Line = Paragraph(
            text = preLayoutInfo.texts[1], style = textStyle, width = Float.POSITIVE_INFINITY,
            density = _density, maxLines = 1, resourceLoader = resourceLoader
        )

        val title1Width = title1Line.maxIntrinsicWidth
        val title2Width = title2Line.maxIntrinsicWidth
        val titleWidth = max(
            title1Width,
            title2Width + (if (hasPopupIcon) 4 * layoutHGap +
                    CommandButtonSizingConstants.PopupIconWidth.toPx() else 0).toInt()
        )
        val width = max(prefIconSize, titleWidth)

        // start height with the top inset
        var height = paddingValues.calculateTopPadding().toPx()
        // icon?
        if (hasIcon) {
            // padding above the icon
            height += layoutVGap
            // icon height
            height += prefIconSize
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
        height += paddingValues.calculateBottomPadding().toPx()

        // and remove the padding above the first and below the last elements
        height -= 2 * layoutVGap
        return Size(bx + width, height)
    }

    override fun getPreLayoutInfo(
        command: Command,
        presentationModel: CommandButtonPresentationModel
    ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
        val hasAction = (command.action != null)
        val hasPopup = (command.secondaryContentModel != null)

        val commandButtonKind = if (hasAction && hasPopup) {
            if (presentationModel.textClick == TextClick.ACTION)
                CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION else
                CommandButtonKind.ACTION_AND_POPUP_MAIN_POPUP
        } else if (hasPopup) {
            CommandButtonKind.POPUP_ONLY
        } else {
            CommandButtonKind.ACTION_ONLY
        }

        val texts = getTitleStrings(command, presentationModel)

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            texts = listOf(texts.first, texts.second),
            extraTexts = emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.ACTION),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.HORIZONTAL
        )
    }

    override fun getLayoutInfo(
        constraints: Constraints,
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
        paddingValues: PaddingValues
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSize = getPreferredSize(
            command, presentationModel,
            preLayoutInfo, paddingValues
        )

        val startInset = paddingValues.calculateStartPadding(layoutDirection).toPx()
        val endInset = paddingValues.calculateEndPadding(layoutDirection).toPx()
        val buttonText = command.text
        val layoutHGap = (2.dp * presentationModel.horizontalGapScaleFactor).toPx()
        val layoutVGap = (2.dp * presentationModel.verticalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null)
        val hasPopupIcon = (command.secondaryContentModel != null)
        val iconSize = getPreferredIconSize().toPx()

        var iconRect = Rect.Zero
        var separatorArea = Rect.Zero
        var popupActionRect = Rect.Zero
        val textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()
        val extraTextLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()

        var shiftY = 0.0f
        var finalWidth = preferredSize.width
        var finalHeight = preferredSize.height
        if (constraints.hasFixedHeight) {
            finalHeight = constraints.maxHeight.toFloat()
            if (finalHeight > preferredSize.height) {
                // We have more vertical space than needed to display the content.
                shiftY = (finalHeight - preferredSize.height) / 2
            }
        }
        if (constraints.hasFixedWidth) {
            finalWidth = constraints.maxWidth.toFloat()
        }

        var y = paddingValues.calculateTopPadding().toPx() + shiftY - layoutVGap

        // icon
        if (hasIcon) {
            y += layoutVGap

            iconRect = Rect(
                left = (finalWidth - iconSize) / 2,
                right = (finalWidth - iconSize) / 2 + iconSize,
                top = y,
                bottom = y + iconSize
            )

            y += (iconSize + layoutVGap)
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
                bottom = y + SeparatorSizingConstants.Thickness.toPx()
            )
        }
        y += SeparatorSizingConstants.Thickness.toPx()

        var lastTextLineWidth = 0.0f
        // text
        //if (hasText) {
        y += layoutVGap

        val title1Line = Paragraph(
            text = preLayoutInfo.texts[0], style = textStyle, width = Float.POSITIVE_INFINITY,
            density = _density, maxLines = 1, resourceLoader = resourceLoader
        )

        lastTextLineWidth = title1Line.maxIntrinsicWidth
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
            text = preLayoutInfo.texts[1], style = textStyle, width = Float.POSITIVE_INFINITY,
            density = _density, maxLines = 1, resourceLoader = resourceLoader
        )

        val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
        val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
        lastTextLineWidth = title2Line.maxIntrinsicWidth
        //val popupIconSize = getPopupIconSize(_density)
        val extraWidth = if (hasPopupIcon) 4 * layoutHGap + popupIconWidth else 0
        // TODO - RTL
        val line2x =
            (startInset + (finalWidth - lastTextLineWidth - extraWidth.toFloat() - startInset - endInset) / 2)
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
            // TODO - RTL
            val popupActionX = if (line2LayoutInfo.textRect.width > 0)
                line2LayoutInfo.textRect.right + 4 * layoutHGap
            else (finalWidth - popupIconWidth) / 2
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
            CommandButtonKind.ACTION_ONLY -> {
                actionClickArea = Rect(
                    left = 0.0f,
                    top = 0.0f,
                    right = finalWidth,
                    bottom = finalHeight
                )
            }
            CommandButtonKind.POPUP_ONLY -> {
                popupClickArea = Rect(
                    left = 0.0f,
                    top = 0.0f,
                    right = finalWidth,
                    bottom = finalHeight
                )
            }
            CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION,
            CommandButtonKind.ACTION_AND_POPUP_MAIN_POPUP -> {
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