/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.demo.svg.random.kirill
import org.pushingpixels.aurora.demo.svg.random.marker
import org.pushingpixels.aurora.demo.svg.random.pattern
import org.pushingpixels.aurora.demo.svg.random.text
import org.pushingpixels.aurora.demo.svg.tango.drive_harddisk
import org.pushingpixels.aurora.demo.svg.tango.help_browser
import org.pushingpixels.aurora.demo.svg.tango.media_floppy
import org.pushingpixels.aurora.demo.svg.tango.system_search
import org.pushingpixels.aurora.icon.AuroraIcon
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
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = media_floppy.of(20.dp, 20.dp))
                BasicText("icon 1")
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = drive_harddisk.of(20.dp, 20.dp))
                BasicText("icon 2")
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = help_browser.of(20.dp, 20.dp))
                BasicText("icon 3")
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = system_search.of(20.dp, 20.dp))
                BasicText("icon 4")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = pattern.of(64.dp, 64.dp))
                BasicText("pattern")
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = text.of(64.dp, 64.dp))
                BasicText("text")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = marker.of(64.dp, 64.dp))
                BasicText("themed 3")
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                AuroraIcon(icon = kirill.of(64.dp, 64.dp))
                BasicText("raster")
            }
        }
    }
}

@Composable
fun IconDemoContent() {
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
            IconDemoArea()
        }
    }
}



