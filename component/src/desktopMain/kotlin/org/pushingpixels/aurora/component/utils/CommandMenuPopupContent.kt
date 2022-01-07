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
package org.pushingpixels.aurora.component.utils

import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import java.awt.*
import java.awt.geom.Rectangle2D
import javax.swing.border.Border
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

internal val Color.awtColor: java.awt.Color
    get() = java.awt.Color(
        this.red, this.green, this.blue, this.alpha
    )

internal data class PopupContentLayoutInfo(
    val fullSize: Size,
    val buttonPanelSize: Size,
    val separatorSize: Size,
    val generalContentSize: Size,
    val generalContentItemHeights: FloatArray,
    val generalVerticalScrollbarSize: Size
)

internal fun displayPopupContent(
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
    contentModel: State<CommandMenuContentModel?>,
    presentationModel: CommandPopupMenuPresentationModel,
    toDismissPopupsOnActivation: Boolean,
    toUseBackgroundStriping: Boolean,
    popupPlacementStrategy: PopupPlacementStrategy,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>
) {
    val popupContentWindow = ComposeWindow()
    popupContentWindow.focusableWindowState = false
    popupContentWindow.type = Window.Type.POPUP
    popupContentWindow.isAlwaysOnTop = true
    popupContentWindow.isUndecorated = true
    popupContentWindow.isResizable = false

    val locationOnScreen = currentWindow.rootPane.locationOnScreen

    val hasButtonPanel = (contentModel.value!!.panelContentModel != null)
    val panelButtonLayoutManager =
        if (hasButtonPanel) presentationModel.panelPresentationModel!!.commandPresentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = textStyle,
            resourceLoader = resourceLoader
        ) else null

    // Compute the size of the popup content, accounting for the panel
    val panelPreferredSize =
        if (hasButtonPanel) getPreferredCommandButtonPanelSize(
            contentModel = contentModel.value!!.panelContentModel!!,
            presentationModel = presentationModel.panelPresentationModel!!,
            buttonLayoutManager = panelButtonLayoutManager!!,
            layoutDirection = layoutDirection,
            density = density
        ) else Size(0.0f, 0.0f)

    // Command presentation for menu content, taking some values from
    // the popup menu presentation model configured on the top-level presentation model
    val regularButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.menuPresentationState,
        iconActiveFilterStrategy = presentationModel.menuIconActiveFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.menuIconEnabledFilterStrategy,
        iconDisabledFilterStrategy = presentationModel.menuIconDisabledFilterStrategy,
        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        horizontalAlignment = HorizontalAlignment.Leading,
        contentPadding = presentationModel.menuContentPadding,
        isMenu = true
    )
    val regularButtonLayoutManager =
        presentationModel.menuPresentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = textStyle,
            resourceLoader = resourceLoader
        )

    var atLeastOneRegularButtonHasIcon = false
    for (commandGroup in contentModel.value!!.groups) {
        for (secondaryCommand in commandGroup.commands) {
            if (secondaryCommand.icon != null) {
                atLeastOneRegularButtonHasIcon = true
            }
            if (secondaryCommand.isActionToggle) {
                atLeastOneRegularButtonHasIcon = true
            }
        }
    }

    val regularButtonPresentationModelOverlay =
        CommandButtonPresentationModel.Overlay(
            forceAllocateSpaceForIcon = atLeastOneRegularButtonHasIcon,
            textStyle = TextStyle(fontWeight = FontWeight.Bold)
        )
    val regularButtonPresentationModelWithOverlay =
        regularButtonPresentationModel.overlayWith(
            regularButtonPresentationModelOverlay
        )

    // Account for possible presence of vertical scroll bar
    var showingVerticalRegularContentScrollBar = false

    var regularButtonColumnWidth = 0.0f
    var regularButtonCount = 0
    var regularButtonHeight = 0.0f
    for (commandGroup in contentModel.value!!.groups) {
        for (secondaryCommand in commandGroup.commands) {
            val preferredSize = regularButtonLayoutManager.getPreferredSize(
                command = secondaryCommand,
                presentationModel = regularButtonPresentationModelWithOverlay,
                preLayoutInfo = regularButtonLayoutManager.getPreLayoutInfo(
                    command = secondaryCommand,
                    presentationModel = regularButtonPresentationModelWithOverlay
                )
            )
            regularButtonColumnWidth = max(regularButtonColumnWidth, preferredSize.width)
            regularButtonHeight = max(regularButtonHeight, preferredSize.height)

            regularButtonCount++
            if (presentationModel.maxVisibleMenuCommands == regularButtonCount) {
                // This is the maximum number of menu commands that we should show
                showingVerticalRegularContentScrollBar = true
            }
        }
    }
    val separatorHeight = SeparatorSizingConstants.Thickness.value * density.density
    var index = 0
    val itemHeights = FloatArray(regularButtonCount + contentModel.value!!.groups.size - 1)
    for ((groupIndex, commandGroup) in contentModel.value!!.groups.withIndex()) {
        for (secondaryCommand in commandGroup.commands) {
            itemHeights[index++] = regularButtonHeight
        }
        if (groupIndex < (contentModel.value!!.groups.size - 1)) {
            itemHeights[index++] = separatorHeight
        }
    }

    val visibleCommands = if (presentationModel.maxVisibleMenuCommands == 0) regularButtonCount
    else min(presentationModel.maxVisibleMenuCommands, regularButtonCount)
    val regularButtonColumnHeight = visibleCommands * regularButtonHeight +
            (contentModel.value!!.groups.size - 1) * separatorHeight

    val generalVerticalScrollbarWidth = if (showingVerticalRegularContentScrollBar) {
        ScrollBarSizingConstants.DefaultScrollBarThickness.value * density.density +
                2 * ScrollBarSizingConstants.DefaultScrollBarMargin.value * density.density
    } else {
        0.0f
    }

    // If we're displaying the button panel, we need to tweak either the button panel width
    // or the general content width so that they match
    val fullContentWidth = max(
        panelPreferredSize.width,
        regularButtonColumnWidth + generalVerticalScrollbarWidth
    )
    val fullContentHeight = panelPreferredSize.height +
            (if (hasButtonPanel) SeparatorSizingConstants.Thickness.value * density.density else 0.0f) +
            regularButtonColumnHeight
    val finalGeneralContentWidth = fullContentWidth - generalVerticalScrollbarWidth

    val offset = ceil(density.density).toInt()

    val contentLayoutInfo = PopupContentLayoutInfo(
        fullSize = Size(
            width = fullContentWidth + 2 * offset,
            height = fullContentHeight + 2 * offset
        ),
        buttonPanelSize = Size(width = fullContentWidth, height = panelPreferredSize.height),
        separatorSize = Size(
            width = fullContentWidth,
            height = if (hasButtonPanel) SeparatorSizingConstants.Thickness.value * density.density else 0.0f
        ),
        generalContentSize = Size(
            width = finalGeneralContentWidth,
            height = regularButtonColumnHeight
        ),
        generalContentItemHeights = itemHeights,
        generalVerticalScrollbarSize = Size(
            width = generalVerticalScrollbarWidth,
            height = regularButtonColumnHeight
        )
    )

    // Full size of the popup accounts for extra two pixels on each side for the popup border
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
        TopLevelPopupContent(
            popupContentWindow = popupContentWindow,
            menuContentModel = contentModel,
            menuPresentationModel = presentationModel,
            toDismissPopupsOnActivation = toDismissPopupsOnActivation,
            toUseBackgroundStriping = toUseBackgroundStriping,
            overlays = overlays,
            contentLayoutInfo = contentLayoutInfo
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
        popupWindow = popupContentWindow,
        popupKind = AuroraPopupManager.PopupKind.POPUP
    )
}

