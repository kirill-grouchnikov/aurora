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
class ext_docx : Painter() {
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(0, 45, 68, 255), 0.056f to Color(1, 56, 82, 255), 0.16f to Color(10, 77, 107, 255), 0.274f to Color(15, 94, 130, 255), 0.398f to Color(15, 109, 150, 255), 0.539f to Color(13, 119, 164, 255), 0.711f to Color(10, 126, 174, 255), 1.0f to Color(8, 129, 178, 255), start = Offset(36.0f, 98.995f), end = Offset(36.0f, 1.0f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(1, 65, 94, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(72.0f, 99.0f)
    lineTo(0.0f, 99.0f)
    lineTo(0.0f, 1.0f)
    lineTo(45.0f, 1.0f)
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
    moveTo(5.4f, 75.3f)
    lineTo(11.3f, 75.3f)
    cubicTo(12.6f, 75.3f, 13.6f, 75.4f, 14.3f, 75.600006f)
    cubicTo(15.2f, 75.90001f, 16.0f, 76.40001f, 16.7f, 77.100006f)
    cubicTo(17.400002f, 77.8f, 17.900002f, 78.600006f, 18.2f, 79.700005f)
    cubicTo(18.5f, 80.700005f, 18.7f, 81.9f, 18.7f, 83.4f)
    cubicTo(18.7f, 84.700005f, 18.5f, 85.8f, 18.2f, 86.8f)
    cubicTo(17.800001f, 87.9f, 17.2f, 88.9f, 16.5f, 89.600006f)
    cubicTo(15.9f, 90.100006f, 15.2f, 90.600006f, 14.2f, 90.90001f)
    cubicTo(13.5f, 91.100006f, 12.5f, 91.20001f, 11.4f, 91.20001f)
    lineTo(5.3999996f, 91.20001f)
    lineTo(5.3999996f, 75.3f)
    close()
    moveTo(8.6f, 78.0f)
    lineTo(8.6f, 88.4f)
    lineTo(11.0f, 88.4f)
    cubicTo(11.9f, 88.4f, 12.5f, 88.4f, 12.9f, 88.200005f)
    cubicTo(13.4f, 88.100006f, 13.9f, 87.9f, 14.2f, 87.50001f)
    cubicTo(14.5f, 87.10001f, 14.8f, 86.700005f, 15.0f, 86.00001f)
    cubicTo(15.2f, 85.30001f, 15.3f, 84.30001f, 15.3f, 83.100006f)
    cubicTo(15.3f, 81.9f, 15.2f, 80.90001f, 15.0f, 80.3f)
    cubicTo(14.8f, 79.600006f, 14.5f, 79.100006f, 14.1f, 78.8f)
    cubicTo(13.700001f, 78.5f, 13.200001f, 78.200005f, 12.6f, 78.100006f)
    cubicTo(12.3f, 78.00001f, 11.400001f, 78.00001f, 10.1f, 78.00001f)
    lineTo(8.6f, 78.00001f)
    close()
    moveTo(20.8f, 83.3f)
    cubicTo(20.8f, 81.700005f, 21.0f, 80.3f, 21.5f, 79.3f)
    cubicTo(21.9f, 78.5f, 22.4f, 77.8f, 23.0f, 77.100006f)
    cubicTo(23.6f, 76.50001f, 24.3f, 76.00001f, 25.1f, 75.700005f)
    cubicTo(26.1f, 75.3f, 27.2f, 75.100006f, 28.5f, 75.100006f)
    cubicTo(30.8f, 75.100006f, 32.7f, 75.8f, 34.1f, 77.3f)
    cubicTo(35.499996f, 78.8f, 36.199997f, 80.700005f, 36.199997f, 83.3f)
    cubicTo(36.199997f, 85.8f, 35.499996f, 87.8f, 34.1f, 89.3f)
    cubicTo(32.699997f, 90.700005f, 30.8f, 91.5f, 28.499998f, 91.5f)
    cubicTo(26.099998f, 91.5f, 24.3f, 90.8f, 22.899998f, 89.4f)
    cubicTo(21.499998f, 87.8f, 20.799997f, 85.8f, 20.799997f, 83.3f)
    close()
    moveTo(24.099998f, 83.200005f)
    cubicTo(24.099998f, 85.00001f, 24.499998f, 86.3f, 25.3f, 87.3f)
    cubicTo(26.099998f, 88.200005f, 27.199999f, 88.700005f, 28.5f, 88.700005f)
    cubicTo(29.8f, 88.700005f, 30.8f, 88.200005f, 31.7f, 87.3f)
    cubicTo(32.5f, 86.4f, 32.9f, 85.0f, 32.9f, 83.200005f)
    cubicTo(32.9f, 81.4f, 32.5f, 80.00001f, 31.7f, 79.200005f)
    cubicTo(30.900002f, 78.4f, 29.800001f, 77.9f, 28.5f, 77.9f)
    cubicTo(27.2f, 77.9f, 26.1f, 78.3f, 25.3f, 79.200005f)
    cubicTo(24.5f, 80.00001f, 24.099998f, 81.4f, 24.099998f, 83.200005f)
    close()
    moveTo(48.899998f, 85.3f)
    lineTo(51.999996f, 86.3f)
    cubicTo(51.499996f, 88.0f, 50.699997f, 89.3f, 49.599995f, 90.100006f)
    cubicTo(48.499996f, 90.90001f, 47.099995f, 91.40001f, 45.399994f, 91.40001f)
    cubicTo(43.299995f, 91.40001f, 41.599995f, 90.70001f, 40.199993f, 89.30001f)
    cubicTo(38.79999f, 87.90001f, 38.199993f, 85.90001f, 38.199993f, 83.40001f)
    cubicTo(38.199993f, 80.80001f, 38.899994f, 78.70001f, 40.29999f, 77.30001f)
    cubicTo(41.699993f, 75.90001f, 43.499992f, 75.10001f, 45.699993f, 75.10001f)
    cubicTo(47.599995f, 75.10001f, 49.199993f, 75.70001f, 50.399994f, 76.80001f)
    cubicTo(51.099995f, 77.50001f, 51.699993f, 78.40001f, 51.999992f, 79.70001f)
    lineTo(48.79999f, 80.500015f)
    cubicTo(48.59999f, 79.70001f, 48.199993f, 79.000015f, 47.59999f, 78.60001f)
    cubicTo(46.999992f, 78.10001f, 46.29999f, 77.90002f, 45.39999f, 77.90002f)
    cubicTo(44.19999f, 77.90002f, 43.29999f, 78.30002f, 42.49999f, 79.20002f)
    cubicTo(41.799988f, 80.00002f, 41.39999f, 81.40002f, 41.39999f, 83.30002f)
    cubicTo(41.39999f, 85.30002f, 41.79999f, 86.70002f, 42.49999f, 87.60002f)
    cubicTo(43.199986f, 88.50002f, 44.19999f, 88.900024f, 45.299988f, 88.900024f)
    cubicTo(46.19999f, 88.900024f, 46.899986f, 88.60002f, 47.49999f, 88.10002f)
    cubicTo(48.19999f, 87.30002f, 48.69999f, 86.400024f, 48.89999f, 85.30002f)
    close()
    moveTo(53.199997f, 91.100006f)
    lineTo(58.6f, 82.90001f)
    lineTo(53.699997f, 75.40001f)
    lineTo(57.499996f, 75.40001f)
    lineTo(60.699997f, 80.50001f)
    lineTo(63.799995f, 75.40001f)
    lineTo(67.49999f, 75.40001f)
    lineTo(62.499992f, 83.00001f)
    lineTo(67.899994f, 91.100006f)
    lineTo(64.0f, 91.100006f)
    lineTo(60.5f, 85.700005f)
    lineTo(57.0f, 91.100006f)
    lineTo(53.2f, 91.100006f)
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
    moveTo(64.4f, 25.7f)
    cubicTo(63.9f, 25.7f, 63.4f, 25.900002f, 63.0f, 26.0f)
    cubicTo(62.5f, 26.2f, 62.0f, 26.4f, 61.6f, 26.6f)
    cubicTo(60.899998f, 27.0f, 60.399998f, 27.4f, 60.1f, 27.9f)
    cubicTo(59.8f, 28.4f, 59.5f, 29.0f, 59.3f, 29.6f)
    cubicTo(58.399998f, 32.0f, 57.1f, 35.6f, 55.3f, 40.9f)
    cubicTo(53.5f, 46.0f, 51.7f, 51.4f, 49.7f, 56.9f)
    lineTo(45.0f, 56.9f)
    lineTo(36.3f, 32.5f)
    lineTo(28.4f, 56.9f)
    lineTo(23.7f, 56.9f)
    cubicTo(21.1f, 49.4f, 19.1f, 43.600002f, 17.7f, 39.7f)
    cubicTo(16.300001f, 35.600002f, 15.200001f, 32.3f, 14.200001f, 29.5f)
    cubicTo(13.900001f, 28.7f, 13.6f, 28.1f, 13.200001f, 27.7f)
    cubicTo(12.800001f, 27.300001f, 12.300001f, 26.900002f, 11.800001f, 26.5f)
    cubicTo(11.400002f, 26.2f, 10.900002f, 26.0f, 10.500001f, 25.9f)
    cubicTo(10.000001f, 25.8f, 9.500001f, 25.699999f, 8.900001f, 25.6f)
    lineTo(8.900001f, 23.800001f)
    lineTo(26.5f, 23.800001f)
    lineTo(26.5f, 25.6f)
    cubicTo(25.5f, 25.7f, 24.7f, 25.800001f, 24.2f, 26.0f)
    cubicTo(23.7f, 26.1f, 23.300001f, 26.2f, 23.0f, 26.4f)
    cubicTo(22.7f, 26.5f, 22.5f, 26.699999f, 22.5f, 26.8f)
    cubicTo(22.4f, 26.9f, 22.4f, 27.099998f, 22.4f, 27.199999f)
    cubicTo(22.4f, 27.4f, 22.4f, 27.599998f, 22.5f, 27.8f)
    cubicTo(22.6f, 28.0f, 22.6f, 28.199999f, 22.7f, 28.5f)
    cubicTo(23.1f, 29.7f, 23.800001f, 32.0f, 25.0f, 35.3f)
    cubicTo(26.1f, 38.7f, 27.5f, 42.8f, 29.2f, 47.8f)
    lineTo(36.7f, 24.0f)
    lineTo(41.600002f, 24.0f)
    lineTo(50.300003f, 48.5f)
    cubicTo(51.600002f, 44.5f, 52.600002f, 41.3f, 53.4f, 38.8f)
    cubicTo(54.2f, 36.3f, 54.800003f, 34.1f, 55.300003f, 32.399998f)
    cubicTo(55.600002f, 31.299997f, 55.9f, 30.399998f, 56.000004f, 29.599998f)
    cubicTo(56.200005f, 28.899998f, 56.300003f, 28.199999f, 56.300003f, 27.699999f)
    cubicTo(56.300003f, 27.4f, 56.100002f, 27.099998f, 55.800003f, 26.9f)
    cubicTo(55.500004f, 26.699999f, 55.100002f, 26.5f, 54.600002f, 26.3f)
    cubicTo(54.2f, 26.199999f, 53.600002f, 26.0f, 52.9f, 26.0f)
    cubicTo(52.2f, 25.9f, 51.600002f, 25.8f, 51.100002f, 25.8f)
    lineTo(51.100002f, 23.8f)
    lineTo(64.4f, 23.8f)
    lineTo(64.4f, 25.699999f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(0, 97, 134, 255), 0.116f to Color(0, 95, 131, 255), 0.239f to Color(0, 90, 125, 255), 0.369f to Color(0, 83, 115, 255), 0.502f to Color(0, 72, 101, 255), 0.639f to Color(0, 57, 83, 255), 0.779f to Color(0, 37, 60, 255), 0.918f to Color(0, 3, 29, 255), 1.0f to Color(0, 0, 0, 255), start = Offset(18.121f, 51.532f), end = Offset(55.041f, 14.611f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(223, 241, 250, 255), 0.3f to Color(220, 238, 247, 255), 0.443f to Color(211, 228, 239, 255), 0.553f to Color(196, 214, 227, 255), 0.647f to Color(177, 197, 213, 255), 0.73f to Color(154, 176, 197, 255), 0.805f to Color(129, 154, 179, 255), 0.875f to Color(100, 130, 159, 255), 0.938f to Color(66, 108, 140, 255), 0.998f to Color(6, 87, 122, 255), 1.0f to Color(1, 86, 121, 255), start = Offset(45.069f, 27.794998f), end = Offset(58.569f, 14.294998f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(1, 65, 94, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.0f)
    lineTo(72.0f, 27.7f)
    lineTo(45.0f, 27.7f)
    lineTo(45.0f, 1.0f)
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
            return 0.12999999523162842
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
            return 0.7400000095367432
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

