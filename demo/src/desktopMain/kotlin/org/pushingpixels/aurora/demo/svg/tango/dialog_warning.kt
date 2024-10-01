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
class dialog_warning : Painter() {
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
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.5666669607162476f, 0.0f, 0.0f, 0.0f,
0.0f, 1.5666669607162476f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-8.925565719604492f, -23.94763946533203f, 0.0f, 1.0f)
))}){
// _0_0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
0.014440740458667278f, 0.0f, 0.0f, 0.0f,
0.0f, 0.013319729827344418f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
33.388710021972656f, 40.40336990356445f, 0.0f, 1.0f)
))}){
// _0_0_0_0
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0_0
shape = Outline.Rectangle(rect = Rect(left = -1559.2523193359375f, top = -150.6968536376953f, right = -219.6187744140625f, bottom = 327.6603240966797f))
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 0), 0.5f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), start = Offset(-1051.9354f, -150.69684f), end = Offset(-1051.9354f, 327.6604f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-219.61876f, -150.68037f)
    cubicTo(-219.61876f, -150.68037f, -219.61876f, 327.65042f, -219.61876f, 327.65042f)
    cubicTo(-76.74459f, 328.55087f, 125.78146f, 220.48074f, 125.78138f, 88.45424f)
    cubicTo(125.78138f, -43.572304f, -33.655437f, -150.68036f, -219.61876f, -150.68037f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-211.146f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.40206185f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_0_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-1559.2523f, -150.68037f)
    cubicTo(-1559.2523f, -150.68037f, -1559.2523f, 327.65042f, -1559.2523f, 327.65042f)
    cubicTo(-1702.1265f, 328.55087f, -1904.6525f, 220.48074f, -1904.6525f, 88.45424f)
    cubicTo(-1904.6525f, -43.572304f, -1745.2157f, -150.68036f, -1559.2523f, -150.68037f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(-1567.7247f, 85.66791f), radius = 325.0f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.004537845961749554f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.13890700042247772f, -1.3947179923227081E-15f, 0.0f, 1.0f)
))}){
// _0_0_0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
-0.00872668344527483f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.32807400822639465f, 1.276595950126648f, 0.0f, 1.0f)
))}){
// _0_0_0_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.28278f, 38.644745f)
    lineTo(22.407791f, 18.394766f)
    cubicTo(22.095291f, 17.832266f, 21.532791f, 17.519768f, 20.907793f, 17.519768f)
    cubicTo(20.282793f, 17.519768f, 19.720295f, 17.894766f, 19.407795f, 18.457266f)
    lineTo(8.7828045f, 38.707245f)
    cubicTo(8.5328045f, 39.207245f, 8.5328045f, 39.894745f, 8.8453045f, 40.394745f)
    cubicTo(9.157804f, 40.894745f, 9.657804f, 41.14474f, 10.282804f, 41.14474f)
    lineTo(31.782782f, 41.14474f)
    cubicTo(32.40778f, 41.14474f, 32.97028f, 40.832245f, 33.22028f, 40.332245f)
    cubicTo(33.53278f, 39.832245f, 33.53278f, 39.207245f, 33.28278f, 38.644745f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(204, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(159, 0, 0, 255))
stroke = Stroke(width=0.6382978f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(33.28278f, 38.644745f)
    lineTo(22.407791f, 18.394766f)
    cubicTo(22.095291f, 17.832266f, 21.532791f, 17.519768f, 20.907793f, 17.519768f)
    cubicTo(20.282793f, 17.519768f, 19.720295f, 17.894766f, 19.407795f, 18.457266f)
    lineTo(8.7828045f, 38.707245f)
    cubicTo(8.5328045f, 39.207245f, 8.5328045f, 39.894745f, 8.8453045f, 40.394745f)
    cubicTo(9.157804f, 40.894745f, 9.657804f, 41.14474f, 10.282804f, 41.14474f)
    lineTo(31.782782f, 41.14474f)
    cubicTo(32.40778f, 41.14474f, 32.97028f, 40.832245f, 33.22028f, 40.332245f)
    cubicTo(33.53278f, 39.832245f, 33.53278f, 39.207245f, 33.28278f, 38.644745f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
0.625f, 0.0f, 0.0f, 0.0f,
-0.005534933879971504f, 0.634253978729248f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
6.164052963256836f, 15.760549545288086f, 0.0f, 1.0f)
))}){
// _0_0_0_1_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_1_1_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.5f, 37.6f)
    cubicTo(9.2f, 38.1f, 9.5f, 38.5f, 10.0f, 38.5f)
    lineTo(38.2f, 38.5f)
    cubicTo(38.7f, 38.5f, 39.0f, 38.1f, 38.7f, 37.6f)
    lineTo(24.4f, 11.0f)
    cubicTo(24.1f, 10.5f, 23.7f, 10.5f, 23.5f, 11.0f)
    lineTo(9.5f, 37.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(212, 212, 212, 255), 0.3982f to Color(226, 226, 226, 255), 1.0f to Color(255, 255, 255, 255), start = Offset(4.1914f, 11.1133f), end = Offset(47.3197f, 56.0523f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
-0.00872668344527483f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.3182770013809204f, 1.276595950126648f, 0.0f, 1.0f)
))}){
// _0_0_0_1_2
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(255, 255, 255, 87), start = Offset(9.558879f, 29.48385f), end = Offset(29.610327f, 46.318954f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.6382979f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(32.323105f, 38.183907f)
    lineTo(22.15027f, 19.265665f)
    cubicTo(21.71698f, 18.45069f, 21.561699f, 18.189213f, 20.908405f, 18.189213f)
    cubicTo(20.346525f, 18.189213f, 20.054127f, 18.57002f, 19.651304f, 19.33929f)
    lineTo(9.748929f, 38.242294f)
    cubicTo(9.173765f, 39.30359f, 9.1128235f, 39.580227f, 9.3937645f, 40.047344f)
    cubicTo(9.674704f, 40.51446f, 10.032797f, 40.48902f, 11.356441f, 40.51949f)
    lineTo(30.974592f, 40.51949f)
    cubicTo(32.206825f, 40.534725f, 32.48399f, 40.440838f, 32.70874f, 39.97372f)
    cubicTo(32.98968f, 39.506603f, 32.867798f, 39.136f, 32.323105f, 38.183907f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
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
0.5550879836082458f, 0.0f, 0.0f, 0.0f,
0.0f, 0.5550519824028015f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
7.749711036682129f, 17.801959991455078f, 0.0f, 1.0f)
))}){
// _0_0_0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.9f, 36.5f)
    cubicTo(22.6f, 36.5f, 21.6f, 35.5f, 21.6f, 34.2f)
    cubicTo(21.6f, 32.8f, 22.5f, 31.9f, 23.9f, 31.9f)
    cubicTo(25.3f, 31.9f, 26.1f, 32.8f, 26.2f, 34.2f)
    cubicTo(26.2f, 35.5f, 25.3f, 36.5f, 23.9f, 36.5f)
    lineTo(23.9f, 36.5f)
    close()
    moveTo(22.5f, 30.6f)
    lineTo(21.9f, 19.1f)
    lineTo(25.9f, 19.1f)
    lineTo(25.3f, 30.6f)
    lineTo(22.4f, 30.6f)
    lineTo(22.5f, 30.6f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(0, 0, 0, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
            return 0.2928876578807831
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 5.000002384185791
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 45.93619155883789
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 41.18846130371094
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

