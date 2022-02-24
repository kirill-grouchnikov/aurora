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
class text : Painter() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var blendMode = DrawScope.DefaultBlendMode
    private var alphaStack = mutableListOf(1.0f)
    private var blendModeStack = mutableListOf(DrawScope.DefaultBlendMode)

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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.004166666883975267f, 0.0f, 0.0f, 0.0f,
0.0f, 0.004166666883975267f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.0f, 0.3333333507180214f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
            generalPathText = null
            alphaText = alpha
            blendModeText = blendMode
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
brush = SolidColor(Color(0, 0, 0, 255))
if (generalPathText == null) {
   generalPathText = Path()
} else {
   generalPathText!!.reset()
}
generalPathText!!.moveTo(21.212402f, 35.0f)
generalPathText!!.lineTo(23.091309f, 25.605469f)
generalPathText!!.lineTo(24.938477f, 25.605469f)
generalPathText!!.lineTo(26.087402f, 32.873535f)
generalPathText!!.lineTo(30.219727f, 25.605469f)
generalPathText!!.lineTo(31.870117f, 25.605469f)
generalPathText!!.lineTo(29.99121f, 35.0f)
generalPathText!!.lineTo(28.74707f, 35.0f)
generalPathText!!.lineTo(30.275585f, 27.357422f)
generalPathText!!.lineTo(26.28291f, 34.371582f)
generalPathText!!.lineTo(24.994335f, 34.371582f)
generalPathText!!.lineTo(23.887304f, 27.338379f)
generalPathText!!.lineTo(22.35498f, 35.0f)
generalPathText!!.close()
generalPathText!!.moveTo(32.34619f, 37.507324f)
generalPathText!!.lineTo(33.964844f, 35.0f)
generalPathText!!.lineTo(32.67754f, 28.106445f)
generalPathText!!.lineTo(34.02959f, 28.106445f)
generalPathText!!.lineTo(34.965233f, 33.29883f)
generalPathText!!.lineTo(38.111134f, 28.106445f)
generalPathText!!.lineTo(39.291798f, 28.106445f)
generalPathText!!.lineTo(33.64746f, 37.507324f)
generalPathText!!.close()
shapeText = Outline.Generic(generalPathText!!)
drawOutline(outline = shapeText!!, style = Fill, brush=brush!!, alpha = alphaText, blendMode = blendModeText)
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
            generalPathText = null
            alphaText = alpha
            blendModeText = blendMode
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
brush = SolidColor(Color(0, 0, 0, 255))
if (generalPathText == null) {
   generalPathText = Path()
} else {
   generalPathText!!.reset()
}
generalPathText!!.moveTo(54.575195f, 34.648438f)
generalPathText!!.quadraticBezierTo(51.879883f, 35.36621f, 49.58008f, 35.36621f)
generalPathText!!.quadraticBezierTo(45.72754f, 35.36621f, 43.493652f, 33.103027f)
generalPathText!!.quadraticBezierTo(41.259766f, 30.839844f, 41.259766f, 26.94336f)
generalPathText!!.quadraticBezierTo(41.259766f, 23.00293f, 43.55957f, 20.776367f)
generalPathText!!.quadraticBezierTo(45.859375f, 18.549805f, 49.916992f, 18.549805f)
generalPathText!!.quadraticBezierTo(51.879883f, 18.549805f, 54.44336f, 19.179688f)
generalPathText!!.lineTo(54.44336f, 22.314453f)
generalPathText!!.quadraticBezierTo(51.777344f, 21.450195f, 50.180664f, 21.450195f)
generalPathText!!.quadraticBezierTo(48.23242f, 21.450195f, 47.0459f, 22.944336f)
generalPathText!!.quadraticBezierTo(45.859375f, 24.438477f, 45.859375f, 26.914062f)
generalPathText!!.quadraticBezierTo(45.859375f, 29.448242f, 47.141113f, 30.97168f)
generalPathText!!.quadraticBezierTo(48.42285f, 32.495117f, 50.561523f, 32.495117f)
generalPathText!!.quadraticBezierTo(52.509766f, 32.495117f, 54.575195f, 31.645508f)
generalPathText!!.close()
generalPathText!!.moveTo(66.55762f, 33.271484f)
generalPathText!!.quadraticBezierTo(64.38965f, 35.36621f, 61.914062f, 35.36621f)
generalPathText!!.quadraticBezierTo(59.804688f, 35.36621f, 58.48633f, 34.07715f)
generalPathText!!.quadraticBezierTo(57.16797f, 32.788086f, 57.16797f, 30.737305f)
generalPathText!!.quadraticBezierTo(57.16797f, 28.07129f, 59.299316f, 26.628418f)
generalPathText!!.quadraticBezierTo(61.430664f, 25.185547f, 65.40039f, 25.185547f)
generalPathText!!.lineTo(66.55762f, 25.185547f)
generalPathText!!.lineTo(66.55762f, 23.720703f)
generalPathText!!.quadraticBezierTo(66.55762f, 21.21582f, 63.70117f, 21.21582f)
generalPathText!!.quadraticBezierTo(61.166992f, 21.21582f, 58.57422f, 22.651367f)
generalPathText!!.lineTo(58.57422f, 19.663086f)
generalPathText!!.quadraticBezierTo(61.518555f, 18.549805f, 64.4043f, 18.549805f)
generalPathText!!.quadraticBezierTo(70.71777f, 18.549805f, 70.71777f, 23.574219f)
generalPathText!!.lineTo(70.71777f, 30.69336f)
generalPathText!!.quadraticBezierTo(70.71777f, 32.583008f, 71.93359f, 32.583008f)
generalPathText!!.quadraticBezierTo(72.15332f, 32.583008f, 72.50488f, 32.524414f)
generalPathText!!.lineTo(72.60742f, 34.956055f)
generalPathText!!.quadraticBezierTo(71.23047f, 35.36621f, 70.17578f, 35.36621f)
generalPathText!!.quadraticBezierTo(67.509766f, 35.36621f, 66.74805f, 33.271484f)
generalPathText!!.close()
generalPathText!!.moveTo(66.55762f, 30.942383f)
generalPathText!!.lineTo(66.55762f, 27.675781f)
generalPathText!!.lineTo(65.53223f, 27.675781f)
generalPathText!!.quadraticBezierTo(61.328125f, 27.675781f, 61.328125f, 30.3125f)
generalPathText!!.quadraticBezierTo(61.328125f, 31.206055f, 61.936035f, 31.813965f)
generalPathText!!.quadraticBezierTo(62.543945f, 32.421875f, 63.4375f, 32.421875f)
generalPathText!!.quadraticBezierTo(64.96094f, 32.421875f, 66.55762f, 30.942383f)
generalPathText!!.close()
generalPathText!!.moveTo(84.60449f, 34.91211f)
generalPathText!!.quadraticBezierTo(83.05176f, 35.36621f, 82.1582f, 35.36621f)
generalPathText!!.quadraticBezierTo(76.518555f, 35.36621f, 76.518555f, 30.092773f)
generalPathText!!.lineTo(76.518555f, 21.625977f)
generalPathText!!.lineTo(74.7168f, 21.625977f)
generalPathText!!.lineTo(74.7168f, 18.916016f)
generalPathText!!.lineTo(76.518555f, 18.916016f)
generalPathText!!.lineTo(76.518555f, 16.206055f)
generalPathText!!.lineTo(80.85449f, 15.708008f)
generalPathText!!.lineTo(80.85449f, 18.916016f)
generalPathText!!.lineTo(84.296875f, 18.916016f)
generalPathText!!.lineTo(84.296875f, 21.625977f)
generalPathText!!.lineTo(80.85449f, 21.625977f)
generalPathText!!.lineTo(80.85449f, 29.521484f)
generalPathText!!.quadraticBezierTo(80.85449f, 32.48047f, 83.271484f, 32.48047f)
generalPathText!!.quadraticBezierTo(83.828125f, 32.48047f, 84.60449f, 32.27539f)
generalPathText!!.close()
shapeText = Outline.Generic(generalPathText!!)
drawOutline(outline = shapeText!!, style = Fill, brush=brush!!, alpha = alphaText, blendMode = blendModeText)
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
            generalPathText = null
            alphaText = alpha
            blendModeText = blendMode
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
brush = SolidColor(Color(0, 0, 0, 255))
if (generalPathText == null) {
   generalPathText = Path()
} else {
   generalPathText!!.reset()
}
generalPathText!!.moveTo(56.25049f, 55.0f)
generalPathText!!.lineTo(57.6292f, 48.106445f)
generalPathText!!.lineTo(58.87969f, 48.106445f)
generalPathText!!.lineTo(57.500977f, 55.0f)
generalPathText!!.close()
generalPathText!!.moveTo(57.879295f, 46.855957f)
generalPathText!!.lineTo(58.129395f, 45.60547f)
generalPathText!!.lineTo(59.379883f, 45.60547f)
generalPathText!!.lineTo(59.129784f, 46.855957f)
generalPathText!!.close()
generalPathText!!.moveTo(61.804688f, 55.15869f)
generalPathText!!.quadraticBezierTo(60.947754f, 55.15869f, 59.802635f, 54.75879f)
generalPathText!!.lineTo(60.03242f, 53.609863f)
generalPathText!!.quadraticBezierTo(61.135643f, 54.21924f, 62.043358f, 54.21924f)
generalPathText!!.quadraticBezierTo(62.58291f, 54.21924f, 62.996777f, 53.927246f)
generalPathText!!.quadraticBezierTo(63.410645f, 53.635254f, 63.49824f, 53.197266f)
generalPathText!!.quadraticBezierTo(63.626465f, 52.556152f, 62.713673f, 52.137207f)
generalPathText!!.lineTo(62.0459f, 51.82617f)
generalPathText!!.quadraticBezierTo(60.561817f, 51.15332f, 60.814453f, 49.890137f)
generalPathText!!.quadraticBezierTo(60.994728f, 48.98877f, 61.736134f, 48.471436f)
generalPathText!!.quadraticBezierTo(62.47754f, 47.9541f, 63.58838f, 47.9541f)
generalPathText!!.quadraticBezierTo(64.166016f, 47.9541f, 64.98486f, 48.112793f)
generalPathText!!.lineTo(65.23496f, 48.163574f)
generalPathText!!.lineTo(65.02676f, 49.20459f)
generalPathText!!.quadraticBezierTo(64.0416f, 48.893555f, 63.42588f, 48.893555f)
generalPathText!!.quadraticBezierTo(62.219826f, 48.893555f, 62.04463f, 49.76953f)
generalPathText!!.quadraticBezierTo(61.93164f, 50.334473f, 62.76826f, 50.72168f)
generalPathText!!.lineTo(63.320507f, 50.975586f)
generalPathText!!.quadraticBezierTo(64.25615f, 51.407227f, 64.585594f, 51.886475f)
generalPathText!!.quadraticBezierTo(64.91504f, 52.365723f, 64.77158f, 53.083008f)
generalPathText!!.quadraticBezierTo(64.59004f, 53.990723f, 63.75596f, 54.574707f)
generalPathText!!.quadraticBezierTo(62.921875f, 55.15869f, 61.804688f, 55.15869f)
generalPathText!!.close()
shapeText = Outline.Generic(generalPathText!!)
drawOutline(outline = shapeText!!, style = Fill, brush=brush!!, alpha = alphaText, blendMode = blendModeText)
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
            generalPathText = null
            alphaText = alpha
            blendModeText = blendMode
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
brush = SolidColor(Color(255, 0, 0, 255))
if (generalPathText == null) {
   generalPathText = Path()
} else {
   generalPathText!!.reset()
}
generalPathText!!.moveTo(93.78906f, 27.910156f)
generalPathText!!.lineTo(91.81641f, 36.25f)
generalPathText!!.lineTo(91.07422f, 36.25f)
generalPathText!!.quadraticBezierTo(91.25f, 34.785156f, 91.25f, 34.08203f)
generalPathText!!.quadraticBezierTo(91.25f, 32.16797f, 89.56055f, 30.69336f)
generalPathText!!.quadraticBezierTo(87.87109f, 29.21875f, 84.90234f, 29.21875f)
generalPathText!!.quadraticBezierTo(78.828125f, 29.21875f, 74.98047f, 34.921875f)
generalPathText!!.quadraticBezierTo(71.91406f, 39.433594f, 71.91406f, 45.01953f)
generalPathText!!.quadraticBezierTo(71.91406f, 48.73047f, 73.80859f, 51.42578f)
generalPathText!!.quadraticBezierTo(75.703125f, 54.121094f, 79.64844f, 54.121094f)
generalPathText!!.quadraticBezierTo(80.625f, 54.121094f, 81.49414f, 53.945312f)
generalPathText!!.quadraticBezierTo(82.36328f, 53.76953f, 84.12109f, 53.164062f)
generalPathText!!.lineTo(86.05469f, 46.347656f)
generalPathText!!.quadraticBezierTo(86.46484f, 44.941406f, 86.46484f, 44.101562f)
generalPathText!!.quadraticBezierTo(86.46484f, 43.398438f, 85.95703f, 43.027344f)
generalPathText!!.quadraticBezierTo(85.13672f, 42.460938f, 83.30078f, 42.460938f)
generalPathText!!.lineTo(82.75391f, 42.460938f)
generalPathText!!.lineTo(82.96875f, 41.69922f)
generalPathText!!.lineTo(93.984375f, 41.69922f)
generalPathText!!.lineTo(93.78906f, 42.460938f)
generalPathText!!.quadraticBezierTo(92.28516f, 42.48047f, 91.60156f, 42.8125f)
generalPathText!!.quadraticBezierTo(90.91797f, 43.14453f, 90.42969f, 43.945312f)
generalPathText!!.quadraticBezierTo(90.09766f, 44.472656f, 89.35547f, 47.01172f)
generalPathText!!.lineTo(87.40234f, 53.710938f)
generalPathText!!.quadraticBezierTo(84.72656f, 54.86328f, 83.08594f, 55.24414f)
generalPathText!!.quadraticBezierTo(81.44531f, 55.625f, 79.62891f, 55.625f)
generalPathText!!.quadraticBezierTo(75.44922f, 55.625f, 72.85156f, 54.052734f)
generalPathText!!.quadraticBezierTo(70.25391f, 52.48047f, 69.01367f, 49.89258f)
generalPathText!!.quadraticBezierTo(67.77344f, 47.304688f, 67.77344f, 44.746094f)
generalPathText!!.quadraticBezierTo(67.77344f, 41.308594f, 69.21875f, 38.21289f)
generalPathText!!.quadraticBezierTo(70.66406f, 35.117188f, 72.822266f, 32.978516f)
generalPathText!!.quadraticBezierTo(74.98047f, 30.839844f, 77.51953f, 29.609375f)
generalPathText!!.quadraticBezierTo(81.015625f, 27.910156f, 84.86328f, 27.910156f)
generalPathText!!.quadraticBezierTo(87.69531f, 27.910156f, 89.98047f, 28.847656f)
generalPathText!!.quadraticBezierTo(90.89844f, 29.21875f, 91.328125f, 29.21875f)
generalPathText!!.quadraticBezierTo(91.81641f, 29.21875f, 92.1582f, 28.99414f)
generalPathText!!.quadraticBezierTo(92.5f, 28.769531f, 93.046875f, 27.910156f)
generalPathText!!.close()
generalPathText!!.moveTo(95.97656f, 38.41797f)
generalPathText!!.lineTo(102.71484f, 37.32422f)
generalPathText!!.lineTo(99.90234f, 46.816406f)
generalPathText!!.quadraticBezierTo(103.32031f, 40.976562f, 106.11328f, 38.652344f)
generalPathText!!.quadraticBezierTo(107.69531f, 37.32422f, 108.69141f, 37.32422f)
generalPathText!!.quadraticBezierTo(109.33594f, 37.32422f, 109.70703f, 37.70508f)
generalPathText!!.quadraticBezierTo(110.078125f, 38.085938f, 110.078125f, 38.808594f)
generalPathText!!.quadraticBezierTo(110.078125f, 40.097656f, 109.41406f, 41.26953f)
generalPathText!!.quadraticBezierTo(108.94531f, 42.148438f, 108.06641f, 42.148438f)
generalPathText!!.quadraticBezierTo(107.61719f, 42.148438f, 107.29492f, 41.85547f)
generalPathText!!.quadraticBezierTo(106.97266f, 41.5625f, 106.89453f, 40.95703f)
generalPathText!!.quadraticBezierTo(106.85547f, 40.585938f, 106.71875f, 40.46875f)
generalPathText!!.quadraticBezierTo(106.5625f, 40.3125f, 106.34766f, 40.3125f)
generalPathText!!.quadraticBezierTo(106.015625f, 40.3125f, 105.72266f, 40.46875f)
generalPathText!!.quadraticBezierTo(105.21484f, 40.742188f, 104.17969f, 41.992188f)
generalPathText!!.quadraticBezierTo(102.55859f, 43.90625f, 100.66406f, 46.953125f)
generalPathText!!.quadraticBezierTo(99.84375f, 48.242188f, 99.25781f, 49.86328f)
generalPathText!!.quadraticBezierTo(98.4375f, 52.089844f, 98.32031f, 52.539062f)
generalPathText!!.lineTo(97.69531f, 55.0f)
generalPathText!!.lineTo(94.70703f, 55.0f)
generalPathText!!.lineTo(98.32031f, 42.871094f)
generalPathText!!.quadraticBezierTo(98.94531f, 40.76172f, 98.94531f, 39.86328f)
generalPathText!!.quadraticBezierTo(98.94531f, 39.51172f, 98.65234f, 39.277344f)
generalPathText!!.quadraticBezierTo(98.26172f, 38.964844f, 97.61719f, 38.964844f)
generalPathText!!.quadraticBezierTo(97.20703f, 38.964844f, 96.11328f, 39.140625f)
generalPathText!!.close()
generalPathText!!.moveTo(128.28125f, 37.79297f)
generalPathText!!.lineTo(124.88281f, 49.433594f)
generalPathText!!.quadraticBezierTo(124.12109f, 52.03125f, 124.12109f, 52.714844f)
generalPathText!!.quadraticBezierTo(124.12109f, 53.066406f, 124.24805f, 53.23242f)
generalPathText!!.quadraticBezierTo(124.375f, 53.398438f, 124.58984f, 53.398438f)
generalPathText!!.quadraticBezierTo(124.921875f, 53.398438f, 125.322266f, 53.095703f)
generalPathText!!.quadraticBezierTo(125.72266f, 52.79297f, 127.34375f, 50.683594f)
generalPathText!!.lineTo(127.92969f, 51.132812f)
generalPathText!!.quadraticBezierTo(126.38672f, 53.57422f, 124.765625f, 54.726562f)
generalPathText!!.quadraticBezierTo(123.69141f, 55.46875f, 122.69531f, 55.46875f)
generalPathText!!.quadraticBezierTo(121.93359f, 55.46875f, 121.50391f, 55.039062f)
generalPathText!!.quadraticBezierTo(121.07422f, 54.609375f, 121.07422f, 53.92578f)
generalPathText!!.quadraticBezierTo(121.07422f, 53.26172f, 121.328125f, 52.16797f)
generalPathText!!.quadraticBezierTo(121.640625f, 50.722656f, 123.10547f, 45.878906f)
generalPathText!!.quadraticBezierTo(119.78516f, 51.328125f, 117.54883f, 53.398438f)
generalPathText!!.quadraticBezierTo(115.3125f, 55.46875f, 113.39844f, 55.46875f)
generalPathText!!.quadraticBezierTo(112.5f, 55.46875f, 111.875f, 54.84375f)
generalPathText!!.quadraticBezierTo(111.25f, 54.21875f, 111.25f, 53.26172f)
generalPathText!!.quadraticBezierTo(111.25f, 51.796875f, 112.109375f, 48.76953f)
generalPathText!!.lineTo(113.80859f, 42.734375f)
generalPathText!!.quadraticBezierTo(114.43359f, 40.566406f, 114.43359f, 40.039062f)
generalPathText!!.quadraticBezierTo(114.43359f, 39.804688f, 114.26758f, 39.63867f)
generalPathText!!.quadraticBezierTo(114.10156f, 39.472656f, 113.90625f, 39.472656f)
generalPathText!!.quadraticBezierTo(113.49609f, 39.472656f, 113.08594f, 39.765625f)
generalPathText!!.quadraticBezierTo(112.67578f, 40.058594f, 111.23047f, 41.933594f)
generalPathText!!.lineTo(110.625f, 41.503906f)
generalPathText!!.quadraticBezierTo(112.08984f, 39.23828f, 113.73047f, 38.164062f)
generalPathText!!.quadraticBezierTo(114.98047f, 37.32422f, 116.03516f, 37.32422f)
generalPathText!!.quadraticBezierTo(116.75781f, 37.32422f, 117.2168f, 37.783203f)
generalPathText!!.quadraticBezierTo(117.67578f, 38.242188f, 117.67578f, 38.964844f)
generalPathText!!.quadraticBezierTo(117.67578f, 40.01953f, 116.89453f, 42.734375f)
generalPathText!!.lineTo(115.05859f, 49.04297f)
generalPathText!!.quadraticBezierTo(114.296875f, 51.621094f, 114.296875f, 52.304688f)
generalPathText!!.quadraticBezierTo(114.296875f, 52.67578f, 114.541016f, 52.90039f)
generalPathText!!.quadraticBezierTo(114.78516f, 53.125f, 115.19531f, 53.125f)
generalPathText!!.quadraticBezierTo(115.83984f, 53.125f, 116.875f, 52.48047f)
generalPathText!!.quadraticBezierTo(117.91016f, 51.835938f, 119.63867f, 49.61914f)
generalPathText!!.quadraticBezierTo(121.36719f, 47.402344f, 122.490234f, 45.458984f)
generalPathText!!.quadraticBezierTo(123.61328f, 43.515625f, 124.88281f, 39.277344f)
generalPathText!!.lineTo(125.3125f, 37.79297f)
generalPathText!!.close()
generalPathText!!.moveTo(138.125f, 37.32422f)
generalPathText!!.lineTo(135.58594f, 46.132812f)
generalPathText!!.quadraticBezierTo(137.28516f, 43.027344f, 138.47656f, 41.464844f)
generalPathText!!.quadraticBezierTo(140.33203f, 39.04297f, 142.08984f, 37.94922f)
generalPathText!!.quadraticBezierTo(143.125f, 37.32422f, 144.25781f, 37.32422f)
generalPathText!!.quadraticBezierTo(145.23438f, 37.32422f, 145.8789f, 37.94922f)
generalPathText!!.quadraticBezierTo(146.52344f, 38.57422f, 146.52344f, 39.55078f)
generalPathText!!.quadraticBezierTo(146.52344f, 40.507812f, 146.07422f, 42.05078f)
generalPathText!!.lineTo(144.6875f, 47.01172f)
generalPathText!!.quadraticBezierTo(147.8711f, 41.054688f, 150.72266f, 38.652344f)
generalPathText!!.quadraticBezierTo(152.30469f, 37.32422f, 153.84766f, 37.32422f)
generalPathText!!.quadraticBezierTo(154.7461f, 37.32422f, 155.32227f, 37.939453f)
generalPathText!!.quadraticBezierTo(155.89844f, 38.554688f, 155.89844f, 39.84375f)
generalPathText!!.quadraticBezierTo(155.89844f, 40.976562f, 155.54688f, 42.246094f)
generalPathText!!.lineTo(153.39844f, 49.84375f)
generalPathText!!.quadraticBezierTo(152.69531f, 52.32422f, 152.69531f, 52.597656f)
generalPathText!!.quadraticBezierTo(152.69531f, 52.871094f, 152.8711f, 53.066406f)
generalPathText!!.quadraticBezierTo(152.98828f, 53.203125f, 153.1836f, 53.203125f)
generalPathText!!.quadraticBezierTo(153.3789f, 53.203125f, 153.86719f, 52.83203f)
generalPathText!!.quadraticBezierTo(154.98047f, 51.972656f, 155.95703f, 50.566406f)
generalPathText!!.lineTo(156.60156f, 50.976562f)
generalPathText!!.quadraticBezierTo(156.11328f, 51.757812f, 154.87305f, 53.125f)
generalPathText!!.quadraticBezierTo(153.63281f, 54.492188f, 152.7539f, 54.98047f)
generalPathText!!.quadraticBezierTo(151.875f, 55.46875f, 151.13281f, 55.46875f)
generalPathText!!.quadraticBezierTo(150.44922f, 55.46875f, 149.99023f, 55.0f)
generalPathText!!.quadraticBezierTo(149.53125f, 54.53125f, 149.53125f, 53.847656f)
generalPathText!!.quadraticBezierTo(149.53125f, 52.910156f, 150.33203f, 50.078125f)
generalPathText!!.lineTo(152.10938f, 43.808594f)
generalPathText!!.quadraticBezierTo(152.71484f, 41.640625f, 152.7539f, 41.40625f)
generalPathText!!.quadraticBezierTo(152.8125f, 41.035156f, 152.8125f, 40.683594f)
generalPathText!!.quadraticBezierTo(152.8125f, 40.195312f, 152.59766f, 39.921875f)
generalPathText!!.quadraticBezierTo(152.36328f, 39.648438f, 152.07031f, 39.648438f)
generalPathText!!.quadraticBezierTo(151.25f, 39.648438f, 150.33203f, 40.48828f)
generalPathText!!.quadraticBezierTo(147.63672f, 42.96875f, 145.29297f, 47.441406f)
generalPathText!!.quadraticBezierTo(143.75f, 50.390625f, 142.40234f, 55.0f)
generalPathText!!.lineTo(139.45312f, 55.0f)
generalPathText!!.lineTo(142.79297f, 43.183594f)
generalPathText!!.quadraticBezierTo(143.33984f, 41.26953f, 143.33984f, 40.625f)
generalPathText!!.quadraticBezierTo(143.33984f, 40.097656f, 143.125f, 39.84375f)
generalPathText!!.quadraticBezierTo(142.91016f, 39.589844f, 142.59766f, 39.589844f)
generalPathText!!.quadraticBezierTo(141.95312f, 39.589844f, 141.23047f, 40.058594f)
generalPathText!!.quadraticBezierTo(140.0586f, 40.820312f, 138.21289f, 43.320312f)
generalPathText!!.quadraticBezierTo(136.36719f, 45.820312f, 135.35156f, 48.10547f)
generalPathText!!.quadraticBezierTo(134.86328f, 49.179688f, 133.02734f, 55.0f)
generalPathText!!.lineTo(130.13672f, 55.0f)
generalPathText!!.lineTo(133.75f, 42.34375f)
generalPathText!!.lineTo(134.21875f, 40.703125f)
generalPathText!!.quadraticBezierTo(134.35547f, 40.33203f, 134.35547f, 40.15625f)
generalPathText!!.quadraticBezierTo(134.35547f, 39.726562f, 133.96484f, 39.384766f)
generalPathText!!.quadraticBezierTo(133.57422f, 39.04297f, 132.96875f, 39.04297f)
generalPathText!!.quadraticBezierTo(132.71484f, 39.04297f, 131.5625f, 39.277344f)
generalPathText!!.lineTo(131.38672f, 38.554688f)
generalPathText!!.close()
generalPathText!!.moveTo(167.1875f, 37.32422f)
generalPathText!!.lineTo(165.83984f, 41.816406f)
generalPathText!!.quadraticBezierTo(167.96875f, 39.316406f, 169.63867f, 38.320312f)
generalPathText!!.quadraticBezierTo(171.3086f, 37.32422f, 173.04688f, 37.32422f)
generalPathText!!.quadraticBezierTo(174.78516f, 37.32422f, 176.01562f, 38.70117f)
generalPathText!!.quadraticBezierTo(177.2461f, 40.078125f, 177.2461f, 42.285156f)
generalPathText!!.quadraticBezierTo(177.2461f, 46.58203f, 173.75f, 51.02539f)
generalPathText!!.quadraticBezierTo(170.2539f, 55.46875f, 165.60547f, 55.46875f)
generalPathText!!.quadraticBezierTo(164.6289f, 55.46875f, 163.87695f, 55.253906f)
generalPathText!!.quadraticBezierTo(163.125f, 55.039062f, 162.1875f, 54.492188f)
generalPathText!!.lineTo(160.76172f, 59.53125f)
generalPathText!!.quadraticBezierTo(160.33203f, 61.015625f, 160.33203f, 61.464844f)
generalPathText!!.quadraticBezierTo(160.33203f, 61.89453f, 160.55664f, 62.197266f)
generalPathText!!.quadraticBezierTo(160.78125f, 62.5f, 161.28906f, 62.666016f)
generalPathText!!.quadraticBezierTo(161.79688f, 62.83203f, 163.30078f, 62.83203f)
generalPathText!!.lineTo(163.125f, 63.554688f)
generalPathText!!.lineTo(153.92578f, 63.554688f)
generalPathText!!.lineTo(154.1211f, 62.83203f)
generalPathText!!.quadraticBezierTo(155.9375f, 62.753906f, 156.57227f, 62.20703f)
generalPathText!!.quadraticBezierTo(157.20703f, 61.660156f, 157.91016f, 59.179688f)
generalPathText!!.lineTo(162.85156f, 42.070312f)
generalPathText!!.quadraticBezierTo(163.30078f, 40.44922f, 163.30078f, 40.058594f)
generalPathText!!.quadraticBezierTo(163.30078f, 39.53125f, 162.99805f, 39.23828f)
generalPathText!!.quadraticBezierTo(162.69531f, 38.945312f, 162.07031f, 38.945312f)
generalPathText!!.quadraticBezierTo(161.5039f, 38.945312f, 160.48828f, 39.08203f)
generalPathText!!.lineTo(160.48828f, 38.320312f)
generalPathText!!.close()
generalPathText!!.moveTo(162.63672f, 52.94922f)
generalPathText!!.quadraticBezierTo(163.84766f, 54.39453f, 165.89844f, 54.39453f)
generalPathText!!.quadraticBezierTo(166.91406f, 54.39453f, 167.96875f, 53.828125f)
generalPathText!!.quadraticBezierTo(169.02344f, 53.26172f, 170.0293f, 52.148438f)
generalPathText!!.quadraticBezierTo(171.03516f, 51.035156f, 171.83594f, 49.58008f)
generalPathText!!.quadraticBezierTo(172.63672f, 48.125f, 173.27148f, 46.152344f)
generalPathText!!.quadraticBezierTo(173.90625f, 44.179688f, 173.90625f, 42.246094f)
generalPathText!!.quadraticBezierTo(173.90625f, 40.683594f, 173.19336f, 39.853516f)
generalPathText!!.quadraticBezierTo(172.48047f, 39.023438f, 171.52344f, 39.023438f)
generalPathText!!.quadraticBezierTo(169.41406f, 39.023438f, 167.35352f, 41.445312f)
generalPathText!!.quadraticBezierTo(165.29297f, 43.867188f, 164.33594f, 47.109375f)
generalPathText!!.close()
generalPathText!!.moveTo(184.72656f, 37.32422f)
generalPathText!!.quadraticBezierTo(185.39062f, 38.45703f, 185.64453f, 39.44336f)
generalPathText!!.quadraticBezierTo(185.89844f, 40.429688f, 186.15234f, 43.320312f)
generalPathText!!.lineTo(187.01172f, 52.929688f)
generalPathText!!.quadraticBezierTo(188.1836f, 51.523438f, 190.41016f, 48.476562f)
generalPathText!!.quadraticBezierTo(191.48438f, 46.992188f, 193.0664f, 44.433594f)
generalPathText!!.quadraticBezierTo(194.02344f, 42.871094f, 194.23828f, 42.246094f)
generalPathText!!.quadraticBezierTo(194.35547f, 41.933594f, 194.35547f, 41.601562f)
generalPathText!!.quadraticBezierTo(194.35547f, 41.38672f, 194.21875f, 41.25f)
generalPathText!!.quadraticBezierTo(194.08203f, 41.11328f, 193.50586f, 40.927734f)
generalPathText!!.quadraticBezierTo(192.92969f, 40.742188f, 192.54883f, 40.24414f)
generalPathText!!.quadraticBezierTo(192.16797f, 39.746094f, 192.16797f, 39.101562f)
generalPathText!!.quadraticBezierTo(192.16797f, 38.30078f, 192.63672f, 37.8125f)
generalPathText!!.quadraticBezierTo(193.10547f, 37.32422f, 193.8086f, 37.32422f)
generalPathText!!.quadraticBezierTo(194.66797f, 37.32422f, 195.27344f, 38.03711f)
generalPathText!!.quadraticBezierTo(195.8789f, 38.75f, 195.8789f, 40.0f)
generalPathText!!.quadraticBezierTo(195.8789f, 41.54297f, 194.82422f, 43.52539f)
generalPathText!!.quadraticBezierTo(193.76953f, 45.507812f, 190.76172f, 49.609375f)
generalPathText!!.quadraticBezierTo(187.7539f, 53.710938f, 183.47656f, 58.554688f)
generalPathText!!.quadraticBezierTo(180.52734f, 61.89453f, 179.10156f, 62.76367f)
generalPathText!!.quadraticBezierTo(177.67578f, 63.632812f, 176.66016f, 63.632812f)
generalPathText!!.quadraticBezierTo(176.05469f, 63.632812f, 175.5957f, 63.17383f)
generalPathText!!.quadraticBezierTo(175.13672f, 62.714844f, 175.13672f, 62.109375f)
generalPathText!!.quadraticBezierTo(175.13672f, 61.347656f, 175.77148f, 60.722656f)
generalPathText!!.quadraticBezierTo(176.40625f, 60.097656f, 177.14844f, 60.097656f)
generalPathText!!.quadraticBezierTo(177.53906f, 60.097656f, 177.79297f, 60.273438f)
generalPathText!!.quadraticBezierTo(177.94922f, 60.371094f, 178.13477f, 60.83008f)
generalPathText!!.quadraticBezierTo(178.32031f, 61.289062f, 178.47656f, 61.445312f)
generalPathText!!.quadraticBezierTo(178.57422f, 61.54297f, 178.71094f, 61.54297f)
generalPathText!!.quadraticBezierTo(178.82812f, 61.54297f, 179.1211f, 61.347656f)
generalPathText!!.quadraticBezierTo(180.19531f, 60.683594f, 181.6211f, 59.23828f)
generalPathText!!.quadraticBezierTo(183.4961f, 57.32422f, 184.39453f, 56.11328f)
generalPathText!!.lineTo(183.30078f, 43.73047f)
generalPathText!!.quadraticBezierTo(183.02734f, 40.664062f, 182.48047f, 40.0f)
generalPathText!!.quadraticBezierTo(181.9336f, 39.335938f, 180.64453f, 39.335938f)
generalPathText!!.quadraticBezierTo(180.23438f, 39.335938f, 179.17969f, 39.453125f)
generalPathText!!.lineTo(179.0039f, 38.73047f)
generalPathText!!.close()
generalPathText!!.moveTo(202.40234f, 48.554688f)
generalPathText!!.lineTo(201.66016f, 48.554688f)
generalPathText!!.quadraticBezierTo(202.61719f, 45.01953f, 203.00781f, 43.154297f)
generalPathText!!.quadraticBezierTo(203.39844f, 41.289062f, 203.88672f, 38.28125f)
generalPathText!!.quadraticBezierTo(204.86328f, 32.070312f, 205.07812f, 31.035156f)
generalPathText!!.quadraticBezierTo(205.46875f, 29.277344f, 206.34766f, 28.359375f)
generalPathText!!.quadraticBezierTo(206.75781f, 27.910156f, 207.48047f, 27.910156f)
generalPathText!!.quadraticBezierTo(208.02734f, 27.910156f, 208.4082f, 28.339844f)
generalPathText!!.quadraticBezierTo(208.78906f, 28.769531f, 208.78906f, 29.492188f)
generalPathText!!.quadraticBezierTo(208.78906f, 30.292969f, 208.41797f, 31.523438f)
generalPathText!!.quadraticBezierTo(207.61719f, 34.21875f, 205.2539f, 40.371094f)
generalPathText!!.quadraticBezierTo(204.1211f, 43.28125f, 204.0039f, 43.61328f)
generalPathText!!.quadraticBezierTo(203.63281f, 44.70703f, 202.40234f, 48.554688f)
generalPathText!!.close()
generalPathText!!.moveTo(201.09375f, 51.640625f)
generalPathText!!.quadraticBezierTo(201.9336f, 51.640625f, 202.51953f, 52.226562f)
generalPathText!!.quadraticBezierTo(203.10547f, 52.8125f, 203.10547f, 53.652344f)
generalPathText!!.quadraticBezierTo(203.10547f, 54.472656f, 202.51953f, 55.058594f)
generalPathText!!.quadraticBezierTo(201.9336f, 55.64453f, 201.09375f, 55.64453f)
generalPathText!!.quadraticBezierTo(200.27344f, 55.64453f, 199.6875f, 55.058594f)
generalPathText!!.quadraticBezierTo(199.10156f, 54.472656f, 199.10156f, 53.652344f)
generalPathText!!.quadraticBezierTo(199.10156f, 52.8125f, 199.6875f, 52.226562f)
generalPathText!!.quadraticBezierTo(200.27344f, 51.640625f, 201.09375f, 51.640625f)
generalPathText!!.close()
shapeText = Outline.Generic(generalPathText!!)
drawOutline(outline = shapeText!!, style = Fill, brush=brush!!, alpha = alphaText, blendMode = blendModeText)
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
            return 0.08838501572608948
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.3987833857536316
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 0.7815694808959961
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 0.1996866911649704
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

