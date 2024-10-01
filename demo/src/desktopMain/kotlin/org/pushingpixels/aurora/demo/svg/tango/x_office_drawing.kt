package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import java.lang.ref.WeakReference
import java.util.*
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class x_office_drawing : Painter() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var blendMode = DrawScope.DefaultBlendMode
    private var alphaStack = mutableListOf(1.0f)
    private var blendModeStack = mutableListOf(DrawScope.DefaultBlendMode)

	@Suppress("UNUSED_VARIABLE", "UNUSED_VALUE", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNNECESSARY_NOT_NULL_ASSERTION")
private fun _paint0(drawScope : DrawScope) {
var shapeText: Outline?
var generalPathText: Path? = null
var alphaText = 0.0f
var blendModeText = DrawScope.DefaultBlendMode
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.02105461061000824f, 0.0f, 0.0f, 0.0f,
0.0f, 0.02086758054792881f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
42.85171890258789f, 41.15359878540039f, 0.0f, 1.0f)
))}){
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0
shape = Outline.Rectangle(rect = Rect(left = -1559.2523193359375f, top = -150.6968536376953f, right = -219.6187744140625f, bottom = 327.6603240966797f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(-1051.9354f, -150.69684f), end = Offset(-1051.9354f, 327.6604f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-219.61876f, -150.68037f)
    cubicTo(-219.61876f, -150.68037f, -219.61876f, 327.65042f, -219.61876f, 327.65042f)
    cubicTo(-76.74459f, 328.55087f, 125.78146f, 220.48074f, 125.78138f, 88.45424f)
    cubicTo(125.78138f, -43.572304f, -33.655437f, -150.68036f, -219.61876f, -150.68037f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-211.146f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1559.2523f, -150.68037f)
    cubicTo(-1559.2523f, -150.68037f, -1559.2523f, 327.65042f, -1559.2523f, 327.65042f)
    cubicTo(-1702.1265f, 328.55087f, -1904.6525f, 220.48074f, -1904.6525f, 88.45424f)
    cubicTo(-1904.6525f, -43.572304f, -1745.2157f, -150.68036f, -1559.2523f, -150.68037f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-1567.7247f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_0
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2980971336364746f, radiusY = 2.2980971336364746f))
brush = Brush.radialGradient(0.0f to Color(250, 250, 250, 255), 1.0f to Color(187, 187, 187, 255), center = Offset(32.62476f, 37.206844f), radius = 83.28285f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.radialGradient(0.0f to Color(163, 163, 163, 255), 1.0f to Color(76, 76, 76, 255), center = Offset(11.898f, 4.525653f), radius = 36.553967f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2980971336364746f, radiusY = 2.2980971336364746f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-4.882682800292969f, 11.185819625854492f, 0.0f, 1.0f)
))}){
// _0_1_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6089820265769958f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6062189936637878f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
12.8233003616333f, 10.55720043182373f, 0.0f, 1.0f)
))}){
// _0_1_1_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.512695f, 30.0f)
    lineTo(39.643234f, 30.0f)
    lineTo(39.643234f, 19.627375f)
    lineTo(5.512695f, 19.627375f)
    lineTo(5.512695f, 30.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(133, 149, 188, 255), 1.0f to Color(4, 26, 59, 255), start = Offset(22.149822f, 17.67732f), end = Offset(22.149822f, 31.652668f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.512695f, 5.679136f)
    lineTo(39.643234f, 5.679136f)
    lineTo(39.643234f, 19.627375f)
    lineTo(5.512695f, 19.627375f)
    lineTo(5.512695f, 5.679136f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1892169713974f, 0.0f, 0.0f, 0.0f,
0.0f, 1.1892169713974f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.525355100631714f, -6.535408020019531f, 0.0f, 1.0f)
))}){
// _0_1_1_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0
alphaStack.add(0, alpha)
alpha *= 0.04999994f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.4f, 15.4f)
    cubicTo(18.4f, 17.6f, 16.6f, 19.5f, 14.3f, 19.5f)
    cubicTo(12.1f, 19.5f, 10.2f, 17.7f, 10.2f, 15.4f)
    cubicTo(10.2f, 13.2f, 12.0f, 11.3f, 14.3f, 11.3f)
    cubicTo(16.5f, 11.3f, 18.4f, 13.1f, 18.4f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(232, 245, 47, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.20829993f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.0f, 15.4f)
    cubicTo(18.0f, 17.4f, 16.4f, 19.1f, 14.3f, 19.1f)
    cubicTo(12.3f, 19.1f, 10.6f, 17.5f, 10.6f, 15.4f)
    cubicTo(10.6f, 13.4f, 12.2f, 11.7f, 14.3f, 11.7f)
    cubicTo(16.3f, 11.7f, 18.0f, 13.3f, 18.0f, 15.4f)
    lineTo(18.0f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(236, 247, 81, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36669993f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.6f, 15.4f)
    cubicTo(17.6f, 17.2f, 16.1f, 18.7f, 14.3f, 18.7f)
    cubicTo(12.5f, 18.7f, 11.0f, 17.2f, 11.0f, 15.4f)
    cubicTo(11.0f, 13.6f, 12.5f, 12.1f, 14.3f, 12.1f)
    cubicTo(16.1f, 12.1f, 17.6f, 13.6f, 17.6f, 15.4f)
    lineTo(17.6f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(240, 249, 114, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.525f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.2f, 15.4f)
    cubicTo(17.2f, 17.0f, 15.9f, 18.3f, 14.3f, 18.3f)
    cubicTo(12.7f, 18.3f, 11.4f, 17.0f, 11.4f, 15.4f)
    cubicTo(11.4f, 13.8f, 12.7f, 12.5f, 14.3f, 12.5f)
    cubicTo(15.9f, 12.5f, 17.2f, 13.8f, 17.2f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(244, 250, 149, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6833f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.8f, 15.4f)
    cubicTo(16.8f, 16.8f, 15.7f, 17.9f, 14.3f, 17.9f)
    cubicTo(12.9f, 17.9f, 11.8f, 16.8f, 11.8f, 15.4f)
    cubicTo(11.8f, 14.0f, 12.9f, 12.9f, 14.3f, 12.9f)
    cubicTo(15.7f, 12.9f, 16.8f, 14.0f, 16.8f, 15.4f)
    lineTo(16.8f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(247, 252, 183, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8417f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.4f, 15.4f)
    cubicTo(16.4f, 16.6f, 15.4f, 17.5f, 14.3f, 17.5f)
    cubicTo(13.2f, 17.5f, 12.2f, 16.5f, 12.2f, 15.4f)
    cubicTo(12.2f, 14.3f, 13.2f, 13.3f, 14.3f, 13.3f)
    cubicTo(15.4f, 13.3f, 16.4f, 14.3f, 16.4f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(251, 253, 219, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_2_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.0f, 15.4f)
    cubicTo(16.0f, 16.4f, 15.2f, 17.2f, 14.2f, 17.2f)
    cubicTo(13.2f, 17.2f, 12.4f, 16.4f, 12.4f, 15.4f)
    cubicTo(12.4f, 14.4f, 13.2f, 13.6f, 14.2f, 13.6f)
    cubicTo(15.2f, 13.6f, 16.0f, 14.4f, 16.0f, 15.4f)
    lineTo(16.0f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.01586f, 21.649044f)
    lineTo(33.697147f, 21.649044f)
    lineTo(35.362053f, 22.124731f)
    lineTo(32.50793f, 22.124731f)
    cubicTo(32.50793f, 22.124731f, 35.362053f, 22.362574f, 36.789116f, 24.1464f)
    cubicTo(38.216175f, 25.811304f, 35.12421f, 27.832975f, 35.12421f, 27.832975f)
    cubicTo(35.12421f, 27.832975f, 35.12421f, 27.832975f, 35.12421f, 27.832975f)
    cubicTo(35.005287f, 27.47621f, 34.291756f, 24.622087f, 32.864697f, 23.43287f)
    cubicTo(31.7944f, 22.481497f, 30.605183f, 22.243652f, 30.605183f, 22.243652f)
    lineTo(25.01586f, 22.243652f)
    lineTo(25.01586f, 21.767965f)
    lineTo(25.01586f, 21.649044f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.724106f, 22.362574f)
    lineTo(25.729391f, 22.362574f)
    lineTo(35.005287f, 27.59513f)
    lineTo(30.724106f, 22.362574f)
    lineTo(30.724106f, 22.362574f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.01586f, 21.767965f)
    lineTo(33.697147f, 21.767965f)
    lineTo(35.005287f, 20.935513f)
    lineTo(32.15117f, 20.935513f)
    cubicTo(32.15117f, 20.935513f, 34.767445f, 20.459827f, 35.12421f, 17.486782f)
    cubicTo(35.480972f, 14.513739f, 31.08087f, 11.183931f, 31.08087f, 11.183931f)
    cubicTo(31.08087f, 11.183931f, 31.08087f, 11.183931f, 31.08087f, 11.302853f)
    cubicTo(31.19979f, 12.016383f, 32.389008f, 17.011095f, 31.556557f, 18.913845f)
    cubicTo(31.19979f, 20.578747f, 30.129496f, 20.935513f, 30.129496f, 20.935513f)
    lineTo(24.659094f, 20.935513f)
    lineTo(24.896938f, 21.767965f)
    lineTo(25.01586f, 21.767965f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(81, 81, 81, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.248419f, 20.459827f)
    lineTo(25.253704f, 20.459827f)
    lineTo(31.19979f, 11.421773f)
    lineTo(30.248419f, 20.459827f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(81, 81, 81, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1_1
brush = SolidColor(Color(158, 158, 158, 255))
stroke = Stroke(width=0.9999986f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 16.508501052856445f, top = 14.48575210571289f, right = 36.50400352478027f, bottom = 28.48321533203125f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(248, 248, 248, 255), center = Offset(11.238739f, 8.152492f), radius = 36.948036f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.666053771972656f, top = 4.583946228027344f, right = 40.44194030761719f, bottom = 43.530330657958984f,radiusX = 0.2980971336364746f, radiusY = 0.2980971336364746f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 0.6011236f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.0f, 1.0f, 0.0f, 1.0f)
))}){
// _0_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_0
shape = Outline.Rectangle(rect = Rect(left = 15.0f, top = 7.0f, right = 17.0f, bottom = 8.99999988079071f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_1
shape = Outline.Rectangle(rect = Rect(left = 37.0f, top = 7.0f, right = 39.0f, bottom = 8.99999988079071f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_2
shape = Outline.Rectangle(rect = Rect(left = 37.0f, top = 29.0f, right = 39.0f, bottom = 30.99999988079071f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_3
shape = Outline.Rectangle(rect = Rect(left = 15.0f, top = 29.0f, right = 17.0f, bottom = 30.99999988079071f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_4
shape = Outline.Rectangle(rect = Rect(left = 15.11611557006836f, top = 18.0f, right = 16.11611557006836f, bottom = 19.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_5
shape = Outline.Rectangle(rect = Rect(left = 38.0f, top = 18.0f, right = 39.0f, bottom = 19.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
2.3581399319511253E-18f, 1.0f, 0.0f, 0.0f,
-1.0f, 2.3581399319511253E-18f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_2_0_6
shape = Outline.Rectangle(rect = Rect(left = 7.0f, top = -28.0f, right = 8.0f, bottom = -27.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
2.3581399319511253E-18f, 1.0f, 0.0f, 0.0f,
-1.0f, 2.3581399319511253E-18f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_2_0_7
shape = Outline.Rectangle(rect = Rect(left = 29.88388442993164f, top = -28.0f, right = 30.88388442993164f, bottom = -27.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9746840000152588f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9746840000152588f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.412975013256073f, 2.1835439205169678f, 0.0f, 1.0f)
))}){
// _0_2_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.0f, 17.125f)
    cubicTo(38.015823f, 20.663519f, 36.13713f, 23.940037f, 33.07529f, 25.713879f)
    cubicTo(30.013456f, 27.487722f, 26.236544f, 27.487722f, 23.174707f, 25.713879f)
    cubicTo(20.112871f, 23.940037f, 18.234175f, 20.663519f, 18.25f, 17.125f)
    cubicTo(18.234175f, 13.586482f, 20.112871f, 10.309964f, 23.174707f, 8.536121f)
    cubicTo(26.236544f, 6.762278f, 30.013456f, 6.762278f, 33.07529f, 8.536121f)
    cubicTo(36.13713f, 10.309964f, 38.015823f, 13.586482f, 38.0f, 17.125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(115, 210, 22, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(78, 154, 6, 255))
stroke = Stroke(width=1.025974f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.0f, 17.125f)
    cubicTo(38.015823f, 20.663519f, 36.13713f, 23.940037f, 33.07529f, 25.713879f)
    cubicTo(30.013456f, 27.487722f, 26.236544f, 27.487722f, 23.174707f, 25.713879f)
    cubicTo(20.112871f, 23.940037f, 18.234175f, 20.663519f, 18.25f, 17.125f)
    cubicTo(18.234175f, 13.586482f, 20.112871f, 10.309964f, 23.174707f, 8.536121f)
    cubicTo(26.236544f, 6.762278f, 30.013456f, 6.762278f, 33.07529f, 8.536121f)
    cubicTo(36.13713f, 10.309964f, 38.015823f, 13.586482f, 38.0f, 17.125f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28089887f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8734170198440552f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8734170198440552f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.435149908065796f, 3.917736053466797f, 0.0f, 1.0f)
))}){
// _0_2_0_9
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(28.125f, 9.539848f), end = Offset(29.341478f, 40.882267f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1449286f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.0f, 17.125f)
    cubicTo(38.015823f, 20.663519f, 36.13713f, 23.940037f, 33.07529f, 25.713879f)
    cubicTo(30.013456f, 27.487722f, 26.236544f, 27.487722f, 23.174707f, 25.713879f)
    cubicTo(20.112871f, 23.940037f, 18.234175f, 20.663519f, 18.25f, 17.125f)
    cubicTo(18.234175f, 13.586482f, 20.112871f, 10.309964f, 23.174707f, 8.536121f)
    cubicTo(26.236544f, 6.762278f, 30.013456f, 6.762278f, 33.07529f, 8.536121f)
    cubicTo(36.13713f, 10.309964f, 38.015823f, 13.586482f, 38.0f, 17.125f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)

}
}



    private fun innerPaint(drawScope: DrawScope) {
	    _paint0(drawScope)


	    shape = null
	    generalPath = null
	    brush = null
	    stroke = null
	    clip = null
	    alpha = 1.0f
	}
	
    companion object {
        /**
         * Returns the X of the bounding box of the original SVG image.
         *
         * @return The X of the bounding box of the original SVG image.
         */
        fun getOrigX(): Double {
            return 2.750002861022949
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.1464462280273438
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 42.74999237060547
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.844627380371094
        }

        
    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        clipRect {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = size.width / fullOrigWidth
            val coef2 = size.height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()

            // Use the original icon bounding box and the current icon dimension to compute
            // the offset pivot for the scaling
            var translateX = -getOrigX()
            var translateY = -getOrigY()
            if (coef1 != coef2) {
                if (coef1 < coef2) {
                    val extraDy = ((fullOrigWidth - fullOrigHeight) / 2.0f).toFloat()
                    translateY += extraDy
                } else {
                    val extraDx = ((fullOrigHeight - fullOrigWidth) / 2.0f).toFloat()
                    translateX += extraDx
                }
            }
            val translateXDp = translateX.toFloat().toDp().value
            val translateYDp = translateY.toFloat().toDp().value

            // Create a combined scale + translate + clip transform before calling the transcoded painting instructions
            withTransform({
                scale(scaleX = coef, scaleY = coef, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

