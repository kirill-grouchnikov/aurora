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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizeSequencingPolicies
import org.pushingpixels.aurora.demo.ColorAuroraIcon
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
    val pageLayoutTask = RibbonTask(
        title = resourceBundle.getString("PageLayout.textTaskTitle"),
        bands = listOf(clipboardBand),
        resizeSequencingPolicy = CoreRibbonResizeSequencingPolicies.RoundRobin(),
        keyTip = "P"
    )
}

private class RibbonBuilder(val resourceBundle: ResourceBundle) {
    val mf = MessageFormat(resourceBundle.getString("TestMenuItem.text"))
    val popupCommand1 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorAuroraIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand2 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorAuroraIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand3 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorAuroraIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand4 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorAuroraIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
        action = { println("Test menu item 1 activated") }
    )
    val popupCommand5 = Command(
        text = mf.format(arrayOf("1")),
        icon = ColorAuroraIcon(Color(red = 0x80, green = 0xDE, blue = 0xEA)),
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
        icon = ColorAuroraIcon(Color(red = 0xFB, green = 0xC0, blue = 0x2D)),
        action = { println("Save Selection activated") }
    )

    val menuClearSelection = Command(
        text = resourceBundle.getString("Format.menuClearSelection.text"),
        icon = ColorAuroraIcon(Color(red = 0xFF, green = 0xA0, blue = 0x00)),
        action = { println("Clear Selection activated") }
    )

    val applyStyles = Command(
        text = resourceBundle.getString("Format.applyStyles.text"),
        icon = ColorAuroraIcon(Color(red = 0xF5, green = 0x7C, blue = 0x00)),
        action = { println("Apply Styles activated") }
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
}


