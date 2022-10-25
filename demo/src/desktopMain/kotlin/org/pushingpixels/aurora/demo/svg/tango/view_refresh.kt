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
class view_refresh : Painter() {
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
alpha *= 0.38333333f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-1.4897359609603882f, 0.0f, 0.0f, 0.0f,
0.0f, -1.0012520551681519f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
61.208648681640625f, 75.28189849853516f, 0.0f, 1.0f)
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
    moveTo(20.152912f, 10.409904f)
    cubicTo(20.152912f, 10.409904f, 11.215413f, 9.784904f, 13.965413f, 20.284904f)
    lineTo(6.277913f, 20.284904f)
    cubicTo(6.277913f, 20.284904f, 6.777913f, 8.409904f, 20.152912f, 10.409904f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(52, 101, 164, 255), 0.33333334f to Color(91, 134, 190, 255), 1.0f to Color(131, 168, 216, 0), start = Offset(13.943967f, 10.334614f), end = Offset(15.88483f, 18.837528f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(52, 101, 164, 255), 1.0f to Color(52, 101, 164, 0), start = Offset(10.399857f, 16.117804f), end = Offset(10.462494f, 19.653337f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.152912f, 10.409904f)
    cubicTo(20.152912f, 10.409904f, 11.215413f, 9.784904f, 13.965413f, 20.284904f)
    lineTo(6.277913f, 20.284904f)
    cubicTo(6.277913f, 20.284904f, 6.777913f, 8.409904f, 20.152912f, 10.409904f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.5790510177612305f, -0.4892280101776123f, 0.0f, 0.0f,
-0.4892280101776123f, 0.5790510177612305f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
56.91585159301758f, 13.371370315551758f, 0.0f, 1.0f)
))}){
// _0_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.30678f, 50.229694f)
    cubicTo(62.8215f, 35.81886f, 49.664585f, 13.411704f, 22.462412f, 12.49765f)
    lineTo(22.113844f, 3.151548f)
    lineTo(7.624544f, 20.496754f)
    lineTo(22.714329f, 33.21919f)
    cubicTo(22.714329f, 33.21919f, 22.462412f, 23.337969f, 22.462412f, 23.337969f)
    cubicTo(41.29217f, 24.336946f, 55.44404f, 37.4097f, 44.30678f, 50.229694f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(185, 207, 231, 255), 1.0f to Color(114, 159, 207, 255), start = Offset(62.513836f, 36.061237f), end = Offset(15.984863f, 20.60858f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(52, 101, 164, 255), 1.0f to Color(52, 101, 164, 255), start = Offset(46.834816f, 45.264122f), end = Offset(45.380436f, 50.939667f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.3191693f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.30678f, 50.229694f)
    cubicTo(62.8215f, 35.81886f, 49.664585f, 13.411704f, 22.462412f, 12.49765f)
    lineTo(22.113844f, 3.151548f)
    lineTo(7.624544f, 20.496754f)
    lineTo(22.714329f, 33.21919f)
    cubicTo(22.714329f, 33.21919f, 22.462412f, 23.337969f, 22.462412f, 23.337969f)
    cubicTo(41.29217f, 24.336946f, 55.44404f, 37.4097f, 44.30678f, 50.229694f)
    close()
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.375f, 33.4375f)
    cubicTo(28.375f, 33.4375f, 37.3125f, 34.0625f, 34.5625f, 23.5625f)
    lineTo(42.338387f, 23.5625f)
    cubicTo(42.338387f, 25.065102f, 41.75f, 35.4375f, 28.375f, 33.4375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(196, 215, 235, 255), 1.0f to Color(196, 215, 235, 0), start = Offset(32.647972f, 30.748846f), end = Offset(37.124462f, 24.842253f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(57, 119, 195, 255), 1.0f to Color(137, 174, 220, 0), start = Offset(36.713837f, 31.455952f), end = Offset(37.124462f, 24.842253f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.375f, 33.4375f)
    cubicTo(28.375f, 33.4375f, 37.3125f, 34.0625f, 34.5625f, 23.5625f)
    lineTo(42.338387f, 23.5625f)
    cubicTo(42.338387f, 25.065102f, 41.75f, 35.4375f, 28.375f, 33.4375f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5790510177612305f, 0.4892280101776123f, 0.0f, 0.0f,
0.4892280101776123f, -0.5790510177612305f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.921022891998291f, 30.53598976135254f, 0.0f, 1.0f)
))}){
// _0_0_4
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.30678f, 50.229694f)
    cubicTo(62.8215f, 35.81886f, 49.664585f, 13.411704f, 22.462412f, 12.49765f)
    lineTo(22.399431f, 3.0690298f)
    lineTo(7.793943f, 20.424006f)
    lineTo(22.462412f, 33.006348f)
    cubicTo(22.462412f, 33.006348f, 22.462412f, 23.337969f, 22.462412f, 23.337969f)
    cubicTo(41.29217f, 24.336946f, 55.44404f, 37.4097f, 44.30678f, 50.229694f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 159, 207, 255), 1.0f to Color(82, 138, 197, 255), start = Offset(18.935766f, 23.667896f), end = Offset(53.588623f, 26.649363f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(52, 101, 164, 255), 1.0f to Color(52, 101, 164, 255), start = Offset(46.834816f, 45.264122f), end = Offset(45.380436f, 50.939667f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.3191693f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.30678f, 50.229694f)
    cubicTo(62.8215f, 35.81886f, 49.664585f, 13.411704f, 22.462412f, 12.49765f)
    lineTo(22.399431f, 3.0690298f)
    lineTo(7.793943f, 20.424006f)
    lineTo(22.462412f, 33.006348f)
    cubicTo(22.462412f, 33.006348f, 22.462412f, 23.337969f, 22.462412f, 23.337969f)
    cubicTo(41.29217f, 24.336946f, 55.44404f, 37.4097f, 44.30678f, 50.229694f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.27222225f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.0625f, 38.1875f)
    lineTo(7.125f, 23.3125f)
    lineTo(20.0625f, 22.9375f)
    lineTo(15.673627f, 28.116318f)
    lineTo(19.540852f, 30.489515f)
    cubicTo(16.540852f, 32.739517f, 14.991304f, 32.911644f, 13.991304f, 35.474144f)
    lineTo(11.174446f, 33.363873f)
    lineTo(7.0625f, 38.1875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.5085359811782837f, 0.4296509921550751f, 0.0f, 0.0f,
0.4296509921550751f, -0.5085359811782837f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.9731879234313965f, 30.541189193725586f, 0.0f, 1.0f)
))}){
// _0_0_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6_0
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(5.9649177f, 26.048164f), end = Offset(52.854095f, 26.048164f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.5020893f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(51.090263f, 45.943707f)
    cubicTo(60.210464f, 30.723955f, 46.631615f, 12.20113f, 19.485058f, 11.948579f)
    lineTo(19.513464f, 3.7032833f)
    lineTo(6.534198f, 19.296638f)
    lineTo(19.36766f, 30.26876f)
    cubicTo(19.36766f, 30.26876f, 19.42328f, 21.261883f, 19.42328f, 21.261883f)
    cubicTo(36.951096f, 21.037973f, 54.618465f, 31.365253f, 51.090263f, 45.943707f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
-0.5085359811782837f, -0.4296509921550751f, 0.0f, 0.0f,
-0.4296509921550751f, 0.5085359811782837f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
53.04899978637695f, 13.365480422973633f, 0.0f, 1.0f)
))}){
// _0_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7_0
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(5.9649177f, 26.048164f), end = Offset(52.854095f, 26.048164f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.5020893f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(51.389927f, 46.505947f)
    cubicTo(60.510128f, 31.286196f, 47.050762f, 12.432359f, 19.628483f, 12.069755f)
    lineTo(19.342825f, 4.05072f)
    lineTo(6.341309f, 19.379475f)
    lineTo(19.80906f, 30.76459f)
    cubicTo(19.80906f, 30.76459f, 19.627295f, 21.311346f, 19.627295f, 21.311346f)
    cubicTo(37.87223f, 21.693317f, 54.411175f, 32.23659f, 51.389927f, 46.505947f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.27222225f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.8125f, 16.5f)
    cubicTo(10.405935f, 6.0587273f, 23.256283f, 10.355393f, 27.0f, 12.0f)
    cubicTo(31.175306f, 12.211475f, 32.674736f, 9.164996f, 36.0f, 9.0f)
    cubicTo(21.950264f, -0.7899963f, 7.1875f, 2.5f, 6.8125f, 16.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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
            return 0.0
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
            return 48.0
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

