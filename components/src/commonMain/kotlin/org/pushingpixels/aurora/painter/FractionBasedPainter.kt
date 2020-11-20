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
package org.pushingpixels.aurora.painter

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.AuroraTrait
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme

/**
 * Base painter with fraction-based stops and a color query associated with each
 * stop. This class allows creating multi-stop gradients with exact control over
 * which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */

typealias ColorQueryStop = Pair<Float, (AuroraColorScheme) -> Color>

abstract class FractionBasedPainter(
    override val displayName: String,
    vararg colorQueryStops: ColorQueryStop
) : AuroraTrait {
    private val fractions: List<Float> = colorQueryStops.map { it.first }
    private val colorQueries: List<(AuroraColorScheme) -> Color> = colorQueryStops.map { it.second }

    /**
     * Returns the fractions of this painter.
     *
     * @return Fractions of this painter.
     */
    fun getFractions(): FloatArray {
        return fractions.toFloatArray()
    }

    /**
     * Returns the color queries of this painter.
     *
     * @return Color queries of this painter.
     */
    fun getColorQueries(): Array<(AuroraColorScheme) -> Color> {
        return colorQueries.toTypedArray()
    }

    init {
        val length = fractions.size
        require(!(fractions[0] != 0.0f || fractions[length - 1] != 1.0f)) { "End fractions must be 0.0 and 1.0" }
        for (i in 0 until length - 1) {
            require(fractions[i + 1] > fractions[i]) { "Fractions must be strictly increasing" }
        }
    }
}