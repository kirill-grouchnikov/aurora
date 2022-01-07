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
class ext_kt : Painter() {
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
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.3f, 27.7f)
generalPath!!.lineTo(72.3f, 99.0f)
generalPath!!.lineTo(0.1f, 99.0f)
generalPath!!.lineTo(0.1f, 1.0f)
generalPath!!.lineTo(45.199997f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.2f, 98.986f), end = Offset(36.2f, 0.99900055f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.3f, 27.7f)
generalPath!!.lineTo(72.3f, 99.0f)
generalPath!!.lineTo(0.1f, 99.0f)
generalPath!!.lineTo(0.1f, 1.0f)
generalPath!!.lineTo(45.199997f, 1.0f)
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.3f, 27.7f)
generalPath!!.lineTo(72.3f, 99.0f)
generalPath!!.lineTo(0.1f, 99.0f)
generalPath!!.lineTo(0.1f, 1.0f)
generalPath!!.lineTo(45.199997f, 1.0f)
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
generalPath!!.moveTo(20.4f, 90.1f)
generalPath!!.lineTo(20.4f, 70.2f)
generalPath!!.lineTo(24.5f, 70.2f)
generalPath!!.lineTo(24.5f, 79.0f)
generalPath!!.lineTo(32.7f, 70.2f)
generalPath!!.lineTo(38.2f, 70.2f)
generalPath!!.lineTo(30.5f, 78.0f)
generalPath!!.lineTo(38.5f, 90.1f)
generalPath!!.lineTo(33.2f, 90.1f)
generalPath!!.lineTo(27.7f, 80.799995f)
generalPath!!.lineTo(24.400002f, 84.1f)
generalPath!!.lineTo(24.400002f, 90.1f)
generalPath!!.lineTo(20.400002f, 90.1f)
generalPath!!.close()
generalPath!!.moveTo(45.1f, 90.1f)
generalPath!!.lineTo(45.1f, 73.6f)
generalPath!!.lineTo(39.1f, 73.6f)
generalPath!!.lineTo(39.1f, 70.2f)
generalPath!!.lineTo(55.1f, 70.2f)
generalPath!!.lineTo(55.1f, 73.6f)
generalPath!!.lineTo(49.1f, 73.6f)
generalPath!!.lineTo(49.1f, 90.1f)
generalPath!!.lineTo(45.1f, 90.1f)
generalPath!!.close()
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
generalPath!!.moveTo(31.8f, 32.0f)
generalPath!!.cubicTo(31.699999f, 32.1f, 31.699999f, 32.1f, 31.8f, 32.0f)
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(31.747f, 32.065002f), end = Offset(31.747f, 32.014f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.9f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.0f, 52.2f)
generalPath!!.lineTo(19.0f, 40.1f)
generalPath!!.cubicTo(19.0f, 39.3f, 19.2f, 38.899998f, 20.0f, 38.5f)
generalPath!!.cubicTo(29.1f, 33.5f, 38.2f, 28.4f, 47.2f, 23.4f)
generalPath!!.cubicTo(48.5f, 22.699999f, 49.8f, 22.0f, 51.3f, 22.0f)
generalPath!!.cubicTo(53.899998f, 22.1f, 56.0f, 23.6f, 56.7f, 26.0f)
generalPath!!.cubicTo(57.5f, 28.4f, 56.7f, 31.2f, 54.600002f, 32.6f)
generalPath!!.cubicTo(52.300003f, 34.0f, 49.7f, 35.399998f, 47.300003f, 36.8f)
generalPath!!.cubicTo(38.200005f, 41.8f, 29.100002f, 46.9f, 19.900003f, 51.9f)
generalPath!!.cubicTo(19.700003f, 52.0f, 19.400003f, 52.100002f, 19.000004f, 52.2f)
generalPath!!.close()
generalPath!!.moveTo(33.4f, 47.7f)
generalPath!!.cubicTo(33.7f, 47.5f, 34.0f, 47.4f, 34.2f, 47.100002f)
generalPath!!.cubicTo(37.2f, 45.4f, 40.2f, 43.9f, 43.1f, 42.2f)
generalPath!!.cubicTo(43.8f, 41.9f, 44.199997f, 41.9f, 44.8f, 42.3f)
generalPath!!.cubicTo(48.2f, 45.399998f, 51.7f, 48.399998f, 55.0f, 51.5f)
generalPath!!.cubicTo(56.9f, 53.2f, 57.4f, 55.4f, 56.7f, 57.7f)
generalPath!!.cubicTo(56.0f, 60.0f, 54.4f, 61.4f, 51.9f, 61.7f)
generalPath!!.cubicTo(50.300003f, 61.9f, 48.800003f, 61.5f, 47.600002f, 60.5f)
generalPath!!.cubicTo(42.9f, 56.3f, 38.300003f, 52.1f, 33.5f, 47.7f)
generalPath!!.cubicTo(33.6f, 47.9f, 33.6f, 47.8f, 33.4f, 47.7f)
generalPath!!.close()
generalPath!!.moveTo(19.0f, 35.8f)
generalPath!!.cubicTo(19.0f, 32.8f, 18.8f, 29.9f, 19.1f, 27.0f)
generalPath!!.cubicTo(19.4f, 23.8f, 22.7f, 21.8f, 26.1f, 22.1f)
generalPath!!.cubicTo(29.0f, 22.4f, 31.400002f, 25.4f, 31.2f, 28.400002f)
generalPath!!.cubicTo(31.2f, 28.7f, 31.0f, 29.2f, 30.6f, 29.300001f)
generalPath!!.lineTo(19.2f, 35.600002f)
generalPath!!.cubicTo(19.300001f, 35.800003f, 19.2f, 35.7f, 19.0f, 35.800003f)
generalPath!!.close()
generalPath!!.moveTo(31.2f, 48.9f)
generalPath!!.cubicTo(31.2f, 51.7f, 31.300001f, 54.100002f, 31.2f, 56.7f)
generalPath!!.cubicTo(31.0f, 59.7f, 28.2f, 62.0f, 25.2f, 62.0f)
generalPath!!.cubicTo(22.300001f, 62.0f, 19.5f, 59.8f, 19.0f, 56.9f)
generalPath!!.cubicTo(18.9f, 56.0f, 19.0f, 55.5f, 19.9f, 55.0f)
generalPath!!.cubicTo(23.3f, 53.1f, 26.8f, 51.2f, 30.2f, 49.3f)
generalPath!!.cubicTo(30.400002f, 49.2f, 30.800001f, 49.1f, 31.2f, 48.899998f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(38.0f, 62.0f), end = Offset(38.0f, 22.0f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.3f, 27.7f)
generalPath!!.lineTo(45.2f, 27.7f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.324f, 27.816002f), end = Offset(58.871f, 14.268997f), tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.3f, 27.7f)
generalPath!!.lineTo(45.2f, 27.7f)
generalPath!!.lineTo(45.2f, 1.0f)
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
generalPath!!.moveTo(45.2f, 1.0f)
generalPath!!.lineTo(72.3f, 27.7f)
generalPath!!.lineTo(45.2f, 27.7f)
generalPath!!.lineTo(45.2f, 1.0f)
generalPath!!.close()
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
            return 0.7420000433921814
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

