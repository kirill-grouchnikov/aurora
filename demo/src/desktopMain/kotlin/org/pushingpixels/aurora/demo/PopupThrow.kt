package org.pushingpixels.aurora.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Rectangle

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Demo") {
        Box(modifier = Modifier.size(100.dp).background(Color.Red).clickable {
            val popupContentWindow = ComposeWindow()
            popupContentWindow.focusableWindowState = false
            popupContentWindow.type = java.awt.Window.Type.POPUP
            popupContentWindow.isAlwaysOnTop = true
            popupContentWindow.isUndecorated = true
            popupContentWindow.isResizable = false

            popupContentWindow.bounds = Rectangle(600, 400, 200, 200)
            popupContentWindow.setContent {
                Box(modifier = Modifier.fillMaxSize().background(Color.Blue).clickable {
                    popupContentWindow.isVisible = false
                    popupContentWindow.dispose()
                })
            }

            popupContentWindow.invalidate()
            popupContentWindow.validate()
            popupContentWindow.isVisible = true
        })
    }
}