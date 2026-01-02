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
package org.pushingpixels.aurora.theming.painter.surface

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.Shader
import org.pushingpixels.aurora.theming.ContainerColorTokens

/**
 * Implementation of [AuroraSurfacePainter] that uses a Skia shader to paint on
 * filled areas.
 *
 * @author Kirill Grouchnikov
 */
abstract class ShaderWrapperSurfacePainter(
    val runtimeEffect: RuntimeEffect,
    baseShader: Shader? = null,
    val baseSurfacePainter: AuroraSurfacePainter
) : AuroraSurfacePainter {
    private val shaderChildren: Array<Shader?>? =
        if (baseShader != null) arrayOf(baseShader) else null

    abstract fun getShaderData(
        density: Density,
        outline: Outline,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ): Data

    override fun paintSurface(
        drawScope: DrawScope,
        size: Size,
        outline: Outline,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with(drawScope) {
            baseSurfacePainter.paintSurface(
                drawScope = this,
                size = size,
                outline = outline,
                colorTokens = colorTokens,
                alpha = alpha
            )

            val shader = runtimeEffect.makeShader(
                uniforms = getShaderData(drawScope, outline, colorTokens, alpha),
                children = shaderChildren,
                localMatrix = null
            )

            val clipPath = Path()
            clipPath.addOutline(outline)
            clipPath(path = clipPath) {
                drawRect(
                    brush = ShaderBrush(shader),
                    topLeft = Offset(0.0f, 0.0f),
                    size = size
                )
            }
        }
    }
}