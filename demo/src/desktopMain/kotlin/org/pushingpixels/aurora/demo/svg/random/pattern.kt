package org.pushingpixels.aurora.demo.svg.random

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
class pattern private constructor(var _width: Dp, var _height: Dp) : AuroraIcon {
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
shape = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 200.0f, bottom = 200.0f))
withTransform({
    if (shape is Outline.Rectangle) {
        clipPath(Path().also { it.addRect((shape as Outline.Rectangle).rect) })
    }
    if (shape is Outline.Rounded) {
        clipPath(Path().also { it.addRoundRect((shape as Outline.Rounded).roundRect) })
    }
    if (shape is Outline.Generic) {
        clipPath(Path().also { it.addPath((shape as Outline.Generic).path) })
    }
}) {
    val rect2D = Rect(left=0.0f, top=0.0f, right=50.0f, bottom=50.0f)
    val tTiled = Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f))
withTransform({transform(tTiled)}){
   var src: Offset
   var dst: Offset
    var startX = rect2D.left
    val maxX = when(shape) {
        is Outline.Rectangle -> (shape as Outline.Rectangle).rect.right
        is Outline.Rounded -> (shape as Outline.Rounded).roundRect.right
        is Outline.Generic -> (shape as Outline.Generic).path.getBounds().right
        else -> 0.0f
    }
    val maxY = when(shape) {
        is Outline.Rectangle -> (shape as Outline.Rectangle).rect.bottom
        is Outline.Rounded -> (shape as Outline.Rounded).roundRect.bottom
        is Outline.Generic -> (shape as Outline.Generic).path.getBounds().bottom
        else -> 0.0f
    }
    tileX@ while (true) {
        var startY = rect2D.top
        tileY@ while (true) {
             translate(left = startX, top = startY) {
             var shapeTile: Outline?
             var alphaTile = alpha
alphaTile = alpha * 1.0f
brush = SolidColor(colorFilter?.invoke(Color(135, 206, 235, 255)) ?: Color(135, 206, 235, 255))
shapeTile = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 50.0f, bottom = 50.0f))
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile)
alphaTile = alpha * 1.0f
brush = Brush.linearGradient(0.05f to (colorFilter?.invoke(Color(255, 0, 0, 255)) ?: Color(255, 0, 0, 255)), 0.95f to (colorFilter?.invoke(Color(255, 165, 0, 255)) ?: Color(255, 165, 0, 255)), start = Offset(0.0f, 0.0f), end = Offset(0.0f, 25.0f), tileMode = TileMode.Clamp)
shapeTile = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 25.0f, bottom = 25.0f))
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile)
alphaTile = alpha * 1.0f
alphaTile = alpha * 1.0f
brush = Brush.linearGradient(0.05f to (colorFilter?.invoke(Color(255, 255, 255, 128)) ?: Color(255, 255, 255, 128)), 0.95f to (colorFilter?.invoke(Color(0, 0, 255, 128)) ?: Color(0, 0, 255, 128)), start = Offset(0.0f, 0.0f), end = Offset(6.666667f, 0.0f), tileMode = TileMode.Clamp)
shapeTile = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 5.0f, top = 5.0f, right = 45.0f, bottom = 45.0f))})
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile)
alphaTile = alpha * 1.0f
            }
            startY += rect2D.height
            src = Offset(x = startX, y = startY)
            dst = tTiled.map(src)
            if (dst.y > maxY) {
                break@tileY
            }
        }
        startX += rect2D.width
        src = Offset(x = startX, y = startY)
        dst = tTiled.map(src)
        if (dst.x > maxX) {
            break@tileX
        }
    }
}
}
brush = SolidColor(colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 200.0f, bottom = 200.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
            return 200.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 200.0
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
            return pattern(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return pattern(getOrigWidth().dp, getOrigHeight().dp)
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

