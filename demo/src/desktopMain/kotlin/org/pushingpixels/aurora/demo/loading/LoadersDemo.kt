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
package org.pushingpixels.aurora.demo.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.loading.*

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(640.dp, 560.dp)
    )

    Window(
        title = "Loaders Demo",
        state = state,
        onCloseRequest = ::exitApplication
    ) {
        val params = SpinnerParams(color = Color(0xFF007F7C))

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item { SpinnerItem("Arc") { Arc(params = params) } }
            item { SpinnerItem("Atom") { Atom(params = params) } }
            item { SpinnerItem("Blocks") { Blocks(params = params, sweep = BlocksSweep.Diagonal) } }
            item { SpinnerItem("BouncingDots") { BouncingDots(params = params) } }
            item { SpinnerItem("Cascade") { Cascade(params = params) } }
            item { SpinnerItem("CircularDots") { CircularDots(params = params) } }
            item { SpinnerItem("Classic") { Classic(params = params) } }
            item { SpinnerItem("ClassicV2") { ClassicV2(params = params) } }
            item { SpinnerItem("Clock") { Clock(params = params) } }
            item { SpinnerItem("Comet") { Comet(params = params) } }
            item { SpinnerItem("Compass") { Compass(params = params) } }
            item { SpinnerItem("Dual") { Dual(params = params) } }
            item { SpinnerItem("Eclipse") { Eclipse(params = params) } }
            item { SpinnerItem("Flip") { Flip(params = params) } }
            item { SpinnerItem("Gather") { Gather(params = params) } }
            item { SpinnerItem("Leap") { Leap(params = params) } }
            item { SpinnerItem("LinearDots") { LinearDots(params = params) } }
            item { SpinnerItem("Loading") { Loading(params = params) } }
            item { SpinnerItem("Morph") { Morph(params = params) } }
            item { SpinnerItem("Orbit") { Orbit(params = params) } }
            item { SpinnerItem("Pulse") { Pulse(params = params) } }
            item { SpinnerItem("Radar") { Radar(params = params) } }
            item { SpinnerItem("Ring") { Ring(params = params) } }
            item { SpinnerItem("Ripple") { Ripple(params = params) } }
            item { SpinnerItem("Slide") { Slide(params = params) } }
            item { SpinnerItem("Snake") { Snake(params = params) } }
            item { SpinnerItem("Swirl") { Swirl(params = params) } }
            item { SpinnerItem("Trace") { Trace(params = params) } }
            item { SpinnerItem("Wave") { Wave(params = params) } }
        }
    }
}

@Composable
private fun SpinnerItem(name: String, content: @Composable () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        content()
        BasicText(text = name, style = TextStyle(color = Color.Black))
    }
}

