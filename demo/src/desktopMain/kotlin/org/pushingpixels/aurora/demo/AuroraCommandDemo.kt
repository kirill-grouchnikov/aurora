/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.AuroraSkinDefinition
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(660.dp, 400.dp)
    )
    val skin = mutableStateOf(marinerSkin())

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        iconFactory = radiance_menu.factory(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        DemoCommandContent(skin)
    }
}

@Composable
fun DemoCommandRow(
    commandActionOnly: Command,
    commandSecondaryOnly: Command,
    commandActionAndSecondary: Command,
    presentationState: CommandButtonPresentationState,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>
) {
    Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
        CommandButtonProjection(
            contentModel = commandActionOnly,
            presentationModel = CommandButtonPresentationModel(presentationState = presentationState)
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = commandSecondaryOnly,
            presentationModel = CommandButtonPresentationModel(presentationState = presentationState)
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = commandActionAndSecondary,
            presentationModel = CommandButtonPresentationModel(
                presentationState = presentationState,
                textClick = TextClick.Action
            )
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = commandActionAndSecondary,
            presentationModel = CommandButtonPresentationModel(
                presentationState = presentationState,
                textClick = TextClick.Popup
            ),
            overlays = overlays
        ).project()
    }
}

enum class CommandDemoAlignment {
    Center, Left, Right, Fill
}

class CommandDemoStyle(
    val bold: MutableState<Boolean>,
    val italic: MutableState<Boolean>,
    val underline: MutableState<Boolean>,
    val strikethrough: MutableState<Boolean>
)

@Composable
fun CommandDemoJustifyStrip(
    enabled: Boolean,
    orientation: StripOrientation,
    alignment: MutableState<CommandDemoAlignment>,
    horizontalGapScaleFactor: Float
) {
    val commandAlignCenter =
        Command(
            text = "Center",
            iconFactory = format_justify_center.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.Center),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.Center
            }
        )
    val commandAlignLeft =
        Command(
            text = "Left",
            iconFactory = format_justify_left.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.Left),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.Left
            }
        )
    val commandAlignRight =
        Command(
            text = "Right",
            iconFactory = format_justify_right.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.Right),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.Right
            }
        )
    val commandAlignFill =
        Command(
            text = "Fill",
            iconFactory = format_justify_fill.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.Fill),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.Fill
            }
        )

    CommandButtonStripProjection(
        contentModel = CommandGroup(
            commands = listOf(
                commandAlignCenter,
                commandAlignLeft,
                commandAlignRight,
                commandAlignFill
            )
        ),
        presentationModel = CommandStripPresentationModel(
            orientation = orientation,
            horizontalGapScaleFactor = horizontalGapScaleFactor
        )
    ).project()
}

@Composable
fun CommandDemoEditStrip(
    actionEnabled: Boolean,
    popupEnabled: Boolean,
    orientation: StripOrientation,
    horizontalGapScaleFactor: Float
) {
    val commandCut =
        Command(
            text = "Cut",
            iconFactory = content_cut_black_24dp.factory(),
            isActionEnabled = actionEnabled,
            action = { println("Cut!") }

        )
    val commandCopy =
        Command(
            text = "Copy",
            iconFactory = content_copy_black_24dp.factory(),
            isActionEnabled = actionEnabled,
            action = { println("Copy!") }

        )

    var togglePasteText by remember { mutableStateOf(false) }
    val commandPasteTextOnly = Command(
        text = "Text only",
        action = { println("Paste text only") },
        isActionToggle = true,
        isActionToggleSelected = togglePasteText,
        onTriggerActionToggleSelectedChange = {
            println("Selected toggle paste text? $it")
            togglePasteText = it
        }
    )

    val commandPaste =
        Command(
            text = "Paste",
            iconFactory = content_paste_black_24dp.factory(),
            isActionEnabled = actionEnabled,
            action = { println("Paste!") },
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(
                    commands = listOf(
                        Command(
                            text = "Keep Formatting",
                            action = { println("Paste with keep formatting") }),
                        Command(
                            text = "Merge Formatting",
                            action = { println("Paste with merge formatting") }),
                        commandPasteTextOnly,
                    )
                ),
                panelContentModel = getQuickStylesContentModel()
            ),
            isSecondaryEnabled = popupEnabled
        )

    CommandButtonStripProjection(
        contentModel = CommandGroup(
            commands = listOf(
                commandCopy,
                commandCut,
                commandPaste
            )
        ),
        presentationModel = CommandStripPresentationModel(
            orientation = orientation,
            horizontalGapScaleFactor = horizontalGapScaleFactor,
            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
        ),
        overlays = mapOf(
            commandPasteTextOnly to CommandButtonPresentationModel.Overlay(
                toDismissPopupsOnActivation = false
            ),
            commandPaste to CommandButtonPresentationModel.Overlay(
                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                    panelPresentationModel = CommandPanelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        showGroupLabels = true,
                        commandPresentationState = CommandButtonPresentationState.FitToIcon,
                        commandIconSize = 24.dp,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        maxColumns = 5,
                        maxRows = 3
                    )
                )
            )
        )
    ).project()
}

