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
package org.pushingpixels.aurora.component.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.popup.awtColor
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.IconProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import java.awt.*
import java.awt.geom.Rectangle2D
import javax.swing.JPopupMenu
import javax.swing.UIManager
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

@OptIn(AuroraInternalApi::class)
internal fun displayRichTooltipWindow(
    popupOriginator: Component,
    layoutDirection: LayoutDirection,
    density: Density,
    textStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver,
    skinColors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
    compositionLocalContext: CompositionLocalContext,
    anchorBoundsInWindow: Rect,
    extraVerticalOffset: Float = 0f,
    richTooltip: RichTooltip,
    presentationModel: RichTooltipPresentationModel,
    popupPlacementStrategy: PopupPlacementStrategy,
): Window? {
    val popupOriginatorLocationOnScreen = popupOriginator.locationOnScreen
    val currentScreenBounds = popupOriginator.graphicsConfiguration.bounds
    popupOriginatorLocationOnScreen.translate(-currentScreenBounds.x, -currentScreenBounds.y)

    val offset = ceil(density.density).toInt()

    // Create our own text style with bold weight for the title
    val boldTextStyle = resolveDefaults(
        TextStyle(
            fontSize = textStyle.fontSize,
            fontWeight = FontWeight.Bold
        ), layoutDirection
    )

    val contentPadding = presentationModel.contentPadding
    val horizontalPaddingPx =
        (contentPadding.calculateLeftPadding(layoutDirection) +
                contentPadding.calculateRightPadding(layoutDirection)).value *
                density.density
    val verticalPaddingPx =
        (contentPadding.calculateTopPadding() +
                contentPadding.calculateBottomPadding()).value *
                density.density
    val verticalGapPx =
        (RichTooltipSizingConstants.VerticalContentLayoutGap.value * density.density).toInt()
    val horizontalGapPx =
        (RichTooltipSizingConstants.HorizontalContentLayoutGap.value * density.density).toInt()

    // Start with the title - spans the entire available width
    val maxContentWidthPx = RichTooltipSizingConstants.MaxWidth.value * density.density -
            horizontalPaddingPx
    // Create the title paragraph with the available horizontal space and bold style.
    // Note that we're not limiting the title to be single line
    val titleParagraph = Paragraph(
        text = richTooltip.title, style = boldTextStyle,
        constraints = Constraints(maxWidth = maxContentWidthPx.toInt()),
        density = density, fontFamilyResolver = fontFamilyResolver
    )
    var fullContentWidth: Float
    var fullContentHeight = titleParagraph.height
    val titleWidth: Float
    if (titleParagraph.lineCount > 1) {
        // If the title goes multi-line, the content spans the entire available space
        titleWidth = maxContentWidthPx
        fullContentWidth = maxContentWidthPx
    } else {
        titleWidth = titleParagraph.maxIntrinsicWidth
        fullContentWidth = titleWidth
    }

    // Main icon - under the title
    val mainIconWidth = if (richTooltip.mainIcon == null) 0.0f else
        presentationModel.mainIconSize.width.value * density.density
    val mainIconHeight = if (richTooltip.mainIcon == null) 0.0f else
        presentationModel.mainIconSize.height.value * density.density

    // Description section(s)
    val descriptionSizes = ArrayList<Size>()
    var descriptionTextHeightPx = 0.0f
    if (richTooltip.hasDescriptionContent) {
        // If we have at least one description section, the entire tooltip goes full max width
        fullContentWidth = maxContentWidthPx

        // Vertical gaps between description sections
        descriptionTextHeightPx += verticalGapPx * (richTooltip.descriptionSections!!.count() - 1)

        // If we're showing the icon, that eats into the horizontal space available to the
        // description sections
        val maxDescriptionWidthPx = if (richTooltip.mainIcon == null) maxContentWidthPx
        else maxContentWidthPx - mainIconWidth - horizontalGapPx

        for (descriptionSection in richTooltip.descriptionSections) {
            // Create the description paragraph with the available horizontal space.
            // Note that we're not limiting the description to be single line
            val descriptionParagraph = Paragraph(
                text = descriptionSection, style = textStyle,
                constraints = Constraints(maxWidth = maxDescriptionWidthPx.toInt()),
                density = density, fontFamilyResolver = fontFamilyResolver
            )

            descriptionTextHeightPx += descriptionParagraph.height

            descriptionSizes.add(
                Size(
                    width = maxDescriptionWidthPx,
                    height = descriptionParagraph.height
                )
            )
        }
    }

    // Content height so far is the max of icon height and title / descriptions height
    if ((mainIconHeight > 0) || (descriptionTextHeightPx > 0)) {
        // Gap between title and icon / description
        fullContentHeight += verticalGapPx
        fullContentHeight += kotlin.math.max(mainIconHeight, descriptionTextHeightPx)
    }

    if (richTooltip.hasFooterContent) {
        // Gap above the footer separator
        fullContentHeight += verticalGapPx
        // Separator itself
        fullContentHeight += SeparatorSizingConstants.Thickness.value * density.density
        // Gap below the footer separator
        fullContentHeight += verticalGapPx
    }

    val footerIconWidth = if (richTooltip.footerIcon == null) 0.0f else
        presentationModel.footerIconSize.width.value * density.density
    val footerIconHeight = if (richTooltip.footerIcon == null) 0.0f else
        presentationModel.footerIconSize.height.value * density.density

    // Footer section(s)
    val footerSizes = ArrayList<Size>()
    var footerTextHeightPx = 0.0f
    if (richTooltip.hasFooterSections) {
        // If we have at least one footer section, the entire tooltip goes full max width
        fullContentWidth = maxContentWidthPx

        // Account for vertical gaps between description sections
        footerTextHeightPx += verticalGapPx * (richTooltip.footerSections!!.count() - 1)

        // If we're showing the footer icon, that eats into the horizontal space available to the
        // footer sections
        val maxFooterWidthPx = if (richTooltip.footerIcon == null) maxContentWidthPx
        else maxContentWidthPx - footerIconWidth - horizontalGapPx

        for (footerSection in richTooltip.footerSections) {
            // Create the footer paragraph with the available horizontal space.
            // Note that we're not limiting the description to be single line
            val footerParagraph = Paragraph(
                text = footerSection, style = textStyle,
                constraints = Constraints(maxWidth = maxFooterWidthPx.toInt()),
                density = density, fontFamilyResolver = fontFamilyResolver
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

    fullContentHeight += kotlin.math.max(footerIconHeight, footerTextHeightPx)

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
            presentationModel.mainIconSize.width.value * density.density,
            presentationModel.mainIconSize.height.value * density.density
        ),
        descriptionSizes = descriptionSizes,
        footerSeparatorSize = if (!richTooltip.hasFooterContent) Size.Zero else Size(
            fullContentWidth - horizontalPaddingPx,
            SeparatorSizingConstants.Thickness.value * density.density
        ),
        footerIconSize = if (richTooltip.footerIcon == null) Size.Zero else Size(
            presentationModel.footerIconSize.width.value * density.density,
            presentationModel.footerIconSize.height.value * density.density
        ),
        footerSizes = footerSizes
    )

    // Full size of the rich tooltip accounts for extra pixel (in DP units) on each side for the popup border
    val fullPopupWidth = ceil(fullContentWidth / density.density).toInt() + 2
    val fullPopupHeight = ceil(fullContentHeight / density.density).toInt() + 2

    // From this point, all coordinates are in Swing display units - which are density independent.
    // This is why the popup width and height was converted from pixels.
    val initialAnchorX = if (layoutDirection == LayoutDirection.Ltr)
        (popupOriginatorLocationOnScreen.x + anchorBoundsInWindow.left).toInt() else
        (popupOriginatorLocationOnScreen.x + anchorBoundsInWindow.left + anchorBoundsInWindow.width).toInt() - fullPopupWidth
    // Initial anchor corresponds to the on-screen location of the top-left corner of the
    // popup window under the default PopupPlacementStrategy.Downward.HAlignStart placement
    // strategy
    val initialAnchor = IntOffset(
        x = initialAnchorX,
        y = (popupOriginatorLocationOnScreen.y + anchorBoundsInWindow.top).toInt()
    )

    val popupShift = BaseCommandMenuHandler.getPlacementAwarePopupShift(
        layoutDirection = layoutDirection,
        anchorDimension = IntSize(
            width = anchorBoundsInWindow.width.toInt(),
            height = anchorBoundsInWindow.height.toInt()
        ),
        popupDimension = IntSize(fullPopupWidth, fullPopupHeight),
        popupPlacementStrategy = popupPlacementStrategy
    )
    val popupRect = Rectangle(
        initialAnchor.x + popupShift.width,
        initialAnchor.y + anchorBoundsInWindow.height.toInt() + popupShift.height + extraVerticalOffset.toInt(),
        fullPopupWidth,
        fullPopupHeight
    )

    // Make sure the popup stays in screen bounds
    val screenBounds = popupOriginator.graphicsConfiguration.bounds
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

    val neutralColorTokens = skinColors.getNeutralContainerTokens(decorationAreaType)

    val popupContent = ComposePanel()
    val fillColor = neutralColorTokens.containerSurface
    val awtFillColor = fillColor.awtColor
    popupContent.background = awtFillColor

    val popupBorderColor = neutralColorTokens.containerOutline
    val awtBorderColor = popupBorderColor.awtColor
    val borderThickness = 1.0f / density.density

    popupContent.border = object : Border {
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
    popupContent.preferredSize = Dimension(popupRect.width, popupRect.height)

    val popupDpSize = DpSize(
        width = (popupRect.width / density.density).dp,
        height = (popupRect.height / density.density).dp
    )

    // This line is needed to ensure that each popup is displayed in its own heavyweight window
    JPopupMenu.setDefaultLightWeightPopupEnabled(false)

    // ComposePanel has a private-access ComposeLayer (which is internal). That one has a SkiaLayer
    // which extends JPanel. Since there is no direct access to that panel, configure its background
    // color indirectly through setting this entry in the Swing's UIManager table.
    UIManager.put("Panel.background", awtFillColor)

    val popupMenu = AuroraSwingPopupMenu(true)
    popupMenu.background = awtFillColor
    popupContent.setContent {
        // Get the current composition context
        CompositionLocalProvider(compositionLocalContext) {
            // And add the composition locals for the new popup
            CompositionLocalProvider(
                LocalPopupMenu provides popupMenu,
                LocalWindowSize provides popupDpSize,
                LocalTopWindowSize provides LocalTopWindowSize.current,
                LocalSkinColors provides LocalSkinColors.current,
            ) {
                Box(modifier = Modifier.fillMaxSize().background(fillColor)) {
                    TopLevelRichTooltipContent(
                        richTooltip = richTooltip,
                        richTooltipPresentationModel = presentationModel,
                        tooltipLayoutInfo = tooltipLayoutInfo
                    )
                }
            }
        }
    }
    popupMenu.add(popupContent)

    // Hide the popups that "start" from our popup originator
    AuroraPopupManager.hidePopups(originator = popupOriginator)
    // And display our new popup content
    return AuroraPopupManager.showPopup(
        originator = popupOriginator,
        popupTriggerAreaInOriginatorWindow = Rect.Zero,
        popup = popupMenu,
        popupContent = popupContent,
        popupRectOnScreen = popupRect,
        popupKind = AuroraPopupManager.PopupKind.RichTooltip,
        onActivatePopup = null,
        onDeactivatePopup = null
    )
}

@Composable
private fun Modifier.richTooltipBackground() = this.then(
    RichTooltipBackground(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors
    )
)

private class RichTooltipBackground(
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        val tokens = colors.getNeutralContainerTokens(decorationAreaType)
        val topColor = if (tokens.isDark) {
            tokens.containerSurface
        } else {
            tokens.containerSurfaceLowest
        }
        val bottomColor = tokens.containerSurfaceLow
        val brush = Brush.verticalGradient(
            0.0f to topColor,
            1.0f to bottomColor,
            startY = 0.0f,
            endY = size.height,
            tileMode = TileMode.Clamp
        )
        drawRect(brush = brush)

        // And don't forget to draw the content
        drawContent()
    }
}

@Composable
private fun TopLevelRichTooltipContent(
    richTooltip: RichTooltip,
    richTooltipPresentationModel: RichTooltipPresentationModel,
    tooltipLayoutInfo: RichTooltipLayoutInfo
) {
    val offset = ceil(LocalDensity.current.density).toInt()
    val layoutDirection = LocalLayoutDirection.current
    val contentPadding = richTooltipPresentationModel.contentPadding

    Layout(modifier = Modifier.richTooltipBackground(),
        content = {
            RichTooltipContent(
                richTooltip = richTooltip,
                richTooltipPresentationModel = richTooltipPresentationModel
            )
        }) { measurables, _ ->
        val verticalGapPx = RichTooltipSizingConstants.VerticalContentLayoutGap.toPx().toInt()
        val horizontalGapPx = RichTooltipSizingConstants.HorizontalContentLayoutGap.toPx().toInt()
        var placeableIndex = 0

        var iconPlaceable: Placeable? = null
        val titlePlaceable = measurables[placeableIndex++].measure(
            Constraints.fixed(
                width = tooltipLayoutInfo.titleSize.width.toInt(),
                height = tooltipLayoutInfo.titleSize.height.toInt()
            )
        )
        if (richTooltip.mainIcon != null) {
            iconPlaceable = measurables[placeableIndex++].measure(
                Constraints.fixed(
                    width = tooltipLayoutInfo.mainIconSize.width.toInt(),
                    height = tooltipLayoutInfo.mainIconSize.height.toInt()
                )
            )
        }
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
            val left = offset + contentPadding.calculateLeftPadding(
                layoutDirection
            ).toPx().toInt()
            val top =
                offset + contentPadding.calculateTopPadding().toPx().toInt()

            titlePlaceable.placeRelative(left, top)

            var x = left
            var y = top + titlePlaceable.height

            if ((iconPlaceable != null) || (descriptionPlaceables.isNotEmpty())) {
                y += verticalGapPx
            }

            var iconBottom = y
            if (iconPlaceable != null) {
                iconPlaceable.placeRelative(x, y)
                x += (iconPlaceable.width + horizontalGapPx)
                iconBottom = y + iconPlaceable.height
            }

            if (descriptionPlaceables.isNotEmpty()) {
                for (descriptionPlaceable in descriptionPlaceables) {
                    descriptionPlaceable.placeRelative(x, y)
                    y += descriptionPlaceable.height
                    y += verticalGapPx
                }
                // Remove the vertical gap "after" the last description section
                y -= verticalGapPx
            }

            y = y.coerceAtLeast(iconBottom)

            if (richTooltip.hasFooterContent) {
                y += verticalGapPx
                footerSeparatorPlaceable!!.placeRelative(left, y)
                y += footerSeparatorPlaceable.height
                y += verticalGapPx
            }

            x = left
            if (footerIconPlaceable != null) {
                footerIconPlaceable.placeRelative(left, y)
                x += (footerIconPlaceable.width +
                        RichTooltipSizingConstants.HorizontalContentLayoutGap.toPx().toInt())
            }

            if (footerPlaceables.isNotEmpty()) {
                for (footerPlaceable in footerPlaceables) {
                    footerPlaceable.placeRelative(x, y)
                    y += footerPlaceable.height
                    y += verticalGapPx
                }
            }
        }
    }
}

