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
class ext_css : Painter() {
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
    moveTo(22.1f, 84.2f)
    lineTo(26.0f, 85.399994f)
    cubicTo(25.4f, 87.59999f, 24.4f, 89.2f, 23.0f, 90.299995f)
    cubicTo(21.6f, 91.399994f, 19.8f, 91.899994f, 17.7f, 91.899994f)
    cubicTo(15.000001f, 91.899994f, 12.900001f, 90.99999f, 11.1f, 89.2f)
    cubicTo(9.400001f, 87.399994f, 8.5f, 84.899994f, 8.5f, 81.7f)
    cubicTo(8.5f, 78.399994f, 9.4f, 75.799995f, 11.1f, 73.899994f)
    cubicTo(12.800001f, 71.99999f, 15.1f, 71.09999f, 17.900002f, 71.09999f)
    cubicTo(20.400002f, 71.09999f, 22.300001f, 71.79999f, 23.900002f, 73.29999f)
    cubicTo(24.800001f, 74.19999f, 25.500002f, 75.39999f, 26.000002f, 76.999985f)
    lineTo(22.000002f, 77.999985f)
    cubicTo(21.800001f, 76.999985f, 21.300001f, 76.09998f, 20.500002f, 75.499985f)
    cubicTo(19.700003f, 74.89999f, 18.800001f, 74.59998f, 17.800001f, 74.59998f)
    cubicTo(16.300001f, 74.59998f, 15.100001f, 75.09998f, 14.200001f, 76.19998f)
    cubicTo(13.300001f, 77.29998f, 12.800001f, 78.999985f, 12.800001f, 81.39998f)
    cubicTo(12.800001f, 83.89998f, 13.300001f, 85.69998f, 14.200001f, 86.79998f)
    cubicTo(15.1f, 87.89998f, 16.300001f, 88.39998f, 17.800001f, 88.39998f)
    cubicTo(18.900002f, 88.39998f, 19.800001f, 88.099976f, 20.6f, 87.39998f)
    cubicTo(21.2f, 86.69998f, 21.7f, 85.599976f, 22.1f, 84.19998f)
    close()
    moveTo(28.400002f, 85.0f)
    lineTo(32.300003f, 84.6f)
    cubicTo(32.500004f, 85.9f, 33.000004f, 86.9f, 33.700005f, 87.5f)
    cubicTo(34.400005f, 88.1f, 35.400005f, 88.4f, 36.600006f, 88.4f)
    cubicTo(37.900005f, 88.4f, 38.900005f, 88.1f, 39.500008f, 87.6f)
    cubicTo(40.20001f, 87.0f, 40.500008f, 86.4f, 40.500008f, 85.7f)
    cubicTo(40.500008f, 85.2f, 40.40001f, 84.799995f, 40.100006f, 84.5f)
    cubicTo(39.800007f, 84.2f, 39.300007f, 83.9f, 38.600006f, 83.6f)
    cubicTo(38.100006f, 83.4f, 37.000008f, 83.1f, 35.400005f, 82.7f)
    cubicTo(33.200005f, 82.2f, 31.700005f, 81.5f, 30.800005f, 80.7f)
    cubicTo(29.600004f, 79.6f, 29.000006f, 78.299995f, 29.000006f, 76.7f)
    cubicTo(29.000006f, 75.7f, 29.300005f, 74.7f, 29.900005f, 73.799995f)
    cubicTo(30.500006f, 72.899994f, 31.300005f, 72.2f, 32.400005f, 71.799995f)
    cubicTo(33.500004f, 71.299995f, 34.800007f, 71.1f, 36.300007f, 71.1f)
    cubicTo(38.800007f, 71.1f, 40.70001f, 71.7f, 42.000008f, 72.799995f)
    cubicTo(43.300007f, 73.899994f, 43.90001f, 75.399994f, 44.000008f, 77.2f)
    lineTo(40.000008f, 77.399994f)
    cubicTo(39.800007f, 76.399994f, 39.500008f, 75.59999f, 38.90001f, 75.2f)
    cubicTo(38.30001f, 74.7f, 37.500008f, 74.5f, 36.30001f, 74.5f)
    cubicTo(35.10001f, 74.5f, 34.200012f, 74.7f, 33.50001f, 75.2f)
    cubicTo(33.30001f, 75.5f, 33.00001f, 76.0f, 33.00001f, 76.5f)
    cubicTo(33.00001f, 77.0f, 33.200012f, 77.4f, 33.60001f, 77.7f)
    cubicTo(34.10001f, 78.1f, 35.40001f, 78.6f, 37.30001f, 79.0f)
    cubicTo(39.200012f, 79.4f, 40.700012f, 79.9f, 41.700012f, 80.4f)
    cubicTo(42.700012f, 80.9f, 43.400013f, 81.6f, 43.900013f, 82.4f)
    cubicTo(44.400013f, 83.200005f, 44.700012f, 84.3f, 44.700012f, 85.6f)
    cubicTo(44.700012f, 86.7f, 44.400013f, 87.799995f, 43.700012f, 88.799995f)
    cubicTo(43.100014f, 89.799995f, 42.200012f, 90.49999f, 41.00001f, 90.99999f)
    cubicTo(39.80001f, 91.49999f, 38.400013f, 91.69999f, 36.60001f, 91.69999f)
    cubicTo(34.10001f, 91.69999f, 32.10001f, 91.09999f, 30.80001f, 89.89999f)
    cubicTo(29.500011f, 88.999985f, 28.70001f, 87.29999f, 28.400011f, 84.999985f)
    close()
    moveTo(47.100002f, 85.0f)
    lineTo(51.000004f, 84.6f)
    cubicTo(51.200005f, 85.9f, 51.700005f, 86.9f, 52.400005f, 87.5f)
    cubicTo(53.100006f, 88.1f, 54.100006f, 88.4f, 55.300007f, 88.4f)
    cubicTo(56.600006f, 88.4f, 57.600006f, 88.1f, 58.20001f, 87.6f)
    cubicTo(58.90001f, 87.0f, 59.20001f, 86.4f, 59.20001f, 85.7f)
    cubicTo(59.20001f, 85.2f, 59.10001f, 84.799995f, 58.800007f, 84.5f)
    cubicTo(58.500008f, 84.2f, 58.000008f, 83.9f, 57.300007f, 83.6f)
    cubicTo(56.800007f, 83.4f, 55.70001f, 83.1f, 54.100006f, 82.7f)
    cubicTo(51.900005f, 82.2f, 50.400005f, 81.5f, 49.500008f, 80.7f)
    cubicTo(48.300007f, 79.6f, 47.70001f, 78.299995f, 47.70001f, 76.7f)
    cubicTo(47.70001f, 75.7f, 48.000008f, 74.7f, 48.60001f, 73.799995f)
    cubicTo(49.20001f, 72.899994f, 50.00001f, 72.2f, 51.10001f, 71.799995f)
    cubicTo(52.20001f, 71.299995f, 53.50001f, 71.1f, 55.00001f, 71.1f)
    cubicTo(57.50001f, 71.1f, 59.400013f, 71.7f, 60.700012f, 72.799995f)
    cubicTo(62.00001f, 73.899994f, 62.600014f, 75.399994f, 62.700012f, 77.2f)
    lineTo(58.700012f, 77.399994f)
    cubicTo(58.50001f, 76.399994f, 58.200012f, 75.59999f, 57.600014f, 75.2f)
    cubicTo(57.000015f, 74.7f, 56.200012f, 74.5f, 55.000015f, 74.5f)
    cubicTo(53.800014f, 74.5f, 52.900017f, 74.7f, 52.200016f, 75.2f)
    cubicTo(51.800014f, 75.5f, 51.600018f, 75.899994f, 51.600018f, 76.399994f)
    cubicTo(51.600018f, 76.899994f, 51.80002f, 77.299995f, 52.200016f, 77.59999f)
    cubicTo(52.700016f, 77.99999f, 54.000015f, 78.49999f, 55.900017f, 78.899994f)
    cubicTo(57.80002f, 79.299995f, 59.30002f, 79.799995f, 60.30002f, 80.299995f)
    cubicTo(61.30002f, 80.799995f, 62.00002f, 81.49999f, 62.50002f, 82.299995f)
    cubicTo(63.00002f, 83.1f, 63.30002f, 84.2f, 63.30002f, 85.49999f)
    cubicTo(63.30002f, 86.59999f, 63.00002f, 87.69999f, 62.30002f, 88.69999f)
    cubicTo(61.70002f, 89.69999f, 60.80002f, 90.39999f, 59.600018f, 90.89999f)
    cubicTo(58.400017f, 91.39999f, 57.00002f, 91.59998f, 55.200016f, 91.59998f)
    cubicTo(52.700016f, 91.59998f, 50.700016f, 90.999985f, 49.400017f, 89.79998f)
    cubicTo(48.200016f, 88.99998f, 47.400017f, 87.29998f, 47.100018f, 84.99998f)
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

