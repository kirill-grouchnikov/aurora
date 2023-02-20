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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizePolicies
import org.pushingpixels.aurora.component.ribbon.resize.RibbonBandResizePolicy
import kotlin.math.max

sealed interface AbstractRibbonBand {
    val title: String
    val icon: Painter?
    val expandCommand: Command?
    val expandCommandKeyTip: String?
    val collapsedStateKeyTip: String?
    val resizePolicies: List<RibbonBandResizePolicy>
}

infix fun RibbonGalleryProjection.at(that: PresentationPriority):
        Pair<RibbonGalleryProjection, PresentationPriority> = Pair(this, that)

infix fun <C : ContentModel, P : PresentationModel> Projection<C, P>.with(that: RibbonComponentPresentationModel):
        Pair<Projection<C, P>, RibbonComponentPresentationModel> = Pair(this, that)

data class RibbonBandGroup(
    val title: String? = null,
    val commandProjections: List<Pair<BaseCommandButtonProjection<*, *>, PresentationPriority>> = emptyList(),
    val componentProjections: List<Pair<Projection<ContentModel, PresentationModel>, RibbonComponentPresentationModel>> = emptyList(),
    val galleryProjections: List<Pair<RibbonGalleryProjection, PresentationPriority>> = emptyList(),
)

data class RibbonBand(
    override val title: String,
    override val icon: Painter? = null,
    override val expandCommand: Command? = null,
    override val expandCommandKeyTip: String? = null,
    override val collapsedStateKeyTip: String? = null,
    override val resizePolicies: List<RibbonBandResizePolicy> =
        CoreRibbonResizePolicies.getCorePoliciesPermissive(),
    val groups: List<RibbonBandGroup> = emptyList()
) : AbstractRibbonBand

data class FlowRibbonBand(
    override val title: String,
    override val icon: Painter? = null,
    override val expandCommand: Command? = null,
    override val expandCommandKeyTip: String? = null,
    override val collapsedStateKeyTip: String? = null,
    override val resizePolicies: List<RibbonBandResizePolicy> =
        CoreRibbonResizePolicies.getCoreFlowPoliciesRestrictive(3),
    val flowComponentProjections: List<Pair<Projection<ContentModel, PresentationModel>, RibbonComponentPresentationModel>> = emptyList()
) : AbstractRibbonBand

