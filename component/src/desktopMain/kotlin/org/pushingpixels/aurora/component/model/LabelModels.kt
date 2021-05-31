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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.icon.AuroraIcon

data class LabelContentModel(
    val text: String,
    val enabled: Boolean = true,
    val iconFactory: AuroraIcon.Factory? = null,
    val disabledIconFactory: AuroraIcon.Factory? = null
): ContentModel

object LabelSizingConstants {
    val DefaultLabelContentPadding = PaddingValues(start = 5.dp, top = 4.dp, end = 5.dp, bottom = 4.dp)
    val DefaultLabelIconSize = 16.dp
}

data class LabelPresentationModel(
    val contentPadding: PaddingValues = LabelSizingConstants.DefaultLabelContentPadding,
    val iconDimension: Dp = LabelSizingConstants.DefaultLabelIconSize,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val inheritStateFromParent: Boolean = false,
    val textStyle: TextStyle? = null,
    val textOverflow: TextOverflow = TextOverflow.Clip,
    val textSoftWrap: Boolean = true,
    val textMaxLines: Int = Int.MAX_VALUE,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    val horizontalGapScaleFactor: Float = 1.0f
): PresentationModel
