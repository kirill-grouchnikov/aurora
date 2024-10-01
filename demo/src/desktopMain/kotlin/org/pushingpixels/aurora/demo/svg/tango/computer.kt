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
class computer : Painter() {
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
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.3689320087432861f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.978553056716919f, -13.617130279541016f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.10058f, 35.051105f)
    cubicTo(41.127632f, 36.682228f, 37.915836f, 38.192577f, 32.681362f, 39.010254f)
    cubicTo(27.446886f, 39.827927f, 20.989925f, 39.827927f, 15.755449f, 39.010254f)
    cubicTo(10.520973f, 38.192577f, 7.3091793f, 36.682228f, 7.336233f, 35.051105f)
    cubicTo(7.3091793f, 33.419983f, 10.520973f, 31.909634f, 15.755449f, 31.091959f)
    cubicTo(20.989925f, 30.274284f, 27.446886f, 30.274284f, 32.681362f, 31.091959f)
    cubicTo(37.915836f, 31.909634f, 41.127632f, 33.419983f, 41.10058f, 35.051105f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.218403f, 35.051075f), radius = 16.882172f, tileMode = TileMode.Clamp)
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
57.533390045166016f, 3.2034270763397217f, 0.0f, 1.0f)
))}){
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-26.263968f, 29.716238f)
    cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
    cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
    cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
    cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
    cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
    cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(173, 176, 170, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(75, 77, 74, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-26.263968f, 29.716238f)
    cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
    cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
    cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
    cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
    cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
    cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
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
0.9402729868888855f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9402729868888855f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
55.40361022949219f, 4.271193981170654f, 0.0f, 1.0f)
))}){
// _0_0_2
brush = SolidColor(Color(123, 127, 122, 255))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-26.263968f, 29.716238f)
    cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
    cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
    cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
    cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
    cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
    cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
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
0.9402729868888855f, 0.0f, 0.0f, 0.0f,
0.0f, 0.9402729868888855f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
55.40361022949219f, 3.5211939811706543f, 0.0f, 1.0f)
))}){
// _0_0_3
brush = Brush.linearGradient(0.0f to Color(216, 223, 214, 255), 1.0f to Color(216, 223, 214, 0), start = Offset(-35.658363f, 33.460922f), end = Offset(-35.658363f, 30.06203f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.6806534f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(-26.263968f, 29.716238f)
    cubicTo(-26.248913f, 31.127916f, -28.036179f, 32.43507f, -30.949007f, 33.14274f)
    cubicTo(-33.861835f, 33.850407f, -37.45494f, 33.850407f, -40.367767f, 33.14274f)
    cubicTo(-43.280594f, 32.43507f, -45.06786f, 31.127916f, -45.052807f, 29.716238f)
    cubicTo(-45.06786f, 28.30456f, -43.280594f, 26.997404f, -40.367767f, 26.289736f)
    cubicTo(-37.45494f, 25.582067f, -33.861835f, 25.582067f, -30.949007f, 26.289736f)
    cubicTo(-28.036179f, 26.997404f, -26.248913f, 28.30456f, -26.263968f, 29.716238f)
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
// _0_0_4
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.6875f, 28.766243f)
    lineTo(25.625f, 29.766243f)
    cubicTo(25.625f, 29.766243f, 29.949108f, 33.36541f, 34.625f, 33.96875f)
    cubicTo(36.962948f, 34.27042f, 39.378674f, 34.67116f, 41.375f, 35.15625f)
    cubicTo(43.371326f, 35.64134f, 44.963356f, 36.275856f, 45.5f, 36.8125f)
    cubicTo(45.81041f, 37.12291f, 45.95106f, 37.38614f, 46.0f, 37.59375f)
    cubicTo(46.04894f, 37.80136f, 46.038216f, 37.948566f, 45.90625f, 38.15625f)
    cubicTo(45.64232f, 38.57162f, 44.826393f, 39.1239f, 43.4375f, 39.5625f)
    cubicTo(40.659714f, 40.439693f, 35.717075f, 41.0f, 28.875f, 41.0f)
    lineTo(28.875f, 42.0f)
    cubicTo(35.770996f, 42.0f, 40.738667f, 41.47233f, 43.71875f, 40.53125f)
    cubicTo(45.208794f, 40.06071f, 46.24369f, 39.515564f, 46.75f, 38.71875f)
    cubicTo(47.003155f, 38.320343f, 47.107323f, 37.8303f, 47.0f, 37.375f)
    cubicTo(46.892677f, 36.9197f, 46.615444f, 36.490444f, 46.21875f, 36.09375f)
    cubicTo(45.34118f, 35.21618f, 43.68191f, 34.68731f, 41.625f, 34.1875f)
    cubicTo(39.56809f, 33.68769f, 37.109264f, 33.27317f, 34.75f, 32.96875f)
    cubicTo(30.031473f, 32.35991f, 25.6875f, 28.766243f, 25.6875f, 28.766243f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(208, 208, 208, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(151, 151, 151, 255))
stroke = Stroke(width=0.4f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(25.6875f, 28.766243f)
    lineTo(25.625f, 29.766243f)
    cubicTo(25.625f, 29.766243f, 29.949108f, 33.36541f, 34.625f, 33.96875f)
    cubicTo(36.962948f, 34.27042f, 39.378674f, 34.67116f, 41.375f, 35.15625f)
    cubicTo(43.371326f, 35.64134f, 44.963356f, 36.275856f, 45.5f, 36.8125f)
    cubicTo(45.81041f, 37.12291f, 45.95106f, 37.38614f, 46.0f, 37.59375f)
    cubicTo(46.04894f, 37.80136f, 46.038216f, 37.948566f, 45.90625f, 38.15625f)
    cubicTo(45.64232f, 38.57162f, 44.826393f, 39.1239f, 43.4375f, 39.5625f)
    cubicTo(40.659714f, 40.439693f, 35.717075f, 41.0f, 28.875f, 41.0f)
    lineTo(28.875f, 42.0f)
    cubicTo(35.770996f, 42.0f, 40.738667f, 41.47233f, 43.71875f, 40.53125f)
    cubicTo(45.208794f, 40.06071f, 46.24369f, 39.515564f, 46.75f, 38.71875f)
    cubicTo(47.003155f, 38.320343f, 47.107323f, 37.8303f, 47.0f, 37.375f)
    cubicTo(46.892677f, 36.9197f, 46.615444f, 36.490444f, 46.21875f, 36.09375f)
    cubicTo(45.34118f, 35.21618f, 43.68191f, 34.68731f, 41.625f, 34.1875f)
    cubicTo(39.56809f, 33.68769f, 37.109264f, 33.27317f, 34.75f, 32.96875f)
    cubicTo(30.031473f, 32.35991f, 25.6875f, 28.766243f, 25.6875f, 28.766243f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
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
0.0f, 1.3689320087432861f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-1.978553056716919f, -19.021259307861328f, 0.0f, 1.0f)
))}){
// _0_0_5
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.10058f, 35.051105f)
    cubicTo(41.127632f, 36.682228f, 37.915836f, 38.192577f, 32.681362f, 39.010254f)
    cubicTo(27.446886f, 39.827927f, 20.989925f, 39.827927f, 15.755449f, 39.010254f)
    cubicTo(10.520973f, 38.192577f, 7.3091793f, 36.682228f, 7.336233f, 35.051105f)
    cubicTo(7.3091793f, 33.419983f, 10.520973f, 31.909634f, 15.755449f, 31.091959f)
    cubicTo(20.989925f, 30.274284f, 27.446886f, 30.274284f, 32.681362f, 31.091959f)
    cubicTo(37.915836f, 31.909634f, 41.127632f, 33.419983f, 41.10058f, 35.051105f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.218403f, 35.051075f), radius = 16.882172f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_6
