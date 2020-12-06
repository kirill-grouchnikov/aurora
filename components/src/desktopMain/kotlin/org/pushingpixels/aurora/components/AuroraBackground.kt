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
package org.pushingpixels.aurora.components

import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.runtime.Composable
import androidx.compose.ui.ContentDrawScope
import androidx.compose.ui.DrawModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.ComponentState
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.painter.overlay.AuroraOverlayPainter

@Composable
fun Modifier.auroraBackground() = this.then(
    AuroraBackground(
        rootSize = Size(
            width = AppWindowAmbient.current!!.width.toFloat(),
            height = AppWindowAmbient.current!!.height.toFloat()
        ),
        decorationAreaType = AuroraSkin.decorationArea.type,
        colors = AuroraSkin.colors,
        decorationPainter = AuroraSkin.painters.decorationPainter,
        overlayPainters = AuroraSkin.painters.getOverlayPainters(AuroraSkin.decorationArea.type)
    )
)

private class AuroraBackground(
    private val rootSize: Size,
    private val decorationAreaType: DecorationAreaType,
    private val colors: AuroraSkinColors,
    private val decorationPainter: AuroraDecorationPainter,
    private val overlayPainters: List<AuroraOverlayPainter>
) : OnGloballyPositionedModifier, DrawModifier {
    var offset = Offset(0.0f, 0.0f)

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        offset = coordinates.localToRoot(Offset(0.0f, 0.0f))
    }

    override fun ContentDrawScope.draw() {
        if (decorationAreaType != DecorationAreaType.NONE
            && colors.isRegisteredAsDecorationArea(decorationAreaType)
        ) {
            // If the current skin has a decoration painter that provides custom visuals
            // for this decoration area, use it
            decorationPainter.paintDecorationArea(
                drawScope = this,
                decorationAreaType = decorationAreaType,
                componentSize = size,
                outline = Outline.Rectangle(Rect(Offset(0.0f, 0.0f), size)),
                rootSize = rootSize,
                offsetFromRoot = offset,
                colorScheme = colors.getBackgroundColorScheme(decorationAreaType)
            )
        } else {
            // Otherwise use flat color fill
            drawRect(
                color = colors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.FILL,
                    componentState = ComponentState.ENABLED
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
