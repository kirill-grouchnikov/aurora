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

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposeWindow
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.State
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.PopupPlacementStrategy
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.CommandButtonPopupContent
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.getPreferredCommandButtonPanelSize
import org.pushingpixels.aurora.component.model.*
import java.awt.Rectangle
import java.awt.Window
import kotlin.math.max

internal data class PopupContentLayoutInfo(
    val fullSize: Size,
    val buttonPanelSize: Size,
    val separatorSize: Size,
    val generalContentSize: Size,
    val generalVerticalScrollbarSize: Size
)

internal fun displayPopupContent(
    parentWindow: ComposeWindow? = null,
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

    val currentWindow = parentWindow ?: AppManager.focusedWindow!!.window
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
            if (secondaryCommand.iconFactory != null) {
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
        CommandButtonPopupContent(
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