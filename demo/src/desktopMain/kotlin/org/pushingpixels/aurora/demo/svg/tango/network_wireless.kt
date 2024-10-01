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
class network_wireless : Painter() {
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
alpha *= 0.4064171f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
2.4600489139556885f, 0.0f, 0.0f, 0.0f,
0.0f, 2.4600489139556885f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-49.40945816040039f, -67.96373748779297f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.769554f, 44.565483f)
    cubicTo(36.780075f, 45.361816f, 35.53091f, 46.099186f, 33.495064f, 46.498383f)
    cubicTo(31.459217f, 46.89758f, 28.947906f, 46.89758f, 26.91206f, 46.498383f)
    cubicTo(24.876213f, 46.099186f, 23.627047f, 45.361816f, 23.63757f, 44.565483f)
    cubicTo(23.627047f, 43.76915f, 24.876213f, 43.03178f, 26.91206f, 42.632584f)
    cubicTo(28.947906f, 42.233387f, 31.459217f, 42.233387f, 33.495064f, 42.632584f)
    cubicTo(35.53091f, 43.03178f, 36.780075f, 43.76915f, 36.769554f, 44.565483f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(30.203562f, 44.565502f), radius = 6.5659924f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 4.414728164672852f, top = 3.5233452320098877f, right = 44.47665214538574f, bottom = 43.58526921272278f,radiusX = 10.909647941589355f, radiusY = 10.909647941589355f))
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(220, 220, 220, 255), center = Offset(24.445688f, 35.878155f), radius = 40.960464f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(155, 155, 155, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 4.414728164672852f, top = 3.5233452320098877f, right = 44.47665214538574f, bottom = 43.58526921272278f,radiusX = 10.909647941589355f, radiusY = 10.909647941589355f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.224903f, 38.95567f)
    lineTo(24.819939f, 24.359674f)
    lineTo(30.135763f, 38.95567f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6892200112342834f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6892200112342834f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
5.768155097961426f, 11.069000244140625f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.910667f, 18.60456f)
    cubicTo(30.91601f, 19.799059f, 30.281818f, 20.905113f, 29.248234f, 21.503908f)
    cubicTo(28.21465f, 22.102705f, 26.939678f, 22.102705f, 25.906094f, 21.503908f)
    cubicTo(24.872509f, 20.905113f, 24.238317f, 19.799059f, 24.24366f, 18.60456f)
    cubicTo(24.238317f, 17.410063f, 24.872509f, 16.304008f, 25.906094f, 15.705213f)
    cubicTo(26.939678f, 15.106417f, 28.21465f, 15.106417f, 29.248234f, 15.705213f)
    cubicTo(30.281818f, 16.304008f, 30.91601f, 17.410063f, 30.910667f, 18.60456f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(239, 41, 41, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
2.38230299949646f, 0.0f, 0.0f, 0.0f,
0.0f, 2.38230299949646f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-40.92229080200195f, -20.430070877075195f, 0.0f, 1.0f)
))}){
// _0_0_4
brush = Brush.radialGradient(0.0f to Color(239, 41, 41, 255), 1.0f to Color(239, 41, 41, 0), center = Offset(27.577166f, 16.213495f), radius = 4.7667828f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1812764f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.910667f, 18.60456f)
    cubicTo(30.91601f, 19.799059f, 30.281818f, 20.905113f, 29.248234f, 21.503908f)
    cubicTo(28.21465f, 22.102705f, 26.939678f, 22.102705f, 25.906094f, 21.503908f)
    cubicTo(24.872509f, 20.905113f, 24.238317f, 19.799059f, 24.24366f, 18.60456f)
    cubicTo(24.238317f, 17.410063f, 24.872509f, 16.304008f, 25.906094f, 15.705213f)
    cubicTo(26.939678f, 15.106417f, 28.21465f, 15.106417f, 29.248234f, 15.705213f)
    cubicTo(30.281818f, 16.304008f, 30.91601f, 17.410063f, 30.910667f, 18.60456f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
4.6576080322265625f, 0.0f, 0.0f, 0.0f,
0.0f, 4.6576080322265625f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-103.66899871826172f, -62.761131286621094f, 0.0f, 1.0f)
))}){
// _0_0_5
brush = Brush.radialGradient(0.0f to Color(239, 41, 41, 255), 1.0f to Color(239, 41, 41, 0), center = Offset(27.577166f, 14.968954f), radius = 4.7667828f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.60420674f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.910667f, 18.60456f)
    cubicTo(30.91601f, 19.799059f, 30.281818f, 20.905113f, 29.248234f, 21.503908f)
    cubicTo(28.21465f, 22.102705f, 26.939678f, 22.102705f, 25.906094f, 21.503908f)
    cubicTo(24.872509f, 20.905113f, 24.238317f, 19.799059f, 24.24366f, 18.60456f)
    cubicTo(24.238317f, 17.410063f, 24.872509f, 16.304008f, 25.906094f, 15.705213f)
    cubicTo(26.939678f, 15.106417f, 28.21465f, 15.106417f, 29.248234f, 15.705213f)
    cubicTo(30.281818f, 16.304008f, 30.91601f, 17.410063f, 30.910667f, 18.60456f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.1764706f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.5677410364151f, 0.0f, 0.0f, 0.0f,
0.0f, 1.5677410364151f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-22.256559371948242f, -31.995590209960938f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.769554f, 44.565483f)
    cubicTo(36.780075f, 45.361816f, 35.53091f, 46.099186f, 33.495064f, 46.498383f)
    cubicTo(31.459217f, 46.89758f, 28.947906f, 46.89758f, 26.91206f, 46.498383f)
    cubicTo(24.876213f, 46.099186f, 23.627047f, 45.361816f, 23.63757f, 44.565483f)
    cubicTo(23.627047f, 43.76915f, 24.876213f, 43.03178f, 26.91206f, 42.632584f)
    cubicTo(28.947906f, 42.233387f, 31.459217f, 42.233387f, 33.495064f, 42.632584f)
    cubicTo(35.53091f, 43.03178f, 36.780075f, 43.76915f, 36.769554f, 44.565483f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(30.203562f, 44.565502f), radius = 6.5659924f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.812107f, 28.571856f)
    lineTo(26.719572f, 29.426992f)
    lineTo(22.44389f, 32.334457f)
    lineTo(28.258818f, 33.873703f)
    lineTo(21.07567f, 36.781166f)
    lineTo(29.627037f, 37.636303f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.597388744354248f, top = 4.70600700378418f, right = 43.29397535324097f, bottom = 42.4025936126709f,radiusX = 8.485278129577637f, radiusY = 8.485278129577637f))
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
            return 3.9147281646728516
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.0233452320098877
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 41.06192398071289
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.13774490356445
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

