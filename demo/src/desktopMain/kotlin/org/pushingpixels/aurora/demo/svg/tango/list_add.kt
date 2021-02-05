package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.toComposeBitmap
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class list_add private constructor(var _width: Int, var _height: Int) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)

	private fun _paint0(drawScope : DrawScope) {
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0
alphaStack.add(0, alpha)
alpha *= 0.10824742f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.5504870414733887f, 0.0f, 0.0f, -12.481300354003906f,
0.0f, 1.9787139892578125f, 0.0f, -32.491031646728516f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(33.278214f, 34.94062f)
generalPath!!.cubicTo(33.29475f, 35.77202f, 31.33152f, 36.541855f, 28.131912f, 36.958633f)
generalPath!!.cubicTo(24.932302f, 37.37541f, 20.985443f, 37.37541f, 17.785833f, 36.958633f)
generalPath!!.cubicTo(14.5862255f, 36.541855f, 12.622995f, 35.77202f, 12.639532f, 34.94062f)
generalPath!!.cubicTo(12.622995f, 34.109222f, 14.5862255f, 33.339386f, 17.785833f, 32.922607f)
generalPath!!.cubicTo(20.985443f, 32.505833f, 24.932302f, 32.505833f, 28.131912f, 32.922607f)
generalPath!!.cubicTo(31.33152f, 33.339386f, 33.29475f, 34.109222f, 33.278214f, 34.94062f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(0.02137708f, 0.0072537824f), radius = 0.008674824f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
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
generalPath!!.moveTo(27.514357f, 37.542683f)
generalPath!!.lineTo(27.514357f, 28.515722f)
generalPath!!.lineTo(37.49282f, 28.475542f)
generalPath!!.lineTo(37.49282f, 21.480219f)
generalPath!!.lineTo(27.523285f, 21.480219f)
generalPath!!.lineTo(27.514357f, 11.520049f)
generalPath!!.lineTo(20.498081f, 11.53121f)
generalPath!!.lineTo(20.502546f, 21.462362f)
generalPath!!.lineTo(10.51292f, 21.536022f)
generalPath!!.lineTo(10.477206f, 28.50456f)
generalPath!!.lineTo(20.511475f, 28.475542f)
generalPath!!.lineTo(20.518171f, 37.515896f)
generalPath!!.lineTo(27.514357f, 37.542683f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(117, 161, 208, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(52, 101, 164, 255))
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(27.514357f, 37.542683f)
generalPath!!.lineTo(27.514357f, 28.515722f)
generalPath!!.lineTo(37.49282f, 28.475542f)
generalPath!!.lineTo(37.49282f, 21.480219f)
generalPath!!.lineTo(27.523285f, 21.480219f)
generalPath!!.lineTo(27.514357f, 11.520049f)
generalPath!!.lineTo(20.498081f, 11.53121f)
generalPath!!.lineTo(20.502546f, 21.462362f)
generalPath!!.lineTo(10.51292f, 21.536022f)
generalPath!!.lineTo(10.477206f, 28.50456f)
generalPath!!.lineTo(20.511475f, 28.475542f)
generalPath!!.lineTo(20.518171f, 37.515896f)
generalPath!!.lineTo(27.514357f, 37.542683f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40860215f
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
generalPath!!.moveTo(26.498701f, 36.53392f)
generalPath!!.lineTo(26.498701f, 27.499739f)
generalPath!!.lineTo(36.501305f, 27.499739f)
generalPath!!.lineTo(36.494606f, 22.47531f)
generalPath!!.lineTo(26.50763f, 22.47531f)
generalPath!!.lineTo(26.50763f, 12.480335f)
generalPath!!.lineTo(21.512796f, 12.498193f)
generalPath!!.lineTo(21.521725f, 22.47531f)
generalPath!!.lineTo(11.495536f, 22.493166f)
generalPath!!.lineTo(11.46875f, 27.466255f)
generalPath!!.lineTo(21.533142f, 27.475185f)
generalPath!!.lineTo(21.51975f, 36.50267f)
generalPath!!.lineTo(26.498701f, 36.53392f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(114, 159, 207, 255), 1.0f to Color(81, 135, 214, 255), start = Offset(-0.031098867f, -0.03246263f), end = Offset(-0.030917305f, -0.03268762f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 87), start = Offset(16.874998f, 22.851799f), end = Offset(27.900846f, 34.9768f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000006f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.498701f, 36.53392f)
generalPath!!.lineTo(26.498701f, 27.499739f)
generalPath!!.lineTo(36.501305f, 27.499739f)
generalPath!!.lineTo(36.494606f, 22.47531f)
generalPath!!.lineTo(26.50763f, 22.47531f)
generalPath!!.lineTo(26.50763f, 12.480335f)
generalPath!!.lineTo(21.512796f, 12.498193f)
generalPath!!.lineTo(21.521725f, 22.47531f)
generalPath!!.lineTo(11.495536f, 22.493166f)
generalPath!!.lineTo(11.46875f, 27.466255f)
generalPath!!.lineTo(21.533142f, 27.475185f)
generalPath!!.lineTo(21.51975f, 36.50267f)
generalPath!!.lineTo(26.498701f, 36.53392f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.31182796f
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
generalPath!!.moveTo(11.0f, 25.0f)
generalPath!!.cubicTo(11.0f, 26.9375f, 36.984375f, 24.03125f, 36.984375f, 24.96875f)
generalPath!!.lineTo(36.984375f, 21.96875f)
generalPath!!.lineTo(27.0f, 22.0f)
generalPath!!.lineTo(27.0f, 12.034772f)
generalPath!!.lineTo(21.0f, 12.034772f)
generalPath!!.lineTo(21.0f, 22.0f)
generalPath!!.lineTo(11.0f, 22.0f)
generalPath!!.lineTo(11.0f, 25.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)

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
            return 7.090490341186523
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 11.019253730773926
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 32.05128860473633
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 30.444957733154297
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return list_add(
                _width = (width.value * LocalDensity.current.density).toInt(),
                _height = (height.value * LocalDensity.current.density).toInt()
            )
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return list_add(getOrigWidth().toInt(), getOrigHeight().toInt())
                }
            }
        }

        
    }

    override fun getWidth(): Int {
        return _width
    }

    override fun getHeight(): Int {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = (width.value * LocalDensity.current.density).toInt()
        _height = (height.value * LocalDensity.current.density).toInt()
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = _width / fullOrigWidth
            val coef2 = _height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()
            val coefDp = coef.dp.toPx()

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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

