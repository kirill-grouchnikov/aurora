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
class paintbrush : Painter() {
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
    moveTo(5.6f, 11.6f)
    lineTo(4.3999996f, 10.400001f)
    cubicTo(3.5999997f, 10.200001f, 2.3999996f, 10.3f, 1.6999996f, 11.400001f)
    cubicTo(0.89999956f, 12.500001f, 1.3999996f, 14.200001f, -4.7683716E-7f, 16.0f)
    cubicTo(-4.7683716E-7f, 16.0f, 3.4999995f, 16.0f, 4.7999997f, 14.7f)
    cubicTo(6.0f, 13.5f, 6.0f, 12.5f, 5.7999997f, 11.7f)
    lineTo(5.6f, 11.599999f)
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
    moveTo(5.8f, 8.1f)
    cubicTo(5.6000004f, 8.400001f, 5.3f, 8.8f, 5.1000004f, 9.1f)
    cubicTo(5.1000004f, 9.3f, 5.0000005f, 9.400001f, 4.9000006f, 9.5f)
    lineTo(6.4000006f, 11.0f)
    cubicTo(6.5000005f, 10.9f, 6.700001f, 10.8f, 6.8000007f, 10.7f)
    cubicTo(7.100001f, 10.5f, 7.5000005f, 10.3f, 7.8000007f, 10.0f)
    cubicTo(8.200001f, 10.0f, 8.400001f, 9.8f, 8.6f, 9.6f)
    lineTo(6.4000006f, 7.4000006f)
    cubicTo(6.200001f, 7.6000004f, 6.0000005f, 7.8000007f, 5.8000007f, 8.1f)
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
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.8f, 0.2f)
    cubicTo(15.5f, -0.10000001f, 15.1f, -0.10000001f, 14.8f, 0.1f)
    cubicTo(14.8f, 0.1f, 11.8f, 2.6f, 8.9f, 5.2f)
    cubicTo(8.5f, 5.6f, 8.2f, 5.8999996f, 7.7999997f, 6.2f)
    cubicTo(7.6f, 6.3999996f, 7.3999996f, 6.6f, 7.2f, 6.7f)
    lineTo(9.299999f, 8.799999f)
    cubicTo(9.499999f, 8.599999f, 9.699999f, 8.4f, 9.799999f, 8.099999f)
    cubicTo(10.099999f, 7.6999993f, 10.4f, 7.3999996f, 10.699999f, 6.9999995f)
    cubicTo(13.199999f, 3.9999995f, 15.799999f, 1.0999994f, 15.799999f, 1.0999994f)
    cubicTo(16.099998f, 0.89999944f, 16.099998f, 0.4999994f, 15.799999f, 0.19999945f)
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
            return 0.0
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
            return 15.999999046325684
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

