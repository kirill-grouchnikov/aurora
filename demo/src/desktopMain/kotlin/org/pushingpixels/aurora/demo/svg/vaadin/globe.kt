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
class globe : Painter() {
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
    moveTo(8.0f, 0.0f)
    cubicTo(3.6f, 0.0f, 0.0f, 3.6f, 0.0f, 8.0f)
    cubicTo(0.0f, 12.4f, 3.6f, 16.0f, 8.0f, 16.0f)
    cubicTo(12.4f, 16.0f, 16.0f, 12.4f, 16.0f, 8.0f)
    cubicTo(16.0f, 3.6000004f, 12.4f, 0.0f, 8.0f, 0.0f)
    close()
    moveTo(13.2f, 5.3f)
    cubicTo(13.599999f, 5.3f, 13.9f, 5.6000004f, 14.3f, 5.6000004f)
    cubicTo(14.0f, 6.0000005f, 12.7f, 6.0000005f, 12.3f, 5.5000005f)
    cubicTo(12.6f, 5.4000006f, 12.8f, 5.3000007f, 13.2f, 5.3000007f)
    close()
    moveTo(1.0f, 8.0f)
    cubicTo(1.0f, 7.6f, 1.0f, 7.2f, 1.1f, 6.7f)
    cubicTo(1.2f, 6.7f, 1.3000001f, 6.7999997f, 1.4000001f, 6.7999997f)
    cubicTo(1.4000001f, 6.7999997f, 1.5000001f, 6.8999996f, 1.5000001f, 6.9999995f)
    cubicTo(1.5000001f, 7.2999997f, 1.8000002f, 7.4999995f, 2.0f, 7.4999995f)
    cubicTo(2.8f, 7.5999994f, 3.1f, 8.299999f, 3.8f, 8.5f)
    cubicTo(4.0f, 8.6f, 3.8999999f, 8.8f, 3.8f, 9.0f)
    cubicTo(3.1999998f, 9.8f, 3.7f, 10.4f, 4.2f, 10.9f)
    cubicTo(4.7f, 11.299999f, 4.7f, 11.7f, 4.7999997f, 12.299999f)
    cubicTo(4.7999997f, 12.999999f, 4.8999996f, 13.799999f, 5.2f, 14.499999f)
    cubicTo(2.6999998f, 13.299999f, 1.0f, 10.9f, 1.0f, 7.999999f)
    close()
    moveTo(8.0f, 15.0f)
    cubicTo(7.3f, 15.0f, 6.5f, 14.9f, 5.9f, 14.7f)
    cubicTo(5.8f, 14.5f, 5.8f, 14.3f, 5.9f, 14.099999f)
    cubicTo(6.3f, 13.299999f, 6.7000003f, 12.599999f, 7.2f, 11.9f)
    cubicTo(7.3999996f, 11.7f, 7.6f, 11.5f, 7.6f, 11.2f)
    cubicTo(7.6f, 11.0f, 7.7f, 10.7f, 7.7999997f, 10.5f)
    cubicTo(8.099999f, 10.0f, 7.9999995f, 9.7f, 7.6f, 9.6f)
    cubicTo(6.7999997f, 9.400001f, 6.3999996f, 8.700001f, 5.8f, 8.400001f)
    cubicTo(5.200001f, 8.1f, 4.6000004f, 7.9000006f, 4.1000004f, 8.200001f)
    cubicTo(3.9000003f, 8.300001f, 3.6000004f, 8.400001f, 3.6000004f, 8.1f)
    cubicTo(3.6000004f, 7.7000003f, 3.1000004f, 7.4000006f, 3.2000003f, 7.0000005f)
    cubicTo(3.1000004f, 7.0000005f, 3.0000002f, 7.0000005f, 2.9000003f, 7.1000004f)
    cubicTo(2.8000004f, 7.2000003f, 2.7000003f, 7.3f, 2.5000002f, 7.2000003f)
    cubicTo(2.3000002f, 7.0000005f, 2.4000003f, 6.8f, 2.4000003f, 6.6000004f)
    cubicTo(2.5000002f, 6.4000006f, 2.6000004f, 6.3f, 2.8000004f, 6.2000003f)
    cubicTo(3.2000005f, 6.1000004f, 3.6000004f, 6.1000004f, 3.8000004f, 6.6000004f)
    cubicTo(4.1000004f, 5.7000003f, 4.7000003f, 5.2000003f, 5.3f, 4.8f)
    cubicTo(5.3f, 4.8f, 6.1000004f, 4.1000004f, 6.2000003f, 4.1000004f)
    cubicTo(6.3f, 4.1000004f, 6.4f, 4.3f, 6.6000004f, 4.4000006f)
    cubicTo(6.8f, 4.4000006f, 6.9000006f, 4.4000006f, 6.9000006f, 4.200001f)
    cubicTo(7.0000005f, 3.7000008f, 6.700001f, 3.1000009f, 6.3000007f, 3.0000007f)
    cubicTo(6.3000007f, 2.9000008f, 6.4000006f, 2.9000008f, 6.4000006f, 2.9000008f)
    cubicTo(6.700001f, 2.800001f, 7.1000004f, 2.6000009f, 7.0000005f, 2.3000007f)
    cubicTo(7.0000005f, 1.9000007f, 6.6000004f, 1.7000006f, 6.2000003f, 1.7000006f)
    cubicTo(6.0000005f, 1.7000006f, 5.8f, 1.7000006f, 5.6000004f, 1.8000007f)
    cubicTo(5.2000003f, 2.0000007f, 4.7000003f, 2.2000008f, 4.1000004f, 2.2000008f)
    cubicTo(5.2000003f, 1.4000008f, 6.6000004f, 1.0000007f, 8.0f, 1.0000007f)
    cubicTo(8.3f, 1.0000007f, 8.5f, 1.0000007f, 8.8f, 1.0000007f)
    cubicTo(8.2f, 1.1000007f, 7.6000004f, 1.3000007f, 7.2000003f, 1.5000007f)
    cubicTo(7.8f, 1.6000007f, 7.9f, 1.9000007f, 7.7000003f, 2.4000006f)
    cubicTo(7.6000004f, 2.6000006f, 7.7000003f, 2.8000007f, 7.9f, 2.9000006f)
    cubicTo(8.1f, 3.0000005f, 8.3f, 3.0000005f, 8.4f, 2.8000007f)
    cubicTo(8.599999f, 2.5000007f, 9.0f, 2.4000006f, 9.299999f, 2.3000007f)
    cubicTo(9.699999f, 2.2000008f, 9.999999f, 2.0000007f, 10.299999f, 1.6000006f)
    cubicTo(10.299999f, 1.5000006f, 10.4f, 1.5000006f, 10.499999f, 1.4000006f)
    cubicTo(11.099999f, 1.6000006f, 11.699999f, 2.0000005f, 12.299999f, 2.4000006f)
    cubicTo(12.199999f, 2.4000006f, 12.199999f, 2.5000005f, 12.099999f, 2.5000005f)
    cubicTo(11.9f, 2.7000005f, 11.599999f, 2.8000004f, 11.9f, 3.2000005f)
    cubicTo(12.0f, 3.4000006f, 11.9f, 3.5000005f, 11.799999f, 3.6000006f)
    cubicTo(11.599999f, 3.7000005f, 11.499999f, 3.6000006f, 11.4f, 3.5000007f)
    cubicTo(11.3f, 3.4000008f, 11.299999f, 3.2000008f, 11.0f, 3.2000008f)
    cubicTo(10.9f, 3.4000008f, 10.6f, 3.5000007f, 10.6f, 3.8000007f)
    cubicTo(11.1f, 3.8000007f, 11.0f, 4.200001f, 11.1f, 4.5000005f)
    cubicTo(10.5f, 4.6000004f, 10.3f, 4.9000006f, 10.6f, 5.4000006f)
    cubicTo(10.700001f, 5.6000004f, 10.5f, 5.700001f, 10.400001f, 5.8000007f)
    cubicTo(10.000001f, 6.4000006f, 9.6f, 6.8000007f, 9.6f, 7.500001f)
    cubicTo(9.6f, 8.200001f, 10.1f, 8.900001f, 10.900001f, 8.800001f)
    cubicTo(11.8f, 8.700001f, 11.8f, 8.700001f, 12.1f, 9.500001f)
    cubicTo(12.1f, 9.600001f, 12.200001f, 9.700001f, 12.200001f, 9.800001f)
    cubicTo(12.300001f, 10.000001f, 12.400001f, 10.200001f, 12.300001f, 10.400002f)
    cubicTo(12.000001f, 11.200002f, 12.400002f, 11.800001f, 12.700001f, 12.400002f)
    cubicTo(12.800001f, 12.600001f, 12.900001f, 12.700002f, 13.000001f, 12.800001f)
    cubicTo(11.700001f, 14.200001f, 10.000001f, 15.000001f, 8.000001f, 15.000001f)
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

