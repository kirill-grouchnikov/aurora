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

val DefaultPaletteColorResolver: TokenPaletteColorResolver
    get() = object : TokenPaletteColorResolver {
        override fun getContainerSurfaceLowest(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurfaceLowest)
        }

        override fun getContainerSurfaceLow(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurfaceLow)
        }

        override fun getContainerSurface(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurface)
        }

        override fun getContainerSurfaceHigh(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurfaceHigh)
        }

        override fun getContainerSurfaceHighest(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurfaceHighest)
        }

        override fun getContainerSurfaceDim(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurfaceDim)
        }

        override fun getContainerSurfaceBright(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerSurfaceBright)
        }

        override fun getOnContainer(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.onContainer)
        }

        override fun getOnContainerVariant(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.onContainerVariant)
        }

        override fun getContainerOutline(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerOutline)
        }

        override fun getContainerOutlineVariant(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.containerOutlineVariant)
        }

        override fun getContainerSurfaceDisabledAlpha(tokenPalette: TokenPalette): Float {
            return 0.3f
        }

        override fun getOnContainerDisabledAlpha(tokenPalette: TokenPalette): Float {
            return 0.45f
        }

        override fun getContainerOutlineDisabledAlpha(tokenPalette: TokenPalette): Float {
            return 0.35f
        }

        override fun getInverseContainerSurface(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.inverseContainerSurface)
        }

        override fun getInverseOnContainer(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.inverseOnContainer)
        }

        override fun getInverseContainerOutline(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.inverseContainerOutline)
        }

        override fun getComplementaryOnContainer(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.complementaryOnContainer)
        }

        override fun getComplementaryContainerOutline(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.complementaryContainerOutline)
        }

        override fun getAccentOnContainer(tokenPalette: TokenPalette): Color {
            return Color(tokenPalette.accentOnContainer)
        }
    }

fun TokenPaletteColorResolver.overlayWith(overlay: TokenPaletteColorResolverOverlay): TokenPaletteColorResolver {
    val original = this
    return object : TokenPaletteColorResolver {
        override fun getContainerSurfaceLowest(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurfaceLowest
            return if (spec == null) {
                original.getContainerSurfaceLowest(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurfaceLow(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurfaceLow
            return if (spec == null) {
                original.getContainerSurfaceLow(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurface(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurface
            return if (spec == null) {
                original.getContainerSurface(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurfaceHigh(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurfaceHigh
            return if (spec == null) {
                original.getContainerSurfaceHigh(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurfaceHighest(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurfaceHighest
            return if (spec == null) {
                original.getContainerSurfaceHighest(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurfaceDim(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurfaceDim
            return if (spec == null) {
                original.getContainerSurfaceDim(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurfaceBright(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerSurfaceBright
            return if (spec == null) {
                original.getContainerSurfaceBright(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getOnContainer(tokenPalette: TokenPalette): Color {
            val spec = overlay.onContainer
            return if (spec == null) {
                original.getOnContainer(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getOnContainerVariant(tokenPalette: TokenPalette): Color {
            val spec = overlay.onContainerVariant
            return if (spec == null) {
                original.getOnContainerVariant(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerOutline(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerOutline
            return if (spec == null) {
                original.getContainerOutline(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerOutlineVariant(tokenPalette: TokenPalette): Color {
            val spec = overlay.containerOutlineVariant
            return if (spec == null) {
                original.getContainerOutlineVariant(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getContainerSurfaceDisabledAlpha(tokenPalette: TokenPalette): Float {
            val spec = overlay.containerSurfaceDisabledAlpha
            return if (spec == null) {
                original.getContainerSurfaceDisabledAlpha(tokenPalette)
            } else {
                spec.invoke(tokenPalette)
            }
        }

        override fun getOnContainerDisabledAlpha(tokenPalette: TokenPalette): Float {
            val spec = overlay.onContainerDisabledAlpha
            return if (spec == null) {
                original.getOnContainerDisabledAlpha(tokenPalette)
            } else {
                spec.invoke(tokenPalette)
            }
        }

        override fun getContainerOutlineDisabledAlpha(tokenPalette: TokenPalette): Float {
            val spec = overlay.containerOutlineDisabledAlpha
            return if (spec == null) {
                original.getContainerOutlineDisabledAlpha(tokenPalette)
            } else {
                spec.invoke(tokenPalette)
            }
        }

        override fun getInverseContainerSurface(tokenPalette: TokenPalette): Color {
            val spec = overlay.inverseContainerSurface
            return if (spec == null) {
                original.getInverseContainerSurface(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getInverseOnContainer(tokenPalette: TokenPalette): Color {
            val spec = overlay.inverseOnContainer
            return if (spec == null) {
                original.getInverseOnContainer(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getInverseContainerOutline(tokenPalette: TokenPalette): Color {
            val spec = overlay.inverseContainerOutline
            return if (spec == null) {
                original.getInverseContainerOutline(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getComplementaryOnContainer(tokenPalette: TokenPalette): Color {
            val spec = overlay.complementaryOnContainer
            return if (spec == null) {
                original.getComplementaryOnContainer(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getComplementaryContainerOutline(tokenPalette: TokenPalette): Color {
            val spec = overlay.complementaryContainerOutline
            return if (spec == null) {
                original.getComplementaryContainerOutline(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }

        override fun getAccentOnContainer(tokenPalette: TokenPalette): Color {
            val spec = overlay.accentOnContainer
            return if (spec == null) {
                original.getAccentOnContainer(tokenPalette)
            } else {
                Color(spec.invoke(tokenPalette))
            }
        }
    }
}
