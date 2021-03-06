/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora

import androidx.compose.desktop.LocalAppWindow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.painter.overlay.AuroraOverlayPainter

@Composable
fun Modifier.auroraBackground() = this.then(
    AuroraBackground(
        rootSize = Size(
            width = LocalAppWindow.current.width * LocalDensity.current.density,
            height = LocalAppWindow.current.height * LocalDensity.current.density
        ),
        decorationAreaType = AuroraSkin.decorationAreaType,
        colors = AuroraSkin.colors,
        decorationPainter = AuroraSkin.painters.decorationPainter,
        overlayPainters = AuroraSkin.painters.getOverlayPainters(AuroraSkin.decorationAreaType)
    )
)

private class AuroraBackground(
    private val rootSize: Size,
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val decorationPainter: AuroraDecorationPainter,
    private val overlayPainters: List<AuroraOverlayPainter>
) : OnGloballyPositionedModifier, DrawModifier {
    var offset = Offset.Zero

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        offset = coordinates.localToRoot(Offset.Zero)
    }

    override fun ContentDrawScope.draw() {
        if (decorationAreaType != DecorationAreaType.None
            && colors.isRegisteredAsDecorationArea(decorationAreaType)
        ) {
            // If the current skin has a decoration painter that provides custom visuals
            // for this decoration area, use it
            decorationPainter.paintDecorationArea(
                drawScope = this,
                decorationAreaType = decorationAreaType,
                componentSize = size,
                outline = Outline.Rectangle(Rect(Offset.Zero, size)),
                rootSize = rootSize,
                offsetFromRoot = offset,
                colorScheme = colors.getBackgroundColorScheme(decorationAreaType)
            )
        } else {
            // Otherwise use flat color fill
            drawRect(
                color = colors.getBackgroundColorScheme(
                    decorationAreaType = decorationAreaType
                ).backgroundFillColor
            )
        }

        if (overlayPainters.isNotEmpty()) {
            // If we have overlay painters registered for this decoration area, ask
            // each one to paint their visuals
            for (overlayPainter in overlayPainters) {
                overlayPainter.paintOverlay(
                    drawScope = this,
                    decorationAreaType = decorationAreaType,
                    width = size.width,
                    height = size.height,
                    colors = colors
                )
            }
        }

        // And don't forget to draw the content
        drawContent()
    }
}
