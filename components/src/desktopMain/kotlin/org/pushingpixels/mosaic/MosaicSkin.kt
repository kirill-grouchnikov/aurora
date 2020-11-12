/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableContract
import androidx.compose.runtime.Providers
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Modifier
import org.pushingpixels.mosaic.colorscheme.MosaicSkinColors

object MosaicSkin {
    @Composable
    @ComposableContract(readonly = true)
    val decorationArea: DecorationArea
        get() = AmbientDecorationArea.current

    @Composable
    @ComposableContract(readonly = true)
    val colorSchemes: ColorSchemes
        get() = AmbientColorSchemes.current

    @Composable
    @ComposableContract(readonly = true)
    val colorSchemeBundle: ColorSchemeBundle
        get() = AmbientColorSchemeBundles.current

    @Composable
    @ComposableContract(readonly = true)
    val colors: MosaicSkinColors
        get() = AmbientSkinColors.current

    @Composable
    @ComposableContract(readonly = true)
    val shapes: ButtonShaper
        get() = AmbientShapes.current

    @Composable
    @ComposableContract(readonly = true)
    val painters: Painters
        get() = AmbientPainters.current

    @Composable
    @ComposableContract(readonly = true)
    val animationConfig: AnimationConfig
        get() = AmbientAnimationConfig.current
}

@Composable
fun MosaicSkin(
    decorationArea: DecorationArea = MosaicSkin.decorationArea,
    colorSchemes: ColorSchemes = MosaicSkin.colorSchemes,
    colorSchemeBundle: ColorSchemeBundle = MosaicSkin.colorSchemeBundle,
    colors: MosaicSkinColors = MosaicSkin.colors,
    shapes: ButtonShaper = MosaicSkin.shapes,
    painters: Painters = MosaicSkin.painters,
    animationConfig: AnimationConfig = MosaicSkin.animationConfig,
    content: @Composable () -> Unit
) {
    Providers(
        AmbientDecorationArea provides decorationArea,
        AmbientColorSchemes provides colorSchemes,
        AmbientColorSchemeBundles provides colorSchemeBundle,
        AmbientSkinColors provides colors,
        AmbientShapes provides shapes,
        AmbientPainters provides painters,
        AmbientAnimationConfig provides animationConfig
    ) {
        content()
    }
}

@Composable
fun DecorationArea(
    decorationAreaType: DecorationAreaType,
    content: @Composable () -> Unit = emptyContent()
) {
    MosaicSkin(decorationArea = DecorationArea(decorationAreaType)) {
        content()
    }
}

class DecorationAreaModifier(val type: DecorationAreaType): Modifier.Element

fun Modifier.decorationArea(type: DecorationAreaType) =
    this.then(DecorationAreaModifier(type))


