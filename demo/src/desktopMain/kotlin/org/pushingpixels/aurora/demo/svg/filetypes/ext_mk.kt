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
class ext_mk : Painter() {
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
    moveTo(38.7f, 45.4f)
    lineTo(41.9f, 46.300003f)
    lineTo(43.800003f, 39.300003f)
    lineTo(40.600002f, 38.4f)
    cubicTo(40.600002f, 37.300003f, 40.500004f, 36.100002f, 40.2f, 35.0f)
    lineTo(43.100002f, 33.3f)
    lineTo(39.4f, 27.0f)
    lineTo(36.5f, 28.7f)
    cubicTo(35.7f, 27.900002f, 34.8f, 27.2f, 33.8f, 26.6f)
    lineTo(34.7f, 23.4f)
    lineTo(27.7f, 21.5f)
    lineTo(26.800001f, 24.7f)
    cubicTo(25.7f, 24.7f, 24.500002f, 24.800001f, 23.400002f, 25.1f)
    lineTo(21.7f, 22.2f)
    lineTo(15.400001f, 25.800001f)
    lineTo(17.1f, 28.7f)
    cubicTo(16.300001f, 29.5f, 15.6f, 30.400002f, 15.0f, 31.400002f)
    lineTo(11.8f, 30.500002f)
    lineTo(9.900001f, 37.5f)
    lineTo(13.1f, 38.4f)
    cubicTo(13.1f, 39.5f, 13.200001f, 40.7f, 13.5f, 41.800003f)
    lineTo(10.6f, 43.500004f)
    lineTo(14.200001f, 49.800003f)
    lineTo(17.1f, 48.100002f)
    cubicTo(17.9f, 48.9f, 18.800001f, 49.600002f, 19.800001f, 50.2f)
    lineTo(18.900002f, 53.4f)
    lineTo(25.900002f, 55.300003f)
    lineTo(26.800001f, 52.100002f)
    cubicTo(27.900002f, 52.100002f, 29.1f, 51.9f, 30.2f, 51.7f)
    lineTo(31.900002f, 54.600002f)
    lineTo(38.2f, 51.000004f)
    lineTo(36.5f, 48.100002f)
    cubicTo(37.4f, 47.300003f, 38.1f, 46.4f, 38.7f, 45.4f)
    close()
    moveTo(21.300001f, 41.7f)
    cubicTo(19.500002f, 38.600002f, 20.6f, 34.600002f, 23.7f, 32.9f)
    cubicTo(26.800001f, 31.100002f, 30.800001f, 32.2f, 32.5f, 35.300003f)
    cubicTo(34.3f, 38.4f, 33.2f, 42.4f, 30.1f, 44.100002f)
    cubicTo(27.0f, 45.800003f, 23.0f, 44.800003f, 21.3f, 41.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(26.861f, 55.391f), end = Offset(26.861f, 21.5f), tileMode = TileMode.Clamp)
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
    moveTo(55.4f, 64.0f)
    lineTo(59.5f, 61.6f)
    lineTo(58.4f, 59.699997f)
    cubicTo(58.9f, 59.199997f, 59.4f, 58.6f, 59.800003f, 57.899998f)
    lineTo(61.9f, 58.499996f)
    lineTo(63.100002f, 53.899998f)
    lineTo(61.000004f, 53.399998f)
    cubicTo(61.000004f, 52.699997f, 60.900005f, 51.899998f, 60.700005f, 51.199997f)
    lineTo(62.600006f, 50.1f)
    lineTo(60.200005f, 46.0f)
    lineTo(58.300003f, 47.0f)
    cubicTo(57.800003f, 46.5f, 57.200005f, 46.0f, 56.500004f, 45.6f)
    lineTo(57.100002f, 43.5f)
    lineTo(52.500004f, 42.3f)
    lineTo(51.900005f, 44.399998f)
    cubicTo(51.200005f, 44.399998f, 50.400005f, 44.499996f, 49.700005f, 44.699997f)
    lineTo(48.600006f, 42.799995f)
    lineTo(44.500008f, 45.199997f)
    lineTo(45.600006f, 47.1f)
    cubicTo(45.100006f, 47.6f, 44.600006f, 48.199997f, 44.200005f, 48.899998f)
    lineTo(42.100006f, 48.3f)
    lineTo(40.900005f, 52.899998f)
    lineTo(43.000004f, 53.499996f)
    cubicTo(43.000004f, 54.199997f, 43.100002f, 54.999996f, 43.300003f, 55.699997f)
    lineTo(41.4f, 56.799995f)
    lineTo(43.800003f, 60.899994f)
    lineTo(45.700005f, 59.799995f)
    cubicTo(46.200005f, 60.299995f, 46.800003f, 60.799995f, 47.500004f, 61.199997f)
    lineTo(46.900005f, 63.299995f)
    lineTo(51.500004f, 64.49999f)
    lineTo(52.100002f, 62.399994f)
    cubicTo(52.800003f, 62.399994f, 53.600002f, 62.299995f, 54.300003f, 62.099995f)
    lineTo(55.4f, 63.999996f)
    close()
    moveTo(48.300003f, 55.5f)
    cubicTo(47.100002f, 53.5f, 47.800003f, 50.9f, 49.9f, 49.7f)
    cubicTo(51.9f, 48.5f, 54.5f, 49.2f, 55.7f, 51.2f)
    cubicTo(56.9f, 53.2f, 56.2f, 55.8f, 54.2f, 57.0f)
    cubicTo(52.100002f, 58.2f, 49.5f, 57.6f, 48.3f, 55.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(52.002f, 64.5f), end = Offset(52.002f, 42.336f), tileMode = TileMode.Clamp)
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
    moveTo(16.3f, 91.1f)
    lineTo(16.3f, 71.2f)
    lineTo(22.3f, 71.2f)
    lineTo(25.9f, 84.7f)
    lineTo(29.5f, 71.2f)
    lineTo(35.5f, 71.2f)
    lineTo(35.5f, 91.0f)
    lineTo(31.8f, 91.0f)
    lineTo(31.8f, 75.4f)
    lineTo(27.8f, 91.0f)
    lineTo(24.0f, 91.0f)
    lineTo(20.0f, 75.4f)
    lineTo(20.0f, 91.0f)
    lineTo(16.3f, 91.0f)
    close()
    moveTo(39.6f, 91.1f)
    lineTo(39.6f, 71.2f)
    lineTo(43.6f, 71.2f)
    lineTo(43.6f, 80.0f)
    lineTo(51.699997f, 71.2f)
    lineTo(57.1f, 71.2f)
    lineTo(49.7f, 79.0f)
    lineTo(57.600002f, 91.1f)
    lineTo(52.4f, 91.1f)
    lineTo(46.9f, 81.799995f)
    lineTo(43.600002f, 85.1f)
    lineTo(43.600002f, 91.1f)
    lineTo(39.600002f, 91.1f)
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.122f, 27.771004f), end = Offset(58.575f, 14.317001f), tileMode = TileMode.Clamp)
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

