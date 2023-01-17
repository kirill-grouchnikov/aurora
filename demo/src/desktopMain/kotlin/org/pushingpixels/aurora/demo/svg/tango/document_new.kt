package org.pushingpixels.aurora.demo.svg.tango

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
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
class document_new : Painter() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var blendMode = DrawScope.DefaultBlendMode
    private var blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
    private var alphaStack = mutableListOf(1.0f)
    private var blendModeStack = mutableListOf(DrawScope.DefaultBlendMode)
    private var blendModeSkiaStack = mutableListOf(org.jetbrains.skia.BlendMode.SRC_OVER)

	@Suppress("UNUSED_VARIABLE", "UNUSED_VALUE", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNNECESSARY_NOT_NULL_ASSERTION")
private fun _paint0(drawScope : DrawScope) {
var shapeText: Outline?
var generalPathText: Path? = null
var alphaText = 0.0f
var blendModeText = DrawScope.DefaultBlendMode
var blendModeTextSkia = org.jetbrains.skia.BlendMode.SRC_OVER
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_0
alphaStack.add(0, alpha)
alpha *= 0.56725144f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
withTransform({
transform(
Matrix(values=floatArrayOf(
1.167598009109497f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-4.692486763000488f, 0.6187170147895813f, 0.0f, 1.0f)
))}){
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(40.128307f, 42.07798f)
    cubicTo(40.15366f, 43.693268f, 37.143654f, 45.188953f, 32.23805f, 45.998688f)
    cubicTo(27.332438f, 46.808426f, 21.28115f, 46.808426f, 16.375542f, 45.998688f)
    cubicTo(11.469933f, 45.188953f, 8.459927f, 43.693268f, 8.485281f, 42.07798f)
    cubicTo(8.459927f, 40.462692f, 11.469933f, 38.967007f, 16.375542f, 38.157272f)
    cubicTo(21.28115f, 37.347534f, 27.332438f, 37.347534f, 32.23805f, 38.157272f)
    cubicTo(37.143654f, 38.967007f, 40.15366f, 40.462692f, 40.128307f, 42.07798f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 24.306795f, y = 42.07797f, r = 15.821516f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 0, g = 0, b = 0), org.jetbrains.skia.Color.makeARGB(a = 0, r = 0, g = 0, b = 0), ), positions = floatArrayOf(0.0f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.mode = org.jetbrains.skia.PaintMode.FILL
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_0
shape = Outline.Rounded(roundRect = RoundRect(left = 6.60355281829834f, top = 3.6464462280273438f, right = 41.47855281829834f, bottom = 44.56694030761719f,radiusX = 2.2980971336364746f, radiusY = 2.2980971336364746f))
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 32.62476f, y = 37.206844f, r = 83.28285f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 250, g = 250, b = 250), org.jetbrains.skia.Color.makeARGB(a = 255, r = 187, g = 187, b = 187), ), positions = floatArrayOf(0.0f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.mode = org.jetbrains.skia.PaintMode.FILL
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 11.898f, y = 4.525653f, r = 36.553967f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 163, g = 163, b = 163), org.jetbrains.skia.Color.makeARGB(a = 255, r = 76, g = 76, b = 76), ), positions = floatArrayOf(0.0f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.strokeWidth = 1.0f
      skiaPaint.strokeCap = org.jetbrains.skia.PaintStrokeCap.ROUND
      skiaPaint.strokeJoin = org.jetbrains.skia.PaintStrokeJoin.ROUND
      skiaPaint.strokeMiter = 4.0f
      skiaPaint.mode = org.jetbrains.skia.PaintMode.STROKE
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_1
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 11.238739f, y = 8.152492f, r = 36.948036f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 255, g = 255, b = 255), org.jetbrains.skia.Color.makeARGB(a = 255, r = 248, g = 248, b = 248), ), positions = floatArrayOf(0.0f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.strokeWidth = 1.0f
      skiaPaint.strokeCap = org.jetbrains.skia.PaintStrokeCap.ROUND
      skiaPaint.strokeJoin = org.jetbrains.skia.PaintStrokeJoin.ROUND
      skiaPaint.strokeMiter = 4.0f
      skiaPaint.mode = org.jetbrains.skia.PaintMode.STROKE
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
withTransform({
transform(
Matrix(values=floatArrayOf(
1.0f, 0.0f, 0.0f, 0.0f,
0.0f, 1.0f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
0.6464470028877258f, -0.03798932954668999f, 0.0f, 1.0f)
))}){
// _0_1_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
withTransform({
transform(
Matrix(values=floatArrayOf(
0.22970299422740936f, 0.0f, 0.0f, 0.0f,
0.0f, 0.22970299422740936f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
4.967081069946289f, 4.244972229003906f, 0.0f, 1.0f)
))}){
// _0_1_2_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_2_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.428f, 113.07f)
    cubicTo(23.428f, 115.043f, 21.828f, 116.642f, 19.855f, 116.642f)
    cubicTo(17.881f, 116.642f, 16.282f, 115.042f, 16.282f, 113.07f)
    cubicTo(16.282f, 111.096f, 17.882f, 109.497f, 19.855f, 109.497f)
    cubicTo(21.828f, 109.497f, 23.428f, 111.097f, 23.428f, 113.07f)
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
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_2_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(23.428f, 63.07f)
    cubicTo(23.428f, 65.043f, 21.828f, 66.643f, 19.855f, 66.643f)
    cubicTo(17.881f, 66.643f, 16.282f, 65.043f, 16.282f, 63.07f)
    cubicTo(16.282f, 61.096f, 17.882f, 59.497f, 19.855f, 59.497f)
    cubicTo(21.828f, 59.497f, 23.428f, 61.097f, 23.428f, 63.07f)
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
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_2_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.995011f, 29.952326f)
    cubicTo(9.995011f, 30.40553f, 9.627486f, 30.772825f, 9.174282f, 30.772825f)
    cubicTo(8.720848f, 30.772825f, 8.353554f, 30.4053f, 8.353554f, 29.952326f)
    cubicTo(8.353554f, 29.498892f, 8.721078f, 29.131598f, 9.174282f, 29.131598f)
    cubicTo(9.627486f, 29.131598f, 9.995011f, 29.499123f, 9.995011f, 29.952326f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 9.412507f, y = 30.296513f, r = 1.2073183f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 240, g = 240, b = 240), org.jetbrains.skia.Color.makeARGB(a = 255, r = 154, g = 154, b = 154), ), positions = floatArrayOf(0.0f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.mode = org.jetbrains.skia.PaintMode.FILL
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_2_2
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(9.995011f, 18.467176f)
    cubicTo(9.995011f, 18.92038f, 9.627486f, 19.287905f, 9.174282f, 19.287905f)
    cubicTo(8.720848f, 19.287905f, 8.353554f, 18.92038f, 8.353554f, 18.467176f)
    cubicTo(8.353554f, 18.013742f, 8.721078f, 17.646446f, 9.174282f, 17.646446f)
    cubicTo(9.627486f, 17.646446f, 9.995011f, 18.013971f, 9.995011f, 18.467176f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 9.412507f, y = 18.811249f, r = 1.2075491f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 240, g = 240, b = 240), org.jetbrains.skia.Color.makeARGB(a = 255, r = 154, g = 154, b = 154), ), positions = floatArrayOf(0.0f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.mode = org.jetbrains.skia.PaintMode.FILL
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_3
brush = SolidColor(Color(0, 0, 0, 4))
stroke = Stroke(width=0.9885531f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(11.505723f, 5.4942765f)
    lineTo(11.505723f, 43.400867f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_1_4
brush = SolidColor(Color(255, 255, 255, 52))
stroke = Stroke(width=1.0f, cap=StrokeCap.Butt, join=StrokeJoin.Miter, miter=4.0f)
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(12.5f, 5.0205154f)
    lineTo(12.5f, 43.038227f)
}
shape = Outline.Generic(generalPath!!)
drawOutline(outline = shape!!, style = stroke!!, brush=brush!!, alpha = alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
// _0_2
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendModeSkiaStack.add(0, org.jetbrains.skia.BlendMode.SRC_OVER)
blendMode = BlendMode.SrcOver
blendModeSkia = org.jetbrains.skia.BlendMode.SRC_OVER
withTransform({
transform(
Matrix(values=floatArrayOf(
0.7832919955253601f, 0.0f, 0.0f, 0.0f,
0.0f, 0.7832919955253601f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-6.340882778167725f, -86.65167999267578f, 0.0f, 1.0f)
))}){
// _0_2_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(69.375f, 125.0f)
    cubicTo(69.39803f, 130.151f, 66.66322f, 134.92062f, 62.206123f, 137.50279f)
    cubicTo(57.74902f, 140.08498f, 52.25098f, 140.08498f, 47.793877f, 137.50279f)
    cubicTo(43.336773f, 134.92062f, 40.601963f, 130.151f, 40.625f, 125.0f)
    cubicTo(40.601963f, 119.84899f, 43.336773f, 115.07938f, 47.793877f, 112.4972f)
    cubicTo(52.25098f, 109.91502f, 57.74902f, 109.91502f, 62.206123f, 112.4972f)
    cubicTo(66.66322f, 115.07938f, 69.39803f, 119.84899f, 69.375f, 125.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
drawIntoCanvas {
   val nativeCanvas = it.nativeCanvas
val shader = org.jetbrains.skia.Shader.makeRadialGradient(x = 55.0f, y = 125.0f, r = 14.375f, colors = intArrayOf(org.jetbrains.skia.Color.makeARGB(a = 255, r = 255, g = 255, b = 255), org.jetbrains.skia.Color.makeARGB(a = 227, r = 255, g = 245, b = 32), org.jetbrains.skia.Color.makeARGB(a = 0, r = 255, g = 243, b = 0), ), positions = floatArrayOf(0.0f, 0.5f, 1.0f, ), style = org.jetbrains.skia.GradientStyle(tileMode = org.jetbrains.skia.FilterTileMode.CLAMP, isPremul = true, localMatrix = null))
   val nativePaint = org.jetbrains.skia.Paint().also { skiaPaint ->
      skiaPaint.shader = shader
      skiaPaint.alpha = (alpha * 255).toInt()
      skiaPaint.blendMode = blendModeSkia
      skiaPaint.mode = org.jetbrains.skia.PaintMode.FILL
   }
   when (shape) {
       is Outline.Rectangle -> nativeCanvas.drawRect((shape as Outline.Rectangle).rect.toSkiaRect(), nativePaint)
       is Outline.Rounded -> nativeCanvas.drawRRect((shape as Outline.Rounded).roundRect.toSkiaRRect(), nativePaint)
       is Outline.Generic -> nativeCanvas.drawPath((shape as Outline.Generic).path.asSkiaPath(), nativePaint)
       else -> {}
   }
}
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
            return 5.185306549072266
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
            return 42.814693450927734
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 47.42714309692383
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

