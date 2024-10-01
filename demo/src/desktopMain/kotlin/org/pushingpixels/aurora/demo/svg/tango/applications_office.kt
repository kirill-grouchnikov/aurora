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
class applications_office : Painter() {
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
alpha *= 0.3186813f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.0f, 4.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.625f, 37.75f)
    cubicTo(37.648037f, 39.810402f, 34.913227f, 41.71825f, 30.456121f, 42.751118f)
    cubicTo(25.999018f, 43.78399f, 20.500982f, 43.78399f, 16.043879f, 42.751118f)
    cubicTo(11.586774f, 41.71825f, 8.851964f, 39.810402f, 8.875f, 37.75f)
    cubicTo(8.851964f, 35.689598f, 11.586774f, 33.78175f, 16.043879f, 32.748882f)
    cubicTo(20.500982f, 31.71601f, 25.999018f, 31.71601f, 30.456121f, 32.748882f)
    cubicTo(34.913227f, 33.78175f, 37.648037f, 35.689598f, 37.625f, 37.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(23.25f, 37.750004f), radius = 14.875f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6978022f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.125f, 4.75f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.5f, 36.8125f)
    cubicTo(33.51372f, 37.865097f, 31.884726f, 38.839756f, 29.229843f, 39.36742f)
    cubicTo(26.574959f, 39.89508f, 23.300041f, 39.89508f, 20.645157f, 39.36742f)
    cubicTo(17.990274f, 38.839756f, 16.361279f, 37.865097f, 16.375f, 36.8125f)
    cubicTo(16.361279f, 35.759903f, 17.990274f, 34.785244f, 20.645157f, 34.25758f)
    cubicTo(23.300041f, 33.72992f, 26.574959f, 33.72992f, 29.229843f, 34.25758f)
    cubicTo(31.884726f, 34.785244f, 33.51372f, 35.759903f, 33.5f, 36.8125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.78571427f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5739129781723022f, 0.0f, 0.0f, 0.0f,
0.0f, 0.5739129781723022f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
10.906519889831543f, 19.584779739379883f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.625f, 37.75f)
    cubicTo(37.648037f, 39.810402f, 34.913227f, 41.71825f, 30.456121f, 42.751118f)
    cubicTo(25.999018f, 43.78399f, 20.500982f, 43.78399f, 16.043879f, 42.751118f)
    cubicTo(11.586774f, 41.71825f, 8.851964f, 39.810402f, 8.875f, 37.75f)
    cubicTo(8.851964f, 35.689598f, 11.586774f, 33.78175f, 16.043879f, 32.748882f)
    cubicTo(20.500982f, 31.71601f, 25.999018f, 31.71601f, 30.456121f, 32.748882f)
    cubicTo(34.913227f, 33.78175f, 37.648037f, 35.689598f, 37.625f, 37.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(23.25f, 37.750004f), radius = 14.875f, tileMode = TileMode.Clamp)
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
1.005025029182434f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.18655799329280853f, 5.625f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.125f, 14.1875f)
    cubicTo(37.14493f, 16.225508f, 34.778725f, 18.112616f, 30.922361f, 19.13426f)
    cubicTo(27.065998f, 20.155903f, 22.309002f, 20.155903f, 18.452639f, 19.13426f)
    cubicTo(14.596274f, 18.112616f, 12.230069f, 16.225508f, 12.25f, 14.1875f)
    cubicTo(12.230069f, 12.149492f, 14.596274f, 10.262384f, 18.452639f, 9.240741f)
    cubicTo(22.309002f, 8.219097f, 27.065998f, 8.219097f, 30.922361f, 9.240741f)
    cubicTo(34.778725f, 10.262384f, 37.14493f, 12.149492f, 37.125f, 14.1875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.32894737f to Color(255, 255, 255, 176), 0.65789473f to Color(194, 194, 194, 87), 1.0f to Color(255, 255, 255, 0), start = Offset(11.75f, 14.1875f), end = Offset(37.625f, 14.1875f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(140, 140, 140, 255))
stroke = Stroke(width=0.99749684f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.125f, 14.1875f)
    cubicTo(37.14493f, 16.225508f, 34.778725f, 18.112616f, 30.922361f, 19.13426f)
    cubicTo(27.065998f, 20.155903f, 22.309002f, 20.155903f, 18.452639f, 19.13426f)
    cubicTo(14.596274f, 18.112616f, 12.230069f, 16.225508f, 12.25f, 14.1875f)
    cubicTo(12.230069f, 12.149492f, 14.596274f, 10.262384f, 18.452639f, 9.240741f)
    cubicTo(22.309002f, 8.219097f, 27.065998f, 8.219097f, 30.922361f, 9.240741f)
    cubicTo(34.778725f, 10.262384f, 37.14493f, 12.149492f, 37.125f, 14.1875f)
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
-40.25f, -7.5f, 0.0f, 1.0f)
))}){
// _0_0_4
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(81.18932f, 8.851212f)
    lineTo(75.59399f, 14.505723f)
    lineTo(60.324795f, 46.150494f)
    cubicTo(59.091904f, 49.407024f, 63.727036f, 51.320175f, 65.33689f, 48.436665f)
    lineTo(80.231415f, 16.856367f)
    lineTo(81.18932f, 8.851212f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(203, 144, 34, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(92, 65, 12, 255))
stroke = Stroke(width=1.0000008f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(81.18932f, 8.851212f)
    lineTo(75.59399f, 14.505723f)
    lineTo(60.324795f, 46.150494f)
    cubicTo(59.091904f, 49.407024f, 63.727036f, 51.320175f, 65.33689f, 48.436665f)
    lineTo(80.231415f, 16.856367f)
    lineTo(81.18932f, 8.851212f)
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
// _0_0_4_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(63.226658f, 41.398f)
    cubicTo(63.226658f, 41.398f, 63.32797f, 42.834988f, 64.58091f, 43.390907f)
    cubicTo(65.871704f, 43.96362f, 67.23713f, 43.376938f, 67.23713f, 43.376938f)
    lineTo(64.79487f, 48.421104f)
    cubicTo(64.79487f, 48.421104f, 63.92115f, 49.877754f, 61.95461f, 49.092304f)
    cubicTo(60.01493f, 48.31758f, 60.78424f, 46.41092f, 60.78424f, 46.41092f)
    lineTo(63.226658f, 41.398f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 209, 209, 255), 0.5f to Color(255, 29, 29, 255), 1.0f to Color(111, 0, 0, 255), start = Offset(60.628685f, 46.317978f), end = Offset(65.51398f, 48.3744f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(63.226658f, 41.398f)
    cubicTo(63.226658f, 41.398f, 63.32797f, 42.834988f, 64.58091f, 43.390907f)
    cubicTo(65.871704f, 43.96362f, 67.23713f, 43.376938f, 67.23713f, 43.376938f)
    lineTo(66.24766f, 45.38217f)
    cubicTo(66.24766f, 45.38217f, 64.93316f, 46.216125f, 63.561344f, 45.61505f)
    cubicTo(62.151825f, 44.997456f, 62.23719f, 43.403233f, 62.23719f, 43.403233f)
    lineTo(63.226658f, 41.398f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(193, 193, 193, 255), 1.0f to Color(172, 172, 172, 255), start = Offset(63.049706f, 43.398952f), end = Offset(65.87105f, 45.07164f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(80.47809f, 10.282021f)
    lineTo(76.001816f, 14.805631f)
    cubicTo(76.8222f, 16.301338f, 78.17004f, 17.07551f, 79.73038f, 16.661047f)
    lineTo(80.47809f, 10.282021f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(231, 226, 184, 255), 1.0f to Color(231, 226, 184, 0), center = Offset(76.93991f, 14.916542f), radius = 9.474863f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(79.078926f, 11.63315f)
    lineTo(80.66411f, 9.999789f)
    lineTo(80.36394f, 12.345152f)
    cubicTo(79.64635f, 12.567679f, 79.30047f, 12.163243f, 79.078926f, 11.63315f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(201, 201, 201, 255), start = Offset(79.87464f, 11.4102125f), end = Offset(79.52982f, 11.207862f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(75.970566f, 14.805795f)
    lineTo(77.22877f, 16.3617f)
    lineTo(64.439995f, 43.31636f)
    cubicTo(63.581627f, 42.87644f, 63.348885f, 42.080315f, 63.26774f, 41.41912f)
    lineTo(75.970566f, 14.805795f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 93))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(79.79288f, 16.660719f)
    lineTo(79.04388f, 16.852161f)
    lineTo(66.49448f, 43.620552f)
    cubicTo(66.49448f, 43.620552f, 67.10751f, 43.507168f, 67.24268f, 43.400734f)
    lineTo(79.79288f, 16.660719f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 93))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.53846157f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.590973f, 22.336294f)
    lineTo(33.477154f, 40.669163f)
    cubicTo(32.63409f, 45.449017f, 16.730858f, 45.501263f, 15.772831f, 40.669163f)
    lineTo(13.570621f, 22.398108f)
    cubicTo(15.678392f, 27.62852f, 34.583355f, 26.853544f, 35.590973f, 22.336294f)
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
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.876644f, 29.502754f)
    cubicTo(24.759054f, 28.895351f, 27.257458f, 31.087465f, 29.508623f, 34.357876f)
    cubicTo(31.759789f, 37.62829f, 32.227455f, 41.218243f, 32.03304f, 41.35207f)
    cubicTo(31.816362f, 41.501217f, 28.652225f, 39.767357f, 26.40106f, 36.496944f)
    cubicTo(24.149895f, 33.226536f, 22.994232f, 30.110155f, 23.876644f, 29.502754f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(231, 231, 231, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(125, 125, 125, 255))
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.876644f, 29.502754f)
    cubicTo(24.759054f, 28.895351f, 27.257458f, 31.087465f, 29.508623f, 34.357876f)
    cubicTo(31.759789f, 37.62829f, 32.227455f, 41.218243f, 32.03304f, 41.35207f)
    cubicTo(31.816362f, 41.501217f, 28.652225f, 39.767357f, 26.40106f, 36.496944f)
    cubicTo(24.149895f, 33.226536f, 22.994232f, 30.110155f, 23.876644f, 29.502754f)
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
0.7960140109062195f, 0.08258056640625f, 0.0f, 0.0f,
-0.08258056640625f, 0.7960140109062195f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.5307120084762573f, -0.7299680113792419f, 0.0f, 1.0f)
))}){
// _0_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.5625100135803223f, -0.8171939849853516f, 0.0f, 0.0f,
0.825069010257721f, -0.5679309964179993f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-15.220560073852539f, 83.8867416381836f, 0.0f, 1.0f)
))}){
// _0_0_7_0
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.2535026f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.085888f, 57.685642f)
    cubicTo(35.617744f, 56.50985f, 40.33878f, 56.182793f, 44.579147f, 56.820156f)
    cubicTo(48.819515f, 57.45752f, 51.981396f, 58.969444f, 52.94643f, 60.82116f)
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
-0.5625100135803223f, -0.8171939849853516f, 0.0f, 0.0f,
0.825069010257721f, -0.5679309964179993f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-14.28555965423584f, 81.45323944091797f, 0.0f, 1.0f)
))}){
// _0_0_7_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.364517f, 54.473244f)
    cubicTo(36.846443f, 55.27986f, 36.010174f, 56.13992f, 34.47655f, 56.414906f)
    cubicTo(32.94293f, 56.68989f, 31.267382f, 56.28022f, 30.69383f, 55.49003f)
    lineTo(33.5f, 54.9375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.2535026f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.364517f, 54.473244f)
    cubicTo(36.846443f, 55.27986f, 36.010174f, 56.13992f, 34.47655f, 56.414906f)
    cubicTo(32.94293f, 56.68989f, 31.267382f, 56.28022f, 30.69383f, 55.49003f)
    lineTo(33.5f, 54.9375f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.309496f, 27.045877f)
    cubicTo(21.251305f, 31.200586f, 24.142324f, 34.798885f, 26.528053f, 37.384544f)
    lineTo(30.395567f, 34.722366f)
    cubicTo(28.634237f, 31.171968f, 25.891142f, 26.577557f, 22.565008f, 21.745474f)
    cubicTo(16.561094f, 13.023205f, 10.607438f, 6.378291f, 8.252501f, 5.590012f)
    cubicTo(8.18023f, 5.5671086f, 8.083313f, 5.536959f, 8.018113f, 5.5255275f)
    cubicTo(7.9722757f, 5.5185456f, 7.9043655f, 5.52092f, 7.862244f, 5.5199065f)
    cubicTo(7.796054f, 5.520212f, 7.7111893f, 5.533879f, 7.6548076f, 5.5497823f)
    cubicTo(7.641025f, 5.554251f, 7.598818f, 5.55428f, 7.5856624f, 5.5597405f)
    cubicTo(7.572823f, 5.565697f, 7.5463f, 5.58828f, 7.534096f, 5.5952363f)
    cubicTo(7.528154f, 5.598964f, 7.514095f, 5.609004f, 7.5083127f, 5.6129837f)
    cubicTo(7.5025306f, 5.616964f, 7.488133f, 5.626512f, 7.4825296f, 5.6307316f)
    cubicTo(7.471675f, 5.639648f, 7.4411106f, 5.6563606f, 7.4309626f, 5.6662273f)
    cubicTo(7.421165f, 5.6765656f, 7.4060698f, 5.715981f, 7.396974f, 5.72726f)
    cubicTo(7.36199f, 5.774247f, 7.3189254f, 5.8486404f, 7.295009f, 5.910359f)
    cubicTo(7.2809167f, 5.9500666f, 7.2544537f, 6.012654f, 7.2446103f, 6.0579634f)
    cubicTo(7.232011f, 6.122947f, 7.225572f, 6.224241f, 7.2211637f, 6.299926f)
    cubicTo(7.11673f, 8.781096f, 11.19814f, 16.71476f, 17.202055f, 25.437027f)
    cubicTo(17.575632f, 25.979746f, 17.93728f, 26.520197f, 18.309496f, 27.045877f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 159, 207, 255), 0.31578946f to Color(165, 191, 218, 255), 1.0f to Color(55, 108, 164, 255), start = Offset(20.117485f, 29.35952f), end = Offset(25.692608f, 25.52192f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.309496f, 27.045877f)
    cubicTo(21.251305f, 31.200586f, 24.142324f, 34.798885f, 26.528053f, 37.384544f)
    lineTo(30.395567f, 34.722366f)
    cubicTo(28.634237f, 31.171968f, 25.891142f, 26.577557f, 22.565008f, 21.745474f)
    cubicTo(16.561094f, 13.023205f, 10.607438f, 6.378291f, 8.252501f, 5.590012f)
    cubicTo(8.18023f, 5.5671086f, 8.083313f, 5.536959f, 8.018113f, 5.5255275f)
    cubicTo(7.9722757f, 5.5185456f, 7.9043655f, 5.52092f, 7.862244f, 5.5199065f)
    cubicTo(7.796054f, 5.520212f, 7.7111893f, 5.533879f, 7.6548076f, 5.5497823f)
    cubicTo(7.641025f, 5.554251f, 7.598818f, 5.55428f, 7.5856624f, 5.5597405f)
    cubicTo(7.572823f, 5.565697f, 7.5463f, 5.58828f, 7.534096f, 5.5952363f)
    cubicTo(7.528154f, 5.598964f, 7.514095f, 5.609004f, 7.5083127f, 5.6129837f)
    cubicTo(7.5025306f, 5.616964f, 7.488133f, 5.626512f, 7.4825296f, 5.6307316f)
    cubicTo(7.471675f, 5.639648f, 7.4411106f, 5.6563606f, 7.4309626f, 5.6662273f)
    cubicTo(7.421165f, 5.6765656f, 7.4060698f, 5.715981f, 7.396974f, 5.72726f)
    cubicTo(7.36199f, 5.774247f, 7.3189254f, 5.8486404f, 7.295009f, 5.910359f)
    cubicTo(7.2809167f, 5.9500666f, 7.2544537f, 6.012654f, 7.2446103f, 6.0579634f)
    cubicTo(7.232011f, 6.122947f, 7.225572f, 6.224241f, 7.2211637f, 6.299926f)
    cubicTo(7.11673f, 8.781096f, 11.19814f, 16.71476f, 17.202055f, 25.437027f)
    cubicTo(17.575632f, 25.979746f, 17.93728f, 26.520197f, 18.309496f, 27.045877f)
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
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.743778f, 20.854607f)
    cubicTo(14.490825f, 21.902176f, 15.264732f, 22.908716f, 15.999685f, 23.855883f)
    lineTo(21.414206f, 20.128834f)
    cubicTo(20.528967f, 18.671322f, 19.545881f, 17.138575f, 18.484474f, 15.596602f)
    cubicTo(13.418672f, 8.237188f, 8.191582f, 2.7719285f, 5.9415207f, 2.2879093f)
    cubicTo(5.921755f, 2.284001f, 5.8740907f, 2.2754467f, 5.8547974f, 2.2723305f)
    cubicTo(5.8357406f, 2.2696111f, 5.786651f, 2.2586727f, 5.7680736f, 2.2567518f)
    cubicTo(5.722229f, 2.2529485f, 5.654997f, 2.249885f, 5.612204f, 2.2511306f)
    cubicTo(5.5953336f, 2.2520366f, 5.5594306f, 2.259366f, 5.543059f, 2.261089f)
    cubicTo(5.494695f, 2.2674897f, 5.422768f, 2.2848985f, 5.378985f, 2.298754f)
    cubicTo(5.364648f, 2.3037906f, 5.3236556f, 2.3028367f, 5.3098397f, 2.3087125f)
    cubicTo(5.289508f, 2.3181574f, 5.2516346f, 2.3506057f, 5.2324896f, 2.361956f)
    cubicTo(5.2262406f, 2.365952f, 5.212821f, 2.3754945f, 5.2067056f, 2.379704f)
    cubicTo(5.2005906f, 2.3839133f, 5.1868863f, 2.3930414f, 5.1809225f, 2.3974514f)
    cubicTo(5.1634846f, 2.411283f, 5.119654f, 2.435075f, 5.103572f, 2.4506953f)
    cubicTo(5.093151f, 2.461503f, 5.0794067f, 2.5001342f, 5.069584f, 2.5117283f)
    cubicTo(5.0410094f, 2.5476797f, 4.9990687f, 2.608651f, 4.975823f, 2.6515422f)
    cubicTo(4.9683685f, 2.6662195f, 4.948704f, 2.6971397f, 4.9418344f, 2.7125752f)
    cubicTo(4.925393f, 2.752103f, 4.9042516f, 2.8159978f, 4.8914366f, 2.860179f)
    cubicTo(4.886598f, 2.8782182f, 4.87929f, 2.9279776f, 4.8750267f, 2.9467492f)
    cubicTo(4.871049f, 2.9658837f, 4.8620224f, 3.0134604f, 4.8586164f, 3.0333192f)
    cubicTo(4.5074186f, 5.307897f, 7.7463174f, 12.141716f, 12.812121f, 19.50113f)
    cubicTo(13.127326f, 19.959047f, 13.428214f, 20.4121f, 13.743778f, 20.854607f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(91, 144, 200, 255), 0.31578946f to Color(143, 176, 209, 255), 1.0f to Color(52, 103, 157, 255), start = Offset(20.117485f, 29.35952f), end = Offset(25.692608f, 25.52192f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.743778f, 20.854607f)
    cubicTo(14.490825f, 21.902176f, 15.264732f, 22.908716f, 15.999685f, 23.855883f)
    lineTo(21.414206f, 20.128834f)
    cubicTo(20.528967f, 18.671322f, 19.545881f, 17.138575f, 18.484474f, 15.596602f)
    cubicTo(13.418672f, 8.237188f, 8.191582f, 2.7719285f, 5.9415207f, 2.2879093f)
    cubicTo(5.921755f, 2.284001f, 5.8740907f, 2.2754467f, 5.8547974f, 2.2723305f)
    cubicTo(5.8357406f, 2.2696111f, 5.786651f, 2.2586727f, 5.7680736f, 2.2567518f)
    cubicTo(5.722229f, 2.2529485f, 5.654997f, 2.249885f, 5.612204f, 2.2511306f)
    cubicTo(5.5953336f, 2.2520366f, 5.5594306f, 2.259366f, 5.543059f, 2.261089f)
    cubicTo(5.494695f, 2.2674897f, 5.422768f, 2.2848985f, 5.378985f, 2.298754f)
    cubicTo(5.364648f, 2.3037906f, 5.3236556f, 2.3028367f, 5.3098397f, 2.3087125f)
    cubicTo(5.289508f, 2.3181574f, 5.2516346f, 2.3506057f, 5.2324896f, 2.361956f)
    cubicTo(5.2262406f, 2.365952f, 5.212821f, 2.3754945f, 5.2067056f, 2.379704f)
    cubicTo(5.2005906f, 2.3839133f, 5.1868863f, 2.3930414f, 5.1809225f, 2.3974514f)
    cubicTo(5.1634846f, 2.411283f, 5.119654f, 2.435075f, 5.103572f, 2.4506953f)
    cubicTo(5.093151f, 2.461503f, 5.0794067f, 2.5001342f, 5.069584f, 2.5117283f)
    cubicTo(5.0410094f, 2.5476797f, 4.9990687f, 2.608651f, 4.975823f, 2.6515422f)
    cubicTo(4.9683685f, 2.6662195f, 4.948704f, 2.6971397f, 4.9418344f, 2.7125752f)
    cubicTo(4.925393f, 2.752103f, 4.9042516f, 2.8159978f, 4.8914366f, 2.860179f)
    cubicTo(4.886598f, 2.8782182f, 4.87929f, 2.9279776f, 4.8750267f, 2.9467492f)
    cubicTo(4.871049f, 2.9658837f, 4.8620224f, 3.0134604f, 4.8586164f, 3.0333192f)
    cubicTo(4.5074186f, 5.307897f, 7.7463174f, 12.141716f, 12.812121f, 19.50113f)
    cubicTo(13.127326f, 19.959047f, 13.428214f, 20.4121f, 13.743778f, 20.854607f)
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
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.2003446f, 8.018811f)
    lineTo(10.016292f, 5.3921294f)
    cubicTo(7.991423f, 3.1273553f, 6.163735f, 1.7285397f, 5.1772695f, 1.7848891f)
    cubicTo(5.1709747f, 1.7853731f, 5.1469803f, 1.7864435f, 5.1407557f, 1.7870493f)
    cubicTo(5.1346025f, 1.7877759f, 5.1103263f, 1.7883613f, 5.104243f, 1.7892089f)
    cubicTo(5.098231f, 1.7901787f, 5.0736694f, 1.7902766f, 5.0677285f, 1.7913684f)
    cubicTo(5.032521f, 1.7986523f, 4.975711f, 1.8194528f, 4.943135f, 1.8311831f)
    cubicTo(4.93778f, 1.8332626f, 4.922632f, 1.8467262f, 4.9173517f, 1.8489307f)
    cubicTo(4.9121466f, 1.85126f, 4.885968f, 1.8486359f, 4.8808393f, 1.8510911f)
    cubicTo(4.8757854f, 1.8536721f, 4.860033f, 1.8661321f, 4.8550553f, 1.8688391f)
    cubicTo(4.850154f, 1.8716723f, 4.834096f, 1.8836267f, 4.8292723f, 1.8865868f)
    cubicTo(4.8245244f, 1.8896735f, 4.8081584f, 1.9011205f, 4.8034887f, 1.9043349f)
    cubicTo(4.798819f, 1.9075493f, 4.7822833f, 1.9187498f, 4.7777057f, 1.9220825f)
    cubicTo(4.7732177f, 1.9255319f, 4.756318f, 1.9362636f, 4.7519217f, 1.9398305f)
    cubicTo(4.747616f, 1.9435138f, 4.7303534f, 1.9537793f, 4.7261386f, 1.9575782f)
    cubicTo(4.7220135f, 1.961493f, 4.7151184f, 1.986883f, 4.7110844f, 1.9909135f)
    cubicTo(4.70714f, 1.9950587f, 4.6891556f, 2.0044017f, 4.6853013f, 2.008661f)
    cubicTo(4.6627135f, 2.0349023f, 4.6230016f, 2.080542f, 4.603627f, 2.1108296f)
    cubicTo(4.600487f, 2.1159885f, 4.5916266f, 2.1388962f, 4.5885744f, 2.1441658f)
    cubicTo(4.585611f, 2.1495457f, 4.5763965f, 2.1720133f, 4.5735207f, 2.177502f)
    cubicTo(4.570733f, 2.1830995f, 4.561167f, 2.2051303f, 4.5584674f, 2.2108374f)
    cubicTo(4.1536427f, 3.1121736f, 4.807748f, 5.3188167f, 6.2003446f, 8.018811f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 159, 207, 255), 0.31578946f to Color(165, 191, 218, 255), 1.0f to Color(55, 108, 164, 255), start = Offset(20.117485f, 29.35952f), end = Offset(25.692608f, 25.52192f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.2003446f, 8.018811f)
    lineTo(10.016292f, 5.3921294f)
    cubicTo(7.991423f, 3.1273553f, 6.163735f, 1.7285397f, 5.1772695f, 1.7848891f)
    cubicTo(5.1709747f, 1.7853731f, 5.1469803f, 1.7864435f, 5.1407557f, 1.7870493f)
    cubicTo(5.1346025f, 1.7877759f, 5.1103263f, 1.7883613f, 5.104243f, 1.7892089f)
    cubicTo(5.098231f, 1.7901787f, 5.0736694f, 1.7902766f, 5.0677285f, 1.7913684f)
    cubicTo(5.032521f, 1.7986523f, 4.975711f, 1.8194528f, 4.943135f, 1.8311831f)
    cubicTo(4.93778f, 1.8332626f, 4.922632f, 1.8467262f, 4.9173517f, 1.8489307f)
    cubicTo(4.9121466f, 1.85126f, 4.885968f, 1.8486359f, 4.8808393f, 1.8510911f)
    cubicTo(4.8757854f, 1.8536721f, 4.860033f, 1.8661321f, 4.8550553f, 1.8688391f)
    cubicTo(4.850154f, 1.8716723f, 4.834096f, 1.8836267f, 4.8292723f, 1.8865868f)
    cubicTo(4.8245244f, 1.8896735f, 4.8081584f, 1.9011205f, 4.8034887f, 1.9043349f)
    cubicTo(4.798819f, 1.9075493f, 4.7822833f, 1.9187498f, 4.7777057f, 1.9220825f)
    cubicTo(4.7732177f, 1.9255319f, 4.756318f, 1.9362636f, 4.7519217f, 1.9398305f)
    cubicTo(4.747616f, 1.9435138f, 4.7303534f, 1.9537793f, 4.7261386f, 1.9575782f)
    cubicTo(4.7220135f, 1.961493f, 4.7151184f, 1.986883f, 4.7110844f, 1.9909135f)
    cubicTo(4.70714f, 1.9950587f, 4.6891556f, 2.0044017f, 4.6853013f, 2.008661f)
    cubicTo(4.6627135f, 2.0349023f, 4.6230016f, 2.080542f, 4.603627f, 2.1108296f)
    cubicTo(4.600487f, 2.1159885f, 4.5916266f, 2.1388962f, 4.5885744f, 2.1441658f)
    cubicTo(4.585611f, 2.1495457f, 4.5763965f, 2.1720133f, 4.5735207f, 2.177502f)
    cubicTo(4.570733f, 2.1830995f, 4.561167f, 2.2051303f, 4.5584674f, 2.2108374f)
    cubicTo(4.1536427f, 3.1121736f, 4.807748f, 5.3188167f, 6.2003446f, 8.018811f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.35714284f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(7.8889513f, 8.592262f), end = Offset(16.981659f, 21.149517f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.417724f, 21.244087f)
    cubicTo(15.60655f, 21.511402f, 15.795857f, 21.760462f, 15.983434f, 22.02224f)
    lineTo(19.733027f, 19.438211f)
    cubicTo(19.219873f, 18.65987f, 18.496328f, 17.390278f, 17.956781f, 16.581053f)
    cubicTo(11.744978f, 7.264434f, 5.6568522f, 2.6091151f, 5.40304f, 2.7829475f)
    cubicTo(5.1238437f, 2.974165f, 7.567532f, 10.260041f, 13.900362f, 19.06121f)
    cubicTo(14.272182f, 19.577953f, 15.059948f, 20.737597f, 15.417724f, 21.244087f)
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
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.125f, 20.0f)
    lineTo(34.25f, 41.375f)
    cubicTo(33.333332f, 46.57216f, 16.041668f, 46.628963f, 15.0f, 41.375f)
    lineTo(12.036612f, 20.007584f)
    cubicTo(13.877231f, 26.876867f, 36.02941f, 27.218151f, 37.125f, 20.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(245, 245, 245, 23), 0.2631579f to Color(255, 255, 255, 230), 0.7479224f to Color(199, 199, 199, 117), 1.0f to Color(255, 255, 255, 199), start = Offset(15.375f, 31.7096f), end = Offset(34.250416f, 31.7096f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(149, 151, 145, 255), 0.5f to Color(248, 248, 248, 255), 1.0f to Color(140, 140, 140, 255), start = Offset(30.875f, 25.0846f), end = Offset(15.625f, 24.7096f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.125f, 20.0f)
    lineTo(34.25f, 41.375f)
    cubicTo(33.333332f, 46.57216f, 16.041668f, 46.628963f, 15.0f, 41.375f)
    lineTo(12.036612f, 20.007584f)
    cubicTo(13.877231f, 26.876867f, 36.02941f, 27.218151f, 37.125f, 20.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.72527474f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.48186f, 2.524195f)
    lineTo(35.708893f, 7.3855543f)
    lineTo(27.400389f, 24.665476f)
    lineTo(36.10664f, 7.8716903f)
    lineTo(40.48186f, 2.524195f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.41758242f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.34928f, 2.524195f)
    lineTo(39.465397f, 9.24171f)
    lineTo(32.70369f, 23.64901f)
    lineTo(39.244427f, 9.1975155f)
    lineTo(38.714096f, 9.285904f)
    lineTo(40.34928f, 2.524195f)
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
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.473166f, 25.284195f)
    lineTo(19.445436f, 44.199303f)
    lineTo(22.715805f, 44.72963f)
    lineTo(22.892582f, 25.814526f)
    lineTo(18.473166f, 25.284195f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(20.064156f, 27.140348f), end = Offset(20.682873f, 44.110912f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 1.4577174186706543
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
            return 40.151206970214844
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.52577209472656
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

