/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.demo

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.icon.AuroraThemedFollowTextIcon
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import kotlin.system.exitProcess

fun main() {
    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        size = IntSize(660, 660),
        undecorated = true
    ) {
        DemoContent()
    }
}

data class Person(val firstName: String, val lastName: String)
enum class DemoAlignment {
    CENTER, LEFT, RIGHT, FILL
}

class DemoStyle(
    val bold: MutableState<Boolean>,
    val italic: MutableState<Boolean>,
    val underline: MutableState<Boolean>,
    val strikethrough: MutableState<Boolean>
)

@Composable
fun DemoProgress(enabled: Boolean) {
    var progress by remember { mutableStateOf(0.5f) }
    val animatedStateProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressConstants.ProgressAnimationSpec
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        AuroraCommandButton(
            command = Command(text = "",
                iconFactory = remove_circle_outline_24px.factory(),
                isActionEnabled = (progress > 0.0f),
                action = { progress -= 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SMALL,
                iconDimension = 14.dp,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.NEVER,
                iconDisabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
                iconEnabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
                iconActiveFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT
            )
        )

        AuroraDeterminateLinearProgress(progress = animatedStateProgress.value, enabled = enabled)

        AuroraCommandButton(
            command = Command(text = "",
                iconFactory = add_circle_outline_24px.factory(),
                isActionEnabled = (progress < 1.0f),
                action = { progress += 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SMALL,
                iconDimension = 14.dp,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.NEVER,
                iconDisabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
                iconEnabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
                iconActiveFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT
            )
        )
    }
}

@Composable
fun DemoMenuBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
    ) {
        AuroraCommandButton(
            command = Command(text = "File"),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                isMenu = true
            )
        )
        AuroraCommandButton(
            command = Command(text = "Edit"),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                isMenu = true
            )
        )
        AuroraCommandButton(
            command = Command(text = "View"),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                isMenu = true
            )
        )
        AuroraCommandButton(
            command = Command(text = "Tools"),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                isMenu = true
            )
        )
        AuroraCommandButton(
            command = Command(text = "Window"),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                isMenu = true
            )
        )
        AuroraCommandButton(
            command = Command(text = "Help"),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                isMenu = true
            )
        )
    }
}

