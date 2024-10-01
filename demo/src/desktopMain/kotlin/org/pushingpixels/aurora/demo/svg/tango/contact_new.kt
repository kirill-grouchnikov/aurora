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
class contact_new : Painter() {
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
alpha *= 0.3976608f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1009429693222046f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7564100027084351f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-4.579099178314209f, 8.809000015258789f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.125f, 38.125f)
    cubicTo(44.154446f, 39.871864f, 40.658646f, 41.489384f, 34.961304f, 42.365078f)
    cubicTo(29.263962f, 43.240772f, 22.236038f, 43.240772f, 16.538696f, 42.365078f)
    cubicTo(10.841355f, 41.489384f, 7.345554f, 39.871864f, 7.375f, 38.125f)
    cubicTo(7.345554f, 36.378136f, 10.841355f, 34.760616f, 16.538696f, 33.884922f)
    cubicTo(22.236038f, 33.009228f, 29.263962f, 33.009228f, 34.961304f, 33.884922f)
    cubicTo(40.658646f, 34.760616f, 44.154446f, 36.378136f, 44.125f, 38.125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(25.75f, 38.124992f), radius = 18.375f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 2.5534627437591553f, top = 8.5f, right = 46.451141119003296f, bottom = 38.5f,radiusX = 5.5f, radiusY = 5.5f))
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(221, 221, 221, 255), start = Offset(16.124355f, 11.75f), end = Offset(33.018734f, 36.125f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(147, 147, 147, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 2.5534627437591553f, top = 8.5f, right = 46.451141119003296f, bottom = 38.5f,radiusX = 5.5f, radiusY = 5.5f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 3.5477371215820312f, top = 9.629325866699219f, right = 45.44866180419922f, bottom = 37.37068557739258f,radiusX = 3.5000007152557373f, radiusY = 3.500000476837158f))
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
0.43479999899864197f, 0.0f, 0.0f, 0.0f,
0.0f, 0.43479999899864197f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-4.122330188751221f, 8.441450119018555f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.62349f, 41.30637f)
    lineTo(43.23009f, 41.30637f)
    cubicTo(46.235294f, 41.30637f, 49.210575f, 40.204437f, 50.30116f, 37.06373f)
    cubicTo(51.336796f, 34.081253f, 50.477936f, 28.40167f, 43.76042f, 23.805477f)
    lineTo(31.209274f, 23.805477f)
    cubicTo(24.49176f, 28.048117f, 23.652283f, 33.850307f, 25.198868f, 37.240505f)
    cubicTo(26.774462f, 40.694294f, 29.441507f, 41.30637f, 32.62349f, 41.30637f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(114, 126, 10, 255), 1.0f to Color(91, 101, 8, 255), center = Offset(35.97705f, 28.430925f), radius = 13.565361f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(64, 70, 4, 255))
