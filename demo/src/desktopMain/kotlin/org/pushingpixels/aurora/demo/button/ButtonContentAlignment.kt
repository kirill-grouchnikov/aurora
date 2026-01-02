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
package org.pushingpixels.aurora.demo.button

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.edit_paste
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(460.dp, 224.dp)
    )
    val resourceBundle = derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        ButtonContentAlignmentContent(resourceBundle)
    }
}

@Composable
fun DemoContentAlignmentRow(
    command: Command,
    horizontalAlignment: HorizontalAlignment
) {
    Column(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LabelProjection(contentModel = LabelContentModel(text = horizontalAlignment.name)).project(
                modifier = Modifier.width(80.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            CommandButtonProjection(
                contentModel = command,
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Small,
                    horizontalAlignment = horizontalAlignment
                )
            ).project(modifier = Modifier.width(40.dp))

            Spacer(modifier = Modifier.width(8.dp))

            CommandButtonProjection(
                contentModel = command,
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium,
                    horizontalAlignment = horizontalAlignment
                )
            ).project(modifier = Modifier.width(100.dp))

            Spacer(modifier = Modifier.width(8.dp))

            CommandButtonProjection(
                contentModel = command,
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Tile,
                    horizontalAlignment = horizontalAlignment
                )
            ).project(modifier = Modifier.width(180.dp))
        }
    }
}

@Composable
fun AuroraApplicationScope.ButtonContentAlignmentContent(
    resourceBundle: State<ResourceBundle>
) {
    val command =
        Command(
            text = resourceBundle.value.getString("Edit.paste.text"),
            extraText = resourceBundle.value.getString("Edit.paste.textExtra"),
            icon = edit_paste(),
            action = { println("Paste activated!") },
        )

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        DemoContentAlignmentRow(
            command,
            HorizontalAlignment.Leading
        )

        DemoContentAlignmentRow(
            command,
            HorizontalAlignment.Center
        )

        DemoContentAlignmentRow(
            command,
            HorizontalAlignment.Trailing
        )
    }
}



