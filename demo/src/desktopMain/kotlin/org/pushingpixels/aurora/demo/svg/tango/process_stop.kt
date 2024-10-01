package org.pushingpixels.aurora.demo.svg.tango

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
class process_stop : Painter() {
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
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
alphaStack.add(0, alpha)
alpha *= 0.6306818f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.1738029718399048f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6000000238418579f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-5.265865802764893f, 19.575000762939453f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.875f, 36.75f)
    cubicTo(40.900238f, 40.109352f, 37.90384f, 43.21997f, 33.020405f, 44.904f)
    cubicTo(28.136969f, 46.588028f, 22.113031f, 46.588028f, 17.229597f, 44.904f)
    cubicTo(12.346162f, 43.21997f, 9.34976f, 40.109352f, 9.375f, 36.75f)
    cubicTo(9.34976f, 33.390648f, 12.346162f, 30.28003f, 17.229597f, 28.596f)
    cubicTo(22.113031f, 26.911972f, 28.136969f, 26.911972f, 33.020405f, 28.596f)
    cubicTo(37.90384f, 30.28003f, 40.900238f, 33.390648f, 40.875f, 36.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(25.125f, 36.749996f), radius = 15.75f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
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
    moveTo(15.591006f, 0.4919213f)
    lineTo(32.67631f, 0.4919213f)
    lineTo(45.497585f, 13.586385f)
    lineTo(45.497585f, 31.48003f)
    lineTo(32.848988f, 43.49693f)
    lineTo(15.418649f, 43.49693f)
    lineTo(2.4943857f, 30.658264f)
    lineTo(2.4943857f, 13.464078f)
    lineTo(15.591006f, 0.4919213f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(204, 0, 0, 255), 1.0f to Color(179, 0, 0, 255), start = Offset(23.995985f, 18.105337f), end = Offset(41.047836f, 35.959785f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(134, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.591006f, 0.4919213f)
    lineTo(32.67631f, 0.4919213f)
    lineTo(45.497585f, 13.586385f)
    lineTo(45.497585f, 31.48003f)
    lineTo(32.848988f, 43.49693f)
    lineTo(15.418649f, 43.49693f)
    lineTo(2.4943857f, 30.658264f)
    lineTo(2.4943857f, 13.464078f)
    lineTo(15.591006f, 0.4919213f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8131868f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
brush = Brush.linearGradient(0.0f to Color(255, 139, 139, 255), 1.0f to Color(236, 27, 27, 255), start = Offset(15.737001f, 10.5036f), end = Offset(53.570126f, 45.374317f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.020655f, 1.5003424f)
    lineTo(32.24856f, 1.5003424f)
    lineTo(44.496456f, 13.922717f)
    lineTo(44.496456f, 31.037f)
    lineTo(32.638474f, 42.48783f)
    lineTo(15.870253f, 42.48783f)
    lineTo(3.5090792f, 30.208717f)
    lineTo(3.5090792f, 13.84561f)
    lineTo(16.020655f, 1.5003424f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28977272f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(15.6875f, 0.75f)
    lineTo(2.75f, 13.5625f)
    lineTo(2.75f, 30.5625f)
    lineTo(5.6875f, 33.46875f)
    cubicTo(22.45004f, 33.5263f, 22.164665f, 20.450068f, 45.25f, 21.59375f)
    lineTo(45.25f, 13.6875f)
    lineTo(32.5625f, 0.75f)
    lineTo(15.6875f, 0.75f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), center = Offset(16.749996f, 10.609468f), radius = 88.29283f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.767176f, 10.5f)
    lineTo(12.5f, 14.767175f)
    lineTo(20.035074f, 22.30225f)
    lineTo(12.5f, 29.837324f)
    lineTo(16.767176f, 34.1045f)
    lineTo(24.30225f, 26.569426f)
    lineTo(31.837324f, 34.1045f)
    lineTo(36.1045f, 29.837324f)
    lineTo(28.569426f, 22.30225f)
    lineTo(36.1045f, 14.767175f)
    lineTo(31.837324f, 10.5f)
    lineTo(24.30225f, 18.035074f)
    lineTo(16.767176f, 10.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(219, 219, 219, 255), center = Offset(24.30226f, 31.302269f), radius = 20.839777f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(255, 2, 2, 255), 1.0f to Color(255, 155, 155, 255), start = Offset(21.75f, 13.80225f), end = Offset(24.30225f, 33.05225f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(16.767176f, 10.5f)
    lineTo(12.5f, 14.767175f)
    lineTo(20.035074f, 22.30225f)
    lineTo(12.5f, 29.837324f)
    lineTo(16.767176f, 34.1045f)
    lineTo(24.30225f, 26.569426f)
    lineTo(31.837324f, 34.1045f)
    lineTo(36.1045f, 29.837324f)
    lineTo(28.569426f, 22.30225f)
    lineTo(36.1045f, 14.767175f)
    lineTo(31.837324f, 10.5f)
    lineTo(24.30225f, 18.035074f)
    lineTo(16.767176f, 10.5f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 1.9943857192993164
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
            return 44.00320053100586
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.27521514892578
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

