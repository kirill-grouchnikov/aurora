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
import org.pushingpixels.ephemeral.chroma.palettes.TokenPalette

interface TokenPaletteColorResolver {
    fun getContainerSurfaceLowest(tokenPalette: TokenPalette): Color
    fun getContainerSurfaceLow(tokenPalette: TokenPalette): Color
    fun getContainerSurface(tokenPalette: TokenPalette): Color
    fun getContainerSurfaceHigh(tokenPalette: TokenPalette): Color
    fun getContainerSurfaceHighest(tokenPalette: TokenPalette): Color

    fun getContainerSurfaceDim(tokenPalette: TokenPalette): Color
    fun getContainerSurfaceBright(tokenPalette: TokenPalette): Color

    fun getOnContainer(tokenPalette: TokenPalette): Color
    fun getOnContainerVariant(tokenPalette: TokenPalette): Color

    fun getContainerOutline(tokenPalette: TokenPalette): Color
    fun getContainerOutlineVariant(tokenPalette: TokenPalette): Color

    fun getContainerSurfaceDisabledAlpha(tokenPalette: TokenPalette): Float
    fun getOnContainerDisabledAlpha(tokenPalette: TokenPalette): Float
    fun getContainerOutlineDisabledAlpha(tokenPalette: TokenPalette): Float

    fun getInverseContainerSurface(tokenPalette: TokenPalette): Color
    fun getInverseOnContainer(tokenPalette: TokenPalette): Color
    fun getInverseContainerOutline(tokenPalette: TokenPalette): Color

    fun getComplementaryOnContainer(tokenPalette: TokenPalette): Color
    fun getComplementaryContainerOutline(tokenPalette: TokenPalette): Color

    fun getAccentOnContainer(tokenPalette: TokenPalette): Color
}
