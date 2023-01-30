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
class preferences_desktop_assistive_technology : Painter() {
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
alpha *= 0.4064171f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
2.4600489139556885f, 0.0f, 0.0f, 0.0f,
0.0f, 2.4600489139556885f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-49.40945816040039f, -67.96373748779297f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.769554f, 44.565483f)
    cubicTo(36.780075f, 45.361816f, 35.53091f, 46.099186f, 33.495064f, 46.498383f)
    cubicTo(31.459217f, 46.89758f, 28.947906f, 46.89758f, 26.91206f, 46.498383f)
    cubicTo(24.876213f, 46.099186f, 23.627047f, 45.361816f, 23.63757f, 44.565483f)
    cubicTo(23.627047f, 43.76915f, 24.876213f, 43.03178f, 26.91206f, 42.632584f)
    cubicTo(28.947906f, 42.233387f, 31.459217f, 42.233387f, 33.495064f, 42.632584f)
    cubicTo(35.53091f, 43.03178f, 36.780075f, 43.76915f, 36.769554f, 44.565483f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(30.203562f, 44.565502f), radius = 6.5659924f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 4.414728164672852f, top = 3.5233452320098877f, right = 44.47665214538574f, bottom = 43.58526921272278f,radiusX = 10.909647941589355f, radiusY = 10.909647941589355f))
