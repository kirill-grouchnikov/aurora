/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo.ribbon

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.demo.AuroraSkinSwitcher
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 480.dp)
    )
    var skin by remember { mutableStateOf(nebulaSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }
    val builder = RibbonBuilder(resourceBundle)

    AuroraWindow(
        skin = skin,
        title = "Aurora Ribbon Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        Column {
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
                Row(modifier = Modifier.fillMaxWidth().auroraBackground().padding(12.dp)) {
                    builder.getApplicationMenuCommandButtonProjection().project()
                }
            }
            Spacer(Modifier.weight(weight = 1.0f, fill = true))
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                AuroraSkinSwitcher({ skin = it }, PopupPlacementStrategy.Upward.HAlignStart)
            }
        }
    }

}



