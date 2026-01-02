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
package org.pushingpixels.aurora.tools.svgtranscoder

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Matrix
import org.apache.batik.bridge.SVGPatternElementBridge
import org.apache.batik.bridge.TextNode
import org.apache.batik.ext.awt.LinearGradientPaint
import org.apache.batik.ext.awt.MultipleGradientPaint
import org.apache.batik.ext.awt.RadialGradientPaint
import org.apache.batik.ext.awt.geom.ExtendedGeneralPath
import org.apache.batik.gvt.*
import org.pushingpixels.aurora.tools.svgtranscoder.utils.McCrashyGraphics2D
import org.pushingpixels.aurora.tools.svgtranscoder.utils.RasterScanner
import java.awt.*
import java.awt.geom.*
import java.awt.image.ImageObserver
import java.awt.image.RenderedImage
import java.io.*
import java.util.*
import kotlin.math.sqrt
import java.awt.geom.PathIterator as AwtPathIterator

/**
 * SVG to Compose transcoder.
 *
 * @author Kirill Grouchnikov.
 */
abstract class SvgBaseTranscoder(private val classname: String) {
    /**
     * Listener.
     */
    var _listener: TranscoderListener? = null

    fun setListener(listener: TranscoderListener) {
        _listener = listener
        setPrintWriter(PrintWriter(listener.writer))
    }

    /**
     * Print writer that outputs the full class.
     */
    @JvmField
    protected var externalPrintWriter: PrintWriter? = null

    /**
     * Print writer that is used during the transcoding traversal to buffer the rendering instructions.
     */
    private var printWriterManager: PrintWriterManager? = null

    /**
     * Package name for the generated Compose code.
     */
    private var packageName: String? = null

    private var hasRasters = false

    private class PrintWriterManager {
        private val streamList: MutableList<ByteArrayOutputStream> = ArrayList()
        private var currentWriter: PrintWriter
        private var lines = 0
        fun println(string: String?) {
            currentWriter.println(string)
            lines++
        }

        fun print(string: String?) {
            currentWriter.print(string)
        }

        fun close() {
            currentWriter.close()
        }

        fun getStreamList(): List<ByteArrayOutputStream> {
            return Collections.unmodifiableList(streamList)
        }

        init {
            val paintingCodeStream = ByteArrayOutputStream()
            streamList.add(paintingCodeStream)
            currentWriter = PrintWriter(paintingCodeStream)
        }
    }

    fun setPackageName(packageName: String) {
        this.packageName = packageName
    }

    private fun setPrintWriter(printWriter: PrintWriter?) {
        externalPrintWriter = printWriter
    }

