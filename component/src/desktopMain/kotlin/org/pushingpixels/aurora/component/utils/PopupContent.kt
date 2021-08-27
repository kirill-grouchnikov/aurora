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

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import java.awt.Rectangle
import java.awt.Window
import kotlin.math.max
import kotlin.math.roundToInt

internal data class PopupContentLayoutInfo(
    val fullSize: Size,
    val buttonPanelSize: Size,
    val separatorSize: Size,
    val generalContentSize: Size,
    val generalVerticalScrollbarSize: Size
)

internal fun displayPopupContent(
    currentWindow: ComposeWindow,
    layoutDirection: LayoutDirection,
    density: Density,
    textStyle: TextStyle,
    resourceLoader: Font.ResourceLoader,
    parentComposition: CompositionContext,
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

    val locationOnScreen = currentWindow.locationOnScreen

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

    // Command presentation for menu content, taking some of the values from
    // the popup menu presentation model configured on the top-level presentation model
    val regularButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.menuPresentationState,
        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        horizontalAlignment = HorizontalAlignment.Leading,
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
    var regularButtonColumnHeight = 0.0f
    var regularButtonCount = 0
    for ((commandGroupIndex, commandGroup) in contentModel.value!!.groups.withIndex()) {
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
            if (!showingVerticalRegularContentScrollBar) {
                regularButtonColumnHeight += preferredSize.height
            }

            regularButtonCount++
            if (presentationModel.maxVisibleMenuCommands == regularButtonCount) {
                // This is the maximum number of menu commands that we should show
                showingVerticalRegularContentScrollBar = true
            }
        }
        // Account for horizontal separator between secondary command groups
        if (commandGroupIndex < (contentModel.value!!.groups.size - 1)) {
            regularButtonColumnHeight += SeparatorSizingConstants.Thickness.value * density.density
        }
    }

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
    val finalGeneralContentWidth = fullContentWidth - generalVerticalScrollbarWidth

    // Full size of the popup accounts for extra one pixel on each side for the popup border
    val contentLayoutInfo = PopupContentLayoutInfo(
        fullSize = Size(
            width = fullContentWidth + 2,
            height = panelPreferredSize.height +
                    (if (hasButtonPanel) SeparatorSizingConstants.Thickness.value * density.density else 0.0f) +
                    regularButtonColumnHeight + 2
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
        generalVerticalScrollbarSize = Size(
            width = generalVerticalScrollbarWidth,
            height = regularButtonColumnHeight
        )
    )

    val fullPopupWidth = (contentLayoutInfo.fullSize.width / density.density).toInt()
    val fullPopupHeight = (contentLayoutInfo.fullSize.height / density.density).toInt()

    // From this point, all coordinates are in Swing display units - which are density independent.
    // This is why the popup width and height was converted from pixels.
    val initialAnchor = IntOffset(
        x = (locationOnScreen.x + anchorBoundsInWindow.left).toInt(),
        y = (locationOnScreen.y + anchorBoundsInWindow.top).toInt()
    )

    // TODO - support RTL for startward and endward
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
        PopupPlacementStrategy.Startward -> Rectangle(
            initialAnchor.x - fullPopupWidth,
            initialAnchor.y,
            fullPopupWidth,
            fullPopupHeight
        )
        PopupPlacementStrategy.Endward -> Rectangle(
            initialAnchor.x + anchorBoundsInWindow.width.toInt(),
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

    popupContentWindow.setContent(parentComposition = parentComposition) {
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
        popupWindow = popupContentWindow
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

    val borderScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = DecorationAreaType.None,
        associationKind = ColorSchemeAssociationKind.Border,
        componentState = ComponentState.Enabled
    )
    val popupBorderColor = AuroraSkin.painters.borderPainter.getRepresentativeColor(borderScheme)
    val hasPanel = (menuContentModel.value!!.panelContentModel != null)
    PopupContentLayout(
        hasPanel = hasPanel,
        contentLayoutInfo = contentLayoutInfo
    ) {
        // This canvas paints the background fill of the popup and the outer hairline border
        Canvas(modifier = Modifier.auroraBackground()) {
            val outline = Outline.Rectangle(
                rect = Rect(
                    left = 0.5f,
                    top = 0.5f,
                    right = size.width - 0.5f,
                    bottom = size.height - 0.5f
                )
            )
            drawOutline(
                outline = outline, color = popupBorderColor, style = Stroke(width = 1.0f)
            )
        }
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
            val placeables = measurables.map { measurable ->
                // Measure each child with fixed (widest) width
                measurable.measure(Constraints.fixedWidth(contentLayoutInfo.generalContentSize.width.roundToInt()))
            }

            // The children are laid out in a column
            val contentMaxHeight = placeables.sumOf { it.height }

            layout(
                width = contentLayoutInfo.generalContentSize.width.roundToInt(),
                height = contentMaxHeight
            ) {
                var yPosition = 0

                // TODO - support RTL
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

    // Command presentation for menu content, taking some of the values from
    // the popup menu presentation model configured on the top-level presentation model
    val menuButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = menuPresentationModel.menuPresentationState,
        forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
        popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        horizontalAlignment = HorizontalAlignment.Leading,
        isMenu = true
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
                command = secondaryCommand,
                parentWindow = popupContentWindow,
                extraAction = {
                    if (toDismissPopupsOnActivation and
                        currSecondaryPresentationModel.toDismissPopupsOnActivation
                    ) {
                        AuroraPopupManager.hidePopups(null)
                    }
                },
                presentationModel = currSecondaryPresentationModel,
                overlays = overlays,
                buttonSides = Sides(straightSides = Side.values().toSet())
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
    contentLayoutInfo: PopupContentLayoutInfo,
    content: @Composable () -> Unit
) {
    Layout(content = content) { measurables, _ ->
        val canvasPlaceable = measurables[0].measure(
            constraints = Constraints.fixed(
                width = contentLayoutInfo.fullSize.width.toInt(),
                height = contentLayoutInfo.fullSize.height.toInt()
            )
        )

        var panelPlaceable: Placeable? = null
        var panelSeparatorPlaceable: Placeable? = null
        if (hasPanel) {
            // The column width is determined by the panel
            panelPlaceable = measurables[1].measure(
                Constraints.fixed(
                    width = contentLayoutInfo.buttonPanelSize.width.toInt(),
                    height = contentLayoutInfo.buttonPanelSize.height.toInt()
                )
            )
            panelSeparatorPlaceable = measurables[2].measure(
                Constraints.fixed(
                    width = contentLayoutInfo.separatorSize.width.toInt(),
                    height = contentLayoutInfo.separatorSize.height.toInt()
                )
            )
        }

        val generalContentPlaceable = measurables[if (hasPanel) 3 else 1].measure(
            Constraints.fixed(
                width = contentLayoutInfo.generalContentSize.width.toInt(),
                height = contentLayoutInfo.generalContentSize.height.toInt()
            )
        )
        var verticalScrollBarPlaceable: Placeable? = null
        val scrollBarMarginPx = ScrollBarSizingConstants.DefaultScrollBarMargin.roundToPx()
        val scrollBarThicknessPx = ScrollBarSizingConstants.DefaultScrollBarThickness.roundToPx()
        if (contentLayoutInfo.generalVerticalScrollbarSize.width > 0.0f) {
            verticalScrollBarPlaceable = measurables[if (hasPanel) 4 else 2].measure(
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
            // TODO - support RTL
            canvasPlaceable.placeRelative(0, 0)

            // Offset everything else by 1,1 for border insets
            var yPosition = 1
            if (panelPlaceable != null) {
                panelPlaceable.placeRelative(1, 1)
                yPosition += panelPlaceable.height
                panelSeparatorPlaceable!!.placeRelative(1, yPosition)
                yPosition += panelSeparatorPlaceable.height
            }

            generalContentPlaceable.placeRelative(x = 1, y = yPosition)
            verticalScrollBarPlaceable?.placeRelative(
                x = 1 + generalContentPlaceable.width + scrollBarMarginPx,
                y = yPosition + scrollBarMarginPx
            )
        }
    }
}
