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
package org.pushingpixels.aurora.theming.palette

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.dynamiccolor.DynamicBimodalPalette
import org.pushingpixels.ephemeral.chroma.dynamiccolor.DynamicPalette
import org.pushingpixels.ephemeral.chroma.dynamiccolor.DuotonePalette
import org.pushingpixels.ephemeral.chroma.hct.Hct

fun getContainerTokens(
    seed: Hct,
    containerConfiguration: ContainerConfiguration,
    colorResolver: TokenPaletteColorResolver = DefaultPaletteColorResolver
): ContainerColorTokens {
    val dynamicPalette = DynamicPalette(
        /* sourceColorHct */ seed,
        /* containerConfiguration */ containerConfiguration
    )

    return object : ContainerColorTokens {
        override val isDark: Boolean
            get() = dynamicPalette.containerConfiguration.isDark

        override val containerSurfaceLowest: Color
            get() = colorResolver.getContainerSurfaceLowest(dynamicPalette)

        override val containerSurfaceLow: Color
            get() = colorResolver.getContainerSurfaceLow(dynamicPalette)

        override val containerSurface: Color
            get() = colorResolver.getContainerSurface(dynamicPalette)

        override val containerSurfaceHigh: Color
            get() = colorResolver.getContainerSurfaceHigh(dynamicPalette)

        override val containerSurfaceHighest: Color
            get() = colorResolver.getContainerSurfaceHighest(dynamicPalette)

        override val containerSurfaceDim: Color
            get() = colorResolver.getContainerSurfaceDim(dynamicPalette)

        override val containerSurfaceBright: Color
            get() = colorResolver.getContainerSurfaceBright(dynamicPalette)

        override val containerShadow: Color
            get() = colorResolver.getContainerShadow(dynamicPalette)

        override val onContainerLow: Color
            get() = colorResolver.getOnContainerLow(dynamicPalette)

        override val onContainer: Color
            get() = colorResolver.getOnContainer(dynamicPalette)

        override val onContainerHigh: Color
            get() = colorResolver.getOnContainerHigh(dynamicPalette)

        override val containerOutlineLow: Color
            get() = colorResolver.getContainerOutlineLow(dynamicPalette)

        override val containerOutline: Color
            get() = colorResolver.getContainerOutline(dynamicPalette)

        override val containerOutlineHigh: Color
            get() = colorResolver.getContainerOutlineHigh(dynamicPalette)

        override val containerSurfaceEnabledAlpha: Float
            get() = colorResolver.getContainerSurfaceEnabledAlpha(dynamicPalette)

        override val onContainerEnabledAlpha: Float
            get() = colorResolver.getOnContainerEnabledAlpha(dynamicPalette)

        override val containerOutlineEnabledAlpha: Float
            get() = colorResolver.getContainerOutlineEnabledAlpha(dynamicPalette)

        override val containerSurfaceDisabledAlpha: Float
            get() = colorResolver.getContainerSurfaceDisabledAlpha(dynamicPalette)

        override val onContainerDisabledAlpha: Float
            get() = colorResolver.getOnContainerDisabledAlpha(dynamicPalette)

        override val containerOutlineDisabledAlpha: Float
            get() = colorResolver.getContainerOutlineDisabledAlpha(dynamicPalette)

        override val inverseContainerSurface: Color
            get() = colorResolver.getInverseContainerSurface(dynamicPalette)

        override val inverseOnContainer: Color
            get() = colorResolver.getInverseOnContainer(dynamicPalette)

        override val inverseContainerOutline: Color
            get() = colorResolver.getInverseContainerOutline(dynamicPalette)

        override val complementaryOnContainer: Color
            get() = colorResolver.getComplementaryOnContainer(dynamicPalette)

        override val complementaryContainerOutline: Color
            get() = colorResolver.getComplementaryContainerOutline(dynamicPalette)

        override val accentOnContainer: Color
            get() = colorResolver.getAccentOnContainer(dynamicPalette)

        override val markerOnContainer: Color
            get() = colorResolver.getMarkerOnContainer(dynamicPalette)

        override val complementaryMarkerOnContainer: Color
            get() = colorResolver.getComplementaryMarkerOnContainer(dynamicPalette)
    }
}

