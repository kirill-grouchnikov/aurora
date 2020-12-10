/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.colorscheme

import androidx.compose.ui.graphics.Color

class AquaColorScheme : BaseLightColorScheme("Aqua") {
    override val foregroundColor = Color.Black
    override val ultraLightColor = Color(194, 224, 237)
    override val extraLightColor = Color(164, 227, 243)
    override val lightColor = Color(112, 206, 239)
    override val midColor = Color(32, 180, 226)
    override val darkColor = Color(44, 47, 140)
    override val ultraDarkColor = Color(30, 40, 100)
}

class CremeColorScheme : BaseLightColorScheme("Creme") {
    override val foregroundColor = Color.Black
    override val ultraLightColor = Color(254, 254, 252)
    override val extraLightColor = Color(238, 243, 230)
    override val lightColor = Color(235, 234, 225)
    override val midColor = Color(227, 228, 219)
    override val darkColor = Color(179, 182, 176)
    override val ultraDarkColor = Color(178, 168, 153)
}

class MetallicColorScheme : BaseLightColorScheme("Metallic") {
    override val foregroundColor = Color(15, 20, 25)
    override val ultraLightColor = Color(250, 252, 255)
    override val extraLightColor = Color(240, 245, 250)
    override val lightColor = Color(200, 210, 220)
    override val midColor = Color(180, 185, 190)
    override val darkColor = Color(80, 85, 90)
    override val ultraDarkColor = Color(32, 37, 42)
}

class OrangeColorScheme : BaseLightColorScheme("Orange") {
    override val foregroundColor = Color.Black
    override val ultraLightColor = Color(255, 250, 235)
    override val extraLightColor = Color(255, 220, 180)
    override val lightColor = Color(245, 200, 128)
    override val midColor = Color(240, 170, 50)
    override val darkColor = Color(229, 151, 0)
    override val ultraDarkColor = Color(180, 100, 0)
}

class PurpleColorScheme : BaseLightColorScheme("Purple") {
    override val foregroundColor = Color.Black
    override val ultraLightColor = Color(240, 220, 245)
    override val extraLightColor = Color(218, 209, 233)
    override val lightColor = Color(203, 175, 237)
    override val midColor = Color(201, 135, 226)
    override val darkColor = Color(140, 72, 170)
    override val ultraDarkColor = Color(94, 39, 114)
}

