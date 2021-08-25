package org.pushingpixels.aurora.demo.svg.tango

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
class go_next private constructor(var _width: Dp, var _height: Dp) : AuroraIcon() {
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
alphaStack.add(0, alpha)
alpha *= 0.2994652f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2711859941482544f, 0.0f, 0.0f, 0.0f,
0.0f, 1.2711859941482544f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-8.119376182556152f, -15.101790428161621f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.48186f, 36.421127f)
generalPath!!.cubicTo(40.50693f, 39.429993f, 37.530556f, 42.216076f, 32.67976f, 43.724407f)
generalPath!!.cubicTo(27.828962f, 45.23274f, 21.845287f, 45.23274f, 16.99449f, 43.724407f)
generalPath!!.cubicTo(12.143692f, 42.216076f, 9.167317f, 39.429993f, 9.192389f, 36.421127f)
generalPath!!.cubicTo(9.167317f, 33.412262f, 12.143692f, 30.626177f, 16.99449f, 29.117847f)
generalPath!!.cubicTo(21.845287f, 27.609516f, 27.828962f, 27.609516f, 32.67976f, 29.117847f)
generalPath!!.cubicTo(37.530556f, 30.626177f, 40.50693f, 33.412262f, 40.48186f, 36.421127f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), center = Offset(24.837126f, 36.42112f), radius = 15.644739f, tileMode = TileMode.Clamp)
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
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.554188f, 15.517348f)
generalPath!!.lineTo(8.554188f, 32.51177f)
generalPath!!.lineTo(21.538f, 32.51177f)
generalPath!!.lineTo(21.538f, 41.056805f)
generalPath!!.lineTo(41.497833f, 24.150366f)
generalPath!!.lineTo(21.41919f, 7.125117f)
generalPath!!.lineTo(21.41919f, 15.522652f)
generalPath!!.lineTo(8.554188f, 15.517348f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(115, 210, 22, 255)) ?: Color(115, 210, 22, 255)), 1.0f to (colorFilter?.invoke(Color(78, 154, 6, 255)) ?: Color(78, 154, 6, 255)), center = Offset(23.291637f, 34.84096f), radius = 14.294449f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(58, 115, 4, 255)) ?: Color(58, 115, 4, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.554188f, 15.517348f)
generalPath!!.lineTo(8.554188f, 32.51177f)
generalPath!!.lineTo(21.538f, 32.51177f)
generalPath!!.lineTo(21.538f, 41.056805f)
generalPath!!.lineTo(41.497833f, 24.150366f)
generalPath!!.lineTo(21.41919f, 7.125117f)
generalPath!!.lineTo(21.41919f, 15.522652f)
generalPath!!.lineTo(8.554188f, 15.517348f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5080214f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(21.962385f, 8.248504f)
generalPath!!.lineTo(21.962385f, 16.054977f)
generalPath!!.lineTo(9.145215f, 16.054977f)
generalPath!!.lineTo(9.145215f, 25.095692f)
generalPath!!.cubicTo(26.895214f, 27.095692f, 25.778751f, 17.640404f, 40.52875f, 24.140404f)
generalPath!!.lineTo(21.962385f, 8.248504f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 0)) ?: Color(255, 255, 255, 0)), center = Offset(20.804909f, 7.964386f), radius = 35.14524f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4812834f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
brush = SolidColor(colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(9.537702f, 16.561892f)
generalPath!!.lineTo(9.537702f, 31.546331f)
generalPath!!.lineTo(22.52307f, 31.546331f)
generalPath!!.lineTo(22.52307f, 38.941498f)
generalPath!!.lineTo(40.001083f, 24.145807f)
generalPath!!.lineTo(22.507109f, 9.365407f)
generalPath!!.lineTo(22.507109f, 16.56679f)
generalPath!!.lineTo(9.537702f, 16.561892f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
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
            return 3.533989191055298
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 6.5895562171936035
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 39.838478088378906
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 35.80787658691406
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
            return go_next(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return go_next(getOrigWidth().dp, getOrigHeight().dp)
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

