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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Data
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.utils.getSpecularRectangularEffect
import java.nio.ByteBuffer
import java.nio.ByteOrder

class SpecularRectangularSurfacePainter(
    base: AuroraSurfacePainter,
    private val baseAlpha: Float = 1.0f,
    private val topQuery: (ContainerColorTokens) -> Color =
        { if (it.isDark) it.containerSurfaceHighest else it.containerSurfaceLowest },
    private val bottomQuery: (ContainerColorTokens) -> Color =
        { if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow }) :
    ShaderWrapperSurfacePainter(
        runtimeEffect = getSpecularRectangularEffect(),
        baseSurfacePainter = base
    ) {
    override val displayName = "Specular Rectangular ${baseSurfacePainter.displayName}"

    override fun getShaderData(
        density: Density,
        outline: Outline,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ): Data {
        val dataBuffer = ByteBuffer.allocate(60).order(ByteOrder.LITTLE_ENDIAN)
        // RGBA for the top highlight color
        val colorTop = topQuery.invoke(colorTokens)
        dataBuffer.putFloat(0, colorTop.red)
        dataBuffer.putFloat(4, colorTop.green)
        dataBuffer.putFloat(8, colorTop.blue)
        dataBuffer.putFloat(12, colorTop.alpha)
        // RGBA for the bottom highlight color
        val colorBottom = bottomQuery.invoke(colorTokens)
        dataBuffer.putFloat(16, colorBottom.red)
        dataBuffer.putFloat(20, colorBottom.green)
        dataBuffer.putFloat(24, colorBottom.blue)
        dataBuffer.putFloat(28, colorBottom.alpha)
        // Alpha
        dataBuffer.putFloat(32, alpha * baseAlpha)
        // Width and height
        dataBuffer.putFloat(36, outline.bounds.width)
        dataBuffer.putFloat(40, outline.bounds.height)

        // This is not ideal, but supporting Path-based outlines would mean having to pass that
        // information to the underlying shader.
        val topLeftRadius: Float
        val topRightRadius: Float
        when (outline) {
            is Outline.Rounded -> {
                topLeftRadius = outline.roundRect.topLeftCornerRadius.x
                topRightRadius = outline.roundRect.topRightCornerRadius.x
            }
            else -> {
                topLeftRadius = 0.0f
                topRightRadius = 0.0f
            }
        }
        dataBuffer.putFloat(44, topLeftRadius)
        dataBuffer.putFloat(48, topRightRadius)

        // Gap
        dataBuffer.putFloat(52, 1.0f * density.density)
        // Ramp
        dataBuffer.putFloat(56, 2.0f * density.density)

        return Data.makeFromBytes(dataBuffer.array())
    }
}
