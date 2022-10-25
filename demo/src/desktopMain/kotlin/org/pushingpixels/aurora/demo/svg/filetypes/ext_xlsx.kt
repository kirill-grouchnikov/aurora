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
class ext_xlsx : Painter() {
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(72.0f, 99.299995f)
    lineTo(0.0f, 99.299995f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(81, 132, 42, 255), 0.102f to Color(96, 150, 49, 255), 0.222f to Color(109, 167, 55, 255), 0.355f to Color(119, 179, 59, 255), 0.506f to Color(126, 187, 62, 255), 0.69f to Color(131, 193, 64, 255), 1.0f to Color(133, 196, 65, 255), start = Offset(36.0f, 99.25f), end = Offset(36.0f, 0.75f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(72.0f, 99.299995f)
    lineTo(0.0f, 99.299995f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(82, 130, 40, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(72.0f, 99.299995f)
    lineTo(0.0f, 99.299995f)
    lineTo(0.0f, 0.8f)
    lineTo(45.0f, 0.8f)
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
    moveTo(7.3f, 91.3f)
    lineTo(12.700001f, 83.100006f)
    lineTo(7.8000007f, 75.600006f)
    lineTo(11.500001f, 75.600006f)
    lineTo(14.700001f, 80.600006f)
    lineTo(17.800001f, 75.600006f)
    lineTo(21.500002f, 75.600006f)
    lineTo(16.600002f, 83.200005f)
    lineTo(22.000002f, 91.3f)
    lineTo(18.200003f, 91.3f)
    lineTo(14.700003f, 85.9f)
    lineTo(11.200003f, 91.3f)
    lineTo(7.3f, 91.3f)
    close()
    moveTo(23.599998f, 91.3f)
    lineTo(23.599998f, 75.7f)
    lineTo(26.8f, 75.7f)
    lineTo(26.8f, 88.6f)
    lineTo(34.7f, 88.6f)
    lineTo(34.7f, 91.2f)
    lineTo(23.6f, 91.2f)
    close()
    moveTo(36.199997f, 86.200005f)
    lineTo(39.299995f, 85.9f)
    cubicTo(39.499996f, 86.9f, 39.899994f, 87.700005f, 40.399994f, 88.200005f)
    cubicTo(40.999992f, 88.700005f, 41.699993f, 88.9f, 42.699993f, 88.9f)
    cubicTo(43.699993f, 88.9f, 44.499992f, 88.700005f, 44.999992f, 88.3f)
    cubicTo(45.499992f, 87.9f, 45.79999f, 87.4f, 45.79999f, 86.8f)
    cubicTo(45.79999f, 86.4f, 45.699993f, 86.100006f, 45.499992f, 85.9f)
    cubicTo(45.29999f, 85.7f, 44.899994f, 85.4f, 44.399994f, 85.200005f)
    cubicTo(43.999992f, 85.100006f, 43.199993f, 84.8f, 41.899994f, 84.50001f)
    cubicTo(40.199993f, 84.100006f, 38.999992f, 83.600006f, 38.299995f, 82.90001f)
    cubicTo(37.299995f, 82.00001f, 36.799995f, 81.00001f, 36.799995f, 79.70001f)
    cubicTo(36.799995f, 78.90001f, 36.999996f, 78.20001f, 37.499996f, 77.500015f)
    cubicTo(37.999996f, 76.80002f, 38.599995f, 76.30002f, 39.499996f, 75.90002f)
    cubicTo(40.399998f, 75.500015f, 41.399998f, 75.40002f, 42.599995f, 75.40002f)
    cubicTo(44.599995f, 75.40002f, 46.099995f, 75.80002f, 47.099995f, 76.70002f)
    cubicTo(48.099995f, 77.60002f, 48.599995f, 78.70002f, 48.699993f, 80.20002f)
    lineTo(45.499992f, 80.30002f)
    cubicTo(45.399994f, 79.500015f, 45.09999f, 78.90002f, 44.59999f, 78.60002f)
    cubicTo(44.19999f, 78.20002f, 43.499992f, 78.10002f, 42.59999f, 78.10002f)
    cubicTo(41.69999f, 78.10002f, 40.999992f, 78.30002f, 40.39999f, 78.70002f)
    cubicTo(40.09999f, 78.90002f, 39.89999f, 79.30002f, 39.89999f, 79.70002f)
    cubicTo(39.89999f, 80.10002f, 40.09999f, 80.40002f, 40.39999f, 80.70002f)
    cubicTo(40.79999f, 81.00002f, 41.79999f, 81.40002f, 43.29999f, 81.70002f)
    cubicTo(44.79999f, 82.10002f, 45.999992f, 82.40002f, 46.699993f, 82.80002f)
    cubicTo(47.399994f, 83.20002f, 47.999992f, 83.70002f, 48.399994f, 84.40002f)
    cubicTo(48.799995f, 85.10001f, 48.999992f, 85.90002f, 48.999992f, 86.90002f)
    cubicTo(48.999992f, 87.80002f, 48.699993f, 88.60001f, 48.199993f, 89.40002f)
    cubicTo(47.699993f, 90.000015f, 46.999992f, 90.60001f, 46.099995f, 91.000015f)
    cubicTo(45.199993f, 91.40002f, 43.999996f, 91.60001f, 42.699993f, 91.60001f)
    cubicTo(40.699993f, 91.60001f, 39.199993f, 91.10001f, 38.099995f, 90.20001f)
    cubicTo(36.999996f, 89.30001f, 36.399994f, 87.90001f, 36.199993f, 86.20001f)
    close()
    moveTo(50.1f, 91.3f)
    lineTo(55.5f, 83.100006f)
    lineTo(50.6f, 75.600006f)
    lineTo(54.3f, 75.600006f)
    lineTo(57.5f, 80.600006f)
    lineTo(60.6f, 75.600006f)
    lineTo(64.299995f, 75.600006f)
    lineTo(59.399994f, 83.200005f)
    lineTo(64.799995f, 91.3f)
    lineTo(61.0f, 91.3f)
    lineTo(57.5f, 85.9f)
    lineTo(54.0f, 91.3f)
    lineTo(50.1f, 91.3f)
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
    moveTo(57.8f, 61.4f)
    lineTo(35.2f, 61.4f)
    lineTo(35.2f, 59.4f)
    cubicTo(37.4f, 59.300003f, 38.7f, 59.100002f, 39.4f, 58.800003f)
    cubicTo(40.0f, 58.500004f, 40.300003f, 58.200005f, 40.300003f, 57.800003f)
    cubicTo(40.300003f, 57.600002f, 40.200005f, 57.200005f, 40.000004f, 56.700005f)
    cubicTo(39.800003f, 56.200005f, 39.500004f, 55.600006f, 39.100002f, 55.000004f)
    cubicTo(38.500004f, 54.000004f, 37.7f, 52.700005f, 36.7f, 51.200005f)
    cubicTo(35.7f, 49.700005f, 34.600002f, 48.100006f, 33.5f, 46.400005f)
    cubicTo(29.9f, 50.600006f, 27.5f, 53.400005f, 26.6f, 54.800003f)
    cubicTo(25.6f, 56.200005f, 25.1f, 57.100002f, 25.1f, 57.600002f)
    cubicTo(25.1f, 57.800003f, 25.1f, 58.000004f, 25.2f, 58.2f)
    cubicTo(25.300001f, 58.4f, 25.5f, 58.5f, 25.800001f, 58.7f)
    cubicTo(26.000002f, 58.8f, 26.500002f, 59.0f, 27.1f, 59.100002f)
    cubicTo(27.7f, 59.2f, 28.4f, 59.300003f, 29.2f, 59.4f)
    lineTo(29.2f, 61.4f)
    lineTo(14.8f, 61.4f)
    lineTo(14.8f, 59.4f)
    cubicTo(15.5f, 59.300003f, 16.1f, 59.300003f, 16.6f, 59.2f)
    cubicTo(17.0f, 59.100002f, 17.5f, 59.0f, 17.9f, 58.8f)
    cubicTo(18.9f, 58.3f, 19.8f, 57.7f, 20.6f, 57.0f)
    cubicTo(21.400002f, 56.3f, 22.2f, 55.4f, 23.0f, 54.5f)
    cubicTo(24.2f, 53.1f, 25.6f, 51.6f, 27.1f, 49.8f)
    cubicTo(28.6f, 48.1f, 30.2f, 46.2f, 31.900002f, 44.2f)
    cubicTo(30.300001f, 41.8f, 28.7f, 39.4f, 27.100002f, 37.2f)
    cubicTo(25.500002f, 34.9f, 24.100002f, 33.0f, 23.000002f, 31.400002f)
    cubicTo(22.400002f, 30.600002f, 21.800001f, 29.800001f, 20.900002f, 29.000002f)
    cubicTo(20.100002f, 28.200003f, 19.2f, 27.600002f, 18.300001f, 27.300001f)
    cubicTo(17.800001f, 27.1f, 17.300001f, 26.900002f, 16.800001f, 26.800001f)
    cubicTo(16.2f, 26.7f, 15.600001f, 26.6f, 14.800001f, 26.500002f)
    lineTo(14.800001f, 24.500002f)
    lineTo(37.4f, 24.500002f)
    lineTo(37.4f, 26.500002f)
    cubicTo(35.600002f, 26.600002f, 34.300003f, 26.700003f, 33.5f, 27.000002f)
    cubicTo(32.7f, 27.200003f, 32.2f, 27.600002f, 32.2f, 28.100002f)
    cubicTo(32.2f, 28.300003f, 32.3f, 28.700003f, 32.4f, 29.000002f)
    cubicTo(32.600002f, 29.400002f, 32.9f, 30.000002f, 33.4f, 30.800001f)
    cubicTo(33.800003f, 31.500002f, 34.5f, 32.5f, 35.300003f, 33.800003f)
    cubicTo(36.200005f, 35.100002f, 37.200005f, 36.700005f, 38.300003f, 38.4f)
    cubicTo(41.600002f, 34.5f, 43.600002f, 31.900002f, 44.500004f, 30.800001f)
    cubicTo(45.300003f, 29.6f, 45.800003f, 28.800001f, 45.800003f, 28.400002f)
    cubicTo(45.800003f, 27.900002f, 45.600002f, 27.500002f, 45.100002f, 27.300001f)
    cubicTo(44.600002f, 27.000002f, 43.500004f, 26.800001f, 41.7f, 26.7f)
    lineTo(41.7f, 24.7f)
    lineTo(56.1f, 24.7f)
    lineTo(56.1f, 26.7f)
    cubicTo(55.399998f, 26.7f, 54.899998f, 26.800001f, 54.5f, 26.900002f)
    cubicTo(54.1f, 27.000002f, 53.6f, 27.2f, 53.0f, 27.400002f)
    cubicTo(51.9f, 27.900002f, 51.0f, 28.500002f, 50.3f, 29.2f)
    cubicTo(49.6f, 29.900002f, 48.8f, 30.7f, 47.899998f, 31.7f)
    cubicTo(46.6f, 33.100002f, 45.399998f, 34.5f, 44.199997f, 35.9f)
    cubicTo(42.999996f, 37.2f, 41.6f, 38.9f, 39.899998f, 40.800003f)
    cubicTo(41.999996f, 44.000004f, 43.8f, 46.600002f, 45.3f, 48.700005f)
    cubicTo(46.8f, 50.800003f, 48.2f, 52.800003f, 49.6f, 54.700005f)
    cubicTo(50.1f, 55.500004f, 50.8f, 56.300003f, 51.6f, 57.100006f)
    cubicTo(52.399998f, 57.900005f, 53.3f, 58.500008f, 54.199997f, 58.800007f)
    cubicTo(54.6f, 59.000008f, 55.1f, 59.100006f, 55.6f, 59.20001f)
    cubicTo(56.1f, 59.300007f, 56.899998f, 59.40001f, 57.699997f, 59.500008f)
    lineTo(57.699997f, 61.40001f)
    lineTo(57.799995f, 61.40001f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(97, 149, 48, 255), 0.267f to Color(96, 148, 47, 255), 0.443f to Color(91, 142, 45, 255), 0.594f to Color(83, 132, 41, 255), 0.731f to Color(72, 119, 35, 255), 0.858f to Color(58, 103, 27, 255), 0.976f to Color(41, 82, 15, 255), 1.0f to Color(36, 77, 11, 255), start = Offset(16.371f, 63.013f), end = Offset(55.488f, 23.897003f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(242, 245, 213, 255), 0.312f to Color(240, 244, 210, 255), 0.458f to Color(232, 238, 202, 255), 0.569f to Color(221, 231, 189, 255), 0.663f to Color(207, 221, 173, 255), 0.745f to Color(190, 210, 152, 255), 0.82f to Color(169, 196, 129, 255), 0.889f to Color(145, 181, 102, 255), 0.951f to Color(120, 166, 71, 255), 1.0f to Color(97, 153, 50, 255), start = Offset(45.034f, 27.648003f), end = Offset(58.534f, 14.148003f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(82, 130, 40, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
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

