/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.icon.transcoder.utils

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

internal open class McCrashyGraphics2D : Graphics2D() {
    override fun draw(s: Shape) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawImage(img: Image, xform: AffineTransform, obs: ImageObserver): Boolean {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawImage(img: BufferedImage, op: BufferedImageOp, x: Int, y: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawRenderedImage(img: RenderedImage, xform: AffineTransform) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawRenderableImage(img: RenderableImage, xform: AffineTransform) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawString(str: String, x: Int, y: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawString(str: String, x: Float, y: Float) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawString(iterator: AttributedCharacterIterator, x: Int, y: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawString(iterator: AttributedCharacterIterator, x: Float, y: Float) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawGlyphVector(g: GlyphVector, x: Float, y: Float) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun fill(s: Shape) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getDeviceConfiguration(): GraphicsConfiguration? {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setComposite(comp: Composite) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setPaint(paint: Paint) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setStroke(s: Stroke) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getRenderingHint(hintKey: RenderingHints.Key): Any? {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setRenderingHints(hints: Map<*, *>?) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun addRenderingHints(hints: Map<*, *>?) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getRenderingHints(): RenderingHints {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun translate(x: Int, y: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun translate(tx: Double, ty: Double) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun rotate(theta: Double) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun rotate(theta: Double, x: Double, y: Double) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun scale(sx: Double, sy: Double) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun shear(shx: Double, shy: Double) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun transform(Tx: AffineTransform) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setTransform(Tx: AffineTransform) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getTransform(): AffineTransform {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getPaint(): Paint {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getComposite(): Composite? {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setBackground(color: Color) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getBackground(): Color {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getStroke(): Stroke {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun clip(s: Shape) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getFontRenderContext(): FontRenderContext {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun create(): Graphics {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getColor(): Color {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setColor(c: Color) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setPaintMode() {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setXORMode(c1: Color) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getFont(): Font {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setFont(font: Font) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getFontMetrics(f: Font): FontMetrics {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getClipBounds(): Rectangle {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun clipRect(x: Int, y: Int, width: Int, height: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setClip(x: Int, y: Int, width: Int, height: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun getClip(): Shape {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setClip(clip: Shape) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun fillRect(x: Int, y: Int, width: Int, height: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun clearRect(x: Int, y: Int, width: Int, height: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawOval(x: Int, y: Int, width: Int, height: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun fillOval(x: Int, y: Int, width: Int, height: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawPolyline(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun fillPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawImage(img: Image, x: Int, y: Int, observer: ImageObserver?): Boolean {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawImage(
        img: Image, x: Int, y: Int, width: Int, height: Int,
        observer: ImageObserver
    ): Boolean {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun drawImage(img: Image, x: Int, y: Int, bgcolor: Color, observer: ImageObserver): Boolean {
        throw UnsupportedOperationException("This operation is not supported")
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
        throw UnsupportedOperationException("This operation is not supported")
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
        throw UnsupportedOperationException("This operation is not supported")
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
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun dispose() {
        throw UnsupportedOperationException("This operation is not supported")
    }
}