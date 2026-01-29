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
package org.pushingpixels.aurora.demo.titlepane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.IconProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.demo.svg.material.chat_black_24dp
import org.pushingpixels.aurora.demo.svg.material.help_outline_black_24dp
import org.pushingpixels.aurora.demo.svg.material.person_black_24dp
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.window.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 600.dp)
    )

    AuroraWindow(
        skin = moderateSkin(),
        title = "",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraIntegrated(
            titlePaneHeight = 40.dp,
            titleControlButtonGroupHorizontalGravity = HorizontalGravity.Leading,
            titleControlButtonGroupVerticalGravity = VerticalGravity.Centered,
        ),
        onCloseRequest = ::exitApplication
    ) {
        ChatFrameContent()
    }
}

@Composable
fun AuroraWindowScope.ChatFrameContent(
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.TitlePane) {
            val titlePaneControlInsets = getTitlePaneControlInsets(layoutDirection, density)
            WindowDraggableArea(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .auroraBackground()
                    .padding(
                        start = titlePaneControlInsets.calculateStartPadding(layoutDirection),
                        end = titlePaneControlInsets.calculateEndPadding(layoutDirection)
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(20.dp))

                    CommandButtonProjection(
                        contentModel = Command(text = "New chat", action = { println("Start chat!") }),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Medium,
                            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding
                        )
                    ).project()

                    Spacer(modifier = Modifier.weight(1.0f))

                    IconProjection(
                        contentModel = IconContentModel(icon = chat_black_24dp()),
                        presentationModel = IconPresentationModel(iconDimension = DpSize(16.dp, 16.dp))
                    ).project()
                    AuroraWindowTitlePaneTitleText(
                        titleTextConfiguration = AuroraWindowTitlePaneConfigurations.DefaultTitlePaneTitleTextConfiguration(),
                        title = "Chat")

                    Spacer(modifier = Modifier.weight(1.0f))

                    TextFieldStringProjection(
                        contentModel = TextFieldStringContentModel(
                            value = " ",
                            onValueChange = { }
                        ),
                        presentationModel = TextFieldPresentationModel(
                            singleLine = true,
                            defaultMinSize = DpSize(width = 120.dp, height = 24.dp)
                        )
                    ).project()

                    Spacer(modifier = Modifier.width(12.dp))

                    IconProjection(
                        contentModel = IconContentModel(icon = help_outline_black_24dp()),
                        presentationModel = IconPresentationModel(iconDimension = DpSize(18.dp, 18.dp))
                    ).project()

                    Spacer(modifier = Modifier.width(12.dp))

                    IconProjection(
                        contentModel = IconContentModel(icon = person_black_24dp()),
                        presentationModel = IconPresentationModel(iconDimension = DpSize(16.dp, 16.dp))
                    ).project()

                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
            Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
        }
    }
}