@Composable
private fun RichTooltipContent(
    richTooltip: RichTooltip,
    richTooltipPresentationModel: RichTooltipPresentationModel
) {
    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveAuroraDefaults()
    // And create our own text style with bold weight. Note that we also unset
    // the color afterwards (as resolveDefaults sets it to the default black, but
    // we do want to use our own text color resolution that is based on the skin
    // and the decoration area type).
    val boldTextStyle = resolveDefaults(
        TextStyle(
            fontSize = resolvedTextStyle.fontSize,
            fontWeight = FontWeight.Bold
        ), LocalLayoutDirection.current
    ).copy(color = Color.Unspecified)

    LabelProjection(
        contentModel = LabelContentModel(text = richTooltip.title),
        presentationModel = LabelPresentationModel(
            textStyle = boldTextStyle,
            contentPadding = PaddingValues(0.dp)
        )
    ).project()

    if (richTooltip.mainIcon != null) {
        IconProjection(
            contentModel = IconContentModel(icon = richTooltip.mainIcon),
            presentationModel = IconPresentationModel(
                iconDimension = richTooltipPresentationModel.mainIconSize
            )
        ).project()
    }

    if (richTooltip.hasDescriptionContent) {
        for (descriptionSection in richTooltip.descriptionSections!!) {
            LabelProjection(
                contentModel = LabelContentModel(text = descriptionSection),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(0.dp),
                    horizontalAlignment = HorizontalAlignment.Leading
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
                        contentPadding = PaddingValues(0.dp),
                        horizontalAlignment = HorizontalAlignment.Leading
                    )
                ).project()
            }
        }
    }
}
