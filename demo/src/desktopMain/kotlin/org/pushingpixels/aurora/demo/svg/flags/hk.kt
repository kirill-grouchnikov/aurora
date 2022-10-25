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
class hk : Painter() {
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
0.699999988079071f, 0.0f, 0.0f, 0.0f,
0.0f, 0.699999988079071f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-113.43000030517578f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(1063.0f, 744.094f)
    lineTo(0.0f, 744.094f)
    lineTo(0.0f, 0.004f)
    lineTo(1063.0f, 0.004f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(516.372f, 360.137f)
    cubicTo(516.372f, 360.137f, 441.35202f, 327.837f, 452.117f, 244.137f)
    cubicTo(462.53702f, 203.84698f, 480.945f, 176.41f, 514.287f, 161.47699f)
    cubicTo(529.917f, 156.61299f, 545.894f, 154.52899f, 562.217f, 153.13998f)
    cubicTo(557.933f, 157.19199f, 554.345f, 161.24399f, 552.493f, 166.33798f)
    cubicTo(548.90295f, 175.48398f, 551.566f, 184.28299f, 556.313f, 193.08098f)
    cubicTo(562.333f, 203.27098f, 565.919f, 213.80498f, 567.078f, 227.46597f)
    cubicTo(569.278f, 246.68398f, 561.058f, 265.20898f, 545.892f, 276.43896f)
    cubicTo(536.28204f, 283.84897f, 524.93805f, 286.39496f, 515.67505f, 294.84598f)
    cubicTo(508.49805f, 301.90897f, 504.09906f, 308.972f, 502.47806f, 321.24197f)
    cubicTo(502.24606f, 344.62897f, 508.61505f, 347.87198f, 516.3721f, 360.14197f)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(502.132f, 239.037f)
    lineTo(502.132f, 238.69f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(502.132f, 239.037f)
    lineTo(502.132f, 238.69f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
brush = SolidColor(Color(186, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(506.872f, 351.447f)
    cubicTo(477.69202f, 325.62698f, 480.242f, 260.68698f, 502.47202f, 239.03699f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(501.898f, 223.064f)
    lineTo(511.508f, 220.899f)
    lineTo(512.652f, 211.529f)
    lineTo(517.432f, 219.559f)
    lineTo(527.04f, 217.37901f)
    lineTo(520.383f, 224.50601f)
    lineTo(525.177f, 232.52602f)
    lineTo(516.283f, 228.90402f)
    lineTo(509.63702f, 236.04002f)
    lineTo(510.79703f, 226.67502f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(522.747f, 354.6f)
    cubicTo(522.747f, 354.6f, 531.64703f, 273.406f, 614.827f, 259.174f)
    cubicTo(656.403f, 257.33102f, 688.03f, 266.89102f, 712.077f, 294.394f)
    cubicTo(721.307f, 307.91202f, 727.98206f, 322.578f, 734.093f, 337.777f)
    cubicTo(728.963f, 334.86902f, 724.038f, 332.625f, 718.625f, 332.34702f)
    cubicTo(708.828f, 331.595f, 701.195f, 336.71902f, 694.175f, 343.83502f)
    cubicTo(686.198f, 352.575f, 677.175f, 359.09302f, 664.455f, 364.20502f)
    cubicTo(646.72504f, 371.937f, 626.60504f, 369.50702f, 611.42303f, 358.298f)
    cubicTo(601.523f, 351.28f, 595.76306f, 341.181f, 584.96906f, 334.80002f)
    cubicTo(576.11206f, 330.00803f, 568.06903f, 327.872f, 555.8641f, 329.91702f)
    cubicTo(533.4361f, 336.54703f, 532.2021f, 343.58704f, 522.7441f, 354.60004f)
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
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(634.36f, 305.5f)
    lineTo(634.69f, 305.397f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(634.36f, 305.5f)
    lineTo(634.69f, 305.397f)
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
brush = SolidColor(Color(186, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(528.272f, 342.97f)
    cubicTo(544.409f, 307.504f, 607.246f, 290.914f, 634.459f, 305.824f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(649.563f, 300.594f)
    lineTo(654.449f, 309.15f)
    lineTo(663.73895f, 307.498f)
    lineTo(657.4639f, 314.421f)
    lineTo(662.36395f, 322.969f)
    lineTo(653.59894f, 318.693f)
    lineTo(647.33496f, 325.626f)
    lineTo(648.19196f, 316.061f)
    lineTo(639.42194f, 311.798f)
    lineTo(648.7169f, 310.163f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(528.353f, 360.463f)
    cubicTo(528.353f, 360.463f, 608.16705f, 343.117f, 648.08105f, 417.46902f)
    cubicTo(663.03107f, 456.30502f, 664.01105f, 489.32904f, 645.57104f, 520.869f)
    cubicTo(635.684f, 533.91205f, 623.89703f, 544.89905f, 611.426f, 555.521f)
    cubicTo(612.556f, 549.733f, 613.116f, 544.351f, 611.66205f, 539.12897f)
    cubicTo(609.2651f, 529.60095f, 601.98206f, 523.99097f, 593.00507f, 519.594f)
    cubicTo(582.18304f, 514.80597f, 573.13904f, 508.32098f, 564.2521f, 497.88098f)
    cubicTo(551.2881f, 483.525f, 547.2021f, 463.675f, 553.0101f, 445.72098f)
    cubicTo(556.5201f, 434.10397f, 564.2671f, 425.43597f, 566.8901f, 413.17398f)
    cubicTo(568.62006f, 403.25397f, 568.0921f, 394.951f, 562.27606f, 384.02597f)
    cubicTo(548.8661f, 364.86597f, 541.7991f, 365.93097f, 528.35205f, 360.45798f)
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
// _0_0_10
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(610.357f, 450.702f)
    lineTo(610.559f, 450.984f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(610.357f, 450.702f)
    lineTo(610.559f, 450.984f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
brush = SolidColor(Color(186, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(541.134f, 362.008f)
    cubicTo(579.888f, 366.046f, 615.576f, 420.36f, 610.07996f, 450.9f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(619.836f, 463.56f)
    lineTo(613.276f, 470.91f)
    lineTo(617.793f, 479.196f)
    lineTo(609.236f, 475.444f)
    lineTo(602.68604f, 482.804f)
    lineTo(603.958f, 473.136f)
    lineTo(595.394f, 469.39798f)
    lineTo(604.737f, 467.17297f)
    lineTo(605.993f, 457.50296f)
    lineTo(610.495f, 465.79797f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(526.556f, 368.693f)
    cubicTo(526.556f, 368.693f, 565.61804f, 440.425f, 505.46603f, 499.61298f)
    cubicTo(472.39102f, 524.86896f, 440.97003f, 535.083f, 405.52304f, 526.24097f)
    cubicTo(390.22604f, 520.41595f, 376.37305f, 512.18896f, 362.67505f, 503.20096f)
    cubicTo(368.54706f, 502.66095f, 373.87106f, 501.68896f, 378.47305f, 498.82596f)
    cubicTo(386.94507f, 493.84897f, 390.28305f, 485.28296f, 391.98306f, 475.43195f)
    cubicTo(393.53906f, 463.70193f, 397.22305f, 453.20193f, 404.74707f, 441.73993f)
    cubicTo(414.88406f, 425.26593f, 432.78708f, 415.76993f, 451.65207f, 416.30194f)
    cubicTo(463.78607f, 416.40793f, 474.28207f, 421.40994f, 486.78607f, 420.48193f)
    cubicTo(496.79306f, 419.35794f, 504.61407f, 416.51892f, 513.46606f, 407.86893f)
    cubicTo(528.08905f, 389.61893f, 525.0821f, 383.1339f, 526.55804f, 368.69193f)
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
// _0_0_13_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(462.977f, 472.74f)
    lineTo(462.763f, 473.013f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(462.977f, 472.74f)
    lineTo(462.763f, 473.013f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13_2
brush = SolidColor(Color(186, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(528.662f, 381.395f)
    cubicTo(535.67f, 419.722f, 493.564f, 469.228f, 462.71f, 472.53f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(453.298f, 485.45f)
    lineTo(444.402f, 481.216f)
    lineTo(437.71802f, 487.878f)
    lineTo(438.915f, 478.612f)
    lineTo(430.01202f, 474.392f)
    lineTo(439.64902f, 472.898f)
    lineTo(440.829f, 463.62802f)
    lineTo(445.58902f, 471.971f)
    lineTo(455.22302f, 470.463f)
    lineTo(448.52603f, 477.113f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(517.706f, 367.138f)
    cubicTo(517.706f, 367.138f, 463.029f, 427.815f, 387.218f, 390.748f)
    cubicTo(352.408f, 367.942f, 332.33298f, 341.69998f, 328.895f, 305.328f)
    cubicTo(329.30698f, 288.966f, 332.465f, 273.165f, 336.38898f, 257.26f)
    cubicTo(338.84897f, 262.618f, 341.53598f, 267.31702f, 345.76498f, 270.707f)
    cubicTo(353.275f, 277.043f, 362.46198f, 277.34702f, 372.318f, 275.677f)
    cubicTo(383.89798f, 273.247f, 395.02798f, 273.231f, 408.33798f, 276.52f)
    cubicTo(427.244f, 280.608f, 442.14798f, 294.34f, 447.91498f, 312.30798f)
    cubicTo(451.84497f, 323.788f, 450.615f, 335.348f, 455.645f, 346.835f)
    cubicTo(460.03f, 355.9f, 465.30698f, 362.33298f, 476.40698f, 367.80798f)
    cubicTo(498.47897f, 375.53598f, 503.59497f, 370.546f, 517.707f, 367.13797f)
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
// _0_0_14_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(398.446f, 341.743f)
    lineTo(398.118f, 341.631f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(398.446f, 341.743f)
    lineTo(398.118f, 341.631f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_2
brush = SolidColor(Color(186, 0, 0, 255))
stroke = Stroke(width=3.127f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(506.426f, 373.345f)
    cubicTo(472.606f, 392.69f, 411.922f, 369.425f, 398.556f, 341.421f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(383.244f, 336.836f)
    lineTo(384.28f, 327.039f)
    lineTo(375.775f, 322.947f)
    lineTo(384.913f, 320.99698f)
    lineTo(385.93298f, 311.19897f)
    lineTo(390.546f, 319.79196f)
    lineTo(399.681f, 317.82697f)
    lineTo(393.395f, 325.08698f)
    lineTo(398.02f, 333.67197f)
    lineTo(389.52f, 329.56497f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(186, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 1.9650020599365234
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.0028000001329928637
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 510.0350036621094
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 511.9971923828125
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

