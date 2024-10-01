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
class locked : Painter() {
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
1.4402250051498413f, 0.0f, 0.0f, 0.0f,
0.0f, 0.41901400685310364f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-8.581572532653809f, 29.820430755615234f, 0.0f, 1.0f)
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
shape = Outline.Rounded(roundRect = RoundRect(left = 6.5f, top = 17.5f, right = 41.493770599365234f, bottom = 44.50927734375f,radiusX = 4.469950199127197f, radiusY = 4.514106750488281f))
brush = Brush.linearGradient(0.0f to Color(234, 210, 0, 182), 0.21f to Color(255, 236, 65, 255), 0.84f to Color(226, 204, 0, 255), 1.0f to Color(195, 175, 0, 255), start = Offset(6.72682f, 29.942535f), end = Offset(40.938126f, 29.942535f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(125, 100, 0, 255), 1.0f to Color(190, 151, 0, 255), start = Offset(31.630468f, 41.56108f), end = Offset(8.671364f, 24.124895f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999946f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.5f, top = 17.5f, right = 41.493770599365234f, bottom = 44.50927734375f,radiusX = 4.469950199127197f, radiusY = 4.514106750488281f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 153), 1.0f to Color(255, 255, 255, 76), start = Offset(10.934005f, 23.257816f), end = Offset(30.88445f, 35.448612f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.5f, top = 18.5f, right = 40.50090408325195f, bottom = 43.50688171386719f,radiusX = 2.577022075653076f, radiusY = 2.577022075653076f))
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
1.0662909746170044f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.5909899473190308f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_0
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 23.0f, right = 40.0f, bottom = 23.972270965576172f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 22.26737f), end = Offset(28.706724f, 23.332083f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_1
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 25.0f, right = 40.0f, bottom = 25.972270965576172f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 24.267368f), end = Offset(28.706724f, 25.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_2
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 27.0f, right = 40.0f, bottom = 27.972270965576172f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 26.267368f), end = Offset(28.706724f, 27.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_3
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 29.0f, right = 40.0f, bottom = 29.972270965576172f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 28.267368f), end = Offset(28.706724f, 29.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_4
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 31.0f, right = 40.0f, bottom = 31.972270965576172f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 30.267368f), end = Offset(28.706724f, 31.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_5
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 33.0f, right = 40.0f, bottom = 33.97227096557617f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 32.26737f), end = Offset(28.706724f, 33.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_6
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 35.0f, right = 40.0f, bottom = 35.97227096557617f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 34.26737f), end = Offset(28.706724f, 35.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_7
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 37.0f, right = 40.0f, bottom = 37.97227096557617f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 36.26737f), end = Offset(28.706724f, 37.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_8
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 39.0f, right = 40.0f, bottom = 39.97227096557617f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 38.26737f), end = Offset(28.706724f, 39.33208f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_9
shape = Outline.Rectangle(rect = Rect(left = 8.0f, top = 41.0f, right = 40.0f, bottom = 41.97227096557617f))
brush = Brush.linearGradient(0.0f to Color(137, 109, 0, 255), 1.0f to Color(161, 128, 0, 68), start = Offset(28.6923f, 40.26737f), end = Offset(28.706724f, 41.33208f), tileMode = TileMode.Clamp)
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
shape = Outline.Rounded(roundRect = RoundRect(left = 7.0f, top = 18.0f, right = 41.0f, bottom = 22.0f,radiusX = 3.429290771484375f, radiusY = 3.3221452236175537f))
brush = Brush.linearGradient(0.0f to Color(255, 236, 65, 255), 1.0f to Color(195, 175, 0, 255), start = Offset(24.875f, 22.0f), end = Offset(24.75f, 18.0f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4065934f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.022896f, 21.954401f)
    lineTo(8.978923f, 21.954401f)
    cubicTo(7.4608207f, 21.962502f, 6.998228f, 22.594587f, 6.982603f, 24.335226f)
    lineTo(6.982603f, 35.18088f)
    cubicTo(12.330783f, 39.93756f, 24.775284f, 30.210882f, 40.9826f, 35.18088f)
    lineTo(40.9826f, 24.314186f)
    cubicTo(40.969994f, 22.533916f, 40.441753f, 21.96814f, 39.022896f, 21.954401f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(12.845678f, 21.827225f), radius = 43.02851f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
-2.0861629934643133E-7f, -0.987405002117157f, 0.0f, 1.0f)
))}){
// _0_0_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.5f, 20.234846f)
    lineTo(10.5f, 13.0f)
    cubicTo(10.5f, 5.1298666f, 15.897609f, 1.3910066f, 24.020027f, 1.4892921f)
    cubicTo(32.18664f, 1.5875777f, 37.5f, 5.037278f, 37.5f, 13.0f)
    lineTo(37.5f, 20.234846f)
    cubicTo(37.416054f, 21.93843f, 32.536613f, 21.93215f, 32.5f, 20.234846f)
    lineTo(32.5f, 15.0f)
    cubicTo(32.5f, 13.0f, 33.138264f, 6.528147f, 24.077242f, 6.528147f)
    cubicTo(14.953718f, 6.528147f, 15.489989f, 13.039885f, 15.52268f, 14.992026f)
    lineTo(15.52268f, 20.268524f)
    cubicTo(15.3125f, 21.859846f, 10.5f, 21.797346f, 10.5f, 20.234846f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(202, 208, 198, 255), 0.5f to Color(234, 236, 233, 255), 1.0f to Color(197, 203, 192, 255), start = Offset(10.650842f, 0.9874052f), end = Offset(27.192274f, 15.543732f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(111, 113, 109, 255), 1.0f to Color(158, 160, 156, 255), start = Offset(19.250618f, 7.737298f), end = Offset(16.198252f, 4.1133757f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.5f, 20.234846f)
    lineTo(10.5f, 13.0f)
    cubicTo(10.5f, 5.1298666f, 15.897609f, 1.3910066f, 24.020027f, 1.4892921f)
    cubicTo(32.18664f, 1.5875777f, 37.5f, 5.037278f, 37.5f, 13.0f)
    lineTo(37.5f, 20.234846f)
    cubicTo(37.416054f, 21.93843f, 32.536613f, 21.93215f, 32.5f, 20.234846f)
    lineTo(32.5f, 15.0f)
    cubicTo(32.5f, 13.0f, 33.138264f, 6.528147f, 24.077242f, 6.528147f)
    cubicTo(14.953718f, 6.528147f, 15.489989f, 13.039885f, 15.52268f, 14.992026f)
    lineTo(15.52268f, 20.268524f)
    cubicTo(15.3125f, 21.859846f, 10.5f, 21.797346f, 10.5f, 20.234846f)
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
// _0_0_6_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.496849f, 13.160018f)
    cubicTo(11.745273f, 8.76218f, 12.91305f, 4.7080564f, 18.456118f, 3.1987817f)
    cubicTo(20.036661f, 5.0232134f, 13.557817f, 5.119544f, 13.458448f, 11.481749f)
    cubicTo(13.458448f, 11.481749f, 13.480108f, 19.46426f, 13.480108f, 19.46426f)
    cubicTo(13.245273f, 20.052649f, 11.559349f, 20.070826f, 11.496849f, 19.414576f)
    lineTo(11.496849f, 13.160018f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 126), center = Offset(14.45829f, 4.4200974f), radius = 13.73973f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.500366f, 9.7120695f)
    lineTo(35.500366f, 9.7120695f)
    lineTo(35.500366f, 19.347483f)
    cubicTo(35.437866f, 20.472483f, 33.469116f, 19.878733f, 33.500366f, 19.347483f)
    lineTo(33.500366f, 9.7120695f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 0), 1.0f to Color(255, 255, 255, 255), center = Offset(35.00354f, 9.712036f), radius = 14.971741f, tileMode = TileMode.Clamp)
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
// _0_0_7
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(21.94151f, 22.490732f), end = Offset(21.94151f, 19.146358f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.5f, top = 18.634618759155273f, right = 40.5f, bottom = 21.4903883934021f,radiusX = 2.400388479232788f, radiusY = 2.193695306777954f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
            return 1.4997771978378296
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
            return 44.853172302246094
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.0190544128418
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

