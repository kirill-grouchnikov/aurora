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
class preferences_desktop_theme : Painter() {
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
alpha *= 0.54385966f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.232558012008667f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
12.041410446166992f, -9.914664268493652f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.516504f, 39.249554f)
    cubicTo(26.53173f, 40.959858f, 24.724045f, 42.543526f, 21.777939f, 43.40089f)
    cubicTo(18.831835f, 44.25826f, 15.19768f, 44.25826f, 12.2515745f, 43.40089f)
    cubicTo(9.305469f, 42.543526f, 7.497783f, 40.959858f, 7.51301f, 39.249554f)
    cubicTo(7.497783f, 37.53925f, 9.305469f, 35.95558f, 12.2515745f, 35.098217f)
    cubicTo(15.19768f, 34.24085f, 18.831835f, 34.24085f, 21.777939f, 35.098217f)
    cubicTo(24.724045f, 35.95558f, 26.53173f, 37.53925f, 26.516504f, 39.249554f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(17.014757f, 39.249573f), radius = 9.501747f, tileMode = TileMode.Clamp)
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
    moveTo(31.205997f, 5.2048745f)
    lineTo(34.908493f, 5.2048745f)
    cubicTo(39.19495f, 5.2048745f, 44.455605f, 6.5223904f, 44.455605f, 7.0610294f)
    lineTo(44.455605f, 29.688444f)
    cubicTo(44.455605f, 30.227083f, 44.018726f, 30.660715f, 43.476055f, 30.660715f)
    lineTo(22.638435f, 30.660715f)
    cubicTo(22.095766f, 30.660715f, 21.65889f, 30.227083f, 21.65889f, 29.688444f)
    lineTo(21.65889f, 7.0610294f)
    cubicTo(21.65889f, 6.5223904f, 26.830568f, 5.2048745f, 31.205997f, 5.2048745f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(214, 214, 214, 255), 1.0f to Color(240, 240, 240, 255), start = Offset(30.881643f, 17.932793f), end = Offset(29.399292f, 10.154616f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.205997f, 5.2048745f)
    lineTo(34.908493f, 5.2048745f)
    cubicTo(39.19495f, 5.2048745f, 44.455605f, 6.5223904f, 44.455605f, 7.0610294f)
    lineTo(44.455605f, 29.688444f)
    cubicTo(44.455605f, 30.227083f, 44.018726f, 30.660715f, 43.476055f, 30.660715f)
    lineTo(22.638435f, 30.660715f)
    cubicTo(22.095766f, 30.660715f, 21.65889f, 30.227083f, 21.65889f, 29.688444f)
    lineTo(21.65889f, 7.0610294f)
    cubicTo(21.65889f, 6.5223904f, 26.830568f, 5.2048745f, 31.205997f, 5.2048745f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.61988306f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.363447f, 6.0663853f)
    lineTo(34.751057f, 6.0663853f)
    cubicTo(38.672962f, 6.0663853f, 43.486214f, 7.5879025f, 43.486214f, 7.5879025f)
    lineTo(43.486214f, 29.657421f)
    cubicTo(43.486214f, 29.657421f, 22.62829f, 29.657421f, 22.62829f, 29.657421f)
    lineTo(22.62829f, 7.5879025f)
    cubicTo(22.62829f, 7.5879025f, 27.360134f, 6.0663853f, 31.363447f, 6.0663853f)
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.410797f, 10.508173f)
    lineTo(30.405594f, 17.314075f)
    lineTo(30.405594f, 29.600058f)
    lineTo(33.587574f, 32.428486f)
    lineTo(36.50439f, 29.600058f)
    lineTo(36.50439f, 17.1373f)
    lineTo(33.410797f, 10.508173f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(176, 176, 176, 255), 1.0f to Color(147, 147, 147, 255), start = Offset(32.93856f, 17.93842f), end = Offset(32.93856f, 14.220481f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.410797f, 10.508173f)
    lineTo(30.405594f, 17.314075f)
    lineTo(30.405594f, 29.600058f)
    lineTo(33.587574f, 32.428486f)
    lineTo(36.50439f, 29.600058f)
    lineTo(36.50439f, 17.1373f)
    lineTo(33.410797f, 10.508173f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.46783626f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(33.455025f, 27.747002f), end = Offset(33.455025f, 17.31718f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.4229f, 13.510439f)
    lineTo(31.417679f, 17.9217f)
    lineTo(31.417679f, 28.969769f)
    lineTo(33.551254f, 31.023417f)
    lineTo(35.492302f, 28.969769f)
    lineTo(35.492302f, 17.793346f)
    lineTo(33.4229f, 13.510439f)
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
15.114439964294434f, -11.15211009979248f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(176, 176, 176, 255), 1.0f to Color(117, 117, 117, 255), center = Offset(18.163805f, 22.632545f), radius = 2.8422909f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
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
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.057243f, 10.331398f)
    lineTo(28.01911f, 14.132097f)
    cubicTo(28.01911f, 14.132097f, 26.162954f, 8.565209f, 26.162954f, 6.0887585f)
    cubicTo(26.162954f, 3.6138842f, 27.48878f, 2.6416132f, 28.902992f, 2.6416132f)
    cubicTo(28.902992f, 2.6416132f, 37.299885f, 2.6416132f, 37.299885f, 2.6416132f)
    cubicTo(38.256462f, 2.6416132f, 39.92944f, 3.0393603f, 40.12831f, 5.9119825f)
    cubicTo(40.327187f, 8.784603f, 38.095383f, 14.132097f, 38.095383f, 14.132097f)
    lineTo(33.057243f, 10.331398f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(240, 240, 240, 255), 1.0f to Color(201, 201, 201, 255), center = Offset(28.909271f, 3.9674196f), radius = 13.500519f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.057243f, 10.331398f)
    lineTo(28.01911f, 14.132097f)
    cubicTo(28.01911f, 14.132097f, 26.162954f, 8.565209f, 26.162954f, 6.0887585f)
    cubicTo(26.162954f, 3.6138842f, 27.48878f, 2.6416132f, 28.902992f, 2.6416132f)
    cubicTo(28.902992f, 2.6416132f, 37.299885f, 2.6416132f, 37.299885f, 2.6416132f)
    cubicTo(38.256462f, 2.6416132f, 39.92944f, 3.0393603f, 40.12831f, 5.9119825f)
    cubicTo(40.327187f, 8.784603f, 38.095383f, 14.132097f, 38.095383f, 14.132097f)
    lineTo(33.057243f, 10.331398f)
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
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.915115f, 5.0280943f)
    cubicTo(29.915115f, 5.0280943f, 28.099472f, 5.5160723f, 28.41988f, 6.687217f)
    cubicTo(28.740288f, 7.8583636f, 31.196747f, 9.712676f, 31.196747f, 9.712676f)
    lineTo(35.789257f, 9.712676f)
    cubicTo(35.789257f, 9.712676f, 37.89861f, 8.15115f, 38.138916f, 6.9800043f)
    cubicTo(38.379223f, 5.8088584f, 36.75048f, 5.0280943f, 36.75048f, 5.0280943f)
    lineTo(29.915115f, 5.0280943f)
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
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.73834f, 4.055826f)
    cubicTo(29.73834f, 4.055826f, 27.922695f, 4.543804f, 28.243105f, 5.714949f)
    cubicTo(28.56351f, 6.8860955f, 31.019972f, 8.740409f, 31.019972f, 8.740409f)
    lineTo(35.61248f, 8.740409f)
    cubicTo(35.61248f, 8.740409f, 37.721832f, 7.178881f, 37.96214f, 6.007736f)
    cubicTo(38.202446f, 4.8365903f, 36.573704f, 4.055826f, 36.573704f, 4.055826f)
    lineTo(29.73834f, 4.055826f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(139, 139, 139, 255), 1.0f to Color(169, 169, 169, 255), center = Offset(33.095787f, 8.350008f), radius = 5.4947357f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.14035088f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.232558012008667f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
12.041410446166992f, -9.914664268493652f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.516504f, 39.249554f)
    cubicTo(26.53173f, 40.959858f, 24.724045f, 42.543526f, 21.777939f, 43.40089f)
    cubicTo(18.831835f, 44.25826f, 15.19768f, 44.25826f, 12.2515745f, 43.40089f)
    cubicTo(9.305469f, 42.543526f, 7.497783f, 40.959858f, 7.51301f, 39.249554f)
    cubicTo(7.497783f, 37.53925f, 9.305469f, 35.95558f, 12.2515745f, 35.098217f)
    cubicTo(15.19768f, 34.24085f, 18.831835f, 34.24085f, 21.777939f, 35.098217f)
    cubicTo(24.724045f, 35.95558f, 26.53173f, 37.53925f, 26.516504f, 39.249554f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(17.014757f, 39.249573f), radius = 9.501747f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(31.205997f, 5.2048745f)
    lineTo(34.908493f, 5.2048745f)
    cubicTo(39.19495f, 5.2048745f, 44.455605f, 6.5223904f, 44.455605f, 7.0610294f)
    lineTo(44.455605f, 29.688444f)
    cubicTo(44.455605f, 30.227083f, 44.018726f, 30.660715f, 43.476055f, 30.660715f)
    lineTo(22.638435f, 30.660715f)
    cubicTo(22.095766f, 30.660715f, 21.65889f, 30.227083f, 21.65889f, 29.688444f)
    lineTo(21.65889f, 7.0610294f)
    cubicTo(21.65889f, 6.5223904f, 26.830568f, 5.2048745f, 31.205997f, 5.2048745f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(80, 121, 173, 255), 1.0f to Color(114, 159, 207, 255), start = Offset(30.881643f, 17.932793f), end = Offset(29.399292f, 10.154616f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.205997f, 5.2048745f)
    lineTo(34.908493f, 5.2048745f)
    cubicTo(39.19495f, 5.2048745f, 44.455605f, 6.5223904f, 44.455605f, 7.0610294f)
    lineTo(44.455605f, 29.688444f)
    cubicTo(44.455605f, 30.227083f, 44.018726f, 30.660715f, 43.476055f, 30.660715f)
    lineTo(22.638435f, 30.660715f)
    cubicTo(22.095766f, 30.660715f, 21.65889f, 30.227083f, 21.65889f, 29.688444f)
    lineTo(21.65889f, 7.0610294f)
    cubicTo(21.65889f, 6.5223904f, 26.830568f, 5.2048745f, 31.205997f, 5.2048745f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.26315793f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.363447f, 6.0663853f)
    lineTo(34.751057f, 6.0663853f)
    cubicTo(38.672962f, 6.0663853f, 43.486214f, 7.5879025f, 43.486214f, 7.5879025f)
    lineTo(43.486214f, 29.657421f)
    cubicTo(43.486214f, 29.657421f, 22.62829f, 29.657421f, 22.62829f, 29.657421f)
    lineTo(22.62829f, 7.5879025f)
    cubicTo(22.62829f, 7.5879025f, 27.360134f, 6.0663853f, 31.363447f, 6.0663853f)
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
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.410797f, 10.508173f)
    cubicTo(33.410797f, 10.508173f, 30.405594f, 15.439076f, 30.405594f, 17.314075f)
    lineTo(30.405594f, 29.600058f)
    lineTo(33.587574f, 32.428486f)
    lineTo(36.50439f, 29.600058f)
    lineTo(36.50439f, 17.1373f)
    cubicTo(36.50439f, 15.3873f, 33.410797f, 10.508173f, 33.410797f, 10.508173f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(117, 80, 123, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(92, 53, 102, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.410797f, 10.508173f)
    cubicTo(33.410797f, 10.508173f, 30.405594f, 15.439076f, 30.405594f, 17.314075f)
    lineTo(30.405594f, 29.600058f)
    lineTo(33.587574f, 32.428486f)
    lineTo(36.50439f, 29.600058f)
    lineTo(36.50439f, 17.1373f)
    cubicTo(36.50439f, 15.3873f, 33.410797f, 10.508173f, 33.410797f, 10.508173f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.25146198f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(33.455025f, 27.747002f), end = Offset(33.455025f, 17.31718f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.4229f, 13.510439f)
    lineTo(31.417679f, 17.9217f)
    lineTo(31.417679f, 28.969769f)
    lineTo(33.551254f, 31.023417f)
    lineTo(35.492302f, 28.969769f)
    lineTo(35.492302f, 17.793346f)
    lineTo(33.4229f, 13.510439f)
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
15.114439964294434f, -11.15211009979248f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(117, 80, 123, 255), 1.0f to Color(84, 57, 88, 255), center = Offset(18.1638f, 22.544163f), radius = 5.317898f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(92, 53, 102, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
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
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.057243f, 10.331398f)
    lineTo(28.01911f, 14.132097f)
    cubicTo(28.01911f, 14.132097f, 26.162954f, 8.565209f, 26.162954f, 6.0887585f)
    cubicTo(26.162954f, 3.6138842f, 27.48878f, 2.6416132f, 28.902992f, 2.6416132f)
    cubicTo(28.902992f, 2.6416132f, 37.299885f, 2.6416132f, 37.299885f, 2.6416132f)
    cubicTo(38.256462f, 2.6416132f, 39.92944f, 3.0393603f, 40.12831f, 5.9119825f)
    cubicTo(40.327187f, 8.784603f, 38.095383f, 14.132097f, 38.095383f, 14.132097f)
    lineTo(33.057243f, 10.331398f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(200, 213, 230, 255), 1.0f to Color(66, 126, 191, 255), center = Offset(29.969925f, 4.4977713f), radius = 7.488949f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.057243f, 10.331398f)
    lineTo(28.01911f, 14.132097f)
    cubicTo(28.01911f, 14.132097f, 26.162954f, 8.565209f, 26.162954f, 6.0887585f)
    cubicTo(26.162954f, 3.6138842f, 27.48878f, 2.6416132f, 28.902992f, 2.6416132f)
    cubicTo(28.902992f, 2.6416132f, 37.299885f, 2.6416132f, 37.299885f, 2.6416132f)
    cubicTo(38.256462f, 2.6416132f, 39.92944f, 3.0393603f, 40.12831f, 5.9119825f)
    cubicTo(40.327187f, 8.784603f, 38.095383f, 14.132097f, 38.095383f, 14.132097f)
    lineTo(33.057243f, 10.331398f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.33333334f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.915115f, 5.0280943f)
    cubicTo(29.915115f, 5.0280943f, 28.099472f, 5.5160723f, 28.41988f, 6.687217f)
    cubicTo(28.740288f, 7.8583636f, 30.696747f, 9.712676f, 31.196747f, 9.712676f)
    lineTo(35.789257f, 9.712676f)
    cubicTo(36.539257f, 9.650176f, 37.89861f, 8.15115f, 38.138916f, 6.9800043f)
    cubicTo(38.379223f, 5.8088584f, 36.75048f, 5.0280943f, 36.75048f, 5.0280943f)
    lineTo(29.915115f, 5.0280943f)
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
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(29.73834f, 4.055826f)
    cubicTo(29.23834f, 4.055826f, 27.922695f, 4.543804f, 28.243105f, 5.714949f)
    cubicTo(28.56351f, 6.8860955f, 30.14274f, 8.740409f, 31.019972f, 8.740409f)
    lineTo(35.61248f, 8.740409f)
    cubicTo(36.30282f, 8.740409f, 37.721832f, 7.178881f, 37.96214f, 6.007736f)
    cubicTo(38.202446f, 4.8365903f, 37.073704f, 4.055826f, 36.573704f, 4.055826f)
    lineTo(29.73834f, 4.055826f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(32, 74, 135, 255), 1.0f to Color(20, 46, 85, 255), center = Offset(33.095787f, 8.740406f), radius = 10.427078f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28654972f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.145634f, 9.68365f)
    lineTo(37.83022f, 13.219183f)
    lineTo(39.067654f, 9.772038f)
    lineTo(37.565052f, 12.246911f)
    lineTo(33.145634f, 9.68365f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(33.76435f, 12.7330475f), end = Offset(33.76435f, 8.75459f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28654972f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.79208f, 9.860426f)
    lineTo(28.372663f, 13.219183f)
    lineTo(26.870062f, 6.5900598f)
    lineTo(28.902992f, 11.981746f)
    lineTo(32.79208f, 9.860426f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(33.76435f, 12.7330475f), end = Offset(33.76435f, 8.75459f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
alpha *= 0.54385966f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.232558012008667f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-6.0782389640808105f, 3.005204916000366f, 0.0f, 1.0f)
))}){
// _0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.516504f, 39.249554f)
    cubicTo(26.53173f, 40.959858f, 24.724045f, 42.543526f, 21.777939f, 43.40089f)
    cubicTo(18.831835f, 44.25826f, 15.19768f, 44.25826f, 12.2515745f, 43.40089f)
    cubicTo(9.305469f, 42.543526f, 7.497783f, 40.959858f, 7.51301f, 39.249554f)
    cubicTo(7.497783f, 37.53925f, 9.305469f, 35.95558f, 12.2515745f, 35.098217f)
    cubicTo(15.19768f, 34.24085f, 18.831835f, 34.24085f, 21.777939f, 35.098217f)
    cubicTo(24.724045f, 35.95558f, 26.53173f, 37.53925f, 26.516504f, 39.249554f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(17.014757f, 39.249573f), radius = 9.501747f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.086385f, 18.12474f)
    lineTo(16.78888f, 18.12474f)
    cubicTo(21.075336f, 18.12474f, 26.33599f, 19.442257f, 26.33599f, 19.980894f)
    lineTo(26.33599f, 42.60831f)
    cubicTo(26.33599f, 43.14695f, 25.899113f, 43.58058f, 25.356443f, 43.58058f)
    lineTo(4.5188212f, 43.58058f)
    cubicTo(3.9761531f, 43.58058f, 3.5392747f, 43.14695f, 3.5392747f, 42.60831f)
    lineTo(3.5392747f, 19.980894f)
    cubicTo(3.5392747f, 19.442257f, 8.710954f, 18.12474f, 13.086385f, 18.12474f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(214, 214, 214, 255), 1.0f to Color(240, 240, 240, 255), start = Offset(12.761994f, 30.852661f), end = Offset(11.279642f, 23.074486f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.086385f, 18.12474f)
    lineTo(16.78888f, 18.12474f)
    cubicTo(21.075336f, 18.12474f, 26.33599f, 19.442257f, 26.33599f, 19.980894f)
    lineTo(26.33599f, 42.60831f)
    cubicTo(26.33599f, 43.14695f, 25.899113f, 43.58058f, 25.356443f, 43.58058f)
    lineTo(4.5188212f, 43.58058f)
    cubicTo(3.9761531f, 43.58058f, 3.5392747f, 43.14695f, 3.5392747f, 42.60831f)
    lineTo(3.5392747f, 19.980894f)
    cubicTo(3.5392747f, 19.442257f, 8.710954f, 18.12474f, 13.086385f, 18.12474f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.61988306f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.243834f, 18.986252f)
    lineTo(16.631443f, 18.986252f)
    cubicTo(20.55335f, 18.986252f, 25.3666f, 20.507769f, 25.3666f, 20.507769f)
    lineTo(25.3666f, 42.57729f)
    cubicTo(25.3666f, 42.57729f, 4.508677f, 42.57729f, 4.508677f, 42.57729f)
    lineTo(4.508677f, 20.507769f)
    cubicTo(4.508677f, 20.507769f, 9.2405205f, 18.986252f, 13.243834f, 18.986252f)
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
// _0_1_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.291184f, 23.42804f)
    lineTo(12.285981f, 30.233944f)
    lineTo(12.285981f, 42.519924f)
    lineTo(15.467961f, 45.34835f)
    lineTo(18.384777f, 42.519924f)
    lineTo(18.384777f, 30.057167f)
    lineTo(15.291184f, 23.42804f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(176, 176, 176, 255), 1.0f to Color(147, 147, 147, 255), start = Offset(14.8189125f, 30.85829f), end = Offset(14.8189125f, 27.14035f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.291184f, 23.42804f)
    lineTo(12.285981f, 30.233944f)
    lineTo(12.285981f, 42.519924f)
    lineTo(15.467961f, 45.34835f)
    lineTo(18.384777f, 42.519924f)
    lineTo(18.384777f, 30.057167f)
    lineTo(15.291184f, 23.42804f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.46783626f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_4
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(15.335377f, 40.66687f), end = Offset(15.335377f, 30.23705f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.303288f, 26.430305f)
    lineTo(13.298065f, 30.841566f)
    lineTo(13.298065f, 41.889637f)
    lineTo(15.431641f, 43.943283f)
    lineTo(17.372688f, 41.889637f)
    lineTo(17.372688f, 30.713213f)
    lineTo(15.303288f, 26.430305f)
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
-3.0052080154418945f, 1.7677680253982544f, 0.0f, 1.0f)
))}){
// _0_1_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(176, 176, 176, 255), 1.0f to Color(117, 117, 117, 255), center = Offset(18.163805f, 22.632545f), radius = 2.8422909f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
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
// _0_1_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.937632f, 23.251265f)
    lineTo(9.899495f, 27.051964f)
    cubicTo(9.899495f, 27.051964f, 8.04334f, 21.485075f, 8.04334f, 19.008623f)
    cubicTo(8.043341f, 16.53375f, 9.369165f, 15.561479f, 10.78338f, 15.561479f)
    cubicTo(10.78338f, 15.561479f, 19.180273f, 15.561479f, 19.180273f, 15.561479f)
    cubicTo(20.13685f, 15.561479f, 21.809826f, 15.959226f, 22.0087f, 18.831848f)
    cubicTo(22.207575f, 21.70447f, 19.975767f, 27.051964f, 19.975767f, 27.051964f)
    lineTo(14.937632f, 23.251265f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(240, 240, 240, 255), 1.0f to Color(201, 201, 201, 255), center = Offset(10.789677f, 16.887306f), radius = 13.500521f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.937632f, 23.251265f)
    lineTo(9.899495f, 27.051964f)
    cubicTo(9.899495f, 27.051964f, 8.04334f, 21.485075f, 8.04334f, 19.008623f)
    cubicTo(8.043341f, 16.53375f, 9.369165f, 15.561479f, 10.78338f, 15.561479f)
    cubicTo(10.78338f, 15.561479f, 19.180273f, 15.561479f, 19.180273f, 15.561479f)
    cubicTo(20.13685f, 15.561479f, 21.809826f, 15.959226f, 22.0087f, 18.831848f)
    cubicTo(22.207575f, 21.70447f, 19.975767f, 27.051964f, 19.975767f, 27.051964f)
    lineTo(14.937632f, 23.251265f)
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
// _0_1_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.795503f, 17.94796f)
    cubicTo(11.795503f, 17.94796f, 9.979857f, 18.435938f, 10.300266f, 19.607082f)
    cubicTo(10.620674f, 20.778229f, 13.077134f, 22.632542f, 13.077134f, 22.632542f)
    lineTo(17.669645f, 22.632542f)
    cubicTo(17.669645f, 22.632542f, 19.778996f, 21.071014f, 20.019302f, 19.89987f)
    cubicTo(20.259607f, 18.728724f, 18.630869f, 17.94796f, 18.630869f, 17.94796f)
    lineTo(11.795503f, 17.94796f)
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
// _0_1_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.618727f, 16.975693f)
    cubicTo(11.618727f, 16.975693f, 9.8030815f, 17.46367f, 10.12349f, 18.634815f)
    cubicTo(10.443897f, 19.805962f, 12.900358f, 21.660275f, 12.900358f, 21.660275f)
    lineTo(17.492868f, 21.660275f)
    cubicTo(17.492868f, 21.660275f, 19.602219f, 20.098747f, 19.842525f, 18.927603f)
    cubicTo(20.082832f, 17.756456f, 18.454092f, 16.975693f, 18.454092f, 16.975693f)
    lineTo(11.618727f, 16.975693f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(139, 139, 139, 255), 1.0f to Color(169, 169, 169, 255), center = Offset(14.976143f, 21.269875f), radius = 5.4947376f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.14035088f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.232558012008667f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-6.0782389640808105f, 3.005204916000366f, 0.0f, 1.0f)
))}){
// _0_1_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(26.516504f, 39.249554f)
    cubicTo(26.53173f, 40.959858f, 24.724045f, 42.543526f, 21.777939f, 43.40089f)
    cubicTo(18.831835f, 44.25826f, 15.19768f, 44.25826f, 12.2515745f, 43.40089f)
    cubicTo(9.305469f, 42.543526f, 7.497783f, 40.959858f, 7.51301f, 39.249554f)
    cubicTo(7.497783f, 37.53925f, 9.305469f, 35.95558f, 12.2515745f, 35.098217f)
    cubicTo(15.19768f, 34.24085f, 18.831835f, 34.24085f, 21.777939f, 35.098217f)
    cubicTo(24.724045f, 35.95558f, 26.53173f, 37.53925f, 26.516504f, 39.249554f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(17.014757f, 39.249573f), radius = 9.501747f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.086385f, 18.12474f)
    lineTo(16.78888f, 18.12474f)
    cubicTo(21.075336f, 18.12474f, 26.33599f, 19.442257f, 26.33599f, 19.980894f)
    lineTo(26.33599f, 42.60831f)
    cubicTo(26.33599f, 43.14695f, 25.899113f, 43.58058f, 25.356443f, 43.58058f)
    lineTo(4.5188212f, 43.58058f)
    cubicTo(3.9761531f, 43.58058f, 3.5392747f, 43.14695f, 3.5392747f, 42.60831f)
    lineTo(3.5392747f, 19.980894f)
    cubicTo(3.5392747f, 19.442257f, 8.710954f, 18.12474f, 13.086385f, 18.12474f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(214, 214, 214, 255), 1.0f to Color(240, 240, 240, 255), start = Offset(12.761994f, 30.852661f), end = Offset(11.279642f, 23.074486f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.086385f, 18.12474f)
    lineTo(16.78888f, 18.12474f)
    cubicTo(21.075336f, 18.12474f, 26.33599f, 19.442257f, 26.33599f, 19.980894f)
    lineTo(26.33599f, 42.60831f)
    cubicTo(26.33599f, 43.14695f, 25.899113f, 43.58058f, 25.356443f, 43.58058f)
    lineTo(4.5188212f, 43.58058f)
    cubicTo(3.9761531f, 43.58058f, 3.5392747f, 43.14695f, 3.5392747f, 42.60831f)
    lineTo(3.5392747f, 19.980894f)
    cubicTo(3.5392747f, 19.442257f, 8.710954f, 18.12474f, 13.086385f, 18.12474f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.61988306f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_11
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.243834f, 18.986252f)
    lineTo(16.631443f, 18.986252f)
    cubicTo(20.55335f, 18.986252f, 25.3666f, 20.507769f, 25.3666f, 20.507769f)
    lineTo(25.3666f, 42.57729f)
    cubicTo(25.3666f, 42.57729f, 4.508677f, 42.57729f, 4.508677f, 42.57729f)
    lineTo(4.508677f, 20.507769f)
    cubicTo(4.508677f, 20.507769f, 9.2405205f, 18.986252f, 13.243834f, 18.986252f)
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
// _0_1_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.291184f, 23.42804f)
    cubicTo(15.291184f, 23.42804f, 12.285981f, 27.845821f, 12.285981f, 30.233944f)
    lineTo(12.285981f, 42.519924f)
    lineTo(15.467961f, 45.34835f)
    lineTo(18.384777f, 42.519924f)
    lineTo(18.384777f, 30.057167f)
    cubicTo(18.384777f, 28.057167f, 15.291184f, 23.42804f, 15.291184f, 23.42804f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(85, 87, 83, 255), 1.0f to Color(124, 127, 121, 255), start = Offset(15.335379f, 28.377787f), end = Offset(15.335379f, 34.388195f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(46, 52, 54, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.291184f, 23.42804f)
    cubicTo(15.291184f, 23.42804f, 12.285981f, 27.845821f, 12.285981f, 30.233944f)
    lineTo(12.285981f, 42.519924f)
    lineTo(15.467961f, 45.34835f)
    lineTo(18.384777f, 42.519924f)
    lineTo(18.384777f, 30.057167f)
    cubicTo(18.384777f, 28.057167f, 15.291184f, 23.42804f, 15.291184f, 23.42804f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.46783626f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_13
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(15.335377f, 40.66687f), end = Offset(15.335377f, 30.23705f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999976f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.303288f, 26.430305f)
    lineTo(13.298065f, 30.841566f)
    lineTo(13.298065f, 41.889637f)
    lineTo(15.431641f, 43.943283f)
    lineTo(17.372688f, 41.889637f)
    lineTo(17.372688f, 30.713213f)
    lineTo(15.303288f, 26.430305f)
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
-3.0052080154418945f, 1.7677680253982544f, 0.0f, 1.0f)
))}){
// _0_1_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(128, 131, 125, 255), 1.0f to Color(76, 77, 74, 255), center = Offset(18.163805f, 22.36738f), radius = 2.8422909f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(46, 52, 54, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.506096f, 23.251263f)
    cubicTo(20.50985f, 23.948053f, 20.064234f, 24.59325f, 19.337984f, 24.942549f)
    cubicTo(18.611736f, 25.291845f, 17.715874f, 25.291845f, 16.989626f, 24.942549f)
    cubicTo(16.263376f, 24.59325f, 15.81776f, 23.948053f, 15.821514f, 23.251263f)
    cubicTo(15.81776f, 22.554472f, 16.263376f, 21.909275f, 16.989626f, 21.559977f)
    cubicTo(17.715874f, 21.21068f, 18.611736f, 21.21068f, 19.337984f, 21.559977f)
    cubicTo(20.064234f, 21.909275f, 20.50985f, 22.554472f, 20.506096f, 23.251263f)
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
// _0_1_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.937632f, 23.251265f)
    lineTo(9.899495f, 27.051964f)
    cubicTo(9.899495f, 27.051964f, 8.04334f, 21.485075f, 8.04334f, 19.008623f)
    cubicTo(8.043341f, 16.53375f, 9.369165f, 15.561479f, 10.78338f, 15.561479f)
    cubicTo(10.78338f, 15.561479f, 19.180273f, 15.561479f, 19.180273f, 15.561479f)
    cubicTo(20.13685f, 15.561479f, 21.809826f, 15.959226f, 22.0087f, 18.831848f)
    cubicTo(22.207575f, 21.70447f, 19.975767f, 27.051964f, 19.975767f, 27.051964f)
    lineTo(14.937632f, 23.251265f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(240, 240, 240, 255), 1.0f to Color(201, 201, 201, 255), center = Offset(10.789677f, 16.887306f), radius = 13.500521f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(148, 148, 148, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.937632f, 23.251265f)
    lineTo(9.899495f, 27.051964f)
    cubicTo(9.899495f, 27.051964f, 8.04334f, 21.485075f, 8.04334f, 19.008623f)
    cubicTo(8.043341f, 16.53375f, 9.369165f, 15.561479f, 10.78338f, 15.561479f)
    cubicTo(10.78338f, 15.561479f, 19.180273f, 15.561479f, 19.180273f, 15.561479f)
    cubicTo(20.13685f, 15.561479f, 21.809826f, 15.959226f, 22.0087f, 18.831848f)
    cubicTo(22.207575f, 21.70447f, 19.975767f, 27.051964f, 19.975767f, 27.051964f)
    lineTo(14.937632f, 23.251265f)
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
// _0_1_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.795503f, 17.94796f)
    cubicTo(11.795503f, 17.94796f, 9.979857f, 18.435938f, 10.300266f, 19.607082f)
    cubicTo(10.620674f, 20.778229f, 12.007307f, 22.632542f, 13.077134f, 22.632542f)
    lineTo(17.669645f, 22.632542f)
    cubicTo(18.546875f, 22.632542f, 19.778996f, 21.071014f, 20.019302f, 19.89987f)
    cubicTo(20.259607f, 18.728724f, 18.630869f, 17.94796f, 18.630869f, 17.94796f)
    lineTo(11.795503f, 17.94796f)
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
// _0_1_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.618727f, 16.975693f)
    cubicTo(11.618727f, 16.975693f, 9.8030815f, 17.46367f, 10.12349f, 18.634815f)
    cubicTo(10.443897f, 19.805962f, 11.775358f, 21.660275f, 12.900358f, 21.660275f)
    lineTo(17.492868f, 21.660275f)
    cubicTo(18.242868f, 21.660275f, 19.602219f, 20.098747f, 19.842525f, 18.927603f)
    cubicTo(20.082832f, 17.756456f, 18.454092f, 16.975693f, 18.454092f, 16.975693f)
    lineTo(11.618727f, 16.975693f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(139, 139, 139, 255), 1.0f to Color(169, 169, 169, 255), center = Offset(14.976143f, 21.269875f), radius = 5.4947376f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.61988306f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.937632f, 22.720934f)
    lineTo(19.622215f, 26.256468f)
    lineTo(20.859652f, 22.809322f)
    lineTo(19.35705f, 25.284197f)
    lineTo(14.937632f, 22.720934f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.61988306f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.584078f, 22.89771f)
    lineTo(10.164659f, 26.256468f)
    lineTo(8.662058f, 19.627342f)
    lineTo(10.69499f, 25.019032f)
    lineTo(14.584078f, 22.89771f)
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
            return 3.0392746925354004
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 2.141613245010376
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 41.91632843017578
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.907508850097656
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

