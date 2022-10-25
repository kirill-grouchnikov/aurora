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
class sa : Painter() {
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
1.032099962234497f, 0.0f, 0.0f, 0.0f,
0.0f, 1.032099962234497f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-128.0f, 0.0f, 0.0f, 1.0f)
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
    moveTo(0.0f, 0.0f)
    lineTo(744.09f, 0.0f)
    lineTo(744.09f, 496.06f)
    lineTo(0.0f, 496.06f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(25, 157, 0, 255))
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
    moveTo(187.52f, 140.62f)
    cubicTo(186.66101f, 152.209f, 185.63f, 172.603f, 195.477f, 174.69499f)
    cubicTo(207.38701f, 175.84f, 200.82f, 154.53699f, 205.13101f, 150.674f)
    cubicTo(205.94601f, 148.765f, 207.449f, 148.75499f, 207.57301f, 151.163f)
    lineTo(207.57301f, 169.233f)
    cubicTo(207.46402f, 175.10901f, 211.32701f, 176.84f, 214.32802f, 178.054f)
    cubicTo(217.45102f, 177.813f, 219.53601f, 177.917f, 220.76003f, 180.956f)
    lineTo(222.22502f, 212.213f)
    cubicTo(222.22502f, 212.213f, 229.46802f, 214.286f, 229.81302f, 194.632f)
    cubicTo(230.15901f, 183.09201f, 227.50803f, 173.432f, 229.06201f, 171.18701f)
    cubicTo(229.117f, 168.98102f, 231.934f, 168.84901f, 233.88802f, 169.92401f)
    cubicTo(237.00102f, 172.12001f, 238.38802f, 174.83202f, 243.22601f, 173.74701f)
    cubicTo(250.58801f, 171.71901f, 255.01302f, 168.13802f, 255.12201f, 162.48502f)
    cubicTo(254.69202f, 157.11302f, 254.09001f, 151.74002f, 251.76201f, 146.36801f)
    cubicTo(252.087f, 145.391f, 250.341f, 142.86002f, 250.66501f, 141.88301f)
    cubicTo(251.988f, 143.95502f, 254.001f, 143.783f, 254.45901f, 141.88301f)
    cubicTo(253.20601f, 137.75401f, 251.26201f, 133.798f, 248.11002f, 132.08601f)
    cubicTo(245.50502f, 129.79102f, 241.69302f, 130.26001f, 240.29701f, 135.04602f)
    cubicTo(239.65001f, 140.56102f, 242.28502f, 147.11302f, 246.30101f, 152.45502f)
    cubicTo(247.154f, 154.54202f, 248.352f, 158.01001f, 247.82501f, 161.13301f)
    cubicTo(245.68802f, 162.352f, 243.55101f, 161.84401f, 241.76102f, 159.95401f)
    cubicTo(241.76102f, 159.95401f, 235.90102f, 155.559f, 235.90102f, 154.58202f)
    cubicTo(237.45601f, 144.62602f, 236.24602f, 143.49701f, 235.38402f, 140.73401f)
    cubicTo(234.78201f, 136.92001f, 232.97202f, 135.697f, 231.50601f, 133.09201f)
    cubicTo(230.04001f, 131.53801f, 228.057f, 131.53801f, 227.10901f, 133.09201f)
    cubicTo(224.51901f, 137.58202f, 225.727f, 147.22101f, 227.598f, 151.53801f)
    cubicTo(228.95001f, 155.509f, 231.016f, 158.001f, 230.04001f, 158.001f)
    cubicTo(229.23601f, 160.24501f, 227.574f, 159.727f, 226.365f, 157.13701f)
    cubicTo(224.639f, 151.78401f, 224.293f, 143.79701f, 224.293f, 140.20001f)
    cubicTo(223.775f, 135.74101f, 223.206f, 126.23001f, 220.271f, 123.81301f)
    cubicTo(218.481f, 121.37501f, 215.827f, 122.56401f, 214.9f, 124.78901f)
    cubicTo(214.707f, 129.21501f, 214.687f, 133.64001f, 215.185f, 137.71901f)
    cubicTo(217.198f, 144.87302f, 217.83f, 151.16301f, 218.80699f, 158.48901f)
    cubicTo(219.07799f, 168.30202f, 213.133f, 162.74701f, 213.405f, 157.88202f)
    cubicTo(214.776f, 151.56302f, 214.42f, 141.61702f, 213.202f, 139.09602f)
    cubicTo(212.236f, 136.57503f, 211.096f, 135.95302f, 208.747f, 136.36803f)
    cubicTo(206.88199f, 136.25403f, 202.083f, 141.49403f, 200.736f, 150.18602f)
    cubicTo(200.736f, 150.18602f, 199.58699f, 154.66002f, 199.09698f, 158.63202f)
    cubicTo(198.43799f, 163.12102f, 195.48299f, 166.28902f, 193.41098f, 158.00102f)
    cubicTo(191.61998f, 151.97702f, 190.51898f, 137.14702f, 187.51997f, 140.62003f)
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
    moveTo(219.88f, 188.16f)
    cubicTo(209.37001f, 193.298f, 199.205f, 198.091f, 188.866f, 203.056f)
    cubicTo(189.244f, 196.026f, 203.611f, 183.336f, 213.409f, 183.157f)
    cubicTo(219.786f, 183.33499f, 218.18399f, 185.62799f, 219.87999f, 188.16f)
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(214.46f, 197.88f)
    cubicTo(198.115f, 240.013f, 252.73502f, 245.884f, 258.83902f, 199.606f)
    cubicTo(259.41403f, 197.707f, 261.717f, 195.807f, 262.12003f, 198.91501f)
    cubicTo(260.85303f, 240.81702f, 219.87103f, 243.69601f, 212.90703f, 230.514f)
    cubicTo(211.18103f, 227.40701f, 210.66203f, 220.5f, 210.48903f, 216.35501f)
    cubicTo(209.45303f, 208.12502f, 205.13702f, 211.29001f, 204.44603f, 219.46301f)
    cubicTo(203.75504f, 224.01001f, 203.92802f, 225.27802f, 203.92802f, 229.65201f)
    cubicTo(206.11502f, 262.74802f, 258.898f, 248.531f, 267.47302f, 221.19101f)
    cubicTo(272.02002f, 206.052f, 266.72403f, 194.88701f, 269.19904f, 194.94301f)
    cubicTo(274.43704f, 200.585f, 281.74603f, 195.692f, 283.35803f, 193.73502f)
    cubicTo(284.04904f, 192.75601f, 285.77704f, 192.12402f, 286.98505f, 193.39001f)
    cubicTo(291.07104f, 196.32701f, 298.26804f, 194.94301f, 299.76306f, 189.76302f)
    cubicTo(300.62708f, 184.69801f, 301.31805f, 179.46002f, 301.49106f, 174.05101f)
    cubicTo(298.15106f, 175.08801f, 295.67706f, 175.77701f, 295.44705f, 177.15901f)
    lineTo(294.75604f, 181.64801f)
    cubicTo(294.46906f, 183.08801f, 291.58905f, 183.14401f, 291.47504f, 181.30202f)
    cubicTo(290.20905f, 175.54602f, 284.97104f, 174.79802f, 281.80502f, 183.72102f)
    cubicTo(279.67502f, 185.44702f, 275.81903f, 185.79303f, 275.415f, 183.20302f)
    cubicTo(275.933f, 177.21701f, 273.515f, 176.41002f, 268.681f, 179.23102f)
    cubicTo(267.127f, 167.37402f, 265.574f, 156.03502f, 264.019f, 144.17801f)
    cubicTo(266.03403f, 144.12001f, 267.877f, 145.61601f, 269.71902f, 143.31401f)
    cubicTo(267.70203f, 137.04102f, 263.44403f, 124.20501f, 261.08304f, 123.28401f)
    cubicTo(259.93204f, 121.903015f, 258.95203f, 122.766014f, 257.45703f, 123.111015f)
    cubicTo(254.92503f, 123.917015f, 252.56403f, 126.10402f, 253.31203f, 130.36401f)
    cubicTo(256.30502f, 148.55202f, 258.26303f, 162.42401f, 261.256f, 180.613f)
    cubicTo(261.716f, 182.74301f, 259.932f, 185.561f, 257.63f, 185.274f)
    cubicTo(253.716f, 182.626f, 252.74f, 177.274f, 246.061f, 177.503f)
    cubicTo(241.226f, 177.561f, 235.699f, 182.79901f, 235.01001f, 187.86601f)
    cubicTo(234.20401f, 191.893f, 233.91501f, 196.26701f, 235.00801f, 199.779f)
    cubicTo(238.406f, 203.865f, 242.492f, 203.464f, 246.061f, 202.542f)
    cubicTo(248.996f, 201.33401f, 251.41301f, 198.39801f, 252.45001f, 199.08801f)
    cubicTo(253.141f, 199.95201f, 252.62102f, 209.62001f, 238.63501f, 217.04602f)
    cubicTo(230.17401f, 220.84502f, 223.43901f, 221.70901f, 219.815f, 214.80103f)
    cubicTo(217.57f, 210.48402f, 219.987f, 194.08002f, 214.46f, 197.88002f)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(283.79f, 155.05f)
    cubicTo(287.071f, 153.841f, 302.614f, 136.056f, 302.614f, 136.056f)
    cubicTo(301.807f, 135.365f, 301.087f, 134.847f, 300.281f, 134.156f)
    cubicTo(299.417f, 133.408f, 299.504f, 132.66f, 300.281f, 131.91101f)
    cubicTo(304.137f, 129.667f, 302.9f, 124.74501f, 300.88602f, 122.50101f)
    cubicTo(297.54703f, 121.004005f, 294.64f, 121.493004f, 292.51f, 122.587006f)
    cubicTo(289.80502f, 125.177f, 289.173f, 129.321f, 291.303f, 131.91101f)
    cubicTo(293.376f, 132.89001f, 295.44702f, 134.99101f, 294.065f, 136.14201f)
    cubicTo(287.706f, 142.93402f, 270.294f, 154.64702f, 272.309f, 155.05002f)
    cubicTo(272.73898f, 155.62602f, 283.44598f, 155.59702f, 283.78998f, 155.05002f)
    close()
    moveTo(189.93f, 217.99f)
    cubicTo(184.101f, 227.276f, 183.594f, 241.149f, 186.81f, 245.285f)
    cubicTo(188.519f, 247.239f, 191.327f, 248.095f, 193.40399f, 247.483f)
    cubicTo(197.066f, 245.895f, 198.66899f, 238.475f, 197.79999f, 235.761f)
    cubicTo(196.57799f, 233.848f, 195.61499f, 233.545f, 194.39499f, 235.172f)
    cubicTo(191.818f, 240.40399f, 190.74799f, 236.815f, 190.52199f, 233.89099f)
    cubicTo(190.12698f, 228.346f, 190.64899f, 223.23898f, 191.25099f, 219.19499f)
    cubicTo(191.892f, 215.04999f, 191.23999f, 216.31699f, 189.93f, 217.98999f)
    close()
    moveTo(439.02f, 203.12f)
    cubicTo(433.384f, 190.989f, 425.582f, 179.00099f, 423.099f, 174.396f)
    cubicTo(420.617f, 169.789f, 401.876f, 142.591f, 399.117f, 139.532f)
    cubicTo(393.029f, 132.297f, 409.003f, 142.545f, 397.096f, 128.181f)
    cubicTo(392.556f, 124.286f, 392.29202f, 124.048996f, 388.52402f, 120.871f)
    cubicTo(386.62302f, 119.522f, 381.98203f, 117.058f, 381.16f, 121.142006f)
    cubicTo(380.747f, 124.743004f, 380.97f, 126.69601f, 381.575f, 129.694f)
    cubicTo(382.039f, 131.693f, 384.95102f, 135.041f, 386.38303f, 136.981f)
    cubicTo(405.39703f, 162.539f, 422.25702f, 188.35f, 438.51602f, 220.802f)
    cubicTo(441.083f, 219.582f, 440.52203f, 205.15f, 439.02002f, 203.12f)
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
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(414.28f, 243.71f)
    cubicTo(413.172f, 244.95401f, 417.016f, 250.27501f, 422.012f, 250.27f)
    cubicTo(430.367f, 249.3f, 437.722f, 244.60701f, 444.53198f, 232.253f)
    cubicTo(446.353f, 229.37201f, 449.554f, 223.21f, 449.64798f, 218.43001f)
    cubicTo(450.28497f, 190.40501f, 448.24496f, 168.598f, 444.046f, 148.34302f)
    cubicTo(443.77698f, 146.37001f, 443.94098f, 144.05103f, 444.275f, 143.45901f)
    cubicTo(444.81598f, 142.81302f, 446.651f, 143.46202f, 447.624f, 141.86601f)
    cubicTo(449.052f, 140.40802f, 443.832f, 128.33101f, 440.856f, 123.679016f)
    cubicTo(439.79898f, 121.60201f, 439.431f, 120.21502f, 437.68198f, 123.92302f)
    cubicTo(435.84198f, 126.93702f, 434.606f, 132.19601f, 434.75098f, 137.11002f)
    cubicTo(438.73697f, 164.70502f, 439.96097f, 188.84601f, 442.56598f, 216.44202f)
    cubicTo(442.77798f, 219.11102f, 442.386f, 222.98901f, 440.61197f, 224.53502f)
    cubicTo(434.04996f, 231.38702f, 424.57898f, 239.82101f, 414.27997f, 243.71002f)
    close()
    moveTo(527.14f, 243.56f)
    cubicTo(521.14404f, 247.026f, 521.137f, 251.013f, 525.985f, 251.157f)
    cubicTo(534.33997f, 250.187f, 544.212f, 249.493f, 551.022f, 239.212f)
    cubicTo(552.844f, 236.33101f, 555.008f, 228.54001f, 555.102f, 223.76001f)
    cubicTo(555.739f, 195.73502f, 554.73596f, 174.81702f, 550.537f, 154.56201f)
    cubicTo(550.268f, 152.589f, 549.39496f, 148.048f, 549.729f, 147.45601f)
    cubicTo(550.27f, 146.06902f, 552.994f, 147.60701f, 553.967f, 146.011f)
    cubicTo(555.39496f, 144.55301f, 546.91595f, 133.661f, 543.94f, 129.009f)
    cubicTo(542.883f, 126.932f, 542.515f, 125.545006f, 540.766f, 129.253f)
    cubicTo(538.92596f, 132.267f, 538.283f, 137.67401f, 539.02f, 142.44f)
    cubicTo(543.45f, 172.405f, 546.74805f, 194.917f, 547.42804f, 221.47601f)
    cubicTo(547.04706f, 223.99701f, 546.952f, 225.35602f, 545.77f, 228.53201f)
    cubicTo(543.155f, 231.88402f, 540.258f, 236.07501f, 537.541f, 238.10101f)
    cubicTo(534.825f, 240.126f, 529.03f, 242.059f, 527.14f, 243.56001f)
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
    moveTo(531.64f, 216.7f)
    cubicTo(531.57104f, 209.692f, 531.739f, 203.64099f, 531.508f, 198.414f)
    cubicTo(531.278f, 193.187f, 530.354f, 188.931f, 528.545f, 185.22101f)
    cubicTo(526.833f, 181.24301f, 527.896f, 178.046f, 527.092f, 173.809f)
    cubicTo(526.289f, 169.573f, 526.487f, 163.229f, 525.273f, 158.20201f)
    cubicTo(524.94f, 156.23901f, 523.92f, 149.95201f, 524.235f, 149.34901f)
    cubicTo(524.73f, 147.94601f, 526.615f, 149.39302f, 527.536f, 147.76701f)
    cubicTo(528.91504f, 146.264f, 522.753f, 130.32f, 519.627f, 125.76701f)
    cubicTo(518.503f, 123.72601f, 516.46204f, 124.42501f, 513.945f, 127.74401f)
    cubicTo(511.611f, 129.92801f, 512.477f, 134.90901f, 513.368f, 139.64801f)
    cubicTo(519.363f, 170.934f, 523.83496f, 199.251f, 522.859f, 229.03302f)
    cubicTo(522.56f, 231.56502f, 531.702f, 221.48003f, 531.64f, 216.70001f)
    close()
    moveTo(487.34f, 177.89f)
    cubicTo(483.569f, 177.821f, 475.671f, 170.552f, 473.366f, 166.293f)
    cubicTo(472.495f, 163.85f, 473.054f, 161.467f, 473.822f, 160.071f)
    cubicTo(475.218f, 159.163f, 477.366f, 158.143f, 478.972f, 159.121f)
    cubicTo(478.972f, 159.121f, 480.637f, 161.451f, 480.31497f, 161.74701f)
    cubicTo(482.37396f, 162.733f, 483.248f, 162.16602f, 483.45697f, 161.328f)
    cubicTo(483.597f, 159.862f, 482.84796f, 158.988f, 482.83997f, 157.373f)
    cubicTo(483.71295f, 152.993f, 488.71896f, 152.312f, 490.60397f, 155.105f)
    cubicTo(491.98398f, 156.808f, 492.48996f, 160.426f, 492.69897f, 162.86899f)
    cubicTo(492.67697f, 164.11699f, 490.65598f, 162.65298f, 489.50797f, 162.95299f)
    cubicTo(488.35898f, 163.25398f, 488.08298f, 164.579f, 487.99396f, 165.77798f)
    cubicTo(487.78595f, 168.95398f, 487.40994f, 174.04999f, 487.33997f, 177.88998f)
    close()
    moveTo(417.75f, 224.47f)
    cubicTo(418.789f, 214.948f, 417.366f, 197.99f, 417.271f, 192.371f)
    cubicTo(416.88998f, 179.097f, 414.722f, 153.459f, 413.69f, 149.176f)
    cubicTo(412.527f, 141.10199f, 417.01102f, 150.062f, 416.39f, 145.36899f)
    cubicTo(414.936f, 137.30598f, 410.46402f, 131.84099f, 405.207f, 124.455986f)
    cubicTo(403.512f, 122.053986f, 403.569f, 121.56399f, 400.952f, 125.04498f)
    cubicTo(398.056f, 131.61499f, 400.556f, 136.13399f, 401.304f, 141.24998f)
    cubicTo(405.09497f, 157.91998f, 407.30698f, 173.25899f, 408.33698f, 188.42198f)
    cubicTo(409.36697f, 203.58598f, 409.68298f, 219.97998f, 408.74298f, 235.94998f)
    cubicTo(411.58398f, 236.06198f, 416.15198f, 231.35399f, 417.74997f, 224.46999f)
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
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(544.47f, 209.22f)
    cubicTo(537.821f, 198.063f, 527.787f, 185.98f, 525.10596f, 181.487f)
    cubicTo(522.42596f, 176.993f, 499.75397f, 147.741f, 496.86496f, 144.805f)
    cubicTo(488.57095f, 136.092f, 500.66895f, 143.387f, 495.27695f, 136.657f)
    cubicTo(490.71695f, 131.65f, 489.38693f, 130.078f, 485.48395f, 127.067f)
    cubicTo(483.52695f, 125.802f, 482.34094f, 123.387f, 481.69794f, 127.503f)
    cubicTo(481.44193f, 131.119f, 481.17493f, 135.302f, 481.41794f, 138.35f)
    cubicTo(481.40393f, 140.044f, 483.16794f, 143.227f, 484.68295f, 145.10301f)
    cubicTo(504.79196f, 169.809f, 526.725f, 195.03601f, 544.38196f, 226.74902f)
    cubicTo(546.894f, 225.41803f, 546.05896f, 211.18303f, 544.47f, 209.22003f)
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
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(242.79f, 188.63f)
    cubicTo(242.31099f, 189.46901f, 241.24599f, 190.556f, 241.603f, 191.681f)
    cubicTo(242.34999f, 192.693f, 242.946f, 192.898f, 244.19f, 192.953f)
    cubicTo(245.269f, 192.953f, 246.77701f, 193.20801f, 247.101f, 192.571f)
    cubicTo(247.68199f, 191.931f, 248.124f, 190.625f, 247.64f, 189.392f)
    cubicTo(246.516f, 186.583f, 243.377f, 187.63f, 242.79f, 188.63f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(27, 164, 0, 255))
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
    moveTo(467.16f, 351.26f)
    cubicTo(476.052f, 351.587f, 481.872f, 351.66602f, 489.781f, 352.607f)
    cubicTo(489.781f, 352.607f, 496.601f, 351.93298f, 499.03702f, 351.565f)
    cubicTo(509.33203f, 350.583f, 509.78802f, 366.269f, 509.78802f, 366.269f)
    cubicTo(509.67502f, 375.46902f, 506.12402f, 375.95f, 501.59103f, 376.922f)
    cubicTo(499.00104f, 377.25198f, 497.64005f, 375.371f, 496.27902f, 373.368f)
    cubicTo(494.57f, 374.083f, 492.24802f, 374.186f, 489.43402f, 373.79602f)
    cubicTo(485.72903f, 373.56403f, 482.023f, 373.579f, 478.31802f, 373.34702f)
    cubicTo(474.381f, 373.00003f, 472.28702f, 373.75702f, 468.35004f, 373.41f)
    cubicTo(467.53903f, 374.684f, 466.48404f, 376.448f, 464.07605f, 375.88f)
    cubicTo(462.07306f, 375.659f, 459.70004f, 370.035f, 460.39905f, 365.76f)
    cubicTo(461.84506f, 362.694f, 461.44806f, 363.68002f, 461.29706f, 362.333f)
    cubicTo(424.94006f, 361.407f, 388.23505f, 359.786f, 352.57706f, 360.249f)
    cubicTo(324.67307f, 360.365f, 297.11505f, 361.52298f, 269.55707f, 362.681f)
    cubicTo(254.85207f, 362.449f, 243.62106f, 360.133f, 235.86307f, 348.786f)
    cubicTo(236.55807f, 348.786f, 273.37805f, 350.87003f, 284.14706f, 350.17502f)
    cubicTo(304.06207f, 349.94302f, 322.24005f, 348.32303f, 342.50305f, 347.74402f)
    cubicTo(382.45004f, 348.43802f, 422.05005f, 348.43903f, 461.99304f, 351.217f)
    cubicTo(458.17203f, 348.603f, 458.03503f, 342.428f, 463.91605f, 340.919f)
    cubicTo(464.41605f, 340.575f, 464.67404f, 343.989f, 465.55405f, 343.92502f)
    cubicTo(470.25903f, 343.57202f, 468.19604f, 349.946f, 467.16104f, 351.26f)
    close()
    moveTo(306.78f, 131.07f)
    cubicTo(300.726f, 148.373f, 310.248f, 167.3f, 316.85f, 165.457f)
    cubicTo(321.613f, 167.429f, 324.646f, 158.368f, 326.596f, 148.441f)
    cubicTo(327.93002f, 145.655f, 328.93802f, 145.358f, 329.62302f, 146.79f)
    cubicTo(329.44702f, 159.991f, 330.571f, 162.915f, 333.96503f, 166.922f)
    cubicTo(341.53604f, 172.763f, 347.799f, 167.668f, 348.29004f, 167.176f)
    lineTo(354.18503f, 161.28099f)
    cubicTo(355.49704f, 159.90099f, 357.24402f, 159.81999f, 359.09702f, 161.036f)
    cubicTo(360.898f, 162.67299f, 360.64502f, 165.50099f, 364.501f, 167.46199f)
    cubicTo(367.746f, 168.76f, 374.68402f, 167.762f, 376.29102f, 164.96599f)
    cubicTo(378.454f, 161.25899f, 378.97302f, 159.986f, 379.975f, 158.57999f)
    cubicTo(381.519f, 156.525f, 384.151f, 157.43898f, 384.151f, 158.08798f)
    cubicTo(383.905f, 159.23398f, 382.36002f, 160.38098f, 383.414f, 162.44498f)
    cubicTo(385.25f, 163.82198f, 385.674f, 162.93597f, 386.76f, 162.62997f)
    cubicTo(390.601f, 160.79398f, 393.48502f, 152.43898f, 393.48502f, 152.43898f)
    cubicTo(393.65503f, 149.33098f, 391.91302f, 149.58798f, 390.78302f, 150.22798f)
    cubicTo(389.30902f, 151.12898f, 389.213f, 151.41698f, 387.739f, 152.31798f)
    cubicTo(385.86102f, 152.59698f, 382.21802f, 153.84198f, 380.41702f, 151.05298f)
    cubicTo(378.57803f, 147.69998f, 378.553f, 143.02197f, 377.14703f, 139.63998f)
    cubicTo(377.14703f, 139.39398f, 374.70703f, 134.31299f, 376.97803f, 133.98799f)
    cubicTo(378.12402f, 134.20099f, 380.56903f, 134.84799f, 380.95804f, 132.78899f)
    cubicTo(382.16003f, 130.78198f, 378.38403f, 125.09898f, 375.80005f, 122.22699f)
    cubicTo(373.55704f, 119.76499f, 370.44904f, 119.46799f, 367.44904f, 121.98199f)
    cubicTo(365.34802f, 123.914986f, 365.65002f, 126.07499f, 365.23804f, 128.122f)
    cubicTo(364.70303f, 130.47299f, 364.81705f, 133.36499f, 367.20303f, 136.47299f)
    cubicTo(369.30002f, 140.60799f, 373.12604f, 145.933f, 371.87003f, 153.42099f)
    cubicTo(371.87003f, 153.42099f, 369.63504f, 156.95999f, 365.74103f, 156.497f)
    cubicTo(364.11804f, 156.14299f, 361.48602f, 155.454f, 360.08002f, 145.06999f)
    cubicTo(359.01602f, 137.20999f, 360.33102f, 126.21299f, 356.99503f, 121.05699f)
    cubicTo(355.79004f, 117.94399f, 354.91003f, 114.93799f, 351.97403f, 120.26199f)
    cubicTo(351.18503f, 122.353f, 347.79904f, 125.52599f, 350.25504f, 132.05199f)
    cubicTo(352.26404f, 136.18999f, 353.08203f, 142.92398f, 352.17004f, 150.41599f)
    cubicTo(350.77805f, 152.54498f, 350.46906f, 153.26599f, 348.64404f, 155.39499f)
    cubicTo(346.08005f, 158.152f, 343.29803f, 157.44798f, 341.16705f, 156.41899f)
    cubicTo(339.17606f, 155.077f, 337.61707f, 154.383f, 336.70804f, 150.12f)
    cubicTo(336.87204f, 143.325f, 337.25104f, 132.202f, 336.00903f, 129.842f)
    cubicTo(334.17804f, 126.187f, 331.15802f, 127.508995f, 329.86804f, 128.61299f)
    cubicTo(323.67004f, 134.28f, 320.60803f, 143.842f, 318.73703f, 151.456f)
    cubicTo(317.01804f, 157.00499f, 315.18903f, 155.415f, 313.903f, 153.176f)
    cubicTo(310.772f, 150.24199f, 310.56003f, 127.298f, 306.78003f, 131.06999f)
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
    moveTo(325.01f, 168.68f)
    cubicTo(327.759f, 166.73299f, 326.474f, 165.372f, 330.58502f, 169.482f)
    cubicTo(335.73203f, 178.282f, 339.04f, 189.678f, 339.536f, 199.775f)
    cubicTo(339.319f, 202.263f, 341.073f, 203.838f, 341.875f, 203.297f)
    cubicTo(342.352f, 197.456f, 356.556f, 189.317f, 369.581f, 188.127f)
    cubicTo(371.573f, 187.694f, 370.604f, 183.877f, 370.929f, 181.93f)
    cubicTo(370.14798f, 174.69899f, 374.99197f, 168.116f, 381.78198f, 167.59099f)
    cubicTo(391.02298f, 168.95699f, 394.09897f, 173.89099f, 394.25497f, 181.42198f)
    cubicTo(393.257f, 195.87997f, 378.19598f, 198.33098f, 369.73398f, 199.48398f)
    cubicTo(368.43597f, 199.97498f, 367.895f, 200.57397f, 369.73398f, 201.28198f)
    lineTo(405.18997f, 201.44298f)
    lineTo(406.99896f, 202.48798f)
    cubicTo(407.21597f, 203.33298f, 406.48196f, 202.62798f, 405.08197f, 204.93298f)
    cubicTo(403.68298f, 207.23798f, 401.61896f, 212.55298f, 401.51196f, 216.10199f)
    cubicTo(390.94797f, 219.499f, 380.02197f, 220.98698f, 368.91696f, 222.32999f)
    cubicTo(365.05896f, 224.28099f, 363.14697f, 226.88098f, 363.93896f, 229.80699f)
    cubicTo(365.23697f, 233.05199f, 373.78296f, 236.29399f, 373.78296f, 236.448f)
    cubicTo(375.40594f, 237.466f, 377.32596f, 239.841f, 373.32394f, 244.708f)
    cubicTo(356.02893f, 243.94699f, 342.62695f, 236.58699f, 337.98694f, 226.19899f)
    cubicTo(336.58893f, 225.11499f, 335.08295f, 226.193f, 334.11795f, 227.59698f)
    cubicTo(327.36594f, 236.30098f, 320.72195f, 244.13998f, 309.20996f, 248.30098f)
    cubicTo(302.34497f, 250.01598f, 295.31696f, 247.24799f, 291.99796f, 242.75198f)
    cubicTo(289.77597f, 240.19298f, 289.86096f, 237.36798f, 289.04495f, 236.75598f)
    cubicTo(285.33496f, 238.39699f, 253.40495f, 251.96399f, 257.45197f, 245.64398f)
    cubicTo(265.22296f, 237.32698f, 278.67596f, 231.21698f, 290.55698f, 223.00798f)
    cubicTo(291.41397f, 220.25798f, 292.97296f, 210.95297f, 297.666f, 207.92297f)
    cubicTo(297.93698f, 207.94597f, 296.922f, 213.38698f, 297.024f, 215.68198f)
    cubicTo(297.077f, 217.56598f, 296.886f, 218.30397f, 297.29797f, 217.81897f)
    cubicTo(298.09998f, 217.30797f, 312.51596f, 205.97997f, 313.62997f, 202.50597f)
    cubicTo(315.03598f, 200.51396f, 314.05197f, 195.46896f, 314.05197f, 195.31596f)
    cubicTo(311.34796f, 188.34796f, 307.55597f, 187.75597f, 306.14996f, 184.29396f)
    cubicTo(304.88397f, 179.69496f, 305.45697f, 174.44496f, 308.08597f, 172.98196f)
    cubicTo(310.41998f, 170.86296f, 313.18497f, 171.12497f, 315.73495f, 173.44096f)
    cubicTo(318.64694f, 176.04897f, 321.23294f, 181.14597f, 321.98096f, 184.94296f)
    cubicTo(321.48096f, 186.44496f, 318.17096f, 183.94496f, 317.02194f, 184.68996f)
    cubicTo(319.05493f, 186.79495f, 320.00394f, 189.22296f, 320.73795f, 192.19395f)
    cubicTo(322.61594f, 200.14696f, 322.04294f, 203.23895f, 320.15295f, 208.38196f)
    cubicTo(313.75195f, 221.83997f, 305.57196f, 225.85596f, 298.41495f, 230.84096f)
    cubicTo(298.22394f, 230.90895f, 298.09695f, 234.25595f, 300.78894f, 236.06596f)
    cubicTo(301.71695f, 237.03996f, 305.44995f, 237.53996f, 309.83994f, 236.13396f)
    cubicTo(318.32193f, 231.50795f, 327.12793f, 222.98695f, 331.49893f, 213.49396f)
    cubicTo(332.76794f, 206.31296f, 331.00793f, 198.69896f, 329.14093f, 192.05896f)
    cubicTo(326.32794f, 185.56895f, 323.01492f, 176.30296f, 323.01492f, 176.14996f)
    cubicTo(322.9059f, 172.10297f, 323.23492f, 170.69896f, 325.00992f, 168.67996f)
    close()
    moveTo(232.18f, 131.25f)
    cubicTo(236.26099f, 133.196f, 243.933f, 132.368f, 243.609f, 125.788f)
    cubicTo(243.609f, 125.206f, 243.461f, 123.237f, 243.403f, 122.704f)
    cubicTo(242.57f, 120.764f, 240.297f, 121.243004f, 239.782f, 123.246f)
    cubicTo(239.62f, 123.902f, 240.071f, 124.97f, 239.477f, 125.3f)
    cubicTo(239.136f, 125.644005f, 237.832f, 125.442f, 237.886f, 123.624f)
    cubicTo(237.886f, 123.045f, 237.459f, 122.422f, 237.202f, 122.055f)
    cubicTo(236.944f, 121.886f, 236.78f, 121.839f, 236.31299f, 121.839f)
    cubicTo(235.74399f, 121.862f, 235.75299f, 122.009995f, 235.441f, 122.488f)
    cubicTo(235.30899f, 122.975f, 235.12599f, 123.451f, 235.12599f, 124.003f)
    cubicTo(235.055f, 124.65f, 234.81099f, 124.881f, 234.33499f, 124.976f)
    cubicTo(233.803f, 124.976f, 233.92299f, 125.034996f, 233.49199f, 124.759995f)
    cubicTo(233.236f, 124.479996f, 232.91599f, 124.37299f, 232.91599f, 123.895f)
    cubicTo(232.91599f, 123.399994f, 232.80399f, 122.599f, 232.65498f, 122.271996f)
    cubicTo(232.42899f, 121.974f, 232.06598f, 121.836f, 231.65698f, 121.730995f)
    cubicTo(229.42798f, 121.739f, 229.27399f, 124.281f, 229.40198f, 125.245995f)
    cubicTo(229.23497f, 125.42799f, 229.13998f, 129.98799f, 232.17998f, 131.25f)
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
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(351.81f, 181.89f)
    cubicTo(355.891f, 183.836f, 365.605f, 182.716f, 363.23898f, 176.428f)
    cubicTo(363.23898f, 175.846f, 363.09097f, 173.877f, 363.033f, 173.344f)
    cubicTo(362.19998f, 171.40399f, 359.927f, 171.883f, 359.412f, 173.886f)
    cubicTo(359.25f, 174.542f, 359.701f, 175.61f, 359.107f, 175.94f)
    cubicTo(358.766f, 176.284f, 357.462f, 176.082f, 357.516f, 174.264f)
    cubicTo(357.516f, 173.68501f, 357.089f, 173.06201f, 356.832f, 172.695f)
    cubicTo(356.574f, 172.526f, 356.41f, 172.479f, 355.943f, 172.479f)
    cubicTo(355.374f, 172.502f, 355.383f, 172.65001f, 355.07098f, 173.128f)
    cubicTo(354.939f, 173.615f, 354.75598f, 174.091f, 354.75598f, 174.643f)
    cubicTo(354.68497f, 175.29001f, 354.44098f, 175.52101f, 353.965f, 175.61601f)
    cubicTo(353.43298f, 175.61601f, 353.553f, 175.67502f, 353.122f, 175.40001f)
    cubicTo(352.866f, 175.12001f, 352.54602f, 175.01302f, 352.54602f, 174.535f)
    cubicTo(352.54602f, 174.04001f, 352.43402f, 173.239f, 352.28503f, 172.912f)
    cubicTo(352.05902f, 172.614f, 351.69604f, 172.476f, 351.28705f, 172.371f)
    cubicTo(349.05804f, 172.379f, 348.90405f, 174.921f, 349.03204f, 175.886f)
    cubicTo(348.86505f, 176.06801f, 348.77005f, 180.628f, 351.81006f, 181.89f)
    close()
    moveTo(421.56f, 160.93f)
    cubicTo(425.641f, 162.87599f, 433.313f, 162.04799f, 432.98898f, 155.46799f)
    cubicTo(432.98898f, 154.88599f, 432.84097f, 152.91699f, 432.783f, 152.38399f)
    cubicTo(431.94998f, 150.44398f, 429.677f, 150.92299f, 429.162f, 152.926f)
    cubicTo(429.0f, 153.582f, 429.451f, 154.65f, 428.857f, 154.98f)
    cubicTo(428.516f, 155.32399f, 427.212f, 155.122f, 427.266f, 153.304f)
    cubicTo(427.266f, 152.725f, 426.839f, 152.102f, 426.582f, 151.735f)
    cubicTo(426.324f, 151.566f, 426.16f, 151.519f, 425.693f, 151.519f)
    cubicTo(425.124f, 151.54199f, 425.133f, 151.69f, 424.82098f, 152.168f)
    cubicTo(424.689f, 152.655f, 424.50598f, 153.131f, 424.50598f, 153.683f)
    cubicTo(424.43497f, 154.33f, 424.19098f, 154.561f, 423.715f, 154.656f)
    cubicTo(423.18298f, 154.656f, 423.303f, 154.71501f, 422.872f, 154.44f)
    cubicTo(422.616f, 154.16f, 422.29602f, 154.05301f, 422.29602f, 153.575f)
    cubicTo(422.29602f, 153.08f, 422.18402f, 152.27899f, 422.03503f, 151.952f)
    cubicTo(421.80902f, 151.65399f, 421.44604f, 151.51599f, 421.03705f, 151.411f)
    cubicTo(418.80804f, 151.41899f, 418.65405f, 153.961f, 418.78204f, 154.926f)
    cubicTo(418.61505f, 155.108f, 418.52005f, 159.668f, 421.56006f, 160.93f)
    close()
    moveTo(457.69f, 213.56f)
    cubicTo(450.578f, 221.575f, 453.714f, 234.832f, 455.32f, 237.688f)
    cubicTo(457.665f, 242.379f, 459.553f, 245.388f, 464.116f, 247.70801f)
    cubicTo(468.272f, 250.76701f, 471.51f, 248.85501f, 473.29498f, 246.71301f)
    cubicTo(477.477f, 242.37901f, 477.52698f, 231.31401f, 479.49f, 229.12102f)
    cubicTo(480.867f, 225.09302f, 484.33398f, 225.78102f, 486.017f, 227.56602f)
    cubicTo(487.648f, 229.91103f, 489.569f, 231.42302f, 491.965f, 232.69902f)
    cubicTo(495.866f, 236.14001f, 500.524f, 236.76903f, 505.113f, 233.63303f)
    cubicTo(508.249f, 231.87402f, 510.288f, 229.60103f, 512.124f, 225.08902f)
    cubicTo(514.164f, 219.63303f, 513.02704f, 194.42603f, 512.619f, 179.48602f)
    cubicTo(512.46204f, 178.31403f, 508.56503f, 158.94102f, 508.56503f, 158.72502f)
    cubicTo(508.56503f, 158.50803f, 508.05002f, 148.83803f, 507.62103f, 146.53403f)
    cubicTo(507.54602f, 145.59903f, 507.31403f, 145.33003f, 508.29404f, 145.44902f)
    cubicTo(509.33304f, 146.32301f, 509.47504f, 146.37802f, 510.12402f, 146.66602f)
    cubicTo(511.17303f, 146.85802f, 512.112f, 145.07301f, 511.47903f, 143.43002f)
    lineTo(501.74405f, 125.47402f)
    cubicTo(500.96304f, 124.705025f, 499.95404f, 123.86002f, 498.71506f, 125.691025f)
    cubicTo(497.53107f, 126.72903f, 496.27005f, 128.60803f, 496.30905f, 131.02303f)
    cubicTo(496.59705f, 135.27803f, 497.34506f, 139.60803f, 497.63306f, 143.86302f)
    lineTo(501.52805f, 165.71303f)
    cubicTo(502.75406f, 181.28903f, 503.06104f, 194.03603f, 504.28705f, 209.61203f)
    cubicTo(504.11505f, 216.20703f, 502.06506f, 221.95703f, 500.14206f, 222.78703f)
    cubicTo(500.14206f, 222.78703f, 497.21606f, 224.48303f, 495.25406f, 222.61003f)
    cubicTo(493.82706f, 222.03703f, 488.11505f, 213.09103f, 488.11505f, 213.09103f)
    cubicTo(485.19504f, 210.41403f, 483.27005f, 211.17903f, 481.19205f, 213.09103f)
    cubicTo(475.46204f, 218.62404f, 472.86905f, 228.97504f, 468.97504f, 236.11403f)
    cubicTo(467.97104f, 237.70703f, 465.13303f, 239.07103f, 461.98904f, 235.99803f)
    cubicTo(454.00406f, 225.08904f, 458.68405f, 209.56903f, 457.69003f, 213.56003f)
    close()
    moveTo(423.4f, 122.72f)
    cubicTo(427.056f, 124.25f, 429.63498f, 131.656f, 428.794f, 135.272f)
    cubicTo(428.066f, 139.74501f, 426.127f, 144.576f, 424.733f, 143.947f)
    cubicTo(423.218f, 143.38501f, 425.766f, 139.501f, 424.31f, 135.425f)
    cubicTo(423.501f, 132.772f, 418.512f, 127.925f, 419.037f, 126.496f)
    cubicTo(418.007f, 123.502f, 421.158f, 122.192f, 423.4f, 122.72f)
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
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(469.46f, 217.96f)
    cubicTo(470.229f, 209.067f, 468.931f, 203.64401f, 468.69998f, 198.417f)
    cubicTo(468.46997f, 193.19f, 462.788f, 153.307f, 461.636f, 149.352f)
    cubicTo(460.24597f, 141.87401f, 467.159f, 148.349f, 466.404f, 144.0f)
    cubicTo(464.012f, 138.517f, 458.06f, 130.533f, 456.19098f, 125.770004f)
    cubicTo(455.067f, 123.729004f, 455.53897f, 121.914f, 453.02197f, 125.233f)
    cubicTo(450.68796f, 132.864f, 449.87796f, 139.101f, 450.76898f, 143.84f)
    cubicTo(456.76398f, 175.12599f, 462.912f, 201.13899f, 461.93597f, 230.92099f)
    cubicTo(464.77896f, 230.93999f, 468.05597f, 224.41599f, 469.45996f, 217.95999f)
    close()
    moveTo(531.93f, 135.33f)
    cubicTo(535.263f, 136.987f, 537.21497f, 146.268f, 536.847f, 148.919f)
    cubicTo(536.18396f, 153.76201f, 534.41595f, 158.992f, 533.14496f, 158.311f)
    cubicTo(531.764f, 157.703f, 533.42194f, 151.129f, 532.759f, 149.085f)
    cubicTo(532.022f, 146.21301f, 527.474f, 140.96501f, 527.95197f, 139.418f)
    cubicTo(527.01294f, 136.177f, 529.886f, 134.759f, 531.93f, 135.33f)
    close()
    moveTo(284.22f, 201.14f)
    cubicTo(287.408f, 202.357f, 289.277f, 209.177f, 288.925f, 211.126f)
    cubicTo(288.289f, 214.684f, 286.597f, 218.52701f, 285.383f, 218.02701f)
    cubicTo(284.061f, 217.58f, 285.648f, 212.74901f, 285.013f, 211.24701f)
    cubicTo(284.744f, 207.604f, 280.319f, 205.718f, 280.412f, 204.14401f)
    cubicTo(279.586f, 201.251f, 282.263f, 200.71901f, 284.22f, 201.14001f)
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
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(361.25f, 211.38f)
    cubicTo(365.365f, 211.641f, 367.421f, 214.87001f, 363.567f, 216.224f)
    cubicTo(359.76498f, 217.526f, 356.111f, 218.543f, 356.094f, 224.037f)
    cubicTo(357.5f, 231.69301f, 354.165f, 229.06601f, 352.176f, 228.026f)
    cubicTo(349.832f, 226.343f, 343.254f, 222.291f, 342.317f, 213.541f)
    cubicTo(342.176f, 211.453f, 343.804f, 209.696f, 346.425f, 209.70801f)
    cubicTo(350.373f, 210.781f, 356.198f, 210.85901f, 361.25f, 211.38f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(27, 157, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(199.01f, 120.52f)
    cubicTo(203.71399f, 121.937996f, 203.99199f, 128.85199f, 203.64499f, 130.873f)
    cubicTo(203.01799f, 134.562f, 201.35199f, 138.546f, 200.15498f, 138.02701f)
    cubicTo(198.85298f, 137.56401f, 200.10399f, 132.55602f, 199.47899f, 130.998f)
    cubicTo(198.78198f, 128.81f, 194.80699f, 124.813f, 195.258f, 123.635f)
    cubicTo(194.373f, 121.165f, 197.082f, 120.084f, 199.01f, 120.520004f)
    close()
    moveTo(291.9f, 153.12f)
    cubicTo(288.291f, 155.07199f, 286.891f, 160.89099f, 289.138f, 164.27899f)
    cubicTo(291.237f, 167.26299f, 294.552f, 166.157f, 294.994f, 166.157f)
    cubicTo(298.53f, 166.599f, 300.628f, 159.528f, 300.628f, 159.528f)
    cubicTo(300.628f, 159.528f, 300.73898f, 157.54f, 296.53998f, 161.297f)
    cubicTo(294.77298f, 161.62799f, 294.55197f, 160.965f, 294.11f, 159.97f)
    cubicTo(293.74197f, 158.129f, 293.81497f, 156.288f, 294.662f, 154.446f)
    cubicTo(295.288f, 152.679f, 293.925f, 151.905f, 291.9f, 153.12f)
    close()
    moveTo(318.97f, 117.84f)
    cubicTo(317.156f, 119.056f, 313.546f, 122.796f, 313.436f, 127.104996f)
    cubicTo(313.32602f, 129.536f, 312.87302f, 129.52899f, 314.465f, 131.07599f)
    cubicTo(315.615f, 132.734f, 316.775f, 132.58499f, 319.096f, 131.36899f)
    cubicTo(320.42902f, 130.387f, 320.88f, 129.73499f, 321.329f, 128.08798f)
    cubicTo(321.88202f, 125.32598f, 318.409f, 129.39798f, 317.967f, 126.32398f)
    cubicTo(317.194f, 123.469986f, 319.424f, 122.30198f, 321.523f, 119.539986f)
    cubicTo(321.59302f, 117.64799f, 321.552f, 116.30798f, 318.97f, 117.83999f)
    close()
    moveTo(340.78f, 121.72f)
    cubicTo(339.998f, 123.447f, 339.061f, 132.468f, 339.217f, 132.468f)
    cubicTo(338.59302f, 135.156f, 342.03f, 136.306f, 343.59302f, 132.851f)
    cubicTo(345.936f, 126.517f, 345.936f, 123.83f, 346.09302f, 121.143f)
    cubicTo(345.363f, 117.049995f, 342.60303f, 117.178f, 340.78003f, 121.72f)
    close()
    moveTo(478.28f, 191.69f)
    cubicTo(478.749f, 191.22101f, 497.656f, 177.784f, 497.656f, 177.784f)
    cubicTo(499.583f, 177.107f, 499.16602f, 184.711f, 498.281f, 184.659f)
    cubicTo(498.64502f, 186.16899f, 479.635f, 199.086f, 478.28f, 198.565f)
    cubicTo(477.343f, 199.242f, 476.405f, 193.357f, 478.28f, 191.69f)
    close()
    moveTo(495.52f, 191.58f)
    cubicTo(498.853f, 193.237f, 500.18f, 202.987f, 499.81198f, 205.638f)
    cubicTo(499.93f, 210.794f, 496.59998f, 214.93f, 495.32898f, 214.249f)
    cubicTo(493.94797f, 213.64099f, 495.44998f, 207.84799f, 494.787f, 205.80399f)
    cubicTo(494.05f, 202.93199f, 491.22f, 197.52798f, 491.698f, 195.98099f)
    cubicTo(490.759f, 192.73999f, 493.476f, 191.00899f, 495.52f, 191.57999f)
    close()
    moveTo(383.06f, 233.56f)
    cubicTo(384.375f, 231.638f, 388.437f, 228.872f, 388.529f, 228.872f)
    cubicTo(390.40298f, 227.93399f, 392.23398f, 229.607f, 392.123f, 229.497f)
    cubicTo(392.435f, 231.372f, 390.93698f, 233.11699f, 391.40598f, 235.618f)
    cubicTo(391.81598f, 236.62599f, 392.11597f, 237.745f, 393.96298f, 237.318f)
    cubicTo(396.965f, 234.956f, 399.74597f, 234.80399f, 402.748f, 234.653f)
    cubicTo(405.04898f, 234.792f, 405.13998f, 238.687f, 403.685f, 238.716f)
    cubicTo(398.142f, 239.921f, 395.658f, 241.405f, 391.7f, 242.916f)
    cubicTo(389.825f, 244.009f, 388.216f, 242.622f, 388.216f, 242.466f)
    cubicTo(388.216f, 242.31001f, 387.11902f, 241.40201f, 387.885f, 238.918f)
    cubicTo(388.021f, 236.926f, 387.21902f, 235.834f, 385.56f, 236.06f)
    cubicTo(384.31f, 236.736f, 383.20898f, 237.18399f, 382.58398f, 235.747f)
    cubicTo(382.33798f, 234.68599f, 382.258f, 234.17f, 383.06f, 233.56f)
    close()
    moveTo(515.42f, 238.81f)
    cubicTo(516.23f, 239.841f, 516.75397f, 240.795f, 515.355f, 242.489f)
    cubicTo(514.029f, 243.704f, 513.095f, 244.373f, 511.76898f, 245.588f)
    cubicTo(511.14297f, 246.65599f, 510.74298f, 248.276f, 512.65796f, 248.79199f)
    cubicTo(516.194f, 249.786f, 524.36993f, 244.48299f, 524.36993f, 244.372f)
    cubicTo(525.6959f, 243.37799f, 525.2539f, 241.5f, 525.14294f, 241.5f)
    cubicTo(524.36993f, 240.616f, 522.6279f, 241.141f, 521.45593f, 240.998f)
    cubicTo(520.89795f, 240.998f, 519.06793f, 240.72101f, 519.9389f, 239.101f)
    cubicTo(520.6659f, 238.093f, 520.9259f, 237.476f, 521.4189f, 236.234f)
    cubicTo(521.9709f, 235.019f, 521.4969f, 234.208f, 519.5079f, 233.545f)
    cubicTo(517.4819f, 233.177f, 516.67285f, 233.361f, 514.4259f, 233.545f)
    cubicTo(513.2109f, 233.803f, 512.7959f, 234.346f, 512.5749f, 235.819f)
    cubicTo(512.66187f, 238.053f, 514.0209f, 237.926f, 515.41986f, 238.81f)
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
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(383.784f, 183.827f)
    cubicTo(383.26498f, 184.72299f, 381.513f, 184.679f, 379.869f, 183.72899f)
    cubicTo(378.22498f, 182.77898f, 377.314f, 181.28099f, 377.83298f, 180.38399f)
    cubicTo(378.35196f, 179.48698f, 380.10297f, 179.53198f, 381.74698f, 180.482f)
    cubicTo(383.391f, 181.432f, 384.30197f, 182.93f, 383.78397f, 183.827f)
    close()
    moveTo(297.484f, 131.908f)
    cubicTo(296.502f, 132.14801f, 295.21802f, 131.27301f, 294.617f, 129.953f)
    cubicTo(294.016f, 128.633f, 294.324f, 127.367004f, 295.307f, 127.126f)
    cubicTo(296.29f, 126.884995f, 297.573f, 127.761f, 298.174f, 129.082f)
    cubicTo(298.77502f, 130.403f, 298.467f, 131.668f, 297.484f, 131.908f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(37, 159, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(468.2f, 363.3f)
    cubicTo(477.26f, 363.74197f, 485.772f, 363.40298f, 494.832f, 363.845f)
    cubicTo(496.473f, 365.24f, 495.301f, 368.66602f, 494.207f, 368.42f)
    cubicTo(491.261f, 368.346f, 489.564f, 368.273f, 486.618f, 368.199f)
    cubicTo(486.517f, 365.31302f, 479.152f, 365.78702f, 479.364f, 368.29102f)
    cubicTo(475.38702f, 368.769f, 471.80002f, 368.152f, 467.823f, 368.005f)
    cubicTo(466.647f, 366.54102f, 466.798f, 363.905f, 468.2f, 363.30002f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(32, 144, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 0.0010338516440242529
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
            return 511.9835205078125
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 511.9834899902344
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

