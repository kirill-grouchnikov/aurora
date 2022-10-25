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
class ext_7z : Painter() {
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
alpha *= 0.98f
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
    moveTo(44.9f, 0.9f)
    lineTo(71.7f, 27.699999f)
    lineTo(71.7f, 99.2f)
    lineTo(0.2f, 99.2f)
    lineTo(0.2f, 0.9f)
    lineTo(44.9f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 196, 2, 255), 0.038f to Color(241, 200, 41, 255), 0.147f to Color(244, 210, 100, 255), 0.258f to Color(247, 220, 139, 255), 0.372f to Color(249, 229, 172, 255), 0.488f to Color(251, 236, 199, 255), 0.606f to Color(252, 243, 221, 255), 0.728f to Color(254, 249, 238, 255), 0.856f to Color(255, 253, 249, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.12f), end = Offset(36.0f, 0.875f), tileMode = TileMode.Clamp)
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
    moveTo(44.9f, 0.9f)
    lineTo(71.7f, 27.699999f)
    lineTo(71.7f, 99.2f)
    lineTo(0.2f, 99.2f)
    lineTo(0.2f, 0.9f)
    lineTo(44.9f, 0.9f)
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
    moveTo(44.9f, 0.9f)
    lineTo(71.7f, 27.699999f)
    lineTo(71.7f, 99.2f)
    lineTo(0.2f, 99.2f)
    lineTo(0.2f, 0.9f)
    lineTo(44.9f, 0.9f)
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
    moveTo(20.6f, 75.9f)
    lineTo(20.6f, 72.4f)
    lineTo(33.6f, 72.4f)
    lineTo(33.6f, 75.200005f)
    cubicTo(32.5f, 76.3f, 31.399998f, 77.8f, 30.3f, 79.8f)
    cubicTo(29.199999f, 81.8f, 28.3f, 83.9f, 27.8f, 86.100006f)
    cubicTo(27.199999f, 88.3f, 26.9f, 90.3f, 26.9f, 92.100006f)
    lineTo(23.199999f, 92.100006f)
    cubicTo(23.3f, 89.3f, 23.8f, 86.50001f, 24.9f, 83.700005f)
    cubicTo(26.0f, 80.8f, 27.4f, 78.3f, 29.2f, 76.00001f)
    lineTo(20.6f, 76.00001f)
    close()
    moveTo(35.2f, 91.9f)
    lineTo(35.2f, 88.3f)
    lineTo(45.7f, 75.4f)
    lineTo(36.4f, 75.4f)
    lineTo(36.4f, 72.0f)
    lineTo(51.0f, 72.0f)
    lineTo(51.0f, 75.1f)
    lineTo(40.1f, 88.6f)
    lineTo(51.399998f, 88.6f)
    lineTo(51.399998f, 92.0f)
    lineTo(35.2f, 92.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 120, 2, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.98f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.9f, 0.9f)
    lineTo(71.7f, 27.699999f)
    lineTo(44.9f, 27.699999f)
    lineTo(44.9f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(255, 255, 255, 255), 0.234f to Color(255, 254, 251, 255), 0.369f to Color(254, 250, 241, 255), 0.481f to Color(253, 245, 228, 255), 0.579f to Color(252, 240, 210, 255), 0.669f to Color(250, 233, 188, 255), 0.752f to Color(249, 226, 162, 255), 0.831f to Color(247, 218, 131, 255), 0.905f to Color(244, 209, 93, 255), 0.975f to Color(241, 200, 39, 255), 1.0f to Color(239, 196, 2, 255), start = Offset(44.942f, 27.674004f), end = Offset(58.348f, 14.266998f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.9f, 0.9f)
    lineTo(71.7f, 27.699999f)
    lineTo(44.9f, 27.699999f)
    lineTo(44.9f, 0.9f)
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
    moveTo(44.9f, 0.9f)
    lineTo(71.7f, 27.699999f)
    lineTo(44.9f, 27.699999f)
    lineTo(44.9f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.98f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.0f, 15.8f)
    lineTo(29.6f, 15.8f)
    lineTo(29.6f, 10.8f)
    lineTo(38.0f, 10.8f)
    lineTo(38.0f, 15.8f)
    close()
    moveTo(38.0f, 24.7f)
    lineTo(29.6f, 24.7f)
    lineTo(29.6f, 29.7f)
    lineTo(38.0f, 29.7f)
    lineTo(38.0f, 24.7f)
    close()
    moveTo(38.0f, 3.9000015f)
    lineTo(29.6f, 3.9000015f)
    lineTo(29.6f, 8.900002f)
    lineTo(38.0f, 8.900002f)
    lineTo(38.0f, 3.9000015f)
    close()
    moveTo(38.0f, 17.7f)
    lineTo(29.6f, 17.7f)
    lineTo(29.6f, 22.7f)
    lineTo(38.0f, 22.7f)
    lineTo(38.0f, 17.7f)
    close()
    moveTo(38.0f, 31.6f)
    lineTo(29.6f, 31.6f)
    lineTo(29.6f, 36.6f)
    lineTo(38.0f, 36.6f)
    lineTo(38.0f, 31.599998f)
    close()
    moveTo(38.7f, 56.2f)
    cubicTo(38.7f, 59.0f, 36.4f, 61.3f, 33.600002f, 61.3f)
    cubicTo(30.800003f, 61.3f, 28.500002f, 59.0f, 28.500002f, 56.2f)
    lineTo(28.500002f, 55.7f)
    lineTo(30.200003f, 41.1f)
    cubicTo(30.200003f, 39.199997f, 31.700003f, 37.699997f, 33.600002f, 37.699997f)
    cubicTo(35.4f, 37.699997f, 36.9f, 39.199997f, 37.000004f, 40.999996f)
    lineTo(38.600002f, 55.399994f)
    cubicTo(38.7f, 55.799995f, 38.7f, 55.999992f, 38.7f, 56.199993f)
    close()
    moveTo(37.100002f, 56.100002f)
    cubicTo(37.100002f, 54.2f, 35.500004f, 52.600002f, 33.600002f, 52.600002f)
    cubicTo(31.7f, 52.600002f, 30.100002f, 54.2f, 30.100002f, 56.100002f)
    cubicTo(30.100002f, 58.000004f, 31.700003f, 59.600002f, 33.600002f, 59.600002f)
    cubicTo(35.600002f, 59.500004f, 37.100002f, 57.9f, 37.100002f, 56.100002f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(164, 125, 3, 255), 0.533f to Color(222, 190, 0, 255), 0.639f to Color(207, 173, 4, 255), 1.0f to Color(160, 120, 2, 255), start = Offset(33.617f, 61.311f), end = Offset(33.617f, 3.8519974f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.98f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.5f, 41.6f)
    lineTo(30.2f, 37.1f)
    lineTo(30.2f, 34.8f)
    lineTo(32.5f, 32.6f)
    lineTo(34.7f, 32.6f)
    lineTo(37.0f, 34.8f)
    lineTo(37.0f, 37.1f)
    lineTo(34.7f, 41.6f)
    lineTo(32.5f, 41.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
            return 0.13199999928474426
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
            return 0.73499995470047
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

