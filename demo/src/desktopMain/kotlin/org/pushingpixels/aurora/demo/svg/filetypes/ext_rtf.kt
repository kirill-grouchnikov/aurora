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
class ext_rtf : Painter() {
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
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.047f), end = Offset(36.0f, 1.050003f), tileMode = TileMode.Clamp)
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
    moveTo(11.6f, 91.1f)
    lineTo(11.6f, 71.3f)
    lineTo(20.1f, 71.3f)
    cubicTo(22.2f, 71.3f, 23.800001f, 71.5f, 24.8f, 71.8f)
    cubicTo(25.8f, 72.200005f, 26.5f, 72.8f, 27.099998f, 73.700005f)
    cubicTo(27.699999f, 74.600006f, 27.999998f, 75.700005f, 27.999998f, 76.8f)
    cubicTo(27.999998f, 78.3f, 27.599998f, 79.5f, 26.699999f, 80.5f)
    cubicTo(25.8f, 81.5f, 24.499998f, 82.1f, 22.699999f, 82.3f)
    cubicTo(23.599998f, 82.8f, 24.3f, 83.4f, 24.9f, 84.0f)
    cubicTo(25.5f, 84.6f, 26.199999f, 85.7f, 27.199999f, 87.2f)
    lineTo(29.599998f, 91.1f)
    lineTo(24.8f, 91.1f)
    lineTo(21.9f, 86.799995f)
    cubicTo(20.9f, 85.299995f, 20.1f, 84.299995f, 19.8f, 83.899994f)
    cubicTo(19.499998f, 83.49999f, 19.0f, 83.2f, 18.599998f, 83.09999f)
    cubicTo(18.199999f, 82.99999f, 17.499998f, 82.899994f, 16.599998f, 82.899994f)
    lineTo(15.799998f, 82.899994f)
    lineTo(15.799998f, 91.2f)
    lineTo(11.599998f, 91.2f)
    close()
    moveTo(15.6f, 79.7f)
    lineTo(18.6f, 79.7f)
    cubicTo(20.5f, 79.7f, 21.800001f, 79.6f, 22.2f, 79.5f)
    cubicTo(22.7f, 79.3f, 23.1f, 79.1f, 23.300001f, 78.7f)
    cubicTo(23.6f, 78.299995f, 23.7f, 77.799995f, 23.7f, 77.2f)
    cubicTo(23.7f, 76.5f, 23.5f, 76.0f, 23.2f, 75.6f)
    cubicTo(22.800001f, 75.2f, 22.300001f, 74.9f, 21.7f, 74.799995f)
    cubicTo(21.400002f, 74.799995f, 20.400002f, 74.7f, 18.7f, 74.7f)
    lineTo(15.500001f, 74.7f)
    lineTo(15.500001f, 79.7f)
    close()
    moveTo(36.300003f, 91.1f)
    lineTo(36.300003f, 74.6f)
    lineTo(30.400003f, 74.6f)
    lineTo(30.400003f, 71.2f)
    lineTo(46.300003f, 71.2f)
    lineTo(46.300003f, 74.6f)
    lineTo(40.4f, 74.6f)
    lineTo(40.4f, 91.1f)
    lineTo(36.300003f, 91.1f)
    close()
    moveTo(48.9f, 91.1f)
    lineTo(48.9f, 71.3f)
    lineTo(62.600002f, 71.3f)
    lineTo(62.600002f, 74.700005f)
    lineTo(53.0f, 74.700005f)
    lineTo(53.0f, 79.4f)
    lineTo(61.4f, 79.4f)
    lineTo(61.4f, 82.8f)
    lineTo(53.0f, 82.8f)
    lineTo(53.0f, 91.200005f)
    lineTo(48.9f, 91.200005f)
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
    moveTo(11.8f, 33.7f)
    lineTo(60.399998f, 33.7f)
    lineTo(60.399998f, 37.8f)
    lineTo(11.8f, 37.8f)
    lineTo(11.8f, 33.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.84f, 35.754997f), end = Offset(60.452f, 35.754997f), tileMode = TileMode.Clamp)
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
    moveTo(11.8f, 57.8f)
    lineTo(60.399998f, 57.8f)
    lineTo(60.399998f, 61.899998f)
    lineTo(11.8f, 61.899998f)
    lineTo(11.8f, 57.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.84f, 59.809f), end = Offset(60.452f, 59.809f), tileMode = TileMode.Clamp)
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
    moveTo(11.8f, 46.0f)
    lineTo(60.399998f, 46.0f)
    lineTo(60.399998f, 50.1f)
    lineTo(11.8f, 50.1f)
    lineTo(11.8f, 46.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.84f, 48.069f), end = Offset(60.452f, 48.069f), tileMode = TileMode.Clamp)
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
    moveTo(11.8f, 21.6f)
    lineTo(60.399998f, 21.6f)
    lineTo(60.399998f, 25.7f)
    lineTo(11.8f, 25.7f)
    lineTo(11.8f, 21.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 145, 161, 255), 1.0f to Color(202, 213, 219, 255), start = Offset(11.84f, 23.580002f), end = Offset(60.452f, 23.580002f), tileMode = TileMode.Clamp)
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
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.068f, 27.845001f), end = Offset(58.568f, 14.345001f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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

