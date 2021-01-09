/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.component.AuroraButton
import org.pushingpixels.aurora.component.AuroraText
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.random.*
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.icon.AuroraThemedFollowTextIcon
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    AuroraWindow(
        title = "Aurora Demo",
        skin = businessSkin(),
        size = IntSize(800, 600)
    ) {
        IconDemoContent()
    }
}

@Composable
fun IconDemoArea() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AuroraButton {
                AuroraIcon(icon = media_floppy.of(16.dp, 16.dp))
                AuroraText("icon 1")
            }
            AuroraButton {
                AuroraIcon(icon = drive_harddisk.of(16.dp, 16.dp))
                AuroraText("icon 2")
            }
            AuroraButton {
                AuroraIcon(icon = help_browser.of(16.dp, 16.dp))
                AuroraText("icon 3")
            }
            AuroraButton {
                AuroraIcon(icon = system_search.of(16.dp, 16.dp))
                AuroraText("icon 4")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AuroraButton {
                AuroraThemedFollowTextIcon(icon = account_box_24px.of(16.dp, 16.dp))
                AuroraText("themed 1")
            }
            AuroraButton {
                AuroraThemedFollowTextIcon(icon = battery_full_24px.of(16.dp, 16.dp))
                AuroraText("themed 2")
            }
            AuroraButton {
                AuroraThemedFollowTextIcon(icon = keyboard_capslock_24px.of(16.dp, 16.dp))
                AuroraText("themed 3")
            }
            AuroraButton {
                AuroraThemedFollowTextIcon(icon = devices_other_24px.of(16.dp, 16.dp))
                AuroraText("themed 4")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AuroraButton {
                AuroraIcon(icon = pattern.of(64.dp, 64.dp))
                AuroraText("pattern")
            }
            AuroraButton {
                AuroraIcon(icon = text.of(64.dp, 64.dp))
                AuroraText("text")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AuroraButton {
                AuroraIcon(icon = marker.of(64.dp, 64.dp))
                AuroraText("themed 3")
            }
            AuroraButton {
                AuroraIcon(icon = kirill.of(64.dp, 64.dp))
                AuroraText("raster")
            }
        }
    }
}

@Composable
fun IconDemoContent() {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(vertical = 12.dp)) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
            IconDemoArea()
        }
    }
}



