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
class ext_cmd : Painter() {
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
    moveTo(18.8f, 83.8f)
    lineTo(22.699999f, 85.0f)
    cubicTo(22.099998f, 87.2f, 21.099998f, 88.8f, 19.699999f, 89.8f)
    cubicTo(18.3f, 90.9f, 16.499998f, 91.4f, 14.399999f, 91.4f)
    cubicTo(11.699999f, 91.4f, 9.599998f, 90.5f, 7.8999987f, 88.700005f)
    cubicTo(6.199999f, 86.90001f, 5.2999988f, 84.4f, 5.2999988f, 81.3f)
    cubicTo(5.2999988f, 78.0f, 6.199999f, 75.4f, 7.8999987f, 73.600006f)
    cubicTo(9.599998f, 71.80001f, 11.899999f, 70.90001f, 14.699999f, 70.90001f)
    cubicTo(17.099998f, 70.90001f, 19.099998f, 71.600006f, 20.699999f, 73.100006f)
    cubicTo(21.599998f, 73.90001f, 22.3f, 75.200005f, 22.699999f, 76.8f)
    lineTo(18.699999f, 77.700005f)
    cubicTo(18.499998f, 76.700005f, 17.999998f, 75.9f, 17.199999f, 75.3f)
    cubicTo(16.499998f, 74.700005f, 15.499999f, 74.4f, 14.499999f, 74.4f)
    cubicTo(12.999999f, 74.4f, 11.799999f, 74.9f, 10.9f, 76.0f)
    cubicTo(10.0f, 77.1f, 9.4f, 78.7f, 9.4f, 81.0f)
    cubicTo(9.4f, 83.5f, 9.9f, 85.3f, 10.799999f, 86.4f)
    cubicTo(11.699999f, 87.5f, 12.9f, 88.0f, 14.299999f, 88.0f)
    cubicTo(15.4f, 88.0f, 16.3f, 87.7f, 17.099998f, 87.0f)
    cubicTo(17.899998f, 86.3f, 18.399998f, 85.2f, 18.8f, 83.8f)
    close()
    moveTo(26.099998f, 91.100006f)
    lineTo(26.099998f, 71.2f)
    lineTo(32.1f, 71.2f)
    lineTo(35.699997f, 84.7f)
    lineTo(39.299995f, 71.2f)
    lineTo(45.299995f, 71.2f)
    lineTo(45.299995f, 91.0f)
    lineTo(41.599995f, 91.0f)
    lineTo(41.599995f, 75.4f)
    lineTo(37.599995f, 91.0f)
    lineTo(33.699993f, 91.0f)
    lineTo(29.699993f, 75.4f)
    lineTo(29.699993f, 91.0f)
    lineTo(26.099993f, 91.0f)
    close()
    moveTo(49.399998f, 71.200005f)
    lineTo(56.8f, 71.200005f)
    cubicTo(58.5f, 71.200005f, 59.7f, 71.3f, 60.6f, 71.600006f)
    cubicTo(61.8f, 71.90001f, 62.8f, 72.600006f, 63.6f, 73.40001f)
    cubicTo(64.399994f, 74.20001f, 65.1f, 75.30001f, 65.5f, 76.600006f)
    cubicTo(65.9f, 77.90001f, 66.2f, 79.40001f, 66.2f, 81.3f)
    cubicTo(66.2f, 82.9f, 66.0f, 84.3f, 65.6f, 85.5f)
    cubicTo(65.1f, 86.9f, 64.4f, 88.1f, 63.5f, 89.0f)
    cubicTo(62.8f, 89.7f, 61.9f, 90.2f, 60.7f, 90.6f)
    cubicTo(59.8f, 90.9f, 58.600002f, 91.0f, 57.100002f, 91.0f)
    lineTo(49.500004f, 91.0f)
    lineTo(49.500004f, 71.2f)
    close()
    moveTo(53.399998f, 74.600006f)
    lineTo(53.399998f, 87.700005f)
    lineTo(56.399998f, 87.700005f)
    cubicTo(57.499996f, 87.700005f, 58.3f, 87.600006f, 58.8f, 87.50001f)
    cubicTo(59.5f, 87.30001f, 60.0f, 87.100006f, 60.399998f, 86.700005f)
    cubicTo(60.799995f, 86.3f, 61.199997f, 85.700005f, 61.499996f, 84.8f)
    cubicTo(61.799995f, 83.9f, 61.899998f, 82.700005f, 61.899998f, 81.200005f)
    cubicTo(61.899998f, 79.700005f, 61.8f, 78.50001f, 61.499996f, 77.700005f)
    cubicTo(61.199993f, 76.9f, 60.799995f, 76.200005f, 60.399998f, 75.8f)
    cubicTo(59.899998f, 75.3f, 59.3f, 75.0f, 58.499996f, 74.9f)
    cubicTo(57.899998f, 74.8f, 56.799995f, 74.700005f, 55.199997f, 74.700005f)
    lineTo(53.399998f, 74.700005f)
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
    moveTo(60.0f, 17.5f)
    lineTo(13.5f, 17.5f)
    lineTo(13.5f, 64.0f)
    lineTo(60.0f, 64.0f)
    lineTo(60.0f, 17.5f)
    close()
    moveTo(22.8f, 26.8f)
    lineTo(18.199999f, 26.8f)
    lineTo(18.199999f, 22.199999f)
    lineTo(22.8f, 22.199999f)
    lineTo(22.8f, 26.8f)
    close()
    moveTo(55.3f, 26.8f)
    lineTo(27.5f, 26.8f)
    lineTo(27.5f, 22.199999f)
    lineTo(55.4f, 22.199999f)
    lineTo(55.4f, 26.8f)
    close()
    moveTo(55.3f, 59.3f)
    lineTo(18.1f, 59.3f)
    lineTo(18.1f, 31.5f)
    lineTo(55.300003f, 31.5f)
    lineTo(55.300003f, 59.3f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.75f, 64.0f), end = Offset(36.75f, 17.5f), tileMode = TileMode.Clamp)
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
    moveTo(25.4f, 35.5f)
    lineTo(22.199999f, 38.7f)
    lineTo(27.5f, 44.100002f)
    lineTo(22.2f, 49.4f)
    lineTo(25.400002f, 52.7f)
    lineTo(34.100002f, 44.1f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(28.109f, 52.694f), end = Offset(28.109f, 35.456f), tileMode = TileMode.Clamp)
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
    moveTo(33.1f, 51.0f)
    lineTo(42.399998f, 51.0f)
    lineTo(42.399998f, 55.7f)
    lineTo(33.1f, 55.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(37.75f, 55.7f), end = Offset(37.75f, 51.05f), tileMode = TileMode.Clamp)
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.122f, 27.771004f), end = Offset(58.575f, 14.317001f), tileMode = TileMode.Clamp)
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

