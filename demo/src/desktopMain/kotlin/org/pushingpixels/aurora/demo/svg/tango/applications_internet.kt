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
class applications_internet : Painter() {
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
0.0f, 1.2431999444961548f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, -10.272000312805176f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.053f, 38.909f)
    cubicTo(45.085377f, 41.58756f, 41.241802f, 44.06779f, 34.977673f, 45.41054f)
    cubicTo(28.713545f, 46.753292f, 20.98646f, 46.753292f, 14.722331f, 45.41054f)
    cubicTo(8.458201f, 44.06779f, 4.614628f, 41.58756f, 4.647003f, 38.909f)
    cubicTo(4.614628f, 36.230442f, 8.458201f, 33.75021f, 14.722331f, 32.40746f)
    cubicTo(20.98646f, 31.064709f, 28.713545f, 31.064709f, 34.977673f, 32.40746f)
    cubicTo(41.241802f, 33.75021f, 45.085377f, 36.230442f, 45.053f, 38.909f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.850704f, 38.90863f), radius = 20.203114f, tileMode = TileMode.Clamp)
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
    moveTo(43.96f, 23.485f)
    cubicTo(43.96f, 34.195f, 35.278f, 42.877f, 24.57f, 42.877f)
    cubicTo(13.86f, 42.877f, 5.178999f, 34.195f, 5.178999f, 23.484999f)
    cubicTo(5.178699f, 12.775999f, 13.859999f, 4.0949993f, 24.57f, 4.0949993f)
    cubicTo(35.278f, 4.095199f, 43.96f, 12.775999f, 43.96f, 23.484999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(211, 233, 255, 255), 0.15517f to Color(211, 233, 255, 255), 0.75f to Color(64, 116, 174, 255), 1.0f to Color(54, 72, 108, 255), center = Offset(18.247816f, 15.716f), radius = 29.992697f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(57, 57, 108, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.96f, 23.485f)
    cubicTo(43.96f, 34.195f, 35.278f, 42.877f, 24.57f, 42.877f)
    cubicTo(13.86f, 42.877f, 5.178999f, 34.195f, 5.178999f, 23.484999f)
    cubicTo(5.178699f, 12.775999f, 13.859999f, 4.0949993f, 24.57f, 4.0949993f)
    cubicTo(35.278f, 4.095199f, 43.96f, 12.775999f, 43.96f, 23.484999f)
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
0.9823700189590454f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9823700189590454f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.12108000367879868f, 0.23291000723838806f, 0.0f, 1.0f)
))}){
// _0_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.071f, 20.714f)
    lineTo(44.071f, 20.714f)
    lineTo(43.526f, 21.332f)
    cubicTo(43.192f, 20.938002f, 42.817f, 20.607f, 42.437f, 20.261002f)
    lineTo(41.601f, 20.384f)
    lineTo(40.837f, 19.521f)
    lineTo(40.837f, 20.589f)
    lineTo(41.491f, 21.084002f)
    lineTo(41.927002f, 21.578001f)
    lineTo(42.509003f, 20.920002f)
    cubicTo(42.655003f, 21.194002f, 42.800003f, 21.468002f, 42.945004f, 21.743002f)
    lineTo(42.945004f, 22.565002f)
    lineTo(42.290005f, 23.305002f)
    lineTo(41.091003f, 24.128002f)
    lineTo(40.183002f, 25.035002f)
    lineTo(39.601f, 24.374002f)
    lineTo(39.892002f, 23.634003f)
    lineTo(39.31f, 22.976002f)
    lineTo(38.329002f, 20.878002f)
    lineTo(37.493004f, 19.933002f)
    lineTo(37.274002f, 20.179003f)
    lineTo(37.602f, 21.373003f)
    lineTo(38.22f, 22.072002f)
    cubicTo(38.572002f, 23.089003f, 38.921f, 24.062002f, 39.384003f, 25.035002f)
    cubicTo(40.102f, 25.035002f, 40.778004f, 24.958002f, 41.491f, 24.869001f)
    lineTo(41.491f, 25.445002f)
    lineTo(40.619f, 27.584002f)
    lineTo(39.819f, 28.488f)
    lineTo(39.165f, 29.889f)
    lineTo(39.165f, 32.192f)
    lineTo(39.384003f, 33.098f)
    lineTo(39.020004f, 33.508f)
    lineTo(38.220005f, 34.002f)
    lineTo(37.384007f, 34.701f)
    lineTo(38.075005f, 35.483f)
    lineTo(37.130005f, 36.307003f)
    lineTo(37.312004f, 36.840004f)
    lineTo(35.894005f, 38.446003f)
    lineTo(34.949005f, 38.446003f)
    lineTo(34.149006f, 38.940002f)
    lineTo(33.640007f, 38.940002f)
    lineTo(33.640007f, 38.281002f)
    lineTo(33.423008f, 36.963f)
    cubicTo(33.14201f, 36.137f, 32.849007f, 35.316f, 32.551006f, 34.496002f)
    cubicTo(32.551006f, 33.891003f, 32.587006f, 33.291f, 32.623005f, 32.686f)
    lineTo(32.987003f, 31.863f)
    lineTo(32.478004f, 30.875f)
    lineTo(32.515003f, 29.518f)
    lineTo(31.823004f, 28.736f)
    lineTo(32.169003f, 27.606f)
    lineTo(31.606003f, 26.967001f)
    lineTo(30.624002f, 26.967001f)
    lineTo(30.297003f, 26.597f)
    lineTo(29.316002f, 27.215f)
    lineTo(28.916002f, 26.761f)
    lineTo(28.007002f, 27.543f)
    cubicTo(27.390001f, 26.842999f, 26.772001f, 26.144f, 26.153002f, 25.445f)
    lineTo(25.427002f, 23.716f)
    lineTo(26.081001f, 22.73f)
    lineTo(25.718f, 22.319f)
    lineTo(26.517f, 20.425f)
    cubicTo(27.173f, 19.609f, 27.858f, 18.826f, 28.552f, 18.039999f)
    lineTo(29.788f, 17.710999f)
    lineTo(31.169f, 17.545998f)
    lineTo(32.114002f, 17.793997f)
    lineTo(33.459003f, 19.149998f)
    lineTo(33.932003f, 18.615997f)
    lineTo(34.585003f, 18.533997f)
    lineTo(35.821003f, 18.944996f)
    lineTo(36.767002f, 18.944996f)
    lineTo(37.421f, 18.368996f)
    lineTo(37.712f, 17.957996f)
    lineTo(37.057003f, 17.545996f)
    lineTo(35.966003f, 17.463995f)
    cubicTo(35.663002f, 17.044994f, 35.382004f, 16.602995f, 35.022003f, 16.229996f)
    lineTo(34.658005f, 16.393995f)
    lineTo(34.513004f, 17.463995f)
    lineTo(33.858006f, 16.723995f)
    lineTo(33.714005f, 15.899995f)
    lineTo(32.987003f, 15.3259945f)
    lineTo(32.695004f, 15.3259945f)
    lineTo(33.423004f, 16.147995f)
    lineTo(33.132004f, 16.887995f)
    lineTo(32.551003f, 17.051994f)
    lineTo(32.914f, 16.311995f)
    lineTo(32.259003f, 15.9839945f)
    lineTo(31.678003f, 15.3259945f)
    lineTo(30.587004f, 15.571995f)
    lineTo(30.442003f, 15.899995f)
    lineTo(29.788004f, 16.311995f)
    lineTo(29.425003f, 17.217995f)
    lineTo(28.516003f, 17.669994f)
    lineTo(28.116003f, 17.217995f)
    lineTo(27.680002f, 17.217995f)
    lineTo(27.680002f, 15.735994f)
    lineTo(28.626001f, 15.241994f)
    lineTo(29.352001f, 15.241994f)
    lineTo(29.206001f, 14.666994f)
    lineTo(28.626001f, 14.090994f)
    lineTo(29.606f, 13.884994f)
    lineTo(30.151001f, 13.267994f)
    lineTo(30.587002f, 12.526994f)
    lineTo(31.388002f, 12.526994f)
    lineTo(31.169003f, 11.951994f)
    lineTo(31.678003f, 11.622993f)
    lineTo(31.678003f, 12.280993f)
    lineTo(32.768f, 12.526994f)
    lineTo(33.858f, 11.622993f)
    lineTo(33.931004f, 11.210994f)
    lineTo(34.876003f, 10.552994f)
    cubicTo(34.534004f, 10.595994f, 34.192f, 10.626994f, 33.858f, 10.717994f)
    lineTo(33.858f, 9.976594f)
    lineTo(34.221f, 9.153794f)
    lineTo(33.858f, 9.153794f)
    lineTo(33.06f, 9.893994f)
    lineTo(32.841f, 10.305994f)
    lineTo(33.06f, 10.881994f)
    lineTo(32.695f, 11.868994f)
    lineTo(32.114f, 11.539993f)
    lineTo(31.605999f, 10.963993f)
    lineTo(30.806f, 11.539993f)
    lineTo(30.515f, 10.223993f)
    lineTo(31.896f, 9.318793f)
    lineTo(31.896f, 8.824694f)
    lineTo(32.768f, 8.248994f)
    lineTo(34.149002f, 7.919394f)
    lineTo(35.095f, 8.248994f)
    lineTo(36.839f, 8.578094f)
    lineTo(36.403f, 9.071294f)
    lineTo(35.458f, 9.071294f)
    lineTo(36.403f, 10.058994f)
    lineTo(37.13f, 9.236294f)
    lineTo(37.351f, 8.874494f)
    cubicTo(37.351f, 8.874494f, 40.138f, 11.371994f, 41.730003f, 14.104994f)
    cubicTo(43.323f, 16.837994f, 44.071003f, 20.059994f, 44.071003f, 20.713993f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_1_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_1_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.07f, 9.2363f)
    lineTo(25.997f, 9.729501f)
    lineTo(26.507f, 10.059001f)
    lineTo(27.378f, 9.482901f)
    lineTo(26.942f, 8.989201f)
    lineTo(26.359999f, 9.318801f)
    lineTo(26.069998f, 9.2363f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.87f, 5.8633f)
    lineTo(24.980001f, 5.1225996f)
    lineTo(22.800001f, 5.3691998f)
    lineTo(20.109001f, 6.1094f)
    lineTo(19.601002f, 6.6035f)
    lineTo(21.272001f, 7.7549f)
    lineTo(21.272001f, 8.4131f)
    lineTo(20.618002f, 9.0713005f)
    lineTo(21.491001f, 10.8f)
    lineTo(22.071001f, 10.47f)
    lineTo(22.800001f, 9.3188f)
    cubicTo(23.923f, 8.9716f, 24.93f, 8.5781f, 25.997002f, 8.0844f)
    lineTo(26.87f, 5.8632f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_3_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_3_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.833f, 12.775f)
    lineTo(28.542f, 12.033999f)
    lineTo(28.032f, 12.198999f)
    lineTo(28.178999f, 13.103f)
    lineTo(28.832998f, 12.775f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_4
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_4_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_4_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.123f, 12.609f)
    lineTo(28.977999f, 13.597f)
    lineTo(29.776999f, 13.432f)
    lineTo(30.357998f, 12.857f)
    lineTo(29.849998f, 12.363f)
    cubicTo(29.678999f, 11.908f, 29.481998f, 11.483f, 29.267998f, 11.046f)
    lineTo(28.832998f, 11.046f)
    lineTo(28.832998f, 11.54f)
    lineTo(29.123f, 11.8689995f)
    lineTo(29.123f, 12.608999f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_5_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_5_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.365f, 28.242f)
    lineTo(17.782999f, 27.09f)
    lineTo(16.692999f, 26.843f)
    lineTo(16.110998f, 25.281f)
    lineTo(14.657998f, 25.445f)
    lineTo(13.421998f, 24.541f)
    lineTo(12.112998f, 25.692001f)
    lineTo(12.112998f, 25.874f)
    cubicTo(11.716998f, 25.759f, 11.229998f, 25.744001f, 10.877998f, 25.527f)
    lineTo(10.586998f, 24.705f)
    lineTo(10.586998f, 23.799f)
    lineTo(9.714798f, 23.881f)
    cubicTo(9.787598f, 23.305f, 9.859798f, 22.73f, 9.933098f, 22.154001f)
    lineTo(9.423798f, 22.154001f)
    lineTo(8.915498f, 22.812f)
    lineTo(8.406198f, 23.058f)
    lineTo(7.6790977f, 22.648f)
    lineTo(7.6062975f, 21.743f)
    lineTo(7.7517977f, 20.755f)
    lineTo(8.842598f, 19.932999f)
    lineTo(9.714698f, 19.932999f)
    lineTo(9.859698f, 19.439f)
    lineTo(10.949998f, 19.685f)
    lineTo(11.749998f, 20.673f)
    lineTo(11.894999f, 19.027f)
    lineTo(13.2769985f, 17.875f)
    lineTo(13.784999f, 16.641f)
    lineTo(14.802999f, 16.230001f)
    lineTo(15.383999f, 15.408001f)
    lineTo(16.692999f, 15.160001f)
    lineTo(17.346998f, 14.173f)
    lineTo(15.383998f, 14.173f)
    lineTo(16.619997f, 13.597f)
    lineTo(17.491997f, 13.597f)
    lineTo(18.727997f, 13.185f)
    lineTo(18.873997f, 12.693001f)
    lineTo(18.436996f, 12.281001f)
    lineTo(17.927996f, 12.116001f)
    lineTo(18.073996f, 11.622001f)
    lineTo(17.710995f, 10.882001f)
    lineTo(16.837996f, 11.210001f)
    lineTo(16.983995f, 10.553001f)
    lineTo(15.965996f, 9.976501f)
    lineTo(15.166996f, 11.3740015f)
    lineTo(15.238996f, 11.868002f)
    lineTo(14.439996f, 12.199002f)
    lineTo(13.929996f, 13.2680025f)
    lineTo(13.711995f, 12.281002f)
    lineTo(12.330996f, 11.704002f)
    lineTo(12.112995f, 10.964003f)
    lineTo(13.929996f, 9.893903f)
    lineTo(14.729996f, 9.153703f)
    lineTo(14.802996f, 8.248902f)
    lineTo(14.366996f, 8.001802f)
    lineTo(13.784996f, 7.9193025f)
    lineTo(13.421996f, 8.824602f)
    cubicTo(13.421996f, 8.824602f, 12.813996f, 8.943702f, 12.657996f, 8.982302f)
    cubicTo(10.661996f, 10.822001f, 6.6285963f, 14.792002f, 5.691596f, 22.288301f)
    cubicTo(5.728696f, 22.462301f, 6.370796f, 23.4703f, 6.370796f, 23.4703f)
    lineTo(7.8971963f, 24.3743f)
    lineTo(9.423596f, 24.7863f)
    lineTo(10.077996f, 25.6103f)
    lineTo(11.095996f, 26.3503f)
    lineTo(11.676996f, 26.2683f)
    lineTo(12.112996f, 26.464298f)
    lineTo(12.112996f, 26.597298f)
    lineTo(11.531996f, 28.160297f)
    lineTo(11.0949955f, 28.818298f)
    lineTo(11.240995f, 29.148298f)
    lineTo(10.8779955f, 30.381298f)
    lineTo(12.185995f, 32.7673f)
    lineTo(13.493995f, 33.9203f)
    lineTo(14.0759945f, 34.7423f)
    lineTo(14.002995f, 36.4703f)
    lineTo(14.439995f, 37.4573f)
    lineTo(14.002995f, 39.349297f)
    cubicTo(14.002995f, 39.349297f, 13.968994f, 39.338295f, 14.024995f, 39.527298f)
    cubicTo(14.080995f, 39.717297f, 16.353994f, 40.9783f, 16.497995f, 40.8713f)
    cubicTo(16.641994f, 40.7623f, 16.764996f, 40.666298f, 16.764996f, 40.666298f)
    lineTo(16.619995f, 40.256298f)
    lineTo(17.200994f, 39.6793f)
    lineTo(17.419994f, 39.1033f)
    lineTo(18.364994f, 38.773296f)
    lineTo(19.091993f, 36.963295f)
    lineTo(18.873993f, 36.470295f)
    lineTo(19.381992f, 35.730293f)
    lineTo(20.471992f, 35.482292f)
    lineTo(21.053993f, 34.16629f)
    lineTo(20.908993f, 32.52129f)
    lineTo(21.780993f, 31.28729f)
    lineTo(21.925993f, 30.05229f)
    cubicTo(20.732992f, 29.46129f, 19.549994f, 28.85129f, 18.364992f, 28.24229f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_6_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_6_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.766f, 9.5649f)
    lineTo(17.492f, 10.059f)
    lineTo(18.074001f, 10.059f)
    lineTo(18.074001f, 9.4829f)
    lineTo(17.348001f, 9.1538f)
    lineTo(16.766f, 9.5649f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_7_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_7_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.876f, 8.9072f)
    lineTo(14.512f, 9.812f)
    lineTo(15.239f, 9.812f)
    lineTo(15.603001f, 8.989201f)
    cubicTo(15.917001f, 8.767501f, 16.229f, 8.5444f, 16.548f, 8.331f)
    lineTo(17.275f, 8.5781f)
    cubicTo(17.758999f, 8.9072f, 18.244f, 9.2363f, 18.729f, 9.5649f)
    lineTo(19.456f, 8.907201f)
    lineTo(18.656f, 8.578101f)
    lineTo(18.292f, 7.8374014f)
    lineTo(16.911f, 7.6728015f)
    lineTo(16.838f, 7.2612014f)
    lineTo(16.184f, 7.4262013f)
    lineTo(15.894f, 8.002002f)
    lineTo(15.53f, 7.261302f)
    lineTo(15.384999f, 7.590402f)
    lineTo(15.457999f, 8.413202f)
    lineTo(14.875999f, 8.907203f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_8
alphaStack.add(0, alpha)
alpha *= 0.75f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_8_0
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_8_1
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_9
alphaStack.add(0, alpha)
alpha *= 0.75f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_9_0
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_9_1
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_10
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_10_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_10_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.492f, 6.8496f)
    lineTo(17.856f, 6.521f)
    lineTo(18.583f, 6.3564f)
    cubicTo(19.081f, 6.1142f, 19.581f, 5.9511f, 20.11f, 5.7802f)
    lineTo(19.82f, 5.2865f)
    lineTo(18.881f, 5.4213f)
    lineTo(18.438f, 5.8632f)
    lineTo(17.706999f, 5.9692f)
    lineTo(17.057f, 6.2744f)
    lineTo(16.741f, 6.4272003f)
    lineTo(16.547998f, 6.6855f)
    lineTo(17.491999f, 6.8496003f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_11
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_11_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_11_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.728f, 14.666f)
    lineTo(19.165f, 14.008f)
    lineTo(18.51f, 13.515f)
    lineTo(18.728f, 14.666f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 74, 135, 182))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3956f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 42), center = Offset(15.600843f, 12.142f), radius = 43.526566f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(42.975f, 23.486f)
    cubicTo(42.975f, 33.651f, 34.733997f, 41.892f, 24.568998f, 41.892f)
    cubicTo(14.403999f, 41.892f, 6.162998f, 33.650997f, 6.162998f, 23.485998f)
    cubicTo(6.1633983f, 13.319998f, 14.403998f, 5.079998f, 24.568998f, 5.079998f)
    cubicTo(34.733997f, 5.079898f, 42.975f, 13.319998f, 42.975f, 23.485998f)
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
1.13100004196167f, 0.613099992275238f, 0.0f, 0.0f,
-0.47655999660491943f, 0.8791400194168091f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
54.090999603271484f, 16.04400062561035f, 0.0f, 1.0f)
))}){
// _0_0_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(-25.176f, 30.057f), end = Offset(-22.252f, 21.042f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.88164f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-2.8284f, 21.042f)
    cubicTo(-2.803187f, 24.431059f, -5.7964487f, 27.569181f, -10.6747675f, 29.268103f)
    cubicTo(-15.553087f, 30.967022f, -21.570711f, 30.967022f, -26.449032f, 29.268103f)
    cubicTo(-31.32735f, 27.569181f, -34.32061f, 24.431059f, -34.2954f, 21.042f)
    cubicTo(-34.32061f, 17.65294f, -31.32735f, 14.514817f, -26.44903f, 12.815898f)
    cubicTo(-21.570711f, 11.116979f, -15.553086f, 11.116979f, -10.6747675f, 12.815898f)
    cubicTo(-5.796448f, 14.514817f, -2.8031867f, 17.65294f, -2.8283997f, 21.042f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.939329981803894f, -0.8790900111198425f, 0.0f, 0.0f,
0.6833099722862244f, 0.7301300168037415f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
32.31399917602539f, -4.451600074768066f, 0.0f, 1.0f)
))}){
// _0_0_5
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(-25.176f, 30.057f), end = Offset(-22.114f, 22.662f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.88164f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-2.8284f, 21.042f)
    cubicTo(-2.803187f, 24.431059f, -5.7964487f, 27.569181f, -10.6747675f, 29.268103f)
    cubicTo(-15.553087f, 30.967022f, -21.570711f, 30.967022f, -26.449032f, 29.268103f)
    cubicTo(-31.32735f, 27.569181f, -34.32061f, 24.431059f, -34.2954f, 21.042f)
    cubicTo(-34.32061f, 17.65294f, -31.32735f, 14.514817f, -26.44903f, 12.815898f)
    cubicTo(-21.570711f, 11.116979f, -15.553086f, 11.116979f, -10.6747675f, 12.815898f)
    cubicTo(-5.796448f, 14.514817f, -2.8031867f, 17.65294f, -2.8283997f, 21.042f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
-1.045799970626831f, 0.7672500014305115f, 0.0f, 0.0f,
0.7672500014305115f, 1.045799970626831f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
35.617000579833984f, -22.143999099731445f, 0.0f, 1.0f)
))}){
// _0_0_6
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
14.949999809265137f, 22.93000030517578f, 0.0f, 1.0f)
))}){
// _0_0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(12.071f, 12.493f), radius = 6.7174997f, tileMode = TileMode.Clamp)
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
0.30827000737190247f, 0.0f, 0.0f, 0.0f,
0.0f, 0.30827000737190247f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
23.299999237060547f, 31.57200050354004f, 0.0f, 1.0f)
))}){
// _0_0_6_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
withTransform({
transform(
Matrix(values=floatArrayOf(
-1.2803000211715698f, -0.12615999579429626f, 0.0f, 0.0f,
0.09806200116872787f, -0.9951800107955933f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-2.405100107192993f, 40.52399826049805f, 0.0f, 1.0f)
))}){
// _0_0_7
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(-22.823f, 28.338f), end = Offset(-22.114f, 22.662f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.88164f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-2.8284f, 21.042f)
    cubicTo(-2.803187f, 24.431059f, -5.7964487f, 27.569181f, -10.6747675f, 29.268103f)
    cubicTo(-15.553087f, 30.967022f, -21.570711f, 30.967022f, -26.449032f, 29.268103f)
    cubicTo(-31.32735f, 27.569181f, -34.32061f, 24.431059f, -34.2954f, 21.042f)
    cubicTo(-34.32061f, 17.65294f, -31.32735f, 14.514817f, -26.44903f, 12.815898f)
    cubicTo(-21.570711f, 11.116979f, -15.553086f, 11.116979f, -10.6747675f, 12.815898f)
    cubicTo(-5.796448f, 14.514817f, -2.8031867f, 17.65294f, -2.8283997f, 21.042f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9178699851036072f, -0.8589800000190735f, 0.0f, 0.0f,
0.6676999926567078f, 0.7134299874305725f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
27.632999420166016f, -6.90910005569458f, 0.0f, 1.0f)
))}){
// _0_0_8
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(-21.659f, 15.649f), end = Offset(-21.962f, 21.336f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.90226f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-2.8284f, 21.042f)
    cubicTo(-2.803187f, 24.431059f, -5.7964487f, 27.569181f, -10.6747675f, 29.268103f)
    cubicTo(-15.553087f, 30.967022f, -21.570711f, 30.967022f, -26.449032f, 29.268103f)
    cubicTo(-31.32735f, 27.569181f, -34.32061f, 24.431059f, -34.2954f, 21.042f)
    cubicTo(-34.32061f, 17.65294f, -31.32735f, 14.514817f, -26.44903f, 12.815898f)
    cubicTo(-21.570711f, 11.116979f, -15.553086f, 11.116979f, -10.6747675f, 12.815898f)
    cubicTo(-5.796448f, 14.514817f, -2.8031867f, 17.65294f, -2.8283997f, 21.042f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.8062800168991089f, 0.5915399789810181f, 0.0f, 0.0f,
0.5915399789810181f, 0.8062800168991089f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
12.38599967956543f, -18.02899932861328f, 0.0f, 1.0f)
))}){
// _0_0_9
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
14.949999809265137f, 22.93000030517578f, 0.0f, 1.0f)
))}){
// _0_0_9_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(12.071f, 12.493f), radius = 6.7174997f, tileMode = TileMode.Clamp)
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
0.30827000737190247f, 0.0f, 0.0f, 0.0f,
0.0f, 0.30827000737190247f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
23.299999237060547f, 31.57200050354004f, 0.0f, 1.0f)
))}){
// _0_0_9_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.8062800168991089f, 0.5915399789810181f, 0.0f, 0.0f,
0.5915399789810181f, 0.8062800168991089f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
13.49899959564209f, -31.5f, 0.0f, 1.0f)
))}){
// _0_0_10
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
14.949999809265137f, 22.93000030517578f, 0.0f, 1.0f)
))}){
// _0_0_10_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(12.071f, 12.493f), radius = 6.7174997f, tileMode = TileMode.Clamp)
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
0.30827000737190247f, 0.0f, 0.0f, 0.0f,
0.0f, 0.30827000737190247f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
23.299999237060547f, 31.57200050354004f, 0.0f, 1.0f)
))}){
// _0_0_10_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.8702300190925598f, 0.6385700106620789f, 0.0f, 0.0f,
0.6384599804878235f, 0.8703799843788147f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
25.204999923706055f, -35.3129997253418f, 0.0f, 1.0f)
))}){
// _0_0_11
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
14.949999809265137f, 22.93000030517578f, 0.0f, 1.0f)
))}){
// _0_0_11_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(12.071f, 12.493f), radius = 6.7174997f, tileMode = TileMode.Clamp)
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
0.30827000737190247f, 0.0f, 0.0f, 0.0f,
0.0f, 0.30827000737190247f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
23.299999237060547f, 31.57200050354004f, 0.0f, 1.0f)
))}){
// _0_0_11_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.789f, 12.493f)
    cubicTo(18.799765f, 14.900088f, 17.521776f, 17.12895f, 15.4389515f, 18.335611f)
    cubicTo(13.356128f, 19.542274f, 10.786871f, 19.542274f, 8.704047f, 18.335611f)
    cubicTo(6.621223f, 17.12895f, 5.343234f, 14.900088f, 5.353999f, 12.493f)
    cubicTo(5.343234f, 10.085912f, 6.621223f, 7.8570504f, 8.704047f, 6.650388f)
    cubicTo(10.786871f, 5.4437256f, 13.356128f, 5.4437256f, 15.4389515f, 6.650388f)
    cubicTo(17.521776f, 7.8570504f, 18.799765f, 10.085912f, 18.789f, 12.493f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
            return 0.038841813802719116
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 2.610968828201294
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 47.961158752441406
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 45.38903045654297
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

