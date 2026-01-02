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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Data
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.utils.getBrushedMetalShader
import org.pushingpixels.aurora.theming.utils.getDuotoneEffect
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Implementation of [AuroraDecorationPainter] that uses brushed metal
 * painting on decoration areas.
 *
 * @author Kirill Grouchnikov
 */
class BrushedMetalDecorationPainter(
    val colorQuery1: (ContainerColorTokens) -> Color,
    val colorQuery2: (ContainerColorTokens) -> Color,
    val textureAlpha: Float = 0.2f,
) : ShaderWrapperDecorationPainter(
    runtimeEffect = getDuotoneEffect(),
    baseShader = getBrushedMetalShader(),
    baseDecorationPainter = ArcDecorationPainter()
) {
    override val displayName = "Brushed Metal"

    override fun getShaderData(
        density: Density,
        componentSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    ): Data {
        val color1 = colorQuery1.invoke(colorTokens)
        val color2 = colorQuery2.invoke(colorTokens)

        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        // RGBA colorBright
        dataBuffer.putFloat(0, color1.red)
        dataBuffer.putFloat(4, color1.green)
        dataBuffer.putFloat(8, color1.blue)
        dataBuffer.putFloat(12, color1.alpha)
        // RGBA colorDim
        dataBuffer.putFloat(16, color2.red)
        dataBuffer.putFloat(20, color2.green)
        dataBuffer.putFloat(24, color2.blue)
        dataBuffer.putFloat(28, color2.alpha)
        // Alpha
        dataBuffer.putFloat(32, textureAlpha)

        return Data.makeFromBytes(dataBuffer.array())
    }
}