brush = Brush.radialGradient(0.0f to Color(156, 188, 222, 255), 1.0f to Color(32, 74, 135, 255), center = Offset(25.159983f, 35.84003f), radius = 43.693687f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 4.414728164672852f, top = 3.5233452320098877f, right = 44.47665214538574f, bottom = 43.58526921272278f,radiusX = 10.909647941589355f, radiusY = 10.909647941589355f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=2.0000021f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.8954033851623535f, top = 5.004019737243652f, right = 42.99603891372681f, bottom = 42.104655265808105f,radiusX = 7.81350040435791f, radiusY = 7.81350040435791f))
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
1.5521910190582275f, -0.6401000022888184f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7692310214042664f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7692310214042664f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
6.846158027648926f, 4.576913833618164f, 0.0f, 1.0f)
))}){
// _0_0_3_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.444443941116333f, 0.0f, 0.0f, 0.0f,
0.0f, 1.444443941116333f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.841267108917236f, -5.8095221519470215f, 0.0f, 1.0f)
))}){
// _0_0_3_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.857141f, 13.071428f)
    cubicTo(20.862293f, 14.2232065f, 20.250782f, 15.289703f, 19.254162f, 15.8670845f)
    cubicTo(18.257544f, 16.444466f, 17.02817f, 16.444466f, 16.03155f, 15.8670845f)
    cubicTo(15.03493f, 15.289703f, 14.42342f, 14.2232065f, 14.428571f, 13.071428f)
    cubicTo(14.42342f, 11.91965f, 15.03493f, 10.853153f, 16.03155f, 10.275772f)
    cubicTo(17.02817f, 9.698391f, 18.257544f, 9.698391f, 19.254162f, 10.275772f)
    cubicTo(20.250782f, 10.853153f, 20.862293f, 11.91965f, 20.857141f, 13.071428f)
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
// _0_0_3_0_1
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=2.5f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.571428f, 13.142857f)
    lineTo(20.0f, 30.0f)
    lineTo(32.0f, 27.428572f)
    lineTo(34.42857f, 37.0f)
    lineTo(38.0f, 36.0f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
    moveTo(17.374998f, 21.668259f)
    cubicTo(14.821727f, 23.078575f, 13.0f, 25.694616f, 13.0f, 28.80768f)
    cubicTo(13.0f, 33.36178f, 16.715132f, 37.076912f, 21.26923f, 37.076912f)
    cubicTo(25.584839f, 37.076912f, 29.03297f, 33.705624f, 29.39423f, 29.480759f)
    lineTo(27.399036f, 29.937489f)
    cubicTo(26.85153f, 32.888084f, 24.382202f, 35.153835f, 21.26923f, 35.153835f)
    cubicTo(17.755636f, 35.153835f, 14.923077f, 32.321274f, 14.923077f, 28.80768f)
    cubicTo(14.923077f, 26.611685f, 16.12003f, 24.778076f, 17.807692f, 23.639412f)
    lineTo(17.374998f, 21.668259f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.9230775f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.857143f, 20.857143f)
    lineTo(31.714287f, 20.0f)
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
0.6082140207290649f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6082140207290649f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
20.155799865722656f, 19.565080642700195f, 0.0f, 1.0f)
))}){
// _0_0_5
alphaStack.add(0, alpha)
alpha *= 0.40909088f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.8838850259780884f, 2.4748740196228027f, 0.0f, 1.0f)
))}){
// _0_0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.078056f, 39.161163f)
    cubicTo(45.1095f, 41.378223f, 41.37642f, 43.431126f, 35.29237f, 44.542526f)
    cubicTo(29.208319f, 45.65393f, 21.70337f, 45.65393f, 15.619318f, 44.542526f)
    cubicTo(9.535267f, 43.431126f, 5.802187f, 41.378223f, 5.8336315f, 39.161163f)
    cubicTo(5.802187f, 36.944103f, 9.535267f, 34.8912f, 15.619318f, 33.7798f)
    cubicTo(21.70337f, 32.668396f, 29.208319f, 32.668396f, 35.29237f, 33.7798f)
    cubicTo(41.37642f, 34.8912f, 45.1095f, 36.944103f, 45.078056f, 39.161163f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(25.455845f, 39.16115f), radius = 19.622211f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.25f, 0.46875f)
    cubicTo(22.784561f, 0.5005963f, 22.332167f, 0.5726847f, 21.875f, 0.625f)
    lineTo(21.84375f, 0.625f)
    lineTo(20.75f, 6.59375f)
    cubicTo(18.967276f, 6.99974f, 17.29009f, 7.6887417f, 15.78125f, 8.625f)
    lineTo(10.875f, 5.09375f)
    cubicTo(9.548712f, 6.1234407f, 8.341802f, 7.3243456f, 7.28125f, 8.625f)
    lineTo(10.6875f, 13.59375f)
    cubicTo(9.653267f, 15.174265f, 8.875532f, 16.978973f, 8.4375f, 18.875f)
    cubicTo(8.437425f, 18.883963f, 8.437439f, 18.904688f, 8.4375f, 18.90625f)
    lineTo(2.5f, 19.84375f)
    cubicTo(2.3914466f, 20.730383f, 2.34375f, 21.646688f, 2.34375f, 22.5625f)
    cubicTo(2.34375f, 23.3118f, 2.3644395f, 24.051088f, 2.4375f, 24.78125f)
    lineTo(8.375f, 25.84375f)
    cubicTo(8.797279f, 27.905642f, 9.599459f, 29.831263f, 10.71875f, 31.53125f)
    lineTo(7.1875f, 36.375f)
    cubicTo(8.198826f, 37.63052f, 9.366392f, 38.773617f, 10.625f, 39.78125f)
    lineTo(15.625f, 36.34375f)
    cubicTo(17.372433f, 37.458466f, 19.323084f, 38.240124f, 21.4375f, 38.625f)
    lineTo(22.375f, 44.53125f)
    cubicTo(23.041183f, 44.59189f, 23.724348f, 44.59375f, 24.40625f, 44.59375f)
    cubicTo(25.368935f, 44.59375f, 26.288486f, 44.557266f, 27.21875f, 44.4375f)
    lineTo(28.34375f, 38.40625f)
    cubicTo(30.35131f, 37.90665f, 32.23722f, 37.03996f, 33.875f, 35.875f)
    lineTo(38.6875f, 39.375f)
    cubicTo(39.935528f, 38.3132f, 41.07678f, 37.092743f, 42.0625f, 35.78125f)
    lineTo(38.5625f, 30.71875f)
    cubicTo(39.510353f, 29.08176f, 40.16713f, 27.275608f, 40.5f, 25.34375f)
    lineTo(46.40625f, 24.40625f)
    cubicTo(46.458042f, 23.789904f, 46.46875f, 23.192163f, 46.46875f, 22.5625f)
    cubicTo(46.46875f, 21.468287f, 46.341568f, 20.395416f, 46.1875f, 19.34375f)
    lineTo(40.1875f, 18.25f)
    cubicTo(39.717304f, 16.513777f, 38.945824f, 14.893898f, 37.96875f, 13.4375f)
    lineTo(41.5f, 8.59375f)
    cubicTo(40.405426f, 7.255143f, 39.156822f, 6.018569f, 37.78125f, 4.96875f)
    lineTo(32.6875f, 8.46875f)
    cubicTo(31.223503f, 7.602913f, 29.648037f, 6.938568f, 27.9375f, 6.5625f)
    lineTo(27.0f, 0.625f)
    cubicTo(26.146702f, 0.5246246f, 25.286379f, 0.46875f, 24.40625f, 0.46875f)
    cubicTo(24.168379f, 0.46875f, 23.923567f, 0.4612654f, 23.6875f, 0.46875f)
    cubicTo(23.572416f, 0.47239882f, 23.458534f, 0.4620551f, 23.34375f, 0.46875f)
    cubicTo(23.312662f, 0.4705632f, 23.281029f, 0.4666269f, 23.25f, 0.46875f)
    close()
    moveTo(24.0625f, 15.65625f)
    cubicTo(24.176666f, 15.650457f, 24.290651f, 15.65625f, 24.40625f, 15.65625f)
    cubicTo(28.105377f, 15.65625f, 31.125f, 18.675875f, 31.125f, 22.375f)
    cubicTo(31.125002f, 26.074125f, 28.105375f, 29.0625f, 24.40625f, 29.0625f)
    cubicTo(20.707125f, 29.062502f, 17.71875f, 26.074125f, 17.71875f, 22.375f)
    cubicTo(17.718752f, 18.791473f, 20.52335f, 15.835842f, 24.0625f, 15.65625f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(201, 201, 201, 255), 0.25f to Color(248, 248, 248, 255), 0.5f to Color(226, 226, 226, 255), 0.75f to Color(176, 176, 176, 255), 1.0f to Color(201, 201, 201, 255), start = Offset(12.9344635f, 8.047592f), end = Offset(37.861908f, 42.077095f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(128, 128, 128, 255))
stroke = Stroke(width=1.6441573f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.25f, 0.46875f)
    cubicTo(22.784561f, 0.5005963f, 22.332167f, 0.5726847f, 21.875f, 0.625f)
    lineTo(21.84375f, 0.625f)
    lineTo(20.75f, 6.59375f)
    cubicTo(18.967276f, 6.99974f, 17.29009f, 7.6887417f, 15.78125f, 8.625f)
    lineTo(10.875f, 5.09375f)
    cubicTo(9.548712f, 6.1234407f, 8.341802f, 7.3243456f, 7.28125f, 8.625f)
    lineTo(10.6875f, 13.59375f)
    cubicTo(9.653267f, 15.174265f, 8.875532f, 16.978973f, 8.4375f, 18.875f)
    cubicTo(8.437425f, 18.883963f, 8.437439f, 18.904688f, 8.4375f, 18.90625f)
    lineTo(2.5f, 19.84375f)
    cubicTo(2.3914466f, 20.730383f, 2.34375f, 21.646688f, 2.34375f, 22.5625f)
    cubicTo(2.34375f, 23.3118f, 2.3644395f, 24.051088f, 2.4375f, 24.78125f)
    lineTo(8.375f, 25.84375f)
    cubicTo(8.797279f, 27.905642f, 9.599459f, 29.831263f, 10.71875f, 31.53125f)
    lineTo(7.1875f, 36.375f)
    cubicTo(8.198826f, 37.63052f, 9.366392f, 38.773617f, 10.625f, 39.78125f)
    lineTo(15.625f, 36.34375f)
    cubicTo(17.372433f, 37.458466f, 19.323084f, 38.240124f, 21.4375f, 38.625f)
    lineTo(22.375f, 44.53125f)
    cubicTo(23.041183f, 44.59189f, 23.724348f, 44.59375f, 24.40625f, 44.59375f)
    cubicTo(25.368935f, 44.59375f, 26.288486f, 44.557266f, 27.21875f, 44.4375f)
    lineTo(28.34375f, 38.40625f)
    cubicTo(30.35131f, 37.90665f, 32.23722f, 37.03996f, 33.875f, 35.875f)
    lineTo(38.6875f, 39.375f)
    cubicTo(39.935528f, 38.3132f, 41.07678f, 37.092743f, 42.0625f, 35.78125f)
    lineTo(38.5625f, 30.71875f)
    cubicTo(39.510353f, 29.08176f, 40.16713f, 27.275608f, 40.5f, 25.34375f)
    lineTo(46.40625f, 24.40625f)
    cubicTo(46.458042f, 23.789904f, 46.46875f, 23.192163f, 46.46875f, 22.5625f)
    cubicTo(46.46875f, 21.468287f, 46.341568f, 20.395416f, 46.1875f, 19.34375f)
    lineTo(40.1875f, 18.25f)
    cubicTo(39.717304f, 16.513777f, 38.945824f, 14.893898f, 37.96875f, 13.4375f)
    lineTo(41.5f, 8.59375f)
    cubicTo(40.405426f, 7.255143f, 39.156822f, 6.018569f, 37.78125f, 4.96875f)
    lineTo(32.6875f, 8.46875f)
    cubicTo(31.223503f, 7.602913f, 29.648037f, 6.938568f, 27.9375f, 6.5625f)
    lineTo(27.0f, 0.625f)
    cubicTo(26.146702f, 0.5246246f, 25.286379f, 0.46875f, 24.40625f, 0.46875f)
    cubicTo(24.168379f, 0.46875f, 23.923567f, 0.4612654f, 23.6875f, 0.46875f)
    cubicTo(23.572416f, 0.47239882f, 23.458534f, 0.4620551f, 23.34375f, 0.46875f)
    cubicTo(23.312662f, 0.4705632f, 23.281029f, 0.4666269f, 23.25f, 0.46875f)
    close()
    moveTo(24.0625f, 15.65625f)
    cubicTo(24.176666f, 15.650457f, 24.290651f, 15.65625f, 24.40625f, 15.65625f)
    cubicTo(28.105377f, 15.65625f, 31.125f, 18.675875f, 31.125f, 22.375f)
    cubicTo(31.125002f, 26.074125f, 28.105375f, 29.0625f, 24.40625f, 29.0625f)
    cubicTo(20.707125f, 29.062502f, 17.71875f, 26.074125f, 17.71875f, 22.375f)
    cubicTo(17.718752f, 18.791473f, 20.52335f, 15.835842f, 24.0625f, 15.65625f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.64772725f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6684309840202332f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6684309840202332f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
8.69454574584961f, 6.464436054229736f, 0.0f, 1.0f)
))}){
// _0_0_5_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=2.4597247f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.239223f, 23.781593f)
    cubicTo(36.25962f, 28.342402f, 33.83816f, 32.565517f, 29.89175f, 34.85183f)
    cubicTo(25.94534f, 37.13814f, 21.077263f, 37.13814f, 17.130852f, 34.85183f)
    cubicTo(13.18444f, 32.565517f, 10.762982f, 28.342402f, 10.783379f, 23.781593f)
    cubicTo(10.762982f, 19.220785f, 13.18444f, 14.997669f, 17.130852f, 12.711357f)
    cubicTo(21.077263f, 10.425044f, 25.94534f, 10.425044f, 29.89175f, 12.711357f)
    cubicTo(33.83816f, 14.997669f, 36.25962f, 19.220785f, 36.239223f, 23.781593f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.34659088f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.644156f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.66343f, 2.8294024f)
    lineTo(21.834734f, 8.282713f)
    cubicTo(20.257912f, 8.641812f, 17.357416f, 9.740083f, 16.022846f, 10.568205f)
    lineTo(11.614124f, 7.2775526f)
    cubicTo(10.441019f, 8.188315f, 10.36057f, 8.250079f, 9.42251f, 9.40051f)
    lineTo(12.610179f, 14.128108f)
    cubicTo(11.6954f, 15.526075f, 10.596635f, 18.017319f, 10.202127f, 19.797586f)
    cubicTo(10.202127f, 19.797586f, 4.61633f, 20.73919f, 4.61633f, 20.73919f)
    cubicTo(4.5203147f, 21.523417f, 4.566459f, 23.201902f, 4.631081f, 23.84773f)
    lineTo(9.966658f, 24.80891f)
    cubicTo(10.340164f, 26.632656f, 11.7379f, 29.568249f, 12.727915f, 31.07189f)
    lineTo(9.353855f, 35.52969f)
    cubicTo(10.248374f, 36.640198f, 10.427401f, 36.741817f, 11.540642f, 37.63307f)
    lineTo(16.052347f, 34.327667f)
    cubicTo(17.597954f, 35.313633f, 20.66584f, 36.51303f, 22.536043f, 36.853455f)
    lineTo(23.276506f, 42.23811f)
    cubicTo(23.865746f, 42.291744f, 25.493572f, 42.4422f, 26.316393f, 42.336266f)
    lineTo(27.145086f, 36.730892f)
    cubicTo(28.920776f, 36.288998f, 31.988947f, 35.02955f, 33.437565f, 33.999138f)
    lineTo(37.944447f, 37.255463f)
    cubicTo(39.04833f, 36.316303f, 39.05822f, 36.17479f, 39.93009f, 35.01477f)
    lineTo(36.59036f, 30.267595f)
    cubicTo(37.428738f, 28.819675f, 38.51277f, 25.987974f, 38.807198f, 24.279242f)
    lineTo(44.275257f, 23.371965f)
    cubicTo(44.321068f, 22.826805f, 44.3233f, 21.306536f, 44.187027f, 20.376335f)
    lineTo(38.61598f, 19.415154f)
    cubicTo(38.200092f, 17.879461f, 36.77285f, 15.112168f, 35.908627f, 13.823982f)
    lineTo(39.4495f, 9.366181f)
    cubicTo(38.481354f, 8.182182f, 38.12138f, 8.019728f, 36.904682f, 7.091162f)
    lineTo(32.240913f, 10.430893f)
    cubicTo(30.946007f, 9.66506f, 28.363857f, 8.497611f, 26.850887f, 8.164979f)
    lineTo(26.027018f, 2.8294024f)
    cubicTo(25.272272f, 2.7406204f, 23.094751f, 2.7800448f, 22.66343f, 2.8294024f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
            return 3.9147281646728516
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.0233452320098877
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 44.08527374267578
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.976654052734375
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

