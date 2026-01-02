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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
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
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*
import kotlin.system.exitProcess

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 780.dp)
    )
    val aboutState = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.TopStart),
        size = DpSize(200.dp, 150.dp)
    )
    var skin by remember { mutableStateOf(mistSilverSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    var isAboutWindowOpen by remember { mutableStateOf(false) }
    if (isAboutWindowOpen) {
        AuroraWindow(
            skin = skin,
            title = "About",
            state = aboutState,
            windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
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
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
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
data class SaveAs(val title: String, val icon: Painter)

enum class DemoAlignment {
    Center, Left, Right, Fill
}

@Stable
data class DemoStyle(
    val bold: Boolean,
    val italic: Boolean,
    val underline: Boolean,
    val strikethrough: Boolean
)

@Composable
fun DemoLinearProgress(enabled: Boolean) {
    var progress by remember { mutableStateOf(0.5f) }
    println("Progress = $progress")
    val animatedStateProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressConstants.ProgressAnimationSpec
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        CommandButtonProjection(
            contentModel = Command(text = "",
                icon = remove_circle_outline_24px(),
                isActionEnabled = enabled and (progress > 0.00001f),
                action = { progress -= 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                iconDimension = DpSize(14.dp, 14.dp),
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()

        DeterminateLinearProgressProjection(
            contentModel = DeterminateProgressContentModel(
                enabled = enabled,
                progress = animatedStateProgress.value
            )
        ).project()

        CommandButtonProjection(
            contentModel = Command(text = "",
                icon = add_circle_outline_24px(),
                isActionEnabled = enabled and (progress < 0.99999f),
                action = { progress += 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                iconDimension = DpSize(14.dp, 14.dp),
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()
    }
}

@Composable
fun DemoCircularProgress(enabled: Boolean, customSize: Dp? = null) {
    var progress by remember { mutableStateOf(0.5f) }
    val animatedStateProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressConstants.ProgressAnimationSpec
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        CommandButtonProjection(
            contentModel = Command(text = "",
                icon = remove_circle_outline_24px(),
                isActionEnabled = enabled and (progress > 0.00001f),
                action = { progress -= 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                iconDimension = DpSize(14.dp, 14.dp),
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()

        val presentationModel = if (customSize != null)
            CircularProgressPresentationModel(radius = customSize) else
                CircularProgressPresentationModel()

        DeterminateCircularProgressProjection(
            contentModel = DeterminateProgressContentModel(
                enabled = enabled,
                progress = animatedStateProgress.value
            ),
            presentationModel = presentationModel
        ).project()

        CommandButtonProjection(
            contentModel = Command(text = "",
                icon = add_circle_outline_24px(),
                isActionEnabled = enabled and (progress < 0.99999f),
                action = { progress += 0.1f }),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Small,
                iconDimension = DpSize(14.dp, 14.dp),
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
    iconDimension: DpSize = DpSize(16.dp, 16.dp)
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
    contentEnabled: Boolean,
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
                enabled = contentEnabled
            )
        ).project(
            modifier = Modifier.auroraContextMenu(
                enabled = contentEnabled,
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
                commandPresentationState = CommandButtonPresentationState.SmallFitToIcon,
                iconDimension = DpSize(14.dp, 14.dp)
            )
        ).project()
    }
}

@Composable
fun DemoHeader(
    text: String,
    icon: Painter,
    contentEnabled: Boolean
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
                enabled = contentEnabled,
                icon = icon,
            ),
            presentationModel = LabelPresentationModel(
                textStyle = textStyle,
                iconDimension = DpSize(10.dp, 10.dp),
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project()
        HorizontalSeparatorProjection().project(modifier = Modifier.weight(1.0f, fill = true))
    }
}

@Composable
fun AuroraApplicationScope.DemoArea(
    modifier: Modifier = Modifier,
    contentEnabled: Boolean,
    onContentEnabledChanged: (Boolean) -> Unit,
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
        AuroraDecorationArea(
            decorationAreaType = DecorationAreaType.ControlPane,
            buttonShaper = ClassicButtonShaper.Instance
        ) {
            Column(
                modifier = Modifier.fillMaxHeight().auroraBackground()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = resourceBundle.getString("Content.enabled"),
                    selected = contentEnabled,
                    onClick = { onContentEnabledChanged.invoke(!contentEnabled) }
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
                        isActionEnabled = contentEnabled,
                        isActionToggle = true,
                        isActionToggleSelected = toggleButtonSelected,
                        onTriggerActionToggleSelectedChange = {
                            println("Selected toggle? $it")
                            toggleButtonSelected = it
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.MediumFitToIcon,
                        iconDimension = DpSize(20.dp, 20.dp),
                        // This is a full-color icon. Use original colors for enabled and active
                        // states, and color tokens based filtering for disabled states
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
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
                        isActionEnabled = contentEnabled,
                        action = {}
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.MediumFitToIcon,
                        iconDimension = DpSize(20.dp, 20.dp),
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
                        isActionEnabled = contentEnabled,
                        action = { println("Clicked!") }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.MediumFitToIcon,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconDimension = DpSize(20.dp, 20.dp),
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    )
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a command button strip
                CommandButtonStripProjection(
                    contentModel = styleCommands,
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Horizontal,
                        iconDimension = DpSize(20.dp, 20.dp)
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                var toggleStarButtonSelected by remember { mutableStateOf(true) }
                // Example of an icon-only button with smaller content padding around the icon
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        icon = star_black_48dp(),
                        isActionEnabled = contentEnabled,
                        isActionToggle = true,
                        isActionToggleSelected = toggleStarButtonSelected,
                        onTriggerActionToggleSelectedChange = {
                            println("Selected small? $it")
                            toggleStarButtonSelected = it
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Small,
                        iconDimension = DpSize(24.dp, 24.dp),
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
                        isActionEnabled = contentEnabled,
                        isActionToggle = true,
                        isActionToggleSelected = toggleStarButtonSelected,
                        onTriggerActionToggleSelectedChange = {
                            println("Selected small? $it")
                            toggleStarButtonSelected = it
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Small,
                        iconDimension = DpSize(24.dp, 24.dp),
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        contentPadding = PaddingValues(8.dp)
                    )
                ).project()
            }

            Row(modifier = Modifier.fillMaxWidth()) {
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
                        enabled = contentEnabled,
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
                        displayPrototype = { "Longer item" },
                        popupDisplayPrototype = { "Longer item" },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        richTooltipPresentationModel = RichTooltipPresentationModel(
                            mainIconSize = DpSize(36.dp, 36.dp),
                            footerIconSize = DpSize(20.dp, 20.dp)
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
                        enabled = contentEnabled,
                        items = personComboItems,
                        selectedItem = personComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            personComboSelectedItem.value = it
                            println("$it selected!")
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        displayConverter = { it.lastName + ", " + it.firstName },
                        displayPrototype = { Person("Frederick", "Rumpelstilt") },
                        popupDisplayPrototype = { Person("Frederick", "Rumpelstiltskin Jr.") },
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Another example of a combobox with display converter for more complex data
                val saveAsComboItems =
                    listOf(
                        SaveAs(resourceBundle.getString("AppMenuSaveAs.word.text"), x_office_document()),
                        SaveAs(resourceBundle.getString("AppMenuSaveAs.html.text"), text_x_generic()),
                        SaveAs(resourceBundle.getString("AppMenuSaveAs.other.text"), document_save_as()),
                    )
                val saveAsComboSelectedItem = remember { mutableStateOf(saveAsComboItems[0]) }
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        enabled = contentEnabled,
                        items = saveAsComboItems,
                        selectedItem = saveAsComboSelectedItem.value,
                        onTriggerItemSelectedChange = {
                            saveAsComboSelectedItem.value = it
                            println("$it selected!")
                        }
                    ),
                    presentationModel = ComboBoxPresentationModel(
                        displayConverter = { it.title },
                        displayIconConverter = { it.icon },
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
                    enabled = contentEnabled,
                    selected = checkboxSelected,
                    onClick = {
                        println("Selected checkbox? $checkboxSelected")
                        checkboxSelected = !checkboxSelected
                    }
                )).project()

                Spacer(modifier = Modifier.width(8.dp))

                // We have two radio buttons and only one can be selected
                var radioState by remember { mutableStateOf(true) }
                Row(Modifier.selectableGroup()) {
                    RadioButtonProjection(
                        contentModel = SelectorContentModel(
                            text = resourceBundle.getString("Control.selector.radio1"),
                            enabled = contentEnabled,
                            selected = radioState,
                            onClick = { radioState = true }
                        )).project()

                    RadioButtonProjection(
                        contentModel = SelectorContentModel(
                            text = resourceBundle.getString("Control.selector.radio2"),
                            enabled = contentEnabled,
                            selected = !radioState,
                            onClick = { radioState = false }
                        )).project()
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                // Example of a tri-state checkbox
                var triStateCheckbox by remember { mutableStateOf(ToggleableState.On) }
                TriStateCheckBoxProjection(
                    contentModel = TriStateSelectorContentModel(
                        text = resourceBundle.getString("Control.selector.triStateCheckbox"),
                        enabled = contentEnabled,
                        state = triStateCheckbox,
                        onClick = {
                            // Cycle through the available states
                            triStateCheckbox = when (triStateCheckbox) {
                                ToggleableState.On -> ToggleableState.Indeterminate
                                ToggleableState.Indeterminate -> ToggleableState.Off
                                ToggleableState.Off -> ToggleableState.On
                            }
                        }
                    )).project()
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = resourceBundle.getString("Control.selector.switches")
                    )
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Enabled selected switch
                var switchEnabledSelected by remember { mutableStateOf(true) }
                SwitchProjection(contentModel = SwitchContentModel(
                    enabled = contentEnabled,
                    selected = switchEnabledSelected,
                    onClick = {
                        println("Selected switch? $switchEnabledSelected")
                        switchEnabledSelected = !switchEnabledSelected
                    }
                )).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Enabled unselected switch
                var switchEnabledUnselected by remember { mutableStateOf(false) }
                SwitchProjection(contentModel = SwitchContentModel(
                    enabled = contentEnabled,
                    selected = switchEnabledUnselected,
                    onClick = {
                        println("Selected switch? $switchEnabledUnselected")
                        switchEnabledUnselected = !switchEnabledUnselected
                    }
                )).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Disabled selected switch
                var switchDisabledSelected by remember { mutableStateOf(true) }
                SwitchProjection(contentModel = SwitchContentModel(
                    enabled = false,
                    selected = switchDisabledSelected,
                    onClick = {
                        println("Selected switch? $switchDisabledSelected")
                        switchDisabledSelected = !switchDisabledSelected
                    }
                )).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Disabled unselected switch
                var switchDisabledUnselected by remember { mutableStateOf(false) }
                SwitchProjection(contentModel = SwitchContentModel(
                    enabled = false,
                    selected = switchDisabledUnselected,
                    onClick = {
                        println("Selected switch? $switchDisabledUnselected")
                        switchDisabledUnselected = !switchDisabledUnselected
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                        enabled = contentEnabled,
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
                    contentModel = IndeterminateProgressContentModel(enabled = contentEnabled),
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a determinate linear progress bar
                DemoLinearProgress(enabled = contentEnabled)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Example of a determinate circular progress bar
                DemoCircularProgress(enabled = contentEnabled)

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a larger determinate circular progress indicator
                DemoCircularProgress(enabled = contentEnabled, customSize = 14.dp)

                Spacer(modifier = Modifier.width(16.dp))

                // Example of an indeterminate circular progress indicator
                IndeterminateCircularProgressProjection(
                    contentModel = IndeterminateProgressContentModel(enabled = contentEnabled)
                ).project()

                Spacer(modifier = Modifier.width(16.dp))

                // Example of a larger indeterminate circular progress indicator
                IndeterminateCircularProgressProjection(
                    contentModel = IndeterminateProgressContentModel(enabled = contentEnabled),
                    presentationModel = CircularProgressPresentationModel(radius = 14.dp)
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
                        enabled = contentEnabled
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
                        enabled = contentEnabled
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
                        enabled = contentEnabled
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
                        enabled = contentEnabled
                    ),
                    presentationModel = TextFieldPresentationModel(singleLine = true)
                ).project()
            }
        }
    }
}

@Composable
fun AuroraApplicationScope.DemoContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
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
                resourceBundle = resourceBundle
            )
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
            DemoArea(
                modifier = Modifier.weight(weight = 1.0f, fill = true),
                styleCommands = styleCommands,
                onSkinChange = onSkinChange,
                resourceBundle = resourceBundle,
                contentEnabled = contentEnabled,
                onContentEnabledChanged = { contentEnabled = it }
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



