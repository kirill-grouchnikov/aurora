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

import org.apache.batik.bridge.SVGPatternElementBridge
import org.apache.batik.gvt.*
import java.awt.Image
import java.awt.Paint
import java.awt.image.ImageObserver
import java.awt.image.RenderedImage
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.PrintWriter
import java.io.UncheckedIOException
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import javax.imageio.ImageIO
import kotlin.collections.HashSet

internal class RasterScanner(val printWriter: PrintWriter) {
    private val processedMD5s = HashSet<String>()

    /**
     * Scans the SVG image for raster content
     *
     * @param gvtRoot Graphics vector tree root.
     */
    fun scan(gvtRoot: GraphicsNode) {
        scanGraphicsNode(gvtRoot)
    }

    private fun scanPatternPaint(paint: PatternPaint) {
        // Since PatternGraphicsNode does not (yet?) expose its content, we ask it to
        // paint itself to a custom extension of Graphics2D that tracks image draw operations
        // and transcodes the image to a base64-string + code that decodes that string back
        // to image content at runtime
        val pgn: SVGPatternElementBridge.PatternGraphicsNode =
            paint.getGraphicsNode() as SVGPatternElementBridge.PatternGraphicsNode
        pgn.primitivePaint(object : McStableGraphics2D() {
            override fun drawImage(img: Image, x: Int, y: Int, observer: ImageObserver): Boolean {
                transcodeRenderedImage(img as RenderedImage?)
                return false
            }
        })
    }

    /**
     * Scans the specified paint.
     *
     * @param paint Paint.
     * @throws UnsupportedOperationException if the paint is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun scanPaint(paint: Paint) {
        if (paint is PatternPaint) {
            scanPatternPaint(paint as PatternPaint)
        }
    }

    /**
     * Scans the specified shape painter.
     *
     * @param painter Shape painter.
     * @throws UnsupportedOperationException if the shape painter is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun scanShapePainter(painter: ShapePainter?) {
        if (painter == null) {
            return
        }
        if (painter is CompositeShapePainter) {
            scanCompositeShapePainter(painter as CompositeShapePainter)
            return
        }
        if (painter is FillShapePainter) {
            scanFillShapePainter(painter as FillShapePainter)
            return
        }
        if (painter is StrokeShapePainter) {
            scanStrokeShapePainter(painter as StrokeShapePainter)
            return
        }
        if (painter is MarkerShapePainter) {
            scanMarkerShapePainter(painter as MarkerShapePainter)
            return
        }
        throw UnsupportedOperationException(painter::class.simpleName)
    }

    /**
     * Scans the specified composite shape painter.
     *
     * @param painter Composite shape painter.
     */
    private fun scanCompositeShapePainter(painter: CompositeShapePainter) {
        for (i in 0 until painter.shapePainterCount) {
            scanShapePainter(painter.getShapePainter(i))
        }
    }

    /**
     * Scans the specified fill shape painter.
     *
     * @param painter Fill shape painter.
     */
    private fun scanFillShapePainter(painter: FillShapePainter) {
        val paint: Paint = painter.paint ?: return
        scanPaint(paint)
    }

    /**
     * Scans the specified stroke shape painter.
     *
     * @param painter Stroke shape painter.
     */
    private fun scanStrokeShapePainter(painter: StrokeShapePainter) {
        val paint: Paint = painter.getPaint() ?: return
        scanPaint(paint)
    }

    /**
     * Scans the specified marker shape painter.
     *
     * @param painter Marker shape painter.
     */
    private fun scanMarkerShapePainter(painter: MarkerShapePainter) {
        val start = painter.startMarker
        if (start != null) {
            scanGraphicsNode(start.markerNode)
        }
        val middle = painter.middleMarker
        if (middle != null) {
            scanGraphicsNode(middle.markerNode)
        }
        val end = painter.endMarker
        if (end != null) {
            scanGraphicsNode(end.markerNode)
        }
    }

