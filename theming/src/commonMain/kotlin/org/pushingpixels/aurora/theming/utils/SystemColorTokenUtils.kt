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
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContrastCurve
import org.pushingpixels.ephemeral.chroma.hct.Hct
import org.pushingpixels.ephemeral.chroma.palettes.TonalPalette
import kotlin.math.max
import kotlin.math.min

internal fun getSystemTokens(seed: Hct, containerConfiguration: ContainerConfiguration): ContainerColorTokens {
    val palette: TonalPalette = TonalPalette.fromHct(seed)

    return object : ContainerColorTokens {
        fun getColor(baseTone: Double, toneDelta: Double): Color {
            val tone =
                (baseTone + toneDelta * containerConfiguration.surfaceRangeAmplitudeFactor).coerceIn(0.0, 100.0)
            return Color(palette.getHct(tone).toInt())
        }

        fun getColor(contrastCurve: ContrastCurve): Color {
            val tone: Double = contrastCurve.get(containerConfiguration.getContrastLevel())
            return Color(palette.getHct(tone).toInt())
        }

        override val isDark: Boolean
            get() = containerConfiguration.isDark

        override val containerSurfaceLowest: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, -8.0) else getColor(85.0, 8.0)

        override val containerSurfaceLow: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, -2.0) else getColor(85.0, 4.0)

        override val containerSurface: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, 0.0) else getColor(85.0, 0.0)

        override val containerSurfaceHigh: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, 5.0) else getColor(85.0, -2.0)

        override val containerSurfaceHighest: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, 10.0) else getColor(85.0, -4.0)

        override val containerSurfaceDim: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, -10.0) else getColor(85.0, -6.0)

        override val containerSurfaceBright: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, 12.0) else getColor(85.0, 10.0)

        override val onContainer: Color
            get() = if (containerConfiguration.isDark)
                getColor(ContrastCurve(80.0, 90.0, 95.0, 100.0))
            else
                getColor(ContrastCurve(40.0, 30.0, 20.0, 10.0))

        override val onContainerVariant: Color
            get() = if (containerConfiguration.isDark)
                getColor(ContrastCurve(70.0, 80.0, 85.0, 90.0))
            else
                getColor(ContrastCurve(45.0, 40.0, 30.0, 20.0))

        override val containerOutline: Color
            get() = if (containerConfiguration.isDark)
                getColor(ContrastCurve(15.0, 10.0, 5.0, 0.0))
            else
                getColor(ContrastCurve(55.0, 50.0, 40.0, 30.0))

        override val containerOutlineVariant: Color
            get() = if (containerConfiguration.isDark)
                getColor(ContrastCurve(35.0, 30.0, 20.0, 10.0))
            else
                getColor(ContrastCurve(85.0, 80.0, 70.0, 50.0))

        override val containerSurfaceDisabledAlpha: Float
            get() = 0.3f

        override val onContainerDisabledAlpha: Float
            get() = 0.45f

        override val containerOutlineDisabledAlpha: Float
            get() = 0.35f

        override val inverseContainerSurface: Color
            get() = if (containerConfiguration.isDark) getColor(85.0, 0.0) else getColor(40.0, 0.0)

        override val inverseOnContainer: Color
            get() = if (containerConfiguration.isDark) getColor(40.0, 0.0) else getColor(85.0, 0.0)

        override val inverseContainerOutline: Color
            get() = if (containerConfiguration.isDark)
                getColor(ContrastCurve(25.0, 20.0, 15.0, 5.0))
            else
                getColor(ContrastCurve(15.0, 10.0, 5.0, 0.0))

        override val complementaryOnContainer: Color
            get() = if (containerConfiguration.isDark) getColor(10.0, 0.0) else getColor(80.0, 0.0)

        override val complementaryContainerOutline: Color
            get() = if (containerConfiguration.isDark)
                getColor(ContrastCurve(85.0, 90.0, 95.0, 100.0))
            else
                getColor(ContrastCurve(90.0, 95.0, 98.0, 100.0))

        override val accentOnContainer: Color
            get() = if (containerConfiguration.isDark) getColor(80.0, 0.0) else getColor(40.0, 0.0)
    }
}
