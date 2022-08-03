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
package org.pushingpixels.aurora.common

import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Rectangle
import javax.swing.JPopupMenu
import javax.swing.SwingUtilities
import javax.swing.border.EmptyBorder

class AuroraSwingPopupMenu : JPopupMenu() {
    init {
        layout = BorderLayout()
        border = EmptyBorder(1, 1, 1, 1)
        addPropertyChangeListener {
            // BasicPopupMenuUI has a comment in cancelPopupMenu that instead of notifying
            // the menu's listener that the menu is about to be canceled, it sends the
            // following property change. Not ideal, but then this whole setup is like that.
            if ((it.propertyName == "JPopupMenu.firePopupMenuCanceled") && (it.newValue as Boolean)) {
                // Handle this as a signal to hide all the popups
                AuroraPopupManager.hidePopups(null)
            }
        }
    }

    override fun menuSelectionChanged(isIncluded: Boolean) {
        // Do nothing, overriding the logic in JPopupMenu that hides a popup that does not
        // originate in Swing's JMenu. We do our own implementation of possibly cascading
        // popups.
    }
}

object AuroraPopupManager {
    enum class PopupKind {
        POPUP, RICH_TOOLTIP
    }

    private data class PopupInfo(
        val originatorPopup: Component,
        val popupTriggerAreaInOriginatorWindow: Rect,
        val popup: AuroraSwingPopupMenu,
        val popupContent: ComposePanel,
        val popupKind: PopupKind
    )

    private val shownPath = arrayListOf<PopupInfo>()

    fun addPopup(
        originator: Component,
        popupTriggerAreaInOriginatorWindow: Rect,
        popup: AuroraSwingPopupMenu,
        popupContent: ComposePanel,
        popupRectOnScreen: Rectangle,
        popupKind: PopupKind
    ) {
        shownPath.add(
            PopupInfo(
                originator, popupTriggerAreaInOriginatorWindow,
                popup, popupContent, popupKind
            )
        )

        val invokerLocOnScreen = originator.locationOnScreen
        popupContent.invalidate()
        popupContent.revalidate()
        popup.show(
            originator, popupRectOnScreen.x - invokerLocOnScreen.x,
            popupRectOnScreen.y - invokerLocOnScreen.y
        )
    }

    fun hideLastPopup() {
        if (shownPath.size == 0) {
            return
        }
        val last: PopupInfo = shownPath.removeLast()
        val lastPopup = last.popup

        // Do not remove this block, this is needed for some reason (shrug) for proper
        // display of the next popup content
        val popupWindow = SwingUtilities.getWindowAncestor(last.popupContent)
        popupWindow.dispose()

        lastPopup.isVisible = false
    }

    fun hidePopups(originator: Component?, popupKind: PopupKind? = null) {
        while (shownPath.size > 0) {
            val currLastShown = shownPath[shownPath.size - 1]
            if (currLastShown.popup == originator) {
                // The current popup window we're looking at is the requested originator.
                // Stop unwinding and return.
                return
            }

            if ((popupKind != null) && (currLastShown.popupKind != popupKind)) {
                // The current popup window we're looking at does not match the requested
                // kind to be dismissed. Stop unwinding and return.
                return
            }

            val last = shownPath.removeLast()
            val lastPopup = last.popup

            // Do not remove this block, this is needed for some reason (shrug) for proper
            // display of the next popup content
            val popupWindow = SwingUtilities.getWindowAncestor(last.popupContent)
            popupWindow.dispose()

            lastPopup.isVisible = false
        }
    }

    fun isShowingPopupFrom(
        originator: Component,
        pointInOriginator: Offset
    ): Boolean {
        val match = shownPath.reversed().find {
            (it.popup == originator) &&
                    (it.popupTriggerAreaInOriginatorWindow.contains(pointInOriginator))
        }
        return match != null
    }
}