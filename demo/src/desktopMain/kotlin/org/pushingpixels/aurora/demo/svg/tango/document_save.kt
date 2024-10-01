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
class document_save : Painter() {
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
alpha *= 0.56f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0525330305099487f, 0.0f, 0.0f, 0.0f,
0.0f, 0.3631129860877991f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.5117570161819458f, 31.99485969543457f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(46.757435f, 27.096155f)
    cubicTo(46.79518f, 32.591297f, 42.313805f, 37.67956f, 35.010204f, 40.434254f)
    cubicTo(27.7066f, 43.188942f, 18.69728f, 43.188942f, 11.393679f, 40.434254f)
    cubicTo(4.0900764f, 37.67956f, -0.39130166f, 32.591297f, -0.35355377f, 27.096155f)
    cubicTo(-0.39130166f, 21.601015f, 4.0900764f, 16.512747f, 11.393679f, 13.758058f)
    cubicTo(18.69728f, 11.003368f, 27.7066f, 11.003368f, 35.010204f, 13.758058f)
    cubicTo(42.313805f, 16.512747f, 46.79518f, 21.601015f, 46.757435f, 27.096155f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(23.20194f, 27.096157f), radius = 23.555494f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
brush = SolidColor(Color(83, 83, 83, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.28569f, 13.087628f)
    cubicTo(10.66069f, 13.087628f, 10.254441f, 13.377808f, 10.004442f, 13.931381f)
    cubicTo(10.004441f, 13.931381f, 3.5356915f, 31.034939f, 3.5356915f, 31.034939f)
    cubicTo(3.5356915f, 31.034939f, 3.2856915f, 31.706497f, 3.2856915f, 32.81619f)
    cubicTo(3.2856915f, 32.81619f, 3.2856915f, 42.466156f, 3.2856915f, 42.466156f)
    cubicTo(3.2856915f, 43.54877f, 3.943477f, 44.09116f, 4.9419417f, 44.091156f)
    lineTo(43.50444f, 44.091156f)
    cubicTo(44.489292f, 44.091156f, 45.09819f, 43.372974f, 45.09819f, 42.247406f)
    lineTo(45.09819f, 32.59744f)
    cubicTo(45.09819f, 32.59744f, 45.20415f, 31.827015f, 45.00444f, 31.284939f)
    lineTo(38.28569f, 14.087631f)
    cubicTo(38.101166f, 13.575725f, 37.648785f, 13.099533f, 37.16069f, 13.087628f)
    lineTo(11.28569f, 13.087628f)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.2735915f, 32.12181f)
    lineTo(4.0381937f, 31.429598f)
    lineTo(41.647884f, 31.492098f)
    lineTo(45.11029f, 31.809395f)
    lineTo(45.11029f, 42.24793f)
    cubicTo(45.11029f, 43.373497f, 44.503273f, 44.09126f, 43.518417f, 44.09126f)
    lineTo(4.9354315f, 44.09126f)
    cubicTo(3.9369667f, 44.09126f, 3.2735915f, 43.549206f, 3.2735915f, 42.466595f)
    lineTo(3.2735915f, 32.12181f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(187, 187, 187, 255), 1.0f to Color(159, 159, 159, 255), start = Offset(7.6046205f, 33.60618f), end = Offset(36.183067f, 46.068935f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(3.5490842f, 31.039404f)
    cubicTo(2.8347986f, 32.50369f, 3.5484686f, 33.432262f, 4.5847983f, 33.432262f)
    cubicTo(4.5847983f, 33.432262f, 43.584797f, 33.432262f, 43.584797f, 33.432262f)
    cubicTo(44.703842f, 33.40845f, 45.430035f, 32.420357f, 45.013367f, 31.289404f)
    lineTo(38.299084f, 14.078704f)
    cubicTo(38.11456f, 13.566798f, 37.64432f, 13.090606f, 37.156223f, 13.078701f)
    lineTo(11.299083f, 13.078701f)
    cubicTo(10.674083f, 13.078701f, 10.263369f, 13.382274f, 10.01337f, 13.935847f)
    cubicTo(10.01337f, 13.935847f, 3.5490842f, 31.039404f, 3.5490842f, 31.039404f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(228, 228, 228, 255), 1.0f to Color(211, 211, 211, 255), center = Offset(15.571496f, 15.880486f), radius = 31.511501f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
shape = Outline.Rectangle(rect = Rect(left = 7.857995986938477f, top = 36.2991828918457f, right = 25.482995986938477f, bottom = 41.8616828918457f))
brush = Brush.linearGradient(0.0f to Color(131, 131, 131, 255), 1.0f to Color(187, 187, 187, 0), start = Offset(7.857993f, 39.08043f), end = Offset(25.482992f, 39.08043f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.81142855f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.857995f, 41.86168f)
    cubicTo(7.857995f, 41.86168f, 7.857995f, 37.850197f, 7.857995f, 37.850197f)
    cubicTo(9.693522f, 41.029423f, 16.154486f, 41.86168f, 20.795492f, 41.86168f)
    cubicTo(20.795492f, 41.86168f, 7.857995f, 41.86168f, 7.857995f, 41.86168f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(238, 238, 238, 255), 1.0f to Color(238, 238, 238, 0), start = Offset(12.277412f, 42.33081f), end = Offset(12.221823f, 38.883667f), tileMode = TileMode.Clamp)
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
    moveTo(44.79616f, 30.753689f)
    cubicTo(44.859684f, 32.003662f, 44.38216f, 33.069527f, 43.474045f, 33.09744f)
    cubicTo(43.474045f, 33.09744f, 5.3553295f, 33.09744f, 5.3553295f, 33.09744f)
    cubicTo(4.0660977f, 33.09744f, 3.4875937f, 32.77249f, 3.271279f, 32.22938f)
    cubicTo(3.3630404f, 33.173714f, 4.0970964f, 33.87869f, 5.3553295f, 33.87869f)
    cubicTo(5.3553295f, 33.87869f, 43.474045f, 33.87869f, 43.474045f, 33.87869f)
    cubicTo(44.550053f, 33.845615f, 45.226852f, 32.454662f, 44.82621f, 30.883898f)
    lineTo(44.79616f, 30.753689f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.69142854f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(10.96875f, 15.28125f)
    cubicTo(10.922675f, 15.481571f, 10.78125f, 15.668047f, 10.78125f, 15.875f)
    cubicTo(10.78125f, 16.823605f, 11.37223f, 17.664474f, 12.125f, 18.46875f)
    cubicTo(12.365268f, 18.314674f, 12.490117f, 18.114342f, 12.75f, 17.96875f)
    cubicTo(11.809691f, 17.152746f, 11.196604f, 16.252169f, 10.96875f, 15.28125f)
    close()
    moveTo(37.625f, 15.28125f)
    cubicTo(37.396275f, 16.250866f, 36.78299f, 17.153675f, 35.84375f, 17.96875f)
    cubicTo(36.117893f, 18.122332f, 36.247738f, 18.33699f, 36.5f, 18.5f)
    cubicTo(37.257263f, 17.693344f, 37.8125f, 16.826956f, 37.8125f, 15.875f)
    cubicTo(37.8125f, 15.668047f, 37.670906f, 15.481571f, 37.625f, 15.28125f)
    close()
    moveTo(39.8125f, 23.71875f)
    cubicTo(39.198708f, 27.758862f, 32.513885f, 30.96875f, 24.28125f, 30.96875f)
    cubicTo(16.068996f, 30.968752f, 9.421101f, 27.775965f, 8.78125f, 23.75f)
    cubicTo(8.748893f, 23.947132f, 8.65625f, 24.141882f, 8.65625f, 24.34375f)
    cubicTo(8.65625f, 28.661697f, 15.645354f, 32.1875f, 24.28125f, 32.1875f)
    cubicTo(32.917145f, 32.1875f, 39.9375f, 28.661697f, 39.9375f, 24.34375f)
    cubicTo(39.9375f, 24.130827f, 39.84845f, 23.926394f, 39.8125f, 23.71875f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 65), 1.0f to Color(255, 255, 255, 255), start = Offset(23.688078f, 16.443836f), end = Offset(23.688078f, 31.482183f), tileMode = TileMode.Clamp)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.08838842809200287f, 5.3017802238464355f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.57367f, 25.593554f)
    cubicTo(8.575866f, 25.957785f, 8.315223f, 26.295048f, 7.890435f, 26.477634f)
    cubicTo(7.4656477f, 26.660223f, 6.9416537f, 26.660223f, 6.516866f, 26.477634f)
    cubicTo(6.0920787f, 26.295048f, 5.8314357f, 25.957785f, 5.833631f, 25.593554f)
    cubicTo(5.8314357f, 25.229322f, 6.0920787f, 24.89206f, 6.516866f, 24.709473f)
    cubicTo(6.9416537f, 24.526884f, 7.4656477f, 24.526884f, 7.890435f, 24.709473f)
    cubicTo(8.315223f, 24.89206f, 8.575866f, 25.229322f, 8.57367f, 25.593554f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 117))
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
33.96704864501953f, 5.213389873504639f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.57367f, 25.593554f)
    cubicTo(8.575866f, 25.957785f, 8.315223f, 26.295048f, 7.890435f, 26.477634f)
    cubicTo(7.4656477f, 26.660223f, 6.9416537f, 26.660223f, 6.516866f, 26.477634f)
    cubicTo(6.0920787f, 26.295048f, 5.8314357f, 25.957785f, 5.833631f, 25.593554f)
    cubicTo(5.8314357f, 25.229322f, 6.0920787f, 24.89206f, 6.516866f, 24.709473f)
    cubicTo(6.9416537f, 24.526884f, 7.4656477f, 24.526884f, 7.890435f, 24.709473f)
    cubicTo(8.315223f, 24.89206f, 8.575866f, 25.229322f, 8.57367f, 25.593554f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 117))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(12.378357f, 9.558136f), end = Offset(44.0961f, 52.745636f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.642515f, 13.540723f)
    cubicTo(11.040823f, 13.540723f, 10.649724f, 13.820081f, 10.409049f, 14.35301f)
    cubicTo(10.409048f, 14.35301f, 3.994034f, 30.943731f, 3.994034f, 30.943731f)
    cubicTo(3.994034f, 30.943731f, 3.7533574f, 31.590246f, 3.7533574f, 32.658554f)
    cubicTo(3.7533574f, 32.658554f, 3.7533574f, 41.94865f, 3.7533574f, 41.94865f)
    cubicTo(3.7533574f, 43.30339f, 4.1974134f, 43.57555f, 5.3478413f, 43.57555f)
    lineTo(43.034744f, 43.57555f)
    cubicTo(44.357872f, 43.57555f, 44.56906f, 43.25915f, 44.56906f, 41.738056f)
    lineTo(44.56906f, 32.447964f)
    cubicTo(44.56906f, 32.447964f, 44.67107f, 31.70627f, 44.478806f, 31.184408f)
    lineTo(37.885616f, 14.378434f)
    cubicTo(37.707973f, 13.885617f, 37.334965f, 13.552184f, 36.86507f, 13.540723f)
    lineTo(11.642515f, 13.540723f)
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
// _0_0_11
brush = SolidColor(Color(255, 255, 255, 108))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.5f, 36.554165f)
    lineTo(40.5f, 41.5751f)
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
brush = SolidColor(Color(255, 255, 255, 108))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(38.5f, 36.61394f)
    lineTo(38.5f, 41.634876f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
brush = SolidColor(Color(255, 255, 255, 108))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(36.5f, 36.61394f)
    lineTo(36.5f, 41.634876f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
brush = SolidColor(Color(255, 255, 255, 108))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.5f, 36.61394f)
    lineTo(34.5f, 41.634876f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15
brush = SolidColor(Color(255, 255, 255, 108))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.5f, 36.61394f)
    lineTo(32.5f, 41.634876f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
brush = SolidColor(Color(255, 255, 255, 108))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(30.5f, 36.61394f)
    lineTo(30.5f, 41.634876f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.09714284f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_17
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.5f, 36.604065f)
    lineTo(39.5f, 41.625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.09714284f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_18
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.5f, 36.66384f)
    lineTo(37.5f, 41.684776f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.09714284f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_19
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.5f, 36.66384f)
    lineTo(35.5f, 41.684776f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.09714284f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_20
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.5f, 36.66384f)
    lineTo(33.5f, 41.684776f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.09714284f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_21
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Square, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(31.5f, 36.66384f)
    lineTo(31.5f, 41.684776f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.44f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_22
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.875f, 36.3125f)
    lineTo(7.875f, 41.84375f)
    lineTo(20.4375f, 41.84375f)
    lineTo(8.21875f, 41.5f)
    lineTo(7.875f, 36.3125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.20571427f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.037814974784851f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0607470273971558f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.6328779458999634f, 3.030369997024536f, 0.0f, 1.0f)
))}){
// _0_0_23
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(39.875f, 19.5625f)
    cubicTo(39.898838f, 21.958838f, 37.068905f, 24.177746f, 32.456768f, 25.379019f)
    cubicTo(27.844637f, 26.580294f, 22.155363f, 26.580294f, 17.54323f, 25.379019f)
    cubicTo(12.931097f, 24.177746f, 10.101163f, 21.958838f, 10.125f, 19.5625f)
    cubicTo(10.101163f, 17.166162f, 12.931097f, 14.947254f, 17.54323f, 13.745981f)
    cubicTo(22.155363f, 12.544707f, 27.844637f, 12.544707f, 32.456768f, 13.745981f)
    cubicTo(37.068905f, 14.947254f, 39.898838f, 17.166162f, 39.875f, 19.5625f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.5f to Color(230, 230, 230, 255), 0.75f to Color(255, 255, 255, 255), 0.84166664f to Color(225, 225, 225, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(33.431175f, 31.964777f), end = Offset(21.747974f, 11.780679f), tileMode = TileMode.Clamp)
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
alpha *= 0.14117648f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1301900148391724f, 1.178179022145491E-16f, 0.0f, 0.0f,
7.91854375568848E-17f, -0.7596009969711304f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-3.9097249507904053f, 53.6655387878418f, 0.0f, 1.0f)
))}){
// _0_1_0
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
// _0_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.2034502f, 25.835194f)
    cubicTo(2.1729476f, -5.385337f, 28.741615f, -0.4511153f, 28.582417f, 15.788689f)
    lineTo(35.89533f, 15.788689f)
    lineTo(24.517653f, 28.774672f)
    lineTo(12.585426f, 15.788689f)
    cubicTo(12.585426f, 15.788689f, 20.12686f, 15.788689f, 20.12686f, 15.788689f)
    cubicTo(20.583921f, 4.8193226f, 3.4092324f, 1.6100346f, 3.2034502f, 25.835194f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(52, 101, 164, 255), 1.0f to Color(52, 101, 164, 0), start = Offset(14.751649f, 15.868432f), end = Offset(8.8953285f, 16.743431f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(32, 74, 135, 255), 1.0f to Color(32, 74, 135, 0), start = Offset(12.25f, 18.25f), end = Offset(7.0f, 21.118431f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.2034502f, 25.835194f)
    cubicTo(2.1729476f, -5.385337f, 28.741615f, -0.4511153f, 28.582417f, 15.788689f)
    lineTo(35.89533f, 15.788689f)
    lineTo(24.517653f, 28.774672f)
    lineTo(12.585426f, 15.788689f)
    cubicTo(12.585426f, 15.788689f, 20.12686f, 15.788689f, 20.12686f, 15.788689f)
    cubicTo(20.583921f, 4.8193226f, 3.4092324f, 1.6100346f, 3.2034502f, 25.835194f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.4715909f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(26.351824f, 22.334444f), end = Offset(7.671385f, 8.796097f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999934f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.6642103f, 9.104105f)
    cubicTo(12.40638f, -0.0400306f, 28.122335f, 2.7175443f, 27.761604f, 16.579393f)
    lineTo(34.078976f, 16.579393f)
    cubicTo(34.078976f, 16.579393f, 24.513151f, 27.53677f, 24.513151f, 27.53677f)
    lineTo(14.41668f, 16.579393f)
    cubicTo(14.41668f, 16.579393f, 20.87332f, 16.579393f, 20.87332f, 16.579393f)
    cubicTo(21.144976f, 5.0041614f, 10.922265f, 5.5345216f, 7.6642103f, 9.104105f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.49431816f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.767155f, 16.211613f)
    lineTo(32.782978f, 18.757322f)
    cubicTo(27.372948f, 17.24103f, 24.89683f, 21.486664f, 17.109283f, 20.489113f)
    lineTo(13.247998f, 16.080076f)
    lineTo(20.434467f, 16.162863f)
    cubicTo(20.48322f, 4.3164573f, 8.34431f, 4.998966f, 5.0292664f, 13.627829f)
    cubicTo(8.83722f, -1.2611216f, 27.893316f, 0.8064118f, 28.28332f, 16.114113f)
    lineTo(34.767155f, 16.211613f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(15.334518f, 3.4830432f), radius = 25.057837f, tileMode = TileMode.Clamp)
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
            return 2.462188482284546
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
            return 44.96506881713867
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

