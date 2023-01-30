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
class ext_csv : Painter() {
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
    moveTo(21.6f, 83.8f)
    lineTo(25.5f, 85.0f)
    cubicTo(24.9f, 87.2f, 23.9f, 88.8f, 22.5f, 89.8f)
    cubicTo(21.1f, 90.9f, 19.3f, 91.4f, 17.2f, 91.4f)
    cubicTo(14.500001f, 91.4f, 12.400001f, 90.5f, 10.700001f, 88.700005f)
    cubicTo(9.000001f, 86.90001f, 8.1f, 84.4f, 8.1f, 81.3f)
    cubicTo(8.1f, 78.0f, 9.0f, 75.4f, 10.700001f, 73.600006f)
    cubicTo(12.400002f, 71.80001f, 14.700001f, 70.90001f, 17.5f, 70.90001f)
    cubicTo(19.9f, 70.90001f, 21.9f, 71.600006f, 23.5f, 73.100006f)
    cubicTo(24.4f, 73.90001f, 25.1f, 75.200005f, 25.5f, 76.8f)
    lineTo(21.5f, 77.700005f)
    cubicTo(21.3f, 76.700005f, 20.8f, 75.9f, 20.0f, 75.3f)
    cubicTo(19.3f, 74.700005f, 18.3f, 74.4f, 17.3f, 74.4f)
    cubicTo(15.799999f, 74.4f, 14.599999f, 74.9f, 13.699999f, 76.0f)
    cubicTo(12.799998f, 77.1f, 12.299999f, 78.8f, 12.299999f, 81.1f)
    cubicTo(12.299999f, 83.6f, 12.799999f, 85.4f, 13.699999f, 86.5f)
    cubicTo(14.599998f, 87.6f, 15.799999f, 88.1f, 17.199999f, 88.1f)
    cubicTo(18.3f, 88.1f, 19.199999f, 87.799995f, 19.999998f, 87.1f)
    cubicTo(20.799997f, 86.4f, 21.299997f, 85.2f, 21.599998f, 83.799995f)
    close()
    moveTo(28.0f, 84.600006f)
    lineTo(31.9f, 84.200005f)
    cubicTo(32.1f, 85.50001f, 32.6f, 86.50001f, 33.3f, 87.100006f)
    cubicTo(34.0f, 87.700005f, 35.0f, 88.00001f, 36.2f, 88.00001f)
    cubicTo(37.5f, 88.00001f, 38.5f, 87.700005f, 39.100002f, 87.200005f)
    cubicTo(39.800003f, 86.700005f, 40.100002f, 86.00001f, 40.100002f, 85.3f)
    cubicTo(40.100002f, 84.8f, 40.000004f, 84.4f, 39.7f, 84.100006f)
    cubicTo(39.4f, 83.8f, 38.9f, 83.50001f, 38.2f, 83.200005f)
    cubicTo(37.7f, 83.00001f, 36.7f, 82.700005f, 35.0f, 82.3f)
    cubicTo(32.8f, 81.8f, 31.3f, 81.100006f, 30.4f, 80.3f)
    cubicTo(29.199999f, 79.200005f, 28.6f, 77.9f, 28.6f, 76.3f)
    cubicTo(28.6f, 75.3f, 28.9f, 74.3f, 29.5f, 73.5f)
    cubicTo(30.1f, 72.6f, 30.9f, 72.0f, 32.0f, 71.5f)
    cubicTo(33.1f, 71.0f, 34.4f, 70.8f, 35.9f, 70.8f)
    cubicTo(38.4f, 70.8f, 40.300003f, 71.3f, 41.600002f, 72.4f)
    cubicTo(42.9f, 73.5f, 43.500004f, 74.9f, 43.600002f, 76.8f)
    lineTo(39.600002f, 77.0f)
    cubicTo(39.4f, 76.0f, 39.100002f, 75.2f, 38.500004f, 74.8f)
    cubicTo(37.900005f, 74.4f, 37.100002f, 74.100006f, 35.900005f, 74.100006f)
    cubicTo(34.700005f, 74.100006f, 33.800007f, 74.3f, 33.100006f, 74.8f)
    cubicTo(32.700005f, 75.100006f, 32.500008f, 75.5f, 32.500008f, 76.0f)
    cubicTo(32.500008f, 76.5f, 32.70001f, 76.9f, 33.100006f, 77.2f)
    cubicTo(33.600006f, 77.6f, 34.800007f, 78.1f, 36.800007f, 78.5f)
    cubicTo(38.800007f, 78.9f, 40.20001f, 79.4f, 41.20001f, 79.9f)
    cubicTo(42.10001f, 80.4f, 42.90001f, 81.1f, 43.40001f, 81.9f)
    cubicTo(43.90001f, 82.700005f, 44.20001f, 83.8f, 44.20001f, 85.1f)
    cubicTo(44.20001f, 86.2f, 43.90001f, 87.299995f, 43.20001f, 88.299995f)
    cubicTo(42.60001f, 89.299995f, 41.70001f, 89.99999f, 40.500008f, 90.49999f)
    cubicTo(39.300007f, 90.99999f, 37.90001f, 91.19999f, 36.20001f, 91.19999f)
    cubicTo(33.70001f, 91.19999f, 31.700008f, 90.59999f, 30.40001f, 89.49999f)
    cubicTo(29.00001f, 88.49999f, 28.200008f, 86.799995f, 28.00001f, 84.59999f)
    close()
    moveTo(52.7f, 91.100006f)
    lineTo(45.600002f, 71.3f)
    lineTo(50.0f, 71.3f)
    lineTo(55.0f, 85.9f)
    lineTo(59.9f, 71.200005f)
    lineTo(64.200005f, 71.200005f)
    lineTo(57.0f, 91.1f)
    lineTo(52.7f, 91.1f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.85f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(47.9f, 26.8f)
    lineTo(61.0f, 26.8f)
    lineTo(61.0f, 35.5f)
    lineTo(47.9f, 35.5f)
    lineTo(47.9f, 26.8f)
    close()
    moveTo(47.9f, 40.199997f)
    lineTo(61.0f, 40.199997f)
    lineTo(61.0f, 49.0f)
    lineTo(47.9f, 49.0f)
    lineTo(47.9f, 40.2f)
    close()
    moveTo(47.9f, 53.6f)
    lineTo(61.0f, 53.6f)
    lineTo(61.0f, 62.3f)
    lineTo(47.9f, 62.3f)
    lineTo(47.9f, 53.6f)
    close()
    moveTo(30.2f, 53.6f)
    lineTo(43.300003f, 53.6f)
    lineTo(43.300003f, 62.3f)
    lineTo(30.2f, 62.3f)
    lineTo(30.2f, 53.6f)
    close()
    moveTo(12.400002f, 53.6f)
    lineTo(25.500002f, 53.6f)
    lineTo(25.500002f, 62.3f)
    lineTo(12.500002f, 62.3f)
    lineTo(12.400002f, 53.6f)
    close()
    moveTo(30.300001f, 26.599998f)
    lineTo(43.4f, 26.599998f)
    lineTo(43.4f, 35.3f)
    lineTo(30.3f, 35.3f)
    lineTo(30.3f, 26.599998f)
    close()
    moveTo(12.6f, 26.599998f)
    lineTo(25.7f, 26.599998f)
    lineTo(25.7f, 35.3f)
    lineTo(12.6f, 35.3f)
    lineTo(12.6f, 26.599998f)
    close()
    moveTo(30.300001f, 40.199997f)
    lineTo(43.4f, 40.199997f)
    lineTo(43.4f, 49.0f)
    lineTo(30.3f, 49.0f)
    lineTo(30.3f, 40.2f)
    close()
    moveTo(12.6f, 40.199997f)
    lineTo(25.7f, 40.199997f)
    lineTo(25.7f, 49.0f)
    lineTo(12.6f, 49.0f)
    lineTo(12.6f, 40.2f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.75f, 62.289f), end = Offset(36.75f, 26.584f), tileMode = TileMode.Clamp)
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

