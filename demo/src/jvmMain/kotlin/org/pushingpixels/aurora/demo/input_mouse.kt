package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
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
class input_mouse private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.6841890215873718f, 0.0f, 0.0f, 7.976597785949707f,
0.0f, 1.2410709857940674f, 0.0f, -9.191010475158691f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(47.174126f, 32.342636f)
generalPath!!.cubicTo(47.21071f, 37.410202f, 42.867462f, 42.10255f, 35.788975f, 44.6429f)
generalPath!!.cubicTo(28.710491f, 47.183247f, 19.978865f, 47.183247f, 12.90038f, 44.6429f)
generalPath!!.cubicTo(5.821895f, 42.10255f, 1.4786458f, 37.410202f, 1.5152302f, 32.342636f)
generalPath!!.cubicTo(1.4786458f, 27.27507f, 5.821895f, 22.58272f, 12.90038f, 20.042374f)
generalPath!!.cubicTo(19.978865f, 17.502026f, 28.710491f, 17.502026f, 35.788975f, 20.042374f)
generalPath!!.cubicTo(42.867462f, 22.58272f, 47.21071f, 27.27507f, 47.174126f, 32.342636f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), centerX = 24.344677f, centerY = 32.34263f, radius = 22.829449f, tileMode = TileMode.Clamp)
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
brush = LinearGradient(0.0f to Color(172, 172, 172, 255), 1.0f to Color(141, 141, 141, 0), startX = 34.213875f, startY = 4.375f, endX = 34.88611f, endY = -1.0f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000004f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(23.922983f, 10.5f)
generalPath!!.cubicTo(20.560867f, 3.0f, 22.031794f, 1.75f, 28.966158f, 3.75f)
generalPath!!.cubicTo(35.754646f, 5.7079268f, 43.465282f, 14.0f, 42.83489f, 5.25f)
generalPath!!.cubicTo(42.167034f, -4.0199065f, 33.378937f, -8.75f, 33.378937f, -8.75f)
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
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
// _0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(9.939883f, 27.131191f)
generalPath!!.cubicTo(7.483519f, 38.037746f, 14.640836f, 45.54149f, 25.387142f, 45.54149f)
generalPath!!.cubicTo(36.017143f, 45.54149f, 41.302036f, 36.850883f, 39.11298f, 27.131191f)
generalPath!!.lineTo(35.62336f, 14.116623f)
generalPath!!.cubicTo(35.216236f, 12.293633f, 34.346813f, 11.710736f, 33.76223f, 11.512351f)
generalPath!!.cubicTo(28.039312f, 8.942826f, 19.976755f, 8.818981f, 14.94167f, 11.512351f)
generalPath!!.cubicTo(14.301906f, 11.859589f, 13.836623f, 11.859587f, 13.371341f, 13.59577f)
generalPath!!.lineTo(9.939883f, 27.131191f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = RadialGradient(0.0f to Color(255, 255, 255, 255), 0.37333333f to Color(217, 217, 217, 255), 0.6031111f to Color(197, 197, 197, 255), 1.0f to Color(144, 144, 144, 255), centerX = 21.434301f, centerY = 13.8242f, radius = 24.751009f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(79, 79, 79, 255))
stroke = Stroke(width=1.0000007f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(9.939883f, 27.131191f)
generalPath!!.cubicTo(7.483519f, 38.037746f, 14.640836f, 45.54149f, 25.387142f, 45.54149f)
generalPath!!.cubicTo(36.017143f, 45.54149f, 41.302036f, 36.850883f, 39.11298f, 27.131191f)
generalPath!!.lineTo(35.62336f, 14.116623f)
generalPath!!.cubicTo(35.216236f, 12.293633f, 34.346813f, 11.710736f, 33.76223f, 11.512351f)
generalPath!!.cubicTo(28.039312f, 8.942826f, 19.976755f, 8.818981f, 14.94167f, 11.512351f)
generalPath!!.cubicTo(14.301906f, 11.859589f, 13.836623f, 11.859587f, 13.371341f, 13.59577f)
generalPath!!.lineTo(9.939883f, 27.131191f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8405290246009827f, 0.0f, 0.0f, 4.170553207397461f,
0.0f, 1.0f, 0.0f, 1.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_3
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
// _0_0_3_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.573669f, 22.948217f)
generalPath!!.cubicTo(16.805824f, 17.644917f, 29.854662f, 17.038826f, 38.524693f, 22.342127f)
generalPath!!.cubicTo(30.832369f, 18.464708f, 17.818977f, 17.99819f, 8.573669f, 22.948217f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 203))
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
// _0_0_3_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(8.750446f, 22.72725f)
generalPath!!.cubicTo(17.380348f, 16.49587f, 30.031439f, 16.42011f, 38.436306f, 22.25374f)
generalPath!!.cubicTo(30.65559f, 17.580828f, 17.686396f, 17.291086f, 8.750446f, 22.72725f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 66))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
}
alpha = alphaStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
1.6663429737091064f, 0.0f, 0.0f, -15.706390380859375f,
0.0f, 1.4178069829940796f, 0.0f, 0.3478730022907257f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.16295f, 11.331463f)
generalPath!!.cubicTo(26.166431f, 13.032716f, 25.753246f, 14.608005f, 25.079851f, 15.460835f)
generalPath!!.cubicTo(24.406454f, 16.313665f, 23.575792f, 16.313665f, 22.902395f, 15.460835f)
generalPath!!.cubicTo(22.229f, 14.608005f, 21.815815f, 13.032716f, 21.819296f, 11.331463f)
generalPath!!.cubicTo(21.815815f, 9.63021f, 22.229f, 8.054921f, 22.902395f, 7.2020903f)
generalPath!!.cubicTo(23.575792f, 6.34926f, 24.406454f, 6.34926f, 25.079851f, 7.2020903f)
generalPath!!.cubicTo(25.753246f, 8.054921f, 26.166431f, 9.63021f, 26.16295f, 11.331463f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(169, 169, 169, 255), startX = 23.991123f, startY = 12.830256f, endX = 23.991123f, endY = 7.1877394f, tileMode = TileMode.Clamp)
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
// _0_0_5
brush = LinearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 0), startX = 14.185482f, startY = 3.4829133f, endX = 18.192976f, endY = 57.399807f, tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0000002f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(10.487085f, 28.908148f)
generalPath!!.cubicTo(9.079121f, 37.36856f, 15.125142f, 44.574368f, 25.45902f, 44.574368f)
generalPath!!.cubicTo(35.792843f, 44.574368f, 39.74773f, 35.87646f, 38.57301f, 28.908148f)
generalPath!!.lineTo(34.847874f, 14.950287f)
generalPath!!.cubicTo(34.538773f, 12.75438f, 33.628273f, 12.542565f, 33.08904f, 12.330979f)
generalPath!!.cubicTo(27.237518f, 10.034947f, 20.414902f, 9.845529f, 15.647667f, 12.330979f)
generalPath!!.cubicTo(15.050261f, 12.642443f, 14.555416f, 12.524067f, 14.192413f, 14.108229f)
generalPath!!.lineTo(10.487085f, 28.908148f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.8405290246009827f, 0.0f, 0.0f, 4.085647106170654f,
0.0f, 1.0f, 0.0f, 2.7425169944763184f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_6
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.16295f, 11.331463f)
generalPath!!.cubicTo(26.166431f, 13.032716f, 25.753246f, 14.608005f, 25.079851f, 15.460835f)
generalPath!!.cubicTo(24.406454f, 16.313665f, 23.575792f, 16.313665f, 22.902395f, 15.460835f)
generalPath!!.cubicTo(22.229f, 14.608005f, 21.815815f, 13.032716f, 21.819296f, 11.331463f)
generalPath!!.cubicTo(21.815815f, 9.63021f, 22.229f, 8.054921f, 22.902395f, 7.2020903f)
generalPath!!.cubicTo(23.575792f, 6.34926f, 24.406454f, 6.34926f, 25.079851f, 7.2020903f)
generalPath!!.cubicTo(25.753246f, 8.054921f, 26.166431f, 9.63021f, 26.16295f, 11.331463f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = LinearGradient(0.0f to Color(177, 177, 177, 255), 0.2f to Color(223, 223, 223, 255), 1.0f to Color(86, 86, 86, 255), startX = 23.991123f, startY = 6.583746f, endX = 23.991123f, endY = 15.069027f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
brush = SolidColor(Color(89, 89, 89, 255))
stroke = Stroke(width=0.5453732f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.16295f, 11.331463f)
generalPath!!.cubicTo(26.166431f, 13.032716f, 25.753246f, 14.608005f, 25.079851f, 15.460835f)
generalPath!!.cubicTo(24.406454f, 16.313665f, 23.575792f, 16.313665f, 22.902395f, 15.460835f)
generalPath!!.cubicTo(22.229f, 14.608005f, 21.815815f, 13.032716f, 21.819296f, 11.331463f)
generalPath!!.cubicTo(21.815815f, 9.63021f, 22.229f, 8.054921f, 22.902395f, 7.2020903f)
generalPath!!.cubicTo(23.575792f, 6.34926f, 24.406454f, 6.34926f, 25.079851f, 7.2020903f)
generalPath!!.cubicTo(25.753246f, 8.054921f, 26.166431f, 9.63021f, 26.16295f, 11.331463f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha)
}
alpha = alphaStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
withTransform({
transform(
Matrix(values=floatArrayOf(
0.23456500470638275f, 0.0f, 0.0f, 17.98657989501953f,
0.0f, 0.5425530076026917f, 0.0f, 6.360321998596191f,
0.0f, 0.0f, 1.0f, 0.0f,
0.0f, 0.0f, 0.0f, 1.0f)
))}){
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(26.16295f, 11.331463f)
generalPath!!.cubicTo(26.166431f, 13.032716f, 25.753246f, 14.608005f, 25.079851f, 15.460835f)
generalPath!!.cubicTo(24.406454f, 16.313665f, 23.575792f, 16.313665f, 22.902395f, 15.460835f)
generalPath!!.cubicTo(22.229f, 14.608005f, 21.815815f, 13.032716f, 21.819296f, 11.331463f)
generalPath!!.cubicTo(21.815815f, 9.63021f, 22.229f, 8.054921f, 22.902395f, 7.2020903f)
generalPath!!.cubicTo(23.575792f, 6.34926f, 24.406454f, 6.34926f, 25.079851f, 7.2020903f)
generalPath!!.cubicTo(25.753246f, 8.054921f, 26.166431f, 9.63021f, 26.16295f, 11.331463f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 255, 255, 85))
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
            return 7.301002025604248
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
            return 36.11579895019531
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 48.0
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
            return input_mouse(
                _width = (width.value * AmbientDensity.current.density).toInt(),
                _height = (height.value * AmbientDensity.current.density).toInt()
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
                    return input_mouse(getOrigWidth().toInt(), getOrigHeight().toInt())
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
        _width = (width.value * AmbientDensity.current.density).toInt()
        _height = (height.value * AmbientDensity.current.density).toInt()
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
                scale(scaleX = coefDp, scaleY = coefDp, pivot = Offset(0.0f, 0.0f))
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

