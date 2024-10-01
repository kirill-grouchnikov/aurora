package org.pushingpixels.aurora.demo.svg.random

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
class face_kiss : Painter() {
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
alpha *= 0.27058825f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(44.42857f, 38.57143f)
    cubicTo(44.460163f, 40.926178f, 40.709564f, 43.10657f, 34.596966f, 44.286995f)
    cubicTo(28.484367f, 45.46742f, 20.944202f, 45.46742f, 14.831604f, 44.286995f)
    cubicTo(8.719005f, 43.10657f, 4.9684076f, 40.926178f, 5.0f, 38.57143f)
    cubicTo(4.9684076f, 36.216682f, 8.719005f, 34.03629f, 14.831604f, 32.855865f)
    cubicTo(20.944202f, 31.675442f, 28.484367f, 31.675442f, 34.596966f, 32.855865f)
    cubicTo(40.709564f, 34.03629f, 44.460163f, 36.216682f, 44.42857f, 38.57143f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.714285f, 38.57142f), radius = 19.714285f, tileMode = TileMode.Clamp)
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
2.083142042160034f, 0.0f, 0.0f, 0.0f,
0.0f, 2.083142042160034f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-40.547149658203125f, -16.49224090576172f, 0.0f, 1.0f)
))}){
// _0_0_1
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
brush = ShaderBrush(org.jetbrains.skia.Shader.makeTwoPointConicalGradient(x0 = 29.158466f, y0 = 15.755712f, r0 = 0.0f, x1 = 29.28807f, y1 = 15.720984f, r1 = 8.902081f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 255, g = 252, b = 222), org.jetbrains.skia.Color.makeARGB(a = 255, r = 246, g = 231, b = 106), org.jetbrains.skia.Color.makeARGB(a = 255, r = 255, g = 183, b = 56), ), positions = floatArrayOf(0.0f, 0.6448598f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null)))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(156, 140, 10, 255))
stroke = Stroke(width=0.48004404f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
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
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.6772152f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.979781985282898f, 0.0f, 0.0f, 0.0f,
0.0f, 1.979781985282898f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-37.33127975463867f, -14.527460098266602f, 0.0f, 1.0f)
))}){
// _0_0_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=0.5051063f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
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
-0.9659259915351868f, -0.25881901383399963f, 0.0f, 0.0f,
0.25881901383399963f, -0.9659259915351868f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
24.534420013427734f, 17.201309204101562f, 0.0f, 1.0f)
))}){
// _0_0_3
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-0.875f, 1.875f)
    cubicTo(-0.875f, 3.1762414f, -1.7679925f, 4.315251f, -3.052219f, 4.6520367f)
    cubicTo(-4.336446f, 4.988823f, -5.691685f, 4.439408f, -6.3564496f, 3.3125f)
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
-0.9659259915351868f, -0.25881901383399963f, 0.0f, 0.0f,
0.25881901383399963f, -0.9659259915351868f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
15.784420013427734f, 17.201309204101562f, 0.0f, 1.0f)
))}){
// _0_0_4
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-0.875f, 1.875f)
    cubicTo(-0.875f, 3.1762414f, -1.7679925f, 4.315251f, -3.052219f, 4.6520367f)
    cubicTo(-4.336446f, 4.988823f, -5.691685f, 4.439408f, -6.3564496f, 3.3125f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.20555556f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.569526f, 26.222095f)
    cubicTo(15.569526f, 26.222095f, 20.308098f, 24.892973f, 21.988094f, 24.873535f)
    cubicTo(23.66809f, 24.8541f, 22.289513f, 26.144342f, 24.507944f, 26.118675f)
    cubicTo(26.663515f, 26.093735f, 25.65386f, 25.056585f, 27.255268f, 25.038057f)
    cubicTo(28.856676f, 25.01953f, 33.069145f, 26.019619f, 33.069145f, 26.019619f)
    cubicTo(30.7796f, 30.353035f, 32.883854f, 35.670177f, 24.488995f, 35.670177f)
    cubicTo(15.330963f, 35.670177f, 18.590937f, 29.30734f, 15.569526f, 26.222095f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.319334f, 34.337654f), radius = 11.843401f, tileMode = TileMode.Clamp)
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
    moveTo(16.11024f, 26.303276f)
    cubicTo(16.11024f, 26.303276f, 32.597942f, 26.490776f, 32.597942f, 26.490776f)
    cubicTo(30.217201f, 25.0f, 29.946346f, 21.213388f, 28.154512f, 21.0f)
    cubicTo(25.20563f, 22.104671f, 24.054214f, 22.193886f, 21.141375f, 21.0f)
    cubicTo(19.287043f, 21.489277f, 17.994148f, 25.144056f, 16.11024f, 26.303276f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(252, 194, 194, 255), 1.0f to Color(204, 0, 0, 255), center = Offset(24.604174f, 21.365164f), radius = 2.457037f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(164, 0, 0, 255))
