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
package org.pushingpixels.aurora.component.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.IconProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import java.awt.*
import java.awt.geom.Rectangle2D
import javax.swing.border.Border
import kotlin.math.ceil

internal data class RichTooltipLayoutInfo(
    val fullSize: Size,
    val mainIconSize: Size,
    val titleSize: Size,
    val descriptionSizes: List<Size>,
    val footerSeparatorSize: Size,
    val footerIconSize: Size,
    val footerSizes: List<Size>
)

internal val RichTooltip.hasDescriptionContent: Boolean
    get() = (this.descriptionSections?.isNotEmpty() ?: false)
internal val RichTooltip.hasFooterSections: Boolean
    get() = (this.footerSections?.isNotEmpty() ?: false)
internal val RichTooltip.hasFooterContent: Boolean
    get() = ((this.footerIcon != null) || this.hasFooterSections)

internal fun displayRichTooltipContent(
    currentWindow: ComposeWindow,
    layoutDirection: LayoutDirection,
    density: Density,
    textStyle: TextStyle,
    resourceLoader: Font.ResourceLoader,
    skinColors: AuroraSkinColors,
    skinPainters: AuroraPainters,
    decorationAreaType: DecorationAreaType,
    compositionLocalContext: CompositionLocalContext,
    anchorBoundsInWindow: Rect,
    richTooltip: RichTooltip,
    presentationModel: RichTooltipPresentationModel,
    popupPlacementStrategy: PopupPlacementStrategy,
) {
    val popupContentWindow = ComposeWindow()
    popupContentWindow.focusableWindowState = false
    popupContentWindow.type = Window.Type.POPUP
    popupContentWindow.isAlwaysOnTop = true
    popupContentWindow.isUndecorated = true
    popupContentWindow.isResizable = false

    val locationOnScreen = currentWindow.locationOnScreen

    val offset = ceil(density.density).toInt()

    // Create our own text style with bold weight for the title
    val boldTextStyle = resolveDefaults(
        TextStyle(
            fontSize = textStyle.fontSize,
            fontWeight = FontWeight.Bold
        ), layoutDirection
    )

    val mainIconSize = if (richTooltip.mainIcon == null) 0.0f else
        presentationModel.mainIconSize.value * density.density

    var fullContentWidth = mainIconSize

    if (richTooltip.mainIcon != null) {
        fullContentWidth = presentationModel.mainIconSize.value * density.density
    }

    val horizontalPaddingPx =
        (RichTooltipSizingConstants.ContentPadding.calculateLeftPadding(layoutDirection) +
                RichTooltipSizingConstants.ContentPadding.calculateRightPadding(layoutDirection)).value *
                density.density
    val verticalPaddingPx =
        (RichTooltipSizingConstants.ContentPadding.calculateTopPadding() +
                RichTooltipSizingConstants.ContentPadding.calculateBottomPadding()).value *
                density.density
    val verticalGapPx =
        (RichTooltipSizingConstants.VerticalContentLayoutGap.value * density.density).toInt()

    var mainTextHeightPx = 0.0f
    val maxContentWidthPx = RichTooltipSizingConstants.MaxWidth.value * density.density -
            horizontalPaddingPx
    var maxTitleWidthPx = maxContentWidthPx
    if (richTooltip.mainIcon != null) {
        // If we're showing the icon, that eats into the horizontal space available to the title
        maxTitleWidthPx -= (presentationModel.mainIconSize +
                RichTooltipSizingConstants.HorizontalContentLayoutGap).value * density.density
    }
    // Create the title paragraph with the available horizontal space and bold style.
    // Note that we're not limiting the title to be single line
    val titleParagraph = Paragraph(
        text = richTooltip.title, style = boldTextStyle, width = maxTitleWidthPx,
        density = density, resourceLoader = resourceLoader
    )
    val titleWidth: Float
    if (titleParagraph.lineCount > 1) {
        // If the title goes multi-line, the content spans the entire available space
        titleWidth = maxTitleWidthPx
        fullContentWidth = maxContentWidthPx
    } else {
        titleWidth = titleParagraph.maxIntrinsicWidth
        if (richTooltip.mainIcon == null) {
            fullContentWidth = titleWidth
        } else {
            // For single line title, add the title width to icon width and gap (if main icon
            // is present)
            fullContentWidth += (RichTooltipSizingConstants.HorizontalContentLayoutGap.value * density.density
                    + titleWidth)
        }
    }
    mainTextHeightPx = titleParagraph.height

    // Description section(s)
    val descriptionSizes = ArrayList<Size>()
    if (richTooltip.hasDescriptionContent) {
        // If we have at least one description section, the entire tooltip goes full max width
        fullContentWidth = maxContentWidthPx

        // Account for vertical gap between title and description
        mainTextHeightPx += verticalGapPx
        // and for vertical gaps between description sections
        mainTextHeightPx += verticalGapPx * (richTooltip.descriptionSections!!.count() - 1)

        val maxDescriptionWidthPx = maxTitleWidthPx
        for (descriptionSection in richTooltip.descriptionSections!!) {
            // Create the description paragraph with the available horizontal space.
            // Note that we're not limiting the description to be single line
            val descriptionParagraph = Paragraph(
                text = descriptionSection, style = textStyle, width = maxDescriptionWidthPx,
                density = density, resourceLoader = resourceLoader
            )

            mainTextHeightPx += descriptionParagraph.height

            descriptionSizes.add(
                Size(
                    width = maxDescriptionWidthPx,
                    height = descriptionParagraph.height
                )
            )
        }
    }

    // Content height so far is the max of icon height and title / descriptions height
    var fullContentHeight = kotlin.math.max(mainIconSize, mainTextHeightPx)

    if (richTooltip.hasFooterContent) {
        // Gap above the footer separator
        fullContentHeight += verticalGapPx
        // Separator itself
        fullContentHeight += SeparatorSizingConstants.Thickness.value * density.density
        // Gap below the footer separator
        fullContentHeight += verticalGapPx
    }

    val footerIconSize = if (richTooltip.footerIcon == null) 0.0f else
        presentationModel.footerIconSize.value * density.density

    // Footer section(s)
    val footerSizes = ArrayList<Size>()
    var footerTextHeightPx = 0.0f
    if (richTooltip.hasFooterSections) {
        // If we have at least one footer section, the entire tooltip goes full max width
        fullContentWidth = maxContentWidthPx

        // Account for vertical gaps between description sections
        footerTextHeightPx += verticalGapPx * (richTooltip.footerSections!!.count() - 1)

        val maxFooterWidthPx = maxTitleWidthPx
        for (footerSection in richTooltip.footerSections) {
            // Create the footer paragraph with the available horizontal space.
            // Note that we're not limiting the description to be single line
            val footerParagraph = Paragraph(
                text = footerSection, style = textStyle, width = maxFooterWidthPx,
                density = density, resourceLoader = resourceLoader
            )

            footerTextHeightPx += footerParagraph.height

            footerSizes.add(
                Size(
                    width = maxFooterWidthPx,
                    height = footerParagraph.height
                )
            )
        }
    }

    fullContentHeight += kotlin.math.max(footerIconSize, footerTextHeightPx)

    // Account for content paddings
    fullContentWidth += horizontalPaddingPx
    fullContentHeight += verticalPaddingPx

    val tooltipLayoutInfo = RichTooltipLayoutInfo(
        fullSize = Size(
            width = fullContentWidth + 2 * offset,
            height = fullContentHeight + 2 * offset
        ),
        titleSize = Size(titleWidth, titleParagraph.height),
        mainIconSize = if (richTooltip.mainIcon == null) Size.Zero else Size(
            presentationModel.mainIconSize.value * density.density,
            presentationModel.mainIconSize.value * density.density
        ),
        descriptionSizes = descriptionSizes,
        footerSeparatorSize = if (!richTooltip.hasFooterContent) Size.Zero else Size(
            fullContentWidth - horizontalPaddingPx,
            SeparatorSizingConstants.Thickness.value * density.density
        ),
        footerIconSize = if (richTooltip.footerIcon == null) Size.Zero else Size(
            presentationModel.footerIconSize.value * density.density,
            presentationModel.footerIconSize.value * density.density
        ),
        footerSizes = footerSizes
    )

    // Full size of the rich tooltip accounts for extra two pixels on each side for the popup border
    val fullPopupWidth = ceil(fullContentWidth / density.density).toInt() + 4
    val fullPopupHeight = ceil(fullContentHeight / density.density).toInt() + 4

    // From this point, all coordinates are in Swing display units - which are density independent.
    // This is why the popup width and height was converted from pixels.
    val initialAnchorX = if (layoutDirection == LayoutDirection.Ltr)
        (locationOnScreen.x + anchorBoundsInWindow.left).toInt() else
        (locationOnScreen.x + anchorBoundsInWindow.left + anchorBoundsInWindow.width).toInt() - fullPopupWidth
    val initialAnchor = IntOffset(
        x = initialAnchorX,
        y = (locationOnScreen.y + anchorBoundsInWindow.top).toInt()
    )

    val popupRect = when (popupPlacementStrategy) {
        PopupPlacementStrategy.Downward -> Rectangle(
            initialAnchor.x,
            initialAnchor.y + anchorBoundsInWindow.height.toInt(),
            fullPopupWidth,
            fullPopupHeight
        )
        PopupPlacementStrategy.Upward -> Rectangle(
            initialAnchor.x,
            initialAnchor.y - fullPopupHeight,
            fullPopupWidth,
            fullPopupHeight
        )
        PopupPlacementStrategy.Startward -> if (layoutDirection == LayoutDirection.Ltr)
            Rectangle(
                initialAnchor.x - fullPopupWidth,
                initialAnchor.y,
                fullPopupWidth,
                fullPopupHeight
            ) else
            Rectangle(
                initialAnchor.x + fullPopupWidth,
                initialAnchor.y,
                fullPopupWidth,
                fullPopupHeight
            )
        PopupPlacementStrategy.Endward -> if (layoutDirection == LayoutDirection.Ltr)
            Rectangle(
                initialAnchor.x + anchorBoundsInWindow.width.toInt(),
                initialAnchor.y,
                fullPopupWidth,
                fullPopupHeight
            ) else
            Rectangle(
                initialAnchor.x - anchorBoundsInWindow.width.toInt(),
                initialAnchor.y,
                fullPopupWidth,
                fullPopupHeight
            )
        PopupPlacementStrategy.CenteredVertically -> Rectangle(
            initialAnchor.x,
            initialAnchor.y + anchorBoundsInWindow.height.toInt() / 2
                    - fullPopupHeight / 2,
            fullPopupWidth,
            fullPopupHeight
        )
    }

    // Make sure the popup stays in screen bounds
    val screenBounds = popupContentWindow.graphicsConfiguration.bounds
    if (popupRect.x < 0) {
        popupRect.translate(-popupRect.x, 0)
    }
    if ((popupRect.x + popupRect.width) > screenBounds.width) {
        popupRect.translate(
            screenBounds.width - popupRect.x - popupRect.width,
            0
        )
    }
    if (popupRect.y < 0) {
        popupRect.translate(0, -popupRect.y)
    }
    if ((popupRect.y + popupRect.height) > screenBounds.height) {
        popupRect.translate(
            0,
            screenBounds.height - popupRect.y - popupRect.height
        )
    }

    popupContentWindow.bounds = popupRect

    val borderScheme = skinColors.getColorScheme(
        decorationAreaType = DecorationAreaType.None,
        associationKind = ColorSchemeAssociationKind.Border,
        componentState = ComponentState.Enabled
    )
    val popupBorderColor = skinPainters.borderPainter.getRepresentativeColor(borderScheme)
    val awtBorderColor = popupBorderColor.awtColor
    val fillColor = skinColors.getBackgroundColorScheme(decorationAreaType).backgroundFillColor
    val awtFillColor = fillColor.awtColor
    val borderThickness = 1.0f / density.density

    popupContentWindow.rootPane.border = object : Border {
        override fun paintBorder(
            c: Component,
            g: Graphics,
            x: Int,
            y: Int,
            width: Int,
            height: Int
        ) {
            val g2d = g.create() as Graphics2D
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g2d.setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE
            )

            g2d.color = awtFillColor
            g2d.fill(Rectangle(0, 0, width, height))

            val thickness = 0.5f
            g2d.stroke = BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND)
            g2d.color = awtBorderColor
            g2d.draw(
                Rectangle2D.Float(
                    borderThickness / 2.0f, borderThickness / 2.0f,
                    width - borderThickness, height - borderThickness
                )
            )
            g2d.dispose()
        }

        override fun getBorderInsets(c: Component?): Insets {
            return Insets(1, 1, 1, 1)
        }

        override fun isBorderOpaque(): Boolean {
            return false
        }
    }

    popupContentWindow.compositionLocalContext = compositionLocalContext
    popupContentWindow.setContent {
        TopLevelTooltipContent(
            popupContentWindow = popupContentWindow,
            richTooltip = richTooltip,
            richTooltipPresentationModel = presentationModel,
            tooltipLayoutInfo = tooltipLayoutInfo
        )
    }

    popupContentWindow.invalidate()
    popupContentWindow.validate()
    popupContentWindow.isVisible = true

    // Hide the popups that "start" from the current window
    AuroraPopupManager.hidePopups(originator = currentWindow)
    // And display our new popup content
    AuroraPopupManager.addPopup(
        originator = currentWindow,
        popupWindow = popupContentWindow
    )
}

