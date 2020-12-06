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
package org.pushingpixels.aurora.painter.decoration

import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme

/**
 * Implementation of [AuroraDecorationPainter] that uses "arc" painting on title panes and
 * lighter gradient near the center of the application frame.
 *
 * @author Kirill Grouchnikov
 */
class ArcDecorationPainter : AuroraDecorationPainter {
    override val displayName = "Arc"

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        if (decorationAreaType === DecorationAreaType.PRIMARY_TITLE_PANE
            || decorationAreaType === DecorationAreaType.SECONDARY_TITLE_PANE
        ) {
            paintTitleBackground(drawScope, componentSize, outline, rootSize, offsetFromRoot, colorScheme)
        } else {
            paintExtraBackground(drawScope, componentSize, outline, rootSize, offsetFromRoot, colorScheme)
        }
    }

    private fun paintTitleBackground(
        drawScope: DrawScope,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        // TODO - implement the arc visuals
    }

    private fun paintExtraBackground(
        drawScope: DrawScope,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorScheme: AuroraColorScheme
    ) {
        with(drawScope) {
            val gradientBottom = LinearGradient(
                0.0f to colorScheme.midColor,
                0.5f to colorScheme.lightColor,
                1.0f to colorScheme.midColor,
                startX = -offsetFromRoot.x,
                startY = 0.0f,
                endX = -offsetFromRoot.x + rootSize.width,
                endY = 0.0f,
                tileMode = TileMode.Repeated
            )

            drawOutline(
                outline = outline,
                style = Fill,
                brush = gradientBottom
            )
        }
    }
}