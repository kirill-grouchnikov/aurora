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
class waves_24px : Painter() {
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
    moveTo(17.0f, 16.99f)
    cubicTo(15.65f, 16.99f, 14.8f, 17.41f, 14.05f, 17.789999f)
    cubicTo(13.400001f, 18.119999f, 12.87f, 18.39f, 12.0f, 18.39f)
    cubicTo(11.1f, 18.39f, 10.6f, 18.14f, 9.95f, 17.789999f)
    cubicTo(9.2f, 17.41f, 8.38f, 16.99f, 7.0f, 16.99f)
    cubicTo(5.62f, 16.99f, 4.8f, 17.41f, 4.05f, 17.789999f)
    cubicTo(3.4f, 18.119999f, 2.88f, 18.39f, 2.0000002f, 18.39f)
    lineTo(2.0000002f, 20.34f)
    cubicTo(3.3500004f, 20.34f, 4.2000003f, 19.92f, 4.9500003f, 19.54f)
    cubicTo(5.6000004f, 19.210001f, 6.1200004f, 18.94f, 7.0f, 18.94f)
    cubicTo(7.8799996f, 18.94f, 8.4f, 19.19f, 9.05f, 19.54f)
    cubicTo(9.8f, 19.92f, 10.62f, 20.34f, 12.0f, 20.34f)
    cubicTo(13.38f, 20.34f, 14.2f, 19.92f, 14.95f, 19.54f)
    cubicTo(15.599999f, 19.210001f, 16.13f, 18.94f, 17.0f, 18.94f)
    cubicTo(17.9f, 18.94f, 18.4f, 19.19f, 19.05f, 19.54f)
    cubicTo(19.8f, 19.92f, 20.63f, 20.34f, 22.0f, 20.34f)
    lineTo(22.0f, 18.39f)
    cubicTo(21.1f, 18.39f, 20.6f, 18.14f, 19.95f, 17.789999f)
    cubicTo(19.2f, 17.41f, 18.35f, 16.99f, 17.0f, 16.99f)
    close()
    moveTo(17.0f, 12.54f)
    cubicTo(15.65f, 12.54f, 14.8f, 12.97f, 14.05f, 13.34f)
    cubicTo(13.400001f, 13.66f, 12.87f, 13.940001f, 12.0f, 13.940001f)
    cubicTo(11.1f, 13.940001f, 10.6f, 13.690001f, 9.95f, 13.34f)
    cubicTo(9.2f, 12.96f, 8.38f, 12.54f, 7.0f, 12.54f)
    cubicTo(5.62f, 12.54f, 4.8f, 12.97f, 4.05f, 13.34f)
    cubicTo(3.4f, 13.66f, 2.88f, 13.940001f, 2.0000002f, 13.940001f)
    lineTo(2.0000002f, 15.89f)
    cubicTo(3.3500004f, 15.89f, 4.2000003f, 15.46f, 4.9500003f, 15.09f)
    cubicTo(5.6000004f, 14.74f, 6.1000004f, 14.49f, 7.0f, 14.49f)
    cubicTo(7.8999996f, 14.49f, 8.4f, 14.74f, 9.05f, 15.09f)
    cubicTo(9.8f, 15.47f, 10.62f, 15.89f, 12.0f, 15.89f)
    cubicTo(13.38f, 15.89f, 14.2f, 15.46f, 14.95f, 15.09f)
    cubicTo(15.599999f, 14.74f, 16.1f, 14.49f, 17.0f, 14.49f)
    cubicTo(17.9f, 14.49f, 18.4f, 14.74f, 19.05f, 15.09f)
    cubicTo(19.8f, 15.47f, 20.63f, 15.89f, 22.0f, 15.89f)
    lineTo(22.0f, 13.940001f)
    cubicTo(21.1f, 13.940001f, 20.6f, 13.690001f, 19.95f, 13.34f)
    cubicTo(19.2f, 12.96f, 18.35f, 12.54f, 17.0f, 12.54f)
    close()
    moveTo(19.95f, 4.46f)
    cubicTo(19.2f, 4.08f, 18.37f, 3.66f, 17.0f, 3.66f)
    cubicTo(15.629999f, 3.66f, 14.8f, 4.08f, 14.05f, 4.46f)
    cubicTo(13.400001f, 4.78f, 12.87f, 5.06f, 12.0f, 5.06f)
    cubicTo(11.1f, 5.06f, 10.6f, 4.81f, 9.95f, 4.46f)
    cubicTo(9.2f, 4.09f, 8.38f, 3.66f, 7.0f, 3.66f)
    cubicTo(5.62f, 3.66f, 4.8f, 4.08f, 4.05f, 4.46f)
    cubicTo(3.4f, 4.79f, 2.88f, 5.06f, 2.0000002f, 5.06f)
    lineTo(2.0000002f, 6.99f)
    cubicTo(3.3500004f, 6.99f, 4.2000003f, 6.56f, 4.9500003f, 6.1899996f)
    cubicTo(5.6000004f, 5.8599997f, 6.1200004f, 5.5899997f, 7.0f, 5.5899997f)
    cubicTo(7.8799996f, 5.5899997f, 8.4f, 5.8399997f, 9.05f, 6.1899996f)
    cubicTo(9.8f, 6.5699997f, 10.62f, 6.99f, 12.0f, 6.99f)
    cubicTo(13.38f, 6.99f, 14.2f, 6.56f, 14.95f, 6.1899996f)
    cubicTo(15.599999f, 5.8699994f, 16.13f, 5.5899997f, 17.0f, 5.5899997f)
    cubicTo(17.9f, 5.5899997f, 18.4f, 5.8399997f, 19.05f, 6.1899996f)
    cubicTo(19.8f, 6.5699997f, 20.63f, 6.99f, 22.0f, 6.99f)
    lineTo(22.0f, 5.04f)
    cubicTo(21.1f, 5.04f, 20.6f, 4.79f, 19.95f, 4.46f)
    close()
    moveTo(17.0f, 8.09f)
    cubicTo(15.65f, 8.09f, 14.8f, 8.52f, 14.05f, 8.89f)
    cubicTo(13.400001f, 9.240001f, 12.900001f, 9.490001f, 12.0f, 9.490001f)
    cubicTo(11.099999f, 9.490001f, 10.6f, 9.240001f, 9.95f, 8.89f)
    cubicTo(9.2f, 8.51f, 8.38f, 8.09f, 7.0f, 8.09f)
    cubicTo(5.62f, 8.09f, 4.8f, 8.52f, 4.05f, 8.89f)
    cubicTo(3.4f, 9.240001f, 2.9f, 9.490001f, 2.0000002f, 9.490001f)
    lineTo(2.0000002f, 11.440001f)
    cubicTo(3.3500004f, 11.440001f, 4.2000003f, 11.01f, 4.9500003f, 10.64f)
    cubicTo(5.6000004f, 10.320001f, 6.13f, 10.04f, 7.0f, 10.04f)
    cubicTo(7.87f, 10.04f, 8.4f, 10.29f, 9.05f, 10.64f)
    cubicTo(9.8f, 11.02f, 10.62f, 11.440001f, 12.0f, 11.440001f)
    cubicTo(13.38f, 11.440001f, 14.2f, 11.01f, 14.95f, 10.64f)
    cubicTo(15.599999f, 10.320001f, 16.13f, 10.04f, 17.0f, 10.04f)
    cubicTo(17.9f, 10.04f, 18.4f, 10.29f, 19.05f, 10.64f)
    cubicTo(19.8f, 11.02f, 20.63f, 11.440001f, 22.0f, 11.440001f)
    lineTo(22.0f, 9.49f)
    cubicTo(21.1f, 9.49f, 20.6f, 9.24f, 19.95f, 8.889999f)
    cubicTo(19.2f, 8.509999f, 18.35f, 8.089999f, 17.0f, 8.089999f)
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
            return 2.000000238418579
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.6600000858306885
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 20.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 16.68000030517578
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

