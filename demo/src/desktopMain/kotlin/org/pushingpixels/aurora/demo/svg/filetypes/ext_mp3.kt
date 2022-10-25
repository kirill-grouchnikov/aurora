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
class ext_mp3 : Painter() {
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
alpha *= 0.99f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
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
    moveTo(45.2f, 0.9f)
    lineTo(72.3f, 27.699999f)
    lineTo(72.3f, 99.1f)
    lineTo(0.0f, 99.1f)
    lineTo(0.0f, 0.9f)
    lineTo(45.2f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(89, 85, 147, 255), 0.012f to Color(91, 87, 148, 255), 0.182f to Color(123, 119, 170, 255), 0.352f to Color(152, 150, 191, 255), 0.521f to Color(178, 178, 210, 255), 0.687f to Color(199, 201, 226, 255), 0.848f to Color(214, 217, 236, 255), 1.0f to Color(219, 223, 240, 255), start = Offset(36.15f, 99.113f), end = Offset(36.15f, 0.87400055f), tileMode = TileMode.Clamp)
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
    moveTo(45.2f, 0.9f)
    lineTo(72.3f, 27.699999f)
    lineTo(72.3f, 99.1f)
    lineTo(0.0f, 99.1f)
    lineTo(0.0f, 0.9f)
    lineTo(45.2f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(45, 50, 147, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 0.9f)
    lineTo(72.3f, 27.699999f)
    lineTo(72.3f, 99.1f)
    lineTo(0.0f, 99.1f)
    lineTo(0.0f, 0.9f)
    lineTo(45.2f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
    moveTo(9.3f, 91.2f)
    lineTo(9.3f, 71.3f)
    lineTo(15.4f, 71.3f)
    lineTo(19.0f, 84.8f)
    lineTo(22.6f, 71.200005f)
    lineTo(28.7f, 71.200005f)
    lineTo(28.7f, 91.100006f)
    lineTo(25.0f, 91.100006f)
    lineTo(25.0f, 75.5f)
    lineTo(21.0f, 91.2f)
    lineTo(17.1f, 91.2f)
    lineTo(13.1f, 75.5f)
    lineTo(13.1f, 91.2f)
    lineTo(9.3f, 91.2f)
    close()
    moveTo(32.8f, 91.2f)
    lineTo(32.8f, 71.3f)
    lineTo(39.3f, 71.3f)
    cubicTo(41.8f, 71.3f, 43.399998f, 71.4f, 44.1f, 71.600006f)
    cubicTo(45.3f, 71.90001f, 46.199997f, 72.50001f, 47.0f, 73.50001f)
    cubicTo(47.800003f, 74.50001f, 48.2f, 75.80001f, 48.2f, 77.40001f)
    cubicTo(48.2f, 78.600006f, 48.0f, 79.600006f, 47.5f, 80.50001f)
    cubicTo(47.1f, 81.30001f, 46.5f, 82.00001f, 45.8f, 82.50001f)
    cubicTo(45.1f, 83.00001f, 44.399998f, 83.30001f, 43.7f, 83.40001f)
    cubicTo(42.7f, 83.600006f, 41.3f, 83.70001f, 39.5f, 83.70001f)
    lineTo(36.9f, 83.70001f)
    lineTo(36.9f, 91.20001f)
    lineTo(32.800003f, 91.20001f)
    close()
    moveTo(36.8f, 74.6f)
    lineTo(36.8f, 80.2f)
    lineTo(39.0f, 80.2f)
    cubicTo(40.6f, 80.2f, 41.7f, 80.1f, 42.2f, 79.899994f)
    cubicTo(42.7f, 79.7f, 43.2f, 79.399994f, 43.5f, 78.899994f)
    cubicTo(43.8f, 78.49999f, 44.0f, 77.899994f, 44.0f, 77.399994f)
    cubicTo(44.0f, 76.7f, 43.8f, 76.09999f, 43.4f, 75.59999f)
    cubicTo(43.0f, 75.09999f, 42.4f, 74.79999f, 41.800003f, 74.69999f)
    cubicTo(41.300003f, 74.59999f, 40.300003f, 74.59999f, 38.9f, 74.59999f)
    lineTo(36.800003f, 74.59999f)
    close()
    moveTo(50.5f, 85.9f)
    lineTo(54.2f, 85.5f)
    cubicTo(54.3f, 86.4f, 54.600002f, 87.2f, 55.2f, 87.7f)
    cubicTo(55.7f, 88.2f, 56.4f, 88.399994f, 57.100002f, 88.399994f)
    cubicTo(57.9f, 88.399994f, 58.600002f, 88.09999f, 59.100002f, 87.49999f)
    cubicTo(59.600002f, 86.899994f, 59.9f, 86.09999f, 59.9f, 85.09999f)
    cubicTo(59.9f, 84.09999f, 59.600002f, 83.399994f, 59.100002f, 82.79999f)
    cubicTo(58.600002f, 82.19998f, 57.9f, 81.999985f, 57.2f, 81.999985f)
    cubicTo(56.7f, 81.999985f, 56.100002f, 82.09998f, 55.4f, 82.29999f)
    lineTo(55.800003f, 79.19999f)
    cubicTo(56.800003f, 79.19999f, 57.600002f, 78.99999f, 58.200005f, 78.49999f)
    cubicTo(58.800007f, 77.99999f, 59.000004f, 77.399994f, 59.000004f, 76.59999f)
    cubicTo(59.000004f, 75.899994f, 58.800003f, 75.399994f, 58.400005f, 74.99999f)
    cubicTo(58.000008f, 74.59999f, 57.500004f, 74.399994f, 56.800007f, 74.399994f)
    cubicTo(56.100006f, 74.399994f, 55.600006f, 74.59999f, 55.100006f, 75.09999f)
    cubicTo(54.600006f, 75.59999f, 54.300007f, 76.19999f, 54.200005f, 77.09999f)
    lineTo(50.600006f, 76.49999f)
    cubicTo(50.800007f, 75.299995f, 51.200005f, 74.299995f, 51.700005f, 73.59999f)
    cubicTo(52.200005f, 72.899994f, 52.900005f, 72.29999f, 53.800003f, 71.899994f)
    cubicTo(54.700005f, 71.49999f, 55.700005f, 71.299995f, 56.800003f, 71.299995f)
    cubicTo(58.700005f, 71.299995f, 60.200005f, 71.899994f, 61.4f, 73.1f)
    cubicTo(62.300003f, 74.1f, 62.800003f, 75.2f, 62.800003f, 76.4f)
    cubicTo(62.800003f, 78.1f, 61.800003f, 79.5f, 59.9f, 80.6f)
    cubicTo(61.100002f, 80.799995f, 62.0f, 81.4f, 62.7f, 82.2f)
    cubicTo(63.4f, 83.0f, 63.7f, 84.1f, 63.7f, 85.299995f)
    cubicTo(63.7f, 86.99999f, 63.100002f, 88.49999f, 61.8f, 89.799995f)
    cubicTo(60.5f, 90.99999f, 58.899998f, 91.6f, 57.0f, 91.6f)
    cubicTo(55.2f, 91.6f, 53.7f, 91.1f, 52.5f, 90.0f)
    cubicTo(51.4f, 88.9f, 50.7f, 87.6f, 50.5f, 85.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.99f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 0.9f)
    lineTo(72.3f, 27.699999f)
    lineTo(45.2f, 27.699999f)
    lineTo(45.2f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(89, 85, 147, 255), 0.07f to Color(112, 108, 162, 255), 0.159f to Color(137, 136, 181, 255), 0.255f to Color(163, 165, 200, 255), 0.359f to Color(186, 191, 217, 255), 0.471f to Color(206, 213, 231, 255), 0.598f to Color(222, 230, 242, 255), 0.751f to Color(233, 243, 250, 255), 1.0f to Color(236, 248, 254, 255), start = Offset(58.321f, 14.726997f), end = Offset(50.783f, 23.161003f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 0.9f)
    lineTo(72.3f, 27.699999f)
    lineTo(45.2f, 27.699999f)
    lineTo(45.2f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(45, 50, 147, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.2f, 0.9f)
    lineTo(72.3f, 27.699999f)
    lineTo(45.2f, 27.699999f)
    lineTo(45.2f, 0.9f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.99f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(14.8f, 39.3f)
    lineTo(21.3f, 39.3f)
    lineTo(34.3f, 29.3f)
    lineTo(34.3f, 62.3f)
    lineTo(21.3f, 52.3f)
    lineTo(14.799999f, 52.3f)
    lineTo(14.799999f, 39.3f)
    close()
    moveTo(40.9f, 49.5f)
    lineTo(40.9f, 52.4f)
    cubicTo(41.0f, 52.4f, 42.5f, 52.4f, 44.100002f, 51.600002f)
    cubicTo(45.700005f, 50.800003f, 47.500004f, 48.9f, 47.500004f, 45.800003f)
    cubicTo(47.500004f, 42.700005f, 45.800003f, 40.800003f, 44.100002f, 40.000004f)
    cubicTo(42.500004f, 39.200005f, 41.000004f, 39.200005f, 40.9f, 39.200005f)
    lineTo(40.9f, 42.100006f)
    lineTo(41.0f, 42.100006f)
    cubicTo(41.4f, 42.100006f, 42.4f, 42.300007f, 43.1f, 42.800007f)
    cubicTo(43.899998f, 43.300007f, 44.5f, 44.000008f, 44.5f, 45.800007f)
    cubicTo(44.5f, 47.800007f, 43.7f, 48.400005f, 42.7f, 49.000008f)
    cubicTo(42.2f, 49.20001f, 41.7f, 49.40001f, 41.4f, 49.40001f)
    cubicTo(41.2f, 49.40001f, 41.100002f, 49.40001f, 41.0f, 49.500008f)
    lineTo(40.9f, 49.500008f)
    close()
    moveTo(40.9f, 54.6f)
    lineTo(40.9f, 57.5f)
    cubicTo(41.0f, 57.5f, 43.7f, 57.5f, 46.7f, 56.1f)
    cubicTo(49.600002f, 54.699997f, 52.7f, 51.5f, 52.600002f, 46.0f)
    cubicTo(52.7f, 40.4f, 49.600002f, 37.3f, 46.7f, 35.9f)
    cubicTo(43.8f, 34.5f, 41.100002f, 34.5f, 40.9f, 34.5f)
    lineTo(40.9f, 37.4f)
    lineTo(41.2f, 37.4f)
    cubicTo(42.0f, 37.5f, 44.3f, 37.800003f, 46.100002f, 39.0f)
    cubicTo(48.000004f, 40.2f, 49.600002f, 42.1f, 49.600002f, 46.1f)
    cubicTo(49.600002f, 50.699997f, 47.500004f, 52.6f, 45.300003f, 53.6f)
    cubicTo(44.200005f, 54.199997f, 43.100002f, 54.399998f, 42.200005f, 54.6f)
    cubicTo(41.800003f, 54.699997f, 41.400005f, 54.699997f, 41.200005f, 54.699997f)
    cubicTo(41.000004f, 54.6f, 40.900005f, 54.6f, 40.900005f, 54.6f)
    close()
    moveTo(40.9f, 59.6f)
    lineTo(40.9f, 62.5f)
    cubicTo(41.0f, 62.5f, 45.0f, 62.5f, 49.2f, 60.4f)
    cubicTo(53.4f, 58.4f, 57.7f, 53.9f, 57.7f, 45.800003f)
    cubicTo(57.8f, 37.700005f, 53.4f, 33.200005f, 49.2f, 31.200003f)
    cubicTo(45.0f, 29.100002f, 41.0f, 29.100002f, 40.9f, 29.100002f)
    lineTo(40.9f, 32.0f)
    lineTo(41.5f, 32.0f)
    cubicTo(42.8f, 32.1f, 46.3f, 32.6f, 49.2f, 34.5f)
    cubicTo(52.100002f, 36.4f, 54.7f, 39.6f, 54.8f, 45.8f)
    cubicTo(54.7f, 52.8f, 51.399998f, 56.0f, 47.899998f, 57.8f)
    cubicTo(46.199997f, 58.7f, 44.399998f, 59.1f, 42.999996f, 59.3f)
    cubicTo(42.299995f, 59.399998f, 41.799995f, 59.5f, 41.399998f, 59.5f)
    cubicTo(41.1f, 59.6f, 40.899998f, 59.6f, 40.899998f, 59.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(53, 44, 127, 255), 0.074f to Color(62, 55, 134, 255), 0.266f to Color(84, 79, 150, 255), 0.457f to Color(103, 99, 165, 255), 0.645f to Color(117, 114, 177, 255), 0.827f to Color(126, 124, 186, 255), 1.0f to Color(129, 128, 189, 255), start = Offset(14.776f, 45.826f), end = Offset(57.726f, 45.826f), tileMode = TileMode.Clamp)
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
            return 0.7430000305175781
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

