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
class address_book_new : Painter() {
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.096455f, 4.65202f)
    lineTo(40.521076f, 4.65202f)
    cubicTo(41.228184f, 4.65202f, 41.75851f, 4.828797f, 41.935287f, 5.71268f)
    lineTo(42.81917f, 12.606972f)
    cubicTo(42.90756f, 13.667632f, 42.44352f, 14.021185f, 41.493347f, 14.021185f)
    lineTo(32.919678f, 14.021185f)
    lineTo(33.096455f, 4.6520205f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(237, 212, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(196, 160, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.096455f, 4.65202f)
    lineTo(40.521076f, 4.65202f)
    cubicTo(41.228184f, 4.65202f, 41.75851f, 4.828797f, 41.935287f, 5.71268f)
    lineTo(42.81917f, 12.606972f)
    cubicTo(42.90756f, 13.667632f, 42.44352f, 14.021185f, 41.493347f, 14.021185f)
    lineTo(32.919678f, 14.021185f)
    lineTo(33.096455f, 4.6520205f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4853801f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000006f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.10295f, 5.638875f)
    lineTo(40.46351f, 5.638875f)
    cubicTo(40.771656f, 5.638875f, 40.94027f, 5.669037f, 40.986057f, 5.960473f)
    lineTo(41.777493f, 12.344449f)
    cubicTo(41.847263f, 12.775421f, 41.9599f, 13.019804f, 41.637215f, 13.034341f)
    lineTo(33.963417f, 13.034341f)
    lineTo(34.102955f, 5.638875f)
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
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.596455f, 12.40202f)
    lineTo(43.021076f, 12.40202f)
    cubicTo(43.728184f, 12.40202f, 44.25851f, 12.578797f, 44.435287f, 13.462681f)
    lineTo(45.31917f, 20.356972f)
    cubicTo(45.40756f, 21.417631f, 44.94352f, 21.771185f, 43.993347f, 21.771185f)
    lineTo(35.419678f, 21.771185f)
    lineTo(35.596455f, 12.4020195f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(157, 176, 41, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(114, 126, 10, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.596455f, 12.40202f)
    lineTo(43.021076f, 12.40202f)
    cubicTo(43.728184f, 12.40202f, 44.25851f, 12.578797f, 44.435287f, 13.462681f)
    lineTo(45.31917f, 20.356972f)
    cubicTo(45.40756f, 21.417631f, 44.94352f, 21.771185f, 43.993347f, 21.771185f)
    lineTo(35.419678f, 21.771185f)
    lineTo(35.596455f, 12.4020195f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4853801f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000006f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.60295f, 13.388875f)
    lineTo(42.96351f, 13.388875f)
    cubicTo(43.271656f, 13.388875f, 43.44027f, 13.419037f, 43.486057f, 13.710473f)
    lineTo(44.277493f, 20.094448f)
    cubicTo(44.347263f, 20.52542f, 44.4599f, 20.769804f, 44.137215f, 20.78434f)
    lineTo(36.463417f, 20.78434f)
    lineTo(36.602955f, 13.388874f)
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
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.06451f, 20.776499f)
    lineTo(44.50992f, 20.776499f)
    cubicTo(45.314243f, 20.776499f, 45.917484f, 20.995897f, 46.11857f, 22.092882f)
    lineTo(47.123974f, 30.64938f)
    cubicTo(47.224514f, 31.965763f, 46.696674f, 32.40456f, 45.615864f, 32.40456f)
    lineTo(35.863426f, 32.40456f)
    lineTo(36.064507f, 20.776497f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(239, 41, 41, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(204, 0, 0, 255))
stroke = Stroke(width=0.9999999f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.06451f, 20.776499f)
    lineTo(44.50992f, 20.776499f)
    cubicTo(45.314243f, 20.776499f, 45.917484f, 20.995897f, 46.11857f, 22.092882f)
    lineTo(47.123974f, 30.64938f)
    cubicTo(47.224514f, 31.965763f, 46.696674f, 32.40456f, 45.615864f, 32.40456f)
    lineTo(35.863426f, 32.40456f)
    lineTo(36.064507f, 20.776497f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4853801f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000007f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.209385f, 21.763575f)
    lineTo(44.444435f, 21.763575f)
    cubicTo(44.79495f, 21.763575f, 44.986744f, 21.80193f, 45.038826f, 22.172514f)
    lineTo(45.93907f, 30.290268f)
    cubicTo(46.018433f, 30.838285f, 46.146557f, 31.149038f, 45.779507f, 31.167522f)
    lineTo(37.05066f, 31.167522f)
    lineTo(37.20938f, 21.763575f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.021649999544024467f, 0.0f, 0.0f, 0.0f,
0.0f, 0.04307999834418297f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
43.08625030517578f, 34.04508972167969f, 0.0f, 1.0f)
))}){
// _0_6
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1559.2523f, -150.69685f)
    lineTo(-219.61877f, -150.69685f)
    lineTo(-219.61877f, 327.66034f)
    lineTo(-1559.2523f, 327.66034f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(-1051.935f, -150.69904f), end = Offset(-1051.935f, 327.65674f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_6_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-219.61876f, -150.68037f)
    lineTo(-219.61876f, 327.6504f)
    cubicTo(-76.7446f, 328.55084f, 125.78145f, 220.48073f, 125.78139f, 88.45421f)
    cubicTo(125.78139f, -43.572327f, -33.655426f, -150.68039f, -219.61876f, -150.6804f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-211.14539f, 85.66498f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_6_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1559.2523f, -150.68037f)
    lineTo(-1559.2523f, 327.6504f)
    cubicTo(-1702.1265f, 328.55084f, -1904.6526f, 220.48073f, -1904.6526f, 88.45421f)
    cubicTo(-1904.6526f, -43.572327f, -1745.2158f, -150.68039f, -1559.2524f, -150.6804f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-1567.7253f, 85.66498f), radius = 325.0f, tileMode = TileMode.Clamp)
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
// _0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.364322f, 5.5185895f)
    cubicTo(6.455105f, 3.6036f, 7.371976f, 2.5542812f, 9.078878f, 2.549044f)
    lineTo(38.405777f, 2.4590576f)
    cubicTo(38.652363f, 2.458301f, 38.97432f, 2.6592069f, 38.999012f, 2.9089887f)
    lineTo(42.257492f, 35.86723f)
    lineTo(40.94219f, 35.923862f)
    lineTo(41.571426f, 42.369514f)
    cubicTo(41.63244f, 42.9945f, 41.390057f, 43.52882f, 40.499996f, 43.533035f)
    lineTo(9.789301f, 43.678474f)
    cubicTo(7.2567563f, 43.690468f, 4.6538415f, 41.599762f, 4.77593f, 39.024403f)
    lineTo(6.364322f, 5.5185895f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(91, 107, 148, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(54, 72, 120, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.364322f, 5.5185895f)
    cubicTo(6.455105f, 3.6036f, 7.371976f, 2.5542812f, 9.078878f, 2.549044f)
    lineTo(38.405777f, 2.4590576f)
    cubicTo(38.652363f, 2.458301f, 38.97432f, 2.6592069f, 38.999012f, 2.9089887f)
    lineTo(42.257492f, 35.86723f)
    lineTo(40.94219f, 35.923862f)
    lineTo(41.571426f, 42.369514f)
    cubicTo(41.63244f, 42.9945f, 41.390057f, 43.52882f, 40.499996f, 43.533035f)
    lineTo(9.789301f, 43.678474f)
    cubicTo(7.2567563f, 43.690468f, 4.6538415f, 41.599762f, 4.77593f, 39.024403f)
    lineTo(6.364322f, 5.5185895f)
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
// _0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.125f, 34.875f)
    lineTo(10.9375f, 35.0f)
    cubicTo(9.380981f, 35.177868f, 8.125f, 36.39612f, 8.125f, 38.0f)
    cubicTo(8.125f, 39.60388f, 9.380981f, 40.822132f, 10.9375f, 41.0f)
    lineTo(40.125f, 41.125f)
    lineTo(40.125f, 41.0625f)
    cubicTo(38.46938f, 40.98435f, 37.125f, 39.67485f, 37.125f, 38.0f)
    cubicTo(37.125f, 36.32515f, 38.46938f, 35.01565f, 40.125f, 34.9375f)
    lineTo(40.125f, 34.875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(203, 203, 203, 255), start = Offset(24.749977f, 40.374817f), end = Offset(24.124977f, 36.374832f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.6875f, 2.8125f)
    cubicTo(7.98059f, 2.8125f, 7.050103f, 3.821506f, 6.96875f, 5.673866f)
    lineTo(5.3125f, 37.82577f)
    cubicTo(5.22054f, 40.904198f, 7.1393733f, 42.654484f, 9.125f, 43.15625f)
    cubicTo(4.875f, 41.525578f, 5.4375f, 34.164455f, 10.75f, 34.19522f)
    lineTo(41.648285f, 34.19522f)
    lineTo(38.335785f, 3.2432423f)
    cubicTo(38.310024f, 3.0025294f, 37.987877f, 2.812499f, 37.742035f, 2.812499f)
    lineTo(9.6875f, 2.812499f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(214, 227, 240, 255), 1.0f to Color(149, 177, 207, 255), start = Offset(7.3088727f, 22.180592f), end = Offset(14.898297f, 22.180592f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4804469f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
-0.03582401058592956f, 0.9993600249290466f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.788627f, 3.968539f)
    lineTo(11.788627f, 3.968539f)
    lineTo(11.788627f, 33.57333f)
    lineTo(9.788627f, 33.57333f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 17), 1.0f to Color(255, 255, 255, 255), start = Offset(9.78862f, 18.770924f), end = Offset(11.258287f, 18.770924f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_11
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(75.22758f, 24.70739f), end = Offset(-2.8284333f, 19.555372f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=20.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.875101f, 3.333683f)
    cubicTo(8.191202f, 3.333683f, 7.5384235f, 4.065846f, 7.4581676f, 5.8878307f)
    lineTo(6.1592636f, 35.7772f)
    cubicTo(7.092592f, 34.170452f, 8.59886f, 33.594437f, 11.011665f, 33.594437f)
    lineTo(40.96308f, 33.594437f)
    lineTo(38.13718f, 3.757363f)
    cubicTo(38.114727f, 3.5203092f, 37.79396f, 3.333683f, 37.551434f, 3.333683f)
    lineTo(9.875101f, 3.333683f)
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
// _0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(21.12553f, 18.381289f)
    cubicTo(21.050283f, 19.50227f, 21.269377f, 20.384155f, 21.782812f, 21.026947f)
    cubicTo(22.296751f, 21.66191f, 23.039742f, 21.979387f, 24.011787f, 21.979387f)
    cubicTo(24.97597f, 21.97939f, 25.754004f, 21.65799f, 26.345892f, 21.01519f)
    cubicTo(26.94559f, 20.372398f, 27.282799f, 19.49443f, 27.357529f, 18.381289f)
    cubicTo(27.431173f, 17.28384f, 27.20737f, 16.41371f, 26.686123f, 15.7709055f)
    cubicTo(26.16537f, 15.120279f, 25.426826f, 14.794959f, 24.470482f, 14.7949505f)
    cubicTo(23.521952f, 14.794959f, 22.743917f, 15.116361f, 22.136377f, 15.759146f)
    cubicTo(21.536655f, 16.401953f, 21.199707f, 17.276f, 21.12553f, 18.381289f)
    moveTo(27.29793f, 21.897076f)
    cubicTo(26.787062f, 22.500679f, 26.216183f, 22.947502f, 25.585289f, 23.237545f)
    cubicTo(24.962732f, 23.519749f, 24.247753f, 23.66085f, 23.44035f, 23.66085f)
    cubicTo(22.092031f, 23.660852f, 21.027197f, 23.174833f, 20.245834f, 22.202799f)
    cubicTo(19.472826f, 21.222927f, 19.138937f, 19.949093f, 19.244171f, 18.38129f)
    cubicTo(19.349394f, 16.8135f, 19.858196f, 15.539667f, 20.770584f, 14.559784f)
    cubicTo(21.682955f, 13.57992f, 22.809376f, 13.089984f, 24.149855f, 13.089972f)
    cubicTo(24.957258f, 13.089984f, 25.656689f, 13.238927f, 26.24815f, 13.536794f)
    cubicTo(26.840107f, 13.826849f, 27.347351f, 14.269752f, 27.76988f, 14.865503f)
    lineTo(27.873268f, 13.325144f)
    lineTo(29.554733f, 13.325144f)
    lineTo(28.97387f, 21.97939f)
    cubicTo(30.12992f, 21.806932f, 31.058552f, 21.285639f, 31.75977f, 20.41551f)
    cubicTo(32.46931f, 19.537546f, 32.87066f, 18.404814f, 32.96381f, 17.017305f)
    cubicTo(33.020084f, 16.178543f, 32.947536f, 15.390723f, 32.74617f, 14.65385f)
    cubicTo(32.5526f, 13.916996f, 32.22602f, 13.235003f, 31.766438f, 12.607875f)
    cubicTo(31.020088f, 11.580981f, 30.077154f, 10.797082f, 28.937628f, 10.256178f)
    cubicTo(27.80643f, 9.707464f, 26.55101f, 9.433099f, 25.171364f, 9.433083f)
    cubicTo(24.207153f, 9.433099f, 23.273472f, 9.562442f, 22.370317f, 9.821113f)
    cubicTo(21.467665f, 10.071976f, 20.623238f, 10.448246f, 19.83703f, 10.949926f)
    cubicTo(18.552633f, 11.749518f, 17.517935f, 12.799941f, 16.732933f, 14.1012f)
    cubicTo(15.956283f, 15.394644f, 15.517189f, 16.797821f, 15.415646f, 18.31074f)
    cubicTo(15.331986f, 19.557144f, 15.477001f, 20.725153f, 15.850694f, 21.814766f)
    cubicTo(16.232216f, 22.904388f, 16.822319f, 23.864666f, 17.621004f, 24.695595f)
    cubicTo(18.389372f, 25.51085f, 19.300241f, 26.13013f, 20.353619f, 26.553436f)
    cubicTo(21.406452f, 26.98458f, 22.548233f, 27.200151f, 23.778965f, 27.200153f)
    cubicTo(24.79018f, 27.20015f, 25.793386f, 27.027693f, 26.788588f, 26.682781f)
    cubicTo(27.79107f, 26.345701f, 28.721254f, 25.859684f, 29.579144f, 25.224728f)
    lineTo(30.549805f, 26.529919f)
    cubicTo(29.518877f, 27.2903f, 28.40992f, 27.870384f, 27.222935f, 28.270174f)
    cubicTo(26.04323f, 28.6778f, 24.85762f, 28.881613f, 23.666107f, 28.881617f)
    cubicTo(22.215885f, 28.881613f, 20.865345f, 28.622927f, 19.614487f, 28.105558f)
    cubicTo(18.363083f, 27.59602f, 17.268574f, 26.851318f, 16.33096f, 25.871445f)
    cubicTo(15.3933325f, 24.891571f, 14.705058f, 23.758839f, 14.266137f, 22.47324f)
    cubicTo(13.827735f, 21.179813f, 13.658257f, 19.79231f, 13.7577f, 18.310738f)
    cubicTo(13.853456f, 16.88405f, 14.2112665f, 15.523986f, 14.831133f, 14.230541f)
    cubicTo(15.450996f, 12.93712f, 16.287666f, 11.800468f, 17.341154f, 10.820581f)
    cubicTo(18.419195f, 9.825045f, 19.63824f, 9.064665f, 20.998291f, 8.539436f)
    cubicTo(22.358847f, 8.0064f, 23.779911f, 7.739876f, 25.261494f, 7.739858f)
    cubicTo(26.923346f, 7.7398753f, 28.440817f, 8.080872f, 29.813917f, 8.762847f)
    cubicTo(31.194818f, 9.444854f, 32.325287f, 10.41297f, 33.20532f, 11.667192f)
    cubicTo(33.741657f, 12.435425f, 34.132446f, 13.270279f, 34.37768f, 14.171751f)
    cubicTo(34.63071f, 15.073242f, 34.72488f, 16.00608f, 34.66019f, 16.97027f)
    cubicTo(34.52179f, 19.031929f, 33.789417f, 20.658518f, 32.463066f, 21.85004f)
    cubicTo(31.136673f, 23.041569f, 29.37445f, 23.660849f, 27.176392f, 23.707882f)
    lineTo(27.297932f, 21.897074f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(173, 127, 168, 255))
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
0.6111299991607666f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6111299991607666f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-24.949920654296875f, -67.63529205322266f, 0.0f, 1.0f)
))}){
// _0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(69.375f, 125.0f)
    cubicTo(69.39803f, 130.151f, 66.66322f, 134.92062f, 62.206123f, 137.50279f)
    cubicTo(57.74902f, 140.08498f, 52.25098f, 140.08498f, 47.793877f, 137.50279f)
    cubicTo(43.336773f, 134.92062f, 40.601963f, 130.151f, 40.625f, 125.0f)
    cubicTo(40.601963f, 119.84899f, 43.336773f, 115.07938f, 47.793877f, 112.4972f)
    cubicTo(52.25098f, 109.91502f, 57.74902f, 109.91502f, 62.206123f, 112.4972f)
    cubicTo(66.66322f, 115.07938f, 69.39803f, 119.84899f, 69.375f, 125.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 245, 32, 227), 1.0f to Color(255, 243, 0, 0), center = Offset(55.0f, 125.0f), radius = 14.375f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
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
            return 0.0
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
            return 47.63536834716797
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 48.0
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

