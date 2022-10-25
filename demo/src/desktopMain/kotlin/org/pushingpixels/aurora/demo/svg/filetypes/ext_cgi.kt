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
class ext_cgi : Painter() {
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
    moveTo(16.2f, 55.5f)
    lineTo(30.9f, 65.0f)
    lineTo(30.9f, 58.0f)
    lineTo(56.0f, 58.0f)
    lineTo(56.0f, 53.0f)
    lineTo(30.9f, 53.0f)
    lineTo(30.9f, 46.1f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(31.254f, 65.191f), end = Offset(42.722f, 45.327f), tileMode = TileMode.Clamp)
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
    moveTo(41.5f, 36.6f)
    lineTo(16.4f, 36.6f)
    lineTo(16.4f, 41.699997f)
    lineTo(41.5f, 41.699997f)
    lineTo(41.5f, 48.6f)
    lineTo(56.2f, 39.199997f)
    lineTo(41.5f, 29.699997f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(29.677f, 49.355f), end = Offset(41.146f, 29.491f), tileMode = TileMode.Clamp)
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.037f, 27.813f), end = Offset(58.537f, 14.313f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
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
// _0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.0f, 84.5f)
    lineTo(29.9f, 85.7f)
    cubicTo(29.3f, 87.899994f, 28.3f, 89.5f, 26.9f, 90.6f)
    cubicTo(25.5f, 91.7f, 23.699999f, 92.2f, 21.599998f, 92.2f)
    cubicTo(18.899998f, 92.2f, 16.8f, 91.299995f, 14.999998f, 89.5f)
    cubicTo(13.299998f, 87.7f, 12.399998f, 85.2f, 12.399998f, 82.0f)
    cubicTo(12.399998f, 78.7f, 13.299997f, 76.1f, 14.999998f, 74.2f)
    cubicTo(16.699999f, 72.299995f, 18.999998f, 71.399994f, 21.8f, 71.399994f)
    cubicTo(24.3f, 71.399994f, 26.199999f, 72.09999f, 27.8f, 73.59999f)
    cubicTo(28.699999f, 74.49999f, 29.4f, 75.69999f, 29.9f, 77.29999f)
    lineTo(25.9f, 78.29999f)
    cubicTo(25.699999f, 77.29999f, 25.199999f, 76.39999f, 24.4f, 75.79999f)
    cubicTo(23.6f, 75.19999f, 22.699999f, 74.999985f, 21.6f, 74.999985f)
    cubicTo(20.1f, 74.999985f, 18.9f, 75.499985f, 18.0f, 76.59998f)
    cubicTo(17.1f, 77.69998f, 16.6f, 79.39999f, 16.6f, 81.79998f)
    cubicTo(16.6f, 84.29998f, 17.1f, 86.09998f, 18.0f, 87.19998f)
    cubicTo(18.9f, 88.29998f, 20.1f, 88.79998f, 21.6f, 88.79998f)
    cubicTo(22.7f, 88.79998f, 23.6f, 88.49998f, 24.4f, 87.79998f)
    cubicTo(25.1f, 87.09998f, 25.6f, 85.99998f, 26.0f, 84.49998f)
    close()
    moveTo(42.7f, 84.5f)
    lineTo(42.7f, 81.1f)
    lineTo(51.4f, 81.1f)
    lineTo(51.4f, 89.1f)
    cubicTo(50.600002f, 89.9f, 49.300003f, 90.6f, 47.7f, 91.299995f)
    cubicTo(46.100002f, 91.899994f, 44.5f, 92.2f, 42.8f, 92.2f)
    cubicTo(40.7f, 92.2f, 38.899998f, 91.799995f, 37.3f, 90.899994f)
    cubicTo(35.7f, 89.99999f, 34.6f, 88.799995f, 33.8f, 87.09999f)
    cubicTo(33.0f, 85.49999f, 32.6f, 83.69999f, 32.6f, 81.79999f)
    cubicTo(32.6f, 79.69999f, 33.0f, 77.89999f, 33.899998f, 76.19999f)
    cubicTo(34.799995f, 74.49999f, 36.1f, 73.29999f, 37.699997f, 72.49999f)
    cubicTo(38.999996f, 71.799995f, 40.6f, 71.49999f, 42.499996f, 71.49999f)
    cubicTo(44.999996f, 71.49999f, 46.899998f, 71.99999f, 48.299995f, 73.09999f)
    cubicTo(49.699997f, 74.09999f, 50.599995f, 75.59999f, 50.999996f, 77.399994f)
    lineTo(46.999996f, 78.2f)
    cubicTo(46.699997f, 77.2f, 46.199997f, 76.399994f, 45.399998f, 75.899994f)
    cubicTo(44.6f, 75.399994f, 43.8f, 74.99999f, 42.6f, 74.99999f)
    cubicTo(40.8f, 74.99999f, 39.399998f, 75.59999f, 38.399998f, 76.69999f)
    cubicTo(37.399998f, 77.79999f, 36.8f, 79.49999f, 36.8f, 81.69999f)
    cubicTo(36.8f, 84.09999f, 37.3f, 85.89999f, 38.399998f, 87.09999f)
    cubicTo(39.499996f, 88.29999f, 40.8f, 88.899994f, 42.6f, 88.899994f)
    cubicTo(43.399998f, 88.899994f, 44.3f, 88.7f, 45.1f, 88.399994f)
    cubicTo(46.0f, 88.09999f, 46.699997f, 87.7f, 47.3f, 87.2f)
    lineTo(47.3f, 84.7f)
    lineTo(42.7f, 84.7f)
    close()
    moveTo(55.0f, 91.9f)
    lineTo(55.0f, 71.9f)
    lineTo(59.0f, 71.9f)
    lineTo(59.0f, 91.9f)
    lineTo(55.0f, 91.9f)
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

