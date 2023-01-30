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
class ext_svg : Painter() {
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
    lineTo(72.3f, 27.7f)
    lineTo(72.3f, 99.0f)
    lineTo(-0.2f, 99.0f)
    lineTo(-0.2f, 1.0f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(248, 176, 184, 255), 0.211f to Color(246, 172, 181, 255), 0.37f to Color(242, 163, 173, 255), 0.512f to Color(237, 149, 161, 255), 0.645f to Color(231, 130, 146, 255), 0.77f to Color(224, 105, 128, 255), 0.889f to Color(215, 72, 107, 255), 1.0f to Color(206, 7, 87, 255), start = Offset(36.0f, 1.0f), end = Offset(36.0f, 98.996f), tileMode = TileMode.Clamp)
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
    lineTo(72.3f, 27.7f)
    lineTo(72.3f, 99.0f)
    lineTo(-0.2f, 99.0f)
    lineTo(-0.2f, 1.0f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(212, 28, 92, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(72.3f, 99.0f)
    lineTo(-0.2f, 99.0f)
    lineTo(-0.2f, 1.0f)
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
    moveTo(7.6f, 84.6f)
    lineTo(11.6f, 84.2f)
    cubicTo(11.8f, 85.5f, 12.3f, 86.5f, 13.1f, 87.1f)
    cubicTo(13.8f, 87.7f, 14.8f, 88.0f, 16.1f, 88.0f)
    cubicTo(17.400002f, 88.0f, 18.4f, 87.7f, 19.1f, 87.2f)
    cubicTo(19.800001f, 86.7f, 20.1f, 86.0f, 20.1f, 85.299995f)
    cubicTo(20.1f, 84.799995f, 20.0f, 84.399994f, 19.7f, 84.1f)
    cubicTo(19.400002f, 83.799995f, 18.900002f, 83.5f, 18.2f, 83.2f)
    cubicTo(17.7f, 83.0f, 16.6f, 82.7f, 14.900001f, 82.299995f)
    cubicTo(12.700001f, 81.799995f, 11.200001f, 81.1f, 10.300001f, 80.299995f)
    cubicTo(9.100001f, 79.2f, 8.400002f, 77.899994f, 8.400002f, 76.299995f)
    cubicTo(8.400002f, 75.299995f, 8.700002f, 74.299995f, 9.300001f, 73.49999f)
    cubicTo(9.900002f, 72.59999f, 10.700001f, 71.99999f, 11.800001f, 71.49999f)
    cubicTo(12.900002f, 70.99999f, 14.200001f, 70.799995f, 15.800001f, 70.799995f)
    cubicTo(18.300001f, 70.799995f, 20.2f, 71.299995f, 21.5f, 72.399994f)
    cubicTo(22.8f, 73.49999f, 23.5f, 74.899994f, 23.5f, 76.799995f)
    lineTo(19.4f, 76.99999f)
    cubicTo(19.199999f, 75.99999f, 18.9f, 75.19999f, 18.3f, 74.799995f)
    cubicTo(17.699999f, 74.399994f, 16.9f, 74.1f, 15.699999f, 74.1f)
    cubicTo(14.499999f, 74.1f, 13.599998f, 74.299995f, 12.899999f, 74.799995f)
    cubicTo(12.499999f, 75.1f, 12.299998f, 75.49999f, 12.299998f, 75.99999f)
    cubicTo(12.299998f, 76.49999f, 12.499998f, 76.899994f, 12.899999f, 77.19999f)
    cubicTo(13.399999f, 77.59999f, 14.699999f, 78.09999f, 16.599998f, 78.49999f)
    cubicTo(18.499998f, 78.899994f, 19.999998f, 79.399994f, 20.999998f, 79.899994f)
    cubicTo(21.999998f, 80.399994f, 22.699999f, 81.09999f, 23.199999f, 81.899994f)
    cubicTo(23.8f, 82.99999f, 23.999998f, 83.99999f, 23.999998f, 85.299995f)
    cubicTo(23.999998f, 86.399994f, 23.699999f, 87.49999f, 22.999998f, 88.49999f)
    cubicTo(22.399998f, 89.49999f, 21.399998f, 90.19999f, 20.299997f, 90.69999f)
    cubicTo(19.199997f, 91.19999f, 17.699997f, 91.39999f, 15.899998f, 91.39999f)
    cubicTo(13.299997f, 91.39999f, 11.399998f, 90.79999f, 9.999998f, 89.69999f)
    cubicTo(8.599998f, 88.59999f, 7.899998f, 86.79999f, 7.599998f, 84.59999f)
    close()
    moveTo(32.6f, 91.1f)
    lineTo(25.399998f, 71.3f)
    lineTo(29.799997f, 71.3f)
    lineTo(34.9f, 86.0f)
    lineTo(39.800003f, 71.3f)
    lineTo(44.100002f, 71.3f)
    lineTo(37.0f, 91.1f)
    lineTo(32.6f, 91.1f)
    close()
    moveTo(55.699997f, 83.799995f)
    lineTo(55.699997f, 80.49999f)
    lineTo(64.5f, 80.49999f)
    lineTo(64.5f, 88.399994f)
    cubicTo(63.6f, 89.2f, 62.4f, 89.899994f, 60.8f, 90.49999f)
    cubicTo(59.199997f, 91.09999f, 57.5f, 91.399994f, 55.899998f, 91.399994f)
    cubicTo(53.8f, 91.399994f, 51.899998f, 90.99999f, 50.399998f, 90.09999f)
    cubicTo(48.8f, 89.19999f, 47.699997f, 87.99999f, 46.899998f, 86.399994f)
    cubicTo(46.1f, 84.799995f, 45.699997f, 82.99999f, 45.699997f, 81.09999f)
    cubicTo(45.699997f, 78.99999f, 46.1f, 77.19999f, 46.999996f, 75.59999f)
    cubicTo(47.899994f, 73.99999f, 49.199997f, 72.79999f, 50.899998f, 71.899994f)
    cubicTo(52.199997f, 71.2f, 53.8f, 70.899994f, 55.699997f, 70.899994f)
    cubicTo(58.199997f, 70.899994f, 60.199997f, 71.399994f, 61.6f, 72.399994f)
    cubicTo(63.0f, 73.399994f, 63.899998f, 74.899994f, 64.299995f, 76.7f)
    lineTo(60.299995f, 77.399994f)
    cubicTo(59.999996f, 76.399994f, 59.499996f, 75.7f, 58.699997f, 75.09999f)
    cubicTo(57.899998f, 74.49999f, 56.899998f, 74.29999f, 55.799995f, 74.29999f)
    cubicTo(53.999996f, 74.29999f, 52.599995f, 74.89999f, 51.599995f, 75.999985f)
    cubicTo(50.499996f, 77.09998f, 49.999996f, 78.79999f, 49.999996f, 80.89999f)
    cubicTo(49.999996f, 83.29999f, 50.499996f, 84.999985f, 51.599995f, 86.19999f)
    cubicTo(52.699993f, 87.399994f, 54.099995f, 87.99999f, 55.799995f, 87.99999f)
    cubicTo(56.699997f, 87.99999f, 57.499996f, 87.799995f, 58.399994f, 87.49999f)
    cubicTo(59.299995f, 87.19999f, 59.999992f, 86.799995f, 60.599995f, 86.299995f)
    lineTo(60.599995f, 83.799995f)
    lineTo(55.699993f, 83.799995f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.73f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(22.0f, 62.3f)
    lineTo(17.4f, 59.0f)
    cubicTo(17.4f, 59.0f, 25.099998f, 43.8f, 22.099998f, 38.2f)
    lineTo(38.399998f, 25.6f)
    cubicTo(38.399998f, 25.6f, 44.6f, 26.6f, 47.699997f, 32.1f)
    lineTo(40.699997f, 51.199997f)
    cubicTo(34.3f, 50.3f, 22.0f, 62.3f, 22.0f, 62.3f)
    close()
    moveTo(33.1f, 42.5f)
    cubicTo(34.399998f, 43.4f, 36.1f, 43.1f, 37.0f, 41.9f)
    cubicTo(37.9f, 40.600002f, 37.6f, 38.9f, 36.4f, 38.0f)
    cubicTo(35.100002f, 37.1f, 33.4f, 37.4f, 32.5f, 38.6f)
    cubicTo(31.5f, 39.8f, 31.8f, 41.6f, 33.1f, 42.5f)
    close()
    moveTo(33.1f, 42.5f)
    lineTo(19.8f, 60.6f)
    moveTo(59.1f, 26.199997f)
    cubicTo(52.899998f, 14.399997f, 39.5f, 12.199997f, 39.5f, 12.199997f)
    lineTo(33.5f, 20.399998f)
    cubicTo(46.9f, 22.599998f, 53.1f, 34.399998f, 53.1f, 34.399998f)
    lineTo(59.1f, 26.199997f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(238, 40, 104, 255), 1.0f to Color(188, 2, 79, 255), start = Offset(44.996216f, 45.965942f), end = Offset(25.40857f, 31.79712f), tileMode = TileMode.Clamp)
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
    moveTo(19.9f, 60.8f)
    lineTo(33.3f, 42.6f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(248, 182, 187, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(19.9f, 60.8f)
    lineTo(33.3f, 42.6f)
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
    moveTo(45.1f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(45.1f, 27.7f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 254, 238, 255), 0.265f to Color(255, 250, 236, 255), 0.402f to Color(254, 242, 230, 255), 0.51f to Color(252, 231, 220, 255), 0.604f to Color(250, 215, 207, 255), 0.687f to Color(246, 195, 191, 255), 0.763f to Color(242, 171, 172, 255), 0.834f to Color(238, 143, 151, 255), 0.901f to Color(234, 111, 130, 255), 0.962f to Color(229, 68, 109, 255), 1.0f to Color(227, 14, 96, 255), start = Offset(45.178f, 27.841003f), end = Offset(58.772f, 14.247002f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(45.1f, 27.7f)
    lineTo(45.1f, 1.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(212, 28, 92, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.1f, 1.0f)
    lineTo(72.3f, 27.7f)
    lineTo(45.1f, 27.7f)
    lineTo(45.1f, 1.0f)
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
            return 0.12799999117851257
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
            return 0.7450000047683716
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

