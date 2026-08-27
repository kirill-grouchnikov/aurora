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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.derivedStateOf
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
import org.pushingpixels.aurora.demo.AuroraLocaleSwitcher
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.layout.builder.ButtonBar
import org.pushingpixels.aurora.layout.builder.DefaultForm
import org.pushingpixels.aurora.layout.factories.Paddings
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora FormLayout Demo",
        state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(300.dp, 180.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        val textFieldMinSize = DpSize(width = 140.dp, height = TextFieldSizingConstants.MinHeight)

        DefaultForm(
            modifier = Modifier.fillMaxSize(),
            padding = Paddings.Dlu4,
            encodedColumnSpecs = "end:pref, @lcgap, 60dlu, @rgap, max(40dlu;default)",
            encodedRowSpecs = "",
            bundle = resourceBundle
        ) {
            var textTitle by rememberSaveable { mutableStateOf("") }
            appendI15d(
                resourceKey = "FormLayout.title",
                component = { builderModifier ->
                    TextFieldStringProjection(
                        contentModel = TextFieldStringContentModel(
                            value = textTitle,
                            placeholder = "",
                            onValueChange = { textTitle = it },
                        ),
                        presentationModel = TextFieldPresentationModel(
                            singleLine = true,
                            defaultMinSize = textFieldMinSize
                        )
                    ).project(builderModifier)
                },
                columnSpan = 3
            )

            var textPrice by rememberSaveable { mutableStateOf("") }
            appendI15d(
                resourceKey = "FormLayout.price",
                component = { builderModifier ->
                    TextFieldStringProjection(
                        contentModel = TextFieldStringContentModel(
                            value = textPrice,
                            placeholder = "",
                            onValueChange = { textPrice = it },
                        ),
                        presentationModel = TextFieldPresentationModel(
                            singleLine = true,
                            defaultMinSize = textFieldMinSize
                        )
                    ).project(builderModifier)
                })

            nextLine()

            var textAuthor by rememberSaveable { mutableStateOf("") }
            appendI15d(
                resourceKey = "FormLayout.author",
                component = { builderModifier ->
                    TextFieldStringProjection(
                        contentModel = TextFieldStringContentModel(
                            value = textAuthor,
                            placeholder = "",
                            onValueChange = { textAuthor = it },
                        ),
                        presentationModel = TextFieldPresentationModel(
                            singleLine = true,
                            defaultMinSize = textFieldMinSize
                        )
                    ).project(builderModifier)
                })

            append(component = { builderModifier ->
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
            })

            nextLine()

            // The locale switcher row spans all the columns, and pushes the switcher to the end edge.
            // Technically, it breaks the horizontal grid rhythm as it's not aligned to the last
            // column. Doesn't matter much here since it's not meant to be a part of the main form content.
            append(
                component = { builderModifier ->
                    ButtonBar(builderModifier, Paddings.createPaddingValues("6dlu, 0dlu, 0dlu, 0dlu")) {
                        glue()
                        fixed({ AuroraLocaleSwitcher(it, resourceBundle) })
                    }
                },
                columnSpan = 5
            )
        }
    }
}
