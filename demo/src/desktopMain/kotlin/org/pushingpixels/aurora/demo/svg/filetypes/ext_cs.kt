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
class ext_cs : Painter() {
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
    moveTo(31.3f, 82.4f)
    lineTo(35.3f, 83.6f)
    cubicTo(34.7f, 85.799995f, 33.7f, 87.4f, 32.3f, 88.5f)
    cubicTo(30.9f, 89.6f, 29.099998f, 90.1f, 26.9f, 90.1f)
    cubicTo(24.199999f, 90.1f, 22.0f, 89.2f, 20.3f, 87.4f)
    cubicTo(18.599998f, 85.6f, 17.699999f, 83.1f, 17.699999f, 79.9f)
    cubicTo(17.699999f, 76.6f, 18.599998f, 74.0f, 20.3f, 72.1f)
    cubicTo(22.0f, 70.299995f, 24.3f, 69.299995f, 27.199999f, 69.299995f)
    cubicTo(29.699999f, 69.299995f, 31.699999f, 69.99999f, 33.199997f, 71.49999f)
    cubicTo(34.1f, 72.399994f, 34.799995f, 73.59999f, 35.299995f, 75.19999f)
    lineTo(31.299995f, 76.19999f)
    cubicTo(31.099995f, 75.19999f, 30.599995f, 74.29999f, 29.799995f, 73.69999f)
    cubicTo(28.999996f, 73.09999f, 28.099995f, 72.79999f, 26.999996f, 72.79999f)
    cubicTo(25.499996f, 72.79999f, 24.299995f, 73.29999f, 23.299995f, 74.39999f)
    cubicTo(22.299995f, 75.499985f, 21.899996f, 77.19999f, 21.899996f, 79.59998f)
    cubicTo(21.899996f, 82.09998f, 22.399996f, 83.89999f, 23.299995f, 84.999985f)
    cubicTo(24.199995f, 86.09998f, 25.399996f, 86.59998f, 26.899996f, 86.59998f)
    cubicTo(27.999996f, 86.59998f, 28.899996f, 86.29998f, 29.699995f, 85.59998f)
    cubicTo(30.499994f, 84.89999f, 30.899996f, 83.89999f, 31.299995f, 82.39999f)
    close()
    moveTo(37.7f, 83.3f)
    lineTo(41.7f, 82.9f)
    cubicTo(41.9f, 84.200005f, 42.4f, 85.200005f, 43.2f, 85.8f)
    cubicTo(44.0f, 86.4f, 44.9f, 86.700005f, 46.2f, 86.700005f)
    cubicTo(47.5f, 86.700005f, 48.5f, 86.4f, 49.2f, 85.9f)
    cubicTo(49.9f, 85.3f, 50.2f, 84.700005f, 50.2f, 84.0f)
    cubicTo(50.2f, 83.5f, 50.100002f, 83.1f, 49.8f, 82.8f)
    cubicTo(49.5f, 82.5f, 49.0f, 82.200005f, 48.3f, 81.9f)
    cubicTo(47.8f, 81.700005f, 46.7f, 81.4f, 45.0f, 81.0f)
    cubicTo(42.8f, 80.5f, 41.3f, 79.8f, 40.4f, 79.0f)
    cubicTo(39.2f, 77.9f, 38.5f, 76.6f, 38.5f, 75.0f)
    cubicTo(38.5f, 74.0f, 38.8f, 73.0f, 39.4f, 72.1f)
    cubicTo(40.000004f, 71.2f, 40.800003f, 70.5f, 41.9f, 70.1f)
    cubicTo(43.0f, 69.6f, 44.300003f, 69.4f, 45.9f, 69.4f)
    cubicTo(48.4f, 69.4f, 50.4f, 70.0f, 51.600002f, 71.1f)
    cubicTo(52.9f, 72.2f, 53.600002f, 73.7f, 53.600002f, 75.5f)
    lineTo(49.500004f, 75.7f)
    cubicTo(49.300003f, 74.7f, 48.900005f, 73.899994f, 48.400005f, 73.5f)
    cubicTo(47.900005f, 73.100006f, 47.000004f, 72.8f, 45.800007f, 72.8f)
    cubicTo(44.600006f, 72.8f, 43.70001f, 73.0f, 43.000008f, 73.5f)
    cubicTo(42.600006f, 73.8f, 42.40001f, 74.2f, 42.40001f, 74.7f)
    cubicTo(42.40001f, 75.2f, 42.60001f, 75.6f, 43.000008f, 75.899994f)
    cubicTo(43.500008f, 76.299995f, 44.800007f, 76.799995f, 46.800007f, 77.2f)
    cubicTo(48.800007f, 77.7f, 50.300007f, 78.1f, 51.20001f, 78.6f)
    cubicTo(52.10001f, 79.1f, 52.90001f, 79.799995f, 53.40001f, 80.6f)
    cubicTo(53.90001f, 81.5f, 54.20001f, 82.5f, 54.20001f, 83.799995f)
    cubicTo(54.20001f, 84.899994f, 53.90001f, 85.99999f, 53.20001f, 86.99999f)
    cubicTo(52.60001f, 87.99999f, 51.60001f, 88.69999f, 50.500008f, 89.19999f)
    cubicTo(49.400005f, 89.69999f, 47.90001f, 89.89999f, 46.100006f, 89.89999f)
    cubicTo(43.500008f, 89.89999f, 41.600006f, 89.29999f, 40.200005f, 88.09998f)
    cubicTo(38.800003f, 86.89998f, 38.000004f, 85.499985f, 37.700005f, 83.29998f)
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

