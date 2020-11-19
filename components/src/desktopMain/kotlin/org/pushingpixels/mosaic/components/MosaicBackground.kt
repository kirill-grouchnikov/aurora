/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.ContentDrawScope
import androidx.compose.ui.DrawModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import org.pushingpixels.mosaic.ColorSchemeAssociationKind
import org.pushingpixels.mosaic.ComponentState
import org.pushingpixels.mosaic.DecorationAreaType
import org.pushingpixels.mosaic.MosaicSkin
import org.pushingpixels.mosaic.colorscheme.MosaicSkinColors
import org.pushingpixels.mosaic.painter.decoration.MosaicDecorationPainter
import org.pushingpixels.mosaic.painter.overlay.MosaicOverlayPainter


@Composable
fun Modifier.mosaicBackground() = this.then(
    MosaicBackground(
        decorationAreaType = MosaicSkin.decorationArea.type,
        colors = MosaicSkin.colors,
        decorationPainter = MosaicSkin.painters.decorationPainter,
        overlayPainters = MosaicSkin.painters.getOverlayPainters(MosaicSkin.decorationArea.type)
    )
)

private class MosaicBackground(
    private val decorationAreaType: DecorationAreaType,
    private val colors: MosaicSkinColors,
    private val decorationPainter: MosaicDecorationPainter,
    private val overlayPainters: List<MosaicOverlayPainter>
) : OnGloballyPositionedModifier, DrawModifier {
    var offset = Offset(0.0f, 0.0f)

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        offset = coordinates.localToRoot(Offset(0.0f, 0.0f))
    }

    override fun ContentDrawScope.draw() {
        // TODO - this needs to use the decoration painter on relevant areas
        // TODO - this also needs to draw overlays from the current skin that match the decoration area
        println(offset.y)

        if (decorationAreaType != DecorationAreaType.NONE
            && colors.isRegisteredAsDecorationArea(decorationAreaType)
        ) {
            decorationPainter.paintDecorationArea(
                drawScope = this,
                decorationAreaType = decorationAreaType,
                componentSize = size,
                outline = Outline.Rectangle(Rect(Offset(0.0f, 0.0f), size)),
                offsetFromRoot = offset,
                colorScheme = colors.getBackgroundColorScheme(decorationAreaType)
            )
        } else {
            drawRect(
                color = colors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.FILL,
                    componentState = ComponentState.ENABLED
                ).backgroundFillColor
            )
        }

        if (overlayPainters.isNotEmpty()) {
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

        drawContent()
    }
}