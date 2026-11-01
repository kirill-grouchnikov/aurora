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
package org.pushingpixels.aurora.demo.filters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.filters.utility.makeHorizontalGradientFilter
import org.pushingpixels.aurora.filters.utility.makeMultiStopHorizontalGradientFilter

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(androidx.compose.ui.Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Horizontal Gradient Filter Demo"
    ) {
        GradientBox()
    }
}

@Composable
fun GradientBox() {
    val gradientFilter = remember {
        makeMultiStopHorizontalGradientFilter(
            listOf(Color(0xFF0000FF),
                Color(0xFF00FF00),
                Color(0xFFFF0000),
                Color(0xFFFFFF00)),
            width = 1200
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                renderEffect = gradientFilter.asComposeRenderEffect(),
                clip = false
            )
    )
}
