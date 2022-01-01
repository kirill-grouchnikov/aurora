/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.painter

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme

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