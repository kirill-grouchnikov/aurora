package org.pushingpixels.aurora.filters.lut

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.graphicsLayer
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.*
import org.pushingpixels.aurora.filters.Filter
import org.pushingpixels.aurora.filters.composite.FloatPair

object LutFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader lut;
            vec4 main(float2 xy) {
                vec4 color = content.eval(xy);
            
                int r = int(color.r * 255.0 / 4);
                int g = int(color.g * 255.0 / 4);
                int b = int(color.b * 255.0 / 4);
                
                float lutX = float(int(mod(float(b), 8.0)) * 64 + r);
                float lutY = float(int((b / 8) * 64 + g));
                
                vec4 lutColor = lut.eval(float2(lutX, lutY));
            
                return lutColor;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

    private fun ImageBitmap.toSkiaShader(
        tileModeX: FilterTileMode = FilterTileMode.CLAMP,
        tileModeY: FilterTileMode = FilterTileMode.CLAMP,
        samplingMode: SamplingMode = SamplingMode.DEFAULT // or SamplingMode.LINEAR
    ): Shader {
        val skiaImage = Image.makeFromBitmap(this.asSkiaBitmap())
        return skiaImage.makeShader(
            tmx = tileModeX,
            tmy = tileModeY,
            sampling = samplingMode
        )
    }

    /**
     * Constructs an [ImageFilter] for the LUT (Look-Up Table) effect with the given parameters.
     *
     * @param size the content size (width, height) — accepted for API compatibility but not used
     *             by this filter
     * @param haldLutImageBitmap the Hald CLUT image bitmap to apply
     */
    fun imageFilter(
        size: FloatPair,
        haldLutImageBitmap: ImageBitmap
    ): ImageFilter {
        val lutShaderBuilder = RuntimeShaderBuilder(this.effect)
        lutShaderBuilder.child("lut", haldLutImageBitmap.toSkiaShader())

        return ImageFilter.makeRuntimeShader(
            runtimeShaderBuilder = lutShaderBuilder,
            shaderNames = arrayOf("content"),
            inputs = arrayOf(null)
        )
    }

    @Composable
    fun effectModifier(
        haldLutImageBitmap: ImageBitmap
    ): Modifier {
        val lutShaderBuilder = RuntimeShaderBuilder(this.effect)
        lutShaderBuilder.child("lut", haldLutImageBitmap.toSkiaShader())

        return Modifier.graphicsLayer {
            renderEffect = ImageFilter.makeRuntimeShader(
                runtimeShaderBuilder = lutShaderBuilder,
                shaderNames = arrayOf("content"),
                inputs = arrayOf(null)
            ).asComposeRenderEffect()
            clip = true
        }
    }
}
