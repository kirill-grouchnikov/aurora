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

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.model.*

internal open class CommandButtonLayoutManagerSmall(
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

    override fun getPreferredSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): Size {
        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasPopup = (command.secondaryContentModel != null)
        val prefIconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val prefIconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

        // start with the left insets
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
        // popup icon?
        if (hasPopup) {
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
        width += presentationModel.horizontalGapScaleFactor * paddingValues.endPadding.toPx()

        // and remove the padding before the first and after the last elements
        width -= 2 * layoutHGap
        return Size(width, by + prefIconHeight)
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

        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = commandButtonKind,
            showIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon,
            texts = emptyList(),
            extraTexts = emptyList(),
            isTextInActionArea = (hasAction or command.isActionToggle) &&
                    (presentationModel.textClick == TextClick.Action),
            separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Vertical,
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
        val paddingTop = presentationModel.verticalGapScaleFactor *
                presentationModel.contentPadding.topPadding.toPx()
        val paddingBottom = presentationModel.verticalGapScaleFactor *
                presentationModel.contentPadding.bottomPadding.toPx()

        val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                presentationModel.horizontalGapScaleFactor).toPx()
        val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
        val hasPopup = (command.secondaryContentModel != null)
        val iconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val iconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var iconRect = Rect.Zero
        var popupActionRect = Rect.Zero

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
                    HorizontalAlignment.Leading ->
                        shiftX = 0.0f
                    HorizontalAlignment.Center,
                    HorizontalAlignment.Fill ->
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
            if (hasPopup) {
                x += 2 * layoutHGap
                val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
                val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()

                popupActionRect = Rect(
                    left = x,
                    right = x + popupIconWidth,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )

                x += 2 * layoutHGap
            }
            val xBorderBetweenActionAndPopup : Float
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
                CommandButtonKind.ActionAndPopupMainPopup ->
                    // no break (all popup) if button has no text and no icon
                    if (hasIcon) {
                        // shift popup action rectangle to the right
                        // to accommodate the vertical separator
                        popupActionRect = popupActionRect.translate(
                            translateX = verticalSeparatorWidth,
                            translateY = 0.0f
                        )
                        xBorderBetweenActionAndPopup = iconRect.right + layoutHGap

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
                    paddingValues.startPadding.toPx() - shiftX

            // icon
            if (hasIcon) {
                val iconTop = paddingTop + (finalHeight - iconHeight - paddingTop - paddingBottom) / 2
                iconRect = Rect(
                    left = x - iconWidth,
                    right = x,
                    top = iconTop,
                    bottom = iconTop + iconHeight
                )
                x -= iconWidth + layoutHGap
            }
            if (hasPopup) {
                x -= 2 * layoutHGap
                val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
                val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()

                popupActionRect = Rect(
                    left = x - popupIconWidth,
                    right = x,
                    top = (finalHeight - popupIconHeight) / 2.0f - 1.0f,
                    bottom = (finalHeight - popupIconHeight) / 2.0f + popupIconHeight + 1.0f
                )

                x -= 2 * layoutHGap
            }
            val xBorderBetweenActionAndPopup : Float
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
                CommandButtonKind.ActionAndPopupMainPopup ->
                    // no break (all popup) if button has no text and no icon
                    if (hasIcon) {
                        // shift popup action rectangle to the left
                        // to accommodate the vertical separator
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

        return CommandButtonLayoutManager.CommandButtonLayoutInfo(
            fullSize = Size(finalWidth, finalHeight),
            actionClickArea = actionClickArea,
            popupClickArea = popupClickArea,
            separatorArea = separatorArea,
            iconRect = iconRect,
            textLayoutInfoList = emptyList(),
            extraTextLayoutInfoList = emptyList(),
            popupActionRect = popupActionRect
        )
    }
}

internal class CommandButtonLayoutManagerSmallFitToIcon(
    layoutDirection: LayoutDirection,
    _density: Density,
    textStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver
) : CommandButtonLayoutManagerSmall(layoutDirection, _density, textStyle, fontFamilyResolver) {
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
