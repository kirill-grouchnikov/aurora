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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.contextmenu.auroraContextMenu
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.demo.svg.vaadin.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.*
import java.text.MessageFormat
import java.util.*
import kotlin.system.exitProcess

@ExperimentalUnitApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(720.dp, 660.dp)
    )
    val aboutState = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.TopStart),
        size = DpSize(200.dp, 150.dp)
    )
    var skin by remember { mutableStateOf(businessSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    var isAboutWindowOpen by remember { mutableStateOf(false) }
    if (isAboutWindowOpen) {
        AuroraWindow(
            skin = skin,
            title = "About",
            state = aboutState,
            undecorated = true,
            onCloseRequest = { isAboutWindowOpen = false }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = resourceBundle.getString("Menu.about")
                    )
                ).project()
            }
        }
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        undecorated = true,
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
                    text = resourceBundle.getString("Menu.view"),
                    action = { println("View activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.tools"),
                    action = { println("Tools activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.window"),
                    action = { println("Window activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.help"),
                    action = { println("Help activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.about"),
                    action = {
                        println("About activated!")
                        isAboutWindowOpen = true
                    })
            )
        )
    ) {
        DemoContent(
            onSkinChange = { skin = it },
            resourceBundle = resourceBundle
        )
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
            contentModel = Command(text = "",
                icon = remove_circle_outline_24px(),
                isActionEnabled = enabled and (progress > 0.0f),
                action = { progress -= 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                iconDimension = 14.dp,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()

        DeterminateLinearProgressProjection(
            contentModel = ProgressDeterminateContentModel(
                enabled = enabled,
                progress = animatedStateProgress.value
            )
        ).project()

        CommandButtonProjection(
            contentModel = Command(text = "",
                icon = add_circle_outline_24px(),
                isActionEnabled = enabled and (progress < 1.0f),
                action = { progress += 0.1f }),
            presentationModel = CommandButtonPresentationModel(
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
    styleCommands: CommandGroup,
    resourceBundle: ResourceBundle,
    iconDimension: Dp = 16.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        CommandButtonProjection(
            contentModel = Command(
                text = resourceBundle.getString("Edit.cut.text"),
                icon = edit_cut(),
                action = { println("Cut!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MediumFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = resourceBundle.getString("Edit.copy.text"),
                icon = edit_copy(),
                isActionEnabled = false,
                action = { println("Copy!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MediumFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = resourceBundle.getString("Edit.paste.text"),
                icon = edit_paste(),
                action = { println("Paste!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = resourceBundle.getString("Edit.selectAll.text"),
                icon = edit_select_all(),
                action = { println("Select all!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = resourceBundle.getString("Edit.delete.text"),
                icon = edit_delete(),
                action = { println("Delete!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()

        Spacer(modifier = Modifier.width(4.dp))
        VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.width(4.dp))

        CommandButtonStripProjection(
            contentModel = alignmentCommands,
            presentationModel = CommandStripPresentationModel(
                commandPresentationState = CommandButtonPresentationState.SmallFitToIcon,
                orientation = StripOrientation.Horizontal,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()

        Spacer(modifier = Modifier.width(4.dp))
        VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.width(4.dp))

        CommandButtonStripProjection(
            contentModel = styleCommands,
            presentationModel = CommandStripPresentationModel(
                commandPresentationState = CommandButtonPresentationState.SmallFitToIcon,
                orientation = StripOrientation.Horizontal,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()

        Spacer(modifier.weight(weight = 1.0f, fill = true))

        CommandButtonProjection(
            contentModel = Command(text = resourceBundle.getString("AppMenuExit.text"),
                icon = process_stop(),
                action = { exitProcess(0) }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = iconDimension
            )
        ).project()
    }
}

@Composable
fun DemoFooter(
    modifier: Modifier = Modifier,
    contentEnabled: MutableState<Boolean>,
    alignmentCommands: CommandGroup,
    resourceBundle: ResourceBundle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Spacer(modifier.weight(weight = 1.0f, fill = true))

        val count = 10
        val toggleStates = remember { mutableStateListOf<Boolean>() }
        for (i in 0 until count) {
            toggleStates.add(false)
        }

        val commands1 = arrayListOf<Command>()
        val commands2 = arrayListOf<Command>()
        val commands3 = arrayListOf<Command>()

        val mf = MessageFormat(resourceBundle.getString("TestMenuItem.text"))
        for (i in 0 until count) {
            val command = Command(
                text = mf.format(arrayOf<Any>(i)),
                isActionToggle = true,
                isActionToggleSelected = toggleStates[i],
                onTriggerActionToggleSelectedChange = {
                    toggleStates[i] = it
                }
            )

            when (i) {
                0, 1, 2 -> commands1.add(command)
                3, 4, 5, 6 -> commands2.add(command)
                else -> commands3.add(command)
            }
        }

        LabelProjection(
            contentModel = LabelContentModel(
                text = resourceBundle.getString("ContextMenu.show"),
                enabled = contentEnabled.value
            )
        ).project(
            modifier = Modifier.auroraContextMenu(
                enabled = contentEnabled.value,
                contentModel = CommandMenuContentModel(
                    groups = listOf(
                        CommandGroup(commands = commands1),
                        CommandGroup(commands = commands2),
                        CommandGroup(commands = commands3)
                    )
                ),
                presentationModel = CommandPopupMenuPresentationModel(
                    popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
                    toDismissOnCommandActivation = false
                )
            )
        )

        CommandButtonStripProjection(
            contentModel = alignmentCommands,
            presentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.Horizontal,
                iconDimension = 14.dp
            )
        ).project()
    }
}

@ExperimentalUnitApi
@Composable
fun DemoHeader(
    text: String,
    icon: Painter,
    contentEnabled: MutableState<Boolean>
) {
    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveAuroraDefaults()
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
        HorizontalSeparatorProjection().project(modifier = Modifier.weight(1.0f, fill = true))
        LabelProjection(
            contentModel = LabelContentModel(
                text = text.uppercase(),
                enabled = contentEnabled.value,
                icon = icon,
            ),
            presentationModel = LabelPresentationModel(
                textStyle = textStyle,
                iconDimension = 10.dp,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()
        HorizontalSeparatorProjection().project(modifier = Modifier.weight(1.0f, fill = true))
    }
}

@ExperimentalUnitApi
@Composable
fun AuroraApplicationScope.DemoArea(
    modifier: Modifier = Modifier,
    contentEnabled: MutableState<Boolean>,
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle,
    styleCommands: CommandGroup
) {
    // TODO - convert this to use ConstraintLayout when (if?) that is available for desktop
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
    ) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
            Column(
                modifier = Modifier.fillMaxHeight().auroraBackground()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = resourceBundle.getString("Content.enabled"),
                    selected = contentEnabled.value,
                    onTriggerSelectedChange = { contentEnabled.value = !contentEnabled.value }
                )).project()

                AuroraSkinSwitcher(onSkinChange)

                AuroraLocaleSwitcher(resourceBundle)
            }
        }

        VerticalSeparatorProjection(
            contentModel = SeparatorContentModel(),
            presentationModel = SeparatorPresentationModel(
                startGradientAmount = 0.dp,
                endGradientAmount = 0.dp
            )
        ).project(modifier = Modifier.fillMaxHeight())

        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DemoHeader(resourceBundle.getString("Group.buttons"), button(), contentEnabled)
            Row(modifier = Modifier.fillMaxWidth()) {
                // A toggle command button backed by a mutable boolean
                var toggleButtonSelected by remember { mutableStateOf(true) }
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Control.button.toggle"),
                        icon = computer(),
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
                        // This is a full-color icon. Use original colors for enabled and active
                        // states, and color scheme based filtering for disabled states
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
                        iconEnabledFilterStrategy = IconFilterStrategy.Original,
                        iconActiveFilterStrategy = IconFilterStrategy.Original,
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // A command button with icon using ThemedFollowText filter strategy
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Control.button.iconText"),
                        icon = keyboard_capslock_24px(),
                        isActionEnabled = contentEnabled.value,
                        action = {}
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        iconDimension = 20.dp,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // A flat command button that displays background when it's
                // active (rollover, pressed, etc)
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Control.button.flat"),
                        icon = account_box_24px(),
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
                ).project()
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                // Example of a command button strip
                CommandButtonStripProjection(
                    contentModel = styleCommands,
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Horizontal,
                        iconDimension = 20.dp
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                var toggleStarButtonSelected by remember { mutableStateOf(true) }
                // Example of an icon-only button with smaller content padding around the icon
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        icon = star_black_48dp(),
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
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Example of an icon-only button with larger content padding around the icon
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        icon = star_black_48dp(),
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
                ).project()

                Spacer(modifier = Modifier.width(20.dp))

                // Example of a simple combobox
                val simpleComboItems =
                    listOf(
                        resourceBundle.getString("Combo.entry.one"),
                        resourceBundle.getString("Combo.entry.two"),
                        resourceBundle.getString("Combo.entry.three")
                    )
                val simpleComboSelectedItem = remember { mutableStateOf(simpleComboItems[1]) }
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled.value,
                        items = simpleComboItems,
                        selectedItem = simpleComboSelectedItem.value,
                        richTooltip = RichTooltip(
                            title = resourceBundle.getString("Tooltip.genericTitle"),
                            mainIcon = user_home(),
                            descriptionSections = listOf(
                                resourceBundle.getString("Tooltip.textParagraph1"),
                                resourceBundle.getString("Tooltip.textParagraph2")
                            ),
                            footerIcon = help_browser(),
                            footerSections = listOf(
                                resourceBundle.getString("Tooltip.textFooterParagraph1")
                            )
                        ),
                        onTriggerItemSelectedChange = {
                            simpleComboSelectedItem.value = it
                            println("$it selected!")
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        displayConverter = { it },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        richTooltipPresentationModel = RichTooltipPresentationModel(
                            mainIconSize = 36.dp,
                            footerIconSize = 20.dp
                        )
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

            DemoHeader(
                resourceBundle.getString("Group.selectors"),
                check_square_o(),
                contentEnabled
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                // Example of a checkbox backed by a mutable boolean
                var checkboxSelected by remember { mutableStateOf(true) }
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = resourceBundle.getString("Control.selector.checkbox"),
                    richTooltip = RichTooltip(
                        title = resourceBundle.getString("Tooltip.genericTitle"),
                        mainIcon = user_home(),
                        descriptionSections = listOf(
                            resourceBundle.getString("Tooltip.textParagraph1"),
                            resourceBundle.getString("Tooltip.textParagraph2")
                        ),
                        footerIcon = help_browser(),
                        footerSections = listOf(
                            resourceBundle.getString("Tooltip.textFooterParagraph1")
                        )
                    ),
                    enabled = contentEnabled.value,
                    selected = checkboxSelected,
                    onTriggerSelectedChange = {
                        println("Selected checkbox? $it")
                        checkboxSelected = it
                    }
                )).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Example of a radio button backed by a mutable boolean
                var radioButtonSelected by remember { mutableStateOf(true) }
                RadioButtonProjection(
                    contentModel = SelectorContentModel(
                        text = resourceBundle.getString("Control.selector.radio"),
                        richTooltip = RichTooltip(
                            title = resourceBundle.getString("Tooltip.genericTitle"),
                            mainIcon = user_home(),
                            descriptionSections = listOf(
                                resourceBundle.getString("Tooltip.textParagraph1"),
                                resourceBundle.getString("Tooltip.textParagraph2")
                            ),
                            footerIcon = help_browser(),
                            footerSections = listOf(
                                resourceBundle.getString("Tooltip.textFooterParagraph1")
                            )
                        ),
                        enabled = contentEnabled.value,
                        selected = radioButtonSelected,
                        onTriggerSelectedChange = {
                            println("Selected radio? $it")
                            radioButtonSelected = it
                        }
                    )).project()
            }

            DemoHeader(
                resourceBundle.getString("Group.comboboxes"),
                combobox(),
                contentEnabled
            )

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
                        popupPlacementStrategy = PopupPlacementStrategy.Startward.VAlignTop,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Startward.VAlignBottom,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignBottom,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignEnd,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
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
                        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd,
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
                        popupPlacementStrategy = PopupPlacementStrategy.CenteredVertically.HAlignStart,
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
                        popupPlacementStrategy = PopupPlacementStrategy.CenteredVertically.HAlignEnd,
                        displayConverter = { it.lastName + ", " + it.firstName },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()
            }

            DemoHeader(
                resourceBundle.getString("Group.progressbars"),
                progressbar(),
                contentEnabled
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Example of an indeterminate linear progress bar
                IndeterminateLinearProgressProjection(
                    contentModel = ProgressIndeterminateContentModel(enabled = contentEnabled.value),
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a determinate linear progress bar
                DemoProgress(enabled = contentEnabled.value)

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a circular progress indicator
                CircularProgressProjection(
                    contentModel = ProgressIndeterminateContentModel(enabled = contentEnabled.value)
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a larger circular progress indicator
                CircularProgressProjection(
                    contentModel = ProgressIndeterminateContentModel(enabled = contentEnabled.value),
                    presentationModel = ProgressCircularPresentationModel(size = 14.dp)
                ).project()
            }

            DemoHeader(resourceBundle.getString("Group.sliders"), slider(), contentEnabled)

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                // Example of a continuous slider
                var sliderValue1 by remember { mutableStateOf(0.3f) }
                SliderProjection(
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
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a discrete slider that draws ticks
                var sliderValue2 by remember { mutableStateOf(30f) }
                SliderProjection(
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
                ).project()
            }

            DemoHeader(
                resourceBundle.getString("Group.textfields"),
                text_input(),
                contentEnabled
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Example of a multi-line text field
                var text1 by rememberSaveable { mutableStateOf("") }
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = text1,
                        placeholder = resourceBundle.getString("Control.textfield.area"),
                        onValueChange = { text1 = it },
                        enabled = contentEnabled.value
                    )
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a single-line text field
                var text2 by rememberSaveable { mutableStateOf("") }
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = text2,
                        placeholder = resourceBundle.getString("Control.textfield.field"),
                        onValueChange = { text2 = it },
                        enabled = contentEnabled.value
                    ),
                    presentationModel = TextFieldPresentationModel(singleLine = true)
                ).project()
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun AuroraApplicationScope.DemoContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
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
                text = resourceBundle.getString("Justify.center"),
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
                text = resourceBundle.getString("Justify.left"),
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
                text = resourceBundle.getString("Justify.right"),
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
                text = resourceBundle.getString("Justify.fill"),
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
                text = resourceBundle.getString("FontStyle.bold.title"),
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
                text = resourceBundle.getString("FontStyle.italic.title"),
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
                text = resourceBundle.getString("FontStyle.underline.title"),
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
                text = resourceBundle.getString("FontStyle.strikethrough.title"),
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
                resourceBundle = resourceBundle
            )
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
            DemoArea(
                modifier = Modifier.weight(weight = 1.0f, fill = true),
                styleCommands = styleCommands,
                onSkinChange = onSkinChange,
                resourceBundle = resourceBundle,
                contentEnabled = contentEnabled
            )
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
            DemoFooter(
                contentEnabled = contentEnabled,
                alignmentCommands = alignmentCommands,
                resourceBundle = resourceBundle
            )
        }
    }
}



