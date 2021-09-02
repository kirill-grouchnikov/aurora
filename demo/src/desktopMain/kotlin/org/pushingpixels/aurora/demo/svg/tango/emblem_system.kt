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
import org.pushingpixels.aurora.skin.utils.toComposeBitmap
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
class emblem_system : Painter() {
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
alpha *= 0.40909088f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.8838850259780884f, 2.4748740196228027f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(45.078056f, 39.161163f)
generalPath!!.cubicTo(45.1095f, 41.378223f, 41.37642f, 43.431126f, 35.29237f, 44.542526f)
generalPath!!.cubicTo(29.208319f, 45.65393f, 21.70337f, 45.65393f, 15.619318f, 44.542526f)
generalPath!!.cubicTo(9.535267f, 43.431126f, 5.802187f, 41.378223f, 5.8336315f, 39.161163f)
generalPath!!.cubicTo(5.802187f, 36.944103f, 9.535267f, 34.8912f, 15.619318f, 33.7798f)
generalPath!!.cubicTo(21.70337f, 32.668396f, 29.208319f, 32.668396f, 35.29237f, 33.7798f)
generalPath!!.cubicTo(41.37642f, 34.8912f, 45.1095f, 36.944103f, 45.078056f, 39.161163f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(25.455845f, 39.16115f), radius = 19.622211f, tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(23.25f, 0.46875f)
generalPath!!.cubicTo(22.784561f, 0.5005963f, 22.332167f, 0.5726847f, 21.875f, 0.625f)
generalPath!!.lineTo(21.84375f, 0.625f)
generalPath!!.lineTo(20.75f, 6.59375f)
generalPath!!.cubicTo(18.967276f, 6.99974f, 17.29009f, 7.6887417f, 15.78125f, 8.625f)
generalPath!!.lineTo(10.875f, 5.09375f)
generalPath!!.cubicTo(9.548712f, 6.1234407f, 8.341802f, 7.3243456f, 7.28125f, 8.625f)
generalPath!!.lineTo(10.6875f, 13.59375f)
generalPath!!.cubicTo(9.653267f, 15.174265f, 8.875532f, 16.978973f, 8.4375f, 18.875f)
generalPath!!.cubicTo(8.437425f, 18.883963f, 8.437439f, 18.904688f, 8.4375f, 18.90625f)
generalPath!!.lineTo(2.5f, 19.84375f)
generalPath!!.cubicTo(2.3914466f, 20.730383f, 2.34375f, 21.646688f, 2.34375f, 22.5625f)
generalPath!!.cubicTo(2.34375f, 23.3118f, 2.3644395f, 24.051088f, 2.4375f, 24.78125f)
generalPath!!.lineTo(8.375f, 25.84375f)
generalPath!!.cubicTo(8.797279f, 27.905642f, 9.599459f, 29.831263f, 10.71875f, 31.53125f)
generalPath!!.lineTo(7.1875f, 36.375f)
generalPath!!.cubicTo(8.198826f, 37.63052f, 9.366392f, 38.773617f, 10.625f, 39.78125f)
generalPath!!.lineTo(15.625f, 36.34375f)
generalPath!!.cubicTo(17.372433f, 37.458466f, 19.323084f, 38.240124f, 21.4375f, 38.625f)
generalPath!!.lineTo(22.375f, 44.53125f)
generalPath!!.cubicTo(23.041183f, 44.59189f, 23.724348f, 44.59375f, 24.40625f, 44.59375f)
generalPath!!.cubicTo(25.368935f, 44.59375f, 26.288486f, 44.557266f, 27.21875f, 44.4375f)
generalPath!!.lineTo(28.34375f, 38.40625f)
generalPath!!.cubicTo(30.35131f, 37.90665f, 32.23722f, 37.03996f, 33.875f, 35.875f)
generalPath!!.lineTo(38.6875f, 39.375f)
generalPath!!.cubicTo(39.935528f, 38.3132f, 41.07678f, 37.092743f, 42.0625f, 35.78125f)
generalPath!!.lineTo(38.5625f, 30.71875f)
generalPath!!.cubicTo(39.510353f, 29.08176f, 40.16713f, 27.275608f, 40.5f, 25.34375f)
generalPath!!.lineTo(46.40625f, 24.40625f)
generalPath!!.cubicTo(46.458042f, 23.789904f, 46.46875f, 23.192163f, 46.46875f, 22.5625f)
generalPath!!.cubicTo(46.46875f, 21.468287f, 46.341568f, 20.395416f, 46.1875f, 19.34375f)
generalPath!!.lineTo(40.1875f, 18.25f)
generalPath!!.cubicTo(39.717304f, 16.513777f, 38.945824f, 14.893898f, 37.96875f, 13.4375f)
generalPath!!.lineTo(41.5f, 8.59375f)
generalPath!!.cubicTo(40.405426f, 7.255143f, 39.156822f, 6.018569f, 37.78125f, 4.96875f)
generalPath!!.lineTo(32.6875f, 8.46875f)
generalPath!!.cubicTo(31.223503f, 7.602913f, 29.648037f, 6.938568f, 27.9375f, 6.5625f)
generalPath!!.lineTo(27.0f, 0.625f)
generalPath!!.cubicTo(26.146702f, 0.5246246f, 25.286379f, 0.46875f, 24.40625f, 0.46875f)
generalPath!!.cubicTo(24.168379f, 0.46875f, 23.923567f, 0.4612654f, 23.6875f, 0.46875f)
generalPath!!.cubicTo(23.572416f, 0.47239882f, 23.458534f, 0.4620551f, 23.34375f, 0.46875f)
generalPath!!.cubicTo(23.312662f, 0.4705632f, 23.281029f, 0.4666269f, 23.25f, 0.46875f)
generalPath!!.close()
generalPath!!.moveTo(24.0625f, 15.65625f)
generalPath!!.cubicTo(24.176666f, 15.650457f, 24.290651f, 15.65625f, 24.40625f, 15.65625f)
generalPath!!.cubicTo(28.105377f, 15.65625f, 31.125f, 18.675875f, 31.125f, 22.375f)
generalPath!!.cubicTo(31.125002f, 26.074125f, 28.105375f, 29.0625f, 24.40625f, 29.0625f)
generalPath!!.cubicTo(20.707125f, 29.062502f, 17.71875f, 26.074125f, 17.71875f, 22.375f)
generalPath!!.cubicTo(17.718752f, 18.791473f, 20.52335f, 15.835842f, 24.0625f, 15.65625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(201, 201, 201, 255), 0.25f to Color(248, 248, 248, 255), 0.5f to Color(226, 226, 226, 255), 0.75f to Color(176, 176, 176, 255), 1.0f to Color(201, 201, 201, 255), start = Offset(12.9344635f, 8.047592f), end = Offset(37.861908f, 42.077095f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(128, 128, 128, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.25f, 0.46875f)
generalPath!!.cubicTo(22.784561f, 0.5005963f, 22.332167f, 0.5726847f, 21.875f, 0.625f)
generalPath!!.lineTo(21.84375f, 0.625f)
generalPath!!.lineTo(20.75f, 6.59375f)
generalPath!!.cubicTo(18.967276f, 6.99974f, 17.29009f, 7.6887417f, 15.78125f, 8.625f)
generalPath!!.lineTo(10.875f, 5.09375f)
generalPath!!.cubicTo(9.548712f, 6.1234407f, 8.341802f, 7.3243456f, 7.28125f, 8.625f)
generalPath!!.lineTo(10.6875f, 13.59375f)
generalPath!!.cubicTo(9.653267f, 15.174265f, 8.875532f, 16.978973f, 8.4375f, 18.875f)
generalPath!!.cubicTo(8.437425f, 18.883963f, 8.437439f, 18.904688f, 8.4375f, 18.90625f)
generalPath!!.lineTo(2.5f, 19.84375f)
generalPath!!.cubicTo(2.3914466f, 20.730383f, 2.34375f, 21.646688f, 2.34375f, 22.5625f)
generalPath!!.cubicTo(2.34375f, 23.3118f, 2.3644395f, 24.051088f, 2.4375f, 24.78125f)
generalPath!!.lineTo(8.375f, 25.84375f)
generalPath!!.cubicTo(8.797279f, 27.905642f, 9.599459f, 29.831263f, 10.71875f, 31.53125f)
generalPath!!.lineTo(7.1875f, 36.375f)
generalPath!!.cubicTo(8.198826f, 37.63052f, 9.366392f, 38.773617f, 10.625f, 39.78125f)
generalPath!!.lineTo(15.625f, 36.34375f)
generalPath!!.cubicTo(17.372433f, 37.458466f, 19.323084f, 38.240124f, 21.4375f, 38.625f)
generalPath!!.lineTo(22.375f, 44.53125f)
generalPath!!.cubicTo(23.041183f, 44.59189f, 23.724348f, 44.59375f, 24.40625f, 44.59375f)
generalPath!!.cubicTo(25.368935f, 44.59375f, 26.288486f, 44.557266f, 27.21875f, 44.4375f)
generalPath!!.lineTo(28.34375f, 38.40625f)
generalPath!!.cubicTo(30.35131f, 37.90665f, 32.23722f, 37.03996f, 33.875f, 35.875f)
generalPath!!.lineTo(38.6875f, 39.375f)
generalPath!!.cubicTo(39.935528f, 38.3132f, 41.07678f, 37.092743f, 42.0625f, 35.78125f)
generalPath!!.lineTo(38.5625f, 30.71875f)
generalPath!!.cubicTo(39.510353f, 29.08176f, 40.16713f, 27.275608f, 40.5f, 25.34375f)
generalPath!!.lineTo(46.40625f, 24.40625f)
generalPath!!.cubicTo(46.458042f, 23.789904f, 46.46875f, 23.192163f, 46.46875f, 22.5625f)
generalPath!!.cubicTo(46.46875f, 21.468287f, 46.341568f, 20.395416f, 46.1875f, 19.34375f)
generalPath!!.lineTo(40.1875f, 18.25f)
generalPath!!.cubicTo(39.717304f, 16.513777f, 38.945824f, 14.893898f, 37.96875f, 13.4375f)
generalPath!!.lineTo(41.5f, 8.59375f)
generalPath!!.cubicTo(40.405426f, 7.255143f, 39.156822f, 6.018569f, 37.78125f, 4.96875f)
generalPath!!.lineTo(32.6875f, 8.46875f)
generalPath!!.cubicTo(31.223503f, 7.602913f, 29.648037f, 6.938568f, 27.9375f, 6.5625f)
generalPath!!.lineTo(27.0f, 0.625f)
generalPath!!.cubicTo(26.146702f, 0.5246246f, 25.286379f, 0.46875f, 24.40625f, 0.46875f)
generalPath!!.cubicTo(24.168379f, 0.46875f, 23.923567f, 0.4612654f, 23.6875f, 0.46875f)
generalPath!!.cubicTo(23.572416f, 0.47239882f, 23.458534f, 0.4620551f, 23.34375f, 0.46875f)
generalPath!!.cubicTo(23.312662f, 0.4705632f, 23.281029f, 0.4666269f, 23.25f, 0.46875f)
generalPath!!.close()
generalPath!!.moveTo(24.0625f, 15.65625f)
generalPath!!.cubicTo(24.176666f, 15.650457f, 24.290651f, 15.65625f, 24.40625f, 15.65625f)
generalPath!!.cubicTo(28.105377f, 15.65625f, 31.125f, 18.675875f, 31.125f, 22.375f)
generalPath!!.cubicTo(31.125002f, 26.074125f, 28.105375f, 29.0625f, 24.40625f, 29.0625f)
generalPath!!.cubicTo(20.707125f, 29.062502f, 17.71875f, 26.074125f, 17.71875f, 22.375f)
generalPath!!.cubicTo(17.718752f, 18.791473f, 20.52335f, 15.835842f, 24.0625f, 15.65625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.64772725f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6065179705619812f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6065179705619812f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
10.1502103805542f, 7.936834812164307f, 0.0f, 1.0f)
))}){
// _0_0_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.6487557f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(36.239223f, 23.781593f)
generalPath!!.cubicTo(36.25962f, 28.342402f, 33.83816f, 32.565517f, 29.89175f, 34.85183f)
generalPath!!.cubicTo(25.94534f, 37.13814f, 21.077263f, 37.13814f, 17.130852f, 34.85183f)
generalPath!!.cubicTo(13.18444f, 32.565517f, 10.762982f, 28.342402f, 10.783379f, 23.781593f)
generalPath!!.cubicTo(10.762982f, 19.220785f, 13.18444f, 14.997669f, 17.130852f, 12.711357f)
generalPath!!.cubicTo(21.077263f, 10.425044f, 25.94534f, 10.425044f, 29.89175f, 12.711357f)
generalPath!!.cubicTo(33.83816f, 14.997669f, 36.25962f, 19.220785f, 36.239223f, 23.781593f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.34659088f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(22.557789f, 1.6501132f)
generalPath!!.lineTo(21.6796f, 7.4291234f)
generalPath!!.cubicTo(20.0086f, 7.809669f, 16.934874f, 8.973535f, 15.520595f, 9.851116f)
generalPath!!.lineTo(10.848562f, 6.3639293f)
generalPath!!.cubicTo(9.605394f, 7.3290873f, 9.520139f, 7.3945394f, 8.526054f, 8.613681f)
generalPath!!.lineTo(11.904107f, 13.623634f)
generalPath!!.cubicTo(10.934692f, 15.105095f, 9.770303f, 17.745129f, 9.352234f, 19.631721f)
generalPath!!.cubicTo(9.352234f, 19.631721f, 3.4328249f, 20.629562f, 3.4328249f, 20.629562f)
generalPath!!.cubicTo(3.331075f, 21.460629f, 3.379975f, 23.23936f, 3.448457f, 23.923761f)
generalPath!!.lineTo(9.1027f, 24.942348f)
generalPath!!.cubicTo(9.498514f, 26.875017f, 10.979731f, 29.985937f, 12.028874f, 31.579384f)
generalPath!!.lineTo(8.453299f, 36.30343f)
generalPath!!.cubicTo(9.401242f, 37.48026f, 9.590962f, 37.587948f, 10.770691f, 38.532433f)
generalPath!!.lineTo(15.551859f, 35.029613f)
generalPath!!.cubicTo(17.189777f, 36.074467f, 20.440891f, 37.345497f, 22.422794f, 37.706253f)
generalPath!!.lineTo(23.207481f, 43.412506f)
generalPath!!.cubicTo(23.831913f, 43.469345f, 25.556961f, 43.628788f, 26.428925f, 43.516525f)
generalPath!!.lineTo(27.307112f, 37.576374f)
generalPath!!.cubicTo(29.188854f, 37.108086f, 32.440273f, 35.773415f, 33.97541f, 34.68146f)
generalPath!!.lineTo(38.751465f, 38.13227f)
generalPath!!.cubicTo(39.921276f, 37.13702f, 39.93176f, 36.987057f, 40.8557f, 35.757755f)
generalPath!!.lineTo(37.316505f, 30.727055f)
generalPath!!.cubicTo(38.204952f, 29.192656f, 39.353733f, 26.191832f, 39.66574f, 24.381044f)
generalPath!!.lineTo(45.460384f, 23.419582f)
generalPath!!.cubicTo(45.50893f, 22.841864f, 45.511295f, 21.230793f, 45.366882f, 20.245037f)
generalPath!!.lineTo(39.463104f, 19.226448f)
generalPath!!.cubicTo(39.022377f, 17.599037f, 37.509895f, 14.666467f, 36.594055f, 13.301345f)
generalPath!!.lineTo(40.34641f, 8.577302f)
generalPath!!.cubicTo(39.320435f, 7.3225875f, 38.938965f, 7.150431f, 37.6496f, 6.1664066f)
generalPath!!.lineTo(32.70729f, 9.705604f)
generalPath!!.cubicTo(31.335043f, 8.894031f, 28.598675f, 7.656856f, 26.99534f, 7.304357f)
generalPath!!.lineTo(26.122267f, 1.6501132f)
generalPath!!.cubicTo(25.322445f, 1.5560285f, 23.014872f, 1.5978075f, 22.557789f, 1.6501132f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
            return 1.84375
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
            return 45.125
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

