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

import org.pushingpixels.ephemeral.chroma.palettes.TokenPalette

class TokenPaletteColorResolverOverlay(
    val containerSurfaceLowest: ((TokenPalette) -> Int)? = null,
    val containerSurfaceLow: ((TokenPalette) -> Int)? = null,
    val containerSurface: ((TokenPalette) -> Int)? = null,
    val containerSurfaceHigh: ((TokenPalette) -> Int)? = null,
    val containerSurfaceHighest: ((TokenPalette) -> Int)? = null,
    val containerSurfaceDim: ((TokenPalette) -> Int)? = null,
    val containerSurfaceBright: ((TokenPalette) -> Int)? = null,
    val onContainer: ((TokenPalette) -> Int)? = null,
    val onContainerVariant: ((TokenPalette) -> Int)? = null,
    val containerOutline: ((TokenPalette) -> Int)? = null,
    val containerOutlineVariant: ((TokenPalette) -> Int)? = null,
    val containerSurfaceDisabledAlpha: ((TokenPalette) -> Float)? = null,
    val onContainerDisabledAlpha: ((TokenPalette) -> Float)? = null,
    val containerOutlineDisabledAlpha: ((TokenPalette) -> Float)? = null,
    val inverseContainerSurface: ((TokenPalette) -> Int)? = null,
    val inverseOnContainer: ((TokenPalette) -> Int)? = null,
    val inverseContainerOutline: ((TokenPalette) -> Int)? = null,
    val complementaryOnContainer: ((TokenPalette) -> Int)? = null,
    val complementaryContainerOutline: ((TokenPalette) -> Int)? = null,
    val accentOnContainer: ((TokenPalette) -> Int)? = null)
