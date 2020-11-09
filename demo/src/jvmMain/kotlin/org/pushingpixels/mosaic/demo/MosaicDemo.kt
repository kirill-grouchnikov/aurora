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
package org.pushingpixels.mosaic.demo

import androidx.compose.desktop.Window
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.MosaicSkin
import org.pushingpixels.mosaic.components.MosaicCheckBox
import org.pushingpixels.mosaic.components.MosaicText
import org.pushingpixels.mosaic.components.MosaicToggleButton
import org.pushingpixels.mosaic.graphiteColorSchemes

fun main() {
    Window("Mosaic Demo", IntSize(500, 400)) {
        MosaicSkin {
            Box(
                modifier = Modifier,
                alignment = Alignment.TopStart
            ) {
                Canvas(
                    Modifier.matchParentSize()
                        .background(MosaicSkin.colorSchemes.background.backgroundColorStart)
                ) {}
                DemoContent()
            }
        }
    }
}

@Composable
fun DemoContent() {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MosaicCheckBox(
                    checked = false,
                    onCheckedChange = { println("Check 1! $it") }
                ) {
                    MosaicText(text = "Checkbox unselected")
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                MosaicCheckBox(
                    checked = true,
                    onCheckedChange = { it -> println("Check 2! $it") }
                ) {
                    MosaicText(text = "Checkbox selected")
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                MosaicCheckBox(
                    checked = true,
                    onCheckedChange = { println("Check 3! $it") }
                ) {
                    MosaicText(text = "Checkbox selected")
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                MosaicToggleButton(onClick = { println("Clicked 1!") }) {
                    MosaicText("Toggle button 1")
                }
            }
        }
        MosaicSkin(colorSchemes = graphiteColorSchemes()) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(MosaicSkin.colorSchemes.background.backgroundColorStart)
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MosaicCheckBox(
                        checked = true,
                        onCheckedChange = { println("Check 4! $it") }
                    ) {
                        MosaicText(text = "Checkbox selected")
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MosaicToggleButton(onClick = { println("Clicked 2!") }) {
                        MosaicText("Toggle button 2")
                    }
                }
            }
        }
    }
}



