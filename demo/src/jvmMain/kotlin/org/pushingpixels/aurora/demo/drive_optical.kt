package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.toComposeBitmap
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class drive_optical private constructor(var _width: Int, var _height: Int) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.024527400732040405f, 0.0f, 0.0f, 45.6905403137207f,
0.0f, 0.02086758054792881f, 0.0f, 34.82775115966797f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_0
shape = Outline.Rectangle(rect = Rect(left = -1559.2523193359375f, top = -150.6968536376953f, right = -219.6187744140625f, bottom = 327.6603240966797f))
brush = LinearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), startX = -1051.9354f, startY = -150.69684f, endX = -1051.9354f, endY = 327.6604f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-219.61876f, -150.68037f)
generalPath!!.cubicTo(-219.61876f, -150.68037f, -219.61876f, 327.65042f, -219.61876f, 327.65042f)
generalPath!!.cubicTo(-76.74459f, 328.55087f, 125.78146f, 220.48074f, 125.78138f, 88.45424f)
generalPath!!.cubicTo(125.78138f, -43.572304f, -33.655437f, -150.68036f, -219.61876f, -150.68037f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = -211.146f, centerY = 85.66791f, radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-1559.2523f, -150.68037f)
generalPath!!.cubicTo(-1559.2523f, -150.68037f, -1559.2523f, 327.65042f, -1559.2523f, 327.65042f)
generalPath!!.cubicTo(-1702.1265f, 328.55087f, -1904.6525f, 220.48074f, -1904.6525f, 88.45424f)
generalPath!!.cubicTo(-1904.6525f, -43.572304f, -1745.2157f, -150.68036f, -1559.2523f, -150.68037f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = -1567.7247f, centerY = 85.66791f, radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
brush = SolidColor(Color(83, 83, 83, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.28569f, 6.018085f)
generalPath!!.cubicTo(10.66069f, 6.018085f, 10.254441f, 6.308265f, 10.004442f, 6.8618383f)
generalPath!!.cubicTo(10.004441f, 6.8618383f, 3.5356915f, 23.965403f, 3.5356915f, 23.965403f)
generalPath!!.cubicTo(3.5356915f, 23.965403f, 3.2856915f, 24.636961f, 3.2856915f, 25.746653f)
generalPath!!.cubicTo(3.2856915f, 25.746653f, 3.2856915f, 35.39662f, 3.2856915f, 35.39662f)
generalPath!!.cubicTo(3.2856915f, 36.479233f, 3.943477f, 37.02162f, 4.9419417f, 37.02162f)
generalPath!!.lineTo(43.50444f, 37.02162f)
generalPath!!.cubicTo(44.489292f, 37.02162f, 45.09819f, 36.30344f, 45.09819f, 35.17787f)
generalPath!!.lineTo(45.09819f, 25.527903f)
generalPath!!.cubicTo(45.09819f, 25.527903f, 45.20415f, 24.757479f, 45.00444f, 24.215403f)
generalPath!!.lineTo(38.28569f, 7.018089f)
generalPath!!.cubicTo(38.101166f, 6.506182f, 37.648785f, 6.0299907f, 37.16069f, 6.018085f)
generalPath!!.lineTo(11.28569f, 6.018085f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5657143f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.883882999420166f,
0.0f, 1.0f, 0.0f, 1.2609419854925363E-6f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(41.896076f, 33.769478f)
generalPath!!.cubicTo(41.924404f, 38.171925f, 38.561268f, 42.248405f, 33.080143f, 44.45533f)
generalPath!!.cubicTo(27.599014f, 46.66226f, 20.8378f, 46.66226f, 15.356671f, 44.45533f)
generalPath!!.cubicTo(9.875545f, 42.248405f, 6.5124087f, 38.171925f, 6.540737f, 33.769478f)
generalPath!!.cubicTo(6.5124087f, 29.367031f, 9.875545f, 25.290552f, 15.356671f, 23.083626f)
generalPath!!.cubicTo(20.8378f, 20.876698f, 27.599014f, 20.876698f, 33.080143f, 23.083626f)
generalPath!!.cubicTo(38.561268f, 25.290552f, 41.924404f, 29.367031f, 41.896076f, 33.769478f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = 24.218407f, centerY = 33.769478f, radius = 17.67767f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(3.2735915f, 25.052277f)
generalPath!!.lineTo(4.0381937f, 24.360062f)
generalPath!!.lineTo(41.647884f, 24.422562f)
generalPath!!.lineTo(45.11029f, 24.739859f)
generalPath!!.lineTo(45.11029f, 35.17839f)
generalPath!!.cubicTo(45.11029f, 36.30396f, 44.503273f, 37.02172f, 43.518417f, 37.02172f)
generalPath!!.lineTo(4.9354315f, 37.02172f)
generalPath!!.cubicTo(3.9369667f, 37.02172f, 3.2735915f, 36.47967f, 3.2735915f, 35.397057f)
generalPath!!.lineTo(3.2735915f, 25.052277f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(187, 187, 187, 255), 1.0f to Color(159, 159, 159, 255), startX = 7.6046205f, startY = 26.53664f, endX = 36.183067f, endY = 38.999397f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(3.5490842f, 23.969868f)
generalPath!!.cubicTo(2.8347986f, 25.434155f, 3.5484686f, 26.362724f, 4.5847983f, 26.362724f)
generalPath!!.cubicTo(4.5847983f, 26.362724f, 43.584797f, 26.362724f, 43.584797f, 26.362724f)
generalPath!!.cubicTo(44.703842f, 26.338915f, 45.430035f, 25.35082f, 45.013367f, 24.219868f)
generalPath!!.lineTo(38.299084f, 7.009162f)
generalPath!!.cubicTo(38.11456f, 6.497255f, 37.64432f, 6.0210633f, 37.156223f, 6.009158f)
generalPath!!.lineTo(11.299083f, 6.009158f)
generalPath!!.cubicTo(10.674083f, 6.009158f, 10.263369f, 6.3127313f, 10.01337f, 6.8663044f)
generalPath!!.cubicTo(10.01337f, 6.8663044f, 3.5490842f, 23.969868f, 3.5490842f, 23.969868f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(228, 228, 228, 255), 1.0f to Color(211, 211, 211, 255), centerX = 15.571496f, centerY = 8.810947f, radius = 31.511501f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(44.79616f, 23.684153f)
generalPath!!.cubicTo(44.859684f, 24.934126f, 44.38216f, 25.999992f, 43.474045f, 26.027903f)
generalPath!!.cubicTo(43.474045f, 26.027903f, 5.3553295f, 26.0279f, 5.3553295f, 26.027903f)
generalPath!!.cubicTo(4.0660977f, 26.027903f, 3.4875937f, 25.702955f, 3.271279f, 25.159845f)
generalPath!!.cubicTo(3.3630404f, 26.104177f, 4.0970964f, 26.809153f, 5.3553295f, 26.809153f)
generalPath!!.cubicTo(5.3553295f, 26.80915f, 43.474045f, 26.809153f, 43.474045f, 26.809153f)
generalPath!!.cubicTo(44.550053f, 26.776081f, 45.226852f, 25.385128f, 44.82621f, 23.814362f)
generalPath!!.lineTo(44.79616f, 23.684153f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 12.378357f, startY = 2.4885988f, endX = 44.0961f, endY = 45.676098f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.642515f, 6.47118f)
generalPath!!.cubicTo(11.040823f, 6.47118f, 10.649724f, 6.750539f, 10.409049f, 7.2834673f)
generalPath!!.cubicTo(10.409048f, 7.2834673f, 3.994034f, 23.874195f, 3.994034f, 23.874195f)
generalPath!!.cubicTo(3.994034f, 23.874195f, 3.7533574f, 24.520712f, 3.7533574f, 25.58902f)
generalPath!!.cubicTo(3.7533574f, 25.58902f, 3.7533574f, 34.879116f, 3.7533574f, 34.879116f)
generalPath!!.cubicTo(3.7533574f, 36.233856f, 4.1974134f, 36.506016f, 5.3478413f, 36.506016f)
generalPath!!.lineTo(43.034744f, 36.506016f)
generalPath!!.cubicTo(44.357872f, 36.506016f, 44.56906f, 36.189617f, 44.56906f, 34.66852f)
generalPath!!.lineTo(44.56906f, 25.378426f)
generalPath!!.cubicTo(44.56906f, 25.378426f, 44.67107f, 24.636736f, 44.478806f, 24.114874f)
generalPath!!.lineTo(37.885616f, 7.3088913f)
generalPath!!.cubicTo(37.707973f, 6.8160744f, 37.334965f, 6.482641f, 36.86507f, 6.47118f)
generalPath!!.lineTo(11.642515f, 6.47118f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9336519837379456f, 0.0f, 0.0f, 1.6127159595489502f,
0.0f, 0.9336519837379456f, 0.0f, -0.36777400970458984f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_0
shape = Outline.Rectangle(rect = Rect(left = 5.341440200805664f, top = 32.36338424682617f, right = 43.27215385437012f, bottom = 36.22842311859131f))
brush = LinearGradient(0.0f to Color(101, 101, 101, 255), 1.0f to Color(101, 101, 101, 0), startX = 24.306797f, startY = 33.69343f, endX = 24.306797f, endY = 37.609333f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.9921136f, 31.810345f)
generalPath!!.cubicTo(7.717179f, 32.641365f, 7.5233636f, 33.51374f, 7.5233636f, 34.404095f)
generalPath!!.cubicTo(7.5233626f, 40.774326f, 14.971312f, 45.872845f, 24.210863f, 45.872845f)
generalPath!!.cubicTo(33.450413f, 45.872845f, 40.867115f, 40.774326f, 40.867115f, 34.404095f)
generalPath!!.cubicTo(40.867115f, 33.51751f, 40.70229f, 32.63814f, 40.429615f, 31.810345f)
generalPath!!.lineTo(24.867113f, 31.810345f)
generalPath!!.cubicTo(26.70693f, 32.05551f, 28.210863f, 33.0697f, 28.210863f, 34.404095f)
generalPath!!.cubicTo(28.210863f, 35.914562f, 26.401684f, 37.154095f, 24.210863f, 37.154095f)
generalPath!!.cubicTo(22.02004f, 37.154095f, 20.210863f, 35.914562f, 20.210863f, 34.404095f)
generalPath!!.cubicTo(20.210863f, 33.0697f, 21.714796f, 32.05551f, 23.554613f, 31.810345f)
generalPath!!.lineTo(7.9921136f, 31.810345f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(235, 235, 235, 255), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(235, 235, 235, 255), startX = 15.630198f, startY = 26.568027f, endX = 32.313206f, endY = 41.750854f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.8358636f, 32.341595f)
generalPath!!.cubicTo(7.6633096f, 33.0074f, 7.5233636f, 33.70288f, 7.5233636f, 34.404095f)
generalPath!!.cubicTo(7.5233636f, 40.774326f, 14.971312f, 45.872845f, 24.210863f, 45.872845f)
generalPath!!.cubicTo(33.450413f, 45.872845f, 40.867115f, 40.774326f, 40.867115f, 34.404095f)
generalPath!!.cubicTo(40.867115f, 33.70288f, 40.72717f, 33.0074f, 40.554615f, 32.341595f)
generalPath!!.lineTo(7.8358636f, 32.341595f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(217, 217, 217, 255), 1.0f to Color(238, 238, 238, 0), startX = 25.795177f, startY = 39.450394f, endX = 25.683758f, endY = 32.728996f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(128, 128, 128, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.8358636f, 32.341595f)
generalPath!!.cubicTo(7.6633096f, 33.0074f, 7.5233636f, 33.70288f, 7.5233636f, 34.404095f)
generalPath!!.cubicTo(7.5233636f, 40.774326f, 14.971312f, 45.872845f, 24.210863f, 45.872845f)
generalPath!!.cubicTo(33.450413f, 45.872845f, 40.867115f, 40.774326f, 40.867115f, 34.404095f)
generalPath!!.cubicTo(40.867115f, 33.70288f, 40.72717f, 33.0074f, 40.554615f, 32.341595f)
generalPath!!.lineTo(7.8358636f, 32.341595f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.10999996f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.57214f, 31.835312f)
generalPath!!.cubicTo(15.798652f, 32.755287f, 15.247183f, 33.294632f, 15.247183f, 34.42291f)
generalPath!!.cubicTo(15.247182f, 37.895943f, 19.357576f, 40.62928f, 24.277803f, 40.62928f)
generalPath!!.cubicTo(29.31518f, 40.62928f, 33.308426f, 37.815174f, 33.308426f, 34.42291f)
generalPath!!.cubicTo(33.308426f, 33.278057f, 32.722183f, 32.74986f, 31.948603f, 31.835312f)
generalPath!!.lineTo(26.571648f, 31.835312f)
generalPath!!.cubicTo(28.249533f, 32.378742f, 29.194088f, 33.07225f, 29.194088f, 34.42291f)
generalPath!!.cubicTo(29.19409f, 36.28058f, 26.972214f, 37.80503f, 24.277803f, 37.80503f)
generalPath!!.cubicTo(21.583391f, 37.80503f, 19.36152f, 36.28058f, 19.36152f, 34.42291f)
generalPath!!.cubicTo(19.36152f, 33.071465f, 20.280853f, 32.37832f, 21.960295f, 31.835312f)
generalPath!!.lineTo(16.57214f, 31.835312f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(18.573984f, 44.74288f)
generalPath!!.lineTo(22.362783f, 37.212044f)
generalPath!!.cubicTo(21.485636f, 36.996353f, 20.814201f, 36.57293f, 20.39036f, 36.015736f)
generalPath!!.lineTo(10.011877f, 39.604015f)
generalPath!!.cubicTo(11.761798f, 41.989464f, 14.806535f, 43.84448f, 18.573984f, 44.74288f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 107))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5464481f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_5
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 13.975835f, startY = 23.831125f, endX = 42.274876f, endY = 49.858604f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.911814f, 32.267513f)
generalPath!!.cubicTo(8.663047f, 33.007385f, 8.507773f, 33.947163f, 8.507773f, 34.740917f)
generalPath!!.cubicTo(8.507773f, 40.331493f, 16.102156f, 44.81307f, 24.21087f, 44.81307f)
generalPath!!.cubicTo(32.319588f, 44.81307f, 39.713146f, 40.331493f, 39.713146f, 34.740917f)
generalPath!!.cubicTo(39.713146f, 33.946625f, 39.529343f, 33.007847f, 39.280247f, 32.267513f)
generalPath!!.lineTo(8.911814f, 32.267513f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7_6
shape = Outline.Rectangle(rect = Rect(left = 5.341440200805664f, top = 31.627470016479492f, right = 43.20521354675293f, bottom = 32.81865096092224f))
brush = LinearGradient(0.0f to Color(101, 101, 101, 255), 1.0f to Color(101, 101, 101, 0), startX = 24.306797f, startY = 32.818634f, endX = 24.306797f, endY = 33.946075f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.3125f, 30.25f)
generalPath!!.lineTo(40.0625f, 30.25f)
generalPath!!.cubicTo(40.0625f, 30.25f, 40.60396f, 31.370993f, 40.0f, 33.625f)
generalPath!!.cubicTo(40.0f, 33.625f, 26.6875f, 33.125f, 26.6875f, 33.125f)
generalPath!!.cubicTo(28.537859f, 31.274641f, 26.3125f, 30.25f, 26.3125f, 30.25f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), startX = 23.375f, startY = 28.433596f, endX = 23.375f, endY = 32.938416f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(22.098146f, 30.25f)
generalPath!!.lineTo(8.348146f, 30.25f)
generalPath!!.cubicTo(8.348146f, 30.25f, 7.806687f, 31.370993f, 8.410646f, 33.625f)
generalPath!!.cubicTo(8.410646f, 33.625f, 21.723146f, 33.125f, 21.723146f, 33.125f)
generalPath!!.cubicTo(19.872787f, 31.274641f, 22.098146f, 30.25f, 22.098146f, 30.25f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), startX = 23.375f, startY = 28.433596f, endX = 23.375f, endY = 32.938416f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)

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
            return 5.01808500289917
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
            return 41.64417266845703
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return drive_optical(
                _width = (width.value * AmbientDensity.current.density).toInt(),
                _height = (height.value * AmbientDensity.current.density).toInt()
            )
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return drive_optical(getOrigWidth().toInt(), getOrigHeight().toInt())
                }
            }
        }

        
    }

    override fun getWidth(): Int {
        return _width
    }

    override fun getHeight(): Int {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = (width.value * AmbientDensity.current.density).toInt()
        _height = (height.value * AmbientDensity.current.density).toInt()
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = _width / fullOrigWidth
            val coef2 = _height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()
            val coefDp = coef.dp.toPx()

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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset(0.0f, 0.0f))
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

