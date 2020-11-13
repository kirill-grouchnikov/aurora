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
package org.pushingpixels.mosaic.painter

import androidx.compose.ui.graphics.Color
import org.pushingpixels.mosaic.MosaicTrait
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme

/**
 * Base painter with fraction-based stops and a color query associated with each
 * stop. This class allows creating multi-stop gradients with exact control over
 * which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */
abstract class FractionBasedPainter(
    override val displayName: String, private val fractions: FloatArray,
    private val colorQueries: Array<(MosaicColorScheme) -> Color>
) : MosaicTrait {
    /**
     * Returns the fractions of this painter.
     *
     * @return Fractions of this painter.
     */
    fun getFractions(): FloatArray {
        return fractions.copyOf()
    }

    /**
     * Returns the color queries of this painter.
     *
     * @return Color queries of this painter.
     */
    fun getColorQueries(): Array<(MosaicColorScheme) -> Color> {
        return colorQueries.copyOf()
    }

    init {
        require(!(fractions == null || colorQueries == null)) { "Cannot pass null arguments" }
        require(fractions.size == colorQueries.size) { "Argument length does not match" }
        val length = fractions.size
        require(!(fractions[0] != 0.0f || fractions[length - 1] != 1.0f)) { "End fractions must be 0.0 and 1.0" }
        for (i in 0 until length - 1) {
            require(fractions[i + 1] > fractions[i]) { "Fractions must be strictly increasing" }
        }
    }
}