stroke = Stroke(width=2.299906f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.62349f, 41.30637f)
    lineTo(43.23009f, 41.30637f)
    cubicTo(46.235294f, 41.30637f, 49.210575f, 40.204437f, 50.30116f, 37.06373f)
    cubicTo(51.336796f, 34.081253f, 50.477936f, 28.40167f, 43.76042f, 23.805477f)
    lineTo(31.209274f, 23.805477f)
    cubicTo(24.49176f, 28.048117f, 23.652283f, 33.850307f, 25.198868f, 37.240505f)
    cubicTo(26.774462f, 40.694294f, 29.441507f, 41.30637f, 32.62349f, 41.30637f)
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
// _0_0_3_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.69468f, 26.457129f)
    cubicTo(37.69468f, 26.457129f, 35.543358f, 28.117462f, 35.72869f, 30.11766f)
    cubicTo(33.687466f, 28.316868f, 33.62882f, 24.866137f, 33.62882f, 24.866137f)
    lineTo(37.69468f, 26.457129f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(157, 176, 41, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.21518986f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=2.2999048f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.2112f, 39.48254f)
    lineTo(42.508293f, 39.48254f)
    cubicTo(44.88714f, 39.48254f, 47.242294f, 38.610283f, 48.105576f, 36.124172f)
    cubicTo(48.92536f, 33.76332f, 48.020214f, 29.267504f, 42.702797f, 25.629272f)
    lineTo(32.317036f, 25.629272f)
    cubicTo(26.99962f, 28.98764f, 26.10982f, 33.58051f, 27.334055f, 36.264107f)
    cubicTo(28.581259f, 38.99804f, 30.692423f, 39.48254f, 33.2112f, 39.48254f)
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
// _0_0_3_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.673107f, 26.457129f)
    cubicTo(38.673107f, 26.457129f, 40.824432f, 28.117462f, 40.6391f, 30.11766f)
    cubicTo(42.680325f, 28.316868f, 42.73897f, 24.866137f, 42.73897f, 24.866137f)
    lineTo(38.673107f, 26.457129f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(157, 176, 41, 255))
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
6.5123138427734375f, 3.1703310012817383f, 0.0f, 1.0f)
))}){
// _0_0_3_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.774754f, 19.008621f)
    cubicTo(39.788635f, 22.112505f, 38.140697f, 24.986568f, 35.454945f, 26.54253f)
    cubicTo(32.769196f, 28.098494f, 29.4562f, 28.098494f, 26.770447f, 26.54253f)
    cubicTo(24.084696f, 24.986568f, 22.436758f, 22.112505f, 22.45064f, 19.008621f)
    cubicTo(22.436758f, 15.904738f, 24.084696f, 13.030674f, 26.770447f, 11.474711f)
    cubicTo(29.4562f, 9.918749f, 32.769196f, 9.918749f, 35.454945f, 11.474711f)
    cubicTo(38.140697f, 13.030674f, 39.788635f, 15.904738f, 39.774754f, 19.008621f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(31.112698f, 19.008621f), radius = 8.66206f, tileMode = TileMode.Clamp)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
6.6373138427734375f, -0.3296689987182617f, 0.0f, 1.0f)
))}){
// _0_0_3_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.774754f, 19.008621f)
    cubicTo(39.788635f, 22.112505f, 38.140697f, 24.986568f, 35.454945f, 26.54253f)
    cubicTo(32.769196f, 28.098494f, 29.4562f, 28.098494f, 26.770447f, 26.54253f)
    cubicTo(24.084696f, 24.986568f, 22.436758f, 22.112505f, 22.45064f, 19.008621f)
    cubicTo(22.436758f, 15.904738f, 24.084696f, 13.030674f, 26.770447f, 11.474711f)
    cubicTo(29.4562f, 9.918749f, 32.769196f, 9.918749f, 35.454945f, 11.474711f)
    cubicTo(38.140697f, 13.030674f, 39.788635f, 15.904738f, 39.774754f, 19.008621f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(233, 177, 94, 255), 1.0f to Color(150, 100, 22, 255), center = Offset(29.344944f, 17.064085f), radius = 7.2196846f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(111, 71, 9, 255))
stroke = Stroke(width=2.299906f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.774754f, 19.008621f)
    cubicTo(39.788635f, 22.112505f, 38.140697f, 24.986568f, 35.454945f, 26.54253f)
    cubicTo(32.769196f, 28.098494f, 29.4562f, 28.098494f, 26.770447f, 26.54253f)
    cubicTo(24.084696f, 24.986568f, 22.436758f, 22.112505f, 22.45064f, 19.008621f)
    cubicTo(22.436758f, 15.904738f, 24.084696f, 13.030674f, 26.770447f, 11.474711f)
    cubicTo(29.4562f, 9.918749f, 32.769196f, 9.918749f, 35.454945f, 11.474711f)
    cubicTo(38.140697f, 13.030674f, 39.788635f, 15.904738f, 39.774754f, 19.008621f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.12658231f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7473769783973694f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7473769783973694f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
14.497119903564453f, 4.472360134124756f, 0.0f, 1.0f)
))}){
// _0_0_3_6
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=3.0773065f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.774754f, 19.008621f)
    cubicTo(39.788635f, 22.112505f, 38.140697f, 24.986568f, 35.454945f, 26.54253f)
    cubicTo(32.769196f, 28.098494f, 29.4562f, 28.098494f, 26.770447f, 26.54253f)
    cubicTo(24.084696f, 24.986568f, 22.436758f, 22.112505f, 22.45064f, 19.008621f)
    cubicTo(22.436758f, 15.904738f, 24.084696f, 13.030674f, 26.770447f, 11.474711f)
    cubicTo(29.4562f, 9.918749f, 32.769196f, 9.918749f, 35.454945f, 11.474711f)
    cubicTo(38.140697f, 13.030674f, 39.788635f, 15.904738f, 39.774754f, 19.008621f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(42.346207f, 33.70497f)
    lineTo(46.58885f, 33.70497f)
    lineTo(44.113976f, 31.406876f)
    lineTo(43.583645f, 32.113983f)
    lineTo(43.053314f, 31.583652f)
    lineTo(42.346207f, 33.70497f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(201, 201, 201, 255), start = Offset(38.280346f, 29.223816f), end = Offset(38.280346f, 35.473816f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.22784807f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.310268f, 40.27208f)
    cubicTo(27.06266f, 39.727108f, 26.504274f, 38.4138f, 26.504274f, 38.4138f)
    cubicTo(27.345554f, 34.344666f, 30.2242f, 31.367584f, 30.2242f, 31.367584f)
    cubicTo(30.2242f, 31.367584f, 27.944878f, 37.7791f, 28.310268f, 40.27208f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(26.504272f, 35.819824f), end = Offset(28.364231f, 36.569828f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.22784807f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(47.215977f, 39.413727f)
    cubicTo(48.447227f, 38.83275f, 49.020355f, 37.411407f, 49.020355f, 37.411407f)
    cubicTo(48.061234f, 33.368423f, 45.044205f, 30.568584f, 45.044205f, 30.568584f)
    cubicTo(45.044205f, 30.568584f, 47.5088f, 36.911186f, 47.215977f, 39.413727f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(47.032272f, 35.89594f), end = Offset(48.145348f, 34.991154f), tileMode = TileMode.Clamp)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(21.342953f, 15.75347f)
    cubicTo(21.34295f, 16.130426f, 21.254414f, 16.419514f, 21.07734f, 16.620735f)
    cubicTo(20.900263f, 16.820616f, 20.658796f, 16.920555f, 20.352942f, 16.920555f)
    cubicTo(20.228184f, 16.920555f, 20.108791f, 16.89708f, 19.994766f, 16.850128f)
    lineTo(19.994766f, 16.314878f)
    cubicTo(20.095377f, 16.391342f, 20.20672f, 16.429575f, 20.328794f, 16.429575f)
    cubicTo(20.479038f, 16.429575f, 20.591724f, 16.373903f, 20.666847f, 16.262562f)
    cubicTo(20.74331f, 16.149878f, 20.781544f, 15.981522f, 20.781544f, 15.757494f)
    lineTo(20.781544f, 13.972657f)
    lineTo(21.342953f, 13.972657f)
    lineTo(21.342953f, 15.75347f)
    moveTo(22.91047f, 14.723214f)
    cubicTo(23.224375f, 14.723216f, 23.47456f, 14.819803f, 23.661028f, 15.012973f)
    cubicTo(23.84749f, 15.206148f, 23.940723f, 15.465724f, 23.940725f, 15.791702f)
    cubicTo(23.940723f, 16.145853f, 23.840782f, 16.422197f, 23.640905f, 16.620735f)
    cubicTo(23.441023f, 16.817932f, 23.172728f, 16.91653f, 22.836018f, 16.916533f)
    cubicTo(22.526134f, 16.91653f, 22.273937f, 16.819275f, 22.079424f, 16.62476f)
    cubicTo(21.886251f, 16.430246f, 21.789663f, 16.175365f, 21.789665f, 15.860117f)
    cubicTo(21.789663f, 15.505968f, 21.890945f, 15.228282f, 22.09351f, 15.027059f)
    cubicTo(22.296072f, 14.824498f, 22.568392f, 14.723216f, 22.91047f, 14.723214f)
    moveTo(22.86419f, 16.483906f)
    cubicTo(23.027847f, 16.483906f, 23.160654f, 16.42555f, 23.262608f, 16.308842f)
    cubicTo(23.3659f, 16.190792f, 23.417547f, 16.030485f, 23.41755f, 15.827922f)
    cubicTo(23.417547f, 15.622677f, 23.366571f, 15.461029f, 23.26462f, 15.342977f)
    cubicTo(23.162666f, 15.223587f, 23.031872f, 15.163891f, 22.872238f, 15.16389f)
    cubicTo(22.713942f, 15.163891f, 22.579794f, 15.22627f, 22.469793f, 15.351026f)
    cubicTo(22.359793f, 15.474443f, 22.304792f, 15.637433f, 22.304792f, 15.839995f)
    cubicTo(22.304792f, 16.039877f, 22.358452f, 16.1975f, 22.46577f, 16.312866f)
    cubicTo(22.574429f, 16.426891f, 22.707235f, 16.483906f, 22.86419f, 16.483906f)
    moveTo(26.282948f, 16.858177f)
    lineTo(25.743673f, 16.858177f)
    lineTo(25.743673f, 15.729323f)
    cubicTo(25.743671f, 15.540176f, 25.714828f, 15.403345f, 25.657146f, 15.318831f)
    cubicTo(25.599463f, 15.232978f, 25.500864f, 15.19005f, 25.361351f, 15.190049f)
    cubicTo(25.239277f, 15.19005f, 25.137995f, 15.236331f, 25.057507f, 15.328892f)
    cubicTo(24.977016f, 15.421455f, 24.936773f, 15.539505f, 24.936773f, 15.683042f)
    lineTo(24.936773f, 16.858177f)
    lineTo(24.391462f, 16.858177f)
    lineTo(24.391462f, 13.807655f)
    lineTo(24.936773f, 13.807655f)
    lineTo(24.936773f, 15.085413f)
    cubicTo(25.010553f, 14.964682f, 25.102446f, 14.874132f, 25.212446f, 14.813764f)
    cubicTo(25.32379f, 14.753399f, 25.453241f, 14.723216f, 25.600805f, 14.723214f)
    cubicTo(25.827515f, 14.723216f, 25.99788f, 14.793644f, 26.11191f, 14.934497f)
    cubicTo(26.225931f, 15.075354f, 26.282946f, 15.286637f, 26.282948f, 15.568346f)
    lineTo(26.282948f, 16.858177f)
    moveTo(28.75395f, 16.858177f)
    lineTo(28.214676f, 16.858177f)
    lineTo(28.214676f, 15.729323f)
    cubicTo(28.214676f, 15.540176f, 28.185833f, 15.403345f, 28.12815f, 15.318831f)
    cubicTo(28.070465f, 15.232978f, 27.971867f, 15.19005f, 27.832355f, 15.190049f)
    cubicTo(27.71028f, 15.19005f, 27.608997f, 15.236331f, 27.52851f, 15.328892f)
    cubicTo(27.44802f, 15.421455f, 27.407776f, 15.539505f, 27.407778f, 15.683042f)
    lineTo(27.407778f, 16.858177f)
    lineTo(26.862467f, 16.858177f)
    lineTo(26.862467f, 14.797666f)
    lineTo(27.407778f, 14.797666f)
    lineTo(27.407778f, 15.085413f)
    cubicTo(27.481558f, 14.964682f, 27.573448f, 14.874132f, 27.68345f, 14.813764f)
    cubicTo(27.794792f, 14.753399f, 27.924246f, 14.723216f, 28.07181f, 14.723214f)
    cubicTo(28.298517f, 14.723216f, 28.468885f, 14.793644f, 28.582912f, 14.934497f)
    cubicTo(28.696936f, 15.075354f, 28.753948f, 15.286637f, 28.75395f, 15.568346f)
    lineTo(28.75395f, 16.858177f)
    moveTo(30.518665f, 13.972657f)
    lineTo(31.5288f, 13.972657f)
    cubicTo(32.017097f, 13.97266f, 32.38332f, 14.090039f, 32.62747f, 14.324795f)
    cubicTo(32.87296f, 14.559556f, 32.995705f, 14.911023f, 32.995705f, 15.379197f)
    cubicTo(32.995705f, 15.831276f, 32.857533f, 16.190792f, 32.58119f, 16.457747f)
    cubicTo(32.306183f, 16.7247f, 31.933252f, 16.858177f, 31.462397f, 16.858177f)
    lineTo(30.518665f, 16.858177f)
    lineTo(30.518665f, 13.972657f)
    moveTo(31.080074f, 16.413477f)
    lineTo(31.450323f, 16.413477f)
    cubicTo(31.753494f, 16.413477f, 31.991608f, 16.322927f, 32.16466f, 16.141829f)
    cubicTo(32.33905f, 15.960729f, 32.426247f, 15.713226f, 32.42625f, 15.399319f)
    cubicTo(32.426247f, 15.094805f, 32.341732f, 14.858035f, 32.17271f, 14.689006f)
    cubicTo(32.00368f, 14.519982f, 31.768251f, 14.435469f, 31.466421f, 14.435467f)
    lineTo(31.080074f, 14.435467f)
    lineTo(31.080074f, 16.413477f)
    moveTo(34.436455f, 14.723214f)
    cubicTo(34.75036f, 14.723216f, 35.00054f, 14.819803f, 35.18701f, 15.012973f)
    cubicTo(35.373474f, 15.206148f, 35.466705f, 15.465724f, 35.46671f, 15.791702f)
    cubicTo(35.466705f, 16.145853f, 35.366768f, 16.422197f, 35.16689f, 16.620735f)
    cubicTo(34.967007f, 16.817932f, 34.69871f, 16.91653f, 34.362003f, 16.916533f)
    cubicTo(34.05212f, 16.91653f, 33.79992f, 16.819275f, 33.605408f, 16.62476f)
    cubicTo(33.412235f, 16.430246f, 33.315647f, 16.175365f, 33.315647f, 15.860117f)
    cubicTo(33.315647f, 15.505968f, 33.41693f, 15.228282f, 33.61949f, 15.027059f)
    cubicTo(33.822056f, 14.824498f, 34.094376f, 14.723216f, 34.436455f, 14.723214f)
    moveTo(34.390175f, 16.483906f)
    cubicTo(34.553833f, 16.483906f, 34.686638f, 16.42555f, 34.788593f, 16.308842f)
    cubicTo(34.891884f, 16.190792f, 34.94353f, 16.030485f, 34.94353f, 15.827922f)
    cubicTo(34.94353f, 15.622677f, 34.892555f, 15.461029f, 34.790604f, 15.342977f)
    cubicTo(34.68865f, 15.223587f, 34.557858f, 15.163891f, 34.39822f, 15.16389f)
    cubicTo(34.239925f, 15.163891f, 34.105778f, 15.22627f, 33.995777f, 15.351026f)
    cubicTo(33.885777f, 15.474443f, 33.830776f, 15.637433f, 33.830776f, 15.839995f)
    cubicTo(33.830776f, 16.039877f, 33.884434f, 16.1975f, 33.991753f, 16.312866f)
    cubicTo(34.10041f, 16.426891f, 34.23322f, 16.483906f, 34.390175f, 16.483906f)
    moveTo(37.690212f, 15.946642f)
    lineTo(36.31788f, 15.946642f)
    cubicTo(36.31788f, 16.11567f, 36.369522f, 16.247133f, 36.47282f, 16.341038f)
    cubicTo(36.577454f, 16.4336f, 36.722332f, 16.479881f, 36.90746f, 16.47988f)
    cubicTo(37.111362f, 16.479881f, 37.307888f, 16.418842f, 37.49704f, 16.296768f)
    lineTo(37.49704f, 16.74348f)
    cubicTo(37.285084f, 16.853481f, 37.04764f, 16.908484f, 36.784714f, 16.908484f)
    cubicTo(36.46678f, 16.908484f, 36.217937f, 16.81525f, 36.03818f, 16.628784f)
    cubicTo(35.859764f, 16.44232f, 35.770554f, 16.187439f, 35.770554f, 15.864142f)
    cubicTo(35.770554f, 15.515358f, 35.8658f, 15.237673f, 36.05629f, 15.031083f)
    cubicTo(36.24812f, 14.823156f, 36.500317f, 14.719192f, 36.812885f, 14.71919f)
    cubicTo(37.087887f, 14.719192f, 37.30252f, 14.805046f, 37.456795f, 14.976753f)
    cubicTo(37.612404f, 15.147123f, 37.69021f, 15.385235f, 37.690212f, 15.691091f)
    lineTo(37.690212f, 15.946642f)
    moveTo(37.187157f, 15.584443f)
    cubicTo(37.187157f, 15.440906f, 37.150936f, 15.328893f, 37.078495f, 15.248403f)
    cubicTo(37.007397f, 15.167916f, 36.906116f, 15.127672f, 36.77465f, 15.12767f)
    cubicTo(36.65526f, 15.127672f, 36.551964f, 15.169257f, 36.46477f, 15.252427f)
    cubicTo(36.37757f, 15.334259f, 36.32861f, 15.444931f, 36.31788f, 15.584443f)
    lineTo(37.187157f, 15.584443f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3976608f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
shape = Outline.Rounded(roundRect = RoundRect(left = 21.0f, top = 19.977853775024414f, right = 38.375f, bottom = 21.977853775024414f,radiusX = 2.0625f, radiusY = 2.0f))
brush = SolidColor(Color(141, 141, 141, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3976608f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6
shape = Outline.Rounded(roundRect = RoundRect(left = 21.0f, top = 23.0f, right = 35.0f, bottom = 25.0f,radiusX = 2.0625f, radiusY = 2.0f))
brush = SolidColor(Color(141, 141, 141, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4853801f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8333330154418945f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 5.0f, 0.0f, 1.0f)
))}){
// _0_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_0
shape = Outline.Rectangle(rect = Rect(left = 6.0f, top = 30.0f, right = 7.0f, bottom = 36.0f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_1
shape = Outline.Rectangle(rect = Rect(left = 8.08464527130127f, top = 30.0f, right = 10.02214527130127f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_2
shape = Outline.Rectangle(rect = Rect(left = 11.0f, top = 30.0f, right = 12.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_3
shape = Outline.Rectangle(rect = Rect(left = 13.0f, top = 30.0f, right = 15.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_4
shape = Outline.Rectangle(rect = Rect(left = 16.0f, top = 30.0f, right = 17.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_5
shape = Outline.Rectangle(rect = Rect(left = 18.0f, top = 30.0f, right = 19.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_6
shape = Outline.Rectangle(rect = Rect(left = 21.0f, top = 30.0f, right = 22.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_7
shape = Outline.Rectangle(rect = Rect(left = 23.0f, top = 30.0f, right = 25.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_8
shape = Outline.Rectangle(rect = Rect(left = 26.0f, top = 30.0f, right = 28.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_9
shape = Outline.Rectangle(rect = Rect(left = 29.0f, top = 30.0f, right = 30.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_10
shape = Outline.Rectangle(rect = Rect(left = 31.0f, top = 30.0f, right = 32.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_11
shape = Outline.Rectangle(rect = Rect(left = 33.0f, top = 30.0f, right = 34.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_12
shape = Outline.Rectangle(rect = Rect(left = 35.0f, top = 30.0f, right = 37.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_13
shape = Outline.Rectangle(rect = Rect(left = 39.0f, top = 30.0f, right = 41.0f, bottom = 33.60001349449158f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_14
shape = Outline.Rectangle(rect = Rect(left = 42.0f, top = 30.0f, right = 43.0f, bottom = 36.0f))
brush = SolidColor(Color(0, 0, 0, 255))
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
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.937491f, 34.398197f)
    cubicTo(9.012451f, 34.414223f, 9.0708685f, 34.447567f, 9.112743f, 34.49823f)
    cubicTo(9.155133f, 34.548893f, 9.17633f, 34.611446f, 9.176331f, 34.68589f)
    cubicTo(9.17633f, 34.80014f, 9.13704f, 34.88854f, 9.058462f, 34.95109f)
    cubicTo(8.979882f, 35.013645f, 8.868217f, 35.04492f, 8.723468f, 35.04492f)
    cubicTo(8.674872f, 35.04492f, 8.624726f, 35.040012f, 8.57303f, 35.03019f)
    cubicTo(8.521851f, 35.02088f, 8.468862f, 35.006668f, 8.414063f, 34.987537f)
    lineTo(8.414063f, 34.836327f)
    cubicTo(8.457488f, 34.861656f, 8.505049f, 34.880787f, 8.556746f, 34.89371f)
    cubicTo(8.608442f, 34.906635f, 8.662465f, 34.913097f, 8.718815f, 34.913097f)
    cubicTo(8.817038f, 34.913097f, 8.89174f, 34.89371f, 8.94292f, 34.85494f)
    cubicTo(8.994616f, 34.816166f, 9.020464f, 34.759815f, 9.020465f, 34.68589f)
    cubicTo(9.020464f, 34.61765f, 8.996425f, 34.564404f, 8.948348f, 34.526146f)
    cubicTo(8.900786f, 34.487373f, 8.834356f, 34.467987f, 8.749058f, 34.467987f)
    lineTo(8.614129f, 34.467987f)
    lineTo(8.614129f, 34.339264f)
    lineTo(8.75526f, 34.339264f)
    cubicTo(8.832289f, 34.339264f, 8.891223f, 34.324013f, 8.932063f, 34.29351f)
    cubicTo(8.972903f, 34.262493f, 8.993323f, 34.218037f, 8.993324f, 34.160133f)
    cubicTo(8.993323f, 34.100685f, 8.972128f, 34.05519f, 8.929737f, 34.023655f)
    cubicTo(8.887862f, 33.991604f, 8.827636f, 33.97558f, 8.749058f, 33.97558f)
    cubicTo(8.706149f, 33.97558f, 8.660139f, 33.980232f, 8.611028f, 33.989536f)
    cubicTo(8.561915f, 33.99884f, 8.507893f, 34.013317f, 8.448958f, 34.03296f)
    lineTo(8.448958f, 33.89338f)
    cubicTo(8.5084095f, 33.87684f, 8.563983f, 33.86443f, 8.61568f, 33.85616f)
    cubicTo(8.667893f, 33.847885f, 8.717005f, 33.843754f, 8.763016f, 33.84375f)
    cubicTo(8.881917f, 33.843754f, 8.976005f, 33.87089f, 9.0452795f, 33.925175f)
    cubicTo(9.114552f, 33.97894f, 9.149189f, 34.05183f, 9.14919f, 34.14385f)
    cubicTo(9.149189f, 34.207954f, 9.1308365f, 34.262238f, 9.094132f, 34.306694f)
    cubicTo(9.057427f, 34.350636f, 9.005214f, 34.381138f, 8.937491f, 34.398197f)
    moveTo(9.609031f, 34.89061f)
    lineTo(10.155723f, 34.89061f)
    lineTo(10.155723f, 35.022434f)
    lineTo(9.420597f, 35.022434f)
    lineTo(9.420597f, 34.89061f)
    cubicTo(9.480048f, 34.82909f, 9.560953f, 34.74663f, 9.663313f, 34.643238f)
    cubicTo(9.766189f, 34.53933f, 9.83081f, 34.47238f, 9.857175f, 34.442398f)
    cubicTo(9.90732f, 34.386047f, 9.942216f, 34.33849f, 9.961861f, 34.299713f)
    cubicTo(9.982022f, 34.260426f, 9.992103f, 34.221912f, 9.992104f, 34.184174f)
    cubicTo(9.992103f, 34.122654f, 9.97039f, 34.07251f, 9.926966f, 34.033737f)
    cubicTo(9.884057f, 33.994965f, 9.827966f, 33.97558f, 9.758693f, 33.97558f)
    cubicTo(9.70958f, 33.97558f, 9.657626f, 33.984108f, 9.602828f, 34.001167f)
    cubicTo(9.548546f, 34.018227f, 9.490387f, 34.044075f, 9.428351f, 34.078712f)
    lineTo(9.428351f, 33.92052f)
    cubicTo(9.491421f, 33.89519f, 9.550355f, 33.87606f, 9.605154f, 33.863136f)
    cubicTo(9.659952f, 33.850212f, 9.710098f, 33.843754f, 9.755591f, 33.84375f)
    cubicTo(9.875526f, 33.843754f, 9.971166f, 33.873737f, 10.042508f, 33.933704f)
    cubicTo(10.113848f, 33.99367f, 10.149519f, 34.073803f, 10.14952f, 34.17409f)
    cubicTo(10.149519f, 34.221653f, 10.140472f, 34.266888f, 10.122379f, 34.309795f)
    cubicTo(10.104801f, 34.35219f, 10.072491f, 34.402332f, 10.025448f, 34.46023f)
    cubicTo(10.012523f, 34.475227f, 9.971424f, 34.51865f, 9.902151f, 34.590508f)
    cubicTo(9.832877f, 34.66185f, 9.73517f, 34.761883f, 9.609031f, 34.89061f)
    moveTo(10.915664f, 34.001167f)
    lineTo(10.520185f, 34.6192f)
    lineTo(10.915664f, 34.6192f)
    lineTo(10.915664f, 34.001167f)
    moveTo(10.874565f, 33.86469f)
    lineTo(11.07153f, 33.86469f)
    lineTo(11.07153f, 34.6192f)
    lineTo(11.2367f, 34.6192f)
    lineTo(11.2367f, 34.749477f)
    lineTo(11.07153f, 34.749477f)
    lineTo(11.07153f, 35.022434f)
    lineTo(10.915664f, 35.022434f)
    lineTo(10.915664f, 34.749477f)
    lineTo(10.393011f, 34.749477f)
    lineTo(10.393011f, 34.598263f)
    lineTo(10.874565f, 33.86469f)
    moveTo(11.498027f, 33.86469f)
    lineTo(12.112959f, 33.86469f)
    lineTo(12.112959f, 33.996513f)
    lineTo(11.641485f, 33.996513f)
    lineTo(11.641485f, 34.28033f)
    cubicTo(11.664231f, 34.272575f, 11.686978f, 34.266888f, 11.709725f, 34.263268f)
    cubicTo(11.732471f, 34.259132f, 11.755217f, 34.257065f, 11.777964f, 34.257065f)
    cubicTo(11.907205f, 34.257065f, 12.009565f, 34.292477f, 12.085042f, 34.3633f)
    cubicTo(12.160519f, 34.434128f, 12.198257f, 34.530025f, 12.198258f, 34.650993f)
    cubicTo(12.198257f, 34.77558f, 12.159485f, 34.872513f, 12.081941f, 34.941788f)
    cubicTo(12.004395f, 35.010544f, 11.895057f, 35.04492f, 11.753925f, 35.04492f)
    cubicTo(11.70533f, 35.04492f, 11.655701f, 35.040787f, 11.605039f, 35.032516f)
    cubicTo(11.554893f, 35.024242f, 11.502938f, 35.011837f, 11.449174f, 34.995293f)
    lineTo(11.449174f, 34.837875f)
    cubicTo(11.4957f, 34.86321f, 11.543778f, 34.882076f, 11.593407f, 34.894485f)
    cubicTo(11.643036f, 34.90689f, 11.695508f, 34.913097f, 11.750824f, 34.913097f)
    cubicTo(11.840258f, 34.913097f, 11.911083f, 34.889572f, 11.963297f, 34.84253f)
    cubicTo(12.01551f, 34.795486f, 12.041617f, 34.73164f, 12.041617f, 34.650993f)
    cubicTo(12.041617f, 34.570347f, 12.01551f, 34.5065f, 11.963297f, 34.459457f)
    cubicTo(11.911083f, 34.412415f, 11.840258f, 34.388893f, 11.750824f, 34.388893f)
    cubicTo(11.708949f, 34.388893f, 11.667075f, 34.393543f, 11.625201f, 34.40285f)
    cubicTo(11.583843f, 34.412155f, 11.541452f, 34.426632f, 11.498027f, 34.446274f)
    lineTo(11.498027f, 33.86469f)
    moveTo(13.34825f, 33.967823f)
    cubicTo(13.267603f, 33.967823f, 13.20686f, 34.00763f, 13.16602f, 34.087242f)
    cubicTo(13.125696f, 34.16634f, 13.105534f, 34.2855f, 13.105534f, 34.444725f)
    cubicTo(13.105534f, 34.60343f, 13.125696f, 34.722595f, 13.16602f, 34.802204f)
    cubicTo(13.20686f, 34.8813f, 13.267603f, 34.92085f, 13.34825f, 34.92085f)
    cubicTo(13.429413f, 34.92085f, 13.490157f, 34.8813f, 13.530481f, 34.802204f)
    cubicTo(13.571321f, 34.722595f, 13.591741f, 34.60343f, 13.591742f, 34.444725f)
    cubicTo(13.591741f, 34.2855f, 13.571321f, 34.16634f, 13.530481f, 34.087242f)
    cubicTo(13.490157f, 34.00763f, 13.429413f, 33.967823f, 13.34825f, 33.967823f)
    moveTo(13.34825f, 33.84375f)
    cubicTo(13.478008f, 33.843754f, 13.577007f, 33.89519f, 13.645248f, 33.998066f)
    cubicTo(13.714003f, 34.100426f, 13.748381f, 34.249313f, 13.748382f, 34.444725f)
    cubicTo(13.748381f, 34.63962f, 13.714003f, 34.788506f, 13.645248f, 34.891384f)
    cubicTo(13.577007f, 34.993744f, 13.478008f, 35.04492f, 13.34825f, 35.04492f)
    cubicTo(13.218491f, 35.04492f, 13.119234f, 34.993744f, 13.050478f, 34.891384f)
    cubicTo(12.982238f, 34.788506f, 12.948118f, 34.63962f, 12.948118f, 34.444725f)
    cubicTo(12.948118f, 34.249313f, 12.982238f, 34.100426f, 13.050478f, 33.998066f)
    cubicTo(13.119234f, 33.89519f, 13.218491f, 33.843754f, 13.34825f, 33.84375f)
    moveTo(14.025993f, 33.86469f)
    lineTo(14.640925f, 33.86469f)
    lineTo(14.640925f, 33.996513f)
    lineTo(14.169452f, 33.996513f)
    lineTo(14.169452f, 34.28033f)
    cubicTo(14.192198f, 34.272575f, 14.214944f, 34.266888f, 14.237691f, 34.263268f)
    cubicTo(14.260437f, 34.259132f, 14.283184f, 34.257065f, 14.305931f, 34.257065f)
    cubicTo(14.435172f, 34.257065f, 14.537531f, 34.292477f, 14.613009f, 34.3633f)
    cubicTo(14.688485f, 34.434128f, 14.726224f, 34.530025f, 14.726225f, 34.650993f)
    cubicTo(14.726224f, 34.77558f, 14.687451f, 34.872513f, 14.609907f, 34.941788f)
    cubicTo(14.532362f, 35.010544f, 14.423023f, 35.04492f, 14.281892f, 35.04492f)
    cubicTo(14.233297f, 35.04492f, 14.183668f, 35.040787f, 14.133006f, 35.032516f)
    cubicTo(14.08286f, 35.024242f, 14.030904f, 35.011837f, 13.97714f, 34.995293f)
    lineTo(13.97714f, 34.837875f)
    cubicTo(14.023667f, 34.86321f, 14.071745f, 34.882076f, 14.121374f, 34.894485f)
    cubicTo(14.171002f, 34.90689f, 14.223474f, 34.913097f, 14.27879f, 34.913097f)
    cubicTo(14.368225f, 34.913097f, 14.439049f, 34.889572f, 14.491263f, 34.84253f)
    cubicTo(14.543476f, 34.795486f, 14.569583f, 34.73164f, 14.569584f, 34.650993f)
    cubicTo(14.569583f, 34.570347f, 14.543476f, 34.5065f, 14.491263f, 34.459457f)
    cubicTo(14.439049f, 34.412415f, 14.368225f, 34.388893f, 14.27879f, 34.388893f)
    cubicTo(14.236915f, 34.388893f, 14.195041f, 34.393543f, 14.153167f, 34.40285f)
    cubicTo(14.11181f, 34.412155f, 14.069418f, 34.426632f, 14.025993f, 34.446274f)
    lineTo(14.025993f, 33.86469f)
    moveTo(15.03718f, 33.86469f)
    lineTo(15.652112f, 33.86469f)
    lineTo(15.652112f, 33.996513f)
    lineTo(15.180638f, 33.996513f)
    lineTo(15.180638f, 34.28033f)
    cubicTo(15.203384f, 34.272575f, 15.226131f, 34.266888f, 15.248878f, 34.263268f)
    cubicTo(15.271624f, 34.259132f, 15.29437f, 34.257065f, 15.317117f, 34.257065f)
    cubicTo(15.446359f, 34.257065f, 15.548718f, 34.292477f, 15.624196f, 34.3633f)
    cubicTo(15.699672f, 34.434128f, 15.73741f, 34.530025f, 15.737411f, 34.650993f)
    cubicTo(15.73741f, 34.77558f, 15.698638f, 34.872513f, 15.621094f, 34.941788f)
    cubicTo(15.543548f, 35.010544f, 15.43421f, 35.04492f, 15.293078f, 35.04492f)
    cubicTo(15.244483f, 35.04492f, 15.194855f, 35.040787f, 15.144192f, 35.032516f)
    cubicTo(15.094046f, 35.024242f, 15.042091f, 35.011837f, 14.988327f, 34.995293f)
    lineTo(14.988327f, 34.837875f)
    cubicTo(15.034854f, 34.86321f, 15.082931f, 34.882076f, 15.13256f, 34.894485f)
    cubicTo(15.182189f, 34.90689f, 15.234661f, 34.913097f, 15.289977f, 34.913097f)
    cubicTo(15.379411f, 34.913097f, 15.450236f, 34.889572f, 15.50245f, 34.84253f)
    cubicTo(15.554663f, 34.795486f, 15.58077f, 34.73164f, 15.58077f, 34.650993f)
    cubicTo(15.58077f, 34.570347f, 15.554663f, 34.5065f, 15.50245f, 34.459457f)
    cubicTo(15.450236f, 34.412415f, 15.379411f, 34.388893f, 15.289977f, 34.388893f)
    cubicTo(15.248102f, 34.388893f, 15.206228f, 34.393543f, 15.164354f, 34.40285f)
    cubicTo(15.122996f, 34.412155f, 15.080605f, 34.426632f, 15.03718f, 34.446274f)
    lineTo(15.03718f, 33.86469f)
    moveTo(16.007269f, 33.86469f)
    lineTo(16.7517f, 33.86469f)
    lineTo(16.7517f, 33.931377f)
    lineTo(16.331406f, 35.022434f)
    lineTo(16.167786f, 35.022434f)
    lineTo(16.563265f, 33.996513f)
    lineTo(16.007269f, 33.996513f)
    lineTo(16.007269f, 33.86469f)
    moveTo(17.565147f, 33.86469f)
    lineTo(18.180079f, 33.86469f)
    lineTo(18.180079f, 33.996513f)
    lineTo(17.708605f, 33.996513f)
    lineTo(17.708605f, 34.28033f)
    cubicTo(17.731352f, 34.272575f, 17.754097f, 34.266888f, 17.776844f, 34.263268f)
    cubicTo(17.79959f, 34.259132f, 17.822336f, 34.257065f, 17.845083f, 34.257065f)
    cubicTo(17.974325f, 34.257065f, 18.076685f, 34.292477f, 18.152163f, 34.3633f)
    cubicTo(18.227638f, 34.434128f, 18.265377f, 34.530025f, 18.265379f, 34.650993f)
    cubicTo(18.265377f, 34.77558f, 18.226604f, 34.872513f, 18.14906f, 34.941788f)
    cubicTo(18.071514f, 35.010544f, 17.962175f, 35.04492f, 17.821045f, 35.04492f)
    cubicTo(17.77245f, 35.04492f, 17.72282f, 35.040787f, 17.67216f, 35.032516f)
    cubicTo(17.622013f, 35.024242f, 17.570059f, 35.011837f, 17.516293f, 34.995293f)
    lineTo(17.516293f, 34.837875f)
    cubicTo(17.56282f, 34.86321f, 17.610897f, 34.882076f, 17.660526f, 34.894485f)
    cubicTo(17.710155f, 34.90689f, 17.762627f, 34.913097f, 17.817944f, 34.913097f)
    cubicTo(17.907377f, 34.913097f, 17.978203f, 34.889572f, 18.030416f, 34.84253f)
    cubicTo(18.082628f, 34.795486f, 18.108736f, 34.73164f, 18.108738f, 34.650993f)
    cubicTo(18.108736f, 34.570347f, 18.082628f, 34.5065f, 18.030416f, 34.459457f)
    cubicTo(17.978203f, 34.412415f, 17.907377f, 34.388893f, 17.817944f, 34.388893f)
    cubicTo(17.77607f, 34.388893f, 17.734194f, 34.393543f, 17.69232f, 34.40285f)
    cubicTo(17.650963f, 34.412155f, 17.608572f, 34.426632f, 17.565147f, 34.446274f)
    lineTo(17.565147f, 33.86469f)
    moveTo(18.601923f, 34.89061f)
    lineTo(18.85782f, 34.89061f)
    lineTo(18.85782f, 34.00737f)
    lineTo(18.579435f, 34.0632f)
    lineTo(18.579435f, 33.92052f)
    lineTo(18.856272f, 33.86469f)
    lineTo(19.01291f, 33.86469f)
    lineTo(19.01291f, 34.89061f)
    lineTo(19.26881f, 34.89061f)
    lineTo(19.26881f, 35.022434f)
    lineTo(18.601923f, 35.022434f)
    lineTo(18.601923f, 34.89061f)
    moveTo(19.920963f, 33.967823f)
    cubicTo(19.840317f, 33.967823f, 19.779572f, 34.00763f, 19.738731f, 34.087242f)
    cubicTo(19.698408f, 34.16634f, 19.678247f, 34.2855f, 19.678247f, 34.444725f)
    cubicTo(19.678247f, 34.60343f, 19.698408f, 34.722595f, 19.738731f, 34.802204f)
    cubicTo(19.779572f, 34.8813f, 19.840317f, 34.92085f, 19.920963f, 34.92085f)
    cubicTo(20.002127f, 34.92085f, 20.06287f, 34.8813f, 20.103193f, 34.802204f)
    cubicTo(20.144033f, 34.722595f, 20.164454f, 34.60343f, 20.164454f, 34.444725f)
    cubicTo(20.164454f, 34.2855f, 20.144033f, 34.16634f, 20.103193f, 34.087242f)
    cubicTo(20.06287f, 34.00763f, 20.002127f, 33.967823f, 19.920963f, 33.967823f)
    moveTo(19.920963f, 33.84375f)
    cubicTo(20.05072f, 33.843754f, 20.14972f, 33.89519f, 20.21796f, 33.998066f)
    cubicTo(20.286716f, 34.100426f, 20.321095f, 34.249313f, 20.321095f, 34.444725f)
    cubicTo(20.321095f, 34.63962f, 20.286716f, 34.788506f, 20.21796f, 34.891384f)
    cubicTo(20.14972f, 34.993744f, 20.05072f, 35.04492f, 19.920963f, 35.04492f)
    cubicTo(19.791204f, 35.04492f, 19.691948f, 34.993744f, 19.62319f, 34.891384f)
    cubicTo(19.55495f, 34.788506f, 19.52083f, 34.63962f, 19.52083f, 34.444725f)
    cubicTo(19.52083f, 34.249313f, 19.55495f, 34.100426f, 19.62319f, 33.998066f)
    cubicTo(19.691948f, 33.89519f, 19.791204f, 33.843754f, 19.920963f, 33.84375f)
    moveTo(20.951536f, 34.381138f)
    cubicTo(20.881227f, 34.381138f, 20.825396f, 34.405178f, 20.784039f, 34.453255f)
    cubicTo(20.743198f, 34.50133f, 20.722778f, 34.567245f, 20.722778f, 34.650993f)
    cubicTo(20.722778f, 34.734226f, 20.743198f, 34.80014f, 20.784039f, 34.848732f)
    cubicTo(20.825396f, 34.896812f, 20.881227f, 34.92085f, 20.951536f, 34.92085f)
    cubicTo(21.021843f, 34.92085f, 21.077417f, 34.896812f, 21.118258f, 34.848732f)
    cubicTo(21.159615f, 34.80014f, 21.180292f, 34.734226f, 21.180294f, 34.650993f)
    cubicTo(21.180292f, 34.567245f, 21.159615f, 34.50133f, 21.118258f, 34.453255f)
    cubicTo(21.077417f, 34.405178f, 21.021843f, 34.381138f, 20.951536f, 34.381138f)
    moveTo(21.262491f, 33.890278f)
    lineTo(21.262491f, 34.03296f)
    cubicTo(21.223202f, 34.01435f, 21.183395f, 34.000134f, 21.143072f, 33.99031f)
    cubicTo(21.103266f, 33.980488f, 21.063717f, 33.97558f, 21.024427f, 33.97558f)
    cubicTo(20.921036f, 33.97558f, 20.84194f, 34.01047f, 20.787142f, 34.08026f)
    cubicTo(20.732859f, 34.150055f, 20.701841f, 34.255516f, 20.694086f, 34.396645f)
    cubicTo(20.724586f, 34.35167f, 20.762842f, 34.31729f, 20.808853f, 34.29351f)
    cubicTo(20.854862f, 34.269215f, 20.905525f, 34.257065f, 20.96084f, 34.257065f)
    cubicTo(21.077158f, 34.257065f, 21.16892f, 34.292477f, 21.236126f, 34.3633f)
    cubicTo(21.303848f, 34.43361f, 21.33771f, 34.529507f, 21.33771f, 34.650993f)
    cubicTo(21.33771f, 34.769897f, 21.302555f, 34.865276f, 21.232248f, 34.937134f)
    cubicTo(21.161942f, 35.00899f, 21.06837f, 35.04492f, 20.951536f, 35.04492f)
    cubicTo(20.81764f, 35.04492f, 20.715282f, 34.993744f, 20.644459f, 34.891384f)
    cubicTo(20.573633f, 34.788506f, 20.538221f, 34.63962f, 20.538221f, 34.444725f)
    cubicTo(20.538221f, 34.26172f, 20.581646f, 34.115932f, 20.668497f, 34.00737f)
    cubicTo(20.755346f, 33.898293f, 20.871923f, 33.843754f, 21.018225f, 33.84375f)
    cubicTo(21.057514f, 33.843754f, 21.097061f, 33.84763f, 21.13687f, 33.85538f)
    cubicTo(21.17719f, 33.863136f, 21.219065f, 33.87477f, 21.262491f, 33.890278f)
    moveTo(22.115486f, 33.86469f)
    lineTo(22.730417f, 33.86469f)
    lineTo(22.730417f, 33.996513f)
    lineTo(22.258944f, 33.996513f)
    lineTo(22.258944f, 34.28033f)
    cubicTo(22.28169f, 34.272575f, 22.304438f, 34.266888f, 22.327185f, 34.263268f)
    cubicTo(22.34993f, 34.259132f, 22.372677f, 34.257065f, 22.395424f, 34.257065f)
    cubicTo(22.524666f, 34.257065f, 22.627024f, 34.292477f, 22.702501f, 34.3633f)
    cubicTo(22.777979f, 34.434128f, 22.815718f, 34.530025f, 22.815718f, 34.650993f)
    cubicTo(22.815718f, 34.77558f, 22.776943f, 34.872513f, 22.6994f, 34.941788f)
    cubicTo(22.621855f, 35.010544f, 22.512516f, 35.04492f, 22.371386f, 35.04492f)
    cubicTo(22.322788f, 35.04492f, 22.27316f, 35.040787f, 22.222498f, 35.032516f)
    cubicTo(22.172352f, 35.024242f, 22.120398f, 35.011837f, 22.066633f, 34.995293f)
    lineTo(22.066633f, 34.837875f)
    cubicTo(22.11316f, 34.86321f, 22.161238f, 34.882076f, 22.210867f, 34.894485f)
    cubicTo(22.260494f, 34.90689f, 22.312967f, 34.913097f, 22.368282f, 34.913097f)
    cubicTo(22.457718f, 34.913097f, 22.528542f, 34.889572f, 22.580755f, 34.84253f)
    cubicTo(22.632969f, 34.795486f, 22.659077f, 34.73164f, 22.659077f, 34.650993f)
    cubicTo(22.659077f, 34.570347f, 22.632969f, 34.5065f, 22.580755f, 34.459457f)
    cubicTo(22.528542f, 34.412415f, 22.457718f, 34.388893f, 22.368282f, 34.388893f)
    cubicTo(22.326408f, 34.388893f, 22.284534f, 34.393543f, 22.24266f, 34.40285f)
    cubicTo(22.201302f, 34.412155f, 22.15891f, 34.426632f, 22.115486f, 34.446274f)
    lineTo(22.115486f, 33.86469f)
    moveTo(23.555496f, 34.001167f)
    lineTo(23.160017f, 34.6192f)
    lineTo(23.555496f, 34.6192f)
    lineTo(23.555496f, 34.001167f)
    moveTo(23.514399f, 33.86469f)
    lineTo(23.711363f, 33.86469f)
    lineTo(23.711363f, 34.6192f)
    lineTo(23.876534f, 34.6192f)
    lineTo(23.876534f, 34.749477f)
    lineTo(23.711363f, 34.749477f)
    lineTo(23.711363f, 35.022434f)
    lineTo(23.555496f, 35.022434f)
    lineTo(23.555496f, 34.749477f)
    lineTo(23.032843f, 34.749477f)
    lineTo(23.032843f, 34.598263f)
    lineTo(23.514399f, 33.86469f)
    moveTo(24.471304f, 33.967823f)
    cubicTo(24.390656f, 33.967823f, 24.329912f, 34.00763f, 24.289072f, 34.087242f)
    cubicTo(24.248749f, 34.16634f, 24.228586f, 34.2855f, 24.228586f, 34.444725f)
    cubicTo(24.228586f, 34.60343f, 24.248749f, 34.722595f, 24.289072f, 34.802204f)
    cubicTo(24.329912f, 34.8813f, 24.390656f, 34.92085f, 24.471304f, 34.92085f)
    cubicTo(24.552465f, 34.92085f, 24.61321f, 34.8813f, 24.653534f, 34.802204f)
    cubicTo(24.694372f, 34.722595f, 24.714792f, 34.60343f, 24.714794f, 34.444725f)
    cubicTo(24.714792f, 34.2855f, 24.694372f, 34.16634f, 24.653534f, 34.087242f)
    cubicTo(24.61321f, 34.00763f, 24.552465f, 33.967823f, 24.471304f, 33.967823f)
    moveTo(24.471304f, 33.84375f)
    cubicTo(24.60106f, 33.843754f, 24.70006f, 33.89519f, 24.7683f, 33.998066f)
    cubicTo(24.837055f, 34.100426f, 24.871433f, 34.249313f, 24.871435f, 34.444725f)
    cubicTo(24.871433f, 34.63962f, 24.837055f, 34.788506f, 24.7683f, 34.891384f)
    cubicTo(24.70006f, 34.993744f, 24.60106f, 35.04492f, 24.471304f, 35.04492f)
    cubicTo(24.341543f, 35.04492f, 24.242287f, 34.993744f, 24.17353f, 34.891384f)
    cubicTo(24.10529f, 34.788506f, 24.07117f, 34.63962f, 24.07117f, 34.444725f)
    cubicTo(24.07117f, 34.249313f, 24.10529f, 34.100426f, 24.17353f, 33.998066f)
    cubicTo(24.242287f, 33.89519f, 24.341543f, 33.843754f, 24.471304f, 33.84375f)
    moveTo(25.501875f, 34.381138f)
    cubicTo(25.431566f, 34.381138f, 25.375734f, 34.405178f, 25.334377f, 34.453255f)
    cubicTo(25.293537f, 34.50133f, 25.273117f, 34.567245f, 25.273117f, 34.650993f)
    cubicTo(25.273117f, 34.734226f, 25.293537f, 34.80014f, 25.334377f, 34.848732f)
    cubicTo(25.375734f, 34.896812f, 25.431566f, 34.92085f, 25.501875f, 34.92085f)
    cubicTo(25.572182f, 34.92085f, 25.627754f, 34.896812f, 25.668596f, 34.848732f)
    cubicTo(25.709953f, 34.80014f, 25.730633f, 34.734226f, 25.730633f, 34.650993f)
    cubicTo(25.730633f, 34.567245f, 25.709953f, 34.50133f, 25.668596f, 34.453255f)
    cubicTo(25.627754f, 34.405178f, 25.572182f, 34.381138f, 25.501875f, 34.381138f)
    moveTo(25.81283f, 33.890278f)
    lineTo(25.81283f, 34.03296f)
    cubicTo(25.77354f, 34.01435f, 25.733734f, 34.000134f, 25.69341f, 33.99031f)
    cubicTo(25.653605f, 33.980488f, 25.614056f, 33.97558f, 25.574766f, 33.97558f)
    cubicTo(25.471373f, 33.97558f, 25.392277f, 34.01047f, 25.337479f, 34.08026f)
    cubicTo(25.283197f, 34.150055f, 25.25218f, 34.255516f, 25.244425f, 34.396645f)
    cubicTo(25.274925f, 34.35167f, 25.313183f, 34.31729f, 25.359192f, 34.29351f)
    cubicTo(25.405203f, 34.269215f, 25.455864f, 34.257065f, 25.51118f, 34.257065f)
    cubicTo(25.627497f, 34.257065f, 25.719257f, 34.292477f, 25.786465f, 34.3633f)
    cubicTo(25.854187f, 34.43361f, 25.888048f, 34.529507f, 25.888048f, 34.650993f)
    cubicTo(25.888048f, 34.769897f, 25.852894f, 34.865276f, 25.782587f, 34.937134f)
    cubicTo(25.712278f, 35.00899f, 25.61871f, 35.04492f, 25.501875f, 35.04492f)
    cubicTo(25.36798f, 35.04492f, 25.265621f, 34.993744f, 25.194798f, 34.891384f)
    cubicTo(25.123972f, 34.788506f, 25.08856f, 34.63962f, 25.08856f, 34.444725f)
    cubicTo(25.08856f, 34.26172f, 25.131985f, 34.115932f, 25.218836f, 34.00737f)
    cubicTo(25.305687f, 33.898293f, 25.42226f, 33.843754f, 25.568563f, 33.84375f)
    cubicTo(25.607853f, 33.843754f, 25.6474f, 33.84763f, 25.687206f, 33.85538f)
    cubicTo(25.72753f, 33.863136f, 25.769403f, 33.87477f, 25.81283f, 33.890278f)
    moveTo(26.665827f, 33.86469f)
    lineTo(27.280758f, 33.86469f)
    lineTo(27.280758f, 33.996513f)
    lineTo(26.809284f, 33.996513f)
    lineTo(26.809284f, 34.28033f)
    cubicTo(26.83203f, 34.272575f, 26.854776f, 34.266888f, 26.877523f, 34.263268f)
    cubicTo(26.90027f, 34.259132f, 26.923016f, 34.257065f, 26.945763f, 34.257065f)
    cubicTo(27.075005f, 34.257065f, 27.177364f, 34.292477f, 27.252842f, 34.3633f)
    cubicTo(27.328318f, 34.434128f, 27.366056f, 34.530025f, 27.366056f, 34.650993f)
    cubicTo(27.366056f, 34.77558f, 27.327284f, 34.872513f, 27.24974f, 34.941788f)
    cubicTo(27.172194f, 35.010544f, 27.062857f, 35.04492f, 26.921724f, 35.04492f)
    cubicTo(26.873129f, 35.04492f, 26.8235f, 35.040787f, 26.772839f, 35.032516f)
    cubicTo(26.722692f, 35.024242f, 26.670736f, 35.011837f, 26.616974f, 34.995293f)
    lineTo(26.616974f, 34.837875f)
    cubicTo(26.6635f, 34.86321f, 26.711576f, 34.882076f, 26.761206f, 34.894485f)
    cubicTo(26.810835f, 34.90689f, 26.863308f, 34.913097f, 26.918623f, 34.913097f)
    cubicTo(27.008057f, 34.913097f, 27.078882f, 34.889572f, 27.131096f, 34.84253f)
    cubicTo(27.18331f, 34.795486f, 27.209415f, 34.73164f, 27.209415f, 34.650993f)
    cubicTo(27.209415f, 34.570347f, 27.18331f, 34.5065f, 27.131096f, 34.459457f)
    cubicTo(27.078882f, 34.412415f, 27.008057f, 34.388893f, 26.918623f, 34.388893f)
    cubicTo(26.876747f, 34.388893f, 26.834873f, 34.393543f, 26.793f, 34.40285f)
    cubicTo(26.751642f, 34.412155f, 26.709251f, 34.426632f, 26.665827f, 34.446274f)
    lineTo(26.665827f, 33.86469f)
    moveTo(28.105837f, 34.001167f)
    lineTo(27.710358f, 34.6192f)
    lineTo(28.105837f, 34.6192f)
    lineTo(28.105837f, 34.001167f)
    moveTo(28.064737f, 33.86469f)
    lineTo(28.261702f, 33.86469f)
    lineTo(28.261702f, 34.6192f)
    lineTo(28.426872f, 34.6192f)
    lineTo(28.426872f, 34.749477f)
    lineTo(28.261702f, 34.749477f)
    lineTo(28.261702f, 35.022434f)
    lineTo(28.105837f, 35.022434f)
    lineTo(28.105837f, 34.749477f)
    lineTo(27.583183f, 34.749477f)
    lineTo(27.583183f, 34.598263f)
    lineTo(28.064737f, 33.86469f)
    moveTo(29.041029f, 34.381138f)
    cubicTo(28.97072f, 34.381138f, 28.914888f, 34.405178f, 28.873531f, 34.453255f)
    cubicTo(28.832691f, 34.50133f, 28.812271f, 34.567245f, 28.812271f, 34.650993f)
    cubicTo(28.812271f, 34.734226f, 28.832691f, 34.80014f, 28.873531f, 34.848732f)
    cubicTo(28.914888f, 34.896812f, 28.97072f, 34.92085f, 29.041029f, 34.92085f)
    cubicTo(29.111336f, 34.92085f, 29.16691f, 34.896812f, 29.20775f, 34.848732f)
    cubicTo(29.249107f, 34.80014f, 29.269787f, 34.734226f, 29.269787f, 34.650993f)
    cubicTo(29.269787f, 34.567245f, 29.249107f, 34.50133f, 29.20775f, 34.453255f)
    cubicTo(29.16691f, 34.405178f, 29.111336f, 34.381138f, 29.041029f, 34.381138f)
    moveTo(29.351984f, 33.890278f)
    lineTo(29.351984f, 34.03296f)
    cubicTo(29.312695f, 34.01435f, 29.272888f, 34.000134f, 29.232565f, 33.99031f)
    cubicTo(29.192759f, 33.980488f, 29.15321f, 33.97558f, 29.11392f, 33.97558f)
    cubicTo(29.010527f, 33.97558f, 28.93143f, 34.01047f, 28.876633f, 34.08026f)
    cubicTo(28.822351f, 34.150055f, 28.791334f, 34.255516f, 28.783579f, 34.396645f)
    cubicTo(28.81408f, 34.35167f, 28.852337f, 34.31729f, 28.898346f, 34.29351f)
    cubicTo(28.944357f, 34.269215f, 28.995018f, 34.257065f, 29.050335f, 34.257065f)
    cubicTo(29.16665f, 34.257065f, 29.258413f, 34.292477f, 29.325619f, 34.3633f)
    cubicTo(29.393341f, 34.43361f, 29.427202f, 34.529507f, 29.427202f, 34.650993f)
    cubicTo(29.427202f, 34.769897f, 29.392048f, 34.865276f, 29.321741f, 34.937134f)
    cubicTo(29.251432f, 35.00899f, 29.157864f, 35.04492f, 29.041029f, 35.04492f)
    cubicTo(28.907133f, 35.04492f, 28.804775f, 34.993744f, 28.733952f, 34.891384f)
    cubicTo(28.663126f, 34.788506f, 28.627714f, 34.63962f, 28.627714f, 34.444725f)
    cubicTo(28.627714f, 34.26172f, 28.671139f, 34.115932f, 28.75799f, 34.00737f)
    cubicTo(28.84484f, 33.898293f, 28.961416f, 33.843754f, 29.107718f, 33.84375f)
    cubicTo(29.147005f, 33.843754f, 29.186554f, 33.84763f, 29.22636f, 33.85538f)
    cubicTo(29.266684f, 33.863136f, 29.308558f, 33.87477f, 29.351984f, 33.890278f)
    moveTo(29.699387f, 33.86469f)
    lineTo(30.314318f, 33.86469f)
    lineTo(30.314318f, 33.996513f)
    lineTo(29.842844f, 33.996513f)
    lineTo(29.842844f, 34.28033f)
    cubicTo(29.86559f, 34.272575f, 29.888336f, 34.266888f, 29.911083f, 34.263268f)
    cubicTo(29.93383f, 34.259132f, 29.956575f, 34.257065f, 29.979322f, 34.257065f)
    cubicTo(30.108564f, 34.257065f, 30.210922f, 34.292477f, 30.286402f, 34.3633f)
    cubicTo(30.361877f, 34.434128f, 30.399616f, 34.530025f, 30.399616f, 34.650993f)
    cubicTo(30.399616f, 34.77558f, 30.360844f, 34.872513f, 30.283298f, 34.941788f)
    cubicTo(30.205753f, 35.010544f, 30.096415f, 35.04492f, 29.955284f, 35.04492f)
    cubicTo(29.906689f, 35.04492f, 29.85706f, 35.040787f, 29.806398f, 35.032516f)
    cubicTo(29.756252f, 35.024242f, 29.704296f, 35.011837f, 29.650532f, 34.995293f)
    lineTo(29.650532f, 34.837875f)
    cubicTo(29.69706f, 34.86321f, 29.745136f, 34.882076f, 29.794765f, 34.894485f)
    cubicTo(29.844395f, 34.90689f, 29.896868f, 34.913097f, 29.952183f, 34.913097f)
    cubicTo(30.041616f, 34.913097f, 30.11244f, 34.889572f, 30.164656f, 34.84253f)
    cubicTo(30.21687f, 34.795486f, 30.242975f, 34.73164f, 30.242975f, 34.650993f)
    cubicTo(30.242975f, 34.570347f, 30.21687f, 34.5065f, 30.164656f, 34.459457f)
    cubicTo(30.11244f, 34.412415f, 30.041616f, 34.388893f, 29.952183f, 34.388893f)
    cubicTo(29.910309f, 34.388893f, 29.868433f, 34.393543f, 29.82656f, 34.40285f)
    cubicTo(29.785202f, 34.412155f, 29.742811f, 34.426632f, 29.699387f, 34.446274f)
    lineTo(29.699387f, 33.86469f)
    moveTo(31.175066f, 33.86469f)
    lineTo(31.919498f, 33.86469f)
    lineTo(31.919498f, 33.931377f)
    lineTo(31.499205f, 35.022434f)
    lineTo(31.335585f, 35.022434f)
    lineTo(31.731064f, 33.996513f)
    lineTo(31.175066f, 33.996513f)
    lineTo(31.175066f, 33.86469f)
    moveTo(32.5608f, 33.967823f)
    cubicTo(32.48015f, 33.967823f, 32.419407f, 34.00763f, 32.378567f, 34.087242f)
    cubicTo(32.33824f, 34.16634f, 32.31808f, 34.2855f, 32.31808f, 34.444725f)
    cubicTo(32.31808f, 34.60343f, 32.33824f, 34.722595f, 32.378567f, 34.802204f)
    cubicTo(32.419407f, 34.8813f, 32.48015f, 34.92085f, 32.5608f, 34.92085f)
    cubicTo(32.64196f, 34.92085f, 32.7027f, 34.8813f, 32.743027f, 34.802204f)
    cubicTo(32.783867f, 34.722595f, 32.804287f, 34.60343f, 32.804287f, 34.444725f)
    cubicTo(32.804287f, 34.2855f, 32.783867f, 34.16634f, 32.743027f, 34.087242f)
    cubicTo(32.7027f, 34.00763f, 32.64196f, 33.967823f, 32.5608f, 33.967823f)
    moveTo(32.5608f, 33.84375f)
    cubicTo(32.690556f, 33.843754f, 32.789555f, 33.89519f, 32.857796f, 33.998066f)
    cubicTo(32.92655f, 34.100426f, 32.96093f, 34.249313f, 32.96093f, 34.444725f)
    cubicTo(32.96093f, 34.63962f, 32.92655f, 34.788506f, 32.857796f, 34.891384f)
    cubicTo(32.789555f, 34.993744f, 32.690556f, 35.04492f, 32.5608f, 35.04492f)
    cubicTo(32.431038f, 35.04492f, 32.33178f, 34.993744f, 32.263023f, 34.891384f)
    cubicTo(32.194782f, 34.788506f, 32.160664f, 34.63962f, 32.160664f, 34.444725f)
    cubicTo(32.160664f, 34.249313f, 32.194782f, 34.100426f, 32.263023f, 33.998066f)
    cubicTo(32.33178f, 33.89519f, 32.431038f, 33.843754f, 32.5608f, 33.84375f)
    moveTo(33.59137f, 34.381138f)
    cubicTo(33.52106f, 34.381138f, 33.465225f, 34.405178f, 33.42387f, 34.453255f)
    cubicTo(33.38303f, 34.50133f, 33.36261f, 34.567245f, 33.36261f, 34.650993f)
    cubicTo(33.36261f, 34.734226f, 33.38303f, 34.80014f, 33.42387f, 34.848732f)
    cubicTo(33.465225f, 34.896812f, 33.52106f, 34.92085f, 33.59137f, 34.92085f)
    cubicTo(33.661674f, 34.92085f, 33.717247f, 34.896812f, 33.758087f, 34.848732f)
    cubicTo(33.799446f, 34.80014f, 33.820126f, 34.734226f, 33.820126f, 34.650993f)
    cubicTo(33.820126f, 34.567245f, 33.799446f, 34.50133f, 33.758087f, 34.453255f)
    cubicTo(33.717247f, 34.405178f, 33.661674f, 34.381138f, 33.59137f, 34.381138f)
    moveTo(33.902325f, 33.890278f)
    lineTo(33.902325f, 34.03296f)
    cubicTo(33.863033f, 34.01435f, 33.823227f, 34.000134f, 33.782906f, 33.99031f)
    cubicTo(33.743095f, 33.980488f, 33.70355f, 33.97558f, 33.66426f, 33.97558f)
    cubicTo(33.560867f, 33.97558f, 33.48177f, 34.01047f, 33.42697f, 34.08026f)
    cubicTo(33.37269f, 34.150055f, 33.34167f, 34.255516f, 33.33392f, 34.396645f)
    cubicTo(33.364418f, 34.35167f, 33.402676f, 34.31729f, 33.448685f, 34.29351f)
    cubicTo(33.494694f, 34.269215f, 33.545357f, 34.257065f, 33.600674f, 34.257065f)
    cubicTo(33.71699f, 34.257065f, 33.80875f, 34.292477f, 33.875957f, 34.3633f)
    cubicTo(33.94368f, 34.43361f, 33.977543f, 34.529507f, 33.977543f, 34.650993f)
    cubicTo(33.977543f, 34.769897f, 33.942387f, 34.865276f, 33.87208f, 34.937134f)
    cubicTo(33.801773f, 35.00899f, 33.708202f, 35.04492f, 33.59137f, 35.04492f)
    cubicTo(33.457474f, 35.04492f, 33.355114f, 34.993744f, 33.28429f, 34.891384f)
    cubicTo(33.213467f, 34.788506f, 33.178055f, 34.63962f, 33.178055f, 34.444725f)
    cubicTo(33.178055f, 34.26172f, 33.221478f, 34.115932f, 33.308327f, 34.00737f)
    cubicTo(33.39518f, 33.898293f, 33.511753f, 33.843754f, 33.658054f, 33.84375f)
    cubicTo(33.697346f, 33.843754f, 33.736893f, 33.84763f, 33.7767f, 33.85538f)
    cubicTo(33.817024f, 33.863136f, 33.8589f, 33.87477f, 33.902325f, 33.890278f)
    moveTo(34.249725f, 33.86469f)
    lineTo(34.86466f, 33.86469f)
    lineTo(34.86466f, 33.996513f)
    lineTo(34.393185f, 33.996513f)
    lineTo(34.393185f, 34.28033f)
    cubicTo(34.41593f, 34.272575f, 34.43868f, 34.266888f, 34.461426f, 34.263268f)
    cubicTo(34.48417f, 34.259132f, 34.506916f, 34.257065f, 34.529663f, 34.257065f)
    cubicTo(34.658905f, 34.257065f, 34.761265f, 34.292477f, 34.836742f, 34.3633f)
    cubicTo(34.912216f, 34.434128f, 34.94996f, 34.530025f, 34.94996f, 34.650993f)
    cubicTo(34.94996f, 34.77558f, 34.911182f, 34.872513f, 34.83364f, 34.941788f)
    cubicTo(34.756096f, 35.010544f, 34.646755f, 35.04492f, 34.505627f, 35.04492f)
    cubicTo(34.45703f, 35.04492f, 34.407402f, 35.040787f, 34.35674f, 35.032516f)
    cubicTo(34.30659f, 35.024242f, 34.25464f, 35.011837f, 34.200874f, 34.995293f)
    lineTo(34.200874f, 34.837875f)
    cubicTo(34.2474f, 34.86321f, 34.29548f, 34.882076f, 34.345108f, 34.894485f)
    cubicTo(34.394733f, 34.90689f, 34.44721f, 34.913097f, 34.50252f, 34.913097f)
    cubicTo(34.591957f, 34.913097f, 34.66278f, 34.889572f, 34.714996f, 34.84253f)
    cubicTo(34.76721f, 34.795486f, 34.793316f, 34.73164f, 34.793316f, 34.650993f)
    cubicTo(34.793316f, 34.570347f, 34.76721f, 34.5065f, 34.714996f, 34.459457f)
    cubicTo(34.66278f, 34.412415f, 34.591957f, 34.388893f, 34.50252f, 34.388893f)
    cubicTo(34.460648f, 34.388893f, 34.418774f, 34.393543f, 34.3769f, 34.40285f)
    cubicTo(34.335545f, 34.412155f, 34.29315f, 34.426632f, 34.249725f, 34.446274f)
    lineTo(34.249725f, 33.86469f)
    moveTo(35.725407f, 33.86469f)
    lineTo(36.469837f, 33.86469f)
    lineTo(36.469837f, 33.931377f)
    lineTo(36.049545f, 35.022434f)
    lineTo(35.885925f, 35.022434f)
    lineTo(36.281403f, 33.996513f)
    lineTo(35.725407f, 33.996513f)
    lineTo(35.725407f, 33.86469f)
    moveTo(37.130524f, 34.381138f)
    cubicTo(37.060215f, 34.381138f, 37.00438f, 34.405178f, 36.963024f, 34.453255f)
    cubicTo(36.922184f, 34.50133f, 36.901764f, 34.567245f, 36.901764f, 34.650993f)
    cubicTo(36.901764f, 34.734226f, 36.922184f, 34.80014f, 36.963024f, 34.848732f)
    cubicTo(37.00438f, 34.896812f, 37.060215f, 34.92085f, 37.130524f, 34.92085f)
    cubicTo(37.20083f, 34.92085f, 37.2564f, 34.896812f, 37.29724f, 34.848732f)
    cubicTo(37.3386f, 34.80014f, 37.35928f, 34.734226f, 37.35928f, 34.650993f)
    cubicTo(37.35928f, 34.567245f, 37.3386f, 34.50133f, 37.29724f, 34.453255f)
    cubicTo(37.2564f, 34.405178f, 37.20083f, 34.381138f, 37.130524f, 34.381138f)
    moveTo(37.44148f, 33.890278f)
    lineTo(37.44148f, 34.03296f)
    cubicTo(37.402187f, 34.01435f, 37.36238f, 34.000134f, 37.32206f, 33.99031f)
    cubicTo(37.28225f, 33.980488f, 37.242702f, 33.97558f, 37.203415f, 33.97558f)
    cubicTo(37.10002f, 33.97558f, 37.020924f, 34.01047f, 36.966125f, 34.08026f)
    cubicTo(36.911842f, 34.150055f, 36.880825f, 34.255516f, 36.873074f, 34.396645f)
    cubicTo(36.903572f, 34.35167f, 36.94183f, 34.31729f, 36.98784f, 34.29351f)
    cubicTo(37.033848f, 34.269215f, 37.08451f, 34.257065f, 37.139828f, 34.257065f)
    cubicTo(37.256145f, 34.257065f, 37.347904f, 34.292477f, 37.41511f, 34.3633f)
    cubicTo(37.482834f, 34.43361f, 37.516693f, 34.529507f, 37.516697f, 34.650993f)
    cubicTo(37.516693f, 34.769897f, 37.48154f, 34.865276f, 37.411236f, 34.937134f)
    cubicTo(37.340927f, 35.00899f, 37.247356f, 35.04492f, 37.130524f, 35.04492f)
    cubicTo(36.996628f, 35.04492f, 36.894268f, 34.993744f, 36.823444f, 34.891384f)
    cubicTo(36.75262f, 34.788506f, 36.71721f, 34.63962f, 36.71721f, 34.444725f)
    cubicTo(36.71721f, 34.26172f, 36.76063f, 34.115932f, 36.84748f, 34.00737f)
    cubicTo(36.934334f, 33.898293f, 37.050907f, 33.843754f, 37.19721f, 33.84375f)
    cubicTo(37.2365f, 33.843754f, 37.276047f, 33.84763f, 37.315853f, 33.85538f)
    cubicTo(37.35618f, 33.863136f, 37.398052f, 33.87477f, 37.44148f, 33.890278f)
    moveTo(37.74778f, 33.86469f)
    lineTo(38.49221f, 33.86469f)
    lineTo(38.49221f, 33.931377f)
    lineTo(38.07192f, 35.022434f)
    lineTo(37.9083f, 35.022434f)
    lineTo(38.303776f, 33.996513f)
    lineTo(37.74778f, 33.996513f)
    lineTo(37.74778f, 33.86469f)
    moveTo(38.803165f, 34.998394f)
    lineTo(38.803165f, 34.855713f)
    cubicTo(38.842457f, 34.874325f, 38.882263f, 34.88854f, 38.922585f, 34.89836f)
    cubicTo(38.96291f, 34.908184f, 39.002457f, 34.913097f, 39.04123f, 34.913097f)
    cubicTo(39.144623f, 34.913097f, 39.22346f, 34.87846f, 39.27774f, 34.809185f)
    cubicTo(39.33254f, 34.739395f, 39.363815f, 34.633675f, 39.37157f, 34.492027f)
    cubicTo(39.341587f, 34.536488f, 39.30359f, 34.570606f, 39.25758f, 34.594387f)
    cubicTo(39.21157f, 34.618168f, 39.16065f, 34.63006f, 39.104816f, 34.63006f)
    cubicTo(38.989017f, 34.63006f, 38.897255f, 34.59516f, 38.829533f, 34.52537f)
    cubicTo(38.762325f, 34.455063f, 38.728725f, 34.359165f, 38.728725f, 34.23768f)
    cubicTo(38.728725f, 34.11878f, 38.763878f, 34.023396f, 38.834187f, 33.951538f)
    cubicTo(38.90449f, 33.87968f, 38.998062f, 33.843754f, 39.1149f, 33.84375f)
    cubicTo(39.24879f, 33.843754f, 39.35089f, 33.89519f, 39.4212f, 33.998066f)
    cubicTo(39.492023f, 34.100426f, 39.527435f, 34.249313f, 39.527435f, 34.444725f)
    cubicTo(39.527435f, 34.627213f, 39.484013f, 34.773f, 39.397163f, 34.882076f)
    cubicTo(39.310825f, 34.990643f, 39.19451f, 35.04492f, 39.04821f, 35.04492f)
    cubicTo(39.00892f, 35.04492f, 38.969112f, 35.041046f, 38.92879f, 35.03329f)
    cubicTo(38.888466f, 35.025536f, 38.846592f, 35.013905f, 38.803165f, 34.998394f)
    moveTo(39.1149f, 34.507538f)
    cubicTo(39.185204f, 34.507538f, 39.24078f, 34.483498f, 39.28162f, 34.435417f)
    cubicTo(39.322975f, 34.38734f, 39.343655f, 34.321426f, 39.343655f, 34.23768f)
    cubicTo(39.343655f, 34.15445f, 39.322975f, 34.088795f, 39.28162f, 34.040714f)
    cubicTo(39.24078f, 33.992123f, 39.185204f, 33.967823f, 39.1149f, 33.967823f)
    cubicTo(39.04459f, 33.967823f, 38.988758f, 33.992123f, 38.9474f, 34.040714f)
    cubicTo(38.90656f, 34.088795f, 38.88614f, 34.15445f, 38.88614f, 34.23768f)
    cubicTo(38.88614f, 34.321426f, 38.90656f, 34.38734f, 38.9474f, 34.435417f)
    cubicTo(38.988758f, 34.483498f, 39.04459f, 34.507538f, 39.1149f, 34.507538f)
    moveTo(39.814354f, 34.998394f)
    lineTo(39.814354f, 34.855713f)
    cubicTo(39.85364f, 34.874325f, 39.893448f, 34.88854f, 39.933773f, 34.89836f)
    cubicTo(39.974094f, 34.908184f, 40.013645f, 34.913097f, 40.052418f, 34.913097f)
    cubicTo(40.155807f, 34.913097f, 40.234646f, 34.87846f, 40.28893f, 34.809185f)
    cubicTo(40.343727f, 34.739395f, 40.375004f, 34.633675f, 40.38276f, 34.492027f)
    cubicTo(40.352776f, 34.536488f, 40.314777f, 34.570606f, 40.26877f, 34.594387f)
    cubicTo(40.222755f, 34.618168f, 40.171837f, 34.63006f, 40.116005f, 34.63006f)
    cubicTo(40.000202f, 34.63006f, 39.90844f, 34.59516f, 39.840717f, 34.52537f)
    cubicTo(39.773514f, 34.455063f, 39.73991f, 34.359165f, 39.73991f, 34.23768f)
    cubicTo(39.73991f, 34.11878f, 39.775063f, 34.023396f, 39.84537f, 33.951538f)
    cubicTo(39.91568f, 33.87968f, 40.00925f, 33.843754f, 40.126083f, 33.84375f)
    cubicTo(40.25998f, 33.843754f, 40.36208f, 33.89519f, 40.43239f, 33.998066f)
    cubicTo(40.503212f, 34.100426f, 40.538624f, 34.249313f, 40.538624f, 34.444725f)
    cubicTo(40.538624f, 34.627213f, 40.495197f, 34.773f, 40.408348f, 34.882076f)
    cubicTo(40.322014f, 34.990643f, 40.205696f, 35.04492f, 40.059395f, 35.04492f)
    cubicTo(40.020107f, 35.04492f, 39.9803f, 35.041046f, 39.939976f, 35.03329f)
    cubicTo(39.899654f, 35.025536f, 39.85778f, 35.013905f, 39.814354f, 34.998394f)
    moveTo(40.126083f, 34.507538f)
    cubicTo(40.196392f, 34.507538f, 40.251965f, 34.483498f, 40.292805f, 34.435417f)
    cubicTo(40.334164f, 34.38734f, 40.35484f, 34.321426f, 40.354843f, 34.23768f)
    cubicTo(40.35484f, 34.15445f, 40.334164f, 34.088795f, 40.292805f, 34.040714f)
    cubicTo(40.251965f, 33.992123f, 40.196392f, 33.967823f, 40.126083f, 33.967823f)
    cubicTo(40.05578f, 33.967823f, 39.999943f, 33.992123f, 39.958588f, 34.040714f)
    cubicTo(39.917747f, 34.088795f, 39.897327f, 34.15445f, 39.897327f, 34.23768f)
    cubicTo(39.897327f, 34.321426f, 39.917747f, 34.38734f, 39.958588f, 34.435417f)
    cubicTo(39.999943f, 34.483498f, 40.05578f, 34.507538f, 40.126083f, 34.507538f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6111270189285278f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6111270189285278f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
5.632443904876709f, -67.63530731201172f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(69.375f, 125.0f)
    cubicTo(69.39803f, 130.151f, 66.66322f, 134.92062f, 62.206123f, 137.50279f)
    cubicTo(57.74902f, 140.08498f, 52.25098f, 140.08498f, 47.793877f, 137.50279f)
    cubicTo(43.336773f, 134.92062f, 40.601963f, 130.151f, 40.625f, 125.0f)
    cubicTo(40.601963f, 119.84899f, 43.336773f, 115.07938f, 47.793877f, 112.4972f)
    cubicTo(52.25098f, 109.91502f, 57.74902f, 109.91502f, 62.206123f, 112.4972f)
    cubicTo(66.66322f, 115.07938f, 69.39803f, 119.84899f, 69.375f, 125.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 245, 32, 227), 1.0f to Color(255, 243, 0, 0), center = Offset(55.0f, 125.0f), radius = 14.375f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 2.0534627437591553
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
            return 45.946537017822266
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 41.351158142089844
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

