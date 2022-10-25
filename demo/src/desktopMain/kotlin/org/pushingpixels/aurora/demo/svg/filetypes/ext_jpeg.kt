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
class ext_jpeg : Painter() {
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
    moveTo(14.0f, 75.3f)
    lineTo(17.2f, 75.3f)
    lineTo(17.2f, 85.3f)
    cubicTo(17.2f, 86.600006f, 17.1f, 87.600006f, 16.900002f, 88.3f)
    cubicTo(16.600002f, 89.200005f, 16.000002f, 90.0f, 15.200002f, 90.5f)
    cubicTo(14.400002f, 91.1f, 13.300002f, 91.3f, 12.000002f, 91.3f)
    cubicTo(10.400002f, 91.3f, 9.200002f, 90.9f, 8.400002f, 90.0f)
    cubicTo(7.5000014f, 89.2f, 7.0000014f, 87.9f, 7.0000014f, 86.2f)
    lineTo(10.000002f, 85.899994f)
    cubicTo(10.000002f, 86.799995f, 10.200002f, 87.399994f, 10.400002f, 87.799995f)
    cubicTo(10.700002f, 88.399994f, 11.300001f, 88.6f, 12.000002f, 88.6f)
    cubicTo(12.700002f, 88.6f, 13.200002f, 88.4f, 13.500002f, 88.0f)
    cubicTo(13.800002f, 87.6f, 13.900002f, 86.8f, 13.900002f, 85.5f)
    lineTo(13.900002f, 75.3f)
    close()
    moveTo(20.6f, 91.100006f)
    lineTo(20.6f, 75.3f)
    lineTo(25.7f, 75.3f)
    cubicTo(27.6f, 75.3f, 28.900002f, 75.4f, 29.5f, 75.5f)
    cubicTo(30.4f, 75.7f, 31.2f, 76.3f, 31.8f, 77.0f)
    cubicTo(32.399998f, 77.8f, 32.7f, 78.8f, 32.7f, 80.1f)
    cubicTo(32.7f, 81.1f, 32.5f, 81.9f, 32.2f, 82.5f)
    cubicTo(31.800001f, 83.2f, 31.400002f, 83.7f, 30.900002f, 84.1f)
    cubicTo(30.400002f, 84.5f, 29.800001f, 84.7f, 29.2f, 84.799995f)
    cubicTo(28.400002f, 84.99999f, 27.300001f, 84.99999f, 25.900002f, 84.99999f)
    lineTo(23.800001f, 84.99999f)
    lineTo(23.800001f, 90.899994f)
    lineTo(20.6f, 90.899994f)
    close()
    moveTo(23.8f, 78.0f)
    lineTo(23.8f, 82.5f)
    lineTo(25.599998f, 82.5f)
    cubicTo(26.899998f, 82.5f, 27.699999f, 82.4f, 28.099998f, 82.3f)
    cubicTo(28.499998f, 82.100006f, 28.899998f, 81.9f, 29.099998f, 81.5f)
    cubicTo(29.3f, 81.1f, 29.499998f, 80.7f, 29.499998f, 80.3f)
    cubicTo(29.499998f, 79.700005f, 29.299997f, 79.3f, 28.999998f, 78.9f)
    cubicTo(28.699999f, 78.5f, 28.199999f, 78.3f, 27.699999f, 78.200005f)
    cubicTo(27.3f, 78.00001f, 26.499998f, 78.00001f, 25.3f, 78.00001f)
    lineTo(23.8f, 78.00001f)
    close()
    moveTo(35.4f, 91.1f)
    lineTo(35.4f, 75.3f)
    lineTo(47.2f, 75.3f)
    lineTo(47.2f, 78.0f)
    lineTo(38.6f, 78.0f)
    lineTo(38.6f, 81.5f)
    lineTo(46.6f, 81.5f)
    lineTo(46.6f, 84.2f)
    lineTo(38.6f, 84.2f)
    lineTo(38.6f, 88.5f)
    lineTo(47.5f, 88.5f)
    lineTo(47.5f, 91.2f)
    lineTo(35.4f, 91.2f)
    close()
    moveTo(57.5f, 85.299995f)
    lineTo(57.5f, 82.6f)
    lineTo(64.4f, 82.6f)
    lineTo(64.4f, 88.9f)
    cubicTo(63.7f, 89.5f, 62.800003f, 90.1f, 61.5f, 90.6f)
    cubicTo(60.2f, 91.1f, 58.9f, 91.299995f, 57.6f, 91.299995f)
    cubicTo(55.899998f, 91.299995f, 54.5f, 90.99999f, 53.3f, 90.299995f)
    cubicTo(52.1f, 89.6f, 51.1f, 88.6f, 50.5f, 87.299995f)
    cubicTo(49.9f, 85.99999f, 49.6f, 84.6f, 49.6f, 83.1f)
    cubicTo(49.6f, 81.5f, 49.899998f, 80.0f, 50.6f, 78.7f)
    cubicTo(51.3f, 77.399994f, 52.3f, 76.399994f, 53.6f, 75.799995f)
    cubicTo(54.6f, 75.299995f, 55.899998f, 74.99999f, 57.399998f, 74.99999f)
    cubicTo(59.399998f, 74.99999f, 60.899998f, 75.399994f, 61.999996f, 76.19999f)
    cubicTo(63.099995f, 76.99999f, 63.799995f, 78.19999f, 64.1f, 79.59999f)
    lineTo(61.0f, 80.29999f)
    cubicTo(60.8f, 79.499985f, 60.4f, 78.89999f, 59.7f, 78.499985f)
    cubicTo(59.100002f, 78.09998f, 58.3f, 77.79999f, 57.4f, 77.79999f)
    cubicTo(56.0f, 77.79999f, 54.9f, 78.19999f, 54.100002f, 79.09999f)
    cubicTo(53.300003f, 79.99999f, 52.9f, 81.29999f, 52.9f, 82.99999f)
    cubicTo(52.9f, 84.899994f, 53.300003f, 86.299995f, 54.2f, 87.19999f)
    cubicTo(55.0f, 88.09999f, 56.100002f, 88.59999f, 57.5f, 88.59999f)
    cubicTo(58.2f, 88.59999f, 58.8f, 88.49999f, 59.5f, 88.19999f)
    cubicTo(60.2f, 87.89999f, 60.8f, 87.59999f, 61.2f, 87.19999f)
    lineTo(61.2f, 85.19999f)
    lineTo(57.5f, 85.19999f)
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
alpha *= 0.99f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(62.7f, 56.8f)
    cubicTo(61.100002f, 56.0f, 58.100002f, 50.2f, 53.5f, 49.8f)
    cubicTo(49.5f, 49.5f, 44.4f, 48.0f, 41.6f, 47.8f)
    cubicTo(38.1f, 42.0f, 32.1f, 32.8f, 27.099998f, 27.9f)
    lineTo(40.899998f, 28.6f)
    cubicTo(37.2f, 19.8f, 27.7f, 23.0f, 27.7f, 23.0f)
    lineTo(34.100002f, 17.7f)
    cubicTo(25.900002f, 14.400001f, 22.500002f, 22.400002f, 22.500002f, 22.400002f)
    cubicTo(14.000002f, 17.7f, 9.600002f, 25.7f, 9.600002f, 25.7f)
    lineTo(18.400002f, 26.300001f)
    cubicTo(8.4f, 29.1f, 11.2f, 39.0f, 11.2f, 39.0f)
    lineTo(20.099998f, 31.0f)
    cubicTo(18.199999f, 35.4f, 22.399998f, 38.5f, 22.399998f, 38.5f)
    lineTo(25.0f, 27.7f)
    cubicTo(25.0f, 27.7f, 34.3f, 38.300003f, 37.2f, 49.1f)
    cubicTo(33.5f, 51.0f, 27.7f, 54.1f, 23.2f, 54.699997f)
    cubicTo(17.0f, 55.499996f, 9.700001f, 59.699997f, 9.700001f, 59.699997f)
    lineTo(9.700001f, 64.6f)
    lineTo(62.8f, 64.6f)
    lineTo(62.7f, 56.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(0, 130, 129, 255), 0.343f to Color(0, 106, 105, 255), 1.0f to Color(0, 56, 54, 255), start = Offset(36.25f, 64.647f), end = Offset(36.25f, 16.838997f), tileMode = TileMode.Clamp)
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

