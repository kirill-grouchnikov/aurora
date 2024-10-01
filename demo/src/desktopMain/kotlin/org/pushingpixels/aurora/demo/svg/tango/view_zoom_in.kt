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
class view_zoom_in : Painter() {
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
alpha *= 0.17112301f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.446431040763855f, 0.0f, 0.0f, 0.0f,
0.0f, 1.5199899673461914f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.974530220031738f, -17.751680374145508f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.65864f, 37.967922f)
    cubicTo(40.685127f, 39.37734f, 37.540596f, 40.6824f, 32.415745f, 41.38893f)
    cubicTo(27.290888f, 42.095467f, 20.969152f, 42.095467f, 15.844297f, 41.38893f)
    cubicTo(10.719443f, 40.6824f, 7.574911f, 39.37734f, 7.6013985f, 37.967922f)
    cubicTo(7.574911f, 36.558506f, 10.719443f, 35.253445f, 15.844297f, 34.546913f)
    cubicTo(20.969152f, 33.840378f, 27.290888f, 33.840378f, 32.415745f, 34.546913f)
    cubicTo(37.540596f, 35.253445f, 40.685127f, 36.558506f, 40.65864f, 37.967922f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.130018f, 37.96793f), radius = 16.52862f, tileMode = TileMode.Clamp)
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
0.497763991355896f, 0.0f, 0.0f, 0.0f,
0.0f, 0.609620988368988f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
8.973526000976562f, 15.619290351867676f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.65864f, 37.967922f)
    cubicTo(40.685127f, 39.37734f, 37.540596f, 40.6824f, 32.415745f, 41.38893f)
    cubicTo(27.290888f, 42.095467f, 20.969152f, 42.095467f, 15.844297f, 41.38893f)
    cubicTo(10.719443f, 40.6824f, 7.574911f, 39.37734f, 7.6013985f, 37.967922f)
    cubicTo(7.574911f, 36.558506f, 10.719443f, 35.253445f, 15.844297f, 34.546913f)
    cubicTo(20.969152f, 33.840378f, 27.290888f, 33.840378f, 32.415745f, 34.546913f)
    cubicTo(37.540596f, 35.253445f, 40.685127f, 36.558506f, 40.65864f, 37.967922f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(24.130018f, 37.96793f), radius = 16.52862f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.12372323125600815f, 0.07535096257925034f, 0.0f, 1.0f)
))}){
// _0_1_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.48844f, 3.1435547f, 3.8827686f, 9.749226f, 3.8827686f, 17.888355f)
    cubicTo(3.8827686f, 26.027485f, 10.48844f, 32.633156f, 18.62757f, 32.633156f)
    cubicTo(22.107124f, 32.633156f, 25.17857f, 31.248762f, 27.701292f, 29.230509f)
    cubicTo(27.495914f, 30.23739f, 27.623262f, 31.265877f, 28.457436f, 31.990433f)
    lineTo(39.42152f, 41.51784f)
    cubicTo(40.654938f, 42.58917f, 42.508984f, 42.448803f, 43.58031f, 41.215385f)
    cubicTo(44.651638f, 39.981968f, 44.51127f, 38.127922f, 43.277855f, 37.056595f)
    lineTo(32.31377f, 27.529188f)
    cubicTo(31.642242f, 26.94591f, 30.820892f, 26.77322f, 30.007532f, 26.886467f)
    cubicTo(31.994232f, 24.374044f, 33.37237f, 21.337664f, 33.37237f, 17.888357f)
    cubicTo(33.37237f, 9.749228f, 26.7667f, 3.1435556f, 18.627571f, 3.1435556f)
    close()
    moveTo(18.551949f, 4.369738f)
    cubicTo(26.191408f, 4.369738f, 31.843723f, 9.158689f, 31.843723f, 17.661512f)
    cubicTo(31.843723f, 26.336624f, 26.027033f, 30.953287f, 18.551949f, 30.953287f)
    cubicTo(11.249f, 30.953287f, 5.2601748f, 25.475195f, 5.2601748f, 17.661512f)
    cubicTo(5.2601748f, 9.677405f, 11.084813f, 4.3697376f, 18.551949f, 4.3697376f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(138, 138, 138, 255), 1.0f to Color(72, 72, 72, 255), start = Offset(27.36634f, 26.580296f), end = Offset(31.335964f, 30.557772f), tileMode = TileMode.Clamp)
stroke = Stroke(width=2.000001f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.48844f, 3.1435547f, 3.8827686f, 9.749226f, 3.8827686f, 17.888355f)
    cubicTo(3.8827686f, 26.027485f, 10.48844f, 32.633156f, 18.62757f, 32.633156f)
    cubicTo(22.107124f, 32.633156f, 25.17857f, 31.248762f, 27.701292f, 29.230509f)
    cubicTo(27.495914f, 30.23739f, 27.623262f, 31.265877f, 28.457436f, 31.990433f)
    lineTo(39.42152f, 41.51784f)
    cubicTo(40.654938f, 42.58917f, 42.508984f, 42.448803f, 43.58031f, 41.215385f)
    cubicTo(44.651638f, 39.981968f, 44.51127f, 38.127922f, 43.277855f, 37.056595f)
    lineTo(32.31377f, 27.529188f)
    cubicTo(31.642242f, 26.94591f, 30.820892f, 26.77322f, 30.007532f, 26.886467f)
    cubicTo(31.994232f, 24.374044f, 33.37237f, 21.337664f, 33.37237f, 17.888357f)
    cubicTo(33.37237f, 9.749228f, 26.7667f, 3.1435556f, 18.627571f, 3.1435556f)
    close()
    moveTo(18.551949f, 4.369738f)
    cubicTo(26.191408f, 4.369738f, 31.843723f, 9.158689f, 31.843723f, 17.661512f)
    cubicTo(31.843723f, 26.336624f, 26.027033f, 30.953287f, 18.551949f, 30.953287f)
    cubicTo(11.249f, 30.953287f, 5.2601748f, 25.475195f, 5.2601748f, 17.661512f)
    cubicTo(5.2601748f, 9.677405f, 11.084813f, 4.3697376f, 18.551949f, 4.3697376f)
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
// _0_1_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.602905f, 3.0803552f)
    cubicTo(10.437466f, 3.0803552f, 3.810441f, 9.707379f, 3.810441f, 17.87282f)
    cubicTo(3.810441f, 26.03826f, 10.437466f, 32.665283f, 18.602905f, 32.665283f)
    cubicTo(22.093708f, 32.665283f, 25.175083f, 31.276417f, 27.70596f, 29.251638f)
    cubicTo(27.499918f, 30.261774f, 27.627668f, 31.293585f, 28.464546f, 32.020485f)
    lineTo(39.464073f, 41.57869f)
    cubicTo(40.701477f, 42.65348f, 42.561516f, 42.51266f, 43.636307f, 41.275253f)
    cubicTo(44.711098f, 40.03785f, 44.570274f, 38.17781f, 43.33287f, 37.10302f)
    lineTo(32.333347f, 27.544815f)
    cubicTo(31.659649f, 26.959652f, 30.835644f, 26.786402f, 30.019653f, 26.900017f)
    cubicTo(32.012775f, 24.379473f, 33.39537f, 21.333277f, 33.39537f, 17.87282f)
    cubicTo(33.39537f, 9.70738f, 26.768347f, 3.0803556f, 18.602905f, 3.0803556f)
    close()
    moveTo(18.527046f, 6.266424f)
    cubicTo(24.808155f, 6.266424f, 29.905865f, 11.364135f, 29.905865f, 17.645243f)
    cubicTo(29.905865f, 23.92635f, 24.808155f, 29.02406f, 18.527046f, 29.02406f)
    cubicTo(12.245938f, 29.02406f, 7.1482277f, 23.92635f, 7.1482277f, 17.64524f)
    cubicTo(7.1482277f, 11.364133f, 12.245938f, 6.2664223f, 18.527046f, 6.2664223f)
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
// _0_1_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.507004f, 41.57769f)
    cubicTo(39.02833f, 39.304504f, 40.904335f, 36.76627f, 43.091057f, 36.789314f)
    cubicTo(43.091057f, 36.789314f, 32.33069f, 27.531204f, 32.33069f, 27.531204f)
    cubicTo(29.385897f, 27.474495f, 28.061186f, 29.80382f, 28.553875f, 32.131126f)
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
// _0_1_0_3
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 255, 255, 56), 1.0f to Color(255, 255, 255, 255), start = Offset(18.292673f, 13.602121f), end = Offset(17.500893f, 25.74347f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.8027336f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
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
alpha *= 0.43315506f
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
// _0_1_0_4
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000311f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 40.37333679199219f, top = 0.14086054265499115f, right = 59.421775817871094f, bottom = 4.581338867545128f,radiusX = 4.273321628570557f, radiusY = 3.7758729457855225f))
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
// _0_1_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424264f, 25.704697f)
    cubicTo(10.848134f, 24.212242f, 9.267461f, 21.455486f, 9.280775f, 18.478292f)
    cubicTo(9.267461f, 15.501098f, 10.848134f, 12.744342f, 13.424264f, 11.251888f)
    cubicTo(16.000395f, 9.759434f, 19.178165f, 9.759434f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744342f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(114, 159, 207, 53), 1.0f to Color(114, 159, 207, 172), center = Offset(18.240929f, 21.817987f), radius = 8.308506f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(48, 99, 163, 255))
