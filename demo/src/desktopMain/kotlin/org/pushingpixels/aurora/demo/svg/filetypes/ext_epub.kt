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
class ext_epub : Painter() {
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
    moveTo(45.3f, 1.0f)
    lineTo(72.4f, 27.7f)
    lineTo(72.4f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.3f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(116, 45, 45, 255), 0.005f to Color(130, 113, 0, 255), 1.0f to Color(255, 238, 145, 255), start = Offset(36.25f, 98.99701f), end = Offset(36.248993f, 1.0239868f), tileMode = TileMode.Clamp)
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
    moveTo(45.3f, 1.0f)
    lineTo(72.4f, 27.7f)
    lineTo(72.4f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.3f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(130, 113, 0, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.3f, 1.0f)
    lineTo(72.4f, 27.7f)
    lineTo(72.4f, 99.0f)
    lineTo(0.1f, 99.0f)
    lineTo(0.1f, 1.0f)
    lineTo(45.3f, 1.0f)
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
    moveTo(7.2f, 91.6f)
    lineTo(7.2f, 76.0f)
    lineTo(18.9f, 76.0f)
    lineTo(18.9f, 78.6f)
    lineTo(10.4f, 78.6f)
    lineTo(10.4f, 82.1f)
    lineTo(18.3f, 82.1f)
    lineTo(18.3f, 84.7f)
    lineTo(10.4f, 84.7f)
    lineTo(10.4f, 89.0f)
    lineTo(19.2f, 89.0f)
    lineTo(19.2f, 91.6f)
    lineTo(7.200001f, 91.6f)
    close()
    moveTo(21.9f, 91.6f)
    lineTo(21.9f, 76.0f)
    lineTo(27.0f, 76.0f)
    cubicTo(28.9f, 76.0f, 30.2f, 76.1f, 30.8f, 76.2f)
    cubicTo(31.699999f, 76.399994f, 32.5f, 76.899994f, 33.1f, 77.7f)
    cubicTo(33.699997f, 78.5f, 34.0f, 79.5f, 34.0f, 80.7f)
    cubicTo(34.0f, 81.7f, 33.8f, 82.5f, 33.5f, 83.1f)
    cubicTo(33.2f, 83.7f, 32.7f, 84.299995f, 32.2f, 84.6f)
    cubicTo(31.7f, 85.0f, 31.1f, 85.2f, 30.5f, 85.299995f)
    cubicTo(29.7f, 85.399994f, 28.6f, 85.49999f, 27.2f, 85.49999f)
    lineTo(25.1f, 85.49999f)
    lineTo(25.1f, 91.399994f)
    lineTo(21.9f, 91.399994f)
    close()
    moveTo(25.1f, 78.6f)
    lineTo(25.1f, 83.0f)
    lineTo(26.800001f, 83.0f)
    cubicTo(28.1f, 83.0f, 28.900002f, 82.9f, 29.300001f, 82.8f)
    cubicTo(29.7f, 82.600006f, 30.1f, 82.4f, 30.300001f, 82.0f)
    cubicTo(30.500002f, 81.7f, 30.7f, 81.2f, 30.7f, 80.8f)
    cubicTo(30.7f, 80.200005f, 30.5f, 79.8f, 30.2f, 79.4f)
    cubicTo(29.900002f, 79.0f, 29.400002f, 78.8f, 28.900002f, 78.700005f)
    cubicTo(28.500002f, 78.600006f, 27.800001f, 78.600006f, 26.600002f, 78.600006f)
    lineTo(25.100002f, 78.600006f)
    close()
    moveTo(36.6f, 76.0f)
    lineTo(39.8f, 76.0f)
    lineTo(39.8f, 84.4f)
    cubicTo(39.8f, 85.700005f, 39.8f, 86.6f, 39.899998f, 87.0f)
    cubicTo(39.999996f, 87.6f, 40.399998f, 88.1f, 40.899998f, 88.5f)
    cubicTo(41.399998f, 88.9f, 42.1f, 89.1f, 42.999996f, 89.1f)
    cubicTo(43.899998f, 89.1f, 44.599995f, 88.9f, 44.999996f, 88.6f)
    cubicTo(45.499996f, 88.2f, 45.699997f, 87.799995f, 45.799995f, 87.299995f)
    cubicTo(45.899994f, 86.799995f, 45.899994f, 85.899994f, 45.899994f, 84.7f)
    lineTo(45.899994f, 76.0f)
    lineTo(49.099995f, 76.0f)
    lineTo(49.099995f, 84.2f)
    cubicTo(49.099995f, 86.1f, 48.999996f, 87.399994f, 48.799995f, 88.2f)
    cubicTo(48.599995f, 89.0f, 48.299995f, 89.6f, 47.799995f, 90.2f)
    cubicTo(47.299995f, 90.7f, 46.699997f, 91.2f, 45.899994f, 91.5f)
    cubicTo(45.09999f, 91.8f, 44.099995f, 92.0f, 42.899994f, 92.0f)
    cubicTo(41.399994f, 92.0f, 40.199993f, 91.8f, 39.499992f, 91.5f)
    cubicTo(38.699993f, 91.2f, 38.09999f, 90.7f, 37.699993f, 90.2f)
    cubicTo(37.199993f, 89.7f, 36.899994f, 89.1f, 36.79999f, 88.5f)
    cubicTo(36.59999f, 87.6f, 36.499992f, 86.3f, 36.499992f, 84.5f)
    lineTo(36.499992f, 76.0f)
    close()
    moveTo(52.6f, 76.0f)
    lineTo(58.899998f, 76.0f)
    cubicTo(60.1f, 76.0f, 61.1f, 76.1f, 61.699997f, 76.2f)
    cubicTo(62.299995f, 76.299995f, 62.899998f, 76.5f, 63.299995f, 76.799995f)
    cubicTo(63.699993f, 77.09999f, 64.2f, 77.6f, 64.49999f, 78.1f)
    cubicTo(64.799995f, 78.6f, 64.99999f, 79.299995f, 64.99999f, 79.9f)
    cubicTo(64.99999f, 80.6f, 64.799995f, 81.3f, 64.399994f, 81.9f)
    cubicTo(63.999992f, 82.5f, 63.499992f, 83.0f, 62.799995f, 83.3f)
    cubicTo(63.799995f, 83.600006f, 64.49999f, 84.0f, 64.99999f, 84.700005f)
    cubicTo(65.49999f, 85.40001f, 65.799995f, 86.100006f, 65.799995f, 87.00001f)
    cubicTo(65.799995f, 87.700005f, 65.6f, 88.40001f, 65.299995f, 89.100006f)
    cubicTo(64.99999f, 89.8f, 64.49999f, 90.3f, 63.899994f, 90.700005f)
    cubicTo(63.299995f, 91.100006f, 62.599995f, 91.3f, 61.799995f, 91.4f)
    cubicTo(61.299995f, 91.5f, 59.999996f, 91.5f, 57.999996f, 91.5f)
    lineTo(52.599995f, 91.5f)
    lineTo(52.599995f, 76.0f)
    close()
    moveTo(55.8f, 78.6f)
    lineTo(55.8f, 82.2f)
    lineTo(57.899998f, 82.2f)
    cubicTo(59.1f, 82.2f, 59.899998f, 82.2f, 60.199997f, 82.1f)
    cubicTo(60.699997f, 82.0f, 61.199997f, 81.9f, 61.499996f, 81.5f)
    cubicTo(61.799995f, 81.1f, 61.999996f, 80.8f, 61.999996f, 80.3f)
    cubicTo(61.999996f, 79.8f, 61.899998f, 79.4f, 61.599995f, 79.100006f)
    cubicTo(61.299995f, 78.8f, 60.899994f, 78.600006f, 60.399994f, 78.600006f)
    cubicTo(60.099995f, 78.600006f, 59.199993f, 78.50001f, 57.699993f, 78.50001f)
    lineTo(55.79999f, 78.50001f)
    close()
    moveTo(55.8f, 84.799995f)
    lineTo(55.8f, 89.0f)
    lineTo(58.8f, 89.0f)
    cubicTo(59.899998f, 89.0f, 60.7f, 89.0f, 61.0f, 88.9f)
    cubicTo(61.5f, 88.8f, 61.9f, 88.6f, 62.2f, 88.3f)
    cubicTo(62.5f, 88.0f, 62.600002f, 87.5f, 62.600002f, 87.0f)
    cubicTo(62.600002f, 86.5f, 62.500004f, 86.1f, 62.300003f, 85.8f)
    cubicTo(62.100002f, 85.5f, 61.700005f, 85.200005f, 61.300003f, 85.100006f)
    cubicTo(60.9f, 85.00001f, 59.9f, 84.90001f, 58.500004f, 84.90001f)
    lineTo(55.800003f, 84.90001f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(254, 254, 254, 255))
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
    moveTo(62.7f, 34.5f)
    cubicTo(62.4f, 33.8f, 62.0f, 33.3f, 61.4f, 32.8f)
    cubicTo(61.300003f, 33.3f, 61.100002f, 33.899998f, 60.800003f, 34.399998f)
    lineTo(44.4f, 59.6f)
    cubicTo(43.800003f, 60.5f, 42.4f, 60.8f, 41.4f, 60.5f)
    lineTo(15.300001f, 53.2f)
    cubicTo(13.700001f, 52.8f, 11.900002f, 51.9f, 11.800001f, 50.100002f)
    cubicTo(11.700001f, 49.4f, 11.800001f, 49.2f, 12.200001f, 48.9f)
    cubicTo(12.6f, 48.600002f, 13.000001f, 48.7f, 13.400001f, 48.800003f)
    lineTo(38.0f, 55.6f)
    cubicTo(41.6f, 56.6f, 42.6f, 55.899998f, 45.2f, 51.899998f)
    lineTo(60.2f, 28.799997f)
    cubicTo(61.0f, 27.599997f, 61.2f, 26.199997f, 60.7f, 24.999998f)
    cubicTo(60.3f, 23.799997f, 59.3f, 22.899998f, 57.9f, 22.499998f)
    lineTo(36.4f, 16.499998f)
    cubicTo(35.9f, 16.399998f, 35.4f, 16.399998f, 34.9f, 16.299997f)
    lineTo(35.0f, 16.199997f)
    cubicTo(31.7f, 14.199997f, 30.4f, 17.999996f, 28.7f, 19.399998f)
    cubicTo(28.1f, 19.899998f, 27.2f, 20.299997f, 27.0f, 20.799997f)
    cubicTo(26.8f, 21.299997f, 26.9f, 21.799997f, 26.7f, 22.299997f)
    cubicTo(26.1f, 23.699997f, 24.2f, 26.099997f, 23.300001f, 26.799997f)
    cubicTo(22.7f, 27.199997f, 22.000002f, 27.299997f, 21.6f, 27.899998f)
    cubicTo(21.300001f, 28.299997f, 21.4f, 28.999998f, 21.2f, 29.599998f)
    cubicTo(20.7f, 30.899998f, 19.0f, 33.1f, 17.800001f, 34.199997f)
    cubicTo(17.400002f, 34.6f, 16.7f, 34.899998f, 16.400002f, 35.399998f)
    cubicTo(16.100002f, 35.8f, 16.2f, 36.499996f, 15.900002f, 36.999996f)
    cubicTo(15.200002f, 38.399998f, 13.600001f, 40.399998f, 12.300001f, 41.499996f)
    cubicTo(11.600001f, 42.099995f, 10.900002f, 42.399998f, 10.600001f, 43.099995f)
    cubicTo(10.400002f, 43.399994f, 10.600001f, 43.899994f, 10.400002f, 44.299995f)
    cubicTo(10.100001f, 44.999996f, 9.800001f, 45.499996f, 9.600001f, 45.999996f)
    cubicTo(8.900002f, 46.999996f, 8.500001f, 48.199997f, 8.600001f, 49.599995f)
    cubicTo(8.800001f, 52.799995f, 11.300001f, 55.899994f, 14.100001f, 56.699993f)
    lineTo(40.3f, 64.0f)
    cubicTo(42.7f, 64.7f, 45.8f, 63.5f, 47.1f, 61.4f)
    lineTo(62.1f, 38.300003f)
    cubicTo(62.899998f, 37.000004f, 63.1f, 35.700005f, 62.699997f, 34.500004f)
    close()
    moveTo(32.5f, 26.2f)
    lineTo(33.6f, 24.6f)
    cubicTo(33.899998f, 24.1f, 34.6f, 23.9f, 35.0f, 24.0f)
    lineTo(52.2f, 28.8f)
    cubicTo(52.7f, 28.9f, 52.9f, 29.4f, 52.600002f, 29.9f)
    lineTo(51.500004f, 31.5f)
    cubicTo(51.200005f, 32.0f, 50.500004f, 32.2f, 50.100002f, 32.1f)
    lineTo(32.9f, 27.3f)
    cubicTo(32.4f, 27.099998f, 32.2f, 26.599998f, 32.5f, 26.199999f)
    close()
    moveTo(28.1f, 32.600002f)
    lineTo(29.2f, 31.000002f)
    cubicTo(29.5f, 30.500002f, 30.2f, 30.300001f, 30.6f, 30.400002f)
    lineTo(47.800003f, 35.2f)
    cubicTo(48.300003f, 35.3f, 48.500004f, 35.8f, 48.200005f, 36.3f)
    lineTo(47.100006f, 37.899998f)
    cubicTo(46.800007f, 38.399998f, 46.100006f, 38.6f, 45.700005f, 38.499996f)
    lineTo(28.500004f, 33.699997f)
    cubicTo(28.000004f, 33.6f, 27.800003f, 33.1f, 28.100004f, 32.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(170, 148, 0, 255), 1.0f to Color(86, 74, 0, 255), start = Offset(42.348785f, 18.972717f), end = Offset(30.169037f, 62.04364f), tileMode = TileMode.Clamp)
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
    moveTo(45.3f, 1.0f)
    lineTo(72.4f, 27.7f)
    lineTo(45.3f, 27.7f)
    lineTo(45.3f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(254, 234, 134, 255), 1.0f to Color(134, 114, 0, 255), start = Offset(45.376007f, 27.838013f), end = Offset(58.92099f, 14.291016f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
    moveTo(45.3f, 1.0f)
    lineTo(72.4f, 27.7f)
    lineTo(45.3f, 27.7f)
    lineTo(45.3f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(130, 113, 0, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.3f, 1.0f)
    lineTo(72.4f, 27.7f)
    lineTo(45.3f, 27.7f)
    lineTo(45.3f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
            return 0.13099999725818634
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
            return 0.743000328540802
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 0.9999999403953552
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

