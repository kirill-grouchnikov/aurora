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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandActionPreview
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

@ExperimentalUnitApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )
    val skin = mutableStateOf(marinerSkin())
    val resourceBundle = derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora skeleton",
        state = state,
        undecorated = true,
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = resourceBundle.value.getString("Menu.file"),
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = resourceBundle.value.getString("Menu.file.new"),
                                    action = { println("New file!") }),
                                Command(
                                    text = resourceBundle.value.getString("Menu.file.open"),
                                    action = { println("Open file!") }),
                                Command(
                                    text = resourceBundle.value.getString("Menu.file.save"),
                                    action = { println("Save file!") })
                            )
                        )
                    )
                ),
                Command(
                    text = resourceBundle.value.getString("Menu.edit"),
                    action = { println("Edit activated!") }),
                Command(
                    text = resourceBundle.value.getString("Menu.source"),
                    action = { println("Source activated!") }),
                Command(
                    text = resourceBundle.value.getString("Menu.refactor"),
                    action = { println("Refactor activated!") }),
                Command(
                    text = resourceBundle.value.getString("Menu.navigate"),
                    action = { println("Navigate activated!") }),
                Command(
                    text = resourceBundle.value.getString("Menu.search"),
                    action = { println("Search activated!") }),
                Command(
                    text = resourceBundle.value.getString("Menu.project"),
                    action = { println("Project activated!") })
            )
        )
    ) {
        DemoSkeletonContent(skin, resourceBundle)
    }
}


@Composable
fun DemoSkeletonFooter(
    modifier: Modifier = Modifier,
    auroraSkinDefinition: MutableState<AuroraSkinDefinition>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Spacer(modifier.weight(weight = 1.0f, fill = true))
        AuroraSkinSwitcher(auroraSkinDefinition, PopupPlacementStrategy.Upward)
    }
}

@ExperimentalUnitApi
@Composable
fun AuroraWindowScope.DemoSkeletonContent(
    auroraSkinDefinition: MutableState<AuroraSkinDefinition>,
    resourceBundle: State<ResourceBundle>
) {
    val contentEnabled = remember { mutableStateOf(true) }
    val alignment = remember { mutableStateOf(DemoAlignment.Center) }

    val style = DemoStyle(
        bold = remember { mutableStateOf(true) },
        italic = remember { mutableStateOf(true) },
        underline = remember { mutableStateOf(false) },
        strikethrough = remember { mutableStateOf(false) },
    )

    val alignmentCommands = CommandGroup(
        commands = listOf(
            Command(
                text = resourceBundle.value.getString("Justify.center"),
                icon = format_justify_center(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Center),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Center
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: Command) {
                        println("Center justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: Command) {
                        println("Center justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.value.getString("Justify.left"),
                icon = format_justify_left(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Left),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Left
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: Command) {
                        println("Left justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: Command) {
                        println("Left justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.value.getString("Justify.right"),
                icon = format_justify_right(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Right),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Right
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: Command) {
                        println("Right justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: Command) {
                        println("Right justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.value.getString("Justify.fill"),
                icon = format_justify_fill(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Fill),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Fill
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: Command) {
                        println("Fill justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: Command) {
                        println("Fill justify preview canceled!")
                    }
                }
            )
        )
    )

    val styleCommands = CommandGroup(
        commands = listOf(
            Command(
                text = resourceBundle.value.getString("FontStyle.bold.title"),
                icon = format_text_bold(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.bold.value,
                onTriggerActionToggleSelectedChange = {
                    style.bold.value = it
                    println("Selected bold? $it")
                }
            ),
            Command(
                text = resourceBundle.value.getString("FontStyle.italic.title"),
                icon = format_text_italic(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.italic.value,
                onTriggerActionToggleSelectedChange = {
                    style.italic.value = it
                    println("Selected italic? $it")
                }
            ),
            Command(
                text = resourceBundle.value.getString("FontStyle.underline.title"),
                icon = format_text_underline(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.underline.value,
                onTriggerActionToggleSelectedChange = {
                    style.underline.value = it
                    println("Selected underline? $it")
                }
            ),
            Command(
                text = resourceBundle.value.getString("FontStyle.strikethrough.title"),
                icon = format_text_strikethrough(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.strikethrough.value,
                onTriggerActionToggleSelectedChange = {
                    style.strikethrough.value = it
                    println("Selected strikethrough? $it")
                }
            )
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Toolbar) {
            DemoToolbar(
                alignmentCommands = alignmentCommands,
                styleCommands = styleCommands,
                resourceBundle = resourceBundle,
                iconDimension = 20.dp
            )
        }
        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
            DemoSkeletonFooter(auroraSkinDefinition = auroraSkinDefinition)
        }
    }
}



