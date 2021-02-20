/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.AuroraCheckBox
import org.pushingpixels.aurora.component.AuroraText
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.demo.svg.tango.accessories_text_editor
import org.pushingpixels.aurora.demo.svg.tango.computer
import org.pushingpixels.aurora.skin.graphiteSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    AuroraWindow(
        skin = graphiteSkin(),
        title = "Aurora Demo",
        size = IntSize(660, 200),
        undecorated = true
    ) {
        DemoCommandContent()
    }
}

@Composable
fun DemoCommandContent() {
    val contentEnabled = remember { mutableStateOf(true) }
    val popupEnabled = remember { mutableStateOf(true) }
    val command1 = remember {
        Command(
            text = "One",
            iconFactory = accessories_text_editor.factory(),
            action = { println("One activated!") },
            isActionEnabled = contentEnabled,
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: Command?) {
                    println("One preview activated!")
                }

                override fun onCommandPreviewCanceled(command: Command?) {
                    println("One preview canceled!")
                }
            }
        )
    }
    val command2 = remember {
        Command(
            text = "Two",
            iconFactory = computer.factory(),
            action = { println("Two activated!") },
            isActionEnabled = contentEnabled
        )
    }
    val command3 = remember {
        Command(
            text = "Split",
            iconFactory = computer.factory(),
            action = { println("Split activated!") },
            isActionEnabled = contentEnabled,
            isSecondaryEnabled = popupEnabled,
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    title = "Group",
                    command = listOf(
                        Command(
                            text = "popup1",
                            iconFactory = computer.factory(),
                            action = { println("popup1 activated!") },
                            isActionEnabled = contentEnabled
                        ),
                        Command(
                            text = "popup2",
                            iconFactory = computer.factory(),
                            action = { println("popup2 activated!") },
                            isActionEnabled = contentEnabled
                        ),
                        Command(
                            text = "popup3",
                            iconFactory = computer.factory(),
                            action = { println("popup3 activated!") },
                            isActionEnabled = contentEnabled
                        )
                    )
                )
            )
        )
    }

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(4.dp)) {
        AuroraCheckBox(
            selected = contentEnabled.value,
            onTriggerSelectedChange = { contentEnabled.value = !contentEnabled.value }
        ) {
            AuroraText(text = "content enabled")
        }

        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(4.dp)) {
            AuroraCommandButton(command = command1, presentationModel = CommandPresentationModel())
        }
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(4.dp)) {
            AuroraCommandButton(command = command2, presentationModel = CommandPresentationModel())
        }
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(4.dp)) {
            AuroraSplitButton(command = command3, presentationModel = CommandPresentationModel())
        }
    }
}



