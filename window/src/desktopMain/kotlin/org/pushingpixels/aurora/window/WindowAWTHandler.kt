/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.window

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Density
import java.awt.*
import java.awt.event.AWTEventListener
import java.awt.event.MouseEvent
import java.security.AccessController
import java.security.PrivilegedActionException
import java.security.PrivilegedExceptionAction
import javax.swing.JRootPane
import javax.swing.SwingUtilities

/**
 * Maps from positions to cursor type. Refer to calculateCorner and calculatePosition for
 * details of this.
 */
private val cursorMapping = intArrayOf(
    Cursor.NW_RESIZE_CURSOR,
    Cursor.NW_RESIZE_CURSOR, Cursor.N_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR,
    Cursor.NE_RESIZE_CURSOR, Cursor.NW_RESIZE_CURSOR, 0, 0, 0,
    Cursor.NE_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR, 0, 0, 0,
    Cursor.E_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, 0, 0, 0,
    Cursor.SE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR,
    Cursor.S_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR
)

private enum class CursorState {
    EXITED, ENTERED, NIL
}

/**
 * MouseInputHandler is responsible for handling resize/moving of the Window. It sets the cursor
 * directly on the Window when then mouse moves over a hot spot.
 */
internal class AWTInputHandler(
    val density: Density,
    val window: Window,
    val rootPane: JRootPane,
    val lastCursor: MutableState<Cursor?>,
    val titlePaneBounds: MutableState<Rect>
) : AWTEventListener {
    /**
     * Set to true if the drag operation is moving the window.
     */
    private var isMovingWindow = false
    private var isMousePressed = false

    /**
     * Used to determine the corner the resize is occuring from.
     */
    private var dragCursor = 0

    /**
     * X location the mouse went down on for a drag operation.
     */
    private var dragOffsetX = 0

    /**
     * Y location the mouse went down on for a drag operation.
     */
    private var dragOffsetY = 0

    /**
     * Width of the window when the drag started.
     */
    private var dragWidth = 0

    /**
     * Height of the window when the drag started.
     */
    private var dragHeight = 0

    /**
     * PrivilegedExceptionAction needed by mouseDragged method to obtain new location of window
     * on screen during the drag.
     */
    private val getLocationAction: PrivilegedExceptionAction<Point> =
        PrivilegedExceptionAction { MouseInfo.getPointerInfo().location }

    override fun eventDispatched(event: AWTEvent?) {
        if (event is MouseEvent) {
            when (event.id) {
                MouseEvent.MOUSE_ENTERED -> mouseEntered(event)
                MouseEvent.MOUSE_EXITED -> mouseExited(event)
                MouseEvent.MOUSE_CLICKED -> mouseClicked(event)
                MouseEvent.MOUSE_PRESSED -> mousePressed(event)
                MouseEvent.MOUSE_RELEASED -> mouseReleased(event)
                MouseEvent.MOUSE_DRAGGED -> mouseDragged(event)
                MouseEvent.MOUSE_MOVED -> mouseMoved(event)
            }
        }
    }
    
    fun getEventWindow(ev: MouseEvent) : Window {
        val source = ev.source
        if (source is Window) {
            return source
        }
        return SwingUtilities.getWindowAncestor(source as Component)
    }

    private fun mousePressed(ev: MouseEvent) {
        //println("mousePressed!")
        isMousePressed = true
        val dragWindowOffset: Point = ev.point
        val w: Window = getEventWindow(ev)
        if (w != null) {
            w.toFront()
        }
        var f: Frame? = null
        var d: Dialog? = null
        if (w is Frame) {
            f = w as Frame
        } else if (w is Dialog) {
            d = w as Dialog
        }
        val frameState = f?.extendedState ?: 0
        if (isMouseEventInExtendedTitlePane(ev, titlePaneBounds.value)) {
            val borderDragThicknessPx = WindowSizingConstants.BorderDragThickness.value *
                    density.density
            if ((((f != null) and ((frameState and Frame.MAXIMIZED_BOTH) == 0)) or (d != null))
                && (dragWindowOffset.y >= borderDragThicknessPx)
                && (dragWindowOffset.x >= borderDragThicknessPx)
                && (dragWindowOffset.x < w.width - borderDragThicknessPx)
            ) {
                isMovingWindow = true
                dragOffsetX = dragWindowOffset.x
                dragOffsetY = dragWindowOffset.y
            }
        } else if ((f != null && f.isResizable
                    && ((frameState and Frame.MAXIMIZED_BOTH) == 0))
            || d != null && d.isResizable
        ) {
            dragOffsetX = dragWindowOffset.x
            dragOffsetY = dragWindowOffset.y
            dragWidth = w.width
            dragHeight = w.height
            dragCursor = getCursor(calculateCorner(w, dragWindowOffset.x, dragWindowOffset.y))
        }
    }

    private fun mouseReleased(ev: MouseEvent?) {
        //println("mouseReleased!")
        if ((dragCursor != 0) && (window != null)
            && !window.isValid
        ) {
            // Some Window systems validate as you resize, others won't,
            // thus the check for validity before repainting.
            window.validate()
            rootPane.repaint()
        }
        isMousePressed = false
        isMovingWindow = false
        dragCursor = 0
    }

    private fun mouseMoved(ev: MouseEvent) {
        //println("mouseMoved ${ev.x}x${ev.y} / ${ev.source}! ${rootPane.windowDecorationStyle}")
//        if (rootPane.windowDecorationStyle == JRootPane.NONE) {
//            return
//        }
        val w: Window = getEventWindow(ev)
        var f: Frame? = null
        var d: Dialog? = null
        if (w is Frame) {
            f = w as Frame
        } else if (w is Dialog) {
            d = w as Dialog
        }

        // Update the cursor
        val cursor = getCursor(calculateCorner(w, ev.x, ev.y))
        val isFrameResizable = (f != null
                && f.isResizable && ((f.extendedState and Frame.MAXIMIZED_BOTH) == 0))
        val isDialogResizable = d != null && d.isResizable
        if (cursor != 0 && (isFrameResizable || isDialogResizable)) {
            w.cursor = Cursor.getPredefinedCursor(cursor)
        } else {
            w.cursor = lastCursor.value
            lastCursor.value = null
        }
    }

    /**
     * Adjusts the bounds.
     *
     * @param bounds
     * Original bounds.
     * @param min
     * Minimum dimension.
     * @param deltaX
     * Delta X.
     * @param deltaY
     * Delta Y.
     * @param deltaWidth
     * Delta width.
     * @param deltaHeight
     * Delta height.
     */
    private fun adjust(
        bounds: Rectangle, min: Dimension?, deltaX: Int, deltaY: Int, deltaWidth: Int,
        deltaHeight: Int
    ) {
        bounds.x += deltaX
        bounds.y += deltaY
        bounds.width += deltaWidth
        bounds.height += deltaHeight
        if (min != null) {
            if (bounds.width < min.width) {
                val correction: Int = min.width - bounds.width
                if (deltaX != 0) {
                    bounds.x -= correction
                }
                bounds.width = min.width
            }
            if (bounds.height < min.height) {
                val correction: Int = min.height - bounds.height
                if (deltaY != 0) {
                    bounds.y -= correction
                }
                bounds.height = min.height
            }
        }
    }

    private fun mouseDragged(ev: MouseEvent) {
        //println("mouseDragged!")
        val w: Window = getEventWindow(ev)
        val pt: Point = ev.point
        if (isMovingWindow) {
            val windowPt: Point
            try {
                windowPt = AccessController.doPrivileged(getLocationAction)
                windowPt.x = windowPt.x - dragOffsetX
                windowPt.y = windowPt.y - dragOffsetY
                w.location = windowPt
            } catch (e: PrivilegedActionException) {
            }
        } else if (dragCursor != 0) {
            val r: Rectangle = w.bounds
            val startBounds = Rectangle(r)
            val min: Dimension = w.minimumSize
            when (dragCursor) {
                Cursor.E_RESIZE_CURSOR -> adjust(
                    r, min, 0, 0,
                    pt.x + (dragWidth - dragOffsetX) - r.width, 0
                )
                Cursor.S_RESIZE_CURSOR -> adjust(
                    r, min, 0, 0, 0,
                    pt.y + (dragHeight - dragOffsetY) - r.height
                )
                Cursor.N_RESIZE_CURSOR -> adjust(
                    r, min, 0, pt.y - dragOffsetY, 0,
                    -(pt.y - dragOffsetY)
                )
                Cursor.W_RESIZE_CURSOR -> adjust(
                    r, min, pt.x - dragOffsetX, 0, -(pt.x - dragOffsetX),
                    0
                )
                Cursor.NE_RESIZE_CURSOR -> adjust(
                    r, min, 0, pt.y - dragOffsetY,
                    pt.x + (dragWidth - dragOffsetX) - r.width,
                    -(pt.y - dragOffsetY)
                )
                Cursor.SE_RESIZE_CURSOR -> adjust(
                    r, min, 0, 0,
                    pt.x + (dragWidth - dragOffsetX) - r.width,
                    pt.y + (dragHeight - dragOffsetY) - r.height
                )
                Cursor.NW_RESIZE_CURSOR -> adjust(
                    r, min, pt.x - dragOffsetX, pt.y - dragOffsetY,
                    -(pt.x - dragOffsetX), -(pt.y - dragOffsetY)
                )
                Cursor.SW_RESIZE_CURSOR -> adjust(
                    r, min, pt.x - dragOffsetX, 0, -(pt.x - dragOffsetX),
                    pt.y + (dragHeight - dragOffsetY) - r.height
                )
                else -> {
                }
            }
            if (r != startBounds) {
                w.bounds = r
                // Defer repaint/validate on mouseReleased unless dynamic
                // layout is active.
                if (Toolkit.getDefaultToolkit().isDynamicLayoutActive) {
                    w.validate()
                    rootPane.repaint()
                }
            }
        }
    }

    private var cursorState: CursorState = CursorState.NIL
    private fun mouseEntered(ev: MouseEvent) {
        //println("mouseEntered!")
        if (isMousePressed) {
            return
        }
        val w: Window = getEventWindow(ev)
        if ((lastCursor.value == null)
            && (cursorState !== CursorState.ENTERED)
        ) {
            // fix for defect 107
            lastCursor.value = w.cursor
        }
        cursorState = CursorState.ENTERED
        mouseMoved(ev)
    }

    private fun mouseExited(ev: MouseEvent) {
        //println("mouseExited!")
        if (isMousePressed) {
            return
        }
        val w: Window = getEventWindow(ev)
        w.cursor = lastCursor.value
        lastCursor.value = null
        cursorState = CursorState.EXITED
    }

    private fun mouseClicked(ev: MouseEvent) {
        val w: Window = getEventWindow(ev)
        var f: Frame? = null
        f = if (w is Frame) {
            w as Frame
        } else {
            return
        }

        val state: Int = f.extendedState
        if (isMouseEventInExtendedTitlePane(ev, titlePaneBounds.value)) {
            if ((ev.clickCount % 2 == 0) && (ev.button == MouseEvent.BUTTON1)) {
                if (f.isResizable) {
                    if ((state and Frame.MAXIMIZED_BOTH) != 0) {
                        f.extendedState = state and Frame.MAXIMIZED_BOTH.inv()
                    } else {
                        f.extendedState = state or Frame.MAXIMIZED_BOTH
                    }
                }
            }
        }
    }

    /**
     * Returns the corner that contains the point `x`, `y`, or -1 if the
     * position doesn't match a corner.
     *
     * @param w
     * Window.
     * @param x
     * X coordinate.
     * @param y
     * Y coordinate.
     * @return Corner that contains the specified point.
     */
    private fun calculateCorner(w: Window, x: Int, y: Int): Int {
        val insets: Insets = w.insets
        val xPosition = calculatePosition(
            x - insets.left,
            w.width - insets.left - insets.right
        )
        val yPosition = calculatePosition(
            y - insets.top,
            w.height - insets.top - insets.bottom
        )
        return if (xPosition == -1 || yPosition == -1) {
            -1
        } else yPosition * 5 + xPosition
    }

    /**
     * Returns the Cursor to render for the specified corner. This returns 0 if the corner
     * doesn't map to a valid Cursor
     *
     * @param corner
     * Corner
     * @return Cursor to render for the specified corner.
     */
    private fun getCursor(corner: Int): Int {
        return if (corner == -1) {
            0
        } else cursorMapping.get(corner)
    }

    /**
     * Returns an integer indicating the position of `spot` in `width`.
     * The return value will be: 0 if < BORDER_DRAG_THICKNESS 1 if < CORNER_DRAG_WIDTH 2 if >=
     * CORNER_DRAG_WIDTH && < width - BORDER_DRAG_THICKNESS 3 if >= width - CORNER_DRAG_WIDTH 4
     * if >= width - BORDER_DRAG_THICKNESS 5 otherwise
     *
     * @param spot
     * Spot.
     * @param width
     * Width.
     * @return The position of spot in width.
     */
    private fun calculatePosition(spot: Int, width: Int): Int {
        val cornerDragWidthPx = WindowSizingConstants.CornerDragWidth.value *
                density.density
        val borderDragThicknessPx = WindowSizingConstants.BorderDragThickness.value *
                density.density
        if (spot < borderDragThicknessPx) {
            return 0
        }
        if (spot < cornerDragWidthPx) {
            return 1
        }
        if (spot >= width - borderDragThicknessPx) {
            return 4
        }
        return if (spot >= width - cornerDragWidthPx) {
            3
        } else 2
    }
}

private fun isMouseEventInExtendedTitlePane(ev: MouseEvent, titlePaneBounds: Rect): Boolean {
    val point = ev.point
    val source = ev.source as Component
    val pointInRootPaneCoords = SwingUtilities.convertPoint(
        source,
        point,
        SwingUtilities.getRootPane(source)
    )
    // TODO - account for title pane offset in root pane
    return titlePaneBounds.contains(
        Offset(pointInRootPaneCoords.x.toFloat(), pointInRootPaneCoords.y.toFloat())
    )
}