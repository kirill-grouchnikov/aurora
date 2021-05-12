/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.painter.border

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme

/**
 * Border painter that draws glass appearance.
 *
 * @author Kirill Grouchnikov
 */
class GlassBorderPainter : StandardBorderPainter() {
    override val displayName = "Glass"

    override fun getTopBorderColor(borderScheme: AuroraColorScheme): Color {
        return super.getMidBorderColor(borderScheme)
    }

    override fun getMidBorderColor(borderScheme: AuroraColorScheme): Color {
        return getTopBorderColor(borderScheme)
    }

    override fun getBottomBorderColor(borderScheme: AuroraColorScheme): Color {
        return getTopBorderColor(borderScheme)
    }
}