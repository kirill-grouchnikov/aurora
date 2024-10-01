package org.pushingpixels.aurora.tools.screenshot.svg.tango

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
class edit_cut : Painter() {
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
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.174313f, 1.6249996f)
    cubicTo(34.38626f, 1.6935354f, 34.59157f, 1.7696619f, 34.798294f, 1.842502f)
    cubicTo(35.44971f, 4.0395036f, 38.469776f, 6.261222f, 37.321354f, 8.449133f)
    cubicTo(33.49551f, 14.82952f, 29.697021f, 21.294565f, 25.89976f, 27.72527f)
    cubicTo(25.154013f, 27.872171f, 24.401731f, 27.952183f, 23.647995f, 27.96996f)
    cubicTo(22.061604f, 28.01017f, 20.433064f, 27.775465f, 18.927431f, 27.23589f)
    cubicTo(23.978304f, 18.684616f, 29.031301f, 10.114483f, 34.174313f, 1.6249996f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(238, 238, 236, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(35.14795f, 7.782281f), end = Offset(18.768555f, 32.403976f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.174313f, 1.6249996f)
    cubicTo(34.38626f, 1.6935354f, 34.59157f, 1.7696619f, 34.798294f, 1.842502f)
    cubicTo(35.44971f, 4.0395036f, 38.469776f, 6.261222f, 37.321354f, 8.449133f)
    cubicTo(33.49551f, 14.82952f, 29.697021f, 21.294565f, 25.89976f, 27.72527f)
    cubicTo(25.154013f, 27.872171f, 24.401731f, 27.952183f, 23.647995f, 27.96996f)
    cubicTo(22.061604f, 28.01017f, 20.433064f, 27.775465f, 18.927431f, 27.23589f)
    cubicTo(23.978304f, 18.684616f, 29.031301f, 10.114483f, 34.174313f, 1.6249996f)
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
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.288822f, 4.25f)
    cubicTo(34.0577f, 4.5574527f, 33.839207f, 5.120942f, 33.602795f, 5.40625f)
    cubicTo(29.555939f, 12.158979f, 25.440784f, 18.90033f, 21.378977f, 25.625f)
    cubicTo(21.318424f, 25.878117f, 20.565046f, 26.637291f, 21.366936f, 26.567963f)
    cubicTo(22.478493f, 26.765842f, 23.638681f, 26.918568f, 24.746761f, 26.625f)
    cubicTo(28.505753f, 20.407795f, 32.19264f, 14.142582f, 35.943047f, 7.923178f)
    cubicTo(36.28552f, 7.5359044f, 36.35216f, 6.99792f, 35.9924f, 6.611197f)
    cubicTo(35.462387f, 5.794589f, 34.925465f, 4.936482f, 34.382374f, 4.15625f)
    lineTo(34.311813f, 4.2269607f)
    lineTo(34.288822f, 4.25f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(226, 226, 226, 255), 1.0f to Color(216, 216, 216, 255), start = Offset(20.288025f, 6.460365f), end = Offset(24.32597f, 23.942537f), tileMode = TileMode.Clamp)
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
3.637892961502075f, 0.0f, 0.0f, 0.0f,
0.0f, 3.4703750610351562f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1056.115966796875f, -16.007240295410156f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(297.04443f, 12.300293f)
    lineTo(296.3994f, 13.384766f)
    lineTo(295.1328f, 14.71875f)
    lineTo(294.73242f, 13.672852f)
    lineTo(295.74658f, 11.960449f)
    lineTo(297.04443f, 12.300293f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 53, 53, 255), 1.0E-9f to Color(201, 26, 26, 255), 1.0f to Color(255, 76, 76, 255), start = Offset(294.59497f, 12.153336f), end = Offset(297.18515f, 13.317198f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(154, 12, 0, 255))
stroke = Stroke(width=0.28144068f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(297.04443f, 12.300293f)
    lineTo(296.3994f, 13.384766f)
    lineTo(295.1328f, 14.71875f)
    lineTo(294.73242f, 13.672852f)
    lineTo(295.74658f, 11.960449f)
    lineTo(297.04443f, 12.300293f)
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.40625f, 26.96875f)
    cubicTo(19.183905f, 27.455467f, 19.192232f, 29.00393f, 18.481272f, 29.932762f)
    cubicTo(18.138948f, 30.648558f, 17.537483f, 31.27899f, 17.28125f, 32.03125f)
    cubicTo(17.27157f, 32.546642f, 17.729202f, 33.391476f, 18.3125f, 32.9375f)
    cubicTo(19.697475f, 31.791172f, 20.876865f, 30.39882f, 21.756725f, 28.810629f)
    cubicTo(21.989088f, 28.320597f, 22.552477f, 27.916466f, 22.625f, 27.40625f)
    cubicTo(22.086432f, 26.835442f, 21.112183f, 26.873224f, 20.40625f, 26.96875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 53, 53, 255), 1.0E-9f to Color(201, 26, 26, 255), 1.0f to Color(255, 76, 76, 255), start = Offset(22.46875f, 37.807575f), end = Offset(22.625f, 18.26949f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.960099f, 1.6249996f)
    cubicTo(12.751966f, 1.6935354f, 12.550355f, 1.7696619f, 12.347353f, 1.842502f)
    cubicTo(11.707669f, 4.0395036f, 8.741988f, 6.261222f, 9.86973f, 8.449133f)
    cubicTo(13.626677f, 14.82952f, 17.35676f, 21.294565f, 21.08564f, 27.72527f)
    cubicTo(21.817957f, 27.872171f, 22.55669f, 27.952183f, 23.296852f, 27.96996f)
    cubicTo(24.854677f, 28.01017f, 26.45389f, 27.775465f, 27.932407f, 27.23589f)
    cubicTo(22.972492f, 18.684616f, 18.010492f, 10.114483f, 12.960099f, 1.6249996f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(238, 238, 236, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(12.004395f, 7.782281f), end = Offset(28.088745f, 32.403976f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(136, 138, 133, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.960099f, 1.6249996f)
    cubicTo(12.751966f, 1.6935354f, 12.550355f, 1.7696619f, 12.347353f, 1.842502f)
    cubicTo(11.707669f, 4.0395036f, 8.741988f, 6.261222f, 9.86973f, 8.449133f)
    cubicTo(13.626677f, 14.82952f, 17.35676f, 21.294565f, 21.08564f, 27.72527f)
    cubicTo(21.817957f, 27.872171f, 22.55669f, 27.952183f, 23.296852f, 27.96996f)
    cubicTo(24.854677f, 28.01017f, 26.45389f, 27.775465f, 27.932407f, 27.23589f)
    cubicTo(22.972492f, 18.684616f, 18.010492f, 10.114483f, 12.960099f, 1.6249996f)
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.719667f, 4.25f)
    cubicTo(12.336632f, 5.3766794f, 11.270006f, 6.2059646f, 11.004855f, 7.40625f)
    cubicTo(14.713376f, 13.800362f, 18.475798f, 20.175379f, 22.181757f, 26.5625f)
    cubicTo(23.380123f, 26.820799f, 24.610197f, 26.655657f, 25.795113f, 26.40625f)
    cubicTo(25.606339f, 25.665808f, 25.056911f, 25.07532f, 24.76513f, 24.3767f)
    cubicTo(20.870525f, 17.806173f, 16.94143f, 11.242872f, 13.087127f, 4.65625f)
    cubicTo(13.072466f, 4.50464f, 12.870425f, 4.1721153f, 12.719667f, 4.25f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(226, 226, 226, 255), 1.0f to Color(216, 216, 216, 255), start = Offset(20.288025f, 6.460365f), end = Offset(24.32597f, 23.942537f), tileMode = TileMode.Clamp)
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
0.9798930287361145f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.311383992433548f, 0.17404299974441528f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(24.190449f, 23.843431f)
    cubicTo(24.192022f, 24.195501f, 24.0051f, 24.521502f, 23.700459f, 24.697992f)
    cubicTo(23.395819f, 24.874483f, 23.02003f, 24.874483f, 22.71539f, 24.697992f)
    cubicTo(22.410748f, 24.521502f, 22.223825f, 24.195501f, 22.225399f, 23.843431f)
    cubicTo(22.223825f, 23.491362f, 22.410748f, 23.165361f, 22.71539f, 22.98887f)
    cubicTo(23.02003f, 22.81238f, 23.395819f, 22.81238f, 23.700459f, 22.98887f)
    cubicTo(24.0051f, 23.165361f, 24.192022f, 23.491362f, 24.190449f, 23.843431f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(186, 189, 182, 255), 1.0f to Color(238, 238, 236, 255), start = Offset(22.225399f, 23.843431f), end = Offset(24.190449f, 22.860907f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.26704544f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2560549974441528f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8191490173339844f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-7.199394226074219f, 9.090420722961426f, 0.0f, 1.0f)
))}){
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.25f, 41.625f)
    cubicTo(43.278946f, 43.730194f, 39.842598f, 45.679516f, 34.24215f, 46.73484f)
    cubicTo(28.6417f, 47.790165f, 21.7333f, 47.790165f, 16.13285f, 46.73484f)
    cubicTo(10.532403f, 45.679516f, 7.0960546f, 43.730194f, 7.125f, 41.625f)
    cubicTo(7.0960546f, 39.519806f, 10.532403f, 37.570484f, 16.13285f, 36.51516f)
    cubicTo(21.7333f, 35.459835f, 28.6417f, 35.459835f, 34.24215f, 36.51516f)
    cubicTo(39.842598f, 37.570484f, 43.278946f, 39.519806f, 43.25f, 41.625f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(25.1875f, 41.625015f), radius = 18.0625f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
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
    moveTo(17.700394f, 30.286934f)
    cubicTo(20.935404f, 32.013584f, 21.19623f, 36.899853f, 18.278337f, 41.201286f)
    cubicTo(15.360479f, 45.50525f, 10.373849f, 47.596474f, 7.1373806f, 45.87742f)
    cubicTo(3.9008825f, 44.15077f, 3.6415462f, 39.267033f, 6.5594354f, 34.965595f)
    cubicTo(9.475807f, 30.664165f, 14.463925f, 28.572945f, 17.700394f, 30.286934f)
    close()
    moveTo(15.845268f, 33.02908f)
    cubicTo(14.408745f, 32.26545f, 11.33781f, 33.5696f, 9.378926f, 36.463108f)
    cubicTo(7.4160166f, 39.356613f, 7.5560293f, 42.376625f, 8.991202f, 43.13795f)
    cubicTo(10.426348f, 43.90618f, 13.499985f, 42.59743f, 15.458868f, 39.703926f)
    cubicTo(17.42313f, 36.81042f, 17.281765f, 33.79271f, 15.845268f, 33.02908f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 53, 53, 255), 1.0E-9f to Color(201, 26, 26, 255), 1.0f to Color(255, 76, 76, 255), start = Offset(5.917134f, 31.089365f), end = Offset(26.793049f, 50.659687f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(164, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.700394f, 30.286934f)
    cubicTo(20.935404f, 32.013584f, 21.19623f, 36.899853f, 18.278337f, 41.201286f)
    cubicTo(15.360479f, 45.50525f, 10.373849f, 47.596474f, 7.1373806f, 45.87742f)
    cubicTo(3.9008825f, 44.15077f, 3.6415462f, 39.267033f, 6.5594354f, 34.965595f)
    cubicTo(9.475807f, 30.664165f, 14.463925f, 28.572945f, 17.700394f, 30.286934f)
    close()
    moveTo(15.845268f, 33.02908f)
    cubicTo(14.408745f, 32.26545f, 11.33781f, 33.5696f, 9.378926f, 36.463108f)
    cubicTo(7.4160166f, 39.356613f, 7.5560293f, 42.376625f, 8.991202f, 43.13795f)
    cubicTo(10.426348f, 43.90618f, 13.499985f, 42.59743f, 15.458868f, 39.703926f)
    cubicTo(17.42313f, 36.81042f, 17.281765f, 33.79271f, 15.845268f, 33.02908f)
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
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.3255f, 30.583288f)
    cubicTo(12.400369f, 30.97051f, 10.691041f, 32.037308f, 9.278593f, 33.06453f)
    cubicTo(8.52683f, 33.759434f, 8.035029f, 34.514454f, 7.362945f, 35.31874f)
    cubicTo(5.654618f, 37.670807f, 4.938707f, 40.76217f, 6.290107f, 43.38841f)
    cubicTo(6.90956f, 44.841515f, 8.932742f, 45.435852f, 10.658323f, 45.067543f)
    cubicTo(12.110236f, 44.819077f, 13.339639f, 43.90647f, 14.470735f, 43.268642f)
    cubicTo(15.391637f, 42.47786f, 16.02475f, 41.64213f, 16.803625f, 40.677364f)
    cubicTo(18.612986f, 38.20296f, 19.595537f, 34.928688f, 18.101604f, 32.16508f)
    cubicTo(17.377897f, 31.022951f, 15.866963f, 30.41829f, 14.3255f, 30.583288f)
    close()
    moveTo(14.797513f, 31.54477f)
    cubicTo(16.814016f, 31.795124f, 18.154488f, 33.577583f, 17.92006f, 35.266636f)
    cubicTo(17.940832f, 37.553574f, 16.774038f, 39.710728f, 15.196909f, 41.500755f)
    cubicTo(13.779705f, 42.902737f, 11.848294f, 44.229027f, 9.532754f, 44.137077f)
    cubicTo(8.1739f, 44.13421f, 7.100179f, 43.224777f, 6.716933f, 42.176617f)
    cubicTo(6.1002936f, 39.644695f, 6.9116497f, 36.91139f, 8.683129f, 34.83862f)
    cubicTo(10.041367f, 33.315308f, 11.877976f, 31.95152f, 14.150642f, 31.596926f)
    cubicTo(14.366331f, 31.581652f, 14.581522f, 31.554432f, 14.797513f, 31.54477f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 53, 53, 255), 1.0E-9f to Color(201, 26, 26, 255), 1.0f to Color(255, 76, 76, 255), start = Offset(13.396414f, 39.832123f), end = Offset(6.2447085f, 2.3727791f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(30.331764f, 30.286934f)
    cubicTo(27.096752f, 32.013584f, 26.83593f, 36.899853f, 29.75382f, 41.201286f)
    cubicTo(32.67168f, 45.50525f, 37.65831f, 47.596474f, 40.894775f, 45.87742f)
    cubicTo(44.131275f, 44.15077f, 44.39061f, 39.267033f, 41.47272f, 34.965595f)
    cubicTo(38.55635f, 30.664165f, 33.568233f, 28.572945f, 30.331764f, 30.286934f)
    close()
    moveTo(32.18689f, 33.02908f)
    cubicTo(33.623413f, 32.26545f, 36.694347f, 33.5696f, 38.653233f, 36.463108f)
    cubicTo(40.616142f, 39.356613f, 40.476128f, 42.376625f, 39.040955f, 43.13795f)
    cubicTo(37.60581f, 43.90618f, 34.532173f, 42.59743f, 32.57329f, 39.703926f)
    cubicTo(30.609028f, 36.81042f, 30.750393f, 33.79271f, 32.18689f, 33.02908f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(239, 53, 53, 255), 1.0E-9f to Color(201, 26, 26, 255), 1.0f to Color(255, 76, 76, 255), center = Offset(34.37609f, 37.46486f), radius = 8.388786f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(164, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.331764f, 30.286934f)
    cubicTo(27.096752f, 32.013584f, 26.83593f, 36.899853f, 29.75382f, 41.201286f)
    cubicTo(32.67168f, 45.50525f, 37.65831f, 47.596474f, 40.894775f, 45.87742f)
    cubicTo(44.131275f, 44.15077f, 44.39061f, 39.267033f, 41.47272f, 34.965595f)
    cubicTo(38.55635f, 30.664165f, 33.568233f, 28.572945f, 30.331764f, 30.286934f)
    close()
    moveTo(32.18689f, 33.02908f)
    cubicTo(33.623413f, 32.26545f, 36.694347f, 33.5696f, 38.653233f, 36.463108f)
    cubicTo(40.616142f, 39.356613f, 40.476128f, 42.376625f, 39.040955f, 43.13795f)
    cubicTo(37.60581f, 43.90618f, 34.532173f, 42.59743f, 32.57329f, 39.703926f)
    cubicTo(30.609028f, 36.81042f, 30.750393f, 33.79271f, 32.18689f, 33.02908f)
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
3.6244380474090576f, 0.0f, 0.0f, 0.0f,
0.0f, 3.6244380474090576f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1053.178955078125f, -16.847200393676758f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(296.95605f, 12.300293f)
    lineTo(297.6001f, 13.384766f)
    lineTo(298.8672f, 14.71875f)
    lineTo(299.26807f, 13.672852f)
    lineTo(298.2539f, 11.960449f)
    lineTo(296.95605f, 12.300293f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(223, 42, 42, 255), 1.0f to Color(223, 42, 42, 0), start = Offset(298.47852f, 13.599585f), end = Offset(298.86948f, 13.802949f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(154, 12, 0, 255), 1.0f to Color(154, 12, 0, 0), start = Offset(298.47852f, 13.599585f), end = Offset(298.86948f, 13.802949f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.2759049f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(296.95605f, 12.300293f)
    lineTo(297.6001f, 13.384766f)
    lineTo(298.8672f, 14.71875f)
    lineTo(299.26807f, 13.672852f)
    lineTo(298.2539f, 11.960449f)
    lineTo(296.95605f, 12.300293f)
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
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.15625f, 27.9375f)
    cubicTo(25.729502f, 28.13632f, 25.139437f, 28.13898f, 24.8125f, 28.4375f)
    cubicTo(25.76252f, 29.838888f, 26.702412f, 31.352161f, 27.66338f, 32.650078f)
    cubicTo(28.331932f, 33.40462f, 29.019194f, 34.150303f, 29.78125f, 34.8125f)
    cubicTo(30.516527f, 33.421078f, 29.91641f, 31.751291f, 28.96875f, 30.625f)
    cubicTo(28.366215f, 29.725307f, 28.138927f, 28.512037f, 27.125f, 28.03125f)
    cubicTo(26.820951f, 27.91284f, 26.474384f, 27.853373f, 26.15625f, 27.9375f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 53, 53, 255), 1.0E-9f to Color(201, 26, 26, 255), 1.0f to Color(255, 76, 76, 255), start = Offset(22.416504f, 26.690367f), end = Offset(26.172241f, 22.828024f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.280087f, 30.449093f)
    cubicTo(30.759703f, 30.678844f, 29.385141f, 31.534748f, 29.039639f, 32.837055f)
    cubicTo(27.908495f, 35.23251f, 28.824762f, 37.95057f, 30.319418f, 40.063908f)
    cubicTo(31.421345f, 41.40911f, 32.259487f, 42.99382f, 33.959f, 43.83788f)
    cubicTo(35.429653f, 44.7615f, 37.300144f, 45.72845f, 39.17664f, 45.138767f)
    cubicTo(40.689957f, 44.70532f, 41.547314f, 43.4582f, 41.85681f, 42.166912f)
    cubicTo(42.461243f, 39.85688f, 41.561115f, 37.49095f, 40.149845f, 35.53043f)
    cubicTo(39.491173f, 34.616722f, 38.81686f, 33.64722f, 38.03653f, 32.83578f)
    cubicTo(36.84197f, 31.93233f, 35.398613f, 31.184254f, 33.94769f, 30.603432f)
    cubicTo(33.41359f, 30.49302f, 32.832462f, 30.37069f, 32.280087f, 30.449093f)
    close()
    moveTo(32.715794f, 31.658699f)
    cubicTo(34.473095f, 31.591923f, 35.950306f, 32.398155f, 37.092163f, 33.427666f)
    cubicTo(38.12446f, 34.396793f, 39.113815f, 35.23287f, 39.754673f, 36.42654f)
    cubicTo(40.831856f, 38.24711f, 41.142532f, 40.4065f, 40.594776f, 42.39007f)
    cubicTo(40.0664f, 43.714584f, 38.36862f, 44.36211f, 36.803658f, 44.00652f)
    cubicTo(34.821777f, 43.77769f, 33.586315f, 42.335503f, 32.277092f, 41.19816f)
    cubicTo(30.771343f, 39.76677f, 29.83647f, 37.719532f, 29.76651f, 35.715782f)
    cubicTo(29.780622f, 34.698112f, 29.740042f, 33.53736f, 30.464653f, 32.682213f)
    cubicTo(30.876926f, 32.13906f, 31.84466f, 31.627886f, 32.715794f, 31.658699f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(239, 53, 53, 255), 1.0f to Color(164, 0, 0, 0), start = Offset(40.92143f, 44.27075f), end = Offset(-9.216463f, -11.735834f), tileMode = TileMode.Clamp)
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
            return 1.749769687652588
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.124945044517517
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 45.37544250488281
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 46.87505340576172
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

