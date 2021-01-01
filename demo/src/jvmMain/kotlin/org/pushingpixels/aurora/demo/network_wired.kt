package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.toComposeBitmap
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class network_wired private constructor(var _width: Int, var _height: Int) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 0.40641713f
withTransform({
transform(
Matrix(values=floatArrayOf(
2.7527360916137695f, -2.4554219245910645f, 0.0f, 48.40044021606445f,
1.438439965248108f, 4.679605007171631f, 0.0f, -223.04119873046875f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(36.769554f, 44.565483f)
generalPath!!.cubicTo(36.780075f, 45.361816f, 35.53091f, 46.099186f, 33.495064f, 46.498383f)
generalPath!!.cubicTo(31.459217f, 46.89758f, 28.947906f, 46.89758f, 26.91206f, 46.498383f)
generalPath!!.cubicTo(24.876213f, 46.099186f, 23.627047f, 45.361816f, 23.63757f, 44.565483f)
generalPath!!.cubicTo(23.627047f, 43.76915f, 24.876213f, 43.03178f, 26.91206f, 42.632584f)
generalPath!!.cubicTo(28.947906f, 42.233387f, 31.459217f, 42.233387f, 33.495064f, 42.632584f)
generalPath!!.cubicTo(35.53091f, 43.03178f, 36.780075f, 43.76915f, 36.769554f, 44.565483f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(30.203562f, 44.565502f), radius = 6.5659924f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(12.80097f, 5.8182116f)
generalPath!!.lineTo(5.1577077f, 12.516803f)
generalPath!!.lineTo(5.4153447f, 18.657177f)
generalPath!!.lineTo(23.407072f, 34.845436f)
generalPath!!.lineTo(35.000786f, 24.110516f)
generalPath!!.lineTo(35.258423f, 17.411926f)
generalPath!!.lineTo(12.80097f, 5.8182116f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(72, 72, 72, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(77, 77, 77, 255))
stroke = Stroke(width=1.9999999f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(12.80097f, 5.8182116f)
generalPath!!.lineTo(5.1577077f, 12.516803f)
generalPath!!.lineTo(5.4153447f, 18.657177f)
generalPath!!.lineTo(23.407072f, 34.845436f)
generalPath!!.lineTo(35.000786f, 24.110516f)
generalPath!!.lineTo(35.258423f, 17.411926f)
generalPath!!.lineTo(12.80097f, 5.8182116f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0704026f, 12.658182f)
generalPath!!.lineTo(5.317116f, 18.579296f)
generalPath!!.lineTo(23.57389f, 34.61565f)
generalPath!!.lineTo(23.327177f, 28.20111f)
generalPath!!.lineTo(5.0704026f, 12.658182f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(166, 166, 166, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.327177f, 28.20111f)
generalPath!!.lineTo(23.327177f, 34.73901f)
generalPath!!.lineTo(34.922695f, 24.006987f)
generalPath!!.lineTo(35.169407f, 17.345732f)
generalPath!!.lineTo(23.327177f, 28.20111f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(127, 127, 127, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.0704026f, 12.669106f)
generalPath!!.lineTo(23.57389f, 28.447823f)
generalPath!!.lineTo(35.169407f, 17.592443f)
generalPath!!.lineTo(12.71851f, 5.750214f)
generalPath!!.lineTo(5.0704026f, 12.669106f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(229, 229, 229, 255), start = Offset(5.0704193f, 17.099028f), end = Offset(35.169434f, 17.099028f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(6.2664924f, 11.644777f)
generalPath!!.lineTo(9.1411705f, 13.645034f)
generalPath!!.cubicTo(9.1411705f, 13.645034f, 9.819632f, 13.891747f, 10.128023f, 13.645034f)
generalPath!!.cubicTo(10.436414f, 13.398322f, 10.374736f, 12.658182f, 10.374736f, 12.658182f)
generalPath!!.lineTo(7.500058f, 10.400285f)
generalPath!!.lineTo(6.2664924f, 11.644777f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 193, 26, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.401031f, 9.671071f)
generalPath!!.lineTo(11.361588f, 11.671328f)
generalPath!!.cubicTo(11.361588f, 11.671328f, 12.040051f, 11.918041f, 12.348442f, 11.671328f)
generalPath!!.cubicTo(12.656831f, 11.424616f, 12.595154f, 10.684476f, 12.595154f, 10.684476f)
generalPath!!.lineTo(9.720477f, 8.426579f)
generalPath!!.lineTo(8.401031f, 9.671071f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 193, 26, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(10.583973f, 7.7348437f)
generalPath!!.lineTo(13.458651f, 9.820981f)
generalPath!!.cubicTo(13.458651f, 9.820981f, 14.137112f, 10.067694f, 14.445505f, 9.820981f)
generalPath!!.cubicTo(14.753896f, 9.574267f, 14.692216f, 8.834126f, 14.692216f, 8.834126f)
generalPath!!.lineTo(11.731658f, 6.4903526f)
generalPath!!.lineTo(10.583973f, 7.7348437f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 193, 26, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.34224597f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(12.829749f, 6.368914f)
generalPath!!.lineTo(5.747854f, 13.004915f)
generalPath!!.lineTo(5.9865694f, 18.350786f)
generalPath!!.lineTo(23.25803f, 33.86536f)
generalPath!!.lineTo(34.34375f, 23.57536f)
generalPath!!.lineTo(34.582466f, 17.96991f)
generalPath!!.lineTo(12.829749f, 6.368914f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_9
brush = Brush.linearGradient(0.0f to Color(81, 81, 82, 255), 1.0f to Color(81, 81, 82, 0), start = Offset(37.490067f, 31.431116f), end = Offset(42.04167f, 38.301468f), tileMode = TileMode.Clamp)
stroke = Stroke(width=5.0f, cap=StrokeCap.Round, join=StrokeJoin.Bevel, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(31.564354f, 28.210627f)
generalPath!!.cubicTo(31.564354f, 28.210627f, 37.40415f, 30.52937f, 40.495808f, 35.252735f)
generalPath!!.cubicTo(43.587463f, 39.976097f, 45.30505f, 46.846447f, 45.30505f, 46.846447f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(32.200706f, 26.732643f)
generalPath!!.cubicTo(31.367882f, 26.833529f, 30.70943f, 27.486206f, 30.601215f, 28.318111f)
generalPath!!.cubicTo(30.492998f, 29.150019f, 30.962563f, 29.94943f, 31.741863f, 30.260021f)
generalPath!!.cubicTo(31.741863f, 30.260021f, 36.6252f, 32.925232f, 38.26074f, 35.423973f)
generalPath!!.cubicTo(40.0495f, 38.156803f, 41.243237f, 42.622124f, 41.243237f, 42.622124f)
generalPath!!.cubicTo(41.49665f, 43.6041f, 42.498135f, 44.194717f, 43.480114f, 43.941303f)
generalPath!!.cubicTo(44.46209f, 43.68789f, 45.052708f, 42.686405f, 44.799294f, 41.70443f)
generalPath!!.cubicTo(44.799294f, 41.70443f, 43.698803f, 36.992825f, 41.35795f, 33.416523f)
generalPath!!.cubicTo(38.863876f, 29.60613f, 33.1184f, 26.876032f, 33.1184f, 26.876032f)
generalPath!!.cubicTo(32.829857f, 26.750353f, 32.51384f, 26.700975f, 32.200706f, 26.732643f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(204, 204, 205, 255), 1.0E-9f to Color(173, 173, 174, 255), 1.0f to Color(143, 143, 144, 0), center = Offset(37.855324f, 32.03591f), radius = 6.401171f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(15.925782f, 15.865453f)
generalPath!!.lineTo(30.975286f, 27.214258f)
generalPath!!.cubicTo(32.70228f, 28.324467f, 34.36759f, 27.029222f, 35.416122f, 25.733978f)
generalPath!!.cubicTo(36.464653f, 24.438734f, 36.279617f, 22.896778f, 35.169407f, 22.03328f)
generalPath!!.lineTo(19.133053f, 13.39832f)
generalPath!!.lineTo(15.925782f, 15.865453f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(160, 160, 161, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(15.309f, 15.865453f)
generalPath!!.lineTo(30.72857f, 25.240553f)
generalPath!!.cubicTo(31.592068f, 25.857334f, 33.442413f, 25.117197f, 34.182556f, 24.253702f)
generalPath!!.cubicTo(34.922695f, 23.390203f, 34.305912f, 22.15664f, 33.689133f, 21.78657f)
generalPath!!.lineTo(19.009695f, 13.02825f)
generalPath!!.lineTo(15.309f, 15.865453f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(229, 229, 229, 255), start = Offset(31.693024f, 22.25179f), end = Offset(0.90356445f, 4.131241f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)

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
            return 4.741071701049805
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
            return 43.25892639160156
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return network_wired(
                _width = (width.value * AmbientDensity.current.density).toInt(),
                _height = (height.value * AmbientDensity.current.density).toInt()
            )
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return network_wired(getOrigWidth().toInt(), getOrigHeight().toInt())
                }
            }
        }

        
    }

    override fun getWidth(): Int {
        return _width
    }

    override fun getHeight(): Int {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = (width.value * AmbientDensity.current.density).toInt()
        _height = (height.value * AmbientDensity.current.density).toInt()
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = _width / fullOrigWidth
            val coef2 = _height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()
            val coefDp = coef.dp.toPx()

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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

