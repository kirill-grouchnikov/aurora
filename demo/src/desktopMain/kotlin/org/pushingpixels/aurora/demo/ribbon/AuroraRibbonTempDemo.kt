/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo.ribbon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skia.Color4f
import org.jetbrains.skia.Font
import org.jetbrains.skia.TextLine
import org.jetbrains.skia.Typeface
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizePolicies
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizeSequencingPolicies
import org.pushingpixels.aurora.demo.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*
import javax.swing.JColorChooser
import kotlin.system.exitProcess

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 480.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }
    val builder = RibbonBuilderTemp(resourceBundle)

    AuroraWindow(
        skin = skin,
        title = "Aurora Ribbon Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row {
               builder.getApplicationMenuCommandButtonProjection().project()
            }
        }
    }

}

private class RibbonBuilderTemp(val resourceBundle: ResourceBundle) {
    val mf = MessageFormat(resourceBundle.getString("TestMenuItem.text"))
    val popupCommand1 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorSolidIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand2 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorSolidIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand3 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorSolidIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand4 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorSolidIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand5 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorSolidIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )

    val cutCommand = Command(
        text = resourceBundle.getString("Edit.cut.text"),
        icon = edit_cut(),
        action = { println("Cut!") },
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("Edit.cut.text"),
            descriptionSections = listOf(resourceBundle.getString("Cut.tooltip.actionParagraph1"))
        ),
        secondaryContentModel = getSimpleMenuModel()
    )

    val copyCommand = Command(
        text = resourceBundle.getString("Edit.copy.text"),
        icon = edit_copy(),
        action = { println("Copy!") },
        secondaryContentModel = getSimpleMenuModel()
    )

    val pasteCommand = Command(
        text = resourceBundle.getString("Edit.paste.text"),
        icon = edit_paste(),
        action = { println("Pasted!") },
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("Edit.paste.text"),
            descriptionSections = listOf(resourceBundle.getString("Paste.tooltip.actionParagraph1"))
        ),
        secondaryContentModel = getSimpleMenuModel(),
        secondaryRichTooltip = RichTooltip(
            title = resourceBundle.getString("Edit.paste.text"),
            descriptionSections = listOf(resourceBundle.getString("Paste.tooltip.popupParagraph1"))
        ),
    )

    val menuSaveSelection = Command(
        text = resourceBundle.getString("Format.menuSaveSelection.text"),
        icon = ColorSolidIcon(Color(red = 0xFB, green = 0xC0, blue = 0x2D)),
        action = { println("Save Selection activated") }
    )

    val menuClearSelection = Command(
        text = resourceBundle.getString("Format.menuClearSelection.text"),
        icon = ColorSolidIcon(Color(red = 0xFF, green = 0xA0, blue = 0x00)),
        action = { println("Clear Selection activated") }
    )

    val applyStyles = Command(
        text = resourceBundle.getString("Format.applyStyles.text"),
        icon = ColorSolidIcon(Color(red = 0xF5, green = 0x7C, blue = 0x00)),
        action = { println("Apply Styles activated") }
    )

    val alignLeftCommand = Command(
        text = "",
        icon = format_justify_left(),
        isActionToggle = true,
        action = {}
    )

    val alignCenterCommand = Command(
        text = "",
        icon = format_justify_center(),
        isActionToggle = true,
        action = {}
    )

    val alignRightCommand = Command(
        text = "",
        icon = format_justify_right(),
        isActionToggle = true,
        action = {}
    )

    val alignFillCommand = Command(
        text = "",
        icon = format_justify_fill(),
        isActionToggle = true,
        action = {}
    )

    val styleBoldCommand = Command(
        text = "",
        icon = format_text_bold(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontStyle.bold.title"),
            descriptionSections = listOf(resourceBundle.getString("FontBold.tooltip.textActionParagraph1"))
        )
    )

    val styleItalicCommand = Command(
        text = "",
        icon = format_text_italic(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontStyle.italic.title"),
            descriptionSections = listOf(resourceBundle.getString("FontItalic.tooltip.textActionParagraph1"))
        )
    )

    val styleUnderlineCommand = Command(
        text = "",
        icon = format_text_underline(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontStyle.underline.title"),
            descriptionSections = listOf(resourceBundle.getString("FontUnderline.tooltip.textActionParagraph1"))
        )
    )

    val styleStrikethroughCommand = Command(
        text = "",
        icon = format_text_strikethrough(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontStyle.strikethrough.title"),
            descriptionSections = listOf(resourceBundle.getString("FontStrikethrough.tooltip.textActionParagraph1"))
        )
    )

    var mfButtonText = MessageFormat(
        resourceBundle.getString("StylesGallery.textButton")
    )
    val overlayFont = Font(Typeface.makeDefault())
    val stylesGalleryCommandList = CommandGroup(
        title = resourceBundle.getString("StylesGallery.textGroupTitle1"),
        commands = (0..10).map { index ->
            Command(
                text = mfButtonText.format(arrayOf(index)),
                icon = DecoratedIcon(main = font_x_generic(),
                    decoration = object : Painter() {
                        override val intrinsicSize: Size = Size.Unspecified

                        override fun DrawScope.onDraw() {
                            this.drawIntoCanvas { canvas ->
                                val nativeCanvas = canvas.nativeCanvas
                                nativeCanvas.drawTextLine(
                                    line = TextLine.make(
                                        text = "$index",
                                        font = overlayFont
                                    ),
                                    x = 2.0f,
                                    y = size.height - overlayFont.metrics.height - 2.0f,
                                    paint = org.jetbrains.skia.Paint().also { skiaPaint ->
                                        skiaPaint.color4f = Color4f(
                                            r = 0f,
                                            g = 0f,
                                            b = 0f,
                                            a = 1.0f
                                        )
                                    }
                                )
                            }
                        }
                    }),
                isActionToggle = true
            )
        }
    )
    val stylesGalleryCommandList2 = CommandGroup(
        title = resourceBundle.getString("StylesGallery.textGroupTitle1"),
        commands = (11..30).map { index ->
            Command(
                text = mfButtonText.format(arrayOf(index)),
                icon = DecoratedIcon(main = font_x_generic(),
                    decoration = object : Painter() {
                        override val intrinsicSize: Size = Size.Unspecified

                        override fun DrawScope.onDraw() {
                            this.drawIntoCanvas { canvas ->
                                val nativeCanvas = canvas.nativeCanvas
                                nativeCanvas.drawTextLine(
                                    line = TextLine.make(
                                        text = "$index",
                                        font = overlayFont
                                    ),
                                    x = 2.0f,
                                    y = size.height - overlayFont.metrics.height - 2.0f,
                                    paint = org.jetbrains.skia.Paint().also { skiaPaint ->
                                        skiaPaint.color4f = Color4f(
                                            r = 0f,
                                            g = 0f,
                                            b = 0f,
                                            a = 1.0f
                                        )
                                    }
                                )
                            }
                        }
                    }),
                isActionToggle = true
            )
        }
    )

    val styleGalleryContentModel = RibbonGalleryContentModel(
        icon = font_x_generic(),
        commandGroups = listOf(stylesGalleryCommandList, stylesGalleryCommandList2),
        selectedCommand = stylesGalleryCommandList.commands[1],
        extraPopupGroups = listOf(
            CommandGroup(commands = listOf(this.menuSaveSelection, this.menuClearSelection)),
            CommandGroup(commands = listOf(this.applyStyles))
        ),
        commandAction = {
            val text = it?.text ?: "[null]"
            println("Command '$text' activated!")
        },
        commandActionPreview = object : CommandActionPreview {
            override fun onCommandPreviewActivated(command: BaseCommand) {
                println("Preview activated for '${command.text}'")
            }

            override fun onCommandPreviewCanceled(command: BaseCommand) {
                println("Preview canceled for '${command.text}'")
            }
        }
    )

    val fontFamilyComboBoxEntries = listOf(
        "Calibri", "Columbus",
        "Consolas", "Cornelius",
        "Cleopatra", "Cornucopia",
        "California", "Calendula",
        "Coriander", "Callisto",
        "Cajun", "Congola",
        "Candella", "Cambria"
    )

    val fontSizeComboBoxEntries = listOf(11, 12, 13, 14, 16)

    val applicationGamesEntries = listOf("Tetris", "Minesweeper", "Doom")
    val applicationInternetEntries = listOf("Firefox", "Opera", "Konqueror")
    val applicationMultimediaEntries = listOf(
        resourceBundle.getString("Pictures.text"),
        resourceBundle.getString("Video.text"),
        resourceBundle.getString("Audio.text")
    )

    val amEntryPrintMemo = Command(
        text = resourceBundle.getString("AppMenuPrint.memo.text"),
        icon = text_x_generic(),
        action = { println("Invoked memo") }
    )

    val amEntryPrintCustom = Command(
        text = resourceBundle.getString("AppMenuPrint.custom.text"),
        icon = text_x_generic(),
        action = { println("Invoked custom") }
    )

    val amEntrySendMail = Command(
        text = resourceBundle.getString("AppMenuSend.email.text"),
        extraText = resourceBundle.getString("AppMenuSend.email.description"),
        icon = mail_message_new(),
        action = { println("Invoked email") }
    )

    val amEntrySendHtml = Command(
        text = resourceBundle.getString("AppMenuSend.html.text"),
        icon = text_x_generic(),
        extraText = resourceBundle.getString("AppMenuSend.html.description"),
        action = { println("Invoked HTML") }
    )

    val amEntrySendDoc = Command(
        text = resourceBundle.getString("AppMenuSend.word.text"),
        icon = x_office_document(),
        extraText = resourceBundle.getString("AppMenuSend.word.description"),
        action = { println("Invoked Word") }
    )

    val amWirelessWiFi = Command(
        text = resourceBundle.getString("AppMenuSend.wireless.wifi.text"),
        icon = EmptyIcon(),
        action = { println("WiFi activated") }
    )

    val amWirelessBluetooth = Command(
        text = resourceBundle.getString("AppMenuSend.wireless.bluetooth.text"),
        icon = network_wireless(),
        action = { println("Bluetooth activated") }
    )

    val wirelessPopupMenuContentModel = CommandMenuContentModel(
        CommandGroup(commands = listOf(amWirelessWiFi, amWirelessBluetooth))
    )

    val amEntrySendWireless = Command(
        text = resourceBundle.getString("AppMenuSend.wireless.text"),
        icon = network_wireless(),
        extraText = resourceBundle.getString("AppMenuSend.wireless.description"),
        secondaryContentModel = wirelessPopupMenuContentModel
    )

    val sendMenu = CommandMenuContentModel(
        CommandGroup(
            title = resourceBundle.getString("AppMenuSend.secondary.textGroupTitle1"),
            commands = listOf(amEntrySendMail, amEntrySendHtml, amEntrySendDoc, amEntrySendWireless)
        )
    )

    val amEntrySend = Command(
        text = resourceBundle.getString("AppMenuSend.text"),
        icon = mail_forward(),
        secondaryContentModel = sendMenu
    )

    val amEntrySaveAsWord = Command(
        text = resourceBundle.getString("AppMenuSaveAs.word.text"),
        icon = x_office_document(),
        extraText = resourceBundle.getString("AppMenuSaveAs.word.description"),
        action = { println("Invoked saved as Word") }
    )

    val amEntrySaveAsHtml = Command(
        text = resourceBundle.getString("AppMenuSaveAs.html.text"),
        icon = text_x_generic(),
        extraText = resourceBundle.getString("AppMenuSaveAs.html.description"),
        action = { println("Invoked saved as HTML") },
        isActionEnabled = false
    )

    val amEntrySaveAsOtherFormats = Command(
        text = resourceBundle.getString("AppMenuSaveAs.other.text"),
        icon = document_save_as(),
        extraText = resourceBundle.getString("AppMenuSaveAs.other.description"),
        action = { println("Invoked saved as other") }
    )

    var saveAsMenu = CommandMenuContentModel(
        CommandGroup(
            title = resourceBundle.getString("AppMenuSaveAs.secondary.textGroupTitle1"),
            commands = listOf(amEntrySaveAsWord, amEntrySaveAsHtml, amEntrySaveAsOtherFormats)
        )
    )

    val amEntrySaveAs = Command(
        text = resourceBundle.getString("AppMenuSaveAs.text"),
        icon = document_save_as(),
        action = { println("Invoked saving document as") },
        secondaryContentModel = saveAsMenu
    )

    val amEntryExit = Command(
        text = resourceBundle.getString("AppMenuExit.text"),
        icon = system_log_out(),
        action = { exitProcess(0) }
    )

    val amFooterProps = Command(
        text = resourceBundle.getString("AppMenuOptions.text"),
        icon = document_properties(),
        action = { println("Invoked Options") }
    )

    fun getSimpleMenuModel(): CommandMenuContentModel {
        return CommandMenuContentModel(
            groups = listOf(
                CommandGroup(
                    title = null,
                    commands = listOf(this.popupCommand1, this.popupCommand2, this.popupCommand3)
                ),
                CommandGroup(
                    title = null,
                    commands = listOf(this.popupCommand4, this.popupCommand5)
                )
            )
        )
    }

    @Composable
    fun getApplicationMenuCommandButtonProjection(): RibbonApplicationMenuCommandButtonProjection {
        val overlays = hashMapOf<Command, CommandButtonPresentationModel.Overlay>()
        val secondaryStates = hashMapOf<Command, CommandButtonPresentationState>()

        val mf = MessageFormat(resourceBundle.getString("TestMenuItem.text"))
        val popupCommand1 = Command(
            text = mf.format(arrayOf("1")),
            icon = ColorSolidIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
            action = { println("Test menu item 1 activated") }
        )

        // "Create new" primary
        val defaultCommands: MutableList<Command> = mutableListOf()
        val mfDefault = MessageFormat(resourceBundle.getString("AppMenu.default.textButton"))
        for (i in 0..4) {
            val command = Command(
                text = mfDefault.format(arrayOf("$i")),
                icon = text_x_generic(),
                action = { println("Creating $i") }
            )
            defaultCommands.add(command)
        }

        val newMenu = CommandMenuContentModel(
            CommandGroup(
                title = resourceBundle.getString("AppMenu.default.textGroupTitle1"),
                commands = defaultCommands
            )
        )

        val amEntryNew = Command(
            text = resourceBundle.getString("AppMenuNew.text"),
            icon = document_new(),
            action = { println("Invoked creating new document") },
            secondaryContentModel = newMenu
        )

        overlays[amEntryNew] = CommandButtonPresentationModel.Overlay(
            actionKeyTip = "N",
            textClick = TextClick.Action
        )
        secondaryStates[amEntryNew] = CommandButtonPresentationState.Medium

        // "Open" primary
        val historyCommands: MutableList<Command> = mutableListOf()
        val mfOpen = MessageFormat(resourceBundle.getString("AppMenuOpen.secondary.textButton"))
        for (i in 0..4) {
            val command = Command(
                text = mfOpen.format(arrayOf("$i")),
                icon = text_x_generic(),
                action = { println("Opening $i") }
            )
            historyCommands.add(command)
        }

        val historyOpenMenu = CommandMenuContentModel(
            CommandGroup(
                resourceBundle.getString("AppMenuOpen.secondary.textGroupTitle1"),
                historyCommands
            )
        )

        val amEntryOpen = Command(
            text = resourceBundle.getString("AppMenuOpen.text"),
            icon = document_open(),
            action = { println("Invoked opening document") },
            secondaryContentModel = historyOpenMenu
        )

        overlays[amEntryOpen] = CommandButtonPresentationModel.Overlay(
            presentationState = CommandButtonPresentationState.Medium,
            actionKeyTip = "O",
            textClick = TextClick.Action
        )
        secondaryStates[amEntryOpen] = CommandButtonPresentationState.Medium

        // "Save" primary
        val amEntrySave = Command(
            text = resourceBundle.getString("AppMenuSave.text"),
            icon = document_save(),
            action = { println("Invoked saving document") },
            isActionEnabled = false
        )
        overlays[amEntrySave] = CommandButtonPresentationModel.Overlay(actionKeyTip = "S")

        // "Save as" primary + secondaries
        overlays[amEntrySaveAsWord] = CommandButtonPresentationModel.Overlay(actionKeyTip = "W")
        overlays[amEntrySaveAsHtml] = CommandButtonPresentationModel.Overlay(actionKeyTip = "H")
        overlays[amEntrySaveAsOtherFormats] = CommandButtonPresentationModel.Overlay(actionKeyTip = "O")
        overlays[amEntrySaveAs] = CommandButtonPresentationModel.Overlay(
            actionKeyTip = "W",
            popupKeyTip = "F",
            textClick = TextClick.Action,
        )
        secondaryStates[amEntrySaveAs] = RibbonApplicationMenuButtonPresentationStates.RibbonAppMenuSecondaryLevel

        // "Print" primary + secondaries
        val amEntryPrintSelect = Command(
            text = resourceBundle.getString("AppMenuPrint.print.text"),
            icon = printer(),
            extraText = resourceBundle.getString("AppMenuPrint.print.description"),
            action = { println("Invoked print") }
        )

        val amEntryPrintDefault = Command(
            text = resourceBundle.getString("AppMenuPrint.quick.text"),
            icon = printer(),
            extraText = resourceBundle.getString("AppMenuPrint.quick.description"),
            action = { println("Invoked quick") }
        )

        val amEntryPrintPreview = Command(
            text = resourceBundle.getString("AppMenuPrint.preview.text"),
            icon = document_print_preview(),
            extraText = resourceBundle.getString("AppMenuPrint.preview.description"),
            action = { println("Invoked preview") }
        )

        overlays[amEntryPrintSelect] = CommandButtonPresentationModel.Overlay(actionKeyTip = "P")
        overlays[amEntryPrintDefault] = CommandButtonPresentationModel.Overlay(actionKeyTip = "Q")
        overlays[amEntryPrintPreview] = CommandButtonPresentationModel.Overlay(actionKeyTip = "V")
        overlays[amEntryPrintMemo] = CommandButtonPresentationModel.Overlay(actionKeyTip = "M")
        overlays[amEntryPrintCustom] = CommandButtonPresentationModel.Overlay(actionKeyTip = "C")

        val printMenu = CommandMenuContentModel(
            groups = listOf(
                CommandGroup(
                    title = resourceBundle.getString("AppMenuPrint.secondary.textGroupTitle1"),
                    commands = listOf(amEntryPrintSelect, amEntryPrintDefault, amEntryPrintPreview)
                ),
                CommandGroup(
                    title = resourceBundle.getString("AppMenuPrint.secondary.textGroupTitle2"),
                    commands = listOf(amEntryPrintMemo, amEntryPrintCustom)
                )
            )
        )

        val amEntryPrint = Command(
            text = resourceBundle.getString("AppMenuPrint.text"),
            icon = document_print(),
            action = { println("Invoked printing as") },
            secondaryContentModel = printMenu
        )

        secondaryStates[amEntryPrint] = RibbonApplicationMenuButtonPresentationStates.RibbonAppMenuSecondaryLevel
        overlays[amEntryPrint] = CommandButtonPresentationModel.Overlay(
            actionKeyTip = "P",
            popupKeyTip = "W",
            textClick = TextClick.Action
        )

        // "Send" primary + secondaries
        overlays[amEntrySendMail] = CommandButtonPresentationModel.Overlay(actionKeyTip = "E")
        overlays[amEntrySendHtml] = CommandButtonPresentationModel.Overlay(actionKeyTip = "H")
        overlays[amEntrySendDoc] = CommandButtonPresentationModel.Overlay(actionKeyTip = "W")

        overlays[amWirelessWiFi] = CommandButtonPresentationModel.Overlay(actionKeyTip = "W")
        overlays[amWirelessBluetooth] = CommandButtonPresentationModel.Overlay(actionKeyTip = "B")

        secondaryStates[amEntrySendWireless] = RibbonApplicationMenuButtonPresentationStates.RibbonAppMenuSecondaryLevel
        overlays[amEntrySendWireless] = CommandButtonPresentationModel.Overlay(popupKeyTip = "X")

        secondaryStates[amEntrySend] = RibbonApplicationMenuButtonPresentationStates.RibbonAppMenuSecondaryLevel
        overlays[amEntrySend] = CommandButtonPresentationModel.Overlay(popupKeyTip = "D")

        overlays[amEntryExit] = CommandButtonPresentationModel.Overlay(popupKeyTip = "X")

        val applicationMenu = RibbonApplicationMenuContentModel(
            groups = listOf(
                CommandGroup(commands = listOf(amEntryNew, amEntryOpen, amEntrySave, amEntrySaveAs)),
                CommandGroup(commands = listOf(amEntryPrint, amEntrySend)),
                CommandGroup(commands = listOf(amEntryExit))
            ),
            footerCommands = CommandGroup(commands = listOf(amFooterProps))
        )

        overlays[amFooterProps] = CommandButtonPresentationModel.Overlay(actionKeyTip = "T")

        val tooltipImage = painterResource("/org/pushingpixels/aurora/demo/appmenubutton-tooltip-main.png")
        val tooltipImageRatio =
            tooltipImage.intrinsicSize.width / tooltipImage.intrinsicSize.height
        val tooltipImageScaledSize = DpSize(160.dp, 160.dp / tooltipImageRatio)

        return RibbonApplicationMenuCommandButtonProjection(
            contentModel = RibbonApplicationMenuCommand(
                text = resourceBundle.getString("AppMenu.title"),
                secondaryRichTooltip = RichTooltip(
                    title = resourceBundle.getString("AppMenu.tooltip.title"),
                    descriptionSections = listOf(resourceBundle.getString("AppMenu.tooltip.paragraph1")),
                    mainIcon = null,
                    footerIcon = help_browser(),
                    footerSections = listOf(resourceBundle.getString("AppMenu.tooltip.footer1"))
                ),
                secondaryContentModel = applicationMenu
            ),
            presentationModel = RibbonApplicationMenuCommandButtonPresentationModel(
                popupKeyTip = "F",
                popupRichTooltipPresentationModel = RichTooltipPresentationModel(
                    mainIconSize = tooltipImageScaledSize,
                ),
                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                    menuPresentationState = CommandButtonPresentationState.Tile
                )
            ),
            overlays = overlays,
            secondaryStates = secondaryStates
        )
    }
}


