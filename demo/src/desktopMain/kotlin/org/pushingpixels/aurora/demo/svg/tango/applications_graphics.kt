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
class applications_graphics : Painter() {
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
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.069200038909912f, 0.0f, 0.0f, 0.0f,
0.0f, 1.1230000257492065f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
5.769000053405762f, -7.116199970245361f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.189f, 42.343f)
    cubicTo(41.212086f, 43.799988f, 38.471092f, 45.149097f, 34.003914f, 45.87948f)
    cubicTo(29.536732f, 46.60986f, 24.026266f, 46.60986f, 19.559084f, 45.87948f)
    cubicTo(15.091904f, 45.149097f, 12.35091f, 43.799988f, 12.373999f, 42.343f)
    cubicTo(12.35091f, 40.88601f, 15.091904f, 39.5369f, 19.559084f, 38.80652f)
    cubicTo(24.026266f, 38.076138f, 29.536732f, 38.076138f, 34.003914f, 38.80652f)
    cubicTo(38.471092f, 39.5369f, 41.212086f, 40.88601f, 41.189f, 42.343f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(26.782f, 42.343616f), radius = 14.407f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(19.652f, 22.587f)
    lineTo(23.718f, 26.387f)
    cubicTo(30.905f, 19.324999f, 41.501f, 2.4099998f, 41.501f, 2.4099998f)
    cubicTo(41.93f, 1.1856998f, 40.413f, 0.41419983f, 39.654f, 1.2396998f)
    cubicTo(39.654f, 1.2396998f, 25.964f, 15.3367f, 19.651999f, 22.5867f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(110, 61, 9, 255), 0.24242f to Color(234, 129, 19, 255), 0.62121f to Color(92, 51, 7, 255), 1.0f to Color(224, 124, 18, 255), start = Offset(24.152f, 17.0651f), end = Offset(29.53f, 21.9401f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(103, 57, 7, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.652f, 22.587f)
    lineTo(23.718f, 26.387f)
    cubicTo(30.905f, 19.324999f, 41.501f, 2.4099998f, 41.501f, 2.4099998f)
    cubicTo(41.93f, 1.1856998f, 40.413f, 0.41419983f, 39.654f, 1.2396998f)
    cubicTo(39.654f, 1.2396998f, 25.964f, 15.3367f, 19.651999f, 22.5867f)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.05f, 32.814f)
    lineTo(14.297f, 34.748f)
    lineTo(22.789f, 27.381f)
    lineTo(23.064f, 26.675001f)
    lineTo(24.158998f, 26.657001f)
    cubicTo(23.720999f, 25.095001f, 21.217f, 22.274002f, 19.342f, 22.274002f)
    lineTo(19.424f, 23.364002f)
    lineTo(18.754f, 23.744001f)
    lineTo(12.049999f, 32.814003f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(189, 189, 189, 255), 0.33333f to Color(226, 226, 226, 255), 0.66667f to Color(163, 163, 163, 255), 1.0f to Color(221, 221, 221, 255), start = Offset(15.488001f, 28.1981f), end = Offset(19.202f, 31.636099f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.05f, 32.814f)
    lineTo(14.297f, 34.748f)
    lineTo(22.789f, 27.381f)
    lineTo(23.064f, 26.675001f)
    lineTo(24.158998f, 26.657001f)
    cubicTo(23.720999f, 25.095001f, 21.217f, 22.274002f, 19.342f, 22.274002f)
    lineTo(19.424f, 23.364002f)
    lineTo(18.754f, 23.744001f)
    lineTo(12.049999f, 32.814003f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3384000062942505f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-20.926000595092773f, -3.4089999198913574f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.189f, 42.343f)
    cubicTo(41.212086f, 43.799988f, 38.471092f, 45.149097f, 34.003914f, 45.87948f)
    cubicTo(29.536732f, 46.60986f, 24.026266f, 46.60986f, 19.559084f, 45.87948f)
    cubicTo(15.091904f, 45.149097f, 12.35091f, 43.799988f, 12.373999f, 42.343f)
    cubicTo(12.35091f, 40.88601f, 15.091904f, 39.5369f, 19.559084f, 38.80652f)
    cubicTo(24.026266f, 38.076138f, 29.536732f, 38.076138f, 34.003914f, 38.80652f)
    cubicTo(38.471092f, 39.5369f, 41.212086f, 40.88601f, 41.189f, 42.343f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(26.782f, 42.343616f), radius = 14.407f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
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
    moveTo(1.7577f, 40.724f)
    cubicTo(8.363f, 40.724f, 13.3757f, 41.510998f, 14.3247f, 36.704998f)
    cubicTo(15.089701f, 32.831997f, 9.7643f, 30.975998f, 7.1255f, 34.434f)
    cubicTo(4.5835f, 37.764f, 1.7574f, 40.724f, 1.7574f, 40.724f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(105, 105, 105, 255), 1.0f to Color(0, 0, 0, 255), center = Offset(10.749684f, 34.438168f), radius = 7.538726f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.52778f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8522999882698059f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8522999882698059f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
3.733599901199341f, 2.5755999088287354f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.875f, 37.75f)
    cubicTo(8.877003f, 38.197914f, 8.639194f, 38.612663f, 8.251619f, 38.8372f)
    cubicTo(7.864045f, 39.061737f, 7.385955f, 39.061737f, 6.9983807f, 38.8372f)
    cubicTo(6.6108065f, 38.612663f, 6.372997f, 38.197914f, 6.375f, 37.75f)
    cubicTo(6.372997f, 37.302086f, 6.6108065f, 36.887337f, 6.9983807f, 36.6628f)
    cubicTo(7.385955f, 36.438263f, 7.864045f, 36.438263f, 8.251619f, 36.6628f)
    cubicTo(8.639194f, 36.887337f, 8.877003f, 37.302086f, 8.875f, 37.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
0.7878699898719788f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7878699898719788f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
14.265000343322754f, -4.723400115966797f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.875f, 37.75f)
    cubicTo(8.877003f, 38.197914f, 8.639194f, 38.612663f, 8.251619f, 38.8372f)
    cubicTo(7.864045f, 39.061737f, 7.385955f, 39.061737f, 6.9983807f, 38.8372f)
    cubicTo(6.6108065f, 38.612663f, 6.372997f, 38.197914f, 6.375f, 37.75f)
    cubicTo(6.372997f, 37.302086f, 6.6108065f, 36.887337f, 6.9983807f, 36.6628f)
    cubicTo(7.385955f, 36.438263f, 7.864045f, 36.438263f, 8.251619f, 36.6628f)
    cubicTo(8.639194f, 36.887337f, 8.877003f, 37.302086f, 8.875f, 37.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.941f, 26.398f)
    lineTo(13.6380005f, 32.541f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.42778f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.0709f, 40.069f)
    cubicTo(3.0709f, 40.069f, 6.4119997f, 38.427002f, 7.8357f, 35.982002f)
    cubicTo(8.3394f, 35.117f, 9.4801f, 35.855003f, 8.7774f, 36.717003f)
    cubicTo(7.2596f, 38.577003f, 3.0709f, 40.069004f, 3.0709f, 40.069004f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(9.605943f, 35.93003f), end = Offset(4.932015f, 39.79095f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.53333f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.237869992852211f, 0.0f, 0.0f, 0.0f,
0.0f, 0.237869992852211f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
38.433998107910156f, -7.10099983215332f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.875f, 37.75f)
    cubicTo(8.877003f, 38.197914f, 8.639194f, 38.612663f, 8.251619f, 38.8372f)
    cubicTo(7.864045f, 39.061737f, 7.385955f, 39.061737f, 6.9983807f, 38.8372f)
    cubicTo(6.6108065f, 38.612663f, 6.372997f, 38.197914f, 6.375f, 37.75f)
    cubicTo(6.372997f, 37.302086f, 6.6108065f, 36.887337f, 6.9983807f, 36.6628f)
    cubicTo(7.385955f, 36.438263f, 7.864045f, 36.438263f, 8.251619f, 36.6628f)
    cubicTo(8.639194f, 36.887337f, 8.877003f, 37.302086f, 8.875f, 37.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.127f, 7.3501f)
    cubicTo(32.069f, 7.4374f, 29.642998f, 8.4062f, 29.642998f, 9.5744f)
    lineTo(29.642998f, 13.145f)
    cubicTo(29.642998f, 14.370001f, 32.312996f, 15.369f, 35.584f, 15.369f)
    cubicTo(38.854f, 15.369f, 41.5f, 14.370001f, 41.5f, 13.145f)
    lineTo(41.5f, 9.5744f)
    cubicTo(41.5f, 8.3487f, 38.854f, 7.3501f, 35.584f, 7.3501f)
    cubicTo(35.431f, 7.3501f, 35.277f, 7.3458f, 35.127f, 7.3501f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 159, 207, 255), 1.0f to Color(60, 116, 177, 255), start = Offset(29.21122f, 12.358063f), end = Offset(41.931076f, 12.358063f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(32, 74, 135, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.127f, 7.3501f)
    cubicTo(32.069f, 7.4374f, 29.642998f, 8.4062f, 29.642998f, 9.5744f)
    lineTo(29.642998f, 13.145f)
    cubicTo(29.642998f, 14.370001f, 32.312996f, 15.369f, 35.584f, 15.369f)
    cubicTo(38.854f, 15.369f, 41.5f, 14.370001f, 41.5f, 13.145f)
    lineTo(41.5f, 9.5744f)
    cubicTo(41.5f, 8.3487f, 38.854f, 7.3501f, 35.584f, 7.3501f)
    cubicTo(35.431f, 7.3501f, 35.277f, 7.3458f, 35.127f, 7.3501f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8862800002098083f, 0.0f, 0.0f, 0.0f,
0.0f, 0.46891000866889954f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.444000244140625f, -9.718899726867676f, 0.0f, 1.0f)
))}){
// _0_0_10_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(57.375f, 49.75f)
    cubicTo(57.383614f, 50.287495f, 56.36103f, 50.785194f, 54.69446f, 51.05464f)
    cubicTo(53.027893f, 51.324085f, 50.972107f, 51.324085f, 49.30554f, 51.05464f)
    cubicTo(47.63897f, 50.785194f, 46.616386f, 50.287495f, 46.625f, 49.75f)
    cubicTo(46.616386f, 49.212505f, 47.63897f, 48.714806f, 49.30554f, 48.44536f)
    cubicTo(50.972107f, 48.175915f, 53.027893f, 48.175915f, 54.69446f, 48.44536f)
    cubicTo(56.36103f, 48.714806f, 57.383614f, 49.212505f, 57.375f, 49.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(52, 101, 164, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(27.926f, 39.155f)
    lineTo(28.973001f, 17.805998f)
    lineTo(31.460001f, 14.142998f)
    cubicTo(33.431f, 13.506998f, 38.187f, 13.506998f, 39.967003f, 14.142998f)
    lineTo(42.454002f, 17.173998f)
    lineTo(45.071003f, 39.660995f)
    lineTo(27.926003f, 39.154995f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(242, 242, 242, 255), 1.0f to Color(195, 195, 195, 255), start = Offset(30.555603f, 26.901672f), end = Offset(42.442192f, 26.901672f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(90, 90, 90, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(27.926f, 39.155f)
    lineTo(28.973001f, 17.805998f)
    lineTo(31.460001f, 14.142998f)
    cubicTo(33.431f, 13.506998f, 38.187f, 13.506998f, 39.967003f, 14.142998f)
    lineTo(42.454002f, 17.173998f)
    lineTo(45.071003f, 39.660995f)
    lineTo(27.926003f, 39.154995f)
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
// _0_0_10_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.828f, 38.896f)
    cubicTo(28.828f, 38.896f, 31.418999f, 29.365f, 36.369f, 29.365f)
    cubicTo(41.475f, 29.365f, 44.537f, 39.277f, 44.537f, 39.277f)
    lineTo(28.828f, 38.896f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(174, 174, 174, 255), 1.0f to Color(196, 196, 196, 0), start = Offset(36.682697f, 30.762218f), end = Offset(36.682697f, 39.27699f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.18f, 20.499f)
    lineTo(28.884f, 26.973f)
    cubicTo(30.547f, 25.895f, 40.877f, 25.709f, 43.228f, 27.361f)
    lineTo(42.526f, 20.764f)
    cubicTo(41.128002f, 19.811f, 31.943f, 19.286f, 29.18f, 20.499f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 159, 207, 255), 1.0f to Color(43, 85, 130, 255), start = Offset(33.942722f, 23.772156f), end = Offset(43.227444f, 23.772156f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.59444f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_5
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.049f, 38.222f)
    lineTo(29.926f, 18.028f)
    lineTo(31.766f, 14.995f)
    cubicTo(33.585f, 14.403f, 37.971f, 14.403f, 39.614002f, 14.995f)
    lineTo(41.575f, 17.528f)
    lineTo(44.027f, 38.776f)
    lineTo(29.049f, 38.222f)
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
// _0_0_10_6
shape = Outline.Rounded(roundRect = RoundRect(left = 26.51300048828125f, top = 37.5359992980957f, right = 46.48700141906738f, bottom = 41.463799238204956f,radiusX = 1.7677600383758545f, radiusY = 1.7677600383758545f))
brush = Brush.linearGradient(0.0f to Color(242, 242, 242, 255), 1.0f to Color(195, 195, 195, 255), start = Offset(35.831207f, 38.048103f), end = Offset(36.031754f, 40.93879f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(90, 90, 90, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 26.51300048828125f, top = 37.5359992980957f, right = 46.48700141906738f, bottom = 41.463799238204956f,radiusX = 1.7677600383758545f, radiusY = 1.7677600383758545f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.395f, 17.996f)
    cubicTo(31.27f, 16.630001f, 38.493f, 16.341f, 42.115f, 17.39f)
    lineTo(39.778f, 14.504f)
    cubicTo(37.3f, 13.837f, 33.853f, 14.127999f, 31.501999f, 14.573f)
    lineTo(29.394999f, 17.996f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.86111f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.173f, 17.416f)
    lineTo(31.173f, 32.873f)
    lineTo(34.408f, 29.278002f)
    lineTo(34.049f, 16.697002f)
    lineTo(31.173f, 17.416002f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(32.790306f, 18.67432f), end = Offset(32.790306f, 30.536335f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.57778f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_9
alphaStack.add(0, alpha)
alpha *= 0.41111f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_9_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.009f, 9.381f)
    lineTo(31.009f, 12.620999f)
    cubicTo(31.009f, 12.620999f, 31.621f, 12.273f, 32.0f, 12.259999f)
    lineTo(32.0f, 8.999999f)
    cubicTo(31.518f, 9.069499f, 31.009f, 9.380999f, 31.009f, 9.380999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(230, 231, 230, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_9_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.009f, 8.76f)
    lineTo(33.009f, 12.0f)
    cubicTo(33.009f, 12.0f, 33.621f, 11.917f, 34.0f, 11.904f)
    lineTo(34.0f, 8.588901f)
    cubicTo(33.488f, 8.6147f, 33.009f, 8.76f, 33.009f, 8.76f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(230, 231, 230, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.44444f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10_9_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.009f, 8.5611f)
    lineTo(35.009f, 11.856f)
    cubicTo(35.009f, 11.856f, 35.565f, 11.851f, 36.0f, 11.871f)
    lineTo(36.0f, 8.5337f)
    cubicTo(35.499f, 8.4821f, 35.009f, 8.5611f, 35.009f, 8.5611f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(230, 231, 230, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 0.39692071080207825
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
            return 44.624698638916016
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

