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
class ext_csh : Painter() {
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
    moveTo(30.2f, 40.4f)
    lineTo(43.1f, 40.4f)
    lineTo(43.1f, 43.600002f)
    lineTo(30.2f, 43.600002f)
    lineTo(30.2f, 40.4f)
    close()
    moveTo(30.2f, 46.800003f)
    lineTo(43.1f, 46.800003f)
    lineTo(43.1f, 50.0f)
    lineTo(30.2f, 50.0f)
    lineTo(30.2f, 46.8f)
    close()
    moveTo(30.2f, 53.300003f)
    lineTo(43.1f, 53.300003f)
    lineTo(43.1f, 56.500004f)
    lineTo(30.2f, 56.500004f)
    lineTo(30.2f, 53.300003f)
    close()
    moveTo(52.800003f, 24.200003f)
    lineTo(26.9f, 24.200003f)
    cubicTo(23.3f, 24.200003f, 20.4f, 27.100002f, 20.4f, 30.700003f)
    lineTo(20.4f, 59.800003f)
    lineTo(14.0f, 59.800003f)
    cubicTo(14.0f, 63.4f, 16.9f, 66.3f, 20.5f, 66.3f)
    lineTo(46.3f, 66.3f)
    cubicTo(49.899998f, 66.3f, 52.8f, 63.4f, 52.8f, 59.800003f)
    lineTo(52.8f, 33.9f)
    lineTo(59.3f, 33.9f)
    lineTo(59.3f, 30.7f)
    cubicTo(59.2f, 27.1f, 56.399998f, 24.2f, 52.8f, 24.2f)
    close()
    moveTo(49.600002f, 59.200005f)
    cubicTo(49.600002f, 61.300003f, 47.9f, 63.000004f, 45.800003f, 63.000004f)
    lineTo(22.1f, 63.000004f)
    cubicTo(23.7f, 61.900005f, 23.7f, 59.800003f, 23.7f, 59.800003f)
    lineTo(23.7f, 30.7f)
    cubicTo(23.7f, 28.900002f, 25.1f, 27.5f, 26.900002f, 27.5f)
    cubicTo(28.7f, 27.5f, 30.100002f, 28.9f, 30.100002f, 30.7f)
    lineTo(30.100002f, 33.9f)
    lineTo(49.5f, 33.9f)
    lineTo(49.5f, 59.2f)
    close()
    moveTo(33.4f, 30.7f)
    lineTo(33.4f, 27.5f)
    lineTo(52.800003f, 27.5f)
    cubicTo(55.700005f, 27.5f, 56.000004f, 29.3f, 56.000004f, 30.7f)
    lineTo(33.4f, 30.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.632f, 66.231f), end = Offset(36.632f, 24.231f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.632f, 66.606f), end = Offset(36.632f, 23.856f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.75f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.2f, 40.4f)
    lineTo(43.1f, 40.4f)
    lineTo(43.1f, 43.600002f)
    lineTo(30.2f, 43.600002f)
    lineTo(30.2f, 40.4f)
    close()
    moveTo(30.2f, 46.800003f)
    lineTo(43.1f, 46.800003f)
    lineTo(43.1f, 50.0f)
    lineTo(30.2f, 50.0f)
    lineTo(30.2f, 46.8f)
    close()
    moveTo(30.2f, 53.300003f)
    lineTo(43.1f, 53.300003f)
    lineTo(43.1f, 56.500004f)
    lineTo(30.2f, 56.500004f)
    lineTo(30.2f, 53.300003f)
    close()
    moveTo(52.800003f, 24.200003f)
    lineTo(26.9f, 24.200003f)
    cubicTo(23.3f, 24.200003f, 20.4f, 27.100002f, 20.4f, 30.700003f)
    lineTo(20.4f, 59.800003f)
    lineTo(14.0f, 59.800003f)
    cubicTo(14.0f, 63.4f, 16.9f, 66.3f, 20.5f, 66.3f)
    lineTo(46.3f, 66.3f)
    cubicTo(49.899998f, 66.3f, 52.8f, 63.4f, 52.8f, 59.800003f)
    lineTo(52.8f, 33.9f)
    lineTo(59.3f, 33.9f)
    lineTo(59.3f, 30.7f)
    cubicTo(59.2f, 27.1f, 56.399998f, 24.2f, 52.8f, 24.2f)
    close()
    moveTo(49.600002f, 59.200005f)
    cubicTo(49.600002f, 61.300003f, 47.9f, 63.000004f, 45.800003f, 63.000004f)
    lineTo(22.1f, 63.000004f)
    cubicTo(23.7f, 61.900005f, 23.7f, 59.800003f, 23.7f, 59.800003f)
    lineTo(23.7f, 30.7f)
    cubicTo(23.7f, 28.900002f, 25.1f, 27.5f, 26.900002f, 27.5f)
    cubicTo(28.7f, 27.5f, 30.100002f, 28.9f, 30.100002f, 30.7f)
    lineTo(30.100002f, 33.9f)
    lineTo(49.5f, 33.9f)
    lineTo(49.5f, 59.2f)
    close()
    moveTo(33.4f, 30.7f)
    lineTo(33.4f, 27.5f)
    lineTo(52.800003f, 27.5f)
    cubicTo(55.700005f, 27.5f, 56.000004f, 29.3f, 56.000004f, 30.7f)
    lineTo(33.4f, 30.7f)
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
    moveTo(23.4f, 85.6f)
    lineTo(26.8f, 86.7f)
    cubicTo(26.3f, 88.6f, 25.4f, 90.0f, 24.199999f, 90.899994f)
    cubicTo(22.999998f, 91.79999f, 21.499998f, 92.299995f, 19.599998f, 92.299995f)
    cubicTo(17.3f, 92.299995f, 15.399999f, 91.49999f, 13.999998f, 89.99999f)
    cubicTo(12.5999975f, 88.49999f, 11.799998f, 86.299995f, 11.799998f, 83.59999f)
    cubicTo(11.799998f, 80.69999f, 12.499998f, 78.49999f, 13.999998f, 76.899994f)
    cubicTo(15.499998f, 75.299995f, 17.399998f, 74.49999f, 19.8f, 74.49999f)
    cubicTo(21.9f, 74.49999f, 23.599998f, 75.09999f, 24.9f, 76.399994f)
    cubicTo(25.699999f, 77.09999f, 26.3f, 78.2f, 26.699999f, 79.59999f)
    lineTo(23.3f, 80.399994f)
    cubicTo(23.099998f, 79.49999f, 22.699999f, 78.799995f, 22.0f, 78.299995f)
    cubicTo(21.300001f, 77.799995f, 20.6f, 77.49999f, 19.7f, 77.49999f)
    cubicTo(18.400002f, 77.49999f, 17.400002f, 77.99999f, 16.6f, 78.899994f)
    cubicTo(15.799999f, 79.799995f, 15.400001f, 81.299995f, 15.400001f, 83.299995f)
    cubicTo(15.400001f, 85.49999f, 15.8f, 86.99999f, 16.6f, 87.899994f)
    cubicTo(17.400002f, 88.799995f, 18.4f, 89.299995f, 19.6f, 89.299995f)
    cubicTo(20.5f, 89.299995f, 21.300001f, 88.99999f, 22.0f, 88.399994f)
    cubicTo(22.699999f, 87.799995f, 23.1f, 86.799995f, 23.4f, 85.59999f)
    close()
    moveTo(28.9f, 86.299995f)
    lineTo(32.3f, 85.99999f)
    cubicTo(32.5f, 87.09999f, 32.899998f, 87.99999f, 33.5f, 88.49999f)
    cubicTo(34.100002f, 88.99999f, 35.0f, 89.299995f, 36.0f, 89.299995f)
    cubicTo(37.1f, 89.299995f, 38.0f, 89.1f, 38.5f, 88.6f)
    cubicTo(39.0f, 88.1f, 39.3f, 87.6f, 39.3f, 86.9f)
    cubicTo(39.3f, 86.5f, 39.2f, 86.1f, 38.899998f, 85.9f)
    cubicTo(38.599995f, 85.700005f, 38.199997f, 85.4f, 37.699997f, 85.200005f)
    cubicTo(37.299995f, 85.100006f, 36.399998f, 84.8f, 34.899998f, 84.4f)
    cubicTo(32.999996f, 83.9f, 31.699997f, 83.4f, 30.999998f, 82.700005f)
    cubicTo(29.899998f, 81.8f, 29.399998f, 80.600006f, 29.399998f, 79.200005f)
    cubicTo(29.399998f, 78.3f, 29.599998f, 77.50001f, 30.099998f, 76.700005f)
    cubicTo(30.599998f, 75.9f, 31.3f, 75.4f, 32.199997f, 75.00001f)
    cubicTo(33.099995f, 74.60001f, 34.299995f, 74.40001f, 35.6f, 74.40001f)
    cubicTo(37.8f, 74.40001f, 39.399998f, 74.90001f, 40.5f, 75.80001f)
    cubicTo(41.600002f, 76.70001f, 42.2f, 78.00001f, 42.2f, 79.60001f)
    lineTo(38.7f, 79.80001f)
    cubicTo(38.600002f, 78.90001f, 38.2f, 78.30001f, 37.7f, 77.90001f)
    cubicTo(37.2f, 77.50001f, 36.5f, 77.30001f, 35.5f, 77.30001f)
    cubicTo(34.5f, 77.30001f, 33.7f, 77.50001f, 33.1f, 77.90001f)
    cubicTo(32.699997f, 78.20001f, 32.5f, 78.50001f, 32.5f, 79.00001f)
    cubicTo(32.5f, 79.40001f, 32.7f, 79.80001f, 33.0f, 80.00001f)
    cubicTo(33.4f, 80.40001f, 34.5f, 80.80001f, 36.2f, 81.100006f)
    cubicTo(37.9f, 81.4f, 39.100002f, 81.90001f, 39.9f, 82.3f)
    cubicTo(40.7f, 82.7f, 41.300003f, 83.3f, 41.800003f, 84.0f)
    cubicTo(42.300003f, 84.7f, 42.500004f, 85.7f, 42.500004f, 86.7f)
    cubicTo(42.500004f, 87.7f, 42.200005f, 88.6f, 41.700005f, 89.5f)
    cubicTo(41.200005f, 90.4f, 40.400005f, 91.0f, 39.400005f, 91.4f)
    cubicTo(38.400005f, 91.8f, 37.200005f, 92.0f, 35.700005f, 92.0f)
    cubicTo(33.500004f, 92.0f, 31.900005f, 91.5f, 30.700005f, 90.5f)
    cubicTo(29.500004f, 89.5f, 29.100004f, 88.2f, 28.900005f, 86.3f)
    close()
    moveTo(45.8f, 91.899994f)
    lineTo(45.8f, 74.7f)
    lineTo(49.3f, 74.7f)
    lineTo(49.3f, 81.5f)
    lineTo(56.0f, 81.5f)
    lineTo(56.0f, 74.7f)
    lineTo(59.5f, 74.7f)
    lineTo(59.5f, 91.899994f)
    lineTo(56.0f, 91.899994f)
    lineTo(56.0f, 84.399994f)
    lineTo(49.2f, 84.399994f)
    lineTo(49.2f, 91.899994f)
    lineTo(45.8f, 91.899994f)
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

