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
package org.pushingpixels.substance.api.shaper

import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.mosaic.ButtonSides
import org.pushingpixels.mosaic.shaper.MosaicButtonShaper
import org.pushingpixels.mosaic.shaper.RectangularButtonShaper
import org.pushingpixels.mosaic.utils.getBaseOutline

/**
 * Button shaper that returns buttons with completely rounded corners.
 *
 * @author Kirill Grouchnikov
 */
class StandardButtonShaper : MosaicButtonShaper, RectangularButtonShaper {
    override val displayName: String
        get() = "Standard"

    override fun getButtonOutline(
        width: Float,
        height: Float,
        extraInsets: Float,
        isInner: Boolean,
        sides: ButtonSides,
        drawScope: DrawScope
    ): Outline {
        var radius = getCornerRadius(width, height, extraInsets, drawScope)
        if (isInner) {
            radius -= 1.0f
            if (radius < 0.0f) radius = 0.0f
        }

        return getBaseOutline(
            width, height, radius, sides.straightSides,
            extraInsets
        )
    }

    override fun getCornerRadius(width: Float, height: Float, insets: Float, drawScope: DrawScope): Float {
        return if (width > height) {
            (height - 2 * insets) / 2.0f
        } else {
            (width - 2 * insets) / 2.0f
        }
    }
}