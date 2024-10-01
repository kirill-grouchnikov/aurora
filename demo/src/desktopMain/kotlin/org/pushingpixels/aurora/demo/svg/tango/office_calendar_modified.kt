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
class office_calendar_modified : Painter() {
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
1.0920079946517944f, 0.0f, 0.0f, 0.0f,
0.0f, 0.5360829830169678f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.549359083175659f, 20.739330291748047f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.428574f, 29.0f)
    cubicTo(44.458107f, 33.965443f, 40.952114f, 38.563232f, 35.23816f, 41.052387f)
    cubicTo(29.524208f, 43.541542f, 22.475794f, 43.541542f, 16.76184f, 41.052387f)
    cubicTo(11.04789f, 38.563232f, 7.5418963f, 33.965443f, 7.5714283f, 29.0f)
    cubicTo(7.5418963f, 24.034557f, 11.04789f, 19.436768f, 16.76184f, 16.947613f)
    cubicTo(22.475794f, 14.458459f, 29.524208f, 14.458459f, 35.23816f, 16.947613f)
    cubicTo(40.952114f, 19.436768f, 44.458107f, 24.034557f, 44.428574f, 29.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(26.0f, 29.0f), radius = 18.428574f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.857143f, 38.42857f)
    cubicTo(4.642857f, 39.42857f, 5.464286f, 40.464287f, 6.821429f, 40.42857f)
    lineTo(43.0f, 40.42857f)
    cubicTo(44.285713f, 40.392857f, 44.714287f, 39.214287f, 44.428574f, 38.25f)
    lineTo(36.57143f, 9.428572f)
    lineTo(10.571429f, 9.428572f)
    lineTo(4.857143f, 38.42857f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(167, 167, 167, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(101, 101, 101, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.857143f, 38.42857f)
    cubicTo(4.642857f, 39.42857f, 5.464286f, 40.464287f, 6.821429f, 40.42857f)
    lineTo(43.0f, 40.42857f)
    cubicTo(44.285713f, 40.392857f, 44.714287f, 39.214287f, 44.428574f, 38.25f)
    lineTo(36.57143f, 9.428572f)
    lineTo(10.571429f, 9.428572f)
    lineTo(4.857143f, 38.42857f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.10857142f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.162504f, 30.806458f)
    lineTo(39.395096f, 30.806458f)
    cubicTo(40.252296f, 30.806458f, 40.94239f, 31.478745f, 40.94239f, 32.313824f)
    lineTo(42.26958f, 37.11476f)
    cubicTo(42.26958f, 37.94984f, 41.579487f, 38.622128f, 40.722282f, 38.622128f)
    lineTo(8.277712f, 38.622128f)
    cubicTo(7.420509f, 38.622128f, 6.7304144f, 37.94984f, 6.7304144f, 37.11476f)
    lineTo(7.6152067f, 32.313824f)
    cubicTo(7.6152067f, 31.478745f, 8.305302f, 30.806458f, 9.162504f, 30.806458f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.162504f, 30.806458f)
    lineTo(39.395096f, 30.806458f)
    cubicTo(40.252296f, 30.806458f, 40.94239f, 31.478745f, 40.94239f, 32.313824f)
    lineTo(42.26958f, 37.11476f)
    cubicTo(42.26958f, 37.94984f, 41.579487f, 38.622128f, 40.722282f, 38.622128f)
    lineTo(8.277712f, 38.622128f)
    cubicTo(7.420509f, 38.622128f, 6.7304144f, 37.94984f, 6.7304144f, 37.11476f)
    lineTo(7.6152067f, 32.313824f)
    cubicTo(7.6152067f, 31.478745f, 8.305302f, 30.806458f, 9.162504f, 30.806458f)
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
// _0_0_3
brush = SolidColor(Color(255, 255, 255, 88))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.0478435f, 37.80557f)
    cubicTo(5.8479824f, 38.738255f, 5.649196f, 39.385227f, 6.522763f, 39.385227f)
    lineTo(42.83744f, 39.385227f)
    cubicTo(43.753494f, 39.385227f, 43.579185f, 38.64554f, 43.312706f, 37.746162f)
    lineTo(35.770172f, 10.471961f)
    lineTo(11.520336f, 10.471961f)
    lineTo(6.0478435f, 37.80557f)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.641802f, 29.928574f)
    lineTo(38.929626f, 29.928574f)
    cubicTo(39.76004f, 29.928574f, 40.42857f, 30.579851f, 40.42857f, 31.388836f)
    lineTo(41.714283f, 36.03974f)
    cubicTo(41.714283f, 36.848724f, 41.045753f, 37.500004f, 40.21534f, 37.500004f)
    lineTo(8.784659f, 37.500004f)
    cubicTo(7.954244f, 37.500004f, 7.2857146f, 36.848724f, 7.2857146f, 36.03974f)
    lineTo(8.142858f, 31.388836f)
    cubicTo(8.142858f, 30.579851f, 8.811387f, 29.928574f, 9.641802f, 29.928574f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(197, 197, 197, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(105, 105, 105, 255))
stroke = Stroke(width=0.99999964f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.641802f, 29.928574f)
    lineTo(38.929626f, 29.928574f)
    cubicTo(39.76004f, 29.928574f, 40.42857f, 30.579851f, 40.42857f, 31.388836f)
    lineTo(41.714283f, 36.03974f)
    cubicTo(41.714283f, 36.848724f, 41.045753f, 37.500004f, 40.21534f, 37.500004f)
    lineTo(8.784659f, 37.500004f)
    cubicTo(7.954244f, 37.500004f, 7.2857146f, 36.848724f, 7.2857146f, 36.03974f)
    lineTo(8.142858f, 31.388836f)
    cubicTo(8.142858f, 30.579851f, 8.811387f, 29.928574f, 9.641802f, 29.928574f)
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.641802f, 27.785717f)
    lineTo(38.929626f, 27.785717f)
    cubicTo(39.76004f, 27.785717f, 40.42857f, 28.436995f, 40.42857f, 29.245977f)
    lineTo(41.714283f, 33.896885f)
    cubicTo(41.714283f, 34.705868f, 41.045753f, 35.357147f, 40.21534f, 35.357147f)
    lineTo(8.784659f, 35.357147f)
    cubicTo(7.954244f, 35.357147f, 7.2857146f, 34.705868f, 7.2857146f, 33.896885f)
    lineTo(8.142858f, 29.245977f)
    cubicTo(8.142858f, 28.436995f, 8.811387f, 27.785717f, 9.641802f, 27.785717f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(226, 226, 226, 255), 1.0f to Color(159, 159, 159, 255), start = Offset(6.785715f, 31.571432f), end = Offset(42.214283f, 31.571432f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(105, 105, 105, 255))
stroke = Stroke(width=0.99999964f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.641802f, 27.785717f)
    lineTo(38.929626f, 27.785717f)
    cubicTo(39.76004f, 27.785717f, 40.42857f, 28.436995f, 40.42857f, 29.245977f)
    lineTo(41.714283f, 33.896885f)
    cubicTo(41.714283f, 34.705868f, 41.045753f, 35.357147f, 40.21534f, 35.357147f)
    lineTo(8.784659f, 35.357147f)
    cubicTo(7.954244f, 35.357147f, 7.2857146f, 34.705868f, 7.2857146f, 33.896885f)
    lineTo(8.142858f, 29.245977f)
    cubicTo(8.142858f, 28.436995f, 8.811387f, 27.785717f, 9.641802f, 27.785717f)
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
0.9425489902496338f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9425489902496338f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.22261899709701538f, 1.8558599948883057f, 0.0f, 1.0f)
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
    moveTo(10.891973f, 11.500004f)
    lineTo(6.571428f, 33.21429f)
    cubicTo(6.571428f, 33.21429f, 32.857143f, 33.21429f, 32.857143f, 33.21429f)
    cubicTo(45.441975f, 33.21429f, 48.085304f, 29.21429f, 48.085304f, 29.21429f)
    cubicTo(48.085304f, 29.21429f, 44.728165f, 28.035719f, 43.299595f, 23.071432f)
    cubicTo(43.299595f, 23.071432f, 40.23864f, 11.500004f, 40.23864f, 11.500004f)
    lineTo(10.891973f, 11.500004f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(231, 235, 235, 255), 0.5f to Color(230, 235, 235, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(13.357142f, 15.214289f), end = Offset(42.214283f, 29.21429f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(105, 105, 105, 255))
stroke = Stroke(width=1.0609524f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.891973f, 11.500004f)
    lineTo(6.571428f, 33.21429f)
    cubicTo(6.571428f, 33.21429f, 32.857143f, 33.21429f, 32.857143f, 33.21429f)
    cubicTo(45.441975f, 33.21429f, 48.085304f, 29.21429f, 48.085304f, 29.21429f)
    cubicTo(48.085304f, 29.21429f, 44.728165f, 28.035719f, 43.299595f, 23.071432f)
    cubicTo(43.299595f, 23.071432f, 40.23864f, 11.500004f, 40.23864f, 11.500004f)
    lineTo(10.891973f, 11.500004f)
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
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.060952f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.803734f, 12.474609f)
    lineTo(7.812257f, 32.23967f)
    cubicTo(7.812257f, 32.23967f, 24.956518f, 32.23967f, 32.23838f, 32.23967f)
    cubicTo(43.46502f, 32.23967f, 46.348812f, 29.388803f, 46.348812f, 29.388803f)
    cubicTo(46.348812f, 29.388803f, 43.35575f, 27.525963f, 42.05542f, 23.007305f)
    cubicTo(42.05542f, 23.007305f, 39.316856f, 12.546038f, 39.316856f, 12.546038f)
    lineTo(11.803734f, 12.474609f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.315699f, 7.4285707f)
    lineTo(36.494514f, 7.4285707f)
    cubicTo(37.367634f, 7.4285707f, 38.070538f, 8.097342f, 38.070538f, 8.928057f)
    lineTo(38.42768f, 11.071942f)
    cubicTo(38.42768f, 11.902657f, 37.724773f, 12.571428f, 36.851658f, 12.571428f)
    lineTo(10.958556f, 12.571428f)
    cubicTo(10.085439f, 12.571428f, 9.382532f, 11.902657f, 9.382532f, 11.071942f)
    lineTo(9.739675f, 8.928057f)
    cubicTo(9.739675f, 8.097342f, 10.442582f, 7.4285707f, 11.315699f, 7.4285707f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(24, 167, 43, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(58, 85, 60, 255))
stroke = Stroke(width=0.9999995f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.315699f, 7.4285707f)
    lineTo(36.494514f, 7.4285707f)
    cubicTo(37.367634f, 7.4285707f, 38.070538f, 8.097342f, 38.070538f, 8.928057f)
    lineTo(38.42768f, 11.071942f)
    cubicTo(38.42768f, 11.902657f, 37.724773f, 12.571428f, 36.851658f, 12.571428f)
    lineTo(10.958556f, 12.571428f)
    cubicTo(10.085439f, 12.571428f, 9.382532f, 11.902657f, 9.382532f, 11.071942f)
    lineTo(9.739675f, 8.928057f)
    cubicTo(9.739675f, 8.097342f, 10.442582f, 7.4285707f, 11.315699f, 7.4285707f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5257143f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.684211015701294f, 0.0f, 0.0f, 0.0f,
0.0f, 0.684211015701294f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
3.5601539611816406f, 2.2781970500946045f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.857143f, 9.928572f)
    cubicTo(12.859318f, 10.414878f, 12.601125f, 10.865177f, 12.18033f, 11.10896f)
    cubicTo(11.759535f, 11.352744f, 11.240465f, 11.352744f, 10.81967f, 11.10896f)
    cubicTo(10.398875f, 10.865177f, 10.140682f, 10.414878f, 10.142857f, 9.928572f)
    cubicTo(10.140682f, 9.4422655f, 10.398875f, 8.991966f, 10.81967f, 8.748183f)
    cubicTo(11.240465f, 8.504399f, 11.759535f, 8.504399f, 12.18033f, 8.748183f)
    cubicTo(12.601125f, 8.991966f, 12.859318f, 9.4422655f, 12.857143f, 9.928572f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(254, 254, 254, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.30285713f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.042053f, 8.601015f)
    lineTo(36.371307f, 8.601015f)
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
    moveTo(13.572528f, 15.756255f)
    lineTo(13.139995f, 18.005424f)
    lineTo(16.196558f, 18.005424f)
    lineTo(16.456078f, 15.756255f)
    lineTo(13.572528f, 15.756255f)
    close()
    moveTo(17.638332f, 15.756255f)
    lineTo(17.378815f, 18.005424f)
    lineTo(21.732973f, 18.005424f)
    lineTo(21.85485f, 15.756255f)
    lineTo(17.638332f, 15.756255f)
    close()
    moveTo(22.730284f, 15.756255f)
    lineTo(22.55727f, 18.005424f)
    lineTo(26.328188f, 18.005424f)
    lineTo(26.068668f, 15.756255f)
    lineTo(22.730284f, 15.756255f)
    close()
    moveTo(26.812992f, 15.756255f)
    lineTo(27.101347f, 18.005424f)
    lineTo(30.446264f, 18.005424f)
    lineTo(29.927225f, 15.756255f)
    lineTo(26.812992f, 15.756255f)
    close()
    moveTo(31.109482f, 15.756255f)
    lineTo(31.62852f, 18.005424f)
    lineTo(35.03111f, 18.005424f)
    cubicTo(34.761227f, 17.069338f, 34.367893f, 15.756255f, 34.367893f, 15.756255f)
    lineTo(31.109482f, 15.756255f)
    close()
    moveTo(12.995818f, 18.841654f)
    lineTo(12.447943f, 21.75404f)
    lineTo(15.73519f, 21.75404f)
    lineTo(16.081217f, 18.841654f)
    lineTo(12.995818f, 18.841654f)
    close()
    moveTo(17.292307f, 18.841654f)
    lineTo(16.946281f, 21.75404f)
    lineTo(21.473455f, 21.75404f)
    lineTo(21.675303f, 18.841654f)
    lineTo(17.292307f, 18.841654f)
    close()
    moveTo(22.4996f, 18.841654f)
    lineTo(22.29775f, 21.75404f)
    lineTo(26.818392f, 21.75404f)
    lineTo(26.44353f, 18.841654f)
    lineTo(22.4996f, 18.841654f)
    close()
    moveTo(27.216688f, 18.841654f)
    lineTo(27.59155f, 21.75404f)
    lineTo(31.253658f, 21.75404f)
    lineTo(30.619278f, 18.841654f)
    lineTo(27.216688f, 18.841654f)
    close()
    moveTo(31.801533f, 18.841654f)
    lineTo(32.46475f, 21.75404f)
    lineTo(36.098022f, 21.75404f)
    cubicTo(35.84072f, 20.874771f, 35.6657f, 20.242601f, 35.26179f, 18.841654f)
    lineTo(31.801533f, 18.841654f)
    close()
    moveTo(12.27493f, 22.590267f)
    lineTo(11.727056f, 25.531488f)
    cubicTo(12.692202f, 25.541471f, 14.135511f, 25.552532f, 15.302658f, 25.560326f)
    lineTo(15.648684f, 22.590267f)
    lineTo(12.27493f, 22.590267f)
    close()
    moveTo(16.830938f, 22.590267f)
    lineTo(16.484913f, 25.560326f)
    cubicTo(18.083767f, 25.569721f, 19.443066f, 25.582628f, 21.1851f, 25.58916f)
    lineTo(21.415785f, 22.590267f)
    lineTo(16.830938f, 22.590267f)
    close()
    moveTo(22.211245f, 22.590267f)
    lineTo(22.009396f, 25.617996f)
    cubicTo(23.807367f, 25.622683f, 25.555758f, 25.598331f, 27.308594f, 25.58916f)
    lineTo(26.933733f, 22.590267f)
    lineTo(22.211245f, 22.590267f)
    close()
    moveTo(27.706892f, 22.590267f)
    lineTo(28.081755f, 25.58916f)
    cubicTo(29.510363f, 25.578526f, 30.82974f, 25.551765f, 32.118725f, 25.531488f)
    lineTo(31.455507f, 22.590267f)
    lineTo(27.706892f, 22.590267f)
    close()
    moveTo(32.637764f, 22.590267f)
    lineTo(33.30098f, 25.531488f)
    cubicTo(34.902946f, 25.49989f, 36.29719f, 25.44944f, 37.366783f, 25.387312f)
    cubicTo(37.09973f, 24.799839f, 36.82402f, 24.149448f, 36.588226f, 23.397661f)
    cubicTo(36.588226f, 23.397661f, 36.396816f, 22.724178f, 36.35754f, 22.590267f)
    lineTo(32.637764f, 22.590267f)
    close()
    moveTo(37.82815f, 26.36772f)
    cubicTo(37.62311f, 26.374735f, 37.466965f, 26.390013f, 37.251442f, 26.396555f)
    cubicTo(36.906914f, 26.46833f, 35.44579f, 26.492613f, 33.531662f, 26.511896f)
    lineTo(34.281387f, 29.856813f)
    cubicTo(38.540436f, 29.392569f, 39.183422f, 28.41504f, 39.183422f, 28.41504f)
    cubicTo(39.183422f, 28.41504f, 38.55028f, 27.712069f, 37.82815f, 26.36772f)
    close()
    moveTo(11.554043f, 26.396555f)
    lineTo(10.861991f, 30.029827f)
    cubicTo(10.861991f, 30.029827f, 13.545705f, 30.026365f, 14.783619f, 30.029827f)
    lineTo(15.187316f, 26.42539f)
    cubicTo(13.982562f, 26.414762f, 12.332649f, 26.406454f, 11.554043f, 26.396555f)
    close()
    moveTo(16.398407f, 26.454226f)
    lineTo(15.965874f, 30.058662f)
    cubicTo(17.830576f, 30.063938f, 18.425137f, 30.051216f, 20.867908f, 30.058662f)
    lineTo(21.127428f, 26.48306f)
    cubicTo(19.377943f, 26.467201f, 18.076532f, 26.468424f, 16.398407f, 26.454226f)
    close()
    moveTo(21.92289f, 26.511896f)
    lineTo(21.66337f, 30.058662f)
    cubicTo(24.648954f, 30.067766f, 26.570898f, 30.082617f, 27.731894f, 30.087498f)
    lineTo(27.423937f, 26.540731f)
    cubicTo(25.58264f, 26.53404f, 23.872128f, 26.528198f, 21.92289f, 26.511896f)
    close()
    moveTo(32.32057f, 26.511896f)
    cubicTo(31.019299f, 26.519527f, 29.755219f, 26.544062f, 28.197096f, 26.540731f)
    lineTo(28.658464f, 30.087498f)
    cubicTo(28.679554f, 30.08759f, 29.06216f, 30.087498f, 29.06216f, 30.087498f)
    cubicTo(30.677769f, 30.087498f, 31.799461f, 30.030746f, 32.94572f, 29.943321f)
    lineTo(32.32057f, 26.511896f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(167, 167, 167, 255))
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
            return 4.322630405426025
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 6.928570747375488
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 41.5482063293457
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 36.81904220581055
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

