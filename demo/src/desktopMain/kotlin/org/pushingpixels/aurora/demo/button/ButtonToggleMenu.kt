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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.CommandDemoAlignment
import org.pushingpixels.aurora.demo.CommandDemoStyle
import org.pushingpixels.aurora.demo.svg.material.format_bold_black_24dp
import org.pushingpixels.aurora.demo.svg.material.format_italic_black_24dp
import org.pushingpixels.aurora.demo.svg.material.format_strikethrough_black_24dp
import org.pushingpixels.aurora.demo.svg.material.format_underlined_black_24dp
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.format_justify_center
import org.pushingpixels.aurora.demo.svg.tango.format_justify_fill
import org.pushingpixels.aurora.demo.svg.tango.format_justify_left
import org.pushingpixels.aurora.demo.svg.tango.format_justify_right
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
        size = DpSize(400.dp, 212.dp)
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
        ButtonToggleMenuContent(resourceBundle)
    }
}

@Composable
fun AuroraApplicationScope.ButtonToggleMenuContent(resourceBundle: State<ResourceBundle>) {
    var alignment by remember { mutableStateOf(CommandDemoAlignment.Center) }
    var style by remember {
        mutableStateOf(
            CommandDemoStyle(
                bold = false,
                italic = true,
                underline = false,
                strikethrough = false,
            )
        )
    }

    val commandBold =
        Command(
            text = resourceBundle.value.getString("FontStyle.bold.title"),
            icon = format_bold_black_24dp(),
            isActionToggle = true,
            isActionToggleSelected = style.bold,
            onTriggerActionToggleSelectedChange = {
                style = style.copy(bold = it)
                println("Selected bold? $it")
            }
        )
    val commandItalic =
        Command(
            text = resourceBundle.value.getString("FontStyle.italic.title"),
            icon = format_italic_black_24dp(),
            isActionToggle = true,
            isActionToggleSelected = style.italic,
            onTriggerActionToggleSelectedChange = {
                style = style.copy(italic = it)
                println("Selected italic? $it")
            }
        )
    val commandUnderline =
        Command(
            text = resourceBundle.value.getString("FontStyle.underline.title"),
            icon = format_underlined_black_24dp(),
            isActionToggle = true,
            isActionToggleSelected = style.underline,
            onTriggerActionToggleSelectedChange = {
                style = style.copy(underline = it)
                println("Selected underline? $it")
            }
        )
    val commandStrikethrough =
        Command(
            text = resourceBundle.value.getString("FontStyle.strikethrough.title"),
            icon = format_strikethrough_black_24dp(),
            isActionToggle = true,
            isActionToggleSelected = style.strikethrough,
            onTriggerActionToggleSelectedChange = {
                style = style.copy(strikethrough = it)
                println("Selected strikethrough? $it")
            }
        )

    val commandAlignCenter =
        Command(
            text = resourceBundle.value.getString("Justify.center"),
            icon = format_justify_center(),
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Center),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment = CommandDemoAlignment.Center
            }
        )
    val commandAlignLeft =
        Command(
            text = resourceBundle.value.getString("Justify.left"),
            icon = format_justify_left(),
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Left),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment = CommandDemoAlignment.Left
            }
        )
    val commandAlignRight =
        Command(
            text = resourceBundle.value.getString("Justify.right"),
            icon = format_justify_right(),
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Right),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment = CommandDemoAlignment.Right
            }
        )
    val commandAlignFill =
        Command(
            text = resourceBundle.value.getString("Justify.fill"),
            icon = format_justify_fill(),
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Fill),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment = CommandDemoAlignment.Fill
            }
        )

    Row(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CommandButtonProjection(
            contentModel = Command(
                text = "single",
                secondaryContentModel = CommandMenuContentModel(
                    CommandGroup(
                        commands = listOf(
                            commandAlignLeft,
                            commandAlignCenter,
                            commandAlignRight,
                            commandAlignFill
                        )
                    )
                )
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Medium
            )
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = Command(
                text = "multi",
                secondaryContentModel = CommandMenuContentModel(
                    CommandGroup(
                        commands = listOf(
                            commandBold,
                            commandItalic,
                            commandUnderline,
                            commandStrikethrough
                        )
                    )
                )
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Medium,
                toDismissPopupsOnActivation = false
            )
        ).project()

    }
}



