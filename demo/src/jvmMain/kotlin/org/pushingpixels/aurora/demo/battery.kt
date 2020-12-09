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
class battery private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
1.0587519407272339f, 0.0f, 0.0f, -1.1159440279006958f,
0.0f, 1.151258945465088f, 0.0f, -10.276909828186035f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(41.0f, 40.0f)
generalPath!!.cubicTo(41.027473f, 43.071407f, 37.766083f, 45.9154f, 32.45078f, 47.455086f)
generalPath!!.cubicTo(27.135475f, 48.994766f, 20.578812f, 48.994766f, 15.263508f, 47.455086f)
generalPath!!.cubicTo(9.948204f, 45.9154f, 6.6868153f, 43.071407f, 6.714287f, 40.0f)
generalPath!!.cubicTo(6.6868153f, 36.928593f, 9.948204f, 34.0846f, 15.263508f, 32.544914f)
generalPath!!.cubicTo(20.578812f, 31.005232f, 27.135475f, 31.005232f, 32.45078f, 32.544914f)
generalPath!!.cubicTo(37.766083f, 34.0846f, 41.027473f, 36.928593f, 41.0f, 40.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = 23.857143f, centerY = 40.0f, radius = 17.142857f, tileMode = TileMode.Clamp)
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
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.937498f, 7.191965f)
generalPath!!.cubicTo(16.078072f, 7.191965f, 9.6875f, 9.994466f, 9.687499f, 13.441966f)
generalPath!!.cubicTo(9.687499f, 13.689351f, 9.687499f, 36.309326f, 9.687499f, 36.566967f)
generalPath!!.cubicTo(9.687499f, 40.014465f, 16.078072f, 42.816967f, 23.937498f, 42.816967f)
generalPath!!.cubicTo(31.796926f, 42.816967f, 38.441963f, 40.014465f, 38.441963f, 36.566967f)
generalPath!!.cubicTo(38.441963f, 36.362904f, 38.441963f, 13.646027f, 38.441963f, 13.441966f)
generalPath!!.cubicTo(38.441963f, 9.994465f, 31.796926f, 7.191965f, 23.937498f, 7.191965f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(126, 128, 122, 255), 0.25f to Color(186, 187, 184, 255), 0.5f to Color(165, 166, 163, 255), 1.0f to Color(51, 52, 50, 255), startX = 9.303052f, startY = 25.004467f, endX = 38.826412f, endY = 25.004467f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = LinearGradient(0.0f to Color(125, 75, 1, 255), 1.0f to Color(83, 83, 83, 255), startX = 25.06473f, startY = 20.14286f, endX = 25.207586f, endY = 23.714287f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.937498f, 7.191965f)
generalPath!!.cubicTo(16.078072f, 7.191965f, 9.6875f, 9.994466f, 9.687499f, 13.441966f)
generalPath!!.cubicTo(9.687499f, 13.689351f, 9.687499f, 36.309326f, 9.687499f, 36.566967f)
generalPath!!.cubicTo(9.687499f, 40.014465f, 16.078072f, 42.816967f, 23.937498f, 42.816967f)
generalPath!!.cubicTo(31.796926f, 42.816967f, 38.441963f, 40.014465f, 38.441963f, 36.566967f)
generalPath!!.cubicTo(38.441963f, 36.362904f, 38.441963f, 13.646027f, 38.441963f, 13.441966f)
generalPath!!.cubicTo(38.441963f, 9.994465f, 31.796926f, 7.191965f, 23.937498f, 7.191965f)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.941292f, 7.4484873f)
generalPath!!.cubicTo(16.316147f, 7.4484873f, 10.116071f, 10.16745f, 10.11607f, 13.512182f)
generalPath!!.cubicTo(10.11607f, 13.752193f, 10.11607f, 19.820066f, 10.11607f, 20.070026f)
generalPath!!.cubicTo(10.11607f, 23.41476f, 16.316147f, 25.717924f, 23.941292f, 25.717924f)
generalPath!!.cubicTo(31.566439f, 25.717924f, 38.013393f, 23.41476f, 38.013393f, 20.070026f)
generalPath!!.cubicTo(38.013393f, 19.872047f, 38.013393f, 13.71016f, 38.013393f, 13.512182f)
generalPath!!.cubicTo(38.013393f, 10.167449f, 31.566437f, 7.4484873f, 23.941292f, 7.4484873f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(247, 148, 3, 255), 0.18691589f to Color(253, 179, 67, 255), 0.43008122f to Color(253, 183, 79, 255), 1.0f to Color(143, 86, 1, 255), startX = 10.11607f, startY = 15.797492f, endX = 38.013393f, endY = 15.797492f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5443038f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 17.160093f, startY = 12.290198f, endX = 37.9694f, endY = 54.86163f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.946121f, 7.971286f)
generalPath!!.cubicTo(16.618364f, 7.971286f, 10.660096f, 10.584206f, 10.660095f, 13.798491f)
generalPath!!.cubicTo(10.660095f, 14.029142f, 10.660095f, 35.68455f, 10.660095f, 35.924763f)
generalPath!!.cubicTo(10.660095f, 39.13905f, 16.618362f, 41.75197f, 23.946121f, 41.75197f)
generalPath!!.cubicTo(31.27388f, 41.75197f, 37.4694f, 39.13905f, 37.4694f, 35.924763f)
generalPath!!.cubicTo(37.4694f, 35.734505f, 37.4694f, 13.988749f, 37.4694f, 13.798491f)
generalPath!!.cubicTo(37.4694f, 10.584205f, 31.273878f, 7.971286f, 23.946121f, 7.971286f)
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
1.0372910499572754f, 0.0f, 0.0f, -0.8949790000915527f,
0.0f, 0.9546189904212952f, 0.0f, -0.07572056353092194f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(36.857143f, 14.071428f)
generalPath!!.cubicTo(36.877747f, 16.042248f, 34.431705f, 17.867146f, 30.445229f, 18.855108f)
generalPath!!.cubicTo(26.45875f, 19.843071f, 21.54125f, 19.843071f, 17.554773f, 18.855108f)
generalPath!!.cubicTo(13.5682955f, 17.867146f, 11.122253f, 16.042248f, 11.142858f, 14.071428f)
generalPath!!.cubicTo(11.122253f, 12.100608f, 13.5682955f, 10.275712f, 17.554773f, 9.287748f)
generalPath!!.cubicTo(21.54125f, 8.299786f, 26.45875f, 8.299786f, 30.445229f, 9.287748f)
generalPath!!.cubicTo(34.431705f, 10.275712f, 36.877747f, 12.100608f, 36.857143f, 14.071428f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(254, 212, 150, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(231, 230, 174, 255), startX = 24.0f, startY = 16.525082f, endX = 24.0f, endY = 13.284962f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.004927f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(36.857143f, 14.071428f)
generalPath!!.cubicTo(36.877747f, 16.042248f, 34.431705f, 17.867146f, 30.445229f, 18.855108f)
generalPath!!.cubicTo(26.45875f, 19.843071f, 21.54125f, 19.843071f, 17.554773f, 18.855108f)
generalPath!!.cubicTo(13.5682955f, 17.867146f, 11.122253f, 16.042248f, 11.142858f, 14.071428f)
generalPath!!.cubicTo(11.122253f, 12.100608f, 13.5682955f, 10.275712f, 17.554773f, 9.287748f)
generalPath!!.cubicTo(21.54125f, 8.299786f, 26.45875f, 8.299786f, 30.445229f, 9.287748f)
generalPath!!.cubicTo(34.431705f, 10.275712f, 36.877747f, 12.100608f, 36.857143f, 14.071428f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.65822786f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.363429993391037f, 0.0f, 0.0f, 15.4724702835083f,
0.0f, 0.363429993391037f, 0.0f, -4.118577003479004f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(41.0f, 40.0f)
generalPath!!.cubicTo(41.027473f, 43.071407f, 37.766083f, 45.9154f, 32.45078f, 47.455086f)
generalPath!!.cubicTo(27.135475f, 48.994766f, 20.578812f, 48.994766f, 15.263508f, 47.455086f)
generalPath!!.cubicTo(9.948204f, 45.9154f, 6.6868153f, 43.071407f, 6.714287f, 40.0f)
generalPath!!.cubicTo(6.6868153f, 36.928593f, 9.948204f, 34.0846f, 15.263508f, 32.544914f)
generalPath!!.cubicTo(20.578812f, 31.005232f, 27.135475f, 31.005232f, 32.45078f, 32.544914f)
generalPath!!.cubicTo(37.766083f, 34.0846f, 41.027473f, 36.928593f, 41.0f, 40.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = 23.857143f, centerY = 40.0f, radius = 17.142857f, tileMode = TileMode.Clamp)
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
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.889442f, 6.465348f)
generalPath!!.cubicTo(21.886648f, 6.465348f, 19.25816f, 7.179501f, 19.25816f, 8.058017f)
generalPath!!.cubicTo(19.25816f, 8.1210575f, 19.25816f, 10.622022f, 19.25816f, 10.687676f)
generalPath!!.cubicTo(19.25816f, 11.566192f, 21.886648f, 12.280345f, 23.889442f, 12.280345f)
generalPath!!.cubicTo(25.892239f, 12.280345f, 28.299858f, 11.566192f, 28.299858f, 10.687676f)
generalPath!!.cubicTo(28.299858f, 10.635676f, 28.299858f, 8.110017f, 28.299858f, 8.058017f)
generalPath!!.cubicTo(28.299858f, 7.1795f, 25.892239f, 6.465348f, 23.889442f, 6.465348f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(252, 175, 62, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(180, 108, 2, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.889442f, 6.465348f)
generalPath!!.cubicTo(21.886648f, 6.465348f, 19.25816f, 7.179501f, 19.25816f, 8.058017f)
generalPath!!.cubicTo(19.25816f, 8.1210575f, 19.25816f, 10.622022f, 19.25816f, 10.687676f)
generalPath!!.cubicTo(19.25816f, 11.566192f, 21.886648f, 12.280345f, 23.889442f, 12.280345f)
generalPath!!.cubicTo(25.892239f, 12.280345f, 28.299858f, 11.566192f, 28.299858f, 10.687676f)
generalPath!!.cubicTo(28.299858f, 10.635676f, 28.299858f, 8.110017f, 28.299858f, 8.058017f)
generalPath!!.cubicTo(28.299858f, 7.1795f, 25.892239f, 6.465348f, 23.889442f, 6.465348f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.49367085f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(13.642858f, 17.999998f)
generalPath!!.lineTo(14.0f, 34.785713f)
generalPath!!.lineTo(18.571428f, 36.071426f)
generalPath!!.lineTo(18.428572f, 19.357141f)
generalPath!!.cubicTo(18.428572f, 19.357141f, 20.5f, 19.357141f, 23.857143f, 19.142857f)
generalPath!!.cubicTo(18.6414f, 18.909782f, 12.67377f, 16.668177f, 10.642858f, 15.214285f)
generalPath!!.cubicTo(12.059757f, 17.306461f, 13.642858f, 17.999998f, 13.642858f, 17.999998f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 15.928574f, startY = 20.428572f, endX = 15.6428585f, endY = 30.928572f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.2719790041446686f, 0.0f, 0.0f, 17.270479202270508f,
0.0f, 0.21661899983882904f, 0.0f, 5.4097418785095215f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(36.857143f, 14.071428f)
generalPath!!.cubicTo(36.877747f, 16.042248f, 34.431705f, 17.867146f, 30.445229f, 18.855108f)
generalPath!!.cubicTo(26.45875f, 19.843071f, 21.54125f, 19.843071f, 17.554773f, 18.855108f)
generalPath!!.cubicTo(13.5682955f, 17.867146f, 11.122253f, 16.042248f, 11.142858f, 14.071428f)
generalPath!!.cubicTo(11.122253f, 12.100608f, 13.5682955f, 10.275712f, 17.554773f, 9.287748f)
generalPath!!.cubicTo(21.54125f, 8.299786f, 26.45875f, 8.299786f, 30.445229f, 9.287748f)
generalPath!!.cubicTo(34.431705f, 10.275712f, 36.877747f, 12.100608f, 36.857143f, 14.071428f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(254, 212, 150, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(231, 230, 174, 255), startX = 24.0f, startY = 16.525082f, endX = 24.0f, endY = 13.284962f, tileMode = TileMode.Clamp)
stroke = Stroke(width=4.1198664f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=10.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(36.857143f, 14.071428f)
generalPath!!.cubicTo(36.877747f, 16.042248f, 34.431705f, 17.867146f, 30.445229f, 18.855108f)
generalPath!!.cubicTo(26.45875f, 19.843071f, 21.54125f, 19.843071f, 17.554773f, 18.855108f)
generalPath!!.cubicTo(13.5682955f, 17.867146f, 11.122253f, 16.042248f, 11.142858f, 14.071428f)
generalPath!!.cubicTo(11.122253f, 12.100608f, 13.5682955f, 10.275712f, 17.554773f, 9.287748f)
generalPath!!.cubicTo(21.54125f, 8.299786f, 26.45875f, 8.299786f, 30.445229f, 9.287748f)
generalPath!!.cubicTo(34.431705f, 10.275712f, 36.877747f, 12.100608f, 36.857143f, 14.071428f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
// _0_1
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
            return 5.9637346267700195
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 5.965347766876221
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 36.35824203491211
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 40.16340637207031
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
            return battery(
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
                    return battery(getOrigWidth().toInt(), getOrigHeight().toInt())
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

