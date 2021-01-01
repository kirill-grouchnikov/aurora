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
class printer private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
0.0231101606041193f, 0.0f, 0.0f, 44.685020446777344f,
0.0f, 0.022715330123901367f, 0.0f, 39.36098861694336f,
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
shape = Outline.Rounded(roundRect = RoundRect(left = 4.75f, top = 36.004188537597656f, right = 43.1875f, bottom = 42.49578285217285f,radiusX = 3.4230966567993164f, radiusY = 3.423095464706421f))
brush = Brush.linearGradient(0.0f to Color(142, 141, 135, 255), 0.27586207f to Color(203, 201, 193, 255), 1.0f to Color(142, 141, 135, 255), start = Offset(4.249999f, 39.249977f), end = Offset(43.687496f, 39.249977f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(89, 89, 89, 255))
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 4.75f, top = 36.004188537597656f, right = 43.1875f, bottom = 42.49578285217285f,radiusX = 3.4230966567993164f, radiusY = 3.423095464706421f))
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
generalPath!!.moveTo(7.130896f, 21.5f)
generalPath!!.lineTo(40.870613f, 21.5f)
generalPath!!.cubicTo(41.25566f, 21.5f, 41.747646f, 21.788155f, 42.05105f, 22.223919f)
generalPath!!.cubicTo(42.35445f, 22.659683f, 43.787518f, 24.83394f, 44.109447f, 25.297964f)
generalPath!!.cubicTo(44.431377f, 25.761988f, 44.502396f, 26.201853f, 44.502396f, 26.77405f)
generalPath!!.lineTo(44.502396f, 38.850952f)
generalPath!!.cubicTo(44.502396f, 39.764523f, 43.7704f, 40.5f, 42.861153f, 40.5f)
generalPath!!.lineTo(5.1403594f, 40.5f)
generalPath!!.cubicTo(4.2311096f, 40.5f, 3.4991138f, 39.764523f, 3.4991138f, 38.850952f)
generalPath!!.lineTo(3.4991138f, 26.77405f)
generalPath!!.cubicTo(3.4991138f, 26.280031f, 3.6002798f, 25.571642f, 3.9455202f, 25.120718f)
generalPath!!.cubicTo(4.3811665f, 24.551714f, 5.549866f, 22.57277f, 5.8581276f, 22.153118f)
generalPath!!.cubicTo(6.1663885f, 21.733467f, 6.732446f, 21.5f, 7.130896f, 21.5f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(220, 220, 218, 255), 1.0f to Color(186, 185, 183, 255), start = Offset(4.3184414f, 40.34375f), end = Offset(43.569324f, 40.34375f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(103, 103, 103, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.130896f, 21.5f)
generalPath!!.lineTo(40.870613f, 21.5f)
generalPath!!.cubicTo(41.25566f, 21.5f, 41.747646f, 21.788155f, 42.05105f, 22.223919f)
generalPath!!.cubicTo(42.35445f, 22.659683f, 43.787518f, 24.83394f, 44.109447f, 25.297964f)
generalPath!!.cubicTo(44.431377f, 25.761988f, 44.502396f, 26.201853f, 44.502396f, 26.77405f)
generalPath!!.lineTo(44.502396f, 38.850952f)
generalPath!!.cubicTo(44.502396f, 39.764523f, 43.7704f, 40.5f, 42.861153f, 40.5f)
generalPath!!.lineTo(5.1403594f, 40.5f)
generalPath!!.cubicTo(4.2311096f, 40.5f, 3.4991138f, 39.764523f, 3.4991138f, 38.850952f)
generalPath!!.lineTo(3.4991138f, 26.77405f)
generalPath!!.cubicTo(3.4991138f, 26.280031f, 3.6002798f, 25.571642f, 3.9455202f, 25.120718f)
generalPath!!.cubicTo(4.3811665f, 24.551714f, 5.549866f, 22.57277f, 5.8581276f, 22.153118f)
generalPath!!.cubicTo(6.1663885f, 21.733467f, 6.732446f, 21.5f, 7.130896f, 21.5f)
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
generalPath!!.moveTo(7.424621f, 21.975533f)
generalPath!!.cubicTo(6.921893f, 21.975533f, 6.3048778f, 22.053783f, 6.0546017f, 22.46703f)
generalPath!!.lineTo(4.1542525f, 25.604816f)
generalPath!!.cubicTo(3.8721285f, 26.070648f, 4.1881986f, 26.868141f, 5.087311f, 26.868141f)
generalPath!!.lineTo(42.730785f, 26.868141f)
generalPath!!.cubicTo(44.040733f, 26.868141f, 43.950535f, 25.858072f, 43.663845f, 25.42804f)
generalPath!!.lineTo(41.896076f, 22.776388f)
generalPath!!.cubicTo(41.575542f, 22.29559f, 41.459198f, 21.975533f, 40.65864f, 21.975533f)
generalPath!!.lineTo(7.424621f, 21.975533f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(251, 251, 251, 255))
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 32), 0.10344828f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(23.049486f, 23.760323f), end = Offset(23.198524f, 41.342876f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9469671f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.60536f, 22.445757f)
generalPath!!.lineTo(40.432674f, 22.445757f)
generalPath!!.cubicTo(40.79835f, 22.445757f, 41.26559f, 22.71863f, 41.553734f, 23.131283f)
generalPath!!.cubicTo(41.841873f, 23.543938f, 42.849964f, 25.160946f, 43.1557f, 25.60036f)
generalPath!!.cubicTo(43.461437f, 26.039776f, 43.59127f, 26.456312f, 43.59127f, 26.998163f)
generalPath!!.lineTo(43.59127f, 38.279263f)
generalPath!!.cubicTo(43.59127f, 39.144386f, 43.457546f, 39.528355f, 42.594032f, 39.528355f)
generalPath!!.lineTo(5.5322266f, 39.528355f)
generalPath!!.cubicTo(4.6687107f, 39.528355f, 4.4726048f, 39.144386f, 4.4726048f, 38.279263f)
generalPath!!.lineTo(4.4726048f, 26.998163f)
generalPath!!.cubicTo(4.4726048f, 26.530346f, 4.69345f, 25.859524f, 5.021325f, 25.432514f)
generalPath!!.cubicTo(5.435059f, 24.893684f, 6.103854f, 23.461634f, 6.3966103f, 23.064238f)
generalPath!!.cubicTo(6.6893663f, 22.666842f, 7.2269516f, 22.445757f, 7.60536f, 22.445757f)
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.672962f, 4.4999475f)
generalPath!!.lineTo(36.325115f, 4.4999475f)
generalPath!!.cubicTo(36.97588f, 4.4999475f, 37.49978f, 5.0100775f, 37.49978f, 5.6437373f)
generalPath!!.lineTo(37.49978f, 24.348175f)
generalPath!!.lineTo(10.498298f, 24.348175f)
generalPath!!.lineTo(10.498298f, 5.6437373f)
generalPath!!.cubicTo(10.498298f, 5.0100775f, 11.022197f, 4.4999475f, 11.672962f, 4.4999475f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(224, 224, 224, 255), 0.4054697f to Color(255, 255, 255, 255), 0.5344828f to Color(205, 205, 205, 255), 1.0f to Color(73, 73, 73, 255), start = Offset(23.74655f, 8.0536375f), end = Offset(23.494041f, 31.26232f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(137, 137, 137, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.672962f, 4.4999475f)
generalPath!!.lineTo(36.325115f, 4.4999475f)
generalPath!!.cubicTo(36.97588f, 4.4999475f, 37.49978f, 5.0100775f, 37.49978f, 5.6437373f)
generalPath!!.lineTo(37.49978f, 24.348175f)
generalPath!!.lineTo(10.498298f, 24.348175f)
generalPath!!.lineTo(10.498298f, 5.6437373f)
generalPath!!.cubicTo(10.498298f, 5.0100775f, 11.022197f, 4.4999475f, 11.672962f, 4.4999475f)
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
// _0_0_6
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 1.0f to Color(248, 248, 248, 255), start = Offset(24.756819f, 23.493353f), end = Offset(24.69277f, 18.540295f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 11.498513221740723f, top = 5.499246597290039f, right = 36.49908924102783f, bottom = 24.335620880126953f,radiusX = 0.35355344414711f, radiusY = 0.35355350375175476f))
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
// _0_0_7
shape = Outline.Rounded(roundRect = RoundRect(left = 6.875f, top = 27.375f, right = 40.625f, bottom = 32.5625f,radiusX = 3.4230966567993164f, radiusY = 3.4230966567993164f))
brush = Brush.linearGradient(0.0f to Color(247, 246, 245, 255), 1.0f to Color(247, 246, 245, 0), start = Offset(24.499998f, 29.28125f), end = Offset(24.499998f, 24.843773f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(102, 102, 102, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(23.0625f, 31.843746f), end = Offset(22.999996f, 28.281271f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.875f, top = 27.375f, right = 40.625f, bottom = 32.5625f,radiusX = 3.4230966567993164f, radiusY = 3.4230966567993164f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 2.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(10.871767f, 27.626486f)
generalPath!!.cubicTo(10.873821f, 28.085733f, 10.629993f, 28.510979f, 10.232612f, 28.741198f)
generalPath!!.cubicTo(9.83523f, 28.971416f, 9.345042f, 28.971416f, 8.94766f, 28.741198f)
generalPath!!.cubicTo(8.550279f, 28.510979f, 8.306451f, 28.085733f, 8.308505f, 27.626486f)
generalPath!!.cubicTo(8.306451f, 27.167238f, 8.550279f, 26.741993f, 8.94766f, 26.511774f)
generalPath!!.cubicTo(9.345042f, 26.281555f, 9.83523f, 26.281555f, 10.232612f, 26.511774f)
generalPath!!.cubicTo(10.629993f, 26.741993f, 10.873821f, 27.167238f, 10.871767f, 27.626486f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 253, 255), 0.5f to Color(187, 187, 185, 255), 1.0f to Color(0, 0, 0, 255), center = Offset(9.129549f, 26.925594f), radius = 2.1227016f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36571428f
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
generalPath!!.moveTo(11.743718f, 25.416054f)
generalPath!!.lineTo(37.306217f, 25.478554f)
generalPath!!.cubicTo(37.993717f, 25.480234f, 38.294037f, 25.107557f, 38.243717f, 24.478554f)
generalPath!!.lineTo(38.118717f, 22.916054f)
generalPath!!.lineTo(39.984837f, 22.916054f)
generalPath!!.cubicTo(40.797337f, 22.916054f, 40.975037f, 23.108616f, 41.172337f, 23.478554f)
generalPath!!.lineTo(41.672337f, 24.416054f)
generalPath!!.cubicTo(42.19913f, 25.403793f, 43.48351f, 26.390165f, 42.170494f, 26.390165f)
generalPath!!.cubicTo(37.667786f, 26.390165f, 13.993718f, 26.041054f, 11.743718f, 25.416054f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 60), 1.0f to Color(0, 0, 0, 0), start = Offset(27.978674f, 23.528128f), end = Offset(28.097887f, 28.880291f), tileMode = TileMode.Clamp)
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
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.99999994f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(43.488808f, 26.5f)
generalPath!!.lineTo(4.5111804f, 26.5f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.43575415f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 2.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_11
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
// _0_0_11_0
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 7.0f, right = 33.0f, bottom = 8.0f))
brush = SolidColor(Color(0, 0, 0, 75))
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
// _0_0_11_1
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 9.0f, right = 33.0f, bottom = 10.0f))
brush = SolidColor(Color(0, 0, 0, 75))
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
// _0_0_11_2
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 11.0f, right = 33.0f, bottom = 12.0f))
brush = SolidColor(Color(0, 0, 0, 75))
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
// _0_0_11_3
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 13.0f, right = 25.0f, bottom = 14.0f))
brush = SolidColor(Color(0, 0, 0, 75))
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
// _0_0_11_4
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 17.0f, right = 33.0f, bottom = 18.0f))
brush = SolidColor(Color(0, 0, 0, 75))
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
// _0_0_11_5
shape = Outline.Rectangle(rect = Rect(left = 14.0f, top = 19.0f, right = 33.0f, bottom = 20.0f))
brush = SolidColor(Color(0, 0, 0, 75))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
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
            return 0.6681960821151733
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.9999473094940186
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 46.92365264892578
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 42.824180603027344
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
            return printer(
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
                    return printer(getOrigWidth().toInt(), getOrigHeight().toInt())
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

