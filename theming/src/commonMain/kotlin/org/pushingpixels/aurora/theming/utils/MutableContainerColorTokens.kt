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
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.ContainerColorTokens

@AuroraInternalApi
data class MutableContainerColorTokens(
    var isDarkAttr: Boolean = false,
    var containerSurfaceLowestAttr: Color = Color.Unspecified,
    var containerSurfaceLowAttr: Color = Color.Unspecified,
    var containerSurfaceAttr: Color = Color.Unspecified,
    var containerSurfaceHighAttr: Color = Color.Unspecified,
    var containerSurfaceHighestAttr: Color = Color.Unspecified,
    var containerSurfaceDimAttr: Color = Color.Unspecified,
    var containerSurfaceBrightAttr: Color = Color.Unspecified,
    var onContainerAttr: Color = Color.Unspecified,
    var onContainerVariantAttr: Color = Color.Unspecified,
    var containerOutlineAttr: Color = Color.Unspecified,
    var containerOutlineVariantAttr: Color = Color.Unspecified,
    var containerSurfaceDisabledAlphaAttr: Float = 0.0f,
    var onContainerDisabledAlphaAttr: Float = 0.0f,
    var containerOutlineDisabledAlphaAttr: Float = 0.0f,
    var inverseContainerSurfaceAttr: Color = Color.Unspecified,
    var inverseOnContainerAttr: Color = Color.Unspecified,
    var inverseContainerOutlineAttr: Color = Color.Unspecified,
    var complementaryOnContainerAttr: Color = Color.Unspecified,
    var complementaryContainerOutlineAttr: Color = Color.Unspecified,
    var accentOnContainerAttr: Color = Color.Unspecified,
) : ContainerColorTokens {
    override val isDark: Boolean
        get() = isDarkAttr

    override val containerSurfaceLowest: Color
        get() = containerSurfaceLowestAttr
    override val containerSurfaceLow: Color
        get() = containerSurfaceLowAttr
    override val containerSurface: Color
        get() = containerSurfaceAttr
    override val containerSurfaceHigh: Color
        get() = containerSurfaceHighAttr
    override val containerSurfaceHighest: Color
        get() = containerSurfaceHighestAttr

    override val containerSurfaceDim: Color
        get() = containerSurfaceDimAttr
    override val containerSurfaceBright: Color
        get() = containerSurfaceBrightAttr

    override val onContainer: Color
        get() = onContainerAttr
    override val onContainerVariant: Color
        get() = onContainerVariantAttr

    override val containerOutline: Color
        get() = containerOutlineAttr
    override val containerOutlineVariant: Color
        get() = containerOutlineVariantAttr

    override val containerSurfaceDisabledAlpha: Float
        get() = containerSurfaceDisabledAlphaAttr
    override val onContainerDisabledAlpha: Float
        get() = onContainerDisabledAlphaAttr
    override val containerOutlineDisabledAlpha: Float
        get() = containerOutlineDisabledAlphaAttr

    override val inverseContainerSurface: Color
        get() = inverseContainerSurfaceAttr
    override val inverseOnContainer: Color
        get() = inverseOnContainerAttr
    override val inverseContainerOutline: Color
        get() = inverseContainerOutlineAttr

    override val complementaryOnContainer: Color
        get() = complementaryOnContainerAttr
    override val complementaryContainerOutline: Color
        get() = complementaryContainerOutlineAttr

    override val accentOnContainer: Color
        get() = accentOnContainerAttr
}
