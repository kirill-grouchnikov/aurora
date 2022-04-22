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
class gb : Painter() {
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
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0240000486373901f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0240000486373901f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-256.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(0.0f, 0.0f)
generalPath!!.lineTo(1000.02f, 0.0f)
generalPath!!.lineTo(1000.02f, 500.01f)
generalPath!!.lineTo(0.0f, 500.01f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 102, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(0.0f, 0.0f)
generalPath!!.lineTo(0.0f, 55.903f)
generalPath!!.lineTo(888.218f, 500.013f)
generalPath!!.lineTo(1000.02f, 500.013f)
generalPath!!.lineTo(1000.02f, 444.11f)
generalPath!!.lineTo(111.802f, 0.003f)
generalPath!!.lineTo(0.0f, 0.003f)
generalPath!!.close()
generalPath!!.moveTo(1000.02f, 0.0f)
generalPath!!.lineTo(1000.02f, 55.9f)
generalPath!!.lineTo(111.802f, 500.01f)
generalPath!!.lineTo(0.0f, 500.01f)
generalPath!!.lineTo(0.0f, 444.11002f)
generalPath!!.lineTo(888.218f, 0.0f)
generalPath!!.lineTo(1000.02f, 0.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(416.675f, 0.0f)
generalPath!!.lineTo(416.675f, 500.01f)
generalPath!!.lineTo(583.345f, 500.01f)
generalPath!!.lineTo(583.345f, 0.0f)
generalPath!!.lineTo(416.675f, 0.0f)
generalPath!!.close()
generalPath!!.moveTo(0.0f, 166.67f)
generalPath!!.lineTo(0.0f, 333.34f)
generalPath!!.lineTo(1000.02f, 333.34f)
generalPath!!.lineTo(1000.02f, 166.67f)
generalPath!!.lineTo(0.0f, 166.67f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_3
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(0.0f, 200.004f)
generalPath!!.lineTo(0.0f, 300.00598f)
generalPath!!.lineTo(1000.02f, 300.00598f)
generalPath!!.lineTo(1000.02f, 200.004f)
generalPath!!.lineTo(0.0f, 200.004f)
generalPath!!.close()
generalPath!!.moveTo(450.01f, 0.0f)
generalPath!!.lineTo(450.01f, 500.01f)
generalPath!!.lineTo(550.01f, 500.01f)
generalPath!!.lineTo(550.01f, 0.0f)
generalPath!!.lineTo(450.01f, 0.0f)
generalPath!!.close()
generalPath!!.moveTo(0.0f, 500.01f)
generalPath!!.lineTo(333.34f, 333.34003f)
generalPath!!.lineTo(407.875f, 333.34003f)
generalPath!!.lineTo(74.535f, 500.01f)
generalPath!!.lineTo(0.0f, 500.01f)
generalPath!!.close()
generalPath!!.moveTo(0.0f, 0.0f)
generalPath!!.lineTo(333.34f, 166.67f)
generalPath!!.lineTo(258.805f, 166.67f)
generalPath!!.lineTo(0.0f, 37.27f)
generalPath!!.lineTo(0.0f, 0.0f)
generalPath!!.close()
generalPath!!.moveTo(592.145f, 166.67f)
generalPath!!.lineTo(925.485f, 0.0f)
generalPath!!.lineTo(1000.02f, 0.0f)
generalPath!!.lineTo(666.68f, 166.67f)
generalPath!!.lineTo(592.145f, 166.67f)
generalPath!!.close()
generalPath!!.moveTo(1000.02f, 500.01f)
generalPath!!.lineTo(666.68f, 333.34f)
generalPath!!.lineTo(741.21497f, 333.34f)
generalPath!!.lineTo(1000.01996f, 462.74298f)
generalPath!!.lineTo(1000.01996f, 500.00998f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(204, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
            return 1.2159347534179688E-5
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

