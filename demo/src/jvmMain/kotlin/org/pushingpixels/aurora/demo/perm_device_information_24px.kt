package org.pushingpixels.aurora.demo
    
    import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.skija.PathEffect
import org.pushingpixels.aurora.icon.AuroraIcon
import kotlin.math.min
    
    /**
* This class has been automatically generated using
* <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
*/
class perm_device_information_24px private constructor(var _width: Int, var _height: Int) : AuroraIcon {
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
1.0f, 0.0f, 0.0f, -0.0f,
0.0f, 1.0f, 0.0f, -0.0f,
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
// _0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath!!.moveTo(13.0f, 7.0f)
generalPath!!.lineTo(11.0f, 7.0f)
generalPath!!.lineTo(11.0f, 9.0f)
generalPath!!.lineTo(13.0f, 9.0f)
generalPath!!.lineTo(13.0f, 7.0f)
generalPath!!.close()
generalPath!!.moveTo(13.0f, 11.0f)
generalPath!!.lineTo(11.0f, 11.0f)
generalPath!!.lineTo(11.0f, 17.0f)
generalPath!!.lineTo(13.0f, 17.0f)
generalPath!!.lineTo(13.0f, 11.0f)
generalPath!!.close()
generalPath!!.moveTo(17.0f, 1.0100002f)
generalPath!!.lineTo(7.0f, 1.0f)
generalPath!!.cubicTo(5.9f, 1.0f, 5.0f, 1.9f, 5.0f, 3.0f)
generalPath!!.lineTo(5.0f, 21.0f)
generalPath!!.cubicTo(5.0f, 22.1f, 5.9f, 23.0f, 7.0f, 23.0f)
generalPath!!.lineTo(17.0f, 23.0f)
generalPath!!.cubicTo(18.1f, 23.0f, 19.0f, 22.1f, 19.0f, 21.0f)
generalPath!!.lineTo(19.0f, 3.0f)
generalPath!!.cubicTo(19.0f, 1.9f, 18.1f, 1.01f, 17.0f, 1.01f)
generalPath!!.close()
generalPath!!.moveTo(17.0f, 19.0f)
generalPath!!.lineTo(7.0f, 19.0f)
generalPath!!.lineTo(7.0f, 5.0f)
generalPath!!.lineTo(17.0f, 5.0f)
generalPath!!.lineTo(17.0f, 19.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha)
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
return 5.0
}
    
    /**
* Returns the Y of the bounding box of the original SVG image.
*
* @return The Y of the bounding box of the original SVG image.
*/
fun getOrigY(): Double {
return 1.0
}
    
    /**
* Returns the width of the bounding box of the original SVG image.
*
* @return The width of the bounding box of the original SVG image.
*/
fun getOrigWidth(): Double {
return 14.0
}
    
    /**
* Returns the height of the bounding box of the original SVG image.
*
* @return The height of the bounding box of the original SVG image.
*/
fun getOrigHeight(): Double {
return 22.0
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
return perm_device_information_24px(
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
return perm_device_information_24px(getOrigWidth().toInt(), getOrigHeight().toInt())
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

