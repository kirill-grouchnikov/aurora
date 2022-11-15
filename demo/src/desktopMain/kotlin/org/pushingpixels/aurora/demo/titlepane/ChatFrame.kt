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
package org.pushingpixels.aurora.demo.titlepane

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.IconProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.demo.svg.material.ic_chat_black_24px
import org.pushingpixels.aurora.demo.svg.material.ic_help_outline_black_24px
import org.pushingpixels.aurora.demo.svg.material.ic_person_black_24px
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.*

@ExperimentalUnitApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 600.dp)
    )

    AuroraWindow(
        skin = moderateSkin(),
        title = "Aurora skeleton",
        state = state,
        windowConfiguration = AuroraWindowConfiguration(
            titlePaneKind = AuroraWindowTitlePaneKind.Aurora,
            titlePaneHeight = 40.dp,
            titleControlButtonGroupHorizontalGravity = HorizontalGravity.Leading,
            titleControlButtonGroupVerticalGravity = VerticalGravity.Centered,
            extendContentIntoTitlePane = true
        ),
        onCloseRequest = ::exitApplication
    ) {
        ChatFrameContent()
    }
}

@ExperimentalUnitApi
@Composable
fun AuroraWindowScope.ChatFrameContent(
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.TitlePane) {
            val titlePaneControlInsets = getTitlePaneControlInsets(layoutDirection, density)
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(
                        start = titlePaneControlInsets.calculateStartPadding(layoutDirection),
                        end = titlePaneControlInsets.calculateEndPadding(layoutDirection)
                    ),
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
                    contentModel = IconContentModel(icon = ic_chat_black_24px()),
                    presentationModel = IconPresentationModel(iconDimension = 16.dp)
                ).project()
                AuroraWindowTitlePaneTitleText(title = "Chat")

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
                    contentModel = IconContentModel(icon = ic_help_outline_black_24px()),
                    presentationModel = IconPresentationModel(iconDimension = 18.dp)
                ).project()

                Spacer(modifier = Modifier.width(12.dp))

                IconProjection(
                    contentModel = IconContentModel(icon = ic_person_black_24px()),
                    presentationModel = IconPresentationModel(iconDimension = 16.dp)
                ).project()

                Spacer(modifier = Modifier.width(2.dp))
            }
        }
        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
    }
}