@Composable
fun CommandDemoStyleStrip(
    enabled: Boolean,
    orientation: StripOrientation,
    style: CommandDemoStyle,
    horizontalGapScaleFactor: Float
) {
    val commandBold =
        Command(
            text = "Bold",
            iconFactory = format_bold_black_24dp.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.bold.value,
            onTriggerActionToggleSelectedChange = {
                style.bold.value = it
                println("Selected bold? $it")
            }
        )
    val commandItalic =
        Command(
            text = "Italic",
            iconFactory = format_italic_black_24dp.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.italic.value,
            onTriggerActionToggleSelectedChange = {
                style.italic.value = it
                println("Selected italic? $it")
            }
        )
    val commandUnderline =
        Command(
            text = "Underline",
            iconFactory = format_underlined_black_24dp.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.underline.value,
            onTriggerActionToggleSelectedChange = {
                style.underline.value = it
                println("Selected underline? $it")
            }
        )
    val commandStrikethrough =
        Command(
            text = "Strikethrough",
            iconFactory = format_strikethrough_black_24dp.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.strikethrough.value,
            onTriggerActionToggleSelectedChange = {
                style.strikethrough.value = it
                println("Selected strikethrough? $it")
            }
        )

    CommandButtonStripProjection(
        contentModel = CommandGroup(
            commands = listOf(
                commandBold,
                commandItalic,
                commandUnderline,
                commandStrikethrough
            )
        ),
        presentationModel = CommandStripPresentationModel(
            orientation = orientation,
            horizontalGapScaleFactor = horizontalGapScaleFactor,
            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
        )
    ).project()
}

