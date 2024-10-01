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
class document_print_preview : Painter() {
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.3119190037250519f, -2.006727933883667f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.125f, 41.875f)
    cubicTo(43.155647f, 44.15936f, 39.517162f, 46.274582f, 33.587276f, 47.41972f)
    cubicTo(27.65739f, 48.564857f, 20.34261f, 48.564857f, 14.4127245f, 47.41972f)
    cubicTo(8.482839f, 46.274582f, 4.8443522f, 44.15936f, 4.875f, 41.875f)
    cubicTo(4.8443522f, 39.59064f, 8.482839f, 37.475418f, 14.4127245f, 36.33028f)
    cubicTo(20.34261f, 35.185143f, 27.65739f, 35.185143f, 33.587276f, 36.33028f)
    cubicTo(39.517162f, 37.475418f, 43.155647f, 39.59064f, 43.125f, 41.875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.0f, 41.87499f), radius = 19.125f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 4.75f, top = 36.0f, right = 43.1875f, bottom = 42.4375f,radiusX = 3.4230966567993164f, radiusY = 3.4230966567993164f))
brush = Brush.linearGradient(0.0f to Color(142, 141, 135, 255), 0.27586207f to Color(203, 201, 193, 255), 1.0f to Color(142, 141, 135, 255), start = Offset(4.249999f, 39.218754f), end = Offset(43.687496f, 39.218754f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(89, 89, 89, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 4.75f, top = 36.0f, right = 43.1875f, bottom = 42.4375f,radiusX = 3.4230966567993164f, radiusY = 3.4230966567993164f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.075825f, 21.5f)
    lineTo(40.975952f, 21.5f)
    cubicTo(41.362827f, 21.5f, 41.857155f, 21.788155f, 42.162f, 22.223919f)
    cubicTo(42.466843f, 22.659683f, 43.906723f, 24.83394f, 44.230183f, 25.297964f)
    cubicTo(44.553642f, 25.761988f, 44.625f, 26.201853f, 44.625f, 26.77405f)
    lineTo(44.625f, 38.850952f)
    cubicTo(44.625f, 39.764523f, 43.889523f, 40.5f, 42.975952f, 40.5f)
    lineTo(5.075825f, 40.5f)
    cubicTo(4.1622524f, 40.5f, 3.4267766f, 39.764523f, 3.4267766f, 38.850952f)
    lineTo(3.4267766f, 26.77405f)
    cubicTo(3.4267766f, 26.280031f, 3.5284235f, 25.571642f, 3.8753054f, 25.120718f)
    cubicTo(4.313023f, 24.551714f, 5.487279f, 22.57277f, 5.7970057f, 22.153118f)
    cubicTo(6.1067324f, 21.733467f, 6.675481f, 21.5f, 7.075825f, 21.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(220, 220, 218, 255), 1.0f to Color(186, 185, 183, 255), start = Offset(4.249999f, 40.34375f), end = Offset(43.687496f, 40.34375f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(103, 103, 103, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.075825f, 21.5f)
    lineTo(40.975952f, 21.5f)
    cubicTo(41.362827f, 21.5f, 41.857155f, 21.788155f, 42.162f, 22.223919f)
    cubicTo(42.466843f, 22.659683f, 43.906723f, 24.83394f, 44.230183f, 25.297964f)
    cubicTo(44.553642f, 25.761988f, 44.625f, 26.201853f, 44.625f, 26.77405f)
    lineTo(44.625f, 38.850952f)
    cubicTo(44.625f, 39.764523f, 43.889523f, 40.5f, 42.975952f, 40.5f)
    lineTo(5.075825f, 40.5f)
    cubicTo(4.1622524f, 40.5f, 3.4267766f, 39.764523f, 3.4267766f, 38.850952f)
    lineTo(3.4267766f, 26.77405f)
    cubicTo(3.4267766f, 26.280031f, 3.5284235f, 25.571642f, 3.8753054f, 25.120718f)
    cubicTo(4.313023f, 24.551714f, 5.487279f, 22.57277f, 5.7970057f, 22.153118f)
    cubicTo(6.1067324f, 21.733467f, 6.675481f, 21.5f, 7.075825f, 21.5f)
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.424621f, 21.975533f)
    cubicTo(6.921893f, 21.975533f, 6.2754774f, 22.107307f, 6.010408f, 22.511225f)
    lineTo(4.1542525f, 25.339651f)
    cubicTo(3.8554516f, 25.794966f, 4.1881986f, 26.868141f, 5.087311f, 26.868141f)
    lineTo(42.730785f, 26.868141f)
    cubicTo(43.946983f, 26.868141f, 43.950535f, 25.858072f, 43.663845f, 25.42804f)
    lineTo(41.896076f, 22.776388f)
    cubicTo(41.575542f, 22.29559f, 41.459198f, 21.975533f, 40.65864f, 21.975533f)
    lineTo(7.424621f, 21.975533f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(251, 251, 251, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 32), 0.10344828f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(23.010353f, 23.760323f), end = Offset(23.15967f, 41.342876f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.946967f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.537484f, 22.445757f)
    lineTo(40.425903f, 22.445757f)
    cubicTo(40.792263f, 22.445757f, 41.260372f, 22.71863f, 41.54905f, 23.131283f)
    cubicTo(41.837727f, 23.543938f, 42.847694f, 25.160946f, 43.154f, 25.60036f)
    cubicTo(43.460304f, 26.039776f, 43.59038f, 26.456312f, 43.59038f, 26.998163f)
    lineTo(43.59038f, 38.279263f)
    cubicTo(43.59038f, 39.144386f, 43.45641f, 39.528355f, 42.591286f, 39.528355f)
    lineTo(5.4604917f, 39.528355f)
    cubicTo(4.5953684f, 39.528355f, 4.398897f, 39.144386f, 4.398897f, 38.279263f)
    lineTo(4.398897f, 26.998163f)
    cubicTo(4.398897f, 26.530346f, 4.6201534f, 25.859524f, 4.948639f, 25.432514f)
    cubicTo(5.363143f, 24.893684f, 6.033183f, 23.461634f, 6.326484f, 23.064238f)
    cubicTo(6.619785f, 22.666842f, 7.158371f, 22.445757f, 7.537484f, 22.445757f)
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.570264f, 4.406405f)
    lineTo(36.30689f, 4.406405f)
    cubicTo(36.95988f, 4.406405f, 37.485577f, 4.9188805f, 37.485577f, 5.5554533f)
    lineTo(37.485577f, 24.345886f)
    lineTo(10.391575f, 24.345886f)
    lineTo(10.391575f, 5.5554533f)
    cubicTo(10.391575f, 4.9188805f, 10.91727f, 4.406405f, 11.570264f, 4.406405f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(224, 224, 224, 255), 0.4054697f to Color(255, 255, 255, 255), 0.5344828f to Color(205, 205, 205, 255), 1.0f to Color(73, 73, 73, 255), start = Offset(23.68521f, 7.976432f), end = Offset(23.431837f, 31.291813f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(137, 137, 137, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.570264f, 4.406405f)
    lineTo(36.30689f, 4.406405f)
    cubicTo(36.95988f, 4.406405f, 37.485577f, 4.9188805f, 37.485577f, 5.5554533f)
    lineTo(37.485577f, 24.345886f)
    lineTo(10.391575f, 24.345886f)
    lineTo(10.391575f, 5.5554533f)
    cubicTo(10.391575f, 4.9188805f, 10.91727f, 4.406405f, 11.570264f, 4.406405f)
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
// _0_0_6
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 1.0f to Color(248, 248, 248, 255), start = Offset(24.672031f, 23.491322f), end = Offset(24.607792f, 18.530766f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999994f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 11.374062538146973f, top = 5.469976425170898f, right = 36.449463844299316f, bottom = 24.334856033325195f,radiusX = 0.3535533547401428f, radiusY = 0.3535533845424652f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
shape = Outline.Rounded(roundRect = RoundRect(left = 6.875f, top = 27.375f, right = 40.625f, bottom = 32.5625f,radiusX = 3.4230966567993164f, radiusY = 3.4230966567993164f))
brush = Brush.linearGradient(0.0f to Color(247, 246, 245, 255), 1.0f to Color(247, 246, 245, 0), start = Offset(24.499998f, 29.28125f), end = Offset(24.499998f, 24.843773f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(102, 102, 102, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(23.0625f, 31.843746f), end = Offset(22.999996f, 28.281271f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.875f, top = 27.375f, right = 40.625f, bottom = 32.5625f,radiusX = 3.4230966567993164f, radiusY = 3.4230966567993164f))
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
0.0f, 2.0f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.871767f, 27.626486f)
    cubicTo(10.873821f, 28.085733f, 10.629993f, 28.510979f, 10.232612f, 28.741198f)
    cubicTo(9.83523f, 28.971416f, 9.345042f, 28.971416f, 8.94766f, 28.741198f)
    cubicTo(8.550279f, 28.510979f, 8.306451f, 28.085733f, 8.308505f, 27.626486f)
    cubicTo(8.306451f, 27.167238f, 8.550279f, 26.741993f, 8.94766f, 26.511774f)
    cubicTo(9.345042f, 26.281555f, 9.83523f, 26.281555f, 10.232612f, 26.511774f)
    cubicTo(10.629993f, 26.741993f, 10.873821f, 27.167238f, 10.871767f, 27.626486f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 253, 255), 0.5f to Color(187, 187, 185, 255), 1.0f to Color(0, 0, 0, 255), center = Offset(9.129549f, 26.925594f), radius = 2.1227016f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36571428f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.743718f, 25.416054f)
    lineTo(37.306217f, 25.478554f)
    cubicTo(37.993717f, 25.480234f, 38.294037f, 25.107557f, 38.243717f, 24.478554f)
    lineTo(38.118717f, 22.916054f)
    lineTo(39.984837f, 22.916054f)
    cubicTo(40.797337f, 22.916054f, 40.975037f, 23.108616f, 41.172337f, 23.478554f)
    lineTo(41.672337f, 24.416054f)
    cubicTo(42.19913f, 25.403793f, 43.48351f, 26.390165f, 42.170494f, 26.390165f)
    cubicTo(37.667786f, 26.390165f, 13.993718f, 26.041054f, 11.743718f, 25.416054f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 60), 1.0f to Color(0, 0, 0, 0), start = Offset(27.978674f, 23.528128f), end = Offset(28.097887f, 28.880291f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(42.9375f, 26.75f)
    lineTo(4.8125f, 26.75f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.43575415f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 2.0f, 0.0f, 1.0f)
))}){
// _0_0_11
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_0
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 5.0f, right = 33.0f, bottom = 6.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_1
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 7.0f, right = 33.0f, bottom = 8.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_2
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 9.0f, right = 33.0f, bottom = 10.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_3
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 11.0f, right = 33.0f, bottom = 12.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_4
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 13.0f, right = 25.0f, bottom = 14.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_5
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 17.0f, right = 33.0f, bottom = 18.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11_6
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 19.0f, right = 33.0f, bottom = 20.0f))
brush = SolidColor(Color(0, 0, 0, 75))
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7156779766082764f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7156779766082764f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
4.077534198760986f, 4.71388578414917f, 0.0f, 1.0f)
))}){
// _0_0_12
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.488439f, 3.1435547f, 3.8827682f, 9.749226f, 3.8827682f, 17.888355f)
    cubicTo(3.8827682f, 26.027487f, 10.488439f, 32.63316f, 18.62757f, 32.63316f)
    cubicTo(22.107124f, 32.63316f, 25.17857f, 31.248766f, 27.701292f, 29.23051f)
    cubicTo(27.495914f, 30.237392f, 27.623257f, 31.265879f, 28.457436f, 31.990437f)
    lineTo(39.42152f, 41.517845f)
    cubicTo(40.654938f, 42.589176f, 42.508984f, 42.448807f, 43.58031f, 41.21539f)
    cubicTo(44.651638f, 39.98197f, 44.51127f, 38.127926f, 43.27785f, 37.0566f)
    lineTo(32.31377f, 27.529188f)
    cubicTo(31.642242f, 26.94591f, 30.82089f, 26.773218f, 30.00753f, 26.886465f)
    cubicTo(31.99423f, 24.374044f, 33.37237f, 21.337664f, 33.37237f, 17.888355f)
    cubicTo(33.37237f, 9.749226f, 26.766699f, 3.1435547f, 18.62757f, 3.1435547f)
    close()
    moveTo(18.551954f, 4.369738f)
    cubicTo(26.191414f, 4.369738f, 31.843729f, 9.158689f, 31.843729f, 17.661512f)
    cubicTo(31.843729f, 26.336626f, 26.027039f, 30.953287f, 18.551954f, 30.953287f)
    cubicTo(11.249005f, 30.953287f, 5.2601805f, 25.475197f, 5.2601805f, 17.661512f)
    cubicTo(5.2601805f, 9.677406f, 11.084819f, 4.369738f, 18.551954f, 4.369738f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(138, 138, 138, 255), 1.0f to Color(72, 72, 72, 255), start = Offset(27.36634f, 26.580296f), end = Offset(31.335964f, 30.557772f), tileMode = TileMode.Clamp)
