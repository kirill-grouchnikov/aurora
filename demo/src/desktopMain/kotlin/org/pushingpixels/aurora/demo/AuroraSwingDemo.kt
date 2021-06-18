/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo

import androidx.compose.desktop.SwingPanel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.auroraBackground
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JPanel

fun main() {
    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        size = IntSize(200, 150),
        undecorated = true
    ) {
        var text by remember { mutableStateOf("Hello, World!") }

        Column(modifier = Modifier.fillMaxSize()) {
            // This is native Compose content rendered by Aurora
            Row(
                modifier = Modifier.fillMaxWidth().auroraBackground().padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = text,
                        action = { text = "Hello, Desktop!" }
                    )
                ).project()
            }

            // This is Swing content
            SwingPanel(
                modifier = Modifier.fillMaxWidth().height(90.dp),
                factory = {
                    JPanel().apply {
                        layout = FlowLayout(FlowLayout.CENTER)
                        add(swingButton("Hello, Swing!") { println("I come from Swing") })
                    }
                }
            )
        }
    }
}

private fun swingButton(text: String, action: () -> Unit): JButton {
    val button = JButton(text)
    button.addActionListener { action.invoke() }

    return button
}