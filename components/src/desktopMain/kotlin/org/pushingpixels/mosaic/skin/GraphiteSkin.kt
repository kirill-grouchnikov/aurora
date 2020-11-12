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
import org.pushingpixels.mosaic.ColorSchemes
import org.pushingpixels.mosaic.colorscheme.BaseColorScheme
import org.pushingpixels.mosaic.colorscheme.MosaicColorSchemeBundle

private fun graphiteCanvas() =
    BaseColorScheme(
        displayName = "Graphite canvas",
        background = Color(0xFF595959),
        foreground = Color(0xFFB4B4B4)
    )

private fun graphiteEnabled() =
    BaseColorScheme(
        displayName = "Graphite enabled",
        background = Color(0xFF3F3F3F),
        foreground = Color(0xFFC4C4C4)
    )

private fun graphiteActive() =
    BaseColorScheme(
        displayName = "Graphite active",
        background = Color(0xFFBCC0C5),
        foreground = Color(0xFF1B2025)
    )

fun graphiteColorSchemes() = ColorSchemes(
    canvas = graphiteCanvas(),
    enabled = graphiteEnabled(),
    selected = graphiteActive()
)

fun graphiteColorSchemeBundle(): MosaicColorSchemeBundle {
    return MosaicColorSchemeBundle(graphiteActive(), graphiteEnabled(), graphiteEnabled())
}

