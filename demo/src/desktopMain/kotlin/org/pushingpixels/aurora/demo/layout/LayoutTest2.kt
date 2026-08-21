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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.FormLayout
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
            size = DpSize(300.dp, 150.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
        menuCommands = null

    ) {
        FormLayout(
            modifier = Modifier.fillMaxSize().padding(Paddings.Dlu9),
            encodedColumnSpecs = "right:pref, 2dlu, 60dlu:grow",
            encodedRowSpecs = "p, 6dlu, p, 10dlu, p",
        ) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Name",
                    enabled = true,
                ),
            ).project(Modifier.xy(1, 1))

            var textName by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textName,
                    placeholder = "User name",
                    onValueChange = { textName = it },
                    enabled = true
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true)
            ).project(Modifier.xy(3, 1))

            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Password",
                    enabled = true,
                ),
            ).project(Modifier.xy(1, 3))

            var textPassword by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textPassword,
                    placeholder = "Password",
                    onValueChange = { textPassword = it },
                    enabled = true
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true)
            ).project(Modifier.xy(3, 3))

            CommandButtonProjection(
                contentModel = Command(
                    text = "Login",
                    icon = radiance_menu(),
                    action = { println("Login!") }
                ),
                presentationModel = CommandButtonPresentationModel(
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                    iconDimension = DpSize(16.dp, 16.dp),
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
                    presentationState = CommandButtonPresentationState.MediumFitToIcon,
                )
            ).project(Modifier.xy(3, 5, CellConstraints.Alignment.Right, CellConstraints.Alignment.Center))
        }
    }
}
