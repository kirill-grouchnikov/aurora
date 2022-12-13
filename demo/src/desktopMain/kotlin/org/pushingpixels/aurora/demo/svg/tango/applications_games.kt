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
class applications_games : Painter() {
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
alpha *= 0.38636363f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.408115029335022f, 0.0f, 0.0f, 0.0f,
0.0f, 0.4929580092430115f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.856804847717285f, 28.288719177246094f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.142857f, 30.857143f)
    cubicTo(38.16781f, 34.491642f, 35.205383f, 37.857037f, 30.377314f, 39.678993f)
    cubicTo(25.549248f, 41.500954f, 19.593609f, 41.500954f, 14.765542f, 39.678993f)
    cubicTo(9.937474f, 37.857037f, 6.9750466f, 34.491642f, 7.0f, 30.857143f)
    cubicTo(6.9750466f, 27.222643f, 9.937474f, 23.85725f, 14.765542f, 22.035294f)
    cubicTo(19.593609f, 20.213335f, 25.549248f, 20.213335f, 30.377314f, 22.035294f)
    cubicTo(35.205383f, 23.85725f, 38.16781f, 27.222643f, 38.142857f, 30.857143f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(22.571428f, 30.857143f), radius = 15.571428f, tileMode = TileMode.Clamp)
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
0.9802799820899963f, -0.19761699438095093f, 0.0f, 0.0f,
0.19761699438095093f, 0.9802799820899963f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.728349685668945f, 1.0851049423217773f, 0.0f, 1.0f)
))}){
// _0_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9653639793395996f, 0.26090800762176514f, 0.0f, 0.0f,
-0.26090800762176514f, 0.9653639793395996f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
11.349209785461426f, -0.5226759910583496f, 0.0f, 1.0f)
))}){
// _0_0_1_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9653840065002441f, -0.2608329951763153f, 0.0f, 0.0f,
0.2608329951763153f, 0.9653840065002441f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_0_0
shape = Outline.Rounded(roundRect = RoundRect(left = -1.9805312156677246f, top = 8.210731506347656f, right = 28.949829578399658f, bottom = 47.24335861206055f,radiusX = 6.060927867889404f, radiusY = 5.656864643096924f))
brush = Brush.linearGradient(0.0f to Color(229, 229, 229, 255), 1.0f to Color(200, 200, 200, 255), start = Offset(19.18689f, 25.82524f), end = Offset(26.975807f, 43.643013f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0000017f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = -1.9805312156677246f, top = 8.210731506347656f, right = 28.949829578399658f, bottom = 47.24335861206055f,radiusX = 6.060927867889404f, radiusY = 5.656864643096924f))
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
0.9653760194778442f, -0.2608639895915985f, 0.0f, 0.0f,
0.2608030140399933f, 0.965391993522644f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = -1.0130478143692017f, top = 9.101876258850098f, right = 27.98428761959076f, bottom = 46.25208759307861f,radiusX = 4.4699506759643555f, radiusY = 3.8891007900238037f))
brush = Brush.linearGradient(0.0f to Color(229, 229, 229, 255), 1.0f to Color(200, 200, 200, 255), start = Offset(15.868883f, 35.902477f), end = Offset(42.592857f, 63.782047f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000025f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = -1.0130478143692017f, top = 9.101876258850098f, right = 27.98428761959076f, bottom = 46.25208759307861f,radiusX = 4.4699506759643555f, radiusY = 3.8891007900238037f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.51648355f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.1767760068178177f, 7.24783992767334f, 0.0f, 1.0f)
))}){
// _0_0_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.130389f, 22.794182f)
    cubicTo(22.980885f, 26.961538f, 29.3674f, 17.052488f, 40.601906f, 22.794182f)
    lineTo(38.721928f, 3.5888283f)
    cubicTo(38.721928f, 3.5888283f, 38.11114f, 1.4341087f, 36.70666f, 1.6919748f)
    lineTo(12.249217f, 1.922697f)
    cubicTo(10.654669f, 1.781581f, 10.44653f, 2.8886232f, 10.479394f, 4.067412f)
    lineTo(10.130389f, 22.794182f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(10.130398f, 12.876509f), end = Offset(40.60191f, 12.876509f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
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
    moveTo(17.6875f, 12.28125f)
    cubicTo(14.13478f, 15.630644f, 12.705188f, 16.554443f, 12.6875f, 18.4375f)
    cubicTo(12.670309f, 20.26803f, 14.981028f, 21.868397f, 17.0f, 20.71875f)
    cubicTo(17.064192f, 21.65549f, 17.094534f, 23.274815f, 16.53125f, 23.9375f)
    cubicTo(16.61964f, 23.937502f, 19.0f, 23.96875f, 19.0f, 23.96875f)
    cubicTo(18.999998f, 23.968752f, 18.387556f, 21.93878f, 18.3125f, 20.59375f)
    cubicTo(20.52189f, 21.985624f, 22.669676f, 20.474531f, 22.6875f, 18.34375f)
    cubicTo(22.706844f, 16.109568f, 21.406126f, 15.397146f, 17.6875f, 12.28125f)
    close()
}
shape = Outline.Generic(generalPath!!)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.0f, 0.25f, 0.0f, 1.0f)
))}){
// _0_0_2
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
0.0f, -1.875f, 0.0f, 1.0f)
))}){
// _0_0_2_0
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
2.5f, 2.75f, 0.0f, 1.0f)
))}){
// _0_0_2_0_0
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
0.1767760068178177f, 7.24783992767334f, 0.0f, 1.0f)
))}){
// _0_0_2_0_0_0
alphaStack.add(0, alpha)
alpha *= 0.51648355f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0_0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.249051f, 13.68551f)
    cubicTo(22.267511f, 21.50188f, 31.322859f, 13.953532f, 40.328777f, 22.789518f)
    lineTo(45.504536f, 4.778087f)
    lineTo(44.31062f, 2.2533867f)
    lineTo(19.506897f, -5.5996203f)
    lineTo(16.789787f, -4.294419f)
    lineTo(11.249051f, 13.68551f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(14.212175f, 4.220833f), end = Offset(43.291893f, 13.324844f), tileMode = TileMode.Clamp)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9664639830589294f, 0.2568039894104004f, 0.0f, 0.0f,
