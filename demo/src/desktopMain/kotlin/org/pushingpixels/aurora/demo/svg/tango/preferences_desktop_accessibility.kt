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
class preferences_desktop_accessibility : Painter() {
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
brush = Brush.radialGradient(0.0f to Color(156, 188, 222, 255), 1.0f to Color(32, 74, 135, 255), center = Offset(25.159983f, 35.84003f), radius = 43.693687f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
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
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=2.0000021f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=10.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.8954033851623535f, top = 5.004019737243652f, right = 42.99603891372681f, bottom = 42.104655265808105f,radiusX = 7.81350040435791f, radiusY = 7.81350040435791f))
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.5521910190582275f, -0.6401000022888184f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7692310214042664f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7692310214042664f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
6.846158027648926f, 4.576913833618164f, 0.0f, 1.0f)
))}){
// _0_0_3_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.444443941116333f, 0.0f, 0.0f, 0.0f,
0.0f, 1.444443941116333f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.841267108917236f, -5.8095221519470215f, 0.0f, 1.0f)
))}){
// _0_0_3_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.857141f, 13.071428f)
    cubicTo(20.862293f, 14.2232065f, 20.250782f, 15.289703f, 19.254162f, 15.8670845f)
    cubicTo(18.257544f, 16.444466f, 17.02817f, 16.444466f, 16.03155f, 15.8670845f)
    cubicTo(15.03493f, 15.289703f, 14.42342f, 14.2232065f, 14.428571f, 13.071428f)
    cubicTo(14.42342f, 11.91965f, 15.03493f, 10.853153f, 16.03155f, 10.275772f)
    cubicTo(17.02817f, 9.698391f, 18.257544f, 9.698391f, 19.254162f, 10.275772f)
    cubicTo(20.250782f, 10.853153f, 20.862293f, 11.91965f, 20.857141f, 13.071428f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_0_1
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=2.5f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.571428f, 13.142857f)
    lineTo(20.0f, 30.0f)
    lineTo(32.0f, 27.428572f)
    lineTo(34.42857f, 37.0f)
    lineTo(38.0f, 36.0f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.374998f, 21.668259f)
    cubicTo(14.821727f, 23.078575f, 13.0f, 25.694616f, 13.0f, 28.80768f)
    cubicTo(13.0f, 33.36178f, 16.715132f, 37.076912f, 21.26923f, 37.076912f)
    cubicTo(25.584839f, 37.076912f, 29.03297f, 33.705624f, 29.39423f, 29.480759f)
    lineTo(27.399036f, 29.937489f)
    cubicTo(26.85153f, 32.888084f, 24.382202f, 35.153835f, 21.26923f, 35.153835f)
    cubicTo(17.755636f, 35.153835f, 14.923077f, 32.321274f, 14.923077f, 28.80768f)
    cubicTo(14.923077f, 26.611685f, 16.12003f, 24.778076f, 17.807692f, 23.639412f)
    lineTo(17.374998f, 21.668259f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
// _0_0_4
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.9230775f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.857143f, 20.857143f)
    lineTo(31.714287f, 20.0f)
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

