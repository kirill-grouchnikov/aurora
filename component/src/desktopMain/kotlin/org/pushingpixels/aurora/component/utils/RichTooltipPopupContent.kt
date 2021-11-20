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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.model.*
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
)

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

    val fullContentWidth = 400.0f
    val fullContentHeight = 200.0f

    val offset = ceil(density.density).toInt()

    val tooltipLayoutInfo = RichTooltipLayoutInfo(
        fullSize = Size(
            width = fullContentWidth + 2 * offset,
            height = fullContentHeight + 2 * offset
        ),
        titleSize = Size(300.0f, 100.0f),
        mainIconSize = Size(presentationModel.mainIconSize.value * density.density,
            presentationModel.mainIconSize.value * density.density)
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

    Layout(content = {
        TooltipGeneralContent(
            popupContentWindow = popupContentWindow,
            richTooltip = richTooltip,
            richTooltipPresentationModel = richTooltipPresentationModel
        )
    }) { measurables, _ ->
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
        layout(
            width = tooltipLayoutInfo.fullSize.width.toInt(),
            height = tooltipLayoutInfo.fullSize.height.toInt()
        ) {
            // Offset everything by [offset,offset] for border insets
            var x = offset
            if (iconPlaceable != null) {
                iconPlaceable.place(offset, offset)
                x += iconPlaceable.width
            }
            titlePlaceable.placeRelative(x, offset)
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
    val boldTextStyle = TextStyle(
        fontSize = resolvedTextStyle.fontSize,
        fontWeight = FontWeight.Bold
    )

    if (richTooltip.mainIcon != null) {
        IconProjection(
            contentModel = IconContentModel(icon = richTooltip.mainIcon),
            presentationModel = IconPresentationModel(iconDimension = richTooltipPresentationModel.mainIconSize)
        ).project()
    }

    LabelProjection(
        contentModel = LabelContentModel(text = richTooltip.title),
        presentationModel = LabelPresentationModel(textStyle = boldTextStyle)
    ).project()
}
