/*
 * Copyright 2019 FormDev Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.component.utils

import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.geom.Rectangle2D
import javax.swing.JComponent
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.hypot

// Taken from https://github.com/JFormDesigner/FlatLaf/blob/main/flatlaf-core/src/main/java/com/formdev/flatlaf/util/HiDPIUtils.java
fun paintAtScale1x(g: Graphics2D, c: JComponent, painterScale1X: PainterScale1X) {
    paintAtScale1x(g, 0, 0, c.getWidth(), c.getHeight(), painterScale1X)
}

/**
 * Paint at system scale factor 1x to avoid rounding issues at 125%, 150% and 175% scaling.
 *
 *
 * Scales the given Graphics2D down to 100% and invokes the
 * given painter passing scaled x, y, width and height.
 *
 *
 * Uses the same scaling calculation as the JRE uses.
 */
fun paintAtScale1x(g: Graphics2D, x: Int, y: Int, width: Int, height: Int, painterScale1X: PainterScale1X) {
    // save original transform
    val t = g.transform

    // get scale X/Y and shear X/Y
    var scaleX = t.scaleX
    var scaleY = t.scaleY
    val shearX = t.shearX
    val shearY = t.shearY

    // check whether rotated
    // (also check for negative scale X/Y because shear X/Y are zero for 180 degrees rotation)
    val rotated = (shearX != 0.0 || shearY != 0.0 || scaleX <= 0 || scaleY <= 0)
    if (rotated) {
        // resulting scale X/Y values are always positive
        scaleX = hypot(scaleX, shearX)
        scaleY = hypot(scaleY, shearY)
    } else {
        // make scale X/Y positive
        scaleX = abs(scaleX)
        scaleY = abs(scaleY)
    }

    // check whether scaled
    if (scaleX == 1.0 && scaleY == 1.0) {
        painterScale1X.paint(g, x, y, width, height, 1.0)
        return
    }

    // scale rectangle
    val scaledRect = scale(scaleX, scaleY, t, x, y, width, height)

    try {
        // unscale to factor 1.0, keep rotation and move origin (to whole numbers)
        val t1x: AffineTransform?
        if (rotated) {
            t1x = AffineTransform(
                t.scaleX, t.shearY, t.shearX, t.scaleY,
                floor(scaledRect.x), floor(scaledRect.y)
            )
            t1x.scale(1.0 / scaleX, 1.0 / scaleY)
        } else t1x = AffineTransform(1.0, 0.0, 0.0, 1.0, floor(scaledRect.x), floor(scaledRect.y))
        g.transform = t1x

        val swidth = scaledRect.width.toInt()
        val sheight = scaledRect.height.toInt()

        // paint
        painterScale1X.paint(g, 0, 0, swidth, sheight, scaleX)
    } finally {
        // restore original transform
        g.transform = t
    }
}

/**
 * Scales a rectangle in the same way as the JRE does in
 * sun.java2d.pipe.PixelToParallelogramConverter.fillRectangle(),
 * which is used by Graphics.fillRect().
 */
private fun scale(
    scaleX: Double,
    scaleY: Double,
    t: AffineTransform,
    x: Int,
    y: Int,
    width: Int,
    height: Int
): Rectangle2D.Double {
    val px = (x * scaleX) + t.translateX
    val py = (y * scaleY) + t.translateY

    val newX = normalize(px)
    val newY = normalize(py)
    val newWidth = normalize(px + (width * scaleX)) - newX
    val newHeight = normalize(py + (height * scaleY)) - newY

    return Rectangle2D.Double(newX, newY, newWidth, newHeight)
}

private fun normalize(value: Double): Double {
    return floor(value + 0.25) + 0.25
}

fun interface PainterScale1X {
    fun paint(graphics1X: Graphics2D, x: Int, y: Int, scaledWidth: Int, scaledHeight: Int, scaleFactor: Double)
}
