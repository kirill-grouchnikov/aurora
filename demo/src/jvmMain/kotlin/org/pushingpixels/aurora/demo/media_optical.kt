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
import org.pushingpixels.aurora.icon.toComposeBitmap
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
class media_optical private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
alpha *= 0.55f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 1.0f,
0.0f, 1.0662909746170044f, 0.0f, -2.885106086730957f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(45.96194f, 41.63604f)
generalPath!!.cubicTo(45.998203f, 43.663067f, 41.693386f, 45.540005f, 34.677544f, 46.556145f)
generalPath!!.cubicTo(27.661701f, 47.572285f, 19.007347f, 47.572285f, 11.991504f, 46.556145f)
generalPath!!.cubicTo(4.9756603f, 45.540005f, 0.67084694f, 43.663067f, 0.70710754f, 41.63604f)
generalPath!!.cubicTo(0.67084694f, 39.609013f, 4.9756603f, 37.732075f, 11.991504f, 36.715935f)
generalPath!!.cubicTo(19.007347f, 35.699795f, 27.661701f, 35.699795f, 34.677544f, 36.715935f)
generalPath!!.cubicTo(41.693386f, 37.732075f, 45.998203f, 39.609013f, 45.96194f, 41.63604f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = 23.334524f, centerY = 41.63604f, radius = 22.627417f, tileMode = TileMode.Clamp)
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
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.166667f)
generalPath!!.cubicTo(12.799386f, 4.166667f, 3.5136719f, 13.452381f, 3.5136719f, 25.0f)
generalPath!!.cubicTo(3.5136719f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.166667f, 24.347006f, 4.166667f)
generalPath!!.lineTo(24.347006f, 4.166667f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(235, 235, 235, 255), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(235, 235, 235, 255), startX = 13.628668f, startY = 10.81964f, endX = 34.47914f, endY = 38.34225f, tileMode = TileMode.Clamp)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(251, 251, 251, 255), 0.5f to Color(182, 182, 182, 255), 1.0f to Color(228, 228, 228, 255), startX = 10.387953f, startY = 36.091064f, endX = 37.90795f, endY = 14.408687f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(128, 128, 128, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.10999995f
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
generalPath!!.moveTo(24.347006f, 14.895835f)
generalPath!!.cubicTo(18.70442f, 14.895835f, 14.24284f, 19.488638f, 14.24284f, 25.0f)
generalPath!!.cubicTo(14.24284f, 30.642588f, 18.835644f, 35.104164f, 24.347006f, 35.104164f)
generalPath!!.cubicTo(29.989592f, 35.104164f, 34.45117f, 30.511364f, 34.45117f, 25.0f)
generalPath!!.cubicTo(34.45117f, 19.357414f, 29.858368f, 14.895835f, 24.347006f, 14.895835f)
generalPath!!.lineTo(24.347006f, 14.895835f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.511364f)
generalPath!!.cubicTo(21.328878f, 30.511364f, 18.835644f, 28.01813f, 18.835644f, 25.0f)
generalPath!!.cubicTo(18.835644f, 21.981873f, 21.328878f, 19.488638f, 24.347006f, 19.488638f)
generalPath!!.cubicTo(27.365133f, 19.488638f, 29.858368f, 21.981873f, 29.858368f, 25.0f)
generalPath!!.cubicTo(29.858368f, 28.01813f, 27.365133f, 30.511364f, 24.347006f, 30.511364f)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(29.922112f, 5.669234f)
generalPath!!.lineTo(26.325518f, 19.663897f)
generalPath!!.cubicTo(27.450222f, 19.963413f, 28.34966f, 20.659367f, 28.960646f, 21.622438f)
generalPath!!.lineTo(41.352867f, 14.073153f)
generalPath!!.cubicTo(38.81762f, 9.943439f, 34.748657f, 6.9050856f, 29.922112f, 5.669234f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(255, 255, 255, 83), 1.0f to Color(255, 255, 255, 154), startX = 20.753254f, startY = 15.677085f, endX = 30.597004f, endY = 32.395836f, tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(17.307823f, 43.766056f)
generalPath!!.lineTo(22.04308f, 30.114557f)
generalPath!!.cubicTo(20.946815f, 29.723566f, 20.107655f, 28.956005f, 19.577936f, 27.945951f)
generalPath!!.lineTo(6.6068754f, 34.4506f)
generalPath!!.cubicTo(8.793933f, 38.77481f, 12.599255f, 42.13748f, 17.307823f, 43.766056f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(255, 255, 255, 83), 1.0f to Color(255, 255, 255, 154), startX = 20.753254f, startY = 15.677085f, endX = 30.597004f, endY = 32.395836f, tileMode = TileMode.Clamp)
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
// _0_0_6
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 10.50172f, startY = 3.610016f, endX = 48.798885f, endY = 54.698483f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347002f, 5.2023654f)
generalPath!!.cubicTo(13.373458f, 5.2023654f, 4.5493712f, 14.026454f, 4.5493712f, 24.999996f)
generalPath!!.cubicTo(4.5493712f, 35.97354f, 13.373458f, 44.797626f, 24.347002f, 44.797626f)
generalPath!!.cubicTo(35.320545f, 44.797626f, 44.144634f, 35.97354f, 44.144634f, 24.999996f)
generalPath!!.cubicTo(44.144634f, 14.026454f, 35.320545f, 5.2023654f, 24.347002f, 5.2023654f)
generalPath!!.lineTo(24.347002f, 5.2023654f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6721311f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0883883535861969f,
0.0f, 1.0f, 0.0f, 0.08838865160942078f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 28.702885f, startY = 31.494707f, endX = 17.74273f, endY = 18.366575f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.93053865f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.40559f, 24.930641f)
generalPath!!.cubicTo(30.415363f, 27.116028f, 29.255081f, 29.139605f, 27.364092f, 30.235128f)
generalPath!!.cubicTo(25.473104f, 31.330654f, 23.140484f, 31.330654f, 21.249496f, 30.235128f)
generalPath!!.cubicTo(19.358507f, 29.139605f, 18.198225f, 27.116028f, 18.207998f, 24.930641f)
generalPath!!.cubicTo(18.198225f, 22.745255f, 19.358507f, 20.721678f, 21.249496f, 19.626154f)
generalPath!!.cubicTo(23.140484f, 18.530628f, 25.473104f, 18.530628f, 27.364092f, 19.626154f)
generalPath!!.cubicTo(29.255081f, 20.721678f, 30.415363f, 22.745255f, 30.40559f, 24.930641f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.1142857f
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
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 243, 7, 255), 0.5f to Color(22, 110, 255, 255), 1.0f to Color(255, 255, 255, 0), centerX = 29.168167f, centerY = 34.741737f, radius = 27.30389f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.09714284f
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
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(179, 7, 255, 210), 1.0f to Color(240, 255, 139, 164), 1.0f to Color(255, 255, 255, 0), centerX = 24.346987f, centerY = 24.99999f, radius = 31.178343f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
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
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(184, 192, 76, 0), centerX = 26.64513f, centerY = 27.298136f, radius = 4.7885404f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.62285715f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 255, 200, 255), 1.0f to Color(154, 145, 239, 0), centerX = 19.220472f, centerY = 24.999994f, radius = 2.623346f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3714286f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 255, 200, 255), 1.0f to Color(154, 145, 239, 0), centerX = 23.163475f, centerY = 18.969662f, radius = 2.2961469f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.23428573f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.347006f, 4.1666665f)
generalPath!!.cubicTo(12.799386f, 4.1666665f, 3.513672f, 13.452381f, 3.513672f, 25.0f)
generalPath!!.cubicTo(3.513672f, 36.54762f, 12.799386f, 45.833336f, 24.347006f, 45.833336f)
generalPath!!.cubicTo(35.894627f, 45.833336f, 45.18034f, 36.54762f, 45.18034f, 25.0f)
generalPath!!.cubicTo(45.18034f, 13.452381f, 35.894627f, 4.1666665f, 24.347006f, 4.1666665f)
generalPath!!.lineTo(24.347006f, 4.1666665f)
generalPath!!.close()
generalPath!!.moveTo(24.347006f, 30.000002f)
generalPath!!.cubicTo(21.608912f, 30.000002f, 19.347006f, 27.738096f, 19.347006f, 25.0f)
generalPath!!.cubicTo(19.347006f, 22.261906f, 21.608912f, 20.0f, 24.347006f, 20.0f)
generalPath!!.cubicTo(27.0851f, 20.0f, 29.347006f, 22.261906f, 29.347006f, 25.0f)
generalPath!!.cubicTo(29.347006f, 27.738096f, 27.0851f, 30.000002f, 24.347006f, 30.000002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 255, 200, 255), 1.0f to Color(154, 145, 239, 0), centerX = 25.584427f, centerY = 30.12656f, radius = 1.1169107f, tileMode = TileMode.Clamp)
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
            return 1.670846939086914
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.6666665077209473
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 45.327354431152344
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.17412567138672
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
            return media_optical(
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
                    return media_optical(getOrigWidth().toInt(), getOrigHeight().toInt())
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