stroke = Stroke(width=0.71499395f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424264f, 25.704697f)
    cubicTo(10.848134f, 24.212242f, 9.267461f, 21.455486f, 9.280775f, 18.478292f)
    cubicTo(9.267461f, 15.501098f, 10.848134f, 12.744342f, 13.424264f, 11.251888f)
    cubicTo(16.000395f, 9.759434f, 19.178165f, 9.759434f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744342f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
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
// _0_1_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.156916f, 7.3966937f)
    cubicTo(12.949326f, 7.3966937f, 8.732368f, 11.613651f, 8.732368f, 16.821241f)
    cubicTo(8.732368f, 18.325216f, 9.152676f, 19.709015f, 9.77954f, 20.971144f)
    cubicTo(11.03192f, 21.432756f, 12.362297f, 21.746826f, 13.774307f, 21.746826f)
    cubicTo(19.945263f, 21.746826f, 24.873589f, 16.88519f, 25.254414f, 10.809697f)
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
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5028156042098999f, 0.0f, 0.0f, 0.0f,
0.0f, 0.5028156042098999f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
6.317196846008301f, 5.165855884552002f, 0.0f, 1.0f)
))}){
// _0_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.307563f, 20.552553f)
    lineTo(10.307563f, 28.507755f)
    lineTo(20.251568f, 28.507755f)
    lineTo(20.251568f, 38.45176f)
    lineTo(28.206772f, 38.45176f)
    lineTo(28.206772f, 28.507755f)
    lineTo(38.150776f, 28.507755f)
    lineTo(38.150776f, 20.552551f)
    lineTo(28.206772f, 20.552551f)
    lineTo(28.206772f, 10.608547f)
    lineTo(20.251568f, 10.608547f)
    lineTo(20.251568f, 20.552551f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(81, 138, 196, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.9888006f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.307563f, 20.552553f)
    lineTo(10.307563f, 28.507755f)
    lineTo(20.251568f, 28.507755f)
    lineTo(20.251568f, 38.45176f)
    lineTo(28.206772f, 38.45176f)
    lineTo(28.206772f, 28.507755f)
    lineTo(38.150776f, 28.507755f)
    lineTo(38.150776f, 20.552551f)
    lineTo(28.206772f, 20.552551f)
    lineTo(28.206772f, 10.608547f)
    lineTo(20.251568f, 10.608547f)
    lineTo(20.251568f, 20.552551f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.41f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_1
brush = SolidColor(Color(238, 238, 236, 255))
stroke = Stroke(width=1.9888006f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.296364f, 22.541353f)
    lineTo(12.296364f, 26.518955f)
    lineTo(22.240368f, 26.518955f)
    lineTo(22.240368f, 36.46296f)
    lineTo(26.217968f, 36.46296f)
    lineTo(26.217968f, 26.518955f)
    lineTo(36.161972f, 26.518955f)
    cubicTo(36.161972f, 26.518955f, 36.161972f, 22.541355f, 36.161972f, 22.541355f)
    lineTo(26.217968f, 22.541355f)
    lineTo(26.217968f, 12.597351f)
    lineTo(22.240368f, 12.597351f)
    lineTo(22.240368f, 22.541355f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.9888006448745728f, 0.0f, 0.0f, 0.0f,
0.0f, 1.9888006448745728f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-12.563645362854004f, -10.273857116699219f, 0.0f, 1.0f)
))}){
// _0_2_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.0f, 11.0f)
    lineTo(17.0f, 15.5f)
    lineTo(17.0f, 16.0f)
    lineTo(16.5f, 16.0f)
    lineTo(12.0f, 16.0f)
    lineTo(12.0f, 18.1875f)
    cubicTo(13.648689f, 17.33277f, 16.029589f, 16.807531f, 18.461166f, 17.484835f)
    cubicTo(20.155914f, 17.956898f, 23.079975f, 17.91651f, 25.0f, 17.625f)
    lineTo(25.0f, 16.0f)
    lineTo(20.5f, 16.0f)
    lineTo(20.0f, 16.0f)
    lineTo(20.0f, 15.5f)
    lineTo(20.0f, 11.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(238, 238, 236, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 0.020128337666392326
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 2.218905210494995
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 47.81550216674805
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 43.7456169128418
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

