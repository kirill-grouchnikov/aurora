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
class ext_php : Painter() {
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
    moveTo(45.2f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(72.3f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.199997f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.2f, 98.986f), end = Offset(36.2f, 0.99900055f), tileMode = TileMode.Clamp)
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
    moveTo(45.2f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(72.3f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.199997f, 1.0f)
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
    moveTo(45.2f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(72.3f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.199997f, 1.0f)
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
    moveTo(9.3f, 90.1f)
    lineTo(9.3f, 70.2f)
    lineTo(15.8f, 70.2f)
    cubicTo(18.3f, 70.2f, 19.9f, 70.299995f, 20.6f, 70.5f)
    cubicTo(21.800001f, 70.8f, 22.7f, 71.4f, 23.5f, 72.4f)
    cubicTo(24.3f, 73.4f, 24.7f, 74.700005f, 24.7f, 76.3f)
    cubicTo(24.7f, 77.5f, 24.5f, 78.5f, 24.0f, 79.4f)
    cubicTo(23.6f, 80.200005f, 23.0f, 80.9f, 22.3f, 81.4f)
    cubicTo(21.599998f, 81.9f, 20.9f, 82.200005f, 20.199999f, 82.3f)
    cubicTo(19.199999f, 82.5f, 17.8f, 82.600006f, 15.999999f, 82.600006f)
    lineTo(13.4f, 82.600006f)
    lineTo(13.4f, 90.100006f)
    lineTo(9.3f, 90.100006f)
    close()
    moveTo(13.4f, 73.6f)
    lineTo(13.4f, 79.2f)
    lineTo(15.599999f, 79.2f)
    cubicTo(17.199999f, 79.2f, 18.3f, 79.1f, 18.8f, 78.899994f)
    cubicTo(19.3f, 78.7f, 19.8f, 78.399994f, 20.099998f, 77.899994f)
    cubicTo(20.399998f, 77.49999f, 20.599998f, 76.899994f, 20.599998f, 76.399994f)
    cubicTo(20.599998f, 75.7f, 20.399998f, 75.09999f, 19.999998f, 74.59999f)
    cubicTo(19.599998f, 74.09999f, 18.999998f, 73.79999f, 18.399998f, 73.69999f)
    cubicTo(17.899998f, 73.59999f, 16.899998f, 73.59999f, 15.499998f, 73.59999f)
    lineTo(13.399998f, 73.59999f)
    close()
    moveTo(28.099998f, 90.1f)
    lineTo(28.099998f, 70.2f)
    lineTo(32.199997f, 70.2f)
    lineTo(32.199997f, 78.0f)
    lineTo(40.199997f, 78.0f)
    lineTo(40.199997f, 70.2f)
    lineTo(44.299995f, 70.2f)
    lineTo(44.299995f, 90.0f)
    lineTo(40.199997f, 90.0f)
    lineTo(40.199997f, 81.3f)
    lineTo(32.199997f, 81.3f)
    lineTo(32.199997f, 90.0f)
    lineTo(28.099997f, 90.0f)
    close()
    moveTo(48.3f, 90.1f)
    lineTo(48.3f, 70.2f)
    lineTo(54.8f, 70.2f)
    cubicTo(57.3f, 70.2f, 58.899998f, 70.299995f, 59.6f, 70.5f)
    cubicTo(60.8f, 70.8f, 61.699997f, 71.4f, 62.5f, 72.4f)
    cubicTo(63.3f, 73.4f, 63.7f, 74.700005f, 63.7f, 76.3f)
    cubicTo(63.7f, 77.5f, 63.5f, 78.5f, 63.0f, 79.4f)
    cubicTo(62.5f, 80.3f, 62.0f, 80.9f, 61.3f, 81.4f)
    cubicTo(60.6f, 81.9f, 59.899998f, 82.200005f, 59.2f, 82.3f)
    cubicTo(58.2f, 82.5f, 56.8f, 82.600006f, 55.0f, 82.600006f)
    lineTo(52.4f, 82.600006f)
    lineTo(52.4f, 90.100006f)
    lineTo(48.300003f, 90.100006f)
    close()
    moveTo(52.399998f, 73.6f)
    lineTo(52.399998f, 79.2f)
    lineTo(54.6f, 79.2f)
    cubicTo(56.199997f, 79.2f, 57.3f, 79.1f, 57.8f, 78.899994f)
    cubicTo(58.3f, 78.7f, 58.8f, 78.399994f, 59.1f, 77.899994f)
    cubicTo(59.399998f, 77.49999f, 59.6f, 76.899994f, 59.6f, 76.399994f)
    cubicTo(59.6f, 75.7f, 59.399998f, 75.09999f, 59.0f, 74.59999f)
    cubicTo(58.6f, 74.09999f, 58.0f, 73.79999f, 57.4f, 73.69999f)
    cubicTo(56.9f, 73.59999f, 55.9f, 73.59999f, 54.5f, 73.59999f)
    lineTo(52.4f, 73.59999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
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
    moveTo(34.1f, 50.9f)
    cubicTo(36.1f, 51.600002f, 39.399998f, 51.5f, 41.8f, 51.2f)
    cubicTo(42.2f, 52.2f, 41.6f, 53.4f, 42.0f, 54.5f)
    cubicTo(42.2f, 55.0f, 42.9f, 55.5f, 43.9f, 56.0f)
    cubicTo(44.100002f, 56.1f, 44.4f, 56.1f, 44.600002f, 56.2f)
    lineTo(44.800003f, 56.4f)
    cubicTo(45.200005f, 56.7f, 46.100002f, 57.2f, 46.300003f, 57.300003f)
    cubicTo(46.600002f, 57.4f, 46.9f, 57.4f, 47.200005f, 57.4f)
    lineTo(48.300003f, 57.4f)
    cubicTo(49.800003f, 57.300003f, 51.300003f, 56.9f, 51.700005f, 56.300003f)
    cubicTo(52.400005f, 55.300003f, 52.000004f, 53.200005f, 51.900005f, 51.4f)
    cubicTo(51.800007f, 49.800003f, 51.600006f, 47.7f, 51.900005f, 46.5f)
    cubicTo(52.000004f, 46.1f, 52.400005f, 45.6f, 52.600006f, 45.1f)
    cubicTo(53.500008f, 43.3f, 54.300007f, 40.3f, 54.000008f, 37.5f)
    cubicTo(53.800007f, 36.2f, 53.20001f, 35.1f, 53.000008f, 34.1f)
    cubicTo(55.000008f, 34.3f, 56.90001f, 33.899998f, 58.70001f, 34.1f)
    cubicTo(59.800007f, 34.199997f, 60.60001f, 34.899998f, 61.60001f, 34.8f)
    cubicTo(61.80001f, 34.3f, 62.40001f, 34.0f, 62.50001f, 33.3f)
    cubicTo(62.60001f, 32.6f, 62.30001f, 31.699999f, 62.00001f, 31.199999f)
    cubicTo(60.60001f, 30.999998f, 59.50001f, 32.3f, 58.200012f, 32.399998f)
    cubicTo(57.80001f, 32.399998f, 57.200012f, 32.3f, 56.700012f, 32.199997f)
    cubicTo(55.100014f, 32.1f, 52.900013f, 32.499996f, 51.50001f, 32.199997f)
    cubicTo(50.50001f, 31.999996f, 49.700012f, 30.899998f, 48.700012f, 30.499996f)
    cubicTo(48.50001f, 30.399996f, 48.100014f, 30.499996f, 47.80001f, 30.299995f)
    cubicTo(47.50001f, 30.199995f, 47.30001f, 29.999996f, 47.10001f, 29.999996f)
    cubicTo(46.00001f, 29.599997f, 44.90001f, 29.199997f, 43.80001f, 28.999996f)
    cubicTo(41.80001f, 28.499996f, 39.00001f, 28.499996f, 36.40001f, 28.699997f)
    cubicTo(35.60001f, 28.799997f, 34.80001f, 29.199997f, 34.000008f, 28.999996f)
    cubicTo(33.40001f, 28.899996f, 33.300007f, 28.499996f, 32.800007f, 28.299995f)
    cubicTo(31.000008f, 27.499996f, 29.200006f, 28.399996f, 28.000008f, 28.999996f)
    cubicTo(27.100008f, 29.399996f, 26.100008f, 30.099997f, 25.200008f, 30.199997f)
    cubicTo(24.300009f, 30.399998f, 23.100008f, 30.199997f, 22.300009f, 30.199997f)
    cubicTo(21.300009f, 30.199997f, 20.100008f, 30.399998f, 19.00001f, 30.499996f)
    cubicTo(18.00001f, 30.699997f, 16.70001f, 30.799995f, 16.10001f, 31.199997f)
    cubicTo(14.60001f, 32.1f, 14.20001f, 35.999996f, 13.70001f, 38.199997f)
    cubicTo(13.5000105f, 39.1f, 13.20001f, 39.899998f, 13.0000105f, 40.799995f)
    cubicTo(12.70001f, 42.699997f, 12.5000105f, 44.699997f, 12.5000105f, 46.499996f)
    cubicTo(12.40001f, 50.199997f, 12.0000105f, 55.399994f, 13.90001f, 56.699997f)
    cubicTo(14.30001f, 56.999996f, 15.70001f, 57.399998f, 16.10001f, 57.199997f)
    cubicTo(16.20001f, 57.199997f, 16.70001f, 56.699997f, 16.80001f, 56.499996f)
    cubicTo(16.900011f, 56.199997f, 16.60001f, 55.799995f, 16.60001f, 55.299995f)
    cubicTo(16.60001f, 54.499996f, 16.40001f, 53.399994f, 16.40001f, 52.499996f)
    cubicTo(16.40001f, 50.299995f, 16.800009f, 47.799995f, 17.40001f, 46.899998f)
    cubicTo(17.40001f, 46.8f, 17.700008f, 46.8f, 17.700008f, 46.699997f)
    cubicTo(17.800009f, 46.499996f, 17.700008f, 46.299995f, 17.90001f, 46.199997f)
    cubicTo(18.300009f, 45.799995f, 19.00001f, 45.299995f, 19.40001f, 45.199997f)
    cubicTo(20.700008f, 44.699997f, 21.40001f, 45.299995f, 22.00001f, 46.1f)
    cubicTo(23.10001f, 47.5f, 23.300009f, 49.8f, 23.40001f, 52.0f)
    lineTo(23.40001f, 53.4f)
    cubicTo(23.40001f, 53.9f, 23.200008f, 54.5f, 23.200008f, 54.800003f)
    cubicTo(23.500008f, 55.700005f, 25.000008f, 56.100002f, 25.600008f, 56.500004f)
    cubicTo(25.600008f, 56.800003f, 25.700008f, 57.200005f, 25.900007f, 57.500004f)
    cubicTo(26.200006f, 58.000004f, 26.700006f, 58.400005f, 27.100008f, 58.600002f)
    cubicTo(28.700008f, 59.500004f, 32.70001f, 58.9f, 33.500008f, 57.9f)
    cubicTo(33.600006f, 57.800003f, 33.70001f, 57.7f, 33.70001f, 57.5f)
    cubicTo(33.800007f, 57.2f, 34.000008f, 56.9f, 34.000008f, 56.7f)
    cubicTo(34.500008f, 54.5f, 33.800007f, 52.9f, 34.100006f, 50.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(37.462f, 58.771f), end = Offset(37.462f, 27.735f), tileMode = TileMode.Clamp)
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
    moveTo(31.8f, 32.0f)
    cubicTo(31.699999f, 32.1f, 31.699999f, 32.1f, 31.8f, 32.0f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(31.747f, 32.065002f), end = Offset(31.747f, 32.014f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(45.2f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(45.2f, 27.7f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.324f, 27.816002f), end = Offset(58.871f, 14.268997f), tileMode = TileMode.Clamp)
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
    moveTo(45.2f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(45.2f, 27.7f)
    lineTo(45.2f, 1.0f)
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
    moveTo(45.2f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(45.2f, 27.7f)
    lineTo(45.2f, 1.0f)
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
            return 0.13099999725818634
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
            return 0.7420000433921814
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

