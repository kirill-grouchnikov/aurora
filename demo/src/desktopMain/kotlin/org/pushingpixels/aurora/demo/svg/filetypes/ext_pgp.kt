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
class ext_pgp : Painter() {
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
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
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.037f, 27.813f), end = Offset(58.537f, 14.313f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
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
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.2f, 91.9f)
    lineTo(9.2f, 71.9f)
    lineTo(15.7f, 71.9f)
    cubicTo(18.2f, 71.9f, 19.8f, 72.0f, 20.5f, 72.200005f)
    cubicTo(21.6f, 72.50001f, 22.6f, 73.200005f, 23.4f, 74.200005f)
    cubicTo(24.199999f, 75.200005f, 24.6f, 76.50001f, 24.6f, 78.100006f)
    cubicTo(24.6f, 79.3f, 24.4f, 80.40001f, 23.9f, 81.200005f)
    cubicTo(23.5f, 82.00001f, 22.9f, 82.700005f, 22.199999f, 83.200005f)
    cubicTo(21.499998f, 83.700005f, 20.8f, 84.00001f, 20.099998f, 84.200005f)
    cubicTo(19.099998f, 84.4f, 17.699999f, 84.50001f, 15.899999f, 84.50001f)
    lineTo(13.299999f, 84.50001f)
    lineTo(13.299999f, 92.100006f)
    lineTo(9.2f, 92.100006f)
    close()
    moveTo(13.299999f, 75.2f)
    lineTo(13.299999f, 80.899994f)
    lineTo(15.499999f, 80.899994f)
    cubicTo(17.099998f, 80.899994f, 18.199999f, 80.799995f, 18.699999f, 80.59999f)
    cubicTo(19.199999f, 80.399994f, 19.699999f, 80.09999f, 19.999998f, 79.59999f)
    cubicTo(20.299997f, 79.19999f, 20.499998f, 78.59999f, 20.499998f, 77.99999f)
    cubicTo(20.499998f, 77.299995f, 20.299997f, 76.69999f, 19.899998f, 76.19999f)
    cubicTo(19.499998f, 75.69999f, 18.899998f, 75.39999f, 18.299997f, 75.29999f)
    cubicTo(17.799997f, 75.19999f, 16.799997f, 75.19999f, 15.399998f, 75.19999f)
    lineTo(13.299997f, 75.19999f)
    close()
    moveTo(37.199997f, 84.5f)
    lineTo(37.199997f, 81.1f)
    lineTo(45.899998f, 81.1f)
    lineTo(45.899998f, 89.1f)
    cubicTo(45.1f, 89.9f, 43.8f, 90.6f, 42.199997f, 91.299995f)
    cubicTo(40.6f, 91.899994f, 38.999996f, 92.2f, 37.299995f, 92.2f)
    cubicTo(35.199997f, 92.2f, 33.399994f, 91.799995f, 31.799995f, 90.899994f)
    cubicTo(30.199997f, 89.99999f, 29.099995f, 88.799995f, 28.299995f, 87.09999f)
    cubicTo(27.499996f, 85.49999f, 27.099995f, 83.69999f, 27.099995f, 81.79999f)
    cubicTo(27.099995f, 79.69999f, 27.499994f, 77.89999f, 28.399994f, 76.19999f)
    cubicTo(29.299994f, 74.49999f, 30.599995f, 73.29999f, 32.199993f, 72.49999f)
    cubicTo(33.499992f, 71.799995f, 35.099995f, 71.49999f, 36.999992f, 71.49999f)
    cubicTo(39.499992f, 71.49999f, 41.399994f, 71.99999f, 42.79999f, 73.09999f)
    cubicTo(44.199993f, 74.09999f, 45.09999f, 75.59999f, 45.499992f, 77.399994f)
    lineTo(41.499992f, 78.2f)
    cubicTo(41.199993f, 77.2f, 40.699993f, 76.399994f, 39.899994f, 75.899994f)
    cubicTo(39.099995f, 75.399994f, 38.299995f, 74.99999f, 37.199993f, 74.99999f)
    cubicTo(35.399994f, 74.99999f, 33.999992f, 75.59999f, 32.999992f, 76.69999f)
    cubicTo(31.999992f, 77.79999f, 31.399992f, 79.49999f, 31.399992f, 81.69999f)
    cubicTo(31.399992f, 84.09999f, 31.899992f, 85.89999f, 32.999992f, 87.09999f)
    cubicTo(34.09999f, 88.29999f, 35.399994f, 88.899994f, 37.199993f, 88.899994f)
    cubicTo(37.999992f, 88.899994f, 38.899994f, 88.7f, 39.699993f, 88.399994f)
    cubicTo(40.599995f, 88.09999f, 41.29999f, 87.7f, 41.899994f, 87.2f)
    lineTo(41.899994f, 84.7f)
    lineTo(37.199993f, 84.7f)
    close()
    moveTo(49.699997f, 91.9f)
    lineTo(49.699997f, 71.9f)
    lineTo(56.199997f, 71.9f)
    cubicTo(58.699997f, 71.9f, 60.299995f, 72.0f, 60.999996f, 72.200005f)
    cubicTo(62.099995f, 72.50001f, 63.099995f, 73.200005f, 63.899998f, 74.200005f)
    cubicTo(64.7f, 75.200005f, 65.1f, 76.50001f, 65.1f, 78.100006f)
    cubicTo(65.1f, 79.3f, 64.9f, 80.40001f, 64.4f, 81.200005f)
    cubicTo(64.0f, 82.00001f, 63.4f, 82.700005f, 62.7f, 83.200005f)
    cubicTo(62.0f, 83.700005f, 61.3f, 84.00001f, 60.600002f, 84.200005f)
    cubicTo(59.600002f, 84.4f, 58.2f, 84.50001f, 56.4f, 84.50001f)
    lineTo(53.800003f, 84.50001f)
    lineTo(53.800003f, 92.100006f)
    lineTo(49.700005f, 92.100006f)
    close()
    moveTo(53.699997f, 75.2f)
    lineTo(53.699997f, 80.899994f)
    lineTo(55.899998f, 80.899994f)
    cubicTo(57.499996f, 80.899994f, 58.6f, 80.799995f, 59.1f, 80.59999f)
    cubicTo(59.6f, 80.399994f, 60.1f, 80.09999f, 60.399998f, 79.59999f)
    cubicTo(60.699997f, 79.19999f, 60.899998f, 78.59999f, 60.899998f, 77.99999f)
    cubicTo(60.899998f, 77.299995f, 60.699997f, 76.69999f, 60.3f, 76.19999f)
    cubicTo(59.899998f, 75.69999f, 59.3f, 75.39999f, 58.7f, 75.29999f)
    cubicTo(58.2f, 75.19999f, 57.2f, 75.19999f, 55.8f, 75.19999f)
    lineTo(53.7f, 75.19999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.9f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, -952.3619995117188f, 0.0f, 1.0f)
))}){
// _0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.4f, 978.9f)
    cubicTo(38.5f, 982.0f, 39.5f, 986.4f, 38.300003f, 990.4f)
    lineTo(54.800003f, 1006.9f)
    lineTo(55.200005f, 1014.30005f)
    lineTo(45.900005f, 1013.50006f)
    lineTo(45.900005f, 1008.80005f)
    lineTo(41.200005f, 1008.80005f)
    lineTo(41.200005f, 1004.10004f)
    lineTo(36.500004f, 1004.10004f)
    lineTo(30.500004f, 998.10004f)
    cubicTo(26.600004f, 999.30005f, 22.100004f, 998.30005f, 19.000004f, 995.2f)
    cubicTo(14.500004f, 990.7f, 14.500004f, 983.4f, 19.000004f, 978.9f)
    cubicTo(23.600004f, 974.4f, 30.900003f, 974.4f, 35.4f, 978.9f)
    close()
    moveTo(26.800001f, 982.0f)
    cubicTo(26.177185f, 981.3759f, 25.3317f, 981.0252f, 24.45f, 981.0252f)
    cubicTo(23.568304f, 981.0252f, 22.722818f, 981.3759f, 22.100002f, 982.0f)
    cubicTo(21.475904f, 982.6228f, 21.125183f, 983.4683f, 21.125183f, 984.35f)
    cubicTo(21.125183f, 985.2317f, 21.475904f, 986.0772f, 22.100002f, 986.7f)
    cubicTo(23.400002f, 988.0f, 25.500002f, 988.0f, 26.800003f, 986.7f)
    cubicTo(28.100002f, 985.4f, 28.100002f, 983.3f, 26.800003f, 982.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(35.444f, 1014.327f), end = Offset(35.444f, 975.551f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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

