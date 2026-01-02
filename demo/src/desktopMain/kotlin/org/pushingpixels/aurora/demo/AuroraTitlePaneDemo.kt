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
package org.pushingpixels.aurora.demo

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
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    var windowTitlePaneConfiguration by remember {
        mutableStateOf(AuroraWindowTitlePaneConfigurations.AuroraPlain())
    }

    println("Window title pane configuration $windowTitlePaneConfiguration")
    AuroraWindow(
        skin = skin,
        title = "Aurora skeleton",
        state = state,
        windowTitlePaneConfiguration = windowTitlePaneConfiguration,
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = resourceBundle.getString("Menu.file"),
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = resourceBundle.getString("Menu.file.new"),
                                    action = { println("New file!") }),
                                Command(
                                    text = resourceBundle.getString("Menu.file.open"),
                                    action = { println("Open file!") }),
                                Command(
                                    text = resourceBundle.getString("Menu.file.save"),
                                    action = { println("Save file!") })
                            )
                        )
                    )
                ),
                Command(
                    text = resourceBundle.getString("Menu.edit"),
                    action = { println("Edit activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.source"),
                    action = { println("Source activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.refactor"),
                    action = { println("Refactor activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.navigate"),
                    action = { println("Navigate activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.search"),
                    action = { println("Search activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.project"),
                    action = { println("Project activated!") })
            )
        )
    ) {
        DemoTitlePaneContent(resourceBundle,
            { skin = it },
            windowTitlePaneConfiguration, { windowTitlePaneConfiguration = it })
    }
}

private data class TitlePaneHorizontalConfiguration(
    val title: String,
    val textHorizontalGravity: HorizontalGravity,
    val controlButtonHorizontalGravity: HorizontalGravity,
    val iconHorizontalGravity: TitleIconHorizontalGravity
)

private val TitlePaneHorizontalConfigurations = listOf(
    TitlePaneHorizontalConfiguration(
        title = "Default",
        textHorizontalGravity = HorizontalGravity.Leading,
        controlButtonHorizontalGravity = HorizontalGravity.Trailing,
        iconHorizontalGravity = TitleIconHorizontalGravity.OppositeControlButtons
    ),
    TitlePaneHorizontalConfiguration(
        title = "Platform",
        textHorizontalGravity = HorizontalGravity.Platform,
        controlButtonHorizontalGravity = HorizontalGravity.Platform,
        iconHorizontalGravity = TitleIconHorizontalGravity.Platform
    ),
    TitlePaneHorizontalConfiguration(
        title = "Force macOS",
        textHorizontalGravity = HorizontalGravity.Centered,
        controlButtonHorizontalGravity = HorizontalGravity.Leading,
        iconHorizontalGravity = TitleIconHorizontalGravity.NextToTitle
    ),
    TitlePaneHorizontalConfiguration(
        title = "Force Windows",
        textHorizontalGravity = HorizontalGravity.Leading,
        controlButtonHorizontalGravity = HorizontalGravity.Trailing,
        iconHorizontalGravity = TitleIconHorizontalGravity.OppositeControlButtons
    ),
    TitlePaneHorizontalConfiguration(
        title = "Force Gnome",
        textHorizontalGravity = HorizontalGravity.Centered,
        controlButtonHorizontalGravity = HorizontalGravity.Trailing,
        iconHorizontalGravity = TitleIconHorizontalGravity.None
    ),
    TitlePaneHorizontalConfiguration(
        title = "Force KDE",
        textHorizontalGravity = HorizontalGravity.Centered,
        controlButtonHorizontalGravity = HorizontalGravity.Trailing,
        iconHorizontalGravity = TitleIconHorizontalGravity.OppositeControlButtons
    )
)


@Composable
private fun AuroraTitlePaneHorizontalConfigSelector(
    currentConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain,
    onConfigurationSelected: (AuroraWindowTitlePaneConfigurations.AuroraPlain) -> Unit,
    popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
) {
    val options = TitlePaneHorizontalConfigurations
    var selectedItem by remember { mutableStateOf(options[0]) }

    ComboBoxProjection(
        contentModel = ComboBoxContentModel(
            items = options,
            selectedItem = selectedItem,
            onTriggerItemSelectedChange = {
                selectedItem = it
                onConfigurationSelected.invoke(
                    currentConfiguration.copy(
                        titleTextHorizontalGravity = it.textHorizontalGravity,
                        titleControlButtonGroupHorizontalGravity = it.controlButtonHorizontalGravity,
                        titleIconHorizontalGravity = it.iconHorizontalGravity
                    )
                )
            }
        ),
        presentationModel = ComboBoxPresentationModel(
            displayConverter = { it.title },
            popupPlacementStrategy = popupPlacementStrategy
        )
    ).project()
}

@Composable
fun DemoTitlePaneFooter(
    modifier: Modifier = Modifier,
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain,
    onWindowTitlePaneConfigurationChange: (AuroraWindowTitlePaneConfigurations.AuroraPlain) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Spacer(Modifier.weight(weight = 1.0f, fill = true))
        AuroraTitlePaneHorizontalConfigSelector(
            windowTitlePaneConfiguration, onWindowTitlePaneConfigurationChange,
            PopupPlacementStrategy.Upward.HAlignStart
        )
        Spacer(Modifier.width(12.dp))
        AuroraSkinSwitcher(onSkinChange, PopupPlacementStrategy.Upward.HAlignStart)
    }
}

