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
package org.pushingpixels.aurora.component.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import kotlin.math.max

@AuroraInternalApi
open class CommandButtonLayoutManagerMedium(
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
        return DpSize(16.dp, 16.dp)
    }

    internal open fun hasIcon(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): Boolean {
        return (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
    }

    private fun getPreferredSizeIgnoringMinWidth(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        buttonShaper: AuroraButtonShaper,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): Size {
        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
        val buttonText = command.text
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = hasIcon(command, presentationModel)
        val hasText = buttonText.isNotEmpty()
        val hasPopup = (command.secondaryContentModel != null)
        val prefIconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val prefIconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

        // start with the start insets
        var width = presentationModel.horizontalGapScaleFactor * paddingValues.startPadding.toPx()
        // icon?
        if (hasIcon) {
            // padding before the icon
            width += layoutHGap
            // icon width
            width += prefIconWidth
            // padding after the icon
            width += layoutHGap
        }
        // text?
        var textHeight = 0f
        if (hasText) {
            // space before the text
            if (hasIcon) {
                width = width - layoutHGap + getIconTextGap(presentationModel).toPx()
            } else {
                width += layoutHGap
            }

            val paragraph = Paragraph(
                text = buttonText, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )
            width += paragraph.maxIntrinsicWidth
            textHeight = paragraph.height

            // padding after the text
            width += layoutHGap
        }
        // popup icon?
        if (hasPopup && presentationModel.showPopupIcon) {
            // padding before the popup icon
            if (hasText || hasIcon) {
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

        // end insets
        width += presentationModel.horizontalGapScaleFactor *
                paddingValues.endPadding.toPx()

        // and remove the padding before the first and after the last elements
        width -= 2 * layoutHGap
        val preferred = Size(width, by + max(prefIconHeight, textHeight))
        return if (hasText) {
            val extraContentPadding = buttonShaper.getExtraContentPadding(
                uiPreferredSize = preferred,
                layoutDirection = layoutDirection,
                density = _density
            )
            val extraStartPx = with (_density) {
                extraContentPadding.startPadding.toPx()
            }
            val extraEndPx = with (_density) {
                extraContentPadding.startPadding.toPx()
            }
            Size(preferred.width + extraStartPx + extraEndPx, preferred.height)
        } else {
            preferred
        }
    }

    override fun getPreferredSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
        buttonShaper: AuroraButtonShaper
    ): Size {
        val preferredSizeIgnoringMinWidth = getPreferredSizeIgnoringMinWidth(
            command, presentationModel, buttonShaper, preLayoutInfo)
        return Size(
            width = max(
                preferredSizeIgnoringMinWidth.width,
                presentationModel.minWidth.toPx()
            ),
            height = preferredSizeIgnoringMinWidth.height
        )
    }

    override fun getPreLayoutInfo(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
        val hasAction = (command.action != null)
        val commandButtonKind = getCommandButtonKind(command, presentationModel)

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            showIcon = hasIcon(command, presentationModel),
            texts = if (command.text.isEmpty()) emptyList() else listOf(command.text),
            extraTexts = emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.Action),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Vertical,
            showPopupIcon = commandButtonKind.hasPopup && presentationModel.showPopupIcon
        )
    }

    override fun getLayoutInfo(
        constraints: Constraints,
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
        buttonShaper: AuroraButtonShaper
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSizeIgnoringMinWidth = getPreferredSizeIgnoringMinWidth(
            command, presentationModel, buttonShaper, preLayoutInfo
        )
        val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo, buttonShaper)
        val originalPaddingValues = presentationModel.contentPadding
        val extraPaddingValues = buttonShaper.getExtraContentPadding(
            uiPreferredSize = preferredSizeIgnoringMinWidth,
            layoutDirection = layoutDirection,
            density = _density
        )
        val paddingValues = PaddingValues(
            start = originalPaddingValues.startPadding + extraPaddingValues.startPadding,
            end = originalPaddingValues.endPadding + extraPaddingValues.endPadding,
            top = originalPaddingValues.topPadding,
            bottom = originalPaddingValues.bottomPadding
        )

        val paddingTop = presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
        val paddingBottom = presentationModel.verticalGapScaleFactor * paddingValues.bottomPadding.toPx()

        val buttonText = command.text
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = hasIcon(command, presentationModel)
        val hasText = buttonText.isNotEmpty()
        val hasPopup = (command.secondaryContentModel != null)
        val iconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val iconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var iconRect = Rect.Zero
        var popupActionRect = Rect.Zero
        val textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> =
            arrayListOf()

        var shiftX = 0.0f
        var finalWidth = preferredSizeIgnoringMinWidth.width
        var finalHeight = preferredSizeIgnoringMinWidth.height

        // First step - do we have constrained maximum width?
        if (constraints.hasFixedWidth && (constraints.maxWidth > 0)) {
            finalWidth = constraints.maxWidth.toFloat()
            if (finalWidth > preferredSize.width) {
                // We have more horizontal space than needed to display the content.
                // Consult the horizontal alignment attribute of the command button to see
                // how we should shift the content horizontally.
                when (presentationModel.horizontalAlignment) {
                    HorizontalAlignment.Leading,
                    HorizontalAlignment.Fill -> {
                    }

                    HorizontalAlignment.Center ->
                        // shift everything to be centered horizontally
                        shiftX = (finalWidth - preferredSize.width) / 2

                    HorizontalAlignment.Trailing -> {
                        // shift everything to the trailing end
                        shiftX = finalWidth - preferredSize.width
                    }
                }
            }
        }
        // Second step - do we have minimum width from the presentation model?
        if (finalWidth < presentationModel.minWidth.toPx()) {
            shiftX += (presentationModel.minWidth.toPx() - finalWidth) / 2.0f
            finalWidth = presentationModel.minWidth.toPx()
        }
        if (constraints.hasFixedHeight && (constraints.maxHeight > 0)) {
            finalHeight = constraints.maxHeight.toFloat()
        }

        var actionClickArea = Rect.Zero
        var popupClickArea = Rect.Zero
        var separatorArea = Rect.Zero
        val verticalSeparatorWidth = SeparatorSizingConstants.Thickness.toPx()

        if (ltr) {
            var x = presentationModel.horizontalGapScaleFactor *
                    paddingValues.startPadding.toPx() + shiftX - layoutHGap

            // icon
            if (hasIcon) {
                x += layoutHGap
                val iconTop = paddingTop + (finalHeight - iconHeight - paddingTop - paddingBottom) / 2
                iconRect = Rect(
                    left = x,
                    right = x + iconWidth,
                    top = iconTop,
                    bottom = iconTop + iconHeight
                )
                x += iconWidth + layoutHGap
            }

            // text
            if (hasText) {
                val textLeft = if (hasIcon) {
                    x - layoutHGap + getIconTextGap(presentationModel).toPx()
                } else {
                    x + layoutHGap
                }
                val textRight = if (hasPopup && presentationModel.showPopupIcon) {
                    finalWidth - presentationModel.horizontalGapScaleFactor *
                            paddingValues.endPadding.toPx() -
                            CommandButtonSizingConstants.PopupIconWidth.toPx() - 4
                } else {
                    finalWidth - presentationModel.horizontalGapScaleFactor *
                            paddingValues.endPadding.toPx()
                }

                val paragraph = Paragraph(
                    text = command.text, style = textStyle,
                    constraints = Constraints(maxWidth = (textRight - textLeft).toInt()),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
                )

                val textHeight = paragraph.height
                val textTop =
                    paddingTop + (finalHeight - textHeight - paddingTop - paddingBottom) / 2.0f
                val lineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                    text = command.text,
                    textRect = Rect(
                        left = textLeft,
                        right = textLeft + paragraph.width,
                        top = textTop,
                        bottom = textTop + textHeight
                    )
                )
                textLayoutInfoList.add(lineLayoutInfo)

                x += lineLayoutInfo.textRect.width
                x += layoutHGap
            }
            if (hasPopup && presentationModel.showPopupIcon) {
                val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
                val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
                if (hasText || hasIcon) {
                    if (presentationModel.horizontalAlignment == HorizontalAlignment.Fill) {
                        // Under Fill alignment, popup icon goes all the way to the right edge
                        x = finalWidth - presentationModel.horizontalGapScaleFactor *
                                paddingValues.endPadding.toPx() -
                                popupIconWidth - 4
                    } else {
                        // Otherwise, the popup icon is to the right of the texts
                        x += 2 * layoutHGap
                    }
                }
                if (!hasText && !hasIcon) {
                    // horizontally center the popup icon
                    x = (finalWidth - 2 * layoutHGap - popupIconWidth) / 2.0f
                }
                popupActionRect = Rect(
                    left = x,
                    right = x + 4 + popupIconWidth,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )
            }

            // Account for content overflowing the available horizontal space (constrained width
            // scenario).
            if (hasText || (hasPopup && presentationModel.showPopupIcon)) {
                val paddingEnd = presentationModel.horizontalGapScaleFactor *
                        paddingValues.endPadding.toPx()
                if (hasPopup && presentationModel.showPopupIcon) {
                    if (popupActionRect.right > (finalWidth - paddingEnd)) {
                        shiftX = popupActionRect.right - (finalWidth - paddingEnd)
                        // Shift the popup action rectangle to the left
                        popupActionRect =
                            popupActionRect.translate(translateX = -shiftX, translateY = 0.0f)
                        if (hasText) {
                            // And shift the right coordinate of the text rectangle
                            textLayoutInfoList[0].textRect = Rect(
                                left = textLayoutInfoList[0].textRect.left,
                                top = textLayoutInfoList[0].textRect.top,
                                right = textLayoutInfoList[0].textRect.right - shiftX,
                                bottom = textLayoutInfoList[0].textRect.bottom
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
                }
            }

            val xBorderBetweenActionAndPopup: Float
            when (preLayoutInfo.commandButtonKind) {
                CommandButtonLayoutManager.CommandButtonKind.ActionOnly -> {
                    actionClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )
                }

                CommandButtonLayoutManager.CommandButtonKind.PopupOnly -> {
                    popupClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )
                }

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainAction ->
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

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainPopup ->
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
        } else {
            var x = finalWidth - presentationModel.horizontalGapScaleFactor *
                    paddingValues.startPadding.toPx() - shiftX + layoutHGap

            // icon
            if (hasIcon) {
                x -= layoutHGap
                val iconTop = paddingTop + (finalHeight - iconHeight - paddingTop - paddingBottom) / 2
                iconRect = Rect(
                    left = x - iconWidth,
                    right = x,
                    top = iconTop,
                    bottom = iconTop + iconHeight
                )
                x -= (iconWidth + layoutHGap)
            }

            // text
            if (hasText) {
                val textRight = if (hasIcon) {
                    x + layoutHGap - getIconTextGap(presentationModel).toPx()
                } else {
                    x - layoutHGap
                }
                val textLeft = if (hasPopup && presentationModel.showPopupIcon) {
                    presentationModel.horizontalGapScaleFactor *
                            paddingValues.endPadding.toPx() +
                            CommandButtonSizingConstants.PopupIconWidth.toPx() + 4
                } else {
                    presentationModel.horizontalGapScaleFactor *
                            paddingValues.endPadding.toPx()
                }

                if (hasIcon) {
                    x = x + layoutHGap - getIconTextGap(presentationModel).toPx()
                } else {
                    x -= layoutHGap
                }

                val paragraph = Paragraph(
                    text = command.text, style = textStyle,
                    Constraints(maxWidth = (textRight - textLeft).toInt()),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
                )
                val textHeight = paragraph.height
                val textTop =
                    paddingTop + (finalHeight - textHeight - paddingTop - paddingBottom) / 2.0f
                val lineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                    text = command.text,
                    textRect = Rect(
                        left = x - paragraph.width,
                        right = x,
                        top = textTop,
                        bottom = textTop + textHeight
                    )
                )
                textLayoutInfoList.add(lineLayoutInfo)

                x -= lineLayoutInfo.textRect.width
                x -= layoutHGap
            }
            if (hasPopup && presentationModel.showPopupIcon) {
                val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
                val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
                if (hasText || hasIcon) {
                    if (presentationModel.horizontalAlignment == HorizontalAlignment.Fill) {
                        // Under Fill alignment, popup icon goes all the way to the left edge
                        x = presentationModel.horizontalGapScaleFactor *
                                paddingValues.endPadding.toPx() + 4
                    } else {
                        // Otherwise, the popup icon is to the left of the texts
                        x -= 2 * layoutHGap
                    }
                }
                if (!hasText && !hasIcon) {
                    // horizontally center the popup icon
                    x -= (finalWidth - 2 * layoutHGap - 1 - popupIconWidth) / 2.0f
                }
                popupActionRect = Rect(
                    left = x - 4 - popupIconWidth,
                    right = x,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )
            }

            // Account for content overflowing the available horizontal space (constrained width
            // scenario).
            if (hasText || (hasPopup && presentationModel.showPopupIcon)) {
                val paddingEnd = presentationModel.horizontalGapScaleFactor *
                        paddingValues.endPadding.toPx()
                if (hasPopup && presentationModel.showPopupIcon) {
                    if (popupActionRect.left < paddingEnd) {
                        shiftX = paddingEnd - popupActionRect.left
                        // Shift the popup action rectangle to the right
                        popupActionRect =
                            popupActionRect.translate(translateX = shiftX, translateY = 0.0f)
                        if (hasText) {
                            // And shift the left coordinate of the text rectangle
                            textLayoutInfoList[0].textRect = Rect(
                                left = textLayoutInfoList[0].textRect.left + shiftX,
                                top = textLayoutInfoList[0].textRect.top,
                                right = textLayoutInfoList[0].textRect.right,
                                bottom = textLayoutInfoList[0].textRect.bottom
                            )
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
                }
            }

            val xBorderBetweenActionAndPopup: Float
            when (preLayoutInfo.commandButtonKind) {
                CommandButtonLayoutManager.CommandButtonKind.ActionOnly -> {
                    actionClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )
                }

                CommandButtonLayoutManager.CommandButtonKind.PopupOnly -> {
                    popupClickArea = Rect(
                        left = 0.0f,
                        top = 0.0f,
                        right = finalWidth,
                        bottom = finalHeight
                    )
                }

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainAction ->
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

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainPopup ->
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
                        popupActionRect = popupActionRect.translate(
                            translateX = -verticalSeparatorWidth,
                            translateY = 0.0f
                        )

                        xBorderBetweenActionAndPopup = (iconRect.left - layoutHGap)
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

    override fun getActionKeyTipAnchorCenterPoint(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
    ): Offset {
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon

        // If the button shows icon, the action keytip is at the end edge of the icon. Otherwise,
        // it is at the end edge of the full action click area.
        if (layoutDirection == LayoutDirection.Ltr) {
            val x = if (hasIcon) {
                layoutInfo.iconRect.left + layoutInfo.iconRect.width
            } else {
                layoutInfo.actionClickArea.left + layoutInfo.actionClickArea.width
            }
            return Offset(
                x = x,
                y = (layoutInfo.fullSize.height + layoutInfo.actionClickArea.height) / 2.0f
            )
        } else {
            val x = if (hasIcon) {
                layoutInfo.iconRect.left
            } else {
                layoutInfo.actionClickArea.left
            }
            return Offset(
                x = x,
                y = (layoutInfo.fullSize.height + layoutInfo.actionClickArea.height) / 2.0f
            )
        }
    }

    override fun getPopupKeyTipAnchorCenterPoint(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
    ): Offset {
        return if (layoutDirection == LayoutDirection.Ltr) {
            Offset(
                x = layoutInfo.popupClickArea.left + layoutInfo.popupClickArea.width,
                y = (layoutInfo.fullSize.height + layoutInfo.popupClickArea.height) / 2.0f
            )
        } else {
            Offset(
                x = layoutInfo.popupClickArea.left,
                y = (layoutInfo.fullSize.height + layoutInfo.popupClickArea.height) / 2.0f
            )
        }
    }
}

@AuroraInternalApi
internal class CommandButtonLayoutManagerMediumFitToIcon(
    layoutDirection: LayoutDirection,
    _density: Density,
    textStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver
) : CommandButtonLayoutManagerMedium(layoutDirection, _density, textStyle, fontFamilyResolver) {
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
