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
class ext_gpg : Painter() {
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
    moveTo(16.5f, 84.5f)
    lineTo(16.5f, 81.1f)
    lineTo(25.2f, 81.1f)
    lineTo(25.2f, 89.1f)
    cubicTo(24.400002f, 89.9f, 23.1f, 90.6f, 21.5f, 91.299995f)
    cubicTo(19.9f, 91.899994f, 18.3f, 92.2f, 16.6f, 92.2f)
    cubicTo(14.5f, 92.2f, 12.700001f, 91.799995f, 11.1f, 90.899994f)
    cubicTo(9.5f, 89.99999f, 8.400001f, 88.799995f, 7.6000004f, 87.09999f)
    cubicTo(6.8f, 85.49999f, 6.4000006f, 83.69999f, 6.4000006f, 81.79999f)
    cubicTo(6.4000006f, 79.69999f, 6.8000007f, 77.89999f, 7.700001f, 76.19999f)
    cubicTo(8.6f, 74.49999f, 9.900001f, 73.29999f, 11.500001f, 72.49999f)
    cubicTo(12.800001f, 71.799995f, 14.400002f, 71.49999f, 16.300001f, 71.49999f)
    cubicTo(18.800001f, 71.49999f, 20.7f, 71.99999f, 22.100002f, 73.09999f)
    cubicTo(23.500002f, 74.09999f, 24.400002f, 75.59999f, 24.800003f, 77.399994f)
    lineTo(20.800003f, 78.2f)
    cubicTo(20.500004f, 77.2f, 20.000004f, 76.399994f, 19.200003f, 75.899994f)
    cubicTo(18.400002f, 75.399994f, 17.600002f, 74.99999f, 16.400003f, 74.99999f)
    cubicTo(14.600003f, 74.99999f, 13.200004f, 75.59999f, 12.200004f, 76.69999f)
    cubicTo(11.200004f, 77.79999f, 10.600003f, 79.49999f, 10.600003f, 81.69999f)
    cubicTo(10.600003f, 84.09999f, 11.100003f, 85.89999f, 12.200004f, 87.09999f)
    cubicTo(13.300004f, 88.29999f, 14.600004f, 88.899994f, 16.400003f, 88.899994f)
    cubicTo(17.200003f, 88.899994f, 18.100004f, 88.7f, 18.900003f, 88.399994f)
    cubicTo(19.800003f, 88.09999f, 20.500004f, 87.7f, 21.100004f, 87.2f)
    lineTo(21.100004f, 84.7f)
    lineTo(16.500004f, 84.7f)
    close()
    moveTo(28.9f, 91.9f)
    lineTo(28.9f, 71.9f)
    lineTo(35.4f, 71.9f)
    cubicTo(37.9f, 71.9f, 39.5f, 72.0f, 40.2f, 72.200005f)
    cubicTo(41.3f, 72.50001f, 42.3f, 73.200005f, 43.100002f, 74.200005f)
    cubicTo(43.900005f, 75.200005f, 44.300003f, 76.50001f, 44.300003f, 78.100006f)
    cubicTo(44.300003f, 79.3f, 44.100002f, 80.40001f, 43.600002f, 81.200005f)
    cubicTo(43.100002f, 82.0f, 42.600002f, 82.700005f, 41.9f, 83.200005f)
    cubicTo(41.2f, 83.700005f, 40.5f, 84.00001f, 39.800003f, 84.200005f)
    cubicTo(38.800003f, 84.4f, 37.4f, 84.50001f, 35.600002f, 84.50001f)
    lineTo(33.0f, 84.50001f)
    lineTo(33.0f, 92.100006f)
    lineTo(28.9f, 92.100006f)
    close()
    moveTo(33.0f, 75.2f)
    lineTo(33.0f, 80.899994f)
    lineTo(35.2f, 80.899994f)
    cubicTo(36.8f, 80.899994f, 37.9f, 80.799995f, 38.4f, 80.59999f)
    cubicTo(38.9f, 80.39999f, 39.4f, 80.09999f, 39.7f, 79.59999f)
    cubicTo(40.0f, 79.09999f, 40.2f, 78.59999f, 40.2f, 77.99999f)
    cubicTo(40.2f, 77.299995f, 40.0f, 76.69999f, 39.600002f, 76.19999f)
    cubicTo(39.200005f, 75.69999f, 38.600002f, 75.39999f, 38.000004f, 75.29999f)
    cubicTo(37.500004f, 75.19999f, 36.500004f, 75.19999f, 35.100002f, 75.19999f)
    lineTo(33.0f, 75.19999f)
    close()
    moveTo(56.9f, 84.5f)
    lineTo(56.9f, 81.1f)
    lineTo(65.6f, 81.1f)
    lineTo(65.6f, 89.1f)
    cubicTo(64.799995f, 89.9f, 63.5f, 90.6f, 61.899998f, 91.299995f)
    cubicTo(60.299995f, 91.99999f, 58.699997f, 92.2f, 56.999996f, 92.2f)
    cubicTo(54.899998f, 92.2f, 53.099995f, 91.799995f, 51.499996f, 90.899994f)
    cubicTo(49.899998f, 89.99999f, 48.799995f, 88.799995f, 47.999996f, 87.09999f)
    cubicTo(47.199997f, 85.39999f, 46.799995f, 83.69999f, 46.799995f, 81.79999f)
    cubicTo(46.799995f, 79.69999f, 47.199997f, 77.89999f, 48.099995f, 76.19999f)
    cubicTo(48.999992f, 74.49999f, 50.299995f, 73.29999f, 51.899994f, 72.49999f)
    cubicTo(53.199993f, 71.799995f, 54.799995f, 71.49999f, 56.699993f, 71.49999f)
    cubicTo(59.199993f, 71.49999f, 61.099995f, 71.99999f, 62.499992f, 73.09999f)
    cubicTo(63.89999f, 74.19999f, 64.799995f, 75.59999f, 65.19999f, 77.399994f)
    lineTo(61.19999f, 78.2f)
    cubicTo(60.89999f, 77.2f, 60.39999f, 76.399994f, 59.59999f, 75.899994f)
    cubicTo(58.79999f, 75.399994f, 58.0f, 75.0f, 56.9f, 75.0f)
    cubicTo(55.100002f, 75.0f, 53.7f, 75.6f, 52.7f, 76.7f)
    cubicTo(51.7f, 77.799995f, 51.100002f, 79.5f, 51.100002f, 81.7f)
    cubicTo(51.100002f, 84.1f, 51.600002f, 85.899994f, 52.7f, 87.1f)
    cubicTo(53.8f, 88.3f, 55.100002f, 88.9f, 56.9f, 88.9f)
    cubicTo(57.7f, 88.9f, 58.600002f, 88.700005f, 59.4f, 88.4f)
    cubicTo(60.2f, 88.1f, 61.0f, 87.700005f, 61.600002f, 87.200005f)
    lineTo(61.600002f, 84.700005f)
    lineTo(56.9f, 84.700005f)
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

