package org.pushingpixels.aurora.tools.screenshot.svg.tango

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
class edit_copy : Painter() {
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
alpha *= 0.49999997f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0015079975128174f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0006159543991089f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.050022050738334656f, -0.06304895132780075f, 0.0f, 1.0f)
))}){
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0
shape = Outline.Rectangle(rect = Rect(left = 20.161836624145508f, top = 34.03341293334961f, right = 33.16183662414551f, bottom = 36.03341293334961f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 1.5484408140182495f, top = 1.5629303455352783f, right = 32.49999988079071f, bottom = 37.539618730545044f,radiusX = 1.131310224533081f, radiusY = 1.1323192119598389f))
brush = Brush.linearGradient(0.0f to Color(240, 240, 239, 255), 0.59928656f to Color(232, 232, 232, 255), 0.82758623f to Color(255, 255, 255, 255), 1.0f to Color(216, 216, 211, 255), start = Offset(15.225623f, 13.864829f), end = Offset(29.587997f, 34.11655f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=0.99893934f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 1.5484408140182495f, top = 1.5629303455352783f, right = 32.49999988079071f, bottom = 37.539618730545044f,radiusX = 1.131310224533081f, radiusY = 1.1323192119598389f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(19.06669f, 21.75695f), end = Offset(23.794687f, 37.040768f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99893963f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 2.532512903213501f, top = 2.5605955123901367f, right = 31.503254175186157f, bottom = 36.54165172576904f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_3
shape = Outline.Rectangle(rect = Rect(left = 7.016119003295898f, top = 10.033413887023926f, right = 28.0161190032959f, bottom = 12.033413887023926f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_4
shape = Outline.Rectangle(rect = Rect(left = 7.016119003295898f, top = 14.033413887023926f, right = 27.0161190032959f, bottom = 16.033413887023926f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_5
shape = Outline.Rectangle(rect = Rect(left = 7.016119003295898f, top = 18.033414840698242f, right = 25.0161190032959f, bottom = 20.033414840698242f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_6
shape = Outline.Rectangle(rect = Rect(left = 7.016119003295898f, top = 22.033414840698242f, right = 28.0161190032959f, bottom = 24.033414840698242f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_7
shape = Outline.Rectangle(rect = Rect(left = 7.016119003295898f, top = 26.03341293334961f, right = 20.0161190032959f, bottom = 28.03341293334961f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.072946f, 10.500852f)
    lineTo(44.929333f, 10.500852f)
    cubicTo(45.24507f, 10.500852f, 45.499256f, 10.753945f, 45.499256f, 11.068324f)
    lineTo(45.499256f, 38.235687f)
    cubicTo(45.499256f, 40.71214f, 38.619446f, 46.538773f, 36.231323f, 46.538773f)
    lineTo(15.072946f, 46.538773f)
    cubicTo(14.757206f, 46.538773f, 14.50302f, 46.285683f, 14.50302f, 45.9713f)
    lineTo(14.50302f, 11.068324f)
    cubicTo(14.50302f, 10.753945f, 14.757206f, 10.500852f, 15.072946f, 10.500852f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(240, 240, 239, 255), 0.59928656f to Color(232, 232, 232, 255), 0.82758623f to Color(255, 255, 255, 255), 1.0f to Color(216, 216, 211, 255), start = Offset(28.199936f, 22.823694f), end = Offset(42.58304f, 43.109886f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.072946f, 10.500852f)
    lineTo(44.929333f, 10.500852f)
    cubicTo(45.24507f, 10.500852f, 45.499256f, 10.753945f, 45.499256f, 11.068324f)
    lineTo(45.499256f, 38.235687f)
    cubicTo(45.499256f, 40.71214f, 38.619446f, 46.538773f, 36.231323f, 46.538773f)
    lineTo(15.072946f, 46.538773f)
    cubicTo(14.757206f, 46.538773f, 14.50302f, 46.285683f, 14.50302f, 45.9713f)
    lineTo(14.50302f, 11.068324f)
    cubicTo(14.50302f, 10.753945f, 14.757206f, 10.500852f, 15.072946f, 10.500852f)
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
// _0_0_1_1
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(32.05231f, 30.730087f), end = Offset(36.784653f, 46.040764f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000008f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 15.502950668334961f, top = 11.5f, right = 44.50029945373535f, bottom = 45.54076385498047f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.220917f, 46.536964f)
    cubicTo(38.251335f, 46.866863f, 45.80971f, 42.00704f, 45.50533f, 38.039124f)
    cubicTo(43.942066f, 40.46222f, 40.746807f, 39.32586f, 36.63805f, 39.48487f)
    cubicTo(36.63805f, 39.48487f, 37.033417f, 46.036964f, 36.220917f, 46.536964f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(124, 124, 124, 255), 1.0f to Color(184, 184, 184, 255), start = Offset(42.158417f, 44.49163f), end = Offset(39.826756f, 41.80413f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(134, 138, 132, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.220917f, 46.536964f)
    cubicTo(38.251335f, 46.866863f, 45.80971f, 42.00704f, 45.50533f, 38.039124f)
    cubicTo(43.942066f, 40.46222f, 40.746807f, 39.32586f, 36.63805f, 39.48487f)
    cubicTo(36.63805f, 39.48487f, 37.033417f, 46.036964f, 36.220917f, 46.536964f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36931816f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_3
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(39.55784f, 40.579742f), end = Offset(40.331882f, 41.72879f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.671356f, 44.345463f)
    cubicTo(39.041134f, 43.661636f, 42.099606f, 42.198997f, 43.398987f, 40.317993f)
    cubicTo(41.80289f, 40.99805f, 40.451176f, 40.527493f, 37.69665f, 40.5084f)
    cubicTo(37.69665f, 40.5084f, 37.858974f, 43.570496f, 37.671356f, 44.345463f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_4
shape = Outline.Rectangle(rect = Rect(left = 20.0f, top = 19.033414840698242f, right = 41.0f, bottom = 21.033414840698242f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_5
shape = Outline.Rectangle(rect = Rect(left = 20.0f, top = 23.033414840698242f, right = 39.99223327636719f, bottom = 25.033414840698242f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_6
shape = Outline.Rectangle(rect = Rect(left = 20.0f, top = 27.033414840698242f, right = 37.976701736450195f, bottom = 29.033414840698242f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.17045452f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1_7
shape = Outline.Rectangle(rect = Rect(left = 20.0f, top = 31.033414840698242f, right = 41.0f, bottom = 33.03341484069824f))
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 1.0005309581756592
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.00106680393219
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 45.013736724853516
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 46.04948043823242
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

