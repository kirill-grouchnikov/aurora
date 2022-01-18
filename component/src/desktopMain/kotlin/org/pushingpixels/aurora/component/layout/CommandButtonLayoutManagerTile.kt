/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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

internal open class CommandButtonLayoutManagerTile(
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

    override fun getPreferredSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): Size {
        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor *
                (paddingValues.calculateTopPadding() + paddingValues.calculateBottomPadding()).toPx()
        val buttonText = command.text
        val extraText = command.extraText
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasText = (buttonText != null) or (extraText != null)
        val hasPopupIcon = (command.secondaryContentModel != null)
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
            showIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon,
            texts = listOf(command.text),
            extraTexts = if (command.extraText != null) listOf(command.extraText) else emptyList(),
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
        val buttonExtraText = command.extraText
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
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
        if (constraints.hasFixedWidth && (constraints.maxWidth > 0)) {
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
        if (finalWidth < presentationModel.minWidth.toPx()) {
            shiftX += (presentationModel.minWidth.toPx() - finalWidth) / 2.0f
            finalWidth = presentationModel.minWidth.toPx()
        }
        if (constraints.hasFixedHeight && (constraints.maxHeight > 0)) {
            finalHeight = constraints.maxHeight.toFloat()
        }

        val paddingValues = presentationModel.contentPadding
        var actionClickArea = Rect.Zero
        var popupClickArea = Rect.Zero
        var separatorArea = Rect.Zero
        val verticalSeparatorWidth = SeparatorSizingConstants.Thickness.toPx()

        if (ltr) {
            var x = presentationModel.horizontalGapScaleFactor *
                    paddingValues.calculateStartPadding(layoutDirection)
                        .toPx() + shiftX - layoutHGap

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

            // Account for content overflowing the available horizontal space (constrained width
            // scenario).
            if (hasText || hasPopup) {
                val paddingEnd = presentationModel.horizontalGapScaleFactor *
                        paddingValues.calculateEndPadding(layoutDirection).toPx()
                if (hasPopup) {
                    if (popupActionRect.right > (finalWidth - paddingEnd)) {
                        shiftX = popupActionRect.right - (finalWidth - paddingEnd)
                        // Shift the popup action rectangle to the left
                        popupActionRect =
                            popupActionRect.translate(translateX = -shiftX, translateY = 0.0f)
                        if (hasText) {
                            // And shift the right coordinate of the text rectangle if needed
                            textLayoutInfoList[0].textRect = Rect(
                                left = textLayoutInfoList[0].textRect.left,
                                top = textLayoutInfoList[0].textRect.top,
                                right = textLayoutInfoList[0].textRect.right.coerceAtMost(
                                    popupActionRect.left - 2 * layoutHGap
                                ),
                                bottom = textLayoutInfoList[0].textRect.bottom
                            )
                        }
                        if (command.extraText != null) {
                            // And shift the right coordinate of the extra text rectangle if needed
                            extraTextLayoutInfoList[0].textRect = Rect(
                                left = extraTextLayoutInfoList[0].textRect.left,
                                top = extraTextLayoutInfoList[0].textRect.top,
                                right = extraTextLayoutInfoList[0].textRect.right.coerceAtMost(
                                    popupActionRect.left - 2 * layoutHGap
                                ),
                                bottom = extraTextLayoutInfoList[0].textRect.bottom
                            )
                        }
                    }
                } else {
                    // We have no popup, but guaranteed to have text in here
                    textLayoutInfoList[0].textRect = Rect(
                        left = textLayoutInfoList[0].textRect.left,
                        top = textLayoutInfoList[0].textRect.top,
                        right = textLayoutInfoList[0].textRect.right.coerceAtMost(
                            finalWidth - paddingEnd
                        ),
                        bottom = textLayoutInfoList[0].textRect.bottom
                    )
                    if (command.extraText != null) {
                        // And shift the right coordinate of the extra text rectangle if needed
                        extraTextLayoutInfoList[0].textRect = Rect(
                            left = extraTextLayoutInfoList[0].textRect.left,
                            top = extraTextLayoutInfoList[0].textRect.top,
                            right = extraTextLayoutInfoList[0].textRect.right.coerceAtMost(
                                finalWidth - paddingEnd
                            ),
                            bottom = extraTextLayoutInfoList[0].textRect.bottom
                        )
                    }
                }
            }

            var xBorderBetweenActionAndPopup = 0.0f
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
                CommandButtonKind.ActionAndPopupMainAction -> {
                    // 1. break before popup icon if button has text or icon
                    // 2. no break (all popup) if button has no text and no icon
                    if (hasText || hasIcon) {
                        // shift popup action rectangle to the right to
                        // accommodate the vertical separator
                        popupActionRect = popupActionRect.translate(
                            translateX = verticalSeparatorWidth,
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
                            right = xBorderBetweenActionAndPopup + verticalSeparatorWidth,
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
                CommandButtonKind.ActionAndPopupMainPopup -> {
                    // 1. break after icon if button has icon
                    // 2. no break (all popup) if button has no icon
                    if (hasIcon) {
                        // shift text rectangle and popup action rectangle to the
                        // right to accommodate the vertical separator
                        for (textLayoutInfo in textLayoutInfoList) {
                            textLayoutInfo.textRect = textLayoutInfo.textRect.translate(
                                translateX = verticalSeparatorWidth,
                                translateY = 0.0f
                            )
                        }
                        for (extraTextLayoutInfo in extraTextLayoutInfoList) {
                            extraTextLayoutInfo.textRect = extraTextLayoutInfo.textRect.translate(
                                translateX = verticalSeparatorWidth,
                                translateY = 0.0f
                            )
                        }
                        popupActionRect = popupActionRect.translate(
                            translateX = verticalSeparatorWidth,
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
                            right = xBorderBetweenActionAndPopup + verticalSeparatorWidth,
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
        } else {
            var x = finalWidth - presentationModel.horizontalGapScaleFactor *
                    paddingValues.calculateStartPadding(layoutDirection)
                        .toPx() - shiftX + layoutHGap

            // icon
            if (hasIcon) {
                x -= layoutHGap
                iconRect = Rect(
                    left = x - iconSize,
                    right = x,
                    top = (finalHeight - iconSize) / 2,
                    bottom = (finalHeight - iconSize) / 2 + iconSize
                )
                x -= iconSize + layoutHGap
            }

            // text
            var textHeight = 0.0f
            if (hasText) {
                x -= layoutHGap
                val hasExtraText = (buttonExtraText != null)

                val paragraph = Paragraph(
                    text = command.text, style = textStyle, width = Float.POSITIVE_INFINITY,
                    density = _density, maxLines = 1, resourceLoader = resourceLoader
                )

                textHeight = paragraph.height
                val lineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                    text = command.text,
                    textRect = Rect(
                        left = x - paragraph.maxIntrinsicWidth,
                        right = x,
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
                            left = x - extraParagraph.maxIntrinsicWidth,
                            right = x,
                            top = lineLayoutInfo.textRect.bottom,
                            bottom = lineLayoutInfo.textRect.bottom + textHeight
                        )
                    )

                    extraTextLayoutInfoList.add(extraLineLayoutInfo)

                    x -= max(
                        lineLayoutInfo.textRect.width,
                        extraLineLayoutInfo.textRect.width
                    )
                } else {
                    x -= lineLayoutInfo.textRect.width
                }

                x -= layoutHGap
            }
            if (hasPopup) {
                if (hasText && hasIcon) {
                    x -= 2 * layoutHGap
                }
                val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
                val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
                popupActionRect = Rect(
                    left = x - 4 - popupIconWidth,
                    right = x,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )
            }

            // Account for content overflowing the available horizontal space (constrained width
            // scenario).
            if (hasText || hasPopup) {
                val paddingEnd = presentationModel.horizontalGapScaleFactor *
                        paddingValues.calculateEndPadding(layoutDirection).toPx()
                if (hasPopup) {
                    if (popupActionRect.left < paddingEnd) {
                        shiftX = paddingEnd - popupActionRect.left
                        // Shift the popup action rectangle to the right
                        popupActionRect =
                            popupActionRect.translate(translateX = shiftX, translateY = 0.0f)
                        if (hasText) {
                            // And shift the left coordinate of the text rectangle if needed
                            textLayoutInfoList[0].textRect = Rect(
                                left = textLayoutInfoList[0].textRect.left.coerceAtLeast(
                                    popupActionRect.right + 2 * layoutHGap
                                ),
                                top = textLayoutInfoList[0].textRect.top,
                                right = textLayoutInfoList[0].textRect.right,
                                bottom = textLayoutInfoList[0].textRect.bottom
                            )
                            if (command.extraText != null) {
                                // And shift the right coordinate of the extra text rectangle if needed
                                extraTextLayoutInfoList[0].textRect = Rect(
                                    left = extraTextLayoutInfoList[0].textRect.left.coerceAtLeast(
                                        popupActionRect.right + 2 * layoutHGap
                                    ),
                                    top = extraTextLayoutInfoList[0].textRect.top,
                                    right = extraTextLayoutInfoList[0].textRect.right,
                                    bottom = extraTextLayoutInfoList[0].textRect.bottom
                                )
                            }
                        }
                    }
                } else {
                    // We have no popup, but guaranteed to have text in here
                    textLayoutInfoList[0].textRect = Rect(
                        left = textLayoutInfoList[0].textRect.left.coerceAtLeast(paddingEnd),
                        top = textLayoutInfoList[0].textRect.top,
                        right = textLayoutInfoList[0].textRect.right,
                        bottom = textLayoutInfoList[0].textRect.bottom
                    )
                    if (command.extraText != null) {
                        // And shift the right coordinate of the extra text rectangle if needed
                        extraTextLayoutInfoList[0].textRect = Rect(
                            left = extraTextLayoutInfoList[0].textRect.left.coerceAtLeast(paddingEnd),
                            top = extraTextLayoutInfoList[0].textRect.top,
                            right = extraTextLayoutInfoList[0].textRect.right,
                            bottom = extraTextLayoutInfoList[0].textRect.bottom
                        )
                    }
                }
            }

            var xBorderBetweenActionAndPopup = 0.0f
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
                CommandButtonKind.ActionAndPopupMainAction -> {
                    // 1. break before popup icon if button has text or icon
                    // 2. no break (all popup) if button has no text and no icon
                    if (hasText || hasIcon) {
                        // shift popup action rectangle to the left to
                        // accommodate the vertical separator
                        popupActionRect = popupActionRect.translate(
                            translateX = -verticalSeparatorWidth,
                            translateY = 0.0f
                        )
                        xBorderBetweenActionAndPopup = popupActionRect.right + 2.0f * layoutHGap

                        actionClickArea = Rect(
                            left = xBorderBetweenActionAndPopup,
                            right = finalWidth,
                            top = 0.0f,
                            bottom = finalHeight
                        )

                        popupClickArea = Rect(
                            left = 0.0f,
                            right = xBorderBetweenActionAndPopup,
                            top = 0.0f,
                            bottom = finalHeight
                        )

                        separatorArea = Rect(
                            left = xBorderBetweenActionAndPopup,
                            right = xBorderBetweenActionAndPopup + verticalSeparatorWidth,
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
                CommandButtonKind.ActionAndPopupMainPopup -> {
                    // 1. break after icon if button has icon
                    // 2. no break (all popup) if button has no icon
                    if (hasIcon) {
                        // shift text rectangle and popup action rectangle to the
                        // left to accommodate the vertical separator
                        for (textLayoutInfo in textLayoutInfoList) {
                            textLayoutInfo.textRect = textLayoutInfo.textRect.translate(
                                translateX = -verticalSeparatorWidth,
                                translateY = 0.0f
                            )
                        }
                        for (extraTextLayoutInfo in extraTextLayoutInfoList) {
                            extraTextLayoutInfo.textRect = extraTextLayoutInfo.textRect.translate(
                                translateX = -verticalSeparatorWidth,
                                translateY = 0.0f
                            )
                        }
                        popupActionRect = popupActionRect.translate(
                            translateX = -verticalSeparatorWidth,
                            translateY = 0.0f
                        )
                        xBorderBetweenActionAndPopup = iconRect.left - layoutHGap

                        actionClickArea = Rect(
                            left = xBorderBetweenActionAndPopup,
                            top = 0.0f,
                            right = finalWidth,
                            bottom = finalHeight
                        )

                        popupClickArea = Rect(
                            left = 0.0f,
                            top = 0.0f,
                            right = xBorderBetweenActionAndPopup,
                            bottom = finalHeight
                        )

                        separatorArea = Rect(
                            left = xBorderBetweenActionAndPopup,
                            right = xBorderBetweenActionAndPopup + verticalSeparatorWidth,
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

internal class CommandButtonLayoutManagerTileFitToIcon(
    layoutDirection: LayoutDirection,
    _density: Density,
    textStyle: TextStyle,
    resourceLoader: Font.ResourceLoader
) : CommandButtonLayoutManagerTile(layoutDirection, _density, textStyle, resourceLoader) {
    override fun getPreferredIconSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel
    ): Dp {
        return presentationModel.iconDimension ?: super.getPreferredIconSize(
            command,
            presentationModel
        )
    }
}
