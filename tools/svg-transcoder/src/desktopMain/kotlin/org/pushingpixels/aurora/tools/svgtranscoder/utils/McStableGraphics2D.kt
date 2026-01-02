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
package org.pushingpixels.aurora.tools.svgtranscoder.utils

import java.awt.*
import java.awt.font.FontRenderContext
import java.awt.font.GlyphVector
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.image.BufferedImageOp
import java.awt.image.ImageObserver
import java.awt.image.RenderedImage
import java.awt.image.renderable.RenderableImage
import java.text.AttributedCharacterIterator

internal open class McStableGraphics2D : Graphics2D() {
    override fun draw(s: Shape) {}
    override fun drawImage(img: Image, xform: AffineTransform, obs: ImageObserver): Boolean {
        return false
    }

    override fun drawImage(img: BufferedImage, op: BufferedImageOp, x: Int, y: Int) {}
    override fun drawRenderedImage(img: RenderedImage, xform: AffineTransform) {}
    override fun drawRenderableImage(img: RenderableImage, xform: AffineTransform) {}
    override fun drawString(str: String, x: Int, y: Int) {}
    override fun drawString(str: String, x: Float, y: Float) {}
    override fun drawString(iterator: AttributedCharacterIterator, x: Int, y: Int) {}
    override fun drawString(iterator: AttributedCharacterIterator, x: Float, y: Float) {}
    override fun drawGlyphVector(g: GlyphVector, x: Float, y: Float) {}
    override fun fill(s: Shape) {}
    override fun hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean {
        return false
    }

    override fun getDeviceConfiguration(): GraphicsConfiguration? {
        return null
    }

    override fun setComposite(comp: Composite) {}
    override fun setPaint(paint: Paint) {}
    override fun setStroke(s: Stroke) {}
    override fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any?) {}
    override fun getRenderingHint(hintKey: RenderingHints.Key): Any? {
        return null
    }

    override fun setRenderingHints(hints: Map<*, *>?) {}
    override fun addRenderingHints(hints: Map<*, *>?) {}
    override fun getRenderingHints(): RenderingHints? {
        return null
    }

    override fun translate(x: Int, y: Int) {}
    override fun translate(tx: Double, ty: Double) {}
    override fun rotate(theta: Double) {}
    override fun rotate(theta: Double, x: Double, y: Double) {}
    override fun scale(sx: Double, sy: Double) {}
    override fun shear(shx: Double, shy: Double) {}
    override fun transform(Tx: AffineTransform) {}
    override fun setTransform(Tx: AffineTransform) {}
    override fun getTransform(): AffineTransform {
        return AffineTransform()
    }

    override fun getPaint(): Paint? {
        return null
    }

    override fun getComposite(): Composite {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER)
    }

    override fun setBackground(color: Color) {}
    override fun getBackground(): Color {
        return Color(0x00000000)
    }

    override fun getStroke(): Stroke {
        return BasicStroke()
    }

    override fun clip(s: Shape) {}
    override fun getFontRenderContext(): FontRenderContext? {
        return null
    }

    override fun create(): Graphics {
        return this
    }

    override fun getColor(): Color {
        return Color(0x00000000)
    }

    override fun setColor(c: Color) {}
    override fun setPaintMode() {}
    override fun setXORMode(c1: Color) {}
    override fun getFont(): Font? {
        return null
    }

    override fun setFont(font: Font) {}
    override fun getFontMetrics(f: Font): FontMetrics? {
        return null
    }

    override fun getClipBounds(): Rectangle? {
        return null
    }

    override fun clipRect(x: Int, y: Int, width: Int, height: Int) {}
    override fun setClip(x: Int, y: Int, width: Int, height: Int) {}
    override fun getClip(): Shape? {
        return null
    }

    override fun setClip(clip: Shape) {}
    override fun copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int) {}
    override fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {}
    override fun fillRect(x: Int, y: Int, width: Int, height: Int) {}
    override fun clearRect(x: Int, y: Int, width: Int, height: Int) {}
    override fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
    override fun fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
    override fun drawOval(x: Int, y: Int, width: Int, height: Int) {}
    override fun fillOval(x: Int, y: Int, width: Int, height: Int) {}
    override fun drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
    override fun fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
    override fun drawPolyline(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {}
    override fun drawPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {}
    override fun fillPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {}
    override fun drawImage(img: Image, x: Int, y: Int, observer: ImageObserver): Boolean {
        return false
    }

    override fun drawImage(
        img: Image, x: Int, y: Int, width: Int, height: Int,
        observer: ImageObserver
    ): Boolean {
        return false
    }

    override fun drawImage(img: Image, x: Int, y: Int, bgcolor: Color, observer: ImageObserver): Boolean {
        return false
    }

    override fun drawImage(
        img: Image,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        bgcolor: Color,
        observer: ImageObserver
    ): Boolean {
        return false
    }

    override fun drawImage(
        img: Image,
        dx1: Int,
        dy1: Int,
        dx2: Int,
        dy2: Int,
        sx1: Int,
        sy1: Int,
        sx2: Int,
        sy2: Int,
        observer: ImageObserver
    ): Boolean {
        return false
    }

    override fun drawImage(
        img: Image,
        dx1: Int,
        dy1: Int,
        dx2: Int,
        dy2: Int,
        sx1: Int,
        sy1: Int,
        sx2: Int,
        sy2: Int,
        bgcolor: Color,
        observer: ImageObserver
    ): Boolean {
        return false
    }

    override fun dispose() {}
}