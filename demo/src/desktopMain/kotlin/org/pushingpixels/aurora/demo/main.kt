package org.pushingpixels.aurora.demo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.auroraBackground
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow

@OptIn(ExperimentalFoundationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(220.dp, 150.dp)
    )

    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication
    ) {
        var text by remember { mutableStateOf("Hello, World!") }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().auroraBackground()
        ) {
            CommandButtonProjection(
                contentModel = Command(
                    text = text,
                    action = { text = "Hello, Desktop!" }
                )
            ).project()
        }
    }
}
