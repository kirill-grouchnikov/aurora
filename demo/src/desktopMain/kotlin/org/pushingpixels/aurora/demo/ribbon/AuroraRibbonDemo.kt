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
package org.pushingpixels.aurora.demo.ribbon

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
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
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizePolicies
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizeSequencingPolicies
import org.pushingpixels.aurora.demo.ColorSolidIcon
import org.pushingpixels.aurora.demo.DecoratedIcon
import org.pushingpixels.aurora.demo.getQuickStylesContentModel
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(500.dp, 400.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    val builder = RibbonBuilder(resourceBundle)
    val clipboardBand = builder.getClipboardBand()
    val quickStylesBand = builder.getQuickStylesBand()
    val fontBand = builder.getFontBand()
    val documentBand = builder.getDocumentBand()
    val findBand = builder.getFindBand()

    val pageLayoutTask = RibbonTask(
        title = resourceBundle.getString("PageLayout.textTaskTitle"),
        bands = listOf(clipboardBand, quickStylesBand, fontBand, documentBand, findBand),
        resizeSequencingPolicy = CoreRibbonResizeSequencingPolicies.RoundRobin(),
        keyTip = "P"
    )

    val actionBand = builder.getActionBand()
    val preferencesBand = builder.getPreferencesBand()
    val applicationsBand = builder.getApplicationsBand()

    val writeTask = RibbonTask(
        title = resourceBundle.getString("Write.textTaskTitle"),
        bands = listOf(actionBand, preferencesBand, applicationsBand),
        resizeSequencingPolicy = CoreRibbonResizeSequencingPolicies.RoundRobin(),
        keyTip = "W"
    )
}

