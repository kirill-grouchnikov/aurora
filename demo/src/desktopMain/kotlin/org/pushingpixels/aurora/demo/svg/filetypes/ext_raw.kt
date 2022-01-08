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
class ext_raw : Painter() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var blendMode = DrawScope.DefaultBlendMode
    private var alphaStack = mutableListOf(1.0f)
    private var blendModeStack = mutableListOf(DrawScope.DefaultBlendMode)

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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.1f, 27.7f)
generalPath!!.lineTo(72.1f, 99.0f)
generalPath!!.lineTo(0.3f, 99.0f)
generalPath!!.lineTo(0.3f, 1.0f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.1f, 27.7f)
generalPath!!.lineTo(72.1f, 99.0f)
generalPath!!.lineTo(0.3f, 99.0f)
generalPath!!.lineTo(0.3f, 1.0f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.1f, 27.7f)
generalPath!!.lineTo(72.1f, 99.0f)
generalPath!!.lineTo(0.3f, 99.0f)
generalPath!!.lineTo(0.3f, 1.0f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(5.2f, 91.1f)
generalPath!!.lineTo(5.2f, 71.2f)
generalPath!!.lineTo(13.7f, 71.2f)
generalPath!!.cubicTo(15.799999f, 71.2f, 17.4f, 71.399994f, 18.4f, 71.7f)
generalPath!!.cubicTo(19.4f, 72.0f, 20.1f, 72.7f, 20.699999f, 73.6f)
generalPath!!.cubicTo(21.3f, 74.5f, 21.599998f, 75.6f, 21.599998f, 76.7f)
generalPath!!.cubicTo(21.599998f, 78.2f, 21.199999f, 79.399994f, 20.3f, 80.399994f)
generalPath!!.cubicTo(19.4f, 81.399994f, 18.099998f, 81.99999f, 16.3f, 82.2f)
generalPath!!.cubicTo(17.199999f, 82.7f, 17.9f, 83.299995f, 18.5f, 83.899994f)
generalPath!!.cubicTo(19.1f, 84.49999f, 19.8f, 85.59999f, 20.8f, 87.09999f)
generalPath!!.lineTo(23.199999f, 90.99999f)
generalPath!!.lineTo(18.399998f, 90.99999f)
generalPath!!.lineTo(15.499998f, 86.69999f)
generalPath!!.cubicTo(14.499998f, 85.19999f, 13.799998f, 84.19999f, 13.399998f, 83.79999f)
generalPath!!.cubicTo(12.999998f, 83.39999f, 12.5999975f, 83.09999f, 12.199998f, 82.999985f)
generalPath!!.cubicTo(11.799998f, 82.89999f, 11.0999975f, 82.79999f, 10.199998f, 82.79999f)
generalPath!!.lineTo(9.199998f, 82.79999f)
generalPath!!.lineTo(9.199998f, 91.09999f)
generalPath!!.lineTo(5.199998f, 91.09999f)
generalPath!!.close()
generalPath!!.moveTo(9.2f, 79.6f)
generalPath!!.lineTo(12.2f, 79.6f)
generalPath!!.cubicTo(14.099999f, 79.6f, 15.299999f, 79.5f, 15.799999f, 79.4f)
generalPath!!.cubicTo(16.3f, 79.200005f, 16.699999f, 79.0f, 16.9f, 78.6f)
generalPath!!.cubicTo(17.199999f, 78.2f, 17.3f, 77.7f, 17.3f, 77.1f)
generalPath!!.cubicTo(17.3f, 76.4f, 17.099998f, 75.9f, 16.8f, 75.5f)
generalPath!!.cubicTo(16.4f, 75.1f, 15.9f, 74.8f, 15.299999f, 74.7f)
generalPath!!.cubicTo(14.999999f, 74.7f, 13.999999f, 74.6f, 12.4f, 74.6f)
generalPath!!.lineTo(9.2f, 74.6f)
generalPath!!.lineTo(9.2f, 79.6f)
generalPath!!.close()
generalPath!!.moveTo(43.3f, 91.1f)
generalPath!!.lineTo(38.899998f, 91.1f)
generalPath!!.lineTo(37.199997f, 86.6f)
generalPath!!.lineTo(29.199997f, 86.6f)
generalPath!!.lineTo(27.599997f, 91.1f)
generalPath!!.lineTo(23.299995f, 91.1f)
generalPath!!.lineTo(31.099995f, 71.3f)
generalPath!!.lineTo(35.399994f, 71.3f)
generalPath!!.lineTo(43.299995f, 91.100006f)
generalPath!!.close()
generalPath!!.moveTo(35.899998f, 83.2f)
generalPath!!.lineTo(33.1f, 75.799995f)
generalPath!!.lineTo(30.399998f, 83.2f)
generalPath!!.lineTo(35.899998f, 83.2f)
generalPath!!.close()
generalPath!!.moveTo(46.799995f, 91.1f)
generalPath!!.lineTo(42.0f, 71.2f)
generalPath!!.lineTo(46.1f, 71.2f)
generalPath!!.lineTo(49.1f, 84.799995f)
generalPath!!.lineTo(52.8f, 71.2f)
generalPath!!.lineTo(57.6f, 71.2f)
generalPath!!.lineTo(61.1f, 85.1f)
generalPath!!.lineTo(64.2f, 71.2f)
generalPath!!.lineTo(68.299995f, 71.2f)
generalPath!!.lineTo(63.4f, 91.0f)
generalPath!!.lineTo(59.100002f, 91.0f)
generalPath!!.lineTo(55.100002f, 76.2f)
generalPath!!.lineTo(51.100002f, 91.0f)
generalPath!!.lineTo(46.800003f, 91.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.1f, 27.7f)
generalPath!!.lineTo(45.2f, 27.7f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.1f, 27.7f)
generalPath!!.lineTo(45.2f, 27.7f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.1f, 27.7f)
generalPath!!.lineTo(45.2f, 27.7f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(62.7f, 56.8f)
generalPath!!.cubicTo(61.100002f, 56.0f, 58.100002f, 50.2f, 53.5f, 49.8f)
generalPath!!.cubicTo(49.5f, 49.5f, 44.4f, 48.0f, 41.6f, 47.8f)
generalPath!!.cubicTo(38.1f, 42.0f, 32.1f, 32.8f, 27.099998f, 27.9f)
generalPath!!.lineTo(40.899998f, 28.6f)
generalPath!!.cubicTo(37.2f, 19.8f, 27.7f, 23.0f, 27.7f, 23.0f)
generalPath!!.lineTo(34.100002f, 17.7f)
generalPath!!.cubicTo(25.900002f, 14.400001f, 22.500002f, 22.400002f, 22.500002f, 22.400002f)
generalPath!!.cubicTo(14.000002f, 17.7f, 9.600002f, 25.7f, 9.600002f, 25.7f)
generalPath!!.lineTo(18.400002f, 26.300001f)
generalPath!!.cubicTo(8.4f, 29.1f, 11.2f, 39.0f, 11.2f, 39.0f)
generalPath!!.lineTo(20.099998f, 31.0f)
generalPath!!.cubicTo(18.199999f, 35.4f, 22.399998f, 38.5f, 22.399998f, 38.5f)
generalPath!!.lineTo(25.0f, 27.7f)
generalPath!!.cubicTo(25.0f, 27.7f, 34.3f, 38.300003f, 37.2f, 49.1f)
generalPath!!.cubicTo(33.5f, 51.0f, 27.7f, 54.1f, 23.2f, 54.699997f)
generalPath!!.cubicTo(17.0f, 55.499996f, 9.700001f, 59.699997f, 9.700001f, 59.699997f)
generalPath!!.lineTo(9.700001f, 64.6f)
generalPath!!.lineTo(62.8f, 64.6f)
generalPath!!.lineTo(62.7f, 56.8f)
generalPath!!.close()
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
