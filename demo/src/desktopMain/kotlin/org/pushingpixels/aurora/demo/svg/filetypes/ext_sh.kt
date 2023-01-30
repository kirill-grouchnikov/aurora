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
class ext_sh : Painter() {
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
    moveTo(30.2f, 40.4f)
    lineTo(43.1f, 40.4f)
    lineTo(43.1f, 43.600002f)
    lineTo(30.2f, 43.600002f)
    lineTo(30.2f, 40.4f)
    close()
    moveTo(30.2f, 46.800003f)
    lineTo(43.1f, 46.800003f)
    lineTo(43.1f, 50.0f)
    lineTo(30.2f, 50.0f)
    lineTo(30.2f, 46.8f)
    close()
    moveTo(30.2f, 53.300003f)
    lineTo(43.1f, 53.300003f)
    lineTo(43.1f, 56.500004f)
    lineTo(30.2f, 56.500004f)
    lineTo(30.2f, 53.300003f)
    close()
    moveTo(52.800003f, 24.200003f)
    lineTo(26.9f, 24.200003f)
    cubicTo(23.3f, 24.200003f, 20.4f, 27.100002f, 20.4f, 30.700003f)
    lineTo(20.4f, 59.800003f)
    lineTo(14.0f, 59.800003f)
    cubicTo(14.0f, 63.4f, 16.9f, 66.3f, 20.5f, 66.3f)
    lineTo(46.3f, 66.3f)
    cubicTo(49.899998f, 66.3f, 52.8f, 63.4f, 52.8f, 59.800003f)
    lineTo(52.8f, 33.9f)
    lineTo(59.3f, 33.9f)
    lineTo(59.3f, 30.7f)
    cubicTo(59.2f, 27.1f, 56.399998f, 24.2f, 52.8f, 24.2f)
    close()
    moveTo(49.600002f, 59.200005f)
    cubicTo(49.600002f, 61.300003f, 47.9f, 63.000004f, 45.800003f, 63.000004f)
    lineTo(22.1f, 63.000004f)
    cubicTo(23.7f, 61.900005f, 23.7f, 59.800003f, 23.7f, 59.800003f)
    lineTo(23.7f, 30.7f)
    cubicTo(23.7f, 28.900002f, 25.1f, 27.5f, 26.900002f, 27.5f)
    cubicTo(28.7f, 27.5f, 30.100002f, 28.9f, 30.100002f, 30.7f)
    lineTo(30.100002f, 33.9f)
    lineTo(49.5f, 33.9f)
    lineTo(49.5f, 59.2f)
    close()
    moveTo(33.4f, 30.7f)
    lineTo(33.4f, 27.5f)
    lineTo(52.800003f, 27.5f)
    cubicTo(55.700005f, 27.5f, 56.000004f, 29.3f, 56.000004f, 30.7f)
    lineTo(33.4f, 30.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.632f, 66.231f), end = Offset(36.632f, 24.231f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.632f, 66.606f), end = Offset(36.632f, 23.856f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.75f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.2f, 40.4f)
    lineTo(43.1f, 40.4f)
    lineTo(43.1f, 43.600002f)
    lineTo(30.2f, 43.600002f)
    lineTo(30.2f, 40.4f)
    close()
    moveTo(30.2f, 46.800003f)
    lineTo(43.1f, 46.800003f)
    lineTo(43.1f, 50.0f)
    lineTo(30.2f, 50.0f)
    lineTo(30.2f, 46.8f)
    close()
    moveTo(30.2f, 53.300003f)
    lineTo(43.1f, 53.300003f)
    lineTo(43.1f, 56.500004f)
    lineTo(30.2f, 56.500004f)
    lineTo(30.2f, 53.300003f)
    close()
    moveTo(52.800003f, 24.200003f)
    lineTo(26.9f, 24.200003f)
    cubicTo(23.3f, 24.200003f, 20.4f, 27.100002f, 20.4f, 30.700003f)
    lineTo(20.4f, 59.800003f)
    lineTo(14.0f, 59.800003f)
    cubicTo(14.0f, 63.4f, 16.9f, 66.3f, 20.5f, 66.3f)
    lineTo(46.3f, 66.3f)
    cubicTo(49.899998f, 66.3f, 52.8f, 63.4f, 52.8f, 59.800003f)
    lineTo(52.8f, 33.9f)
    lineTo(59.3f, 33.9f)
    lineTo(59.3f, 30.7f)
    cubicTo(59.2f, 27.1f, 56.399998f, 24.2f, 52.8f, 24.2f)
    close()
    moveTo(49.600002f, 59.200005f)
    cubicTo(49.600002f, 61.300003f, 47.9f, 63.000004f, 45.800003f, 63.000004f)
    lineTo(22.1f, 63.000004f)
    cubicTo(23.7f, 61.900005f, 23.7f, 59.800003f, 23.7f, 59.800003f)
    lineTo(23.7f, 30.7f)
    cubicTo(23.7f, 28.900002f, 25.1f, 27.5f, 26.900002f, 27.5f)
    cubicTo(28.7f, 27.5f, 30.100002f, 28.9f, 30.100002f, 30.7f)
    lineTo(30.100002f, 33.9f)
    lineTo(49.5f, 33.9f)
    lineTo(49.5f, 59.2f)
    close()
    moveTo(33.4f, 30.7f)
    lineTo(33.4f, 27.5f)
    lineTo(52.800003f, 27.5f)
    cubicTo(55.700005f, 27.5f, 56.000004f, 29.3f, 56.000004f, 30.7f)
    lineTo(33.4f, 30.7f)
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.037f, 27.813f), end = Offset(58.537f, 14.313f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
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
// _0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.2f, 86.3f)
    lineTo(23.6f, 86.0f)
    cubicTo(23.800001f, 87.1f, 24.2f, 88.0f, 24.800001f, 88.5f)
    cubicTo(25.400002f, 89.0f, 26.300001f, 89.3f, 27.300001f, 89.3f)
    cubicTo(28.400002f, 89.3f, 29.300001f, 89.100006f, 29.800001f, 88.600006f)
    cubicTo(30.300001f, 88.100006f, 30.6f, 87.600006f, 30.6f, 86.90001f)
    cubicTo(30.6f, 86.50001f, 30.5f, 86.100006f, 30.2f, 85.90001f)
    cubicTo(29.900002f, 85.70001f, 29.5f, 85.40001f, 29.0f, 85.20001f)
    cubicTo(28.6f, 85.10001f, 27.7f, 84.80001f, 26.2f, 84.40001f)
    cubicTo(24.300001f, 83.90001f, 23.0f, 83.40001f, 22.300001f, 82.70001f)
    cubicTo(21.2f, 81.80001f, 20.7f, 80.60001f, 20.7f, 79.20001f)
    cubicTo(20.7f, 78.30001f, 20.900002f, 77.500015f, 21.400002f, 76.70001f)
    cubicTo(21.900002f, 75.90001f, 22.600002f, 75.40001f, 23.500002f, 75.000015f)
    cubicTo(24.400002f, 74.60002f, 25.600002f, 74.40002f, 26.900002f, 74.40002f)
    cubicTo(29.100002f, 74.40002f, 30.7f, 74.90002f, 31.800001f, 75.80002f)
    cubicTo(32.9f, 76.70002f, 33.5f, 78.000015f, 33.5f, 79.60002f)
    lineTo(30.0f, 79.80002f)
    cubicTo(29.9f, 78.90002f, 29.5f, 78.30002f, 29.0f, 77.90002f)
    cubicTo(28.5f, 77.500015f, 27.8f, 77.30002f, 26.8f, 77.30002f)
    cubicTo(25.8f, 77.30002f, 25.0f, 77.500015f, 24.4f, 77.90002f)
    cubicTo(24.0f, 78.20002f, 23.8f, 78.500015f, 23.8f, 79.000015f)
    cubicTo(23.8f, 79.40002f, 24.0f, 79.80002f, 24.3f, 80.000015f)
    cubicTo(24.699999f, 80.40002f, 25.8f, 80.80002f, 27.5f, 81.10001f)
    cubicTo(29.2f, 81.40001f, 30.4f, 81.90002f, 31.2f, 82.30001f)
    cubicTo(32.0f, 82.700005f, 32.600002f, 83.30001f, 33.100002f, 84.00001f)
    cubicTo(33.600002f, 84.700005f, 33.800003f, 85.700005f, 33.800003f, 86.700005f)
    cubicTo(33.800003f, 87.700005f, 33.500004f, 88.600006f, 33.000004f, 89.50001f)
    cubicTo(32.500004f, 90.40001f, 32.000004f, 91.100006f, 31.000004f, 91.600006f)
    cubicTo(30.000004f, 92.100006f, 28.800003f, 92.200005f, 27.300003f, 92.200005f)
    cubicTo(25.100002f, 92.200005f, 23.500004f, 91.700005f, 22.300003f, 90.700005f)
    cubicTo(21.100002f, 89.700005f, 20.400003f, 88.200005f, 20.200003f, 86.3f)
    close()
    moveTo(37.1f, 91.9f)
    lineTo(37.1f, 74.7f)
    lineTo(40.6f, 74.7f)
    lineTo(40.6f, 81.5f)
    lineTo(47.399998f, 81.5f)
    lineTo(47.399998f, 74.7f)
    lineTo(50.899998f, 74.7f)
    lineTo(50.899998f, 91.899994f)
    lineTo(47.399998f, 91.899994f)
    lineTo(47.399998f, 84.399994f)
    lineTo(40.6f, 84.399994f)
    lineTo(40.6f, 91.899994f)
    lineTo(37.1f, 91.899994f)
    close()
}
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

