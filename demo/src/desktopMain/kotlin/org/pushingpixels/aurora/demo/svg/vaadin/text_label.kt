package org.pushingpixels.aurora.demo.svg.vaadin

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
class text_label private constructor(var _width: Dp, var _height: Dp) : AuroraIcon() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)
    private var colorFilter: ((Color) -> Color)? = null

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.0f, -0.0f, 0.0f, 1.0f)
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(12.5f, 4.9f)
generalPath!!.cubicTo(11.1f, 4.9f, 10.0f, 5.7000003f, 9.9f, 5.8f)
generalPath!!.lineTo(11.099999f, 7.4f)
generalPath!!.cubicTo(11.099999f, 7.4f, 11.799999f, 6.9f, 12.499999f, 6.9f)
generalPath!!.cubicTo(13.899999f, 6.9f, 13.999999f, 8.1f, 13.999999f, 8.5f)
generalPath!!.cubicTo(13.599999f, 8.4f, 12.899999f, 8.2f, 11.999999f, 8.4f)
generalPath!!.cubicTo(10.599999f, 8.7f, 9.199999f, 10.4f, 9.9f, 12.299999f)
generalPath!!.cubicTo(10.599999f, 14.099999f, 13.0f, 14.4f, 14.0f, 12.9f)
generalPath!!.lineTo(14.0f, 13.9f)
generalPath!!.lineTo(16.0f, 13.9f)
generalPath!!.lineTo(16.0f, 8.599999f)
generalPath!!.cubicTo(16.0f, 5.8999996f, 14.1f, 4.8999996f, 12.5f, 4.8999996f)
generalPath!!.close()
generalPath!!.moveTo(11.5f, 11.4f)
generalPath!!.cubicTo(11.4f, 9.5f, 13.0f, 9.5f, 14.0f, 9.599999f)
generalPath!!.lineTo(14.0f, 10.599999f)
generalPath!!.cubicTo(14.0f, 11.799999f, 11.7f, 12.9f, 11.5f, 11.4f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(68, 68, 68, 255)) ?: Color(68, 68, 68, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(6.9f, 14.0f)
generalPath!!.lineTo(9.0f, 14.0f)
generalPath!!.lineTo(5.8f, 2.0f)
generalPath!!.lineTo(3.1000001f, 2.0f)
generalPath!!.lineTo(2.3841858E-7f, 14.0f)
generalPath!!.lineTo(2.1000001f, 14.0f)
generalPath!!.lineTo(3.1000001f, 10.0f)
generalPath!!.lineTo(5.8f, 10.0f)
generalPath!!.lineTo(6.9f, 14.0f)
generalPath!!.close()
generalPath!!.moveTo(3.6f, 8.0f)
generalPath!!.lineTo(4.4f, 4.8f)
generalPath!!.lineTo(5.3f, 8.0f)
generalPath!!.lineTo(3.6000001f, 8.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(colorFilter?.invoke(Color(68, 68, 68, 255)) ?: Color(68, 68, 68, 255))
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
            return 2.384185791015625E-7
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
            return 16.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 12.399999618530273
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
            return text_label(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return text_label(getOrigWidth().dp, getOrigHeight().dp)
                }
            }
        }

        
    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        setSize(size.width.toDp(), size.height.toDp())
        paintIcon(this)
    }

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    override fun setSize(width: Dp, height: Dp) {
        _width = width
        _height = height
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        this.colorFilter = colorFilter
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

