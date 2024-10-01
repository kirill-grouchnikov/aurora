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
class ext_app : Painter() {
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
    moveTo(28.0f, 91.1f)
    lineTo(23.6f, 91.1f)
    lineTo(21.9f, 86.6f)
    lineTo(13.9f, 86.6f)
    lineTo(12.299999f, 91.1f)
    lineTo(8.0f, 91.1f)
    lineTo(15.8f, 71.3f)
    lineTo(20.0f, 71.3f)
    lineTo(28.0f, 91.100006f)
    close()
    moveTo(20.6f, 83.2f)
    lineTo(17.800001f, 75.799995f)
    lineTo(15.100001f, 83.2f)
    lineTo(20.600002f, 83.2f)
    close()
    moveTo(30.1f, 91.1f)
    lineTo(30.1f, 71.2f)
    lineTo(36.6f, 71.2f)
    cubicTo(39.1f, 71.2f, 40.699997f, 71.299995f, 41.399998f, 71.5f)
    cubicTo(42.499996f, 71.8f, 43.499996f, 72.4f, 44.3f, 73.4f)
    cubicTo(45.1f, 74.4f, 45.5f, 75.700005f, 45.5f, 77.3f)
    cubicTo(45.5f, 78.5f, 45.3f, 79.5f, 44.8f, 80.4f)
    cubicTo(44.399998f, 81.200005f, 43.8f, 81.9f, 43.1f, 82.4f)
    cubicTo(42.399998f, 82.9f, 41.699997f, 83.200005f, 41.0f, 83.3f)
    cubicTo(40.0f, 83.5f, 38.6f, 83.600006f, 36.8f, 83.600006f)
    lineTo(34.2f, 83.600006f)
    lineTo(34.2f, 91.100006f)
    lineTo(30.1f, 91.100006f)
    close()
    moveTo(34.2f, 74.6f)
    lineTo(34.2f, 80.2f)
    lineTo(36.4f, 80.2f)
    cubicTo(38.0f, 80.2f, 39.100002f, 80.1f, 39.600002f, 79.899994f)
    cubicTo(40.100002f, 79.7f, 40.600002f, 79.399994f, 40.9f, 78.899994f)
    cubicTo(41.2f, 78.49999f, 41.4f, 77.899994f, 41.4f, 77.399994f)
    cubicTo(41.4f, 76.7f, 41.2f, 76.09999f, 40.800003f, 75.59999f)
    cubicTo(40.4f, 75.09999f, 39.800003f, 74.79999f, 39.200005f, 74.69999f)
    cubicTo(38.700005f, 74.59999f, 37.800003f, 74.59999f, 36.300003f, 74.59999f)
    lineTo(34.200005f, 74.59999f)
    close()
    moveTo(48.7f, 91.1f)
    lineTo(48.7f, 71.2f)
    lineTo(55.2f, 71.2f)
    cubicTo(57.7f, 71.2f, 59.3f, 71.299995f, 60.0f, 71.5f)
    cubicTo(61.1f, 71.8f, 62.1f, 72.4f, 62.9f, 73.4f)
    cubicTo(63.7f, 74.4f, 64.1f, 75.700005f, 64.1f, 77.3f)
    cubicTo(64.1f, 78.5f, 63.899998f, 79.5f, 63.399998f, 80.4f)
    cubicTo(62.999996f, 81.200005f, 62.399998f, 81.9f, 61.699997f, 82.4f)
    cubicTo(60.999996f, 82.9f, 60.299995f, 83.200005f, 59.6f, 83.3f)
    cubicTo(58.6f, 83.5f, 57.199997f, 83.600006f, 55.399998f, 83.600006f)
    lineTo(52.8f, 83.600006f)
    lineTo(52.8f, 91.100006f)
    lineTo(48.7f, 91.100006f)
    close()
    moveTo(52.8f, 74.6f)
    lineTo(52.8f, 80.2f)
    lineTo(55.0f, 80.2f)
    cubicTo(56.6f, 80.2f, 57.7f, 80.1f, 58.2f, 79.899994f)
    cubicTo(58.7f, 79.7f, 59.2f, 79.399994f, 59.5f, 78.899994f)
    cubicTo(59.8f, 78.49999f, 60.0f, 77.899994f, 60.0f, 77.399994f)
    cubicTo(60.0f, 76.7f, 59.8f, 76.09999f, 59.4f, 75.59999f)
    cubicTo(59.0f, 75.09999f, 58.4f, 74.79999f, 57.800003f, 74.69999f)
    cubicTo(57.300003f, 74.59999f, 56.4f, 74.59999f, 54.9f, 74.59999f)
    lineTo(52.800003f, 74.59999f)
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
    moveTo(55.1f, 36.3f)
    cubicTo(55.0f, 35.3f, 54.8f, 34.399998f, 54.5f, 33.399998f)
    lineTo(59.2f, 30.699997f)
    lineTo(54.2f, 22.099997f)
    lineTo(49.5f, 24.799997f)
    cubicTo(48.2f, 23.399998f, 46.6f, 22.199997f, 44.9f, 21.199997f)
    lineTo(46.300003f, 16.099997f)
    lineTo(36.600002f, 13.399997f)
    lineTo(35.2f, 18.499996f)
    cubicTo(34.2f, 18.499996f, 33.3f, 18.499996f, 32.3f, 18.599997f)
    cubicTo(31.3f, 18.699997f, 30.4f, 18.899996f, 29.4f, 19.199997f)
    lineTo(26.699999f, 14.499997f)
    lineTo(18.0f, 19.399998f)
    lineTo(20.7f, 24.099998f)
    cubicTo(19.300001f, 25.499998f, 18.1f, 26.999998f, 17.1f, 28.699999f)
    lineTo(11.900001f, 27.3f)
    lineTo(9.4f, 37.0f)
    lineTo(14.599999f, 38.4f)
    cubicTo(14.599999f, 39.4f, 14.599999f, 40.300003f, 14.799999f, 41.300003f)
    cubicTo(14.9f, 42.300003f, 15.099999f, 43.200005f, 15.4f, 44.200005f)
    lineTo(10.7f, 46.900005f)
    lineTo(15.7f, 55.500008f)
    lineTo(20.4f, 52.800007f)
    cubicTo(21.699999f, 54.20001f, 23.3f, 55.400005f, 25.0f, 56.400005f)
    lineTo(23.6f, 61.500004f)
    lineTo(33.3f, 64.200005f)
    lineTo(34.7f, 59.100006f)
    cubicTo(35.7f, 59.100006f, 36.600002f, 59.100006f, 37.600002f, 59.000008f)
    cubicTo(38.600002f, 58.90001f, 39.500004f, 58.70001f, 40.500004f, 58.40001f)
    lineTo(43.200005f, 63.10001f)
    lineTo(51.900005f, 58.20001f)
    lineTo(49.200005f, 53.500008f)
    cubicTo(50.600006f, 52.20001f, 51.800003f, 50.600006f, 52.800003f, 48.90001f)
    lineTo(58.000004f, 50.30001f)
    lineTo(60.700005f, 40.700012f)
    lineTo(55.500004f, 39.30001f)
    cubicTo(55.300003f, 38.30001f, 55.300003f, 37.30001f, 55.100002f, 36.30001f)
    close()
    moveTo(36.1f, 47.0f)
    cubicTo(31.599998f, 47.6f, 27.399998f, 44.3f, 26.8f, 39.9f)
    cubicTo(26.199999f, 35.4f, 29.5f, 31.300001f, 34.0f, 30.7f)
    cubicTo(38.5f, 30.1f, 42.7f, 33.4f, 43.3f, 37.8f)
    cubicTo(43.8f, 42.3f, 40.6f, 46.4f, 36.1f, 47.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(34.962f, 64.153f), end = Offset(34.962f, 13.529999f), tileMode = TileMode.Clamp)
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
// _0_5
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

