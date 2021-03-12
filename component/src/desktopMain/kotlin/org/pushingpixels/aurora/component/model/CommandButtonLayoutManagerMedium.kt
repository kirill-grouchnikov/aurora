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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.SeparatorSizingConstants
import kotlin.math.max

internal class CommandButtonLayoutManagerMedium(
    override val layoutDirection: LayoutDirection,
    val _density: Density,
    val textStyle: TextStyle,
    val resourceLoader: Font.ResourceLoader
) : CommandButtonLayoutManager {
    override val density = _density.density
    override val fontScale = _density.fontScale

    override fun getPreferredIconSize(): Dp {
        return 16.dp
    }

    protected val iconTextGapFactor: Float
        protected get() = 1.0f


    private fun getPreferredSize(
        command: Command,
        presentationModel: CommandPresentationModel,
        paddingValues: PaddingValues
    ): Size {
        val by =
            (paddingValues.calculateTopPadding() + paddingValues.calculateBottomPadding()).toPx()
        val buttonText = command.text
        val layoutHGap = (2.dp * presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null)
        val hasPopupIcon = (command.secondaryContentModel != null)
        val prefIconSize = (if (hasIcon) getPreferredIconSize() else 0.dp).toPx()

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
            width += if (hasIcon) {
                (layoutHGap * iconTextGapFactor)
            } else {
                layoutHGap
            }

            val paragraph = Paragraph(
                text = command.text, style = textStyle, width = Float.POSITIVE_INFINITY,
                density = _density, maxLines = 1, resourceLoader = resourceLoader
            )
            width += paragraph.maxIntrinsicWidth
            textHeight = paragraph.height

            // padding after the text
            width += layoutHGap
        }
        // popup icon?
        if (hasPopupIcon) {
            // padding before the popup icon
            if (hasText && hasIcon) {
                width += 2 * layoutHGap
            }
            // icon width
            width += 1 + (getPreferredIconSize().toPx() / 2.0f)
            // padding after the popup icon
            width += 2 * layoutHGap
        }

        // TODO - account for separator
//            // separator?
//            val buttonKind: CommandButtonKind = commandButton.getCommandButtonKind()
//            var hasSeparator = false
//            if (buttonKind === CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION
//                && (hasIcon || hasText)
//            ) {
//                hasSeparator = true
//            }
//            if (buttonKind === CommandButtonKind.ACTION_AND_POPUP_MAIN_POPUP
//                && hasIcon
//            ) {
//                hasSeparator = true
//            }
//            if (hasSeparator) {
//                // space for a vertical separator
//                width += JSeparator(JSeparator.VERTICAL).getPreferredSize().width
//            }

        // right insets
        width += paddingValues.calculateEndPadding(layoutDirection).toPx()

        // and remove the padding before the first and after the last elements
        width -= 2 * layoutHGap
        return Size(
            width, by + max(prefIconSize, textHeight)
        )
    }

    override fun getLayoutInfo(
        command: Command,
        presentationModel: CommandPresentationModel,
        paddingValues: PaddingValues
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSize = getPreferredSize(command, presentationModel, paddingValues)

        val buttonText = command.text
        val layoutHGap = (2.dp * presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null)
        val hasAction = (command.action != null)
        val hasPopup = (command.secondaryContentModel != null)
        val iconSize = (if (hasIcon) getPreferredIconSize() else 0.dp).toPx()
        //val prefWidth = preferredSize.width
        var shiftX = 0

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var iconRect = Rect.Zero
        var popupActionRect = Rect.Zero
        var textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()
        var separatorOrientation =
            CommandButtonLayoutManager.CommandButtonSeparatorOrientation.VERTICAL
        var isTextInActionArea = true

//        if (width > prefWidth) {
//            // We have more horizontal space than needed to display the content.
//            // Consult the horizontal alignment attribute of the command button to see
//            // how we should shift the content horizontally.
//            when (commandButton.getHorizontalAlignment()) {
//                SwingConstants.LEADING -> if (!ltr) {
//                    // shift everything to the right
//                    shiftX = width - prefWidth
//                }
//                SwingConstants.CENTER ->                     // shift everything to be centered horizontally
//                    shiftX = (width - prefWidth) / 2
//                SwingConstants.TRAILING -> if (ltr) {
//                    // shift everything to the right
//                    shiftX = width - prefWidth
//                }
//            }
//        }

        val commandButtonKind: CommandButtonKind
        if (hasAction && hasPopup) {
            commandButtonKind = if (presentationModel.textClick == TextClick.ACTION)
                CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION else
                CommandButtonKind.ACTION_AND_POPUP_MAIN_POPUP
        } else if (hasPopup) {
            commandButtonKind = CommandButtonKind.POPUP_ONLY
        } else {
            commandButtonKind = CommandButtonKind.ACTION_ONLY
        }

//        val fm: FontMetrics = SubstanceMetricsUtilities.getFontMetrics(commandButton.getFont())
//        val labelHeight = fm.ascent + fm.descent
//        val layoutHGap: Int = FlamingoUtilities.getHLayoutGap(commandButton)
//        if (ltr) {
        var x = paddingValues.calculateStartPadding(layoutDirection).toPx() + shiftX - layoutHGap

        // icon
        if (hasIcon) {
            x += layoutHGap
            iconRect = Rect(
                left = x,
                right = x + iconSize,
                top = (preferredSize.height - iconSize) / 2,
                bottom = (preferredSize.height - iconSize) / 2 + iconSize
            )
            x += iconSize + layoutHGap
        }

        // text
        var textHeight = 0.0f
        if (hasText) {
            x += if (hasIcon) {
                layoutHGap * iconTextGapFactor
            } else {
                layoutHGap
            }

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
                    top = (preferredSize.height - textHeight) / 2.0f,
                    bottom = (preferredSize.height - textHeight) / 2.0f + textHeight
                )
            )
            textLayoutInfoList.add(lineLayoutInfo)

            x += lineLayoutInfo.textRect.width
            x += layoutHGap
        }
        if (hasPopup) {
            if (hasText && hasIcon) {
                x += 2 * layoutHGap
            }
            val popupIconSize = if (textHeight > 0) textHeight else getPreferredIconSize().toPx()
            if (!hasText && !hasIcon) {
                // horizontally center the popup icon
                x += (preferredSize.width - 2 * layoutHGap - 1 - popupIconSize / 2.0f) / 2.0f
            }
            popupActionRect = Rect(
                left = x,
                right = x + 4 + popupIconSize / 2.0f,
                top = (preferredSize.height - popupIconSize) / 2.0f - 1.0f,
                bottom = (preferredSize.height - popupIconSize) / 2.0f + popupIconSize + 1.0f
            )
        }
        var xBorderBetweenActionAndPopup = 0.0f
        var actionClickArea = Rect.Zero
        var popupClickArea = Rect.Zero
        var separatorArea = Rect.Zero
