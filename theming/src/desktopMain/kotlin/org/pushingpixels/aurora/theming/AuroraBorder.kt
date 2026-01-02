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
package org.pushingpixels.aurora.theming

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.painter.outline.AuroraOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import org.pushingpixels.aurora.theming.utils.paintOutline

@Composable
fun Modifier.auroraBorder(): Modifier = this.then(
    AuroraBorder(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        outlinePainter = AuroraSkin.painters.outlinePainter,
        outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay
    )
)

@Composable
fun Modifier.auroraBorder(sides: Sides): Modifier = this.then(
    AuroraBorderWithSides(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        outlinePainter = AuroraSkin.painters.outlinePainter,
        outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay,
        sides = sides
    )
)

private object BorderOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = with (density) {
            2.0f.dp.toPx()
        }
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = cornerRadius - radiusAdjustment,
            sides = Sides(),
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

private class BorderWithSidesOutlineSuppler(val sides: Sides): OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = with (density) {
            2.0f.dp.toPx()
        }
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = cornerRadius - radiusAdjustment,
            sides = sides,
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

private class AuroraBorder(
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val outlinePainter: AuroraOutlinePainter,
    private val outlinePainterOverlay: AuroraOutlinePainter.Overlay?,
) : DrawModifier {
    @OptIn(AuroraInternalApi::class)
    override fun ContentDrawScope.draw() {
        val borderTokens = colors.getNeutralContainerTokens(decorationAreaType = decorationAreaType)

        paintOutline(
            drawScope = this,
            componentState = ComponentState.Enabled,
            outlinePainter = outlinePainter,
            outlinePainterOverlay = outlinePainterOverlay,
            size = this.size,
            alpha = 1.0f,
            outlineSupplier = BorderOutlineSuppler,
            colorTokens = borderTokens)

        // And don't forget to draw the content
        drawContent()
    }
}

private class AuroraBorderWithSides(
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val outlinePainter: AuroraOutlinePainter,
    private val outlinePainterOverlay: AuroraOutlinePainter.Overlay?,
    private val sides: Sides
) : DrawModifier {

    @OptIn(AuroraInternalApi::class)
    override fun ContentDrawScope.draw() {
        val borderTokens = colors.getNeutralContainerTokens(decorationAreaType = decorationAreaType)

        paintOutline(
            drawScope = this,
            componentState = ComponentState.Enabled,
            outlinePainter = outlinePainter,
            outlinePainterOverlay = outlinePainterOverlay,
            size = this.size,
            alpha = 1.0f,
            outlineSupplier = BorderWithSidesOutlineSuppler(sides),
            colorTokens = borderTokens)

        // And don't forget to draw the content
        drawContent()
    }
}

