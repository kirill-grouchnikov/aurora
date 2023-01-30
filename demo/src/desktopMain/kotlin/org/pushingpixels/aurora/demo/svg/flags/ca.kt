package org.pushingpixels.aurora.demo.svg.flags

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
class ca : Painter() {
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.18199999630451202f, -3.0250000953674316f, 0.0f, 1.0f)
))}){
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
    moveTo(81.32f, 3.025f)
    lineTo(443.595f, 3.025f)
    lineTo(443.595f, 515.025f)
    lineTo(81.32f, 515.025f)
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
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-99.818f, 3.025f)
    lineTo(81.32f, 3.025f)
    lineTo(81.32f, 515.025f)
    lineTo(-99.818f, 515.025f)
    close()
    moveTo(443.59503f, 3.025f)
    lineTo(624.73303f, 3.025f)
    lineTo(624.73303f, 515.025f)
    lineTo(443.595f, 515.025f)
    close()
    moveTo(135.49f, 250.44f)
    lineTo(121.423004f, 255.248f)
    lineTo(186.879f, 312.694f)
    cubicTo(191.829f, 327.458f, 185.159f, 331.81f, 180.909f, 339.55402f)
    lineTo(251.969f, 330.53403f)
    lineTo(250.11899f, 402.04602f)
    lineTo(264.83698f, 401.62302f)
    lineTo(261.62698f, 330.70502f)
    lineTo(332.757f, 339.13702f)
    cubicTo(328.35498f, 329.84003f, 324.43698f, 324.90402f, 328.50998f, 310.03903f)
    lineTo(393.92398f, 255.61304f)
    lineTo(382.477f, 251.46904f)
    cubicTo(373.117f, 244.24704f, 386.521f, 216.68504f, 388.543f, 199.29105f)
    cubicTo(388.543f, 199.29105f, 350.348f, 212.42604f, 347.845f, 205.55304f)
    lineTo(338.118f, 186.86804f)
    lineTo(303.371f, 225.03804f)
    cubicTo(299.575f, 225.94804f, 297.958f, 224.43803f, 297.06702f, 221.23004f)
    lineTo(313.12003f, 141.46405f)
    lineTo(287.7f, 155.76105f)
    cubicTo(285.57202f, 156.67105f, 283.444f, 155.88605f, 282.04202f, 153.40605f)
    lineTo(257.59702f, 104.33305f)
    lineTo(232.38702f, 155.28505f)
    cubicTo(230.48703f, 157.11105f, 228.58502f, 157.32205f, 227.00702f, 156.08105f)
    lineTo(202.8f, 142.505f)
    lineTo(217.33f, 221.64801f)
    cubicTo(216.174f, 224.79001f, 213.406f, 225.67401f, 210.15001f, 223.973f)
    lineTo(176.934f, 186.23601f)
    cubicTo(172.589f, 193.19801f, 169.64401f, 204.572f, 163.901f, 207.121f)
    cubicTo(158.157f, 209.509f, 138.921f, 202.299f, 126.028f, 199.485f)
    cubicTo(130.432f, 215.38f, 144.204f, 241.787f, 135.488f, 250.442f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(191, 10, 48, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
            return 0.0
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
            return 512.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 512.0
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

