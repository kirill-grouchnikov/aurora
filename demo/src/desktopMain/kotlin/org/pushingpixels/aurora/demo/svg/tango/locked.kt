package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
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
class locked private constructor(var _width: Dp, var _height: Dp) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)
    private var colorFilter: ((Color) -> Color)? = null

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
alpha *= 0.47368422f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.4402250051498413f, 0.0f, 0.0f, -8.581572532653809f,
0.0f, 0.41901400685310364f, 0.0f, 29.820430755615234f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(38.142857f, 30.857143f)
generalPath!!.cubicTo(38.16781f, 34.491642f, 35.205383f, 37.857037f, 30.377314f, 39.678993f)
generalPath!!.cubicTo(25.549248f, 41.500954f, 19.593609f, 41.500954f, 14.765542f, 39.678993f)
generalPath!!.cubicTo(9.937474f, 37.857037f, 6.9750466f, 34.491642f, 7.0f, 30.857143f)
generalPath!!.cubicTo(6.9750466f, 27.222643f, 9.937474f, 23.85725f, 14.765542f, 22.035294f)
generalPath!!.cubicTo(19.593609f, 20.213335f, 25.549248f, 20.213335f, 30.377314f, 22.035294f)
generalPath!!.cubicTo(35.205383f, 23.85725f, 38.16781f, 27.222643f, 38.142857f, 30.857143f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), center = Offset(0.06779286f, 0.060368784f), radius = 0.046768494f, tileMode = TileMode.Clamp)
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
shape = Outline.Rounded(roundRect = RoundRect(left = 6.5f, top = 17.5f, right = 41.493770599365234f, bottom = 44.50927734375f,radiusX = 4.469950199127197f, radiusY = 4.514106750488281f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(234, 210, 0, 182)) ?: Color(234, 210, 0, 182)), 0.21f to (colorFilter?.invoke(Color(255, 236, 65, 255)) ?: Color(255, 236, 65, 255)), 0.84f to (colorFilter?.invoke(Color(226, 204, 0, 255)) ?: Color(226, 204, 0, 255)), 1.0f to (colorFilter?.invoke(Color(195, 175, 0, 255)) ?: Color(195, 175, 0, 255)), start = Offset(-0.041183054f, -0.21459797f), end = Offset(-0.2506321f, -0.21459797f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(125, 100, 0, 255)) ?: Color(125, 100, 0, 255)), 1.0f to (colorFilter?.invoke(Color(190, 151, 0, 255)) ?: Color(190, 151, 0, 255)), start = Offset(-0.19098246f, -0.27501538f), end = Offset(-0.08515013f, -0.27604917f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999946f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.5f, top = 17.5f, right = 41.493770599365234f, bottom = 44.50927734375f,radiusX = 4.469950199127197f, radiusY = 4.514106750488281f))
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
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 153)) ?: Color(255, 255, 255, 153)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 76)) ?: Color(255, 255, 255, 76)), start = Offset(-0.10672865f, -0.26832587f), end = Offset(-0.20923455f, -0.26851794f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.5f, top = 18.5f, right = 40.50090408325195f, bottom = 43.50688171386719f,radiusX = 2.577022075653076f, radiusY = 2.577022075653076f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0662909746170044f, 0.0f, 0.0f, -1.5909899473190308f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_0
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 23.0f, right = 40.0f, bottom = 23.972270965576172f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.18430589f, 0.039679695f), end = Offset(0.14596912f, 0.03595114f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_1
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 25.0f, right = 40.0f, bottom = 25.972270965576172f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.14805253f, 0.031874616f), end = Offset(0.11946478f, 0.029423313f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_2
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 27.0f, right = 40.0f, bottom = 27.972270965576172f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.12371709f, 0.026635375f), end = Offset(0.10110639f, 0.024901776f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_3
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 29.0f, right = 40.0f, bottom = 29.972270965576172f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.10625237f, 0.02287535f), end = Offset(0.08763879f, 0.021584801f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_4
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 31.0f, right = 40.0f, bottom = 31.972270965576172f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.09310855f, 0.020045582f), end = Offset(0.07733727f, 0.019047612f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_5
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 33.0f, right = 40.0f, bottom = 33.97227096557617f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.082858615f, 0.017838847f), end = Offset(0.0692028f, 0.017044151f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_6
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 35.0f, right = 40.0f, bottom = 35.97227096557617f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.074641645f, 0.016069794f), end = Offset(0.062616676f, 0.015422037f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_7
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 37.0f, right = 40.0f, bottom = 37.97227096557617f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.067907356f, 0.01461995f), end = Offset(0.057175238f, 0.014081849f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_8
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 39.0f, right = 40.0f, bottom = 39.97227096557617f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.06228767f, 0.013410075f), end = Offset(0.05260391f, 0.012955965f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_9
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 41.0f, right = 40.0f, bottom = 41.97227096557617f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(137, 109, 0, 255)) ?: Color(137, 109, 0, 255)), 1.0f to (colorFilter?.invoke(Color(161, 128, 0, 68)) ?: Color(161, 128, 0, 68)), start = Offset(0.057527f, 0.012385137f), end = Offset(0.048709452f, 0.011996787f), tileMode = TileMode.Clamp)
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
// _0_0_4
shape = Outline.Rounded(roundRect = RoundRect(left = 7.0f, top = 18.0f, right = 41.0f, bottom = 22.0f,radiusX = 3.429290771484375f, radiusY = 3.3221452236175537f))
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 236, 65, 255)) ?: Color(255, 236, 65, 255)), 1.0f to (colorFilter?.invoke(Color(195, 175, 0, 255)) ?: Color(195, 175, 0, 255)), start = Offset(1.1306819f, 0.9545455f), end = Offset(1.375f, 0.9444445f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4065934f
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
generalPath!!.moveTo(39.022896f, 21.954401f)
generalPath!!.lineTo(8.978923f, 21.954401f)
generalPath!!.cubicTo(7.4608207f, 21.962502f, 6.998228f, 22.594587f, 6.982603f, 24.335226f)
generalPath!!.lineTo(6.982603f, 35.18088f)
generalPath!!.cubicTo(12.330783f, 39.93756f, 24.775284f, 30.210882f, 40.9826f, 35.18088f)
generalPath!!.lineTo(40.9826f, 24.314186f)
generalPath!!.cubicTo(40.969994f, 22.533916f, 40.441753f, 21.96814f, 39.022896f, 21.954401f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 0)) ?: Color(255, 255, 255, 0)), center = Offset(-0.06922903f, -0.081265554f), radius = 0.041875854f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -2.0861629934643133E-7f,
0.0f, 1.0f, 0.0f, -0.987405002117157f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
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
// _0_0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(10.5f, 20.234846f)
generalPath!!.lineTo(10.5f, 13.0f)
generalPath!!.cubicTo(10.5f, 5.1298666f, 15.897609f, 1.3910066f, 24.020027f, 1.4892921f)
generalPath!!.cubicTo(32.18664f, 1.5875777f, 37.5f, 5.037278f, 37.5f, 13.0f)
generalPath!!.lineTo(37.5f, 20.234846f)
generalPath!!.cubicTo(37.416054f, 21.93843f, 32.536613f, 21.93215f, 32.5f, 20.234846f)
generalPath!!.lineTo(32.5f, 15.0f)
generalPath!!.cubicTo(32.5f, 13.0f, 33.138264f, 6.528147f, 24.077242f, 6.528147f)
generalPath!!.cubicTo(14.953718f, 6.528147f, 15.489989f, 13.039885f, 15.52268f, 14.992026f)
generalPath!!.lineTo(15.52268f, 20.268524f)
generalPath!!.cubicTo(15.3125f, 21.859846f, 10.5f, 21.797346f, 10.5f, 20.234846f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(202, 208, 198, 255)) ?: Color(202, 208, 198, 255)), 0.5f to (colorFilter?.invoke(Color(234, 236, 233, 255)) ?: Color(234, 236, 233, 255)), 1.0f to (colorFilter?.invoke(Color(197, 203, 192, 255)) ?: Color(197, 203, 192, 255)), start = Offset(-2.3090913f, -0.6316837f), end = Offset(-0.8327875f, -0.5350346f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(111, 113, 109, 255)) ?: Color(111, 113, 109, 255)), 1.0f to (colorFilter?.invoke(Color(158, 160, 156, 255)) ?: Color(158, 160, 156, 255)), start = Offset(-1.0928695f, -0.5486073f), end = Offset(-1.5232425f, -0.56795377f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(10.5f, 20.234846f)
generalPath!!.lineTo(10.5f, 13.0f)
generalPath!!.cubicTo(10.5f, 5.1298666f, 15.897609f, 1.3910066f, 24.020027f, 1.4892921f)
generalPath!!.cubicTo(32.18664f, 1.5875777f, 37.5f, 5.037278f, 37.5f, 13.0f)
generalPath!!.lineTo(37.5f, 20.234846f)
generalPath!!.cubicTo(37.416054f, 21.93843f, 32.536613f, 21.93215f, 32.5f, 20.234846f)
generalPath!!.lineTo(32.5f, 15.0f)
generalPath!!.cubicTo(32.5f, 13.0f, 33.138264f, 6.528147f, 24.077242f, 6.528147f)
generalPath!!.cubicTo(14.953718f, 6.528147f, 15.489989f, 13.039885f, 15.52268f, 14.992026f)
generalPath!!.lineTo(15.52268f, 20.268524f)
generalPath!!.cubicTo(15.3125f, 21.859846f, 10.5f, 21.797346f, 10.5f, 20.234846f)
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
// _0_0_6_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.496849f, 13.160018f)
generalPath!!.cubicTo(11.745273f, 8.76218f, 12.91305f, 4.7080564f, 18.456118f, 3.1987817f)
generalPath!!.cubicTo(20.036661f, 5.0232134f, 13.557817f, 5.119544f, 13.458448f, 11.481749f)
generalPath!!.cubicTo(13.458448f, 11.481749f, 13.480108f, 19.46426f, 13.480108f, 19.46426f)
generalPath!!.cubicTo(13.245273f, 20.052649f, 11.559349f, 20.070826f, 11.496849f, 19.414576f)
generalPath!!.lineTo(11.496849f, 13.160018f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 126)) ?: Color(255, 255, 255, 126)), center = Offset(0.105315454f, -0.1690088f), radius = 0.06132523f, tileMode = TileMode.Clamp)
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
// _0_0_6_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.500366f, 9.7120695f)
generalPath!!.lineTo(35.500366f, 9.7120695f)
generalPath!!.lineTo(35.500366f, 19.347483f)
generalPath!!.cubicTo(35.437866f, 20.472483f, 33.469116f, 19.878733f, 33.500366f, 19.347483f)
generalPath!!.lineTo(33.500366f, 9.7120695f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 0)) ?: Color(255, 255, 255, 0)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), center = Offset(-0.12995605f, 0.16928573f), radius = 0.027430434f, tileMode = TileMode.Clamp)
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
// _0_0_7
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 0)) ?: Color(255, 255, 255, 0)), start = Offset(0.5034336f, 0.47069794f), end = Offset(0.5988141f, 0.46860376f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.5f, top = 18.634618759155273f, right = 40.5f, bottom = 21.4903883934021f,radiusX = 2.400388479232788f, radiusY = 2.193695306777954f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
            return 1.4640640020370483
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.0
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 44.92459487915039
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.20991134643555
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
            return locked(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return locked(getOrigWidth().dp, getOrigHeight().dp)
                }
            }
        }

        
    }

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = width
        _height = height
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        this.colorFilter = colorFilter
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            clipRect {
                // Use the original icon bounding box and the current icon dimension to compute
                // the scaling factor
                val fullOrigWidth = getOrigX() + getOrigWidth()
                val fullOrigHeight = getOrigY() + getOrigHeight()
                val coef1 = _width.toPx() / fullOrigWidth
                val coef2 = _height.toPx() / fullOrigHeight
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
}

