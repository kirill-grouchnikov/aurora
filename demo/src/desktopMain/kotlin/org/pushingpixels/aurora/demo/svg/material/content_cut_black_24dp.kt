package org.pushingpixels.aurora.demo.svg.material

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.toComposeBitmap
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class content_cut_black_24dp private constructor(var _width: Dp, var _height: Dp) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -0.0f,
0.0f, 1.0f, 0.0f, -0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_1
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_2
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_3
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(9.64f, 7.64f)
generalPath!!.cubicTo(9.87f, 7.14f, 10.0f, 6.59f, 10.0f, 6.0f)
generalPath!!.cubicTo(10.0f, 3.79f, 8.21f, 2.0f, 6.0f, 2.0f)
generalPath!!.cubicTo(3.79f, 2.0f, 2.0f, 3.79f, 2.0f, 6.0f)
generalPath!!.cubicTo(2.0f, 8.21f, 3.79f, 10.0f, 6.0f, 10.0f)
generalPath!!.cubicTo(6.59f, 10.0f, 7.14f, 9.87f, 7.64f, 9.64f)
generalPath!!.lineTo(10.0f, 12.0f)
generalPath!!.lineTo(7.6400003f, 14.36f)
generalPath!!.cubicTo(7.14f, 14.13f, 6.59f, 14.0f, 6.0f, 14.0f)
generalPath!!.cubicTo(3.79f, 14.0f, 2.0f, 15.79f, 2.0f, 18.0f)
generalPath!!.cubicTo(2.0f, 20.21f, 3.79f, 22.0f, 6.0f, 22.0f)
generalPath!!.cubicTo(8.21f, 22.0f, 10.0f, 20.21f, 10.0f, 18.0f)
generalPath!!.cubicTo(10.0f, 17.41f, 9.87f, 16.86f, 9.64f, 16.36f)
generalPath!!.lineTo(12.0f, 14.0f)
generalPath!!.lineTo(19.0f, 21.0f)
generalPath!!.lineTo(22.0f, 21.0f)
generalPath!!.lineTo(22.0f, 20.0f)
generalPath!!.lineTo(9.64f, 7.64f)
generalPath!!.close()
generalPath!!.moveTo(6.0f, 8.0f)
generalPath!!.cubicTo(4.9f, 8.0f, 4.0f, 7.11f, 4.0f, 6.0f)
generalPath!!.cubicTo(4.0f, 4.89f, 4.9f, 4.0f, 6.0f, 4.0f)
generalPath!!.cubicTo(7.1f, 4.0f, 8.0f, 4.89f, 8.0f, 6.0f)
generalPath!!.cubicTo(8.0f, 7.11f, 7.1f, 8.0f, 6.0f, 8.0f)
generalPath!!.close()
generalPath!!.moveTo(6.0f, 20.0f)
generalPath!!.cubicTo(4.9f, 20.0f, 4.0f, 19.11f, 4.0f, 18.0f)
generalPath!!.cubicTo(4.0f, 16.89f, 4.9f, 16.0f, 6.0f, 16.0f)
generalPath!!.cubicTo(7.1f, 16.0f, 8.0f, 16.89f, 8.0f, 18.0f)
generalPath!!.cubicTo(8.0f, 19.11f, 7.1f, 20.0f, 6.0f, 20.0f)
generalPath!!.close()
generalPath!!.moveTo(12.0f, 12.5f)
generalPath!!.cubicTo(11.72f, 12.5f, 11.5f, 12.28f, 11.5f, 12.0f)
generalPath!!.cubicTo(11.5f, 11.72f, 11.72f, 11.5f, 12.0f, 11.5f)
generalPath!!.cubicTo(12.28f, 11.5f, 12.5f, 11.72f, 12.5f, 12.0f)
generalPath!!.cubicTo(12.5f, 12.28f, 12.28f, 12.5f, 12.0f, 12.5f)
generalPath!!.close()
generalPath!!.moveTo(19.0f, 3.0f)
generalPath!!.lineTo(13.0f, 9.0f)
generalPath!!.lineTo(15.0f, 11.0f)
generalPath!!.lineTo(22.0f, 4.0f)
generalPath!!.lineTo(22.0f, 3.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)

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
            return 2.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 2.0
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
            return 20.0
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return content_cut_black_24dp(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return content_cut_black_24dp(getOrigWidth().dp, getOrigHeight().dp)
                }
            }
        }

        
    }

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = width
        _height = height
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            clipRect {
                // Use the original icon bounding box and the current icon dimension to compute
                // the scaling factor
                val fullOrigWidth = getOrigX() + getOrigWidth()
                val fullOrigHeight = getOrigY() + getOrigHeight()
                val coef1 = _width.toPx() / fullOrigWidth
                val coef2 = _height.toPx() / fullOrigHeight
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
}

