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
package org.pushingpixels.aurora.common

import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import java.awt.*
import javax.swing.*
import javax.swing.border.EmptyBorder

/**
 * Major moving parts in the current implementation of Aurora popups.
 *
 * 1. Every popup is hosted in its own heavyweight window so that popups (including
 *    cascading popups where applicable) are not constrained to the boundaries of the
 *    original content window.
 * 2. Popup content is hosted in a custom sub-class of Swing's JPopupMenu. This is
 *    needed to use the built-in (but all private to JDK) implementation of window grabbing
 *    and ungrabbing that allows Aurora to dismiss its popup(s) when the user either
 *    moves a system-decorated Aurora window by its title pane or switches to another
 *    window.
 * 3. The implementation is using a hardcoded constant from sun.awt.SunToolkit.GRAB_EVENT_MASK
 *    alongside comparing the classname of the global AWT event to "UngrabEvent". This is
 *    not ideal as it can break on future JDKs or JDKs that deviate from the baseline
 *    implementation.
 * 4. Another alternative is to continue using JPopupMenu and register a property change
 *    listener on it that gets fired with a synthetic change on property named
 *    "JPopupMenu.firePopupMenuCanceled". This can also break on future JDKs and JDKs that
 *    deviate from the baseline implementation. In addition, it is harder to coordinate
 *    the logic around popup dismissals inside the popup menu object itself.
 * 5. Popup content is hosted in a ComposePanel that uses Compose's currentCompositionLocalContext
 *    to pass all the composition locals from the main window into the popup window.
 * 6. For historical reasons, window-level Swing and AWT APIs operate in device-independent
 *    units that logically correspond to Compose's dp unit. When popup content is configured
 *    and pre-measured to determine how big the Swing / AWT popup window needs to be, those
 *    measurements are converted from pixels to these device-independent units based on the
 *    screen density.
 * 7. There's an issue with first display of content in a ComposePanel embedded in a JPopupMenu
 *    where it starts with the default light grey fill for the first frame. This is particularly
 *    visible in dark Aurora skins. To work around this issue, every Aurora popup starts at
 *    AWT opacity 0. The calling code is responsible for setting the popup window opacity to 1
 *    after that first display, which is at the present moment done using a  CoroutineScope.launch
 *    block.
 */

// IMPORTANT - this needs to be a subclass of JPopupMenu to get the ungrab events
// for the AWT listeners registered at Aurora window level.
class AuroraSwingPopupMenu(val toDismissPopupsOnActivation: Boolean) : JPopupMenu() {
    init {
        layout = BorderLayout()
        border = EmptyBorder(1, 1, 1, 1)
    }

    override fun menuSelectionChanged(isIncluded: Boolean) {
        // Do nothing, overriding the logic in JPopupMenu that hides a popup that does not
        // originate in Swing's JMenu. We do our own implementation of possibly cascading
        // popups.
    }
}

private class AuroraPopup : Popup() {
    private var hostWindow: JWindow? = null

    @Suppress("DEPRECATION")
    override fun show() {
        this.hostWindow?.show()
    }

    @Suppress("DEPRECATION")
    override fun hide() {
        this.hostWindow?.hide()
        this.hostWindow?.contentPane?.removeAll()
        this.hostWindow?.dispose()
    }

    fun reset(owner: Component, contents: Component, ownerX: Int, ownerY: Int) {
        if (this.hostWindow == null) {
            this.hostWindow = createHostWindow(owner)
        }
        if (this.hostWindow == null) {
            return
        }
        this.hostWindow!!.opacity = 0.0f
        // Sets the proper location, and resets internal state of the window
        this.hostWindow!!.setBounds(ownerX, ownerY, 1, 1)
        this.hostWindow!!.background = contents.background
        this.hostWindow!!.contentPane.add(contents, BorderLayout.CENTER)
        this.hostWindow!!.invalidate()
        this.hostWindow!!.validate()
        if (this.hostWindow!!.isVisible) {
            // Do not call pack() if window is not visible to
            // avoid early native peer creation
            this.hostWindow?.pack()
        }
    }

