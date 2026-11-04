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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.filters.composite.ColorHalftoneFilter
import org.pushingpixels.aurora.filters.composite.effectChainModifier
import org.pushingpixels.aurora.filters.convolution.MotionBlurFilter
import kotlin.math.PI

/**
 * Entry point for the Composed Filters Demo.
 *
 * Displays four tiles: the original image, motion blur with zoom, color halftone,
 * and a composed filter chain (halftone over motion blur).
 */
fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(1200.dp, 800.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Composed Filters Demo"
    ) {
        val colorHalftoneFilter = remember { ColorHalftoneFilter() }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ) {
            item {
                OriginalBox()
            }

            item {
                FilterBox(
                    effectModifier = MotionBlurFilter.effectModifier(
                        angle = PI.toFloat() / 4.0f,
                        distanceAmount = 40f,
                        zoom = 0.2f
                    ),
                    renderEffectTitle = "Motion blur (zoom=0.2)"
                )
            }

            item {
                FilterBox(
                    effectModifier = colorHalftoneFilter.effectModifier(dotRadius = 3.0f),
                    renderEffectTitle = "Color halftone"
                )
            }

            item {
                FilterBox(
                    effectModifier = Modifier.effectChainModifier(
                        outer = { size ->
                            colorHalftoneFilter.imageFilter(size, dotRadius = 3.0f)
                        },
                        inner = { size ->
                            MotionBlurFilter.imageFilter(
                                size = size,
                                angle = PI.toFloat() / 4.0f,
                                distanceAmount = 40f,
                                zoom = 0.2f
                            )
                        }
                    ),
                    renderEffectTitle = "Composed (halftone over motion blur)"
                )
            }

            item {
                FilterBox(
                    effectModifier = Modifier.effectChainModifier(
                        outer = { size ->
                            MotionBlurFilter.imageFilter(
                                size = size,
                                angle = PI.toFloat() / 4.0f,
                                distanceAmount = 40f,
                                zoom = 0.2f
                            )
                        },
                        inner = { size ->
                            colorHalftoneFilter.imageFilter(size, dotRadius = 3.0f)
                        }
                    ),
                    renderEffectTitle = "Composed (motion blur over halftone)"
                )
            }
        }
    }
}
