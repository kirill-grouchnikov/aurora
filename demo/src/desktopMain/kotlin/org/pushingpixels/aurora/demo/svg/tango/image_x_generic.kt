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
class image_x_generic : Painter() {
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
alpha *= 0.5276382f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.3864840269088745f, -2.9843900203704834f, 0.0f, 1.0f)
))}){
// _0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(46.315495f, 41.63604f)
    cubicTo(46.350906f, 43.092964f, 42.146984f, 44.442017f, 35.295578f, 45.172367f)
    cubicTo(28.444166f, 45.902714f, 19.992647f, 45.902714f, 13.141237f, 45.172367f)
    cubicTo(6.289828f, 44.442017f, 2.085908f, 43.092964f, 2.1213188f, 41.63604f)
    cubicTo(2.085908f, 40.179115f, 6.289828f, 38.830063f, 13.141237f, 38.099712f)
    cubicTo(19.992647f, 37.369366f, 28.444166f, 37.369366f, 35.295578f, 38.099712f)
    cubicTo(42.146984f, 38.830063f, 46.350906f, 40.179115f, 46.315495f, 41.63604f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.218407f, 41.63604f), radius = 22.097088f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.7177715f, 6.454775f)
    lineTo(43.379543f, 6.454775f)
    cubicTo(44.002792f, 6.454775f, 44.504543f, 6.956525f, 44.504543f, 7.5797744f)
    lineTo(44.504543f, 31.480581f)
    cubicTo(44.504543f, 32.103832f, 36.04784f, 39.49987f, 35.424595f, 39.49987f)
    lineTo(2.7177715f, 39.49987f)
    cubicTo(2.094522f, 39.49987f, 1.5927727f, 38.998123f, 1.5927727f, 38.37487f)
    lineTo(1.5927727f, 7.5797744f)
    cubicTo(1.5927727f, 6.956525f, 2.094522f, 6.454775f, 2.7177715f, 6.454775f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(210, 210, 210, 255), 1.0f to Color(237, 237, 237, 255), start = Offset(70.2285f, 6.5338235f), end = Offset(96.89235f, 38.514523f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(187, 191, 187, 255))
stroke = Stroke(width=0.99999994f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.7177715f, 6.454775f)
    lineTo(43.379543f, 6.454775f)
    cubicTo(44.002792f, 6.454775f, 44.504543f, 6.956525f, 44.504543f, 7.5797744f)
    lineTo(44.504543f, 31.480581f)
    cubicTo(44.504543f, 32.103832f, 36.04784f, 39.49987f, 35.424595f, 39.49987f)
    lineTo(2.7177715f, 39.49987f)
    cubicTo(2.094522f, 39.49987f, 1.5927727f, 38.998123f, 1.5927727f, 38.37487f)
    lineTo(1.5927727f, 7.5797744f)
    cubicTo(1.5927727f, 6.956525f, 2.094522f, 6.454775f, 2.7177715f, 6.454775f)
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
1.0547740459442139f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0499889850616455f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.8146470189094543f, 4.485012054443359f, 0.0f, 1.0f)
))}){
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.512695f, 30.0f)
    lineTo(39.643234f, 30.0f)
    lineTo(39.643234f, 19.627375f)
    lineTo(5.512695f, 19.627375f)
    lineTo(5.512695f, 30.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(133, 149, 188, 255), 1.0f to Color(4, 26, 59, 255), start = Offset(22.149822f, 17.67732f), end = Offset(22.149822f, 31.652668f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.512695f, 5.237844f)
    lineTo(39.643234f, 5.237844f)
    lineTo(39.643234f, 19.627375f)
    lineTo(5.512695f, 19.627375f)
    lineTo(5.512695f, 5.237844f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(208, 214, 229, 255), 1.0f to Color(9, 58, 128, 255), start = Offset(22.149822f, 22.332615f), end = Offset(22.149822f, 2.945166f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1892169713974f, 0.0f, 0.0f, 0.0f,
0.0f, 1.1892169713974f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.525355100631714f, -6.535408020019531f, 0.0f, 1.0f)
))}){
// _0_2_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0
alphaStack.add(0, alpha)
alpha *= 0.04999994f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.4f, 15.4f)
    cubicTo(18.4f, 17.6f, 16.6f, 19.5f, 14.3f, 19.5f)
    cubicTo(12.1f, 19.5f, 10.2f, 17.7f, 10.2f, 15.4f)
    cubicTo(10.2f, 13.2f, 12.0f, 11.3f, 14.3f, 11.3f)
    cubicTo(16.5f, 11.3f, 18.4f, 13.1f, 18.4f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(232, 245, 47, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.20829993f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.0f, 15.4f)
    cubicTo(18.0f, 17.4f, 16.4f, 19.1f, 14.3f, 19.1f)
    cubicTo(12.3f, 19.1f, 10.6f, 17.5f, 10.6f, 15.4f)
    cubicTo(10.6f, 13.4f, 12.2f, 11.7f, 14.3f, 11.7f)
    cubicTo(16.3f, 11.7f, 18.0f, 13.3f, 18.0f, 15.4f)
    lineTo(18.0f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(236, 247, 81, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.36669993f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.6f, 15.4f)
    cubicTo(17.6f, 17.2f, 16.1f, 18.7f, 14.3f, 18.7f)
    cubicTo(12.5f, 18.7f, 11.0f, 17.2f, 11.0f, 15.4f)
    cubicTo(11.0f, 13.6f, 12.5f, 12.1f, 14.3f, 12.1f)
    cubicTo(16.1f, 12.1f, 17.6f, 13.6f, 17.6f, 15.4f)
    lineTo(17.6f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(240, 249, 114, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.525f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.2f, 15.4f)
    cubicTo(17.2f, 17.0f, 15.9f, 18.3f, 14.3f, 18.3f)
    cubicTo(12.7f, 18.3f, 11.4f, 17.0f, 11.4f, 15.4f)
    cubicTo(11.4f, 13.8f, 12.7f, 12.5f, 14.3f, 12.5f)
    cubicTo(15.9f, 12.5f, 17.2f, 13.8f, 17.2f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(244, 250, 149, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6833f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.8f, 15.4f)
    cubicTo(16.8f, 16.8f, 15.7f, 17.9f, 14.3f, 17.9f)
    cubicTo(12.9f, 17.9f, 11.8f, 16.8f, 11.8f, 15.4f)
    cubicTo(11.8f, 14.0f, 12.9f, 12.9f, 14.3f, 12.9f)
    cubicTo(15.7f, 12.9f, 16.8f, 14.0f, 16.8f, 15.4f)
    lineTo(16.8f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(247, 252, 183, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8417f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.4f, 15.4f)
    cubicTo(16.4f, 16.6f, 15.4f, 17.5f, 14.3f, 17.5f)
    cubicTo(13.2f, 17.5f, 12.2f, 16.5f, 12.2f, 15.4f)
    cubicTo(12.2f, 14.3f, 13.2f, 13.3f, 14.3f, 13.3f)
    cubicTo(15.4f, 13.3f, 16.4f, 14.3f, 16.4f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(251, 253, 219, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.0f, 15.4f)
    cubicTo(16.0f, 16.4f, 15.2f, 17.2f, 14.2f, 17.2f)
    cubicTo(13.2f, 17.2f, 12.4f, 16.4f, 12.4f, 15.4f)
    cubicTo(12.4f, 14.4f, 13.2f, 13.6f, 14.2f, 13.6f)
    cubicTo(15.2f, 13.6f, 16.0f, 14.4f, 16.0f, 15.4f)
    lineTo(16.0f, 15.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.01586f, 21.649044f)
    lineTo(33.697147f, 21.649044f)
    lineTo(35.362053f, 22.124731f)
    lineTo(32.50793f, 22.124731f)
    cubicTo(32.50793f, 22.124731f, 35.362053f, 22.362574f, 36.789116f, 24.1464f)
    cubicTo(38.216175f, 25.811304f, 35.12421f, 27.832975f, 35.12421f, 27.832975f)
    cubicTo(35.12421f, 27.832975f, 35.12421f, 27.832975f, 35.12421f, 27.832975f)
    cubicTo(35.005287f, 27.47621f, 34.291756f, 24.622087f, 32.864697f, 23.43287f)
    cubicTo(31.7944f, 22.481497f, 30.605183f, 22.243652f, 30.605183f, 22.243652f)
    lineTo(25.01586f, 22.243652f)
    lineTo(25.01586f, 21.767965f)
    lineTo(25.01586f, 21.649044f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.3f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.724106f, 22.362574f)
    lineTo(25.729391f, 22.362574f)
    lineTo(35.005287f, 27.59513f)
    lineTo(30.724106f, 22.362574f)
    lineTo(30.724106f, 22.362574f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.01586f, 21.767965f)
    lineTo(33.697147f, 21.767965f)
    lineTo(35.005287f, 20.935513f)
    lineTo(32.15117f, 20.935513f)
    cubicTo(32.15117f, 20.935513f, 34.767445f, 20.459827f, 35.12421f, 17.486782f)
    cubicTo(35.480972f, 14.513739f, 31.08087f, 11.183931f, 31.08087f, 11.183931f)
    cubicTo(31.08087f, 11.183931f, 31.08087f, 11.183931f, 31.08087f, 11.302853f)
    cubicTo(31.19979f, 12.016383f, 32.389008f, 17.011095f, 31.556557f, 18.913845f)
    cubicTo(31.19979f, 20.578747f, 30.129496f, 20.935513f, 30.129496f, 20.935513f)
    lineTo(24.659094f, 20.935513f)
    lineTo(24.896938f, 21.767965f)
    lineTo(25.01586f, 21.767965f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(81, 81, 81, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.248419f, 20.459827f)
    lineTo(25.253704f, 20.459827f)
    lineTo(31.19979f, 11.421773f)
    lineTo(30.248419f, 20.459827f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(81, 81, 81, 255))
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
// _0_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.8042316f, 7.4528584f)
    lineTo(43.233986f, 7.4528584f)
    cubicTo(43.384365f, 7.4528584f, 43.505432f, 7.5739236f, 43.505432f, 7.7243047f)
    lineTo(43.505432f, 31.422651f)
    cubicTo(43.505432f, 32.368526f, 36.401688f, 38.5f, 36.251305f, 38.5f)
    lineTo(2.8042316f, 38.5f)
    cubicTo(2.6538508f, 38.5f, 2.532786f, 38.378937f, 2.532786f, 38.228554f)
    lineTo(2.532786f, 7.7243047f)
    cubicTo(2.532786f, 7.5739236f, 2.6538508f, 7.4528584f, 2.8042316f, 7.4528584f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.84659094f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
brush = SolidColor(Color(79, 79, 79, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rectangle(rect = Rect(left = 5.5f, top = 10.5f, right = 40.5625f, bottom = 35.5625f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.206654f, 39.46876f)
    cubicTo(37.23707f, 39.79866f, 44.795444f, 34.938835f, 44.491062f, 30.970919f)
    cubicTo(42.9278f, 33.394016f, 39.73254f, 32.257656f, 35.623783f, 32.416668f)
    cubicTo(35.623783f, 32.416668f, 36.019154f, 38.96876f, 35.206654f, 39.46876f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(124, 124, 124, 255), 1.0f to Color(184, 184, 184, 255), start = Offset(41.144154f, 37.42343f), end = Offset(38.812492f, 34.73593f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(187, 189, 186, 255), 1.0f to Color(112, 116, 110, 255), start = Offset(42.1875f, 31.0f), end = Offset(45.0f, 39.98469f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.206654f, 39.46876f)
    cubicTo(37.23707f, 39.79866f, 44.795444f, 34.938835f, 44.491062f, 30.970919f)
    cubicTo(42.9278f, 33.394016f, 39.73254f, 32.257656f, 35.623783f, 32.416668f)
    cubicTo(35.623783f, 32.416668f, 36.019154f, 38.96876f, 35.206654f, 39.46876f)
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
// _0_6
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(38.543575f, 33.511543f), end = Offset(39.31762f, 34.66059f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.65709f, 37.27726f)
    cubicTo(38.026867f, 36.593433f, 41.08534f, 35.130795f, 42.38472f, 33.24979f)
    cubicTo(40.788624f, 33.929848f, 39.43691f, 33.45929f, 36.682384f, 33.440197f)
    cubicTo(36.682384f, 33.440197f, 36.844707f, 36.502293f, 36.65709f, 37.27726f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.30113637f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.0625f, 8.0f)
    lineTo(3.0625f, 30.0625f)
    cubicTo(25.388578f, 30.950861f, 27.884634f, 17.0f, 43.0f, 17.0f)
    lineTo(43.0f, 8.0f)
    lineTo(3.0625f, 8.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(10.156253f, 13.506495f), radius = 34.15717f, tileMode = TileMode.Clamp)
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
            return 0.7346128225326538
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 5.954774856567383
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 44.26993179321289
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 36.78096389770508
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

