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
class video_display private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
alpha *= 0.50857145f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0502510070800781f, 0.0f, 0.0f, -0.9455580115318298f,
0.0f, 1.8678879737854004f, 0.0f, -28.106109619140625f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(41.10058f, 35.051105f)
generalPath!!.cubicTo(41.127632f, 36.682228f, 37.915836f, 38.192577f, 32.681362f, 39.010254f)
generalPath!!.cubicTo(27.446886f, 39.827927f, 20.989925f, 39.827927f, 15.755449f, 39.010254f)
generalPath!!.cubicTo(10.520973f, 38.192577f, 7.3091793f, 36.682228f, 7.336233f, 35.051105f)
generalPath!!.cubicTo(7.3091793f, 33.419983f, 10.520973f, 31.909634f, 15.755449f, 31.091959f)
generalPath!!.cubicTo(20.989925f, 30.274284f, 27.446886f, 30.274284f, 32.681362f, 31.091959f)
generalPath!!.cubicTo(37.915836f, 31.909634f, 41.127632f, 33.419983f, 41.10058f, 35.051105f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.218403f, 35.051075f), radius = 16.882172f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 60.033390045166016f,
0.0f, 1.0f, 0.0f, 8.07843017578125f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-26.263968f, 29.716238f)
generalPath!!.cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
generalPath!!.cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
generalPath!!.cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
generalPath!!.cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
generalPath!!.cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
generalPath!!.cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(173, 176, 170, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(75, 77, 74, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-26.263968f, 29.716238f)
generalPath!!.cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
generalPath!!.cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
generalPath!!.cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
generalPath!!.cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
generalPath!!.cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
generalPath!!.cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
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
0.9023730158805847f, 0.0f, 0.0f, 56.55215072631836f,
0.0f, 0.8276500105857849f, 0.0f, 12.867919921875f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
brush = Brush.linearGradient(0.0f to Color(123, 127, 122, 255), 1.0f to Color(123, 127, 122, 0), start = Offset(-35.658386f, 33.416473f), end = Offset(-35.658386f, 28.205938f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1571338f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-26.263968f, 29.716238f)
generalPath!!.cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
generalPath!!.cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
generalPath!!.cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
generalPath!!.cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
generalPath!!.cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
generalPath!!.cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
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
0.837548017501831f, 0.0f, 0.0f, 54.17810821533203f,
0.0f, 0.8526549935340881f, 0.0f, 11.006150245666504f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(-35.12269f, 34.242237f), end = Offset(-35.074745f, 30.962345f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.1833371f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-26.263968f, 29.716238f)
generalPath!!.cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
generalPath!!.cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
generalPath!!.cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
generalPath!!.cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
generalPath!!.cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
generalPath!!.cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
shape = Outline.Rectangle(rect = Rect(left = 19.972396850585938f, top = 31.07861328125f, right = 29.012069702148438f, bottom = 37.44365215301514f))
brush = Brush.linearGradient(0.0f to Color(88, 89, 86, 255), 1.0f to Color(187, 190, 184, 255), start = Offset(24.671595f, 28.222458f), end = Offset(24.528107f, 42.74772f), tileMode = TileMode.Clamp)
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.5809026f, 4.570622f)
generalPath!!.lineTo(41.169098f, 4.570622f)
generalPath!!.cubicTo(42.08044f, 4.570622f, 42.793243f, 5.1541038f, 42.83585f, 5.972209f)
generalPath!!.lineTo(44.167892f, 31.550323f)
generalPath!!.cubicTo(44.2261f, 32.668056f, 43.266838f, 33.57063f, 42.147587f, 33.57063f)
generalPath!!.lineTo(6.602412f, 33.57063f)
generalPath!!.cubicTo(5.483163f, 33.57063f, 4.523898f, 32.668056f, 4.5821066f, 31.550323f)
generalPath!!.lineTo(5.9141507f, 5.972209f)
generalPath!!.cubicTo(5.9544344f, 5.1986747f, 6.461653f, 4.570622f, 7.5809026f, 4.570622f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(221, 225, 217, 255), 1.0f to Color(202, 205, 198, 255), start = Offset(12.604956f, 7.9690657f), end = Offset(42.17669f, 31.078438f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(143, 143, 143, 255), 1.0f to Color(73, 73, 73, 255), start = Offset(15.9755f, 7.7480407f), end = Offset(40.43357f, 31.167397f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.5809026f, 4.570622f)
generalPath!!.lineTo(41.169098f, 4.570622f)
generalPath!!.cubicTo(42.08044f, 4.570622f, 42.793243f, 5.1541038f, 42.83585f, 5.972209f)
generalPath!!.lineTo(44.167892f, 31.550323f)
generalPath!!.cubicTo(44.2261f, 32.668056f, 43.266838f, 33.57063f, 42.147587f, 33.57063f)
generalPath!!.lineTo(6.602412f, 33.57063f)
generalPath!!.cubicTo(5.483163f, 33.57063f, 4.523898f, 32.668056f, 4.5821066f, 31.550323f)
generalPath!!.lineTo(5.9141507f, 5.972209f)
generalPath!!.cubicTo(5.9544344f, 5.1986747f, 6.461653f, 4.570622f, 7.5809026f, 4.570622f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.910535f, 7.180827f)
generalPath!!.lineTo(7.6683397f, 29.226145f)
generalPath!!.lineTo(39.31873f, 29.226145f)
generalPath!!.lineTo(37.98371f, 7.274256f)
generalPath!!.lineTo(8.910535f, 7.180827f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(91, 91, 151, 255), 1.0f to Color(27, 27, 67, 255), start = Offset(27.707052f, 32.38555f), end = Offset(24.378864f, 9.926256f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(0, 0, 121, 255))
stroke = Stroke(width=0.5f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.910535f, 7.180827f)
generalPath!!.lineTo(7.6683397f, 29.226145f)
generalPath!!.lineTo(39.31873f, 29.226145f)
generalPath!!.lineTo(37.98371f, 7.274256f)
generalPath!!.lineTo(8.910535f, 7.180827f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 63), 1.0f to Color(0, 0, 0, 0), start = Offset(26.649012f, 32.219574f), end = Offset(26.64901f, 30.66997f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9961812f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(6.677433f, 31.610788f)
generalPath!!.lineTo(42.10591f, 31.610788f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 179), 1.0f to Color(255, 255, 255, 0), start = Offset(25.110981f, 15.611387f), end = Offset(44.25421f, 53.69208f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999964f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(7.4145985f, 5.58134f)
generalPath!!.lineTo(41.2601f, 5.543538f)
generalPath!!.cubicTo(41.543797f, 5.5432215f, 41.819405f, 5.780788f, 41.842205f, 6.196082f)
generalPath!!.lineTo(43.204098f, 30.99933f)
generalPath!!.cubicTo(43.26214f, 32.056362f, 42.66435f, 32.785202f, 41.60573f, 32.785202f)
generalPath!!.lineTo(7.0817585f, 32.785202f)
generalPath!!.cubicTo(6.0231357f, 32.785202f, 5.488744f, 32.05641f, 5.545887f, 30.99933f)
generalPath!!.lineTo(6.8699775f, 6.505163f)
generalPath!!.cubicTo(6.9086733f, 5.7893324f, 7.0363626f, 5.581762f, 7.4145985f, 5.58134f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5314286f
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
generalPath!!.moveTo(9.211536f, 7.621363f)
generalPath!!.lineTo(8.409007f, 25.491693f)
generalPath!!.cubicTo(19.453646f, 23.091063f, 23.83047f, 14.999494f, 37.563038f, 12.344943f)
generalPath!!.lineTo(37.401566f, 7.687427f)
generalPath!!.lineTo(9.211536f, 7.621363f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(252, 252, 255, 0), start = Offset(19.329168f, 3.0251684f), end = Offset(26.400234f, 25.491693f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3312369585037231f, 0.0f, 0.0f, -5.91933012008667f,
0.0f, 0.6584489941596985f, 0.0f, 5.728866100311279f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(35.620502f, 3.9384086f)
generalPath!!.cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
generalPath!!.cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
generalPath!!.cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
generalPath!!.cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
generalPath!!.cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
generalPath!!.cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3312369585037231f, 0.0f, 0.0f, -5.805729866027832f,
0.0f, 0.6584489941596985f, 0.0f, 7.834650039672852f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(35.620502f, 3.9384086f)
generalPath!!.cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
generalPath!!.cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
generalPath!!.cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
generalPath!!.cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
generalPath!!.cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
generalPath!!.cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3312369585037231f, 0.0f, 0.0f, -5.692130088806152f,
0.0f, 0.6584489941596985f, 0.0f, 9.834650039672852f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(35.620502f, 3.9384086f)
generalPath!!.cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
generalPath!!.cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
generalPath!!.cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
generalPath!!.cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
generalPath!!.cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
generalPath!!.cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3312369585037231f, 0.0f, 0.0f, -5.5785298347473145f,
0.0f, 0.6584489941596985f, 0.0f, 11.834650039672852f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(35.620502f, 3.9384086f)
generalPath!!.cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
generalPath!!.cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
generalPath!!.cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
generalPath!!.cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
generalPath!!.cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
generalPath!!.cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3312369585037231f, 0.0f, 0.0f, -5.464930057525635f,
0.0f, 0.6584489941596985f, 0.0f, 13.834650039672852f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(35.620502f, 3.9384086f)
generalPath!!.cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
generalPath!!.cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
generalPath!!.cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
generalPath!!.cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
generalPath!!.cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
generalPath!!.cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
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
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(22.5f, 30.192665f)
generalPath!!.lineTo(22.781715f, 30.192665f)
generalPath!!.cubicTo(22.86548f, 30.192667f, 22.9297f, 30.21133f, 22.974377f, 30.248655f)
generalPath!!.cubicTo(23.019344f, 30.28569f, 23.041828f, 30.338594f, 23.04183f, 30.40737f)
generalPath!!.cubicTo(23.041828f, 30.47644f, 23.019344f, 30.529638f, 22.974377f, 30.566965f)
generalPath!!.cubicTo(22.9297f, 30.603998f, 22.86548f, 30.622515f, 22.781715f, 30.622515f)
generalPath!!.lineTo(22.669735f, 30.622515f)
generalPath!!.lineTo(22.669735f, 30.850885f)
generalPath!!.lineTo(22.5f, 30.850885f)
generalPath!!.lineTo(22.5f, 30.192665f)
generalPath!!.moveTo(22.669735f, 30.315668f)
generalPath!!.lineTo(22.669735f, 30.499512f)
generalPath!!.lineTo(22.76364f, 30.499512f)
generalPath!!.cubicTo(22.796558f, 30.499512f, 22.821981f, 30.491575f, 22.83991f, 30.475704f)
generalPath!!.cubicTo(22.85784f, 30.45954f, 22.866804f, 30.436762f, 22.866804f, 30.40737f)
generalPath!!.cubicTo(22.866804f, 30.37798f, 22.85784f, 30.355349f, 22.83991f, 30.339476f)
generalPath!!.cubicTo(22.821981f, 30.323605f, 22.796558f, 30.315668f, 22.76364f, 30.315668f)
generalPath!!.lineTo(22.669735f, 30.315668f)
generalPath!!.moveTo(23.461979f, 30.303764f)
generalPath!!.cubicTo(23.41025f, 30.303766f, 23.37013f, 30.32287f, 23.341621f, 30.361078f)
generalPath!!.cubicTo(23.313112f, 30.399288f, 23.298857f, 30.453074f, 23.298857f, 30.522436f)
generalPath!!.cubicTo(23.298857f, 30.591507f, 23.313112f, 30.645145f, 23.341621f, 30.683355f)
generalPath!!.cubicTo(23.37013f, 30.721563f, 23.41025f, 30.740667f, 23.461979f, 30.740667f)
generalPath!!.cubicTo(23.514002f, 30.740667f, 23.554268f, 30.721563f, 23.582779f, 30.683355f)
generalPath!!.cubicTo(23.611286f, 30.645145f, 23.625542f, 30.591507f, 23.625542f, 30.522436f)
generalPath!!.cubicTo(23.625542f, 30.453074f, 23.611286f, 30.399288f, 23.582779f, 30.361078f)
generalPath!!.cubicTo(23.554268f, 30.32287f, 23.514002f, 30.303766f, 23.461979f, 30.303764f)
generalPath!!.moveTo(23.461979f, 30.180761f)
generalPath!!.cubicTo(23.567787f, 30.180763f, 23.650671f, 30.211037f, 23.71063f, 30.271582f)
generalPath!!.cubicTo(23.770588f, 30.332129f, 23.800568f, 30.415747f, 23.800568f, 30.522436f)
generalPath!!.cubicTo(23.800568f, 30.628834f, 23.770588f, 30.712305f, 23.71063f, 30.772852f)
generalPath!!.cubicTo(23.650671f, 30.833399f, 23.567787f, 30.86367f, 23.461979f, 30.86367f)
generalPath!!.cubicTo(23.356464f, 30.86367f, 23.27358f, 30.833399f, 23.213327f, 30.772852f)
generalPath!!.cubicTo(23.15337f, 30.712305f, 23.12339f, 30.628834f, 23.12339f, 30.522436f)
generalPath!!.cubicTo(23.12339f, 30.415747f, 23.15337f, 30.332129f, 23.213327f, 30.271582f)
generalPath!!.cubicTo(23.27358f, 30.211037f, 23.356464f, 30.180763f, 23.461979f, 30.180761f)
generalPath!!.moveTo(23.92842f, 30.192665f)
generalPath!!.lineTo(24.117994f, 30.192665f)
generalPath!!.lineTo(24.357388f, 30.644117f)
generalPath!!.lineTo(24.357388f, 30.192665f)
generalPath!!.lineTo(24.518305f, 30.192665f)
generalPath!!.lineTo(24.518305f, 30.850885f)
generalPath!!.lineTo(24.32873f, 30.850885f)
generalPath!!.lineTo(24.089338f, 30.399433f)
generalPath!!.lineTo(24.089338f, 30.850885f)
generalPath!!.lineTo(23.92842f, 30.850885f)
generalPath!!.lineTo(23.92842f, 30.192665f)
generalPath!!.moveTo(24.59149f, 30.192665f)
generalPath!!.lineTo(24.777096f, 30.192665f)
generalPath!!.lineTo(24.92699f, 30.42721f)
generalPath!!.lineTo(25.076887f, 30.192665f)
generalPath!!.lineTo(25.262936f, 30.192665f)
generalPath!!.lineTo(25.01208f, 30.573578f)
generalPath!!.lineTo(25.01208f, 30.850885f)
generalPath!!.lineTo(24.842344f, 30.850885f)
generalPath!!.lineTo(24.842344f, 30.573578f)
generalPath!!.lineTo(24.59149f, 30.192665f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(74, 74, 74, 255))
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
            return 4.079588413238525
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 4.070621967315674
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 40.660064697265625
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 42.21737289428711
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
            return video_display(
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
                    return video_display(getOrigWidth().toInt(), getOrigHeight().toInt())
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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

