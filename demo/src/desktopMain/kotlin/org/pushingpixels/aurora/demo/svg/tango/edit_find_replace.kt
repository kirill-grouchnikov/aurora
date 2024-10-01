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
class edit_find_replace : Painter() {
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.02083333395421505f, 0.0f, 0.0f, 0.0f,
0.0f, 0.02083333395421505f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.0f, -0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 0.7836257f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.7071080207824707f, 0.0f, 1.0f)
))}){
// _0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.128307f, 42.07798f)
    cubicTo(40.15366f, 43.693268f, 37.143654f, 45.188953f, 32.238045f, 45.998688f)
    cubicTo(27.332438f, 46.808426f, 21.281149f, 46.808426f, 16.37554f, 45.998688f)
    cubicTo(11.469932f, 45.188953f, 8.459925f, 43.693268f, 8.485279f, 42.07798f)
    cubicTo(8.459925f, 40.462692f, 11.469932f, 38.967007f, 16.37554f, 38.157272f)
    cubicTo(21.281149f, 37.347534f, 27.332438f, 37.347534f, 32.238045f, 38.157272f)
    cubicTo(37.143654f, 38.967007f, 40.15366f, 40.462692f, 40.128307f, 42.07798f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.306795f, 42.07797f), radius = 15.821516f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
brush = SolidColor(Color(0, 0, 0, 4))
stroke = Stroke(width=0.9885531f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.505723f, 5.4942765f)
    lineTo(11.505723f, 43.40087f)
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
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0035840272903442f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0019429922103882f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.1272200047969818f, -0.15353399515151978f, 0.0f, 1.0f)
))}){
// _0_2_0
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2898895740509033f, radiusY = 2.2936408519744873f))
brush = Brush.radialGradient(0.0f to Color(250, 250, 250, 255), 1.0f to Color(187, 187, 187, 255), center = Offset(32.62476f, 37.206844f), radius = 83.28285f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.radialGradient(0.0f to Color(163, 163, 163, 255), 1.0f to Color(76, 76, 76, 255), center = Offset(11.898f, 4.525653f), radius = 36.553967f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99724436f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2898895740509033f, radiusY = 2.2936408519744873f))
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
1.0035840272903442f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0019429922103882f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.1272200047969818f, -0.15353399515151978f, 0.0f, 1.0f)
))}){
// _0_2_1
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(248, 248, 248, 255), center = Offset(11.238739f, 8.152492f), radius = 36.948036f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99724436f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.666053771972656f, top = 4.583946228027344f, right = 40.44194030761719f, bottom = 43.530330657958984f,radiusX = 0.29703250527381897f, radiusY = 0.29751908779144287f))
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
0.6464470028877258f, -0.03798932954668999f, 0.0f, 1.0f)
))}){
// _0_2_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.22970299422740936f, 0.0f, 0.0f, 0.0f,
0.0f, 0.22970299422740936f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
4.967081069946289f, 4.244972229003906f, 0.0f, 1.0f)
))}){
// _0_2_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.428f, 113.07f)
    cubicTo(23.428f, 115.043f, 21.828f, 116.642f, 19.855f, 116.642f)
    cubicTo(17.881f, 116.642f, 16.282f, 115.042f, 16.282f, 113.07f)
    cubicTo(16.282f, 111.096f, 17.882f, 109.497f, 19.855f, 109.497f)
    cubicTo(21.828f, 109.497f, 23.428f, 111.097f, 23.428f, 113.07f)
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
// _0_2_2_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.428f, 63.07f)
    cubicTo(23.428f, 65.043f, 21.828f, 66.643f, 19.855f, 66.643f)
    cubicTo(17.881f, 66.643f, 16.282f, 65.043f, 16.282f, 63.07f)
    cubicTo(16.282f, 61.096f, 17.882f, 59.497f, 19.855f, 59.497f)
    cubicTo(21.828f, 59.497f, 23.428f, 61.097f, 23.428f, 63.07f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
// _0_2_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.995011f, 29.952326f)
    cubicTo(9.995011f, 30.405529f, 9.627486f, 30.772825f, 9.174282f, 30.772825f)
    cubicTo(8.720848f, 30.772825f, 8.353553f, 30.405302f, 8.353553f, 29.952326f)
    cubicTo(8.353553f, 29.498892f, 8.721078f, 29.131598f, 9.174282f, 29.131598f)
    cubicTo(9.627486f, 29.131598f, 9.99501f, 29.499123f, 9.99501f, 29.952326f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(240, 240, 240, 255), 1.0f to Color(154, 154, 154, 255), center = Offset(9.412507f, 30.296513f), radius = 1.2073193f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.995011f, 18.467176f)
    cubicTo(9.995011f, 18.92038f, 9.627486f, 19.287905f, 9.174282f, 19.287905f)
    cubicTo(8.720848f, 19.287905f, 8.353553f, 18.92038f, 8.353553f, 18.467176f)
    cubicTo(8.353553f, 18.013742f, 8.721078f, 17.646448f, 9.174282f, 17.646448f)
    cubicTo(9.627486f, 17.646448f, 9.99501f, 18.013973f, 9.99501f, 18.467176f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(240, 240, 240, 255), 1.0f to Color(154, 154, 154, 255), center = Offset(9.412507f, 18.811249f), radius = 1.2075491f, tileMode = TileMode.Clamp)
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
// _0_2_3
brush = SolidColor(Color(255, 255, 255, 52))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.5f, 5.0205154f)
    lineTo(12.5f, 43.038227f)
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
0.9090909957885742f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.3636279106140137f, 0.0f, 0.0f, 1.0f)
))}){
// _0_2_4
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_0
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 9.0f, right = 37.0000057220459f, bottom = 10.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_1
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 11.0f, right = 37.0000057220459f, bottom = 12.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_2
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 13.0f, right = 37.0000057220459f, bottom = 14.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_3
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 15.0f, right = 37.0000057220459f, bottom = 16.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_4
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 17.0f, right = 37.0000057220459f, bottom = 18.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_5
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 19.0f, right = 37.0000057220459f, bottom = 20.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_6
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 21.0f, right = 37.0000057220459f, bottom = 22.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_7
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 23.0f, right = 37.0000057220459f, bottom = 24.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_8
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 25.0f, right = 24.89999771118164f, bottom = 26.0f,radiusX = 0.1364084780216217f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_9
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 29.0f, right = 36.999996185302734f, bottom = 30.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_10
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 31.0f, right = 36.999996185302734f, bottom = 32.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_11
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 33.0f, right = 36.999996185302734f, bottom = 34.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_12
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 35.0f, right = 36.999996185302734f, bottom = 36.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4_13
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 37.0f, right = 30.40000629425049f, bottom = 38.0f,radiusX = 0.21219104528427124f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17112301f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9624220132827759f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0113660097122192f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.130765914916992f, -7.903209209442139f, 0.0f, 1.0f)
))}){
// _0_2_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.65864f, 37.967922f)
    cubicTo(40.685127f, 39.37734f, 37.540596f, 40.6824f, 32.415745f, 41.38893f)
    cubicTo(27.290888f, 42.095467f, 20.969152f, 42.095467f, 15.844297f, 41.38893f)
    cubicTo(10.719443f, 40.6824f, 7.574911f, 39.37734f, 7.6013985f, 37.967922f)
    cubicTo(7.574911f, 36.558506f, 10.719443f, 35.253445f, 15.844297f, 34.546913f)
    cubicTo(20.969152f, 33.840378f, 27.290888f, 33.840378f, 32.415745f, 34.546913f)
    cubicTo(37.540596f, 35.253445f, 40.685127f, 36.558506f, 40.65864f, 37.967922f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.130018f, 37.96793f), radius = 16.52862f, tileMode = TileMode.Clamp)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.0003249645233154f, -0.8508800268173218f, 0.0f, 1.0f)
))}){
// _0_2_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_6_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.6432769894599915f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6432769894599915f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
31.49802017211914f, 4.828703880310059f, 0.0f, 1.0f)
))}){
// _0_2_6_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.48844f, 3.1435547f, 3.8827686f, 9.749226f, 3.8827686f, 17.888355f)
    cubicTo(3.8827686f, 26.027485f, 10.48844f, 32.633156f, 18.62757f, 32.633156f)
    cubicTo(22.107124f, 32.633156f, 25.17857f, 31.248762f, 27.701292f, 29.230509f)
    cubicTo(27.495914f, 30.23739f, 27.623257f, 31.265877f, 28.457436f, 31.990433f)
    lineTo(39.42152f, 41.51784f)
    cubicTo(40.654938f, 42.58917f, 42.508984f, 42.448803f, 43.58031f, 41.215385f)
    cubicTo(44.651638f, 39.981968f, 44.51127f, 38.127922f, 43.277855f, 37.056595f)
    lineTo(32.31377f, 27.529182f)
    cubicTo(31.642242f, 26.945904f, 30.820892f, 26.773214f, 30.007532f, 26.886461f)
    cubicTo(31.994232f, 24.374039f, 33.37237f, 21.337658f, 33.37237f, 17.888351f)
    cubicTo(33.37237f, 9.749222f, 26.7667f, 3.14355f, 18.627571f, 3.14355f)
    close()
    moveTo(18.551954f, 4.369738f)
    cubicTo(26.191414f, 4.369738f, 31.843729f, 9.158689f, 31.843729f, 17.661512f)
    cubicTo(31.843729f, 26.336624f, 26.027039f, 30.953287f, 18.551954f, 30.953287f)
    cubicTo(11.249005f, 30.953287f, 5.2601805f, 25.475195f, 5.2601805f, 17.661512f)
    cubicTo(5.2601805f, 9.677405f, 11.084819f, 4.3697376f, 18.551954f, 4.3697376f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(138, 138, 138, 255), 1.0f to Color(72, 72, 72, 255), start = Offset(20.817749f, 26.580296f), end = Offset(16.848125f, 30.557772f), tileMode = TileMode.Clamp)
stroke = Stroke(width=3.1090834f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.48844f, 3.1435547f, 3.8827686f, 9.749226f, 3.8827686f, 17.888355f)
    cubicTo(3.8827686f, 26.027485f, 10.48844f, 32.633156f, 18.62757f, 32.633156f)
    cubicTo(22.107124f, 32.633156f, 25.17857f, 31.248762f, 27.701292f, 29.230509f)
    cubicTo(27.495914f, 30.23739f, 27.623257f, 31.265877f, 28.457436f, 31.990433f)
    lineTo(39.42152f, 41.51784f)
    cubicTo(40.654938f, 42.58917f, 42.508984f, 42.448803f, 43.58031f, 41.215385f)
    cubicTo(44.651638f, 39.981968f, 44.51127f, 38.127922f, 43.277855f, 37.056595f)
    lineTo(32.31377f, 27.529182f)
    cubicTo(31.642242f, 26.945904f, 30.820892f, 26.773214f, 30.007532f, 26.886461f)
    cubicTo(31.994232f, 24.374039f, 33.37237f, 21.337658f, 33.37237f, 17.888351f)
    cubicTo(33.37237f, 9.749222f, 26.7667f, 3.14355f, 18.627571f, 3.14355f)
    close()
    moveTo(18.551954f, 4.369738f)
    cubicTo(26.191414f, 4.369738f, 31.843729f, 9.158689f, 31.843729f, 17.661512f)
    cubicTo(31.843729f, 26.336624f, 26.027039f, 30.953287f, 18.551954f, 30.953287f)
    cubicTo(11.249005f, 30.953287f, 5.2601805f, 25.475195f, 5.2601805f, 17.661512f)
    cubicTo(5.2601805f, 9.677405f, 11.084819f, 4.3697376f, 18.551954f, 4.3697376f)
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
-0.6432769894599915f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6432769894599915f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
31.49802017211914f, 4.828703880310059f, 0.0f, 1.0f)
))}){
// _0_2_6_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.602905f, 3.0803552f)
    cubicTo(10.437466f, 3.0803552f, 3.810441f, 9.707379f, 3.810441f, 17.87282f)
    cubicTo(3.810441f, 26.03826f, 10.437466f, 32.665283f, 18.602905f, 32.665283f)
    cubicTo(22.093708f, 32.665283f, 25.175083f, 31.276417f, 27.70596f, 29.251638f)
    cubicTo(27.499918f, 30.261774f, 27.627672f, 31.293585f, 28.464546f, 32.020485f)
    lineTo(39.464073f, 41.57869f)
    cubicTo(40.701477f, 42.65348f, 42.561516f, 42.51266f, 43.636307f, 41.275253f)
    cubicTo(44.711098f, 40.03785f, 44.570274f, 38.17781f, 43.33287f, 37.10302f)
    lineTo(32.333344f, 27.544811f)
    cubicTo(31.659645f, 26.959648f, 30.83564f, 26.786398f, 30.01965f, 26.900013f)
    cubicTo(32.01277f, 24.379469f, 33.395367f, 21.333273f, 33.395367f, 17.872816f)
    cubicTo(33.395367f, 9.7073765f, 26.768343f, 3.0803518f, 18.602901f, 3.0803518f)
    close()
    moveTo(18.527046f, 6.266424f)
    cubicTo(24.808155f, 6.266424f, 29.905865f, 11.364135f, 29.905865f, 17.645243f)
    cubicTo(29.905865f, 23.92635f, 24.808155f, 29.02406f, 18.527046f, 29.02406f)
    cubicTo(12.245937f, 29.02406f, 7.1482277f, 23.92635f, 7.1482277f, 17.64524f)
    cubicTo(7.1482277f, 11.364133f, 12.245938f, 6.2664223f, 18.527046f, 6.2664223f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
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
-0.6432769894599915f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6432769894599915f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
31.49802017211914f, 4.828703880310059f, 0.0f, 1.0f)
))}){
// _0_2_6_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.507004f, 41.57769f)
    cubicTo(39.02833f, 39.304504f, 40.904335f, 36.76627f, 43.091057f, 36.789314f)
    cubicTo(43.091057f, 36.789314f, 32.33069f, 27.531204f, 32.33069f, 27.531204f)
    cubicTo(29.385897f, 27.474499f, 28.061186f, 29.80382f, 28.553875f, 32.131126f)
    lineTo(39.507004f, 41.57769f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(125, 125, 125, 255), 0.5f to Color(177, 177, 177, 255), 1.0f to Color(104, 104, 104, 255), start = Offset(14.244308f, 36.443268f), end = Offset(10.824417f, 32.650097f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.8013579845428467f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8013579845428467f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
33.70146942138672f, 0.8551589846611023f, 0.0f, 1.0f)
))}){
// _0_2_6_1
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 255, 255, 56), 1.0f to Color(255, 255, 255, 255), start = Offset(25.885595f, 13.602121f), end = Offset(26.677376f, 25.74347f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.2478822f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.549437f, 18.920233f)
    cubicTo(28.567142f, 22.879269f, 26.465181f, 26.545166f, 23.039478f, 28.529814f)
    cubicTo(19.613773f, 30.51446f, 15.388013f, 30.51446f, 11.962308f, 28.529814f)
    cubicTo(8.536603f, 26.545166f, 6.4346433f, 22.879269f, 6.4523487f, 18.920233f)
    cubicTo(6.4346433f, 14.961198f, 8.536603f, 11.295299f, 11.962308f, 9.310653f)
    cubicTo(15.388013f, 7.326006f, 19.613773f, 7.326006f, 23.039478f, 9.310653f)
    cubicTo(26.465181f, 11.295299f, 28.567142f, 14.961198f, 28.549437f, 18.920233f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.43315506f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.48437899351119995f, 0.42329999804496765f, 0.0f, 0.0f,
0.4174230098724365f, 0.4894520044326782f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
31.49802017211914f, 4.828703880310059f, 0.0f, 1.0f)
))}){
// _0_2_6_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.5545894f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 40.37333679199219f, top = 0.14086054265499115f, right = 59.421775817871094f, bottom = 4.581338867545128f,radiusX = 6.64305305480957f, radiusY = 4.440478324890137f))
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
-0.8996970057487488f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8996970057487488f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
35.50199890136719f, -0.5098260045051575f, 0.0f, 1.0f)
))}){
// _0_2_6_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424264f, 25.704697f)
    cubicTo(10.848134f, 24.212242f, 9.267461f, 21.455486f, 9.280775f, 18.478292f)
    cubicTo(9.267461f, 15.501098f, 10.848134f, 12.744342f, 13.424264f, 11.251888f)
    cubicTo(16.000395f, 9.759434f, 19.178165f, 9.759434f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744342f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(114, 159, 207, 53), 1.0f to Color(114, 159, 207, 172), center = Offset(25.111073f, 21.817987f), radius = 8.308506f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(48, 99, 163, 255))
stroke = Stroke(width=1.1114874f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424264f, 25.704697f)
    cubicTo(10.848134f, 24.212242f, 9.267461f, 21.455486f, 9.280775f, 18.478292f)
    cubicTo(9.267461f, 15.501098f, 10.848134f, 12.744342f, 13.424264f, 11.251888f)
    cubicTo(16.000395f, 9.759434f, 19.178165f, 9.759434f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744342f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8342246f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6432769894599915f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6432769894599915f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
7.85593318939209f, 4.828703880310059f, 0.0f, 1.0f)
))}){
// _0_2_6_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.156916f, 7.3966937f)
    cubicTo(12.949326f, 7.3966937f, 8.732368f, 11.613651f, 8.732368f, 16.821241f)
    cubicTo(8.732368f, 18.325216f, 9.152676f, 19.709015f, 9.77954f, 20.971144f)
    cubicTo(11.03192f, 21.432756f, 12.362297f, 21.746826f, 13.774307f, 21.746826f)
    cubicTo(19.945263f, 21.746826f, 24.873589f, 16.88519f, 25.254414f, 10.809697f)
    cubicTo(23.523449f, 8.764166f, 21.044374f, 7.396693f, 18.156916f, 7.396693f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 63), center = Offset(14.909132f, 10.512936f), radius = 17.259417f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.2f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.61661297082901f, 0.0f, 0.0f, 0.0f,
0.0f, 0.2935769855976105f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
12.738160133361816f, 29.128480911254883f, 0.0f, 1.0f)
))}){
// _0_2_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.125f, 40.4375f)
    cubicTo(43.15635f, 42.878628f, 39.434628f, 45.13901f, 33.36909f, 46.36274f)
    cubicTo(27.303555f, 47.586468f, 19.821445f, 47.586468f, 13.755908f, 46.36274f)
    cubicTo(7.690371f, 45.13901f, 3.9686508f, 42.878628f, 4.0f, 40.4375f)
    cubicTo(3.9686508f, 37.996372f, 7.690371f, 35.73599f, 13.755908f, 34.51226f)
    cubicTo(19.821445f, 33.288532f, 27.303555f, 33.288532f, 33.36909f, 34.51226f)
    cubicTo(39.434628f, 35.73599f, 43.15635f, 37.996372f, 43.125f, 40.4375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(23.5625f, 40.437508f), radius = 19.5625f, tileMode = TileMode.Clamp)
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
1.0336990356445312f, -0.276978999376297f, 0.0f, 0.0f,
0.276978999376297f, 1.0336990356445312f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-9.427308082580566f, 13.333688735961914f, 0.0f, 1.0f)
))}){
// _0_2_8
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_8_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.34116f, 32.5f)
    lineTo(22.96616f, 26.875f)
    lineTo(43.059906f, 17.125f)
    cubicTo(46.309906f, 15.875f, 48.247406f, 20.5f, 45.372406f, 22.125f)
    lineTo(25.341158f, 31.5f)
    lineTo(17.341158f, 32.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(203, 144, 34, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(92, 65, 12, 255))
stroke = Stroke(width=0.9344358f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.34116f, 32.5f)
    lineTo(22.96616f, 26.875f)
    lineTo(43.059906f, 17.125f)
    cubicTo(46.309906f, 15.875f, 48.247406f, 20.5f, 45.372406f, 22.125f)
    lineTo(25.341158f, 31.5f)
    lineTo(17.341158f, 32.5f)
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
// _0_2_8_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.330708f, 20.0f)
    cubicTo(38.330708f, 20.0f, 39.768208f, 20.09375f, 40.330708f, 21.34375f)
    cubicTo(40.910202f, 22.631512f, 40.330708f, 24.0f, 40.330708f, 24.0f)
    lineTo(45.361958f, 21.53125f)
    cubicTo(45.361958f, 21.53125f, 46.813988f, 20.649883f, 46.018208f, 18.6875f)
    cubicTo(45.233295f, 16.751923f, 43.330708f, 17.53125f, 43.330708f, 17.53125f)
    lineTo(38.330708f, 20.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 209, 209, 255), 0.5f to Color(255, 29, 29, 255), 1.0f to Color(111, 0, 0, 255), start = Offset(43.236958f, 17.376184f), end = Offset(45.319042f, 22.250591f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_8_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.330708f, 20.0f)
    cubicTo(38.330708f, 20.0f, 39.768208f, 20.09375f, 40.330708f, 21.34375f)
    cubicTo(40.910202f, 22.631512f, 40.330708f, 24.0f, 40.330708f, 24.0f)
    lineTo(42.330708f, 23.0f)
    cubicTo(42.330708f, 23.0f, 43.157738f, 21.681133f, 42.549458f, 20.3125f)
    cubicTo(41.924458f, 18.90625f, 40.330708f, 19.0f, 40.330708f, 19.0f)
    lineTo(38.330708f, 20.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(193, 193, 193, 255), 1.0f to Color(172, 172, 172, 255), start = Offset(40.330708f, 19.8125f), end = Offset(42.018208f, 22.625f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_8_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.768208f, 31.78125f)
    lineTo(23.268208f, 27.28125f)
    cubicTo(24.768208f, 28.09375f, 25.549458f, 29.4375f, 25.143208f, 31.0f)
    lineTo(18.768208f, 31.78125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(231, 226, 184, 255), 1.0f to Color(231, 226, 184, 0), center = Offset(23.384048f, 28.21873f), radius = 9.474846f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_8_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.111958f, 30.375f)
    lineTo(18.486958f, 31.96875f)
    lineTo(20.830708f, 31.65625f)
    cubicTo(21.049458f, 30.9375f, 20.643208f, 30.59375f, 20.111958f, 30.375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(201, 201, 201, 255), start = Offset(19.893208f, 31.171875f), end = Offset(19.689047f, 30.828125f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_8_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.268208f, 27.25f)
    lineTo(24.830708f, 28.5f)
    lineTo(40.21805f, 21.18133f)
    cubicTo(39.773617f, 20.325285f, 38.97628f, 20.096733f, 38.31467f, 20.019068f)
    lineTo(23.26821f, 27.25f)
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
// _0_2_8_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.143208f, 31.0625f)
    lineTo(25.330708f, 30.3125f)
    lineTo(40.5618f, 23.1829f)
    cubicTo(40.5618f, 23.1829f, 40.451637f, 23.796526f, 40.34592f, 23.93225f)
    lineTo(25.14321f, 31.0625f)
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
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
            return 0.003850573441013694
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.0625084638595581
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 0.9961494207382202
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 0.9231811165809631
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

