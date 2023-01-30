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
class us : Painter() {
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
withTransform({
transform(
Matrix(values=floatArrayOf(
3.938499927520752f, 0.0f, 0.0f, 0.0f,
0.0f, 3.938499927520752f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(0.0f, 0.0f)
    lineTo(247.0f, 0.0f)
    lineTo(247.0f, 10.0f)
    lineTo(0.0f, 10.0f)
    close()
    moveTo(0.0f, 20.0f)
    lineTo(247.0f, 20.0f)
    lineTo(247.0f, 30.0f)
    lineTo(0.0f, 30.0f)
    close()
    moveTo(0.0f, 40.0f)
    lineTo(247.0f, 40.0f)
    lineTo(247.0f, 50.0f)
    lineTo(0.0f, 50.0f)
    close()
    moveTo(0.0f, 60.0f)
    lineTo(247.0f, 60.0f)
    lineTo(247.0f, 70.0f)
    lineTo(0.0f, 70.0f)
    close()
    moveTo(0.0f, 80.0f)
    lineTo(247.0f, 80.0f)
    lineTo(247.0f, 90.0f)
    lineTo(0.0f, 90.0f)
    close()
    moveTo(0.0f, 100.0f)
    lineTo(247.0f, 100.0f)
    lineTo(247.0f, 110.0f)
    lineTo(0.0f, 110.0f)
    close()
    moveTo(0.0f, 120.0f)
    lineTo(247.0f, 120.0f)
    lineTo(247.0f, 130.0f)
    lineTo(0.0f, 130.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(189, 61, 68, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(0.0f, 10.0f)
    lineTo(247.0f, 10.0f)
    lineTo(247.0f, 20.0f)
    lineTo(0.0f, 20.0f)
    close()
    moveTo(0.0f, 30.0f)
    lineTo(247.0f, 30.0f)
    lineTo(247.0f, 40.0f)
    lineTo(0.0f, 40.0f)
    close()
    moveTo(0.0f, 50.0f)
    lineTo(247.0f, 50.0f)
    lineTo(247.0f, 60.0f)
    lineTo(0.0f, 60.0f)
    close()
    moveTo(0.0f, 70.0f)
    lineTo(247.0f, 70.0f)
    lineTo(247.0f, 80.0f)
    lineTo(0.0f, 80.0f)
    close()
    moveTo(0.0f, 90.0f)
    lineTo(247.0f, 90.0f)
    lineTo(247.0f, 100.0f)
    lineTo(0.0f, 100.0f)
    close()
    moveTo(0.0f, 110.0f)
    lineTo(247.0f, 110.0f)
    lineTo(247.0f, 120.0f)
    lineTo(0.0f, 120.0f)
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
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(0.0f, 0.0f)
    lineTo(98.8f, 0.0f)
    lineTo(98.8f, 70.0f)
    lineTo(0.0f, 70.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(25, 47, 93, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.233f, 2.996f)
    lineTo(9.132999f, 5.763f)
    lineTo(12.040999f, 5.763f)
    lineTo(9.688f, 7.473f)
    lineTo(10.587f, 10.239f)
    lineTo(8.233f, 8.529f)
    lineTo(5.88f, 10.239f)
    lineTo(6.7790003f, 7.4730005f)
    lineTo(4.425f, 5.7630005f)
    lineTo(7.335f, 5.7630005f)
    close()
    moveTo(24.699999f, 2.996f)
    lineTo(25.598999f, 5.763f)
    lineTo(28.508f, 5.763f)
    lineTo(26.154999f, 7.473f)
    lineTo(27.053f, 10.239f)
    lineTo(24.7f, 8.53f)
    lineTo(22.347f, 10.24f)
    lineTo(23.245f, 7.474f)
    lineTo(20.892f, 5.764f)
    lineTo(23.801f, 5.764f)
    close()
    moveTo(41.167f, 2.996f)
    lineTo(42.065998f, 5.763f)
    lineTo(44.975f, 5.763f)
    lineTo(42.621998f, 7.473f)
    lineTo(43.520996f, 10.239f)
    lineTo(41.166996f, 8.529f)
    lineTo(38.813995f, 10.239f)
    lineTo(39.712994f, 7.4730005f)
    lineTo(37.358994f, 5.7630005f)
    lineTo(40.268993f, 5.7630005f)
    close()
    moveTo(57.633f, 2.996f)
    lineTo(58.531998f, 5.763f)
    lineTo(61.440998f, 5.763f)
    lineTo(59.087997f, 7.473f)
    lineTo(59.986996f, 10.239f)
    lineTo(57.632996f, 8.529f)
    lineTo(55.279995f, 10.239f)
    lineTo(56.178993f, 7.4730005f)
    lineTo(53.824993f, 5.7630005f)
    lineTo(56.734993f, 5.7630005f)
    close()
    moveTo(74.1f, 2.996f)
    lineTo(74.999f, 5.763f)
    lineTo(77.908f, 5.763f)
    lineTo(75.554f, 7.473f)
    lineTo(76.454f, 10.239f)
    lineTo(74.1f, 8.53f)
    lineTo(71.747f, 10.24f)
    lineTo(72.645004f, 7.474f)
    lineTo(70.29201f, 5.764f)
    lineTo(73.201004f, 5.764f)
    close()
    moveTo(90.567f, 2.996f)
    lineTo(91.466f, 5.763f)
    lineTo(94.375f, 5.763f)
    lineTo(92.020996f, 7.473f)
    lineTo(92.921f, 10.239f)
    lineTo(90.567f, 8.529f)
    lineTo(88.214005f, 10.239f)
    lineTo(89.11201f, 7.4730005f)
    lineTo(86.75901f, 5.7630005f)
    lineTo(89.66801f, 5.7630005f)
    close()
    moveTo(16.467f, 9.996f)
    lineTo(17.366f, 12.7630005f)
    lineTo(20.275f, 12.7630005f)
    lineTo(17.921999f, 14.473001f)
    lineTo(18.82f, 17.239f)
    lineTo(16.467f, 15.529f)
    lineTo(14.113999f, 17.239f)
    lineTo(15.011999f, 14.473001f)
    lineTo(12.658999f, 12.7630005f)
    lineTo(15.567999f, 12.7630005f)
    close()
    moveTo(32.933f, 9.996f)
    lineTo(33.831997f, 12.7630005f)
    lineTo(36.740997f, 12.7630005f)
    lineTo(34.387997f, 14.473001f)
    lineTo(35.286995f, 17.239f)
    lineTo(32.932995f, 15.529f)
    lineTo(30.579994f, 17.239f)
    lineTo(31.478994f, 14.473001f)
    lineTo(29.124994f, 12.7630005f)
    lineTo(32.034996f, 12.7630005f)
    close()
    moveTo(49.399998f, 9.996f)
    lineTo(50.298996f, 12.7630005f)
    lineTo(53.207996f, 12.7630005f)
    lineTo(50.854996f, 14.473001f)
    lineTo(51.753994f, 17.239f)
    lineTo(49.399994f, 15.529f)
    lineTo(47.046993f, 17.239f)
    lineTo(47.94599f, 14.473001f)
    lineTo(45.59199f, 12.7630005f)
    lineTo(48.50199f, 12.7630005f)
    close()
    moveTo(65.867f, 9.996f)
    lineTo(66.766f, 12.7630005f)
    lineTo(69.674995f, 12.7630005f)
    lineTo(67.322f, 14.473001f)
    lineTo(68.22f, 17.239f)
    lineTo(65.867004f, 15.529f)
    lineTo(63.514004f, 17.239f)
    lineTo(64.413f, 14.473001f)
    lineTo(62.059002f, 12.7630005f)
    lineTo(64.969f, 12.7630005f)
    close()
    moveTo(82.33299f, 9.996f)
    lineTo(83.231995f, 12.7630005f)
    lineTo(86.14099f, 12.7630005f)
    lineTo(83.78699f, 14.473001f)
    lineTo(84.68699f, 17.239f)
    lineTo(82.33299f, 15.529f)
    lineTo(79.979996f, 17.239f)
    lineTo(80.878f, 14.473001f)
    lineTo(78.525f, 12.7630005f)
    lineTo(81.434f, 12.7630005f)
    close()
    moveTo(8.233f, 16.996f)
    lineTo(9.132999f, 19.763f)
    lineTo(12.040999f, 19.763f)
    lineTo(9.688f, 21.473f)
    lineTo(10.587f, 24.239f)
    lineTo(8.233f, 22.529f)
    lineTo(5.88f, 24.238998f)
    lineTo(6.7790003f, 21.472998f)
    lineTo(4.425f, 19.762997f)
    lineTo(7.335f, 19.762997f)
    close()
    moveTo(24.699999f, 16.996f)
    lineTo(25.598999f, 19.763f)
    lineTo(28.508f, 19.763f)
    lineTo(26.154999f, 21.473f)
    lineTo(27.053f, 24.239f)
    lineTo(24.699999f, 22.529f)
    lineTo(22.346998f, 24.238998f)
    lineTo(23.244999f, 21.472998f)
    lineTo(20.891998f, 19.762997f)
    lineTo(23.800999f, 19.762997f)
    close()
    moveTo(41.167f, 16.996f)
    lineTo(42.065998f, 19.763f)
    lineTo(44.975f, 19.763f)
    lineTo(42.621998f, 21.473f)
    lineTo(43.520996f, 24.239f)
    lineTo(41.166996f, 22.529f)
    lineTo(38.813995f, 24.238998f)
    lineTo(39.712994f, 21.472998f)
    lineTo(37.358994f, 19.762997f)
    lineTo(40.268993f, 19.762997f)
    close()
    moveTo(57.633f, 16.996f)
    lineTo(58.531998f, 19.763f)
    lineTo(61.440998f, 19.763f)
    lineTo(59.087997f, 21.473f)
    lineTo(59.986996f, 24.239f)
    lineTo(57.632996f, 22.529f)
    lineTo(55.279995f, 24.238998f)
    lineTo(56.178993f, 21.472998f)
    lineTo(53.824993f, 19.762997f)
    lineTo(56.734993f, 19.762997f)
    close()
    moveTo(74.1f, 16.996f)
    lineTo(74.999f, 19.763f)
    lineTo(77.908f, 19.763f)
    lineTo(75.554f, 21.473f)
    lineTo(76.454f, 24.239f)
    lineTo(74.100006f, 22.529f)
    lineTo(71.74701f, 24.238998f)
    lineTo(72.64501f, 21.472998f)
    lineTo(70.292015f, 19.762997f)
    lineTo(73.20101f, 19.762997f)
    close()
    moveTo(90.567f, 16.996f)
    lineTo(91.466f, 19.763f)
    lineTo(94.375f, 19.763f)
    lineTo(92.020996f, 21.473f)
    lineTo(92.921f, 24.239f)
    lineTo(90.567f, 22.529f)
    lineTo(88.214005f, 24.238998f)
    lineTo(89.11201f, 21.472998f)
    lineTo(86.75901f, 19.762997f)
    lineTo(89.66801f, 19.762997f)
    close()
    moveTo(16.467f, 23.996f)
    lineTo(17.366f, 26.763f)
    lineTo(20.275f, 26.763f)
    lineTo(17.921999f, 28.473f)
    lineTo(18.82f, 31.239f)
    lineTo(16.467f, 29.529f)
    lineTo(14.113999f, 31.238998f)
    lineTo(15.011999f, 28.472998f)
    lineTo(12.658999f, 26.762997f)
    lineTo(15.567999f, 26.762997f)
    close()
    moveTo(32.933f, 23.996f)
    lineTo(33.831997f, 26.763f)
    lineTo(36.740997f, 26.763f)
    lineTo(34.387997f, 28.473f)
    lineTo(35.286995f, 31.239f)
    lineTo(32.932995f, 29.529f)
    lineTo(30.579994f, 31.238998f)
    lineTo(31.478994f, 28.472998f)
    lineTo(29.124994f, 26.762997f)
    lineTo(32.034996f, 26.762997f)
    close()
    moveTo(49.399998f, 23.996f)
    lineTo(50.298996f, 26.763f)
    lineTo(53.207996f, 26.763f)
    lineTo(50.854996f, 28.473f)
    lineTo(51.753994f, 31.239f)
    lineTo(49.399994f, 29.529f)
    lineTo(47.046993f, 31.238998f)
    lineTo(47.94599f, 28.472998f)
    lineTo(45.59199f, 26.762997f)
    lineTo(48.50199f, 26.762997f)
    close()
    moveTo(65.867f, 23.996f)
    lineTo(66.766f, 26.763f)
    lineTo(69.674995f, 26.763f)
    lineTo(67.322f, 28.473f)
    lineTo(68.22f, 31.239f)
    lineTo(65.867004f, 29.529f)
    lineTo(63.514004f, 31.238998f)
    lineTo(64.413f, 28.472998f)
    lineTo(62.059002f, 26.762997f)
    lineTo(64.969f, 26.762997f)
    close()
    moveTo(82.33299f, 23.996f)
    lineTo(83.231995f, 26.763f)
    lineTo(86.14099f, 26.763f)
    lineTo(83.78699f, 28.473f)
    lineTo(84.68699f, 31.239f)
    lineTo(82.33299f, 29.529f)
    lineTo(79.979996f, 31.238998f)
    lineTo(80.878f, 28.472998f)
    lineTo(78.525f, 26.762997f)
    lineTo(81.434f, 26.762997f)
    close()
    moveTo(8.233f, 30.996f)
    lineTo(9.132999f, 33.763f)
    lineTo(12.040999f, 33.763f)
    lineTo(9.688f, 35.473f)
    lineTo(10.587f, 38.239f)
    lineTo(8.233f, 36.529f)
    lineTo(5.88f, 38.239f)
    lineTo(6.7790003f, 35.473f)
    lineTo(4.425f, 33.763f)
    lineTo(7.335f, 33.763f)
    close()
    moveTo(24.699999f, 30.996f)
    lineTo(25.598999f, 33.763f)
    lineTo(28.508f, 33.763f)
    lineTo(26.154999f, 35.473f)
    lineTo(27.053f, 38.239f)
    lineTo(24.699999f, 36.529f)
    lineTo(22.346998f, 38.239f)
    lineTo(23.244999f, 35.473f)
    lineTo(20.891998f, 33.763f)
    lineTo(23.800999f, 33.763f)
    close()
    moveTo(41.167f, 30.996f)
    lineTo(42.065998f, 33.763f)
    lineTo(44.975f, 33.763f)
    lineTo(42.621998f, 35.473f)
    lineTo(43.520996f, 38.239f)
    lineTo(41.166996f, 36.529f)
    lineTo(38.813995f, 38.239f)
    lineTo(39.712994f, 35.473f)
    lineTo(37.358994f, 33.763f)
    lineTo(40.268993f, 33.763f)
    close()
    moveTo(57.633f, 30.996f)
    lineTo(58.531998f, 33.763f)
    lineTo(61.440998f, 33.763f)
    lineTo(59.087997f, 35.473f)
    lineTo(59.986996f, 38.239f)
    lineTo(57.632996f, 36.529f)
    lineTo(55.279995f, 38.239f)
    lineTo(56.178993f, 35.473f)
    lineTo(53.824993f, 33.763f)
    lineTo(56.734993f, 33.763f)
    close()
    moveTo(74.1f, 30.996f)
    lineTo(74.999f, 33.763f)
    lineTo(77.908f, 33.763f)
    lineTo(75.554f, 35.473f)
    lineTo(76.454f, 38.239f)
    lineTo(74.100006f, 36.529f)
    lineTo(71.74701f, 38.239f)
    lineTo(72.64501f, 35.473f)
    lineTo(70.292015f, 33.763f)
    lineTo(73.20101f, 33.763f)
    close()
    moveTo(90.567f, 30.996f)
    lineTo(91.466f, 33.763f)
    lineTo(94.375f, 33.763f)
    lineTo(92.020996f, 35.473f)
    lineTo(92.921f, 38.239f)
    lineTo(90.567f, 36.529f)
    lineTo(88.214005f, 38.239f)
    lineTo(89.11201f, 35.473f)
    lineTo(86.75901f, 33.763f)
    lineTo(89.66801f, 33.763f)
    close()
    moveTo(16.467f, 37.996f)
    lineTo(17.366f, 40.762997f)
    lineTo(20.275f, 40.762997f)
    lineTo(17.921999f, 42.472996f)
    lineTo(18.82f, 45.238995f)
    lineTo(16.467f, 43.528996f)
    lineTo(14.113999f, 45.238995f)
    lineTo(15.011999f, 42.472996f)
    lineTo(12.658999f, 40.762997f)
    lineTo(15.567999f, 40.762997f)
    close()
    moveTo(32.933f, 37.996f)
    lineTo(33.831997f, 40.762997f)
    lineTo(36.740997f, 40.762997f)
    lineTo(34.387997f, 42.472996f)
    lineTo(35.286995f, 45.238995f)
    lineTo(32.932995f, 43.528996f)
    lineTo(30.579994f, 45.238995f)
    lineTo(31.478994f, 42.472996f)
    lineTo(29.124994f, 40.762997f)
    lineTo(32.034996f, 40.762997f)
    close()
    moveTo(49.399998f, 37.996f)
    lineTo(50.298996f, 40.762997f)
    lineTo(53.207996f, 40.762997f)
    lineTo(50.854996f, 42.472996f)
    lineTo(51.753994f, 45.238995f)
    lineTo(49.399994f, 43.528996f)
    lineTo(47.046993f, 45.238995f)
    lineTo(47.94599f, 42.472996f)
    lineTo(45.59199f, 40.762997f)
    lineTo(48.50199f, 40.762997f)
    close()
    moveTo(65.867f, 37.996f)
    lineTo(66.766f, 40.762997f)
    lineTo(69.674995f, 40.762997f)
    lineTo(67.322f, 42.472996f)
    lineTo(68.22f, 45.238995f)
    lineTo(65.867004f, 43.528996f)
    lineTo(63.514004f, 45.238995f)
    lineTo(64.413f, 42.472996f)
    lineTo(62.059002f, 40.762997f)
    lineTo(64.969f, 40.762997f)
    close()
    moveTo(82.33299f, 37.996f)
    lineTo(83.231995f, 40.762997f)
    lineTo(86.14099f, 40.762997f)
    lineTo(83.78699f, 42.472996f)
    lineTo(84.68699f, 45.238995f)
    lineTo(82.33299f, 43.528996f)
    lineTo(79.979996f, 45.238995f)
    lineTo(80.878f, 42.472996f)
    lineTo(78.525f, 40.762997f)
    lineTo(81.434f, 40.762997f)
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
// _0_0_2_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.233f, 44.996f)
    lineTo(9.132999f, 47.762997f)
    lineTo(12.040999f, 47.762997f)
    lineTo(9.688f, 49.472996f)
    lineTo(10.587f, 52.238995f)
    lineTo(8.233f, 50.528996f)
    lineTo(5.88f, 52.238995f)
    lineTo(6.7790003f, 49.472996f)
    lineTo(4.425f, 47.762997f)
    lineTo(7.335f, 47.762997f)
    close()
    moveTo(24.699999f, 44.996f)
    lineTo(25.598999f, 47.762997f)
    lineTo(28.508f, 47.762997f)
    lineTo(26.154999f, 49.472996f)
    lineTo(27.053f, 52.238995f)
    lineTo(24.699999f, 50.528996f)
    lineTo(22.346998f, 52.238995f)
    lineTo(23.244999f, 49.472996f)
    lineTo(20.891998f, 47.762997f)
    lineTo(23.800999f, 47.762997f)
    close()
    moveTo(41.167f, 44.996f)
    lineTo(42.065998f, 47.762997f)
    lineTo(44.975f, 47.762997f)
    lineTo(42.621998f, 49.472996f)
    lineTo(43.520996f, 52.238995f)
    lineTo(41.166996f, 50.528996f)
    lineTo(38.813995f, 52.238995f)
    lineTo(39.712994f, 49.472996f)
    lineTo(37.358994f, 47.762997f)
    lineTo(40.268993f, 47.762997f)
    close()
    moveTo(57.633f, 44.996f)
    lineTo(58.531998f, 47.762997f)
    lineTo(61.440998f, 47.762997f)
    lineTo(59.087997f, 49.472996f)
    lineTo(59.986996f, 52.238995f)
    lineTo(57.632996f, 50.528996f)
    lineTo(55.279995f, 52.238995f)
    lineTo(56.178993f, 49.472996f)
    lineTo(53.824993f, 47.762997f)
    lineTo(56.734993f, 47.762997f)
    close()
    moveTo(74.1f, 44.996f)
    lineTo(74.999f, 47.762997f)
    lineTo(77.908f, 47.762997f)
    lineTo(75.554f, 49.472996f)
    lineTo(76.454f, 52.238995f)
    lineTo(74.100006f, 50.528996f)
    lineTo(71.74701f, 52.238995f)
    lineTo(72.64501f, 49.472996f)
    lineTo(70.292015f, 47.762997f)
    lineTo(73.20101f, 47.762997f)
    close()
    moveTo(90.567f, 44.996f)
    lineTo(91.466f, 47.762997f)
    lineTo(94.375f, 47.762997f)
    lineTo(92.020996f, 49.472996f)
    lineTo(92.921f, 52.238995f)
    lineTo(90.567f, 50.528996f)
    lineTo(88.214005f, 52.238995f)
    lineTo(89.11201f, 49.472996f)
    lineTo(86.75901f, 47.762997f)
    lineTo(89.66801f, 47.762997f)
    close()
    moveTo(16.467f, 51.996f)
    lineTo(17.366f, 54.762997f)
    lineTo(20.275f, 54.762997f)
    lineTo(17.921999f, 56.472996f)
    lineTo(18.82f, 59.238995f)
    lineTo(16.467f, 57.528996f)
    lineTo(14.113999f, 59.238995f)
    lineTo(15.011999f, 56.472996f)
    lineTo(12.658999f, 54.762997f)
    lineTo(15.567999f, 54.762997f)
    close()
    moveTo(32.933f, 51.996f)
    lineTo(33.831997f, 54.762997f)
    lineTo(36.740997f, 54.762997f)
    lineTo(34.387997f, 56.472996f)
    lineTo(35.286995f, 59.238995f)
    lineTo(32.932995f, 57.528996f)
    lineTo(30.579994f, 59.238995f)
    lineTo(31.478994f, 56.472996f)
    lineTo(29.124994f, 54.762997f)
    lineTo(32.034996f, 54.762997f)
    close()
    moveTo(49.399998f, 51.996f)
    lineTo(50.298996f, 54.762997f)
    lineTo(53.207996f, 54.762997f)
    lineTo(50.854996f, 56.472996f)
    lineTo(51.753994f, 59.238995f)
    lineTo(49.399994f, 57.528996f)
    lineTo(47.046993f, 59.238995f)
    lineTo(47.94599f, 56.472996f)
    lineTo(45.59199f, 54.762997f)
    lineTo(48.50199f, 54.762997f)
    close()
    moveTo(65.867f, 51.996f)
    lineTo(66.766f, 54.762997f)
    lineTo(69.674995f, 54.762997f)
    lineTo(67.322f, 56.472996f)
    lineTo(68.22f, 59.238995f)
    lineTo(65.867004f, 57.528996f)
    lineTo(63.514004f, 59.238995f)
    lineTo(64.413f, 56.472996f)
    lineTo(62.059002f, 54.762997f)
    lineTo(64.969f, 54.762997f)
    close()
    moveTo(82.33299f, 51.996f)
    lineTo(83.231995f, 54.762997f)
    lineTo(86.14099f, 54.762997f)
    lineTo(83.78699f, 56.472996f)
    lineTo(84.68699f, 59.238995f)
    lineTo(82.33299f, 57.528996f)
    lineTo(79.979996f, 59.238995f)
    lineTo(80.878f, 56.472996f)
    lineTo(78.525f, 54.762997f)
    lineTo(81.434f, 54.762997f)
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
// _0_0_2_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.233f, 58.996f)
    lineTo(9.132999f, 61.762997f)
    lineTo(12.040999f, 61.762997f)
    lineTo(9.688f, 63.472996f)
    lineTo(10.587f, 66.239f)
    lineTo(8.233f, 64.529f)
    lineTo(5.88f, 66.239f)
    lineTo(6.7790003f, 63.473f)
    lineTo(4.425f, 61.763f)
    lineTo(7.335f, 61.763f)
    close()
    moveTo(24.699999f, 58.996f)
    lineTo(25.598999f, 61.762997f)
    lineTo(28.508f, 61.762997f)
    lineTo(26.154999f, 63.472996f)
    lineTo(27.053f, 66.239f)
    lineTo(24.699999f, 64.529f)
    lineTo(22.346998f, 66.239f)
    lineTo(23.244999f, 63.473f)
    lineTo(20.891998f, 61.763f)
    lineTo(23.800999f, 61.763f)
    close()
    moveTo(41.167f, 58.996f)
    lineTo(42.065998f, 61.762997f)
    lineTo(44.975f, 61.762997f)
    lineTo(42.621998f, 63.472996f)
    lineTo(43.520996f, 66.239f)
    lineTo(41.166996f, 64.529f)
    lineTo(38.813995f, 66.239f)
    lineTo(39.712994f, 63.473f)
    lineTo(37.358994f, 61.763f)
    lineTo(40.268993f, 61.763f)
    close()
    moveTo(57.633f, 58.996f)
    lineTo(58.531998f, 61.762997f)
    lineTo(61.440998f, 61.762997f)
    lineTo(59.087997f, 63.472996f)
    lineTo(59.986996f, 66.239f)
    lineTo(57.632996f, 64.529f)
    lineTo(55.279995f, 66.239f)
    lineTo(56.178993f, 63.473f)
    lineTo(53.824993f, 61.763f)
    lineTo(56.734993f, 61.763f)
    close()
    moveTo(74.1f, 58.996f)
    lineTo(74.999f, 61.762997f)
    lineTo(77.908f, 61.762997f)
    lineTo(75.554f, 63.472996f)
    lineTo(76.454f, 66.239f)
    lineTo(74.100006f, 64.529f)
    lineTo(71.74701f, 66.239f)
    lineTo(72.64501f, 63.473f)
    lineTo(70.292015f, 61.763f)
    lineTo(73.20101f, 61.763f)
    close()
    moveTo(90.567f, 58.996f)
    lineTo(91.466f, 61.762997f)
    lineTo(94.375f, 61.762997f)
    lineTo(92.020996f, 63.472996f)
    lineTo(92.921f, 66.239f)
    lineTo(90.567f, 64.529f)
    lineTo(88.214005f, 66.239f)
    lineTo(89.11201f, 63.473f)
    lineTo(86.75901f, 61.763f)
    lineTo(89.66801f, 61.763f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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

