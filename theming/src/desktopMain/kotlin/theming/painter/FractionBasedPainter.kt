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
package org.pushingpixels.aurora.theming.painter

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.ContainerColorTokens

/**
 * Base painter with fraction-based stops and a color query associated with each
 * stop. This class allows creating multi-stop gradients with exact control over
 * which color is used at every gradient control point.
 *
 * @author Kirill Grouchnikov
 */

data class ColorStop(val fraction: Float, val alpha: Float = 1.0f, val colorQuery: (ContainerColorTokens) -> Color)

abstract class FractionBasedPainter(
    override val displayName: String,
    vararg colorQueryStops: ColorStop
) : AuroraTrait {
    protected val fractions: List<Float> = colorQueryStops.map { it.fraction }
    protected val alphas: List<Float> = colorQueryStops.map { it.alpha }
    protected val colorQueries: List<(ContainerColorTokens) -> Color> = colorQueryStops.map { it.colorQuery }

    init {
        val length = fractions.size
        require(fractions[0] == 0.0f) { "Start fraction must be 0.0f" }
        require(fractions[length - 1] == 1.0f) { "End fraction must be 1.0f" }
        for (i in 0 until length - 1) {
            require(fractions[i + 1] > fractions[i]) { "Fractions must be strictly increasing" }
        }
    }
}