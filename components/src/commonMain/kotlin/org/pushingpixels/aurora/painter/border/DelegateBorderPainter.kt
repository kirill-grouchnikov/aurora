/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.painter.border

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme

/**
 * Delegate border painter that allows tweaking the visual appearance of
 * borders.
 *
 * Mask is an 8-digit hexadecimal number applied on a color painted by
 * the delegate. Can be used to apply custom translucency. For example,
 * value 0x80FFFFFF will result in 50% translucency of the original
 * border color.
 *
 * @author Kirill Grouchnikov
 */
class DelegateBorderPainter(
    override val displayName: String,
    val delegate: StandardBorderPainter,
    val topMask: Long = 0xFFFFFFFF,
    val midMask: Long = 0xFFFFFFFF,
    val bottomMask: Long = 0xFFFFFFFF,
    val transform: (AuroraColorScheme) -> AuroraColorScheme = { it }
) : StandardBorderPainter() {
    override fun getTopBorderColor(borderScheme: AuroraColorScheme): Color {
        val original = delegate.getTopBorderColor(borderScheme)
        val originalArgb = (
                ((original.alpha * 255.0f + 0.5f).toInt() shl 24) or
                        ((original.red * 255.0f + 0.5f).toInt() shl 16) or
                        ((original.green * 255.0f + 0.5f).toInt() shl 8) or
                        (original.blue * 255.0f + 0.5f).toInt()
                )
        return Color(value = (originalArgb.toULong() and topMask.toULong()) shl 32)
    }

    override fun getMidBorderColor(borderScheme: AuroraColorScheme): Color {
        val original = delegate.getMidBorderColor(borderScheme)
        val originalArgb = (
                ((original.alpha * 255.0f + 0.5f).toInt() shl 24) or
                        ((original.red * 255.0f + 0.5f).toInt() shl 16) or
                        ((original.green * 255.0f + 0.5f).toInt() shl 8) or
                        (original.blue * 255.0f + 0.5f).toInt()
                )
        return Color(value = (originalArgb.toULong() and midMask.toULong()) shl 32)
    }

    override fun getBottomBorderColor(borderScheme: AuroraColorScheme): Color {
        val original = delegate.getBottomBorderColor(borderScheme)
        val originalArgb = (
                ((original.alpha * 255.0f + 0.5f).toInt() shl 24) or
                        ((original.red * 255.0f + 0.5f).toInt() shl 16) or
                        ((original.green * 255.0f + 0.5f).toInt() shl 8) or
                        (original.blue * 255.0f + 0.5f).toInt()
                )
        return Color(value = (originalArgb.toULong() and bottomMask.toULong()) shl 32)
    }

    override fun paintBorder(
        drawScope: DrawScope,
        size: Size,
        outline: Outline,
        outlineInner: Outline?,
        borderScheme: AuroraColorScheme
    ) {
        // TODO: cache the transformed scheme
        super.paintBorder(
            drawScope, size, outline, outlineInner,
            transform.invoke(borderScheme)
        )
    }
}