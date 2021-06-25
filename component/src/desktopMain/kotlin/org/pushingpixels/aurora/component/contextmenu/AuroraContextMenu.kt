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
import androidx.compose.ui.unit.IntOffset
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.CommandButtonPopupContent
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.component.model.CommandPopupMenuPresentationModel
import org.pushingpixels.aurora.component.utils.AuroraSize
import java.awt.Window
import java.awt.event.MouseEvent

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
    val auroraSize = AuroraSize(0, 0)

    val parentComposition = rememberCompositionContext()
    val contentModelState = rememberUpdatedState(contentModel)
    val enabledState = rememberUpdatedState(enabled)

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

                // TODO - hopefully temporary. Mark the popup window as fully transparent
                //  so that when it is globally positioned, we can size it to the actual
                //  content and make it fully opaque
                popupContentWindow.opacity = 0.0f

                val currentWindow = AppManager.focusedWindow!!.window
                val locationOnScreen = currentWindow.locationOnScreen

                // anchor the popup window to the point that was clicked
                // TODO - figure out the sizing (see above)
                val initialWidth = 1000
                val initialHeight = 1000
                val initialWindowAnchor = IntOffset(
                    x = locationOnScreen.x + lastEvent!!.x,
                    y = locationOnScreen.y + lastEvent!!.y
                )
                popupContentWindow.setBounds(
                    locationOnScreen.x + lastEvent!!.x,
                    locationOnScreen.y + lastEvent!!.y,
                    initialWidth,
                    initialHeight
                )

                popupContentWindow.setContent(
                    parentComposition = parentComposition
                ) {
                    CommandButtonPopupContent(
                        popupContentWindow = popupContentWindow,
                        initialAnchor = initialWindowAnchor,
                        anchorSize = auroraSize,
                        menuContentModel = contentModelState,
                        menuPresentationModel = presentationModel,
                        popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                        toDismissPopupsOnActivation = true,
                        overlays = overlays
                    )
                }

                popupContentWindow.invalidate()
                popupContentWindow.validate()
                popupContentWindow.isVisible = true
                popupContentWindow.pack()

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
