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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.demo.svg.vaadin.*
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import kotlin.system.exitProcess

@ExperimentalComposeApi
fun main() {
    val skin = mutableStateOf(marinerSkin())

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        size = IntSize(720, 660),
        undecorated = true,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = "File",
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(text = "New", action = { println("New file!") }),
                                Command(text = "Open", action = { println("Open file!") }),
                                Command(text = "Save", action = { println("Save file!") })
                            )
                        )
                    )
                ),
                Command(text = "Edit", action = { println("Edit activated!") }),
                Command(text = "View", action = { println("View activated!") }),
                Command(text = "Tools", action = { println("Tools activated!") }),
                Command(text = "Window", action = { println("Window activated!") }),
                Command(text = "Help", action = { println("Help activated!") })
            )
        )
    ) {
        DemoContent(skin)
    }
}

data class Person(val firstName: String, val lastName: String)
enum class DemoAlignment {
    Center, Left, Right, Fill
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
        CommandButtonProjection(
            command = Command(text = "",
                iconFactory = remove_circle_outline_24px.factory(),
                isActionEnabled = enabled and (progress > 0.0f),
                action = { progress -= 0.1f }),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                iconDimension = 14.dp,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()

        AuroraDeterminateLinearProgress(
            contentModel = ProgressDeterminateContentModel(
                enabled = enabled,
                progress = animatedStateProgress.value
            )
        )

        CommandButtonProjection(
            command = Command(text = "",
                iconFactory = add_circle_outline_24px.factory(),
                isActionEnabled = enabled and (progress < 1.0f),
                action = { progress += 0.1f }),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                iconDimension = 14.dp,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()
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
        CommandButtonProjection(
            command = Command(
                text = "cut",
                iconFactory = edit_cut.factory(),
                action = { println("Cut!") }
            ),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = 20.dp
            )
        ).project()
        CommandButtonProjection(
            command = Command(
                text = "copy",
                iconFactory = edit_copy.factory(),
                isActionEnabled = false,
                action = { println("Copy!") }
            ),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = 20.dp
            )
        ).project()
        CommandButtonProjection(
            command = Command(
                text = "paste",
                iconFactory = edit_paste.factory(),
                action = { println("Paste!") }
            ),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = 20.dp
            )
        ).project()
        CommandButtonProjection(
            command = Command(
                text = "select all",
                iconFactory = edit_select_all.factory(),
                action = { println("Select all!") }
            ),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = 20.dp
            )
        ).project()
        CommandButtonProjection(
            command = Command(
                text = "delete",
                iconFactory = edit_delete.factory(),
                action = { println("Delete!") }
            ),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = 20.dp
            )
        ).project()

        Spacer(modifier = Modifier.width(4.dp))
        AuroraVerticalSeparator(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.width(4.dp))

        CommandButtonStripProjection(
            commandGroup = alignmentCommands,
            commandStripPresentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.Horizontal,
                iconDimension = 20.dp
            )
        ).project()

        Spacer(modifier = Modifier.width(4.dp))
        AuroraVerticalSeparator(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.width(4.dp))

        CommandButtonStripProjection(
            commandGroup = styleCommands,
            commandStripPresentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.Horizontal,
                iconDimension = 20.dp
            )
        ).project()

        Spacer(modifier.weight(weight = 1.0f, fill = true))

        CommandButtonProjection(
            command = Command(text = "exit",
                iconFactory = process_stop.factory(),
                action = { exitProcess(0) }),
            commandButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                iconDimension = 20.dp
            )
        ).project()
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

        CommandButtonStripProjection(
            commandGroup = alignmentCommands,
            commandStripPresentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.Horizontal,
                iconDimension = 14.dp
            )
        ).project()
    }
}

