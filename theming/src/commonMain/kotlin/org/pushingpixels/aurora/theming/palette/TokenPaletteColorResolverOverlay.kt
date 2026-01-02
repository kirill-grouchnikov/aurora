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
