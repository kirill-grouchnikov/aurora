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
class dialog_error : Painter() {
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
alpha *= 0.6f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0705549716949463f, 0.0f, 0.0f, 0.0f,
0.0f, 0.5249999761581421f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.8927549719810486f, 22.5f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.0f, 40.0f)
    cubicTo(41.027473f, 43.071407f, 37.766083f, 45.9154f, 32.45078f, 47.455086f)
    cubicTo(27.135475f, 48.994766f, 20.578812f, 48.994766f, 15.263508f, 47.455086f)
    cubicTo(9.948204f, 45.9154f, 6.6868153f, 43.071407f, 6.714287f, 40.0f)
    cubicTo(6.6868153f, 36.928593f, 9.948204f, 34.0846f, 15.263508f, 32.544914f)
    cubicTo(20.578812f, 31.005232f, 27.135475f, 31.005232f, 32.45078f, 32.544914f)
    cubicTo(37.766083f, 34.0846f, 41.027473f, 36.928593f, 41.0f, 40.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(23.857143f, 40.0f), radius = 17.142857f, tileMode = TileMode.Clamp)
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
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9204879999160767f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9204879999160767f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
2.3685319423675537f, 0.9740800261497498f, 0.0f, 1.0f)
))}){
// _0_1_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(46.857143f, 23.928572f)
    cubicTo(46.894573f, 32.29816f, 42.45093f, 40.048042f, 35.208828f, 44.24368f)
    cubicTo(27.966728f, 48.439316f, 19.033272f, 48.439316f, 11.79117f, 44.24368f)
    cubicTo(4.549069f, 40.048042f, 0.10542657f, 32.29816f, 0.1428566f, 23.928572f)
    cubicTo(0.10542657f, 15.558984f, 4.549069f, 7.8091016f, 11.79117f, 3.6134653f)
    cubicTo(19.033272f, -0.5821709f, 27.966728f, -0.5821709f, 35.208828f, 3.6134653f)
    cubicTo(42.45093f, 7.8091016f, 46.894573f, 15.558984f, 46.857143f, 23.928572f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(164, 0, 0, 255), 1.0f to Color(255, 23, 23, 255), start = Offset(36.917976f, 66.28806f), end = Offset(19.071495f, 5.541011f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(178, 0, 0, 255))
stroke = Stroke(width=1.08638f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(46.857143f, 23.928572f)
    cubicTo(46.894573f, 32.29816f, 42.45093f, 40.048042f, 35.208828f, 44.24368f)
    cubicTo(27.966728f, 48.439316f, 19.033272f, 48.439316f, 11.79117f, 44.24368f)
    cubicTo(4.549069f, 40.048042f, 0.10542657f, 32.29816f, 0.1428566f, 23.928572f)
    cubicTo(0.10542657f, 15.558984f, 4.549069f, 7.8091016f, 11.79117f, 3.6134653f)
    cubicTo(19.033272f, -0.5821709f, 27.966728f, -0.5821709f, 35.208828f, 3.6134653f)
    cubicTo(42.45093f, 7.8091016f, 46.894573f, 15.558984f, 46.857143f, 23.928572f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.34659088f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8560929894447327f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8560929894447327f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.818274974822998f, 0.19776900112628937f, 0.0f, 1.0f)
))}){
// _0_1_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(49.901535f, 26.635273f)
    cubicTo(49.93998f, 35.232037f, 45.375725f, 43.192272f, 37.937054f, 47.50179f)
    cubicTo(30.49838f, 51.811306f, 21.322443f, 51.811306f, 13.883771f, 47.50179f)
    cubicTo(6.445098f, 43.192272f, 1.8808427f, 35.232037f, 1.9192886f, 26.635273f)
    cubicTo(1.8808427f, 18.038511f, 6.445098f, 10.078274f, 13.883771f, 5.7687564f)
    cubicTo(21.322443f, 1.4592386f, 30.49838f, 1.4592386f, 37.937054f, 5.7687564f)
    cubicTo(45.375725f, 10.078274f, 49.93998f, 18.038511f, 49.901535f, 26.635273f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(204, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(255, 230, 155, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(43.93581f, 53.835983f), end = Offset(20.064686f, -8.562671f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1680961f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(49.901535f, 26.635273f)
    cubicTo(49.93998f, 35.232037f, 45.375725f, 43.192272f, 37.937054f, 47.50179f)
    cubicTo(30.49838f, 51.811306f, 21.322443f, 51.811306f, 13.883771f, 47.50179f)
    cubicTo(6.445098f, 43.192272f, 1.8808427f, 35.232037f, 1.9192886f, 26.635273f)
    cubicTo(1.8808427f, 18.038511f, 6.445098f, 10.078274f, 13.883771f, 5.7687564f)
    cubicTo(21.322443f, 1.4592386f, 30.49838f, 1.4592386f, 37.937054f, 5.7687564f)
    cubicTo(45.375725f, 10.078274f, 49.93998f, 18.038511f, 49.901535f, 26.635273f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0058759450912476f, 0.0f, 0.0f, 0.0f,
0.0f, 1.1152009963989258f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.13804499804973602f, -2.3727080821990967f, 0.0f, 1.0f)
))}){
// _0_2_0
shape = Outline.Rectangle(rect = Rect(left = 10.078821182250977f, top = 19.164932250976562f, right = 37.91525650024414f, bottom = 26.338526725769043f))
brush = SolidColor(Color(239, 239, 239, 255))
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
// _0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0029939413070679f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0029939413070679f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.07185874134302139f, 0.019683560356497765f, 0.0f, 1.0f)
))}){
// _0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.370686f, 21.715487f)
    cubicTo(43.370686f, 32.5461f, 33.016357f, 15.449178f, 24.695948f, 22.101873f)
    cubicTo(16.569626f, 28.599384f, 4.098984f, 34.292423f, 4.098984f, 23.461805f)
    cubicTo(4.098984f, 12.377753f, 12.79438f, 2.094803f, 23.625f, 2.094803f)
    cubicTo(34.45562f, 2.094803f, 43.370686f, 10.884868f, 43.370686f, 21.715487f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 254, 255, 85), 1.0f to Color(255, 254, 255, 55), start = Offset(21.993773f, 33.9553f), end = Offset(20.917078f, 15.814602f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
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
            return 1.9998139142990112
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.9037070274353027
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 44.00037384033203
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.09629440307617
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

