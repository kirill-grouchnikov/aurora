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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.skin.AuroraSkinDefinition
import org.pushingpixels.aurora.skin.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.skin.IconFilterStrategy
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import java.awt.ComponentOrientation
import java.text.MessageFormat
import java.util.*

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 400.dp)
    )
    val skin = mutableStateOf(marinerSkin())
    val currLocale = mutableStateOf(Locale.getDefault())
    val resourceBundle = derivedStateOf {
        ResourceBundle
            .getBundle("org.pushingpixels.aurora.demo.Resources", currLocale.value)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides
                    if (ComponentOrientation.getOrientation(currLocale.value).isLeftToRight)
                        LayoutDirection.Ltr else LayoutDirection.Rtl,
        ) {
            OrientationCommandContent(skin, currLocale, resourceBundle)
        }
    }
}

@Composable
fun OrientationCommandRow(
    actionCommand: Command,
    splitMainActionCommand: Command,
    splitMainPopupCommand: Command,
    popupCommand: Command,
    presentationState: CommandButtonPresentationState
) {
    Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
        CommandButtonProjection(
            contentModel = actionCommand,
            presentationModel = CommandButtonPresentationModel(presentationState = presentationState)
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = splitMainActionCommand,
            presentationModel = CommandButtonPresentationModel(
                presentationState = presentationState,
                textClick = TextClick.Action
            )
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = splitMainPopupCommand,
            presentationModel = CommandButtonPresentationModel(
                presentationState = presentationState,
                textClick = TextClick.Popup
            )
        ).project()

        Spacer(modifier = Modifier.width(8.dp))

        CommandButtonProjection(
            contentModel = popupCommand,
            presentationModel = CommandButtonPresentationModel(presentationState = presentationState)
        ).project()
    }
}

