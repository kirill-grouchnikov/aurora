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
class document_open : Painter() {
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
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.6200285f, 38.651016f)
    cubicTo(4.6618366f, 39.07147f, 5.117414f, 39.491924f, 5.5311837f, 39.491924f)
    lineTo(36.667347f, 39.491924f)
    cubicTo(37.081116f, 39.491924f, 37.45308f, 39.07147f, 37.41127f, 38.651016f)
    lineTo(34.714653f, 11.531728f)
    cubicTo(34.672844f, 11.111274f, 34.217266f, 10.69082f, 33.803497f, 10.69082f)
    lineTo(21.080082f, 10.69082f)
    cubicTo(20.489536f, 10.69082f, 19.870998f, 10.311268f, 19.677221f, 9.730485f)
    lineTo(18.574219f, 6.4246087f)
    cubicTo(18.404966f, 5.9173307f, 18.02707f, 5.6888137f, 17.259747f, 5.6888137f)
    lineTo(2.3224187f, 5.6888137f)
    cubicTo(1.9086492f, 5.6888137f, 1.5366876f, 6.109268f, 1.5784956f, 6.529722f)
    lineTo(4.6200285f, 38.651016f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(160, 160, 160, 255), 1.0f to Color(168, 168, 168, 255), center = Offset(30.453064f, 38.130978f), radius = 32.764153f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(90, 90, 90, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.6200285f, 38.651016f)
    cubicTo(4.6618366f, 39.07147f, 5.117414f, 39.491924f, 5.5311837f, 39.491924f)
    lineTo(36.667347f, 39.491924f)
    cubicTo(37.081116f, 39.491924f, 37.45308f, 39.07147f, 37.41127f, 38.651016f)
    lineTo(34.714653f, 11.531728f)
    cubicTo(34.672844f, 11.111274f, 34.217266f, 10.69082f, 33.803497f, 10.69082f)
    lineTo(21.080082f, 10.69082f)
    cubicTo(20.489536f, 10.69082f, 19.870998f, 10.311268f, 19.677221f, 9.730485f)
    lineTo(18.574219f, 6.4246087f)
    cubicTo(18.404966f, 5.9173307f, 18.02707f, 5.6888137f, 17.259747f, 5.6888137f)
    lineTo(2.3224187f, 5.6888137f)
    cubicTo(1.9086492f, 5.6888137f, 1.5366876f, 6.109268f, 1.5784956f, 6.529722f)
    lineTo(4.6200285f, 38.651016f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.3386018f, 17.533487f)
    lineTo(34.48846f, 17.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.3386018f, 17.533487f)
    lineTo(34.48846f, 17.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.3301525f, 37.533485f)
    lineTo(35.317905f, 37.533485f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.3301525f, 37.533485f)
    lineTo(35.317905f, 37.533485f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.3301525f, 35.533485f)
    lineTo(35.317905f, 35.533485f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.3301525f, 35.533485f)
    lineTo(35.317905f, 35.533485f)
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
0.02165151946246624f, 0.0f, 0.0f, 0.0f,
0.0f, 0.019038410857319832f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
42.41537857055664f, 36.933719635009766f, 0.0f, 1.0f)
))}){
// _0_1_4
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_4_0
shape = Outline.Rectangle(rect = Rect(left = -1559.2523193359375f, top = -150.6968536376953f, right = -219.6187744140625f, bottom = 327.6603240966797f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(-1051.9354f, -150.69684f), end = Offset(-1051.9354f, 327.6604f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_4_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-219.61876f, -150.68037f)
    cubicTo(-219.61876f, -150.68037f, -219.61876f, 327.65042f, -219.61876f, 327.65042f)
    cubicTo(-76.74459f, 328.55087f, 125.78146f, 220.48074f, 125.78138f, 88.45424f)
    cubicTo(125.78138f, -43.572304f, -33.655437f, -150.68036f, -219.61876f, -150.68037f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-211.146f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_4_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1559.2523f, -150.68037f)
    cubicTo(-1559.2523f, -150.68037f, -1559.2523f, 327.65042f, -1559.2523f, 327.65042f)
    cubicTo(-1702.1265f, 328.55087f, -1904.6525f, 220.48074f, -1904.6525f, 88.45424f)
    cubicTo(-1904.6525f, -43.572304f, -1745.2157f, -150.68036f, -1559.2523f, -150.68037f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-1567.7247f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
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
// _0_1_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.171752f, 38.418674f)
    cubicTo(6.203108f, 38.729f, 6.017127f, 38.935886f, 5.6963477f, 38.832443f)
    lineTo(5.6963477f, 38.832443f)
    cubicTo(5.3755684f, 38.729f, 5.14778f, 38.522118f, 5.1164236f, 38.21179f)
    lineTo(2.0868573f, 6.8445945f)
    cubicTo(2.0555012f, 6.534267f, 2.243451f, 6.346871f, 2.5537784f, 6.346871f)
    lineTo(17.303532f, 6.255425f)
    cubicTo(17.834814f, 6.2521315f, 18.04296f, 6.308731f, 18.18333f, 6.7726374f)
    cubicTo(18.18333f, 6.7726374f, 19.268703f, 9.885435f, 19.429564f, 10.470742f)
    lineTo(17.873968f, 7.553706f)
    cubicTo(17.608788f, 7.0564437f, 17.275225f, 7.1399364f, 16.901178f, 7.1399364f)
    lineTo(3.7717774f, 7.1399364f)
    cubicTo(3.4614503f, 7.1399364f, 3.2754695f, 7.3468213f, 3.3068254f, 7.657149f)
    lineTo(6.285646f, 38.522118f)
    lineTo(6.171752f, 38.418674f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 179), 1.0f to Color(255, 255, 255, 0), start = Offset(8.995817f, 12.802291f), end = Offset(15.647332f, 35.97866f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.3052332f, 7.533487f)
    lineTo(17.088966f, 7.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.3052332f, 7.533487f)
    lineTo(17.088966f, 7.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.7573333f, 11.533487f)
    lineTo(33.496216f, 11.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.7573333f, 11.533487f)
    lineTo(33.496216f, 11.533487f)
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
1.0344239473342896f, 0.0f, 0.0f, 0.0f,
0.10452000051736832f, 1.0344239473342896f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.032480239868164f, 2.631913900375366f, 0.0f, 1.0f)
))}){
// _0_1_8
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_8_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.785744f, 9.0363865f)
    cubicTo(41.79537f, 8.561804f, 41.800934f, 8.311881f, 41.36235f, 8.312183f)
    lineTo(28.80653f, 8.32084f)
    cubicTo(28.50653f, 8.32084f, 28.481916f, 8.177634f, 28.80653f, 8.32084f)
    cubicTo(29.131144f, 8.4640465f, 30.053629f, 8.979112f, 30.989227f, 9.021835f)
    cubicTo(30.989227f, 9.021835f, 41.785706f, 9.038299f, 41.785744f, 9.0363865f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 148))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.1628954f, 15.533487f)
    lineTo(33.99345f, 15.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=0.99999994f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.1628954f, 15.533487f)
    lineTo(33.99345f, 15.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.1594715f, 33.533485f)
    lineTo(35.147224f, 33.533485f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.1594715f, 33.533485f)
    lineTo(35.147224f, 33.533485f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.8658085f, 31.533487f)
    lineTo(34.974533f, 31.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.8658085f, 31.533487f)
    lineTo(34.974533f, 31.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.6336365f, 29.533487f)
    lineTo(34.80285f, 29.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.6336365f, 29.533487f)
    lineTo(34.80285f, 29.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.4629555f, 27.533487f)
    lineTo(34.632168f, 27.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.4629555f, 27.533487f)
    lineTo(34.632168f, 27.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.255672f, 25.533487f)
    lineTo(34.460793f, 25.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.255672f, 25.533487f)
    lineTo(34.460793f, 25.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.02352f, 23.533487f)
    lineTo(34.2891f, 23.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(4.02352f, 23.533487f)
    lineTo(34.2891f, 23.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.852839f, 21.533487f)
    lineTo(34.11842f, 21.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.852839f, 21.533487f)
    lineTo(34.11842f, 21.533487f)
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
1.0344239473342896f, 0.0f, 0.0f, 0.0f,
0.10452000051736832f, 1.0344239473342896f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.032480239868164f, 2.631913900375366f, 0.0f, 1.0f)
))}){
// _0_1_17
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_17_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.785744f, 9.0363865f)
    cubicTo(41.79537f, 8.561804f, 41.800934f, 8.311881f, 41.36235f, 8.312183f)
    lineTo(28.80653f, 8.32084f)
    cubicTo(28.50653f, 8.32084f, 28.481916f, 8.177634f, 28.80653f, 8.32084f)
    cubicTo(29.131144f, 8.4640465f, 30.053629f, 8.979112f, 30.989227f, 9.021835f)
    cubicTo(30.989227f, 9.021835f, 41.785706f, 9.038299f, 41.785744f, 9.0363865f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 148))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.9642313f, 13.533487f)
    lineTo(33.990734f, 13.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.9642313f, 13.533487f)
    lineTo(33.990734f, 13.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.651419f, 19.533487f)
    lineTo(33.947216f, 19.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(3.651419f, 19.533487f)
    lineTo(33.947216f, 19.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.11363633f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_20
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.5242572f, 9.533487f)
    lineTo(17.805073f, 9.533487f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(114, 159, 207, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(2.5242572f, 9.533487f)
    lineTo(17.805073f, 9.533487f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.39204544f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_21
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(34.375f, 14.125f)
    lineTo(37.0f, 38.75f)
    lineTo(6.0f, 38.875f)
    cubicTo(6.0f, 38.875f, 4.125f, 14.125f, 4.125f, 14.125f)
    cubicTo(4.125f, 14.125f, 34.5f, 14.125f, 34.375f, 14.125f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(22.25f, 37.625f), end = Offset(19.75f, 14.875f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_22
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.375f, 2.4944034f)
    cubicTo(43.875f, 19.373135f, 34.29994f, 21.022879f, 37.36244f, 31.494661f)
    cubicTo(37.36244f, 31.494661f, 5.875f, 32.380596f, 5.875f, 32.380596f)
    cubicTo(4.0f, 19.527987f, 14.25f, 11.166045f, 11.25f, 2.649254f)
    lineTo(43.375f, 2.4944034f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(250, 250, 250, 255), 0.5f to Color(168, 168, 168, 255), 1.0f to Color(205, 205, 205, 255), start = Offset(25.875f, 5.2817163f), end = Offset(25.25f, 30.367538f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.radialGradient(0.0f to Color(163, 163, 163, 255), 1.0f to Color(76, 76, 76, 255), center = Offset(52.908974f, -13.075683f), radius = 36.553963f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(43.375f, 2.4944034f)
    cubicTo(43.875f, 19.373135f, 34.29994f, 21.022879f, 37.36244f, 31.494661f)
    cubicTo(37.36244f, 31.494661f, 5.875f, 32.380596f, 5.875f, 32.380596f)
    cubicTo(4.0f, 19.527987f, 14.25f, 11.166045f, 11.25f, 2.649254f)
    lineTo(43.375f, 2.4944034f)
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
// _0_1_23
brush = SolidColor(Color(161, 161, 161, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.4375f, 6.5625f)
    lineTo(39.0f, 6.5625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_24
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.7785654f, 39.065998f)
    cubicTo(5.8820076f, 39.277466f, 6.0888915f, 39.488926f, 6.399217f, 39.488926f)
    lineTo(39.70767f, 39.488926f)
    cubicTo(39.914562f, 39.488926f, 40.228832f, 39.36262f, 40.415844f, 39.224575f)
    cubicTo(40.946247f, 38.83304f, 41.070705f, 38.61219f, 41.308624f, 38.251106f)
    cubicTo(43.756752f, 34.53565f, 47.113766f, 18.974215f, 47.113766f, 18.974215f)
    cubicTo(47.21721f, 18.762754f, 47.010326f, 18.551294f, 46.7f, 18.551294f)
    lineTo(11.776358f, 18.551294f)
    cubicTo(11.466032f, 18.551294f, 10.120393f, 34.658623f, 6.913359f, 37.838318f)
    lineTo(5.6751237f, 39.065998f)
    lineTo(5.7785654f, 39.065998f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(97, 148, 203, 255), 1.0f to Color(114, 159, 207, 255), start = Offset(74.23291f, 36.483074f), end = Offset(74.12227f, 31.436365f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(5.7785654f, 39.065998f)
    cubicTo(5.8820076f, 39.277466f, 6.0888915f, 39.488926f, 6.399217f, 39.488926f)
    lineTo(39.70767f, 39.488926f)
    cubicTo(39.914562f, 39.488926f, 40.228832f, 39.36262f, 40.415844f, 39.224575f)
    cubicTo(40.946247f, 38.83304f, 41.070705f, 38.61219f, 41.308624f, 38.251106f)
    cubicTo(43.756752f, 34.53565f, 47.113766f, 18.974215f, 47.113766f, 18.974215f)
    cubicTo(47.21721f, 18.762754f, 47.010326f, 18.551294f, 46.7f, 18.551294f)
    lineTo(11.776358f, 18.551294f)
    cubicTo(11.466032f, 18.551294f, 10.120393f, 34.658623f, 6.913359f, 37.838318f)
    lineTo(5.6751237f, 39.065998f)
    lineTo(5.7785654f, 39.065998f)
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
// _0_1_25
brush = SolidColor(Color(161, 161, 161, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.356073f, 8.5625f)
    lineTo(35.08142f, 8.5625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_26
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.134476f, 20.138641f)
    cubicTo(12.361729f, 25.129398f, 11.633175f, 29.147884f, 10.418486f, 33.652504f)
    cubicTo(12.804971f, 32.945396f, 17.534601f, 30.448f, 27.534601f, 30.448f)
    cubicTo(37.534603f, 30.448f, 44.258175f, 21.1993f, 45.186253f, 20.094446f)
    lineTo(13.134476f, 20.138641f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 34), 1.0f to Color(255, 255, 255, 13), start = Offset(26.326418f, 20.073f), end = Offset(38.326416f, 29.698002f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_27
brush = SolidColor(Color(161, 161, 161, 255))
stroke = Stroke(width=1.0000001f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.143007f, 10.5625f)
    lineTo(39.457832f, 10.5625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.52272725f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_28
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(27.174267f, 20.362497f), end = Offset(27.61609f, 36.51871f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.820084f, 19.6875f)
    lineTo(12.661612f, 19.6875f)
    cubicTo(12.661612f, 19.6875f, 10.513864f, 35.707108f, 7.93934f, 37.928078f)
    cubicTo(16.060417f, 37.928078f, 39.51051f, 37.87944f, 39.53033f, 37.87944f)
    cubicTo(41.28199f, 37.87944f, 44.43797f, 25.243248f, 45.820084f, 19.6875f)
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
// _0_1_29
brush = SolidColor(Color(161, 161, 161, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.398767f, 12.5625f)
    lineTo(38.25216f, 12.5625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_30
brush = SolidColor(Color(161, 161, 161, 255))
stroke = Stroke(width=1.0000005f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(13.629028f, 14.5625f)
    lineTo(36.97533f, 14.5625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_31
brush = SolidColor(Color(161, 161, 161, 255))
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.520679f, 16.5625f)
    lineTo(31.16684f, 16.5625f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1_32
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.375f, 31.75f)
    cubicTo(5.1336346f, 19.511961f, 13.5625f, 12.6875f, 12.0f, 3.0f)
    lineTo(42.875f, 3.0f)
    lineTo(12.875f, 3.625f)
    cubicTo(14.125f, 13.1875f, 6.6786165f, 18.271446f, 6.375f, 31.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
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
            return 1.0752776861190796
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.9920581579208374
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 46.568946838378906
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 41.17979049682617
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