@Composable
fun DemoToolbar(
    modifier: Modifier = Modifier,
    alignmentCommands: CommandGroup,
    styleCommands: CommandGroup
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        AuroraCommandButton(
            command = Command(
                text = "cut",
                iconFactory = edit_cut.factory(),
                action = { println("Cut!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                iconDimension = 20.dp
            )
        )
        AuroraCommandButton(
            command = Command(
                text = "copy",
                iconFactory = edit_copy.factory(),
                isActionEnabled = false,
                action = { println("Copy!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                iconDimension = 20.dp
            )
        )
        AuroraCommandButton(
            command = Command(
                text = "paste",
                iconFactory = edit_paste.factory(),
                action = { println("Paste!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SMALL,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                iconDimension = 20.dp
            )
        )
        AuroraCommandButton(
            command = Command(
                text = "select all",
                iconFactory = edit_select_all.factory(),
                action = { println("Select all!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SMALL,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                iconDimension = 20.dp
            )
        )
        AuroraCommandButton(
            command = Command(
                text = "delete",
                iconFactory = edit_delete.factory(),
                action = { println("Delete!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SMALL,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                iconDimension = 20.dp
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        AuroraVerticalSeparator(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.width(4.dp))

        AuroraCommandButtonStrip(
            commandGroup = alignmentCommands,
            presentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.HORIZONTAL,
                iconDimension = 20.dp
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        AuroraVerticalSeparator(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.width(4.dp))

        AuroraCommandButtonStrip(
            commandGroup = styleCommands,
            presentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.HORIZONTAL,
                iconDimension = 20.dp
            )
        )

        Spacer(modifier.weight(weight = 1.0f, fill = true))

        AuroraCommandButton(
            command = Command(text = "exit",
                iconFactory = process_stop.factory(),
                action = { exitProcess(0) }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SMALL,
                iconDimension = 20.dp
            )
        )
    }
}

@Composable
fun DemoFooter(
    modifier: Modifier = Modifier,
    alignmentCommands: CommandGroup
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Spacer(modifier.weight(weight = 1.0f, fill = true))

        AuroraCommandButtonStrip(
            commandGroup = alignmentCommands,
            presentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.HORIZONTAL,
                iconDimension = 14.dp
            )
        )
    }
}

@Composable
fun DemoArea(
    modifier: Modifier = Modifier, contentEnabled: MutableState<Boolean>,
    styleCommands: CommandGroup
) {
    // TODO - convert this to use ConstraintLayout when (if?) that is available for desktop
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(8.dp)
    ) {
        AuroraCheckBox(
            selected = contentEnabled.value,
            onTriggerSelectedChange = { contentEnabled.value = !contentEnabled.value }
        ) {
            AuroraText(text = "content enabled")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                var checkboxSelected by remember { mutableStateOf(true) }
                AuroraCheckBox(
                    selected = checkboxSelected,
                    onTriggerSelectedChange = {
                        println("Selected checkbox? $it")
                        checkboxSelected = it
                    },
                    enabled = contentEnabled.value
                ) {
                    AuroraText(text = "sample check")
                }

                Spacer(modifier = Modifier.width(8.dp))

                var radioButtonSelected by remember { mutableStateOf(true) }
                AuroraRadioButton(
                    selected = radioButtonSelected,
                    onTriggerSelectedChange = {
                        println("Selected radio? $it")
                        radioButtonSelected = it
                    },
                    enabled = contentEnabled.value
                ) {
                    AuroraText(text = "sample radio")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                var toggleButtonSelected by remember { mutableStateOf(true) }
                AuroraCommandButton(
                    command = Command(
                        text = "toggle",
                        iconFactory = computer.factory(),
                        isActionEnabled = contentEnabled.value,
                        isActionToggle = true,
                        isActionToggleSelected = toggleButtonSelected,
                        onTriggerActionToggleSelectedChange = {
                            println("Selected toggle? $it")
                            toggleButtonSelected = it
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        iconDimension = 20.dp,
                        // This is a full-color icon. Use original colors for enabled and active states,
                        // and color scheme based filtering for disabled states
                        iconDisabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
                        iconEnabledFilterStrategy = IconFilterStrategy.ORIGINAL,
                        iconActiveFilterStrategy = IconFilterStrategy.ORIGINAL,
                    )
                )
                AuroraCommandButton(
                    command = Command(
                        text = "never",
                        isActionEnabled = contentEnabled.value,
                        action = { println("Clicked!") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.NEVER
                    )
                )
                AuroraCommandButton(
                    command = Command(
                        text = "flat",
                        iconFactory = account_box_24px.factory(),
                        isActionEnabled = contentEnabled.value,
                        action = { println("Clicked!") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                        iconDimension = 20.dp
                    )
                )
                AuroraCommandButton(
                    command = Command(
                        text = "always",
                        iconFactory = keyboard_capslock_24px.factory(),
                        isActionEnabled = contentEnabled.value,
                        action = { println("Clicked!") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
                        iconDimension = 20.dp
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                AuroraCircularProgress(enabled = contentEnabled.value)

                Spacer(modifier = Modifier.width(8.dp))

                AuroraCircularProgress(
                    modifier = Modifier.size(14.dp),
                    enabled = contentEnabled.value
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                AuroraCommandButtonStrip(
                    commandGroup = styleCommands,
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.HORIZONTAL,
                        iconDimension = 20.dp
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                AuroraCommandButton(
                    command = Command(
                        text = "always",
                        iconFactory = star_black_48dp.factory(),
                        isActionEnabled = contentEnabled.value,
                        isActionToggle = true,
                        isActionToggleSelected = true,
                        onTriggerActionToggleSelectedChange = { println("Selected small? $it") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.SMALL,
                        iconDimension = 24.dp,
                        horizontalGapScaleFactor = 0.25f,
                        verticalGapScaleFactor = 0.25f
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                AuroraCommandButton(
                    command = Command(
                        text = "always",
                        iconFactory = star_black_48dp.factory(),
                        isActionEnabled = contentEnabled.value,
                        isActionToggle = true,
                        isActionToggleSelected = true,
                        onTriggerActionToggleSelectedChange = { println("Selected small? $it") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.SMALL,
                        iconDimension = 24.dp,
                        horizontalGapScaleFactor = 1.25f,
                        verticalGapScaleFactor = 1.25f
                    )
                )

                Spacer(modifier = Modifier.width(20.dp))

                val simpleComboItems = listOf("one", "two", "three")
                val simpleComboSelectedItem = remember { mutableStateOf(simpleComboItems[1]) }
                AuroraComboBox(
                    enabled = contentEnabled.value,
                    items = simpleComboItems,
                    selectedItem = simpleComboSelectedItem.value,
                    displayConverter = { it },
                    onTriggerItemSelectedChange = {
                        simpleComboSelectedItem.value = it
                        println("$it selected!")
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )

                Spacer(modifier = Modifier.width(8.dp))

                val personComboItems =
                    listOf(
                        Person("Bob", "Loblaw"),
                        Person("Paige", "Turner"),
                        Person("Donaldson", "Duck")
                    )
                val personComboSelectedItem = remember { mutableStateOf(personComboItems[0]) }
                AuroraComboBox(
                    enabled = contentEnabled.value,
                    items = personComboItems,
                    selectedItem = personComboSelectedItem.value,
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onTriggerItemSelectedChange = {
                        personComboSelectedItem.value = it
                        println("$it selected!")
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )
            }

            val snowComboItems =
                listOf(
                    Person("Jon", "Snow"),
                    Person("Arya", "Stark"),
                    Person("Mance", "Rayder"),
                    Person("Jeor", "Mormont")
                )
            val snowComboSelectedItem = remember { mutableStateOf(snowComboItems[1]) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AuroraComboBox(
                    enabled = contentEnabled.value,
                    popupPlacementStrategy = PopupPlacementStrategy.STARTWARD,
                    items = snowComboItems,
                    selectedItem = snowComboSelectedItem.value,
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onTriggerItemSelectedChange = {
                        snowComboSelectedItem.value = it
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )

                Spacer(modifier = Modifier.width(16.dp))

                AuroraComboBox(
                    enabled = contentEnabled.value,
                    popupPlacementStrategy = PopupPlacementStrategy.ENDWARD,
                    items = snowComboItems,
                    selectedItem = snowComboSelectedItem.value,
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onTriggerItemSelectedChange = {
                        snowComboSelectedItem.value = it
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AuroraComboBox(
                    enabled = contentEnabled.value,
                    popupPlacementStrategy = PopupPlacementStrategy.UPWARD,
                    items = snowComboItems,
                    selectedItem = snowComboSelectedItem.value,
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onTriggerItemSelectedChange = {
                        snowComboSelectedItem.value = it
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )

                Spacer(modifier = Modifier.width(16.dp))

                AuroraComboBox(
                    enabled = contentEnabled.value,
                    popupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
                    items = snowComboItems,
                    selectedItem = snowComboSelectedItem.value,
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onTriggerItemSelectedChange = {
                        snowComboSelectedItem.value = it
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )

                Spacer(modifier = Modifier.width(16.dp))

                AuroraComboBox(
                    enabled = contentEnabled.value,
                    popupPlacementStrategy = PopupPlacementStrategy.CENTERED_VERTICALLY,
                    items = snowComboItems,
                    selectedItem = snowComboSelectedItem.value,
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onTriggerItemSelectedChange = {
                        snowComboSelectedItem.value = it
                    },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AuroraIndeterminateLinearProgress(
                    enabled = contentEnabled.value
                )

                Spacer(modifier = Modifier.width(16.dp))

                DemoProgress(enabled = contentEnabled.value)
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                var sliderValue1 by remember { mutableStateOf(0.5f) }
                AuroraSlider(
                    value = sliderValue1,
                    valueRange = 0.0f.rangeTo(1.0f),
                    onTriggerValueChange = {
                        sliderValue1 = it
                        println("Slider $it")
                    },
                    onValueChangeEnd = { println("Slider change done!") },
                    enabled = contentEnabled.value
                )

                Spacer(modifier = Modifier.width(16.dp))

                var sliderValue2 by remember { mutableStateOf(50f) }
                AuroraSlider(
                    value = sliderValue2,
                    valueRange = 0.0f.rangeTo(100.0f),
                    onTriggerValueChange = {
                        sliderValue2 = it
                        println("Slider $it")
                    },
                    onValueChangeEnd = { println("Slider change done!") },
                    enabled = contentEnabled.value,
                    tickSteps = 9,
                    snapToTicks = true,
                    drawTicks = true,
                )
            }
        }
    }
}

@Composable
fun DemoContent() {
    var contentEnabled = remember { mutableStateOf(true) }
    val alignment = remember { mutableStateOf(DemoAlignment.CENTER) }

    val style = DemoStyle(
        bold = remember { mutableStateOf(false) },
        italic = remember { mutableStateOf(true) },
        underline = remember { mutableStateOf(false) },
        strikethrough = remember { mutableStateOf(false) },
    )

    val alignmentCommands = CommandGroup(
        commands = listOf(
            Command(
                text = "Center",
                iconFactory = format_justify_center.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.CENTER),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.CENTER
                }
            ),
            Command(
                text = "Left",
                iconFactory = format_justify_left.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.LEFT),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.LEFT
                }
            ),
            Command(
                text = "Right",
                iconFactory = format_justify_right.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.RIGHT),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.RIGHT
                }
            ),
            Command(
                text = "Fill",
                iconFactory = format_justify_fill.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.FILL),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.FILL
                }
            )
        )
    )

    val styleCommands = CommandGroup(
        commands = listOf(
            Command(
                text = "Bold",
                iconFactory = format_text_bold.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.bold.value,
                onTriggerActionToggleSelectedChange = {
                    style.bold.value = it
                    println("Selected bold? $it")
                }
            ),
            Command(
                text = "Italic",
                iconFactory = format_text_italic.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.italic.value,
                onTriggerActionToggleSelectedChange = {
                    style.italic.value = it
                    println("Selected italic? $it")
                }
            ),
            Command(
                text = "Underline",
                iconFactory = format_text_underline.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = style.underline.value,
                onTriggerActionToggleSelectedChange = {
                    style.underline.value = it
                    println("Selected underline? $it")
                }
            ),
            Command(
                text = "Strikethrough",
                iconFactory = format_text_strikethrough.factory(),
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

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

        AuroraDecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
            DemoMenuBar()
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.TOOLBAR) {
            DemoToolbar(alignmentCommands = alignmentCommands, styleCommands = styleCommands)
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.NONE) {
            DemoArea(styleCommands = styleCommands, contentEnabled = contentEnabled)
        }
        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.FOOTER) {
            DemoFooter(alignmentCommands = alignmentCommands)
        }
    }
}



