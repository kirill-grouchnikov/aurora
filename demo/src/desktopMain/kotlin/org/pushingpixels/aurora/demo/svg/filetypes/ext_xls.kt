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
class ext_xls : Painter() {
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
    moveTo(45.0f, 0.7f)
    lineTo(72.0f, 27.6f)
    lineTo(72.0f, 99.2f)
    lineTo(0.0f, 99.2f)
    lineTo(0.0f, 0.7f)
    lineTo(45.0f, 0.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(81, 132, 42, 255), 0.102f to Color(96, 150, 49, 255), 0.222f to Color(109, 167, 55, 255), 0.355f to Color(119, 179, 59, 255), 0.506f to Color(126, 187, 62, 255), 0.69f to Color(131, 193, 64, 255), 1.0f to Color(133, 196, 65, 255), start = Offset(36.0f, 99.235f), end = Offset(36.0f, 0.7350006f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.7f)
    lineTo(72.0f, 27.6f)
    lineTo(72.0f, 99.2f)
    lineTo(0.0f, 99.2f)
    lineTo(0.0f, 0.7f)
    lineTo(45.0f, 0.7f)
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
    moveTo(45.0f, 0.7f)
    lineTo(72.0f, 27.6f)
    lineTo(72.0f, 99.2f)
    lineTo(0.0f, 99.2f)
    lineTo(0.0f, 0.7f)
    lineTo(45.0f, 0.7f)
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
    moveTo(8.4f, 91.3f)
    lineTo(15.2f, 80.9f)
    lineTo(9.0f, 71.4f)
    lineTo(13.7f, 71.4f)
    lineTo(17.7f, 77.8f)
    lineTo(21.6f, 71.4f)
    lineTo(26.3f, 71.4f)
    lineTo(20.2f, 81.0f)
    lineTo(27.0f, 91.3f)
    lineTo(22.1f, 91.3f)
    lineTo(17.7f, 84.4f)
    lineTo(13.200001f, 91.3f)
    lineTo(8.4f, 91.3f)
    close()
    moveTo(29.199999f, 91.3f)
    lineTo(29.199999f, 71.5f)
    lineTo(33.199997f, 71.5f)
    lineTo(33.199997f, 87.9f)
    lineTo(43.299995f, 87.9f)
    lineTo(43.299995f, 91.3f)
    lineTo(29.2f, 91.3f)
    close()
    moveTo(45.199997f, 84.8f)
    lineTo(49.1f, 84.4f)
    cubicTo(49.3f, 85.700005f, 49.8f, 86.700005f, 50.5f, 87.3f)
    cubicTo(51.2f, 87.9f, 52.2f, 88.200005f, 53.4f, 88.200005f)
    cubicTo(54.7f, 88.200005f, 55.7f, 87.9f, 56.300003f, 87.4f)
    cubicTo(56.900005f, 86.9f, 57.300003f, 86.200005f, 57.300003f, 85.5f)
    cubicTo(57.300003f, 85.0f, 57.200005f, 84.6f, 56.9f, 84.3f)
    cubicTo(56.600002f, 84.0f, 56.100002f, 83.700005f, 55.4f, 83.4f)
    cubicTo(54.9f, 83.200005f, 53.800003f, 82.9f, 52.2f, 82.5f)
    cubicTo(50.0f, 82.0f, 48.5f, 81.3f, 47.600002f, 80.5f)
    cubicTo(46.4f, 79.4f, 45.800003f, 78.1f, 45.800003f, 76.5f)
    cubicTo(45.800003f, 75.5f, 46.100002f, 74.5f, 46.700005f, 73.7f)
    cubicTo(47.300003f, 72.799995f, 48.100006f, 72.1f, 49.200005f, 71.7f)
    cubicTo(50.300003f, 71.2f, 51.600006f, 71.0f, 53.100006f, 71.0f)
    cubicTo(55.600006f, 71.0f, 57.500008f, 71.5f, 58.800007f, 72.6f)
    cubicTo(60.100006f, 73.7f, 60.70001f, 75.2f, 60.800007f, 77.0f)
    lineTo(56.800007f, 77.2f)
    cubicTo(56.600006f, 76.2f, 56.300007f, 75.399994f, 55.70001f, 75.0f)
    cubicTo(55.10001f, 74.6f, 54.300007f, 74.3f, 53.10001f, 74.3f)
    cubicTo(51.90001f, 74.3f, 51.00001f, 74.5f, 50.30001f, 75.0f)
    cubicTo(49.90001f, 75.3f, 49.700012f, 75.7f, 49.700012f, 76.2f)
    cubicTo(49.700012f, 76.7f, 49.900013f, 77.1f, 50.30001f, 77.399994f)
    cubicTo(50.80001f, 77.799995f, 52.10001f, 78.299995f, 54.00001f, 78.7f)
    cubicTo(55.900013f, 79.1f, 57.400013f, 79.6f, 58.400013f, 80.1f)
    cubicTo(59.400013f, 80.6f, 60.100014f, 81.299995f, 60.600014f, 82.1f)
    cubicTo(61.100014f, 83.0f, 61.400013f, 84.0f, 61.400013f, 85.299995f)
    cubicTo(61.400013f, 86.399994f, 61.100014f, 87.49999f, 60.400013f, 88.49999f)
    cubicTo(59.700012f, 89.49999f, 58.900013f, 90.19999f, 57.700012f, 90.69999f)
    cubicTo(56.50001f, 91.19999f, 55.100014f, 91.39999f, 53.30001f, 91.39999f)
    cubicTo(50.80001f, 91.39999f, 48.80001f, 90.79999f, 47.50001f, 89.69999f)
    cubicTo(46.200012f, 88.59999f, 45.50001f, 86.99999f, 45.200012f, 84.79999f)
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
    cubicTo(21.4f, 56.3f, 22.2f, 55.4f, 23.0f, 54.5f)
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
    lineTo(57.799995f, 61.40001f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(97, 149, 48, 255), 0.267f to Color(96, 148, 47, 255), 0.443f to Color(91, 142, 45, 255), 0.594f to Color(83, 132, 41, 255), 0.731f to Color(72, 119, 35, 255), 0.858f to Color(58, 103, 27, 255), 0.976f to Color(41, 82, 15, 255), 1.0f to Color(36, 77, 11, 255), start = Offset(16.371f, 62.998f), end = Offset(55.488f, 23.883003f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.7f)
    lineTo(72.0f, 27.6f)
    lineTo(45.0f, 27.6f)
    lineTo(45.0f, 0.7f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(242, 245, 213, 255), 0.312f to Color(240, 244, 210, 255), 0.458f to Color(232, 238, 202, 255), 0.569f to Color(221, 231, 189, 255), 0.663f to Color(207, 221, 173, 255), 0.745f to Color(190, 210, 152, 255), 0.82f to Color(169, 196, 129, 255), 0.889f to Color(145, 181, 102, 255), 0.951f to Color(120, 166, 71, 255), 1.0f to Color(97, 153, 50, 255), start = Offset(45.034f, 27.633003f), end = Offset(58.534f, 14.133003f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.7f)
    lineTo(72.0f, 27.6f)
    lineTo(45.0f, 27.6f)
    lineTo(45.0f, 0.7f)
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
    moveTo(45.0f, 0.7f)
    lineTo(72.0f, 27.6f)
    lineTo(45.0f, 27.6f)
    lineTo(45.0f, 0.7f)
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

