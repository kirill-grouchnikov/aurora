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

internal class RasterScanner(private val printWriter: PrintWriter) {
    private val processedMD5s = HashSet<String>()

    /**
     * Scans the SVG image for raster content
     *
     * @param gvtRoot Graphics vector tree root.
     */
    fun scan(gvtRoot: GraphicsNode) {
        scanGraphicsNode(gvtRoot)
    }

    fun hasRasters() : Boolean {
        return processedMD5s.isNotEmpty()
    }

    private fun scanPatternPaint(paint: PatternPaint) {
        // Since PatternGraphicsNode does not (yet?) expose its content, we ask it to
        // paint itself to a custom extension of Graphics2D that tracks image draw operations
        // and transcodes the image to a base64-string + code that decodes that string back
        // to image content at runtime
        val pgn: SVGPatternElementBridge.PatternGraphicsNode =
            paint.graphicsNode as SVGPatternElementBridge.PatternGraphicsNode
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
            scanPatternPaint(paint)
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
            scanCompositeShapePainter(painter)
            return
        }
        if (painter is FillShapePainter) {
            scanFillShapePainter(painter)
            return
        }
        if (painter is StrokeShapePainter) {
            scanStrokeShapePainter(painter)
            return
        }
        if (painter is MarkerShapePainter) {
            scanMarkerShapePainter(painter)
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
        val paint: Paint = painter.paint ?: return
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
        val sPainter: ShapePainter = node.shapePainter
        scanShapePainter(sPainter)
    }

    /**
     * Scans the specified composite graphics node.
     *
     * @param node    Composite graphics node.
     */
    private fun scanCompositeGraphicsNode(node: CompositeGraphicsNode) {
        for (obj in node.children) {
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
        printWriter.println("private fun getImage$md5(): ImageBitmap {")
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
            val chunkLength: Int = 1000.coerceAtMost(encoded.length - imageDataStart)
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
        printWriter.println("    val data = org.jetbrains.skia.Data.makeFromBytes(Base64.getDecoder().decode(imageData.toString()))")
        printWriter.println("    val codec = org.jetbrains.skia.Codec.makeFromData(data)")
        printWriter.println("    val pixels = codec.readPixels()")
        printWriter.println("    val result = pixels.asComposeImageBitmap()")
        printWriter.println("    image$md5 = WeakReference(result)")
        printWriter.println("    return result")
        printWriter.println("}")
        processedMD5s.add(md5)
    }

    private fun scanRasterImageNode(node: RasterImageNode) {
        val image: RenderedImage = node.image.createDefaultRendering()
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
            scanShapeNode(node)
            return
        }
        if (node is CompositeGraphicsNode) {
            scanCompositeGraphicsNode(node)
            return
        }
        if (node is RasterImageNode) {
            scanRasterImageNode(node)
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
