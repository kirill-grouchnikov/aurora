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
class ext_log : Painter() {
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
@Suppress("UNUSED_VARIABLE") var shapeText: Outline?
@Suppress("UNUSED_VARIABLE") var generalPathText: Path? = null
@Suppress("UNUSED_VARIABLE") var alphaText = 0.0f
@Suppress("UNUSED_VARIABLE") var blendModeText = DrawScope.DefaultBlendMode
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(72.0f, 99.0f)
generalPath!!.lineTo(0.0f, 99.0f)
generalPath!!.lineTo(0.0f, 1.0f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.0f), end = Offset(36.0f, 1.0f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(72.0f, 99.0f)
generalPath!!.lineTo(0.0f, 99.0f)
generalPath!!.lineTo(0.0f, 1.0f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(72.0f, 99.0f)
generalPath!!.lineTo(0.0f, 99.0f)
generalPath!!.lineTo(0.0f, 1.0f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(11.8f, 33.7f)
generalPath!!.lineTo(60.399998f, 33.7f)
generalPath!!.lineTo(60.399998f, 37.8f)
generalPath!!.lineTo(11.8f, 37.8f)
generalPath!!.lineTo(11.8f, 33.7f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.8f, 35.75f), end = Offset(60.4f, 35.75f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(11.8f, 57.8f)
generalPath!!.lineTo(60.399998f, 57.8f)
generalPath!!.lineTo(60.399998f, 61.899998f)
generalPath!!.lineTo(11.8f, 61.899998f)
generalPath!!.lineTo(11.8f, 57.8f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.8f, 59.85f), end = Offset(60.4f, 59.85f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(11.8f, 46.0f)
generalPath!!.lineTo(60.399998f, 46.0f)
generalPath!!.lineTo(60.399998f, 50.1f)
generalPath!!.lineTo(11.8f, 50.1f)
generalPath!!.lineTo(11.8f, 46.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.8f, 48.05f), end = Offset(60.4f, 48.05f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(11.8f, 21.6f)
generalPath!!.lineTo(60.399998f, 21.6f)
generalPath!!.lineTo(60.399998f, 25.7f)
generalPath!!.lineTo(11.8f, 25.7f)
generalPath!!.lineTo(11.8f, 21.6f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.8f, 23.65f), end = Offset(60.4f, 23.65f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(45.0f, 27.7f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.037f, 27.813f), end = Offset(58.537f, 14.313f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(45.0f, 27.7f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(45.0f, 27.7f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.6f, 91.2f)
generalPath!!.lineTo(7.6f, 71.3f)
generalPath!!.lineTo(11.6f, 71.3f)
generalPath!!.lineTo(11.6f, 87.8f)
generalPath!!.lineTo(21.7f, 87.8f)
generalPath!!.lineTo(21.7f, 91.200005f)
generalPath!!.lineTo(7.6f, 91.200005f)
generalPath!!.close()
generalPath!!.moveTo(23.800001f, 81.299995f)
generalPath!!.cubicTo(23.800001f, 79.299995f, 24.1f, 77.49999f, 24.7f, 76.2f)
generalPath!!.cubicTo(25.2f, 75.2f, 25.800001f, 74.299995f, 26.6f, 73.5f)
generalPath!!.cubicTo(27.4f, 72.7f, 28.300001f, 72.1f, 29.2f, 71.7f)
generalPath!!.cubicTo(30.400002f, 71.2f, 31.900002f, 70.899994f, 33.5f, 70.899994f)
generalPath!!.cubicTo(36.5f, 70.899994f, 38.8f, 71.799995f, 40.6f, 73.59999f)
generalPath!!.cubicTo(42.399998f, 75.399994f, 43.3f, 77.99999f, 43.3f, 81.19999f)
generalPath!!.cubicTo(43.3f, 84.39999f, 42.399998f, 86.99999f, 40.7f, 88.79999f)
generalPath!!.cubicTo(38.9f, 90.59999f, 36.600002f, 91.499985f, 33.600002f, 91.499985f)
generalPath!!.cubicTo(30.600002f, 91.499985f, 28.200003f, 90.59998f, 26.500002f, 88.79999f)
generalPath!!.cubicTo(24.600002f, 86.999985f, 23.800001f, 84.499985f, 23.800001f, 81.29999f)
generalPath!!.close()
generalPath!!.moveTo(27.900002f, 81.2f)
generalPath!!.cubicTo(27.900002f, 83.5f, 28.400002f, 85.2f, 29.500002f, 86.399994f)
generalPath!!.cubicTo(30.500002f, 87.59999f, 31.900002f, 88.2f, 33.5f, 88.2f)
generalPath!!.cubicTo(35.1f, 88.2f, 36.4f, 87.6f, 37.5f, 86.5f)
generalPath!!.cubicTo(38.5f, 85.3f, 39.1f, 83.6f, 39.1f, 81.3f)
generalPath!!.cubicTo(39.1f, 79.0f, 38.6f, 77.3f, 37.6f, 76.200005f)
generalPath!!.cubicTo(36.6f, 75.100006f, 35.3f, 74.50001f, 33.6f, 74.50001f)
generalPath!!.cubicTo(31.899998f, 74.50001f, 30.599998f, 75.100006f, 29.599998f, 76.200005f)
generalPath!!.cubicTo(28.399998f, 77.100006f, 27.899998f, 78.9f, 27.899998f, 81.200005f)
generalPath!!.close()
generalPath!!.moveTo(55.7f, 83.799995f)
generalPath!!.lineTo(55.7f, 80.399994f)
generalPath!!.lineTo(64.4f, 80.399994f)
generalPath!!.lineTo(64.4f, 88.399994f)
generalPath!!.cubicTo(63.600002f, 89.2f, 62.300003f, 89.899994f, 60.7f, 90.59999f)
generalPath!!.cubicTo(59.100002f, 91.19999f, 57.5f, 91.49999f, 55.8f, 91.49999f)
generalPath!!.cubicTo(53.7f, 91.49999f, 51.899998f, 91.09999f, 50.3f, 90.19999f)
generalPath!!.cubicTo(48.7f, 89.29999f, 47.6f, 88.09999f, 46.8f, 86.39999f)
generalPath!!.cubicTo(46.0f, 84.79999f, 45.6f, 82.999985f, 45.6f, 81.09998f)
generalPath!!.cubicTo(45.6f, 78.999985f, 46.0f, 77.19998f, 46.899998f, 75.499985f)
generalPath!!.cubicTo(47.799995f, 73.79999f, 49.1f, 72.59998f, 50.699997f, 71.79999f)
generalPath!!.cubicTo(51.999996f, 71.09999f, 53.6f, 70.79999f, 55.499996f, 70.79999f)
generalPath!!.cubicTo(57.999996f, 70.79999f, 59.899998f, 71.29999f, 61.299995f, 72.39999f)
generalPath!!.cubicTo(62.699997f, 73.39999f, 63.599995f, 74.89999f, 63.999996f, 76.69999f)
generalPath!!.lineTo(59.999996f, 77.49999f)
generalPath!!.cubicTo(59.699997f, 76.49999f, 59.199997f, 75.69999f, 58.399998f, 75.19999f)
generalPath!!.cubicTo(57.6f, 74.69999f, 56.6f, 74.29999f, 55.499996f, 74.29999f)
generalPath!!.cubicTo(53.699997f, 74.29999f, 52.299995f, 74.89999f, 51.299995f, 75.999985f)
generalPath!!.cubicTo(50.299995f, 77.09998f, 49.699997f, 78.79999f, 49.699997f, 80.999985f)
generalPath!!.cubicTo(49.699997f, 83.39999f, 50.199997f, 85.19998f, 51.299995f, 86.39999f)
generalPath!!.cubicTo(52.399994f, 87.59998f, 53.699997f, 88.19999f, 55.499996f, 88.19999f)
generalPath!!.cubicTo(56.299995f, 88.19999f, 57.199997f, 87.99999f, 57.999996f, 87.69999f)
generalPath!!.cubicTo(58.899998f, 87.39999f, 59.599995f, 86.99999f, 60.199997f, 86.49999f)
generalPath!!.lineTo(60.199997f, 84.0f)
generalPath!!.lineTo(55.699997f, 84.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
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
            return 0.12999999523162842
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
            return 0.7400000095367432
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

