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
class ext_woff2 : Painter() {
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
    lineTo(71.9f, 27.7f)
    lineTo(71.9f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(190, 192, 219, 255), 0.139f to Color(206, 205, 230, 255), 0.359f to Color(225, 220, 243, 255), 0.617f to Color(239, 230, 251, 255), 1.0f to Color(245, 245, 255, 255), start = Offset(36.0f, 98.995f), end = Offset(36.0f, 1.0f), tileMode = TileMode.Clamp)
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
    lineTo(71.9f, 27.7f)
    lineTo(71.9f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(113, 135, 161, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(71.9f, 27.7f)
    lineTo(71.9f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
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
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.7f, 1.0f)
    lineTo(71.6f, 27.7f)
    lineTo(44.7f, 27.7f)
    lineTo(44.7f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 245, 255, 255), 0.35f to Color(250, 241, 251, 255), 0.532f to Color(237, 231, 244, 255), 0.675f to Color(221, 219, 233, 255), 0.799f to Color(199, 201, 218, 255), 0.908f to Color(173, 179, 199, 255), 1.0f to Color(146, 145, 176, 255), start = Offset(44.754f, 27.765999f), end = Offset(58.198f, 14.322998f), tileMode = TileMode.Clamp)
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
    moveTo(44.7f, 1.0f)
    lineTo(71.6f, 27.7f)
    lineTo(44.7f, 27.7f)
    lineTo(44.7f, 1.0f)
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
    moveTo(44.7f, 1.0f)
    lineTo(71.6f, 27.7f)
    lineTo(44.7f, 27.7f)
    lineTo(44.7f, 1.0f)
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
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.9f, 49.3f)
    lineTo(16.5f, 49.3f)
    lineTo(15.3f, 52.1f)
    cubicTo(14.900001f, 53.0f, 14.7f, 53.8f, 14.7f, 54.399998f)
    cubicTo(14.7f, 55.199997f, 15.0f, 55.8f, 15.7f, 56.199997f)
    cubicTo(16.1f, 56.399998f, 17.1f, 56.6f, 18.6f, 56.699997f)
    lineTo(18.6f, 57.499996f)
    lineTo(8.7f, 57.499996f)
    lineTo(8.7f, 56.699997f)
    cubicTo(9.8f, 56.499996f, 10.599999f, 56.1f, 11.299999f, 55.399998f)
    cubicTo(11.999999f, 54.699997f, 12.799999f, 53.3f, 13.799999f, 51.1f)
    lineTo(24.3f, 28.399998f)
    lineTo(24.699999f, 28.399998f)
    lineTo(35.3f, 51.799995f)
    cubicTo(36.3f, 53.999996f, 37.1f, 55.399994f, 37.8f, 55.999996f)
    cubicTo(38.3f, 56.399998f, 39.0f, 56.699997f, 39.899998f, 56.799995f)
    lineTo(39.899998f, 57.599995f)
    lineTo(25.7f, 57.599995f)
    lineTo(25.7f, 56.799995f)
    lineTo(26.300001f, 56.799995f)
    cubicTo(27.400002f, 56.799995f, 28.2f, 56.599995f, 28.7f, 56.299995f)
    cubicTo(29.0f, 56.099995f, 29.2f, 55.799995f, 29.2f, 55.299995f)
    cubicTo(29.2f, 54.999996f, 29.2f, 54.799995f, 29.1f, 54.499996f)
    cubicTo(29.1f, 54.399998f, 28.9f, 53.799995f, 28.4f, 52.899998f)
    lineTo(26.9f, 49.3f)
    close()
    moveTo(26.1f, 47.7f)
    lineTo(21.7f, 37.9f)
    lineTo(17.2f, 47.7f)
    lineTo(26.1f, 47.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 145, 161, 255))
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
    moveTo(62.1f, 38.6f)
    lineTo(57.899998f, 52.6f)
    lineTo(57.499996f, 54.3f)
    cubicTo(57.499996f, 54.5f, 57.399998f, 54.7f, 57.399998f, 54.8f)
    cubicTo(57.399998f, 55.0f, 57.499996f, 55.1f, 57.6f, 55.2f)
    cubicTo(57.699997f, 55.3f, 57.8f, 55.4f, 58.0f, 55.4f)
    cubicTo(58.3f, 55.4f, 58.7f, 55.2f, 59.3f, 54.7f)
    cubicTo(59.5f, 54.5f, 60.0f, 53.9f, 60.899998f, 52.8f)
    lineTo(61.699997f, 53.2f)
    cubicTo(60.699997f, 55.0f, 59.6f, 56.3f, 58.399998f, 57.2f)
    cubicTo(57.199997f, 58.0f, 55.999996f, 58.4f, 54.6f, 58.4f)
    cubicTo(53.8f, 58.4f, 53.1f, 58.2f, 52.699997f, 57.800003f)
    cubicTo(52.299995f, 57.4f, 51.999996f, 56.9f, 51.999996f, 56.200005f)
    cubicTo(51.999996f, 55.700005f, 52.199997f, 54.600006f, 52.699997f, 53.200005f)
    lineTo(53.199997f, 51.500004f)
    cubicTo(51.499996f, 54.300003f, 49.899998f, 56.300003f, 48.399998f, 57.400005f)
    cubicTo(47.499996f, 58.100006f, 46.499996f, 58.400005f, 45.499996f, 58.400005f)
    cubicTo(44.199997f, 58.400005f, 43.199997f, 57.900005f, 42.599995f, 56.800007f)
    cubicTo(41.999996f, 55.70001f, 41.699993f, 54.600006f, 41.699993f, 53.20001f)
    cubicTo(41.699993f, 51.20001f, 42.29999f, 49.000008f, 43.599995f, 46.40001f)
    cubicTo(44.899998f, 43.80001f, 46.499996f, 41.80001f, 48.499996f, 40.20001f)
    cubicTo(50.199997f, 38.90001f, 51.699997f, 38.300007f, 53.199997f, 38.300007f)
    cubicTo(53.999996f, 38.300007f, 54.699997f, 38.500008f, 55.199997f, 39.000008f)
    cubicTo(55.699997f, 39.500008f, 56.1f, 40.300007f, 56.299995f, 41.500008f)
    lineTo(57.099995f, 38.90001f)
    lineTo(62.099995f, 38.60001f)
    close()
    moveTo(55.1f, 42.6f)
    cubicTo(55.1f, 41.5f, 54.899998f, 40.6f, 54.6f, 40.1f)
    cubicTo(54.3f, 39.699997f, 54.0f, 39.6f, 53.5f, 39.6f)
    cubicTo(53.1f, 39.6f, 52.6f, 39.8f, 52.1f, 40.199997f)
    cubicTo(51.1f, 41.1f, 50.1f, 42.799995f, 49.0f, 45.499996f)
    cubicTo(47.9f, 48.199997f, 47.3f, 50.399998f, 47.3f, 52.399998f)
    cubicTo(47.3f, 53.1f, 47.399998f, 53.699997f, 47.7f, 53.999996f)
    cubicTo(48.0f, 54.299995f, 48.2f, 54.499996f, 48.5f, 54.499996f)
    cubicTo(49.2f, 54.499996f, 49.8f, 54.099995f, 50.5f, 53.399998f)
    cubicTo(51.5f, 52.399998f, 52.3f, 51.1f, 53.1f, 49.6f)
    cubicTo(54.399998f, 47.0f, 55.1f, 44.699997f, 55.1f, 42.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(54, 69, 77, 255))
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
    moveTo(9.4f, 91.1f)
    lineTo(6.5f, 78.9f)
    lineTo(9.0f, 78.9f)
    lineTo(10.8f, 87.3f)
    lineTo(13.0f, 78.9f)
    lineTo(16.0f, 78.9f)
    lineTo(18.1f, 87.4f)
    lineTo(20.0f, 78.9f)
    lineTo(22.5f, 78.9f)
    lineTo(19.5f, 91.1f)
    lineTo(16.9f, 91.1f)
    lineTo(14.5f, 82.0f)
    lineTo(12.1f, 91.1f)
    lineTo(9.4f, 91.1f)
    close()
    moveTo(23.3f, 85.1f)
    cubicTo(23.3f, 83.9f, 23.5f, 82.799995f, 23.9f, 82.0f)
    cubicTo(24.199999f, 81.4f, 24.6f, 80.8f, 25.0f, 80.3f)
    cubicTo(25.5f, 79.8f, 26.0f, 79.4f, 26.6f, 79.200005f)
    cubicTo(27.4f, 78.9f, 28.2f, 78.700005f, 29.2f, 78.700005f)
    cubicTo(31.0f, 78.700005f, 32.4f, 79.3f, 33.5f, 80.4f)
    cubicTo(34.6f, 81.5f, 35.1f, 83.1f, 35.1f, 85.0f)
    cubicTo(35.1f, 87.0f, 34.6f, 88.5f, 33.5f, 89.6f)
    cubicTo(32.4f, 90.7f, 31.0f, 91.299995f, 29.2f, 91.299995f)
    cubicTo(27.400002f, 91.299995f, 25.900002f, 90.7f, 24.900002f, 89.6f)
    cubicTo(23.800001f, 88.5f, 23.300001f, 87.0f, 23.300001f, 85.1f)
    close()
    moveTo(25.8f, 85.0f)
    cubicTo(25.8f, 86.4f, 26.099998f, 87.4f, 26.8f, 88.1f)
    cubicTo(27.5f, 88.799995f, 28.3f, 89.2f, 29.199999f, 89.2f)
    cubicTo(30.099998f, 89.2f, 30.999998f, 88.799995f, 31.599998f, 88.1f)
    cubicTo(32.199997f, 87.4f, 32.5f, 86.299995f, 32.5f, 84.9f)
    cubicTo(32.5f, 83.5f, 32.2f, 82.5f, 31.6f, 81.8f)
    cubicTo(31.0f, 81.100006f, 30.2f, 80.8f, 29.1f, 80.8f)
    cubicTo(28.1f, 80.8f, 27.300001f, 81.100006f, 26.6f, 81.8f)
    cubicTo(25.9f, 82.5f, 25.800001f, 83.600006f, 25.800001f, 85.0f)
    close()
    moveTo(37.1f, 91.1f)
    lineTo(37.1f, 78.9f)
    lineTo(45.5f, 78.9f)
    lineTo(45.5f, 81.0f)
    lineTo(39.6f, 81.0f)
    lineTo(39.6f, 83.9f)
    lineTo(44.699997f, 83.9f)
    lineTo(44.699997f, 86.0f)
    lineTo(39.6f, 86.0f)
    lineTo(39.6f, 91.2f)
    lineTo(37.1f, 91.2f)
    close()
    moveTo(47.6f, 91.1f)
    lineTo(47.6f, 78.9f)
    lineTo(56.0f, 78.9f)
    lineTo(56.0f, 81.0f)
    lineTo(50.0f, 81.0f)
    lineTo(50.0f, 83.9f)
    lineTo(55.1f, 83.9f)
    lineTo(55.1f, 86.0f)
    lineTo(50.0f, 86.0f)
    lineTo(50.0f, 91.2f)
    lineTo(47.6f, 91.2f)
    close()
    moveTo(65.399994f, 88.9f)
    lineTo(65.399994f, 91.1f)
    lineTo(57.199993f, 91.1f)
    cubicTo(57.29999f, 90.299995f, 57.599995f, 89.5f, 57.999992f, 88.799995f)
    cubicTo(58.39999f, 88.09999f, 59.29999f, 87.1f, 60.59999f, 85.899994f)
    cubicTo(61.69999f, 84.899994f, 62.29999f, 84.299995f, 62.499992f, 83.899994f)
    cubicTo(62.79999f, 83.399994f, 62.999992f, 82.899994f, 62.999992f, 82.49999f)
    cubicTo(62.999992f, 81.99999f, 62.899994f, 81.59999f, 62.59999f, 81.299995f)
    cubicTo(62.29999f, 80.99999f, 61.89999f, 80.899994f, 61.39999f, 80.899994f)
    cubicTo(60.89999f, 80.899994f, 60.49999f, 80.99999f, 60.19999f, 81.299995f)
    cubicTo(59.89999f, 81.6f, 59.69999f, 82.1f, 59.69999f, 82.799995f)
    lineTo(57.39999f, 82.6f)
    cubicTo(57.49999f, 81.299995f, 57.99999f, 80.4f, 58.69999f, 79.799995f)
    cubicTo(59.39999f, 79.2f, 60.39999f, 78.99999f, 61.49999f, 78.99999f)
    cubicTo(62.69999f, 78.99999f, 63.69999f, 79.299995f, 64.29999f, 79.99999f)
    cubicTo(64.999985f, 80.59999f, 65.29999f, 81.49999f, 65.29999f, 82.399994f)
    cubicTo(65.29999f, 82.899994f, 65.19999f, 83.49999f, 64.999985f, 83.99999f)
    cubicTo(64.79999f, 84.49999f, 64.499985f, 84.99999f, 64.09998f, 85.59999f)
    cubicTo(63.799984f, 85.99999f, 63.299984f, 86.49999f, 62.599983f, 87.19999f)
    cubicTo(61.899982f, 87.89999f, 61.399982f, 88.29999f, 61.19998f, 88.49999f)
    cubicTo(60.99998f, 88.69999f, 60.79998f, 88.899994f, 60.69998f, 89.09999f)
    lineTo(65.39998f, 89.09999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 98, 123, 255))
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
            return 0.7379999756813049
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