    /**
     * Transcodes the SVG image into Compose code.
     *
     * @param gvtRoot        Graphics vector tree root.
     * @param templateStream Stream with the template content.
     */
    fun transcode(gvtRoot: GraphicsNode, templateStream: InputStream) {
        // load the template
        val templateBuffer = StringBuffer()
        val templateReader = BufferedReader(InputStreamReader(templateStream))
        try {
            while (true) {
                val line = templateReader.readLine() ?: break
                templateBuffer.append("$line\n")
            }
            templateReader.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
        var templateString = templateBuffer.toString()
        templateString = if (packageName != null) {
            templateString.replace(
                TOKEN_PACKAGE.toRegex(),
                "package $packageName"
            )
        } else {
            templateString.replace(TOKEN_PACKAGE.toRegex(), "")
        }
        templateString = templateString.replace(TOKEN_CLASSNAME.toRegex(), classname)

        // Pass 1 - scan and transcode all raster images
        val rasterCodeStream = ByteArrayOutputStream()
        val rasterPrintWriter = PrintWriter(rasterCodeStream)
        val rasterScanner = RasterScanner(rasterPrintWriter)
        rasterScanner.scan(gvtRoot)
        rasterPrintWriter.close()
        val rasterCode = String(rasterCodeStream.toByteArray())
        templateString = templateString.replace(TOKEN_RASTER_CODE.toRegex(), rasterCode)

        this.hasRasters = rasterScanner.hasRasters()

        // Pass 2 - transcode the rest of the content
        printWriterManager = PrintWriterManager()
        transcodeGraphicsNode(gvtRoot, "")
        printWriterManager!!.close()
        val paintingCodeStreams = printWriterManager!!.getStreamList()
        val streamCount = paintingCodeStreams.size
        val combinedPaintingCode = StringBuffer()
        for (i in 0 until streamCount) {
            val currentPaintingCodeStream = paintingCodeStreams[i]
            val paintingCode = String(currentPaintingCodeStream.toByteArray())
            val paintingCodeMethod =
                "@Suppress(\"UNUSED_VARIABLE\", \"UNUSED_VALUE\", \"VARIABLE_WITH_REDUNDANT_INITIALIZER\", \"UNNECESSARY_NOT_NULL_ASSERTION\")\n" +
                "private fun _paint$i(drawScope : DrawScope) {\n" +
                        "var shapeText: Outline?\n" +
                        "var generalPathText: Path? = null\n" +
                        "var alphaText = 0.0f\n" +
                        "var blendModeText = DrawScope.DefaultBlendMode\n" +
                        "with(drawScope) {\n" + paintingCode + "\n}\n}"
            combinedPaintingCode.append(paintingCodeMethod)
            combinedPaintingCode.append("\n\n")
        }
        templateString =
            templateString.replace(TOKEN_PAINTING_CODE.toRegex(), combinedPaintingCode.toString())
        val combinedPaintingInvocations = StringBuffer()
        for (i in 0 until streamCount) {
            combinedPaintingInvocations.append("_paint$i(drawScope)\n")
        }
        templateString = templateString.replace(
            TOKEN_PAINTING_INVOCATIONS.toRegex(),
            combinedPaintingInvocations.toString()
        )
        val bounds = gvtRoot.bounds
        templateString = templateString.replace(TOKEN_ORIG_X.toRegex(), "" + bounds.x)
        templateString = templateString.replace(TOKEN_ORIG_Y.toRegex(), "" + bounds.y)
        templateString = templateString.replace(TOKEN_ORIG_WIDTH.toRegex(), "" + bounds.width)
        templateString = templateString.replace(TOKEN_ORIG_HEIGHT.toRegex(), "" + bounds.height)
        externalPrintWriter!!.println(templateString)
        externalPrintWriter!!.close()
        _listener?.finished()
    }

    /**
     * Transcodes the specified path iterator.
     *
     * @param pathIterator Path iterator.
     */
    private fun transcodePathIterator(pathIterator: AwtPathIterator, suffix: String) {
        val coords = FloatArray(6)
        printWriterManager!!.println("if (generalPath$suffix == null) {")
        printWriterManager!!.println("   generalPath$suffix = Path()")
        printWriterManager!!.println("} else {")
        printWriterManager!!.println("   generalPath$suffix!!.reset()")
        printWriterManager!!.println("}")
        printWriterManager!!.println("generalPath$suffix?.run {")
        while (!pathIterator.isDone) {
            when (pathIterator.currentSegment(coords)) {
                AwtPathIterator.SEG_CUBICTO -> printWriterManager?.println(
                    "    cubicTo(${coords[0]}f, ${coords[1]}f, ${coords[2]}f, ${coords[3]}f, ${coords[4]}f, ${coords[5]}f)"
                )
                AwtPathIterator.SEG_QUADTO -> printWriterManager?.println(
                    "    quadraticTo(${coords[0]}f, ${coords[1]}f, ${coords[2]}f, ${coords[3]}f)"
                )
                AwtPathIterator.SEG_MOVETO -> printWriterManager?.println(
                    "    moveTo(${coords[0]}f, ${coords[1]}f)"
                )
                AwtPathIterator.SEG_LINETO -> printWriterManager?.println(
                    "    lineTo(${coords[0]}f, ${coords[1]}f)"
                )
                AwtPathIterator.SEG_CLOSE -> printWriterManager?.println(
                    "    close()"
                )
            }
            pathIterator.next()
        }
        printWriterManager!!.println("}")
        printWriterManager!!.println("shape$suffix = Outline.Generic(generalPath$suffix!!)")
    }

    /**
     * Transcodes the specified shape.
     *
     * @param shape Shape.
     * @throws UnsupportedOperationException if the shape is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun transcodeShape(shape: Shape, suffix: String) {
        if (shape is ExtendedGeneralPath) {
            transcodePathIterator(shape.getPathIterator(null), suffix)
            return
        }
        if (shape is GeneralPath) {
            transcodePathIterator(shape.getPathIterator(null), suffix)
            return
        }
        if (shape is Rectangle2D) {
            printWriterManager!!.println(
                "shape$suffix = Outline.Rectangle(rect = Rect(left = ${shape.x}f, " +
                        "top = ${shape.y}f, right = ${shape.x + shape.width}f, " +
                        "bottom = ${shape.y + shape.height}f))"
            )
            return
        }
        if (shape is RoundRectangle2D) {
            printWriterManager!!.println(
                "shape$suffix = Outline.Rounded(roundRect = RoundRect(" +
                        "left = ${shape.x}f, top = ${shape.y}f, " +
                        "right = ${shape.x + shape.width}f, bottom = ${shape.y + shape.height}f," +
                        "radiusX = ${shape.arcWidth}f, radiusY = ${shape.arcHeight}f))"
            )
            return
        }
        if (shape is Ellipse2D) {
            printWriterManager!!.println(
                "shape$suffix = Outline.Generic(path = Path().also { it.addOval(oval=Rect(" +
                        "left = ${shape.x}f, top = ${shape.y}f, " +
                        "right = ${shape.x + shape.width}f, bottom = ${shape.y + shape.height}f))})"
            )
            return
        }
        if (shape is Line2D) {
            printWriterManager!!.println("shape$suffix = Outline.Generic(path = Path().also {")
            printWriterManager!!.println("   it.moveTo(x = ${shape.x1}f, y = ${shape.y1}f)")
            printWriterManager!!.println("   it.lineTo(x = ${shape.x2}f, y = ${shape.y2}f)})")
            return
        }
        throw UnsupportedOperationException(shape.javaClass.canonicalName)
    }

    /**
     * Transcodes the specified linear gradient paint.
     *
     * @param paint Linear gradient paint.
     * @throws IllegalArgumentException if the fractions are not strictly increasing.
     */
    @Throws(IllegalArgumentException::class)
    private fun transcodeLinearGradientPaint(paint: LinearGradientPaint) {
        val startPoint = paint.startPoint
        val endPoint = paint.endPoint
        val fractions = paint.fractions
        val colors = paint.colors
        val cycleMethod = paint.cycleMethod
        val transform = paint.transform

        // Check validity of fractions
        var previousFraction = -1.0f
        for (currentFraction in fractions!!) {
            require(!(currentFraction < 0f || currentFraction > 1f)) { "Fraction values must be in the range 0 to 1: $currentFraction" }
            require(currentFraction >= previousFraction) { "Keyframe fractions must be non-decreasing: $currentFraction" }
            previousFraction = currentFraction
        }

        // Correct fractions so that we don't have two consecutive identical
        // fraction values (since that would not sit well with color stop
        // handling in Compose)
        val correctedFractions = mutableListOf<Float>()
        previousFraction = -1.0f
        for (currentFraction in fractions) {
            var fraction = currentFraction
            if (fraction == previousFraction) fraction += 0.000000001f
            correctedFractions.add(fraction)
            previousFraction = fraction
        }

        var tileModeRep: String? = null
        if (cycleMethod === MultipleGradientPaint.NO_CYCLE) {
            tileModeRep = "TileMode.Clamp"
        }
        if (cycleMethod === MultipleGradientPaint.REFLECT) {
            tileModeRep = "TileMode.Mirror"
        }
        if (cycleMethod === MultipleGradientPaint.REPEAT) {
            tileModeRep = "TileMode.Repeated"
        }

        printWriterManager!!.print("brush = Brush.linearGradient(")
        val stopCount = correctedFractions.size
        for (stop in 0 until stopCount) {
            val stopColor = "Color(${colors[stop].red}, ${colors[stop].green}, " +
                    "${colors[stop].blue}, ${colors[stop].alpha})"
            printWriterManager!!.print(
                "${correctedFractions[stop]}f to $stopColor, "
            )
        }

        // Apply the affine transform of the paint to start and end points
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)
        val matrix = Matrix(
            values = floatArrayOf(
                transfMatrix[0].toFloat(),
                transfMatrix[1].toFloat(),
                0.0f,
                0.0f,
                transfMatrix[2].toFloat(),
                transfMatrix[3].toFloat(),
                0.0f,
                0.0f,
                0.0f,
                0.0f,
                1.0f,
                0.0f,
                transfMatrix[4].toFloat(),
                transfMatrix[5].toFloat(),
                0.0f,
                1.0f
            )
        )
        val transformedStart =
            matrix.map(Offset(x = startPoint.x.toFloat(), y = startPoint.y.toFloat()))
        val transformedEnd = matrix.map(Offset(x = endPoint.x.toFloat(), y = endPoint.y.toFloat()))

        printWriterManager!!.print("start = Offset(${transformedStart.x}f, ${transformedStart.y}f), ")
        printWriterManager!!.print("end = Offset(${transformedEnd.x}f, ${transformedEnd.y}f), ")
        printWriterManager!!.println("tileMode = $tileModeRep)")
    }

