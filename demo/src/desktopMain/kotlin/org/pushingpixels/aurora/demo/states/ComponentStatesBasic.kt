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
package org.pushingpixels.aurora.demo.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.ceruleanSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

@ExperimentalComposeUiApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(260.dp, 92.dp)
    )

    AuroraWindow(
        skin = ceruleanSkin(),
        title = "States",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommandButtonProjection(
                contentModel = Command(
                    text = "selected",
                    action = {},
                    isActionToggle = true,
                    isActionToggleSelected = true
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium
                )
            ).project(
                modifier = Modifier.weight(1.0f)
            )
            CommandButtonProjection(
                contentModel = Command(
                    text = "enabled",
                    action = {}
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium
                )
            ).project(
                modifier = Modifier.weight(1.0f)
            )
            CommandButtonProjection(
                contentModel = Command(
                    text = "disabled",
                    action = {},
                    isActionEnabled = false
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium
                )
            ).project(
                modifier = Modifier.weight(1.0f)
            )
        }
    }
}

