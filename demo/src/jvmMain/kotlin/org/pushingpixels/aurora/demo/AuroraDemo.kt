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
import org.pushingpixels.aurora.AuroraWindow
import org.pushingpixels.aurora.DecorationArea
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.components.AuroraCheckBox
import org.pushingpixels.aurora.components.AuroraText
import org.pushingpixels.aurora.components.AuroraToggleButton
import org.pushingpixels.aurora.components.auroraBackground
import org.pushingpixels.aurora.skin.autumnSkin

fun main() {
    // TODO - this is needed until https://github.com/JetBrains/compose-jb/issues/111 is fixed
    initCompose()

    AuroraWindow(
        title = "Aurora Demo",
        skin = autumnSkin(),
        size = IntSize(500, 400)
    ) {
        //ImageBitmap(100, 100)
        Box(
            modifier = Modifier,
            alignment = Alignment.TopStart
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
fun DemoArea(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.Top, modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(8.dp)
    ) {
        val enabled = remember { mutableStateOf(true) }
        AuroraCheckBox(
            checked = true,
            onCheckedChange = { enabled.value = !enabled.value }
        ) {
            AuroraText(text = "content enabled")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            AuroraCheckBox(
                checked = true,
                onCheckedChange = { println("Check! $it") },
                enabled = enabled.value
            ) {
                AuroraText(text = "sample check")
            }
            AuroraToggleButton(enabled = enabled.value,
                onClick = { println("Clicked!") }) {
                AuroraText("sample toggle")
            }
        }
    }
}

@Composable
fun DemoContent() {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        DecorationArea(decorationAreaType = DecorationAreaType.TOOLBAR) {
            DemoArea()
        }
        DecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
            DemoArea()
        }
        DecorationArea(decorationAreaType = DecorationAreaType.NONE) {
            DemoArea()
        }
        DecorationArea(decorationAreaType = DecorationAreaType.FOOTER) {
            DemoArea()
        }
    }
}