    private fun transcodePatternPaint(paint: PatternPaint) {
        val transform = paint.patternTransform
        transform.concatenate(paint.graphicsNode.transform)

        // Confine the tiling to the shape of the current node
        printWriterManager!!.println("withTransform({")
        printWriterManager!!.println("    if (shape is Outline.Rectangle) {")
        printWriterManager!!.println("        clipPath(Path().also { it.addRect((shape as Outline.Rectangle).rect) })")
        printWriterManager!!.println("    }")
        printWriterManager!!.println("    if (shape is Outline.Rounded) {")
        printWriterManager!!.println("        clipPath(Path().also { it.addRoundRect((shape as Outline.Rounded).roundRect) })")
        printWriterManager!!.println("    }")
        printWriterManager!!.println("    if (shape is Outline.Generic) {")
        printWriterManager!!.println("        clipPath(Path().also { it.addPath((shape as Outline.Generic).path) })")
        printWriterManager!!.println("    }")
        printWriterManager!!.println("}) {")

        // Get the pre-transformation bounding box of the pattern node
        val rect2D = paint.graphicsNode.bounds
        printWriterManager!!.println(
            "    val rect2D = Rect(left=${rect2D.x}f, top=${rect2D.y}f, " +
                    "right=${rect2D.x + rect2D.width}f, bottom=${rect2D.y + rect2D.height}f)"
        )
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)

        printWriterManager!!.println("    val tTiled = Matrix(values=floatArrayOf(")
        printWriterManager!!.println(
            "" + transfMatrix[0] + "f, " + transfMatrix[1] + "f, 0.0f, 0.0f,"
        )
        printWriterManager!!.println(
            "" + transfMatrix[2] + "f, " + transfMatrix[3] + "f, 0.0f, 0.0f,"
        )
        printWriterManager!!.println("0.0f, 0.0f, 1.0f, 0.0f,")
        printWriterManager!!.println(
            "" + transfMatrix[4] + "f, " + transfMatrix[5] + "f, 0.0f, 1.0f))"
        )

        // Apply the transformation from the pattern node
        printWriterManager!!.println("withTransform({transform(tTiled)}){")

        // Point2D objects for tracking when the tiling ends (in both directions)
        printWriterManager!!.println("   var src: Offset")
        printWriterManager!!.println("   var dst: Offset")

        // Start a nested loop that tiles the pattern (post-transformation) on the
        // clipped draw scope.
        printWriterManager!!.println("    var startX = rect2D.left")
        printWriterManager!!.println("    val maxX = when(shape) {")
        printWriterManager!!.println("        is Outline.Rectangle -> (shape as Outline.Rectangle).rect.right")
        printWriterManager!!.println("        is Outline.Rounded -> (shape as Outline.Rounded).roundRect.right")
        printWriterManager!!.println("        is Outline.Generic -> (shape as Outline.Generic).path.getBounds().right")
        printWriterManager!!.println("        else -> 0.0f")
        printWriterManager!!.println("    }")
        printWriterManager!!.println("    val maxY = when(shape) {")
        printWriterManager!!.println("        is Outline.Rectangle -> (shape as Outline.Rectangle).rect.bottom")
        printWriterManager!!.println("        is Outline.Rounded -> (shape as Outline.Rounded).roundRect.bottom")
        printWriterManager!!.println("        is Outline.Generic -> (shape as Outline.Generic).path.getBounds().bottom")
        printWriterManager!!.println("        else -> 0.0f")
        printWriterManager!!.println("    }")

        printWriterManager!!.println("    tileX@ while (true) {")
        printWriterManager!!.println("        var startY = rect2D.top")
        printWriterManager!!.println("        tileY@ while (true) {")

        printWriterManager!!.println("             translate(left = startX, top = startY) {")
        printWriterManager!!.println("             var shapeTile: Outline?")
        printWriterManager!!.println("             var generalPathTile: Path? = null")
        printWriterManager!!.println("             var alphaTile = alpha")
        printWriterManager!!.println("             var blendModeTile = blendMode")

