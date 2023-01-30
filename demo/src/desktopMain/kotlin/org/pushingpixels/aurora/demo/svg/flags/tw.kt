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
class tw : Painter() {
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
1.032099962234497f, 0.0f, 0.0f, 0.0f,
0.0f, 1.032099962234497f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
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
    moveTo(0.0f, 0.0f)
    lineTo(744.09f, 0.0f)
    lineTo(744.09f, 496.06f)
    lineTo(0.0f, 496.06f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(222, 33, 16, 255))
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
    moveTo(0.0f, 0.0f)
    lineTo(373.69f, 0.0f)
    lineTo(373.69f, 248.03f)
    lineTo(0.0f, 248.03f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(8, 57, 156, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(273.324f, 173.001f)
    lineTo(227.464f, 163.57101f)
    lineTo(237.23401f, 209.36102f)
    lineTo(202.22401f, 178.27103f)
    lineTo(187.78401f, 222.81104f)
    lineTo(173.014f, 178.37103f)
    lineTo(138.244f, 209.73103f)
    lineTo(147.67401f, 163.87103f)
    lineTo(101.88401f, 173.63103f)
    lineTo(132.97401f, 138.63103f)
    lineTo(88.43501f, 124.191025f)
    lineTo(132.86502f, 109.42102f)
    lineTo(101.51502f, 74.648026f)
    lineTo(147.37503f, 84.07503f)
    lineTo(137.61504f, 38.283028f)
    lineTo(172.61504f, 69.37803f)
    lineTo(187.05504f, 24.838028f)
    lineTo(201.82504f, 69.27003f)
    lineTo(236.59505f, 37.916027f)
    lineTo(227.16504f, 83.77902f)
    lineTo(272.95505f, 74.012024f)
    lineTo(241.86505f, 109.02202f)
    lineTo(286.40506f, 123.45202f)
    lineTo(241.97507f, 138.22202f)
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
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(231.045f, 169.48f)
    lineTo(216.575f, 177.164f)
    lineTo(202.458f, 185.504f)
    lineTo(186.06699f, 184.94f)
    lineTo(169.67499f, 185.131f)
    lineTo(155.76099f, 176.468f)
    lineTo(141.46298f, 168.451f)
    lineTo(133.75699f, 154.009f)
    lineTo(125.39499f, 139.94f)
    lineTo(125.95099f, 123.593f)
    lineTo(125.76899f, 107.235f)
    lineTo(134.45499f, 93.361f)
    lineTo(142.49399f, 79.103f)
    lineTo(156.96399f, 71.416f)
    lineTo(171.081f, 63.076f)
    lineTo(187.472f, 63.638f)
    lineTo(203.874f, 63.452f)
    lineTo(217.788f, 72.112f)
    lineTo(232.07599f, 80.13f)
    lineTo(239.78198f, 94.56799f)
    lineTo(248.14398f, 108.63699f)
    lineTo(247.58798f, 124.98399f)
    lineTo(247.76999f, 141.342f)
    lineTo(239.08398f, 155.219f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 83, 135, 255))
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
    moveTo(237.028f, 124.291f)
    cubicTo(237.028f, 152.045f, 214.52899f, 174.544f, 186.775f, 174.544f)
    cubicTo(159.021f, 174.544f, 136.522f, 152.04501f, 136.522f, 124.29101f)
    cubicTo(136.522f, 96.537f, 159.022f, 74.03801f, 186.775f, 74.03801f)
    cubicTo(214.52798f, 74.03801f, 237.02798f, 96.53801f, 237.02798f, 124.29101f)
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
            return 511.9834899902344
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 511.9834899902344
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

