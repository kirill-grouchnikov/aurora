package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.skija.PathEffect
import org.pushingpixels.aurora.icon.AuroraIcon
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class camera_photo private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
0.024527400732040405f, 0.0f, 0.0f, 45.15005874633789f,
0.0f, 0.02086758054792881f, 0.0f, 37.31475830078125f,
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
alpha *= 0.39873418f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6931939721107483f, 0.0f, 0.0f, 13.910189628601074f,
0.0f, 0.8110229969024658f, 0.0f, 9.956689834594727f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.875f, 36.8125f)
generalPath!!.cubicTo(42.903046f, 39.656754f, 39.57371f, 42.29041f, 34.14767f, 43.71622f)
generalPath!!.cubicTo(28.72163f, 45.14203f, 22.02837f, 45.14203f, 16.60233f, 43.71622f)
generalPath!!.cubicTo(11.1762905f, 42.29041f, 7.8469563f, 39.656754f, 7.875f, 36.8125f)
generalPath!!.cubicTo(7.8469563f, 33.968246f, 11.1762905f, 31.33459f, 16.60233f, 29.908781f)
generalPath!!.cubicTo(22.02837f, 28.482971f, 28.72163f, 28.482971f, 34.14767f, 29.908781f)
generalPath!!.cubicTo(39.57371f, 31.33459f, 42.903046f, 33.968246f, 42.875f, 36.8125f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = 25.375f, centerY = 36.812485f, radius = 17.5f, tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(8.932776f, 18.92078f)
generalPath!!.cubicTo(5.4615073f, 18.92078f, 2.6534934f, 20.04078f, 2.6534934f, 21.42078f)
generalPath!!.cubicTo(2.6534934f, 21.577572f, 2.8105028f, 21.709461f, 2.8800654f, 21.85828f)
generalPath!!.cubicTo(2.869879f, 21.907713f, 2.782963f, 21.89946f, 2.782963f, 21.95203f)
generalPath!!.cubicTo(2.782963f, 21.95203f, 2.6534934f, 38.26399f, 2.6534934f, 38.42078f)
generalPath!!.cubicTo(2.6534934f, 39.80078f, 5.4615073f, 40.92078f, 8.932776f, 40.92078f)
generalPath!!.cubicTo(12.404046f, 40.92078f, 15.212059f, 39.80078f, 15.212059f, 38.42078f)
generalPath!!.lineTo(15.08259f, 21.95203f)
generalPath!!.cubicTo(15.08259f, 21.89946f, 14.995674f, 21.907713f, 14.985487f, 21.85828f)
generalPath!!.cubicTo(15.05505f, 21.709461f, 15.212059f, 21.577572f, 15.212059f, 21.42078f)
generalPath!!.cubicTo(15.212059f, 20.04078f, 12.404046f, 18.92078f, 8.932776f, 18.92078f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(85, 87, 83, 255), 1.0f to Color(49, 51, 48, 255), startX = 7.989489f, startY = 29.92078f, endX = 15.21206f, endY = 29.92078f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(40, 40, 40, 255))
stroke = Stroke(width=2.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.932776f, 18.92078f)
generalPath!!.cubicTo(5.4615073f, 18.92078f, 2.6534934f, 20.04078f, 2.6534934f, 21.42078f)
generalPath!!.cubicTo(2.6534934f, 21.577572f, 2.8105028f, 21.709461f, 2.8800654f, 21.85828f)
generalPath!!.cubicTo(2.869879f, 21.907713f, 2.782963f, 21.89946f, 2.782963f, 21.95203f)
generalPath!!.cubicTo(2.782963f, 21.95203f, 2.6534934f, 38.26399f, 2.6534934f, 38.42078f)
generalPath!!.cubicTo(2.6534934f, 39.80078f, 5.4615073f, 40.92078f, 8.932776f, 40.92078f)
generalPath!!.cubicTo(12.404046f, 40.92078f, 15.212059f, 39.80078f, 15.212059f, 38.42078f)
generalPath!!.lineTo(15.08259f, 21.95203f)
generalPath!!.cubicTo(15.08259f, 21.89946f, 14.995674f, 21.907713f, 14.985487f, 21.85828f)
generalPath!!.cubicTo(15.05505f, 21.709461f, 15.212059f, 21.577572f, 15.212059f, 21.42078f)
generalPath!!.cubicTo(15.212059f, 20.04078f, 12.404046f, 18.92078f, 8.932776f, 18.92078f)
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(2.142857f, 38.647934f)
generalPath!!.lineTo(42.285713f, 38.647934f)
generalPath!!.cubicTo(43.39688f, 38.748947f, 44.505077f, 37.4979f, 44.505077f, 36.79079f)
generalPath!!.lineTo(44.505077f, 19.142857f)
generalPath!!.cubicTo(44.40406f, 18.031689f, 40.536106f, 16.719103f, 39.62697f, 16.618088f)
generalPath!!.lineTo(37.19485f, 16.618088f)
generalPath!!.lineTo(34.271324f, 13.533125f)
generalPath!!.lineTo(23.714287f, 13.533125f)
generalPath!!.lineTo(20.278818f, 16.564531f)
generalPath!!.cubicTo(20.278818f, 16.564531f, 13.552003f, 16.776186f, 8.337708f, 17.731617f)
generalPath!!.cubicTo(3.123414f, 18.687048f, 2.0f, 20.318892f, 2.0f, 21.531075f)
generalPath!!.lineTo(2.142857f, 38.647934f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(53, 54, 51, 255), 1.0f to Color(103, 105, 100, 255), startX = 16.353432f, startY = 26.09341f, endX = 30.130077f, endY = 26.09341f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(40, 40, 40, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(2.142857f, 38.647934f)
generalPath!!.lineTo(42.285713f, 38.647934f)
generalPath!!.cubicTo(43.39688f, 38.748947f, 44.505077f, 37.4979f, 44.505077f, 36.79079f)
generalPath!!.lineTo(44.505077f, 19.142857f)
generalPath!!.cubicTo(44.40406f, 18.031689f, 40.536106f, 16.719103f, 39.62697f, 16.618088f)
generalPath!!.lineTo(37.19485f, 16.618088f)
generalPath!!.lineTo(34.271324f, 13.533125f)
generalPath!!.lineTo(23.714287f, 13.533125f)
generalPath!!.lineTo(20.278818f, 16.564531f)
generalPath!!.cubicTo(20.278818f, 16.564531f, 13.552003f, 16.776186f, 8.337708f, 17.731617f)
generalPath!!.cubicTo(3.123414f, 18.687048f, 2.0f, 20.318892f, 2.0f, 21.531075f)
generalPath!!.lineTo(2.142857f, 38.647934f)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.890617f, 13.907352f)
generalPath!!.lineTo(35.254326f, 17.695423f)
generalPath!!.lineTo(37.123104f, 17.240854f)
generalPath!!.lineTo(33.890617f, 13.907352f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(63, 65, 62, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2569140195846558f, 0.0f, 0.0f, -11.686750411987305f,
0.0f, 1.2569140195846558f, 0.0f, -10.024789810180664f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(43.133514f, 33.90837f)
generalPath!!.cubicTo(43.13667f, 34.614212f, 42.76192f, 35.267788f, 42.151165f, 35.621624f)
generalPath!!.cubicTo(41.540413f, 35.975456f, 40.787018f, 35.975456f, 40.176266f, 35.621624f)
generalPath!!.cubicTo(39.56551f, 35.267788f, 39.19076f, 34.614212f, 39.193916f, 33.90837f)
generalPath!!.cubicTo(39.19076f, 33.20253f, 39.56551f, 32.548954f, 40.176266f, 32.195118f)
generalPath!!.cubicTo(40.787018f, 31.841284f, 41.540413f, 31.841284f, 42.151165f, 32.195118f)
generalPath!!.cubicTo(42.76192f, 32.548954f, 43.13667f, 33.20253f, 43.133514f, 33.90837f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(88, 91, 87, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(167, 167, 167, 255))
stroke = Stroke(width=0.79559946f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(43.133514f, 33.90837f)
generalPath!!.cubicTo(43.13667f, 34.614212f, 42.76192f, 35.267788f, 42.151165f, 35.621624f)
generalPath!!.cubicTo(41.540413f, 35.975456f, 40.787018f, 35.975456f, 40.176266f, 35.621624f)
generalPath!!.cubicTo(39.56551f, 35.267788f, 39.19076f, 34.614212f, 39.193916f, 33.90837f)
generalPath!!.cubicTo(39.19076f, 33.20253f, 39.56551f, 32.548954f, 40.176266f, 32.195118f)
generalPath!!.cubicTo(40.787018f, 31.841284f, 41.540413f, 31.841284f, 42.151165f, 32.195118f)
generalPath!!.cubicTo(42.76192f, 32.548954f, 43.13667f, 33.20253f, 43.133514f, 33.90837f)
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
1.0829230546951294f, 0.0f, 0.0f, -3.3205010890960693f,
0.0f, 1.0829230546951294f, 0.0f, -4.385684013366699f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.714287f, 33.57143f)
generalPath!!.cubicTo(40.73054f, 37.20593f, 38.800884f, 40.571323f, 35.656f, 42.39328f)
generalPath!!.cubicTo(32.51111f, 44.21524f, 28.631748f, 44.21524f, 25.48686f, 42.39328f)
generalPath!!.cubicTo(22.341972f, 40.571323f, 20.412317f, 37.20593f, 20.428572f, 33.57143f)
generalPath!!.cubicTo(20.412317f, 29.93693f, 22.341972f, 26.571537f, 25.48686f, 24.74958f)
generalPath!!.cubicTo(28.631748f, 22.927622f, 32.51111f, 22.927622f, 35.656f, 24.74958f)
generalPath!!.cubicTo(38.800884f, 26.571537f, 40.73054f, 29.93693f, 40.714287f, 33.57143f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(54, 61, 64, 255), 0.5f to Color(129, 143, 149, 255), 1.0f to Color(49, 56, 59, 255), startX = 22.549892f, startY = 39.051506f, endX = 38.41619f, endY = 30.566227f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0829230546951294f, 0.0f, 0.0f, -3.155039072036743f,
0.0f, 1.0829230546951294f, 0.0f, -2.4959299564361572f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.714287f, 33.57143f)
generalPath!!.cubicTo(40.73054f, 37.20593f, 38.800884f, 40.571323f, 35.656f, 42.39328f)
generalPath!!.cubicTo(32.51111f, 44.21524f, 28.631748f, 44.21524f, 25.48686f, 42.39328f)
generalPath!!.cubicTo(22.341972f, 40.571323f, 20.412317f, 37.20593f, 20.428572f, 33.57143f)
generalPath!!.cubicTo(20.412317f, 29.93693f, 22.341972f, 26.571537f, 25.48686f, 24.74958f)
generalPath!!.cubicTo(28.631748f, 22.927622f, 32.51111f, 22.927622f, 35.656f, 24.74958f)
generalPath!!.cubicTo(38.800884f, 26.571537f, 40.73054f, 29.93693f, 40.714287f, 33.57143f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(139, 139, 139, 255), 0.5f to Color(211, 217, 218, 255), 1.0f to Color(130, 130, 130, 255), startX = 23.660564f, startY = 35.302856f, endX = 33.904015f, endY = 30.916578f, tileMode = TileMode.Clamp)
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
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.932773f, 18.71875f)
generalPath!!.cubicTo(5.4975805f, 18.71875f, 2.71875f, 19.83875f, 2.71875f, 21.21875f)
generalPath!!.cubicTo(2.71875f, 21.375542f, 2.8741276f, 21.507433f, 2.9429674f, 21.65625f)
generalPath!!.cubicTo(2.9328866f, 21.705683f, 2.8468742f, 21.69743f, 2.8468742f, 21.75f)
generalPath!!.cubicTo(2.8468742f, 21.75f, 2.71875f, 38.06196f, 2.71875f, 38.21875f)
generalPath!!.cubicTo(2.71875f, 39.59875f, 5.4975805f, 40.71875f, 8.932773f, 40.71875f)
generalPath!!.cubicTo(12.367966f, 40.71875f, 15.146796f, 39.59875f, 15.146796f, 38.21875f)
generalPath!!.lineTo(15.018672f, 21.75f)
generalPath!!.cubicTo(15.018672f, 21.69743f, 14.932659f, 21.705683f, 14.922578f, 21.65625f)
generalPath!!.cubicTo(14.991418f, 21.507433f, 15.146796f, 21.375542f, 15.146796f, 21.21875f)
generalPath!!.cubicTo(15.146796f, 19.83875f, 12.367966f, 18.71875f, 8.932773f, 18.71875f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(85, 87, 83, 255), 1.0f to Color(49, 51, 48, 255), startX = 7.999284f, startY = 29.71875f, endX = 15.146788f, endY = 29.71875f, tileMode = TileMode.Clamp)
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
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.243662f, 14.15989f)
generalPath!!.lineTo(23.486048f, 17.796438f)
generalPath!!.lineTo(23.132492f, 19.008621f)
generalPath!!.lineTo(20.354572f, 16.937809f)
generalPath!!.lineTo(24.243662f, 14.15989f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(123, 126, 121, 255))
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
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.03008f, 27.9953f)
generalPath!!.lineTo(23.940615f, 19.412683f)
generalPath!!.lineTo(35.15331f, 19.412683f)
generalPath!!.lineTo(39.991886f, 26.528042f)
generalPath!!.lineTo(35.254326f, 17.796438f)
generalPath!!.lineTo(23.43554f, 17.796438f)
generalPath!!.lineTo(19.03008f, 27.9953f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(167, 171, 167, 255))
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
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(20.3125f, 16.875f)
generalPath!!.cubicTo(20.3125f, 16.875f, 8.166604f, 17.513063f, 4.15625f, 19.625f)
generalPath!!.cubicTo(3.3042102f, 20.073702f, 2.6682425f, 20.465607f, 2.6682425f, 21.067226f)
generalPath!!.cubicTo(2.6682425f, 22.447227f, 5.4923215f, 24.09375f, 8.84375f, 24.09375f)
generalPath!!.cubicTo(12.195179f, 24.09375f, 14.84375f, 22.59875f, 14.84375f, 21.21875f)
generalPath!!.cubicTo(14.84375f, 20.904411f, 14.663819f, 20.589697f, 14.40625f, 20.3125f)
generalPath!!.lineTo(23.03125f, 18.5f)
generalPath!!.lineTo(20.3125f, 16.875f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(136, 138, 133, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.41772148f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 16.66662f, startY = 16.12499f, endX = 35.888493f, endY = 57.124992f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.553211f, 14.482118f)
generalPath!!.lineTo(20.635908f, 17.275103f)
generalPath!!.cubicTo(20.635908f, 17.275105f, 14.191167f, 17.710136f, 9.386732f, 18.590467f)
generalPath!!.cubicTo(4.582297f, 19.4708f, 3.16662f, 20.423893f, 3.04162f, 21.603294f)
generalPath!!.lineTo(3.267739f, 38.194717f)
generalPath!!.cubicTo(3.5637007f, 39.3558f, 6.4977956f, 40.26786f, 9.496312f, 40.26786f)
generalPath!!.cubicTo(12.494828f, 40.26786f, 14.9977f, 38.6683f, 15.293662f, 37.507217f)
generalPath!!.lineTo(41.781643f, 37.632217f)
generalPath!!.cubicTo(43.24297f, 37.662792f, 43.513493f, 36.931126f, 43.513493f, 36.2796f)
generalPath!!.lineTo(43.513493f, 19.602476f)
generalPath!!.cubicTo(43.513493f, 18.890493f, 40.67186f, 17.738264f, 39.834183f, 17.64519f)
generalPath!!.lineTo(36.900776f, 17.70769f)
generalPath!!.lineTo(33.660465f, 14.482118f)
generalPath!!.lineTo(24.553211f, 14.482118f)
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
1.0829230546951294f, 0.0f, 0.0f, -3.0508909225463867f,
0.0f, 1.0829230546951294f, 0.0f, -1.568850040435791f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.714287f, 33.57143f)
generalPath!!.cubicTo(40.73054f, 37.20593f, 38.800884f, 40.571323f, 35.656f, 42.39328f)
generalPath!!.cubicTo(32.51111f, 44.21524f, 28.631748f, 44.21524f, 25.48686f, 42.39328f)
generalPath!!.cubicTo(22.341972f, 40.571323f, 20.412317f, 37.20593f, 20.428572f, 33.57143f)
generalPath!!.cubicTo(20.412317f, 29.93693f, 22.341972f, 26.571537f, 25.48686f, 24.74958f)
generalPath!!.cubicTo(28.631748f, 22.927622f, 32.51111f, 22.927622f, 35.656f, 24.74958f)
generalPath!!.cubicTo(38.800884f, 26.571537f, 40.73054f, 29.93693f, 40.714287f, 33.57143f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(46, 52, 54, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.6769839525222778f, 0.0f, 0.0f, 3.4884190559387207f,
0.0f, 1.6769839525222778f, 0.0f, -3.0505259037017822f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.875f, 22.5625f)
generalPath!!.cubicTo(19.88161f, 24.063011f, 19.096838f, 25.452421f, 17.817844f, 26.20462f)
generalPath!!.cubicTo(16.538849f, 26.95682f, 14.961151f, 26.95682f, 13.682157f, 26.20462f)
generalPath!!.cubicTo(12.403161f, 25.452421f, 11.61839f, 24.063011f, 11.625f, 22.5625f)
generalPath!!.cubicTo(11.61839f, 21.061989f, 12.403161f, 19.672579f, 13.682157f, 18.92038f)
generalPath!!.cubicTo(14.961151f, 18.16818f, 16.538849f, 18.16818f, 17.817844f, 18.92038f)
generalPath!!.cubicTo(19.096838f, 19.672579f, 19.88161f, 21.061989f, 19.875f, 22.5625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(122, 122, 122, 255), 1.0f to Color(0, 0, 0, 255), centerX = 16.875f, centerY = 23.687511f, radius = 4.625f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = LinearGradient(0.0f to Color(1, 1, 1, 255), 1.0f to Color(149, 149, 149, 255), startX = 12.835793f, startY = 18.849401f, endX = 18.422049f, endY = 27.072878f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.59630877f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.875f, 22.5625f)
generalPath!!.cubicTo(19.88161f, 24.063011f, 19.096838f, 25.452421f, 17.817844f, 26.20462f)
generalPath!!.cubicTo(16.538849f, 26.95682f, 14.961151f, 26.95682f, 13.682157f, 26.20462f)
generalPath!!.cubicTo(12.403161f, 25.452421f, 11.61839f, 24.063011f, 11.625f, 22.5625f)
generalPath!!.cubicTo(11.61839f, 21.061989f, 12.403161f, 19.672579f, 13.682157f, 18.92038f)
generalPath!!.cubicTo(14.961151f, 18.16818f, 16.538849f, 18.16818f, 17.817844f, 18.92038f)
generalPath!!.cubicTo(19.096838f, 19.672579f, 19.88161f, 21.061989f, 19.875f, 22.5625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.60759497f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.6769839525222778f, 0.0f, 0.0f, 5.499948024749756f,
0.0f, 1.6769839525222778f, 0.0f, -3.195173978805542f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.5f, 22.6875f)
generalPath!!.cubicTo(16.502804f, 23.336975f, 16.16987f, 23.93836f, 15.627267f, 24.263939f)
generalPath!!.cubicTo(15.084663f, 24.58952f, 14.415337f, 24.58952f, 13.872733f, 24.263939f)
generalPath!!.cubicTo(13.330129f, 23.93836f, 12.997195f, 23.336975f, 13.0f, 22.6875f)
generalPath!!.cubicTo(12.997195f, 22.038025f, 13.330129f, 21.43664f, 13.872733f, 21.111061f)
generalPath!!.cubicTo(14.415337f, 20.78548f, 15.084663f, 20.78548f, 15.627267f, 21.111061f)
generalPath!!.cubicTo(16.16987f, 21.43664f, 16.502804f, 22.038025f, 16.5f, 22.6875f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 135))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3734177f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.6732449531555176f, 0.0f, 0.0f, 3.333940029144287f,
0.0f, 1.6732449531555176f, 0.0f, -5.000546932220459f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.5f, 22.6875f)
generalPath!!.cubicTo(16.502804f, 23.336975f, 16.16987f, 23.93836f, 15.627267f, 24.263939f)
generalPath!!.cubicTo(15.084663f, 24.58952f, 14.415337f, 24.58952f, 13.872733f, 24.263939f)
generalPath!!.cubicTo(13.330129f, 23.93836f, 12.997195f, 23.336975f, 13.0f, 22.6875f)
generalPath!!.cubicTo(12.997195f, 22.038025f, 13.330129f, 21.43664f, 13.872733f, 21.111061f)
generalPath!!.cubicTo(14.415337f, 20.78548f, 15.084663f, 20.78548f, 15.627267f, 21.111061f)
generalPath!!.cubicTo(16.16987f, 21.43664f, 16.502804f, 22.038025f, 16.5f, 22.6875f)
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
0.41273000836372375f, 0.0f, 0.0f, 27.45136070251465f,
0.0f, 0.41273000836372375f, 0.0f, 28.981969833374023f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.5f, 22.6875f)
generalPath!!.cubicTo(16.502804f, 23.336975f, 16.16987f, 23.93836f, 15.627267f, 24.263939f)
generalPath!!.cubicTo(15.084663f, 24.58952f, 14.415337f, 24.58952f, 13.872733f, 24.263939f)
generalPath!!.cubicTo(13.330129f, 23.93836f, 12.997195f, 23.336975f, 13.0f, 22.6875f)
generalPath!!.cubicTo(12.997195f, 22.038025f, 13.330129f, 21.43664f, 13.872733f, 21.111061f)
generalPath!!.cubicTo(14.415337f, 20.78548f, 15.084663f, 20.78548f, 15.627267f, 21.111061f)
generalPath!!.cubicTo(16.16987f, 21.43664f, 16.502804f, 22.038025f, 16.5f, 22.6875f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), centerX = 14.75f, centerY = 22.687494f, radius = 1.75f, tileMode = TileMode.Clamp)
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
// _0_0_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(22.223356f, 15.574103f)
generalPath!!.lineTo(25.152798f, 13.452783f)
generalPath!!.lineTo(26.061935f, 11.028417f)
generalPath!!.lineTo(32.425896f, 11.028417f)
generalPath!!.lineTo(33.739094f, 13.95786f)
generalPath!!.lineTo(36.56752f, 16.382225f)
generalPath!!.lineTo(34.04214f, 5.4725776f)
generalPath!!.lineTo(24.142647f, 5.4725776f)
generalPath!!.lineTo(22.223356f, 15.574103f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(85, 87, 83, 255), 1.0f to Color(20, 21, 20, 255), startX = 29.39544f, startY = 9.412171f, endX = 29.39544f, endY = 12.84669f, tileMode = TileMode.Clamp)
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
// _0_0_19
shape = Outline.Rounded(roundRect = RoundRect(left = 24.478593826293945f, top = 4.6366777420043945f, right = 33.80710983276367f, bottom = 9.220470905303955f,radiusX = 3.0070888996124268f, radiusY = 3.0070908069610596f))
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(203, 203, 203, 255), startX = 28.261124f, startY = 6.6165237f, endX = 28.261124f, endY = 8.236649f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = LinearGradient(0.0f to Color(238, 238, 238, 255), 1.0f to Color(162, 162, 162, 255), startX = 29.142853f, startY = 9.607073f, endX = 29.142853f, endY = 4.8783493f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 24.478593826293945f, top = 4.6366777420043945f, right = 33.80710983276367f, bottom = 9.220470905303955f,radiusX = 3.0070888996124268f, radiusY = 3.0070908069610596f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.3594439923763275f, 0.0f, 0.0f, 7.698551177978516f,
0.0f, 0.5543439984321594f, 0.0f, 0.0730554386973381f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_20
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(14.857143f, 38.214287f)
generalPath!!.cubicTo(14.866873f, 39.110115f, 13.711798f, 39.939613f, 11.829294f, 40.388687f)
generalPath!!.cubicTo(9.946791f, 40.83776f, 7.624638f, 40.83776f, 5.7421346f, 40.388687f)
generalPath!!.cubicTo(3.859631f, 39.939613f, 2.7045557f, 39.110115f, 2.7142854f, 38.214287f)
generalPath!!.cubicTo(2.7045557f, 37.31846f, 3.859631f, 36.48896f, 5.7421346f, 36.039886f)
generalPath!!.cubicTo(7.624638f, 35.590813f, 9.946791f, 35.590813f, 11.829294f, 36.039886f)
generalPath!!.cubicTo(13.711798f, 36.48896f, 14.866873f, 37.31846f, 14.857143f, 38.214287f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(47, 48, 46, 255))
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
// _0_0_21
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(2.703427f, 21.250565f)
generalPath!!.cubicTo(2.5678754f, 23.036228f, 5.1472726f, 25.130768f, 8.898797f, 25.132036f)
generalPath!!.cubicTo(12.712821f, 25.133307f, 14.748227f, 22.948217f, 14.950257f, 21.028927f)
generalPath!!.lineTo(19.495943f, 19.311668f)
generalPath!!.lineTo(14.344166f, 20.32182f)
generalPath!!.cubicTo(14.445182f, 22.24111f, 12.642329f, 24.152279f, 8.903873f, 24.092825f)
generalPath!!.cubicTo(4.8175063f, 24.026842f, 3.328427f, 22.438065f, 2.703427f, 21.250565f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(196, 198, 195, 255))
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
// _0_0_22
shape = Outline.Rounded(roundRect = RoundRect(left = 17.562915802001953f, top = 18.317474365234375f, right = 21.69914722442627f, bottom = 22.707702159881592f,radiusX = 1.3908467292785645f, radiusY = 1.390847086906433f))
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(203, 203, 203, 255), startX = 19.240067f, startY = 20.213703f, endX = 19.240067f, endY = 21.765411f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(70, 70, 70, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 17.562915802001953f, top = 18.317474365234375f, right = 21.69914722442627f, bottom = 22.707702159881592f,radiusX = 1.3908467292785645f, radiusY = 1.390847086906433f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.43037972f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.389835000038147f, 0.0f, 0.0f, 4.452476978302002f,
0.0f, 0.389835000038147f, 0.0f, 12.386590003967285f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_23
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.5f, 22.6875f)
generalPath!!.cubicTo(16.502804f, 23.336975f, 16.16987f, 23.93836f, 15.627267f, 24.263939f)
generalPath!!.cubicTo(15.084663f, 24.58952f, 14.415337f, 24.58952f, 13.872733f, 24.263939f)
generalPath!!.cubicTo(13.330129f, 23.93836f, 12.997195f, 23.336975f, 13.0f, 22.6875f)
generalPath!!.cubicTo(12.997195f, 22.038025f, 13.330129f, 21.43664f, 13.872733f, 21.111061f)
generalPath!!.cubicTo(14.415337f, 20.78548f, 15.084663f, 20.78548f, 15.627267f, 21.111061f)
generalPath!!.cubicTo(16.16987f, 21.43664f, 16.502804f, 22.038025f, 16.5f, 22.6875f)
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
0.45611000061035156f, 0.0f, 0.0f, 20.302690505981445f,
0.0f, 0.45611000061035156f, 0.0f, 21.393259048461914f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_24
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.5f, 22.6875f)
generalPath!!.cubicTo(16.502804f, 23.336975f, 16.16987f, 23.93836f, 15.627267f, 24.263939f)
generalPath!!.cubicTo(15.084663f, 24.58952f, 14.415337f, 24.58952f, 13.872733f, 24.263939f)
generalPath!!.cubicTo(13.330129f, 23.93836f, 12.997195f, 23.336975f, 13.0f, 22.6875f)
generalPath!!.cubicTo(12.997195f, 22.038025f, 13.330129f, 21.43664f, 13.872733f, 21.111061f)
generalPath!!.cubicTo(14.415337f, 20.78548f, 15.084663f, 20.78548f, 15.627267f, 21.111061f)
generalPath!!.cubicTo(16.16987f, 21.43664f, 16.502804f, 22.038025f, 16.5f, 22.6875f)
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
1.0384620428085327f, 0.0f, 0.0f, -1.1875f,
0.0f, 1.6363639831542969f, 0.0f, -12.710229873657227f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_25
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.25f, 20.5625f)
generalPath!!.cubicTo(42.252605f, 20.808853f, 41.94345f, 21.036964f, 41.439606f, 21.16046f)
generalPath!!.cubicTo(40.93576f, 21.283955f, 40.31424f, 21.283955f, 39.810394f, 21.16046f)
generalPath!!.cubicTo(39.30655f, 21.036964f, 38.997395f, 20.808853f, 39.0f, 20.5625f)
generalPath!!.cubicTo(38.997395f, 20.316147f, 39.30655f, 20.088036f, 39.810394f, 19.96454f)
generalPath!!.cubicTo(40.31424f, 19.841045f, 40.93576f, 19.841045f, 41.439606f, 19.96454f)
generalPath!!.cubicTo(41.94345f, 20.088036f, 42.252605f, 20.316147f, 42.25f, 20.5625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(197, 197, 197, 135))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0384620428085327f, 0.0f, 0.0f, -1.125f,
0.0f, 1.6363639831542969f, 0.0f, -12.335229873657227f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_26
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.25f, 20.5625f)
generalPath!!.cubicTo(42.252605f, 20.808853f, 41.94345f, 21.036964f, 41.439606f, 21.16046f)
generalPath!!.cubicTo(40.93576f, 21.283955f, 40.31424f, 21.283955f, 39.810394f, 21.16046f)
generalPath!!.cubicTo(39.30655f, 21.036964f, 38.997395f, 20.808853f, 39.0f, 20.5625f)
generalPath!!.cubicTo(38.997395f, 20.316147f, 39.30655f, 20.088036f, 39.810394f, 19.96454f)
generalPath!!.cubicTo(40.31424f, 19.841045f, 40.93576f, 19.841045f, 41.439606f, 19.96454f)
generalPath!!.cubicTo(41.94345f, 20.088036f, 42.252605f, 20.316147f, 42.25f, 20.5625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(86, 86, 86, 255))
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
// _0_0_27
brush = SolidColor(Color(119, 119, 119, 255))
stroke = Stroke(width=1.0000007f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.271357f, 28.422173f)
generalPath!!.cubicTo(25.874096f, 25.054531f, 32.02702f, 24.22946f, 35.546276f, 27.380934f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6708861f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_28
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.21998f, 31.818048f)
generalPath!!.cubicTo(28.600014f, 31.818048f, 27.309626f, 33.185947f, 27.309626f, 34.86377f)
generalPath!!.cubicTo(27.309626f, 35.247616f, 27.556343f, 35.50693f, 27.68188f, 35.84517f)
generalPath!!.cubicTo(27.808561f, 35.862522f, 27.88906f, 35.980534f, 28.020294f, 35.980534f)
generalPath!!.cubicTo(29.636648f, 35.980534f, 30.930649f, 34.642735f, 30.930649f, 32.968655f)
generalPath!!.cubicTo(30.930649f, 32.575047f, 30.6565f, 32.29859f, 30.524553f, 31.953415f)
generalPath!!.cubicTo(30.409594f, 31.93922f, 30.338612f, 31.818048f, 30.21998f, 31.818048f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
            return 4.1366777420043945
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
            return 42.431236267089844
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
            return camera_photo(
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
                    return camera_photo(getOrigWidth().toInt(), getOrigHeight().toInt())
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

