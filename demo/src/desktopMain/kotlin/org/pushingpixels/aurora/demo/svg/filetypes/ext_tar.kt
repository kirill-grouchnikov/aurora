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
class ext_tar : Painter() {
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
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.8f, 1.0f)
    lineTo(71.8f, 27.7f)
    lineTo(71.8f, 99.0f)
    lineTo(-0.19999695f, 99.0f)
    lineTo(-0.19999695f, 1.0f)
    lineTo(44.800003f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 196, 2, 255), 0.038f to Color(241, 200, 41, 255), 0.147f to Color(244, 210, 100, 255), 0.258f to Color(247, 220, 139, 255), 0.372f to Color(249, 229, 172, 255), 0.488f to Color(251, 236, 199, 255), 0.606f to Color(252, 243, 221, 255), 0.728f to Color(254, 249, 238, 255), 0.856f to Color(255, 253, 249, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(35.75f, 99.005f), end = Offset(35.75f, 0.99900055f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(44.8f, 1.0f)
    lineTo(71.8f, 27.7f)
    lineTo(71.8f, 99.0f)
    lineTo(-0.19999695f, 99.0f)
    lineTo(-0.19999695f, 1.0f)
    lineTo(44.800003f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(186, 156, 2, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.8f, 1.0f)
    lineTo(71.8f, 27.7f)
    lineTo(71.8f, 99.0f)
    lineTo(-0.19999695f, 99.0f)
    lineTo(-0.19999695f, 1.0f)
    lineTo(44.800003f, 1.0f)
    close()
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
// _0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.8f, 92.1f)
    lineTo(14.8f, 75.6f)
    lineTo(8.8f, 75.6f)
    lineTo(8.8f, 72.2f)
    lineTo(24.7f, 72.2f)
    lineTo(24.7f, 75.6f)
    lineTo(18.800001f, 75.6f)
    lineTo(18.800001f, 92.1f)
    lineTo(14.800001f, 92.1f)
    close()
    moveTo(43.4f, 92.1f)
    lineTo(39.0f, 92.1f)
    lineTo(37.2f, 87.6f)
    lineTo(29.2f, 87.6f)
    lineTo(27.5f, 92.1f)
    lineTo(23.2f, 92.1f)
    lineTo(31.0f, 72.3f)
    lineTo(35.3f, 72.3f)
    lineTo(43.4f, 92.100006f)
    close()
    moveTo(35.9f, 84.2f)
    lineTo(33.100002f, 76.799995f)
    lineTo(30.400002f, 84.2f)
    lineTo(35.9f, 84.2f)
    close()
    moveTo(45.5f, 92.1f)
    lineTo(45.5f, 72.2f)
    lineTo(54.0f, 72.2f)
    cubicTo(56.1f, 72.2f, 57.7f, 72.399994f, 58.7f, 72.7f)
    cubicTo(59.7f, 73.0f, 60.4f, 73.7f, 61.0f, 74.6f)
    cubicTo(61.6f, 75.5f, 61.9f, 76.6f, 61.9f, 77.7f)
    cubicTo(61.9f, 79.2f, 61.5f, 80.399994f, 60.600002f, 81.399994f)
    cubicTo(59.700005f, 82.399994f, 58.4f, 82.99999f, 56.600002f, 83.2f)
    cubicTo(57.500004f, 83.7f, 58.2f, 84.299995f, 58.800003f, 84.899994f)
    cubicTo(59.4f, 85.49999f, 60.100002f, 86.59999f, 61.100002f, 88.09999f)
    lineTo(63.500004f, 91.99999f)
    lineTo(58.700005f, 91.99999f)
    lineTo(55.800003f, 87.69999f)
    cubicTo(54.800003f, 86.19999f, 54.000004f, 85.19999f, 53.700005f, 84.79999f)
    cubicTo(53.400005f, 84.39999f, 52.900005f, 84.09999f, 52.500004f, 83.999985f)
    cubicTo(52.100002f, 83.89999f, 51.400005f, 83.79999f, 50.500004f, 83.79999f)
    lineTo(49.700005f, 83.79999f)
    lineTo(49.700005f, 92.09999f)
    lineTo(45.500004f, 92.09999f)
    close()
    moveTo(49.6f, 80.6f)
    lineTo(52.6f, 80.6f)
    cubicTo(54.5f, 80.6f, 55.8f, 80.5f, 56.199997f, 80.4f)
    cubicTo(56.699997f, 80.200005f, 57.1f, 80.0f, 57.299995f, 79.6f)
    cubicTo(57.599995f, 79.2f, 57.699997f, 78.7f, 57.699997f, 78.1f)
    cubicTo(57.699997f, 77.4f, 57.499996f, 76.9f, 57.199997f, 76.5f)
    cubicTo(56.799995f, 76.1f, 56.299995f, 75.8f, 55.699997f, 75.7f)
    cubicTo(55.399998f, 75.7f, 54.399998f, 75.6f, 52.699997f, 75.6f)
    lineTo(49.499996f, 75.6f)
    lineTo(49.499996f, 80.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, -952.3619995117188f, 0.0f, 1.0f)
))}){
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
    moveTo(30.0f, 973.4f)
    cubicTo(29.9f, 973.4f, 29.8f, 973.4f, 29.7f, 973.5f)
    lineTo(13.0f, 981.1f)
    cubicTo(12.5f, 981.3f, 12.3f, 982.1f, 12.7f, 982.6f)
    lineTo(17.9f, 988.69995f)
    lineTo(12.7f, 994.69995f)
    cubicTo(12.4f, 995.1f, 12.5f, 995.89996f, 13.0f, 996.19995f)
    lineTo(18.2f, 998.6f)
    lineTo(18.2f, 1008.69995f)
    cubicTo(18.2f, 1009.1f, 18.400002f, 1009.39996f, 18.800001f, 1009.6f)
    lineTo(35.9f, 1017.19995f)
    cubicTo(36.100002f, 1017.2999f, 36.4f, 1017.2999f, 36.7f, 1017.19995f)
    lineTo(53.9f, 1009.6f)
    cubicTo(54.2f, 1009.5f, 54.5f, 1009.1f, 54.5f, 1008.69995f)
    lineTo(54.5f, 998.6f)
    lineTo(59.7f, 996.19995f)
    cubicTo(60.2f, 995.99994f, 60.4f, 995.19995f, 60.0f, 994.69995f)
    lineTo(54.8f, 988.69995f)
    lineTo(60.0f, 982.6f)
    cubicTo(60.4f, 982.19995f, 60.2f, 981.39996f, 59.7f, 981.1f)
    lineTo(43.0f, 973.5f)
    cubicTo(42.9f, 973.4f, 42.7f, 973.4f, 42.5f, 973.4f)
    cubicTo(42.3f, 973.4f, 42.0f, 973.5f, 41.9f, 973.7f)
    lineTo(36.4f, 979.60004f)
    lineTo(30.900002f, 973.7f)
    cubicTo(30.600002f, 973.5f, 30.300001f, 973.3f, 30.000002f, 973.4f)
    close()
    moveTo(29.8f, 975.5f)
    lineTo(34.6f, 980.7f)
    lineTo(19.3f, 987.5f)
    lineTo(14.9f, 982.3f)
    lineTo(29.8f, 975.5f)
    close()
    moveTo(42.699997f, 975.5f)
    lineTo(57.6f, 982.3f)
    lineTo(53.199997f, 987.5f)
    lineTo(37.899998f, 980.7f)
    lineTo(42.699997f, 975.5f)
    close()
    moveTo(36.299995f, 982.0f)
    lineTo(51.099995f, 988.6f)
    lineTo(36.299995f, 995.19995f)
    lineTo(21.499996f, 988.6f)
    lineTo(36.299995f, 982.0f)
    close()
    moveTo(19.399996f, 989.8f)
    lineTo(34.699997f, 996.6f)
    lineTo(29.899998f, 1001.8f)
    lineTo(15.0f, 995.0f)
    lineTo(19.4f, 989.8f)
    close()
    moveTo(53.199997f, 989.8f)
    lineTo(57.6f, 995.0f)
    lineTo(42.699997f, 1001.8f)
    lineTo(37.899998f, 996.6f)
    lineTo(53.199997f, 989.8f)
    close()
    moveTo(35.299995f, 998.7f)
    lineTo(35.299995f, 1014.8f)
    lineTo(20.0f, 1008.0f)
    lineTo(20.0f, 999.4f)
    lineTo(29.6f, 1003.80005f)
    cubicTo(30.0f, 1004.00006f, 30.4f, 1003.9f, 30.7f, 1003.60004f)
    lineTo(35.3f, 998.7f)
    close()
    moveTo(37.199997f, 998.7f)
    lineTo(41.699997f, 1003.60004f)
    cubicTo(41.999996f, 1003.9f, 42.399998f, 1004.00006f, 42.799995f, 1003.80005f)
    lineTo(52.399994f, 999.4f)
    lineTo(52.399994f, 1008.0f)
    lineTo(37.099995f, 1014.8f)
    lineTo(37.099995f, 998.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(164, 125, 3, 255), 0.533f to Color(222, 190, 0, 255), 0.639f to Color(207, 173, 4, 255), 1.0f to Color(160, 120, 2, 255), start = Offset(12.471f, 995.277f), end = Offset(60.078f, 995.277f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.005f to Color(164, 125, 3, 255), 0.533f to Color(222, 190, 0, 255), 0.639f to Color(207, 173, 4, 255), 1.0f to Color(160, 120, 2, 255), start = Offset(11.721f, 995.277f), end = Offset(60.828f, 995.277f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.5f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.0f, 973.4f)
    cubicTo(29.9f, 973.4f, 29.8f, 973.4f, 29.7f, 973.5f)
    lineTo(13.0f, 981.1f)
    cubicTo(12.5f, 981.3f, 12.3f, 982.1f, 12.7f, 982.6f)
    lineTo(17.9f, 988.69995f)
    lineTo(12.7f, 994.69995f)
    cubicTo(12.4f, 995.1f, 12.5f, 995.89996f, 13.0f, 996.19995f)
    lineTo(18.2f, 998.6f)
    lineTo(18.2f, 1008.69995f)
    cubicTo(18.2f, 1009.1f, 18.400002f, 1009.39996f, 18.800001f, 1009.6f)
    lineTo(35.9f, 1017.19995f)
    cubicTo(36.100002f, 1017.2999f, 36.4f, 1017.2999f, 36.7f, 1017.19995f)
    lineTo(53.9f, 1009.6f)
    cubicTo(54.2f, 1009.5f, 54.5f, 1009.1f, 54.5f, 1008.69995f)
    lineTo(54.5f, 998.6f)
    lineTo(59.7f, 996.19995f)
    cubicTo(60.2f, 995.99994f, 60.4f, 995.19995f, 60.0f, 994.69995f)
    lineTo(54.8f, 988.69995f)
    lineTo(60.0f, 982.6f)
    cubicTo(60.4f, 982.19995f, 60.2f, 981.39996f, 59.7f, 981.1f)
    lineTo(43.0f, 973.5f)
    cubicTo(42.9f, 973.4f, 42.7f, 973.4f, 42.5f, 973.4f)
    cubicTo(42.3f, 973.4f, 42.0f, 973.5f, 41.9f, 973.7f)
    lineTo(36.4f, 979.60004f)
    lineTo(30.900002f, 973.7f)
    cubicTo(30.600002f, 973.5f, 30.300001f, 973.3f, 30.000002f, 973.4f)
    close()
    moveTo(29.8f, 975.5f)
    lineTo(34.6f, 980.7f)
    lineTo(19.3f, 987.5f)
    lineTo(14.9f, 982.3f)
    lineTo(29.8f, 975.5f)
    close()
    moveTo(42.699997f, 975.5f)
    lineTo(57.6f, 982.3f)
    lineTo(53.199997f, 987.5f)
    lineTo(37.899998f, 980.7f)
    lineTo(42.699997f, 975.5f)
    close()
    moveTo(36.299995f, 982.0f)
    lineTo(51.099995f, 988.6f)
    lineTo(36.299995f, 995.19995f)
    lineTo(21.499996f, 988.6f)
    lineTo(36.299995f, 982.0f)
    close()
    moveTo(19.399996f, 989.8f)
    lineTo(34.699997f, 996.6f)
    lineTo(29.899998f, 1001.8f)
    lineTo(15.0f, 995.0f)
    lineTo(19.4f, 989.8f)
    close()
    moveTo(53.199997f, 989.8f)
    lineTo(57.6f, 995.0f)
    lineTo(42.699997f, 1001.8f)
    lineTo(37.899998f, 996.6f)
    lineTo(53.199997f, 989.8f)
    close()
    moveTo(35.299995f, 998.7f)
    lineTo(35.299995f, 1014.8f)
    lineTo(20.0f, 1008.0f)
    lineTo(20.0f, 999.4f)
    lineTo(29.6f, 1003.80005f)
    cubicTo(30.0f, 1004.00006f, 30.4f, 1003.9f, 30.7f, 1003.60004f)
    lineTo(35.3f, 998.7f)
    close()
    moveTo(37.199997f, 998.7f)
    lineTo(41.699997f, 1003.60004f)
    cubicTo(41.999996f, 1003.9f, 42.399998f, 1004.00006f, 42.799995f, 1003.80005f)
    lineTo(52.399994f, 999.4f)
    lineTo(52.399994f, 1008.0f)
    lineTo(37.099995f, 1014.8f)
    lineTo(37.099995f, 998.7f)
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
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.2f, 27.7f)
    lineTo(45.199997f, 27.7f)
    lineTo(45.199997f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(255, 255, 255, 255), 0.234f to Color(255, 254, 251, 255), 0.369f to Color(254, 250, 241, 255), 0.481f to Color(253, 245, 228, 255), 0.579f to Color(252, 240, 210, 255), 0.669f to Color(250, 233, 188, 255), 0.752f to Color(249, 226, 162, 255), 0.831f to Color(247, 218, 131, 255), 0.905f to Color(244, 209, 93, 255), 0.975f to Color(241, 200, 39, 255), 1.0f to Color(239, 196, 2, 255), start = Offset(45.344f, 27.769997f), end = Offset(58.844f, 14.269997f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.2f, 27.7f)
    lineTo(45.199997f, 27.7f)
    lineTo(45.199997f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(186, 156, 2, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.2f, 27.7f)
    lineTo(45.199997f, 27.7f)
    lineTo(45.199997f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
            return 0.12800002098083496
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
            return 0.7410314083099365
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