@Composable
fun AuroraWindowScope.DemoTitlePaneContent(
    resourceBundle: ResourceBundle,
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain,
    onWindowTitlePaneConfigurationChange: (AuroraWindowTitlePaneConfigurations.AuroraPlain) -> Unit,
) {
    var contentEnabled by remember { mutableStateOf(true) }
    var alignment by remember { mutableStateOf(DemoAlignment.Center) }
    var style by remember {
        mutableStateOf(
            DemoStyle(
                bold = true,
                italic = true,
                underline = false,
                strikethrough = false,
            )
        )
    }

    val alignmentCommands = CommandGroup(
        commands = listOf(
            Command(
                text = resourceBundle.getString("Justify.center"),
                icon = format_justify_center(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Center),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Center
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Center justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Center justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.getString("Justify.left"),
                icon = format_justify_left(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Left),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Left
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Left justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Left justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.getString("Justify.right"),
                icon = format_justify_right(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Right),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Right
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Right justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Right justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.getString("Justify.fill"),
                icon = format_justify_fill(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Fill),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Fill
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Fill justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Fill justify preview canceled!")
                    }
                }
            )
        )
    )

    val styleCommands = CommandGroup(
        commands = listOf(
            Command(
                text = resourceBundle.getString("FontStyle.bold.title"),
                icon = format_text_bold(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = style.bold,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(bold = it)
                    println("Selected bold? $it")
                }
            ),
            Command(
                text = resourceBundle.getString("FontStyle.italic.title"),
                icon = format_text_italic(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = style.italic,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(italic = it)
                    println("Selected italic? $it")
                }
            ),
            Command(
                text = resourceBundle.getString("FontStyle.underline.title"),
                icon = format_text_underline(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = style.underline,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(underline = it)
                    println("Selected underline? $it")
                }
            ),
            Command(
                text = resourceBundle.getString("FontStyle.strikethrough.title"),
                icon = format_text_strikethrough(),
                isActionEnabled = contentEnabled,
                isActionToggle = true,
                isActionToggleSelected = style.strikethrough,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(strikethrough = it)
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
                iconDimension = DpSize(20.dp, 20.dp),
            )
        }
        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
            DemoTitlePaneFooter(
                onSkinChange = onSkinChange,
                windowTitlePaneConfiguration = windowTitlePaneConfiguration,
                onWindowTitlePaneConfigurationChange = onWindowTitlePaneConfigurationChange
            )
        }
    }
}



