/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.model.*
import kotlin.math.max

internal open class CommandButtonLayoutManagerMedium(
    override val layoutDirection: LayoutDirection,
    private val _density: Density,
    private val textStyle: TextStyle,
    private val resourceLoader: Font.ResourceLoader
) : CommandButtonLayoutManager {
    override val density = _density.density
    override val fontScale = _density.fontScale

    protected open val iconTextGapFactor: Float
        get() = 1.0f

    override fun getPreferredIconSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel
    ): Dp {
        return presentationModel.iconDimension ?: 16.dp
    }

    override fun getPreferredSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): Size {
        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor *
                (paddingValues.calculateTopPadding() + paddingValues.calculateBottomPadding()).toPx()
        val buttonText = command.text
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null)
        val hasPopup = (command.secondaryContentModel != null)
        val prefIconSize = getPreferredIconSize(command, presentationModel).toPx()

        // start with the left insets
        var width = presentationModel.horizontalGapScaleFactor *
                paddingValues.calculateStartPadding(layoutDirection).toPx()
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
                text = buttonText, style = textStyle, width = Float.POSITIVE_INFINITY,
                density = _density, maxLines = 1, resourceLoader = resourceLoader
            )
            width += paragraph.maxIntrinsicWidth
            textHeight = paragraph.height

            // padding after the text
            width += layoutHGap
        }
        // popup icon?
        if (hasPopup) {
            // padding before the popup icon
            if (hasText && hasIcon) {
                width += 2 * layoutHGap
            }
            // popup icon width
            width += 1 + CommandButtonSizingConstants.PopupIconWidth.toPx()
            // padding after the popup icon
            width += 2 * layoutHGap
        }

        if (preLayoutInfo.commandButtonKind.hasAction and preLayoutInfo.commandButtonKind.hasPopup) {
            // space for a vertical separator
            width += SeparatorSizingConstants.Thickness.toPx()
        }

        // right insets
        width += presentationModel.horizontalGapScaleFactor *
                paddingValues.calculateEndPadding(layoutDirection).toPx()

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
            if (presentationModel.textClick == TextClick.Action)
                CommandButtonKind.ActionAndPopupMainAction else
                CommandButtonKind.ActionAndPopupMainPopup
        } else if (hasPopup) {
            CommandButtonKind.PopupOnly
        } else {
            CommandButtonKind.ActionOnly
        }

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            texts = listOf(command.text),
            extraTexts = emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.Action),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Vertical,
            showPopupIcon = commandButtonKind.hasPopup
        )
    }

    override fun getLayoutInfo(
        constraints: Constraints,
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo)

        val buttonText = command.text
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.iconFactory != null)
        val hasText = (buttonText != null)
        val hasPopup = (command.secondaryContentModel != null)
        val iconSize = getPreferredIconSize(command, presentationModel).toPx()

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var iconRect = Rect.Zero
        var popupActionRect = Rect.Zero
        val textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
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
                    HorizontalAlignment.Leading -> if (!ltr) {
                        // shift everything to the right
                        shiftX = finalWidth - preferredSize.width
                    }
                    HorizontalAlignment.Center ->
                        // shift everything to be centered horizontally
                        shiftX = (finalWidth - preferredSize.width) / 2
                    HorizontalAlignment.Trailing -> if (ltr) {
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
        val paddingValues = presentationModel.contentPadding
        var x = presentationModel.horizontalGapScaleFactor *
                paddingValues.calculateStartPadding(layoutDirection).toPx() + shiftX - layoutHGap

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
                    top = (finalHeight - textHeight) / 2.0f,
                    bottom = (finalHeight - textHeight) / 2.0f + textHeight
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
            val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
            val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
            if (!hasText && !hasIcon) {
                // horizontally center the popup icon
                x += (finalWidth - 2 * layoutHGap - 1 - popupIconWidth) / 2.0f
            }
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
            CommandButtonKind.ActionAndPopupMainAction ->
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
            CommandButtonKind.ActionAndPopupMainPopup ->
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
//                        // left to accommodate the vertical separator
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
            fullSize = Size(finalWidth, finalHeight),
            actionClickArea = actionClickArea,
            popupClickArea = popupClickArea,
            separatorArea = separatorArea,
            iconRect = iconRect,
            textLayoutInfoList = textLayoutInfoList,
            extraTextLayoutInfoList = emptyList(),
            popupActionRect = popupActionRect
        )
    }
}