stroke = Stroke(width=0.9999997f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.11024f, 26.303276f)
    cubicTo(16.11024f, 26.303276f, 32.597942f, 26.490776f, 32.597942f, 26.490776f)
    cubicTo(30.217201f, 25.0f, 29.946346f, 21.213388f, 28.154512f, 21.0f)
    cubicTo(25.20563f, 22.104671f, 24.054214f, 22.193886f, 21.141375f, 21.0f)
    cubicTo(19.287043f, 21.489277f, 17.994148f, 25.144056f, 16.11024f, 26.303276f)
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
    moveTo(17.160517f, 26.255438f)
    cubicTo(17.160517f, 26.255438f, 21.109268f, 25.24186f, 22.509247f, 25.227037f)
    cubicTo(23.909222f, 25.212215f, 25.0f, 26.5f, 25.0f, 26.5f)
    cubicTo(25.0f, 26.5f, 25.564007f, 25.36663f, 26.898495f, 25.3525f)
    cubicTo(28.23298f, 25.33837f, 31.743322f, 26.10103f, 31.743322f, 26.10103f)
    cubicTo(29.835394f, 29.405659f, 31.588915f, 33.46047f, 24.5933f, 33.46047f)
    cubicTo(16.961716f, 33.46047f, 19.678322f, 28.60822f, 17.160517f, 26.255438f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(250, 146, 146, 255), 1.0f to Color(204, 0, 0, 255), center = Offset(25.054567f, 26.943333f), radius = 2.892928f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(164, 0, 0, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.160517f, 26.255438f)
    cubicTo(17.160517f, 26.255438f, 21.109268f, 25.24186f, 22.509247f, 25.227037f)
    cubicTo(23.909222f, 25.212215f, 25.0f, 26.5f, 25.0f, 26.5f)
    cubicTo(25.0f, 26.5f, 25.564007f, 25.36663f, 26.898495f, 25.3525f)
    cubicTo(28.23298f, 25.33837f, 31.743322f, 26.10103f, 31.743322f, 26.10103f)
    cubicTo(29.835394f, 29.405659f, 31.588915f, 33.46047f, 24.5933f, 33.46047f)
    cubicTo(16.961716f, 33.46047f, 19.678322f, 28.60822f, 17.160517f, 26.255438f)
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
-0.31437599658966064f, 0.9492989778518677f, 0.0f, 0.0f,
0.9492989778518677f, 0.31437599658966064f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
29.095399856567383f, 15.15050983428955f, 0.0f, 1.0f)
))}){
// _0_0_8
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-0.875f, 1.875f)
    cubicTo(-0.875f, 2.7186134f, -1.2535756f, 3.5196557f, -1.9104304f, 4.065906f)
    cubicTo(-2.5672853f, 4.612157f, -3.4361262f, 4.8484855f, -4.2867703f, 4.7122808f)
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
-0.24359199404716492f, 0.7355570197105408f, 0.0f, 0.0f,
0.7355570197105408f, 0.24359199404716492f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
28.133020401000977f, 13.866080284118652f, 0.0f, 1.0f)
))}){
// _0_0_9
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.2905844f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-0.875f, 1.875f)
    cubicTo(-0.875f, 2.7000043f, -1.2371213f, 3.4852536f, -1.8692578f, 4.0310183f)
    cubicTo(-2.5013943f, 4.5767827f, -3.3426235f, 4.830464f, -4.178977f, 4.727538f)
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
-0.24359199404716492f, 0.7355570197105408f, 0.0f, 0.0f,
0.7355570197105408f, 0.24359199404716492f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
25.633020401000977f, 13.616080284118652f, 0.0f, 1.0f)
))}){
// _0_0_10
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.2905844f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1.1427273f, 3.074174f)
    cubicTo(-1.7041996f, 4.2715774f, -3.025402f, 4.9422364f, -4.348891f, 4.701663f)
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
0.31437599658966064f, 0.9492989778518677f, 0.0f, 0.0f,
-0.9492989778518677f, 0.31437599658966064f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
19.431129455566406f, 15.15050983428955f, 0.0f, 1.0f)
))}){
// _0_0_11
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-0.875f, 1.875f)
    cubicTo(-0.875f, 2.7186134f, -1.2535756f, 3.5196557f, -1.9104304f, 4.065906f)
    cubicTo(-2.5672853f, 4.612157f, -3.4361262f, 4.8484855f, -4.2867703f, 4.7122808f)
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
0.24359199404716492f, 0.7355570197105408f, 0.0f, 0.0f,
-0.7355570197105408f, 0.24359199404716492f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
20.393510818481445f, 13.866080284118652f, 0.0f, 1.0f)
))}){
// _0_0_12
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.2905844f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-0.875f, 1.875f)
    cubicTo(-0.875f, 2.7000043f, -1.2371213f, 3.4852536f, -1.8692578f, 4.0310183f)
    cubicTo(-2.5013943f, 4.5767827f, -3.3426235f, 4.830464f, -4.178977f, 4.727538f)
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
0.24359199404716492f, 0.7355570197105408f, 0.0f, 0.0f,
-0.7355570197105408f, 0.24359199404716492f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
22.893510818481445f, 13.616080284118652f, 0.0f, 1.0f)
))}){
// _0_0_13
brush = SolidColor(Color(85, 87, 83, 255))
stroke = Stroke(width=1.2905844f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1.1427273f, 3.074174f)
    cubicTo(-1.7041996f, 4.2715774f, -3.025402f, 4.9422364f, -4.348891f, 4.701663f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
            return 4.999802112579346
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 4.4802446365356445
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 39.4289665222168
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 40.69206619262695
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

