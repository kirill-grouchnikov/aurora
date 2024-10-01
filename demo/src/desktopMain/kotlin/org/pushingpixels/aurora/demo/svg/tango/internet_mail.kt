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
class internet_mail : Painter() {
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
alpha *= 0.4557f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.8006000518798828f, 0.0f, 0.0f, 0.0f,
0.0f, 1.9747999906539917f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.083899974822998f, -38.01300048828125f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.5f, 38.7f)
    cubicTo(26.522034f, 40.061657f, 23.90613f, 41.322495f, 19.642813f, 42.00509f)
    cubicTo(15.379496f, 42.68768f, 10.120504f, 42.68768f, 5.8571877f, 42.00509f)
    cubicTo(1.5938711f, 41.322495f, -1.0220345f, 40.061657f, -1.0f, 38.7f)
    cubicTo(-1.0220345f, 37.338345f, 1.5938711f, 36.077507f, 5.8571877f, 35.394913f)
    cubicTo(10.120504f, 34.712322f, 15.379496f, 34.712322f, 19.642813f, 35.394913f)
    cubicTo(23.90613f, 36.077507f, 26.522034f, 37.338345f, 26.5f, 38.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(12.749876f, 38.69993f), radius = 13.749865f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.3334f, 16.972f)
    lineTo(6.3334f, 41.482002f)
    lineTo(43.3064f, 41.482002f)
    lineTo(43.2444f, 17.090002f)
    cubicTo(43.2414f, 15.712002f, 31.3964f, 2.4120016f, 29.2114f, 2.4120016f)
    lineTo(20.659399f, 2.4121015f)
    cubicTo(18.3624f, 2.4121015f, 6.333399f, 15.674102f, 6.333399f, 16.972101f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(226, 226, 226, 255), start = Offset(18.427826f, 7.044237f), end = Offset(27.700663f, 37.21261f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(152, 150, 144, 255), 1.0f to Color(101, 100, 96, 255), start = Offset(5.6496625f, 21.9475f), end = Offset(43.9906f, 21.9475f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.8566f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.3334f, 16.972f)
    lineTo(6.3334f, 41.482002f)
    lineTo(43.3064f, 41.482002f)
    lineTo(43.2444f, 17.090002f)
    cubicTo(43.2414f, 15.712002f, 31.3964f, 2.4120016f, 29.2114f, 2.4120016f)
    lineTo(20.659399f, 2.4121015f)
    cubicTo(18.3624f, 2.4121015f, 6.333399f, 15.674102f, 6.333399f, 16.972101f)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.9231f, 16.787f)
    cubicTo(6.525f, 16.357f, 18.8101f, 3.0930004f, 20.6671f, 3.0930004f)
    lineTo(29.043102f, 3.0935004f)
    cubicTo(30.790102f, 3.0935004f, 43.0801f, 16.2215f, 42.4701f, 16.9795f)
    lineTo(31.6091f, 30.474499f)
    lineTo(19.295101f, 30.156498f)
    lineTo(6.9231014f, 16.786499f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(237, 237, 237, 255), 1.0f to Color(200, 200, 200, 255), start = Offset(12.030758f, 8.95524f), end = Offset(24.03077f, 21.634508f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.078f, 30.018f)
    lineTo(11.744999f, 21.272f)
    lineTo(36.563f, 14.3359995f)
    lineTo(39.592f, 20.552f)
    lineTo(32.176f, 29.992f)
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
// _0_0_4
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.775f, 29.957f)
    lineTo(11.099999f, 21.297f)
    lineTo(36.068f, 14.232f)
    lineTo(39.354f, 20.825f)
    lineTo(31.874f, 29.932001f)
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
// _0_0_6
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(220, 220, 220, 255), start = Offset(21.407251f, 15.918174f), end = Offset(30.734802f, 30.745848f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.488f, 29.064f)
    lineTo(7.092001f, 40.036f)
    lineTo(21.001001f, 30.432f)
    lineTo(30.019001f, 30.432f)
    lineTo(42.439003f, 39.914f)
    lineTo(30.575003f, 29.064001f)
    lineTo(20.488003f, 29.064001f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(154, 162, 154, 255), 1.0f to Color(181, 190, 181, 255), start = Offset(24.765512f, 28.687115f), end = Offset(27.146423f, 40.036343f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.9635f, 16.885f)
    lineTo(18.4795f, 31.201f)
    lineTo(19.5475f, 30.347f)
    lineTo(6.963501f, 16.885f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(154, 162, 154, 255), 1.0f to Color(181, 190, 181, 255), start = Offset(24.765512f, 28.687115f), end = Offset(27.146423f, 40.036343f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(237, 237, 237, 255), start = Offset(17.83042f, 19.619055f), end = Offset(31.373539f, 34.702435f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.8566f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.3077f, 17.131f)
    lineTo(7.3389f, 40.342003f)
    lineTo(42.2839f, 40.342003f)
    lineTo(42.2209f, 17.258003f)
    cubicTo(42.218903f, 16.508003f, 31.004902f, 3.4590034f, 28.836903f, 3.4590034f)
    lineTo(20.941902f, 3.4592035f)
    cubicTo(18.688902f, 3.4592035f, 7.306902f, 16.351204f, 7.3079023f, 17.131203f)
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
// _0_0_10
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
    lineTo(20.957f, 30.453f)
    close()
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
// _0_0_11
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
    close()
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
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.308f, 23.636f)
    lineTo(19.334f, 30.09f)
    lineTo(20.531f, 29.064f)
    lineTo(30.618f, 29.106998f)
    lineTo(31.43f, 29.833998f)
    lineTo(35.405f, 25.089998f)
    cubicTo(34.251f, 23.678999f, 13.307999f, 23.635998f, 13.307999f, 23.635998f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 33), 1.0f to Color(0, 0, 0, 0), start = Offset(23.90768f, 29.855352f), end = Offset(26.258274f, 25.495474f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
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
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(177, 177, 177, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 0.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.9837015867233276
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 48.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 43.9659309387207
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

