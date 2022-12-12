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
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
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

    val pageLayoutTask = RibbonTask(
        title = resourceBundle.getString("PageLayout.textTaskTitle"),
        bands = listOf(clipboardBand, quickStylesBand),
        resizeSequencingPolicy = CoreRibbonResizeSequencingPolicies.RoundRobin(),
        keyTip = "P"
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

    var mfButtonText = MessageFormat(
        resourceBundle.getString("StylesGallery.textButton")
    )
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
                                        font = Font(Typeface.makeDefault())
                                    ),
                                    x = 2.0f,
                                    y = size.height - 12.0f,
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
                                        font = Font(Typeface.makeDefault())
                                    ),
                                    x = 2.0f,
                                    y = size.height - 12.0f,
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
            commandProjections = listOf(
                RibbonCommandButtonProjection(
                    contentModel = pasteCommand,
                    presentationModel = RibbonCommandButtonPresentationModel(
                        presentationPriority = PresentationPriority.Top,
                        actionKeyTip = "Y",
                        popupKeyTip = "V",
                        textClick = TextClick.Action
                    ),
                    overlays = mapOf(
                        popupCommand1 to RibbonCommandButtonPresentationModel.Overlay(popupKeyTip = "1"),
                        popupCommand2 to RibbonCommandButtonPresentationModel.Overlay(popupKeyTip = "2"),
                        popupCommand3 to RibbonCommandButtonPresentationModel.Overlay(popupKeyTip = "3"),
                        popupCommand4 to RibbonCommandButtonPresentationModel.Overlay(popupKeyTip = "4"),
                        popupCommand5 to RibbonCommandButtonPresentationModel.Overlay(popupKeyTip = "5"),
                    )
                ),
                RibbonCommandButtonProjection(
                    contentModel = cutCommand,
                    presentationModel = RibbonCommandButtonPresentationModel(
                        presentationPriority = PresentationPriority.Medium,
                        popupKeyTip = "X",
                        textClick = TextClick.Action
                    )
                ),
                RibbonCommandButtonProjection(
                    contentModel = copyCommand,
                    presentationModel = RibbonCommandButtonPresentationModel(
                        presentationPriority = PresentationPriority.Medium,
                        popupKeyTip = "C",
                        textClick = TextClick.Popup
                    )
                ),
                RibbonCommandButtonProjection(
                    contentModel = formatCommand,
                    presentationModel = RibbonCommandButtonPresentationModel(
                        presentationPriority = PresentationPriority.Medium,
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
                        this.menuSaveSelection to RibbonCommandButtonPresentationModel.Overlay(actionKeyTip = "SS"),
                        this.menuClearSelection to RibbonCommandButtonPresentationModel.Overlay(actionKeyTip = "SC"),
                        this.applyStyles to RibbonCommandButtonPresentationModel.Overlay(actionKeyTip = "SA")
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
            commandProjections = listOf(
                RibbonCommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Styles1.text"),
                        icon = font_x_generic(),
                        action = { println("Generic activated") }
                    ),
                    presentationModel = RibbonCommandButtonPresentationModel(
                        presentationPriority = PresentationPriority.Medium,
                        actionKeyTip = "SA"
                    )
                ),
                RibbonCommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Styles2.text"),
                        icon = image_x_generic(),
                        action = { println("Image activated") }
                    ),
                    presentationModel = RibbonCommandButtonPresentationModel(
                        presentationPriority = PresentationPriority.Medium,
                        actionKeyTip = "SB"
                    )
                )
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
                )
            )
        )
    }
}


