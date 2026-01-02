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
package org.pushingpixels.aurora.theming.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.Shader
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.DecorationAreaType

/**
 * Implementation of [AuroraDecorationPainter] that uses a Skia shader to paint on
 * decoration areas.
 *
 * @author Kirill Grouchnikov
 */
abstract class ShaderWrapperDecorationPainter(
    val runtimeEffect: RuntimeEffect,
    val baseShader: Shader,
    val baseDecorationPainter: AuroraDecorationPainter? = null
) : AuroraDecorationPainter {
    abstract fun getShaderData(
        density: Density,
        componentSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    ): Data

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    ) {
        with(drawScope) {
            if (baseDecorationPainter != null) {
                baseDecorationPainter.paintDecorationArea(
                    drawScope = this,
                    decorationAreaType = decorationAreaType,
                    componentSize = componentSize,
                    outline = outline,
                    rootSize = rootSize,
                    offsetFromRoot = offsetFromRoot,
                    colorTokens = colorTokens
                )
            } else {
                drawOutline(
                    outline = outline,
                    style = Fill,
                    color = colorTokens.containerSurface
                )
            }

            val shader = runtimeEffect.makeShader(
                uniforms = getShaderData(drawScope, componentSize, offsetFromRoot, colorTokens),
                children = arrayOf(baseShader),
                localMatrix = null
            )

            val clipPath = Path()
            clipPath.addOutline(outline)
            clipPath(path = clipPath) {
                drawRect(
                    brush = ShaderBrush(shader),
                    topLeft = Offset(-offsetFromRoot.x, -offsetFromRoot.y),
                    size = Size(
                        componentSize.width + offsetFromRoot.x,
                        componentSize.height + offsetFromRoot.y
                    )
                )
            }
        }
    }
}