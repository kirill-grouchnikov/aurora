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

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
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
-0.0f, -0.0f, 0.0f, 1.0f)
))}){
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
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(0.0f, 0.0f)
generalPath!!.lineTo(744.09f, 0.0f)
generalPath!!.lineTo(744.09f, 496.06f)
generalPath!!.lineTo(0.0f, 496.06f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(222, 33, 16, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(0.0f, 0.0f)
generalPath!!.lineTo(373.69f, 0.0f)
generalPath!!.lineTo(373.69f, 248.03f)
generalPath!!.lineTo(0.0f, 248.03f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(8, 57, 156, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(273.324f, 173.001f)
generalPath!!.lineTo(227.464f, 163.57101f)
generalPath!!.lineTo(237.23401f, 209.36102f)
generalPath!!.lineTo(202.22401f, 178.27103f)
generalPath!!.lineTo(187.78401f, 222.81104f)
generalPath!!.lineTo(173.014f, 178.37103f)
generalPath!!.lineTo(138.244f, 209.73103f)
generalPath!!.lineTo(147.67401f, 163.87103f)
generalPath!!.lineTo(101.88401f, 173.63103f)
generalPath!!.lineTo(132.97401f, 138.63103f)
generalPath!!.lineTo(88.43501f, 124.191025f)
generalPath!!.lineTo(132.86502f, 109.42102f)
generalPath!!.lineTo(101.51502f, 74.648026f)
generalPath!!.lineTo(147.37503f, 84.07503f)
generalPath!!.lineTo(137.61504f, 38.283028f)
generalPath!!.lineTo(172.61504f, 69.37803f)
generalPath!!.lineTo(187.05504f, 24.838028f)
generalPath!!.lineTo(201.82504f, 69.27003f)
generalPath!!.lineTo(236.59505f, 37.916027f)
generalPath!!.lineTo(227.16504f, 83.77902f)
generalPath!!.lineTo(272.95505f, 74.012024f)
generalPath!!.lineTo(241.86505f, 109.02202f)
generalPath!!.lineTo(286.40506f, 123.45202f)
generalPath!!.lineTo(241.97507f, 138.22202f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(231.045f, 169.48f)
generalPath!!.lineTo(216.575f, 177.164f)
generalPath!!.lineTo(202.458f, 185.504f)
generalPath!!.lineTo(186.06699f, 184.94f)
generalPath!!.lineTo(169.67499f, 185.131f)
generalPath!!.lineTo(155.76099f, 176.468f)
generalPath!!.lineTo(141.46298f, 168.451f)
generalPath!!.lineTo(133.75699f, 154.009f)
generalPath!!.lineTo(125.39499f, 139.94f)
generalPath!!.lineTo(125.95099f, 123.593f)
generalPath!!.lineTo(125.76899f, 107.235f)
generalPath!!.lineTo(134.45499f, 93.361f)
generalPath!!.lineTo(142.49399f, 79.103f)
generalPath!!.lineTo(156.96399f, 71.416f)
generalPath!!.lineTo(171.081f, 63.076f)
generalPath!!.lineTo(187.472f, 63.638f)
generalPath!!.lineTo(203.874f, 63.452f)
generalPath!!.lineTo(217.788f, 72.112f)
generalPath!!.lineTo(232.07599f, 80.13f)
generalPath!!.lineTo(239.78198f, 94.56799f)
generalPath!!.lineTo(248.14398f, 108.63699f)
generalPath!!.lineTo(247.58798f, 124.98399f)
generalPath!!.lineTo(247.76999f, 141.342f)
generalPath!!.lineTo(239.08398f, 155.219f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 83, 135, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(237.028f, 124.291f)
generalPath!!.cubicTo(237.028f, 152.045f, 214.52899f, 174.544f, 186.775f, 174.544f)
generalPath!!.cubicTo(159.021f, 174.544f, 136.522f, 152.04501f, 136.522f, 124.29101f)
generalPath!!.cubicTo(136.522f, 96.537f, 159.022f, 74.03801f, 186.775f, 74.03801f)
generalPath!!.cubicTo(214.52798f, 74.03801f, 237.02798f, 96.53801f, 237.02798f, 124.29101f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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

