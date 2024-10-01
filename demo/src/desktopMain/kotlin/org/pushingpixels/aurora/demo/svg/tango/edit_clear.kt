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
class edit_clear : Painter() {
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
alpha *= 0.47368422f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.4357800483703613f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8257039785385132f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-5.050459861755371f, 13.431819915771484f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.142857f, 30.857143f)
    cubicTo(38.16781f, 34.491642f, 35.205383f, 37.857037f, 30.377314f, 39.678993f)
    cubicTo(25.549248f, 41.500954f, 19.593609f, 41.500954f, 14.765542f, 39.678993f)
    cubicTo(9.937474f, 37.857037f, 6.9750466f, 34.491642f, 7.0f, 30.857143f)
    cubicTo(6.9750466f, 27.222643f, 9.937474f, 23.85725f, 14.765542f, 22.035294f)
    cubicTo(19.593609f, 20.213335f, 25.549248f, 20.213335f, 30.377314f, 22.035294f)
    cubicTo(35.205383f, 23.85725f, 38.16781f, 27.222643f, 38.142857f, 30.857143f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(22.571428f, 30.857143f), radius = 15.571428f, tileMode = TileMode.Clamp)
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
    moveTo(6.9163117f, 1.7780186f)
    cubicTo(8.646088f, 0.8999591f, 11.042237f, 1.7815151f, 11.928102f, 3.3353386f)
    lineTo(16.098341f, 12.820053f)
    cubicTo(16.984205f, 14.373873f, 16.731756f, 16.189358f, 15.532312f, 16.890638f)
    cubicTo(14.332867f, 17.591923f, 12.654079f, 16.90558f, 11.768213f, 15.351758f)
    lineTo(5.84975f, 6.996031f)
    cubicTo(4.9638853f, 5.4422064f, 5.2207537f, 2.6387086f, 6.9163117f, 1.7780186f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(193, 125, 16, 255), 1.0f to Color(155, 101, 12, 255), start = Offset(11.573825f, 9.360087f), end = Offset(14.824485f, 15.2196865f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(143, 89, 2, 255))
stroke = Stroke(width=1.0063211f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.9163117f, 1.7780186f)
    cubicTo(8.646088f, 0.8999591f, 11.042237f, 1.7815151f, 11.928102f, 3.3353386f)
    lineTo(16.098341f, 12.820053f)
    cubicTo(16.984205f, 14.373873f, 16.731756f, 16.189358f, 15.532312f, 16.890638f)
    cubicTo(14.332867f, 17.591923f, 12.654079f, 16.90558f, 11.768213f, 15.351758f)
    lineTo(5.84975f, 6.996031f)
    cubicTo(4.9638853f, 5.4422064f, 5.2207537f, 2.6387086f, 6.9163117f, 1.7780186f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4230769f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(9.562158f, 6.952559f), end = Offset(14.766725f, 14.200403f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0056905f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.44627f, 2.7359104f)
    cubicTo(8.868223f, 1.9500906f, 10.266975f, 2.5431693f, 11.141568f, 4.0752983f)
    lineTo(15.589706f, 14.162023f)
    lineTo(13.158185f, 15.581885f)
    lineTo(6.861574f, 6.761549f)
    cubicTo(5.986981f, 5.2294207f, 5.9853272f, 3.5432765f, 7.44627f, 2.7359104f)
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
    moveTo(14.780848f, 22.793568f)
    cubicTo(13.985353f, 27.080402f, 15.414814f, 36.746067f, 21.040081f, 42.487564f)
    cubicTo(25.386835f, 42.628857f, 35.03017f, 39.06988f, 41.571175f, 32.290527f)
    cubicTo(32.335f, 27.773815f, 26.034647f, 16.567549f, 21.135868f, 19.007853f)
    lineTo(14.780848f, 22.793568f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(254, 240, 136, 255), 0.5f to Color(253, 230, 58, 255), 1.0f to Color(218, 194, 3, 255), center = Offset(18.92845f, 20.94527f), radius = 7.382419f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(196, 160, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.780848f, 22.793568f)
    cubicTo(13.985353f, 27.080402f, 15.414814f, 36.746067f, 21.040081f, 42.487564f)
    cubicTo(25.386835f, 42.628857f, 35.03017f, 39.06988f, 41.571175f, 32.290527f)
    cubicTo(32.335f, 27.773815f, 26.034647f, 16.567549f, 21.135868f, 19.007853f)
    lineTo(14.780848f, 22.793568f)
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
// _0_0_4
brush = Brush.linearGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), start = Offset(23.151512f, 39.85529f), end = Offset(17.12548f, 27.334774f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999999f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.33836f, 24.367273f)
    cubicTo(18.687374f, 30.637545f, 18.827803f, 37.155575f, 23.522142f, 42.28716f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.46153846f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.91878f, 23.414124f)
    cubicTo(15.006687f, 23.922228f, 15.758401f, 35.934338f, 21.503035f, 41.41449f)
    cubicTo(28.88902f, 41.227592f, 37.02675f, 35.018303f, 39.895985f, 32.576645f)
    cubicTo(30.39657f, 26.592524f, 24.971184f, 17.814877f, 21.415195f, 20.003061f)
    lineTo(15.91878f, 23.414124f)
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
brush = Brush.linearGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), start = Offset(32.581184f, 35.16632f), end = Offset(24.146275f, 22.817135f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.848486f, 22.273333f)
    cubicTo(24.714846f, 21.524332f, 29.598505f, 28.360998f, 38.846985f, 34.655132f)
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
brush = Brush.linearGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), start = Offset(24.515398f, 35.325256f), end = Offset(16.88407f, 24.210546f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.012894f, 22.86392f)
    cubicTo(21.725203f, 28.211416f, 20.975883f, 35.288177f, 26.853708f, 40.94503f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
brush = Brush.linearGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), start = Offset(29.455156f, 33.9843f), end = Offset(22.583254f, 22.081823f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.983898f, 22.775822f)
    cubicTo(22.983898f, 22.775822f, 31.160627f, 35.84745f, 34.165833f, 37.880383f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
brush = Brush.linearGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), start = Offset(26.080812f, 28.373215f), end = Offset(19.26732f, 22.338493f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.300308f, 22.200695f)
    cubicTo(22.747454f, 25.11751f, 28.002865f, 36.363884f, 30.610323f, 39.678444f)
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
    moveTo(8.508945f, 18.061867f)
    lineTo(19.970615f, 11.50296f)
    cubicTo(20.569265f, 13.885531f, 19.628777f, 14.743545f, 21.766565f, 18.650673f)
    lineTo(13.856274f, 23.177313f)
    cubicTo(12.402846f, 19.699894f, 10.291388f, 19.767015f, 8.508945f, 18.061867f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(218, 194, 3, 255), 0.5f to Color(253, 236, 105, 255), 1.0f to Color(253, 239, 126, 255), start = Offset(11.843589f, 19.567532f), end = Offset(17.707006f, 16.190516f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(196, 160, 0, 255))
stroke = Stroke(width=1.0103954f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.508945f, 18.061867f)
    lineTo(19.970615f, 11.50296f)
    cubicTo(20.569265f, 13.885531f, 19.628777f, 14.743545f, 21.766565f, 18.650673f)
    lineTo(13.856274f, 23.177313f)
    cubicTo(12.402846f, 19.699894f, 10.291388f, 19.767015f, 8.508945f, 18.061867f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.24725272f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.043449f, 21.24569f)
    cubicTo(13.229488f, 19.824472f, 11.481136f, 19.009348f, 10.36413f, 18.11846f)
    lineTo(19.121552f, 13.184722f)
    cubicTo(19.10196f, 14.66569f, 19.688457f, 16.272928f, 19.997164f, 17.703388f)
    lineTo(14.043449f, 21.24569f)
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
0.8660249710083008f, -0.5000010132789612f, 0.0f, 0.0f,
0.5f, 0.866025984287262f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
shape = Outline.Rounded(roundRect = RoundRect(left = -1.4127867221832275f, top = 24.17477798461914f, right = 11.60223650932312f, bottom = 27.634668588638306f,radiusX = 1.9999947547912598f, radiusY = 1.9999960660934448f))
brush = Brush.linearGradient(0.0f to Color(179, 0, 0, 255), 0.5f to Color(255, 92, 92, 255), 1.0f to Color(195, 0, 0, 255), start = Offset(11.671259f, 27.784811f), end = Offset(1.541838f, 26.740229f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(105, 0, 0, 255))
stroke = Stroke(width=0.99999803f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = -1.4127867221832275f, top = 24.17477798461914f, right = 11.60223650932312f, bottom = 27.634668588638306f,radiusX = 1.9999947547912598f, radiusY = 1.9999960660934448f))
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
0.5835570096969604f, 0.15636399388313293f, 0.0f, 0.0f,
-0.15636399388313293f, 0.5835570096969604f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
20.457820892333984f, 5.158782005310059f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-17.172594f, 4.083618f)
    cubicTo(-17.169153f, 4.8528023f, -17.577536f, 5.565034f, -18.243101f, 5.950622f)
    cubicTo(-18.908667f, 6.3362107f, -19.72967f, 6.3362107f, -20.395235f, 5.950622f)
    cubicTo(-21.0608f, 5.565034f, -21.469183f, 4.8528023f, -21.465742f, 4.083618f)
    cubicTo(-21.469183f, 3.3144343f, -21.0608f, 2.6022027f, -20.395235f, 2.2166142f)
    cubicTo(-19.72967f, 1.8310258f, -18.908667f, 1.8310258f, -18.243101f, 2.2166142f)
    cubicTo(-17.577536f, 2.6022027f, -17.169153f, 3.3144343f, -17.172594f, 4.083618f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(143, 89, 2, 255))
