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
package org.pushingpixels.aurora.icon.transcoder

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apache.batik.bridge.SVGPatternElementBridge
import org.apache.batik.bridge.TextNode
import org.apache.batik.ext.awt.LinearGradientPaint
import org.apache.batik.ext.awt.MultipleGradientPaint
import org.apache.batik.ext.awt.RadialGradientPaint
import org.apache.batik.ext.awt.geom.ExtendedGeneralPath
import org.apache.batik.gvt.*
import org.pushingpixels.aurora.icon.transcoder.LanguageRenderer.MethodArgument
import org.pushingpixels.aurora.icon.transcoder.utils.McCrashyGraphics2D
import org.pushingpixels.aurora.icon.transcoder.utils.RasterScanner
import java.awt.*
import java.awt.geom.*
import java.awt.image.ImageObserver
import java.awt.image.RenderedImage
import java.io.*
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * SVG to Java2D transcoder.
 *
 * @author Kirill Grouchnikov.
 */
abstract class SvgBaseTranscoder(private val classname: String, private val languageRenderer: LanguageRenderer) {
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
     * Package name for the generated Java2D code.
     */
    private var packageName: String? = null

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

        fun format(format: String?, vararg args: Any?) {
            currentWriter.format(format, *args)
        }

        fun format(l: Locale?, format: String?, vararg args: Any?) {
            currentWriter.format(l, format, *args)
        }

        fun checkin() {
            // TODO - figure out if the nested withTransform{} blocks
            //  can somehow be broken on bigger inputs
//            if (lines >= ROTATION_THRESHOLD) {
//                currentWriter.close()
//                val paintingCodeStream = ByteArrayOutputStream()
//                streamList.add(paintingCodeStream)
//                currentWriter = PrintWriter(paintingCodeStream)
//                lines = 0
//            }
        }

        fun close() {
            currentWriter.close()
        }

        fun getStreamList(): List<ByteArrayOutputStream> {
            return Collections.unmodifiableList(streamList)
        }

        companion object {
            private const val ROTATION_THRESHOLD = 1000
        }

        init {
            val paintingCodeStream = ByteArrayOutputStream()
            streamList.add(paintingCodeStream)
            currentWriter = PrintWriter(paintingCodeStream)
        }
    }

    fun setPackageName(packageName: String?) {
        this.packageName = packageName
    }

    /**
     * Sets the listener.
     *
     * @param listener Listener.
     */
