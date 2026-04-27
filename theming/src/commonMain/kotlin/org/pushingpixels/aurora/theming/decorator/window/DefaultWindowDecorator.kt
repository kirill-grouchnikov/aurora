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
package org.pushingpixels.aurora.theming.decorator.window

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.ContainerColorTokens

open class DefaultWindowDecorator: AuroraWindowDecorator {
    override fun getWindowBorderInsets(): Dp {
        return DecoratedBorderThickness
    }

    override fun paintWindowBorder(drawScope: DrawScope, size: Size, colorTokens: ContainerColorTokens) {
        with (drawScope) {
            val width: Float = size.width
            val height: Float = size.height
            val thickness = getWindowBorderInsets().toPx()

            if ((width > thickness) && (height > thickness)) {
                drawRect(
                    color = colorTokens.containerSurface,
                    topLeft = Offset(thickness / 2.0f, thickness / 2.0f),
                    size = Size(width - thickness, height - thickness),
                    style = Stroke(width = thickness)
                )

                val quarterThickness = thickness / 4.0f
                // top and left as 40% mix of outline variant and outline
                val colorOutlineMixed = colorTokens.containerOutlineVariant.interpolateTowards(
                    colorTokens.containerOutline, 0.4f
                )
                drawLine(
                    color = colorOutlineMixed,
                    start = Offset(x = 0f, y = quarterThickness / 2.0f),
                    end = Offset(x = width - quarterThickness, y = quarterThickness / 2.0f),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
                drawLine(
                    color = colorOutlineMixed,
                    start = Offset(x = quarterThickness / 2.0f, y = 0f),
                    end = Offset(x = quarterThickness / 2.0f, y = height - quarterThickness),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
                // bottom and right as outline
                drawLine(
                    color = colorTokens.containerOutline,
                    start = Offset(x = 0f, y = height - quarterThickness / 2.0f),
                    end = Offset(x = width, y = height - quarterThickness / 2.0f),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
                drawLine(
                    color = colorTokens.containerOutline,
                    start = Offset(x = width - quarterThickness / 2.0f, y = 0f),
                    end = Offset(x = width - quarterThickness / 2.0f, y = height),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
            }
        }
    }

    companion object {
        val DecoratedBorderThickness = 4.dp
    }
}