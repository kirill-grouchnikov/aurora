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
class appointment_new : Painter() {
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
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
2.5631580352783203f, 0.0f, 0.0f, 0.0f,
0.0f, 1.2196019887924194f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-55.98413848876953f, 14.0414400100708f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.774754f, 19.008621f)
    cubicTo(39.788635f, 22.112505f, 38.140697f, 24.986568f, 35.454945f, 26.54253f)
    cubicTo(32.769196f, 28.098494f, 29.4562f, 28.098494f, 26.770447f, 26.54253f)
    cubicTo(24.084696f, 24.986568f, 22.436758f, 22.112505f, 22.45064f, 19.008621f)
    cubicTo(22.436758f, 15.904738f, 24.084696f, 13.030674f, 26.770447f, 11.474711f)
    cubicTo(29.4562f, 9.918749f, 32.769196f, 9.918749f, 35.454945f, 11.474711f)
    cubicTo(38.140697f, 13.030674f, 39.788635f, 15.904738f, 39.774754f, 19.008621f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(31.112698f, 19.008621f), radius = 8.66206f, tileMode = TileMode.Clamp)
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
    moveTo(18.587591f, 1.403729f)
    lineTo(4.226755f, 18.096664f)
    lineTo(5.4854717f, 19.339844f)
    lineTo(18.587591f, 1.403729f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(154, 162, 154, 255), 1.0f to Color(181, 190, 181, 255), start = Offset(-4.0365143f, 16.474691f), end = Offset(-1.0068378f, 30.91694f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(18.467176f, 1.3138036f)
    lineTo(5.6605716f, 19.072613f)
    lineTo(7.4900985f, 20.687914f)
    lineTo(18.467176f, 1.3138036f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(254, 254, 254, 255))
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
1.4315290451049805f, 0.0f, 0.0f, 0.0f,
0.0f, 1.4315290451049805f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.5694590210914612f, -1.6546180248260498f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.160713f, 16.910715f)
    cubicTo(31.184608f, 22.253685f, 28.34788f, 27.20105f, 23.724672f, 29.879456f)
    cubicTo(19.101465f, 32.55786f, 13.398534f, 32.55786f, 8.775327f, 29.879456f)
    cubicTo(4.15212f, 27.20105f, 1.3153913f, 22.253685f, 1.3392859f, 16.910715f)
    cubicTo(1.3153913f, 11.567745f, 4.15212f, 6.6203814f, 8.775327f, 3.9419744f)
    cubicTo(13.398534f, 1.2635677f, 19.101465f, 1.2635677f, 23.724672f, 3.9419744f)
    cubicTo(28.34788f, 6.6203814f, 31.184608f, 11.567745f, 31.160713f, 16.910715f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(148, 151, 179, 255), 1.0f to Color(76, 64, 89, 255), center = Offset(8.746825f, 6.8283234f), radius = 29.889713f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(96, 87, 115, 255))
stroke = Stroke(width=0.69855404f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.160713f, 16.910715f)
    cubicTo(31.184608f, 22.253685f, 28.34788f, 27.20105f, 23.724672f, 29.879456f)
    cubicTo(19.101465f, 32.55786f, 13.398534f, 32.55786f, 8.775327f, 29.879456f)
    cubicTo(4.15212f, 27.20105f, 1.3153913f, 22.253685f, 1.3392859f, 16.910715f)
    cubicTo(1.3153913f, 11.567745f, 4.15212f, 6.6203814f, 8.775327f, 3.9419744f)
    cubicTo(13.398534f, 1.2635677f, 19.101465f, 1.2635677f, 23.724672f, 3.9419744f)
    cubicTo(28.34788f, 6.6203814f, 31.184608f, 11.567745f, 31.160713f, 16.910715f)
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
1.1638380289077759f, 0.0f, 0.0f, 0.0f,
0.0f, 1.1638380289077759f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
4.824800968170166f, 2.7775559425354004f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.160713f, 16.910715f)
    cubicTo(31.184608f, 22.253685f, 28.34788f, 27.20105f, 23.724672f, 29.879456f)
    cubicTo(19.101465f, 32.55786f, 13.398534f, 32.55786f, 8.775327f, 29.879456f)
    cubicTo(4.15212f, 27.20105f, 1.3153913f, 22.253685f, 1.3392859f, 16.910715f)
    cubicTo(1.3153913f, 11.567745f, 4.15212f, 6.6203814f, 8.775327f, 3.9419744f)
    cubicTo(13.398534f, 1.2635677f, 19.101465f, 1.2635677f, 23.724672f, 3.9419744f)
    cubicTo(28.34788f, 6.6203814f, 31.184608f, 11.567745f, 31.160713f, 16.910715f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 253, 255), 1.0f to Color(203, 203, 201, 255), center = Offset(11.901996f, 10.045444f), radius = 29.292713f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(160, 160, 160, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(6.342216f, 7.7893324f), end = Offset(22.218424f, 25.884274f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.711396f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.160713f, 16.910715f)
    cubicTo(31.184608f, 22.253685f, 28.34788f, 27.20105f, 23.724672f, 29.879456f)
    cubicTo(19.101465f, 32.55786f, 13.398534f, 32.55786f, 8.775327f, 29.879456f)
    cubicTo(4.15212f, 27.20105f, 1.3153913f, 22.253685f, 1.3392859f, 16.910715f)
    cubicTo(1.3153913f, 11.567745f, 4.15212f, 6.6203814f, 8.775327f, 3.9419744f)
    cubicTo(13.398534f, 1.2635677f, 19.101465f, 1.2635677f, 23.724672f, 3.9419744f)
    cubicTo(28.34788f, 6.6203814f, 31.184608f, 11.567745f, 31.160713f, 16.910715f)
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
1.7699509859085083f, 0.0f, 0.0f, 0.0f,
0.0f, 1.7699509859085083f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-17.024240493774414f, 1.6107410192489624f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.679382f, 6.638714f)
    cubicTo(18.281221f, 4.5904484f, 20.732477f, 3.3880484f, 23.332691f, 3.3751054f)
    lineTo(23.375f, 11.875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), center = Offset(23.375f, 11.874996f), radius = 12.153954f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(196, 160, 0, 255), 1.0f to Color(196, 160, 0, 0), start = Offset(19.667364f, 4.2570662f), end = Offset(20.329933f, 5.2845874f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.56498736f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.679382f, 6.638714f)
    cubicTo(18.281221f, 4.5904484f, 20.732477f, 3.3880484f, 23.332691f, 3.3751054f)
    lineTo(23.375f, 11.875f)
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
2.0732951164245605f, 0.0f, 0.0f, 0.0f,
0.0f, 2.0732951164245605f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.3102240562438965f, -13.136819839477539f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.40625f, 17.28125f)
    cubicTo(16.408203f, 17.717966f, 16.176338f, 18.122347f, 15.798454f, 18.34127f)
    cubicTo(15.420569f, 18.560194f, 14.954431f, 18.560194f, 14.576546f, 18.34127f)
    cubicTo(14.198661f, 18.122347f, 13.966797f, 17.717966f, 13.96875f, 17.28125f)
    cubicTo(13.966797f, 16.844534f, 14.198661f, 16.440153f, 14.576546f, 16.22123f)
    cubicTo(14.954431f, 16.002306f, 15.420569f, 16.002306f, 15.798454f, 16.22123f)
    cubicTo(16.176338f, 16.440153f, 16.408203f, 16.844534f, 16.40625f, 17.28125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(243, 243, 243, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=0.48232403f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.40625f, 17.28125f)
    cubicTo(16.408203f, 17.717966f, 16.176338f, 18.122347f, 15.798454f, 18.34127f)
    cubicTo(15.420569f, 18.560194f, 14.954431f, 18.560194f, 14.576546f, 18.34127f)
    cubicTo(14.198661f, 18.122347f, 13.966797f, 17.717966f, 13.96875f, 17.28125f)
    cubicTo(13.966797f, 16.844534f, 14.198661f, 16.440153f, 14.576546f, 16.22123f)
    cubicTo(14.954431f, 16.002306f, 15.420569f, 16.002306f, 15.798454f, 16.22123f)
    cubicTo(16.176338f, 16.440153f, 16.408203f, 16.844534f, 16.40625f, 17.28125f)
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
// _0_0_7
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.176615f, 20.718014f)
    lineTo(13.155702f, 13.140282f)
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
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.408613f, 29.776506f)
    lineTo(22.368654f, 25.283228f)
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
2.749492883682251f, 0.0f, 0.0f, 0.0f,
0.0f, 2.749492883682251f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-22.300729751586914f, -12.409390449523926f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.324118f, 7.6932044f)
    cubicTo(17.32511f, 7.914911f, 17.2074f, 8.120201f, 17.01556f, 8.231341f)
    cubicTo(16.823719f, 8.342482f, 16.587078f, 8.342482f, 16.395237f, 8.231341f)
    cubicTo(16.203398f, 8.120201f, 16.085688f, 7.914911f, 16.08668f, 7.6932044f)
    cubicTo(16.085688f, 7.471498f, 16.203398f, 7.2662077f, 16.395237f, 7.1550674f)
    cubicTo(16.587078f, 7.043927f, 16.823719f, 7.043927f, 17.01556f, 7.1550674f)
    cubicTo(17.2074f, 7.2662077f, 17.32511f, 7.471498f, 17.324118f, 7.6932044f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(182, 185, 177, 255))
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
2.749492883682251f, 0.0f, 0.0f, 0.0f,
0.0f, 2.749492883682251f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-22.300729751586914f, 14.809220314025879f, 0.0f, 1.0f)
))}){
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.324118f, 7.6932044f)
    cubicTo(17.32511f, 7.914911f, 17.2074f, 8.120201f, 17.01556f, 8.231341f)
    cubicTo(16.823719f, 8.342482f, 16.587078f, 8.342482f, 16.395237f, 8.231341f)
    cubicTo(16.203398f, 8.120201f, 16.085688f, 7.914911f, 16.08668f, 7.6932044f)
    cubicTo(16.085688f, 7.471498f, 16.203398f, 7.2662077f, 16.395237f, 7.1550674f)
    cubicTo(16.587078f, 7.043927f, 16.823719f, 7.043927f, 17.01556f, 7.1550674f)
    cubicTo(17.2074f, 7.2662077f, 17.32511f, 7.471498f, 17.324118f, 7.6932044f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(182, 185, 177, 255))
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
2.749492883682251f, 0.0f, 0.0f, 0.0f,
0.0f, 2.749492883682251f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-35.91004180908203f, 1.1998900175094604f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.324118f, 7.6932044f)
    cubicTo(17.32511f, 7.914911f, 17.2074f, 8.120201f, 17.01556f, 8.231341f)
    cubicTo(16.823719f, 8.342482f, 16.587078f, 8.342482f, 16.395237f, 8.231341f)
    cubicTo(16.203398f, 8.120201f, 16.085688f, 7.914911f, 16.08668f, 7.6932044f)
    cubicTo(16.085688f, 7.471498f, 16.203398f, 7.2662077f, 16.395237f, 7.1550674f)
    cubicTo(16.587078f, 7.043927f, 16.823719f, 7.043927f, 17.01556f, 7.1550674f)
    cubicTo(17.2074f, 7.2662077f, 17.32511f, 7.471498f, 17.324118f, 7.6932044f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(182, 185, 177, 255))
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
2.749492883682251f, 0.0f, 0.0f, 0.0f,
0.0f, 2.749492883682251f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-8.691448211669922f, 1.1998900175094604f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.324118f, 7.6932044f)
    cubicTo(17.32511f, 7.914911f, 17.2074f, 8.120201f, 17.01556f, 8.231341f)
    cubicTo(16.823719f, 8.342482f, 16.587078f, 8.342482f, 16.395237f, 8.231341f)
    cubicTo(16.203398f, 8.120201f, 16.085688f, 7.914911f, 16.08668f, 7.6932044f)
    cubicTo(16.085688f, 7.471498f, 16.203398f, 7.2662077f, 16.395237f, 7.1550674f)
    cubicTo(16.587078f, 7.043927f, 16.823719f, 7.043927f, 17.01556f, 7.1550674f)
    cubicTo(17.2074f, 7.2662077f, 17.32511f, 7.471498f, 17.324118f, 7.6932044f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(182, 185, 177, 255))
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
1.3576539754867554f, 0.0f, 0.0f, 0.0f,
0.0f, 1.3576539754867554f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.7698960304260254f, -0.4937349855899811f, 0.0f, 1.0f)
))}){
// _0_0_13
brush = Brush.radialGradient(0.0f to Color(243, 244, 255, 255), 1.0f to Color(145, 147, 175, 255), center = Offset(11.3292f, 10.58397f), radius = 15.53206f, tileMode = TileMode.Clamp)
stroke = Stroke(width=0.7365651f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.160713f, 16.910715f)
    cubicTo(31.184608f, 22.253685f, 28.34788f, 27.20105f, 23.724672f, 29.879456f)
    cubicTo(19.101465f, 32.55786f, 13.398534f, 32.55786f, 8.775327f, 29.879456f)
    cubicTo(4.15212f, 27.20105f, 1.3153913f, 22.253685f, 1.3392859f, 16.910715f)
    cubicTo(1.3153913f, 11.567745f, 4.15212f, 6.6203814f, 8.775327f, 3.9419744f)
    cubicTo(13.398534f, 1.2635677f, 19.101465f, 1.2635677f, 23.724672f, 3.9419744f)
    cubicTo(28.34788f, 6.6203814f, 31.184608f, 11.567745f, 31.160713f, 16.910715f)
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
0.6111270189285278f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6111270189285278f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
5.5440521240234375f, -66.92817687988281f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(69.375f, 125.0f)
    cubicTo(69.39803f, 130.151f, 66.66322f, 134.92062f, 62.206123f, 137.50279f)
    cubicTo(57.74902f, 140.08498f, 52.25098f, 140.08498f, 47.793877f, 137.50279f)
    cubicTo(43.336773f, 134.92062f, 40.601963f, 130.151f, 40.625f, 125.0f)
    cubicTo(40.601963f, 119.84899f, 43.336773f, 115.07938f, 47.793877f, 112.4972f)
    cubicTo(52.25098f, 109.91502f, 57.74902f, 109.91502f, 62.206123f, 112.4972f)
    cubicTo(66.66322f, 115.07938f, 69.39803f, 119.84899f, 69.375f, 125.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(255, 245, 32, 227), 1.0f to Color(255, 243, 0, 0), center = Offset(55.0f, 125.0f), radius = 14.375f, tileMode = TileMode.Clamp)
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
            return 1.560176134109497
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.6127699017524719
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 46.38090133666992
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.223236083984375
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

