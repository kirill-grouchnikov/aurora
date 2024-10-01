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
class bookmark_new : Painter() {
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
shape = Outline.Rounded(roundRect = RoundRect(left = 6.5f, top = 3.5f, right = 41.375f, bottom = 44.56343078613281f,radiusX = 2.2980971336364746f, radiusY = 2.298096179962158f))
brush = Brush.radialGradient(0.0f to Color(250, 250, 250, 255), 1.0f to Color(187, 187, 187, 255), center = Offset(32.521206f, 37.17764f), radius = 83.28285f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.radialGradient(0.0f to Color(163, 163, 163, 255), 1.0f to Color(76, 76, 76, 255), center = Offset(11.794447f, 4.382276f), radius = 36.553963f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.5f, top = 3.5f, right = 41.375f, bottom = 44.56343078613281f,radiusX = 2.2980971336364746f, radiusY = 2.298096179962158f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(248, 248, 248, 255), center = Offset(11.092703f, 8.077092f), radius = 37.129677f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 7.502455234527588f, top = 4.501048564910889f, right = 40.43946695327759f, bottom = 43.529258251190186f,radiusX = 0.29809704422950745f, radiusY = 0.29809698462486267f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2
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
// _0_1_3
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
// _0_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_0
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 9.0f, right = 36.0f, bottom = 10.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 11.0f, right = 36.0f, bottom = 12.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_2
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 13.0f, right = 36.0f, bottom = 14.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_3
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 15.0f, right = 36.0f, bottom = 16.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_4
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 17.0f, right = 36.0f, bottom = 18.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_5
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 19.0f, right = 36.0f, bottom = 20.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_6
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 21.0f, right = 36.0f, bottom = 22.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_7
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999994277954102f, top = 23.0f, right = 36.0f, bottom = 24.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_8
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999985694885254f, top = 25.0f, right = 24.999991416931152f, bottom = 26.0f,radiusX = 0.12400771677494049f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_9
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999985694885254f, top = 29.0f, right = 35.99999141693115f, bottom = 30.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_10
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999985694885254f, top = 31.0f, right = 35.99999141693115f, bottom = 32.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_11
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999985694885254f, top = 33.0f, right = 35.99999141693115f, bottom = 34.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_12
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999985694885254f, top = 35.0f, right = 35.99999141693115f, bottom = 36.0f,radiusX = 0.27557262778282166f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0_13
shape = Outline.Rounded(roundRect = RoundRect(left = 15.999985694885254f, top = 37.0f, right = 30.0f, bottom = 38.0f,radiusX = 0.19290097057819366f, radiusY = 0.13078175485134125f))
brush = SolidColor(Color(155, 155, 155, 140))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28021976f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.245857f, 31.324905f)
    lineTo(21.14787f, 27.133701f)
    lineTo(14.30757f, 30.8838f)
    lineTo(13.761859f, 3.9475667f)
    lineTo(28.549599f, 3.9475667f)
    lineTo(28.245857f, 31.324905f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(24.186203f, 17.156267f), end = Offset(24.471561f, 35.00565f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.427339f, 3.5180202f)
    cubicTo(12.427339f, 3.5180202f, 12.240033f, 0.6052061f, 15.107867f, 0.5427061f)
    lineTo(25.119343f, 0.50728625f)
    cubicTo(26.277287f, 0.50728625f, 26.581888f, 1.1910177f, 26.581888f, 2.1095588f)
    lineTo(26.581888f, 29.729916f)
    lineTo(20.545425f, 24.533861f)
    lineTo(14.674346f, 29.729916f)
    lineTo(14.591655f, 3.519629f)
    lineTo(12.427339f, 3.5180202f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(133, 157, 188, 255), 1.0f to Color(84, 114, 153, 255), start = Offset(21.214817f, 23.500282f), end = Offset(21.176764f, 4.2109227f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(54, 72, 120, 255))
stroke = Stroke(width=0.9999999f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.427339f, 3.5180202f)
    cubicTo(12.427339f, 3.5180202f, 12.240033f, 0.6052061f, 15.107867f, 0.5427061f)
    lineTo(25.119343f, 0.50728625f)
    cubicTo(26.277287f, 0.50728625f, 26.581888f, 1.1910177f, 26.581888f, 2.1095588f)
    lineTo(26.581888f, 29.729916f)
    lineTo(20.545425f, 24.533861f)
    lineTo(14.674346f, 29.729916f)
    lineTo(14.591655f, 3.519629f)
    lineTo(12.427339f, 3.5180202f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4450549f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.030252f, 3.011792f)
    cubicTo(13.011046f, 2.225362f, 13.312918f, 1.0801307f, 15.375418f, 1.0176307f)
    lineTo(25.027906f, 1.0f)
    cubicTo(25.640923f, 1.0f, 26.090153f, 1.167432f, 26.090153f, 1.7994802f)
    lineTo(26.060993f, 10.491851f)
    lineTo(15.317102f, 10.491851f)
    lineTo(15.192102f, 2.999325f)
    cubicTo(15.192102f, 2.999325f, 13.030252f, 3.011792f, 13.030252f, 3.011792f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(18.203613f, 2.1918054f), end = Offset(18.203613f, 6.065163f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28021976f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4
shape = Outline.Rounded(roundRect = RoundRect(left = 15.317100524902344f, top = 6.6907958984375f, right = 25.87349224090576f, bottom = 19.494352340698242f,radiusX = 0.12400771677494049f, radiusY = 0.13078175485134125f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 145), 1.0f to Color(0, 0, 0, 0), start = Offset(20.595297f, 6.208167f), end = Offset(20.595297f, 18.716755f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_5
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 49), 1.0f to Color(255, 255, 255, 0), start = Offset(20.606592f, 8.080574f), end = Offset(20.606592f, 3.4899082f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.476831f, 2.2095506f)
    lineTo(25.575535f, 3.113139f)
    lineTo(25.547445f, 27.511911f)
    lineTo(20.497463f, 23.203758f)
    lineTo(15.704084f, 27.415203f)
    lineTo(15.699081f, 2.7495618f)
    lineTo(24.476831f, 2.2095506f)
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
0.6111270189285278f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6111270189285278f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
5.6324381828308105f, -67.28175354003906f, 0.0f, 1.0f)
))}){
// _0_2_6
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
alphaStack.add(0, alpha)
alpha *= 0.48295456f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.158602f, 3.9384084f)
    lineTo(15.114407f, 1.0335178f)
    cubicTo(12.983906f, 1.0335178f, 12.993087f, 2.9680774f, 12.993087f, 3.9384084f)
    lineTo(15.158602f, 3.9384084f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(13.354311f, 1.0335178f), end = Offset(14.075844f, 2.1177697f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.35795456f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.158602f, 3.9384086f)
    lineTo(15.114407f, 1.8247592f)
    cubicTo(12.81631f, 1.8426926f, 12.993087f, 3.9384086f, 12.993087f, 3.9384086f)
    lineTo(15.158602f, 3.9384086f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(14.584077f, 1.82309f), end = Offset(14.552828f, 3.1813283f), tileMode = TileMode.Clamp)
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
            return 6.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.0072863101959228516
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 42.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.30581283569336
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

