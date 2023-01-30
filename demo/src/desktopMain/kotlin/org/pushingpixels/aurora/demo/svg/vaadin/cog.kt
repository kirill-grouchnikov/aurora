package org.pushingpixels.aurora.demo.svg.vaadin

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
class cog : Painter() {
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
    moveTo(16.0f, 9.0f)
    lineTo(16.0f, 7.0f)
    lineTo(14.3f, 6.4f)
    cubicTo(14.1f, 5.8f, 13.900001f, 5.2f, 13.6f, 4.6000004f)
    lineTo(14.400001f, 3.0000005f)
    lineTo(13.000001f, 1.6000005f)
    lineTo(11.400001f, 2.4000006f)
    cubicTo(10.900001f, 2.1000006f, 10.3f, 1.8000005f, 9.6f, 1.7000005f)
    lineTo(9.0f, 4.7683716E-7f)
    lineTo(7.0f, 4.7683716E-7f)
    lineTo(6.4f, 1.7000005f)
    cubicTo(5.8f, 1.9000006f, 5.2f, 2.1000006f, 4.7f, 2.4000006f)
    lineTo(3.1f, 1.6000006f)
    lineTo(1.5999999f, 3.1000006f)
    lineTo(2.3999999f, 4.700001f)
    cubicTo(2.1f, 5.200001f, 1.8999999f, 5.8000007f, 1.6999998f, 6.4000006f)
    lineTo(-2.3841858E-7f, 7.0000005f)
    lineTo(-2.3841858E-7f, 9.0f)
    lineTo(1.6999998f, 9.6f)
    cubicTo(1.8999999f, 10.200001f, 2.1f, 10.8f, 2.3999999f, 11.400001f)
    lineTo(1.5999999f, 13.000001f)
    lineTo(3.0f, 14.400001f)
    lineTo(4.6f, 13.6f)
    cubicTo(5.1f, 13.900001f, 5.7f, 14.200001f, 6.3999996f, 14.3f)
    lineTo(6.9999995f, 16.0f)
    lineTo(9.0f, 16.0f)
    lineTo(9.6f, 14.3f)
    cubicTo(10.200001f, 14.1f, 10.8f, 13.900001f, 11.400001f, 13.6f)
    lineTo(13.000001f, 14.400001f)
    lineTo(14.400001f, 13.000001f)
    lineTo(13.6f, 11.400001f)
    cubicTo(13.900001f, 10.900001f, 14.200001f, 10.3f, 14.3f, 9.6f)
    lineTo(16.0f, 9.0f)
    close()
    moveTo(8.0f, 12.0f)
    cubicTo(5.8f, 12.0f, 4.0f, 10.2f, 4.0f, 8.0f)
    cubicTo(4.0f, 5.8f, 5.8f, 4.0f, 8.0f, 4.0f)
    cubicTo(10.2f, 4.0f, 12.0f, 5.8f, 12.0f, 8.0f)
    cubicTo(12.0f, 10.2f, 10.2f, 12.0f, 8.0f, 12.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(68, 68, 68, 255))
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
    moveTo(10.6f, 7.9f)
    cubicTo(10.6f, 9.281f, 9.481001f, 10.4f, 8.1f, 10.4f)
    cubicTo(6.719f, 10.4f, 5.6000004f, 9.281f, 5.6000004f, 7.8999996f)
    cubicTo(5.6000004f, 6.5189996f, 6.7190003f, 5.3999996f, 8.1f, 5.3999996f)
    cubicTo(9.481001f, 5.3999996f, 10.6f, 6.5189996f, 10.6f, 7.8999996f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(68, 68, 68, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 0.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 4.76837158203125E-7
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 16.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 16.0
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