        // Since PatternGraphicsNode does not (yet?) expose its content, we ask it to
        // paint itself to a custom extension of Graphics2D that tracks all relevant
        // operations and converts them to the matching DrawScope instructions that are
        // printed to our writer.
        val pgn = paint.graphicsNode as SVGPatternElementBridge.PatternGraphicsNode
        pgn.primitivePaint(object : McCrashyGraphics2D() {
            var _clip: Shape? = null
            private val _hints = RenderingHints(HashMap<RenderingHints.Key, Any?>())
            var _transform = AffineTransform()
            var _composite: Composite? = null
                private set

            override fun create(): Graphics {
                return this
            }

            override fun dispose() {}
            override fun drawImage(img: Image, x: Int, y: Int, observer: ImageObserver?): Boolean {
                transcodeRenderedImage(img as RenderedImage)
                return false
            }

            override fun draw(s: Shape) {
                transcodeShape(s, "Tile")
                printWriterManager!!.println("drawOutline(outline = shapeTile!!, style = stroke!!, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)")
            }

            override fun fill(s: Shape) {
                transcodeShape(s, "Tile")
                printWriterManager!!.println("drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)")
            }

            override fun setComposite(comp: Composite) {
                this._composite = comp
                val rule = (comp as AlphaComposite).rule
                val alpha = comp.alpha
                printWriterManager!!.println("alphaTile = alpha * ${alpha}f")
                printWriterManager!!.println("blendModeTile = ${blendModeToCompose(rule)}")
            }

            override fun getComposite(): Composite? {
                return this._composite
            }

            override fun setPaint(paint: Paint) {
                transcodePaint(paint)
            }

            override fun setStroke(s: Stroke) {
                val width = (s as BasicStroke).lineWidth
                val cap = s.endCap
                val join = s.lineJoin
                val miterlimit = s.miterLimit
                val dash = s.dashArray
                val dash_phase = s.dashPhase
                val dashRep = StringBuffer()
                if (dash == null) {
                    dashRep.append("null")
                } else {
                    var sep = ""
                    dashRep.append("floatArrayOf(")
                    for (_dash in dash) {
                        dashRep.append(sep)
                        dashRep.append(_dash.toString() + "f")
                        sep = ","
                    }
                    dashRep.append(")")
                }
                val strokeCap = when (cap) {
                    BasicStroke.CAP_BUTT -> "StrokeCap.Butt"
                    BasicStroke.CAP_ROUND -> "StrokeCap.Round"
                    BasicStroke.CAP_SQUARE -> "StrokeCap.Square"
                    else -> throw UnsupportedOperationException("Unsupported stroke cap $cap")
                }
                val strokeJoin = when (join) {
                    BasicStroke.JOIN_BEVEL -> "StrokeJoin.Bevel"
                    BasicStroke.JOIN_MITER -> "StrokeJoin.Miter"
                    BasicStroke.JOIN_ROUND -> "StrokeJoin.Round"
                    else -> throw UnsupportedOperationException("Unsupported stroke join $join")
                }
                if (dash == null) {
                    printWriterManager!!.println(
                        "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f)"
                    )
                } else {
                    printWriterManager!!.println(
                        "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f, " +
                                "pathEffect=PathEffect.dashPathEffect($dashRep, ${dash_phase}f))"
                    )
                }
            }

            override fun setClip(x: Int, y: Int, width: Int, height: Int) {
                _clip = Rectangle2D.Double(
                    x.toDouble(),
                    y.toDouble(),
                    width.toDouble(),
                    height.toDouble()
                )
            }

            override fun getClip(): Shape? {
                return _clip
            }

            override fun clip(s: Shape) {
                if (_clip == null) {
                    _clip = s
                } else {
                    val current = Area(_clip)
                    val requested = Area(s)
                    current.intersect(requested)
                    _clip = current
                }
            }

            override fun clipRect(x: Int, y: Int, width: Int, height: Int) {
                clip(
                    Rectangle2D.Double(
                        x.toDouble(),
                        y.toDouble(),
                        width.toDouble(),
                        height.toDouble()
                    )
                )
            }

            override fun addRenderingHints(hints: Map<*, *>?) {
                this._hints.putAll(hints!!)
            }

            override fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any?) {
                _hints[hintKey] = hintValue
            }

            override fun getRenderingHint(hintKey: RenderingHints.Key): Any? {
                return _hints[hintKey]
            }

            override fun setRenderingHints(hints: Map<*, *>?) {
                this._hints.clear()
                this._hints.putAll(hints!!)
            }

            override fun transform(Tx: AffineTransform) {
                this._transform.concatenate(Tx)
            }

            override fun translate(x: Int, y: Int) {
                this._transform.translate(x.toDouble(), y.toDouble())
            }

            override fun translate(tx: Double, ty: Double) {
                this._transform.translate(tx, ty)
            }

            override fun rotate(theta: Double) {
                this._transform.rotate(theta)
            }

            override fun rotate(theta: Double, x: Double, y: Double) {
                this._transform.rotate(theta, x, y)
            }

            override fun setTransform(Tx: AffineTransform) {
                this._transform = Tx
            }

            override fun getTransform(): AffineTransform {
                return this._transform
            }

            override fun getDeviceConfiguration(): GraphicsConfiguration? {
                return null
            }
        })
        printWriterManager!!.println("            }")
        printWriterManager!!.println("            startY += rect2D.height")
        printWriterManager!!.println("            src = Offset(x = startX, y = startY)")
        printWriterManager!!.println("            dst = tTiled.map(src)")
        printWriterManager!!.println("            if (dst.y > maxY) {")
        printWriterManager!!.println("                break@tileY")
        printWriterManager!!.println("            }")
        printWriterManager!!.println("        }")
        printWriterManager!!.println("        startX += rect2D.width")
        printWriterManager!!.println("        src = Offset(x = startX, y = startY)")
        printWriterManager!!.println("        dst = tTiled.map(src)")
        printWriterManager!!.println("        if (dst.x > maxX) {")
        printWriterManager!!.println("            break@tileX")
        printWriterManager!!.println("        }")
        printWriterManager!!.println("    }")
        printWriterManager!!.println("}")
        printWriterManager!!.println("}")
    }

    /**
     * Transcodes the specified radial gradient paint.
     *
     * @param paint Radial gradient paint.
     * @throws IllegalArgumentException if the fractions are not strictly increasing.
     */
    @Throws(IllegalArgumentException::class)
    private fun transcodeRadialGradientPaint(paint: RadialGradientPaint) {
        val centerPoint = paint.centerPoint
        val radius = paint.radius
        val focusPoint = paint.focusPoint
        val fractions = paint.fractions
        val colors = paint.colors
        val cycleMethod = paint.cycleMethod
        val transform = paint.transform

        // Check validity of fractions
        var previousFraction = -1.0f
        for (currentFraction in fractions!!) {
            require(!(currentFraction < 0f || currentFraction > 1f)) { "Fraction values must be in the range 0 to 1: $currentFraction" }
            require(currentFraction >= previousFraction) { "Keyframe fractions must be non-decreasing: $currentFraction" }
            previousFraction = currentFraction
        }
        // Correct fractions so that we don't have two consecutive identical
        // fraction values (since that would not sit well with color stop
        // handling in Compose)
        val correctedFractions = mutableListOf<Float>()
        previousFraction = -1.0f
        for (currentFraction in fractions) {
            var fraction = currentFraction
            if (fraction == previousFraction) fraction += 0.000000001f
            correctedFractions.add(fraction)
            previousFraction = fraction
        }

        // Apply the affine transform of the paint to center point
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)
        val matrix = Matrix(
            values = floatArrayOf(
                transfMatrix[0].toFloat(),
                transfMatrix[1].toFloat(),
                0.0f,
                0.0f,
                transfMatrix[2].toFloat(),
                transfMatrix[3].toFloat(),
                0.0f,
                0.0f,
                0.0f,
                0.0f,
                1.0f,
                0.0f,
                transfMatrix[4].toFloat(),
                transfMatrix[5].toFloat(),
                0.0f,
                1.0f
            )
        )
        val transformedCenter =
            matrix.map(Offset(x = centerPoint.x.toFloat(), y = centerPoint.y.toFloat()))
        val transformedFocus =
            matrix.map(Offset(x = focusPoint.x.toFloat(), y = focusPoint.y.toFloat()))
        val transformedEdge =
            matrix.map(Offset(x = (centerPoint.x + radius).toFloat(), y = centerPoint.y.toFloat()))
        val dx = transformedEdge.x - transformedCenter.x
        val dy = transformedEdge.y - transformedCenter.y
        val transformedRadius = sqrt(dx * dx + dy * dy)

        if (transformedFocus.equals(transformedCenter)) {
            // Focus is the same as center - use Compose's Brush.radialGradient
            val tileMode = when (cycleMethod) {
                MultipleGradientPaint.NO_CYCLE -> "TileMode.Clamp"
                MultipleGradientPaint.REFLECT -> "TileMode.Mirror"
                else -> "TileMode.Repeated"
            }

            printWriterManager!!.print("brush = Brush.radialGradient(")
            val stopCount = correctedFractions.size
            for (stop in 0 until stopCount) {
                val stopColor = "Color(${colors[stop].red}, ${colors[stop].green}, " +
                        "${colors[stop].blue}, ${colors[stop].alpha})"
                printWriterManager!!.print(
                    "${correctedFractions[stop]}f to $stopColor, "
                )
            }
            printWriterManager!!.print("center = Offset(${transformedCenter.x}f, ${transformedCenter.y}f), ")
            printWriterManager!!.print("radius = ${transformedRadius}f, ")
            printWriterManager!!.println("tileMode = $tileMode)")
        } else {
            // Focus is different from center - use Skia's Shader.makeTwoPointConicalGradient
            // and wrap it in Compose's ShaderBrush
            val tileMode = when (cycleMethod) {
                MultipleGradientPaint.NO_CYCLE -> "org.jetbrains.skia.FilterTileMode.CLAMP"
                MultipleGradientPaint.REFLECT -> "org.jetbrains.skia.FilterTileMode.MIRROR"
                else -> "org.jetbrains.skia.FilterTileMode.REPEAT"
            }

            // Transcode as two-point conical gradient
            printWriterManager!!.print("brush = ShaderBrush(org.jetbrains.skia.Shader.makeTwoPointConicalGradient(")

            printWriterManager!!.print("x0 = ${transformedFocus.x}f, y0 = ${transformedFocus.y}f, ")
            printWriterManager!!.print("r0 = 0.0f, ")
            printWriterManager!!.print("x1 = ${transformedCenter.x}f, y1 = ${transformedCenter.y}f, ")
            printWriterManager!!.print("r1 = ${transformedRadius}f, ")

            val stopCount = correctedFractions.size
            printWriterManager!!.print("colors = intArrayOf(")
            for (stop in 0 until stopCount) {
                printWriterManager!!.print("org.jetbrains.skia.Color.makeARGB(a = ${colors[stop].alpha}, r = ${colors[stop].red}, g = ${colors[stop].green}, b = ${colors[stop].blue}), ")
            }
            printWriterManager!!.print("), ")

            printWriterManager!!.print("positions = floatArrayOf(")
            for (stop in 0 until stopCount) {
                printWriterManager!!.print("${correctedFractions[stop]}f, ")
            }
            printWriterManager!!.print("), ")

            printWriterManager!!.print("style = org.jetbrains.skia.GradientStyle(tileMode = $tileMode, isPremul = true, localMatrix = null)")

            printWriterManager!!.println("))")
        }
    }

    /**
     * Transcodes the specified radial gradient paint.
     *
     * @param paint Radial gradient paint.
     * @throws IllegalArgumentException if the fractions are not strictly increasing.
     */
    @Throws(IllegalArgumentException::class)
    private fun transcodeRadialGradientPaintSkia(paint: RadialGradientPaint) {
        val centerPoint = paint.centerPoint
        val radius = paint.radius
        val focusPoint = paint.focusPoint
        val fractions = paint.fractions
        val colors = paint.colors
        val cycleMethod = paint.cycleMethod
        val transform = paint.transform

        // Check validity of fractions
        var previousFraction = -1.0f
        for (currentFraction in fractions!!) {
            require(!(currentFraction < 0f || currentFraction > 1f)) { "Fraction values must be in the range 0 to 1: $currentFraction" }
            require(currentFraction >= previousFraction) { "Keyframe fractions must be non-decreasing: $currentFraction" }
            previousFraction = currentFraction
        }
        // Correct fractions so that we don't have two consecutive identical
        // fraction values (since that would not sit well with color stop
        // handling in Compose)
        val correctedFractions = mutableListOf<Float>()
        previousFraction = -1.0f
        for (currentFraction in fractions) {
            var fraction = currentFraction
            if (fraction == previousFraction) fraction += 0.000000001f
            correctedFractions.add(fraction)
            previousFraction = fraction
        }

        // Apply the affine transform of the paint to center point
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)
        val matrix = Matrix(
            values = floatArrayOf(
                transfMatrix[0].toFloat(),
                transfMatrix[1].toFloat(),
                0.0f,
                0.0f,
                transfMatrix[2].toFloat(),
                transfMatrix[3].toFloat(),
                0.0f,
                0.0f,
                0.0f,
                0.0f,
                1.0f,
                0.0f,
                transfMatrix[4].toFloat(),
                transfMatrix[5].toFloat(),
                0.0f,
                1.0f
            )
        )
        val transformedCenter =
            matrix.map(Offset(x = centerPoint.x.toFloat(), y = centerPoint.y.toFloat()))
        val transformedFocus =
            matrix.map(Offset(x = focusPoint.x.toFloat(), y = focusPoint.y.toFloat()))
        val transformedEdge =
            matrix.map(Offset(x = (centerPoint.x + radius).toFloat(), y = centerPoint.y.toFloat()))
        val dx = transformedEdge.x - transformedCenter.x
        val dy = transformedEdge.y - transformedCenter.y
        val transformedRadius = sqrt(dx * dx + dy * dy)

        val tileMode = when (cycleMethod) {
            MultipleGradientPaint.NO_CYCLE -> "org.jetbrains.skia.FilterTileMode.CLAMP"
            MultipleGradientPaint.REFLECT -> "org.jetbrains.skia.FilterTileMode.MIRROR"
            else -> "org.jetbrains.skia.FilterTileMode.REPEAT"
        }

        if (focusPoint.equals(centerPoint)) {
            // Transcode as radial gradient
            printWriterManager!!.print("shaderSkia = org.jetbrains.skia.Shader.makeRadialGradient(")

            printWriterManager!!.print("x = ${transformedCenter.x}f, y = ${transformedCenter.y}f, ")
            printWriterManager!!.print("r = ${transformedRadius}f, ")

            val stopCount = correctedFractions.size
            printWriterManager!!.print("colors = intArrayOf(")
            for (stop in 0 until stopCount) {
                printWriterManager!!.print("org.jetbrains.skia.Color.makeARGB(a = ${colors[stop].alpha}, r = ${colors[stop].red}, g = ${colors[stop].green}, b = ${colors[stop].blue}), ")
            }
            printWriterManager!!.print("), ")

            printWriterManager!!.print("positions = floatArrayOf(")
            for (stop in 0 until stopCount) {
                printWriterManager!!.print("${correctedFractions[stop]}f, ")
            }
            printWriterManager!!.print("), ")

            printWriterManager!!.print("style = org.jetbrains.skia.GradientStyle(tileMode = $tileMode, isPremul = true, localMatrix = null)")

            printWriterManager!!.println(")")
        } else {
            // Transcode as two-point conical gradient
            printWriterManager!!.print("shaderSkia = org.jetbrains.skia.Shader.makeTwoPointConicalGradient(")

            printWriterManager!!.print("x0 = ${transformedFocus.x}f, y0 = ${transformedFocus.y}f, ")
            printWriterManager!!.print("r0 = 0.0f, ")
            printWriterManager!!.print("x1 = ${transformedCenter.x}f, y1 = ${transformedCenter.y}f, ")
            printWriterManager!!.print("r1 = ${transformedRadius}f, ")

            val stopCount = correctedFractions.size
            printWriterManager!!.print("colors = intArrayOf(")
            for (stop in 0 until stopCount) {
                printWriterManager!!.print("org.jetbrains.skia.Color.makeARGB(a = ${colors[stop].alpha}, r = ${colors[stop].red}, g = ${colors[stop].green}, b = ${colors[stop].blue}), ")
            }
            printWriterManager!!.print("), ")

            printWriterManager!!.print("positions = floatArrayOf(")
            for (stop in 0 until stopCount) {
                printWriterManager!!.print("${correctedFractions[stop]}f, ")
            }
            printWriterManager!!.print("), ")

            printWriterManager!!.print("style = org.jetbrains.skia.GradientStyle(tileMode = $tileMode, isPremul = true, localMatrix = null)")

            printWriterManager!!.println(")")
        }

        printWriterManager!!.println("brush = null")
        printWriterManager!!.println("stroke = null")
    }

    /**
     * Transcodes the specified paint.
     *
     * @param paint Paint.
     * @throws UnsupportedOperationException if the paint is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun transcodePaint(paint: Paint?) {
        if (paint is PatternPaint) {
            transcodePatternPaint(paint)
            return
        }
        if (paint is RadialGradientPaint) {
            transcodeRadialGradientPaint(paint)
            return
        }
        if (paint is LinearGradientPaint) {
            transcodeLinearGradientPaint(paint)
            return
        }
        if (paint is Color) {
            val solidColor = "Color(${paint.red}, ${paint.green}, ${paint.blue}, ${paint.alpha})"
            printWriterManager!!.println("brush = SolidColor($solidColor)")
            return
        }
        if (paint == null) {
            printWriterManager!!.println("No paint")
            return
        }
        throw UnsupportedOperationException(paint.javaClass.canonicalName)
    }

    /**
     * Transcodes the specified paint.
     *
     * @param paint Paint.
     * @throws UnsupportedOperationException if the paint is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun transcodePaintAndFill(paint: Paint?) {
        if (paint is PatternPaint) {
            transcodePatternPaint(paint)
            return
        }
        if (paint is RadialGradientPaint) {
            transcodeRadialGradientPaint(paint)
            printWriterManager!!.println("drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)")
            return
        }
        if (paint is LinearGradientPaint) {
            transcodeLinearGradientPaint(paint)
            printWriterManager!!.println("drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)")
            return
        }
        if (paint is Color) {
            val solidColor = "Color(${paint.red}, ${paint.green}, ${paint.blue}, ${paint.alpha})"
            printWriterManager!!.println("brush = SolidColor($solidColor)")
            printWriterManager!!.println("drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)")
            return
        }
        if (paint == null) {
            throw UnsupportedOperationException("No paint")
        }
        throw UnsupportedOperationException(paint.javaClass.canonicalName)
    }

    /**
     * Transcodes the specified shape painter.
     *
     * @param painter Shape painter.
     * @throws UnsupportedOperationException if the shape painter is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun transcodeShapePainter(painter: ShapePainter?, shape: Shape, comment: String) {
        if (painter == null) return
        if (painter is CompositeShapePainter) {
            transcodeCompositeShapePainter(painter, shape, comment)
            return
        }
        if (painter is FillShapePainter) {
            transcodeFillShapePainter(painter)
            return
        }
        if (painter is StrokeShapePainter) {
            transcodeStrokeShapePainter(painter)
            return
        }
        if (painter is MarkerShapePainter) {
            transcodeMarkerShapePainter(painter, shape, comment)
            return
        }
        throw UnsupportedOperationException(painter.javaClass.canonicalName)
    }

    /**
     * Transcodes the specified composite shape painter.
     *
     * @param painter Composite shape painter.
     */
    private fun transcodeCompositeShapePainter(
        painter: CompositeShapePainter, shape: Shape,
        comment: String
    ) {
        for (i in 0 until painter.shapePainterCount) {
            transcodeShapePainter(painter.getShapePainter(i), shape, comment)
        }
    }

    /**
     * Transcodes the specified fill shape painter.
     *
     * @param painter Fill shape painter.
     */
    private fun transcodeFillShapePainter(painter: FillShapePainter) {
        val paint = painter.paint ?: return
        transcodeShape(painter.shape, "")
        transcodePaintAndFill(paint)
    }

    /**
     * Transcodes the specified stroke shape painter.
     *
     * @param painter Stroke shape painter.
     */
    private fun transcodeStrokeShapePainter(painter: StrokeShapePainter) {
        val shape = painter.shape
        val paint = painter.paint ?: return
        val stroke = painter.stroke
        val bStroke = stroke as BasicStroke
        val width = bStroke.lineWidth
        val cap = bStroke.endCap
        val join = bStroke.lineJoin
        val miterlimit = bStroke.miterLimit
        val dash = bStroke.dashArray
        val dash_phase = bStroke.dashPhase

        val dashRep = StringBuffer()
        if (dash == null) {
            dashRep.append("null")
        } else {
            var sep = ""
            dashRep.append("floatArrayOf(")
            for (_dash in dash) {
                dashRep.append(sep)
                dashRep.append(_dash.toString() + "f")
                sep = ","
            }
            dashRep.append(")")
        }

        transcodePaint(paint)
        val strokeCap = when (cap) {
            BasicStroke.CAP_BUTT -> "StrokeCap.Butt"
            BasicStroke.CAP_ROUND -> "StrokeCap.Round"
            BasicStroke.CAP_SQUARE -> "StrokeCap.Square"
            else -> throw UnsupportedOperationException("Unsupported stroke cap $cap")
        }
        val strokeJoin = when (join) {
            BasicStroke.JOIN_BEVEL -> "StrokeJoin.Bevel"
            BasicStroke.JOIN_MITER -> "StrokeJoin.Miter"
            BasicStroke.JOIN_ROUND -> "StrokeJoin.Round"
            else -> throw UnsupportedOperationException("Unsupported stroke join $join")
        }
        if (dash == null) {
            printWriterManager!!.println(
                "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f)"
            )
        } else {
            printWriterManager!!.println(
                "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f, " +
                        "pathEffect=PathEffect.dashPathEffect($dashRep, ${dash_phase}f))"
            )
        }
        transcodeShape(shape, "")
        printWriterManager!!.println(
            "drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)")
    }

    /**
     * Transcodes the specified marker shape painter.
     *
     * @param painter Marker shape painter.
     */
    private fun transcodeMarkerShapePainter(
        painter: MarkerShapePainter, shape: Shape,
        comment: String
    ) {
        // Get all the shape points by flattening it into PathIterator
        val pathIterator = shape.getPathIterator(null)
        val pathPoints: MutableList<Point2D> = ArrayList()
        val coords = FloatArray(6)
        while (!pathIterator.isDone) {
            when (pathIterator.currentSegment(coords)) {
                AwtPathIterator.SEG_CUBICTO -> pathPoints.add(Point2D.Float(coords[4], coords[5]))
                AwtPathIterator.SEG_QUADTO -> pathPoints.add(Point2D.Float(coords[2], coords[3]))
                AwtPathIterator.SEG_MOVETO, AwtPathIterator.SEG_LINETO -> pathPoints.add(
                    Point2D.Float(
                        coords[0], coords[1]
                    )
                )
            }
            pathIterator.next()
        }
        val pathPointCount = pathPoints.size

        // Transcode the start marker if it's there. This only applies to the first point.
        if (painter.startMarker != null) {
            val firstPoint = pathPoints[0]
            val startMarker = painter.startMarker
            val dx = firstPoint.x - startMarker.ref.x
            val dy = firstPoint.y - startMarker.ref.y

            printWriterManager!!.println("withTransform({")
            printWriterManager!!.println("   translate(left=${dx}f, top=${dy}f)")
            if (java.lang.Double.isFinite(startMarker.orient) && (startMarker.orient != 0.0)) {
                printWriterManager!!.println("   rotate(degrees=${startMarker.orient}f, pivot = Offset.Zero)")
            }
            printWriterManager!!.println("}) {")

            transcodeGraphicsNode(startMarker.markerNode, comment + "_" + "m0")

            printWriterManager!!.println("}")
        }

        // Transcode the middle marker if it's there. This only applies to the points between
        // the first and the last.
        if (painter.middleMarker != null) {
            if (pathPointCount > 2) {
                val middleMarker = painter.middleMarker
                for (i in 1 until pathPointCount - 1) {
                    val middlePoint = pathPoints[i]
                    val dx = middlePoint.x - middleMarker.ref.x
                    val dy = middlePoint.y - middleMarker.ref.y

                    printWriterManager!!.println("withTransform({")
                    printWriterManager!!.println("   translate(left=${dx}f, top=${dy}f)")
                    if (java.lang.Double.isFinite(middleMarker.orient) && (middleMarker.orient != 0.0)) {
                        printWriterManager!!.println("   rotate(degrees=${middleMarker.orient}f, pivot = Offset.Zero)")
                    }
                    printWriterManager!!.println("}) {")

                    transcodeGraphicsNode(middleMarker.markerNode, comment + "_" + "m" + i)

                    printWriterManager!!.println("}")
                }
            }
        }

        // Transcode the end marker if it's there. This only applies to the last point.
        if (painter.endMarker != null) {
            val lastPoint = pathPoints[pathPointCount - 1]
            val endMarker = painter.endMarker
            val dx = lastPoint.x - endMarker.ref.x
            val dy = lastPoint.y - endMarker.ref.y

            printWriterManager!!.println("withTransform({")
            printWriterManager!!.println("   translate(left=${dx}f, top=${dy}f)")
            if (java.lang.Double.isFinite(endMarker.orient) && (endMarker.orient != 0.0)) {
                printWriterManager!!.println("   rotate(degrees=${endMarker.orient}f, pivot = Offset.Zero)")
            }
            printWriterManager!!.println("}) {")

            transcodeGraphicsNode(
                endMarker.markerNode,
                comment + "_" + "m" + (pathPointCount - 1)
            )

            printWriterManager!!.println("}")
        }
    }

    /**
     * Transcodes the specified shape node.
     *
     * @param node    Shape node.
     * @param comment Comment (for associating the Compose section with the corresponding SVG
     * section).
     */
    private fun transcodeShapeNode(node: ShapeNode, comment: String) {
        printWriterManager!!.println("// $comment")
        transcodeShapePainter(node.shapePainter, node.shape, comment)
    }

    /**
     * Transcodes the specified composite graphics node.
     *
     * @param node    Composite graphics node.
     * @param comment Comment (for associating the Compose section with the corresponding SVG
     * section).
     */
    private fun transcodeCompositeGraphicsNode(node: CompositeGraphicsNode, comment: String) {
        printWriterManager!!.println("// $comment")
        for ((count, obj) in node.children.withIndex()) {
            transcodeGraphicsNode(obj as GraphicsNode?, comment + "_" + count)
        }
    }

    private fun transcodeRenderedImage(image: RenderedImage) {
        val md5: String = RasterScanner.getMD5(image)
        printWriterManager!!.println("val image$md5 = getImage$md5()")
        printWriterManager!!.println("drawImage(image$md5)")
    }

    private fun transcodeRasterImageNode(node: RasterImageNode, comment: String) {
        printWriterManager!!.println("// $comment")
        val image = node.image.createDefaultRendering()
        transcodeRenderedImage(image)
    }

    private fun transcodeTextNode(node: TextNode, comment: String) {
        printWriterManager!!.println("// $comment")

        printWriterManager!!.println("            generalPathText = null")
        printWriterManager!!.println("            alphaText = alpha")
        printWriterManager!!.println("            blendModeText = blendMode")

        node.primitivePaint(object : McCrashyGraphics2D() {
            var _clip: Shape? = null
            private val _hints = RenderingHints(HashMap<RenderingHints.Key, Any?>())

            private var _transform = AffineTransform()
            private var _composite: Composite? = null
            override fun create(): Graphics {
                return this
            }

            override fun dispose() {}
            override fun draw(s: Shape) {
                transcodeShape(s, "Text")
                printWriterManager!!.println("drawOutline(outline = shapeText!!, style = stroke!!, brush=brush!!, alpha = alphaText, blendMode = blendModeText)")
            }

            override fun fill(s: Shape) {
                transcodeShape(s, "Text")
                printWriterManager!!.println("drawOutline(outline = shapeText!!, style = Fill, brush=brush!!, alpha = alphaText, blendMode = blendModeText)")
            }

            override fun setComposite(comp: Composite) {
                this._composite = comp
                val rule = (comp as AlphaComposite).rule
                val alpha = comp.alpha
                printWriterManager!!.println("alphaText = alpha * ${alpha}f")
                printWriterManager!!.println("blendModeText = ${blendModeToCompose(rule)}")
            }

            override fun setPaint(paint: Paint) {
                transcodePaint(paint)
            }

            override fun setStroke(s: Stroke) {
                val width = (s as BasicStroke).lineWidth
                val cap = s.endCap
                val join = s.lineJoin
                val miterlimit = s.miterLimit
                val dash = s.dashArray
                val dash_phase = s.dashPhase
                val dashRep = StringBuffer()
                if (dash == null) {
                    dashRep.append("null")
                } else {
                    var sep = ""
                    dashRep.append("floatArrayOf(")
                    for (_dash in dash) {
                        dashRep.append(sep)
                        dashRep.append(_dash.toString() + "f")
                        sep = ","
                    }
                    dashRep.append(")")
                }
                val strokeCap = when (cap) {
                    BasicStroke.CAP_BUTT -> "StrokeCap.Butt"
                    BasicStroke.CAP_ROUND -> "StrokeCap.Round"
                    BasicStroke.CAP_SQUARE -> "StrokeCap.Square"
                    else -> throw UnsupportedOperationException("Unsupported stroke cap $cap")
                }
                val strokeJoin = when (join) {
                    BasicStroke.JOIN_BEVEL -> "StrokeJoin.Bevel"
                    BasicStroke.JOIN_MITER -> "StrokeJoin.Miter"
                    BasicStroke.JOIN_ROUND -> "StrokeJoin.Round"
                    else -> throw UnsupportedOperationException("Unsupported stroke join $join")
                }
                if (dash == null) {
                    printWriterManager!!.println(
                        "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f)"
                    )
                } else {
                    printWriterManager!!.println(
                        "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f, " +
                                "pathEffect=PathEffect.dashPathEffect($dashRep, ${dash_phase}f))"
                    )
                }
            }

            override fun setClip(x: Int, y: Int, width: Int, height: Int) {
                _clip = Rectangle2D.Double(
                    x.toDouble(),
                    y.toDouble(),
                    width.toDouble(),
                    height.toDouble()
                )
            }

            override fun getClip(): Shape? {
                return _clip
            }

            override fun clip(s: Shape) {
                if (_clip == null) {
                    _clip = s
                } else {
                    val current = Area(_clip)
                    val requested = Area(s)
                    current.intersect(requested)
                    _clip = current
                }
            }

            override fun clipRect(x: Int, y: Int, width: Int, height: Int) {
                clip(
                    Rectangle2D.Double(
                        x.toDouble(),
                        y.toDouble(),
                        width.toDouble(),
                        height.toDouble()
                    )
                )
            }

            override fun setRenderingHints(hints: Map<*, *>?) {
                this._hints.clear()
                this._hints.putAll(hints!!)
            }

            override fun addRenderingHints(hints: Map<*, *>?) {
                this._hints.putAll(hints!!)
            }

            override fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any?) {
                _hints[hintKey] = hintValue
            }

            override fun getRenderingHint(hintKey: RenderingHints.Key): Any? {
                return _hints[hintKey]
            }

            override fun getRenderingHints(): RenderingHints {
                return _hints
            }

            override fun transform(Tx: AffineTransform) {
                _transform.concatenate(Tx)
            }

            override fun translate(x: Int, y: Int) {
                _transform.translate(x.toDouble(), y.toDouble())
            }

            override fun translate(tx: Double, ty: Double) {
                _transform.translate(tx, ty)
            }

            override fun rotate(theta: Double) {
                _transform.rotate(theta)
            }

            override fun rotate(theta: Double, x: Double, y: Double) {
                _transform.rotate(theta, x, y)
            }

            override fun getComposite(): Composite? {
                return _composite
            }

            override fun getDeviceConfiguration(): GraphicsConfiguration? {
                return null
            }
        })
    }

    /**
     * Transcodes the specified graphics node.
     *
     * @param node    Graphics node.
     * @param comment Comment (for associating the Compose section with the corresponding SVG
     * section).
     * @throws UnsupportedOperationException if the graphics node is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun transcodeGraphicsNode(node: GraphicsNode?, comment: String) {
        val composite = node!!.composite as? AlphaComposite
        if (composite != null) {
            printWriterManager!!.println("alphaStack.add(0, alpha)")
            printWriterManager!!.println("alpha *= ${composite.alpha}f")
            printWriterManager!!.println("blendModeStack.add(0, ${blendModeToCompose(composite.rule)})")
            printWriterManager!!.println("blendMode = ${blendModeToCompose(composite.rule)}")
        }
        val transform = node.transform
        if (isNonIdentityTransform(transform)) {
            val transfMatrix = DoubleArray(6)
            transform.getMatrix(transfMatrix)
            printWriterManager!!.println("withTransform({")
            printWriterManager!!.println("transform(")
            printWriterManager!!.println("Matrix(values=floatArrayOf(")
            printWriterManager!!.println(
                "" + transfMatrix[0] + "f, " + transfMatrix[1] + "f, 0.0f, 0.0f,"
            )
            printWriterManager!!.println(
                "" + transfMatrix[2] + "f, " + transfMatrix[3] + "f, 0.0f, 0.0f,"
            )
            printWriterManager!!.println("0.0f, 0.0f, 1.0f, 0.0f,")
            printWriterManager!!.println(
                "" + transfMatrix[4] + "f, " + transfMatrix[5] + "f, 0.0f, 1.0f)"
            )
            printWriterManager!!.println("))}){")
        }
        try {
            if (node is ShapeNode) {
                transcodeShapeNode(node, comment)
                return
            }
            if (node is CompositeGraphicsNode) {
                transcodeCompositeGraphicsNode(node, comment)
                return
            }
            if (node is RasterImageNode) {
                transcodeRasterImageNode(node, comment)
                return
            }
            if (node is TextNode) {
                transcodeTextNode(node, comment)
                return
            }
            throw UnsupportedOperationException(node.javaClass.canonicalName)
        } finally {
            if (isNonIdentityTransform(transform)) {
                printWriterManager!!.println("}")
            }
            if (composite != null) {
                printWriterManager!!.println("alpha = alphaStack.removeAt(0)")
                printWriterManager!!.println("blendMode = blendModeStack.removeAt(0)")
            }
        }
    }

    companion object {
        private const val TOKEN_PACKAGE = "TOKEN_PACKAGE"
        private const val TOKEN_CLASSNAME = "TOKEN_CLASSNAME"
        private const val TOKEN_RASTER_CODE = "TOKEN_RASTER_CODE"
        private const val TOKEN_PAINTING_CODE = "TOKEN_PAINTING_CODE"
        private const val TOKEN_PAINTING_INVOCATIONS = "TOKEN_PAINTING_INVOCATIONS"
        private const val TOKEN_ORIG_X = "TOKEN_ORIG_X"
        private const val TOKEN_ORIG_Y = "TOKEN_ORIG_Y"
        private const val TOKEN_ORIG_WIDTH = "TOKEN_ORIG_WIDTH"
        private const val TOKEN_ORIG_HEIGHT = "TOKEN_ORIG_HEIGHT"

        private fun blendModeToCompose(blendModeJava2D: Int): String {
            return when (blendModeJava2D) {
                AlphaComposite.CLEAR -> "BlendMode.Clear"
                AlphaComposite.DST -> "BlendMode.Dst"
                AlphaComposite.DST_ATOP -> "BlendMode.DstAtop"
                AlphaComposite.DST_IN -> "BlendMode.DstIn"
                AlphaComposite.DST_OUT -> "BlendMode.DstOut"
                AlphaComposite.DST_OVER -> "BlendMode.DstOver"
                AlphaComposite.SRC -> "BlendMode.Src"
                AlphaComposite.SRC_ATOP -> "BlendMode.SrcAtop"
                AlphaComposite.SRC_IN -> "BlendMode.SrcIn"
                AlphaComposite.SRC_OUT -> "BlendMode.SrcOut"
                AlphaComposite.SRC_OVER -> "BlendMode.SrcOver"
                AlphaComposite.XOR -> "BlendMode.Xor"
                else -> "DrawScope.DefaultBlendMode"
            }
        }

        private fun blendModeToSkia(blendModeJava2D: Int): String {
            return when (blendModeJava2D) {
                AlphaComposite.CLEAR -> "org.jetbrains.skia.BlendMode.CLEAR"
                AlphaComposite.DST -> "org.jetbrains.skia.BlendMode.DST"
                AlphaComposite.DST_ATOP -> "org.jetbrains.skia.BlendMode.DST_ATOP"
                AlphaComposite.DST_IN -> "org.jetbrains.skia.BlendMode.DST_IN"
                AlphaComposite.DST_OUT -> "org.jetbrains.skia.BlendMode.DST_OUT"
                AlphaComposite.DST_OVER -> "org.jetbrains.skia.BlendMode.DST_OVER"
                AlphaComposite.SRC -> "org.jetbrains.skia.BlendMode.SRC"
                AlphaComposite.SRC_ATOP -> "org.jetbrains.skia.BlendMode.SRC_ATOP"
                AlphaComposite.SRC_IN -> "org.jetbrains.skia.BlendMode.SRC_IN"
                AlphaComposite.SRC_OUT -> "org.jetbrains.skia.BlendMode.SRC_OUT"
                AlphaComposite.SRC_OVER -> "org.jetbrains.skia.BlendMode.SRC_OVER"
                AlphaComposite.XOR -> "org.jetbrains.skia.BlendMode.XOR"
                else -> "org.jetbrains.skia.BlendMode.SRC_OVER"
            }
        }

        private fun isNonIdentityTransform(transform: AffineTransform?): Boolean {
            return if (transform == null) {
                false
            } else !transform.isIdentity
        }
    }
}
