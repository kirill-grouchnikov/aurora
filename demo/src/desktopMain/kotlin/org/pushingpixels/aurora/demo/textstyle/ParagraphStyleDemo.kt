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
package org.pushingpixels.aurora.demo.textstyle

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.component.projection.TextFieldValueProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.format_justify_center
import org.pushingpixels.aurora.demo.svg.tango.format_justify_fill
import org.pushingpixels.aurora.demo.svg.tango.format_justify_left
import org.pushingpixels.aurora.demo.svg.tango.format_justify_right
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 320.dp)
    )

    AuroraWindow(
        skin = marinerSkin(),
        title = "Text alignment demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {

        val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim" +
                " veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip " +
                "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum."
        var textAlign by remember { mutableStateOf(TextAlign.Left) }
        val paragraphStyle by derivedStateOf { ParagraphStyle(textAlign = textAlign) }
        val textFieldValue by derivedStateOf {
            TextFieldValue(
                annotatedString = AnnotatedString(
                    text = text,
                    paragraphStyle = paragraphStyle
                )
            )
        }

        // Align left command
        val commandAlignLeft = Command(
            text = "Left",
            icon = format_justify_left(),
            isActionToggle = true,
            isActionToggleSelected = (textAlign == TextAlign.Left),
            onTriggerActionToggleSelectedChange = {
                if (it) textAlign = TextAlign.Left
            }
        )

        // Align center command
        val commandAlignCenter = Command(
            text = "Center",
            icon = format_justify_center(),
            isActionToggle = true,
            isActionToggleSelected = (textAlign == TextAlign.Center),
            onTriggerActionToggleSelectedChange = {
                if (it) textAlign = TextAlign.Center
            }
        )

        // Align right command
        val commandAlignRight = Command(
            text = "Right",
            icon = format_justify_right(),
            isActionToggle = true,
            isActionToggleSelected = (textAlign == TextAlign.Right),
            onTriggerActionToggleSelectedChange = {
                if (it) textAlign = TextAlign.Right
            }
        )

        // Align fill command
        val commandAlignFill = Command(
            text = "Fill",
            icon = format_justify_fill(),
            isActionToggle = true,
            isActionToggleSelected = (textAlign == TextAlign.Justify),
            onTriggerActionToggleSelectedChange = {
                if (it) textAlign = TextAlign.Justify
            }
        )

        Row(
            modifier = Modifier.fillMaxSize().padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(modifier = Modifier.padding(top = 6.dp)) {
                CommandButtonStripProjection(
                    contentModel = CommandGroup(
                        commands = listOf(
                            commandAlignLeft,
                            commandAlignCenter,
                            commandAlignRight,
                            commandAlignFill
                        )
                    ),
                    presentationModel = CommandStripPresentationModel(
                        orientation = StripOrientation.Vertical,
                        commandPresentationState = CommandButtonPresentationState.Small,
                        horizontalGapScaleFactor = 0.75f
                    )
                ).project()
            }

            TextFieldValueProjection(
                contentModel = TextFieldValueContentModel(
                    value = textFieldValue,
                    readOnly = true,
                    onValueChange = {}
                ),
                presentationModel = TextFieldPresentationModel(showBorder = false)
            ).project(modifier = Modifier.weight(1.0f, true))
        }
    }
}
