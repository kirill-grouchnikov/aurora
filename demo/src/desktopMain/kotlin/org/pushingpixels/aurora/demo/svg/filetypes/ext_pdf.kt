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
class ext_pdf : Painter() {
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(72.0f, 99.299995f)
    lineTo(0.0f, 99.299995f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(110, 14, 16, 255), 0.047f to Color(126, 20, 22, 255), 0.116f to Color(148, 25, 28, 255), 0.196f to Color(167, 29, 33, 255), 0.289f to Color(182, 31, 36, 255), 0.403f to Color(192, 32, 38, 255), 0.563f to Color(199, 32, 39, 255), 1.0f to Color(200, 33, 39, 255), start = Offset(36.0f, 99.233f), end = Offset(36.0f, 0.75f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(72.0f, 99.299995f)
    lineTo(0.0f, 99.299995f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(149, 27, 31, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(72.0f, 99.299995f)
    lineTo(0.0f, 99.299995f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
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
    moveTo(9.9f, 91.3f)
    lineTo(9.9f, 71.3f)
    lineTo(16.4f, 71.3f)
    cubicTo(18.9f, 71.3f, 20.5f, 71.4f, 21.2f, 71.600006f)
    cubicTo(22.300001f, 71.90001f, 23.300001f, 72.50001f, 24.1f, 73.600006f)
    cubicTo(24.9f, 74.600006f, 25.300001f, 75.90001f, 25.300001f, 77.50001f)
    cubicTo(25.300001f, 78.700005f, 25.1f, 79.80001f, 24.6f, 80.600006f)
    cubicTo(24.2f, 81.40001f, 23.6f, 82.100006f, 22.9f, 82.600006f)
    cubicTo(22.199999f, 83.100006f, 21.5f, 83.40001f, 20.8f, 83.50001f)
    cubicTo(19.8f, 83.700005f, 18.4f, 83.80001f, 16.599998f, 83.80001f)
    lineTo(14.0f, 83.80001f)
    lineTo(14.0f, 91.30001f)
    lineTo(9.9f, 91.30001f)
    close()
    moveTo(14.0f, 74.7f)
    lineTo(14.0f, 80.399994f)
    lineTo(16.2f, 80.399994f)
    cubicTo(17.800001f, 80.399994f, 18.900002f, 80.299995f, 19.400002f, 80.09999f)
    cubicTo(19.900002f, 79.899994f, 20.400002f, 79.59999f, 20.7f, 79.09999f)
    cubicTo(21.0f, 78.69999f, 21.2f, 78.09999f, 21.2f, 77.49999f)
    cubicTo(21.2f, 76.799995f, 21.0f, 76.19999f, 20.6f, 75.69999f)
    cubicTo(20.2f, 75.19999f, 19.6f, 74.89999f, 19.0f, 74.79999f)
    cubicTo(18.5f, 74.69999f, 17.5f, 74.69999f, 16.1f, 74.69999f)
    lineTo(14.0f, 74.69999f)
    close()
    moveTo(28.6f, 71.299995f)
    lineTo(36.0f, 71.299995f)
    cubicTo(37.7f, 71.299995f, 38.9f, 71.399994f, 39.8f, 71.7f)
    cubicTo(41.0f, 72.0f, 42.0f, 72.7f, 42.8f, 73.5f)
    cubicTo(43.6f, 74.4f, 44.3f, 75.5f, 44.7f, 76.7f)
    cubicTo(45.100002f, 78.0f, 45.4f, 79.5f, 45.4f, 81.399994f)
    cubicTo(45.4f, 82.99999f, 45.2f, 84.49999f, 44.800003f, 85.59999f)
    cubicTo(44.300003f, 87.09999f, 43.600002f, 88.19999f, 42.700005f, 89.09999f)
    cubicTo(42.000004f, 89.79999f, 41.000004f, 90.29999f, 39.900005f, 90.69999f)
    cubicTo(39.000004f, 90.99999f, 37.800007f, 91.09999f, 36.300007f, 91.09999f)
    lineTo(28.700006f, 91.09999f)
    lineTo(28.700006f, 71.3f)
    close()
    moveTo(32.6f, 74.7f)
    lineTo(32.6f, 87.899994f)
    lineTo(35.6f, 87.899994f)
    cubicTo(36.699997f, 87.899994f, 37.5f, 87.799995f, 38.0f, 87.7f)
    cubicTo(38.7f, 87.5f, 39.2f, 87.299995f, 39.6f, 86.899994f)
    cubicTo(40.0f, 86.49999f, 40.399998f, 85.899994f, 40.699997f, 84.99999f)
    cubicTo(40.999996f, 84.09999f, 41.1f, 82.899994f, 41.1f, 81.299995f)
    cubicTo(41.1f, 79.799995f, 41.0f, 78.6f, 40.699997f, 77.799995f)
    cubicTo(40.399994f, 76.99999f, 39.999996f, 76.299995f, 39.6f, 75.899994f)
    cubicTo(39.1f, 75.399994f, 38.5f, 75.09999f, 37.699997f, 74.99999f)
    cubicTo(37.1f, 74.899994f, 35.999996f, 74.799995f, 34.399998f, 74.799995f)
    lineTo(32.6f, 74.799995f)
    close()
    moveTo(48.8f, 91.299995f)
    lineTo(48.8f, 71.299995f)
    lineTo(62.5f, 71.299995f)
    lineTo(62.5f, 74.7f)
    lineTo(52.8f, 74.7f)
    lineTo(52.8f, 79.399994f)
    lineTo(61.199997f, 79.399994f)
    lineTo(61.199997f, 82.799995f)
    lineTo(52.799995f, 82.799995f)
    lineTo(52.799995f, 91.299995f)
    lineTo(48.799995f, 91.299995f)
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
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(253, 223, 216, 255), 0.166f to Color(252, 220, 212, 255), 0.302f to Color(249, 211, 201, 255), 0.427f to Color(244, 197, 183, 255), 0.546f to Color(239, 177, 160, 255), 0.661f to Color(233, 153, 131, 255), 0.772f to Color(226, 126, 102, 255), 0.88f to Color(218, 92, 70, 255), 0.984f to Color(210, 43, 42, 255), 1.0f to Color(209, 32, 39, 255), start = Offset(45.035f, 27.643997f), end = Offset(58.535f, 14.143997f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(142, 25, 28, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
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
    moveTo(15.7f, 54.3f)
    cubicTo(12.799999f, 57.3f, 9.0f, 59.5f, 7.8999996f, 64.2f)
    cubicTo(14.9f, 62.499996f, 18.9f, 54.999996f, 22.8f, 48.299995f)
    cubicTo(24.4f, 45.499996f, 26.599998f, 41.899994f, 28.5f, 38.099995f)
    cubicTo(30.1f, 34.799995f, 32.7f, 29.699995f, 32.8f, 26.799995f)
    cubicTo(33.0f, 21.299995f, 28.3f, 14.199995f, 32.399998f, 8.799995f)
    cubicTo(34.999996f, 8.399996f, 36.399998f, 9.299995f, 35.999996f, 11.999995f)
    cubicTo(35.199997f, 11.199995f, 35.499996f, 9.499995f, 33.899998f, 9.499995f)
    cubicTo(31.099998f, 12.099995f, 32.699997f, 19.899994f, 33.899998f, 22.899994f)
    cubicTo(34.6f, 20.599995f, 35.3f, 18.099995f, 35.3f, 15.099994f)
    cubicTo(36.5f, 19.399994f, 34.6f, 22.999994f, 34.899998f, 26.399994f)
    cubicTo(35.899998f, 35.099995f, 43.799995f, 40.199993f, 49.799995f, 44.099995f)
    cubicTo(55.599995f, 43.899994f, 65.1f, 42.399994f, 67.59999f, 46.599995f)
    cubicTo(66.99999f, 47.499996f, 65.79999f, 45.899994f, 64.399994f, 45.499996f)
    cubicTo(61.299995f, 44.799995f, 56.699993f, 45.399998f, 52.999992f, 45.099995f)
    cubicTo(56.499992f, 47.399994f, 60.899994f, 48.799995f, 66.899994f, 48.699993f)
    cubicTo(61.399994f, 52.599995f, 53.599995f, 46.099995f, 48.799995f, 45.499992f)
    cubicTo(41.799995f, 44.59999f, 33.999996f, 48.899994f, 27.099995f, 49.399994f)
    cubicTo(32.699993f, 46.899994f, 39.499992f, 45.699993f, 46.299995f, 44.399994f)
    cubicTo(41.099995f, 40.599995f, 36.599995f, 36.099995f, 34.199997f, 29.599995f)
    cubicTo(32.499996f, 35.299995f, 28.699997f, 41.699997f, 24.999996f, 47.999992f)
    cubicTo(21.299995f, 54.09999f, 17.999996f, 60.59999f, 13.299996f, 63.899994f)
    cubicTo(11.199997f, 65.399994f, 8.899996f, 65.7f, 6.499996f, 64.299995f)
    cubicTo(7.099996f, 58.899994f, 13.199996f, 55.499996f, 15.699996f, 54.299995f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(165, 13, 18, 255), 0.432f to Color(162, 13, 18, 255), 0.639f to Color(154, 12, 16, 255), 0.799f to Color(140, 9, 11, 255), 0.933f to Color(123, 4, 5, 255), 1.0f to Color(112, 1, 1, 255), start = Offset(10.268f, 68.084f), end = Offset(52.274f, 26.079002f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.8f, 65.5f)
    lineTo(6.5f, 65.6f)
    lineTo(6.5f, 65.4f)
    lineTo(5.9f, 65.1f)
    lineTo(5.3f, 64.799995f)
    lineTo(5.4f, 64.2f)
    cubicTo(6.2000003f, 58.1f, 12.700001f, 54.699997f, 15.200001f, 53.499996f)
    lineTo(16.300001f, 55.099995f)
    cubicTo(13.700001f, 57.799995f, 10.800001f, 59.599995f, 9.400002f, 62.799995f)
    cubicTo(14.800001f, 60.499996f, 18.300001f, 53.999996f, 21.900002f, 47.999996f)
    cubicTo(23.500002f, 45.199997f, 25.7f, 41.599995f, 27.600002f, 37.899994f)
    cubicTo(29.200003f, 34.699993f, 31.700003f, 29.499994f, 31.800003f, 26.999994f)
    lineTo(31.800003f, 26.699995f)
    cubicTo(31.800003f, 23.299995f, 29.700003f, 18.799995f, 29.700003f, 14.499995f)
    cubicTo(29.700003f, 12.399996f, 30.200003f, 10.299995f, 31.600002f, 8.499995f)
    lineTo(31.800003f, 7.999995f)
    lineTo(32.200005f, 7.8999953f)
    cubicTo(32.600006f, 7.7999954f, 33.000004f, 7.7999954f, 33.400005f, 7.7999954f)
    cubicTo(34.400005f, 7.7999954f, 35.300007f, 7.999995f, 36.000004f, 8.599996f)
    cubicTo(36.700005f, 9.199996f, 37.000004f, 10.199996f, 37.000004f, 11.199995f)
    cubicTo(37.000004f, 11.499995f, 37.000004f, 11.899995f, 36.900005f, 12.299995f)
    lineTo(36.600006f, 14.199995f)
    lineTo(35.200005f, 12.899995f)
    cubicTo(34.500004f, 12.199995f, 34.400005f, 11.499995f, 34.200005f, 11.099995f)
    cubicTo(34.100006f, 10.999994f, 34.100006f, 10.899995f, 34.100006f, 10.899995f)
    cubicTo(33.500008f, 11.699995f, 33.200005f, 13.199995f, 33.200005f, 14.899995f)
    cubicTo(33.200005f, 16.399994f, 33.400005f, 17.999994f, 33.700005f, 19.499994f)
    cubicTo(34.000004f, 18.199995f, 34.100006f, 16.899994f, 34.200005f, 15.399994f)
    lineTo(36.200005f, 15.099994f)
    cubicTo(36.500004f, 16.199993f, 36.700005f, 17.399994f, 36.700005f, 18.399994f)
    cubicTo(36.700005f, 21.199993f, 35.900005f, 23.699993f, 35.900005f, 25.799994f)
    lineTo(35.900005f, 26.699993f)
    cubicTo(36.700005f, 34.699993f, 44.100006f, 39.699993f, 50.100006f, 43.499992f)
    cubicTo(52.500008f, 43.399994f, 55.700005f, 43.09999f, 58.700005f, 43.09999f)
    cubicTo(62.700005f, 43.09999f, 66.600006f, 43.59999f, 68.50001f, 46.499992f)
    lineTo(68.80001f, 47.09999f)
    lineTo(68.40001f, 47.59999f)
    cubicTo(68.30001f, 47.79999f, 68.00001f, 47.999992f, 67.80001f, 48.09999f)
    lineTo(70.10001f, 47.999992f)
    lineTo(67.500015f, 49.899994f)
    cubicTo(66.10001f, 50.899994f, 64.500015f, 51.299995f, 62.900017f, 51.299995f)
    cubicTo(57.600018f, 51.199997f, 52.000015f, 47.199997f, 48.700016f, 46.799995f)
    cubicTo(48.100018f, 46.699997f, 47.500015f, 46.699997f, 46.900017f, 46.699997f)
    cubicTo(40.80002f, 46.699997f, 33.80002f, 50.199997f, 27.300016f, 50.699997f)
    lineTo(26.800016f, 48.799995f)
    cubicTo(31.900017f, 46.499996f, 38.000015f, 45.299995f, 44.000015f, 44.199997f)
    cubicTo(40.100014f, 41.1f, 36.700016f, 37.499996f, 34.400017f, 32.799995f)
    cubicTo(32.400017f, 37.999996f, 29.200016f, 43.499996f, 25.900017f, 48.899994f)
    cubicTo(22.200016f, 54.999992f, 18.900017f, 61.499992f, 13.900017f, 65.09999f)
    cubicTo(12.600017f, 65.99999f, 11.200017f, 66.59999f, 9.700017f, 66.59999f)
    cubicTo(8.600017f, 66.09999f, 7.700017f, 65.899994f, 6.800017f, 65.49999f)
    close()
    moveTo(66.0f, 47.7f)
    lineTo(66.5f, 47.7f)
    cubicTo(66.0f, 47.5f, 65.7f, 47.3f, 65.4f, 47.100002f)
    cubicTo(64.9f, 46.800003f, 64.5f, 46.500004f, 64.200005f, 46.500004f)
    cubicTo(63.100006f, 46.200005f, 61.600006f, 46.100002f, 60.000004f, 46.100002f)
    lineTo(57.100002f, 46.100002f)
    cubicTo(59.600002f, 47.100002f, 62.500004f, 47.7f, 66.0f, 47.7f)
    close()
    moveTo(51.1f, 45.0f)
    lineTo(51.0f, 45.0f)
    lineTo(51.1f, 45.0f)
    close()
    moveTo(48.6f, 44.4f)
    cubicTo(46.899998f, 43.300003f, 45.1f, 42.100002f, 43.3f, 40.800003f)
    cubicTo(44.399998f, 41.800003f, 45.6f, 42.700005f, 46.8f, 43.600002f)
    lineTo(47.899998f, 44.4f)
    lineTo(48.6f, 44.4f)
    close()
    moveTo(33.8f, 26.400002f)
    lineTo(33.8f, 26.800001f)
    cubicTo(33.8f, 27.2f, 33.7f, 27.6f, 33.7f, 28.000002f)
    lineTo(34.0f, 26.900002f)
    lineTo(34.0f, 26.500002f)
    cubicTo(34.0f, 26.200003f, 33.9f, 25.900002f, 33.9f, 25.600002f)
    lineTo(33.800003f, 25.300003f)
    cubicTo(33.700005f, 25.600002f, 33.800003f, 26.000004f, 33.800003f, 26.400003f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(165, 13, 18, 255), 0.432f to Color(162, 13, 18, 255), 0.639f to Color(154, 12, 16, 255), 0.799f to Color(140, 9, 11, 255), 0.933f to Color(123, 4, 5, 255), 1.0f to Color(112, 1, 1, 255), start = Offset(9.782f, 69.125f), end = Offset(53.239f, 25.668f), tileMode = TileMode.Clamp)
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
            return 0.740000307559967
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

