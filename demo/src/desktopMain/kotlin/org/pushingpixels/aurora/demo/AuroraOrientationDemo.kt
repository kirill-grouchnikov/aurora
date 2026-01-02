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
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 400.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        OrientationCommandContent({ skin = it }, resourceBundle)
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
    alignment: CommandDemoAlignment,
    onAlignmentChanged: (CommandDemoAlignment) -> Unit,
    horizontalGapScaleFactor: Float,
    resourceBundle: ResourceBundle
) {
    val commandAlignCenter =
        Command(
            text = resourceBundle.getString("Justify.center"),
            icon = format_justify_center(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Center),
            onTriggerActionToggleSelectedChange = {
                if (it) onAlignmentChanged(CommandDemoAlignment.Center)
            }
        )
    val commandAlignLeft =
        Command(
            text = resourceBundle.getString("Justify.left"),
            icon = format_justify_left(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Left),
            onTriggerActionToggleSelectedChange = {
                if (it) onAlignmentChanged(CommandDemoAlignment.Left)
            }
        )
    val commandAlignRight =
        Command(
            text = resourceBundle.getString("Justify.right"),
            icon = format_justify_right(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Right),
            onTriggerActionToggleSelectedChange = {
                if (it) onAlignmentChanged(CommandDemoAlignment.Right)
            }
        )
    val commandAlignFill =
        Command(
            text = resourceBundle.getString("Justify.fill"),
            icon = format_justify_fill(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = (alignment == CommandDemoAlignment.Fill),
            onTriggerActionToggleSelectedChange = {
                if (it) onAlignmentChanged(CommandDemoAlignment.Fill)
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
    resourceBundle: ResourceBundle
) {
    val commandCut =
        Command(
            text = resourceBundle.getString("Edit.cut.text"),
            icon = content_cut_black_24dp(),
            isActionEnabled = actionEnabled,
            action = { println("Cut!") }

        )
    val commandCopy =
        Command(
            text = resourceBundle.getString("Edit.copy.text"),
            icon = content_copy_black_24dp(),
            isActionEnabled = actionEnabled,
            action = { println("Copy!") }

        )

    var togglePasteText by remember { mutableStateOf(false) }
    val commandPasteTextOnly = Command(
        text = resourceBundle.getString("Edit.paste.textOnlyText"),
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
            text = resourceBundle.getString("Edit.paste.text"),
            icon = content_paste_black_24dp(),
            isActionEnabled = actionEnabled,
            action = { println("Paste!") },
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(
                    commands = listOf(
                        Command(
                            text = resourceBundle.getString("Edit.paste.keepFormattingText"),
                            action = { println("Paste with keep formatting") }),
                        Command(
                            text = resourceBundle.getString("Edit.paste.mergeFormattingText"),
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
            commandPasteTextOnly to BaseCommandButtonPresentationModel.Overlay(
                toDismissPopupsOnActivation = false
            ),
            commandPaste to BaseCommandButtonPresentationModel.Overlay(
                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                    panelPresentationModel = CommandPopupMenuPanelPresentationModel(
                        layoutSpec = MenuPopupPanelLayoutSpec(columnCount = 5, visibleRowCount = 3),
                        contentPadding = PaddingValues(0.dp),
                        showGroupLabels = true,
                        commandPresentationState = CommandButtonPresentationState.BigFitToIcon,
                        commandIconDimension = DpSize(24.dp, 24.dp),
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                )
            )
        )
    ).project()
}

@Composable
fun CommandOrientationStyleStrip(
    resourceBundle: ResourceBundle,
    enabled: Boolean,
    orientation: StripOrientation,
    style: CommandDemoStyle,
    onStyleChanged: (CommandDemoStyle) -> Unit,
    horizontalGapScaleFactor: Float
) {
    val commandBold =
        Command(
            text = resourceBundle.getString("FontStyle.bold.title"),
            icon = format_bold_black_24dp(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.bold,
            onTriggerActionToggleSelectedChange = {
                onStyleChanged(style.copy(bold = it))
                println("Selected bold? $it")
            }
        )
    val commandItalic =
        Command(
            text = resourceBundle.getString("FontStyle.italic.title"),
            icon = format_italic_black_24dp(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.italic,
            onTriggerActionToggleSelectedChange = {
                onStyleChanged(style.copy(italic = it))
                println("Selected italic? $it")
            }
        )
    val commandUnderline =
        Command(
            text = resourceBundle.getString("FontStyle.underline.title"),
            icon = format_underlined_black_24dp(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.underline,
            onTriggerActionToggleSelectedChange = {
                onStyleChanged(style.copy(underline = it))
                println("Selected underline? $it")
            }
        )
    val commandStrikethrough =
        Command(
            text = resourceBundle.getString("FontStyle.strikethrough.title"),
            icon = format_strikethrough_black_24dp(),
            isActionEnabled = enabled,
            isActionToggle = true,
            isActionToggleSelected = style.strikethrough,
            onTriggerActionToggleSelectedChange = {
                onStyleChanged(style.copy(strikethrough = it))
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

private fun getPopupMenuContentModel(resourceBundle: ResourceBundle): CommandMenuContentModel {
    val mf = MessageFormat(resourceBundle.getString("TestMenuItem.text"))
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
fun AuroraApplicationScope.OrientationCommandContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
) {
    var actionEnabled by remember { mutableStateOf(true) }
    var popupEnabled by remember { mutableStateOf(true) }

    val actionCommand =
        Command(
            text = resourceBundle.getString("Edit.paste.text"),
            extraText = resourceBundle.getString("Edit.paste.textExtra"),
            icon = edit_paste(),
            action = { println("Paste activated!") },
            isActionEnabled = actionEnabled
        )

    val splitMainActionCommand =
        Command(
            text = resourceBundle.getString("Edit.copy.text"),
            extraText = resourceBundle.getString("Edit.copy.textExtra"),
            icon = edit_copy(),
            action = { println("Copy activated!") },
            isActionEnabled = actionEnabled,
            secondaryContentModel = getPopupMenuContentModel(resourceBundle),
            isSecondaryEnabled = popupEnabled
        )

    val splitMainPopupCommand =
        Command(
            text = resourceBundle.getString("Edit.cut.text"),
            extraText = resourceBundle.getString("Edit.cut.textExtra"),
            icon = edit_cut(),
            action = { println("Cut activated!") },
            isActionEnabled = actionEnabled,
            secondaryContentModel = getPopupMenuContentModel(resourceBundle),
            isSecondaryEnabled = popupEnabled
        )

    val popupCommand =
        Command(
            text = resourceBundle.getString("Edit.selectAll.text"),
            extraText = resourceBundle.getString("Edit.selectAll.textExtra"),
            icon = edit_select_all(),
            secondaryContentModel = getPopupMenuContentModel(resourceBundle),
            isSecondaryEnabled = popupEnabled
        )

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

    Row(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Box(modifier = Modifier.padding(8.dp)) {
            CommandOrientationJustifyStrip(
                enabled = actionEnabled,
                orientation = StripOrientation.Vertical,
                alignment = alignment,
                onAlignmentChanged = { alignment = it },
                horizontalGapScaleFactor = 0.7f,
                resourceBundle = resourceBundle
            )
        }
        Box(modifier = Modifier.padding(8.dp)) {
            CommandOrientationStyleStrip(
                resourceBundle = resourceBundle,
                enabled = actionEnabled,
                orientation = StripOrientation.Vertical,
                style = style,
                onStyleChanged = { style = it },
                horizontalGapScaleFactor = 0.7f
            )
        }

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
                AuroraDecorationArea(
                    decorationAreaType = DecorationAreaType.None,
                    buttonShaper = ClassicButtonShaper.Instance
                ) {
                    AuroraSkinSwitcher(onSkinChange)

                    Spacer(modifier = Modifier.width(8.dp))

                    AuroraLocaleSwitcher(resourceBundle)
                }
            }

            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = resourceBundle.getString("Action.enabled"),
                    selected = actionEnabled,
                    onClick = { actionEnabled = !actionEnabled }
                )).project()
                Spacer(modifier = Modifier.width(8.dp))
                CheckBoxProjection(contentModel = SelectorContentModel(
                    text = resourceBundle.getString("Popup.enabled"),
                    selected = popupEnabled,
                    onClick = { popupEnabled = !popupEnabled }
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
                    onAlignmentChanged = { alignment = it },
                    horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis,
                    resourceBundle = resourceBundle
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommandOrientationStyleStrip(
                    resourceBundle = resourceBundle,
                    enabled = actionEnabled,
                    orientation = StripOrientation.Horizontal,
                    style = style,
                    onStyleChanged = { style = it },
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



