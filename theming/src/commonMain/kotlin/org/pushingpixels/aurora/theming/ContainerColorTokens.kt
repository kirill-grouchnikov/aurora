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
package org.pushingpixels.aurora.theming

import androidx.compose.ui.graphics.Color

interface ContainerColorTokens {
    val isDark: Boolean

    val containerSurfaceLowest: Color
    val containerSurfaceLow: Color
    val containerSurface: Color
    val containerSurfaceHigh: Color
    val containerSurfaceHighest: Color

    val containerSurfaceDim: Color
    val containerSurfaceBright: Color

    val onContainer: Color
    val onContainerVariant: Color

    val containerOutline: Color
    val containerOutlineVariant: Color

    val containerSurfaceDisabledAlpha: Float
    val onContainerDisabledAlpha: Float
    val containerOutlineDisabledAlpha: Float

    val inverseContainerSurface: Color
    val inverseOnContainer: Color
    val inverseContainerOutline: Color

    val complementaryOnContainer: Color
    val complementaryContainerOutline: Color

    val accentOnContainer: Color
}