shape = Outline.Rectangle(rect = Rect(left = 17.472396850585938f, top = 30.703611373901367f, right = 26.512069702148438f, bottom = 33.443650245666504f))
brush = Brush.linearGradient(0.0f to Color(88, 89, 86, 255), 1.0f to Color(187, 190, 184, 255), start = Offset(22.171595f, 29.474092f), end = Offset(22.028107f, 35.72697f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_7
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.0809026f, 1.6956221f)
    lineTo(36.669098f, 1.6956221f)
    cubicTo(37.58044f, 1.6956221f, 38.293243f, 2.279104f, 38.33585f, 3.0972092f)
    lineTo(39.667892f, 28.675323f)
    cubicTo(39.7261f, 29.793058f, 38.766838f, 30.695627f, 37.647587f, 30.695627f)
    lineTo(6.102412f, 30.695627f)
    cubicTo(4.983163f, 30.695627f, 4.023898f, 29.793058f, 4.0821066f, 28.675323f)
    lineTo(5.4141507f, 3.0972092f)
    cubicTo(5.4544344f, 2.3236744f, 5.961653f, 1.6956221f, 7.0809026f, 1.6956221f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(221, 225, 217, 255), 1.0f to Color(202, 205, 198, 255), start = Offset(8.104956f, 5.0940657f), end = Offset(37.67669f, 28.203438f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(143, 143, 143, 255), 1.0f to Color(73, 73, 73, 255), start = Offset(11.4755f, 4.8730407f), end = Offset(35.93357f, 28.292397f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(7.0809026f, 1.6956221f)
    lineTo(36.669098f, 1.6956221f)
    cubicTo(37.58044f, 1.6956221f, 38.293243f, 2.279104f, 38.33585f, 3.0972092f)
    lineTo(39.667892f, 28.675323f)
    cubicTo(39.7261f, 29.793058f, 38.766838f, 30.695627f, 37.647587f, 30.695627f)
    lineTo(6.102412f, 30.695627f)
    cubicTo(4.983163f, 30.695627f, 4.023898f, 29.793058f, 4.0821066f, 28.675323f)
    lineTo(5.4141507f, 3.0972092f)
    cubicTo(5.4544344f, 2.3236744f, 5.961653f, 1.6956221f, 7.0809026f, 1.6956221f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_8
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.410535f, 4.305827f)
    lineTo(7.1683397f, 26.351145f)
    lineTo(34.81873f, 26.351145f)
    lineTo(33.48371f, 4.3992558f)
    lineTo(8.410535f, 4.305827f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(91, 91, 151, 255), 1.0f to Color(27, 27, 67, 255), start = Offset(23.207052f, 29.510551f), end = Offset(19.878864f, 7.051256f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = SolidColor(Color(0, 0, 121, 255))
stroke = Stroke(width=0.5f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.410535f, 4.305827f)
    lineTo(7.1683397f, 26.351145f)
    lineTo(34.81873f, 26.351145f)
    lineTo(33.48371f, 4.3992558f)
    lineTo(8.410535f, 4.305827f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_9
brush = Brush.linearGradient(0.0f to Color(0, 0, 0, 63), 1.0f to Color(0, 0, 0, 0), start = Offset(22.149012f, 29.344574f), end = Offset(22.14901f, 27.79497f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.9961812f, cap=StrokeCap.Round, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.177433f, 28.735788f)
    lineTo(37.60591f, 28.735788f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_10
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 179), 1.0f to Color(255, 255, 255, 0), start = Offset(20.610981f, 12.736387f), end = Offset(39.75421f, 50.81708f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.99999964f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.9145985f, 2.7063396f)
    lineTo(36.7601f, 2.6685383f)
    cubicTo(37.043797f, 2.668179f, 37.319405f, 2.9057882f, 37.342205f, 3.321082f)
    lineTo(38.704098f, 28.12433f)
    cubicTo(38.76214f, 29.18136f, 38.16435f, 29.9102f, 37.10573f, 29.9102f)
    lineTo(6.5817585f, 29.9102f)
    cubicTo(5.5231357f, 29.9102f, 4.988744f, 29.18141f, 5.045887f, 28.12433f)
    lineTo(6.3699775f, 3.6301632f)
    cubicTo(6.4086733f, 2.9143326f, 6.5363626f, 2.7068188f, 6.9145985f, 2.7063396f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 0.5314286f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_11
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(8.711536f, 4.7463627f)
    lineTo(7.909007f, 22.616693f)
    cubicTo(18.953646f, 20.216063f, 19.33047f, 12.124494f, 33.063038f, 9.469943f)
    lineTo(32.901566f, 4.8124266f)
    lineTo(8.711536f, 4.7463627f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(255, 255, 255, 255), 1.0f to Color(252, 252, 255, 0), start = Offset(14.829169f, 0.15016854f), end = Offset(21.900234f, 22.616693f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.2643979787826538f, 0.0f, 0.0f, 0.0f,
0.0f, 1.2912620306015015f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-6.216331958770752f, -4.000422954559326f, 0.0f, 1.0f)
))}){
// _0_0_12
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(41.10058f, 35.051105f)
    cubicTo(41.127632f, 36.682228f, 37.915836f, 38.192577f, 32.681362f, 39.010254f)
    cubicTo(27.446886f, 39.827927f, 20.989925f, 39.827927f, 15.755449f, 39.010254f)
    cubicTo(10.520973f, 38.192577f, 7.3091793f, 36.682228f, 7.336233f, 35.051105f)
    cubicTo(7.3091793f, 33.419983f, 10.520973f, 31.909634f, 15.755449f, 31.091959f)
    cubicTo(20.989925f, 30.274284f, 27.446886f, 30.274284f, 32.681362f, 31.091959f)
    cubicTo(37.915836f, 31.909634f, 41.127632f, 33.419983f, 41.10058f, 35.051105f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.radialGradient(0.0f to Color(0, 0, 0, 255), 1.0f to Color(0, 0, 0, 0), center = Offset(24.218403f, 35.051075f), radius = 16.882172f, tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_13
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.462184f, 36.81745f)
    lineTo(37.46459f, 36.81745f)
    cubicTo(38.58384f, 36.81745f, 38.441944f, 37.08889f, 38.556816f, 37.430298f)
    lineTo(41.391464f, 45.855106f)
    cubicTo(41.506336f, 46.196518f, 41.418484f, 46.467953f, 40.299236f, 46.467953f)
    lineTo(3.6275382f, 46.467953f)
    cubicTo(2.508289f, 46.467953f, 2.4204388f, 46.196518f, 2.5353107f, 45.855106f)
    lineTo(5.3699565f, 37.430298f)
    cubicTo(5.4848285f, 37.08889f, 5.3429346f, 36.81745f, 6.462184f, 36.81745f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(221, 225, 217, 255), 1.0f to Color(202, 205, 198, 255), start = Offset(20.6957f, 43.052326f), end = Offset(20.537241f, 46.498077f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
brush = Brush.linearGradient(0.0f to Color(143, 143, 143, 255), 1.0f to Color(73, 73, 73, 255), start = Offset(11.4755f, 4.8730407f), end = Offset(35.93357f, 28.292397f), tileMode = TileMode.Clamp)
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.462184f, 36.81745f)
    lineTo(37.46459f, 36.81745f)
    cubicTo(38.58384f, 36.81745f, 38.441944f, 37.08889f, 38.556816f, 37.430298f)
    lineTo(41.391464f, 45.855106f)
    cubicTo(41.506336f, 46.196518f, 41.418484f, 46.467953f, 40.299236f, 46.467953f)
    lineTo(3.6275382f, 46.467953f)
    cubicTo(2.508289f, 46.467953f, 2.4204388f, 46.196518f, 2.5353107f, 45.855106f)
    lineTo(5.3699565f, 37.430298f)
    cubicTo(5.4848285f, 37.08889f, 5.3429346f, 36.81745f, 6.462184f, 36.81745f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_14
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.3916893f, 38.829113f)
    lineTo(4.6239223f, 43.95564f)
    lineTo(10.104f, 43.95564f)
    lineTo(10.63433f, 41.922707f)
    lineTo(25.483572f, 41.922707f)
    lineTo(26.03325f, 43.99782f)
    lineTo(32.201084f, 43.99782f)
    lineTo(30.521708f, 38.829113f)
    lineTo(6.3916893f, 38.829113f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(122, 125, 119, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_15
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.076272f, 42.27626f)
    lineTo(10.63433f, 43.95564f)
    lineTo(25.395184f, 43.95564f)
    lineTo(24.953241f, 42.187874f)
    lineTo(11.076272f, 42.27626f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(119, 120, 116, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_16
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.592777f, 38.829113f)
    lineTo(39.272156f, 43.86725f)
    lineTo(33.792076f, 43.778862f)
    lineTo(32.289474f, 38.917503f)
    lineTo(37.592777f, 38.829113f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(119, 122, 117, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_17
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(37.592777f, 38.298786f)
    lineTo(39.272156f, 43.33692f)
    lineTo(33.792076f, 43.24853f)
    lineTo(32.289474f, 38.387173f)
    lineTo(37.592777f, 38.298786f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(157, 157, 157, 255), 1.0f to Color(185, 185, 185, 255), start = Offset(18.7408f, 38.318054f), end = Offset(18.740799f, 43.37945f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_18
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.3916893f, 38.210396f)
    lineTo(4.6239223f, 43.33692f)
    lineTo(10.104f, 43.33692f)
    lineTo(10.63433f, 41.30399f)
    lineTo(25.483572f, 41.30399f)
    lineTo(26.03325f, 43.379105f)
    lineTo(32.201084f, 43.379105f)
    lineTo(30.521708f, 38.210396f)
    lineTo(6.3916893f, 38.210396f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(157, 157, 157, 255), 1.0f to Color(185, 185, 185, 255), start = Offset(18.7408f, 38.318054f), end = Offset(18.740799f, 43.37945f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_19
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.076272f, 41.745934f)
    lineTo(10.63433f, 43.425312f)
    lineTo(25.395184f, 43.425312f)
    lineTo(24.953241f, 41.657543f)
    lineTo(11.076272f, 41.745934f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(157, 157, 157, 255), 1.0f to Color(185, 185, 185, 255), start = Offset(18.7408f, 38.318054f), end = Offset(18.740799f, 43.37945f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_20
brush = Brush.linearGradient(0.0f to Color(249, 255, 245, 255), 1.0f to Color(249, 255, 245, 0), start = Offset(30.214968f, 46.740234f), end = Offset(19.539223f, 34.057743f), tileMode = TileMode.Clamp)
stroke = Stroke(width=0.5f, cap=StrokeCap.Butt, join=StrokeJoin.Round, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(6.127819f, 37.578117f)
    lineTo(37.953632f, 37.578117f)
    lineTo(40.590813f, 45.670677f)
    lineTo(3.329743f, 45.670677f)
    lineTo(6.127819f, 37.578117f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
withTransform({
transform(
Matrix(values=floatArrayOf(
1.3312369585037231f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6584489941596985f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.419329643249512f, 2.8538661003112793f, 0.0f, 1.0f)
))}){
// _0_0_21
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.620502f, 3.9384086f)
    cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
    cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
    cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
    cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
    cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
    cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
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
1.3312369585037231f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6584489941596985f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.305729866027832f, 4.959650993347168f, 0.0f, 1.0f)
))}){
// _0_0_22
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.620502f, 3.9384086f)
    cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
    cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
    cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
    cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
    cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
    cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
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
1.3312369585037231f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6584489941596985f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.192130088806152f, 6.959650993347168f, 0.0f, 1.0f)
))}){
// _0_0_23
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.620502f, 3.9384086f)
    cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
    cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
    cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
    cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
    cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
    cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
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
1.3312369585037231f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6584489941596985f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-10.078530311584473f, 8.959650993347168f, 0.0f, 1.0f)
))}){
// _0_0_24
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.620502f, 3.9384086f)
    cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
    cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
    cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
    cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
    cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
    cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
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
1.3312369585037231f, 0.0f, 0.0f, 0.0f,
0.0f, 0.6584489941596985f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-9.964929580688477f, 10.959650039672852f, 0.0f, 1.0f)
))}){
// _0_0_25
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(35.620502f, 3.9384086f)
    cubicTo(35.62185f, 4.2392945f, 35.4621f, 4.5179024f, 35.201748f, 4.668735f)
    cubicTo(34.941395f, 4.8195677f, 34.620235f, 4.8195677f, 34.359882f, 4.668735f)
    cubicTo(34.09953f, 4.5179024f, 33.93978f, 4.2392945f, 33.941128f, 3.9384086f)
    cubicTo(33.93978f, 3.6375227f, 34.09953f, 3.358915f, 34.359882f, 3.2080822f)
    cubicTo(34.620235f, 3.0572495f, 34.941395f, 3.0572495f, 35.201748f, 3.2080822f)
    cubicTo(35.4621f, 3.358915f, 35.62185f, 3.6375227f, 35.620502f, 3.9384086f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = Brush.linearGradient(0.0f to Color(144, 144, 144, 255), 1.0f to Color(190, 190, 190, 0), start = Offset(34.30099f, 3.9384086f), end = Offset(35.520542f, 3.8451097f), tileMode = TileMode.Clamp)
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_26
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(20.0f, 27.317665f)
    lineTo(20.281715f, 27.317665f)
    cubicTo(20.36548f, 27.317667f, 20.4297f, 27.33633f, 20.474377f, 27.373655f)
    cubicTo(20.519344f, 27.41069f, 20.541828f, 27.463594f, 20.54183f, 27.53237f)
    cubicTo(20.541828f, 27.60144f, 20.519344f, 27.654638f, 20.474377f, 27.691965f)
    cubicTo(20.4297f, 27.728998f, 20.36548f, 27.747515f, 20.281715f, 27.747515f)
    lineTo(20.169735f, 27.747515f)
    lineTo(20.169735f, 27.975885f)
    lineTo(20.0f, 27.975885f)
    lineTo(20.0f, 27.317665f)
    moveTo(20.169735f, 27.440668f)
    lineTo(20.169735f, 27.624512f)
    lineTo(20.26364f, 27.624512f)
    cubicTo(20.296558f, 27.624512f, 20.321981f, 27.616575f, 20.33991f, 27.600704f)
    cubicTo(20.35784f, 27.58454f, 20.366804f, 27.561762f, 20.366804f, 27.53237f)
    cubicTo(20.366804f, 27.50298f, 20.35784f, 27.480349f, 20.33991f, 27.464476f)
    cubicTo(20.321981f, 27.448605f, 20.296558f, 27.440668f, 20.26364f, 27.440668f)
    lineTo(20.169735f, 27.440668f)
    moveTo(20.961979f, 27.428764f)
    cubicTo(20.91025f, 27.428766f, 20.87013f, 27.44787f, 20.841621f, 27.486078f)
    cubicTo(20.813112f, 27.524288f, 20.798857f, 27.578074f, 20.798857f, 27.647436f)
    cubicTo(20.798857f, 27.716507f, 20.813112f, 27.770145f, 20.841621f, 27.808355f)
    cubicTo(20.87013f, 27.846563f, 20.91025f, 27.865667f, 20.961979f, 27.865667f)
    cubicTo(21.014002f, 27.865667f, 21.054268f, 27.846563f, 21.082779f, 27.808355f)
    cubicTo(21.111286f, 27.770145f, 21.125542f, 27.716507f, 21.125542f, 27.647436f)
    cubicTo(21.125542f, 27.578074f, 21.111286f, 27.524288f, 21.082779f, 27.486078f)
    cubicTo(21.054268f, 27.44787f, 21.014002f, 27.428766f, 20.961979f, 27.428764f)
    moveTo(20.961979f, 27.305761f)
    cubicTo(21.067787f, 27.305763f, 21.150671f, 27.336037f, 21.21063f, 27.396582f)
    cubicTo(21.270588f, 27.457129f, 21.300568f, 27.540747f, 21.300568f, 27.647436f)
    cubicTo(21.300568f, 27.753834f, 21.270588f, 27.837305f, 21.21063f, 27.897852f)
    cubicTo(21.150671f, 27.958399f, 21.067787f, 27.98867f, 20.961979f, 27.98867f)
    cubicTo(20.856464f, 27.98867f, 20.77358f, 27.958399f, 20.713327f, 27.897852f)
    cubicTo(20.65337f, 27.837305f, 20.62339f, 27.753834f, 20.62339f, 27.647436f)
    cubicTo(20.62339f, 27.540747f, 20.65337f, 27.457129f, 20.713327f, 27.396582f)
    cubicTo(20.77358f, 27.336037f, 20.856464f, 27.305763f, 20.961979f, 27.305761f)
    moveTo(21.42842f, 27.317665f)
    lineTo(21.617994f, 27.317665f)
    lineTo(21.857388f, 27.769117f)
    lineTo(21.857388f, 27.317665f)
    lineTo(22.018305f, 27.317665f)
    lineTo(22.018305f, 27.975885f)
    lineTo(21.82873f, 27.975885f)
    lineTo(21.589338f, 27.524433f)
    lineTo(21.589338f, 27.975885f)
    lineTo(21.42842f, 27.975885f)
    lineTo(21.42842f, 27.317665f)
    moveTo(22.09149f, 27.317665f)
    lineTo(22.277096f, 27.317665f)
    lineTo(22.42699f, 27.55221f)
    lineTo(22.576887f, 27.317665f)
    lineTo(22.762936f, 27.317665f)
    lineTo(22.51208f, 27.698578f)
    lineTo(22.51208f, 27.975885f)
    lineTo(22.342344f, 27.975885f)
    lineTo(22.342344f, 27.698578f)
    lineTo(22.09149f, 27.317665f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(74, 74, 74, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
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
            return 1.997342824935913
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.1956220865249634
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 45.24462127685547
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 45.96828842163086
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

