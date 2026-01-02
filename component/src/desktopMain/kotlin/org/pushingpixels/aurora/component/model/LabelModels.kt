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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.IconFilterStrategy

data class LabelContentModel(
    val text: String,
    val enabled: Boolean = true,
    val icon: Painter? = null
): ContentModel

object LabelSizingConstants {
    val DefaultLabelContentPadding = PaddingValues(start = 5.dp, top = 4.dp, end = 5.dp, bottom = 4.dp)
    val DefaultLabelIconSize = DpSize(16.dp, 16.dp)
    val DefaultIconTextGap = 4.dp
}

data class LabelPresentationModel(
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val contentPadding: PaddingValues = LabelSizingConstants.DefaultLabelContentPadding,
    val iconDimension: DpSize = LabelSizingConstants.DefaultLabelIconSize,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val inheritStateFromParent: Boolean = false,
    val textStyle: TextStyle? = null,
    val textOverflow: TextOverflow = TextOverflow.Clip,
    val textSoftWrap: Boolean = true,
    val textMaxLines: Int = Int.MAX_VALUE,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val iconTextGap: Dp = LabelSizingConstants.DefaultIconTextGap,
    val horizontalGapScaleFactor: Float = 1.0f,
    val singleLineDisplayPrototype: String? = null
): PresentationModel
