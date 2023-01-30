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
class ext_plist : Painter() {
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.062f, 27.79f), end = Offset(58.56f, 14.290001f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
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
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.9f, 66.4f)
    lineTo(43.1f, 66.4f)
    lineTo(43.1f, 72.1f)
    lineTo(29.9f, 72.1f)
    lineTo(29.9f, 66.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(237, 240, 243, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.85f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(60.3f, 33.6f)
    cubicTo(59.5f, 32.8f, 58.5f, 32.399998f, 57.399998f, 32.399998f)
    lineTo(15.7f, 32.399998f)
    cubicTo(14.599999f, 32.399998f, 13.6f, 32.8f, 12.799999f, 33.6f)
    cubicTo(11.999999f, 34.399998f, 11.599999f, 35.399998f, 11.599999f, 36.5f)
    lineTo(11.599999f, 64.8f)
    cubicTo(11.599999f, 65.9f, 11.999999f, 66.9f, 12.799999f, 67.700005f)
    cubicTo(13.599999f, 68.50001f, 14.599999f, 68.9f, 15.699999f, 68.9f)
    lineTo(29.899998f, 68.9f)
    cubicTo(29.899998f, 69.6f, 29.799997f, 70.200005f, 29.499998f, 70.9f)
    cubicTo(29.199999f, 71.6f, 28.899998f, 72.200005f, 28.699999f, 72.700005f)
    cubicTo(28.4f, 73.200005f, 28.3f, 73.600006f, 28.3f, 73.8f)
    cubicTo(28.3f, 74.3f, 28.5f, 74.600006f, 28.8f, 75.0f)
    cubicTo(29.099998f, 75.3f, 29.5f, 75.5f, 30.0f, 75.5f)
    lineTo(43.3f, 75.5f)
    cubicTo(43.8f, 75.5f, 44.1f, 75.3f, 44.5f, 75.0f)
    cubicTo(44.8f, 74.7f, 45.0f, 74.3f, 45.0f, 73.8f)
    cubicTo(45.0f, 73.600006f, 44.9f, 73.200005f, 44.6f, 72.700005f)
    cubicTo(44.3f, 72.200005f, 44.0f, 71.600006f, 43.8f, 70.9f)
    cubicTo(43.5f, 70.200005f, 43.399998f, 69.5f, 43.399998f, 68.9f)
    lineTo(57.6f, 68.9f)
    cubicTo(58.699997f, 68.9f, 59.699997f, 68.5f, 60.5f, 67.700005f)
    cubicTo(61.3f, 66.9f, 61.7f, 65.9f, 61.7f, 64.8f)
    lineTo(61.7f, 36.6f)
    cubicTo(61.5f, 35.399998f, 61.100002f, 34.5f, 60.3f, 33.6f)
    close()
    moveTo(58.2f, 58.3f)
    cubicTo(58.2f, 58.5f, 58.100002f, 58.7f, 58.0f, 58.899998f)
    cubicTo(57.8f, 59.1f, 57.6f, 59.1f, 57.4f, 59.1f)
    lineTo(15.7f, 59.1f)
    cubicTo(15.5f, 59.1f, 15.3f, 59.0f, 15.099999f, 58.899998f)
    cubicTo(14.9f, 58.699997f, 14.9f, 58.499996f, 14.9f, 58.3f)
    lineTo(14.9f, 36.6f)
    cubicTo(14.9f, 36.399998f, 15.0f, 36.199997f, 15.099999f, 36.0f)
    cubicTo(15.299999f, 35.8f, 15.499999f, 35.8f, 15.7f, 35.8f)
    lineTo(57.4f, 35.8f)
    cubicTo(57.600002f, 35.8f, 57.800003f, 35.899998f, 58.0f, 36.0f)
    cubicTo(58.2f, 36.2f, 58.2f, 36.4f, 58.2f, 36.6f)
    lineTo(58.2f, 58.3f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.5f, 75.753f), end = Offset(36.5f, 32.419f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.6f, 56.6f)
    cubicTo(32.199997f, 56.3f, 31.899998f, 56.0f, 31.599998f, 55.699997f)
    cubicTo(31.3f, 55.299995f, 30.999998f, 54.899998f, 30.8f, 54.499996f)
    cubicTo(30.199999f, 53.599995f, 29.699999f, 52.599995f, 29.4f, 51.599995f)
    cubicTo(29.0f, 50.399994f, 28.8f, 49.299995f, 28.8f, 48.199993f)
    cubicTo(28.8f, 46.999992f, 29.099998f, 45.899994f, 29.599998f, 44.999992f)
    cubicTo(29.999998f, 44.29999f, 30.599998f, 43.699993f, 31.3f, 43.29999f)
    cubicTo(32.0f, 42.89999f, 32.8f, 42.699993f, 33.5f, 42.59999f)
    cubicTo(33.8f, 42.59999f, 34.1f, 42.59999f, 34.4f, 42.69999f)
    cubicTo(34.600002f, 42.799988f, 34.9f, 42.89999f, 35.2f, 42.99999f)
    cubicTo(35.600002f, 43.19999f, 35.9f, 43.299988f, 36.0f, 43.299988f)
    cubicTo(36.3f, 43.399986f, 36.5f, 43.399986f, 36.6f, 43.399986f)
    cubicTo(36.699997f, 43.399986f, 36.899998f, 43.399986f, 37.1f, 43.299988f)
    cubicTo(37.199997f, 43.299988f, 37.399998f, 43.19999f, 37.699997f, 43.099987f)
    cubicTo(37.999996f, 42.999985f, 38.199997f, 42.899986f, 38.399998f, 42.799988f)
    cubicTo(38.699997f, 42.69999f, 38.999996f, 42.599987f, 39.199997f, 42.599987f)
    cubicTo(39.499996f, 42.599987f, 39.799995f, 42.49999f, 40.1f, 42.599987f)
    cubicTo(40.699997f, 42.599987f, 41.199997f, 42.799988f, 41.6f, 42.899986f)
    cubicTo(42.399998f, 43.199986f, 43.1f, 43.699986f, 43.5f, 44.499985f)
    cubicTo(43.3f, 44.599983f, 43.1f, 44.799984f, 42.9f, 44.899986f)
    cubicTo(42.5f, 45.199986f, 42.2f, 45.599987f, 41.9f, 46.099987f)
    cubicTo(41.600002f, 46.699986f, 41.4f, 47.399986f, 41.4f, 48.099987f)
    cubicTo(41.4f, 48.99999f, 41.600002f, 49.699986f, 42.100002f, 50.399986f)
    cubicTo(42.4f, 50.899986f, 42.800003f, 51.299988f, 43.300003f, 51.599987f)
    cubicTo(43.500004f, 51.799988f, 43.800003f, 51.899986f, 44.000004f, 51.99999f)
    lineTo(43.700005f, 52.89999f)
    cubicTo(43.400005f, 53.49999f, 43.100006f, 54.19999f, 42.700005f, 54.69999f)
    cubicTo(42.400005f, 55.19999f, 42.100006f, 55.59999f, 41.900005f, 55.799988f)
    cubicTo(41.600006f, 56.19999f, 41.300007f, 56.49999f, 41.000004f, 56.69999f)
    cubicTo(40.800003f, 56.799988f, 40.400005f, 56.99999f, 40.000004f, 56.99999f)
    cubicTo(39.700005f, 56.99999f, 39.400005f, 56.99999f, 39.200005f, 56.89999f)
    cubicTo(39.000004f, 56.79999f, 38.700005f, 56.69999f, 38.500004f, 56.59999f)
    cubicTo(38.300003f, 56.499992f, 38.000004f, 56.39999f, 37.800003f, 56.29999f)
    cubicTo(37.500004f, 56.199993f, 37.200005f, 56.199993f, 36.9f, 56.199993f)
    cubicTo(36.600002f, 56.199993f, 36.300003f, 56.199993f, 36.0f, 56.29999f)
    cubicTo(35.8f, 56.39999f, 35.5f, 56.499992f, 35.3f, 56.59999f)
    cubicTo(35.0f, 56.69999f, 34.8f, 56.79999f, 34.6f, 56.89999f)
    cubicTo(34.3f, 56.89999f, 34.0f, 56.99999f, 33.8f, 56.99999f)
    cubicTo(33.399998f, 56.99999f, 33.0f, 56.89999f, 32.6f, 56.599987f)
    close()
    moveTo(38.0f, 42.0f)
    cubicTo(37.5f, 42.3f, 37.0f, 42.4f, 36.4f, 42.3f)
    cubicTo(36.300003f, 41.8f, 36.4f, 41.3f, 36.600002f, 40.7f)
    cubicTo(36.800003f, 40.2f, 37.000004f, 39.8f, 37.4f, 39.4f)
    cubicTo(37.8f, 39.000004f, 38.2f, 38.600002f, 38.7f, 38.4f)
    cubicTo(39.2f, 38.100002f, 39.7f, 38.0f, 40.2f, 38.0f)
    cubicTo(40.3f, 38.5f, 40.2f, 39.1f, 40.0f, 39.6f)
    cubicTo(39.8f, 40.1f, 39.6f, 40.6f, 39.2f, 41.0f)
    cubicTo(38.9f, 41.4f, 38.5f, 41.8f, 38.0f, 42.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.5f, 57.0f), end = Offset(36.5f, 38.0f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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

