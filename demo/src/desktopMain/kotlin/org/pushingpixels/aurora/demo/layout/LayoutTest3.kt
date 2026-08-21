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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.demo.AuroraLocaleSwitcher
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.FormLayout
import org.pushingpixels.aurora.layout.Sizes
import org.pushingpixels.aurora.layout.factories.Paddings
import org.pushingpixels.aurora.layout.util.LayoutStyle
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.theming.resolveAuroraDefaults
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.ResourceBundle

@Composable
private fun Separator(modifier: Modifier, label: String) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        // Resolve the default text style to get the default font size
        val resolvedTextStyle = resolveAuroraDefaults()
        // Create our own text style with bold weight
        val textStyle = TextStyle(
            fontSize = resolvedTextStyle.fontSize,
            fontWeight = FontWeight.Bold
        )

        LabelProjection(
            contentModel = LabelContentModel(text = label),
            presentationModel = LabelPresentationModel(textStyle = textStyle)
        ).project()

        val density = LocalDensity.current
        Spacer(modifier = Modifier.width((Sizes.DluX1.getPixelSize() / density.density).dp))

        HorizontalSeparatorProjection().project(modifier = Modifier.weight(1.0f, fill = true).padding(top = 2.dp))
    }
}

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
            size = DpSize(460.dp, 260.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        val textFieldMinSize = DpSize(width = 120.dp, height = TextFieldSizingConstants.MinHeight)

        FormLayout(
            modifier = Modifier.fillMaxSize().padding(Paddings.Dlu9),
            encodedColumnSpecs = "right:pref, 4dlu, pref:grow, 8dlu, right:pref, 4dlu, pref:grow",
            encodedRowSpecs = "p, 3dlu, p, 3dlu, p, 10dlu, p, 3dlu, p, 3dlu, p, 14dlu, p",
            colGroupIndices = arrayOf(intArrayOf(1, 5), intArrayOf(3, 7))
        ) {
            Separator(modifier = Modifier.xyw(col = 1, row = 1, colSpan = 7), label = "General")

            LabelProjection(
                contentModel = LabelContentModel(text = "Company"),
            ).project(Modifier.xy(1, 3))

            var textCompany by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textCompany,
                    placeholder = "",
                    onValueChange = { textCompany = it },
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
            ).project(Modifier.xyw(3, 3, 5))

            LabelProjection(
                contentModel = LabelContentModel(text = "Contact"),
            ).project(Modifier.xy(1, 5))

            var textContact by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textContact,
                    placeholder = "",
                    onValueChange = { textContact = it },
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
            ).project(Modifier.xyw(3, 5, 5))

            Separator(modifier = Modifier.xyw(col = 1, row = 7, colSpan = 7), label = "Propeller")

            LabelProjection(
                contentModel = LabelContentModel(text = "PTI [kW]"),
            ).project(Modifier.xy(1, 9))

            var textPTI by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textPTI,
                    placeholder = "",
                    onValueChange = { textPTI = it },
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
            ).project(Modifier.xyw(3, 9))

            LabelProjection(
                contentModel = LabelContentModel(text = "Power [kW]"),
            ).project(Modifier.xy(5, 9))

            var textPower by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textPower,
                    placeholder = "",
                    onValueChange = { textPower = it },
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
            ).project(Modifier.xyw(7, 9))

            LabelProjection(
                contentModel = LabelContentModel(text = "R [mm]"),
            ).project(Modifier.xy(1, 11))

            var textR by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textR,
                    placeholder = "",
                    onValueChange = { textR = it },
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
            ).project(Modifier.xyw(3, 11))

            LabelProjection(
                contentModel = LabelContentModel(text = "D [mm]"),
            ).project(Modifier.xy(5, 11))

            var textD by rememberSaveable { mutableStateOf("") }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = textD,
                    placeholder = "",
                    onValueChange = { textD = it },
                ),
                presentationModel = TextFieldPresentationModel(singleLine = true, defaultMinSize = textFieldMinSize)
            ).project(Modifier.xyw(7, 11))

            Row(modifier = Modifier.xyw(1, 13, 7, CellConstraints.Alignment.Right, CellConstraints.Alignment.Center)) {
                val density = LocalDensity.current
                val buttonGap = with (density) {
                    LayoutStyle.current.unrelatedComponentsPadX.getPixelSize().toDp()
                }

                AuroraLocaleSwitcher(resourceBundle)

                Spacer(modifier = Modifier.width(buttonGap))

                CommandButtonProjection(
                    contentModel = Command(
                        text = "Ignite",
                        action = { println("Ignite") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        minWidth = 72.dp,
                    )
                ).project()

                Spacer(modifier = Modifier.width(buttonGap))

                CommandButtonProjection(
                    contentModel = Command(
                        text = "Explode",
                        action = { println("Explode") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        minWidth = 72.dp,
                    )
                ).project()
            }
        }
    }
}
