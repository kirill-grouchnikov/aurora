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
class help_browser : Painter() {
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
alpha *= 0.6306818f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1738029718399048f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6000000238418579f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-5.004403114318848f, 20.325000762939453f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.875f, 36.75f)
    cubicTo(40.900238f, 40.109352f, 37.90384f, 43.21997f, 33.020405f, 44.904f)
    cubicTo(28.136969f, 46.588028f, 22.113031f, 46.588028f, 17.229597f, 44.904f)
    cubicTo(12.346162f, 43.21997f, 9.34976f, 40.109352f, 9.375f, 36.75f)
    cubicTo(9.34976f, 33.390648f, 12.346162f, 30.28003f, 17.229597f, 28.596f)
    cubicTo(22.113031f, 26.911972f, 28.136969f, 26.911972f, 33.020405f, 28.596f)
    cubicTo(37.90384f, 30.28003f, 40.900238f, 33.390648f, 40.875f, 36.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(25.125f, 36.749996f), radius = 15.75f, tileMode = TileMode.Clamp)
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
0.9384419918060303f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9386799931526184f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
1.564074993133545f, 1.6339060068130493f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.785164f, 23.825787f)
    cubicTo(45.82022f, 31.664677f, 41.65834f, 38.923157f, 34.875446f, 42.852757f)
    cubicTo(28.09255f, 46.782352f, 19.725546f, 46.782352f, 12.942651f, 42.852757f)
    cubicTo(6.1597557f, 38.923157f, 1.9978756f, 31.664677f, 2.0329323f, 23.825787f)
    cubicTo(1.9978756f, 15.986897f, 6.1597557f, 8.728418f, 12.942651f, 4.7988186f)
    cubicTo(19.725546f, 0.8692189f, 28.09255f, 0.8692189f, 34.875446f, 4.7988186f)
    cubicTo(41.65834f, 8.728418f, 45.82022f, 15.986897f, 45.785164f, 23.825787f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(156, 188, 222, 255), 1.0f to Color(32, 74, 135, 255), center = Offset(25.693232f, 28.868702f), radius = 27.709608f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(32, 74, 135, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.785164f, 23.825787f)
    cubicTo(45.82022f, 31.664677f, 41.65834f, 38.923157f, 34.875446f, 42.852757f)
    cubicTo(28.09255f, 46.782352f, 19.725546f, 46.782352f, 12.942651f, 42.852757f)
    cubicTo(6.1597557f, 38.923157f, 1.9978756f, 31.664677f, 2.0329323f, 23.825787f)
    cubicTo(1.9978756f, 15.986897f, 6.1597557f, 8.728418f, 12.942651f, 4.7988186f)
    cubicTo(19.725546f, 0.8692189f, 28.09255f, 0.8692189f, 34.875446f, 4.7988186f)
    cubicTo(41.65834f, 8.728418f, 45.82022f, 15.986897f, 45.785164f, 23.825787f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.96022725f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8551030158996582f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8552129864692688f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
3.555288076400757f, 3.625019073486328f, 0.0f, 1.0f)
))}){
// _0_0_2
brush = SolidColor(Color(255, 255, 255, 255))
stroke = Stroke(width=3.0307744f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.785164f, 23.825787f)
    cubicTo(45.82022f, 31.664677f, 41.65834f, 38.923157f, 34.875446f, 42.852757f)
    cubicTo(28.09255f, 46.782352f, 19.725546f, 46.782352f, 12.942651f, 42.852757f)
    cubicTo(6.1597557f, 38.923157f, 1.9978756f, 31.664677f, 2.0329323f, 23.825787f)
    cubicTo(1.9978756f, 15.986897f, 6.1597557f, 8.728418f, 12.942651f, 4.7988186f)
    cubicTo(19.725546f, 0.8692189f, 28.09255f, 0.8692189f, 34.875446f, 4.7988186f)
    cubicTo(41.65834f, 8.728418f, 45.82022f, 15.986897f, 45.785164f, 23.825787f)
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
0.8498950004577637f, 0.0f, 0.0f, 0.0f,
0.0f, 0.8352050185203552f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
41.72980880737305f, 8.548327445983887f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-20.25f, 5.875f)
    cubicTo(-21.30902f, 5.875026f, -22.397636f, 5.9982357f, -23.53125f, 6.21875f)
    cubicTo(-24.664175f, 6.4391785f, -25.911411f, 6.756263f, -27.28125f, 7.21875f)
    cubicTo(-27.291632f, 7.21754f, -27.302118f, 7.21754f, -27.3125f, 7.21875f)
    cubicTo(-27.324562f, 7.227379f, -27.335121f, 7.237937f, -27.34375f, 7.25f)
    cubicTo(-27.355812f, 7.258629f, -27.366371f, 7.269187f, -27.375f, 7.28125f)
    cubicTo(-27.37621f, 7.2916317f, -27.37621f, 7.302119f, -27.375f, 7.3125f)
    cubicTo(-27.37621f, 7.3228817f, -27.37621f, 7.333369f, -27.375f, 7.34375f)
    lineTo(-27.375f, 12.5f)
    cubicTo(-27.37621f, 12.510382f, -27.37621f, 12.520868f, -27.375f, 12.53125f)
    cubicTo(-27.37621f, 12.541632f, -27.37621f, 12.552118f, -27.375f, 12.5625f)
    cubicTo(-27.366371f, 12.574563f, -27.355812f, 12.585121f, -27.34375f, 12.59375f)
    cubicTo(-27.335121f, 12.605813f, -27.324562f, 12.616371f, -27.3125f, 12.625f)
    cubicTo(-27.302118f, 12.62621f, -27.291632f, 12.62621f, -27.28125f, 12.625f)
    cubicTo(-27.270868f, 12.62621f, -27.260382f, 12.62621f, -27.25f, 12.625f)
    cubicTo(-27.239618f, 12.62621f, -27.229132f, 12.62621f, -27.21875f, 12.625f)
    cubicTo(-27.208368f, 12.62621f, -27.197882f, 12.62621f, -27.1875f, 12.625f)
    cubicTo(-26.045061f, 11.905957f, -24.954147f, 11.357862f, -23.90625f, 11.0f)
    cubicTo(-22.858109f, 10.631244f, -21.863134f, 10.437521f, -20.96875f, 10.4375f)
    cubicTo(-20.019531f, 10.437521f, -19.323826f, 10.648045f, -18.8125f, 11.0625f)
    cubicTo(-18.303778f, 11.46459f, -18.031261f, 12.04554f, -18.03125f, 12.78125f)
    cubicTo(-18.03126f, 13.261907f, -18.175438f, 13.73266f, -18.46875f, 14.21875f)
    cubicTo(-18.751741f, 14.705766f, -19.209015f, 15.249245f, -19.84375f, 15.8125f)
    lineTo(-20.9375f, 16.75f)
    cubicTo(-22.13896f, 17.83049f, -22.926743f, 18.741022f, -23.3125f, 19.46875f)
    cubicTo(-23.695614f, 20.180197f, -23.875006f, 20.988073f, -23.875f, 21.90625f)
    lineTo(-23.875f, 22.71875f)
    cubicTo(-23.87621f, 22.729132f, -23.87621f, 22.739618f, -23.875f, 22.75f)
    cubicTo(-23.87621f, 22.760382f, -23.87621f, 22.770868f, -23.875f, 22.78125f)
    cubicTo(-23.866371f, 22.793312f, -23.855812f, 22.803871f, -23.84375f, 22.8125f)
    cubicTo(-23.835121f, 22.824562f, -23.824562f, 22.835121f, -23.8125f, 22.84375f)
    cubicTo(-23.802118f, 22.84496f, -23.791632f, 22.84496f, -23.78125f, 22.84375f)
    cubicTo(-23.770868f, 22.84496f, -23.760382f, 22.84496f, -23.75f, 22.84375f)
    lineTo(-17.65625f, 22.84375f)
    cubicTo(-17.645868f, 22.84496f, -17.635382f, 22.84496f, -17.625f, 22.84375f)
    cubicTo(-17.614618f, 22.84496f, -17.604132f, 22.84496f, -17.59375f, 22.84375f)
    cubicTo(-17.581688f, 22.835121f, -17.571129f, 22.824562f, -17.5625f, 22.8125f)
    cubicTo(-17.550438f, 22.803871f, -17.539879f, 22.793312f, -17.53125f, 22.78125f)
    cubicTo(-17.53004f, 22.770868f, -17.53004f, 22.760382f, -17.53125f, 22.75f)
    cubicTo(-17.53004f, 22.739618f, -17.53004f, 22.729132f, -17.53125f, 22.71875f)
    lineTo(-17.53125f, 21.96875f)
    cubicTo(-17.531261f, 21.500553f, -17.38288f, 21.075901f, -17.15625f, 20.6875f)
    cubicTo(-16.933954f, 20.296215f, -16.448177f, 19.73714f, -15.6875f, 19.0625f)
    lineTo(-14.625f, 18.125f)
    cubicTo(-13.558412f, 17.14269f, -12.794341f, 16.240347f, -12.34375f, 15.375f)
    cubicTo(-11.894481f, 14.500954f, -11.656268f, 13.50158f, -11.65625f, 12.40625f)
    cubicTo(-11.656268f, 10.279985f, -12.400019f, 8.672222f, -13.875f, 7.5625f)
    cubicTo(-15.350197f, 6.441475f, -17.48124f, 5.875026f, -20.25f, 5.875f)
    close()
    moveTo(-23.8125f, 25.03125f)
    cubicTo(-23.824562f, 25.039879f, -23.835121f, 25.050438f, -23.84375f, 25.0625f)
    cubicTo(-23.855812f, 25.071129f, -23.866371f, 25.081688f, -23.875f, 25.09375f)
    cubicTo(-23.87621f, 25.104132f, -23.87621f, 25.114618f, -23.875f, 25.125f)
    cubicTo(-23.87621f, 25.135382f, -23.87621f, 25.145868f, -23.875f, 25.15625f)
    lineTo(-23.875f, 31.0f)
    cubicTo(-23.87621f, 31.010382f, -23.87621f, 31.020868f, -23.875f, 31.03125f)
    cubicTo(-23.87621f, 31.041632f, -23.87621f, 31.052118f, -23.875f, 31.0625f)
    cubicTo(-23.866371f, 31.074562f, -23.855812f, 31.085121f, -23.84375f, 31.09375f)
    cubicTo(-23.835121f, 31.105812f, -23.824562f, 31.116371f, -23.8125f, 31.125f)
    cubicTo(-23.802118f, 31.12621f, -23.791632f, 31.12621f, -23.78125f, 31.125f)
    cubicTo(-23.770868f, 31.12621f, -23.760382f, 31.12621f, -23.75f, 31.125f)
    lineTo(-17.65625f, 31.125f)
    cubicTo(-17.645868f, 31.12621f, -17.635382f, 31.12621f, -17.625f, 31.125f)
    cubicTo(-17.614618f, 31.12621f, -17.604132f, 31.12621f, -17.59375f, 31.125f)
    cubicTo(-17.581688f, 31.116371f, -17.571129f, 31.105812f, -17.5625f, 31.09375f)
    cubicTo(-17.550438f, 31.085121f, -17.539879f, 31.074562f, -17.53125f, 31.0625f)
    cubicTo(-17.53004f, 31.052118f, -17.53004f, 31.041632f, -17.53125f, 31.03125f)
    cubicTo(-17.53004f, 31.020868f, -17.53004f, 31.010382f, -17.53125f, 31.0f)
    lineTo(-17.53125f, 25.15625f)
    cubicTo(-17.53004f, 25.145868f, -17.53004f, 25.135382f, -17.53125f, 25.125f)
    cubicTo(-17.53004f, 25.114618f, -17.53004f, 25.104132f, -17.53125f, 25.09375f)
    cubicTo(-17.539879f, 25.081688f, -17.550438f, 25.071129f, -17.5625f, 25.0625f)
    cubicTo(-17.571129f, 25.050438f, -17.581688f, 25.039879f, -17.59375f, 25.03125f)
    cubicTo(-17.604132f, 25.03004f, -17.614618f, 25.03004f, -17.625f, 25.03125f)
    cubicTo(-17.635382f, 25.03004f, -17.645868f, 25.03004f, -17.65625f, 25.03125f)
    lineTo(-23.75f, 25.03125f)
    cubicTo(-23.760382f, 25.03004f, -23.770868f, 25.03004f, -23.78125f, 25.03125f)
    cubicTo(-23.791632f, 25.03004f, -23.802118f, 25.03004f, -23.8125f, 25.03125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(184, 184, 184, 255), center = Offset(-19.51564f, 11.07151f), radius = 38.918613f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(255, 255, 255, 200))
