/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.magellanSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

@Composable
private fun StateRow(
    label: String,
    rollover: Boolean = false,
    selected: Boolean = false,
    pressed: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth(0.6f).padding(end = 8.dp)) {
            LabelProjection(contentModel = LabelContentModel(text = label)).project(
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        val actionInteractionSource = remember { MutableInteractionSource() }
        CommandButtonProjection(
            contentModel = Command(
                text = "sample",
                action = {},
                isActionToggle = selected,
                isActionToggleSelected = selected
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Medium,
                contentPadding = PaddingValues(start = 20.dp, top = 3.dp, end = 20.dp, bottom = 4.dp)
            )
        ).project(
            modifier = Modifier.fillMaxWidth(1.0f),
            actionInteractionSource = actionInteractionSource
        )
        LaunchedEffect(Unit) {
            if (rollover) {
                actionInteractionSource.emit(HoverInteraction.Enter())
            }
            if (pressed) {
                actionInteractionSource.emit(PressInteraction.Press(Offset(5.0f, 5.0f)))
            }
        }
    }
}

@ExperimentalComposeUiApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(260.dp, 240.dp)
    )

    AuroraWindow(
        skin = magellanSkin(),
        title = "States",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StateRow("Regular")
            StateRow("Rollover", rollover = true)
            StateRow("Selected", selected = true)
            StateRow("Rollover selected", rollover = true, selected = true)
            StateRow("Pressed", pressed = true)
            StateRow("Pressed selected", pressed = true, selected = true)
        }
    }
}

