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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.builder.Panel
import org.pushingpixels.aurora.layout.factories.Paddings
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
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
            size = DpSize(300.dp, 140.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        val textFieldMinSize = DpSize(width = 140.dp, height = TextFieldSizingConstants.MinHeight)

        Panel(
            modifier = Modifier.wrapContentSize(),
            padding = Paddings.Dlu4,
            encodedColumnSpecs = "pref, @lcgap, 60dlu, @rgap, max(40dlu;default)",
            encodedRowSpecs = "pref, @lg, pref, @lg, pref",
        ) {
            label("Title:", CellConstraints.xy(1, 1))

            var textTitle by rememberSaveable { mutableStateOf("") }
            component({ builderModifier ->
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = textTitle,
                        placeholder = "",
                        onValueChange = { textTitle = it },
                    ),
                    presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
                ).project(builderModifier)
            }, CellConstraints.xywh(3, 1, 3, 1))

            label("Price:", CellConstraints.xy(1, 3))

            var textPrice by rememberSaveable { mutableStateOf("") }
            component({ builderModifier ->
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = textPrice,
                        placeholder = "",
                        onValueChange = { textPrice = it },
                    ),
                    presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
                ).project(builderModifier)
            }, CellConstraints.xy(3, 3))

            label("Author:", CellConstraints.xy(1, 5))

            var textAuthor by rememberSaveable { mutableStateOf("") }
            component({ builderModifier ->
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = textAuthor,
                        placeholder = "",
                        onValueChange = { textAuthor = it },
                    ),
                    presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
                ).project(builderModifier)
            }, CellConstraints.xy(3, 5))

            component({ builderModifier ->
                CommandButtonProjection(
                    contentModel = Command(
                        text = "...",
                        action = { println("Go!") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        presentationState = CommandButtonPresentationState.Medium,
                    )
                ).project(builderModifier)
            }, CellConstraints.xy(5, 5))
        }
    }
}
