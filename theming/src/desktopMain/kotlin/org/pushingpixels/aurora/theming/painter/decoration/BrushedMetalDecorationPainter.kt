/*
 * Copyright 2020-2025 Aurora, Kirill Grouchnikov
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
class BrushedMetalDecorationPainter : ShaderWrapperDecorationPainter(
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
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        // RGBA colorBright
        dataBuffer.putFloat(0, colorTokens.containerSurfaceBright.red)
        dataBuffer.putFloat(4, colorTokens.containerSurfaceBright.green)
        dataBuffer.putFloat(8, colorTokens.containerSurfaceBright.blue)
        dataBuffer.putFloat(12, colorTokens.containerSurfaceBright.alpha)
        // RGBA colorDim
        dataBuffer.putFloat(16, colorTokens.containerSurfaceDim.red)
        dataBuffer.putFloat(20, colorTokens.containerSurfaceDim.green)
        dataBuffer.putFloat(24, colorTokens.containerSurfaceDim.blue)
        dataBuffer.putFloat(28, colorTokens.containerSurfaceDim.alpha)
        // Alpha
        dataBuffer.putFloat(32, 0.2f)

        return Data.makeFromBytes(dataBuffer.array())
    }
}
