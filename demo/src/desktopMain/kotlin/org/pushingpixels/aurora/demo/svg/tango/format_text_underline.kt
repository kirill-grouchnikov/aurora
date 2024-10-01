package org.pushingpixels.aurora.demo.svg.tango

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
class format_text_underline : Painter() {
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
alphaStack.add(0, alpha)
alpha *= 0.15f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.4701440334320068f, 0.0f, 0.0f, 0.0f,
0.0f, 0.5354740023612976f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-12.76416015625f, 20.915340423583984f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.48186f, 36.421127f)
    cubicTo(40.50693f, 39.429993f, 37.530556f, 42.216076f, 32.67976f, 43.724407f)
    cubicTo(27.828962f, 45.23274f, 21.845287f, 45.23274f, 16.99449f, 43.724407f)
    cubicTo(12.143692f, 42.216076f, 9.167317f, 39.429993f, 9.192389f, 36.421127f)
    cubicTo(9.167317f, 33.412262f, 12.143692f, 30.626177f, 16.99449f, 29.117847f)
    cubicTo(21.845287f, 27.609516f, 27.828962f, 27.609516f, 32.67976f, 29.117847f)
    cubicTo(37.530556f, 30.626177f, 40.50693f, 33.412262f, 40.48186f, 36.421127f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.837126f, 36.42112f), radius = 15.644739f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.0f, 2.5f)
    lineTo(5.0f, 39.5f)
    lineTo(11.0f, 39.5f)
    lineTo(14.25f, 31.5f)
    lineTo(33.75f, 31.5f)
    lineTo(37.0f, 39.5f)
    lineTo(43.0f, 39.5f)
    lineTo(28.0f, 2.5f)
    lineTo(20.0f, 2.5f)
    close()
    moveTo(24.0f, 7.5f)
    lineTo(31.71875f, 26.5f)
    lineTo(16.28125f, 26.5f)
    lineTo(24.0f, 7.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(73, 127, 198, 255), 1.0f to Color(144, 179, 217, 255), start = Offset(29.033241f, 35.44521f), end = Offset(14.33379f, 8.459655f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(42, 83, 135, 255), 1.0f to Color(52, 101, 164, 255), start = Offset(22.125f, 36.96281f), end = Offset(22.125f, 8.25f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.0f, 2.5f)
    lineTo(5.0f, 39.5f)
    lineTo(11.0f, 39.5f)
    lineTo(14.25f, 31.5f)
    lineTo(33.75f, 31.5f)
    lineTo(37.0f, 39.5f)
    lineTo(43.0f, 39.5f)
    lineTo(28.0f, 2.5f)
    lineTo(20.0f, 2.5f)
    close()
    moveTo(24.0f, 7.5f)
    lineTo(31.71875f, 26.5f)
    lineTo(16.28125f, 26.5f)
    lineTo(24.0f, 7.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(179, 179, 179, 0), start = Offset(19.891792f, 15.114628f), end = Offset(21.506844f, 84.69681f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.5f, 38.5f)
    lineTo(6.646263f, 38.5f)
    lineTo(20.682829f, 3.5f)
    lineTo(27.353737f, 3.5f)
    lineTo(41.68283f, 38.5f)
    lineTo(37.865658f, 38.5f)
    lineTo(34.403564f, 30.469063f)
    lineTo(13.523417f, 30.368273f)
    cubicTo(13.523417f, 30.368273f, 10.5f, 38.5f, 10.5f, 38.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.47802198f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(23.911612f, 21.13604f), end = Offset(23.911612f, 5.402913f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.43934f, 27.5f)
    lineTo(33.383884f, 27.5f)
    lineTo(24.041632f, 4.81103f)
    lineTo(14.43934f, 27.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_3
shape = Outline.Rectangle(rect = Rect(left = 4.5f, top = 41.5f, right = 43.5f, bottom = 44.5f))
brush = Brush.linearGradient(0.0f to Color(73, 127, 198, 255), 1.0f to Color(144, 179, 217, 255), start = Offset(27.675018f, 43.398964f), end = Offset(16.0744f, 25.920008f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(42, 83, 135, 255), 1.0f to Color(52, 101, 164, 255), start = Offset(5.0271654f, 48.7619f), end = Offset(5.0271654f, 41.416153f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 4.5f, top = 41.5f, right = 43.5f, bottom = 44.5f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(39.421204f, 42.459263f), end = Offset(-24.130018f, 42.5f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.5f, 42.5f)
    lineTo(42.5f, 42.5f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 0.7497439980506897
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
            return 46.000492095947266
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 43.0
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

