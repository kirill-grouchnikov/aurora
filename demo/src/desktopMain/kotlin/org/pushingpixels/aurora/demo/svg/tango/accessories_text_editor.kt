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
class accessories_text_editor : Painter() {
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
0.02417561039328575f, 0.0f, 0.0f, 0.0f,
0.0f, 0.02086758054792881f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
45.12765121459961f, 40.15359878540039f, 0.0f, 1.0f)
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
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.16387f, 4.5063725f)
    lineTo(39.81312f, 4.5063725f)
    cubicTo(40.5757f, 4.5063725f, 41.189613f, 5.038824f, 41.189613f, 5.70021f)
    cubicTo(41.189613f, 5.70021f, 43.590946f, 39.868908f, 43.590946f, 39.868908f)
    cubicTo(43.590946f, 39.868908f, 43.6034f, 42.21653f, 43.6034f, 42.21653f)
    cubicTo(43.6034f, 42.877914f, 42.989487f, 43.410366f, 42.22691f, 43.410366f)
    lineTo(4.750081f, 43.410366f)
    cubicTo(3.9875042f, 43.410366f, 3.3735888f, 42.877914f, 3.3735888f, 42.21653f)
    lineTo(3.3624172f, 40.049614f)
    lineTo(5.7873774f, 5.70021f)
    cubicTo(5.7873774f, 5.038824f, 6.4012933f, 4.5063725f, 7.16387f, 4.5063725f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(223, 223, 223, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(21.283886f, 42.83337f), end = Offset(13.592059f, 6.8333683f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(147, 147, 147, 255), 1.0f to Color(66, 66, 66, 255), start = Offset(26.612417f, 28.083368f), end = Offset(26.228401f, 42.83337f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.16387f, 4.5063725f)
    lineTo(39.81312f, 4.5063725f)
    cubicTo(40.5757f, 4.5063725f, 41.189613f, 5.038824f, 41.189613f, 5.70021f)
    cubicTo(41.189613f, 5.70021f, 43.590946f, 39.868908f, 43.590946f, 39.868908f)
    cubicTo(43.590946f, 39.868908f, 43.6034f, 42.21653f, 43.6034f, 42.21653f)
    cubicTo(43.6034f, 42.877914f, 42.989487f, 43.410366f, 42.22691f, 43.410366f)
    lineTo(4.750081f, 43.410366f)
    cubicTo(3.9875042f, 43.410366f, 3.3735888f, 42.877914f, 3.3735888f, 42.21653f)
    lineTo(3.3624172f, 40.049614f)
    lineTo(5.7873774f, 5.70021f)
    cubicTo(5.7873774f, 5.038824f, 6.4012933f, 4.5063725f, 7.16387f, 4.5063725f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31578943f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.61661297082901f, 0.0f, 0.0f, 0.0f,
0.0f, 0.4403670132160187f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
10.614250183105469f, 13.942660331726074f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.125f, 40.4375f)
    cubicTo(43.15635f, 42.878628f, 39.434628f, 45.13901f, 33.36909f, 46.36274f)
    cubicTo(27.303555f, 47.586468f, 19.821445f, 47.586468f, 13.755908f, 46.36274f)
    cubicTo(7.690371f, 45.13901f, 3.9686508f, 42.878628f, 4.0f, 40.4375f)
    cubicTo(3.9686508f, 37.996372f, 7.690371f, 35.73599f, 13.755908f, 34.51226f)
    cubicTo(19.821445f, 33.288532f, 27.303555f, 33.288532f, 33.36909f, 34.51226f)
    cubicTo(39.434628f, 35.73599f, 43.15635f, 37.996372f, 43.125f, 40.4375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(23.5625f, 40.437508f), radius = 19.5625f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
shape = Outline.Rounded(roundRect = RoundRect(left = 3.977037191390991f, top = 39.86827087402344f, right = 43.02511382102966f, bottom = 42.93976593017578f,radiusX = 1.3587572574615479f, radiusY = 1.3587572574615479f))
brush = SolidColor(Color(164, 164, 164, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.9267507f, 40.442795f)
    cubicTo(3.9267507f, 40.442795f, 4.0776124f, 39.912464f, 4.6307726f, 39.86827f)
    lineTo(42.195374f, 39.86827f)
    cubicTo(42.949684f, 39.86827f, 42.99997f, 40.61957f, 42.99997f, 40.61957f)
    cubicTo(42.99997f, 40.61957f, 43.02357f, 39.0f, 41.7161f, 39.0f)
    lineTo(5.304216f, 39.0f)
    cubicTo(4.29847f, 39.088387f, 3.9267507f, 39.779884f, 3.9267507f, 40.442795f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.25f, 5.734375f)
    lineTo(6.0f, 10.125f)
    cubicTo(6.0f, 10.125f, 6.3125f, 9.0f, 7.0f, 9.0f)
    lineTo(40.125f, 9.0f)
    cubicTo(40.828125f, 8.984375f, 40.859375f, 9.3125f, 40.984375f, 9.828125f)
    cubicTo(40.984375f, 9.828125f, 40.734375f, 5.953125f, 40.734375f, 5.953125f)
    cubicTo(40.703125f, 5.40625f, 40.515625f, 5.0f, 39.953125f, 5.0f)
    lineTo(7.0625f, 5.0f)
    cubicTo(6.609375f, 5.0f, 6.296875f, 5.34375f, 6.25f, 5.734375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(163, 164, 160, 255), 1.0f to Color(136, 138, 133, 255), start = Offset(6.0f, 7.5625f), end = Offset(40.984375f, 7.5625f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4385965f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.99999946f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.8126473f, 5.54045f)
    lineTo(38.944984f, 5.54045f)
    cubicTo(39.66702f, 5.54045f, 40.2483f, 5.388346f, 40.2483f, 6.014572f)
    cubicTo(40.2483f, 6.014572f, 42.521973f, 39.023075f, 42.521973f, 39.023075f)
    cubicTo(42.521973f, 39.023075f, 42.622154f, 41.732033f, 42.622154f, 41.732033f)
    cubicTo(42.622154f, 42.358257f, 42.48282f, 42.37627f, 41.76078f, 42.37627f)
    lineTo(4.8620443f, 42.37627f)
    cubicTo(4.449366f, 42.37627f, 4.442611f, 42.26987f, 4.442611f, 41.864616f)
    lineTo(4.432034f, 39.194176f)
    lineTo(6.7280807f, 6.045822f)
    cubicTo(6.7280807f, 5.419596f, 7.09061f, 5.54045f, 7.8126473f, 5.54045f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_0
shape = Outline.Rounded(roundRect = RoundRect(left = 8.5f, top = 2.5f, right = 10.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 8.5f, top = 2.5f, right = 10.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_1
shape = Outline.Rounded(roundRect = RoundRect(left = 12.5f, top = 2.5f, right = 14.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 12.5f, top = 2.5f, right = 14.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_2
shape = Outline.Rounded(roundRect = RoundRect(left = 16.5f, top = 2.5f, right = 18.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 16.5f, top = 2.5f, right = 18.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_3
shape = Outline.Rounded(roundRect = RoundRect(left = 20.5f, top = 2.5f, right = 22.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 20.5f, top = 2.5f, right = 22.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_4
shape = Outline.Rounded(roundRect = RoundRect(left = 24.5f, top = 2.5f, right = 26.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 24.5f, top = 2.5f, right = 26.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_5
shape = Outline.Rounded(roundRect = RoundRect(left = 28.5f, top = 2.5f, right = 30.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 28.5f, top = 2.5f, right = 30.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_6
shape = Outline.Rounded(roundRect = RoundRect(left = 32.5f, top = 2.5f, right = 34.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 32.5f, top = 2.5f, right = 34.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_7
shape = Outline.Rounded(roundRect = RoundRect(left = 36.5f, top = 2.5f, right = 38.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
brush = SolidColor(Color(252, 233, 79, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 111, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 36.5f, top = 2.5f, right = 38.5f, bottom = 7.5f,radiusX = 2.0f, radiusY = 2.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_0
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 12.0f, right = 38.0f, bottom = 13.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_1
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 14.981792449951172f, right = 38.0f, bottom = 15.981792449951172f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_2
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 18.003938674926758f, right = 22.0f, bottom = 19.003938674926758f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_3
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 22.98573112487793f, right = 38.0f, bottom = 23.98573112487793f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_4
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 26.007877349853516f, right = 38.0f, bottom = 27.007877349853516f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_5
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 29.0300235748291f, right = 38.0f, bottom = 30.0300235748291f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28070176f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8_6
shape = Outline.Rectangle(rect = Rect(left = 9.0f, top = 32.05216979980469f, right = 17.0f, bottom = 33.05216979980469f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.34116f, 32.5f)
    lineTo(22.96616f, 26.875f)
    lineTo(43.05991f, 17.125f)
    cubicTo(46.30991f, 15.875f, 48.24741f, 20.5f, 45.37241f, 22.125f)
    lineTo(25.34116f, 31.5f)
    lineTo(17.34116f, 32.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(203, 144, 34, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(92, 65, 12, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.34116f, 32.5f)
    lineTo(22.96616f, 26.875f)
    lineTo(43.05991f, 17.125f)
    cubicTo(46.30991f, 15.875f, 48.24741f, 20.5f, 45.37241f, 22.125f)
    lineTo(25.34116f, 31.5f)
    lineTo(17.34116f, 32.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.330708f, 20.0f)
    cubicTo(38.330708f, 20.0f, 39.768208f, 20.09375f, 40.330708f, 21.34375f)
    cubicTo(40.910202f, 22.631512f, 40.330708f, 24.0f, 40.330708f, 24.0f)
    lineTo(45.361958f, 21.53125f)
    cubicTo(45.361958f, 21.53125f, 46.81399f, 20.649883f, 46.018208f, 18.6875f)
    cubicTo(45.233295f, 16.751923f, 43.330708f, 17.53125f, 43.330708f, 17.53125f)
    lineTo(38.330708f, 20.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 209, 209, 255), 0.5f to Color(255, 29, 29, 255), 1.0f to Color(111, 0, 0, 255), start = Offset(43.236958f, 17.376184f), end = Offset(45.319042f, 22.250591f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.330708f, 20.0f)
    cubicTo(38.330708f, 20.0f, 39.768208f, 20.09375f, 40.330708f, 21.34375f)
    cubicTo(40.910202f, 22.631512f, 40.330708f, 24.0f, 40.330708f, 24.0f)
    lineTo(42.330708f, 23.0f)
    cubicTo(42.330708f, 23.0f, 43.15774f, 21.681133f, 42.549458f, 20.3125f)
    cubicTo(41.924458f, 18.90625f, 40.330708f, 19.0f, 40.330708f, 19.0f)
    lineTo(38.330708f, 20.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(193, 193, 193, 255), 1.0f to Color(172, 172, 172, 255), start = Offset(40.330708f, 19.8125f), end = Offset(42.018208f, 22.625f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.768208f, 31.78125f)
    lineTo(23.268208f, 27.28125f)
    cubicTo(24.768208f, 28.09375f, 25.549458f, 29.4375f, 25.143208f, 31.0f)
    lineTo(18.768208f, 31.78125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(231, 226, 184, 255), 1.0f to Color(231, 226, 184, 0), center = Offset(23.384048f, 28.21873f), radius = 9.474846f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.111958f, 30.375f)
    lineTo(18.486958f, 31.96875f)
    lineTo(20.830708f, 31.65625f)
    cubicTo(21.049458f, 30.9375f, 20.643208f, 30.59375f, 20.111958f, 30.375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(201, 201, 201, 255), start = Offset(19.893208f, 31.171875f), end = Offset(19.689047f, 30.828125f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.268208f, 27.25f)
    lineTo(24.830708f, 28.5f)
    lineTo(40.21805f, 21.18133f)
    cubicTo(39.773617f, 20.325287f, 38.97628f, 20.096733f, 38.31467f, 20.019068f)
    lineTo(23.268208f, 27.25f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 93))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.143208f, 31.0625f)
    lineTo(25.330708f, 30.3125f)
    lineTo(40.5618f, 23.1829f)
    cubicTo(40.5618f, 23.1829f, 40.451637f, 23.796528f, 40.34592f, 23.93225f)
    lineTo(25.143208f, 31.0625f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 93))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 0.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 2.0
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 48.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.99107360839844
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

