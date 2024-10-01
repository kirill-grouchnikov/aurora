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
class applications_multimedia : Painter() {
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
alpha *= 0.74372f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, -0.8571400046348572f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(46.714f, 44.286f)
    cubicTo(46.74937f, 45.92411f, 42.55032f, 47.440933f, 35.706844f, 48.26211f)
    cubicTo(28.863367f, 49.083286f, 20.421633f, 49.083286f, 13.578157f, 48.26211f)
    cubicTo(6.734681f, 47.440933f, 2.5356293f, 45.92411f, 2.5709991f, 44.286f)
    cubicTo(2.5356293f, 42.64789f, 6.734681f, 41.131065f, 13.578157f, 40.309887f)
    cubicTo(20.421633f, 39.488712f, 28.863367f, 39.488712f, 35.706844f, 40.309887f)
    cubicTo(42.55032f, 41.131065f, 46.74937f, 42.64789f, 46.714f, 44.286f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.643f, 44.285515f), radius = 22.070997f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
shape = Outline.Rounded(roundRect = RoundRect(left = 5.428599834442139f, top = 16.570999145507812f, right = 43.57160139083862f, bottom = 44.57099914550781f,radiusX = 2.285799980163574f, radiusY = 2.285799980163574f))
brush = Brush.radialGradient(0.0f to Color(93, 93, 93, 255), 1.0f to Color(68, 68, 68, 255), center = Offset(13.498444f, 23.711525f), radius = 56.425156f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(27, 27, 27, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 5.428599834442139f, top = 16.570999145507812f, right = 43.57160139083862f, bottom = 44.57099914550781f,radiusX = 2.285799980163574f, radiusY = 2.285799980163574f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31156f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(5.4635f, 13.143f), end = Offset(21.536f, 49.143f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 6.392099857330322f, top = 17.48900032043457f, right = 42.46510171890259f, bottom = 43.54899978637695f,radiusX = 0.5474399924278259f, radiusY = 0.5474399924278259f))
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
-0.9727100133895874f, 0.23202000558376312f, 0.0f, 0.0f,
0.23202000558376312f, 0.9727100133895874f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
45.7859992980957f, 5.110599994659424f, 0.0f, 1.0f)
))}){
// _0_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.974405387304794f, -0.22479800086164994f, 0.0f, 0.0f,
0.22479800086164994f, 0.974405387304794f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_0
shape = Outline.Rounded(roundRect = RoundRect(left = 1.705199956893921f, top = 10.352999687194824f, right = 41.511199712753296f, bottom = 16.861799716949463f,radiusX = 2.285799980163574f, radiusY = 2.285799980163574f))
brush = Brush.linearGradient(0.0f to Color(253, 253, 253, 255), 1.0f to Color(212, 212, 212, 255), start = Offset(2.7612f, 6.1103f), end = Offset(18.773f, 18.693f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(43, 43, 43, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 1.705199956893921f, top = 10.352999687194824f, right = 41.511199712753296f, bottom = 16.861799716949463f,radiusX = 2.285799980163574f, radiusY = 2.285799980163574f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
    moveTo(9.1896f, 8.9692f)
    lineTo(6.7611f, 15.541f)
    lineTo(11.475f, 14.255f)
    lineTo(13.904f, 7.9692f)
    lineTo(9.1896f, 8.9692f)
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
// _0_0_3_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(18.332f, 6.8978f)
    lineTo(16.047f, 13.398f)
    lineTo(20.761002f, 12.255f)
    lineTo(23.047f, 5.8978f)
    lineTo(18.332f, 6.8978f)
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
// _0_0_3_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(27.904f, 4.6835f)
    lineTo(25.618f, 11.1119995f)
    lineTo(30.332f, 9.969199f)
    lineTo(32.618f, 3.6834993f)
    lineTo(27.904f, 4.6834993f)
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
// _0_0_3_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.413f, 2.0183f)
    cubicTo(39.642f, 2.1743f, 39.814f, 2.3819f, 39.882f, 2.6745f)
    lineTo(40.614f, 5.7504f)
    lineTo(39.891f, 7.7504f)
    lineTo(35.203f, 8.8353f)
    lineTo(37.483997f, 2.4781003f)
    lineTo(39.413f, 2.0183003f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.974405387304794f, -0.22479800086164994f, 0.0f, 0.0f,
0.22479800086164994f, 0.974405387304794f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3_5
brush = SolidColor(Color(255, 255, 255, 147))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 2.6717000007629395f, top = 11.32699966430664f, right = 40.51469850540161f, bottom = 15.933899879455566f,radiusX = 0.5301200151443481f, radiusY = 0.5301200151443481f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
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
0.974405387304794f, -0.22479800086164994f, 0.0f, 0.0f,
0.22479800086164994f, 0.974405387304794f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
shape = Outline.Rounded(roundRect = RoundRect(left = 0.6704300045967102f, top = 9.685199737548828f, right = 40.74742966890335f, bottom = 16.62679958343506f,radiusX = 2.285799980163574f, radiusY = 2.285799980163574f))
brush = Brush.linearGradient(0.0f to Color(253, 253, 253, 255), 1.0f to Color(212, 212, 212, 255), start = Offset(2.16153f, 5.5321302f), end = Offset(18.17333f, 18.11483f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(46, 46, 46, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 0.6704300045967102f, top = 9.685199737548828f, right = 40.74742966890335f, bottom = 16.62679958343506f,radiusX = 2.285799980163574f, radiusY = 2.285799980163574f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
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
    moveTo(10.143f, 8.1429f)
    lineTo(7.7142997f, 14.714001f)
    lineTo(12.429f, 13.429001f)
    lineTo(14.857f, 7.142901f)
    lineTo(10.143f, 8.1429f)
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
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.429f, 5.7857f)
    lineTo(16.786001f, 12.785999f)
    lineTo(21.500002f, 11.643f)
    lineTo(24.143002f, 4.7857f)
    lineTo(19.429f, 5.7857f)
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
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.071f, 3.6429f)
    lineTo(26.5f, 10.785999f)
    lineTo(31.214f, 9.6428995f)
    lineTo(33.786f, 2.6428995f)
    lineTo(29.071f, 3.6428995f)
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
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.295f, 0.97768f)
    cubicTo(41.524f, 1.1337f, 41.696f, 1.3413f, 41.762997f, 1.6339f)
    lineTo(42.423996f, 4.2813f)
    lineTo(41.343994f, 7.0670004f)
    lineTo(36.227993f, 8.2946005f)
    lineTo(38.579994f, 1.5804005f)
    lineTo(41.294994f, 0.97768044f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.974405387304794f, -0.22479800086164994f, 0.0f, 0.0f,
0.22479800086164994f, 0.974405387304794f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_9
brush = SolidColor(Color(255, 255, 255, 147))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 1.6368999481201172f, top = 10.659000396728516f, right = 39.83590126037598f, bottom = 15.573700428009033f,radiusX = 0.5301200151443481f, radiusY = 0.5301200151443481f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
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
    moveTo(6.5357f, 10.893f)
    lineTo(6.5357f, 18.678999f)
    cubicTo(6.5357f, 19.143f, 6.9286f, 19.571f, 7.3929f, 19.606998f)
    lineTo(15.285999f, 19.606998f)
    cubicTo(16.149f, 19.606998f, 16.56f, 18.345f, 15.893f, 17.678999f)
    lineTo(9.6786f, 10.393f)
    cubicTo(8.7143f, 9.0f, 6.5357003f, 9.678599f, 6.5357003f, 10.893f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(147, 147, 147, 255), 1.0f to Color(217, 217, 217, 255), start = Offset(10.679f, 15.929f), end = Offset(7.9286f, 11.107f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(27, 27, 27, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.5357f, 10.893f)
    lineTo(6.5357f, 18.678999f)
    cubicTo(6.5357f, 19.143f, 6.9286f, 19.571f, 7.3929f, 19.606998f)
    lineTo(15.285999f, 19.606998f)
    cubicTo(16.149f, 19.606998f, 16.56f, 18.345f, 15.893f, 17.678999f)
    lineTo(9.6786f, 10.393f)
    cubicTo(8.7143f, 9.0f, 6.5357003f, 9.678599f, 6.5357003f, 10.893f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.07142899930477142f, -4.964300155639648f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.0357f, 17.196f)
    cubicTo(9.036472f, 17.368769f, 8.944744f, 17.528746f, 8.795249f, 17.615355f)
    cubicTo(8.645754f, 17.701962f, 8.461346f, 17.701962f, 8.311851f, 17.615355f)
    cubicTo(8.162355f, 17.528746f, 8.070627f, 17.368769f, 8.0714f, 17.196f)
    cubicTo(8.070627f, 17.02323f, 8.162355f, 16.863253f, 8.311851f, 16.776644f)
    cubicTo(8.461346f, 16.690037f, 8.645754f, 16.690037f, 8.795249f, 16.776644f)
    cubicTo(8.944744f, 16.863253f, 9.036472f, 17.02323f, 9.0357f, 17.196f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.07142899930477142f, 0.3214299976825714f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.0357f, 17.196f)
    cubicTo(9.036472f, 17.368769f, 8.944744f, 17.528746f, 8.795249f, 17.615355f)
    cubicTo(8.645754f, 17.701962f, 8.461346f, 17.701962f, 8.311851f, 17.615355f)
    cubicTo(8.162355f, 17.528746f, 8.070627f, 17.368769f, 8.0714f, 17.196f)
    cubicTo(8.070627f, 17.02323f, 8.162355f, 16.863253f, 8.311851f, 16.776644f)
    cubicTo(8.461346f, 16.690037f, 8.645754f, 16.690037f, 8.795249f, 16.776644f)
    cubicTo(8.944744f, 16.863253f, 9.036472f, 17.02323f, 9.0357f, 17.196f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
4.964300155639648f, 0.3214299976825714f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.0357f, 17.196f)
    cubicTo(9.036472f, 17.368769f, 8.944744f, 17.528746f, 8.795249f, 17.615355f)
    cubicTo(8.645754f, 17.701962f, 8.461346f, 17.701962f, 8.311851f, 17.615355f)
    cubicTo(8.162355f, 17.528746f, 8.070627f, 17.368769f, 8.0714f, 17.196f)
    cubicTo(8.070627f, 17.02323f, 8.162355f, 16.863253f, 8.311851f, 16.776644f)
    cubicTo(8.461346f, 16.690037f, 8.645754f, 16.690037f, 8.795249f, 16.776644f)
    cubicTo(8.944744f, 16.863253f, 9.036472f, 17.02323f, 9.0357f, 17.196f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.26131f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 4.0f, 0.0f, 1.0f)
))}){
// _0_0_14
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_0
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.0f, 28.429f)
    lineTo(38.0f, 28.429f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_1
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.0f, 32.571f)
    lineTo(38.0f, 32.571f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.5f, 28.0f)
    lineTo(32.5f, 24.0f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_3
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.0f, 32.571f)
    lineTo(38.0f, 32.571f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_4
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.5f, 28.0f)
    lineTo(22.5f, 24.0f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_5
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.7429f, 36.571f)
    lineTo(23.0429f, 36.571f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6880599856376648f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6880599856376648f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
11.982000350952148f, 14.732999801635742f, 0.0f, 1.0f)
))}){
// _0_0_15
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.817f, 27.839f)
    cubicTo(13.931001f, 28.804f, 14.062f, 29.766f, 14.212001f, 30.727001f)
    lineTo(12.104001f, 31.842001f)
    cubicTo(11.971001f, 30.899f, 11.860001f, 29.949001f, 11.653001f, 29.018002f)
    lineTo(13.817001f, 27.839f)
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
// _0_0_15_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.665f, 28.673f)
    cubicTo(17.641f, 30.752f, 18.343f, 29.866001f, 15.754001f, 31.262001f)
    cubicTo(16.57f, 29.959002f, 17.946001f, 28.644001f, 19.303001f, 29.817001f)
    cubicTo(21.894001f, 30.239002f, 17.869001f, 32.299f, 19.658f, 30.132002f)
    cubicTo(22.258001f, 27.862001f, 21.880001f, 29.134003f, 24.062f, 29.263002f)
    cubicTo(25.842001f, 29.693003f, 25.858f, 29.319002f, 23.492f, 30.680002f)
    lineTo(23.588001f, 30.596003f)
    lineTo(25.827002f, 29.649002f)
    cubicTo(25.801003f, 29.688002f, 25.776001f, 29.726002f, 25.751001f, 29.765001f)
    cubicTo(22.939001f, 31.443f, 24.082f, 30.949001f, 22.104002f, 30.691002f)
    cubicTo(20.237001f, 30.364002f, 19.228003f, 30.138002f, 21.883001f, 29.207003f)
    cubicTo(20.743002f, 30.501003f, 18.901001f, 32.507004f, 17.264002f, 31.090002f)
    cubicTo(15.676002f, 29.919003f, 19.397001f, 28.676003f, 18.029001f, 30.359001f)
    cubicTo(15.346001f, 31.837002f, 15.628001f, 31.910002f, 15.468001f, 29.793001f)
    lineTo(17.665f, 28.673f)
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
// _0_0_15_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.175f, 26.231f)
    cubicTo(29.321f, 27.739f, 29.401f, 29.25f, 29.58f, 30.755001f)
    cubicTo(31.004f, 32.606003f, 27.753f, 35.389f, 28.233f, 32.564003f)
    cubicTo(28.575f, 29.320004f, 28.883f, 28.720003f, 31.762f, 27.684002f)
    cubicTo(32.309f, 28.668001f, 32.608997f, 29.822002f, 33.466f, 30.412003f)
    cubicTo(28.626f, 32.964005f, 35.159f, 28.931004f, 35.887f, 28.545002f)
    cubicTo(36.352f, 28.436003f, 36.826f, 28.380001f, 37.299f, 28.314001f)
    lineTo(35.378f, 29.752f)
    cubicTo(34.913f, 29.820002f, 34.442997f, 29.87f, 33.993f, 30.013f)
    cubicTo(36.992f, 28.101f, 33.425f, 30.855f, 31.487f, 31.83f)
    cubicTo(30.563f, 31.105999f, 30.344f, 29.936f, 29.703999f, 28.95f)
    cubicTo(32.85f, 26.731f, 30.336998f, 28.809f, 30.342f, 31.527f)
    cubicTo(30.057999f, 33.877f, 27.022f, 35.077f, 27.526999f, 31.834f)
    cubicTo(27.353998f, 30.352f, 27.276999f, 28.851f, 26.996998f, 27.386f)
    lineTo(29.174997f, 26.230999f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0140000581741333f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0140000581741333f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.878600001335144f, 5.469099998474121f, 0.0f, 1.0f)
))}){
// _0_0_16
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.316f, 22.374f)
    cubicTo(13.137f, 21.041f, 12.443f, 22.093f, 12.445f, 23.521f)
    cubicTo(12.429999f, 24.807f, 12.620999f, 26.078f, 12.907f, 27.327f)
    lineTo(11.643999f, 28.01f)
    cubicTo(11.356999f, 26.75f, 11.164999f, 25.469f, 11.168999f, 24.173f)
    cubicTo(11.148998f, 22.62f, 10.879999f, 22.220001f, 12.515999f, 21.502f)
    lineTo(11.315999f, 22.374f)
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
// _0_0_16_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.611f, 22.413f)
    cubicTo(15.587f, 23.203001f, 15.587f, 23.994f, 15.584f, 24.785f)
    cubicTo(15.582999f, 25.117f, 15.582999f, 25.449f, 15.582999f, 25.781f)
    lineTo(14.317999f, 26.425f)
    cubicTo(14.317999f, 26.094f, 14.317999f, 25.762f, 14.3169985f, 25.431f)
    cubicTo(14.313998f, 24.649f, 14.313998f, 23.868f, 14.289998f, 23.086f)
    lineTo(15.610998f, 22.413f)
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
// _0_0_16_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.833f, 21.201f)
    cubicTo(17.932001f, 21.997f, 17.95f, 22.856f, 17.978f, 23.674f)
    cubicTo(17.986f, 23.914999f, 17.992f, 24.156f, 17.998001f, 24.397f)
    lineTo(16.741001f, 25.043f)
    cubicTo(16.733002f, 24.803f, 16.727001f, 24.561998f, 16.717001f, 24.321999f)
    cubicTo(16.681002f, 23.506998f, 16.675001f, 22.673998f, 16.512001f, 21.873999f)
    lineTo(17.833f, 21.200998f)
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
// _0_0_16_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.258f, 20.595f)
    cubicTo(20.060999f, 21.276999f, 19.989f, 21.991f, 19.92f, 22.699f)
    cubicTo(19.853f, 23.46f, 19.822f, 24.223f, 19.791f, 24.987f)
    lineTo(18.534f, 25.621f)
    cubicTo(18.564001f, 24.858f, 18.594f, 24.095001f, 18.65f, 23.332f)
    cubicTo(18.705f, 22.644001f, 18.733f, 21.927f, 18.935999f, 21.268002f)
    lineTo(20.258f, 20.595001f)
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
// _0_0_16_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.8969f, 22.31f)
    cubicTo(10.804f, 22.501f, 11.724f, 22.659f, 12.639f, 22.814f)
    cubicTo(14.789f, 23.146f, 16.946f, 23.441f, 19.107f, 23.688f)
    cubicTo(20.011f, 23.779999f, 20.914f, 23.878f, 21.817001f, 23.984f)
    lineTo(20.649002f, 24.810999f)
    cubicTo(19.755003f, 24.699999f, 18.860003f, 24.596998f, 17.964003f, 24.498999f)
    cubicTo(15.802003f, 24.237999f, 13.6430025f, 23.943998f, 11.489002f, 23.620998f)
    cubicTo(10.561003f, 23.471998f, 9.627102f, 23.289999f, 8.697103f, 23.182f)
    lineTo(9.896902f, 22.31f)
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
            return 2.230882406234741
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
            return 44.483341217041016
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

