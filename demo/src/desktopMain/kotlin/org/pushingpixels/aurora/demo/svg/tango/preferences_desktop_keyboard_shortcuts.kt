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
class preferences_desktop_keyboard_shortcuts : Painter() {
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
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.000005006790161f, -2.993760108947754f, 0.0f, 1.0f)
))}){
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 0.3888889f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5241569876670837f, 0.0f, 0.0f, 0.0f,
0.0f, 0.27388399839401245f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.4482979774475098f, 12.695910453796387f, 0.0f, 1.0f)
))}){
// _0_0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.625f, 39.8125f)
    cubicTo(41.652843f, 42.29842f, 38.34729f, 44.600277f, 32.960007f, 45.84646f)
    cubicTo(27.572727f, 47.09264f, 20.927273f, 47.09264f, 15.539991f, 45.84646f)
    cubicTo(10.15271f, 44.600277f, 6.8471565f, 42.29842f, 6.875f, 39.8125f)
    cubicTo(6.8471565f, 37.32658f, 10.15271f, 35.024723f, 15.539991f, 33.77854f)
    cubicTo(20.927273f, 32.53236f, 27.572727f, 32.53236f, 32.960007f, 33.77854f)
    cubicTo(38.34729f, 35.024723f, 41.652843f, 37.32658f, 41.625f, 39.8125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.25f, 39.812515f), radius = 17.375f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 5.495044231414795f, top = 4.505629539489746f, right = 25.48451566696167f, bottom = 25.44937038421631f,radiusX = 4.898674011230469f, radiusY = 5.132530212402344f))
