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
import org.pushingpixels.ephemeral.chroma.hct.Hct

fun getContainerTokens(seed: Hct, containerConfiguration: ContainerConfiguration): ContainerColorTokens {
    return getContainerTokens(
        seed, containerConfiguration,
        DefaultPaletteColorResolver
    )
}

fun getContainerTokens(
    seed: Hct,
    containerConfiguration: ContainerConfiguration?,
    colorResolver: TokenPaletteColorResolver
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

        override val onContainer: Color
            get() = colorResolver.getOnContainer(dynamicPalette)

        override val onContainerVariant: Color
            get() = colorResolver.getOnContainerVariant(dynamicPalette)

        override val containerOutline: Color
            get() = colorResolver.getContainerOutline(dynamicPalette)

        override val containerOutlineVariant: Color
            get() = colorResolver.getContainerOutlineVariant(dynamicPalette)

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
    }
}

fun getBimodalContainerTokens(
    seedOne: Hct,
    seedTwo: Hct,
    transitionRange: DynamicBimodalPalette.TransitionRange,
    fidelityTone: Double,
    containerConfiguration: ContainerConfiguration?,
    colorResolver: TokenPaletteColorResolver
): ContainerColorTokens {
    val dynamicPalette = DynamicBimodalPalette(
        /* seedOne */ seedOne,
        /* seedTwo */ seedTwo,
        /* transitionRange */ transitionRange,
        /* fidelityTone */ fidelityTone,
        /* containerConfiguration */ containerConfiguration
    )

    return object : ContainerColorTokens {
        override val isDark: Boolean
            get() = dynamicPalette.containerConfiguration.isDark()

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

        override val onContainer: Color
            get() = colorResolver.getOnContainer(dynamicPalette)

        override val onContainerVariant: Color
            get() = colorResolver.getOnContainerVariant(dynamicPalette)

        override val containerOutline: Color
            get() = colorResolver.getContainerOutline(dynamicPalette)

        override val containerOutlineVariant: Color
            get() = colorResolver.getContainerOutlineVariant(dynamicPalette)

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
    }
}
