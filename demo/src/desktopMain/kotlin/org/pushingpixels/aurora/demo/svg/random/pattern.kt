package org.pushingpixels.aurora.demo.svg.random

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
class pattern : Painter() {
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
             var generalPathTile: Path? = null
             var alphaTile = alpha
             var blendModeTile = blendMode
alphaTile = alpha * 1.0f
blendModeTile = BlendMode.SrcOver
brush = SolidColor(Color(135, 206, 235, 255))
shapeTile = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 50.0f, bottom = 50.0f))
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)
alphaTile = alpha * 1.0f
blendModeTile = BlendMode.SrcOver
brush = Brush.linearGradient(0.05f to Color(255, 0, 0, 255), 0.95f to Color(255, 165, 0, 255), start = Offset(0.0f, 0.0f), end = Offset(0.0f, 25.0f), tileMode = TileMode.Clamp)
shapeTile = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 25.0f, bottom = 25.0f))
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)
alphaTile = alpha * 1.0f
blendModeTile = BlendMode.SrcOver
alphaTile = alpha * 1.0f
blendModeTile = BlendMode.SrcOver
brush = Brush.linearGradient(0.05f to Color(255, 255, 255, 128), 0.95f to Color(0, 0, 255, 128), start = Offset(5.0f, 5.0f), end = Offset(45.0f, 5.0f), tileMode = TileMode.Clamp)
shapeTile = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 5.0f, top = 5.0f, right = 45.0f, bottom = 45.0f))})
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)
alphaTile = alpha * 1.0f
blendModeTile = BlendMode.SrcOver
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
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 0.0f, top = 0.0f, right = 200.0f, bottom = 200.0f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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