stroke = Stroke(width=2.7945554f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.488439f, 3.1435547f, 3.8827682f, 9.749226f, 3.8827682f, 17.888355f)
    cubicTo(3.8827682f, 26.027487f, 10.488439f, 32.63316f, 18.62757f, 32.63316f)
    cubicTo(22.107124f, 32.63316f, 25.17857f, 31.248766f, 27.701292f, 29.23051f)
    cubicTo(27.495914f, 30.237392f, 27.623257f, 31.265879f, 28.457436f, 31.990437f)
    lineTo(39.42152f, 41.517845f)
    cubicTo(40.654938f, 42.589176f, 42.508984f, 42.448807f, 43.58031f, 41.21539f)
    cubicTo(44.651638f, 39.98197f, 44.51127f, 38.127926f, 43.27785f, 37.0566f)
    lineTo(32.31377f, 27.529188f)
    cubicTo(31.642242f, 26.94591f, 30.82089f, 26.773218f, 30.00753f, 26.886465f)
    cubicTo(31.99423f, 24.374044f, 33.37237f, 21.337664f, 33.37237f, 17.888355f)
    cubicTo(33.37237f, 9.749226f, 26.766699f, 3.1435547f, 18.62757f, 3.1435547f)
    close()
    moveTo(18.551954f, 4.369738f)
    cubicTo(26.191414f, 4.369738f, 31.843729f, 9.158689f, 31.843729f, 17.661512f)
    cubicTo(31.843729f, 26.336626f, 26.027039f, 30.953287f, 18.551954f, 30.953287f)
    cubicTo(11.249005f, 30.953287f, 5.2601805f, 25.475197f, 5.2601805f, 17.661512f)
    cubicTo(5.2601805f, 9.677406f, 11.084819f, 4.369738f, 18.551954f, 4.369738f)
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
// _0_0_12_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.602905f, 3.0803552f)
    cubicTo(10.437465f, 3.0803552f, 3.8104408f, 9.707379f, 3.8104408f, 17.87282f)
    cubicTo(3.8104408f, 26.03826f, 10.437465f, 32.665283f, 18.602905f, 32.665283f)
    cubicTo(22.093708f, 32.665283f, 25.175081f, 31.276417f, 27.70596f, 29.251638f)
    cubicTo(27.49992f, 30.261774f, 27.627672f, 31.293585f, 28.464546f, 32.020485f)
    lineTo(39.464073f, 41.57869f)
    cubicTo(40.701477f, 42.653484f, 42.561516f, 42.51266f, 43.636307f, 41.275257f)
    cubicTo(44.711098f, 40.037853f, 44.570274f, 38.177814f, 43.33287f, 37.103024f)
    lineTo(32.333347f, 27.544815f)
    cubicTo(31.659649f, 26.959652f, 30.835642f, 26.786402f, 30.019653f, 26.900017f)
    cubicTo(32.012775f, 24.379473f, 33.39537f, 21.333277f, 33.39537f, 17.87282f)
    cubicTo(33.39537f, 9.707379f, 26.768345f, 3.0803552f, 18.602905f, 3.0803552f)
    close()
    moveTo(18.527046f, 6.266424f)
    cubicTo(24.808153f, 6.2664247f, 29.905865f, 11.364135f, 29.905865f, 17.645243f)
    cubicTo(29.905865f, 23.926352f, 24.808153f, 29.024061f, 18.527046f, 29.024061f)
    cubicTo(12.245938f, 29.024061f, 7.1482277f, 23.926352f, 7.1482277f, 17.645243f)
    cubicTo(7.1482277f, 11.364135f, 12.245938f, 6.266424f, 18.527046f, 6.266424f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.507004f, 41.57769f)
    cubicTo(39.02833f, 39.304504f, 40.904335f, 36.76627f, 43.091057f, 36.789314f)
    cubicTo(43.091057f, 36.789314f, 32.33069f, 27.531204f, 32.33069f, 27.531204f)
    cubicTo(29.385899f, 27.474499f, 28.061188f, 29.80382f, 28.553877f, 32.131126f)
    lineTo(39.507004f, 41.57769f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(125, 125, 125, 255), 0.5f to Color(177, 177, 177, 255), 1.0f to Color(104, 104, 104, 255), start = Offset(33.939777f, 36.443268f), end = Offset(37.35967f, 32.650097f), tileMode = TileMode.Clamp)
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
1.2457430362701416f, 0.0f, 0.0f, 0.0f,
0.0f, 1.2457430362701416f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.4253458976745605f, -6.177032947540283f, 0.0f, 1.0f)
))}){
// _0_0_12_3
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 255, 255, 56), 1.0f to Color(255, 255, 255, 255), start = Offset(18.292673f, 13.602121f), end = Offset(17.500893f, 25.74347f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1216413f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.549437f, 18.920233f)
    cubicTo(28.567142f, 22.879269f, 26.465181f, 26.545166f, 23.039478f, 28.529814f)
    cubicTo(19.613773f, 30.51446f, 15.388013f, 30.51446f, 11.962308f, 28.529814f)
    cubicTo(8.536603f, 26.545166f, 6.4346433f, 22.879269f, 6.4523487f, 18.920233f)
    cubicTo(6.4346433f, 14.961198f, 8.536603f, 11.295299f, 11.962308f, 9.310653f)
    cubicTo(15.388013f, 7.326006f, 19.613773f, 7.326006f, 23.039478f, 9.310653f)
    cubicTo(26.465181f, 11.295299f, 28.567142f, 14.961198f, 28.549437f, 18.920233f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4331551f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7529860138893127f, 0.658037006855011f, 0.0f, 0.0f,
-0.6489019989967346f, 0.7608720064163208f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12_4
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.3973206f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 40.373348236083984f, top = 0.14086054265499115f, right = 59.42179298400879f, bottom = 4.581338867545128f,radiusX = 5.971015930175781f, radiusY = 4.440478324890137f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
1.3986140489578247f, 0.0f, 0.0f, 0.0f,
0.0f, 1.3986140489578247f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-6.224338054656982f, -8.298957824707031f, 0.0f, 1.0f)
))}){
// _0_0_12_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424265f, 25.704697f)
    cubicTo(10.848135f, 24.212242f, 9.267462f, 21.455486f, 9.280776f, 18.478292f)
    cubicTo(9.267462f, 15.501098f, 10.848135f, 12.744343f, 13.424265f, 11.251888f)
    cubicTo(16.000395f, 9.759435f, 19.178165f, 9.759435f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744343f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(114, 159, 207, 53), 1.0f to Color(114, 159, 207, 172), center = Offset(18.240929f, 21.817987f), radius = 8.308506f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(48, 99, 163, 255))
stroke = Stroke(width=0.99904466f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424265f, 25.704697f)
    cubicTo(10.848135f, 24.212242f, 9.267462f, 21.455486f, 9.280776f, 18.478292f)
    cubicTo(9.267462f, 15.501098f, 10.848135f, 12.744343f, 13.424265f, 11.251888f)
    cubicTo(16.000395f, 9.759435f, 19.178165f, 9.759435f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744343f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8342246f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.156916f, 7.3966937f)
    cubicTo(12.949325f, 7.3966937f, 8.732368f, 11.613651f, 8.732368f, 16.821241f)
    cubicTo(8.732368f, 18.325216f, 9.152676f, 19.709015f, 9.77954f, 20.971144f)
    cubicTo(11.03192f, 21.432756f, 12.362297f, 21.746826f, 13.774307f, 21.746826f)
    cubicTo(19.945263f, 21.746826f, 24.873589f, 16.88519f, 25.254414f, 10.809698f)
    cubicTo(23.523449f, 8.764167f, 21.044374f, 7.3966937f, 18.156916f, 7.3966937f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 63), center = Offset(14.909142f, 10.512936f), radius = 17.259415f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 2.926776647567749
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.906404972076416
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 42.19822311401367
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 42.365440368652344
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

