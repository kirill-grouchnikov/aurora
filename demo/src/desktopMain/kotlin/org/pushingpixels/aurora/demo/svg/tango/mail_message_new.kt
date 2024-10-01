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
class mail_message_new : Painter() {
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
alpha *= 0.402f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
shape = Outline.Rectangle(rect = Rect(left = 9.256999969482422f, top = 37.369998931884766f, right = 40.24099922180176f, bottom = 44.476998805999756f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(1.556f, 37.387844f), end = Offset(1.556f, 44.503647f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.402f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.24f, 37.38f)
    lineTo(40.24f, 44.487f)
    cubicTo(43.544003f, 44.5f, 48.229f, 42.895f, 48.229f, 40.933f)
    cubicTo(48.229f, 38.970997f, 44.541f, 37.379997f, 40.24f, 37.379997f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(40.45558f, 40.903847f), radius = 7.5203896f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.402f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.257f, 37.38f)
    lineTo(9.257f, 44.487f)
    cubicTo(5.953f, 44.5f, 1.2680001f, 42.895f, 1.2680001f, 40.933f)
    cubicTo(1.2680001f, 38.970997f, 4.9560003f, 37.379997f, 9.257f, 37.379997f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(9.042419f, 40.903847f), radius = 7.5203896f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.333f, 16.972f)
    lineTo(6.333f, 41.482002f)
    lineTo(43.306f, 41.482002f)
    lineTo(43.244f, 17.090002f)
    cubicTo(43.241f, 15.712002f, 31.396f, 2.4120016f, 29.214f, 2.4120016f)
    lineTo(20.662f, 2.4120016f)
    cubicTo(18.365002f, 2.4120016f, 6.3360004f, 15.674002f, 6.3360004f, 16.972002f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(226, 226, 226, 255), start = Offset(18.428326f, 7.044353f), end = Offset(27.701164f, 37.208755f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(152, 150, 144, 255), 1.0f to Color(101, 100, 96, 255), start = Offset(5.650297f, 0.315f), end = Offset(43.9911f, 0.315f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.857f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.333f, 16.972f)
    lineTo(6.333f, 41.482002f)
    lineTo(43.306f, 41.482002f)
    lineTo(43.244f, 17.090002f)
    cubicTo(43.241f, 15.712002f, 31.396f, 2.4120016f, 29.214f, 2.4120016f)
    lineTo(20.662f, 2.4120016f)
    cubicTo(18.365002f, 2.4120016f, 6.3360004f, 15.674002f, 6.3360004f, 16.972002f)
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
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.923f, 16.787f)
    cubicTo(6.5249996f, 16.357f, 18.81f, 3.0930004f, 20.667f, 3.0930004f)
    lineTo(29.043f, 3.0930004f)
    cubicTo(30.789999f, 3.0930004f, 43.083f, 16.223f, 42.47f, 16.979f)
    lineTo(31.609001f, 30.473999f)
    lineTo(19.295002f, 30.155998f)
    lineTo(6.9230022f, 16.786f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(237, 237, 237, 255), 1.0f to Color(200, 200, 200, 255), start = Offset(12.031887f, 8.955121f), end = Offset(24.03127f, 21.634508f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.08f, 30.02f)
    lineTo(11.747f, 21.274f)
    lineTo(36.565002f, 14.338f)
    lineTo(39.595f, 20.554f)
    lineTo(32.179f, 29.994f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 37))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.292f, 29.836f)
    lineTo(10.809f, 21.026001f)
    lineTo(35.457f, 14.133001f)
    lineTo(38.631f, 20.404001f)
    lineTo(31.39f, 29.811f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 37))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.775f, 29.957f)
    lineTo(11.099999f, 21.297f)
    lineTo(36.068f, 14.227001f)
    lineTo(39.354f, 20.820002f)
    lineTo(31.874f, 29.927002f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 37))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.594f, 30.441f)
    lineTo(11.261f, 21.695f)
    lineTo(35.973f, 14.801f)
    lineTo(39.083f, 21.189f)
    lineTo(31.963001f, 30.175f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(220, 220, 220, 255), start = Offset(21.407753f, 15.920231f), end = Offset(30.735302f, 30.745848f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_7_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.488f, 29.06f)
    lineTo(7.092001f, 40.031998f)
    lineTo(21.001001f, 30.427998f)
    lineTo(30.021002f, 30.427998f)
    lineTo(42.441f, 39.909996f)
    lineTo(30.577002f, 29.059996f)
    lineTo(20.487001f, 29.059996f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(154, 162, 154, 255), 1.0f to Color(181, 190, 181, 255), start = Offset(24.766998f, 28.689402f), end = Offset(27.148151f, 40.03558f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_7_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.963f, 16.885f)
    lineTo(18.479f, 31.201f)
    lineTo(19.547f, 30.347f)
    lineTo(6.9630013f, 16.885f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(154, 162, 154, 255), 1.0f to Color(181, 190, 181, 255), start = Offset(24.766998f, 28.689402f), end = Offset(27.148151f, 40.03558f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_8
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(237, 237, 237, 255), start = Offset(17.83032f, 19.618845f), end = Offset(31.3707f, 34.697895f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.857f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.308f, 17.13f)
    lineTo(7.339f, 40.339996f)
    lineTo(42.284f, 40.339996f)
    lineTo(42.221f, 17.259996f)
    cubicTo(42.219f, 16.509996f, 31.005001f, 3.4609966f, 28.837002f, 3.4609966f)
    lineTo(20.942001f, 3.4609966f)
    cubicTo(18.689001f, 3.4609966f, 7.307001f, 16.352997f, 7.3080015f, 17.132996f)
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
// _0_9
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_9_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.957f, 30.453f)
    lineTo(9.016001f, 38.724f)
    lineTo(11.235001f, 38.73f)
    lineTo(21.233002f, 31.861f)
    lineTo(30.055f, 30.438f)
    lineTo(20.955f, 30.453f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_9_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.428f, 21.67f)
    lineTo(12.752001f, 23.081f)
    lineTo(35.543f, 16.196999f)
    lineTo(38.458f, 21.878998f)
    lineTo(39.072f, 21.166998f)
    lineTo(36.003f, 14.788998f)
    lineTo(11.427998f, 21.669998f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.308f, 23.636f)
    lineTo(19.338f, 30.09f)
    lineTo(20.535f, 29.064f)
    lineTo(30.625f, 29.106998f)
    lineTo(31.437f, 29.833998f)
    lineTo(35.412f, 25.089998f)
    cubicTo(34.258f, 23.678999f, 13.311998f, 23.635998f, 13.311998f, 23.635998f)
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 33), 1.0f to Color(0, 0, 0, 0), start = Offset(23.907223f, 29.856863f), end = Offset(26.259014f, 25.494421f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.813f, 17.848f)
    lineTo(31.861f, 30.479f)
    lineTo(30.793f, 29.624f)
    lineTo(41.813f, 17.848f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(177, 177, 177, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_12
shape = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 25.48000144958496f, top = 0.0f, right = 48.00000190734863f, bottom = 22.520000457763672f))})
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 245, 32, 227), 1.0f to Color(255, 243, 0, 0), center = Offset(36.74f, 11.259f), radius = 11.259998f, tileMode = TileMode.Clamp)
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
            return 1.2680001258850098
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
            return 46.731998443603516
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.48707962036133
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

