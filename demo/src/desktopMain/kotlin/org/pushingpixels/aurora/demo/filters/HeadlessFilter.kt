/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.demo.filters

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.Paint
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.jetbrains.skia.Surface
import org.pushingpixels.aurora.filters.composite.ColorHalftoneFilter
import java.io.File
import kotlin.math.PI

fun main(args: Array<String>) {
    require(args.size == 2) { "Expected input and output file paths." }

    val inputFile = File(args[0])
    val inputImage: ImageBitmap = Image.makeFromEncoded(inputFile.readBytes()).toComposeImageBitmap()

    applyShaderAndSaveToFile(
        sourceBitmap = inputImage,
        runtimeEffect = ColorHalftoneFilter.effect,
        outputPath = args[1],
        configureUniforms = {
            uniform("dotRadius", 2.0f)
            uniform("cyanAngle", 108.0f * PI.toFloat() / 180.0f)
            uniform("magentaAngle", 162.0f * PI.toFloat() / 180.0f)
            uniform("yellowAngle", 190.0f * PI.toFloat() / 180.0f)
        }
    )
}

/**
 * Applies an SkSL shader to an ImageBitmap headlessly and writes it to disk.
 * Can be called from any thread without a Compose UI hierarchy.
 */
fun applyShaderAndSaveToFile(
    sourceBitmap: ImageBitmap,
    runtimeEffect: RuntimeEffect,
    outputPath: String,
    configureUniforms: (RuntimeShaderBuilder.() -> Unit)? = null
): ImageBitmap {
    val width = sourceBitmap.width
    val height = sourceBitmap.height

    // 1. Convert Compose ImageBitmap to a Skia Image
    val sourceSkiaImage = Image.makeFromBitmap(sourceBitmap.asSkiaBitmap())

    // 2. Compile the SkSL shader and configure uniforms
    val shaderBuilder = RuntimeShaderBuilder(runtimeEffect).apply {
        configureUniforms?.invoke(this)
    }

    // 3. Wrap the shader in a Skia ImageFilter (replaces graphicsLayer { renderEffect = ... })
    // "content" is the standard SkSL child shader name for the input image
    val imageFilter = ImageFilter.makeRuntimeShader(
        runtimeShaderBuilder = shaderBuilder,
        shaderName = "content",
        input = null
    )

    // 4. Create an offscreen raster Surface matching the exact image dimensions
    val surface = Surface.makeRasterN32Premul(width, height)

    try {
        val paint = Paint().apply {
            this.imageFilter = imageFilter
        }

        // 5. Draw the source image through the shader filter onto the offscreen canvas
        surface.canvas.drawImage(sourceSkiaImage, 0f, 0f, paint)

        // 6. Snapshot the rendered result, encode to PNG, and write to disk
        val resultImage = surface.makeImageSnapshot()
        val pngData = resultImage.encodeToData(EncodedImageFormat.PNG)
            ?: error("Failed to encode filtered image to PNG")

        File(outputPath).writeBytes(pngData.bytes)

        // Optionally return the modified ImageBitmap if needed elsewhere in your app
        return resultImage.toComposeImageBitmap()
    } finally {
        // Clean up native Skia resources
        surface.close()
        imageFilter.close()
    }
}