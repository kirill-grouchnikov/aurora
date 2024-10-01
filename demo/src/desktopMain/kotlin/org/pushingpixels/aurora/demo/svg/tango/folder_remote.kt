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
class folder_remote : Painter() {
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
// _0_0_0
shape = Outline.Rectangle(rect = Rect(left = 5.457477569580078f, top = 40.52276611328125f, right = 42.0f, bottom = 44.52276611328125f))
brush = Brush.linearGradient(0.0f to Color(104, 104, 104, 0), 0.23762377f to Color(104, 104, 104, 255), 0.7810999f to Color(104, 104, 104, 255), 1.0f to Color(104, 104, 104, 0), start = Offset(4.957491f, 42.522808f), end = Offset(42.500015f, 42.522808f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
shape = Outline.Rectangle(rect = Rect(left = 5.457477569580078f, top = 44.0550537109375f, right = 42.0f, bottom = 45.044646084308624f))
brush = Brush.linearGradient(0.0f to Color(71, 71, 71, 0), 0.1f to Color(71, 71, 71, 255), 0.9f to Color(71, 71, 71, 255), 1.0f to Color(71, 71, 71, 0), start = Offset(5.4574914f, 44.54975f), end = Offset(42.00002f, 44.54975f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
shape = Outline.Rectangle(rect = Rect(left = 5.457477569580078f, top = 40.0f, right = 42.0f, bottom = 41.02274703979492f))
brush = Brush.linearGradient(0.0f to Color(71, 71, 71, 0), 0.1f to Color(71, 71, 71, 255), 0.9f to Color(71, 71, 71, 255), 1.0f to Color(71, 71, 71, 0), start = Offset(5.4574914f, 48.61103f), end = Offset(42.00002f, 48.61103f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.83707863f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
shape = Outline.Rectangle(rect = Rect(left = 5.457477569580078f, top = 41.55805969238281f, right = 42.0f, bottom = 43.000001430511475f))
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.10827128f to Color(255, 255, 255, 180), 0.920539f to Color(255, 255, 255, 180), 1.0f to Color(255, 255, 255, 0), start = Offset(4.957491f, 42.279152f), end = Offset(42.500015f, 42.279152f), tileMode = TileMode.Clamp)
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
    moveTo(21.923477f, 29.991507f)
    cubicTo(21.139997f, 29.991507f, 20.517227f, 30.614277f, 20.517227f, 31.397757f)
    lineTo(20.517227f, 38.491505f)
    lineTo(16.892227f, 38.491505f)
    cubicTo(16.108747f, 38.491505f, 15.485978f, 39.114277f, 15.485978f, 39.897755f)
    lineTo(15.485978f, 44.116505f)
    cubicTo(15.485978f, 44.899975f, 16.108747f, 45.522755f, 16.892227f, 45.522755f)
    lineTo(30.079727f, 45.522755f)
    cubicTo(30.863197f, 45.522755f, 31.485977f, 44.899975f, 31.485977f, 44.116505f)
    lineTo(31.485977f, 39.897755f)
    cubicTo(31.485977f, 39.114277f, 30.863197f, 38.491505f, 30.079727f, 38.491505f)
    lineTo(27.048477f, 38.491505f)
    lineTo(27.048477f, 31.397757f)
    cubicTo(27.048477f, 30.614277f, 26.425697f, 29.991507f, 25.642227f, 29.991507f)
    lineTo(21.923477f, 29.991507f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(122, 122, 122, 255), 0.1980198f to Color(197, 197, 197, 255), 0.5990099f to Color(98, 98, 98, 255), 1.0f to Color(136, 136, 136, 255), start = Offset(23.18334f, 39.450085f), end = Offset(23.18334f, 45.161324f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(67, 67, 67, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(21.923477f, 29.991507f)
    cubicTo(21.139997f, 29.991507f, 20.517227f, 30.614277f, 20.517227f, 31.397757f)
    lineTo(20.517227f, 38.491505f)
    lineTo(16.892227f, 38.491505f)
    cubicTo(16.108747f, 38.491505f, 15.485978f, 39.114277f, 15.485978f, 39.897755f)
    lineTo(15.485978f, 44.116505f)
    cubicTo(15.485978f, 44.899975f, 16.108747f, 45.522755f, 16.892227f, 45.522755f)
    lineTo(30.079727f, 45.522755f)
    cubicTo(30.863197f, 45.522755f, 31.485977f, 44.899975f, 31.485977f, 44.116505f)
    lineTo(31.485977f, 39.897755f)
    cubicTo(31.485977f, 39.114277f, 30.863197f, 38.491505f, 30.079727f, 38.491505f)
    lineTo(27.048477f, 38.491505f)
    lineTo(27.048477f, 31.397757f)
    cubicTo(27.048477f, 30.614277f, 26.425697f, 29.991507f, 25.642227f, 29.991507f)
    lineTo(21.923477f, 29.991507f)
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
// _0_0_5
shape = Outline.Rounded(roundRect = RoundRect(left = 21.457477569580078f, top = 36.02276611328125f, right = 23.429749369621277f, bottom = 39.02276611328125f,radiusX = 0.5991116762161255f, radiusY = 0.5991116762161255f))
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 115), 1.0f to Color(255, 255, 255, 0), start = Offset(21.457472f, 37.52275f), end = Offset(23.429743f, 37.52275f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.551237f, 35.975876f)
    lineTo(21.019987f, 35.975876f)
    cubicTo(21.019987f, 35.975876f, 21.019987f, 37.663376f, 21.019987f, 38.147755f)
    cubicTo(21.566858f, 36.366505f, 26.551237f, 35.975876f, 26.551237f, 35.975876f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 128), 1.0f to Color(0, 0, 0, 0), start = Offset(23.673359f, 36.077366f), end = Offset(23.795822f, 37.985367f), tileMode = TileMode.Clamp)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.5703089833259583f, 0.9008449912071228f, 0.0f, 1.0f)
))}){
// _0_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_0
shape = Outline.Rounded(roundRect = RoundRect(left = 19.007808685302734f, top = 39.09915542602539f, right = 30.570308685302734f, bottom = 40.099155366420746f,radiusX = 1.0f, radiusY = 0.9999999403953552f))
brush = SolidColor(Color(255, 255, 255, 184))
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
0.9411799907684326f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9411799907684326f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.6878949999809265f, 1.413727045059204f, 0.0f, 1.0f)
))}){
// _0_0_7_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.0f, 40.625f)
    cubicTo(19.00085f, 40.815365f, 18.899782f, 40.99163f, 18.735064f, 41.08706f)
    cubicTo(18.570345f, 41.182487f, 18.367155f, 41.182487f, 18.202436f, 41.08706f)
    cubicTo(18.037718f, 40.99163f, 17.93665f, 40.815365f, 17.9375f, 40.625f)
    cubicTo(17.93665f, 40.434635f, 18.037718f, 40.25837f, 18.202436f, 40.16294f)
    cubicTo(18.367155f, 40.067513f, 18.570345f, 40.067513f, 18.735064f, 40.16294f)
    cubicTo(18.899782f, 40.25837f, 19.00085f, 40.434635f, 19.0f, 40.625f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 184))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.471571f, 34.644497f)
    cubicTo(5.493367f, 35.060802f, 5.931476f, 35.477108f, 6.3477807f, 35.477108f)
    lineTo(37.6748f, 35.477108f)
    cubicTo(38.091103f, 35.477108f, 38.485615f, 35.060802f, 38.463818f, 34.644497f)
    lineTo(37.527374f, 7.4177623f)
    cubicTo(37.50558f, 7.0014596f, 37.06748f, 6.5851464f, 36.651173f, 6.5851464f)
    lineTo(23.3803f, 6.5851464f)
    cubicTo(22.895245f, 6.5851464f, 22.145828f, 6.2695575f, 21.978657f, 5.478514f)
    lineTo(21.367266f, 2.5854433f)
    cubicTo(21.211798f, 1.84977f, 20.485052f, 1.547557f, 20.068748f, 1.547557f)
    lineTo(5.289888f, 1.547557f)
    cubicTo(4.873575f, 1.547557f, 4.4790673f, 1.9638611f, 4.500863f, 2.3801653f)
    lineTo(5.471571f, 34.644497f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(32, 32, 32, 255), 1.0f to Color(185, 185, 185, 255), center = Offset(25.889977f, 32.94683f), radius = 32.61662f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(66, 66, 66, 255), 1.0f to Color(119, 119, 119, 255), start = Offset(19.0625f, 27.324831f), end = Offset(16.46468f, 2.137331f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.471571f, 34.644497f)
    cubicTo(5.493367f, 35.060802f, 5.931476f, 35.477108f, 6.3477807f, 35.477108f)
    lineTo(37.6748f, 35.477108f)
    cubicTo(38.091103f, 35.477108f, 38.485615f, 35.060802f, 38.463818f, 34.644497f)
    lineTo(37.527374f, 7.4177623f)
    cubicTo(37.50558f, 7.0014596f, 37.06748f, 6.5851464f, 36.651173f, 6.5851464f)
    lineTo(23.3803f, 6.5851464f)
    cubicTo(22.895245f, 6.5851464f, 22.145828f, 6.2695575f, 21.978657f, 5.478514f)
    lineTo(21.367266f, 2.5854433f)
    cubicTo(21.211798f, 1.84977f, 20.485052f, 1.547557f, 20.068748f, 1.547557f)
    lineTo(5.289888f, 1.547557f)
    cubicTo(4.873575f, 1.547557f, 4.4790673f, 1.9638611f, 4.500863f, 2.3801653f)
    lineTo(5.471571f, 34.644497f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.176383f, 18.51958f)
    lineTo(36.441963f, 18.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.176383f, 18.51958f)
    lineTo(36.441963f, 18.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.991964f, 14.519581f)
    lineTo(36.438896f, 14.519581f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.991964f, 14.519581f)
    lineTo(36.438896f, 14.519581f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.930487f, 8.519581f)
    lineTo(36.437847f, 8.519581f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.930487f, 8.519581f)
    lineTo(36.437847f, 8.519581f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.335948f, 28.51958f)
    lineTo(36.44467f, 28.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.335948f, 28.51958f)
    lineTo(36.44467f, 28.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.4589305f, 30.51958f)
    lineTo(36.446686f, 30.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.4589305f, 30.51958f)
    lineTo(36.446686f, 30.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5568182f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9145029783248901f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0104689598083496f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.2309449911117554f, -6.716239929199219f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.48707f, 41.591846f)
    cubicTo(43.517735f, 43.254642f, 39.87714f, 44.79432f, 33.943817f, 45.62787f)
    cubicTo(28.010498f, 46.461422f, 20.691483f, 46.461422f, 14.758162f, 45.62787f)
    cubicTo(8.8248415f, 44.79432f, 5.1842465f, 43.254642f, 5.2149124f, 41.591846f)
    cubicTo(5.1842465f, 39.92905f, 8.8248415f, 38.389374f, 14.758162f, 37.555824f)
    cubicTo(20.691483f, 36.72227f, 28.010498f, 36.72227f, 33.943817f, 37.555824f)
    cubicTo(39.87714f, 38.389374f, 43.517735f, 39.92905f, 43.48707f, 41.591846f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.35099f, 41.591835f), radius = 19.136078f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.991964f, 12.519581f)
    lineTo(36.438896f, 12.519581f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.991964f, 12.519581f)
    lineTo(36.438896f, 12.519581f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.961225f, 10.519581f)
    lineTo(36.43837f, 10.519581f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.961225f, 10.519581f)
    lineTo(36.43837f, 10.519581f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.871887f, 6.5195804f)
    lineTo(21.152702f, 6.5195804f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.871887f, 6.5195804f)
    lineTo(21.152702f, 6.5195804f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.823544f, 4.5195804f)
    lineTo(20.607279f, 4.5195804f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.823544f, 4.5195804f)
    lineTo(20.607279f, 4.5195804f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.274457f, 24.51958f)
    lineTo(36.44367f, 24.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.274457f, 24.51958f)
    lineTo(36.44367f, 24.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_20
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.2378545f, 22.51958f)
    lineTo(36.442974f, 22.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.2378545f, 22.51958f)
    lineTo(36.442974f, 22.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_21
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.176383f, 20.51958f)
    lineTo(36.441963f, 20.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.176383f, 20.51958f)
    lineTo(36.441963f, 20.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_22
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.145644f, 16.51958f)
    lineTo(36.44144f, 16.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.145644f, 16.51958f)
    lineTo(36.44144f, 16.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_23
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.274457f, 26.51958f)
    lineTo(36.44367f, 26.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.274457f, 26.51958f)
    lineTo(36.44367f, 26.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_24
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.4589305f, 32.51958f)
    lineTo(36.446686f, 32.51958f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.4589305f, 32.51958f)
    lineTo(36.446686f, 32.51958f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4514286f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_25
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.0181336f, 34.821102f)
    cubicTo(7.0344763f, 35.13333f, 6.837222f, 35.341484f, 6.519549f, 35.237408f)
    lineTo(6.519549f, 35.237408f)
    cubicTo(6.201867f, 35.13333f, 5.9828176f, 34.92518f, 5.966466f, 34.61295f)
    lineTo(5.0187464f, 2.5484643f)
    cubicTo(5.0024037f, 2.236236f, 5.1839323f, 2.0476937f, 5.4961605f, 2.0476937f)
    lineTo(19.918211f, 2.0f)
    cubicTo(20.230438f, 2.0f, 20.850155f, 2.3004727f, 21.051147f, 3.3221817f)
    lineTo(21.624636f, 6.1377163f)
    cubicTo(21.197582f, 5.6724596f, 21.205442f, 5.6580977f, 20.987078f, 4.9810104f)
    lineTo(20.580982f, 3.7218282f)
    cubicTo(20.361933f, 2.9941814f, 19.882782f, 2.889928f, 19.570555f, 2.889928f)
    lineTo(6.6827793f, 2.889928f)
    cubicTo(6.3705516f, 2.889928f, 6.173298f, 3.0980804f, 6.1896486f, 3.4103167f)
    lineTo(7.127654f, 34.92518f)
    lineTo(7.0181336f, 34.821102f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 223), 1.0f to Color(255, 255, 254, 0), start = Offset(9.519847f, 4.403715f), end = Offset(15.084075f, 42.02279f), tileMode = TileMode.Clamp)
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
1.0407639741897583f, 0.0f, 0.0f, 0.0f,
0.0544925183057785f, 1.0407639741897583f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.720407962799072f, -1.3723249435424805f, 0.0f, 1.0f)
))}){
// _0_0_26
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_26_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(42.417183f, 8.515178f)
    cubicTo(42.422268f, 8.418064f, 42.28902f, 8.268189f, 42.182068f, 8.268171f)
    lineTo(29.150665f, 8.266053f)
    cubicTo(29.150665f, 8.266053f, 30.06238f, 8.854008f, 31.352476f, 8.862296f)
    lineTo(42.405975f, 8.933317f)
    cubicTo(42.41706f, 8.721589f, 42.408695f, 8.677284f, 42.417183f, 8.515178f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 130))
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
// _0_0_27
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.733322f, 35.4677f)
    cubicTo(41.877216f, 35.423637f, 42.6964f, 34.371403f, 42.780357f, 33.146694f)
    cubicTo(43.572144f, 21.59801f, 44.439716f, 11.914747f, 44.439716f, 11.914747f)
    cubicTo(44.51187f, 11.667263f, 44.27181f, 11.41978f, 43.95958f, 11.41978f)
    lineTo(9.588421f, 11.41978f)
    cubicTo(9.588421f, 11.41978f, 7.738102f, 33.28667f, 7.738102f, 33.28667f)
    cubicTo(7.6235466f, 34.268738f, 7.2720942f, 35.09139f, 6.188266f, 35.470387f)
    lineTo(40.733322f, 35.4677f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(97, 148, 203, 255), 1.0f to Color(114, 159, 207, 255), start = Offset(23.125767f, 32.94508f), end = Offset(23.015121f, 28.00758f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.733322f, 35.4677f)
    cubicTo(41.877216f, 35.423637f, 42.6964f, 34.371403f, 42.780357f, 33.146694f)
    cubicTo(43.572144f, 21.59801f, 44.439716f, 11.914747f, 44.439716f, 11.914747f)
    cubicTo(44.51187f, 11.667263f, 44.27181f, 11.41978f, 43.95958f, 11.41978f)
    lineTo(9.588421f, 11.41978f)
    cubicTo(9.588421f, 11.41978f, 7.738102f, 33.28667f, 7.738102f, 33.28667f)
    cubicTo(7.6235466f, 34.268738f, 7.2720942f, 35.09139f, 6.188266f, 35.470387f)
    lineTo(40.733322f, 35.4677f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.46590912f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_28
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(17.244604f, 21.222073f), end = Offset(17.00492f, 32.749348f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.570035f, 12.421002f)
    lineTo(43.361134f, 12.485816f)
    lineTo(41.787086f, 32.487797f)
    cubicTo(41.702766f, 33.559307f, 41.33641f, 33.91601f, 39.914433f, 33.91601f)
    cubicTo(38.04293f, 33.91601f, 11.236464f, 33.883602f, 8.51969f, 33.883602f)
    cubicTo(8.753288f, 33.562794f, 8.853445f, 32.89498f, 8.854786f, 32.87899f)
    lineTo(10.570035f, 12.421002f)
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
// _0_0_29
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.570039f, 12.180263f)
    lineTo(9.403392f, 27.823534f)
    cubicTo(9.403392f, 27.823534f, 17.699547f, 23.675455f, 28.06974f, 23.675455f)
    cubicTo(38.439934f, 23.675455f, 43.62503f, 12.180263f, 43.62503f, 12.180263f)
    lineTo(10.570039f, 12.180263f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 23))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
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
            return 4.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.04755699634552
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 40.953609466552734
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.975196838378906
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

