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
class ext_ttf : Painter() {
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
    lineTo(71.9f, 27.7f)
    lineTo(71.9f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(190, 192, 219, 255), 0.139f to Color(206, 205, 230, 255), 0.359f to Color(225, 220, 243, 255), 0.617f to Color(239, 230, 251, 255), 1.0f to Color(245, 245, 255, 255), start = Offset(36.0f, 98.995f), end = Offset(36.0f, 1.0f), tileMode = TileMode.Clamp)
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
    lineTo(71.9f, 27.7f)
    lineTo(71.9f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(113, 135, 161, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(71.9f, 27.7f)
    lineTo(71.9f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
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
    moveTo(44.7f, 1.0f)
    lineTo(71.6f, 27.7f)
    lineTo(44.7f, 27.7f)
    lineTo(44.7f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 245, 255, 255), 0.35f to Color(250, 241, 251, 255), 0.532f to Color(237, 231, 244, 255), 0.675f to Color(221, 219, 233, 255), 0.799f to Color(199, 201, 218, 255), 0.908f to Color(173, 179, 199, 255), 1.0f to Color(146, 145, 176, 255), start = Offset(44.754f, 27.765999f), end = Offset(58.198f, 14.322998f), tileMode = TileMode.Clamp)
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
    moveTo(44.7f, 1.0f)
    lineTo(71.6f, 27.7f)
    lineTo(44.7f, 27.7f)
    lineTo(44.7f, 1.0f)
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
    moveTo(44.7f, 1.0f)
    lineTo(71.6f, 27.7f)
    lineTo(44.7f, 27.7f)
    lineTo(44.7f, 1.0f)
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
    moveTo(41.0f, 22.2f)
    lineTo(41.0f, 30.0f)
    lineTo(40.2f, 30.0f)
    cubicTo(39.7f, 28.2f, 39.2f, 26.9f, 38.7f, 26.1f)
    cubicTo(38.100002f, 25.300001f, 37.4f, 24.7f, 36.4f, 24.2f)
    cubicTo(35.9f, 23.900002f, 34.9f, 23.800001f, 33.600002f, 23.800001f)
    lineTo(31.400002f, 23.800001f)
    lineTo(31.400002f, 46.0f)
    cubicTo(31.400002f, 47.5f, 31.500002f, 48.4f, 31.600002f, 48.8f)
    cubicTo(31.700003f, 49.199997f, 32.100002f, 49.5f, 32.600002f, 49.8f)
    cubicTo(33.100002f, 50.1f, 33.7f, 50.2f, 34.600002f, 50.2f)
    lineTo(35.600002f, 50.2f)
    lineTo(35.600002f, 51.0f)
    lineTo(20.4f, 51.0f)
    lineTo(20.4f, 50.2f)
    lineTo(21.4f, 50.2f)
    cubicTo(22.199999f, 50.2f, 22.9f, 50.100002f, 23.4f, 49.8f)
    cubicTo(23.8f, 49.6f, 24.1f, 49.3f, 24.3f, 48.8f)
    cubicTo(24.5f, 48.5f, 24.5f, 47.6f, 24.5f, 46.1f)
    lineTo(24.5f, 23.9f)
    lineTo(22.4f, 23.9f)
    cubicTo(20.4f, 23.9f, 19.0f, 24.3f, 18.099998f, 25.1f)
    cubicTo(16.899998f, 26.2f, 16.099998f, 27.9f, 15.699999f, 30.0f)
    lineTo(14.899999f, 30.0f)
    lineTo(14.899999f, 22.2f)
    lineTo(41.0f, 22.2f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 145, 161, 255))
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
    moveTo(54.1f, 30.8f)
    lineTo(54.1f, 38.6f)
    lineTo(53.3f, 38.6f)
    cubicTo(52.8f, 36.8f, 52.3f, 35.5f, 51.8f, 34.699997f)
    cubicTo(51.2f, 33.899998f, 50.5f, 33.299995f, 49.5f, 32.799995f)
    cubicTo(49.0f, 32.499996f, 48.0f, 32.399994f, 46.7f, 32.399994f)
    lineTo(44.5f, 32.399994f)
    lineTo(44.5f, 54.599995f)
    cubicTo(44.5f, 56.099995f, 44.6f, 56.999996f, 44.7f, 57.399994f)
    cubicTo(44.9f, 57.799995f, 45.2f, 58.099995f, 45.7f, 58.399994f)
    cubicTo(46.2f, 58.699993f, 46.8f, 58.799995f, 47.7f, 58.799995f)
    lineTo(48.7f, 58.799995f)
    lineTo(48.7f, 59.599995f)
    lineTo(33.5f, 59.599995f)
    lineTo(33.5f, 58.799995f)
    lineTo(34.5f, 58.799995f)
    cubicTo(35.3f, 58.799995f, 36.0f, 58.699997f, 36.5f, 58.399994f)
    cubicTo(36.9f, 58.199993f, 37.2f, 57.899994f, 37.4f, 57.399994f)
    cubicTo(37.600002f, 57.099995f, 37.600002f, 56.199993f, 37.600002f, 54.699993f)
    lineTo(37.600002f, 32.4f)
    lineTo(35.500004f, 32.4f)
    cubicTo(33.500004f, 32.4f, 32.100002f, 32.800003f, 31.200005f, 33.600002f)
    cubicTo(30.000004f, 34.7f, 29.200005f, 36.4f, 28.800005f, 38.500004f)
    lineTo(28.0f, 38.500004f)
    lineTo(28.0f, 30.700005f)
    lineTo(54.1f, 30.700005f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(54, 69, 77, 255))
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
generalPath?.run {
    moveTo(16.9f, 92.1f)
    lineTo(16.9f, 75.6f)
    lineTo(11.0f, 75.6f)
    lineTo(11.0f, 72.2f)
    lineTo(26.9f, 72.2f)
    lineTo(26.9f, 75.6f)
    lineTo(21.0f, 75.6f)
    lineTo(21.0f, 92.1f)
    lineTo(16.9f, 92.1f)
    close()
    moveTo(33.9f, 92.1f)
    lineTo(33.9f, 75.6f)
    lineTo(28.0f, 75.6f)
    lineTo(28.0f, 72.2f)
    lineTo(43.9f, 72.2f)
    lineTo(43.9f, 75.6f)
    lineTo(38.0f, 75.6f)
    lineTo(38.0f, 92.1f)
    lineTo(33.9f, 92.1f)
    close()
    moveTo(46.5f, 92.1f)
    lineTo(46.5f, 72.2f)
    lineTo(60.2f, 72.2f)
    lineTo(60.2f, 75.6f)
    lineTo(50.5f, 75.6f)
    lineTo(50.5f, 80.299995f)
    lineTo(58.8f, 80.299995f)
    lineTo(58.8f, 83.7f)
    lineTo(50.5f, 83.7f)
    lineTo(50.5f, 92.1f)
    lineTo(46.5f, 92.1f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 98, 123, 255))
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
            return 0.13099999725818634
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
            return 0.7379999756813049
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