@Composable
fun CommandOrientationJustifyStrip(
    enabled: Boolean,
    orientation: StripOrientation,
    alignment: MutableState<CommandDemoAlignment>,
    horizontalGapScaleFactor: Float
) {
    val commandAlignCenter =
        Command(
            text = "Center",
            icon = format_justify_center(),
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
            icon = format_justify_left(),
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
            icon = format_justify_right(),
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
            icon = format_justify_fill(),
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
fun CommandOrientationEditStrip(
    actionEnabled: Boolean,
    popupEnabled: Boolean,
    orientation: StripOrientation,
    horizontalGapScaleFactor: Float,
    resourceBundle: State<ResourceBundle>
) {
    val commandCut =
        Command(
            text = resourceBundle.value.getString("Cut.text"),
            icon = content_cut_black_24dp(),
            isActionEnabled = actionEnabled,
            action = { println("Cut!") }

        )
    val commandCopy =
        Command(
            text = resourceBundle.value.getString("Copy.text"),
            icon = content_copy_black_24dp(),
            isActionEnabled = actionEnabled,
            action = { println("Copy!") }

        )

    var togglePasteText by remember { mutableStateOf(false) }
    val commandPasteTextOnly = Command(
        text = resourceBundle.value.getString("Paste.textOnlyText"),
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
            text = resourceBundle.value.getString("Paste.text"),
            icon = content_paste_black_24dp(),
            isActionEnabled = actionEnabled,
            action = { println("Paste!") },
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(
                    commands = listOf(
                        Command(
                            text = resourceBundle.value.getString("Paste.keepFormattingText"),
                            action = { println("Paste with keep formatting") }),
                        Command(
                            text = resourceBundle.value.getString("Paste.mergeFormattingText"),
                            action = { println("Paste with merge formatting") }),
                        commandPasteTextOnly,
                    )
                ),
                panelContentModel = getQuickStylesContentModel(resourceBundle)
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
fun CommandOrientationStyleStrip(
    resourceBundle: State<ResourceBundle>,
    enabled: Boolean,
    orientation: StripOrientation,
    style: CommandDemoStyle,
    horizontalGapScaleFactor: Float
) {
    val commandBold =
        Command(
            text = resourceBundle.value.getString("FontBold.tooltip.textActionTitle"),
            icon = format_bold_black_24dp(),
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
            text = resourceBundle.value.getString("FontItalic.tooltip.textActionTitle"),
            icon = format_italic_black_24dp(),
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
            text = resourceBundle.value.getString("FontUnderline.tooltip.textActionTitle"),
            icon = format_underlined_black_24dp(),
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
            text = resourceBundle.value.getString("FontStrikethrough.tooltip.textActionTitle"),
            icon = format_strikethrough_black_24dp(),
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

private fun getPopupMenuContentModel(resourceBundle: State<ResourceBundle>): CommandMenuContentModel {
    val mf = MessageFormat(resourceBundle.value.getString("TestMenuItem.text"))
    val simpleEntries1: MutableList<Command> = ArrayList()
    val simpleEntries2: MutableList<Command> = ArrayList()

    simpleEntries1.add(
        Command(
            text = mf.format(arrayOf<Any>("1")),
            icon = computer(),
            action = { println("Popup action 2") })
    )
    simpleEntries1.add(
        Command(
            text = mf.format(arrayOf<Any>("2")),
            action = { println("Popup action 2") })
    )
    simpleEntries1.add(
        Command(
            text = mf.format(arrayOf<Any>("3")),
            action = { println("Popup action 3") })
    )

    simpleEntries2.add(
        Command(
            text = mf.format(arrayOf<Any>("4")),
            action = { println("Popup action 4") })
    )
    simpleEntries2.add(
        Command(
            text = mf.format(arrayOf<Any>("5")),
            action = { println("Popup action 5") })
    )
    return CommandMenuContentModel(
        groups = arrayListOf(
            CommandGroup(commands = simpleEntries1),
            CommandGroup(commands = simpleEntries2)
        )
    )
}

@Composable
fun WindowScope.OrientationCommandContent(
    auroraSkinDefinition: MutableState<AuroraSkinDefinition>,
    locale: MutableState<Locale>,
    resourceBundle: State<ResourceBundle>
) {
    var actionEnabled by remember { mutableStateOf(true) }
    var popupEnabled by remember { mutableStateOf(true) }

    val actionCommand =
        Command(
            text = resourceBundle.value.getString("Paste.text"),
            extraText = resourceBundle.value.getString("Paste.textExtra"),
            icon = edit_paste(),
            action = { println("Paste activated!") },
            isActionEnabled = actionEnabled
        )

    val splitMainActionCommand =
        Command(
            text = resourceBundle.value.getString("Copy.text"),
            extraText = resourceBundle.value.getString("Copy.textExtra"),
            icon = edit_copy(),
            action = { println("Copy activated!") },
            isActionEnabled = actionEnabled,
            secondaryContentModel = getPopupMenuContentModel(resourceBundle),
            isSecondaryEnabled = popupEnabled
        )

    val splitMainPopupCommand =
        Command(
            text = resourceBundle.value.getString("Cut.text"),
            extraText = resourceBundle.value.getString("Cut.textExtra"),
            icon = edit_cut(),
            action = { println("Cut activated!") },
            isActionEnabled = actionEnabled,
            secondaryContentModel = getPopupMenuContentModel(resourceBundle),
            isSecondaryEnabled = popupEnabled
        )

    val popupCommand =
        Command(
            text = resourceBundle.value.getString("SelectAll.text"),
            extraText = resourceBundle.value.getString("SelectAll.textExtra"),
            icon = edit_select_all(),
            secondaryContentModel = getPopupMenuContentModel(resourceBundle),
            isSecondaryEnabled = popupEnabled
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
            CommandOrientationJustifyStrip(
                enabled = actionEnabled,
                orientation = StripOrientation.Vertical,
                alignment = alignment,
                horizontalGapScaleFactor = 0.7f
            )
        }
        Box(modifier = Modifier.padding(8.dp)) {
            CommandOrientationStyleStrip(
                resourceBundle = resourceBundle,
                enabled = actionEnabled,
                orientation = StripOrientation.Vertical,
                style = style,
                horizontalGapScaleFactor = 0.7f
            )
        }

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
                AuroraSkinSwitcher(auroraSkinDefinition)

                Spacer(modifier = Modifier.width(8.dp))

                AuroraLocaleSwitcher(locale, resourceBundle)
            }

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
                CommandButtonProjection(contentModel = splitMainActionCommand).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandButtonProjection(contentModel = splitMainPopupCommand).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandOrientationJustifyStrip(
                    enabled = actionEnabled,
                    orientation = StripOrientation.Horizontal,
                    alignment = alignment,
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandOrientationStyleStrip(
                    resourceBundle = resourceBundle,
                    enabled = actionEnabled,
                    orientation = StripOrientation.Horizontal,
                    style = style,
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandOrientationEditStrip(
                    actionEnabled = actionEnabled,
                    popupEnabled = popupEnabled,
                    orientation = StripOrientation.Horizontal,
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis,
                    resourceBundle = resourceBundle
                )
            }

            OrientationCommandRow(
                actionCommand,
                splitMainActionCommand,
                splitMainPopupCommand,
                popupCommand,
                CommandButtonPresentationState.Small
            )

            OrientationCommandRow(
                actionCommand,
                splitMainActionCommand,
                splitMainPopupCommand,
                popupCommand,
                CommandButtonPresentationState.Medium
            )

            OrientationCommandRow(
                actionCommand,
                splitMainActionCommand,
                splitMainPopupCommand,
                popupCommand,
                CommandButtonPresentationState.Tile
            )

            OrientationCommandRow(
                actionCommand,
                splitMainActionCommand,
                splitMainPopupCommand,
                popupCommand,
                CommandButtonPresentationState.Big
            )
        }
    }
}