    private fun getParentWindow(owner: Component): Window {
        return if (owner is Window) {
            owner
        } else {
            SwingUtilities.getWindowAncestor(owner)
        }
    }

    fun createHostWindow(owner: Component): JWindow? {
        return if (GraphicsEnvironment.isHeadless()) {
            // Don't support popups in headless mode
            null
        } else AuroraHeavyWeightWindow(getParentWindow(owner))
    }
}


private class AuroraHeavyWeightWindow(parent: Window) : JWindow(parent) {
    init {
        focusableWindowState = false
        type = Type.POPUP
        isAlwaysOnTop = true
        background = parent.background
    }

    override fun update(g: Graphics) {
        paint(g)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun show() {
        pack()
        if (this.width > 0 && this.height > 0) {
            super.show()
        }
    }
}

private class AuroraPopupFactory : PopupFactory() {
    override fun getPopup(owner: Component, contents: Component, x: Int, y: Int): Popup {
        val popup = AuroraPopup()
        popup.reset(owner, contents, x, y)
        return popup
    }
}

object AuroraPopupManager {
    class PopupKind {
        companion object {
            val Popup = PopupKind()
            val RichTooltip = PopupKind()
        }
    }

    private data class PopupInfo(
        val originatorPopup: Component,
        val popupTriggerAreaInOriginatorWindow: Rect,
        val popup: AuroraSwingPopupMenu,
        val popupContent: ComposePanel,
        val popupKind: PopupKind,
        val onActivatePopup: (() -> Unit)?,
        val onDeactivatePopup: (() -> Unit)?
    )

    private val shownPath = arrayListOf<PopupInfo>()

    fun showPopup(
        originator: Component,
        popupTriggerAreaInOriginatorWindow: Rect,
        popup: AuroraSwingPopupMenu,
        popupContent: ComposePanel,
        popupRectOnScreen: Rectangle,
        popupKind: PopupKind,
        onActivatePopup: (() -> Unit)? = null,
        onDeactivatePopup: (() -> Unit)? = null
    ): Window? {
        shownPath.add(
            PopupInfo(
                originator, popupTriggerAreaInOriginatorWindow,
                popup, popupContent, popupKind,
                onActivatePopup, onDeactivatePopup
            )
        )

        val invokerLocOnScreen = originator.locationOnScreen
        val currentScreenBounds = originator.graphicsConfiguration.bounds
        invokerLocOnScreen.translate(-currentScreenBounds.x, -currentScreenBounds.y)

        PopupFactory.setSharedInstance(AuroraPopupFactory())

        popupContent.invalidate()
        popupContent.revalidate()
        popup.show(
            originator, popupRectOnScreen.x - invokerLocOnScreen.x,
            popupRectOnScreen.y - invokerLocOnScreen.y
        )
        onActivatePopup?.invoke()
        return SwingUtilities.getWindowAncestor(popup)
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
        last.onDeactivatePopup?.invoke()

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
            last.onDeactivatePopup?.invoke()

            lastPopup.isVisible = false
        }
    }

    fun isShowingPopupFrom(
        originator: Component,
        pointInOriginator: Offset
    ): Boolean {
        val match = shownPath.reversed().find {
            (it.originatorPopup == originator) &&
                    (it.popupTriggerAreaInOriginatorWindow.contains(pointInOriginator))
        }
        return match != null
    }

    fun isShowingPopups(): Boolean {
        return shownPath.isNotEmpty()
    }

    fun dump() {
        println("Popups")
        for (link in shownPath) {
            println("\tOriginator ${link.originatorPopup.javaClass.simpleName}@${link.originatorPopup.hashCode()} [${link.popupKind.javaClass.simpleName}]")
            println("\t --- trigger area ${link.popupTriggerAreaInOriginatorWindow}")
        }
    }
}