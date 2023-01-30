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
class ext_config : Painter() {
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
    moveTo(42.0f, 46.4f)
    cubicTo(42.0f, 43.9f, 40.5f, 41.9f, 38.3f, 41.0f)
    lineTo(38.3f, 33.7f)
    cubicTo(38.3f, 32.5f, 37.399998f, 31.6f, 36.2f, 31.6f)
    cubicTo(35.000004f, 31.6f, 34.100002f, 32.5f, 34.100002f, 33.7f)
    lineTo(34.100002f, 41.0f)
    cubicTo(31.900002f, 41.8f, 30.400002f, 43.9f, 30.400002f, 46.4f)
    cubicTo(30.400002f, 48.9f, 31.900002f, 51.0f, 34.100002f, 51.800003f)
    cubicTo(34.100002f, 51.9f, 34.000004f, 52.100002f, 34.000004f, 52.200005f)
    lineTo(34.000004f, 70.600006f)
    cubicTo(34.000004f, 71.8f, 34.900005f, 72.700005f, 36.100002f, 72.700005f)
    cubicTo(37.3f, 72.700005f, 38.2f, 71.8f, 38.2f, 70.600006f)
    lineTo(38.2f, 52.2f)
    cubicTo(38.2f, 52.0f, 38.2f, 51.9f, 38.100002f, 51.8f)
    cubicTo(40.500004f, 51.0f, 42.000004f, 48.899998f, 42.000004f, 46.399998f)
    close()
    moveTo(36.2f, 48.0f)
    cubicTo(35.3f, 48.0f, 34.600002f, 47.3f, 34.600002f, 46.4f)
    cubicTo(34.600002f, 45.500004f, 35.300003f, 44.800003f, 36.2f, 44.800003f)
    cubicTo(37.1f, 44.800003f, 37.8f, 45.500004f, 37.8f, 46.4f)
    cubicTo(37.8f, 47.3f, 37.1f, 48.0f, 36.2f, 48.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.2f, 72.717f), end = Offset(36.2f, 31.717f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.2f, 73.217f), end = Offset(36.2f, 31.217f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(42.0f, 46.4f)
    cubicTo(42.0f, 43.9f, 40.5f, 41.9f, 38.3f, 41.0f)
    lineTo(38.3f, 33.7f)
    cubicTo(38.3f, 32.5f, 37.399998f, 31.6f, 36.2f, 31.6f)
    cubicTo(35.000004f, 31.6f, 34.100002f, 32.5f, 34.100002f, 33.7f)
    lineTo(34.100002f, 41.0f)
    cubicTo(31.900002f, 41.8f, 30.400002f, 43.9f, 30.400002f, 46.4f)
    cubicTo(30.400002f, 48.9f, 31.900002f, 51.0f, 34.100002f, 51.800003f)
    cubicTo(34.100002f, 51.9f, 34.000004f, 52.100002f, 34.000004f, 52.200005f)
    lineTo(34.000004f, 70.600006f)
    cubicTo(34.000004f, 71.8f, 34.900005f, 72.700005f, 36.100002f, 72.700005f)
    cubicTo(37.3f, 72.700005f, 38.2f, 71.8f, 38.2f, 70.600006f)
    lineTo(38.2f, 52.2f)
    cubicTo(38.2f, 52.0f, 38.2f, 51.9f, 38.100002f, 51.8f)
    cubicTo(40.500004f, 51.0f, 42.000004f, 48.899998f, 42.000004f, 46.399998f)
    close()
    moveTo(36.2f, 48.0f)
    cubicTo(35.3f, 48.0f, 34.600002f, 47.3f, 34.600002f, 46.4f)
    cubicTo(34.600002f, 45.500004f, 35.300003f, 44.800003f, 36.2f, 44.800003f)
    cubicTo(37.1f, 44.800003f, 37.8f, 45.500004f, 37.8f, 46.4f)
    cubicTo(37.8f, 47.3f, 37.1f, 48.0f, 36.2f, 48.0f)
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
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(53.0f, 33.8f)
    cubicTo(53.0f, 32.6f, 52.1f, 31.699999f, 50.9f, 31.699999f)
    cubicTo(49.7f, 31.699999f, 48.800003f, 32.6f, 48.800003f, 33.8f)
    lineTo(48.800003f, 51.0f)
    cubicTo(46.600002f, 51.8f, 45.100002f, 53.9f, 45.100002f, 56.4f)
    cubicTo(45.100002f, 58.9f, 46.600002f, 61.0f, 48.800003f, 61.800003f)
    cubicTo(48.800003f, 61.9f, 48.700005f, 62.100002f, 48.700005f, 62.200005f)
    lineTo(48.700005f, 70.600006f)
    cubicTo(48.700005f, 71.8f, 49.600006f, 72.700005f, 50.800003f, 72.700005f)
    cubicTo(52.000004f, 72.700005f, 52.9f, 71.8f, 52.9f, 70.600006f)
    lineTo(52.9f, 62.200005f)
    cubicTo(52.9f, 62.000004f, 52.9f, 61.900005f, 52.800003f, 61.800003f)
    cubicTo(55.000004f, 61.000004f, 56.500004f, 58.9f, 56.500004f, 56.4f)
    cubicTo(56.500004f, 53.9f, 55.000004f, 51.9f, 52.800003f, 51.0f)
    lineTo(52.800003f, 33.8f)
    close()
    moveTo(50.9f, 58.0f)
    cubicTo(50.0f, 58.0f, 49.300003f, 57.3f, 49.300003f, 56.4f)
    cubicTo(49.300003f, 55.5f, 50.000004f, 54.800003f, 50.9f, 54.800003f)
    cubicTo(51.8f, 54.800003f, 52.5f, 55.500004f, 52.5f, 56.4f)
    cubicTo(52.5f, 57.300003f, 51.8f, 58.0f, 50.9f, 58.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(50.918f, 72.717f), end = Offset(50.918f, 31.717f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(50.918f, 73.217f), end = Offset(50.918f, 31.217f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(53.0f, 33.8f)
    cubicTo(53.0f, 32.6f, 52.1f, 31.699999f, 50.9f, 31.699999f)
    cubicTo(49.7f, 31.699999f, 48.800003f, 32.6f, 48.800003f, 33.8f)
    lineTo(48.800003f, 51.0f)
    cubicTo(46.600002f, 51.8f, 45.100002f, 53.9f, 45.100002f, 56.4f)
    cubicTo(45.100002f, 58.9f, 46.600002f, 61.0f, 48.800003f, 61.800003f)
    cubicTo(48.800003f, 61.9f, 48.700005f, 62.100002f, 48.700005f, 62.200005f)
    lineTo(48.700005f, 70.600006f)
    cubicTo(48.700005f, 71.8f, 49.600006f, 72.700005f, 50.800003f, 72.700005f)
    cubicTo(52.000004f, 72.700005f, 52.9f, 71.8f, 52.9f, 70.600006f)
    lineTo(52.9f, 62.200005f)
    cubicTo(52.9f, 62.000004f, 52.9f, 61.900005f, 52.800003f, 61.800003f)
    cubicTo(55.000004f, 61.000004f, 56.500004f, 58.9f, 56.500004f, 56.4f)
    cubicTo(56.500004f, 53.9f, 55.000004f, 51.9f, 52.800003f, 51.0f)
    lineTo(52.800003f, 33.8f)
    close()
    moveTo(50.9f, 58.0f)
    cubicTo(50.0f, 58.0f, 49.300003f, 57.3f, 49.300003f, 56.4f)
    cubicTo(49.300003f, 55.5f, 50.000004f, 54.800003f, 50.9f, 54.800003f)
    cubicTo(51.8f, 54.800003f, 52.5f, 55.500004f, 52.5f, 56.4f)
    cubicTo(52.5f, 57.300003f, 51.8f, 58.0f, 50.9f, 58.0f)
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
// _0_2_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.4f, 70.6f)
    cubicTo(19.4f, 71.799995f, 20.3f, 72.7f, 21.5f, 72.7f)
    cubicTo(22.7f, 72.7f, 23.6f, 71.799995f, 23.6f, 70.6f)
    lineTo(23.6f, 62.199997f)
    cubicTo(23.6f, 61.999996f, 23.6f, 61.899998f, 23.5f, 61.799995f)
    cubicTo(25.7f, 60.999996f, 27.2f, 58.899994f, 27.2f, 56.399994f)
    cubicTo(27.2f, 53.899994f, 25.7f, 51.899994f, 23.5f, 50.999992f)
    lineTo(23.5f, 33.8f)
    cubicTo(23.5f, 32.6f, 22.6f, 31.699999f, 21.4f, 31.699999f)
    cubicTo(20.199999f, 31.699999f, 19.3f, 32.6f, 19.3f, 33.8f)
    lineTo(19.3f, 51.0f)
    cubicTo(17.099998f, 51.8f, 15.599999f, 53.9f, 15.599999f, 56.4f)
    cubicTo(15.599999f, 58.9f, 17.099998f, 61.0f, 19.3f, 61.800003f)
    cubicTo(19.3f, 61.9f, 19.199999f, 62.100002f, 19.199999f, 62.200005f)
    lineTo(19.199999f, 70.600006f)
    close()
    moveTo(21.5f, 54.8f)
    cubicTo(22.4f, 54.8f, 23.1f, 55.5f, 23.1f, 56.399998f)
    cubicTo(23.1f, 57.3f, 22.4f, 57.999996f, 21.5f, 57.999996f)
    cubicTo(20.6f, 57.999996f, 19.9f, 57.299995f, 19.9f, 56.399998f)
    cubicTo(19.9f, 55.499996f, 20.6f, 54.8f, 21.5f, 54.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(21.482f, 72.717f), end = Offset(21.482f, 31.717f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(21.482f, 73.217f), end = Offset(21.482f, 31.217f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.4f, 70.6f)
    cubicTo(19.4f, 71.799995f, 20.3f, 72.7f, 21.5f, 72.7f)
    cubicTo(22.7f, 72.7f, 23.6f, 71.799995f, 23.6f, 70.6f)
    lineTo(23.6f, 62.199997f)
    cubicTo(23.6f, 61.999996f, 23.6f, 61.899998f, 23.5f, 61.799995f)
    cubicTo(25.7f, 60.999996f, 27.2f, 58.899994f, 27.2f, 56.399994f)
    cubicTo(27.2f, 53.899994f, 25.7f, 51.899994f, 23.5f, 50.999992f)
    lineTo(23.5f, 33.8f)
    cubicTo(23.5f, 32.6f, 22.6f, 31.699999f, 21.4f, 31.699999f)
    cubicTo(20.199999f, 31.699999f, 19.3f, 32.6f, 19.3f, 33.8f)
    lineTo(19.3f, 51.0f)
    cubicTo(17.099998f, 51.8f, 15.599999f, 53.9f, 15.599999f, 56.4f)
    cubicTo(15.599999f, 58.9f, 17.099998f, 61.0f, 19.3f, 61.800003f)
    cubicTo(19.3f, 61.9f, 19.199999f, 62.100002f, 19.199999f, 62.200005f)
    lineTo(19.199999f, 70.600006f)
    close()
    moveTo(21.5f, 54.8f)
    cubicTo(22.4f, 54.8f, 23.1f, 55.5f, 23.1f, 56.399998f)
    cubicTo(23.1f, 57.3f, 22.4f, 57.999996f, 21.5f, 57.999996f)
    cubicTo(20.6f, 57.999996f, 19.9f, 57.299995f, 19.9f, 56.399998f)
    cubicTo(19.9f, 55.499996f, 20.6f, 54.8f, 21.5f, 54.8f)
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

