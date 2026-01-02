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
import org.pushingpixels.aurora.theming.utils.getLuminousEffect
import java.nio.ByteBuffer
import java.nio.ByteOrder

class LuminousSurfacePainter(
    base: AuroraSurfacePainter = MatteSurfacePainter(),
    private val baseAlpha: Float = 1.0f,
    private val query: (ContainerColorTokens) -> Color =
        { if (it.isDark) it.containerSurfaceHighest else it.containerSurfaceLowest }) :
    ShaderWrapperSurfacePainter(
        runtimeEffect = getLuminousEffect(),
        baseSurfacePainter = base
    ) {
    override val displayName = "Luminous ${baseSurfacePainter.displayName}"

    override fun getShaderData(
        density: Density,
        outline: Outline,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ): Data {
        val dataBuffer = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN)
        // RGBA for the highlight color
        val colorTop = query.invoke(colorTokens)
        dataBuffer.putFloat(0, colorTop.red)
        dataBuffer.putFloat(4, colorTop.green)
        dataBuffer.putFloat(8, colorTop.blue)
        dataBuffer.putFloat(12, colorTop.alpha)
        // Alpha
        dataBuffer.putFloat(16, alpha * baseAlpha)
        // Width and height
        dataBuffer.putFloat(20, outline.bounds.width)
        dataBuffer.putFloat(24, outline.bounds.height)

        // This is not ideal, but supporting Path-based outlines would mean having to pass that
        // information to the underlying shader.
        val topLeftRadius: Float
        val topRightRadius: Float
        val bottomLeftRadius: Float
        val bottomRightRadius: Float
        when (outline) {
            is Outline.Rounded -> {
                val roundRect = outline.roundRect
                topLeftRadius = roundRect.topLeftCornerRadius.x
                topRightRadius = roundRect.topRightCornerRadius.x
                bottomLeftRadius = roundRect.bottomLeftCornerRadius.x
                bottomRightRadius = roundRect.bottomRightCornerRadius.x
            }
            else -> {
                topLeftRadius = 0.0f
                topRightRadius = 0.0f
                bottomLeftRadius = 0.0f
                bottomRightRadius = 0.0f
            }
        }
        dataBuffer.putFloat(28, topLeftRadius)
        dataBuffer.putFloat(32, topRightRadius)
        dataBuffer.putFloat(36, bottomLeftRadius)
        dataBuffer.putFloat(40, bottomRightRadius)

        return Data.makeFromBytes(dataBuffer.array())
    }
}
