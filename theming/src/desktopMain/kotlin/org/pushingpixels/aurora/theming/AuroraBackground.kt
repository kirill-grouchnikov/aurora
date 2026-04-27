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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.decorator.window.AuroraWindowDecorator
import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter

@OptIn(AuroraInternalApi::class)
@Composable
fun Modifier.auroraBackground() = this.then(
    // TODO - is there another way to get window size in here without our own composition local?
    AuroraBackground(
        isWindowDecorated = LocalWindowDecorated.current,
        rootSize = Size(
            width = LocalTopWindowSize.current.width.value * LocalDensity.current.density,
            height = LocalTopWindowSize.current.height.value * LocalDensity.current.density
        ),
        decorationAreaType = AuroraSkin.decorationAreaType,
        windowDecorator = AuroraSkin.decorators.windowDecorator,
        colors = AuroraSkin.colors,
        decorationPainter = AuroraSkin.painters.decorationPainter,
        showOverlays = true
    )
)

@OptIn(AuroraInternalApi::class)
@Composable
fun Modifier.auroraFlatBackground(color: Color) = this.then(
    // TODO - is there another way to get window size in here without our own composition local?
    AuroraFlatBackground(
        isWindowDecorated = LocalWindowDecorated.current,
        rootSize = Size(
            width = LocalTopWindowSize.current.width.value * LocalDensity.current.density,
            height = LocalTopWindowSize.current.height.value * LocalDensity.current.density
        ),
        decorationAreaType = AuroraSkin.decorationAreaType,
        decorationPainter = AuroraSkin.painters.decorationPainter,
        windowDecorator = AuroraSkin.decorators.windowDecorator,
        color = color,
        inlayColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
    )
)

@OptIn(AuroraInternalApi::class)
@Composable
fun Modifier.auroraBackgroundNoOverlays() = this.then(
    // TODO - is there another way to get window size in here without our own composition local?
    AuroraBackground(
        isWindowDecorated = LocalWindowDecorated.current,
        rootSize = Size(
            width = LocalTopWindowSize.current.width.value * LocalDensity.current.density,
            height = LocalTopWindowSize.current.height.value * LocalDensity.current.density
        ),
        decorationAreaType = AuroraSkin.decorationAreaType,
        windowDecorator = AuroraSkin.decorators.windowDecorator,
        colors = AuroraSkin.colors,
        decorationPainter = AuroraSkin.painters.decorationPainter,
        showOverlays = false
    )
)

private class AuroraBackground(
    private val isWindowDecorated: Boolean,
    private val rootSize: Size,
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val decorationPainter: AuroraDecorationPainter,
    private val windowDecorator: AuroraWindowDecorator,
    private val showOverlays: Boolean
) : OnGloballyPositionedModifier, DrawModifier {
    var offset = Offset.Zero

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        offset = coordinates.localToRoot(Offset.Zero)
    }

    override fun ContentDrawScope.draw() {
        val extraOffset = if (isWindowDecorated) windowDecorator.getWindowBorderInsets().toPx() else 0.0f
        val tweakedOffset = if (isWindowDecorated)
            Offset(offset.x - extraOffset, offset.y - extraOffset) else offset

        val colorTokens = colors.getNeutralContainerTokens(decorationAreaType)
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
                offsetFromRoot = tweakedOffset,
                colorTokens = colorTokens,
            )
        } else {
            // Otherwise use flat container surface fill
            drawRect(
                color = colorTokens.containerSurface
            )
        }

        // Ask the inlay painter to paint its visuals
        decorationPainter.inlayPainter?.paintInlay(
            drawScope = this,
            decorationAreaType = decorationAreaType,
            rootSize = rootSize,
            offsetFromRoot = tweakedOffset,
            width = size.width,
            height = size.height,
            colorTokens = colorTokens
        )

        if (showOverlays) {
            // If we have overlay painters registered for this decoration area, ask
            // each one to paint their visuals
            decorationPainter.getOverlayPainters(decorationAreaType).forEach {
                it.paintOverlay(
                    drawScope = this,
                    decorationAreaType = decorationAreaType,
                    width = size.width,
                    height = size.height,
                    colorTokens = colorTokens
                )
            }
        }

        // And don't forget to draw the content
        drawContent()
    }
}

private class AuroraFlatBackground(
    private val isWindowDecorated: Boolean,
    private val rootSize: Size,
    private val decorationAreaType: DecorationAreaType,
    private val decorationPainter: AuroraDecorationPainter,
    private val windowDecorator: AuroraWindowDecorator,
    private val color: Color,
    private val inlayColorTokens: ContainerColorTokens,
) : OnGloballyPositionedModifier, DrawModifier {
    var offset = Offset.Zero

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        offset = coordinates.localToRoot(Offset.Zero)
    }

    override fun ContentDrawScope.draw() {
        val extraOffset = if (isWindowDecorated) windowDecorator.getWindowBorderInsets().toPx() else 0.0f
        val tweakedOffset = if (isWindowDecorated)
            Offset(offset.x - extraOffset, offset.y - extraOffset) else offset

        // Use flat fill
        drawRect(color = color)

        // Ask the inlay painter to paint its visuals
        decorationPainter.inlayPainter?.paintInlay(
            drawScope = this,
            decorationAreaType = decorationAreaType,
            rootSize = rootSize,
            offsetFromRoot = tweakedOffset,
            width = size.width,
            height = size.height,
            colorTokens = inlayColorTokens
        )

        // And don't forget to draw the content
        drawContent()
    }
}
