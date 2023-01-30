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
class ext_go : Painter() {
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
    moveTo(25.6f, 84.2f)
    lineTo(25.6f, 80.799995f)
    lineTo(34.3f, 80.799995f)
    lineTo(34.3f, 88.799995f)
    cubicTo(33.5f, 89.6f, 32.2f, 90.299995f, 30.599998f, 90.99999f)
    cubicTo(28.999998f, 91.59999f, 27.399998f, 91.899994f, 25.699999f, 91.899994f)
    cubicTo(23.599998f, 91.899994f, 21.8f, 91.49999f, 20.199999f, 90.59999f)
    cubicTo(18.599998f, 89.69999f, 17.499998f, 88.49999f, 16.699999f, 86.79999f)
    cubicTo(15.899999f, 85.19999f, 15.499999f, 83.39999f, 15.499999f, 81.499985f)
    cubicTo(15.499999f, 79.39999f, 15.899999f, 77.59998f, 16.8f, 75.89999f)
    cubicTo(17.7f, 74.19999f, 19.0f, 72.999985f, 20.599998f, 72.19999f)
    cubicTo(21.899998f, 71.49999f, 23.499998f, 71.19999f, 25.399998f, 71.19999f)
    cubicTo(27.899998f, 71.19999f, 29.799997f, 71.69999f, 31.199997f, 72.79999f)
    cubicTo(32.6f, 73.79999f, 33.499996f, 75.29999f, 33.899998f, 77.09999f)
    lineTo(29.899998f, 77.899994f)
    cubicTo(29.599998f, 76.899994f, 29.099998f, 76.09999f, 28.299997f, 75.59999f)
    cubicTo(27.499996f, 75.09999f, 26.499998f, 74.69999f, 25.399998f, 74.69999f)
    cubicTo(23.599998f, 74.69999f, 22.199997f, 75.29999f, 21.199997f, 76.39999f)
    cubicTo(20.199997f, 77.499985f, 19.599997f, 79.19999f, 19.599997f, 81.39999f)
    cubicTo(19.599997f, 83.79999f, 20.099997f, 85.59998f, 21.199997f, 86.79999f)
    cubicTo(22.299997f, 87.999985f, 23.599997f, 88.59999f, 25.399998f, 88.59999f)
    cubicTo(26.199997f, 88.59999f, 27.099998f, 88.399994f, 27.899998f, 88.09999f)
    cubicTo(28.799997f, 87.79999f, 29.499998f, 87.399994f, 30.099998f, 86.899994f)
    lineTo(30.099998f, 84.399994f)
    lineTo(25.599998f, 84.399994f)
    close()
    moveTo(37.2f, 81.6f)
    cubicTo(37.2f, 79.6f, 37.5f, 77.799995f, 38.100002f, 76.5f)
    cubicTo(38.600002f, 75.5f, 39.2f, 74.6f, 40.000004f, 73.8f)
    cubicTo(40.800003f, 73.0f, 41.700005f, 72.4f, 42.600002f, 72.0f)
    cubicTo(43.800003f, 71.5f, 45.300003f, 71.2f, 46.9f, 71.2f)
    cubicTo(49.9f, 71.2f, 52.2f, 72.1f, 54.0f, 73.899994f)
    cubicTo(55.8f, 75.7f, 56.7f, 78.299995f, 56.7f, 81.49999f)
    cubicTo(56.7f, 84.69999f, 55.8f, 87.299995f, 54.100002f, 89.09999f)
    cubicTo(52.300003f, 90.899994f, 50.000004f, 91.79999f, 47.000004f, 91.79999f)
    cubicTo(44.000004f, 91.79999f, 41.600002f, 90.89999f, 39.900005f, 89.09999f)
    cubicTo(38.100006f, 87.29999f, 37.200005f, 84.79999f, 37.200005f, 81.59999f)
    close()
    moveTo(41.4f, 81.5f)
    cubicTo(41.4f, 83.8f, 41.9f, 85.5f, 43.0f, 86.7f)
    cubicTo(44.0f, 87.899994f, 45.4f, 88.5f, 47.0f, 88.5f)
    cubicTo(48.6f, 88.5f, 49.9f, 87.9f, 51.0f, 86.8f)
    cubicTo(52.0f, 85.600006f, 52.6f, 83.9f, 52.6f, 81.600006f)
    cubicTo(52.6f, 79.3f, 52.1f, 77.600006f, 51.1f, 76.50001f)
    cubicTo(50.1f, 75.40001f, 48.8f, 74.80001f, 47.1f, 74.80001f)
    cubicTo(45.399998f, 74.80001f, 44.1f, 75.40001f, 43.1f, 76.50001f)
    cubicTo(41.899998f, 77.50001f, 41.399998f, 79.200005f, 41.399998f, 81.50001f)
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

