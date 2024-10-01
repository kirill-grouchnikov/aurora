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
class edit_paste : Painter() {
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
0.023252159357070923f, 0.0f, 0.0f, 0.0f,
0.0f, 0.014857430011034012f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
44.806270599365234f, 43.06039047241211f, 0.0f, 1.0f)
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
shape = Outline.Rounded(roundRect = RoundRect(left = 4.464317321777344f, top = 4.5f, right = 43.5f, bottom = 45.54543685913086f,radiusX = 2.775874137878418f, radiusY = 2.7758727073669434f))
brush = Brush.linearGradient(0.0f to Color(198, 136, 39, 255), 1.0f to Color(137, 96, 31, 255), start = Offset(6.1071744f, 10.45129f), end = Offset(33.857143f, 37.87986f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(113, 76, 22, 255))
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 4.464317321777344f, top = 4.5f, right = 43.5f, bottom = 45.54543685913086f,radiusX = 2.775874137878418f, radiusY = 2.7758727073669434f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
shape = Outline.Rounded(roundRect = RoundRect(left = 8.53232192993164f, top = 6.529515743255615f, right = 39.4838809967041f, bottom = 42.50620412826538f,radiusX = 1.1330167055130005f, radiusY = 1.1330167055130005f))
brush = Brush.linearGradient(0.0f to Color(240, 240, 239, 255), 0.59928656f to Color(232, 232, 232, 255), 0.82758623f to Color(255, 255, 255, 255), 1.0f to Color(216, 216, 211, 255), start = Offset(22.209503f, 18.831415f), end = Offset(36.57188f, 39.083138f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 8.53232192993164f, top = 6.529515743255615f, right = 39.4838809967041f, bottom = 42.50620412826538f,radiusX = 1.1330167055130005f, radiusY = 1.1330167055130005f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
shape = Outline.Rounded(roundRect = RoundRect(left = 18.0f, top = 0.0f, right = 30.0f, bottom = 4.0f,radiusX = 1.9677506685256958f, radiusY = 1.9677506685256958f))
brush = SolidColor(Color(92, 92, 92, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(26.076092f, 26.696676f), end = Offset(30.811172f, 42.00735f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 9.517141342163086f, top = 7.466585636138916f, right = 38.531288146972656f, bottom = 41.507349491119385f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
brush = SolidColor(Color(198, 136, 39, 255))
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.439342498779297f, top = 5.430777549743652f, right = 42.52499771118164f, bottom = 44.52376461029053f,radiusX = 0.9575969576835632f, radiusY = 0.9575969576835632f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.10795455f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6
shape = Outline.Rounded(roundRect = RoundRect(left = 14.791487693786621f, top = 4.472271919250488f, right = 33.738863945007324f, bottom = 11.472271919250488f,radiusX = 2.7758753299713135f, radiusY = 2.7758727073669434f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 14.791487693786621f, top = 4.472271919250488f, right = 33.738863945007324f, bottom = 11.472271919250488f,radiusX = 2.7758753299713135f, radiusY = 2.7758727073669434f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
shape = Outline.Rounded(roundRect = RoundRect(left = 14.526322364807129f, top = 3.5f, right = 33.47369861602783f, bottom = 10.5f,radiusX = 2.7758753299713135f, radiusY = 2.7758727073669434f))
brush = Brush.linearGradient(0.0f to Color(151, 151, 138, 255), 0.5f to Color(194, 194, 185, 255), 1.0f to Color(125, 125, 111, 255), start = Offset(24.95219f, 3.8180194f), end = Offset(25.014967f, 9.323351f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(92, 92, 92, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 14.526322364807129f, top = 3.5f, right = 33.47369861602783f, bottom = 10.5f,radiusX = 2.7758753299713135f, radiusY = 2.7758727073669434f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
shape = Outline.Rounded(roundRect = RoundRect(left = 19.151323318481445f, top = 1.20867919921875f, right = 28.84869956970215f, bottom = 4.7913196086883545f,radiusX = 0.6508727073669434f, radiusY = 0.6508727073669434f))
brush = Brush.linearGradient(0.0f to Color(151, 151, 138, 255), 0.5f to Color(194, 194, 185, 255), 1.0f to Color(125, 125, 111, 255), start = Offset(24.487335f, 1.3714452f), end = Offset(24.487335f, 2.734406f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
shape = Outline.Rounded(roundRect = RoundRect(left = 14.953014373779297f, top = 3.9375f, right = 33.047006607055664f, bottom = 10.125f,radiusX = 2.0258727073669434f, radiusY = 2.0258727073669434f))
brush = Brush.linearGradient(0.0f to Color(151, 151, 138, 255), 0.5f to Color(194, 194, 185, 255), 1.0f to Color(125, 125, 111, 255), start = Offset(24.90931f, 4.218604f), end = Offset(24.969261f, 9.084921f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48863637f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.018307f, 36.25f)
    lineTo(39.0625f, 42.0625f)
    lineTo(30.5625f, 42.018307f)
    lineTo(39.018307f, 36.25f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(36.8125f, 39.15625f), end = Offset(39.0625f, 42.0625f), tileMode = TileMode.Clamp)
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
    moveTo(30.059082f, 42.086864f)
    cubicTo(31.850224f, 42.254517f, 39.04881f, 37.717278f, 39.53992f, 33.698856f)
    cubicTo(37.97666f, 36.121952f, 34.584972f, 35.667446f, 30.476213f, 35.826454f)
    cubicTo(30.476213f, 35.826454f, 30.871582f, 41.586864f, 30.059082f, 42.086864f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(124, 124, 124, 255), 1.0f to Color(184, 184, 184, 255), start = Offset(35.996582f, 40.45822f), end = Offset(33.66492f, 37.77072f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(134, 138, 132, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.059082f, 42.086864f)
    cubicTo(31.850224f, 42.254517f, 39.04881f, 37.717278f, 39.53992f, 33.698856f)
    cubicTo(37.97666f, 36.121952f, 34.584972f, 35.667446f, 30.476213f, 35.826454f)
    cubicTo(30.476213f, 35.826454f, 30.871582f, 41.586864f, 30.059082f, 42.086864f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31681818f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(25.682829f, 12.172059f), end = Offset(25.69217f, -0.20294096f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999994f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.46875f, 1.46875f)
    cubicTo(19.466654f, 1.4708456f, 19.470413f, 1.4975336f, 19.46875f, 1.5f)
    cubicTo(19.46758f, 1.502776f, 19.438116f, 1.4969757f, 19.4375f, 1.5f)
    lineTo(19.4375f, 4.375f)
    cubicTo(19.4375f, 4.381423f, 19.46641f, 4.400698f, 19.46875f, 4.40625f)
    cubicTo(19.471216f, 4.4079137f, 19.465975f, 4.43633f, 19.46875f, 4.4375f)
    lineTo(15.9375f, 4.4375f)
    cubicTo(15.91974f, 4.4375f, 15.892285f, 4.4357553f, 15.875f, 4.4375f)
    cubicTo(15.840968f, 4.4426713f, 15.781454f, 4.4572763f, 15.75f, 4.46875f)
    cubicTo(15.611832f, 4.5269966f, 15.482328f, 4.66777f, 15.4375f, 4.8125f)
    cubicTo(15.426991f, 4.8535347f, 15.4375f, 4.924349f, 15.4375f, 4.96875f)
    lineTo(15.4375f, 9.125f)
    cubicTo(15.4375f, 9.14276f, 15.435755f, 9.170215f, 15.4375f, 9.1875f)
    cubicTo(15.442671f, 9.221532f, 15.457276f, 9.281046f, 15.46875f, 9.3125f)
    cubicTo(15.478458f, 9.335528f, 15.487176f, 9.3851f, 15.5f, 9.40625f)
    cubicTo(15.5046f, 9.41307f, 15.526336f, 9.430921f, 15.53125f, 9.4375f)
    cubicTo(15.552124f, 9.462814f, 15.599686f, 9.510377f, 15.625f, 9.53125f)
    cubicTo(15.638159f, 9.541079f, 15.6734f, 9.55395f, 15.6875f, 9.5625f)
    cubicTo(15.702038f, 9.570378f, 15.734648f, 9.587278f, 15.75f, 9.59375f)
    cubicTo(15.781454f, 9.605224f, 15.840968f, 9.619829f, 15.875f, 9.625f)
    cubicTo(15.892285f, 9.626745f, 15.91974f, 9.625f, 15.9375f, 9.625f)
    lineTo(32.0625f, 9.625f)
    cubicTo(32.08026f, 9.625f, 32.107716f, 9.626745f, 32.125f, 9.625f)
    cubicTo(32.15903f, 9.619829f, 32.218548f, 9.605224f, 32.25f, 9.59375f)
    cubicTo(32.26535f, 9.587278f, 32.297962f, 9.570378f, 32.3125f, 9.5625f)
    cubicTo(32.3266f, 9.55395f, 32.36184f, 9.541079f, 32.375f, 9.53125f)
    cubicTo(32.400314f, 9.510377f, 32.447876f, 9.462814f, 32.46875f, 9.4375f)
    cubicTo(32.473663f, 9.430921f, 32.4954f, 9.41307f, 32.5f, 9.40625f)
    cubicTo(32.512825f, 9.3851f, 32.52154f, 9.335528f, 32.53125f, 9.3125f)
    cubicTo(32.542725f, 9.281046f, 32.557327f, 9.221532f, 32.5625f, 9.1875f)
    cubicTo(32.564243f, 9.170215f, 32.5625f, 9.14276f, 32.5625f, 9.125f)
    lineTo(32.5625f, 4.96875f)
    cubicTo(32.5625f, 4.924349f, 32.57301f, 4.8535347f, 32.5625f, 4.8125f)
    cubicTo(32.517673f, 4.66777f, 32.38817f, 4.5269966f, 32.25f, 4.46875f)
    cubicTo(32.218548f, 4.4572763f, 32.15903f, 4.4426713f, 32.125f, 4.4375f)
    cubicTo(32.107716f, 4.4357553f, 32.08026f, 4.4375f, 32.0625f, 4.4375f)
    lineTo(28.53125f, 4.4375f)
    cubicTo(28.534025f, 4.43633f, 28.528784f, 4.4079137f, 28.53125f, 4.40625f)
    cubicTo(28.53359f, 4.400698f, 28.5625f, 4.381423f, 28.5625f, 4.375f)
    lineTo(28.5625f, 1.5f)
    cubicTo(28.561884f, 1.4969757f, 28.53242f, 1.502776f, 28.53125f, 1.5f)
    cubicTo(28.529587f, 1.4975336f, 28.533346f, 1.4708456f, 28.53125f, 1.46875f)
    cubicTo(28.528475f, 1.4675798f, 28.503023f, 1.4693657f, 28.5f, 1.46875f)
    lineTo(19.5f, 1.46875f)
    cubicTo(19.496977f, 1.4693657f, 19.471525f, 1.4675798f, 19.46875f, 1.46875f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3693182f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(33.396004f, 36.921333f), end = Offset(34.170048f, 38.07038f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.50952f, 40.68705f)
    cubicTo(32.8793f, 40.00322f, 36.038784f, 38.086018f, 37.338165f, 36.205013f)
    cubicTo(35.545643f, 36.581497f, 34.347244f, 36.794586f, 31.610577f, 36.900494f)
    cubicTo(31.610577f, 36.900494f, 31.697138f, 39.91208f, 31.50952f, 40.68705f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045455f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 15.0f, right = 35.0f, bottom = 17.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045455f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 19.0f, right = 34.0f, bottom = 21.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045455f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 23.0f, right = 32.0f, bottom = 25.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045455f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_17
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 27.0f, right = 35.0f, bottom = 29.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045455f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_18
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 31.0f, right = 27.0f, bottom = 33.0f))
brush = SolidColor(Color(0, 0, 0, 255))
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
            return 0.5189879536628723
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.0
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 47.211971282958984
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.92858123779297
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

