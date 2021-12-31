/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package org.pushingpixels.aurora.theming.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Data
import org.jetbrains.skia.Shader
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.utils.getDuotoneEffect
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Implementation of [AuroraDecorationPainter] that uses marble noise
 * painting on decoration areas.
 *
 * @author Kirill Grouchnikov
 */
class MarbleNoiseDecorationPainter(
    val textureAlpha: Float,
    baseDecorationPainter: AuroraDecorationPainter? = null
) : ShaderWrapperDecorationPainter(
    runtimeEffect = getDuotoneEffect(),
    baseShader = Shader.makeFractalNoise(
        baseFrequencyX = 0.25f,
        baseFrequencyY = 0.25f,
        numOctaves = 1,
        seed = 0.0f
    ),
    baseDecorationPainter = baseDecorationPainter
) {

    override val displayName = "Marble Noise"

    override fun getShaderData(
        density: Density,
        componentSize: Size,
        offsetFromRoot: Offset,
        fillScheme: AuroraColorScheme
    ): Data {
        val dataBuffer = ByteBuffer.allocate(36).order(ByteOrder.LITTLE_ENDIAN)
        // RGBA colorLight
        dataBuffer.putFloat(0, fillScheme.lightColor.red)
        dataBuffer.putFloat(4, fillScheme.lightColor.green)
        dataBuffer.putFloat(8, fillScheme.lightColor.blue)
        dataBuffer.putFloat(12, fillScheme.lightColor.alpha)
        // RGBA colorDark
        dataBuffer.putFloat(16, fillScheme.darkColor.red)
        dataBuffer.putFloat(20, fillScheme.darkColor.green)
        dataBuffer.putFloat(24, fillScheme.darkColor.blue)
        dataBuffer.putFloat(28, fillScheme.darkColor.alpha)
        // Alpha
        dataBuffer.putFloat(32, textureAlpha)

        return Data.makeFromBytes(dataBuffer.array())
    }
}