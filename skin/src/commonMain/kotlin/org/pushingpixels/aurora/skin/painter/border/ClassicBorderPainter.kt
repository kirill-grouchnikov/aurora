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
package org.pushingpixels.aurora.skin.painter.border

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.skin.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.common.interpolateTowards

/**
 * Border painter that returns images with classic appearance.
 *
 * @author Kirill Grouchnikov
 */
class ClassicBorderPainter : StandardBorderPainter() {
    override val displayName: String
        get() = "Classic"

    override fun getTopBorderColor(borderScheme: AuroraColorScheme): Color {
        return super.getTopBorderColor(borderScheme).interpolateTowards(
            super.getMidBorderColor(borderScheme), 0.0f
        )
    }

    override fun getMidBorderColor(borderScheme: AuroraColorScheme): Color {
        return getTopBorderColor(borderScheme)
    }

    override fun getBottomBorderColor(borderScheme: AuroraColorScheme): Color {
        return getTopBorderColor(borderScheme)
    }
}