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
class ext_asp : Painter() {
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.5f)
    lineTo(72.0f, 98.8f)
    lineTo(0.0f, 98.8f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 98.735f), end = Offset(36.0f, 0.75f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.5f)
    lineTo(72.0f, 98.8f)
    lineTo(0.0f, 98.8f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.5f)
    lineTo(72.0f, 98.8f)
    lineTo(0.0f, 98.8f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
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
    moveTo(28.0f, 89.8f)
    lineTo(23.6f, 89.8f)
    lineTo(21.800001f, 85.3f)
    lineTo(13.800001f, 85.3f)
    lineTo(12.100001f, 89.8f)
    lineTo(7.9f, 89.8f)
    lineTo(15.7f, 70.0f)
    lineTo(20.0f, 70.0f)
    lineTo(28.0f, 89.8f)
    close()
    moveTo(20.6f, 82.0f)
    lineTo(17.800001f, 74.6f)
    lineTo(15.100001f, 82.0f)
    lineTo(20.600002f, 82.0f)
    close()
    moveTo(29.2f, 83.4f)
    lineTo(33.100002f, 83.0f)
    cubicTo(33.300003f, 84.3f, 33.800003f, 85.3f, 34.500004f, 85.9f)
    cubicTo(35.200005f, 86.5f, 36.200005f, 86.8f, 37.400005f, 86.8f)
    cubicTo(38.700005f, 86.8f, 39.700005f, 86.5f, 40.300007f, 86.0f)
    cubicTo(40.90001f, 85.5f, 41.300007f, 84.8f, 41.300007f, 84.1f)
    cubicTo(41.300007f, 83.6f, 41.20001f, 83.2f, 40.900005f, 82.9f)
    cubicTo(40.600002f, 82.600006f, 40.100006f, 82.3f, 39.400005f, 82.0f)
    cubicTo(38.900005f, 81.8f, 37.800007f, 81.5f, 36.200005f, 81.1f)
    cubicTo(34.000004f, 80.6f, 32.500004f, 79.9f, 31.600004f, 79.1f)
    cubicTo(30.400003f, 78.0f, 29.800005f, 76.7f, 29.800005f, 75.1f)
    cubicTo(29.800005f, 74.1f, 30.100004f, 73.1f, 30.700005f, 72.299995f)
    cubicTo(31.300005f, 71.399994f, 32.100006f, 70.799995f, 33.200005f, 70.299995f)
    cubicTo(34.300003f, 69.799995f, 35.600006f, 69.6f, 37.100006f, 69.6f)
    cubicTo(39.600006f, 69.6f, 41.500008f, 70.1f, 42.800007f, 71.2f)
    cubicTo(44.100006f, 72.299995f, 44.70001f, 73.7f, 44.800007f, 75.6f)
    lineTo(40.800007f, 75.799995f)
    cubicTo(40.600006f, 74.799995f, 40.300007f, 73.99999f, 39.70001f, 73.6f)
    cubicTo(39.10001f, 73.2f, 38.300007f, 72.9f, 37.10001f, 72.9f)
    cubicTo(35.90001f, 72.9f, 35.00001f, 73.1f, 34.30001f, 73.6f)
    cubicTo(33.90001f, 73.9f, 33.700012f, 74.299995f, 33.700012f, 74.799995f)
    cubicTo(33.700012f, 75.299995f, 33.900013f, 75.7f, 34.30001f, 75.99999f)
    cubicTo(34.80001f, 76.399994f, 36.10001f, 76.899994f, 38.00001f, 77.299995f)
    cubicTo(39.900013f, 77.7f, 41.400013f, 78.2f, 42.400013f, 78.7f)
    cubicTo(43.300014f, 79.2f, 44.100014f, 79.899994f, 44.600014f, 80.7f)
    cubicTo(45.100014f, 81.5f, 45.400013f, 82.6f, 45.400013f, 83.899994f)
    cubicTo(45.400013f, 84.99999f, 45.100014f, 86.09999f, 44.400013f, 87.09999f)
    cubicTo(43.800014f, 88.09999f, 42.900013f, 88.79999f, 41.700012f, 89.29999f)
    cubicTo(40.50001f, 89.79999f, 39.100014f, 89.999985f, 37.30001f, 89.999985f)
    cubicTo(34.80001f, 89.999985f, 32.80001f, 89.39999f, 31.500011f, 88.29999f)
    cubicTo(30.200012f, 87.29999f, 29.400011f, 85.59999f, 29.200012f, 83.39999f)
    close()
    moveTo(48.9f, 89.8f)
    lineTo(48.9f, 70.0f)
    lineTo(55.4f, 70.0f)
    cubicTo(57.9f, 70.0f, 59.5f, 70.1f, 60.2f, 70.3f)
    cubicTo(61.3f, 70.600006f, 62.3f, 71.200005f, 63.100002f, 72.200005f)
    cubicTo(63.9f, 73.200005f, 64.3f, 74.50001f, 64.3f, 76.100006f)
    cubicTo(64.3f, 77.3f, 64.100006f, 78.3f, 63.600002f, 79.200005f)
    cubicTo(63.1f, 80.100006f, 62.600002f, 80.700005f, 61.9f, 81.200005f)
    cubicTo(61.2f, 81.700005f, 60.5f, 82.00001f, 59.800003f, 82.100006f)
    cubicTo(58.800003f, 82.3f, 57.4f, 82.40001f, 55.600002f, 82.40001f)
    lineTo(53.0f, 82.40001f)
    lineTo(53.0f, 89.90001f)
    lineTo(48.9f, 89.90001f)
    close()
    moveTo(52.9f, 73.3f)
    lineTo(52.9f, 79.0f)
    lineTo(55.100002f, 79.0f)
    cubicTo(56.7f, 79.0f, 57.800003f, 78.9f, 58.300003f, 78.7f)
    cubicTo(58.800003f, 78.5f, 59.300003f, 78.2f, 59.600002f, 77.7f)
    cubicTo(59.9f, 77.299995f, 60.100002f, 76.7f, 60.100002f, 76.2f)
    cubicTo(60.100002f, 75.5f, 59.9f, 74.899994f, 59.500004f, 74.399994f)
    cubicTo(59.100006f, 73.899994f, 58.500004f, 73.59999f, 57.900005f, 73.49999f)
    cubicTo(57.400005f, 73.399994f, 56.400005f, 73.399994f, 55.000004f, 73.399994f)
    lineTo(52.900005f, 73.399994f)
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
    moveTo(28.2f, 44.6f)
    cubicTo(26.800001f, 45.6f, 25.5f, 46.699997f, 24.2f, 47.699997f)
    cubicTo(21.5f, 49.699997f, 18.900002f, 51.799995f, 16.2f, 53.799995f)
    cubicTo(15.800001f, 54.099995f, 15.6f, 54.099995f, 15.200001f, 53.899994f)
    cubicTo(14.700001f, 53.599995f, 14.1f, 53.299995f, 13.500001f, 52.999992f)
    cubicTo(13.200001f, 52.79999f, 13.100001f, 52.59999f, 13.100001f, 52.29999f)
    lineTo(13.100001f, 31.1f)
    cubicTo(13.100001f, 30.9f, 13.300001f, 30.5f, 13.500001f, 30.4f)
    cubicTo(14.100001f, 30.0f, 14.800001f, 29.699999f, 15.400001f, 29.4f)
    cubicTo(15.700001f, 29.199999f, 16.0f, 29.4f, 16.300001f, 29.6f)
    cubicTo(18.500002f, 31.300001f, 20.7f, 33.0f, 22.900002f, 34.6f)
    cubicTo(24.7f, 36.0f, 26.500002f, 37.399998f, 28.300001f, 38.699997f)
    cubicTo(28.400002f, 38.6f, 28.6f, 38.499996f, 28.7f, 38.399998f)
    cubicTo(35.3f, 31.999998f, 41.9f, 25.599998f, 48.4f, 19.199997f)
    cubicTo(48.7f, 18.899998f, 49.0f, 18.799997f, 49.4f, 18.999996f)
    cubicTo(52.2f, 20.099997f, 55.0f, 21.199997f, 57.800003f, 22.399996f)
    cubicTo(58.000004f, 22.499996f, 58.200005f, 22.799995f, 58.300003f, 22.999996f)
    cubicTo(58.4f, 23.099997f, 58.300003f, 23.299995f, 58.300003f, 23.499996f)
    lineTo(58.300003f, 60.0f)
    cubicTo(58.300003f, 60.9f, 58.300003f, 60.9f, 57.4f, 61.2f)
    cubicTo(54.7f, 62.3f, 52.100002f, 63.3f, 49.5f, 64.4f)
    cubicTo(49.0f, 64.6f, 48.7f, 64.5f, 48.4f, 64.200005f)
    cubicTo(41.9f, 57.800003f, 35.4f, 51.500004f, 28.800001f, 45.200005f)
    lineTo(28.2f, 44.600006f)
    close()
    moveTo(47.2f, 50.399998f)
    lineTo(47.2f, 33.0f)
    cubicTo(43.3f, 35.9f, 39.5f, 38.8f, 35.6f, 41.7f)
    cubicTo(39.5f, 44.600002f, 43.3f, 47.5f, 47.199997f, 50.4f)
    close()
    moveTo(22.900002f, 41.699997f)
    cubicTo(21.000002f, 39.999996f, 19.100002f, 38.299995f, 17.100002f, 36.499996f)
    lineTo(17.100002f, 46.899994f)
    cubicTo(19.000002f, 45.199993f, 20.900002f, 43.399994f, 22.900002f, 41.699993f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(35.619f, 64.5f), end = Offset(35.619f, 18.906f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.5f)
    lineTo(45.0f, 27.5f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.069f, 27.543f), end = Offset(58.569f, 14.042999f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.5f)
    lineTo(45.0f, 27.5f)
    lineTo(45.0f, 0.8f)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.5f)
    lineTo(45.0f, 27.5f)
    lineTo(45.0f, 0.8f)
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
            return 0.9980000257492065
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

