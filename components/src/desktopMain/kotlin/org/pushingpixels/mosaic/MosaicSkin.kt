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

import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableContract
import androidx.compose.runtime.Providers
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.MenuBar
import org.pushingpixels.mosaic.colorscheme.MosaicSkinColors
import java.awt.image.BufferedImage

object MosaicSkin {
    @Composable
    @ComposableContract(readonly = true)
    val decorationArea: DecorationArea
        get() = AmbientDecorationArea.current

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

data class MosaicSkinDefinition(
    override val displayName: String,
    val colors: MosaicSkinColors,
    val shapes: ButtonShaper,
    val painters: Painters
) : MosaicTrait

fun MosaicWindow(
    title: String,
    skin: MosaicSkinDefinition,
    size: IntSize,
    location: IntOffset = IntOffset.Zero,
    centered: Boolean = true,
    icon: BufferedImage? = null,
    menuBar: MenuBar? = null,
    undecorated: Boolean = false,
    events: WindowEvents = WindowEvents(),
    onDismissEvent: (() -> Unit)? = null,
    content: @Composable () -> Unit = emptyContent()
) = Window(
    title = title,
    size = size,
    location = location,
    centered = centered,
    icon = icon,
    menuBar = menuBar,
    undecorated = undecorated,
    events = events,
    onDismissEvent = onDismissEvent
) {
    MosaicSkin(
        decorationArea = DecorationArea(DecorationAreaType.NONE),
        colors = skin.colors,
        shapes = skin.shapes,
        painters = skin.painters,
        animationConfig = MosaicSkin.animationConfig,
        content = content
    )
}

@Composable
private fun MosaicSkin(
    decorationArea: DecorationArea = MosaicSkin.decorationArea,
    colors: MosaicSkinColors = MosaicSkin.colors,
    shapes: ButtonShaper = MosaicSkin.shapes,
    painters: Painters = MosaicSkin.painters,
    animationConfig: AnimationConfig = MosaicSkin.animationConfig,
    content: @Composable () -> Unit
) {
    Providers(
        AmbientDecorationArea provides decorationArea,
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

