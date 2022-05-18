package org.pushingpixels.aurora.demo.bugs

import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication

fun main() = auroraApplication {
    val windowState = rememberWindowState()
    AuroraWindow(
        skin = marinerSkin(),
        state = windowState,
        onCloseRequest = ::exitApplication,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = "Fullscreen Top",
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Fullscreen",
                                    action = {
                                        if (windowState.placement == WindowPlacement.Floating) {
                                            windowState.placement = WindowPlacement.Fullscreen
                                        } else {
                                            windowState.placement = WindowPlacement.Floating
                                        }
                                    }
                                )
                            )
                        )
                    )
                )
            )
        )
    ) {

    }
}