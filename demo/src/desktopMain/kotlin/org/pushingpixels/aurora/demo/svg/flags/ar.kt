package org.pushingpixels.aurora.demo.svg.flags

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
class ar : Painter() {
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
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(0.0f, 0.0f)
    lineTo(512.0f, 0.0f)
    lineTo(512.0f, 512.0f)
    lineTo(0.0f, 512.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(116, 172, 223, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(0.0f, 170.67f)
    lineTo(512.0f, 170.67f)
    lineTo(512.0f, 341.34f)
    lineTo(0.0f, 341.34f)
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0240000486373901f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0240000486373901f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-153.60000610351562f, 0.0f, 0.0f, 1.0f)
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
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_2_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_2_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_2_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
// _0_2_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_2_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_2_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_2_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_7_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.0f, 1.0f, 0.0f, 0.0f,
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
512.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0240000486373901f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0240000486373901f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-153.60000610351562f, 0.0f, 0.0f, 1.0f)
))}){
// _0_3_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_3_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_3_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_3_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
// _0_3_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_3_0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_3_0_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_3_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0_7_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, -1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
512.0f, 512.0f, 0.0f, 1.0f)
))}){
// _0_4
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0240000486373901f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0240000486373901f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-153.60000610351562f, 0.0f, 0.0f, 1.0f)
))}){
// _0_4_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_4_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_4_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_4_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
// _0_4_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_4_0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_4_0_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_4_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4_0_7_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.0f, -1.0f, 0.0f, 0.0f,
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 512.0f, 0.0f, 1.0f)
))}){
// _0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0240000486373901f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0240000486373901f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-153.60000610351562f, 0.0f, 0.0f, 1.0f)
))}){
// _0_5_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_5_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_5_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_5_0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.112f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(396.84f, 251.31f)
    lineTo(425.294f, 313.302f)
    cubicTo(425.294f, 313.302f, 425.784f, 314.487f, 426.574f, 314.161f)
    cubicTo(427.364f, 313.834f, 426.87302f, 312.64902f, 426.87302f, 312.64902f)
    lineTo(403.15802f, 248.69302f)
    moveTo(402.47803f, 272.81302f)
    cubicTo(402.13104f, 282.24103f, 407.93002f, 287.42603f, 407.17203f, 295.84503f)
    cubicTo(406.41504f, 304.26505f, 411.03903f, 309.02502f, 412.11203f, 312.29904f)
    cubicTo(413.18503f, 315.57303f, 410.95203f, 317.53104f, 411.91403f, 317.99704f)
    cubicTo(412.87704f, 318.46304f, 414.98404f, 315.87704f, 414.29703f, 311.22205f)
    cubicTo(413.61002f, 306.56705f, 410.07703f, 305.18506f, 410.907f, 294.90204f)
    cubicTo(411.737f, 284.61905f, 406.70102f, 282.22403f, 407.927f, 272.84402f)
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
// _0_5_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.9238795325112867f, 0.3826834323650898f, 0.0f, 0.0f,
-0.3826834323650898f, 0.9238795325112867f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
126.11904508675775f, -134.04325607385758f, 0.0f, 1.0f)
))}){
// _0_5_0_5
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_5_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.7071067811865476f, 0.7071067811865475f, 0.0f, 0.0f,
-0.7071067811865475f, 0.7071067811865476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
293.9339828220178f, -209.61940777125585f, 0.0f, 1.0f)
))}){
// _0_5_0_6
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_6_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
0.38268343236508984f, 0.9238795325112867f, 0.0f, 0.0f,
-0.9238795325112867f, 0.38268343236508984f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
477.89651018178574f, -215.22267109578718f, 0.0f, 1.0f)
))}){
// _0_5_0_7
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5_0_7_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(404.31f, 274.41f)
    cubicTo(404.763f, 283.464f, 409.897f, 287.473f, 408.889f, 295.724f)
    cubicTo(411.10202f, 289.199f, 405.765f, 284.141f, 406.069f, 274.504f)
    moveTo(398.42f, 250.747f)
    lineTo(417.907f, 293.324f)
    lineTo(401.578f, 249.43701f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
// _0_6
shape = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 227.55499267578125f, top = 227.55499267578125f, right = 284.4449920654297f, bottom = 284.4449920654297f))})
brush = SolidColor(Color(246, 180, 14, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(133, 52, 10, 255))
stroke = Stroke(width=1.536f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 227.55499267578125f, top = 227.55499267578125f, right = 284.4449920654297f, bottom = 284.4449920654297f))})
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(265.697f, 249.917f)
    cubicTo(263.755f, 249.917f, 261.895f, 250.759f, 260.801f, 252.509f)
    cubicTo(262.98898f, 254.479f, 267.822f, 254.692f, 271.10498f, 252.285f)
    cubicTo(269.68298f, 250.755f, 267.63898f, 249.917f, 265.697f, 249.917f)
    close()
    moveTo(265.66498f, 250.365f)
    cubicTo(267.555f, 250.33101f, 269.32297f, 251.199f, 269.56897f, 252.061f)
    cubicTo(267.38098f, 254.468f, 263.88498f, 254.259f, 261.69696f, 252.509f)
    cubicTo(262.65497f, 250.978f, 264.19495f, 250.392f, 265.66495f, 250.365f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(132, 53, 17, 255))
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
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
512.2559814453125f, 0.0f, 0.0f, 1.0f)
))}){
// _0_8
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_8_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(246.19f, 248.566f)
    cubicTo(249.033f, 248.566f, 249.69f, 249.22299f, 251.004f, 250.316f)
    cubicTo(252.318f, 251.411f, 252.974f, 251.191f, 253.192f, 251.411f)
    cubicTo(253.409f, 251.631f, 253.192f, 252.28499f, 252.755f, 252.066f)
    cubicTo(252.31801f, 251.846f, 251.44101f, 251.411f, 250.13f, 250.316f)
    cubicTo(248.81601f, 249.221f, 247.505f, 249.222f, 246.19101f, 249.222f)
    cubicTo(242.253f, 249.222f, 240.065f, 252.504f, 239.628f, 252.285f)
    cubicTo(239.19101f, 252.065f, 241.81601f, 248.565f, 246.19101f, 248.565f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
512.2559814453125f, 0.0f, 0.0f, 1.0f)
))}){
// _0_9
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_9_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(238.316f, 249.876f)
    cubicTo(243.34799f, 245.501f, 249.692f, 244.84401f, 253.194f, 248.126f)
    cubicTo(254.051f, 249.274f, 254.6f, 250.50201f, 254.826f, 251.78201f)
    cubicTo(255.266f, 254.27402f, 254.488f, 256.96503f, 252.53601f, 259.724f)
    cubicTo(252.75601f, 259.724f, 253.19402f, 259.942f, 253.41301f, 260.161f)
    cubicTo(255.15001f, 256.83902f, 255.76501f, 253.42702f, 255.195f, 250.18102f)
    cubicTo(255.04292f, 249.33081f, 254.8133f, 248.49634f, 254.509f, 247.68802f)
    cubicTo(249.695f, 243.75002f, 243.132f, 243.31201f, 238.319f, 249.87701f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
19.315000534057617f, 0.0f, 0.0f, 1.0f)
))}){
// _0_10
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_10_0
shape = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 244.34400939941406f, top = 250.08900451660156f, right = 248.28200936317444f, bottom = 254.02700448036194f))})
brush = SolidColor(Color(133, 52, 10, 255))
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
-1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
512.2559814453125f, 0.0f, 0.0f, 1.0f)
))}){
// _0_11
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_11_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(240.937f, 253.379f)
    cubicTo(244.657f, 256.224f, 248.377f, 256.005f, 250.564f, 254.691f)
    cubicTo(252.752f, 253.379f, 252.752f, 252.941f, 252.315f, 252.941f)
    cubicTo(251.879f, 252.941f, 251.442f, 253.379f, 249.69101f, 254.25299f)
    cubicTo(247.93901f, 255.12999f, 245.315f, 255.12999f, 240.93901f, 253.379f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
// _0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(251.648f, 259.932f)
    cubicTo(250.713f, 260.10202f, 250.04799f, 260.932f, 250.04799f, 261.884f)
    cubicTo(250.04799f, 262.971f, 250.94699f, 263.836f, 252.03198f, 263.836f)
    cubicTo(252.67398f, 263.836f, 253.26299f, 263.532f, 253.63199f, 263.004f)
    cubicTo(254.38899f, 263.574f, 255.439f, 263.634f, 255.99998f, 263.644f)
    cubicTo(256.086f, 263.64603f, 256.198f, 263.644f, 256.25598f, 263.644f)
    cubicTo(256.817f, 263.634f, 257.86597f, 263.574f, 258.624f, 263.004f)
    cubicTo(258.99298f, 263.532f, 259.582f, 263.836f, 260.224f, 263.836f)
    cubicTo(261.309f, 263.836f, 262.208f, 262.971f, 262.208f, 261.884f)
    cubicTo(262.208f, 260.933f, 261.543f, 260.103f, 260.608f, 259.932f)
    cubicTo(261.133f, 260.117f, 261.47202f, 260.625f, 261.47202f, 261.18f)
    cubicTo(261.47256f, 261.52814f, 261.3345f, 261.86215f, 261.08832f, 262.1083f)
    cubicTo(260.84216f, 262.3545f, 260.50815f, 262.49255f, 260.16f, 262.492f)
    cubicTo(259.46188f, 262.48734f, 258.88757f, 261.941f, 258.848f, 261.24402f)
    cubicTo(258.634f, 261.67f, 257.789f, 262.93903f, 256.128f, 263.00403f)
    cubicTo(254.467f, 262.93903f, 253.622f, 261.67102f, 253.40799f, 261.24402f)
    cubicTo(253.36842f, 261.941f, 252.7941f, 262.48734f, 252.096f, 262.492f)
    cubicTo(251.74786f, 262.49252f, 251.41385f, 262.35446f, 251.16768f, 262.1083f)
    cubicTo(250.92152f, 261.86215f, 250.78346f, 261.5281f, 250.784f, 261.18f)
    cubicTo(250.784f, 260.625f, 251.123f, 260.117f, 251.648f, 259.932f)
    close()
    moveTo(253.788f, 265.759f)
    cubicTo(251.59799f, 265.759f, 250.734f, 267.742f, 248.76399f, 269.055f)
    cubicTo(249.859f, 268.61798f, 250.71999f, 267.755f, 252.252f, 266.879f)
    cubicTo(253.784f, 266.004f, 255.09f, 267.07098f, 255.964f, 267.07098f)
    lineTo(255.996f, 267.07098f)
    cubicTo(256.87f, 267.07098f, 258.176f, 266.004f, 259.708f, 266.879f)
    cubicTo(261.241f, 267.755f, 262.134f, 268.619f, 263.228f, 269.055f)
    cubicTo(261.258f, 267.742f, 260.361f, 265.759f, 258.172f, 265.759f)
    cubicTo(257.735f, 265.759f, 256.87f, 265.995f, 255.996f, 266.431f)
    lineTo(255.964f, 266.431f)
    cubicTo(255.09001f, 265.994f, 254.225f, 265.759f, 253.78801f, 265.759f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(253.05f, 268.35f)
    cubicTo(252.187f, 268.388f, 251.044f, 268.561f, 249.403f, 269.053f)
    cubicTo(253.343f, 268.17902f, 254.212f, 269.501f, 255.963f, 269.501f)
    lineTo(255.995f, 269.501f)
    cubicTo(257.745f, 269.501f, 258.615f, 268.178f, 262.555f, 269.053f)
    cubicTo(258.17798f, 267.741f, 257.307f, 268.605f, 255.995f, 268.605f)
    lineTo(255.963f, 268.605f)
    cubicTo(255.14299f, 268.605f, 254.491f, 268.285f, 253.051f, 268.349f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(249.6f, 269.025f)
    cubicTo(249.34601f, 269.02798f, 249.069f, 269.03f, 248.768f, 269.057f)
    cubicTo(253.364f, 269.495f, 251.155f, 272.129f, 255.968f, 272.129f)
    lineTo(256.0f, 272.129f)
    cubicTo(260.813f, 272.129f, 258.636f, 269.495f, 263.232f, 269.057f)
    cubicTo(258.418f, 268.62f, 259.94f, 271.457f, 256.0f, 271.457f)
    lineTo(255.968f, 271.457f)
    cubicTo(252.274f, 271.457f, 253.412f, 268.977f, 249.6f, 269.025f)
    close()
    moveTo(259.942f, 276.132f)
    cubicTo(259.94144f, 273.95715f, 258.17883f, 272.1941f, 256.004f, 272.193f)
    cubicTo(253.82878f, 272.19354f, 252.06555f, 273.95676f, 252.065f, 276.132f)
    cubicTo(252.49928f, 274.3079f, 254.1289f, 273.0204f, 256.004f, 273.02f)
    cubicTo(257.8791f, 273.01978f, 259.50876f, 274.30762f, 259.942f, 276.132f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(238.316f, 249.876f)
    cubicTo(243.34799f, 245.501f, 249.692f, 244.84401f, 253.194f, 248.126f)
    cubicTo(254.051f, 249.274f, 254.6f, 250.50201f, 254.826f, 251.78201f)
    cubicTo(255.266f, 254.27402f, 254.488f, 256.96503f, 252.53601f, 259.724f)
    cubicTo(252.75601f, 259.724f, 253.19402f, 259.942f, 253.41301f, 260.161f)
    cubicTo(255.15001f, 256.83902f, 255.76501f, 253.42702f, 255.195f, 250.18102f)
    cubicTo(255.04292f, 249.33081f, 254.8133f, 248.49634f, 254.509f, 247.68802f)
    cubicTo(249.695f, 243.75002f, 243.132f, 243.31201f, 238.319f, 249.87701f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(246.19f, 248.566f)
    cubicTo(249.033f, 248.566f, 249.69f, 249.22299f, 251.004f, 250.316f)
    cubicTo(252.318f, 251.411f, 252.974f, 251.191f, 253.192f, 251.411f)
    cubicTo(253.409f, 251.631f, 253.192f, 252.28499f, 252.755f, 252.066f)
    cubicTo(252.31801f, 251.846f, 251.44101f, 251.411f, 250.13f, 250.316f)
    cubicTo(248.81601f, 249.221f, 247.505f, 249.222f, 246.19101f, 249.222f)
    cubicTo(242.253f, 249.222f, 240.065f, 252.504f, 239.628f, 252.285f)
    cubicTo(239.19101f, 252.065f, 241.81601f, 248.565f, 246.19101f, 248.565f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
-19.641000747680664f, 0.0f, 0.0f, 1.0f)
))}){
// _0_17
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_17_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(265.697f, 249.917f)
    cubicTo(263.755f, 249.917f, 261.895f, 250.759f, 260.801f, 252.509f)
    cubicTo(262.98898f, 254.479f, 267.822f, 254.692f, 271.10498f, 252.285f)
    cubicTo(269.68298f, 250.755f, 267.63898f, 249.917f, 265.697f, 249.917f)
    close()
    moveTo(265.66498f, 250.365f)
    cubicTo(267.555f, 250.33101f, 269.32297f, 251.199f, 269.56897f, 252.061f)
    cubicTo(267.38098f, 254.468f, 263.88498f, 254.259f, 261.69696f, 252.509f)
    cubicTo(262.65497f, 250.978f, 264.19495f, 250.392f, 265.66495f, 250.365f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(132, 53, 17, 255))
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
// _0_18
shape = Outline.Generic(path = Path().also { it.addOval(oval=Rect(left = 244.34400939941406f, top = 250.08900451660156f, right = 248.28200936317444f, bottom = 254.02700448036194f))})
brush = SolidColor(Color(133, 52, 10, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(240.937f, 253.379f)
    cubicTo(244.657f, 256.224f, 248.377f, 256.005f, 250.564f, 254.691f)
    cubicTo(252.752f, 253.379f, 252.752f, 252.941f, 252.315f, 252.941f)
    cubicTo(251.879f, 252.941f, 251.442f, 253.379f, 249.69101f, 254.25299f)
    cubicTo(247.93901f, 255.12999f, 245.315f, 255.12999f, 240.93901f, 253.379f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(133, 52, 10, 255))
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
            return 0.0
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
            return 512.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 512.0
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