stroke = Stroke(width=1.0994728f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-20.25f, 5.875f)
    cubicTo(-21.30902f, 5.875026f, -22.397636f, 5.9982357f, -23.53125f, 6.21875f)
    cubicTo(-24.664175f, 6.4391785f, -25.911411f, 6.756263f, -27.28125f, 7.21875f)
    cubicTo(-27.291632f, 7.21754f, -27.302118f, 7.21754f, -27.3125f, 7.21875f)
    cubicTo(-27.324562f, 7.227379f, -27.335121f, 7.237937f, -27.34375f, 7.25f)
    cubicTo(-27.355812f, 7.258629f, -27.366371f, 7.269187f, -27.375f, 7.28125f)
    cubicTo(-27.37621f, 7.2916317f, -27.37621f, 7.302119f, -27.375f, 7.3125f)
    cubicTo(-27.37621f, 7.3228817f, -27.37621f, 7.333369f, -27.375f, 7.34375f)
    lineTo(-27.375f, 12.5f)
    cubicTo(-27.37621f, 12.510382f, -27.37621f, 12.520868f, -27.375f, 12.53125f)
    cubicTo(-27.37621f, 12.541632f, -27.37621f, 12.552118f, -27.375f, 12.5625f)
    cubicTo(-27.366371f, 12.574563f, -27.355812f, 12.585121f, -27.34375f, 12.59375f)
    cubicTo(-27.335121f, 12.605813f, -27.324562f, 12.616371f, -27.3125f, 12.625f)
    cubicTo(-27.302118f, 12.62621f, -27.291632f, 12.62621f, -27.28125f, 12.625f)
    cubicTo(-27.270868f, 12.62621f, -27.260382f, 12.62621f, -27.25f, 12.625f)
    cubicTo(-27.239618f, 12.62621f, -27.229132f, 12.62621f, -27.21875f, 12.625f)
    cubicTo(-27.208368f, 12.62621f, -27.197882f, 12.62621f, -27.1875f, 12.625f)
    cubicTo(-26.045061f, 11.905957f, -24.954147f, 11.357862f, -23.90625f, 11.0f)
    cubicTo(-22.858109f, 10.631244f, -21.863134f, 10.437521f, -20.96875f, 10.4375f)
    cubicTo(-20.019531f, 10.437521f, -19.323826f, 10.648045f, -18.8125f, 11.0625f)
    cubicTo(-18.303778f, 11.46459f, -18.031261f, 12.04554f, -18.03125f, 12.78125f)
    cubicTo(-18.03126f, 13.261907f, -18.175438f, 13.73266f, -18.46875f, 14.21875f)
    cubicTo(-18.751741f, 14.705766f, -19.209015f, 15.249245f, -19.84375f, 15.8125f)
    lineTo(-20.9375f, 16.75f)
    cubicTo(-22.13896f, 17.83049f, -22.926743f, 18.741022f, -23.3125f, 19.46875f)
    cubicTo(-23.695614f, 20.180197f, -23.875006f, 20.988073f, -23.875f, 21.90625f)
    lineTo(-23.875f, 22.71875f)
    cubicTo(-23.87621f, 22.729132f, -23.87621f, 22.739618f, -23.875f, 22.75f)
    cubicTo(-23.87621f, 22.760382f, -23.87621f, 22.770868f, -23.875f, 22.78125f)
    cubicTo(-23.866371f, 22.793312f, -23.855812f, 22.803871f, -23.84375f, 22.8125f)
    cubicTo(-23.835121f, 22.824562f, -23.824562f, 22.835121f, -23.8125f, 22.84375f)
    cubicTo(-23.802118f, 22.84496f, -23.791632f, 22.84496f, -23.78125f, 22.84375f)
    cubicTo(-23.770868f, 22.84496f, -23.760382f, 22.84496f, -23.75f, 22.84375f)
    lineTo(-17.65625f, 22.84375f)
    cubicTo(-17.645868f, 22.84496f, -17.635382f, 22.84496f, -17.625f, 22.84375f)
    cubicTo(-17.614618f, 22.84496f, -17.604132f, 22.84496f, -17.59375f, 22.84375f)
    cubicTo(-17.581688f, 22.835121f, -17.571129f, 22.824562f, -17.5625f, 22.8125f)
    cubicTo(-17.550438f, 22.803871f, -17.539879f, 22.793312f, -17.53125f, 22.78125f)
    cubicTo(-17.53004f, 22.770868f, -17.53004f, 22.760382f, -17.53125f, 22.75f)
    cubicTo(-17.53004f, 22.739618f, -17.53004f, 22.729132f, -17.53125f, 22.71875f)
    lineTo(-17.53125f, 21.96875f)
    cubicTo(-17.531261f, 21.500553f, -17.38288f, 21.075901f, -17.15625f, 20.6875f)
    cubicTo(-16.933954f, 20.296215f, -16.448177f, 19.73714f, -15.6875f, 19.0625f)
    lineTo(-14.625f, 18.125f)
    cubicTo(-13.558412f, 17.14269f, -12.794341f, 16.240347f, -12.34375f, 15.375f)
    cubicTo(-11.894481f, 14.500954f, -11.656268f, 13.50158f, -11.65625f, 12.40625f)
    cubicTo(-11.656268f, 10.279985f, -12.400019f, 8.672222f, -13.875f, 7.5625f)
    cubicTo(-15.350197f, 6.441475f, -17.48124f, 5.875026f, -20.25f, 5.875f)
    close()
    moveTo(-23.8125f, 25.03125f)
    cubicTo(-23.824562f, 25.039879f, -23.835121f, 25.050438f, -23.84375f, 25.0625f)
    cubicTo(-23.855812f, 25.071129f, -23.866371f, 25.081688f, -23.875f, 25.09375f)
    cubicTo(-23.87621f, 25.104132f, -23.87621f, 25.114618f, -23.875f, 25.125f)
    cubicTo(-23.87621f, 25.135382f, -23.87621f, 25.145868f, -23.875f, 25.15625f)
    lineTo(-23.875f, 31.0f)
    cubicTo(-23.87621f, 31.010382f, -23.87621f, 31.020868f, -23.875f, 31.03125f)
    cubicTo(-23.87621f, 31.041632f, -23.87621f, 31.052118f, -23.875f, 31.0625f)
    cubicTo(-23.866371f, 31.074562f, -23.855812f, 31.085121f, -23.84375f, 31.09375f)
    cubicTo(-23.835121f, 31.105812f, -23.824562f, 31.116371f, -23.8125f, 31.125f)
    cubicTo(-23.802118f, 31.12621f, -23.791632f, 31.12621f, -23.78125f, 31.125f)
    cubicTo(-23.770868f, 31.12621f, -23.760382f, 31.12621f, -23.75f, 31.125f)
    lineTo(-17.65625f, 31.125f)
    cubicTo(-17.645868f, 31.12621f, -17.635382f, 31.12621f, -17.625f, 31.125f)
    cubicTo(-17.614618f, 31.12621f, -17.604132f, 31.12621f, -17.59375f, 31.125f)
    cubicTo(-17.581688f, 31.116371f, -17.571129f, 31.105812f, -17.5625f, 31.09375f)
    cubicTo(-17.550438f, 31.085121f, -17.539879f, 31.074562f, -17.53125f, 31.0625f)
    cubicTo(-17.53004f, 31.052118f, -17.53004f, 31.041632f, -17.53125f, 31.03125f)
    cubicTo(-17.53004f, 31.020868f, -17.53004f, 31.010382f, -17.53125f, 31.0f)
    lineTo(-17.53125f, 25.15625f)
    cubicTo(-17.53004f, 25.145868f, -17.53004f, 25.135382f, -17.53125f, 25.125f)
    cubicTo(-17.53004f, 25.114618f, -17.53004f, 25.104132f, -17.53125f, 25.09375f)
    cubicTo(-17.539879f, 25.081688f, -17.550438f, 25.071129f, -17.5625f, 25.0625f)
    cubicTo(-17.571129f, 25.050438f, -17.581688f, 25.039879f, -17.59375f, 25.03125f)
    cubicTo(-17.604132f, 25.03004f, -17.614618f, 25.03004f, -17.625f, 25.03125f)
    cubicTo(-17.635382f, 25.03004f, -17.645868f, 25.03004f, -17.65625f, 25.03125f)
    lineTo(-23.75f, 25.03125f)
    cubicTo(-23.760382f, 25.03004f, -23.770868f, 25.03004f, -23.78125f, 25.03125f)
    cubicTo(-23.791632f, 25.03004f, -23.802118f, 25.03004f, -23.8125f, 25.03125f)
    close()
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
            return 3.002436876296997
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 2.9026434421539307
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 41.99778747558594
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 45.097354888916016
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