@ExperimentalComposeApi
@Composable
fun DemoHeader(
    text: String,
    iconFactory: AuroraIcon.Factory,
    contentEnabled: MutableState<Boolean>
) {
    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    val fontSize = resolvedTextStyle.fontSize
    // Compute a smaller font size
    val smallerFontSize = TextUnit(fontSize.value - 4.0f, fontSize.type)
    // And create our own text style with smaller font size and bold weight
    val textStyle = TextStyle(
        fontSize = smallerFontSize,
        fontWeight = FontWeight.Bold
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuroraHorizontalSeparator(modifier = Modifier.weight(1.0f, fill = true))
        AuroraLabel(
            contentModel = LabelContentModel(
                text = text.toUpperCase(),
                enabled = contentEnabled.value,
                iconFactory = iconFactory,
            ),
            presentationModel = LabelPresentationModel(
                textStyle = textStyle,
                iconDimension = 10.dp,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        )
        AuroraHorizontalSeparator(modifier = Modifier.weight(1.0f, fill = true))
    }
}

@ExperimentalComposeApi
@Composable
fun DemoArea(
    modifier: Modifier = Modifier,
    contentEnabled: MutableState<Boolean>,
    auroraSkinDefinition: MutableState<AuroraSkinDefinition>,
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
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AuroraCheckBox(contentModel = SelectorContentModel(
                text = "content enabled",
                selected = contentEnabled.value,
                onTriggerSelectedChange = { contentEnabled.value = !contentEnabled.value }
            ))

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
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            DemoHeader("Buttons", button.factory(), contentEnabled)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // A toggle command button backed by a mutable boolean
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
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
                        iconEnabledFilterStrategy = IconFilterStrategy.Original,
                        iconActiveFilterStrategy = IconFilterStrategy.Original,
                    )
                )
                // A command button with icon using THEMED_FOLLOW_TEXT filter strategy
                AuroraCommandButton(
                    command = Command(
                        text = "icon / text",
                        iconFactory = keyboard_capslock_24px.factory(),
                        isActionEnabled = contentEnabled.value,
                        action = {}
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        iconDimension = 20.dp,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    )
                )
                // A flat command button with displays background when it's
                // active (rollover, pressed, etc)
                AuroraCommandButton(
                    command = Command(
                        text = "flat",
                        iconFactory = account_box_24px.factory(),
                        isActionEnabled = contentEnabled.value,
                        action = { println("Clicked!") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconDimension = 20.dp,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    )
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                // Example of a command button strip
                CommandButtonStripProjection(
                    commandGroup = styleCommands,
                    commandStripPresentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Horizontal,
                        iconDimension = 20.dp
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                var toggleStarButtonSelected by remember { mutableStateOf(true) }
                // Example of an icon-only button with smaller content padding around the icon
                AuroraCommandButton(
                    command = Command(
                        text = "always",
                        iconFactory = star_black_48dp.factory(),
                        isActionEnabled = contentEnabled.value,
                        isActionToggle = true,
                        isActionToggleSelected = toggleStarButtonSelected,
                        onTriggerActionToggleSelectedChange = {
                            println("Selected small? $it")
                            toggleStarButtonSelected = it
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Small,
                        iconDimension = 24.dp,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        horizontalGapScaleFactor = 0.25f,
                        verticalGapScaleFactor = 0.25f
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Example of an icon-only button with larger content padding around the icon
                AuroraCommandButton(
                    command = Command(
                        text = "always",
                        iconFactory = star_black_48dp.factory(),
                        isActionEnabled = contentEnabled.value,
                        isActionToggle = true,
                        isActionToggleSelected = toggleStarButtonSelected,
                        onTriggerActionToggleSelectedChange = {
                            println("Selected small? $it")
                            toggleStarButtonSelected = it
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Small,
                        iconDimension = 24.dp,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        contentPadding = PaddingValues(8.dp)
                    )
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Example of a simple combobox
                val simpleComboItems = listOf("one", "two", "three")
                val simpleComboSelectedItem = remember { mutableStateOf(simpleComboItems[1]) }
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = simpleComboItems,
                        selectedItem = simpleComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            simpleComboSelectedItem.value = it
                            println("$it selected!")
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        displayConverter = { it },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Example of a combobox with display converter for more complex data
                val personComboItems =
                    listOf(
                        Person("Bob", "Loblaw"),
                        Person("Paige", "Turner"),
                        Person("Donaldson", "Duck")
                    )
                val personComboSelectedItem = remember { mutableStateOf(personComboItems[0]) }
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = personComboItems,
                        selectedItem = personComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            personComboSelectedItem.value = it
                            println("$it selected!")
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()
            }

            DemoHeader("Selectors", check_square_o.factory(), contentEnabled)
            Row(modifier = Modifier.fillMaxWidth()) {
                // Example of a checkbox backed by a mutable boolean
                var checkboxSelected by remember { mutableStateOf(true) }
                AuroraCheckBox(contentModel = SelectorContentModel(
                    text = "sample check",
                    enabled = contentEnabled.value,
                    selected = checkboxSelected,
                    onTriggerSelectedChange = {
                        println("Selected checkbox? $it")
                        checkboxSelected = it
                    }
                ))

                Spacer(modifier = Modifier.width(8.dp))

                // Example of a radio button backed by a mutable boolean
                var radioButtonSelected by remember { mutableStateOf(true) }
                AuroraRadioButton(contentModel = SelectorContentModel(
                    text = "sample radio",
                    enabled = contentEnabled.value,
                    selected = radioButtonSelected,
                    onTriggerSelectedChange = {
                        println("Selected radio? $it")
                        radioButtonSelected = it
                    }
                ))
            }

            DemoHeader("Combo boxes", combobox.factory(), contentEnabled)

            // Example of comboboxes with different popup placements, all updating and being
            // updated by the same mutable selection tracker
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
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = snowComboItems,
                        selectedItem = snowComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            snowComboSelectedItem.value = it
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        popupPlacementStrategy = PopupPlacementStrategy.Startward,
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = snowComboItems,
                        selectedItem = snowComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            snowComboSelectedItem.value = it
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        popupPlacementStrategy = PopupPlacementStrategy.Endward,
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = snowComboItems,
                        selectedItem = snowComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            snowComboSelectedItem.value = it
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        popupPlacementStrategy = PopupPlacementStrategy.Upward,
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = snowComboItems,
                        selectedItem = snowComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            snowComboSelectedItem.value = it
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        popupPlacementStrategy = PopupPlacementStrategy.Downward,
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = snowComboItems,
                        selectedItem = snowComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            snowComboSelectedItem.value = it
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        popupPlacementStrategy = PopupPlacementStrategy.CenteredVertically,
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()
            }

            DemoHeader("Progress bars", progressbar.factory(), contentEnabled)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Example of an indeterminate linear progress bar
                AuroraIndeterminateLinearProgress(
                    contentModel = ProgressIndeterminateContentModel(enabled = contentEnabled.value),
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Example of an determinate linear progress bar
                DemoProgress(enabled = contentEnabled.value)

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a circular progress indicator
                AuroraCircularProgress(
                    contentModel = ProgressIndeterminateContentModel(enabled = contentEnabled.value)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a larger circular progress indicator
                AuroraCircularProgress(
                    contentModel = ProgressIndeterminateContentModel(enabled = contentEnabled.value),
                    presentationModel = ProgressCircularPresentationModel(size = 14.dp)
                )
            }

            DemoHeader("Sliders", slider.factory(), contentEnabled)

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                // Example of a continuous slider
                var sliderValue1 by remember { mutableStateOf(0.5f) }
                AuroraSlider(
                    contentModel = SliderContentModel(
                        value = sliderValue1,
                        valueRange = 0.0f.rangeTo(1.0f),
                        onTriggerValueChange = {
                            sliderValue1 = it
                            println("Slider $it")
                        },
                        onValueChangeEnd = { println("Slider change done!") },
                        enabled = contentEnabled.value
                    )
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a discrete slider that draws ticks
                var sliderValue2 by remember { mutableStateOf(50f) }
                AuroraSlider(
                    contentModel = SliderContentModel(
                        value = sliderValue2,
                        valueRange = 0.0f.rangeTo(100.0f),
                        onTriggerValueChange = {
                            sliderValue2 = it
                            println("Slider $it")
                        },
                        onValueChangeEnd = { println("Slider change done!") },
                        enabled = contentEnabled.value
                    ),
                    presentationModel = SliderPresentationModel(
                        tickSteps = 9,
                        snapToTicks = true,
                        drawTicks = true
                    )
                )
            }

            DemoHeader("Text fields", text_input.factory(), contentEnabled)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Example of a multi-line text field
                var text1 by rememberSaveable { mutableStateOf("Sample text area") }
                AuroraTextField(
                    contentModel = TextFieldStringContentModel(
                        value = text1,
                        onValueChange = { text1 = it },
                        enabled = contentEnabled.value
                    )
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a single-line text field
                var text2 by rememberSaveable { mutableStateOf("Sample text field") }
                AuroraTextField(
                    contentModel = TextFieldStringContentModel(
                        value = text2,
                        onValueChange = { text2 = it },
                        enabled = contentEnabled.value
                    ),
                    presentationModel = TextFieldPresentationModel(singleLine = true)
                )
            }
        }
    }
}

@ExperimentalComposeApi
@Composable
fun DemoContent(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    val contentEnabled = remember { mutableStateOf(true) }
    val alignment = remember { mutableStateOf(DemoAlignment.Center) }

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
                isActionToggleSelected = (alignment.value == DemoAlignment.Center),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Center
                }
            ),
            Command(
                text = "Left",
                iconFactory = format_justify_left.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Left),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Left
                }
            ),
            Command(
                text = "Right",
                iconFactory = format_justify_right.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Right),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Right
                }
            ),
            Command(
                text = "Fill",
                iconFactory = format_justify_fill.factory(),
                isActionEnabled = contentEnabled.value,
                isActionToggle = true,
                isActionToggleSelected = (alignment.value == DemoAlignment.Fill),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment.value = DemoAlignment.Fill
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

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.TOOLBAR) {
            DemoToolbar(alignmentCommands = alignmentCommands, styleCommands = styleCommands)
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.NONE) {
            DemoArea(
                styleCommands = styleCommands, auroraSkinDefinition = auroraSkinDefinition,
                contentEnabled = contentEnabled
            )
        }
        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.FOOTER) {
            DemoFooter(alignmentCommands = alignmentCommands)
        }
    }
}



