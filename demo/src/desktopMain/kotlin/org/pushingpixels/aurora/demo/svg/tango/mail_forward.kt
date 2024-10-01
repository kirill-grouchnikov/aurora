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
class mail_forward : Painter() {
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
1.0136200189590454f, 0.0f, 0.0f, 0.0f,
0.0f, -1.0136200189590454f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
11.129389762878418f, -8.619853973388672f, 0.0f, 1.0f)
))}){
// _0_0_0
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.596972942352295f, 0.0f, 0.0f, 0.0f,
0.0f, 1.5260640382766724f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
3.9802498817443848f, -19.785049438476562f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.5f, 38.7f)
    cubicTo(26.522034f, 40.061657f, 23.90613f, 41.322495f, 19.642813f, 42.00509f)
    cubicTo(15.379496f, 42.68768f, 10.120504f, 42.68768f, 5.8571877f, 42.00509f)
    cubicTo(1.5938711f, 41.322495f, -1.0220345f, 40.061657f, -1.0f, 38.7f)
    cubicTo(-1.0220345f, 37.338345f, 1.5938711f, 36.077507f, 5.8571877f, 35.394913f)
    cubicTo(10.120504f, 34.712322f, 15.379496f, 34.712322f, 19.642813f, 35.394913f)
    cubicTo(23.90613f, 36.077507f, 26.522034f, 37.338345f, 26.5f, 38.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(12.750001f, 38.700005f), radius = 13.750003f, tileMode = TileMode.Clamp)
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
1.0047270059585571f, 0.0f, 0.0f, 0.0f,
0.0f, 1.001608967781067f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.042089998722076416f, -8.972783088684082f, 0.0f, 1.0f)
))}){
// _0_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.34375f, 15.454879f)
    lineTo(6.34375f, 41.44216f)
    lineTo(43.3125f, 41.44216f)
    lineTo(43.25f, 15.554447f)
    cubicTo(43.24999f, 15.548732f, 43.250374f, 15.527358f, 43.25f, 15.521258f)
    cubicTo(43.249268f, 15.514776f, 43.251087f, 15.494928f, 43.25f, 15.488068f)
    cubicTo(43.24856f, 15.480833f, 43.22054f, 15.462487f, 43.21875f, 15.454879f)
    lineTo(6.34375f, 15.454879f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(226, 226, 226, 255), start = Offset(18.427334f, 4.880972f), end = Offset(27.700596f, 36.920475f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=0.99684346f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.34375f, 15.454879f)
    lineTo(6.34375f, 41.44216f)
    lineTo(43.3125f, 41.44216f)
    lineTo(43.25f, 15.554447f)
    cubicTo(43.24999f, 15.548732f, 43.250374f, 15.527358f, 43.25f, 15.521258f)
    cubicTo(43.249268f, 15.514776f, 43.251087f, 15.494928f, 43.25f, 15.488068f)
    cubicTo(43.24856f, 15.480833f, 43.22054f, 15.462487f, 43.21875f, 15.454879f)
    lineTo(6.34375f, 15.454879f)
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
// _0_0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.490673f, 29.058712f)
    lineTo(7.09471f, 40.0307f)
    lineTo(21.003551f, 30.426394f)
    lineTo(30.02171f, 30.426394f)
    lineTo(42.440758f, 39.90859f)
    lineTo(30.577332f, 29.058712f)
    lineTo(20.490673f, 29.058712f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(223, 224, 223, 255), 0.23809524f to Color(166, 176, 166, 255), 1.0f to Color(181, 190, 181, 255), start = Offset(25.378117f, 29.347559f), end = Offset(27.148497f, 40.030693f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.9968433f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.4471445f, 16.725622f)
    cubicTo(7.440469f, 16.738968f, 7.4525223f, 16.74528f, 7.4471445f, 16.757236f)
    cubicTo(7.4447837f, 16.762865f, 7.4180174f, 16.783579f, 7.415987f, 16.788853f)
    cubicTo(7.4142895f, 16.793766f, 7.417349f, 16.81591f, 7.415987f, 16.820465f)
    cubicTo(7.414963f, 16.82466f, 7.416671f, 16.848255f, 7.415987f, 16.852081f)
    lineTo(7.4471445f, 40.341904f)
    lineTo(42.28123f, 40.341904f)
    lineTo(42.218914f, 16.97854f)
    cubicTo(42.21823f, 16.974827f, 42.219936f, 16.951012f, 42.218914f, 16.946924f)
    cubicTo(42.2043f, 16.898813f, 42.177208f, 16.814676f, 42.125443f, 16.725622f)
    lineTo(7.4471445f, 16.725622f)
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
// _0_0_2_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.329298f, 32.99672f)
    cubicTo(20.93719f, 32.550377f, 7.9003873f, 18.771126f, 6.596606f, 16.372023f)
    cubicTo(6.5816493f, 16.343449f, 6.5559707f, 16.288609f, 6.5446897f, 16.2636f)
    lineTo(41.057804f, 16.2636f)
    cubicTo(40.780724f, 18.766403f, 33.533577f, 32.769344f, 31.496525f, 32.99672f)
    cubicTo(31.488352f, 32.99719f, 31.475246f, 32.99672f, 31.46725f, 32.99672f)
    lineTo(23.446392f, 32.99672f)
    cubicTo(23.412766f, 32.99672f, 23.368837f, 33.0041f, 23.329298f, 32.99672f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 33), 1.0f to Color(0, 0, 0, 0), center = Offset(28.275463f, 31.417538f), radius = 14.016269f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.77475f, 31.085394f)
    cubicTo(18.407309f, 30.694258f, 7.945269f, 18.619434f, 7.118584f, 16.51709f)
    cubicTo(7.109327f, 16.49205f, 7.094677f, 16.443993f, 7.088438f, 16.42208f)
    lineTo(42.630646f, 16.42208f)
    cubicTo(41.80703f, 18.6153f, 31.332195f, 30.886145f, 29.185501f, 31.085394f)
    cubicTo(29.176985f, 31.085802f, 29.16359f, 31.085394f, 29.155355f, 31.085394f)
    lineTo(20.895334f, 31.085394f)
    cubicTo(20.860706f, 31.085394f, 20.81388f, 31.091858f, 20.77475f, 31.085394f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(226, 226, 226, 255), start = Offset(18.964605f, 16.702318f), end = Offset(21.692474f, 15.9880085f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(152, 152, 152, 255))
stroke = Stroke(width=0.85390013f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.77475f, 31.085394f)
    cubicTo(18.407309f, 30.694258f, 7.945269f, 18.619434f, 7.118584f, 16.51709f)
    cubicTo(7.109327f, 16.49205f, 7.094677f, 16.443993f, 7.088438f, 16.42208f)
    lineTo(42.630646f, 16.42208f)
    cubicTo(41.80703f, 18.6153f, 31.332195f, 30.886145f, 29.185501f, 31.085394f)
    cubicTo(29.176985f, 31.085802f, 29.16359f, 31.085394f, 29.155355f, 31.085394f)
    lineTo(20.895334f, 31.085394f)
    cubicTo(20.860706f, 31.085394f, 20.81388f, 31.091858f, 20.77475f, 31.085394f)
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
// _0_0_2_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.625174f, 30.490479f)
    cubicTo(18.51921f, 29.999928f, 7.7224803f, 17.98771f, 7.0314245f, 16.466377f)
    cubicTo(7.028888f, 16.460379f, 7.033602f, 16.43969f, 7.0314245f, 16.434063f)
    cubicTo(7.0259733f, 16.418306f, 7.002328f, 16.381763f, 7.0001745f, 16.369436f)
    cubicTo(7.000203f, 16.366104f, 6.9997683f, 16.34006f, 7.0001745f, 16.337122f)
    cubicTo(7.0013437f, 16.334982f, 7.0298696f, 16.33886f, 7.0314245f, 16.337122f)
    lineTo(7.1251745f, 16.240181f)
    lineTo(42.593925f, 16.240181f)
    cubicTo(42.59121f, 16.264507f, 42.57124f, 16.307055f, 42.562675f, 16.337122f)
    cubicTo(42.555172f, 16.360727f, 42.542103f, 16.407354f, 42.531425f, 16.434063f)
    cubicTo(41.609325f, 18.615f, 31.023436f, 30.200512f, 29.187674f, 30.490479f)
    cubicTo(29.172747f, 30.492123f, 29.138826f, 30.490479f, 29.125174f, 30.490479f)
    lineTo(20.750174f, 30.490479f)
    cubicTo(20.719887f, 30.488811f, 20.66042f, 30.49869f, 20.625174f, 30.490479f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(226, 226, 226, 255), start = Offset(18.721912f, 8.433992f), end = Offset(29.953362f, 42.605003f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_6
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(237, 237, 237, 255), start = Offset(18.758963f, 27.487238f), end = Offset(57.395164f, 22.857212f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.85389996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.875174f, 30.051142f)
    cubicTo(18.427216f, 29.50167f, 8.704f, 18.433899f, 7.5314245f, 16.451725f)
    lineTo(42.125175f, 16.451725f)
    cubicTo(40.634987f, 18.784897f, 31.078503f, 29.863516f, 28.968924f, 30.051142f)
    cubicTo(28.96018f, 30.051542f, 28.946142f, 30.051142f, 28.937674f, 30.051142f)
    lineTo(21.031424f, 30.051142f)
    cubicTo(21.00503f, 30.051142f, 20.966541f, 30.054691f, 20.937674f, 30.051142f)
    cubicTo(20.917889f, 30.047995f, 20.896025f, 30.05582f, 20.875174f, 30.051142f)
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
// _0_0_2_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.95951f, 30.447113f)
    lineTo(9.018012f, 38.717968f)
    lineTo(11.237445f, 38.724075f)
    lineTo(21.23557f, 31.855137f)
    lineTo(30.057478f, 30.432299f)
    lineTo(20.95951f, 30.447113f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(17.397203f, 33.357376f), end = Offset(22.17771f, 31.026741f), tileMode = TileMode.Clamp)
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
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.8457019925117493f, 0.0f, 0.0f, 0.0f,
0.0f, -0.8457019925117493f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
51.403228759765625f, 49.140480041503906f, 0.0f, 1.0f)
))}){
// _0_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(49.78573f, 36.46161f)
    cubicTo(31.871506f, 29.801214f, 51.855164f, 14.067224f, 22.462412f, 12.49765f)
    lineTo(22.462412f, 3.1222396f)
    lineTo(5.81393f, 17.708818f)
    lineTo(22.462412f, 33.006348f)
    cubicTo(22.462412f, 33.006348f, 22.462412f, 23.337969f, 22.462412f, 23.337969f)
    cubicTo(39.481644f, 22.456387f, 30.293505f, 37.380238f, 49.78573f, 36.46161f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(253, 157, 20, 255), 1.0f to Color(255, 200, 121, 0), start = Offset(32.707863f, 28.04214f), end = Offset(43.986744f, 34.407845f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(159, 98, 11, 255), 1.0f to Color(255, 200, 121, 0), start = Offset(32.707863f, 28.04214f), end = Offset(43.986744f, 34.407845f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1824504f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(49.78573f, 36.46161f)
    cubicTo(31.871506f, 29.801214f, 51.855164f, 14.067224f, 22.462412f, 12.49765f)
    lineTo(22.462412f, 3.1222396f)
    lineTo(5.81393f, 17.708818f)
    lineTo(22.462412f, 33.006348f)
    cubicTo(22.462412f, 33.006348f, 22.462412f, 23.337969f, 22.462412f, 23.337969f)
    cubicTo(39.481644f, 22.456387f, 30.293505f, 37.380238f, 49.78573f, 36.46161f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.7f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.8457019925117493f, 0.0f, 0.0f, 0.0f,
0.0f, -0.8457019925117493f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
51.403228759765625f, 49.140480041503906f, 0.0f, 1.0f)
))}){
// _0_0_3_1
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(17.802746f, 7.3735547f), end = Offset(29.196747f, 43.90839f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1824498f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.926064f, 35.103043f)
    cubicTo(33.88701f, 28.727802f, 48.67967f, 14.639454f, 21.448702f, 13.549959f)
    lineTo(21.448702f, 5.4508677f)
    cubicTo(21.448702f, 5.4508677f, 7.400963f, 17.714588f, 7.400963f, 17.714588f)
    lineTo(21.448702f, 30.658617f)
    cubicTo(21.448702f, 30.658617f, 21.448702f, 22.38098f, 21.448702f, 22.38098f)
    cubicTo(37.544903f, 20.111229f, 34.13055f, 34.399548f, 44.926064f, 35.103043f)
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
// _0_0_3_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.84375f, 38.1875f)
    lineTo(32.78125f, 45.5f)
    lineTo(45.875f, 34.15625f)
    lineTo(32.84375f, 22.09375f)
    cubicTo(32.84375f, 22.093752f, 32.78125f, 29.65625f, 32.78125f, 29.65625f)
    cubicTo(20.26305f, 32.276806f, 23.547112f, 18.410612f, 11.6875f, 18.9375f)
    cubicTo(22.872463f, 24.02322f, 9.730253f, 37.29154f, 32.84375f, 38.1875f)
    close()
    moveTo(40.78125f, 29.625f)
    cubicTo(40.860493f, 29.62021f, 40.917072f, 29.627337f, 41.0f, 29.625f)
    lineTo(45.8125f, 34.1875f)
    lineTo(32.875f, 45.46875f)
    lineTo(32.78125f, 38.15625f)
    lineTo(28.59375f, 37.625f)
    cubicTo(37.938694f, 35.04962f, 35.789047f, 29.926678f, 40.78125f, 29.625f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(11.6875f, 14.319358f), end = Offset(37.113785f, 36.087452f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 2.383056402206421
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 6.0077385902404785
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 44.85194778442383
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 41.595096588134766
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

