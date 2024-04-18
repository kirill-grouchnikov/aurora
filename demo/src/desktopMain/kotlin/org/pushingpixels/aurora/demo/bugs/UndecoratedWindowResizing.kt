package org.pushingpixels.aurora.demo.bugs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.*
import java.awt.event.AWTEventListener
import java.awt.event.MouseEvent
import java.security.PrivilegedExceptionAction
import javax.swing.JRootPane
import javax.swing.SwingUtilities

object WindowSizingConstants {
    // The amount of space that the cursor is changed on.
    val CornerDragWidth = 16.dp

    // Region from edges that dragging is active from.
    val BorderDragThickness = 5.dp
}

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
    Exited, Entered, Nil
}

/**
 * MouseInputHandler is responsible for handling resize/moving of the Window. It sets the cursor
 * directly on the Window when then mouse moves over a hot spot.
 */
internal class AWTInputHandler(
    val density: Density,
    val window: Window,
    val rootPane: JRootPane,
    val lastCursor: MutableState<Cursor?>
) : AWTEventListener {
    /**
     * Set to true if the drag operation is moving the window.
     */
    private var isMovingWindow = false
    private var isMousePressed = false

    /**
     * Used to determine the corner the resize is occurring from.
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
            val window = getEventWindow(event)
            if (window != this.window) {
                // Only handle events originating in the associated window
                return
            }

            val pointInWindow = SwingUtilities.convertPoint(
                event.source as Component?, event.point, window
            )

            when (event.id) {
                MouseEvent.MOUSE_ENTERED -> mouseEntered(pointInWindow, window)
                MouseEvent.MOUSE_EXITED -> mouseExited(pointInWindow, window)
                MouseEvent.MOUSE_PRESSED -> mousePressed(pointInWindow, window)
                MouseEvent.MOUSE_RELEASED -> mouseReleased(pointInWindow, window)
                MouseEvent.MOUSE_DRAGGED -> mouseDragged(pointInWindow, window, event)
                MouseEvent.MOUSE_MOVED -> mouseMoved(pointInWindow, window)
            }
        }
    }

    private fun getEventWindow(ev: MouseEvent): Window? {
        val source = ev.source
        return when (source) {
            is Window -> source
            is Component -> SwingUtilities.getWindowAncestor(source)
            else -> null
        }
    }

    private fun mousePressed(pointInWindow: Point, w: Window) {
        //println("mousePressed!")
        isMousePressed = true
        val dragWindowOffset: Point = pointInWindow
        w.toFront()
        var f: Frame? = null
        var d: Dialog? = null
        if (w is Frame) {
            f = w
        } else if (w is Dialog) {
            d = w
        }
        val frameState = f?.extendedState ?: 0
        if ((f != null && f.isResizable
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

    @Suppress("UNUSED_PARAMETER")
    private fun mouseReleased(pointInWindow: Point, w: Window) {
        //println("mouseReleased!")
        @Suppress("SENSELESS_COMPARISON")
        if ((dragCursor != 0) && (w != null) && !w.isValid) {
            // Some Window systems validate as you resize, others won't,
            // thus the check for validity before repainting.
            w.validate()
            rootPane.repaint()
        }
        isMousePressed = false
        isMovingWindow = false
        dragCursor = 0
    }

    private fun mouseMoved(pointInWindow: Point, w: Window) {
        //println("mouseMoved ${ev.x}x${ev.y} / ${ev.source}! ${rootPane.windowDecorationStyle}")
//        if (rootPane.windowDecorationStyle == JRootPane.NONE) {
//            return
//        }
        var f: Frame? = null
        var d: Dialog? = null

        if (w is Frame) {
            f = w
        } else if (w is Dialog) {
            d = w
        }
        // Update the cursor
        val cursor = getCursor(calculateCorner(w, pointInWindow.x, pointInWindow.y))
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

    private fun mouseDragged(pointInWindow: Point, w: Window, ev: MouseEvent) {
        //println("mouseDragged!")
        val pt: Point = pointInWindow
        if (isMovingWindow) {
            val windowPt: Point = ev.locationOnScreen
            windowPt.x -= dragOffsetX
            windowPt.y -= dragOffsetY
            w.location = windowPt
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

    private var cursorState: CursorState = CursorState.Nil
    private fun mouseEntered(pointInWindow: Point, w: Window) {
        //println("mouseEntered!")
        if (isMousePressed) {
            return
        }
        if ((lastCursor.value == null)
            && (cursorState !== CursorState.Entered)
        ) {
            // fix for defect 107
            lastCursor.value = w.cursor
        }
        cursorState = CursorState.Entered
        mouseMoved(pointInWindow, w)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun mouseExited(pointInWindow: Point, w: Window) {
        //println("mouseExited!")
        if (isMousePressed) {
            return
        }
        w.cursor = lastCursor.value
        lastCursor.value = null
        cursorState = CursorState.Exited
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
        } else cursorMapping[corner]
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

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )
    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        resizable = true,
        state = state
    ) {
        val density = LocalDensity.current

        Column(Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().height(24.dp).background(Color.Blue))
            Box(modifier = Modifier.fillMaxWidth().height(32.dp).background(Color.Green))
            Box(modifier = Modifier.fillMaxWidth().weight(1.0f).background(Color.Red))
        }

        LaunchedEffect(Unit) {
            val lastCursor = mutableStateOf(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            val awtInputHandler = AWTInputHandler(
                density = density,
                window = window,
                rootPane = window.rootPane,
                lastCursor = lastCursor
            )

            Toolkit.getDefaultToolkit().addAWTEventListener(
                awtInputHandler,
                AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK
            )
        }
    }
}