private class RibbonBuilder(val resourceBundle: ResourceBundle) {
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
        text = resourceBundle.getString("Cut.text"),
        icon = edit_cut(),
        action = { println("Cut!") },
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("Cut.text"),
            descriptionSections = listOf(resourceBundle.getString("Cut.tooltip.actionParagraph1"))
        ),
        secondaryContentModel = getSimpleMenuModel()
    )

    val copyCommand = Command(
        text = resourceBundle.getString("Copy.text"),
        icon = edit_copy(),
        action = { println("Copy!") },
        secondaryContentModel = getSimpleMenuModel()
    )

    val pasteCommand = Command(
        text = resourceBundle.getString("Paste.text"),
        icon = edit_paste(),
        action = { println("Pasted!") },
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("Paste.text"),
            descriptionSections = listOf(resourceBundle.getString("Paste.tooltip.actionParagraph1"))
        ),
        secondaryContentModel = getSimpleMenuModel(),
        secondaryRichTooltip = RichTooltip(
            title = resourceBundle.getString("Paste.text"),
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
            title = resourceBundle.getString("FontBold.tooltip.textActionTitle"),
            descriptionSections = listOf(resourceBundle.getString("FontBold.tooltip.textActionParagraph1"))
        )
    )

    val styleItalicCommand = Command(
        text = "",
        icon = format_text_italic(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontItalic.tooltip.textActionTitle"),
            descriptionSections = listOf(resourceBundle.getString("FontItalic.tooltip.textActionParagraph1"))
        )
    )

    val styleUnderlineCommand = Command(
        text = "",
        icon = format_text_underline(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontUnderline.tooltip.textActionTitle"),
            descriptionSections = listOf(resourceBundle.getString("FontUnderline.tooltip.textActionParagraph1"))
        )
    )

    val styleStrikethroughCommand = Command(
        text = "",
        icon = format_text_strikethrough(),
        isActionToggle = true,
        action = {},
        actionRichTooltip = RichTooltip(
            title = resourceBundle.getString("FontStrikethrough.tooltip.textActionTitle"),
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
            override fun onCommandPreviewActivated(command: Command) {
                println("Preview activated for '${command.text}'")
            }

            override fun onCommandPreviewCanceled(command: Command) {
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

    fun getClipboardBand(): RibbonBand {
        val formatCommand = Command(
            text = resourceBundle.getString("Format.text"),
            icon = edit_paste(),
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(commands = listOf(this.menuSaveSelection, this.menuClearSelection)),
                panelContentModel = getQuickStylesContentModel(resourceBundle)
            ),
            secondaryRichTooltip = RichTooltip(
                title = "Main title that can go over multiple lines of text even exceeding the bigger",
                descriptionSections = listOf(
                    "Simple description that can go over multiple lines of text even exceeding the bigger",
                    "Second paragraph that can be multiline as well to test this feature"
                ),
                mainIcon = address_book_new(),
                footerIcon = help_browser()
            )
        )

        return RibbonBand(
            title = resourceBundle.getString("Clipboard.textBandTitle"),
            icon = edit_paste(),
            expandCommand = Command(
                text = "",
                icon = null,
                action = { println("Expand button clicked! ") },
                actionRichTooltip = RichTooltip(
                    title = resourceBundle.getString("Clipboard.textBandTitle"),
                    descriptionSections = listOf(resourceBundle.getString("Clipboard.textBandTooltipParagraph1"))
                )
            ),
            expandCommandKeyTip = "Q",
            collapsedStateKeyTip = "ZC",
            groups = listOf(
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = pasteCommand,
                            presentationModel = CommandButtonPresentationModel(
                                actionKeyTip = "Y",
                                popupKeyTip = "V",
                                textClick = TextClick.Action
                            ),
                            overlays = mapOf(
                                popupCommand1 to CommandButtonPresentationModel.Overlay(popupKeyTip = "1"),
                                popupCommand2 to CommandButtonPresentationModel.Overlay(popupKeyTip = "2"),
                                popupCommand3 to CommandButtonPresentationModel.Overlay(popupKeyTip = "3"),
                                popupCommand4 to CommandButtonPresentationModel.Overlay(popupKeyTip = "4"),
                                popupCommand5 to CommandButtonPresentationModel.Overlay(popupKeyTip = "5"),
                            )
                        ) at PresentationPriority.Top,
                        CommandButtonProjection(
                            contentModel = cutCommand,
                            presentationModel = CommandButtonPresentationModel(
                                popupKeyTip = "X",
                                textClick = TextClick.Action
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = copyCommand,
                            presentationModel = CommandButtonPresentationModel(
                                popupKeyTip = "C",
                                textClick = TextClick.Popup
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = formatCommand,
                            presentationModel = CommandButtonPresentationModel(
                                popupKeyTip = "FP",
                                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                                    panelPresentationModel = CommandPopupMenuPanelPresentationModel(
                                        layoutSpec = MenuPopupPanelLayoutSpec(columnCount = 5, visibleRowCount = 3),
                                        showGroupLabels = false,
                                        commandPresentationState = CommandButtonPresentationState.BigFitToIcon,
                                        commandIconDimension = 48.dp
                                    )
                                )
                            ),
                            overlays = mapOf(
                                this.menuSaveSelection to CommandButtonPresentationModel.Overlay(actionKeyTip = "SS"),
                                this.menuClearSelection to CommandButtonPresentationModel.Overlay(actionKeyTip = "SC"),
                                this.applyStyles to CommandButtonPresentationModel.Overlay(actionKeyTip = "SA")
                            )
                        ) at PresentationPriority.Medium
                    )
                )
            )
        )
    }

    fun getQuickStylesBand(): RibbonBand {
        return RibbonBand(
            title = resourceBundle.getString("QuickStyles.textBandTitle"),
            icon = preferences_desktop_theme(),
            collapsedStateKeyTip = "ZS",
            resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(),
            groups = listOf(
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("Styles1.text"),
                                icon = font_x_generic(),
                                action = { println("Generic activated") }
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                actionKeyTip = "SA"
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("Styles2.text"),
                                icon = image_x_generic(),
                                action = { println("Image activated") }
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                actionKeyTip = "SB"
                            )
                        ) at PresentationPriority.Medium
                    ),
                    galleryProjections = listOf(
                        RibbonGalleryProjection(
                            contentModel = styleGalleryContentModel,
                            presentationModel = RibbonGalleryPresentationModel(
                                preferredVisibleCommandCounts = mapOf(
                                    PresentationPriority.Low to 1,
                                    PresentationPriority.Medium to 2,
                                    PresentationPriority.Top to 2
                                ),
                                popupLayoutSpec = MenuPopupPanelLayoutSpec(
                                    columnCount = 3, visibleRowCount = 3
                                ),
                                commandButtonPresentationState = RibbonBandCommandButtonPresentationStates.BigFixedLandscape,
                                expandKeyTip = "L"
                            )
                        ) at PresentationPriority.Top
                    )
                )
            )
        )
    }

    @Composable
    fun getFontBand(): FlowRibbonBand {
        val fontFamilyComboSelectedItem = remember { mutableStateOf(this.fontFamilyComboBoxEntries[0]) }
        val fontFamilyComboBoxContentModel = ComboBoxContentModel(
            items = this.fontFamilyComboBoxEntries,
            selectedItem = fontFamilyComboSelectedItem.value,
            onTriggerItemSelectedChange = {
                fontFamilyComboSelectedItem.value = it
                println("New font family selection -> $it")
            },
            richTooltip = RichTooltip(title = resourceBundle.getString("Fonts.tooltip.title")),
        )

        val fontSizeComboSelectedItem = remember { mutableStateOf(this.fontSizeComboBoxEntries[0]) }
        val fontSizeComboBoxContentModel = ComboBoxContentModel(
            items = this.fontSizeComboBoxEntries,
            selectedItem = fontSizeComboSelectedItem.value,
            onTriggerItemSelectedChange = {
                fontSizeComboSelectedItem.value = it
                println("New font size selection -> $it")
            }
        )

        val indentLeft = Command(
            text = "",
            icon = format_indent_less(),
            action = { println("<- Left") }
        )
        val indentRight = Command(
            text = "",
            icon = format_indent_more(),
            action = { println("-> Right") }
        )

        return FlowRibbonBand(
            title = resourceBundle.getString("Font.textBandTitle"),
            icon = preferences_desktop_font(),
            collapsedStateKeyTip = "ZF",
            expandCommandKeyTip = "FN",
            expandCommand = Command(
                text = "",
                icon = null,
                action = { println("Expand button clicked! ") }
            ),
            flowComponentProjections = listOf(
                ComboBoxProjection(
                    contentModel = fontFamilyComboBoxContentModel,
                    presentationModel = ComboBoxPresentationModel(displayConverter = { "+ Minor ($it)   " }),
                ) with RibbonComponentPresentationModel(keyTip = "SF"),
                ComboBoxProjection(
                    contentModel = fontSizeComboBoxContentModel,
                    presentationModel = ComboBoxPresentationModel(displayConverter = { "$it   " }),
                ) with RibbonComponentPresentationModel(keyTip = "SS"),
                CommandButtonStripProjection(
                    contentModel = CommandGroup(commands = listOf(indentLeft, indentRight)),
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Horizontal
                    ),
                    overlays = mapOf(
                        indentLeft to CommandButtonPresentationModel.Overlay(actionKeyTip = "AO"),
                        indentRight to CommandButtonPresentationModel.Overlay(actionKeyTip = "AI")
                    )
                ) with RibbonComponentPresentationModel(),
                CommandButtonStripProjection(
                    contentModel = CommandGroup(
                        commands = listOf(
                            styleBoldCommand, styleItalicCommand,
                            styleUnderlineCommand, styleStrikethroughCommand
                        )
                    ),
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Horizontal
                    ),
                    overlays = mapOf(
                        styleBoldCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "1"),
                        styleItalicCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "2"),
                        styleUnderlineCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "3"),
                        styleStrikethroughCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "4")
                    )
                ) with RibbonComponentPresentationModel(),
                CommandButtonStripProjection(
                    contentModel = CommandGroup(
                        commands = listOf(
                            alignLeftCommand, alignCenterCommand,
                            alignRightCommand, alignFillCommand
                        )
                    ),
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Horizontal
                    ),
                    overlays = mapOf(
                        alignLeftCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "AL"),
                        alignCenterCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "AC"),
                        alignRightCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "AR"),
                        alignFillCommand to CommandButtonPresentationModel.Overlay(actionKeyTip = "AF")
                    )
                ) with RibbonComponentPresentationModel()
            )
        )
    }

    enum class DocumentSaveLocation {
        None, Local, Remote, Saved
    }

    @Composable
    fun getDocumentBand(): RibbonBand {
        var saveLocation by remember { mutableStateOf(DocumentSaveLocation.None) }

        return RibbonBand(
            title = resourceBundle.getString("Document.textBandTitle"),
            icon = applications_office(),
            expandCommandKeyTip = "FY",
            expandCommand = Command(
                text = "",
                icon = null,
                action = { println("Expand button clicked! ") }
            ),
            collapsedStateKeyTip = "ZD",
            resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(),
            groups = listOf(
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentLocal.text"),
                                icon = folder(),
                                isActionToggle = true,
                                isActionToggleSelected = (saveLocation == DocumentSaveLocation.Local),
                                onTriggerActionToggleSelectedChange = {
                                    if (it) saveLocation = DocumentSaveLocation.Local
                                },
                                action = { println("Document Local activated") }
                            )
                        ) at PresentationPriority.Top,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentRemote.text"),
                                icon = folder_remote(),
                                isActionToggle = true,
                                isActionToggleSelected = (saveLocation == DocumentSaveLocation.Remote),
                                onTriggerActionToggleSelectedChange = {
                                    if (it) saveLocation = DocumentSaveLocation.Remote
                                },
                                action = { println("Document Remote activated") }
                            )
                        ) at PresentationPriority.Top,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentSaved.text"),
                                icon = folder_saved_search(),
                                isActionToggle = true,
                                isActionToggleSelected = (saveLocation == DocumentSaveLocation.Saved),
                                onTriggerActionToggleSelectedChange = {
                                    if (it) saveLocation = DocumentSaveLocation.Saved
                                },
                                action = { println("Document Saved activated") }
                            )
                        ) at PresentationPriority.Top
                    )
                ),
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentNew.text"),
                                icon = document_new(),
                                action = { println("Document New activated") }
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentOpen.text"),
                                icon = document_open(),
                                action = { println("Document Open activated") }
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentSave.text"),
                                icon = document_save(),
                                action = { println("Document Save activated") }
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentPrint.text"),
                                icon = document_print(),
                                action = { println("Document Print activated") }
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentPrintPreview.text"),
                                icon = document_print_preview(),
                                action = { println("Document Print Preview activated") }
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("DocumentProperties.text"),
                                icon = document_properties(),
                                action = { println("Document Properties activated") }
                            )
                        ) at PresentationPriority.Medium,
                    )
                )
            )
        )
    }

    @Composable
    fun getFindBand(): RibbonBand {
        return RibbonBand(
            title = resourceBundle.getString("Find.textBandTitle"),
            icon = edit_find(),
            collapsedStateKeyTip = "ZY",
            // TODO - custom policy list
            resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(),
            groups = listOf(
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("Search.text"),
                                icon = system_search(),
                                action = { println("Search activated") }
                            ),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "FD")
                        ) at PresentationPriority.Top,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("Find.text"),
                                icon = edit_find(),
                                action = { println("Find activated") }
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("FindReplace.text"),
                                icon = edit_find_replace(),
                                action = { println("Find Replace activated") },
                                isActionEnabled = false
                            )
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("SelectAll.text"),
                                icon = edit_select_all(),
                                action = { println("Select All activated") }
                            )
                        ) at PresentationPriority.Medium
                    )
                )
            )
        )
    }

    fun getActionBand(): RibbonBand {
        return RibbonBand(
            title = resourceBundle.getString("Action.textBandTitle"),
            icon = document_new(),
            expandCommand = Command(
                text = "",
                icon = null,
                action = { println("Expand button clicked! ") }
            ),
            // TODO - custom list
            resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(),
            groups = listOf(
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("AddressBook.text"),
                                icon = address_book_new(),
                                action = { println("Address Book activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "NA")
                        ) at PresentationPriority.Top
                    )
                ),
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Document.text"),
                                icon = document_new(),
                                action = { println("Document activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "ND")
                        ) at PresentationPriority.Top,
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Appointment.text"),
                                icon = appointment_new(),
                                action = { println("Appointment activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "NP")
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Bookmark.text"),
                                icon = bookmark_new(),
                                action = { println("Bookmark activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "NB")
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Contact.text"),
                                icon = contact_new(),
                                action = { println("Contact activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "NC")
                        ) at PresentationPriority.Medium
                    )
                )
            )
        )
    }

    fun getPreferencesBand(): RibbonBand {
        return RibbonBand(
            title = resourceBundle.getString("Preferences.textBandTitle"),
            icon = preferences_desktop_font(),
            expandCommand = Command(
                text = "",
                icon = null,
                action = { println("Expand button clicked! ") }
            ),
            resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(),
            groups = listOf(
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Accessibility.text"),
                                icon = preferences_desktop_accessibility(),
                                action = { println("Accessibility activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "Y")
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Assistive.text"),
                                icon = preferences_desktop_assistive_technology(),
                                action = { println("Assistive activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "E")
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(
                                text = resourceBundle.getString("KeyboardShortcuts.text"),
                                icon = preferences_desktop_keyboard_shortcuts(),
                                secondaryContentModel = getSimpleMenuModel()
                            ),
                            presentationModel = CommandButtonPresentationModel(popupKeyTip = "H")
                        ) at PresentationPriority.Medium
                    )
                ),
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Font.text"),
                                icon = preferences_desktop_font(),
                                action = { println("Font activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "Z")
                        ) at PresentationPriority.Top,
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Locale.text"),
                                icon = preferences_desktop_locale(),
                                action = { println("Locale activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "L")
                        ) at PresentationPriority.Top
                    )
                ),
                RibbonBandGroup(
                    commandProjections = listOf(
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Screensaver.text"),
                                icon = preferences_desktop_screensaver(),
                                action = { println("Screensaver activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "V")
                        ) at PresentationPriority.Medium,
                        CommandButtonProjection(
                            contentModel = Command(text = resourceBundle.getString("Themes.text"),
                                icon = preferences_desktop_locale(),
                                action = { println("Themes activated") }),
                            presentationModel = CommandButtonPresentationModel(actionKeyTip = "T")
                        ) at PresentationPriority.Medium
                    )
                )
            )
        )
    }

    @Composable
    fun getApplicationsBand(): RibbonBand {
        val gamesComboSelectedItem = remember { mutableStateOf(this.applicationGamesEntries[0]) }
        val gamesComboBoxContentModel = ComboBoxContentModel(
            items = this.applicationGamesEntries,
            selectedItem = gamesComboSelectedItem.value,
            onTriggerItemSelectedChange = {
                gamesComboSelectedItem.value = it
                println("New game selection -> $it")
            }
        )

        val internetComboSelectedItem = remember { mutableStateOf(this.applicationInternetEntries[0]) }
        val internetComboBoxContentModel = ComboBoxContentModel(
            items = this.applicationInternetEntries,
            selectedItem = internetComboSelectedItem.value,
            onTriggerItemSelectedChange = {
                internetComboSelectedItem.value = it
                println("New Internet selection -> $it")
            }
        )

        val multimediaComboSelectedItem = remember { mutableStateOf(this.applicationMultimediaEntries[0]) }
        val multimediaComboBoxContentModel = ComboBoxContentModel(
            items = this.applicationMultimediaEntries,
            selectedItem = multimediaComboSelectedItem.value,
            onTriggerItemSelectedChange = {
                multimediaComboSelectedItem.value = it
                println("New multimedia selection -> $it")
            }
        )

        return RibbonBand(
            title = resourceBundle.getString("Applications.textBandTitle"),
            icon = office_calendar_modified(),
            expandCommand = Command(
                text = "",
                icon = null,
                action = { println("Expand button clicked! ") }
            ),
            groups = listOf(
                RibbonBandGroup(
                    componentProjections = listOf(
                        ComboBoxProjection(
                            contentModel = gamesComboBoxContentModel,
                            presentationModel = ComboBoxPresentationModel(displayConverter = { it }),
                        ) with RibbonComponentPresentationModel(
                            caption = resourceBundle.getString("Games.text"),
                            icon = applications_games(),
                            keyTip = "AG",
                            isResizingAware = true,
                            horizontalAlignment = HorizontalAlignment.Leading
                        ),
                        ComboBoxProjection(
                            contentModel = internetComboBoxContentModel,
                            presentationModel = ComboBoxPresentationModel(displayConverter = { it }),
                        ) with RibbonComponentPresentationModel(
                            caption = resourceBundle.getString("Internet.text"),
                            icon = applications_internet(),
                            keyTip = "AI",
                            isResizingAware = true,
                            horizontalAlignment = HorizontalAlignment.Leading
                        ),
                        ComboBoxProjection(
                            contentModel = multimediaComboBoxContentModel,
                            presentationModel = ComboBoxPresentationModel(displayConverter = { it }),
                        ) with RibbonComponentPresentationModel(
                            caption = resourceBundle.getString("Multimedia.text"),
                            keyTip = "AM",
                            isResizingAware = true,
                            horizontalAlignment = HorizontalAlignment.Leading
                        )
                    )
                )
            )
        )
    }
}