@Composable
fun DemoCommandContent(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    var selected by remember { mutableStateOf(false) }
    var actionEnabled by remember { mutableStateOf(true) }
    var popupEnabled by remember { mutableStateOf(true) }

    val commandActionOnly =
        Command(
            text = "Action!",
            extraText = "Extra action",
            iconFactory = accessories_text_editor.factory(),
            action = { println("Action activated!") },
            isActionEnabled = actionEnabled,
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: Command) {
                    println("Action preview activated!")
                }

                override fun onCommandPreviewCanceled(command: Command) {
                    println("Action preview canceled!")
                }
            }
        )

    val commandActionOnlyNoIcon =
        Command(
            text = "Action 2!",
            extraText = "Extra action 2",
            action = { println("Action 2 activated!") },
            isActionEnabled = actionEnabled,
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: Command) {
                    println("Action 2 preview activated!")
                }

                override fun onCommandPreviewCanceled(command: Command) {
                    println("Action 2 preview canceled!")
                }
            }
        )

    val commandActionToggle =
        Command(
            text = "Toggle",
            extraText = "Extra toggle",
            iconFactory = computer.factory(),
            isActionEnabled = actionEnabled,
            isActionToggle = true,
            isActionToggleSelected = selected,
            onTriggerActionToggleSelectedChange = {
                selected = it
                println("Toggle selected? $selected")
            }
        )

    val commandSecondaryOnly =
        Command(
            text = "Popup",
            extraText = "Extra popup",
            iconFactory = computer.factory(),
            isSecondaryEnabled = popupEnabled,
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    title = "Group",
                    commands = listOf(
                        Command(
                            text = "popup1",
                            iconFactory = computer.factory(),
                            action = { println("popup1 activated!") },
                            isActionEnabled = actionEnabled
                        ),
                        Command(
                            text = "popup2",
                            iconFactory = computer.factory(),
                            action = { println("popup2 activated!") },
                            isActionEnabled = actionEnabled
                        ),
                        Command(
                            text = "popup3",
                            iconFactory = computer.factory(),
                            action = { println("popup3 activated!") },
                            isActionEnabled = actionEnabled
                        )
                    )
                )
            )
        )

    val secondaryCommand1 = Command(
        text = "secondary 1",
        iconFactory = computer.factory(),
        action = { println("secondary 1 activated!") },
        isActionEnabled = actionEnabled
    )
    val secondaryCommand2 = Command(
        text = "secondary 2",
        iconFactory = computer.factory(),
        action = { println("secondary 2 activated!") },
        isActionEnabled = actionEnabled
    )
    val secondaryCommand3 = Command(
        text = "secondary 3",
        iconFactory = computer.factory(),
        action = { println("secondary 3 activated!") },
        isActionEnabled = actionEnabled
    )
    val secondaryCommand4 = Command(
        text = "secondary 4",
        iconFactory = computer.factory(),
        action = { println("secondary 4 activated!") },
        isActionEnabled = actionEnabled,
        secondaryContentModel = CommandMenuContentModel(
            group = CommandGroup(
                title = "Sub group",
                commands = listOf(
                    Command(
                        text = "secondary 4/1",
                        extraText = "extra text for 4/1",
                        iconFactory = computer.factory(),
                        action = { println("secondary 4/1 activated!") },
                        isActionEnabled = actionEnabled
                    ),
                    Command(
                        text = "secondary 4/2",
                        extraText = "extra text for 4/2",
                        iconFactory = computer.factory(),
                        action = { println("secondary 4/2 activated!") },
                        isActionEnabled = actionEnabled
                    ),
                    Command(
                        text = "secondary 4/3",
                        extraText = "extra text for 4/3",
                        iconFactory = computer.factory(),
                        action = { println("secondary 4/3 activated!") },
                        isActionEnabled = actionEnabled
                    )
                )
            )
        ),
        isSecondaryEnabled = popupEnabled
    )

    val secondaryCommand5 = Command(
        text = "secondary 5",
        iconFactory = computer.factory(),
        action = { println("secondary 5 activated!") },
        isActionEnabled = actionEnabled,
        secondaryContentModel = CommandMenuContentModel(
            group = CommandGroup(
                title = "Sub group",
                commands = listOf(
                    Command(
                        text = "secondary 5/1",
                        extraText = "extra text for 5/1",
                        iconFactory = computer.factory(),
                        action = { println("secondary 5/1 activated!") },
                        isActionEnabled = actionEnabled
                    ),
                    Command(
                        text = "secondary 5/2",
                        extraText = "extra text for 5/2",
                        iconFactory = computer.factory(),
                        action = { println("secondary 5/2 activated!") },
                        isActionEnabled = actionEnabled
                    ),
                    Command(
                        text = "secondary 5/3",
                        extraText = "extra text for 5/3",
                        iconFactory = computer.factory(),
                        action = { println("secondary 5/3 activated!") },
                        isActionEnabled = actionEnabled
                    )
                )
            )
        ),
        isSecondaryEnabled = popupEnabled
    )

    val commandActionAndSecondary =
        Command(
            text = "Both parts",
            extraText = "Extra both",
            iconFactory = computer.factory(),
            action = { println("Split activated!") },
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: Command) {
                    println("Both preview activated!")
                }

                override fun onCommandPreviewCanceled(command: Command) {
                    println("Both preview canceled!")
                }
            },
            isActionEnabled = actionEnabled,
            isSecondaryEnabled = popupEnabled,
            secondaryContentModel = CommandMenuContentModel(
                groups = listOf(
                    CommandGroup(
                        title = "Group 1",
                        commands = listOf(secondaryCommand1, secondaryCommand2, secondaryCommand3)
                    ),
                    CommandGroup(
                        title = "Group 2",
                        commands = listOf(secondaryCommand4, secondaryCommand5)
                    )
                )
            )
        )

    val overlays = hashMapOf<Command, CommandButtonPresentationModel.Overlay>()
    // Configure one of our secondary commands to use a different presentation state
    // for its popup menu content
    overlays[secondaryCommand5] = CommandButtonPresentationModel.Overlay(
        popupMenuPresentationModel = CommandPopupMenuPresentationModel(
            menuPresentationState = CommandButtonPresentationState.Tile
        )
    )

    val alignment = remember { mutableStateOf(CommandDemoAlignment.Center) }
    val style = CommandDemoStyle(
        bold = remember { mutableStateOf(false) },
        italic = remember { mutableStateOf(true) },
        underline = remember { mutableStateOf(false) },
        strikethrough = remember { mutableStateOf(false) },
    )

    Row(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Box(modifier = Modifier.padding(8.dp)) {
            CommandDemoJustifyStrip(
                enabled = actionEnabled,
                orientation = StripOrientation.Vertical,
                alignment = alignment,
                horizontalGapScaleFactor = 0.7f
            )
        }
        Box(modifier = Modifier.padding(8.dp)) {
            CommandDemoStyleStrip(
                enabled = actionEnabled,
                orientation = StripOrientation.Vertical,
                style = style,
                horizontalGapScaleFactor = 0.7f
            )
        }

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            val currentSkinDisplayName = AuroraSkin.displayName
            val auroraSkins = getAuroraSkins()
            val selectedSkinItem =
                remember { mutableStateOf(auroraSkins.first { it.first == currentSkinDisplayName }) }

            ComboBoxProjection(
                contentModel = ComboBoxContentModel(
                    items = auroraSkins,
                    selectedItem = selectedSkinItem.value,
                    onTriggerItemSelectedChange = {
                        selectedSkinItem.value = it
                        auroraSkinDefinition.value = it.second.invoke()
                    }
                ),
                presentationModel = ComboBoxPresentationModel(
                    displayConverter = { it.first }
                )
            ).project()

            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = "action enabled",
                    selected = actionEnabled,
                    onTriggerSelectedChange = { actionEnabled = !actionEnabled }
                )).project()
                Spacer(modifier = Modifier.width(8.dp))
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = "popup enabled",
                    selected = popupEnabled,
                    onTriggerSelectedChange = { popupEnabled = !popupEnabled }
                )).project()
            }

            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
                CommandButtonProjection(contentModel = commandActionOnlyNoIcon).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandButtonProjection(contentModel = commandActionToggle).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandDemoJustifyStrip(
                    enabled = actionEnabled,
                    orientation = StripOrientation.Horizontal,
                    alignment = alignment,
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandDemoStyleStrip(
                    enabled = actionEnabled,
                    orientation = StripOrientation.Horizontal,
                    style = style,
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandDemoEditStrip(
                    actionEnabled = actionEnabled,
                    popupEnabled = popupEnabled,
                    orientation = StripOrientation.Horizontal,
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
                )
            }

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.Small,
                overlays
            )

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.Medium,
                overlays
            )

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.Tile,
                overlays
            )

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.Big,
                overlays
            )
        }
    }
}



