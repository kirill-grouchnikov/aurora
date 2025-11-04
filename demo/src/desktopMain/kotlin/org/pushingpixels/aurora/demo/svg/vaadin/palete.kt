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
class palete : Painter() {
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
    moveTo(8.25f, 0.0f)
    cubicTo(1.8699999f, 0.0f, -0.85999966f, 7.38f, 0.23999977f, 9.92f)
    cubicTo(1.0599997f, 11.81f, 2.8599997f, 10.0f, 3.5799997f, 10.92f)
    cubicTo(5.4599996f, 13.38f, 1.4699998f, 14.73f, 3.6699996f, 15.6f)
    cubicTo(6.2599993f, 16.66f, 16.0f, 16.0f, 16.0f, 7.0700006f)
    cubicTo(16.0f, 4.3800006f, 14.66f, 4.7683716E-7f, 8.25f, 4.7683716E-7f)
    close()
    moveTo(4.47f, 9.0f)
    cubicTo(3.6549997f, 8.983f, 2.9999998f, 8.318f, 2.9999998f, 7.5f)
    cubicTo(2.9999998f, 6.672f, 3.6719997f, 6.0f, 4.5f, 6.0f)
    cubicTo(5.328f, 6.0f, 6.0f, 6.671f, 6.0f, 7.5f)
    cubicTo(6.0f, 7.5f, 6.0f, 7.5f, 6.0f, 7.5f)
    cubicTo(6.0f, 8.328f, 5.328f, 9.0f, 4.5f, 9.0f)
    cubicTo(4.489f, 9.0f, 4.479f, 9.0f, 4.468f, 9.0f)
    close()
    moveTo(6.0f, 3.5f)
    cubicTo(6.0f, 2.672f, 6.672f, 2.0f, 7.5f, 2.0f)
    cubicTo(8.328f, 2.0f, 9.0f, 2.672f, 9.0f, 3.5f)
    cubicTo(9.0f, 4.328f, 8.328f, 5.0f, 7.5f, 5.0f)
    cubicTo(7.489f, 5.0f, 7.479f, 5.0f, 7.468f, 5.0f)
    cubicTo(6.654f, 4.983f, 6.0f, 4.318f, 6.0f, 3.5f)
    cubicTo(6.0f, 3.5f, 6.0f, 3.5f, 6.0f, 3.5f)
    close()
    moveTo(8.47f, 14.0f)
    cubicTo(7.655f, 13.983f, 7.0f, 13.318f, 7.0f, 12.5f)
    cubicTo(7.0f, 11.672f, 7.672f, 11.0f, 8.5f, 11.0f)
    cubicTo(9.328f, 11.0f, 10.0f, 11.671f, 10.0f, 12.5f)
    cubicTo(10.0f, 12.5f, 10.0f, 12.5f, 10.0f, 12.5f)
    cubicTo(10.0f, 13.328f, 9.328f, 14.0f, 8.5f, 14.0f)
    cubicTo(8.489f, 14.0f, 8.479f, 14.0f, 8.468f, 14.0f)
    close()
    moveTo(12.47f, 11.0f)
    cubicTo(11.655001f, 10.983f, 11.0f, 10.318f, 11.0f, 9.5f)
    cubicTo(11.0f, 8.672f, 11.672f, 8.0f, 12.5f, 8.0f)
    cubicTo(13.328f, 8.0f, 14.0f, 8.671f, 14.0f, 9.5f)
    cubicTo(14.0f, 9.5f, 14.0f, 9.5f, 14.0f, 9.5f)
    cubicTo(14.0f, 10.328f, 13.328f, 11.0f, 12.5f, 11.0f)
    cubicTo(12.489f, 11.0f, 12.479f, 11.0f, 12.468f, 11.0f)
    close()
    moveTo(12.47f, 6.0f)
    cubicTo(11.655001f, 5.983f, 11.0f, 5.318f, 11.0f, 4.5f)
    cubicTo(11.0f, 3.672f, 11.672f, 3.0f, 12.5f, 3.0f)
    cubicTo(13.328f, 3.0f, 14.0f, 3.671f, 14.0f, 4.5f)
    cubicTo(14.0f, 4.5f, 14.0f, 4.5f, 14.0f, 4.5f)
    cubicTo(14.0f, 5.328f, 13.328f, 6.0f, 12.5f, 6.0f)
    cubicTo(12.489f, 6.0f, 12.479f, 6.0f, 12.468f, 6.0f)
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
            return 0.002519027329981327
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
            return 15.997481346130371
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 15.980156898498535
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