//            val verticalSeparatorWidth: Int = JSeparator(JSeparator.VERTICAL)
//                .getPreferredSize().width
        when (commandButtonKind) {
            CommandButtonKind.ACTION_ONLY -> {
                actionClickArea = Rect(
                    left = 0.0f,
                    top = 0.0f,
                    right = preferredSize.width,
                    bottom = preferredSize.height
                )
                isTextInActionArea = true
            }
            CommandButtonKind.POPUP_ONLY -> {
                popupClickArea = Rect(
                    left = 0.0f,
                    top = 0.0f,
                    right = preferredSize.width,
                    bottom = preferredSize.height
                )
                isTextInActionArea = false
            }
            CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION ->
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
                        bottom = preferredSize.height
                    )

                    popupClickArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        top = 0.0f,
                        right = preferredSize.width,
                        bottom = preferredSize.height
                    )

                    separatorOrientation =
                        CommandButtonLayoutManager.CommandButtonSeparatorOrientation.VERTICAL

                    separatorArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        right = xBorderBetweenActionAndPopup + SeparatorSizingConstants.Thickness.toPx(),
                        top = 0.0f,
                        bottom = preferredSize.height
                    )
                    isTextInActionArea = true
                } else {
                    popupClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = preferredSize.width,
                        bottom = preferredSize.height
                    )
                    isTextInActionArea = false
                }
            CommandButtonKind.ACTION_AND_POPUP_MAIN_POPUP ->
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
                    popupActionRect = popupActionRect.translate(
                        translateX = SeparatorSizingConstants.Thickness.toPx(),
                        translateY = 0.0f
                    )
                    xBorderBetweenActionAndPopup = (iconRect.left + iconRect.width + layoutHGap)
                    actionClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = xBorderBetweenActionAndPopup,
                        bottom = preferredSize.height
                    )

                    popupClickArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        top = 0.0f,
                        right = preferredSize.width,
                        bottom = preferredSize.height
                    )

                    separatorOrientation =
                        CommandButtonLayoutManager.CommandButtonSeparatorOrientation.VERTICAL

                    separatorArea = Rect(
                        left = xBorderBetweenActionAndPopup,
                        right = xBorderBetweenActionAndPopup + SeparatorSizingConstants.Thickness.toPx(),
                        top = 0.0f,
                        bottom = preferredSize.height
                    )
                    isTextInActionArea = false
                } else {
                    popupClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = preferredSize.width,
                        bottom = preferredSize.height
                    )
                    isTextInActionArea = true
                }
        }
