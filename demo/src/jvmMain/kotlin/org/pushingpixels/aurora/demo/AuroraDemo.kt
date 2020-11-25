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
package org.pushingpixels.aurora.demo

import androidx.compose.desktop.initCompose
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.components.*
import org.pushingpixels.aurora.skin.geminiSkin
import org.pushingpixels.aurora.skin.marinerSkin

fun main() {
    // TODO - this is needed until https://github.com/JetBrains/compose-jb/issues/111 is fixed
    initCompose()

    AuroraWindow(
        title = "Aurora Demo",
        skin = geminiSkin(),
        size = IntSize(500, 600)
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.TopStart
        ) {
            Canvas(
                Modifier.matchParentSize()
                    .auroraBackground()
            ) {}
            DemoContent()
        }
    }
}

@Composable
fun DemoArea(modifier: Modifier = Modifier, selected: Boolean = false) {
    // TODO - convert this to use ConstraintLayout
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(8.dp)
    ) {
        val enabled = remember { mutableStateOf(true) }
        AuroraCheckBox(
            selected = true,
            onSelectedChange = { enabled.value = !enabled.value }
        ) {
            AuroraText(text = "content enabled")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AuroraCheckBox(
                    selected = selected,
                    onSelectedChange = { println("Selected checkbox? $it") },
                    enabled = enabled.value
                ) {
                    AuroraText(text = "sample check")
                }
                AuroraRadioButton(
                    selected = selected,
                    onSelectedChange = { println("Selected radio? $it") },
                    enabled = enabled.value
                ) {
                    AuroraText(text = "sample radio")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AuroraButton(
                    enabled = enabled.value,
                    onClick = { println("Clicked!") }) {
                    AuroraText("sample button")
                }
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = selected,
                    onSelectedChange = { println("Selected toggle? $it") }) {
                    AuroraText("sample toggle")
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = false,
                    onSelectedChange = { println("Selected bold? $it") },
                    sides = ButtonSides(straightSides = setOf(Side.RIGHT))
                ) {
                    AuroraText("bold")
                }
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = true,
                    onSelectedChange = { println("Selected italic? $it") },
                    sides = ButtonSides(
                        straightSides = setOf(Side.LEFT, Side.RIGHT),
                        openSides = setOf(Side.LEFT, Side.RIGHT)
                    )
                ) {
                    AuroraText("italic")
                }
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = false,
                    onSelectedChange = { println("Selected under? $it") },
                    sides = ButtonSides(straightSides = setOf(Side.LEFT))
                ) {
                    AuroraText("under")
                }
            }
        }
    }
}

@Composable
fun DemoContent() {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        DecorationArea(decorationAreaType = DecorationAreaType.TOOLBAR) {
            DemoArea(selected = true)
        }
        DecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
            DemoArea(selected = false)
        }
        DecorationArea(decorationAreaType = DecorationAreaType.NONE) {
            DemoArea(selected = true)
        }
        DecorationArea(decorationAreaType = DecorationAreaType.FOOTER) {
            DemoArea(selected = false)
        }
    }
}