@Composable
private fun TopLevelTooltipContent(
    popupContentWindow: ComposeWindow,
    richTooltip: RichTooltip,
    richTooltipPresentationModel: RichTooltipPresentationModel,
    tooltipLayoutInfo: RichTooltipLayoutInfo
) {
    val offset = ceil(LocalDensity.current.density).toInt()
    val layoutDirection = LocalLayoutDirection.current

    Layout(content = {
        TooltipGeneralContent(
            popupContentWindow = popupContentWindow,
            richTooltip = richTooltip,
            richTooltipPresentationModel = richTooltipPresentationModel
        )
    }) { measurables, _ ->
        val verticalGapPx = RichTooltipSizingConstants.VerticalContentLayoutGap.toPx().toInt()
        var placeableIndex = 0

        var iconPlaceable: Placeable? = null
        if (richTooltip.mainIcon != null) {
            iconPlaceable = measurables[placeableIndex++].measure(
                Constraints.fixed(
                    width = tooltipLayoutInfo.mainIconSize.width.toInt(),
                    height = tooltipLayoutInfo.mainIconSize.height.toInt()
                )
            )
        }
        val titlePlaceable = measurables[placeableIndex++].measure(
            Constraints.fixed(
                width = tooltipLayoutInfo.titleSize.width.toInt(),
                height = tooltipLayoutInfo.titleSize.height.toInt()
            )
        )
        val descriptionPlaceables = ArrayList<Placeable>()
        if (richTooltip.hasDescriptionContent) {
            for (index in 0 until richTooltip.descriptionSections!!.size) {
                descriptionPlaceables.add(
                    measurables[placeableIndex++].measure(
                        Constraints.fixed(
                            width = tooltipLayoutInfo.descriptionSizes[index].width.toInt(),
                            height = tooltipLayoutInfo.descriptionSizes[index].height.toInt()
                        )
                    )
                )
            }
        }
        var footerSeparatorPlaceable: Placeable? = null
        if (richTooltip.hasFooterContent) {
            footerSeparatorPlaceable = measurables[placeableIndex++].measure(
                Constraints.fixed(
                    width = tooltipLayoutInfo.footerSeparatorSize.width.toInt(),
                    height = tooltipLayoutInfo.footerSeparatorSize.height.toInt()
                )
            )
        }

        var footerIconPlaceable: Placeable? = null
        if (richTooltip.footerIcon != null) {
            footerIconPlaceable = measurables[placeableIndex++].measure(
                Constraints.fixed(
                    width = tooltipLayoutInfo.footerIconSize.width.toInt(),
                    height = tooltipLayoutInfo.footerIconSize.height.toInt()
                )
            )
        }
        val footerPlaceables = ArrayList<Placeable>()
        if (richTooltip.hasFooterSections) {
            for (index in 0 until richTooltip.footerSections!!.size) {
                footerPlaceables.add(
                    measurables[placeableIndex++].measure(
                        Constraints.fixed(
                            width = tooltipLayoutInfo.footerSizes[index].width.toInt(),
                            height = tooltipLayoutInfo.footerSizes[index].height.toInt()
                        )
                    )
                )
            }
        }

        layout(
            width = tooltipLayoutInfo.fullSize.width.toInt(),
            height = tooltipLayoutInfo.fullSize.height.toInt()
        ) {
            // Offset everything by [offset,offset] for border insets
            // TODO - RTL support
            val left = offset + RichTooltipSizingConstants.ContentPadding.calculateLeftPadding(
                layoutDirection
            ).toPx().toInt()
            val top =
                offset + RichTooltipSizingConstants.ContentPadding.calculateTopPadding().toPx()
                    .toInt()

            var x = left
            if (iconPlaceable != null) {
                iconPlaceable.place(left, top)
                x += (iconPlaceable.width +
                        RichTooltipSizingConstants.HorizontalContentLayoutGap.toPx().toInt())
            }
            titlePlaceable.place(x, top)

            var y = kotlin.math.max(top + (iconPlaceable?.height ?: 0), top + titlePlaceable.height)
            if (descriptionPlaceables.isNotEmpty()) {
                y += verticalGapPx
                for (descriptionPlaceable in descriptionPlaceables) {
                    descriptionPlaceable.place(x, y)
                    y += descriptionPlaceable.height
                    y += verticalGapPx
                }
                // Remove the vertical gap "after" the last description section
                y -= verticalGapPx
            }

            y = y.coerceAtLeast(top + (iconPlaceable?.height ?: 0))

            if (richTooltip.hasFooterContent) {
                y += verticalGapPx
                footerSeparatorPlaceable!!.place(left, y)
                y += footerSeparatorPlaceable.height
                y += verticalGapPx
            }

            x = left
            if (footerIconPlaceable != null) {
                footerIconPlaceable.place(left, y)
                x += (footerIconPlaceable.width +
                        RichTooltipSizingConstants.HorizontalContentLayoutGap.toPx().toInt())
            }

            if (footerPlaceables.isNotEmpty()) {
                for (footerPlaceable in footerPlaceables) {
                    footerPlaceable.place(x, y)
                    y += footerPlaceable.height
                    y += verticalGapPx
                }
            }
        }
    }
}

