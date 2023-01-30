package org.pushingpixels.aurora.demo.svg.material

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
class devices_other_24px : Painter() {
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
    moveTo(3.0f, 6.0f)
    lineTo(21.0f, 6.0f)
    lineTo(21.0f, 4.0f)
    lineTo(3.0f, 4.0f)
    cubicTo(1.9f, 4.0f, 1.0f, 4.9f, 1.0f, 6.0f)
    lineTo(1.0f, 18.0f)
    cubicTo(1.0f, 19.1f, 1.9f, 20.0f, 3.0f, 20.0f)
    lineTo(7.0f, 20.0f)
    lineTo(7.0f, 18.0f)
    lineTo(3.0f, 18.0f)
    lineTo(3.0f, 6.0f)
    close()
    moveTo(13.0f, 12.0f)
    lineTo(9.0f, 12.0f)
    lineTo(9.0f, 13.78f)
    cubicTo(8.39f, 14.33f, 8.0f, 15.11f, 8.0f, 16.0f)
    cubicTo(8.0f, 16.89f, 8.39f, 17.67f, 9.0f, 18.22f)
    lineTo(9.0f, 20.0f)
    lineTo(13.0f, 20.0f)
    lineTo(13.0f, 18.22f)
    cubicTo(13.61f, 17.67f, 14.0f, 16.88f, 14.0f, 15.999999f)
    cubicTo(14.0f, 15.119999f, 13.61f, 14.329999f, 13.0f, 13.779999f)
    lineTo(13.0f, 12.0f)
    close()
    moveTo(11.0f, 17.5f)
    cubicTo(10.17f, 17.5f, 9.5f, 16.83f, 9.5f, 16.0f)
    cubicTo(9.5f, 15.17f, 10.17f, 14.5f, 11.0f, 14.5f)
    cubicTo(11.83f, 14.5f, 12.5f, 15.17f, 12.5f, 16.0f)
    cubicTo(12.5f, 16.83f, 11.83f, 17.5f, 11.0f, 17.5f)
    close()
    moveTo(22.0f, 8.0f)
    lineTo(16.0f, 8.0f)
    cubicTo(15.5f, 8.0f, 15.0f, 8.5f, 15.0f, 9.0f)
    lineTo(15.0f, 19.0f)
    cubicTo(15.0f, 19.5f, 15.5f, 20.0f, 16.0f, 20.0f)
    lineTo(22.0f, 20.0f)
    cubicTo(22.5f, 20.0f, 23.0f, 19.5f, 23.0f, 19.0f)
    lineTo(23.0f, 9.0f)
    cubicTo(23.0f, 8.5f, 22.5f, 8.0f, 22.0f, 8.0f)
    close()
    moveTo(21.0f, 18.0f)
    lineTo(17.0f, 18.0f)
    lineTo(17.0f, 10.0f)
    lineTo(21.0f, 10.0f)
    lineTo(21.0f, 18.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
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
            return 1.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 4.0
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 22.0
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

