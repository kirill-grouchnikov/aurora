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
import org.pushingpixels.aurora.component.model.*
import kotlin.math.max

internal class CommandButtonLayoutManagerTile(
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
        return 32.dp
    }

    private fun getPreferredSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        paddingValues: PaddingValues
    ): Size {
        val by =
            (paddingValues.calculateTopPadding() + paddingValues.calculateBottomPadding()).toPx()
        val buttonText = command.text
        val extraText = command.extraText
        val layoutHGap = (2.dp * presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null) or (extraText != null)
        val hasPopupIcon = (command.secondaryContentModel != null)
        val prefIconSize = getPreferredIconSize(command, presentationModel).toPx()

        // start with the left insets
        var width = paddingValues.calculateStartPadding(layoutDirection).toPx()
        // icon?
        if (hasIcon) {
            // padding before the icon
            width += layoutHGap
            // icon width
            width += prefIconSize
            // padding after the icon
            width += layoutHGap
        }
        // text?
        var textHeight = 0f
        if (hasText) {
            // padding before the text
            width += layoutHGap

            // text width
            var textWidth = 0.0f
            if (buttonText != null) {
                val textParagraph = Paragraph(
                    text = buttonText, style = textStyle, width = Float.POSITIVE_INFINITY,
                    density = _density, maxLines = 1, resourceLoader = resourceLoader
                )
                textWidth = textParagraph.maxIntrinsicWidth
                textHeight += textParagraph.height
            }
            if (extraText != null) {
                val extraTextParagraph = Paragraph(
                    text = extraText, style = textStyle, width = Float.POSITIVE_INFINITY,
                    density = _density, maxLines = 1, resourceLoader = resourceLoader
                )
                textWidth = max(textWidth, extraTextParagraph.maxIntrinsicWidth)
                textHeight += extraTextParagraph.height
            }
            width += textWidth.toInt()
            // padding after the text
            width += layoutHGap
        }
        // popup icon?
        if (hasPopupIcon) {
            // padding before the popup icon
            width += 2 * layoutHGap
            // popup icon width
            width += 1 + CommandButtonSizingConstants.PopupIconWidth.toPx()
            // padding after the popup icon
            width += 2 * layoutHGap
        }

        // right insets
        width += paddingValues.calculateEndPadding(layoutDirection).toPx()

        // and remove the padding before the first and after the last elements
        width -= 2 * layoutHGap
        return Size(width, by + max(prefIconSize, textHeight))
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

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            texts = listOf(command.text),
            extraTexts = if (command.extraText != null) listOf(command.extraText) else emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.ACTION),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.VERTICAL
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

        val buttonText = command.text
        val buttonExtraText = command.extraText
        val layoutHGap = (2.dp * presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null) or (buttonExtraText != null)
        val hasPopup = (command.secondaryContentModel != null)
        val iconSize = getPreferredIconSize(command, presentationModel).toPx()

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var iconRect = Rect.Zero
        var popupActionRect = Rect.Zero
        val textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()
        val extraTextLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()

        var shiftX = 0.0f
        var finalWidth = preferredSize.width
        var finalHeight = preferredSize.height
        if (constraints.hasFixedWidth) {
            finalWidth = constraints.maxWidth.toFloat()
            if (finalWidth > preferredSize.width) {
                // We have more horizontal space than needed to display the content.
                // Consult the horizontal alignment attribute of the command button to see
                // how we should shift the content horizontally.
                when (presentationModel.horizontalAlignment) {
                    HorizontalAlignment.LEADING -> if (!ltr) {
                        // shift everything to the right
                        shiftX = finalWidth - preferredSize.width
                    }
                    HorizontalAlignment.CENTER ->
                        // shift everything to be centered horizontally
                        shiftX = (finalWidth - preferredSize.width) / 2
                    HorizontalAlignment.TRAILING -> if (ltr) {
                        // shift everything to the right
                        shiftX = finalWidth - preferredSize.width
                    }
                }
            }
        }
        if (constraints.hasFixedHeight) {
            finalHeight = constraints.maxHeight.toFloat()
        }

        // TODO - support RTL