@Composable
private fun TooltipGeneralContent(
    popupContentWindow: ComposeWindow,
    richTooltip: RichTooltip,
    richTooltipPresentationModel: RichTooltipPresentationModel
) {
    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveAuroraDefaults()
    // And create our own text style with bold weight
    val boldTextStyle = resolveDefaults(
        TextStyle(
            fontSize = resolvedTextStyle.fontSize,
            fontWeight = FontWeight.Bold
        ), LocalLayoutDirection.current
    )

    if (richTooltip.mainIcon != null) {
        IconProjection(
            contentModel = IconContentModel(icon = richTooltip.mainIcon),
            presentationModel = IconPresentationModel(
                iconDimension = richTooltipPresentationModel.mainIconSize
            )
        ).project()
    }

    LabelProjection(
        contentModel = LabelContentModel(text = richTooltip.title),
        presentationModel = LabelPresentationModel(
            textStyle = boldTextStyle,
            contentPadding = PaddingValues(0.dp)
        )
    ).project()

    if (richTooltip.hasDescriptionContent) {
        for (descriptionSection in richTooltip.descriptionSections!!) {
            LabelProjection(
                contentModel = LabelContentModel(text = descriptionSection),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(0.dp)
                )
            ).project()
        }
    }

    if (richTooltip.hasFooterContent) {
        HorizontalSeparatorProjection(
            presentationModel = SeparatorPresentationModel(
                startGradientAmount = 6.dp,
                endGradientAmount = 6.dp
            )
        ).project()

        if (richTooltip.footerIcon != null) {
            IconProjection(
                contentModel = IconContentModel(icon = richTooltip.footerIcon),
                presentationModel = IconPresentationModel(
                    iconDimension = richTooltipPresentationModel.footerIconSize
                )
            ).project()
        }
        if (richTooltip.hasFooterSections) {
            for (footerSection in richTooltip.footerSections!!) {
                LabelProjection(
                    contentModel = LabelContentModel(text = footerSection),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(0.dp)
                    )
                ).project()
            }
        }
    }
}
