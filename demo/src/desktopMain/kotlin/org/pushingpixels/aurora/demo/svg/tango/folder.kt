package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.toComposeBitmap
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class folder private constructor(var _width: Dp, var _height: Dp) : AuroraIcon() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)
    private var colorFilter: ((Color) -> Color)? = null

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.022623829543590546f, 0.0f, 0.0f, 0.0f,
0.0f, 0.02086758054792881f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
43.38343048095703f, 36.36962127685547f, 0.0f, 1.0f)
))}){
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_0
shape = Outline.Rectangle(rect = Rect(left = -1559.2523193359375f, top = -150.6968536376953f, right = -219.6187744140625f, bottom = 327.6603240966797f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), 0.5f to (colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), start = Offset(-1051.9354f, -150.69684f), end = Offset(-1051.9354f, 327.6604f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-219.61876f, -150.68037f)
generalPath!!.cubicTo(-219.61876f, -150.68037f, -219.61876f, 327.65042f, -219.61876f, 327.65042f)
generalPath!!.cubicTo(-76.74459f, 328.55087f, 125.78146f, 220.48074f, 125.78138f, 88.45424f)
generalPath!!.cubicTo(125.78138f, -43.572304f, -33.655437f, -150.68036f, -219.61876f, -150.68037f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), center = Offset(-211.146f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-1559.2523f, -150.68037f)
generalPath!!.cubicTo(-1559.2523f, -150.68037f, -1559.2523f, 327.65042f, -1559.2523f, 327.65042f)
generalPath!!.cubicTo(-1702.1265f, 328.55087f, -1904.6525f, 220.48074f, -1904.6525f, 88.45424f)
generalPath!!.cubicTo(-1904.6525f, -43.572304f, -1745.2157f, -150.68036f, -1559.2523f, -150.68037f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), center = Offset(-1567.7247f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.5217805f, 38.687416f)
generalPath!!.cubicTo(4.5435767f, 39.10372f, 4.981685f, 39.520027f, 5.39799f, 39.520027f)
generalPath!!.lineTo(36.72501f, 39.520027f)
generalPath!!.cubicTo(37.14131f, 39.520027f, 37.535824f, 39.10372f, 37.514027f, 38.687416f)
generalPath!!.lineTo(36.577583f, 11.460682f)
generalPath!!.cubicTo(36.555786f, 11.044379f, 36.117687f, 10.628066f, 35.70138f, 10.628066f)
generalPath!!.lineTo(22.43051f, 10.628066f)
generalPath!!.cubicTo(21.945454f, 10.628066f, 21.196037f, 10.312477f, 21.028866f, 9.521434f)
generalPath!!.lineTo(20.417475f, 6.6283627f)
generalPath!!.cubicTo(20.262007f, 5.8926897f, 19.535261f, 5.5904765f, 19.118958f, 5.5904765f)
generalPath!!.lineTo(4.3400974f, 5.5904765f)
generalPath!!.cubicTo(3.9237847f, 5.5904765f, 3.5292766f, 6.0067806f, 3.5510726f, 6.4230847f)
generalPath!!.lineTo(4.5217805f, 38.687416f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(32, 32, 32, 255)) ?: Color(32, 32, 32, 255)), 1.0f to (colorFilter?.invoke(Color(185, 185, 185, 255)) ?: Color(185, 185, 185, 255)), center = Offset(24.940186f, 36.98975f), radius = 32.61662f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(66, 66, 66, 255)) ?: Color(66, 66, 66, 255)), 1.0f to (colorFilter?.invoke(Color(119, 119, 119, 255)) ?: Color(119, 119, 119, 255)), start = Offset(18.112709f, 31.36775f), end = Offset(15.514889f, 6.18025f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.5217805f, 38.687416f)
generalPath!!.cubicTo(4.5435767f, 39.10372f, 4.981685f, 39.520027f, 5.39799f, 39.520027f)
generalPath!!.lineTo(36.72501f, 39.520027f)
generalPath!!.cubicTo(37.14131f, 39.520027f, 37.535824f, 39.10372f, 37.514027f, 38.687416f)
generalPath!!.lineTo(36.577583f, 11.460682f)
generalPath!!.cubicTo(36.555786f, 11.044379f, 36.117687f, 10.628066f, 35.70138f, 10.628066f)
generalPath!!.lineTo(22.43051f, 10.628066f)
generalPath!!.cubicTo(21.945454f, 10.628066f, 21.196037f, 10.312477f, 21.028866f, 9.521434f)
generalPath!!.lineTo(20.417475f, 6.6283627f)
generalPath!!.cubicTo(20.262007f, 5.8926897f, 19.535261f, 5.5904765f, 19.118958f, 5.5904765f)
generalPath!!.lineTo(4.3400974f, 5.5904765f)
generalPath!!.cubicTo(3.9237847f, 5.5904765f, 3.5292766f, 6.0067806f, 3.5510726f, 6.4230847f)
generalPath!!.lineTo(4.5217805f, 38.687416f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.2265925f, 22.5625f)
generalPath!!.lineTo(35.492172f, 22.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.2265925f, 22.5625f)
generalPath!!.lineTo(35.492172f, 22.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0421734f, 18.5625f)
generalPath!!.lineTo(35.489105f, 18.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0421734f, 18.5625f)
generalPath!!.lineTo(35.489105f, 18.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.9806967f, 12.5625f)
generalPath!!.lineTo(35.488056f, 12.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.9806967f, 12.5625f)
generalPath!!.lineTo(35.488056f, 12.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.3861575f, 32.5625f)
generalPath!!.lineTo(35.49488f, 32.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.3861575f, 32.5625f)
generalPath!!.lineTo(35.49488f, 32.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.50914f, 34.5625f)
generalPath!!.lineTo(35.496895f, 34.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.50914f, 34.5625f)
generalPath!!.lineTo(35.496895f, 34.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0421734f, 16.5625f)
generalPath!!.lineTo(35.489105f, 16.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0421734f, 16.5625f)
generalPath!!.lineTo(35.489105f, 16.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0114346f, 14.5625f)
generalPath!!.lineTo(35.48858f, 14.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0114346f, 14.5625f)
generalPath!!.lineTo(35.48858f, 14.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.9220967f, 10.5625f)
generalPath!!.lineTo(20.202911f, 10.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.9220967f, 10.5625f)
generalPath!!.lineTo(20.202911f, 10.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.8737535f, 8.5625f)
generalPath!!.lineTo(19.657488f, 8.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(4.8737535f, 8.5625f)
generalPath!!.lineTo(19.657488f, 8.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.3246665f, 28.5625f)
generalPath!!.lineTo(35.493877f, 28.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.3246665f, 28.5625f)
generalPath!!.lineTo(35.493877f, 28.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.288064f, 26.5625f)
generalPath!!.lineTo(35.493183f, 26.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.288064f, 26.5625f)
generalPath!!.lineTo(35.493183f, 26.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.2265925f, 24.5625f)
generalPath!!.lineTo(35.492172f, 24.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.2265925f, 24.5625f)
generalPath!!.lineTo(35.492172f, 24.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.1958537f, 20.5625f)
generalPath!!.lineTo(35.49165f, 20.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.1958537f, 20.5625f)
generalPath!!.lineTo(35.49165f, 20.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.3246665f, 30.5625f)
generalPath!!.lineTo(35.493877f, 30.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.3246665f, 30.5625f)
generalPath!!.lineTo(35.493877f, 30.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.50914f, 36.5625f)
generalPath!!.lineTo(35.496895f, 36.5625f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.50914f, 36.5625f)
generalPath!!.lineTo(35.496895f, 36.5625f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.45142856f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(6.068343f, 38.86402f)
generalPath!!.cubicTo(6.084686f, 39.17625f, 5.8874316f, 39.384403f, 5.5697584f, 39.280327f)
generalPath!!.lineTo(5.5697584f, 39.280327f)
generalPath!!.cubicTo(5.2520766f, 39.17625f, 5.033027f, 38.968098f, 5.0166755f, 38.65587f)
generalPath!!.lineTo(4.068956f, 6.591384f)
generalPath!!.cubicTo(4.0526133f, 6.2791557f, 4.234142f, 6.0906134f, 4.54637f, 6.0906134f)
generalPath!!.lineTo(18.96842f, 6.0429196f)
generalPath!!.cubicTo(19.280647f, 6.0429196f, 19.900364f, 6.3433924f, 20.101357f, 7.3651013f)
generalPath!!.lineTo(20.674845f, 10.180636f)
generalPath!!.cubicTo(20.247791f, 9.715379f, 20.255651f, 9.701017f, 20.037287f, 9.02393f)
generalPath!!.lineTo(19.631191f, 7.764748f)
generalPath!!.cubicTo(19.412142f, 7.037101f, 18.932991f, 6.932848f, 18.620764f, 6.932848f)
generalPath!!.lineTo(5.732989f, 6.932848f)
generalPath!!.cubicTo(5.420761f, 6.932848f, 5.2235074f, 7.141f, 5.239858f, 7.4532366f)
generalPath!!.lineTo(6.1778636f, 38.968098f)
generalPath!!.lineTo(6.068343f, 38.86402f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 223)) ?: Color(255, 255, 255, 223)), 1.0f to (colorFilter?.invoke(Color(255, 255, 254, 0)) ?: Color(255, 255, 254, 0)), start = Offset(8.570057f, 8.446634f), end = Offset(14.134285f, 46.06571f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0407639741897583f, 0.0f, 0.0f, 0.0f,
0.0544925183057785f, 1.0407639741897583f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-8.670199394226074f, 2.6705939769744873f, 0.0f, 1.0f)
))}){
// _0_0_18
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_18_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.417183f, 8.515178f)
generalPath!!.cubicTo(42.422268f, 8.418064f, 42.28902f, 8.268189f, 42.182068f, 8.268171f)
generalPath!!.lineTo(29.150665f, 8.266053f)
generalPath!!.cubicTo(29.150665f, 8.266053f, 30.06238f, 8.854008f, 31.352476f, 8.862296f)
generalPath!!.lineTo(42.405975f, 8.933317f)
generalPath!!.cubicTo(42.41706f, 8.721589f, 42.408695f, 8.677284f, 42.417183f, 8.515178f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(255, 255, 255, 130)) ?: Color(255, 255, 255, 130))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(39.78353f, 39.51062f)
generalPath!!.cubicTo(40.927425f, 39.466557f, 41.74661f, 38.41432f, 41.830566f, 37.189613f)
generalPath!!.cubicTo(42.622353f, 25.640928f, 43.489925f, 15.957666f, 43.489925f, 15.957666f)
generalPath!!.cubicTo(43.56208f, 15.710182f, 43.322018f, 15.462699f, 43.00979f, 15.462699f)
generalPath!!.lineTo(8.638631f, 15.462699f)
generalPath!!.cubicTo(8.638631f, 15.462699f, 6.7883115f, 37.32959f, 6.7883115f, 37.32959f)
generalPath!!.cubicTo(6.673756f, 38.311657f, 6.322304f, 39.134308f, 5.2384753f, 39.513306f)
generalPath!!.lineTo(39.78353f, 39.51062f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(97, 148, 203, 255)) ?: Color(97, 148, 203, 255)), 1.0f to (colorFilter?.invoke(Color(114, 159, 207, 255)) ?: Color(114, 159, 207, 255)), start = Offset(22.175976f, 36.988f), end = Offset(22.06533f, 32.0505f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(52, 101, 164, 255)) ?: Color(52, 101, 164, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(39.78353f, 39.51062f)
generalPath!!.cubicTo(40.927425f, 39.466557f, 41.74661f, 38.41432f, 41.830566f, 37.189613f)
generalPath!!.cubicTo(42.622353f, 25.640928f, 43.489925f, 15.957666f, 43.489925f, 15.957666f)
generalPath!!.cubicTo(43.56208f, 15.710182f, 43.322018f, 15.462699f, 43.00979f, 15.462699f)
generalPath!!.lineTo(8.638631f, 15.462699f)
generalPath!!.cubicTo(8.638631f, 15.462699f, 6.7883115f, 37.32959f, 6.7883115f, 37.32959f)
generalPath!!.cubicTo(6.673756f, 38.311657f, 6.322304f, 39.134308f, 5.2384753f, 39.513306f)
generalPath!!.lineTo(39.78353f, 39.51062f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4659091f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_20
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 0)) ?: Color(255, 255, 255, 0)), start = Offset(16.294813f, 25.264992f), end = Offset(16.055128f, 36.792267f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(9.620244f, 16.46392f)
generalPath!!.lineTo(42.411343f, 16.528734f)
generalPath!!.lineTo(40.837296f, 36.530712f)
generalPath!!.cubicTo(40.752975f, 37.602226f, 40.38662f, 37.958927f, 38.96464f, 37.958927f)
generalPath!!.cubicTo(37.09314f, 37.958927f, 10.286673f, 37.92652f, 7.569899f, 37.92652f)
generalPath!!.cubicTo(7.8034973f, 37.605713f, 7.9036546f, 36.9379f, 7.9049954f, 36.92191f)
generalPath!!.lineTo(9.620244f, 16.46392f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_21
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(9.620248f, 16.223183f)
generalPath!!.lineTo(8.453602f, 31.866453f)
generalPath!!.cubicTo(8.453602f, 31.866453f, 16.749756f, 27.718374f, 27.11995f, 27.718374f)
generalPath!!.cubicTo(37.490143f, 27.718374f, 42.67524f, 16.223183f, 42.67524f, 16.223183f)
generalPath!!.lineTo(9.620248f, 16.223183f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(255, 255, 255, 23)) ?: Color(255, 255, 255, 23))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_1
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)

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
            return 0.2928977608680725
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 5.0904765129089355
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 45.93619155883789
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 38.13520431518555
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return folder(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return folder(getOrigWidth().dp, getOrigHeight().dp)
                }
            }
        }

        
    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        this.colorFilter = colorFilter
    }

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

