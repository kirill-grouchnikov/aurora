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
package org.pushingpixels.mosaic.skin

import androidx.compose.ui.graphics.Color
import org.pushingpixels.mosaic.ColorSchemeAssociationKind
import org.pushingpixels.mosaic.ColorSchemes
import org.pushingpixels.mosaic.ComponentState
import org.pushingpixels.mosaic.colorscheme.BaseColorScheme
import org.pushingpixels.mosaic.colorscheme.MosaicColorSchemeBundle


private fun businessCanvas() =
    BaseColorScheme(
        displayName = "Business canvas",
        background = Color(0xFFEFF1F3),
        foreground = Color(0xFF0E1318)
    )

private fun businessEnabled() =
    BaseColorScheme(
        displayName = "Business enabled",
//        background = Color.Red,
        backgroundStart = Color(0xFFE5EAEF),
        backgroundEnd = Color(0xFFBFC8D2),
        foreground = Color(0xFF0E1318)
    )

private fun businessActive() =
    BaseColorScheme(
        displayName = "Business active",
//        background = Color.Blue,
        backgroundStart = Color(0xFFF2F7FB),
        backgroundEnd = Color(0xFFD1DAE2),
        foreground = Color(0xFF505152)
    )

private fun businessSelected() =
    BaseColorScheme(
        displayName = "Business selected",
        background = Color(0xFFF9E07D),
        foreground = Color(0xFF00C8E8)
    )

fun businessColorSchemes() = ColorSchemes(
    canvas = businessCanvas(),
    enabled = businessEnabled(),
    selected = businessSelected()
)

fun businessColorSchemeBundle(): MosaicColorSchemeBundle {
    val activeScheme = businessActive()
    val enabledScheme = businessEnabled()

    val result = MosaicColorSchemeBundle(
        activeScheme, enabledScheme, enabledScheme
    )

    result.registerColorScheme(
        businessSelected(), ColorSchemeAssociationKind.FILL, ComponentState.SELECTED
    )

    return result
}

