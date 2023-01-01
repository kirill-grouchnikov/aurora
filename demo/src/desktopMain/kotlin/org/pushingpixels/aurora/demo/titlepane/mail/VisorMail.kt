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
package org.pushingpixels.aurora.demo.titlepane.mail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

object VisorDecorations {
    val Destinations = DecorationAreaType("Visor Destinations")
    val Threads = DecorationAreaType("Visor Threads")
}

@ExperimentalUnitApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(920.dp, 600.dp)
    )

    // Extend our content into the title pane and configure the title control buttons to be
    // vertically centered and in the leading horizontal position (in our main selector
    // pane). Also increase the height of the title pane to play nicer with additional
    // content that we are displaying in that area.
    AuroraWindow(
        skin = visorSkin(),
        title = "",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraIntegrated(
            titlePaneHeight = 40.dp,
            titleControlButtonGroupHorizontalGravity = HorizontalGravity.Leading,
            titleControlButtonGroupVerticalGravity = VerticalGravity.Centered,
        ),
        onCloseRequest = ::exitApplication
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // In the real app the space allocation between the panels will probably be
            // more intelligent, allowing the user to reallocate the available space.
            DestinationsPanel(modifier = Modifier.weight(0.2f).fillMaxHeight())
            AuroraDecorationArea(decorationAreaType = VisorDecorations.Threads) {
                Box(modifier = Modifier.fillMaxHeight().width(1.dp).auroraBackground()) {
                    VisorMailPanelSeparator(true)
                }
            }
            ThreadListPanel(modifier = Modifier.weight(0.3f).fillMaxHeight())
            AuroraDecorationArea(decorationAreaType = VisorDecorations.Threads) {
                Box(modifier = Modifier.fillMaxHeight().width(1.dp).auroraBackground()) {
                    VisorMailPanelSeparator(false)
                }
            }
            ThreadPanel(modifier = Modifier.weight(0.5f, true).fillMaxHeight())
        }
    }
}

@Composable
private fun VisorMailPanelSeparator(isLeading: Boolean) {
    val separatorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Separator,
        componentState = ComponentState.Enabled
    )

    val ltr = (LocalLayoutDirection.current == LayoutDirection.Ltr)
    val isLeft = ltr xor isLeading

    Canvas(modifier = Modifier.fillMaxHeight().width(1.dp)) {
        val x = if (isLeft) 0.5f else size.width - 0.5f
        drawLine(
            color = separatorScheme.separatorPrimaryColor,
            start = Offset(x, 0.0f),
            end = Offset(x, size.height),
            strokeWidth = 1.0f
        )
    }
}


