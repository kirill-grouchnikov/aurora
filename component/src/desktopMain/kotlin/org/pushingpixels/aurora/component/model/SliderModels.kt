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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay

data class SliderContentModel(
    val value: Float,
    val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val onTriggerValueChange: (Float) -> Unit,
    val onValueChangeEnd: () -> Unit = {},
    val enabled: Boolean = true
): ContentModel

object SliderSizingConstants {
    val DefaultSliderContentPadding = PaddingValues(start = 0.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)
    val DefaultWidth = 240.dp
    val ThumbFullSize = 18.dp
    val TrackHeight = 6.dp
    val TrackTickGap = 4.dp
    val TickHeight = 8.dp
}

data class SliderPresentationModel(
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val tickSteps: Int = 0, // Zero means continuous slider value range
    val snapToTicks: Boolean = false,
    val drawTicks: Boolean = false
): PresentationModel