fun getBimodalContainerTokens(
    seedOne: Hct,
    seedTwo: Hct,
    transitionRange: DynamicBimodalPalette.TransitionRange,
    fidelityTone: Double,
    containerConfiguration: ContainerConfiguration,
    colorResolver: TokenPaletteColorResolver = DefaultPaletteColorResolver
): ContainerColorTokens {
    val bimodalPalette = DynamicBimodalPalette(
        /* seedOne */ seedOne,
        /* seedTwo */ seedTwo,
        /* transitionRange */ transitionRange,
        /* fidelityTone */ fidelityTone,
        /* containerConfiguration */ containerConfiguration
    )

    return object : ContainerColorTokens {
        override val isDark: Boolean
            get() = bimodalPalette.containerConfiguration.isDark()

        override val containerSurfaceLowest: Color
            get() = colorResolver.getContainerSurfaceLowest(bimodalPalette)

        override val containerSurfaceLow: Color
            get() = colorResolver.getContainerSurfaceLow(bimodalPalette)

        override val containerSurface: Color
            get() = colorResolver.getContainerSurface(bimodalPalette)

        override val containerSurfaceHigh: Color
            get() = colorResolver.getContainerSurfaceHigh(bimodalPalette)

        override val containerSurfaceHighest: Color
            get() = colorResolver.getContainerSurfaceHighest(bimodalPalette)

        override val containerSurfaceDim: Color
            get() = colorResolver.getContainerSurfaceDim(bimodalPalette)

        override val containerSurfaceBright: Color
            get() = colorResolver.getContainerSurfaceBright(bimodalPalette)

        override val containerShadow: Color
            get() = colorResolver.getContainerShadow(bimodalPalette)

        override val onContainerLow: Color
            get() = colorResolver.getOnContainerLow(bimodalPalette)

        override val onContainer: Color
            get() = colorResolver.getOnContainer(bimodalPalette)

        override val onContainerHigh: Color
            get() = colorResolver.getOnContainerHigh(bimodalPalette)

        override val containerOutlineLow: Color
            get() = colorResolver.getContainerOutlineLow(bimodalPalette)

        override val containerOutline: Color
            get() = colorResolver.getContainerOutline(bimodalPalette)

        override val containerOutlineHigh: Color
            get() = colorResolver.getContainerOutlineHigh(bimodalPalette)

        override val containerSurfaceEnabledAlpha: Float
            get() = colorResolver.getContainerSurfaceEnabledAlpha(bimodalPalette)

        override val onContainerEnabledAlpha: Float
            get() = colorResolver.getOnContainerEnabledAlpha(bimodalPalette)

        override val containerOutlineEnabledAlpha: Float
            get() = colorResolver.getContainerOutlineEnabledAlpha(bimodalPalette)

        override val containerSurfaceDisabledAlpha: Float
            get() = colorResolver.getContainerSurfaceDisabledAlpha(bimodalPalette)

        override val onContainerDisabledAlpha: Float
            get() = colorResolver.getOnContainerDisabledAlpha(bimodalPalette)

        override val containerOutlineDisabledAlpha: Float
            get() = colorResolver.getContainerOutlineDisabledAlpha(bimodalPalette)

        override val inverseContainerSurface: Color
            get() = colorResolver.getInverseContainerSurface(bimodalPalette)

        override val inverseOnContainer: Color
            get() = colorResolver.getInverseOnContainer(bimodalPalette)

        override val inverseContainerOutline: Color
            get() = colorResolver.getInverseContainerOutline(bimodalPalette)

        override val complementaryOnContainer: Color
            get() = colorResolver.getComplementaryOnContainer(bimodalPalette)

        override val complementaryContainerOutline: Color
            get() = colorResolver.getComplementaryContainerOutline(bimodalPalette)

        override val accentOnContainer: Color
            get() = colorResolver.getAccentOnContainer(bimodalPalette)

        override val markerOnContainer: Color
            get() = colorResolver.getMarkerOnContainer(bimodalPalette)

        override val complementaryMarkerOnContainer: Color
            get() = colorResolver.getComplementaryMarkerOnContainer(bimodalPalette)
    }
}

