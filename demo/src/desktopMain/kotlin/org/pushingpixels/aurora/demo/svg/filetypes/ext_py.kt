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
class ext_py : Painter() {
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
    moveTo(19.3f, 90.1f)
    lineTo(19.3f, 70.2f)
    lineTo(25.8f, 70.2f)
    cubicTo(28.3f, 70.2f, 29.9f, 70.299995f, 30.599998f, 70.5f)
    cubicTo(31.8f, 70.8f, 32.699997f, 71.4f, 33.5f, 72.4f)
    cubicTo(34.3f, 73.4f, 34.7f, 74.700005f, 34.7f, 76.3f)
    cubicTo(34.7f, 77.5f, 34.5f, 78.5f, 34.0f, 79.4f)
    cubicTo(33.6f, 80.200005f, 33.0f, 80.9f, 32.3f, 81.4f)
    cubicTo(31.599998f, 81.9f, 30.9f, 82.200005f, 30.199999f, 82.3f)
    cubicTo(29.199999f, 82.5f, 27.8f, 82.600006f, 26.0f, 82.600006f)
    lineTo(23.4f, 82.600006f)
    lineTo(23.4f, 90.100006f)
    lineTo(19.3f, 90.100006f)
    close()
    moveTo(23.3f, 73.6f)
    lineTo(23.3f, 79.2f)
    lineTo(25.5f, 79.2f)
    cubicTo(27.1f, 79.2f, 28.2f, 79.1f, 28.7f, 78.899994f)
    cubicTo(29.2f, 78.7f, 29.7f, 78.399994f, 30.0f, 77.899994f)
    cubicTo(30.3f, 77.49999f, 30.5f, 76.899994f, 30.5f, 76.399994f)
    cubicTo(30.5f, 75.7f, 30.3f, 75.09999f, 29.9f, 74.59999f)
    cubicTo(29.5f, 74.09999f, 28.9f, 73.79999f, 28.3f, 73.69999f)
    cubicTo(27.8f, 73.59999f, 26.8f, 73.59999f, 25.4f, 73.59999f)
    lineTo(23.3f, 73.59999f)
    close()
    moveTo(43.3f, 90.1f)
    lineTo(43.3f, 81.799995f)
    lineTo(35.899998f, 70.299995f)
    lineTo(40.699997f, 70.299995f)
    lineTo(45.399998f, 78.1f)
    lineTo(49.999996f, 70.299995f)
    lineTo(54.699997f, 70.299995f)
    lineTo(47.299995f, 81.799995f)
    lineTo(47.299995f, 90.1f)
    lineTo(43.299995f, 90.1f)
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
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(31.747f, 32.065002f), end = Offset(31.747f, 32.014f), tileMode = TileMode.Clamp)
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
// _0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.6f, 18.6f)
    lineTo(31.0f, 18.6f)
    cubicTo(23.4f, 18.6f, 23.4f, 23.900002f, 23.4f, 23.900002f)
    lineTo(23.4f, 27.2f)
    lineTo(38.5f, 27.2f)
    lineTo(38.5f, 29.300001f)
    lineTo(23.3f, 29.300001f)
    cubicTo(15.699999f, 29.300001f, 15.699999f, 34.600002f, 15.699999f, 34.600002f)
    lineTo(15.699999f, 39.9f)
    cubicTo(15.699999f, 45.2f, 23.3f, 45.2f, 23.3f, 45.2f)
    cubicTo(23.3f, 39.9f, 30.9f, 39.9f, 30.9f, 39.9f)
    lineTo(38.5f, 39.9f)
    cubicTo(46.1f, 39.9f, 46.1f, 34.600002f, 46.1f, 34.600002f)
    lineTo(46.1f, 23.9f)
    cubicTo(46.199997f, 18.599998f, 38.6f, 18.599998f, 38.6f, 18.599998f)
    close()
    moveTo(26.399998f, 25.5f)
    cubicTo(25.599998f, 25.5f, 24.999998f, 24.9f, 24.999998f, 24.1f)
    cubicTo(24.999998f, 23.300001f, 25.599998f, 22.7f, 26.399998f, 22.7f)
    cubicTo(27.199997f, 22.7f, 27.799997f, 23.300001f, 27.799997f, 24.1f)
    cubicTo(27.899998f, 24.800001f, 27.199997f, 25.5f, 26.399998f, 25.5f)
    close()
    moveTo(47.299995f, 35.4f)
    cubicTo(47.299995f, 40.7f, 39.699997f, 40.7f, 39.699997f, 40.7f)
    lineTo(32.199997f, 40.7f)
    cubicTo(24.599997f, 40.7f, 24.599997f, 46.0f, 24.599997f, 46.0f)
    lineTo(24.599997f, 56.7f)
    cubicTo(24.599997f, 62.0f, 32.199997f, 62.0f, 32.199997f, 62.0f)
    lineTo(39.799995f, 62.0f)
    cubicTo(47.399994f, 62.0f, 47.399994f, 56.7f, 47.399994f, 56.7f)
    lineTo(47.399994f, 53.4f)
    lineTo(32.2f, 53.4f)
    lineTo(32.2f, 51.300003f)
    lineTo(47.4f, 51.300003f)
    cubicTo(55.0f, 51.3f, 55.0f, 46.0f, 55.0f, 46.0f)
    lineTo(55.0f, 40.7f)
    cubicTo(54.9f, 35.4f, 47.3f, 35.4f, 47.3f, 35.4f)
    close()
    moveTo(44.299995f, 55.100002f)
    cubicTo(45.099995f, 55.100002f, 45.699997f, 55.7f, 45.699997f, 56.500004f)
    cubicTo(45.699997f, 57.300007f, 45.1f, 57.900005f, 44.299995f, 57.900005f)
    cubicTo(43.499992f, 57.900005f, 42.899994f, 57.300007f, 42.899994f, 56.500004f)
    cubicTo(42.899994f, 55.7f, 43.499992f, 55.100002f, 44.299995f, 55.100002f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(35.35f, 62.151f), end = Offset(35.35f, 18.563004f), tileMode = TileMode.Clamp)
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