brush = Brush.radialGradient(0.0f to Color(248, 248, 247, 255), 1.0f to Color(186, 189, 182, 255), center = Offset(15.489777f, 5.8524966f), radius = 23.122776f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=0.9999993f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.495044231414795f, top = 4.505629539489746f, right = 25.48451566696167f, bottom = 25.44937038421631f,radiusX = 4.898674011230469f, radiusY = 5.132530212402344f))
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
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0_2
shape = Outline.Rounded(roundRect = RoundRect(left = -22.537153244018555f, top = 7.234417915344238f, right = -8.462854385375977f, bottom = 20.561720848083496f,radiusX = 3.4490861892700195f, radiusY = 3.2660250663757324f))
brush = Brush.radialGradient(0.0f to Color(242, 244, 241, 255), 1.0f to Color(211, 215, 207, 255), center = Offset(-15.499998f, 17.550545f), radius = 15.777922f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(238, 238, 236, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(-15.500005f, 10.017332f), end = Offset(-15.500005f, 19.83331f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = -22.537153244018555f, top = 7.234417915344238f, right = -8.462854385375977f, bottom = 20.561720848083496f,radiusX = 3.4490861892700195f, radiusY = 3.2660250663757324f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.489673137664795f, top = 5.500080108642578f, right = 24.510337352752686f, bottom = 24.496803283691406f,radiusX = 3.1135053634643555f, radiusY = 3.1135053634643555f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.975835f, 22.771961f)
    cubicTo(6.920206f, 23.604708f, 7.4649177f, 24.090702f, 8.383736f, 24.022097f)
    lineTo(9.265165f, 20.851969f)
    lineTo(8.619088f, 20.469515f)
    lineTo(6.975835f, 22.771961f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.054497f, 22.998959f)
    cubicTo(23.867056f, 23.942188f, 23.167665f, 24.052534f, 22.337234f, 24.05022f)
    lineTo(21.522097f, 20.96848f)
    lineTo(22.190271f, 20.563929f)
    lineTo(24.054497f, 22.998959f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.029566f, 7.081846f)
    cubicTo(23.977024f, 6.4997454f, 23.650196f, 6.0533137f, 23.015554f, 6.0f)
    lineTo(22.15468f, 7.1442795f)
    lineTo(22.756563f, 7.778922f)
    lineTo(24.029566f, 7.081846f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(22.481161f, 6.8019323f), end = Offset(23.081062f, 7.5591f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.903371f, 6.982795f)
    cubicTo(7.0443015f, 6.4669857f, 7.4488535f, 6.0312166f, 8.017205f, 6.0f)
    lineTo(8.779029f, 7.187702f)
    lineTo(8.219797f, 7.822345f)
    lineTo(6.903371f, 6.982795f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(7.7836266f, 7.406542f), end = Offset(8.281891f, 6.80426f), tileMode = TileMode.Clamp)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.384225994348526f, 0.0f, 0.0f, 0.0f,
0.0f, 0.384225994348526f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
25.257190704345703f, 17.865219116210938f, 0.0f, 1.0f)
))}){
// _0_0_1
brush = SolidColor(Color(32, 74, 135, 255))
stroke = Stroke(width=5.20527f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.724503f, 19.538952f)
    cubicTo(44.746315f, 24.416483f, 42.1567f, 28.932869f, 37.936234f, 31.377954f)
    cubicTo(33.715767f, 33.823036f, 28.50963f, 33.823036f, 24.289162f, 31.377954f)
    cubicTo(20.068693f, 28.932869f, 17.47908f, 24.416483f, 17.500893f, 19.538952f)
    cubicTo(17.47908f, 14.661421f, 20.068693f, 10.145034f, 24.289162f, 7.6999497f)
    cubicTo(28.50963f, 5.2548656f, 33.715767f, 5.2548656f, 37.936234f, 7.6999497f)
    cubicTo(42.1567f, 10.145034f, 44.746315f, 14.661421f, 44.724503f, 19.538952f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
20.004959106445312f, -2.9431300163269043f, 0.0f, 1.0f)
))}){
// _0_0_2
alphaStack.add(0, alpha)
alpha *= 0.3888889f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5241569876670837f, 0.0f, 0.0f, 0.0f,
0.0f, 0.27388399839401245f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.4482979774475098f, 12.695910453796387f, 0.0f, 1.0f)
))}){
// _0_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.625f, 39.8125f)
    cubicTo(41.652843f, 42.29842f, 38.34729f, 44.600277f, 32.960007f, 45.84646f)
    cubicTo(27.572727f, 47.09264f, 20.927273f, 47.09264f, 15.539991f, 45.84646f)
    cubicTo(10.15271f, 44.600277f, 6.8471565f, 42.29842f, 6.875f, 39.8125f)
    cubicTo(6.8471565f, 37.32658f, 10.15271f, 35.024723f, 15.539991f, 33.77854f)
    cubicTo(20.927273f, 32.53236f, 27.572727f, 32.53236f, 32.960007f, 33.77854f)
    cubicTo(38.34729f, 35.024723f, 41.652843f, 37.32658f, 41.625f, 39.8125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.25f, 39.812515f), radius = 17.375f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_1
shape = Outline.Rounded(roundRect = RoundRect(left = 5.495044231414795f, top = 4.505629539489746f, right = 25.48451566696167f, bottom = 25.44937038421631f,radiusX = 4.898674011230469f, radiusY = 5.132530212402344f))
brush = SolidColor(Color(186, 189, 182, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=0.9999993f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.495044231414795f, top = 4.505629539489746f, right = 25.48451566696167f, bottom = 25.44937038421631f,radiusX = 4.898674011230469f, radiusY = 5.132530212402344f))
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
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2_2
shape = Outline.Rounded(roundRect = RoundRect(left = -22.537153244018555f, top = 7.234417915344238f, right = -8.462854385375977f, bottom = 20.561720848083496f,radiusX = 3.4490861892700195f, radiusY = 3.2660250663757324f))
brush = Brush.radialGradient(0.0f to Color(242, 244, 241, 255), 1.0f to Color(211, 215, 207, 255), center = Offset(-15.499998f, 17.550545f), radius = 15.777922f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(238, 238, 236, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(-15.500005f, 10.017332f), end = Offset(-15.500005f, 19.83331f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = -22.537153244018555f, top = 7.234417915344238f, right = -8.462854385375977f, bottom = 20.561720848083496f,radiusX = 3.4490861892700195f, radiusY = 3.2660250663757324f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.489673137664795f, top = 5.500080108642578f, right = 24.510337352752686f, bottom = 24.496803283691406f,radiusX = 3.1135053634643555f, radiusY = 3.1135053634643555f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.975835f, 22.771961f)
    cubicTo(6.920206f, 23.604708f, 7.4649177f, 24.090702f, 8.383736f, 24.022097f)
    lineTo(9.265165f, 20.851969f)
    lineTo(8.619088f, 20.469515f)
    lineTo(6.975835f, 22.771961f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.054497f, 22.998959f)
    cubicTo(23.867056f, 23.942188f, 23.167665f, 24.052534f, 22.337234f, 24.05022f)
    lineTo(21.522097f, 20.96848f)
    lineTo(22.190271f, 20.563929f)
    lineTo(24.054497f, 22.998959f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.029566f, 7.081846f)
    cubicTo(23.977024f, 6.4997454f, 23.650196f, 6.0533137f, 23.015554f, 6.0f)
    lineTo(22.15468f, 7.1442795f)
    lineTo(22.756563f, 7.778922f)
    lineTo(24.029566f, 7.081846f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(22.481161f, 6.8019323f), end = Offset(23.081062f, 7.5591f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(6.903371f, 6.982795f)
    cubicTo(7.0443015f, 6.4669857f, 7.4488535f, 6.0312166f, 8.017205f, 6.0f)
    lineTo(8.779029f, 7.187702f)
    lineTo(8.219797f, 7.822345f)
    lineTo(6.903371f, 6.982795f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(7.7836266f, 7.406542f), end = Offset(8.281891f, 6.80426f), tileMode = TileMode.Clamp)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.005411982536316f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-2.9845149517059326f, 19.97269058227539f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 0.3888889f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5241569876670837f, 0.0f, 0.0f, 0.0f,
0.0f, 0.27388399839401245f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.4482979774475098f, 12.695910453796387f, 0.0f, 1.0f)
))}){
// _0_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.625f, 39.8125f)
    cubicTo(41.652843f, 42.29842f, 38.34729f, 44.600277f, 32.960007f, 45.84646f)
    cubicTo(27.572727f, 47.09264f, 20.927273f, 47.09264f, 15.539991f, 45.84646f)
    cubicTo(10.15271f, 44.600277f, 6.8471565f, 42.29842f, 6.875f, 39.8125f)
    cubicTo(6.8471565f, 37.32658f, 10.15271f, 35.024723f, 15.539991f, 33.77854f)
    cubicTo(20.927273f, 32.53236f, 27.572727f, 32.53236f, 32.960007f, 33.77854f)
    cubicTo(38.34729f, 35.024723f, 41.652843f, 37.32658f, 41.625f, 39.8125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.25f, 39.812515f), radius = 17.375f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_1
shape = Outline.Rounded(roundRect = RoundRect(left = 6.365069389343262f, top = 4.505629539489746f, right = 39.52490520477295f, bottom = 25.44937038421631f,radiusX = 5.132530689239502f, radiusY = 5.104902267456055f))
brush = SolidColor(Color(186, 189, 182, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=0.99730414f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.365069389343262f, top = 4.505629539489746f, right = 39.52490520477295f, bottom = 25.44937038421631f,radiusX = 5.132530689239502f, radiusY = 5.104902267456055f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_2
shape = Outline.Rounded(roundRect = RoundRect(left = 9.190199851989746f, top = 7.44936990737915f, right = 36.48972988128662f, bottom = 20.500000476837158f,radiusX = 2.6410248279571533f, radiusY = 2.6268086433410645f))
brush = Brush.radialGradient(0.0f to Color(242, 244, 241, 255), 1.0f to Color(211, 215, 207, 255), center = Offset(22.839966f, 20.127712f), radius = 14.35202f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(238, 238, 236, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(15.591383f, 10.232284f), end = Offset(15.591383f, 20.04826f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99730474f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 9.190199851989746f, top = 7.44936990737915f, right = 36.48972988128662f, bottom = 20.500000476837158f,radiusX = 2.6410248279571533f, radiusY = 2.6268086433410645f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.99730456f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.3617119789123535f, top = 5.437580108642578f, right = 38.57697343826294f, bottom = 24.434303283691406f,radiusX = 3.1135053634643555f, radiusY = 3.096745729446411f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.96021f, 22.928211f)
    cubicTo(7.920206f, 23.604708f, 8.730542f, 23.981327f, 9.086861f, 23.943972f)
    lineTo(10.12454f, 20.883219f)
    lineTo(9.353463f, 20.29764f)
    lineTo(7.96021f, 22.928211f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.48888892f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.516914f, 22.835573f)
    cubicTo(38.329475f, 23.778805f, 37.63008f, 23.88915f, 36.799652f, 23.886835f)
    lineTo(35.984516f, 20.805096f)
    lineTo(36.65269f, 20.400543f)
    lineTo(38.516914f, 22.835573f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.8594f, 6.968716f)
    cubicTo(37.806858f, 6.3866153f, 37.480034f, 5.9401836f, 36.84539f, 5.88687f)
    lineTo(35.984516f, 7.0311494f)
    lineTo(36.5864f, 7.6657925f)
    lineTo(37.8594f, 6.968716f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(36.311f, 6.688802f), end = Offset(36.910904f, 7.4459696f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.984515f, 6.932165f)
    cubicTo(8.125445f, 6.4163556f, 8.529998f, 5.9805865f, 9.09835f, 5.94937f)
    lineTo(9.860173f, 7.137072f)
    lineTo(9.300941f, 7.7717147f)
    lineTo(7.984515f, 6.932165f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 0), 0.5f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(8.864771f, 7.3559117f), end = Offset(9.363035f, 6.7536297f), tileMode = TileMode.Clamp)
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.61914f, 8.9375f)
    lineTo(15.37793f, 14.635742f)
    cubicTo(15.159828f, 15.185871f, 14.889646f, 15.597655f, 14.567383f, 15.871094f)
    cubicTo(14.245116f, 16.147783f, 13.872395f, 16.28613f, 13.449219f, 16.286133f)
    cubicTo(13.292968f, 16.28613f, 13.12858f, 16.2666f, 12.956055f, 16.22754f)
    lineTo(12.956055f, 15.329102f)
    cubicTo(13.148112f, 15.387694f, 13.322265f, 15.416991f, 13.478516f, 15.416992f)
    cubicTo(13.667317f, 15.416991f, 13.839843f, 15.360025f, 13.996094f, 15.246094f)
    cubicTo(14.155597f, 15.135415f, 14.284178f, 14.966145f, 14.381836f, 14.738281f)
    lineTo(14.728516f, 13.9375f)
    lineTo(12.746094f, 8.9375f)
    lineTo(13.795898f, 8.9375f)
    lineTo(15.089844f, 12.6875f)
    cubicTo(15.112628f, 12.746095f, 15.141925f, 12.876303f, 15.177734f, 13.078125f)
    lineTo(15.207031f, 13.078125f)
    cubicTo(15.255857f, 12.873048f, 15.290036f, 12.74284f, 15.30957f, 12.6875f)
    lineTo(16.637695f, 8.9375f)
    lineTo(17.61914f, 8.9375f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.714844f, 8.9375f)
    lineTo(38.035156f, 11.457031f)
    lineTo(39.70508f, 13.9375f)
    lineTo(38.606445f, 13.9375f)
    lineTo(37.4541f, 11.989258f)
    lineTo(36.262695f, 13.9375f)
    lineTo(35.183594f, 13.9375f)
    lineTo(36.907227f, 11.476562f)
    lineTo(35.286133f, 8.9375f)
    lineTo(36.384766f, 8.9375f)
    lineTo(37.493164f, 10.978516f)
    lineTo(38.68457f, 8.9375f)
    lineTo(39.714844f, 8.9375f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.385742f, 38.052734f)
    cubicTo(13.851883f, 38.348957f, 13.189449f, 38.49707f, 12.398438f, 38.49707f)
    cubicTo(11.405596f, 38.49707f, 10.609699f, 38.176434f, 10.010742f, 37.535156f)
    cubicTo(9.415038f, 36.893883f, 9.1171875f, 36.050785f, 9.1171875f, 35.00586f)
    cubicTo(9.1171875f, 33.89584f, 9.458983f, 32.994144f, 10.142578f, 32.30078f)
    cubicTo(10.829425f, 31.604174f, 11.680661f, 31.255865f, 12.696289f, 31.25586f)
    cubicTo(13.383133f, 31.255865f, 13.946284f, 31.35515f, 14.385742f, 31.55371f)
    lineTo(14.385742f, 32.583984f)
    cubicTo(13.868159f, 32.278f, 13.301753f, 32.125008f, 12.686523f, 32.125f)
    cubicTo(11.924801f, 32.125008f, 11.304685f, 32.382168f, 10.826172f, 32.896484f)
    cubicTo(10.347655f, 33.40756f, 10.108397f, 34.094406f, 10.108398f, 34.95703f)
    cubicTo(10.108397f, 35.77409f, 10.329751f, 36.425133f, 10.772461f, 36.910156f)
    cubicTo(11.215167f, 37.39193f, 11.802731f, 37.632812f, 12.535156f, 37.632812f)
    cubicTo(13.244787f, 37.632812f, 13.861648f, 37.463543f, 14.385742f, 37.125f)
    lineTo(14.385742f, 38.052734f)
    moveTo(18.13086f, 38.30664f)
    cubicTo(17.925777f, 38.41081f, 17.662107f, 38.46289f, 17.339844f, 38.46289f)
    cubicTo(16.874348f, 38.46289f, 16.52604f, 38.337566f, 16.294922f, 38.086914f)
    cubicTo(16.0638f, 37.833008f, 15.948241f, 37.45215f, 15.948242f, 36.944336f)
    lineTo(15.948242f, 34.126953f)
    lineTo(15.108398f, 34.126953f)
    lineTo(15.108398f, 33.375f)
    lineTo(15.948242f, 33.375f)
    lineTo(15.948242f, 32.183594f)
    lineTo(16.890625f, 31.885742f)
    lineTo(16.890625f, 33.375f)
    lineTo(18.13086f, 33.375f)
    lineTo(18.13086f, 34.126953f)
    lineTo(16.890625f, 34.126953f)
    lineTo(16.890625f, 36.76367f)
    cubicTo(16.890623f, 37.089195f, 16.945961f, 37.32194f, 17.05664f, 37.461914f)
    cubicTo(17.167315f, 37.598633f, 17.361f, 37.666992f, 17.637695f, 37.666992f)
    cubicTo(17.816729f, 37.666992f, 17.981117f, 37.61328f, 18.13086f, 37.50586f)
    lineTo(18.13086f, 38.30664f)
    moveTo(22.011719f, 34.336914f)
    cubicTo(21.842445f, 34.222984f, 21.625973f, 34.16602f, 21.362305f, 34.166016f)
    cubicTo(21.049803f, 34.16602f, 20.50488f, 34.30925f, 20.29004f, 34.595703f)
    cubicTo(20.075193f, 34.882164f, 19.967772f, 35.261395f, 19.967773f, 35.7334f)
    lineTo(19.967773f, 38.375f)
    lineTo(19.030273f, 38.375f)
    lineTo(19.030273f, 33.375f)
    lineTo(19.967773f, 33.375f)
    lineTo(19.967773f, 34.36621f)
    cubicTo(20.088215f, 34.014652f, 20.263994f, 33.747726f, 20.495117f, 33.56543f)
    cubicTo(20.726234f, 33.379887f, 21.259764f, 33.287113f, 21.533203f, 33.28711f)
    cubicTo(21.738277f, 33.287113f, 21.897783f, 33.309902f, 22.011719f, 33.35547f)
    lineTo(22.011719f, 34.336914f)
    moveTo(24.00586f, 38.375f)
    lineTo(23.058594f, 38.375f)
    lineTo(23.058594f, 30.972656f)
    lineTo(24.00586f, 30.972656f)
    lineTo(24.00586f, 38.375f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
brush = SolidColor(Color(32, 74, 135, 255))
stroke = Stroke(width=2.0000005f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 3.1503379344940186f, top = 23.928627014160156f, right = 36.84966731071472f, bottom = 46.071388244628906f,radiusX = 5.800036430358887f, radiusY = 5.800036430358887f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
brush = SolidColor(Color(32, 74, 135, 255))
stroke = Stroke(width=2.0000012f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 25.063217163085938f, top = 1.1243784427642822f, right = 45.94068145751953f, bottom = 22.90563416481018f,radiusX = 5.446484088897705f, radiusY = 5.446484088897705f))
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
            return 1.995039701461792
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.12437784671783447
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 44.945640563964844
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 46.9470100402832
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

