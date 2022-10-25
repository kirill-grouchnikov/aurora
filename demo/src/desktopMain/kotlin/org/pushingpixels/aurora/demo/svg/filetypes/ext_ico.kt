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
class ext_ico : Painter() {
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
alpha *= 0.99f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.1f, 27.7f)
    lineTo(72.1f, 99.0f)
    lineTo(0.3f, 99.0f)
    lineTo(0.3f, 1.0f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 107, 105, 255), 0.124f to Color(0, 128, 127, 255), 0.262f to Color(0, 147, 147, 255), 0.41f to Color(0, 163, 163, 255), 0.571f to Color(0, 176, 175, 255), 0.752f to Color(8, 184, 183, 255), 1.0f to Color(20, 187, 187, 255), start = Offset(36.2f, 98.995f), end = Offset(36.2f, 1.0f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.1f, 27.7f)
    lineTo(72.1f, 99.0f)
    lineTo(0.3f, 99.0f)
    lineTo(0.3f, 1.0f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 110, 108, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.1f, 27.7f)
    lineTo(72.1f, 99.0f)
    lineTo(0.3f, 99.0f)
    lineTo(0.3f, 1.0f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
    moveTo(13.3f, 91.1f)
    lineTo(13.3f, 71.2f)
    lineTo(17.3f, 71.2f)
    lineTo(17.3f, 91.0f)
    lineTo(13.299999f, 91.0f)
    close()
    moveTo(34.0f, 83.8f)
    lineTo(37.9f, 85.0f)
    cubicTo(37.300003f, 87.2f, 36.300003f, 88.8f, 34.9f, 89.8f)
    cubicTo(33.5f, 90.9f, 31.7f, 91.4f, 29.600002f, 91.4f)
    cubicTo(26.900002f, 91.4f, 24.800003f, 90.5f, 23.100002f, 88.700005f)
    cubicTo(21.400002f, 86.90001f, 20.500002f, 84.4f, 20.500002f, 81.3f)
    cubicTo(20.500002f, 78.0f, 21.400002f, 75.4f, 23.100002f, 73.600006f)
    cubicTo(24.800003f, 71.80001f, 27.100002f, 70.90001f, 29.900002f, 70.90001f)
    cubicTo(32.300003f, 70.90001f, 34.300003f, 71.600006f, 35.9f, 73.100006f)
    cubicTo(36.800003f, 73.90001f, 37.5f, 75.200005f, 37.9f, 76.8f)
    lineTo(33.9f, 77.700005f)
    cubicTo(33.7f, 76.700005f, 33.2f, 75.9f, 32.4f, 75.3f)
    cubicTo(31.7f, 74.700005f, 30.7f, 74.4f, 29.7f, 74.4f)
    cubicTo(28.2f, 74.4f, 27.0f, 74.9f, 26.1f, 76.0f)
    cubicTo(25.2f, 77.1f, 24.7f, 78.8f, 24.7f, 81.1f)
    cubicTo(24.7f, 83.6f, 25.2f, 85.4f, 26.1f, 86.5f)
    cubicTo(27.0f, 87.6f, 28.2f, 88.1f, 29.6f, 88.1f)
    cubicTo(30.7f, 88.1f, 31.6f, 87.799995f, 32.4f, 87.1f)
    cubicTo(33.200005f, 86.4f, 33.600002f, 85.2f, 34.0f, 83.799995f)
    close()
    moveTo(40.5f, 81.3f)
    cubicTo(40.5f, 79.3f, 40.8f, 77.600006f, 41.4f, 76.200005f)
    cubicTo(41.9f, 75.200005f, 42.5f, 74.3f, 43.300003f, 73.50001f)
    cubicTo(44.100002f, 72.700005f, 44.9f, 72.100006f, 45.9f, 71.700005f)
    cubicTo(47.100002f, 71.200005f, 48.600002f, 70.9f, 50.2f, 70.9f)
    cubicTo(53.100002f, 70.9f, 55.5f, 71.8f, 57.3f, 73.6f)
    cubicTo(59.1f, 75.4f, 59.899998f, 77.9f, 59.899998f, 81.2f)
    cubicTo(59.899998f, 84.399994f, 58.999996f, 86.899994f, 57.3f, 88.7f)
    cubicTo(55.5f, 90.5f, 53.2f, 91.399994f, 50.3f, 91.399994f)
    cubicTo(47.3f, 91.399994f, 45.0f, 90.49999f, 43.2f, 88.7f)
    cubicTo(41.4f, 86.9f, 40.5f, 84.399994f, 40.5f, 81.299995f)
    close()
    moveTo(44.7f, 81.100006f)
    cubicTo(44.7f, 83.3f, 45.2f, 85.100006f, 46.3f, 86.200005f)
    cubicTo(47.3f, 87.4f, 48.7f, 87.9f, 50.3f, 87.9f)
    cubicTo(51.899998f, 87.9f, 53.2f, 87.3f, 54.3f, 86.200005f)
    cubicTo(55.3f, 85.00001f, 55.8f, 83.3f, 55.8f, 81.00001f)
    cubicTo(55.8f, 78.700005f, 55.3f, 77.00001f, 54.3f, 75.90001f)
    cubicTo(53.3f, 74.80001f, 52.0f, 74.20001f, 50.3f, 74.20001f)
    cubicTo(48.6f, 74.20001f, 47.3f, 74.80001f, 46.3f, 75.90001f)
    cubicTo(45.2f, 77.100006f, 44.7f, 78.90001f, 44.7f, 81.100006f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.99f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.1f, 27.7f)
    lineTo(45.2f, 27.7f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(214, 237, 232, 255), 0.297f to Color(211, 235, 230, 255), 0.44f to Color(199, 227, 223, 255), 0.551f to Color(183, 216, 213, 255), 0.645f to Color(160, 203, 201, 255), 0.729f to Color(132, 186, 185, 255), 0.804f to Color(98, 167, 167, 255), 0.874f to Color(52, 147, 148, 255), 0.938f to Color(0, 127, 127, 255), 0.998f to Color(0, 107, 106, 255), 1.0f to Color(0, 107, 105, 255), start = Offset(45.214f, 27.771004f), end = Offset(58.667f, 14.318001f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.1f, 27.7f)
    lineTo(45.2f, 27.7f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 110, 108, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.1f, 27.7f)
    lineTo(45.2f, 27.7f)
    lineTo(45.2f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
    moveTo(35.8f, 16.2f)
    cubicTo(36.0f, 15.700001f, 36.2f, 15.700001f, 36.399998f, 16.2f)
    lineTo(41.8f, 32.9f)
    cubicTo(42.0f, 33.4f, 42.399998f, 33.800003f, 43.0f, 33.800003f)
    lineTo(60.6f, 33.800003f)
    cubicTo(61.1f, 33.800003f, 61.199997f, 34.000004f, 60.8f, 34.300003f)
    lineTo(46.5f, 44.6f)
    cubicTo(46.1f, 44.899998f, 45.9f, 45.5f, 46.1f, 46.0f)
    lineTo(51.5f, 62.7f)
    cubicTo(51.7f, 63.2f, 51.4f, 63.3f, 51.1f, 63.0f)
    lineTo(36.8f, 52.7f)
    cubicTo(36.399998f, 52.4f, 35.8f, 52.4f, 35.3f, 52.7f)
    lineTo(21.2f, 63.0f)
    cubicTo(20.800001f, 63.3f, 20.6f, 63.1f, 20.800001f, 62.7f)
    lineTo(26.2f, 46.0f)
    cubicTo(26.300001f, 45.5f, 26.2f, 44.9f, 25.800001f, 44.6f)
    lineTo(11.5f, 34.3f)
    cubicTo(11.1f, 34.0f, 11.2f, 33.8f, 11.7f, 33.8f)
    lineTo(29.3f, 33.8f)
    cubicTo(29.8f, 33.8f, 30.3f, 33.399998f, 30.5f, 32.899998f)
    lineTo(35.8f, 16.199997f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(0, 130, 129, 255), 0.343f to Color(0, 106, 105, 255), 1.0f to Color(0, 56, 54, 255), start = Offset(36.118f, 63.158f), end = Offset(36.118f, 15.82f), tileMode = TileMode.Clamp)
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
            return 0.13300000131130219
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
            return 0.7379999160766602
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

