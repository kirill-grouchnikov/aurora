/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.painter.border

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme

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
        borderScheme: AuroraColorScheme,
        alpha: Float
    ) {
        // TODO: cache the transformed scheme
        super.paintBorder(
            drawScope, size, outline, outlineInner,
            transform.invoke(borderScheme), alpha
        )
    }

    override fun getRepresentativeColor(borderScheme: AuroraColorScheme): Color {
        // TODO: cache the transformed scheme
        return super.getRepresentativeColor(transform.invoke(borderScheme))
    }
}
