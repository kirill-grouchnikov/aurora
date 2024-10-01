package org.pushingpixels.aurora.demo.svg.filetypes

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
class ext_psd : Painter() {
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.009999999776482582f, 0.0f, 0.0f, 0.0f,
0.0f, 0.009999999776482582f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.13999999687075615f, -0.0f, 0.0f, 1.0f)
))}){
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
    moveTo(45.1f, 1.0f)
    lineTo(72.399994f, 27.9f)
    lineTo(72.399994f, 99.5f)
    lineTo(-0.4f, 99.5f)
    lineTo(-0.4f, 1.0f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(181, 216, 233, 255), 0.264f to Color(178, 214, 232, 255), 0.412f to Color(167, 208, 227, 255), 0.53f to Color(150, 198, 220, 255), 0.633f to Color(125, 184, 210, 255), 0.726f to Color(93, 167, 197, 255), 0.812f to Color(48, 148, 181, 255), 0.892f to Color(0, 127, 163, 255), 0.966f to Color(0, 106, 144, 255), 1.0f to Color(0, 96, 134, 255), start = Offset(36.0f, 0.99900055f), end = Offset(36.0f, 99.483f), tileMode = TileMode.Clamp)
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
    moveTo(45.1f, 1.0f)
    lineTo(72.399994f, 27.9f)
    lineTo(72.399994f, 99.5f)
    lineTo(-0.4f, 99.5f)
    lineTo(-0.4f, 1.0f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(54, 108, 129, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.399994f, 27.9f)
    lineTo(72.399994f, 99.5f)
    lineTo(-0.4f, 99.5f)
    lineTo(-0.4f, 1.0f)
    lineTo(45.1f, 1.0f)
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
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.0f, 91.5f)
    lineTo(9.0f, 71.6f)
    lineTo(15.6f, 71.6f)
    cubicTo(18.1f, 71.6f, 19.7f, 71.7f, 20.5f, 71.9f)
    cubicTo(21.7f, 72.200005f, 22.6f, 72.9f, 23.4f, 73.9f)
    cubicTo(24.199999f, 74.9f, 24.6f, 76.200005f, 24.6f, 77.8f)
    cubicTo(24.6f, 79.0f, 24.4f, 80.100006f, 23.9f, 80.9f)
    cubicTo(23.4f, 81.700005f, 22.9f, 82.4f, 22.199999f, 82.9f)
    cubicTo(21.499998f, 83.4f, 20.8f, 83.700005f, 20.099998f, 83.8f)
    cubicTo(19.099998f, 84.0f, 17.699999f, 84.100006f, 15.899999f, 84.100006f)
    lineTo(13.199999f, 84.100006f)
    lineTo(13.199999f, 91.600006f)
    lineTo(9.0f, 91.600006f)
    close()
    moveTo(13.1f, 74.9f)
    lineTo(13.1f, 80.6f)
    lineTo(15.3f, 80.6f)
    cubicTo(16.9f, 80.6f, 18.0f, 80.5f, 18.5f, 80.299995f)
    cubicTo(19.0f, 80.1f, 19.5f, 79.799995f, 19.8f, 79.299995f)
    cubicTo(20.099998f, 78.799995f, 20.3f, 78.299995f, 20.3f, 77.7f)
    cubicTo(20.3f, 77.0f, 20.099998f, 76.399994f, 19.699999f, 75.899994f)
    cubicTo(19.3f, 75.399994f, 18.699999f, 75.09999f, 18.099998f, 74.99999f)
    cubicTo(17.599998f, 74.899994f, 16.599998f, 74.899994f, 15.199999f, 74.899994f)
    lineTo(13.099998f, 74.899994f)
    close()
    moveTo(26.8f, 85.0f)
    lineTo(30.8f, 84.6f)
    cubicTo(31.0f, 85.9f, 31.5f, 86.9f, 32.3f, 87.5f)
    cubicTo(33.0f, 88.1f, 34.0f, 88.4f, 35.3f, 88.4f)
    cubicTo(36.6f, 88.4f, 37.6f, 88.1f, 38.3f, 87.6f)
    cubicTo(39.0f, 87.1f, 39.3f, 86.4f, 39.3f, 85.7f)
    cubicTo(39.3f, 85.2f, 39.2f, 84.799995f, 38.899998f, 84.5f)
    cubicTo(38.599995f, 84.200005f, 38.1f, 83.9f, 37.399998f, 83.6f)
    cubicTo(36.899998f, 83.4f, 35.8f, 83.1f, 34.1f, 82.7f)
    cubicTo(31.899998f, 82.2f, 30.399998f, 81.5f, 29.499998f, 80.7f)
    cubicTo(28.299997f, 79.6f, 27.599998f, 78.299995f, 27.599998f, 76.7f)
    cubicTo(27.599998f, 75.7f, 27.899998f, 74.7f, 28.499998f, 73.899994f)
    cubicTo(29.099998f, 72.99999f, 29.899998f, 72.299995f, 30.999998f, 71.899994f)
    cubicTo(32.1f, 71.399994f, 33.399998f, 71.2f, 35.0f, 71.2f)
    cubicTo(37.5f, 71.2f, 39.5f, 71.7f, 40.7f, 72.799995f)
    cubicTo(42.0f, 73.899994f, 42.7f, 75.399994f, 42.7f, 77.2f)
    lineTo(38.600002f, 77.399994f)
    cubicTo(38.4f, 76.399994f, 38.100002f, 75.59999f, 37.500004f, 75.2f)
    cubicTo(36.900005f, 74.799995f, 36.100002f, 74.5f, 34.900005f, 74.5f)
    cubicTo(33.700005f, 74.5f, 32.800007f, 74.7f, 32.100006f, 75.2f)
    cubicTo(31.700006f, 75.5f, 31.500006f, 75.899994f, 31.500006f, 76.399994f)
    cubicTo(31.500006f, 76.899994f, 31.700006f, 77.299995f, 32.100006f, 77.59999f)
    cubicTo(32.600006f, 77.99999f, 33.900005f, 78.49999f, 35.900005f, 78.899994f)
    cubicTo(37.900005f, 79.399994f, 39.400005f, 79.799995f, 40.300007f, 80.299995f)
    cubicTo(41.300007f, 80.799995f, 42.000008f, 81.49999f, 42.500008f, 82.299995f)
    cubicTo(43.000008f, 83.2f, 43.300007f, 84.2f, 43.300007f, 85.49999f)
    cubicTo(43.300007f, 86.59999f, 43.000008f, 87.69999f, 42.300007f, 88.69999f)
    cubicTo(41.600006f, 89.69999f, 40.70001f, 90.39999f, 39.600006f, 90.89999f)
    cubicTo(38.400005f, 91.39999f, 37.000008f, 91.59998f, 35.200005f, 91.59998f)
    cubicTo(32.600006f, 91.59998f, 30.700005f, 90.999985f, 29.300005f, 89.89999f)
    cubicTo(27.900005f, 88.999985f, 27.100004f, 87.29999f, 26.800005f, 84.999985f)
    close()
    moveTo(46.699997f, 71.6f)
    lineTo(54.199997f, 71.6f)
    cubicTo(55.899998f, 71.6f, 57.199997f, 71.7f, 58.1f, 72.0f)
    cubicTo(59.3f, 72.3f, 60.3f, 73.0f, 61.199997f, 73.8f)
    cubicTo(61.999996f, 74.700005f, 62.699997f, 75.8f, 63.1f, 77.0f)
    cubicTo(63.5f, 78.3f, 63.8f, 79.8f, 63.8f, 81.7f)
    cubicTo(63.8f, 83.299995f, 63.6f, 84.799995f, 63.2f, 85.899994f)
    cubicTo(62.7f, 87.399994f, 62.0f, 88.49999f, 61.0f, 89.399994f)
    cubicTo(60.3f, 90.09999f, 59.3f, 90.59999f, 58.1f, 90.99999f)
    cubicTo(57.199997f, 91.299995f, 56.0f, 91.399994f, 54.5f, 91.399994f)
    lineTo(46.8f, 91.399994f)
    lineTo(46.8f, 71.6f)
    close()
    moveTo(50.799995f, 74.9f)
    lineTo(50.799995f, 88.1f)
    lineTo(53.899994f, 88.1f)
    cubicTo(54.999992f, 88.1f, 55.899994f, 88.0f, 56.399994f, 87.9f)
    cubicTo(57.099995f, 87.700005f, 57.599995f, 87.5f, 58.099995f, 87.1f)
    cubicTo(58.499996f, 86.7f, 58.899994f, 86.1f, 59.199993f, 85.2f)
    cubicTo(59.499992f, 84.299995f, 59.599995f, 83.1f, 59.599995f, 81.5f)
    cubicTo(59.599995f, 80.0f, 59.499996f, 78.8f, 59.199993f, 78.0f)
    cubicTo(58.89999f, 77.2f, 58.499992f, 76.5f, 57.999992f, 76.1f)
    cubicTo(57.499992f, 75.6f, 56.899994f, 75.299995f, 56.09999f, 75.2f)
    cubicTo(55.499992f, 75.1f, 54.39999f, 75.0f, 52.69999f, 75.0f)
    lineTo(50.799988f, 75.0f)
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
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.399994f, 27.9f)
    lineTo(45.1f, 27.9f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(235, 243, 248, 255), 0.357f to Color(232, 242, 247, 255), 0.494f to Color(223, 237, 244, 255), 0.593f to Color(208, 229, 240, 255), 0.673f to Color(187, 218, 233, 255), 0.743f to Color(160, 204, 225, 255), 0.805f to Color(127, 188, 216, 255), 0.861f to Color(87, 171, 204, 255), 0.913f to Color(11, 153, 191, 255), 0.959f to Color(0, 135, 177, 255), 1.0f to Color(0, 118, 163, 255), start = Offset(45.199f, 27.963997f), end = Offset(58.84f, 14.322998f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.399994f, 27.9f)
    lineTo(45.1f, 27.9f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(52, 106, 128, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.399994f, 27.9f)
    lineTo(45.1f, 27.9f)
    lineTo(45.1f, 1.0f)
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
// _0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(48.9f, 58.2f)
    cubicTo(49.7f, 58.600002f, 48.300003f, 58.600002f, 48.9f, 58.2f)
    close()
    moveTo(50.300003f, 61.100002f)
    cubicTo(51.4f, 63.500004f, 52.9f, 65.4f, 54.000004f, 67.8f)
    cubicTo(54.800003f, 66.9f, 52.600002f, 65.8f, 53.300003f, 64.4f)
    cubicTo(52.300003f, 63.600002f, 52.300003f, 60.7f, 50.300003f, 61.100002f)
    close()
    moveTo(31.7f, 34.5f)
    cubicTo(30.1f, 36.0f, 27.0f, 36.7f, 24.1f, 36.5f)
    cubicTo(24.0f, 37.2f, 24.7f, 37.5f, 25.1f, 38.0f)
    cubicTo(27.800001f, 41.4f, 30.7f, 45.5f, 33.3f, 49.0f)
    cubicTo(37.8f, 48.7f, 40.2f, 50.6f, 43.0f, 51.9f)
    cubicTo(40.7f, 51.300003f, 37.7f, 49.9f, 34.1f, 50.100002f)
    cubicTo(35.6f, 51.7f, 36.3f, 53.800003f, 38.199997f, 55.2f)
    cubicTo(40.399998f, 56.8f, 43.799995f, 55.9f, 46.899998f, 56.8f)
    cubicTo(47.499996f, 56.899998f, 47.499996f, 57.7f, 47.999996f, 57.8f)
    cubicTo(48.499996f, 57.6f, 47.799995f, 56.899998f, 47.499996f, 56.5f)
    cubicTo(38.299995f, 41.9f, 28.899996f, 25.9f, 16.199997f, 14.5f)
    cubicTo(30.899998f, 24.6f, 38.899998f, 41.3f, 48.6f, 56.3f)
    cubicTo(48.699997f, 54.8f, 49.5f, 53.2f, 49.399998f, 51.6f)
    cubicTo(49.3f, 50.1f, 48.499996f, 48.8f, 47.8f, 47.5f)
    cubicTo(47.1f, 46.2f, 46.5f, 44.8f, 45.5f, 44.1f)
    cubicTo(45.4f, 46.5f, 45.7f, 48.5f, 46.2f, 50.3f)
    cubicTo(45.2f, 48.3f, 44.100002f, 45.5f, 44.9f, 42.6f)
    cubicTo(40.2f, 35.6f, 35.4f, 27.599998f, 29.7f, 21.8f)
    cubicTo(29.900002f, 23.3f, 30.300001f, 24.599998f, 30.2f, 26.4f)
    cubicTo(29.5f, 24.8f, 29.5f, 22.6f, 28.900002f, 20.9f)
    cubicTo(26.500002f, 18.5f, 23.800001f, 16.5f, 21.000002f, 14.5f)
    cubicTo(20.600002f, 15.0f, 20.700003f, 15.9f, 20.500002f, 16.6f)
    cubicTo(20.200003f, 15.8f, 20.200003f, 14.700001f, 20.000002f, 13.8f)
    cubicTo(18.400002f, 12.900001f, 16.700003f, 12.0f, 14.700002f, 11.200001f)
    cubicTo(12.900002f, 10.500001f, 10.900002f, 9.200001f, 8.600002f, 10.1f)
    cubicTo(8.500002f, 11.700001f, 9.100002f, 12.6f, 9.300002f, 14.0f)
    cubicTo(10.4000025f, 15.1f, 12.300002f, 15.3f, 14.600002f, 15.1f)
    cubicTo(13.300002f, 15.6f, 11.200003f, 15.5f, 10.000002f, 14.900001f)
    cubicTo(10.200002f, 17.0f, 11.500002f, 18.7f, 12.400002f, 20.400002f)
    cubicTo(15.300001f, 25.500002f, 18.800001f, 30.400002f, 22.600002f, 34.800003f)
    cubicTo(25.500002f, 35.800003f, 29.300003f, 35.600002f, 31.700003f, 34.500004f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(68, 158, 192, 255), 0.188f to Color(62, 156, 189, 255), 0.377f to Color(40, 148, 183, 255), 0.567f to Color(0, 137, 173, 255), 0.758f to Color(0, 122, 159, 255), 0.947f to Color(0, 104, 143, 255), 1.0f to Color(0, 99, 138, 255), start = Offset(54.34503f, 38.848f), end = Offset(8.718018f, 38.848f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 0.12600000202655792
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
            return 0.747999906539917
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 1.0
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

