/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.material.account_box_24px
import org.pushingpixels.aurora.demo.svg.material.battery_full_24px
import org.pushingpixels.aurora.demo.svg.material.perm_device_information_24px
import org.pushingpixels.aurora.demo.svg.material.waves_24px
import org.pushingpixels.aurora.demo.svg.random.*
import org.pushingpixels.aurora.demo.svg.tango.drive_harddisk
import org.pushingpixels.aurora.demo.svg.tango.help_browser
import org.pushingpixels.aurora.demo.svg.tango.media_floppy
import org.pushingpixels.aurora.demo.svg.tango.x_office_document_template
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.utils.getContainerColorTokensFilter
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 600.dp)
    )
    val skin by remember { mutableStateOf(geminiSkin()) }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
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
                Box(modifier = Modifier.size(40.dp).paint(painter = media_floppy()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 1")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = drive_harddisk()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 2")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = help_browser()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 3")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = x_office_document_template()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 4")).project()
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = media_floppy(),
                        colorFilter = getContainerColorTokensFilter(
                            colorTokens = autumnSkin().colors.getMutedContainerTokens(DecorationAreaType.None))
                    )
                )
                LabelProjection(contentModel = LabelContentModel(text = "icon 1")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = drive_harddisk(),
                        colorFilter = getContainerColorTokensFilter(
                            colorTokens = nebulaAmethystSkin().colors.getMutedContainerTokens(DecorationAreaType.None))
                    )
                )
                LabelProjection(contentModel = LabelContentModel(text = "icon 2")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = help_browser(),
                        colorFilter = getContainerColorTokensFilter(
                            colorTokens = magellanSkin().colors.getMutedContainerTokens(DecorationAreaType.None))
                    )
                )
                LabelProjection(
                    contentModel =
                    LabelContentModel(text = "icon 3")
                ).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = x_office_document_template(),
                        colorFilter = getContainerColorTokensFilter(
                            colorTokens = twilightSkin().colors.getMutedContainerTokens(DecorationAreaType.None))
                    )
                )
                LabelProjection(
                    contentModel =
                    LabelContentModel(text = "icon 4")
                ).project()
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = account_box_24px()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 1")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = battery_full_24px()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 2")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = perm_device_information_24px()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 3")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(40.dp).paint(painter = waves_24px()))
                LabelProjection(contentModel = LabelContentModel(text = "icon 4")).project()
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = account_box_24px(),
                        colorFilter = ColorFilter.tint(color = Color(0xFF9C27B0))
                    )
                )
                LabelProjection(contentModel = LabelContentModel(text = "icon 1")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = battery_full_24px(),
                        colorFilter = ColorFilter.tint(color = Color(0xFFFF1744))
                    )
                )
                LabelProjection(contentModel = LabelContentModel(text = "icon 2")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = perm_device_information_24px(),
                        colorFilter = ColorFilter.tint(color = Color(0xFF4CAF50))
                    )
                )
                LabelProjection(contentModel = LabelContentModel(text = "icon 3")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(
                    modifier = Modifier.size(40.dp).paint(
                        painter = waves_24px(),
                        colorFilter = ColorFilter.tint(color = Color(0xFFFF6F00))
                    )
                )
                LabelProjection(contentModel = LabelContentModel(text = "icon 4")).project()
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = pattern()))
                LabelProjection(contentModel = LabelContentModel(text = "pattern")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = pattern2()))
                LabelProjection(contentModel = LabelContentModel(text = "pattern radial")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = text()))
                LabelProjection(contentModel = LabelContentModel(text = "text")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = text2()))
                LabelProjection(contentModel = LabelContentModel(text = "text gradient linear")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = text3()))
                LabelProjection(contentModel = LabelContentModel(text = "text gradient radial")).project()
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = marker()))
                LabelProjection(contentModel = LabelContentModel(text = "marker")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = kirill()))
                LabelProjection(contentModel = LabelContentModel(text = "raster")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = sample()))
                LabelProjection(contentModel = LabelContentModel(text = "gradient radial stroke")).project()
            }
            Column(modifier = Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.size(128.dp).paint(painter = face_kiss()))
                LabelProjection(contentModel = LabelContentModel(text = "gradient radial fill")).project()
            }
        }
    }
}

@Composable
fun IconDemoContent() {
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            IconDemoArea()
        }
    }
}



