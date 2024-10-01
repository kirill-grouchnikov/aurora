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
class pattern2 : Painter() {
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
    val rect2D = Rect(left=0.00998297892510891f, top=-0.6340898275375366f, right=100.00000373087823f, bottom=99.99999678134918f)
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
brush = SolidColor(Color(0, 0, 0, 0))
if (generalPathTile == null) {
   generalPathTile = Path()
} else {
   generalPathTile!!.reset()
}
generalPathTile?.run {
    moveTo(30.0f, 5.0f)
    cubicTo(21.776237f, 2.8811646f, 13.111074f, 6.1868696f, 8.388181f, 13.244773f)
    cubicTo(3.6652865f, 20.302677f, 3.914604f, 29.57363f, 9.010004f, 36.367496f)
    cubicTo(14.105404f, 43.161366f, 22.93571f, 45.996574f, 31.033709f, 43.43881f)
    cubicTo(39.131706f, 40.881046f, 44.73143f, 33.488087f, 45.0f, 25.0f)
}
shapeTile = Outline.Generic(generalPathTile!!)
drawOutline(outline = shapeTile!!, style = Fill, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)
brush = ShaderBrush(org.jetbrains.skia.Shader.makeTwoPointConicalGradient(x0 = 29.003996f, y0 = 14.36685f, r0 = 0.0f, x1 = 25.004993f, y1 = 26.3676f, r1 = 19.995007f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 128, r = 31, g = 175, b = 198), org.jetbrains.skia.Color.makeARGB(a = 128, r = 161, g = 211, b = 60), ), positions = floatArrayOf(0.9f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null)))
stroke = Stroke(width=10.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPathTile == null) {
   generalPathTile = Path()
} else {
   generalPathTile!!.reset()
}
generalPathTile?.run {
    moveTo(30.0f, 5.0f)
    cubicTo(21.776237f, 2.8811646f, 13.111074f, 6.1868696f, 8.388181f, 13.244773f)
    cubicTo(3.6652865f, 20.302677f, 3.914604f, 29.57363f, 9.010004f, 36.367496f)
    cubicTo(14.105404f, 43.161366f, 22.93571f, 45.996574f, 31.033709f, 43.43881f)
    cubicTo(39.131706f, 40.881046f, 44.73143f, 33.488087f, 45.0f, 25.0f)
}
shapeTile = Outline.Generic(generalPathTile!!)
drawOutline(outline = shapeTile!!, style = stroke!!, brush=brush!!, alpha = alphaTile, blendMode = blendModeTile)
alphaTile = alpha * 1.0f
blendModeTile = BlendMode.SrcOver
brush = ShaderBrush(org.jetbrains.skia.Shader.makeTwoPointConicalGradient(x0 = 90.0f, y0 = 62.5f, r0 = 0.0f, x1 = 75.0f, y1 = 77.5f, r1 = 25.0f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 128, r = 131, g = 75, b = 198), org.jetbrains.skia.Color.makeARGB(a = 128, r = 61, g = 211, b = 60), ), positions = floatArrayOf(0.6f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null)))
shapeTile = Outline.Rectangle(rect = Rect(left = 50.0f, top = 50.0f, right = 100.0f, bottom = 100.0f))
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

