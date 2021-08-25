package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
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
class process_stop private constructor(var _width: Dp, var _height: Dp) : AuroraIcon() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var alphaStack = mutableListOf(1.0f)
    private var colorFilter: ((Color) -> Color)? = null

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
alpha *= 0.6306818f
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
generalPath!!.moveTo(40.875f, 36.75f)
generalPath!!.cubicTo(40.900238f, 40.109352f, 37.90384f, 43.21997f, 33.020405f, 44.904f)
generalPath!!.cubicTo(28.136969f, 46.588028f, 22.113031f, 46.588028f, 17.229597f, 44.904f)
generalPath!!.cubicTo(12.346162f, 43.21997f, 9.34976f, 40.109352f, 9.375f, 36.75f)
generalPath!!.cubicTo(9.34976f, 33.390648f, 12.346162f, 30.28003f, 17.229597f, 28.596f)
generalPath!!.cubicTo(22.113031f, 26.911972f, 28.136969f, 26.911972f, 33.020405f, 28.596f)
generalPath!!.cubicTo(37.90384f, 30.28003f, 40.900238f, 33.390648f, 40.875f, 36.75f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(0, 0, 0, 255)) ?: Color(0, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(0, 0, 0, 0)) ?: Color(0, 0, 0, 0)), center = Offset(25.125f, 36.749996f), radius = 15.75f, tileMode = TileMode.Clamp)
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
generalPath!!.moveTo(15.591006f, 0.4919213f)
generalPath!!.lineTo(32.67631f, 0.4919213f)
generalPath!!.lineTo(45.497585f, 13.586385f)
generalPath!!.lineTo(45.497585f, 31.48003f)
generalPath!!.lineTo(32.848988f, 43.49693f)
generalPath!!.lineTo(15.418649f, 43.49693f)
generalPath!!.lineTo(2.4943857f, 30.658264f)
generalPath!!.lineTo(2.4943857f, 13.464078f)
generalPath!!.lineTo(15.591006f, 0.4919213f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(204, 0, 0, 255)) ?: Color(204, 0, 0, 255)), 1.0f to (colorFilter?.invoke(Color(179, 0, 0, 255)) ?: Color(179, 0, 0, 255)), start = Offset(23.995985f, 18.105337f), end = Offset(41.047836f, 35.959785f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(colorFilter?.invoke(Color(134, 0, 0, 255)) ?: Color(134, 0, 0, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(15.591006f, 0.4919213f)
generalPath!!.lineTo(32.67631f, 0.4919213f)
generalPath!!.lineTo(45.497585f, 13.586385f)
generalPath!!.lineTo(45.497585f, 31.48003f)
generalPath!!.lineTo(32.848988f, 43.49693f)
generalPath!!.lineTo(15.418649f, 43.49693f)
generalPath!!.lineTo(2.4943857f, 30.658264f)
generalPath!!.lineTo(2.4943857f, 13.464078f)
generalPath!!.lineTo(15.591006f, 0.4919213f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.8131868f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_2
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 139, 139, 255)) ?: Color(255, 139, 139, 255)), 1.0f to (colorFilter?.invoke(Color(236, 27, 27, 255)) ?: Color(236, 27, 27, 255)), start = Offset(15.737001f, 10.5036f), end = Offset(53.570126f, 45.374317f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.020655f, 1.5003424f)
generalPath!!.lineTo(32.24856f, 1.5003424f)
generalPath!!.lineTo(44.496456f, 13.922717f)
generalPath!!.lineTo(44.496456f, 31.037f)
generalPath!!.lineTo(32.638474f, 42.48783f)
generalPath!!.lineTo(15.870253f, 42.48783f)
generalPath!!.lineTo(3.5090792f, 30.208717f)
generalPath!!.lineTo(3.5090792f, 13.84561f)
generalPath!!.lineTo(16.020655f, 1.5003424f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.28977272f
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
generalPath!!.moveTo(15.6875f, 0.75f)
generalPath!!.lineTo(2.75f, 13.5625f)
generalPath!!.lineTo(2.75f, 30.5625f)
generalPath!!.lineTo(5.6875f, 33.46875f)
generalPath!!.cubicTo(22.45004f, 33.5263f, 22.164665f, 20.450068f, 45.25f, 21.59375f)
generalPath!!.lineTo(45.25f, 13.6875f)
generalPath!!.lineTo(32.5625f, 0.75f)
generalPath!!.lineTo(15.6875f, 0.75f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(255, 255, 255, 0)) ?: Color(255, 255, 255, 0)), center = Offset(16.749996f, 10.609468f), radius = 88.29283f, tileMode = TileMode.Clamp)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.767176f, 10.5f)
generalPath!!.lineTo(12.5f, 14.767175f)
generalPath!!.lineTo(20.035074f, 22.30225f)
generalPath!!.lineTo(12.5f, 29.837324f)
generalPath!!.lineTo(16.767176f, 34.1045f)
generalPath!!.lineTo(24.30225f, 26.569426f)
generalPath!!.lineTo(31.837324f, 34.1045f)
generalPath!!.lineTo(36.1045f, 29.837324f)
generalPath!!.lineTo(28.569426f, 22.30225f)
generalPath!!.lineTo(36.1045f, 14.767175f)
generalPath!!.lineTo(31.837324f, 10.5f)
generalPath!!.lineTo(24.30225f, 18.035074f)
generalPath!!.lineTo(16.767176f, 10.5f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to (colorFilter?.invoke(Color(255, 255, 255, 255)) ?: Color(255, 255, 255, 255)), 1.0f to (colorFilter?.invoke(Color(219, 219, 219, 255)) ?: Color(219, 219, 219, 255)), center = Offset(24.30226f, 31.302269f), radius = 20.839777f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = Brush.linearGradient(0.0f to (colorFilter?.invoke(Color(255, 2, 2, 255)) ?: Color(255, 2, 2, 255)), 1.0f to (colorFilter?.invoke(Color(255, 155, 155, 255)) ?: Color(255, 155, 155, 255)), start = Offset(21.75f, 13.80225f), end = Offset(24.30225f, 33.05225f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9999996f, cap=StrokeCap.Round, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(16.767176f, 10.5f)
generalPath!!.lineTo(12.5f, 14.767175f)
generalPath!!.lineTo(20.035074f, 22.30225f)
generalPath!!.lineTo(12.5f, 29.837324f)
generalPath!!.lineTo(16.767176f, 34.1045f)
generalPath!!.lineTo(24.30225f, 26.569426f)
generalPath!!.lineTo(31.837324f, 34.1045f)
generalPath!!.lineTo(36.1045f, 29.837324f)
generalPath!!.lineTo(28.569426f, 22.30225f)
generalPath!!.lineTo(36.1045f, 14.767175f)
generalPath!!.lineTo(31.837324f, 10.5f)
generalPath!!.lineTo(24.30225f, 18.035074f)
generalPath!!.lineTo(16.767176f, 10.5f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
            return 47.52781677246094
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
            return process_stop(_width = width, _height = height)
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return process_stop(getOrigWidth().dp, getOrigHeight().dp)
                }
            }
        }

        
    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        setSize(size.width.toDp(), size.height.toDp())
        paintIcon(this)
    }

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    override fun setSize(width: Dp, height: Dp) {
        _width = width
        _height = height
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        this.colorFilter = colorFilter
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            clipRect {
                // Use the original icon bounding box and the current icon dimension to compute
                // the scaling factor
                val fullOrigWidth = getOrigX() + getOrigWidth()
                val fullOrigHeight = getOrigY() + getOrigHeight()
                val coef1 = _width.toPx() / fullOrigWidth
                val coef2 = _height.toPx() / fullOrigHeight
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
}

