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
package org.pushingpixels.aurora.theming.painter.surface

import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.painter.ColorStop

/**
 * Surface painter that draws visuals with classic appearance.
 *
 * @author Kirill Grouchnikov
 */
open class ClassicSurfacePainter : FractionBasedSurfacePainter(
    ColorStop(fraction = 0.0f, colorQuery = {
        if (it.isDark) it.containerSurfaceLow else it.containerSurfaceHigh
    }),
    ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
    ColorStop(fraction = 1.0f, colorQuery = {
        if (it.isDark) it.containerSurfaceHighest else it.containerSurfaceLowest
    }),
    displayName = "Classic"
) {
    companion object {
        /** Reusable instance of this painter. */
        val Instance = ClassicSurfacePainter()
    }
}
