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
class ext_img : Painter() {
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
alpha *= 0.95f
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
generalPath?.run {
    moveTo(44.8f, 1.0f)
    lineTo(71.8f, 27.7f)
    lineTo(71.8f, 99.0f)
    lineTo(-0.19999695f, 99.0f)
    lineTo(-0.19999695f, 1.0f)
    lineTo(44.800003f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 196, 2, 255), 0.038f to Color(241, 200, 41, 255), 0.147f to Color(244, 210, 100, 255), 0.258f to Color(247, 220, 139, 255), 0.372f to Color(249, 229, 172, 255), 0.488f to Color(251, 236, 199, 255), 0.606f to Color(252, 243, 221, 255), 0.728f to Color(254, 249, 238, 255), 0.856f to Color(255, 253, 249, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(35.75f, 99.005f), end = Offset(35.75f, 0.99900055f), tileMode = TileMode.Clamp)
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
generalPath?.run {
    moveTo(44.8f, 1.0f)
    lineTo(71.8f, 27.7f)
    lineTo(71.8f, 99.0f)
    lineTo(-0.19999695f, 99.0f)
    lineTo(-0.19999695f, 1.0f)
    lineTo(44.800003f, 1.0f)
    close()
}
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
generalPath?.run {
    moveTo(44.8f, 1.0f)
    lineTo(71.8f, 27.7f)
    lineTo(71.8f, 99.0f)
    lineTo(-0.19999695f, 99.0f)
    lineTo(-0.19999695f, 1.0f)
    lineTo(44.800003f, 1.0f)
    close()
}
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
generalPath?.run {
    moveTo(11.1f, 92.1f)
    lineTo(11.1f, 72.2f)
    lineTo(15.1f, 72.2f)
    lineTo(15.1f, 92.0f)
    lineTo(11.1f, 92.0f)
    close()
    moveTo(19.0f, 92.1f)
    lineTo(19.0f, 72.2f)
    lineTo(25.0f, 72.2f)
    lineTo(28.6f, 85.7f)
    lineTo(32.2f, 72.2f)
    lineTo(38.3f, 72.2f)
    lineTo(38.3f, 92.0f)
    lineTo(34.5f, 92.0f)
    lineTo(34.5f, 76.4f)
    lineTo(30.5f, 92.0f)
    lineTo(26.6f, 92.0f)
    lineTo(22.6f, 76.4f)
    lineTo(22.6f, 92.0f)
    lineTo(19.0f, 92.0f)
    close()
    moveTo(51.7f, 84.799995f)
    lineTo(51.7f, 81.49999f)
    lineTo(60.4f, 81.49999f)
    lineTo(60.4f, 89.399994f)
    cubicTo(59.600002f, 90.2f, 58.300003f, 90.899994f, 56.7f, 91.49999f)
    cubicTo(55.100002f, 92.09999f, 53.5f, 92.399994f, 51.8f, 92.399994f)
    cubicTo(49.7f, 92.399994f, 47.899998f, 91.99999f, 46.3f, 91.09999f)
    cubicTo(44.7f, 90.19999f, 43.6f, 88.99999f, 42.8f, 87.399994f)
    cubicTo(42.0f, 85.799995f, 41.6f, 83.99999f, 41.6f, 82.09999f)
    cubicTo(41.6f, 79.99999f, 42.0f, 78.19999f, 42.899998f, 76.59999f)
    cubicTo(43.799995f, 74.99999f, 45.1f, 73.79999f, 46.699997f, 72.899994f)
    cubicTo(47.999996f, 72.2f, 49.6f, 71.899994f, 51.499996f, 71.899994f)
    cubicTo(53.999996f, 71.899994f, 55.899998f, 72.399994f, 57.299995f, 73.399994f)
    cubicTo(58.699997f, 74.399994f, 59.599995f, 75.899994f, 59.999996f, 77.7f)
    lineTo(55.999996f, 78.399994f)
    cubicTo(55.699997f, 77.399994f, 55.199997f, 76.7f, 54.399998f, 76.09999f)
    cubicTo(53.6f, 75.49999f, 52.6f, 75.29999f, 51.499996f, 75.29999f)
    cubicTo(49.699997f, 75.29999f, 48.299995f, 75.89999f, 47.299995f, 76.999985f)
    cubicTo(46.299995f, 78.09998f, 45.699997f, 79.79999f, 45.699997f, 81.89999f)
    cubicTo(45.699997f, 84.29999f, 46.199997f, 85.999985f, 47.299995f, 87.19999f)
    cubicTo(48.399994f, 88.39999f, 49.699997f, 88.99999f, 51.499996f, 88.99999f)
    cubicTo(52.299995f, 88.99999f, 53.199997f, 88.799995f, 54.099995f, 88.49999f)
    cubicTo(54.999996f, 88.19999f, 55.699993f, 87.799995f, 56.299995f, 87.299995f)
    lineTo(56.299995f, 84.799995f)
    lineTo(51.699997f, 84.799995f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.8f, 18.8f)
    cubicTo(23.4f, 18.8f, 13.299999f, 28.8f, 13.299999f, 41.1f)
    cubicTo(13.299999f, 53.399998f, 23.4f, 63.399998f, 35.8f, 63.399998f)
    cubicTo(48.199997f, 63.399998f, 58.3f, 53.399998f, 58.3f, 41.1f)
    cubicTo(58.3f, 28.8f, 48.199997f, 18.8f, 35.8f, 18.8f)
    close()
    moveTo(35.8f, 48.699997f)
    cubicTo(31.5f, 48.699997f, 28.099998f, 45.199997f, 28.099998f, 41.1f)
    cubicTo(28.099998f, 36.8f, 31.599998f, 33.5f, 35.8f, 33.5f)
    cubicTo(40.1f, 33.5f, 43.5f, 37.0f, 43.5f, 41.1f)
    cubicTo(43.5f, 45.3f, 40.0f, 48.699997f, 35.8f, 48.699997f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(164, 125, 3, 255), 0.533f to Color(222, 190, 0, 255), 0.639f to Color(207, 173, 4, 255), 1.0f to Color(160, 120, 2, 255), start = Offset(35.75f, 63.367f), end = Offset(35.75f, 18.819f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.8f, 38.3f)
    cubicTo(34.2f, 38.3f, 33.0f, 39.6f, 33.0f, 41.1f)
    cubicTo(33.0f, 42.699997f, 34.3f, 43.899998f, 35.8f, 43.899998f)
    cubicTo(37.399998f, 43.899998f, 38.6f, 42.6f, 38.6f, 41.1f)
    cubicTo(38.5f, 39.5f, 37.399998f, 38.3f, 35.8f, 38.3f)
    close()
    moveTo(35.8f, 42.1f)
    cubicTo(35.3f, 42.1f, 34.8f, 41.699997f, 34.8f, 41.1f)
    cubicTo(34.8f, 40.6f, 35.2f, 40.1f, 35.8f, 40.1f)
    cubicTo(36.3f, 40.1f, 36.8f, 40.5f, 36.8f, 41.1f)
    cubicTo(36.8f, 41.6f, 36.2f, 42.1f, 35.8f, 42.1f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.8f, 32.8f)
    cubicTo(31.099998f, 32.8f, 27.4f, 36.6f, 27.4f, 41.1f)
    cubicTo(27.4f, 45.8f, 31.199999f, 49.399998f, 35.8f, 49.399998f)
    cubicTo(40.5f, 49.399998f, 44.199997f, 45.6f, 44.199997f, 41.1f)
    cubicTo(44.199997f, 36.399998f, 40.499996f, 32.8f, 35.799995f, 32.8f)
    close()
    moveTo(35.8f, 48.0f)
    cubicTo(31.9f, 48.0f, 28.8f, 44.9f, 28.8f, 41.1f)
    cubicTo(28.8f, 37.299995f, 31.9f, 34.199997f, 35.8f, 34.199997f)
    cubicTo(39.699997f, 34.199997f, 42.8f, 37.299995f, 42.8f, 41.1f)
    cubicTo(42.8f, 44.9f, 39.7f, 48.0f, 35.8f, 48.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.95f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
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
    moveTo(45.2f, 1.0f)
    lineTo(72.2f, 27.7f)
    lineTo(45.199997f, 27.7f)
    lineTo(45.199997f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(255, 255, 255, 255), 0.234f to Color(255, 254, 251, 255), 0.369f to Color(254, 250, 241, 255), 0.481f to Color(253, 245, 228, 255), 0.579f to Color(252, 240, 210, 255), 0.669f to Color(250, 233, 188, 255), 0.752f to Color(249, 226, 162, 255), 0.831f to Color(247, 218, 131, 255), 0.905f to Color(244, 209, 93, 255), 0.975f to Color(241, 200, 39, 255), 1.0f to Color(239, 196, 2, 255), start = Offset(45.344f, 27.769997f), end = Offset(58.844f, 14.269997f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.2f, 27.7f)
    lineTo(45.199997f, 27.7f)
    lineTo(45.199997f, 1.0f)
    close()
}
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
generalPath?.run {
    moveTo(45.2f, 1.0f)
    lineTo(72.2f, 27.7f)
    lineTo(45.199997f, 27.7f)
    lineTo(45.199997f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 0.12800002098083496
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
            return 0.7410314083099365
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

