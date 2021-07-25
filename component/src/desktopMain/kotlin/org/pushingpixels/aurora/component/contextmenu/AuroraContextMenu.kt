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
package org.pushingpixels.aurora.component.contextmenu

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposeWindow
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.LocalTextStyle
import org.pushingpixels.aurora.PopupPlacementStrategy
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.CommandButtonPopupContent
import org.pushingpixels.aurora.component.model.*
import java.awt.Rectangle
import java.awt.Window
import java.awt.event.MouseEvent
import kotlin.math.max
import kotlin.math.roundToInt

private suspend fun AwaitPointerEventScope.awaitEventFirstDown(): PointerEvent {
    var event: PointerEvent
    do {
        event = awaitPointerEvent()
    } while (
        !event.changes.all { it.changedToDown() }
    )
    return event
}

@Composable
fun Modifier.auroraContextMenu(
    enabled: Boolean = true,
    contentModel: CommandMenuContentModel,
    presentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay> = mapOf()
): Modifier {
    var lastEvent by remember { mutableStateOf<MouseEvent?>(null) }

    val parentComposition = rememberCompositionContext()
    val contentModelState = rememberUpdatedState(contentModel)
    val enabledState = rememberUpdatedState(enabled)

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val mergedTextStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current

    // Command presentation for menu content, taking some of the values from
    // the popup menu presentation model configured on the top-level presentation model
    val popupButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.menuPresentationState,
        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        horizontalAlignment = HorizontalAlignment.Leading,
        isMenu = true
    )
    val popupItemLayoutManager =
        presentationModel.menuPresentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = mergedTextStyle,
            resourceLoader = resourceLoader
        )

    return this.then(Modifier.pointerInput(Unit) {
        forEachGesture {
            // TODO - this only detects PRESSED events, so it doesn't work on platforms
            //  such as Windows that show popups on RELEASED. See
            //  https://github.com/JetBrains/compose-jb/issues/812
            awaitPointerEventScope {
                lastEvent = awaitEventFirstDown().also { event ->
                    event.changes.forEach { it.consumeDownChange() }
                }.mouseEvent
            }

            if (enabledState.value && (lastEvent?.isPopupTrigger == true)) {
                val popupContentWindow = ComposeWindow()
                popupContentWindow.focusableWindowState = false
                popupContentWindow.type = Window.Type.POPUP
                popupContentWindow.isAlwaysOnTop = true
                popupContentWindow.isUndecorated = true

                val currentWindow = AppManager.focusedWindow!!.window
                val locationOnScreen = currentWindow.locationOnScreen

                var atLeastOnePopupButtonHasIcon = false
                for (commandGroup in contentModel.groups) {
                    for (secondaryCommand in commandGroup.commands) {
                        if (secondaryCommand.iconFactory != null) {
                            atLeastOnePopupButtonHasIcon = true
                        }
                        if (secondaryCommand.isActionToggle) {
                            atLeastOnePopupButtonHasIcon = true
                        }
                    }
                }
                val secondaryPresentationModelOverlay =
                    CommandButtonPresentationModel.Overlay(
                        forceAllocateSpaceForIcon = atLeastOnePopupButtonHasIcon,
                    )
                val popupButtonPresentationModelWithOverlay =
                    popupButtonPresentationModel.overlayWith(
                        secondaryPresentationModelOverlay
                    )

                var popupColumnWidth = 0.0f
                var popupColumnHeight = 0.0f
                for ((commandGroupIndex, commandGroup) in contentModel.groups.withIndex()) {
                    for (secondaryCommand in commandGroup.commands) {
                        val preferredSize = popupItemLayoutManager.getPreferredSize(
                            command = secondaryCommand,
                            presentationModel = popupButtonPresentationModelWithOverlay,
                            preLayoutInfo = popupItemLayoutManager.getPreLayoutInfo(
                                command = secondaryCommand,
                                presentationModel = popupButtonPresentationModelWithOverlay
                            )
                        )
                        popupColumnWidth = max(popupColumnWidth, preferredSize.width)
                        popupColumnHeight += preferredSize.height
                    }
                    // Account for horizontal separator between secondary command groups
                    if (commandGroupIndex < (contentModel.groups.size - 1)) {
                        popupColumnHeight += SeparatorSizingConstants.Thickness.value * density.density
                    }
                }

                val fullPopupWidth = ((popupColumnWidth.roundToInt() + 2) / density.density).toInt()
                val fullPopupHeight =
                    ((popupColumnHeight.roundToInt() + 2) / density.density).toInt()

                // anchor the popup window to the mouse event
                val initialAnchor = IntOffset(
                    x = locationOnScreen.x + lastEvent!!.x,
                    y = locationOnScreen.y + lastEvent!!.y
                )

                // TODO - support RTL for startward and endward
                val popupRect = when (presentationModel.popupPlacementStrategy) {
                    PopupPlacementStrategy.Downward -> Rectangle(
                        initialAnchor.x,
                        initialAnchor.y,
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
                        initialAnchor.x,
                        initialAnchor.y,
                        fullPopupWidth,
                        fullPopupHeight
                    )
                    PopupPlacementStrategy.CenteredVertically -> Rectangle(
                        initialAnchor.x,
                        initialAnchor.y - fullPopupHeight / 2,
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

                popupContentWindow.setContent(
                    parentComposition = parentComposition
                ) {
                    CommandButtonPopupContent(
                        popupContentWindow = popupContentWindow,
                        menuContentModel = contentModelState,
                        menuPresentationModel = presentationModel,
                        toDismissPopupsOnActivation = true,
                        overlays = overlays
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
        }
    })
}
