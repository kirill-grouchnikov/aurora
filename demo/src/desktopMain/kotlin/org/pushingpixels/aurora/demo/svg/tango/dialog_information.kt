package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
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
class dialog_information private constructor(var _width: Dp, var _height: Dp) : AuroraIcon {
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
alpha *= 0.8f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1971830129623413f, 0.0f, 0.0f, -6.201581954956055f,
0.0f, 1.0985909700393677f, 0.0f, -3.2095069885253906f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(39.875f, 42.0625f)
generalPath!!.cubicTo(39.897133f, 43.652596f, 37.26934f, 45.124954f, 32.986645f, 45.92206f)
generalPath!!.cubicTo(28.703949f, 46.719166f, 23.421051f, 46.719166f, 19.138357f, 45.92206f)
generalPath!!.cubicTo(14.855661f, 45.124954f, 12.227865f, 43.652596f, 12.25f, 42.0625f)
generalPath!!.cubicTo(12.227865f, 40.472404f, 14.855661f, 39.000046f, 19.138357f, 38.20294f)
generalPath!!.cubicTo(23.421051f, 37.405834f, 28.703949f, 37.405834f, 32.986645f, 38.20294f)
generalPath!!.cubicTo(37.26934f, 39.000046f, 39.897133f, 40.472404f, 39.875f, 42.0625f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 131), 0.55172414f to Color(0, 0, 0, 37), 1.0f to Color(0, 0, 0, 0), center = Offset(26.062506f, 42.06249f), radius = 13.812502f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 1.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0758229494094849f, 0.0f, 0.0f, -2.551335096359253f,
0.0f, 0.9374930262565613f, 0.0f, 3.047213077545166f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(21.893503f, 38.885944f)
generalPath!!.lineTo(21.893503f, 40.36116f)
generalPath!!.cubicTo(21.893503f, 41.836376f, 23.204807f, 43.14768f, 24.680021f, 43.14768f)
generalPath!!.cubicTo(26.155237f, 43.14768f, 27.46654f, 41.836376f, 27.46654f, 40.36116f)
generalPath!!.lineTo(27.46654f, 38.885944f)
generalPath!!.lineTo(21.893503f, 38.885944f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005618f to Color(104, 104, 104, 255), 0.03012137f to Color(119, 119, 119, 255), 0.08366583f to Color(146, 146, 146, 255), 0.1422f to Color(167, 167, 167, 255), 0.2074f to Color(182, 182, 182, 255), 0.2846f to Color(190, 190, 190, 255), 0.4045f to Color(193, 193, 193, 255), 0.4962f to Color(188, 188, 188, 255), 0.6057f to Color(173, 173, 173, 255), 0.7245f to Color(149, 149, 149, 255), 0.8497f to Color(116, 116, 116, 255), 0.9789f to Color(73, 73, 73, 255), 1.0f to Color(65, 65, 65, 255), start = Offset(-0.0229114f, -0.042768143f), end = Offset(-0.025412137f, -0.041426625f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(86, 86, 86, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(21.893503f, 38.885944f)
generalPath!!.lineTo(21.893503f, 40.36116f)
generalPath!!.cubicTo(21.893503f, 41.836376f, 23.204807f, 43.14768f, 24.680021f, 43.14768f)
generalPath!!.cubicTo(26.155237f, 43.14768f, 27.46654f, 41.836376f, 27.46654f, 40.36116f)
generalPath!!.lineTo(27.46654f, 38.885944f)
generalPath!!.lineTo(21.893503f, 38.885944f)
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
0.9890729784965515f, 0.0f, 0.0f, -0.40873900055885315f,
0.0f, 0.993556022644043f, 0.0f, 0.007920479401946068f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1
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
// _0_0_1_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.511724f, 27.668867f)
generalPath!!.cubicTo(21.208843f, 27.660896f, 17.463276f, 28.632053f, 19.492912f, 30.467932f)
generalPath!!.cubicTo(18.98969f, 30.670935f, 18.27037f, 31.124313f, 18.355167f, 32.185223f)
generalPath!!.cubicTo(18.401983f, 32.739285f, 18.989243f, 33.079395f, 19.79236f, 33.32911f)
generalPath!!.cubicTo(18.881908f, 33.967724f, 18.302582f, 34.642555f, 18.355167f, 35.26492f)
generalPath!!.cubicTo(18.401438f, 35.812527f, 18.976334f, 36.18753f, 19.76303f, 36.43814f)
generalPath!!.cubicTo(18.875519f, 37.069405f, 18.3033f, 37.76012f, 18.355167f, 38.37395f)
generalPath!!.cubicTo(18.434437f, 39.312088f, 20.457743f, 40.362926f, 24.838928f, 40.2419f)
generalPath!!.cubicTo(27.993328f, 40.155914f, 30.776913f, 39.590515f, 30.9966f, 38.37395f)
generalPath!!.cubicTo(31.082863f, 37.896248f, 30.691908f, 37.45053f, 30.087355f, 37.05408f)
generalPath!!.cubicTo(30.539927f, 36.59792f, 30.85698f, 36.135242f, 30.820616f, 35.70488f)
generalPath!!.cubicTo(30.774128f, 35.154694f, 30.205994f, 34.78192f, 29.412754f, 34.53166f)
generalPath!!.cubicTo(30.300264f, 33.9004f, 30.872482f, 33.20968f, 30.820616f, 32.595848f)
generalPath!!.cubicTo(30.774128f, 32.045666f, 30.205994f, 31.702225f, 29.412754f, 31.45196f)
generalPath!!.cubicTo(30.310848f, 30.817287f, 30.872816f, 30.133928f, 30.820616f, 29.51615f)
generalPath!!.cubicTo(30.762592f, 28.829447f, 27.61599f, 27.676357f, 24.511724f, 27.668867f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(174, 174, 87, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(76, 76, 40, 255), 1.0f to Color(76, 76, 40, 0), start = Offset(24.6784f, 31.063726f), end = Offset(24.6784f, 26.668818f), tileMode = TileMode.Clamp)
stroke = Stroke(width=2.0175292f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.511724f, 27.668867f)
generalPath!!.cubicTo(21.208843f, 27.660896f, 17.463276f, 28.632053f, 19.492912f, 30.467932f)
generalPath!!.cubicTo(18.98969f, 30.670935f, 18.27037f, 31.124313f, 18.355167f, 32.185223f)
generalPath!!.cubicTo(18.401983f, 32.739285f, 18.989243f, 33.079395f, 19.79236f, 33.32911f)
generalPath!!.cubicTo(18.881908f, 33.967724f, 18.302582f, 34.642555f, 18.355167f, 35.26492f)
generalPath!!.cubicTo(18.401438f, 35.812527f, 18.976334f, 36.18753f, 19.76303f, 36.43814f)
generalPath!!.cubicTo(18.875519f, 37.069405f, 18.3033f, 37.76012f, 18.355167f, 38.37395f)
generalPath!!.cubicTo(18.434437f, 39.312088f, 20.457743f, 40.362926f, 24.838928f, 40.2419f)
generalPath!!.cubicTo(27.993328f, 40.155914f, 30.776913f, 39.590515f, 30.9966f, 38.37395f)
generalPath!!.cubicTo(31.082863f, 37.896248f, 30.691908f, 37.45053f, 30.087355f, 37.05408f)
generalPath!!.cubicTo(30.539927f, 36.59792f, 30.85698f, 36.135242f, 30.820616f, 35.70488f)
generalPath!!.cubicTo(30.774128f, 35.154694f, 30.205994f, 34.78192f, 29.412754f, 34.53166f)
generalPath!!.cubicTo(30.300264f, 33.9004f, 30.872482f, 33.20968f, 30.820616f, 32.595848f)
generalPath!!.cubicTo(30.774128f, 32.045666f, 30.205994f, 31.702225f, 29.412754f, 31.45196f)
generalPath!!.cubicTo(30.310848f, 30.817287f, 30.872816f, 30.133928f, 30.820616f, 29.51615f)
generalPath!!.cubicTo(30.762592f, 28.829447f, 27.61599f, 27.676357f, 24.511724f, 27.668867f)
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
// _0_0_1_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.920208f, 38.329765f)
generalPath!!.cubicTo(30.700521f, 39.54633f, 27.591421f, 40.23286f, 22.615131f, 39.983673f)
generalPath!!.cubicTo(19.463507f, 39.825855f, 19.283163f, 38.944054f, 19.502848f, 37.72749f)
generalPath!!.cubicTo(19.722534f, 36.510925f, 22.458319f, 35.65848f, 25.609509f, 35.824707f)
generalPath!!.cubicTo(28.7607f, 35.990936f, 31.139893f, 37.1132f, 30.920208f, 38.329765f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005618f to Color(163, 163, 73, 255), 0.02078677f to Color(172, 172, 84, 255), 0.06600059f to Color(193, 193, 114, 255), 0.1148f to Color(212, 214, 142, 255), 0.1677f to Color(226, 228, 166, 255), 0.2265f to Color(237, 240, 184, 255), 0.2963f to Color(243, 246, 195, 255), 0.4045f to Color(245, 248, 199, 255), 0.5239f to Color(238, 240, 190, 255), 0.6666f to Color(219, 221, 169, 255), 0.8211f to Color(190, 189, 136, 255), 0.9832f to Color(152, 149, 100, 255), 1.0f to Color(148, 145, 96, 255), start = Offset(0.23620462f, -0.5623359f), end = Offset(-0.0021522515f, 0.041872725f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(146, 148, 112, 255), 0.2647059f to Color(252, 255, 193, 255), 0.63235295f to Color(243, 245, 186, 255), 1.0f to Color(146, 148, 112, 255), start = Offset(-0.011763208f, 0.06623628f), end = Offset(0.16250998f, -0.3755338f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.08906282f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.920208f, 38.329765f)
generalPath!!.cubicTo(30.700521f, 39.54633f, 27.591421f, 40.23286f, 22.615131f, 39.983673f)
generalPath!!.cubicTo(19.463507f, 39.825855f, 19.283163f, 38.944054f, 19.502848f, 37.72749f)
generalPath!!.cubicTo(19.722534f, 36.510925f, 22.458319f, 35.65848f, 25.609509f, 35.824707f)
generalPath!!.cubicTo(28.7607f, 35.990936f, 31.139893f, 37.1132f, 30.920208f, 38.329765f)
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
0.6027399897575378f, 0.06428372114896774f, 0.0f, 31.120210647583008f,
-0.12862500548362732f, 0.7607880234718323f, 0.0f, 14.491410255432129f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-3.535534f, 27.228739f)
generalPath!!.cubicTo(-3.5189617f, 28.400614f, -5.486396f, 29.48572f, -8.692855f, 30.073174f)
generalPath!!.cubicTo(-11.899315f, 30.66063f, -15.854626f, 30.66063f, -19.061085f, 30.073174f)
generalPath!!.cubicTo(-22.267544f, 29.48572f, -24.23498f, 28.400614f, -24.218407f, 27.228739f)
generalPath!!.cubicTo(-24.23498f, 26.056864f, -22.267544f, 24.971758f, -19.061085f, 24.384304f)
generalPath!!.cubicTo(-15.854626f, 23.796848f, -11.899315f, 23.796848f, -8.692855f, 24.384304f)
generalPath!!.cubicTo(-5.486396f, 24.971758f, -3.5189617f, 26.056864f, -3.535534f, 27.228739f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005618f to Color(163, 163, 73, 255), 0.02078677f to Color(172, 172, 84, 255), 0.06600059f to Color(193, 193, 114, 255), 0.1148f to Color(212, 214, 142, 255), 0.1677f to Color(226, 228, 166, 255), 0.2265f to Color(237, 240, 184, 255), 0.2963f to Color(243, 246, 195, 255), 0.4045f to Color(245, 248, 199, 255), 0.5239f to Color(238, 240, 190, 255), 0.6666f to Color(219, 221, 169, 255), 0.8211f to Color(190, 189, 136, 255), 0.9832f to Color(152, 149, 100, 255), 1.0f to Color(148, 145, 96, 255), start = Offset(-23.479193f, 37.67939f), end = Offset(-4.506969f, 37.67939f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(146, 148, 112, 255), 0.2647059f to Color(252, 255, 193, 255), 0.63235295f to Color(243, 245, 186, 255), 1.0f to Color(146, 148, 112, 255), start = Offset(-10.758083f, 38.028126f), end = Offset(-24.48226f, 38.134212f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.13035245f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-3.535534f, 27.228739f)
generalPath!!.cubicTo(-3.5189617f, 28.400614f, -5.486396f, 29.48572f, -8.692855f, 30.073174f)
generalPath!!.cubicTo(-11.899315f, 30.66063f, -15.854626f, 30.66063f, -19.061085f, 30.073174f)
generalPath!!.cubicTo(-22.267544f, 29.48572f, -24.23498f, 28.400614f, -24.218407f, 27.228739f)
generalPath!!.cubicTo(-24.23498f, 26.056864f, -22.267544f, 24.971758f, -19.061085f, 24.384304f)
generalPath!!.cubicTo(-15.854626f, 23.796848f, -11.899315f, 23.796848f, -8.692855f, 24.384304f)
generalPath!!.cubicTo(-5.486396f, 24.971758f, -3.5189617f, 26.056864f, -3.535534f, 27.228739f)
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
0.6027399897575378f, 0.06428372114896774f, 0.0f, 31.120210647583008f,
-0.12862500548362732f, 0.7607880234718323f, 0.0f, 11.395910263061523f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-3.535534f, 27.228739f)
generalPath!!.cubicTo(-3.5189617f, 28.400614f, -5.486396f, 29.48572f, -8.692855f, 30.073174f)
generalPath!!.cubicTo(-11.899315f, 30.66063f, -15.854626f, 30.66063f, -19.061085f, 30.073174f)
generalPath!!.cubicTo(-22.267544f, 29.48572f, -24.23498f, 28.400614f, -24.218407f, 27.228739f)
generalPath!!.cubicTo(-24.23498f, 26.056864f, -22.267544f, 24.971758f, -19.061085f, 24.384304f)
generalPath!!.cubicTo(-15.854626f, 23.796848f, -11.899315f, 23.796848f, -8.692855f, 24.384304f)
generalPath!!.cubicTo(-5.486396f, 24.971758f, -3.5189617f, 26.056864f, -3.535534f, 27.228739f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005618f to Color(163, 163, 73, 255), 0.02078677f to Color(172, 172, 84, 255), 0.06600059f to Color(193, 193, 114, 255), 0.1148f to Color(212, 214, 142, 255), 0.1677f to Color(226, 228, 166, 255), 0.2265f to Color(237, 240, 184, 255), 0.2963f to Color(243, 246, 195, 255), 0.4045f to Color(245, 248, 199, 255), 0.5239f to Color(238, 240, 190, 255), 0.6666f to Color(219, 221, 169, 255), 0.8211f to Color(190, 189, 136, 255), 0.9832f to Color(152, 149, 100, 255), 1.0f to Color(148, 145, 96, 255), start = Offset(-23.479193f, 37.67939f), end = Offset(-4.506969f, 37.67939f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(146, 148, 112, 255), 0.2647059f to Color(252, 255, 193, 255), 0.63235295f to Color(243, 245, 186, 255), 1.0f to Color(146, 148, 112, 255), start = Offset(-10.758083f, 38.028126f), end = Offset(-24.48226f, 38.134212f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.13035245f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(-3.535534f, 27.228739f)
generalPath!!.cubicTo(-3.5189617f, 28.400614f, -5.486396f, 29.48572f, -8.692855f, 30.073174f)
generalPath!!.cubicTo(-11.899315f, 30.66063f, -15.854626f, 30.66063f, -19.061085f, 30.073174f)
generalPath!!.cubicTo(-22.267544f, 29.48572f, -24.23498f, 28.400614f, -24.218407f, 27.228739f)
generalPath!!.cubicTo(-24.23498f, 26.056864f, -22.267544f, 24.971758f, -19.061085f, 24.384304f)
generalPath!!.cubicTo(-15.854626f, 23.796848f, -11.899315f, 23.796848f, -8.692855f, 24.384304f)
generalPath!!.cubicTo(-5.486396f, 24.971758f, -3.5189617f, 26.056864f, -3.535534f, 27.228739f)
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
// _0_0_1_1_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.698088f, 29.636387f)
generalPath!!.cubicTo(30.698088f, 31.014688f, 28.157326f, 32.55444f, 24.7166f, 33.288692f)
generalPath!!.cubicTo(21.275875f, 34.022945f, 18.38922f, 33.50421f, 18.273172f, 32.130802f)
generalPath!!.cubicTo(18.157124f, 30.757395f, 20.50968f, 29.155466f, 23.952389f, 28.968826f)
generalPath!!.cubicTo(27.422379f, 28.78071f, 30.698088f, 28.9249f, 30.698088f, 29.636387f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005618f to Color(163, 163, 73, 255), 0.02078677f to Color(172, 172, 84, 255), 0.06600059f to Color(193, 193, 114, 255), 0.1148f to Color(212, 214, 142, 255), 0.1677f to Color(226, 228, 166, 255), 0.2265f to Color(237, 240, 184, 255), 0.2963f to Color(243, 246, 195, 255), 0.4045f to Color(245, 248, 199, 255), 0.5239f to Color(238, 240, 190, 255), 0.6666f to Color(219, 221, 169, 255), 0.8211f to Color(190, 189, 136, 255), 0.9832f to Color(152, 149, 100, 255), 1.0f to Color(148, 145, 96, 255), start = Offset(0.04940227f, -0.06986124f), end = Offset(-0.04219807f, 0.15314804f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(146, 148, 112, 255), 0.2647059f to Color(252, 255, 193, 255), 0.63235295f to Color(243, 245, 186, 255), 1.0f to Color(146, 148, 112, 255), start = Offset(9.9561205f, -24.18906f), end = Offset(0.047853798f, -0.0660928f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.08906286f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(30.698088f, 29.636387f)
generalPath!!.cubicTo(30.698088f, 31.014688f, 28.157326f, 32.55444f, 24.7166f, 33.288692f)
generalPath!!.cubicTo(21.275875f, 34.022945f, 18.38922f, 33.50421f, 18.273172f, 32.130802f)
generalPath!!.cubicTo(18.157124f, 30.757395f, 20.50968f, 29.155466f, 23.952389f, 28.968826f)
generalPath!!.cubicTo(27.422379f, 28.78071f, 30.698088f, 28.9249f, 30.698088f, 29.636387f)
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
0.3354640007019043f, 0.0f, 0.0f, 11.746780395507812f,
0.0f, 0.3354640007019043f, 0.0f, 27.22610092163086f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(31.0f, 22.375f)
generalPath!!.cubicTo(31.005209f, 23.539576f, 30.386904f, 24.617924f, 29.37921f, 25.20172f)
generalPath!!.cubicTo(28.371517f, 25.785517f, 27.128483f, 25.785517f, 26.12079f, 25.20172f)
generalPath!!.cubicTo(25.113096f, 24.617924f, 24.494791f, 23.539576f, 24.5f, 22.375f)
generalPath!!.cubicTo(24.494791f, 21.210424f, 25.113096f, 20.132076f, 26.12079f, 19.54828f)
generalPath!!.cubicTo(27.128483f, 18.964483f, 28.371517f, 18.964483f, 29.37921f, 19.54828f)
generalPath!!.cubicTo(30.386904f, 20.132076f, 31.005209f, 21.210424f, 31.0f, 22.375f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.342182f, 33.378864f)
generalPath!!.cubicTo(22.736591f, 33.883533f, 26.320992f, 33.34619f, 29.214315f, 31.470806f)
generalPath!!.cubicTo(30.025581f, 30.944962f, 30.147604f, 30.343945f, 30.520922f, 29.873844f)
generalPath!!.cubicTo(29.09679f, 31.000706f, 25.494982f, 34.035625f, 19.342182f, 33.378864f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 60))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.3354640007019043f, 0.0f, 0.0f, 11.746780395507812f,
0.0f, 0.3354640007019043f, 0.0f, 30.233760833740234f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(31.0f, 22.375f)
generalPath!!.cubicTo(31.005209f, 23.539576f, 30.386904f, 24.617924f, 29.37921f, 25.20172f)
generalPath!!.cubicTo(28.371517f, 25.785517f, 27.128483f, 25.785517f, 26.12079f, 25.20172f)
generalPath!!.cubicTo(25.113096f, 24.617924f, 24.494791f, 23.539576f, 24.5f, 22.375f)
generalPath!!.cubicTo(24.494791f, 21.210424f, 25.113096f, 20.132076f, 26.12079f, 19.54828f)
generalPath!!.cubicTo(27.128483f, 18.964483f, 28.371517f, 18.964483f, 29.37921f, 19.54828f)
generalPath!!.cubicTo(30.386904f, 20.132076f, 31.005209f, 21.210424f, 31.0f, 22.375f)
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_1_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.466621f, 39.517838f)
generalPath!!.cubicTo(22.86103f, 40.022507f, 26.44543f, 39.485165f, 29.338753f, 37.60978f)
generalPath!!.cubicTo(30.15002f, 37.083935f, 30.272043f, 36.482918f, 30.645359f, 36.012817f)
generalPath!!.cubicTo(29.221228f, 37.13968f, 25.61942f, 40.1746f, 19.466621f, 39.517838f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 60))
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
// _0_0_1_1_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(19.487362f, 36.40687f)
generalPath!!.cubicTo(22.88177f, 36.91154f, 26.46617f, 36.3742f, 29.359491f, 34.498814f)
generalPath!!.cubicTo(30.17076f, 33.97297f, 30.292782f, 33.371952f, 30.6661f, 32.90185f)
generalPath!!.cubicTo(29.241968f, 34.028713f, 25.64016f, 37.063633f, 19.487362f, 36.40687f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 60))
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
1.0f, 0.0f, 0.0f, -0.9887970089912415f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2
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
// _0_0_1_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9544389843940735f, 0.0f, 0.0f, 1.4332220554351807f,
0.0f, 0.9898689985275269f, 0.0f, 0.6398810148239136f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(18.87103f, 29.628128f)
generalPath!!.cubicTo(18.87103f, 28.836695f, 20.445135f, 27.889988f, 24.419233f, 27.942972f)
generalPath!!.cubicTo(28.101154f, 27.99206f, 30.526608f, 28.83866f, 30.526608f, 30.105404f)
generalPath!!.cubicTo(30.526608f, 31.345282f, 27.307241f, 32.174416f, 23.874678f, 32.008186f)
generalPath!!.cubicTo(20.442114f, 31.84196f, 18.87103f, 30.868006f, 18.87103f, 29.628128f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(214, 215, 165, 255), 1.0f to Color(142, 143, 109, 255), start = Offset(2.149528f, 2.2804809f), end = Offset(1.9770123f, 3.1213932f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(146, 148, 112, 255), 0.2647059f to Color(96, 97, 74, 255), 0.63235295f to Color(243, 245, 186, 255), 1.0f to Color(146, 148, 112, 255), start = Offset(-1.5719136f, -1.3230133f), end = Offset(-1.5493056f, -0.9360691f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.09083303f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(18.87103f, 29.628128f)
generalPath!!.cubicTo(18.87103f, 28.836695f, 20.445135f, 27.889988f, 24.419233f, 27.942972f)
generalPath!!.cubicTo(28.101154f, 27.99206f, 30.526608f, 28.83866f, 30.526608f, 30.105404f)
generalPath!!.cubicTo(30.526608f, 31.345282f, 27.307241f, 32.174416f, 23.874678f, 32.008186f)
generalPath!!.cubicTo(20.442114f, 31.84196f, 18.87103f, 30.868006f, 18.87103f, 29.628128f)
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
0.9544389843940735f, 0.0f, 0.0f, 1.4332220554351807f,
0.0f, 0.9898689985275269f, 0.0f, 0.6398810148239136f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.680021f, 0.8622936f)
generalPath!!.cubicTo(16.858006f, 0.8622936f, 10.506261f, 6.837263f, 10.506261f, 14.195288f)
generalPath!!.cubicTo(10.506261f, 21.73785f, 16.247826f, 22.573217f, 16.247826f, 25.352995f)
generalPath!!.cubicTo(16.247826f, 28.61906f, 19.614103f, 32.322685f, 25.14931f, 32.188995f)
generalPath!!.cubicTo(31.035158f, 32.046837f, 33.464184f, 28.825655f, 33.464184f, 25.352995f)
generalPath!!.cubicTo(33.464184f, 22.384064f, 38.853783f, 22.30489f, 38.853783f, 14.195288f)
generalPath!!.cubicTo(38.853783f, 6.837263f, 32.502037f, 0.8622936f, 24.680021f, 0.8622936f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 45), 0.882f to Color(112, 154, 200, 255), 1.0f to Color(111, 150, 221, 255), center = Offset(-0.061722342f, -0.037596628f), radius = 0.014277713f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(97, 100, 113, 255))
stroke = Stroke(width=1.0159545f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.680021f, 0.8622936f)
generalPath!!.cubicTo(16.858006f, 0.8622936f, 10.506261f, 6.837263f, 10.506261f, 14.195288f)
generalPath!!.cubicTo(10.506261f, 21.73785f, 16.247826f, 22.573217f, 16.247826f, 25.352995f)
generalPath!!.cubicTo(16.247826f, 28.61906f, 19.614103f, 32.322685f, 25.14931f, 32.188995f)
generalPath!!.cubicTo(31.035158f, 32.046837f, 33.464184f, 28.825655f, 33.464184f, 25.352995f)
generalPath!!.cubicTo(33.464184f, 22.384064f, 38.853783f, 22.30489f, 38.853783f, 14.195288f)
generalPath!!.cubicTo(38.853783f, 6.837263f, 32.502037f, 0.8622936f, 24.680021f, 0.8622936f)
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
0.9544389843940735f, 0.0f, 0.0f, 1.4332220554351807f,
0.0f, 0.9898689985275269f, 0.0f, 0.6398810148239136f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2_0_2
brush = Brush.linearGradient(0.0f to Color(241, 243, 255, 255), 1.0f to Color(241, 243, 255, 0), start = Offset(0.5757097f, 0.27842075f), end = Offset(0.683804f, -0.55319047f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.946857f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(24.680021f, 1.9277146f)
generalPath!!.cubicTo(17.39f, 1.9277146f, 11.470252f, 7.4963126f, 11.470252f, 14.353901f)
generalPath!!.cubicTo(11.470252f, 21.383476f, 16.82132f, 22.162027f, 16.82132f, 24.752747f)
generalPath!!.cubicTo(16.82132f, 27.79668f, 19.958649f, 31.248413f, 25.117392f, 31.123814f)
generalPath!!.cubicTo(30.60293f, 30.991322f, 32.866753f, 27.989222f, 32.866753f, 24.752747f)
generalPath!!.cubicTo(32.866753f, 21.98574f, 37.88979f, 21.911947f, 37.88979f, 14.353901f)
generalPath!!.cubicTo(37.88979f, 7.4963126f, 31.970043f, 1.9277146f, 24.680021f, 1.9277146f)
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
0.9375f, 0.0f, 0.0f, 0.569221019744873f,
0.0f, 0.926937997341156f, 0.0f, 0.25176000595092773f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2_1
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
// _0_0_1_2_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(31.947292f, 19.22274f)
generalPath!!.cubicTo(32.260033f, 19.326988f, 32.46853f, 19.63973f, 32.36428f, 19.95247f)
generalPath!!.lineTo(28.507133f, 31.523912f)
generalPath!!.cubicTo(28.402887f, 31.836655f, 28.090145f, 32.04515f, 27.777403f, 31.940903f)
generalPath!!.cubicTo(27.464663f, 31.836655f, 27.256168f, 31.523912f, 27.360415f, 31.211172f)
generalPath!!.lineTo(31.217562f, 19.63973f)
generalPath!!.cubicTo(31.32181f, 19.326988f, 31.63455f, 19.118492f, 31.947292f, 19.22274f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.4117647f to Color(255, 255, 255, 113), 1.0f to Color(0, 0, 0, 123), start = Offset(-0.012101424f, 0.010292555f), end = Offset(-0.011903377f, 0.016972383f), tileMode = TileMode.Clamp)
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
// _0_0_1_2_1_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(20.152405f, 19.34774f)
generalPath!!.cubicTo(19.839663f, 19.451988f, 19.631166f, 19.76473f, 19.735415f, 20.07747f)
generalPath!!.lineTo(23.592562f, 31.648912f)
generalPath!!.cubicTo(23.69681f, 31.961655f, 24.00955f, 32.17015f, 24.322292f, 32.065903f)
generalPath!!.cubicTo(24.635035f, 31.961655f, 24.843529f, 31.648912f, 24.73928f, 31.336172f)
generalPath!!.lineTo(20.882133f, 19.76473f)
generalPath!!.cubicTo(20.777887f, 19.451988f, 20.465145f, 19.243492f, 20.152405f, 19.34774f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.4117647f to Color(255, 255, 255, 113), 1.0f to Color(0, 0, 0, 123), start = Offset(0.2273667f, 0.19338089f), end = Offset(0.17187096f, 0.24506153f), tileMode = TileMode.Clamp)
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
// _0_0_1_2_1_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(20.255362f, 19.273129f)
generalPath!!.cubicTo(20.009453f, 19.315193f, 19.816807f, 19.507772f, 19.774652f, 19.753668f)
generalPath!!.cubicTo(19.732498f, 19.999561f, 19.850004f, 20.24531f, 20.067862f, 20.366879f)
generalPath!!.cubicTo(20.067862f, 20.366879f, 21.910084f, 21.447746f, 24.317862f, 21.991879f)
generalPath!!.cubicTo(26.72564f, 22.536009f, 29.806763f, 22.571304f, 32.130363f, 20.304379f)
generalPath!!.cubicTo(32.305607f, 20.165344f, 32.386852f, 19.938963f, 32.340008f, 19.720224f)
generalPath!!.cubicTo(32.29316f, 19.501486f, 32.126324f, 19.328234f, 31.90951f, 19.273169f)
generalPath!!.cubicTo(31.692694f, 19.218103f, 31.463406f, 19.29075f, 31.317862f, 19.460629f)
generalPath!!.cubicTo(29.367327f, 21.36359f, 26.773024f, 21.36522f, 24.567862f, 20.866879f)
generalPath!!.cubicTo(22.3627f, 20.368536f, 20.661612f, 19.366879f, 20.661612f, 19.366879f)
generalPath!!.cubicTo(20.542177f, 19.287088f, 20.397682f, 19.253744f, 20.255362f, 19.273129f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(163, 163, 163, 255), 1.0f to Color(181, 181, 181, 0), start = Offset(0.17028524f, 0.093009174f), end = Offset(0.1129302f, 0.09869519f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.21454535f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(20.255362f, 19.273129f)
generalPath!!.cubicTo(20.009453f, 19.315193f, 19.816807f, 19.507772f, 19.774652f, 19.753668f)
generalPath!!.cubicTo(19.732498f, 19.999561f, 19.850004f, 20.24531f, 20.067862f, 20.366879f)
generalPath!!.cubicTo(20.067862f, 20.366879f, 21.910084f, 21.447746f, 24.317862f, 21.991879f)
generalPath!!.cubicTo(26.72564f, 22.536009f, 29.806763f, 22.571304f, 32.130363f, 20.304379f)
generalPath!!.cubicTo(32.305607f, 20.165344f, 32.386852f, 19.938963f, 32.340008f, 19.720224f)
generalPath!!.cubicTo(32.29316f, 19.501486f, 32.126324f, 19.328234f, 31.90951f, 19.273169f)
generalPath!!.cubicTo(31.692694f, 19.218103f, 31.463406f, 19.29075f, 31.317862f, 19.460629f)
generalPath!!.cubicTo(29.367327f, 21.36359f, 26.773024f, 21.36522f, 24.567862f, 20.866879f)
generalPath!!.cubicTo(22.3627f, 20.368536f, 20.661612f, 19.366879f, 20.661612f, 19.366879f)
generalPath!!.cubicTo(20.542177f, 19.287088f, 20.397682f, 19.253744f, 20.255362f, 19.273129f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5977654f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.9544389843940735f, 0.0f, 0.0f, 1.4332220554351807f,
0.0f, 0.9898689985275269f, 0.0f, 0.6398810148239136f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1_2_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(25.001158f, 3.5644321f)
generalPath!!.cubicTo(18.737608f, 3.5644321f, 13.655359f, 7.590033f, 13.655359f, 12.547843f)
generalPath!!.cubicTo(13.655359f, 14.527956f, 14.632918f, 16.261759f, 16.006008f, 17.747034f)
generalPath!!.cubicTo(17.558672f, 18.378895f, 19.249826f, 18.832941f, 21.114752f, 18.832941f)
generalPath!!.cubicTo(27.378302f, 18.832941f, 32.46055f, 14.807341f, 32.460552f, 9.849528f)
generalPath!!.cubicTo(32.460552f, 7.857476f, 31.466743f, 6.107463f, 30.07856f, 4.617433f)
generalPath!!.cubicTo(28.533138f, 3.99306f, 26.85424f, 3.5644321f, 25.001158f, 3.5644321f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), start = Offset(-0.71695846f, -0.3445387f), end = Offset(-0.33425662f, -0.3105369f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
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
            return 8.437410354614258
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.990607738494873
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 33.12517547607422
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 46.00939178466797
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
            return dialog_information(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return dialog_information(getOrigWidth().dp, getOrigHeight().dp)
                }
            }
        }

        
    }

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = width
        _height = height
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            clipRect {
                // Use the original icon bounding box and the current icon dimension to compute
                // the scaling factor
                val fullOrigWidth = getOrigX() + getOrigWidth()
                val fullOrigHeight = getOrigY() + getOrigHeight()
                val coef1 = _width.toPx() / fullOrigWidth
                val coef2 = _height.toPx() / fullOrigHeight
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
}

