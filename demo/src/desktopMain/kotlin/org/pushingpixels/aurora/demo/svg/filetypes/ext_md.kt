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
class ext_md : Painter() {
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
    moveTo(45.0f, 1.2f)
    lineTo(72.0f, 28.0f)
    lineTo(72.0f, 99.2f)
    lineTo(0.0f, 99.2f)
    lineTo(0.0f, 1.199997f)
    lineTo(45.0f, 1.199997f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(200, 212, 219, 255), 0.047f to Color(207, 217, 224, 255), 0.225f to Color(228, 234, 237, 255), 0.424f to Color(243, 246, 247, 255), 0.655f to Color(252, 253, 253, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(36.0f, 99.2f), end = Offset(36.0f, 1.199997f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(113, 145, 160, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.2f)
    lineTo(72.0f, 28.0f)
    lineTo(72.0f, 99.2f)
    lineTo(0.0f, 99.2f)
    lineTo(0.0f, 1.199997f)
    lineTo(45.0f, 1.199997f)
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
// _0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(17.0f, 92.3f)
    lineTo(17.0f, 72.5f)
    lineTo(23.0f, 72.5f)
    lineTo(26.7f, 86.0f)
    lineTo(30.300001f, 72.5f)
    lineTo(36.4f, 72.5f)
    lineTo(36.4f, 92.3f)
    lineTo(32.600002f, 92.3f)
    lineTo(32.600002f, 76.7f)
    lineTo(28.600002f, 92.299995f)
    lineTo(24.700003f, 92.299995f)
    lineTo(20.700003f, 76.7f)
    lineTo(20.700003f, 92.299995f)
    lineTo(17.0f, 92.299995f)
    close()
    moveTo(40.3f, 72.5f)
    lineTo(47.7f, 72.5f)
    cubicTo(49.4f, 72.5f, 50.600002f, 72.6f, 51.5f, 72.9f)
    cubicTo(52.7f, 73.200005f, 53.7f, 73.9f, 54.5f, 74.700005f)
    cubicTo(55.3f, 75.50001f, 56.0f, 76.600006f, 56.4f, 77.9f)
    cubicTo(56.800003f, 79.2f, 57.100002f, 80.700005f, 57.100002f, 82.6f)
    cubicTo(57.100002f, 84.2f, 56.9f, 85.6f, 56.500004f, 86.799995f)
    cubicTo(56.000004f, 88.2f, 55.300003f, 89.399994f, 54.400005f, 90.299995f)
    cubicTo(53.700005f, 90.99999f, 52.800007f, 91.49999f, 51.600006f, 91.899994f)
    cubicTo(50.700005f, 92.2f, 49.500008f, 92.299995f, 48.000008f, 92.299995f)
    lineTo(40.40001f, 92.299995f)
    lineTo(40.40001f, 72.5f)
    lineTo(40.30001f, 72.5f)
    close()
    moveTo(44.399998f, 75.8f)
    lineTo(44.399998f, 89.0f)
    lineTo(47.399998f, 89.0f)
    cubicTo(48.499996f, 89.0f, 49.3f, 88.9f, 49.8f, 88.8f)
    cubicTo(50.5f, 88.600006f, 51.0f, 88.4f, 51.399998f, 88.0f)
    cubicTo(51.8f, 87.6f, 52.199997f, 87.0f, 52.499996f, 86.1f)
    cubicTo(52.799995f, 85.2f, 52.899998f, 84.0f, 52.899998f, 82.5f)
    cubicTo(52.899998f, 81.0f, 52.8f, 79.8f, 52.499996f, 79.0f)
    cubicTo(52.199997f, 78.2f, 51.799995f, 77.5f, 51.399998f, 77.1f)
    cubicTo(51.0f, 76.7f, 50.3f, 76.299995f, 49.499996f, 76.2f)
    cubicTo(48.899998f, 76.1f, 47.799995f, 76.0f, 46.199997f, 76.0f)
    lineTo(44.399998f, 76.0f)
    lineTo(44.399998f, 75.8f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(76, 108, 123, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.9f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.7f, 51.8f)
    lineTo(19.6f, 51.8f)
    lineTo(19.6f, 46.2f)
    lineTo(25.0f, 46.2f)
    lineTo(26.8f, 38.600002f)
    lineTo(19.5f, 38.600002f)
    lineTo(19.5f, 33.0f)
    lineTo(28.2f, 33.0f)
    lineTo(30.6f, 23.0f)
    lineTo(37.4f, 23.0f)
    lineTo(35.0f, 33.0f)
    lineTo(41.7f, 33.0f)
    lineTo(44.0f, 23.0f)
    lineTo(51.1f, 23.0f)
    lineTo(48.699997f, 33.0f)
    lineTo(53.0f, 33.0f)
    lineTo(53.0f, 38.7f)
    lineTo(47.4f, 38.7f)
    lineTo(45.600002f, 46.3f)
    lineTo(53.0f, 46.3f)
    lineTo(53.0f, 51.899998f)
    lineTo(44.2f, 51.899998f)
    lineTo(41.8f, 61.899998f)
    lineTo(35.0f, 61.899998f)
    lineTo(37.3f, 51.899998f)
    lineTo(30.5f, 51.899998f)
    lineTo(28.1f, 61.899998f)
    lineTo(21.2f, 61.899998f)
    lineTo(23.7f, 51.799995f)
    close()
    moveTo(33.7f, 38.699997f)
    lineTo(31.900002f, 46.299995f)
    lineTo(38.7f, 46.299995f)
    lineTo(40.5f, 38.699997f)
    lineTo(33.7f, 38.699997f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(173, 204, 220, 255), 1.0f to Color(76, 108, 123, 255), start = Offset(36.25f, 61.9f), end = Offset(36.25f, 23.0f), tileMode = TileMode.Clamp)
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
    moveTo(45.0f, 1.2f)
    lineTo(72.0f, 28.0f)
    lineTo(45.0f, 28.0f)
    lineTo(45.0f, 1.2f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 0.335f to Color(253, 253, 253, 255), 0.51f to Color(245, 247, 248, 255), 0.647f to Color(231, 236, 238, 255), 0.765f to Color(212, 220, 224, 255), 0.87f to Color(188, 200, 207, 255), 0.966f to Color(158, 175, 185, 255), 1.0f to Color(145, 165, 176, 255), start = Offset(45.05f, 28.050003f), end = Offset(58.55f, 14.550003f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(113, 145, 160, 255))
stroke = Stroke(width=2.0f, cap=StrokeCap.Butt, join=StrokeJoin.Bevel, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(45.0f, 1.2f)
    lineTo(72.0f, 28.0f)
    lineTo(45.0f, 28.0f)
    lineTo(45.0f, 1.2f)
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
            return 0.0019999693613499403
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
            return 0.9980000257492065
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

