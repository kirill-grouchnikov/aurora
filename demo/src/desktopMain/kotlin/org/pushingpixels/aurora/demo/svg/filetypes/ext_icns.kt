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
class ext_icns : Painter() {
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
    moveTo(9.0f, 91.1f)
    lineTo(9.0f, 73.9f)
    lineTo(12.5f, 73.9f)
    lineTo(12.5f, 91.100006f)
    lineTo(9.0f, 91.100006f)
    close()
    moveTo(26.9f, 84.799995f)
    lineTo(30.3f, 85.899994f)
    cubicTo(29.8f, 87.799995f, 28.9f, 89.2f, 27.699999f, 90.09999f)
    cubicTo(26.499998f, 90.99999f, 24.999998f, 91.49999f, 23.099998f, 91.49999f)
    cubicTo(20.8f, 91.49999f, 18.899998f, 90.69999f, 17.399998f, 89.19999f)
    cubicTo(15.899998f, 87.69999f, 15.199998f, 85.49999f, 15.199998f, 82.79999f)
    cubicTo(15.199998f, 79.89999f, 15.899998f, 77.69999f, 17.399998f, 76.09999f)
    cubicTo(18.899998f, 74.49999f, 20.799997f, 73.69999f, 23.299997f, 73.69999f)
    cubicTo(25.399998f, 73.69999f, 27.099997f, 74.29999f, 28.499996f, 75.59999f)
    cubicTo(29.299995f, 76.29999f, 29.899996f, 77.399994f, 30.299995f, 78.79999f)
    lineTo(26.799995f, 79.59999f)
    cubicTo(26.599995f, 78.69999f, 26.199995f, 77.99999f, 25.499996f, 77.49999f)
    cubicTo(24.799997f, 76.99999f, 24.099997f, 76.69999f, 23.099997f, 76.69999f)
    cubicTo(21.799997f, 76.69999f, 20.799997f, 77.19999f, 19.999996f, 78.09999f)
    cubicTo(19.199997f, 78.99999f, 18.799995f, 80.49999f, 18.799995f, 82.49999f)
    cubicTo(18.799995f, 84.69999f, 19.199995f, 86.19999f, 19.999996f, 87.09999f)
    cubicTo(20.799997f, 87.99999f, 21.799995f, 88.49999f, 23.099997f, 88.49999f)
    cubicTo(23.999996f, 88.49999f, 24.799997f, 88.19999f, 25.499996f, 87.59999f)
    cubicTo(26.199995f, 86.99999f, 26.599997f, 85.99999f, 26.899996f, 84.79999f)
    close()
    moveTo(33.3f, 91.1f)
    lineTo(33.3f, 73.9f)
    lineTo(36.7f, 73.9f)
    lineTo(43.8f, 85.4f)
    lineTo(43.8f, 73.9f)
    lineTo(47.0f, 73.9f)
    lineTo(47.0f, 91.100006f)
    lineTo(43.5f, 91.100006f)
    lineTo(36.5f, 79.90001f)
    lineTo(36.5f, 91.100006f)
    lineTo(33.3f, 91.100006f)
    close()
    moveTo(49.8f, 85.5f)
    lineTo(53.2f, 85.2f)
    cubicTo(53.4f, 86.299995f, 53.8f, 87.2f, 54.4f, 87.7f)
    cubicTo(55.000004f, 88.2f, 55.9f, 88.5f, 56.9f, 88.5f)
    cubicTo(58.0f, 88.5f, 58.9f, 88.3f, 59.4f, 87.8f)
    cubicTo(59.9f, 87.3f, 60.300003f, 86.8f, 60.300003f, 86.100006f)
    cubicTo(60.300003f, 85.700005f, 60.200005f, 85.3f, 59.9f, 85.100006f)
    cubicTo(59.6f, 84.90001f, 59.2f, 84.600006f, 58.600002f, 84.40001f)
    cubicTo(58.2f, 84.30001f, 57.300003f, 84.00001f, 55.800003f, 83.600006f)
    cubicTo(53.9f, 83.100006f, 52.600002f, 82.600006f, 51.9f, 81.90001f)
    cubicTo(50.800003f, 81.00001f, 50.300003f, 79.80001f, 50.300003f, 78.40001f)
    cubicTo(50.300003f, 77.50001f, 50.500004f, 76.70001f, 51.100002f, 75.90001f)
    cubicTo(51.600002f, 75.100006f, 52.300003f, 74.600006f, 53.300003f, 74.20001f)
    cubicTo(54.300003f, 73.80002f, 55.4f, 73.60001f, 56.700005f, 73.60001f)
    cubicTo(58.900005f, 73.60001f, 60.500004f, 74.10001f, 61.600006f, 75.000015f)
    cubicTo(62.700005f, 75.90002f, 63.300007f, 77.20001f, 63.300007f, 78.80002f)
    lineTo(59.800007f, 79.000015f)
    cubicTo(59.70001f, 78.10001f, 59.300007f, 77.500015f, 58.800007f, 77.10001f)
    cubicTo(58.300007f, 76.70001f, 57.600006f, 76.500015f, 56.600006f, 76.500015f)
    cubicTo(55.600006f, 76.500015f, 54.800007f, 76.70001f, 54.200005f, 77.10001f)
    cubicTo(53.800003f, 77.40002f, 53.600006f, 77.70001f, 53.600006f, 78.20001f)
    cubicTo(53.600006f, 78.60001f, 53.800007f, 79.000015f, 54.100006f, 79.20001f)
    cubicTo(54.500008f, 79.60001f, 55.600006f, 80.000015f, 57.300007f, 80.30001f)
    cubicTo(59.000008f, 80.70001f, 60.300007f, 81.10001f, 61.100006f, 81.50001f)
    cubicTo(61.900005f, 81.90001f, 62.600006f, 82.50001f, 63.000008f, 83.200005f)
    cubicTo(63.40001f, 83.9f, 63.70001f, 84.9f, 63.70001f, 85.9f)
    cubicTo(63.70001f, 86.9f, 63.40001f, 87.8f, 62.90001f, 88.700005f)
    cubicTo(62.40001f, 89.600006f, 61.60001f, 90.200005f, 60.60001f, 90.600006f)
    cubicTo(59.60001f, 91.00001f, 58.30001f, 91.200005f, 56.80001f, 91.200005f)
    cubicTo(54.60001f, 91.200005f, 52.90001f, 90.700005f, 51.80001f, 89.700005f)
    cubicTo(50.700012f, 88.700005f, 50.00001f, 87.4f, 49.80001f, 85.50001f)
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

