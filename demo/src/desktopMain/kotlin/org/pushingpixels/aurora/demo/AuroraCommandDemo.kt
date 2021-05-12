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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.component.AuroraCheckBox
import org.pushingpixels.aurora.component.AuroraCommandButton
import org.pushingpixels.aurora.component.AuroraCommandButtonStrip
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        size = IntSize(660, 400),
        undecorated = true
    ) {
        DemoCommandContent()
    }
}

@Composable
fun DemoCommandRow(
    commandActionOnly: Command,
    commandSecondaryOnly: Command,
    commandActionAndSecondary: Command,
    presentationState: CommandButtonPresentationState
) {
    Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
        AuroraCommandButton(
            command = commandActionOnly,
            presentationModel = CommandButtonPresentationModel(presentationState = presentationState)
        )

        Spacer(modifier = Modifier.width(8.dp))

        AuroraCommandButton(
            command = commandSecondaryOnly,
            presentationModel = CommandButtonPresentationModel(presentationState = presentationState)
        )

        Spacer(modifier = Modifier.width(8.dp))

        AuroraCommandButton(
            command = commandActionAndSecondary,
            presentationModel = CommandButtonPresentationModel(
                presentationState = presentationState,
                textClick = TextClick.ACTION
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        AuroraCommandButton(
            command = commandActionAndSecondary,
            presentationModel = CommandButtonPresentationModel(
                presentationState = presentationState,
                textClick = TextClick.POPUP
            )
        )
    }
}

enum class CommandDemoAlignment {
    CENTER, LEFT, RIGHT, FILL
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
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.CENTER),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.CENTER
            }
        )
    val commandAlignLeft =
        Command(
            text = "Left",
            iconFactory = format_justify_left.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.LEFT),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.LEFT
            }
        )
    val commandAlignRight =
        Command(
            text = "Right",
            iconFactory = format_justify_right.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.RIGHT),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.RIGHT
            }
        )
    val commandAlignFill =
        Command(
            text = "Fill",
            iconFactory = format_justify_fill.factory(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment.value == CommandDemoAlignment.FILL),
            onTriggerActionToggleSelectedChange = {
                if (it) alignment.value = CommandDemoAlignment.FILL
            }
        )

    AuroraCommandButtonStrip(
        commandGroup = CommandGroup(
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
    )
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
                        Command(
                            text = "Text only",
                            action = { println("Paste text only") })
                    )
                )
            ),
            isSecondaryEnabled = popupEnabled
        )

    AuroraCommandButtonStrip(
        commandGroup = CommandGroup(
            commands = listOf(
                commandCopy,
                commandCut,
                commandPaste
            )
        ),
        presentationModel = CommandStripPresentationModel(
            orientation = orientation,
            horizontalGapScaleFactor = horizontalGapScaleFactor,
            iconEnabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
            iconDisabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
            iconActiveFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT
        )
    )
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

    AuroraCommandButtonStrip(
        commandGroup = CommandGroup(
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
            iconEnabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
            iconDisabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
            iconActiveFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT
        )
    )
}

@Composable
fun DemoCommandContent() {
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
                    ),
                    CommandGroup(
                        title = "Group 2",
                        commands = listOf(
                            Command(
                                text = "popup4",
                                iconFactory = computer.factory(),
                                action = { println("popup4 activated!") },
                                isActionEnabled = actionEnabled
                            ),
                            Command(
                                text = "popup5",
                                iconFactory = computer.factory(),
                                action = { println("popup5 activated!") },
                                isActionEnabled = actionEnabled,
                                secondaryContentModel = CommandMenuContentModel(
                                    group = CommandGroup(
                                        title = "Sub group",
                                        commands = listOf(
                                            Command(
                                                text = "popup11",
                                                iconFactory = computer.factory(),
                                                action = { println("popup11 activated!") },
                                                isActionEnabled = actionEnabled
                                            ),
                                            Command(
                                                text = "popup12",
                                                iconFactory = computer.factory(),
                                                action = { println("popup12 activated!") },
                                                isActionEnabled = actionEnabled
                                            ),
                                            Command(
                                                text = "popup13",
                                                iconFactory = computer.factory(),
                                                action = { println("popup13 activated!") },
                                                isActionEnabled = actionEnabled
                                            )
                                        )
                                    )
                                ),
                                isSecondaryEnabled = popupEnabled,
                            )
                        )
                    )
                )
            )
        )

    val alignment = remember { mutableStateOf(CommandDemoAlignment.CENTER) }
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
                orientation = StripOrientation.VERTICAL,
                alignment = alignment,
                horizontalGapScaleFactor = 0.7f
            )
        }
        Box(modifier = Modifier.padding(8.dp)) {
            CommandDemoStyleStrip(
                enabled = actionEnabled,
                orientation = StripOrientation.VERTICAL,
                style = style,
                horizontalGapScaleFactor = 0.7f
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
                AuroraCheckBox(contentModel = SelectorContentModel(
                    text = "action enabled",
                    selected = actionEnabled,
                    onTriggerSelectedChange = { actionEnabled = !actionEnabled }
                ))
                Spacer(modifier = Modifier.width(8.dp))
                AuroraCheckBox(contentModel = SelectorContentModel(
                    text = "popup enabled",
                    selected = popupEnabled,
                    onTriggerSelectedChange = { popupEnabled = !popupEnabled }
                ))
            }

            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
                AuroraCommandButton(command = commandActionOnlyNoIcon)
                Spacer(modifier = Modifier.width(8.dp))
                AuroraCommandButton(command = commandActionToggle)
                Spacer(modifier = Modifier.width(8.dp))
                CommandDemoJustifyStrip(
                    enabled = actionEnabled,
                    orientation = StripOrientation.HORIZONTAL,
                    alignment = alignment,
                    horizontalGapScaleFactor = CommandStripPresentationModel.DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandDemoStyleStrip(
                    enabled = actionEnabled,
                    orientation = StripOrientation.HORIZONTAL,
                    style = style,
                    horizontalGapScaleFactor = CommandStripPresentationModel.DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandDemoEditStrip(
                    actionEnabled = actionEnabled,
                    popupEnabled = popupEnabled,
                    orientation = StripOrientation.HORIZONTAL,
                    horizontalGapScaleFactor = CommandStripPresentationModel.DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
                )
            }

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.SMALL
            )

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.MEDIUM
            )

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.TILE
            )

            DemoCommandRow(
                commandActionOnly,
                commandSecondaryOnly,
                commandActionAndSecondary,
                CommandButtonPresentationState.BIG
            )
        }
    }
}



