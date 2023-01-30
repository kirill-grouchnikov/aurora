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
class ext_pptm : Painter() {
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
brush = Brush.linearGradient(0.0f to Color(203, 85, 40, 255), 0.032f to Color(207, 91, 39, 255), 0.162f to Color(219, 112, 38, 255), 0.305f to Color(230, 128, 37, 255), 0.468f to Color(238, 140, 35, 255), 0.666f to Color(244, 147, 34, 255), 1.0f to Color(246, 150, 34, 255), start = Offset(36.0f, 99.3f), end = Offset(36.0f, 0.79999995f), tileMode = TileMode.Clamp)
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
brush = SolidColor(Color(193, 81, 39, 255))
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
    moveTo(56.5f, 32.0f)
    cubicTo(56.5f, 34.3f, 56.0f, 36.4f, 55.1f, 38.0f)
    cubicTo(54.1f, 39.7f, 52.8f, 41.1f, 51.1f, 42.1f)
    cubicTo(49.3f, 43.199997f, 47.3f, 44.0f, 45.1f, 44.5f)
    cubicTo(42.8f, 45.0f, 40.3f, 45.2f, 37.5f, 45.2f)
    lineTo(34.0f, 45.2f)
    lineTo(34.0f, 56.800003f)
    cubicTo(34.0f, 57.500004f, 34.1f, 58.100002f, 34.4f, 58.600002f)
    cubicTo(34.700005f, 59.100002f, 35.2f, 59.500004f, 35.9f, 59.800003f)
    cubicTo(36.300003f, 60.000004f, 36.9f, 60.100002f, 37.800003f, 60.300003f)
    cubicTo(38.700005f, 60.500004f, 39.4f, 60.600002f, 40.000004f, 60.700005f)
    lineTo(40.000004f, 63.0f)
    lineTo(17.5f, 63.0f)
    lineTo(17.5f, 60.7f)
    cubicTo(18.1f, 60.600002f, 18.8f, 60.600002f, 19.8f, 60.5f)
    cubicTo(20.699999f, 60.4f, 21.4f, 60.3f, 21.8f, 60.1f)
    cubicTo(22.599998f, 59.8f, 23.099998f, 59.399998f, 23.3f, 59.0f)
    cubicTo(23.5f, 58.600002f, 23.699999f, 57.9f, 23.699999f, 57.1f)
    lineTo(23.699999f, 27.9f)
    cubicTo(23.699999f, 27.199999f, 23.599998f, 26.6f, 23.3f, 26.1f)
    cubicTo(23.099998f, 25.6f, 22.599998f, 25.2f, 21.8f, 24.9f)
    cubicTo(21.3f, 24.699999f, 20.5f, 24.5f, 19.599998f, 24.3f)
    cubicTo(18.699999f, 24.099998f, 17.999998f, 24.0f, 17.599998f, 23.9f)
    lineTo(17.599998f, 21.6f)
    lineTo(40.5f, 21.6f)
    cubicTo(45.9f, 21.6f, 49.9f, 22.5f, 52.5f, 24.2f)
    cubicTo(55.1f, 25.900002f, 56.5f, 28.5f, 56.5f, 32.0f)
    close()
    moveTo(45.0f, 33.5f)
    cubicTo(45.0f, 30.4f, 44.4f, 28.1f, 43.1f, 26.5f)
    cubicTo(41.8f, 25.0f, 39.6f, 24.2f, 36.399998f, 24.2f)
    lineTo(34.0f, 24.2f)
    lineTo(34.0f, 42.300003f)
    lineTo(35.2f, 42.300003f)
    cubicTo(38.4f, 42.300003f, 40.8f, 41.600002f, 42.5f, 40.100002f)
    cubicTo(44.2f, 38.800003f, 45.0f, 36.600002f, 45.0f, 33.500004f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.005f to Color(214, 91, 37, 255), 0.418f to Color(211, 90, 37, 255), 0.679f to Color(202, 86, 36, 255), 0.897f to Color(188, 79, 34, 255), 1.0f to Color(179, 75, 33, 255), start = Offset(12.838f, 58.262f), end = Offset(49.833f, 21.267f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 0.8f)
    lineTo(72.0f, 27.699999f)
    lineTo(45.0f, 27.699999f)
    lineTo(45.0f, 0.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 252, 227, 255), 0.383f to Color(255, 250, 224, 255), 0.521f to Color(253, 243, 216, 255), 0.62f to Color(251, 235, 204, 255), 0.699f to Color(248, 223, 187, 255), 0.767f to Color(245, 208, 166, 255), 0.828f to Color(241, 191, 141, 255), 0.882f to Color(236, 170, 114, 255), 0.933f to Color(231, 147, 84, 255), 0.977f to Color(226, 124, 52, 255), 1.0f to Color(223, 111, 38, 255), start = Offset(45.07f, 27.68f), end = Offset(58.57f, 14.18f), tileMode = TileMode.Clamp)
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
brush = SolidColor(Color(0, 0, 0, 0))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(193, 81, 39, 255))
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
    moveTo(6.4f, 91.3f)
    lineTo(6.4f, 75.6f)
    lineTo(11.5f, 75.6f)
    cubicTo(13.4f, 75.6f, 14.7f, 75.7f, 15.3f, 75.799995f)
    cubicTo(16.2f, 75.99999f, 17.0f, 76.6f, 17.6f, 77.299995f)
    cubicTo(18.2f, 78.1f, 18.5f, 79.1f, 18.5f, 80.399994f)
    cubicTo(18.5f, 81.399994f, 18.3f, 82.2f, 18.0f, 82.799995f)
    cubicTo(17.6f, 83.49999f, 17.2f, 83.99999f, 16.7f, 84.399994f)
    cubicTo(16.2f, 84.799995f, 15.6f, 84.99999f, 15.000001f, 85.09999f)
    cubicTo(14.200001f, 85.29999f, 13.100001f, 85.29999f, 11.700001f, 85.29999f)
    lineTo(9.6f, 85.29999f)
    lineTo(9.6f, 91.19999f)
    lineTo(6.4f, 91.19999f)
    close()
    moveTo(9.6f, 78.200005f)
    lineTo(9.6f, 82.700005f)
    lineTo(11.400001f, 82.700005f)
    cubicTo(12.700001f, 82.700005f, 13.5f, 82.600006f, 13.900001f, 82.50001f)
    cubicTo(14.3f, 82.30001f, 14.700001f, 82.100006f, 14.900001f, 81.700005f)
    cubicTo(15.1f, 81.3f, 15.3f, 80.9f, 15.3f, 80.50001f)
    cubicTo(15.3f, 79.90001f, 15.1f, 79.50001f, 14.8f, 79.100006f)
    cubicTo(14.5f, 78.700005f, 14.0f, 78.50001f, 13.5f, 78.40001f)
    cubicTo(13.1f, 78.30001f, 12.4f, 78.30001f, 11.2f, 78.30001f)
    lineTo(9.6f, 78.30001f)
    close()
    moveTo(21.2f, 91.3f)
    lineTo(21.2f, 75.6f)
    lineTo(26.300001f, 75.6f)
    cubicTo(28.2f, 75.6f, 29.500002f, 75.7f, 30.1f, 75.799995f)
    cubicTo(31.0f, 75.99999f, 31.800001f, 76.6f, 32.4f, 77.299995f)
    cubicTo(33.0f, 78.1f, 33.300003f, 79.1f, 33.300003f, 80.399994f)
    cubicTo(33.300003f, 81.399994f, 33.100002f, 82.2f, 32.800003f, 82.799995f)
    cubicTo(32.4f, 83.49999f, 32.000004f, 83.99999f, 31.500004f, 84.399994f)
    cubicTo(31.000004f, 84.799995f, 30.400003f, 84.99999f, 29.800003f, 85.09999f)
    cubicTo(29.000004f, 85.29999f, 27.900003f, 85.29999f, 26.500004f, 85.29999f)
    lineTo(24.400003f, 85.29999f)
    lineTo(24.400003f, 91.19999f)
    lineTo(21.200003f, 91.19999f)
    close()
    moveTo(24.400002f, 78.200005f)
    lineTo(24.400002f, 82.700005f)
    lineTo(26.2f, 82.700005f)
    cubicTo(27.5f, 82.700005f, 28.300001f, 82.600006f, 28.7f, 82.50001f)
    cubicTo(29.1f, 82.30001f, 29.5f, 82.100006f, 29.7f, 81.700005f)
    cubicTo(29.900002f, 81.3f, 30.1f, 80.9f, 30.1f, 80.50001f)
    cubicTo(30.1f, 79.90001f, 29.9f, 79.50001f, 29.6f, 79.100006f)
    cubicTo(29.300001f, 78.700005f, 28.800001f, 78.50001f, 28.300001f, 78.40001f)
    cubicTo(27.900002f, 78.30001f, 27.2f, 78.30001f, 26.000002f, 78.30001f)
    lineTo(24.400002f, 78.30001f)
    close()
    moveTo(39.5f, 91.3f)
    lineTo(39.5f, 78.2f)
    lineTo(34.8f, 78.2f)
    lineTo(34.8f, 75.5f)
    lineTo(47.4f, 75.5f)
    lineTo(47.4f, 78.2f)
    lineTo(42.7f, 78.2f)
    lineTo(42.7f, 91.299995f)
    lineTo(39.5f, 91.299995f)
    close()
    moveTo(49.5f, 91.3f)
    lineTo(49.5f, 75.6f)
    lineTo(54.3f, 75.6f)
    lineTo(57.2f, 86.299995f)
    lineTo(60.0f, 75.6f)
    lineTo(64.8f, 75.6f)
    lineTo(64.8f, 91.299995f)
    lineTo(61.800003f, 91.299995f)
    lineTo(61.800003f, 78.9f)
    lineTo(58.700005f, 91.3f)
    lineTo(55.600006f, 91.3f)
    lineTo(52.500008f, 78.9f)
    lineTo(52.500008f, 91.3f)
    lineTo(49.500008f, 91.3f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
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

