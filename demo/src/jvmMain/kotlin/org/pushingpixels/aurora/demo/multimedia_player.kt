package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.toComposeBitmap
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class multimedia_player private constructor(var _width: Int, var _height: Int) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.09440699964761734f,
0.0f, 1.0f, 0.0f, -0.03895187005400658f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.77996f, 13.351768f)
generalPath!!.cubicTo(42.789875f, 15.568827f, 41.612778f, 17.621729f, 39.694386f, 18.733131f)
generalPath!!.cubicTo(37.77599f, 19.844534f, 35.409565f, 19.844534f, 33.49117f, 18.733131f)
generalPath!!.cubicTo(31.572777f, 17.621729f, 30.39568f, 15.568827f, 30.405594f, 13.351768f)
generalPath!!.cubicTo(30.39568f, 11.134708f, 31.572777f, 9.081805f, 33.49117f, 7.9704037f)
generalPath!!.cubicTo(35.409565f, 6.8590016f, 37.77599f, 6.8590016f, 39.694386f, 7.9704037f)
generalPath!!.cubicTo(41.612778f, 9.081805f, 42.789875f, 11.134708f, 42.77996f, 13.351768f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 77, 74, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(46, 52, 54, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(42.77996f, 13.351768f)
generalPath!!.cubicTo(42.789875f, 15.568827f, 41.612778f, 17.621729f, 39.694386f, 18.733131f)
generalPath!!.cubicTo(37.77599f, 19.844534f, 35.409565f, 19.844534f, 33.49117f, 18.733131f)
generalPath!!.cubicTo(31.572777f, 17.621729f, 30.39568f, 15.568827f, 30.405594f, 13.351768f)
generalPath!!.cubicTo(30.39568f, 11.134708f, 31.572777f, 9.081805f, 33.49117f, 7.9704037f)
generalPath!!.cubicTo(35.409565f, 6.8590016f, 37.77599f, 6.8590016f, 39.694386f, 7.9704037f)
generalPath!!.cubicTo(41.612778f, 9.081805f, 42.789875f, 11.134708f, 42.77996f, 13.351768f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.02243424952030182f, 0.0f, 0.0f, 42.953819274902344f,
0.0f, 0.02086758054792881f, 0.0f, 39.40359878540039f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_0
shape = Outline.Rectangle(rect = Rect(left = -1559.2523193359375f, top = -150.6968536376953f, right = -219.6187744140625f, bottom = 327.6603240966797f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(-1051.9354f, -150.69684f), end = Offset(-1051.9354f, 327.6604f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-219.61876f, -150.68037f)
generalPath!!.cubicTo(-219.61876f, -150.68037f, -219.61876f, 327.65042f, -219.61876f, 327.65042f)
generalPath!!.cubicTo(-76.74459f, 328.55087f, 125.78146f, 220.48074f, 125.78138f, 88.45424f)
generalPath!!.cubicTo(125.78138f, -43.572304f, -33.655437f, -150.68036f, -219.61876f, -150.68037f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-211.146f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-1559.2523f, -150.68037f)
generalPath!!.cubicTo(-1559.2523f, -150.68037f, -1559.2523f, 327.65042f, -1559.2523f, 327.65042f)
generalPath!!.cubicTo(-1702.1265f, 328.55087f, -1904.6525f, 220.48074f, -1904.6525f, 88.45424f)
generalPath!!.cubicTo(-1904.6525f, -43.572304f, -1745.2157f, -150.68036f, -1559.2523f, -150.68037f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-1567.7247f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.375f, 3.5625f)
generalPath!!.cubicTo(4.3091497f, 3.7244606f, 3.5f, 4.637521f, 3.5f, 5.75f)
generalPath!!.lineTo(3.5f, 40.25f)
generalPath!!.cubicTo(3.5f, 41.477562f, 4.4911866f, 42.4375f, 5.71875f, 42.4375f)
generalPath!!.lineTo(40.28125f, 42.4375f)
generalPath!!.cubicTo(41.508812f, 42.4375f, 42.5f, 41.477562f, 42.5f, 40.25f)
generalPath!!.lineTo(42.5f, 19.5f)
generalPath!!.cubicTo(41.339455f, 19.426907f, 40.40625f, 18.460861f, 40.40625f, 17.28125f)
generalPath!!.lineTo(40.40625f, 9.71875f)
generalPath!!.cubicTo(40.40625f, 8.539138f, 41.33945f, 7.5730934f, 42.5f, 7.5f)
generalPath!!.lineTo(42.5f, 5.75f)
generalPath!!.cubicTo(42.5f, 4.5224366f, 41.508812f, 3.5625f, 40.28125f, 3.5625f)
generalPath!!.lineTo(5.71875f, 3.5625f)
generalPath!!.cubicTo(5.603666f, 3.5625f, 5.4852605f, 3.5457454f, 5.375f, 3.5625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(136, 138, 133, 255), 0.10810811f to Color(169, 170, 167, 255), 0.39737034f to Color(108, 110, 106, 255), 0.5439559f to Color(129, 131, 126, 255), 1.0f to Color(116, 118, 113, 255), start = Offset(23.0f, 3.047944f), end = Offset(23.0f, 42.952038f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(46, 52, 54, 255))
stroke = Stroke(width=0.9999998f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.375f, 3.5625f)
generalPath!!.cubicTo(4.3091497f, 3.7244606f, 3.5f, 4.637521f, 3.5f, 5.75f)
generalPath!!.lineTo(3.5f, 40.25f)
generalPath!!.cubicTo(3.5f, 41.477562f, 4.4911866f, 42.4375f, 5.71875f, 42.4375f)
generalPath!!.lineTo(40.28125f, 42.4375f)
generalPath!!.cubicTo(41.508812f, 42.4375f, 42.5f, 41.477562f, 42.5f, 40.25f)
generalPath!!.lineTo(42.5f, 19.5f)
generalPath!!.cubicTo(41.339455f, 19.426907f, 40.40625f, 18.460861f, 40.40625f, 17.28125f)
generalPath!!.lineTo(40.40625f, 9.71875f)
generalPath!!.cubicTo(40.40625f, 8.539138f, 41.33945f, 7.5730934f, 42.5f, 7.5f)
generalPath!!.lineTo(42.5f, 5.75f)
generalPath!!.cubicTo(42.5f, 4.5224366f, 41.508812f, 3.5625f, 40.28125f, 3.5625f)
generalPath!!.lineTo(5.71875f, 3.5625f)
generalPath!!.cubicTo(5.603666f, 3.5625f, 5.4852605f, 3.5457454f, 5.375f, 3.5625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3732270002365112f, 0.0f, 0.0f, -17.4478702545166f,
0.0f, 1.6043779850006104f, 0.0f, -31.516510009765625f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.12831f, 42.961864f)
generalPath!!.cubicTo(40.140778f, 45.210598f, 38.660995f, 47.292828f, 36.2493f, 48.420105f)
generalPath!!.cubicTo(33.837605f, 49.547386f, 30.862669f, 49.547386f, 28.450972f, 48.420105f)
generalPath!!.cubicTo(26.039276f, 47.292828f, 24.559496f, 45.210598f, 24.57196f, 42.961864f)
generalPath!!.cubicTo(24.559496f, 40.71313f, 26.039276f, 38.6309f, 28.450972f, 37.503624f)
generalPath!!.cubicTo(30.862669f, 36.376343f, 33.837605f, 36.376343f, 36.2493f, 37.503624f)
generalPath!!.cubicTo(38.660995f, 38.6309f, 40.140778f, 40.71313f, 40.12831f, 42.961864f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(32.350136f, 42.961857f), radius = 7.7781754f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1734260320663452f, 0.0f, 0.0f, -12.044960021972656f,
0.0f, 1.3709449768066406f, 0.0f, -21.3110294342041f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.12831f, 42.961864f)
generalPath!!.cubicTo(40.140778f, 45.210598f, 38.660995f, 47.292828f, 36.2493f, 48.420105f)
generalPath!!.cubicTo(33.837605f, 49.547386f, 30.862669f, 49.547386f, 28.450972f, 48.420105f)
generalPath!!.cubicTo(26.039276f, 47.292828f, 24.559496f, 45.210598f, 24.57196f, 42.961864f)
generalPath!!.cubicTo(24.559496f, 40.71313f, 26.039276f, 38.6309f, 28.450972f, 37.503624f)
generalPath!!.cubicTo(30.862669f, 36.376343f, 33.837605f, 36.376343f, 36.2493f, 37.503624f)
generalPath!!.cubicTo(38.660995f, 38.6309f, 40.140778f, 40.71313f, 40.12831f, 42.961864f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(176, 176, 176, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(71, 71, 71, 255))
stroke = Stroke(width=0.7884284f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.12831f, 42.961864f)
generalPath!!.cubicTo(40.140778f, 45.210598f, 38.660995f, 47.292828f, 36.2493f, 48.420105f)
generalPath!!.cubicTo(33.837605f, 49.547386f, 30.862669f, 49.547386f, 28.450972f, 48.420105f)
generalPath!!.cubicTo(26.039276f, 47.292828f, 24.559496f, 45.210598f, 24.57196f, 42.961864f)
generalPath!!.cubicTo(24.559496f, 40.71313f, 26.039276f, 38.6309f, 28.450972f, 37.503624f)
generalPath!!.cubicTo(30.862669f, 36.376343f, 33.837605f, 36.376343f, 36.2493f, 37.503624f)
generalPath!!.cubicTo(38.660995f, 38.6309f, 40.140778f, 40.71313f, 40.12831f, 42.961864f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -6.1846208572387695f,
0.0f, 1.1043610572814941f, 0.0f, -8.733105659484863f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.12831f, 42.961864f)
generalPath!!.cubicTo(40.140778f, 45.210598f, 38.660995f, 47.292828f, 36.2493f, 48.420105f)
generalPath!!.cubicTo(33.837605f, 49.547386f, 30.862669f, 49.547386f, 28.450972f, 48.420105f)
generalPath!!.cubicTo(26.039276f, 47.292828f, 24.559496f, 45.210598f, 24.57196f, 42.961864f)
generalPath!!.cubicTo(24.559496f, 40.71313f, 26.039276f, 38.6309f, 28.450972f, 37.503624f)
generalPath!!.cubicTo(30.862669f, 36.376343f, 33.837605f, 36.376343f, 36.2493f, 37.503624f)
generalPath!!.cubicTo(38.660995f, 38.6309f, 40.140778f, 40.71313f, 40.12831f, 42.961864f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(221, 221, 221, 255), center = Offset(32.350136f, 41.041008f), radius = 12.673382f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28140703f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(14.700136f, -2.2827868f), end = Offset(36.779945f, 50.396667f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(6.203608f, 4.4761443f)
generalPath!!.cubicTo(5.1878667f, 4.630491f, 4.4167576f, 5.5006247f, 4.4167576f, 6.5608034f)
generalPath!!.lineTo(4.4167576f, 39.438854f)
generalPath!!.cubicTo(4.4167576f, 40.608707f, 5.3613453f, 41.523514f, 6.5311975f, 41.523514f)
generalPath!!.lineTo(39.46881f, 41.523514f)
generalPath!!.cubicTo(40.63866f, 41.523514f, 41.58325f, 40.608707f, 41.58325f, 39.438854f)
generalPath!!.lineTo(41.58325f, 20.327288f)
generalPath!!.cubicTo(40.477264f, 20.257631f, 39.587933f, 19.160225f, 39.587933f, 18.036072f)
generalPath!!.lineTo(39.587933f, 9.149728f)
generalPath!!.cubicTo(39.587933f, 8.025573f, 40.477264f, 6.9723625f, 41.58325f, 6.902705f)
generalPath!!.lineTo(41.58325f, 6.030473f)
generalPath!!.cubicTo(41.58325f, 4.8606215f, 40.63866f, 4.4761443f, 39.46881f, 4.4761443f)
generalPath!!.lineTo(6.5311975f, 4.4761443f)
generalPath!!.cubicTo(6.4215236f, 4.4761443f, 6.308685f, 4.4601774f, 6.203608f, 4.4761443f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28140703f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
shape = Outline.Rounded(roundRect = RoundRect(left = 8.0f, top = 8.0f, right = 36.0f, bottom = 15.0f,radiusX = 2.6638693809509277f, radiusY = 2.6638693809509277f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(22.0f, 6.805061f), end = Offset(22.0f, 16.758337f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
shape = Outline.Rounded(roundRect = RoundRect(left = 9.502381324768066f, top = 9.0f, right = 34.497618675231934f, bottom = 14.0f,radiusX = 1.249655842781067f, radiusY = 1.249655842781067f))
brush = SolidColor(Color(133, 143, 74, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40201002f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(10.0f, 9.0f)
generalPath!!.cubicTo(9.949065f, 9.01037f, 9.889295f, 9.040618f, 9.84375f, 9.0625f)
generalPath!!.cubicTo(9.808331f, 9.081694f, 9.749586f, 9.130823f, 9.71875f, 9.15625f)
generalPath!!.cubicTo(9.70397f, 9.169679f, 9.669678f, 9.20397f, 9.65625f, 9.21875f)
generalPath!!.cubicTo(9.567256f, 9.326674f, 9.5f, 9.473557f, 9.5f, 9.625f)
generalPath!!.lineTo(9.5f, 12.21875f)
generalPath!!.cubicTo(12.146211f, 12.602725f, 15.034555f, 12.8125f, 18.0625f, 12.8125f)
generalPath!!.cubicTo(24.51413f, 12.8125f, 30.33753f, 11.855998f, 34.5f, 10.3125f)
generalPath!!.lineTo(34.5f, 9.625f)
generalPath!!.cubicTo(34.5f, 9.473557f, 34.432743f, 9.326674f, 34.34375f, 9.21875f)
generalPath!!.cubicTo(34.330322f, 9.20397f, 34.29603f, 9.169679f, 34.28125f, 9.15625f)
generalPath!!.cubicTo(34.250416f, 9.130823f, 34.19167f, 9.081694f, 34.15625f, 9.0625f)
generalPath!!.cubicTo(34.07427f, 9.023111f, 33.972355f, 9.0f, 33.875f, 9.0f)
generalPath!!.lineTo(10.125f, 9.0f)
generalPath!!.cubicTo(10.081731f, 9.0f, 10.040748f, 8.991704f, 10.0f, 9.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(16.873478f, 7.0153146f), radius = 20.371204f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2907079458236694f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7747690081596375f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.794487f, 27.7462f)
generalPath!!.lineTo(6.6029377f, 27.7462f)
generalPath!!.cubicTo(6.9045706f, 27.746202f, 7.1331344f, 27.811089f, 7.28863f, 27.94086f)
generalPath!!.cubicTo(7.444122f, 28.070635f, 7.521868f, 28.261202f, 7.5218706f, 28.512562f)
generalPath!!.cubicTo(7.521868f, 28.753405f, 7.4347687f, 28.95274f, 7.260571f, 29.11057f)
generalPath!!.cubicTo(7.0875387f, 29.267235f, 6.849037f, 29.345566f, 6.545066f, 29.345566f)
generalPath!!.lineTo(6.2802587f, 29.345566f)
generalPath!!.lineTo(6.2802587f, 30.26099f)
generalPath!!.lineTo(5.794487f, 30.26099f)
generalPath!!.lineTo(5.794487f, 27.7462f)
generalPath!!.moveTo(6.2802587f, 28.961508f)
generalPath!!.lineTo(6.538051f, 28.961508f)
generalPath!!.cubicTo(6.7028966f, 28.961508f, 6.8268237f, 28.925264f, 6.909833f, 28.852777f)
generalPath!!.cubicTo(6.9928393f, 28.780294f, 7.0343437f, 28.671564f, 7.034345f, 28.526592f)
generalPath!!.cubicTo(7.0343437f, 28.392143f, 6.9928393f, 28.2916f, 6.909833f, 28.224957f)
generalPath!!.cubicTo(6.8268237f, 28.15715f, 6.7028966f, 28.123245f, 6.538051f, 28.123243f)
generalPath!!.lineTo(6.2802587f, 28.123243f)
generalPath!!.lineTo(6.2802587f, 28.961508f)
generalPath!!.moveTo(8.968664f, 27.711126f)
generalPath!!.cubicTo(9.308878f, 27.711128f, 9.583037f, 27.826874f, 9.791143f, 28.058357f)
generalPath!!.cubicTo(9.999246f, 28.288677f, 10.103298f, 28.595572f, 10.1033f, 28.979044f)
generalPath!!.cubicTo(10.103298f, 29.376547f, 9.99457f, 29.695133f, 9.777114f, 29.934805f)
generalPath!!.cubicTo(9.559655f, 30.173306f, 9.269712f, 30.292557f, 8.907285f, 30.292557f)
generalPath!!.cubicTo(8.558885f, 30.292557f, 8.277125f, 30.176813f, 8.062007f, 29.945326f)
generalPath!!.cubicTo(7.8480563f, 29.71384f, 7.7410817f, 29.409866f, 7.7410817f, 29.033407f)
generalPath!!.cubicTo(7.7410817f, 28.63357f, 7.8533177f, 28.313229f, 8.07779f, 28.072388f)
generalPath!!.cubicTo(8.303431f, 27.831549f, 8.600389f, 27.711128f, 8.968664f, 27.711126f)
generalPath!!.moveTo(8.916053f, 29.887453f)
generalPath!!.cubicTo(9.125325f, 29.887455f, 9.294264f, 29.810293f, 9.422869f, 29.655968f)
generalPath!!.cubicTo(9.551471f, 29.501644f, 9.615773f, 29.288862f, 9.615775f, 29.017626f)
generalPath!!.cubicTo(9.615773f, 28.740543f, 9.552055f, 28.522503f, 9.424623f, 28.363499f)
generalPath!!.cubicTo(9.298356f, 28.203331f, 9.128833f, 28.123245f, 8.916053f, 28.123243f)
generalPath!!.cubicTo(8.707948f, 28.123245f, 8.540178f, 28.203915f, 8.4127445f, 28.365253f)
generalPath!!.cubicTo(8.285309f, 28.525425f, 8.221592f, 28.740543f, 8.221592f, 29.01061f)
generalPath!!.cubicTo(8.221592f, 29.278341f, 8.285309f, 29.491707f, 8.4127445f, 29.650705f)
generalPath!!.cubicTo(8.5413475f, 29.808538f, 8.709117f, 29.887455f, 8.916053f, 29.887453f)
generalPath!!.moveTo(12.781183f, 30.26099f)
generalPath!!.lineTo(12.274367f, 30.26099f)
generalPath!!.lineTo(11.074844f, 28.489763f)
generalPath!!.cubicTo(11.044446f, 28.447678f, 11.01931f, 28.396236f, 10.999436f, 28.33544f)
generalPath!!.lineTo(10.988913f, 28.33544f)
generalPath!!.cubicTo(11.002942f, 28.403252f, 11.009957f, 28.46346f, 11.009958f, 28.51607f)
generalPath!!.lineTo(11.009958f, 30.26099f)
generalPath!!.lineTo(10.561013f, 30.26099f)
generalPath!!.lineTo(10.561013f, 27.7462f)
generalPath!!.lineTo(11.095888f, 27.7462f)
generalPath!!.lineTo(12.249816f, 29.46657f)
generalPath!!.cubicTo(12.276704f, 29.508657f, 12.304178f, 29.558931f, 12.332239f, 29.617386f)
generalPath!!.lineTo(12.342761f, 29.617386f)
generalPath!!.cubicTo(12.331068f, 29.5601f, 12.325222f, 29.491121f, 12.325224f, 29.410452f)
generalPath!!.lineTo(12.325224f, 27.7462f)
generalPath!!.lineTo(12.781183f, 27.7462f)
generalPath!!.lineTo(12.781183f, 30.26099f)
generalPath!!.moveTo(15.169707f, 27.7462f)
generalPath!!.lineTo(14.340212f, 29.308737f)
generalPath!!.lineTo(14.340212f, 30.26099f)
generalPath!!.lineTo(13.859701f, 30.26099f)
generalPath!!.lineTo(13.859701f, 29.31926f)
generalPath!!.lineTo(13.051251f, 27.7462f)
generalPath!!.lineTo(13.586126f, 27.7462f)
generalPath!!.lineTo(13.922834f, 28.509054f)
generalPath!!.cubicTo(14.026885f, 28.742882f, 14.084173f, 28.874992f, 14.094696f, 28.90539f)
generalPath!!.cubicTo(14.105217f, 28.935787f, 14.112816f, 28.961508f, 14.117494f, 28.982552f)
generalPath!!.lineTo(14.121001f, 28.982552f)
generalPath!!.cubicTo(14.13503f, 28.935787f, 14.147305f, 28.901299f, 14.157829f, 28.879084f)
generalPath!!.lineTo(14.675167f, 27.7462f)
generalPath!!.lineTo(15.169707f, 27.7462f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(31, 31, 31, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2907079458236694f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7747690081596375f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(5.7944827f, 27.518019f)
generalPath!!.lineTo(6.602933f, 27.518019f)
generalPath!!.cubicTo(6.904566f, 27.51802f, 7.1331296f, 27.582907f, 7.288625f, 27.712677f)
generalPath!!.cubicTo(7.4441166f, 27.842451f, 7.5218635f, 28.03302f, 7.5218654f, 28.28438f)
generalPath!!.cubicTo(7.5218635f, 28.52522f, 7.434764f, 28.724558f, 7.260566f, 28.882387f)
generalPath!!.cubicTo(7.087534f, 29.039051f, 6.849033f, 29.117382f, 6.545061f, 29.11738f)
generalPath!!.lineTo(6.2802544f, 29.11738f)
generalPath!!.lineTo(6.2802544f, 30.032806f)
generalPath!!.lineTo(5.7944827f, 30.032806f)
generalPath!!.lineTo(5.7944827f, 27.518019f)
generalPath!!.moveTo(6.2802544f, 28.733324f)
generalPath!!.lineTo(6.538047f, 28.733324f)
generalPath!!.cubicTo(6.702892f, 28.733324f, 6.8268194f, 28.697083f, 6.9098287f, 28.624596f)
generalPath!!.cubicTo(6.992835f, 28.55211f, 7.034339f, 28.443382f, 7.0343404f, 28.298409f)
generalPath!!.cubicTo(7.034339f, 28.163961f, 6.992835f, 28.063417f, 6.9098287f, 27.996775f)
generalPath!!.cubicTo(6.8268194f, 27.928968f, 6.702892f, 27.895063f, 6.538047f, 27.895061f)
generalPath!!.lineTo(6.2802544f, 27.895061f)
generalPath!!.lineTo(6.2802544f, 28.733324f)
generalPath!!.moveTo(8.968658f, 27.482944f)
generalPath!!.cubicTo(9.308872f, 27.482946f, 9.583032f, 27.59869f, 9.791138f, 27.830173f)
generalPath!!.cubicTo(9.99924f, 28.060493f, 10.103292f, 28.36739f, 10.103294f, 28.75086f)
generalPath!!.cubicTo(10.103292f, 29.148363f, 9.994563f, 29.46695f, 9.777108f, 29.70662f)
generalPath!!.cubicTo(9.5596485f, 29.945122f, 9.269706f, 30.064373f, 8.907279f, 30.064373f)
generalPath!!.cubicTo(8.558879f, 30.064373f, 8.27712f, 29.94863f, 8.062001f, 29.717142f)
generalPath!!.cubicTo(7.848051f, 29.485657f, 7.7410765f, 29.181684f, 7.7410765f, 28.805225f)
generalPath!!.cubicTo(7.7410765f, 28.405386f, 7.8533125f, 28.085047f, 8.077785f, 27.844204f)
generalPath!!.cubicTo(8.303425f, 27.603367f, 8.600383f, 27.482946f, 8.968658f, 27.482944f)
generalPath!!.moveTo(8.916047f, 29.659271f)
generalPath!!.cubicTo(9.1253195f, 29.659271f, 9.294258f, 29.58211f, 9.422863f, 29.427784f)
generalPath!!.cubicTo(9.551465f, 29.27346f, 9.615767f, 29.06068f, 9.615768f, 28.789442f)
generalPath!!.cubicTo(9.615767f, 28.512362f, 9.55205f, 28.29432f, 9.424617f, 28.135317f)
generalPath!!.cubicTo(9.298349f, 27.975147f, 9.128827f, 27.895063f, 8.916047f, 27.895061f)
generalPath!!.cubicTo(8.707942f, 27.895063f, 8.540173f, 27.975733f, 8.412739f, 28.13707f)
generalPath!!.cubicTo(8.285304f, 28.297241f, 8.221586f, 28.512362f, 8.221587f, 28.782427f)
generalPath!!.cubicTo(8.221586f, 29.050158f, 8.285304f, 29.263523f, 8.412739f, 29.422523f)
generalPath!!.cubicTo(8.541342f, 29.580355f, 8.709111f, 29.659271f, 8.916047f, 29.659271f)
generalPath!!.moveTo(12.781175f, 30.032806f)
generalPath!!.lineTo(12.27436f, 30.032806f)
generalPath!!.lineTo(11.074837f, 28.261581f)
generalPath!!.cubicTo(11.044439f, 28.219496f, 11.019303f, 28.168055f, 10.999429f, 28.107258f)
generalPath!!.lineTo(10.988906f, 28.107258f)
generalPath!!.cubicTo(11.002935f, 28.175068f, 11.00995f, 28.235277f, 11.009951f, 28.287888f)
generalPath!!.lineTo(11.009951f, 30.032806f)
generalPath!!.lineTo(10.561007f, 30.032806f)
generalPath!!.lineTo(10.561007f, 27.518019f)
generalPath!!.lineTo(11.095881f, 27.518019f)
generalPath!!.lineTo(12.249808f, 29.238386f)
generalPath!!.cubicTo(12.276696f, 29.280476f, 12.30417f, 29.330748f, 12.332231f, 29.389202f)
generalPath!!.lineTo(12.342753f, 29.389202f)
generalPath!!.cubicTo(12.33106f, 29.331917f, 12.325214f, 29.262938f, 12.325217f, 29.182268f)
generalPath!!.lineTo(12.325217f, 27.518019f)
generalPath!!.lineTo(12.781175f, 27.518019f)
generalPath!!.lineTo(12.781175f, 30.032806f)
generalPath!!.moveTo(15.169698f, 27.518019f)
generalPath!!.lineTo(14.340204f, 29.080553f)
generalPath!!.lineTo(14.340204f, 30.032806f)
generalPath!!.lineTo(13.859693f, 30.032806f)
generalPath!!.lineTo(13.859693f, 29.091076f)
generalPath!!.lineTo(13.051243f, 27.518019f)
generalPath!!.lineTo(13.586118f, 27.518019f)
generalPath!!.lineTo(13.922826f, 28.280872f)
generalPath!!.cubicTo(14.026877f, 28.5147f, 14.084164f, 28.64681f, 14.094688f, 28.677206f)
generalPath!!.cubicTo(14.105209f, 28.707603f, 14.112808f, 28.733324f, 14.117486f, 28.754368f)
generalPath!!.lineTo(14.120993f, 28.754368f)
generalPath!!.cubicTo(14.135021f, 28.707603f, 14.147297f, 28.673115f, 14.15782f, 28.6509f)
generalPath!!.lineTo(14.675158f, 27.518019f)
generalPath!!.lineTo(15.169698f, 27.518019f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28140703f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.03125f, 20.000248f)
generalPath!!.cubicTo(28.64685f, 20.03536f, 27.531248f, 21.169935f, 27.53125f, 22.562748f)
generalPath!!.cubicTo(27.53125f, 23.977669f, 28.67883f, 25.125246f, 30.09375f, 25.125248f)
generalPath!!.cubicTo(31.000183f, 25.125248f, 31.983568f, 23.968998f, 33.0f, 23.968998f)
generalPath!!.cubicTo(34.018353f, 23.968998f, 34.964146f, 25.125246f, 35.875f, 25.125248f)
generalPath!!.cubicTo(37.28992f, 25.125248f, 38.437504f, 23.977669f, 38.4375f, 22.562748f)
generalPath!!.cubicTo(38.4375f, 21.147827f, 37.28992f, 20.00025f, 35.875f, 20.000248f)
generalPath!!.cubicTo(34.955303f, 20.000248f, 34.08074f, 21.218998f, 33.0f, 21.218998f)
generalPath!!.cubicTo(31.856855f, 21.218998f, 31.009027f, 20.00025f, 30.09375f, 20.000248f)
generalPath!!.cubicTo(30.071642f, 20.000248f, 30.053225f, 19.999691f, 30.03125f, 20.000248f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(32.984375f, 19.999998f), end = Offset(32.984375f, 25.852335f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2820509672164917f, 0.0f, 0.0f, -11.15464973449707f,
0.0f, 1.2820509672164917f, 0.0f, -6.203519821166992f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.34375f, 22.4375f)
generalPath!!.cubicTo(33.345703f, 22.874216f, 33.11384f, 23.278597f, 32.735954f, 23.49752f)
generalPath!!.cubicTo(32.35807f, 23.716444f, 31.891932f, 23.716444f, 31.514046f, 23.49752f)
generalPath!!.cubicTo(31.136162f, 23.278597f, 30.904297f, 22.874216f, 30.90625f, 22.4375f)
generalPath!!.cubicTo(30.904297f, 22.000784f, 31.136162f, 21.596403f, 31.514046f, 21.37748f)
generalPath!!.cubicTo(31.891932f, 21.158556f, 32.35807f, 21.158556f, 32.735954f, 21.37748f)
generalPath!!.cubicTo(33.11384f, 21.596403f, 33.345703f, 22.000784f, 33.34375f, 22.4375f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(212, 212, 212, 255), 1.0f to Color(102, 102, 102, 255), center = Offset(32.125f, 22.03125f), radius = 1.21875f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2820509672164917f, 0.0f, 0.0f, -5.1546502113342285f,
0.0f, 1.2820509672164917f, 0.0f, -6.203519821166992f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.34375f, 22.4375f)
generalPath!!.cubicTo(33.345703f, 22.874216f, 33.11384f, 23.278597f, 32.735954f, 23.49752f)
generalPath!!.cubicTo(32.35807f, 23.716444f, 31.891932f, 23.716444f, 31.514046f, 23.49752f)
generalPath!!.cubicTo(31.136162f, 23.278597f, 30.904297f, 22.874216f, 30.90625f, 22.4375f)
generalPath!!.cubicTo(30.904297f, 22.000784f, 31.136162f, 21.596403f, 31.514046f, 21.37748f)
generalPath!!.cubicTo(31.891932f, 21.158556f, 32.35807f, 21.158556f, 32.735954f, 21.37748f)
generalPath!!.cubicTo(33.11384f, 21.596403f, 33.345703f, 22.000784f, 33.34375f, 22.4375f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(212, 212, 212, 255), 1.0f to Color(102, 102, 102, 255), center = Offset(32.125f, 22.03125f), radius = 1.21875f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8717939853668213f, 0.0f, 0.0f, -18.943880081176758f,
0.0f, 0.8717939853668213f, 0.0f, 17.501619338989258f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.34375f, 22.4375f)
generalPath!!.cubicTo(33.345703f, 22.874216f, 33.11384f, 23.278597f, 32.735954f, 23.49752f)
generalPath!!.cubicTo(32.35807f, 23.716444f, 31.891932f, 23.716444f, 31.514046f, 23.49752f)
generalPath!!.cubicTo(31.136162f, 23.278597f, 30.904297f, 22.874216f, 30.90625f, 22.4375f)
generalPath!!.cubicTo(30.904297f, 22.000784f, 31.136162f, 21.596403f, 31.514046f, 21.37748f)
generalPath!!.cubicTo(31.891932f, 21.158556f, 32.35807f, 21.158556f, 32.735954f, 21.37748f)
generalPath!!.cubicTo(33.11384f, 21.596403f, 33.345703f, 22.000784f, 33.34375f, 22.4375f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(212, 212, 212, 255), 1.0f to Color(102, 102, 102, 255), center = Offset(32.125f, 22.03125f), radius = 1.21875f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8717939853668213f, 0.0f, 0.0f, -15.943880081176758f,
0.0f, 0.8717939853668213f, 0.0f, 17.376619338989258f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.34375f, 22.4375f)
generalPath!!.cubicTo(33.345703f, 22.874216f, 33.11384f, 23.278597f, 32.735954f, 23.49752f)
generalPath!!.cubicTo(32.35807f, 23.716444f, 31.891932f, 23.716444f, 31.514046f, 23.49752f)
generalPath!!.cubicTo(31.136162f, 23.278597f, 30.904297f, 22.874216f, 30.90625f, 22.4375f)
generalPath!!.cubicTo(30.904297f, 22.000784f, 31.136162f, 21.596403f, 31.514046f, 21.37748f)
generalPath!!.cubicTo(31.891932f, 21.158556f, 32.35807f, 21.158556f, 32.735954f, 21.37748f)
generalPath!!.cubicTo(33.11384f, 21.596403f, 33.345703f, 22.000784f, 33.34375f, 22.4375f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(212, 212, 212, 255), 1.0f to Color(102, 102, 102, 255), center = Offset(32.125f, 22.03125f), radius = 1.21875f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8717939853668213f, 0.0f, 0.0f, -12.943880081176758f,
0.0f, 0.8717939853668213f, 0.0f, 17.501619338989258f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.34375f, 22.4375f)
generalPath!!.cubicTo(33.345703f, 22.874216f, 33.11384f, 23.278597f, 32.735954f, 23.49752f)
generalPath!!.cubicTo(32.35807f, 23.716444f, 31.891932f, 23.716444f, 31.514046f, 23.49752f)
generalPath!!.cubicTo(31.136162f, 23.278597f, 30.904297f, 22.874216f, 30.90625f, 22.4375f)
generalPath!!.cubicTo(30.904297f, 22.000784f, 31.136162f, 21.596403f, 31.514046f, 21.37748f)
generalPath!!.cubicTo(31.891932f, 21.158556f, 32.35807f, 21.158556f, 32.735954f, 21.37748f)
generalPath!!.cubicTo(33.11384f, 21.596403f, 33.345703f, 22.000784f, 33.34375f, 22.4375f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(212, 212, 212, 255), 1.0f to Color(102, 102, 102, 255), center = Offset(32.125f, 22.03125f), radius = 1.21875f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_18
brush = Brush.linearGradient(0.0f to Color(128, 128, 128, 255), 1.0f to Color(192, 192, 192, 255), start = Offset(43.462418f, 30.56131f), end = Offset(44.977024f, 35.43631f), tileMode = TileMode.Clamp)
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(40.44781f, 37.298096f)
generalPath!!.cubicTo(40.44781f, 37.298096f, 46.86789f, 36.302113f, 46.458218f, 33.32062f)
generalPath!!.cubicTo(46.06047f, 30.425901f, 42.922688f, 30.845747f, 42.922688f, 30.845747f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9549279808998108f, 0.2968370020389557f, 0.0f, 0.0f,
-0.2968370020389557f, 0.9549279808998108f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_19
shape = Outline.Rounded(roundRect = RoundRect(left = 11.994140625f, top = 45.88050079345703f, right = 29.495019912719727f, bottom = 49.94636154174805f,radiusX = 4.065860748291016f, radiusY = 4.065860748291016f))
brush = SolidColor(Color(190, 190, 190, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(190, 190, 190, 255))
stroke = Stroke(width=0.99999875f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 11.994140625f, top = 45.88050079345703f, right = 29.495019912719727f, bottom = 49.94636154174805f,radiusX = 4.065860748291016f, radiusY = 4.065860748291016f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -4.82219123840332f,
0.0f, 1.0f, 0.0f, -1.7639579772949219f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_20
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -4.94719123840332f,
0.0f, 1.0f, 0.0f, -1.9514579772949219f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_21
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(149, 149, 149, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -3.5405609607696533f,
0.0f, 1.0f, 0.0f, -3.9294729232788086f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_22
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -3.6655609607696533f,
0.0f, 1.0f, 0.0f, -4.116972923278809f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_23
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(149, 149, 149, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -1.5076210498809814f,
0.0f, 1.0f, 0.0f, -5.034327030181885f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_24
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -1.6326210498809814f,
0.0f, 1.0f, 0.0f, -5.221827030181885f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_25
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(196, 196, 196, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, -1.6326210498809814f,
0.0f, 1.0f, 0.0f, -5.221827030181885f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_26
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.870056f, 40.840546f)
generalPath!!.cubicTo(26.871048f, 41.062252f, 26.753338f, 41.26754f, 26.561499f, 41.37868f)
generalPath!!.cubicTo(26.36966f, 41.489822f, 26.133018f, 41.489822f, 25.94118f, 41.37868f)
generalPath!!.cubicTo(25.74934f, 41.26754f, 25.63163f, 41.062252f, 25.632622f, 40.840546f)
generalPath!!.cubicTo(25.63163f, 40.61884f, 25.74934f, 40.41355f, 25.94118f, 40.30241f)
generalPath!!.cubicTo(26.133018f, 40.19127f, 26.36966f, 40.19127f, 26.561499f, 40.30241f)
generalPath!!.cubicTo(26.753338f, 40.41355f, 26.871048f, 40.61884f, 26.870056f, 40.840546f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(149, 149, 149, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9549279808998108f, 0.2968370020389557f, 0.0f, 0.0f,
-0.2968370020389557f, 0.9549279808998108f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_27
shape = Outline.Rounded(roundRect = RoundRect(left = 11.994140625f, top = 45.88050079345703f, right = 24.991881370544434f, bottom = 49.84241342544556f,radiusX = 4.065860748291016f, radiusY = 3.893588066101074f))
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(203, 203, 203, 255), start = Offset(20.466257f, 46.392647f), end = Offset(20.495934f, 49.640648f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(190, 190, 190, 255))
stroke = Stroke(width=0.99999875f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
shape = Outline.Rounded(roundRect = RoundRect(left = 11.994140625f, top = 45.88050079345703f, right = 24.991881370544434f, bottom = 49.84241342544556f,radiusX = 4.065860748291016f, radiusY = 3.893588066101074f))
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5833333f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_28
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(11.5625f, 10.658537f)
generalPath!!.lineTo(11.5625f, 12.341463f)
generalPath!!.lineTo(13.0f, 11.464939f)
generalPath!!.lineTo(11.5625f, 10.658537f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.51666665f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_29
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(14.46875f, 10.223621f)
generalPath!!.lineTo(14.46875f, 10.176186f)
generalPath!!.cubicTo(14.473268f, 10.155857f, 14.484561f, 10.135528f, 14.502632f, 10.115199f)
generalPath!!.cubicTo(14.522961f, 10.094871f, 14.545549f, 10.0768f, 14.570396f, 10.060987f)
generalPath!!.cubicTo(14.595242f, 10.042918f, 14.620089f, 10.028236f, 14.644936f, 10.016941f)
generalPath!!.cubicTo(14.672042f, 10.005647f, 14.694629f, 10.000001f, 14.7127f, 10.0f)
generalPath!!.lineTo(15.817252f, 10.0f)
generalPath!!.lineTo(15.817252f, 10.711521f)
generalPath!!.cubicTo(15.810474f, 10.729591f, 15.798051f, 10.748791f, 15.779982f, 10.76912f)
generalPath!!.cubicTo(15.76191f, 10.787191f, 15.741581f, 10.805262f, 15.718994f, 10.823332f)
generalPath!!.cubicTo(15.696405f, 10.839143f, 15.672688f, 10.852696f, 15.647842f, 10.86399f)
generalPath!!.cubicTo(15.625253f, 10.875284f, 15.606053f, 10.880931f, 15.590243f, 10.880931f)
generalPath!!.lineTo(15.583467f, 10.880931f)
generalPath!!.lineTo(14.46875f, 10.880931f)
generalPath!!.lineTo(14.46875f, 10.223621f)
generalPath!!.moveTo(15.549585f, 10.206679f)
generalPath!!.lineTo(14.871946f, 10.206679f)
generalPath!!.cubicTo(14.838063f, 10.217974f, 14.812087f, 10.232657f, 14.794017f, 10.250726f)
generalPath!!.cubicTo(14.775946f, 10.268797f, 14.762394f, 10.291386f, 14.753359f, 10.31849f)
generalPath!!.lineTo(14.753359f, 10.677639f)
generalPath!!.lineTo(15.414057f, 10.677639f)
generalPath!!.cubicTo(15.461491f, 10.666345f, 15.494244f, 10.652793f, 15.512315f, 10.636981f)
generalPath!!.cubicTo(15.530384f, 10.62117f, 15.542808f, 10.595194f, 15.549585f, 10.559052f)
generalPath!!.lineTo(15.549585f, 10.206679f)
generalPath!!.moveTo(15.956962f, 10.0f)
generalPath!!.lineTo(15.956962f, 10.880931f)
generalPath!!.lineTo(16.234795f, 10.880931f)
generalPath!!.lineTo(16.234795f, 10.525171f)
generalPath!!.lineTo(16.773518f, 10.525171f)
generalPath!!.cubicTo(16.789328f, 10.525171f, 16.802881f, 10.529688f, 16.814177f, 10.538723f)
generalPath!!.cubicTo(16.827728f, 10.547758f, 16.84015f, 10.560182f, 16.851446f, 10.575993f)
generalPath!!.cubicTo(16.86274f, 10.589546f, 16.874033f, 10.604229f, 16.885328f, 10.62004f)
generalPath!!.cubicTo(16.89888f, 10.633593f, 16.911303f, 10.646016f, 16.922598f, 10.65731f)
generalPath!!.cubicTo(16.940666f, 10.682157f, 16.97342f, 10.756697f, 17.020857f, 10.880931f)
generalPath!!.lineTo(17.356289f, 10.880931f)
generalPath!!.cubicTo(17.356285f, 10.851567f, 17.349508f, 10.818814f, 17.335958f, 10.782673f)
generalPath!!.cubicTo(17.322405f, 10.746532f, 17.304335f, 10.709262f, 17.281748f, 10.670863f)
generalPath!!.cubicTo(17.259157f, 10.632464f, 17.233183f, 10.595194f, 17.20382f, 10.559052f)
generalPath!!.cubicTo(17.176712f, 10.522912f, 17.147348f, 10.490159f, 17.115726f, 10.460795f)
generalPath!!.lineTo(17.356289f, 10.460795f)
generalPath!!.cubicTo(17.351768f, 10.431431f, 17.341604f, 10.399808f, 17.325794f, 10.365925f)
generalPath!!.cubicTo(17.30998f, 10.329785f, 17.279488f, 10.284609f, 17.234312f, 10.230397f)
generalPath!!.cubicTo(17.211723f, 10.205551f, 17.185747f, 10.179575f, 17.156384f, 10.152469f)
generalPath!!.cubicTo(17.12702f, 10.125364f, 17.093136f, 10.101647f, 17.054737f, 10.081317f)
generalPath!!.cubicTo(17.018597f, 10.058729f, 16.979067f, 10.040659f, 16.936152f, 10.027105f)
generalPath!!.cubicTo(16.895493f, 10.011295f, 16.852575f, 10.00226f, 16.8074f, 10.0f)
generalPath!!.lineTo(15.956962f, 10.0f)
generalPath!!.moveTo(16.234795f, 10.355761f)
generalPath!!.lineTo(16.234795f, 10.18635f)
generalPath!!.lineTo(16.759966f, 10.18635f)
generalPath!!.cubicTo(16.773518f, 10.190869f, 16.78707f, 10.196516f, 16.800623f, 10.203292f)
generalPath!!.cubicTo(16.814175f, 10.210069f, 16.831116f, 10.223622f, 16.851446f, 10.24395f)
generalPath!!.cubicTo(16.871775f, 10.26428f, 16.887587f, 10.284609f, 16.898882f, 10.304938f)
generalPath!!.cubicTo(16.910173f, 10.325267f, 16.91808f, 10.342208f, 16.922598f, 10.355761f)
generalPath!!.lineTo(16.234795f, 10.355761f)
generalPath!!.moveTo(17.494886f, 10.0f)
generalPath!!.lineTo(17.494886f, 10.880931f)
generalPath!!.lineTo(18.89421f, 10.880931f)
generalPath!!.cubicTo(18.89421f, 10.851567f, 18.887432f, 10.818814f, 18.873882f, 10.782673f)
generalPath!!.cubicTo(18.860329f, 10.746532f, 18.842257f, 10.709262f, 18.819672f, 10.670863f)
generalPath!!.cubicTo(18.797081f, 10.632464f, 18.771105f, 10.595194f, 18.741741f, 10.559052f)
generalPath!!.cubicTo(18.714636f, 10.522912f, 18.68527f, 10.490159f, 18.653648f, 10.460795f)
generalPath!!.lineTo(18.89421f, 10.460795f)
generalPath!!.cubicTo(18.889692f, 10.431431f, 18.879526f, 10.400937f, 18.863716f, 10.369313f)
generalPath!!.cubicTo(18.847904f, 10.335432f, 18.81741f, 10.290256f, 18.772236f, 10.233786f)
generalPath!!.cubicTo(18.751905f, 10.20668f, 18.72593f, 10.179575f, 18.694307f, 10.152469f)
generalPath!!.cubicTo(18.664942f, 10.125364f, 18.63106f, 10.101647f, 18.592663f, 10.081317f)
generalPath!!.cubicTo(18.556519f, 10.058729f, 18.51699f, 10.040659f, 18.474073f, 10.027105f)
generalPath!!.cubicTo(18.433416f, 10.011295f, 18.390497f, 10.00226f, 18.345324f, 10.0f)
generalPath!!.lineTo(17.494886f, 10.0f)
generalPath!!.moveTo(17.776106f, 10.694581f)
generalPath!!.lineTo(17.776106f, 10.525171f)
generalPath!!.lineTo(18.311441f, 10.525171f)
generalPath!!.cubicTo(18.336287f, 10.525171f, 18.361134f, 10.542112f, 18.38598f, 10.575993f)
generalPath!!.cubicTo(18.410828f, 10.607616f, 18.430027f, 10.647145f, 18.44358f, 10.694581f)
generalPath!!.lineTo(17.776106f, 10.694581f)
generalPath!!.moveTo(17.772718f, 10.355761f)
generalPath!!.lineTo(17.772718f, 10.18635f)
generalPath!!.lineTo(18.297888f, 10.18635f)
generalPath!!.cubicTo(18.311441f, 10.190869f, 18.324993f, 10.196516f, 18.338547f, 10.203292f)
generalPath!!.cubicTo(18.352098f, 10.210069f, 18.36904f, 10.223622f, 18.38937f, 10.24395f)
generalPath!!.cubicTo(18.409698f, 10.26428f, 18.425508f, 10.284609f, 18.436804f, 10.304938f)
generalPath!!.cubicTo(18.448097f, 10.325267f, 18.456003f, 10.342208f, 18.460522f, 10.355761f)
generalPath!!.lineTo(17.772718f, 10.355761f)
generalPath!!.moveTo(19.04173f, 10.0f)
generalPath!!.lineTo(19.04173f, 10.880931f)
generalPath!!.lineTo(19.326338f, 10.880931f)
generalPath!!.lineTo(19.326338f, 10.0f)
generalPath!!.lineTo(19.04173f, 10.0f)
generalPath!!.moveTo(20.558346f, 10.18635f)
generalPath!!.lineTo(20.558346f, 10.0f)
generalPath!!.lineTo(19.47751f, 10.0f)
generalPath!!.lineTo(19.47751f, 10.18635f)
generalPath!!.lineTo(19.87054f, 10.18635f)
generalPath!!.lineTo(19.87054f, 10.880931f)
generalPath!!.lineTo(20.165316f, 10.880931f)
generalPath!!.lineTo(20.165316f, 10.267667f)
generalPath!!.lineTo(20.165316f, 10.18635f)
generalPath!!.lineTo(20.558346f, 10.18635f)
generalPath!!.moveTo(20.699194f, 10.880931f)
generalPath!!.lineTo(20.699194f, 10.182963f)
generalPath!!.cubicTo(20.70823f, 10.160375f, 20.72178f, 10.137787f, 20.739853f, 10.115199f)
generalPath!!.cubicTo(20.760181f, 10.092612f, 20.782768f, 10.073412f, 20.807615f, 10.057599f)
generalPath!!.cubicTo(20.832462f, 10.03953f, 20.85731f, 10.025977f, 20.882156f, 10.016941f)
generalPath!!.cubicTo(20.907003f, 10.005647f, 20.92959f, 10.000001f, 20.94992f, 10.0f)
generalPath!!.lineTo(22.054472f, 10.0f)
generalPath!!.lineTo(22.054472f, 10.880931f)
generalPath!!.lineTo(21.769863f, 10.880931f)
generalPath!!.lineTo(21.769863f, 10.65731f)
generalPath!!.lineTo(20.97025f, 10.65731f)
generalPath!!.lineTo(20.97025f, 10.880931f)
generalPath!!.lineTo(20.699194f, 10.880931f)
generalPath!!.moveTo(21.769863f, 10.4879f)
generalPath!!.lineTo(21.769863f, 10.206679f)
generalPath!!.lineTo(21.109165f, 10.206679f)
generalPath!!.cubicTo(21.06173f, 10.217974f, 21.028978f, 10.231527f, 21.010906f, 10.247338f)
generalPath!!.cubicTo(20.992836f, 10.263151f, 20.979284f, 10.286867f, 20.97025f, 10.31849f)
generalPath!!.lineTo(20.97025f, 10.4879f)
generalPath!!.lineTo(21.769863f, 10.4879f)
generalPath!!.moveTo(22.17814f, 10.0f)
generalPath!!.lineTo(22.466139f, 10.0f)
generalPath!!.lineTo(22.466139f, 10.609875f)
generalPath!!.cubicTo(22.484207f, 10.655052f, 22.525995f, 10.683287f, 22.591501f, 10.694581f)
generalPath!!.lineTo(23.262363f, 10.694581f)
generalPath!!.lineTo(23.262363f, 10.880931f)
generalPath!!.lineTo(22.449198f, 10.880931f)
generalPath!!.cubicTo(22.424349f, 10.880931f, 22.396114f, 10.874155f, 22.364492f, 10.860602f)
generalPath!!.cubicTo(22.332869f, 10.844791f, 22.303505f, 10.825591f, 22.2764f, 10.803003f)
generalPath!!.cubicTo(22.249292f, 10.778156f, 22.225576f, 10.75218f, 22.205248f, 10.725074f)
generalPath!!.cubicTo(22.187176f, 10.69571f, 22.17814f, 10.668604f, 22.17814f, 10.643758f)
generalPath!!.lineTo(22.17814f, 10.0f)
generalPath!!.moveTo(24.828371f, 10.355761f)
generalPath!!.lineTo(24.828371f, 10.525171f)
generalPath!!.lineTo(24.204945f, 10.525171f)
generalPath!!.lineTo(24.204945f, 10.355761f)
generalPath!!.lineTo(24.828371f, 10.355761f)
generalPath!!.moveTo(25.804888f, 10.0f)
generalPath!!.lineTo(25.804888f, 10.880931f)
generalPath!!.lineTo(26.089497f, 10.880931f)
generalPath!!.lineTo(26.089497f, 10.0f)
generalPath!!.lineTo(25.804888f, 10.0f)
generalPath!!.moveTo(28.726334f, 10.0f)
generalPath!!.lineTo(28.726334f, 10.697968f)
generalPath!!.cubicTo(28.717297f, 10.720556f, 28.702616f, 10.743145f, 28.682287f, 10.765733f)
generalPath!!.cubicTo(28.664215f, 10.786061f, 28.642756f, 10.805262f, 28.61791f, 10.823332f)
generalPath!!.cubicTo(28.595322f, 10.839143f, 28.570475f, 10.852696f, 28.543371f, 10.86399f)
generalPath!!.cubicTo(28.518522f, 10.875284f, 28.495935f, 10.880931f, 28.475607f, 10.880931f)
generalPath!!.lineTo(28.011423f, 10.880931f)
generalPath!!.lineTo(28.011423f, 10.745403f)
generalPath!!.cubicTo(27.95947f, 10.790579f, 27.915424f, 10.824461f, 27.879284f, 10.847049f)
generalPath!!.cubicTo(27.843143f, 10.869637f, 27.809261f, 10.880931f, 27.777637f, 10.880931f)
generalPath!!.lineTo(27.059341f, 10.880931f)
generalPath!!.lineTo(27.059341f, 10.0f)
generalPath!!.lineTo(27.34395f, 10.0f)
generalPath!!.lineTo(27.34395f, 10.674251f)
generalPath!!.lineTo(27.618393f, 10.674251f)
generalPath!!.cubicTo(27.665829f, 10.662957f, 27.69858f, 10.649404f, 27.716652f, 10.633593f)
generalPath!!.cubicTo(27.73472f, 10.617781f, 27.748274f, 10.594064f, 27.757309f, 10.562441f)
generalPath!!.lineTo(27.757309f, 10.0f)
generalPath!!.lineTo(28.028364f, 10.0f)
generalPath!!.lineTo(28.028364f, 10.674251f)
generalPath!!.lineTo(28.316362f, 10.674251f)
generalPath!!.cubicTo(28.363794f, 10.662957f, 28.396547f, 10.649404f, 28.41462f, 10.633593f)
generalPath!!.cubicTo(28.43269f, 10.617781f, 28.446241f, 10.594064f, 28.455278f, 10.562441f)
generalPath!!.lineTo(28.455278f, 10.0f)
generalPath!!.lineTo(28.726334f, 10.0f)
generalPath!!.moveTo(28.87147f, 10.0f)
generalPath!!.lineTo(28.87147f, 10.880931f)
generalPath!!.lineTo(29.156078f, 10.880931f)
generalPath!!.lineTo(29.156078f, 10.0f)
generalPath!!.lineTo(28.87147f, 10.0f)
generalPath!!.moveTo(29.307251f, 10.687803f)
generalPath!!.lineTo(29.307251f, 10.880931f)
generalPath!!.lineTo(30.48973f, 10.880931f)
generalPath!!.cubicTo(30.523611f, 10.880931f, 30.555235f, 10.873026f, 30.5846f, 10.857213f)
generalPath!!.cubicTo(30.613962f, 10.839143f, 30.638811f, 10.817685f, 30.659142f, 10.792838f)
generalPath!!.cubicTo(30.681728f, 10.765733f, 30.69867f, 10.736368f, 30.709965f, 10.704745f)
generalPath!!.cubicTo(30.723515f, 10.673122f, 30.730291f, 10.641499f, 30.730293f, 10.609875f)
generalPath!!.cubicTo(30.730291f, 10.589546f, 30.725775f, 10.5647f, 30.71674f, 10.535335f)
generalPath!!.cubicTo(30.707705f, 10.505971f, 30.69528f, 10.477736f, 30.67947f, 10.45063f)
generalPath!!.cubicTo(30.665916f, 10.423525f, 30.647846f, 10.400937f, 30.62526f, 10.382866f)
generalPath!!.cubicTo(30.602669f, 10.364797f, 30.578953f, 10.355761f, 30.554108f, 10.355761f)
generalPath!!.lineTo(29.710445f, 10.355761f)
generalPath!!.cubicTo(29.70141f, 10.339949f, 29.696894f, 10.328655f, 29.696894f, 10.321879f)
generalPath!!.cubicTo(29.696894f, 10.312844f, 29.698023f, 10.304939f, 29.700281f, 10.298161f)
generalPath!!.cubicTo(29.704798f, 10.291386f, 29.710445f, 10.28235f, 29.717222f, 10.271056f)
generalPath!!.cubicTo(29.730776f, 10.252986f, 29.746586f, 10.234916f, 29.764656f, 10.216844f)
generalPath!!.cubicTo(29.784986f, 10.196516f, 29.79515f, 10.186351f, 29.79515f, 10.18635f)
generalPath!!.lineTo(30.62187f, 10.18635f)
generalPath!!.lineTo(30.62187f, 10.0f)
generalPath!!.lineTo(29.79515f, 10.0f)
generalPath!!.cubicTo(29.722868f, 10.000001f, 29.657364f, 10.014683f, 29.598637f, 10.044047f)
generalPath!!.cubicTo(29.542166f, 10.073412f, 29.492472f, 10.112941f, 29.449554f, 10.162634f)
generalPath!!.cubicTo(29.408897f, 10.210069f, 29.377274f, 10.265409f, 29.354685f, 10.328655f)
generalPath!!.cubicTo(29.332096f, 10.391902f, 29.320803f, 10.457406f, 29.320803f, 10.525171f)
generalPath!!.lineTo(30.32371f, 10.525171f)
generalPath!!.cubicTo(30.328226f, 10.525171f, 30.335003f, 10.536464f, 30.34404f, 10.559052f)
generalPath!!.cubicTo(30.353071f, 10.579382f, 30.35759f, 10.596322f, 30.357592f, 10.609875f)
generalPath!!.cubicTo(30.35759f, 10.62117f, 30.353071f, 10.636981f, 30.34404f, 10.65731f)
generalPath!!.cubicTo(30.335003f, 10.677639f, 30.325968f, 10.687804f, 30.316933f, 10.687803f)
generalPath!!.lineTo(29.307251f, 10.687803f)
generalPath!!.moveTo(30.863703f, 10.0f)
generalPath!!.lineTo(30.863703f, 10.880931f)
generalPath!!.lineTo(31.148312f, 10.880931f)
generalPath!!.lineTo(31.148312f, 10.528558f)
generalPath!!.lineTo(31.934374f, 10.528558f)
generalPath!!.lineTo(31.934374f, 10.880931f)
generalPath!!.lineTo(32.218983f, 10.880931f)
generalPath!!.lineTo(32.218983f, 10.0f)
generalPath!!.lineTo(31.927597f, 10.0f)
generalPath!!.lineTo(31.927597f, 10.355761f)
generalPath!!.lineTo(31.1517f, 10.355761f)
generalPath!!.lineTo(31.1517f, 10.0f)
generalPath!!.lineTo(30.863703f, 10.0f)
generalPath!!.moveTo(33.18414f, 10.0f)
generalPath!!.lineTo(33.18414f, 10.880931f)
generalPath!!.lineTo(33.46875f, 10.880931f)
generalPath!!.lineTo(33.46875f, 10.0f)
generalPath!!.lineTo(33.18414f, 10.0f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.47666666f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_30
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(15.540446f, 13.0f)
generalPath!!.lineTo(15.0f, 13.0f)
generalPath!!.lineTo(15.0f, 12.0f)
generalPath!!.lineTo(15.140167f, 12.0f)
generalPath!!.lineTo(15.140167f, 12.873082f)
generalPath!!.lineTo(15.540446f, 12.873082f)
generalPath!!.lineTo(15.540446f, 13.0f)
generalPath!!.moveTo(15.685495f, 12.0f)
generalPath!!.lineTo(15.960949f, 12.0f)
generalPath!!.cubicTo(16.072525f, 12.000001f, 16.157833f, 12.025803f, 16.216877f, 12.077405f)
generalPath!!.cubicTo(16.276382f, 12.129011f, 16.306135f, 12.201767f, 16.306137f, 12.295676f)
generalPath!!.cubicTo(16.306135f, 12.388192f, 16.273129f, 12.465133f, 16.207113f, 12.526499f)
generalPath!!.cubicTo(16.141562f, 12.587866f, 16.050907f, 12.61855f, 15.935147f, 12.618549f)
generalPath!!.lineTo(15.822176f, 12.618549f)
generalPath!!.lineTo(15.822176f, 13.0f)
generalPath!!.lineTo(15.685495f, 13.0f)
generalPath!!.lineTo(15.685495f, 12.0f)
generalPath!!.moveTo(15.822176f, 12.49721f)
generalPath!!.lineTo(15.936542f, 12.49721f)
generalPath!!.cubicTo(16.01232f, 12.497211f, 16.070665f, 12.480474f, 16.111576f, 12.447002f)
generalPath!!.cubicTo(16.152952f, 12.413064f, 16.17364f, 12.364714f, 16.173641f, 12.301953f)
generalPath!!.cubicTo(16.17364f, 12.242446f, 16.154114f, 12.19642f, 16.115063f, 12.163877f)
generalPath!!.cubicTo(16.076475f, 12.130869f, 16.020687f, 12.114366f, 15.947699f, 12.114365f)
generalPath!!.lineTo(15.822176f, 12.114365f)
generalPath!!.lineTo(15.822176f, 12.49721f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)

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
            return 0.22437061369419098
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.055053472518921
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 47.25265121459961
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 44.921173095703125
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return multimedia_player(
                _width = (width.value * AmbientDensity.current.density).toInt(),
                _height = (height.value * AmbientDensity.current.density).toInt()
            )
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return multimedia_player(getOrigWidth().toInt(), getOrigHeight().toInt())
                }
            }
        }

        
    }

    override fun getWidth(): Int {
        return _width
    }

    override fun getHeight(): Int {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = (width.value * AmbientDensity.current.density).toInt()
        _height = (height.value * AmbientDensity.current.density).toInt()
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = _width / fullOrigWidth
            val coef2 = _height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()
            val coefDp = coef.dp.toPx()

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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset(0.0f, 0.0f))
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

