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
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.painter.outline.AuroraOutlinePainter
import org.pushingpixels.aurora.theming.shaper.AuroraComponentShaper
import org.pushingpixels.aurora.theming.utils.paintOutline

@Composable
fun Modifier.auroraBorder(): Modifier = this.then(
    AuroraBorder(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        outlinePainter = AuroraSkin.painters.outlinePainter,
        outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay,
        componentShaper = AuroraSkin.componentShaper
    )
)

@Composable
fun Modifier.auroraBorder(sides: Sides): Modifier = this.then(
    AuroraBorderWithSides(
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        outlinePainter = AuroraSkin.painters.outlinePainter,
        outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay,
        componentShaper = AuroraSkin.componentShaper,
        sides = sides
    )
)

private class AuroraBorder(
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val outlinePainter: AuroraOutlinePainter,
    private val outlinePainterOverlay: AuroraOutlinePainter.Overlay?,
    private val componentShaper: AuroraComponentShaper,
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
            outlineSupplier = componentShaper.getBaselineOutlineSupplier(),
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
    private val componentShaper: AuroraComponentShaper,
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
            outlineSupplier = componentShaper.getBaselineOutlineSupplier(sides),
            colorTokens = borderTokens)

        // And don't forget to draw the content
        drawContent()
    }
}

