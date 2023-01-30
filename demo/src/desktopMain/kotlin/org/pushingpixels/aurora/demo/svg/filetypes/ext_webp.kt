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
class ext_webp : Painter() {
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
    moveTo(7.0f, 91.1f)
    lineTo(3.3f, 75.3f)
    lineTo(6.6f, 75.3f)
    lineTo(9.0f, 86.1f)
    lineTo(11.9f, 75.299995f)
    lineTo(15.7f, 75.299995f)
    lineTo(18.5f, 86.299995f)
    lineTo(20.9f, 75.299995f)
    lineTo(24.1f, 75.299995f)
    lineTo(20.2f, 91.0f)
    lineTo(16.800001f, 91.0f)
    lineTo(13.600001f, 79.2f)
    lineTo(10.5f, 91.0f)
    lineTo(7.0f, 91.0f)
    close()
    moveTo(25.7f, 91.1f)
    lineTo(25.7f, 75.3f)
    lineTo(37.5f, 75.3f)
    lineTo(37.5f, 78.0f)
    lineTo(28.9f, 78.0f)
    lineTo(28.9f, 81.5f)
    lineTo(36.9f, 81.5f)
    lineTo(36.9f, 84.2f)
    lineTo(28.900002f, 84.2f)
    lineTo(28.900002f, 88.5f)
    lineTo(37.800003f, 88.5f)
    lineTo(37.800003f, 91.2f)
    lineTo(25.7f, 91.2f)
    close()
    moveTo(40.5f, 75.299995f)
    lineTo(46.8f, 75.299995f)
    cubicTo(48.1f, 75.299995f, 49.0f, 75.399994f, 49.6f, 75.49999f)
    cubicTo(50.199997f, 75.59999f, 50.8f, 75.799995f, 51.3f, 76.09999f)
    cubicTo(51.8f, 76.39999f, 52.2f, 76.899994f, 52.5f, 77.399994f)
    cubicTo(52.8f, 77.899994f, 53.0f, 78.59999f, 53.0f, 79.2f)
    cubicTo(53.0f, 79.899994f, 52.8f, 80.6f, 52.4f, 81.2f)
    cubicTo(52.000004f, 81.799995f, 51.5f, 82.299995f, 50.800003f, 82.6f)
    cubicTo(51.800003f, 82.9f, 52.500004f, 83.4f, 53.000004f, 84.0f)
    cubicTo(53.500004f, 84.7f, 53.800003f, 85.5f, 53.800003f, 86.4f)
    cubicTo(53.800003f, 87.1f, 53.600002f, 87.8f, 53.300003f, 88.5f)
    cubicTo(53.000004f, 89.2f, 52.500004f, 89.7f, 51.9f, 90.1f)
    cubicTo(51.300003f, 90.5f, 50.600002f, 90.799995f, 49.800003f, 90.799995f)
    cubicTo(49.300003f, 90.899994f, 48.000004f, 90.899994f, 45.9f, 90.899994f)
    lineTo(40.5f, 90.899994f)
    lineTo(40.5f, 75.3f)
    close()
    moveTo(43.7f, 77.899994f)
    lineTo(43.7f, 81.49999f)
    lineTo(45.8f, 81.49999f)
    cubicTo(47.0f, 81.49999f, 47.8f, 81.49999f, 48.1f, 81.399994f)
    cubicTo(48.6f, 81.299995f, 49.1f, 81.09999f, 49.399998f, 80.799995f)
    cubicTo(49.699997f, 80.49999f, 49.899998f, 80.1f, 49.899998f, 79.6f)
    cubicTo(49.899998f, 79.1f, 49.8f, 78.7f, 49.499996f, 78.4f)
    cubicTo(49.199997f, 78.1f, 48.799995f, 77.9f, 48.299995f, 77.8f)
    cubicTo(47.999996f, 77.8f, 47.099995f, 77.700005f, 45.599995f, 77.700005f)
    lineTo(43.699993f, 77.700005f)
    close()
    moveTo(43.7f, 84.2f)
    lineTo(43.7f, 88.399994f)
    lineTo(46.7f, 88.399994f)
    cubicTo(47.9f, 88.399994f, 48.600002f, 88.399994f, 48.9f, 88.299995f)
    cubicTo(49.4f, 88.2f, 49.800003f, 87.99999f, 50.100002f, 87.7f)
    cubicTo(50.4f, 87.399994f, 50.500004f, 86.899994f, 50.500004f, 86.399994f)
    cubicTo(50.500004f, 85.899994f, 50.400005f, 85.49999f, 50.200005f, 85.2f)
    cubicTo(50.000004f, 84.899994f, 49.600006f, 84.6f, 49.200005f, 84.5f)
    cubicTo(48.800003f, 84.3f, 47.800003f, 84.3f, 46.400005f, 84.3f)
    lineTo(43.700005f, 84.3f)
    close()
    moveTo(56.5f, 91.1f)
    lineTo(56.5f, 75.3f)
    lineTo(61.6f, 75.3f)
    cubicTo(63.5f, 75.3f, 64.799995f, 75.4f, 65.4f, 75.5f)
    cubicTo(66.3f, 75.7f, 67.1f, 76.3f, 67.700005f, 77.0f)
    cubicTo(68.3f, 77.8f, 68.600006f, 78.8f, 68.600006f, 80.1f)
    cubicTo(68.600006f, 81.1f, 68.40001f, 81.9f, 68.100006f, 82.5f)
    cubicTo(67.8f, 83.1f, 67.3f, 83.7f, 66.8f, 84.1f)
    cubicTo(66.3f, 84.5f, 65.700005f, 84.7f, 65.100006f, 84.799995f)
    cubicTo(64.3f, 84.99999f, 63.200005f, 84.99999f, 61.800007f, 84.99999f)
    lineTo(59.70001f, 84.99999f)
    lineTo(59.70001f, 90.899994f)
    lineTo(56.500008f, 90.899994f)
    close()
    moveTo(59.7f, 78.0f)
    lineTo(59.7f, 82.5f)
    lineTo(61.5f, 82.5f)
    cubicTo(62.8f, 82.5f, 63.6f, 82.4f, 64.0f, 82.3f)
    cubicTo(64.4f, 82.200005f, 64.8f, 81.9f, 65.0f, 81.5f)
    cubicTo(65.2f, 81.1f, 65.4f, 80.7f, 65.4f, 80.3f)
    cubicTo(65.4f, 79.700005f, 65.200005f, 79.3f, 64.9f, 78.9f)
    cubicTo(64.6f, 78.5f, 64.1f, 78.3f, 63.600002f, 78.200005f)
    cubicTo(63.100002f, 78.00001f, 62.4f, 78.00001f, 61.2f, 78.00001f)
    lineTo(59.7f, 78.00001f)
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

