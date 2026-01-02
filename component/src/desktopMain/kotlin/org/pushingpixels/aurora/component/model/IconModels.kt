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

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.IconFilterStrategy

data class IconContentModel(
    val icon: Painter,
    val enabled: Boolean = true
) : ContentModel

object IconSizingConstants {
    val DefaultIconSize = DpSize(16.dp, 16.dp)
}

data class IconPresentationModel(
    val iconDimension: DpSize = IconSizingConstants.DefaultIconSize,
    val iconColorFilter: ColorFilter? = null,
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val inheritStateFromParent: Boolean = false,
) : PresentationModel
