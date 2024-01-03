/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.painter.border.AuroraBorderPainter
import org.pushingpixels.aurora.theming.utils.getBaseOutline

@Composable
fun Modifier.auroraBorder(): Modifier = this.then(
    AuroraBorder(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        borderPainter = AuroraSkin.painters.borderPainter
    )
)

@Composable
fun Modifier.auroraBorder(sides: Sides): Modifier = this.then(
    AuroraBorderWithSides(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        borderPainter = AuroraSkin.painters.borderPainter,
        layoutDirection = LocalLayoutDirection.current,
        sides = sides
    )
)

private class AuroraBorder(
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val borderPainter: AuroraBorderPainter,
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        val borderScheme = colors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = ColorSchemeAssociationKind.Border,
            componentState = ComponentState.Enabled
        )

        val outerRadius = CornerRadius(2.0f.dp.toPx(), 2.0f.dp.toPx())
        val innerRadius = CornerRadius(1.0f.dp.toPx(), 1.0f.dp.toPx())
        val borderOutline = Outline.Rounded(
            roundRect = RoundRect(
                left = 0.5f, top = 0.5f,
                right = size.width - 0.5f, bottom = size.height - 0.5f,
                topLeftCornerRadius = outerRadius,
                topRightCornerRadius = outerRadius,
                bottomRightCornerRadius = outerRadius,
                bottomLeftCornerRadius = outerRadius
            )
        )

        val innerBorderOutline = if (borderPainter.isPaintingInnerOutline)
            Outline.Rounded(
                roundRect = RoundRect(
                    left = 1.0f, top = 1.0f,
                    right = size.width - 1.0f, bottom = size.height - 1.0f,
                    topLeftCornerRadius = innerRadius,
                    topRightCornerRadius = innerRadius,
                    bottomRightCornerRadius = innerRadius,
                    bottomLeftCornerRadius = innerRadius
                )
            ) else null

        borderPainter.paintBorder(
            this, size, borderOutline, innerBorderOutline, borderScheme, 1.0f
        )

        // And don't forget to draw the content
        drawContent()
    }
}

private class AuroraBorderWithSides(
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val borderPainter: AuroraBorderPainter,
    private val layoutDirection: LayoutDirection,
    private val sides: Sides
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        val borderScheme = colors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = ColorSchemeAssociationKind.Border,
            componentState = ComponentState.Enabled
        )

        val borderOutline = getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = 2.0f.dp.toPx(),
            sides = sides,
            insets = 0.5f,
            outlineKind = OutlineKind.Border
        )

        val innerBorderOutline = if (borderPainter.isPaintingInnerOutline)
            getBaseOutline(
                layoutDirection = layoutDirection,
                width = size.width,
                height = size.height,
                radius = 1.0f.dp.toPx(),
                sides = sides,
                insets = 1.0f,
                outlineKind = OutlineKind.Border
            )  else null

        borderPainter.paintBorder(
            this, size, borderOutline, innerBorderOutline, borderScheme, 1.0f
        )

        // And don't forget to draw the content
        drawContent()
    }
}

