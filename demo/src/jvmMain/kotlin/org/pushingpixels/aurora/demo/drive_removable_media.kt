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
class drive_removable_media private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
0.0f, 0.02086758054792881f, 0.0f, 36.15359878540039f,
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
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(-1051.9354f, -150.69684f), end = Offset(-1051.9354f, 327.6604f), tileMode = TileMode.Clamp)
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
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-211.146f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
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
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-1567.7247f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(11.28569f, 8.0180855f)
generalPath!!.cubicTo(10.66069f, 8.0180855f, 10.254441f, 8.308265f, 10.004442f, 8.861838f)
generalPath!!.cubicTo(10.004441f, 8.861838f, 3.5356915f, 25.965403f, 3.5356915f, 25.965403f)
generalPath!!.cubicTo(3.5356915f, 25.965403f, 3.2856915f, 26.636961f, 3.2856915f, 27.746653f)
generalPath!!.cubicTo(3.2856915f, 27.746653f, 3.2856915f, 37.39662f, 3.2856915f, 37.39662f)
generalPath!!.cubicTo(3.2856915f, 38.479233f, 3.943477f, 39.02162f, 4.9419417f, 39.02162f)
generalPath!!.lineTo(43.50444f, 39.02162f)
generalPath!!.cubicTo(44.489292f, 39.02162f, 45.09819f, 38.30344f, 45.09819f, 37.17787f)
generalPath!!.lineTo(45.09819f, 27.527903f)
generalPath!!.cubicTo(45.09819f, 27.527903f, 45.20415f, 26.757479f, 45.00444f, 26.215403f)
generalPath!!.lineTo(38.28569f, 9.018089f)
generalPath!!.cubicTo(38.101166f, 8.506182f, 37.648785f, 8.029991f, 37.16069f, 8.0180855f)
generalPath!!.lineTo(11.28569f, 8.0180855f)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(3.2735915f, 27.052277f)
generalPath!!.lineTo(4.0381937f, 26.360062f)
generalPath!!.lineTo(41.647884f, 26.422562f)
generalPath!!.lineTo(45.11029f, 26.739859f)
generalPath!!.lineTo(45.11029f, 37.17839f)
generalPath!!.cubicTo(45.11029f, 38.30396f, 44.503273f, 39.02172f, 43.518417f, 39.02172f)
generalPath!!.lineTo(4.9354315f, 39.02172f)
generalPath!!.cubicTo(3.9369667f, 39.02172f, 3.2735915f, 38.47967f, 3.2735915f, 37.397057f)
generalPath!!.lineTo(3.2735915f, 27.052277f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(187, 187, 187, 255), 1.0f to Color(159, 159, 159, 255), start = Offset(7.6046205f, 28.53664f), end = Offset(36.183067f, 40.999397f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(3.5490842f, 25.969868f)
generalPath!!.cubicTo(2.8347986f, 27.434155f, 3.5484686f, 28.362724f, 4.5847983f, 28.362724f)
generalPath!!.cubicTo(4.5847983f, 28.362724f, 43.584797f, 28.362724f, 43.584797f, 28.362724f)
generalPath!!.cubicTo(44.703842f, 28.338915f, 45.430035f, 27.35082f, 45.013367f, 26.219868f)
generalPath!!.lineTo(38.299084f, 9.009162f)
generalPath!!.cubicTo(38.11456f, 8.497255f, 37.64432f, 8.021063f, 37.156223f, 8.009158f)
generalPath!!.lineTo(11.299083f, 8.009158f)
generalPath!!.cubicTo(10.674083f, 8.009158f, 10.263369f, 8.312731f, 10.01337f, 8.866304f)
generalPath!!.cubicTo(10.01337f, 8.866304f, 3.5490842f, 25.969868f, 3.5490842f, 25.969868f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(228, 228, 228, 255), 1.0f to Color(211, 211, 211, 255), center = Offset(15.571496f, 10.810947f), radius = 31.511501f, tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(43.562435f, 27.674347f)
generalPath!!.cubicTo(43.562435f, 27.674347f, 5.443718f, 27.674347f, 5.443718f, 27.674347f)
generalPath!!.cubicTo(4.154486f, 27.674347f, 3.5317879f, 27.437788f, 3.315473f, 26.894678f)
generalPath!!.cubicTo(3.4072344f, 27.83901f, 4.185485f, 28.455597f, 5.443718f, 28.455597f)
generalPath!!.cubicTo(5.443718f, 28.455597f, 43.562435f, 28.455597f, 43.562435f, 28.455597f)
generalPath!!.cubicTo(44.63844f, 28.422525f, 45.30183f, 27.596846f, 45.04718f, 26.300495f)
generalPath!!.cubicTo(44.91313f, 27.142076f, 44.470547f, 27.646437f, 43.562435f, 27.674347f)
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(38.34467f, 9.212088f)
generalPath!!.cubicTo(38.34467f, 9.212088f, 44.5f, 24.75f, 44.5f, 24.75f)
generalPath!!.cubicTo(43.881283f, 24.352251f, 43.618717f, 24.036612f, 43.0f, 24.125f)
generalPath!!.lineTo(5.25f, 24.125f)
generalPath!!.cubicTo(4.5428934f, 24.125f, 3.8383882f, 24.875f, 3.8383882f, 24.875f)
generalPath!!.lineTo(10.125f, 8.875f)
generalPath!!.cubicTo(10.258882f, 8.375346f, 10.748699f, 8.073223f, 11.411612f, 8.073223f)
generalPath!!.lineTo(36.830807f, 7.984835f)
generalPath!!.cubicTo(38.15663f, 8.161612f, 38.1237f, 8.5933695f, 38.34467f, 9.212088f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(122, 122, 122, 255), 1.0f to Color(165, 165, 165, 255), start = Offset(34.420757f, 22.70549f), end = Offset(16.12734f, 10.596288f), tileMode = TileMode.Clamp)
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(44.70777f, 25.36201f)
generalPath!!.cubicTo(44.373547f, 25.128502f, 44.0728f, 25.167488f, 43.51824f, 25.13958f)
generalPath!!.cubicTo(43.51824f, 25.13958f, 4.7366114f, 24.874414f, 4.7366114f, 24.874414f)
generalPath!!.cubicTo(4.162087f, 24.918608f, 3.495712f, 25.684093f, 3.495712f, 25.684093f)
generalPath!!.cubicTo(3.495712f, 25.684093f, 4.066774f, 24.284061f, 4.066774f, 24.284061f)
generalPath!!.cubicTo(4.066774f, 24.284061f, 4.4064555f, 23.120892f, 5.664689f, 23.120892f)
generalPath!!.cubicTo(5.6646886f, 23.120893f, 42.855328f, 23.120892f, 42.855328f, 23.120892f)
generalPath!!.cubicTo(43.57778f, 23.153963f, 44.02256f, 23.550547f, 44.207493f, 24.01646f)
generalPath!!.lineTo(44.70777f, 25.36201f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(104, 104, 104, 255))
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
// _0_0_7
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(12.378357f, 4.488599f), end = Offset(44.0961f, 47.676098f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.642515f, 8.47118f)
generalPath!!.cubicTo(11.040823f, 8.47118f, 10.649724f, 8.750539f, 10.409049f, 9.283467f)
generalPath!!.cubicTo(10.409048f, 9.283467f, 3.994034f, 25.874195f, 3.994034f, 25.874195f)
generalPath!!.cubicTo(3.994034f, 25.874195f, 3.7533574f, 26.520712f, 3.7533574f, 27.58902f)
generalPath!!.cubicTo(3.7533574f, 27.58902f, 3.7533574f, 36.879116f, 3.7533574f, 36.879116f)
generalPath!!.cubicTo(3.7533574f, 38.233856f, 4.1974134f, 38.506016f, 5.3478413f, 38.506016f)
generalPath!!.lineTo(43.034744f, 38.506016f)
generalPath!!.cubicTo(44.357872f, 38.506016f, 44.56906f, 38.189617f, 44.56906f, 36.66852f)
generalPath!!.lineTo(44.56906f, 27.378426f)
generalPath!!.cubicTo(44.56906f, 27.378426f, 44.67107f, 26.636736f, 44.478806f, 26.114874f)
generalPath!!.lineTo(37.885616f, 9.308891f)
generalPath!!.cubicTo(37.707973f, 8.816075f, 37.334965f, 8.482641f, 36.86507f, 8.47118f)
generalPath!!.lineTo(11.642515f, 8.47118f)
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
0.8281970024108887f, 0.0f, 0.0f, 4.176000118255615f,
0.0f, 0.6102399826049805f, 0.0f, 11.161430358886719f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
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
// _0_0_8_0
shape = Outline.Rectangle(rect = Rect(left = 5.341440200805664f, top = 32.36338424682617f, right = 43.27215385437012f, bottom = 36.22842311859131f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 121), 1.0f to Color(0, 0, 0, 0), start = Offset(25.785229f, 32.363384f), end = Offset(25.785229f, 35.670216f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.71428573f
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
generalPath!!.moveTo(8.625f, 33.25f)
generalPath!!.cubicTo(8.625f, 33.25f, 9.069649f, 34.06694f, 9.865144f, 34.022747f)
generalPath!!.cubicTo(9.865144f, 34.022747f, 40.715385f, 34.0f, 40.715385f, 34.0f)
generalPath!!.cubicTo(40.671192f, 31.56932f, 40.027885f, 30.88143f, 40.027885f, 30.88143f)
generalPath!!.lineTo(40.0625f, 33.3125f)
generalPath!!.lineTo(8.625f, 33.25f)
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
1.380952000617981f, 0.0f, 0.0f, -15.625f,
0.0f, 1.380952000617981f, 0.0f, -10.946430206298828f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.0f, 33.984375f)
generalPath!!.cubicTo(42.00158f, 34.33711f, 41.8143f, 34.663723f, 41.509087f, 34.840546f)
generalPath!!.cubicTo(41.203873f, 35.01737f, 40.827377f, 35.01737f, 40.522163f, 34.840546f)
generalPath!!.cubicTo(40.21695f, 34.663723f, 40.02967f, 34.33711f, 40.03125f, 33.984375f)
generalPath!!.cubicTo(40.02967f, 33.63164f, 40.21695f, 33.305027f, 40.522163f, 33.128204f)
generalPath!!.cubicTo(40.827377f, 32.95138f, 41.203873f, 32.95138f, 41.509087f, 33.128204f)
generalPath!!.cubicTo(41.8143f, 33.305027f, 42.00158f, 33.63164f, 42.0f, 33.984375f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(111, 111, 111, 255), center = Offset(40.796864f, 33.734367f), radius = 1.234375f, tileMode = TileMode.Clamp)
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
            return 7.018085479736328
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
            return 35.991573333740234
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
            return drive_removable_media(
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
                    return drive_removable_media(getOrigWidth().toInt(), getOrigHeight().toInt())
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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