    /**
     * Scans the specified shape node.
     *
     * @param node    Shape node.
     */
    private fun scanShapeNode(node: ShapeNode) {
        val sPainter: ShapePainter = node.getShapePainter()
        scanShapePainter(sPainter)
    }

    /**
     * Scans the specified composite graphics node.
     *
     * @param node    Composite graphics node.
     */
    private fun scanCompositeGraphicsNode(node: CompositeGraphicsNode) {
        for (obj in node.getChildren()) {
            scanGraphicsNode(obj as GraphicsNode)
        }
    }

    private fun transcodeRenderedImage(image: RenderedImage?) {
        val md5 = getMD5(image)
        if (processedMD5s.contains(md5)) {
            // Already transcoded
            return
        }

        // Static WeakReference to the decoded image
        printWriter.println("private lateinit var image$md5: WeakReference<ImageBitmap>")

        // Static method that returns an ImageBitmap
        printWriter.println("private fun getImage$md5(): ImageBitmap? {")
        printWriter.println("    if (::image$md5.isInitialized) {")
        printWriter.println("        val result = image$md5.get()")
        printWriter.println("        if (result != null) {")
        printWriter.println("            return result")
        printWriter.println("        }")
        printWriter.println("    }")

        // Encode image content as a single base-64 string
        val encoded = convertToBase64String(image)
        // Work around compile-time limitations on how long a String can be in the source file
        // by splitting the full base64 encoding into chunks of 1000 characters
        printWriter.println("    val imageData = StringBuilder(${encoded.length})")
        var imageDataStart = 0
        while (true) {
            val chunkLength: Int = Math.min(1000, encoded.length - imageDataStart)
            printWriter.println(
                ("    imageData.append(\""
                        + encoded.substring(imageDataStart, imageDataStart + chunkLength)
                        ) + "\")"
            )
            imageDataStart += 1000
            if (imageDataStart > encoded.length) {
                break
            }
        }
        printWriter.println("    try {")
        printWriter.println(
            "        val decoded = ImageIO.read(ByteArrayInputStream(Base64.getDecoder().decode(imageData.toString())))"
        )
        printWriter.println("        val result = decoded.toComposeBitmap()")
        printWriter.println("        image$md5 = WeakReference(result)")
        printWriter.println("        return result")
        printWriter.println("    } catch (ioe: IOException) {")
        printWriter.println("    }")
        printWriter.println("    return null")
        printWriter.println("}")
        processedMD5s.add(md5)
    }

    private fun scanRasterImageNode(node: RasterImageNode) {
        val image: RenderedImage = node.getImage().createDefaultRendering()
        transcodeRenderedImage(image)
    }

    /**
     * Scans the specified graphics node.
     *
     * @param node    Graphics node.
     * @throws UnsupportedOperationException if the graphics node is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun scanGraphicsNode(node: GraphicsNode) {
        if (node is ShapeNode) {
            scanShapeNode(node as ShapeNode)
            return
        }
        if (node is CompositeGraphicsNode) {
            scanCompositeGraphicsNode(node as CompositeGraphicsNode)
            return
        }
        if (node is RasterImageNode) {
            scanRasterImageNode(node as RasterImageNode)
            return
        }
    }

    companion object {
        private fun convertToBase64String(renderedImage: RenderedImage?): String {
            return try {
                val os = ByteArrayOutputStream()
                ImageIO.write(renderedImage, "png", Base64.getEncoder().wrap(os))
                os.toString(StandardCharsets.ISO_8859_1.name())
            } catch (ioe: IOException) {
                throw UncheckedIOException(ioe)
            }
        }

        fun getMD5(renderedImage: RenderedImage?): String {
            return try {
                val os = ByteArrayOutputStream()
                ImageIO.write(renderedImage, "png", Base64.getEncoder().wrap(os))
                val md5Digest: MessageDigest = MessageDigest.getInstance("MD5")
                md5Digest.update(os.toByteArray())
                val digest: ByteArray = md5Digest.digest()
                val result = StringBuilder()
                for (i in digest.indices) {
                    result.append(((digest[i].toInt() and 255) + 0x100).toString(16).substring(1))
                }
                result.toString()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}