object RibbonBandCommandButtonPresentationStates {
    val BigFixed: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Big Fixed") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                throw UnsupportedOperationException()
            }
        }

    private class CommandButtonLayoutManagerBigFixedLandscape(
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
                extraTexts = if (command.extraText != null) listOf(command.extraText!!) else emptyList(),
                isTextInActionArea = (hasAction or command.isActionToggle) &&
                        (presentationModel.textClick == TextClick.Action),
                separatorOrientation = CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Horizontal,
                showPopupIcon = commandButtonKind.hasPopup
            )
        }

        override fun getPreferredSize(
            command: BaseCommand,
            presentationModel: BaseCommandButtonPresentationModel,
            preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
        ): Size {
            val paddingValues = presentationModel.contentPadding
            val bx = presentationModel.horizontalGapScaleFactor *
                    (paddingValues.startPadding + paddingValues.endPadding).toPx()
            val buttonText = command.text
            val layoutHGap = (CommandButtonSizingConstants.DefaultHorizontalContentLayoutGap *
                    presentationModel.horizontalGapScaleFactor).toPx()
            val layoutVGap = (CommandButtonSizingConstants.DefaultVerticalContentLayoutGap *
                    presentationModel.verticalGapScaleFactor).toPx()
            val hasIcon = (command.icon != null) || presentationModel.forceAllocateSpaceForIcon
            val hasText = buttonText.isNotEmpty()
            val hasPopupIcon = (command.secondaryContentModel != null)

            val titleLine = Paragraph(
                text = preLayoutInfo.texts[0], style = textStyle,
                constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )

            val titleWidth = titleLine.maxIntrinsicWidth + (if (hasPopupIcon) 4 * layoutHGap +
                    CommandButtonSizingConstants.PopupIconWidth.toPx() else 0).toInt()
            val width = max(getPreferredIconSize(command, presentationModel).width.toPx(), titleWidth)

            // start height with the top inset
            var height = presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
            // icon?
            if (hasIcon) {
                // icon height
                height += getPreferredIconSize(command, presentationModel).height.toPx()
                // padding below the icon
                height += getIconTextGap(presentationModel).toPx()
            }
            // text?
            if (hasText) {
                // padding above the text
                height += layoutVGap
                // text height
                height += titleLine.height
            }

            // bottom insets
            height += presentationModel.verticalGapScaleFactor * paddingValues.bottomPadding.toPx()

            // Bump up the width if necessary to be at least 125% of the button height
            return Size(max(bx + width, height * 5 / 4), height)
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
                    paddingValues.startPadding.toPx()
            val endInset = presentationModel.horizontalGapScaleFactor *
                    paddingValues.endPadding.toPx()
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
            val textLayoutInfoList: MutableList<CommandButtonLayoutManager.TextLayoutInfo> = arrayListOf()
            val extraTextLayoutInfoList: List<CommandButtonLayoutManager.TextLayoutInfo> = emptyList()

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
                    paddingValues.topPadding.toPx() + shiftY - layoutVGap

            // icon
            if (hasIcon) {
                y += layoutVGap

                val iconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
                val iconHeight = getPreferredIconSize(command, presentationModel).height.toPx()
                iconRect = Rect(
                    left = (finalWidth - iconWidth) / 2,
                    right = (finalWidth - iconWidth) / 2 + iconWidth,
                    top = y,
                    bottom = y + iconHeight
                )

                y += (iconHeight + getIconTextGap(presentationModel).toPx())
            }

            y += layoutVGap

            val titleLine = Paragraph(
                text = preLayoutInfo.texts[0], style = textStyle,
                constraints = Constraints(maxWidth = Int.MAX_VALUE),
                density = _density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
            )

            val textLineWidth = titleLine.maxIntrinsicWidth
            val popupIconWidth = CommandButtonSizingConstants.PopupIconWidth.toPx()
            val popupIconHeight = CommandButtonSizingConstants.PopupIconHeight.toPx()
            val extraWidth = if (hasPopupIcon) 4 * layoutHGap + popupIconWidth else 0

            val titleLineLayoutInfo = CommandButtonLayoutManager.TextLayoutInfo(
                text = preLayoutInfo.texts[0],
                textRect = Rect(
                    left = (startInset + (finalWidth - textLineWidth - extraWidth.toFloat() - startInset - endInset) / 2),
                    right = (startInset + (finalWidth - textLineWidth - extraWidth.toFloat() - startInset - endInset) / 2) + textLineWidth,
                    top = y,
                    bottom = y + titleLine.height
                )
            )

            textLayoutInfoList.add(titleLineLayoutInfo)

            if (hasPopupIcon) {
                val popupActionX =
                    if (ltr)
                        titleLineLayoutInfo.textRect.right + 4 * layoutHGap
                    else
                        titleLineLayoutInfo.textRect.left - 4 * layoutHGap - popupIconWidth
                popupActionRect = Rect(
                    left = popupActionX,
                    right = popupActionX + popupIconWidth,
                    top = y + (titleLine.height - popupIconHeight) / 2,
                    bottom = y + (titleLine.height - popupIconHeight) / 2 + popupIconHeight
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

                else -> {}
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

    val BigFixedLandscape: CommandButtonPresentationState =
        object : CommandButtonPresentationState("Big Fixed Landscape") {
            override fun createLayoutManager(
                layoutDirection: LayoutDirection,
                density: Density,
                textStyle: TextStyle,
                fontFamilyResolver: FontFamily.Resolver
            ): CommandButtonLayoutManager {
                return CommandButtonLayoutManagerBigFixedLandscape(
                    layoutDirection,
                    density,
                    textStyle,
                    fontFamilyResolver
                )
            }
        }

}
