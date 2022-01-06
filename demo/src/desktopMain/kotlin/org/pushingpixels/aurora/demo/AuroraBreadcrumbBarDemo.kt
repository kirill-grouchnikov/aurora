/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.delay
import org.pushingpixels.aurora.component.AuroraBreadcrumbBar
import org.pushingpixels.aurora.component.model.BreadcrumbBarContentProvider
import org.pushingpixels.aurora.component.model.BreadcrumbBarPresentationModel
import org.pushingpixels.aurora.component.model.BreadcrumbItem
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.io.InputStream

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(400.dp, 200.dp)
    )
    val skin = mutableStateOf(marinerSkin())

    AuroraWindow(
        skin = skin,
        title = "Breadcrumb Bar Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        BreadcrumbContent(skin)
    }
}

@Composable
fun AuroraApplicationScope.BreadcrumbContent(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    val topContent = listOf(
        BreadcrumbItem("account", account_box_24px(), "account activated"),
        BreadcrumbItem("apps", apps_24px(), "apps activated"),
        BreadcrumbItem("backup", backup_24px(), "backup activated"),
        BreadcrumbItem("devices", devices_other_24px(), "devices activated"),
        BreadcrumbItem("help", help_24px(), "help activated"),
        BreadcrumbItem("keyboard", keyboard_capslock_24px(), "keyboard activated"),
        BreadcrumbItem("location", location_on_24px(), "location activated"),
        BreadcrumbItem("permissions", perm_device_information_24px(), "permission activated"),
        BreadcrumbItem("storage", storage_24px(), "storage activated")
    )
    val secondaryContent = listOf(
        BreadcrumbItem("bold", format_bold_black_24dp(), ""),
        BreadcrumbItem("italic", format_italic_black_24dp(), ""),
        BreadcrumbItem("strikethrough", format_strikethrough_black_24dp(), ""),
        BreadcrumbItem("underlined", format_underlined_black_24dp(), ""),
    )

    val contentProvider: BreadcrumbBarContentProvider<String> = object : BreadcrumbBarContentProvider<String> {
        override suspend fun getPathChoices(path: List<BreadcrumbItem<String>>): List<BreadcrumbItem<String>> {
            // Sample delay to emulate slow loading of content
            delay(500)
            if (path.isEmpty()) {
                return topContent
            }
            if (path.size == 1) {
                return secondaryContent
            }
            return emptyList()
        }

        override suspend fun getLeaves(path: List<BreadcrumbItem<String>>): List<BreadcrumbItem<String>> {
            // Sample delay to emulate slow loading of content
            delay(500)
            return emptyList()
        }

        override suspend fun getLeafContent(leaf: String): InputStream? {
            return null
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            AuroraBreadcrumbBar(
                contentProvider = contentProvider,
                presentationModel = BreadcrumbBarPresentationModel(
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                ),
                modifier = Modifier.fillMaxWidth().auroraBackground()
                    .padding(horizontal = 2.dp, vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1.0f, true))
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .auroraBackground()
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Spacer(modifier = Modifier.weight(1.0f, true))
                AuroraSkinSwitcher(
                    auroraSkinDefinition = auroraSkinDefinition,
                    popupPlacementStrategy = PopupPlacementStrategy.Upward
                )
            }
        }
    }
}





