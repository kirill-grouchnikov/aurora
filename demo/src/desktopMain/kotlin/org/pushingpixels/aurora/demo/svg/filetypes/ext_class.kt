package org.pushingpixels.aurora.demo.svg.filetypes

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
class ext_class : Painter() {
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
0.009999999776482582f, 0.0f, 0.0f, 0.0f,
0.0f, 0.009999999776482582f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.13999999687075615f, -0.0f, 0.0f, 1.0f)
))}){
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.0f), end = Offset(36.0f, 1.0f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
brush = SolidColor(Color(113, 145, 161, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.4f, 44.1f)
    lineTo(14.4f, 39.199997f)
    cubicTo(15.4f, 39.1f, 16.199999f, 38.999996f, 16.699999f, 38.699997f)
    cubicTo(17.199999f, 38.499996f, 17.599998f, 37.999996f, 17.999998f, 37.499996f)
    cubicTo(18.399998f, 36.899998f, 18.599998f, 36.199997f, 18.799997f, 35.299995f)
    cubicTo(18.899998f, 34.599995f, 18.999998f, 33.499996f, 18.999998f, 31.899996f)
    cubicTo(18.999998f, 29.199995f, 19.099998f, 27.399996f, 19.399998f, 26.299995f)
    cubicTo(19.599998f, 25.299995f, 20.099998f, 24.399996f, 20.699997f, 23.799995f)
    cubicTo(21.299995f, 23.199995f, 22.299997f, 22.699995f, 23.499996f, 22.299995f)
    cubicTo(24.299995f, 22.099995f, 25.599997f, 21.899996f, 27.399996f, 21.899996f)
    lineTo(28.499996f, 21.899996f)
    lineTo(28.499996f, 26.799995f)
    cubicTo(26.999996f, 26.799995f, 25.999996f, 26.899996f, 25.599997f, 27.099995f)
    cubicTo(25.199997f, 27.299995f, 24.899996f, 27.499994f, 24.599997f, 27.899994f)
    cubicTo(24.399996f, 28.199993f, 24.299997f, 28.799994f, 24.299997f, 29.699993f)
    cubicTo(24.299997f, 30.599993f, 24.199997f, 32.199993f, 24.099997f, 34.599995f)
    cubicTo(23.999996f, 35.999996f, 23.899996f, 37.099995f, 23.599997f, 37.999996f)
    cubicTo(23.299997f, 38.799995f, 22.899996f, 39.499996f, 22.499996f, 40.099995f)
    cubicTo(22.099997f, 40.599995f, 21.399996f, 41.199993f, 20.499996f, 41.799995f)
    cubicTo(21.299995f, 42.299995f, 21.999996f, 42.799995f, 22.499996f, 43.399994f)
    cubicTo(22.999996f, 43.999992f, 23.399996f, 44.799995f, 23.699997f, 45.699993f)
    cubicTo(23.999998f, 46.59999f, 24.199997f, 47.79999f, 24.199997f, 49.29999f)
    cubicTo(24.299997f, 51.59999f, 24.299997f, 52.999992f, 24.299997f, 53.699993f)
    cubicTo(24.299997f, 54.599995f, 24.399998f, 55.199993f, 24.599997f, 55.599995f)
    cubicTo(24.799997f, 55.999996f, 25.199997f, 56.199993f, 25.599997f, 56.399994f)
    cubicTo(25.999996f, 56.599995f, 26.999996f, 56.699993f, 28.499996f, 56.699993f)
    lineTo(28.499996f, 61.599995f)
    lineTo(27.399996f, 61.599995f)
    cubicTo(25.599997f, 61.599995f, 24.099997f, 61.499996f, 23.199997f, 61.199993f)
    cubicTo(22.199997f, 60.899994f, 21.399998f, 60.399994f, 20.699997f, 59.699993f)
    cubicTo(19.999996f, 58.999992f, 19.599997f, 58.199993f, 19.299997f, 57.199993f)
    cubicTo(19.099997f, 56.199993f, 18.999998f, 54.599995f, 18.999998f, 52.399994f)
    cubicTo(18.999998f, 49.899994f, 18.899998f, 48.199993f, 18.699999f, 47.499992f)
    cubicTo(18.4f, 46.399994f, 17.9f, 45.59999f, 17.3f, 45.09999f)
    cubicTo(16.599998f, 44.499992f, 15.699999f, 44.09999f, 14.4f, 44.09999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(21.45f, 61.55f), end = Offset(21.45f, 21.95f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(58.7f, 44.1f)
    cubicTo(57.7f, 44.199997f, 56.9f, 44.3f, 56.4f, 44.6f)
    cubicTo(55.9f, 44.8f, 55.5f, 45.3f, 55.100002f, 45.8f)
    cubicTo(54.7f, 46.399998f, 54.500004f, 47.1f, 54.300003f, 48.0f)
    cubicTo(54.200005f, 48.7f, 54.100002f, 49.8f, 54.100002f, 51.4f)
    cubicTo(54.100002f, 54.100002f, 54.000004f, 55.9f, 53.7f, 57.0f)
    cubicTo(53.5f, 58.1f, 53.0f, 58.9f, 52.4f, 59.5f)
    cubicTo(51.800003f, 60.1f, 50.800003f, 60.6f, 49.600002f, 61.0f)
    cubicTo(48.800003f, 61.2f, 47.500004f, 61.4f, 45.7f, 61.4f)
    lineTo(44.600002f, 61.4f)
    lineTo(44.600002f, 56.5f)
    cubicTo(46.100002f, 56.5f, 47.000004f, 56.4f, 47.500004f, 56.2f)
    cubicTo(48.000004f, 56.0f, 48.300003f, 55.7f, 48.500004f, 55.4f)
    cubicTo(48.700005f, 55.100002f, 48.800003f, 54.5f, 48.800003f, 53.600002f)
    cubicTo(48.800003f, 52.800003f, 48.9f, 51.2f, 49.000004f, 48.800003f)
    cubicTo(49.100002f, 47.4f, 49.300003f, 46.200005f, 49.600002f, 45.4f)
    cubicTo(49.9f, 44.5f, 50.300003f, 43.800003f, 50.800003f, 43.2f)
    cubicTo(51.300003f, 42.6f, 51.9f, 42.100002f, 52.700005f, 41.600002f)
    cubicTo(51.700005f, 40.9f, 50.900005f, 40.300003f, 50.500004f, 39.7f)
    cubicTo(49.900005f, 38.8f, 49.400005f, 37.600002f, 49.200005f, 36.3f)
    cubicTo(49.000004f, 35.3f, 48.900005f, 33.2f, 48.900005f, 30.0f)
    cubicTo(48.900005f, 29.0f, 48.800007f, 28.3f, 48.600006f, 27.9f)
    cubicTo(48.400005f, 27.6f, 48.100006f, 27.3f, 47.700005f, 27.1f)
    cubicTo(47.300003f, 26.9f, 46.300003f, 26.800001f, 44.700005f, 26.800001f)
    lineTo(44.700005f, 22.0f)
    lineTo(45.800003f, 22.0f)
    cubicTo(47.600002f, 22.0f, 49.100002f, 22.1f, 50.000004f, 22.4f)
    cubicTo(51.000004f, 22.699999f, 51.800003f, 23.199999f, 52.500004f, 23.9f)
    cubicTo(53.200005f, 24.6f, 53.600002f, 25.4f, 53.900005f, 26.4f)
    cubicTo(54.100006f, 27.4f, 54.300007f, 29.0f, 54.300007f, 31.2f)
    cubicTo(54.300007f, 33.7f, 54.400005f, 35.3f, 54.600006f, 36.100002f)
    cubicTo(54.900005f, 37.2f, 55.400005f, 38.000004f, 56.000008f, 38.4f)
    cubicTo(56.600006f, 38.9f, 57.600006f, 39.100002f, 58.800007f, 39.2f)
    lineTo(58.70001f, 44.100002f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(51.7f, 61.45f), end = Offset(51.7f, 21.95f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.037f, 27.813f), end = Offset(58.537f, 14.313f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
brush = SolidColor(Color(113, 145, 161, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
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
// _0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.3f, 86.5f)
    lineTo(17.0f, 87.3f)
    cubicTo(16.6f, 88.8f, 15.9f, 89.9f, 15.0f, 90.600006f)
    cubicTo(14.1f, 91.3f, 12.8f, 91.700005f, 11.4f, 91.700005f)
    cubicTo(9.599999f, 91.700005f, 8.099999f, 91.100006f, 6.8999996f, 89.8f)
    cubicTo(5.7f, 88.600006f, 5.2f, 86.9f, 5.2f, 84.700005f)
    cubicTo(5.2f, 82.4f, 5.7999997f, 80.700005f, 7.0f, 79.4f)
    cubicTo(8.2f, 78.1f, 9.7f, 77.5f, 11.6f, 77.5f)
    cubicTo(13.3f, 77.5f, 14.6f, 78.0f, 15.700001f, 79.0f)
    cubicTo(16.300001f, 79.6f, 16.800001f, 80.4f, 17.1f, 81.5f)
    lineTo(14.400001f, 82.1f)
    cubicTo(14.200001f, 81.4f, 13.900001f, 80.799995f, 13.400001f, 80.4f)
    cubicTo(12.900001f, 80.0f, 12.3f, 79.8f, 11.500001f, 79.8f)
    cubicTo(10.500001f, 79.8f, 9.700001f, 80.200005f, 9.000001f, 80.9f)
    cubicTo(8.300001f, 81.9f, 8.000001f, 83.0f, 8.000001f, 84.700005f)
    cubicTo(8.000001f, 86.4f, 8.300001f, 87.600006f, 8.900001f, 88.4f)
    cubicTo(9.500001f, 89.1f, 10.3f, 89.5f, 11.300001f, 89.5f)
    cubicTo(12.000001f, 89.5f, 12.700001f, 89.3f, 13.200001f, 88.8f)
    cubicTo(13.700001f, 88.3f, 14.1f, 87.5f, 14.300001f, 86.5f)
    close()
    moveTo(19.4f, 91.5f)
    lineTo(19.4f, 78.1f)
    lineTo(22.1f, 78.1f)
    lineTo(22.1f, 89.299995f)
    lineTo(29.0f, 89.299995f)
    lineTo(29.0f, 91.6f)
    lineTo(19.4f, 91.6f)
    close()
    moveTo(43.199997f, 91.5f)
    lineTo(40.199997f, 91.5f)
    lineTo(38.999996f, 88.5f)
    lineTo(33.599995f, 88.5f)
    lineTo(32.499996f, 91.6f)
    lineTo(29.599997f, 91.6f)
    lineTo(34.9f, 78.0f)
    lineTo(37.800003f, 78.0f)
    lineTo(43.200005f, 91.5f)
    close()
    moveTo(38.199997f, 86.2f)
    lineTo(36.299995f, 81.2f)
    lineTo(34.499996f, 86.2f)
    lineTo(38.199997f, 86.2f)
    close()
    moveTo(43.999996f, 87.1f)
    lineTo(46.699997f, 86.799995f)
    cubicTo(46.899998f, 87.7f, 47.199997f, 88.399994f, 47.699997f, 88.799995f)
    cubicTo(48.199997f, 89.2f, 48.899998f, 89.399994f, 49.699997f, 89.399994f)
    cubicTo(50.6f, 89.399994f, 51.299995f, 89.2f, 51.699997f, 88.799995f)
    cubicTo(52.1f, 88.399994f, 52.399998f, 87.99999f, 52.399998f, 87.49999f)
    cubicTo(52.399998f, 87.19999f, 52.3f, 86.899994f, 52.1f, 86.69999f)
    cubicTo(51.899998f, 86.49999f, 51.6f, 86.29999f, 51.1f, 86.09999f)
    cubicTo(50.8f, 85.99999f, 50.0f, 85.79999f, 48.899998f, 85.49999f)
    cubicTo(47.399998f, 85.09999f, 46.399998f, 84.69999f, 45.8f, 84.19999f)
    cubicTo(45.0f, 83.49999f, 44.5f, 82.49999f, 44.5f, 81.49999f)
    cubicTo(44.5f, 80.799995f, 44.7f, 80.19999f, 45.1f, 79.59999f)
    cubicTo(45.5f, 78.99999f, 46.1f, 78.49999f, 46.8f, 78.19999f)
    cubicTo(47.5f, 77.89999f, 48.399998f, 77.69999f, 49.5f, 77.69999f)
    cubicTo(51.2f, 77.69999f, 52.5f, 78.09999f, 53.4f, 78.79999f)
    cubicTo(54.300003f, 79.499985f, 54.7f, 80.499985f, 54.800003f, 81.79999f)
    lineTo(52.100002f, 81.89999f)
    cubicTo(52.000004f, 81.19999f, 51.7f, 80.69999f, 51.300003f, 80.39999f)
    cubicTo(50.9f, 80.09998f, 50.300003f, 79.89999f, 49.600002f, 79.89999f)
    cubicTo(48.800003f, 79.89999f, 48.2f, 80.09998f, 47.7f, 80.39999f)
    cubicTo(47.4f, 80.59998f, 47.3f, 80.89999f, 47.3f, 81.19999f)
    cubicTo(47.3f, 81.49999f, 47.399998f, 81.79999f, 47.7f, 81.99999f)
    cubicTo(48.0f, 82.299995f, 48.9f, 82.59999f, 50.2f, 82.899994f)
    cubicTo(51.5f, 83.2f, 52.5f, 83.49999f, 53.2f, 83.899994f)
    cubicTo(53.8f, 84.2f, 54.3f, 84.7f, 54.7f, 85.299995f)
    cubicTo(55.100002f, 85.899994f, 55.2f, 86.6f, 55.2f, 87.49999f)
    cubicTo(55.2f, 88.299995f, 55.0f, 88.99999f, 54.600002f, 89.69999f)
    cubicTo(54.2f, 90.39999f, 53.600002f, 90.89999f, 52.800003f, 91.19999f)
    cubicTo(52.000004f, 91.49999f, 51.000004f, 91.69999f, 49.800003f, 91.69999f)
    cubicTo(48.100002f, 91.69999f, 46.800003f, 91.29999f, 45.800003f, 90.49999f)
    cubicTo(44.700005f, 89.799995f, 44.100002f, 88.59999f, 44.000004f, 87.09999f)
    close()
    moveTo(56.6f, 87.1f)
    lineTo(59.3f, 86.799995f)
    cubicTo(59.5f, 87.7f, 59.8f, 88.399994f, 60.3f, 88.799995f)
    cubicTo(60.8f, 89.2f, 61.5f, 89.399994f, 62.3f, 89.399994f)
    cubicTo(63.2f, 89.399994f, 63.899998f, 89.2f, 64.3f, 88.799995f)
    cubicTo(64.700005f, 88.399994f, 65.0f, 87.99999f, 65.0f, 87.49999f)
    cubicTo(65.0f, 87.19999f, 64.9f, 86.899994f, 64.7f, 86.69999f)
    cubicTo(64.5f, 86.49999f, 64.2f, 86.29999f, 63.699997f, 86.09999f)
    cubicTo(63.399998f, 85.99999f, 62.6f, 85.79999f, 61.499996f, 85.49999f)
    cubicTo(59.999996f, 85.09999f, 58.999996f, 84.69999f, 58.399998f, 84.19999f)
    cubicTo(57.6f, 83.49999f, 57.1f, 82.49999f, 57.1f, 81.49999f)
    cubicTo(57.1f, 80.799995f, 57.3f, 80.19999f, 57.699997f, 79.59999f)
    cubicTo(58.1f, 78.99999f, 58.699997f, 78.49999f, 59.399998f, 78.19999f)
    cubicTo(60.1f, 77.89999f, 60.999996f, 77.69999f, 62.1f, 77.69999f)
    cubicTo(63.8f, 77.69999f, 65.1f, 78.09999f, 66.0f, 78.79999f)
    cubicTo(66.9f, 79.499985f, 67.3f, 80.499985f, 67.4f, 81.79999f)
    lineTo(64.700005f, 81.89999f)
    cubicTo(64.600006f, 81.19999f, 64.3f, 80.69999f, 63.900005f, 80.39999f)
    cubicTo(63.500004f, 80.09998f, 62.900005f, 79.89999f, 62.200005f, 79.89999f)
    cubicTo(61.400005f, 79.89999f, 60.800003f, 80.09998f, 60.300003f, 80.39999f)
    cubicTo(60.000004f, 80.59998f, 59.9f, 80.89999f, 59.9f, 81.19999f)
    cubicTo(59.9f, 81.49999f, 60.0f, 81.79999f, 60.300003f, 81.99999f)
    cubicTo(60.600002f, 82.299995f, 61.500004f, 82.59999f, 62.800003f, 82.899994f)
    cubicTo(64.100006f, 83.2f, 65.100006f, 83.49999f, 65.8f, 83.899994f)
    cubicTo(66.4f, 84.2f, 66.9f, 84.7f, 67.3f, 85.299995f)
    cubicTo(67.700005f, 85.899994f, 67.8f, 86.6f, 67.8f, 87.49999f)
    cubicTo(67.8f, 88.299995f, 67.600006f, 88.99999f, 67.200005f, 89.69999f)
    cubicTo(66.8f, 90.39999f, 66.200005f, 90.89999f, 65.4f, 91.19999f)
    cubicTo(64.6f, 91.49999f, 63.600002f, 91.69999f, 62.4f, 91.69999f)
    cubicTo(60.7f, 91.69999f, 59.4f, 91.29999f, 58.4f, 90.49999f)
    cubicTo(57.4f, 89.799995f, 56.800003f, 88.59999f, 56.600002f, 87.09999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 0.12999999523162842
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
            return 0.7400000095367432
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 1.0
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

