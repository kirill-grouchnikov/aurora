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
class ext_cpp : Painter() {
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
    moveTo(22.1f, 83.8f)
    lineTo(26.0f, 85.0f)
    cubicTo(25.4f, 87.2f, 24.4f, 88.8f, 23.0f, 89.8f)
    cubicTo(21.6f, 90.9f, 19.8f, 91.4f, 17.7f, 91.4f)
    cubicTo(15.000001f, 91.4f, 12.900001f, 90.5f, 11.200001f, 88.700005f)
    cubicTo(9.500001f, 86.90001f, 8.6f, 84.4f, 8.6f, 81.3f)
    cubicTo(8.6f, 78.0f, 9.5f, 75.4f, 11.200001f, 73.600006f)
    cubicTo(12.900002f, 71.80001f, 15.200001f, 70.90001f, 18.0f, 70.90001f)
    cubicTo(20.4f, 70.90001f, 22.4f, 71.600006f, 24.0f, 73.100006f)
    cubicTo(24.9f, 73.90001f, 25.6f, 75.200005f, 26.0f, 76.8f)
    lineTo(22.0f, 77.700005f)
    cubicTo(21.8f, 76.700005f, 21.3f, 75.9f, 20.5f, 75.3f)
    cubicTo(19.8f, 74.700005f, 18.8f, 74.4f, 17.8f, 74.4f)
    cubicTo(16.3f, 74.4f, 15.099999f, 74.9f, 14.199999f, 76.0f)
    cubicTo(13.299998f, 77.1f, 12.799999f, 78.8f, 12.799999f, 81.1f)
    cubicTo(12.799999f, 83.6f, 13.299999f, 85.4f, 14.199999f, 86.5f)
    cubicTo(15.099998f, 87.6f, 16.3f, 88.1f, 17.699999f, 88.1f)
    cubicTo(18.8f, 88.1f, 19.699999f, 87.799995f, 20.499998f, 87.1f)
    cubicTo(21.299997f, 86.4f, 21.799997f, 85.2f, 22.099998f, 83.799995f)
    close()
    moveTo(29.5f, 91.100006f)
    lineTo(29.5f, 71.2f)
    lineTo(36.0f, 71.2f)
    cubicTo(38.5f, 71.2f, 40.1f, 71.299995f, 40.8f, 71.5f)
    cubicTo(41.899998f, 71.8f, 42.899998f, 72.4f, 43.7f, 73.4f)
    cubicTo(44.5f, 74.4f, 44.9f, 75.700005f, 44.9f, 77.3f)
    cubicTo(44.9f, 78.5f, 44.7f, 79.5f, 44.2f, 80.4f)
    cubicTo(43.8f, 81.200005f, 43.2f, 81.9f, 42.5f, 82.4f)
    cubicTo(41.8f, 82.9f, 41.1f, 83.200005f, 40.4f, 83.3f)
    cubicTo(39.4f, 83.5f, 38.0f, 83.600006f, 36.2f, 83.600006f)
    lineTo(33.600002f, 83.600006f)
    lineTo(33.600002f, 91.100006f)
    lineTo(29.500002f, 91.100006f)
    close()
    moveTo(33.5f, 74.600006f)
    lineTo(33.5f, 80.200005f)
    lineTo(35.7f, 80.200005f)
    cubicTo(37.3f, 80.200005f, 38.4f, 80.100006f, 38.9f, 79.9f)
    cubicTo(39.4f, 79.700005f, 39.9f, 79.4f, 40.2f, 78.9f)
    cubicTo(40.5f, 78.5f, 40.7f, 77.9f, 40.7f, 77.4f)
    cubicTo(40.7f, 76.700005f, 40.5f, 76.1f, 40.100002f, 75.6f)
    cubicTo(39.7f, 75.1f, 39.100002f, 74.799995f, 38.500004f, 74.7f)
    cubicTo(38.000004f, 74.6f, 37.100002f, 74.6f, 35.600002f, 74.6f)
    lineTo(33.500004f, 74.6f)
    close()
    moveTo(48.1f, 91.100006f)
    lineTo(48.1f, 71.2f)
    lineTo(54.6f, 71.2f)
    cubicTo(57.1f, 71.2f, 58.699997f, 71.299995f, 59.399998f, 71.5f)
    cubicTo(60.499996f, 71.8f, 61.499996f, 72.4f, 62.3f, 73.4f)
    cubicTo(63.1f, 74.4f, 63.5f, 75.700005f, 63.5f, 77.3f)
    cubicTo(63.5f, 78.5f, 63.3f, 79.5f, 62.8f, 80.4f)
    cubicTo(62.399998f, 81.200005f, 61.8f, 81.9f, 61.1f, 82.4f)
    cubicTo(60.399998f, 82.9f, 59.699997f, 83.200005f, 59.0f, 83.3f)
    cubicTo(58.0f, 83.5f, 56.6f, 83.600006f, 54.8f, 83.600006f)
    lineTo(52.2f, 83.600006f)
    lineTo(52.2f, 91.100006f)
    lineTo(48.100002f, 91.100006f)
    close()
    moveTo(52.1f, 74.600006f)
    lineTo(52.1f, 80.200005f)
    lineTo(54.3f, 80.200005f)
    cubicTo(55.899998f, 80.200005f, 57.0f, 80.100006f, 57.5f, 79.9f)
    cubicTo(58.0f, 79.700005f, 58.5f, 79.4f, 58.8f, 78.9f)
    cubicTo(59.1f, 78.5f, 59.3f, 77.9f, 59.3f, 77.4f)
    cubicTo(59.3f, 76.700005f, 59.1f, 76.1f, 58.7f, 75.6f)
    cubicTo(58.3f, 75.1f, 57.7f, 74.799995f, 57.100002f, 74.7f)
    cubicTo(56.600002f, 74.6f, 55.7f, 74.6f, 54.2f, 74.6f)
    lineTo(52.100002f, 74.6f)
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
    moveTo(13.1f, 22.0f)
    lineTo(54.1f, 22.0f)
    lineTo(54.1f, 26.0f)
    lineTo(13.099998f, 26.0f)
    lineTo(13.099998f, 22.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(13.15f, 24.0f), end = Offset(54.15f, 24.0f), tileMode = TileMode.Clamp)
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
    moveTo(19.1f, 33.7f)
    lineTo(60.1f, 33.7f)
    lineTo(60.1f, 37.8f)
    lineTo(19.099998f, 37.8f)
    lineTo(19.099998f, 33.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(19.15f, 35.75f), end = Offset(60.15f, 35.75f), tileMode = TileMode.Clamp)
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
    moveTo(13.1f, 45.7f)
    lineTo(54.1f, 45.7f)
    lineTo(54.1f, 49.8f)
    lineTo(13.099998f, 49.8f)
    lineTo(13.099998f, 45.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(13.15f, 47.75f), end = Offset(54.15f, 47.75f), tileMode = TileMode.Clamp)
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
    moveTo(19.1f, 58.0f)
    lineTo(60.1f, 58.0f)
    lineTo(60.1f, 62.0f)
    lineTo(19.099998f, 62.0f)
    lineTo(19.099998f, 58.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(19.15f, 60.0f), end = Offset(60.15f, 60.0f), tileMode = TileMode.Clamp)
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
// _0_8
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

