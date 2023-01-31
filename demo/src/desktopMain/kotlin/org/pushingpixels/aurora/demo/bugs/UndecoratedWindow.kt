package org.pushingpixels.aurora.demo.bugs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )
    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        resizable = true,
        state = state
    ) {
        Column(Modifier.fillMaxSize()) {
            WindowDraggableArea {
                Box(modifier = Modifier.fillMaxWidth().height(24.dp).background(Color.Blue))
            }
            Box(modifier = Modifier.fillMaxWidth().height(32.dp).background(Color.Green))
            Box(modifier = Modifier.fillMaxWidth().weight(1.0f).background(Color.Red))
        }
    }
}