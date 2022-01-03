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
import org.pushingpixels.aurora.component.AuroraBreadcrumbBar
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(400.dp, 200.dp)
    )
    val skin = mutableStateOf(marinerSkin())

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
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
    val icons = arrayOf(
        accessories_text_editor(),
        computer(),
        drive_harddisk(),
        emblem_system(),
        font_x_generic(),
        help_browser(),
        media_floppy(),
        preferences_desktop_locale_2(),
        user_home()
    )
    val commands = icons.map {
        Command(
            text = "sample",
            icon = it,
            action = { println("Activated!") }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Toolbar) {
            AuroraBreadcrumbBar(
                commands = commands,
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
                AuroraSkinSwitcher(auroraSkinDefinition = auroraSkinDefinition)
            }
        }
    }
}





