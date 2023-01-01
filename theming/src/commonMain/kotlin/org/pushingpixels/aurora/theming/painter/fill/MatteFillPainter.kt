/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.painter.fill

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme

/**
 * Fill painter that returns images with matte appearance.
 *
 * @author Kirill Grouchnikov
 */
class MatteFillPainter : ClassicFillPainter() {
    override val displayName: String
        get() = "Matte"

    override fun getTopFillColor(fillScheme: AuroraColorScheme): Color {
        return super.getBottomFillColor(fillScheme).interpolateTowards(
            super.getMidFillColorTop(fillScheme), 0.5f
        )
    }

    override fun getMidFillColorTop(fillScheme: AuroraColorScheme): Color {
        return super.getMidFillColorTop(fillScheme).interpolateTowards(
            super.getBottomFillColor(fillScheme), 0.7f
        )
    }

    override fun getBottomFillColor(fillScheme: AuroraColorScheme): Color {
        return super.getMidFillColorTop(fillScheme)
    }
}