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
class ext_js : Painter() {
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
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(28.1f, 71.5f)
    lineTo(32.1f, 71.5f)
    lineTo(32.1f, 84.2f)
    cubicTo(32.1f, 85.899994f, 31.999998f, 87.1f, 31.699999f, 88.0f)
    cubicTo(31.3f, 89.2f, 30.599998f, 90.1f, 29.599998f, 90.8f)
    cubicTo(28.599998f, 91.5f, 27.199999f, 91.9f, 25.499998f, 91.9f)
    cubicTo(23.499998f, 91.9f, 21.999998f, 91.3f, 20.899998f, 90.200005f)
    cubicTo(19.799997f, 89.100006f, 19.299997f, 87.50001f, 19.299997f, 85.3f)
    lineTo(23.099997f, 84.9f)
    cubicTo(23.099997f, 86.0f, 23.299997f, 86.9f, 23.599997f, 87.3f)
    cubicTo(23.999996f, 88.0f, 24.699997f, 88.4f, 25.599997f, 88.4f)
    cubicTo(26.499996f, 88.4f, 27.099997f, 88.1f, 27.499996f, 87.6f)
    cubicTo(27.899996f, 87.1f, 28.099997f, 86.0f, 28.099997f, 84.4f)
    lineTo(28.099997f, 71.5f)
    close()
    moveTo(35.5f, 85.0f)
    lineTo(39.4f, 84.6f)
    cubicTo(39.600002f, 85.9f, 40.100002f, 86.9f, 40.800003f, 87.5f)
    cubicTo(41.500004f, 88.1f, 42.500004f, 88.4f, 43.700005f, 88.4f)
    cubicTo(45.000004f, 88.4f, 46.000004f, 88.1f, 46.600006f, 87.6f)
    cubicTo(47.300007f, 87.0f, 47.600006f, 86.4f, 47.600006f, 85.7f)
    cubicTo(47.600006f, 85.2f, 47.500008f, 84.799995f, 47.200005f, 84.5f)
    cubicTo(46.900005f, 84.2f, 46.400005f, 83.9f, 45.700005f, 83.6f)
    cubicTo(45.200005f, 83.4f, 44.100006f, 83.1f, 42.500004f, 82.7f)
    cubicTo(40.300003f, 82.2f, 38.800003f, 81.5f, 37.900005f, 80.7f)
    cubicTo(36.700005f, 79.6f, 36.100006f, 78.299995f, 36.100006f, 76.7f)
    cubicTo(36.100006f, 75.7f, 36.400005f, 74.7f, 37.000008f, 73.799995f)
    cubicTo(37.600006f, 72.899994f, 38.40001f, 72.2f, 39.500008f, 71.799995f)
    cubicTo(40.600006f, 71.299995f, 41.90001f, 71.1f, 43.40001f, 71.1f)
    cubicTo(45.90001f, 71.1f, 47.80001f, 71.7f, 49.10001f, 72.799995f)
    cubicTo(50.40001f, 73.899994f, 51.00001f, 75.399994f, 51.10001f, 77.2f)
    lineTo(47.10001f, 77.399994f)
    cubicTo(46.90001f, 76.399994f, 46.60001f, 75.59999f, 46.00001f, 75.2f)
    cubicTo(45.400013f, 74.7f, 44.60001f, 74.5f, 43.400013f, 74.5f)
    cubicTo(42.200012f, 74.5f, 41.300014f, 74.7f, 40.600014f, 75.2f)
    cubicTo(40.200012f, 75.5f, 40.000015f, 75.899994f, 40.000015f, 76.399994f)
    cubicTo(40.000015f, 76.899994f, 40.200016f, 77.299995f, 40.600014f, 77.59999f)
    cubicTo(41.100014f, 77.99999f, 42.400013f, 78.49999f, 44.300014f, 78.899994f)
    cubicTo(46.200016f, 79.299995f, 47.700016f, 79.799995f, 48.700016f, 80.299995f)
    cubicTo(49.700016f, 80.799995f, 50.400017f, 81.49999f, 50.900017f, 82.299995f)
    cubicTo(51.400017f, 83.1f, 51.700016f, 84.2f, 51.700016f, 85.49999f)
    cubicTo(51.700016f, 86.59999f, 51.400017f, 87.69999f, 50.700016f, 88.69999f)
    cubicTo(50.100018f, 89.69999f, 49.200016f, 90.39999f, 48.000015f, 90.89999f)
    cubicTo(46.800014f, 91.39999f, 45.400017f, 91.59998f, 43.600014f, 91.59998f)
    cubicTo(41.100014f, 91.59998f, 39.100014f, 90.999985f, 37.800014f, 89.79998f)
    cubicTo(36.500015f, 88.99998f, 35.700016f, 87.29998f, 35.500015f, 84.99998f)
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

