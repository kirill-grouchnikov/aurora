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
class ext_gradle : Painter() {
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
    moveTo(10.2f, 87.1f)
    lineTo(10.2f, 85.299995f)
    lineTo(15.0f, 85.299995f)
    lineTo(15.0f, 89.6f)
    cubicTo(14.5f, 90.0f, 13.9f, 90.4f, 13.0f, 90.799995f)
    cubicTo(12.1f, 91.1f, 11.3f, 91.299995f, 10.4f, 91.299995f)
    cubicTo(9.299999f, 91.299995f, 8.299999f, 91.1f, 7.3999996f, 90.6f)
    cubicTo(6.5999994f, 90.1f, 5.8999996f, 89.5f, 5.4999995f, 88.6f)
    cubicTo(5.0999994f, 87.7f, 4.8999996f, 86.799995f, 4.8999996f, 85.7f)
    cubicTo(4.8999996f, 84.6f, 5.0999994f, 83.6f, 5.5999994f, 82.7f)
    cubicTo(6.0999994f, 81.799995f, 6.799999f, 81.2f, 7.6999993f, 80.7f)
    cubicTo(8.4f, 80.299995f, 9.299999f, 80.2f, 10.299999f, 80.2f)
    cubicTo(11.599999f, 80.2f, 12.699999f, 80.5f, 13.4f, 81.0f)
    cubicTo(14.2f, 81.6f, 14.599999f, 82.3f, 14.9f, 83.3f)
    lineTo(12.7f, 83.700005f)
    cubicTo(12.5f, 83.200005f, 12.3f, 82.8f, 11.8f, 82.50001f)
    cubicTo(11.400001f, 82.200005f, 10.900001f, 82.00001f, 10.2f, 82.00001f)
    cubicTo(9.2f, 82.00001f, 8.5f, 82.30001f, 7.8999996f, 82.90001f)
    cubicTo(7.2999997f, 83.50001f, 7.0999994f, 84.40001f, 7.0999994f, 85.600006f)
    cubicTo(7.0999994f, 86.90001f, 7.3999996f, 87.8f, 7.9999995f, 88.50001f)
    cubicTo(8.599999f, 89.100006f, 9.299999f, 89.50001f, 10.2f, 89.50001f)
    cubicTo(10.7f, 89.50001f, 11.099999f, 89.40001f, 11.599999f, 89.200005f)
    cubicTo(12.099999f, 89.00001f, 12.499999f, 88.8f, 12.799999f, 88.600006f)
    lineTo(12.799999f, 87.200005f)
    lineTo(10.199999f, 87.200005f)
    close()
    moveTo(17.0f, 91.1f)
    lineTo(17.0f, 80.3f)
    lineTo(21.6f, 80.3f)
    cubicTo(22.800001f, 80.3f, 23.6f, 80.4f, 24.1f, 80.600006f)
    cubicTo(24.6f, 80.8f, 25.0f, 81.100006f, 25.4f, 81.600006f)
    cubicTo(25.8f, 82.100006f, 25.9f, 82.700005f, 25.9f, 83.3f)
    cubicTo(25.9f, 84.100006f, 25.699999f, 84.8f, 25.199999f, 85.3f)
    cubicTo(24.699999f, 85.8f, 23.999998f, 86.200005f, 23.099998f, 86.3f)
    cubicTo(23.599998f, 86.600006f, 23.999998f, 86.9f, 24.3f, 87.200005f)
    cubicTo(24.599998f, 87.50001f, 25.0f, 88.100006f, 25.5f, 88.9f)
    lineTo(26.8f, 91.0f)
    lineTo(24.199999f, 91.0f)
    lineTo(22.599998f, 88.7f)
    cubicTo(21.999998f, 87.899994f, 21.699999f, 87.299995f, 21.399998f, 87.1f)
    cubicTo(21.199997f, 86.9f, 20.999998f, 86.7f, 20.699997f, 86.7f)
    cubicTo(20.499996f, 86.6f, 20.099997f, 86.6f, 19.599997f, 86.6f)
    lineTo(19.199997f, 86.6f)
    lineTo(19.199997f, 91.1f)
    lineTo(17.0f, 91.1f)
    close()
    moveTo(19.2f, 84.9f)
    lineTo(20.800001f, 84.9f)
    cubicTo(21.800001f, 84.9f, 22.500002f, 84.9f, 22.800001f, 84.8f)
    cubicTo(23.1f, 84.700005f, 23.300001f, 84.600006f, 23.400002f, 84.3f)
    cubicTo(23.500002f, 84.0f, 23.600002f, 83.8f, 23.600002f, 83.5f)
    cubicTo(23.600002f, 83.1f, 23.500002f, 82.8f, 23.300003f, 82.6f)
    cubicTo(23.100002f, 82.4f, 22.800003f, 82.2f, 22.500004f, 82.2f)
    lineTo(19.200005f, 82.2f)
    lineTo(19.200005f, 84.899994f)
    close()
    moveTo(37.6f, 91.1f)
    lineTo(35.199997f, 91.1f)
    lineTo(34.299995f, 88.7f)
    lineTo(30.0f, 88.7f)
    lineTo(29.1f, 91.1f)
    lineTo(26.800001f, 91.1f)
    lineTo(31.0f, 80.3f)
    lineTo(33.3f, 80.3f)
    lineTo(37.6f, 91.100006f)
    close()
    moveTo(33.6f, 86.799995f)
    lineTo(32.1f, 82.799995f)
    lineTo(30.599998f, 86.799995f)
    lineTo(33.6f, 86.799995f)
    close()
    moveTo(38.8f, 80.299995f)
    lineTo(42.8f, 80.299995f)
    cubicTo(43.7f, 80.299995f, 44.399998f, 80.399994f, 44.899998f, 80.49999f)
    cubicTo(45.499996f, 80.69999f, 46.1f, 80.99999f, 46.499996f, 81.49999f)
    cubicTo(46.899994f, 81.99999f, 47.299995f, 82.59999f, 47.499996f, 83.19999f)
    cubicTo(47.699997f, 83.89999f, 47.899998f, 84.69999f, 47.899998f, 85.69999f)
    cubicTo(47.899998f, 86.59999f, 47.8f, 87.29999f, 47.6f, 87.99999f)
    cubicTo(47.3f, 88.799995f, 46.899998f, 89.399994f, 46.399998f, 89.899994f)
    cubicTo(45.999996f, 90.299995f, 45.499996f, 90.59999f, 44.899998f, 90.799995f)
    cubicTo(44.399998f, 90.99999f, 43.8f, 90.99999f, 42.999996f, 90.99999f)
    lineTo(38.899998f, 90.99999f)
    lineTo(38.899998f, 80.3f)
    close()
    moveTo(41.0f, 82.1f)
    lineTo(41.0f, 89.2f)
    lineTo(42.6f, 89.2f)
    cubicTo(43.199997f, 89.2f, 43.6f, 89.2f, 43.899998f, 89.1f)
    cubicTo(44.3f, 89.0f, 44.499996f, 88.9f, 44.8f, 88.7f)
    cubicTo(45.0f, 88.5f, 45.2f, 88.1f, 45.399998f, 87.7f)
    cubicTo(45.499996f, 87.2f, 45.6f, 86.6f, 45.6f, 85.7f)
    cubicTo(45.6f, 84.799995f, 45.5f, 84.2f, 45.399998f, 83.799995f)
    cubicTo(45.3f, 83.399994f, 44.999996f, 82.99999f, 44.8f, 82.799995f)
    cubicTo(44.5f, 82.6f, 44.2f, 82.399994f, 43.8f, 82.299995f)
    cubicTo(43.5f, 82.2f, 42.899998f, 82.2f, 42.0f, 82.2f)
    lineTo(41.0f, 82.2f)
    close()
    moveTo(49.7f, 91.1f)
    lineTo(49.7f, 80.4f)
    lineTo(51.9f, 80.4f)
    lineTo(51.9f, 89.200005f)
    lineTo(57.300003f, 89.200005f)
    lineTo(57.300003f, 91.0f)
    lineTo(49.700005f, 91.0f)
    close()
    moveTo(58.9f, 91.1f)
    lineTo(58.9f, 80.3f)
    lineTo(66.9f, 80.3f)
    lineTo(66.9f, 82.100006f)
    lineTo(61.100002f, 82.100006f)
    lineTo(61.100002f, 84.50001f)
    lineTo(66.5f, 84.50001f)
    lineTo(66.5f, 86.30001f)
    lineTo(61.1f, 86.30001f)
    lineTo(61.1f, 89.20001f)
    lineTo(67.1f, 89.20001f)
    lineTo(67.1f, 91.0f)
    lineTo(58.899998f, 91.0f)
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
    moveTo(60.1f, 36.1f)
    cubicTo(58.399998f, 33.0f, 55.399998f, 32.1f, 53.199997f, 32.1f)
    cubicTo(50.6f, 32.1f, 48.399998f, 33.5f, 48.799995f, 34.5f)
    cubicTo(48.899994f, 34.7f, 49.399994f, 35.8f, 49.699997f, 36.2f)
    cubicTo(50.199997f, 36.9f, 50.999996f, 36.3f, 51.199997f, 36.2f)
    cubicTo(51.999996f, 35.7f, 52.899998f, 35.600002f, 53.799995f, 35.7f)
    cubicTo(54.699997f, 35.8f, 55.999996f, 36.4f, 56.899994f, 38.0f)
    cubicTo(58.899994f, 41.8f, 52.799995f, 49.7f, 45.099995f, 44.2f)
    cubicTo(37.299995f, 38.8f, 29.799995f, 40.5f, 26.399994f, 41.7f)
    cubicTo(22.999994f, 42.8f, 21.499994f, 43.9f, 22.799994f, 46.4f)
    cubicTo(24.599993f, 49.800003f, 24.099993f, 48.800003f, 25.799994f, 51.7f)
    cubicTo(28.599993f, 56.3f, 34.799995f, 49.600002f, 34.799995f, 49.600002f)
    cubicTo(30.199995f, 56.4f, 26.299995f, 54.800003f, 24.799995f, 52.4f)
    cubicTo(23.399996f, 50.300003f, 22.399996f, 47.800003f, 22.399996f, 47.800003f)
    cubicTo(10.8f, 51.9f, 13.9f, 70.0f, 13.9f, 70.0f)
    lineTo(19.599998f, 70.0f)
    cubicTo(21.099998f, 63.3f, 26.3f, 63.6f, 27.199999f, 70.0f)
    lineTo(31.599998f, 70.0f)
    cubicTo(35.5f, 57.0f, 45.3f, 70.0f, 45.3f, 70.0f)
    lineTo(51.0f, 70.0f)
    cubicTo(49.4f, 61.1f, 54.2f, 58.4f, 57.2f, 53.2f)
    cubicTo(60.4f, 47.9f, 63.3f, 41.6f, 60.100002f, 36.1f)
    close()
    moveTo(45.3f, 53.2f)
    cubicTo(42.3f, 52.2f, 43.3f, 49.2f, 43.3f, 49.2f)
    cubicTo(43.3f, 49.2f, 45.899998f, 50.0f, 49.5f, 51.3f)
    cubicTo(49.4f, 52.0f, 47.6f, 53.899998f, 45.3f, 53.2f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(37.5f, 69.969f), end = Offset(37.5f, 32.031f), tileMode = TileMode.Clamp)
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