//        } else {
//            var x: Int = width - ins.right - shiftX + layoutHGap
//
//            // icon
//            if (hasIcon) {
//                x -= layoutHGap
//                result.iconRect.x = x - iconSize
//                result.iconRect.y = (height - iconSize) / 2
//                result.iconRect.width = iconSize
//                result.iconRect.height = iconSize
//                x -= iconSize + layoutHGap
//            }
//
//            // text
//            if (hasText) {
//                x -= if (hasIcon) {
//                    (layoutHGap * iconTextGapFactor).toInt()
//                } else {
//                    layoutHGap
//                }
//                val lineLayoutInfo = TextLayoutInfo()
//                lineLayoutInfo.text = commandButton.getText()
//                lineLayoutInfo.textRect = Rectangle()
//                result.textLayoutInfoList = ArrayList<TextLayoutInfo>()
//                result.textLayoutInfoList.add(lineLayoutInfo)
//                lineLayoutInfo.textRect.width = fm.stringWidth(buttonText)
//                lineLayoutInfo.textRect.x = x - lineLayoutInfo.textRect.width
//                lineLayoutInfo.textRect.y = (height - labelHeight) / 2
//                lineLayoutInfo.textRect.height = labelHeight
//                x -= lineLayoutInfo.textRect.width
//                x -= layoutHGap
//            }
//            if (hasPopupIcon) {
//                if (hasText && hasIcon) {
//                    x -= 2 * layoutHGap
//                }
//                if (!hasText && !hasIcon) {
//                    // horizontally center the popup icon
//                    x -= (width - 2 * layoutHGap - 1 - labelHeight / 2) / 2
//                }
//                result.popupActionRect.width = 1 + labelHeight / 2
//                result.popupActionRect.x = x - result.popupActionRect.width
//                result.popupActionRect.y = (height - labelHeight) / 2 - 1
//                result.popupActionRect.height = labelHeight + 2
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
//                ACTION_AND_POPUP_MAIN_ACTION ->                     // 1. break before popup icon if button has text or icon
//                    // 2. no break (all popup) if button has no text and no icon
//                    if (hasText || hasIcon) {
//                        // shift popup action rectangle to the left to
//                        // accomodate the vertical separator
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
//                        result.isTextInActionArea = true
//                    } else {
//                        result.popupClickArea.x = 0
//                        result.popupClickArea.y = 0
//                        result.popupClickArea.width = width
//                        result.popupClickArea.height = height
//                        result.isTextInActionArea = false
//                    }
//                ACTION_AND_POPUP_MAIN_POPUP ->                     // 1. break after icon if button has icon
//                    // 2. no break (all popup) if button has no icon
//                    if (hasIcon) {
//                        // shift text rectangle and popup action rectangle to the
//                        // left to accomodate the vertical separator
//                        if (result.textLayoutInfoList != null) {
//                            for (textLayoutInfo in result.textLayoutInfoList) {
//                                textLayoutInfo.textRect.x -= verticalSeparatorWidth
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
//                        result.isTextInActionArea = false
//                    } else {
//                        result.popupClickArea.x = 0
//                        result.popupClickArea.y = 0
//                        result.popupClickArea.width = width
//                        result.popupClickArea.height = height
//                        result.isTextInActionArea = true
//                    }
//            }
//        }
//        return result

        return CommandButtonLayoutManager.CommandButtonLayoutInfo(
            fullSize = preferredSize,
            actionClickArea = actionClickArea,
            popupClickArea = popupClickArea,
            separatorArea = separatorArea,
            separatorOrientation = separatorOrientation,
            iconRect = iconRect,
            textLayoutInfoList = textLayoutInfoList,
            extraTextLayoutInfoList = null,
            popupActionRect = popupActionRect,
            isTextInActionArea = isTextInActionArea
        )
    }
}