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
import androidx.compose.material.Checkbox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.component.AuroraCommandButton
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.demo.svg.tango.format_justify_center
import org.pushingpixels.aurora.demo.svg.tango.format_justify_left
import org.pushingpixels.aurora.skin.nebulaBrickWallSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    AuroraWindow(
        skin = nebulaBrickWallSkin(),
        title = "Aurora Demo",
        size = IntSize(660, 200),
        undecorated = true
    ) {
        DemoToggleContent()
    }
}

private enum class ToggleAlignment {
    CENTER, LEFT
}

@Composable
fun DemoToggleToolbarEnum() {
    var alignment by remember { mutableStateOf(ToggleAlignment.CENTER) }

    AuroraCommandButton(
        command = Command(
            text = "",
            iconFactory = format_justify_center.factory(),
            isActionToggle = true,
            isActionToggleSelected = (alignment == ToggleAlignment.CENTER),
            onTriggerActionToggleSelectedChange = {
                println("Here in first $it")
                if (it) alignment = ToggleAlignment.CENTER
            },
        ),
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.SMALL,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT
        )
    )

    AuroraCommandButton(
        command = Command(
            text = "",
            iconFactory = format_justify_left.factory(),
            isActionToggle = true,
            isActionToggleSelected = (alignment == ToggleAlignment.LEFT),
            onTriggerActionToggleSelectedChange = {
                println("Here in second $it")
                if (it) alignment = ToggleAlignment.LEFT
            }
        ),
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.SMALL,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT
        )
    )
}

@Composable
fun DemoToggleToolbarBooleans() {
    var isCenter by remember { mutableStateOf(true) }
    var isLeft by remember { mutableStateOf(false) }

    AuroraCommandButton(
        command = Command(
            text = "",
            iconFactory = format_justify_center.factory(),
            isActionToggle = true,
            isActionToggleSelected = isCenter,
            onTriggerActionToggleSelectedChange = {
                isCenter = it
                isLeft = !it
            }
        ),
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.SMALL,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT
        )
    )
    AuroraCommandButton(
        command = Command(
            text = "",
            iconFactory = format_justify_left.factory(),
            isActionToggle = true,
            isActionToggleSelected = isLeft,
            onTriggerActionToggleSelectedChange = {
                isLeft = it
                isCenter = !it
            }
        ),
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.SMALL,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT
        )
    )
}

@Composable
fun DemoToggleContent() {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
            DemoToggleToolbarEnum()
        }
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
            DemoToggleToolbarBooleans()
        }
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
            val checkedState = remember { mutableStateOf(true) }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
            Checkbox(
                checked = true,
                onCheckedChange = { println("New value $it") }
            )
        }
    }
}



