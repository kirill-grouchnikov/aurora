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
package org.pushingpixels.aurora.demo.tonal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.HorizontalAlignment
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.ContainerColorTokens

enum class Variant {
    Compact, Wide
}

@Composable
fun ContainerPreview(colorTokens: ContainerColorTokens, text: String, variant: Variant) {
    Box(modifier = Modifier.width(if (variant == Variant.Compact) 150.dp else 340.dp)
        .height(54.dp)
        .padding(horizontal = 10.dp)
        .border(width = 2.dp, color = colorTokens.containerOutline, shape = RoundedCornerShape(6.dp))
        .background(color = colorTokens.containerSurface, shape = RoundedCornerShape(5.dp))
        .padding(all = 4.dp),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.Start) {
            LabelProjection(
                contentModel = LabelContentModel(text = text),
                presentationModel = LabelPresentationModel(
                    textStyle = TextStyle(color = colorTokens.onContainer),
                    contentPadding = PaddingValues(0.dp),
                    horizontalAlignment = HorizontalAlignment.Center,
                    textMaxLines = 1,
                )
            ).project()
            LabelProjection(
                contentModel = LabelContentModel(text = "$text variant"),
                presentationModel = LabelPresentationModel(
                    textStyle = TextStyle(color = colorTokens.onContainerVariant),
                    contentPadding = PaddingValues(0.dp),
                    horizontalAlignment = HorizontalAlignment.Center,
                    textMaxLines = 1,
                )
            ).project()
        }
    }
}
