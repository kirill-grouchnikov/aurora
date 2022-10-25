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
class globe_wire : Painter() {
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
    cubicTo(3.5819998f, 0.0f, 0.0f, 3.582f, 0.0f, 8.0f)
    cubicTo(0.0f, 12.418f, 3.582f, 16.0f, 8.0f, 16.0f)
    cubicTo(12.418f, 16.0f, 16.0f, 12.418f, 16.0f, 8.0f)
    cubicTo(16.0f, 3.5819998f, 12.418f, 0.0f, 8.0f, 0.0f)
    close()
    moveTo(14.8f, 9.5f)
    cubicTo(14.8f, 10.0f, 14.1f, 10.16f, 12.8f, 10.5f)
    cubicTo(12.924f, 9.911f, 13.0060005f, 9.223f, 13.029f, 8.52f)
    lineTo(15.030001f, 8.5f)
    cubicTo(15.030001f, 8.86f, 14.950001f, 9.0f, 14.870001f, 9.5f)
    lineTo(14.870001f, 9.5f)
    close()
    moveTo(1.2f, 9.5f)
    lineTo(1.2f, 9.5f)
    cubicTo(1.1f, 9.0f, 1.0500001f, 8.86f, 1.0f, 8.5f)
    lineTo(3.0f, 8.5f)
    cubicTo(3.024f, 9.223f, 3.106f, 9.911f, 3.244f, 10.579f)
    cubicTo(1.9f, 10.160001f, 1.2f, 10.0f, 1.2f, 9.5f)
    close()
    moveTo(1.2f, 6.5f)
    cubicTo(1.2f, 6.0f, 1.9000001f, 5.84f, 3.2f, 5.5f)
    cubicTo(3.085f, 6.094f, 3.013f, 6.784f, 3.0f, 7.489f)
    lineTo(1.0f, 7.5f)
    cubicTo(1.0f, 7.14f, 1.08f, 7.0f, 1.16f, 6.5f)
    lineTo(1.16f, 6.5f)
    close()
    moveTo(8.5f, 5.0f)
    cubicTo(9.63f, 5.013f, 10.726f, 5.107f, 11.798f, 5.277f)
    cubicTo(11.845f, 5.92f, 11.963f, 6.687f, 11.999001f, 7.476f)
    lineTo(8.5f, 7.501f)
    lineTo(8.5f, 5.001f)
    close()
    moveTo(8.5f, 4.0f)
    lineTo(8.5f, 1.06f)
    cubicTo(9.67f, 1.3299999f, 10.7f, 2.53f, 11.34f, 4.21f)
    cubicTo(10.504f, 4.094f, 9.521f, 4.018f, 8.523001f, 4.0f)
    close()
    moveTo(7.5f, 1.06f)
    lineTo(7.5f, 4.0f)
    cubicTo(6.483f, 4.015f, 5.499f, 4.087f, 4.532f, 4.214f)
    cubicTo(5.3f, 2.5300002f, 6.33f, 1.3300002f, 7.5f, 1.0600002f)
    close()
    moveTo(7.5f, 5.0f)
    lineTo(7.5f, 7.5f)
    lineTo(4.0f, 7.5f)
    cubicTo(4.031f, 6.6940002f, 4.142f, 5.929f, 4.326f, 5.193f)
    cubicTo(5.2580004f, 5.113f, 6.361f, 5.016f, 7.484f, 5.0f)
    close()
    moveTo(4.0f, 8.5f)
    lineTo(7.5f, 8.5f)
    lineTo(7.5f, 11.0f)
    cubicTo(6.37f, 10.987f, 5.274f, 10.893f, 4.2019997f, 10.723f)
    cubicTo(4.1549997f, 10.08f, 4.0369997f, 9.313f, 4.0009995f, 8.524f)
    close()
    moveTo(7.5f, 12.0f)
    lineTo(7.5f, 14.940001f)
    cubicTo(6.33f, 14.67f, 5.3f, 13.47f, 4.66f, 11.790001f)
    cubicTo(5.496f, 11.906001f, 6.479f, 11.982001f, 7.4769998f, 12.000001f)
    close()
    moveTo(8.5f, 14.94f)
    lineTo(8.5f, 12.0f)
    cubicTo(9.517f, 11.985f, 10.500999f, 11.913f, 11.468f, 11.786f)
    cubicTo(10.700001f, 13.47f, 9.67f, 14.67f, 8.5f, 14.940001f)
    close()
    moveTo(8.5f, 11.0f)
    lineTo(8.5f, 8.5f)
    lineTo(12.0f, 8.5f)
    cubicTo(11.969f, 9.306f, 11.858f, 10.071f, 11.674f, 10.807f)
    cubicTo(10.742f, 10.887f, 9.639f, 10.984f, 8.516f, 11.0f)
    close()
    moveTo(15.0f, 7.5f)
    lineTo(13.0f, 7.5f)
    cubicTo(12.976f, 6.777f, 12.894f, 6.0889997f, 12.756f, 5.421f)
    cubicTo(14.11f, 5.82f, 14.7699995f, 5.98f, 14.7699995f, 6.5f)
    lineTo(14.7699995f, 6.5f)
    cubicTo(14.9f, 7.0f, 14.95f, 7.14f, 14.999999f, 7.5f)
    close()
    moveTo(14.3f, 4.91f)
    cubicTo(13.794001f, 4.706f, 13.194f, 4.5299997f, 12.574f, 4.41f)
    cubicTo(12.213f, 3.3909998f, 11.765f, 2.5119998f, 11.185f, 1.7379999f)
    cubicTo(12.540001f, 2.464f, 13.598001f, 3.5489998f, 14.252001f, 4.869f)
    close()
    moveTo(4.84f, 1.76f)
    cubicTo(4.2720003f, 2.512f, 3.821f, 3.391f, 3.5350003f, 4.341f)
    cubicTo(2.8360004f, 4.53f, 2.2360003f, 4.7060003f, 1.6610004f, 4.934f)
    cubicTo(2.4120004f, 3.5440001f, 3.4840002f, 2.459f, 4.8f, 1.7780001f)
    close()
    moveTo(1.73f, 11.09f)
    cubicTo(2.236f, 11.294001f, 2.836f, 11.47f, 3.4559999f, 11.59f)
    cubicTo(3.817f, 12.609f, 4.265f, 13.488f, 4.845f, 14.262f)
    cubicTo(3.4779997f, 13.54f, 2.4089997f, 12.455f, 1.7479999f, 11.1310005f)
    close()
    moveTo(11.17f, 14.24f)
    cubicTo(11.734f, 13.4869995f, 12.182f, 12.608999f, 12.465f, 11.658999f)
    cubicTo(13.1640005f, 11.469999f, 13.764f, 11.294f, 14.339f, 11.065999f)
    cubicTo(13.587999f, 12.455999f, 12.516f, 13.540998f, 11.2f, 14.221999f)
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

