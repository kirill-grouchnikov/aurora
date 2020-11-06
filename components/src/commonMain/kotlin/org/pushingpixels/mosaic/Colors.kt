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

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Immutable
class Colors(
    background: Color, foreground: Color, enabledBackground: Color,
    enabledForeground: Color, selectedBackground: Color,
    selectedForeground: Color
) {
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var foreground by mutableStateOf(foreground, structuralEqualityPolicy())
        internal set
    var enabledBackground by mutableStateOf(enabledBackground, structuralEqualityPolicy())
        internal set
    var enabledForeground by mutableStateOf(enabledForeground, structuralEqualityPolicy())
        internal set
    var selectedBackground by mutableStateOf(selectedBackground, structuralEqualityPolicy())
        internal set
    var selectedForeground by mutableStateOf(selectedForeground, structuralEqualityPolicy())
        internal set
}

fun businessColors() = Colors(
    background = Color(0xFFEFF1F3),
    foreground = Color(0xFF0E1318),
    enabledBackground = Color(0xFFBFC8D2),
    enabledForeground = Color(0xFF0E1318),
    selectedBackground = Color(0xFFF9E07D),
    selectedForeground = Color(0xFF00C8E8)
)

val AmbientColors = staticAmbientOf { businessColors() }