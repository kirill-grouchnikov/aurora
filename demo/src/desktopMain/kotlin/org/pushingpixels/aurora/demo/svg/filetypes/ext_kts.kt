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
class ext_kts : Painter() {
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
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.2f, 99.0f), end = Offset(36.2f, 1.0f), tileMode = TileMode.Clamp)
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
    moveTo(10.5f, 90.1f)
    lineTo(10.5f, 70.2f)
    lineTo(14.6f, 70.2f)
    lineTo(14.6f, 79.0f)
    lineTo(22.8f, 70.2f)
    lineTo(28.3f, 70.2f)
    lineTo(20.7f, 78.0f)
    lineTo(28.7f, 90.1f)
    lineTo(23.400002f, 90.1f)
    lineTo(17.900002f, 80.799995f)
    lineTo(14.600001f, 84.1f)
    lineTo(14.600001f, 90.1f)
    lineTo(10.500002f, 90.1f)
    close()
    moveTo(35.3f, 90.1f)
    lineTo(35.3f, 73.6f)
    lineTo(29.3f, 73.6f)
    lineTo(29.3f, 70.2f)
    lineTo(45.3f, 70.2f)
    lineTo(45.3f, 73.6f)
    lineTo(39.3f, 73.6f)
    lineTo(39.3f, 90.1f)
    lineTo(35.3f, 90.1f)
    close()
    moveTo(46.9f, 83.6f)
    lineTo(50.9f, 83.2f)
    cubicTo(51.100002f, 84.5f, 51.600002f, 85.5f, 52.300003f, 86.1f)
    cubicTo(53.000004f, 86.7f, 54.000004f, 87.0f, 55.200005f, 87.0f)
    cubicTo(56.500004f, 87.0f, 57.500004f, 86.7f, 58.200005f, 86.2f)
    cubicTo(58.900005f, 85.7f, 59.200005f, 85.0f, 59.200005f, 84.299995f)
    cubicTo(59.200005f, 83.799995f, 59.100006f, 83.399994f, 58.800003f, 83.1f)
    cubicTo(58.500004f, 82.799995f, 58.000004f, 82.5f, 57.300003f, 82.2f)
    cubicTo(56.800003f, 82.0f, 55.700005f, 81.7f, 54.000004f, 81.299995f)
    cubicTo(51.800003f, 80.799995f, 50.300003f, 80.1f, 49.400005f, 79.299995f)
    cubicTo(48.200005f, 78.2f, 47.500004f, 76.899994f, 47.500004f, 75.299995f)
    cubicTo(47.500004f, 74.299995f, 47.800003f, 73.299995f, 48.400005f, 72.49999f)
    cubicTo(49.000008f, 71.69999f, 49.800007f, 70.99999f, 50.900005f, 70.49999f)
    cubicTo(52.000004f, 69.99999f, 53.300007f, 69.799995f, 54.900005f, 69.799995f)
    cubicTo(57.400005f, 69.799995f, 59.300007f, 70.299995f, 60.600006f, 71.399994f)
    cubicTo(61.900005f, 72.49999f, 62.500008f, 73.899994f, 62.600006f, 75.799995f)
    lineTo(58.500008f, 75.99999f)
    cubicTo(58.300007f, 74.99999f, 58.000008f, 74.19999f, 57.40001f, 73.799995f)
    cubicTo(56.80001f, 73.399994f, 56.000008f, 73.1f, 54.80001f, 73.1f)
    cubicTo(53.60001f, 73.1f, 52.700012f, 73.299995f, 52.00001f, 73.799995f)
    cubicTo(51.60001f, 74.1f, 51.400013f, 74.49999f, 51.400013f, 74.99999f)
    cubicTo(51.400013f, 75.49999f, 51.600014f, 75.899994f, 52.00001f, 76.19999f)
    cubicTo(52.50001f, 76.59999f, 53.80001f, 77.09999f, 55.700012f, 77.49999f)
    cubicTo(57.600014f, 77.899994f, 59.100014f, 78.399994f, 60.100014f, 78.899994f)
    cubicTo(61.000015f, 79.399994f, 61.800014f, 80.09999f, 62.300014f, 80.899994f)
    cubicTo(62.800014f, 81.7f, 63.100014f, 82.799995f, 63.100014f, 84.09999f)
    cubicTo(63.100014f, 85.19999f, 62.800014f, 86.29999f, 62.100014f, 87.29999f)
    cubicTo(61.500015f, 88.29999f, 60.600014f, 88.999985f, 59.400013f, 89.499985f)
    cubicTo(58.200012f, 89.999985f, 56.800014f, 90.19998f, 55.00001f, 90.19998f)
    cubicTo(52.50001f, 90.19998f, 50.50001f, 89.59998f, 49.10001f, 88.499985f)
    cubicTo(48.00001f, 87.499985f, 47.10001f, 85.79999f, 46.90001f, 83.59998f)
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
    moveTo(31.8f, 32.0f)
    cubicTo(31.699999f, 32.1f, 31.699999f, 32.1f, 31.8f, 32.0f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(31.762f, 32.065002f), end = Offset(31.762f, 32.014f), tileMode = TileMode.Clamp)
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
    moveTo(18.5f, 52.2f)
    lineTo(18.5f, 40.1f)
    cubicTo(18.5f, 39.3f, 18.7f, 38.899998f, 19.5f, 38.5f)
    cubicTo(28.6f, 33.5f, 37.7f, 28.4f, 46.7f, 23.4f)
    cubicTo(48.0f, 22.699999f, 49.3f, 22.0f, 50.8f, 22.0f)
    cubicTo(53.399998f, 22.1f, 55.5f, 23.6f, 56.2f, 26.0f)
    cubicTo(57.0f, 28.4f, 56.2f, 31.2f, 54.100002f, 32.6f)
    cubicTo(51.800003f, 34.0f, 49.2f, 35.399998f, 46.800003f, 36.8f)
    cubicTo(37.700005f, 41.8f, 28.600002f, 46.9f, 19.400003f, 51.9f)
    cubicTo(19.200003f, 52.0f, 18.900003f, 52.100002f, 18.500004f, 52.2f)
    close()
    moveTo(32.9f, 47.7f)
    cubicTo(33.2f, 47.5f, 33.5f, 47.4f, 33.7f, 47.100002f)
    cubicTo(36.7f, 45.4f, 39.7f, 43.9f, 42.6f, 42.2f)
    cubicTo(43.3f, 41.9f, 43.699997f, 41.9f, 44.3f, 42.3f)
    cubicTo(47.7f, 45.399998f, 51.2f, 48.399998f, 54.5f, 51.5f)
    cubicTo(56.4f, 53.2f, 56.9f, 55.4f, 56.2f, 57.7f)
    cubicTo(55.5f, 60.0f, 53.9f, 61.4f, 51.4f, 61.7f)
    cubicTo(49.800003f, 61.9f, 48.300003f, 61.5f, 47.100002f, 60.5f)
    cubicTo(42.4f, 56.3f, 37.800003f, 52.1f, 32.9f, 47.7f)
    cubicTo(33.100002f, 47.9f, 33.100002f, 47.8f, 32.9f, 47.7f)
    close()
    moveTo(18.5f, 35.8f)
    cubicTo(18.5f, 32.8f, 18.3f, 29.9f, 18.6f, 27.0f)
    cubicTo(18.9f, 23.8f, 22.2f, 21.8f, 25.6f, 22.1f)
    cubicTo(28.5f, 22.4f, 30.900002f, 25.4f, 30.7f, 28.400002f)
    cubicTo(30.7f, 28.7f, 30.5f, 29.2f, 30.1f, 29.300001f)
    lineTo(18.7f, 35.600002f)
    cubicTo(18.800001f, 35.800003f, 18.7f, 35.7f, 18.5f, 35.800003f)
    close()
    moveTo(30.7f, 48.9f)
    cubicTo(30.7f, 51.7f, 30.800001f, 54.100002f, 30.7f, 56.7f)
    cubicTo(30.5f, 59.7f, 27.7f, 62.0f, 24.7f, 62.0f)
    cubicTo(21.800001f, 62.0f, 19.0f, 59.8f, 18.5f, 56.9f)
    cubicTo(18.4f, 56.0f, 18.5f, 55.5f, 19.4f, 55.0f)
    cubicTo(22.8f, 53.1f, 26.3f, 51.2f, 29.7f, 49.3f)
    cubicTo(29.900002f, 49.2f, 30.300001f, 49.1f, 30.7f, 48.899998f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(37.5f, 62.0f), end = Offset(37.5f, 22.0f), tileMode = TileMode.Clamp)
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.3f, 27.8f), end = Offset(58.85f, 14.25f), tileMode = TileMode.Clamp)
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

