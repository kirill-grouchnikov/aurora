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
class document_properties : Painter() {
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
alpha *= 0.4090909f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.96875f, 14.555141f)
    cubicTo(25.65626f, 14.579414f, 25.35254f, 14.618618f, 25.03125f, 14.680141f)
    lineTo(23.75f, 15.836391f)
    lineTo(27.71875f, 19.586391f)
    lineTo(27.9375f, 22.867641f)
    lineTo(24.96875f, 25.555141f)
    lineTo(21.46875f, 25.180141f)
    lineTo(17.84375f, 21.773891f)
    cubicTo(17.843748f, 21.773891f, 16.5625f, 23.023891f, 16.5625f, 23.023891f)
    cubicTo(15.971737f, 28.665209f, 21.875f, 33.71139f, 27.9375f, 30.336391f)
    lineTo(39.78125f, 42.46139f)
    lineTo(39.78125f, 32.08639f)
    lineTo(33.21875f, 25.055141f)
    cubicTo(35.345703f, 19.146938f, 31.370365f, 14.13556f, 25.96875f, 14.555141f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(26.03008f, 23.369438f), radius = 24.55044f, tileMode = TileMode.Clamp)
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
            return 35.875
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.16665267944336
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