stroke = Stroke(width=1.6552416f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-17.172594f, 4.083618f)
    cubicTo(-17.169153f, 4.8528023f, -17.577536f, 5.565034f, -18.243101f, 5.950622f)
    cubicTo(-18.908667f, 6.3362107f, -19.72967f, 6.3362107f, -20.395235f, 5.950622f)
    cubicTo(-21.0608f, 5.565034f, -21.469183f, 4.8528023f, -21.465742f, 4.083618f)
    cubicTo(-21.469183f, 3.3144343f, -21.0608f, 2.6022027f, -20.395235f, 2.2166142f)
    cubicTo(-19.72967f, 1.8310258f, -18.908667f, 1.8310258f, -18.243101f, 2.2166142f)
    cubicTo(-17.577536f, 2.6022027f, -17.169153f, 3.3144343f, -17.172594f, 4.083618f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31730768f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(27.734835f, 40.55524f)
    cubicTo(27.734835f, 40.55524f, 29.241419f, 40.054592f, 29.910933f, 39.613483f)
    cubicTo(28.673494f, 37.66894f, 26.507591f, 33.71782f, 24.507807f, 30.737564f)
    cubicTo(25.70105f, 37.2783f, 27.734835f, 40.55524f, 27.734835f, 40.55524f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31730768f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.141806f, 39.05827f)
    lineTo(33.167805f, 37.9731f)
    cubicTo(31.223259f, 36.116943f, 25.699306f, 27.857203f, 25.699306f, 27.857203f)
    lineTo(31.141806f, 39.05827f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31730768f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.658524f, 36.38781f)
    lineTo(38.094894f, 34.64406f)
    cubicTo(34.91291f, 33.318233f, 26.508352f, 24.763609f, 26.508352f, 24.763609f)
    cubicTo(28.891289f, 27.775503f, 33.275585f, 33.375916f, 35.658524f, 36.38781f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31730768f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.15369f, 33.91721f)
    lineTo(40.846f, 32.5077f)
    cubicTo(37.53144f, 31.005098f, 28.350325f, 22.595682f, 28.350325f, 22.595682f)
    cubicTo(28.350325f, 22.595682f, 34.42491f, 31.795889f, 39.15369f, 33.91721f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31730765f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.376076f, 41.571705f)
    cubicTo(24.376076f, 41.571705f, 25.573301f, 41.292027f, 26.419592f, 41.116085f)
    cubicTo(24.961184f, 39.87865f, 22.618504f, 36.67883f, 21.325825f, 32.50533f)
    cubicTo(21.856155f, 39.399624f, 24.376076f, 41.571705f, 24.376076f, 41.571705f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31730765f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(21.370872f, 41.96945f)
    cubicTo(21.370872f, 41.96945f, 22.618893f, 41.955776f, 22.618893f, 41.955776f)
    cubicTo(20.320795f, 39.436707f, 19.480717f, 36.590443f, 18.188038f, 32.549526f)
    cubicTo(18.055456f, 37.234108f, 21.370872f, 41.96945f, 21.370872f, 41.96945f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
            return 4.850370407104492
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.9262688159942627
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 43.149627685546875
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 46.39695358276367
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

