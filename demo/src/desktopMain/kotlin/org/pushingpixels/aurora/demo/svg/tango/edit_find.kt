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
class edit_find : Painter() {
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
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.128307f, 42.07798f)
    cubicTo(40.15366f, 43.693268f, 37.143654f, 45.188953f, 32.23805f, 45.998688f)
    cubicTo(27.332438f, 46.808426f, 21.28115f, 46.808426f, 16.375542f, 45.998688f)
    cubicTo(11.469933f, 45.188953f, 8.459927f, 43.693268f, 8.485281f, 42.07798f)
    cubicTo(8.459927f, 40.462692f, 11.469933f, 38.967007f, 16.375542f, 38.157272f)
    cubicTo(21.28115f, 37.347534f, 27.332438f, 37.347534f, 32.23805f, 38.157272f)
    cubicTo(37.143654f, 38.967007f, 40.15366f, 40.462692f, 40.128307f, 42.07798f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.306795f, 42.07797f), radius = 15.821516f, tileMode = TileMode.Clamp)
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
// _0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_0
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2980971336364746f, radiusY = 2.2980971336364746f))
brush = Brush.radialGradient(0.0f to Color(250, 250, 250, 255), 1.0f to Color(187, 187, 187, 255), center = Offset(32.62476f, 37.206844f), radius = 83.28285f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.radialGradient(0.0f to Color(163, 163, 163, 255), 1.0f to Color(76, 76, 76, 255), center = Offset(11.898f, 4.525653f), radius = 36.553967f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2980971336364746f, radiusY = 2.2980971336364746f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(248, 248, 248, 255), center = Offset(11.238739f, 8.152492f), radius = 36.948036f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.666053771972656f, top = 4.583946228027344f, right = 40.44194030761719f, bottom = 43.530330657958984f,radiusX = 0.2980971336364746f, radiusY = 0.2980971336364746f))
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.6464470028877258f, -0.03798932954668999f, 0.0f, 1.0f)
))}){
// _0_1_2
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
// _0_1_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2_0_0
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
// _0_1_2_0_1
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
// _0_1_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.995011f, 29.952326f)
    cubicTo(9.995011f, 30.40553f, 9.627486f, 30.772825f, 9.174282f, 30.772825f)
    cubicTo(8.720848f, 30.772825f, 8.353554f, 30.4053f, 8.353554f, 29.952326f)
    cubicTo(8.353554f, 29.498892f, 8.721078f, 29.131598f, 9.174282f, 29.131598f)
    cubicTo(9.627486f, 29.131598f, 9.995011f, 29.499123f, 9.995011f, 29.952326f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(240, 240, 240, 255), 1.0f to Color(154, 154, 154, 255), center = Offset(9.412507f, 30.296513f), radius = 1.2073183f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.995011f, 18.467176f)
    cubicTo(9.995011f, 18.92038f, 9.627486f, 19.287905f, 9.174282f, 19.287905f)
    cubicTo(8.720848f, 19.287905f, 8.353554f, 18.92038f, 8.353554f, 18.467176f)
    cubicTo(8.353554f, 18.013742f, 8.721078f, 17.646446f, 9.174282f, 17.646446f)
    cubicTo(9.627486f, 17.646446f, 9.995011f, 18.013971f, 9.995011f, 18.467176f)
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
// _0_1_3
brush = SolidColor(Color(0, 0, 0, 4))
stroke = Stroke(width=0.9885531f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.505723f, 5.4942765f)
    lineTo(11.505723f, 43.400867f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_4
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
0.9090909957885742f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.3636279106140137f, 0.0f, 0.0f, 1.0f)
))}){
// _0_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_0
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 9.0f, right = 37.0000057220459f, bottom = 10.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 11.0f, right = 37.0000057220459f, bottom = 12.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_2
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 13.0f, right = 37.0000057220459f, bottom = 14.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_3
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 15.0f, right = 37.0000057220459f, bottom = 16.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_4
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 17.0f, right = 37.0000057220459f, bottom = 18.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_5
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 19.0f, right = 37.0000057220459f, bottom = 20.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_6
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 21.0f, right = 37.0000057220459f, bottom = 22.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_7
shape = Outline.Rounded(roundRect = RoundRect(left = 15.000001907348633f, top = 23.0f, right = 37.0000057220459f, bottom = 24.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_8
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 25.0f, right = 24.89999771118164f, bottom = 26.0f,radiusX = 0.1364084780216217f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_9
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 29.0f, right = 36.999996185302734f, bottom = 30.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_10
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 31.0f, right = 36.999996185302734f, bottom = 32.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_11
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 33.0f, right = 36.999996185302734f, bottom = 34.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_12
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 35.0f, right = 36.999996185302734f, bottom = 36.0f,radiusX = 0.3031298518180847f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_13
shape = Outline.Rounded(roundRect = RoundRect(left = 14.999992370605469f, top = 37.0f, right = 30.40000629425049f, bottom = 38.0f,radiusX = 0.21219104528427124f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
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
0.6653770208358765f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6653770208358765f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
15.9864501953125f, 17.908349990844727f, 0.0f, 1.0f)
))}){
// _0_2_1
alphaStack.add(0, alpha)
alpha *= 0.17112298f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.446431040763855f, 0.0f, 0.0f, 0.0f,
0.0f, 1.5199899673461914f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.974530220031738f, -17.751680374145508f, 0.0f, 1.0f)
))}){
// _0_2_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.65864f, 37.967922f)
    cubicTo(40.685127f, 39.37734f, 37.540596f, 40.6824f, 32.41574f, 41.38893f)
    cubicTo(27.290888f, 42.095467f, 20.96915f, 42.095467f, 15.8442955f, 41.38893f)
    cubicTo(10.719441f, 40.6824f, 7.574909f, 39.37734f, 7.6013966f, 37.967922f)
    cubicTo(7.574909f, 36.558506f, 10.719441f, 35.253445f, 15.8442955f, 34.546913f)
    cubicTo(20.96915f, 33.840378f, 27.290888f, 33.840378f, 32.41574f, 34.546913f)
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
// _0_2_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.488439f, 3.1435547f, 3.8827682f, 9.749226f, 3.8827682f, 17.888355f)
    cubicTo(3.8827682f, 26.027487f, 10.488439f, 32.63316f, 18.62757f, 32.63316f)
    cubicTo(22.107124f, 32.63316f, 25.17857f, 31.248766f, 27.701292f, 29.23051f)
    cubicTo(27.495914f, 30.237392f, 27.623257f, 31.265879f, 28.457436f, 31.990437f)
    lineTo(39.42152f, 41.517845f)
    cubicTo(40.654938f, 42.589176f, 42.508984f, 42.448807f, 43.58031f, 41.21539f)
    cubicTo(44.651638f, 39.98197f, 44.51127f, 38.127926f, 43.27785f, 37.0566f)
    lineTo(32.31377f, 27.529188f)
    cubicTo(31.642242f, 26.94591f, 30.82089f, 26.773218f, 30.00753f, 26.886465f)
    cubicTo(31.99423f, 24.374044f, 33.37237f, 21.337664f, 33.37237f, 17.888355f)
    cubicTo(33.37237f, 9.749226f, 26.766699f, 3.1435547f, 18.62757f, 3.1435547f)
    close()
    moveTo(18.551954f, 4.369738f)
    cubicTo(26.191414f, 4.369738f, 31.843729f, 9.158689f, 31.843729f, 17.661512f)
    cubicTo(31.843729f, 26.336626f, 26.027039f, 30.953287f, 18.551954f, 30.953287f)
    cubicTo(11.249005f, 30.953287f, 5.2601805f, 25.475197f, 5.2601805f, 17.661512f)
    cubicTo(5.2601805f, 9.677406f, 11.084819f, 4.369738f, 18.551954f, 4.369738f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(138, 138, 138, 255), 1.0f to Color(72, 72, 72, 255), start = Offset(27.36634f, 26.580296f), end = Offset(31.335964f, 30.557772f), tileMode = TileMode.Clamp)
stroke = Stroke(width=3.0058157f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.62757f, 3.1435547f)
    cubicTo(10.488439f, 3.1435547f, 3.8827682f, 9.749226f, 3.8827682f, 17.888355f)
    cubicTo(3.8827682f, 26.027487f, 10.488439f, 32.63316f, 18.62757f, 32.63316f)
    cubicTo(22.107124f, 32.63316f, 25.17857f, 31.248766f, 27.701292f, 29.23051f)
    cubicTo(27.495914f, 30.237392f, 27.623257f, 31.265879f, 28.457436f, 31.990437f)
    lineTo(39.42152f, 41.517845f)
    cubicTo(40.654938f, 42.589176f, 42.508984f, 42.448807f, 43.58031f, 41.21539f)
    cubicTo(44.651638f, 39.98197f, 44.51127f, 38.127926f, 43.27785f, 37.0566f)
    lineTo(32.31377f, 27.529188f)
    cubicTo(31.642242f, 26.94591f, 30.82089f, 26.773218f, 30.00753f, 26.886465f)
    cubicTo(31.99423f, 24.374044f, 33.37237f, 21.337664f, 33.37237f, 17.888355f)
    cubicTo(33.37237f, 9.749226f, 26.766699f, 3.1435547f, 18.62757f, 3.1435547f)
    close()
    moveTo(18.551954f, 4.369738f)
    cubicTo(26.191414f, 4.369738f, 31.843729f, 9.158689f, 31.843729f, 17.661512f)
    cubicTo(31.843729f, 26.336626f, 26.027039f, 30.953287f, 18.551954f, 30.953287f)
    cubicTo(11.249005f, 30.953287f, 5.2601805f, 25.475197f, 5.2601805f, 17.661512f)
    cubicTo(5.2601805f, 9.677406f, 11.084819f, 4.369738f, 18.551954f, 4.369738f)
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
// _0_2_1_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.602905f, 3.0803552f)
    cubicTo(10.437465f, 3.0803552f, 3.8104408f, 9.707379f, 3.8104408f, 17.87282f)
    cubicTo(3.8104408f, 26.03826f, 10.437465f, 32.665283f, 18.602905f, 32.665283f)
    cubicTo(22.093708f, 32.665283f, 25.175081f, 31.276417f, 27.70596f, 29.251638f)
    cubicTo(27.49992f, 30.261774f, 27.627672f, 31.293585f, 28.464546f, 32.020485f)
    lineTo(39.464073f, 41.57869f)
    cubicTo(40.701477f, 42.653484f, 42.561516f, 42.51266f, 43.636307f, 41.275257f)
    cubicTo(44.711098f, 40.037853f, 44.570274f, 38.177814f, 43.33287f, 37.103024f)
    lineTo(32.333347f, 27.544815f)
    cubicTo(31.659649f, 26.959652f, 30.835642f, 26.786402f, 30.019653f, 26.900017f)
    cubicTo(32.012775f, 24.379473f, 33.39537f, 21.333277f, 33.39537f, 17.87282f)
    cubicTo(33.39537f, 9.707379f, 26.768345f, 3.0803552f, 18.602905f, 3.0803552f)
    close()
    moveTo(18.527046f, 6.266424f)
    cubicTo(24.808153f, 6.2664247f, 29.905865f, 11.364135f, 29.905865f, 17.645243f)
    cubicTo(29.905865f, 23.926352f, 24.808153f, 29.024061f, 18.527046f, 29.024061f)
    cubicTo(12.245938f, 29.024061f, 7.1482277f, 23.926352f, 7.1482277f, 17.645243f)
    cubicTo(7.1482277f, 11.364135f, 12.245938f, 6.266424f, 18.527046f, 6.266424f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(220, 220, 220, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.507004f, 41.57769f)
    cubicTo(39.02833f, 39.304504f, 40.904335f, 36.76627f, 43.091057f, 36.789314f)
    cubicTo(43.091057f, 36.789314f, 32.33069f, 27.531204f, 32.33069f, 27.531204f)
    cubicTo(29.385899f, 27.474499f, 28.061188f, 29.80382f, 28.553877f, 32.131126f)
    lineTo(39.507004f, 41.57769f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(125, 125, 125, 255), 0.5f to Color(177, 177, 177, 255), 1.0f to Color(104, 104, 104, 255), start = Offset(33.939777f, 36.443268f), end = Offset(37.35967f, 32.650097f), tileMode = TileMode.Clamp)
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
1.2457430362701416f, 0.0f, 0.0f, 0.0f,
0.0f, 1.2457430362701416f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.4253458976745605f, -6.177032947540283f, 0.0f, 1.0f)
))}){
// _0_2_1_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 255, 255, 56), 1.0f to Color(255, 255, 255, 255), start = Offset(18.292673f, 13.602121f), end = Offset(17.500893f, 25.74347f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.206434f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
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
alpha *= 0.4331551f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7529860138893127f, 0.658037006855011f, 0.0f, 0.0f,
-0.6489019989967346f, 0.7608720064163208f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_2_1_5
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.5029539f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 40.37333679199219f, top = 0.14086054265499115f, right = 59.421775817871094f, bottom = 4.581338867545128f,radiusX = 6.422405242919922f, radiusY = 4.440478324890137f))
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
1.3986140489578247f, 0.0f, 0.0f, 0.0f,
0.0f, 1.3986140489578247f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-6.224338054656982f, -8.298957824707031f, 0.0f, 1.0f)
))}){
// _0_2_1_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424265f, 25.704697f)
    cubicTo(10.848135f, 24.212242f, 9.267462f, 21.455486f, 9.280776f, 18.478292f)
    cubicTo(9.267462f, 15.501098f, 10.848135f, 12.744343f, 13.424265f, 11.251888f)
    cubicTo(16.000395f, 9.759435f, 19.178165f, 9.759435f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744343f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(114, 159, 207, 53), 1.0f to Color(114, 159, 207, 172), center = Offset(18.240929f, 21.817987f), radius = 8.308506f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(48, 99, 163, 255))
stroke = Stroke(width=1.0745695f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.897785f, 18.478292f)
    cubicTo(25.9111f, 21.455486f, 24.330425f, 24.212242f, 21.754295f, 25.704697f)
    cubicTo(19.178165f, 27.197151f, 16.000395f, 27.197151f, 13.424265f, 25.704697f)
    cubicTo(10.848135f, 24.212242f, 9.267462f, 21.455486f, 9.280776f, 18.478292f)
    cubicTo(9.267462f, 15.501098f, 10.848135f, 12.744343f, 13.424265f, 11.251888f)
    cubicTo(16.000395f, 9.759435f, 19.178165f, 9.759435f, 21.754295f, 11.251888f)
    cubicTo(24.330425f, 12.744343f, 25.9111f, 15.501098f, 25.897785f, 18.478292f)
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
// _0_2_1_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.156916f, 7.3966937f)
    cubicTo(12.949325f, 7.3966937f, 8.732368f, 11.613651f, 8.732368f, 16.821241f)
    cubicTo(8.732368f, 18.325216f, 9.152676f, 19.709015f, 9.77954f, 20.971144f)
    cubicTo(11.03192f, 21.432756f, 12.362297f, 21.746826f, 13.774307f, 21.746826f)
    cubicTo(19.945263f, 21.746826f, 24.873589f, 16.88519f, 25.254414f, 10.809698f)
    cubicTo(23.523449f, 8.764167f, 21.044374f, 7.3966937f, 18.156916f, 7.3966937f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 63), center = Offset(14.909142f, 10.512936f), radius = 17.259415f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
            return 6.10355281829834
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.1464462280273438
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 41.71162796020508
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.853553771972656
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

