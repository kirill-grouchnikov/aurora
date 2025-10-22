/*
 * Copyright (c) 2005-2025 Radiance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
