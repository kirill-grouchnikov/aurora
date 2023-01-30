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
class ext_tmp : Painter() {
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
    moveTo(45.1f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.2f, 99.0f)
    lineTo(0.2f, 1.0f)
    lineTo(45.100002f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.108f, 98.997f), end = Offset(36.108f, 0.99900055f), tileMode = TileMode.Clamp)
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
    moveTo(45.1f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.2f, 99.0f)
    lineTo(0.2f, 1.0f)
    lineTo(45.100002f, 1.0f)
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
    moveTo(13.1f, 91.1f)
    lineTo(13.1f, 74.6f)
    lineTo(7.1000004f, 74.6f)
    lineTo(7.1000004f, 71.2f)
    lineTo(23.0f, 71.2f)
    lineTo(23.0f, 74.6f)
    lineTo(17.1f, 74.6f)
    lineTo(17.1f, 91.1f)
    lineTo(13.1f, 91.1f)
    close()
    moveTo(25.6f, 91.1f)
    lineTo(25.6f, 71.2f)
    lineTo(31.6f, 71.2f)
    lineTo(35.2f, 84.7f)
    lineTo(38.8f, 71.2f)
    lineTo(44.8f, 71.2f)
    lineTo(44.8f, 91.0f)
    lineTo(41.1f, 91.0f)
    lineTo(41.1f, 75.4f)
    lineTo(37.1f, 91.0f)
    lineTo(33.199997f, 91.0f)
    lineTo(29.199997f, 75.4f)
    lineTo(29.199997f, 91.0f)
    lineTo(25.599997f, 91.0f)
    close()
    moveTo(48.800003f, 91.1f)
    lineTo(48.800003f, 71.2f)
    lineTo(55.300003f, 71.2f)
    cubicTo(57.800003f, 71.2f, 59.4f, 71.299995f, 60.100002f, 71.5f)
    cubicTo(61.2f, 71.8f, 62.2f, 72.4f, 63.000004f, 73.4f)
    cubicTo(63.800003f, 74.4f, 64.200005f, 75.700005f, 64.200005f, 77.3f)
    cubicTo(64.200005f, 78.5f, 64.00001f, 79.5f, 63.500004f, 80.4f)
    cubicTo(63.0f, 81.3f, 62.500004f, 81.9f, 61.800003f, 82.4f)
    cubicTo(61.100002f, 82.9f, 60.4f, 83.200005f, 59.700005f, 83.3f)
    cubicTo(58.700005f, 83.5f, 57.300003f, 83.600006f, 55.500004f, 83.600006f)
    lineTo(52.900005f, 83.600006f)
    lineTo(52.900005f, 91.100006f)
    lineTo(48.800007f, 91.100006f)
    close()
    moveTo(52.9f, 74.6f)
    lineTo(52.9f, 80.2f)
    lineTo(55.100002f, 80.2f)
    cubicTo(56.7f, 80.2f, 57.800003f, 80.1f, 58.300003f, 79.899994f)
    cubicTo(58.800003f, 79.7f, 59.300003f, 79.399994f, 59.600002f, 78.899994f)
    cubicTo(59.9f, 78.49999f, 60.100002f, 77.899994f, 60.100002f, 77.399994f)
    cubicTo(60.100002f, 76.7f, 59.9f, 76.09999f, 59.500004f, 75.59999f)
    cubicTo(59.100002f, 75.09999f, 58.500004f, 74.79999f, 57.900005f, 74.69999f)
    cubicTo(57.400005f, 74.59999f, 56.500004f, 74.59999f, 55.000004f, 74.59999f)
    lineTo(52.900005f, 74.59999f)
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
    moveTo(45.1f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.1f, 27.7f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.122f, 27.771004f), end = Offset(58.575f, 14.317001f), tileMode = TileMode.Clamp)
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
    moveTo(45.1f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.1f, 27.7f)
    lineTo(45.1f, 1.0f)
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
    moveTo(24.2f, 41.8f)
    lineTo(28.800001f, 38.0f)
    cubicTo(28.900002f, 37.9f, 29.000002f, 37.9f, 29.000002f, 37.8f)
    cubicTo(29.400002f, 37.399998f, 29.600002f, 36.899998f, 29.600002f, 36.399998f)
    lineTo(29.600002f, 36.199997f)
    lineTo(29.000002f, 28.099997f)
    lineTo(29.000002f, 28.0f)
    cubicTo(28.900002f, 27.3f, 28.400002f, 26.8f, 27.700003f, 26.8f)
    cubicTo(27.000002f, 26.8f, 26.400003f, 27.3f, 26.400003f, 28.0f)
    lineTo(25.900003f, 35.4f)
    cubicTo(25.900003f, 35.600002f, 25.800003f, 35.800003f, 25.700003f, 36.0f)
    lineTo(22.300003f, 40.0f)
    cubicTo(21.900003f, 40.5f, 21.900003f, 41.3f, 22.400003f, 41.7f)
    cubicTo(22.900003f, 42.3f, 23.700003f, 42.3f, 24.200003f, 41.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(25.756f, 26.807f), end = Offset(25.756f, 42.164f), tileMode = TileMode.Clamp)
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
    moveTo(12.0f, 36.2f)
    cubicTo(12.0f, 44.100002f, 18.0f, 50.7f, 25.7f, 51.6f)
    cubicTo(25.7f, 51.0f, 25.6f, 50.399998f, 25.6f, 49.899998f)
    cubicTo(25.6f, 49.1f, 25.6f, 48.399998f, 25.7f, 47.6f)
    cubicTo(20.2f, 46.699997f, 15.900001f, 42.0f, 15.900001f, 36.199997f)
    cubicTo(15.900001f, 29.799997f, 21.1f, 24.699997f, 27.400002f, 24.699997f)
    cubicTo(32.300003f, 24.699997f, 36.5f, 27.799997f, 38.2f, 32.1f)
    cubicTo(39.5f, 31.699999f, 40.8f, 31.399998f, 42.100002f, 31.199999f)
    cubicTo(40.000004f, 25.099998f, 34.2f, 20.699999f, 27.400002f, 20.699999f)
    cubicTo(19.0f, 20.6f, 12.0f, 27.6f, 12.0f, 36.2f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(27.127f, 20.616f), end = Offset(27.127f, 51.578f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.9f, 48.9f)
    lineTo(28.9f, 50.9f)
    cubicTo(28.9f, 51.600002f, 29.5f, 52.2f, 30.199999f, 52.2f)
    lineTo(32.199997f, 52.2f)
    cubicTo(32.499996f, 53.9f, 33.199997f, 55.5f, 34.1f, 56.9f)
    lineTo(32.699997f, 58.300003f)
    cubicTo(32.199997f, 58.800003f, 32.199997f, 59.600002f, 32.699997f, 60.200005f)
    lineTo(34.1f, 61.600006f)
    cubicTo(34.6f, 62.100006f, 35.399998f, 62.100006f, 36.0f, 61.600006f)
    lineTo(37.4f, 60.200005f)
    cubicTo(38.800003f, 61.200005f, 40.4f, 61.800003f, 42.100002f, 62.200005f)
    lineTo(42.100002f, 64.100006f)
    cubicTo(42.100002f, 64.8f, 42.7f, 65.40001f, 43.4f, 65.40001f)
    lineTo(45.4f, 65.40001f)
    cubicTo(46.100002f, 65.40001f, 46.7f, 64.80001f, 46.7f, 64.100006f)
    lineTo(46.7f, 62.200005f)
    cubicTo(48.4f, 61.900005f, 50.0f, 61.200005f, 51.4f, 60.200005f)
    lineTo(52.800003f, 61.500004f)
    cubicTo(53.300003f, 62.000004f, 54.100002f, 62.000004f, 54.700005f, 61.500004f)
    lineTo(56.100006f, 60.100002f)
    cubicTo(56.600006f, 59.600002f, 56.600006f, 58.800003f, 56.100006f, 58.2f)
    lineTo(54.700005f, 56.8f)
    cubicTo(55.700005f, 55.399998f, 56.300003f, 53.7f, 56.600006f, 52.1f)
    lineTo(58.500008f, 52.1f)
    cubicTo(59.20001f, 52.1f, 59.800007f, 51.5f, 59.800007f, 50.8f)
    lineTo(59.800007f, 48.8f)
    cubicTo(59.800007f, 48.1f, 59.20001f, 47.5f, 58.500008f, 47.5f)
    lineTo(56.600006f, 47.5f)
    cubicTo(56.300007f, 45.8f, 55.600006f, 44.2f, 54.600006f, 42.8f)
    lineTo(56.000008f, 41.399998f)
    cubicTo(56.500008f, 40.899998f, 56.500008f, 40.1f, 56.000008f, 39.499996f)
    lineTo(54.600006f, 38.099995f)
    cubicTo(54.100006f, 37.599995f, 53.300007f, 37.599995f, 52.700005f, 38.099995f)
    lineTo(51.300003f, 39.499996f)
    cubicTo(49.9f, 38.499996f, 48.300003f, 37.899998f, 46.600002f, 37.599995f)
    lineTo(46.600002f, 35.599995f)
    cubicTo(46.600002f, 34.899994f, 46.000004f, 34.299995f, 45.300003f, 34.299995f)
    lineTo(43.300003f, 34.299995f)
    cubicTo(42.600002f, 34.299995f, 42.000004f, 34.899994f, 42.000004f, 35.599995f)
    lineTo(42.000004f, 37.599995f)
    cubicTo(40.400005f, 37.899994f, 38.800003f, 38.599995f, 37.300003f, 39.599995f)
    lineTo(36.0f, 38.1f)
    cubicTo(35.5f, 37.6f, 34.7f, 37.6f, 34.1f, 38.1f)
    lineTo(32.699997f, 39.5f)
    cubicTo(32.199997f, 40.0f, 32.199997f, 40.8f, 32.699997f, 41.4f)
    lineTo(34.1f, 42.800003f)
    cubicTo(33.1f, 44.200005f, 32.5f, 45.800003f, 32.199997f, 47.500004f)
    lineTo(30.199997f, 47.500004f)
    cubicTo(29.499996f, 47.500004f, 28.899998f, 48.100002f, 28.899998f, 48.900005f)
    close()
    moveTo(39.6f, 45.100002f)
    cubicTo(42.199997f, 42.4f, 46.5f, 42.4f, 49.199997f, 45.100002f)
    cubicTo(51.899998f, 47.7f, 51.899998f, 52.000004f, 49.199997f, 54.700005f)
    cubicTo(46.6f, 57.400005f, 42.299995f, 57.400005f, 39.6f, 54.700005f)
    cubicTo(37.0f, 52.000004f, 37.0f, 47.700005f, 39.6f, 45.100006f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(44.455f, 34.275f), end = Offset(44.455f, 65.384f), tileMode = TileMode.Clamp)
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
            return 0.13199999928474426
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