//        if (ltr) {
        var x = paddingValues.calculateStartPadding(layoutDirection).toPx() + shiftX - layoutHGap

        // icon
        if (hasIcon) {
            x += layoutHGap
            iconRect = Rect(
                left = x,
                right = x + iconSize,
                top = (finalHeight - iconSize) / 2,
                bottom = (finalHeight - iconSize) / 2 + iconSize
            )
            x += iconSize + layoutHGap
        }

        // text
        var textHeight = 0.0f
        if (hasText) {
            x += layoutHGap
            val hasExtraText = (buttonExtraText != null)

            val paragraph = Paragraph(
                text = command.text, style = textStyle, width = Float.POSITIVE_INFINITY,
                density = _density, maxLines = 1, resourceLoader = resourceLoader
            )

            textHeight = paragraph.height
            val lineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                text = command.text,
                textRect = Rect(
                    left = x,
                    right = x + paragraph.maxIntrinsicWidth,
                    top = (finalHeight - (if (hasExtraText) 2 else 1) * textHeight) / 2.0f,
                    bottom = (finalHeight - (if (hasExtraText) 2 else 1) * textHeight) / 2.0f + textHeight
                )
            )
            textLayoutInfoList.add(lineLayoutInfo)

            if (command.extraText != null) {
                val extraParagraph = Paragraph(
                    text = command.extraText,
                    style = textStyle,
                    width = Float.POSITIVE_INFINITY,
                    density = _density,
                    maxLines = 1,
                    resourceLoader = resourceLoader
                )

                val extraLineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                    text = command.extraText,
                    textRect = Rect(
                        left = x,
                        right = x + extraParagraph.maxIntrinsicWidth,
                        top = lineLayoutInfo.textRect.bottom,
                        bottom = lineLayoutInfo.textRect.bottom + textHeight
                    )
                )

                extraTextLayoutInfoList.add(extraLineLayoutInfo)

                x += max(
                    lineLayoutInfo.textRect.width,
                    extraLineLayoutInfo.textRect.width
                )
            } else {
                x += lineLayoutInfo.textRect.width
            }

            x += layoutHGap
        }
        if (hasPopup) {
            if (hasText && hasIcon) {
                x += 2 * layoutHGap
            }
            val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
            val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
            popupActionRect = Rect(
                left = x,
                right = x + 4 + popupIconWidth,
                top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
            )
        }
        var xBorderBetweenActionAndPopup = 0.0f
        var actionClickArea = Rect.Zero
        var popupClickArea = Rect.Zero
        var separatorArea = Rect.Zero
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
            CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION -> {
                // 1. break before popup icon if button has text or icon
                // 2. no break (all popup) if button has no text and no icon
                if (hasText || hasIcon) {
                    // shift popup action rectangle to the right to
                    // accommodate the vertical separator
                    popupActionRect = popupActionRect.translate(
                        translateX = SeparatorSizingConstants.Thickness.toPx(),
                        translateY = 0.0f
                    )
                    xBorderBetweenActionAndPopup = popupActionRect.left - 2.0f * layoutHGap

                    actionClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = xBorderBetweenActionAndPopup,
                        bottom = finalHeight
                    )

                    popupClickArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )

                    separatorArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        right = xBorderBetweenActionAndPopup + SeparatorSizingConstants.Thickness.toPx(),
                        top = 0.0f,
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
            CommandButtonKind.ACTION_AND_POPUP_MAIN_POPUP -> {
                // 1. break after icon if button has icon
                // 2. no break (all popup) if button has no icon
                if (hasIcon) {
                    // shift text rectangle and popup action rectangle to the
                    // right to accommodate the vertical separator
                    for (textLayoutInfo in textLayoutInfoList) {
                        textLayoutInfo.textRect = textLayoutInfo.textRect.translate(
                            translateX = SeparatorSizingConstants.Thickness.toPx(),
                            translateY = 0.0f
                        )
                    }
                    for (extraTextLayoutInfo in extraTextLayoutInfoList) {
                        extraTextLayoutInfo.textRect = extraTextLayoutInfo.textRect.translate(
                            translateX = SeparatorSizingConstants.Thickness.toPx(),
                            translateY = 0.0f
                        )
                    }
                    popupActionRect = popupActionRect.translate(
                        translateX = SeparatorSizingConstants.Thickness.toPx(),
                        translateY = 0.0f
                    )
                    xBorderBetweenActionAndPopup = (iconRect.left + iconRect.width + layoutHGap)
                    actionClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = xBorderBetweenActionAndPopup,
                        bottom = finalHeight
                    )

                    popupClickArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )

                    separatorArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        right = xBorderBetweenActionAndPopup + SeparatorSizingConstants.Thickness.toPx(),
                        top = 0.0f,
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
//        } else {
//            var x: Int = width - ins.right - shiftX + layoutHGap
//
//            // icon
//            if (hasIcon) {
//                x -= layoutHGap
//                val iconHeight: Int = buttonIcon.getIconHeight()
//                val iconWidth: Int = buttonIcon.getIconWidth()
//                result.iconRect.x = x - iconWidth
//                result.iconRect.y = (height - iconHeight) / 2
//                result.iconRect.width = iconWidth
//                result.iconRect.height = iconHeight
//                x -= iconWidth + layoutHGap
//            }
//
//            // text
//            if (hasText) {
//                x -= layoutHGap
//                val lineLayoutInfo = TextLayoutInfo()
//                lineLayoutInfo.text = commandButton.getText()
//                lineLayoutInfo.textRect = Rectangle()
//                lineLayoutInfo.textRect.width =
//                    if (buttonText == null) 0 else fm.stringWidth(buttonText)
//                lineLayoutInfo.textRect.x = x - lineLayoutInfo.textRect.width
//                lineLayoutInfo.textRect.y = (height - 2 * labelHeight) / 2
//                lineLayoutInfo.textRect.height = labelHeight
//                result.textLayoutInfoList = ArrayList<TextLayoutInfo>()
//                result.textLayoutInfoList.add(lineLayoutInfo)
//                val extraText: String = commandButton.getExtraText()
//                val extraLineLayoutInfo = TextLayoutInfo()
//                extraLineLayoutInfo.text = extraText
//                extraLineLayoutInfo.textRect = Rectangle()
//                extraLineLayoutInfo.textRect.width =
//                    if (extraText == null) 0 else fm.stringWidth(buttonText)
//                extraLineLayoutInfo.textRect.x = (x
//                        - extraLineLayoutInfo.textRect.width)
//                extraLineLayoutInfo.textRect.y = (lineLayoutInfo.textRect.y
//                        + labelHeight)
//                extraLineLayoutInfo.textRect.height = labelHeight
//                result.extraTextLayoutInfoList = ArrayList<TextLayoutInfo>()
//                result.extraTextLayoutInfoList.add(extraLineLayoutInfo)
//                x -= Math.max(
//                    lineLayoutInfo.textRect.width,
//                    extraLineLayoutInfo.textRect.width
//                )
//                x -= layoutHGap
//            }
//            if (hasPopupIcon) {
//                x -= 2 * layoutHGap
//                result.popupActionRect.width = 1 + labelHeight / 2
//                result.popupActionRect.x = x - result.popupActionRect.width
//                result.popupActionRect.y = (height - labelHeight) / 2 - 1
//                result.popupActionRect.height = labelHeight + 2
//                x -= result.popupActionRect.width
//                x -= 2 * layoutHGap
//            }
//            var xBorderBetweenActionAndPopup = 0
//            val verticalSeparatorWidth: Int = JSeparator(JSeparator.VERTICAL)
//                .getPreferredSize().width
//            when (buttonKind) {
//                ACTION_ONLY -> {
//                    result.actionClickArea.x = 0
//                    result.actionClickArea.y = 0
//                    result.actionClickArea.width = width
//                    result.actionClickArea.height = height
//                    result.isTextInActionArea = true
//                }
//                POPUP_ONLY -> {
//                    result.popupClickArea.x = 0
//                    result.popupClickArea.y = 0
//                    result.popupClickArea.width = width
//                    result.popupClickArea.height = height
//                    result.isTextInActionArea = false
//                }
//                ACTION_AND_POPUP_MAIN_ACTION -> {
//                    // 1. break before popup icon if button has text or icon
//                    // 2. no break (all popup) if button has no text and no icon
//                    if (hasText || hasIcon) {
//                        // shift popup action rectangle to the left to
//                        // accommodate the vertical separator
//                        result.popupActionRect.x -= verticalSeparatorWidth
//                        xBorderBetweenActionAndPopup = (result.popupActionRect.x
//                                + result.popupActionRect.width + 2 * layoutHGap)
//                        result.actionClickArea.x = xBorderBetweenActionAndPopup
//                        result.actionClickArea.y = 0
//                        result.actionClickArea.width = (width
//                                - xBorderBetweenActionAndPopup)
//                        result.actionClickArea.height = height
//                        result.popupClickArea.x = 0
//                        result.popupClickArea.y = 0
//                        result.popupClickArea.width = xBorderBetweenActionAndPopup
//                        result.popupClickArea.height = height
//                        result.separatorOrientation = CommandButtonSeparatorOrientation.VERTICAL
//                        result.separatorArea = Rectangle()
//                        result.separatorArea.x = xBorderBetweenActionAndPopup
//                        result.separatorArea.y = 0
//                        result.separatorArea.width = verticalSeparatorWidth
//                        result.separatorArea.height = height
//                    } else {
//                        result.popupClickArea.x = 0
//                        result.popupClickArea.y = 0
//                        result.popupClickArea.width = width
//                        result.popupClickArea.height = height
//                    }
//                    result.isTextInActionArea = true
//                }
//                ACTION_AND_POPUP_MAIN_POPUP -> {
//                    // 1. break after icon if button has icon
//                    // 2. no break (all popup) if button has no icon
//                    if (hasIcon) {
//                        // shift text rectangles and popup action rectangle to the
//                        // left to accommodate the vertical separator
//                        if (result.textLayoutInfoList != null) {
//                            for (textLayoutInfo in result.textLayoutInfoList) {
//                                textLayoutInfo.textRect.x -= verticalSeparatorWidth
//                            }
//                        }
//                        if (result.extraTextLayoutInfoList != null) {
//                            for (extraTextLayoutInfo in result.extraTextLayoutInfoList) {
//                                extraTextLayoutInfo.textRect.x -= verticalSeparatorWidth
//                            }
//                        }
//                        result.popupActionRect.x -= verticalSeparatorWidth
//                        xBorderBetweenActionAndPopup = (result.iconRect.x
//                                - layoutHGap)
//                        result.actionClickArea.x = xBorderBetweenActionAndPopup
//                        result.actionClickArea.y = 0
//                        result.actionClickArea.width = (width
//                                - xBorderBetweenActionAndPopup)
//                        result.actionClickArea.height = height
//                        result.popupClickArea.x = 0
//                        result.popupClickArea.y = 0
//                        result.popupClickArea.width = xBorderBetweenActionAndPopup
//                        result.popupClickArea.height = height
//                        result.separatorOrientation = CommandButtonSeparatorOrientation.VERTICAL
//                        result.separatorArea = Rectangle()
//                        result.separatorArea.x = xBorderBetweenActionAndPopup
//                        result.separatorArea.y = 0
//                        result.separatorArea.width = verticalSeparatorWidth
//                        result.separatorArea.height = height
//                    } else {
//                        result.popupClickArea.x = 0
//                        result.popupClickArea.y = 0
//                        result.popupClickArea.width = width
//                        result.popupClickArea.height = height
//                    }
//                    result.isTextInActionArea = false
//                }
//            }
//        }
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