fun getDuotoneContainerTokens(
    seedContainer: Hct,
    seedOnContainer: Hct,
    containerConfiguration: ContainerConfiguration,
    colorResolver: TokenPaletteColorResolver = DefaultPaletteColorResolver
): ContainerColorTokens {
    val duotonePalette = DuotonePalette(
        /* seedContainer */ seedContainer,
        /* seedOnContainer */ seedOnContainer,
        /* containerConfiguration */ containerConfiguration
    )

    return object : ContainerColorTokens {
        override val isDark: Boolean
            get() = duotonePalette.containerConfiguration.isDark()

        override val containerSurfaceLowest: Color
            get() = colorResolver.getContainerSurfaceLowest(duotonePalette)

        override val containerSurfaceLow: Color
            get() = colorResolver.getContainerSurfaceLow(duotonePalette)

        override val containerSurface: Color
            get() = colorResolver.getContainerSurface(duotonePalette)

        override val containerSurfaceHigh: Color
            get() = colorResolver.getContainerSurfaceHigh(duotonePalette)

        override val containerSurfaceHighest: Color
            get() = colorResolver.getContainerSurfaceHighest(duotonePalette)

        override val containerSurfaceDim: Color
            get() = colorResolver.getContainerSurfaceDim(duotonePalette)

        override val containerSurfaceBright: Color
            get() = colorResolver.getContainerSurfaceBright(duotonePalette)

        override val containerShadow: Color
            get() = colorResolver.getContainerShadow(duotonePalette)

        override val onContainerLow: Color
            get() = colorResolver.getOnContainerLow(duotonePalette)

        override val onContainer: Color
            get() = colorResolver.getOnContainer(duotonePalette)

        override val onContainerHigh: Color
            get() = colorResolver.getOnContainerHigh(duotonePalette)

        override val containerOutlineLow: Color
            get() = colorResolver.getContainerOutlineLow(duotonePalette)

        override val containerOutline: Color
            get() = colorResolver.getContainerOutline(duotonePalette)

        override val containerOutlineHigh: Color
            get() = colorResolver.getContainerOutlineHigh(duotonePalette)

        override val containerSurfaceEnabledAlpha: Float
            get() = colorResolver.getContainerSurfaceEnabledAlpha(duotonePalette)

        override val onContainerEnabledAlpha: Float
            get() = colorResolver.getOnContainerEnabledAlpha(duotonePalette)

        override val containerOutlineEnabledAlpha: Float
            get() = colorResolver.getContainerOutlineEnabledAlpha(duotonePalette)

        override val containerSurfaceDisabledAlpha: Float
            get() = colorResolver.getContainerSurfaceDisabledAlpha(duotonePalette)

        override val onContainerDisabledAlpha: Float
            get() = colorResolver.getOnContainerDisabledAlpha(duotonePalette)

        override val containerOutlineDisabledAlpha: Float
            get() = colorResolver.getContainerOutlineDisabledAlpha(duotonePalette)

        override val inverseContainerSurface: Color
            get() = colorResolver.getInverseContainerSurface(duotonePalette)

        override val inverseOnContainer: Color
            get() = colorResolver.getInverseOnContainer(duotonePalette)

        override val inverseContainerOutline: Color
            get() = colorResolver.getInverseContainerOutline(duotonePalette)

        override val complementaryOnContainer: Color
            get() = colorResolver.getComplementaryOnContainer(duotonePalette)

        override val complementaryContainerOutline: Color
            get() = colorResolver.getComplementaryContainerOutline(duotonePalette)

        override val accentOnContainer: Color
            get() = colorResolver.getAccentOnContainer(duotonePalette)

        override val markerOnContainer: Color
            get() = colorResolver.getMarkerOnContainer(duotonePalette)

        override val complementaryMarkerOnContainer: Color
            get() = colorResolver.getComplementaryMarkerOnContainer(duotonePalette)
    }
}