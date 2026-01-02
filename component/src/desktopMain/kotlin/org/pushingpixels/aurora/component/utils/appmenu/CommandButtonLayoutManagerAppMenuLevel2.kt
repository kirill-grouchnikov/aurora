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
package org.pushingpixels.aurora.component.utils.appmenu

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.layout.getCommandButtonKind
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import kotlin.math.max

internal class CommandButtonLayoutManagerAppMenuLevel2(
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

    override fun getIconTextGap(presentationModel: BaseCommandButtonPresentationModel): Dp {
        // Bigger icon needs larger gap
        return super.getIconTextGap(presentationModel) * 2.0f
    }

    override fun getPreLayoutInfo(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
        val hasAction = (command.action != null)
        val commandButtonKind = getCommandButtonKind(command, presentationModel)

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            showIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon,
            texts = listOf(command.text),
            extraTexts = if (command.extraText != null) listOf(command.extraText!!) else emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.Action),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Vertical,
            showPopupIcon = commandButtonKind.hasPopup
        )
    }

    override fun getPreferredSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
        buttonShaper: AuroraButtonShaper
    ): Size {
        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
        val buttonText = command.text
        val extraText = command.extraText
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasText = buttonText.isNotEmpty() or (extraText != null)
        val hasPopupIcon = (command.secondaryContentModel != null)
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

            // text width
            var textWidth = 0.0f
            if (buttonText.isNotEmpty()) {
                val textParagraph = Paragraph(
                    text = buttonText, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
                )
                textWidth = textParagraph.maxIntrinsicWidth
                textHeight += textParagraph.height
            }
            if (extraText != null) {
                val extraTextParagraph = Paragraph(
                    text = extraText, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
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

        // end insets
        width += presentationModel.horizontalGapScaleFactor *
                paddingValues.endPadding.toPx()

        // and remove the padding before the first and after the last elements
        width -= 2 * layoutHGap
        return Size(width, by + max(prefIconHeight, textHeight))
    }

    internal fun getPreferredHeight(
        fixedWidth: Dp,
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): Float {
        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
        val buttonText = command.text
        val extraText = command.extraText
        val iconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val iconHeight = getPreferredIconSize(command, presentationModel).height.toPx()
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()

        // texts
        var textHeight = 0f
        var availableWidthForText = fixedWidth.value * _density.density -
                presentationModel.horizontalGapScaleFactor * paddingValues.horizontalPaddings.toPx()
        if (command.icon != null) {
            // Account for horizontal space the icon needs + gaps
            availableWidthForText -= (layoutHGap + iconWidth + getIconTextGap(presentationModel).toPx())
        }
        if (command.secondaryContentModel != null) {
            // Account for horizontal space the popup arrow icon needs + gaps
            availableWidthForText -= (CommandButtonSizingConstants.PopupIconWidth.toPx() + 4)
        }
        val textConstraints = Constraints(maxWidth = availableWidthForText.toInt())
        if (buttonText.isNotEmpty()) {
            val textParagraph = Paragraph(
                text = buttonText, style = textStyle, constraints = textConstraints,
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )
            textHeight += textParagraph.height
        }
        if (extraText != null) {
            val extraTextParagraph = Paragraph(
                text = extraText, style = textStyle, constraints = textConstraints,
                density = _density, maxLines = 2, fontFamilyResolver = fontFamilyResolver
            )
            textHeight += extraTextParagraph.height
        }
        return by + max(iconHeight, textHeight)
    }

    override fun getExtraTextMaxLines(): Int {
        return 2
    }

    override fun getLayoutInfo(
        constraints: Constraints,
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo,
        buttonShaper: AuroraButtonShaper
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo, buttonShaper)

        val buttonText = command.text
        val buttonExtraText = command.extraText
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasText = buttonText.isNotEmpty() or (buttonExtraText != null)
        val hasPopup = (command.secondaryContentModel != null)
        val iconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val iconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

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
                    HorizontalAlignment.Leading,
                    HorizontalAlignment.Fill -> {
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
            // This layout manager allows the button to grow vertically to accommodate two lines of
            // extra text
            finalHeight = getPreferredHeight(
                fixedWidth = (constraints.maxWidth / _density.density).dp,
                command = command,
                presentationModel = presentationModel)
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

        val iconTop = if (buttonExtraText != null) {
            presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
        } else {
            (finalHeight - iconHeight) / 2.0f
        }
        if (ltr) {
            var x = presentationModel.horizontalGapScaleFactor *
                    paddingValues.startPadding.toPx() + shiftX - layoutHGap

            // icon
            if (hasIcon) {
                x += layoutHGap
                iconRect = Rect(
                    left = x,
                    right = x + iconWidth,
                    top = iconTop,
                    bottom = iconTop + iconHeight
                )
                x += iconWidth + layoutHGap
            }

            // text
            val textHeight: Float
            if (hasText) {
                val textLeft = if (hasIcon) {
                    x - layoutHGap + getIconTextGap(presentationModel).toPx()
                } else {
                    x + layoutHGap
                }
                val textRight = if (hasPopup) {
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

                textHeight = paragraph.height
                val textTop = if (buttonExtraText != null) {
                    presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
                } else {
                    (finalHeight - paragraph.height) / 2.0f
                }
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

                if (command.extraText != null) {
                    val extraParagraph = Paragraph(
                        text = command.extraText!!,
                        style = textStyle,
                        constraints = Constraints(maxWidth = (textRight - textLeft).toInt()),
                        density = _density,
                        maxLines = 2,
                        fontFamilyResolver = fontFamilyResolver
                    )

                    val extraLineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                        text = command.extraText!!,
                        textRect = Rect(
                            left = textLeft,
                            right = textLeft + extraParagraph.width,
                            top = lineLayoutInfo.textRect.bottom,
                            bottom = lineLayoutInfo.textRect.bottom + extraParagraph.height
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
                val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
                val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
                x = finalWidth - presentationModel.horizontalGapScaleFactor *
                        paddingValues.endPadding.toPx() -
                        popupIconWidth - 4
                popupActionRect = Rect(
                    left = x,
                    right = x + 4 + popupIconWidth,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )
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

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainAction -> {
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

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainPopup -> {
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
                    paddingValues.startPadding.toPx() - shiftX + layoutHGap

            // icon
            if (hasIcon) {
                x -= layoutHGap
                iconRect = Rect(
                    left = x - iconWidth,
                    right = x,
                    top = iconTop,
                    bottom = iconTop + iconHeight
                )
                x -= (iconWidth + layoutHGap)
            }

            // text
            val textHeight: Float
            if (hasText) {
                val textRight = if (hasIcon) {
                    x + layoutHGap - getIconTextGap(presentationModel).toPx()
                } else {
                    x - layoutHGap
                }
                val textLeft = if (hasPopup) {
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
                    constraints = Constraints(maxWidth = (textRight - textLeft).toInt()),
                    density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
                )

                textHeight = paragraph.height
                val textTop = if (buttonExtraText != null) {
                    presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
                } else {
                    (finalHeight - paragraph.height) / 2.0f
                }
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

                if (command.extraText != null) {
                    val extraParagraph = Paragraph(
                        text = command.extraText!!,
                        style = textStyle,
                        constraints = Constraints(maxWidth = (textRight - textLeft).toInt()),
                        density = _density,
                        maxLines = 2,
                        fontFamilyResolver = fontFamilyResolver
                    )

                    val extraLineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                        text = command.extraText!!,
                        textRect = Rect(
                            left = x - extraParagraph.width,
                            right = x,
                            top = lineLayoutInfo.textRect.bottom,
                            bottom = lineLayoutInfo.textRect.bottom + extraParagraph.height
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
                popupActionRect = Rect(
                    left = x - 4 - popupIconWidth,
                    right = x,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )
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

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainAction -> {
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

                CommandButtonLayoutManager.CommandButtonKind.ActionAndPopupMainPopup -> {
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

    override fun getActionKeyTipAnchorCenterPoint(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
    ): Offset {
        return if (layoutDirection == LayoutDirection.Ltr) {
            // bottom-right corner of the icon area
            Offset(
                x = layoutInfo.iconRect.left + layoutInfo.iconRect.width,
                y = layoutInfo.actionClickArea.top + layoutInfo.actionClickArea.height
            )
        } else {
            // bottom-left corner of the icon area
            Offset(
                x = layoutInfo.iconRect.left,
                y = layoutInfo.actionClickArea.top + layoutInfo.actionClickArea.height
            )
        }
    }

    override fun getPopupKeyTipAnchorCenterPoint(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
    ): Offset {
        return if (layoutDirection == LayoutDirection.Ltr) {
            // bottom-left corner of the popup click area
            Offset(
                x = layoutInfo.popupClickArea.left,
                y = layoutInfo.popupClickArea.top + layoutInfo.popupClickArea.height
            )
        } else {
            // bottom-left corner of the popup click area
            Offset(
                x = layoutInfo.popupClickArea.left + layoutInfo.popupClickArea.width,
                y = layoutInfo.popupClickArea.top + layoutInfo.popupClickArea.height
            )
        }
    }
}
