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

import androidx.compose.ui.awt.ComposeWindow

object AuroraPopupManager {
    enum class PopupKind {
        POPUP, RICH_TOOLTIP
    }

    private data class PopupInfo(
        val originator: ComposeWindow,
        val popupWindow: ComposeWindow,
        val popupKind: PopupKind
    )

    private val shownPath = arrayListOf<PopupInfo>()

    fun addPopup(originator: ComposeWindow, popupWindow: ComposeWindow, popupKind: PopupKind) {
        shownPath.add(PopupInfo(originator, popupWindow, popupKind))

        popupWindow.invalidate()
        popupWindow.validate()
        popupWindow.isVisible = true
        popupWindow.pack()
    }

    fun hideLastPopup() {
        if (shownPath.size == 0) {
            return
        }
        val last: PopupInfo = shownPath.removeLast()
        val lastPopupWindow = last.popupWindow
        if (lastPopupWindow.isDisplayable) {
            lastPopupWindow.isVisible = false
            lastPopupWindow.dispose()
        }
    }

    fun hidePopups(originator: ComposeWindow?, popupKind: PopupKind? = null) {
        // Start going over the popups in reverse order (from the most recently displayed
        // towards the very first one) and dismissing them one by one until we hit the
        // originator
        while (shownPath.size > 0) {
            val currLastShown = shownPath[shownPath.size - 1]
            if (currLastShown.popupWindow == originator) {
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
            val lastPopupWindow = last.popupWindow
            if (lastPopupWindow.isDisplayable) {
                lastPopupWindow.isVisible = false
                lastPopupWindow.dispose()
            }
            // Continue unwinding
        }
    }
}