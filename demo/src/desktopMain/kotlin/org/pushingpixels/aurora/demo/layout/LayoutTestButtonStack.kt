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
package org.pushingpixels.aurora.demo.layout

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.layout.builder.ButtonStack
import org.pushingpixels.aurora.layout.factories.Paddings
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

fun main() = auroraApplication {
    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora FormLayout Demo",
        state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(300.dp, 160.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        ButtonStack(modifier = Modifier.wrapContentSize(), padding = Paddings.Dialog) {
            button(text = "Start")
            unrelatedGap()
            button(text = "Pause")
            relatedGap()
            button(text = "Stop")
        }
    }
}
