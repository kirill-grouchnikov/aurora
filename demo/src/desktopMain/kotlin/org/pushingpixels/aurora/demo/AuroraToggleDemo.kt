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
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.ButtonSizingStrategy
import org.pushingpixels.aurora.component.AuroraToggleButton
import org.pushingpixels.aurora.demo.svg.tango.format_justify_center
import org.pushingpixels.aurora.demo.svg.tango.format_justify_left
import org.pushingpixels.aurora.icon.AuroraIcon
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

    AuroraToggleButton(
        selected = (alignment == ToggleAlignment.CENTER),
        onTriggerSelectedChange = {
            println("Here in first $it")
            if (it) alignment = ToggleAlignment.CENTER
        },
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
        sizingStrategy = ButtonSizingStrategy.COMPACT
    ) {
        AuroraIcon(
            icon = format_justify_center.of(10.dp, 10.dp),
        )
    }
    AuroraToggleButton(
        selected = (alignment == ToggleAlignment.LEFT),
        onTriggerSelectedChange = {
            println("Here in second $it")
            if (it) alignment = ToggleAlignment.LEFT
        },
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
        sizingStrategy = ButtonSizingStrategy.COMPACT
    ) {
        AuroraIcon(
            icon = format_justify_left.of(10.dp, 10.dp),
        )
    }
}

@Composable
fun DemoToggleToolbarBooleans() {
    var isCenter by remember { mutableStateOf(true) }
    var isLeft by remember { mutableStateOf(false) }

    AuroraToggleButton(
        selected = isCenter,
        onTriggerSelectedChange = {
            isCenter = it
            isLeft = !it
        },
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
        sizingStrategy = ButtonSizingStrategy.COMPACT
    ) {
        AuroraIcon(
            icon = format_justify_center.of(10.dp, 10.dp),
        )
    }
    AuroraToggleButton(
        selected = isLeft,
        onTriggerSelectedChange = {
            isLeft = it
            isCenter = !it
        },
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
        sizingStrategy = ButtonSizingStrategy.COMPACT
    ) {
        AuroraIcon(
            icon = format_justify_left.of(10.dp, 10.dp),
        )
    }
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



