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
class ext_rar : Painter() {
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
alpha *= 0.98f
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
generalPath!!.moveTo(44.9f, 0.9f)
generalPath!!.lineTo(71.7f, 27.699999f)
generalPath!!.lineTo(71.7f, 99.2f)
generalPath!!.lineTo(0.2f, 99.2f)
generalPath!!.lineTo(0.2f, 0.9f)
generalPath!!.lineTo(44.9f, 0.9f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 196, 2, 255), 0.038f to Color(241, 200, 41, 255), 0.147f to Color(244, 210, 100, 255), 0.258f to Color(247, 220, 139, 255), 0.372f to Color(249, 229, 172, 255), 0.488f to Color(251, 236, 199, 255), 0.606f to Color(252, 243, 221, 255), 0.728f to Color(254, 249, 238, 255), 0.856f to Color(255, 253, 249, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.12f), end = Offset(36.0f, 0.875f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(44.9f, 0.9f)
generalPath!!.lineTo(71.7f, 27.699999f)
generalPath!!.lineTo(71.7f, 99.2f)
generalPath!!.lineTo(0.2f, 99.2f)
generalPath!!.lineTo(0.2f, 0.9f)
generalPath!!.lineTo(44.9f, 0.9f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(186, 156, 2, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(44.9f, 0.9f)
generalPath!!.lineTo(71.7f, 27.699999f)
generalPath!!.lineTo(71.7f, 99.2f)
generalPath!!.lineTo(0.2f, 99.2f)
generalPath!!.lineTo(0.2f, 0.9f)
generalPath!!.lineTo(44.9f, 0.9f)
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
generalPath!!.moveTo(8.1f, 92.2f)
generalPath!!.lineTo(8.1f, 72.3f)
generalPath!!.lineTo(16.6f, 72.3f)
generalPath!!.cubicTo(18.7f, 72.3f, 20.300001f, 72.5f, 21.2f, 72.8f)
generalPath!!.cubicTo(22.1f, 73.100006f, 22.900002f, 73.8f, 23.5f, 74.700005f)
generalPath!!.cubicTo(24.1f, 75.600006f, 24.4f, 76.700005f, 24.4f, 77.8f)
generalPath!!.cubicTo(24.4f, 79.3f, 24.0f, 80.5f, 23.1f, 81.5f)
generalPath!!.cubicTo(22.2f, 82.5f, 20.9f, 83.1f, 19.2f, 83.3f)
generalPath!!.cubicTo(20.1f, 83.8f, 20.800001f, 84.4f, 21.400002f, 85.0f)
generalPath!!.cubicTo(22.000002f, 85.6f, 22.7f, 86.7f, 23.7f, 88.2f)
generalPath!!.lineTo(26.1f, 92.1f)
generalPath!!.lineTo(21.3f, 92.1f)
generalPath!!.lineTo(18.4f, 87.799995f)
generalPath!!.cubicTo(17.4f, 86.299995f, 16.699999f, 85.299995f, 16.3f, 84.899994f)
generalPath!!.cubicTo(15.9f, 84.49999f, 15.499999f, 84.2f, 15.099999f, 84.09999f)
generalPath!!.cubicTo(14.7f, 83.99999f, 13.999999f, 83.899994f, 13.099999f, 83.899994f)
generalPath!!.lineTo(12.299999f, 83.899994f)
generalPath!!.lineTo(12.299999f, 92.2f)
generalPath!!.lineTo(8.1f, 92.2f)
generalPath!!.close()
generalPath!!.moveTo(12.1f, 80.7f)
generalPath!!.lineTo(15.1f, 80.7f)
generalPath!!.cubicTo(17.0f, 80.7f, 18.2f, 80.6f, 18.7f, 80.5f)
generalPath!!.cubicTo(19.2f, 80.3f, 19.6f, 80.1f, 19.800001f, 79.7f)
generalPath!!.cubicTo(20.1f, 79.299995f, 20.2f, 78.799995f, 20.2f, 78.2f)
generalPath!!.cubicTo(20.2f, 77.5f, 20.0f, 77.0f, 19.7f, 76.6f)
generalPath!!.cubicTo(19.300001f, 76.2f, 18.800001f, 75.9f, 18.2f, 75.799995f)
generalPath!!.cubicTo(17.900002f, 75.799995f, 16.900002f, 75.7f, 15.300001f, 75.7f)
generalPath!!.lineTo(12.200001f, 75.7f)
generalPath!!.lineTo(12.200001f, 80.7f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
generalPath!!.moveTo(46.1f, 92.2f)
generalPath!!.lineTo(41.699997f, 92.2f)
generalPath!!.lineTo(40.0f, 87.6f)
generalPath!!.lineTo(32.0f, 87.6f)
generalPath!!.lineTo(30.4f, 92.1f)
generalPath!!.lineTo(26.099998f, 92.1f)
generalPath!!.lineTo(33.899998f, 72.2f)
generalPath!!.lineTo(38.1f, 72.2f)
generalPath!!.lineTo(46.1f, 92.2f)
generalPath!!.close()
generalPath!!.moveTo(38.699997f, 84.299995f)
generalPath!!.lineTo(36.0f, 76.9f)
generalPath!!.lineTo(33.3f, 84.3f)
generalPath!!.lineTo(38.7f, 84.3f)
generalPath!!.close()
generalPath!!.moveTo(48.299995f, 92.2f)
generalPath!!.lineTo(48.299995f, 72.3f)
generalPath!!.lineTo(56.799995f, 72.3f)
generalPath!!.cubicTo(58.899994f, 72.3f, 60.499996f, 72.5f, 61.399994f, 72.8f)
generalPath!!.cubicTo(62.29999f, 73.100006f, 63.099995f, 73.8f, 63.699993f, 74.700005f)
generalPath!!.cubicTo(64.299995f, 75.600006f, 64.59999f, 76.700005f, 64.59999f, 77.8f)
generalPath!!.cubicTo(64.59999f, 79.3f, 64.19999f, 80.5f, 63.29999f, 81.5f)
generalPath!!.cubicTo(62.399994f, 82.5f, 61.09999f, 83.1f, 59.39999f, 83.3f)
generalPath!!.cubicTo(60.29999f, 83.8f, 60.99999f, 84.4f, 61.59999f, 85.0f)
generalPath!!.cubicTo(62.199993f, 85.6f, 62.89999f, 86.7f, 63.89999f, 88.2f)
generalPath!!.lineTo(66.29999f, 92.1f)
generalPath!!.lineTo(61.49999f, 92.1f)
generalPath!!.lineTo(58.599987f, 87.799995f)
generalPath!!.cubicTo(57.599987f, 86.299995f, 56.899986f, 85.299995f, 56.49999f, 84.899994f)
generalPath!!.cubicTo(56.099987f, 84.49999f, 55.69999f, 84.2f, 55.299988f, 84.09999f)
generalPath!!.cubicTo(54.899986f, 83.99999f, 54.19999f, 83.899994f, 53.299988f, 83.899994f)
generalPath!!.lineTo(52.49999f, 83.899994f)
generalPath!!.lineTo(52.49999f, 92.2f)
generalPath!!.lineTo(48.299988f, 92.2f)
generalPath!!.close()
generalPath!!.moveTo(52.299995f, 80.7f)
generalPath!!.lineTo(55.299995f, 80.7f)
generalPath!!.cubicTo(57.199997f, 80.7f, 58.399994f, 80.6f, 58.899994f, 80.5f)
generalPath!!.cubicTo(59.399994f, 80.3f, 59.799995f, 80.1f, 59.999992f, 79.7f)
generalPath!!.cubicTo(60.29999f, 79.299995f, 60.399994f, 78.799995f, 60.399994f, 78.2f)
generalPath!!.cubicTo(60.399994f, 77.5f, 60.199993f, 77.0f, 59.899994f, 76.6f)
generalPath!!.cubicTo(59.499992f, 76.2f, 58.999992f, 75.9f, 58.399994f, 75.799995f)
generalPath!!.cubicTo(58.099995f, 75.799995f, 57.099995f, 75.7f, 55.499992f, 75.7f)
generalPath!!.lineTo(52.399994f, 75.7f)
generalPath!!.lineTo(52.399994f, 80.7f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.98f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(44.9f, 0.9f)
generalPath!!.lineTo(71.7f, 27.699999f)
generalPath!!.lineTo(44.9f, 27.699999f)
generalPath!!.lineTo(44.9f, 0.9f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(255, 255, 255, 255), 0.234f to Color(255, 254, 251, 255), 0.369f to Color(254, 250, 241, 255), 0.481f to Color(253, 245, 228, 255), 0.579f to Color(252, 240, 210, 255), 0.669f to Color(250, 233, 188, 255), 0.752f to Color(249, 226, 162, 255), 0.831f to Color(247, 218, 131, 255), 0.905f to Color(244, 209, 93, 255), 0.975f to Color(241, 200, 39, 255), 1.0f to Color(239, 196, 2, 255), start = Offset(44.942f, 27.674004f), end = Offset(58.348f, 14.266998f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(44.9f, 0.9f)
generalPath!!.lineTo(71.7f, 27.699999f)
generalPath!!.lineTo(44.9f, 27.699999f)
generalPath!!.lineTo(44.9f, 0.9f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(186, 156, 2, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(44.9f, 0.9f)
generalPath!!.lineTo(71.7f, 27.699999f)
generalPath!!.lineTo(44.9f, 27.699999f)
generalPath!!.lineTo(44.9f, 0.9f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.98f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(38.0f, 15.8f)
generalPath!!.lineTo(29.6f, 15.8f)
generalPath!!.lineTo(29.6f, 10.8f)
generalPath!!.lineTo(38.0f, 10.8f)
generalPath!!.lineTo(38.0f, 15.8f)
generalPath!!.close()
generalPath!!.moveTo(38.0f, 24.7f)
generalPath!!.lineTo(29.6f, 24.7f)
generalPath!!.lineTo(29.6f, 29.7f)
generalPath!!.lineTo(38.0f, 29.7f)
generalPath!!.lineTo(38.0f, 24.7f)
generalPath!!.close()
generalPath!!.moveTo(38.0f, 3.9000015f)
generalPath!!.lineTo(29.6f, 3.9000015f)
generalPath!!.lineTo(29.6f, 8.900002f)
generalPath!!.lineTo(38.0f, 8.900002f)
generalPath!!.lineTo(38.0f, 3.9000015f)
generalPath!!.close()
generalPath!!.moveTo(38.0f, 17.7f)
generalPath!!.lineTo(29.6f, 17.7f)
generalPath!!.lineTo(29.6f, 22.7f)
generalPath!!.lineTo(38.0f, 22.7f)
generalPath!!.lineTo(38.0f, 17.7f)
generalPath!!.close()
generalPath!!.moveTo(38.0f, 31.6f)
generalPath!!.lineTo(29.6f, 31.6f)
generalPath!!.lineTo(29.6f, 36.6f)
generalPath!!.lineTo(38.0f, 36.6f)
generalPath!!.lineTo(38.0f, 31.599998f)
generalPath!!.close()
generalPath!!.moveTo(38.7f, 56.2f)
generalPath!!.cubicTo(38.7f, 59.0f, 36.4f, 61.3f, 33.600002f, 61.3f)
generalPath!!.cubicTo(30.800003f, 61.3f, 28.500002f, 59.0f, 28.500002f, 56.2f)
generalPath!!.lineTo(28.500002f, 55.7f)
generalPath!!.lineTo(30.200003f, 41.1f)
generalPath!!.cubicTo(30.200003f, 39.199997f, 31.700003f, 37.699997f, 33.600002f, 37.699997f)
generalPath!!.cubicTo(35.4f, 37.699997f, 36.9f, 39.199997f, 37.000004f, 40.999996f)
generalPath!!.lineTo(38.600002f, 55.399994f)
generalPath!!.cubicTo(38.7f, 55.799995f, 38.7f, 55.999992f, 38.7f, 56.199993f)
generalPath!!.close()
generalPath!!.moveTo(37.100002f, 56.100002f)
generalPath!!.cubicTo(37.100002f, 54.2f, 35.500004f, 52.600002f, 33.600002f, 52.600002f)
generalPath!!.cubicTo(31.7f, 52.600002f, 30.100002f, 54.2f, 30.100002f, 56.100002f)
generalPath!!.cubicTo(30.100002f, 58.000004f, 31.700003f, 59.600002f, 33.600002f, 59.600002f)
generalPath!!.cubicTo(35.600002f, 59.500004f, 37.100002f, 57.9f, 37.100002f, 56.100002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(164, 125, 3, 255), 0.533f to Color(222, 190, 0, 255), 0.639f to Color(207, 173, 4, 255), 1.0f to Color(160, 120, 2, 255), start = Offset(33.617f, 61.311f), end = Offset(33.617f, 3.8519974f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.98f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(32.5f, 41.6f)
generalPath!!.lineTo(30.2f, 37.1f)
generalPath!!.lineTo(30.2f, 34.8f)
generalPath!!.lineTo(32.5f, 32.6f)
generalPath!!.lineTo(34.7f, 32.6f)
generalPath!!.lineTo(37.0f, 34.8f)
generalPath!!.lineTo(37.0f, 37.1f)
generalPath!!.lineTo(34.7f, 41.6f)
generalPath!!.lineTo(32.5f, 41.6f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
            return 0.73499995470047
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