-0.2568039894104004f, 0.9664639830589294f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2_0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 16.60003089904785f, top = -3.979738712310791f, right = 47.53040313720703f, bottom = 35.0528998374939f,radiusX = 6.060929775238037f, radiusY = 5.656866550445557f))
brush = Brush.linearGradient(0.0f to Color(229, 229, 229, 255), 1.0f to Color(200, 200, 200, 255), start = Offset(31.668783f, 15.403031f), end = Offset(46.440422f, 33.75061f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0000019f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 16.60003089904785f, top = -3.979738712310791f, right = 47.53040313720703f, bottom = 35.0528998374939f,radiusX = 6.060929775238037f, radiusY = 5.656866550445557f))
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
0.9664720296859741f, 0.2567729949951172f, 0.0f, 0.0f,
-0.2568340003490448f, 0.9664559960365295f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2_0_0_2
shape = Outline.Rounded(roundRect = RoundRect(left = 17.567136764526367f, top = -3.087998390197754f, right = 46.56447219848633f, bottom = 34.06225872039795f,radiusX = 4.469949722290039f, radiusY = 3.889106273651123f))
brush = Brush.linearGradient(0.0f to Color(229, 229, 229, 255), 1.0f to Color(223, 223, 223, 255), start = Offset(30.622723f, 6.8805733f), end = Offset(44.96019f, 37.79245f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0000031f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 17.567136764526367f, top = -3.087998390197754f, right = 46.56447219848633f, bottom = 34.06225872039795f,radiusX = 4.469949722290039f, radiusY = 3.889106273651123f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(28.21921f, 18.57943f)
    cubicTo(32.016663f, 16.34947f, 35.37125f, 20.241547f, 34.574306f, 23.107834f)
    cubicTo(33.84576f, 25.728115f, 31.535908f, 26.45757f, 25.327822f, 29.802046f)
    cubicTo(21.304976f, 24.065321f, 19.745802f, 22.573204f, 20.607912f, 19.463713f)
    cubicTo(21.49938f, 16.244665f, 25.660599f, 14.764478f, 28.21921f, 18.57943f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(204, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.51648355f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.408724f, 24.666098f)
    cubicTo(26.878572f, 31.803663f, 32.987286f, 22.750612f, 42.50975f, 31.027325f)
    lineTo(47.031673f, 13.797161f)
    cubicTo(47.29684f, 12.824889f, 47.01348f, 12.056174f, 45.59927f, 11.70262f)
    lineTo(22.57675f, 5.2715507f)
    cubicTo(21.250923f, 4.9179974f, 20.120085f, 5.8543754f, 19.943308f, 6.738259f)
    lineTo(15.408724f, 24.666098f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(26.63446f, 7.3501387f), end = Offset(33.671352f, 40.404648f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 0.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.07118754088878632
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
            return 47.92881393432617
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