//    fun setListener(listener: TranscoderListener) {
//        this.listener = listener
//        setPrintWriter(PrintWriter(listener.writer))
//    }

    fun setPrintWriter(printWriter: PrintWriter?) {
        externalPrintWriter = printWriter
    }

    /**
     * Transcodes the SVG image into Java2D code.
     *
     * @param gvtRoot        Graphics vector tree root.
     * @param templateStream Stream with the template content.
     */
    fun transcode(gvtRoot: GraphicsNode, templateStream: InputStream?) {
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
                "package " + packageName + languageRenderer.statementEnd
            )
        } else {
            templateString.replace(TOKEN_PACKAGE.toRegex(), "")
        }
        templateString = templateString.replace(TOKEN_CLASSNAME.toRegex(), classname)

        // Pass 1 - scan and transcode all raster images
        val rasterCodeStream = ByteArrayOutputStream()
        val rasterPrintWriter = PrintWriter(rasterCodeStream)
        val rasterScanner = RasterScanner(rasterPrintWriter, languageRenderer)
        rasterScanner.scan(gvtRoot)
        rasterPrintWriter.close()
        val rasterCode = String(rasterCodeStream.toByteArray())
        templateString = templateString.replace(TOKEN_RASTER_CODE.toRegex(), rasterCode)

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
                "private fun _paint$i(drawScope : DrawScope) {" +
                        "\nwith(drawScope) {\n" + paintingCode + "\n}\n}"
            combinedPaintingCode.append(paintingCodeMethod)
            combinedPaintingCode.append("\n\n")
        }
        templateString = templateString.replace(TOKEN_PAINTING_CODE.toRegex(), combinedPaintingCode.toString())
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
    private fun transcodePathIterator(pathIterator: PathIterator, suffix: String) {
        val coords = FloatArray(6)
        printWriterManager!!.println("if (generalPath$suffix == null) {")
        printWriterManager!!.println("   generalPath$suffix = Path()")
        printWriterManager!!.println("} else {")
        printWriterManager!!.println("   generalPath$suffix!!.reset()")
        printWriterManager!!.println("}")
        while (!pathIterator.isDone) {
            // Probably the resulting class will run into "error: too many constants" in any case ¯\_(ツ)_/¯
            printWriterManager!!.checkin()
            when (pathIterator.currentSegment(coords)) {
                PathIterator.SEG_CUBICTO -> printWriterManager?.println(
                    "generalPath$suffix!!.cubicTo(${coords[0]}f, ${coords[1]}f, ${coords[2]}f, ${coords[3]}f, ${coords[4]}f, ${coords[5]}f)")
                PathIterator.SEG_QUADTO -> printWriterManager?.println(
                    "generalPath$suffix!!.quadraticBezierTo(${coords[0]}f, ${coords[1]}f, ${coords[2]}f, ${coords[3]}f)")
                PathIterator.SEG_MOVETO -> printWriterManager?.println(
                    "generalPath$suffix!!.moveTo(${coords[0]}f, ${coords[1]}f)")
                PathIterator.SEG_LINETO -> printWriterManager?.println(
                    "generalPath$suffix!!.lineTo(${coords[0]}f, ${coords[1]}f)")
                PathIterator.SEG_CLOSE -> printWriterManager?.println(
                    "generalPath$suffix!!.close()")
            }
            pathIterator.next()
        }
        printWriterManager!!.println("shape$suffix = Outline.Generic(generalPath!!)")
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

        printWriterManager!!.print("brush = LinearGradient(")
        val stopCount = correctedFractions.size
        for (stop in 0 until stopCount) {
            printWriterManager!!.print(
                "${correctedFractions[stop]}f to Color(${colors[stop].red}, " +
                        "${colors[stop].green}, ${colors[stop].blue}, ${colors[stop].alpha}), "
            )
        }

        // Apply the affine transform of the paint to start and end points
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)
        val matrix = Matrix(
            values = floatArrayOf(
                transfMatrix[0].toFloat(), transfMatrix[2].toFloat(), 0.0f, transfMatrix[4].toFloat(),
                transfMatrix[1].toFloat(), transfMatrix[3].toFloat(), 0.0f, transfMatrix[5].toFloat(),
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
            )
        )
        val transformedStart = matrix.map(Offset(x = startPoint.x.toFloat(), y = startPoint.y.toFloat()))
        val transformedEnd = matrix.map(Offset(x = endPoint.x.toFloat(), y = endPoint.y.toFloat()))

        printWriterManager!!.print("startX = ${transformedStart.x}f, startY = ${transformedStart.y}f, ")
        printWriterManager!!.print("endX = ${transformedEnd.x}f, endY = ${transformedEnd.y}f, ")
        printWriterManager!!.println("tileMode = $tileModeRep)")
    }

    private fun transcodePatternPaint(paint: PatternPaint) {
        val transform = paint.patternTransform
        transform.concatenate(paint.graphicsNode.transform)

        // Confine the tiling to the shape of the current node
        printWriterManager!!.println(
            "clip = g" + languageRenderer.getGetter("clip")
                    + languageRenderer.statementEnd
        )
        printWriterManager!!.println("g.clip(shape)" + languageRenderer.statementEnd)
        printWriterManager!!.println("{")
        // Get the pre-transformation bounding box of the pattern node
        val rect2D = paint.graphicsNode.bounds
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("Rectangle2D")
                    + "rect2D = " + languageRenderer.getObjectCreation("Rectangle2D.Double")
                    + "(" + rect2D.x + ", " + rect2D.y + ", " + rect2D.width + ", "
                    + rect2D.height + ")" + languageRenderer.statementEnd
        )
        // Create a new Graphics2D object
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("Graphics2D")
                    + "gTiled = " + languageRenderer.getObjectCast("g.create()", "Graphics2D")
                    + languageRenderer.statementEnd
        )
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)
        // Apply the transformation from the pattern node
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("AffineTransform")
                    + "tTiled = " + languageRenderer.getObjectCreation(
                "AffineTransform"
            )
                    + "(" + transfMatrix[0] + "f, " + transfMatrix[1] + "f, "
                    + transfMatrix[2] + "f, " + transfMatrix[3] + "f, " + transfMatrix[4]
                    + "f, " + transfMatrix[5] + "f)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println("    gTiled.transform(tTiled)" + languageRenderer.statementEnd)
        // Point2D objects for tracking when the tiling ends (in both directions)
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("Point2D")
                    + "src = " + languageRenderer.getObjectCreation("Point2D.Double")
                    + "(0, 0)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("Point2D")
                    + "dst = " + languageRenderer.getObjectCreation("Point2D.Double")
                    + "(0, 0)" + languageRenderer.statementEnd
        )

        // Start a nested loop that tiles the pattern (post-transformation) on the
        // clipped Graphics2D.
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("double")
                    + "startX = rect2D.getX()" + languageRenderer.statementEnd
        )
        printWriterManager!!.println("    while (true) {")
        printWriterManager!!.println(
            "        " + languageRenderer.startVariableDefinition("double")
                    + "startY = rect2D.getY()" + languageRenderer.statementEnd
        )
        printWriterManager!!.println("        while (true) {")
        printWriterManager!!.println(
            "            gTiled.translate(startX, startY)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "            " + languageRenderer.startVariableDefinition("Shape")
                    + "shapeTile = null" + languageRenderer.statementEnd
        )

        // Since PatternGraphicsNode does not (yet?) expose its content, we ask it to
        // paint itself to a custom extension of Graphics2D that tracks all relevant
        // operations and converts them to the matching Java2D instructions that are
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
                transcodeRenderedImage(img as RenderedImage, "gTiled")
                return false
            }

            override fun draw(shape: Shape) {
                transcodeShape(shape, "Tile")
                printWriterManager!!.println("gTiled.draw(shapeTile);")
            }

            override fun fill(shape: Shape) {
                transcodeShape(shape, "Tile")
                printWriterManager!!.println("gTiled.fill(shapeTile);")
            }

            override fun setComposite(composite: Composite) {
                this._composite = composite
                val rule = (composite as AlphaComposite).rule
                val alpha = composite.alpha
                printWriterManager!!.println(
                    "gTiled" + languageRenderer.startSetterAssignment("composite")
                            + "AlphaComposite.getInstance(" + rule + ", " + alpha + "f * origAlpha)"
                            + languageRenderer.endSetterAssignment() + languageRenderer.statementEnd
                )
            }

            override fun setPaint(paint: Paint) {
                transcodePaint(paint)
                printWriterManager!!.println(
                    "gTiled" + languageRenderer.startSetterAssignment("paint") + "paint"
                            + languageRenderer.endSetterAssignment() + languageRenderer.statementEnd
                )
            }

            override fun setStroke(stroke: Stroke) {
                val strokeWidth = (stroke as BasicStroke).lineWidth
                val endCap = stroke.endCap
                val lineJoin = stroke.lineJoin
                val miterLimit = stroke.miterLimit
                val dash = stroke.dashArray
                val dash_phase = stroke.dashPhase
                val dashRep = StringBuffer()
                if (dash == null) {
                    dashRep.append("null")
                } else {
                    var sep = ""
                    dashRep.append(languageRenderer.startPrimitiveArrayOf("float"))
                    for (_dash in dash) {
                        dashRep.append(sep)
                        dashRep.append(_dash.toString() + "f")
                        sep = ","
                    }
                    dashRep.append("}")
                }
                printWriterManager!!.println(
                    "gTiled" + languageRenderer.startSetterAssignment("stroke")
                            + languageRenderer.getObjectCreation("BasicStroke")
                            + "(" + strokeWidth + "f," + endCap + "," + lineJoin + "," + miterLimit
                            + "f," + dashRep + "," + dash_phase + "f)"
                            + languageRenderer.endSetterAssignment() + languageRenderer.statementEnd
                )
            }

            override fun setClip(x: Int, y: Int, width: Int, height: Int) {
                _clip = Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
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
                clip(Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble()))
            }

            override fun addRenderingHints(hints: Map<*, *>?) {
                this._hints.putAll(hints!!)
            }

            override fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any) {
                _hints[hintKey] = hintValue
            }

            override fun getRenderingHint(key: RenderingHints.Key): Any? {
                return _hints[key]
            }

            override fun setRenderingHints(hints: Map<*, *>?) {
                this._hints.clear()
                this._hints.putAll(hints!!)
            }

            override fun transform(Tx: AffineTransform) {
                this._transform.concatenate(Tx)
                //                double[] transfMatrix = new double[6];
//                transform.getMatrix(transfMatrix);
//                printWriter
//                        .println("gTiled.transform(" + languageRenderer.getObjectCreation
//                        ("AffineTransform")
//                                + "(" + transfMatrix[0] + "f, " + transfMatrix[1] + "f, "
//                                + transfMatrix[2] + "f, " + transfMatrix[3] + "f, " +
//                                transfMatrix[4]
//                                + "f, " + transfMatrix[5] + "f))" + languageRenderer
//                                .getStatementEnd());
            }

            override fun translate(x: Int, y: Int) {
                this._transform.translate(x.toDouble(), y.toDouble())
                //                printWriterManager.println("gTiled.translate(" + x + ", " + y + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun translate(tx: Double, ty: Double) {
                this._transform.translate(tx, ty)
                //                printWriterManager.println("gTiled.translate(" + tx + ", " + ty + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun rotate(theta: Double) {
                this._transform.rotate(theta)
                //                printWriterManager.println("gTiled.rotate(" + theta + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun rotate(theta: Double, x: Double, y: Double) {
                this._transform.rotate(theta, x, y)
                //                printWriterManager.println("gTiled.rotate(" + theta + ", " + x + ", " + y + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun getDeviceConfiguration(): GraphicsConfiguration? {
                return null
            }
        })
        printWriterManager!!.println(
            "            gTiled.translate(-startX, -startY)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "            startY += rect2D.getHeight()" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "            src.setLocation(startX, startY)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "            tTiled.transform(src, dst)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println("            if (dst.getY() > shape.getBounds().getMaxY()) {")
        printWriterManager!!.println("                break" + languageRenderer.statementEnd)
        printWriterManager!!.println("            }")
        printWriterManager!!.println("        }")
        printWriterManager!!.println(
            "        startX += rect2D.getWidth()" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "        src.setLocation(startX, startY)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "        tTiled.transform(src, dst)" + languageRenderer.statementEnd
        )
        printWriterManager!!.println("        if (dst.getX() > shape.getBounds().getMaxX()) {")
        printWriterManager!!.println("            break" + languageRenderer.statementEnd)
        printWriterManager!!.println("        }")
        printWriterManager!!.println("    }")
        printWriterManager!!.println("    gTiled.dispose()" + languageRenderer.statementEnd)
        printWriterManager!!.println("}")

        // Restore the original (pre-pattern) clip
        printWriterManager!!.println("g.setClip(clip)" + languageRenderer.statementEnd)
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

        require((abs(focusPoint.x - centerPoint.x) < 0.00001f) && (abs(focusPoint.y - centerPoint.y) < 0.00001f)) {
            "Focus point different from center point not supported"
        }

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

        printWriterManager!!.print("brush = RadialGradient(")
        val stopCount = correctedFractions.size
        for (stop in 0 until stopCount) {
            printWriterManager!!.print(
                "${correctedFractions[stop]}f to Color(${colors[stop].red}, " +
                        "${colors[stop].green}, ${colors[stop].blue}, ${colors[stop].alpha}), "
            )
        }

        // Apply the affine transform of the paint to center point
        val transfMatrix = DoubleArray(6)
        transform.getMatrix(transfMatrix)
        val matrix = Matrix(
            values = floatArrayOf(
                transfMatrix[0].toFloat(), transfMatrix[2].toFloat(), 0.0f, transfMatrix[4].toFloat(),
                transfMatrix[1].toFloat(), transfMatrix[3].toFloat(), 0.0f, transfMatrix[5].toFloat(),
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
            )
        )
        val transformedCenter = matrix.map(Offset(x = centerPoint.x.toFloat(), y = centerPoint.y.toFloat()))
        val transformedEdge = matrix.map(Offset(x = (centerPoint.x + radius).toFloat(), y = centerPoint.y.toFloat()))
        val dx = transformedEdge.x - transformedCenter.x
        val dy = transformedEdge.y - transformedCenter.y
        val transformedRadius = sqrt(dx * dx + dy * dy)

        printWriterManager!!.print("centerX = ${transformedCenter.x}f, centerY = ${transformedCenter.y}f, ")
        printWriterManager!!.print("radius = ${transformedRadius}f, ")
        printWriterManager!!.println("tileMode = $tileModeRep)")
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
            printWriterManager!!.println("brush = SolidColor(Color(${paint.red}, ${paint.green}, ${paint.blue}, ${paint.alpha}))")
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
            printWriterManager!!.println("drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)")
            return
        }
        if (paint is LinearGradientPaint) {
            transcodeLinearGradientPaint(paint)
            printWriterManager!!.println("drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)")
            return
        }
        if (paint is Color) {
            printWriterManager!!.println(
                "brush = SolidColor(Color(${paint.red}, ${paint.green}, ${paint.blue}, ${paint.alpha}))"
            )
            printWriterManager!!.println("drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)")
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
        transcodePaint(paint)
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
            dashRep.append(languageRenderer.startPrimitiveArrayOf("float"))
            for (_dash in dash) {
                dashRep.append(sep)
                dashRep.append(_dash.toString() + "f")
                sep = ","
            }
            dashRep.append("}")
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
            // TODO - switch away from Skija API for creating PathEffect once the fix for
            //  https://issuetracker.google.com/issues/171072166 is available
            printWriterManager!!.println(
                "stroke = Stroke(width=${width}f, cap=$strokeCap, join=$strokeJoin, miter=${miterlimit}f, " +
                        "pathEffect=PathEffect.makeDash($dashRep, ${dash_phase}f))"
            )
        }
        transcodeShape(shape, "")
        printWriterManager!!.println(
            "drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)"
        )
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
            val type = pathIterator.currentSegment(coords)
            when (type) {
                PathIterator.SEG_CUBICTO -> pathPoints.add(Point2D.Float(coords[4], coords[5]))
                PathIterator.SEG_QUADTO -> pathPoints.add(Point2D.Float(coords[2], coords[3]))
                PathIterator.SEG_MOVETO, PathIterator.SEG_LINETO -> pathPoints.add(
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
                printWriterManager!!.println("   rotate(degrees=${startMarker.orient}f, pivot = Offset(0.0f, 0.0f))")
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
                        printWriterManager!!.println("   rotate(degrees=${middleMarker.orient}f, pivot = Offset(0.0f, 0.0f))")
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
                printWriterManager!!.println("   rotate(degrees=${endMarker.orient}f, pivot = Offset(0.0f, 0.0f))")
            }
            printWriterManager!!.println("}) {")

            transcodeGraphicsNode(
                endMarker.markerNode,
                comment + "_" + "m" + (pathPointCount - 1)
            )

            printWriterManager!!.println("}")
        }
    }

    private fun rotate(angle: Double) {
        if (java.lang.Double.isFinite(angle) && angle != 0.0) {
            printWriterManager!!.println("g.rotate(" + angle + ")" + languageRenderer.statementEnd)
        }
    }

    /**
     * Transcodes the specified shape node.
     *
     * @param node    Shape node.
     * @param comment Comment (for associating the Java2D section with the corresponding SVG
     * section).
     */
    private fun transcodeShapeNode(node: ShapeNode, comment: String) {
        printWriterManager!!.println("// $comment")
        transcodeShapePainter(node.shapePainter, node.shape, comment)
        printWriterManager!!.checkin()
    }

    /**
     * Transcodes the specified composite graphics node.
     *
     * @param node    Composite graphics node.
     * @param comment Comment (for associating the Java2D section with the corresponding SVG
     * section).
     */
    private fun transcodeCompositeGraphicsNode(node: CompositeGraphicsNode, comment: String) {
        printWriterManager!!.println("// $comment")
        var count = 0
        for (obj in node.children) {
            transcodeGraphicsNode(obj as GraphicsNode?, comment + "_" + count)
            count++
        }
        printWriterManager!!.checkin()
    }

    private fun transcodeRenderedImage(image: RenderedImage?, graphicsName: String) {
        val md5: String = RasterScanner.getMD5(image)
        printWriterManager!!.println(
            languageRenderer.startVariableDefinition("BufferedImage")
                    + "image" + md5 + "=getImage" + md5 + "()" + languageRenderer.statementEnd
        )
        printWriterManager!!.println("if (image$md5 != null) {")
        printWriterManager!!.println(
            "    " + graphicsName + ".drawImage(image" + md5 + ", 0, 0, null)"
                    + languageRenderer.statementEnd
        )
        printWriterManager!!.println("}")
    }

    private fun transcodeRasterImageNode(node: RasterImageNode, comment: String) {
        printWriterManager!!.println("// $comment")
        val image = node.image.createDefaultRendering()
        transcodeRenderedImage(image, "g")
    }

    private fun transcodeTextNode(node: TextNode, comment: String) {
        printWriterManager!!.println("// $comment")
        printWriterManager!!.println("{")
        // Create a new Graphics2D object
        printWriterManager!!.println(
            "    " + languageRenderer.startVariableDefinition("Graphics2D")
                    + "gText = " + languageRenderer.getObjectCast("g.create()", "Graphics2D")
                    + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "            " + languageRenderer.startVariableDefinition("Shape")
                    + "shapeText = null" + languageRenderer.statementEnd
        )
        printWriterManager!!.println(
            "            " + languageRenderer.startVariableDefinition("GeneralPath")
                    + "generalPathText = null" + languageRenderer.statementEnd
        )
        node.primitivePaint(object : McCrashyGraphics2D() {
            var _clip: Shape? = null
            private val _hints = RenderingHints(HashMap<RenderingHints.Key, Any?>())

            //                double[] transfMatrix = new double[6];
//                transform.getMatrix(transfMatrix);
//                printWriter
//                        .println("gTiled.setTransform(" + languageRenderer.getObjectCreation
//                        ("AffineTransform")
//                                + "(" + transfMatrix[0] + "f, " + transfMatrix[1] + "f, "
//                                + transfMatrix[2] + "f, " + transfMatrix[3] + "f, " +
//                                transfMatrix[4]
//                                + "f, " + transfMatrix[5] + "f))" + languageRenderer
//                                .getStatementEnd());
            private var _transform = AffineTransform()
            private var _composite: Composite? = null
            override fun create(): Graphics {
                return this
            }

            override fun dispose() {}
            override fun draw(shape: Shape) {
                transcodeShape(shape, "Text")
                printWriterManager!!.println("gText.draw(shapeText);")
            }

            override fun fill(shape: Shape) {
                transcodeShape(shape, "Text")
                printWriterManager!!.println("gText.fill(shapeText);")
            }

            override fun setComposite(composite: Composite) {
                this._composite = composite
                val rule = (composite as AlphaComposite).rule
                val alpha = composite.alpha
                printWriterManager!!.println(
                    "gText" + languageRenderer.startSetterAssignment("composite")
                            + "AlphaComposite.getInstance(" + rule + ", " + alpha + "f * origAlpha)"
                            + languageRenderer.endSetterAssignment() + languageRenderer.statementEnd
                )
            }

            override fun setPaint(paint: Paint) {
                transcodePaint(paint)
                printWriterManager!!.println(
                    "gText" + languageRenderer.startSetterAssignment("paint") + "paint"
                            + languageRenderer.endSetterAssignment() + languageRenderer.statementEnd
                )
            }

            override fun setStroke(stroke: Stroke) {
                val strokeWidth = (stroke as BasicStroke).lineWidth
                val endCap = stroke.endCap
                val lineJoin = stroke.lineJoin
                val miterLimit = stroke.miterLimit
                val dash = stroke.dashArray
                val dash_phase = stroke.dashPhase
                val dashRep = StringBuffer()
                if (dash == null) {
                    dashRep.append("null")
                } else {
                    var sep = ""
                    dashRep.append(languageRenderer.startPrimitiveArrayOf("float"))
                    for (_dash in dash) {
                        dashRep.append(sep)
                        dashRep.append(_dash.toString() + "f")
                        sep = ","
                    }
                    dashRep.append("}")
                }
                printWriterManager!!.println(
                    "gText" + languageRenderer.startSetterAssignment("stroke")
                            + languageRenderer.getObjectCreation("BasicStroke")
                            + "(" + strokeWidth + "f," + endCap + "," + lineJoin + "," + miterLimit
                            + "f," + dashRep + "," + dash_phase + "f)"
                            + languageRenderer.endSetterAssignment() + languageRenderer.statementEnd
                )
            }

            override fun setClip(x: Int, y: Int, width: Int, height: Int) {
                _clip = Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
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
                clip(Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble()))
            }

            override fun setRenderingHints(hints: Map<*, *>?) {
                this._hints.clear()
                this._hints.putAll(hints!!)
            }

            override fun addRenderingHints(hints: Map<*, *>?) {
                this._hints.putAll(hints!!)
            }

            override fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any) {
                _hints[hintKey] = hintValue
            }

            override fun getRenderingHint(key: RenderingHints.Key): Any? {
                return _hints[key]
            }

            override fun getRenderingHints(): RenderingHints {
                return _hints
            }

            override fun transform(Tx: AffineTransform) {
                _transform.concatenate(Tx)
                //                double[] transfMatrix = new double[6];
//                transform.getMatrix(transfMatrix);
//                printWriter
//                        .println("gTiled.transform(" + languageRenderer.getObjectCreation
//                        ("AffineTransform")
//                                + "(" + transfMatrix[0] + "f, " + transfMatrix[1] + "f, "
//                                + transfMatrix[2] + "f, " + transfMatrix[3] + "f, " +
//                                transfMatrix[4]
//                                + "f, " + transfMatrix[5] + "f))" + languageRenderer
//                                .getStatementEnd());
            }

            override fun translate(x: Int, y: Int) {
                _transform.translate(x.toDouble(), y.toDouble())
                //                printWriterManager.println("gTiled.translate(" + x + ", " + y + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun translate(tx: Double, ty: Double) {
                _transform.translate(tx, ty)
                //                printWriterManager.println("gTiled.translate(" + tx + ", " + ty + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun rotate(theta: Double) {
                _transform.rotate(theta)
                //                printWriterManager.println("gTiled.rotate(" + theta + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun rotate(theta: Double, x: Double, y: Double) {
                _transform.rotate(theta, x, y)
                //                printWriterManager.println("gTiled.rotate(" + theta + ", " + x + ", " + y + ")"
//                        + languageRenderer.getStatementEnd());
            }

            override fun getComposite(): Composite? {
                return _composite
            }

            override fun getDeviceConfiguration(): GraphicsConfiguration? {
                return null
            }
        })
        printWriterManager!!.println("    gText.dispose()" + languageRenderer.statementEnd)
        printWriterManager!!.println("}")
        printWriterManager!!.checkin()
    }

    /**
     * Transcodes the specified graphics node.
     *
     * @param node    Graphics node.
     * @param comment Comment (for associating the Java2D section with the corresponding SVG
     * section).
     * @throws UnsupportedOperationException if the graphics node is unsupported.
     */
    @Throws(UnsupportedOperationException::class)
    private fun transcodeGraphicsNode(node: GraphicsNode?, comment: String) {
        val composite = node!!.composite as? AlphaComposite
        if (composite != null) {
            // TODO - throw on non-default rule?
            printWriterManager!!.println("alphaStack.add(0, alpha)")
            printWriterManager!!.println("alpha *= ${composite.alpha}f")
        }
        val transform = node.transform
        if (transform != null) {
            val transfMatrix = DoubleArray(6)
            transform.getMatrix(transfMatrix)
            printWriterManager!!.println("withTransform({")
            printWriterManager!!.println("transform(")
            printWriterManager!!.println("Matrix(values=floatArrayOf(")
            printWriterManager!!.println(
                "" + transfMatrix[0] + "f, " + transfMatrix[2] + "f, "
                        + "0.0f, " + transfMatrix[4] + "f,"
            )
            printWriterManager!!.println(
                "" + transfMatrix[1] + "f, " + transfMatrix[3] + "f, "
                        + "0.0f, " + transfMatrix[5] + "f,"
            )
            printWriterManager!!.println("0.0f, 0.0f, 1.0f, 0.0f,")
            printWriterManager!!.println("0.0f, 0.0f, 0.0f, 1.0f)")
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
            if (transform != null) {
                printWriterManager!!.println("}")
                if (composite != null) {
                    printWriterManager!!.println("alpha = alphaStack.removeAt(0)")
                }
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
    }
}