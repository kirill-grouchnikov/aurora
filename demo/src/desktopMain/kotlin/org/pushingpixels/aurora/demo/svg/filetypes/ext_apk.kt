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
class ext_apk : Painter() {
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
@Suppress("UNUSED_VARIABLE") var shapeText: Outline?
@Suppress("UNUSED_VARIABLE") var generalPathText: Path? = null
@Suppress("UNUSED_VARIABLE") var alphaText = 0.0f
@Suppress("UNUSED_VARIABLE") var blendModeText = DrawScope.DefaultBlendMode
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(72.0f, 99.0f)
generalPath!!.lineTo(0.0f, 99.0f)
generalPath!!.lineTo(0.0f, 1.0f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.139f to Color(216, 225, 230, 255), 0.359f to Color(235, 240, 243, 255), 0.617f to Color(249, 250, 251, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.0f), end = Offset(36.0f, 1.0f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_1
brush = SolidColor(Color(113, 145, 161, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(72.0f, 99.0f)
generalPath!!.lineTo(0.0f, 99.0f)
generalPath!!.lineTo(0.0f, 1.0f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
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
generalPath!!.moveTo(26.1f, 91.1f)
generalPath!!.lineTo(21.7f, 91.1f)
generalPath!!.lineTo(19.900002f, 86.6f)
generalPath!!.lineTo(11.900002f, 86.6f)
generalPath!!.lineTo(10.200002f, 91.1f)
generalPath!!.lineTo(6.0f, 91.1f)
generalPath!!.lineTo(13.8f, 71.3f)
generalPath!!.lineTo(18.1f, 71.3f)
generalPath!!.lineTo(26.1f, 91.100006f)
generalPath!!.close()
generalPath!!.moveTo(18.6f, 83.299995f)
generalPath!!.lineTo(15.8f, 75.899994f)
generalPath!!.lineTo(13.1f, 83.299995f)
generalPath!!.lineTo(18.6f, 83.299995f)
generalPath!!.close()
generalPath!!.moveTo(28.2f, 91.1f)
generalPath!!.lineTo(28.2f, 71.3f)
generalPath!!.lineTo(34.7f, 71.3f)
generalPath!!.cubicTo(37.2f, 71.3f, 38.8f, 71.4f, 39.5f, 71.600006f)
generalPath!!.cubicTo(40.6f, 71.90001f, 41.6f, 72.50001f, 42.4f, 73.50001f)
generalPath!!.cubicTo(43.2f, 74.50001f, 43.600002f, 75.80001f, 43.600002f, 77.40001f)
generalPath!!.cubicTo(43.600002f, 78.600006f, 43.4f, 79.600006f, 42.9f, 80.50001f)
generalPath!!.cubicTo(42.5f, 81.30001f, 41.9f, 82.00001f, 41.2f, 82.50001f)
generalPath!!.cubicTo(40.5f, 83.00001f, 39.8f, 83.30001f, 39.100002f, 83.40001f)
generalPath!!.cubicTo(38.100002f, 83.600006f, 36.7f, 83.70001f, 34.9f, 83.70001f)
generalPath!!.lineTo(32.300003f, 83.70001f)
generalPath!!.lineTo(32.300003f, 91.20001f)
generalPath!!.lineTo(28.200003f, 91.20001f)
generalPath!!.lineTo(28.200003f, 91.10001f)
generalPath!!.close()
generalPath!!.moveTo(32.3f, 74.6f)
generalPath!!.lineTo(32.3f, 80.2f)
generalPath!!.lineTo(34.5f, 80.2f)
generalPath!!.cubicTo(36.1f, 80.2f, 37.2f, 80.1f, 37.7f, 79.899994f)
generalPath!!.cubicTo(38.2f, 79.7f, 38.7f, 79.399994f, 39.0f, 78.899994f)
generalPath!!.cubicTo(39.3f, 78.49999f, 39.5f, 77.899994f, 39.5f, 77.399994f)
generalPath!!.cubicTo(39.5f, 76.7f, 39.3f, 76.09999f, 38.9f, 75.59999f)
generalPath!!.cubicTo(38.5f, 75.09999f, 37.9f, 74.79999f, 37.300003f, 74.69999f)
generalPath!!.cubicTo(36.800003f, 74.59999f, 35.800003f, 74.59999f, 34.4f, 74.59999f)
generalPath!!.lineTo(32.300003f, 74.59999f)
generalPath!!.close()
generalPath!!.moveTo(47.0f, 91.1f)
generalPath!!.lineTo(47.0f, 71.3f)
generalPath!!.lineTo(51.0f, 71.3f)
generalPath!!.lineTo(51.0f, 80.100006f)
generalPath!!.lineTo(59.2f, 71.3f)
generalPath!!.lineTo(64.6f, 71.3f)
generalPath!!.lineTo(57.1f, 79.0f)
generalPath!!.lineTo(65.1f, 91.1f)
generalPath!!.lineTo(59.899998f, 91.1f)
generalPath!!.lineTo(54.399998f, 81.799995f)
generalPath!!.lineTo(50.999996f, 85.1f)
generalPath!!.lineTo(50.999996f, 91.1f)
generalPath!!.lineTo(46.999996f, 91.1f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.9f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.4f, 51.7f)
generalPath!!.cubicTo(23.4f, 52.9f, 24.199999f, 53.8f, 25.5f, 53.8f)
generalPath!!.lineTo(27.6f, 53.8f)
generalPath!!.lineTo(27.6f, 61.0f)
generalPath!!.cubicTo(27.6f, 62.6f, 29.0f, 64.1f, 30.7f, 64.1f)
generalPath!!.cubicTo(32.3f, 64.1f, 33.8f, 62.699997f, 33.8f, 61.0f)
generalPath!!.lineTo(33.8f, 53.8f)
generalPath!!.lineTo(37.899998f, 53.8f)
generalPath!!.lineTo(37.899998f, 61.0f)
generalPath!!.cubicTo(37.899998f, 62.6f, 39.3f, 64.1f, 40.999996f, 64.1f)
generalPath!!.cubicTo(42.699993f, 64.1f, 44.099995f, 62.699997f, 44.099995f, 61.0f)
generalPath!!.lineTo(44.099995f, 53.8f)
generalPath!!.lineTo(46.0f, 53.8f)
generalPath!!.cubicTo(47.2f, 53.8f, 48.1f, 53.0f, 48.1f, 51.7f)
generalPath!!.lineTo(48.1f, 31.1f)
generalPath!!.lineTo(23.4f, 31.1f)
generalPath!!.lineTo(23.4f, 51.7f)
generalPath!!.close()
generalPath!!.moveTo(18.3f, 31.1f)
generalPath!!.cubicTo(16.699999f, 31.1f, 15.199999f, 32.5f, 15.199999f, 34.2f)
generalPath!!.lineTo(15.199999f, 48.6f)
generalPath!!.cubicTo(15.199999f, 50.199997f, 16.599998f, 51.699997f, 18.3f, 51.699997f)
generalPath!!.cubicTo(19.9f, 51.699997f, 21.4f, 50.299995f, 21.4f, 48.6f)
generalPath!!.lineTo(21.4f, 34.2f)
generalPath!!.cubicTo(21.4f, 32.5f, 19.9f, 31.1f, 18.3f, 31.1f)
generalPath!!.close()
generalPath!!.moveTo(53.2f, 31.1f)
generalPath!!.cubicTo(51.600002f, 31.1f, 50.100002f, 32.5f, 50.100002f, 34.2f)
generalPath!!.lineTo(50.100002f, 48.6f)
generalPath!!.cubicTo(50.100002f, 50.199997f, 51.500004f, 51.699997f, 53.2f, 51.699997f)
generalPath!!.cubicTo(54.899998f, 51.699997f, 56.3f, 50.299995f, 56.3f, 48.6f)
generalPath!!.lineTo(56.3f, 34.2f)
generalPath!!.cubicTo(56.3f, 32.5f, 54.899998f, 31.1f, 53.2f, 31.1f)
generalPath!!.close()
generalPath!!.moveTo(42.9f, 19.2f)
generalPath!!.lineTo(45.600002f, 16.5f)
generalPath!!.cubicTo(46.000004f, 16.1f, 46.000004f, 15.5f, 45.600002f, 15.1f)
generalPath!!.cubicTo(45.2f, 14.700001f, 44.600002f, 14.700001f, 44.2f, 15.1f)
generalPath!!.lineTo(41.100002f, 18.2f)
generalPath!!.cubicTo(39.7f, 17.2f, 37.800003f, 16.800001f, 35.800003f, 16.800001f)
generalPath!!.cubicTo(33.800003f, 16.800001f, 31.900003f, 17.2f, 30.200003f, 18.000002f)
generalPath!!.lineTo(27.300003f, 14.900002f)
generalPath!!.cubicTo(26.900003f, 14.700002f, 26.100002f, 14.700002f, 25.700003f, 14.900002f)
generalPath!!.cubicTo(25.500002f, 15.300001f, 25.500002f, 16.100002f, 25.700003f, 16.500002f)
generalPath!!.lineTo(28.400003f, 19.200003f)
generalPath!!.cubicTo(25.500004f, 21.500002f, 23.500004f, 25.000004f, 23.500004f, 29.100002f)
generalPath!!.lineTo(48.200005f, 29.100002f)
generalPath!!.cubicTo(48.100006f, 24.900002f, 46.000004f, 21.200003f, 42.900005f, 19.200003f)
generalPath!!.close()
generalPath!!.moveTo(31.600002f, 24.900002f)
generalPath!!.lineTo(29.500002f, 24.900002f)
generalPath!!.lineTo(29.500002f, 22.800001f)
generalPath!!.lineTo(31.600002f, 22.800001f)
generalPath!!.lineTo(31.600002f, 24.900002f)
generalPath!!.close()
generalPath!!.moveTo(41.9f, 24.900002f)
generalPath!!.lineTo(39.800003f, 24.900002f)
generalPath!!.lineTo(39.800003f, 22.800001f)
generalPath!!.lineTo(41.9f, 22.800001f)
generalPath!!.lineTo(41.9f, 24.900002f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(35.75f, 64.1f), end = Offset(35.75f, 14.75f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(45.0f, 27.7f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.35f to Color(250, 251, 251, 255), 0.532f to Color(237, 241, 244, 255), 0.675f to Color(221, 229, 233, 255), 0.799f to Color(199, 211, 218, 255), 0.908f to Color(173, 189, 199, 255), 1.0f to Color(146, 165, 176, 255), start = Offset(45.036f, 27.814f), end = Offset(58.536f, 14.313f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_5
brush = SolidColor(Color(113, 145, 161, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(45.0f, 1.0f)
generalPath!!.lineTo(72.0f, 27.7f)
generalPath!!.lineTo(45.0f, 27.7f)
generalPath!!.lineTo(45.0f, 1.0f)
generalPath!!.close()
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