@Composable
private fun TopLevelPopupContent(
    popupContentWindow: ComposeWindow,
    menuContentModel: State<CommandMenuContentModel?>,
    menuPresentationModel: CommandPopupMenuPresentationModel,
    toDismissPopupsOnActivation: Boolean,
    toUseBackgroundStriping: Boolean,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
    contentLayoutInfo: PopupContentLayoutInfo
) {
    val stateVertical = rememberScrollState(0)

    val hasPanel = (menuContentModel.value!!.panelContentModel != null)
    PopupContentLayout(
        modifier = Modifier.auroraBackground(),
        hasPanel = hasPanel,
        contentLayoutInfo = contentLayoutInfo
    ) {
        if (hasPanel) {
            AuroraCommandButtonPanel(
                contentModel = menuContentModel.value!!.panelContentModel!!,
                presentationModel = menuPresentationModel.panelPresentationModel!!,
                extraAction = {
                    if (toDismissPopupsOnActivation) {
                        AuroraPopupManager.hidePopups(null)
                    }
                }
            )
            HorizontalSeparatorProjection(
                presentationModel = SeparatorPresentationModel(
                    startGradientAmount = 0.dp,
                    endGradientAmount = 0.dp
                )
            ).project()
        }

        Layout(modifier = Modifier.verticalScroll(stateVertical), content = {
            PopupGeneralContent(
                popupContentWindow = popupContentWindow,
                menuContentModel = menuContentModel,
                menuPresentationModel = menuPresentationModel,
                toDismissPopupsOnActivation = toDismissPopupsOnActivation,
                toUseBackgroundStriping = toUseBackgroundStriping,
                overlays = overlays
            )
        }) { measurables, _ ->
            val placeables = measurables.mapIndexed { index, measurable ->
                // Measure each child with fixed (widest) width and fixed (tallest) height
                measurable.measure(
                    Constraints.fixed(
                        width = contentLayoutInfo.generalContentSize.width.roundToInt(),
                        height = contentLayoutInfo.generalContentItemHeights[index].toInt()
                    )
                )
            }

            // The children are laid out in a column
            val contentMaxHeight = placeables.sumOf { it.height }

            layout(
                width = contentLayoutInfo.generalContentSize.width.roundToInt(),
                height = contentMaxHeight
            ) {
                var yPosition = 0

                placeables.forEach { placeable ->
                    placeable.placeRelative(
                        x = 0,
                        y = yPosition
                    )
                    yPosition += placeable.height
                }
            }
        }
        if (contentLayoutInfo.generalVerticalScrollbarSize.width > 0.0f) {
            AuroraVerticalScrollbar(
                adapter = remember(stateVertical) {
                    ScrollbarAdapter(stateVertical)
                }
            )
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun PopupGeneralContent(
    popupContentWindow: ComposeWindow,
    menuContentModel: State<CommandMenuContentModel?>,
    menuPresentationModel: CommandPopupMenuPresentationModel,
    toDismissPopupsOnActivation: Boolean,
    toUseBackgroundStriping: Boolean,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>
) {
    // If at least one secondary command in this popup menu has icon factory
    // we force all command buttons to allocate space for the icon (for overall
    // alignment of content across the entire popup menu)
    var atLeastOneButtonHasIcon = false
    for (commandGroup in menuContentModel.value!!.groups) {
        for (secondaryCommand in commandGroup.commands) {
            if (secondaryCommand.icon != null) {
                atLeastOneButtonHasIcon = true
            }
            if (secondaryCommand.isActionToggle) {
                atLeastOneButtonHasIcon = true
            }
        }
    }

    // Command presentation for menu content, taking some values from
    // the popup menu presentation model configured on the top-level presentation model
    val menuButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = menuPresentationModel.menuPresentationState,
        iconActiveFilterStrategy = menuPresentationModel.menuIconActiveFilterStrategy,
        iconEnabledFilterStrategy = menuPresentationModel.menuIconEnabledFilterStrategy,
        iconDisabledFilterStrategy = menuPresentationModel.menuIconDisabledFilterStrategy,
        forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
        popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        horizontalAlignment = HorizontalAlignment.Leading,
        contentPadding = menuPresentationModel.menuContentPadding,
        isMenu = true,
        sides = Sides(straightSides = Side.values().toSet())
    )

    var runningCommandIndex = 0
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
    val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
    for ((commandGroupIndex, commandGroup) in menuContentModel.value!!.groups.withIndex()) {
        for (secondaryCommand in commandGroup.commands) {
            // Check if we have a presentation overlay for this secondary command
            val hasOverlay = overlays.containsKey(secondaryCommand)
            var currSecondaryPresentationModel = if (hasOverlay)
                menuButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
            else menuButtonPresentationModel
            if (secondaryCommand == menuContentModel.value!!.highlightedCommand) {
                // If our secondary content model has a highlighted command, pass bold
                // font weight to the text style of the matching command button.
                currSecondaryPresentationModel =
                    currSecondaryPresentationModel.overlayWith(
                        CommandButtonPresentationModel.Overlay(
                            textStyle = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    )
            }

            // Create a command button for each secondary command, passing the same
            // overlays into it.
            AuroraCommandButton(
                modifier = if (toUseBackgroundStriping)
                    Modifier.background(
                        color = if ((runningCommandIndex % 2) == 0) backgroundEvenRows else backgroundOddRows
                    ) else Modifier,
                actionInteractionSource = remember { MutableInteractionSource() },
                popupInteractionSource = remember { MutableInteractionSource() },
                command = secondaryCommand,
                parentWindow = popupContentWindow,
                extraAction = {
                    if (toDismissPopupsOnActivation and
                        currSecondaryPresentationModel.toDismissPopupsOnActivation
                    ) {
                        AuroraPopupManager.hidePopups(null)
                    }
                },
                popupPlacementStrategyProvider = null,
                presentationModel = currSecondaryPresentationModel,
                overlays = overlays
            )
            runningCommandIndex++
        }
        if (commandGroupIndex < (menuContentModel.value!!.groups.size - 1)) {
            HorizontalSeparatorProjection(
                presentationModel = SeparatorPresentationModel(
                    startGradientAmount = 0.dp,
                    endGradientAmount = 0.dp
                )
            ).project()
        }
    }
}

@Composable
private fun PopupContentLayout(
    hasPanel: Boolean,
    modifier: Modifier = Modifier,
    contentLayoutInfo: PopupContentLayoutInfo,
    content: @Composable () -> Unit
) {
    val offset = ceil(LocalDensity.current.density).toInt()
    Layout(modifier = modifier, content = content) { measurables, _ ->
        var panelPlaceable: Placeable? = null
        var panelSeparatorPlaceable: Placeable? = null
        if (hasPanel) {
            // The column width is determined by the panel
            panelPlaceable = measurables[0].measure(
                Constraints.fixed(
                    width = contentLayoutInfo.buttonPanelSize.width.toInt(),
                    height = contentLayoutInfo.buttonPanelSize.height.toInt()
                )
            )
            panelSeparatorPlaceable = measurables[1].measure(
                Constraints.fixed(
                    width = contentLayoutInfo.separatorSize.width.toInt(),
                    height = contentLayoutInfo.separatorSize.height.toInt()
                )
            )
        }

        val generalContentPlaceable = measurables[if (hasPanel) 2 else 0].measure(
            Constraints.fixed(
                width = contentLayoutInfo.generalContentSize.width.toInt(),
                height = contentLayoutInfo.generalContentSize.height.toInt()
            )
        )
        var verticalScrollBarPlaceable: Placeable? = null
        val scrollBarMarginPx = ScrollBarSizingConstants.DefaultScrollBarMargin.roundToPx()
        val scrollBarThicknessPx = ScrollBarSizingConstants.DefaultScrollBarThickness.roundToPx()
        if (contentLayoutInfo.generalVerticalScrollbarSize.width > 0.0f) {
            verticalScrollBarPlaceable = measurables[if (hasPanel) 3 else 1].measure(
                Constraints.fixed(
                    width = scrollBarThicknessPx,
                    height = contentLayoutInfo.generalVerticalScrollbarSize.height.toInt() - 2 * scrollBarMarginPx
                )
            )
        }

        layout(
            width = contentLayoutInfo.fullSize.width.toInt(),
            height = contentLayoutInfo.fullSize.height.toInt()
        ) {
            // Offset everything by [offset,offset] for border insets
            var yPosition = offset
            if (panelPlaceable != null) {
                panelPlaceable.placeRelative(offset, offset)
                yPosition += panelPlaceable.height
                panelSeparatorPlaceable!!.placeRelative(offset, yPosition)
                yPosition += panelSeparatorPlaceable.height
            }

            generalContentPlaceable.placeRelative(x = offset, y = yPosition)
            verticalScrollBarPlaceable?.placeRelative(
                x = offset + generalContentPlaceable.width + scrollBarMarginPx + offset,
                y = yPosition + scrollBarMarginPx
            )
        }
    }
}
